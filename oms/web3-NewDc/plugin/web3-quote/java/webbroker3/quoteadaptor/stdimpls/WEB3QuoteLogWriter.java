head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.41.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteLogWriter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3QuoteLogWriterクラス(WEB3QuoteLogWriter.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/05/18 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.quoteadaptor.stdimpls;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.util.WEB3LogUtility;

/**
 * 受信した時価情報をファ@イルに出力するクラス。<BR>
 * <BR>
 * ファ@イルに出力する時価情報を内部キューに一時的にためることにより、
 * 非同期でログファ@イルに出力することができる。<BR>
 * <BR>
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
class WEB3QuoteLogWriter
{

    /** ログ出力ユーティリティ */
    private static final WEB3LogUtility LOG = 
        WEB3LogUtility.getInstance(WEB3QuoteLogWriter.class);

    /** デバック出力フラグ */
    private static final boolean DBG = LOG.ison();
    
    /** キューの初期容量 */
    private static final int INITIAL_QUEUE_SIZE = 256;
    
    /** ファ@イルに出力する時価情報を一時的に保管するキュー */
    private final Queue queue;    

    /** ファ@イルに出力する時価情報の作業用インスタンス */
    private final QuoteData tmpData;
    
    /** ファ@イルに出力するスレッドのインスタンス */
    private LogWriterThread logWriterThread;

    /** プロトコル */
    private String protocol;

    /** スレッドローカル日付フォーマットプール */
    private static final ThreadLocal DATE_FORMAT_POOL = new ThreadLocal() {
        protected Object initialValue()
        {
            return new HashMap();
        }
    };

    /** 日付フォーマット：yyyy-MM-dd HH:mm:ss.SSS */
    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    /** コンストラクタ */
    WEB3QuoteLogWriter(String l_strProtocol)
    {
        this.queue = new Queue(INITIAL_QUEUE_SIZE);
        this.tmpData = new QuoteData();
        this.protocol = l_strProtocol;
    }

    /**
     * ファ@イル出力を開始する。
     */
    synchronized void start()
    {
        
        if (logWriterThread != null)
        {
            throw new IllegalStateException("LogWriterThread has already started.");
        }
        
        int priority = 5;
        logWriterThread = new LogWriterThread();
        logWriterThread.setName("LogWriterThread");
        logWriterThread.setDaemon(true);
        logWriterThread.setPriority(priority);
        logWriterThread.start();
        
        if (DBG)
        {
            LOG.debug("LogWriterThread created!!! [priority=" + priority + "]");
        }

    }

    /**
     * ファ@イル出力を停止する。
     *
     */
    synchronized void stop()
    {
        
        if (logWriterThread != null)
        {
            try
            {
                logWriterThread.isActive = false;
                logWriterThread.interrupt();
                logWriterThread.join();
            } catch (InterruptedException ie)
            {
                LOG.warn("Exception occured while waiting for LogWriterThread finish.");
            }
        }
    }

    /**
     * 時価情報をファ@イルに出力する。
     * 
     * @@param headerBuf 時価サーバより受信した時価情報（ヘッダー部）
     * @@param dataBuf 時価サーバより受信した時価情報（データ部）
     */
    void write(byte[] headerBuf, byte[] dataBuf)
    {
        tmpData.headerBuf = headerBuf;
        tmpData.dataBuf = dataBuf;
        queue.push(tmpData);
    }
    
    /**
     * 時価情報を出力するファ@イルの名称を取得する。
     */
    private String getFileName()
    {
        String sep = System.getProperty("file.separator");
        String dirName = WEB3QuotePropertyManager.getProperty(
                WEB3QuotePlugin.OUTPUT_DIR_PREF_NAME, 
                "." + sep);
        if (!dirName.endsWith(sep))
        {
            dirName += sep;
        }
        String timestamp = GtlUtils.getThreadSafeSimpleDateFormat(
                "yyyyMMdd_HHmm").format(GtlUtils.getSystemTimestamp());
        return dirName + "web3_quote_received-" + protocol + "-" + timestamp + ".txt";
    }

    static DateFormat getDateFormat(String pattern)
    {
        Map dateFormatMap = (Map) DATE_FORMAT_POOL.get();
        DateFormat dateFormat = (DateFormat) dateFormatMap.get(pattern);
        if (dateFormat == null)
        {
            dateFormat = new SimpleDateFormat(pattern);
            dateFormatMap.put(pattern, dateFormat);
        }
        return dateFormat;
    }

    /**
     * ファ@イルに出力する時価情報を表すオブジェクト
     *
     * @@author Takuji Yamada (FLJ)
     * @@version 1.0
     */
    private class QuoteData
    {
        
        /** 時価情報のヘッダー部分 */
        private byte[] headerBuf;
        
        /** 時価情報のデータ部分 */
        private byte[] dataBuf;

        /**
         * コンストラクタ
         */
        public QuoteData()
        {
            if(WEB3QuoteConstants.QUOTE_PROTOCOL_TCP.equalsIgnoreCase(protocol))
            {
                headerBuf = new byte[WEB3QuoteConstants.HEADER_SIZE];
            }
            else
            {
                headerBuf = new byte[WEB3QuoteConstants.RMM_HEADER_SIZE];
            }
            dataBuf = new byte[WEB3QuoteConstants.MAX_DATA_SIZE];
        }
        
        /**
         * 指定した時価情報から内容をコピーする
         * 
         * @@param source コピー元時価情報
         */
        void copy(QuoteData source)
        {
            int numOfRecords = source.getNumOfRecords();
            if(WEB3QuoteConstants.QUOTE_PROTOCOL_TCP.equalsIgnoreCase(protocol))
            {
                System.arraycopy(source.headerBuf, 0, headerBuf, 0,
                        WEB3QuoteConstants.HEADER_SIZE);
            }
            else
            {
                System.arraycopy(source.headerBuf, 0, headerBuf, 0,
                        WEB3QuoteConstants.RMM_HEADER_SIZE);
            }
            System.arraycopy(source.dataBuf, 0, dataBuf, 0,
                    WEB3QuoteConstants.RECORD_SIZE * numOfRecords);
        }
        
        /**
         * この時価情報が含む時価レコードの件数を取得する。
         */
        int getNumOfRecords()
        {
            if(WEB3QuoteConstants.QUOTE_PROTOCOL_TCP.equalsIgnoreCase(protocol))
            {
                return WEB3QuoteUtil.toInteger(headerBuf,
                        WEB3QuoteConstants.SEQUENCE_NO_SIZE,
                        WEB3QuoteConstants.NUM_OF_RECORDS_SIZE);
            }
            else
            {
                return WEB3QuoteUtil.toInteger(headerBuf,
                        WEB3QuoteConstants.FROM_MACHINE_NAME_SIZE + WEB3QuoteConstants.TO_MACHINE_NAME_SIZE + WEB3QuoteConstants.SEQUENCE_NO_SIZE,
                        WEB3QuoteConstants.NUM_OF_RECORDS_SIZE);
            }
        }
        
        /**
         * この時価情報のヘッダー部分を文字列で取得する。
         */
        String getHeader()
        {
            if(WEB3QuoteConstants.QUOTE_PROTOCOL_TCP.equalsIgnoreCase(protocol))
            {
                return WEB3QuoteUtil.toString(headerBuf, 0,
                        WEB3QuoteConstants.HEADER_SIZE);
            }
            else
            {
                return WEB3QuoteUtil.toString(headerBuf, 0,
                        WEB3QuoteConstants.RMM_HEADER_SIZE);
            }
        }
        
        /**
         * この時価情報が含む時価レコードを文字列で取得する。
         * 
         * @@param index 取得する時価レコードのインデックス
         */
        String getData(int index)
        {
            int offset = (index - 1) * WEB3QuoteConstants.RECORD_SIZE;
            return WEB3QuoteUtil.toString(dataBuf, offset,
                    WEB3QuoteConstants.RECORD_SIZE - 1, false);
        }
        
    }
    
    /**
     * ファ@イルに出力する時価情報を一時的にためておくキュー
     *
     * @@author Takuji Yamada (FLJ)
     * @@version 1.0
     */
    private class Queue
    {
        
        private final Object lock = new Object();
        
        /** 時価情報の配列 */
        private QuoteData[] queue;
        
        /** POPするときのインデックス */
        private int head;
        
        /** PUSHするときのインデックス */
        private int tail;
        
        /** 末尾 */
        private int last;
        
        /**
         * コンストラクタ
         * 
         * @@param size キューの初期容量
         */
        private Queue(int size)
        {
            QuoteData[] queue = new QuoteData[size];
            for (int i = 0; i < queue.length; i++)
            {
                queue[i] = new QuoteData();
            }
            this.queue = queue;
            this.head = 0;
            this.tail = 0;
            this.last = size - 1;
        }
        
        /**
         * キューの最後に時価情報を追加する。
         */
        private void push(QuoteData data)
        {
            
            synchronized (lock)
            {

                if (((tail + 1) & last) == head)
                {
                    expand();
                    if (DBG)
                    {
                        LOG.debug("LogWriter's queue expanded. [new capacity=" + queue.length + "]");
                    }
                }

                queue[tail].copy(data);
                tail = (tail + 1) & last;
                lock.notify();
                
            }

        }
        
        /**
         * キューの先頭から時価情報を取得し、先頭の時価情報を削除する。
         */
        private QuoteData pop()
        {
            
            synchronized (lock)
            {
                
                QuoteData data = null;
                
                //
                // if the queue is empty, wait until
                // someone push an element.
                //
                while (tail == head)
                {
                    if(!logWriterThread.isActive)
                    {
                        return null;
                    }
                    // Wait for an event to be pushed in
                    try
                    {
                        LOG.debug("LogWriter Message Queue is empty now waiting...");
                        lock.wait();
                    } catch (InterruptedException e)
                    {
                    }
                }

                 
                if (tail != head)
                {
                    data = new QuoteData();
                    data.copy(queue[head]);
                    head = (head + 1) & last;
                    lock.notify();
                }
                
                return data;
                
            }
            
        }
        
        /**
         * キューの容量を拡張する
         */
        private void expand()
        {
            QuoteData[] newQueue = new QuoteData[(last + 1) * 2];
            System.arraycopy(queue, head, newQueue, 0, (last - head + 1));
            if (head > 0)
            {
                System.arraycopy(queue, 0, newQueue, (last - head + 1), tail);
            }
            for (int i = last; i < newQueue.length; i++)
            {
                newQueue[i] = new QuoteData();
            }
            queue = newQueue;
            head = 0;
            tail = last;
            last = newQueue.length - 1;
        }
        
    }
    
    /**
     * キューにプッシュされた時価情報をファ@イルに出力するスレッド
     *
     * @@author Takuji Yamada (FLJ)
     * @@version 1.0
     */
    private class LogWriterThread extends Thread
    {
        
        /** スレッドが処理中の間「true」を設定するフラグ */
        private boolean isActive = true;

        /**
         * @@see java.lang.Runnable#run()
         */
        public void run()
        {
            BufferedWriter writer = null;
            try
            {
                String fileName = getFileName();
                writer = new BufferedWriter(new FileWriter(fileName));
                
                LOG.info("LogWriterThread start writing. [file=" + fileName + "]");
                
                while (true)
                {
                    
                    QuoteData quoteData = queue.pop();
                    if (!isActive && quoteData == null)
                    {
                        break;
                    }
                    
                    if (quoteData != null)
                    {

                        int numOfRecords = quoteData.getNumOfRecords();
                        String header = quoteData.getHeader();
                        for (int i = 1; i <= numOfRecords; i++)
                        {
                            String data = quoteData.getData(i);
                            writer.write(header);
                            DateFormat l_dateFormat = getDateFormat(TIMESTAMP_FORMAT);
                            writer.write(l_dateFormat.format(new Timestamp(System.currentTimeMillis())));
                            writer.write(data);
                            writer.newLine();
                        }
                        writer.flush();
                    }
                    
                }

            } catch (IOException ioe)
            {
                LOG.error("Exception occured while running.", ioe);
            } finally
            {
                if (writer != null)
                {
                    try
                    {
                        writer.flush();
                        writer.close();
                    } catch (IOException ioe)
                    {
                        LOG.error("Exception occured while closing file.", ioe);
                    }
                }
            }
            
            LOG.debug("LogWriterThread exitting.");
            
        }
    }

}@

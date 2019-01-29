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
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3QuoteLogWriter�N���X(WEB3QuoteLogWriter.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/05/18 �R�c�@@��i(FLJ) �V�K�쐬
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
 * ��M�������������t�@@�C���ɏo�͂���N���X�B<BR>
 * <BR>
 * �t�@@�C���ɏo�͂��鎞����������L���[�Ɉꎞ�I�ɂ��߂邱�Ƃɂ��A
 * �񓯊��Ń��O�t�@@�C���ɏo�͂��邱�Ƃ��ł���B<BR>
 * <BR>
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
class WEB3QuoteLogWriter
{

    /** ���O�o�̓��[�e�B���e�B */
    private static final WEB3LogUtility LOG = 
        WEB3LogUtility.getInstance(WEB3QuoteLogWriter.class);

    /** �f�o�b�N�o�̓t���O */
    private static final boolean DBG = LOG.ison();
    
    /** �L���[�̏����e�� */
    private static final int INITIAL_QUEUE_SIZE = 256;
    
    /** �t�@@�C���ɏo�͂��鎞�������ꎞ�I�ɕۊǂ���L���[ */
    private final Queue queue;    

    /** �t�@@�C���ɏo�͂��鎞�����̍�Ɨp�C���X�^���X */
    private final QuoteData tmpData;
    
    /** �t�@@�C���ɏo�͂���X���b�h�̃C���X�^���X */
    private LogWriterThread logWriterThread;

    /** �v���g�R�� */
    private String protocol;

    /** �X���b�h���[�J�����t�t�H�[�}�b�g�v�[�� */
    private static final ThreadLocal DATE_FORMAT_POOL = new ThreadLocal() {
        protected Object initialValue()
        {
            return new HashMap();
        }
    };

    /** ���t�t�H�[�}�b�g�Fyyyy-MM-dd HH:mm:ss.SSS */
    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    /** �R���X�g���N�^ */
    WEB3QuoteLogWriter(String l_strProtocol)
    {
        this.queue = new Queue(INITIAL_QUEUE_SIZE);
        this.tmpData = new QuoteData();
        this.protocol = l_strProtocol;
    }

    /**
     * �t�@@�C���o�͂��J�n����B
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
     * �t�@@�C���o�͂��~����B
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
     * ���������t�@@�C���ɏo�͂���B
     * 
     * @@param headerBuf �����T�[�o����M�����������i�w�b�_�[���j
     * @@param dataBuf �����T�[�o����M�����������i�f�[�^���j
     */
    void write(byte[] headerBuf, byte[] dataBuf)
    {
        tmpData.headerBuf = headerBuf;
        tmpData.dataBuf = dataBuf;
        queue.push(tmpData);
    }
    
    /**
     * ���������o�͂���t�@@�C���̖��̂��擾����B
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
     * �t�@@�C���ɏo�͂��鎞������\���I�u�W�F�N�g
     *
     * @@author Takuji Yamada (FLJ)
     * @@version 1.0
     */
    private class QuoteData
    {
        
        /** �������̃w�b�_�[���� */
        private byte[] headerBuf;
        
        /** �������̃f�[�^���� */
        private byte[] dataBuf;

        /**
         * �R���X�g���N�^
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
         * �w�肵��������񂩂���e���R�s�[����
         * 
         * @@param source �R�s�[���������
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
         * ���̎�����񂪊܂ގ������R�[�h�̌������擾����B
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
         * ���̎������̃w�b�_�[�����𕶎���Ŏ擾����B
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
         * ���̎�����񂪊܂ގ������R�[�h�𕶎���Ŏ擾����B
         * 
         * @@param index �擾���鎞�����R�[�h�̃C���f�b�N�X
         */
        String getData(int index)
        {
            int offset = (index - 1) * WEB3QuoteConstants.RECORD_SIZE;
            return WEB3QuoteUtil.toString(dataBuf, offset,
                    WEB3QuoteConstants.RECORD_SIZE - 1, false);
        }
        
    }
    
    /**
     * �t�@@�C���ɏo�͂��鎞�������ꎞ�I�ɂ��߂Ă����L���[
     *
     * @@author Takuji Yamada (FLJ)
     * @@version 1.0
     */
    private class Queue
    {
        
        private final Object lock = new Object();
        
        /** �������̔z�� */
        private QuoteData[] queue;
        
        /** POP����Ƃ��̃C���f�b�N�X */
        private int head;
        
        /** PUSH����Ƃ��̃C���f�b�N�X */
        private int tail;
        
        /** ���� */
        private int last;
        
        /**
         * �R���X�g���N�^
         * 
         * @@param size �L���[�̏����e��
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
         * �L���[�̍Ō�Ɏ�������ǉ�����B
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
         * �L���[�̐擪���玞�������擾���A�擪�̎��������폜����B
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
         * �L���[�̗e�ʂ��g������
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
     * �L���[�Ƀv�b�V�����ꂽ���������t�@@�C���ɏo�͂���X���b�h
     *
     * @@author Takuji Yamada (FLJ)
     * @@version 1.0
     */
    private class LogWriterThread extends Thread
    {
        
        /** �X���b�h���������̊ԁutrue�v��ݒ肷��t���O */
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

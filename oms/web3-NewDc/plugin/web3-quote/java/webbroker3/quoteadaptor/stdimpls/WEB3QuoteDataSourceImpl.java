head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.41.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteDataSourceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (株)大和総研 証券ソリューションシステム第二部
 * File Name        : QuoteDataSourceの実装クラス(WEB3QuoteDataSourceImpl.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2004/02/04 山田　@卓司(FLJ) 新規作成
 *                  : 2005/05/17 山田　@卓司(FLJ) ハートビート処理を追加
 *                  : 2005/05/17 山田　@卓司(FLJ) 接続ステータス変更処理を移動
 *                  : 2005/05/24 山田　@卓司(FLJ) 受信データのファ@イル出力を非同期で行うように変更 
 *                  : 2005/05/24 山田　@卓司(FLJ) ローカルポート番号「0」の場合は、空いているポートを使用するように変更
 *                  : 2005/05/24 山田　@卓司(FLJ) 監視サービスの開始・停止処理を追加
 *                  : 2009/04/23 齋藤　@栄三(FLJ) 時価システムQUICKへの移行
 */
package webbroker3.quoteadaptor.stdimpls;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;

import webbroker3.quoteadaptor.WEB3QuoteDataSource;
import webbroker3.quoteadaptor.WEB3QuoteEventHandler;
import webbroker3.util.WEB3LogUtility;

/**
 * QuoteDataSourceの実装クラス。
 * 時価サーバに接続して時価情報を受信し、
 * 登録されたQuoteEventHandlerに渡す。
 *
 * @@author Yoshihara Tadafumi
 * @@version 1.0
 */
class WEB3QuoteDataSourceImpl implements WEB3QuoteDataSource
{

    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QuoteDataSourceImpl.class);

    private static final boolean DBG = log.ison();
    
    private static final Object staticLock = new Object();

    private static int updateThreadSeqNo = 1;
    private static int receiveThreadSeqNo = 1;
    private static int heartbeatThreadSeqNo = 1;

    private final byte[] heartbeatBuf = new byte[WEB3QuoteConstants.HEARTBEAT_SIZE];
    private final byte[] headerBuf = new byte[WEB3QuoteConstants.HEADER_SIZE];
    private final byte[] dataBuf = new byte[WEB3QuoteConstants.MAX_DATA_SIZE];
    
    private String serviceId;
    private Socket socket;
    private InputStream inputStream;
    private BufferedOutputStream outputStream;
    private String serverAddress;
    private int serverPort;
    private String localAddress;
    private int localPort;
    
    private byte[] heartbeatMsg;

    /**
     * The total number of successfully received records. 
     */
    private int totalRecords; // = 0

    private final WEB3MessageQueue queue =
        new WEB3MessageQueue(
            WEB3QuotePropertyManager.getProperty(
                WEB3QuotePlugin.QUEUE_SIZE_PREF_NAME,
                WEB3QuoteConstants.QUEUE_SIZE));

    private final WEB3Message tmpMessage = new WEB3Message(0, null);

    private final boolean outputFileRequired;
    
    private WEB3QuoteLogWriter logWriter;
    
    private ReceiveThread receiveThread;
    private UpdateThread updateThread;
    private HeartbeatThread heartbeatThread;

    private WEB3QuoteEventHandler handler;

    private final Object instanceLock = new Object();
    
    /**
     * 指定された接続情報でSeikoQuoteDataSourceを生成する。
     *
     * @@param id サービスID。
     * @@param serverAddress 接続先IPアドレス。
     * @@param serverPort 接続先ポート
     * @@param localAddress 接続元IPアドレス。
     * @@param localPort 接続元ポート。
     */
    WEB3QuoteDataSourceImpl(
        String id,
        String serverAddress,
        int serverPort,
        String localAddress,
        int localPort)
    {

        if (serverAddress == null || localAddress == null)
        {
            throw new NullPointerException();
        }

        this.serviceId = id;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.localAddress = localAddress;
        this.localPort = localPort;
        this.heartbeatMsg = createHeartbeatMsg(localAddress);
        
        this.outputFileRequired = 
            WEB3QuotePropertyManager.getProperty(
                WEB3QuotePlugin.OUTPUT_FILE_REQUIRED_PREF_NAME,
                false);
        
        if (DBG)
        {
            log.debug("QuoteDataSource initialized."
                    + " ServiceId=" + serviceId
                    + ", ServerAddress=" + serverAddress
                    + ", ServerPort=" + serverPort
                    + ", LocalAddress=" + localAddress
                    + ", LocalPort=" + localPort);
        }
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataSource#getServiceId()
     */
    public String getServiceId()
    {
        return this.serviceId;
    }
    
    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataSource#isStarted()
     */
    public boolean isStarted()
    {
        synchronized (instanceLock)
        {
            boolean isStarted = (updateThread != null || receiveThread != null);
            return isStarted;
        }
    }
    
	/**
	 * @@see webbroker3.quoteadaptor.WEB3QuoteDataSource#getFeeders()
	 */
	public List getFeeders()
	{
		throw new RuntimeException("No implementation.") ;
	}
	
    /**
     * 時価情報通知イベントのハンドラを登録する。
     *
     * @@param h 時価情報通知イベントのハンドラ。
     */
    public void registerHandler(WEB3QuoteEventHandler h)
    {
        this.handler = h;
    }

    /**
     * サーバからの時価情報受信を開始する。
     */
    public void start()
    {
        synchronized (instanceLock)
        {
            
            if (outputFileRequired)
            {
                logWriter = new WEB3QuoteLogWriter(WEB3QuoteConstants.QUOTE_PROTOCOL_TCP);
                logWriter.start();
                log.info("WEB3QuoteLogwWriter started.");
            }
            
            
            if (updateThread != null || receiveThread != null)
            {
                throw new IllegalStateException("stop() should be called before calling start().");
            }
            updateThread = new UpdateThread();

            updateThread.setName("UpdateThread-" + getSeqNo(updateThread));
            updateThread.setDaemon(true);
            updateThread.setPriority(
                Thread.NORM_PRIORITY 
                    + WEB3QuotePropertyManager.getProperty(
                        WEB3QuotePlugin.UPDATER_PRIORITY_PREF_NAME,
                        WEB3QuoteConstants.UPDATER_PRIORITY));

            if (DBG)
            {
                log.debug("UpdateThread created !!! [priority=" 
                        + updateThread.getPriority() + "]");
            }

            queue.notifyStart();
            updateThread.start();
            
            receiveThread = new ReceiveThread();

            receiveThread.setName("ReceiveThread-" + getSeqNo(receiveThread));
            receiveThread.setDaemon(true);
            receiveThread.setPriority(
                Thread.NORM_PRIORITY
                    + WEB3QuotePropertyManager.getProperty(
                        WEB3QuotePlugin.RECEIVER_PRIORITY_PREF_NAME,
                        WEB3QuoteConstants.RECEIVER_PRIORITY));

            receiveThread.setRetryIntervalNormal(
                WEB3QuotePropertyManager.getProperty(
                    WEB3QuotePlugin.RETRY_INTERVAL_NORMAL_PREF_NAME,
                    WEB3QuoteConstants.RETRY_INTERVAL_NORMAL));

            receiveThread.setRetryThreshhold(
                WEB3QuotePropertyManager.getProperty(
                    WEB3QuotePlugin.RETRY_THRESHOLD_PREF_NAME,
                    WEB3QuoteConstants.RETRY_THRESHHOLD));

            receiveThread.setRetryIntervalEconomy(
                WEB3QuotePropertyManager.getProperty(
                    WEB3QuotePlugin.RETRY_INTERVAL_ECONOMY_PREF_NAME,
                    WEB3QuoteConstants.RETRY_INTERVAL_ECONOMY));

            if (DBG)
            {
                log.debug("ReceiveThread created !!! [priority=" 
                        + receiveThread.getPriority() + "]");
            }

            receiveThread.start();
        }
    }

    /**
     * サーバからの時価情報の受信を停止する。
     *
     */
    public void stop()
    {
        synchronized (instanceLock)
        {
            if (receiveThread != null)
            {
                receiveThread.isActive = false;
                // close the socket forcefully
                close();

                try
                {
                    receiveThread.interrupt();
                    receiveThread.join();
                } catch (InterruptedException ie)
                {
                    log.warn(
                        "Excepttion occured while waiting for receiveThread to finish.");
                }
                receiveThread = null;
            }

            if (updateThread != null)
            {
                // notify the queue of finish before
                // stopping pop from it
                queue.notifyFinish();
                try
                {
                    updateThread.join();
                } catch (InterruptedException ie)
                {
                    log.warn(
                        "Exception occured while waiting for updateThread to finish.");
                }

                updateThread = null;

            }
            
            if (logWriter != null)
            {
                logWriter.stop();
                log.info("WEB3QuoteLogWriter stopped.");
            }
        }
    }
    
    /**
     * 時価サーバへの接続を行う。
     * 
     * @@throws IOException
     */
    private void connect() throws IOException
    {
        if (DBG)
        {
            log.debug("Connecting to " + serverAddress + ":" + serverPort);
        }
        
        int timeout = 
            WEB3QuotePropertyManager.getProperty(
                WEB3QuotePlugin.HEARTBEAT_TIMEOUT,
                WEB3QuoteConstants.HEARTBEAT_TIMEOUT);
        
        if (localPort != 0)
        {
            socket =
                new Socket(
                    InetAddress.getByName(serverAddress),
                    serverPort,
                    InetAddress.getByName(localAddress),
                    localPort);
        } else
        {
            socket =
                new Socket(
                    InetAddress.getByName(serverAddress),
                    serverPort);
        }
        socket.setSoTimeout(timeout);

        log.info("Connection established: "
                + serverAddress + ":" + serverPort
                + "[LOCAL_PORT=" + socket.getLocalPort()
                + ",SO_TIMEOUT=" + socket.getSoTimeout()
                + "]");

        outputStream = new BufferedOutputStream(socket.getOutputStream());
        inputStream = new BufferedInputStream(socket.getInputStream());
        
        afterConnect();
        
    }

    /**
     * 時価サーバへの接続を切断する。
     *
     */
    void close()
    {
        if (DBG)
        {
            log.debug(
                "Closing the connection to "
                    + serverAddress
                    + ": "
                    + serverPort);
        }
        
        beforeClose();
        
        if (outputStream != null)
        {
            try
            {
                outputStream.flush();
                outputStream.close();
                log.info("Output stream closed: " + serverAddress + ": " + serverPort);
            } catch (IOException ioe)
            {
                log.warn("Error while closing output stream.");
            } finally
            {
                outputStream = null;
            }
             
        }
        
        if (inputStream != null)
        {
            try
            {
                inputStream.close();
                log.info("Input stream closed: " + serverAddress + ": " + serverPort);
            } catch (IOException ioe)
            {
                log.warn("Error while closing input stream.");
            } finally
            {
                inputStream = null;
            }
        }

        if (socket != null)
        {
            try
            {
                socket.close();
                log.info(
                    "Connection closed: " + serverAddress + ": " + serverPort);
                if (DBG)
                {
                    log.debug("totalRecords = " + totalRecords);
                }
            } catch (IOException ioe)
            {
                log.warn("Error while closing the connection.", ioe);
            } finally
            {
                socket = null;
            }
        }
        
    }
    
    /**
     * 時価サーバーに接続されているソケットからデータを取得する。
     */
    private boolean receiveData() throws SocketException, IOException
    {
        
        int count = 0;
        int received;
        while (count < WEB3QuoteConstants.HEARTBEAT_SIZE)
        {
            if ((received =
                inputStream.read(
                    heartbeatBuf,
                    count,
                    WEB3QuoteConstants.HEARTBEAT_SIZE - count))
                < 0)
            {
                return false;
            }
            count += received;
        }
        
        if (DBG)
        {
            String header = 
                WEB3QuoteUtil.toString(
                    heartbeatBuf, 
                    0, 
                    WEB3QuoteConstants.HEARTBEAT_SIZE);
            log.debug("msg header=" + header);
        }
      
        // ハートビートの場合はここで終了
        if (isHeartbeatMessage())
        {
            if (DBG)
            {
                log.debug("Heartbeat succeeded.");
            }
            return true;
        } else
        {
            count = 
                WEB3QuoteConstants.HEARTBEAT_SIZE 
                    - WEB3QuoteConstants.HEADER_SIZE;
            System.arraycopy(
                heartbeatBuf, 0, 
                headerBuf, 0, 
                WEB3QuoteConstants.HEADER_SIZE);
            System.arraycopy(
                heartbeatBuf, WEB3QuoteConstants.HEADER_SIZE, 
                dataBuf, 0,
                count);
        }
        
        // ヘッダーからレコード数を読み込む
        int numRecords = 0;
        String numRecordsStr = null;
        try
        {
            numRecordsStr =
                new String(
                    headerBuf,
                    WEB3QuoteConstants.SEQUENCE_NO_SIZE,
                    WEB3QuoteConstants.NUM_OF_RECORDS_SIZE,
                    WEB3QuoteConstants.ENCODING).trim();

            numRecords = Integer.parseInt(numRecordsStr);

            if (DBG)
            {
                log.debug("numRecords = " + numRecords);
            }

        } catch (UnsupportedEncodingException uee)
        {
            String errorMsg = "Illegal message";
            throw new RuntimeSystemException(errorMsg, uee);
        } catch (NumberFormatException nfe)
        {
            String errorMsg =
                "Illegal format: numRecordsStr = "
                    + numRecordsStr
                    + "[10]"
                    + Integer.toHexString(headerBuf[10] & 0xFF)
                    + "[11]"
                    + Integer.toHexString(headerBuf[11] & 0xFF);

            throw new RuntimeSystemException(errorMsg, nfe);

        }

        //
        // データ部分を読み込む
        //
        final int dataSize = numRecords * WEB3QuoteConstants.RECORD_SIZE;
        while (count < dataSize)
        {
            if ((received = inputStream.read(dataBuf, count, dataSize - count))
                < 0)
            {
                return false;
            }

            count += received;
            
        }
        
        // update the counter for total records
        totalRecords += numRecords;
        
        // 受信した時価情報をログファ@イルに出力
        writeLog();

        // Messageオブジェクトに値をセットする
        tmpMessage.count = numRecords;
        tmpMessage.data = dataBuf;
        
        // QueueにMessageをプッシュする
        if (count > 0)
        {
            if (DBG)
            {
                log.debug(new String(dataBuf, 0, count));
            }
            queue.push(tmpMessage);
        }

        return true;
    }
    
    private boolean isHeartbeatMessage()
    {
        String received = WEB3QuoteUtil.toString(heartbeatBuf, 0, 8);
        String l_localAddress = WEB3QuotePlugin.getLocalHostNameByFixedLen();
        return l_localAddress.equals(received);
    }
    
    /**
     * ハートビートを実行スレッドを生成し、
     * 時価サーバにハートビートメッセージを送信する。
     */
    private void startHeartbeat()
    {
        
        if (heartbeatThread != null)
        {
            throw new IllegalStateException("HeartbeatThread has already started.");
        }

        int priority = Thread.NORM_PRIORITY
                + WEB3QuotePropertyManager.getProperty(
                        WEB3QuotePlugin.HEARTBEAT_PRIORITY_PREF_NAME,
                        WEB3QuoteConstants.HEARTBEAT_PRIORITY);
        long heartbeatInterval = 
            WEB3QuotePropertyManager.getProperty(
                WEB3QuotePlugin.HEARTBEAT_INTERVAL,
                WEB3QuoteConstants.HEARTBEAT_INTERVAL);
        heartbeatThread = new HeartbeatThread();
        heartbeatThread.setName("HeartbeatThread-" + getSeqNo(heartbeatThread));
        heartbeatThread.setPriority(priority);
        heartbeatThread.setDaemon(true);
        heartbeatThread.heartbeatInterval = heartbeatInterval;
        heartbeatThread.start();
        
        if (DBG)
        {
            log.debug("HeartbeatThread created !!! [priority=" 
                    + heartbeatThread.getPriority() + "]");
        }
        
    }
    
    /**
     * ハートビートを実行するスレッドを停止する。
     */
    private void stopHeartbeat()
    {
        if (heartbeatThread != null)
        {

            heartbeatThread.isActive = false;
            try
            {
                heartbeatThread.interrupt();
                heartbeatThread.join();
            } catch (InterruptedException ie)
            {
                log.warn("Exception occured while waiting for receiveThread to finish.");
            } finally
            {
                heartbeatThread = null;
            }
            
        }
    }
    
    /**
     * 時価サーバに送信するハートビートメッセージを生成する。
     */
    private byte[] createHeartbeatMsg(String localAddress)
    {
        StringBuffer sb = new StringBuffer();
        WEB3QuoteUtil.append(sb, localAddress, 8, true);
        WEB3QuoteUtil.append(sb, null, 8, true);
        sb.append("0");
        return sb.toString().getBytes();
    }
    
    /**
     * スレッド名につけるシーケンシャルな番号を取得する。
     */
    private int getSeqNo(Thread thread)
    {
        synchronized (staticLock)
        {
            int seqNo = 0;
            if (thread instanceof UpdateThread)
            {
                seqNo = updateThreadSeqNo++;
            } else if (thread instanceof ReceiveThread)
            {
                seqNo = receiveThreadSeqNo++;
            } else if (thread instanceof HeartbeatThread)
            {
                seqNo = heartbeatThreadSeqNo++;
            }
            return seqNo;
        }
    }

    /**
     * 受信した時価情報をファ@イルに出力する
     */
    private void writeLog()
    {
        if (!outputFileRequired || logWriter == null)
        {
            return;
        }
        
        logWriter.write(headerBuf, dataBuf);
        
    }
    
    /**
     * 時価サーバとの接続が確立した後に起動する処理
     */
    private void afterConnect()
    {
        
        startHeartbeat();
        
        WEB3QuoteDataSupplierServiceManager serviceMgr =
            (WEB3QuoteDataSupplierServiceManager) Services.getService(
                WEB3QuoteDataSupplierServiceManager.class);
        serviceMgr.startMonitoring();
        
        WEB3QuoteStatusManager statusManager = 
            WEB3QuoteStatusManager.getInstance();
        QuoteStatus currentStatus = statusManager.getStatus();
        if (QuoteStatus.CONNECTING.equals(currentStatus))
        {
            statusManager.modifyStatus(QuoteStatus.CONNECTED);
        }
        
    }
    
    /**
     * 時価サーバとの接続を切断する前に起動する処理
     *
     */
    private void beforeClose()
    {

        WEB3QuoteDataSupplierServiceManager serviceMgr = 
            (WEB3QuoteDataSupplierServiceManager) Services.getService(
                WEB3QuoteDataSupplierServiceManager.class);
        serviceMgr.stopMonitoring();

        stopHeartbeat();

    }

    /**
     * @@see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer(); 
        l_sb.append("WEB3QuoteDataSourceImpl[");
        l_sb.append("ServiceId=" + serviceId);
        l_sb.append(",ServerAddress=" + serverAddress);
        l_sb.append(",ServerPort=" + serverPort);
        l_sb.append(",LocalAddress=" + localAddress);
        l_sb.append(",LocalPort=" + localPort);
        l_sb.append(",isStarted=" + isStarted());
        l_sb.append("]");
        return l_sb.toString();
    }

    /**
     * 時価サーバに接続されているソケットから時価情報を取得し、 WEB3MessageQueueに取得した時価情報をプットするスレッド。
     * 
     * @@author Takuji Yamada (FLJ)
     * @@version 1.0
     */
    private class ReceiveThread extends Thread
    {

        private volatile boolean isActive = true;
        private boolean connected = false;
        private long retryIntervalNormal = 1000;
        private long retryIntervalEconomy = 1000;
        private int retryThreshhold = 10;
        private int retryCounts = 0;

        /**
         * We need to handle the connection
         * error during the process. In that case
         * we would re-connect to the server and
         * receive the missing data.
         */

        void setRetryIntervalNormal(long l)
        {
            this.retryIntervalNormal = l;
        }

        void setRetryIntervalEconomy(long l)
        {
            this.retryIntervalEconomy = l;
        }

        void setRetryThreshhold(int i)
        {
            this.retryThreshhold = i;
        }

        private long getSleepingTime(int counts)
        {
            if (counts < retryThreshhold)
            {
                return retryIntervalNormal;
            } else
            {
                return retryIntervalEconomy;
            }
        }
        
        private boolean initialConnect()
        {
            
            // 既に接続済みの場合は何もしない。
            if (connected)
            {
                return connected;
            }
            
            // 接続開始
            if (DBG)
            {
                log.debug(
                    "receiveThread: Connection is not established, trying to connect to the server. ");
            }

            try
            {
                close();
                connect();
                connected = true;
                retryCounts = 0;
            } catch (IOException ioe)
            {
                // 接続できなかった場合
                log.warn(
                    "receiveThread: Cannnot conenct to  "
                        + serverAddress
                        + ":"
                        + serverPort
                        + " "
                        + ioe.getMessage());
                        
                try
                {
                    log.info(
                        "receiveThread: Waiting for a while before trying again. retryCounts = "
                            + retryCounts);
                    Thread.sleep(getSleepingTime(retryCounts));
                    retryCounts++;
                } catch (InterruptedException e)
                {
                    log.warn(
                        "Excepttion occured while waiting for a while before retrying connection.");
                }
            }
            
            return connected;
            
        }
        
        public void run()
        {
            try
            {
                // 
                // Continuously receive data
                //
                while (isActive)
                {
                    
                    if (!initialConnect())
                    {
                        continue;
                    }
                    
                    try
                    {
                        if (!receiveData())
                        {
                            log.error("receiveThread: Connection has been closed by the server.");
                            connected = false;
                        }
                    } catch (IOException ioe)
                    {
                        if (isActive)
                        {
                            log.error(
                                "receiveThread: Connection error occured while receiving data.",
                                ioe);
                        } else
                        {
                            log.info("receiveThread: Connection closed by another thread.");
                        }
                        connected = false;
                    }
                } //while(!isActive)
            } catch (Throwable t)
            {
                log.error("receiveThread: Unexpected error occured.", t);
            } finally
            {
                log.debug(
                    "recieveThread: Closing connection before exitting..");
                close();
            }
            if (DBG)
            {
                log.debug("receiveThread: Exitting..");
            }
        }
    }

    /**
     * WEB3MessageQueueにプールされている時価情報を取得し、
     * 時価情報通知イベントハンドラに取得した時価情報を通知するスレッド。
     *
     * @@author Takuji Yamada (FLJ)
     * @@version 1.0
     */
    private class UpdateThread extends Thread
    {

        public void run()
        {
            try
            {
                final WEB3Message tmpMessage = new WEB3Message();

                WEB3QuoteEventImpl[] records =
                    new WEB3QuoteEventImpl[WEB3QuoteConstants.MAX_RECORDS];
                
                for (int i = 0; i < WEB3QuoteConstants.MAX_RECORDS; i++)
                {
                    records[i] = new WEB3QuoteEventImpl();
                }

                int count = 0;
                while (queue.pop(tmpMessage))
                {
                    if (DBG)
                    {
                        log.debug("updateThread: top of while loop");
                    }

                    count = tmpMessage.parse(records);

                    for (int i = 0; i < count; i++)
                    {
                        handler.push(records[i]);
                    }
                }
            } catch (Throwable t)
            {
                log.error("updateThread: Unexpected error occured.", t);
            }
        }
    }

    /**
     * 時価サーバにハートビートメッセージを送信するスレッド
     *
     * @@author Takuji Yamada (FLJ)
     * @@version 1.0
     */
    private class HeartbeatThread extends Thread
    {
        
        private volatile boolean isActive = true;
        private long heartbeatInterval;
        
        public void run()
        {
            try
            {
                while (isActive)
                {
                    
                    outputStream.write(heartbeatMsg);
                    outputStream.flush();
                        
                    log.debug("Heartbeat message sended.");
                        
                    try
                    {
                        Thread.sleep(heartbeatInterval);
                    } catch (InterruptedException ie)
                    {
                        log.debug("heartbeatThread: Exception occured while waiting for next try.");
                    }
                    
                }
            } catch (IOException ioe)
            {
                log.error("heartbeatThread: connection has been closed by the server. " + ioe.getMessage());
            } catch (Throwable th)
            {
                log.error("heartbeatThread: Unexpected exception occured.", th);
            } finally
            {
            }
            
            log.debug("heartbeatThread: Exitting..");
            
        }
        
    }
    
}
@

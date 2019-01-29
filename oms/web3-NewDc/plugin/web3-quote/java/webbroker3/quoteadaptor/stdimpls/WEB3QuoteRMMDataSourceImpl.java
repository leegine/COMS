head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.41.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteRMMDataSourceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (株)大和総研 証券ソリューションシステム第二部
 * File Name        : QuoteDataSourceのRMM実装クラス(WEB3QuoteRMMDataSourceImpl.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2009/04/23 齋藤　@栄三(FLJ) 新規作成（時価システムQUICKへの移行）
 */
package webbroker3.quoteadaptor.stdimpls;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;

import webbroker3.feed.comm.RMMReceiverConfig;
import webbroker3.feed.comm.RMMRecevierCallBack;
import webbroker3.feed.comm.SingleRMMReceiver;
import webbroker3.feed.jmx.WEB3FeedJMXHttpClient;
import webbroker3.quoteadaptor.WEB3QuoteDataSource;
import webbroker3.quoteadaptor.WEB3QuoteEventHandler;
import webbroker3.util.WEB3LogUtility;

/**
 * QuoteDataSourceのRMM実装クラス。
 * 時価サーバから配信される時価情報を
 * 登録されたQuoteEventHandlerに渡す。
 *
 * @@author 齋藤(FLJ)
 * @@version 1.0
 */
class WEB3QuoteRMMDataSourceImpl implements WEB3QuoteDataSource, WEB3QuoteRMMRcvdProcessor
{

    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QuoteRMMDataSourceImpl.class);

    private static final boolean DBG = log.ison();
    
    private static final Object staticLock = new Object();

    private static int updateThreadSeqNo = 1;
    private static int initialRequestThreadSeqNo = 1;

    private final byte[] headerBuf = new byte[WEB3QuoteConstants.RMM_HEADER_SIZE];
    private final byte[] dataBuf = new byte[WEB3QuoteConstants.MAX_DATA_SIZE];
    
    private String serviceId;

    /**
     * The total number of successfully received records. 
     */
    private int totalRecords; // = 0

    private final WEB3MessageQueue queue =
        new WEB3MessageQueue(
            WEB3QuotePropertyManager.getProperty(
                WEB3QuotePlugin.RMM_QUEUE_SIZE_PREF_NAME,
                WEB3QuoteConstants.RMM_QUEUE_SIZE));

    private final WEB3Message tmpMessage = new WEB3Message(0, null);

    private final boolean outputFileRequired;
    
    private WEB3QuoteLogWriter logWriter;
    
    private SingleRMMReceiver receiveThread;
    private UpdateThread updateThread;
    private InitialRequestThread initialRequestThread;

    private WEB3QuoteEventHandler handler;

    private final Object instanceLock = new Object();

    private final String dsId;
    
    private List feeders = new ArrayList();

    /**
     * 指定された接続情報でRMMQuoteDataSourceを生成する。
     *
     * @@param id サービスID。
     * @@param serverAddress 接続先IPアドレス。
     * @@param serverPort 接続先ポート
     * @@param localAddress 接続元IPアドレス。
     * @@param localPort 接続元ポート。
     */
    WEB3QuoteRMMDataSourceImpl(
        String id,
        String dsId)
    {

        this.serviceId = id;
        this.dsId = dsId;
        
        this.outputFileRequired = 
            WEB3QuotePropertyManager.getProperty(
                WEB3QuotePlugin.OUTPUT_FILE_REQUIRED_PREF_NAME,
                false);
        
        if (DBG)
        {
            log.debug("WEB3QuoteRMMDataSourceImpl initialized."
                    + " ServiceId=" + serviceId
                    + " DataSourceID=" + dsId);
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
    	return Collections.unmodifiableList(feeders);
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
                logWriter = new WEB3QuoteLogWriter(WEB3QuoteConstants.QUOTE_PROTOCOL_RMM);
                logWriter.start();
                log.info("WEB3QuoteLogWriter started.");
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
                        WEB3QuotePlugin.RMM_UPDATER_PRIORITY_PREF_NAME,
                        WEB3QuoteConstants.RMM_UPDATER_PRIORITY));

            if (DBG)
            {
                log.debug("UpdateThread created !!! [priority=" 
                        + updateThread.getPriority() + "]");
            }

            queue.notifyStart();
            updateThread.start();
            
            setupRMM();
        }
    }

    /**
     * RMM関連のセットアップをする
     */
    protected void setupRMM()
    {
        String l_strLocalHostName = WEB3QuotePlugin.getLocalAddress();
        
        RMMReceiverConfig l_config = new RMMReceiverConfig();

        //config
        Properties l_configParams = getProperties(WEB3QuotePlugin.RMM_RCVD_CONFIG_PREFIX + "." + dsId + "." + l_strLocalHostName + ".");
        l_config.setConfigProperties(l_configParams);

        //ancillary
        Properties l_ancillaryParams = getProperties(WEB3QuotePlugin.RMM_RCVD_ANCILLARY_PREFIX + "." + dsId + "." + l_strLocalHostName + ".");
        l_config.setAncillaryParams(l_ancillaryParams);

        //トレースフラグ
        boolean l_blnTraceEnable = WEB3QuotePropertyManager.getProperty(
            WEB3QuotePlugin.RMM_RCVD_TRACE + "." + dsId + "." + l_strLocalHostName, WEB3QuoteConstants.RMM_RCVD_TRACE);
        l_config.setTraceEnabled(l_blnTraceEnable);

        //デスティネーション
        String l_strDestination = WEB3QuotePropertyManager.getProperty(
            WEB3QuotePlugin.RMM_DS_DESTINATION_PREF_NAME + "." + dsId);
        l_config.setMulticastGroup(l_strDestination);

        //トピック名
        String l_strTopicName = WEB3QuotePropertyManager.getProperty(
            WEB3QuotePlugin.RMM_DS_TOPIC_NAME_PREF_NAME + "." + dsId);
        l_config.setTopic(l_strTopicName);

        RMMRecevierCallBack l_callback = new WEB3QuoteRMMRcvdCallbackImpl(this);
        //callback
        l_config.setCallback(l_callback);

        try
        {
            receiveThread = new SingleRMMReceiver(l_config);
            
            log.info("SingleRMMReceiver created.");
        }
        catch (Exception e)
        {
            log.error("SingleRMMReceiver Error:" + e.getMessage(), e);
        }


        //時価Feeder
        String l_strFeederList = WEB3QuotePropertyManager.getProperty(
            WEB3QuotePlugin.RMM_DS_FEEDER_LIST_PREF_NAME + "." + dsId);
        
        //時価Feederポート
        String l_strPortList = WEB3QuotePropertyManager.getProperty(
            WEB3QuotePlugin.RMM_DS_FEEDER_MSG_PORT_LIST_PREF_NAME + "." + dsId);
        
        String[] l_strFeeders = l_strFeederList.split(WEB3QuoteConstants.DELIMITER);
        String[] l_strPorts = l_strPortList.split(WEB3QuoteConstants.DELIMITER);
        for(int j=0;j<l_strFeeders.length;j++)
        {
            WEB3QuoteFeeder l_feeder = new WEB3QuoteFeeder();
            l_feeder.setHost(l_strFeeders[j]);
            l_feeder.setPort(new Integer(l_strPorts[j]).intValue());
            feeders.add(l_feeder);
        }

        //配信元を取得してる場合
        if(feeders.size() > 0)
        {
            // 接続を再試行する間隔
            long l_lngRetryInterval = WEB3QuotePropertyManager.getProperty(
                WEB3QuotePlugin.RMM_INIT_RETRY_INTERVAL_PREF_NAME,
                WEB3QuoteConstants.RMM_INIT_RETRY_INTERVAL);

            //初期化要求スレッド
            initialRequestThread = new InitialRequestThread();
            initialRequestThread.setName("InitialRequestThread-" + getSeqNo(initialRequestThread));
            initialRequestThread.setDaemon(true);
            initialRequestThread.setRetryInterval(l_lngRetryInterval);
            initialRequestThread.start();
            
            log.info("InitialRequestThread started. "
                + "[priority=" + initialRequestThread.getPriority()
                + ",retryIntervalNormal=" + l_lngRetryInterval +"]");
        }
        else
        {
            log.error("no feeder found. feeder=" + feeders);
        }
    }

    /**
     * 引数の接頭辞をキーにRMMオブジェクトが必要とするプロパティを返す
     */
    protected Properties getProperties(String l_strConfigPrefix)
    {
        Properties l_props = new Properties();

        //引数の文字列を接頭辞とするプロパティMapを取得
        Map l_map = WEB3QuotePropertyManager.getProperties(l_strConfigPrefix);
        
        Iterator l_iter = l_map.keySet().iterator();
        
        while(l_iter.hasNext())
        {
            String l_strKey = (String)l_iter.next();
            String l_value = (String)l_map.get(l_strKey);
            
            //接頭辞以降の文字列をキーとする
            String l_strConfigKey = l_strKey.substring(l_strConfigPrefix.length());
            l_props.setProperty(l_strConfigKey, l_value);
        }
        return l_props;
    }
    
    /**
     * サーバからの時価情報の受信を停止する。
     *
     */
    public void stop()
    {
        synchronized (instanceLock)
        {
            //初期化要求スレッドを停止する。
            if(initialRequestThread != null)
            {
                initialRequestThread.isActive = false;
                try
                {
                    initialRequestThread.interrupt();
                    initialRequestThread.join();
                    log.info("InitialRequestThread stopped.");
                } catch (InterruptedException ie)
                {
                    log.warn(
                        "Exception occured while waiting for initialRequestThread to finish.");
                }
                initialRequestThread = null;
            }

            if (receiveThread != null)
            {
                beforeClose();
                log.info("RMMReceiver is stopping.");
                receiveThread.stop();
                log.info("RMMReceiver stopped.");
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
                    log.info("UpdateThread stopped.");
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

            feeders.clear();
            log.info("WEB3QuoteFeeder List cleared.");
            
            if (DBG)
            {
                log.debug("totalRecords = " + totalRecords);
            }
        }
    }
    
    /**
     * 時価サーバーに接続されているソケットからデータを取得する。
     */
    public void receiveData(byte[] l_data)
    {
        
        // 読み込んだメッセージを時価メッセージ用バッファ@にコピー
        System.arraycopy(
            l_data, 0, headerBuf, 0,
            WEB3QuoteConstants.RMM_HEADER_SIZE);
        System.arraycopy(
            l_data, WEB3QuoteConstants.RMM_HEADER_SIZE,
            dataBuf, 0, l_data.length - WEB3QuoteConstants.RMM_HEADER_SIZE);

        
        // レコード数を取得
        int numRecords = 0;
        String numRecordsStr = null;
        try
        {
            numRecordsStr =
                new String(
                    headerBuf,
                    WEB3QuoteConstants.FROM_MACHINE_NAME_SIZE + WEB3QuoteConstants.TO_MACHINE_NAME_SIZE + WEB3QuoteConstants.SEQUENCE_NO_SIZE,
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
            // フォーマットエラーの場合
            String errorMsg = 
                "num_of_record field must be a number." 
                + "[26]" + Integer.toHexString(headerBuf[26] & 0xFF)
                + "[27]"+ Integer.toHexString(headerBuf[27] & 0xFF);
            throw new RuntimeException(errorMsg, nfe);
        }
        
        totalRecords += numRecords;
        
        // 受信した時価情報をログファ@イルに出力
        writeLog();
        
        //切捨て可能かチェック
        if(isDiscardable())
        {
            return;
        }
        
        // Messageオブジェクトに値をセットする
        tmpMessage.count = numRecords;
        tmpMessage.data = dataBuf;
        
        if (DBG)
        {
            log.debug("data=" + WEB3QuoteUtil.toString(dataBuf, 0, dataBuf.length));
        }
        
        // QueueにMessageをプッシュする
        queue.push(tmpMessage);
    }
    
    /**
     * (is切捨可能)<BR>
     * <BR>
     * 時価データソースから通知された時価情報が
     * AP上で処理する必要があるか判定する。<BR>
     * 
     * @@param l_fromHost Fromマシン名
     * @@param l_toHost Toマシン名
     * @@return 指定した時価情報を切り捨て可能な場合に<code>true</code>、
     *         それ以外の場合は<code>false</code>を返す。
     */
    private boolean isDiscardable()
    {

        //TOマシン名を取得
        String toMachineName = 
            WEB3QuoteUtil.toString(
                headerBuf, 
                WEB3QuoteConstants.FROM_MACHINE_NAME_SIZE, 
                WEB3QuoteConstants.TO_MACHINE_NAME_SIZE);

        if (DBG)
        {
            log.debug("toMachineName=" + toMachineName);
        }
   
        //宛先が他サーバの場合、切捨可能
        if(toMachineName != null && !toMachineName.equals(WEB3QuotePlugin.getLocalHostNameByFixedLen()))
        {
            if (DBG)
            {
                log.debug("QuoteData discarded because target is other machine." 
                        + " to=[" + toMachineName + "]");
            }
            return true;
        }
        
        return false;
        
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
            }
            else if (thread instanceof InitialRequestThread)
            {
                seqNo = initialRequestThreadSeqNo++;
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
        
        log.info("Connection established to someone. ServiceId=" + serviceId + ", DataSourceID=" + dsId + ", feeders=" + feeders);
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

    }

    /**
     * @@see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer(); 
        l_sb.append("WEB3QuoteRMMDataSourceImpl[");
        l_sb.append("ServiceId=" + serviceId);
        l_sb.append(",DataSourceID=" + dsId);
        l_sb.append(",isStarted=" + isStarted());
        l_sb.append("]");
        return l_sb.toString();
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
     * (要求スレッド)<BR>
     * <BR>
     * 時価サーバに要求を出すスレッド。<BR>
     *
     * @@author Eizo Saito (FLJ)
     * @@version 1.0
     */
    private class InitialRequestThread extends Thread
    {
        boolean isActive = true;
        
        long retryInterval = WEB3QuoteConstants.RMM_INIT_RETRY_INTERVAL;
        
        void setRetryInterval(long l_retryInterval)
        {
            retryInterval = l_retryInterval;
        }

        /**
         * 時価初期化要求をする。<BR>
         * 
         * @@see java.lang.Runnable#run()
         */
        public void run()
        {
            try
            {
            	int l_intCount = 0;
            	
                while(isActive)
                {
                    int l_intError = 0;

                    //疎通確認もかねて全てに初期化要求をする
                    for(int i=0;i<feeders.size();i++)
                    {
                        // 時価サーバ
                        WEB3QuoteFeeder l_feeder = (WEB3QuoteFeeder)feeders.get(i);
                        
                        // サーバアドレス
                        String l_serverAddress = l_feeder.getHost();

                        // サーバポート
                        int l_serverPort = l_feeder.getPort();

                        try
                        {
                            log.info("Initialize Request trying to " + l_serverAddress + ":" + l_serverPort);

                            //JMXで要求
                            WEB3FeedJMXHttpClient.getSnapshot(WEB3QuotePlugin.getLocalHostNameByFixedLen(), l_serverAddress, l_serverPort);
                            
                            log.info("Initialize Request succeeded to " + l_serverAddress + ":" + l_serverPort);
                        }
                        catch (Exception e)
                        {
                            l_intError++;
                            log.error("Initialize Request failed to " + l_serverAddress + ":" + l_serverPort + " " + e.getMessage(), e);
                        }
                    }

                    if(l_intError < feeders.size())
                    {
                        afterConnect();
                        break;
                    }

                    //閾値以上の場合、データソースを切り替える
                    l_intCount++;
                    if(l_intCount >= WEB3QuotePropertyManager.
                    					getProperty(WEB3QuotePlugin.RMM_DS_CHANGE_INIT_THRESHOLD_PREF_NAME,
                    								WEB3QuoteConstants.RMM_DS_CHANGE_INIT_THRESHOLD))
                    {
                        log.warn("Quote feeder regarded as dead status. Because Initialize Request loop counter reached threshold. counter=" + l_intCount);

                        WEB3QuoteDataSupplierServiceManager l_serviceManager = 
			                (WEB3QuoteDataSupplierServiceManager) Services.getService(WEB3QuoteDataSupplierServiceManager.class);

			            l_serviceManager.changeRMMDataSource();
                    	
                    	break;
                    }

                    //応答性を上げるため、スリープ前にチェック
                    if(!isActive)
                    {
                        break;
                    }

                    try
                    {
                        Thread.sleep(retryInterval);
                    }
                    catch (InterruptedException ie)
                    {
                        log.debug("InitialRequestThread: Exception occured while waiting for next try.");
                    }
                }
            }
            catch (Throwable th)
            {
                log.error(getName() + " : Unexpected error occured.", th);
            }
        }
    }
}
@

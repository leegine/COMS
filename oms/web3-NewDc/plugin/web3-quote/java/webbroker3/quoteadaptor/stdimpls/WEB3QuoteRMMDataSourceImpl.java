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
 * Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 * File Name        : QuoteDataSource��RMM�����N���X(WEB3QuoteRMMDataSourceImpl.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2009/04/23 �V���@@�h�O(FLJ) �V�K�쐬�i�����V�X�e��QUICK�ւ̈ڍs�j
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
 * QuoteDataSource��RMM�����N���X�B
 * �����T�[�o����z�M����鎞������
 * �o�^���ꂽQuoteEventHandler�ɓn���B
 *
 * @@author �V��(FLJ)
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
     * �w�肳�ꂽ�ڑ�����RMMQuoteDataSource�𐶐�����B
     *
     * @@param id �T�[�r�XID�B
     * @@param serverAddress �ڑ���IP�A�h���X�B
     * @@param serverPort �ڑ���|�[�g
     * @@param localAddress �ڑ���IP�A�h���X�B
     * @@param localPort �ڑ����|�[�g�B
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
     * �������ʒm�C�x���g�̃n���h����o�^����B
     *
     * @@param h �������ʒm�C�x���g�̃n���h���B
     */
    public void registerHandler(WEB3QuoteEventHandler h)
    {
        this.handler = h;
    }

    /**
     * �T�[�o����̎�������M���J�n����B
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
     * RMM�֘A�̃Z�b�g�A�b�v������
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

        //�g���[�X�t���O
        boolean l_blnTraceEnable = WEB3QuotePropertyManager.getProperty(
            WEB3QuotePlugin.RMM_RCVD_TRACE + "." + dsId + "." + l_strLocalHostName, WEB3QuoteConstants.RMM_RCVD_TRACE);
        l_config.setTraceEnabled(l_blnTraceEnable);

        //�f�X�e�B�l�[�V����
        String l_strDestination = WEB3QuotePropertyManager.getProperty(
            WEB3QuotePlugin.RMM_DS_DESTINATION_PREF_NAME + "." + dsId);
        l_config.setMulticastGroup(l_strDestination);

        //�g�s�b�N��
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


        //����Feeder
        String l_strFeederList = WEB3QuotePropertyManager.getProperty(
            WEB3QuotePlugin.RMM_DS_FEEDER_LIST_PREF_NAME + "." + dsId);
        
        //����Feeder�|�[�g
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

        //�z�M�����擾���Ă�ꍇ
        if(feeders.size() > 0)
        {
            // �ڑ����Ď��s����Ԋu
            long l_lngRetryInterval = WEB3QuotePropertyManager.getProperty(
                WEB3QuotePlugin.RMM_INIT_RETRY_INTERVAL_PREF_NAME,
                WEB3QuoteConstants.RMM_INIT_RETRY_INTERVAL);

            //�������v���X���b�h
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
     * �����̐ړ������L�[��RMM�I�u�W�F�N�g���K�v�Ƃ���v���p�e�B��Ԃ�
     */
    protected Properties getProperties(String l_strConfigPrefix)
    {
        Properties l_props = new Properties();

        //�����̕������ړ����Ƃ���v���p�e�BMap���擾
        Map l_map = WEB3QuotePropertyManager.getProperties(l_strConfigPrefix);
        
        Iterator l_iter = l_map.keySet().iterator();
        
        while(l_iter.hasNext())
        {
            String l_strKey = (String)l_iter.next();
            String l_value = (String)l_map.get(l_strKey);
            
            //�ړ����ȍ~�̕�������L�[�Ƃ���
            String l_strConfigKey = l_strKey.substring(l_strConfigPrefix.length());
            l_props.setProperty(l_strConfigKey, l_value);
        }
        return l_props;
    }
    
    /**
     * �T�[�o����̎������̎�M���~����B
     *
     */
    public void stop()
    {
        synchronized (instanceLock)
        {
            //�������v���X���b�h���~����B
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
     * �����T�[�o�[�ɐڑ�����Ă���\�P�b�g����f�[�^���擾����B
     */
    public void receiveData(byte[] l_data)
    {
        
        // �ǂݍ��񂾃��b�Z�[�W���������b�Z�[�W�p�o�b�t�@@�ɃR�s�[
        System.arraycopy(
            l_data, 0, headerBuf, 0,
            WEB3QuoteConstants.RMM_HEADER_SIZE);
        System.arraycopy(
            l_data, WEB3QuoteConstants.RMM_HEADER_SIZE,
            dataBuf, 0, l_data.length - WEB3QuoteConstants.RMM_HEADER_SIZE);

        
        // ���R�[�h�����擾
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
            // �t�H�[�}�b�g�G���[�̏ꍇ
            String errorMsg = 
                "num_of_record field must be a number." 
                + "[26]" + Integer.toHexString(headerBuf[26] & 0xFF)
                + "[27]"+ Integer.toHexString(headerBuf[27] & 0xFF);
            throw new RuntimeException(errorMsg, nfe);
        }
        
        totalRecords += numRecords;
        
        // ��M���������������O�t�@@�C���ɏo��
        writeLog();
        
        //�؎̂ĉ\���`�F�b�N
        if(isDiscardable())
        {
            return;
        }
        
        // Message�I�u�W�F�N�g�ɒl���Z�b�g����
        tmpMessage.count = numRecords;
        tmpMessage.data = dataBuf;
        
        if (DBG)
        {
            log.debug("data=" + WEB3QuoteUtil.toString(dataBuf, 0, dataBuf.length));
        }
        
        // Queue��Message���v�b�V������
        queue.push(tmpMessage);
    }
    
    /**
     * (is�؎̉\)<BR>
     * <BR>
     * �����f�[�^�\�[�X����ʒm���ꂽ�������
     * AP��ŏ�������K�v�����邩���肷��B<BR>
     * 
     * @@param l_fromHost From�}�V����
     * @@param l_toHost To�}�V����
     * @@return �w�肵����������؂�̂ĉ\�ȏꍇ��<code>true</code>�A
     *         ����ȊO�̏ꍇ��<code>false</code>��Ԃ��B
     */
    private boolean isDiscardable()
    {

        //TO�}�V�������擾
        String toMachineName = 
            WEB3QuoteUtil.toString(
                headerBuf, 
                WEB3QuoteConstants.FROM_MACHINE_NAME_SIZE, 
                WEB3QuoteConstants.TO_MACHINE_NAME_SIZE);

        if (DBG)
        {
            log.debug("toMachineName=" + toMachineName);
        }
   
        //���悪���T�[�o�̏ꍇ�A�؎̉\
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
     * �X���b�h���ɂ���V�[�P���V�����Ȕԍ����擾����B
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
     * ��M�������������t�@@�C���ɏo�͂���
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
     * �����T�[�o�Ƃ̐ڑ����m��������ɋN�����鏈��
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
     * �����T�[�o�Ƃ̐ڑ���ؒf����O�ɋN�����鏈��
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
     * WEB3MessageQueue�Ƀv�[������Ă��鎞�������擾���A
     * �������ʒm�C�x���g�n���h���Ɏ擾������������ʒm����X���b�h�B
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
     * (�v���X���b�h)<BR>
     * <BR>
     * �����T�[�o�ɗv�����o���X���b�h�B<BR>
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
         * �����������v��������B<BR>
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

                    //�a�ʊm�F�����˂đS�Ăɏ������v��������
                    for(int i=0;i<feeders.size();i++)
                    {
                        // �����T�[�o
                        WEB3QuoteFeeder l_feeder = (WEB3QuoteFeeder)feeders.get(i);
                        
                        // �T�[�o�A�h���X
                        String l_serverAddress = l_feeder.getHost();

                        // �T�[�o�|�[�g
                        int l_serverPort = l_feeder.getPort();

                        try
                        {
                            log.info("Initialize Request trying to " + l_serverAddress + ":" + l_serverPort);

                            //JMX�ŗv��
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

                    //臒l�ȏ�̏ꍇ�A�f�[�^�\�[�X��؂�ւ���
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

                    //���������グ�邽�߁A�X���[�v�O�Ƀ`�F�b�N
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

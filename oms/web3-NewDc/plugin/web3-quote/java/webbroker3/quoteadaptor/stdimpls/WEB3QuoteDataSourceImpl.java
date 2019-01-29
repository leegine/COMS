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
 * Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 * File Name        : QuoteDataSource�̎����N���X(WEB3QuoteDataSourceImpl.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2004/02/04 �R�c�@@��i(FLJ) �V�K�쐬
 *                  : 2005/05/17 �R�c�@@��i(FLJ) �n�[�g�r�[�g������ǉ�
 *                  : 2005/05/17 �R�c�@@��i(FLJ) �ڑ��X�e�[�^�X�ύX�������ړ�
 *                  : 2005/05/24 �R�c�@@��i(FLJ) ��M�f�[�^�̃t�@@�C���o�͂�񓯊��ōs���悤�ɕύX 
 *                  : 2005/05/24 �R�c�@@��i(FLJ) ���[�J���|�[�g�ԍ��u0�v�̏ꍇ�́A�󂢂Ă���|�[�g���g�p����悤�ɕύX
 *                  : 2005/05/24 �R�c�@@��i(FLJ) �Ď��T�[�r�X�̊J�n�E��~������ǉ�
 *                  : 2009/04/23 �V���@@�h�O(FLJ) �����V�X�e��QUICK�ւ̈ڍs
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
 * QuoteDataSource�̎����N���X�B
 * �����T�[�o�ɐڑ����Ď���������M���A
 * �o�^���ꂽQuoteEventHandler�ɓn���B
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
     * �w�肳�ꂽ�ڑ�����SeikoQuoteDataSource�𐶐�����B
     *
     * @@param id �T�[�r�XID�B
     * @@param serverAddress �ڑ���IP�A�h���X�B
     * @@param serverPort �ڑ���|�[�g
     * @@param localAddress �ڑ���IP�A�h���X�B
     * @@param localPort �ڑ����|�[�g�B
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
     * �T�[�o����̎������̎�M���~����B
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
     * �����T�[�o�ւ̐ڑ����s���B
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
     * �����T�[�o�ւ̐ڑ���ؒf����B
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
     * �����T�[�o�[�ɐڑ�����Ă���\�P�b�g����f�[�^���擾����B
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
      
        // �n�[�g�r�[�g�̏ꍇ�͂����ŏI��
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
        
        // �w�b�_�[���烌�R�[�h����ǂݍ���
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
        // �f�[�^������ǂݍ���
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
        
        // ��M���������������O�t�@@�C���ɏo��
        writeLog();

        // Message�I�u�W�F�N�g�ɒl���Z�b�g����
        tmpMessage.count = numRecords;
        tmpMessage.data = dataBuf;
        
        // Queue��Message���v�b�V������
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
     * �n�[�g�r�[�g�����s�X���b�h�𐶐����A
     * �����T�[�o�Ƀn�[�g�r�[�g���b�Z�[�W�𑗐M����B
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
     * �n�[�g�r�[�g�����s����X���b�h���~����B
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
     * �����T�[�o�ɑ��M����n�[�g�r�[�g���b�Z�[�W�𐶐�����B
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
     * �����T�[�o�Ƃ̐ڑ���ؒf����O�ɋN�����鏈��
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
     * �����T�[�o�ɐڑ�����Ă���\�P�b�g���玞�������擾���A WEB3MessageQueue�Ɏ擾�������������v�b�g����X���b�h�B
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
            
            // ���ɐڑ��ς݂̏ꍇ�͉������Ȃ��B
            if (connected)
            {
                return connected;
            }
            
            // �ڑ��J�n
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
                // �ڑ��ł��Ȃ������ꍇ
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
     * �����T�[�o�Ƀn�[�g�r�[�g���b�Z�[�W�𑗐M����X���b�h
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

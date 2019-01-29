/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3DefaultQuoteDataSourceImpl�N���X(DOTDefaultQuoteDataSourceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/24 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Timestamp;


import com.fitechlabs.fin.intellioms.rulesys.BizDateProvider;
import com.fitechlabs.fin.intellioms.util.Log;
import com.fitechlabs.xtier.kernel.XtierKernel;
import com.fitechlabs.xtier.services.ioc.IocServiceException;

import jp.co.dir.dot.intellioms.enums.QuoteStatus;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteConnectionErrorHandler;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteDataSource;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteEventHandler;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteStatusManager;



/**
 * (�����f�[�^�\�[�XImpl)
 *
 * �����f�[�^�\�[�X�̎����N���X�B
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTDefaultQuoteDataSourceImpl implements DOTQuoteDataSource
{

    /** ���K�[ */
    private static final Log log = Log.getLogger(DOTDefaultQuoteDataSourceImpl.class);

    /** �f�o�b�N�t���O */
    private static final boolean DBG = log.isDebug();

    /** �X���b�h�^�C�v�F�n�[�g�r�[�g�X���b�h */
    private static final int HEARTBEAT_THREAD = 0;

    /** �X���b�h�^�C�v�F��M�X���b�h */
    private static final int RECEIVE_THREAD = 1;

    /** �X���b�h�^�C�v�F�X�V�X���b�h */
    private static final int UPDATE_THREAD = 2;

    /** �X���b�hID */
    private static int[] threadNo = { 1, 1, 1 };

    /** ���[�J���z�X�g�̃z�X�g�� */
    private final String localHostName;

    /** ��Ɨp�������b�Z�[�W */
    private final DOTQuoteMessage message;

    /** �n�[�g�r�[�g���b�Z�[�W�p�o�b�t�@ */
    private final byte[] heartbeatBuf = new byte[DOTQuoteConstants.HEARTBEAT_SIZE];

    /** �������b�Z�[�W�E�w�b�_���p�o�b�t�@ */
    private final byte[] headerBuf = new byte[DOTQuoteConstants.HEADER_SIZE];

    /** �������b�Z�[�W�E�f�[�^���p�o�b�t�@ */
    private final byte[] dataBuf = new byte[DOTQuoteConstants.MAX_DATA_SIZE];

    /** �����A�_�v�^�v���p�e�B */
    private final DOTQuoteProperties props;

    /** �������b�Z�[�W�L���[ */
    private DOTQuoteMessageQueue queue;

    /** �����C�x���g�n���h�� */
    private DOTQuoteEventHandler handler;

    /** �ڑ��G���[�n���h�� */
    private DOTQuoteConnectionErrorHandler errorHandler;

    /** �ڑ���ԊǗ� */
    private DOTQuoteStatusManager statusManager;

    /** �����T�[�o�̃z�X�g�� */
    private String serverAddress;

    /** �����T�[�o�̃|�[�g�ԍ� */
    private int serverPort;

    /** SO_TIMEOUT���� */
    private int timeout;

    /** �����T�[�o�Ƃ̐ڑ��Ɏg�p����\�P�b�g */
    private Socket socket;

    /** �����T�[�o����̓��̓X�g���[�� */
    private InputStream input;

    /** �����T�[�o�ւ̏o�̓X�g���[�� */
    private OutputStream output;

    /** ��M�X���b�h */
    private ReceiveThread receiveThread;

    /** �X�V�X���b�h */
    private UpdateThread updateThread;

    /** �n�[�g�r�[�g�X���b�h */
    private HeartbeatThread heartbeatThread;

    /** �r�W�l�X���t�v���o�C�_ */
    private BizDateProvider bizDateProvider;

    /** �����T�[�o�Ƃ̐ڑ����m���������� */
    private Timestamp lastConnectedTime;

    /** �����T�[�o�Ƃ̐ؒf���m���������� */
    private Timestamp lastDisconnectedTime;

    public DOTDefaultQuoteDataSourceImpl() throws IocServiceException
    {
        this((DOTQuoteProperties) XtierKernel
            .getInstance()
            .ioc()
            .makeIocObject("web3.quote.properties.adaptor"));
    }

    /**
     * �R���X�g���N�^<BR>
     *
     * @param l_props �����A�_�v�^�v���p�e�B
     */
    public DOTDefaultQuoteDataSourceImpl(DOTQuoteProperties l_props)
    {

        // �v���p�e�B
        this.props = l_props;

        // ���[�J���z�X�g��
        this.localHostName = getLocalHostName();

        // ��Ɨp���b�Z�[�W
        this.message = new DOTQuoteMessage();

        log.info("DOTQuoteDataSource initialized.");

    }

    //
    // public methods ---------------------------------------------------------
    //

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteDataSource#registerHandler(jp.co.fitechlabs.fin.quote.DOTQuoteEventHandler)
     */
    public void registerHandler(DOTQuoteEventHandler handler)
    {
        this.handler = handler;
    }

    /**
     * (register�r�W�l�X���t�v���o�C�_)<BR>
     * <BR>
     * �r�W�l�X���t�v���o�C�_��o�^����B<BR>
     *
     * @param l_bizDateProvider �r�W�l�X���t�v���o�C�_
     */
    public void registerBizDateProvider(BizDateProvider l_bizDateProvider)
    {
        this.bizDateProvider = l_bizDateProvider;
    }

    /**
     * (register�ڑ��X�e�[�^�X�}�l�[�W��)<BR>
     * <BR>
     * �ڑ��X�e�[�^�X�}�l�[�W����o�^����B<BR>
     *
     * @param l_statusManager �ڑ��X�e�[�^�X�}�l�[�W��
     */
    public void registerQuoteStatusManager(DOTQuoteStatusManager l_statusManager)
    {

        if (l_statusManager == null)
        {
            throw new IllegalArgumentException("l_statusManager must be not null.");
        }

        this.statusManager = l_statusManager;

        log.info("QuoteStatusManager registered. ["
            + l_statusManager.getClass().getName() + "]");

    }

    /**
     * @throws IllegalStateException ���Ɏ����T�[�o�Ƃ̐ڑ����J�n����Ă���ꍇ
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteDataSource#start()
     */
    public synchronized void start()
    {

        if (updateThread != null || receiveThread != null)
        {
            throw new IllegalStateException("DOTQuoteDataSource already started.");
        }

        // �ڑ���Ԃ��u�ڑ����v�ɕύX
        statusManager.modifyStatus(QuoteStatus.CONNECTING);

        // ������񃁃b�Z�[�W�L���[��������
        if (queue == null)
        {
            queue = new DOTQuoteMessageQueue(props.getProperty(
                DOTQuoteProperties.QUEUE_SIZE_PREF_NAME,
                DOTQuoteConstants.QUEUE_SIZE));
        }

        // ������񃁃b�Z�[�W�L���[���J�n����B
        queue.start();

        // �X�V�X���b�h�̗D�揇��
        int l_intUpdatePriority = props.getProperty(
            DOTQuoteProperties.UPDATER_PRIORITY_PREF_NAME,
            DOTQuoteConstants.UPDATER_PRIORITY)
            + Thread.NORM_PRIORITY;

        // ��M�X���b�h�̗D�揇��
        int l_intReceiverPriority = props.getProperty(
            DOTQuoteProperties.RECEIVER_PRIOTIRY_PREF_NAME,
            DOTQuoteConstants.RECEIVER_PRIORITY)
            + Thread.NORM_PRIORITY;

        // �ڑ����Ď��s����Ԋu�i�ʏ�j
        long l_lngRetryIntervalNoraml = props.getProperty(
            DOTQuoteProperties.RETRY_INTERVAL_NORMAL_PREF_NAME,
            DOTQuoteConstants.RETRY_INTERVAL_NORAML);

        // �ڑ����Ď��s����Ԋu�i�G�R�m�~�[�j
        long l_lngRetryIntervalEconomy = props.getProperty(
            DOTQuoteProperties.RETRY_INTERVAL_ECONOMY_PREF_NAME,
            DOTQuoteConstants.RETRY_INTERVAL_ECONOMY);

        // �ڑ����Ď��s����Ԋu��؂�ւ���臒l
        int l_intRetryThreshold = props.getProperty(
            DOTQuoteProperties.RETRY_THRESHOLD_PREF_NAME,
            DOTQuoteConstants.RETRY_THRESHOLD);

        // �X�V�X���b�h���J�n����B
        updateThread = new UpdateThread();
        updateThread.setName("UpdateThread-" + getThreadNo(UPDATE_THREAD));
        updateThread.setDaemon(true);
        updateThread.setPriority(l_intUpdatePriority);
        updateThread.start();

        log.info("UpdateThread started. "
            + "[priority=" + updateThread.getPriority() + "]");

        // ��M�X���b�h���J�n����B
        receiveThread = new ReceiveThread();
        receiveThread.setName("ReceiveThread-" +  + getThreadNo(RECEIVE_THREAD));
        receiveThread.setDaemon(true);
        receiveThread.setPriority(l_intReceiverPriority);
        receiveThread.setRetryIntervalNormal(l_lngRetryIntervalNoraml);
        receiveThread.setRetryIntervalEconomy(l_lngRetryIntervalEconomy);
        receiveThread.setRetryThreshold(l_intRetryThreshold);
        receiveThread.start();

        log.info("ReceiveThread started. "
            + "[priority=" + receiveThread.getPriority()
            + ",retryIntervalNormal=" + l_lngRetryIntervalNoraml
            + ",retryIntervalEconomy=" + l_lngRetryIntervalEconomy
            + ",retryThreshold=" + l_intRetryThreshold + "]");

    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteDataSource#stop()
     */
    public synchronized void stop()
    {


        // ������񃁃b�Z�[�W�L���[���~����B
        if (queue != null)
        {
            queue.stop();
        }

        // ��M�X���b�h���~����B
        if (receiveThread != null)
        {

            receiveThread.isActive = false;
            disconnect();

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


        // �X�V�X���b�h���~����B
        if (updateThread != null)
        {

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

        // �ڑ���Ԃ��u���ڑ��v�ɕύX
        statusManager.modifyStatus(QuoteStatus.CLOSED);

    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteDataSource#getStatus()
     */
    public QuoteStatus getStatus()
    {
        return statusManager.getCurrentStatus();
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteDataSource#getLastConnectedTime()
     */
    public synchronized Timestamp getLastConnectedTime()
    {
        return lastConnectedTime;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteDataSource#getLastDisconnectedTime()
     */
    public synchronized Timestamp getLastDisconnectedTime()
    {
        return lastDisconnectedTime;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteDataSource#isConnected()
     */
    public boolean isConnected()
    {
        return QuoteStatus.CONNECTED.equals(getStatus());
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteDataSource#registerErrorHandler(jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteConnectionErrorHandler)
     */
    public void registerErrorHandler(
        DOTQuoteConnectionErrorHandler l_errorHandler)
    {

        if (l_errorHandler == null)
        {
            throw new IllegalArgumentException("l_errorHandler must be not null.");
        }

        this.errorHandler = l_errorHandler;
        l_errorHandler.onRegister(this);

        log.info("QuoteConnectionErrorHandler registred. ["
            + l_errorHandler + "]");

    }

    /**
     * @see Object#toString()
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("DOTDefaultQuoteDataSourceImpl[");
        l_sb.append("]");
        return l_sb.toString();
    }

    //
    // protected methods ------------------------------------------------------
    //

    /**
     * (onConnectionEstablished)<BR>
     * <BR>
     * �����T�[�o�Ƃ̐ڑ����m�������Ƃ��Ɏ��s���鏈���B<BR>
     */
    protected void onConnectionEstablished()
    {

        long l_lngConnectedTime = bizDateProvider.getCurrentTimeMillis();
        setLastConnectedTime(new Timestamp(l_lngConnectedTime));

        startHeartbeat();

        statusManager.modifyStatus(QuoteStatus.CONNECTED);

    }

    /**
     * (onDisconnectingConnection)<BR>
     * <BR>
     * �����T�[�o�Ƃ̐ڑ���ؒf����Ƃ��Ɏ��s���鏈���B<BR>
     */
    protected void onDisconnectingConnection()
    {

        long l_lngDisconnectedTime = bizDateProvider.getCurrentTimeMillis();
        setLastDisconnectedTime(new Timestamp(l_lngDisconnectedTime));

        stopHeartbeat();

        statusManager.modifyStatus(QuoteStatus.CONNECTING);

    }

    /**
     * (onConnectionError)<BR>
     * <BR>
     * �����T�[�o�Ƃ̐ڑ����ɒʐM�G���[�����������ꍇ�̏����B<BR>
     */
    protected void onConnectionError()
    {

        onDisconnectingConnection();

        if (errorHandler != null)
        {
            errorHandler.handle();
        }

    }

    //
    // private methods --------------------------------------------------------
    //

    /**
     * (connect)<BR>
     * <BR>
     * �����T�[�o�ɐڑ�����B<BR>
     *
     * @throws IOException �ڑ��ł��Ȃ������ꍇ
     */
    private void connect() throws IOException
    {

        // �T�[�o�A�h���X
        serverAddress = props.getProperty(
            DOTQuoteProperties.SERVER_ADDRESS_PREF_NAME,
            DOTQuoteConstants.SERVER_ADDRESS);

        // �T�[�o�|�[�g
        serverPort = props.getProperty(
            DOTQuoteProperties.SERVER_PORT_PREF_NAME,
            DOTQuoteConstants.SERVER_PORT);

        // SO_TIMEOUT����
        timeout = props.getProperty(
            DOTQuoteProperties.HEARTBEAT_TIMEOUT_PREF_NAME,
            DOTQuoteConstants.HEARTBEAT_TIMEOUT);

        log.info(
            "Connecting to "
            + serverAddress + ":" + serverPort + " ["
            + "SO_TIMEOUT=" + timeout + ".");

        socket = new Socket(serverAddress, serverPort);
        socket.setSoTimeout(timeout);

        log.info(
            "Connection established to "
            + serverAddress + ":" + serverPort + " ["
            + "SO_TIMEOUT=" + timeout + ".");

        input = new BufferedInputStream(socket.getInputStream());
        output = new BufferedOutputStream(socket.getOutputStream());

        onConnectionEstablished();

    }

    /**
     * (disconnect)<BR>
     * <BR>
     * �����T�[�o�Ƃ̐ڑ���ؒf����B<BR>
     */
    private void disconnect()
    {

        log.info("Disconnecing the connection to "
            + serverAddress + ":" + serverPort + ".");

        onDisconnectingConnection();

        close();

        log.info("Connection disconnected to "
            + serverAddress + ":" + serverPort + ".");

    }

    /**
     * (close)<BR>
     * <BR>
     * �����T�[�o�Ƃ̐ڑ��Ɏg�p����\�P�b�g��I/O�X�g���[�������B<BR>
     */
    private void close()
    {

        if (output != null)
        {
            try
            {

                output.flush();
                output.close();

                log.debug("Output stream closed.");

            } catch (IOException ioe)
            {
                log.warn(
                    "Unexpected Exception occured while closing output stream.",
                    ioe);
            } finally
            {
                output = null;
            }
        }

        if (input != null)
        {
            try
            {

                input.close();

                log.debug("Input stream closed.");

            } catch (IOException ioe)
            {
                log.warn(
                    "Unexpected Exception occured while closing input stream.",
                    ioe);
            } finally
            {
                input = null;
            }
        }

        if (socket != null)
        {
            try
            {
                socket.close();

                log.debug("Socket closed.");

            } catch (IOException ioe)
            {
                log.warn(
                    "Unexpected Exception occured while closing input stream.",
                    ioe);
            } finally
            {
                socket = null;
            }
        }

    }

    /**
     * (receive�������)<BR>
     * <BR>
     * �����T�[�o����z�M���ꂽ���b�Z�[�W����������B<BR>
     * �z�M���ꂽ���b�Z�[�W���������ł���ꍇ�́A
     * �������b�Z�[�W�L���[�Ƀv�b�g����B<BR>
     * �z�M���ꂽ���b�Z�[�W���n�[�g�r�[�g���b�Z�[�W�ł���ꍇ�́A
     * ���̃��b�Z�[�W����M����B<BR>
     *
     *
     * @return �����ɐ��������ꍇ<code>true</code>�A���s�����ꍇ<code>false</code>
     * @throws IOException �f�[�^�������ɔ�������IOException���X���[����B
     */
    private boolean receiveData() throws IOException
    {

        // �ǂݍ��񂾃��b�Z�[�W�̃o�C�g��
        int count = 0;

        // �ǂݍ��񂾃��b�Z�[�W�̃o�C�g���i�e���|�����j
        int received = 0;

        // �n�[�g�r�[�g���b�Z�[�W�p�o�b�t�@�ɓǂݍ��ށB
        while (count < DOTQuoteConstants.HEARTBEAT_SIZE)
        {
            if ((received =
                input.read(
                    heartbeatBuf,
                    count,
                    DOTQuoteConstants.HEARTBEAT_SIZE - count))
                < 0)
            {
                return false;
            }
            count += received;
        }

        // ��M���Ԃ��L�^����B
        long updatedTime = bizDateProvider.getCurrentTimeMillis();

        if (DBG)
        {
            String header =
                DOTQuoteUtils.toString(
                    heartbeatBuf,
                    0,
                    DOTQuoteConstants.HEARTBEAT_SIZE);
            log.debug("msg header=" + header);
        }

        // �n�[�g�r�[�g���b�Z�[�W�ꍇ
        if (isHeartbeatMessage())
        {
            if (DBG)
            {
                log.debug("Heartbeat succeeded.");
            }

            // true��Ԃ��ďI��
            return true;
        }

        // �ǂݍ��񂾃��b�Z�[�W���������b�Z�[�W�p�o�b�t�@�ɃR�s�[
        count = DOTQuoteConstants.HEARTBEAT_SIZE - DOTQuoteConstants.HEADER_SIZE;
        System.arraycopy(
            heartbeatBuf, 0, headerBuf, 0,
            DOTQuoteConstants.HEADER_SIZE);
        System.arraycopy(
            heartbeatBuf, DOTQuoteConstants.HEADER_SIZE,
            dataBuf, 0, count);

        // �V�[�P���XNO���擾�B
        long sequenceNo = 0;
        try
        {
            sequenceNo =
                DOTQuoteUtils.toLong(
                    headerBuf,
                    0,
                    DOTQuoteConstants.SEQUENCE_NO_SIZE,
                    0);
        } catch (NumberFormatException nfe)
        {
            // �t�H�[�}�b�g�G���[�̏ꍇ
            String errorMsg =
                "sequence_no field must be a number."
                + "[0]" + Integer.toHexString(headerBuf[0] & 0xFF)
                + ",[1]"+ Integer.toHexString(headerBuf[1] & 0xFF)
                + ",[2]"+ Integer.toHexString(headerBuf[2] & 0xFF)
                + ",[3]"+ Integer.toHexString(headerBuf[3] & 0xFF)
                + ",[4]"+ Integer.toHexString(headerBuf[4] & 0xFF)
                + ",[5]"+ Integer.toHexString(headerBuf[5] & 0xFF)
                + ",[6]"+ Integer.toHexString(headerBuf[6] & 0xFF)
                + ",[7]"+ Integer.toHexString(headerBuf[7] & 0xFF)
                + ",[8]"+ Integer.toHexString(headerBuf[8] & 0xFF)
                + ",[9]"+ Integer.toHexString(headerBuf[9] & 0xFF);
            log.warn(errorMsg, nfe);
        }

        // ���R�[�h�����擾
        int numRecords = 0;
        try
        {
            numRecords =
                DOTQuoteUtils.toInteger(
                    headerBuf,
                    DOTQuoteConstants.SEQUENCE_NO_SIZE,
                    DOTQuoteConstants.NUM_OF_RECORDS_SIZE,
                    0);
        } catch (NumberFormatException nfe)
        {
            // �t�H�[�}�b�g�G���[�̏ꍇ
            String errorMsg =
                "num_of_record field must be a number."
                + "[10]" + Integer.toHexString(headerBuf[10] & 0xFF)
                + "[11]"+ Integer.toHexString(headerBuf[11] & 0xFF);
            throw new RuntimeException(errorMsg, nfe);
        }

        if (DBG)
        {
            log.debug("numRecords=" + numRecords);
        }

        // �f�[�^���̓ǂݍ��݃T�C�Y���擾���A�f�[�^����ǂݍ���
        int dataSize = numRecords * DOTQuoteConstants.RECORD_SIZE;
        while (count < dataSize)
        {

            if ((received =
                input.read(dataBuf, count, dataSize - count))
                < 0)
            {
                return false;
            }

            count += received;

        }

        // ��Ɨp�������b�Z�[�W�ɒl��ݒ�
        message.sequenceNo = sequenceNo;
        message.updatedTime = updatedTime;
        message.count = numRecords;
        message.data = dataBuf;

        if (count > 0)
        {

            if (DBG)
            {
                log.debug("data=" + DOTQuoteUtils.toString(dataBuf, 0, count));
            }

            // �������b�Z�[�W�E�L���[�Ƀv�b�V��
            queue.push(message);

        }

        return true;

    }

    /**
     * (start�n�[�g�r�[�g)<BR>
     * <BR>
     * �n�[�g�r�[�g�������J�n����B<BR>
     */
    private void startHeartbeat()
    {

        if (heartbeatThread != null)
        {
            throw new IllegalStateException("HeartbeatThread has already started.");
        }

        // �n�[�g�r�[�g�X���b�h�̗D�揇��
        int l_intHearbeatPriority = props.getProperty(
            DOTQuoteProperties.HEARTBEAT_PRIORITY_PREF_NAME,
            DOTQuoteConstants.HEARTBEAT_PRIORITY)
            + Thread.NORM_PRIORITY;

        // �n�[�g�r�[�g���b�Z�[�W���M�Ԋu
        long l_lngHeartbeatInterval = props.getProperty(
            DOTQuoteProperties.HEARTBEAT_INTERVAL_PREF_NAME,
            DOTQuoteConstants.HEARTBEAT_INTERVAL);

        heartbeatThread = new HeartbeatThread();
        heartbeatThread.setName("HeartbeatThread-" + getThreadNo(HEARTBEAT_THREAD));
        heartbeatThread.setDaemon(true);
        heartbeatThread.setPriority(l_intHearbeatPriority);
        heartbeatThread.heartbeatInterval = l_lngHeartbeatInterval;
        heartbeatThread.start();

        log.info("HeartbeatThread started. "
            + "[priority=" + heartbeatThread.getPriority()
            + ",hearbeatInterval=" + l_lngHeartbeatInterval + "]");

    }

    /**
     * (stop�n�[�g�r�[�g)<BR>
     * <BR>
     * �n�[�g�r�[�g�������~����B<BR>
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
     * (get���[�J���z�X�g��)<BR>
     * <BR>
     * ���[�J���z�X�g�̃z�X�g�����擾����B<BR>
     *
     * @return �z�X�g��
     */
    private String getLocalHostName()
    {
        try
        {
            String hostName = InetAddress.getLocalHost().getHostName();
            if (hostName.length() > DOTQuoteConstants.HOST_NAME_SIZE)
            {
                return hostName.substring(0, DOTQuoteConstants.HOST_NAME_SIZE);
            } else
            {
                return hostName;
            }
        } catch (UnknownHostException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * (is�n�[�g�r�[�g���b�Z�[�W)<BR>
     *
     * �����T�[�o�����M�������b�Z�[�W���n�[�g�r�[�g���b�Z�[�W�����肷��B<BR>
     *
     * @return �n�[�g�r�[�g���b�Z�[�W�̏ꍇ<code>true</code>�A����ȊO�̏ꍇ<code>false</code>��Ԃ��B
     */
    private boolean isHeartbeatMessage()
    {
        String message = DOTQuoteUtils.toString(heartbeatBuf, 0, 8);
        return localHostName.equals(message);
    }

    /**
     * (get�X���b�hNO)<BR>
     * <BR>
     * �����f�[�^�\�[�X���Ǘ�����e�X���b�h�̃X���b�h���ɂ���X���b�hNO���擾����B<BR>
     *
     * @param index 0:�n�[�g�r�[�g�X���b�h�A1:��M�X���b�h�A2:�X�V�X���b�h
     * @return �X���b�hNO
     */
    private int getThreadNo(int index)
    {
        synchronized(DOTDefaultQuoteDataSourceImpl.class)
        {
            return threadNo[index]++;
        }
    }

    /**
     * (set�ڑ��m������)<BR>
     * <BR>
     * �ڑ��m�����Ԃ�ݒ肷��B<BR>
     *
     * @param l_lastConnectedTime �ڑ��m������
     */
    private synchronized void setLastConnectedTime(Timestamp l_lastConnectedTime)
    {
        this.lastConnectedTime = l_lastConnectedTime;
    }

    /**
     * (set�ڑ��ؒf����)<BR>
     * <BR>
     * �ڑ��ؒf���Ԃ�ݒ肷��B<BR>
     *
     * @param l_lastDisconnectedTime �ڑ��ؒf����
     */
    private synchronized void setLastDisconnectedTime(Timestamp l_lastDisconnectedTime)
    {
        this.lastDisconnectedTime = l_lastDisconnectedTime;
    }

    /**
     * (��M�X���b�h)<BR>
     * <BR>
     * �����T�[�o���玞��������M����X���b�h�B<BR>
     *
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    private class ReceiveThread extends Thread
    {

        /** is�A�N�e�B�u */
        private volatile boolean isActive = true;

        /** is�ڑ��� */
        private boolean isConnected = false;

        /** �Đڑ��Ԋu�y�ʏ�z */
        private long retryIntervalNormal = DOTQuoteConstants.RETRY_INTERVAL_NORAML;

        /** �Đڑ��Ԋu�y�G�R�m�~�[�z */
        private long retryIntervalEconomy = DOTQuoteConstants.RETRY_INTERVAL_ECONOMY;

        /** �Đڑ��Ԋu�؂�ւ�臒l */
        private int retryThreshhold = DOTQuoteConstants.RETRY_THRESHOLD;

        /** �Đڑ��� */
        private int retryCounts = 0;

        /**
         * (set�Đڑ��Ԋu�y�ʏ�z)<BR>
         * <BR>
         * �Đڑ��Ԋu�y�ʏ�z��ݒ肷��B<BR>
         *
         * @param retryIntervalNormal �Đڑ��Ԋu�y�ʏ�z
         */
        void setRetryIntervalNormal(long retryIntervalNormal)
        {
            this.retryIntervalNormal = retryIntervalNormal;
        }

        /**
         * (set�Đڑ��Ԋu�y�G�R�m�~�[�z)<BR>
         * <BR>
         * �Đڑ��Ԋu�y�G�R�m�~�[�z��ݒ肷��B<BR>
         *
         * @param retryIntervalEconomy �Đڑ��Ԋu�y�G�R�m�~�[�z
         */
        void setRetryIntervalEconomy(long retryIntervalEconomy)
        {
            this.retryIntervalEconomy = retryIntervalEconomy;
        }

        /**
         * (set�Đڑ��Ԋu�؂�ւ�臒l)<BR>
         * <BR>
         * �Đڑ��Ԋu�؂�ւ�臒l��ݒ肷��B<BR>
         *
         * @param retryThreshhold �Đڑ��Ԋu�؂�ւ�臒l
         */
        void setRetryThreshold(int retryThreshhold)
        {
            this.retryThreshhold = retryThreshhold;
        }

        /**
         * (get�Đڑ��ҋ@����)<BR>
         * <BR>
         * �Đڑ������݂�܂łɑҋ@���鎞�Ԃ��擾����B<BR>
         * ���݂̍Đڑ���<�Đڑ��Ԋu�؂�ւ�臒l�̏ꍇ�͍Đڑ��Ԋu�y�ʏ�z��Ԃ��B
         * ����ȊO�̏ꍇ�͍Đڑ��Ԋu�y�G�R�m�~�[�z��Ԃ��B<BR>
         *
         * @param counts �Đڑ���
         * @return �Đڑ������݂�܂łɑҋ@���鎞��
         */
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

        /**
         * �����T�[�o�ɐڑ�������������M����B<BR>
         * ��M�������������������b�Z�[�W�L���[�Ƀv�b�g����B
         * �����T�[�o�ɐڑ��ł��Ȃ��ꍇ�́A��莞�Ԃ��ƂɍĐڑ������݂�B<BR>
         *
         * @see Runnable#run()
         */
        public void run()
        {

            try
            {

                while (isActive)
                {

                    if (!isConnected())
                    {
                        continue;
                    }

                    try
                    {
                        if (!receiveData())
                        {
                            log.error("receiveThread: Connection has been closed by the server.");
                            isConnected = false;
                            onConnectionError();
                        }
                    } catch (IOException ioe)
                    {
                        if (isActive)
                        {
                            log.error("receiveThread: Connection error occured while receiving data.", ioe);
                            onConnectionError();
                        } else
                        {
                            log.info("receiveThread: Connection closed by another thread.");
                        }
                        isConnected = false;
                    }
                } //while(!isActive)
            } catch (Throwable t)
            {
                log.error("receiveThread: Unexpected error occured.", t);
            } finally
            {
                log.debug("recieveThread: Closing connection before exitting..");
                stopHeartbeat();
                close();
            }
        }

        /**
         * (is�ڑ���)<BR>
         * <BR>
         * �����T�[�o�ɐڑ�����Ă���ꍇ��<code>true</code>��Ԃ��B<BR>
         * ����ȊO�̏ꍇ�͎����T�[�o�ւ̐ڑ������݁A
         * �ڑ����m�������ꍇ��<code>true</code>�A
         * ����ȊO�̏ꍇ��<code>false</code>��Ԃ��B<BR>
         */
        private boolean isConnected()
        {

            // ���ɐڑ��ς݂̏ꍇ�͉������Ȃ��B
            if (isConnected)
            {
                return isConnected;
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
                isConnected = true;
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
                }
            }

            return isConnected;

        }

    }

    /**
     * (�X�V�X���b�h)<BR>
     * <BR>
     * �����T�[�o�����M�����������������C�x���g�n���h���ɒʒm����X���b�h�B<BR>
     *
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    private class UpdateThread extends Thread
    {

        /**
         * �������b�Z�[�W�L���[���玞�������擾����B<BR>
         * �擾�����������������C�x���g�ɐݒ肵�A
         * �����C�x���g�n���h���ɒʒm����B<BR>
         *
         * @see Runnable#run()
         */
        public void run()
        {
            try
            {

                final DOTQuoteMessage tmpMsg = new DOTQuoteMessage();

                DOTQuoteEventImpl[] records =
                    new DOTQuoteEventImpl[DOTQuoteConstants.MAX_RECORDS];
                for (int i = 0; i < records.length; i++)
                {
                    records[i] = new DOTQuoteEventImpl();
                }

                int count = 0;
                while (queue.pop(tmpMsg))
                {
                    count = tmpMsg.parse(records);
                    for (int i = 0; i < count; i++)
                    {
                        handler.push(records[i]);
                    }
                }


            } catch (Throwable th)
            {
                log.error(getName() + " : Unexpected error occured.", th);
            } finally
            {
                log.debug("updateThread exitting.");
            }
        }

    }

    /**
     * (�n�[�g�r�[�g�X���b�h)<BR>
     * <BR>
     * �����T�[�o�Ƀn�[�g�r�[�g���b�Z�[�W�𑗐M����X���b�h�B<BR>
     *
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    private class HeartbeatThread extends Thread
    {

        /** is�A�N�e�B�u */
        private volatile boolean isActive = true;

        /** �n�[�g�r�[�g���b�Z�[�W���M�Ԋu */
        private long heartbeatInterval;

        /**
         * �����T�[�o�Ƀn�[�g�r�[�g�𑗐M����B<BR>
         *
         * @see Runnable#run()
         */
        public void run()
        {
            try
            {

                StringBuffer l_sbMessage = new StringBuffer();
                int l_intSize = DOTQuoteConstants.HEARTBEAT_SIZE - 1;
                for (int i = 0; i <  l_intSize; i++)
                {
                    l_sbMessage.append(" ");
                }
                l_sbMessage.append("0");

                int l_intEnd =
                    (localHostName.length() > DOTQuoteConstants.HOST_NAME_SIZE)
                    ? DOTQuoteConstants.HOST_NAME_SIZE
                    : localHostName.length();
                l_sbMessage.replace(0, l_intEnd, localHostName);

                byte[] heartbeatMsg =
                    l_sbMessage.toString().getBytes(
                        DOTQuoteConstants.DEFAULT_ENCODING);

                while (isActive)
                {

                    output.write(heartbeatMsg);
                    output.flush();

                    log.debug("Heartbeat message sended. [message=" + l_sbMessage + "]");

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

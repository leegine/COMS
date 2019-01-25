/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3TestQuoteServer�N���X(WEB3TestQuoteServer.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/08 �R�c�@��i(FLJ) �V�K�쐬
 */
package webbroker3.intellioms.quote.adaptor.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;



/**
 * �P�̃e�X�g�p�̎����T�[�o�N���X�B
 * 
 * �e�L�X�g�t�@�C�����玞������ǂݎ��A�ڑ������N���C�A���g�ɔz�M����B
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class WEB3TestQuoteServer
{
    
    /**
     * �f�o�b�N���[�h�œ��삷�邽�߂ɐݒ肷��V�X�e���v���p�e�B�̖��O
     */
    public static final String IS_DEBUG_PROPERTY_NAME = "WEB3TestQuoteServer.isDebug";

    /**
     * �|�[�g�ԍ��̃f�t�H���g�l
     */
    public static final int DEFAULT_PORT = 9000;

    /**
     * �N�����Ɏ�������ǂݎ��t�@�C�����̃f�t�H���g�l
     */
    public static final String DETAULT_FILE_NAME = "./quotes.txt";

    /**
     * �n�[�g�r�[�g�������~����臒l�̃f�t�H���g�l
     */
    public static final int DEFAULT_HEARTBEAT_FAILURE_THRESHOLD = 0;

    /**
     * 1��ɑ��M���鎞�����̌����̃f�t�H���g�l
     */
    public static final int DEFAULT_QUOTE_DATA_SIZE = 1;
    
    /**
     * ��������z�M����Ԋu�̃f�t�H���g�l
     */
    public static final long DEFAULT_FEED_INTERVAL = 0;

    /**
     * WorkerThread�̃X���b�h���ɂ���V���A���ԍ�
     */
    private static int serialNo = 0;
    
    /**
     * �������Ă���WorkerThread�̃R���N�V����
     */
    private final Set workerThreads = Collections.synchronizedSet(new HashSet());

    /**
     * �e�X�g�p�����T�[�o�̃|�[�g�ԍ�
     */
    private int port;
    
    /**
     * �N�����Ɏ�������ǂݎ��t�@�C����
     */
    private String dataFileName;
    
    /**
     * �N����
     */
    private boolean isActive;
    
    /**
     * �n�[�g�r�[�g�������~����臒l
     * 
     * �n�[�g�r�[�g�����̑��M�������Ŏw�肵���񐔍s���ƁA
     * �ȍ~�́A�n�[�g�r�[�g�����𑗐M���~����B
     * �u0����w�肷��ƁA�����T�[�o�̓n�[�g�r�[�g�����𑗐M���Â���B
     * 
     * �f�t�H���g�l�F0
     */
    private int heartbeartFailureThreshold;
    
    /**
     * 1��ɑ��M���鎞�����̌���
     * 
     * �����T�[�o��1��ɑ��鎞�����̌������w�肷��B
     * �u1��`�u10�v�̐����l���w�肷�邱�Ƃ��o����B
     * ��L�͈͊O�̒l�����Ă������ꍇ�A�u1��`�u10��̊Ԃ�
     * �����_���Ɍ��肳�ꂽ�����𑗐M����B
     * 
     * �f�t�H���g�l�F1
     */
    private int feedUnitSize;
    
    /**
     * ��������z�M����Ԋu
     * 
     * �����T�[�o����������z�M���Ă���A���̔z�M�܂łɑҋ@���鎞�Ԃ�
     * �~���b�P�ʂŎw�肷��B
     * �0����w�肳�ꂽ�ꍇ�́A�ҋ@�����Ɏ��̎�������z�M����B
     * 
     * �f�t�H���g�l�F0
     */
    private long feedInterval;
    
    /**
     * �f�o�b�N���O�o�̓t���O
     */
    private boolean isDebug;

    /**
     * �T�[�o�\�P�b�g
     */
    private ServerSocket serverSocket;

    /**
     * �T�[�o�̃��C�����������s����X���b�h
     */
    private ServerThread serverThread;
    
    /**
     * �R���X�g���N�^
     * 
     * @param l_intPort �����T�[�o�̃|�[�g�ԍ�
     * @param l_strDataFileName �N�����ɓǂݍ��ގ������̃t�@�C����
     */
    public WEB3TestQuoteServer(int l_intPort, String l_strDataFileName)
    {
        this(l_intPort, l_strDataFileName, DEFAULT_HEARTBEAT_FAILURE_THRESHOLD);
    }

    /**
     * �R���X�g���N�^
     * 
     * @param l_intPort �����T�[�o�̃|�[�g�ԍ�
     * @param l_strDataFileName �N�����ɓǂݍ��ގ������̃t�@�C����
     * @param l_intHeartbeatFilureThreshold �n�[�g�r�[�g�������~����臒l
     */
    public WEB3TestQuoteServer(int l_intPort, String l_strDataFileName,
        int l_intHeartbeatFilureThreshold)
    {
        this(
            l_intPort,
            l_strDataFileName,
            l_intHeartbeatFilureThreshold,
            DEFAULT_QUOTE_DATA_SIZE);
    }
    
    /**
     * �R���X�g���N�^
     * 
     * @param l_intPort �����T�[�o�̃|�[�g�ԍ�
     * @param l_strDataFileName �N�����ɓǂݍ��ގ������̃t�@�C����
     * @param l_intHeartbeatFilureThreshold �n�[�g�r�[�g�������~����臒l
     * @param l_intQuoteDataSize 1��ɑ��M���鎞�����̌���
     */
    public WEB3TestQuoteServer(int l_intPort, String l_strDataFileName,
        int l_intHeartbeatFilureThreshold, int l_intQuoteDataSize)
    {
        this(
            l_intPort,
            l_strDataFileName,
            l_intHeartbeatFilureThreshold,
            l_intQuoteDataSize,
            DEFAULT_FEED_INTERVAL);
    }
    
    /**
     * �R���X�g���N�^
     * 
     * @param l_intPort �����T�[�o�̃|�[�g�ԍ�
     * @param l_strDataFileName �N�����ɓǂݍ��ގ������̃t�@�C����
     * @param l_intHeartbeatFilureThreshold �n�[�g�r�[�g�������~����臒l
     * @param l_intFeedUnitSize 1��ɑ��M���鎞�����̌���
     * @param l_lngFeedInterval ��������z�M����Ԋu
     */
    public WEB3TestQuoteServer(int l_intPort, String l_strDataFileName,
        int l_intHeartbeatFilureThreshold, int l_intFeedUnitSize,
        long l_lngFeedInterval)
    {
        this.port = l_intPort;
        this.dataFileName = l_strDataFileName;
        this.heartbeartFailureThreshold = l_intHeartbeatFilureThreshold;
        this.feedUnitSize = l_intFeedUnitSize;
        this.feedInterval = l_lngFeedInterval;
        this.isDebug = getIsDebugFromSystemProperty();
        this.isActive = false;
    }
    
    /**
     * �w�肵��String�z��̈�����ݒ肵�������T�[�o�̃C���X�^���X���쐬����B
     * 
     * @param ��1���� �����T�[�o�̃|�[�g�ԍ��y�ȗ����F9000�z
     * @param ��2���� �N�����ɓǂݍ��ގ������̃t�@�C�����y�ȗ����F./test/java/quotes.txt�z
     * @param ��3���� �n�[�g�r�[�g�������~����臒l�y�ȗ����F0�z
     * @param ��4���� 1��ɑ��M���鎞�����̌����y�ȗ����F0�z
     * @param ��5���� ��������z�M����Ԋu�y�ȗ����F0�z
     */
    public static WEB3TestQuoteServer setUpQuoteServer(String[] l_strArgs)
    {
        
        System.out.println("Initializing Test Quote Server...");
        
        // �|�[�g�ԍ����擾����B
        int l_intPort = DEFAULT_PORT;
        if (l_strArgs.length >= 1)
        {
            l_intPort = Integer.parseInt(l_strArgs[0]);
        }
        
        // �������t�@�C�������擾����B
        String l_strDataFileName = DETAULT_FILE_NAME;
        if (l_strArgs.length >= 2)
        {
            l_strDataFileName = l_strArgs[1];
        }
        
        System.out.println("dataFile=" + l_strDataFileName);
        
        // �n�[�g�r�[�g���~����臒l���擾����B
        int l_intHeartbeatFailureThreshold = DEFAULT_HEARTBEAT_FAILURE_THRESHOLD;
        if (l_strArgs.length >= 3)
        {
            l_intHeartbeatFailureThreshold = Integer.parseInt(l_strArgs[2]);
        }
        
        System.out.println("heartbeatFailureThreashold=" + l_intHeartbeatFailureThreshold);
        
        // 1��ɔz�M���鎞�����̌������擾����B
        int l_intFeedUnitSize = DEFAULT_QUOTE_DATA_SIZE;
        if (l_strArgs.length >= 4)
        {
            l_intFeedUnitSize = Integer.parseInt(l_strArgs[3]);
        }
        
        System.out.println("feedUnitSize=" + l_intFeedUnitSize);
        
        // ��������z�M����Ԋu���擾����B
        long l_lngFeedInterval = DEFAULT_FEED_INTERVAL;
        if (l_strArgs.length >= 5)
        {
            l_lngFeedInterval = Long.parseLong(l_strArgs[4]);
        }
        
        System.out.println("feedInterval=" + l_lngFeedInterval);
        
        return new WEB3TestQuoteServer(
            l_intPort,
            l_strDataFileName,
            l_intHeartbeatFailureThreshold,
            l_intFeedUnitSize,
            l_lngFeedInterval);
        
    }
    
    /**
     * ���C�����\�b�h
     * 
     * �e�X�g�p�����T�[�o���N������B
     * 
     * @param ��1���� �����T�[�o�̃|�[�g�ԍ��y�ȗ����F9000�z
     * @param ��2���� �N�����ɓǂݍ��ގ������̃t�@�C�����y�ȗ����F./test/java/quotes.txt�z
     * @param ��3���� �n�[�g�r�[�g�������~����臒l�y�ȗ����F0�z
     * @param ��4���� 1��ɑ��M���鎞�����̌����y�ȗ����F0�z
     */
    public static void main(String[] l_strArgs)
    {
        
        WEB3TestQuoteServer l_quoteServer = null; 
        
        try
        {
            
            // �����T�[�o���N������B
            l_quoteServer = setUpQuoteServer(l_strArgs);
            l_quoteServer.start();
            
            // 10���ԑҋ@����B
            Thread.sleep(600000);
            
        } catch (Exception ex)
        {
            System.out.println("!!!!! Unexpected exception occured while starting quote server. !!!!!");
            ex.printStackTrace();
        } finally
        {
            // �����T�[�o���N���ς̏ꍇ�́A��~����B
            if (l_quoteServer != null)
            {
                l_quoteServer.stop();
            }
        }
        
        
    }

    /**
     * �P�̃e�X�g�p�����T�[�o���J�n����B
     * 
     * @throws IOException
     */
    public synchronized void start() throws IOException
    {
        
        System.out.println("Starting quote server."
            + "[port=" + port
            + ",dataFileName=" + dataFileName
            + ",heartbeatFailureThreshold=" + heartbeartFailureThreshold
            + ",feedUnitSize=" + feedUnitSize
            + ",feedInterval=" + feedInterval
            + "]");
        
        isActive = true;
        
        // �T�[�o�\�P�b�g���쐬����B
        serverSocket = new ServerSocket(port);
        
        // �T�[�o�X���b�h���J�n����B
        serverThread = new ServerThread();
        serverThread.setName("ServerThread");
        serverThread.setDaemon(true);
        serverThread.start();
        
    }

    /**
     * �P�̃e�X�g�p�����T�[�o���~����B
     *
     */
    public synchronized void stop()
    {
        
        System.out.println("Stopping quote server.");
        
        isActive = false;
        
        if (serverThread != null)
        {
            
            serverThread.interrupt();
            
            try
            {
                serverSocket.close();
            } catch (IOException e)
            {
            }
            
            try
            {
                serverThread.join();
            } catch (InterruptedException ie)
            {
            }
            
            serverThread = null;
            
        }
        
//        // �N�����Ă��郏�[�J�X���b�h���~����B
//        for (Iterator it = workerThreads.iterator(); it.hasNext();)
//        {
//            WorkerThread workerThread = (WorkerThread) it.next();
//            workerThread.interruptAndStop();
//        }
        
        System.out.println("Quote server stopped.");
        
    }
    
    /**
     * ���ݓ��쒆��FeedThread�����ɑ��M���鎞������ݒ肷��B
     * 
     * @param l_strFileName �������t�@�C����
     */
    public synchronized void setQuoteData(String l_strFileName) throws IOException
    {
        for (Iterator l_it = workerThreads.iterator(); l_it.hasNext();)
        {
            Object l_objWorker = l_it.next();
            if (l_objWorker instanceof FeedThread)
            {
                FeedThread l_feeder = (FeedThread) l_objWorker;
                l_feeder.parseQuoteData(l_strFileName);
            }
        }
    }
    
    /**
     * ���[�J�X���b�h�̃R���N�V�����Ɏw�肵�����[�J�X���b�h��ǉ�����B
     */
    private void addWorkerThread(WorkerThread workerThread)
    {
        workerThreads.add(workerThread);
    }
    
    /**
     * ���[�J�X���b�h�̃R���N�V��������w�肵�����[�J�X���b�h���폜����B
     */
    private void removeWorkerThread(WorkerThread workerThread)
    {
        workerThreads.remove(workerThread);
    }
    
    /**
     * �V�X�e���v���p�e�B����f�o�b�N���[�h�̐ݒ�l���擾����B
     */
    private boolean getIsDebugFromSystemProperty()
    {
        return Boolean.getBoolean(IS_DEBUG_PROPERTY_NAME);
    }
    
    /**
     * �N���C�A���g�Ƃ̐ڑ��\�P�b�g��ێ�����\�P�b�g�z���_�[�B
     * �ڑ��\�P�b�g����擾�������o�̓X�g���[�����Ǘ�����B
     *
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    private class SocketHolder
    {
        
        /** 
         * �\�P�b�g 
         */
        private Socket socket;
        
        /**
         * ���̓X�g���[��
         */
        private BufferedInputStream in;
        
        /**
         * �o�̓X�g���[��
         */
        private BufferedOutputStream out;
        
        /**
         * �X�g���[���I�[�v���t���O
         */
        private boolean isOpen;
        
        /**
         * �R���X�g���N�^
         */
        SocketHolder(Socket socket)
        {
            this.socket = socket;
        }
        
        /**
         * ���o�̓X�g���[�����J���B
         */
        synchronized void open() throws IOException
        {
            
            // ���ɊJ���Ă���ꍇ�́A�������Ȃ��B
            if (isOpen) return;
            
            in = new BufferedInputStream(socket.getInputStream());
            out = new BufferedOutputStream(socket.getOutputStream());
            isOpen = true;
            
        }
        
        /**
         * ���o�̓X�g���[�������B
         */
        synchronized void close() throws IOException
        {
            if (in != null)
            {
                try
                {
                    in.close();
                } catch (IOException ioe)
                {
                    System.out.println("!!!!! Unexpected exception occured while closing input stream. !!!!!");
                    ioe.printStackTrace();
                } finally
                {
                    in = null;
                }
            }
            
            if (out != null)
            {
                try
                {
                    out.close();
                } catch (IOException ioe)
                {
                    System.out.println("!!!!! Unexpected exception occured while closing output stream. !!!!!");
                    ioe.printStackTrace();
                } finally
                {
                    out = null;
                }
            }

            if (socket != null)
            {
                try
                {
                    socket.close();
                } catch (IOException ioe)
                {
                    System.out.println("!!!!! Unexpected exception occured while closing socket. !!!!!");
                    ioe.printStackTrace();
                } finally
                {
                    socket = null;
                }
            }
            
        }

        /**
         * �w�肵���o�C�g�z��ɓ��̓X�g���[������o�C�g��ǂݍ��ށB
         */
        int read(byte[] b, int off, int len) throws IOException
        {
            synchronized (in)
            {
                return in.read(b, off, len);
            }
        }
        
        /**
         * �w�肵���o�C�g�z����o�̓X�g���[���ɏ������ށB
         */
        void write(byte[] b) throws IOException
        {
            synchronized (out)
            {
                out.write(b);
            }
            System.out.println("write"+new String(b));
        }
        
        /**
         * �w�肵���o�C�g�z����o�̓X�g���[���ɏ������ށB
         */
        void write(byte[] b, int off, int len) throws IOException
        {
            synchronized (out)
            {
                out.write(b, off, len);
            }
        }
        
        void flush() throws IOException
        {
            synchronized (out)
            {
                out.flush();
            }
        }
        
    }
    
    /**
     * �N���C�A���g����̐ڑ���ҋ@����T�[�o�X���b�h�B
     */
    private class ServerThread extends Thread
    {

        public void run()
        {
            
            if (isDebug)
            {
                System.out.println("Server thread started.");
            }
            
            try
            {
                while (true)
                {
                    
                    if (!isActive)
                    {
                        break;
                    }
                    
                    // �N���C�A���g����̐ڑ���ҋ@����B
                    Socket socket = serverSocket.accept();
                    
                    // �N���C�A���g�̃z�X�g���ƃ|�[�g�ԍ����擾
                    String address = socket.getInetAddress().getHostName();
                    int port = socket.getPort();
                    
                    if (isDebug)
                    {
                        System.out.println("Connection established. " + address + ":" + port);
                    }
                    
                    // �X���b�h���p�V���A���ԍ����C���N�������g
                    serialNo++;
                    
                    // �n�[�g�r�[�g�����s����X���b�h���N������B
                    WorkerThread t1 = new HeartbeatThread(socket);
                    t1.setName("Heartbeat-" + serialNo);
                    t1.setDaemon(true);
                    t1.start();
                    
                    if (isDebug)
                    {
                        System.out.println("Heartbeat thread started. "
                            + "[threadName=" + t1.getName() + 
                            ", client=" + address + ":" + port + "]");
                    }
                    
                    // ��������z�M����X���b�h���N������B
                    WorkerThread t2 = new FeedThread(socket);
                    t2.setName("Feed-" + serialNo);
                    t2.setDaemon(true);
                    t2.start();
                    
                    if (isDebug)
                    {
                        System.out.println("Feeder thread started. "
                            + "[threadName=" + t2.getName() + ", client="
                            + address + ":" + port + "]");
                    }
                    
                }
                
            } catch (Throwable th)
            {
                if (isActive)
                {
                    System.out.println("!!!!! Unexpected exception occured on server thread while running. !!!!!");
                    th.printStackTrace();
                }
            } finally
            {
                System.out.println("Server thread stopped.");
            }
            
        }

    }
    
    /**
     * �N���C�A���g�Ƃ̒ʐM���s�����[�J�X���b�h�̒��ۃN���X�B
     */
    private abstract class WorkerThread extends Thread
    {
        
        /**
         * �\�P�b�g�z���_�[
         */
        protected SocketHolder socketHolder;
        
        /**
         * �R���X�g���N�^�[
         */
        WorkerThread(Socket socket)
        {
            this.socketHolder = new SocketHolder(socket);
        }

        /**
         * @see java.lang.Runnable#run()
         */
        public final void run()
        {
            
            if (isDebug)
            {
                System.out.println(getClass().getName() + " started.");
            }
            
            try
            {
                
                 //���[�J�X���b�h�R���N�V�����ɒǉ�����B
                addWorkerThread(this);
                
                // ���o�̓X�g���[�����J���B
                socketHolder.open();
                
                // �������J�n����B
                process();
                
            } catch (Throwable th)
            {
                if (isActive)
                {
                    System.out.println("!!!!! Unexpected exception occured on worker thread while running. !!!!!");
                    th.printStackTrace();
                }
            } finally
            {

                try
                {
                    
                    // ���o�̓X�g���[�������B
                    socketHolder.close();
                    
                } catch (IOException e)
                {
                }

                // ���[�J�X���b�h�R���N�V��������폜����B
                removeWorkerThread(this);
                
                if (isDebug)
                {
                    System.out.println(getClass().getName() + " stopped.");
                }
                
            }
            
        }
        
        protected synchronized void interruptAndStop()
        {
            interrupt();
        }
        
        /**
         * �T�u�N���X�Ŏ�������郏�[�J�X���b�h�̏����B
         */
        protected abstract void process() throws IOException;
        
    }
    
    /**
     * �n�[�g�r�[�g�����s���郏�[�J�X���b�h
     *
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    private class HeartbeatThread extends WorkerThread
    {

        /**
         * �R���X�g���N�^
         */
        HeartbeatThread(Socket socket)
        {
            super(socket);
        }
        
        /**
         * �N���C�A���g�Ƃ̊ԂŃn�[�g�r�[�g���b�Z�[�W�̑���M���s���B
         * 
         * @see webbroker3.intellioms.quote.adaptor.impl.WEB3TestQuoteServer.WorkerThread#process()
         */
        protected void process() throws IOException
        {

            int count = 0;
            byte[] readBuf = new byte[17];
            byte[] writeBuf = "        w3ptqt101".getBytes();
            
            while (true)
            {
                
                if (!isActive)
                {
                    break;
                }
                
                if (socketHolder.read(readBuf, 0, 17) < 17)
                {
                    break;
                }
                
                if (isDebug)
                {
                    System.out.println(
                        "HeartbeatThread received heartbeat request. [received="
                        + new String(readBuf) + "]");
                }
                
                if (heartbeartFailureThreshold > 0
                        && count > heartbeartFailureThreshold)
                {
                    try
                    {
                        Thread.sleep(60000);
                    } catch (InterruptedException e)
                    {
                        break;
                    }
                }
                
                System.arraycopy(readBuf, 0, writeBuf, 0, 8);
                
                socketHolder.write(writeBuf, 0, 17);
                socketHolder.flush();
                
                count++;
                
                if (isDebug)
                {
                    System.out.println(
                        "HeartbeatThread sended heartbeat response. [sended="
                        + new String(writeBuf) + "]");
                }
                
                
            }
            
        }

    }
    
    /**
     * ��������z�M���郏�[�J�X���b�h�̃T�u�N���X
     *
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    private class FeedThread extends WorkerThread
    {
        
        WEB3TestQuoteDataHolder dataHolder;

        FeedThread(Socket socket)
        {
            super(socket);
            dataHolder = new WEB3TestQuoteDataHolder();
        }

        protected void process() throws IOException
        {
            
            int l_intCount = 0;
            int l_intSize = 0;
            if (1 <= feedUnitSize && feedUnitSize <= 10)
            {
                l_intSize = feedUnitSize;
            }
            
            try
            {
                parseQuoteData(dataFileName);
            } catch (IOException e)
            {
                System.out.println("Exception occured while loading initial data file.");
                e.printStackTrace();
            }
            
            while (true)
            {
                
                if (!isActive)
                {
                    break;
                }
                
                // �������𑗐M����B
                if (feed(l_intSize))
                {
                    l_intCount = 0;
                } else
                {
                    l_intCount++;
                }
                
                // 10��ȏ�A���ő��M�ł��Ȃ��ꍇ�́A���̎��s�܂�1�b�ҋ@
                if (l_intCount > 10)
                {
                    try
                    {
                        Thread.sleep(1000);
                    } catch (InterruptedException e)
                    {
                        break;
                    }
                }
                
            }
            
        }
        
        /**
         * �������𑗐M����B
         */
        synchronized boolean feed(int l_intSize) throws IOException
        {

            // ���M���鎞����񂪑��݂��Ȃ��ꍇ�͏I��
            if (dataHolder.isEmpty())
            {
                return false;
            }

            // ���M���鎞�������擾����B
            byte[] data = null;
            if (l_intSize == 0)
            {
                data = dataHolder.pop();
            } else
            {
                data = dataHolder.pop(l_intSize);
            }

            // �擾�����������𑗐M����B
            socketHolder.write(data);
            socketHolder.flush();

            // ���ɑ��M����܂őҋ@����B
            if (feedInterval > 0)
            {
                try
                {
                    Thread.sleep(feedInterval);
                } catch (InterruptedException e)
                {
                }
            }

            return true;

        }
        
        /**
         * ���M���鎞�������t�@�C������ǂݍ���
         */
        synchronized void parseQuoteData(String fileName) throws IOException
        {
            
            BufferedReader reader = null;
            try
            {
                
                reader = new BufferedReader(new FileReader(fileName));
                
                String source = null;
                while ((source = reader.readLine()) != null)
                {
                    if (source.length() > 0 && !source.startsWith("#") && source.length()==214)
                    {
                        source = source.substring(35);
                        dataHolder.push(source);
                    }
                    else if (source.length() > 0 && !source.startsWith("#"))
                    {
                        dataHolder.push(source);
                    }
                }
                
            } finally
            {
                if (reader != null)
                {
                    try
                    {
                        reader.close();
                    } catch (IOException ioe)
                    {
                    }
                }
            }
            
        }

    }
}
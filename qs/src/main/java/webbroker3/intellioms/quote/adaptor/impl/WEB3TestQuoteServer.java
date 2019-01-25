/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TestQuoteServerクラス(WEB3TestQuoteServer.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/08 山田　卓司(FLJ) 新規作成
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
 * 単体テスト用の時価サーバクラス。
 * 
 * テキストファイルから時価情報を読み取り、接続したクライアントに配信する。
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class WEB3TestQuoteServer
{
    
    /**
     * デバックモードで動作するために設定するシステムプロパティの名前
     */
    public static final String IS_DEBUG_PROPERTY_NAME = "WEB3TestQuoteServer.isDebug";

    /**
     * ポート番号のデフォルト値
     */
    public static final int DEFAULT_PORT = 9000;

    /**
     * 起動時に時価情報を読み取るファイル名のデフォルト値
     */
    public static final String DETAULT_FILE_NAME = "./quotes.txt";

    /**
     * ハートビート応答を停止する閾値のデフォルト値
     */
    public static final int DEFAULT_HEARTBEAT_FAILURE_THRESHOLD = 0;

    /**
     * 1回に送信する時価情報の件数のデフォルト値
     */
    public static final int DEFAULT_QUOTE_DATA_SIZE = 1;
    
    /**
     * 時価情報を配信する間隔のデフォルト値
     */
    public static final long DEFAULT_FEED_INTERVAL = 0;

    /**
     * WorkerThreadのスレッド名につけるシリアル番号
     */
    private static int serialNo = 0;
    
    /**
     * 生存しているWorkerThreadのコレクション
     */
    private final Set workerThreads = Collections.synchronizedSet(new HashSet());

    /**
     * テスト用時価サーバのポート番号
     */
    private int port;
    
    /**
     * 起動時に時価情報を読み取るファイル名
     */
    private String dataFileName;
    
    /**
     * 起動中
     */
    private boolean isActive;
    
    /**
     * ハートビート応答を停止する閾値
     * 
     * ハートビート応答の送信をここで指定した回数行うと、
     * 以降は、ハートビート応答を送信を停止する。
     * 「0｣を指定すると、時価サーバはハートビート応答を送信しつづける。
     * 
     * デフォルト値：0
     */
    private int heartbeartFailureThreshold;
    
    /**
     * 1回に送信する時価情報の件数
     * 
     * 時価サーバが1回に送る時価情報の件数を指定する。
     * 「1｣～「10」の整数値を指定することが出来る。
     * 上記範囲外の値をしていした場合、「1｣～「10｣の間で
     * ランダムに決定された件数を送信する。
     * 
     * デフォルト値：1
     */
    private int feedUnitSize;
    
    /**
     * 時価情報を配信する間隔
     * 
     * 時価サーバが時価情報を配信してから、次の配信までに待機する時間を
     * ミリ秒単位で指定する。
     * ｢0｣が指定された場合は、待機せずに次の時価情報を配信する。
     * 
     * デフォルト値：0
     */
    private long feedInterval;
    
    /**
     * デバックログ出力フラグ
     */
    private boolean isDebug;

    /**
     * サーバソケット
     */
    private ServerSocket serverSocket;

    /**
     * サーバのメイン処理を実行するスレッド
     */
    private ServerThread serverThread;
    
    /**
     * コンストラクタ
     * 
     * @param l_intPort 時価サーバのポート番号
     * @param l_strDataFileName 起動時に読み込む時価情報のファイル名
     */
    public WEB3TestQuoteServer(int l_intPort, String l_strDataFileName)
    {
        this(l_intPort, l_strDataFileName, DEFAULT_HEARTBEAT_FAILURE_THRESHOLD);
    }

    /**
     * コンストラクタ
     * 
     * @param l_intPort 時価サーバのポート番号
     * @param l_strDataFileName 起動時に読み込む時価情報のファイル名
     * @param l_intHeartbeatFilureThreshold ハートビート応答を停止する閾値
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
     * コンストラクタ
     * 
     * @param l_intPort 時価サーバのポート番号
     * @param l_strDataFileName 起動時に読み込む時価情報のファイル名
     * @param l_intHeartbeatFilureThreshold ハートビート応答を停止する閾値
     * @param l_intQuoteDataSize 1回に送信する時価情報の件数
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
     * コンストラクタ
     * 
     * @param l_intPort 時価サーバのポート番号
     * @param l_strDataFileName 起動時に読み込む時価情報のファイル名
     * @param l_intHeartbeatFilureThreshold ハートビート応答を停止する閾値
     * @param l_intFeedUnitSize 1回に送信する時価情報の件数
     * @param l_lngFeedInterval 時価情報を配信する間隔
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
     * 指定したString配列の引数を設定した時価サーバのインスタンスを作成する。
     * 
     * @param 第1引数 時価サーバのポート番号【省略時：9000】
     * @param 第2引数 起動時に読み込む時価情報のファイル名【省略時：./test/java/quotes.txt】
     * @param 第3引数 ハートビート応答を停止する閾値【省略時：0】
     * @param 第4引数 1回に送信する時価情報の件数【省略時：0】
     * @param 第5引数 時価情報を配信する間隔【省略時：0】
     */
    public static WEB3TestQuoteServer setUpQuoteServer(String[] l_strArgs)
    {
        
        System.out.println("Initializing Test Quote Server...");
        
        // ポート番号を取得する。
        int l_intPort = DEFAULT_PORT;
        if (l_strArgs.length >= 1)
        {
            l_intPort = Integer.parseInt(l_strArgs[0]);
        }
        
        // 時価情報ファイル名を取得する。
        String l_strDataFileName = DETAULT_FILE_NAME;
        if (l_strArgs.length >= 2)
        {
            l_strDataFileName = l_strArgs[1];
        }
        
        System.out.println("dataFile=" + l_strDataFileName);
        
        // ハートビートを停止する閾値を取得する。
        int l_intHeartbeatFailureThreshold = DEFAULT_HEARTBEAT_FAILURE_THRESHOLD;
        if (l_strArgs.length >= 3)
        {
            l_intHeartbeatFailureThreshold = Integer.parseInt(l_strArgs[2]);
        }
        
        System.out.println("heartbeatFailureThreashold=" + l_intHeartbeatFailureThreshold);
        
        // 1回に配信する時価情報の件数を取得する。
        int l_intFeedUnitSize = DEFAULT_QUOTE_DATA_SIZE;
        if (l_strArgs.length >= 4)
        {
            l_intFeedUnitSize = Integer.parseInt(l_strArgs[3]);
        }
        
        System.out.println("feedUnitSize=" + l_intFeedUnitSize);
        
        // 時価情報を配信する間隔を取得する。
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
     * メインメソッド
     * 
     * テスト用時価サーバを起動する。
     * 
     * @param 第1引数 時価サーバのポート番号【省略時：9000】
     * @param 第2引数 起動時に読み込む時価情報のファイル名【省略時：./test/java/quotes.txt】
     * @param 第3引数 ハートビート応答を停止する閾値【省略時：0】
     * @param 第4引数 1回に送信する時価情報の件数【省略時：0】
     */
    public static void main(String[] l_strArgs)
    {
        
        WEB3TestQuoteServer l_quoteServer = null; 
        
        try
        {
            
            // 時価サーバを起動する。
            l_quoteServer = setUpQuoteServer(l_strArgs);
            l_quoteServer.start();
            
            // 10分間待機する。
            Thread.sleep(600000);
            
        } catch (Exception ex)
        {
            System.out.println("!!!!! Unexpected exception occured while starting quote server. !!!!!");
            ex.printStackTrace();
        } finally
        {
            // 時価サーバが起動済の場合は、停止する。
            if (l_quoteServer != null)
            {
                l_quoteServer.stop();
            }
        }
        
        
    }

    /**
     * 単体テスト用時価サーバを開始する。
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
        
        // サーバソケットを作成する。
        serverSocket = new ServerSocket(port);
        
        // サーバスレッドを開始する。
        serverThread = new ServerThread();
        serverThread.setName("ServerThread");
        serverThread.setDaemon(true);
        serverThread.start();
        
    }

    /**
     * 単体テスト用時価サーバを停止する。
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
        
//        // 起動しているワーカスレッドを停止する。
//        for (Iterator it = workerThreads.iterator(); it.hasNext();)
//        {
//            WorkerThread workerThread = (WorkerThread) it.next();
//            workerThread.interruptAndStop();
//        }
        
        System.out.println("Quote server stopped.");
        
    }
    
    /**
     * 現在動作中のFeedThreadが次に送信する時価情報を設定する。
     * 
     * @param l_strFileName 時価情報ファイル名
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
     * ワーカスレッドのコレクションに指定したワーカスレッドを追加する。
     */
    private void addWorkerThread(WorkerThread workerThread)
    {
        workerThreads.add(workerThread);
    }
    
    /**
     * ワーカスレッドのコレクションから指定したワーカスレッドを削除する。
     */
    private void removeWorkerThread(WorkerThread workerThread)
    {
        workerThreads.remove(workerThread);
    }
    
    /**
     * システムプロパティからデバックモードの設定値を取得する。
     */
    private boolean getIsDebugFromSystemProperty()
    {
        return Boolean.getBoolean(IS_DEBUG_PROPERTY_NAME);
    }
    
    /**
     * クライアントとの接続ソケットを保持するソケットホルダー。
     * 接続ソケットから取得した入出力ストリームも管理する。
     *
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    private class SocketHolder
    {
        
        /** 
         * ソケット 
         */
        private Socket socket;
        
        /**
         * 入力ストリーム
         */
        private BufferedInputStream in;
        
        /**
         * 出力ストリーム
         */
        private BufferedOutputStream out;
        
        /**
         * ストリームオープンフラグ
         */
        private boolean isOpen;
        
        /**
         * コンストラクタ
         */
        SocketHolder(Socket socket)
        {
            this.socket = socket;
        }
        
        /**
         * 入出力ストリームを開く。
         */
        synchronized void open() throws IOException
        {
            
            // 既に開いている場合は、何もしない。
            if (isOpen) return;
            
            in = new BufferedInputStream(socket.getInputStream());
            out = new BufferedOutputStream(socket.getOutputStream());
            isOpen = true;
            
        }
        
        /**
         * 入出力ストリームを閉じる。
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
         * 指定したバイト配列に入力ストリームからバイトを読み込む。
         */
        int read(byte[] b, int off, int len) throws IOException
        {
            synchronized (in)
            {
                return in.read(b, off, len);
            }
        }
        
        /**
         * 指定したバイト配列を出力ストリームに書き込む。
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
         * 指定したバイト配列を出力ストリームに書き込む。
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
     * クライアントからの接続を待機するサーバスレッド。
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
                    
                    // クライアントからの接続を待機する。
                    Socket socket = serverSocket.accept();
                    
                    // クライアントのホスト名とポート番号を取得
                    String address = socket.getInetAddress().getHostName();
                    int port = socket.getPort();
                    
                    if (isDebug)
                    {
                        System.out.println("Connection established. " + address + ":" + port);
                    }
                    
                    // スレッド名用シリアル番号をインクリメント
                    serialNo++;
                    
                    // ハートビートを実行するスレッドを起動する。
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
                    
                    // 時価情報を配信するスレッドを起動する。
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
     * クライアントとの通信を行うワーカスレッドの抽象クラス。
     */
    private abstract class WorkerThread extends Thread
    {
        
        /**
         * ソケットホルダー
         */
        protected SocketHolder socketHolder;
        
        /**
         * コンストラクター
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
                
                 //ワーカスレッドコレクションに追加する。
                addWorkerThread(this);
                
                // 入出力ストリームを開く。
                socketHolder.open();
                
                // 処理を開始する。
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
                    
                    // 入出力ストリームを閉じる。
                    socketHolder.close();
                    
                } catch (IOException e)
                {
                }

                // ワーカスレッドコレクションから削除する。
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
         * サブクラスで実装されるワーカスレッドの処理。
         */
        protected abstract void process() throws IOException;
        
    }
    
    /**
     * ハートビートを実行するワーカスレッド
     *
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    private class HeartbeatThread extends WorkerThread
    {

        /**
         * コンストラクタ
         */
        HeartbeatThread(Socket socket)
        {
            super(socket);
        }
        
        /**
         * クライアントとの間でハートビートメッセージの送受信を行う。
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
     * 時価情報を配信するワーカスレッドのサブクラス
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
                
                // 時価情報を送信する。
                if (feed(l_intSize))
                {
                    l_intCount = 0;
                } else
                {
                    l_intCount++;
                }
                
                // 10回以上連続で送信できない場合は、次の実行まで1秒待機
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
         * 時価情報を送信する。
         */
        synchronized boolean feed(int l_intSize) throws IOException
        {

            // 送信する時価情報が存在しない場合は終了
            if (dataHolder.isEmpty())
            {
                return false;
            }

            // 送信する時価情報を取得する。
            byte[] data = null;
            if (l_intSize == 0)
            {
                data = dataHolder.pop();
            } else
            {
                data = dataHolder.pop(l_intSize);
            }

            // 取得した時価情報を送信する。
            socketHolder.write(data);
            socketHolder.flush();

            // 次に送信するまで待機する。
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
         * 送信する時価情報をファイルから読み込む
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
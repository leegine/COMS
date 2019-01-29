head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.25.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	ThreadPool.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :スレッド プール(ThreadPool.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/08/06 劉(FLJ) 新規作成
 */
package webbroker3.system.tune.threadpool;

import java.util.*;

import webbroker3.util.*;

/*
 **
 * スレッド プール クラス.
 */
public class ThreadPool
    extends ThreadFactoryBase
{

    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(ThreadPool.class);

    /**
     * スレッド プール最大サイズの
     * デフォルト値(Integer.MAX_VALUE).
     **/
    public static final int DEFAULT_MAXIMUMPOOLSIZE = Integer.MAX_VALUE;

    /**
     * スレッド プール最小サイズの
     * デフォルト値（1）.
     **/
    public static final int DEFAULT_MINIMUMPOOLSIZE = 1;

    /**
     * 新タスクを待つ時間の
     * デフォルト値 (60000 milliseconds).
     **/
    public static final long DEFAULT_KEEPALIVETIME = 60 * 1000;

    /**
     * スレッド プール最大サイズ.
     */
    protected int maximumPoolSize_ = DEFAULT_MAXIMUMPOOLSIZE;

    /**
     * スレッド プール最小サイズ.
     **/
    protected int minimumPoolSize_ = DEFAULT_MINIMUMPOOLSIZE;

    /**
     * スレッド プール現在サイズ.
     **/
    protected int poolSize_ = 0;

    /**
     * スレッドが新タスクに待つ時間.
     **/
    protected long keepAliveTime_ = DEFAULT_KEEPALIVETIME;

    /**
     * シャットダウンフラグ
     **/
    protected boolean shutdown_ = false;

    /**
     * スレッドプール予定に処理するコマンドを管理するチャネル.
     **/
    protected final Channel handOff_;

    /**
     * 活動状態のスレッドのセット
     **/
    protected final Map threads_;

    /**
     * ブロックされるタスクを処理するハンドル
     *
     * */
    protected BlockedExecutionHandler blockedExecutionHandler_;

    /**
     * デフォルトコンストラクト
     */
    public ThreadPool()
    {
        this(new SynchronousChannel(), DEFAULT_MAXIMUMPOOLSIZE);
    }

    /**
     * スレッドプール最大サイズを指定するコンストラクト
     */
    public ThreadPool(int maxPoolSize)
    {
        this(new SynchronousChannel(), maxPoolSize);
    }

    /**
     * キューチャネルを指定するコンストラクト
     */
    public ThreadPool(Channel channel)
    {
        this(channel, DEFAULT_MAXIMUMPOOLSIZE);
    }

    /**
     * キューチャネルとスレッドプール最大サイズを指定するコンストラクト
     */
    public ThreadPool(Channel channel, int maxPoolSize)
    {
        maximumPoolSize_ = maxPoolSize;
        handOff_ = channel;
        runWhenBlocked();
        threads_ = new HashMap();
    }

    /**
     * スレッド プール最大サイズを取得.
     */
    public synchronized int getMaximumPoolSize()
    {
        return maximumPoolSize_;
    }

    /**
     * スレッド プール最大サイズを設定する.
     */
    public synchronized void setMaximumPoolSize(int newMaximum)
    {
        if (newMaximum <= 0)
        {
            throw new IllegalArgumentException();
        }
        maximumPoolSize_ = newMaximum;
    }

    /**
     * スレッド プール最小サイズを取得する.
     */
    public synchronized int getMinimumPoolSize()
    {
        return minimumPoolSize_;
    }

    /**
     * スレッド プール最小サイズを設定する.
     */
    public synchronized void setMinimumPoolSize(int newMinimum)
    {
        if (newMinimum < 0)
        {
            throw new IllegalArgumentException();
        }
        minimumPoolSize_ = newMinimum;
    }

    /**
     * スレッド プール現在サイズを取得する.
     **/
    public synchronized int getPoolSize()
    {
        return poolSize_;
    }

    /**
     * スレッドの新タスクに待つ時間を取得する.
     **/
    public synchronized long getKeepAliveTime()
    {
        return keepAliveTime_;
    }

    /**
     * スレッドの新タスクに待つ時間を設定する.
     **/
    public synchronized void setKeepAliveTime(long msecs)
    {
        keepAliveTime_ = msecs;
    }

    /**
     * ブロックされるタスクを処理するハンドルを取得する
     *
     * */
    public synchronized BlockedExecutionHandler getBlockedExecutionHandler()
    {
        return blockedExecutionHandler_;
    }

    /**
     * ブロックされるタスクを処理するハンドルを設定する
     *
     * */
    public synchronized void setBlockedExecutionHandler(BlockedExecutionHandler h)
    {
        blockedExecutionHandler_ = h;
    }

    /**
     * スレッドを作成し、コマンドを実行する
     **/
    protected void addThread(Runnable command)
    {
        Worker worker = new Worker(command);
        Thread thread = getThreadFactory().newThread(worker);
        threads_.put(worker, thread);
        ++poolSize_;
        if (log.ison())
        {
            log.debug("got thread to execute command.");
            log.debug("thread pool size=" + poolSize_);
            log.debug("thread=" + thread.getName());
            log.debug("command=" + command.getClass());
        }
        thread.start();
    }

    /**
     * 指定数量のスレッドを一括作成する
     **/
    public int createThreads(int numberOfThreads)
    {
        int ncreated = 0;
        for (int i = 0; i < numberOfThreads; ++i)
        {
            synchronized (this)
            {
                if (poolSize_ < maximumPoolSize_)
                {
                    addThread(null);
                    ++ncreated;
                }
                else
                {
                    break;
                }
            }
        }
        return ncreated;
    }

    /**
     * プール中のスレッドを全部中断する
     **/
    public synchronized void interruptAll()
    {
        for (Iterator it = threads_.values().iterator(); it.hasNext(); )
        {
            Thread t = (Thread) (it.next());
            t.interrupt();
        }
    }

    /**
     * スレッドプールをすぐシャットダウンする、すべでのスレッドを中断し、新しいスレッドを作成しないようにする
     **/
    public void shutdownNow()
    {
        shutdownNow(new DiscardWhenBlocked());
    }

    /**
     * スレッドプールをすぐシャットダウンする
     * すべでのスレッドを中断し、新しいスレッドを作成しないようにする
     **/
    public synchronized void shutdownNow(BlockedExecutionHandler handler)
    {
        setBlockedExecutionHandler(handler);
        shutdown_ = true; // don't allow new tasks
        minimumPoolSize_ = maximumPoolSize_ = 0; // don't make new threads
        interruptAll(); // interrupt all existing threads
    }

    /**
     * スレッドプールをシャットダウンする
     * 現在キュー最中のタスクを最後まで実行後、すべでのスレッドを中断する
     **/
    public void shutdownAfterProcessingCurrentlyQueuedTasks()
    {
        shutdownAfterProcessingCurrentlyQueuedTasks(new DiscardWhenBlocked());
    }

    /**
     * スレッドプールをシャットダウンする
     * 現在キュー最中のタスクを最後まで実行後、すべでのスレッドを中断する
     **/
    public synchronized void shutdownAfterProcessingCurrentlyQueuedTasks(
        BlockedExecutionHandler handler)
    {
        setBlockedExecutionHandler(handler);
        shutdown_ = true;
        if (poolSize_ == 0)
        { // disable new thread construction when idle
            minimumPoolSize_ = maximumPoolSize_ = 0;
        }
    }

    /**
     * スレッドプールをシャットダウンする後状態を返す
     **/
    public synchronized boolean isTerminatedAfterShutdown()
    {
        return shutdown_ && poolSize_ == 0;
    }

    /**
     * スレッドプールシャットダウンするまで、指定される時間単位で毎回スレッドプール状態をチェックする
     **/
    public synchronized boolean awaitTerminationAfterShutdown(long maxWaitTime) throws
        InterruptedException
    {
        if (!shutdown_)
        {
            throw new IllegalStateException();
        }
        if (poolSize_ == 0)
        {
            return true;
        }
        long waitTime = maxWaitTime;
        if (waitTime <= 0)
        {
            return false;
        }
        long start = System.currentTimeMillis();
        for (; ; )
        {
            wait(waitTime);
            if (poolSize_ == 0)
            {
                return true;
            }
            waitTime = maxWaitTime - (System.currentTimeMillis() - start);
            if (waitTime <= 0)
            {
                return false;
            }
        }
    }

    /**
     * スレッドプールシャットダウンするまで待つ
     **/
    public synchronized void awaitTerminationAfterShutdown() throws
        InterruptedException
    {
        if (!shutdown_)
        {
            throw new IllegalStateException();
        }
        while (poolSize_ > 0)
        {
            wait();
        }
    }

    /**
     * タスクキューに未処理タスク全部削除する。
     */
    public List drain()
    {
        boolean wasInterrupted = false;
        Vector tasks = new Vector();
        for (; ; )
        {
            try
            {
                Object x = handOff_.poll(0);
                if (x == null)
                {
                    break;
                }
                else
                {
                    tasks.addElement(x);
                }
            }
            catch (InterruptedException ex)
            {
                wasInterrupted = true; // postpone re-interrupt until drained
            }
        }
        if (wasInterrupted)
        {
            Thread.currentThread().interrupt();
        }
        return tasks;
    }

    /**
     * タスク処理済みワークスレッドをクリアする
     */
    protected synchronized void workerDone(Worker w)
    {
        threads_.remove(w);
        if (--poolSize_ == 0 && shutdown_)
        {
            maximumPoolSize_ = minimumPoolSize_ = 0; // disable new threads
            notifyAll(); // notify awaitTerminationAfterShutdown
        }

        // Create a replacement if needed
        if (poolSize_ == 0 || poolSize_ < minimumPoolSize_)
        {
            try
            {
                Runnable r = (Runnable) (handOff_.poll(0));
                if (r != null && !shutdown_)
                { // just consume task if shut down
                    addThread(r);
                }
            }
            catch (InterruptedException ie)
            {
                return;
            }
        }
    }

    /**
     * タスクキューから新タスクを取得する, シャットダウンの場合、 nullを返す
     **/
    protected Runnable getTask() throws InterruptedException
    {
        long waitTime;
        synchronized (this)
        {
            if (poolSize_ > maximumPoolSize_)
            { // Cause to die if too many threads
                return null;
            }
            waitTime = (shutdown_) ? 0 : keepAliveTime_;
        }
        if (waitTime >= 0)
        {
            return (Runnable) (handOff_.poll(waitTime));
        }
        else
        {
            return (Runnable) (handOff_.take());
        }
    }

    /**
     * ワークスレッドタスク.
     **/
    protected class Worker
        implements Runnable
    {
        protected Runnable firstTask_;

        protected Worker(Runnable firstTask)
        {
            firstTask_ = firstTask;
        }

        public void run()
        {
            try
            {
                Runnable task = firstTask_;
                firstTask_ = null; // enable GC

                if (task != null)
                {
                    task.run();
                    task = null;
                }

                while ( (task = getTask()) != null)
                {
                    task.run();
                    task = null;
                }
            }
            catch (InterruptedException ex)
            {} // fall through
            finally
            {
                workerDone(this);
            }
        }
    }

    /**
     * ブロックされるタスクを処理するハンドルインターフェース
     *
     * */
    public interface BlockedExecutionHandler
    {
        boolean blockedAction(Runnable command) throws InterruptedException;
    }

    /**
     * ブロックされるタスクをマインスレッドで処理するハンドルクラス
     *
     * */
    protected class RunWhenBlocked
        implements BlockedExecutionHandler
    {
        public boolean blockedAction(Runnable command)
        {
            command.run();
            return true;
        }
    }

    /**
     * ブロックされるタスクをマインスレッドで処理する
     *
     * */
    public void runWhenBlocked()
    {
        setBlockedExecutionHandler(new RunWhenBlocked());
    }

    /**
     * ブロックされるタスクをスレッド空いてるまで待つ処理するハンドルクラス
     *
     * */
    protected class WaitWhenBlocked
        implements BlockedExecutionHandler
    {
        public boolean blockedAction(Runnable command) throws InterruptedException
        {
            synchronized (ThreadPool.this)
            {
                if (shutdown_)
                {
                    return true;
                }
            }
            handOff_.put(command);
            return true;
        }
    }

    /**
     * ブロックされるタスクをスレッド空いてるまで待つ処理
     *
     * */
    public void waitWhenBlocked()
    {
        setBlockedExecutionHandler(new WaitWhenBlocked());
    }

    /**
     * ブロックされるタスクを廃棄処理するハンドルクラス
     *
     * */
    protected class DiscardWhenBlocked
        implements BlockedExecutionHandler
    {
        public boolean blockedAction(Runnable command)
        {
            return true;
        }
    }

    /**
     * ブロックされるタスクを廃棄処理する
     *
     * */
    public void discardWhenBlocked()
    {
        setBlockedExecutionHandler(new DiscardWhenBlocked());
    }

    /**
     * ブロックされるタスク発生する場合、全処理中断するハンドルクラス
     *
     * */
    protected class AbortWhenBlocked
        implements BlockedExecutionHandler
    {
        public boolean blockedAction(Runnable command)
        {
            throw new RuntimeException("Pool is blocked");
        }
    }

    /**
     * ブロックされるタスク発生する場合、全処理中断する
     *
     * */
    public void abortWhenBlocked()
    {
        setBlockedExecutionHandler(new AbortWhenBlocked());
    }

    /**
     * ブロックされるタスク発生する場合、一番古いタスクの処理中断するハンドルクラス
     *
     * */
    protected class DiscardOldestWhenBlocked
        implements BlockedExecutionHandler
    {
        public boolean blockedAction(Runnable command) throws InterruptedException
        {
            handOff_.poll(0);
            if (!handOff_.offer(command, 0))
            {
                command.run();
            }
            return true;
        }
    }

    /**
     * ブロックされるタスク発生する場合、一番古いタスクの処理中断する
     *
     * */
    public void discardOldestWhenBlocked()
    {
        setBlockedExecutionHandler(new DiscardOldestWhenBlocked());
    }

    /**
     * 与えるタスクにプールからスレッドを取得し、タスクを実行する
     **/
    public void execute(Runnable command) throws InterruptedException
    {
        for (; ; )
        {
            synchronized (this)
            {
                if (!shutdown_)
                {
                    int size = poolSize_;

                    if (log.ison())
                    {
                        log.debug("try to get thread to execute command...");
                    }
                    // Ensure minimum number of threads
                    if (size < minimumPoolSize_)
                    {
                        addThread(command);
                        return;
                    }

                    // Try to give to existing thread
                    if (handOff_.offer(command, 0))
                    {
                        return;
                    }

                    // If cannot handoff and still under maximum, create new thread
                    if (size < maximumPoolSize_)
                    {
                        addThread(command);
                        return;
                    }
                }
            }

            // Cannot hand off and cannot create -- ask for help
            if (getBlockedExecutionHandler().blockedAction(command))
            {
                return;
            }
        }
    }
}
@

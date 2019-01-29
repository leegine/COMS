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
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�X���b�h �v�[��(ThreadPool.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/08/06 ��(FLJ) �V�K�쐬
 */
package webbroker3.system.tune.threadpool;

import java.util.*;

import webbroker3.util.*;

/*
 **
 * �X���b�h �v�[�� �N���X.
 */
public class ThreadPool
    extends ThreadFactoryBase
{

    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(ThreadPool.class);

    /**
     * �X���b�h �v�[���ő�T�C�Y��
     * �f�t�H���g�l(Integer.MAX_VALUE).
     **/
    public static final int DEFAULT_MAXIMUMPOOLSIZE = Integer.MAX_VALUE;

    /**
     * �X���b�h �v�[���ŏ��T�C�Y��
     * �f�t�H���g�l�i1�j.
     **/
    public static final int DEFAULT_MINIMUMPOOLSIZE = 1;

    /**
     * �V�^�X�N��҂��Ԃ�
     * �f�t�H���g�l (60000 milliseconds).
     **/
    public static final long DEFAULT_KEEPALIVETIME = 60 * 1000;

    /**
     * �X���b�h �v�[���ő�T�C�Y.
     */
    protected int maximumPoolSize_ = DEFAULT_MAXIMUMPOOLSIZE;

    /**
     * �X���b�h �v�[���ŏ��T�C�Y.
     **/
    protected int minimumPoolSize_ = DEFAULT_MINIMUMPOOLSIZE;

    /**
     * �X���b�h �v�[�����݃T�C�Y.
     **/
    protected int poolSize_ = 0;

    /**
     * �X���b�h���V�^�X�N�ɑ҂���.
     **/
    protected long keepAliveTime_ = DEFAULT_KEEPALIVETIME;

    /**
     * �V���b�g�_�E���t���O
     **/
    protected boolean shutdown_ = false;

    /**
     * �X���b�h�v�[���\��ɏ�������R�}���h���Ǘ�����`���l��.
     **/
    protected final Channel handOff_;

    /**
     * ������Ԃ̃X���b�h�̃Z�b�g
     **/
    protected final Map threads_;

    /**
     * �u���b�N�����^�X�N����������n���h��
     *
     * */
    protected BlockedExecutionHandler blockedExecutionHandler_;

    /**
     * �f�t�H���g�R���X�g���N�g
     */
    public ThreadPool()
    {
        this(new SynchronousChannel(), DEFAULT_MAXIMUMPOOLSIZE);
    }

    /**
     * �X���b�h�v�[���ő�T�C�Y���w�肷��R���X�g���N�g
     */
    public ThreadPool(int maxPoolSize)
    {
        this(new SynchronousChannel(), maxPoolSize);
    }

    /**
     * �L���[�`���l�����w�肷��R���X�g���N�g
     */
    public ThreadPool(Channel channel)
    {
        this(channel, DEFAULT_MAXIMUMPOOLSIZE);
    }

    /**
     * �L���[�`���l���ƃX���b�h�v�[���ő�T�C�Y���w�肷��R���X�g���N�g
     */
    public ThreadPool(Channel channel, int maxPoolSize)
    {
        maximumPoolSize_ = maxPoolSize;
        handOff_ = channel;
        runWhenBlocked();
        threads_ = new HashMap();
    }

    /**
     * �X���b�h �v�[���ő�T�C�Y���擾.
     */
    public synchronized int getMaximumPoolSize()
    {
        return maximumPoolSize_;
    }

    /**
     * �X���b�h �v�[���ő�T�C�Y��ݒ肷��.
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
     * �X���b�h �v�[���ŏ��T�C�Y���擾����.
     */
    public synchronized int getMinimumPoolSize()
    {
        return minimumPoolSize_;
    }

    /**
     * �X���b�h �v�[���ŏ��T�C�Y��ݒ肷��.
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
     * �X���b�h �v�[�����݃T�C�Y���擾����.
     **/
    public synchronized int getPoolSize()
    {
        return poolSize_;
    }

    /**
     * �X���b�h�̐V�^�X�N�ɑ҂��Ԃ��擾����.
     **/
    public synchronized long getKeepAliveTime()
    {
        return keepAliveTime_;
    }

    /**
     * �X���b�h�̐V�^�X�N�ɑ҂��Ԃ�ݒ肷��.
     **/
    public synchronized void setKeepAliveTime(long msecs)
    {
        keepAliveTime_ = msecs;
    }

    /**
     * �u���b�N�����^�X�N����������n���h�����擾����
     *
     * */
    public synchronized BlockedExecutionHandler getBlockedExecutionHandler()
    {
        return blockedExecutionHandler_;
    }

    /**
     * �u���b�N�����^�X�N����������n���h����ݒ肷��
     *
     * */
    public synchronized void setBlockedExecutionHandler(BlockedExecutionHandler h)
    {
        blockedExecutionHandler_ = h;
    }

    /**
     * �X���b�h���쐬���A�R�}���h�����s����
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
     * �w�萔�ʂ̃X���b�h���ꊇ�쐬����
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
     * �v�[�����̃X���b�h��S�����f����
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
     * �X���b�h�v�[���������V���b�g�_�E������A���ׂł̃X���b�h�𒆒f���A�V�����X���b�h���쐬���Ȃ��悤�ɂ���
     **/
    public void shutdownNow()
    {
        shutdownNow(new DiscardWhenBlocked());
    }

    /**
     * �X���b�h�v�[���������V���b�g�_�E������
     * ���ׂł̃X���b�h�𒆒f���A�V�����X���b�h���쐬���Ȃ��悤�ɂ���
     **/
    public synchronized void shutdownNow(BlockedExecutionHandler handler)
    {
        setBlockedExecutionHandler(handler);
        shutdown_ = true; // don't allow new tasks
        minimumPoolSize_ = maximumPoolSize_ = 0; // don't make new threads
        interruptAll(); // interrupt all existing threads
    }

    /**
     * �X���b�h�v�[�����V���b�g�_�E������
     * ���݃L���[�Œ��̃^�X�N���Ō�܂Ŏ��s��A���ׂł̃X���b�h�𒆒f����
     **/
    public void shutdownAfterProcessingCurrentlyQueuedTasks()
    {
        shutdownAfterProcessingCurrentlyQueuedTasks(new DiscardWhenBlocked());
    }

    /**
     * �X���b�h�v�[�����V���b�g�_�E������
     * ���݃L���[�Œ��̃^�X�N���Ō�܂Ŏ��s��A���ׂł̃X���b�h�𒆒f����
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
     * �X���b�h�v�[�����V���b�g�_�E��������Ԃ�Ԃ�
     **/
    public synchronized boolean isTerminatedAfterShutdown()
    {
        return shutdown_ && poolSize_ == 0;
    }

    /**
     * �X���b�h�v�[���V���b�g�_�E������܂ŁA�w�肳��鎞�ԒP�ʂŖ���X���b�h�v�[����Ԃ��`�F�b�N����
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
     * �X���b�h�v�[���V���b�g�_�E������܂ő҂�
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
     * �^�X�N�L���[�ɖ������^�X�N�S���폜����B
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
     * �^�X�N�����ς݃��[�N�X���b�h���N���A����
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
     * �^�X�N�L���[����V�^�X�N���擾����, �V���b�g�_�E���̏ꍇ�A null��Ԃ�
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
     * ���[�N�X���b�h�^�X�N.
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
     * �u���b�N�����^�X�N����������n���h���C���^�[�t�F�[�X
     *
     * */
    public interface BlockedExecutionHandler
    {
        boolean blockedAction(Runnable command) throws InterruptedException;
    }

    /**
     * �u���b�N�����^�X�N���}�C���X���b�h�ŏ�������n���h���N���X
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
     * �u���b�N�����^�X�N���}�C���X���b�h�ŏ�������
     *
     * */
    public void runWhenBlocked()
    {
        setBlockedExecutionHandler(new RunWhenBlocked());
    }

    /**
     * �u���b�N�����^�X�N���X���b�h�󂢂Ă�܂ő҂�������n���h���N���X
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
     * �u���b�N�����^�X�N���X���b�h�󂢂Ă�܂ő҂���
     *
     * */
    public void waitWhenBlocked()
    {
        setBlockedExecutionHandler(new WaitWhenBlocked());
    }

    /**
     * �u���b�N�����^�X�N��p����������n���h���N���X
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
     * �u���b�N�����^�X�N��p����������
     *
     * */
    public void discardWhenBlocked()
    {
        setBlockedExecutionHandler(new DiscardWhenBlocked());
    }

    /**
     * �u���b�N�����^�X�N��������ꍇ�A�S�������f����n���h���N���X
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
     * �u���b�N�����^�X�N��������ꍇ�A�S�������f����
     *
     * */
    public void abortWhenBlocked()
    {
        setBlockedExecutionHandler(new AbortWhenBlocked());
    }

    /**
     * �u���b�N�����^�X�N��������ꍇ�A��ԌÂ��^�X�N�̏������f����n���h���N���X
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
     * �u���b�N�����^�X�N��������ꍇ�A��ԌÂ��^�X�N�̏������f����
     *
     * */
    public void discardOldestWhenBlocked()
    {
        setBlockedExecutionHandler(new DiscardOldestWhenBlocked());
    }

    /**
     * �^����^�X�N�Ƀv�[������X���b�h���擾���A�^�X�N�����s����
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

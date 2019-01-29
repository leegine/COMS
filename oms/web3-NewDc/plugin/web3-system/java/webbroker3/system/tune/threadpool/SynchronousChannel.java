head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.24.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	SynchronousChannel.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :同期チャネルクラス(SynchronousChannel.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/08/06 劉(FLJ) 新規作成
 */
package webbroker3.system.tune.threadpool;

/**
 * 同期チャネル
 */
public class SynchronousChannel
    implements Channel
{

    /**
     * キューノード失効状態を表現する定数
     **/
    protected static final Object CANCELLED = new Object();

    /**
     *  FIFOキュークラス.
     **/
    protected static class Queue
    {
        protected LinkedNode head;
        protected LinkedNode last;

        protected void enq(LinkedNode p)
        {
            if (last == null)
            {
                last = head = p;
            }
            else
            {
                last = last.next = p;
            }
        }

        protected LinkedNode deq()
        {
            LinkedNode p = head;
            if (p != null && (head = p.next) == null)
            {
                last = null;
            }
            return p;
        }
    }

    protected final Queue waitingPuts = new Queue();
    protected final Queue waitingTakes = new Queue();

    /**
     * 同期チャネルの容量がないので、０を返す
     **/
    public int capacity()
    {
        return 0;
    }

    /**
     * チャネル頭からアイテムを取得する、同期方式の場合、nullを返す
     **/
    public Object peek()
    {
        return null;
    }

    /**
     * チャネルにアイテムを入れる
     **/
    public void put(Object x) throws InterruptedException
    {
        if (x == null)
        {
            throw new IllegalArgumentException();
        }

        // Outer loop is to handle retry due to cancelled waiting taker
        for (; ; )
        {

            // Get out now if we are interrupted
            if (Thread.interrupted())
            {
                throw new InterruptedException();
            }

            // Exactly one of item or slot will be nonnull at end of
            // synchronized block, depending on whether a put or a take
            // arrived first.
            LinkedNode slot;
            LinkedNode item = null;

            synchronized (this)
            {
                // Try to match up with a waiting taker; fill and signal it below
                slot = waitingTakes.deq();

                // If no takers yet, create a node and wait below
                if (slot == null)
                {
                    waitingPuts.enq(item = new LinkedNode(x));
                }
            }

            if (slot != null)
            { // There is a waiting taker.
                // Fill in the slot created by the taker and signal taker to
                // continue.
                synchronized (slot)
                {
                    if (slot.value != CANCELLED)
                    {
                        slot.value = x;
                        slot.notify();
                        return;
                    }
                    // else the taker has cancelled, so retry outer loop
                }
            }

            else
            {
                // Wait for a taker to arrive and take the item.
                synchronized (item)
                {
                    try
                    {
                        while (item.value != null)
                        {
                            item.wait();
                        }
                        return;
                    }
                    catch (InterruptedException ie)
                    {
                        // If item was taken, return normally but set interrupt status
                        if (item.value == null)
                        {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        else
                        {
                            item.value = CANCELLED;
                            throw ie;
                        }
                    }
                }
            }
        }
    }

    /**
     * チャネルからアイテムを引出する
     **/
    public Object take() throws InterruptedException
    {

        for (; ; )
        {
            if (Thread.interrupted())
            {
                throw new InterruptedException();
            }

            LinkedNode item;
            LinkedNode slot = null;

            synchronized (this)
            {
                item = waitingPuts.deq();
                if (item == null)
                {
                    waitingTakes.enq(slot = new LinkedNode());
                }
            }

            if (item != null)
            {
                synchronized (item)
                {
                    Object x = item.value;
                    if (x != CANCELLED)
                    {
                        item.value = null;
                        item.next = null;
                        item.notify();
                        return x;
                    }
                }
            }

            else
            {
                synchronized (slot)
                {
                    try
                    {
                        for (; ; )
                        {
                            Object x = slot.value;
                            if (x != null)
                            {
                                slot.value = null;
                                slot.next = null;
                                return x;
                            }
                            else
                            {
                                slot.wait();
                            }
                        }
                    }
                    catch (InterruptedException ie)
                    {
                        Object x = slot.value;
                        if (x != null)
                        {
                            slot.value = null;
                            slot.next = null;
                            Thread.currentThread().interrupt();
                            return x;
                        }
                        else
                        {
                            slot.value = CANCELLED;
                            throw ie;
                        }
                    }
                }
            }
        }
    }

    /**
     * 時間範囲内、チャネルが受け入れる可能アイテムを入れる
     **/
    public boolean offer(Object x, long msecs) throws InterruptedException
    {
        if (x == null)
        {
            throw new IllegalArgumentException();
        }
        long waitTime = msecs;
        long startTime = 0; // lazily initialize below if needed

        for (; ; )
        {
            if (Thread.interrupted())
            {
                throw new InterruptedException();
            }

            LinkedNode slot;
            LinkedNode item = null;

            synchronized (this)
            {
                slot = waitingTakes.deq();
                if (slot == null)
                {
                    if (waitTime <= 0)
                    {
                        return false;
                    }
                    else
                    {
                        waitingPuts.enq(item = new LinkedNode(x));
                    }
                }
            }

            if (slot != null)
            {
                synchronized (slot)
                {
                    if (slot.value != CANCELLED)
                    {
                        slot.value = x;
                        slot.notify();
                        return true;
                    }
                }
            }

            long now = System.currentTimeMillis();
            if (startTime == 0)
            {
                startTime = now;
            }
            else
            {
                waitTime = msecs - (now - startTime);

            }
            if (item != null)
            {
                synchronized (item)
                {
                    try
                    {
                        for (; ; )
                        {
                            if (item.value == null)
                            {
                                return true;
                            }
                            if (waitTime <= 0)
                            {
                                item.value = CANCELLED;
                                return false;
                            }
                            item.wait(waitTime);
                            waitTime = msecs - (System.currentTimeMillis() - startTime);
                        }
                    }
                    catch (InterruptedException ie)
                    {
                        if (item.value == null)
                        {
                            Thread.currentThread().interrupt();
                            return true;
                        }
                        else
                        {
                            item.value = CANCELLED;
                            throw ie;
                        }
                    }
                }
            }
        }
    }

    /**
     * 時間範囲内、チャネルが引出可能アイテムを引出する
     **/
    public Object poll(long msecs) throws InterruptedException
    {
        long waitTime = msecs;
        long startTime = 0;

        for (; ; )
        {
            if (Thread.interrupted())
            {
                throw new InterruptedException();
            }

            LinkedNode item;
            LinkedNode slot = null;

            synchronized (this)
            {
                item = waitingPuts.deq();
                if (item == null)
                {
                    if (waitTime <= 0)
                    {
                        return null;
                    }
                    else
                    {
                        waitingTakes.enq(slot = new LinkedNode());
                    }
                }
            }

            if (item != null)
            {
                synchronized (item)
                {
                    Object x = item.value;
                    if (x != CANCELLED)
                    {
                        item.value = null;
                        item.next = null;
                        item.notify();
                        return x;
                    }
                }
            }

            long now = System.currentTimeMillis();
            if (startTime == 0)
            {
                startTime = now;
            }
            else
            {
                waitTime = msecs - (now - startTime);

            }
            if (slot != null)
            {
                synchronized (slot)
                {
                    try
                    {
                        for (; ; )
                        {
                            Object x = slot.value;
                            if (x != null)
                            {
                                slot.value = null;
                                slot.next = null;
                                return x;
                            }
                            if (waitTime <= 0)
                            {
                                slot.value = CANCELLED;
                                return null;
                            }
                            slot.wait(waitTime);
                            waitTime = msecs - (System.currentTimeMillis() - startTime);
                        }
                    }
                    catch (InterruptedException ie)
                    {
                        Object x = slot.value;
                        if (x != null)
                        {
                            slot.value = null;
                            slot.next = null;
                            Thread.currentThread().interrupt();
                            return x;
                        }
                        else
                        {
                            slot.value = CANCELLED;
                            throw ie;
                        }
                    }
                }
            }
        }
    }

}
@

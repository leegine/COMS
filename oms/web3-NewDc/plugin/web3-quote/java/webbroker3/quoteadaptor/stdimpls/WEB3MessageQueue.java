head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.41.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3MessageQueue.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quoteadaptor.stdimpls;

import webbroker3.util.WEB3LogUtility;

/**
 * �����T�[�o���z�M���ꂽ���������ꎞ�I�ɂ��߂Ă����L���[<BR>
 * <BR>
 * ���̃N���X�̊e���\�b�h�͓���������Ă���B
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
final class WEB3MessageQueue
{
    
    private static final WEB3LogUtility LOG = 
        WEB3LogUtility.getInstance(WEB3MessageQueue.class);

    private WEB3Message messages[];

    private int head; // index to pop 
    private int tail; // index to push
    private int last; // last index

    private final Object lock = new Object();

    private boolean block = true;

    /**
     * �R���X�g���N�^
     * 
     * Constructor. The value of the argument capacity needs
     * to be the power of two.
     *  
     * @@param size �L���[�̏����T�C�Y
     */
    WEB3MessageQueue(int size)
    {
        /**
         * Cehck if size is the power of two or not.
         */
        if ((size & (size - 1)) != 0)
        {
            throw new Error();
        }

        // DECLARE AND INITIALIZE ARRAY OF EVENTS
        messages = new WEB3Message[size];
        for (int i = 0; i < size; i++)
        {
            messages[i] = new WEB3Message();
        }

        last = size - 1;
    }

    /**
     * �L���[�Ɏ�������ǉ�����B<BR>
     * <BR>
     * �w�肵��WEB3Message����A�L���[�����ŕێ�����WEB3Message��
     * ������񂪃R�s�[�����B<BR>
     * <BR>
     * 'Push' (i.e. 'copy') an event onto the queue<BR>
     * <BR>
     * 
     * @@param src �ǉ����鎞�����
     */
    void push(WEB3Message src)
    {
        synchronized (lock)
        {
            //
            // if the queue is full, wait until
            // someone pop an element
            //
            while (((tail + 1) & last) == head)
            {
                try
                {
                    LOG.debug("Quote Message Queue is full now waiting...");
                    lock.wait();
                } catch (InterruptedException e)
                {
                }
            }

            messages[tail].copy(src);

            tail = (tail + 1) & last;
            lock.notify();
        }
    }

    /**
     * �L���[���玞�������擾����B<BR>
     * <BR>
     * �L���[�����ŕێ�����WEB3Message����A�w�肵��WEB3Message��
     * ������񂪃R�s�[�����B<BR>
     * <BR>
     * Populate a passed event with the data on the top of the queue
     * Blocks until an event is available.<BR>
     * <BR>
     * 
     * @@param WEB3Message �L���[����擾������������ێ�����WEB3Message
     * 
     */
    boolean pop(WEB3Message receivingMessage)
    {
        synchronized (lock)
        {
            //
            // if the queue is empty, wait until
            // someone push an element.
            //
            while (tail == head)
            {
                if (!block)
                {
                    return false;
                }
                // Wait for an event to be pushed in
                try
                {
                    LOG.debug("Quote Message Queue is empty now waiting...");
                    lock.wait();
                } catch (InterruptedException e)
                {
                }
            }

            //
            // if the queue is full now, wake up
            // the sleeping thread that is trying
            // to push an element after finishing
            // this sysnchronized block.
            //
            if (((tail + 1) & last) == head)
            {
                lock.notify();
            }

            receivingMessage.copy(messages[head]);
            head = (head + 1) & last;
            return true;
        }
    }

    boolean isEmpty()
    {
        synchronized (lock)
        {
            return (tail == head);
        }
    }

    void notifyStart()
    {
        synchronized (lock)
        {
            this.block = true;
        }
    }

    void notifyFinish()
    {
        synchronized (lock)
        {
            this.block = false;
            lock.notify();
        }
    }

    //
    // Flush all events from the queue
    // 
    void flush(boolean abortPop)
    {
        synchronized (lock)
        {
            tail = 0;
            head = 0;
        }
    }

}
@

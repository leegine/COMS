/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteMessageQueueクラス(DOTQuoteMessageQueue.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/23 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

import com.fitechlabs.fin.intellioms.util.Log;


/**
 * (時価メッセージキュー)<BR>
 * <BR>
 * 時価サーバから配信された時価メッセージのキュー。<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
class DOTQuoteMessageQueue
{
    
    /** ロガー */
    private static final Log log = Log.getLogger(DOTQuoteMessageQueue.class);
    
    /** キュー */
    private DOTQuoteMessage[] messages;
    
    private int head;
    private int tail;
    private int last;
    
    /** ブロックフラグ */
    private boolean block;
    
    /**
     * コンストラクタ<BR>
     * <BR>
     * パラメータ.sizeで指定するキューのサイズは2の乗数であること。<BR>
     * 
     * @param size キューの容量
     * @throws IllegalArgumentException 指定されたキューのサイズが2の乗数でない場合
     */
    DOTQuoteMessageQueue(int size)
    {
        
        if ((size & (size - 1)) != 0)
        {
            throw new IllegalArgumentException("size must be the power of 2.");
        }
        
        messages = new DOTQuoteMessage[size];
        for (int i = 0; i < size; i++)
        {
            messages[i] = new DOTQuoteMessage();
        }
        
        head = 0;
        tail = 0;
        last = size - 1;
        block = true;
        
        log.info(
            "QuoteMessageQueue initialized. "
            + "[size=" + size + "]");
        
    }
    
    /**
     * (push)<BR>
     * <BR>
     * 時価メッセージキューの最後にある要素に
     * パラメータで指定した時価メッセージの内容をコピーする。<BR>
     * 時価メッセージキューがすでにいっぱいのとき、
     * 時価メッセージキューが開始されいる場合は、
     * このメソッドの呼び出しはキューから要素がプットされるまでブロックされる。
     * 時価メッセージキューが開始されていない場合は、
     * キューに要素はプットされず、即座に返される。
     * 
     * @param source 時価メッセージ
     */
    synchronized void push(DOTQuoteMessage source)
    {
        
        while (((tail + 1) & last) == head)
        {
            
            if (!block)
            {
                return;
            }
            
            try
            {
                
                log.debug("QuoteMessageQueue is full.");
                wait();
                
            } catch (InterruptedException ie)
            {
            }
        }
        
        messages[tail].copy(source);
        tail = (tail + 1) & last;
        notify();
        
    }
    
    /**
     * (pop)<BR>
     * <BR>
     * 時価メッセージキューの先頭にある要素から
     * パラメータで指定した時価メッセージに内容をコピーする。<BR>
     * 時価メッセージキューが空のときで、
     * キューが既に開始されている場合は
     * このメソッドの呼び出しは、キューに要素がプットされるまでブロックされる。
     * キューが開始されていない場合は即座に<code>false</code>を返す。 
     * 
     * @param receivingMessage ポップした時価メッセージの内容がコピーされる時価メッセージ
     * @return
     */
    synchronized boolean pop(DOTQuoteMessage receivingMessage)
    {
        
        while (tail == head)
        {
            
            if (!block)
            {
                return false;
            }
            
            try
            {
                
                log.debug("QuoteMessageQueue is empty.");
                wait();
                
            } catch (InterruptedException ie)
            {
            }
            
        }
        
        if (((tail + 1) & last) == head)
        {
            notify();
        }
        
        receivingMessage.copy(messages[head]);
        head = (head + 1) & last;
        return true;
        
    }
    
    /**
     * (isEmpty)<BR>
     * <BR>
     * @return 時価メッセージキューが空の場合は<code>true</code>、
     *  それ以外の場合は<code>false</code>を返す。
     */
    synchronized boolean isEmpty()
    {
        return (tail == head);
    }
    
    /**
     * (start)<BR>
     * <BR>
     * キューを開始する。
     */
    synchronized void start()
    {
        block = true;
        log.debug("QuoteMessageQueue started.");
    }
    
    /**
     * (stop)<BR>
     * <BR>
     * キューを停止する。
     */
    synchronized void stop()
    {
        block = false;
        notify();
        log.debug("QuoteMessageQueue stoped.");
    }

}

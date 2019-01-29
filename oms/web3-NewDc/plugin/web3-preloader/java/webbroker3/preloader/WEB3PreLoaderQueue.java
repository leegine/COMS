head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.19.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d91339b43f9;
filename	WEB3PreLoaderQueue.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 実行するプリローダーのインスタンスを保持するキュー(WEB3PreLoaderQueue.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/19 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.preloader;

import java.util.LinkedList;
import java.util.NoSuchElementException;


/**
 * 実行するプリローダーのインスタンスを保持するキュー。
 * 
 * プリロードを実行する複数のスレッドから参照される。 
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
class WEB3PreLoaderQueue
{
    
    /**
     * プリローダーを保持するリスト
     */
    private LinkedList queue;
    
    /**
     * コンストラクタ
     */
    WEB3PreLoaderQueue()
    {
        queue = new LinkedList();
    }
    
    /**
     * キューの最後にプリローダーを追加する。
     */
    synchronized void push(WEB3PreLoader l_preLoader)
    {
        queue.addLast(l_preLoader);
    }
    
    /**
     * キューの先頭からプリローダーを取得し、先頭のプリローダーをキューから削除する。
     * キューが空の場合は、<code>null</code>を返す。
     */
    synchronized WEB3PreLoader pop()
    {
        WEB3PreLoader l_preLoader = null; 
        try
        {
            l_preLoader = (WEB3PreLoader) queue.removeFirst();
        } catch (NoSuchElementException e)
        {
        }
        return l_preLoader;
    }
    
    /**
     * キューがプリローダーを保持しない場合に<code>true</code>を返す。
     */
    synchronized boolean isEmpty()
    {
        return queue.isEmpty();
    }

}
@

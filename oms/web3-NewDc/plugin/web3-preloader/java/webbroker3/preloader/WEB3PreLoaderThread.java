head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.19.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d91339b43f9;
filename	WEB3PreLoaderThread.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : プリロードを実行するスレッド(WEB3PreLoaderThread.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/19 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.preloader;

import webbroker3.util.WEB3LogUtility;


/**
 * プリロードを実行するスレッド。
 * 
 * 登録されたキューが空になるまで、キューからプリローダーを取得し、
 * 取得したプリローダーを実行する。
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
class WEB3PreLoaderThread extends Thread
{
    
    /**
     * ログユーティリティ
     */
    private static final WEB3LogUtility LOG = WEB3LogUtility.getInstance(WEB3PreLoaderThread.class);
    
    private static final boolean DBG = LOG.ison();
    
    /**
     * 実行するプリローダーを保持するキュー
     */
    private WEB3PreLoaderQueue queue;
    
    /**
     * このスレッドのステータス
     */
    private boolean isActive;
    
    public void run()
    {
        
        synchronized(this)
        {
            if (queue == null)
            {
                throw new IllegalStateException("preloader queue is not registered.");
            }
            isActive = true;
            
            if (DBG)
            {
                LOG.debug(getName() + " start loading.");
            }
            
        }
        
        try {
            
            while(isActive)
            {
                synchronized(this)
                {
                    WEB3PreLoader l_preLoader = queue.pop();
                    if (l_preLoader != null)
                    {
                        l_preLoader.execute();
                    } else
                    {
                        isActive = false;
                        break;
                    }
                }
            }
            
        } catch (Throwable l_th)
        {
            LOG.error("Unexpected exception occured.", l_th);
        }
        
        if (DBG)
        {
            LOG.debug(getName() + " stop loading.");
        }
            
    }
    
    /**
     * 実行するプリローダーを保持するキューを設定する。
     * 
     * @@param l_queue キュー
     */
    synchronized void setQueue(WEB3PreLoaderQueue l_queue)
    {
        this.queue = l_queue;
    }
    
    /**
     * このスレッドのステータスを返す。
     * 登録されているキューが空になった場合、<code>true</code>を返す。
     * 
     * @@return ステータス
     */
    synchronized boolean isActive()
    {
        return this.isActive;
    }
    
    
    
}
@

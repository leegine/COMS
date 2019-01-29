head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.19.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d91339b43f9;
filename	WEB3PreLoaderManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3PreLoaderManagerImplクラス(WEB3PreLoaderManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/07 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.preloader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

/**
 * WEB3PreLoaderManagerの実装クラス
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
class WEB3PreLoaderManagerImpl implements WEB3PreLoaderManager
{
    
    private static final WEB3LogUtility LOG = 
        WEB3LogUtility.getInstance(WEB3PreLoaderManagerImpl.class);
    
    private static final boolean DBG = LOG.ison();
    
    /**
     * プリロードを実行するスレッドに設定されるシーケンシャルな番号
     */
    private static int seqNo = 0;
    
    /**
     * プリローダーを保持するマップ
     * KEY：テーブル名
     * VALUE：プリローダーを保持するList
     */
    private Map preLoaderHolder;
    
    /**
     * コンストラクタ
     */
    WEB3PreLoaderManagerImpl()
    {
        preLoaderHolder = new TreeMap();
    }

    /**
     * @@see webbroker3.preloader.WEB3PreLoaderManager#registerPreLoader(webbroker3.preloader.WEB3PreLoader)
     */
    public synchronized void registerPreLoader(WEB3PreLoader l_preLoader)
    {
        String l_strTableName = l_preLoader.getRowType().getTableName();
        List l_lisPreLoaders = (List) preLoaderHolder.get(l_strTableName);
        if (l_lisPreLoaders == null)
        {
            l_lisPreLoaders = new ArrayList();
            preLoaderHolder.put(l_strTableName, l_lisPreLoaders);
        }
        l_lisPreLoaders.add(l_preLoader);
    }

    /**
     * @@see webbroker3.preloader.WEB3PreLoaderManager#getPreLoader(java.lang.String)
     */
    public synchronized WEB3PreLoader[] getPreLoader(String l_strTableName)
    {
        List l_lisPreLoaders = (List) preLoaderHolder.get(l_strTableName);
        if (l_lisPreLoaders != null && l_lisPreLoaders.size() > 0)
        {
            return (WEB3PreLoader[]) l_lisPreLoaders.toArray(
                    new WEB3PreLoader[l_lisPreLoaders.size()]);
        } else {
            return null;
        }
    }

    /**
     * @@see webbroker3.preloader.WEB3PreLoaderManager#load(java.lang.String)
     */
    public synchronized void load(String l_strTableName)
    {
        String[] l_strTableNames = { l_strTableName };
        WEB3PreLoaderQueue l_queue = createQueue(l_strTableNames);
        load(l_queue, 1);
    }

    /**
     * @@see webbroker3.preloader.WEB3PreLoaderManager#load(java.lang.String[], int)
     */
    public synchronized void load(String[] l_strTableNames, int l_intThreadsSize)
    {
        WEB3PreLoaderQueue l_queue = createQueue(l_strTableNames);
        load(l_queue, l_intThreadsSize);
    }
    
    /**
     * @@see webbroker3.preloader.WEB3PreLoaderManager#loadAll()
     */
    public synchronized void loadAll() 
    {
        WEB3PreLoaderQueue l_queue = createQueue();
        int l_intThreadsSize = getThreadsSize();
        load(l_queue, l_intThreadsSize);
    }
    
    // private methods --------------------------------------------------------
    
    /**
     * 指定されたキューに登録されているプリローダーを指定したスレッド数で実行する。
     * 
     * @@param l_queue 実行するプリローダーを保持するキュー
     * @@param l_intThreadsSize スレッド数
     */
    private void load(WEB3PreLoaderQueue l_queue, int l_intThreadsSize)
    {
        
        if (l_queue.isEmpty())
        {
            return;
        }

        boolean l_hasAlreadyStarted = false;
        WEB3PreLoaderThread[] l_threads = null;
        long l_lngStart = System.currentTimeMillis();
        
        try {
            
            // キャッシュ統計収集開始
            l_hasAlreadyStarted = WEB3CacheStatisticsUtils.startCollecting();
            
            // プリロード実行スレッドを生成し、プリロード開始
            l_threads = new WEB3PreLoaderThread[l_intThreadsSize];
            for (int i = 0; i < l_threads.length; i++)
            {
                l_threads[i] = new WEB3PreLoaderThread();
                l_threads[i].setName("PreLoader-" + getSeqNo());
                l_threads[i].setQueue(l_queue);
                l_threads[i].start();
            }
            
            // プリロード完了まで待機@
            while(true)
            {
                
                // キューが空かつ、すべてのスレッドが停止した場合、待機@終了
                if (l_queue.isEmpty())
                {
                    boolean l_isFinished = true;
                    for (int i = 0; i < l_threads.length; i++)
                    {
                        if (l_threads[i].isActive())
                        {
                            l_isFinished = false;
                            break;
                        }
                    }
                    
                    if (l_isFinished)
                    {
                        break;
                    }
                    
                }
                
                try
                {
                    Thread.sleep(100);
                    continue;
                } catch (InterruptedException e)
                {
                }
            }

        } finally {
            
            // スレッドの参照を開放
            if (l_threads != null)
            {
                for (int i = 0; i < l_threads.length; i++)
                {
                    l_threads[i] = null;
                }
                l_threads = null;
            }
            
            // キャッシュ統計収集終了
            WEB3CacheStatisticsUtils.stopCollecting(l_hasAlreadyStarted);
            
        }
        
        long l_lngEnd = System.currentTimeMillis();
        
        if (DBG)
        {
            LOG.debug("process time = " + ( l_lngEnd - l_lngStart ) + " [msec]");
        }
        
    }

    /**
     * スレッド名に設定するシーケンシャルな値を取得する。
     */
    private int getSeqNo()
    {
        synchronized (WEB3PreLoaderManagerImpl.class)
        {
            seqNo++;
            return seqNo;
        }
    }

    /**
     * SYSTEM_PREFERENCESテーブルに設定されているスレッド数を取得する。
     */
    private int getThreadsSize()
    {
        int l_intThreadsSize = WEB3PreLoaderPlugin.DEFAULT_PRELOADER_THREADS_SIZE;
        String l_strValue = GtlUtils.getTradingSystem().getPreference(WEB3PreLoaderPlugin.PRELOADER_THREADS_SIZE_PREF_NAME);
        if (l_strValue != null)
        {
            try {
                l_intThreadsSize = Integer.parseInt(l_strValue);
            } catch (NumberFormatException l_nfe)
            {
            }
        }
        
        if (DBG)
        {
            LOG.debug("PreLoader threads size = " + l_intThreadsSize);
        }
        
        return l_intThreadsSize;
    }
    
    /**
     * 指定したテーブルに関連付けられているプリローダーを保持するキューを生成する。
     * 
     * @@param l_strTableNames テーブル名
     * @@return 生成されたキュー
     */
    private WEB3PreLoaderQueue createQueue(String[] l_strTableNames)
    {
        List l_preLoaders = new ArrayList();
        if (!preLoaderHolder.isEmpty())
        {
            for (int i = 0; i < l_strTableNames.length; i++)
            {
                List l_temp = (List) preLoaderHolder.get(l_strTableNames[i]);
                if (l_temp != null && !l_temp.isEmpty())
                {
                    l_preLoaders.addAll(l_temp);
                }
            }
        }
        return createQueue(l_preLoaders);
    }
    
    /**
     * 登録されているすべてのプリローダーを保持するキューを生成する。
     * 
     * @@return 生成されたキュー
     */
    private WEB3PreLoaderQueue createQueue()
    {
        List l_preLoaders = new ArrayList();
        if (!preLoaderHolder.isEmpty())
        {
            for (Iterator l_it = preLoaderHolder.values().iterator(); l_it.hasNext();)
            {
                List l_temp = (List) l_it.next();
                if (l_temp != null && !l_temp.isEmpty())
                {
                    l_preLoaders.addAll(l_temp);
                }
            }
        }
        return createQueue(l_preLoaders);
    }

    /**
     * 指定したプリローダーのリストを保持するキューを生成する。
     * 
     * @@param l_preLoaders プリローダーのリスト
     * @@return 生成されたキュー
     */
    private WEB3PreLoaderQueue createQueue(List l_preLoaders)
    {
        WEB3PreLoaderQueue l_queue = new WEB3PreLoaderQueue();
        for (Iterator l_it = l_preLoaders.iterator(); l_it.hasNext();)
        {
            WEB3PreLoader l_preLoader = (WEB3PreLoader) l_it.next();
            l_queue.push(l_preLoader);
        }
        return l_queue;
    }

}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.25.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3ThreadPoolPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : スレッドプールプラグインクラス(WEB3ThreadPoolPlugin.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/03/07 劉 新規作成
 */
package webbroker3.system.tune.threadpool;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

/**
 * スレッドプールプラグインクラス
 *
 * @@author 劉
 * @@version 1.0
 */
public final class WEB3ThreadPoolPlugin
    extends Plugin
{

    /**
     * スレッド プール
     */
    private static ThreadPool pool;

    /**
     * スレッド プールのDEFAULTサイズ
     */
    private final static int DEFAULT_THREAD_POOL_SIZE = 20;

    /**
     * スレッド プールサイズの設定キー
     */
    private final static String STR_THREAD_POOL_SIZE_KEY = "system.thread.pool.size";

    static
    {
        //スレッド プール初期化
        int l_intThreadSize = DEFAULT_THREAD_POOL_SIZE;
        String l_strThreadSize = GtlUtils.getTradingSystem().getPreference(
            STR_THREAD_POOL_SIZE_KEY);
        if (l_strThreadSize != null)
        {
            try
            {
                l_intThreadSize = Integer.parseInt(l_strThreadSize);
            }
            catch (Exception e)
            {}
        }
        pool = new ThreadPool(l_intThreadSize);
    }

    /**
     * コンストラクタ。
     */
    public WEB3ThreadPoolPlugin()
    {
    }

    public static ThreadPool getThreadPool()
    {
        return pool;
    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception
    {
        plug(WEB3ThreadPoolPlugin.class);
    }

    /**
     * プラグイン処理。
     */
    public static void onPlug() throws Exception
    {
        Services.registerService(WEB3AsynExecuteService.class,
                                 new WEB3AsynExecuteServiceImpl());

    }

    /**
     * プラグイン終了処理。
     */
    public static void onUnplug() throws Exception
    {
        pool.shutdownNow();
    }

}
@

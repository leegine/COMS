head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.48.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3SockPoolCtrlPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3SockPoolCtrlPluginプラグインのプラグインクラス(WEB3SockPoolCtrlPlugin.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/08/18 劉 (FLJ)新規作成
                  : 2008/10/24 毛 (FTL) Invalidationエラー対応
 */
package webbroker3.sockpoolctrl;

import java.lang.reflect.Method;

import com.fitechlabs.dbind.impl.InvHeartbeats;
import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.sockpoolctrl.handler.*;
import webbroker3.sockpoolctrl.message.*;
import webbroker3.sockpoolctrl.service.delegate.*;
import webbroker3.sockpoolctrl.service.delegate.stdimpls.*;
import webbroker3.util.*;

/**
 * <p>
 * WEB3SockPoolCtrlPluginプラグインのプラグインクラス。<br>
 * <br>
 * </p>
 *
 * @@author FLJ劉
 * @@version 1.0
 */
public class WEB3SockPoolCtrlPlugin
    extends Plugin
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SockPoolCtrlPlugin.class);

    private static boolean isPlugged = false;

    /**
     * インバリデーション終了待機@時間
     */
    private final static long DEFAULT_SLEEP_MILLISECS = 5000;

    /**
     * インバリデーション終了待機@時間プリファ@レンスキー
     */
    private final static String INVALIDATION_SAFE_STOP_SLEEP_KEY = "invalidation.safe.stop.sleep.millisecs";

    /**
     * デフォルトコンストラクタ。
     */
    public WEB3SockPoolCtrlPlugin()
    {
    }

    public static void plug() throws Exception
    {
        plug(WEB3SockPoolCtrlPlugin.class);
    }

    /**
     * このプラグインクラスのメインメソッド
     */
    public static void onPlug() throws Exception
    {

        KernelPlugin.plug();

        regClass(WEB3SockPoolCtrlRequest.class);
        regClass(WEB3SockPoolCtrlResponse.class);

        regHandler(
            WEB3SockPoolCtrlPlugin.class,
            WEB3SockPoolCtrlRequest.class,
            WEB3SockPoolCtrlHandler.class,
            "handleWEB3SockPoolCtrlRequest");

        Services.registerService(
            WEB3SockPoolControlService.class,
            new WEB3SockPoolControlServiceImpl());

        isPlugged = true;

        log.info("WEB3SockPoolCtrlPlugin was plugged.");
    }

    public static boolean isPlugged()
    {
        return isPlugged;
    }

    public static void onUnplug() throws Exception
    {

        try
        {
            log.info("on unplugging WEB3SockPoolCtrlPlugin...");

            long l_lngSleepTime = DEFAULT_SLEEP_MILLISECS;
            String l_strSleepTime = GtlUtils.getTradingSystem().getPreference(
                    INVALIDATION_SAFE_STOP_SLEEP_KEY);
            if (l_strSleepTime != null)
            {
                try
                {
                    l_lngSleepTime = Long.parseLong(l_strSleepTime);
                }
                catch (NumberFormatException e)
                {
                    log.error(e.getMessage(), e);
                }
            }

            synchronized (com.fitechlabs.dbind.impl.InvServer.class)
            {

                log.error("stopping InvHeartbeats ...");
                Class heartbeats = InvHeartbeats.class;
                Method method = heartbeats.getDeclaredMethod("stopAll", new Class[0]);
                method.setAccessible(true);
                method.invoke(heartbeats, new Object[0]);
                log.error("stopped  InvHeartbeats successfully.");

                log.info("sleep millisecs=" + l_lngSleepTime);
                try
                {
                    Thread.sleep(l_lngSleepTime);
                }
                catch (InterruptedException e)
                {
                    log.error(e.getMessage(), e);
                }
            }

            isPlugged = false;
            
            log.info("WEB3SockPoolCtrlPlugin was unplugged.");
        }
        catch(Throwable t)
        {
            log.error("error on unplugging WEB3SockPoolCtrlPlugin. "  + t.getMessage(), t);
        }
    }

}
@

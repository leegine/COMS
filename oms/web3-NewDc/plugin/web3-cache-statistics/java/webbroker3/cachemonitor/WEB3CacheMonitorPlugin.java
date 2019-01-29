head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.51.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheMonitorPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3CacheMonitorPluginプラグインのプラグインクラス(WEB3CacheMonitorPlugin.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/04/18 劉 (FLJ)新規作成
 */
package webbroker3.cachemonitor;

import com.fitechlabs.xtrade.kernel.boot.*;
import webbroker3.cachemonitor.data.*;
import webbroker3.cachemonitor.handler.*;
import webbroker3.cachemonitor.message.*;
import webbroker3.cachemonitor.service.delegate.*;
import webbroker3.cachemonitor.service.delegate.stdimpls.*;
import webbroker3.util.*;

/**
 * <p>
 * WEB3CacheMonitorPluginプラグインのプラグインクラス。<br>
 * <br>
 * </p>
 *
 * @@author FLJ劉
 * @@version 1.0
 */
public class WEB3CacheMonitorPlugin
    extends Plugin
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3CacheMonitorPlugin.class);

    private static boolean isPlugged = false;

    /**
     * デフォルトコンストラクタ。
     */
    public WEB3CacheMonitorPlugin()
    {
    }

    public static void plug() throws Exception
    {
        plug(WEB3CacheMonitorPlugin.class);
    }

    /**
     * このプラグインクラスのメインメソッド
     */
    public static void onPlug() throws Exception
    {

        KernelPlugin.plug();

        WEB3CacheMonitorSessionDatabaseExtensions.plug();

        regClass(WEB3CacheMonitorRequest.class);
        regClass(WEB3CacheMonitorResponse.class);

        regHandler(
            WEB3CacheMonitorPlugin.class,
            WEB3CacheMonitorRequest.class,
            WEB3CacheMonitorHandler.class,
            "handleWEB3CacheMonitorRequest");

        Services.registerService(
            WEB3CacheMonitorService.class,
            new WEB3CacheMonitorServiceImpl());

        isPlugged = true;

        log.info("WEB3CacheMonitorPlugin was plugged.");
    }

    public static boolean isPlugged()
    {
        return isPlugged;
    }

}
@

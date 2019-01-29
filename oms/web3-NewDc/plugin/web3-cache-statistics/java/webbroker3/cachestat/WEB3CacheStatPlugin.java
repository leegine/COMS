head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.52.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheStatPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3CacheStatPluginクラス(WEB3CacheStatPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/16 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.cachestat;

import webbroker3.cachestat.handler.WEB3CacheStatMessageHandler;
import webbroker3.cachestat.message.WEB3CacheStatClearRequest;
import webbroker3.cachestat.message.WEB3CacheStatGetStatisticsRequest;
import webbroker3.cachestat.message.WEB3CacheStatGetStatisticsResponse;
import webbroker3.cachestat.message.WEB3CacheStatStartCollectingRequest;
import webbroker3.cachestat.message.WEB3CacheStatStopCollectingRequest;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import webbroker3.cachemonitor.WEB3CacheMonitorPlugin;
import webbroker3.sockpoolctrl.WEB3SockPoolCtrlPlugin;
import webbroker3.quoteprice.WEB3QuotePricePlugin;
import webbroker3.quotecheck.WEB3QuoteCheckPlugin;

/**
 * キャッシュ統計プラグインのプラグインクラス
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3CacheStatPlugin extends Plugin
{

    private static final WEB3LogUtility LOG = WEB3LogUtility.getInstance(WEB3CacheStatPlugin.class);

    public WEB3CacheStatPlugin() throws Exception
    {
        super();
    }

    public static void plug() throws Exception
    {
        plug(WEB3CacheStatPlugin.class);
    }

    public static void onPlug() throws Exception
    {

        regClass(WEB3CacheStatStartCollectingRequest.class);
        regClass(WEB3CacheStatStopCollectingRequest.class);
        regClass(WEB3CacheStatClearRequest.class);
        regClass(WEB3CacheStatGetStatisticsRequest.class);
        regClass(WEB3CacheStatGetStatisticsResponse.class);

        regHandler(
            WEB3CacheStatPlugin.class,
            WEB3CacheStatStartCollectingRequest.class,
            WEB3CacheStatMessageHandler.class,
            "startCollecting");

        regHandler(
            WEB3CacheStatPlugin.class,
            WEB3CacheStatStopCollectingRequest.class,
            WEB3CacheStatMessageHandler.class,
            "stopCollecting");

        regHandler(
            WEB3CacheStatPlugin.class,
            WEB3CacheStatClearRequest.class,
            WEB3CacheStatMessageHandler.class,
            "clear");

        regHandler(
            WEB3CacheStatPlugin.class,
            WEB3CacheStatGetStatisticsRequest.class,
            WEB3CacheStatMessageHandler.class,
            "getStastics");

        WEB3CacheMonitorPlugin.plug();
        WEB3SockPoolCtrlPlugin.plug();
        WEB3QuotePricePlugin.plug();
        WEB3QuoteCheckPlugin.plug();

        LOG.info("***** WEB3CacheStatPlugin plugged. *****");

    }

}
@

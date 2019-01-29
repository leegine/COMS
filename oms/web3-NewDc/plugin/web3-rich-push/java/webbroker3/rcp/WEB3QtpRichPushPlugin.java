head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.25.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3QtpRichPushPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3RichPushPluginプラグインのプラグインクラス(WEB3RichPushPlugin.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/06 劉 (FLJ)新規作成
                  : 2009/06/03 毛 (FTL) 岩井対応
 */
package webbroker3.rcp;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.rcp.data.qtp.WEB3QtpRcpSessionDatabaseExtensions;
import webbroker3.rcp.handler.WEB3QtpRichPushMainHandler;
import webbroker3.rcp.message.WEB3QtpRichPushMainRequest;
import webbroker3.rcp.message.WEB3QtpRichPushMainResponse;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginChangeCancelUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginContUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginLapseUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpChangeCancelUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpContUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpLapseUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushGateWayService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushMainService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushPersistentDataManager;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushSwapOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpRichPushEquityMarginChangeCancelUnitServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpRichPushEquityMarginContUnitServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpRichPushEquityMarginLapseUnitServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpRichPushEquityMarginOrderAcceptUnitServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpRichPushFuOpChangeCancelUnitServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpRichPushFuOpContUnitServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpRichPushFuOpLapseUnitServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpRichPushFuOpOrderAcceptUnitServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpRichPushGateWayServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpRichPushMainServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpRichPushPersistentDataManagerImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpRichPushSwapOrderAcceptUnitServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * <p>
 * WEB3RichPushPluginプラグインのプラグインクラス。<br>
 * <br>
 * </p>
 *
 * @@author FLJ劉
 * @@version 1.0
 */
public class WEB3QtpRichPushPlugin
    extends Plugin
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QtpRichPushPlugin.class);

    private static boolean isPlugged = false;

    /** 一回最大プッシュメッセージ数 */
    private static final int DEFAULT_MAX_PUSH_MESSAGES_SIZE = 100;

    /** 一回最大プッシュメッセージ数設定キー */
    private final static String STR_MAX_PUSH_MESSAGES_SIZE_KEY =
        "qtp.rich.push.max.messages.size";

    /** 一回最大プッシュメッセージ数 */
    private static int max_push_messages_size = DEFAULT_MAX_PUSH_MESSAGES_SIZE;

    /** プッシュオブジェクトソート用コンテクストキー*/
    public static final String PUSH_OBJECT_CONTEXT_ATTRIBUTE_NAME =
        "webbroker3.qtp.rcp.pushobjectcontext";

    /**
     * デフォルトコンストラクタ。
     */
    public WEB3QtpRichPushPlugin()
    {
    }

    public static void plug() throws Exception
    {
        plug(WEB3QtpRichPushPlugin.class);
    }

    /**
     * このプラグインクラスのメインメソッド
     */
    public static void onPlug() throws Exception
    {

        KernelPlugin.plug();

        WEB3QtpRcpSessionDatabaseExtensions.plug();

        // QTPリッチクライアントプッシュメインメッセージの登録
        regClass(WEB3QtpRichPushMainRequest.class);
        regClass(WEB3QtpRichPushMainResponse.class);

        // QTPリッチクライアントプッシュメインハンドラーの登録
        regHandler(WEB3QtpRichPushPlugin.class,
                   WEB3QtpRichPushMainRequest.class,
                   WEB3QtpRichPushMainHandler.class,
                   "handleQtpRichPushMainRequest");

        // QTPリッチクライアントプッシュメインサービス
        Services.registerService(
            WEB3QtpRichPushMainService.class,
            new WEB3QtpRichPushMainServiceImpl());

        // Type<00> QTPリッチクライアントプッシュ株式注文受付サービス
        Services.registerService(
            WEB3QtpRichPushEquityMarginOrderAcceptUnitService.class,
            new WEB3QtpRichPushEquityMarginOrderAcceptUnitServiceImpl());

        // Type<01> QTPリッチクライアントプッシュ株式信用現引現渡受付サービス
        Services.registerService(
            WEB3QtpRichPushSwapOrderAcceptUnitService.class,
            new WEB3QtpRichPushSwapOrderAcceptUnitServiceImpl());

        // Type<02> QTPリッチクライアントプッシュ株式訂正取消通知サービス
        Services.registerService(
            WEB3QtpRichPushEquityMarginChangeCancelUnitService.class,
            new WEB3QtpRichPushEquityMarginChangeCancelUnitServiceImpl());

        // Type<03> QTPリッチクライアントプッシュ株式出来通知サービス
        Services.registerService(
            WEB3QtpRichPushEquityMarginContUnitService.class,
            new WEB3QtpRichPushEquityMarginContUnitServiceImpl());

        // Type<04> QTPリッチクライアントプッシュ株式失効通知サービス
        Services.registerService(
            WEB3QtpRichPushEquityMarginLapseUnitService.class,
            new WEB3QtpRichPushEquityMarginLapseUnitServiceImpl());

        // Type<10> QTPリッチクライアントプッシュ先物OP注文受付通知サービス
        Services.registerService(
            WEB3QtpRichPushFuOpOrderAcceptUnitService.class,
            new WEB3QtpRichPushFuOpOrderAcceptUnitServiceImpl());

        // Type<12> QTPリッチクライアントプッシュ先物OP訂正取消通知サービス
        Services.registerService(
            WEB3QtpRichPushFuOpChangeCancelUnitService.class,
            new WEB3QtpRichPushFuOpChangeCancelUnitServiceImpl());

        // Type<13> QTPリッチクライアントプッシュ先物OP出来通知サービス
        Services.registerService(
            WEB3QtpRichPushFuOpContUnitService.class,
            new WEB3QtpRichPushFuOpContUnitServiceImpl());

        // Type<14> QTPリッチクライアントプッシュ先物OP失効通知　@サービス
        Services.registerService(
            WEB3QtpRichPushFuOpLapseUnitService.class,
            new WEB3QtpRichPushFuOpLapseUnitServiceImpl());

        // QTPリッチクライアントプッシュゲートウェーサービス
        Services.registerService(
            WEB3QtpRichPushGateWayService.class,
            new WEB3QtpRichPushGateWayServiceImpl());

        // QTPリッチクライアントデータプッシュデータベースアクセス管理サービス
        Services.registerService(
            WEB3QtpRichPushPersistentDataManager.class,
            new WEB3QtpRichPushPersistentDataManagerImpl());

        max_push_messages_size = getPushMaxMessageSizePreference();

        isPlugged = true;

        log.info("WEB3QtpRichPushPlugin was plugged.");
    }

    public static boolean isPlugged()
    {
        return isPlugged;
    }

    public static int getPushMaxMessageSize()
    {
        return max_push_messages_size;
    }

    private static int getPushMaxMessageSizePreference()
    {

        final String STR_METHOD_NAME = "getPushMaxMessageSizePreference()";
        log.entering(STR_METHOD_NAME);

        int l_intPushMaxMessageSize = DEFAULT_MAX_PUSH_MESSAGES_SIZE;
        String l_strPushMaxMessageSize = GtlUtils.getTradingSystem().getPreference(
            STR_MAX_PUSH_MESSAGES_SIZE_KEY);
        if (l_strPushMaxMessageSize != null)
        {
            try
            {
                l_intPushMaxMessageSize = Integer.parseInt(l_strPushMaxMessageSize);
            }
            catch (Exception e)
            {
                log.error(e.getMessage(), e);
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_intPushMaxMessageSize;

    }

}
@

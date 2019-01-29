head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.24.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushPlugin.java;


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
 */
package webbroker3.rcp;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

import webbroker3.rcp.data.WEB3RcpSessionDatabaseExtensions;
import webbroker3.rcp.handler.WEB3RichPushMainHandler;
import webbroker3.rcp.message.WEB3RichPushMainRequest;
import webbroker3.rcp.message.WEB3RichPushMainResponse;
import webbroker3.rcp.service.delegate.WEB3RichPushEquityMarginChangeCancelUnitService;
import webbroker3.rcp.service.delegate.WEB3RichPushEquityMarginContUnitService;
import webbroker3.rcp.service.delegate.WEB3RichPushEquityMarginLapseUnitService;
import webbroker3.rcp.service.delegate.WEB3RichPushEquityMarginOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.WEB3RichPushFuOpChangeCancelUnitService;
import webbroker3.rcp.service.delegate.WEB3RichPushFuOpContUnitService;
import webbroker3.rcp.service.delegate.WEB3RichPushFuOpLapseUnitService;
import webbroker3.rcp.service.delegate.WEB3RichPushFuOpOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.WEB3RichPushGateWayService;
import webbroker3.rcp.service.delegate.WEB3RichPushMainService;
import webbroker3.rcp.service.delegate.WEB3RichPushPersistentDataManager;
import webbroker3.rcp.service.delegate.WEB3RichPushSwapOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.stdimpls.WEB3RichPushEquityMarginChangeCancelUnitServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3RichPushEquityMarginContUnitServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3RichPushEquityMarginLapseUnitServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3RichPushEquityMarginOrderAcceptUnitServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3RichPushFuOpChangeCancelUnitServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3RichPushFuOpContUnitServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3RichPushFuOpLapseUnitServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3RichPushFuOpOrderAcceptUnitServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3RichPushGateWayServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3RichPushMainServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3RichPushPersistentDataManagerImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3RichPushSwapOrderAcceptUnitServiceImpl;
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
public class WEB3RichPushPlugin
    extends Plugin
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RichPushPlugin.class);

    private static boolean isPlugged = false;

    /** 一回最大プッシュメッセージ数 */
    private static final int DEFAULT_MAX_PUSH_MESSAGES_SIZE = 100;

    /** 一回最大プッシュメッセージ数設定キー */
    private final static String STR_MAX_PUSH_MESSAGES_SIZE_KEY =
        "rich.push.max.messages.size";

    /** 一回最大プッシュメッセージ数 */
    private static int max_push_messages_size = DEFAULT_MAX_PUSH_MESSAGES_SIZE;

    /** プッシュオブジェクトソート用コンテクストキー*/
    public static final String PUSH_OBJECT_CONTEXT_ATTRIBUTE_NAME =
        "webbroker3.rcp.pushobjectcontext";

    /**
     * デフォルトコンストラクタ。
     */
    public WEB3RichPushPlugin()
    {
    }

    public static void plug() throws Exception
    {
        plug(WEB3RichPushPlugin.class);
    }

    /**
     * このプラグインクラスのメインメソッド
     */
    public static void onPlug() throws Exception
    {

        KernelPlugin.plug();

        WEB3RcpSessionDatabaseExtensions.plug();

        //リッチクライアントプッシュメインメッセージの登録
        regClass(WEB3RichPushMainRequest.class);
        regClass(WEB3RichPushMainResponse.class);

        //リッチクライアントプッシュメインハンドラーの登録
        regHandler(WEB3RichPushPlugin.class,
                   WEB3RichPushMainRequest.class,
                   WEB3RichPushMainHandler.class,
                   "handleRichPushMainRequest");

        //リッチクライアントプッシュメインサービス
        Services.registerService(
            WEB3RichPushMainService.class,
            new WEB3RichPushMainServiceImpl());

        //リッチクライアントプッシュゲートウェーサービス
        Services.registerService(
            WEB3RichPushGateWayService.class,
            new WEB3RichPushGateWayServiceImpl());

        //リッチクライアントプッシュ株式注文受付サービス
        Services.registerService(
            WEB3RichPushEquityMarginOrderAcceptUnitService.class,
            new WEB3RichPushEquityMarginOrderAcceptUnitServiceImpl());

        //リッチクライアントプッシュ株式信用現引現渡受付サービス
        Services.registerService(
            WEB3RichPushSwapOrderAcceptUnitService.class,
            new WEB3RichPushSwapOrderAcceptUnitServiceImpl());

        //リッチクライアントプッシュ株式訂正取消通知サービス
        Services.registerService(
            WEB3RichPushEquityMarginChangeCancelUnitService.class,
            new WEB3RichPushEquityMarginChangeCancelUnitServiceImpl());

        //リッチクライアントプッシュ株式出来通知サービス
        Services.registerService(
            WEB3RichPushEquityMarginContUnitService.class,
            new WEB3RichPushEquityMarginContUnitServiceImpl());

        //リッチクライアントプッシュ株式失効通知サービス
        Services.registerService(
            WEB3RichPushEquityMarginLapseUnitService.class,
            new WEB3RichPushEquityMarginLapseUnitServiceImpl());

        //リッチクライアントプッシュ先物OP注文受付通知サービス
        Services.registerService(
            WEB3RichPushFuOpOrderAcceptUnitService.class,
            new WEB3RichPushFuOpOrderAcceptUnitServiceImpl());

        //リッチクライアントプッシュ先物OP訂正取消通知サービス
        Services.registerService(
            WEB3RichPushFuOpChangeCancelUnitService.class,
            new WEB3RichPushFuOpChangeCancelUnitServiceImpl());

        //リッチクライアントプッシュ先物OP出来通知サービス
        Services.registerService(
            WEB3RichPushFuOpContUnitService.class,
            new WEB3RichPushFuOpContUnitServiceImpl());

        //リッチクライアントプッシュ先物OP失効通知　@サービス
        Services.registerService(
            WEB3RichPushFuOpLapseUnitService.class,
            new WEB3RichPushFuOpLapseUnitServiceImpl());

        //リッチクライアントプッシュゲートウェーサービス
        Services.registerService(
            WEB3RichPushPersistentDataManager.class,
            new WEB3RichPushPersistentDataManagerImpl());

        // 自動トランザクション設定
//        Services.addInterceptor(
//            WEB3RichPushPersistentDataManager.class,
//            new TransactionalInterceptor(
//            TransactionalInterceptor.TX_JOIN_EXISTING));

        max_push_messages_size = getPushMaxMessageSizePreference();

        try
        {
            WEB3QtpRichPushPlugin.plug();
        }
        catch(Throwable l_t)
        {
            log.error("Qtp Plug Error!", l_t);
        }
        isPlugged = true;

        log.info("WEB3RichPushPlugin was plugged.");
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

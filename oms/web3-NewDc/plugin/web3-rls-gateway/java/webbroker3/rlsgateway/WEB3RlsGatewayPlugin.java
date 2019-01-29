head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.25.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsGatewayPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3RlsGatewayPluginプラグインのプラグインクラス(WEB3RlsGatewayPlugin.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2003/09/14 FLJ劉　@新規作成
 */
package webbroker3.rlsgateway;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.interceptor.*;
import webbroker3.rlsgateway.connector.*;
import webbroker3.rlsgateway.data.*;
import webbroker3.rlsgateway.service.*;
import webbroker3.rlsgateway.service.delegate.stdimpls.*;
import webbroker3.util.*;

/**
 * <p>
 * WEB3RlsGatewayPluginプラグインのプラグインクラス。<br>
 * <br>
 * </p>
 *
 * @@author FLJ劉
 * @@version 1.0
 */
public class WEB3RlsGatewayPlugin
    extends Plugin
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RlsGatewayPlugin.class);

    private static boolean isPlugged = false;

    public static final String MESSAGE_CONTEXT_ATTRIBUTE_NAME =
        "webbroker3.rlsgateway.messagecontext";

    /**
     * 条件付き注文のの発注モードの設定キー
     */
    public final static String STR_RLS_SERVER_ORDER_SUBMIT_MODE_KEY =
        "rls.server.order.submit.mode";

    /**
     * デフォルトコンストラクタ。
     */
    public WEB3RlsGatewayPlugin()
    {
    }

    public static void plug() throws Exception
    {
        plug(WEB3RlsGatewayPlugin.class);
    }

    /**
     * このプラグインクラスのメインメソッド
     */
    public static void onPlug() throws Exception
    {

        KernelPlugin.plug();

        // WEB3RlsRequestSenderServiceサービスを登録
        Services.registerService(
            WEB3RlsRequestSenderService.class,
            new WEB3RlsRequestSenderServiceImpl());

        // WEB3RlsRealTxSenderServiceサービスを登録
        Services.registerService(
            WEB3RlsRealTxSenderService.class,
            new WEB3RlsRealTxSenderServiceImpl());

        // 自動トランザクション設定
        Services.addInterceptor(
            WEB3RlsRealTxSenderService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        WEB3RlsGatewaySessionDatabaseExtensions.plug();

        //ルールエンジン接続用サービス
        WEB3RlsConnectorPlugin.plug();

        isPlugged = true;

        log.info("WEB3RlsGatewayPlugin was plugged.");
    }

    public static boolean isPlugged()
    {
        return isPlugged;
    }
}
@

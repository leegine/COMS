head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.27.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d4c6839;
filename	WEB3RlsOmsAdaptorPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3RlsOmsAdaptorPluginプラグインのプラグインクラス(WEB3RlsOmsAdaptorPlugin.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2003/09/14 FLJ劉　@新規作成
 */
package webbroker3.omsadaptor;

import com.fitechlabs.xtrade.kernel.boot.*;
import webbroker3.omsadaptor.handler.*;
import webbroker3.omsadaptor.message.*;
import webbroker3.omsadaptor.service.delegate.*;
import webbroker3.omsadaptor.service.delegate.stdimpls.*;
import webbroker3.util.*;

/**
 * <p>
 * WEB3RlsOmsAdaptorPluginプラグインのプラグインクラス。<br>
 * <br>
 * </p>
 *
 * @@author FLJ劉
 * @@version 1.0
 */
public class WEB3RlsOmsAdaptorPlugin
    extends Plugin
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RlsOmsAdaptorPlugin.class);

    private static boolean isPlugged = false;

    /**
     * デフォルトコンストラクタ。
     */
    public WEB3RlsOmsAdaptorPlugin()
    {
    }

    public static void plug() throws Exception
    {
        plug(WEB3RlsOmsAdaptorPlugin.class);
    }

    /**
     * このプラグインクラスのメインメソッド
     */
    public static void onPlug() throws Exception
    {

        KernelPlugin.plug();

        //ルールエンジン一件通知用メッセージの登録
        regClass(WEB3RlsCondOrderSubmitRequest.class);
        regClass(WEB3RlsCondOrderSubmitResponse.class);

        //ルールエンジン通知メッセージの登録
        regClass(WEB3RlsCondOrderNotifyMainRequest.class);
        regClass(WEB3RlsCondOrderNotifyMainResponse.class);

        //ルールエンジン一件通知ハンドラーの登録
        regHandler(WEB3RlsOmsAdaptorPlugin.class,
                   WEB3RlsCondOrderSubmitRequest.class,
                   WEB3RlsCondOrderSubmitHandler.class,
                   "handleRlsCondOrderSubmitRequest");

        //ルールエンジン通知ハンドラーの登録
        regHandler(WEB3RlsOmsAdaptorPlugin.class,
                   WEB3RlsCondOrderNotifyMainRequest.class,
                   WEB3RlsCondOrderNotifyMainHandler.class,
                   "handleRlsCondOrderNotifyMainRequest");

        // WEB3RlsCondOrderSubmitServiceサービスを登録
        Services.registerService(
            WEB3RlsCondOrderSubmitService.class,
            new WEB3RlsCondOrderSubmitServiceImpl());
        // WEB33RlsCondOrderNotifyMainServiceサービスを登録
        Services.registerService(
            WEB33RlsCondOrderNotifyMainService.class,
            new WEB3RlsCondOrderNotifyMainServiceImpl());

        // RAC-CTXインタセプタの設定
        Services.addInterceptor(
            WEB3RlsCondOrderSubmitService.class,
            new WEB3RlsDescendRacCtxInterceptor());

        isPlugged = true;

        log.info("WEB3RlsOmsAdaptorPlugin was plugged.");
    }

    public static boolean isPlugged()
    {
        return isPlugged;
    }
}
@

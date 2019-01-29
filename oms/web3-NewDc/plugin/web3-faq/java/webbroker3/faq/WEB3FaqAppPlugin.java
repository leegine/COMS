head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FaqAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-Faq プラグインクラス(WEB3FaqAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 凌建平 (中訊) 新規作成
*/

package webbroker3.faq;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.faq.data.WEB3FaqMasterDatabaseExtensions;
import webbroker3.faq.data.WEB3FaqSessionDatabaseExtensions;
import webbroker3.faq.handler.WEB3AdminFaqDetailsHandler;
import webbroker3.faq.handler.WEB3AdminFaqListHandler;
import webbroker3.faq.handler.WEB3FaqInputHandler;
import webbroker3.faq.message.WEB3AdminFaqDetailsRequest;
import webbroker3.faq.message.WEB3AdminFaqDetailsResponse;
import webbroker3.faq.message.WEB3AdminFaqListInputRequest;
import webbroker3.faq.message.WEB3AdminFaqListInputResponse;
import webbroker3.faq.message.WEB3AdminFaqListRequest;
import webbroker3.faq.message.WEB3AdminFaqListResponse;
import webbroker3.faq.message.WEB3FaqCompleteRequest;
import webbroker3.faq.message.WEB3FaqCompleteResponse;
import webbroker3.faq.message.WEB3FaqConfirmRequest;
import webbroker3.faq.message.WEB3FaqConfirmResponse;
import webbroker3.faq.message.WEB3FaqInputRequest;
import webbroker3.faq.message.WEB3FaqInputResponse;
import webbroker3.faq.service.delegate.WEB3AdminFaqDetailsService;
import webbroker3.faq.service.delegate.WEB3AdminFaqListService;
import webbroker3.faq.service.delegate.WEB3FaqInputService;
import webbroker3.faq.service.delegate.WEB3FaqNumberService;
import webbroker3.faq.service.delegate.stdimpls.WEB3AdminFaqDetailsServiceImpl;
import webbroker3.faq.service.delegate.stdimpls.WEB3AdminFaqListServiceImpl;
import webbroker3.faq.service.delegate.stdimpls.WEB3FaqInputServiceImpl;
import webbroker3.faq.service.delegate.stdimpls.WEB3FaqNumberServiceImpl;
import webbroker3.util.WEB3LogUtility;


/**
 * Webbroker3-Faq プラグインクラス。
 *
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public final class WEB3FaqAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FaqAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3FaqAppPlugin()
    {

    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);

        plug(WEB3FaqAppPlugin.class);

        log.exiting(METHOD_NAME);
    }

    /**
     * プラグイン処理。
     */
    public static void onPlug() throws Exception
    {
        String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);
        
        WEB3FaqMasterDatabaseExtensions.plug();
        // 李志強 Codegenの対応 start
        WEB3FaqSessionDatabaseExtensions.plug();
        // 李志強 Codegenの対応 end

        KernelPlugin.plug();

        // ---------------------- 1 Service の登録処理 ----------------------
        //お問合せ入力サービス
        Services.registerService(
            WEB3FaqInputService.class,
            new WEB3FaqInputServiceImpl());

        //お問合せ一覧サービス
        Services.registerService(
            WEB3AdminFaqListService.class,
            new WEB3AdminFaqListServiceImpl());

        //わせ管理お問合せ詳細サービス
        Services.registerService(
            WEB3AdminFaqDetailsService.class,
            new WEB3AdminFaqDetailsServiceImpl());

        //問合せ管理問合せコード採番サービス
        Services.registerService(
            WEB3FaqNumberService.class,
            new WEB3FaqNumberServiceImpl());

        // ---------------------- 2 Service.execute()呼び出し前後に  ----------------------
        // 処理開始時刻と処理終了時刻をログ出力するように設定する
        //お問合せ入力サービス
        Services.addInterceptor(
            WEB3FaqInputService.class,
            new WEB3LogSysTimeInterceptor());

        //お問合せ一覧サービス
        Services.addInterceptor(
            WEB3AdminFaqListService.class,
            new WEB3LogSysTimeInterceptor());

        //わせ管理お問合せ詳細サービス
        Services.addInterceptor(
            WEB3AdminFaqDetailsService.class,
            new WEB3LogSysTimeInterceptor());

        // ---------------------- 3 Service に ServiceInterceptor を設定する ----------------------
        //お問合せ入力サービス
        Services.addInterceptor(
            WEB3FaqInputService.class,
            new WEB3FaqInputServiceInterceptor());

        //お問合せ一覧サービス
        Services.addInterceptor(
            WEB3AdminFaqListService.class,
            new WEB3FaqServiceInterceptor());
        
        //わせ管理お問合せ詳細サービス
        Services.addInterceptor(
            WEB3AdminFaqDetailsService.class,
            new WEB3FaqServiceInterceptor());

        // ---------------------- 4 Service の Interceptor 設定処理 ----------------------
        // 自動トランザクション設定
        //お問合せ入力サービス
        Services.addInterceptor(
            WEB3FaqInputService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //お問合せ一覧サービス
        Services.addInterceptor(
        WEB3AdminFaqListService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //わせ管理お問合せ詳細サービス
        Services.addInterceptor(
        WEB3AdminFaqDetailsService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //問合せ管理問合せコード採番サービス
        Services.addInterceptor(
        WEB3FaqNumberService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // ---------------------- 5 Message の登録処理 ----------------------
        //問合せ管理お問合せ入力リクエスト
        regClass(WEB3FaqInputRequest.class);
        //問合せ管理お問合せ入力レスポンス
        regClass(WEB3FaqInputResponse.class);

        //問合せ管理お問合せ確認リクエスト
        regClass(WEB3FaqConfirmRequest.class);
        //問合せ管理お問合せ確認レスポンス
        regClass(WEB3FaqConfirmResponse.class);

        //問合せ管理お問合せ完了リクエスト
        regClass(WEB3FaqCompleteRequest.class);
        //問合せ管理お問合せ完了レスポンス
        regClass(WEB3FaqCompleteResponse.class);

        //管理者問合せ管理お問合せ一覧入力リクエスト
        regClass(WEB3AdminFaqListInputRequest.class);
        //管理者問合せ管理お問合せ一覧入力レスポンス
        regClass(WEB3AdminFaqListInputResponse.class);

        //管理者問合せ管理お問合せ一覧リクエスト
        regClass(WEB3AdminFaqListRequest.class);
        //管理者問合せ管理お問合せ一覧レスポンス
        regClass(WEB3AdminFaqListResponse.class);

        //管理者問合せ管理お問合せ詳細リクエスト
        regClass(WEB3AdminFaqDetailsRequest.class);
        //管理者問合せ管理お問合せ詳細レスポンス
        regClass(WEB3AdminFaqDetailsResponse.class);

        // ---------------------- 6 Handler の登録処理 ----------------------
        //問合せ管理お問合せ入力
        regHandler(
            WEB3FaqAppPlugin.class,
            WEB3FaqInputRequest.class,
            WEB3FaqInputHandler.class,
            "inputScreenDisplay");

        regHandler(
            WEB3FaqAppPlugin.class,
            WEB3FaqConfirmRequest.class,
            WEB3FaqInputHandler.class,
            "faqInputConfirm");

        regHandler(
            WEB3FaqAppPlugin.class,
            WEB3FaqCompleteRequest.class,
            WEB3FaqInputHandler.class,
            "faqInputComplete");

        //管理者問合せ管理お問合せ一覧入力
        regHandler(
            WEB3FaqAppPlugin.class,
            WEB3AdminFaqListInputRequest.class,
            WEB3AdminFaqListHandler.class,
            "inputScreenDisplay");

        regHandler(
            WEB3FaqAppPlugin.class,
            WEB3AdminFaqListRequest.class,
            WEB3AdminFaqListHandler.class,
            "faqListDisplay");

        //管理者問合せ管理お問合せ詳細
        regHandler(
            WEB3FaqAppPlugin.class,
            WEB3AdminFaqDetailsRequest.class,
            WEB3AdminFaqDetailsHandler.class,
            "faqDetailsDisplay");

        log.exiting(METHOD_NAME);
    }
}
@

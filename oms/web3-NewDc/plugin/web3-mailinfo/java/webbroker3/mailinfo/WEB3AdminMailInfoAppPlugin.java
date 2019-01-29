head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.16.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-AdminMailInfo プラグイン(WEB3AdminMailInfoAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 王亞洲 (中訊) 新規作成
*/

package webbroker3.mailinfo;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.mailinfo.handler.WEB3AdminMailInfoChangeHandler;
import webbroker3.mailinfo.handler.WEB3AdminMailInfoDeleteHandler;
import webbroker3.mailinfo.handler.WEB3AdminMailInfoDetailsHandler;
import webbroker3.mailinfo.handler.WEB3AdminMailInfoReferenceHandler;
import webbroker3.mailinfo.handler.WEB3AdminMailInfoRegistHandler;
import webbroker3.mailinfo.message.WEB3AdminMailInfoChangeCompleteRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoChangeCompleteResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoChangeConfirmRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoChangeConfirmResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoChangeInputRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoChangeInputResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoCommonRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoCommonResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteCommonRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteCompleteRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteCompleteResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteConfirmRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteConfirmResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDetailsRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDetailsResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoReferenceRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoReferenceResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoRegistCompleteRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoRegistCompleteResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoRegistConfirmRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoRegistConfirmResponse;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoChangeService;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoDeleteService;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoDetailsService;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoReferenceService;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoRegistService;
import webbroker3.mailinfo.service.delegate.stdimpls.WEB3AdminMailInfoChangeServiceImpl;
import webbroker3.mailinfo.service.delegate.stdimpls.WEB3AdminMailInfoDeleteServiceImpl;
import webbroker3.mailinfo.service.delegate.stdimpls.WEB3AdminMailInfoDetailsServiceImpl;
import webbroker3.mailinfo.service.delegate.stdimpls.WEB3AdminMailInfoReferenceServiceImpl;
import webbroker3.mailinfo.service.delegate.stdimpls.WEB3AdminMailInfoRegistServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-AdminMailInfo プラグインクラス。
 *                                                                
 * @@author 王亞洲
 * @@version 1.0
 */
public final class WEB3AdminMailInfoAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMailInfoAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3AdminMailInfoAppPlugin()
    {
        String STR_METHOD_NAME = " WEB3AdminMailInfoAppPlugin()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception
    {
        String STR_METHOD_NAME = " plug()";
        log.entering(STR_METHOD_NAME);

        plug(WEB3AdminMailInfoAppPlugin.class);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * プラグイン処理。
     */
    public static void onPlug() throws Exception
    {
        String STR_METHOD_NAME = " onPlug()";
        log.entering(STR_METHOD_NAME);
        
        // このプラグインより先に読み込む必要のあるプラグインの指定。
        // install the system plugins that we need
        KernelPlugin.plug();
        

        // Service の登録処理 ----------------------
        
        //メール情報変更サービス
        Services.registerService(WEB3AdminMailInfoChangeService.class, new WEB3AdminMailInfoChangeServiceImpl());

        //メール情報削除サービス
        Services.registerService(WEB3AdminMailInfoDeleteService.class, new WEB3AdminMailInfoDeleteServiceImpl());
        
        //メール情報詳細サービス
        Services.registerService(WEB3AdminMailInfoDetailsService.class, new WEB3AdminMailInfoDetailsServiceImpl());

        //メール情報一覧サービス
        Services.registerService(WEB3AdminMailInfoReferenceService.class, new WEB3AdminMailInfoReferenceServiceImpl());

        //メール情報登録サービス
        Services.registerService(WEB3AdminMailInfoRegistService.class, new WEB3AdminMailInfoRegistServiceImpl());


        // Service の Interceptor 設定処理 ----------------------
        // 自動トランザクション設定

        //メール情報変更サービス
        Services.addInterceptor(WEB3AdminMailInfoChangeService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMailInfoChangeService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //メール情報削除サービス
        Services.addInterceptor(WEB3AdminMailInfoDeleteService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMailInfoDeleteService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //メール情報詳細サービス
        Services.addInterceptor(WEB3AdminMailInfoDetailsService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMailInfoDetailsService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //メール情報一覧サービス
        Services.addInterceptor(WEB3AdminMailInfoReferenceService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMailInfoReferenceService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //メール情報登録サービス        
        Services.addInterceptor(WEB3AdminMailInfoRegistService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMailInfoRegistService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));


        // Message の登録処理 ----------------------

        //メール情報共通リクエスト
        regClass(WEB3AdminMailInfoCommonRequest.class);
        //メール情報共通レスポンス
        regClass(WEB3AdminMailInfoCommonResponse.class);

        //メール情報削除共通リクエスト
        regClass(WEB3AdminMailInfoDeleteCommonRequest.class);

        //メール情報一覧リクエスト
        regClass(WEB3AdminMailInfoReferenceRequest.class);
        //メール情報一覧レスポンス
        regClass(WEB3AdminMailInfoReferenceResponse.class);

        //メール情報削除確認リクエスト
        regClass(WEB3AdminMailInfoDeleteConfirmRequest.class);
        //メール情報削除確認レスポンス
        regClass(WEB3AdminMailInfoDeleteConfirmResponse.class);

        //メール情報削除完了リクエスト
        regClass(WEB3AdminMailInfoDeleteCompleteRequest.class);
        //メール情報削除完了レスポンス
        regClass(WEB3AdminMailInfoDeleteCompleteResponse.class);

        //メール情報詳細リクエスト
        regClass(WEB3AdminMailInfoDetailsRequest.class);
        //メール情報詳細レスポンス
        regClass(WEB3AdminMailInfoDetailsResponse.class);

        //メール情報登録確認リクエスト
        regClass(WEB3AdminMailInfoRegistConfirmRequest.class);
        //メール情報登録確認レスポンス
        regClass(WEB3AdminMailInfoRegistConfirmResponse.class);

        //メール情報登録完了リクエスト
        regClass(WEB3AdminMailInfoRegistCompleteRequest.class);
        //メール情報登録完了レスポンス
        regClass(WEB3AdminMailInfoRegistCompleteResponse.class);

        //メール情報変更入力画面リクエスト
        regClass(WEB3AdminMailInfoChangeInputRequest.class);
        //メール情報変更入力画面レスポンス
        regClass(WEB3AdminMailInfoChangeInputResponse.class);

        //メール情報変更確認リクエスト
        regClass(WEB3AdminMailInfoChangeConfirmRequest.class);
        //メール情報変更確認レスポンス
        regClass(WEB3AdminMailInfoChangeConfirmResponse.class);

        //メール情報変更完了リクエスト
        regClass(WEB3AdminMailInfoChangeCompleteRequest.class);
        //メール情報変更完了レスポンス
        regClass(WEB3AdminMailInfoChangeCompleteResponse.class);


        //Handler の登録処理 ----------------------

        //メール情報変更 用ハンドラーの登録
        regHandler(WEB3AdminMailInfoChangeInputRequest.class, WEB3AdminMailInfoChangeHandler.class, "mailInfoChangeInputScreenRequest");

        //メール情報変更 用ハンドラーの登録
        regHandler(WEB3AdminMailInfoChangeConfirmRequest.class, WEB3AdminMailInfoChangeHandler.class, "confirmMailInfoChange");

        //メール情報変更 用ハンドラーの登録
        regHandler(WEB3AdminMailInfoChangeCompleteRequest.class, WEB3AdminMailInfoChangeHandler.class, "completeMailInfoChange");

        //メール情報削除 用ハンドラーの登録
        regHandler(WEB3AdminMailInfoDeleteConfirmRequest.class, WEB3AdminMailInfoDeleteHandler.class, "confirmMailInfoDelete");

        //メール情報削除 用ハンドラーの登録
        regHandler(WEB3AdminMailInfoDeleteCompleteRequest.class, WEB3AdminMailInfoDeleteHandler.class, "completeMailInfoDelete");

        //メール情報詳細 用ハンドラーの登録
        regHandler(WEB3AdminMailInfoDetailsRequest.class, WEB3AdminMailInfoDetailsHandler.class, "mailInfoDetailsRequest");

        //メール情報一覧 用ハンドラーの登録
        regHandler(WEB3AdminMailInfoReferenceRequest.class, WEB3AdminMailInfoReferenceHandler.class, "mailInfoReferenceRequest");

        //メール情報登録 用ハンドラーの登録
        regHandler(WEB3AdminMailInfoRegistConfirmRequest.class, WEB3AdminMailInfoRegistHandler.class, "confirmMailInfoRegist");

        //メール情報登録 用ハンドラーの登録
        regHandler(WEB3AdminMailInfoRegistCompleteRequest.class, WEB3AdminMailInfoRegistHandler.class, "completeMailInfoRegist");

        log.exiting(STR_METHOD_NAME);
    }
}@

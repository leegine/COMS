head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DocAdminAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-DocAdmin プラグインクラス(WEB3DocAdminAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/10/10  何文敏(中訊) 新規作成
Revision History : 2007/11/06 謝旋 (中訊) 仕様変更・モデル No.010,No.011
Revision History : 2007/12/11 武波 (中訊) 仕様変更・モデル No.012,No.013
Revision History : 2008/03/03 馮海濤 (中訊) 仕様変更・ モデルNo.037
*/

package webbroker3.docadmin;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.docadmin.data.WEB3DocadminSessionDatabaseExtensions;
import webbroker3.docadmin.handler.WEB3AdminFPTDeleteHandler;
import webbroker3.docadmin.handler.WEB3AdminFPTDocumentListReferenceHandler;
import webbroker3.docadmin.handler.WEB3AdminFPTDocumentUpdateHandler;
import webbroker3.docadmin.handler.WEB3AdminFPTForceLogoutHandler;
import webbroker3.docadmin.handler.WEB3AdminFPTForceLogoutMainHandler;
import webbroker3.docadmin.handler.WEB3AdminFPTListReferenceHandler;
import webbroker3.docadmin.handler.WEB3AdminFPTRegistHandler;
import webbroker3.docadmin.handler.WEB3AdminFPTUploadHandler;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListReferenceRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListReferenceResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListSearchInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListSearchInputResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateInputResponse;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutInputResponse;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutMainRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutMainResponse;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutReferenceRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutReferenceResponse;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTListReferenceRequest;
import webbroker3.docadmin.message.WEB3AdminFPTListReferenceResponse;
import webbroker3.docadmin.message.WEB3AdminFPTRegistCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTRegistConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTRegistInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistInputResponse;
import webbroker3.docadmin.message.WEB3AdminFPTSearchInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTSearchInputResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUpdateCommonRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCancelRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCancelResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUploadConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUploadInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadInputResponse;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTDeleteService;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTDocumentListReferenceService;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTDocumentUpdateService;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTForceLogoutService;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTForceLogoutMainService;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTForceLogoutUnitService;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTListReferenceService;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTRegistService;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTUploadService;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTDeleteServiceImpl;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTDocumentListReferenceServiceImpl;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTDocumentUpdateServiceImpl;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTForceLogoutServiceImpl;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTListReferenceServiceImpl;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTRegistServiceImpl;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTUploadServiceImpl;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTForceLogoutMainServiceImpl;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTForceLogoutUnitServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-DocAdmin プラグインクラス。
 *
 * @@author 何文敏(中訊)
 * @@version 1.0
 */
public class WEB3DocAdminAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3DocAdminAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3DocAdminAppPlugin()
    {

    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);

        plug(WEB3DocAdminAppPlugin.class);

        log.exiting(METHOD_NAME);
    }

    /**
     * プラグイン処理。
     */
    public static void onPlug() throws Exception
    {
        String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);

        // ---------------------- 1 Service の登録処理 ----------------------
        // 管理者金商法@交付閲覧照会サービス
        Services.registerService(
            WEB3AdminFPTListReferenceService.class,
            new WEB3AdminFPTListReferenceServiceImpl());
        // 管理者金商法@交付閲覧登録サービス
        Services.registerService(
            WEB3AdminFPTRegistService.class,
            new WEB3AdminFPTRegistServiceImpl());
        //管理者金商法@交付閲覧削除サービス
        Services.registerService(
            WEB3AdminFPTDeleteService.class,
            new WEB3AdminFPTDeleteServiceImpl());

        //管理者金商法@交付閲覧アップロードサービス
        Services.registerService(
            WEB3AdminFPTUploadService.class,
            new WEB3AdminFPTUploadServiceImpl());
        
        //管理者金商法@交付書面照会サービス
        Services.registerService(
            WEB3AdminFPTDocumentListReferenceService.class,
            new WEB3AdminFPTDocumentListReferenceServiceImpl());
        
        //管理者金商法@交付書面更新サービス
        Services.registerService(
            WEB3AdminFPTDocumentUpdateService.class,
            new WEB3AdminFPTDocumentUpdateServiceImpl());

        //管理者書面未承諾 強制ログアウトサービス
        Services.registerService(
            WEB3AdminFPTForceLogoutService.class,
            new WEB3AdminFPTForceLogoutServiceImpl());
        
        //管理者 書面未承諾 強制ログアウトメインサービス
        Services.registerService(
            WEB3AdminFPTForceLogoutMainService.class,
            new WEB3AdminFPTForceLogoutMainServiceImpl());
        
        //管理者 書面未承諾 強制ログアウト一件サービス
        Services.registerService(
                WEB3AdminFPTForceLogoutUnitService.class,
                new WEB3AdminFPTForceLogoutUnitServiceImpl());        
        
        // ---------------------- 2 Service.execute()呼び出し前後に  ----------------------
        // 処理開始時刻と処理終了時刻をログ出力するように設定する
        // 管理者金商法@交付閲覧照会サービス
        Services.addInterceptor(
            WEB3AdminFPTListReferenceService.class,
            new WEB3LogSysTimeInterceptor());
        // 管理者金商法@交付閲覧登録サービス
        Services.addInterceptor(
            WEB3AdminFPTRegistService.class,
            new WEB3LogSysTimeInterceptor());
        //管理者金商法@交付閲覧削除サービス
        Services.addInterceptor(
            WEB3AdminFPTDeleteService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者金商法@交付閲覧アップロードサービス
        Services.addInterceptor(
            WEB3AdminFPTUploadService.class,
            new WEB3LogSysTimeInterceptor());
        
        //管理 書面未承諾 強制ログアウトサービス
        Services.addInterceptor(
            WEB3AdminFPTForceLogoutService.class,
            new WEB3LogSysTimeInterceptor());
        
        //管理者 書面未承諾 強制ログアウトメインサービス
        Services.addInterceptor(
            WEB3AdminFPTForceLogoutMainService.class,
            new WEB3LogSysTimeInterceptor());
        
        //管理者 書面未承諾 強制ログアウト一件サービス
        Services.addInterceptor(
                WEB3AdminFPTForceLogoutUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // ---------------------- 3 Service に ServiceInterceptor を設定する ----------------------

        //管理者金商法@交付閲覧アップロードサービス
        Services.addInterceptor(WEB3AdminFPTUploadService.class,
            new WEB3AdminFPTUploadInterceptor());
        
        //管理 書面未承諾 強制ログアウトサービス
        Services.addInterceptor(
            WEB3AdminFPTForceLogoutService.class,
            new WEB3AdminFPTForceLogoutInterceptor());

        // ---------------------- 4 Service の Interceptor 設定処理 ----------------------
        // 自動トランザクション設定
        //管理者金商法@交付閲覧照会サービス
        Services.addInterceptor(
            WEB3AdminFPTListReferenceService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));
        // 管理者金商法@交付閲覧登録サービス
        Services.addInterceptor(
            WEB3AdminFPTRegistService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));
        //管理者金商法@交付閲覧削除サービス
        Services.addInterceptor(
            WEB3AdminFPTDeleteService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者金商法@交付閲覧アップロードサービス
        Services.addInterceptor(
            WEB3AdminFPTUploadService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //管理 書面未承諾 強制ログアウトサービス
        Services.addInterceptor(
            WEB3AdminFPTForceLogoutService.class,
            new TransactionalInterceptor(
                    TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //管理者 書面未承諾 強制ログアウトメインサービス
        Services.addInterceptor(
            WEB3AdminFPTForceLogoutMainService.class,
            new TransactionalInterceptor(
                    TransactionalInterceptor.TX_CREATE_NEW));
        
        //管理者 書面未承諾 強制ログアウト一件サービス
        Services.addInterceptor(
                WEB3AdminFPTForceLogoutUnitService.class,
                new TransactionalInterceptor(
                    TransactionalInterceptor.TX_CREATE_NEW));

        // ---------------------- 5 Message の登録処理 ----------------------
        //管理者金商法@交付閲覧一覧照会リクエスト
        regClass(WEB3AdminFPTListReferenceRequest.class);
        //管理者金商法@交付閲覧一覧照会レスポンス
        regClass(WEB3AdminFPTListReferenceResponse.class);
        
        //管理者金商法@交付閲覧登録完了リクエスト
        regClass(WEB3AdminFPTRegistCompleteRequest.class);
        //管理者金商法@交付閲覧登録完了レスポンス
        regClass(WEB3AdminFPTRegistCompleteResponse.class);
        
        //管理者金商法@交付閲覧登録確認リクエスト
        regClass(WEB3AdminFPTRegistConfirmRequest.class);
        //管理者金商法@交付閲覧登録確認レスポンス
        regClass(WEB3AdminFPTRegistConfirmResponse.class);
        
        //管理者金商法@交付閲覧登録入力リクエスト
        regClass(WEB3AdminFPTRegistInputRequest.class);
        //管理者金商法@交付閲覧登録入力レスポンス
        regClass(WEB3AdminFPTRegistInputResponse.class);
        
        //管理者金商法@交付閲覧検索入力リクエスト
        regClass(WEB3AdminFPTSearchInputRequest.class);
        //管理者金商法@交付閲覧検索入力レスポンス
        regClass(WEB3AdminFPTSearchInputResponse.class);

        //管理者金商法@交付閲覧削除確認リクエスト
        regClass(WEB3AdminFPTDeleteConfirmRequest.class);
        //管理者金商法@交付閲覧削除確認レスポンス
        regClass(WEB3AdminFPTDeleteConfirmResponse.class);

        //管理者金商法@交付閲覧削除完了リクエスト
        regClass(WEB3AdminFPTDeleteCompleteRequest.class);
        //管理者金商法@交付閲覧削除完了レスポンス
        regClass(WEB3AdminFPTDeleteCompleteResponse.class);

        //管理者金商法@交付更新共通リクエスト
        regClass(WEB3AdminFPTUpdateCommonRequest.class);

        //管理者金商法@交付閲覧アップロード中止リクエスト
        regClass(WEB3AdminFPTUploadCancelRequest.class);
        //管理者金商法@交付閲覧アップロード中止レスポンス
        regClass(WEB3AdminFPTUploadCancelResponse.class);

        //管理者金商法@交付閲覧アップロード完了リクエスト
        regClass(WEB3AdminFPTUploadCompleteRequest.class);
        //管理者金商法@交付閲覧アップロード完了レスポンス
        regClass(WEB3AdminFPTUploadCompleteResponse.class);

        //管理者金商法@交付閲覧アップロード確認リクエスト
        regClass(WEB3AdminFPTUploadConfirmRequest.class);
        //管理者金商法@交付閲覧アップロード確認レスポンス
        regClass(WEB3AdminFPTUploadConfirmResponse.class);

        //管理者金商法@交付閲覧アップロード入力リクエスト
        regClass(WEB3AdminFPTUploadInputRequest.class);
        //管理者金商法@交付閲覧アップロード入力レスポンス
        regClass(WEB3AdminFPTUploadInputResponse.class);
        
        //管理者金商法@交付書面更新入力レスポンスクラス
        regClass(WEB3AdminFPTDocumentUpdateInputResponse.class);
        //管理者金商法@交付書面更新入力リクエストクラス
        regClass(WEB3AdminFPTDocumentUpdateInputRequest.class);
        //管理者金商法@交付書面更新確認レスポンスクラス
        regClass(WEB3AdminFPTDocumentUpdateConfirmResponse.class);
        //管理者金商法@交付書面更新確認リクエストクラス
        regClass(WEB3AdminFPTDocumentUpdateConfirmRequest.class);
        //管理者金商法@交付書面更新完了レスポンスクラス
        regClass(WEB3AdminFPTDocumentUpdateCompleteResponse.class);
        //管理者金商法@交付書面更新完了リクエストクラス
        regClass(WEB3AdminFPTDocumentUpdateCompleteRequest.class);
        //管理者金商法@交付書面照会検索入力レスポンスクラス
        regClass(WEB3AdminFPTDocumentListSearchInputResponse.class);
        //管理者金商法@交付書面照会検索入力リクエストクラス
        regClass(WEB3AdminFPTDocumentListSearchInputRequest.class);
        //管理者金商法@交付書面照会一覧レスポンスクラス
        regClass(WEB3AdminFPTDocumentListReferenceResponse.class);
        //管理者金商法@交付書面照会一覧リクエストクラス
        regClass(WEB3AdminFPTDocumentListReferenceRequest.class);
       
        //管理者書面未承諾強制ログアウト入力リクエスト
        regClass(WEB3AdminFPTForceLogoutInputRequest.class);
        //管理者書面未承諾強制ログアウト入力レスポンス
        regClass(WEB3AdminFPTForceLogoutInputResponse.class);
        
        //管理者書面未承諾強制ログアウト確認リクエスト
        regClass(WEB3AdminFPTForceLogoutConfirmRequest.class);
        //管理者書面未承諾強制ログアウト確認レスポンス
        regClass(WEB3AdminFPTForceLogoutConfirmResponse.class);
        
        //管理者書面未承諾強制ログアウト完了リクエスト
        regClass(WEB3AdminFPTForceLogoutCompleteRequest.class);
        //管理者書面未承諾強制ログアウト完了レスポンス
        regClass(WEB3AdminFPTForceLogoutCompleteResponse.class);
        
        //管理者書面未承諾強制ログアウト結果照会リクエスト
        regClass(WEB3AdminFPTForceLogoutReferenceRequest.class);
        //管理者書面未承諾強制ログアウト結果照会レスポンス
        regClass(WEB3AdminFPTForceLogoutReferenceResponse.class);
        
        //管理者 書面未承諾 強制ログアウトメインリクエスト
        regClass(WEB3AdminFPTForceLogoutMainRequest.class);
        
        //管理者 書面未承諾 強制ログアウトメインレスポンス
        regClass(WEB3AdminFPTForceLogoutMainResponse.class);

        // ---------------------- 6 Handler の登録処理 ----------------------
        //管理者金商法@交付閲覧照会ハンドラ
        //get交付閲覧一覧検索画面
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTSearchInputRequest.class,
            WEB3AdminFPTListReferenceHandler.class,
            "getListSearchScreen");

        //get交付閲覧一覧照会画面
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTListReferenceRequest.class,
            WEB3AdminFPTListReferenceHandler.class,
            "getListReferenceScreen");

        //管理者金商法@交付閲覧登録ハンドラ
        //get金商法@交付閲覧登録入力
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTRegistInputRequest.class,
            WEB3AdminFPTRegistHandler.class,
            "getRegistInput");

        //get金商法@交付閲覧登録確認
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTRegistConfirmRequest.class,
            WEB3AdminFPTRegistHandler.class,
            "getRegistConfirm");

        //get金商法@交付閲覧登録完了
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTRegistCompleteRequest.class,
            WEB3AdminFPTRegistHandler.class,
            "getRegistComplete");

        //get金商法@交付閲覧削除確認
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTDeleteConfirmRequest.class,
            WEB3AdminFPTDeleteHandler.class,
            "getDeleteConfirm");

        //get金商法@交付閲覧削除完了
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTDeleteCompleteRequest.class,
            WEB3AdminFPTDeleteHandler.class,
            "getDeleteComplete");

        //アップロード画面表示
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTUploadInputRequest.class,
            WEB3AdminFPTUploadHandler.class,
            "uploadScreenDisplay");

        //アップロードファ@イル確認
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTUploadConfirmRequest.class,
            WEB3AdminFPTUploadHandler.class,
            "uploadFileConfirm");

        //金商法@交付閲覧アップロード
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTUploadCompleteRequest.class,
            WEB3AdminFPTUploadHandler.class,
            "adminFPTUpload");

        //アップロード中止
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTUploadCancelRequest.class,
            WEB3AdminFPTUploadHandler.class,
            "uploadCancel");
        
        //管理者金商法@交付書面照会ハンドラ
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTDocumentListSearchInputRequest.class,
            WEB3AdminFPTDocumentListReferenceHandler.class,    
            "getDocumentReferenceSearchInput");
        //管理者金商法@交付書面照会ハンドラ
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTDocumentListReferenceRequest.class,
            WEB3AdminFPTDocumentListReferenceHandler.class,    
            "getDocumentReferenceList");
    
        //管理者金商法@交付書面更新ハンドラ
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTDocumentUpdateInputRequest.class,
            WEB3AdminFPTDocumentUpdateHandler.class,    
            "getDocumentUpdateInput");
        
        //管理者金商法@交付書面更新ハンドラ
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTDocumentUpdateConfirmRequest.class,
            WEB3AdminFPTDocumentUpdateHandler.class,    
            "getDocumentUpdateConfirm");
        
        //管理者金商法@交付書面更新ハンドラ
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTDocumentUpdateCompleteRequest.class,
            WEB3AdminFPTDocumentUpdateHandler.class,    
            "getDocumentUpdateComplete");

        //管理 書面未承諾 強制ログアウトハンドラ get入力画面
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTForceLogoutInputRequest.class,
            WEB3AdminFPTForceLogoutHandler.class,
            "getInputPage");
        
        //管理 書面未承諾 強制ログアウトハンドラ validate強制ログアウト
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTForceLogoutConfirmRequest.class,
            WEB3AdminFPTForceLogoutHandler.class,
            "validateForceLogout");
        
        //管理 書面未承諾 強制ログアウトハンドラ submit強制ログアウト
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTForceLogoutCompleteRequest.class,
            WEB3AdminFPTForceLogoutHandler.class,
            "submitForceLogout");
        
        //管理 書面未承諾 強制ログアウトハンドラ 結果照会画面表示処理
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTForceLogoutReferenceRequest.class,
            WEB3AdminFPTForceLogoutHandler.class,
            "getResultRefrence");
        
        //管理者 書面未承諾 強制ログアウトメインハンドラ
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTForceLogoutMainRequest.class,
            WEB3AdminFPTForceLogoutMainHandler.class,
            "execForceLogout");
        
        // ---------------------- 7 DB の登録処理 ----------------------
        WEB3DocadminSessionDatabaseExtensions.plug();
        
        log.exiting(METHOD_NAME);
    }
}
@

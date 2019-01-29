head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.11.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-PvInfo プラグイン(WEB3PvInfoAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/27 王亞洲 (中訊) 新規作成
*/
package webbroker3.pvinfo;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.pvinfo.data.WEB3PvInfoAccountDatabaseExtensions;
import webbroker3.pvinfo.data.WEB3PvInfoMasterDatabaseExtensions;
import webbroker3.pvinfo.handler.WEB3AdminPvInfoConditionChangeHandler;
import webbroker3.pvinfo.handler.WEB3AdminPvInfoConditionDelHandler;
import webbroker3.pvinfo.handler.WEB3AdminPvInfoConditionDetailHandler;
import webbroker3.pvinfo.handler.WEB3AdminPvInfoConditionListHandler;
import webbroker3.pvinfo.handler.WEB3AdminPvInfoConditionRegistHandler;
import webbroker3.pvinfo.handler.WEB3AdminPvInfoDirectChangeHandler;
import webbroker3.pvinfo.handler.WEB3AdminPvInfoDirectRegistHandler;
import webbroker3.pvinfo.handler.WEB3PvInfoPrivateInformationDetailHandler;
import webbroker3.pvinfo.handler.WEB3PvInfoPrivateInformationListHandler;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeInputRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeInputResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDetailRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDetailResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionListRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionListResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistInputRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistInputResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionUpdateRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionUpdateResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCancelRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCancelResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeInputRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeInputResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCancelRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCancelResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistInputRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistInputResponse;
import webbroker3.pvinfo.message.WEB3PvInfoAccountConnectionRequest;
import webbroker3.pvinfo.message.WEB3PvInfoAccountConnectionResponse;
import webbroker3.pvinfo.message.WEB3PvInfoDirectMessageDeleteRequest;
import webbroker3.pvinfo.message.WEB3PvInfoDirectMessageDeleteResponse;
import webbroker3.pvinfo.message.WEB3PvInfoInstitutionConnectionRequest;
import webbroker3.pvinfo.message.WEB3PvInfoInstitutionConnectionResponse;
import webbroker3.pvinfo.message.WEB3PvInfoOrderExecStateRequest;
import webbroker3.pvinfo.message.WEB3PvInfoOrderExecStateResponse;
import webbroker3.pvinfo.message.WEB3PvInfoPrivateInformationDetailRequest;
import webbroker3.pvinfo.message.WEB3PvInfoPrivateInformationDetailResponse;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionChangeService;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionDelService;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionDetailService;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionListService;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionRegistService;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoDirectChangeService;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoDirectRegistService;
import webbroker3.pvinfo.service.delegate.WEB3PvInfoPrivateInformationDetailService;
import webbroker3.pvinfo.service.delegate.WEB3PvInfoPrivateInformationListService;
import webbroker3.pvinfo.service.delegate.stdimpls.WEB3AdminPvInfoConditionChangeServiceImpl;
import webbroker3.pvinfo.service.delegate.stdimpls.WEB3AdminPvInfoConditionDelServiceImpl;
import webbroker3.pvinfo.service.delegate.stdimpls.WEB3AdminPvInfoConditionDetailServiceImpl;
import webbroker3.pvinfo.service.delegate.stdimpls.WEB3AdminPvInfoConditionListServiceImpl;
import webbroker3.pvinfo.service.delegate.stdimpls.WEB3AdminPvInfoConditionRegistServiceImpl;
import webbroker3.pvinfo.service.delegate.stdimpls.WEB3AdminPvInfoDirectChangeServiceImpl;
import webbroker3.pvinfo.service.delegate.stdimpls.WEB3AdminPvInfoDirectRegistServiceImpl;
import webbroker3.pvinfo.service.delegate.stdimpls.WEB3PvInfoPrivateInformationDetailServiceImpl;
import webbroker3.pvinfo.service.delegate.stdimpls.WEB3PvInfoPrivateInformationListServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-PvInfoInfo プラグインクラス。
 *                                                                
 * @@author 王亞洲
 * @@version 1.0
 */
public final class WEB3PvInfoAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PvInfoAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3PvInfoAppPlugin()
    {
        String STR_METHOD_NAME = " WEB3PvInfoAppPlugin()";
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

        plug(WEB3PvInfoAppPlugin.class);

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
        
        //DatabaseExtensions のプラグイン処理 ----------------------
        WEB3PvInfoMasterDatabaseExtensions.plug();
        WEB3PvInfoAccountDatabaseExtensions.plug();
        
        //Service の登録処理 ----------------------
        
        //ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ
        Services.registerService(WEB3PvInfoDataManager.class, new WEB3PvInfoDataManagerImpl());
        
        //管理者表示設定変更サービス
        Services.registerService(WEB3AdminPvInfoConditionChangeService.class, new WEB3AdminPvInfoConditionChangeServiceImpl());
        
        //管理者表示設定削除サービス
        Services.registerService(WEB3AdminPvInfoConditionDelService.class, new WEB3AdminPvInfoConditionDelServiceImpl());
        
        //管理者表示設定詳細サービス
        Services.registerService(WEB3AdminPvInfoConditionDetailService.class, new WEB3AdminPvInfoConditionDetailServiceImpl());
        
        //管理者表示設定一覧サービス
        Services.registerService(WEB3AdminPvInfoConditionListService.class, new WEB3AdminPvInfoConditionListServiceImpl());
        
        //管理者表示設定登録サービス
        Services.registerService(WEB3AdminPvInfoConditionRegistService.class, new WEB3AdminPvInfoConditionRegistServiceImpl());
        
        //管理者ダイレクト指定変更サービス
        Services.registerService(WEB3AdminPvInfoDirectChangeService.class, new WEB3AdminPvInfoDirectChangeServiceImpl());
        
        //管理者ダイレクト指定登録サービス
        Services.registerService(WEB3AdminPvInfoDirectRegistService.class, new WEB3AdminPvInfoDirectRegistServiceImpl());
        
        //ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ詳細サービス
        Services.registerService(WEB3PvInfoPrivateInformationDetailService.class, new WEB3PvInfoPrivateInformationDetailServiceImpl());
        
        //ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧サービス
        Services.registerService(WEB3PvInfoPrivateInformationListService.class, new WEB3PvInfoPrivateInformationListServiceImpl());
        

        // Service の Interceptor 設定処理 ----------------------
        // 自動トランザクション設定        
        
        //管理者表示設定変更サービス
        Services.addInterceptor(WEB3AdminPvInfoConditionChangeService.class, new WEB3AdminPvInfoServiceInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoConditionChangeService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoConditionChangeService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者表示設定削除サービス
        Services.addInterceptor(WEB3AdminPvInfoConditionDelService.class, new WEB3AdminPvInfoServiceInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoConditionDelService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoConditionDelService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者表示設定詳細サービス
        Services.addInterceptor(WEB3AdminPvInfoConditionDetailService.class, new WEB3AdminPvInfoServiceInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoConditionDetailService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoConditionDetailService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //管理者表示設定一覧サービス
        Services.addInterceptor(WEB3AdminPvInfoConditionListService.class, new WEB3AdminPvInfoServiceInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoConditionListService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoConditionListService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //管理者表示設定登録サービス
        Services.addInterceptor(WEB3AdminPvInfoConditionRegistService.class, new WEB3AdminPvInfoServiceInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoConditionRegistService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoConditionRegistService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者ダイレクト指定変更サービス
        Services.addInterceptor(WEB3AdminPvInfoDirectChangeService.class, new WEB3AdminPvInfoServiceInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoDirectChangeService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoDirectChangeService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者ダイレクト指定登録サービス
        Services.addInterceptor(WEB3AdminPvInfoDirectRegistService.class, new WEB3AdminPvInfoServiceInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoDirectRegistService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoDirectRegistService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ詳細サービス
        Services.addInterceptor(WEB3PvInfoPrivateInformationDetailService.class, new WEB3PvInfoPrivateInformationDetailServiceInterceptor());
        Services.addInterceptor(WEB3PvInfoPrivateInformationDetailService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3PvInfoPrivateInformationDetailService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧サービス
        Services.addInterceptor(WEB3PvInfoPrivateInformationListService.class, new WEB3PvInfoPrivateInformationListServiceInterceptor());
        Services.addInterceptor(WEB3PvInfoPrivateInformationListService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3PvInfoPrivateInformationListService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // Message の登録処理 ----------------------
        //ダイレクト指定メッセージ削除リクエスト 
        regClass(WEB3PvInfoDirectMessageDeleteRequest.class);
        //ダイレクト指定メッセージ削除レスポンス 
        regClass(WEB3PvInfoDirectMessageDeleteResponse.class);
        
        //ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ詳細リクエスト    
        regClass(WEB3PvInfoPrivateInformationDetailRequest.class);
        //ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ詳細レスポンス    
        regClass(WEB3PvInfoPrivateInformationDetailResponse.class);

        //顧客連絡リクエスト   
        regClass(WEB3PvInfoAccountConnectionRequest.class);
        //顧客連絡レスポンス   
        regClass(WEB3PvInfoAccountConnectionResponse.class);

        //証券会社連絡リクエスト 
        regClass(WEB3PvInfoInstitutionConnectionRequest.class);
        //証券会社連絡レスポンス 
        regClass(WEB3PvInfoInstitutionConnectionResponse.class);

        //注文約定状況リクエスト 
        regClass(WEB3PvInfoOrderExecStateRequest.class);
        //注文約定状況レスポンス 
        regClass(WEB3PvInfoOrderExecStateResponse.class);

        //管理者・現在状況更新リクエスト 
        regClass(WEB3AdminPvInfoConditionUpdateRequest.class);
        //管理者・現在状況更新レスポンス 
        regClass(WEB3AdminPvInfoConditionUpdateResponse.class);
        
        //管理者・表示設定一覧リクエスト 
        regClass(WEB3AdminPvInfoConditionListRequest.class);
        //管理者・表示設定一覧レスポンス
        regClass(WEB3AdminPvInfoConditionListResponse.class);

        //管理者・表示設定詳細リクエスト 
        regClass(WEB3AdminPvInfoConditionDetailRequest.class);
        //管理者・表示設定詳細レスポンス 
        regClass(WEB3AdminPvInfoConditionDetailResponse.class);

        //管理者・表示設定登録入力リクエスト   
        regClass(WEB3AdminPvInfoConditionRegistInputRequest.class);
        //管理者・表示設定登録入力レスポンス   
        regClass(WEB3AdminPvInfoConditionRegistInputResponse.class);
        
        //管理者・表示設定登録確認リクエスト   
        regClass(WEB3AdminPvInfoConditionRegistConfirmRequest.class);
        //管理者・表示設定登録確認レスポンス   
        regClass(WEB3AdminPvInfoConditionRegistConfirmResponse.class);
        
        //管理者・表示設定登録完了リクエスト   
        regClass(WEB3AdminPvInfoConditionRegistCompleteRequest.class);
        //管理者・表示設定登録完了レスポンス   
        regClass(WEB3AdminPvInfoConditionRegistCompleteResponse.class);
        
        //管理者・表示設定変更入力リクエスト   
        regClass(WEB3AdminPvInfoConditionChangeInputRequest.class);
        //管理者・表示設定変更入力レスポンス   
        regClass(WEB3AdminPvInfoConditionChangeInputResponse.class);
        
        //管理者・表示設定変更確認リクエスト   
        regClass(WEB3AdminPvInfoConditionChangeConfirmRequest.class);
        //管理者・表示設定変更確認レスポンス   
        regClass(WEB3AdminPvInfoConditionChangeConfirmResponse.class);
        
        //管理者・表示設定変更完了リクエスト   
        regClass(WEB3AdminPvInfoConditionChangeCompleteRequest.class);
        //管理者・表示設定変更完了レスポンス   
        regClass(WEB3AdminPvInfoConditionChangeCompleteResponse.class);
        
        //管理者・表示設定削除確認リクエスト   
        regClass(WEB3AdminPvInfoConditionDelConfirmRequest.class);
        //管理者・表示設定削除確認レスポンス   
        regClass(WEB3AdminPvInfoConditionDelConfirmResponse.class);
        
        //管理者・表示設定削除完了リクエスト   
        regClass(WEB3AdminPvInfoConditionDelCompleteRequest.class);
        //管理者・表示設定削除完了レスポンス   
        regClass(WEB3AdminPvInfoConditionDelCompleteResponse.class);
        
        //管理者・ダイレクト指定登録入力リクエスト    
        regClass(WEB3AdminPvInfoDirectRegistInputRequest.class);
        //管理者・ダイレクト指定登録入力レスポンス    
        regClass(WEB3AdminPvInfoDirectRegistInputResponse.class);
        
        //管理者・ダイレクト指定登録確認リクエスト    
        regClass(WEB3AdminPvInfoDirectRegistConfirmRequest.class);
        //管理者・ダイレクト指定登録確認レスポンス    
        regClass(WEB3AdminPvInfoDirectRegistConfirmResponse.class);
        
        //管理者・ダイレクト指定登録完了リクエスト    
        regClass(WEB3AdminPvInfoDirectRegistCompleteRequest.class);
        //管理者・ダイレクト指定登録完了レスポンス    
        regClass(WEB3AdminPvInfoDirectRegistCompleteResponse.class);
        
        //管理者・ダイレクト指定登録アップロード中止リクエスト  
        regClass(WEB3AdminPvInfoDirectRegistCancelRequest.class);
        //管理者・ダイレクト指定登録アップロード中止レスポンス  
        regClass(WEB3AdminPvInfoDirectRegistCancelResponse.class);
        
        //管理者・ダイレクト指定変更入力リクエスト    
        regClass(WEB3AdminPvInfoDirectChangeInputRequest.class);
        //管理者・ダイレクト指定変更入力レスポンス    
        regClass(WEB3AdminPvInfoDirectChangeInputResponse.class);
        
        //管理者・ダイレクト指定変更確認リクエスト    
        regClass(WEB3AdminPvInfoDirectChangeConfirmRequest.class);
        //管理者・ダイレクト指定変更確認レスポンス    
        regClass(WEB3AdminPvInfoDirectChangeConfirmResponse.class);
        
        //管理者・ダイレクト指定変更完了リクエスト    
        regClass(WEB3AdminPvInfoDirectChangeCompleteRequest.class);
        //管理者・ダイレクト指定変更完了レスポンス    
        regClass(WEB3AdminPvInfoDirectChangeCompleteResponse.class);
        
        //管理者・ダイレクト指定変更アップロード中止リクエスト  
        regClass(WEB3AdminPvInfoDirectChangeCancelRequest.class);
        //管理者・ダイレクト指定変更アップロード中止レスポンス  
        regClass(WEB3AdminPvInfoDirectChangeCancelResponse.class);
        
        //Handler の登録処理 ----------------------
        
        //ダイレクト指定メッセージ削除 用ハンドラーの登録
        regHandler(WEB3PvInfoAppPlugin.class, WEB3PvInfoDirectMessageDeleteRequest.class, WEB3PvInfoPrivateInformationDetailHandler.class, "deleteDirectMessage");
        
        //ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ詳細 用ハンドラーの登録    
        regHandler(WEB3PvInfoAppPlugin.class, WEB3PvInfoPrivateInformationDetailRequest.class, WEB3PvInfoPrivateInformationDetailHandler.class, "getDetailedScreen");
        
        //顧客連絡 用ハンドラーの登録   
        regHandler(WEB3PvInfoAppPlugin.class, WEB3PvInfoAccountConnectionRequest.class, WEB3PvInfoPrivateInformationListHandler.class, "getAccountConnectionScreen");
        
        //証券会社連絡 用ハンドラーの登録 
        regHandler(WEB3PvInfoAppPlugin.class, WEB3PvInfoInstitutionConnectionRequest.class, WEB3PvInfoPrivateInformationListHandler.class, "getInstitutionConnectionScreen");
        
        //注文約定状況 用ハンドラーの登録 
        regHandler(WEB3PvInfoAppPlugin.class, WEB3PvInfoOrderExecStateRequest.class, WEB3PvInfoPrivateInformationListHandler.class, "getOrderExecStateScreen");
        
        //管理者・現在状況更新 用ハンドラーの登録 
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoConditionUpdateRequest.class, WEB3AdminPvInfoConditionListHandler.class, "updateCondition");
        
        //管理者・表示設定一覧 用ハンドラーの登録 
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoConditionListRequest.class, WEB3AdminPvInfoConditionListHandler.class, "getConditionListScreen");
        
        //管理者・表示設定詳細 用ハンドラーの登録 
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoConditionDetailRequest.class, WEB3AdminPvInfoConditionDetailHandler.class, "getConditionDetailScreen");
        
        //管理者・表示設定登録入力 用ハンドラーの登録   
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoConditionRegistInputRequest.class, WEB3AdminPvInfoConditionRegistHandler.class, "getConditionRegistInputScreen"); 
        
        //管理者・表示設定登録確認 用ハンドラーの登録   
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoConditionRegistConfirmRequest.class, WEB3AdminPvInfoConditionRegistHandler.class, "confirmConditionRegist"); 
        
        //管理者・表示設定登録完了 用ハンドラーの登録   
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoConditionRegistCompleteRequest.class, WEB3AdminPvInfoConditionRegistHandler.class, "completeConditionRegist");
        
        //管理者・表示設定変更入力 用ハンドラーの登録   
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoConditionChangeInputRequest.class, WEB3AdminPvInfoConditionChangeHandler.class, "getConditionChangeInputScreen"); 
        
        //管理者・表示設定変更確認 用ハンドラーの登録   
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoConditionChangeConfirmRequest.class, WEB3AdminPvInfoConditionChangeHandler.class, "confirmConditionChange"); 
        
        //管理者・表示設定変更完了 用ハンドラーの登録   
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoConditionChangeCompleteRequest.class, WEB3AdminPvInfoConditionChangeHandler.class, "completeConditionChange");
        
        //管理者・表示設定削除確認 用ハンドラーの登録   
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoConditionDelConfirmRequest.class, WEB3AdminPvInfoConditionDelHandler.class, "confirmConditionDel"); 
        
        //管理者・表示設定削除完了 用ハンドラーの登録   
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoConditionDelCompleteRequest.class, WEB3AdminPvInfoConditionDelHandler.class, "completeConditionDel");
        
        //管理者・ダイレクト指定登録入力 用ハンドラーの登録    
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoDirectRegistInputRequest.class, WEB3AdminPvInfoDirectRegistHandler.class, "getDirectRegistInputScreen"); 
        
        //管理者・ダイレクト指定登録確認 用ハンドラーの登録    
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoDirectRegistConfirmRequest.class, WEB3AdminPvInfoDirectRegistHandler.class, "confirmDirectRegist"); 
        
        //管理者・ダイレクト指定登録完了 用ハンドラーの登録    
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoDirectRegistCompleteRequest.class, WEB3AdminPvInfoDirectRegistHandler.class, "completeDirectRegist"); 
        
        //管理者・ダイレクト指定登録アップロード中止 用ハンドラーの登録  
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoDirectRegistCancelRequest.class, WEB3AdminPvInfoDirectRegistHandler.class, "undoDirectRegistUpload"); 
        
        //管理者・ダイレクト指定変更入力 用ハンドラーの登録    
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoDirectChangeInputRequest.class, WEB3AdminPvInfoDirectChangeHandler.class, "getDirectChangeInputScreen"); 
        
        //管理者・ダイレクト指定変更確認 用ハンドラーの登録    
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoDirectChangeConfirmRequest.class, WEB3AdminPvInfoDirectChangeHandler.class, "confirmDirectChange"); 
        
        //管理者・ダイレクト指定変更完了 用ハンドラーの登録    
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoDirectChangeCompleteRequest.class, WEB3AdminPvInfoDirectChangeHandler.class, "completeDirectChange"); 
        
        //管理者・ダイレクト指定変更アップロード中止 用ハンドラーの登録  
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoDirectChangeCancelRequest.class, WEB3AdminPvInfoDirectChangeHandler.class, "undoDirectChangeUpload"); 

        log.exiting(STR_METHOD_NAME);
    }  
}
@

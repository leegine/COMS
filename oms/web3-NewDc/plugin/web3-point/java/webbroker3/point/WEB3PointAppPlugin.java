head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.01.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-ポイントシステム プラグイン(WEB3PointAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/13 李頴淵 (中訊) 新規作成
*/
package webbroker3.point;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.point.data.WEB3PointMasterDatabaseExtensions;
import webbroker3.point.handler.WEB3AdminPointCategoryChangeHandler;
import webbroker3.point.handler.WEB3AdminPointCategoryDeleteHandler;
import webbroker3.point.handler.WEB3AdminPointCategoryReferenceHandler;
import webbroker3.point.handler.WEB3AdminPointCategoryRegistHandler;
import webbroker3.point.handler.WEB3AdminPointExchangeApplyAcceptHandler;
import webbroker3.point.handler.WEB3AdminPointManageByCustomerHandler;
import webbroker3.point.handler.WEB3AdminPointPackageAdjustHandler;
import webbroker3.point.handler.WEB3AdminPointPremiumChangeHandler;
import webbroker3.point.handler.WEB3AdminPointPremiumDeleteHandler;
import webbroker3.point.handler.WEB3AdminPointPremiumReferenceHandler;
import webbroker3.point.handler.WEB3AdminPointPremiumRegistHandler;
import webbroker3.point.handler.WEB3AdminPointTradeBonusPlanReferenceHandler;
import webbroker3.point.handler.WEB3PointCommissionInfoReferenceHandler;
import webbroker3.point.handler.WEB3PointExchangeApplyHandler;
import webbroker3.point.handler.WEB3PointTradeBonusPlanReferenceHandler;
import webbroker3.point.message.WEB3AdminPointAdjustCompleteRequest;
import webbroker3.point.message.WEB3AdminPointAdjustCompleteResponse;
import webbroker3.point.message.WEB3AdminPointAdjustConfirmRequest;
import webbroker3.point.message.WEB3AdminPointAdjustConfirmResponse;
import webbroker3.point.message.WEB3AdminPointAdjustInputRequest;
import webbroker3.point.message.WEB3AdminPointAdjustInputResponse;
import webbroker3.point.message.WEB3AdminPointCategoryChangeCommonRequest;
import webbroker3.point.message.WEB3AdminPointCategoryChangeCompleteRequest;
import webbroker3.point.message.WEB3AdminPointCategoryChangeCompleteResponse;
import webbroker3.point.message.WEB3AdminPointCategoryChangeConfirmRequest;
import webbroker3.point.message.WEB3AdminPointCategoryChangeConfirmResponse;
import webbroker3.point.message.WEB3AdminPointCategoryChangeInputRequest;
import webbroker3.point.message.WEB3AdminPointCategoryChangeInputResponse;
import webbroker3.point.message.WEB3AdminPointCategoryDeleteCompleteRequest;
import webbroker3.point.message.WEB3AdminPointCategoryDeleteCompleteResponse;
import webbroker3.point.message.WEB3AdminPointCategoryDeleteConfirmRequest;
import webbroker3.point.message.WEB3AdminPointCategoryDeleteConfirmResponse;
import webbroker3.point.message.WEB3AdminPointCategoryReferenceRequest;
import webbroker3.point.message.WEB3AdminPointCategoryReferenceResponse;
import webbroker3.point.message.WEB3AdminPointCategoryRegistCommonRequest;
import webbroker3.point.message.WEB3AdminPointCategoryRegistCompleteRequest;
import webbroker3.point.message.WEB3AdminPointCategoryRegistCompleteResponse;
import webbroker3.point.message.WEB3AdminPointCategoryRegistConfirmRequest;
import webbroker3.point.message.WEB3AdminPointCategoryRegistConfirmResponse;
import webbroker3.point.message.WEB3AdminPointCategoryRegistInputRequest;
import webbroker3.point.message.WEB3AdminPointCategoryRegistInputResponse;
import webbroker3.point.message.WEB3AdminPointExchangeAcceptRequest;
import webbroker3.point.message.WEB3AdminPointExchangeAcceptResponse;
import webbroker3.point.message.WEB3AdminPointExchangeCancelCompleteRequest;
import webbroker3.point.message.WEB3AdminPointExchangeCancelCompleteResponse;
import webbroker3.point.message.WEB3AdminPointExchangeCancelConfirmRequest;
import webbroker3.point.message.WEB3AdminPointExchangeCancelConfirmResponse;
import webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseCompleteRequest;
import webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseCompleteResponse;
import webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseConfirmRequest;
import webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseConfirmResponse;
import webbroker3.point.message.WEB3AdminPointExchangeCommonRequest;
import webbroker3.point.message.WEB3AdminPointExchangeCompleteCommonRequest;
import webbroker3.point.message.WEB3AdminPointExchangeStateReferenceRequest;
import webbroker3.point.message.WEB3AdminPointExchangeStateReferenceResponse;
import webbroker3.point.message.WEB3AdminPointHistoryReferenceRequest;
import webbroker3.point.message.WEB3AdminPointHistoryReferenceResponse;
import webbroker3.point.message.WEB3AdminPointManageCommonRequest;
import webbroker3.point.message.WEB3AdminPointManageDisplayRequest;
import webbroker3.point.message.WEB3AdminPointManageDisplayResponse;
import webbroker3.point.message.WEB3AdminPointPremiumChangeCommonRequest;
import webbroker3.point.message.WEB3AdminPointPremiumChangeCompleteRequest;
import webbroker3.point.message.WEB3AdminPointPremiumChangeCompleteResponse;
import webbroker3.point.message.WEB3AdminPointPremiumChangeConfirmRequest;
import webbroker3.point.message.WEB3AdminPointPremiumChangeConfirmResponse;
import webbroker3.point.message.WEB3AdminPointPremiumChangeInputRequest;
import webbroker3.point.message.WEB3AdminPointPremiumChangeInputResponse;
import webbroker3.point.message.WEB3AdminPointPremiumDeleteCompleteRequest;
import webbroker3.point.message.WEB3AdminPointPremiumDeleteCompleteResponse;
import webbroker3.point.message.WEB3AdminPointPremiumDeleteConfirmRequest;
import webbroker3.point.message.WEB3AdminPointPremiumDeleteConfirmResponse;
import webbroker3.point.message.WEB3AdminPointPremiumReferenceRequest;
import webbroker3.point.message.WEB3AdminPointPremiumReferenceResponse;
import webbroker3.point.message.WEB3AdminPointPremiumRegistCommonRequest;
import webbroker3.point.message.WEB3AdminPointPremiumRegistCompleteRequest;
import webbroker3.point.message.WEB3AdminPointPremiumRegistCompleteResponse;
import webbroker3.point.message.WEB3AdminPointPremiumRegistConfirmRequest;
import webbroker3.point.message.WEB3AdminPointPremiumRegistConfirmResponse;
import webbroker3.point.message.WEB3AdminPointPremiumRegistInputRequest;
import webbroker3.point.message.WEB3AdminPointPremiumRegistInputResponse;
import webbroker3.point.message.WEB3AdminPointTradeBonusPlanReferenceRequest;
import webbroker3.point.message.WEB3AdminPointTradeBonusPlanReferenceResponse;
import webbroker3.point.message.WEB3AdminPointUploadCancelRequest;
import webbroker3.point.message.WEB3AdminPointUploadCancelResponse;
import webbroker3.point.message.WEB3AdminPointUploadCompleteRequest;
import webbroker3.point.message.WEB3AdminPointUploadCompleteResponse;
import webbroker3.point.message.WEB3AdminPointUploadConfirmRequest;
import webbroker3.point.message.WEB3AdminPointUploadConfirmResponse;
import webbroker3.point.message.WEB3AdminPointUploadInputRequest;
import webbroker3.point.message.WEB3AdminPointUploadInputResponse;
import webbroker3.point.message.WEB3PointApplyCommonRequest;
import webbroker3.point.message.WEB3PointApplyCompleteRequest;
import webbroker3.point.message.WEB3PointApplyCompleteResponse;
import webbroker3.point.message.WEB3PointApplyConfirmRequest;
import webbroker3.point.message.WEB3PointApplyConfirmResponse;
import webbroker3.point.message.WEB3PointApplyInputRequest;
import webbroker3.point.message.WEB3PointApplyInputResponse;
import webbroker3.point.message.WEB3PointApplyReferenceRequest;
import webbroker3.point.message.WEB3PointApplyReferenceResponse;
import webbroker3.point.message.WEB3PointCommissionInfoReferenceRequest;
import webbroker3.point.message.WEB3PointCommissionInfoReferenceResponse;
import webbroker3.point.message.WEB3PointTradeBonusPlanReferenceRequest;
import webbroker3.point.message.WEB3PointTradeBonusPlanReferenceResponse;
import webbroker3.point.service.delegate.WEB3AdminPointCategoryChangeService;
import webbroker3.point.service.delegate.WEB3AdminPointCategoryDeleteService;
import webbroker3.point.service.delegate.WEB3AdminPointCategoryReferenceService;
import webbroker3.point.service.delegate.WEB3AdminPointCategoryRegistService;
import webbroker3.point.service.delegate.WEB3AdminPointExchangeApplyAcceptService;
import webbroker3.point.service.delegate.WEB3AdminPointManageByCustomerService;
import webbroker3.point.service.delegate.WEB3AdminPointPackageAdjustService;
import webbroker3.point.service.delegate.WEB3AdminPointPremiumChangeService;
import webbroker3.point.service.delegate.WEB3AdminPointPremiumDeleteService;
import webbroker3.point.service.delegate.WEB3AdminPointPremiumReferenceService;
import webbroker3.point.service.delegate.WEB3AdminPointPremiumRegistService;
import webbroker3.point.service.delegate.WEB3AdminPointTradeBonusPlanReferenceService;
import webbroker3.point.service.delegate.WEB3PointCommissionInfoReferenceService;
import webbroker3.point.service.delegate.WEB3PointExchangeApplyService;
import webbroker3.point.service.delegate.WEB3PointTradeBonusPlanReferenceService;
import webbroker3.point.service.delegate.stdimpls.WEB3AdminPointCategoryChangeServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3AdminPointCategoryDeleteServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3AdminPointCategoryReferenceServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3AdminPointCategoryRegistServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3AdminPointExchangeApplyAcceptServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3AdminPointManageByCustomerServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3AdminPointPackageAdjustServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3AdminPointPremiumChangeServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3AdminPointPremiumDeleteServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3AdminPointPremiumReferenceServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3AdminPointPremiumRegistServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3AdminPointTradeBonusPlanReferenceServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3PointCommissionInfoReferenceServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3PointExchangeApplyServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3PointTradeBonusPlanReferenceServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-ポイントシステム プラグインクラス。
 *                                                                
 * @@author 李頴淵
 * @@version 1.0
 */
public final class WEB3PointAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PointAppPlugin.class);
    
    /**
     * コンストラクタ。
     */
    public WEB3PointAppPlugin()
    {
        String STR_METHOD_NAME = " WEB3PointAppPlugin()";
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

        plug(WEB3PointAppPlugin.class);

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

        // DatabaseExtensions のプラグイン処理 ----------------------
        WEB3PointMasterDatabaseExtensions.plug();

        // Service の登録処理 ----------------------
        //ポイント商品マネージャ
        Services.registerService(
            WEB3PointProductManager.class, 
            new WEB3PointProductManagerImpl());
        
        //ポイント申込マネージャ
        Services.registerService(
            WEB3PointApplyManager.class,
            new WEB3PointApplyManagerImpl());
        
        //ポイント交換申込サービス
        Services.registerService(
            WEB3PointExchangeApplyService.class,
            new WEB3PointExchangeApplyServiceImpl());
        
        //カテゴリー一覧サービス
        Services.registerService(
            WEB3AdminPointCategoryReferenceService.class,
            new WEB3AdminPointCategoryReferenceServiceImpl());
        
        //カテゴリー削除サービス
        Services.registerService(
            WEB3AdminPointCategoryDeleteService.class,
            new WEB3AdminPointCategoryDeleteServiceImpl());
        
        //カテゴリー訂正サービス
        Services.registerService(
            WEB3AdminPointCategoryChangeService.class,
            new WEB3AdminPointCategoryChangeServiceImpl());
        
        //カテゴリー登録サービス
        Services.registerService(
            WEB3AdminPointCategoryRegistService.class, 
            new WEB3AdminPointCategoryRegistServiceImpl());
        
        //ポイント一括調整サービス
        Services.registerService(
            WEB3AdminPointPackageAdjustService.class, 
            new WEB3AdminPointPackageAdjustServiceImpl());
        
        //ポイント交換受付サービス
        Services.registerService(
            WEB3AdminPointExchangeApplyAcceptService.class, 
            new WEB3AdminPointExchangeApplyAcceptServiceImpl());
        
        //景品一覧サービス
        Services.registerService(
            WEB3AdminPointPremiumReferenceService.class,
            new WEB3AdminPointPremiumReferenceServiceImpl());
        
        //景品削除サービス
        Services.registerService(
            WEB3AdminPointPremiumDeleteService.class,
            new WEB3AdminPointPremiumDeleteServiceImpl());
            
        //景品訂正サービス
        Services.registerService(
            WEB3AdminPointPremiumChangeService.class,
            new WEB3AdminPointPremiumChangeServiceImpl());
            
        //景品登録サービス
        Services.registerService(
            WEB3AdminPointPremiumRegistService.class,
            new WEB3AdminPointPremiumRegistServiceImpl());
            
        //顧客別ポイント管理サービス
        Services.registerService(
            WEB3AdminPointManageByCustomerService.class,
            new WEB3AdminPointManageByCustomerServiceImpl());                 
            
        //株式手数料無料情報照会サービス
        Services.registerService(
            WEB3PointCommissionInfoReferenceService.class,
            new WEB3PointCommissionInfoReferenceServiceImpl());                 
            
        //トレードボーナスプラン照会サービス
        Services.registerService(
            WEB3PointTradeBonusPlanReferenceService.class,
            new WEB3PointTradeBonusPlanReferenceServiceImpl());                 
            
        //管理者トレードボーナスプラン照会サービス
        Services.registerService(
            WEB3AdminPointTradeBonusPlanReferenceService.class,
            new WEB3AdminPointTradeBonusPlanReferenceServiceImpl()); 
        
        //Service の Interceptor 設定処理 ----------------------
        //自動トランザクション設定       
        //ポイント交換申込サービス
        Services.addInterceptor(
            WEB3PointExchangeApplyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //カテゴリー一覧サービス
        Services.addInterceptor(
            WEB3AdminPointCategoryReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //カテゴリー削除サービス
        Services.addInterceptor(
            WEB3AdminPointCategoryDeleteService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //カテゴリー訂正サービス
        Services.addInterceptor(
            WEB3AdminPointCategoryChangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //カテゴリー登録サービス
        Services.addInterceptor(
            WEB3AdminPointCategoryRegistService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //ポイント一括調整サービス
        Services.addInterceptor(
            WEB3AdminPointPackageAdjustService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //ポイント交換受付サービス
        Services.addInterceptor(
            WEB3AdminPointExchangeApplyAcceptService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //景品一覧サービス
        Services.addInterceptor(
            WEB3AdminPointPremiumReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //景品削除サービス
        Services.addInterceptor(
            WEB3AdminPointPremiumDeleteService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //景品訂正サービス
        Services.addInterceptor(
            WEB3AdminPointPremiumChangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //景品登録サービス
        Services.addInterceptor(
            WEB3AdminPointPremiumRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //顧客別ポイント管理サービス
        Services.addInterceptor(
            WEB3AdminPointManageByCustomerService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));    
            
        //株式手数料無料情報照会サービスインタセプタ
        Services.addInterceptor(
            WEB3PointCommissionInfoReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));    
            
        //トレードボーナスプラン照会サービスインタセプタ
        Services.addInterceptor(
            WEB3PointTradeBonusPlanReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));    
                    
        // Service.execute()呼び出し前後に  ----------------------
        // 処理開始時刻と処理終了時刻をログ出力するように設定する
        Services.addInterceptor(
            WEB3PointExchangeApplyService.class,
            new WEB3LogSysTimeInterceptor());
        
        //カテゴリー一覧サービス
        Services.addInterceptor(
            WEB3AdminPointCategoryReferenceService.class,
            new WEB3LogSysTimeInterceptor());
        
        //カテゴリー削除サービス
        Services.addInterceptor(
            WEB3AdminPointCategoryDeleteService.class,
            new WEB3LogSysTimeInterceptor());
        
        //カテゴリー訂正サービス
        Services.addInterceptor(
            WEB3AdminPointCategoryChangeService.class,
            new WEB3LogSysTimeInterceptor());
        
        //カテゴリー登録サービス
        Services.addInterceptor(
            WEB3AdminPointCategoryRegistService.class, 
            new WEB3LogSysTimeInterceptor());
        
        //ポイント一括調整サービス
        Services.addInterceptor(
            WEB3AdminPointPackageAdjustService.class, 
            new WEB3LogSysTimeInterceptor());
        
        //ポイント交換受付サービス
        Services.addInterceptor(
            WEB3AdminPointExchangeApplyAcceptService.class, 
            new WEB3LogSysTimeInterceptor());
        
        //景品一覧サービス
        Services.addInterceptor(
            WEB3AdminPointPremiumReferenceService.class,
            new WEB3LogSysTimeInterceptor());
        
        //景品削除サービス
        Services.addInterceptor(
            WEB3AdminPointPremiumDeleteService.class,
            new WEB3LogSysTimeInterceptor());
            
        //景品訂正サービス
        Services.addInterceptor(
            WEB3AdminPointPremiumChangeService.class,
            new WEB3LogSysTimeInterceptor());
            
        //景品登録サービス
        Services.addInterceptor(
            WEB3AdminPointPremiumRegistService.class,
            new WEB3LogSysTimeInterceptor());
            
        //顧客別ポイント管理サービス
        Services.addInterceptor(
            WEB3AdminPointManageByCustomerService.class,
            new WEB3LogSysTimeInterceptor()); 
            
        //株式手数料無料情報照会サービスインタセプタ
        Services.addInterceptor(
            WEB3PointCommissionInfoReferenceService.class,
            new WEB3LogSysTimeInterceptor());
            
        //トレードボーナスプラン照会サービスインタセプタ
        Services.addInterceptor(
            WEB3PointTradeBonusPlanReferenceService.class,
            new WEB3LogSysTimeInterceptor());
        
        
        // Service.execute()呼び出し前後に  ----------------------
        // 処理開始時刻と処理終了時刻をログ出力するように設定する
        //ポイント交換申込サービス
        Services.addInterceptor(
            WEB3PointExchangeApplyService.class, 
            new WEB3PointExchangeApplyServiceInterceptor());
        
        //ポイント一括調整サービス
        Services.addInterceptor(
            WEB3AdminPointPackageAdjustService.class,
            new WEB3AdminPointPackageAdjustServiceInterceptor());
            
        //株式手数料無料情報照会サービスインタセプタ
        Services.addInterceptor(
            WEB3PointCommissionInfoReferenceService.class,
            new WEB3PointCommissionInfoReferenceServiceInterceptor());
            
        //トレードボーナスプラン照会サービスインタセプタ
        Services.addInterceptor(
            WEB3PointTradeBonusPlanReferenceService.class,
            new WEB3PointTradeBonusPlanReferenceServiceInterceptor());
            
        // Message の登録処理 ----------------------
        //ポイントサービス画面リクエスト
        regClass(WEB3PointApplyReferenceRequest.class);
        //ポイントサービス画面レスポンス
        regClass(WEB3PointApplyReferenceResponse.class);
        
        //ポイント申込確認リクエスト
        regClass(WEB3PointApplyConfirmRequest.class);
        //ポイント申込確認レスポンス
        regClass(WEB3PointApplyConfirmResponse.class);
        
        //ポイント申込完了リクエスト
        regClass(WEB3PointApplyCompleteRequest.class);
        //ポイント申込完了レスポンス
        regClass(WEB3PointApplyCompleteResponse.class);
        
        //ポイント申込共通リクエスト
        regClass(WEB3PointApplyCommonRequest.class);
        
        //ポイント申込選択リクエスト
        regClass(WEB3PointApplyInputRequest.class);
        //ポイント申込選択レスポンス
        regClass(WEB3PointApplyInputResponse.class);
        
        //アップロード確認リクエスト
        regClass(WEB3AdminPointUploadConfirmRequest.class);
        //アップロード確認レスポンス
        regClass(WEB3AdminPointUploadConfirmResponse.class);
        
        //アップロード完了リクエスト
        regClass(WEB3AdminPointUploadCompleteRequest.class);
        //アップロード完了レスポンス
        regClass(WEB3AdminPointUploadCompleteResponse.class);
        
        //アップロード中止リクエスト
        regClass(WEB3AdminPointUploadCancelRequest.class);
        //アップロード中止レスポンス
        regClass(WEB3AdminPointUploadCancelResponse.class);
        
        //アップロード入力リクエスト
        regClass(WEB3AdminPointUploadInputRequest.class);
        //アップロード入力レスポンス
        regClass(WEB3AdminPointUploadInputResponse.class);
        
        //カテゴリー一覧リクエスト
        regClass(WEB3AdminPointCategoryReferenceRequest.class);
        //カテゴリー一覧レスポンス
        regClass(WEB3AdminPointCategoryReferenceResponse.class);
        
        //カテゴリー削除確認リクエスト
        regClass(WEB3AdminPointCategoryDeleteConfirmRequest.class);
        //カテゴリー削除確認レスポンス
        regClass(WEB3AdminPointCategoryDeleteConfirmResponse.class);
        
        //カテゴリー削除完了リクエスト
        regClass(WEB3AdminPointCategoryDeleteCompleteRequest.class);
        //カテゴリー削除完了レスポンス
        regClass(WEB3AdminPointCategoryDeleteCompleteResponse.class);
        
        //カテゴリー訂正確認リクエスト
        regClass(WEB3AdminPointCategoryChangeConfirmRequest.class);
        //カテゴリー訂正確認レスポンス
        regClass(WEB3AdminPointCategoryChangeConfirmResponse.class);
        
        //カテゴリー訂正完了リクエスト
        regClass(WEB3AdminPointCategoryChangeCompleteRequest.class);
        //カテゴリー訂正完了レスポンス
        regClass(WEB3AdminPointCategoryChangeCompleteResponse.class);
        
        //カテゴリー訂正共通リクエスト
        regClass(WEB3AdminPointCategoryChangeCommonRequest.class);
        
        //カテゴリー訂正入力リクエスト
        regClass(WEB3AdminPointCategoryChangeInputRequest.class);
        //カテゴリー訂正入力レスポンス
        regClass(WEB3AdminPointCategoryChangeInputResponse.class);
        
        //カテゴリー登録確認リクエスト
        regClass(WEB3AdminPointCategoryRegistConfirmRequest.class);
        //カテゴリー登録確認レスポンス
        regClass(WEB3AdminPointCategoryRegistConfirmResponse.class);
        
        //カテゴリー登録完了リクエスト
        regClass(WEB3AdminPointCategoryRegistCompleteRequest.class);
        //カテゴリー登録完了レスポンス
        regClass(WEB3AdminPointCategoryRegistCompleteResponse.class);
        
        //カテゴリー登録共通リクエスト
        regClass(WEB3AdminPointCategoryRegistCommonRequest.class);
        
        //カテゴリー登録入力リクエスト
        regClass(WEB3AdminPointCategoryRegistInputRequest.class);
        //カテゴリー登録入力レスポンス
        regClass(WEB3AdminPointCategoryRegistInputResponse.class);
        
        //ポイント管理画面リクエスト
        regClass(WEB3AdminPointManageDisplayRequest.class);
        //ポイント管理画面レスポンス
        regClass(WEB3AdminPointManageDisplayResponse.class);
        
        //ポイント管理共通リクエスト
        regClass(WEB3AdminPointManageCommonRequest.class);
        
        //ポイント交換一覧リクエスト
        regClass(WEB3AdminPointExchangeStateReferenceRequest.class);
        //ポイント交換一覧レスポンス
        regClass(WEB3AdminPointExchangeStateReferenceResponse.class);
        
        //ポイント交換完了共通リクエスト
        regClass(WEB3AdminPointExchangeCompleteCommonRequest.class);
        
        //ポイント交換共通リクエスト
        regClass(WEB3AdminPointExchangeCommonRequest.class);
        
        //ポイント交換取消解除確認リクエスト
        regClass(WEB3AdminPointExchangeCancelReleaseConfirmRequest.class);
        //ポイント交換取消解除確認レスポンス
        regClass(WEB3AdminPointExchangeCancelReleaseConfirmResponse.class);
        
        //ポイント交換取消解除完了リクエスト
        regClass(WEB3AdminPointExchangeCancelReleaseCompleteRequest.class);
        //ポイント交換取消解除完了レスポンス
        regClass(WEB3AdminPointExchangeCancelReleaseCompleteResponse.class);
        
        //ポイント交換取消確認リクエスト
        regClass(WEB3AdminPointExchangeCancelConfirmRequest.class);
        //ポイント交換取消確認レスポンス
        regClass(WEB3AdminPointExchangeCancelConfirmResponse.class);
        
        //ポイント交換取消完了リクエスト
        regClass(WEB3AdminPointExchangeCancelCompleteRequest.class);
        //ポイント交換取消完了レスポンス
        regClass(WEB3AdminPointExchangeCancelCompleteResponse.class);
        
        //ポイント交換受付リクエスト
        regClass(WEB3AdminPointExchangeAcceptRequest.class);
        //ポイント交換受付レスポンス
        regClass(WEB3AdminPointExchangeAcceptResponse.class);
        
        //ポイント調整確認リクエスト
        regClass(WEB3AdminPointAdjustConfirmRequest.class);
        //ポイント調整確認レスポンス
        regClass(WEB3AdminPointAdjustConfirmResponse.class);
        
        //ポイント調整完了リクエスト
        regClass(WEB3AdminPointAdjustCompleteRequest.class);
        //ポイント調整完了レスポンス
        regClass(WEB3AdminPointAdjustCompleteResponse.class);
        
        //ポイント調整入力リクエスト
        regClass(WEB3AdminPointAdjustInputRequest.class);
        //ポイント調整入力レスポンス
        regClass(WEB3AdminPointAdjustInputResponse.class);
        
        //ポイント履歴照会リクエスト
        regClass(WEB3AdminPointHistoryReferenceRequest.class);
        //ポイント履歴照会レスポンス
        regClass(WEB3AdminPointHistoryReferenceResponse.class);
        
        //景品一覧リクエスト
        regClass(WEB3AdminPointPremiumReferenceRequest.class);
        //景品一覧レスポンス
        regClass(WEB3AdminPointPremiumReferenceResponse.class);
        
        //景品削除確認リクエスト
        regClass(WEB3AdminPointPremiumDeleteConfirmRequest.class);
        //景品削除確認レスポンス
        regClass(WEB3AdminPointPremiumDeleteConfirmResponse.class);
        
        //景品削除完了リクエスト
        regClass(WEB3AdminPointPremiumDeleteCompleteRequest.class);
        //景品削除完了レスポンス
        regClass(WEB3AdminPointPremiumDeleteCompleteResponse.class);
        
        //景品訂正確認リクエスト
        regClass(WEB3AdminPointPremiumChangeConfirmRequest.class);
        //景品訂正確認レスポンス
        regClass(WEB3AdminPointPremiumChangeConfirmResponse.class);
        
        //景品訂正完了リクエスト
        regClass(WEB3AdminPointPremiumChangeCompleteRequest.class);
        //景品訂正完了レスポンス
        regClass(WEB3AdminPointPremiumChangeCompleteResponse.class);
        
        //景品訂正共通リクエスト
        regClass(WEB3AdminPointPremiumChangeCommonRequest.class);
        
        //景品訂正入力リクエスト
        regClass(WEB3AdminPointPremiumChangeInputRequest.class);
        //景品訂正入力レスポンス
        regClass(WEB3AdminPointPremiumChangeInputResponse.class);
        
        //景品登録確認リクエスト
        regClass(WEB3AdminPointPremiumRegistConfirmRequest.class);
        //景品登録確認レスポンス
        regClass(WEB3AdminPointPremiumRegistConfirmResponse.class);
        
        //景品登録完了リクエスト
        regClass(WEB3AdminPointPremiumRegistCompleteRequest.class);
        //景品登録完了レスポンス
        regClass(WEB3AdminPointPremiumRegistCompleteResponse.class);
        
        //景品登録共通リクエスト
        regClass(WEB3AdminPointPremiumRegistCommonRequest.class);
        
        //景品登録入力リクエスト
        regClass(WEB3AdminPointPremiumRegistInputRequest.class);
        //景品登録入力レスポンス
        regClass(WEB3AdminPointPremiumRegistInputResponse.class);
        
        //トレードボーナスプラン照会リクエスト  
        regClass(WEB3PointTradeBonusPlanReferenceRequest.class);
        //トレードボーナスプラン照会レスポンス  
        regClass(WEB3PointTradeBonusPlanReferenceResponse.class);
        
        //株式手数料無料情報照会リクエスト    
        regClass(WEB3PointCommissionInfoReferenceRequest.class);
        //株式手数料無料情報照会レスポンス    
        regClass(WEB3PointCommissionInfoReferenceResponse.class);

        //管理者トレードボーナスプラン照会リクエスト
        regClass(WEB3AdminPointTradeBonusPlanReferenceRequest.class);
        //管理者トレードボーナスプラン照会レスポンス
        regClass(WEB3AdminPointTradeBonusPlanReferenceResponse.class);
        
        //Handler の登録処理 ----------------------
        //ポイント交換申込 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3PointApplyReferenceRequest.class,
            WEB3PointExchangeApplyHandler.class,
            "serviceScreenDisplay");
            
        //ポイント交換申込 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3PointApplyInputRequest.class,
            WEB3PointExchangeApplyHandler.class,
            "selectScreenDisplay");
            
        //ポイント交換申込 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3PointApplyConfirmRequest.class,
            WEB3PointExchangeApplyHandler.class,
            "applyConfirm");
            
        //ポイント交換申込 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3PointApplyCompleteRequest.class,
            WEB3PointExchangeApplyHandler.class,
            "applyComplete");
            
        //カテゴリー管理 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointCategoryReferenceRequest.class,
            WEB3AdminPointCategoryReferenceHandler.class,
            "categoryReferenceRequest");
            
        //カテゴリー削除 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointCategoryDeleteConfirmRequest.class,
            WEB3AdminPointCategoryDeleteHandler.class,
            "deleteConfirm");
            
        //カテゴリー削除 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointCategoryDeleteCompleteRequest.class,
            WEB3AdminPointCategoryDeleteHandler.class,
            "deleteComplete");
            
        //カテゴリー訂正 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointCategoryChangeInputRequest.class,
            WEB3AdminPointCategoryChangeHandler.class,
            "inputScreenDisplay");
            
        //カテゴリー訂正 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointCategoryChangeConfirmRequest.class,
            WEB3AdminPointCategoryChangeHandler.class,
            "changeConfirm");
            
        //カテゴリー訂正 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointCategoryChangeCompleteRequest.class,
            WEB3AdminPointCategoryChangeHandler.class,
            "changeComplete");
            
        //カテゴリー登録 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointCategoryRegistInputRequest.class,
            WEB3AdminPointCategoryRegistHandler.class,
            "inputScreenDisplay");
            
        //カテゴリー登録 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointCategoryRegistConfirmRequest.class,
            WEB3AdminPointCategoryRegistHandler.class,
            "registConfirm");
            
        //カテゴリー登録 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointCategoryRegistCompleteRequest.class,
            WEB3AdminPointCategoryRegistHandler.class,
            "registComplete");
            
        //ポイント一括調整 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointUploadInputRequest.class,
            WEB3AdminPointPackageAdjustHandler.class,
            "inputScreenDisplay");
            
        //ポイント一括調整 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointUploadConfirmRequest.class,
            WEB3AdminPointPackageAdjustHandler.class,
            "uploadConfirm");
            
        //ポイント一括調整 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointUploadCompleteRequest.class,
            WEB3AdminPointPackageAdjustHandler.class,
            "uploadComplete");
            
        //ポイント一括調整 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointUploadCancelRequest.class,
            WEB3AdminPointPackageAdjustHandler.class,
            "uploadStop");
            
        //ポイント交換申込受付 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointExchangeStateReferenceRequest.class,
            WEB3AdminPointExchangeApplyAcceptHandler.class,
            "listScreenDisplay");
            
        //ポイント交換申込受付 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointExchangeAcceptRequest.class,
            WEB3AdminPointExchangeApplyAcceptHandler.class,
            "acceptComplete");  
            
        //ポイント交換申込受付 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointExchangeCancelConfirmRequest.class,
            WEB3AdminPointExchangeApplyAcceptHandler.class,
            "cancelConfirm");   
            
        //ポイント交換申込受付 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointExchangeCancelCompleteRequest.class,
            WEB3AdminPointExchangeApplyAcceptHandler.class,
            "cancelComplete");  
            
        //ポイント交換申込受付 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointExchangeCancelReleaseConfirmRequest.class,
            WEB3AdminPointExchangeApplyAcceptHandler.class,
            "cancelReleaseConfirm");    
            
        //ポイント交換申込受付 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointExchangeCancelReleaseCompleteRequest.class,
            WEB3AdminPointExchangeApplyAcceptHandler.class,
            "cancelReleaseComplete");            
            
        //景品管理 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointPremiumReferenceRequest.class,
            WEB3AdminPointPremiumReferenceHandler.class,
            "premiumReferenceRequest");
            
        //景品削除 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointPremiumDeleteConfirmRequest.class,
            WEB3AdminPointPremiumDeleteHandler.class,
            "deleteConfirm");   
            
        //景品削除 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointPremiumDeleteCompleteRequest.class,
            WEB3AdminPointPremiumDeleteHandler.class,
            "deleteComplete"); 
            
        //景品訂正 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointPremiumChangeInputRequest.class,
            WEB3AdminPointPremiumChangeHandler.class,
            "inputScreenDisplay");   
            
        //景品訂正 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointPremiumChangeConfirmRequest.class,
            WEB3AdminPointPremiumChangeHandler.class,
            "changeConfirm");   
            
        //景品訂正 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointPremiumChangeCompleteRequest.class,
            WEB3AdminPointPremiumChangeHandler.class,
            "changeComplete");  
            
        //景品登録 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointPremiumRegistInputRequest.class,
            WEB3AdminPointPremiumRegistHandler.class,
            "inputScreenDisplay");    
            
        //景品登録 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointPremiumRegistConfirmRequest.class,
            WEB3AdminPointPremiumRegistHandler.class,
            "registConfirm");    
            
        //景品登録 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointPremiumRegistCompleteRequest.class,
            WEB3AdminPointPremiumRegistHandler.class,
            "registComplete"); 
            
        //顧客別ポイント管理 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointManageDisplayRequest.class,
            WEB3AdminPointManageByCustomerHandler.class,
            "manageScreenDisplay");  
            
        //顧客別ポイント管理 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointAdjustInputRequest.class,
            WEB3AdminPointManageByCustomerHandler.class,
            "adjustInput");     
            
        //顧客別ポイント管理 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointAdjustConfirmRequest.class,
            WEB3AdminPointManageByCustomerHandler.class,
            "adjustConfirm"); 
            
        //顧客別ポイント管理 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointAdjustCompleteRequest.class,
            WEB3AdminPointManageByCustomerHandler.class,
            "adjustComplete");      
            
        //顧客別ポイント管理 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointHistoryReferenceRequest.class,
            WEB3AdminPointManageByCustomerHandler.class,
            "historyReference");                               
        
        //トレードボーナスプラン照会 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3PointTradeBonusPlanReferenceRequest.class,
            WEB3PointTradeBonusPlanReferenceHandler.class,
            "tradeBonusPlanReference"); 
        
        //株式手数料無料情報照会 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3PointCommissionInfoReferenceRequest.class,
            WEB3PointCommissionInfoReferenceHandler.class,
            "commissionInfoReference"); 
        
        //管理者トレードボーナスプラン照会 用ハンドラーの登録
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointTradeBonusPlanReferenceRequest.class,
            WEB3AdminPointTradeBonusPlanReferenceHandler.class,
            "getReferenceScreen"); 
        
        log.exiting(STR_METHOD_NAME);
    }
    
}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-AdminTriggerOrder プラグインクラス(WEB3AdminToAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/15 沈迪 (中訊) 新規作成 
                 : 2006/03/21 譚漢江(中訊) トリガー注文管理者・手動失効
                 : 2006/04/05 鄭徳懇(中訊) トリガー注文管理者・取扱停止一覧
                 : 2006/04/05 鄭徳懇(中訊) トリガー注文管理者・取扱停止削除
                 : 2006/04/05 鄭徳懇(中訊) トリガー注文管理者・取扱停止登録
                 : 2006/04/05 鄭徳懇(中訊) トリガー注文管理者・取扱停止入力
                 : 2006/04/05 鄭徳懇(中訊) トリガー注文管理者・取扱停止変更  
                 : 2006/07/10 李　@俊(中訊) 仕様変更モデル077     
*/
package webbroker3.admintriggerorder;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.admintriggerorder.data.WEB3AdminTriggerorderSessionDatabaseExtensions;
import webbroker3.admintriggerorder.handler.WEB3AdminToEquityOrderReferenceHandler;
import webbroker3.admintriggerorder.handler.WEB3AdminToIfoOrderReferenceHandler;
import webbroker3.admintriggerorder.handler.WEB3AdminToManualExpireHandler;
import webbroker3.admintriggerorder.handler.WEB3AdminToManualExpireMainHandler;
import webbroker3.admintriggerorder.handler.WEB3AdminToTradeStopChangeHandler;
import webbroker3.admintriggerorder.handler.WEB3AdminToTradeStopDeleteHandler;
import webbroker3.admintriggerorder.handler.WEB3AdminToTradeStopInputHandler;
import webbroker3.admintriggerorder.handler.WEB3AdminToTradeStopListHandler;
import webbroker3.admintriggerorder.handler.WEB3AdminToTradeStopRegistHandler;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefInpRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefInpResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefRefRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefRefResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefInpRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefInpResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefRefRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefRefResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseConfirmRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseConfirmResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseInputRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseInputResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseMainRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseMainResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseRunRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseRunResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseStatusRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseStatusResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToOrderRefRefCommonRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelCompleteRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelCompleteResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelConfirmRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelConfirmResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopInputRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopInputResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopListRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopListResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopRegCompleteRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopRegCompleteResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopRegConfirmRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopRegConfirmResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdCompleteRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdCompleteResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdConfirmRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdConfirmResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdInputRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdInputResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToEquityOrderReferenceService;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToIfoOrderReferenceService;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToManualExpireMainService;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToManualExpireService;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopChangeService;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopDeleteService;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopInputService;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopListService;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopRegistService;
import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToEquityOrderReferenceServiceImpl;
import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToIfoOrderReferenceServiceImpl;
import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToManualExpireMainServiceImpl;
import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToManualExpireServiceImpl;
import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToTradeStopChangeServiceImpl;
import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToTradeStopDeleteServiceImpl;
import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToTradeStopInputServiceImpl;
import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToTradeStopListServiceImpl;
import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToTradeStopRegistServiceImpl;
import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-AdminTriggerOrder プラグインクラス<BR>
 *   
 * @@author 沈迪
 * @@version 1.0
 */
public class WEB3AdminToAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3AdminToAppPlugin()
    {
        
    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception
    {
        final String METHOD_NAME = " plug()";
        log.entering(METHOD_NAME);
        
        plug(WEB3AdminToAppPlugin.class);
        
        log.exiting(METHOD_NAME);
    }
    
    /**
     * プラグイン処理。
     */
    public static void onPlug() throws Exception 
    {
        final String METHOD_NAME = " onPlug()";
        log.entering(METHOD_NAME);
        
        //このプラグインより先に読み込む必要のあるプラグインの指定。
        //install the system plugins that we need
        KernelPlugin.plug();
        
        // DatabaseExtensions のプラグイン処理 ----------------------
        // WEB3AdminTriggerorderSessionDatabaseExtensions をプラグイン
        WEB3AdminTriggerorderSessionDatabaseExtensions.plug();
        
        //Service の登録
        //トリガー注文管理者・先物OP注文照会サービス
        Services.registerService(
            WEB3AdminToIfoOrderReferenceService.class,
            new WEB3AdminToIfoOrderReferenceServiceImpl());
        
        //トリガー注文管理者・株式注文照会サービス
        Services.registerService(
                WEB3AdminToEquityOrderReferenceService.class,
            new WEB3AdminToEquityOrderReferenceServiceImpl());
        
        //トリガー注文管理者・手動失効サービス
        Services.registerService(
            WEB3AdminToManualExpireService.class,
            new WEB3AdminToManualExpireServiceImpl());
        
        //トリガー注文管理者・取扱停止一覧サービス
        Services.registerService(
            WEB3AdminToTradeStopListService.class,
            new WEB3AdminToTradeStopListServiceImpl());
        
        //トリガー注文管理者・取扱停止変更サービス
        Services.registerService(
            WEB3AdminToTradeStopChangeService.class,
            new WEB3AdminToTradeStopChangeServiceImpl());
        
        //トリガー注文管理者・取扱停止削除サービス
        Services.registerService(
            WEB3AdminToTradeStopDeleteService.class,
            new WEB3AdminToTradeStopDeleteServiceImpl());
        
        //トリガー注文管理者・取扱停止入力サービス
        Services.registerService(
            WEB3AdminToTradeStopInputService.class,
            new WEB3AdminToTradeStopInputServiceImpl());
        
        //トリガー注文管理者・取扱停止登録サービス
        Services.registerService(
            WEB3AdminToTradeStopRegistService.class,
            new WEB3AdminToTradeStopRegistServiceImpl());
        
        //トリガー注文管理者・手動失効メインサービス
        Services.registerService(
            WEB3AdminToManualExpireMainService.class,
            new WEB3AdminToManualExpireMainServiceImpl());
        
        // Service.execute()呼び出し前後に  ----------------------
        // 処理開始時刻と処理終了時刻をログ出力するように設定する

        //トリガー注文管理者・先物OP注文照会サービスインタセプタ 
        Services.addInterceptor(
            WEB3AdminToIfoOrderReferenceService.class,
            new WEB3LogSysTimeInterceptor());
        
        //トリガー注文管理者・株式注文照会サービスインタセプタ 
        Services.addInterceptor(
                WEB3AdminToEquityOrderReferenceService.class,
                new WEB3LogSysTimeInterceptor());
        
        //トリガー注文管理者・手動失効サービス
        Services.addInterceptor(
            WEB3AdminToManualExpireService.class,
            new WEB3LogSysTimeInterceptor());
        
        //トリガー注文管理者・取扱停止一覧サービス
        Services.addInterceptor(
            WEB3AdminToTradeStopListService.class,
            new WEB3LogSysTimeInterceptor());
        
        //トリガー注文管理者・取扱停止変更サービス
        Services.addInterceptor(
            WEB3AdminToTradeStopChangeService.class,
            new WEB3LogSysTimeInterceptor());
        
        //トリガー注文管理者・取扱停止削除サービス
        Services.addInterceptor(
            WEB3AdminToTradeStopDeleteService.class,
            new WEB3LogSysTimeInterceptor());
        
        //トリガー注文管理者・取扱停止入力サービス
        Services.addInterceptor(
            WEB3AdminToTradeStopInputService.class,
            new WEB3LogSysTimeInterceptor());
        
        //トリガー注文管理者・取扱停止登録サービス
        Services.addInterceptor(
            WEB3AdminToTradeStopRegistService.class,
            new WEB3LogSysTimeInterceptor());
        
        //トリガー注文管理者・手動失効メインサービス
        Services.addInterceptor(
            WEB3AdminToManualExpireMainService.class,
            new WEB3LogSysTimeInterceptor());
        
        //Service に ServiceInterceptor を設定する ----------------------
        //設定内容確認サービス
        Services.addInterceptor(
            WEB3AdminToIfoOrderReferenceService.class,
            new WEB3AdminToIfoOrderReferenceServiceInterceptor());   
        
        //Service に ServiceInterceptor を設定する ----------------------
        //設定内容確認サービス
        Services.addInterceptor(
                WEB3AdminToEquityOrderReferenceService.class,
            new WEB3AdminToEquityOrderReferenceServiceInterceptor());   
        
        //トリガー注文管理者・手動失効サービス
        Services.addInterceptor(
            WEB3AdminToManualExpireService.class,
            new WEB3AdminToManualExpireServiceInterceptor());   
        
        //トリガー注文管理者・取扱停止一覧サービス
        Services.addInterceptor(
            WEB3AdminToTradeStopListService.class,
            new WEB3AdminToTradeStopServiceInterceptor());
        
        //トリガー注文管理者・取扱停止変更サービス
        Services.addInterceptor(
            WEB3AdminToTradeStopChangeService.class,
            new WEB3AdminToTradeStopServiceInterceptor());
        
        //トリガー注文管理者・取扱停止削除サービス
        Services.addInterceptor(
            WEB3AdminToTradeStopDeleteService.class,
            new WEB3AdminToTradeStopServiceInterceptor());
        
        //トリガー注文管理者・取扱停止入力サービス
        Services.addInterceptor(
            WEB3AdminToTradeStopInputService.class,
            new WEB3AdminToTradeStopServiceInterceptor());
        
        //トリガー注文管理者・取扱停止登録サービス
        Services.addInterceptor(
            WEB3AdminToTradeStopRegistService.class,
            new WEB3AdminToTradeStopServiceInterceptor());
        
        // Service の Interceptor 設定処理 ----------------------
        // 自動トランザクション設定
        //設定内容確認サービス
        Services.addInterceptor(
            WEB3AdminToIfoOrderReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        // 自動トランザクション設定
        //設定内容確認サービス
        Services.addInterceptor(
                WEB3AdminToEquityOrderReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        // 自動トランザクション設定
        //トリガー注文管理者・手動失効サービス
        Services.addInterceptor(
            WEB3AdminToManualExpireService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //トリガー注文管理者・取扱停止一覧サービス
        Services.addInterceptor(
            WEB3AdminToTradeStopListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //トリガー注文管理者・取扱停止変更サービス
        Services.addInterceptor(
            WEB3AdminToTradeStopChangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //トリガー注文管理者・取扱停止削除サービス
        Services.addInterceptor(
            WEB3AdminToTradeStopDeleteService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //リガー注文管理者・取扱停止入力サービス
        Services.addInterceptor(
            WEB3AdminToTradeStopInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //トリガー注文管理者・取扱停止登録サービス
        Services.addInterceptor(
            WEB3AdminToTradeStopRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //トリガー注文管理者・手動失効メインサービス        
        Services.addInterceptor(
            WEB3AdminToManualExpireMainService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
    
        //Message の登録処理 ----------------------
        //トリガー注文管理者・先物OP注文照会リクエスト
        regClass(WEB3AdminToIfoOrderRefRefRequest.class);
                
        //トリガー注文管理者・先物OP注文照会レスポンス
        regClass(WEB3AdminToIfoOrderRefRefResponse.class);
        
        //トリガー注文管理者・先物OP注文照会入力リクエスト
        regClass(WEB3AdminToIfoOrderRefInpRequest.class);
        
        //トリガー注文管理者・先物OP注文照会入力レスポンス
        regClass(WEB3AdminToIfoOrderRefInpResponse.class);
        
        //トリガー注文管理者・注文照会共通リクエスト
        regClass(WEB3AdminToOrderRefRefCommonRequest.class);
        
        //トリガー注文管理者・株式注文照会入力リクエスト
        regClass(WEB3AdminToEquityOrderRefInpRequest.class);
        
        //トリガー注文管理者・株式注文照会入力レスポンス
        regClass(WEB3AdminToEquityOrderRefInpResponse.class);
        
        //トリガー注文管理者・株式注文照会リクエスト
        regClass(WEB3AdminToEquityOrderRefRefRequest.class);
        
        //トリガー注文管理者・株式注文照会レスポンス
        regClass(WEB3AdminToEquityOrderRefRefResponse.class);
        
        //トリガー注文管理者・手動失効確認リクエスト
        regClass(WEB3AdminToManualLapseConfirmRequest.class);
        
        //トリガー注文管理者・手動失効確認レスポンス
        regClass(WEB3AdminToManualLapseConfirmResponse.class);
        
        //トリガー注文管理者・手動失効入力リクエスト
        regClass(WEB3AdminToManualLapseInputRequest.class);
        
        //トリガー注文管理者・手動失効入力レスポンス
        regClass(WEB3AdminToManualLapseInputResponse.class);
        
        //トリガー注文管理者・手動失効メインリクエスト
        regClass(WEB3AdminToManualLapseMainRequest.class);
        
        //トリガー注文管理者・手動失効メインレスポンス
        regClass(WEB3AdminToManualLapseMainResponse.class);
        
        //トリガー注文管理者・手動失効処理起動リクエスト
        regClass(WEB3AdminToManualLapseRunRequest.class);
        
        //トリガー注文管理者・手動失効処理起動レスポンス
        regClass(WEB3AdminToManualLapseRunResponse.class);
        
        //トリガー注文管理者・手動失効処理ステータス確認リクエスト
        regClass(WEB3AdminToManualLapseStatusRequest.class);
        
        //トリガー注文管理者・手動失効処理ステータス確認レスポンス
        regClass(WEB3AdminToManualLapseStatusResponse.class);
        
        //トリガー注文管理者・取扱停止削除完了リクエスト
        regClass(WEB3AdminToTradeStopDelCompleteRequest.class);
        
        //トリガー注文管理者・取扱停止削除完了レスポンス
        regClass(WEB3AdminToTradeStopDelCompleteResponse.class);
        
        //トリガー注文管理者・取扱停止削除確認リクエスト
        regClass(WEB3AdminToTradeStopDelConfirmRequest.class);
        
        //トリガー注文管理者・取扱停止削除確認レスポンス
        regClass(WEB3AdminToTradeStopDelConfirmResponse.class);
        
        //トリガー注文管理者・取扱停止入力リクエスト
        regClass(WEB3AdminToTradeStopInputRequest.class);
        
        //トリガー注文管理者・取扱停止入力レスポンス
        regClass(WEB3AdminToTradeStopInputResponse.class);
        
        //トリガー注文管理者・取扱停止一覧リクエスト
        regClass(WEB3AdminToTradeStopListRequest.class);
        
        //トリガー注文管理者・取扱停止一覧レスポンス
        regClass(WEB3AdminToTradeStopListResponse.class);
        
        //トリガー注文管理者・取扱停止登録完了リクエスト
        regClass(WEB3AdminToTradeStopRegCompleteRequest.class);
        
        //トリガー注文管理者・取扱停止登録完了レスポンス
        regClass(WEB3AdminToTradeStopRegCompleteResponse.class);
        
        //トリガー注文管理者・取扱停止登録確認リクエスト
        regClass(WEB3AdminToTradeStopRegConfirmRequest.class);
        
        //トリガー注文管理者・取扱停止登録確認レスポンス
        regClass(WEB3AdminToTradeStopRegConfirmResponse.class);
        
        //トリガー注文管理者・取扱停止変更完了リクエスト
        regClass(WEB3AdminToTradeStopUpdCompleteRequest.class);
        
        //トリガー注文管理者・取扱停止変更完了レスポンス
        regClass(WEB3AdminToTradeStopUpdCompleteResponse.class);
        
        //トリガー注文管理者・取扱停止変更確認リクエスト
        regClass(WEB3AdminToTradeStopUpdConfirmRequest.class);
        
        //トリガー注文管理者・取扱停止変更確認レスポンス
        regClass(WEB3AdminToTradeStopUpdConfirmResponse.class);
        
        //トリガー注文管理者・取扱停止変更入力リクエスト
        regClass(WEB3AdminToTradeStopUpdInputRequest.class);
        
        //トリガー注文管理者・取扱停止変更入力レスポンス
        regClass(WEB3AdminToTradeStopUpdInputResponse.class);
        
        //Handler の登録処理
        //トリガー注文管理者・先物OP注文照会ハンドラ
        regHandler(
            WEB3AdminToAppPlugin.class,
            WEB3AdminToIfoOrderRefInpRequest.class,
            WEB3AdminToIfoOrderReferenceHandler.class,
            "getInputScreen");
        
        regHandler(
            WEB3AdminToAppPlugin.class,
            WEB3AdminToIfoOrderRefRefRequest.class,
            WEB3AdminToIfoOrderReferenceHandler.class,
            "getReferenceScreen");
       
        //Handler の登録処理
        //トリガー注文管理者・株式注文照会サービスハンドラ
        regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToEquityOrderRefInpRequest.class,
                WEB3AdminToEquityOrderReferenceHandler.class,
                "getInputScreen");
            
            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToEquityOrderRefRefRequest.class,
                WEB3AdminToEquityOrderReferenceHandler.class,
                "getReferenceScreen");

        //Handler の登録処理
        //トリガー注文管理者・手動失効ハンドラ
            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToManualLapseInputRequest.class,
                WEB3AdminToManualExpireHandler.class,
                "getInputScreen");

            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToManualLapseConfirmRequest.class,
                WEB3AdminToManualExpireHandler.class,
                "confirmManualExpire");

            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToManualLapseRunRequest.class,
                WEB3AdminToManualExpireHandler.class,
                "runManualExpire");

            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToManualLapseStatusRequest.class,
                WEB3AdminToManualExpireHandler.class,
                "confirmStatus");

            //トリガー注文管理者・手動失効メインハンドラ
            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToManualLapseMainRequest.class,
                WEB3AdminToManualExpireMainHandler.class,
                "manualExpireRequest");
            
            //トリガー注文管理者・取扱停止一覧ハンドラ
            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToTradeStopListRequest.class,
                WEB3AdminToTradeStopListHandler.class,
                "getListScreen");
            
            //トリガー注文管理者・取扱停止変更ハンドラ
            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToTradeStopUpdInputRequest.class,
                WEB3AdminToTradeStopChangeHandler.class,
                "getInputScreen");
            
            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToTradeStopUpdConfirmRequest.class,
                WEB3AdminToTradeStopChangeHandler.class,
                "confirmChange");
            
            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToTradeStopUpdCompleteRequest.class,
                WEB3AdminToTradeStopChangeHandler.class,
                "completeChange");
            
            //トリガー注文管理者・取扱停止削除ハンドラ
            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToTradeStopDelConfirmRequest.class,
                WEB3AdminToTradeStopDeleteHandler.class,
                "confirmDelete");
            
            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToTradeStopDelCompleteRequest.class,
                WEB3AdminToTradeStopDeleteHandler.class,
                "completeDelete");
            
            //トリガー注文管理者・取扱停止入力ハンドラ
            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToTradeStopInputRequest.class,
                WEB3AdminToTradeStopInputHandler.class,
                "getInputScreen");
            
            //トリガー注文管理者・取扱停止登録ハンドラ
            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToTradeStopRegConfirmRequest.class,
                WEB3AdminToTradeStopRegistHandler.class,
                "confirmRegist");
            
            //トリガー注文管理者・取扱停止登録ハンドラ
            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToTradeStopRegCompleteRequest.class,
                WEB3AdminToTradeStopRegistHandler.class,
                "completeRegist");
            
        log.exiting(METHOD_NAME);
    }
}
@

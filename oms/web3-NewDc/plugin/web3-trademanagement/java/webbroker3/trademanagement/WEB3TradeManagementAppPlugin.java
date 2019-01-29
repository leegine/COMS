head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.25.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3TradeManagementAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-trademanagement プラグインクラス(WEB3TradeManagementAppPlugin.java)
Author Name      : Daiwa Institute of Research
                   2006/10/11 徐宏偉(中訊) 管理者為替登録 対応
Revision History : 2008/09/26 張騰宇(中訊) 不正アタック防止対応
*/
package webbroker3.trademanagement;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.trademanagement.handler.WEB3AdminTMExchangeRegistHandler;
import webbroker3.trademanagement.handler.WEB3AdminTMLoginFrequencyListHandler;
import webbroker3.trademanagement.handler.WEB3AdminTMLoginProcessingListHandler;
import webbroker3.trademanagement.handler.WEB3AdminTMLoginRejectIPManagementHandler;
import webbroker3.trademanagement.handler.WEB3AdminTMLoginStopStartChangeHandler;
import webbroker3.trademanagement.handler.WEB3AdminTMMarketStopStartChangeServiceHandler;
import webbroker3.trademanagement.handler.WEB3AdminTMProductStopStartChangeServiceHandler;
import webbroker3.trademanagement.handler.WEB3AdminTMProductStopStartReferenceHandler;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTMLStopStartChgCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTMLStopStartChgCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTMLStopStartChgConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTMLStopStartChgConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTMLStopStartChgInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTMLStopStartChgInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTMMStopStartChgCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTMMStopStartChgCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTMMStopStartChgConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTMMStopStartChgConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTMMStopStartChgInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTMMStopStartChgInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTMPStopStartCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTMPStopStartCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTMPStopStartConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTMPStopStartConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTMPStopStartReferenceRequest;
import webbroker3.trademanagement.message.WEB3AdminTMPStopStartReferenceResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlListRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlListResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountListRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountListResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryListRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryListResponse;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMExchangeRegistService;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginFrequencyListService;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginProcessingListService;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginRejectIPManagementService;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginStopStartChangeService;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMMarketStopStartChangeService;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMProductStopStartChangeService;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMProductStopStartReferenceService;
import webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMExchangeRegistServiceImpl;
import webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginFrequencyListServiceImpl;
import webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginProcessingListServiceImpl;
import webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl;
import webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginStopStartChangeServiceImpl;
import webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMMarketStopStartChangeServiceImpl;
import webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMProductStopStartChangeServiceImpl;
import webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMProductStopStartReferenceServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-trademanagement プラグインクラス。<BR>
 * @@author Amarnath
 * @@version 1.0
 */
public final class WEB3TradeManagementAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TradeManagementAppPlugin.class);

    /**
     * デフォルトコンストラクタ。
     */
    public WEB3TradeManagementAppPlugin()
    {
    }

    /**
     * plug method
     * @@throws Exception exception
     */
    public static void plug() throws Exception
    {
        final String STR_METHOD_NAME = "plug()";
        log.entering(STR_METHOD_NAME);
        plug(WEB3TradeManagementAppPlugin.class);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * onPlug method
     * @@throws Exception exception
     */
    public static void onPlug() throws Exception
    {
        final String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);

        // このプラグインより先に読み込む必要のあるプラグインの指定。
        KernelPlugin.plug();

        // Service の登録処理
        // 管理者商品別取扱停止再開照会サービス
        Services.registerService(WEB3AdminTMProductStopStartReferenceService.class,
            new WEB3AdminTMProductStopStartReferenceServiceImpl());

        // 管理者商品別取扱停止再開変更サービス
        Services.registerService(WEB3AdminTMProductStopStartChangeService.class,
            new WEB3AdminTMProductStopStartChangeServiceImpl());

        // 管理者ログイン停止再開変更サービス
        Services.registerService(WEB3AdminTMLoginStopStartChangeService.class,
            new WEB3AdminTMLoginStopStartChangeServiceImpl());

        // 管理者市場別取引停止再開変更サービス
        Services.registerService(WEB3AdminTMMarketStopStartChangeService.class,
            new WEB3AdminTMMarketStopStartChangeServiceImpl());

        //管理者為替登録サービス
        Services.registerService(WEB3AdminTMExchangeRegistService.class,
            new WEB3AdminTMExchangeRegistServiceImpl());

        //管理者ログイン処理一覧サービス
        Services.registerService(WEB3AdminTMLoginProcessingListService.class,
            new WEB3AdminTMLoginProcessingListServiceImpl());

        //IP別ログイン回数一覧サービス
        Services.registerService(WEB3AdminTMLoginFrequencyListService.class,
            new WEB3AdminTMLoginFrequencyListServiceImpl());

        //管理者ログイン拒否IP管理サービス
        Services.registerService(WEB3AdminTMLoginRejectIPManagementService.class,
            new WEB3AdminTMLoginRejectIPManagementServiceImpl());

        // サービスインタセプタの設定
        // 管理者商品別取扱停止再開照会サービスインタセプタ
        Services.addInterceptor(
        WEB3AdminTMProductStopStartReferenceService.class,
                new WEB3AdminTMProductStopStartReferenceServiceInterceptor());

        // 管理者商品別取扱停止再開変更サービスインタセプタ
        Services.addInterceptor(
        WEB3AdminTMProductStopStartChangeService.class,
                new WEB3AdminTMProductStopStartChangeServiceInterceptor());

        // 管理者ログイン停止再開変更サービスインタセプタ
        Services.addInterceptor(
        WEB3AdminTMLoginStopStartChangeService.class,
                new WEB3AdminTMLoginStopStartChangeServiceInterceptor());

        // 管理者市場別取引停止再開変更サービスインタセプタ
        Services.addInterceptor(
        WEB3AdminTMMarketStopStartChangeService.class,
                new WEB3AdminTMMarketStopStartChangeServiceInterceptor());

        // サービス呼び出し前後に
        // 処理開始時刻と処理終了時刻をログ出力するように設定する
        // 管理者商品別取扱停止再開照会サービス
        Services.addInterceptor(
        WEB3AdminTMProductStopStartReferenceService.class,
                new TransactionalInterceptor(
                    TransactionalInterceptor.TX_CREATE_NEW));

        // 管理者商品別取扱停止再開変更サービス
        Services.addInterceptor(
        WEB3AdminTMProductStopStartChangeService.class,
            new WEB3LogSysTimeInterceptor());

        // 管理者ログイン停止再開変更サービス
        Services.addInterceptor(
                WEB3AdminTMLoginStopStartChangeService.class,
                    new WEB3LogSysTimeInterceptor());

        // 管理者市場別取引停止再開変更サービス
        Services.addInterceptor(
        WEB3AdminTMMarketStopStartChangeService.class,
            new WEB3LogSysTimeInterceptor());

        // 管理者商品別取扱停止再開照会サービス
        Services.addInterceptor(
        WEB3AdminTMProductStopStartReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者為替登録サービス
        Services.addInterceptor(
        WEB3AdminTMExchangeRegistService.class,
            new WEB3LogSysTimeInterceptor());
        
        //管理者ログイン処理一覧サービス
        Services.addInterceptor(
            WEB3AdminTMLoginProcessingListService.class,
            new WEB3LogSysTimeInterceptor());

        //IP別ログイン回数一覧サービス
        Services.addInterceptor(
            WEB3AdminTMLoginFrequencyListService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者ログイン拒否IP管理サービス
        Services.addInterceptor(
            WEB3AdminTMLoginRejectIPManagementService.class,
            new WEB3LogSysTimeInterceptor());

        // 自動トランザクション設定
        // 管理者商品別取扱停止再開変更サービス
        Services.addInterceptor(
        WEB3AdminTMProductStopStartChangeService.class,
                new TransactionalInterceptor(
                    TransactionalInterceptor.TX_CREATE_NEW));
        // 管理者ログイン停止再開変更サービス
        Services.addInterceptor(
        WEB3AdminTMLoginStopStartChangeService.class,
                new TransactionalInterceptor(
                    TransactionalInterceptor.TX_CREATE_NEW));

        // 管理者市場別取引停止再開変更サービス
        Services.addInterceptor(
        WEB3AdminTMMarketStopStartChangeService.class,
                new TransactionalInterceptor(
                    TransactionalInterceptor.TX_CREATE_NEW));

        // 管理者為替登録サービス
        Services.addInterceptor(
                WEB3AdminTMExchangeRegistService.class,
                new TransactionalInterceptor(
                    TransactionalInterceptor.TX_CREATE_NEW));

        //管理者ログイン処理一覧サービス
        Services.addInterceptor(
            WEB3AdminTMLoginProcessingListService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //IP別ログイン回数一覧サービス
        Services.addInterceptor(
            WEB3AdminTMLoginFrequencyListService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者ログイン拒否IP管理サービス
        Services.addInterceptor(
            WEB3AdminTMLoginRejectIPManagementService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // Message の登録
        // 管理者商品別取扱停止再開照会リクエスト・レスポンス
        regClass(WEB3AdminTMPStopStartReferenceRequest.class);
        regClass(WEB3AdminTMPStopStartReferenceResponse.class);

        // 管理者・商品別取扱停止再開変更完了リクエスト・レスポンス
        regClass(WEB3AdminTMPStopStartCompleteRequest.class);
        regClass(WEB3AdminTMPStopStartCompleteResponse.class);

        // 管理者・商品別取扱停止再開変更確認リクエスト・レスポンス
        regClass(WEB3AdminTMPStopStartConfirmRequest.class);
        regClass(WEB3AdminTMPStopStartConfirmResponse.class);

        // 管理者・ログイン停止再開変更入力リクエスト・レスポンス
        regClass(WEB3AdminTMLStopStartChgInputRequest.class);
        regClass(WEB3AdminTMLStopStartChgInputResponse.class);

        // 管理者・ログイン停止再開変更確認リクエスト・レスポンス
        regClass(WEB3AdminTMLStopStartChgConfirmRequest.class);
        regClass(WEB3AdminTMLStopStartChgConfirmResponse.class);

        // 管理者・ログイン停止再開変更完了リクエスト・レスポンス
        regClass(WEB3AdminTMLStopStartChgCompleteRequest.class);
        regClass(WEB3AdminTMLStopStartChgCompleteResponse.class);

        // 管理者・市場別取引停止再開変更入力リクエスト・レスポンス
        regClass(WEB3AdminTMMStopStartChgInputRequest.class);
        regClass(WEB3AdminTMMStopStartChgInputResponse.class);

        // 管理者・市場別取引停止再開変更確認リクエスト・レスポンス
        regClass(WEB3AdminTMMStopStartChgConfirmRequest.class);
        regClass(WEB3AdminTMMStopStartChgConfirmResponse.class);
        // 管理者・市場別取引停止再開変更完了リクエスト・レスポンス
        regClass(WEB3AdminTMMStopStartChgCompleteRequest.class);
        regClass(WEB3AdminTMMStopStartChgCompleteResponse.class);

        //管理者・為替登録入力リクエスト・レスポンス
        regClass(WEB3AdminTMExchangeRegistInputRequest.class);
        regClass(WEB3AdminTMExchangeRegistInputResponse.class);
        //管理者・為替登録確認リクエスト・レスポンス
        regClass(WEB3AdminTMExchangeRegistConfirmRequest.class);
        regClass(WEB3AdminTMExchangeRegistConfirmResponse.class);

        //管理者・為替登録完了リクエスト・レスポンス
        regClass(WEB3AdminTMExchangeRegistCompleteRequest.class);
        regClass(WEB3AdminTMExchangeRegistCompleteResponse.class);

        //管理者・ログイン処理一覧検索入力リクエスト.レスポンス
        regClass(WEB3AdminTraderAdminLoginHistoryInputRequest.class);
        regClass(WEB3AdminTraderAdminLoginHistoryInputResponse.class);

        //管理者・ログイン処理一覧の検索結果リクエスト.レスポンス
        regClass(WEB3AdminTraderAdminLoginHistoryListRequest.class);
        regClass(WEB3AdminTraderAdminLoginHistoryListResponse.class);

        //管理者・ログイン拒否IP一覧リクエスト.レスポンス
        regClass(WEB3AdminTraderAdminIPControlListRequest.class);
        regClass(WEB3AdminTraderAdminIPControlListResponse.class);

        //管理者・ログイン拒否IP登録リクエスト.レスポンス
        regClass(WEB3AdminTraderAdminIPControlRegistInputRequest.class);
        regClass(WEB3AdminTraderAdminIPControlRegistInputResponse.class);

        //管理者・ログイン拒否IP登録情報変更リクエスト.レスポンス
        regClass(WEB3AdminTraderAdminIPControlUpdateInputRequest.class);
        regClass(WEB3AdminTraderAdminIPControlUpdateInputResponse.class);

        //管理者・ログイン拒否IP登録情報削除確認リクエスト.レスポンス
        regClass(WEB3AdminTraderAdminIPControlDeleteConfirmRequest.class);
        regClass(WEB3AdminTraderAdminIPControlDeleteConfirmResponse.class);

        //管理者・ログイン拒否IP登録確認リクエスト.レスポンス
        regClass(WEB3AdminTraderAdminIPControlRegistConfirmRequest.class);
        regClass(WEB3AdminTraderAdminIPControlRegistConfirmResponse.class);

        //管理者・ログイン拒否IP登録情報変更確認リクエスト.レスポンス
        regClass(WEB3AdminTraderAdminIPControlUpdateConfirmRequest.class);
        regClass(WEB3AdminTraderAdminIPControlUpdateConfirmResponse.class);

        //管理者・ログイン拒否IP登録情報削除完了リクエスト.レスポンス
        regClass(WEB3AdminTraderAdminIPControlDeleteCompleteRequest.class);
        regClass(WEB3AdminTraderAdminIPControlDeleteCompleteResponse.class);

        //管理者・ログイン拒否IP登録完了リクエスト.レスポンス
        regClass(WEB3AdminTraderAdminIPControlRegistCompleteRequest.class);
        regClass(WEB3AdminTraderAdminIPControlRegistCompleteResponse.class);

        //管理者・ログイン拒否IP登録情報変更完了リクエスト.レスポンス
        regClass(WEB3AdminTraderAdminIPControlUpdateCompleteRequest.class);
        regClass(WEB3AdminTraderAdminIPControlUpdateCompleteResponse.class);

        //管理者・IP別ログイン回数一覧検索入力リクエスト.レスポンス
        regClass(WEB3AdminTraderAdminLoginCountInputRequest.class);
        regClass(WEB3AdminTraderAdminLoginCountInputResponse.class);
        
        //管理者・IP別ログイン回数一覧検索結果リクエスト.レスポンス
        regClass(WEB3AdminTraderAdminLoginCountListRequest.class);
        regClass(WEB3AdminTraderAdminLoginCountListResponse.class);
        

        // ハンドラーの登録
        // 管理者商品別取扱停止再開照会ハンドラ
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTMPStopStartReferenceRequest.class,
            WEB3AdminTMProductStopStartReferenceHandler.class,
            "getProductHandlingStatusReference");

        // 管理者商品別取扱停止再開変更ハンドラ
        regHandler(
        WEB3TradeManagementAppPlugin.class,
        WEB3AdminTMPStopStartConfirmRequest.class,
        WEB3AdminTMProductStopStartChangeServiceHandler.class,
            "confirmChange");
        regHandler(
        WEB3TradeManagementAppPlugin.class,
        WEB3AdminTMPStopStartCompleteRequest.class,
        WEB3AdminTMProductStopStartChangeServiceHandler.class,
            "completeChange");

        // 管理者ログイン停止再開変更ハンドラ
        regHandler(
        WEB3TradeManagementAppPlugin.class,
        WEB3AdminTMLStopStartChgInputRequest.class,
        WEB3AdminTMLoginStopStartChangeHandler.class,
            "getChangeInputScreen");
        regHandler(
        WEB3TradeManagementAppPlugin.class,
        WEB3AdminTMLStopStartChgConfirmRequest.class,
        WEB3AdminTMLoginStopStartChangeHandler.class,
            "confirmChange");
        regHandler(
        WEB3TradeManagementAppPlugin.class,
        WEB3AdminTMLStopStartChgCompleteRequest.class,
        WEB3AdminTMLoginStopStartChangeHandler.class,
            "completeChange");

        // 管理者市場別取引停止再開変更ハンドラ
        regHandler(
        WEB3TradeManagementAppPlugin.class,
        WEB3AdminTMMStopStartChgInputRequest.class,
        WEB3AdminTMMarketStopStartChangeServiceHandler.class,
            "getChangeInputScreen");
        regHandler(
        WEB3TradeManagementAppPlugin.class,
        WEB3AdminTMMStopStartChgConfirmRequest.class,
        WEB3AdminTMMarketStopStartChangeServiceHandler.class,
            "confirmChange");
        regHandler(
        WEB3TradeManagementAppPlugin.class,
        WEB3AdminTMMStopStartChgCompleteRequest.class,
        WEB3AdminTMMarketStopStartChangeServiceHandler.class,
            "completeChange");

        //管理者為替登録ハンドラ
        regHandler(
        WEB3TradeManagementAppPlugin.class,
        WEB3AdminTMExchangeRegistInputRequest.class,
        WEB3AdminTMExchangeRegistHandler.class,
            "getInputScreen");
        regHandler(
        WEB3TradeManagementAppPlugin.class,
        WEB3AdminTMExchangeRegistConfirmRequest.class,
        WEB3AdminTMExchangeRegistHandler.class,
            "validateExchangeRegist");
        regHandler(
        WEB3TradeManagementAppPlugin.class,
        WEB3AdminTMExchangeRegistCompleteRequest.class,
        WEB3AdminTMExchangeRegistHandler.class,
            "submitExchangeRegist");

        //管理者ログイン処理一覧ハンドラ
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminLoginHistoryInputRequest.class,
            WEB3AdminTMLoginProcessingListHandler.class,
            "getSearchInputScreen");
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminLoginHistoryListRequest.class,
            WEB3AdminTMLoginProcessingListHandler.class,
            "getSearchResultScreen");

        //管理者ログイン拒否IP管理ハンドラ
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminIPControlListRequest.class,
            WEB3AdminTMLoginRejectIPManagementHandler.class,
            "getListScreen");
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminIPControlRegistInputRequest.class,
            WEB3AdminTMLoginRejectIPManagementHandler.class,
            "getRegistScreen");
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminIPControlRegistConfirmRequest.class,
            WEB3AdminTMLoginRejectIPManagementHandler.class,
            "validateRegist");
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminIPControlRegistCompleteRequest.class,
            WEB3AdminTMLoginRejectIPManagementHandler.class,
            "submitRegist");
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminIPControlUpdateInputRequest.class,
            WEB3AdminTMLoginRejectIPManagementHandler.class,
            "getChangedScreen");
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminIPControlUpdateConfirmRequest.class,
            WEB3AdminTMLoginRejectIPManagementHandler.class,
            "validateChange");
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminIPControlUpdateCompleteRequest.class,
            WEB3AdminTMLoginRejectIPManagementHandler.class,
            "submitChange");
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminIPControlDeleteConfirmRequest.class,
            WEB3AdminTMLoginRejectIPManagementHandler.class,
            "validateDelete");
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminIPControlDeleteCompleteRequest.class,
            WEB3AdminTMLoginRejectIPManagementHandler.class,
            "submitDelete");

        //IP別ログイン回数一覧ハンドラ
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminLoginCountInputRequest.class,
            WEB3AdminTMLoginFrequencyListHandler.class,
            "getSearchInputScreen");
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminLoginCountListRequest.class,
            WEB3AdminTMLoginFrequencyListHandler.class,
            "getSearchResultScreen");

        log.exiting(METHOD_NAME);
    }
}@

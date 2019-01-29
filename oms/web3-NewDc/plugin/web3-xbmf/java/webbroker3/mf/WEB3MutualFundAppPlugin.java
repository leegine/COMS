head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-xbmf プラグインクラス(WEB3MutualFundAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/25 王蘭芬 (中訊)  新規作成
Revesion History : 2004/12/15 王蘭芬(中訊)残対応
Revesion History : 2006/06/27 肖志偉(中訊) 仕様変更 モデル412,417
Revesion History : 2006/07/25 徐宏偉(中訊) 仕様変更 モデル460,410
Revesion History : 2007/02/07 肖志偉(中訊) 仕様変更 モデル534,536
Revesion History : 2008/07/14 安陽(中訊) 仕様変更 モデル605,608
*/
package webbroker3.mf;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AlreadyInstalledException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotInstalledException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.mf.data.WEB3MfAccountDatabaseExtensions;
import webbroker3.mf.data.WEB3MfMasterDatabaseExtensions;
import webbroker3.mf.data.WEB3MfSessionDatabaseExtensions;
import webbroker3.mf.handler.WEB3AdminMutualCategoryRegistHandler;
import webbroker3.mf.handler.WEB3AdminMutualConditionsHandler;
import webbroker3.mf.handler.WEB3AdminMutualConditionsReferenceHandler;
import webbroker3.mf.handler.WEB3AdminMutualDisplayOrderHandler;
import webbroker3.mf.handler.WEB3AdminMutualTPACancelHandler;
import webbroker3.mf.handler.WEB3AdminMutualTPAdjustHandler;
import webbroker3.mf.handler.WEB3MutualBalanceReferenceHandler;
import webbroker3.mf.handler.WEB3MutualBuyHandler;
import webbroker3.mf.handler.WEB3MutualBuyInputHandler;
import webbroker3.mf.handler.WEB3MutualBuyListHandler;
import webbroker3.mf.handler.WEB3MutualCancelAcceptHandler;
import webbroker3.mf.handler.WEB3MutualCancelHandler;
import webbroker3.mf.handler.WEB3MutualFixedBuyApplyHandler;
import webbroker3.mf.handler.WEB3MutualFixedBuyConditionHandler;
import webbroker3.mf.handler.WEB3MutualFixedBuyConditionListHandler;
import webbroker3.mf.handler.WEB3MutualFrgnMmfOrderAcceptHandler;
import webbroker3.mf.handler.WEB3MutualFrgncalHandler;
import webbroker3.mf.handler.WEB3MutualOrderAcceptServiceHandler;
import webbroker3.mf.handler.WEB3MutualOrderReferenceHandler;
import webbroker3.mf.handler.WEB3MutualRecruitOrderHandler;
import webbroker3.mf.handler.WEB3MutualRecruitOrderInputHandler;
import webbroker3.mf.handler.WEB3MutualSellHandler;
import webbroker3.mf.handler.WEB3MutualSellInputHandler;
import webbroker3.mf.handler.WEB3MutualSellSwtListInquiryHandler;
import webbroker3.mf.handler.WEB3MutualSwitchingHandler;
import webbroker3.mf.handler.WEB3MutualSwitchingInputHandler;
import webbroker3.mf.handler.WEB3MutualSwtProductListHandler;
import webbroker3.mf.handler.WEB3MutualTradeOrderNotifyHandler;
import webbroker3.mf.marketadaptor.WEB3MutualFundMarketAdaptorAppPlugin;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistChangeRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistChangeResponse;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistConfirmRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistConfirmResponse;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistInputRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistInputResponse;
import webbroker3.mf.message.WEB3AdminMutualConditionsCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualConditionsConfirmRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsConfirmResponse;
import webbroker3.mf.message.WEB3AdminMutualConditionsInputRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsInputResponse;
import webbroker3.mf.message.WEB3AdminMutualConditionsRefInputRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsRefInputResponse;
import webbroker3.mf.message.WEB3AdminMutualConditionsReferenceRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsReferenceResponse;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderInputRequest;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderInputResponse;
import webbroker3.mf.message.WEB3AdminMutualFrgncalCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualFrgncalInputRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalInputResponse;
import webbroker3.mf.message.WEB3AdminMutualFrgncalReferenceRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalReferenceResponse;
import webbroker3.mf.message.WEB3AdminMutualTPACancelCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualTPACancelCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualTPACancelListRequest;
import webbroker3.mf.message.WEB3AdminMutualTPACancelListResponse;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustConfirmRequest;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustConfirmResponse;
import webbroker3.mf.message.WEB3MutualApplyCompleteRequest;
import webbroker3.mf.message.WEB3MutualApplyCompleteResponse;
import webbroker3.mf.message.WEB3MutualApplyConfirmRequest;
import webbroker3.mf.message.WEB3MutualApplyConfirmResponse;
import webbroker3.mf.message.WEB3MutualApplyInputRequest;
import webbroker3.mf.message.WEB3MutualApplyInputResponse;
import webbroker3.mf.message.WEB3MutualBalanceReferenceRequest;
import webbroker3.mf.message.WEB3MutualBalanceReferenceResponse;
import webbroker3.mf.message.WEB3MutualBalanceReferenceTotalRequest;
import webbroker3.mf.message.WEB3MutualBalanceReferenceTotalResponse;
import webbroker3.mf.message.WEB3MutualBuyCompleteRequest;
import webbroker3.mf.message.WEB3MutualBuyCompleteResponse;
import webbroker3.mf.message.WEB3MutualBuyConfirmRequest;
import webbroker3.mf.message.WEB3MutualBuyConfirmResponse;
import webbroker3.mf.message.WEB3MutualBuyInputRequest;
import webbroker3.mf.message.WEB3MutualBuyInputResponse;
import webbroker3.mf.message.WEB3MutualBuyListRequest;
import webbroker3.mf.message.WEB3MutualBuyListResponse;
import webbroker3.mf.message.WEB3MutualCancelAcceptRequest;
import webbroker3.mf.message.WEB3MutualCancelAcceptResponse;
import webbroker3.mf.message.WEB3MutualCancelCompleteRequest;
import webbroker3.mf.message.WEB3MutualCancelCompleteResponse;
import webbroker3.mf.message.WEB3MutualCancelConfirmRequest;
import webbroker3.mf.message.WEB3MutualCancelConfirmResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyApplyConfirmRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyApplyConfirmResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyApplyInputRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyApplyInputResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyCommonRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionCompleteRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionCompleteResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionConfirmRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionConfirmResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionInputRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionInputResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionListRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionListResponse;
import webbroker3.mf.message.WEB3MutualFrgnMmfOrderAcceptRequest;
import webbroker3.mf.message.WEB3MutualFrgnMmfOrderAcceptResponse;
import webbroker3.mf.message.WEB3MutualOrderAcceptRequest;
import webbroker3.mf.message.WEB3MutualOrderAcceptResponse;
import webbroker3.mf.message.WEB3MutualOrderReferenceRequest;
import webbroker3.mf.message.WEB3MutualOrderReferenceResponse;
import webbroker3.mf.message.WEB3MutualSellCompleteRequest;
import webbroker3.mf.message.WEB3MutualSellCompleteResponse;
import webbroker3.mf.message.WEB3MutualSellConfirmRequest;
import webbroker3.mf.message.WEB3MutualSellConfirmResponse;
import webbroker3.mf.message.WEB3MutualSellInputRequest;
import webbroker3.mf.message.WEB3MutualSellInputResponse;
import webbroker3.mf.message.WEB3MutualSellSwtListRequest;
import webbroker3.mf.message.WEB3MutualSellSwtListResponse;
import webbroker3.mf.message.WEB3MutualSwTargetListRequest;
import webbroker3.mf.message.WEB3MutualSwTargetListResponse;
import webbroker3.mf.message.WEB3MutualSwitchingCompleteRequest;
import webbroker3.mf.message.WEB3MutualSwitchingCompleteResponse;
import webbroker3.mf.message.WEB3MutualSwitchingConfirmRequest;
import webbroker3.mf.message.WEB3MutualSwitchingConfirmResponse;
import webbroker3.mf.message.WEB3MutualSwitchingInputRequest;
import webbroker3.mf.message.WEB3MutualSwitchingInputResponse;
import webbroker3.mf.message.WEB3MutualTradeOrderNotifyRequest;
import webbroker3.mf.message.WEB3MutualTradeOrderNotifyResponse;
import webbroker3.mf.service.delegate.WEB3AdminMutualCategoryRegistService;
import webbroker3.mf.service.delegate.WEB3AdminMutualConditionsReferenceService;
import webbroker3.mf.service.delegate.WEB3AdminMutualConditionsService;
import webbroker3.mf.service.delegate.WEB3AdminMutualDisplayOrderService;
import webbroker3.mf.service.delegate.WEB3AdminMutualFrgncalService;
import webbroker3.mf.service.delegate.WEB3AdminMutualTPACancelService;
import webbroker3.mf.service.delegate.WEB3AdminMutualTPAdjustService;
import webbroker3.mf.service.delegate.WEB3MutualBalanceReferenceService;
import webbroker3.mf.service.delegate.WEB3MutualBuyInputService;
import webbroker3.mf.service.delegate.WEB3MutualBuyListService;
import webbroker3.mf.service.delegate.WEB3MutualBuyService;
import webbroker3.mf.service.delegate.WEB3MutualCancelAcceptService;
import webbroker3.mf.service.delegate.WEB3MutualCancelAcceptUnitService;
import webbroker3.mf.service.delegate.WEB3MutualCancelService;
import webbroker3.mf.service.delegate.WEB3MutualFixedBuyApplyService;
import webbroker3.mf.service.delegate.WEB3MutualFixedBuyConditionListService;
import webbroker3.mf.service.delegate.WEB3MutualFixedBuyConditionService;
import webbroker3.mf.service.delegate.WEB3MutualFrgnMmfOrderAcceptService;
import webbroker3.mf.service.delegate.WEB3MutualOrderAcceptService;
import webbroker3.mf.service.delegate.WEB3MutualOrderAcceptUnitService;
import webbroker3.mf.service.delegate.WEB3MutualOrderReferenceService;
import webbroker3.mf.service.delegate.WEB3MutualRecruitOrderInputService;
import webbroker3.mf.service.delegate.WEB3MutualRecruitOrderService;
import webbroker3.mf.service.delegate.WEB3MutualSellInputService;
import webbroker3.mf.service.delegate.WEB3MutualSellService;
import webbroker3.mf.service.delegate.WEB3MutualSellSwtListInquiryService;
import webbroker3.mf.service.delegate.WEB3MutualSwitchingInputService;
import webbroker3.mf.service.delegate.WEB3MutualSwitchingService;
import webbroker3.mf.service.delegate.WEB3MutualSwtProductListService;
import webbroker3.mf.service.delegate.WEB3MutualTradeOrderNotifyService;
import webbroker3.mf.service.delegate.WEB3MutualTradeOrderNotifyUnitService;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualCategoryRegistServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualConditionsReferenceServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualConditionsServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualDisplayOrderServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualFrgncalServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualTPACancelServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualTPAdjustServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualBalanceReferenceServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualBuyInputServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualBuyListServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualBuyServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualCancelAcceptServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualCancelAcceptUnitServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualCancelServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyApplyServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyConditionListServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyConditionServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualFrgnMmfOrderAcceptServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualOrderAcceptServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualOrderAcceptUnitServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualOrderReferenceServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualRecruitOrderInputServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualRecruitOrderServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualSellInputServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualSellServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualSellSwtListInquiryServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualSwitchingInputServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualSwitchingServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualSwtProductListServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualTradeOrderNotifyServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualTradeOrderNotifyUnitServiceImpl;
import webbroker3.mqgateway.WEB3MQGatewayInterceptor;
import webbroker3.util.WEB3LogUtility;


/**
 * Webbroker3-MF プラグインクラス。
 *
 * @@author 王蘭芬 (中訊)
 * @@version 1.0
 */
public final class WEB3MutualFundAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3MutualFundAppPlugin()
    {
    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);

        plug(WEB3MutualFundAppPlugin.class);

        log.exiting(METHOD_NAME);
    }

    /**
     * プラグイン処理。
     */
    public static void onPlug() throws Exception
    {
        String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);

        // このプラグインより先に読み込む必要のあるプラグインの指定。
        // install the system plugins that we need
        KernelPlugin.plug();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        // 拡張トランザクション・マネージャーは
        // オーバーライドメソッドが無いため拡張取引モジュールクラスを作成し
        // 拡張取引モジュールクラス内で設定

        try
        {
            l_finApp.uninstallTradingModule("xb-mf-pdbt");
        }
        catch (NotInstalledException l_ex)
        {
            log.debug(l_ex.getMessage());
        }

        try
        {
            log.debug("Installing TradingModule : web3-mf");
            l_finApp.installTradingModule(new WEB3MutualFundTradingModule());
            log.debug("Installed TradingModule : web3-mf");
        }
        catch (AlreadyInstalledException l_ex)
        {
            log.debug(l_ex.getMessage());
        }

        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);

        // 拡張プロダクト・マネージャー
        l_tradingModule.overrideProductManager(new WEB3MutualFundProductManager());

        // 計算サービスクラス
        l_tradingModule.overrideBizLogicProvider(new WEB3MutualFundBizLogicProvider());

        // 拡張注文マネージャ
        l_tradingModule.overrideOrderManager(new WEB3MutualFundOrderManager());

        // ポジションマネージャ
        l_tradingModule.overridePositionManager(new WEB3MutualFundPositionManager());
        
        // 投資信託発注審査個別チェック
        WEB3MutualFundOrderManagerReusableValidationsCheck.register();
        
        // Webbroker3-MF-MarketAdaptor プラグイン
        WEB3MutualFundMarketAdaptorAppPlugin.plug();


        // DatabaseExtensions のプラグイン処理 ----------------------
        WEB3MfMasterDatabaseExtensions.plug();
        WEB3MfAccountDatabaseExtensions.plug();
        WEB3MfSessionDatabaseExtensions.plug();

        // Service の登録処理 ----------------------

        // 投信海外市場カレンダー登録サービスインタフェース
        Services.registerService(
            WEB3AdminMutualFrgncalService.class,
            new WEB3AdminMutualFrgncalServiceImpl());

        // 管理者投信銘柄条件登録サービスインターフェイス
        Services.registerService(
            WEB3AdminMutualConditionsService.class,
            new WEB3AdminMutualConditionsServiceImpl());

        // 管理者投信銘柄条件登録照会サービスインターフェイス
        Services.registerService(
            WEB3AdminMutualConditionsReferenceService.class,
            new WEB3AdminMutualConditionsReferenceServiceImpl());

        // 投信解約サービスインタフェース
        Services.registerService(
            WEB3MutualSellService.class,
            new WEB3MutualSellServiceImpl());

        // 投信解約乗換一覧照会サービス インターフェイス
        Services.registerService(
            WEB3MutualSellSwtListInquiryService.class,
            new WEB3MutualSellSwtListInquiryServiceImpl());

        // 投信解約入力サービスインターフェイス
        Services.registerService(
            WEB3MutualSellInputService.class,
            new WEB3MutualSellInputServiceImpl());

        // 投信取消サービス インタフェース
        Services.registerService(
            WEB3MutualCancelService.class,
            new WEB3MutualCancelServiceImpl());

        // 投信取消受付サービスインターフェイス
        Services.registerService(
            WEB3MutualCancelAcceptService.class,
            new WEB3MutualCancelAcceptServiceImpl());

        // 投信取消受付１件サービスインタフェース
        Services.registerService(
            WEB3MutualCancelAcceptUnitService.class,
            new WEB3MutualCancelAcceptUnitServiceImpl());

        // 投信乗換サービスインターフェイス
        Services.registerService(
            WEB3MutualSwitchingService.class,
            new WEB3MutualSwitchingServiceImpl());

        // 投信乗換入力サービスインタフェース
        Services.registerService(
            WEB3MutualSwitchingInputService.class,
            new WEB3MutualSwitchingInputServiceImpl());

        // 投信注文受付サービスインターフェイス
        Services.registerService(
            WEB3MutualOrderAcceptService.class,
            new WEB3MutualOrderAcceptServiceImpl());

        // 投信注文受付１件サービスインタフェース
        Services.registerService(
            WEB3MutualOrderAcceptUnitService.class,
            new WEB3MutualOrderAcceptUnitServiceImpl());

        // 投信注文照会サービスインターフェイスクラス
        Services.registerService(
            WEB3MutualOrderReferenceService.class,
            new WEB3MutualOrderReferenceServiceImpl());

        // 投信買付一覧照会サービスインターフェイス
        Services.registerService(
            WEB3MutualBuyListService.class,
            new WEB3MutualBuyListServiceImpl());

        // 投信買付注文サービス インターフェイス
        Services.registerService(
            WEB3MutualBuyService.class,
            new WEB3MutualBuyServiceImpl());

        // 投信買付注文入力サービスインタフェース
        Services.registerService(
            WEB3MutualBuyInputService.class,
            new WEB3MutualBuyInputServiceImpl());


        // 投信売買注文通知サービスインターフェイス
        Services.registerService(
            WEB3MutualTradeOrderNotifyService.class,
            new WEB3MutualTradeOrderNotifyServiceImpl());

        // 投信売買注文通知１件サービスインタフェース
        Services.registerService(
            WEB3MutualTradeOrderNotifyUnitService.class,
            new WEB3MutualTradeOrderNotifyUnitServiceImpl());

        // 管理者カテゴリー登録サービス 
        Services.registerService(
            WEB3AdminMutualCategoryRegistService.class,
            new WEB3AdminMutualCategoryRegistServiceImpl());

        // 投信管理者銘柄表示順序登録サービス
        Services.registerService(
            WEB3AdminMutualDisplayOrderService.class,
            new WEB3AdminMutualDisplayOrderServiceImpl());
        
        // 投信残高照会サービスインタフェイス
        Services.registerService(
            WEB3MutualBalanceReferenceService.class,
            new WEB3MutualBalanceReferenceServiceImpl());
        
        // 投信募集注文入力サービス
        Services.registerService(
            WEB3MutualRecruitOrderInputService.class,
            new WEB3MutualRecruitOrderInputServiceImpl());
        
        // 投信募集注文サービス
        Services.registerService(
            WEB3MutualRecruitOrderService.class,
            new WEB3MutualRecruitOrderServiceImpl());
        
        // 投信乗換先銘柄一覧照会サービス 
        Services.registerService(
            WEB3MutualSwtProductListService.class,
            new WEB3MutualSwtProductListServiceImpl());
        
        // 投信管理者余力調整サービス 
        Services.registerService(
            WEB3AdminMutualTPAdjustService.class,
            new WEB3AdminMutualTPAdjustServiceImpl());
        
        // 投信管理者余力調整取消サービス 
        Services.registerService(
            WEB3AdminMutualTPACancelService.class,
            new WEB3AdminMutualTPACancelServiceImpl());
        
        // 投信定時定額買付新規申込サービス
        Services.registerService(
            WEB3MutualFixedBuyApplyService.class,
            new WEB3MutualFixedBuyApplyServiceImpl());
        
        //投信定時定額買付条件一覧サービス 
        Services.registerService(
            WEB3MutualFixedBuyConditionListService.class,
            new WEB3MutualFixedBuyConditionListServiceImpl());
        
        // 定時定額買付共通サービス
        Services.registerService(
            WEB3MutualFixedBuyCommonService.class,
            new WEB3MutualFixedBuyCommonServiceImpl());
        
        //外貨MMF注文受付サービス
        Services.registerService(
            WEB3MutualFrgnMmfOrderAcceptService.class,
            new WEB3MutualFrgnMmfOrderAcceptServiceImpl());

        //投信定時定額買付銘柄条件登録サービス
        Services.registerService(
            WEB3MutualFixedBuyConditionService.class,
            new WEB3MutualFixedBuyConditionServiceImpl());

        // Service.execute()呼び出し前後に  ----------------------
        // 処理開始時刻と処理終了時刻をログ出力するように設定する

        // 投信海外市場カレンダー登録サービスインタフェース
        Services.addInterceptor(
            WEB3AdminMutualFrgncalService.class,
            new WEB3LogSysTimeInterceptor());

        // 管理者投信銘柄条件登録サービスインターフェイス
        Services.addInterceptor(
            WEB3AdminMutualConditionsService.class,
            new WEB3LogSysTimeInterceptor());

        // 管理者投信銘柄条件登録照会サービスインターフェイス
        Services.addInterceptor(
            WEB3AdminMutualConditionsReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // 投信解約サービス インタフェース
        Services.addInterceptor(
            WEB3MutualSellService.class,
            new WEB3LogSysTimeInterceptor());

        // 投信解約乗換一覧照会サービスインターフェイス
        Services.addInterceptor(
            WEB3MutualSellSwtListInquiryService.class,
            new WEB3LogSysTimeInterceptor());

        // 投信解約入力サービスインターフェイス
        Services.addInterceptor(
            WEB3MutualSellInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 投信取消サービス インタフェース
        Services.addInterceptor(
            WEB3MutualCancelService.class,
            new WEB3LogSysTimeInterceptor());

        // 投信取消受付サービスインターフェイス
        Services.addInterceptor(
            WEB3MutualCancelAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        // 投信取消受付１件サービスインタフェース
        Services.addInterceptor(
            WEB3MutualCancelAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 投信乗換サービスインターフェイス
        Services.addInterceptor(
            WEB3MutualSwitchingService.class,
            new WEB3LogSysTimeInterceptor());

        // 投信乗換入力サービスインタフェース
        Services.addInterceptor(
            WEB3MutualSwitchingInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 投信注文受付サービスインターフェイス
        Services.addInterceptor(
            WEB3MutualOrderAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        // 投信注文受付１件サービスインタフェース
        Services.addInterceptor(
            WEB3MutualOrderAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 投信注文照会サービスインターフェイスクラス
        Services.addInterceptor(
            WEB3MutualOrderReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // 投信買付一覧照会サービス インターフェイス
        Services.addInterceptor(
            WEB3MutualBuyListService.class,
            new WEB3LogSysTimeInterceptor());

        // 投信買付注文サービスインターフェイス
        Services.addInterceptor(
            WEB3MutualBuyService.class,
            new WEB3LogSysTimeInterceptor());

        // 投信買付注文入力サービスインタフェース
        Services.addInterceptor(
            WEB3MutualBuyInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 投信売買注文通知サービスインターフェイスクラス
        Services.addInterceptor(
            WEB3MutualTradeOrderNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        // 投信売買注文通知１件サービスインターフェイス
        Services.addInterceptor(
            WEB3MutualTradeOrderNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 管理者カテゴリー登録サービス ログ
        Services.addInterceptor(
            WEB3AdminMutualCategoryRegistService.class,
            new WEB3LogSysTimeInterceptor());

        // 投信管理者銘柄表示順序登録サービス
        Services.addInterceptor(
            WEB3AdminMutualDisplayOrderService.class,
            new WEB3LogSysTimeInterceptor());

        // 投信残高照会サービス
        Services.addInterceptor(
            WEB3MutualBalanceReferenceService.class,
            new WEB3LogSysTimeInterceptor());
        
        // 投信募集注文入力サービス
        Services.addInterceptor(
            WEB3MutualRecruitOrderInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 投信募集注文サービス
        Services.addInterceptor(
            WEB3MutualRecruitOrderService.class,
            new WEB3LogSysTimeInterceptor());
        
        // 投信乗換先銘柄一覧照会サービス 
        Services.addInterceptor(
            WEB3MutualSwtProductListService.class,
            new WEB3LogSysTimeInterceptor());
        
        // 投信管理者余力調整サービス 
        Services.addInterceptor(
            WEB3AdminMutualTPAdjustService.class,
            new WEB3LogSysTimeInterceptor());
        
        // 投信管理者余力調整取消サービス
        Services.addInterceptor(
            WEB3AdminMutualTPACancelService.class,
            new WEB3LogSysTimeInterceptor());
        
        //投信定時定額買付新規申込サービス
        Services.addInterceptor(
            WEB3MutualFixedBuyApplyService.class,
            new WEB3LogSysTimeInterceptor());
        
        //投信定時定額買付条件一覧サービス
        Services.addInterceptor(
            WEB3MutualFixedBuyConditionListService.class,
            new WEB3LogSysTimeInterceptor());

        //外貨MMF注文受付サービス
        Services.addInterceptor(
            WEB3MutualFrgnMmfOrderAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        //投信定時定額買付銘柄条件登録サービス
        Services.addInterceptor(
            WEB3MutualFixedBuyConditionService.class,
            new WEB3LogSysTimeInterceptor());

        //Service に ServiceInterceptor を設定する ----------------------
        
        // 投信海外市場カレンダー登録サービスインタフェース
        Services.addInterceptor(
            WEB3AdminMutualConditionsService.class,
            new WEB3AdminMutualConditionsServiceInterceptor());
        
        // 投信解約サービス インタフェース
        Services.addInterceptor(
            WEB3MutualSellService.class,
            new WEB3MutualSellServiceInterceptor());

        // 投信解約乗換一覧照会サービスインターフェイス
        Services.addInterceptor(
            WEB3MutualSellSwtListInquiryService.class,
            new WEB3MutualSellSwtListInquiryServiceInterceptor());

        // 投信解約入力サービスインターフェイス
        Services.addInterceptor(
            WEB3MutualSellInputService.class,
            new WEB3MutualSellInputSeviceInterceptor());

        // 投信取消サービス インタフェース
        Services.addInterceptor(
            WEB3MutualCancelService.class,
            new WEB3MutualCancelServiceInterceptor());

        // 投信取消受付１件サービスインタフェース
        Services.addInterceptor(
            WEB3MutualCancelAcceptUnitService.class,
            new WEB3MutualCancelAcceptUnitServiceInterceptor());

        // 投信乗換サービスインターフェイス
        Services.addInterceptor(
            WEB3MutualSwitchingService.class,
            new WEB3MutualSwitchingServiceInterceptor());

        // 投信乗換入力サービスインタフェース
        Services.addInterceptor(
            WEB3MutualSwitchingInputService.class,
            new WEB3MutualSwitchingInputServiceInterceptor());

        // 投信注文受付１件サービスインタフェース
        Services.addInterceptor(
            WEB3MutualOrderAcceptUnitService.class,
            new WEB3MutualOrderAcceptUnitServiceInterceptor());

        // 投信注文照会サービスインターフェイスクラス
        Services.addInterceptor(
            WEB3MutualOrderReferenceService.class,
            new WEB3MutualOrderReferenceServiceInterceptor());

        // 投信買付一覧照会サービス インターフェイス
        Services.addInterceptor(
            WEB3MutualBuyListService.class,
            new WEB3MutualBuyListServiceInterceptor());

        // 投信買付注文サービスインターフェイス
        Services.addInterceptor(
            WEB3MutualBuyService.class,
            new WEB3MutualBuyServiceInterceptor());

        // 投信買付注文入力サービスインタフェース
        Services.addInterceptor(
            WEB3MutualBuyInputService.class,
            new WEB3MutualBuyInputServiceInterceptor());

        // 投信売買注文通知１件サービスインターフェイス
        Services.addInterceptor(
            WEB3MutualTradeOrderNotifyUnitService.class,
            new WEB3MutualTradeOrderNotifyUnitServiceIntercetor());
            
        // 投信管理者銘柄表示順序登録サービスインタセプタ
        Services.addInterceptor(
            WEB3AdminMutualDisplayOrderService.class,
            new WEB3AdminMutualDisplayOrderServiceInterceptor());
         
        // 投信残高照会サービス
        Services.addInterceptor(
            WEB3MutualBalanceReferenceService.class,
            new WEB3MutualBalanceReferenceServiceInterceptor());
        
        // 投信募集注文入力サービス
        Services.addInterceptor(
            WEB3MutualRecruitOrderInputService.class,
            new WEB3MutualRecruitOrderInputServiceInterceptor());

        // 投信募集注文サービス
        Services.addInterceptor(
            WEB3MutualRecruitOrderService.class,
            new WEB3MutualRecruitOrderServiceInterceptor());
        
        // 投信乗換先銘柄一覧照会サービス 
        Services.addInterceptor(
            WEB3MutualSwtProductListService.class,
            new WEB3MutualSwtProductListInterceptor());
        
        // 投信管理者余力調整サービス 
        Services.addInterceptor(
            WEB3AdminMutualTPAdjustService.class,
            new WEB3AdminMutualTPAdjustServiceInterceptor());
        
        // 投信管理者余力調整取消サービス
        Services.addInterceptor(
            WEB3AdminMutualTPACancelService.class,
            new WEB3AdminMutualTPACancelServiceInterceptor());
        
        //投信定時定額買付新規申込サービス
        Services.addInterceptor(
            WEB3MutualFixedBuyApplyService.class,
            new WEB3MutualFixedBuyApplyServiceInterceptor());
        
        //投信定時定額買付条件一覧サービスインタセプタ    
        Services.addInterceptor(
            WEB3MutualFixedBuyConditionListService.class,
            new WEB3MutualFixedBuyConditionListServiceInterceptor());

        //投信定時定額買付銘柄条件登録サービスインタセプタ
        Services.addInterceptor(
            WEB3MutualFixedBuyConditionService.class,
            new WEB3MutualFixedBuyConditionServiceInterceptor());

        // Service の Interceptor 設定処理 ----------------------
        // 自動トランザクション設定

        // 投信海外市場カレンダー登録サービスインタフェース
        Services.addInterceptor(
            WEB3AdminMutualFrgncalService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 管理者投信銘柄条件登録サービスインターフェイス
        Services.addInterceptor(
            WEB3AdminMutualConditionsService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 管理者投信銘柄条件登録照会サービスインターフェイス
        Services.addInterceptor(
            WEB3AdminMutualConditionsReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 投信解約サービス インタフェース
        Services.addInterceptor(
            WEB3MutualSellService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 投信解約乗換一覧照会サービスインターフェイス
        Services.addInterceptor(
            WEB3MutualSellSwtListInquiryService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 投信解約入力サービスインターフェイス
        Services.addInterceptor(
            WEB3MutualSellInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 投信取消サービス インタフェース
        Services.addInterceptor(
            WEB3MutualCancelService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 投信取消受付１件サービスインタフェース
        Services.addInterceptor(
            WEB3MutualCancelAcceptUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 投信乗換サービスインターフェイス
        Services.addInterceptor(
            WEB3MutualSwitchingService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 投信乗換入力サービスインタフェース
        Services.addInterceptor(
            WEB3MutualSwitchingInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 投信注文受付１件サービスインタフェース
        Services.addInterceptor(
            WEB3MutualOrderAcceptUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 投信注文照会サービスインターフェイスクラス
        Services.addInterceptor(
            WEB3MutualOrderReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 投信買付一覧照会サービス インターフェイス
        Services.addInterceptor(
            WEB3MutualBuyListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 投信買付注文サービスインターフェイス
        Services.addInterceptor(
            WEB3MutualBuyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 投信買付注文入力サービスインタフェース
        Services.addInterceptor(
            WEB3MutualBuyInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 投信売買注文通知１件サービスインターフェイス
        Services.addInterceptor(
            WEB3MutualTradeOrderNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 管理者カテゴリー登録サービス 
        Services.addInterceptor(
            WEB3AdminMutualCategoryRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 投信管理者銘柄表示順序登録サービス
        Services.addInterceptor(
            WEB3AdminMutualDisplayOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 投信残高照会サービス
        Services.addInterceptor(
            WEB3MutualBalanceReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        // 投信募集注文入力サービス
        Services.addInterceptor(
            WEB3MutualRecruitOrderInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 投信募集注文サービス
        Services.addInterceptor(
            WEB3MutualRecruitOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        // 投信乗換先銘柄一覧照会サービス 
        Services.addInterceptor(
            WEB3MutualSwtProductListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        // 投信管理者余力調整サービス 
        Services.addInterceptor(
            WEB3AdminMutualTPAdjustService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        // 投信管理者余力調整取消サービス
        Services.addInterceptor(
            WEB3AdminMutualTPACancelService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //投信定時定額買付新規申込サービス インタフェース
        Services.addInterceptor(
            WEB3MutualFixedBuyApplyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //投信定時定額買付条件一覧サービス 
        Services.addInterceptor(
            WEB3MutualFixedBuyConditionListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //外貨MMF注文受付サービス
        Services.addInterceptor(
            WEB3MutualFrgnMmfOrderAcceptService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //投信定時定額買付銘柄条件登録サービス
        Services.addInterceptor(
            WEB3MutualFixedBuyConditionService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // MQGatewayInterceptorの設定 ---------------
        
        // 投信買付サービス
        Services.addInterceptor(
            WEB3MutualBuyService.class,
            new WEB3MQGatewayInterceptor());
        
        // 投信解約サービス
        Services.addInterceptor(
            WEB3MutualSellService.class,
            new WEB3MQGatewayInterceptor());
        
        // 投信乗換サービス
        Services.addInterceptor(
            WEB3MutualSwitchingService.class,
            new WEB3MQGatewayInterceptor());
        
        // 投信取消サービス
        Services.addInterceptor(
            WEB3MutualCancelService.class,
            new WEB3MQGatewayInterceptor());
        
        // 投信募集注文サービス
        Services.addInterceptor(
            WEB3MutualRecruitOrderService.class,
            new WEB3MQGatewayInterceptor());

        // Message の登録処理 ----------------------

        // 投信解約確認リクエスト 
        regClass(WEB3MutualSellConfirmRequest.class);
        // 投信解約確認レスポンス 
        regClass(WEB3MutualSellConfirmResponse.class);
        // 投信解約完了リクエスト 
        regClass(WEB3MutualSellCompleteRequest.class);
        // 投信解約完了レスポンス 
        regClass(WEB3MutualSellCompleteResponse.class);
        // 投信解約乗換一覧照会リクエスト 
        regClass(WEB3MutualSellSwtListRequest.class);
        // 投信解約乗換一覧照会レスポンス 
        regClass(WEB3MutualSellSwtListResponse.class);
        // 投信解約入力リクエスト 
        regClass(WEB3MutualSellInputRequest.class);
        // 投信解約入力レスポンス 
        regClass(WEB3MutualSellInputResponse.class);
        // 投信海外市場カレンダー登録完了リクエスト 
        regClass(WEB3AdminMutualFrgncalCompleteRequest.class);
        // 投信海外市場カレンダー登録完了レスポンス 
        regClass(WEB3AdminMutualFrgncalCompleteResponse.class);
        // 投信海外市場カレンダー登録照会リクエスト 
        regClass(WEB3AdminMutualFrgncalReferenceRequest.class);
        // 投信海外市場カレンダー登録照会レスポンス 
        regClass(WEB3AdminMutualFrgncalReferenceResponse.class);
        // 投信海外市場カレンダー登録入力リクエスト 
        regClass(WEB3AdminMutualFrgncalInputRequest.class);
        // 投信海外市場カレンダー登録入力レスポンス 
        regClass(WEB3AdminMutualFrgncalInputResponse.class);
        // 投信取消確認リクエスト 
        regClass(WEB3MutualCancelConfirmRequest.class);
        // 投信取消確認レスポンス 
        regClass(WEB3MutualCancelConfirmResponse.class);
        // 投信取消完了リクエスト
        regClass(WEB3MutualCancelCompleteRequest.class);
        // 投信取消完了レスポンス 
        regClass(WEB3MutualCancelCompleteResponse.class);
        // 投信取消受付リクエスト 
        regClass(WEB3MutualCancelAcceptRequest.class);
        // 投信取消受付レスポンス 
        regClass(WEB3MutualCancelAcceptResponse.class);
        // 投信乗換確認リクエスト 
        regClass(WEB3MutualSwitchingConfirmRequest.class);
        // 投信乗換確認レスポンス 
        regClass(WEB3MutualSwitchingConfirmResponse.class);
        // 投信乗換完了リクエスト 
        regClass(WEB3MutualSwitchingCompleteRequest.class);
        // 投信乗換完了レスポンス 
        regClass(WEB3MutualSwitchingCompleteResponse.class);
        // 投信乗換入力リクエスト 
        regClass(WEB3MutualSwitchingInputRequest.class);
        // 投信乗換入力レスポンス 
        regClass(WEB3MutualSwitchingInputResponse.class);
        // 投信注文受付リクエスト 
        regClass(WEB3MutualOrderAcceptRequest.class);
        // 投信注文受付レスポンス 
        regClass(WEB3MutualOrderAcceptResponse.class);
        // 投信注文照会リクエスト 
        regClass(WEB3MutualOrderReferenceRequest.class);
        // 投信注文照会レスポンス 
        regClass(WEB3MutualOrderReferenceResponse.class);
        // 投信買付一覧照会リクエスト 
        regClass(WEB3MutualBuyListRequest.class);
        // 投信買付一覧照会レスポンス 
        regClass(WEB3MutualBuyListResponse.class);
        // 投信買付注文確認リクエスト 
        regClass(WEB3MutualBuyConfirmRequest.class);
        // 投信買付注文確認レスポンス 
        regClass(WEB3MutualBuyConfirmResponse.class);
        // 投信買付注文完了リクエスト 
        regClass(WEB3MutualBuyCompleteRequest.class);
        // 投信買付注文完了レスポンス 
        regClass(WEB3MutualBuyCompleteResponse.class);
        // 投信買付注文入力リクエスト 
        regClass(WEB3MutualBuyInputRequest.class);
        // 投信買付注文入力レスポンス 
        regClass(WEB3MutualBuyInputResponse.class);
        // 投信売買注文通知リクエスト 
        regClass(WEB3MutualTradeOrderNotifyRequest.class);
        // 投信売買注文通知レスポンス 
        regClass(WEB3MutualTradeOrderNotifyResponse.class);
        // 投信銘柄条件登録確認リクエスト 
        regClass(WEB3AdminMutualConditionsConfirmRequest.class);
        // 投信銘柄条件登録確認レスポンス 
        regClass(WEB3AdminMutualConditionsConfirmResponse.class);
        // 投信銘柄条件登録完了リクエスト 
        regClass(WEB3AdminMutualConditionsCompleteRequest.class);
        // 投信銘柄条件登録完了レスポンス 
        regClass(WEB3AdminMutualConditionsCompleteResponse.class);
        // 投信銘柄条件登録照会リクエスト 
        regClass(WEB3AdminMutualConditionsReferenceRequest.class);
        // 投信銘柄条件登録照会レスポンス 
        regClass(WEB3AdminMutualConditionsReferenceResponse.class);
        // 投信銘柄条件登録照会入力リクエスト 
        regClass(WEB3AdminMutualConditionsRefInputRequest.class);
        // 投信銘柄条件登録照会入力レスポンス 
        regClass(WEB3AdminMutualConditionsRefInputResponse.class);
        // 投信銘柄条件登録入力リクエスト 
        regClass(WEB3AdminMutualConditionsInputRequest.class);
        // 投信銘柄条件登録入力レスポンス 
        regClass(WEB3AdminMutualConditionsInputResponse.class);       
        //投信募集注文入力リクエスト    
        regClass(WEB3MutualApplyInputRequest.class);
        //投信募集注文入力レスポンス    
        regClass(WEB3MutualApplyInputResponse.class);
        //投信募集注文確認リクエスト    
        regClass(WEB3MutualApplyConfirmRequest.class);       
        //投信募集注文確認レスポンス    
        regClass(WEB3MutualApplyConfirmResponse.class);       
        //投信募集注文完了リクエスト    
        regClass(WEB3MutualApplyCompleteRequest.class);       
        //投信募集注文完了レスポンス    
        regClass(WEB3MutualApplyCompleteResponse.class);              
        //投信乗換先銘柄一覧リクエスト
        regClass(WEB3MutualSwTargetListRequest.class);       
        //投信乗換先銘柄一覧レスポンス
        regClass(WEB3MutualSwTargetListResponse.class);       
        //投信定時定額買付共通リクエスト
        regClass(WEB3MutualFixedBuyCommonRequest.class);
        //投信定時定額買付新規申込入力リクエスト
        regClass(WEB3MutualFixedBuyApplyInputRequest.class);
        //投信定時定額買付新規申込入力レスポンス
        regClass(WEB3MutualFixedBuyApplyInputResponse.class);
        //投信定時定額買付新規申込確認リクエスト
        regClass(WEB3MutualFixedBuyApplyConfirmRequest.class);
        //投信定時定額買付新規申込確認レスポンス
        regClass(WEB3MutualFixedBuyApplyConfirmResponse.class);
        //投信定時定額買付条件一覧リクエスト    
        regClass(WEB3MutualFixedBuyConditionListRequest.class);
        //投信定時定額買付条件一覧レスポンス    
        regClass(WEB3MutualFixedBuyConditionListResponse.class);
        //外貨MMF注文受付リクエスト
        regClass(WEB3MutualFrgnMmfOrderAcceptRequest.class);
        //外貨MMF注文受付レスポンス
        regClass(WEB3MutualFrgnMmfOrderAcceptResponse.class);

        // 投信管理者カテゴリー登録入力画面要求  
        regClass(WEB3AdminMutualCategoryRegistInputRequest.class);
        // 投信管理者カテゴリー登録入力画面応答　@ 
        regClass(WEB3AdminMutualCategoryRegistInputResponse.class);
        // 投信管理者カテゴリー変更入力画面要求　@　@    
        regClass(WEB3AdminMutualCategoryRegistChangeRequest.class);
        // 投信管理者カテゴリー変更入力画面応答  
        regClass(WEB3AdminMutualCategoryRegistChangeResponse.class);
        // 投信管理者カテゴリー登録確認要求　@　@  
        regClass(WEB3AdminMutualCategoryRegistConfirmRequest.class);
        // 投信管理者カテゴリー登録確認応答    
        regClass(WEB3AdminMutualCategoryRegistConfirmResponse.class);
        // 投信管理者カテゴリー登録完了要求    
        regClass(WEB3AdminMutualCategoryRegistCompleteRequest.class);
        // 投信管理者カテゴリー登録完了応答    
        regClass(WEB3AdminMutualCategoryRegistCompleteResponse.class);
        // 投信管理者銘柄表示順序登録入力画面要求　@    
        regClass(WEB3AdminMutualDisplayOrderInputRequest.class);
        // 投信管理者銘柄表示順序登録入力画面応答 
        regClass(WEB3AdminMutualDisplayOrderInputResponse.class);
        // 投信管理者銘柄表示順序登録完了要求　@  
        regClass(WEB3AdminMutualDisplayOrderCompleteRequest.class);
        // 投信管理者銘柄表示順序登録完了応答   
        regClass(WEB3AdminMutualDisplayOrderCompleteResponse.class);

        //投信残高照会
        regClass(WEB3MutualBalanceReferenceRequest.class);
        regClass(WEB3MutualBalanceReferenceResponse.class);
        regClass(WEB3MutualBalanceReferenceTotalRequest.class);
        regClass(WEB3MutualBalanceReferenceTotalResponse.class);
        
        // 投信管理者余力調整取消一覧リクエスト
        regClass(WEB3AdminMutualTPACancelListRequest.class);
        // 投信管理者余力調整取消一覧レスポンス
        regClass(WEB3AdminMutualTPACancelListResponse.class);
        // 投信管理者余力調整取消完了リクエスト　@  
        regClass(WEB3AdminMutualTPACancelCompleteRequest.class);
        // 投信管理者余力調整取消完了レスポンス 
        regClass(WEB3AdminMutualTPACancelCompleteResponse.class);
        
        // 投信管理者余力調整確認リクエスト
        regClass(WEB3AdminMutualTPAdjustConfirmRequest.class);
        // 投信管理者余力調整確認レスポンス
        regClass(WEB3AdminMutualTPAdjustConfirmResponse.class);
        // 投信管理者余力調整完了リクエスト
        regClass(WEB3AdminMutualTPAdjustCompleteRequest.class);
        // 投信管理者余力調整完了レスポンス
        regClass(WEB3AdminMutualTPAdjustCompleteResponse.class);

        // 投信定時定額買付銘柄条件登録入力リクエスト
        regClass(WEB3MutualFixedBuyConditionInputRequest.class);
        // 投信定時定額買付銘柄条件登録入力レスポンス
        regClass(WEB3MutualFixedBuyConditionInputResponse.class);
        // 投信定時定額買付銘柄条件登録確認リクエスト
        regClass(WEB3MutualFixedBuyConditionConfirmRequest.class);
        // 投信定時定額買付銘柄条件登録確認レスポンス
        regClass(WEB3MutualFixedBuyConditionConfirmResponse.class);
        // 投信定時定額買付銘柄条件登録完了リクエスト
        regClass(WEB3MutualFixedBuyConditionCompleteRequest.class);
        // 投信定時定額買付銘柄条件登録完了レスポンス
        regClass(WEB3MutualFixedBuyConditionCompleteResponse.class);

        //Handler の登録処理 ----------------------

        // 投信海外市場カレンダー登録ハンドラの登録 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualFrgncalInputRequest.class,
            WEB3MutualFrgncalHandler.class,
            "frgncalInputRequest");     
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualFrgncalReferenceRequest.class,
            WEB3MutualFrgncalHandler.class,
            "searchFrgncalReg");     
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualFrgncalCompleteRequest.class,
            WEB3MutualFrgncalHandler.class,
            "completeFrgncalReg");     
        // 管理者投信銘柄条件登録ハンドラの登録
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualConditionsInputRequest.class,
            WEB3AdminMutualConditionsHandler.class,
            "productConditionsRegistInputRequest");     
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualConditionsConfirmRequest.class,
            WEB3AdminMutualConditionsHandler.class,
            "searchProductConditionsRegist");     
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualConditionsCompleteRequest.class,
            WEB3AdminMutualConditionsHandler.class,
            "completeProductConditionsRegist");     
        // 管理者投信銘柄条件登録照会ハンドラの登録 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualConditionsRefInputRequest.class,
            WEB3AdminMutualConditionsReferenceHandler.class,
            "productConditionsRegistRefRequest");
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualConditionsReferenceRequest.class,
            WEB3AdminMutualConditionsReferenceHandler.class,
            "searchProductConditionsRegist");
        // 投信解約ハンドラの登録 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualSellConfirmRequest.class,
            WEB3MutualSellHandler.class,
            "confirmSell");     
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualSellCompleteRequest.class,
            WEB3MutualSellHandler.class,
            "completeSell");     
        // 投信解約乗換一覧照会ハンドラの登録
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualSellSwtListRequest.class,
            WEB3MutualSellSwtListInquiryHandler.class,
            "searchSellSwtList");     
        // 投信解約入力ハンドラの登録 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualSellInputRequest.class,
            WEB3MutualSellInputHandler.class,
            "sellInputRequest");     
        // 投信取消ハンドラの登録 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualCancelConfirmRequest.class,
            WEB3MutualCancelHandler.class,
            "confirmCancel");     
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualCancelCompleteRequest.class,
            WEB3MutualCancelHandler.class,
            "completeCancel");     
        // 投信取消受付ハンドラの登録 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualCancelAcceptRequest.class,
            WEB3MutualCancelAcceptHandler.class,
            "cancelAcceptRequest");     
        // 投信乗換ハンドラの登録
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualSwitchingConfirmRequest.class,
            WEB3MutualSwitchingHandler.class,
            "confirmSwitching");     
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualSwitchingCompleteRequest.class,
            WEB3MutualSwitchingHandler.class,
            "completeSwitching");     
        // 投信乗換入力ハンドラの登録
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualSwitchingInputRequest.class,
            WEB3MutualSwitchingInputHandler.class,
            "switchingInputRequest");     
        // 投信注文受付ハンドラの登録
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualOrderAcceptRequest.class,
            WEB3MutualOrderAcceptServiceHandler.class,
            "mutualOrderAcceptRequest");     
        // 投信注文照会ハンドラの登録 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualOrderReferenceRequest.class,
            WEB3MutualOrderReferenceHandler.class,
            "searchOrder");     
        // 投信買付一覧照会ハンドラの登録 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualBuyListRequest.class,
            WEB3MutualBuyListHandler.class,
            "searchOrder");     
        // 投信買付注文ハンドラの登録 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualBuyConfirmRequest.class,
            WEB3MutualBuyHandler.class,
            "confirmBuyOrder");     
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualBuyCompleteRequest.class,
            WEB3MutualBuyHandler.class,
            "completeBuyOrder");     
        // 投信買付注文入力ハンドラの登録
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualBuyInputRequest.class,
            WEB3MutualBuyInputHandler.class,
            "buyInputRequest");     
        // 投信売買注文通知ハンドラの登録 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualTradeOrderNotifyRequest.class,
            WEB3MutualTradeOrderNotifyHandler.class,
            "tradeOrderNotifyRequest");     

        // 投信管理者カテゴリー登録ハンドラ  の登録 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualCategoryRegistInputRequest.class,
            WEB3AdminMutualCategoryRegistHandler.class,
            "getCategoryRegistrInput");     
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualCategoryRegistChangeRequest.class,
            WEB3AdminMutualCategoryRegistHandler.class,
            "getCategoryRegistChangeInput");     
        regHandler(
                WEB3MutualFundAppPlugin.class,
                WEB3AdminMutualCategoryRegistConfirmRequest.class,
                WEB3AdminMutualCategoryRegistHandler.class,
                "confirmCategoryRegistRequest");     
        regHandler(
                WEB3MutualFundAppPlugin.class,
                WEB3AdminMutualCategoryRegistCompleteRequest.class,
                WEB3AdminMutualCategoryRegistHandler.class,
                "completeCategoryRegistRequest");     

        // 投信管理者銘柄表示順序登録ハンドラの登録 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualDisplayOrderInputRequest.class,
            WEB3AdminMutualDisplayOrderHandler.class,
            "getProductDisplayOrderInput");     
        regHandler(
                WEB3MutualFundAppPlugin.class,
                WEB3AdminMutualDisplayOrderCompleteRequest.class,
                WEB3AdminMutualDisplayOrderHandler.class,
                "completeAdminMutualDisplayOrderRegistr");     
        
        //投信残高照会ハンドラの登録
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualBalanceReferenceRequest.class,
            WEB3MutualBalanceReferenceHandler.class,
            "getBalanceReference");     
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualBalanceReferenceTotalRequest.class,
            WEB3MutualBalanceReferenceHandler.class,
            "getBalanceReferenceTotal");    
        
        //投信募集注文入力ハンドラの登録
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualApplyInputRequest.class,
            WEB3MutualRecruitOrderInputHandler.class,
            "RecruitOrderInput");
        //投信募集注文ハンドラの登録
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualApplyConfirmRequest.class,
            WEB3MutualRecruitOrderHandler.class,
            "RecruitOrderValidation"); 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualApplyCompleteRequest.class,
            WEB3MutualRecruitOrderHandler.class,
            "RecruitOrderRegister");  
        
        //投信乗換先銘柄一覧照会ハンドラ 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualSwTargetListRequest.class,
            WEB3MutualSwtProductListHandler.class,
            "swtListRequest");   
        
        //投信管理者余力調整取消ハンドラの登録
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualTPACancelListRequest.class,
            WEB3AdminMutualTPACancelHandler.class,
            "tpACancelList"); 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualTPACancelCompleteRequest.class,
            WEB3AdminMutualTPACancelHandler.class,
            "tpACancelComplete");  
        
        //投信管理者余力調整ハンドラの登録
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualTPAdjustConfirmRequest.class,
            WEB3AdminMutualTPAdjustHandler.class,
            "tpAdjustConfirm"); 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualTPAdjustCompleteRequest.class,
            WEB3AdminMutualTPAdjustHandler.class,
            "tpAdjustComplete");  
        
        //投信定時定額買付新規申込ハンドラの登録
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualFixedBuyApplyInputRequest.class,
            WEB3MutualFixedBuyApplyHandler.class,
            "fixedBuyApplyInput");
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualFixedBuyApplyConfirmRequest.class,
            WEB3MutualFixedBuyApplyHandler.class,
            "fixedBuyApplyValidate");
        
        //投信定時定額買付条件一覧ハンドラの登録
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualFixedBuyConditionListRequest.class,
            WEB3MutualFixedBuyConditionListHandler.class,
            "searchOrder");
        
        //外貨MMF注文受付ハンドラの登録
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualFrgnMmfOrderAcceptRequest.class,
            WEB3MutualFrgnMmfOrderAcceptHandler.class,
            "orderAcceptRequest");

        //投信定時定額買付銘柄条件登録ハンドラの登録
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualFixedBuyConditionInputRequest.class,
            WEB3MutualFixedBuyConditionHandler.class,
            "mutualFixedBuyConditionInput");
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualFixedBuyConditionConfirmRequest.class,
            WEB3MutualFixedBuyConditionHandler.class,
            "mutualFixedBuyConditionConfirm");
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualFixedBuyConditionCompleteRequest.class,
            WEB3MutualFixedBuyConditionHandler.class,
            "mutualFixedBuyConditionComplete");

        //------------------------------------
        // RAC-CTXインタセプタを設定する 
        //------------------------------------
        // 投信注文受付一件
        Services.addInterceptor(
            WEB3MutualOrderAcceptUnitService.class,
            new WEB3MutualFundDescendRacCtxInterceptor());
            
        // 投信取消受付一件
        Services.addInterceptor(
            WEB3MutualCancelAcceptUnitService.class,
            new WEB3MutualFundDescendRacCtxInterceptor());
            
        // 投信売買注文通知一件
        Services.addInterceptor(
            WEB3MutualTradeOrderNotifyUnitService.class,
            new WEB3MutualFundDescendRacCtxInterceptor());

        log.exiting(METHOD_NAME);

    }
}

@

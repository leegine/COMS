head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-Equity プラグインクラス(WEB3EquityAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/19 髙橋　@良和(SRA) 新規作成
                   2004/04/01 鄒政 (中訊) 追加
                   2004/12/16 中尾寿彦(SRA) 残案件対応による修正
                   2004/12/29 岡村和明(SRA) コメントの修正
                   2005/01/06 岡村和明(SRA) JavaDoc修正
Revesion History : 2007/12/24 肖志偉(中訊) PTS仕様変更
Revesion History : 2008/01/29 趙林鵬(中訊) モデル1285,1286
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.equity.data.WEB3EquityMarketSessionDatabaseExtensions;
import webbroker3.equity.data.WEB3EquityMasterDatabaseExtensions;
import webbroker3.equity.handler.WEB3EquityAssetInquiryHandler;
import webbroker3.equity.handler.WEB3EquityBalanceReferenceHandler;
import webbroker3.equity.handler.WEB3EquityBookValuePriceRegistHandler;
import webbroker3.equity.handler.WEB3EquityCancelOrderHandler;
import webbroker3.equity.handler.WEB3EquityChangeCancelAcceptHandler;
import webbroker3.equity.handler.WEB3EquityChangeCancelNotifyMainHandler;
import webbroker3.equity.handler.WEB3EquityChangeOrderHandler;
import webbroker3.equity.handler.WEB3EquityChangeOrderInputHandler;
import webbroker3.equity.handler.WEB3EquityExecuteReferenceHandler;
import webbroker3.equity.handler.WEB3EquityMarginExecuteReferenceHandler;
import webbroker3.equity.handler.WEB3EquityOffFloorProductListHandler;
import webbroker3.equity.handler.WEB3EquityOrderAcceptHandler;
import webbroker3.equity.handler.WEB3EquityOrderBuyInputHandler;
import webbroker3.equity.handler.WEB3EquityOrderCarryOverHandler;
import webbroker3.equity.handler.WEB3EquityOrderCarryOverSkipHandler;
import webbroker3.equity.handler.WEB3EquityOrderExecEndNotifyHandler;
import webbroker3.equity.handler.WEB3EquityOrderExecNotifyMainHandler;
import webbroker3.equity.handler.WEB3EquityOrderHandler;
import webbroker3.equity.handler.WEB3EquityOrderNotifyHandler;
import webbroker3.equity.handler.WEB3EquityPTSExecEndNotifyHandler;
import webbroker3.equity.handler.WEB3EquityProductInformationHandler;
import webbroker3.equity.handler.WEB3EquityReceiveCloseOrderHandler;
import webbroker3.equity.handler.WEB3EquitySellOrderInputHandler;
import webbroker3.equity.handler.WEB3MarginBalanceReferenceHandler;
import webbroker3.equity.handler.WEB3MarginCancelHandler;
import webbroker3.equity.handler.WEB3MarginChangeCloseMarginHandler;
import webbroker3.equity.handler.WEB3MarginChangeCloseMarginInputHandler;
import webbroker3.equity.handler.WEB3MarginChangeOpenMarginHandler;
import webbroker3.equity.handler.WEB3MarginChangeOpenMarginInputHandler;
import webbroker3.equity.handler.WEB3MarginCloseMarginHandler;
import webbroker3.equity.handler.WEB3MarginCloseMarginInputServiceHandler;
import webbroker3.equity.handler.WEB3MarginCloseMarginListHandler;
import webbroker3.equity.handler.WEB3MarginContractReferenceHandler;
import webbroker3.equity.handler.WEB3MarginExecuteReferenceHandler;
import webbroker3.equity.handler.WEB3MarginOpenMarginHandler;
import webbroker3.equity.handler.WEB3MarginOpenMarginInputHandler;
import webbroker3.equity.handler.WEB3MarginOrderNotifyHandler;
import webbroker3.equity.handler.WEB3MarginSwapMarginAcceptHandler;
import webbroker3.equity.handler.WEB3MarginSwapMarginHandler;
import webbroker3.equity.handler.WEB3MarginSwapMarginInputHandler;
import webbroker3.equity.handler.WEB3MarginSwapOrderNotifyHandler;
import webbroker3.equity.handler.WEB3MstkBalanceReferenceHandler;
import webbroker3.equity.handler.WEB3MstkBookValuePriceRegistHandler;
import webbroker3.equity.handler.WEB3MstkBuyHandler;
import webbroker3.equity.handler.WEB3MstkCancelHandler;
import webbroker3.equity.handler.WEB3MstkExecuteReferenceHandler;
import webbroker3.equity.handler.WEB3MstkSellHandler;
import webbroker3.equity.marketadaptor.WEB3EquityMarketRequestSenderServiceImpl;
import webbroker3.equity.message.*;
import webbroker3.equity.service.delegate.WEB3EquityAssetInquiryService;
import webbroker3.equity.service.delegate.WEB3EquityBalanceReferenceService;
import webbroker3.equity.service.delegate.WEB3EquityBookValuePriceRegistService;
import webbroker3.equity.service.delegate.WEB3EquityCancelOrderService;
import webbroker3.equity.service.delegate.WEB3EquityChangeCancelAcceptService;
import webbroker3.equity.service.delegate.WEB3EquityChangeCancelAcceptUnitService;
import webbroker3.equity.service.delegate.WEB3EquityChangeCancelNotifyMainService;
import webbroker3.equity.service.delegate.WEB3EquityChangeOrderInputService;
import webbroker3.equity.service.delegate.WEB3EquityChangeOrderService;
import webbroker3.equity.service.delegate.WEB3EquityExecuteReferenceService;
import webbroker3.equity.service.delegate.WEB3EquityExecutedMailSenderService;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.equity.service.delegate.WEB3EquityMarginExecuteReferenceService;
import webbroker3.equity.service.delegate.WEB3EquityOffFloorProductListService;
import webbroker3.equity.service.delegate.WEB3EquityOrderAcceptService;
import webbroker3.equity.service.delegate.WEB3EquityOrderAcceptUnitService;
import webbroker3.equity.service.delegate.WEB3EquityOrderBuyInputService;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverService;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverSkipObjectCheckService;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverSkipService;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverSkipUnitService;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverUnitService;
import webbroker3.equity.service.delegate.WEB3EquityOrderExecEndNotifyService;
import webbroker3.equity.service.delegate.WEB3EquityOrderExecNotifyMainService;
import webbroker3.equity.service.delegate.WEB3EquityOrderExecNotifyUnitService;
import webbroker3.equity.service.delegate.WEB3EquityOrderNotifyService;
import webbroker3.equity.service.delegate.WEB3EquityOrderNotifyUnitService;
import webbroker3.equity.service.delegate.WEB3EquityOrderService;
import webbroker3.equity.service.delegate.WEB3EquityPTSCancelOrderService;
import webbroker3.equity.service.delegate.WEB3EquityPTSChangeOrderInputService;
import webbroker3.equity.service.delegate.WEB3EquityPTSChangeOrderService;
import webbroker3.equity.service.delegate.WEB3EquityPTSExecEndNotifyService;
import webbroker3.equity.service.delegate.WEB3EquityPTSOrderService;
import webbroker3.equity.service.delegate.WEB3EquityProductInformationService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCancelEventService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveChangeEventService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderUnitService;
import webbroker3.equity.service.delegate.WEB3EquitySellOrderInputService;
import webbroker3.equity.service.delegate.WEB3MarginBalanceReferenceService;
import webbroker3.equity.service.delegate.WEB3MarginCancelService;
import webbroker3.equity.service.delegate.WEB3MarginChangeCancelNotifyCancelUnitService;
import webbroker3.equity.service.delegate.WEB3MarginChangeCancelNotifyChangeUnitService;
import webbroker3.equity.service.delegate.WEB3MarginChangeCloseMarginInputService;
import webbroker3.equity.service.delegate.WEB3MarginChangeCloseMarginService;
import webbroker3.equity.service.delegate.WEB3MarginChangeOpenMarginInputService;
import webbroker3.equity.service.delegate.WEB3MarginChangeOpenMarginService;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginInputService;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginListService;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginService;
import webbroker3.equity.service.delegate.WEB3MarginContractReferenceService;
import webbroker3.equity.service.delegate.WEB3MarginExecuteReferenceService;
import webbroker3.equity.service.delegate.WEB3MarginOpenMarginInputService;
import webbroker3.equity.service.delegate.WEB3MarginOpenMarginService;
import webbroker3.equity.service.delegate.WEB3MarginOrderExecNotifyUnitService;
import webbroker3.equity.service.delegate.WEB3MarginOrderNotifyService;
import webbroker3.equity.service.delegate.WEB3MarginOrderNotifyUnitService;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginAcceptService;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginAcceptUnitService;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginInputService;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginService;
import webbroker3.equity.service.delegate.WEB3MarginSwapOrderNotifyService;
import webbroker3.equity.service.delegate.WEB3MarginSwapOrderNotifyUnitService;
import webbroker3.equity.service.delegate.WEB3MstkBalanceReferenceService;
import webbroker3.equity.service.delegate.WEB3MstkBookValuePriceRegistService;
import webbroker3.equity.service.delegate.WEB3MstkBuyService;
import webbroker3.equity.service.delegate.WEB3MstkCancelService;
import webbroker3.equity.service.delegate.WEB3MstkExecuteReferenceService;
import webbroker3.equity.service.delegate.WEB3MstkSellService;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityAssetInquiryServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityBalanceReferenceServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityBookValuePriceRegistServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityCancelOrderServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityChangeCancelAcceptServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityChangeCancelAcceptUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityChangeCancelNotifyMainServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityChangeOrderInputServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityChangeOrderServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityExecuteReferenceServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityExecutedMailSenderServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOffFloorProductListServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderAcceptServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderAcceptUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderBuyInputServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderCarryOverServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderCarryOverSkipObjectCheckServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderCarryOverSkipServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderCarryOverSkipUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderCarryOverUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderExecEndNotifyServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderExecNotifyMainServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderExecNotifyUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderNotifyServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderNotifyUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSCancelOrderServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSChangeOrderInputServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSChangeOrderServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSExecEndNotifyServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSOrderServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityProductInformationServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCancelEventServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveChangeEventServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquitySellOrderInputServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginBalanceReferenceServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginCancelServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginChangeCancelNotifyCancelUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginChangeCancelNotifyChangeUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginChangeCloseMarginInputServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginChangeCloseMarginServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginChangeOpenMarginInputServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginChangeOpenMarginServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginCloseMarginInputServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginCloseMarginListServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginCloseMarginServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginContractReferenceServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginExecuteReferenceServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginOpenMarginInputServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginOpenMarginServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginOrderExecNotifyUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginOrderNotifyServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginOrderNotifyUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapMarginAcceptServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapMarginAcceptUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapMarginInputServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapMarginServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapOrderNotifyServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapOrderNotifyUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MstkBalanceReferenceServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MstkBookValuePriceRegistServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MstkBuyServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MstkCancelServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MstkExecuteReferenceServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MstkSellServiceImpl;
import webbroker3.mqgateway.WEB3MQGatewayInterceptor;
import webbroker3.rlsgateway.WEB3RlsGatewayInterceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * （Webbroker3-Equity プラグインクラス）。
 * @@author 髙橋　@良和(SRA)
 * @@version 1.0
 */
public final class WEB3EquityAppPlugin extends Plugin {
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3EquityAppPlugin()
    {
    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);
        plug(WEB3EquityAppPlugin.class);
        log.exiting(METHOD_NAME);
    }

    /**
     * プラグイン処理。
     */
    public static void onPlug() throws Exception {
        String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);

        // このプラグインより先に読み込む必要のあるプラグインの指定。
        // install the system plugins that we need
        KernelPlugin.plug();

        // DatabaseExtensions のプラグイン処理 ----------------------
        // WEB3EquityMasterDatabaseExtensions をプラグイン
        WEB3EquityMasterDatabaseExtensions.plug();
        // WEB3EquityMarketSessionDatabaseExtensions をプラグイン
        WEB3EquityMarketSessionDatabaseExtensions.plug();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        // 拡張トランザクション・マネージャーは
        // オーバーライドメソッドが無いため拡張取引モジュールクラスを作成し
        // 拡張取引モジュールクラス内で設定
        l_finApp.uninstallTradingModule("eqtype");
        l_finApp.installTradingModule(new WEB3EquityTradingModule());
        TradingModule l_tradingModule = l_finApp.getTradingModule("eqtype");

        // Adapterの登録 ----------------------------------------
        // 市場リクエスト送信サービス
        l_tradingModule.getMarketAdapter().installMarketRequestSenderService(
            new WEB3EquityMarketRequestSenderServiceImpl());

        // 拡張プロダクト・マネージャー
        l_tradingModule.overrideProductManager(new WEB3EquityProductManager());

        // 計算サービスクラス
        l_tradingModule.overrideBizLogicProvider(
            new WEB3EquityBizLogicProvider());

        // PTS注文マネージャ
        l_tradingModule.overrideOrderManager(
            new WEB3EquityPTSOrderManager());

        // ポジションマネージャ
        l_tradingModule.overridePositionManager(
            new WEB3EquityPositionManager());

        // PTS発注審査個別チェック
        new WEB3EquityPTSOrderManagerReusableValidations().register();


        /*
         * 現物株式
         */
        // Service の登録
        // 1) 売付一覧サービス
        Services.registerService(
            WEB3EquityAssetInquiryService.class,
            new WEB3EquityAssetInquiryServiceImpl());

        // 2) 株式注文取消サービス
        Services.registerService(
            WEB3EquityCancelOrderService.class,
            new WEB3EquityCancelOrderServiceImpl());

        // 3) 株式訂正取消受付サービス
        Services.registerService(
            WEB3EquityChangeCancelAcceptService.class,
            new WEB3EquityChangeCancelAcceptServiceImpl());

        // 4) 株式注文訂正入力サービス
        Services.registerService(
            WEB3EquityChangeOrderInputService.class,
            new WEB3EquityChangeOrderInputServiceImpl());

        // 5) 株式注文訂正サービス
        Services.registerService(
            WEB3EquityChangeOrderService.class,
            new WEB3EquityChangeOrderServiceImpl());

        // 6) 現物株式注文約定照会サービス
        Services.registerService(
            WEB3EquityExecuteReferenceService.class,
            new WEB3EquityExecuteReferenceServiceImpl());

        // 7) 株式注文受付サービス
        Services.registerService(
            WEB3EquityOrderAcceptService.class,
            new WEB3EquityOrderAcceptServiceImpl());

        // 8) 株式注文買付入力サービス
        Services.registerService(
            WEB3EquityOrderBuyInputService.class,
            new WEB3EquityOrderBuyInputServiceImpl());

        // 9) 注文繰越一件サービス
        Services.registerService(
            WEB3EquityOrderCarryOverUnitService.class,
            new WEB3EquityOrderCarryOverUnitServiceImpl());

        // 10) 注文繰越サービス
        Services.registerService(
            WEB3EquityOrderCarryOverService.class,
            new WEB3EquityOrderCarryOverServiceImpl());

        // 11) 株式注文繰越スキップ銘柄通知繰越対象チェックサービス
        Services.registerService(
            WEB3EquityOrderCarryOverSkipObjectCheckService.class,
            new WEB3EquityOrderCarryOverSkipObjectCheckServiceImpl());

        // 12) 株式注文繰越スキップ銘柄通知一件サービス
        Services.registerService(
            WEB3EquityOrderCarryOverSkipUnitService.class,
            new WEB3EquityOrderCarryOverSkipUnitServiceImpl());

        // 13) 株式注文繰越スキップ銘柄通知サービス
        Services.registerService(
            WEB3EquityOrderCarryOverSkipService.class,
            new WEB3EquityOrderCarryOverSkipServiceImpl());

        // 14) 出来終了通知サービス
        Services.registerService(
            WEB3EquityOrderExecEndNotifyService.class,
            new WEB3EquityOrderExecEndNotifyServiceImpl());

        // 15) 現物株式出来通知一件サービス
        Services.registerService(
            WEB3EquityOrderExecNotifyUnitService.class,
            new WEB3EquityOrderExecNotifyUnitServiceImpl());

        // 16) 株式出来通知メインサービス
        Services.registerService(
            WEB3EquityOrderExecNotifyMainService.class,
            new WEB3EquityOrderExecNotifyMainServiceImpl());

        // 17) 株式注文通知一件サービス
        Services.registerService(
            WEB3EquityOrderNotifyUnitService.class,
            new WEB3EquityOrderNotifyUnitServiceImpl());

        // 18) 株式注文通知サービス
        Services.registerService(
            WEB3EquityOrderNotifyService.class,
            new WEB3EquityOrderNotifyServiceImpl());

        // 19) 株式注文サービス
        Services.registerService(
            WEB3EquityOrderService.class,
            new WEB3EquityOrderServiceImpl());

        // 20) 現物株式訂正取消通知取消一件サービス
        Services.registerService(
            WEB3EquityReceiveCancelEventService.class,
            new WEB3EquityReceiveCancelEventServiceImpl());

        // 21) 株式訂正取消通知メインサービス
        Services.registerService(
            WEB3EquityChangeCancelNotifyMainService.class,
            new WEB3EquityChangeCancelNotifyMainServiceImpl());

        // 22) 現物株式訂正取消通知訂正一件サービス
        Services.registerService(
            WEB3EquityReceiveChangeEventService.class,
            new WEB3EquityReceiveChangeEventServiceImpl());

        // 23) 株式失効通知一件サービス
        Services.registerService(
            WEB3EquityReceiveCloseOrderUnitService.class,
            new WEB3EquityReceiveCloseOrderUnitServiceImpl());

        // 24) 株式失効通知サービス
        Services.registerService(
            WEB3EquityReceiveCloseOrderService.class,
            new WEB3EquityReceiveCloseOrderServiceImpl());

        // 25) 現物株式売付注文入力サービス
        Services.registerService(
            WEB3EquitySellOrderInputService.class,
            new WEB3EquitySellOrderInputServiceImpl());

        // 26) 約定メール送信サービス
        Services.registerService(
            WEB3EquityExecutedMailSenderService.class,
            new WEB3EquityExecutedMailSenderServiceImpl());

        // 27) 株式銘柄情報表示サービス
        Services.registerService(
            WEB3EquityProductInformationService.class,
            new WEB3EquityProductInformationServiceImpl());

        // 28) 立会外分売銘柄一覧サービス
        Services.registerService(
            WEB3EquityOffFloorProductListService.class,
            new WEB3EquityOffFloorProductListServiceImpl());

        // 29) 現物株式残高照会サービス
        Services.registerService(
            WEB3EquityBalanceReferenceService.class,
            new WEB3EquityBalanceReferenceServiceImpl());

        // 30) 現物株式簿価単価登録サービス
        Services.registerService(
            WEB3EquityBookValuePriceRegistService.class,
            new WEB3EquityBookValuePriceRegistServiceImpl());

        // 31) 株式注文受付一件サービス
        Services.registerService(
            WEB3EquityOrderAcceptUnitService.class,
            new WEB3EquityOrderAcceptUnitServiceImpl());

        // 32) 株式訂正取消受付一件サービス
        Services.registerService(
            WEB3EquityChangeCancelAcceptUnitService.class,
            new WEB3EquityChangeCancelAcceptUnitServiceImpl());

        // 33) 株式発注サービス
        Services.registerService(
            WEB3EquityFrontOrderService.class,
            new WEB3EquityFrontOrderServiceImpl());

        // 34) 株式・信用注文約定照会サービス
        Services.registerService(
        	WEB3EquityMarginExecuteReferenceService.class,
            new WEB3EquityMarginExecuteReferenceServiceImpl());

        // 35) (PTS)現物株式注文サービス
        Services.registerService(
            WEB3EquityPTSOrderService.class,
            new WEB3EquityPTSOrderServiceImpl());

        // 36) (PTS)現物株式注文取消サービス
        Services.registerService(
            WEB3EquityPTSCancelOrderService.class,
            new WEB3EquityPTSCancelOrderServiceImpl());

        // 37) (PTS)現物株式注文訂正サービス
        Services.registerService(
            WEB3EquityPTSChangeOrderService.class,
            new WEB3EquityPTSChangeOrderServiceImpl());

        // 38) (PTS)現物株式注文訂正入力サービス
        Services.registerService(
            WEB3EquityPTSChangeOrderInputService.class,
            new WEB3EquityPTSChangeOrderInputServiceImpl());

        // 39) (PTS)株式出来終了通知サービス
        Services.registerService(
            WEB3EquityPTSExecEndNotifyService.class,
            new WEB3EquityPTSExecEndNotifyServiceImpl());

        // Serviceインタセプタの設定
        // 1) 売付一覧サービスインタセプタ
        Services.addInterceptor(
            WEB3EquityAssetInquiryService.class,
            new WEB3EquityAssetInquiryServiceInterceptor());

        // 2) 株式注文取消サービスインタセプタ
        Services.addInterceptor(
            WEB3EquityCancelOrderService.class,
            new WEB3EquityCancelOrderServiceInterceptor());

        // 3 現物株式注文訂正入力サービスインタセプタ
        Services.addInterceptor(
            WEB3EquityChangeOrderInputService.class,
            new WEB3EquityChangeOrderInputServiceInterceptor());

        // 4) 株式注文訂正サービスインタセプタ
        Services.addInterceptor(
            WEB3EquityChangeOrderService.class,
            new WEB3EquityChangeOrderServiceInterceptor());

        // 5) 現物株式注文約定照会サービスインタセプタ
        Services.addInterceptor(
            WEB3EquityExecuteReferenceService.class,
            new WEB3EquityExecuteReferenceServiceInterceptor());

        // 6) 現物株式買付注文入力サービスインタセプタ
        Services.addInterceptor(
            WEB3EquityOrderBuyInputService.class,
            new WEB3EquityOrderBuyInputServiceInterceptor());

        // 7) 注文繰越一件サービスインタセプタ
        Services.addInterceptor(
            WEB3EquityOrderCarryOverUnitService.class,
            new WEB3EquityOrderCarryOverUnitServiceInterceptor());

        // 8) 株式注文繰越スキップ銘柄通知繰越対象チェックサービスインタセプタ
        Services.addInterceptor(
            WEB3EquityOrderCarryOverSkipObjectCheckService.class,
            new WEB3EquityOrderCarryOverSkipObjectCheckServiceInterceptor());

        // 9) 現物株式出来通知一件サービスインタセプタ
        Services.addInterceptor(
            WEB3EquityOrderExecNotifyUnitService.class,
            new WEB3EquityOrderExecNotifyUnitServiceInterceptor());

        // 10) 株式注文通知一件サービスインタセプタ
        Services.addInterceptor(
            WEB3EquityOrderNotifyUnitService.class,
            new WEB3EquityOrderNotifyPartServiceInterceptor());

        // 11) 株式注文サービスインタセプタ
        Services.addInterceptor(
            WEB3EquityOrderService.class,
            new WEB3EquityOrderServiceInterceptor());

        // 12) 現物株式訂正取消通知取消一件サービスインタセプタ
        Services.addInterceptor(
            WEB3EquityReceiveCancelEventService.class,
            new WEB3EquityReceiveCancelEventServiceInterceptor());

        // 13) 現物株式訂正取消通知訂正一件サービスインタセプタ
        Services.addInterceptor(
            WEB3EquityReceiveChangeEventService.class,
            new WEB3EquityReceiveChangeEventServiceInterceptor());

        // 14) 現物株式売付注文入力サービスインタセプタ
        Services.addInterceptor(
            WEB3EquitySellOrderInputService.class,
            new WEB3EquitySellOrderInputServiceInterceptor());

        // 15) 株式失効通知一件サービスインタセプタ
        Services.addInterceptor(
            WEB3EquityReceiveCloseOrderUnitService.class,
            new WEB3EquityReceiveCloseOrderUnitServiceInterceptor());

        // 16) 株式銘柄情報表示サービスインタセプタ
        Services.addInterceptor(
            WEB3EquityProductInformationService.class,
            new WEB3EquityProductInformationServiceInterceptor());

        // 17) 立会外分売銘柄一覧サービスインタセプタ
        Services.addInterceptor(
            WEB3EquityOffFloorProductListService.class,
            new WEB3EquityOffFloorProductListServiceInterceptor());

        // 18) 現物株式残高照会サービスインタセプタ
        Services.addInterceptor(
            WEB3EquityBalanceReferenceService.class,
            new WEB3EquityBalanceReferenceServiceInterceptor());

        // 19) 現物株式簿価単価登録サービスインタセプタ
        Services.addInterceptor(
            WEB3EquityBookValuePriceRegistService.class,
            new WEB3EquityBookValuePriceRegistServiceInterceptor());

        // 20) 株式注文受付一件サービスインタセプタ
        Services.addInterceptor(
            WEB3EquityOrderAcceptUnitService.class,
            new WEB3EquityOrderAcceptUnitServiceInterceptor());

        // 21) 訂正取消受付一件サービスインタセプタ
        Services.addInterceptor(
            WEB3EquityChangeCancelAcceptUnitService.class,
            new WEB3EquityChangeCancelAcceptUnitServiceInterceptor());

        // 22) 株式・信用注文約定照会サービスインタセプタ
        Services.addInterceptor(
        	WEB3EquityMarginExecuteReferenceService.class,
            new WEB3EquityMarginExecuteReferenceServiceInterceptor());

        // 23) (PTS)現物株式注文サービス
        Services.addInterceptor(
            WEB3EquityPTSOrderService.class,
            new WEB3EquityPTSOrderServiceInterceptor());

        // 24) (PTS)現物株式注文取消サービス
        Services.addInterceptor(
            WEB3EquityPTSCancelOrderService.class,
            new WEB3EquityPTSCancelOrderServiceInterceptor());

        // 25) (PTS)現物株式注文訂正サービス
        Services.addInterceptor(
            WEB3EquityPTSChangeOrderService.class,
            new WEB3EquityPTSChangeOrderServiceInterceptor());

        // 26) (PTS)現物株式注文訂正入力サービス
        Services.addInterceptor(
            WEB3EquityPTSChangeOrderInputService.class,
            new WEB3EquityPTSChangeOrderInputServiceInterceptor());

        // 自動トランザクション設定
        // 1) 注文繰越一件サービス
        Services.addInterceptor(
            WEB3EquityOrderCarryOverUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 2) 株式注文繰越スキップ銘柄通知一件サービス
        Services.addInterceptor(
            WEB3EquityOrderCarryOverSkipUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 3) 株式失効通知一件サービス
        Services.addInterceptor(
            WEB3EquityReceiveCloseOrderUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 4) 現物株式出来通知一件サービス
        Services.addInterceptor(
            WEB3EquityOrderExecNotifyUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 5) 株式注文通知一件サービス
        Services.addInterceptor(
            WEB3EquityOrderNotifyUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 6) 現物株式訂正取消通知取消一件サービス
        Services.addInterceptor(
            WEB3EquityReceiveCancelEventService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 7) 現物株式訂正取消通知訂正一件サービス
        Services.addInterceptor(
            WEB3EquityReceiveChangeEventService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 8) 株式注文サービス
        Services.addInterceptor(
            WEB3EquityOrderService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 9) 株式注文受付サービス
        Services.addInterceptor(
            WEB3EquityOrderAcceptService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 10) 株式注文訂正サービス
        Services.addInterceptor(
            WEB3EquityChangeOrderService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 11) 株式注文取消サービス
        Services.addInterceptor(
            WEB3EquityCancelOrderService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 12) 株式訂正取消受付サービス
        Services.addInterceptor(
            WEB3EquityChangeCancelAcceptService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 13) 株式訂正取消通知メインサービス
        Services.addInterceptor(
            WEB3EquityChangeCancelNotifyMainService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 14) 約定メール送信サービス
        Services.addInterceptor(
            WEB3EquityExecutedMailSenderService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 15) 注文繰越サービス
        Services.addInterceptor(
            WEB3EquityOrderCarryOverService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 16) 株式注文繰越スキップ銘柄通知繰越対象チェックサービス
        Services.addInterceptor(
            WEB3EquityOrderCarryOverSkipObjectCheckService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 17) 株式注文繰越スキップ銘柄通知サービス
        Services.addInterceptor(
            WEB3EquityOrderCarryOverSkipService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 18) 出来終了通知サービス
        Services.addInterceptor(
            WEB3EquityOrderExecEndNotifyService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 19) 株式出来通知メインサービス
        Services.addInterceptor(
            WEB3EquityOrderExecNotifyMainService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 20) 株式注文通知サービス
        Services.addInterceptor(
            WEB3EquityOrderNotifyService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 21) 株式失効通知サービス
        Services.addInterceptor(
            WEB3EquityReceiveCloseOrderService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 22) 現物株式簿価単価登録サービス
        Services.addInterceptor(
            WEB3EquityBookValuePriceRegistService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 23) 株式注文受付一件サービス
        Services.addInterceptor(
            WEB3EquityOrderAcceptUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 24) 株式訂正取消受付一件サービス
        Services.addInterceptor(
            WEB3EquityChangeCancelAcceptUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 25) 株式・信用注文約定照会サービス
        Services.addInterceptor(
        		WEB3EquityMarginExecuteReferenceService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 23) (PTS)現物株式注文サービス
        Services.addInterceptor(
            WEB3EquityPTSOrderService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 24) (PTS)現物株式注文取消サービス
        Services.addInterceptor(
            WEB3EquityPTSCancelOrderService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 25) (PTS)現物株式注文訂正サービス
        Services.addInterceptor(
            WEB3EquityPTSChangeOrderService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));
        
        // 26) (PTS)株式出来終了通知サービス
        Services.addInterceptor(
            WEB3EquityPTSExecEndNotifyService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // ルールエンジンサーバ・インタセプタの設定
        // 1) 株式注文取消サービス
        Services.addInterceptor(
            WEB3EquityCancelOrderService.class,
            new WEB3RlsGatewayInterceptor());

        // 2) 株式注文訂正サービス
        Services.addInterceptor(
            WEB3EquityChangeOrderService.class,
            new WEB3RlsGatewayInterceptor());

        // 3) 注文繰越一件サービス
        Services.addInterceptor(
            WEB3EquityOrderCarryOverUnitService.class,
            new WEB3RlsGatewayInterceptor());

        // 4) 株式注文サービス
        Services.addInterceptor(
            WEB3EquityOrderService.class,
            new WEB3RlsGatewayInterceptor());

        // 5) 株式出来通知一件サービス
        Services.addInterceptor(
            WEB3EquityOrderExecNotifyUnitService.class,
            new WEB3RlsGatewayInterceptor());

        // 6) 株式訂正取消通知訂正一件サービス
        Services.addInterceptor(
            WEB3EquityReceiveChangeEventService.class,
            new WEB3RlsGatewayInterceptor());

        // MQ-Gatewayインタセプタの設定
        // 1) 株式注文取消サービス
        Services.addInterceptor(
            WEB3EquityCancelOrderService.class,
            new WEB3MQGatewayInterceptor());

        // 2) 株式注文訂正サービス
        Services.addInterceptor(
            WEB3EquityChangeOrderService.class,
            new WEB3MQGatewayInterceptor());

        // 3) 注文繰越一件サービス
        Services.addInterceptor(
            WEB3EquityOrderCarryOverUnitService.class,
            new WEB3MQGatewayInterceptor());

        // 4) 株式注文サービス
        Services.addInterceptor(
            WEB3EquityOrderService.class,
            new WEB3MQGatewayInterceptor());

        // 処理時間ログ出力インタセプタの設定
        // 1) 売付一覧サービス
        Services.addInterceptor(
            WEB3EquityAssetInquiryService.class,
            new WEB3LogSysTimeInterceptor());

        // 2) 株式注文取消サービス
        Services.addInterceptor(
            WEB3EquityCancelOrderService.class,
            new WEB3LogSysTimeInterceptor());

        // 3) 株式訂正取消受付サービス
        Services.addInterceptor(
            WEB3EquityChangeCancelAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        // 4) 株式注文訂正入力サービス
        Services.addInterceptor(
            WEB3EquityChangeOrderInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 5) 株式注文訂正サービス
        Services.addInterceptor(
            WEB3EquityChangeOrderService.class,
            new WEB3LogSysTimeInterceptor());

        // 6) 現物株式注文約定照会サービス
        Services.addInterceptor(
            WEB3EquityExecuteReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // 7) 株式注文受付サービス
        Services.addInterceptor(
            WEB3EquityOrderAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        // 8) 株式注文買付入力サービス
        Services.addInterceptor(
            WEB3EquityOrderBuyInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 9) 注文繰越一件サービス
        Services.addInterceptor(
            WEB3EquityOrderCarryOverUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 10) 注文繰越サービス
        Services.addInterceptor(
            WEB3EquityOrderCarryOverService.class,
            new WEB3LogSysTimeInterceptor());

        // 11) 株式注文繰越スキップ銘柄通知繰越対象チェックサービス
        Services.addInterceptor(
            WEB3EquityOrderCarryOverSkipObjectCheckService.class,
            new WEB3LogSysTimeInterceptor());

        // 12) 株式注文繰越スキップ銘柄通知一件サービス
        Services.addInterceptor(
            WEB3EquityOrderCarryOverSkipUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 13) 株式注文繰越スキップ銘柄通知サービス
        Services.addInterceptor(
            WEB3EquityOrderCarryOverSkipService.class,
            new WEB3LogSysTimeInterceptor());

        // 14) 出来終了通知サービス
        Services.addInterceptor(
            WEB3EquityOrderExecEndNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        // 15) 現物株式出来通知一件サービス
        Services.addInterceptor(
            WEB3EquityOrderExecNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 16) 株式出来通知メインサービス
        Services.addInterceptor(
            WEB3EquityOrderExecNotifyMainService.class,
            new WEB3LogSysTimeInterceptor());

        // 17) 株式注文通知一件サービス
        Services.addInterceptor(
            WEB3EquityOrderNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 18) 株式注文通知サービス
        Services.addInterceptor(
            WEB3EquityOrderNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        // 19) 株式注文サービス
        Services.addInterceptor(
            WEB3EquityOrderService.class,
            new WEB3LogSysTimeInterceptor());

        // 20) 現物株式訂正取消通知取消一件サービス
        Services.addInterceptor(
            WEB3EquityReceiveCancelEventService.class,
            new WEB3LogSysTimeInterceptor());

        // 21) 株式訂正取消通知メインサービス
        Services.addInterceptor(
            WEB3EquityChangeCancelNotifyMainService.class,
            new WEB3LogSysTimeInterceptor());

        // 22) 現物株式訂正取消通知訂正一件サービス
        Services.addInterceptor(
            WEB3EquityReceiveChangeEventService.class,
            new WEB3LogSysTimeInterceptor());

        // 23) 株式失効通知一件サービス
        Services.addInterceptor(
            WEB3EquityReceiveCloseOrderUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 24) 株式失効通知サービス
        Services.addInterceptor(
            WEB3EquityReceiveCloseOrderService.class,
            new WEB3LogSysTimeInterceptor());

        // 25) 現物株式売付注文入力サービス
        Services.addInterceptor(
            WEB3EquitySellOrderInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 26) 株式銘柄情報表示サービス
        Services.addInterceptor(
            WEB3EquityProductInformationService.class,
            new WEB3LogSysTimeInterceptor());

        // 27) 立会外分売銘柄一覧サービス
        Services.addInterceptor(
            WEB3EquityOffFloorProductListService.class,
            new WEB3LogSysTimeInterceptor());

        // 28) 現物株式残高照会サービス
        Services.addInterceptor(
            WEB3EquityBalanceReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // 29) 現物株式簿価単価登録サービス
        Services.addInterceptor(
            WEB3EquityBookValuePriceRegistService.class,
            new WEB3LogSysTimeInterceptor());

        // 30) 株式注文受付一件サービス
        Services.addInterceptor(
            WEB3EquityOrderAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 31) 株式訂正取消受付一件サービス
        Services.addInterceptor(
            WEB3EquityChangeCancelAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 32) 株式・信用注文約定照会サービ
        Services.addInterceptor(
        		WEB3EquityMarginExecuteReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // 33) (PTS)現物株式注文取消サービス
        Services.addInterceptor(
            WEB3EquityPTSCancelOrderService.class,
            new WEB3LogSysTimeInterceptor());

        // 34) (PTS)現物株式注文訂正サービス
        Services.addInterceptor(
            WEB3EquityPTSChangeOrderService.class,
            new WEB3LogSysTimeInterceptor());

        // 35) (PTS)現物株式注文訂正入力サービス
        Services.addInterceptor(
            WEB3EquityPTSChangeOrderInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 36) (PTS)株式出来終了通知サービス
        Services.addInterceptor(
            WEB3EquityPTSExecEndNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        // Message の登録
        // 1-1) 現物株式買付注文完了リクエスト
        regClass(WEB3EquityBuyCompleteRequest.class);
        // 1-2) 現物株式買付注文完了レスポンス
        regClass(WEB3EquityBuyCompleteResponse.class);

        // 2-1) 現物株式買付注文確認リクエスト
        regClass(WEB3EquityBuyConfirmRequest.class);
        // 2-2) 現物株式買付注文確認レスポンス
        regClass(WEB3EquityBuyConfirmResponse.class);

        // 3-1) 現物株式買付注文入力リクエスト
        regClass(WEB3EquityBuyInputRequest.class);
        // 3-2) 現物株式買付注文入力レスポンス
        regClass(WEB3EquityBuyInputResponse.class);

        // 4-1) 現物株式注文取消完了リクエスト
        regClass(WEB3EquityCancelCompleteRequest.class);
        // 4-2) 現物株式注文取消完了レスポンス
        regClass(WEB3EquityCancelCompleteResponse.class);

        // 5-1) 現物株式注文取消確認リクエスト
        regClass(WEB3EquityCancelConfirmRequest.class);
        // 5-2) 現物株式注文取消確認レスポンス
        regClass(WEB3EquityCancelConfirmResponse.class);

        // 6-1) 注文繰越スキップ銘柄通知リクエスト
        regClass(WEB3EquityCarryOverSkipRequest.class);
        // 6-2) 注文繰越スキップ銘柄通知レスポンス
        regClass(WEB3EquityCarryOverSkipResponse.class);

        // 7-1) 株式訂正取消受付リクエスト
        regClass(WEB3EquityChangeCancelAcceptRequest.class);
        // 7-2) 株式訂正取消受付レスポンス
        regClass(WEB3EquityChangeCancelAcceptResponse.class);

        // 8-1) 現物株式注文訂正完了リクエスト
        regClass(WEB3EquityChangeCompleteRequest.class);
        // 8-2) 現物株式注文訂正完了レスポンス
        regClass(WEB3EquityChangeCompleteResponse.class);

        // 9-1) 現物株式注文訂正確認リクエスト
        regClass(WEB3EquityChangeConfirmRequest.class);
        // 9-2) 現物株式注文訂正確認レスポンス
        regClass(WEB3EquityChangeConfirmResponse.class);

        // 10-1) 現物株式注文訂正入力リクエスト
        regClass(WEB3EquityChangeInputRequest.class);
        // 10-2) 現物株式注文訂正入力レスポンス
        regClass(WEB3EquityChangeInputResponse.class);

        // 11-1) 失効リクエスト
        regClass(WEB3EquityCloseOrderRequest.class);
        // 11-2) 失効レスポンス
        regClass(WEB3EquityCloseOrderResponse.class);

        // 12-1) 出来終了通知リクエスト
        regClass(WEB3EquityExecEndNotifyRequest.class);
        // 12-2) 出来終了通知レスポンス
        regClass(WEB3EquityExecEndNotifyResponse.class);

        // 13-1) 株式出来通知メインリクエスト
        regClass(WEB3EquityExecNotifyMainRequest.class);
        // 13-2) 出来通知レスポンス
        regClass(WEB3EquityExecNotifyMainResponse.class);

        // 14-1) 現物株式注文約定詳細リクエスト
        regClass(WEB3EquityExecuteDetailsRequest.class);
        // 14-2) 現物株式注文約定詳細レスポンス
        regClass(WEB3EquityExecuteDetailsResponse.class);

        // 15-1) 現物株式注文約定照会リクエスト
        regClass(WEB3EquityExecuteReferenceRequest.class);
        // 15-2) 現物株式注文約定照会レスポンス
        regClass(WEB3EquityExecuteReferenceResponse.class);

        // 16-1) 株式注文受付リクエスト
        regClass(WEB3EquityOrderAcceptRequest.class);
        // 16-2) 株式注文受付レスポンス
        regClass(WEB3EquityOrderAcceptResponse.class);

        // 17-1) 注文繰越リクエスト
        regClass(WEB3EquityOrderCarryOverRequest.class);
        // 17-2) 注文繰越レスポンス
        regClass(WEB3EquityOrderCarryOverResponse.class);

        // 18-1) 現物株式注文約定履歴リクエスト
        regClass(WEB3EquityOrderHistoryRequest.class);
        // 18-2) 現物株式注文約定履歴レスポンス
        regClass(WEB3EquityOrderHistoryResponse.class);

        // 19-1) 現物株式注文通知リクエスト
        regClass(WEB3EquityOrderNotifyRequest.class);
        // 19-2) 現物株式注文通知レスポンス
        regClass(WEB3EquityOrderNotifyResponse.class);

        // 20-1) 訂正取消通知メインリクエスト
        regClass(WEB3EquityChangeCancelNotifyMainRequest.class);
        // 20-2) 訂正取消通知レスポンス
        regClass(WEB3EquityChangeCancelNotifyMainResponse.class);

        // 21-1) 現物株式売付注文完了リクエスト
        regClass(WEB3EquitySellCompleteRequest.class);
        // 21-2) 現物株式売付注文完了レスポンス
        regClass(WEB3EquitySellCompleteResponse.class);

        // 22-1) 現物株式売付注文確認リクエスト
        regClass(WEB3EquitySellConfirmRequest.class);
        // 22-2) 現物株式売付注文確認レスポンス
        regClass(WEB3EquitySellConfirmResponse.class);

        // 23-1) 現物株式売付注文入力リクエスト
        regClass(WEB3EquitySellInputRequest.class);
        // 23-2) 現物株式売付注文入力レスポンス
        regClass(WEB3EquitySellInputResponse.class);

        // 24-1) 現物株式売付一覧リクエスト
        regClass(WEB3EquitySellListRequest.class);
        // 24-2) 現物株式売付一覧レスポンス
        regClass(WEB3EquitySellListResponse.class);

        // 25-1) 株式銘柄情報表示リクエスト
        regClass(WEB3EquityProductInformationRequest.class);
        // 25-2) 株式銘柄情報表示レスポンス
        regClass(WEB3EquityProductInformationResponse.class);

        // 26-1) 立会外分売銘柄一覧表示サービス
        regClass(WEB3EquityOffFloorProductListRequest.class);
        // 26-2) 立会外分売銘柄一覧表示レスポンス
        regClass(WEB3EquityOffFloorProductListResponse.class);

        // 27-1) 現物株式買付注文銘柄選択表示リクエスト
        regClass(WEB3EquityProductSelectRequest.class);
        // 27-2) 現物株式買付注文銘柄選択表示レスポンス
        regClass(WEB3EquityProductSelectResponse.class);

        // 28-1) 現物株式残高照会リクエスト
        regClass(WEB3EquityBalanceReferenceRequest.class);
        // 28-2) 現物株式残高照会レスポンス
        regClass(WEB3EquityBalanceReferenceResponse.class);

        // 29-1) 現物株式残高合計リクエスト
        regClass(WEB3EquityBalanceReferenceTotalRequest.class);
        // 29-2) 現物株式残高合計レスポンス
        regClass(WEB3EquityBalanceReferenceTotalResponse.class);

        // 30-1) 現物株式簿価単価登録入力リクエスト
        regClass(WEB3EquityBookPriceInputRequest.class);
        // 30-2) 現物株式簿価単価登録入力レスポンス
        regClass(WEB3EquityBookPriceInputResponse.class);

        // 31-1) 現物株式簿価単価登録リクエスト
        regClass(WEB3EquityBookPriceRegistRequest.class);
        // 31-2) 現物株式簿価単価登録レスポンス
        regClass(WEB3EquityBookPriceRegistResponse.class);

        // 32-1) 株式・信用注文約定照会リクエスト
        regClass(WEB3EquityMarginExecuteReferenceRequest.class);
        // 32-2) 株式・信用注文約定照会レスポンス
        regClass(WEB3EquityMarginExecuteReferenceResponse.class);

        // 33-1) (PTS)株式出来終了通知リクエスト
        regClass(WEB3EquityPTSExecEndNotifyRequest.class);
        // 33-2) (PTS)株式出来終了通知レスポンス
        regClass(WEB3EquityPTSExecEndNotifyResponse.class);

        // Handler の登録
        // 1) 現物株式売付一覧リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquitySellListRequest.class,
            WEB3EquityAssetInquiryHandler.class,
            "assetInquiryRequest");

        // 2) 現物株式注文取消確認リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityCancelConfirmRequest.class,
            WEB3EquityCancelOrderHandler.class,
            "equityCancelConfirmRequest");

        // 3) 現物株式注文取消完了リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityCancelCompleteRequest.class,
            WEB3EquityCancelOrderHandler.class,
            "equityCancelCompleteRequest");

        // 4) 株式訂正取消受付リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityChangeCancelAcceptRequest.class,
            WEB3EquityChangeCancelAcceptHandler.class,
            "equityChangeCancelAcceptRequest");

        // 5) 現物株式注文訂正確認リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityChangeConfirmRequest.class,
            WEB3EquityChangeOrderHandler.class,
            "equityChangeOrderConfirmRequest");

        // 6) 現物株式注文訂正完了リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityChangeCompleteRequest.class,
            WEB3EquityChangeOrderHandler.class,
            "equityChangeOrderCompleteRequest");

        // 7) 現物株式注文訂正入力リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityChangeInputRequest.class,
            WEB3EquityChangeOrderInputHandler.class,
            "equityChangeOrderInputRequest");

        // 8) 現物株式注文約定照会リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityExecuteReferenceRequest.class,
            WEB3EquityExecuteReferenceHandler.class,
            "searchExecuteReference");

        // 9) 現物株式注文約定詳細リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityExecuteDetailsRequest.class,
            WEB3EquityExecuteReferenceHandler.class,
            "searchExecuteDetails");

        // 10) 現物株式注文約定履歴リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityOrderHistoryRequest.class,
            WEB3EquityExecuteReferenceHandler.class,
            "searchOrderHistory");

        // 11) 株式注文受付リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityOrderAcceptRequest.class,
            WEB3EquityOrderAcceptHandler.class,
            "equityOrderAcceptRequest");

        // 12) 現物株式買付注文入力リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityBuyInputRequest.class,
            WEB3EquityOrderBuyInputHandler.class,
            "equityOrderBuyInputRequest");

        // 13) 株式注文繰越リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityOrderCarryOverRequest.class,
            WEB3EquityOrderCarryOverHandler.class,
            "completeCarryOver");

        // 14) 注文繰越スキップ銘柄通知リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityCarryOverSkipRequest.class,
            WEB3EquityOrderCarryOverSkipHandler.class,
            "completeNotify");

        // 15) 出来終了通知リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityExecEndNotifyRequest.class,
            WEB3EquityOrderExecEndNotifyHandler.class,
            "completeNotify");

        // 16) 株式出来通知メインリクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityExecNotifyMainRequest.class,
            WEB3EquityOrderExecNotifyMainHandler.class,
            "equityOrderExecNotifyMainRequest");

        // 17) 現物株式買付注文確認リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityBuyConfirmRequest.class,
            WEB3EquityOrderHandler.class,
            "equityBuyOrderConfirm");

        // 18) 現物株式買付注文完了リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityBuyCompleteRequest.class,
            WEB3EquityOrderHandler.class,
            "equityBuyOrderComplete");

        // 19) 現物株式売付注文確認リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquitySellConfirmRequest.class,
            WEB3EquityOrderHandler.class,
            "equitySellOrderConfirm");

        // 20) 現物株式売付注文完了リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquitySellCompleteRequest.class,
            WEB3EquityOrderHandler.class,
            "equitySellOrderComplete");

        // 21) 現物株式注文通知リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityOrderNotifyRequest.class,
            WEB3EquityOrderNotifyHandler.class,
            "WEB3EquityOrderNotifyRequest");

        // 22) 株式訂正取消通知メインリクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityChangeCancelNotifyMainRequest.class,
            WEB3EquityChangeCancelNotifyMainHandler.class,
            "equityChangeCancelNotifyMainRequest");

        // 23) 株式失効通知リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityCloseOrderRequest.class,
            WEB3EquityReceiveCloseOrderHandler.class,
            "receiveCloseOrder");

        // 24) 現物株式売付注文入力リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquitySellInputRequest.class,
            WEB3EquitySellOrderInputHandler.class,
            "equitySellOrderInputRequest");

        // 25) 株式銘柄情報表示リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityProductInformationRequest.class,
            WEB3EquityProductInformationHandler.class,
            "equityProductInformationRequest");

        // 26) 立会外分売銘柄一覧リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityOffFloorProductListRequest.class,
            WEB3EquityOffFloorProductListHandler.class,
            "equityOffFloorProductListRequest");

        // 27) 現物株式買付注文銘柄選択リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityProductSelectRequest.class,
            WEB3EquityOrderBuyInputHandler.class,
            "equityProductSelectRequest");

        // 28) 現現物株式残高照会リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityBalanceReferenceRequest.class,
            WEB3EquityBalanceReferenceHandler.class,
            "getBalanceReference");

        // 29) 現物株式残高照会残高合計リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityBalanceReferenceTotalRequest.class,
            WEB3EquityBalanceReferenceHandler.class,
            "getBalanceTotal");

        // 30) 現物株式簿価単価登録入力リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityBookPriceInputRequest.class,
            WEB3EquityBookValuePriceRegistHandler.class,
            "getInputScreen");

        // 31) 現物株式簿価単価登録リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityBookPriceRegistRequest.class,
            WEB3EquityBookValuePriceRegistHandler.class,
            "completeBookValuePrice");

        // 32) 株式・信用注文約定照会リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityMarginExecuteReferenceRequest.class,
            WEB3EquityMarginExecuteReferenceHandler.class,
            "searchExecuteReference");

        // 33) (PTS)株式出来終了通知ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityPTSExecEndNotifyRequest.class,
            WEB3EquityPTSExecEndNotifyHandler.class,
            "completeNotify");

        /*
         * 信用取引
         */
        // Service の登録
        // 1) 信用取引決済一覧サービス
        Services.registerService(
            WEB3MarginCloseMarginListService.class,
            new WEB3MarginCloseMarginListServiceImpl());

        // 2) 信用取引建株照会サービス
        Services.registerService(
            WEB3MarginContractReferenceService.class,
            new WEB3MarginContractReferenceServiceImpl());

        // 3) 信用取引現引現渡サービス
        Services.registerService(
            WEB3MarginSwapMarginService.class,
            new WEB3MarginSwapMarginServiceImpl());

        // 4) 信用取引現引現渡受付サービス
        Services.registerService(
            WEB3MarginSwapMarginAcceptService.class,
            new WEB3MarginSwapMarginAcceptServiceImpl());

        // 5) 信用取引現引現渡受付一件サービス
        Services.registerService(
            WEB3MarginSwapMarginAcceptUnitService.class,
            new WEB3MarginSwapMarginAcceptUnitServiceImpl());

        // 6) 信用取引現引現渡入力サービス
        Services.registerService(
            WEB3MarginSwapMarginInputService.class,
            new WEB3MarginSwapMarginInputServiceImpl());

        // 7) 信用取引取消サービス
        Services.registerService(
            WEB3MarginCancelService.class,
            new WEB3MarginCancelServiceImpl());

        // 8) 信用取引出来通知一件サービス
        Services.registerService(
            WEB3MarginOrderExecNotifyUnitService.class,
            new WEB3MarginOrderExecNotifyUnitServiceImpl());

        // 9) 信用取引新規建サービス
        Services.registerService(
            WEB3MarginOpenMarginService.class,
            new WEB3MarginOpenMarginServiceImpl());

        // 10) 信用取引新規建入力サービス
        Services.registerService(
            WEB3MarginOpenMarginInputService.class,
            new WEB3MarginOpenMarginInputServiceImpl());

        // 11) 信用取引注文通知サービス
        Services.registerService(
            WEB3MarginOrderNotifyService.class,
            new WEB3MarginOrderNotifyServiceImpl());

        // 12) 信用取引注文通知一件サービス
        Services.registerService(
            WEB3MarginOrderNotifyUnitService.class,
            new WEB3MarginOrderNotifyUnitServiceImpl());

        // 13) 信用取引注文約定照会サービス
        Services.registerService(
            WEB3MarginExecuteReferenceService.class,
            new WEB3MarginExecuteReferenceServiceImpl());

        // 14) 信用取引訂正取消通知取消一件サービス
        Services.registerService(
            WEB3MarginChangeCancelNotifyCancelUnitService.class,
            new WEB3MarginChangeCancelNotifyCancelUnitServiceImpl());

        // 15) 信用取引訂正取消通知訂正一件サービス
        Services.registerService(
            WEB3MarginChangeCancelNotifyChangeUnitService.class,
            new WEB3MarginChangeCancelNotifyChangeUnitServiceImpl());

        // 16) 信用取引訂正新規建サービス
        Services.registerService(
            WEB3MarginChangeOpenMarginService.class,
            new WEB3MarginChangeOpenMarginServiceImpl());

        // 17) 信用取引訂正新規建入力サービス
        Services.registerService(
            WEB3MarginChangeOpenMarginInputService.class,
            new WEB3MarginChangeOpenMarginInputServiceImpl());

        // 18) 信用取引訂正返済サービス
        Services.registerService(
            WEB3MarginChangeCloseMarginService.class,
            new WEB3MarginChangeCloseMarginServiceImpl());

        // 19) 信用取引訂正返済入力サービス
        Services.registerService(
            WEB3MarginChangeCloseMarginInputService.class,
            new WEB3MarginChangeCloseMarginInputServiceImpl());

        // 20) 信用取引返済サービス
        Services.registerService(
            WEB3MarginCloseMarginService.class,
            new WEB3MarginCloseMarginServiceImpl());

        // 21) 信用取引返済入力サービス
        Services.registerService(
            WEB3MarginCloseMarginInputService.class,
            new WEB3MarginCloseMarginInputServiceImpl());

        // 22) 信用取引現引現渡注文通知サービス
        Services.registerService(
            WEB3MarginSwapOrderNotifyService.class,
            new WEB3MarginSwapOrderNotifyServiceImpl());

        // 23) 信用取引現引現渡注文通知一件サービス
        Services.registerService(
            WEB3MarginSwapOrderNotifyUnitService.class,
            new WEB3MarginSwapOrderNotifyUnitServiceImpl());

        // 24) 信用取引残高照会サービス
        Services.registerService(
            WEB3MarginBalanceReferenceService.class,
            new WEB3MarginBalanceReferenceServiceImpl());

        // Serviceインタセプタの設定
        // 1) 信用取引決済一覧サービスインタセプタ
        Services.addInterceptor(
            WEB3MarginCloseMarginListService.class,
            new WEB3MarginCloseMarginListServiceInterceptor());

        // 2) 信用取引建株照会サービスインタセプタ
        Services.addInterceptor(
            WEB3MarginContractReferenceService.class,
            new WEB3MarginContractReferenceServiceInterceptor());

        // 3) 信用取引現引現渡サービスインタセプタ
        Services.addInterceptor(
            WEB3MarginSwapMarginService.class,
            new WEB3MarginSwapMarginServiceInterceptor());

        // 4) 信用取引現引現渡受付一件サービスインタセプタ
        Services.addInterceptor(
            WEB3MarginSwapMarginAcceptUnitService.class,
            new WEB3MarginSwapMarginAcceptUnitServiceInterceptor());

        // 5) 信用取引現引現渡入力サービスインタセプタ
        Services.addInterceptor(
            WEB3MarginSwapMarginInputService.class,
            new WEB3MarginSwapMarginInputServiceInterceptor());

        // 6) 信用取引取消サービスインタセプタ
        Services.addInterceptor(
            WEB3MarginCancelService.class,
            new WEB3MarginCancelServiceInterceptor());

        // 7) 信用取引出来通知一件サービスインタセプタ
        Services.addInterceptor(
            WEB3MarginOrderExecNotifyUnitService.class,
            new WEB3MarginOrderExecNotifyUnitServiceInterceptor());

        // 8) 信用取引新規建サービスインタセプタ
        Services.addInterceptor(
            WEB3MarginOpenMarginService.class,
            new WEB3MarginOpenMarginServiceInterceptor());

        // 9) 信用取引新規建入力サービスインタセプタ
        Services.addInterceptor(
            WEB3MarginOpenMarginInputService.class,
            new WEB3MarginOpenMarginInputServiceInterceptor());

        // 10) 信用取引注文通知一件サービスインタセプタ
        Services.addInterceptor(
            WEB3MarginOrderNotifyUnitService.class,
            new WEB3MarginOrderNotifyUnitServiceInterceptor());

        // 11) 信用取引注文約定照会サービスインタセプタ
        Services.addInterceptor(
            WEB3MarginExecuteReferenceService.class,
            new WEB3MarginExecuteReferenceServiceInterceptor());

        // 12) 信用取引訂正取消通知サービスインタセプタ
        Services.addInterceptor(
            WEB3MarginChangeCancelNotifyChangeUnitService.class,
            new WEB3MarginChangeCancelNotifyChangeUnitServiceInterceptor());

        // 13) 信用取引訂正新規建サービスインタセプタ
        Services.addInterceptor(
            WEB3MarginChangeOpenMarginService.class,
            new WEB3MarginChangeOpenMarginServiceInterceptor());

        // 14) 信用取引訂正新規建入力サービスインタセプタ
        Services.addInterceptor(
            WEB3MarginChangeOpenMarginInputService.class,
            new WEB3MarginChangeOpenMarginInputServiceInterceptor());

        // 15) 信用取引訂正返済サービスインタセプタ
        Services.addInterceptor(
            WEB3MarginChangeCloseMarginService.class,
            new WEB3MarginChangeCloseMarginServiceInterceptor());

        // 16) 信用取引訂正返済入力サービスインタセプタ
        Services.addInterceptor(
            WEB3MarginChangeCloseMarginInputService.class,
            new WEB3MarginChangeCloseMarginInputServiceInterceptor());

        // 17) 信用取引返済サービスインタセプタ
        Services.addInterceptor(
            WEB3MarginCloseMarginService.class,
            new WEB3MarginCloseMarginServiceInterceptor());

        // 18) 信用取引返済入力サービスインタセプタ
        Services.addInterceptor(
            WEB3MarginCloseMarginInputService.class,
            new WEB3MarginCloseMarginInputServiceInterceptor());

        // 19) 信用取引訂正取消通知取消一件サービスインタセプタ
        Services.addInterceptor(
            WEB3MarginChangeCancelNotifyCancelUnitService.class,
            new WEB3MarginChangeCancelNotifyCancelUnitServiceInterceptor());

        // 20) 信用取引現引現渡注文通知一件サービスインタセプタ
        Services.addInterceptor(
            WEB3MarginSwapOrderNotifyUnitService.class,
            new WEB3MarginSwapOrderNotifyUnitServiceInterceptor());

        // 21) 信用取引株式残高照会サービスインタセプタ
        Services.addInterceptor(
            WEB3MarginBalanceReferenceService.class,
            new WEB3MarginBalanceReferenceServiceInterceptor());

        // 自動トランザクション設定
        // 1) 信用取引現引現渡サービス
        Services.addInterceptor(
            WEB3MarginSwapMarginService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 2) 信用取引現引現渡受付一件サービス
        Services.addInterceptor(
            WEB3MarginSwapMarginAcceptUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 3) 信用取引取消サービス
        Services.addInterceptor(
            WEB3MarginCancelService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 4) 信用取引出来通知一件サービス
        Services.addInterceptor(
            WEB3MarginOrderExecNotifyUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 5) 信用取引新規建サービス
        Services.addInterceptor(
            WEB3MarginOpenMarginService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 6) 信用取引注文通知一件サービス
        Services.addInterceptor(
            WEB3MarginOrderNotifyUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 7) 信用取引訂正取消通知訂正一件サービス
        Services.addInterceptor(
            WEB3MarginChangeCancelNotifyChangeUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 8) 信用取引訂正新規建サービス
        Services.addInterceptor(
            WEB3MarginChangeOpenMarginService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 9) 信用取引訂正返済サービス
        Services.addInterceptor(
            WEB3MarginChangeCloseMarginService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 10) 信用取引返済サービス
        Services.addInterceptor(
            WEB3MarginCloseMarginService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 11) 信用取引訂正取消通知取消一件サービス
        Services.addInterceptor(
            WEB3MarginChangeCancelNotifyCancelUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 12) 信用取引現引現渡注文通知一件サービス
        Services.addInterceptor(
            WEB3MarginSwapOrderNotifyUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // ルールエンジンサーバ・インタセプタの設定
        // 1) 信用取引現引現渡サービス
        Services.addInterceptor(
            WEB3MarginSwapMarginService.class,
            new WEB3RlsGatewayInterceptor());

        // 2) 信用取引取消サービス
        Services.addInterceptor(
            WEB3MarginCancelService.class,
            new WEB3RlsGatewayInterceptor());

        // 3) 信用取引新規建サービス
        Services.addInterceptor(
            WEB3MarginOpenMarginService.class,
            new WEB3RlsGatewayInterceptor());

        // 4) 信用取引訂正新規建サービス
        Services.addInterceptor(
            WEB3MarginChangeOpenMarginService.class,
            new WEB3RlsGatewayInterceptor());

        // 5) 信用取引訂正返済サービス
        Services.addInterceptor(
            WEB3MarginChangeCloseMarginService.class,
            new WEB3RlsGatewayInterceptor());

        // 6) 信用取引返済サービス
        Services.addInterceptor(
            WEB3MarginCloseMarginService.class,
            new WEB3RlsGatewayInterceptor());

        // 7) 信用取引訂正取消通知訂正一件サービス
        Services.addInterceptor(
            WEB3MarginChangeCancelNotifyChangeUnitService.class,
            new WEB3RlsGatewayInterceptor());

        // 8) 信用取引出来通知一件サービス
        Services.addInterceptor(
            WEB3MarginOrderExecNotifyUnitService.class,
            new WEB3RlsGatewayInterceptor());

        // MQ-Gatewayインタセプタの設定
        // 1) 信用取引現引現渡サービス
        Services.addInterceptor(
            WEB3MarginSwapMarginService.class,
            new WEB3MQGatewayInterceptor());

        // 2) 信用取引取消サービス
        Services.addInterceptor(
            WEB3MarginCancelService.class,
            new WEB3MQGatewayInterceptor());

        // 3) 信用取引新規建サービス
        Services.addInterceptor(
            WEB3MarginOpenMarginService.class,
            new WEB3MQGatewayInterceptor());

        // 4) 信用取引訂正新規建サービス
        Services.addInterceptor(
            WEB3MarginChangeOpenMarginService.class,
            new WEB3MQGatewayInterceptor());

        // 5) 信用取引訂正返済サービス
        Services.addInterceptor(
            WEB3MarginChangeCloseMarginService.class,
            new WEB3MQGatewayInterceptor());

        // 6) 信用取引返済サービス
        Services.addInterceptor(
            WEB3MarginCloseMarginService.class,
            new WEB3MQGatewayInterceptor());

        // 処理時間ログ出力インタセプタの設定
        // 1) 信用取引決済一覧サービス
        Services.addInterceptor(
            WEB3MarginCloseMarginListService.class,
            new WEB3LogSysTimeInterceptor());

        // 2) 信用取引建株照会サービス
        Services.addInterceptor(
            WEB3MarginContractReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // 3) 信用取引現引現渡サービス
        Services.addInterceptor(
            WEB3MarginSwapMarginService.class,
            new WEB3LogSysTimeInterceptor());

        // 4) 信用取引現引現渡受付サービス
        Services.addInterceptor(
            WEB3MarginSwapMarginAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        // 5) 信用取引現引現渡受付一件サービス
        Services.addInterceptor(
            WEB3MarginSwapMarginAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 6) 信用取引現引現渡入力サービス
        Services.addInterceptor(
            WEB3MarginSwapMarginInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 7) 信用取引取消サービス
        Services.addInterceptor(
            WEB3MarginCancelService.class,
            new WEB3LogSysTimeInterceptor());

        // 8) 信用取引出来通知一件サービス
        Services.addInterceptor(
            WEB3MarginOrderExecNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 9) 信用取引新規建サービス
        Services.addInterceptor(
            WEB3MarginOpenMarginService.class,
            new WEB3LogSysTimeInterceptor());

        // 10) 信用取引新規建入力サービス
        Services.addInterceptor(
            WEB3MarginOpenMarginInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 11) 信用取引注文通知サービス
        Services.addInterceptor(
            WEB3MarginOrderNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        // 12) 信用取引注文通知一件サービス
        Services.addInterceptor(
            WEB3MarginOrderNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 13) 信用取引注文約定照会サービス
        Services.addInterceptor(
            WEB3MarginExecuteReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // 14) 信用取引訂正取消通知取消一件サービス
        Services.addInterceptor(
            WEB3MarginChangeCancelNotifyCancelUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 15) 信用取引訂正取消通知訂正一件サービス
        Services.addInterceptor(
            WEB3MarginChangeCancelNotifyChangeUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 16) 信用取引訂正新規建サービス
        Services.addInterceptor(
            WEB3MarginChangeOpenMarginService.class,
            new WEB3LogSysTimeInterceptor());

        // 17) 信用取引訂正新規建入力サービス
        Services.addInterceptor(
            WEB3MarginChangeOpenMarginInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 18) 信用取引訂正返済サービス
        Services.addInterceptor(
            WEB3MarginChangeCloseMarginService.class,
            new WEB3LogSysTimeInterceptor());

        // 19) 信用取引訂正返済入力サービス
        Services.addInterceptor(
            WEB3MarginChangeCloseMarginInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 20) 信用取引返済サービス
        Services.addInterceptor(
            WEB3MarginCloseMarginService.class,
            new WEB3LogSysTimeInterceptor());

        // 21) 信用取引返済入力サービス
        Services.addInterceptor(
            WEB3MarginCloseMarginInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 22) 信用取引現引現渡注文通知サービス
        Services.addInterceptor(
            WEB3MarginSwapOrderNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        // 23) 信用取引現引現渡注文通知一件サービス
        Services.addInterceptor(
            WEB3MarginSwapOrderNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 24) 信用取引残高照会サービス
        Services.addInterceptor(
            WEB3MarginBalanceReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // Message の登録
        // 1-1) 信用取引決済一覧リクエスト
        regClass(WEB3MarginCloseMarginListRequest.class);
        // 1-2) 信用取引決済一覧レスポンス
        regClass(WEB3MarginCloseMarginListResponse.class);

        // 2-1) 信用取引個別決済一覧リクエスト
        regClass(WEB3MarginIndividualCloseMarginListRequest.class);
        // 2-2) 信用取引個別決済一覧レスポンス
        regClass(WEB3MarginIndividualCloseMarginListResponse.class);

        // 3-1) 信用取引建株照会リクエスト
        regClass(WEB3MarginContractReferenceRequest.class);
        // 3-2) 信用取引建株照会レスポンス
        regClass(WEB3MarginContractReferenceResponse.class);

        // 4-1) 信用取引現引現渡注文確認リクエスト
        regClass(WEB3MarginSwapMarginConfirmRequest.class);
        // 4-2) 信用取引現引現渡注文確認レスポンス
        regClass(WEB3MarginSwapMarginConfirmResponse.class);

        // 5-1) 信用取引現引現渡注文完了リクエスト
        regClass(WEB3MarginSwapMarginCompleteRequest.class);
        // 5-2) 信用取引現引現渡注文完了レスポンス
        regClass(WEB3MarginSwapMarginCompleteResponse.class);

        // 6-1) 信用取引現引現渡受付リクエスト
        regClass(WEB3MarginSwapMarginAcceptRequest.class);
        // 6-2) 信用取引現引現渡受付レスポンス
        regClass(WEB3MarginSwapMarginAcceptResponse.class);

        // 7-1) 信用取引現引現渡注文入力リクエスト
        regClass(WEB3MarginSwapMarginInputRequest.class);
        // 7-2) 信用取引現引現渡注文入力レスポンス
        regClass(WEB3MarginSwapMarginInputResponse.class);

        // 8-1) 信用取引注文取消確認リクエスト
        regClass(WEB3MarginCancelConfirmRequest.class);
        // 8-2) 信用取引注文取消確認レスポンス
        regClass(WEB3MarginCancelConfirmResponse.class);

        // 9-1) 信用取引注文取消完了リクエスト
        regClass(WEB3MarginCancelCompleteRequest.class);
        // 9-2) 信用取引注文取消完了レスポンス
        regClass(WEB3MarginCancelCompleteResponse.class);

        // 10-1) 信用取引新規建注文確認リクエスト
        regClass(WEB3MarginOpenMarginConfirmRequest.class);
        // 10-2) 信用取引新規建注文確認レスポンス
        regClass(WEB3MarginOpenMarginConfirmResponse.class);

        // 11-1) 信用取引新規建注文完了リクエスト
        regClass(WEB3MarginOpenMarginCompleteRequest.class);
        // 11-2) 信用取引新規建注文完了レスポンス
        regClass(WEB3MarginOpenMarginCompleteResponse.class);

        // 12-1) 信用取引新規建注文銘柄選択リクエスト
        regClass(WEB3MarginProductSelectRequest.class);
        // 12-2) 信用取引新規建注文銘柄選択レスポンス
        regClass(WEB3MarginProductSelectResponse.class);

        // 13-1) 信用取引新規建注文入力リクエスト
        regClass(WEB3MarginOpenMarginInputRequest.class);
        // 13-2) 信用取引新規建注文入力レスポンス
        regClass(WEB3MarginOpenMarginInputResponse.class);

        // 14-1) 信用取引注文通知リクエスト
        regClass(WEB3MarginOrderNotifyRequest.class);
        // 14-2) 信用取引注文通知レスポンス
        regClass(WEB3MarginOrderNotifyResponse.class);

        // 15-1) 信用取引注文約定照会リクエスト
        regClass(WEB3MarginExecuteReferenceRequest.class);
        // 15-2) 信用取引注文約定照会レスポンス
        regClass(WEB3MarginExecuteReferenceResponse.class);

        // 16-1) 信用取引注文約定詳細リクエスト
        regClass(WEB3MarginExecuteDetailsRequest.class);
        // 16-2) 信用取引注文約定詳細レスポンス
        regClass(WEB3MarginExecuteDetailsResponse.class);

        // 17-1) 信用取引注文履歴照会リクエスト
        regClass(WEB3MarginOrderHistoryRequest.class);
        // 17-2) 信用取引注文履歴照会レスポンス
        regClass(WEB3MarginOrderHistoryResponse.class);

        // 18-1) 信用取引決済建株一覧リクエスト
        regClass(WEB3MarginCloseMarginContractListRequest.class);
        // 18-2) 信用取引決済建株一覧レスポンス
        regClass(WEB3MarginCloseMarginContractListResponse.class);

        // 19-1) 信用取引注文訂正_新規建確認リクエスト
        regClass(WEB3MarginOpenMarginChangeConfirmRequest.class);
        // 19-2) 信用取引注文訂正_新規建確認レスポンス
        regClass(WEB3MarginOpenMarginChangeConfirmResponse.class);

        // 20-1) 信用取引注文訂正_新規建完了リクエスト
        regClass(WEB3MarginOpenMarginChangeCompleteRequest.class);
        // 20-2) 信用取引注文訂正_新規建完了レスポンス
        regClass(WEB3MarginOpenMarginChangeCompleteResponse.class);

        // 21-1) 信用取引訂正新規建入力リクエスト
        regClass(WEB3MarginOpenMarginChangeInputRequest.class);
        // 21-2) 信用取引訂正新規建入力レスポンス
        regClass(WEB3MarginOpenMarginChangeInputResponse.class);

        // 22-1) 信用取引注文訂正_返済確認リクエスト
        regClass(WEB3MarginCloseMarginChangeConfirmRequest.class);
        // 22-2) 信用取引注文訂正_返済確認レスポンス
        regClass(WEB3MarginCloseMarginChangeConfirmResponse.class);

        // 23-1) 信用取引注文訂正_返済完了リクエスト
        regClass(WEB3MarginCloseMarginChangeCompleteRequest.class);
        // 23-2) 信用取引注文訂正_返済完了レスポンス
        regClass(WEB3MarginCloseMarginChangeCompleteResponse.class);

        // 24-1) 信用取引訂正返済入力リクエスト
        regClass(WEB3MarginCloseMarginChangeInputRequest.class);
        // 24-2) 信用取引訂正返済入力レスポンス
        regClass(WEB3MarginCloseMarginChangeInputResponse.class);

        // 25-1) 信用取引返済注文確認リクエスト
        regClass(WEB3MarginCloseMarginConfirmRequest.class);
        // 25-2) 信用取引返済注文確認レスポンス
        regClass(WEB3MarginCloseMarginConfirmResponse.class);

        // 26-1) 信用取引返済注文完了リクエスト
        regClass(WEB3MarginCloseMarginCompleteRequest.class);
        // 26-2) 信用取引返済注文完了レスポンス
        regClass(WEB3MarginCloseMarginCompleteResponse.class);

        // 27-1) 信用取引返済注文入力リクエスト
        regClass(WEB3MarginCloseMarginInputRequest.class);
        // 27-2) 信用取引返済注文入力レスポンス
        regClass(WEB3MarginCloseMarginInputResponse.class);

        // 28-1) 信用取引現引現渡注文通知リクエスト
        regClass(WEB3MarginSwapOrderNotifyRequest.class);
        // 28-2) 信用取引現引現渡注文通知レスポンス
        regClass(WEB3MarginSwapOrderNotifyResponse.class);

        // 29-1) 信用取引残高照会リクエスト
        regClass(WEB3MarginBalanceReferenceRequest.class);
        // 29-2) 信用取引残高照会レスポンス
        regClass(WEB3MarginBalanceReferenceResponse.class);

        // 30-1) 信用取引残高合計リクエスト
        regClass(WEB3MarginBalanceReferenceTotalRequest.class);
        // 30-2) 信用取引残高合計レスポンス
        regClass(WEB3MarginBalanceReferenceTotalResponse.class);

        // Handler の登録
        // 1) 信用取引決済一覧リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginCloseMarginListRequest.class,
            WEB3MarginCloseMarginListHandler.class,
            "getCloseMarginList");

        // 2) 信用取引個別決済一覧リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginIndividualCloseMarginListRequest.class,
            WEB3MarginCloseMarginListHandler.class,
            "getIndividualCloseMarginList");

        // 3) 信用取引建株照会リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginContractReferenceRequest.class,
            WEB3MarginContractReferenceHandler.class,
            "getContractReference");

        // 4) 信用取引現引現渡注文確認リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginSwapMarginConfirmRequest.class,
            WEB3MarginSwapMarginHandler.class,
            "confirmSwapMargin");

        // 5) 信用取引現引現渡注文完了リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginSwapMarginCompleteRequest.class,
            WEB3MarginSwapMarginHandler.class,
            "completeSwapMargin");

        // 6) 信用取引現引現渡受付リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginSwapMarginAcceptRequest.class,
            WEB3MarginSwapMarginAcceptHandler.class,
            "swapMarginAcceptRequest");

        // 7) 信用取引現引現渡注文入力リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginSwapMarginInputRequest.class,
            WEB3MarginSwapMarginInputHandler.class,
            "getSwapMarginInputScreen");

        // 8) 信用取引注文取消確認リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginCancelConfirmRequest.class,
            WEB3MarginCancelHandler.class,
            "confirmCancel");

        // 9) 信用取引注文取消完了リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginCancelCompleteRequest.class,
            WEB3MarginCancelHandler.class,
            "completeCancel");

        // 10) 信用取引新規建注文確認リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginOpenMarginConfirmRequest.class,
            WEB3MarginOpenMarginHandler.class,
            "confirmOrder");

        // 11) 信用取引新規建注文完了リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginOpenMarginCompleteRequest.class,
            WEB3MarginOpenMarginHandler.class,
            "completeOrder");

        // 12) 信用取引新規建注文銘柄選択リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginProductSelectRequest.class,
            WEB3MarginOpenMarginInputHandler.class,
            "getProductSelectScreen");

        // 13) 信用取引新規建注文入力リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginOpenMarginInputRequest.class,
            WEB3MarginOpenMarginInputHandler.class,
            "getOpenMarginInputScreen");

        // 14) 信用取引注文通知リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginOrderNotifyRequest.class,
            WEB3MarginOrderNotifyHandler.class,
            "orderNotifyRequest");

        // 15) 信用取引注文約定照会リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginExecuteReferenceRequest.class,
            WEB3MarginExecuteReferenceHandler.class,
            "searchOrderExecuteReference");

        // 16) 信用取引注文約定詳細リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginExecuteDetailsRequest.class,
            WEB3MarginExecuteReferenceHandler.class,
            "searchOrderExecuteDetails");

        // 17) 信用取引注文履歴照会リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginOrderHistoryRequest.class,
            WEB3MarginExecuteReferenceHandler.class,
            "searchOrderHistoryInquiry");

        // 18) 信用取引決済建株一覧リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginCloseMarginContractListRequest.class,
            WEB3MarginExecuteReferenceHandler.class,
            "searchCloseMarginContractList");

        // 19) 信用取引注文訂正_新規建確認リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginOpenMarginChangeConfirmRequest.class,
            WEB3MarginChangeOpenMarginHandler.class,
            "confirmOpenMarginChange");

        // 20) 信用取引注文訂正_新規建完了リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginOpenMarginChangeCompleteRequest.class,
            WEB3MarginChangeOpenMarginHandler.class,
            "completeOpenMarginChange");

        // 21) 信用取引訂正新規建入力リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginOpenMarginChangeInputRequest.class,
            WEB3MarginChangeOpenMarginInputHandler.class,
            "getOpenMarginChangeInputScreen");

        // 22) 信用取引注文訂正_返済確認リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginCloseMarginChangeConfirmRequest.class,
            WEB3MarginChangeCloseMarginHandler.class,
            "confirmCloseMarginChange");

        // 23) 信用取引注文訂正_返済完了リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginCloseMarginChangeCompleteRequest.class,
            WEB3MarginChangeCloseMarginHandler.class,
            "completeCloseMarginChange");

        // 24) 信用取引訂正返済入力リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginCloseMarginChangeInputRequest.class,
            WEB3MarginChangeCloseMarginInputHandler.class,
            "getCloseMarginChangeInputScreen");

        // 25) 信用取引返済注文確認リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginCloseMarginConfirmRequest.class,
            WEB3MarginCloseMarginHandler.class,
            "confirmCloseMargin");

        // 26) 信用取引返済注文完了リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginCloseMarginCompleteRequest.class,
            WEB3MarginCloseMarginHandler.class,
            "completeCloseMargin");

        // 27) 信用取引返済注文入力リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginCloseMarginInputRequest.class,
            WEB3MarginCloseMarginInputServiceHandler.class,
            "getCloseMarginInputScreen");

        // 28) 信用取引現引現渡注文通知リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginSwapOrderNotifyRequest.class,
            WEB3MarginSwapOrderNotifyHandler.class,
            "swapOrderNotify");

        // 29) 信用取引残高照会リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginBalanceReferenceRequest.class,
            WEB3MarginBalanceReferenceHandler.class,
            "getBalanceReference");

        // 30) 信用取引残高照会残高合計リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginBalanceReferenceTotalRequest.class,
            WEB3MarginBalanceReferenceHandler.class,
            "getBalanceTotal");


        /**
         *  株式ミニ投資
         */
        // Serviceの登録
        // 1) 株式ミニ投資注文取消サービス
        Services.registerService(
            WEB3MstkCancelService.class,
            new WEB3MstkCancelServiceImpl());

        // 2) 株式ミニ投資買付注文サービス
        Services.registerService(
            WEB3MstkBuyService.class,
            new WEB3MstkBuyServiceImpl());

        // 3) 株式ミニ投資注文約定照会サービス
        Services.registerService(
            WEB3MstkExecuteReferenceService.class,
            new WEB3MstkExecuteReferenceServiceImpl());

        // 4) 株式ミニ投資売付注文サービス
        Services.registerService(
            WEB3MstkSellService.class,
            new WEB3MstkSellServiceImpl());

        // 5) 株式ミニ投資残高照会サービス
        Services.registerService(
            WEB3MstkBalanceReferenceService.class,
            new WEB3MstkBalanceReferenceServiceImpl());

        // 6) 株式ミニ投資簿価単価登録サービス
        Services.registerService(
            WEB3MstkBookValuePriceRegistService.class,
            new WEB3MstkBookValuePriceRegistServiceImpl());

        // Serviceインタセプタの設定
        // 1) 株式ミニ投資注文取消サービスインタセプタ
        Services.addInterceptor(
            WEB3MstkCancelService.class,
            new WEB3MstkCancelServiceInterceptor());

        // 2) 株式ミニ投資買付注文サービスインタセプタ
        Services.addInterceptor(
            WEB3MstkBuyService.class,
            new WEB3MstkBuyServiceInterceptor());

        // 3) 株式ミニ投資注文約定照会サービスインタセプタ
        Services.addInterceptor(
            WEB3MstkExecuteReferenceService.class,
            new WEB3MstkExecuteReferenceServiceInterceptor());

        // 4) 株式ミニ投資売付注文サービスインタセプタ
        Services.addInterceptor(
            WEB3MstkSellService.class,
            new WEB3MstkSellServiceInterceptor());

        // 5) 株式ミニ投資残高照会サービスインタセプタ
        Services.addInterceptor(
            WEB3MstkBalanceReferenceService.class,
            new WEB3MstkBalanceReferenceServiceInterceptor());

        // 6) 株式ミニ投資簿価単価登録サービスインタセプタ
        Services.addInterceptor(
            WEB3MstkBookValuePriceRegistService.class,
            new WEB3MstkBookValuePriceRegistServiceInterceptor());

        // 自動トランザクションの設定
        // 1) 株式ミニ投資注文取消サービス
        Services.addInterceptor(
            WEB3MstkCancelService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));
        
        // 2) 株式ミニ投資買付注文サービス
        Services.addInterceptor(
            WEB3MstkBuyService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 3) 株式ミニ投資売付注文サービス
        Services.addInterceptor(
            WEB3MstkSellService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 4) 株式ミニ投資簿価単価登録サービス
        Services.addInterceptor(
            WEB3MstkBookValuePriceRegistService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // MQ-Gatewayインタセプタの設定
        // 1) 株式ミニ投資注文取消サービス
        Services.addInterceptor(
            WEB3MstkCancelService.class,
            new WEB3MQGatewayInterceptor());

        // 2) 株式ミニ投資買付注文サービス
        Services.addInterceptor(
           WEB3MstkBuyService.class,
           new WEB3MQGatewayInterceptor());

        // 3) 株式ミニ投資売付注文サービス
        Services.addInterceptor(
            WEB3MstkSellService.class,
            new WEB3MQGatewayInterceptor());

        // 処理時間ログ出力インタセプタの設定
        // 1) 株式ミニ投資注文約定照会サービス
        Services.addInterceptor(
            WEB3MstkExecuteReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // 2) 株式ミニ投資買付注文サービス
        Services.addInterceptor(
            WEB3MstkBuyService.class,
            new WEB3LogSysTimeInterceptor());

        // 3) 株式ミニ投資注文取消サービス
        Services.addInterceptor(
            WEB3MstkCancelService.class,
            new WEB3LogSysTimeInterceptor());

        // 4) 株式ミニ投資売付注文サービス
        Services.addInterceptor(
            WEB3MstkSellService.class,
            new WEB3LogSysTimeInterceptor());

        // 5) 株式ミニ投資残高照会サービス
        Services.addInterceptor(
            WEB3MstkBalanceReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // 6) 株式ミニ投資簿価単価登録サービス
        Services.addInterceptor(
            WEB3MstkBookValuePriceRegistService.class,
            new WEB3LogSysTimeInterceptor());
        
        // Message の登録
        // 1-1) 株式ミニ投資注文取消確認リクエスト
        regClass(WEB3MstkCancelConfirmRequest.class);
        // 1-2) 株式ミニ投資注文取消確認レスポンス
        regClass(WEB3MstkCancelConfirmResponse.class);
        
        // 2-1) 株式ミニ投資注文取消完了リクエスト
        regClass(WEB3MstkCancelCompleteRequest.class);
        // 2-2) 株式ミニ投資注文取消完了レスポンス
        regClass(WEB3MstkCancelCompleteResponse.class);

        // 3-1) 株式ミニ投資買付注文確認リクエスト
        regClass(WEB3MstkBuyConfirmRequest.class);
        // 3-2) 株式ミニ投資買付注文確認レスポンス
        regClass(WEB3MstkBuyConfirmResponse.class);
        
        // 4-1) 株式ミニ投資買付注文完了リクエスト
        regClass(WEB3MstkBuyCompleteRequest.class);
        // 4-2) 株式ミニ投資買付注文完了レスポンス
        regClass(WEB3MstkBuyCompleteResponse.class);
        
        // 5-1) 株式ミニ投資買付注文入力リクエスト
        regClass(WEB3MstkBuyInputRequest.class);
        // 5-2) 株式ミニ投資買付注文入力レスポンス
        regClass(WEB3MstkBuyInputResponse.class);

        // 6-1) 株式ミニ投資注文約定照会リクエスト
        regClass(WEB3MstkExecuteReferenceRequest.class);
        // 6-2) 株式ミニ投資注文約定照会レスポンス
        regClass(WEB3MstkExecuteReferenceResponse.class);

        // 7-1) 株式ミニ投資売付注文確認リクエスト
        regClass(WEB3MstkSellConfirmRequest.class);
        // 7-2) 株式ミニ投資売付注文確認レスポンス
        regClass(WEB3MstkSellConfirmResponse.class);
        
        // 8-1) 株式ミニ投資売付注文完了リクエスト
        regClass(WEB3MstkSellCompleteRequest.class);
        // 8-2) 株式ミニ投資売付注文完了レスポンス
        regClass(WEB3MstkSellCompleteResponse.class);
        
        // 9-1) 株式ミニ投資売付注文入力リクエスト
        regClass(WEB3MstkSellInputRequest.class);
        // 9-2) 株式ミニ投資売付注文入力レスポンス
        regClass(WEB3MstkSellInputResponse.class);
        
        // 10-1) 株式ミニ投資売付一覧リクエスト
        regClass(WEB3MstkSellListRequest.class);
        // 10-2) 株式ミニ投資売付一覧レスポンス
        regClass(WEB3MstkSellListResponse.class);

        // 11-1) 株式ミニ投資残高照会リクエスト
        regClass(WEB3MstkBalanceReferenceRequest.class);
        // 11-2) 株式ミニ投資残高照会レスポンス
        regClass(WEB3MstkBalanceReferenceResponse.class);
        
        // 12-1) 株式ミニ投資残高合計リクエスト
        regClass(WEB3MstkBalanceReferenceTotalRequest.class);
        // 12-2) 株式ミニ投資残高合計レスポンス
        regClass(WEB3MstkBalanceReferenceTotalResponse.class);

        // 13-1) 株式ミニ投資簿価単価登録入力リクエスト
        regClass(WEB3MstkBookPriceInputRequest.class);
        // 13-2) 株式ミニ投資簿価単価登録入力レスポンス
        regClass(WEB3MstkBookPriceInputResponse.class);
        
        // 14-1) 株式ミニ投資簿価単価登録リクエスト
        regClass(WEB3MstkBookPriceRegistRequest.class);
        // 14-2) 株式ミニ投資簿価単価登録レスポンス
        regClass(WEB3MstkBookPriceRegistResponse.class);

        // Handler の登録
        // 1) 株式ミニ投資注文取消確認リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkCancelConfirmRequest.class,
            WEB3MstkCancelHandler.class,
            "handleConfirmOrder");

        // 2) 株式ミニ投資注文取消完了リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkCancelCompleteRequest.class,
            WEB3MstkCancelHandler.class,
            "handleCompleteOrder");

        // 3) 株式ミニ投資売付一覧リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkSellListRequest.class,
            WEB3MstkSellHandler.class,
            "handleGetSellList");

        // 4) 株式ミニ投資売付注文入力リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkSellInputRequest.class,
            WEB3MstkSellHandler.class,
            "handleGetSellInputScreen");

        // 5) 株式ミニ投資売付注文確認リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkSellConfirmRequest.class,
            WEB3MstkSellHandler.class,
            "handleConfirmOrder");

        // 6) 株式ミニ投資売付注文完了リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkSellCompleteRequest.class,
            WEB3MstkSellHandler.class,
            "handleCompleteOrder");

        // 7) 株式ミニ投資注文約定照会リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkExecuteReferenceRequest.class,
            WEB3MstkExecuteReferenceHandler.class,
            "handleSearchOrderExecuteReference");

        // 8) 株式ミニ投資買付注文確認リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkBuyConfirmRequest.class,
            WEB3MstkBuyHandler.class,
            "handleConfirmOrder");

        // 9) 株式ミニ投資買付注文完了リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkBuyCompleteRequest.class,
            WEB3MstkBuyHandler.class,
            "handleCompleteOrder");

        // 10) 株式ミニ投資買付注文入力リクエスト用ハンドラ
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkBuyInputRequest.class,
            WEB3MstkBuyHandler.class,
            "handleGetBuyInputScreen");

        // 11) 株式ミニ投資残高照会リクエスト用ハンドラ－
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkBalanceReferenceRequest.class,
            WEB3MstkBalanceReferenceHandler.class,
            "getBalanceReference");

        // 12) 株式ミニ投資残高合計リクエスト用ハンドラ－
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkBalanceReferenceTotalRequest.class,
            WEB3MstkBalanceReferenceHandler.class,
            "getBalanceTotal");

        // 13) 株式ミニ投資簿価単価登録入力画面リクエスト用ハンドラ－
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkBookPriceInputRequest.class,
            WEB3MstkBookValuePriceRegistHandler.class,
            "getInputScreen");

        // 14) 株式ミニ投資簿価単価登録リクエスト用ハンドラ－
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkBookPriceRegistRequest.class,
            WEB3MstkBookValuePriceRegistHandler.class,
            "completeBookValuePrice");


        // RAC-CTXインタセプタの設定
        // 1）現物株式出来通知一件サービス
        Services.addInterceptor(
            WEB3EquityOrderExecNotifyUnitService.class,
            new WEB3EquityDescendRacCtxInterceptor());
        // 2）信用取引出来通知一件サービス
        Services.addInterceptor(
            WEB3MarginOrderExecNotifyUnitService.class,
            new WEB3EquityDescendRacCtxInterceptor());
        // 3）株式失効通知一件サービス
        Services.addInterceptor(
            WEB3EquityReceiveCloseOrderUnitService.class,
            new WEB3EquityDescendRacCtxInterceptor());
        // 4）株式訂正取消受付一件サービス
        Services.addInterceptor(
            WEB3EquityChangeCancelAcceptUnitService.class,
            new WEB3EquityDescendRacCtxInterceptor());
        // 5）株式注文受付一件サービス
        Services.addInterceptor(
            WEB3EquityOrderAcceptUnitService.class,
            new WEB3EquityDescendRacCtxInterceptor());
        // 6）信用取引注文通知一件サービス
        Services.addInterceptor(
            WEB3MarginSwapOrderNotifyUnitService.class,
            new WEB3EquityDescendRacCtxInterceptor());
        // 7）信用取引現引現渡受付一件サービス
        Services.addInterceptor(
            WEB3MarginSwapMarginAcceptUnitService.class,
            new WEB3EquityDescendRacCtxInterceptor());
        // 8）株式注文通知一件サービス
        Services.addInterceptor(
            WEB3EquityOrderNotifyUnitService.class,
            new WEB3EquityDescendRacCtxInterceptor());
        // 9）信用取引注文通知一件サービス
        Services.addInterceptor(
            WEB3MarginOrderNotifyUnitService.class,
            new WEB3EquityDescendRacCtxInterceptor());
        // 10）信用取引訂正取消通知訂正一件サービス
        Services.addInterceptor(
            WEB3MarginChangeCancelNotifyChangeUnitService.class,
            new WEB3EquityDescendRacCtxInterceptor());
        // 11）信用取引訂正取消通知取消一件サービス
        Services.addInterceptor(
            WEB3MarginChangeCancelNotifyCancelUnitService.class,
            new WEB3EquityDescendRacCtxInterceptor());
        // 12）注文繰越一件サービス
        Services.addInterceptor(
            WEB3EquityOrderCarryOverUnitService.class,
            new WEB3EquityDescendRacCtxInterceptor());
        
        log.exiting(METHOD_NAME);
    }
}
@

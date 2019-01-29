head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-xbruito プラグインクラス(WEB3RuitoAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/18 王蘭芬 (中訊)  新規作成
                   2004/12/15 王蘭芬(中訊)残対応
*/
package webbroker3.xbruito;

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
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.data.WEB3RuitoSessionDatabaseExtensions;
import webbroker3.xbruito.handler.WEB3AdminRuitoTradeStopHandler;
import webbroker3.xbruito.handler.WEB3RuitoBuyOrderHandler;
import webbroker3.xbruito.handler.WEB3RuitoBuyOrderInputHandler;
import webbroker3.xbruito.handler.WEB3RuitoCancelAcceptedHandler;
import webbroker3.xbruito.handler.WEB3RuitoCancelHandler;
import webbroker3.xbruito.handler.WEB3RuitoMRFCancelAcceptHandler;
import webbroker3.xbruito.handler.WEB3RuitoMRFOrderAcceptHandler;
import webbroker3.xbruito.handler.WEB3RuitoOrderAcceptHandler;
import webbroker3.xbruito.handler.WEB3RuitoOrderReferenceHandler;
import webbroker3.xbruito.handler.WEB3RuitoSellHandler;
import webbroker3.xbruito.handler.WEB3RuitoSellInputHandler;
import webbroker3.xbruito.handler.WEB3RuitoSellPossibleListReferenceHandler;
import webbroker3.xbruito.handler.WEB3RuitoTradeOrderNotifyHandler;
import webbroker3.xbruito.marketadaptor.WEB3RuitoMarketAdaptorAppPlugin;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopCompleteRequest;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopCompleteResponse;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopConfirmRequest;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopConfirmResponse;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopInputRequest;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopInputResponse;
import webbroker3.xbruito.message.WEB3RuitoBuyCompleteRequest;
import webbroker3.xbruito.message.WEB3RuitoBuyCompleteResponse;
import webbroker3.xbruito.message.WEB3RuitoBuyConfirmRequest;
import webbroker3.xbruito.message.WEB3RuitoBuyConfirmResponse;
import webbroker3.xbruito.message.WEB3RuitoBuyInputRequest;
import webbroker3.xbruito.message.WEB3RuitoBuyInputResponse;
import webbroker3.xbruito.message.WEB3RuitoCancelAcceptRequest;
import webbroker3.xbruito.message.WEB3RuitoCancelAcceptResponse;
import webbroker3.xbruito.message.WEB3RuitoCancelCompleteRequest;
import webbroker3.xbruito.message.WEB3RuitoCancelCompleteResponse;
import webbroker3.xbruito.message.WEB3RuitoCancelConfirmRequest;
import webbroker3.xbruito.message.WEB3RuitoCancelConfirmResponse;
import webbroker3.xbruito.message.WEB3RuitoCommonRequest;
import webbroker3.xbruito.message.WEB3RuitoDealingOrderNotifyRequest;
import webbroker3.xbruito.message.WEB3RuitoDealingOrderNotifyResponse;
import webbroker3.xbruito.message.WEB3RuitoMRFCancelAcceptRequest;
import webbroker3.xbruito.message.WEB3RuitoMRFCancelAcceptResponse;
import webbroker3.xbruito.message.WEB3RuitoMRFOrderAcceptRequest;
import webbroker3.xbruito.message.WEB3RuitoMRFOrderAcceptResponse;
import webbroker3.xbruito.message.WEB3RuitoOrderAcceptRequest;
import webbroker3.xbruito.message.WEB3RuitoOrderAcceptResponse;
import webbroker3.xbruito.message.WEB3RuitoOrderReferenceRequest;
import webbroker3.xbruito.message.WEB3RuitoOrderReferenceResponse;
import webbroker3.xbruito.message.WEB3RuitoSellCompleteRequest;
import webbroker3.xbruito.message.WEB3RuitoSellCompleteResponse;
import webbroker3.xbruito.message.WEB3RuitoSellConfirmRequest;
import webbroker3.xbruito.message.WEB3RuitoSellConfirmResponse;
import webbroker3.xbruito.message.WEB3RuitoSellInputRequest;
import webbroker3.xbruito.message.WEB3RuitoSellInputResponse;
import webbroker3.xbruito.message.WEB3RuitoSellListRequest;
import webbroker3.xbruito.message.WEB3RuitoSellListResponse;
import webbroker3.xbruito.service.delegate.WEB3AdminRuitoTradeStopService;
import webbroker3.xbruito.service.delegate.WEB3RuitoBuyOrderInputService;
import webbroker3.xbruito.service.delegate.WEB3RuitoBuyOrderService;
import webbroker3.xbruito.service.delegate.WEB3RuitoCancelAcceptedService;
import webbroker3.xbruito.service.delegate.WEB3RuitoCancelAcceptedUnitService;
import webbroker3.xbruito.service.delegate.WEB3RuitoCancelService;
import webbroker3.xbruito.service.delegate.WEB3RuitoMRFCancelAcceptService;
import webbroker3.xbruito.service.delegate.WEB3RuitoMRFCancelAcceptUnitService;
import webbroker3.xbruito.service.delegate.WEB3RuitoMRFOrderAcceptService;
import webbroker3.xbruito.service.delegate.WEB3RuitoMRFOrderAcceptUnitService;
import webbroker3.xbruito.service.delegate.WEB3RuitoOrderAcceptService;
import webbroker3.xbruito.service.delegate.WEB3RuitoOrderAcceptUnitService;
import webbroker3.xbruito.service.delegate.WEB3RuitoOrderReferenceService;
import webbroker3.xbruito.service.delegate.WEB3RuitoSellInputService;
import webbroker3.xbruito.service.delegate.WEB3RuitoSellPossibleListReferenceService;
import webbroker3.xbruito.service.delegate.WEB3RuitoSellService;
import webbroker3.xbruito.service.delegate.WEB3RuitoTradeOrderNotifyService;
import webbroker3.xbruito.service.delegate.WEB3RuitoTradeOrderNotifyUnitService;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3AdminRuitoTradeStopServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoBuyOrderInputServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoBuyOrderServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoCancelAcceptedServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoCancelAcceptedUnitServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoCancelServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoMRFCancelAcceptServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoMRFCancelAcceptUnitServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoMRFOrderAcceptServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoMRFOrderAcceptUnitServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoOrderAcceptServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoOrderAcceptUnitServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoOrderReferenceServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoSellInputServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoSellPossibleListReferenceServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoSellServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoTradeOrderNotifyServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoTradeOrderNotifyUnitServiceImpl;
import webbroker3.mqgateway.WEB3MQGatewayInterceptor;

/**
 * Webbroker3-Ruito プラグインクラス。
 *
 * @@author 王蘭芬 (中訊)
 * @@version 1.0
 */
public final class WEB3RuitoAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3RuitoAppPlugin()
    {
        String METHOD_NAME = "WEB3RuitoAppPlugin()";
        log.entering(METHOD_NAME);

        log.exiting(METHOD_NAME);
    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);

        plug(WEB3RuitoAppPlugin.class);

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
            l_finApp.uninstallTradingModule("xb-ruito-pdbt");
        }
        catch (NotInstalledException l_ex)
        {
            log.debug(l_ex.getMessage());
        }

        try
        {
            log.debug("Installing TradingModule : web3-ruito");
            l_finApp.installTradingModule(new WEB3RuitoTradingModule());
            log.debug("Installed TradingModule : web3-ruito");
        }
        catch (AlreadyInstalledException l_ex)
        {
            log.debug(l_ex.getMessage());
        }

        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.RUITO);

        // 拡張プロダクト・マネージャー
        l_tradingModule.overrideProductManager(new WEB3RuitoProductManager());

        // 拡張注文マネージャ
        l_tradingModule.overrideOrderManager(new WEB3RuitoOrderManager());

        // ポジションマネージャ
        l_tradingModule.overridePositionManager(new WEB3RuitoPositionManager());

        // 累投発注審査個別チェック
        WEB3RuitoOrderManagerReusableValidationsCheck.register();

        // Webbroker3-Ruito-MarketAdaptor プラグイン
        WEB3RuitoMarketAdaptorAppPlugin.plug();


        // DatabaseExtensions のプラグイン処理 ----------------------
        WEB3RuitoSessionDatabaseExtensions.plug();

        // Service の登録処理 ----------------------

        // 累積投資買付注文入力サービスインタフェース
        Services.registerService(
            WEB3RuitoBuyOrderInputService.class,
            new WEB3RuitoBuyOrderInputServiceImpl());

        // 累積投資買付注文サービスインターフェイス
        Services.registerService(
            WEB3RuitoBuyOrderService.class,
            new WEB3RuitoBuyOrderServiceImpl());

        // 累積投資取消受付サービスインターフェイス
        Services.registerService(
            WEB3RuitoCancelAcceptedService.class,
            new WEB3RuitoCancelAcceptedServiceImpl());

        // 累投取消受付１件サービスインタフェース
        Services.registerService(
            WEB3RuitoCancelAcceptedUnitService.class,
            new WEB3RuitoCancelAcceptedUnitServiceImpl());

        // 累積投資取消サービスインターフェイス
        Services.registerService(
            WEB3RuitoCancelService.class,
            new WEB3RuitoCancelServiceImpl());

        // 累積投資MRF取消受付サービスインターフェイス
        Services.registerService(
            WEB3RuitoMRFCancelAcceptService.class,
            new WEB3RuitoMRFCancelAcceptServiceImpl());

        // 累投MRF取消受付１件サービスインタフェース
        Services.registerService(
            WEB3RuitoMRFCancelAcceptUnitService.class,
            new WEB3RuitoMRFCancelAcceptUnitServiceImpl());

        // 累積投資MRF注文受付サービスインターフェイス
        Services.registerService(
            WEB3RuitoMRFOrderAcceptService.class,
            new WEB3RuitoMRFOrderAcceptServiceImpl());

        // 累投MRF注文受付１件サービスインタフェース
        Services.registerService(
            WEB3RuitoMRFOrderAcceptUnitService.class,
            new WEB3RuitoMRFOrderAcceptUnitServiceImpl());

        // 累積投資注文受付サービスインターフェイス
        Services.registerService(
            WEB3RuitoOrderAcceptService.class,
            new WEB3RuitoOrderAcceptServiceImpl());

        // 累投注文受付１件サービスインタフェース
        Services.registerService(
            WEB3RuitoOrderAcceptUnitService.class,
            new WEB3RuitoOrderAcceptUnitServiceImpl());

        // 累積投資注文照会サービスインターフェイス
        Services.registerService(
            WEB3RuitoOrderReferenceService.class,
            new WEB3RuitoOrderReferenceServiceImpl());

        // 累投解約入力サービスインタフェース
        Services.registerService(
            WEB3RuitoSellInputService.class,
            new WEB3RuitoSellInputServiceImpl());

        // 累投解約可能一覧照会サービスインターフェイスクラス
        Services.registerService(
            WEB3RuitoSellPossibleListReferenceService.class,
            new WEB3RuitoSellPossibleListReferenceServiceImpl());

        // 累積投資解約サービスインターフェイス
        Services.registerService(
            WEB3RuitoSellService.class,
            new WEB3RuitoSellServiceImpl());

        // 累積投資売買注文通知サービスインターフェイス
        Services.registerService(
            WEB3RuitoTradeOrderNotifyService.class,
            new WEB3RuitoTradeOrderNotifyServiceImpl());

        // 累投売買注文通知１件サービスインタフェース
        Services.registerService(
            WEB3RuitoTradeOrderNotifyUnitService.class,
            new WEB3RuitoTradeOrderNotifyUnitServiceImpl());

        // 管理者累投銘柄別売買停止サービス
        Services.registerService(
            WEB3AdminRuitoTradeStopService.class,
            new WEB3AdminRuitoTradeStopServiceImpl());

        // Service.execute()呼び出し前後に  ----------------------
        // 処理開始時刻と処理終了時刻をログ出力するように設定する

        // 累積投資買付注文入力サービスインタフェース
        Services.addInterceptor(
            WEB3RuitoBuyOrderInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 累積投資買付注文サービスインターフェイス
        Services.addInterceptor(
            WEB3RuitoBuyOrderService.class,
            new WEB3LogSysTimeInterceptor());

        // 累積投資取消受付サービスインターフェイス
        Services.addInterceptor(
            WEB3RuitoCancelAcceptedService.class,
            new WEB3LogSysTimeInterceptor());

        // 累投取消受付１件サービスインタフェース
        Services.addInterceptor(
            WEB3RuitoCancelAcceptedUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 累積投資取消サービスインターフェイス
        Services.addInterceptor(
            WEB3RuitoCancelService.class,
            new WEB3LogSysTimeInterceptor());

        // 累積投資MRF取消受付サービスインターフェイス
        Services.addInterceptor(
            WEB3RuitoMRFCancelAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        // 累投MRF取消受付１件サービスインタフェース
        Services.addInterceptor(
            WEB3RuitoMRFCancelAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 累積投資MRF注文受付サービスインターフェイス
        Services.addInterceptor(
            WEB3RuitoMRFOrderAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        // 累投MRF注文受付１件サービスインタフェース
        Services.addInterceptor(
            WEB3RuitoMRFOrderAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 累積投資注文受付サービスインターフェイス
        Services.addInterceptor(
            WEB3RuitoOrderAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        // 累投注文受付１件サービスインタフェース
        Services.addInterceptor(
            WEB3RuitoOrderAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 累積投資注文照会サービスインターフェイス
        Services.addInterceptor(
            WEB3RuitoOrderReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // 累投解約入力サービスインタフェース
        Services.addInterceptor(
            WEB3RuitoSellInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 累投解約可能一覧照会サービスインターフェイスクラス
        Services.addInterceptor(
            WEB3RuitoSellPossibleListReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // 累積投資解約サービスインターフェイス
        Services.addInterceptor(
            WEB3RuitoSellService.class,
            new WEB3LogSysTimeInterceptor());

        // 累積投資売買注文通知サービスインターフェイス
        Services.addInterceptor(
            WEB3RuitoTradeOrderNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        // 累投売買注文通知１件サービスインタフェース
        Services.addInterceptor(
            WEB3RuitoTradeOrderNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 管理者累投銘柄別売買停止サービス ログインターフェイス
        Services.addInterceptor(
            WEB3AdminRuitoTradeStopService.class,
            new WEB3LogSysTimeInterceptor());

        //Service に ServiceInterceptor を設定する ----------------------

        // 累積投資買付注文入力サービスインタフェース
        Services.addInterceptor(
            WEB3RuitoBuyOrderInputService.class,
            new WEB3RuitoBuyOrderInputInterceptor());

        // 累積投資買付注文サービスインターフェイス
        Services.addInterceptor(
            WEB3RuitoBuyOrderService.class,
            new WEB3RuitoBuyOrderServiceInterceptor());

        // 累投取消受付１件サービスインタフェース
        Services.addInterceptor(
            WEB3RuitoCancelAcceptedUnitService.class,
            new WEB3RuitoCancelAcceptedUnitServiceInterceptor());

        // 累積投資取消サービスインターフェイス
        Services.addInterceptor(
            WEB3RuitoCancelService.class,
            new WEB3RuitoCancelServiceInterceptor());

        // 累投MRF取消受付１件サービスインタフェース
        Services.addInterceptor(
            WEB3RuitoMRFCancelAcceptUnitService.class,
            new WEB3RuitoMRFCancelAcceptUnitServiceInterceptor());

        // 累投MRF注文受付１件サービスインタフェース
        Services.addInterceptor(
            WEB3RuitoMRFOrderAcceptUnitService.class,
            new WEB3RuitoMRFOrderAcceptUnitServiceInterceptor());

        // 累投注文受付１件サービスインタフェース
        Services.addInterceptor(
            WEB3RuitoOrderAcceptUnitService.class,
            new WEB3RuitoOrderAcceptUnitServiceInterceptor());

        // 累積投資注文照会サービスインターフェイス
        Services.addInterceptor(
            WEB3RuitoOrderReferenceService.class,
            new WEB3RuitoOrderReferenceServiceInterceptor());

        // 累投解約入力サービスインタフェース
        Services.addInterceptor(
            WEB3RuitoSellInputService.class,
            new WEB3RuitoSellInputInterceptor());

        // 累投解約可能一覧照会サービスインターフェイスクラス
        Services.addInterceptor(
            WEB3RuitoSellPossibleListReferenceService.class,
            new WEB3RuitoSellPossibleListReferenceInterceptor());

        // 累積投資解約サービスインターフェイス
        Services.addInterceptor(
            WEB3RuitoSellService.class,
            new WEB3RuitoSellServiceInterceptor());

        // 累投売買注文通知１件サービスインタフェース
        Services.addInterceptor(
            WEB3RuitoTradeOrderNotifyUnitService.class,
            new WEB3RuitoTradeOrderNotifyUnitServiceInterceptor());

        // 管理者累投銘柄別売買停止サービスインタセプタ
        Services.addInterceptor(
            WEB3AdminRuitoTradeStopService.class,
            new WEB3AdminRuitoTradeStopInterceptor());

        // Service の Interceptor 設定処理 ----------------------
        // 自動トランザクション設定

        // 累積投資買付注文入力サービスインタフェース
        Services.addInterceptor(
            WEB3RuitoBuyOrderInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 累積投資買付注文サービスインターフェイス
        Services.addInterceptor(
            WEB3RuitoBuyOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 累投取消受付１件サービスインタフェース
        Services.addInterceptor(
            WEB3RuitoCancelAcceptedUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 累積投資取消サービスインターフェイス
        Services.addInterceptor(
            WEB3RuitoCancelService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 累投MRF取消受付１件サービスインタフェース
        Services.addInterceptor(
            WEB3RuitoMRFCancelAcceptUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 累投MRF注文受付１件サービスインタフェース
        Services.addInterceptor(
            WEB3RuitoMRFOrderAcceptUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 累投注文受付１件サービスインタフェース
        Services.addInterceptor(
            WEB3RuitoOrderAcceptUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 累積投資注文照会サービスインターフェイス
        Services.addInterceptor(
            WEB3RuitoOrderReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 累投解約入力サービスインタフェース
        Services.addInterceptor(
            WEB3RuitoSellInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 累投解約可能一覧照会サービスインターフェイスクラス
        Services.addInterceptor(
            WEB3RuitoSellPossibleListReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 累積投資解約サービスインターフェイス
        Services.addInterceptor(
            WEB3RuitoSellService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 累投売買注文通知１件サービスインタフェース
        Services.addInterceptor(
            WEB3RuitoTradeOrderNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 管理者累投銘柄別売買停止サービス
        Services.addInterceptor(
            WEB3AdminRuitoTradeStopService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // MQ-Gateway　@Interceptor 設定処理-----------------------

        // 累積投資買付サービス
        Services.addInterceptor(
            WEB3RuitoBuyOrderService.class,
            new WEB3MQGatewayInterceptor());

        // 累積投資解約サービス
        Services.addInterceptor(
            WEB3RuitoSellService.class,
            new WEB3MQGatewayInterceptor());

        // 累積投資取消サービス
        Services.addInterceptor(
            WEB3RuitoCancelService.class,
            new WEB3MQGatewayInterceptor());

        // 累積投資MRF注文受付１件サービス
        Services.addInterceptor(
            WEB3RuitoMRFOrderAcceptUnitService.class,
            new WEB3MQGatewayInterceptor());

        // 累積投資注文受付１件サービス
        Services.addInterceptor(
            WEB3RuitoOrderAcceptUnitService.class,
            new WEB3MQGatewayInterceptor());

        // 累積投資取消受付１件サービス
        Services.addInterceptor(
            WEB3RuitoCancelAcceptedUnitService.class,
            new WEB3MQGatewayInterceptor());

        // Message の登録処理 ----------------------

        // 累積投資買付注文完了リクエスト
        regClass(WEB3RuitoBuyCompleteRequest.class);
        // 累投買付注文完了レスポンス
        regClass(WEB3RuitoBuyCompleteResponse.class);

        // 累積投資買付注文確認リクエスト
        regClass(WEB3RuitoBuyConfirmRequest.class);
        // 累積投資買付注文確認レスポンスクラス
        regClass(WEB3RuitoBuyConfirmResponse.class);

        // 累投注文入力リクエストクラス
        regClass(WEB3RuitoBuyInputRequest.class);
        // 累投注文入力レスポンスクラス
        regClass(WEB3RuitoBuyInputResponse.class);

        // 累積投資取消受付リクエスト
        regClass(WEB3RuitoCancelAcceptRequest.class);
        // 累積投資取消受付レスポンス
        regClass(WEB3RuitoCancelAcceptResponse.class);

        // 累積投資取消完了リクエスト
        regClass(WEB3RuitoCancelCompleteRequest.class);
        // 累積投資取消完了レスポンス
        regClass(WEB3RuitoCancelCompleteResponse.class);

        // 累積投資取消確認リクエスト
        regClass(WEB3RuitoCancelConfirmRequest.class);
        // 累積投資取消確認レスポンス
        regClass(WEB3RuitoCancelConfirmResponse.class);

        // 累積投資共通リクエスト
        regClass(WEB3RuitoCommonRequest.class);

        // 累積投資売買注文通知リクエスト
        regClass(WEB3RuitoDealingOrderNotifyRequest.class);
        // 累積投資売買注文通知レスポンス
        regClass(WEB3RuitoDealingOrderNotifyResponse.class);

        // 累積投資MRF取消受付リクエスト
        regClass(WEB3RuitoMRFCancelAcceptRequest.class);
        // 累積投資MRF取消受付レスポンス
        regClass(WEB3RuitoMRFCancelAcceptResponse.class);

        // 累積投資MRF注文受付リクエスト
        regClass(WEB3RuitoMRFOrderAcceptRequest.class);
        // 累積投資MRF注文受付レスポンス
        regClass(WEB3RuitoMRFOrderAcceptResponse.class);

        // 累積投資注文受付リクエスト
        regClass(WEB3RuitoOrderAcceptRequest.class);
        // 累積投資注文受付レスポンス
        regClass(WEB3RuitoOrderAcceptResponse.class);

        // 累投注文照会リクエスト
        regClass(WEB3RuitoOrderReferenceRequest.class);
        // 累投注文照会レスポンス
        regClass(WEB3RuitoOrderReferenceResponse.class);

        // 累投解約完了リクエスト
        regClass(WEB3RuitoSellCompleteRequest.class);
        // 累投解約完了レスポンス
        regClass(WEB3RuitoSellCompleteResponse.class);

        // 累投解約確認リクエスト
        regClass(WEB3RuitoSellConfirmRequest.class);
        // 累投解約確認レスポンス
        regClass(WEB3RuitoSellConfirmResponse.class);

        // 累投解約入力リクエスト
        regClass(WEB3RuitoSellInputRequest.class);
        // 累投解約入力レスポンス
        regClass(WEB3RuitoSellInputResponse.class);

        // 累投解約一覧リクエスト
        regClass(WEB3RuitoSellListRequest.class);
        // 累投解約一覧レスポンス
        regClass(WEB3RuitoSellListResponse.class);

        //累投銘柄別売買停止確認リクエスト
        regClass(WEB3AdminRuitoTradeStopConfirmRequest.class);
        //累投銘柄別売買停止確認レスポンス
        regClass(WEB3AdminRuitoTradeStopConfirmResponse.class);
        //累投銘柄別売買停止完了リクエスト
        regClass(WEB3AdminRuitoTradeStopCompleteRequest.class);
        //累投銘柄別売買停止完了レスポンス
        regClass(WEB3AdminRuitoTradeStopCompleteResponse.class);
        //累投銘柄別売買停止入力画面リクエスト
        regClass(WEB3AdminRuitoTradeStopInputRequest.class);
        //累投銘柄別売買停止入力画面レスポンス
        regClass(WEB3AdminRuitoTradeStopInputResponse.class);


        //Handler の登録処理 ----------------------

        // 累積投資買付注文ハンドの登録
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoBuyConfirmRequest.class,
            WEB3RuitoBuyOrderHandler.class,
            "confirmBuyOrder");
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoBuyCompleteRequest.class,
            WEB3RuitoBuyOrderHandler.class,
            "submitBuyOrder");

        // 累積投資買付注文入力ハンドラの登録
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoBuyInputRequest.class,
            WEB3RuitoBuyOrderInputHandler.class,
            "ruitoBuyInputRequest");

        // 累積投資取消受付ハンドラーの登録
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoCancelAcceptRequest.class,
            WEB3RuitoCancelAcceptedHandler.class,
            "cancelAcceptedRequest");

        // 累積投資取消 用ハンドラーの登録
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoCancelConfirmRequest.class,
            WEB3RuitoCancelHandler.class,
            "confirmCancel");
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoCancelCompleteRequest.class,
            WEB3RuitoCancelHandler.class,
            "completeCancel");

        // 累積投資MRF取消受付 用ハンドラーの登録
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoMRFCancelAcceptRequest.class,
            WEB3RuitoMRFCancelAcceptHandler.class,
            "mrfCancelAcceptRequest");

        // 累積投資MRF注文受付 用ハンドラーの登録
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoMRFOrderAcceptRequest.class,
            WEB3RuitoMRFOrderAcceptHandler.class,
            "mrfOrderAcceptRequest");

        // 累積投資注文受付 用ハンドラーの登録
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoOrderAcceptRequest.class,
            WEB3RuitoOrderAcceptHandler.class,
            "orderAcceptRequest");

        // 累積投資注文照会 用ハンドラーの登録
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoOrderReferenceRequest.class,
            WEB3RuitoOrderReferenceHandler.class,
            "ruitoOrderReferenceRequest");

        // 累積投資解約 用ハンドラーの登録
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoSellConfirmRequest.class,
            WEB3RuitoSellHandler.class,
            "confirmSell");
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoSellCompleteRequest.class,
            WEB3RuitoSellHandler.class,
            "completeSell");

        // 累投解約入力 用ハンドラーの登録
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoSellInputRequest.class,
            WEB3RuitoSellInputHandler.class,
            "sellInputRequest");

        // 累積投資解約一覧 用ハンドラーの登録
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoSellListRequest.class,
            WEB3RuitoSellPossibleListReferenceHandler.class,
            "searchRuitoSellPossibleListReference");

        // 累積投資売買注文通知 用ハンドラーの登録
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoDealingOrderNotifyRequest.class,
            WEB3RuitoTradeOrderNotifyHandler.class,
            "tradeOrderNotifyRequest");

        // 管理者累投銘柄別売買停止ハンドラの登録
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3AdminRuitoTradeStopInputRequest.class,
            WEB3AdminRuitoTradeStopHandler.class,
            "handleGetInputScreen");

        // 管理者累投銘柄別売買停止ハンドラの登録
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3AdminRuitoTradeStopConfirmRequest.class,
            WEB3AdminRuitoTradeStopHandler.class,
            "handleComfirmTradeStop");

        // 管理者累投銘柄別売買停止ハンドラの登録
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3AdminRuitoTradeStopCompleteRequest.class,
            WEB3AdminRuitoTradeStopHandler.class,
            "handleCompleteTradeStop");

        // 李志強 U01306の暫定対応 start
        //------------------------------------
        // RAC-CTXインタセプタを設定する
        //------------------------------------
        // 累積投資注文受付一件
        Services.addInterceptor(
            WEB3RuitoOrderAcceptUnitService.class,
            new WEB3RuitoDescendRacCtxInterceptor());

        // 累積投資取消受付一件
        Services.addInterceptor(
            WEB3RuitoCancelAcceptedUnitService.class,
            new WEB3RuitoDescendRacCtxInterceptor());

        // 累積投資売買注文通知一件
        Services.addInterceptor(
            WEB3RuitoTradeOrderNotifyUnitService.class,
            new WEB3RuitoDescendRacCtxInterceptor());

        // ＭＲＦ注文受付一件
        Services.addInterceptor(
            WEB3RuitoMRFOrderAcceptUnitService.class,
            new WEB3RuitoDescendRacCtxInterceptor());

        // ＭＲＦ取消受付一件
        Services.addInterceptor(
            WEB3RuitoMRFCancelAcceptUnitService.class,
            new WEB3RuitoDescendRacCtxInterceptor());

        log.exiting(METHOD_NAME);

    }
}

@

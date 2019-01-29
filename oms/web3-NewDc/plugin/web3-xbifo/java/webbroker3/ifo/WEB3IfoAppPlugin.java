head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-Ifo プラグインクラス(WEB3IfoAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15 張宝楠 (中訊) 新規作成
Revesion History : 2004/07/27 王暁傑 (中訊) WEB3ProtoQuotePlugin.onPlug()を追加
Revesion History : 2004/07/29 王暁傑 (中訊) WEB3OptionOrderCarryOverUnitServiceInterceptorのPlugを追加
Revesion History : 2004/07/29 王暁傑 (中訊) 対応バッグ　@WEB3_IFO_UT-000069
Revesion History : 2004/07/29 王暁傑 (中訊) 対応バッグ　@WEB3_IFO_UT-000101'
Revesion History : 2007/02/01 徐宏偉 (中訊) モデルNo.587
Revision History : 2007/06/22 趙林鵬 (中訊) モデルNo.669
Revesion History : 2008/04/11 趙林鵬 (中訊) モデルNo.846
*/

package webbroker3.ifo;

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

import webbroker3.ifo.data.WEB3IfoMasterDatabaseExtensions;
import webbroker3.ifo.data.WEB3IfoSessionDatabaseExtensions;
import webbroker3.ifo.handler.WEB3FuturesCancelOrderHandler;
import webbroker3.ifo.handler.WEB3FuturesChangeCancelNotifyHandler;
import webbroker3.ifo.handler.WEB3FuturesChangeClosingContractHandler;
import webbroker3.ifo.handler.WEB3FuturesChangeClosingContractInputHandler;
import webbroker3.ifo.handler.WEB3FuturesChangeOpenContractHandler;
import webbroker3.ifo.handler.WEB3FuturesChangeOpenContractInputHandler;
import webbroker3.ifo.handler.WEB3FuturesContractInquiryHandler;
import webbroker3.ifo.handler.WEB3FuturesExecuteReferenceHandler;
import webbroker3.ifo.handler.WEB3FuturesIndividualSettleContractListHandler;
import webbroker3.ifo.handler.WEB3FuturesOpenContractHandler;
import webbroker3.ifo.handler.WEB3FuturesOpenContractInputHandler;
import webbroker3.ifo.handler.WEB3FuturesOrderExecNotifyHandler;
import webbroker3.ifo.handler.WEB3FuturesOrderNotifyHandler;
import webbroker3.ifo.handler.WEB3FuturesSettleContractInputHandler;
import webbroker3.ifo.handler.WEB3FuturesSettleContractOrderHandler;
import webbroker3.ifo.handler.WEB3IfoBalanceReferenceHandler;
import webbroker3.ifo.handler.WEB3IfoChangeCancelOrderAcceptHandler;
import webbroker3.ifo.handler.WEB3IfoCloseNotifyHandler;
import webbroker3.ifo.handler.WEB3IfoExecuteEndNotifyHandler;
import webbroker3.ifo.handler.WEB3IfoOrderAcceptHandler;
import webbroker3.ifo.handler.WEB3OptionCancelOrderHandler;
import webbroker3.ifo.handler.WEB3OptionChangeCancelNotifyHandler;
import webbroker3.ifo.handler.WEB3OptionChangeClosingContractHandler;
import webbroker3.ifo.handler.WEB3OptionChangeClosingContractInputHandler;
import webbroker3.ifo.handler.WEB3OptionChangeOpenContractHandler;
import webbroker3.ifo.handler.WEB3OptionChangeOpenContractInputHandler;
import webbroker3.ifo.handler.WEB3OptionContractInquiryHandler;
import webbroker3.ifo.handler.WEB3OptionIndividualSettleContractListServiceHandler;
import webbroker3.ifo.handler.WEB3OptionOpenContractInputHandler;
import webbroker3.ifo.handler.WEB3OptionOpenContractOrderHandler;
import webbroker3.ifo.handler.WEB3OptionOrderExecNotifyHandler;
import webbroker3.ifo.handler.WEB3OptionOrderExecutedInquiryHandler;
import webbroker3.ifo.handler.WEB3OptionSettleContractHandler;
import webbroker3.ifo.handler.WEB3OptionSettleContractInputServiceHandler;
import webbroker3.ifo.handler.WEB3OptionsOrderNotifyHandler;
import webbroker3.ifo.marketadaptor.WEB3IfoMarketAdaptorAppPlugin;
import webbroker3.ifo.message.*;
import webbroker3.ifo.service.delegate.WEB3FuturesCancelOrderService;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeCancelNotifyService;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeCancelNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeClosingContractInputService;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeClosingContractService;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeOpenContractInputService;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeOpenContractService;
import webbroker3.ifo.service.delegate.WEB3FuturesContractInquiryService;
import webbroker3.ifo.service.delegate.WEB3FuturesExecuteReferenceService;
import webbroker3.ifo.service.delegate.WEB3FuturesIndividualSettleContractListService;
import webbroker3.ifo.service.delegate.WEB3FuturesOpenContractInputService;
import webbroker3.ifo.service.delegate.WEB3FuturesOpenContractService;
import webbroker3.ifo.service.delegate.WEB3FuturesOrderExecNotifyService;
import webbroker3.ifo.service.delegate.WEB3FuturesOrderExecNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3FuturesOrderNotifyService;
import webbroker3.ifo.service.delegate.WEB3FuturesOrderNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractInputService;
import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractOrderService;
import webbroker3.ifo.service.delegate.WEB3IfoBalanceReferenceService;
import webbroker3.ifo.service.delegate.WEB3IfoChangeCancelOrderAcceptService;
import webbroker3.ifo.service.delegate.WEB3IfoChangeCancelOrderAcceptUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoCloseNotifyService;
import webbroker3.ifo.service.delegate.WEB3IfoCloseNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoExecuteEndNotifyService;
import webbroker3.ifo.service.delegate.WEB3IfoExecuteEndNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoExecutedMailSendService;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.ifo.service.delegate.WEB3IfoOrderAcceptService;
import webbroker3.ifo.service.delegate.WEB3IfoOrderAcceptUnitService;
import webbroker3.ifo.service.delegate.WEB3OptionCancelOrderService;
import webbroker3.ifo.service.delegate.WEB3OptionChangeCancelNotifyService;
import webbroker3.ifo.service.delegate.WEB3OptionChangeCancelNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3OptionChangeClosingContractInputService;
import webbroker3.ifo.service.delegate.WEB3OptionChangeClosingContractService;
import webbroker3.ifo.service.delegate.WEB3OptionChangeOpenContractInputService;
import webbroker3.ifo.service.delegate.WEB3OptionChangeOpenContractService;
import webbroker3.ifo.service.delegate.WEB3OptionContractInquiryService;
import webbroker3.ifo.service.delegate.WEB3OptionIndividualSettleContractListService;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractInputService;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractOrderService;
import webbroker3.ifo.service.delegate.WEB3OptionOrderExecNotifyService;
import webbroker3.ifo.service.delegate.WEB3OptionOrderExecNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3OptionOrderExecutedInquiryService;
import webbroker3.ifo.service.delegate.WEB3OptionSettleContractInputService;
import webbroker3.ifo.service.delegate.WEB3OptionSettleContractOrderService;
import webbroker3.ifo.service.delegate.WEB3OptionsOrderNotifyService;
import webbroker3.ifo.service.delegate.WEB3OptionsOrderNotifyUnitService;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesCancelOrderServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeCancelNotifyServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeCancelNotifyUnitServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeClosingContractInputServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeClosingContractServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeOpenContractInputServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeOpenContractServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesContractInquiryServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesExecuteReferenceServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesIndividualSettleContractListServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOpenContractInputServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOpenContractServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderExecNotifyServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderExecNotifyUnitServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderNotifyServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderNotifyUnitServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesSettleContractInputServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesSettleContractOrderServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoBalanceReferenceServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoChangeCancelOrderAcceptServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoChangeCancelOrderAcceptUnitServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoExecuteEndNotifyServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoExecuteEndNotifyUnitServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoExecutedMailSendServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptUnitServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionCancelOrderServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeCancelNotifyServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeCancelNotifyUnitServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeClosingContractInputServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeClosingContractServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeOpenContractInputServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeOpenContractServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionContractInquiryServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionIndividualSettleContractListServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOpenContractInputServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOpenContractOrderServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecNotifyServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecNotifyUnitServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecutedInquiryServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionSettleContractInputServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionSettleContractOrderServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionsOrderNotifyServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionsOrderNotifyUnitServiceImpl;
import webbroker3.mqgateway.WEB3MQGatewayInterceptor;


/**
 * Webbroker3-Ifo プラグインクラス。
 *
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public final class WEB3IfoAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3IfoAppPlugin()
    {
        String METHOD_NAME = "WEB3IfoAppPlugin()";
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

        plug(WEB3IfoAppPlugin.class);

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
        KernelPlugin.plug();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        // 拡張トランザクション・マネージャーは
        // オーバーライドメソッドが無いため拡張取引モジュールクラスを作成し
        // 拡張取引モジュールクラス内で設定
        try
        {
            l_finApp.uninstallTradingModule("xb-ifo-pdbt");
        }
        catch (NotInstalledException l_ex)
        {
            log.debug(l_ex.getMessage());
        }

        try
        {
            log.debug("Installing TradingModule : web3-xbifo");
            l_finApp.installTradingModule(new WEB3IfoTradingModule());
            log.debug("Installed TradingModule : web3-xbifo");
        }
        catch (AlreadyInstalledException l_ex)
        {
            log.debug(l_ex.getMessage());
        }

        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        // 拡張プロダクト・マネージャー
        l_tradingModule.overrideProductManager(new WEB3IfoProductManagerImpl());

        // 計算サービスクラス
        l_tradingModule.overrideBizLogicProvider(new WEB3IfoBizLogicProvider());

        // 拡張注文マネージャ
        l_tradingModule.overrideOrderManager(new WEB3FuturesOrderManagerImpl());

        // ポジションマネージャ
        l_tradingModule.overridePositionManager(new WEB3IfoPositionManagerImpl());

        // 株式発注審査個別チェック
        new WEB3IfoOrderManagerReusableValidations().register();

        // Adapterの登録 --------------------
        // 市場リクエスト送信サービス
        log.debug("XBIFO: 市場Adapterの登録");
        WEB3IfoMarketAdaptorAppPlugin.plug();

        // DatabaseExtensions のプラグイン処理 ----------------------
        WEB3IfoMasterDatabaseExtensions.plug();
        WEB3IfoSessionDatabaseExtensions.plug();

        //---------------------- 1 Service の登録処理 ----------------------
        //---先物OP共通サービス---
        //先物OP注文受付サービス
        Services.registerService(
            WEB3IfoOrderAcceptService.class,
            new WEB3IfoOrderAcceptServiceImpl());

        //先物OP注文受付一件サービス
        Services.registerService(
            WEB3IfoOrderAcceptUnitService.class,
            new WEB3IfoOrderAcceptUnitServiceImpl());

        //先物OP訂正取消受付サービス
        Services.registerService(
            WEB3IfoChangeCancelOrderAcceptService.class,
            new WEB3IfoChangeCancelOrderAcceptServiceImpl());

        //先物OP訂正取消受付一件サービス
        Services.registerService(
            WEB3IfoChangeCancelOrderAcceptUnitService.class,
            new WEB3IfoChangeCancelOrderAcceptUnitServiceImpl());

        //先物OP失効通知サービス
        Services.registerService(
            WEB3IfoCloseNotifyService.class,
            new WEB3IfoCloseNotifyServiceImpl());

        //先物OP失効通知一件サービス
        Services.registerService(
            WEB3IfoCloseNotifyUnitService.class,
            new WEB3IfoCloseNotifyUnitServiceImpl());

        //先物OP出来終了通知サービス
        Services.registerService(
            WEB3IfoExecuteEndNotifyService.class,
            new WEB3IfoExecuteEndNotifyServiceImpl());

        //先物OP出来終了通知一件サービス
        Services.registerService(
            WEB3IfoExecuteEndNotifyUnitService.class,
            new WEB3IfoExecuteEndNotifyUnitServiceImpl());

        //先物OP約定メール送信サービス
        Services.registerService(
            WEB3IfoExecutedMailSendService.class,
            new WEB3IfoExecutedMailSendServiceImpl());

        //先物OP残高照会サービス
        Services.registerService(
            WEB3IfoBalanceReferenceService.class,
            new WEB3IfoBalanceReferenceServiceImpl());

        //先物OP発注サービス
        Services.registerService(
            WEB3IfoFrontOrderService.class,
            new WEB3IfoFrontOrderServiceImpl());

        //---OPサービス---
        //OP出来通知サービス
        Services.registerService(
            WEB3OptionOrderExecNotifyService.class,
            new WEB3OptionOrderExecNotifyServiceImpl());

        //OP出来通知一件サービス
        Services.registerService(
            WEB3OptionOrderExecNotifyUnitService.class,
            new WEB3OptionOrderExecNotifyUnitServiceImpl());

        //OP訂正取消通知サービス
        Services.registerService(
            WEB3OptionChangeCancelNotifyService.class,
            new WEB3OptionChangeCancelNotifyServiceImpl());

        //OP訂正取消通知一件サービス
        Services.registerService(
            WEB3OptionChangeCancelNotifyUnitService.class,
            new WEB3OptionChangeCancelNotifyUnitServiceImpl());

        //OP注文通知サービス
        Services.registerService(
            WEB3OptionsOrderNotifyService.class,
            new WEB3OptionsOrderNotifyServiceImpl());

        //OP注文通知一件サービス
        Services.registerService(
            WEB3OptionsOrderNotifyUnitService.class,
            new WEB3OptionsOrderNotifyUnitServiceImpl());

        //OP新規建入力サービス
        Services.registerService(
            WEB3OptionOpenContractInputService.class,
            new WEB3OptionOpenContractInputServiceImpl());

        //OP新規建注文サービス
        Services.registerService(
            WEB3OptionOpenContractOrderService.class,
            new WEB3OptionOpenContractOrderServiceImpl());

        //OP訂正新規建入力サービス
        Services.registerService(
            WEB3OptionChangeOpenContractInputService.class,
            new WEB3OptionChangeOpenContractInputServiceImpl());

        //OP訂正新規建サービス
        Services.registerService(
            WEB3OptionChangeOpenContractService.class,
            new WEB3OptionChangeOpenContractServiceImpl());

        //OP個別返済一覧表示サービス
        Services.registerService(
            WEB3OptionIndividualSettleContractListService.class,
            new WEB3OptionIndividualSettleContractListServiceImpl());

        //OP返済入力サービス
        Services.registerService(
            WEB3OptionSettleContractInputService.class,
            new WEB3OptionSettleContractInputServiceImpl());

        //OP返済注文サービス
        Services.registerService(
            WEB3OptionSettleContractOrderService.class,
            new WEB3OptionSettleContractOrderServiceImpl());

        //OP訂正返済入力サービス
        Services.registerService(
            WEB3OptionChangeClosingContractInputService.class,
            new WEB3OptionChangeClosingContractInputServiceImpl());

        //OP訂正返済サービス
        Services.registerService(
            WEB3OptionChangeClosingContractService.class,
            new WEB3OptionChangeClosingContractServiceImpl());

        //OP取消注文サービス
        Services.registerService(
            WEB3OptionCancelOrderService.class,
            new WEB3OptionCancelOrderServiceImpl());

        //OP注文約定照会サービス
        Services.registerService(
            WEB3OptionOrderExecutedInquiryService.class,
            new WEB3OptionOrderExecutedInquiryServiceImpl());

        //OP建玉照会サービス
        Services.registerService(
            WEB3OptionContractInquiryService.class,
            new WEB3OptionContractInquiryServiceImpl());

        //---先物サービス---
        //先物出来通知サービス
        Services.registerService(
            WEB3FuturesOrderExecNotifyService.class,
            new WEB3FuturesOrderExecNotifyServiceImpl());

        //先物出来通知一件サービス
        Services.registerService(
            WEB3FuturesOrderExecNotifyUnitService.class,
            new WEB3FuturesOrderExecNotifyUnitServiceImpl());

        //先物訂正取消通知サービス
        Services.registerService(
            WEB3FuturesChangeCancelNotifyService.class,
            new WEB3FuturesChangeCancelNotifyServiceImpl());

        //先物訂正取消通知一件サービス
        Services.registerService(
            WEB3FuturesChangeCancelNotifyUnitService.class,
            new WEB3FuturesChangeCancelNotifyUnitServiceImpl());

        //先物注文通知サービス
        Services.registerService(
            WEB3FuturesOrderNotifyService.class,
            new WEB3FuturesOrderNotifyServiceImpl());

        //先物注文通知一件サービス
        Services.registerService(
            WEB3FuturesOrderNotifyUnitService.class,
            new WEB3FuturesOrderNotifyUnitServiceImpl());

        //先物新規建入力サービス
        Services.registerService(
            WEB3FuturesOpenContractInputService.class,
            new WEB3FuturesOpenContractInputServiceImpl());

        //先物新規建注文サービス
        Services.registerService(
            WEB3FuturesOpenContractService.class,
            new WEB3FuturesOpenContractServiceImpl());

        //先物訂正新規建入力サービス
        Services.registerService(
            WEB3FuturesChangeOpenContractInputService.class,
            new WEB3FuturesChangeOpenContractInputServiceImpl());

        //先物訂正新規建サービス
        Services.registerService(
            WEB3FuturesChangeOpenContractService.class,
            new WEB3FuturesChangeOpenContractServiceImpl());

        //先物個別返済一覧表示サービス
        Services.registerService(
            WEB3FuturesIndividualSettleContractListService.class,
            new WEB3FuturesIndividualSettleContractListServiceImpl());

        //先物返済入力サービス
        Services.registerService(
            WEB3FuturesSettleContractInputService.class,
            new WEB3FuturesSettleContractInputServiceImpl());

        //先物返済注文サービス
        Services.registerService(
            WEB3FuturesSettleContractOrderService.class,
            new WEB3FuturesSettleContractOrderServiceImpl());

        //先物訂正返済入力サービス
        Services.registerService(
            WEB3FuturesChangeClosingContractInputService.class,
            new WEB3FuturesChangeClosingContractInputServiceImpl());

        //先物訂正返済サービス
        Services.registerService(
            WEB3FuturesChangeClosingContractService.class,
            new WEB3FuturesChangeClosingContractServiceImpl());

        //先物取消注文サービス
        Services.registerService(
            WEB3FuturesCancelOrderService.class,
            new WEB3FuturesCancelOrderServiceImpl());

        //先物注文約定照会サービス
        Services.registerService(
            WEB3FuturesExecuteReferenceService.class,
            new WEB3FuturesExecuteReferenceServiceImpl());

        //先物建玉照会サービス
        Services.registerService(
            WEB3FuturesContractInquiryService.class,
            new WEB3FuturesContractInquiryServiceImpl());

        //---------------------- 2 ログ出力設定処理 ----------------------
        //(Service.execute()呼び出し前後に処理開始時刻と処理終了時刻をログ出力する)

        //---先物OP共通サービス---
        //先物OP注文受付サービス
        Services.addInterceptor(
            WEB3IfoOrderAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        //先物OP注文受付一件サービス
        Services.addInterceptor(
            WEB3IfoOrderAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //先物OP訂正取消受付サービス
        Services.addInterceptor(
            WEB3IfoChangeCancelOrderAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        //先物OP訂正取消受付一件サービス
        Services.addInterceptor(
            WEB3IfoChangeCancelOrderAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //先物OP失効通知サービス
        Services.addInterceptor(
            WEB3IfoCloseNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        //先物OP失効通知一件サービス
        Services.addInterceptor(
            WEB3IfoCloseNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //先物OP出来終了通知サービス
        Services.addInterceptor(
            WEB3IfoExecuteEndNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        //先物OP出来終了通知一件サービス
        Services.addInterceptor(
            WEB3IfoExecuteEndNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //先物OP約定メール送信サービス
        Services.addInterceptor(
            WEB3IfoExecutedMailSendService.class,
            new WEB3LogSysTimeInterceptor());

        //先物OP残高照会サービス
        Services.addInterceptor(
            WEB3IfoBalanceReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        //---OPサービス---
        //OP出来通知サービス
        Services.addInterceptor(
            WEB3OptionOrderExecNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        //OP出来通知一件サービス
        Services.addInterceptor(
            WEB3OptionOrderExecNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //OP訂正取消通知サービス
        Services.addInterceptor(
            WEB3OptionChangeCancelNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        //OP訂正取消通知一件サービス
        Services.addInterceptor(
            WEB3OptionChangeCancelNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //OP注文通知サービス
        Services.addInterceptor(
            WEB3OptionsOrderNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        //OP注文通知一件サービス
        Services.addInterceptor(
            WEB3OptionsOrderNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //OP新規建入力サービス
        Services.addInterceptor(
            WEB3OptionOpenContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //OP新規建注文サービス
        Services.addInterceptor(
            WEB3OptionOpenContractOrderService.class,
            new WEB3LogSysTimeInterceptor());

        //OP訂正新規建入力サービス
        Services.addInterceptor(
            WEB3OptionChangeOpenContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //OP訂正新規建サービス
        Services.addInterceptor(
            WEB3OptionChangeOpenContractService.class,
            new WEB3LogSysTimeInterceptor());

        //OP個別返済一覧表示サービス
        Services.addInterceptor(
            WEB3OptionIndividualSettleContractListService.class,
            new WEB3LogSysTimeInterceptor());

        //OP返済入力サービス
        Services.addInterceptor(
            WEB3OptionSettleContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //OP返済注文サービス
        Services.addInterceptor(
            WEB3OptionSettleContractOrderService.class,
            new WEB3LogSysTimeInterceptor());

        //OP訂正返済入力サービス
        Services.addInterceptor(
            WEB3OptionChangeClosingContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //OP訂正返済サービス
        Services.addInterceptor(
            WEB3OptionChangeClosingContractService.class,
            new WEB3LogSysTimeInterceptor());

        //OP取消注文サービス
        Services.addInterceptor(
            WEB3OptionCancelOrderService.class,
            new WEB3LogSysTimeInterceptor());

        //OP注文約定照会サービス
        Services.addInterceptor(
            WEB3OptionOrderExecutedInquiryService.class,
            new WEB3LogSysTimeInterceptor());

        //OP建玉照会サービス
        Services.addInterceptor(
            WEB3OptionContractInquiryService.class,
            new WEB3LogSysTimeInterceptor());

        //---先物サービス---
        //先物出来通知サービス
        Services.addInterceptor(
            WEB3FuturesOrderExecNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        //先物出来通知一件サービス
        Services.addInterceptor(
            WEB3FuturesOrderExecNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //先物訂正取消通知一件サービス
        Services.addInterceptor(
            WEB3FuturesChangeCancelNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //先物注文通知サービス
        Services.addInterceptor(
            WEB3FuturesOrderNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        //先物注文通知一件サービス
        Services.addInterceptor(
            WEB3FuturesOrderNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //先物新規建入力サービス
        Services.addInterceptor(
            WEB3FuturesOpenContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //先物新規建注文サービス
        Services.addInterceptor(
            WEB3FuturesOpenContractService.class,
            new WEB3LogSysTimeInterceptor());

        //先物訂正新規建入力サービス
        Services.addInterceptor(
            WEB3FuturesChangeOpenContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //先物訂正新規建サービス
        Services.addInterceptor(
            WEB3FuturesChangeOpenContractService.class,
            new WEB3LogSysTimeInterceptor());

        //先物個別返済一覧表示サービス
        Services.addInterceptor(
            WEB3FuturesIndividualSettleContractListService.class,
            new WEB3LogSysTimeInterceptor());

        //先物返済入力サービス
        Services.addInterceptor(
            WEB3FuturesSettleContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //先物返済注文サービス
        Services.addInterceptor(
            WEB3FuturesSettleContractOrderService.class,
            new WEB3LogSysTimeInterceptor());

        //先物訂正返済入力サービス
        Services.addInterceptor(
            WEB3FuturesChangeClosingContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //先物訂正返済サービス
        Services.addInterceptor(
            WEB3FuturesChangeClosingContractService.class,
            new WEB3LogSysTimeInterceptor());

        //先物取消注文サービス
        Services.addInterceptor(
            WEB3FuturesCancelOrderService.class,
            new WEB3LogSysTimeInterceptor());

        //先物注文約定照会サービス
        Services.addInterceptor(
            WEB3FuturesExecuteReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        //先物建玉照会サービス
        Services.addInterceptor(
            WEB3FuturesContractInquiryService.class,
            new WEB3LogSysTimeInterceptor());

        //---------------------- 3 Service に ServiceInterceptor を設定する ----------------------
        //---先物OP共通サービス---

        //先物OP注文受付一件サービス
        Services.addInterceptor(
            WEB3IfoOrderAcceptUnitService.class,
            new WEB3IfoOrderAcceptUnitServiceInterceptor());

        //先物OP訂正取消受付一件サービス
        Services.addInterceptor(
            WEB3IfoChangeCancelOrderAcceptUnitService.class,
            new WEB3IfoChangeCancelOrderAcceptUnitServiceInterceptor());

        //先物OP失効通知一件サービス
        Services.addInterceptor(
            WEB3IfoCloseNotifyUnitService.class,
            new WEB3IfoCloseNotifyUnitServiceInterceptor());

        //先物OP出来終了通知一件サービス
        Services.addInterceptor(
            WEB3IfoExecuteEndNotifyUnitService.class,
            new WEB3IfoExecuteEndNotifyUnitServiceInterceptor());

        //先物OP残高照会サービス
        Services.addInterceptor(
            WEB3IfoBalanceReferenceService.class,
            new WEB3IfoBalanceReferenceServiceInterceptor());

        //---OPサービス---
        //OP出来通知一件サービス
        Services.addInterceptor(
            WEB3OptionOrderExecNotifyUnitService.class,
            new WEB3OptionOrderExecutedNotifyUnitServiceInterceptor());

        //OP訂正取消通知一件サービス
        Services.addInterceptor(
            WEB3OptionChangeCancelNotifyUnitService.class,
            new WEB3OptionChangeCancelNotifyUnitServiceInterceptor());

        //OP注文通知サービス
        Services.addInterceptor(
            WEB3OptionsOrderNotifyUnitService.class,
            new WEB3OptionsOrderNotifyUnitServiceInterceptor());

        //OP新規建入力サービス
        Services.addInterceptor(
            WEB3OptionOpenContractInputService.class,
            new WEB3OptionOpenContractInputServiceInterceptor());

        //OP新規建注文サービス
        Services.addInterceptor(
            WEB3OptionOpenContractOrderService.class,
            new WEB3OptionOpenContractOrderServiceInterceptor());

        //OP訂正新規建入力サービス
        Services.addInterceptor(
            WEB3OptionChangeOpenContractInputService.class,
            new WEB3OptionChangeOpenContractInputServiceInterceptor());

        //OP訂正新規建サービス
        Services.addInterceptor(
            WEB3OptionChangeOpenContractService.class,
            new WEB3OptionChangeOpenContractServiceInterceptor());

        //OP個別返済一覧表示サービス
        Services.addInterceptor(
            WEB3OptionIndividualSettleContractListService.class,
            new WEB3OptionIndividualSettleContractListServiceInterceptor());

        //OP返済入力サービス
        Services.addInterceptor(
            WEB3OptionSettleContractInputService.class,
            new WEB3OptionSettleContractInputServiceInterceptor());

        //OP返済注文サービス
        Services.addInterceptor(
            WEB3OptionSettleContractOrderService.class,
            new WEB3OptionSettleContractOrderServiceInterceptor());

        //OP訂正返済入力サービス
        Services.addInterceptor(
            WEB3OptionChangeClosingContractInputService.class,
            new WEB3OptionChangeClosingContractInputServiceInterceptor());

        //OP訂正返済サービス
        Services.addInterceptor(
            WEB3OptionChangeClosingContractService.class,
            new WEB3OptionChangeClosingContractServiceInterceptor());

        //OP取消注文サービス
        Services.addInterceptor(
            WEB3OptionCancelOrderService.class,
            new WEB3OptionCancelOrderServiceInterceptor());

        //OP注文約定照会サービス
        Services.addInterceptor(
            WEB3OptionOrderExecutedInquiryService.class,
            new WEB3OptionOrderExecutedInquiryServiceInterceptor());

        //OP建玉照会サービス
        Services.addInterceptor(
            WEB3OptionContractInquiryService.class,
            new WEB3OptionContractInquiryServiceInterceptor());

        //---先物サービス---
        //先物出来通知一件サービス
        Services.addInterceptor(
            WEB3FuturesOrderExecNotifyUnitService.class,
            new WEB3FuturesOrderExecNotifyUnitServiceInterceptor());

        //先物訂正取消通知一件サービス
        Services.addInterceptor(
            WEB3FuturesChangeCancelNotifyUnitService.class,
            new WEB3FuturesChangeCancelNotifyUnitServiceInterceptor());

        //先物注文通知サービス
        Services.addInterceptor(
            WEB3FuturesOrderNotifyUnitService.class,
            new WEB3FuturesOrderNotifyUnitServiceInterceptor());

        //先物新規建入力サービス
        Services.addInterceptor(
            WEB3FuturesOpenContractInputService.class,
            new WEB3FuturesOpenContractInputServiceInterceptor());

        //先物新規建注文サービス
        Services.addInterceptor(
            WEB3FuturesOpenContractService.class,
            new WEB3FuturesOpenContractServiceInterceptor());

        //先物訂正新規建入力サービス
        Services.addInterceptor(
            WEB3FuturesChangeOpenContractInputService.class,
            new WEB3FuturesChangeOpenContractInputServiceInterceptor());

        //先物訂正新規建サービス
        Services.addInterceptor(
            WEB3FuturesChangeOpenContractService.class,
            new WEB3FuturesChangeOpenContractServiceInterceptor());

        //先物個別返済一覧表示サービス
        Services.addInterceptor(
            WEB3FuturesIndividualSettleContractListService.class,
            new WEB3FuturesIndividualSettleContractListServiceInterceptor());

        //先物返済入力サービス
        Services.addInterceptor(
            WEB3FuturesSettleContractInputService.class,
            new WEB3FuturesSettleContractInputServiceInterceptor());

        //先物返済注文サービス
        Services.addInterceptor(
            WEB3FuturesSettleContractOrderService.class,
            new WEB3FuturesSettleContractOrderServiceInterceptor());

        //先物訂正返済入力サービス
        Services.addInterceptor(
            WEB3FuturesChangeClosingContractInputService.class,
            new WEB3FuturesChangeClosingContractInputServiceInterceptor());

        //先物訂正返済サービス
        Services.addInterceptor(
            WEB3FuturesChangeClosingContractService.class,
            new WEB3FuturesChangeClosingContractServiceInterceptor());

        //先物取消注文サービス
        Services.addInterceptor(
            WEB3FuturesCancelOrderService.class,
            new WEB3FuturesCancelOrderServiceInterceptor());

        //先物注文約定照会サービス
        Services.addInterceptor(
            WEB3FuturesExecuteReferenceService.class,
            new WEB3FuturesExecuteReferenceServiceInterceptor());

        //先物建玉照会サービス
        Services.addInterceptor(
            WEB3FuturesContractInquiryService.class,
            new WEB3FuturesContractInquiryServiceInterceptor());

        //---------------------- 4 Service の Interceptor 設定処理(自動トランザクション設定) ----------------------
        //---先物OP共通サービス---
        //先物OP注文受付サービス
        Services.addInterceptor(
            WEB3IfoOrderAcceptService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //先物OP注文受付一件サービス
        Services.addInterceptor(
            WEB3IfoOrderAcceptUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //先物OP訂正取消受付サービス
        Services.addInterceptor(
            WEB3IfoChangeCancelOrderAcceptService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //先物OP訂正取消受付一件サービス
        Services.addInterceptor(
            WEB3IfoChangeCancelOrderAcceptUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //先物OP失効通知サービス
        Services.addInterceptor(
            WEB3IfoCloseNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //先物OP失効通知一件サービス
        Services.addInterceptor(
            WEB3IfoCloseNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //先物OP出来終了通知サービス
        Services.addInterceptor(
            WEB3IfoExecuteEndNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //先物OP出来終了通知一件サービス
        Services.addInterceptor(
            WEB3IfoExecuteEndNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //先物OP約定メール送信サービス
        Services.addInterceptor(
            WEB3IfoExecutedMailSendService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //---OPサービス---
        //OP出来通知サービス
        Services.addInterceptor(
            WEB3OptionOrderExecNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //OP出来通知一件サービス
        Services.addInterceptor(
            WEB3OptionOrderExecNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //OP訂正取消通知サービス
        Services.addInterceptor(
            WEB3OptionChangeCancelNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //OP訂正取消通知一件サービス
        Services.addInterceptor(
            WEB3OptionChangeCancelNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //OP注文通知サービス
        Services.addInterceptor(
            WEB3OptionsOrderNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //OP注文通知一件サービス
        Services.addInterceptor(
            WEB3OptionsOrderNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //OP新規建注文サービス
        Services.addInterceptor(
            WEB3OptionOpenContractOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //OP訂正新規建サービス
        Services.addInterceptor(
            WEB3OptionChangeOpenContractService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //OP返済注文サービス
        Services.addInterceptor(
            WEB3OptionSettleContractOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //OP訂正返済サービス
        Services.addInterceptor(
            WEB3OptionChangeClosingContractService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //OP取消注文サービス
        Services.addInterceptor(
            WEB3OptionCancelOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //---先物サービス---
        //先物出来通知サービス
        Services.addInterceptor(
            WEB3FuturesOrderExecNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //先物出来通知一件サービス
        Services.addInterceptor(
            WEB3FuturesOrderExecNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //先物訂正取消通知サービス
        Services.addInterceptor(
            WEB3FuturesChangeCancelNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //先物訂正取消通知一件サービス
        Services.addInterceptor(
            WEB3FuturesChangeCancelNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //先物注文通知サービス
        Services.addInterceptor(
            WEB3FuturesOrderNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //先物注文通知一件サービス
        Services.addInterceptor(
            WEB3FuturesOrderNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //先物新規建注文サービス
        Services.addInterceptor(
            WEB3FuturesOpenContractService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //先物訂正新規建サービス
        Services.addInterceptor(
            WEB3FuturesChangeOpenContractService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //先物返済注文サービス
        Services.addInterceptor(
            WEB3FuturesSettleContractOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //先物訂正返済サービス
        Services.addInterceptor(
            WEB3FuturesChangeClosingContractService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //先物取消注文サービス
        Services.addInterceptor(
            WEB3FuturesCancelOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //----------------------  MQ-Gatewayインタセプタの設定  ----------------------
        //---OPサービス---
        //OP新規建注文サービス
        Services.addInterceptor(
            WEB3OptionOpenContractOrderService.class,
            new WEB3MQGatewayInterceptor());

        //OP訂正新規建注文サービス
        Services.addInterceptor(
            WEB3OptionChangeOpenContractService.class,
            new WEB3MQGatewayInterceptor());

        //OP返済注文サービス
        Services.addInterceptor(
            WEB3OptionSettleContractOrderService.class,
            new WEB3MQGatewayInterceptor());

        //OP訂正返済注文サービス
        Services.addInterceptor(
            WEB3OptionChangeClosingContractService.class,
            new WEB3MQGatewayInterceptor());

        //OP取消注文サービス
        Services.addInterceptor(
            WEB3OptionCancelOrderService.class,
            new WEB3MQGatewayInterceptor());

        //---先物サービス---
        //先物新規建注文サービス
        Services.addInterceptor(
            WEB3FuturesOpenContractService.class,
             new WEB3MQGatewayInterceptor());

        //先物訂正新規建注文サービス
        Services.addInterceptor(
            WEB3FuturesChangeOpenContractService.class,
            new WEB3MQGatewayInterceptor());

        //先物返済注文サービス
        Services.addInterceptor(
             WEB3FuturesSettleContractOrderService.class,
            new WEB3MQGatewayInterceptor());

        //先物訂正返済注文サービス
        Services.addInterceptor(
            WEB3FuturesChangeClosingContractService.class,
            new WEB3MQGatewayInterceptor());

        //先物取消注文サービス
        Services.addInterceptor(
            WEB3FuturesCancelOrderService.class,
            new WEB3MQGatewayInterceptor());

        //---------------------- 5 Message の登録処理 ----------------------
        //---先物OP共通サービス---
        //先物OP注文受付リクエスト
        regClass(WEB3IfoOrderAcceptRequest.class);
        //先物OP注文受付レスポンス
        regClass(WEB3IfoOrderAcceptResponse.class);

        //先物OP訂正取消受付リクエスト
        regClass(WEB3IfoChangeCancelAcceptRequest.class);
        //先物OP訂正取消受付レスポンス
        regClass(WEB3IfoChangeCancelAcceptResponse.class);

        //先物OP失効通知リクエストクラス
        regClass(WEB3IfoCloseOrderRequest.class);
        //先物OP失効通知レスポンスクラス
        regClass(WEB3IfoCloseOrderResponse.class);

        //先物OP出来終了通知リクエストクラス
        regClass(WEB3IfoExecEndNotifyRequest.class);
        //先物OP出来終了通知レスポンスクラス
        regClass(WEB3IfoExecEndNotifyResponse.class);

        //先物OP残高照会リクエストクラス
        regClass(WEB3FuturesOptionsBalanceReferenceRequest.class);
        //先物OP残高照会レスポンスクラス
        regClass(WEB3FuturesOptionsBalanceReferenceResponse.class);

        //先物OP残高照会残高合計リクエストクラス
        regClass(WEB3FuturesOptionsBalanceReferenceTotalRequest.class);
        //先物OP残高照会残高合計レスポンスクラス
        regClass(WEB3FuturesOptionsBalanceReferenceTotalResponse.class);

        //---OPサービス---
        //株式指数OP出来通知リクエスト
        regClass(WEB3OptionOrderExecNotifyRequest.class);
        //株式指数OP出来通知レスポンス
        regClass(WEB3OptionOrderExecNotifyResponse.class);

        //株式指数OP訂正取消通知リクエスト
        regClass(WEB3OptionsChangeCancelNotifyRequest.class);
        //株式指数OP訂正取消通知レスポンス
        regClass(WEB3OptionsChangeCancelNotifyResponse.class);

        //株価指数OP注文通知リクエスト
        regClass(WEB3OptionsOrderNotifyRequest.class);
        //株価指数OP注文通知レスポンス
        regClass(WEB3OptionsOrderNotifyResponse.class);

        //株価指数OP共通入力リクエス
        regClass(WEB3OptionsCommonRequest.class);
        //株価指数OP共通入力レスポンス
        regClass(WEB3OptionsCommonResponse.class);

        //株価指数OP新規建注文銘柄選択画面リクエスト
        regClass(WEB3OptionsProductSelectRequest.class);
        //株価指数OP新規建注文銘柄選択画面レスポンス
        regClass(WEB3OptionsProductSelectResponse.class);

        //株価指数OP新規建注文入力画面リクエスト
        regClass(WEB3OptionsOpenMarginInputRequest.class);
        //株価指数OP新規建注文入力画面レスポンス
        regClass(WEB3OptionsOpenMarginInputResponse.class);

        //株価指数OP新規建注文確認リクエスト
        regClass(WEB3OptionsOpenMarginConfirmRequest.class);
        //株価指数OP新規建注文確認レスポンス
        regClass(WEB3OptionsOpenMarginConfirmResponse.class);

        //株価指数OP新規建注文完了リクエスト
        regClass(WEB3OptionsOpenMarginCompleteRequest.class);
        //株価指数OP新規建注文完了レスポンス
        regClass(WEB3OptionsOpenMarginCompleteResponse.class);

        //株価指数OP訂正新規建入力画面リクエスト
        regClass(WEB3OptionsOpenMarginChangeInputRequest.class);
        //株価指数OP訂正新規建入力画面レスポンス
        regClass(WEB3OptionsOpenMarginChangeInputResponse.class);

        //株価指数OP訂正新規建確認リクエスト
        regClass(WEB3OptionsOpenMarginChangeConfirmRequest.class);
        //株価指数OP訂正新規建確認レスポンス
        regClass(WEB3OptionsOpenMarginChangeConfirmResponse.class);

        //株価指数OP訂正新規建完了リクエスト
        regClass(WEB3OptionsOpenMarginChangeCompleteRequest.class);
        //株価指数OP訂正新規建完了レスポンス
        regClass(WEB3OptionsOpenMarginChangeCompleteResponse.class);

        //株価指数OP個別返済一覧画面表示リクエスト
        regClass(WEB3OptionsIndividualCloseMarginListRequest.class);
        //株価指数OP個別返済一覧画面表示レスポンス
        regClass(WEB3OptionsIndividualCloseMarginListResponse.class);

        //株価指数OP返済一覧画面表示リクエスト
        regClass(WEB3OptionsCloseMarginListRequest.class);
        //株価指数OP返済一覧画面表示レスポンス
        regClass(WEB3OptionsCloseMarginListResponse.class);

        //株価指数OP返済入力画面リクエスト
        regClass(WEB3OptionsCloseMarginInputRequest.class);
        //株価指数OP返済入力画面レスポンス
        regClass(WEB3OptionsCloseMarginInputResponse.class);

        //株価指数OP返済確認リクエスト
        regClass(WEB3OptionsCloseMarginConfirmRequest.class);
        //株価指数OP返済確認レスポンス
        regClass(WEB3OptionsCloseMarginConfirmResponse.class);

        //株価指数OP返済完了リクエスト
        regClass(WEB3OptionsCloseMarginCompleteRequest.class);
        //株価指数OP返済完了レスポンス
        regClass(WEB3OptionsCloseMarginCompleteResponse.class);

        //株価指数OP取消注文確認リクエスト
        regClass(WEB3OptionsCancelConfirmRequest.class);
        //株価指数OP取消注文確認レスポンス
        regClass(WEB3OptionsCancelConfirmResponse.class);

        //株価指数OP取消注文完了リクエスト
        regClass(WEB3OptionsCancelCompleteRequest.class);
        //株価指数OP取消注文完了レスポンス
        regClass(WEB3OptionsCancelCompleteResponse.class);

        //株価指数OP訂正返済完了リクエスト
        regClass(WEB3OptionsCloseMarginChangeCompleteRequest.class);
        //株価指数OP訂正返済完了レスポンス
        regClass(WEB3OptionsCloseMarginChangeCompleteResponse.class);

        //株価指数OP訂正返済確認リクエスト
        regClass(WEB3OptionsCloseMarginChangeConfirmRequest.class);
        //株価指数OP訂正返済確認レスポンス
        regClass(WEB3OptionsCloseMarginChangeConfirmResponse.class);

        //株価指数OP訂正返済入力画面リクエスト
        regClass(WEB3OptionsCloseMarginChangeInputRequest.class);
        //株価指数OP訂正返済入力画面レスポンス
        regClass(WEB3OptionsCloseMarginChangeInputResponse.class);

        //株価指数OP注文約定照会リクエスト
        regClass(WEB3OptionsExecuteReferenceRequest.class);
        //株価指数OP注文約定照会レスポンス
        regClass(WEB3OptionsExecuteReferenceResponse.class);

        //株価指数OP当日注文約定詳細リクエスト
        regClass(WEB3OptionsExecuteDetailsRequest.class);
        //株価指数OP当日注文約定詳細レスポンス
        regClass(WEB3OptionsExecuteDetailsResponse.class);

        //株価指数OP注文履歴照会リクエスト
        regClass(WEB3OptionsOrderHistoryRequest.class);
        //株価指数OP注文履歴照会レスポンス
        regClass(WEB3OptionsOrderHistoryResponse.class);

        //株価指数OP返済建玉一覧リクエスト
        regClass(WEB3OptionsCloseMarginContractListRequest.class);
        //株価指数OP返済建玉一覧レスポンス
        regClass(WEB3OptionsCloseMarginContractListResponse.class);

        //株価指数OP建玉照会リクエスト
        regClass(WEB3OptionsContractReferenceRequest.class);
        //株価指数OP建玉照会レスポンス
        regClass(WEB3OptionsContractReferenceResponse.class);

        //---先物サービス---
        //株価指数先物出来通知リクエスト
        regClass(WEB3FuturesOrderExecNotifyRequest.class);
        //株価指数先物出来通知レスポンス
        regClass(WEB3FuturesOrderExecNotifyResponse.class);

        //株価指数先物訂正取消通知リクエスト
        regClass(WEB3FuturesChangeCancelNotifyRequest.class);
        //株価指数先物訂正取消通知レスポンス
        regClass(WEB3FuturesChangeCancelNotifyResponse.class);

        //株価指数先物注文通知リクエスト
        regClass(WEB3FuturesOrderNotifyRequest.class);
        //株価指数先物注文通知レスポンス
        regClass(WEB3FuturesOrderNotifyResponse.class);

        //株価指数先物共通入力リクエスト
        regClass(WEB3FuturesCommonRequest.class);
        //株価指数先物共通入力レスポンス
        regClass(WEB3FuturesCommonResponse.class);

        //株価指数先物新規建注文銘柄選択画面リクエスト
        regClass(WEB3FuturesProductSelectRequest.class);
        //株価指数先物新規建注文銘柄選択画面レスポンス
        regClass(WEB3FuturesProductSelectResponse.class);

        //株価指数先物新規建注文入力画面リクエスト
        regClass(WEB3FuturesOpenMarginInputRequest.class);
        //株価指数先物新規建注文入力画面レスポンス
        regClass(WEB3FuturesOpenMarginInputResponse.class);

        //株価指数先物新規建注文確認リクエスト
        regClass(WEB3FuturesOpenMarginConfirmRequest.class);
        //株価指数先物新規建注文確認レスポンス
        regClass(WEB3FuturesOpenMarginConfirmResponse.class);

        //株価指数先物新規建注文完了リクエスト
        regClass(WEB3FuturesOpenMarginCompleteRequest.class);
        //株価指数先物新規建注文完了レスポンス
        regClass(WEB3FuturesOpenMarginCompleteResponse.class);

        //株価指数先物訂正新規建入力画面リクエスト
        regClass(WEB3FuturesOpenMarginChangeInputRequest.class);
        //株価指数先物訂正新規建入力画面レスポンス
        regClass(WEB3FuturesOpenMarginChangeInputResponse.class);

        //株価指数先物訂正新規建確認リクエスト
        regClass(WEB3FuturesOpenMarginChangeConfirmRequest.class);
        //株価指数先物訂正新規建確認レスポンス
        regClass(WEB3FuturesOpenMarginChangeConfirmResponse.class);

        //株価指数先物訂正新規建完了リクエスト
        regClass(WEB3FuturesOpenMarginChangeCompleteRequest.class);
        //株価指数先物訂正新規建完了レスポンス
        regClass(WEB3FuturesOpenMarginChangeCompleteResponse.class);

        //株価指数先物個別返済一覧画面表示リクエスト
        regClass(WEB3FuturesIndividualCloseMarginListRequest.class);
        //株価指数先物個別返済一覧画面表示レスポンス
        regClass(WEB3FuturesIndividualCloseMarginListResponse.class);

        //株価指数先物返済一覧画面表示リクエスト
        regClass(WEB3FuturesCloseMarginListRequest.class);
        //株価指数先物返済一覧画面表示レスポンス
        regClass(WEB3FuturesCloseMarginListResponse.class);

        //株価指数先物返済入力画面リクエスト
        regClass(WEB3FuturesCloseMarginInputRequest.class);
        //株価指数先物返済入力画面レスポンス
        regClass(WEB3FuturesCloseMarginInputResponse.class);

        //株価指数先物返済注文確認リクエスト
        regClass(WEB3FuturesCloseMarginConfirmRequest.class);
        //株価指数先物返済注文確認レスポンス
        regClass(WEB3FuturesCloseMarginConfirmResponse.class);

        //株価指数先物返済注文完了リクエスト
        regClass(WEB3FuturesCloseMarginCompleteRequest.class);
        //株価指数先物返済注文完了レスポンス
        regClass(WEB3FuturesCloseMarginCompleteResponse.class);

        //株価指数先物訂正返済入力画面リクエスト
        regClass(WEB3FuturesCloseMarginChangeInputRequest.class);
        //株価指数先物訂正返済入力画面レスポンス
        regClass(WEB3FuturesCloseMarginChangeInputResponse.class);

        //株価指数先物訂正返済確認リクエスト
        regClass(WEB3FuturesCloseMarginChangeConfirmRequest.class);
        //株価指数先物訂正返済確認レスポンス
        regClass(WEB3FuturesCloseMarginChangeConfirmResponse.class);

        //株価指数先物訂正返済完了リクエスト
        regClass(WEB3FuturesCloseMarginChangeCompleteRequest.class);
        //株価指数先物訂正返済完了レスポンス
        regClass(WEB3FuturesCloseMarginChangeCompleteResponse.class);

        //株価指数先物取消注文確認リクエスト
        regClass(WEB3FuturesCancelConfirmRequest.class);
        //株価指数先物取消注文確認レスポンス
        regClass(WEB3FuturesCancelConfirmResponse.class);

        //株価指数先物取消注文完了リクエスト
        regClass(WEB3FuturesCancelCompleteRequest.class);
        //株価指数先物取消注文完了レスポンス
        regClass(WEB3FuturesCancelCompleteResponse.class);

        //株価指数先物注文約定照会リクエスト
        regClass(WEB3FuturesExecuteReferenceRequest.class);
        //株価指数先物注文約定照会レスポンス
        regClass(WEB3FuturesExecuteReferenceResponse.class);

        //株価指数先物注文履歴照会リクエスト
        regClass(WEB3FuturesOrderHistoryRequest.class);
        //株価指数先物注文履歴照会レスポンス
        regClass(WEB3FuturesOrderHistoryResponse.class);

        //株価指数先物当日注文約定詳細リクエスト
        regClass(WEB3FuturesExecuteDetailsRequest.class);
        //株価指数先物当日注文約定詳細レスポンス
        regClass(WEB3FuturesExecuteDetailsResponse.class);

        //株価指数先物返済建玉一覧リクエスト
        regClass(WEB3FuturesCloseMarginContractListRequest.class);
        //株価指数先物返済建玉一覧レスポンス
        regClass(WEB3FuturesCloseMarginContractListResponse.class);

        //株価指数先物建玉照会リクエスト
        regClass(WEB3FuturesContractReferenceRequest.class);
        //株価指数先物建玉照会レスポンス
        regClass(WEB3FuturesContractReferenceResponse.class);

        //---------------------- 6 Handler の登録処理 ----------------------
        //---先物OP共通サービス---
        //先物OP注文受付リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3IfoOrderAcceptRequest.class,
            WEB3IfoOrderAcceptHandler.class,
            "optionOrderAcceptRequest");

        //先物OP訂正取消受付リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3IfoChangeCancelAcceptRequest.class,
            WEB3IfoChangeCancelOrderAcceptHandler.class,
            "ifoChangeCancelOrderAcceptRequest");

        //先物OP失効通知リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3IfoCloseOrderRequest.class,
            WEB3IfoCloseNotifyHandler.class,
            "ifoCloseNotifyRequest");

        //先物OP出来終了通知リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3IfoExecEndNotifyRequest.class,
            WEB3IfoExecuteEndNotifyHandler.class,
            "executeEndNotifyRequest");

        //先物OP残高照会リクエスト用ハンドラ(get残高照会)
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesOptionsBalanceReferenceRequest.class,
            WEB3IfoBalanceReferenceHandler.class,
            "getBalanceReference");

        //先物OP残高照会リクエスト用ハンドラ(get残高合計)
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesOptionsBalanceReferenceTotalRequest.class,
            WEB3IfoBalanceReferenceHandler.class,
            "getBalanceReferenceTotal");

        //---OPサービス---
        //OP出来通知リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionOrderExecNotifyRequest.class,
            WEB3OptionOrderExecNotifyHandler.class,
            "optionOrderExecNotifyRequest");

        //OP訂正取消通知リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsChangeCancelNotifyRequest.class,
            WEB3OptionChangeCancelNotifyHandler.class,
            "optionChangeCancelNotifyRequest");

        //OP注文通知リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsOrderNotifyRequest.class,
            WEB3OptionsOrderNotifyHandler.class,
            "optionsOrderNotifyRequest");

        //OP新規建注文銘柄選択画面リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsProductSelectRequest.class,
            WEB3OptionOpenContractInputHandler.class,
            "openContractProductSelectRequest");

        //OP新規建入力リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsOpenMarginInputRequest.class,
            WEB3OptionOpenContractInputHandler.class,
            "openContractInputRequest");

        //OP新規建注文確認リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsOpenMarginConfirmRequest.class,
            WEB3OptionOpenContractOrderHandler.class,
            "confirmOrder");

        //OP新規建注文完了リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsOpenMarginCompleteRequest.class,
            WEB3OptionOpenContractOrderHandler.class,
            "completeOrder");

        //OP訂正新規建入力リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsOpenMarginChangeInputRequest.class,
            WEB3OptionChangeOpenContractInputHandler.class,
            "changeOpenContractInputRequest");

        //OP訂正新規建確認リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsOpenMarginChangeConfirmRequest.class,
            WEB3OptionChangeOpenContractHandler.class,
            "confirmChangeOpenContract");

        //OP訂正新規建完了リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsOpenMarginChangeCompleteRequest.class,
            WEB3OptionChangeOpenContractHandler.class,
            "completeChangeOpenContract");

        //OP個別返済一覧表示リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsIndividualCloseMarginListRequest.class,
            WEB3OptionIndividualSettleContractListServiceHandler.class,
            "getIndividualSettleContractList");

        //OP返済一覧リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsCloseMarginListRequest.class,
            WEB3OptionSettleContractInputServiceHandler.class,
            "getSettleContractList");

        //OP返済入力リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsCloseMarginInputRequest.class,
            WEB3OptionSettleContractInputServiceHandler.class,
            "getSettleContractInputScreen");

        //OP返済確認リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsCloseMarginConfirmRequest.class,
            WEB3OptionSettleContractHandler.class,
            "confirmSettleContract");

        //OP返済完了リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsCloseMarginCompleteRequest.class,
            WEB3OptionSettleContractHandler.class,
            "completeSettleContract");

        //OP訂正返済入力リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsCloseMarginChangeInputRequest.class,
            WEB3OptionChangeClosingContractInputHandler.class,
            "changeClosingContractInputRequest");

        //OP訂正返済確認リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsCloseMarginChangeConfirmRequest.class,
            WEB3OptionChangeClosingContractHandler.class,
            "confirmChangeClosingContract");

        //OP訂正返済完了リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsCloseMarginChangeCompleteRequest.class,
            WEB3OptionChangeClosingContractHandler.class,
            "completeChangeClosingContract");

        //OP取消確認リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsCancelConfirmRequest.class,
            WEB3OptionCancelOrderHandler.class,
            "confirmCancel");

        //OP取消完了リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsCancelCompleteRequest.class,
            WEB3OptionCancelOrderHandler.class,
            "completeCancel");

        //OP注文約定照会リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsExecuteReferenceRequest.class,
            WEB3OptionOrderExecutedInquiryHandler.class,
            "getOrderExecutedInquiry");

        //OP注文約定詳細リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsExecuteDetailsRequest.class,
            WEB3OptionOrderExecutedInquiryHandler.class,
            "getOrderExecutedDetail");

        //OP注文履歴リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsOrderHistoryRequest.class,
            WEB3OptionOrderExecutedInquiryHandler.class,
            "getOrderActionInquiry");

        //OP返済建玉一覧リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsCloseMarginContractListRequest.class,
            WEB3OptionOrderExecutedInquiryHandler.class,
            "getSettleContractList");

        //OP建玉照会リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsContractReferenceRequest.class,
            WEB3OptionContractInquiryHandler.class,
            "getContract");

        //---先物サービス---
        //先物出来通知リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesOrderExecNotifyRequest.class,
            WEB3FuturesOrderExecNotifyHandler.class,
            "futuresOrderExecNotifyRequest");

        //先物訂正取消通知リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesChangeCancelNotifyRequest.class,
            WEB3FuturesChangeCancelNotifyHandler.class,
            "WEB3FuturesChangeCancelNotifyRequest");

        //先物注文通知リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesOrderNotifyRequest.class,
            WEB3FuturesOrderNotifyHandler.class,
            "futuresOrderNotifyRequest");

        //先物新規建注文銘柄選択画面リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesProductSelectRequest.class,
            WEB3FuturesOpenContractInputHandler.class,
            "openMarginProductSelectRequest");

        //先物新規建入力リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesOpenMarginInputRequest.class,
            WEB3FuturesOpenContractInputHandler.class,
            "openMarginInputRequest");

        //先物新規建確認リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesOpenMarginConfirmRequest.class,
            WEB3FuturesOpenContractHandler.class,
            "confirmOrder");

        //先物新規建完了リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesOpenMarginCompleteRequest.class,
            WEB3FuturesOpenContractHandler.class,
            "completeOrder");

        //先物訂正新規建入力リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesOpenMarginChangeInputRequest.class,
            WEB3FuturesChangeOpenContractInputHandler.class,
            "openMarginChangeInputRequest");

        //先物訂正新規建確認リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesOpenMarginChangeConfirmRequest.class,
            WEB3FuturesChangeOpenContractHandler.class,
            "confirmOpenMarginChange");

        //先物訂正新規建完了リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesOpenMarginChangeCompleteRequest.class,
            WEB3FuturesChangeOpenContractHandler.class,
            "completeOpenMarginChange");

        //先物個別返済一覧表示リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesIndividualCloseMarginListRequest.class,
            WEB3FuturesIndividualSettleContractListHandler.class,
            "getIndividualCloseMarginList");

        //先物返済一覧リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesCloseMarginListRequest.class,
            WEB3FuturesSettleContractInputHandler.class,
            "getCloseMarginList");

        //先物返済入力リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesCloseMarginInputRequest.class,
            WEB3FuturesSettleContractInputHandler.class,
            "getCloseMarginInput");

        //先物返済確認リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesCloseMarginConfirmRequest.class,
            WEB3FuturesSettleContractOrderHandler.class,
            "confirmCloseMargin");

        //先物返済完了リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesCloseMarginCompleteRequest.class,
            WEB3FuturesSettleContractOrderHandler.class,
            "completeCloseMargin");

        //先物訂正返済入力リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesCloseMarginChangeInputRequest.class,
            WEB3FuturesChangeClosingContractInputHandler.class,
            "WEB3FuturesCloseMarginChangeInputRequest");

        //先物訂正返済確認リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesCloseMarginChangeConfirmRequest.class,
            WEB3FuturesChangeClosingContractHandler.class,
            "confirmChangeClosingContract");

        //先物訂正返済完了リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesCloseMarginChangeCompleteRequest.class,
            WEB3FuturesChangeClosingContractHandler.class,
            "completeChangeClosingContract");

        //先物取消確認リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesCancelConfirmRequest.class,
            WEB3FuturesCancelOrderHandler.class,
            "confirmCancel");

        //先物取消完了リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesCancelCompleteRequest.class,
            WEB3FuturesCancelOrderHandler.class,
            "completeCancel");

        //先物注文約定照会リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesExecuteReferenceRequest.class,
            WEB3FuturesExecuteReferenceHandler.class,
            "getOrderExecutedInquiry");

        //先物注文約定詳細リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesExecuteDetailsRequest.class,
            WEB3FuturesExecuteReferenceHandler.class,
            "getOrderExecutedDetail");

        //先物注文履歴リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesOrderHistoryRequest.class,
            WEB3FuturesExecuteReferenceHandler.class,
            "getOrderActionInquiry");

        //先物返済建玉一覧リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesCloseMarginContractListRequest.class,
            WEB3FuturesExecuteReferenceHandler.class,
            "getSettleContractList");

        //先物建玉照会リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesContractReferenceRequest.class,
            WEB3FuturesContractInquiryHandler.class,
            "getContract");

        //---------------------- RAC-CTXインタセプタの設定 ----------------------
        // 先物・OP注文受付一件
        Services.addInterceptor(
            WEB3IfoOrderAcceptUnitService.class,
            new WEB3IfoDescendRacCtxInterceptor());

        // 先物・OP訂正取消受付一件
        Services.addInterceptor(
            WEB3IfoChangeCancelOrderAcceptUnitService.class,
            new WEB3IfoDescendRacCtxInterceptor());

        // 先物・OP失効通知一件
        Services.addInterceptor(
            WEB3IfoCloseNotifyUnitService.class,
            new WEB3IfoDescendRacCtxInterceptor());

        // 先物OP出来終了通知一件
        Services.addInterceptor(
            WEB3IfoExecuteEndNotifyUnitService.class,
            new WEB3IfoDescendRacCtxInterceptor());

        // OP出来通知一件
        Services.addInterceptor(
            WEB3OptionOrderExecNotifyUnitService.class,
            new WEB3IfoDescendRacCtxInterceptor());

        // OP訂正・取消通知一件
        Services.addInterceptor(
            WEB3OptionChangeCancelNotifyUnitService.class,
            new WEB3IfoDescendRacCtxInterceptor());

        // OP注文通知一件
        Services.addInterceptor(
            WEB3OptionsOrderNotifyUnitService.class,
            new WEB3IfoDescendRacCtxInterceptor());

        // 先物出来通知一件
        Services.addInterceptor(
            WEB3FuturesOrderExecNotifyUnitService.class,
            new WEB3IfoDescendRacCtxInterceptor());

        // 先物訂正・取消通知一件
        Services.addInterceptor(
            WEB3FuturesChangeCancelNotifyUnitService.class,
            new WEB3IfoDescendRacCtxInterceptor());

        // 先物注文通知一件
        Services.addInterceptor(
            WEB3FuturesOrderNotifyUnitService.class,
            new WEB3IfoDescendRacCtxInterceptor());

        log.exiting(METHOD_NAME);
    }
}
@

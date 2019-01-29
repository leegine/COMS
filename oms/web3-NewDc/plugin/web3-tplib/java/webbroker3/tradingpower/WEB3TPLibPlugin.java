head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.56.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPLibPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 余力計算プラグインクラス(WEB3TPLibPlugin.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/09/08 劉 新規作成
 Revesion History : 2007/08/01 金傑(中訊) モデルNo.112
 Revesion History : 2008/05/27 トウ鋒鋼(中訊) 「債券シンプレクス連携」対応
 */
package webbroker3.tradingpower;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.tradingpower.data.WEB3TPLibAccountDatabaseExtensions;
import webbroker3.tradingpower.data.WEB3TPLibMasterDatabaseExtensions;
import webbroker3.tradingpower.data.WEB3TPLibSessionDatabaseExtensions;
import webbroker3.tradingpower.handler.WEB3TPOrixTpCalcResultHandler;
import webbroker3.tradingpower.handler.WEB3TPReCalcNotifyHandler;
import webbroker3.tradingpower.message.WEB3TPOrixTpCalcResultRequest;
import webbroker3.tradingpower.message.WEB3TPOrixTpCalcResultResponse;
import webbroker3.tradingpower.message.WEB3TPReCalcNotifyRequest;
import webbroker3.tradingpower.message.WEB3TPReCalcNotifyResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPBondSimplexCooperationService;
import webbroker3.tradingpower.service.delegate.WEB3TPOrixVantiveService;
import webbroker3.tradingpower.service.delegate.WEB3TPPaymentRequisitionManageService;
import webbroker3.tradingpower.service.delegate.WEB3TPReCalcNotifyService;
import webbroker3.tradingpower.service.delegate.WEB3TPReCalcNotifyUnitService;
import webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPBondSimplexCooperationServiceImpl;
import webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPOrixVantiveServiceImpl;
import webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl;
import webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPReCalcNotifyServiceImpl;
import webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPReCalcNotifyUnitServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * 余力計算プラグインクラス
 *
 * @@author 劉
 * @@version 1.0
 */
public final class WEB3TPLibPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPLibPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3TPLibPlugin()
    {
    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);
        plug(WEB3TPLibPlugin.class);
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

        // DatabaseExtensions プラグイン
        WEB3TPLibAccountDatabaseExtensions.plug();
        WEB3TPLibMasterDatabaseExtensions.plug();
        WEB3TPLibSessionDatabaseExtensions.plug();

        // Service の登録処理 ----------------------------------

        /*
         * 取引余力サービスを登録
         */
        Services.registerService(
            WEB3TPTradingPowerService.class,
            new WEB3TPTradingPowerServiceImpl());
        log.debug("WEB3TPTradingPowerService registered.");

        //余力サービスインターセプタを登録
        Services.addInterceptor(
            WEB3TPTradingPowerService.class,
            new WEB3TPTradingPowerServiceInterceptor());
        log.debug(
            "WEB3TPTradingPowerServiceInterceptor added to WEB3TPTradingPowerService");

        // TransactionalInterceptorを登録
        Services.addInterceptor(
            WEB3TPTradingPowerService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        log.debug("TransactionalInterceptor added to WEB3TPTradingPowerService");


        /*
         * 余力再計算サービスを登録
         */
        Services.registerService(
            WEB3TPTradingPowerReCalcService.class,
            new WEB3TPTradingPowerReCalcServiceImpl());
        log.debug("WEB3TPTradingPowerReCalcService registered.");

        //余力サービスインターセプタを登録
        Services.addInterceptor(
            WEB3TPTradingPowerReCalcService.class,
            new WEB3TPTradingPowerServiceInterceptor());
        log.debug(
            "WEB3TPTradingPowerServiceInterceptor added to WEB3TPTradingPowerReCalcService");

        // TransactionalInterceptorを登録
        Services.addInterceptor(
            WEB3TPTradingPowerReCalcService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        log.debug("TransactionalInterceptor added to WEB3TPTradingPowerReCalcService");


        /*
         * 返済後余力サービスを登録
         */
        Services.registerService(
            WEB3TPTradingPowerAfterRepayService.class,
            new WEB3TPTradingPowerAfterRepayServiceImpl());
        log.debug("WEB3TPTradingPowerAfterRepayService registered.");

        //余力サービスインターセプタを登録
        Services.addInterceptor(
            WEB3TPTradingPowerAfterRepayService.class,
            new WEB3TPTradingPowerServiceInterceptor());
        log.debug(
            "WEB3TPTradingPowerServiceInterceptor added to WEB3TPTradingPowerAfterRepayService");

        //TransactionalInterceptorを登録
        Services.addInterceptor(
            WEB3TPTradingPowerAfterRepayService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        log.debug("TransactionalInterceptor added to WEB3TPTradingPowerAfterRepayService");


        /*
         * 差金決済取引余力サービスを登録
         */
        Services.registerService(
            WEB3TPTradingPowerSettlementService.class,
            new WEB3TPTradingPowerSettlementServiceImpl());
        log.debug("WEB3TPTradingPowerSettlementService registered.");

        //余力サービスインターセプタを登録
        Services.addInterceptor(
            WEB3TPTradingPowerSettlementService.class,
            new WEB3TPTradingPowerServiceInterceptor());
        log.debug(
            "WEB3TPTradingPowerServiceInterceptor added to WEB3TPTradingPowerSettlementService");

        //TransactionalInterceptorを登録
        Services.addInterceptor(
            WEB3TPTradingPowerSettlementService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        log.debug("TransactionalInterceptor added to WEB3TPTradingPowerSettlementService");


        /*
         * 余力計算通知サービス
         */
        Services.registerService(
            WEB3TPReCalcNotifyService.class,
            new WEB3TPReCalcNotifyServiceImpl());
        log.debug("WEB3TPReCalcNotifyService registered.");

        //TransactionalInterceptorを登録
        Services.addInterceptor(
            WEB3TPReCalcNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        log.debug("TransactionalInterceptor added to WEB3TPReCalcNotifyService");

        /*
         * 余力計算通知一件サービス
         */
        Services.registerService(
            WEB3TPReCalcNotifyUnitService.class,
            new WEB3TPReCalcNotifyUnitServiceImpl());
        log.debug("WEB3TPReCalcNotifyUnitService registered.");

        //余力計算通知一件サービスインタセプタを登録
        Services.addInterceptor(
            WEB3TPReCalcNotifyUnitService.class,
            new WEB3TPReCalcNotifyUnitServiceInterceptor());
        log.debug(
            "WEB3TPReCalcNotifyUnitServiceInterceptor added to WEB3TPReCalcNotifyUnitService");

        //TransactionalInterceptorを登録
        Services.addInterceptor(
            WEB3TPReCalcNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        log.debug("TransactionalInterceptor added to WEB3TPReCalcNotifyUnitService");

        // RAC-CTXインタセプタを設定する
        Services.addInterceptor(
        WEB3TPReCalcNotifyUnitService.class,
            new WEB3TPDescendRacCtxInterceptor());
        log.debug("WEB3TPDescendRacCtxInterceptor added to WEB3TPReCalcNotifyUnitService");

        /*
         * Orix Vantive連携サービスを登録
         */
        Services.registerService(
            WEB3TPOrixVantiveService.class,
            new WEB3TPOrixVantiveServiceImpl());
        log.debug("WEB3TPOrixVantiveService registered.");

        // TransactionalInterceptorを登録
        Services.addInterceptor(
            WEB3TPOrixVantiveService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        log.debug("TransactionalInterceptor added to WEB3TPOrixVantiveService");
        
        /*
         * 入金請求管理サービスを登録
         */
         Services.registerService(
             WEB3TPPaymentRequisitionManageService.class,
             new WEB3TPPaymentRequisitionManageServiceImpl());
         log.debug("WEB3TPPaymentRequisitionManageService registered.");
         
         // WEB3TPTradingPowerServiceInterceptor
         Services.addInterceptor(
             WEB3TPPaymentRequisitionManageService.class,
             new WEB3TPTradingPowerServiceInterceptor());
         log.debug("WEB3TPTradingPowerServiceInterceptor added to WEB3TPPaymentRequisitionManageService");

         // WEB3LogSysTimeInterceptor
         Services.addInterceptor(
             WEB3TPPaymentRequisitionManageService.class,
             new WEB3LogSysTimeInterceptor());
         log.debug("WEB3LogSysTimeInterceptor added to WEB3TPPaymentRequisitionManageService");
         
         // TransactionalInterceptorを登録
         Services.addInterceptor(
             WEB3TPPaymentRequisitionManageService.class,
             new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
         log.debug("TransactionalInterceptor added to WEB3TPPaymentRequisitionManageService");

        // 債券シンプレクス連携サービス
        Services.registerService(
            WEB3TPBondSimplexCooperationService.class,
            new WEB3TPBondSimplexCooperationServiceImpl());

        Services.addInterceptor(
            WEB3TPBondSimplexCooperationService.class,
            new WEB3TPTradingPowerServiceInterceptor());

        Services.addInterceptor(
            WEB3TPBondSimplexCooperationService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // Message の登録処理 ----------------------------------

        // 余力計算通知リクエスト を登録
        regClass(WEB3TPReCalcNotifyRequest.class);
        // 余力計算通知レスポンス を登録
        regClass(WEB3TPReCalcNotifyResponse.class);
        // Orix余力計算結果リクエスト を登録
        regClass(WEB3TPOrixTpCalcResultRequest.class);
        // Orix余力計算結果レスポンス を登録
        regClass(WEB3TPOrixTpCalcResultResponse.class);

        // Handler の登録処理 ------------------------------

        // 余力計算通知ハンドラの登録
        regHandler(
            WEB3TPLibPlugin.class,
            WEB3TPReCalcNotifyRequest.class,
            WEB3TPReCalcNotifyHandler.class,
            "reCalcNotifyRequest");
        // Orix余力計算結果ハンドラの登録
        regHandler(
            WEB3TPLibPlugin.class,
            WEB3TPOrixTpCalcResultRequest.class,
            WEB3TPOrixTpCalcResultHandler.class,
            "orixTpCalcResultRequest");

        log.exiting(METHOD_NAME);
    }
}
@

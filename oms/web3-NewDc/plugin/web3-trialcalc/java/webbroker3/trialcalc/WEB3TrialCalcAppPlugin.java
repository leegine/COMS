head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.25.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-TrialCalc プラグインクラス(WEB3TrialCalcAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History    : 2005/01/05 中尾寿彦(SRA) 新規作成
*/
package webbroker3.trialcalc;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3LogSysTimeInterceptor;

import webbroker3.trialcalc.data.WEB3trialcalcAccountDatabaseExtensions;
import webbroker3.trialcalc.handler.WEB3TrialCalcEstimatedAmountCalServiceHandler;
import webbroker3.trialcalc.handler.WEB3TrialCalcProfitLossCalHandler;
import webbroker3.trialcalc.handler.WEB3TrialCalcPortfolioHandler;
import webbroker3.trialcalc.handler.WEB3TrialCalcProfitLossBreakEvenPointCalHandler;
import webbroker3.trialcalc.message.WEB3TrialCalcBreakEvenPointRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcBreakEvenPointResponse;
import webbroker3.trialcalc.message.WEB3TrialCalcBreakEvenPointInputRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcBreakEvenPointInputResponse;
import webbroker3.trialcalc.message.WEB3TrialCalcDeliveryPriceCalcInputRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcDeliveryPriceCalcInputResponse;
import webbroker3.trialcalc.message.WEB3TrialCalcDeliveryPriceCalcRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcDeliveryPriceCalcResponse;
import webbroker3.trialcalc.message.WEB3TrialCalcProfitLossCalcInputRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcProfitLossCalcInputResponse;
import webbroker3.trialcalc.message.WEB3TrialCalcProfitLossCalcRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcProfitLossCalcResponse;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioDisplayRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioDisplayResponse;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioEditInputRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioEditInputResponse;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioEditRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioEditResponse;
import webbroker3.trialcalc.service.delegate.WEB3TrialCalcEstimatedAmountCalService;
import webbroker3.trialcalc.service.delegate.WEB3TrialCalcPortfolioService;
import webbroker3.trialcalc.service.delegate.WEB3TrialCalcProfitLossBreakEvenPointCalService;
import webbroker3.trialcalc.service.delegate.WEB3TrialCalcProfitLossCalService;
import webbroker3.trialcalc.service.delegate.stdimpls.WEB3TrialCalcEstimatedAmountCalServiceImpl;
import webbroker3.trialcalc.service.delegate.stdimpls.WEB3TrialCalcPortfolioServiceImpl;
import webbroker3.trialcalc.service.delegate.stdimpls.WEB3TrialCalcProfitLossCalServiceImpl;
import webbroker3.trialcalc.service.delegate.stdimpls.
    WEB3TrialCalcProfitLossBreakEvenPointCalServiceImpl;

/**
 * Webbroker3-TrialCalc プラグインクラス。<BR>
 * @@author Amarnath
 * @@version 1.0
 */
public final class WEB3TrialCalcAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TrialCalcAppPlugin.class);

    /**
     * デフォルトコンストラクタ。
     */
    public WEB3TrialCalcAppPlugin()
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
        plug(WEB3TrialCalcAppPlugin.class);
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

        // DatabaseExtensions のプラグイン処理
        WEB3trialcalcAccountDatabaseExtensions.plug();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        // Service の登録処理
        // 1) 計算サービス損益計算サービス
        Services.registerService(WEB3TrialCalcProfitLossCalService.class,
            new WEB3TrialCalcProfitLossCalServiceImpl());
        // 2) 計算サービス損益分岐点計算サービス
        Services.registerService(WEB3TrialCalcEstimatedAmountCalService.class,
            new WEB3TrialCalcEstimatedAmountCalServiceImpl());
        // 3) 計算サービスポートフォリオサービス
        Services.registerService(WEB3TrialCalcPortfolioService.class,
            new WEB3TrialCalcPortfolioServiceImpl());
        // 4) 計算サービス受渡代金計算サービス
        Services.registerService(WEB3TrialCalcProfitLossBreakEvenPointCalService.class,
            new WEB3TrialCalcProfitLossBreakEvenPointCalServiceImpl());

        // サービスインタセプタの設定
        // 1) 計算サービス損益計算サービス
        Services.addInterceptor(
            WEB3TrialCalcProfitLossCalService.class,
                new WEB3TrialCalcProfitLossCalServiceInterceptor());
        // 2) 計算サービス損益分岐点計算サービス
        Services.addInterceptor(
            WEB3TrialCalcEstimatedAmountCalService.class,
                new WEB3TrialCalcEstimatedAmountCalServiceInterceptor());
        // 3) 計算サービスポートフォリオサービス
        Services.addInterceptor(
            WEB3TrialCalcPortfolioService.class,
                new WEB3TrialCalcPortfolioServiceIntercepter());
        // 4) 計算サービス受渡代金計算サービス
        Services.addInterceptor(
            WEB3TrialCalcProfitLossBreakEvenPointCalService.class,
                new WEB3TrialCalcProfitLossBreakEvenPointCalServiceInterceptor());

        // サービス呼び出し前後に
        // 処理開始時刻と処理終了時刻をログ出力するように設定する
        // 1) 計算サービス損益計算サービス
        Services.addInterceptor(
            WEB3TrialCalcProfitLossCalService.class,
            new WEB3LogSysTimeInterceptor());
        // 2) 計算サービス損益分岐点計算サービス
        Services.addInterceptor(
            WEB3TrialCalcEstimatedAmountCalService.class,
            new WEB3LogSysTimeInterceptor());
        // 3) 計算サービスポートフォリオサービス
        Services.addInterceptor(
            WEB3TrialCalcPortfolioService.class,
            new WEB3LogSysTimeInterceptor());
        // 4) 計算サービス受渡代金計算サービス
        Services.addInterceptor(
            WEB3TrialCalcProfitLossBreakEvenPointCalService.class,
            new WEB3LogSysTimeInterceptor());

        // 自動トランザクション設定
        // 1) 計算サービス損益計算サービス
        Services.addInterceptor(
            WEB3TrialCalcProfitLossCalService.class,
                new TransactionalInterceptor(
                    TransactionalInterceptor.TX_CREATE_NEW));
        // 2) 計算サービス損益分岐点計算サービス
        Services.addInterceptor(
            WEB3TrialCalcProfitLossCalService.class,
                new TransactionalInterceptor(
                    TransactionalInterceptor.TX_CREATE_NEW));
        // 3) 計算サービスポートフォリオサービス
        Services.addInterceptor(
            WEB3TrialCalcPortfolioService.class,
                new TransactionalInterceptor(
                    TransactionalInterceptor.TX_CREATE_NEW));
        // 4) 計算サービス受渡代金計算サービス
        Services.addInterceptor(
            WEB3TrialCalcProfitLossBreakEvenPointCalService.class,
                new TransactionalInterceptor(
                    TransactionalInterceptor.TX_CREATE_NEW));

        // Message の登録
        // 1-1) 計算サービス損益計算入力リクエスト・レスポンス
        regClass(WEB3TrialCalcProfitLossCalcInputRequest.class);
        regClass(WEB3TrialCalcProfitLossCalcInputResponse.class);
        // 1-2) 計算サービス損益計算リクエスト・レスポンス
        regClass(WEB3TrialCalcProfitLossCalcRequest.class);
        regClass(WEB3TrialCalcProfitLossCalcResponse.class);
        // 2-1) 計算サービス損益分岐点計算入力リクエスト・レスポンス
        regClass(WEB3TrialCalcDeliveryPriceCalcInputRequest.class);
        regClass(WEB3TrialCalcDeliveryPriceCalcInputResponse.class);
        // 2-2) 計算サービス損益分岐点計算リクエスト・レスポンス
        regClass(WEB3TrialCalcDeliveryPriceCalcRequest.class);
        regClass(WEB3TrialCalcDeliveryPriceCalcResponse.class);
        // 3-1) 計算サービスポートフォリオ表示リクエスト・レスポンス
        regClass(WEB3TrialCalcPortfolioDisplayRequest.class);
        regClass(WEB3TrialCalcPortfolioDisplayResponse.class);
        // 3-2) 計算サービスポートフォリオ編集リクエスト・レスポンス
        regClass(WEB3TrialCalcPortfolioEditInputRequest.class);
        regClass(WEB3TrialCalcPortfolioEditInputResponse.class);
        // 3-3) 計算サービスポートフォリオ編集入力リクエスト・レスポンス
        regClass(WEB3TrialCalcPortfolioEditRequest.class);
        regClass(WEB3TrialCalcPortfolioEditResponse.class);
        // 4-1) 計算サービス受渡代金計算入力リクエスト・レスポンス
        regClass(WEB3TrialCalcBreakEvenPointInputRequest.class);
        regClass(WEB3TrialCalcBreakEvenPointInputResponse.class);
        // 4-2) 計算サービス受渡代金計算リクエスト・レスポンス
        regClass(WEB3TrialCalcBreakEvenPointRequest.class);
        regClass(WEB3TrialCalcBreakEvenPointResponse.class);

        // ハンドラーの登録
        // 1) 計算サービス損益計算ハンドラ
        regHandler(
            WEB3TrialCalcAppPlugin.class,
            WEB3TrialCalcProfitLossCalcInputRequest.class,
            WEB3TrialCalcProfitLossCalHandler.class,
            "getInputDisplay");
        regHandler(
            WEB3TrialCalcAppPlugin.class,
            WEB3TrialCalcProfitLossCalcRequest.class,
            WEB3TrialCalcProfitLossCalHandler.class,
            "calcProfitLoss");
        // 2) 計算ハンドラ損益分岐点計算ハンドラ
        regHandler(
            WEB3TrialCalcAppPlugin.class,
            WEB3TrialCalcDeliveryPriceCalcInputRequest.class,
            WEB3TrialCalcEstimatedAmountCalServiceHandler.class,
            "getInputDisplay");
        regHandler(
            WEB3TrialCalcAppPlugin.class,
            WEB3TrialCalcDeliveryPriceCalcRequest.class,
            WEB3TrialCalcEstimatedAmountCalServiceHandler.class,
            "calcEstimatedAmount");
        // 3) 計算ハンドラポートフォリオハンドラ
        regHandler(
            WEB3TrialCalcAppPlugin.class,
            WEB3TrialCalcPortfolioDisplayRequest.class,
            WEB3TrialCalcPortfolioHandler.class,
            "getPortfolioDisplay");
        regHandler(
            WEB3TrialCalcAppPlugin.class,
            WEB3TrialCalcPortfolioEditInputRequest.class,
            WEB3TrialCalcPortfolioHandler.class,
            "getPortfolioEditInput");
        regHandler(
            WEB3TrialCalcAppPlugin.class,
            WEB3TrialCalcPortfolioEditRequest.class,
            WEB3TrialCalcPortfolioHandler.class,
             "updatePortfolioEdit");
        // 4) 計算ハンドラ受渡代金計算ハンドラ
        regHandler(
            WEB3TrialCalcAppPlugin.class,
            WEB3TrialCalcBreakEvenPointInputRequest.class,
            WEB3TrialCalcProfitLossBreakEvenPointCalHandler.class,
             "getInputDisplay");
        regHandler(
            WEB3TrialCalcAppPlugin.class,
            WEB3TrialCalcBreakEvenPointRequest.class,
            WEB3TrialCalcProfitLossBreakEvenPointCalHandler.class,
             "calcProfitLossBreakPoint");
        log.exiting(METHOD_NAME);
    }
}@

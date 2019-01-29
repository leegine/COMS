head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.00.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAfterRepayTransitionReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 返済後余力情報画面表示サービスImpl(WEB3TPAfterRepayTransitionReferenceServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/04/08 nakazato(ACT) 新規作成
 */
package webbroker3.tradingpower.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3MarginAfterRepayCalcInfoResponse;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginService;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerAfterRepayService;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcMarginAfterRepay;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.message.WEB3TPAfterRepayTransitionReferenceRequest;
import webbroker3.tradingpower.message.WEB3TPAfterRepayTransitionReferenceResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPAfterRepayTransitionReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (返済後余力情報画面表示サービスImpl)
 */
public class WEB3TPAfterRepayTransitionReferenceServiceImpl
        extends WEB3TPAssetTradingPowerServiceImpl
        implements WEB3TPAfterRepayTransitionReferenceService
{

    /**
     * ログ出力ユーティリティ。 <BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
        .getInstance(WEB3TPAssetTradingPowerServiceImpl.class);

    /**
     * @@roseuid 4255DAAC0262
     */
    public WEB3TPAfterRepayTransitionReferenceServiceImpl()
    {

    }

    /**
     * (execute)<BR>
     * <BR>
     * ※シーケンス図「（返済後余力情報画面表示サービス）execute」参照<BR>
     * <BR>
     * @@param リクエスト
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //validate注文受付可能
		WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //(返済後余力)信用取引返済注文リクエスト
        WEB3TPAfterRepayTransitionReferenceRequest l_afterRepayRequest = (WEB3TPAfterRepayTransitionReferenceRequest) l_request;

        //信用取引返済サービス取得
        WEB3MarginCloseMarginService l_closeMarginService = (WEB3MarginCloseMarginService) Services
            .getService(WEB3MarginCloseMarginService.class);

        //信用取引返済後余力計算情報レスポンスを取得
        WEB3MarginAfterRepayCalcInfoResponse l_calcInfoResponse = (WEB3MarginAfterRepayCalcInfoResponse) l_closeMarginService
            .execute(l_afterRepayRequest);

        //補助口座取得
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        //返済後余力サービスを取得
        WEB3TPTradingPowerAfterRepayService l_afterRepayService = (WEB3TPTradingPowerAfterRepayService) Services
            .getService(WEB3TPTradingPowerAfterRepayService.class);

        //返済後資産余力情報オブジェクトを取得
        WEB3TPTradingPowerCalcMarginAfterRepay l_calcMarginAfterRepay = l_afterRepayService
            .createWEB3TPTradingPowerCalcAfterRepay(
                    l_subAccount, l_calcInfoResponse.interceptor,
                    l_calcInfoResponse.orderSpec);

        //(返済後余力)余力推移画面表示レスポンス
        WEB3TPAfterRepayTransitionReferenceResponse l_afterRepayResponse = (WEB3TPAfterRepayTransitionReferenceResponse) l_afterRepayRequest
            .createResponse();

        //(返済後余力)余力推移画面表示レスポンスに値をセット
        this.createTransitionReferenceMargin(
                l_calcMarginAfterRepay, l_afterRepayResponse);

        //T+0..5の間、繰り返し処理
        for(int index = 0; index <= WEB3TPSpecifiedPointDef.T_5; index++)
        {
            //発注分返済決済損益をセット
            l_afterRepayResponse.transitionReferenceUnits[index].orderRepaySettleProfitLoss = format(l_calcMarginAfterRepay
                .getOrderRepaySettleProfitLoss(index));
            //今回発注分返済決済損益をセット
            l_afterRepayResponse.transitionReferenceUnits[index].currOrderRepaySettleProfitLoss = format(l_calcMarginAfterRepay
                .getCurrOrderRepaySettleProfitLoss(index));
        }

        //(返済後余力)余力推移画面表示レスポンスを返却
        log.exiting(STR_METHOD_NAME);
        return l_afterRepayResponse;
    }
}@

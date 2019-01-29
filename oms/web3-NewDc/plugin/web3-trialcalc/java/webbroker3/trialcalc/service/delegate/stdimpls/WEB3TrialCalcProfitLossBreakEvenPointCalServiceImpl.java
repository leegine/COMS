head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.43.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcProfitLossBreakEvenPointCalServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービス損益分岐点計算サービスImpl(WEB3TrialCalcProfitLossBreakEvenPointCalServiceImpl.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import webbroker3.trialcalc.service.delegate.WEB3TrialCalcClientRequestService;
import webbroker3.trialcalc.service.delegate.WEB3TrialCalcProfitLossBreakEvenPointCalService;
import webbroker3.trialcalc.define.WEB3TrialCalcEquityMiniDivDef;
import webbroker3.trialcalc.message.WEB3TrialCalcBreakEvenPointRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcBreakEvenPointResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.define.WEB3MarginTradingDivDef;

import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.trialcalc.message.WEB3TrialCalcBreakEvenPointInputRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcBreakEvenPointInputResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * （計算サービス損益分岐点計算サービスImpl）<BR>
 * <BR>
 * 損益分岐点計算サービス実装クラス。<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * Break-even point calculation service mounting class. <BR>
 * @@author Babu Prasad
 * @@version 1.0
 */
public class WEB3TrialCalcProfitLossBreakEvenPointCalServiceImpl
    extends WEB3TrialCalcClientRequestService
    implements WEB3TrialCalcProfitLossBreakEvenPointCalService
{

    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcProfitLossBreakEvenPointCalServiceImpl.class);
    /**
     * @@roseuid 41C815D002A0
     */
    public WEB3TrialCalcProfitLossBreakEvenPointCalServiceImpl()
    {

    }

    /**
     * 損益分岐点計算サービス処理を実施する。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Execute the break-even point calculation service processing. <BR>
     * @@param l_request - リクエストデータ。
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException WEB3BaseException
     * システム共通（web3-common）.(web3)システム実装クラス_common.WEB3BaseException
     * @@roseuid 41908581017B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3TrialCalcBreakEvenPointInputRequest)
        {
            l_response = getInputDisplay((WEB3TrialCalcBreakEvenPointInputRequest) l_request);
        } else if (l_request instanceof WEB3TrialCalcBreakEvenPointRequest)
        {
            l_response = calcProfitLossBreakPoint((WEB3TrialCalcBreakEvenPointRequest) l_request);
        } else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "INPUT リクエスト NOT 売付一覧リクエスト");
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * （get入力画面）<BR>
     * <BR>
     * 損益分岐点計算入力画面表示サービスを実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（損益分岐点計算サービス）get入力画面」参照。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Perform the break-even point calculation input screen display service. <BR>
     * Refer to sequence chart "(break-even point calculation service)
     * getInputDisplay". <BR>
     * @@param l_request - （リクエストデータ）
     * @@return webbroker3.trialcalc.message.WEB3TrialCalcBreakEvenPointInputResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 4192CF440142
     */
    protected WEB3TrialCalcBreakEvenPointInputResponse getInputDisplay(
        WEB3TrialCalcBreakEvenPointInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputDisplay(WEB3TrialCalcBreakEvenPointInputRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3TrialCalcBreakEvenPointInputResponse l_response = null;

        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        l_response = (WEB3TrialCalcBreakEvenPointInputResponse) l_request.createResponse();
        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * （calc損益分岐点）<BR>
     * <BR>
     * 損益分岐点計算サービスを実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（損益分岐点計算サービス）calc損益分岐点」参照。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Perform the break-even point calculation service. <BR>
     * Refer to sequence chart "(break-even point calculation service)
     * calcProfitLossBreakPoint". <BR>
     * @@param l_request - （リクエストデータ）
     * @@return webbroker3.trialcalc.message.WEB3TrialCalcBreakEvenPointResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 4192CF440152
     */
    protected WEB3TrialCalcBreakEvenPointResponse
        calcProfitLossBreakPoint(WEB3TrialCalcBreakEvenPointRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcProfitLossBreakPoint(WEB3TrialCalcBreakEvenPointRequest)";
        log.entering(STR_METHOD_NAME);
        SubAccount l_subAccount = null;
        WEB3GentradeSubAccount l_genSubAccount = null;

        OrderCategEnum l_orderCateg = null;
        MainAccountTypeEnum l_mainAccountTypeEnum = null;
        WEB3TrialCalcBreakEvenPointResponse l_response = null;
        WEB3GentradeBranch l_branch = null;
        WEB3EquityOrderManager l_equityOrderManager = null;
        WEB3GentradeMarket l_market = null;
        WEB3GentradeCommission l_genTradeComm = null;
        WEB3EquityBizLogicProvider l_equityBizLogicProvider = null;
        BigDecimal l_bdBuyAmtPrice = null;
        BigDecimal l_bdOrderQuantity = null;
        BigDecimal l_bdRestraintTurnOver = null;
        BigDecimal l_bdExpensesAmount = null;
        BigDecimal l_bdBuyPrice = null;
        BigDecimal l_bdDealingUnit = null;
        BigDecimal l_bdgenCommissionBuy = null;
        BigDecimal l_bdCommisionPromotion = null;
        BigDecimal l_bdBreakEventPoint = null;
        BigDecimal l_bdSellAmount = null;
        BigDecimal l_bdBuyCommision = null;
        BigDecimal l_bdBuySalesTax = null;
        BigDecimal l_bdSellCommision = null;
        BigDecimal l_bdSellSalesTax = null;
        BigDecimal l_bdDealingProfit = null;
        BigDecimal l_bdBuySellComm = null;
        Date l_datBizDate = null;
        Timestamp l_tsBaseDate = null;
        String l_strMarketCode = null;
        String l_strOrderChanel = null;
        String l_strMarginType = null;
        String l_strCommissionProductCode = null;
        double l_dblRestraintTurnOver = 0;
        double l_dblRepaymentNum = 0;
        double l_dblExpensesAmount = 0;
        double l_dblgenCommissionBuy = 0;
        double l_dblgenMinCommissionBuy = 0;
        double l_dblCommissionBuy = 0;
        double l_dblCommisionPromotion = 0;
        double l_dblAmount = 0;
        double l_dblAmount1 = 0;
        double l_dblCalcSalesTaxBuy = 0;
        double l_dblCalcSalesTaxBuy1 = 0;
        boolean l_blnIsLimitPrice = false;
        boolean l_blnIsBreakPoint = true;

        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        l_request.validate();
        l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();
        l_branch = ((WEB3GentradeSubAccount) this.getSubAccount()).getWeb3GenBranch();

        l_bdBuyAmtPrice = new BigDecimal(l_request.buyPrice);
        l_bdOrderQuantity = new BigDecimal(l_request.orderQuantity);
        l_bdRestraintTurnOver = l_bdBuyAmtPrice.multiply(l_bdOrderQuantity);

        l_dblRestraintTurnOver = l_bdRestraintTurnOver.doubleValue();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        l_equityOrderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();

        MainAccount l_mainAcc = (MainAccount) l_subAccount.getMainAccount();
        MainAccountParams l_mainAccParams = (MainAccountParams) l_mainAcc.getDataSourceObject();
        l_mainAccountTypeEnum = (MainAccountTypeEnum) l_mainAccParams.getAccountType();
        l_equityOrderManager.validateMaxHandlingPrice(
            l_branch,
            l_market,
            l_dblRestraintTurnOver,
            l_mainAccountTypeEnum);

        l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        l_genSubAccount = (WEB3GentradeSubAccount) this.getSubAccount();
        l_strOrderChanel = l_request.orderForm;
        l_strMarginType = WEB3MarginTradingDivDef.DEFAULT;
        l_equityBizLogicProvider =
            (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();

        l_genTradeComm =
            l_equityBizLogicProvider.createCommission(
                l_genSubAccount,
                l_strMarketCode,
                l_datBizDate,
                l_strOrderChanel,
                l_strMarginType,
                l_dblRepaymentNum,
                l_orderCateg);

        if (WEB3TrialCalcEquityMiniDivDef.EQUITY.equals(l_request.equityMiniDiv))
        {
            l_blnIsLimitPrice = true;
        } else
        {
            l_blnIsLimitPrice = false;
        }
        l_genTradeComm.setIsLimitPrice(l_blnIsLimitPrice);

        l_strCommissionProductCode = WEB3CommisionProductCodeDef.MINI_STOCK;
        if (WEB3TrialCalcEquityMiniDivDef.MINI_STOCK_DIVISION.equals(l_request.equityMiniDiv))
        {
            l_genTradeComm.setCommissionProductCode(l_strCommissionProductCode);
        }

        l_bdBuyPrice = new BigDecimal(l_request.buyPrice);

        if (WEB3TrialCalcEquityMiniDivDef.EQUITY.equals(l_request.equityMiniDiv))
        {
            l_bdExpensesAmount = l_bdOrderQuantity.multiply(l_bdBuyPrice);
        } else
        {
			l_bdDealingUnit = new BigDecimal(l_request.dealingUnit);
            l_bdExpensesAmount = l_bdDealingUnit.multiply(l_bdBuyPrice);
        }
        l_dblExpensesAmount = l_bdExpensesAmount.doubleValue();
        l_genTradeComm.setExpensesCalcAmount(l_dblExpensesAmount);

        l_equityBizLogicProvider.calcCommission(l_genTradeComm, l_subAccount);

        l_dblgenCommissionBuy = l_genTradeComm.getCommission();
        l_dblgenMinCommissionBuy = l_genTradeComm.getMinCommission();

        if (WEB3TrialCalcEquityMiniDivDef.EQUITY.equals(l_request.equityMiniDiv))
        {
            l_dblCommissionBuy = Math.round(l_dblgenCommissionBuy);
        } else
        {
            l_bdgenCommissionBuy = new BigDecimal(l_dblgenCommissionBuy);
            l_bdCommisionPromotion = l_bdOrderQuantity.multiply(l_bdgenCommissionBuy);
            l_bdCommisionPromotion = l_bdCommisionPromotion.divide(
                                                        l_bdDealingUnit, BigDecimal.ROUND_DOWN);
            l_dblCommisionPromotion = l_bdCommisionPromotion.doubleValue();
            if (l_dblCommisionPromotion < l_dblgenMinCommissionBuy)
            {
                l_dblCommissionBuy = Math.round(l_dblgenMinCommissionBuy);
            } else
            {
                l_dblCommissionBuy = Math.round(l_dblCommisionPromotion);
            }
        }
        l_dblAmount = l_dblCommissionBuy;
        l_tsBaseDate = new Timestamp(l_datBizDate.getTime());
        l_dblCalcSalesTaxBuy =
            l_equityBizLogicProvider.calcSalesTax(l_dblAmount, l_tsBaseDate, l_subAccount);

        l_bdBreakEventPoint = new BigDecimal(l_request.buyPrice);
        while (l_blnIsBreakPoint)
        {
            if (WEB3TrialCalcEquityMiniDivDef.EQUITY.equals(l_request.equityMiniDiv))
            {
                l_bdExpensesAmount = l_bdOrderQuantity.multiply(l_bdBreakEventPoint);
            } else
            {
                l_bdExpensesAmount = l_bdDealingUnit.multiply(l_bdBreakEventPoint);
            }
            l_dblExpensesAmount = l_bdExpensesAmount.doubleValue();
            l_genTradeComm.setExpensesCalcAmount(l_dblExpensesAmount);

            l_equityBizLogicProvider.calcCommission(l_genTradeComm, l_subAccount);

            l_dblgenCommissionBuy = l_genTradeComm.getCommission();
            l_dblgenMinCommissionBuy = l_genTradeComm.getMinCommission();

            if (WEB3TrialCalcEquityMiniDivDef.EQUITY.equals(l_request.equityMiniDiv))
            {
                l_dblCommissionBuy = Math.round(l_dblgenCommissionBuy);
            } else
            {
                l_bdgenCommissionBuy = new BigDecimal(l_dblgenCommissionBuy);
                l_bdCommisionPromotion = l_bdOrderQuantity.multiply(l_bdgenCommissionBuy);
                l_bdCommisionPromotion = l_bdCommisionPromotion.divide(
                                                            l_bdDealingUnit, BigDecimal.ROUND_DOWN);
                l_dblCommisionPromotion = l_bdCommisionPromotion.doubleValue();
                if (l_dblCommisionPromotion < l_dblgenMinCommissionBuy)
                {
                    l_dblCommissionBuy = Math.round(l_dblgenMinCommissionBuy);
                } else
                {
                    l_dblCommissionBuy = Math.round(l_dblCommisionPromotion);
                }
            }
            l_dblAmount1 = l_dblCommissionBuy;
            l_tsBaseDate = new Timestamp(l_datBizDate.getTime());
            l_dblCalcSalesTaxBuy1 =
                l_equityBizLogicProvider.calcSalesTax(l_dblAmount1, l_tsBaseDate, l_subAccount);
            l_bdSellAmount = l_bdBreakEventPoint.multiply(l_bdOrderQuantity);

            l_bdBuyCommision = new BigDecimal(l_dblAmount);
            l_bdBuySalesTax = new BigDecimal(l_dblCalcSalesTaxBuy);
            l_bdSellCommision = new BigDecimal(l_dblAmount1);
            l_bdSellSalesTax = new BigDecimal(l_dblCalcSalesTaxBuy1);
            l_bdDealingProfit = new BigDecimal(l_bdSellAmount.doubleValue());
            l_bdBuyAmtPrice = l_bdBuyPrice.multiply(l_bdOrderQuantity);
            l_bdDealingProfit = l_bdDealingProfit.subtract(l_bdBuyAmtPrice);

            l_bdBuySellComm = new BigDecimal(l_bdBuyCommision.doubleValue());
            l_bdBuySellComm = l_bdBuySellComm.add(l_bdBuySalesTax);
            l_bdBuySellComm = l_bdBuySellComm.add(l_bdSellCommision);
            l_bdBuySellComm = l_bdBuySellComm.add(l_bdSellSalesTax);

            l_bdDealingProfit = l_bdDealingProfit.subtract(l_bdBuySellComm);

            if (l_bdDealingProfit.intValue() >= 0)
            {
                l_blnIsBreakPoint = false;
            }
            else 
            {
                l_bdBreakEventPoint = l_bdBreakEventPoint.add(new BigDecimal(1));
            }
        }
        l_response = (WEB3TrialCalcBreakEvenPointResponse) l_request.createResponse();

		// (U01444 No.1)未フォーマットを修正。        
        DecimalFormat l_dFormat = new DecimalFormat("0");
        
        l_response.breakEvenPoint = l_dFormat.format(l_bdBreakEventPoint);
        l_response.buyCommission = l_dFormat.format(l_bdBuyCommision);
        l_response.buyCommissionTax = l_dFormat.format(l_bdBuySalesTax);
        l_response.sellCommission = l_dFormat.format(l_bdSellCommision);
        l_response.sellCommissionTax = l_dFormat.format(l_bdSellSalesTax);
        l_response.commissionCourse = l_genTradeComm.getCommissionCourseDiv();
        l_response.dealingProfit = l_dFormat.format(l_bdDealingProfit);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@

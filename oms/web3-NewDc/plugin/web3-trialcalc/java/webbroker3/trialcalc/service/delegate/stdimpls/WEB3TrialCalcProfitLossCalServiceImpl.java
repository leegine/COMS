head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.43.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcProfitLossCalServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービス損益計算サービスImpl(WEB3TrialCalcProfitLossCalServiceImpl.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityOrderManager;

import webbroker3.trialcalc.define.WEB3TrialCalcEquityMiniDivDef;
import webbroker3.trialcalc.message.WEB3TrialCalcProfitLossCalcInputRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcProfitLossCalcInputResponse;
import webbroker3.trialcalc.message.WEB3TrialCalcProfitLossCalcRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcProfitLossCalcResponse;
import webbroker3.trialcalc.service.delegate.WEB3TrialCalcClientRequestService;
import webbroker3.trialcalc.service.delegate.WEB3TrialCalcProfitLossCalService;

/**
 * （計算サービス損益計算サービスImpl）<BR>
 * <BR>
 * 損益計算サービス実装クラス。<BR>
 * <BR>
 * WEB3TrialCalcProfitLossCalServiceImpl<BR>
 * <BR>
 * @@author Rajesh Sharma
 * @@version 1.0
 */
public class WEB3TrialCalcProfitLossCalServiceImpl
    extends WEB3TrialCalcClientRequestService implements WEB3TrialCalcProfitLossCalService
{
    /**
     * Variable for log of WEB3LogUtility
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcProfitLossCalServiceImpl.class);

    /**
     * @@roseuid 41C817E7032D
     */
    public WEB3TrialCalcProfitLossCalServiceImpl()
    {
    }

    /**
     * 損益計算サービス処理を実施する。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Execute the profit and loss calculation service processing. <BR>
     * @@param l_request - リクエストデータ。
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * システム共通（web3-common）.(web3)システム実装クラス_common.WEB3BaseException
     * @@roseuid 41906111038E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3TrialCalcProfitLossCalcInputRequest)
        {
            l_response = getInputDisplay((WEB3TrialCalcProfitLossCalcInputRequest) l_request);
        } else if (l_request instanceof WEB3TrialCalcProfitLossCalcRequest)
        {
            l_response = calcProfitLoss((WEB3TrialCalcProfitLossCalcRequest) l_request);
        } else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "INPUT リクエスト NOT 損益計算リクエスト");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （get入力画面）<BR>
     * <BR>
     * 損益計算入力画面表示サービスを実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（損益計算サービス）get入力画面」参照。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Perform the profit and loss calculation input display .<BR>
     * Refer to sequence chart "(profit and loss calculation service)
     * getInputDisplay". <BR>
     * @@param l_request WEB3TrialCalcProfitLossCalcInputRequest
     * @@return WEB3TrialCalcProfitLossCalcInputResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 4192BE910161
     */
    protected WEB3TrialCalcProfitLossCalcInputResponse
        getInputDisplay(WEB3TrialCalcProfitLossCalcInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputDisplay(WEB3TrialCalcProfitLossCalcInputRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3TrialCalcProfitLossCalcInputResponse l_response = null;
        //Step  1.1
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        //Step 1,2
        l_response = (WEB3TrialCalcProfitLossCalcInputResponse) l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （calc損益）<BR>
     * <BR>
     * 損益計算サービスを実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（損益計算サービス）calc損益」参照。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Perform the profit and loss calculation service.  <BR>
     * Refer to sequence chart "(profit and loss calculation service) calcProfitLoss".
     * <BR>
     * @@param l_request WEB3TrialCalcProfitLossCalcRequest
     * @@return WEB3TrialCalcProfitLossCalcResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 4192C5A4023C
     */
    protected WEB3TrialCalcProfitLossCalcResponse
        calcProfitLoss(WEB3TrialCalcProfitLossCalcRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcProfitLoss(WEB3TrialCalcProfitLossCalcRequest)";
        log.entering(STR_METHOD_NAME);

        //Step  1.1
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //Step  1.2
        l_request.validate();

        //Step 1.3
		WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
		TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
		WEB3EquityOrderManager l_orderManager =
		    (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
		WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
		MainAccount l_mainAcc = l_subAccount.getMainAccount();
		MainAccountRow l_mainAccRow = (MainAccountRow)l_mainAcc.getDataSourceObject();
		MainAccountTypeEnum l_mainAccountTypeEnum = l_mainAccRow.getAccountType();

		BigDecimal l_bdOrderQuantity = new BigDecimal(l_request.orderQuantity);
		
		//Step 1.4
		BigDecimal l_bdBuyPrice = new BigDecimal(l_request.buyPrice);
        BigDecimal l_bdBuyAmtPrice = l_bdBuyPrice.multiply(l_bdOrderQuantity);
        l_orderManager.validateMaxHandlingPrice(
            l_branch, null, l_bdBuyAmtPrice.doubleValue(), l_mainAccountTypeEnum);

        //Step 1.5
        BigDecimal l_bdSellPrice = new BigDecimal(l_request.sellPrice);
		BigDecimal l_bdSellAmtPrice = l_bdSellPrice.multiply(l_bdOrderQuantity);
        l_orderManager.validateMaxHandlingPrice(
            l_branch, null, l_bdSellAmtPrice.doubleValue(), l_mainAccountTypeEnum);

        //Step 1.6
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //Step 1.7
		WEB3EquityBizLogicProvider l_equityBizLogicProvider =
            (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();

		WEB3GentradeCommission l_genTradeComm =
            l_equityBizLogicProvider.createCommission(
		        l_subAccount,
                null,
                l_datBizDate,
		        l_request.orderForm,
		        WEB3MarginTradingDivDef.DEFAULT,
                0,
		        OrderCategEnum.ASSET);

        //step1.8
        if (l_request.equityMiniDiv.equals(WEB3TrialCalcEquityMiniDivDef.EQUITY))
        {
			l_genTradeComm.setIsLimitPrice(true);
        }
        else
        {
			l_genTradeComm.setIsLimitPrice(false);
        }

        //Step 1.9
        if (l_request.equityMiniDiv.equals(WEB3TrialCalcEquityMiniDivDef.MINI_STOCK_DIVISION))
        { //Step 9.1
            l_genTradeComm.setCommissionProductCode(WEB3CommisionProductCodeDef.MINI_STOCK);
        }
        
        //Step 10
        //Step 1.10.1
		BigDecimal l_bdDealingUnit = null;
		double l_dblExpensesAmount = 0.0D;
        if (!l_request.equityMiniDiv.equals(WEB3TrialCalcEquityMiniDivDef.EQUITY))
        {
			l_bdDealingUnit = new BigDecimal(l_request.dealingUnit);
        }

        if (l_request.equityMiniDiv.equals(WEB3TrialCalcEquityMiniDivDef.EQUITY))
        {
            l_dblExpensesAmount = l_bdBuyAmtPrice.doubleValue();
        }
        else
        {
            BigDecimal l_bdBuyExpense = l_bdBuyPrice.multiply(l_bdDealingUnit);
            l_dblExpensesAmount = l_bdBuyExpense.doubleValue();
        }
        l_genTradeComm.setExpensesCalcAmount(l_dblExpensesAmount);

        //Step 1.10.2
        l_equityBizLogicProvider.calcCommission(l_genTradeComm, l_subAccount);

        //Step 1.10.3
		BigDecimal l_bdCommissionBuy = new BigDecimal(l_genTradeComm.getCommission());
        //Step 1.10.4
        double l_dblMinCommissionBuy = l_genTradeComm.getMinCommission();

        //Step 1.10.5
		double l_dblCommissionBuy = 0;
        if (l_request.equityMiniDiv.equals(WEB3TrialCalcEquityMiniDivDef.EQUITY))
        {
            l_dblCommissionBuy = l_genTradeComm.getCommission();
        }
        else
        {
            l_bdCommissionBuy = l_bdCommissionBuy.multiply(l_bdOrderQuantity);
            l_bdCommissionBuy = l_bdCommissionBuy.divide(l_bdDealingUnit, BigDecimal.ROUND_DOWN);
            l_dblCommissionBuy = l_bdCommissionBuy.doubleValue();
            /*
             * check for the condition that genTradeCommissionBuy is less than MinCommissionBuy
             */
            if (l_dblCommissionBuy < l_dblMinCommissionBuy)
            {
                l_dblCommissionBuy = l_dblMinCommissionBuy;
            }
        }

        //Step 1.10.6
        Timestamp l_tsBaseDate = new Timestamp(l_datBizDate.getTime());
        double l_dblCalcSalesTaxBuy =
            l_equityBizLogicProvider.calcSalesTax(l_dblCommissionBuy, l_tsBaseDate, l_subAccount);

        //Step 1.11
        //Step 1.11.1
        BigDecimal l_bdSellExpense;
        if (l_request.equityMiniDiv.equals(WEB3TrialCalcEquityMiniDivDef.EQUITY))
        {
			l_dblExpensesAmount = l_bdSellAmtPrice.doubleValue();
        }
        else
        {
            l_bdSellExpense = l_bdDealingUnit.multiply(l_bdSellPrice);
            l_dblExpensesAmount = l_bdSellExpense.doubleValue();
        }
        l_genTradeComm.setExpensesCalcAmount(l_dblExpensesAmount);

        //Step 1.11.2
        l_equityBizLogicProvider.calcCommission(l_genTradeComm, l_subAccount);

        //Step 1.11.3
        //Step 1.11.4
        double l_dblMinCommissionSell = l_genTradeComm.getMinCommission();
		BigDecimal l_bdCommissionSell = new BigDecimal(l_genTradeComm.getCommission());

        //Step 1.11.5
		double l_dblCommissionSell = 0;
        if (l_request.equityMiniDiv.equals(WEB3TrialCalcEquityMiniDivDef.EQUITY))
        {
            l_dblCommissionSell = l_genTradeComm.getCommission();
        }
        else
        {
            l_bdCommissionSell = l_bdCommissionSell.multiply(l_bdOrderQuantity);
            l_bdCommissionSell = l_bdCommissionSell.divide(l_bdDealingUnit, BigDecimal.ROUND_DOWN);
            l_dblCommissionSell = l_bdCommissionSell.doubleValue();
            /*
             * check for the condition that genTradeCommissionSell is less than MinCommissionSell
             */
            if (l_dblCommissionSell < l_dblMinCommissionSell)
            {
                l_dblCommissionSell = l_dblMinCommissionSell;
            }
        }
        //Step 1.11.6
        double l_dblCalcSalesTaxSell =
            l_equityBizLogicProvider.calcSalesTax(l_dblCommissionSell, l_tsBaseDate, l_subAccount);

        //Step 1.12
		WEB3TrialCalcProfitLossCalcResponse l_response =
		    (WEB3TrialCalcProfitLossCalcResponse) l_request.createResponse();
        l_response.buyCommission = WEB3StringTypeUtility.formatNumber(l_dblCommissionBuy);
        l_response.buyCommissionTax = WEB3StringTypeUtility.formatNumber(l_dblCalcSalesTaxBuy);
        l_response.sellCommission = WEB3StringTypeUtility.formatNumber(l_dblCommissionSell);
        l_response.sellCommissionTax = WEB3StringTypeUtility.formatNumber(l_dblCalcSalesTaxSell);
        l_response.commissionCourse = l_genTradeComm.getCommissionCourseDiv();

		BigDecimal l_bdCommission = new BigDecimal(l_dblCommissionBuy);
        l_bdCommission = l_bdCommission.add(new BigDecimal(l_dblCalcSalesTaxBuy));
        l_bdCommission = l_bdCommission.add(new BigDecimal(l_dblCommissionSell));
        l_bdCommission = l_bdCommission.add(new BigDecimal(l_dblCalcSalesTaxSell));
		BigDecimal l_bdProLossAmt = l_bdSellAmtPrice.subtract(l_bdBuyAmtPrice);
        l_bdProLossAmt = l_bdProLossAmt.subtract(l_bdCommission);
        l_response.prolossAmount = WEB3StringTypeUtility.formatNumber(l_bdProLossAmt.doubleValue());

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@

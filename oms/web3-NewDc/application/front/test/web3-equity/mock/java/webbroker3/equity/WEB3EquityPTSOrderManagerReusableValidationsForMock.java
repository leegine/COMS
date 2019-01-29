head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.01.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityPTSOrderManagerReusableValidationsForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3EquityPTSOrderManagerReusableValidationsForMock.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/24 金傑（中訊）新規作成
*/
package webbroker3.equity;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityPTSOrderManagerReusableValidationsForMock extends WEB3EquityPTSOrderManagerReusableValidations
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSOrderManagerReusableValidationsForMock.class);

    /**
     * （validate決済総建株数(Mock)）<BR>
     * <BR>
     */
     public void validateSettleContractTotalQuantity(
        WEB3GentradeSubAccount l_subAccount,
        long l_lngOrderUnitId,
        WEB3EquityTradedProduct l_equityTradedProduct,
        TaxTypeEnum l_taxTypeEnum,
        String l_strRepaymentType,
        double l_dblRepaymentNum,
        double l_dblQuantity,
        ContractTypeEnum l_contractType) throws WEB3BaseException
     {
        final String STR_METHOD_NAME =
            "validateSettleContractTotalQuantity(WEB3GentradeSubAccount, long, WEB3EquityTradedProduct, TaxTypeEnum, String, double, double, ContractTypeEnum)";
        log.entering(STR_METHOD_NAME);        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityTypeOrderManagerReusableValidations",
                "validateSettleContractTotalQuantity",
                new Class[] 
                  {
                    WEB3GentradeSubAccount.class,
                    long.class,
                    WEB3EquityTradedProduct.class,
                    TaxTypeEnum.class,
                    String.class,
                    double.class,
                    double.class,
                    ContractTypeEnum.class
                   },
                new Object[]{
                    l_subAccount, 
                    new Long(l_lngOrderUnitId),
                    l_equityTradedProduct,
                    l_taxTypeEnum, 
                    l_strRepaymentType, 
                    new Double(l_dblRepaymentNum), 
                    new Double(l_dblQuantity),
                    l_contractType});

            if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.equity.WEB3EquityTypeOrderManagerReusableValidations",
                "validateSettleContractTotalQuantity",
                new Class[] 
                  {
                    WEB3GentradeSubAccount.class,
                    long.class,
                    WEB3EquityTradedProduct.class,
                    TaxTypeEnum.class,
                    String.class,
                    double.class,
                    double.class,
                    ContractTypeEnum.class
                   }))
            {
                //2）MockFor --〉 asWEB3BaseException
                log.exiting(STR_METHOD_NAME);
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityTypeOrderManagerReusableValidations",
                    "validateSettleContractTotalQuantity",
                    new Class[] 
                      {
                        WEB3GentradeSubAccount.class,
                        long.class,
                        WEB3EquityTradedProduct.class,
                        TaxTypeEnum.class,
                        String.class,
                        double.class,
                        double.class,
                        ContractTypeEnum.class
                       }).asWEB3BaseException();

                //3)MockFor --〉 asVoid
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityTypeOrderManagerReusableValidations",
                    "validateSettleContractTotalQuantity",
                    new Class[] 
                          {
                            WEB3GentradeSubAccount.class,
                            long.class,
                            WEB3EquityTradedProduct.class,
                            TaxTypeEnum.class,
                            String.class,
                            double.class,
                            double.class,
                            ContractTypeEnum.class
                           }).asVoid();
                
                return;
            }

            log.exiting(STR_METHOD_NAME);
            super.validateSettleContractTotalQuantity(
                l_subAccount, 
                l_lngOrderUnitId,
                l_equityTradedProduct,
                l_taxTypeEnum, 
                l_strRepaymentType, 
                l_dblRepaymentNum, 
                l_dblQuantity,
                l_contractType);
     }
     
     public void validatePTSOrderForChangeability(Order l_order) throws OrderValidationException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSOrderForChangeability",
                new Class[]
                { Order.class }, new Object[]
                { l_order });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validatePTSOrderForChangeability", new Class[]
                { Order.class }))
        {
            log
                    .debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validatePTSOrderForChangeability()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSOrderForChangeability", new Class[]
                    { Order.class }).asVoid();
            return;
        }
        super.validatePTSOrderForChangeability(l_order);
    }
     
     public void validatePTSOrderForCancellation(Order l_order) throws OrderValidationException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSOrderForCancellation",
                new Class[]
                { Order.class }, new Object[]
                { l_order });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validatePTSOrderForCancellation", new Class[]
                { Order.class }))
        {
            log
                    .debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validatePTSOrderForCancellation()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSOrderForCancellation", new Class[]
                    { Order.class }).asVoid();
            return;
        }
        super.validatePTSOrderForCancellation(l_order);
    }
     
     public WEB3EquityProduct validateProductCode(String l_strProductCode, String l_strInstitutionCode)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateProductCode", new Class[]
                { String.class, String.class }, new Object[]
                { l_strProductCode, l_strInstitutionCode });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateProductCode", new Class[]
                { String.class, String.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validateProductCode()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateProductCode",
                    new Class[]
                    { String.class, String.class }).asWEB3BaseException();
            return (WEB3EquityProduct) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateProductCode",
                    new Class[]
                    { String.class, String.class }).asObject();
        }
        return super.validateProductCode(l_strProductCode, l_strInstitutionCode);
    }
     
     public void validateCapitalGainTaxDealingsReg(TaxTypeEnum l_taxTypeEnum, EqTypeProduct l_eqTypeProduct,
            boolean l_isBuyOrder) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateCapitalGainTaxDealingsReg",
                new Class[]
                { TaxTypeEnum.class, EqTypeProduct.class, boolean.class }, new Object[]
                { l_taxTypeEnum, l_eqTypeProduct, new Boolean(l_isBuyOrder) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateCapitalGainTaxDealingsReg", new Class[]
                { TaxTypeEnum.class, EqTypeProduct.class, boolean.class }))
        {
            log
                    .debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validateCapitalGainTaxDealingsReg()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateCapitalGainTaxDealingsReg", new Class[]
                    { TaxTypeEnum.class, EqTypeProduct.class, boolean.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateCapitalGainTaxDealingsReg", new Class[]
                    { TaxTypeEnum.class, EqTypeProduct.class, boolean.class }).asVoid();
            return;
        }
        super.validateCapitalGainTaxDealingsReg(l_taxTypeEnum, l_eqTypeProduct, l_isBuyOrder);
    }
     
     public void validateOrderCondition(WEB3GentradeSubAccount l_subAccount, long l_lngOrderUnitId,
            WEB3EquityTradedProduct l_tradedProduct, Date l_datOrderBizDate, Date l_datExpirationDate,
            String l_strOrderConditionType, EqTypeExecutionConditionType l_executionCondition,
            boolean l_isCarriedOrder, String l_strMarginTradeType, String l_strPriceConditionType,
            String l_strMarketCode) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateOrderCondition", new Class[]
                { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                        String.class, EqTypeExecutionConditionType.class, boolean.class, String.class, String.class,
                        String.class }, new Object[]
                { l_subAccount, new Long(l_lngOrderUnitId), l_tradedProduct, l_datOrderBizDate, l_datExpirationDate,
                        l_strOrderConditionType, l_executionCondition, new Boolean(l_isCarriedOrder),
                        l_strMarginTradeType, l_strPriceConditionType, l_strMarketCode });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateOrderCondition", new Class[]
                { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                        String.class, EqTypeExecutionConditionType.class, boolean.class, String.class, String.class,
                        String.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validateOrderCondition()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class }).asVoid();
            return;
        }
        super.validateOrderCondition(l_subAccount, l_lngOrderUnitId, l_tradedProduct, l_datOrderBizDate,
                l_datExpirationDate, l_strOrderConditionType, l_executionCondition, l_isCarriedOrder,
                l_strMarginTradeType, l_strPriceConditionType, l_strMarketCode);
    }
     
     public void validatePTSQuantity(TradedProduct l_tradedProduct, WEB3GentradeBranch l_branch,
            double l_dblStockQuantity) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSQuantity", new Class[]
                { TradedProduct.class, WEB3GentradeBranch.class, double.class }, new Object[]
                { l_tradedProduct, l_branch, new Double(l_dblStockQuantity) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validatePTSQuantity", new Class[]
                { TradedProduct.class, WEB3GentradeBranch.class, double.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validatePTSQuantity()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSQuantity",
                    new Class[]
                    { TradedProduct.class, WEB3GentradeBranch.class, double.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSQuantity",
                    new Class[]
                    { TradedProduct.class, WEB3GentradeBranch.class, double.class }).asVoid();
            return;
        }
        super.validatePTSQuantity(l_tradedProduct, l_branch, l_dblStockQuantity);
    }
     
     public void validatePTSLimitOrder(WEB3EquityNewCashBasedOrderSpec l_equityNewCashBasedOrderSpec)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSLimitOrder", new Class[]
                { WEB3EquityNewCashBasedOrderSpec.class }, new Object[]
                { l_equityNewCashBasedOrderSpec });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validatePTSLimitOrder", new Class[]
                { WEB3EquityNewCashBasedOrderSpec.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validatePTSLimitOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSLimitOrder",
                    new Class[]
                    { WEB3EquityNewCashBasedOrderSpec.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSLimitOrder",
                    new Class[]
                    { WEB3EquityNewCashBasedOrderSpec.class }).asVoid();
            return;
        }
        super.validatePTSLimitOrder(l_equityNewCashBasedOrderSpec);
    }
     
     public boolean validatePTSPrice(double l_dblLimitPrice, WEB3EquityTradedProduct l_tradedProduct,
            SubAccount l_subAccount) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSPrice", new Class[]
                { double.class, WEB3EquityTradedProduct.class, SubAccount.class }, new Object[]
                { new Double(l_dblLimitPrice), l_tradedProduct, l_subAccount });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validatePTSPrice", new Class[]
                { double.class, WEB3EquityTradedProduct.class, SubAccount.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validatePTSPrice()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSPrice", new Class[]
                    { double.class, WEB3EquityTradedProduct.class, SubAccount.class }).asWEB3BaseException();
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSPrice", new Class[]
                    { double.class, WEB3EquityTradedProduct.class, SubAccount.class }).asBoolean();
        }
        return super.validatePTSPrice(l_dblLimitPrice, l_tradedProduct, l_subAccount);
    }
     
     public void validateSellableAssetQuantity(SubAccount l_subAccount, TradedProduct l_tradedProduct,
            double l_dblQuantity, TaxTypeEnum l_taxTypeEnum) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateSellableAssetQuantity",
                new Class[]
                { SubAccount.class, TradedProduct.class, double.class, TaxTypeEnum.class }, new Object[]
                { l_subAccount, l_tradedProduct, new Double(l_dblQuantity), l_taxTypeEnum });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateSellableAssetQuantity", new Class[]
                { SubAccount.class, TradedProduct.class, double.class, TaxTypeEnum.class }))
        {
            log
                    .debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validateSellableAssetQuantity()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateSellableAssetQuantity",
                    new Class[]
                    { SubAccount.class, TradedProduct.class, double.class, TaxTypeEnum.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateSellableAssetQuantity",
                    new Class[]
                    { SubAccount.class, TradedProduct.class, double.class, TaxTypeEnum.class }).asVoid();
            return;
        }
        super.validateSellableAssetQuantity(l_subAccount, l_tradedProduct, l_dblQuantity, l_taxTypeEnum);
    }
     
     public Order validateOrderIdForExistence(long orderId) throws OrderValidationException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateOrderIdForExistence",
                new Class[]
                { long.class }, new Object[]
                { new Long(orderId) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateOrderIdForExistence", new Class[]
                { long.class }))
        {
            log
                    .debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validateOrderIdForExistence()");
            return (Order) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateOrderIdForExistence",
                    new Class[]
                    { long.class }).asObject();
        }
        return super.validateOrderIdForExistence(orderId);
    }
     
     public void validatePTSAccountProductOrderStop(SubAccount l_subAccount, long l_lngProductId,
            OrderTypeEnum l_orderType) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSAccountProductOrderStop",
                new Class[]
                { SubAccount.class, long.class, OrderTypeEnum.class }, new Object[]
                { l_subAccount, new Long(l_lngProductId), l_orderType });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validatePTSAccountProductOrderStop", new Class[]
                { SubAccount.class, long.class, OrderTypeEnum.class }))
        {
            log
                    .debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validatePTSAccountProductOrderStop()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSAccountProductOrderStop", new Class[]
                    { SubAccount.class, long.class, OrderTypeEnum.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSAccountProductOrderStop", new Class[]
                    { SubAccount.class, long.class, OrderTypeEnum.class }).asVoid();
            return;
        }
        super.validatePTSAccountProductOrderStop(l_subAccount, l_lngProductId, l_orderType);
    }
     
     public void validatePTSChangeItem(EqTypeOrderUnit l_orderUnit, double l_dblModifiedQuantity,
            double l_dblModifiedLimitPrice, EqTypeExecutionConditionType l_eqTypeExecutionConditionType,
            String l_strModifiedPriceConditionType, String l_strModifiedOrderConditionType,
            String l_strModifiedOrderCondOperator, double l_dblModifiedStopOrderPrice, double l_dblModifiedWLimitPrice,
            EqTypeExecutionConditionType l_modifiedWLimitExecCondType, boolean l_modifiedIsCarriedOrder,
            Date l_datModifiedExpirationDate, EqTypeSettleContractOrderEntry[] l_modifiedSettleContractEntries)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSChangeItem", new Class[]
                { EqTypeOrderUnit.class, double.class, double.class, EqTypeExecutionConditionType.class, String.class,
                        String.class, String.class, double.class, double.class, EqTypeExecutionConditionType.class,
                        boolean.class, Date.class, EqTypeSettleContractOrderEntry[].class }, new Object[]
                { l_orderUnit, new Double(l_dblModifiedQuantity), new Double(l_dblModifiedLimitPrice),
                        l_eqTypeExecutionConditionType, l_strModifiedPriceConditionType,
                        l_strModifiedOrderConditionType, l_strModifiedOrderCondOperator,
                        new Double(l_dblModifiedStopOrderPrice), new Double(l_dblModifiedWLimitPrice),
                        l_modifiedWLimitExecCondType, new Boolean(l_modifiedIsCarriedOrder),
                        l_datModifiedExpirationDate, l_modifiedSettleContractEntries });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validatePTSChangeItem", new Class[]
                { EqTypeOrderUnit.class, double.class, double.class, EqTypeExecutionConditionType.class, String.class,
                        String.class, String.class, double.class, double.class, EqTypeExecutionConditionType.class,
                        boolean.class, Date.class, EqTypeSettleContractOrderEntry[].class }))
        {
            log.debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validatePTSChangeItem()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSChangeItem",
                    new Class[]
                    { EqTypeOrderUnit.class, double.class, double.class, EqTypeExecutionConditionType.class,
                            String.class, String.class, String.class, double.class, double.class,
                            EqTypeExecutionConditionType.class, boolean.class, Date.class,
                            EqTypeSettleContractOrderEntry[].class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSChangeItem",
                    new Class[]
                    { EqTypeOrderUnit.class, double.class, double.class, EqTypeExecutionConditionType.class,
                            String.class, String.class, String.class, double.class, double.class,
                            EqTypeExecutionConditionType.class, boolean.class, Date.class,
                            EqTypeSettleContractOrderEntry[].class }).asVoid();
            return;
        }
        super.validatePTSChangeItem(l_orderUnit, l_dblModifiedQuantity, l_dblModifiedLimitPrice,
                l_eqTypeExecutionConditionType, l_strModifiedPriceConditionType, l_strModifiedOrderConditionType,
                l_strModifiedOrderCondOperator, l_dblModifiedStopOrderPrice, l_dblModifiedWLimitPrice,
                l_modifiedWLimitExecCondType, l_modifiedIsCarriedOrder, l_datModifiedExpirationDate,
                l_modifiedSettleContractEntries);
    }
     
     public void validatePTSChangeOrderRevUpperLimit(EqTypeOrderUnit l_orderUnit, double l_dblQuantity,
            double l_dblLimitPrice, EqTypeExecutionConditionType l_executionConditionType,
            String l_strPriceConditionType) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER
                .setMethodParamsValue("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                        "validatePTSChangeOrderRevUpperLimit", new Class[]
                        { EqTypeOrderUnit.class, double.class, double.class, EqTypeExecutionConditionType.class,
                                String.class }, new Object[]
                        { l_orderUnit, new Double(l_dblQuantity), new Double(l_dblLimitPrice),
                                l_executionConditionType, l_strPriceConditionType });
        if (TestBaseForMock.MOCK_MANAGER
                .isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                        "validatePTSChangeOrderRevUpperLimit", new Class[]
                        { EqTypeOrderUnit.class, double.class, double.class, EqTypeExecutionConditionType.class,
                                String.class }))
        {
            log
                    .debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validatePTSChangeOrderRevUpperLimit()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSChangeOrderRevUpperLimit",
                    new Class[]
                    { EqTypeOrderUnit.class, double.class, double.class, EqTypeExecutionConditionType.class,
                            String.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSChangeOrderRevUpperLimit",
                    new Class[]
                    { EqTypeOrderUnit.class, double.class, double.class, EqTypeExecutionConditionType.class,
                            String.class }).asVoid();
            return;
        }
        super.validatePTSChangeOrderRevUpperLimit(l_orderUnit, l_dblQuantity, l_dblLimitPrice,
                l_executionConditionType, l_strPriceConditionType);
    }    
     
    public void validateMechanismDepositAgree(SubAccount l_subAccount) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
            "validateMechanismDepositAgree",
            new Class[]{SubAccount.class},
            new Object[]{l_subAccount});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
            "validateMechanismDepositAgree",
            new Class[]{SubAccount.class}))
        {
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateMechanismDepositAgree",
                    new Class[]{SubAccount.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateMechanismDepositAgree",
                    new Class[]{SubAccount.class}).asVoid();
            return;
        }
        super.validateMechanismDepositAgree(l_subAccount);
    }

    public void validateInsider(SubAccount l_subAccount, EqTypeProduct l_eqtypeProduct) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateInsider", new Class[]
                { SubAccount.class, EqTypeProduct.class }, new Object[]
                { l_subAccount, l_eqtypeProduct });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateInsider", new Class[]
                { SubAccount.class, EqTypeProduct.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validateInsider()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateInsider",
                    new Class[]
                    { SubAccount.class, EqTypeProduct.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateInsider",
                    new Class[]
                    { SubAccount.class, EqTypeProduct.class }).asVoid();
            return;
        }
        super.validateInsider(l_subAccount, l_eqtypeProduct);
    }
    
    public void validateAccountProductOrderStop(
            SubAccount l_subAccount,
            long l_lngProductId,
            OrderTypeEnum l_orderType) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateAccountProductOrderStop", new Class[]
                { SubAccount.class, long.class, OrderTypeEnum.class }, new Object[]
                { l_subAccount, new Long(l_lngProductId), l_orderType });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateAccountProductOrderStop", new Class[]
                { SubAccount.class, long.class, OrderTypeEnum.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validateAccountProductOrderStop()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateAccountProductOrderStop",
                    new Class[]
                    { SubAccount.class, long.class, OrderTypeEnum.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateAccountProductOrderStop",
                    new Class[]
                    { SubAccount.class, long.class, OrderTypeEnum.class }).asVoid();
            return;
        }
        super.validateAccountProductOrderStop( l_subAccount, l_lngProductId, l_orderType );
    }
    
    public void validateMarketOrderRestraint(
            WEB3EquityTradedProduct l_tradedProduct, 
            String l_strRepaymentType, 
            OrderCategEnum l_orderCateg, 
            boolean l_blnIsMarketOrder, 
            boolean l_blnIsShort,
            EqTypeExecutionConditionType l_executionCondition) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateMarketOrderRestraint", new Class[]
                { WEB3EquityTradedProduct.class, String.class, OrderCategEnum.class, boolean.class, boolean.class, EqTypeExecutionConditionType.class }, new Object[]
                { l_tradedProduct, l_strRepaymentType, l_orderCateg, new Boolean(l_blnIsMarketOrder), new Boolean(l_blnIsShort), l_executionCondition });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateMarketOrderRestraint", new Class[]
                { WEB3EquityTradedProduct.class, String.class, OrderCategEnum.class, boolean.class, boolean.class, EqTypeExecutionConditionType.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validateAccountProductOrderStop()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateMarketOrderRestraint",
                    new Class[]
                    { WEB3EquityTradedProduct.class, String.class, OrderCategEnum.class, boolean.class, boolean.class, EqTypeExecutionConditionType.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateMarketOrderRestraint",
                    new Class[]
                    { WEB3EquityTradedProduct.class, String.class, OrderCategEnum.class, boolean.class, boolean.class, EqTypeExecutionConditionType.class }).asVoid();
            return;
        }
        super.validateMarketOrderRestraint( l_tradedProduct, l_strRepaymentType, l_orderCateg, l_blnIsMarketOrder, l_blnIsShort, l_executionCondition );
    }
    
    public void validateMarginSpecialAccountOpen(
            WEB3GentradeSubAccount l_subAccount, 
            TaxTypeEnum l_taxType, 
            Date l_datDeliveryDate) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateMarginSpecialAccountOpen", new Class[]
                { WEB3GentradeSubAccount.class, TaxTypeEnum.class, Date.class }, new Object[]
                { l_subAccount, l_taxType, l_datDeliveryDate });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateMarginSpecialAccountOpen", new Class[]
                { WEB3GentradeSubAccount.class, TaxTypeEnum.class, Date.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validateAccountProductOrderStop()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateMarginSpecialAccountOpen",
                    new Class[]
                    { WEB3GentradeSubAccount.class, TaxTypeEnum.class, Date.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateMarginSpecialAccountOpen",
                    new Class[]
                    { WEB3GentradeSubAccount.class, TaxTypeEnum.class, Date.class }).asVoid();
            return;
        }
        super.validateMarginSpecialAccountOpen( l_subAccount, l_taxType, l_datDeliveryDate );
    }
    
    public void validateHandlingMarket(
            WEB3GentradeBranch l_branch, 
            WEB3EquityTradedProduct l_tradedProduct, 
            String l_strMarketCode, 
            String l_strRepaymentType, 
            double l_dblRepaymentNum) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateHandlingMarket", new Class[]
                { WEB3GentradeBranch.class, WEB3EquityTradedProduct.class, String.class, String.class, double.class }, new Object[]
                { l_branch, l_tradedProduct, l_strMarketCode, l_strRepaymentType, new Double(l_dblRepaymentNum) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateHandlingMarket", new Class[]
                { WEB3GentradeBranch.class, WEB3EquityTradedProduct.class, String.class, String.class, double.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validateAccountProductOrderStop()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateHandlingMarket",
                    new Class[]
                    { WEB3GentradeBranch.class, WEB3EquityTradedProduct.class, String.class, String.class, double.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateHandlingMarket",
                    new Class[]
                    { WEB3GentradeBranch.class, WEB3EquityTradedProduct.class, String.class, String.class, double.class }).asVoid();
            return;
        }
        super.validateHandlingMarket( l_branch, l_tradedProduct, l_strMarketCode, l_strRepaymentType, l_dblRepaymentNum );
    }
    
    public void validateQuantity(
            WEB3EquityTradedProduct l_tradedProduct, 
            WEB3GentradeBranch l_branch, 
            double l_dblQuantity, 
            OrderTypeEnum l_orderType, 
            String l_strRepaymentType, 
            double l_dblRepaymentNum) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateQuantity", new Class[]
                { WEB3EquityTradedProduct.class, WEB3GentradeBranch.class, double.class, OrderTypeEnum.class, String.class, double.class }, new Object[]
                { l_tradedProduct, l_branch, new Double(l_dblQuantity), l_orderType, l_strRepaymentType, new Double(l_dblRepaymentNum) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateQuantity", new Class[]
                { WEB3EquityTradedProduct.class, WEB3GentradeBranch.class, double.class, OrderTypeEnum.class, String.class, double.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validateAccountProductOrderStop()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateQuantity",
                    new Class[]
                    { WEB3EquityTradedProduct.class, WEB3GentradeBranch.class, double.class, OrderTypeEnum.class, String.class, double.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateQuantity",
                    new Class[]
                    { WEB3EquityTradedProduct.class, WEB3GentradeBranch.class, double.class, OrderTypeEnum.class, String.class, double.class }).asVoid();
            return;
        }
        super.validateQuantity( l_tradedProduct, l_branch, l_dblQuantity, l_orderType, l_strRepaymentType, l_dblRepaymentNum );
    }
    
    public boolean validatePrice(
            double l_dblLimitPrice, 
            WEB3EquityTradedProduct l_tradedProduct, 
            SubAccount l_subAccount) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePrice", new Class[]
                { double.class, WEB3EquityTradedProduct.class, SubAccount.class }, new Object[]
                { new Double(l_dblLimitPrice), l_tradedProduct, l_subAccount });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validatePrice", new Class[]
                { double.class, WEB3EquityTradedProduct.class, SubAccount.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validateAccountProductOrderStop()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePrice",
                    new Class[]
                    { double.class, WEB3EquityTradedProduct.class, SubAccount.class }).asWEB3BaseException();
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePrice",
                    new Class[]
                    { double.class, WEB3EquityTradedProduct.class, SubAccount.class }).asBoolean();
        }
        return super.validatePrice( l_dblLimitPrice, l_tradedProduct, l_subAccount );
    }
    
    public void validateWLimitPriceOrder(
            WEB3GentradeSubAccount l_subAccount,
            long l_lngOrderUnitId,
            double l_dblLimitPrice,
            String l_strOrderCondition,
            double l_dblOrderCondPrice,
            String l_strWLimitPrice,
            EqTypeExecutionConditionType l_wLimitExecCondType,
            String l_strWlimitEnableStatusDiv,
            WEB3EquityTradedProduct l_equityTradedProduct,
            boolean l_blnIsBuyToOpenOrder,
            String l_strRepaymentType,
            OrderCategEnum l_orderCateg,
            double l_dblQuantity,
            String l_strPriceConditionType,
            OrderTypeEnum l_orderType) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateWLimitPriceOrder", new Class[]
                { WEB3GentradeSubAccount.class,
                        long.class,
                        double.class,
                        String.class,
                        double.class,
                        String.class,
                        EqTypeExecutionConditionType.class,
                        String.class,
                        WEB3EquityTradedProduct.class,
                        boolean.class,
                        String.class,
                        OrderCategEnum.class,
                        double.class,
                        String .class,
                        OrderTypeEnum.class }, new Object[]
                { l_subAccount,
                        new Long(l_lngOrderUnitId),
                        new Double(l_dblLimitPrice),
                        l_strOrderCondition,
                        new Double(l_dblOrderCondPrice),
                        l_strWLimitPrice,
                        l_wLimitExecCondType,
                        l_strWlimitEnableStatusDiv,
                        l_equityTradedProduct,
                        new Boolean(l_blnIsBuyToOpenOrder),
                        l_strRepaymentType,
                        l_orderCateg,
                        new Double(l_dblQuantity),
                        l_strPriceConditionType,
                        l_orderType });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateWLimitPriceOrder", new Class[]
                { WEB3GentradeSubAccount.class,
                        long.class,
                        double.class,
                        String.class,
                        double.class,
                        String.class,
                        EqTypeExecutionConditionType.class,
                        String.class,
                        WEB3EquityTradedProduct.class,
                        boolean.class,
                        String.class,
                        OrderCategEnum.class,
                        double.class,
                        String .class,
                        OrderTypeEnum.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validateAccountProductOrderStop()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateWLimitPriceOrder",
                    new Class[]
                    { WEB3GentradeSubAccount.class,
                            long.class,
                            double.class,
                            String.class,
                            double.class,
                            String.class,
                            EqTypeExecutionConditionType.class,
                            String.class,
                            WEB3EquityTradedProduct.class,
                            boolean.class,
                            String.class,
                            OrderCategEnum.class,
                            double.class,
                            String .class,
                            OrderTypeEnum.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateWLimitPriceOrder",
                    new Class[]
                    { WEB3GentradeSubAccount.class,
                            long.class,
                            double.class,
                            String.class,
                            double.class,
                            String.class,
                            EqTypeExecutionConditionType.class,
                            String.class,
                            WEB3EquityTradedProduct.class,
                            boolean.class,
                            String.class,
                            OrderCategEnum.class,
                            double.class,
                            String .class,
                            OrderTypeEnum.class }).asVoid();
            return;
        }
        super.validateWLimitPriceOrder( l_subAccount,
                l_lngOrderUnitId,
                l_dblLimitPrice,
                l_strOrderCondition,
                l_dblOrderCondPrice,
                l_strWLimitPrice,
                l_wLimitExecCondType,
                l_strWlimitEnableStatusDiv,
                l_equityTradedProduct,
                l_blnIsBuyToOpenOrder,
                l_strRepaymentType,
                l_orderCateg,
                l_dblQuantity,
                l_strPriceConditionType,
                l_orderType );
    }
    
    public boolean isShortSellingRestraint(
            WEB3GentradeSubAccount l_subAccount, 
            WEB3EquityTradedProduct l_tradedProduct, 
            double l_dblQuantity, 
            OrderTypeEnum l_orderType,
            boolean isMarketOrder,
            EqTypeExecutionConditionType l_execCondType,
            String l_strPriceConditionType,
            EqtypeOrderUnitRow l_changeOrderUnit) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "isShortSellingRestraint", new Class[]
                { WEB3GentradeSubAccount.class,
                        WEB3EquityTradedProduct.class,
                        double.class,
                        OrderTypeEnum.class,
                        boolean.class,
                        EqTypeExecutionConditionType.class,
                        String.class,
                        EqtypeOrderUnitRow.class }, new Object[]
                { l_subAccount,
                        l_subAccount,
                        l_tradedProduct,
                        new Double(l_dblQuantity),
                        l_orderType,
                        new Boolean(isMarketOrder),
                        l_execCondType,
                        l_strPriceConditionType,
                        l_changeOrderUnit });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "isShortSellingRestraint", new Class[]
                { WEB3GentradeSubAccount.class,
                        WEB3EquityTradedProduct.class,
                        double.class,
                        OrderTypeEnum.class,
                        boolean.class,
                        EqTypeExecutionConditionType.class,
                        String.class,
                        EqtypeOrderUnitRow.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validateAccountProductOrderStop()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "isShortSellingRestraint",
                    new Class[]
                    { WEB3GentradeSubAccount.class,
                            WEB3EquityTradedProduct.class,
                            double.class,
                            OrderTypeEnum.class,
                            boolean.class,
                            EqTypeExecutionConditionType.class,
                            String.class,
                            EqtypeOrderUnitRow.class }).asWEB3BaseException();
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                        "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                        "isShortSellingRestraint",
                        new Class[]
                                  { WEB3GentradeSubAccount.class,
                                        WEB3EquityTradedProduct.class,
                                        double.class,
                                        OrderTypeEnum.class,
                                        boolean.class,
                                        EqTypeExecutionConditionType.class,
                                        String.class,
                                        EqtypeOrderUnitRow.class }).asBoolean();
        }
        return super.isShortSellingRestraint( l_subAccount,
                l_tradedProduct,
                l_dblQuantity,
                l_orderType,
                isMarketOrder,
                l_execCondType,
                l_strPriceConditionType,
                l_changeOrderUnit );
    }
    
    public void validateEverySettleContractOrderEntryLotSize(
            WEB3EquityTradedProduct l_equityTradedProduct,
            EqTypeSettleContractOrderEntry[] l_eqTypeSettleContractOrderEntrys) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateEverySettleContractOrderEntryLotSize", new Class[]
                { WEB3EquityTradedProduct.class, EqTypeSettleContractOrderEntry[].class }, new Object[]
                { l_equityTradedProduct, l_eqTypeSettleContractOrderEntrys });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateEverySettleContractOrderEntryLotSize", new Class[]
                { WEB3EquityTradedProduct.class, EqTypeSettleContractOrderEntry[].class }))
        {
            log.debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validateAccountProductOrderStop()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateEverySettleContractOrderEntryLotSize",
                    new Class[]
                    { WEB3EquityTradedProduct.class, EqTypeSettleContractOrderEntry[].class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateEverySettleContractOrderEntryLotSize",
                    new Class[]
                    { WEB3EquityTradedProduct.class, EqTypeSettleContractOrderEntry[].class }).asVoid();
            return;
        }
        super.validateEverySettleContractOrderEntryLotSize( l_equityTradedProduct, l_eqTypeSettleContractOrderEntrys );
    }
    
    public WEB3EquityTradedProduct validateTradedProductForMarginTrading(
            SubAccount l_subAccount,
            WEB3EquityProduct l_product,
            WEB3GentradeMarket l_market,
            WEB3GentradeBranch l_branch,
            String l_strRepaymentType,
            OrderCategEnum l_orderCateg,
            boolean l_isShort) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateTradedProductForMarginTrading", new Class[]
                { SubAccount.class, WEB3EquityProduct.class, WEB3GentradeMarket.class, WEB3GentradeBranch.class, String.class, OrderCategEnum.class, boolean.class }, new Object[]
                { l_subAccount, l_product, l_market, l_branch, l_strRepaymentType, l_orderCateg, new Boolean(l_isShort) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateTradedProductForMarginTrading", new Class[]
                { SubAccount.class, WEB3EquityProduct.class, WEB3GentradeMarket.class, WEB3GentradeBranch.class, String.class, OrderCategEnum.class, boolean.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validateAccountProductOrderStop()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateTradedProductForMarginTrading",
                    new Class[]
                    { SubAccount.class, WEB3EquityProduct.class, WEB3GentradeMarket.class, WEB3GentradeBranch.class, String.class, OrderCategEnum.class, boolean.class }).asWEB3BaseException();
            return (WEB3EquityTradedProduct) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateTradedProductForMarginTrading",
                    new Class[]
                    { SubAccount.class, WEB3EquityProduct.class, WEB3GentradeMarket.class, WEB3GentradeBranch.class, String.class, OrderCategEnum.class, boolean.class }).asObject();
        }
        return super.validateTradedProductForMarginTrading( l_subAccount, l_product, l_market, l_branch, l_strRepaymentType, l_orderCateg, l_isShort );
    }
    
     /**
         * （スーパークラスに自身のインスタンスを登録する。）。<BR>
         * <BR>
         * （プラグイン初期化時にコールされる）<BR>
         * <BR>
         * ---<BR>
         * super.setInstance(this);<BR>
         * ---
         */
     public void register()
     {
         log.debug("プラグイン初期化時にコールされるregister");
         super.setInstance(this);
     }
}
@

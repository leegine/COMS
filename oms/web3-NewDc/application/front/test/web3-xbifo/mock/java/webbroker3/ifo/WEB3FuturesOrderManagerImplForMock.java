head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.42.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesOrderManagerImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3FuturesOrderManagerImplForMock.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/02/06 金傑 (中訊) 新規作成
 */
package webbroker3.ifo;

import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoChangeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoOpenContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoSettleContractOrderSpec;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesOrderManagerImplForMock extends WEB3OptionOrderManagerImplForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesOrderManagerImplForMock.class);

    public OrderUnit getOrderUnit(String l_strInstitutionCode, String l_strBranchCode, ProductTypeEnum l_productType,
            String l_strDiscriminationCode) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "getOrderUnit(l_strSecutieCompanyCode,l_strBranchCode,"
                + "l_productType,l_strDiscriminationCode)--ForMock";
        log.entering(STR_METHOD_NAME);

        // 1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getOrderUnit",
                new Class[]
                { String.class, String.class, ProductTypeEnum.class, String.class }, new Object[]
                { l_strInstitutionCode, l_strBranchCode, l_productType, l_strDiscriminationCode });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getOrderUnit",
                new Class[]
                { String.class, String.class, ProductTypeEnum.class, String.class }))
        {
            // 2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "getOrderUnit", new Class[]
                    { String.class, String.class, ProductTypeEnum.class, String.class }).asWEB3BaseException();

            // 3)MockFor --〉 asVoid
            return (OrderUnit) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getOrderUnit", new Class[]
                    { String.class, String.class, ProductTypeEnum.class, String.class }).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getOrderUnit(l_strInstitutionCode, l_strBranchCode, l_productType, l_strDiscriminationCode);
    }

    /**
     * (insert新規建注文キュー(Mock))<BR>
     * <BR>
     * （insertOpenContractHostOrder）<BR>
     * 
     * @@param l_lngOrderId -
     *            注文ID<BR>
     *            注文ID。
     * @@throws WEB3BaseException
     */
    public void insertOpenContractHostOrder(long l_lngOrderId) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "insertOpenContractHostOrder(long) -- ForMock";
        log.entering(STR_METHOD_NAME);

        // 1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "insertOpenContractHostOrder", new Class[]
                { long.class }, new Object[]
                { new Long(l_lngOrderId) });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "insertOpenContractHostOrder", new Class[]
                { long.class }))
        {
            // 2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "insertOpenContractHostOrder", new Class[]
                    { long.class }).asWEB3BaseException();

            // 3)MockFor --〉 asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "insertOpenContractHostOrder", new Class[]
                    { long.class }).asVoid();
            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.insertOpenContractHostOrder(l_lngOrderId);

    }

    /**
     * (is内容通知済注文(Mock))<BR>
     * 発注済注文であるかを判定する。<BR>
     * 
     * @@return boolean
     */
    public boolean isNotifyEndOrder(OrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isNotifyEndOrder(l_orderUnit)--ForMock";
        log.entering(STR_METHOD_NAME);

        // 1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "isNotifyEndOrder", new Class[]
                { OrderUnit.class }, new Object[]
                { l_orderUnit });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl", "isNotifyEndOrder",
                new Class[]
                { OrderUnit.class }))
        {
            // 2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isNotifyEndOrder", new Class[]
                    { OrderUnit.class }).asWEB3BaseException();

            // 3)MockFor --〉 asBoolean
            return (boolean) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "isNotifyEndOrder", new Class[]
                    { OrderUnit.class }).asBoolean();
        }

        log.exiting(STR_METHOD_NAME);
        return super.isNotifyEndOrder(l_orderUnit);
    }

    /**
     * (validate注文(Mock))<BR>
     */
    public void validateOrder(SubAccount l_subAccount, String l_strFuturesOptionDivision) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(l_subAccount,l_strFuturesOptionDivision)--ForMock";
        log.entering(STR_METHOD_NAME);

        // 1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateOrder", new Class[]
                { SubAccount.class, String.class }, new Object[]
                { l_subAccount, l_strFuturesOptionDivision });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateOrder",
                new Class[]
                { SubAccount.class, String.class }))
        {
            // 2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateOrder", new Class[]
                    { SubAccount.class, String.class }).asWEB3BaseException();

            // 3)MockFor --〉 asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateOrder", new Class[]
                    { SubAccount.class, String.class }).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateOrder(l_subAccount, l_strFuturesOptionDivision);
    }

    /**
     * (insert返済注文キュー(Mock))<BR>
     * 
     * @@param l_lngOrderId -
     *            注文ID<BR>
     *            注文ID。
     * @@throws WEB3BaseException
     */
    public void insertSettleContractHostOrder(long l_lngOrderId) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "insertSettleContractHostOrder(long)--ForMock";
        log.entering(STR_METHOD_NAME);

        // 1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "insertSettleContractHostOrder", new Class[]
                { long.class }, new Object[]
                { new Long(l_lngOrderId) });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "insertSettleContractHostOrder", new Class[]
                { long.class }))
        {
            // 2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "insertSettleContractHostOrder", new Class[]
                    { long.class }).asWEB3BaseException();

            // 3)MockFor --〉 asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "insertSettleContractHostOrder", new Class[]
                    { long.class }).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.insertSettleContractHostOrder(l_lngOrderId);
    }

    public OrderUnit getOrderUnit(long orderUnitId) throws NotFoundException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getOrderUnit",
                new Class[]
                { long.class }, new Object[]
                { new Long(orderUnitId) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "getOrderUnit",
                new Class[]{long.class}))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.getOrderUnit(long)");
            return (OrderUnit) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getOrderUnit", new Class[]
                    { long.class }).asObject();
        }
        return super.getOrderUnit(orderUnitId);
    }

    public OrderValidationResult validateChangeSettleContractOrder(WEB3GentradeSubAccount l_subAccount,
            WEB3IfoChangeSettleContractOrderSpec l_settleContractChangeSpec, boolean l_blnIsSkipDelayStatusCheck)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateChangeSettleContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class, boolean.class },
                new Object[]
                { l_subAccount, l_settleContractChangeSpec, new Boolean(l_blnIsSkipDelayStatusCheck) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateChangeSettleContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class, boolean.class }))
        {
            log
                    .debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.validateChangeSettleContractOrder(WEB3GentradeSubAccount,WEB3IfoChangeSettleContractOrderSpec,boolean)");

            return (OrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateChangeSettleContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class, boolean.class })
                    .asObject();
        }

        return super.validateChangeSettleContractOrder(l_subAccount, l_settleContractChangeSpec,
                l_blnIsSkipDelayStatusCheck);
    }

    public OrderValidationResult validateFuturesChangeOrder(WEB3GentradeSubAccount l_subAccount,
            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec, boolean l_blnIsSkipDelayStatusCheck)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateFuturesChangeOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class, boolean.class }, new Object[]
                { l_subAccount, l_ifoOpenContractChangeSpec, new Boolean(l_blnIsSkipDelayStatusCheck) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateFuturesChangeOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class, boolean.class }))
        {
            log
                    .debug("webbroker3.ifo.WEB3FuturesOrderManagerImpl.WEB3FuturesOrderManagerImplForMock.validateFuturesChangeOrder(WEB3GentradeSubAccount,WEB3IfoOpenContractChangeSpec,boolean)");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateFuturesChangeOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class, boolean.class }).asWEB3BaseRuntimeException();

            return (OrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateFuturesChangeOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class, boolean.class }).asObject();
        }

        return super.validateFuturesChangeOrder(l_subAccount, l_ifoOpenContractChangeSpec, l_blnIsSkipDelayStatusCheck);
    }

    public WEB3IfoEstimateDeliveryAmountCalcResult calcChangeEstimateDeliveryAmount(
            WEB3GentradeCommission l_commission, double l_dblLimitPrice, WEB3GentradeSubAccount l_subAccount,
            WEB3IfoTradedProductImpl l_futuresOptionTradedProduct, double l_dblQuantity, SideEnum l_dealing,
            boolean l_blnIsClosingContractOrder, double l_dblExecQuantity, double l_dblSumTransferredAssetBookValue,
            boolean l_blnIsSkipPriceCheck) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcChangeEstimateDeliveryAmount", new Class[]
                { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class, double.class, SideEnum.class, boolean.class, double.class,
                        double.class, boolean.class }, new Object[]
                { l_commission, new Double(l_dblLimitPrice), l_subAccount, l_futuresOptionTradedProduct,
                        new Double(l_dblQuantity), l_dealing, new Boolean(l_blnIsClosingContractOrder),
                        new Double(l_dblExecQuantity), new Double(l_dblSumTransferredAssetBookValue),
                        new Boolean(l_blnIsSkipPriceCheck) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcChangeEstimateDeliveryAmount", new Class[]
                { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class, double.class, SideEnum.class, boolean.class, double.class,
                        double.class, boolean.class }))
        {

            log
                    .debug("webbroker3.ifo.WEB3FuturesOrderManagerImpl.WEB3FuturesOrderManagerImplForMock.calcChangeEstimateDeliveryAmount");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                            WEB3IfoTradedProductImpl.class, double.class, SideEnum.class, boolean.class, double.class,
                            double.class, boolean.class }).asWEB3BaseException();
            return (WEB3IfoEstimateDeliveryAmountCalcResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                            WEB3IfoTradedProductImpl.class, double.class, SideEnum.class, boolean.class, double.class,
                            double.class, boolean.class }).asObject();
        }
        return super.calcChangeEstimateDeliveryAmount(l_commission, l_dblLimitPrice, l_subAccount,
                l_futuresOptionTradedProduct, l_dblQuantity, l_dealing, l_blnIsClosingContractOrder, l_dblExecQuantity,
                l_dblSumTransferredAssetBookValue, l_blnIsSkipPriceCheck);
    }

    public WEB3IfoEstimateDeliveryAmountCalcResult calcChangeEstimatePrice(WEB3GentradeCommission l_commission,
            double l_dblLimitPrice, WEB3GentradeSubAccount l_subAccount,
            WEB3IfoTradedProductImpl l_futuresOptionTradedProduct, double l_dblQuantity, double l_dblExecQuantity,
            double l_dblSumTransferredAssetBookValue, boolean l_blnIsSkipPriceCheck) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcChangeEstimatePrice", new Class[]
                { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class, double.class, double.class, double.class, boolean.class },
                new Object[]
                { l_commission, new Double(l_dblLimitPrice), l_subAccount, l_futuresOptionTradedProduct,
                        new Double(l_dblQuantity), new Double(l_dblExecQuantity),
                        new Double(l_dblSumTransferredAssetBookValue), new Boolean(l_blnIsSkipPriceCheck) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcChangeEstimatePrice", new Class[]
                { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class, double.class, double.class, double.class, boolean.class }))
        {
            log
                    .debug("webbroker3.ifo.WEB3FuturesOrderManagerImpl.WEB3FuturesOrderManagerImplForMock.calcChangeEstimateDeliveryAmount");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcChangeEstimatePrice",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                            WEB3IfoTradedProductImpl.class, double.class, double.class, double.class, boolean.class })
                    .asWEB3BaseException();
            return (WEB3IfoEstimateDeliveryAmountCalcResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcChangeEstimatePrice",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                            WEB3IfoTradedProductImpl.class, double.class, double.class, double.class, boolean.class })
                    .asObject();
        }
        return super.calcChangeEstimatePrice(l_commission, l_dblLimitPrice, l_subAccount, l_futuresOptionTradedProduct,
                l_dblQuantity, l_dblExecQuantity, l_dblSumTransferredAssetBookValue, l_blnIsSkipPriceCheck);
    }

    public OrderValidationResult validateChangeOrder(WEB3GentradeSubAccount l_subAccount,
            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec, boolean l_blnIsSkipDelayStatusCheck)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateChangeOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class, boolean.class }, new Object[]
                { l_subAccount, l_ifoOpenContractChangeSpec, new Boolean(l_blnIsSkipDelayStatusCheck) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateChangeOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class, boolean.class }))
        {
            log
                    .debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.validateChangeOrder(WEB3GentradeSubAccount,WEB3IfoChangeSettleContractOrderSpec,boolean)");

            return (OrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateChangeOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class, boolean.class }).asObject();
        }
        return super.validateChangeOrder(l_subAccount, l_ifoOpenContractChangeSpec, l_blnIsSkipDelayStatusCheck);
    }

    public OrderValidationResult validateFuturesChangeSettleContractOrder(WEB3GentradeSubAccount l_subAccount,
            WEB3IfoChangeSettleContractOrderSpec l_changeSettleContractOrderSpec, boolean l_blnIsSkipDelayStatusCheck)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateFuturesChangeSettleContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class, boolean.class },
                new Object[]
                { l_subAccount, l_changeSettleContractOrderSpec, new Boolean(l_blnIsSkipDelayStatusCheck) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateFuturesChangeSettleContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class, boolean.class }))
        {
            log
                    .debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.validateFuturesChangeSettleContractOrder(WEB3GentradeSubAccount,WEB3IfoChangeSettleContractOrderSpec,boolean)");
            return (OrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateFuturesChangeSettleContractOrder",
                    new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class, boolean.class })
                    .asObject();
        }
        return super.validateFuturesChangeSettleContractOrder(l_subAccount, l_changeSettleContractOrderSpec,
                l_blnIsSkipDelayStatusCheck);
    }

    public WEB3IfoEstimateDeliveryAmountCalcResult calcChangeEstimateSettlementIncome(
            WEB3GentradeCommission l_commission, double l_dblLimitPrice, WEB3GentradeSubAccount l_subAccount,
            WEB3IfoTradedProductImpl l_futuresOptionTradedProduct, SettleContractEntry[] l_settleContractEntry,
            double l_dblQuantity, SideEnum l_dealing, double l_dblExecQuantity, long l_lngOrderUnitId,
            boolean l_blnIsSkipPriceCheck) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcChangeEstimateSettlementIncome", new Class[]
                { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class, SideEnum.class,
                        double.class, long.class, boolean.class }, new Object[]
                { l_commission, new Double(l_dblLimitPrice), l_subAccount, l_futuresOptionTradedProduct,
                        l_settleContractEntry, new Double(l_dblQuantity), l_dealing, new Double(l_dblExecQuantity),
                        new Long(l_lngOrderUnitId), new Boolean(l_blnIsSkipPriceCheck) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcChangeEstimateSettlementIncome", new Class[]
                { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class, SideEnum.class,
                        double.class, long.class, boolean.class }))
        {
            log
                    .debug("webbroker3.ifo.WEB3FuturesOrderManagerImpl.WEB3FuturesOrderManagerImplForMock.calcChangeEstimateDeliveryAmount");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcChangeEstimateSettlementIncome",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                            WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class, SideEnum.class,
                            double.class, long.class, boolean.class }).asWEB3BaseException();
            return (WEB3IfoEstimateDeliveryAmountCalcResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcChangeEstimateSettlementIncome",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                            WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class, SideEnum.class,
                            double.class, long.class, boolean.class }).asObject();
        }
        return super.calcChangeEstimateSettlementIncome(l_commission, l_dblLimitPrice, l_subAccount,
                l_futuresOptionTradedProduct, l_settleContractEntry, l_dblQuantity, l_dealing, l_dblExecQuantity,
                l_lngOrderUnitId, l_blnIsSkipPriceCheck);
    }

    public void updateOrderData(IfoOrderUnit l_orderUnit, boolean l_blnIsCreateOrderAction) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "updateOrderData", new Class[]
                { IfoOrderUnit.class, boolean.class }, new Object[]
                { l_orderUnit, new Boolean(l_blnIsCreateOrderAction) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl", "updateOrderData",
                new Class[]
                { IfoOrderUnit.class, boolean.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.updateOrderData(IfoOrderUnit,boolean)");

            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "updateOrderData", new Class[]
                    { IfoOrderUnit.class, boolean.class }).asWEB3BaseException();

            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "updateOrderData", new Class[]
                    { IfoOrderUnit.class, boolean.class }).asVoid();
            return;
        }
        super.updateOrderData(l_orderUnit, l_blnIsCreateOrderAction);
    }

    public OrderValidationResult validateChangeOrder(WEB3GentradeSubAccount l_subAccount,
            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateChangeOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class }, new Object[]
                { l_subAccount, l_ifoOpenContractChangeSpec });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateChangeOrder",
                new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImpl.validateChangeOrder()");
            return (OrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateChangeOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class }).asObject();
        }
        return super.validateChangeOrder(l_subAccount, l_ifoOpenContractChangeSpec);
    }

    public String[] getHandlingPossibleExecConds(
            String[] l_strHandlingPossibleOrderPriceDivs,
            String[] l_strHandlingPossibleExecConds) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "getHandlingPossibleExecConds", new Class[]
                { String[].class, String[].class }, new Object[]
                { l_strHandlingPossibleOrderPriceDivs, l_strHandlingPossibleExecConds });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "getHandlingPossibleExecConds", new Class[]
                { String[].class, String[].class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.getHandlingPossibleExecConds()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "getHandlingPossibleExecConds", new Class[]
                    { String[].class, String[].class }).asWEB3BaseException();
            return (String[]) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getHandlingPossibleExecConds", new Class[]
                    { String[].class, String[].class }).asObject();
        }
        return super.getHandlingPossibleExecConds(l_strHandlingPossibleOrderPriceDivs, l_strHandlingPossibleExecConds);
    }
    
    public boolean isEveningSessionOrder(IfoOrderUnit l_ifoOrderUnit) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "isEveningSessionOrder", new Class[]
                { IfoOrderUnit.class }, new Object[]
                { l_ifoOrderUnit });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "isEveningSessionOrder", new Class[]
                { IfoOrderUnit.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.isEveningSessionOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isEveningSessionOrder", new Class[]
                    { IfoOrderUnit.class }).asWEB3BaseException();
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isEveningSessionOrder", new Class[]
                    { IfoOrderUnit.class }).asBoolean();
        }
        return super.isEveningSessionOrder(l_ifoOrderUnit);
    }
    
    public SettleContractEntry[] createSettleContractEntry(long l_lngOrderUnitId,
            WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "createSettleContractEntry", new Class[]
                { long.class, WEB3FuturesOptionsCloseMarginContractUnit[].class }, new Object[]
                { new Long(l_lngOrderUnitId), l_futuresOptionsCloseMarginContractUnits });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "createSettleContractEntry", new Class[]
                { long.class, WEB3FuturesOptionsCloseMarginContractUnit[].class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.createSettleContractEntry()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "createSettleContractEntry", new Class[]
                    { long.class, WEB3FuturesOptionsCloseMarginContractUnit[].class }).asWEB3BaseException();
            return (SettleContractEntry[]) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "createSettleContractEntry", new Class[]
                    { long.class, WEB3FuturesOptionsCloseMarginContractUnit[].class }).asObject();
        }
        return super.createSettleContractEntry(l_lngOrderUnitId, l_futuresOptionsCloseMarginContractUnits);
    }
    
    public SettleContractEntry[] createSettleContractEntry(long l_lngOrderUnitId, double l_dblOrderQuantity,
            WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER
                .setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl", "createSettleContractEntry",
                        new Class[]
                        { long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class }, new Object[]
                        { new Long(l_lngOrderUnitId), new Double(l_dblOrderQuantity),
                                l_futuresOptionsCloseMarginContractUnits });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "createSettleContractEntry", new Class[]
                { long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.createSettleContractEntry()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "createSettleContractEntry", new Class[]
                    { long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class })
                    .asWEB3BaseException();
            return (SettleContractEntry[]) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "createSettleContractEntry", new Class[]
                    { long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class }).asObject();
        }
        return super.createSettleContractEntry(l_lngOrderUnitId, l_dblOrderQuantity,
                l_futuresOptionsCloseMarginContractUnits);
    }
    
    public NewOrderValidationResult validateSettleContractOrder(WEB3GentradeSubAccount l_subAccount,
            WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateSettleContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class }, new Object[]
                { l_subAccount, l_settleContractOrderSpec });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateSettleContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.validateSettleContractOrder()");
            return (NewOrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateSettleContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class }).asObject();
        }
        return super.validateSettleContractOrder(l_subAccount, l_settleContractOrderSpec);
    }
    
    public OrderUnit[] getOrderUnits(long orderId)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "getOrderUnits", new Class[]
                { long.class }, new Object[]
                { new Long(orderId) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getOrderUnits",
                new Class[]
                { long.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.getOrderUnits()");
            return (OrderUnit[]) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getOrderUnits", new Class[]
                    { long.class }).asObject();
        }
        return super.getOrderUnits(orderId);
    }
    
    public OrderValidationResult validateChangeSettleContractOrder(WEB3GentradeSubAccount l_subAccount,
            WEB3IfoChangeSettleContractOrderSpec l_settleContractChangeSpec)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateChangeSettleContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class }, new Object[]
                { l_subAccount, l_settleContractChangeSpec });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateChangeSettleContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.validateChangeSettleContractOrder()");
            return (OrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateChangeSettleContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class }).asObject();
        }
        return super.validateChangeSettleContractOrder(l_subAccount, l_settleContractChangeSpec);
    }
    
    public WEB3IfoEstimateDeliveryAmountCalcResult calcEstimateDeliveryAmount(WEB3GentradeCommission l_commission,
            double l_dblLimitPrice, SubAccount l_subAccount, WEB3IfoTradedProductImpl l_tradeProduct,
            double l_dblQuantity, SideEnum l_dealing, boolean l_blnIsClosingContractOrder, boolean l_blnIsSkipPriceCheck)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcEstimateDeliveryAmount", new Class[]
                { WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                        double.class, SideEnum.class, boolean.class, boolean.class }, new Object[]
                { l_commission, new Double(l_dblLimitPrice), l_subAccount, l_tradeProduct, new Double(l_dblQuantity),
                        l_dealing, new Boolean(l_blnIsClosingContractOrder), new Boolean(l_blnIsSkipPriceCheck) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcEstimateDeliveryAmount", new Class[]
                { WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                        double.class, SideEnum.class, boolean.class, boolean.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.calcEstimateDeliveryAmount()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimateDeliveryAmount",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                            double.class, SideEnum.class, boolean.class, boolean.class }).asWEB3BaseException();
            return (WEB3IfoEstimateDeliveryAmountCalcResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimateDeliveryAmount",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                            double.class, SideEnum.class, boolean.class, boolean.class }).asObject();
        }
        return super.calcEstimateDeliveryAmount(l_commission, l_dblLimitPrice, l_subAccount, l_tradeProduct,
                l_dblQuantity, l_dealing, l_blnIsClosingContractOrder, l_blnIsSkipPriceCheck);
    }
    
    public void setThreadLocalPersistenceEventInterceptor(IfoOrderManagerPersistenceEventInterceptor l_interceptor)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "setThreadLocalPersistenceEventInterceptor", new Class[]
                { IfoOrderManagerPersistenceEventInterceptor.class }, new Object[]
                { l_interceptor });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "setThreadLocalPersistenceEventInterceptor", new Class[]
                { IfoOrderManagerPersistenceEventInterceptor.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.setThreadLocalPersistenceEventInterceptor()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "setThreadLocalPersistenceEventInterceptor", new Class[]
                    { IfoOrderManagerPersistenceEventInterceptor.class }).asVoid();
            return;
        }
        super.setThreadLocalPersistenceEventInterceptor(l_interceptor);
    }
    
    public String getDayTradeType(SettleContractEntry[] l_settleContractEntrys) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "getDayTradeType", new Class[]
                { SettleContractEntry[].class }, new Object[]
                { l_settleContractEntrys });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getDayTradeType",
                new Class[]
                { SettleContractEntry[].class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.getDayTradeType()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "getDayTradeType", new Class[]
                    { SettleContractEntry[].class }).asWEB3BaseException();
            return (String) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getDayTradeType", new Class[]
                    { SettleContractEntry[].class }).asObject();
        }
        return super.getDayTradeType(l_settleContractEntrys);
    }
    
    public OrderValidationResult validateCancelOrder(WEB3GentradeSubAccount l_subAccount,
            CancelOrderSpec l_cancelOrderSpec)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateCancelOrder", new Class[]
                { WEB3GentradeSubAccount.class, CancelOrderSpec.class }, new Object[]
                { l_subAccount, l_cancelOrderSpec });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateCancelOrder", new Class[]
                { WEB3GentradeSubAccount.class, CancelOrderSpec.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.validateCancelOrder()");
            return (OrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateCancelOrder", new Class[]
                    { WEB3GentradeSubAccount.class, CancelOrderSpec.class }).asObject();
        }
        return super.validateCancelOrder(l_subAccount, l_cancelOrderSpec);
    }
    
    public Order getOrder(long l_orderId) throws NotFoundException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getOrder",
                new Class[]
                { long.class }, new Object[]
                { new Long(l_orderId) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getOrder",
                new Class[]
                { long.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.getOrder()");

            return (Order) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getOrder", new Class[]
                    { long.class }).asObject();
        }
        return super.getOrder(l_orderId);
    }
    
    public NewOrderValidationResult validateOpenContractOrder(WEB3GentradeSubAccount l_subAccount,
            WEB3IfoOpenContractOrderSpec l_openContractOrderSpec)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateOpenContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class }, new Object[]
                { l_subAccount, l_openContractOrderSpec });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateOpenContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.validateOpenContractOrder()");
            return (NewOrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateOpenContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class }).asObject();
        }
        return super.validateOpenContractOrder(l_subAccount, l_openContractOrderSpec);
    }
    
    public WEB3FuturesOptionsContractUnit[] createContractUnitByOrder(long l_lngOrderId) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "createContractUnitByOrder", new Class[]
                { long.class }, new Object[]
                { new Long(l_lngOrderId) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "createContractUnitByOrder", new Class[]
                { long.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.createContractUnitByOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "createContractUnitByOrder", new Class[]
                    { long.class }).asWEB3BaseException();
            return (WEB3FuturesOptionsContractUnit[]) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "createContractUnitByOrder", new Class[]
                    { long.class }).asObject();
        }
        return super.createContractUnitByOrder(l_lngOrderId);
    }
    
    public boolean isCarriedOrderUnit(long l_ifoOrderUnit) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "isCarriedOrderUnit", new Class[]
                { long.class }, new Object[]
                { new Long(l_ifoOrderUnit) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "isCarriedOrderUnit",
                new Class[]
                { long.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.isCarriedOrderUnit()");
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isCarriedOrderUnit", new Class[]
                    { long.class }).asWEB3BaseException();
            
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isCarriedOrderUnit", new Class[]
                    { long.class }).asBoolean();
        }
        return super.isCarriedOrderUnit(l_ifoOrderUnit);
    }
    
    public WEB3IfoEstimateDeliveryAmountCalcResult calcEstimatePrice(WEB3GentradeCommission l_commission,
            double l_dblLimitPrice, WEB3GentradeSubAccount l_subAccount,
            WEB3IfoTradedProductImpl l_futuresOptionTradedProduct, double l_dblQuantity, boolean l_blnIsSkipPriceCheck)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcEstimatePrice", new Class[]
                { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class, double.class, boolean.class }, new Object[]
                { l_commission, new Double(l_dblLimitPrice), l_subAccount, l_futuresOptionTradedProduct,
                        new Double(l_dblQuantity), new Boolean(l_blnIsSkipPriceCheck) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl", "calcEstimatePrice",
                new Class[]
                { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class, double.class, boolean.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.calcEstimatePrice()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimatePrice",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                            WEB3IfoTradedProductImpl.class, double.class, boolean.class }).asWEB3BaseException();
            return (WEB3IfoEstimateDeliveryAmountCalcResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimatePrice",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                            WEB3IfoTradedProductImpl.class, double.class, boolean.class }).asObject();
        }
        return super.calcEstimatePrice(l_commission, l_dblLimitPrice, l_subAccount, l_futuresOptionTradedProduct,
                l_dblQuantity, l_blnIsSkipPriceCheck);
    }
    
    public WEB3IfoEstimateDeliveryAmountCalcResult calcEstimateSettlementIncome(WEB3GentradeCommission l_commission,
            double l_dblLimitPrice, WEB3GentradeSubAccount l_subAccount,
            WEB3IfoTradedProductImpl l_futuresOptionTradedProduct, SettleContractEntry[] l_settleContractEntry,
            double l_dblQuantity, SideEnum l_dealing, boolean l_blnIsSkipPriceCheck) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER
                .setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl", "calcEstimateSettlementIncome",
                        new Class[]
                        { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                                WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class,
                                SideEnum.class, boolean.class }, new Object[]
                        { l_commission, new Double(l_dblLimitPrice), l_subAccount, l_futuresOptionTradedProduct,
                                l_settleContractEntry, new Double(l_dblQuantity), l_dealing,
                                new Boolean(l_blnIsSkipPriceCheck) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcEstimateSettlementIncome", new Class[]
                { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class, SideEnum.class,
                        boolean.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.calcEstimateSettlementIncome()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimateSettlementIncome",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                            WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class, SideEnum.class,
                            boolean.class }).asWEB3BaseException();
            return (WEB3IfoEstimateDeliveryAmountCalcResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimateSettlementIncome",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                            WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class, SideEnum.class,
                            boolean.class }).asObject();
        }
        return super.calcEstimateSettlementIncome(l_commission, l_dblLimitPrice, l_subAccount,
                l_futuresOptionTradedProduct, l_settleContractEntry, l_dblQuantity, l_dealing, l_blnIsSkipPriceCheck);
    }
    
    public WEB3IfoEstimateDeliveryAmountCalcResult calcEstimateSettlementIncome(WEB3GentradeCommission l_commission,
            double l_dblLimitPrice, WEB3GentradeSubAccount l_subAccount,
            WEB3IfoTradedProductImpl l_futuresOptionTradedProduct, SettleContractEntry[] l_settleContractEntry,
            double l_dblQuantity, SideEnum l_dealing, boolean l_blnIsSkipPriceCheck, WEB3IfoContractImpl l_ifoContractImpl) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER
                .setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl", "calcEstimateSettlementIncome",
                        new Class[]
                        { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                                WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class,
                                SideEnum.class, boolean.class, WEB3IfoContractImpl.class}, new Object[]
                        { l_commission, new Double(l_dblLimitPrice), l_subAccount, l_futuresOptionTradedProduct,
                                l_settleContractEntry, new Double(l_dblQuantity), l_dealing,
                                new Boolean(l_blnIsSkipPriceCheck), l_ifoContractImpl});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcEstimateSettlementIncome", new Class[]
                { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class, SideEnum.class,
                        boolean.class, WEB3IfoContractImpl.class}))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.calcEstimateSettlementIncome()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimateSettlementIncome",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                            WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class, SideEnum.class,
                            boolean.class, WEB3IfoContractImpl.class}).asWEB3BaseException();
            return (WEB3IfoEstimateDeliveryAmountCalcResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimateSettlementIncome",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                            WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class, SideEnum.class,
                            boolean.class, WEB3IfoContractImpl.class}).asObject();
        }
        return super.calcEstimateSettlementIncome(l_commission, l_dblLimitPrice, l_subAccount,
                l_futuresOptionTradedProduct, l_settleContractEntry, l_dblQuantity, l_dealing, l_blnIsSkipPriceCheck, l_ifoContractImpl);
    }
    
    public NewOrderValidationResult validateFuturesSettleContractOrder(WEB3GentradeSubAccount l_subAccount,
            WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateFuturesSettleContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class }, new Object[]
                { l_subAccount, l_settleContractOrderSpec });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateFuturesSettleContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.validateFuturesSettleContractOrder()");
            return (NewOrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateFuturesSettleContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class }).asObject();
        }
        return super.validateFuturesSettleContractOrder(l_subAccount, l_settleContractOrderSpec);
    }
    
    public NewOrderValidationResult validateFuturesSettleContractOrder(WEB3GentradeSubAccount l_subAccount,
            WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec, WEB3IfoContractImpl l_ifoContractImpl)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateFuturesSettleContractOrder", new Class[]
                {WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class, WEB3IfoContractImpl.class},
                new Object[]
                {l_subAccount, l_settleContractOrderSpec, l_ifoContractImpl});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateFuturesSettleContractOrder", new Class[]
                {WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class, WEB3IfoContractImpl.class}))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.validateFuturesSettleContractOrder()");
            return (NewOrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateFuturesSettleContractOrder", new Class[]
                    {WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class, WEB3IfoContractImpl.class})
                    .asObject();
        }
        return super.validateFuturesSettleContractOrder(l_subAccount, l_settleContractOrderSpec, l_ifoContractImpl);
    }
    
    public OrderSubmissionResult submitOpenContractOrder(SubAccount subAccount, IfoOpenContractOrderSpec spec,
            long orderId, String tradingPassword, boolean skipOrderValidation)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "submitOpenContractOrder", new Class[]
                { SubAccount.class, IfoOpenContractOrderSpec.class, long.class, String.class, boolean.class },
                new Object[]
                { subAccount, spec, new Long(orderId), tradingPassword, new Boolean(skipOrderValidation) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "submitOpenContractOrder", new Class[]
                { SubAccount.class, IfoOpenContractOrderSpec.class, long.class, String.class, boolean.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.submitOpenContractOrder()");
            return (OrderSubmissionResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "submitOpenContractOrder", new Class[]
                    { SubAccount.class, IfoOpenContractOrderSpec.class, long.class, String.class, boolean.class })
                    .asObject();
        }
        return super.submitOpenContractOrder(subAccount, spec, orderId, tradingPassword, skipOrderValidation);
    }
    
    public OrderSubmissionResult submitChangeSettleContractOrder(SubAccount l_subAccount,
            IfoChangeSettleContractOrderSpec l_spec, String l_StrTradingPassword, boolean l_blnIsSkipOrderValidation)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "submitChangeSettleContractOrder", new Class[]
                { SubAccount.class, IfoChangeSettleContractOrderSpec.class, String.class, boolean.class }, new Object[]
                { l_subAccount, l_spec, l_StrTradingPassword, new Boolean(l_blnIsSkipOrderValidation) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "submitChangeSettleContractOrder", new Class[]
                { SubAccount.class, IfoChangeSettleContractOrderSpec.class, String.class, boolean.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.submitChangeSettleContractOrder()");
            return (OrderSubmissionResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "submitChangeSettleContractOrder", new Class[]
                    { SubAccount.class, IfoChangeSettleContractOrderSpec.class, String.class, boolean.class })
                    .asObject();
        }
        return super.submitChangeSettleContractOrder(l_subAccount, l_spec, l_StrTradingPassword,
                l_blnIsSkipOrderValidation);
    }

    public OrderValidationResult validateFuturesCancelOrder(WEB3GentradeSubAccount l_subAccount,
            CancelOrderSpec l_cancelOrderSpec)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateFuturesCancelOrder", new Class[]
                { WEB3GentradeSubAccount.class, CancelOrderSpec.class }, new Object[]
                { l_subAccount, l_cancelOrderSpec });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateFuturesCancelOrder", new Class[]
                { WEB3GentradeSubAccount.class, CancelOrderSpec.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.validateFuturesCancelOrder()");
            return (OrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateFuturesCancelOrder", new Class[]
                    { WEB3GentradeSubAccount.class, CancelOrderSpec.class }).asObject();
        }
        return super.validateFuturesCancelOrder(l_subAccount, l_cancelOrderSpec);
    }
    
    public NewOrderValidationResult validateFuturesOpenContractOrder(WEB3GentradeSubAccount l_subAccount,
            WEB3IfoOpenContractOrderSpec l_openContractOrderSpec)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateFuturesOpenContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class }, new Object[]
                { l_subAccount, l_openContractOrderSpec });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateFuturesOpenContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.validateFuturesOpenContractOrder()");
            return (NewOrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateFuturesOpenContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class }).asObject();
        }
        return super.validateFuturesOpenContractOrder(l_subAccount, l_openContractOrderSpec);
    }
    
    public OrderValidationResult validateFuturesChangeOrder(WEB3GentradeSubAccount l_subAccount,
            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateFuturesChangeOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class }, new Object[]
                { l_subAccount, l_ifoOpenContractChangeSpec });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateFuturesChangeOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.validateFuturesChangeOrder()");
            return (OrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateFuturesChangeOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class }).asObject();
        }
        return super.validateFuturesChangeOrder(l_subAccount, l_ifoOpenContractChangeSpec);
    }
    
    public boolean isCarriedOrderUnit(IfoOrderUnit l_ifoOrderUnit) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "isCarriedOrderUnit", new Class[]
                { IfoOrderUnit.class }, new Object[]
                { l_ifoOrderUnit});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl", "isCarriedOrderUnit",
                new Class[]
                { IfoOrderUnit.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.isCarriedOrderUnit(IfoOrderUnit)");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isCarriedOrderUnit", new Class[]
                    { IfoOrderUnit.class }).asWEB3BaseException();

            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isCarriedOrderUnit", new Class[]
                    { IfoOrderUnit.class }).asBoolean();
        }
        return super.isCarriedOrderUnit(l_ifoOrderUnit);
    }
    
    public NewOrderValidationResult validateOpenContractOrder(WEB3GentradeSubAccount l_subAccount,
            WEB3IfoOpenContractOrderSpec l_openContractOrderSpec, IfoOrderUnit l_ifoOrderUnit)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateOpenContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class, IfoOrderUnit.class }, new Object[]
                { l_subAccount, l_openContractOrderSpec, l_ifoOrderUnit });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateOpenContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class, IfoOrderUnit.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.validateOpenContractOrder()");
            return (NewOrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateOpenContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class, IfoOrderUnit.class })
                    .asObject();
        }
        return super.validateOpenContractOrder(l_subAccount, l_openContractOrderSpec, l_ifoOrderUnit);
    }
    
    public void throwTpErrorInfo(WEB3TPTradingPowerResult l_tpResult, WEB3GentradeSubAccount l_subAccount)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "throwTpErrorInfo", new Class[]
                { WEB3TPTradingPowerResult.class, WEB3GentradeSubAccount.class }, new Object[]
                { l_tpResult, l_subAccount });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl", "throwTpErrorInfo",
                new Class[]
                { WEB3TPTradingPowerResult.class, WEB3GentradeSubAccount.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.throwTpErrorInfo()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "throwTpErrorInfo", new Class[]
                    { WEB3TPTradingPowerResult.class, WEB3GentradeSubAccount.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "throwTpErrorInfo", new Class[]
                    { WEB3TPTradingPowerResult.class, WEB3GentradeSubAccount.class }).asVoid();
            return;
        }
        super.throwTpErrorInfo(l_tpResult, l_subAccount);
    }
    
    public NewOrderValidationResult validateFuturesOpenContractOrder(WEB3GentradeSubAccount l_subAccount,
            WEB3IfoOpenContractOrderSpec l_openContractOrderSpec, IfoOrderUnit l_ifoOrderUnit)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateFuturesOpenContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class, IfoOrderUnit.class }, new Object[]
                { l_subAccount, l_openContractOrderSpec, l_ifoOrderUnit });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateFuturesOpenContractOrder", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class, IfoOrderUnit.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.validateFuturesOpenContractOrder()");
            return (NewOrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateFuturesOpenContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class, IfoOrderUnit.class })
                    .asObject();
        }
        return super.validateFuturesOpenContractOrder(l_subAccount, l_openContractOrderSpec, l_ifoOrderUnit);
    }
    
    public OrderSubmissionResult submitChangeOrder(SubAccount l_subAccount, ChangeOrderSpec l_spec,
            String l_strTradingPassword, boolean l_blnIsSkipOrderValidation)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "submitChangeOrder", new Class[]
                { SubAccount.class, ChangeOrderSpec.class, String.class, boolean.class }, new Object[]
                { l_subAccount, l_spec, l_strTradingPassword, new Boolean(l_blnIsSkipOrderValidation) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl", "submitChangeOrder",
                new Class[]
                { SubAccount.class, ChangeOrderSpec.class, String.class, boolean.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.submitChangeOrder()");
            return (OrderSubmissionResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "submitChangeOrder", new Class[]
                    { SubAccount.class, ChangeOrderSpec.class, String.class, boolean.class }).asObject();
        }
        return super.submitChangeOrder(l_subAccount, l_spec, l_strTradingPassword, l_blnIsSkipOrderValidation);
    }
    
    public OrderSubmissionResult submitCancelOrder(SubAccount subAccount, CancelOrderSpec spec, 
        String tradingPassword, boolean skipOrderValidation)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "submitCancelOrder", 
                new Class[]
                { SubAccount.class, CancelOrderSpec.class, String.class, boolean.class }, 
                new Object[]
                { subAccount, spec, tradingPassword, new Boolean(skipOrderValidation) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "submitCancelOrder",
                new Class[]
                {SubAccount.class, CancelOrderSpec.class, String.class, boolean.class}))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.submitChangeOrder()");
            return (OrderSubmissionResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "submitCancelOrder", 
                    new Class[]
                    {SubAccount.class, CancelOrderSpec.class, String.class, boolean.class}).asObject();
        }
        return super.submitCancelOrder(subAccount, spec,tradingPassword,skipOrderValidation);
    }
    
    public OrderSubmissionResult submitSettleContractOrder(SubAccount l_subAccount, IfoSettleContractOrderSpec l_spec,
            long l_lngOrderId, String l_strTradingPassword, boolean l_blnIsSkipOrderValidation)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "submitSettleContractOrder", new Class[]
                { SubAccount.class, IfoSettleContractOrderSpec.class, long.class, String.class, boolean.class },
                new Object[]
                { l_subAccount, l_spec, new Long(l_lngOrderId), l_strTradingPassword,
                        new Boolean(l_blnIsSkipOrderValidation) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "submitSettleContractOrder", new Class[]
                { SubAccount.class, IfoSettleContractOrderSpec.class, long.class, String.class, boolean.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.submitSettleContractOrder()");
            return (OrderSubmissionResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "submitSettleContractOrder", new Class[]
                    { SubAccount.class, IfoSettleContractOrderSpec.class, long.class, String.class, boolean.class })
                    .asObject();
        }
        return super.submitSettleContractOrder(l_subAccount, l_spec, l_lngOrderId, l_strTradingPassword,
                l_blnIsSkipOrderValidation);
    }
    
    public void notifyRLS(IfoOrderUnit l_orderUnit, OrderManagerPersistenceContext l_context)
        throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "notifyRLS", new Class[]
                { IfoOrderUnit.class, OrderManagerPersistenceContext.class }, new Object[]
                { l_orderUnit, l_context });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl", "notifyRLS",
                new Class[]
                { IfoOrderUnit.class, OrderManagerPersistenceContext.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.notifyRLS()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "notifyRLS", new Class[]
                    { IfoOrderUnit.class, OrderManagerPersistenceContext.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "notifyRLS", new Class[]
                    { IfoOrderUnit.class, OrderManagerPersistenceContext.class }).asVoid();
            return;
        }
        super.notifyRLS(l_orderUnit, l_context);
        
    }

    public boolean isCarryoverOrder(IfoOrderUnit l_ifoOrderUnit) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "isCarryoverOrder", new Class[]
            {IfoOrderUnit.class}, new Object[]{l_ifoOrderUnit});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "isCarryoverOrder", new Class[] {IfoOrderUnit.class}))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImpl.isCarryoverOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "isCarryoverOrder", new Class[] {IfoOrderUnit.class}).asWEB3BaseException();
            return (boolean) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "isCarryoverOrder", new Class[]
                {IfoOrderUnit.class}).asBoolean();
        }
        return super.isCarryoverOrder(l_ifoOrderUnit);
    }

    public ProcessingResult expireOrder(final long orderId)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "expireOrder",
                new Class[] {long.class},
                new Object[]{new Long(orderId)});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "expireOrder", new Class[] {long.class}))
        {
            log.debug("webbroker3.ifo.WEB3OptionOrderManagerImpl.WEB3FuturesOrderManagerImpl.expireOrder(long)");
            return (ProcessingResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "expireOrder", new Class[] {long.class}).asObject();
        }
        
        return super.expireOrder(orderId);
    }
    
    public long createNewOrderId()
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "createNewOrderId",
                new Class[] {},
                null);
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "createNewOrderId", new Class[] {}))
        {
            log.debug("webbroker3.ifo.WEB3OptionOrderManagerImpl.WEB3FuturesOrderManagerImpl.expireOrder(long)");
            return (long)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "createNewOrderId", new Class[] {}).asLong();
        }
        return super.createNewOrderId();
    }
}
@

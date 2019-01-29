head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.41.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccOrderManagerImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : （WEB3ToSuccOrderManagerImplForMock.java）
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/26 トウ鋒鋼 (中訊) 新規作成
 */
package webbroker3.triggerorder;

import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccOrderManagerImplForMock extends WEB3ToSuccOrderManagerImpl
{
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3ToSuccOrderManagerImplForMock.class);

    public boolean isCarryoverReserveIfoOrderUnit(OrderUnit l_orderUnit)throws WEB3BaseException 
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
            "isCarryoverReserveIfoOrderUnit",
            new Class[]{OrderUnit.class},
            new Object[]{l_orderUnit});
        if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
            "isCarryoverReserveIfoOrderUnit",
            new Class[]{OrderUnit.class}))
        {
            log.debug("webbroker3.triggerorder.WEB3ToSuccOrderManagerImplForMock.isCarryoverReserveIfoOrderUnit()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "isCarryoverReserveIfoOrderUnit",
                    new Class[]{OrderUnit.class}).asWEB3BaseException();
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "isCarryoverReserveIfoOrderUnit",
            new Class[]{OrderUnit.class}).asBoolean();
        }
            return super.isCarryoverReserveIfoOrderUnit(l_orderUnit);
    }    
    
    public boolean isReversingTrade(String l_strRsvOrderTradingDiv, OrderUnit l_parentOrderUnit)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "isReversingTrade", new Class[]
                {String.class, OrderUnit.class}, new Object[]
                {l_strRsvOrderTradingDiv, l_parentOrderUnit});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "isReversingTrade", new Class[]
                {String.class, OrderUnit.class}))
        {
            log.debug("webbroker3.triggerorder.WEB3ToSuccOrderManagerImplForMock.isReversingTrade()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class}).asWEB3BaseException();
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class}).asBoolean();
        }
        return super.isReversingTrade(l_strRsvOrderTradingDiv, l_parentOrderUnit);
    }

    public void validateSuccOrder(WEB3GentradeSubAccount l_subAccount, ProductTypeEnum l_productType,
            String l_strFutureOptionDiv, String l_strRsvOrderTradingType, OrderUnit l_parentOrderUnit)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "validateSuccOrder", new Class[]
                {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                new Object[]
                {l_subAccount, l_productType, l_strFutureOptionDiv, l_strRsvOrderTradingType, l_parentOrderUnit});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "validateSuccOrder", new Class[]
                {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class}))
        {
            log.debug("webbroker3.triggerorder.WEB3ToSuccOrderManagerImplForMock.validateSuccOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder", new Class[]
                    {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class})
                    .asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder", new Class[]
                    {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class})
                    .asVoid();
            return;
        }
        super.validateSuccOrder(l_subAccount, l_productType, l_strFutureOptionDiv, l_strRsvOrderTradingType,
                l_parentOrderUnit);
    }

    public void validateIfoCancelOrder(WEB3GentradeSubAccount l_subAccount,
            WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "validateIfoCancelOrder", new Class[]
                {WEB3GentradeSubAccount.class, WEB3ToSuccIfoOrderUnitImpl.class}, new Object[]
                {l_subAccount, l_toSuccIfoOrderUnitImpl});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "validateIfoCancelOrder", new Class[]
                {WEB3GentradeSubAccount.class, WEB3ToSuccIfoOrderUnitImpl.class}))
        {
            log.debug("webbroker3.triggerorder.WEB3ToSuccOrderManagerImplForMock.validateIfoCancelOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateIfoCancelOrder", new Class[]
                    {WEB3GentradeSubAccount.class, WEB3ToSuccIfoOrderUnitImpl.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateIfoCancelOrder", new Class[]
                    {WEB3GentradeSubAccount.class, WEB3ToSuccIfoOrderUnitImpl.class}).asVoid();
            return;
        }
        super.validateIfoCancelOrder(l_subAccount, l_toSuccIfoOrderUnitImpl);
    }

    public void submitIfoCancelOrder(SubAccount l_subAccount, WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl,
            String l_strTradingPassword) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "submitIfoCancelOrder", new Class[]
                {SubAccount.class, WEB3ToSuccIfoOrderUnitImpl.class, String.class}, new Object[]
                {l_subAccount, l_toSuccIfoOrderUnitImpl, l_strTradingPassword});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "submitIfoCancelOrder", new Class[]
                {SubAccount.class, WEB3ToSuccIfoOrderUnitImpl.class, String.class}))
        {
            log.debug("webbroker3.triggerorder.WEB3ToSuccOrderManagerImplForMock.submitIfoCancelOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoCancelOrder", new Class[]
                    {SubAccount.class, WEB3ToSuccIfoOrderUnitImpl.class, String.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoCancelOrder", new Class[]
                    {SubAccount.class, WEB3ToSuccIfoOrderUnitImpl.class, String.class}).asVoid();
            return;
        }
        super.submitIfoCancelOrder(l_subAccount, l_toSuccIfoOrderUnitImpl, l_strTradingPassword);
    }

    public void validateSuccOrderMaxQuantity(OrderUnit l_parentOrderUnit) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "validateSuccOrderMaxQuantity", new Class[]
                {OrderUnit.class}, new Object[]
                {l_parentOrderUnit});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "validateSuccOrderMaxQuantity", new Class[]
                {OrderUnit.class}))
        {
            log.debug("webbroker3.triggerorder.WEB3ToSuccOrderManagerImplForMock.validateSuccOrderMaxQuantity()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity", new Class[]
                    {OrderUnit.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity", new Class[]
                    {OrderUnit.class}).asVoid();
            return;
        }
        super.validateSuccOrderMaxQuantity(l_parentOrderUnit);
    }

    public void submitIfoOpenContractNewOrder(SubAccount l_subAccount,
            WEB3IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec, long l_lngOrderId, String l_strTradingPassword,
            String l_strRsvOrderTradingType, Double l_priceAdjustValue, IfoOrderUnit l_parentOrderUnit,
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "submitIfoOpenContractNewOrder", new Class[]
                {SubAccount.class, WEB3IfoOpenContractOrderSpec.class, long.class, String.class, String.class,
                        Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class}, new Object[]
                {l_subAccount, l_ifoOpenContractOrderSpec, new Long(l_lngOrderId), l_strTradingPassword,
                        l_strRsvOrderTradingType, l_priceAdjustValue, l_parentOrderUnit,
                        l_ifoEstimateDeliveryAmountCalcResult});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "submitIfoOpenContractNewOrder", new Class[]
                {SubAccount.class, WEB3IfoOpenContractOrderSpec.class, long.class, String.class, String.class,
                        Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class}))
        {
            log.debug("webbroker3.triggerorder.WEB3ToSuccOrderManagerImplForMock.submitIfoOpenContractNewOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoOpenContractNewOrder",
                    new Class[]
                    {SubAccount.class, WEB3IfoOpenContractOrderSpec.class, long.class, String.class, String.class,
                            Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class})
                    .asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoOpenContractNewOrder",
                    new Class[]
                    {SubAccount.class, WEB3IfoOpenContractOrderSpec.class, long.class, String.class, String.class,
                            Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class}).asVoid();
            return;
        }
        super.submitIfoOpenContractNewOrder(l_subAccount, l_ifoOpenContractOrderSpec, l_lngOrderId,
                l_strTradingPassword, l_strRsvOrderTradingType, l_priceAdjustValue, l_parentOrderUnit,
                l_ifoEstimateDeliveryAmountCalcResult);
    }

    public double getReserveIfoOrderExecPrice(IfoOrderUnit l_parentOrderUnit, double l_dblLimitPrice,
            Double l_priceAdjustValue, WEB3IfoTradedProductImpl l_ifoTradedProductImpl) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "getReserveIfoOrderExecPrice", new Class[]
                {IfoOrderUnit.class, double.class, Double.class, WEB3IfoTradedProductImpl.class}, new Object[]
                {l_parentOrderUnit, new Double(l_dblLimitPrice), l_priceAdjustValue, l_ifoTradedProductImpl});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "getReserveIfoOrderExecPrice", new Class[]
                {IfoOrderUnit.class, double.class, Double.class, WEB3IfoTradedProductImpl.class}))
        {
            log.debug("webbroker3.triggerorder.WEB3ToSuccOrderManagerImplForMock.getReserveIfoOrderExecPrice()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "getReserveIfoOrderExecPrice", new Class[]
                    {IfoOrderUnit.class, double.class, Double.class, WEB3IfoTradedProductImpl.class})
                    .asWEB3BaseException();
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "getReserveIfoOrderExecPrice", new Class[]
                    {IfoOrderUnit.class, double.class, Double.class, WEB3IfoTradedProductImpl.class}).asDouble();
        }
        return super.getReserveIfoOrderExecPrice(l_parentOrderUnit, l_dblLimitPrice, l_priceAdjustValue,
                l_ifoTradedProductImpl);
    }

    public WEB3IfoContractImpl createIfoContract(IfoOrderUnit l_parentOrderUnit) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "createIfoContract", new Class[]
                {IfoOrderUnit.class}, new Object[]
                {l_parentOrderUnit});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "createIfoContract", new Class[]
                {IfoOrderUnit.class}))
        {
            log.debug("webbroker3.triggerorder.WEB3ToSuccOrderManagerImplForMock.createIfoContract()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "createIfoContract", new Class[]
                    {IfoOrderUnit.class}).asWEB3BaseException();
            return (WEB3IfoContractImpl) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "createIfoContract", new Class[]
                    {IfoOrderUnit.class}).asObject();
        }
        return super.createIfoContract(l_parentOrderUnit);
    }

    public IfoOrderUnit createIfoOrderUnit(WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "createIfoOrderUnit", new Class[]
                {WEB3ToSuccIfoOrderUnitImpl.class}, new Object[]
                {l_rsvIfoOrderUnit});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "createIfoOrderUnit", new Class[]
                {WEB3ToSuccIfoOrderUnitImpl.class}))
        {
            log.debug("webbroker3.triggerorder.WEB3ToSuccOrderManagerImplForMock.createIfoOrderUnit()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "createIfoOrderUnit", new Class[]
                    {WEB3ToSuccIfoOrderUnitImpl.class}).asWEB3BaseException();
            return (IfoOrderUnit) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "createIfoOrderUnit", new Class[]
                    {WEB3ToSuccIfoOrderUnitImpl.class}).asObject();
        }
        return super.createIfoOrderUnit(l_rsvIfoOrderUnit);
    }

    public void submitIfoCloseContractNewOrder(SubAccount l_subAccount,
            WEB3IfoSettleContractOrderSpec l_ifoSettleContractOrderSpec, long l_lngOrderId,
            String l_strTradingPassword, String l_strRsvOrderTradingType, Double l_priceAdjustValue,
            IfoOrderUnit l_parentOrderUnit,
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult,
            WEB3IfoContractImpl l_ifoContractImpl, String l_strClosingOrder) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "submitIfoCloseContractNewOrder", new Class[]
                {SubAccount.class, WEB3IfoSettleContractOrderSpec.class, long.class, String.class, String.class,
                        Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class,
                        WEB3IfoContractImpl.class, String.class}, new Object[]
                {l_subAccount, l_ifoSettleContractOrderSpec, new Long(l_lngOrderId), l_strTradingPassword,
                        l_strRsvOrderTradingType, l_priceAdjustValue, l_parentOrderUnit,
                        l_ifoEstimateDeliveryAmountCalcResult, l_ifoContractImpl, l_strClosingOrder});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "submitIfoCloseContractNewOrder", new Class[]
                {SubAccount.class, WEB3IfoSettleContractOrderSpec.class, long.class, String.class, String.class,
                        Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class,
                        WEB3IfoContractImpl.class, String.class}))
        {
            log.debug("webbroker3.triggerorder.WEB3ToSuccOrderManagerImplForMock.submitIfoCloseContractNewOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoCloseContractNewOrder",
                    new Class[]
                    {SubAccount.class, WEB3IfoSettleContractOrderSpec.class, long.class, String.class, String.class,
                            Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class,
                            WEB3IfoContractImpl.class, String.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoCloseContractNewOrder",
                    new Class[]
                    {SubAccount.class, WEB3IfoSettleContractOrderSpec.class, long.class, String.class, String.class,
                            Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class,
                            WEB3IfoContractImpl.class, String.class}).asVoid();
            return;
        }
        super.submitIfoCloseContractNewOrder(l_subAccount, l_ifoSettleContractOrderSpec, l_lngOrderId,
                l_strTradingPassword, l_strRsvOrderTradingType, l_priceAdjustValue, l_parentOrderUnit,
                l_ifoEstimateDeliveryAmountCalcResult, l_ifoContractImpl, l_strClosingOrder);
    }

    public void submitIfoChangeOpenContractOrder(SubAccount l_subAccount,
            WEB3ToSuccIfoChangeOpenContractOrderSpec l_ifoChangeOrderSpec, String l_strTradingPassword,
            WEB3ToSuccIfoOrderUnitImpl l_changingBeforeRsvIfoOrderUnit) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "submitIfoChangeOpenContractOrder", new Class[]
                {SubAccount.class, WEB3ToSuccIfoChangeOpenContractOrderSpec.class, String.class,
                        WEB3ToSuccIfoOrderUnitImpl.class}, new Object[]
                {l_subAccount, l_ifoChangeOrderSpec, l_strTradingPassword, l_changingBeforeRsvIfoOrderUnit});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "submitIfoChangeOpenContractOrder", new Class[]
                {SubAccount.class, WEB3ToSuccIfoChangeOpenContractOrderSpec.class, String.class,
                        WEB3ToSuccIfoOrderUnitImpl.class}))
        {
            log.debug("webbroker3.triggerorder.WEB3ToSuccOrderManagerImplForMock.submitIfoChangeOpenContractOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoChangeOpenContractOrder",
                    new Class[]
                    {SubAccount.class, WEB3ToSuccIfoChangeOpenContractOrderSpec.class, String.class,
                            WEB3ToSuccIfoOrderUnitImpl.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoChangeOpenContractOrder",
                    new Class[]
                    {SubAccount.class, WEB3ToSuccIfoChangeOpenContractOrderSpec.class, String.class,
                            WEB3ToSuccIfoOrderUnitImpl.class}).asVoid();
            return;
        }
        super.submitIfoChangeOpenContractOrder(l_subAccount, l_ifoChangeOrderSpec, l_strTradingPassword,
                l_changingBeforeRsvIfoOrderUnit);
    }

    public void submitIfoChangeSettleContractOrder(SubAccount l_subAccount,
            WEB3ToSuccIfoChangeSettleContractOrderSpec l_toSuccIfoChangeSettleContractOrderSpec,
            String l_strTradingPassword, WEB3ToSuccIfoOrderUnitImpl l_changingBeforeRsvIfoOrderUnit)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "submitIfoChangeSettleContractOrder", new Class[]
                {SubAccount.class, WEB3ToSuccIfoChangeSettleContractOrderSpec.class, String.class,
                        WEB3ToSuccIfoOrderUnitImpl.class}, new Object[]
                {l_subAccount, l_toSuccIfoChangeSettleContractOrderSpec, l_strTradingPassword,
                        l_changingBeforeRsvIfoOrderUnit});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "submitIfoChangeSettleContractOrder", new Class[]
                {SubAccount.class, WEB3ToSuccIfoChangeSettleContractOrderSpec.class, String.class,
                        WEB3ToSuccIfoOrderUnitImpl.class}))
        {
            log.debug("webbroker3.triggerorder.WEB3ToSuccOrderManagerImplForMock.submitIfoChangeSettleContractOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoChangeSettleContractOrder",
                    new Class[]
                    {SubAccount.class, WEB3ToSuccIfoChangeSettleContractOrderSpec.class, String.class,
                            WEB3ToSuccIfoOrderUnitImpl.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoChangeSettleContractOrder",
                    new Class[]
                    {SubAccount.class, WEB3ToSuccIfoChangeSettleContractOrderSpec.class, String.class,
                            WEB3ToSuccIfoOrderUnitImpl.class}).asVoid();
            return;
        }
        super.submitIfoChangeSettleContractOrder(l_subAccount, l_toSuccIfoChangeSettleContractOrderSpec,
                l_strTradingPassword, l_changingBeforeRsvIfoOrderUnit);
    }

    public void validateEveningSessionOrderPossibleChange(String l_strExpirationDateType,
            WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "validateEveningSessionOrderPossibleChange", new Class[]
                {String.class, WEB3ToSuccIfoOrderUnitImpl.class}, new Object[]
                {l_strExpirationDateType, l_toSuccIfoOrderUnitImpl});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "validateEveningSessionOrderPossibleChange", new Class[]
                {String.class, WEB3ToSuccIfoOrderUnitImpl.class}))
        {
            log
                    .debug("webbroker3.triggerorder.WEB3ToSuccOrderManagerImplForMock.validateEveningSessionOrderPossibleChange()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateEveningSessionOrderPossibleChange", new Class[]
                    {String.class, WEB3ToSuccIfoOrderUnitImpl.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateEveningSessionOrderPossibleChange", new Class[]
                    {String.class, WEB3ToSuccIfoOrderUnitImpl.class}).asVoid();
            return;
        }
        super.validateEveningSessionOrderPossibleChange(l_strExpirationDateType, l_toSuccIfoOrderUnitImpl);
    }

    public WEB3ToSuccEqTypeOrderUnitImpl getReserveEqtypeOrderUnit(long l_lngOrderId) throws NotFoundException,
            WEB3BaseException
    { 
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "getReserveEqtypeOrderUnit", new Class[]
                {long.class}, new Object[]
                {new Long(l_lngOrderId)});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "getReserveEqtypeOrderUnit", new Class[]
                {long.class}))
        {
            log.debug("webbroker3.triggerorder.WEB3ToSuccOrderManagerImplForMock.createIfoOrderUnit()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "getReserveEqtypeOrderUnit", new Class[]
                    {long.class}).asWEB3BaseException();
            return (WEB3ToSuccEqTypeOrderUnitImpl) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "getReserveEqtypeOrderUnit", new Class[]
                    {long.class}).asObject();
        }
        return super.getReserveEqtypeOrderUnit(l_lngOrderId);
    }
    public void setOrderedToOrderUnit(ProductTypeEnum l_productType, long l_lngOrderId) throws WEB3BaseException 
    {
    	TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "setOrderedToOrderUnit", new Class[]
                {ProductTypeEnum.class, long.class}, new Object[]
                {l_productType, new Long(l_lngOrderId)});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "setOrderedToOrderUnit", new Class[]
                {ProductTypeEnum.class, long.class}))
        {
            log
                    .debug("webbroker3.triggerorder.WEB3ToSuccOrderManagerImplForMock.setOrderedToOrderUnit()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "setOrderedToOrderUnit", new Class[]
                    {ProductTypeEnum.class, long.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "setOrderedToOrderUnit", new Class[]
                    {ProductTypeEnum.class, long.class}).asVoid();
            return;
        }
        super.setOrderedToOrderUnit(l_productType, l_lngOrderId);
    }
    
    public WEB3FuturesOptionsContractUnit[] createIfoContractUnitByOrder(
            WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "createIfoContractUnitByOrder", new Class[]
                {WEB3ToSuccIfoOrderUnitImpl.class}, new Object[]
                {l_rsvIfoOrderUnit});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "createIfoContractUnitByOrder", new Class[]
                {WEB3ToSuccIfoOrderUnitImpl.class}))
        {
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "createIfoContractUnitByOrder", new Class[]
                    {WEB3ToSuccIfoOrderUnitImpl.class}).asWEB3BaseException();
            
            return (WEB3FuturesOptionsContractUnit[])TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "createIfoContractUnitByOrder", new Class[]
                    {WEB3ToSuccIfoOrderUnitImpl.class}).asObject();
        }
        return super.createIfoContractUnitByOrder(l_rsvIfoOrderUnit);
    }
}
@

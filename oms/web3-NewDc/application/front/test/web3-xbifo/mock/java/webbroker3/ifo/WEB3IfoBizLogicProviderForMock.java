head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.42.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoBizLogicProviderForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3IfoBizLogicProviderForMock.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/02/06 金傑 (中訊) 新規作成
 */
package webbroker3.ifo;


import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoBizLogicProviderForMock extends WEB3IfoBizLogicProvider
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoBizLogicProviderForMock.class);
    
	public WEB3GentradeCommission createCommission(long l_lngOrderUnitId) throws WEB3BaseException
	{
		TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
				"webbroker3.ifo.WEB3IfoBizLogicProvider",
				"createCommission", 
				new Class[] { long.class }, new Object[] { new Long(l_lngOrderUnitId) });

		if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
				"webbroker3.ifo.WEB3IfoBizLogicProvider",
				"createCommission", new Class[] { long.class }))
		{
			log.debug("webbroker3.ifo.WEB3IfoBizLogicProviderForMock.createCommission(long)");

			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.ifo.WEB3IfoBizLogicProvider",
					"createCommission", new Class[] { long.class }).asWEB3BaseException();

			return (WEB3GentradeCommission) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.ifo.WEB3IfoBizLogicProvider",
					"createCommission", new Class[] { long.class })
					.asObject();
		}
		return super.createCommission(l_lngOrderUnitId);
	}
    
    public void calcCommission(WEB3GentradeCommission l_commission, SubAccount l_subAccount) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoBizLogicProvider",
                "calcCommission", new Class[]
                { WEB3GentradeCommission.class, SubAccount.class }, new Object[]
                { l_commission, l_subAccount });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoBizLogicProvider",
                "calcCommission", new Class[]
                { WEB3GentradeCommission.class, SubAccount.class }))
        {
            log.debug("webbroker3.ifo.WEB3IfoBizLogicProviderForMock.calcCommission()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoBizLogicProvider",
                    "calcCommission", new Class[]
                    { WEB3GentradeCommission.class, SubAccount.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoBizLogicProvider",
                    "calcCommission", new Class[]
                    { WEB3GentradeCommission.class, SubAccount.class }).asVoid();
            return;
        }
        super.calcCommission(l_commission, l_subAccount);
    }
    
    public double calcSalesTax(double l_dblAmount, Timestamp l_tsBaseDate, SubAccount l_subAccount)
        throws WEB3BaseException
    {
    TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoBizLogicProvider",
            "calcSalesTax", new Class[]
            { double.class, Timestamp.class, SubAccount.class }, new Object[]
            { new Double(l_dblAmount), l_tsBaseDate, l_subAccount });
    if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoBizLogicProvider", "calcSalesTax",
            new Class[]
            { double.class, Timestamp.class, SubAccount.class }))
    {
        log.debug("webbroker3.ifo.WEB3IfoBizLogicProviderForMock.calcSalesTax()");
        TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoBizLogicProvider",
                "calcSalesTax", new Class[]
                { double.class, Timestamp.class, SubAccount.class }).asWEB3BaseException();
        return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.WEB3IfoBizLogicProvider", "calcSalesTax", new Class[]
                { double.class, Timestamp.class, SubAccount.class }).asDouble();
    }
    return super.calcSalesTax(l_dblAmount, l_tsBaseDate, l_subAccount);
    }

    public double calcRestraintTurnOver(
        double l_dblQuantity,
        double l_dblUnitPrice,
        long l_lngBranchId,
        String l_strCommissionProductCode,
        boolean l_blnIsLimitPrice,
        WEB3IfoTradedProductImpl l_ifoTradedProduct)
        throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.ifo.WEB3IfoBizLogicProvider",
                "calcRestraintTurnOver", 
                new Class[]
                {double.class, double.class, long.class, String.class, boolean.class, WEB3IfoTradedProductImpl.class},
                new Object[]
                {new Double(l_dblQuantity), new Double(l_dblUnitPrice), new Long(l_lngBranchId),
                        l_strCommissionProductCode, new Boolean(l_blnIsLimitPrice), l_ifoTradedProduct});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoBizLogicProvider", "calcRestraintTurnOver",
                new Class[]
                {double.class, double.class, long.class, String.class, boolean.class, WEB3IfoTradedProductImpl.class}))
        {
            log.debug("webbroker3.ifo.WEB3IfoBizLogicProviderForMock.calcRestraintTurnOver()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoBizLogicProvider",
                    "calcRestraintTurnOver",
                    new Class[]
                    {double.class, double.class, long.class, String.class, boolean.class,
                            WEB3IfoTradedProductImpl.class}).asWEB3BaseException();
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoBizLogicProvider",
                    "calcRestraintTurnOver",
                    new Class[]
                    {double.class, double.class, long.class, String.class, boolean.class,
                            WEB3IfoTradedProductImpl.class}).asDouble();
        }
        return super.calcRestraintTurnOver(l_dblQuantity, l_dblUnitPrice, l_lngBranchId, l_strCommissionProductCode,
                l_blnIsLimitPrice, l_ifoTradedProduct);
    }
    
    public double calcTurnOver(
        double l_dblQuantity,
        double l_dblCalcUnitPrice,
        WEB3IfoTradedProductImpl l_ifoTradedProduct)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoBizLogicProvider", "calcTurnOver",
                new Class[]
                {double.class, double.class, WEB3IfoTradedProductImpl.class}, new Object[]
                {new Double(l_dblQuantity), new Double(l_dblCalcUnitPrice), l_ifoTradedProduct});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoBizLogicProvider", "calcTurnOver",
                new Class[]
                {double.class, double.class, WEB3IfoTradedProductImpl.class}))
        {
            log.debug("webbroker3.ifo.WEB3IfoBizLogicProviderForMock.calcTurnOver()");
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoBizLogicProvider",
                    "calcTurnOver", new Class[]
                    {double.class, double.class, WEB3IfoTradedProductImpl.class}).asDouble();
        }
        return super.calcTurnOver(l_dblQuantity, l_dblCalcUnitPrice, l_ifoTradedProduct);
    }

    public double calcDeliveryAmount(
        SideEnum l_dealing,
        double l_dblExpenseCalculationAmount,
        double l_dblConsignmentCommission,
        double l_dblCommConsumptionTax)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoBizLogicProvider",
                "calcDeliveryAmount", new Class[]
                {SideEnum.class, double.class, double.class, double.class}, new Object[]
                {l_dealing, new Double(l_dblExpenseCalculationAmount), new Double(l_dblConsignmentCommission),
                        new Double(l_dblCommConsumptionTax)});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoBizLogicProvider", "calcDeliveryAmount",
                new Class[]
                {SideEnum.class, double.class, double.class, double.class}))
        {
            log.debug("webbroker3.ifo.WEB3IfoBizLogicProviderForMock.calcDeliveryAmount()");
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoBizLogicProvider",
                    "calcDeliveryAmount", new Class[]
                    {SideEnum.class, double.class, double.class, double.class}).asDouble();
        }
        return super.calcDeliveryAmount(l_dealing, l_dblExpenseCalculationAmount, l_dblConsignmentCommission,
                l_dblCommConsumptionTax);
    }
    
}
@

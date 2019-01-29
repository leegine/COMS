head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.01.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondDomesticOrderUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd;

import java.math.BigDecimal;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

public class WEB3BondDomesticOrderUpdateInterceptorTest extends TestBaseForMock
{

    public WEB3BondDomesticOrderUpdateInterceptorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticOrderUpdateInterceptorTest.class);

    public void test_mutateBondOrderUnitParams_0001()
    {
        final String STR_METHOD_NAME =
            " test_bondDomesticApplyComplete_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                WEB3ChannelDef.CALL_CENTER);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123456L);
            l_bondProductParams.setProductCode("123");
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123456L);
            TestDBUtility.insertWithDel(l_productParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            OrderManagerPersistenceType l_type = OrderManagerPersistenceType.UPDATE;

            OrderManagerPersistenceContext l_context = OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER;
            WEB3BondDomesticOrderUpdateInterceptor l_bondDomesticOrderUpdateInterceptor =
                new WEB3BondDomesticOrderUpdateInterceptor();

            WEB3BondOrderTypeJudge l_bondOrderTypeJudge = new WEB3BondOrderTypeJudge();
            WEB3BondNewOrderSpec l_bondNewOrderSpec = WEB3BondNewOrderSpec.createBondNewOrderSpec(
                null, l_bondOrderTypeJudge, "123", 1.1D, 1.1D, TaxTypeEnum.UNDEFINED,
                GtlUtils.getSystemTimestamp(), "1");
            l_bondDomesticOrderUpdateInterceptor.setBondNewOrderSpec(l_bondNewOrderSpec);

            WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult = new WEB3BondEstimatedPriceCalcResult();
            l_bondEstimatedPriceCalcResult.setPrice(new BigDecimal("2.2"));
            l_bondEstimatedPriceCalcResult.setTradingPrice(new BigDecimal("1.1"));
            l_bondEstimatedPriceCalcResult.setAccruedInterest(new BigDecimal("3.13"));
            l_bondEstimatedPriceCalcResult.setEstimatedPrice(new BigDecimal("4.4"));
            l_bondDomesticOrderUpdateInterceptor.setBondEstimatedPriceCalcResult(l_bondEstimatedPriceCalcResult);

            WEB3BondExecuteDateInfo l_bondExecuteDateInfo = new WEB3BondExecuteDateInfo();
            l_bondExecuteDateInfo.setDeliveryDate(GtlUtils.getSystemTimestamp());
            l_bondExecuteDateInfo.setBizDate(GtlUtils.getSystemTimestamp());
            l_bondDomesticOrderUpdateInterceptor.setBondExecuteDateInfo(l_bondExecuteDateInfo);

            BondOrderUnitParams l_orderUnitRow = TestDBUtility.getBondOrderUnitRow();
            l_orderUnitRow.setAccountId(1001L);
            TestDBUtility.insertWithDel(l_orderUnitRow);
            BondOrderUnitParams l_bondOrderUnitParams =
                l_bondDomesticOrderUpdateInterceptor.mutate(l_type, l_context, l_orderUnitRow);
            assertEquals(new BigDecimal("2.2"), new BigDecimal(l_bondOrderUnitParams.getPrice()+ ""));
            assertEquals(new BigDecimal("1.1"), new BigDecimal(l_bondOrderUnitParams.getTradingPrice()+ ""));
            assertEquals(new BigDecimal("3.13"), new BigDecimal(l_bondOrderUnitParams.getAccruedInterest() + ""));
            assertEquals(new BigDecimal("4.4"), new BigDecimal(l_bondOrderUnitParams.getEstimatedPrice() + ""));

            String l_strMethodReturnValue = (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class}).asObject();
            assertEquals("2", l_strMethodReturnValue);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public void test_mutateBondOrderUnitParams_0003()
    {
        final String STR_METHOD_NAME =
            " test_bondDomesticApplyComplete_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                WEB3ChannelDef.CALL_CENTER);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123456L);
            l_bondProductParams.setProductCode("123");
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123456L);
            TestDBUtility.insertWithDel(l_productParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            OrderManagerPersistenceType l_type = OrderManagerPersistenceType.UPDATE;

            OrderManagerPersistenceContext l_context = OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER;
            WEB3BondDomesticOrderUpdateInterceptor l_bondDomesticOrderUpdateInterceptor =
                new WEB3BondDomesticOrderUpdateInterceptor();

            WEB3BondOrderTypeJudge l_bondOrderTypeJudge = new WEB3BondOrderTypeJudge();
            WEB3BondNewOrderSpec l_bondNewOrderSpec = WEB3BondNewOrderSpec.createBondNewOrderSpec(
                null, l_bondOrderTypeJudge, "123", 1.1D, 1.1D, TaxTypeEnum.UNDEFINED,
                GtlUtils.getSystemTimestamp(), "1");
            l_bondDomesticOrderUpdateInterceptor.setBondNewOrderSpec(l_bondNewOrderSpec);

            WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult = new WEB3BondEstimatedPriceCalcResult();
            l_bondEstimatedPriceCalcResult.setPrice(null);
            l_bondEstimatedPriceCalcResult.setTradingPrice(null);
            l_bondEstimatedPriceCalcResult.setAccruedInterest(null);
            l_bondEstimatedPriceCalcResult.setEstimatedPrice(null);
            l_bondDomesticOrderUpdateInterceptor.setBondEstimatedPriceCalcResult(l_bondEstimatedPriceCalcResult);

            WEB3BondExecuteDateInfo l_bondExecuteDateInfo = new WEB3BondExecuteDateInfo();
            l_bondExecuteDateInfo.setDeliveryDate(GtlUtils.getSystemTimestamp());
            l_bondExecuteDateInfo.setBizDate(GtlUtils.getSystemTimestamp());
            l_bondDomesticOrderUpdateInterceptor.setBondExecuteDateInfo(l_bondExecuteDateInfo);

            BondOrderUnitParams l_orderUnitRow = TestDBUtility.getBondOrderUnitRow();
            l_orderUnitRow.setAccountId(1001L);
            TestDBUtility.insertWithDel(l_orderUnitRow);
            BondOrderUnitParams l_bondOrderUnitParams =
                l_bondDomesticOrderUpdateInterceptor.mutate(l_type, l_context, l_orderUnitRow);
            assertTrue(l_bondOrderUnitParams.getPriceIsNull());
            assertTrue(l_bondOrderUnitParams.getTradingPriceIsNull());
            assertTrue(l_bondOrderUnitParams.getAccruedInterestIsNull());
            assertTrue(l_bondOrderUnitParams.getEstimatedPriceIsNull());

            String l_strMethodReturnValue = (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class}).asObject();
            assertEquals("2", l_strMethodReturnValue);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public void test_mutateBondOrderUnitParams_0002()
    {
        final String STR_METHOD_NAME =
            " test_bondDomesticApplyComplete_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            OrderManagerPersistenceType l_type = OrderManagerPersistenceType.UPDATE;

            OrderManagerPersistenceContext l_context = OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER;
            WEB3BondDomesticOrderUpdateInterceptor l_bondDomesticOrderUpdateInterceptor =
                new WEB3BondDomesticOrderUpdateInterceptor();

            BondOrderUnitParams l_orderUnitRow = null;
            l_bondDomesticOrderUpdateInterceptor.mutate(l_type, l_context, l_orderUnitRow);
            fail();
        }
        catch (WEB3BaseRuntimeException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public void test_mutateBondOrderActionParams_0001()
    {
        final String STR_METHOD_NAME =
            " test_mutateBondOrderActionParams_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            OrderManagerPersistenceType l_type = OrderManagerPersistenceType.UPDATE;

            OrderManagerPersistenceContext l_context = OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER;
            WEB3BondDomesticOrderUpdateInterceptor l_bondDomesticOrderUpdateInterceptor =
                new WEB3BondDomesticOrderUpdateInterceptor();

            BondOrderActionParams l_orderUnitRow = null;
            l_bondDomesticOrderUpdateInterceptor.mutate(l_type, l_context, l_orderUnitRow);
            fail();
        }
        catch (WEB3BaseRuntimeException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public void test_mutateBondOrderActionParams_0002()
    {
        final String STR_METHOD_NAME =
            " test_mutateBondOrderActionParams_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            OrderManagerPersistenceType l_type = OrderManagerPersistenceType.UPDATE;

            OrderManagerPersistenceContext l_context = OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER;
            WEB3BondDomesticOrderUpdateInterceptor l_bondDomesticOrderUpdateInterceptor =
                new WEB3BondDomesticOrderUpdateInterceptor();

            BondOrderActionParams l_orderUnitRow = new BondOrderActionParams();
            l_orderUnitRow.setOrderUnitId(1001L);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(1001L);
            l_bondOrderUnitParams.setPaymentDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_bondOrderUnitParams.setBaseFxRate(3.1D);
            l_bondOrderUnitParams.setExecFxRate(4.1D);
            l_bondOrderUnitParams.setTradingPrice(5.1D);
            l_bondOrderUnitParams.setForeignTradingPrice(6.1D);
            l_bondOrderUnitParams.setAccruedInterest(7.1D);
            l_bondOrderUnitParams.setForeignAccruedInterest(8.1D);
            l_bondOrderUnitParams.setEstimatedPrice(9.1D);
            l_bondOrderUnitParams.setForeignEstimatedPrice(10.1D);
            l_bondOrderUnitParams.setAdjustmentBeforeMaturity(11.1D);
            l_bondOrderUnitParams.setElapsedDays(1);
            l_bondOrderUnitParams.setCalcBaseDays(2);
            l_bondOrderUnitParams.setForeignExecDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_bondOrderUnitParams.setOrderExecStatus("1");
            l_bondOrderUnitParams.setLimitPrice(2.1D);
            l_bondOrderUnitParams.setPrice(1.1D);
            l_bondOrderUnitParams.setDealType("1");
            l_bondOrderUnitParams.setTraderId(0L);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            BondOrderActionParams l_bondOrderActionParams =
                l_bondDomesticOrderUpdateInterceptor.mutate(l_type, l_context, l_orderUnitRow);
            assertEquals(0L, l_bondOrderActionParams.getTraderId());
            assertEquals(new BigDecimal("1.1"), new BigDecimal(l_bondOrderActionParams.getPrice()+ ""));
            assertEquals(new BigDecimal("2.1"), new BigDecimal(l_bondOrderActionParams.getLimitPrice()+ ""));
            assertEquals(new BigDecimal("3.1"), new BigDecimal(l_bondOrderActionParams.getBaseFxRate()+ ""));
            assertEquals(new BigDecimal("4.1"), new BigDecimal(l_bondOrderActionParams.getExecFxRate()+ ""));
            assertEquals(new BigDecimal("5.1"), new BigDecimal(l_bondOrderActionParams.getTradingPrice()+ ""));
            assertEquals(new BigDecimal("6.1"),
                new BigDecimal(l_bondOrderActionParams.getForeignTradingPrice()+ ""));
            assertEquals(new BigDecimal("7.1"),
                new BigDecimal(l_bondOrderActionParams.getAccruedInterest()+ ""));
            assertEquals(new BigDecimal("8.1"),
                new BigDecimal(l_bondOrderActionParams.getForeignAccruedInterest()+ ""));
            assertEquals(new BigDecimal("9.1"), new BigDecimal(l_bondOrderActionParams.getEstimatedPrice()+ ""));
            assertEquals(new BigDecimal("10.1"),
                new BigDecimal(l_bondOrderActionParams.getForeignEstimatedPrice()+ ""));
            assertEquals(new BigDecimal("11.1"),
                new BigDecimal(l_bondOrderActionParams.getAdjustmentBeforeMaturity()+ ""));
            assertEquals(1, l_bondOrderActionParams.getElapsedDays());
            assertEquals(2, l_bondOrderActionParams.getCalcBaseDays());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }


    public void test_mutateBondOrderActionParams_0003()
    {
        final String STR_METHOD_NAME =
            " test_mutateBondOrderActionParams_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            OrderManagerPersistenceType l_type = OrderManagerPersistenceType.UPDATE;

            OrderManagerPersistenceContext l_context = OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER;
            WEB3BondDomesticOrderUpdateInterceptor l_bondDomesticOrderUpdateInterceptor =
                new WEB3BondDomesticOrderUpdateInterceptor();

            BondOrderActionParams l_orderUnitRow = new BondOrderActionParams();
            l_orderUnitRow.setOrderUnitId(1001L);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(1001L);
            l_bondOrderUnitParams.setPaymentDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_bondOrderUnitParams.setBaseFxRate(null);
            l_bondOrderUnitParams.setExecFxRate(null);
            l_bondOrderUnitParams.setTradingPrice(null);
            l_bondOrderUnitParams.setForeignTradingPrice(null);
            l_bondOrderUnitParams.setAccruedInterest(null);
            l_bondOrderUnitParams.setForeignAccruedInterest(null);
            l_bondOrderUnitParams.setEstimatedPrice(null);
            l_bondOrderUnitParams.setForeignEstimatedPrice(null);
            l_bondOrderUnitParams.setAdjustmentBeforeMaturity(null);
            l_bondOrderUnitParams.setElapsedDays(null);
            l_bondOrderUnitParams.setCalcBaseDays(null);
            l_bondOrderUnitParams.setForeignExecDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_bondOrderUnitParams.setOrderExecStatus("1");
            l_bondOrderUnitParams.setLimitPrice(null);
            l_bondOrderUnitParams.setPrice(null);
            l_bondOrderUnitParams.setDealType("1");
            l_bondOrderUnitParams.setTraderId(null);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            BondOrderActionParams l_bondOrderActionParams =
                l_bondDomesticOrderUpdateInterceptor.mutate(l_type, l_context, l_orderUnitRow);
            assertTrue(l_bondOrderActionParams.getTraderIdIsNull());
            assertTrue(l_bondOrderActionParams.getPriceIsNull());
            assertTrue(l_bondOrderActionParams.getLimitPriceIsNull());
            assertTrue(l_bondOrderActionParams.getBaseFxRateIsNull());
            assertTrue(l_bondOrderActionParams.getExecFxRateIsNull());
            assertTrue(l_bondOrderActionParams.getTradingPriceIsNull());
            assertTrue(l_bondOrderActionParams.getForeignTradingPriceIsNull());
            assertTrue(l_bondOrderActionParams.getAccruedInterestIsNull());
            assertTrue(l_bondOrderActionParams.getForeignAccruedInterestIsNull());
            assertTrue(l_bondOrderActionParams.getEstimatedPriceIsNull());
            assertTrue(l_bondOrderActionParams.getForeignEstimatedPriceIsNull());
            assertTrue(l_bondOrderActionParams.getAdjustmentBeforeMaturityIsNull());
            assertTrue(l_bondOrderActionParams.getElapsedDaysIsNull());
            assertTrue(l_bondOrderActionParams.getCalcBaseDaysIsNull());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }
}
@

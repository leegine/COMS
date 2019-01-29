head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.15.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundTradeOrderNotifyConfirmInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualFundTradeOrderNotifyConfirmInterceptorTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundTradeOrderNotifyConfirmInterceptorTest.class);

    public WEB3MutualFundTradeOrderNotifyConfirmInterceptorTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testMutate1_C0001()
    {

        final String STR_METHOD_NAME = "testMutate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderManagerPersistenceType l_orderManagerPersistenceType = null;
        OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
        MutualFundOrderActionParams l_mutualFundOrderActionParams = null;

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        try
        {
            l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_mutualFundOrderActionParams);
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage());
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMutate1_C0002()
    {

        final String STR_METHOD_NAME = "testMutate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderManagerPersistenceType l_orderManagerPersistenceType = null;
        OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
        MutualFundOrderActionParams l_mutualFundOrderActionParams =
            new MutualFundOrderActionParams();

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        MutualFundOrderActionParams l_actionParams = null;
        try
        {
            l_actionParams = l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_mutualFundOrderActionParams);
            assertNull(l_actionParams.getErrorReasonCode());
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage());
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMutate2_C0001()
    {
        final String STR_METHOD_NAME = "testMutate2_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderManagerPersistenceType l_orderManagerPersistenceType = null;
        OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams = null;

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        try
        {
            l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_mutualFundOrderUnitParams);
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage());
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMutate2_C0002()
    {
        final String STR_METHOD_NAME = "testMutate2_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderManagerPersistenceType l_orderManagerPersistenceType = null;
        OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams =
            TestDBUtility.getMutualFundOrderUnitRow();

        MutualFundOrderUnitParams l_unitParams = null;
        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        try
        {
            l_interceptor.setBizDate(new Timestamp(20081010));
            l_interceptor.setMutualFundType("1");
            l_interceptor.setSwitchingConstantValue(Double.NaN);
            l_interceptor.setSwitchingEstimatedQty(Double.NaN);
            l_unitParams = l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_mutualFundOrderUnitParams);
            assertEquals(1, l_unitParams.getFundType().intValue());
            assertTrue(GtlUtils.Double.isZero(l_unitParams.getSwtCalcConstantValue()));
            assertTrue(GtlUtils.Double.isZero(l_unitParams.getSwtEstimateDealingQty()));
            assertNull(l_unitParams.getExecStatus());
            assertNull(l_unitParams.getErrorReasonCode());
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage());
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    public void testMutate2_C0003()
    {
        final String STR_METHOD_NAME = "testMutate2_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderManagerPersistenceType l_orderManagerPersistenceType = null;
        OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams =
            TestDBUtility.getMutualFundOrderUnitRow();

        MutualFundOrderUnitParams l_unitParams = null;
        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        try
        {
            l_interceptor.setBizDate(new Timestamp(20081010));
            l_interceptor.setMutualFundType("2");
            l_interceptor.setSwitchingConstantValue(20);
            l_interceptor.setSwitchingEstimatedQty(Double.NaN);
            l_unitParams = l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_mutualFundOrderUnitParams);
            assertEquals(2, l_unitParams.getFundType().intValue());
            assertTrue(GtlUtils.Double.isEqual(20, l_unitParams.getSwtCalcConstantValue()));
            assertTrue(GtlUtils.Double.isZero(l_unitParams.getSwtEstimateDealingQty()));
            assertNull(l_unitParams.getExecStatus());
            assertNull(l_unitParams.getErrorReasonCode());
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage());
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMutate2_C0004()
    {
        final String STR_METHOD_NAME = "testMutate2_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderManagerPersistenceType l_orderManagerPersistenceType = null;
        OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams =
            TestDBUtility.getMutualFundOrderUnitRow();

        MutualFundOrderUnitParams l_unitParams = null;
        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        try
        {
            l_interceptor.setBizDate(new Timestamp(20081010));
            l_interceptor.setMutualFundType("0");
            l_interceptor.setSwitchingConstantValue(45);
            l_interceptor.setSwitchingEstimatedQty(40);
            l_interceptor.setBizDate(new Timestamp(20081010));
            l_interceptor.setOrderType(OrderTypeEnum.MARGIN_LONG);
            l_unitParams = l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_mutualFundOrderUnitParams);

            String l_strBizDate = 
                WEB3DateUtility.formatDate(
                        new Timestamp(20081010),
                        "yyyyMMdd");
            assertEquals(l_strBizDate, l_unitParams.getBizDate());
            assertEquals(3, l_unitParams.getOrderType().intValue());
            assertEquals(0, l_unitParams.getFundType().intValue());
            assertTrue(GtlUtils.Double.isEqual(45, l_unitParams.getSwtCalcConstantValue()));
            assertTrue(GtlUtils.Double.isEqual(40, l_unitParams.getSwtEstimateDealingQty()));
            assertNull(l_unitParams.getExecStatus());
            assertNull(l_unitParams.getErrorReasonCode());
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage());
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetDeliveryDate()
    {
        final String STR_METHOD_NAME = "testSetDeliveryDate()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        l_interceptor.setDeliveryDate(new Timestamp(20081010));
        assertEquals(new Timestamp(20081010), l_interceptor.getDeliveryDate());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetBizDate()
    {
        final String STR_METHOD_NAME = "testSetBizDate()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        l_interceptor.setBizDate(new Timestamp(20081010));
        assertEquals(new Timestamp(20081010), l_interceptor.getBizDate());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetAcceptDate()
    {
        final String STR_METHOD_NAME = "testSetAcceptDate()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        l_interceptor.setAcceptDate(new Timestamp(20081010));
        assertEquals(new Timestamp(20081010), l_interceptor.getAcceptDate());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetDiscriminationCode()
    {
        final String STR_METHOD_NAME = "testSetDiscriminationCode()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        l_interceptor.setDiscriminationCode("123");
        assertEquals("123", l_interceptor.getDiscriminationCode());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetConstantValue()
    {
        final String STR_METHOD_NAME = "testSetConstantValue()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        l_interceptor.setConstantValue(123);
        assertTrue(GtlUtils.Double.isEqual(123, l_interceptor.getConstantValue()));

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetSwitchingConstantValue()
    {
        final String STR_METHOD_NAME = "testSetSwitchingConstantValue()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        l_interceptor.setSwitchingConstantValue(123);
        assertTrue(GtlUtils.Double.isEqual(123, l_interceptor.getSwitchingConstantValue()));

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetEstimatedPrice()
    {
        final String STR_METHOD_NAME = "testSetEstimatedPrice()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        l_interceptor.setEstimatedPrice(123);
        assertTrue(GtlUtils.Double.isEqual(123, l_interceptor.getEstimatedPrice()));

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetEstimatedQty()
    {
        final String STR_METHOD_NAME = "testSetEstimatedQty()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        l_interceptor.setEstimatedQty(123);
        assertTrue(GtlUtils.Double.isEqual(123, l_interceptor.getEstimatedQty()));

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetDeliveryDiv()
    {
        final String STR_METHOD_NAME = "testSetDeliveryDiv()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        l_interceptor.setDeliveryDiv("1");
        assertEquals("1", l_interceptor.getDeliveryDiv());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetMutualFundType()
    {
        final String STR_METHOD_NAME = "testSetMutualFundType()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        l_interceptor.setMutualFundType("1");
        assertEquals("1", l_interceptor.getMutualFundType());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetExecutionTimestamp()
    {
        final String STR_METHOD_NAME = "testSetExecutionTimestamp()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        l_interceptor.setExecutionTimestamp(new Timestamp(20081010));
        assertEquals(new Timestamp(20081010), l_interceptor.getExecutionTimestamp());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetSettlementType()
    {
        final String STR_METHOD_NAME = "testSetSettlementType()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        l_interceptor.setSettlementType("2");
        assertEquals("2", l_interceptor.getSettlementType());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetNoCommissionDivision()
    {
        final String STR_METHOD_NAME = "testSetNoCommissionDivision()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        l_interceptor.setNoCommissionDivision("12");
        assertEquals("12", l_interceptor.getNoCommissionDivision());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetSwitchingSubjectMutualProductCode()
    {
        final String STR_METHOD_NAME = "testSetSwitchingSubjectMutualProductCode()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        l_interceptor.setSwitchingSubjectMutualProductCode("12");
        assertEquals("12", l_interceptor.getSwitchingSubjectMutualProductCode());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetRequestDivision()
    {
        final String STR_METHOD_NAME = "testSetRequestDivision()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        l_interceptor.setRequestDivision("12");
        assertEquals("12", l_interceptor.getRequestDivision());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetSonarTraderCode()
    {
        final String STR_METHOD_NAME = "testSetSonarTraderCode()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        l_interceptor.setSonarTraderCode("12");
        assertEquals("12", l_interceptor.getSonarTraderCode());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetMutualFundSellDiv()
    {
        final String STR_METHOD_NAME = "testSetMutualFundSellDiv()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        l_interceptor.setMutualFundSellDiv("12");
        assertEquals("12", l_interceptor.getMutualFundSellDiv());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetConstantValueAppDate()
    {
        final String STR_METHOD_NAME = "testSetConstantValueAppDate()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        l_interceptor.setConstantValueAppDate(new Timestamp(20081010));
        assertEquals(new Timestamp(20081010), l_interceptor.getConstantValueAppDate());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetSwitchingEstimatedQty()
    {
        final String STR_METHOD_NAME = "testSetSwitchingEstimatedQty()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        l_interceptor.setSwitchingEstimatedQty(12);
        assertTrue(GtlUtils.Double.isEqual(12, l_interceptor.getSwitchingEstimatedQty()));

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetSwitchingSubjectTaxDivision()
    {
        final String STR_METHOD_NAME = "testSetSwitchingSubjectTaxDivision()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        l_interceptor.setSwitchingSubjectTaxDivision(TaxTypeEnum.NORMAL);
        assertEquals(TaxTypeEnum.NORMAL, l_interceptor.getSwitchingSubjectTaxDivision());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetOrderType()
    {
        final String STR_METHOD_NAME = "testSetOrderType()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        l_interceptor.setOrderType(OrderTypeEnum.ASSET_IN);
        assertEquals(OrderTypeEnum.ASSET_IN, l_interceptor.getOrderType());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetPaymentDate()
    {
        final String STR_METHOD_NAME = "testSetPaymentDate()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        l_interceptor.setPaymentDate(new Timestamp(20081010));
        assertEquals(new Timestamp(20081010), l_interceptor.getPaymentDate());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetCPUNo()
    {
        final String STR_METHOD_NAME = "testSetCPUNo()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundTradeOrderNotifyConfirmInterceptor l_interceptor =
            new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
        l_interceptor.setCPUNo("12");
        assertEquals("12", l_interceptor.getCPUNo());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@

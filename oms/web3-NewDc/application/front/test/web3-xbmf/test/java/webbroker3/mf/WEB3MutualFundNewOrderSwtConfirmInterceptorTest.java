head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.15.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundNewOrderSwtConfirmInterceptorTest.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualFundNewOrderSwtConfirmInterceptorTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundTradeOrderNotifyConfirmInterceptorTest.class);

    private WEB3MutualFundNewOrderSwtConfirmInterceptor l_interceptor;

    public WEB3MutualFundNewOrderSwtConfirmInterceptorTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        this.l_interceptor = new WEB3MutualFundNewOrderSwtConfirmInterceptor();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.l_interceptor = null;
    }

    public void testMutate_C0001()
    {
        final String STR_METHOD_NAME = "testMutate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderManagerPersistenceType l_orderManagerPersistenceType = null;
        OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams = null;

        try
        {
            this.l_interceptor.mutate(
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

    public void testMutate_C0002()
    {
        final String STR_METHOD_NAME = "testMutate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderManagerPersistenceType l_orderManagerPersistenceType = null;
        OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams =
            TestDBUtility.getMutualFundOrderUnitRow();
        l_mutualFundOrderUnitParams.setAccountId(123);
        l_mutualFundOrderUnitParams.setSubAccountId(12345);

        MutualFundOrderUnitParams l_params = null;
        try
        {
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountId(12345);
            l_subAccountParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setMarketCode("N1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber",
                    new Class[] { String.class, String.class, ProductTypeEnum.class },
                    "111");

            this.l_interceptor.setMutualFundType("0");
            this.l_interceptor.setSwitchingExecutionTimestamp(new Timestamp(20081010));
            this.l_interceptor.setBizDate("20081010");

            l_params = this.l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_mutualFundOrderUnitParams);

            assertEquals("20081010", l_params.getBizDate());
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetWithholdingTaxRestriction_C0001()
    {
        final String STR_METHOD_NAME = "testSetWithholdingTaxRestriction_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_interceptor.setWithholdingTaxRestriction(new Double(100));

        boolean l_blReturn = GtlUtils.Double.isEqual(
            this.l_interceptor.getWithholdingTaxRestriction().doubleValue(),
            100);
        assertTrue(l_blReturn);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetSwitchingExecutionTimestamp_C0001()
    {
        final String STR_METHOD_NAME = "testSetSwitchingExecutionTimestamp_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_interceptor.setSwitchingExecutionTimestamp(new Timestamp(1200));

        assertEquals(this.l_interceptor.getSwitchingExecutionTimestamp(), new Timestamp(1200));
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetBizDate_C0001()
    {
        final String STR_METHOD_NAME = "testSetBizDate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_interceptor.setBizDate("20081010");

        assertEquals(this.l_interceptor.getBizDate(), "20081010");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@

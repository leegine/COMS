head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.14.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundNewOrderApplyConfirmInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf;

import java.sql.Timestamp;

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

public class WEB3MutualFundNewOrderApplyConfirmInterceptorTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyApplyServiceInterceptorTest.class);

    public WEB3MutualFundNewOrderApplyConfirmInterceptorTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testMutate_C0001()
    {
        final String STR_METHOD_NAME = "testMutate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundNewOrderApplyConfirmInterceptor l_interceptor =
            new WEB3MutualFundNewOrderApplyConfirmInterceptor();

        OrderManagerPersistenceType l_type = null;
        OrderManagerPersistenceContext l_context = null;
        MutualFundOrderUnitParams l_params = null;

        try
        {
            l_interceptor.mutate(l_type, l_context, l_params);
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
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

        WEB3MutualFundNewOrderApplyConfirmInterceptor l_interceptor =
            new WEB3MutualFundNewOrderApplyConfirmInterceptor();

        OrderManagerPersistenceType l_orderManagerPersistenceType = null;
        OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams =
            TestDBUtility.getMutualFundOrderUnitRow();
        l_mutualFundOrderUnitParams.setAccountId(123);
        l_mutualFundOrderUnitParams.setSubAccountId(12345);

        MutualFundOrderUnitParams l_unitParams = null;
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

            l_interceptor.setMutualFundType("0");
            l_unitParams = l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_mutualFundOrderUnitParams);

            assertEquals("111", l_unitParams.getOrderRequestNumber());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage());
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetPaymentDate()
    {
        final String STR_METHOD_NAME = "testSetPaymentDate()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFundNewOrderApplyConfirmInterceptor l_interceptor =
            new WEB3MutualFundNewOrderApplyConfirmInterceptor();

        l_interceptor.setPaymentDate(new Timestamp(20081010));

        assertEquals(l_interceptor.getPaymentDate(), new Timestamp(20081010));
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@

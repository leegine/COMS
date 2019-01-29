head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.14.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualCancelAcceptUnitServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundOrderUnitImpl;

import test.util.TestDBUtility;
import test.util.TestSpecialClassUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualCancelAcceptUnitServiceInterceptorTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyApplyServiceInterceptorTest.class);

    public WEB3MutualCancelAcceptUnitServiceInterceptorTest(String name)
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

    public void testOnCall_C0001()
    {
        final String STR_METHOD_NAME = "testOnCall_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualCancelAcceptUnitServiceInterceptor l_interceptor =
            new WEB3MutualCancelAcceptUnitServiceInterceptor();
        try
        {
            l_interceptor.onCall(null, null);
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testOnCall_C0002()
    {
        final String STR_METHOD_NAME = "testOnCall_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualCancelAcceptUnitServiceInterceptor l_interceptor =
            new WEB3MutualCancelAcceptUnitServiceInterceptor();

        Object[] l_serviceParam = new Object[0];
        try
        {
            l_interceptor.onCall(null, l_serviceParam);
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testOnCall_C0003()
    {
        final String STR_METHOD_NAME = "testOnCall_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualCancelAcceptUnitServiceInterceptor l_interceptor =
            new WEB3MutualCancelAcceptUnitServiceInterceptor();
        try
        {
            TestDBUtility.deleteAll(MutualFundOrderUnitParams.TYPE);
            MutualFundOrderUnitParams l_orderUnitParams =
                TestDBUtility.getMutualFundOrderUnitRow();
            l_orderUnitParams.setOrderUnitId(123);
            l_orderUnitParams.setBranchId(123456);
            TestDBUtility.insertWithDel(l_orderUnitParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(12345);
            TestDBUtility.insertWithDel(l_branchParams);

            Object[] l_serviceParam = new Object[1];
            MutualFundOrderUnit l_unit = new MutualFundOrderUnitImplTest(123);
            l_serviceParam[0] = l_unit;

            l_interceptor.onCall(null, l_serviceParam);
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        } 
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage());
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testOnCall_C0004()
    {
        final String STR_METHOD_NAME = "testOnCall_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualCancelAcceptUnitServiceInterceptor l_interceptor =
            new WEB3MutualCancelAcceptUnitServiceInterceptor();
        try
        {
            TestDBUtility.deleteAll(MutualFundOrderUnitParams.TYPE);
            MutualFundOrderUnitParams l_orderUnitParams =
                TestDBUtility.getMutualFundOrderUnitRow();
            l_orderUnitParams.setOrderUnitId(123);
            l_orderUnitParams.setBranchId(123456);
            l_orderUnitParams.setProductId(12345678);
            l_orderUnitParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_orderUnitParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(12345678);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(12345678);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(123456);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("06");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setProductCode("0001000");
            l_tradingTimeParams.setMarketCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Object[] l_serviceParam = new Object[1];
            MutualFundOrderUnit l_unit = new MutualFundOrderUnitImplTest(123);
            l_serviceParam[0] = l_unit;

            l_interceptor.onCall(null, l_serviceParam);
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            log.debug(l_ex.getMessage());
            fail();
        } 
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage());
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testOnThrowable()
    {
        TestSpecialClassUtility.testServiceInterceptor(new WEB3MutualCancelAcceptUnitServiceInterceptor());
    }
    class MutualFundOrderUnitImplTest extends MutualFundOrderUnitImpl
    {
        public MutualFundOrderUnitImplTest(long order_unit_id) throws DataQueryException, DataNetworkException
        {
            super(order_unit_id);
        }
    }

}
@

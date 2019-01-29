head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.14.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundNewOrderConfirmInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

public class WEB3MutualFundNewOrderConfirmInterceptorTest extends TestBaseForMock
{
    /**
     * Logger
     */
   private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3MutualFundNewOrderConfirmInterceptorTest.class);


    public WEB3MutualFundNewOrderConfirmInterceptorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.mf.WEB3MutualFundNewOrderConfirmInterceptor.mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, MutualFundOrderUnitParams)'
     */
    public void testMutate1()
    {
        final String STR_METHOD_NAME = "testMutate1()";
        log.entering(STR_METHOD_NAME);
        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        l_subAccountParams.setSubAccountId(33381251220301L);
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512246L);
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        l_branchParams.setBranchId(l_mainAccountParams.getBranchId());
        
        MutualFundOrderUnitParams l_mfOrderUnitParams =
            TestDBUtility.getMutualFundOrderUnitRow();
        l_mfOrderUnitParams.setOrderUnitId(2050L);
        l_mfOrderUnitParams.setSubAccountId(33381251220301L);
        l_mfOrderUnitParams.setProductId(3304148080001L);
        l_mfOrderUnitParams.setBranchId(33381);
        l_mfOrderUnitParams.setAccountId(333812512246L);
        
        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", "getNewNumber", new Class[]
                { String.class, String.class, ProductTypeEnum.class },  "123");

            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(
                WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_mfOrderUnitParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3MutualFundNewOrderConfirmInterceptor l_interceptor =
                new WEB3MutualFundNewOrderConfirmInterceptor();
            l_interceptor.setMutualFundType("2");//FOREIGN
            MutualFundOrderUnitParams l_params = new MutualFundOrderUnitParams();
            l_params = l_interceptor.mutate(null,null,l_mfOrderUnitParams);
            assertEquals(l_params.getFundType(), MutualFundTypeEnum.FOREIGN);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testMutate2()
    {
        final String STR_METHOD_NAME = "testMutate2()";
        log.entering(STR_METHOD_NAME);
        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        l_subAccountParams.setSubAccountId(33381251220301L);
        
        MutualFundOrderUnitParams l_mfOrderUnitParams =
            TestDBUtility.getMutualFundOrderUnitRow();
        l_mfOrderUnitParams.setOrderUnitId(2050L);
        l_mfOrderUnitParams.setSubAccountId(33381251220301L);
        l_mfOrderUnitParams.setProductId(3304148080001L);
        l_mfOrderUnitParams.setBranchId(33381);
        l_mfOrderUnitParams.setAccountId(333812512246L);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", "getNewNumber", new Class[]
                { String.class, String.class, ProductTypeEnum.class },  "123");

            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(
                WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_mfOrderUnitParams);
            
            WEB3MutualFundNewOrderConfirmInterceptor l_interceptor =
                new WEB3MutualFundNewOrderConfirmInterceptor();
            l_interceptor.setMutualFundType("3");//FOREIGN_MMF
            MutualFundOrderUnitParams l_params = new MutualFundOrderUnitParams();
            l_params = l_interceptor.mutate(null,null,l_mfOrderUnitParams);
            assertEquals(l_params.getFundType(), MutualFundTypeEnum.FOREIGN_MMF);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testMutatecase0001()
    {
        final String STR_METHOD_NAME = "testMutatecase0001()";
        log.entering(STR_METHOD_NAME);
        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        l_subAccountParams.setSubAccountId(33381251220301L);
        
        MutualFundOrderUnitParams l_mfOrderUnitParams =
            TestDBUtility.getMutualFundOrderUnitRow();
        l_mfOrderUnitParams.setOrderUnitId(2050L);
        l_mfOrderUnitParams.setSubAccountId(33381251220301L);
        l_mfOrderUnitParams.setProductId(3304148080001L);
        l_mfOrderUnitParams.setBranchId(33381);
        l_mfOrderUnitParams.setAccountId(333812512246L);
        l_mfOrderUnitParams.setCalcConstantValue(100);
        
        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", "getNewNumber", new Class[]
                { String.class, String.class, ProductTypeEnum.class },  "123");

            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(
                WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_mfOrderUnitParams);
            
            WEB3MutualFundNewOrderConfirmInterceptor l_interceptor =
                new WEB3MutualFundNewOrderConfirmInterceptor();
            l_interceptor.setMutualFundType("3");//FOREIGN_MMF
            l_interceptor.setConstantValue(500);
            l_interceptor.setDeliveryDate(new Timestamp(WEB3DateUtility.getDate("20070213","yyyyMMdd").getTime()));
            
            MutualFundOrderUnitRow l_row = MutualFundOrderUnitDao.findRowByPk(2050L);
            MutualFundOrderUnitParams l_params = new MutualFundOrderUnitParams(l_row);
            
            log.debug("l_params.getCalcConstantValue==================" + l_params.getCalcConstantValue());
            l_params = l_interceptor.mutate(null,null,l_mfOrderUnitParams);
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_params);
            
            log.debug("l_params.getCalcConstantValue==================" + l_params.getCalcConstantValue());
            assertEquals(WEB3StringTypeUtility.formatNumber(l_params.getCalcConstantValue()), "500");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testMutatecase0002()
    {
        final String STR_METHOD_NAME = "testMutatecase0002()";
        log.entering(STR_METHOD_NAME);
        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        l_subAccountParams.setSubAccountId(33381251220301L);
        
        MutualFundOrderUnitParams l_mfOrderUnitParams =
            TestDBUtility.getMutualFundOrderUnitRow();
        l_mfOrderUnitParams.setOrderUnitId(2050L);
        l_mfOrderUnitParams.setSubAccountId(33381251220301L);
        l_mfOrderUnitParams.setProductId(3304148080001L);
        l_mfOrderUnitParams.setBranchId(33381);
        l_mfOrderUnitParams.setAccountId(333812512246L);
        l_mfOrderUnitParams.setCalcConstantValue(100);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", "getNewNumber", new Class[]
                { String.class, String.class, ProductTypeEnum.class },  "123");

            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(
                WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_mfOrderUnitParams);
            
            WEB3MutualFundNewOrderConfirmInterceptor l_interceptor =
                new WEB3MutualFundNewOrderConfirmInterceptor();
            l_interceptor.setMutualFundType("3");//FOREIGN_MMF
            l_interceptor.setConstantValue(Double.NaN);
            l_interceptor.setDeliveryDate(new Timestamp(WEB3DateUtility.getDate("20070213","yyyyMMdd").getTime()));
            
            MutualFundOrderUnitRow l_row = MutualFundOrderUnitDao.findRowByPk(2050L);
            MutualFundOrderUnitParams l_params = new MutualFundOrderUnitParams(l_row);
            
            log.debug("l_params.getCalcConstantValue==================" + l_params.getCalcConstantValue());
            l_params = l_interceptor.mutate(null,null,l_mfOrderUnitParams);
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_params);
            
            log.debug("l_params.getCalcConstantValue==================" + l_params.getCalcConstantValue());
            assertEquals(WEB3StringTypeUtility.formatNumber(l_params.getCalcConstantValue()), "0");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(STR_METHOD_NAME);
    }

}
@

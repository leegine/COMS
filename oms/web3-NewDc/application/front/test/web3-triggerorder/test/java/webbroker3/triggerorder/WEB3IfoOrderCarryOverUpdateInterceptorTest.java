head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.41.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoOrderCarryOverUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 
Author Name      : Daiwa Institute of Research
Revesion History : 
*/
package webbroker3.triggerorder;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoOrderCarryOverUpdateInterceptorTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3IfoOrderCarryOverUpdateInterceptorTest.class);

    public WEB3IfoOrderCarryOverUpdateInterceptorTest(String arg0)
    {
        super(arg0);
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

    /*
     * Test method for 'webbroker3.ifo.WEB3IfoOrderCarryOverUpdateInterceptor.mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, IfoOrderUnitParams)'
     */
    public void testMutate1()
    {
        final String STR_METHOD_NAME = "testMutate1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoOrderCarryOverUpdateInterceptor l_interceptor =
            new WEB3IfoOrderCarryOverUpdateInterceptor();
        WEB3GentradeCommission l_commRevMstId = new WEB3GentradeCommission();
        l_commRevMstId.setOrderChannel("123");
        l_commRevMstId.setCommissionNo("123");
        l_commRevMstId.setCommissionRevNo("123");
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmounCalcResult =
            new WEB3IfoEstimateDeliveryAmountCalcResult();
        l_estimateDeliveryAmounCalcResult.setCalcUnitPrice(100);
        l_estimateDeliveryAmounCalcResult.setEstimateDeliveryAmount(100);
        l_interceptor.commissionFee = l_commRevMstId;
        l_interceptor.estimatedDeliveryAmountCalculationResult = l_estimateDeliveryAmounCalcResult;
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getSubmitOrderRouteDiv", new Class[] {String.class, String.class},
            "0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
          "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
          "getNewNumber", new Class[]
        { String.class, String.class, ProductTypeEnum.class },
        "235678");

        IfoOrderUnitParams l_params = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderUnitParams l_inputParams = new IfoOrderUnitParams();
        l_inputParams.setFutureOptionDiv("2");
        l_inputParams.setMarketId(1002);
        
        l_params.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
        l_params.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
        l_params.setExpirationDateType("opdfp");
        IfoOrderUnitParams l_outputParams = new IfoOrderUnitParams();
        l_interceptor.setOrderUnitParams(l_params);
        
        l_interceptor.setSessionType("55");
        
        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;
        SubAccountParams l_subParams = TestDBUtility.getSubAccountRow();
        l_subParams.setAccountId(l_params.getAccountId());
        l_subParams.setSubAccountId(l_params.getSubAccountId());

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(1002);
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(101001010010L);
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006169090018L);
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006169090018L);
        
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionId(33);
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        l_branchParams.setBranchId(33381L);
        try
        {
            TestDBUtility.deleteAllAndCommit(InstitutionParams.TYPE);
            TestDBUtility.deleteAllAndCommit(SubAccountParams.TYPE);
            TestDBUtility.deleteAllAndCommit(MarketParams.TYPE);
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            TestDBUtility.deleteAllAndCommit(ProductParams.TYPE);
            TestDBUtility.deleteAllAndCommit(IfoProductParams.TYPE);
            TestDBUtility.deleteAllAndCommit(BranchParams.TYPE);
            
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            TestDBUtility.insertWithDelAndCommit(l_subParams);
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            TestDBUtility.insertWithDelAndCommit(l_productParams);
            TestDBUtility.insertWithDelAndCommit(l_ifoProductParams);
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            l_outputParams = l_interceptor.mutate(l_updateType, l_dealing, l_inputParams);

            assertEquals("0", l_outputParams.getSubmitOrderRouteDiv());
            assertEquals(null, l_outputParams.getConfirmedOrderRev());
            assertEquals(null, l_outputParams.getOrderRev());
            
            assertEquals(BooleanEnum.TRUE, l_outputParams.getEveningSessionCarryoverFlag());
            assertEquals("55", l_outputParams.getSessionType());
            assertNull(l_outputParams.getDayTradeType());
            
            assertEquals("1001","" + l_outputParams.getFirstOrderUnitId());
            assertEquals("0", "" + l_outputParams.getReserveOrderExistFlag());
            assertEquals("opdfp", "" + l_outputParams.getExpirationDateType());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMutate2()
    {
        final String STR_METHOD_NAME = "testMutate2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getSubmitOrderRouteDiv", new Class[] {String.class, String.class},
            "1");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                "getNewNumber", new Class[]
              { String.class, String.class, ProductTypeEnum.class },
              "235678");
        WEB3IfoOrderCarryOverUpdateInterceptor l_interceptor =
            new WEB3IfoOrderCarryOverUpdateInterceptor();
        WEB3GentradeCommission l_commRevMstId = new WEB3GentradeCommission();
        l_commRevMstId.setOrderChannel("123");
        l_commRevMstId.setCommissionNo("123");
        l_commRevMstId.setCommissionRevNo("123");
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmounCalcResult =
            new WEB3IfoEstimateDeliveryAmountCalcResult();
        l_estimateDeliveryAmounCalcResult.setCalcUnitPrice(100);
        l_estimateDeliveryAmounCalcResult.setEstimateDeliveryAmount(100);
        l_interceptor.commissionFee = l_commRevMstId;
        l_interceptor.estimatedDeliveryAmountCalculationResult = l_estimateDeliveryAmounCalcResult;
        IfoOrderUnitParams l_params = TestDBUtility.getIfoOrderUnitRow();
        l_params.setFirstOrderUnitId(0);
        IfoOrderUnitParams l_inputParams = new IfoOrderUnitParams();
        l_inputParams.setFutureOptionDiv("1");
        l_inputParams.setMarketId(1002);
        IfoOrderUnitParams l_outputParams = new IfoOrderUnitParams();
        l_interceptor.setOrderUnitParams(l_params);
        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;
        SubAccountParams l_subParams = TestDBUtility.getSubAccountRow();
        l_subParams.setAccountId(l_params.getAccountId());
        l_subParams.setSubAccountId(l_params.getSubAccountId());

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(1002);
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(101001010010L);
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006169090018L);
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006169090018L);
        try
        {
            TestDBUtility.insertWithDelAndCommit(l_subParams);
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            TestDBUtility.insertWithDelAndCommit(l_productParams);
            TestDBUtility.insertWithDelAndCommit(l_ifoProductParams);
            l_outputParams = l_interceptor.mutate(l_updateType, l_dealing, l_inputParams);

            assertEquals("1", l_outputParams.getSubmitOrderRouteDiv());
            assertEquals(null, l_outputParams.getConfirmedOrderRev());
            assertEquals(null, l_outputParams.getOrderRev());
            
            assertEquals("1001","" + l_outputParams.getFirstOrderUnitId());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testMutate3()
    {
        final String STR_METHOD_NAME = "testMutate3()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoOrderCarryOverUpdateInterceptor l_interceptor =
            new WEB3IfoOrderCarryOverUpdateInterceptor();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                "getNewNumber", new Class[]
              { String.class, String.class, ProductTypeEnum.class },
              "235678");
        WEB3GentradeCommission l_commRevMstId = new WEB3GentradeCommission();
        l_commRevMstId.setOrderChannel("123");
        l_commRevMstId.setCommissionNo("123");
        l_commRevMstId.setCommissionRevNo("123");
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmounCalcResult =
            new WEB3IfoEstimateDeliveryAmountCalcResult();
        l_estimateDeliveryAmounCalcResult.setCalcUnitPrice(100);
        l_estimateDeliveryAmounCalcResult.setEstimateDeliveryAmount(100);
        l_interceptor.commissionFee = l_commRevMstId;
        l_interceptor.estimatedDeliveryAmountCalculationResult = l_estimateDeliveryAmounCalcResult;
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getSubmitOrderRouteDiv", new Class[] {String.class, String.class},
            "0");
        IfoOrderUnitParams l_params = TestDBUtility.getIfoOrderUnitRow();
        l_params.setFirstOrderUnitId(2);
        IfoOrderUnitParams l_inputParams = new IfoOrderUnitParams();
        l_inputParams.setFutureOptionDiv("2");
        l_inputParams.setMarketId(1002);
        
        l_params.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
        l_params.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
        
        IfoOrderUnitParams l_outputParams = new IfoOrderUnitParams();
        l_interceptor.setOrderUnitParams(l_params);
        
        l_interceptor.setSessionType("55");
        
        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;
        SubAccountParams l_subParams = TestDBUtility.getSubAccountRow();
        l_subParams.setAccountId(l_params.getAccountId());
        l_subParams.setSubAccountId(l_params.getSubAccountId());

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(1002);
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(101001010010L);
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006169090018L);
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006169090018L);
        try
        {
            TestDBUtility.insertWithDelAndCommit(l_subParams);
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            TestDBUtility.insertWithDelAndCommit(l_productParams);
            TestDBUtility.insertWithDelAndCommit(l_ifoProductParams);
            l_outputParams = l_interceptor.mutate(l_updateType, l_dealing, l_inputParams);

            assertEquals("0", l_outputParams.getSubmitOrderRouteDiv());
            assertEquals(null, l_outputParams.getConfirmedOrderRev());
            assertEquals(null, l_outputParams.getOrderRev());
            
            assertEquals(BooleanEnum.TRUE, l_outputParams.getEveningSessionCarryoverFlag());
            assertEquals("55", l_outputParams.getSessionType());
            assertNull(l_outputParams.getDayTradeType());
            assertEquals("2","" + l_outputParams.getFirstOrderUnitId());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testMutate4()
    {
        final String STR_METHOD_NAME = "testMutate4()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoOrderCarryOverUpdateInterceptor l_interceptor =
            new WEB3IfoOrderCarryOverUpdateInterceptor();
        WEB3GentradeCommission l_commRevMstId = new WEB3GentradeCommission();
        l_commRevMstId.setOrderChannel("123");
        l_commRevMstId.setCommissionNo("123");
        l_commRevMstId.setCommissionRevNo("123");
        l_commRevMstId.setDayTradeType("1");
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmounCalcResult =
            new WEB3IfoEstimateDeliveryAmountCalcResult();
        l_estimateDeliveryAmounCalcResult.setCalcUnitPrice(100);
        l_estimateDeliveryAmounCalcResult.setEstimateDeliveryAmount(100);
        l_interceptor.commissionFee = l_commRevMstId;
        l_interceptor.estimatedDeliveryAmountCalculationResult = l_estimateDeliveryAmounCalcResult;
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getSubmitOrderRouteDiv", new Class[] {String.class, String.class},
            "0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                "getNewNumber", new Class[]
              { String.class, String.class, ProductTypeEnum.class },
              "235678");
        IfoOrderUnitParams l_params = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderUnitParams l_inputParams = new IfoOrderUnitParams();
        l_inputParams.setFutureOptionDiv("2");
        l_inputParams.setMarketId(1002);
        
        l_params.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
        l_params.setOrderCateg(OrderCategEnum.IDX_OPTIONS_CLOSE);
        
        IfoOrderUnitParams l_outputParams = new IfoOrderUnitParams();
        l_interceptor.setOrderUnitParams(l_params);
        
        l_interceptor.setSessionType("55");
        
        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;
        SubAccountParams l_subParams = TestDBUtility.getSubAccountRow();
        l_subParams.setAccountId(l_params.getAccountId());
        l_subParams.setSubAccountId(l_params.getSubAccountId());

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(1002);
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(101001010010L);
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006169090018L);
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006169090018L);
        try
        {
            TestDBUtility.insertWithDelAndCommit(l_subParams);
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            TestDBUtility.insertWithDelAndCommit(l_productParams);
            TestDBUtility.insertWithDelAndCommit(l_ifoProductParams);
            l_outputParams = l_interceptor.mutate(l_updateType, l_dealing, l_inputParams);

            assertEquals("0", l_outputParams.getSubmitOrderRouteDiv());
            assertEquals(null, l_outputParams.getConfirmedOrderRev());
            assertEquals(null, l_outputParams.getOrderRev());
            
            assertEquals(BooleanEnum.TRUE, l_outputParams.getEveningSessionCarryoverFlag());
            assertEquals("55", l_outputParams.getSessionType());
            assertEquals("1",l_outputParams.getDayTradeType());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testMutate5()
    {
        final String STR_METHOD_NAME = "testMutate5()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoOrderCarryOverUpdateInterceptor l_interceptor =
            new WEB3IfoOrderCarryOverUpdateInterceptor();
        WEB3GentradeCommission l_commRevMstId = new WEB3GentradeCommission();
        l_commRevMstId.setOrderChannel("123");
        l_commRevMstId.setCommissionNo("123");
        l_commRevMstId.setCommissionRevNo("123");
        l_commRevMstId.setDayTradeType("2");
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmounCalcResult =
            new WEB3IfoEstimateDeliveryAmountCalcResult();
        l_estimateDeliveryAmounCalcResult.setCalcUnitPrice(100);
        l_estimateDeliveryAmounCalcResult.setEstimateDeliveryAmount(100);
        l_interceptor.commissionFee = l_commRevMstId;
        l_interceptor.estimatedDeliveryAmountCalculationResult = l_estimateDeliveryAmounCalcResult;
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getSubmitOrderRouteDiv", new Class[] {String.class, String.class},
            "0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                "getNewNumber", new Class[]
              { String.class, String.class, ProductTypeEnum.class },
              "235678");
        IfoOrderUnitParams l_params = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderUnitParams l_inputParams = new IfoOrderUnitParams();
        l_inputParams.setFutureOptionDiv("2");
        l_inputParams.setMarketId(1002);
        
        l_params.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
        l_params.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
        
        IfoOrderUnitParams l_outputParams = new IfoOrderUnitParams();
        l_interceptor.setOrderUnitParams(l_params);
        
        l_interceptor.setSessionType("55");
        
        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;
        SubAccountParams l_subParams = TestDBUtility.getSubAccountRow();
        l_subParams.setAccountId(l_params.getAccountId());
        l_subParams.setSubAccountId(l_params.getSubAccountId());

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(1002);
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(101001010010L);
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006169090018L);
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006169090018L);
        try
        {
            TestDBUtility.insertWithDelAndCommit(l_subParams);
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            TestDBUtility.insertWithDelAndCommit(l_productParams);
            TestDBUtility.insertWithDelAndCommit(l_ifoProductParams);
            l_outputParams = l_interceptor.mutate(l_updateType, l_dealing, l_inputParams);

            assertEquals("0", l_outputParams.getSubmitOrderRouteDiv());
            assertEquals(null, l_outputParams.getConfirmedOrderRev());
            assertEquals(null, l_outputParams.getOrderRev());
            
            assertEquals(BooleanEnum.TRUE, l_outputParams.getEveningSessionCarryoverFlag());
            assertEquals("55", l_outputParams.getSessionType());
            assertEquals("2",l_outputParams.getDayTradeType());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@

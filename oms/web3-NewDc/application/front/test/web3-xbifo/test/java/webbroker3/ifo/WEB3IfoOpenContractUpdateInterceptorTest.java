head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.59.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoOpenContractUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoOpenContractUpdateInterceptorTest extends TestBaseForMock
{
    WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoOpenContractUpdateInterceptorTest.class);
    WEB3IfoOpenContractUpdateInterceptor l_interceptor = null;
    IfoProductParams l_params = null;
    public WEB3IfoOpenContractUpdateInterceptorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        l_params = new IfoProductParams();
        l_params.setProductId(3304148080000L);
        l_params.setInstitutionCode("0D");
        l_params.setUnderlyingProductCode("61");
        l_params.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_params.setStrikePrice(123d);
        l_params.setMonthOfDelivery("12");
        Timestamp l_time = GtlUtils.getSystemTimestamp();
        l_params.setCreatedTimestamp(l_time);
        l_params.setLastUpdatedTimestamp(l_time);
        Calendar ca1 =  Calendar.getInstance();
        ca1.set(2007,6-1,19);
        
        Date date1 = ca1.getTime();
        WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }


    public void testMutate_T01()
    {
        String STR_METHOD_NAME = "testMutate_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            
            Timestamp l_time = GtlUtils.getSystemTimestamp();
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_params);
            WEB3IfoProductImpl l_ifoProductImpl = 
                new WEB3IfoProductImpl(l_params);
            
            WEB3IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec =
                WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                    "123",null,
                    true,"123",
                    l_ifoProductImpl,
                    456d,456d,
                    IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED,
                    l_time,"123",
                    456d,456d,
                    IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED,
                    "789",null,false);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl", 
                    "getSubmitOrderRouteDiv",
                    new Class[] {String.class, String.class},
                    "321");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty",
                    new Class[] {String.class},
                    "456");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                    "getNewNumber", new Class[]{ String.class, String.class, ProductTypeEnum.class },
                    "2345678");
            
            MainAccountParams l_accountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_accountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_accountParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(123l);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getMainAccount",
                    new Class[] {long.class},
                    l_mainAccount);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getBranch",
                    new Class[] {long.class},
                    l_branch);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getInstitution",
                    new Class[] {long.class},
                    l_institution);
            
            TestDBUtility.insertWithDel(l_accountParams);
            l_interceptor = new WEB3IfoOpenContractUpdateInterceptor(l_ifoOpenContractOrderSpec);
            
            WEB3IfoEstimateDeliveryAmountCalcResult estimateDeliveryAmounCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            estimateDeliveryAmounCalcResult.setCalcUnitPrice(213d);
            estimateDeliveryAmounCalcResult.setEstimateDeliveryAmount(546d);
            WEB3GentradeCommission commRevMstId = new WEB3GentradeCommission();
            commRevMstId.setCommissionNo("0");
            commRevMstId.setCommissionRevNo("1");
            l_interceptor.commRevMstId = commRevMstId;
            l_interceptor.estimateDeliveryAmounCalcResult = estimateDeliveryAmounCalcResult;
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setMarketId(123l);
            l_ifoOrderUnitParams.setConfirmedOrderRev("00");
            l_ifoOrderUnitParams.setOrderRev("00");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnitRow l_ifoOrderUnitRow =
                IfoOrderUnitDao.findRowByPk(l_ifoOrderUnitParams.getOrderUnitId());
            IfoOrderUnitParams l_ifoOrderUnitParam = new IfoOrderUnitParams(l_ifoOrderUnitRow);
            OrderManagerPersistenceType l_orderManagerPersistenceType = null;
            OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
            
            
            IfoOrderUnitParams l_params = l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_ifoOrderUnitParam);
            assertEquals("321", l_params.getSubmitOrderRouteDiv());
            assertEquals("00", l_params.getConfirmedOrderRev());
            assertEquals("00", l_params.getOrderRev());
            //SonarTraderCode インタセプタ.扱者コード（SONAR）=nullの場合 顧客.扱者コード（SONAR）
            assertEquals("11124", l_params.getSonarTraderCode());
            assertEquals(l_params.getOrderChanel(), "456");
            assertEquals(l_params.getOrderRootDiv(), "456");
            log.info(STR_METHOD_NAME + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testMutate_T02()
    {
        String STR_METHOD_NAME = "testMutate_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            
            Timestamp l_time = GtlUtils.getSystemTimestamp();
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_params);
            WEB3IfoProductImpl l_ifoProductImpl = 
                new WEB3IfoProductImpl(l_params);
            
            WEB3IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec =
                WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                    "123",null,
                    true,"123",
                    l_ifoProductImpl,
                    456d,456d,
                    IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED,
                    l_time,"123",
                    456d,456d,
                    IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED,
                    "789",null,false);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl", 
                    "getSubmitOrderRouteDiv",
                    new Class[] {String.class, String.class},
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, "test error 8005"));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty",
                    new Class[] {String.class},
                    "456");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", "getNewNumber", new Class[]
                    { String.class, String.class, ProductTypeEnum.class },
                    "2345678");
            
            MainAccountParams l_accountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_accountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_accountParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(123l);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getMainAccount",
                    new Class[] {long.class},
                    l_mainAccount);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getBranch",
                    new Class[] {long.class},
                    l_branch);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getInstitution",
                    new Class[] {long.class},
                    l_institution);
            
            TestDBUtility.insertWithDel(l_accountParams);
            l_interceptor = new WEB3IfoOpenContractUpdateInterceptor(l_ifoOpenContractOrderSpec);
            
            WEB3IfoEstimateDeliveryAmountCalcResult estimateDeliveryAmounCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            estimateDeliveryAmounCalcResult.setCalcUnitPrice(213d);
            estimateDeliveryAmounCalcResult.setEstimateDeliveryAmount(546d);
            WEB3GentradeCommission commRevMstId = new WEB3GentradeCommission();
            commRevMstId.setCommissionNo("0");
            commRevMstId.setCommissionRevNo("1");
            l_interceptor.commRevMstId = commRevMstId;
            l_interceptor.estimateDeliveryAmounCalcResult = estimateDeliveryAmounCalcResult;
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setMarketId(123l);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnitRow l_ifoOrderUnitRow =
                IfoOrderUnitDao.findRowByPk(l_ifoOrderUnitParams.getOrderUnitId());
            IfoOrderUnitParams l_ifoOrderUnitParam = new IfoOrderUnitParams(l_ifoOrderUnitRow);
            OrderManagerPersistenceType l_orderManagerPersistenceType = null;
            OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
            IfoOrderUnitParams l_params = l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_ifoOrderUnitParam);
            assertEquals("321", l_params.getSubmitOrderRouteDiv());
            assertEquals("00", l_params.getConfirmedOrderRev());
            assertEquals("00", l_params.getOrderRev());
            fail();
        }
        catch(WEB3BaseRuntimeException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_exc);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testMutate_C0001()
    {
        String STR_METHOD_NAME = "testMutate_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            
            Timestamp l_time = GtlUtils.getSystemTimestamp();
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_params);
            WEB3IfoProductImpl l_ifoProductImpl = 
                new WEB3IfoProductImpl(l_params);
            
            WEB3IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec =
                WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                    "123",null,
                    true,"123",
                    l_ifoProductImpl,
                    456d,456d,
                    IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED,
                    l_time,"123",
                    456d,456d,
                    IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED,
                    "2",null,false);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl", 
                    "getSubmitOrderRouteDiv",
                    new Class[] {String.class, String.class},
                    "321");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty",
                    new Class[] {String.class},
                    "456");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                    "getNewNumber", new Class[]{ String.class, String.class, ProductTypeEnum.class },
                    "2345678");
            
            MainAccountParams l_accountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_accountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_accountParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(123l);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getMainAccount",
                    new Class[] {long.class},
                    l_mainAccount);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getBranch",
                    new Class[] {long.class},
                    l_branch);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getInstitution",
                    new Class[] {long.class},
                    l_institution);
            
            TestDBUtility.insertWithDel(l_accountParams);
            l_interceptor = new WEB3IfoOpenContractUpdateInterceptor(l_ifoOpenContractOrderSpec);
            
            WEB3IfoEstimateDeliveryAmountCalcResult estimateDeliveryAmounCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            estimateDeliveryAmounCalcResult.setCalcUnitPrice(213d);
            estimateDeliveryAmounCalcResult.setEstimateDeliveryAmount(546d);
            WEB3GentradeCommission commRevMstId = new WEB3GentradeCommission();
            commRevMstId.setCommissionNo("0");
            commRevMstId.setCommissionRevNo("1");
            l_interceptor.commRevMstId = commRevMstId;
            l_interceptor.estimateDeliveryAmounCalcResult = estimateDeliveryAmounCalcResult;
            l_interceptor.setSonarTraderCode("123");
            l_interceptor.setOrderChanel("111");
            l_interceptor.setOrderRootDiv("222");
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setMarketId(123l);
            l_ifoOrderUnitParams.setConfirmedOrderRev("00");
            l_ifoOrderUnitParams.setOrderRev("00");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnitRow l_ifoOrderUnitRow =
                IfoOrderUnitDao.findRowByPk(l_ifoOrderUnitParams.getOrderUnitId());
            IfoOrderUnitParams l_ifoOrderUnitParam = new IfoOrderUnitParams(l_ifoOrderUnitRow);
            OrderManagerPersistenceType l_orderManagerPersistenceType = null;
            OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
            IfoOrderUnitParams l_params = l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_ifoOrderUnitParam);
            assertEquals("321", l_params.getSubmitOrderRouteDiv());
            assertEquals("00", l_params.getConfirmedOrderRev());
            assertEquals("00", l_params.getOrderRev());
            assertEquals("00", l_params.getOrderRev());
            assertEquals("0", l_params.getReserveOrderExistFlag());
            assertEquals("2", l_params.getExpirationDateType());
            
            //インタセプタ.扱者コード（SONAR）!=nullの場合 インタセプタ.扱者コード（SONAR）
            assertEquals("123", l_params.getSonarTraderCode());
            assertEquals(l_params.getOrderChanel(), "111");
            assertEquals(l_params.getOrderRootDiv(), "222");
            
            log.info(STR_METHOD_NAME + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@

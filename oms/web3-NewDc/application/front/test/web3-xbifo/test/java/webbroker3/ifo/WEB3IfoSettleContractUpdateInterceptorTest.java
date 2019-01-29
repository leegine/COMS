head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.00.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoSettleContractUpdateInterceptorTest.java;


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

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoSettleContractUpdateInterceptorTest extends TestBaseForMock
{
    WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoOpenContractChangeUpdateInterceptorTest.class);
    WEB3IfoSettleContractUpdateInterceptor l_interceptor = null;
    public WEB3IfoSettleContractUpdateInterceptorTest(String arg0)
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
//
//    public void testMutate_T01()
//    {
//        String STR_METHOD_NAME = "testMutate_T01()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();
//
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setStartTime("000000");
//            l_tradingTimeParams.setEndTime("235959");
//            l_tradingTimeParams.setSubmitMarketTrigger("0");
//            l_tradingTimeParams.setEnableOrder("0");
//            l_tradingTimeParams.setBizdateCalcParameter("1");
//            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//            
//            Timestamp l_time = GtlUtils.getSystemTimestamp();
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            TestDBUtility.insertWithDel(l_productParams);
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            l_ifoProductParams.setProductId(3304148080000L);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            
//            WEB3IfoSettleContractOrderSpec l_ifoSettleContractOrderSpec =
//                WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
//                    "123",null,
//                    456d,IfoOrderExecutionConditionType.AT_MARKET_CLOSE,
//                    l_time,null,"123",
//                    456d,456d,
//                    IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED,
//                    "789",null,false);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl", 
//                    "getSubmitOrderRouteDiv",
//                    new Class[] {String.class, String.class},
//                    "321");
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
//                    "getSessionProperty",
//                    new Class[] {String.class},
//                    "456");
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", "getNewNumber", new Class[]
//                    { String.class, String.class, ProductTypeEnum.class },
//                    "2345678");
//            
//            MainAccountParams l_accountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(l_accountParams);
//            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_accountParams);
//            
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setMarketId(123l);
//            TestDBUtility.insertWithDel(l_marketParams);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
//                    "getMainAccount",
//                    new Class[] {long.class},
//                    l_mainAccount);
//            
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchParams);
//            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
//                    "getBranch",
//                    new Class[] {long.class},
//                    l_branch);
//            
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
//                    "getInstitution",
//                    new Class[] {long.class},
//                    l_institution);
//            
//            TestDBUtility.insertWithDel(l_accountParams);
//            l_interceptor = new WEB3IfoSettleContractUpdateInterceptor(l_ifoSettleContractOrderSpec);
//            
//            WEB3IfoEstimateDeliveryAmountCalcResult estimateDeliveryAmounCalcResult =
//                new WEB3IfoEstimateDeliveryAmountCalcResult();
//            estimateDeliveryAmounCalcResult.setCalcUnitPrice(213d);
//            estimateDeliveryAmounCalcResult.setEstimateDeliveryAmount(546d);
//            WEB3GentradeCommission commRevMstId = new WEB3GentradeCommission();
//            commRevMstId.setCommissionNo("0");
//            commRevMstId.setCommissionRevNo("1");
//            l_interceptor.commRevMstId = commRevMstId;
//            l_interceptor.estimateDeliveryAmounCalcResult = estimateDeliveryAmounCalcResult;
//            
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setProductId(3304148080000L);
//            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
//            l_ifoOrderUnitParams.setMarketId(123l);
//            l_ifoOrderUnitParams.setConfirmedOrderRev("00");
//            l_ifoOrderUnitParams.setOrderRev("00");
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            IfoOrderUnitRow l_ifoOrderUnitRow =
//                IfoOrderUnitDao.findRowByPk(l_ifoOrderUnitParams.getOrderUnitId());
//            IfoOrderUnitParams l_ifoOrderUnitParam = new IfoOrderUnitParams(l_ifoOrderUnitRow);
//            OrderManagerPersistenceType l_orderManagerPersistenceType = null;
//            OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
//            IfoOrderUnitParams l_params = l_interceptor.mutate(
//                l_orderManagerPersistenceType,
//                l_orderManagerPersistenceContext,
//                l_ifoOrderUnitParam);
//            assertEquals("321", l_params.getSubmitOrderRouteDiv());
//            assertEquals("00", l_params.getConfirmedOrderRev());
//            assertEquals("00", l_params.getOrderRev());
//            log.info(STR_METHOD_NAME + "------------------>ok");
//        }
//        catch(Exception l_exc)
//        {
//            log.exiting(STR_METHOD_NAME);
//            l_exc.printStackTrace();
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//
//    public void testMutate_T02()
//    {
//        String STR_METHOD_NAME = "testMutate_T02()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();
//
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setStartTime("000000");
//            l_tradingTimeParams.setEndTime("235959");
//            l_tradingTimeParams.setSubmitMarketTrigger("0");
//            l_tradingTimeParams.setEnableOrder("0");
//            l_tradingTimeParams.setBizdateCalcParameter("1");
//            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//            
//            Timestamp l_time = GtlUtils.getSystemTimestamp();
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            TestDBUtility.insertWithDel(l_productParams);
//            
//            WEB3IfoSettleContractOrderSpec l_ifoSettleContractOrderSpec =
//                WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
//                    "123",null,
//                    456d,IfoOrderExecutionConditionType.AT_MARKET_CLOSE,
//                    l_time,null,"123",
//                    456d,456d,
//                    IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED,
//                    "789",null,false);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl", 
//                    "getSubmitOrderRouteDiv",
//                    new Class[] {String.class, String.class},
//                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, "test error 8005"));
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
//                    "getSessionProperty",
//                    new Class[] {String.class},
//                    "456");
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", "getNewNumber", new Class[]
//                    { String.class, String.class, ProductTypeEnum.class },
//                    "2345678");
//            
//            MainAccountParams l_accountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(l_accountParams);
//            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_accountParams);
//            
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setMarketId(123l);
//            TestDBUtility.insertWithDel(l_marketParams);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
//                    "getMainAccount",
//                    new Class[] {long.class},
//                    l_mainAccount);
//            
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchParams);
//            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
//                    "getBranch",
//                    new Class[] {long.class},
//                    l_branch);
//            
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
//                    "getInstitution",
//                    new Class[] {long.class},
//                    l_institution);
//            
//            TestDBUtility.insertWithDel(l_accountParams);
//            l_interceptor = new WEB3IfoSettleContractUpdateInterceptor(l_ifoSettleContractOrderSpec);
//            
//            WEB3IfoEstimateDeliveryAmountCalcResult estimateDeliveryAmounCalcResult =
//                new WEB3IfoEstimateDeliveryAmountCalcResult();
//            estimateDeliveryAmounCalcResult.setCalcUnitPrice(213d);
//            estimateDeliveryAmounCalcResult.setEstimateDeliveryAmount(546d);
//            WEB3GentradeCommission commRevMstId = new WEB3GentradeCommission();
//            commRevMstId.setCommissionNo("0");
//            commRevMstId.setCommissionRevNo("1");
//            l_interceptor.commRevMstId = commRevMstId;
//            l_interceptor.estimateDeliveryAmounCalcResult = estimateDeliveryAmounCalcResult;
//            
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setProductId(3304148080000L);
//            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
//            l_ifoOrderUnitParams.setMarketId(123l);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            IfoOrderUnitRow l_ifoOrderUnitRow =
//                IfoOrderUnitDao.findRowByPk(l_ifoOrderUnitParams.getOrderUnitId());
//            IfoOrderUnitParams l_ifoOrderUnitParam = new IfoOrderUnitParams(l_ifoOrderUnitRow);
//            l_ifoOrderUnitParam.setConfirmedOrderRev("00");
//            l_ifoOrderUnitParam.setOrderRev("00");
//            OrderManagerPersistenceType l_orderManagerPersistenceType = null;
//            OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
//            IfoOrderUnitParams l_params = l_interceptor.mutate(
//                l_orderManagerPersistenceType,
//                l_orderManagerPersistenceContext,
//                l_ifoOrderUnitParam);
//            assertEquals("321", l_params.getSubmitOrderRouteDiv());
//            assertEquals("00", l_params.getConfirmedOrderRev());
//            assertEquals("00", l_params.getOrderRev());
//            log.info(STR_METHOD_NAME + "------------------>ok");
//        }
//        catch(WEB3BaseRuntimeException l_exc)
//        {
//            log.exiting(STR_METHOD_NAME);
//            log.error(STR_METHOD_NAME, l_exc);
//            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_exc.getErrorInfo());
//            log.info(STR_METHOD_NAME + "------------------>ok");
//        }
//        catch(Exception l_exc)
//        {
//            log.exiting(STR_METHOD_NAME);
//            l_exc.printStackTrace();
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /**
//     * ó[èÍëOåJâzëŒè€ÉtÉâÉO = true
//     *
//     */
//    public void test_mutate_0001()
//    {
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//        final String STR_METHOD_NAME = "test_mutate_0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        String l_strInstitutionCode = "00";
//        Trader l_trader = null;
//        
//        double l_dblLimitPrice = 0;
//        IfoOrderExecutionConditionType l_executionConditionType = null;
//        Date l_datExpirationDate = new Date();
//        String l_strOrderCond = "";
//        double l_dblStopOrderBasePrice = 0;
//        double l_dblWLimitPriceChange = 0;
//        IfoOrderExecutionConditionType l_wLimitExecCondType = null;
//        String l_strExpirationDateType = "00";
//        Long l_lngFirstOrderUnitId = new Long(0);
//        boolean l_blnEveningSessionCarryoverFlag = false;
//        
//        
//        SettleContractEntry[] l_settleContractOrderEntry = null;
//        
//        WEB3IfoSettleContractOrderSpec l_ifoSettleContractOrderSpec = null;
//        WEB3IfoProductImpl l_product = null;
//        
//        try
//        {            
//            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();
//
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setStartTime("000000");
//            l_tradingTimeParams.setEndTime("235959");
//            l_tradingTimeParams.setSubmitMarketTrigger("0");
//            l_tradingTimeParams.setEnableOrder("0");
//            l_tradingTimeParams.setBizdateCalcParameter("1");
//            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//            
//            ProductParams l_productParams = this.getProductRow();
//            IfoProductParams l_ifoProductParams = this.getIfoProductRow();
//            MarketParams l_marketParams = this.getMarketRow();
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(101001010010L);
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            TestDBUtility.deleteAll(IfoProductRow.TYPE);
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            try
//            {
//                l_product = new WEB3IfoProductImpl(1006169090018L);
//            }
//            catch (DataFindException e)
//            {
//                log.error("", e);
//                log.exiting(TEST_END + STR_METHOD_NAME);
//                fail();
//            }
//            catch (DataQueryException e)
//            {
//                log.error("", e);
//                log.exiting(TEST_END + STR_METHOD_NAME);
//                fail();
//            }
//            catch (DataNetworkException e)
//            {
//                log.error("", e);
//                log.exiting(TEST_END + STR_METHOD_NAME);
//                fail();
//            }
//
//        }
//        catch (Exception l_ex)
//        {
//            log.error("", l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        
//        try
//        {
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "getSessionProperty",
//                    new Class[] {String.class},
//                    "aa");
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
//                    "getSubmitOrderRouteDiv",
//                    new Class[] {String.class, String.class},
//                    "BB");
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
//                    "getNewNumber", 
//                    new Class[]{ String.class, String.class, ProductTypeEnum.class},
//                    "bbbb");
//                    
//            l_ifoSettleContractOrderSpec = WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
//                    l_strInstitutionCode,
//                    l_trader,
//                    l_dblLimitPrice,
//                    l_executionConditionType,
//                    l_datExpirationDate,
//                    l_settleContractOrderEntry,
//                    l_strOrderCond,
//                    l_dblStopOrderBasePrice,
//                    l_dblWLimitPriceChange,
//                    l_wLimitExecCondType,
//                    l_strExpirationDateType,
//                    l_lngFirstOrderUnitId,
//                    l_blnEveningSessionCarryoverFlag);
//            //ó[èÍëOåJâzëŒè€ÉtÉâÉO
//            l_ifoSettleContractOrderSpec.setEveningSessionCarryoverFlag(true);
//            l_ifoSettleContractOrderSpec.setFirstOrderUnitId(new Long(55));
//        }
//        catch (WEB3BaseException e)
//        {
//            log.error("", e);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        
//        OrderManagerPersistenceType l_orderManagerPersistenceType = null;
//        OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
//        IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
//        l_ifoOrderUnitParams.product_id = 1006169090018L;
//        l_ifoOrderUnitParams.account_id = 101001010010L;
//        l_ifoOrderUnitParams.setMarketId(1002);
//        
//        WEB3GentradeCommission l_commision = new WEB3GentradeCommission();
//        l_commision.setDayTradeType("012");
//        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmounCalcResult =
//            new WEB3IfoEstimateDeliveryAmountCalcResult();
//        l_estimateDeliveryAmounCalcResult.setCalcUnitPrice(11);
//        
//        WEB3IfoSettleContractUpdateInterceptor l_interceptor =
//            new WEB3IfoSettleContractUpdateInterceptor(l_ifoSettleContractOrderSpec);
//        l_interceptor.setSessionType("123");
//        
//        l_interceptor.setCommision(l_commision);
////        l_interceptor.setOrderRequestNumber("1243");
//        l_interceptor.setEstimateDeliveryAmountCalcResult(l_estimateDeliveryAmounCalcResult);
//        
//        l_ifoOrderUnitParams = l_interceptor.mutate(
//                l_orderManagerPersistenceType,
//                l_orderManagerPersistenceContext,
//                l_ifoOrderUnitParams);
//        
//        assertEquals("123",l_ifoOrderUnitParams.getSessionType());
//        assertEquals("012",l_ifoOrderUnitParams.getDayTradeType());
//        assertEquals("1","" + l_ifoOrderUnitParams.getEveningSessionCarryoverFlag().intValue());
//        assertEquals("123",l_interceptor.getSessionType());
//        
//        assertEquals("55","" + l_ifoOrderUnitParams.getFirstOrderUnitId());
//    }
//    
//    /**
//     * ó[èÍëOåJâzëŒè€ÉtÉâÉO = false
//     *
//     */
//    public void test_mutate_0002()
//    {
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//        final String STR_METHOD_NAME = "test_mutate_0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        String l_strInstitutionCode = "00";
//        Trader l_trader = null;
//        
//        double l_dblLimitPrice = 0;
//        IfoOrderExecutionConditionType l_executionConditionType = null;
//        Date l_datExpirationDate = new Date();
//        String l_strOrderCond = "";
//        double l_dblStopOrderBasePrice = 0;
//        double l_dblWLimitPriceChange = 0;
//        IfoOrderExecutionConditionType l_wLimitExecCondType = null;
//        String l_strExpirationDateType = "00";
//        Long l_lngFirstOrderUnitId = new Long(0);
//        boolean l_blnEveningSessionCarryoverFlag = false;
//        
//        
//        SettleContractEntry[] l_settleContractOrderEntry = null;
//        
//        WEB3IfoSettleContractOrderSpec l_ifoSettleContractOrderSpec = null;
//        WEB3IfoProductImpl l_product = null;
//        
//        try
//        {            
//            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();
//
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setStartTime("000000");
//            l_tradingTimeParams.setEndTime("235959");
//            l_tradingTimeParams.setSubmitMarketTrigger("0");
//            l_tradingTimeParams.setEnableOrder("0");
//            l_tradingTimeParams.setBizdateCalcParameter("1");
//            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//            
//            ProductParams l_productParams = this.getProductRow();
//            IfoProductParams l_ifoProductParams = this.getIfoProductRow();
//            MarketParams l_marketParams = this.getMarketRow();
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(101001010010L);
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            TestDBUtility.deleteAll(IfoProductRow.TYPE);
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            try
//            {
//                l_product = new WEB3IfoProductImpl(1006169090018L);
//            }
//            catch (DataFindException e)
//            {
//                log.error("", e);
//                log.exiting(TEST_END + STR_METHOD_NAME);
//                fail();
//            }
//            catch (DataQueryException e)
//            {
//                log.error("", e);
//                log.exiting(TEST_END + STR_METHOD_NAME);
//                fail();
//            }
//            catch (DataNetworkException e)
//            {
//                log.error("", e);
//                log.exiting(TEST_END + STR_METHOD_NAME);
//                fail();
//            }
//
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.error("", l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        
//        try
//        {
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "getSessionProperty",
//                    new Class[] {String.class},
//                    "aa");
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
//                    "getSubmitOrderRouteDiv",
//                    new Class[] {String.class, String.class},
//                    "BB");
//                    
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
//                    "getNewNumber", 
//                    new Class[]{ String.class, String.class, ProductTypeEnum.class},
//                    "bbbb");
//            
//            l_ifoSettleContractOrderSpec = WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
//                    l_strInstitutionCode,
//                    l_trader,
//                    l_dblLimitPrice,
//                    l_executionConditionType,
//                    l_datExpirationDate,
//                    l_settleContractOrderEntry,
//                    l_strOrderCond,
//                    l_dblStopOrderBasePrice,
//                    l_dblWLimitPriceChange,
//                    l_wLimitExecCondType,
//                    l_strExpirationDateType,
//                    l_lngFirstOrderUnitId,
//                    l_blnEveningSessionCarryoverFlag);
//            //ó[èÍëOåJâzëŒè€ÉtÉâÉO
//            l_ifoSettleContractOrderSpec.setEveningSessionCarryoverFlag(false);
//        }
//        catch (WEB3BaseException e)
//        {
//            log.error("", e);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        
//        OrderManagerPersistenceType l_orderManagerPersistenceType = null;
//        OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
//        IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
//        l_ifoOrderUnitParams.product_id = 1006169090018L;
//        l_ifoOrderUnitParams.account_id = 101001010010L;
//        l_ifoOrderUnitParams.setMarketId(1002);
//        
//        WEB3GentradeCommission l_commision = new WEB3GentradeCommission();
//        l_commision.setDayTradeType("012");
//        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmounCalcResult =
//            new WEB3IfoEstimateDeliveryAmountCalcResult();
//        l_estimateDeliveryAmounCalcResult.setCalcUnitPrice(11);
//        
//        WEB3IfoSettleContractUpdateInterceptor l_interceptor =
//            new WEB3IfoSettleContractUpdateInterceptor(l_ifoSettleContractOrderSpec);
//        l_interceptor.setSessionType("123");
//        
//        l_interceptor.setCommision(l_commision);
////        l_interceptor.setOrderRequestNumber("1243");
//        l_interceptor.setEstimateDeliveryAmountCalcResult(l_estimateDeliveryAmounCalcResult);
//        
//        l_ifoOrderUnitParams = l_interceptor.mutate(
//                l_orderManagerPersistenceType,
//                l_orderManagerPersistenceContext,
//                l_ifoOrderUnitParams);
//        
//        assertEquals("123",l_ifoOrderUnitParams.getSessionType());
//        assertEquals("012",l_ifoOrderUnitParams.getDayTradeType());
//        assertEquals("0","" + l_ifoOrderUnitParams.getEveningSessionCarryoverFlag().intValue());
//        assertEquals("123",l_interceptor.getSessionType());
//    }
//    
    /**
     * éQêîíçï∂íPà Params = NULL;
     */
    public void test_mutate_0003()
    {
        String STR_METHOD_NAME = "test_mutate_0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_interceptor = new WEB3IfoSettleContractUpdateInterceptor();
            OrderManagerPersistenceType l_orderManagerPersistenceType = null;
            OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
            IfoOrderUnitParams l_orderUnitParams = null;
            IfoOrderUnitParams l_params = l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_orderUnitParams);
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 
     *
     */
    public void test_mutate_0004()
    {
        String STR_METHOD_NAME = "test_mutate_0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getSessionProperty",
                  new Class[] {String.class},
                  "456");
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
          "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl", 
          "getSubmitOrderRouteDiv",
          new Class[] {String.class, String.class},
          "321");
          
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                  "getNewNumber", 
                  new Class[]{ String.class, String.class, ProductTypeEnum.class },
                  "321");
          
            ProductParams l_productParams = this.getProductRow();
            IfoProductParams l_ifoProductParams = this.getIfoProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(123L);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
//            [0D, 123, N1, 01, 0, 1]
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
          
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(1006169090018L);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setMarketId(123);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            l_interceptor = new WEB3IfoSettleContractUpdateInterceptor();
            OrderManagerPersistenceType l_orderManagerPersistenceType = null;
            OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
            WEB3GentradeCommission commRevMstId = new WEB3GentradeCommission();
            WEB3IfoEstimateDeliveryAmountCalcResult estimateDeliveryAmounCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            SettleContractEntry[] l_settleContractOrderEntries = new SettleContractEntry[1];
            l_settleContractOrderEntries[0] = new  SettleContractEntry(1005,500);
            Trader trader = null;
            IfoOrderExecutionConditionType execType = null;
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            WEB3IfoSettleContractOrderSpec futuresOptionSettleContractOrderSpec =
                WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                    "0D",
                    trader,
                    10.0,
                    execType,
                    l_datBizDate,
                    l_settleContractOrderEntries,
                    "2",
                    10.0,
                    10.0,
                    execType,
                    "1",
                    new Long(1001L),
                    true);
            estimateDeliveryAmounCalcResult.setCalcUnitPrice(100.0);
            estimateDeliveryAmounCalcResult.setEstimateDeliveryAmount(100.0);
            commRevMstId.setCommissionRevNo("123");
            commRevMstId.setCommissionRevNo("1234");
            WEB3IfoSettleContractUpdateInterceptor l_interceptor =
                new WEB3IfoSettleContractUpdateInterceptor(futuresOptionSettleContractOrderSpec);
            l_interceptor.commRevMstId = commRevMstId;
            l_interceptor.estimateDeliveryAmounCalcResult = estimateDeliveryAmounCalcResult;

            IfoOrderUnitParams l_params = l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_ifoOrderUnitParams);
            
            assertEquals(l_params.getExpirationDateType(), "1");
            assertEquals(l_params.getSonarTraderCode(), "11124");
            assertEquals(l_params.getOrderChanel(), "456");
            assertEquals(l_params.getOrderRootDiv(), "456");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
            
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    
    
    public void test_mutate_0005()
    {
        String STR_METHOD_NAME = "test_mutate_0005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getSessionProperty",
                  new Class[] {String.class},
                  "456");
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
          "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl", 
          "getSubmitOrderRouteDiv",
          new Class[] {String.class, String.class},
          "321");
          
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                  "getNewNumber", 
                  new Class[]{ String.class, String.class, ProductTypeEnum.class },
                  "321");
            ProductParams l_productParams = this.getProductRow();
            IfoProductParams l_ifoProductParams = this.getIfoProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(123L);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
//            [0D, 123, N1, 01, 0, 1]
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
          
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(1006169090018L);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setMarketId(123);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            l_interceptor = new WEB3IfoSettleContractUpdateInterceptor();
            OrderManagerPersistenceType l_orderManagerPersistenceType = null;
            OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
            WEB3GentradeCommission commRevMstId = new WEB3GentradeCommission();
            WEB3IfoEstimateDeliveryAmountCalcResult estimateDeliveryAmounCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            SettleContractEntry[] l_settleContractOrderEntries = new SettleContractEntry[1];
            l_settleContractOrderEntries[0] = new  SettleContractEntry(1005,500);
            Trader trader = null;
            IfoOrderExecutionConditionType execType = null;
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            WEB3IfoSettleContractOrderSpec futuresOptionSettleContractOrderSpec =
                WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                    "0D",
                    trader,
                    10.0,
                    execType,
                    l_datBizDate,
                    l_settleContractOrderEntries,
                    "2",
                    10.0,
                    10.0,
                    execType,
                    "1",
                    new Long(1001L),
                    true);
            estimateDeliveryAmounCalcResult.setCalcUnitPrice(100.0);
            estimateDeliveryAmounCalcResult.setEstimateDeliveryAmount(100.0);
            commRevMstId.setCommissionRevNo("123");
            commRevMstId.setCommissionRevNo("1234");
            WEB3IfoSettleContractUpdateInterceptor l_interceptor =
                new WEB3IfoSettleContractUpdateInterceptor(futuresOptionSettleContractOrderSpec);
            l_interceptor.commRevMstId = commRevMstId;
            l_interceptor.estimateDeliveryAmounCalcResult = estimateDeliveryAmounCalcResult;
            l_interceptor.setSonarTraderCode("123");
            l_interceptor.setOrderChanel("111");
            l_interceptor.setOrderRootDiv("222");
            IfoOrderUnitParams l_params = l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_ifoOrderUnitParams);
            
            assertEquals(l_params.getExpirationDateType(), "1");
            assertEquals(l_params.getSonarTraderCode(), "123");
            assertEquals(l_params.getOrderChanel(), "111");
            assertEquals(l_params.getOrderRootDiv(), "222");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
            
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    
    
    
    
    /**
     * ñ¡ïøRowÇçÏê¨.<BR>
     */
    public static ProductParams getProductRow()
    {
        ProductParams l_productParams = new ProductParams();

        l_productParams.setProductId(1006169090018L);
        l_productParams.setInstitutionCode("0D");
        l_productParams.setProductType(ProductTypeEnum.IFO);
        l_productParams.setStandardName("ÉVÉìÉZÉìÉeÉãÉX");
        l_productParams.setLotSize(0.000000D);
        l_productParams.setCalcSize(1.000000D);
        l_productParams.setEstimationPrice(0.000000D);
        l_productParams.setMarginRatio(0.000000D);
        l_productParams.setSecuritiesEstimationRatio(0.000000D);
        l_productParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_productParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_productParams;
    }
    
    /**
     * êÊï®OPñ¡ïøÉeÅ[ÉuÉã (ifo_product)
     */
    public static IfoProductParams getIfoProductRow()
    {
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setInstitutionCode("10");
        l_ifoProductParams.setProductCode("160030005");
        l_ifoProductParams.setUnderlyingProductCode("0005");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setStrikePrice(0);
        l_ifoProductParams.setMonthOfDelivery("200503");
        l_ifoProductParams.setExerciseDate(WEB3DateUtility.getDate("20070112","yyyyMMdd"));
        l_ifoProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        return l_ifoProductParams;
    }
    
    /**
     * ésèÍRowÇçÏê¨.<BR>
     */
    public static MarketParams getMarketRow()
    {
        MarketParams l_marketParams = new MarketParams();

        l_marketParams.setMarketId(1002);
        l_marketParams.setInstitutionCode("0D");
        l_marketParams.setMarketCode("SP");
        l_marketParams.setSonarMarketCode("G");
        l_marketParams.setMarketName("ÉVÉìÉKÉ|Å[Éã");
        l_marketParams.setOpenTime("09:00");
        l_marketParams.setCloseTime("15:00");
        l_marketParams.setSuspension("0");
        l_marketParams.setChangeableType("1");
        l_marketParams.setFeqOrderEmpDiv("7");
        l_marketParams.setFeqOrderRequestDiv("1");
        l_marketParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_marketParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_marketParams;
    }
}
@

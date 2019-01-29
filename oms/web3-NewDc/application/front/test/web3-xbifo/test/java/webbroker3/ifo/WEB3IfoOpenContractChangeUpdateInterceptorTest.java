head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.58.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoOpenContractChangeUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoOpenContractChangeUpdateInterceptorTest extends TestBaseForMock
{
    WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoOpenContractChangeUpdateInterceptorTest.class);
    WEB3IfoOpenContractChangeUpdateInterceptor l_interceptor = null;
    public WEB3IfoOpenContractChangeUpdateInterceptorTest(String arg0)
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

    public void testMutate_T01()
    {
        String STR_METHOD_NAME = "testMutate_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Timestamp l_time = GtlUtils.getSystemTimestamp();
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3IfoOpenContractChangeSpec l_openContractChangeSpec =
                WEB3IfoOpenContractChangeSpec.createIfoOpenContractChangeSpec(
                    123l,123l,
                    123d,123d,
                    IfoOrderExecutionConditionType.AT_MARKET_CLOSE,
                    l_time,l_time,
                    "456","456","456",
                    456d,456d,
                    IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED,
                    "789","789",false);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl", 
                    "getChangeSubmitOrderRouteDiv",
                    new Class[] {IfoOrderUnit.class},
                    "321");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl", 
                    "getChangeOrderRev",
                    new Class[] {IfoOrderUnit.class},
                    "654");
            l_interceptor = new WEB3IfoOpenContractChangeUpdateInterceptor(l_openContractChangeSpec);
            WEB3IfoEstimateDeliveryAmountCalcResult estimateDeliveryAmounCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            estimateDeliveryAmounCalcResult.setCalcUnitPrice(213d);
            estimateDeliveryAmounCalcResult.setEstimateDeliveryAmount(546d);
            l_interceptor.estimateDeliveryAmounCalcResult = estimateDeliveryAmounCalcResult;
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            OrderManagerPersistenceType l_orderManagerPersistenceType = null;
            OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
            IfoOrderUnitParams l_params = l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_ifoOrderUnitParams);
            assertEquals("321", l_params.getSubmitOrderRouteDiv());
            assertEquals("654", l_params.getOrderRev());
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
            WEB3IfoOpenContractChangeSpec l_openContractChangeSpec =
                WEB3IfoOpenContractChangeSpec.createIfoOpenContractChangeSpec(
                    123l,123l,
                    123d,123d,
                    IfoOrderExecutionConditionType.AT_MARKET_CLOSE,
                    l_time,l_time,
                    "456","456","456",
                    456d,456d,
                    IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED,
                    "789","789",false);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl", 
                    "getChangeSubmitOrderRouteDiv",
                    new Class[] {IfoOrderUnit.class},
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, "test error"));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl", 
                    "getChangeOrderRev",
                    new Class[] {IfoOrderUnit.class},
                    "654");
            l_interceptor = new WEB3IfoOpenContractChangeUpdateInterceptor(l_openContractChangeSpec);
            WEB3IfoEstimateDeliveryAmountCalcResult estimateDeliveryAmounCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            estimateDeliveryAmounCalcResult.setCalcUnitPrice(213d);
            estimateDeliveryAmounCalcResult.setEstimateDeliveryAmount(546d);
            l_interceptor.estimateDeliveryAmounCalcResult = estimateDeliveryAmounCalcResult;
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            OrderManagerPersistenceType l_orderManagerPersistenceType = null;
            OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
            IfoOrderUnitParams l_params = l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_ifoOrderUnitParams);
            assertEquals("321", l_params.getSubmitOrderRouteDiv());
            assertEquals("654", l_params.getOrderRev());
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
    
    public void testMutate_T03()
    {
        String STR_METHOD_NAME = "testMutate_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Timestamp l_time = GtlUtils.getSystemTimestamp();
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3IfoOpenContractChangeSpec l_openContractChangeSpec =
                WEB3IfoOpenContractChangeSpec.createIfoOpenContractChangeSpec(
                    123l,123l,
                    123d,123d,
                    IfoOrderExecutionConditionType.AT_MARKET_CLOSE,
                    l_time,l_time,
                    "456","456","456",
                    456d,456d,
                    IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED,
                    "789","789",false);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl", 
                    "getChangeSubmitOrderRouteDiv",
                    new Class[] {IfoOrderUnit.class},
                    "321");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl", 
                    "getChangeOrderRev",
                    new Class[] {IfoOrderUnit.class},
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, "test error"));
            l_interceptor = new WEB3IfoOpenContractChangeUpdateInterceptor(l_openContractChangeSpec);
            WEB3IfoEstimateDeliveryAmountCalcResult estimateDeliveryAmounCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            estimateDeliveryAmounCalcResult.setCalcUnitPrice(213d);
            estimateDeliveryAmounCalcResult.setEstimateDeliveryAmount(546d);
            l_interceptor.estimateDeliveryAmounCalcResult = estimateDeliveryAmounCalcResult;
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            OrderManagerPersistenceType l_orderManagerPersistenceType = null;
            OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
            IfoOrderUnitParams l_params = l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_ifoOrderUnitParams);
            assertEquals("321", l_params.getSubmitOrderRouteDiv());
            assertEquals("654", l_params.getOrderRev());
            fail();
        }
        catch(WEB3BaseRuntimeException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_exc);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_exc.getErrorInfo());
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

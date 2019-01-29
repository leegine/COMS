head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.39.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioSLRepayApplyServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import test.util.TestDBUtility;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.message.WEB3SLRepayApplyCompleteRequest;
import webbroker3.aio.message.WEB3SLRepayApplyCompleteResponse;
import webbroker3.aio.message.WEB3SLRepayApplyConfirmRequest;
import webbroker3.aio.message.WEB3SLRepayApplyConfirmResponse;
import webbroker3.aio.message.WEB3SLRepayApplyInputRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.StockSecuredLoanParams;
import webbroker3.gentrade.data.StockSecuredLoanRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AioSLRepayApplyServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLRepayApplyServiceImplTest.class);
    WEB3AioSLRepayApplyServiceImpl l_impl = new WEB3AioSLRepayApplyServiceImpl();

    public WEB3AioSLRepayApplyServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        this.deleteAllRows();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.deleteAllRows();
    }

    public void testValidateOrder_T01()
    {
        final String METHOD_NAME = "testValidateOrder_T01()";
        log.entering(METHOD_NAME);
        try
        {
            WEB3SLRepayApplyConfirmRequest l_request = new WEB3SLRepayApplyConfirmRequest();
            l_impl.validateOrder(null);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.exiting(METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(METHOD_NAME);
    }

    public void testValidateOrder_T02()
    {
        final String METHOD_NAME = "testValidateOrder_T02()";
        log.entering(METHOD_NAME);
        try
        {
            WEB3SLRepayApplyConfirmRequest l_request = new WEB3SLRepayApplyConfirmRequest();
            l_request.repayAmt = "abd";
            l_impl.validateOrder(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02909, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(METHOD_NAME);
    }
    
    public void testValidateOrder_T03()
    {
        final String METHOD_NAME = "testValidateOrder_T03()";
        log.entering(METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456789l));

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            
            WEB3SLRepayApplyConfirmRequest l_request = new WEB3SLRepayApplyConfirmRequest();
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = new Date(2007-1900, 9, 13);
            l_impl.validateOrder(l_request);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.exiting(METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(METHOD_NAME);
    }
 
    public void testValidateOrder_T04()
    {
        final String METHOD_NAME = "testValidateOrder_T04()";
        log.entering(METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456789l));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, "exception forMock"));

            //DBRows
            this.insertAllRows();

            WEB3SLRepayApplyConfirmRequest l_request = new WEB3SLRepayApplyConfirmRequest();
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = new Date(2007-1900, 9, 13);
            l_impl.validateOrder(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(METHOD_NAME);
    }
    
    public void testValidateOrder_T05()
    {
        final String METHOD_NAME = "testValidateOrder_T05()";
        log.entering(METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456789l));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            //DBRows
            this.insertAllRows();
            
            //StockSecuredLoanRow
            StockSecuredLoanParams l_stockSecuredLoanParams = this.insertStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAccountOpenStatus("1");
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            
            WEB3SLRepayApplyConfirmRequest l_request = new WEB3SLRepayApplyConfirmRequest();
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = new Date(2007-1900, 9, 13);
            l_impl.validateOrder(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02914, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(METHOD_NAME);
    }
    
    public void testValidateOrder_T06()
    {
        final String METHOD_NAME = "testValidateOrder_T06()";
        log.entering(METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456789l));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            //DBRows
            this.insertAllRows();

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2007-1900, 9, 23));

            WEB3SLRepayApplyConfirmRequest l_request = new WEB3SLRepayApplyConfirmRequest();
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = new Date(2007-1900, 9, 13);
            l_impl.validateOrder(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02915, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(METHOD_NAME);
    }

    public void testValidateOrder_T07()
    {
        final String METHOD_NAME = "testValidateOrder_T07()";
        log.entering(METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456789l));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456l));

            //DBRows
            this.insertAllRows();

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2007-1900, 9, 13));

            WEB3SLRepayApplyConfirmRequest l_request = new WEB3SLRepayApplyConfirmRequest();
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = new Date(2007-1900, 9, 23);
            l_impl.validateOrder(l_request);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.exiting(METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(METHOD_NAME);
    }
    
    
    public void testValidateOrder_T08()
    {
        final String METHOD_NAME = "testValidateOrder_T08()";
        log.entering(METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456789l));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456l));

            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class, boolean.class},
                l_result);

            //DBRows
            this.insertAllRows();

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2007-1900, 9, 13));

            WEB3SLRepayApplyConfirmRequest l_request = new WEB3SLRepayApplyConfirmRequest();
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = new Date(2007-1900, 9, 23);
            WEB3SLRepayApplyConfirmResponse l_response = l_impl.validateOrder(l_request);
            assertEquals(l_request.repayAmt, l_response.repayAmt);
            assertEquals(l_request.repayScheduledDate, l_response.repayScheduledDate);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(METHOD_NAME);
    }


    public void testValidateOrder_T09()
    {
        final String METHOD_NAME = "testValidateOrder_T09()";
        log.entering(METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456789l));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456l));

            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(false);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class, boolean.class},
                l_result);

            //DBRows
            this.insertAllRows();

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2007-1900, 9, 13));

            WEB3SLRepayApplyConfirmRequest l_request = new WEB3SLRepayApplyConfirmRequest();
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = new Date(2007-1900, 9, 23);
            WEB3SLRepayApplyConfirmResponse l_response = l_impl.validateOrder(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01306, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(METHOD_NAME);
    }

    public void testValidateOrder_T10()
    {
        final String METHOD_NAME = "testValidateOrder_T10()";
        log.entering(METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456789l));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456l));

            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class, boolean.class},
                l_result);

            //DBRows
            this.insertAllRows();

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2007-1900, 9, 22));

            WEB3SLRepayApplyConfirmRequest l_request = new WEB3SLRepayApplyConfirmRequest();
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = new Date(2007-1900, 9, 24);
            WEB3SLRepayApplyConfirmResponse l_response = l_impl.validateOrder(l_request);
            assertEquals(l_request.repayAmt, l_response.repayAmt);
            assertEquals(l_request.repayScheduledDate, l_response.repayScheduledDate);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(METHOD_NAME);
    }


    public void testValidateOrder_T11()
    {
        final String METHOD_NAME = "testValidateOrder_T11()";
        log.entering(METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456789l));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456l));

            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class, boolean.class},
                l_result);

            //DBRows
            this.insertAllRows();

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2007-1900, 9, 23));

            WEB3SLRepayApplyConfirmRequest l_request = new WEB3SLRepayApplyConfirmRequest();
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = new Date(2007-1900, 9, 24);
            WEB3SLRepayApplyConfirmResponse l_response = l_impl.validateOrder(l_request);
            assertEquals(l_request.repayAmt, l_response.repayAmt);
            assertEquals(l_request.repayScheduledDate, l_response.repayScheduledDate);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(METHOD_NAME);
    }
    
    
    public void testValidateOrder_T12()
    {
        final String METHOD_NAME = "testValidateOrder_T12()";
        log.entering(METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456789l));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456l));

            //DBRows
            this.insertAllRows();

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2007-1900, 9, 13));

            WEB3SLRepayApplyConfirmRequest l_request = new WEB3SLRepayApplyConfirmRequest();
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = new Date(2007-1900, 9, 23);
            
            //AioOrderUnitParams
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnit = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnit.setAccountId(123456789l);
            l_aioOrderUnit.setSubAccountId(33381251220301L);
            l_aioOrderUnit.setOrderType(OrderTypeEnum.TO_ORIX_CREDIT);
            l_aioOrderUnit.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_aioOrderUnit.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_aioOrderUnit.setDeliveryDate( l_request.repayScheduledDate);
            l_aioOrderUnit.setPaymentApplicationDiv(null);
            TestDBUtility.insertWithDel(l_aioOrderUnit);
            
            l_impl.validateOrder(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00757, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(METHOD_NAME);
    }


    public void testSubmitOrder_T01()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_impl.submitOrder(null);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrder_T02()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3SLRepayApplyCompleteRequest l_request = new WEB3SLRepayApplyCompleteRequest();
            l_request.repayAmt = "abc";
            l_impl.submitOrder(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02909, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrder_T03()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456789l));

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            
            WEB3SLRepayApplyCompleteRequest l_request = new WEB3SLRepayApplyCompleteRequest();
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = new Date(2007-1900, 9, 13);
            l_impl.submitOrder(l_request);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitOrder_T04()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456789l));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, "exception forMock"));

            //DBRows
            this.insertAllRows();
            
            WEB3SLRepayApplyCompleteRequest l_request = new WEB3SLRepayApplyCompleteRequest();
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = new Date(2007-1900, 9, 13);
            l_impl.submitOrder(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    

    public void testSubmitOrder_T05()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_T05()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456789l));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            //DBRows
            this.insertAllRows();
            
            //StockSecuredLoanRow
            StockSecuredLoanParams l_stockSecuredLoanParams = this.insertStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAccountOpenStatus("1");
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            
            WEB3SLRepayApplyCompleteRequest l_request = new WEB3SLRepayApplyCompleteRequest();
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = new Date(2007-1900, 9, 13);
            l_impl.submitOrder(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02914, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrder_T06()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_T06()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456789l));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            //DBRows
            this.insertAllRows();

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2007-1900, 9, 23));
            
            WEB3SLRepayApplyCompleteRequest l_request = new WEB3SLRepayApplyCompleteRequest();
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = new Date(2007-1900, 9, 13);
            l_impl.submitOrder(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02915, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testSubmitOrder_T07()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_T07()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getAccountId",
                    new Class[] {},
                    new Long(123456789l));
                
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager", 
                    "validateOrder",
                    new Class[] {SubAccount.class},
                    null);
                
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());

                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123456l));

                //DBRows
                this.insertAllRows();

                //ProductParams
                TestDBUtility.deleteAll(ProductParams.TYPE);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2007-1900, 9, 13));
            
            WEB3SLRepayApplyCompleteRequest l_request = new WEB3SLRepayApplyCompleteRequest();
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = new Date(2007-1900, 9, 23);
            l_impl.submitOrder(l_request);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    

    public void testSubmitOrder_T08()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_T08()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getAccountId",
                    new Class[] {},
                    new Long(123456789l));
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456l));

            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(false);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class, boolean.class},
                l_result);

            //DBRows
            this.insertAllRows();

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2007-1900, 9, 13));
            
            WEB3SLRepayApplyCompleteRequest l_request = new WEB3SLRepayApplyCompleteRequest();
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = new Date(2007-1900, 9, 23);
            l_impl.submitOrder(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01306, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testSubmitOrder_T09()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_T09()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456789l));
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456l));

            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class, boolean.class},
                l_result);

            ProcessingResult l_processingResult = 
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            OrderSubmissionResult l_submissionResult = new OrderSubmissionResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitNewOrder",
                new Class[] {SubAccount.class, ProductTypeEnum.class, NewOrderSpec.class, long.class, String.class, boolean.class},
                l_submissionResult);

            //DBRows
            this.insertAllRows();

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2007-1900, 9, 13));
            
            WEB3SLRepayApplyCompleteRequest l_request = new WEB3SLRepayApplyCompleteRequest();
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = new Date(2007-1900, 9, 23);
            l_impl.submitOrder(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testSubmitOrder_T10()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_T10()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456789l));
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456l));

            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class, boolean.class},
                l_result);

            ProcessingResult l_processingResult = 
                ProcessingResult.newSuccessResultInstance();
            OrderSubmissionResult l_submissionResult = new OrderSubmissionResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitNewOrder",
                new Class[] {SubAccount.class, ProductTypeEnum.class, NewOrderSpec.class, long.class, String.class, boolean.class},
                l_submissionResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "createNewOrderId",
                new Class[] {},
                new Long(1001));

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3AioOrderManager l_aioOrderManager =
                (WEB3AioOrderManager)l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();
            OrderUnit[] l_orderUnits = l_aioOrderManager.getOrderUnits(1001);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "getOrderUnits",
                new Class[] {long.class},
                l_orderUnits);
            
            //DBRows
            this.insertAllRows();

            //AioOrderUnitParams
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2007-1900, 9, 13));
            
            WEB3SLRepayApplyCompleteRequest l_request = new WEB3SLRepayApplyCompleteRequest();
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = new Date(2007-1900, 9, 23);
            l_impl.submitOrder(l_request);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    
    public void testSubmitOrder_T11()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_T11()";
        log.entering(STR_METHOD_NAME);
        try
        {

            //DBRows
            this.insertAllRows();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456789l));
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456l));

            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class, boolean.class},
                l_result);

            ProcessingResult l_processingResult = 
                ProcessingResult.newSuccessResultInstance();
            OrderSubmissionResult l_submissionResult = new OrderSubmissionResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitNewOrder",
                new Class[] {SubAccount.class, ProductTypeEnum.class, NewOrderSpec.class, long.class, String.class, boolean.class},
                l_submissionResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "createNewOrderId",
                new Class[] {},
                new Long(1001));

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3AioOrderManager l_aioOrderManager =
                (WEB3AioOrderManager)l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();
            OrderUnit[] l_orderUnits = l_aioOrderManager.getOrderUnits(1001);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "getOrderUnits",
                new Class[] {long.class},
                l_orderUnits);

            //AioOrderUnitParams
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2007-1900, 9, 23));
            
            WEB3SLRepayApplyCompleteRequest l_request = new WEB3SLRepayApplyCompleteRequest();
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = new Date(2007-1900, 9, 23);
            WEB3SLRepayApplyCompleteResponse l_response = l_impl.submitOrder(l_request);
            assertEquals(l_request.repayAmt, l_response.repayAmt);
            assertEquals(l_request.repayScheduledDate, l_response.repayScheduledDate);
            assertEquals("1001", l_response.orderId);
            assertEquals(WEB3DateUtility.getDate("20040716","yyyyMMdd"), l_response.lastUpdatedTimestamp);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrder_T12()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_T12()";
        log.entering(STR_METHOD_NAME);
        try
        {

            //DBRows
            this.insertAllRows();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456789l));
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456l));

            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class, boolean.class},
                l_result);

            ProcessingResult l_processingResult = 
                ProcessingResult.newSuccessResultInstance();
            OrderSubmissionResult l_submissionResult = new OrderSubmissionResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitNewOrder",
                new Class[] {SubAccount.class, ProductTypeEnum.class, NewOrderSpec.class, long.class, String.class, boolean.class},
                l_submissionResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "createNewOrderId",
                new Class[] {},
                new Long(1001));

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3AioOrderManager l_aioOrderManager =
                (WEB3AioOrderManager)l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();
            OrderUnit[] l_orderUnits = l_aioOrderManager.getOrderUnits(1001);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "getOrderUnits",
                new Class[] {long.class},
                l_orderUnits);

            //AioOrderUnitParams
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2007-1900, 9, 23));
            
            WEB3SLRepayApplyCompleteRequest l_request = new WEB3SLRepayApplyCompleteRequest();
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = new Date(2007-1900, 9, 24);
            WEB3SLRepayApplyCompleteResponse l_response = l_impl.submitOrder(l_request);
            assertEquals(l_request.repayAmt, l_response.repayAmt);
            assertEquals(l_request.repayScheduledDate, l_response.repayScheduledDate);
            assertEquals("1001", l_response.orderId);
            assertEquals(WEB3DateUtility.getDate("20040716","yyyyMMdd"), l_response.lastUpdatedTimestamp);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrder_T13()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_T13()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456789l));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456l));

            //DBRows
            this.insertAllRows();

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2007-1900, 9, 13));
            
            WEB3SLRepayApplyCompleteRequest l_request = new WEB3SLRepayApplyCompleteRequest();
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = new Date(2007-1900, 9, 23);
            
            //AioOrderUnitParams
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnit = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnit.setAccountId(123456789l);
            l_aioOrderUnit.setSubAccountId(33381251220301L);
            l_aioOrderUnit.setOrderType(OrderTypeEnum.TO_ORIX_CREDIT);
            l_aioOrderUnit.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_aioOrderUnit.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_aioOrderUnit.setDeliveryDate( l_request.repayScheduledDate);
            l_aioOrderUnit.setPaymentApplicationDiv(null);
            TestDBUtility.insertWithDel(l_aioOrderUnit);
            
            l_impl.submitOrder(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00757, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testExcute_T01()
    {
        final String METHOD_NAME = "testExcute_T01()";
        log.entering(METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456789l));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456l));

            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class, boolean.class},
                l_result);

            //DBRows
            this.insertAllRows();

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2007-1900, 9, 23));

            WEB3SLRepayApplyConfirmRequest l_request = new WEB3SLRepayApplyConfirmRequest();
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = new Date(2007-1900, 9, 24);
            WEB3SLRepayApplyConfirmResponse l_response =
                (WEB3SLRepayApplyConfirmResponse)l_impl.execute(l_request);
            assertEquals(l_request.repayAmt, l_response.repayAmt);
            assertEquals(l_request.repayScheduledDate, l_response.repayScheduledDate);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(METHOD_NAME);
    }
    
    
    
    public void testExcute_T02()
    {
        final String STR_METHOD_NAME = "testExcute_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {

            //DBRows
            this.insertAllRows();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456789l));
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456l));

            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class, boolean.class},
                l_result);

            ProcessingResult l_processingResult = 
                ProcessingResult.newSuccessResultInstance();
            OrderSubmissionResult l_submissionResult = new OrderSubmissionResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitNewOrder",
                new Class[] {SubAccount.class, ProductTypeEnum.class, NewOrderSpec.class, long.class, String.class, boolean.class},
                l_submissionResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "createNewOrderId",
                new Class[] {},
                new Long(1001));

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3AioOrderManager l_aioOrderManager =
                (WEB3AioOrderManager)l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();
            OrderUnit[] l_orderUnits = l_aioOrderManager.getOrderUnits(1001);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "getOrderUnits",
                new Class[] {long.class},
                l_orderUnits);

            //AioOrderUnitParams
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2007-1900, 9, 23));
            
            WEB3SLRepayApplyCompleteRequest l_request = new WEB3SLRepayApplyCompleteRequest();
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = new Date(2007-1900, 9, 24);
            WEB3SLRepayApplyCompleteResponse l_response =
                (WEB3SLRepayApplyCompleteResponse)l_impl.execute(l_request);
            assertEquals(l_request.repayAmt, l_response.repayAmt);
            assertEquals(l_request.repayScheduledDate, l_response.repayScheduledDate);
            assertEquals("1001", l_response.orderId);
            assertEquals(WEB3DateUtility.getDate("20040716","yyyyMMdd"), l_response.lastUpdatedTimestamp);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testExcute_T03()
    {
        final String STR_METHOD_NAME = "testExcute_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3SLRepayApplyInputRequest l_request = new WEB3SLRepayApplyInputRequest();
            l_impl.execute(l_request);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
   
    
    public void deleteAllRows()
    {
        try
        {
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);

            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);

            //StockSecuredLoanRow
            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);

            //TraderParams
            TestDBUtility.deleteAll(TraderParams.TYPE);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            //AioOrderUnitParams
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

    }

    public void insertAllRows()
    {
        try
        {
            //MainAccountParams
            MainAccountParams l_mainAccountRow = TestDBUtility.getMainAccountRow();
            l_mainAccountRow.setAccountId(123456789l);
            l_mainAccountRow.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountRow);

            //SubAccountParams
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456789l);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //StockSecuredLoanParams
            StockSecuredLoanParams l_stockSecuredLoanParams = this.insertStockSecuredLoanRow();
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            //InstitutionParams
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setTheDayTransfer("1");
            TestDBUtility.insertWithDel(l_institutionParams);

            //TraderParams
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(123456l);
            TestDBUtility.insertWithDel(l_traderParams);

            //ProductParams
            ProductParams l_params = TestDBUtility.getProductRow();
            l_params.setInstitutionCode("0D");
            l_params.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.insertWithDel(l_params);

            //AioOrderUnitParams
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderId(1001);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //22222222
            l_aioOrderUnitParams.setOrderUnitId(123456789l);
            l_queryProcessor.doInsertQuery(l_aioOrderUnitParams);
            
            //333333333333
            l_aioOrderUnitParams.setOrderUnitId(456789l);
            l_queryProcessor.doInsertQuery(l_aioOrderUnitParams);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    
    public StockSecuredLoanParams insertStockSecuredLoanRow()
    {
        StockSecuredLoanParams l_params = new StockSecuredLoanParams();
        //ストックローン口座番号   stock_loan_account_code     VARCHAR2    10  NotNull
        l_params.setStockLoanAccountCode("789456132");
        //口座ID  account_id  NUMBER  18  Notnull
        l_params.setAccountId(123456789l);
        //証券会社コード   institution_code    VARCHAR2    3   NotNull
        l_params.setInstitutionCode("0D");
        //部店コード     branch_code     VARCHAR2    3   NotNull
        l_params.setBranchCode("64");
        //口座コード     account_code    VARCHAR2    7   NotNull
        l_params.setAccountCode("123456");
        //開設状況  account_open_status     VARCHAR2    1   Null
        l_params.setAccountOpenStatus("2");
        //申込日時  appli_date  DATE    Null
        //開設日   account_open_date   DATE    Null
        //成約データ受信日時     order_data_reception_date   DATE    Null
        //解約データ受信日時     cancel_data_reception_date  DATE    Null
        //閉鎖日   close_date  DATE    Null
        //申込時Y客情報   y_customer_data     VARCHAR2    1   Null
        //申込時ロック客情報（考査ロック）  examin_lock_flag    VARCHAR2    1   Null
        //申込時ロック客情報（支店ロック）  branch_lock     VARCHAR2    1   Null
        //申込時ロック客情報（管理ロック）  mng_lock_flag   VARCHAR2    1   Null
        //申込時ロック客情報（管理ロック理由・立替金）    mng_lock_flag_advance   NUMBER  1   Null
        //申込時ロック客情報（管理ロック理由・保証金未入）  mng_lock_flag_unpay_margin  NUMBER  1   Null
        //申込時ロック客情報（管理ロック理由・適格担保不足）     mng_lock_flag_short_security    NUMBER  1   Null
        //申込時ロック客情報（管理ロック理由・預り証長期未差換）   mng_lock_flag_unsubstit_depo    NUMBER  1   Null
        //更新者コード    last_updater    VARCHAR2    20  Null
        //作成日時  created_timestamp   DATE    NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日時  last_updated_timestamp  DATE    NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_params;
    }
}
@

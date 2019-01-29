head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.40.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioSLRepayCancelServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 証券担保ローン返済取消サービスImplのテストクラス(WEB3AioSLRepayCancelServiceImplTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/09/23 何文敏 (中訊) 新規作成
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;

import test.util.TestDBUtility;

import webbroker3.aio.message.WEB3SLRepayApplyInputRequest;
import webbroker3.aio.message.WEB3SLRepayCancelCompleteRequest;
import webbroker3.aio.message.WEB3SLRepayCancelCompleteResponse;
import webbroker3.aio.message.WEB3SLRepayCancelConfirmRequest;
import webbroker3.aio.message.WEB3SLRepayCancelConfirmResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.StockSecuredLoanParams;
import webbroker3.gentrade.data.StockSecuredLoanRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AioSLRepayCancelServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLRepayCancelServiceImplTest.class);
    WEB3AioSLRepayCancelServiceImpl l_impl =
        new WEB3AioSLRepayCancelServiceImpl();
    
    public WEB3AioSLRepayCancelServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
        l_aioOrderParams.setOrderId(123);
        TestDBUtility.insertWithDel(l_aioOrderParams);
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testExecute_case0001()
    {
        final String STR_METHOD_NAME = "testExecute_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3GenRequest l_request = null;
            l_impl.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_case0002()
    {
        final String STR_METHOD_NAME = "testExecute_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3SLRepayApplyInputRequest l_request = new WEB3SLRepayApplyInputRequest(); 
            l_impl.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_case0003()
    {
        final String STR_METHOD_NAME = "testExecute_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AioSLRepayCancelServiceImplForTest l_implfro = new WEB3AioSLRepayCancelServiceImplForTest();
            WEB3SLRepayCancelConfirmRequest l_request = new WEB3SLRepayCancelConfirmRequest(); 
            WEB3SLRepayCancelConfirmResponse l_response =
                (WEB3SLRepayCancelConfirmResponse)l_implfro.execute(l_request);
            assertEquals("123", l_response.orderId);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_case0004()
    {
        final String STR_METHOD_NAME = "testExecute_case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AioSLRepayCancelServiceImplForTest l_implfro = new WEB3AioSLRepayCancelServiceImplForTest();
            WEB3SLRepayCancelCompleteRequest l_request = new WEB3SLRepayCancelCompleteRequest(); 
            WEB3SLRepayCancelCompleteResponse l_response =
                (WEB3SLRepayCancelCompleteResponse)l_implfro.execute(l_request);
            assertEquals("123", l_response.orderId);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateOrder_case0001()
    {
        final String STR_METHOD_NAME = "testValidateOrder_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.AIO);
            l_aioOrderUnitParams.setBizDate(
                    WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_aioOrderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            l_aioOrderUnitParams.setMqStatus(WEB3MqStatusDef.NOT_SEND_MAIL);
            l_aioOrderUnitParams.setOrderId(123);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "getSLChangePossAmt",
                    new Class[]
                    { WEB3GentradeSubAccount.class, Date.class },
                    new Double(123.45));
            
            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = new StockSecuredLoanParams();
            l_stockSecuredLoanParams.setAccountCode("123456");
            l_stockSecuredLoanParams.setAccountId(l_subAccountParams.getAccountId());
            l_stockSecuredLoanParams.setAccountOpenDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setAccountOpenStatus("2");
            l_stockSecuredLoanParams.setAppliDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setBranchCode("381");
            l_stockSecuredLoanParams.setStockLoanAccountCode("101234567");
            l_stockSecuredLoanParams.setInstitutionCode("0D");
            l_stockSecuredLoanParams.setOrderDataReceptionDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setCancelDataReceptionDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setCloseDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setMngLockFlagAdvance(BooleanEnum.TRUE);
            l_stockSecuredLoanParams.setMngLockFlagShortSecurity(BooleanEnum.TRUE);
            l_stockSecuredLoanParams.setMngLockFlagUnpayMargin(BooleanEnum.TRUE);
            l_stockSecuredLoanParams.setMngLockFlagUnsubstitDepo(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            
            WEB3AioSLRepayCancelServiceImplForTest1 l_implfro = new WEB3AioSLRepayCancelServiceImplForTest1();
            
            WEB3SLRepayCancelConfirmRequest l_request = new WEB3SLRepayCancelConfirmRequest();
            l_request.orderId = "123";
            WEB3SLRepayCancelConfirmResponse l_response = l_implfro.validateOrder(l_request);
            assertEquals(l_response.orderId, "123");
            assertEquals(l_response.repayableAmt, "123.45");
            assertEquals(l_response.repayAmt, "350000");
            assertEquals(WEB3DateUtility.formatDate(l_response.repayScheduledDate, "yyyyMMdd"), "20040716");
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateOrder_case0002()
    {
        final String STR_METHOD_NAME = "testValidateOrder_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3SLRepayCancelConfirmRequest l_request = new WEB3SLRepayCancelConfirmRequest();
            l_impl.validateOrder(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateOrder_case0003()
    {
        final String STR_METHOD_NAME = "testValidateOrder_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            
            WEB3SLRepayCancelConfirmRequest l_request = new WEB3SLRepayCancelConfirmRequest();
            l_request.orderId = "123";
            WEB3AioSLRepayCancelServiceImplForTest1 l_implfro = new WEB3AioSLRepayCancelServiceImplForTest1();
            l_implfro.validateOrder(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02914, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateOrder_case0004()
    {
        final String STR_METHOD_NAME = "testValidateOrder_case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.AIO);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_aioOrderUnitParams.setBizDate(
                    WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_aioOrderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            l_aioOrderUnitParams.setMqStatus(WEB3MqStatusDef.NOT_SEND_MAIL);
            l_aioOrderUnitParams.setOrderId(123);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            AioOrderUnitParams l_aioOrderUnitParams1 = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams1.setProductType(ProductTypeEnum.AIO);
            l_aioOrderUnitParams1.setOrderUnitId(123);
            l_aioOrderUnitParams.setBizDate("20101212");
            l_aioOrderUnitParams1.setOrderId(123);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams1);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "getSLChangePossAmt",
                    new Class[]
                    { WEB3GentradeSubAccount.class, Date.class },
                    new Double(123.45));
            
            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = new StockSecuredLoanParams();
            l_stockSecuredLoanParams.setAccountCode("123456");
            l_stockSecuredLoanParams.setAccountId(l_subAccountParams.getAccountId());
            l_stockSecuredLoanParams.setAccountOpenDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setAccountOpenStatus("2");
            l_stockSecuredLoanParams.setAppliDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setBranchCode("381");
            l_stockSecuredLoanParams.setStockLoanAccountCode("101234567");
            l_stockSecuredLoanParams.setInstitutionCode("0D");
            l_stockSecuredLoanParams.setOrderDataReceptionDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setCancelDataReceptionDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setCloseDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setMngLockFlagAdvance(BooleanEnum.TRUE);
            l_stockSecuredLoanParams.setMngLockFlagShortSecurity(BooleanEnum.TRUE);
            l_stockSecuredLoanParams.setMngLockFlagUnpayMargin(BooleanEnum.TRUE);
            l_stockSecuredLoanParams.setMngLockFlagUnsubstitDepo(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            
            WEB3AioSLRepayCancelServiceImplForTest1 l_implfro = new WEB3AioSLRepayCancelServiceImplForTest1();
            
            WEB3SLRepayCancelConfirmRequest l_request = new WEB3SLRepayCancelConfirmRequest();
            l_request.orderId = "123";
            WEB3SLRepayCancelConfirmResponse l_response = l_implfro.validateOrder(l_request);
            assertEquals(l_response.orderId, "123");
            assertEquals(l_response.repayableAmt, "123.45");
            assertEquals(l_response.repayAmt, "350000");
            assertEquals(WEB3DateUtility.formatDate(l_response.repayScheduledDate, "yyyyMMdd"), "20040716");
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02914, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void test_submitOrder_0001()
    {
        final String STR_METHOD_NAME =
            " test_submitOrder_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3SLRepayCancelCompleteRequest l_request = new WEB3SLRepayCancelCompleteRequest();

            l_impl.submitOrder(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public void test_submitOrder_0002()
    {
        final String STR_METHOD_NAME =
            " test_submitOrder_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);

            WEB3SLRepayCancelCompleteRequest l_request = new WEB3SLRepayCancelCompleteRequest();
            l_request.orderId = "123";
            WEB3AioSLRepayCancelServiceImplForTest1 l_implfro = new WEB3AioSLRepayCancelServiceImplForTest1();
            l_implfro.submitOrder(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02914, l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }


    public void test_submitOrder_0003()
    {
        final String STR_METHOD_NAME =
            " test_submitOrder_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.AIO);
            l_aioOrderUnitParams.setQuantity(1.1);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_aioOrderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            l_aioOrderUnitParams.setMqStatus(WEB3MqStatusDef.NOT_SEND_MAIL);
            l_aioOrderUnitParams.setOrderId(123);
            l_aioOrderUnitParams.setBizDate(
                    WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_aioOrderUnitParams.setErrorReasonCode("1");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            AioOrderUnitParams l_aioOrderUnitParams1 = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams1.setProductType(ProductTypeEnum.AIO);
            l_aioOrderUnitParams1.setOrderUnitId(123);
            l_aioOrderUnitParams1.setOrderId(123);
            l_aioOrderUnitParams1.setQuantity(1.1);
            l_aioOrderUnitParams1.setBizDate("20101212");
            l_aioOrderUnitParams1.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_aioOrderUnitParams1.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            l_aioOrderUnitParams1.setMqStatus(WEB3MqStatusDef.NOT_SEND_MAIL);
            l_aioOrderUnitParams1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_aioOrderUnitParams1.setErrorReasonCode("1");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams1);

            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setProductType(ProductTypeEnum.AIO);
            l_aioOrderParams.setOrderId(123);
            TestDBUtility.insertWithDel(l_aioOrderParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "getSLChangePossAmt",
                new Class[]
                { WEB3GentradeSubAccount.class, Date.class },
                new Double(123.45));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);

            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = new StockSecuredLoanParams();
            l_stockSecuredLoanParams.setAccountCode("123456");
            l_stockSecuredLoanParams.setAccountId(l_subAccountParams.getAccountId());
            l_stockSecuredLoanParams.setAccountOpenDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setAccountOpenStatus("2");
            l_stockSecuredLoanParams.setAppliDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setBranchCode("381");
            l_stockSecuredLoanParams.setStockLoanAccountCode("101234567");
            l_stockSecuredLoanParams.setInstitutionCode("0D");
            l_stockSecuredLoanParams.setOrderDataReceptionDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setCancelDataReceptionDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setCloseDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setMngLockFlagAdvance(BooleanEnum.TRUE);
            l_stockSecuredLoanParams.setMngLockFlagShortSecurity(BooleanEnum.TRUE);
            l_stockSecuredLoanParams.setMngLockFlagUnpayMargin(BooleanEnum.TRUE);
            l_stockSecuredLoanParams.setMngLockFlagUnsubstitDepo(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setTheDayTransfer("1");
            l_institutionParams.setPaymentReserve("2");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AioSLRepayCancelServiceImplForTest1 l_implfro = new WEB3AioSLRepayCancelServiceImplForTest1();

            WEB3SLRepayCancelCompleteRequest l_request = new WEB3SLRepayCancelCompleteRequest();
            l_request.orderId = "123";
            WEB3SLRepayCancelCompleteResponse l_slRepayCancelCompleteResponse =
                (WEB3SLRepayCancelCompleteResponse)l_implfro.submitOrder(l_request);
            assertEquals("123", l_slRepayCancelCompleteResponse.orderId);
            assertEquals("1.1", l_slRepayCancelCompleteResponse.repayAmt);
        }
        catch (WEB3BusinessLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02914, l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public void testvalidateCancelAccept_T01()
    {
        final String STR_METHOD_NAME = "testvalidateCancelAccept_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Method method = WEB3AioSLRepayCancelServiceImpl.class.getDeclaredMethod(
                "validateCancelAccept",
                new Class[]{CancelOrderSpec.class});
            method.setAccessible(true);
            Object[] l_obj = new Object[]{null};
            WEB3AioSLRepayCancelServiceImpl l_impl = new WEB3AioSLRepayCancelServiceImpl();
            method.invoke(l_impl, l_obj);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            WEB3SystemLayerException l_sysLex =
                (WEB3SystemLayerException)l_ex.getTargetException();
            assertEquals(l_sysLex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testvalidateCancelAccept_T02()
    {
        final String STR_METHOD_NAME = "testvalidateCancelAccept_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            CancelOrderSpec l_orderSpec = new CancelOrderSpec(123456789l);
            Method method = WEB3AioSLRepayCancelServiceImpl.class.getDeclaredMethod(
                "validateCancelAccept",
                new Class[]{CancelOrderSpec.class});
            method.setAccessible(true);
            Object[] l_obj = new Object[]{l_orderSpec};
            TestDBUtility.deleteAll(AioOrderParams.TYPE);
            WEB3AioSLRepayCancelServiceImpl l_impl = new WEB3AioSLRepayCancelServiceImpl();
            method.invoke(l_impl, l_obj);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            WEB3SystemLayerException l_sysLex =
                (WEB3SystemLayerException)l_ex.getTargetException();
            assertEquals(l_sysLex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
   
    public void testvalidateCancelAccept_T03()
    {
        final String STR_METHOD_NAME = "testvalidateCancelAccept_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            CancelOrderSpec l_orderSpec = new CancelOrderSpec(123456789l);
            Method method = WEB3AioSLRepayCancelServiceImpl.class.getDeclaredMethod(
                "validateCancelAccept",
                new Class[]{CancelOrderSpec.class});
            method.setAccessible(true);
            Object[] l_obj = new Object[]{l_orderSpec};

            AioOrderUnitParams l_aioOrderUnitParams1 = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams1.setProductType(ProductTypeEnum.AIO);
            l_aioOrderUnitParams1.setOrderUnitId(123);
            l_aioOrderUnitParams1.setOrderId(123456789);
            l_aioOrderUnitParams1.setQuantity(1.1);
            l_aioOrderUnitParams1.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_aioOrderUnitParams1.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            l_aioOrderUnitParams1.setMqStatus(WEB3MqStatusDef.NOT_SEND_MAIL);
            l_aioOrderUnitParams1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_aioOrderUnitParams1.setErrorReasonCode("1");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams1);

            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(123456789);
            TestDBUtility.insertWithDel(l_aioOrderParams);
            
            WEB3AioSLRepayCancelServiceImpl l_impl = new WEB3AioSLRepayCancelServiceImpl();
            method.invoke(l_impl, l_obj);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            WEB3BusinessLayerException l_sysLex =
                (WEB3BusinessLayerException)l_ex.getTargetException();
            assertEquals(l_sysLex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02965);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }


    public void testvalidateCancelAccept_T04()
    {
        final String STR_METHOD_NAME = "testvalidateCancelAccept_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            CancelOrderSpec l_orderSpec = new CancelOrderSpec(123456789l);
            Method method = WEB3AioSLRepayCancelServiceImpl.class.getDeclaredMethod(
                "validateCancelAccept",
                new Class[]{CancelOrderSpec.class});
            method.setAccessible(true);
            Object[] l_obj = new Object[]{l_orderSpec};

            AioOrderUnitParams l_aioOrderUnitParams1 = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams1.setProductType(ProductTypeEnum.AIO);
            l_aioOrderUnitParams1.setOrderUnitId(123);
            l_aioOrderUnitParams1.setOrderId(123456789);
            l_aioOrderUnitParams1.setBizDate("21101012");
            l_aioOrderUnitParams1.setQuantity(1.1);
            l_aioOrderUnitParams1.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_aioOrderUnitParams1.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            l_aioOrderUnitParams1.setMqStatus(WEB3MqStatusDef.NOT_SEND_MAIL);
            l_aioOrderUnitParams1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_aioOrderUnitParams1.setErrorReasonCode("1");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams1);

            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(123456789);
            TestDBUtility.insertWithDel(l_aioOrderParams);
            
            WEB3AioSLRepayCancelServiceImpl l_impl = new WEB3AioSLRepayCancelServiceImpl();
            method.invoke(l_impl, l_obj);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testvalidateCancelAccept_T05()
    {
        final String STR_METHOD_NAME = "testvalidateCancelAccept_T05()";
        log.entering(STR_METHOD_NAME);
        try
        {
            CancelOrderSpec l_orderSpec = new CancelOrderSpec(123456789l);
            Method method = WEB3AioSLRepayCancelServiceImpl.class.getDeclaredMethod(
                "validateCancelAccept",
                new Class[]{CancelOrderSpec.class});
            method.setAccessible(true);
            Object[] l_obj = new Object[]{l_orderSpec};

            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(123456789);
            TestDBUtility.insertWithDel(l_aioOrderParams);

            WEB3AioSLRepayCancelServiceImpl l_impl = new WEB3AioSLRepayCancelServiceImpl();
            method.invoke(l_impl, l_obj);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    class WEB3AioSLRepayCancelServiceImplForTest extends WEB3AioSLRepayCancelServiceImpl
    {
        protected WEB3SLRepayCancelConfirmResponse validateOrder(WEB3SLRepayCancelConfirmRequest l_request)
            throws WEB3BaseException
        {
            WEB3SLRepayCancelConfirmResponse l_response =
                (WEB3SLRepayCancelConfirmResponse)l_request.createResponse();
            l_response.orderId = "123";
            return l_response;
        }
        
        protected WEB3SLRepayCancelCompleteResponse submitOrder(WEB3SLRepayCancelCompleteRequest l_request)
        throws WEB3BaseException
        {
            WEB3SLRepayCancelCompleteResponse l_response =
                (WEB3SLRepayCancelCompleteResponse)l_request.createResponse();
            l_response.orderId = "123";
            return l_response;
        }
    }
    class WEB3AioSLRepayCancelServiceImplForTest1 extends WEB3AioSLRepayCancelServiceImpl
    {
        public SubAccount getSubAccount(SubAccountTypeEnum l_subAccountType) throws WEB3SystemLayerException
        {
            SubAccount l_subAccount = null;
            try
            {
                l_subAccount = new WEB3GentradeSubAccount(333812512203L, 33381251220301L);
            } catch (DataQueryException e)
            {

            } catch (DataNetworkException e)
            {

            }
            return l_subAccount;
        }
    }
}
@

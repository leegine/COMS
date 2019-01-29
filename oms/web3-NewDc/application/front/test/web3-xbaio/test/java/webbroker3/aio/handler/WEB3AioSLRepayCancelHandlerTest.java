head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.25.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioSLRepayCancelHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 証券担保ローン返済取消ハンドラのテストクラス(WEB3AioSLRepayCancelHandlerTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/09/23 何文敏 (中訊) 新規作成
*/
package webbroker3.aio.handler;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import test.util.TestDBUtility;

import webbroker3.aio.message.WEB3SLRepayCancelCompleteRequest;
import webbroker3.aio.message.WEB3SLRepayCancelCompleteResponse;
import webbroker3.aio.message.WEB3SLRepayCancelConfirmRequest;
import webbroker3.aio.message.WEB3SLRepayCancelConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioSLRepayCancelService;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSLRepayCancelServiceImpl;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.StockSecuredLoanParams;
import webbroker3.gentrade.data.StockSecuredLoanRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AioSLRepayCancelHandlerTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLRepayCancelHandlerTest.class);
    WEB3AioSLRepayCancelHandler l_handler = new WEB3AioSLRepayCancelHandler();
    WEB3AioSLRepayCancelService service = null;

    public WEB3AioSLRepayCancelHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        Services.overrideService(WEB3AioSLRepayCancelService.class,
            new WEB3AioSLRepayCancelServiceImpl());
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testCompleteOrder_case0001()
    {
        final String STR_METHOD_NAME = "testCompleteOrder_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            service =
                (WEB3AioSLRepayCancelService)Services.getService(
                    WEB3AioSLRepayCancelService.class);
            Services.unregisterService(WEB3AioSLRepayCancelService.class);
            WEB3SLRepayCancelCompleteRequest l_request =
                new WEB3SLRepayCancelCompleteRequest();
            WEB3SLRepayCancelCompleteResponse l_response = l_handler.completeOrder(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AioSLRepayCancelService.class, service);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompleteOrder_case0002()
    {
        final String STR_METHOD_NAME = "testCompleteOrder_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("37");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            WEB3SLRepayCancelCompleteRequest l_request =
                new WEB3SLRepayCancelCompleteRequest();
            WEB3SLRepayCancelCompleteResponse l_response = l_handler.completeOrder(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testConfirmOrder_case0001()
    {
        final String STR_METHOD_NAME = "testConfirmOrder_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            service =
                (WEB3AioSLRepayCancelService)Services.getService(
                    WEB3AioSLRepayCancelService.class);
            Services.unregisterService(WEB3AioSLRepayCancelService.class);
            WEB3SLRepayCancelConfirmRequest l_request =
                new WEB3SLRepayCancelConfirmRequest();
            WEB3SLRepayCancelConfirmResponse l_response = l_handler.confirmOrder(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AioSLRepayCancelService.class, service);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testConfirmOrder_case0002()
    {
        final String STR_METHOD_NAME = "testConfirmOrder_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("37");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            WEB3SLRepayCancelConfirmRequest l_request =
                new WEB3SLRepayCancelConfirmRequest();
            WEB3SLRepayCancelConfirmResponse l_response = l_handler.confirmOrder(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testConfirmOrder_case0003()
    {
        final String STR_METHOD_NAME = "testConfirmOrder_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("37");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            WEB3SLRepayCancelConfirmRequest l_request =
                new WEB3SLRepayCancelConfirmRequest();
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(l_subAccountParams.getAccountId()));
            
            TestDBUtility.deleteAll(AioOrderParams.TYPE);
            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setProductType(ProductTypeEnum.AIO);
            l_aioOrderParams.setOrderId(123);
            TestDBUtility.insertWithDel(l_aioOrderParams);
            
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.AIO);
            l_aioOrderUnitParams.setOrderId(123);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "getSLChangePossAmt",
                    new Class[]
                    { WEB3GentradeSubAccount.class, Date.class },
                    new Double(123.45));
                    
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager", "validateCancelOrder",
                    new Class[]
                    {SubAccount.class, CancelOrderSpec.class},
                    l_result);
            
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
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("19960212", "yyyyMMdd"));
            
            l_request.orderId = "123";
            WEB3SLRepayCancelConfirmResponse l_response = l_handler.confirmOrder(l_request);
            assertEquals(l_response.orderId, "123");
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    class WEB3AioSLRepayCancelServiceImplForTest extends WEB3AioSLRepayCancelServiceImpl
    {
        
    }
}
@

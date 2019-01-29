head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.45.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondCancelServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3BondCancelServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/05/11 徐宏偉 (中訊) 新規作成
Revesion History : 2007/07/05  【WEB3】【CITIフロント導入（債券）】案件取消，注掉代碼
*/
package webbroker3.bd.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;

import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.message.WEB3BondCancelCompleteRequest;
import webbroker3.bd.message.WEB3BondCancelConfirmRequest;
import webbroker3.bd.message.WEB3BondCancelConfirmResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.OrderAcceptStatusParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3BondCancelServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondCancelServiceImplTest.class);

    WEB3BondCancelServiceImpl l_impl = new WEB3BondCancelServiceImpl();

    public WEB3BondCancelServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        MOCK_MANAGER.setIsMockUsed(true);
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
//
//    public void testValidateCancelOrder_case001()
//    {
//        final String STR_METHOD_NAME = " testValidateCancelOrder_case001";
//        log.entering(STR_METHOD_NAME);
//        
//        WEB3BondCancelConfirmRequest l_request = new WEB3BondCancelConfirmRequest();
//        try
//        {
//            for (int i = 0; i < 2; i++)
//            {
//                MOCK_MANAGER.setIsMockUsed(true);
//                l_request.orderId = "123";
//                TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
//                BondOrderUnitParams l_bondOrderunitParams = TestDBUtility.getBondOrderUnitRow();
//                l_bondOrderunitParams.setOrderUnitId(1232);
//                l_bondOrderunitParams.setOrderId(123);
//                l_bondOrderunitParams.setAccountId(333812512203L);
//                l_bondOrderunitParams.setSubAccountId(33381251220301L);
//                l_bondOrderunitParams.setOrderType(OrderTypeEnum.BOND_BUY);
//                l_bondOrderunitParams.setDealType("92");
//                l_bondOrderunitParams.setOrderExecStatus("3");
//                l_bondOrderunitParams.setBizDate("20080908");
//                l_bondOrderunitParams.setExecDate(WEB3DateUtility.getDate("20070408", "yyyyMMdd"));
//                l_bondOrderunitParams.setForeignExecDate(WEB3DateUtility.getDate("20070408", "yyyyMMdd"));
//                l_bondOrderunitParams.setDeliveryDate(WEB3DateUtility.getDate("20070408", "yyyyMMdd"));
//                l_bondOrderunitParams.setForeignDeliveryDate(WEB3DateUtility.getDate("20070408", "yyyyMMdd"));
//                l_bondOrderunitParams.setPaymentDate(WEB3DateUtility.getDate("20070408", "yyyyMMdd"));
//                l_bondOrderunitParams.setLimitPrice(10);
//                
//                if (i == 0)
//                {
//                    l_bondOrderunitParams.setLimitPrice(10);
//                }
//                else if (i == 1)
//                {
//                    l_bondOrderunitParams.setLimitPrice(0);
//                }
//
//                TestDBUtility.insertWithDel(l_bondOrderunitParams);
//                
//                TestDBUtility.deleteAll(BondOrderParams.TYPE);
//                BondOrderParams l_bondOrderParams = new BondOrderParams();
//                l_bondOrderParams.setOrderId(123);
//                l_bondOrderParams.setAccountId(333812512203L);
//                l_bondOrderParams.setSubAccountId(33381251220301L);
//                l_bondOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070408", "yyyyMMdd"));
//                l_bondOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070408", "yyyyMMdd"));
//                l_bondOrderParams.setProductType(ProductTypeEnum.BOND);
//                TestDBUtility.insertWithDel(l_bondOrderParams);
//
//                TestDBUtility.deleteAll(ProductParams.TYPE);
//                ProductParams l_productParams = TestDBUtility.getProductRow();
//                l_productParams.setProductId(123456);
//                TestDBUtility.insertWithDel(l_productParams);
//
//                TestDBUtility.deleteAll(BondProductParams.TYPE);
//                BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
//                l_bondProductParams.setTradingTimeCheckDiv("1");
//                l_bondProductParams.setProductId(123456);
//                l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
//                l_bondProductParams.setTradeType("1");
//                l_bondProductParams.setTradeHandleDiv("2");
//                l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070408", "yyyyMMdd"));
//                l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20080908", "yyyyMMdd"));
//                l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
//                l_bondProductParams.setMaturityDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
//                l_bondProductParams.setBuyPrice(0);
//                TestDBUtility.insertWithDel(l_bondProductParams);
//                
//
//                WEB3GentradeTradingTimeManagementForMock.getClendarContext();
//                MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//                l_mainAccountParams.setAccountId(333812512203L);
//                TestDBUtility.insertWithDel(l_mainAccountParams);
//
//                SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//                l_subAccountParams.setAccountId(333812512203L);
//                l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//                TestDBUtility.insertWithDel(l_subAccountParams);
//
//                GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
//                l_genCurrencyParams.setInstitutionCode("0D");
//                l_genCurrencyParams.setCurrencyCode("01");
//                TestDBUtility.insertWithDel(l_genCurrencyParams);
//
//                BranchParams l_branchParams = TestDBUtility.getBranchRow();
//                l_branchParams.setBranchId(33381L);
//                TestDBUtility.insertWithDel(l_branchParams);
//                
//                InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//                l_institutionParams.setInstitutionId(33L);
//                TestDBUtility.insertWithDel(l_institutionParams);
//
//                BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
//                l_bondBranchConditionParams.setBranchId(33381L);
//                
//                TestDBUtility.insertWithDel(l_bondBranchConditionParams);
//                
//                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
//                    new Class[] {}, new Long(333812512203L));
//                Date l_bizDate = WEB3DateUtility.getDate("20080908", "yyyyMMdd");
//                WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDate);
//                
//                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
//                    "validateSubAccountForTrading",
//                    new Class[] {SubAccount.class}, OrderValidationResult.VALIDATION_OK_RESULT);
//
//                WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();
//                l_tradingPowerResult.setResultFlg(true);
//                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
//                    "getOtherTradingPower",
//                    new Class[] {WEB3GentradeSubAccount.class, Date.class}, new Double(133));
//                
//                WEB3BondCancelConfirmResponse l_response = l_impl.validateCancelOrder(l_request);
//
//                assertEquals("20070408", WEB3DateUtility.formatDate(l_response.executionUpdateDate, "yyyyMMdd"));
//                assertEquals("20070408", WEB3DateUtility.formatDate(l_response.foreignExecutionDate, "yyyyMMdd"));
//                assertEquals("20070408", WEB3DateUtility.formatDate(l_response.deliveryDate, "yyyyMMdd"));
//                assertEquals("20070408", WEB3DateUtility.formatDate(l_response.foreignDeliveryDate, "yyyyMMdd"));
//                assertEquals("20070408", WEB3DateUtility.formatDate(l_response.paymentDate, "yyyyMMdd"));
//                
//                if (i == 0)
//                {
//                    assertEquals("1", l_response.orderPriceDiv);    
//                }
//                else if (i == 1)
//                {
//                    assertEquals("2", l_response.orderPriceDiv);   
//                }
//            }
//        }
//        catch (WEB3BaseException e)
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
    
    public void testValidateCancelOrder_0001()
    {
        final String STR_METHOD_NAME = " testValidateCancelOrder_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        WEB3BondCancelConfirmRequest l_request = new WEB3BondCancelConfirmRequest();
        l_request.orderId = "123456";
        
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                "web3.tradingcalendarcontext");
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        l_context = l_clendarContext;
        l_context.setInstitutionCode("0D");
        l_context.setBranchCode("381");
        l_context.setOrderAcceptProduct("01");
        l_context.setOrderAcceptTransaction("07");
        ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

        try
        {
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            TestDBUtility.deleteAll(l_orderAcceptStatusParams.getRowType());
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            
            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(3304148080000L);
            l_bondProductParams.setBondType(BondTypeEnum.FOREIGN_BOND);
            
            WEB3BondOrderUnit l_bondOrderUnit = new WEB3BondOrderUnit(Long.parseLong(l_request.orderId));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(                
                "webbroker3.bd.WEB3BondOrderManager",
                "getBondOrderUnitByOrderId",
                new Class[]{long.class},
                l_bondOrderUnit);
            
            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondProductManager",
                "getBondProduct",
                new Class[] { long.class },
                l_bondProduct);
            
            OrderValidationResult l_result = new OrderValidationResult(ProcessingResult.newSuccessResultInstance());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result);
            TestDBUtility.deleteAll(BondOrderParams.TYPE);
            WEB3BondCancelServiceImplForTest l_bondCancelServiceImplForTest = new WEB3BondCancelServiceImplForTest();
            l_bondCancelServiceImplForTest.validateCancelOrder(l_request);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02011, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateCancelOrder_0002()
    {
        final String STR_METHOD_NAME = " testValidateCancelOrder_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        WEB3BondCancelConfirmRequest l_request = new WEB3BondCancelConfirmRequest();
        l_request.orderId = "123456";
        
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                "web3.tradingcalendarcontext");
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        l_context = l_clendarContext;
        l_context.setInstitutionCode("0D");
        l_context.setBranchCode("381");
        l_context.setOrderAcceptProduct("01");
        l_context.setOrderAcceptTransaction("07");
        ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

        try
        {
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            TestDBUtility.deleteAll(l_orderAcceptStatusParams.getRowType());
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            
            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setExecDate(WEB3DateUtility.getDate("20070101", "yyyyMMdd"));
            l_bondOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20080101", "yyyyMMdd"));
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(3304148080000L);
            l_bondProductParams.setBondType(BondTypeEnum.CORPORATE_BOND);
            
            WEB3BondOrderUnit l_bondOrderUnit = new WEB3BondOrderUnit(Long.parseLong(l_request.orderId));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(                
                "webbroker3.bd.WEB3BondOrderManager",
                "getBondOrderUnitByOrderId",
                new Class[]{long.class},
                l_bondOrderUnit);
            
            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondProductManager",
                "getBondProduct",
                new Class[] { long.class },
                l_bondProduct);
            
            OrderValidationResult l_result = new OrderValidationResult(ProcessingResult.newSuccessResultInstance());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondOrderManager",
                "validateOrderCancelPossibleStatus",
                new Class[] { WEB3BondOrderUnit.class },
                null);
            TestDBUtility.deleteAll(BondOrderParams.TYPE);
            WEB3BondCancelServiceImplForTest l_bondCancelServiceImplForTest = new WEB3BondCancelServiceImplForTest();
            l_bondCancelServiceImplForTest.validateCancelOrder(l_request);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02011, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateCancelOrder_0003()
    {
        final String STR_METHOD_NAME = " testValidateCancelOrder_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        WEB3BondCancelConfirmRequest l_request = new WEB3BondCancelConfirmRequest();
        l_request.orderId = "123456";
        
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                "web3.tradingcalendarcontext");
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        l_context = l_clendarContext;
        l_context.setInstitutionCode("0D");
        l_context.setBranchCode("381");
        l_context.setOrderAcceptProduct("01");
        l_context.setOrderAcceptTransaction("07");
        ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

        try
        {
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            TestDBUtility.deleteAll(l_orderAcceptStatusParams.getRowType());
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            TestDBUtility.deleteAll(BondOrderParams.TYPE);
            BondOrderParams l_bondOrderParams = new BondOrderParams();
            l_bondOrderParams.setOrderId(123456L);
            l_bondOrderParams.setAccountId(1);
            l_bondOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_bondOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_bondOrderParams.setProductType(ProductTypeEnum.BOND);
            l_bondOrderParams.setSubAccountId(1);
            TestDBUtility.insertWithDel(l_bondOrderParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setExecDate(WEB3DateUtility.getDate("20070101", "yyyyMMdd"));
            l_bondOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20080101", "yyyyMMdd"));
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(3304148080000L);
            l_bondProductParams.setBondType(BondTypeEnum.FOREIGN_BOND);
            
            WEB3BondOrderUnit l_bondOrderUnit = new WEB3BondOrderUnit(Long.parseLong(l_request.orderId));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(                
                "webbroker3.bd.WEB3BondOrderManager",
                "getBondOrderUnitByOrderId",
                new Class[]{long.class},
                l_bondOrderUnit);
            
            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondProductManager",
                "getBondProduct",
                new Class[] { long.class },
                l_bondProduct);
            
            OrderValidationResult l_result = new OrderValidationResult(ProcessingResult.newSuccessResultInstance());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondOrderManager",
                "validateOrderCancelPossibleStatus",
                new Class[] { WEB3BondOrderUnit.class },
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondOrderManager",
                "validateAccountHandlingPossibleProduct",
                new Class[]{ WEB3BondProduct.class, String.class },
                null);
            
            WEB3BondCancelServiceImplForTest l_bondCancelServiceImplForTest = new WEB3BondCancelServiceImplForTest();
            WEB3BondCancelConfirmResponse l_response = l_bondCancelServiceImplForTest.validateCancelOrder(l_request);
            
            assertEquals(WEB3DateUtility.getDate("20070101", "yyyyMMdd").toLocaleString(), l_response.executionUpdateDate.toLocaleString());
            assertEquals(WEB3DateUtility.getDate("20080101", "yyyyMMdd").toLocaleString(), l_response.deliveryDate.toLocaleString());
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02012, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitCancelOrder_0001()
    {
        final String STR_METHOD_NAME = " testSubmitCancelOrder_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        WEB3BondCancelCompleteRequest l_request = new WEB3BondCancelCompleteRequest();
        l_request.orderId = "123456";
        l_request.checkDate = WEB3DateUtility.getDate("20070203", "yyyyMMdd");
        
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                "web3.tradingcalendarcontext");
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        l_context = l_clendarContext;
        l_context.setInstitutionCode("0D");
        l_context.setBranchCode("381");
        l_context.setOrderAcceptProduct("01");
        l_context.setOrderAcceptTransaction("07");
        ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

        try
        {
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            TestDBUtility.deleteAll(l_orderAcceptStatusParams.getRowType());
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            
            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(3304148080000L);
            l_bondProductParams.setBondType(BondTypeEnum.FOREIGN_BOND);
            
            WEB3BondOrderUnit l_bondOrderUnit = new WEB3BondOrderUnit(Long.parseLong(l_request.orderId));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(                
                "webbroker3.bd.WEB3BondOrderManager",
                "getBondOrderUnitByOrderId",
                new Class[]{long.class},
                l_bondOrderUnit);
            
            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondProductManager",
                "getBondProduct",
                new Class[] { long.class },
                l_bondProduct);
            
            OrderValidationResult l_result = new OrderValidationResult(ProcessingResult.newSuccessResultInstance());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result);
            TestDBUtility.deleteAll(BondOrderParams.TYPE);
            WEB3BondCancelServiceImplForTest l_bondCancelServiceImplForTest = new WEB3BondCancelServiceImplForTest();
            l_bondCancelServiceImplForTest.submitCancelOrder(l_request);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02011, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitCancelOrder_0002()
    {
        final String STR_METHOD_NAME = " testSubmitCancelOrder_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        WEB3BondCancelCompleteRequest l_request = new WEB3BondCancelCompleteRequest();
        l_request.orderId = "123456";
        l_request.checkDate = WEB3DateUtility.getDate("20070203", "yyyyMMdd");
        
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                "web3.tradingcalendarcontext");
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        l_context = l_clendarContext;
        l_context.setInstitutionCode("0D");
        l_context.setBranchCode("381");
        l_context.setOrderAcceptProduct("01");
        l_context.setOrderAcceptTransaction("07");
        ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

        try
        {
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            TestDBUtility.deleteAll(l_orderAcceptStatusParams.getRowType());
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            
            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(3304148080000L);
            l_bondProductParams.setBondType(BondTypeEnum.CORPORATE_BOND);
            
            WEB3BondOrderUnit l_bondOrderUnit = new WEB3BondOrderUnit(Long.parseLong(l_request.orderId));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(                
                "webbroker3.bd.WEB3BondOrderManager",
                "getBondOrderUnitByOrderId",
                new Class[]{long.class},
                l_bondOrderUnit);
            
            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondProductManager",
                "getBondProduct",
                new Class[] { long.class },
                l_bondProduct);
            
            OrderValidationResult l_result = new OrderValidationResult(ProcessingResult.newSuccessResultInstance());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result);
            TestDBUtility.deleteAll(BondOrderParams.TYPE);
            WEB3BondCancelServiceImplForTest l_bondCancelServiceImplForTest = new WEB3BondCancelServiceImplForTest();
            l_bondCancelServiceImplForTest.submitCancelOrder(l_request);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02011, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public class WEB3BondCancelServiceImplForTest extends WEB3BondCancelServiceImpl
    {
        public SubAccount getSubAccount() throws WEB3BaseException 
        {
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            return l_subAccount;
        }
    }
}
@

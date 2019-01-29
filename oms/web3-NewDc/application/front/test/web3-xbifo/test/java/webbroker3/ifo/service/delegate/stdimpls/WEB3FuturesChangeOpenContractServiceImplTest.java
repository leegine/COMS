head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesChangeOpenContractServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3FuturesChangeOpenContractServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/28 孫洪江 (中訊) 新規作成
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractChangeSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeCompleteResponse;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeConfirmResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.triggerorder.base.data.RsvIfoOrderActionRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesChangeOpenContractServiceImplTest extends TestBaseForMock
{
    private WEB3FuturesOpenMarginChangeConfirmRequest l_request = null;
    private WEB3FuturesOpenMarginChangeConfirmResponse l_response = null;
    private WEB3FuturesChangeOpenContractServiceImpl l_service = null;
    
    private WEB3FuturesOpenMarginChangeCompleteRequest l_completeRequest = null;
    private WEB3FuturesOpenMarginChangeCompleteResponse l_completeResponse = null;
    
    private boolean l_blnIsReserveOrderExist = true;
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3FuturesChangeOpenContractServiceImplTest.class);
    
    public WEB3FuturesChangeOpenContractServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_request = new WEB3FuturesOpenMarginChangeConfirmRequestForTest();
        this.l_service = new WEB3FuturesChangeOpenContractServiceImpl();
        this.l_completeRequest = new WEB3FuturesOpenMarginChangeCompleteRequestForTest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
//    /**
//     * create新規建訂正内容の引数設定
//     */
//    public void testValidateOrder_0001()
//    {
//        final String STR_METHOD_NAME = "testValidateOrder_0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            l_request.id = "33381";
//            l_request.limitPrice = "0";
//            this.initData();
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
//                "validateFuturesChangeOrder", 
//                new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class },
//                new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
//            
//            this.l_response = this.l_service.validateOrder(this.l_request);
//            fail();
//        }
//        catch(WEB3MockObjectRuntimeException l_web3MockObjectRuntimeException)
//        {
//            log.error(l_web3MockObjectRuntimeException.getMessage(), l_web3MockObjectRuntimeException);
//            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
//                    "validateFuturesChangeOrder", 
//                    new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class });
//            assertEquals(false,((
//                WEB3IfoOpenContractChangeSpec)l_paramsValue1.getFirstCalled()[1]).getEveningSessionCarryoverFlag());
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.exiting(TEST_START + STR_METHOD_NAME);
//    }
//    
//    /**
//     * create新規建訂正内容の引数設定
//     */
//    public void testSubmitOrder_0001()
//    {
//        final String STR_METHOD_NAME = "testSubmitOrder_0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            this.l_completeRequest.id = "33381"; 
//            this.l_completeRequest.limitPrice = "0";
//            this.initData();
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
//                    "validateFuturesChangeOrder", 
//                    new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class },
//                    new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
//            
////            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
////                    "webbroker3.gentrade.WEB3GentradeAccountManager",
////                    "getBranch",
////                    new Class[] {long.class},
////                    null);
//                    
//            this.l_completeResponse = this.l_service.submitOrder(this.l_completeRequest);
//            fail();
//        }
//        catch(WEB3MockObjectRuntimeException l_web3MockObjectRuntimeException)
//        {
//            log.error(l_web3MockObjectRuntimeException.getMessage(), l_web3MockObjectRuntimeException);
//            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
//                    "validateFuturesChangeOrder", 
//                    new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class });
//            assertEquals(false,((
//                WEB3IfoOpenContractChangeSpec)l_paramsValue1.getFirstCalled()[1]).getEveningSessionCarryoverFlag());
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        
//        log.exiting(TEST_START + STR_METHOD_NAME);
//    }
    
    /**
     * 
     *
     */
    public void testValidateOrder_0002()
    {
        final String STR_METHOD_NAME = "testValidateOrder_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            l_request.id = "33381";
            l_request.limitPrice = "0";
            l_request.expirationDate = WEB3DateUtility.getDate("20040717", "yyyyMMdd");
            this.initData();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "validateFuturesChangeOrder", 
                new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class },
                new OrderValidationResult(
                        ProcessingResult.newSuccessResultInstance()));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "orderChannel");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},new Long(101001010010L));
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = 
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcChangeEstimatePrice", new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class, double.class, double.class,
                        double.class, boolean.class },
                 l_ifoEstimateDeliveryAmountCalcResult);
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, 
                        Object[].class, OrderTypeEnum.class,boolean.class},
                        l_tpResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getExpirationDate",
                    new Class[] {Date.class, String.class, String.class, String.class},
                    WEB3DateUtility.getDate("20040717", "yyyyMMdd"));
            WEB3FuturesOpenMarginChangeConfirmResponse l_response = 
                this.l_response = this.l_service.validateOrder(this.l_request);
            
            assertEquals(WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"), 
                    "20040717");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * create新規建訂正内容の引数設定
     */
    public void testSubmitOrder_0002()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.l_completeRequest.id = "33381"; 
            this.l_completeRequest.limitPrice = "0";
            l_request.expirationDate = WEB3DateUtility.getDate("20040717", "yyyyMMdd");
            this.initData();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "validateFuturesChangeOrder", 
                new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class },
                new OrderValidationResult(
                        ProcessingResult.newSuccessResultInstance()));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "orderChannel");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},new Long(101001010010L));
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = 
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcChangeEstimatePrice", new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class, double.class, double.class,
                        double.class, boolean.class },
                 l_ifoEstimateDeliveryAmountCalcResult);
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, 
                        Object[].class, OrderTypeEnum.class,boolean.class},
                        l_tpResult);
            OrderSubmissionResult l_orderSubmissionResult = 
                new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "submitChangeOrder", new Class[]
                    { SubAccount.class, ChangeOrderSpec.class, String.class, boolean.class },
                    l_orderSubmissionResult);
            
            this.l_completeResponse = this.l_service.submitOrder(this.l_completeRequest);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * is予約注文確認要 = true
     *
     */
    public void testSubmitOrder_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.l_completeRequest.id = "33381"; 
            this.l_completeRequest.limitPrice = "0";
            l_request.expirationDate = WEB3DateUtility.getDate("20040717", "yyyyMMdd");
            this.initData();
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getRsvIfoOrderUnitRow());
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "validateFuturesChangeOrder", 
                new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class },
                new OrderValidationResult(
                        ProcessingResult.newSuccessResultInstance()));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "orderChannel");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},new Long(101001010010L));
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = 
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcChangeEstimatePrice", new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class, double.class, double.class,
                        double.class, boolean.class },
                 l_ifoEstimateDeliveryAmountCalcResult);
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, 
                        Object[].class, OrderTypeEnum.class,boolean.class},
                        l_tpResult);
            OrderSubmissionResult l_orderSubmissionResult = 
                new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "submitChangeOrder", new Class[]
                    { SubAccount.class, ChangeOrderSpec.class, String.class, boolean.class },
                    l_orderSubmissionResult);
            
            this.l_completeResponse = this.l_service.submitOrder(this.l_completeRequest);
            
            assertNotNull(this.l_completeResponse);
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisRsvIfoOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    RsvIfoOrderUnitRow.TYPE,
                    " parent_order_id = ?",
                    new Object[]{new Long(1001)});
            // setOrderOpenStatus
            assertEquals(OrderOpenStatusEnum.OPEN,((RsvIfoOrderUnitRow)l_lisRsvIfoOrderUnitRows.get(0)).getOrderOpenStatus());
            
            List l_lisRsvIfoOrderActionRows =
                l_queryProcessor.doFindAllQuery(
                    RsvIfoOrderActionRow.TYPE,
                    "order_id = ?",
                    new Object[]{new Long(1001)});
            
            assertEquals(0,l_lisRsvIfoOrderActionRows.size());

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * is予約注文確認要 = false
     *
     */
    public void testSubmitOrder_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.l_completeRequest.id = "33381"; 
            this.l_completeRequest.limitPrice = "0";
            l_request.expirationDate = WEB3DateUtility.getDate("20040717", "yyyyMMdd");
            this.l_blnIsReserveOrderExist = false;
            this.initData();
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getRsvIfoOrderUnitRow());
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "validateFuturesChangeOrder", 
                new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class },
                new OrderValidationResult(
                        ProcessingResult.newSuccessResultInstance()));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "orderChannel");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},new Long(101001010010L));
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = 
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcChangeEstimatePrice", new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class, double.class, double.class,
                        double.class, boolean.class },
                 l_ifoEstimateDeliveryAmountCalcResult);
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, 
                        Object[].class, OrderTypeEnum.class,boolean.class},
                        l_tpResult);
            OrderSubmissionResult l_orderSubmissionResult = 
                new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "submitChangeOrder", new Class[]
                    { SubAccount.class, ChangeOrderSpec.class, String.class, boolean.class },
                    l_orderSubmissionResult);
            
            this.l_completeResponse = this.l_service.submitOrder(this.l_completeRequest);
            
            assertNotNull(this.l_completeResponse);
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisRsvIfoOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    RsvIfoOrderUnitRow.TYPE,
                    " parent_order_id = ?",
                    new Object[]{new Long(1001)});
            // setOrderOpenStatus
            assertEquals(OrderOpenStatusEnum.OPEN,((RsvIfoOrderUnitRow)l_lisRsvIfoOrderUnitRows.get(0)).getOrderOpenStatus());
            
            List l_lisRsvIfoOrderActionRows =
                l_queryProcessor.doFindAllQuery(
                    RsvIfoOrderActionRow.TYPE,
                    "order_id = ?",
                    new Object[]{new Long(1001)});
            
            assertEquals(0,l_lisRsvIfoOrderActionRows.size());

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    
    public class WEB3FuturesOpenMarginChangeConfirmRequestForTest extends WEB3FuturesOpenMarginChangeConfirmRequest
    {
        public void validate() throws WEB3BaseException
        {
            log.debug("WEB3FuturesOpenMarginChangeConfirmRequestForTest.validate()");
        }
    }
    
//    public class WEB3FuturesChangeOpenContractServiceImplForTest extends WEB3FuturesChangeOpenContractServiceImpl
//    {
//        public SubAccount getSubAccount() throws WEB3BaseException
//        {
//            return null;
//        }
//    }
    
    public class WEB3FuturesOpenMarginChangeCompleteRequestForTest extends WEB3FuturesOpenMarginChangeCompleteRequest
    {
        public void validate() throws WEB3BaseException
        {
            log.debug("WEB3FuturesOpenMarginChangeCompleteRequestForTest.validate()");
        }
    }
    
    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            //TradingTimeRow
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("1");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            //IfoOrderRow
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(33381L);
            l_ifoOrderParams.setAccountId(123333812512238L);
            l_ifoOrderParams.setSubAccountId(33381251223801L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.OTHER);
            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            //IfoOrderUnitRow
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(33381L);
            l_ifoOrderUnitParams.setAccountId(101001010010L);
            l_ifoOrderUnitParams.setSubAccountId(10100101001007L);
            l_ifoOrderUnitParams.setBranchId(33381);
            l_ifoOrderUnitParams.setTraderId(null);
            l_ifoOrderUnitParams.setOrderId(33381L);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
            l_ifoOrderUnitParams.setLastExecutionSerialNo(0);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.OTHER);
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            l_ifoOrderUnitParams.setMarketId(3303L);
            l_ifoOrderUnitParams.setQuantity(100);
            l_ifoOrderUnitParams.setLimitPrice(0);
            l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.NONE);
            l_ifoOrderUnitParams.setOrderConditionType("0");
            l_ifoOrderUnitParams.setOrderCondOperator(null);
            l_ifoOrderUnitParams.setStopPriceType(null);
            l_ifoOrderUnitParams.setStopOrderPrice(null);
            l_ifoOrderUnitParams.setWLimitPrice(0);
            l_ifoOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_ifoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
            l_ifoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_ifoOrderUnitParams.setBizDate("20040101");
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setOrderRequestNumber("000003006");
            l_ifoOrderUnitParams.setConfirmedOrderRev("2");
            l_ifoOrderUnitParams.setOrderRev("1");
            // 予約注文の設定
            if(l_blnIsReserveOrderExist)
            {
                l_ifoOrderUnitParams.setReserveOrderExistFlag("0"); 
            }
            else
            {
                l_ifoOrderUnitParams.setReserveOrderExistFlag("1");   
            }
            
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            //l_ifoProductParams.setProductCode("0005");
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setTargetMarketId(3303L);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080000L);
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(330304148080000L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            Calendar ca =  Calendar.getInstance();
            ca.add(Calendar.DATE, 1);
            Date date = ca.getTime();
            l_ifoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(date, "yyyyMMdd"));
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(3304148080000L);
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080000L);
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate); 

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.56.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityChangeOrderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.service.delegate.stdimpls;

import java.util.Calendar;

import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;

import test.util.TestDBUtility;

import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.message.WEB3EquityChangeCompleteRequest;
import webbroker3.equity.message.WEB3EquityChangeCompleteResponse;
import webbroker3.equity.message.WEB3EquityChangeConfirmRequest;
import webbroker3.equity.message.WEB3EquityChangeConfirmResponse;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author âΩï∂ïq(íÜêu)
 * @@version 1.0
 */
public class WEB3EquityChangeOrderServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityChangeOrderServiceImplTest.class);
    WEB3EquityChangeOrderServiceImpl l_impl = new WEB3EquityChangeOrderServiceImpl();
    public WEB3EquityChangeOrderServiceImplTest(String arg0)
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

    public void testValidateChangeOrder_Case0001()
    {
        final String STR_METHOD_NAME = "testValidateChangeOrder_Case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3EquityChangeConfirmRequest l_request =
                new WEB3EquityChangeConfirmRequest();
            WEB3MarginCloseMarginContractUnit l_unit =
                new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "123456";
            l_unit.orderQuantity = "100";
            l_request.orderPriceDiv = "0";
            l_unit.settlePriority = "123";
            l_request.orderCondType = "0";
            l_request.priceCondType = "0";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "1";
            l_request.execCondType = "1";
            l_request.stopOrderCondPrice = "15";
            l_request.stopOrderCondOperator = "1";
            l_request.id = "123456789";
            l_request.orderQuantity = "10";
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setProductId(12342);
            l_eqtypeOrderUnitParams.setMarketId(33381);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(12342);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(12342);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setProductId(12342L);
            l_tradedProductParams.setMarketId(33381L);
            l_tradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            l_eqtypeTradedProductParams.setProductId(12342L);
            l_eqtypeTradedProductParams.setMarketId(33381L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeparams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeparams.setMarketCode("SP");
            l_tradingTimeparams.setBranchCode("123");
            l_tradingTimeparams.setBizDateType("1");
            l_tradingTimeparams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeparams);
            
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setOrderBizDate(GtlUtils.getSystemTimestamp());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
        
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "12");
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeOrderValidationResult l_result = new EqTypeOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "validateChangeOrder", new Class[]
                    { SubAccount.class, EqTypeChangeOrderSpec.class, boolean.class},
                    l_result);
            
            LoginInfo l_info = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_info);
            
            WEB3TPTradingPowerResult l_powerResult = new WEB3TPTradingPowerResult();
            l_powerResult.setAttentionObjectionType("1");
            l_powerResult.setLackAccountBalance(999);
            l_powerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_powerResult);
            
            
            WEB3EquityEstimatedDeliveryPrice l_price = new WEB3EquityEstimatedDeliveryPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                            WEB3GentradeCommission.class, double.class,
                            double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                            String.class,
                            String.class,
                            String.class,
                            boolean.class,
                            SubAccount.class,
                            WEB3EquityTradedProduct.class,
                            double.class,
                            boolean.class,
                            double.class,
                            double.class,
                            boolean.class
                            },
                            l_price);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(Calendar.getInstance().getTime());
            WEB3EquityChangeConfirmResponse l_response = (WEB3EquityChangeConfirmResponse)l_impl.validateChangeOrder(l_request);
      
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "validateChangeOrder", new Class[]
                             { SubAccount.class, EqTypeChangeOrderSpec.class, boolean.class});
           assertEquals(333812512246L,((SubAccount)l_paramsValue2.getFirstCalled()[0]).getAccountId());
           assertEquals(123456789L,((EqTypeChangeOrderSpec)l_paramsValue2.getFirstCalled()[1]).getOrderId());
           assertFalse(((Boolean)l_paramsValue2.getFirstCalled()[2]).booleanValue());

            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class});
           assertEquals(333812512246L, ((WEB3GentradeSubAccount)l_paramsValue3.getFirstCalled()[0]).getAccountId());
           assertEquals(Object[].class, l_paramsValue3.getFirstCalled()[1].getClass());
           assertEquals(Object[].class, l_paramsValue3.getFirstCalled()[2].getClass());
           assertEquals(OrderTypeEnum.class, l_paramsValue3.getFirstCalled()[3].getClass());

            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue("webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                            WEB3GentradeCommission.class, double.class,
                            double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                            String.class,
                            String.class,
                            String.class,
                            boolean.class,
                            SubAccount.class,
                            WEB3EquityTradedProduct.class,
                            double.class,
                            boolean.class,
                            double.class,
                            double.class,
                            boolean.class
                            });
            
           assertEquals(33381L, ((WEB3GentradeCommission)l_paramsValue4.getFirstCalled()[0]).getBranchId());
           assertEquals(new Double(0.0), l_paramsValue4.getFirstCalled()[1]);
           assertEquals(new Double(0.0), l_paramsValue4.getFirstCalled()[2]);
           assertEquals(null, l_paramsValue4.getFirstCalled()[5]);
           assertEquals("0", l_paramsValue4.getFirstCalled()[6]);
           assertEquals("1", l_paramsValue4.getFirstCalled()[7]);
           assertEquals( null,l_paramsValue4.getFirstCalled()[8]);
           assertEquals(new Double(10.0), l_paramsValue4.getFirstCalled()[12]);
           assertFalse(((Boolean)l_paramsValue4.getFirstCalled()[13]).booleanValue());
           assertEquals(new Double(500.0), l_paramsValue4.getFirstCalled()[14]);
           assertEquals(new Double(2000.0), l_paramsValue4.getFirstCalled()[15]);
            
            
//            log.debug("***************** expirationDate = " + l_response.expirationDate);
//            assertNull(l_response.expirationDate);
           
           assertEquals("1", l_response.attentionObjectionType);
           assertEquals("999", l_response.accountBalanceInsufficiency);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrder_Case0001()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_Case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3EquityChangeCompleteRequest l_request =
                new WEB3EquityChangeCompleteRequest();
            WEB3MarginCloseMarginContractUnit l_unit =
                new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "123456";
            l_unit.orderQuantity = "100";
            l_request.orderPriceDiv = "0";
            l_unit.settlePriority = "123";
            l_request.orderCondType = "0";
            l_request.priceCondType = "0";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "1";
            l_request.execCondType = "1";
            l_request.stopOrderCondPrice = "15";
            l_request.stopOrderCondOperator = "1";
            l_request.id = "123456789";
            l_request.orderQuantity = "10";
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setProductId(12342);
            l_eqtypeOrderUnitParams.setMarketId(33381);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(12342);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(12342);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setProductId(12342L);
            l_tradedProductParams.setMarketId(33381L);
            l_tradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            l_eqtypeTradedProductParams.setProductId(12342L);
            l_eqtypeTradedProductParams.setMarketId(33381L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeparams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeparams.setMarketCode("SP");
            l_tradingTimeparams.setBranchCode("123");
            l_tradingTimeparams.setBizDateType("1");
            l_tradingTimeparams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeparams);
            
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setOrderBizDate(GtlUtils.getSystemTimestamp());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
        
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "12");
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeOrderValidationResult l_result = new EqTypeOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "validateChangeOrder", new Class[]
                    { SubAccount.class, EqTypeChangeOrderSpec.class, boolean.class},
                    l_result);
            
            EqTypeOrderSubmissionResult l_submitResult = new EqTypeOrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "submitChangeOrder", new Class[]
                    { SubAccount.class, EqTypeChangeOrderSpec.class, String.class, boolean.class },
                    l_submitResult);
            
            LoginInfo l_info = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_info);
            
            WEB3TPTradingPowerResult l_powerResult = new WEB3TPTradingPowerResult();
            l_powerResult.setAttentionObjectionType("1");
            l_powerResult.setLackAccountBalance(999);
            l_powerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_powerResult);
            
            
            WEB3EquityEstimatedDeliveryPrice l_price = new WEB3EquityEstimatedDeliveryPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                            WEB3GentradeCommission.class, double.class,
                            double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                            String.class,
                            String.class,
                            String.class,
                            boolean.class,
                            SubAccount.class,
                            WEB3EquityTradedProduct.class,
                            double.class,
                            boolean.class,
                            double.class,
                            double.class,
                            boolean.class
                            },
                            l_price);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(Calendar.getInstance().getTime());
            WEB3EquityChangeCompleteResponse l_response = (WEB3EquityChangeCompleteResponse)l_impl.submitChangeOrder(l_request);
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "validateChangeOrder", new Class[]
                             { SubAccount.class, EqTypeChangeOrderSpec.class, boolean.class});
            assertEquals(333812512246L,((SubAccount)l_paramsValue2.getFirstCalled()[0]).getAccountId());
            assertEquals(123456789L,((EqTypeChangeOrderSpec)l_paramsValue2.getFirstCalled()[1]).getOrderId());
            assertFalse(((Boolean)l_paramsValue2.getFirstCalled()[2]).booleanValue());

            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class});
            assertEquals(333812512246L, ((WEB3GentradeSubAccount)l_paramsValue3.getFirstCalled()[0]).getAccountId());
            assertEquals(Object[].class, l_paramsValue3.getFirstCalled()[1].getClass());
            assertEquals(Object[].class, l_paramsValue3.getFirstCalled()[2].getClass());
            assertEquals(OrderTypeEnum.class, l_paramsValue3.getFirstCalled()[3].getClass());

            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue("webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                            WEB3GentradeCommission.class, double.class,
                            double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                            String.class,
                            String.class,
                            String.class,
                            boolean.class,
                            SubAccount.class,
                            WEB3EquityTradedProduct.class,
                            double.class,
                            boolean.class,
                            double.class,
                            double.class,
                            boolean.class
                            });
            assertEquals(33381L, ((WEB3GentradeCommission)l_paramsValue4.getFirstCalled()[0]).getBranchId());
            assertEquals(new Double(0.0), l_paramsValue4.getFirstCalled()[1]);
            assertEquals(new Double(0.0), l_paramsValue4.getFirstCalled()[2]);
            assertEquals(null, l_paramsValue4.getFirstCalled()[5]);
            assertEquals("0", l_paramsValue4.getFirstCalled()[6]);
            assertEquals("1", l_paramsValue4.getFirstCalled()[7]);
            assertEquals( null,l_paramsValue4.getFirstCalled()[8]);
            assertEquals(new Double(10.0), l_paramsValue4.getFirstCalled()[12]);
            assertFalse(((Boolean)l_paramsValue4.getFirstCalled()[13]).booleanValue());
            assertEquals(new Double(500.0), l_paramsValue4.getFirstCalled()[14]);
            assertEquals(new Double(2000.0), l_paramsValue4.getFirstCalled()[15]);
            
//            log.debug("***************** expirationDate = " + l_response.expirationDate);
//            assertNull(l_response.expirationDate);
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@

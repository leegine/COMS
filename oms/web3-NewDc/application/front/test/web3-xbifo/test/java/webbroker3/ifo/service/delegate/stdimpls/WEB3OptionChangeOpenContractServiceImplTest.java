head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.48.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionChangeOpenContractServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP訂正新規建サービスImpl
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/11 孟亜南 (中訊)
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductCalendarRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractChangeSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.marketadaptor.WEB3IfoMarketRequestSenderServiceImplTest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeConfirmResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.mock.util.WEB3MockObjectRuntimeException;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * OP訂正新規建サービスImpl
 * @@author 孟亜南
 */
public class WEB3OptionChangeOpenContractServiceImplTest extends TestBaseForMock
{

    public WEB3OptionChangeOpenContractServiceImplTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception 
    {
        TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setTradingTimeType("26");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setStartTime("000000");
        l_tradingTimeParams.setEndTime("235959");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        l_tradingTimeParams.setEnableOrder("0");
        l_tradingTimeParams.setBizdateCalcParameter("1");
        l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        TestDBUtility.deleteAll(TradingTimeRow.TYPE);
        TestDBUtility.insertWithDel(l_tradingTimeParams);
        
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        l_context.setInstitutionCode("0D");
        l_context.setMarketCode("SP");
        l_context.setBranchCode("381");
        l_context.setProductCode("0");
        l_context.setBizDateType("1");
        l_context.setTradingTimeType("26");
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext",
            l_context);
        
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.set(Calendar.YEAR,2007);
        l_calendar.set(Calendar.MONTH,2);
        l_calendar.set(Calendar.DAY_OF_MONTH,14);
        l_calendar.set(Calendar.HOUR_OF_DAY,15);
        l_calendar.set(Calendar.MINUTE,00);
        l_calendar.set(Calendar.SECOND,01);

        Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());
        Timestamp l_tsBizDate1 = new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime());

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate1);
        
        CalendarParams l_calendarParams = new CalendarParams();
        l_calendarParams.setHoliday(l_tsBizDate1);
        l_calendarParams.setBizDateType("1");
        l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        TestDBUtility.deleteAll(CalendarRow.TYPE);
        TestDBUtility.insertWithDel(l_calendarParams);
    }

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3IfoMarketRequestSenderServiceImplTest.class);
    
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    /**
     * validate注文
     */
    public void test_validateOrder_0001()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        final String STR_METHOD_NAME = "test_send_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionChangeOpenContractServiceImpl l_serviceImpl = 
            new WEB3OptionChangeOpenContractServiceImpl();
        WEB3OptionsOpenMarginChangeConfirmRequest l_request = 
            new WEB3OptionsOpenMarginChangeConfirmRequest();
        
        l_request.id = "1001";
        l_request.opOrderQuantity = "123";
        l_request.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
        l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
        l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateChangeOrder", 
                    new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class },
                    new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
                    
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            
            
            IfoOrderUnitParams l_ifoOrderUnitParams = this.ifoOrderUnit();
            
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            l_serviceImpl.validateOrder(l_request);
            fail();
            
        }
        catch(WEB3MockObjectRuntimeException l_ex)
        {
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl", 
                    "validateChangeOrder", 
                    new Class[] {  WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class});
            
            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec = 
                (WEB3IfoOpenContractChangeSpec) l_paramsValue.getFirstCalled()[1];
            
            assertFalse(l_ifoOpenContractChangeSpec.getEveningSessionCarryoverFlag());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
    
    public void test_validateOrder_0002()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        final String STR_METHOD_NAME = "test_send_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        
        WEB3OptionChangeOpenContractServiceImpl l_serviceImpl = 
            new WEB3OptionChangeOpenContractServiceImpl();
        WEB3OptionsOpenMarginChangeConfirmRequest l_request = 
            new WEB3OptionsOpenMarginChangeConfirmRequest();
        
        l_request.id = "1001";
        l_request.opOrderQuantity = "123";
        l_request.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
        l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
        l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
        l_request.expirationDate = WEB3DateUtility.getDate("20070102", "yyyyMMdd");
        
        try
        {
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateChangeOrder", 
                new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class },
                l_result);
           
            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_calcResult.setCalcUnitPrice(0.2D);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class, double.class, SideEnum.class, boolean.class, double.class,
                        double.class, boolean.class },
                        l_calcResult);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            
            
            IfoOrderUnitParams l_ifoOrderUnitParams = this.ifoOrderUnit();
            
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_tradedProductParams.setTradedProductId(1006169090018L);
            l_tradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            IfoTradedProductParams l_ifoYradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoYradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoYradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            TestDBUtility.insertWithDel(l_ifoYradedProductParams);
            
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "1");
            
            LoginInfo l_lifo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_lifo);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(l_traderParams.getLoginId()));
            
            WEB3TPTradingPowerResult l_powerResult = new WEB3TPTradingPowerResult();
            l_powerResult.setAttentionObjectionType("0");
            l_powerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                    Object[].class, OrderTypeEnum.class,boolean.class},
                    l_powerResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getExpirationDate",
                    new Class[] {Date.class, String.class, String.class, String.class},
                    WEB3DateUtility.getDate("20071122", "yyyyMMdd"));
            WEB3IfoTradedProductImplForTest l_tradedProduct = new WEB3IfoTradedProductImplForTest(l_ifoOrderUnitParams.getProductId());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct",
                    new Class[]{ long.class, long.class },
                    l_tradedProduct);
            WEB3OptionsOpenMarginChangeConfirmResponse l_response = l_serviceImpl.validateOrder(l_request);
            assertEquals(WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"), "20071122");
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
    
    /**
     * submit注文
     */
    public void test_submitOrder_0001()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        final String STR_METHOD_NAME = "test_submitOrder_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionChangeOpenContractServiceImpl l_serviceImpl = 
            new WEB3OptionChangeOpenContractServiceImpl();
        WEB3OptionsOpenMarginChangeCompleteRequest l_request = 
            new WEB3OptionsOpenMarginChangeCompleteRequest();
        
        l_request.id = "1001";
        l_request.opOrderQuantity = "123";
        l_request.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
        l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
        l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateChangeOrder", 
                    new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class },
                    new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
                    
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            
            
            IfoOrderUnitParams l_ifoOrderUnitParams = this.ifoOrderUnit();
            
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            l_serviceImpl.submitOrder(l_request);
            fail();
            
        }
        catch(WEB3MockObjectRuntimeException l_ex)
        {
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl", 
                    "validateChangeOrder", 
                    new Class[] {  WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class});
            
            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec = 
                (WEB3IfoOpenContractChangeSpec) l_paramsValue.getFirstCalled()[1];
            
            assertFalse(l_ifoOpenContractChangeSpec.getEveningSessionCarryoverFlag());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
    
    public void test_submitOrder_0002()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        final String STR_METHOD_NAME = "test_submitOrder_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionChangeOpenContractServiceImpl l_serviceImpl = 
            new WEB3OptionChangeOpenContractServiceImpl();
        WEB3OptionsOpenMarginChangeCompleteRequest l_request = 
            new WEB3OptionsOpenMarginChangeCompleteRequest();
        
        l_request.id = "1001";
        l_request.opOrderQuantity = "123";
        l_request.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
        l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
        l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
        l_request.expirationDate = WEB3DateUtility.getDate("20071122", "yyyyMMdd");
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateChangeOrder", 
                    new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class },
                    new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getExpirationDate",
                    new Class[] {Date.class, String.class, String.class, String.class},
                    WEB3DateUtility.getDate("20071122", "yyyyMMdd"));
                    
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            
            
            IfoOrderUnitParams l_ifoOrderUnitParams = this.ifoOrderUnit();
            
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            l_serviceImpl.submitOrder(l_request);
            fail();
            
        }
        catch(WEB3MockObjectRuntimeException l_ex)
        {
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl", 
                    "validateChangeOrder", 
                    new Class[] {  WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class});
            
            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec = 
                (WEB3IfoOpenContractChangeSpec) l_paramsValue.getFirstCalled()[1];
            
            assertTrue(l_ifoOpenContractChangeSpec.getEveningSessionCarryoverFlag());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
    
    /**
     * submit注文
     * リクエストデータ.確認時単価 == nullの場合
         注文単位.getSide() == "買" and リクエストデータ.注文単価(*1) < リクエストデータ.W指値用注文単価(*2)
     */
    public void test_submitOrder_0003()
    {
        final String STR_METHOD_NAME = "test_submitOrder_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3OptionChangeOpenContractServiceImpl l_serviceImpl = 
            new WEB3OptionChangeOpenContractServiceImpl();
        WEB3OptionsOpenMarginChangeCompleteRequestForMock l_request = 
            new WEB3OptionsOpenMarginChangeCompleteRequestForMock();
        

        l_request.id = "1001";
        l_request.opOrderQuantity = "123";
        l_request.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
        l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
        l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
        l_request.expirationDate = WEB3DateUtility.getDate("20071122", "yyyyMMdd");
        l_request.limitPrice = "100";
        l_request.wLimitPrice = "300";
        
        try
        {

            l_request.checkDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateChangeOrder", 
                    new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class },
                    new OrderValidationResult(
                        ProcessingResult.newSuccessResultInstance()));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getExpirationDate",
                    new Class[] {Date.class, String.class, String.class, String.class},
                    WEB3DateUtility.getDate("20071122", "yyyyMMdd"));
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[] {WEB3GentradeCommission.class,
                            double.class,
                        WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class,
                        double.class,
                        SideEnum.class,
                        boolean.class,
                        double.class,
                        double.class,
                        boolean.class},
                        new WEB3IfoEstimateDeliveryAmountCalcResult());
        
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                    "validateTradingPower",
                    new Class[] {
                            WEB3GentradeSubAccount.class,
                            Object[].class, Object[].class,
                            OrderTypeEnum.class,boolean.class},
                            l_tpResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "submitChangeOrder",
                new Class[]{ SubAccount.class, ChangeOrderSpec.class, String.class, boolean.class },
                new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT));
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            
            
            IfoOrderUnitParams l_ifoOrderUnitParams = this.ifoOrderUnit();
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.deleteAll(TradedProductCalendarRow.TYPE);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoTradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            
            l_tradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_tradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(0);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate); 
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams1 = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams1.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams1.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams1.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams1.setMarketId(l_ifoOrderUnitParams.getMarketId());
            Date l_datpreBizDate1 = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate1 = l_format.format(l_datpreBizDate1);
            l_ifoTradedProductUpdqParams1.setValidForBizDate(l_strCreateDate1); 
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams1);
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams2 = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams2.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams2.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams2.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams2.setMarketId(l_ifoOrderUnitParams.getMarketId());
            Date l_datpreBizDate2 = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(2);
            String l_strCreateDate2 = l_format.format(l_datpreBizDate2);
            l_ifoTradedProductUpdqParams2.setValidForBizDate(l_strCreateDate2); 
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams2);
            
            l_serviceImpl.submitOrder(l_request);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[] {WEB3GentradeCommission.class,
                            double.class,
                        WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class,
                        double.class,
                        SideEnum.class,
                        boolean.class,
                        double.class,
                        double.class,
                        boolean.class});
           assertEquals("300.0", l_paramsValue1.getFirstCalled()[1] + "");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submit注文
     * リクエストデータ.確認時単価 == nullの場合
         注文単位.getSide() ！= "買" and リクエストデータ.注文単価(*1) < リクエストデータ.W指値用注文単価以外。
     */
    public void test_submitOrder_0004()
    {
        final String STR_METHOD_NAME = "test_submitOrder_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3OptionChangeOpenContractServiceImpl l_serviceImpl = 
            new WEB3OptionChangeOpenContractServiceImpl();
        WEB3OptionsOpenMarginChangeCompleteRequestForMock l_request = 
            new WEB3OptionsOpenMarginChangeCompleteRequestForMock();
        

        l_request.id = "1001";
        l_request.opOrderQuantity = "123";
        l_request.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
        l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
        l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
        l_request.expirationDate = WEB3DateUtility.getDate("20071122", "yyyyMMdd");
        l_request.limitPrice = "100";
        l_request.wLimitPrice = "300";
        
        try
        {

            l_request.checkDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateChangeOrder", 
                    new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class },
                    new OrderValidationResult(
                        ProcessingResult.newSuccessResultInstance()));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getExpirationDate",
                    new Class[] {Date.class, String.class, String.class, String.class},
                    WEB3DateUtility.getDate("20071122", "yyyyMMdd"));
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[] {WEB3GentradeCommission.class,
                            double.class,
                        WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class,
                        double.class,
                        SideEnum.class,
                        boolean.class,
                        double.class,
                        double.class,
                        boolean.class},
                        new WEB3IfoEstimateDeliveryAmountCalcResult());
        
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                    "validateTradingPower",
                    new Class[] {
                            WEB3GentradeSubAccount.class,
                            Object[].class, Object[].class,
                            OrderTypeEnum.class,boolean.class},
                            l_tpResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "submitChangeOrder",
                new Class[]{ SubAccount.class, ChangeOrderSpec.class, String.class, boolean.class },
                new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT));
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.deleteAll(TradedProductCalendarRow.TYPE);
            
            
            IfoOrderUnitParams l_ifoOrderUnitParams = this.ifoOrderUnit();
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE);
            
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoTradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            
            l_tradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_tradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(0);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate); 
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams1 = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams1.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams1.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams1.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams1.setMarketId(l_ifoOrderUnitParams.getMarketId());
            Date l_datpreBizDate1 = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate1 = l_format.format(l_datpreBizDate1);
            l_ifoTradedProductUpdqParams1.setValidForBizDate(l_strCreateDate1); 
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams1);
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams2 = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams2.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams2.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams2.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams2.setMarketId(l_ifoOrderUnitParams.getMarketId());
            Date l_datpreBizDate2 = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(2);
            String l_strCreateDate2 = l_format.format(l_datpreBizDate2);
            l_ifoTradedProductUpdqParams2.setValidForBizDate(l_strCreateDate2); 
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams2);
            
            l_serviceImpl.submitOrder(l_request);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[] {WEB3GentradeCommission.class,
                            double.class,
                        WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class,
                        double.class,
                        SideEnum.class,
                        boolean.class,
                        double.class,
                        double.class,
                        boolean.class});
           assertEquals("100.0", l_paramsValue1.getFirstCalled()[1] + "");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submit注文
     * リクエストデータ.確認時単価 == nullの場合
     注文単位.getSide() == "買" and リクエストデータ.注文単価(*1)  > リクエストデータ.W指値用注文単価以外。
     */
    public void test_submitOrder_0005()
    {
        final String STR_METHOD_NAME = "test_submitOrder_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3OptionChangeOpenContractServiceImpl l_serviceImpl = 
            new WEB3OptionChangeOpenContractServiceImpl();
        WEB3OptionsOpenMarginChangeCompleteRequestForMock l_request = 
            new WEB3OptionsOpenMarginChangeCompleteRequestForMock();
        

        l_request.id = "1001";
        l_request.opOrderQuantity = "123";
        l_request.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
        l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
        l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
        l_request.expirationDate = WEB3DateUtility.getDate("20071122", "yyyyMMdd");
        l_request.limitPrice = "100";
        l_request.wLimitPrice = "20";
        
        try
        {

            l_request.checkDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateChangeOrder", 
                    new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class },
                    new OrderValidationResult(
                        ProcessingResult.newSuccessResultInstance()));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getExpirationDate",
                    new Class[] {Date.class, String.class, String.class, String.class},
                    WEB3DateUtility.getDate("20071122", "yyyyMMdd"));
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[] {WEB3GentradeCommission.class,
                            double.class,
                        WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class,
                        double.class,
                        SideEnum.class,
                        boolean.class,
                        double.class,
                        double.class,
                        boolean.class},
                        new WEB3IfoEstimateDeliveryAmountCalcResult());
        
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                    "validateTradingPower",
                    new Class[] {
                            WEB3GentradeSubAccount.class,
                            Object[].class, Object[].class,
                            OrderTypeEnum.class,boolean.class},
                            l_tpResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "submitChangeOrder",
                new Class[]{ SubAccount.class, ChangeOrderSpec.class, String.class, boolean.class },
                new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT));
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.deleteAll(TradedProductCalendarRow.TYPE);
            
            
            IfoOrderUnitParams l_ifoOrderUnitParams = this.ifoOrderUnit();
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoTradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            
            l_tradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_tradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(0);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate); 
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams1 = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams1.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams1.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams1.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams1.setMarketId(l_ifoOrderUnitParams.getMarketId());
            Date l_datpreBizDate1 = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate1 = l_format.format(l_datpreBizDate1);
            l_ifoTradedProductUpdqParams1.setValidForBizDate(l_strCreateDate1); 
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams1);
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams2 = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams2.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams2.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams2.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams2.setMarketId(l_ifoOrderUnitParams.getMarketId());
            Date l_datpreBizDate2 = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(2);
            String l_strCreateDate2 = l_format.format(l_datpreBizDate2);
            l_ifoTradedProductUpdqParams2.setValidForBizDate(l_strCreateDate2); 
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams2);
            
            l_serviceImpl.submitOrder(l_request);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[] {WEB3GentradeCommission.class,
                            double.class,
                        WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class,
                        double.class,
                        SideEnum.class,
                        boolean.class,
                        double.class,
                        double.class,
                        boolean.class});
           assertEquals("100.0", l_paramsValue1.getFirstCalled()[1] + "");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submit注文
     * 予約注文確認要（is予約注文確認要() == true）の場合
         レスポンスデータにプロパティをセットする。
     */
    public void test_submitOrder_0006()
    {
        final String STR_METHOD_NAME = "test_submitOrder_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3OptionChangeOpenContractServiceImpl l_serviceImpl = 
            new WEB3OptionChangeOpenContractServiceImpl();
        WEB3OptionsOpenMarginChangeCompleteRequestForMock l_request = 
            new WEB3OptionsOpenMarginChangeCompleteRequestForMock();
        

        l_request.id = "1001";
        l_request.opOrderQuantity = "123";
        l_request.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
        l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
        l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
        l_request.expirationDate = WEB3DateUtility.getDate("20071122", "yyyyMMdd");
        l_request.limitPrice = "100";
        l_request.wLimitPrice = "20";
        
        try
        {

            l_request.checkDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateChangeOrder", 
                    new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class },
                    new OrderValidationResult(
                        ProcessingResult.newSuccessResultInstance()));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getExpirationDate",
                    new Class[] {Date.class, String.class, String.class, String.class},
                    WEB3DateUtility.getDate("20071122", "yyyyMMdd"));
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[] {WEB3GentradeCommission.class,
                            double.class,
                        WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class,
                        double.class,
                        SideEnum.class,
                        boolean.class,
                        double.class,
                        double.class,
                        boolean.class},
                        new WEB3IfoEstimateDeliveryAmountCalcResult());
        
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                    "validateTradingPower",
                    new Class[] {
                            WEB3GentradeSubAccount.class,
                            Object[].class, Object[].class,
                            OrderTypeEnum.class,boolean.class},
                            l_tpResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "submitChangeOrder",
                new Class[]{ SubAccount.class, ChangeOrderSpec.class, String.class, boolean.class },
                new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT));
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.deleteAll(TradedProductCalendarRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = this.ifoOrderUnit();
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setReserveOrderExistFlag("1");
            
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoTradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            
            l_tradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_tradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(0);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate); 
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams1 = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams1.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams1.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams1.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams1.setMarketId(l_ifoOrderUnitParams.getMarketId());
            Date l_datpreBizDate1 = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate1 = l_format.format(l_datpreBizDate1);
            l_ifoTradedProductUpdqParams1.setValidForBizDate(l_strCreateDate1); 
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams1);
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams2 = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams2.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams2.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams2.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams2.setMarketId(l_ifoOrderUnitParams.getMarketId());
            Date l_datpreBizDate2 = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(2);
            String l_strCreateDate2 = l_format.format(l_datpreBizDate2);
            l_ifoTradedProductUpdqParams2.setValidForBizDate(l_strCreateDate2); 
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams2);

            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setParentOrderId(l_ifoOrderUnitParams.getOrderId());
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);

            WEB3OptionsOpenMarginChangeCompleteResponse l_response = l_serviceImpl.submitOrder(l_request);

           assertEquals(0, WEB3DateUtility.compareToDay(
               l_response.lastUpdatedTimestamp, GtlUtils.getSystemTimestamp()));
           
           assertTrue(l_response.succSettingFlag);
           
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submit注文
     * 予約注文確認要（is予約注文確認要() == false）の場合
         レスポンスデータにプロパティをセットする。
     */
    public void test_submitOrder_0007()
    {
        final String STR_METHOD_NAME = "test_submitOrder_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3OptionChangeOpenContractServiceImpl l_serviceImpl = 
            new WEB3OptionChangeOpenContractServiceImpl();
        WEB3OptionsOpenMarginChangeCompleteRequestForMock l_request = 
            new WEB3OptionsOpenMarginChangeCompleteRequestForMock();
        

        l_request.id = "1001";
        l_request.opOrderQuantity = "123";
        l_request.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
        l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
        l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
        l_request.expirationDate = WEB3DateUtility.getDate("20071122", "yyyyMMdd");
        l_request.limitPrice = "100";
        l_request.wLimitPrice = "20";
        
        try
        {

            l_request.checkDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateChangeOrder", 
                    new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class },
                    new OrderValidationResult(
                        ProcessingResult.newSuccessResultInstance()));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getExpirationDate",
                    new Class[] {Date.class, String.class, String.class, String.class},
                    WEB3DateUtility.getDate("20071122", "yyyyMMdd"));
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[] {WEB3GentradeCommission.class,
                            double.class,
                        WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class,
                        double.class,
                        SideEnum.class,
                        boolean.class,
                        double.class,
                        double.class,
                        boolean.class},
                        new WEB3IfoEstimateDeliveryAmountCalcResult());
        
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                    "validateTradingPower",
                    new Class[] {
                            WEB3GentradeSubAccount.class,
                            Object[].class, Object[].class,
                            OrderTypeEnum.class,boolean.class},
                            l_tpResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "submitChangeOrder",
                new Class[]{ SubAccount.class, ChangeOrderSpec.class, String.class, boolean.class },
                new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT));
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.deleteAll(TradedProductCalendarRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = this.ifoOrderUnit();
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setReserveOrderExistFlag("0");
            
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoTradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            
            l_tradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_tradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(0);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate); 
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams1 = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams1.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams1.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams1.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams1.setMarketId(l_ifoOrderUnitParams.getMarketId());
            Date l_datpreBizDate1 = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate1 = l_format.format(l_datpreBizDate1);
            l_ifoTradedProductUpdqParams1.setValidForBizDate(l_strCreateDate1); 
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams1);
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams2 = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams2.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams2.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams2.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams2.setMarketId(l_ifoOrderUnitParams.getMarketId());
            Date l_datpreBizDate2 = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(2);
            String l_strCreateDate2 = l_format.format(l_datpreBizDate2);
            l_ifoTradedProductUpdqParams2.setValidForBizDate(l_strCreateDate2); 
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams2);

            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setParentOrderId(l_ifoOrderUnitParams.getOrderId());
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);

            WEB3OptionsOpenMarginChangeCompleteResponse l_response = l_serviceImpl.submitOrder(l_request);

           assertEquals(0, WEB3DateUtility.compareToDay(
               l_response.lastUpdatedTimestamp, GtlUtils.getSystemTimestamp()));
           
           assertFalse(l_response.succSettingFlag);
           
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public IfoOrderUnitParams ifoOrderUnit()
    {
        IfoOrderUnitParams l_params = new IfoOrderUnitParams();
        l_params.setOrderUnitId(1001);
        l_params.setAccountId(101001010010L);
        l_params.setSubAccountId(10100101001007L);
        l_params.setBranchId(33381);
        l_params.setTraderId(null);
        l_params.setOrderId(1001);
        l_params.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
        l_params.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
        l_params.setLastOrderActionSerialNo(1);
        l_params.setLastExecutionSerialNo(0);
        l_params.setProductType(ProductTypeEnum.IFO);
        l_params.setFutureOptionDiv("1");
        l_params.setMarketId(1002);
        l_params.setQuantity(100);
        l_params.setLimitPrice(0);
        l_params.setExecutionConditionType(IfoOrderExecutionConditionType.NONE);
        l_params.setOrderConditionType("2");
        l_params.setOrderCondOperator(null);
        l_params.setStopPriceType(null);
        l_params.setStopOrderPrice(null);
        l_params.setWLimitPrice(null);
        l_params.setDeliveryDate(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
        l_params.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_params.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        l_params.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_params.setTaxType(TaxTypeEnum.NORMAL);
        l_params.setBizDate("20040101");
        l_params.setProductId(1006169090018L);
        l_params.setCreatedTimestamp(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
        l_params.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
        l_params.setOrderRequestNumber("000003006");
        l_params.setConfirmedOrderRev("2");
        l_params.setOrderRev("1");
        
        l_params.setConfirmedExecConditionType(IfoOrderExecutionConditionType.NONE);
        l_params.setConfirmedQuantity(100);
        return l_params;
    }
    
    public class WEB3OptionsOpenMarginChangeCompleteRequestForMock 
            extends WEB3OptionsOpenMarginChangeCompleteRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "WEB3OptionsOpenMarginChangeCompleteRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    
    private class WEB3IfoTradedProductImplForTest extends WEB3IfoTradedProductImpl
    {

        public WEB3IfoTradedProductImplForTest(long l_lngTradedProductID) throws DataQueryException, DataNetworkException, DataFindException
        {
            super(l_lngTradedProductID);
            // TODO Auto-generated constructor stub
        }
        
    }
}
@

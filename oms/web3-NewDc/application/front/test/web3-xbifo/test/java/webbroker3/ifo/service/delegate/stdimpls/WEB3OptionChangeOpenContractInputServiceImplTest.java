head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.47.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionChangeOpenContractInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP訂正新規建入力サービスImpl
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/14 孟亜南 (中訊)
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
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
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;
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
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CarriedOrderDef;
import webbroker3.common.define.WEB3OrderexecutionEndTypeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionClientRequestService;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeInputRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeInputResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.quoteadaptor.AskPriceTitle;
import webbroker3.quoteadaptor.BidPriceTitle;
import webbroker3.quoteadaptor.CurrentPriceFlag;
import webbroker3.quoteadaptor.DataType;
import webbroker3.quoteadaptor.PutAndCall;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * OP訂正新規建入力サービスImpl
 * @@author 孟亜南
 */
public class WEB3OptionChangeOpenContractInputServiceImplTest extends TestBaseForMock
{

    public WEB3OptionChangeOpenContractInputServiceImplTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception 
    {

    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
                 WEB3OptionChangeOpenContractInputServiceImplTest.class);
    

    /**
     * 1、set取引最終日 <= 現在日付の場合、
     *    is出来るまで注文取扱可能<取引最終日考慮> = false　@かつ　@
     *    get出来るまで注文開始日( ) = null かつ 
     *    レスポンス.有効期限開始日 = null
     * 2、レスポンス.注文期限区分＝先物OPデータアダプタ.get注文期限区分(注文単位)の戻り値( = 1)の場合、
     *    レスポンス.注文有効期限 = null
     */
    public void test_execute_0001()
    {
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        final String STR_METHOD_NAME = "test_execute_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionChangeOpenContractInputServiceImpl l_serviceImpl =
            new WEB3OptionChangeOpenContractInputServiceImpl();
        
        WEB3OptionsOpenMarginChangeInputRequest l_request = new WEB3OptionsOpenMarginChangeInputRequest();
        l_request.id = "1001";
        
        try
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
            
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(OrderexecutionEndParams.TYPE);
            
            InstitutionParams l_institutionParams = getInstitutionRow();
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            IfoOrderParams l_ifoOrderParams = getIfoOrderRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = ifoOrderUnit();
            l_ifoOrderUnitParams.setSessionType("5");
            l_ifoOrderUnitParams.setLimitPrice(0);
            ProductParams l_productParams = getProductRow();
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            MarketParams l_marketParams = getMarketRow();
            EnableOrderConditionParams l_enableOrderConditionParams = getEnableOrderConditionParamsRow();
            
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();
            
            
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setNameSerialNo(2);
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("evening.session.div");
            
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("1");
            l_orderexecutionEndParams.setOrderexecutionEndType(WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END);
            
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            
            
            
            
            IfoTradedProductImplForTest l_ifoTradedProductImpl = null;
            try
            {
                l_ifoTradedProductImpl = new IfoTradedProductImplForTest();
            }
            catch (Exception e)
            {
                fail();
            }
            
            String[] l_str = new String[]{"3"};
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder",
                    new Class[] {SubAccount.class, String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateHandlingOpenContractOrder", 
                    new Class[]
                    { SubAccount.class, boolean.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability",
                    new Class[] {Order.class, boolean.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateMarketID",
                    new Class[]{long.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[]{WEB3IfoProductImpl.class, WEB3GentradeMarket.class,
                        boolean.class, boolean.class},
                        l_ifoTradedProductImpl);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getHandlingPossibleExecConds", new Class[]
                    { String[].class, String[].class },
                    l_str);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        WEB3GenResponse l_response = null;
        try
        {
            WEB3IfoQuoteDataImplForTest l_ifoQuoteData = new WEB3IfoQuoteDataImplForTest();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getIfoQuote",
                    new Class[]
                    {IfoTradedProduct.class,RealType.class},
                    l_ifoQuoteData);
                    
            l_response = l_serviceImpl.execute(l_request);
            
            WEB3OptionsOpenMarginChangeInputResponse l_response1 =
                (WEB3OptionsOpenMarginChangeInputResponse)l_response;
            
            assertEquals("0",l_response1.orderPriceDivList[0]);
            //レスポンス.有効期限開始日
            assertNull(l_response1.expirationStartDate);
            //レスポンス.注文期限区分
            assertEquals("1",l_response1.expirationDateType);
            //レスポンス.注文有効期限
            assertNull(l_response1.expirationDate);
            //レスポンス.執行条件一覧
            assertEquals("3",l_response1.execCondList[0]);
            
            assertEquals("5",l_response1.sessionType);
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
    }
    
    /**
     * 1、set取引最終日 > 現在日付の場合、
     *    is出来るまで注文取扱可能<取引最終日考慮> = true　@かつ　@
     *    get出来るまで注文開始日( ) != null かつ 
     *    レスポンス.有効期限開始日 != null
     * 2、レスポンス.注文期限区分＝先物OPデータアダプタ.get注文期限区分(注文単位)の戻り値( = 2)の場合、
     *    レスポンス.注文有効期限 = expiration_date
     */
    public void test_execute_0002()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        final String STR_METHOD_NAME = "test_execute_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionChangeOpenContractInputServiceImpl l_serviceImpl =
            new WEB3OptionChangeOpenContractInputServiceImpl();
        
        WEB3OptionsOpenMarginChangeInputRequest l_request = new WEB3OptionsOpenMarginChangeInputRequest();
        l_request.id = "1001";
        
        try
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
            
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(OrderexecutionEndParams.TYPE);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            
            InstitutionParams l_institutionParams = getInstitutionRow();
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            IfoOrderParams l_ifoOrderParams = getIfoOrderRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = this.ifoOrderUnit();
            //
            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
            l_ifoOrderUnitParams.setSessionType("1");
            l_ifoOrderUnitParams.setLimitPrice(10);
//            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
//            l_ifoOrderUnitParams.setOrderConditionType("1");
            ProductParams l_productParams = getProductRow();
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            MarketParams l_marketParams = getMarketRow();
            EnableOrderConditionParams l_enableOrderConditionParams = getEnableOrderConditionParamsRow();
            
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();
 
            
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setNameSerialNo(2);
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("evening.session.div");
            
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("1");
            l_orderexecutionEndParams.setOrderexecutionEndType(WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END);
            
            
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            
            IfoTradedProductImplAForTest l_ifoTradedProductImpl = null;
            try
            {
                l_ifoTradedProductImpl = new IfoTradedProductImplAForTest();
            }
            catch (Exception e)
            {
                fail();
            }
            
            String[] l_str = new String[]{"2"};
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder",
                    new Class[] {SubAccount.class, String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateHandlingOpenContractOrder", 
                    new Class[]
                    { SubAccount.class, boolean.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability",
                    new Class[] {Order.class, boolean.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateMarketID",
                    new Class[]{long.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[]{WEB3IfoProductImpl.class, WEB3GentradeMarket.class,
                        boolean.class, boolean.class},
                        l_ifoTradedProductImpl);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getHandlingPossibleExecConds", new Class[]
                    { String[].class, String[].class },
                    l_str);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        WEB3GenResponse l_response = null;
        try
        {
            WEB3IfoQuoteDataImplForTest l_ifoQuoteData = new WEB3IfoQuoteDataImplForTest();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getIfoQuote",
                    new Class[]
                    {IfoTradedProduct.class,RealType.class},
                    l_ifoQuoteData);
                    
            l_response = l_serviceImpl.execute(l_request);
            
            WEB3OptionsOpenMarginChangeInputResponse l_response1 =
                (WEB3OptionsOpenMarginChangeInputResponse)l_response;
            
            assertEquals("1",l_response1.orderPriceDivList[0]);
            //レスポンス.有効期限開始日
            assertEquals("0","" + WEB3DateUtility.compareToDay(
                    WEB3DateUtility.getDate("20070810","yyyyMMdd"),l_response1.expirationStartDate));
            //レスポンス.注文期限区分
            assertEquals("2",l_response1.expirationDateType);
            //レスポンス.注文有効期限
            assertEquals("0","" + WEB3DateUtility.compareToDay(
                    WEB3DateUtility.getDate("20040101", "yyyyMMdd"),l_response1.expirationDate));
        }
        catch (WEB3BaseException e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * is夕場まで注文 = true
     * 
     */
    public void test_execute_0003()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        final String STR_METHOD_NAME = "test_execute_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionChangeOpenContractInputServiceImpl l_serviceImpl =
            new WEB3OptionChangeOpenContractInputServiceImpl();
        
        WEB3OptionsOpenMarginChangeInputRequest l_request = new WEB3OptionsOpenMarginChangeInputRequest();
        l_request.id = "1001";
        
        try
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
            
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(OrderexecutionEndParams.TYPE);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            
            InstitutionParams l_institutionParams = getInstitutionRow();
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            IfoOrderParams l_ifoOrderParams = getIfoOrderRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = ifoOrderUnit();
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setSessionType("1");
            ProductParams l_productParams = getProductRow();
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            MarketParams l_marketParams = getMarketRow();
            EnableOrderConditionParams l_enableOrderConditionParams = getEnableOrderConditionParamsRow();
            
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();
            
            
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setNameSerialNo(2);
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("evening.session.div");
            
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("1");
            l_orderexecutionEndParams.setOrderexecutionEndType(WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END);
            
            
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            
            IfoTradedProductImplForTest l_ifoTradedProductImpl = null;
            try
            {
                l_ifoTradedProductImpl = new IfoTradedProductImplForTest();
            }
            catch (Exception e)
            {
                fail();
            }
            
            String[] l_str = new String[]{"4"};
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder",
                    new Class[] {SubAccount.class, String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateHandlingOpenContractOrder", 
                    new Class[]
                    { SubAccount.class, boolean.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability",
                    new Class[] {Order.class, boolean.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateMarketID",
                    new Class[]{long.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[]{WEB3IfoProductImpl.class, WEB3GentradeMarket.class,
                        boolean.class, boolean.class},
                        l_ifoTradedProductImpl);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getHandlingPossibleExecConds", new Class[]
                    { String[].class, String[].class },
                    l_str);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        WEB3GenResponse l_response = null;
        try
        {
            WEB3IfoQuoteDataImplForTest l_ifoQuoteData = new WEB3IfoQuoteDataImplForTest();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getIfoQuote",
                    new Class[]
                    {IfoTradedProduct.class,RealType.class},
                    l_ifoQuoteData);
                    
            l_response = l_serviceImpl.execute(l_request);
            
            WEB3OptionsOpenMarginChangeInputResponse l_response1 =
                (WEB3OptionsOpenMarginChangeInputResponse)l_response;
            
            //レスポンス.有効期限開始日
            assertNull(l_response1.expirationStartDate);
            //レスポンス.注文期限区分
            assertEquals("3",l_response1.expirationDateType);
            //レスポンス.注文有効期限
            assertNull(l_response1.expirationDate);
            //レスポンス.執行条件一覧
            assertEquals("1",l_response1.execCondList[0]);
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
    }
    
    
    /**
     * 777
     */
    public void test_execute_0004()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        final String STR_METHOD_NAME = "test_execute_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionChangeOpenContractInputServiceImpl l_serviceImpl =
            new WEB3OptionChangeOpenContractInputServiceImpl();
        
        WEB3OptionsOpenMarginChangeInputRequest l_request = new WEB3OptionsOpenMarginChangeInputRequest();
        l_request.id = "1001";
        
        try
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
            l_context.setBizDateType("0");
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
            WEB3GentradeBizDate l_bizDate = new WEB3GentradeBizDate(l_tsBizDate1);
            Timestamp l_tsBizDate11 = l_bizDate.roll(1);
            
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate1);
            
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(l_tsBizDate11);
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            TestDBUtility.insertWithDel(l_calendarParams);
            
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(OrderexecutionEndParams.TYPE);
            
            InstitutionParams l_institutionParams = getInstitutionRow();
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            IfoOrderParams l_ifoOrderParams = getIfoOrderRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = ifoOrderUnit();
            l_ifoOrderUnitParams.setSessionType("5");
            l_ifoOrderUnitParams.setFirstOrderUnitId(1001);
            ProductParams l_productParams = getProductRow();
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            MarketParams l_marketParams = getMarketRow();
            EnableOrderConditionParams l_enableOrderConditionParams = getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("");
            l_enableOrderConditionParams.setCarriedOrder(WEB3CarriedOrderDef.CAN_NOT_DEALT);
            
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();
            
            
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setNameSerialNo(2);
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("evening.session.div");
            
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("1");
            l_orderexecutionEndParams.setOrderexecutionEndType(WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END);
            
            
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            
            IfoTradedProductImplForTest l_ifoTradedProductImpl = null;
            try
            {
                l_ifoTradedProductImpl = new IfoTradedProductImplForTest();
            }
            catch (Exception e)
            {
                fail();
            }
            
            String[] l_str = new String[]{"3"};
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder",
                    new Class[] {SubAccount.class, String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateHandlingOpenContractOrder", 
                    new Class[]
                    { SubAccount.class, boolean.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability",
                    new Class[] {Order.class, boolean.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateMarketID",
                    new Class[]{long.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[]{WEB3IfoProductImpl.class, WEB3GentradeMarket.class,
                        boolean.class, boolean.class},
                        l_ifoTradedProductImpl);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getHandlingPossibleExecConds", new Class[]
                    { String[].class, String[].class },
                    l_str);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        WEB3GenResponse l_response = null;
        try
        {
            WEB3IfoQuoteDataImplForTest l_ifoQuoteData = new WEB3IfoQuoteDataImplForTest();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getIfoQuote",
                    new Class[]
                    {IfoTradedProduct.class,RealType.class},
                    l_ifoQuoteData);
                    
            l_response = l_serviceImpl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00413,e.getErrorInfo());

        }
    }
    
    public void test_execute_0005()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        final String STR_METHOD_NAME = "test_execute_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionChangeOpenContractInputServiceImpl l_serviceImpl =
            new WEB3OptionChangeOpenContractInputServiceImpl();
        
        WEB3OptionsOpenMarginChangeInputRequest l_request = new WEB3OptionsOpenMarginChangeInputRequest();
        l_request.id = "1001";
        
        try
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
            
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(OrderexecutionEndParams.TYPE);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            
            InstitutionParams l_institutionParams = getInstitutionRow();
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            IfoOrderParams l_ifoOrderParams = getIfoOrderRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = this.ifoOrderUnit();
            //
            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
            l_ifoOrderUnitParams.setSessionType("1");
//            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
//            l_ifoOrderUnitParams.setOrderConditionType("1");
            ProductParams l_productParams = getProductRow();
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            MarketParams l_marketParams = getMarketRow();
            EnableOrderConditionParams l_enableOrderConditionParams = getEnableOrderConditionParamsRow();
            
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();
 
            
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setNameSerialNo(2);
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("evening.session.div");
            
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("1");
            l_orderexecutionEndParams.setOrderexecutionEndType(WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END);
            
            
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            
            IfoTradedProductImplAForTest l_ifoTradedProductImpl = null;
            try
            {
                l_ifoTradedProductImpl = new IfoTradedProductImplAForTest();
            }
            catch (Exception e)
            {
                fail();
            }
            
            String[] l_str = new String[]{"2"};
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder",
                    new Class[] {SubAccount.class, String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateHandlingOpenContractOrder", 
                    new Class[]
                    { SubAccount.class, boolean.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability",
                    new Class[] {Order.class, boolean.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateMarketID",
                    new Class[]{long.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[]{WEB3IfoProductImpl.class, WEB3GentradeMarket.class,
                        boolean.class, boolean.class},
                        l_ifoTradedProductImpl);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getHandlingPossibleExecConds", new Class[]
                    { String[].class, String[].class },
                    l_str);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        WEB3GenResponse l_response = null;
        try
        {
            WEB3IfoQuoteDataImplForTest l_ifoQuoteData = new WEB3IfoQuoteDataImplForTest();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getIfoQuote",
                    new Class[]
                    {IfoTradedProduct.class,RealType.class},
                    l_ifoQuoteData);
                    
            l_response = l_serviceImpl.execute(l_request);
            
            WEB3OptionsOpenMarginChangeInputResponse l_response1 =
                (WEB3OptionsOpenMarginChangeInputResponse)l_response;
            
            //レスポンス.有効期限開始日
            assertEquals("0","" + WEB3DateUtility.compareToDay(
                    WEB3DateUtility.getDate("20070810","yyyyMMdd"),l_response1.expirationStartDate));
            //レスポンス.有効期限最終日
            assertEquals("0","" + WEB3DateUtility.compareToDay(
                    WEB3DateUtility.getDate("20070810","yyyyMMdd"),l_response1.expirationEndDate));
        }
        catch (WEB3BaseException e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * create入力画面 
     * validate注文訂正可能抛異常
     */
    public void testCreateInputScreenCase0001()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        final String STR_METHOD_NAME = "testCreateInputScreenCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionChangeOpenContractInputServiceImplForTest l_serviceImpl =
            new WEB3OptionChangeOpenContractInputServiceImplForTest();
        
        WEB3OptionsOpenMarginChangeInputRequest l_request = new WEB3OptionsOpenMarginChangeInputRequest();
        l_request.id = "1001";
        
        try
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
            
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(OrderexecutionEndParams.TYPE);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            
            InstitutionParams l_institutionParams = getInstitutionRow();
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            IfoOrderParams l_ifoOrderParams = getIfoOrderRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = this.ifoOrderUnit();
            //
            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
            l_ifoOrderUnitParams.setSessionType("1");
            ProductParams l_productParams = getProductRow();
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            MarketParams l_marketParams = getMarketRow();
            EnableOrderConditionParams l_enableOrderConditionParams = getEnableOrderConditionParamsRow();
            
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();

            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setNameSerialNo(2);
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("evening.session.div");
            
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("1");
            l_orderexecutionEndParams.setOrderexecutionEndType(WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END);

            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder",
                    new Class[] {SubAccount.class, String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateHandlingOpenContractOrder", 
                    new Class[]
                    { SubAccount.class, boolean.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability",
                    new Class[] {Order.class, boolean.class},
                    null);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        try
        {
            l_serviceImpl.execute(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
            log.exiting(TEST_END + STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * create入力画面 
     *  OP訂正新規建入力サービスimplのcreate入力画面 
     レスポンスデータ注文単価区分にプロパティをセットする
     注文単位.isMarketOrder = true
     */
    public void testCreateInputScreenCase0002()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        final String STR_METHOD_NAME = "testCreateInputScreenCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionChangeOpenContractInputServiceImpl l_serviceImpl =
            new WEB3OptionChangeOpenContractInputServiceImpl();
        
        WEB3OptionsOpenMarginChangeInputRequest l_request = new WEB3OptionsOpenMarginChangeInputRequest();
        l_request.id = "1001";
        
        try
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
            
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(OrderexecutionEndParams.TYPE);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            
            InstitutionParams l_institutionParams = getInstitutionRow();
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            IfoOrderParams l_ifoOrderParams = getIfoOrderRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = this.ifoOrderUnit();
            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
            l_ifoOrderUnitParams.setSessionType("1");
            l_ifoOrderUnitParams.setLimitPrice(null);
            ProductParams l_productParams = getProductRow();
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            MarketParams l_marketParams = getMarketRow();
            EnableOrderConditionParams l_enableOrderConditionParams = getEnableOrderConditionParamsRow();
            
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();
 
            
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setNameSerialNo(2);
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("evening.session.div");
            
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("1");
            l_orderexecutionEndParams.setOrderexecutionEndType(WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END);
            
            
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            
            IfoTradedProductImplAForTest l_ifoTradedProductImpl = null;
            try
            {
                l_ifoTradedProductImpl = new IfoTradedProductImplAForTest();
            }
            catch (Exception e)
            {
                fail();
            }
            
            String[] l_str = new String[]{"2"};
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder",
                    new Class[] {SubAccount.class, String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateHandlingOpenContractOrder", 
                    new Class[]
                    { SubAccount.class, boolean.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability",
                    new Class[] {Order.class, boolean.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateMarketID",
                    new Class[]{long.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[]{WEB3IfoProductImpl.class, WEB3GentradeMarket.class,
                        boolean.class, boolean.class},
                        l_ifoTradedProductImpl);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getHandlingPossibleExecConds", new Class[]
                    { String[].class, String[].class },
                    l_str);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        WEB3GenResponse l_response = null;
        try
        {
            WEB3IfoQuoteDataImplForTest l_ifoQuoteData = new WEB3IfoQuoteDataImplForTest();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getIfoQuote",
                    new Class[]
                    {IfoTradedProduct.class,RealType.class},
                    l_ifoQuoteData);
                    
            l_response = l_serviceImpl.execute(l_request);
            
            WEB3OptionsOpenMarginChangeInputResponse l_response1 =
                (WEB3OptionsOpenMarginChangeInputResponse)l_response;
            
            //レスポンス.注文単価区分
            assertEquals("0", l_response1.orderPriceDiv);
        }
        catch (WEB3BaseException e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * create入力画面 
     *  OP訂正新規建入力サービスimplのcreate入力画面 
     レスポンスデータ注文単価区分にプロパティをセットする
     注文単位.isMarketOrder = false
     */
    public void testCreateInputScreenCase0003()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        final String STR_METHOD_NAME = "testCreateInputScreenCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionChangeOpenContractInputServiceImpl l_serviceImpl =
            new WEB3OptionChangeOpenContractInputServiceImpl();
        
        WEB3OptionsOpenMarginChangeInputRequest l_request = new WEB3OptionsOpenMarginChangeInputRequest();
        l_request.id = "1001";
        
        try
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
            
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(OrderexecutionEndParams.TYPE);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            
            InstitutionParams l_institutionParams = getInstitutionRow();
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            IfoOrderParams l_ifoOrderParams = getIfoOrderRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = this.ifoOrderUnit();
            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
            l_ifoOrderUnitParams.setSessionType("1");
            l_ifoOrderUnitParams.setLimitPrice(300);
            ProductParams l_productParams = getProductRow();
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            MarketParams l_marketParams = getMarketRow();
            EnableOrderConditionParams l_enableOrderConditionParams = getEnableOrderConditionParamsRow();
            
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();
 
            
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setNameSerialNo(2);
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("evening.session.div");
            
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("1");
            l_orderexecutionEndParams.setOrderexecutionEndType(WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END);
            
            
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            
            IfoTradedProductImplAForTest l_ifoTradedProductImpl = null;
            try
            {
                l_ifoTradedProductImpl = new IfoTradedProductImplAForTest();
            }
            catch (Exception e)
            {
                fail();
            }
            
            String[] l_str = new String[]{"2"};
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder",
                    new Class[] {SubAccount.class, String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateHandlingOpenContractOrder", 
                    new Class[]
                    { SubAccount.class, boolean.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability",
                    new Class[] {Order.class, boolean.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateMarketID",
                    new Class[]{long.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[]{WEB3IfoProductImpl.class, WEB3GentradeMarket.class,
                        boolean.class, boolean.class},
                        l_ifoTradedProductImpl);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getHandlingPossibleExecConds", new Class[]
                    { String[].class, String[].class },
                    l_str);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        WEB3GenResponse l_response = null;
        try
        {
            WEB3IfoQuoteDataImplForTest l_ifoQuoteData = new WEB3IfoQuoteDataImplForTest();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getIfoQuote",
                    new Class[]
                    {IfoTradedProduct.class,RealType.class},
                    l_ifoQuoteData);
                    
            l_response = l_serviceImpl.execute(l_request);
            
            WEB3OptionsOpenMarginChangeInputResponse l_response1 =
                (WEB3OptionsOpenMarginChangeInputResponse)l_response;
            
            //レスポンス.注文単価区分
            assertEquals("1", l_response1.orderPriceDiv);
        }
        catch (WEB3BaseException e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    public class WEB3OptionChangeOpenContractInputServiceImplForTest
        extends WEB3OptionChangeOpenContractInputServiceImpl
    {
        protected void validateOrderForChangeability(IfoOrderUnit l_orderUnit)
            throws WEB3BaseException
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, "");
        }
    }

    public class IfoTradedProductImplForTest extends WEB3IfoTradedProductImpl
    implements IfoTradedProduct
    {
        public IfoTradedProductImplForTest() throws Exception
        {
            super(0L);
        }

        public double getLastClosingPrice()
        {
            return 0;
        }

        public long getUnitSize()
        {
            return 0;
        }

        public long getPerOrderMaxUnits()
        {
            return 0;
        }

        public double getTickValueUnit(double arg0)
        {
            return 0;
        }

        public boolean isValidPriceAsPerTickValue(double arg0)
        {
            return false;
        }

        public Date getStartTradingDate()
        {
            return null;
        }

        public Date getLastTradingDate()
        {
            return WEB3DateUtility.getDate("20070101","yyyyMMdd");
        }

        public boolean isTradingStopped()
        {
            return false;
        }

        public boolean isBuyToOpenOrdersStopped()
        {
            return false;
        }

        public boolean isSellToOpenOrdersStopped()
        {
            return false;
        }

        public boolean isSellToCloseOrdersStopped()
        {
            return false;
        }

        public boolean isBuyToCloseOrdersStopped()
        {
            return false;
        }

        public double getLimitPriceRange()
        {
            return 0;
        }

        public double getStopHighPrice()
        {
            return 0;
        }

        public double getStopLowPrice()
        {
            return 0;
        }

        public long getTradedProductId()
        {
            return 0;
        }

        public Product getProduct()
        {
            return null;
        }

        public Market getMarket()
        {
            return null;
        }

        public boolean isTradingSuspended()
        {
            return false;
        }

        public double getMarginRatio()
        {
            return 0;
        }

        public Date getBaseDate()
        {
            return null;
        }

        public Date getDailyDeliveryDate()
        {
            return null;
        }

        public boolean isCollateralizable()
        {
            return false;
        }

        public Institution getInstitution()
        {
            return null;
        }

        public Object getDataSourceObject()
        {
            return null;
        }
        
    }
    
    public class IfoTradedProductImplAForTest extends WEB3IfoTradedProductImpl
    implements IfoTradedProduct
    {
        public IfoTradedProductImplAForTest() throws Exception
        {
            super(0L);
        }

        public double getLastClosingPrice()
        {
            return 0;
        }

        public long getUnitSize()
        {
            return 0;
        }

        public long getPerOrderMaxUnits()
        {
            return 0;
        }

        public double getTickValueUnit(double arg0)
        {
            return 0;
        }

        public boolean isValidPriceAsPerTickValue(double arg0)
        {
            return false;
        }

        public Date getStartTradingDate()
        {
            return null;
        }

        public Date getLastTradingDate()
        {
            return WEB3DateUtility.getDate("20100101","yyyyMMdd");
        }

        public boolean isTradingStopped()
        {
            return false;
        }

        public boolean isBuyToOpenOrdersStopped()
        {
            return false;
        }

        public boolean isSellToOpenOrdersStopped()
        {
            return false;
        }

        public boolean isSellToCloseOrdersStopped()
        {
            return false;
        }

        public boolean isBuyToCloseOrdersStopped()
        {
            return false;
        }

        public double getLimitPriceRange()
        {
            return 0;
        }

        public double getStopHighPrice()
        {
            return 0;
        }

        public double getStopLowPrice()
        {
            return 0;
        }

        public long getTradedProductId()
        {
            return 0;
        }

        public Product getProduct()
        {
            return null;
        }

        public Market getMarket()
        {
            return null;
        }

        public boolean isTradingSuspended()
        {
            return false;
        }

        public double getMarginRatio()
        {
            return 0;
        }

        public Date getBaseDate()
        {
            return null;
        }

        public Date getDailyDeliveryDate()
        {
            return null;
        }

        public boolean isCollateralizable()
        {
            return false;
        }

        public Institution getInstitution()
        {
            return null;
        }

        public Object getDataSourceObject()
        {
            return null;
        }
        
    }
    
    class WEB3IfoQuoteDataImplForTest implements WEB3IfoQuoteData
    {

        public WEB3IfoQuoteDataImplForTest()
        {
            
        }
        
        public double getCurrentPrice()
        {
            return 0;
        }

        public double getBidPrice()
        {
            return 0;
        }

        public double getAskPrice()
        {
            return 0;
        }

        public double getOpenPrice()
        {
            return 0;
        }

        public double getLastClosingPrice()
        {
            return 0;
        }

        public Timestamp getQuoteTimestamp()
        {
            return null;
        }

        public Date getQuoteDate()
        {
            return null;
        }

        public RealType getRealType()
        {
            return null;
        }

        public DataType getDataType()
        {
            return null;
        }

        public String getMarketCode()
        {
            return null;
        }

        public String getProductCode()
        {
            return null;
        }

        public String getMonthOfDelivery()
        {
            return null;
        }

        public PutAndCall getPutAndCall()
        {
            return null;
        }

        public double getStrikePrice()
        {
            return 0;
        }

        public Timestamp getOpenPriceTime()
        {
            return null;
        }

        public double getHighPrice()
        {
            return 0;
        }

        public Timestamp getHighPriceTime()
        {
            return null;
        }

        public double getLowPrice()
        {
            return 0;
        }

        public Timestamp getLowPriceTime()
        {
            return null;
        }

        public Timestamp getCurrentPriceTime()
        {
            return null;
        }

        public CurrentPriceFlag getCurrentPriceFlag()
        {
            return null;
        }

        public double getChange()
        {
            return 0;
        }

        public double getVolume()
        {
            return 0;
        }

        public Timestamp getVolumeTime()
        {
            return null;
        }

        public AskPriceTitle getAskPriceTitle()
        {
            return null;
        }

        public Timestamp getAskPriceTime()
        {
            return null;
        }

        public BidPriceTitle getBidPriceTitle()
        {
            return null;
        }

        public Timestamp getBidPriceTime()
        {
            return null;
        }

        public double getBasePrice()
        {
            return 0;
        }
        
    }
    
    /**
     * 注文テーブル（ヘッダ）(ifo_order)
     */
    public static IfoOrderParams getIfoOrderRow()
    {
        IfoOrderParams l_ifoOrderParams = new IfoOrderParams();
        l_ifoOrderParams.setOrderId(1001);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.OTHER);
        l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        return l_ifoOrderParams;
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
        l_params.setOrderConditionType("3");
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
        l_params.setBizDate("20070808");
        l_params.setWLimitExecCondType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        
        l_params.setConfirmedExecConditionType(IfoOrderExecutionConditionType.NONE);
        l_params.setConfirmedQuantity(100);
        
        l_params.setExpirationDate(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
        return l_params;
    }
    
    /**
     * 銘柄Rowを作成.<BR>
     */
    public static ProductParams getProductRow()
    {
        ProductParams l_productParams = new ProductParams();

        l_productParams.setProductId(1006169090018L);
        l_productParams.setInstitutionCode("0D");
        l_productParams.setProductType(ProductTypeEnum.IFO);
        l_productParams.setStandardName("シンセンテルス");
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
     * 先物OP銘柄テーブル (ifo_product)
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
     * 市場Rowを作成.<BR>
     */
    public static MarketParams getMarketRow()
    {
        MarketParams l_marketParams = new MarketParams();

        l_marketParams.setMarketId(1002);
        l_marketParams.setInstitutionCode("0D");
        l_marketParams.setMarketCode("SP");
        l_marketParams.setSonarMarketCode("G");
        l_marketParams.setMarketName("シンガポール");
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
    
    /**
     * (enable_order_condition)
     */
    public static EnableOrderConditionParams getEnableOrderConditionParamsRow()
    {
        EnableOrderConditionParams l_enableOrderConditionParams = new EnableOrderConditionParams();
        l_enableOrderConditionParams.setInstitutionCode("0D");
        l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
        l_enableOrderConditionParams.setFutureOptionDiv("2");
        l_enableOrderConditionParams.setMarginTradingDiv("0");
        l_enableOrderConditionParams.setMarketCode("0");
        l_enableOrderConditionParams.setAtMarketOpen("1");
        l_enableOrderConditionParams.setStopOrder("1");
        l_enableOrderConditionParams.setCarriedOrder("1");
        l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("1");
        l_enableOrderConditionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_enableOrderConditionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        return l_enableOrderConditionParams;
    }
    
    /**
     * 証券会社Rowを作成.<BR>
     */
    public static InstitutionParams getInstitutionRow()
    {
        InstitutionParams l_institutionParams = new InstitutionParams();

        l_institutionParams.setInstitutionCode("0D");
        l_institutionParams.setInstitutionId(33);

        return l_institutionParams;
    }
    
    /**
     * 先物OP取引銘柄マスタ(ifo_traded_product)
     */
    public static IfoTradedProductParams getIfoTradedProductRow()
    {
        IfoTradedProductParams l_IfoTradedProductParams = new IfoTradedProductParams();
        l_IfoTradedProductParams.setTradedProductId(0L);
        l_IfoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
        l_IfoTradedProductParams.setInstitutionCode("0D");
        l_IfoTradedProductParams.setMarketId(1002);
        l_IfoTradedProductParams.setProductId(1006169090018L);
        l_IfoTradedProductParams.setUnitSize(10000L);
        l_IfoTradedProductParams.setUnitMargin(0L);
        l_IfoTradedProductParams.setPerOrderMaxUnits(1000L);
        l_IfoTradedProductParams.setOrderCloseTime("");
        l_IfoTradedProductParams.setLastClosingPrice(8D);
        l_IfoTradedProductParams.setStartTradingDate(WEB3DateUtility.getDate("20040708","yyyyMMdd"));
        l_IfoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20040718","yyyyMMdd")); 
        l_IfoTradedProductParams.setTradeStopFlag(BooleanEnum.FALSE);
        l_IfoTradedProductParams.setBuyToOpenStopFlag(BooleanEnum.FALSE);
        l_IfoTradedProductParams.setSellToOpenStopFlag(BooleanEnum.FALSE);
        l_IfoTradedProductParams.setSellToCloseStopFlag(BooleanEnum.FALSE);
        l_IfoTradedProductParams.setBuyToCloseStopFlag(BooleanEnum.FALSE);
        l_IfoTradedProductParams.setListFlag(BooleanEnum.TRUE);
        l_IfoTradedProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        l_IfoTradedProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        return l_IfoTradedProductParams;
    }
    
    /**
     * 取引銘柄マスターRowを作成.<BR>
     */
    public static TradedProductParams getTradedProductRow()
    {
        TradedProductParams l_tradedProductParams = new TradedProductParams();
        
        l_tradedProductParams.setTradedProductId(0L);
        l_tradedProductParams.setInstitutionCode("0D");
        l_tradedProductParams.setProductId(1006169090018L);
        l_tradedProductParams.setMarketId(1002);
        l_tradedProductParams.setMarginRatio(70.000000D);
        l_tradedProductParams.setSuspensionFlag(BooleanEnum.FALSE);
        l_tradedProductParams.setBaseDate(WEB3DateUtility.getDate("20040917","yyyyMMdd"));
        l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20040924","yyyyMMdd"));
        l_tradedProductParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
        l_tradedProductParams.setCollateralFlag(BooleanEnum.FALSE);
        l_tradedProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_tradedProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        return l_tradedProductParams;
    }
    
    /**
     * 先物OP取引銘柄ﾏｽﾀ（一時ﾃｰﾌﾞﾙ）(ifo_traded_product_updq)
     */
    public static IfoTradedProductUpdqParams getIfoTradedProductUpdqRow()
    {                
        Date l_datNextBizDate = null;
        try
        {
            l_datNextBizDate = new WEB3GentradeBizDate(
                    new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime())).roll(1);
        }
        catch (WEB3SystemLayerException e)
        {
            fail();
        }
        
        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = new IfoTradedProductUpdqParams();
        l_ifoTradedProductUpdqParams.setTradedProductId(0L);
        l_ifoTradedProductUpdqParams.setValidForBizDate(WEB3DateUtility.formatDate(l_datNextBizDate,"yyyyMMdd"));
        l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
        l_ifoTradedProductUpdqParams.setMarketId(1002);
        l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
        l_ifoTradedProductUpdqParams.setUnitSize(10000D);
        l_ifoTradedProductUpdqParams.setUnitMargin(0L);
        l_ifoTradedProductUpdqParams.setPerOrderMaxUnits(1000D);
        l_ifoTradedProductUpdqParams.setOrderCloseTime("0");
        l_ifoTradedProductUpdqParams.setLastClosingPrice(28);
        l_ifoTradedProductUpdqParams.setStartTradingDate(WEB3DateUtility.getDate("20040708","yyyyMMdd"));
        l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
        l_ifoTradedProductUpdqParams.setTradeStopFlag(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setBuyToOpenStopFlag(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setSellToOpenStopFlag(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setSellToCloseStopFlag(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setBuyToCloseStopFlag(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setListFlag(BooleanEnum.TRUE);
        l_ifoTradedProductUpdqParams.setListDate(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        l_ifoTradedProductUpdqParams.setUnlistedDate(WEB3DateUtility.getDate("20040730","yyyyMMdd"));
        l_ifoTradedProductUpdqParams.setExerciseStop(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setActualDelivaryStop(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setActualRecieveStop(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setReserveStop(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setIndicationPrice(30.8D);
        l_ifoTradedProductUpdqParams.setLastLiquidationPrice(0D);
        l_ifoTradedProductUpdqParams.setTargetSpotPrice(1212D);
        l_ifoTradedProductUpdqParams.setLiquidationPrice(8.13D);
        l_ifoTradedProductUpdqParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        l_ifoTradedProductUpdqParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        return l_ifoTradedProductUpdqParams;
    }
}
@

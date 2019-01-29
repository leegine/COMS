head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.45.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionChangeClosingContractInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP訂正返済入力サービスImpl
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/18 孟亜南 (中訊)
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendarDetails;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
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
import webbroker3.common.define.WEB3CarriedOrderLapseDateSpecDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingClendarDetailsImpl;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoOpenContractChangeSpec;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeInputRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeInputResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
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
 * OP訂正返済入力サービスImpl
 * @@author 孟亜南
 */
public class WEB3OptionChangeClosingContractInputServiceImplTest extends TestBaseForMock
{

    public WEB3OptionChangeClosingContractInputServiceImplTest(String name)
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
        
        TradingCalendarDetails tradingCalendarDetails =
            new WEB3GentradeTradingClendarDetailsImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                "getTradingCalendarDetails",
                new Class[] {long.class},
                tradingCalendarDetails);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                "getCurrentBizDate",
                new Class[] {long.class},
                WEB3DateUtility.getDate("20070619","yyyyMMdd"));
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
                 WEB3OptionChangeClosingContractInputServiceImplTest.class);
    

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
        
        WEB3OptionChangeClosingContractInputServiceImpl l_serviceImpl =
            new WEB3OptionChangeClosingContractInputServiceImpl();
        
        WEB3OptionsCloseMarginChangeInputRequest l_request = new WEB3OptionsCloseMarginChangeInputRequest();
        l_request.id = "1001";
        
        try
        {
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
            TestDBUtility.deleteAll(IfoClosingContractSpecRow.TYPE);
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            
            TestDBUtility.deleteAll(OrderexecutionEndParams.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("1");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = this.getIfoTradedProductUpdqRow();
            IfoOrderParams l_ifoOrderParams = this.getIfoOrderRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = this.ifoOrderUnit();
            l_ifoOrderUnitParams.setSessionType("5");
            l_ifoOrderUnitParams.setLimitPrice(0);
            ProductParams l_productParams = this.getProductRow();
            IfoProductParams l_ifoProductParams = this.getIfoProductRow();
            MarketParams l_marketParams = this.getMarketRow();
            EnableOrderConditionParams l_enableOrderConditionParams = this.getEnableOrderConditionParamsRow();
            
            TradedProductParams l_TradedProductParams = this.getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = this.getIfoTradedProductRow();
            IfoClosingContractSpecParams l_ifoClosingContractSpecParams = this.getIfoClosingContractSpecRow();
            IfoContractParams l_ifoContractParams = this.getIfoContractRow();
            
            InstitutionParams l_institutionParams = this.getInstitutionRow();
            
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
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
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
            
            ErrorInfo errorInfo = new ErrorInfo();
            
            ProcessingResult processingResult = ProcessingResult.newFailedResultInstance(errorInfo);
            
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateChangeOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class, boolean.class },
                    l_result);
            
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
            
            TradingTimeParams l_ttParams = TestDBUtility.getTradingTimeRow();
            l_ttParams.setInstitutionCode("0D");
            l_ttParams.setBranchCode("381");
            l_ttParams.setProductCode("0");
            l_ttParams.setMarketCode("SP");
            l_ttParams.setTradingTimeType("26");
            l_ttParams.setBizDateType("2");
            TestDBUtility.insertWithDel(l_ttParams);
            
            l_response = l_serviceImpl.execute(l_request);
            
            WEB3OptionsCloseMarginChangeInputResponse l_response1 =
                (WEB3OptionsCloseMarginChangeInputResponse)l_response;
            
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
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
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
        
        WEB3OptionChangeClosingContractInputServiceImpl l_serviceImpl =
            new WEB3OptionChangeClosingContractInputServiceImpl();
        
        WEB3OptionsCloseMarginChangeInputRequest l_request = new WEB3OptionsCloseMarginChangeInputRequest();
        l_request.id = "1001";
        
        try
        {
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
            
            TestDBUtility.deleteAll(OrderexecutionEndParams.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("1");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = this.getIfoTradedProductUpdqRow();
            IfoOrderParams l_ifoOrderParams = this.getIfoOrderRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = this.ifoOrderUnit();
            //
            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
            l_ifoOrderUnitParams.setSessionType("1");
            l_ifoOrderUnitParams.setLimitPrice(10);
//            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
//            l_ifoOrderUnitParams.setOrderConditionType("1");
            ProductParams l_productParams = this.getProductRow();
            IfoProductParams l_ifoProductParams = this.getIfoProductRow();
            MarketParams l_marketParams = this.getMarketRow();
            EnableOrderConditionParams l_enableOrderConditionParams = this.getEnableOrderConditionParamsRow();
            
            TradedProductParams l_TradedProductParams = this.getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = this.getIfoTradedProductRow();
            
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
            
            ErrorInfo errorInfo = new ErrorInfo();
            
            ProcessingResult processingResult = ProcessingResult.newFailedResultInstance(errorInfo);
            
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateChangeOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class, boolean.class },
                    l_result);
            
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
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
            TradingTimeParams l_ttParams = TestDBUtility.getTradingTimeRow();
            l_ttParams.setInstitutionCode("0D");
            l_ttParams.setBranchCode("381");
            l_ttParams.setProductCode("0");
            l_ttParams.setMarketCode("SP");
            l_ttParams.setTradingTimeType("26");
            l_ttParams.setBizDateType("2");
            TestDBUtility.insertWithDel(l_ttParams);
            l_response = l_serviceImpl.execute(l_request);

            WEB3OptionsCloseMarginChangeInputResponse l_response1 =
                (WEB3OptionsCloseMarginChangeInputResponse)l_response;
            
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
        
        WEB3OptionChangeClosingContractInputServiceImpl l_serviceImpl =
            new WEB3OptionChangeClosingContractInputServiceImpl();
        
        WEB3OptionsCloseMarginChangeInputRequest l_request = new WEB3OptionsCloseMarginChangeInputRequest();
        l_request.id = "1001";
        
        try
        {
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
            
            TestDBUtility.deleteAll(OrderexecutionEndParams.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("1");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = this.getIfoTradedProductUpdqRow();
            IfoOrderParams l_ifoOrderParams = this.getIfoOrderRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = this.ifoOrderUnit();
            l_ifoOrderUnitParams.setSessionType("1");
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            ProductParams l_productParams = this.getProductRow();
            IfoProductParams l_ifoProductParams = this.getIfoProductRow();
            MarketParams l_marketParams = this.getMarketRow();
            EnableOrderConditionParams l_enableOrderConditionParams = this.getEnableOrderConditionParamsRow();
            
            TradedProductParams l_TradedProductParams = this.getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = this.getIfoTradedProductRow();
            
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
            
            ErrorInfo errorInfo = new ErrorInfo();
            
            ProcessingResult processingResult = ProcessingResult.newFailedResultInstance(errorInfo);
            
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateChangeOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class, boolean.class },
                    l_result);
            
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

            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
            TradingTimeParams l_ttParams = TestDBUtility.getTradingTimeRow();
            l_ttParams.setInstitutionCode("0D");
            l_ttParams.setBranchCode("381");
            l_ttParams.setProductCode("0");
            l_ttParams.setMarketCode("SP");
            l_ttParams.setTradingTimeType("26");
            l_ttParams.setBizDateType("2");
            TestDBUtility.insertWithDel(l_ttParams);
            l_response = l_serviceImpl.execute(l_request);
            
            WEB3OptionsCloseMarginChangeInputResponse l_response1 =
                (WEB3OptionsCloseMarginChangeInputResponse)l_response;
            
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
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
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
        
        WEB3OptionChangeClosingContractInputServiceImpl l_serviceImpl =
            new WEB3OptionChangeClosingContractInputServiceImpl();
        
        WEB3OptionsCloseMarginChangeInputRequest l_request = new WEB3OptionsCloseMarginChangeInputRequest();
        l_request.id = "1001";
        
        try
        {
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
            TestDBUtility.deleteAll(IfoClosingContractSpecRow.TYPE);
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            
            TestDBUtility.deleteAll(OrderexecutionEndParams.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("1");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = this.getIfoTradedProductUpdqRow();
            IfoOrderParams l_ifoOrderParams = this.getIfoOrderRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = this.ifoOrderUnit();
            l_ifoOrderUnitParams.setSessionType("5");
            l_ifoOrderUnitParams.setFirstOrderUnitId(1001);
            ProductParams l_productParams = this.getProductRow();
            IfoProductParams l_ifoProductParams = this.getIfoProductRow();
            MarketParams l_marketParams = this.getMarketRow();
            EnableOrderConditionParams l_enableOrderConditionParams = this.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder(WEB3CarriedOrderDef.CAN_NOT_DEALT);
            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec(WEB3CarriedOrderLapseDateSpecDef.EXPIRATION_DATE_USER_DES);
            
            
            TradedProductParams l_TradedProductParams = this.getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = this.getIfoTradedProductRow();
            IfoClosingContractSpecParams l_ifoClosingContractSpecParams = this.getIfoClosingContractSpecRow();
            IfoContractParams l_ifoContractParams = this.getIfoContractRow();
            
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
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
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
            
            ErrorInfo errorInfo = new ErrorInfo();
            
            ProcessingResult processingResult = ProcessingResult.newFailedResultInstance(errorInfo);
            
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateChangeOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class, boolean.class },
                    l_result);
            
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
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
            TradingTimeParams l_ttParams = TestDBUtility.getTradingTimeRow();
            l_ttParams.setInstitutionCode("0D");
            l_ttParams.setBranchCode("381");
            l_ttParams.setProductCode("0");
            l_ttParams.setMarketCode("SP");
            l_ttParams.setTradingTimeType("26");
            l_ttParams.setBizDateType("2");
            TestDBUtility.insertWithDel(l_ttParams);
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
        
        WEB3OptionChangeClosingContractInputServiceImpl l_serviceImpl =
            new WEB3OptionChangeClosingContractInputServiceImpl();
        
        WEB3OptionsCloseMarginChangeInputRequest l_request = new WEB3OptionsCloseMarginChangeInputRequest();
        l_request.id = "1001";
        
        try
        {
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
            
            TestDBUtility.deleteAll(OrderexecutionEndParams.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("1");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = this.getIfoTradedProductUpdqRow();
            IfoOrderParams l_ifoOrderParams = this.getIfoOrderRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = this.ifoOrderUnit();
            //
            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
            l_ifoOrderUnitParams.setSessionType("1");
//            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
//            l_ifoOrderUnitParams.setOrderConditionType("1");
            ProductParams l_productParams = this.getProductRow();
            IfoProductParams l_ifoProductParams = this.getIfoProductRow();
            MarketParams l_marketParams = this.getMarketRow();
            EnableOrderConditionParams l_enableOrderConditionParams = this.getEnableOrderConditionParamsRow();
            
            TradedProductParams l_TradedProductParams = this.getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = this.getIfoTradedProductRow();
            
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
            
            ErrorInfo errorInfo = new ErrorInfo();
            
            ProcessingResult processingResult = ProcessingResult.newFailedResultInstance(errorInfo);
            
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateChangeOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class, boolean.class },
                    l_result);
            
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

            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
            TradingTimeParams l_ttParams = TestDBUtility.getTradingTimeRow();
            l_ttParams.setInstitutionCode("0D");
            l_ttParams.setBranchCode("381");
            l_ttParams.setProductCode("0");
            l_ttParams.setMarketCode("SP");
            l_ttParams.setTradingTimeType("26");
            l_ttParams.setBizDateType("2");
            TestDBUtility.insertWithDel(l_ttParams);
            l_response = l_serviceImpl.execute(l_request);
            
            WEB3OptionsCloseMarginChangeInputResponse l_response1 =
                (WEB3OptionsCloseMarginChangeInputResponse)l_response;
            
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
     * get訂正返済入力画面
     *  validate注文訂正可能抛異常
     */
    public void testGetCloseChangeInputScreenCase0001()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        final String STR_METHOD_NAME = "testGetCloseChangeInputScreenCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionChangeClosingContractInputServiceImplForTest2 l_serviceImpl =
            new WEB3OptionChangeClosingContractInputServiceImplForTest2();
        
        WEB3OptionsCloseMarginChangeInputRequest l_request = new WEB3OptionsCloseMarginChangeInputRequest();
        l_request.id = "1001";
        
        try
        {
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
            TestDBUtility.deleteAll(IfoClosingContractSpecRow.TYPE);
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            
            
            TestDBUtility.deleteAll(OrderexecutionEndParams.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("1");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = this.getIfoTradedProductUpdqRow();
            
            IfoOrderParams l_ifoOrderParams = this.getIfoOrderRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = this.ifoOrderUnit();
            l_ifoOrderUnitParams.setSessionType("5");
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE);
            l_ifoOrderUnitParams.setLimitPrice(100);
            
            IfoProductParams l_ifoProductParams = this.getIfoProductRow();
            l_ifoProductParams.setProductId(100106139070605L);
            MarketParams l_marketParams = this.getMarketRow();
            EnableOrderConditionParams l_enableOrderConditionParams = this.getEnableOrderConditionParamsRow();
            
            TradedProductParams l_TradedProductParams = this.getTradedProductRow();
            l_TradedProductParams.setProductId(100106139070605L);
            l_TradedProductParams.setTradedProductId(100106139070605L);
            IfoTradedProductParams l_IfoTradedProductParams = this.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(100106139070605L);
            l_IfoTradedProductParams.setTradedProductId(100106139070605L);
            IfoClosingContractSpecParams l_ifoClosingContractSpecParams = this.getIfoClosingContractSpecRow();
            IfoContractParams l_ifoContractParams = this.getIfoContractRow();
            
            ProductParams l_pparams = TestDBUtility.getProductRow();
            l_pparams.setProductId(100106139070605L);
            
            TestDBUtility.insertWithDel(l_pparams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            WEB3IfoTradedProductImpl l_tradedProductImpl = 
                new WEB3IfoTradedProductImpl(100106139070605L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[]{
                        WEB3IfoProductImpl.class,
                        WEB3GentradeMarket.class,
                        boolean.class,
                        boolean.class},
                        l_tradedProductImpl);
            
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
                    new Class[] {Order.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateMarketID",
                    new Class[]{long.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getHandlingPossibleExecConds", new Class[]
                    { String[].class, String[].class },
                    l_str);
            
            ErrorInfo errorInfo = new ErrorInfo();
            
            ProcessingResult processingResult = ProcessingResult.newFailedResultInstance(errorInfo);
            
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateChangeOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class, boolean.class },
                    l_result);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        try
        {
            WEB3IfoQuoteDataImplForTest l_ifoQuoteData = new WEB3IfoQuoteDataImplForTest();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getIfoQuote",
                    new Class[]
                    {IfoTradedProduct.class,RealType.class},
                    l_ifoQuoteData);

            WEB3IfoTradedProductImplForTest l_tradedProduct = new WEB3IfoTradedProductImplForTest(100106139070605L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct",
                    new Class[]{ long.class, long.class },
                    l_tradedProduct);
           l_serviceImpl.getCloseChangeInputScreen(l_request);
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
            log.error("", l_ex);
            fail();
        }
    }
    
    /**
     * get訂正返済入力画面
     *OPの訂正返済入力画面表示サービスを実施 
     レスポンスデータ注文単価区分にプロパティをセットする。
     注文単位.getSide() = ”買”
     注文単位.isMarketOrder = true
     */
    public void testGetCloseChangeInputScreenCase0002()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        final String STR_METHOD_NAME = "testGetCloseChangeInputScreenCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionChangeClosingContractInputServiceImplForTest l_serviceImpl =
            new WEB3OptionChangeClosingContractInputServiceImplForTest();
        
        WEB3OptionsCloseMarginChangeInputRequest l_request = new WEB3OptionsCloseMarginChangeInputRequest();
        l_request.id = "1001";
        
        try
        {
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
            TestDBUtility.deleteAll(IfoClosingContractSpecRow.TYPE);
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            
            
            TestDBUtility.deleteAll(OrderexecutionEndParams.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("1");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = this.getIfoTradedProductUpdqRow();
            IfoOrderParams l_ifoOrderParams = this.getIfoOrderRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = this.ifoOrderUnit();
            l_ifoOrderUnitParams.setSessionType("5");
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE);
            
            IfoProductParams l_ifoProductParams = this.getIfoProductRow();
            l_ifoProductParams.setProductId(100106139070605L);
            MarketParams l_marketParams = this.getMarketRow();
            EnableOrderConditionParams l_enableOrderConditionParams = this.getEnableOrderConditionParamsRow();
            
            TradedProductParams l_TradedProductParams = this.getTradedProductRow();
            l_TradedProductParams.setProductId(100106139070605L);
            l_TradedProductParams.setTradedProductId(100106139070605L);
            IfoTradedProductParams l_IfoTradedProductParams = this.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(100106139070605L);
            l_IfoTradedProductParams.setTradedProductId(100106139070605L);
            IfoClosingContractSpecParams l_ifoClosingContractSpecParams = this.getIfoClosingContractSpecRow();
            IfoContractParams l_ifoContractParams = this.getIfoContractRow();
            
            ProductParams l_pparams = TestDBUtility.getProductRow();
            l_pparams.setProductId(100106139070605L);
            TestDBUtility.insertWithDel(l_pparams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            
            
            WEB3IfoTradedProductImpl l_tradedProductImpl = 
                new WEB3IfoTradedProductImpl(100106139070605L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[]{
                        WEB3IfoProductImpl.class,
                        WEB3GentradeMarket.class,
                        boolean.class,
                        boolean.class},
                        l_tradedProductImpl);
            
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
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getHandlingPossibleExecConds", new Class[]
                    { String[].class, String[].class },
                    l_str);
            WEB3IfoTradedProductImplForTest l_tradedProduct = new WEB3IfoTradedProductImplForTest(100106139070605L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct",
                    new Class[]{ long.class, long.class },
                    l_tradedProduct);

            WEB3FuturesOptionsContractUnit[] l_units = new WEB3FuturesOptionsContractUnit[1];
            l_units[0] = new WEB3FuturesOptionsContractUnit();
            l_units[0].id = "12345";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "createContractUnitByOrder", new Class[]
                    { long.class },
                    l_units);
            
            ErrorInfo errorInfo = new ErrorInfo();
            
            ProcessingResult processingResult = ProcessingResult.newFailedResultInstance(errorInfo);
            
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateChangeOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class, boolean.class },
                    l_result);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
		 catch (Exception l_ex)
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

            WEB3IfoProductImpl l_productImpl = new WEB3IfoProductImpl(100106139070605L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateProductID",
                    new Class[] {long.class},
                    l_productImpl);

            l_response = l_serviceImpl.getCloseChangeInputScreen(l_request);
            
            WEB3OptionsCloseMarginChangeInputResponse l_response1 =
                (WEB3OptionsCloseMarginChangeInputResponse)l_response;
            
            assertEquals("0", l_response1.orderPriceDiv);
            
        }
        catch (WEB3BaseException e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();            
        }
    }
    
    
    
    /**
     * get訂正返済入力画面
     *OPの訂正返済入力画面表示サービスを実施 
     レスポンスデータ注文単価区分にプロパティをセットする。
     注文単位.getSide() = ”売”
     注文単位.isMarketOrder = false
     */
    public void testGetCloseChangeInputScreenCase0003()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        final String STR_METHOD_NAME = "testGetCloseChangeInputScreenCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionChangeClosingContractInputServiceImplForTest l_serviceImpl =
            new WEB3OptionChangeClosingContractInputServiceImplForTest();
        
        WEB3OptionsCloseMarginChangeInputRequest l_request = new WEB3OptionsCloseMarginChangeInputRequest();
        l_request.id = "1001";
        
        try
        {
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
            TestDBUtility.deleteAll(IfoClosingContractSpecRow.TYPE);
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            
            
            TestDBUtility.deleteAll(OrderexecutionEndParams.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("1");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = this.getIfoTradedProductUpdqRow();
            IfoOrderParams l_ifoOrderParams = this.getIfoOrderRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = this.ifoOrderUnit();
            l_ifoOrderUnitParams.setSessionType("5");
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE);
            l_ifoOrderUnitParams.setLimitPrice(100);
            
            IfoProductParams l_ifoProductParams = this.getIfoProductRow();
            l_ifoProductParams.setProductId(100106139070605L);
            MarketParams l_marketParams = this.getMarketRow();
            EnableOrderConditionParams l_enableOrderConditionParams = this.getEnableOrderConditionParamsRow();
            
            TradedProductParams l_TradedProductParams = this.getTradedProductRow();
            l_TradedProductParams.setProductId(100106139070605L);
            l_TradedProductParams.setTradedProductId(100106139070605L);
            IfoTradedProductParams l_IfoTradedProductParams = this.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(100106139070605L);
            l_IfoTradedProductParams.setTradedProductId(100106139070605L);
            IfoClosingContractSpecParams l_ifoClosingContractSpecParams = this.getIfoClosingContractSpecRow();
            IfoContractParams l_ifoContractParams = this.getIfoContractRow();
            
            ProductParams l_pparams = TestDBUtility.getProductRow();
            l_pparams.setProductId(100106139070605L);
            
            TestDBUtility.insertWithDel(l_pparams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            WEB3IfoTradedProductImpl l_tradedProductImpl = 
                new WEB3IfoTradedProductImpl(100106139070605L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[]{
                        WEB3IfoProductImpl.class,
                        WEB3GentradeMarket.class,
                        boolean.class,
                        boolean.class},
                        l_tradedProductImpl);
            
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
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getHandlingPossibleExecConds", new Class[]
                    { String[].class, String[].class },
                    l_str);
            
            ErrorInfo errorInfo = new ErrorInfo();
            
            ProcessingResult processingResult = ProcessingResult.newFailedResultInstance(errorInfo);
            
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateChangeOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class, boolean.class },
                    l_result);

            WEB3IfoTradedProductImplForTest l_tradedProduct = new WEB3IfoTradedProductImplForTest(100106139070605L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct",
                    new Class[]{ long.class, long.class },
                    l_tradedProduct);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
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

            WEB3IfoProductImpl l_productImpl = new WEB3IfoProductImpl(100106139070605L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateProductID",
                    new Class[] {long.class},
                    l_productImpl);
            
            l_response = l_serviceImpl.getCloseChangeInputScreen(l_request);
            
            WEB3OptionsCloseMarginChangeInputResponse l_response1 =
                (WEB3OptionsCloseMarginChangeInputResponse)l_response;
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[]{WEB3IfoProductImpl.class, WEB3GentradeMarket.class,
                        boolean.class, boolean.class});

            assertEquals(new Boolean(true), (Boolean)l_paramsValue1.getFirstCalled()[2]);

            assertEquals("1", l_response1.orderPriceDiv);
            
        }
        catch (WEB3BaseException e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
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
        l_params.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
        l_params.setOrderCateg(OrderCategEnum.IDX_OPTIONS_CLOSE);
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
        l_params.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
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
        l_params.setWLimitExecCondType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE);
        
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
        l_enableOrderConditionParams.setFutureOptionDiv("1");
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
//        l_IfoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
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
    
    /**
     * ifo_closing_contract_spec
     */

    public static IfoClosingContractSpecParams getIfoClosingContractSpecRow()
    {
        IfoClosingContractSpecParams l_ifoClosingContractSpecParams = new IfoClosingContractSpecParams();

        l_ifoClosingContractSpecParams.setClosingContractSpecId(1001);
        l_ifoClosingContractSpecParams.setAccountId(101001010010L);
        l_ifoClosingContractSpecParams.setSubAccountId(10100101001007L);
        l_ifoClosingContractSpecParams.setOrderId(1001);
        l_ifoClosingContractSpecParams.setOrderUnitId(1001);
        l_ifoClosingContractSpecParams.setContractId(1001);
        l_ifoClosingContractSpecParams.setClosingSerialNo(111);
        l_ifoClosingContractSpecParams.setQuantity(1.0D);
        l_ifoClosingContractSpecParams.setConfirmedQuantity(200);
        l_ifoClosingContractSpecParams.setExecutedQuantity(100);
        l_ifoClosingContractSpecParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
        l_ifoClosingContractSpecParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));

        return l_ifoClosingContractSpecParams;
    }
    
    /**
     * 建玉テーブル (ifo_contract)
     */
    public static IfoContractParams getIfoContractRow()
    {
        IfoContractParams l_ifoContractParams = new IfoContractParams();
        l_ifoContractParams.setContractId(1001);
        l_ifoContractParams.setAccountId(101001010000L);
        l_ifoContractParams.setSubAccountId(10100101000007L);
        l_ifoContractParams.setMarketId(1002);
        l_ifoContractParams.setUnitSize(1000.0);
        l_ifoContractParams.setOriginalQuantity(1.0);
        l_ifoContractParams.setQuantity(1.0);
        l_ifoContractParams.setOriginalContractPrice(3720.0);
        l_ifoContractParams.setContractType(ContractTypeEnum.LONG);
        l_ifoContractParams.setContractPrice(3720.0);
        l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20040702","yyyyMMdd"));
        l_ifoContractParams.setCloseDate(WEB3DateUtility.getDate("20040812","yyyyMMdd"));
        l_ifoContractParams.setSetupFee(2000.0);
        l_ifoContractParams.setSetupFeeTax(100.0);
        l_ifoContractParams.setManagementFee(.0);
        l_ifoContractParams.setManagementFeeTax(.0);
        l_ifoContractParams.setInterestFee(.0);
        l_ifoContractParams.setInterestFeeTax(.0);
        l_ifoContractParams.setProductId(1006169090018L);
        l_ifoContractParams.setProductType(ProductTypeEnum.IFO);
        l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20040702","yyyyMMdd"));
        l_ifoContractParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
        l_ifoContractParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
        return l_ifoContractParams;
    }
    
    public class WEB3OptionChangeClosingContractInputServiceImplForTest
        extends WEB3OptionChangeClosingContractInputServiceImpl
    {
        protected WEB3FuturesOptionsContractUnit[] createContractUnitByOrder(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
            return null;
    }
    }
    
    public class WEB3OptionChangeClosingContractInputServiceImplForTest2
    extends WEB3OptionChangeClosingContractInputServiceImpl
	{
        protected void validateOrderForChangeability(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, "");
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

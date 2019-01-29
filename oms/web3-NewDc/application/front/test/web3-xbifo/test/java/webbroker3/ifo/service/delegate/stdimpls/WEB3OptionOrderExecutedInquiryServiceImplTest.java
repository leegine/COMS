head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.47.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionOrderExecutedInquiryServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP注文約定照会サービスImplTest(WEB3OptionOrderExecutedInquiryServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/20 趙林鵬(中訊) 新規作成
Revision History : 2007/08/25 劉剣 (中訊) IFO小数点対応
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendarDetails;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
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
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingClendarDetailsImpl;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3OptionsCloseMarginContractListRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginContractListResponse;
import webbroker3.ifo.message.WEB3OptionsExecuteDetailsRequest;
import webbroker3.ifo.message.WEB3OptionsExecuteDetailsResponse;
import webbroker3.ifo.message.WEB3OptionsExecuteGroup;
import webbroker3.ifo.message.WEB3OptionsExecuteReferenceRequest;
import webbroker3.ifo.message.WEB3OptionsExecuteReferenceResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionOrderExecutedInquiryServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3OptionOrderExecutedInquiryServiceImplTest.class);

    public WEB3OptionOrderExecutedInquiryServiceImplTest(String arg0)
    {
        super(arg0);
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
        l_tradingTimeParams.setSessionType("4");

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
       
        super.setUp();
        
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    /*
     * リクエストデータの整合性をチェックする失敗。
     * WEB3ErrorCatalog.BUSINESS_ERROR_00080
     */
    public void testGetOrderExecutedDetail_C0001()
    {
        final String STR_METHOD_NAME = "testgetOrderExecutedDetail_C0001()";

        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3OptionsExecuteDetailsRequest l_request = new WEB3OptionsExecuteDetailsRequest();
            l_request.id = "";
            
            WEB3OptionOrderExecutedInquiryServiceImpl l_impl = new WEB3OptionOrderExecutedInquiryServiceImpl();
            l_impl.getOrderExecutedDetail(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * 受付時間チェック、システム売買停止チェック失敗。
     * WEB3ErrorCatalog.SYSTEM_ERROR_80005
     */
    public void testGetOrderExecutedDetail_C0002()
    {
        final String STR_METHOD_NAME = "testgetOrderExecutedDetail_C0002()";

        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            WEB3OptionsExecuteDetailsRequest l_request = new WEB3OptionsExecuteDetailsRequest();
            l_request.id = "1001";
            
            WEB3OptionOrderExecutedInquiryServiceImpl l_impl = new WEB3OptionOrderExecutedInquiryServiceImpl();
            l_impl.getOrderExecutedDetail(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * 注文IDに対応する注文単位オブジェクトを取得する失敗。
     * WEB3ErrorCatalog.BUSINESS_ERROR_00398
     */
    public void testGetOrderExecutedDetail_C0003()
    {
        final String STR_METHOD_NAME = "testgetOrderExecutedDetail_C0003()";

        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3OptionsExecuteDetailsRequest l_request = new WEB3OptionsExecuteDetailsRequest();
            l_request.id = "1002";
            
            WEB3OptionOrderExecutedInquiryServiceImpl l_impl = new WEB3OptionOrderExecutedInquiryServiceImpl();
            l_impl.getOrderExecutedDetail(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * 市場の取得失敗。
     * WEB3ErrorCatalog.BUSINESS_ERROR_00003
     */
    public void testGetOrderExecutedDetail_C0004()
    {
        final String STR_METHOD_NAME = "testgetOrderExecutedDetail_C0004()";

        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setLimitPrice(0);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoproductParams = TestDBUtility.getIfoProductRow();
            l_ifoproductParams.setProductId(1006169090018L);
            l_ifoproductParams.setDerivativeType(IfoDerivativeTypeEnum.CALL_OPTIONS);
            TestDBUtility.insertWithDel(l_ifoproductParams);
            
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setOrderId(1001);
            l_ifoFinTransactionParams.setOrderUnitId(1001);
            l_ifoFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            
            WEB3OptionsExecuteDetailsRequest l_request = new WEB3OptionsExecuteDetailsRequest();
            l_request.id = "1001";
            
            WEB3OptionOrderExecutedInquiryServiceImpl l_impl = new WEB3OptionOrderExecutedInquiryServiceImpl();
            l_impl.getOrderExecutedDetail(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00003, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * レスポンス.オプション商品区分 = CALL_OPTIONS
     * レスポンス.注文単価区分 = 注文単位.isMarketOrderの返り値がtrueの場合は、"成行"
     * 注文単位.発注条件が"逆指値"の場合
     */
    public void testGetOrderExecutedDetail_C0005()
    {
        final String STR_METHOD_NAME = "testgetOrderExecutedDetail_C0005()";

        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "isEveningSessionOrder",
            new Class[]{ IfoOrderUnit.class },
            new Boolean(false));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setLimitPrice(0);
            l_ifoOrderUnitParams.setFirstOrderUnitId(11);
            l_ifoOrderUnitParams.setOrderConditionType("1");
            l_ifoOrderUnitParams.setExpirationDate(GtlUtils.getTradingSystem().getBizDate());
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_ifoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
            l_ifoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.FUTURE_OP_ADMIN_MANUAL_EXPIRED);
            l_ifoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.ACCEPT_ERROR);
            l_ifoOrderUnitParams.setOrgStopOrderPrice(100);
            l_ifoOrderUnitParams.setOrgOrderConditionType("2");
            l_ifoOrderUnitParams.setRequestType("2");
            l_ifoOrderUnitParams.setWLimitBeforeExecCondType(IfoOrderExecutionConditionType.NONE);
            l_ifoOrderUnitParams.setOrgWLimitPrice(1);
            l_ifoOrderUnitParams.setOrgWLimitExecCondType(IfoOrderExecutionConditionType.NONE);
            l_ifoOrderUnitParams.setExecutedQuantity(100);
            l_ifoOrderUnitParams.setExecutedAmount(1);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoproductParams = TestDBUtility.getIfoProductRow();
            l_ifoproductParams.setProductId(1006169090018L);
            l_ifoproductParams.setDerivativeType(IfoDerivativeTypeEnum.CALL_OPTIONS);
            TestDBUtility.insertWithDel(l_ifoproductParams);
            
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setOrderId(1001);
            l_ifoFinTransactionParams.setOrderUnitId(1001);
            l_ifoFinTransactionParams.setCommissionFee(7.0d);
            l_ifoFinTransactionParams.setCommissionFeeTax(7.0d);
            l_ifoFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(1006169090018L);
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3OptionsExecuteDetailsRequest l_request = new WEB3OptionsExecuteDetailsRequest();
            l_request.id = "1001";
            
            WEB3OptionOrderExecutedInquiryServiceImpl l_impl = new WEB3OptionOrderExecutedInquiryServiceImpl();
            WEB3OptionsExecuteDetailsResponse l_response = l_impl.getOrderExecutedDetail(l_request);

            assertEquals("7", l_response.commission);
            assertEquals("7", l_response.consumptionTax);
            assertEquals("0", l_response.execPrice);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * レスポンス.オプション商品区分 = PUT_OPTIONS
     * レスポンス.注文単価区分 = 注文単位.isMarketOrderの返り値がfalseの場合は、"指値"
     * 注文単位.発注条件が"W指値"の場合
     * レスポンス.W指値用注文単価 = null
     */
    public void testGetOrderExecutedDetail_C0006()
    {
        final String STR_METHOD_NAME = "testgetOrderExecutedDetail_C0006()";

        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "isEveningSessionOrder",
                new Class[]{ IfoOrderUnit.class },
                new Boolean(false));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setLimitPrice(1);
            l_ifoOrderUnitParams.setFirstOrderUnitId(11);
            l_ifoOrderUnitParams.setOrderConditionType("2");
            l_ifoOrderUnitParams.setExpirationDate(GtlUtils.getTradingSystem().getBizDate());
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_ifoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
            l_ifoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.FUTURE_OP_ADMIN_MANUAL_EXPIRED);
            l_ifoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.ACCEPT_ERROR);
            l_ifoOrderUnitParams.setOrgStopOrderPrice(100);
            l_ifoOrderUnitParams.setOrgOrderConditionType("2");
            l_ifoOrderUnitParams.setRequestType("2");
            l_ifoOrderUnitParams.setWLimitBeforeExecCondType(IfoOrderExecutionConditionType.NONE);
            l_ifoOrderUnitParams.setOrgWLimitPrice(1);
            l_ifoOrderUnitParams.setOrgWLimitExecCondType(IfoOrderExecutionConditionType.NONE);
            l_ifoOrderUnitParams.setExecutedQuantity(100);
            l_ifoOrderUnitParams.setExecutedAmount(1);
            l_ifoOrderUnitParams.setWLimitExecCondType(IfoOrderExecutionConditionType.NONE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoproductParams = TestDBUtility.getIfoProductRow();
            l_ifoproductParams.setProductId(1006169090018L);
            l_ifoproductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            TestDBUtility.insertWithDel(l_ifoproductParams);
            
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setOrderId(1001);
            l_ifoFinTransactionParams.setOrderUnitId(1001);
            l_ifoFinTransactionParams.setCommissionFee(7.0d);
            l_ifoFinTransactionParams.setCommissionFeeTax(7.0d);
            l_ifoFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(1006169090018L);
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3OptionsExecuteDetailsRequest l_request = new WEB3OptionsExecuteDetailsRequest();
            l_request.id = "1001";
            
            WEB3OptionOrderExecutedInquiryServiceImpl l_impl = new WEB3OptionOrderExecutedInquiryServiceImpl();
            WEB3OptionsExecuteDetailsResponse l_response = l_impl.getOrderExecutedDetail(l_request);

            assertEquals("7", l_response.commission);
            assertEquals("7", l_response.consumptionTax);
            assertEquals("0", l_response.execPrice);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * レスポンス.オプション商品区分 = PUT_OPTIONS
     * レスポンス.注文単価区分 = 注文単位.isMarketOrderの返り値がfalseの場合は、"指値"
     * 注文単位.発注条件が"W指値"の場合
     * レスポンス.W指値用注文単価 = 1
     */
    public void testGetOrderExecutedDetail_C0007()
    {
        final String STR_METHOD_NAME = "testgetOrderExecutedDetail_C0007()";

        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "isEveningSessionOrder",
                new Class[]{ IfoOrderUnit.class },
                new Boolean(false));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setLimitPrice(1);
            l_ifoOrderUnitParams.setFirstOrderUnitId(11);
            l_ifoOrderUnitParams.setOrderConditionType("2");
            l_ifoOrderUnitParams.setExpirationDate(GtlUtils.getTradingSystem().getBizDate());
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_ifoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
            l_ifoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.FUTURE_OP_ADMIN_MANUAL_EXPIRED);
            l_ifoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.ACCEPT_ERROR);
            l_ifoOrderUnitParams.setOrgStopOrderPrice(100);
            l_ifoOrderUnitParams.setOrgOrderConditionType("2");
            l_ifoOrderUnitParams.setRequestType("2");
            l_ifoOrderUnitParams.setWLimitBeforeExecCondType(IfoOrderExecutionConditionType.NONE);
            l_ifoOrderUnitParams.setOrgWLimitPrice(1);
            l_ifoOrderUnitParams.setOrgWLimitExecCondType(IfoOrderExecutionConditionType.NONE);
            l_ifoOrderUnitParams.setExecutedQuantity(100);
            l_ifoOrderUnitParams.setExecutedAmount(1);
            l_ifoOrderUnitParams.setWLimitExecCondType(IfoOrderExecutionConditionType.NONE);
            l_ifoOrderUnitParams.setWLimitPrice(1);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoproductParams = TestDBUtility.getIfoProductRow();
            l_ifoproductParams.setProductId(1006169090018L);
            l_ifoproductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            TestDBUtility.insertWithDel(l_ifoproductParams);
            
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setOrderId(1001);
            l_ifoFinTransactionParams.setOrderUnitId(1001);
            l_ifoFinTransactionParams.setCommissionFee(7.0d);
            l_ifoFinTransactionParams.setCommissionFeeTax(7.0d);
            l_ifoFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(1006169090018L);
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3OptionsExecuteDetailsRequest l_request = new WEB3OptionsExecuteDetailsRequest();
            l_request.id = "1001";
            
            WEB3OptionOrderExecutedInquiryServiceImpl l_impl = new WEB3OptionOrderExecutedInquiryServiceImpl();
            WEB3OptionsExecuteDetailsResponse l_response = l_impl.getOrderExecutedDetail(l_request);

            assertEquals("7", l_response.commission);
            assertEquals("7", l_response.consumptionTax);
            assertEquals("0", l_response.execPrice);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * リクエストデータの整合性をチェックする失敗
     * WEB3ErrorCatalog.BUSINESS_ERROR_00080
     */
    public void testGetSettleContractList_C0001()
    {
        final String STR_METHOD_NAME = "testGetSettleContractList_C0001()";

        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3OptionsCloseMarginContractListRequest l_request = new WEB3OptionsCloseMarginContractListRequest();
            l_request.id = "";
            
            WEB3OptionOrderExecutedInquiryServiceImpl l_impl = new WEB3OptionOrderExecutedInquiryServiceImpl();
            l_impl.getSettleContractList(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * 受付時間チェック、システム売買停止チェック失敗
     * WEB3ErrorCatalog.BUSINESS_ERROR_00080
     */
    public void testGetSettleContractList_C0002()
    {
        final String STR_METHOD_NAME = "testGetSettleContractList_C0002()";

        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            WEB3OptionsCloseMarginContractListRequest l_request = new WEB3OptionsCloseMarginContractListRequest();
            l_request.id = "1001";
            
            WEB3OptionOrderExecutedInquiryServiceImpl l_impl = new WEB3OptionOrderExecutedInquiryServiceImpl();
            l_impl.getSettleContractList(l_request);

            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * 指定した注文IDに該当するデータが存在しません。
     * WEB3ErrorCatalog.BUSINESS_ERROR_00398
     */
    public void testGetSettleContractList_C0003()
    {
        final String STR_METHOD_NAME = "testGetSettleContractList_C0003()";

        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            
            WEB3OptionsCloseMarginContractListRequest l_request = new WEB3OptionsCloseMarginContractListRequest();
            l_request.id = "1001";
            
            WEB3OptionOrderExecutedInquiryServiceImpl l_impl = new WEB3OptionOrderExecutedInquiryServiceImpl();
            l_impl.getSettleContractList(l_request);

            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * 返済注文以外の場合
     * WEB3ErrorCatalog.SYSTEM_ERROR_80025
     */
    public void testGetSettleContractList_C0004()
    {
        final String STR_METHOD_NAME = "testGetSettleContractList_C0004()";

        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3OptionsCloseMarginContractListRequest l_request = new WEB3OptionsCloseMarginContractListRequest();
            l_request.id = "1001";
            
            WEB3OptionOrderExecutedInquiryServiceImpl l_impl = new WEB3OptionOrderExecutedInquiryServiceImpl();
            l_impl.getSettleContractList(l_request);

            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80025, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * 注文単位に該当する返済指定情報が取得できなかった場合
     * WEB3ErrorCatalog.BUSINESS_ERROR_00398
     */
    public void testGetSettleContractList_C0005()
    {
        final String STR_METHOD_NAME = "testGetSettleContractList_C0005()";

        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_CLOSE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoClosingContractSpecRow.TYPE);
            
            WEB3OptionsCloseMarginContractListRequest l_request = new WEB3OptionsCloseMarginContractListRequest();
            l_request.id = "1001";
            
            WEB3OptionOrderExecutedInquiryServiceImpl l_impl = new WEB3OptionOrderExecutedInquiryServiceImpl();
            l_impl.getSettleContractList(l_request);

            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * get建玉失敗的場合    
     * WEB3ErrorCatalog.SYSTEM_ERROR_80005
     */
    public void testGetSettleContractList_C0006()
    {
        final String STR_METHOD_NAME = "testGetSettleContractList_C0006()";

        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_CLOSE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoClosingContractSpecRow.TYPE);
            IfoClosingContractSpecParams l_ifoClosingContractSpecParams = TestDBUtility.getIfoClosingContractSpecRow();
            l_ifoClosingContractSpecParams.setClosingContractSpecId(1001);
            l_ifoClosingContractSpecParams.setAccountId(101001010010L);
            l_ifoClosingContractSpecParams.setOrderUnitId(1001);
            l_ifoClosingContractSpecParams.setContractId(1001);
            l_ifoClosingContractSpecParams.setQuantity(0.0D);
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            
            WEB3OptionsCloseMarginContractListRequest l_request = new WEB3OptionsCloseMarginContractListRequest();
            l_request.id = "1001";
            
            WEB3OptionOrderExecutedInquiryServiceImpl l_impl = new WEB3OptionOrderExecutedInquiryServiceImpl();
            l_impl.getSettleContractList(l_request);

            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * 注文単位.getLimitPrice() = 0  
     * 先物OP建玉.isLong() = true
     */
    public void testGetSettleContractList_C0007()
    {
        final String STR_METHOD_NAME = "testGetSettleContractList_C0007()";

        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_CLOSE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoClosingContractSpecRow.TYPE);
            IfoClosingContractSpecParams l_ifoClosingContractSpecParams1 = TestDBUtility.getIfoClosingContractSpecRow();
            l_ifoClosingContractSpecParams1.setClosingContractSpecId(1001);
            l_ifoClosingContractSpecParams1.setAccountId(101001010010L);
            l_ifoClosingContractSpecParams1.setOrderUnitId(1001);
            l_ifoClosingContractSpecParams1.setContractId(1002);
            l_ifoClosingContractSpecParams1.setQuantity(0.0D);
            l_ifoClosingContractSpecParams1.setClosingSerialNo(222);
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams1);
            IfoClosingContractSpecParams l_ifoClosingContractSpecParams2 = TestDBUtility.getIfoClosingContractSpecRow();
            l_ifoClosingContractSpecParams2.setClosingContractSpecId(1002);
            l_ifoClosingContractSpecParams2.setAccountId(101001010011L);
            l_ifoClosingContractSpecParams2.setOrderUnitId(1001);
            l_ifoClosingContractSpecParams2.setContractId(1001);
            l_ifoClosingContractSpecParams2.setQuantity(100);
            l_ifoClosingContractSpecParams2.setExecutedQuantity(80);
            l_ifoClosingContractSpecParams2.setClosingSerialNo(111);
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams2);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1001);
            l_ifoContractParams.setProductId(1006149081018L);
            l_ifoContractParams.setContractPrice(15);
            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20080808","yyyyMMdd"));
            l_ifoContractParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006149081018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006149081018L);
            l_ifoProductParams.setUnderlyingProductCode("0001");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006149081018L);
            l_tradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(1006149081018L);
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setOrderUnitId(1001);
            l_ifoFinTransactionParams.setContractId(1001);
            l_ifoFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            l_ifoFinTransactionParams.setPrice(25);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            
            WEB3OptionsCloseMarginContractListRequest l_request = new WEB3OptionsCloseMarginContractListRequest();
            l_request.id = "1001";
            
            WEB3OptionOrderExecutedInquiryServiceImpl l_impl = new WEB3OptionOrderExecutedInquiryServiceImpl();
            WEB3OptionsCloseMarginContractListResponse l_response = l_impl.getSettleContractList(l_request);

            assertEquals("2", l_response.closeMarginContractGroups[0].execPrice);
            assertEquals("11", l_response.closeMarginContractGroups[0].income);
            assertEquals("40009", l_response.closeMarginContractGroups[0].contractCommission);
            assertEquals("2010", l_response.closeMarginContractGroups[0].contractCommissionConsumptionTax);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * 注文単位.getLimitPrice() = 1  
     * 先物OP建玉.isLong() = false
     */
    public void testGetSettleContractList_C0008()
    {
        final String STR_METHOD_NAME = "testGetSettleContractList_C0008()";

        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_CLOSE);
            l_ifoOrderUnitParams.setLimitPrice(1);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoClosingContractSpecRow.TYPE);
            IfoClosingContractSpecParams l_ifoClosingContractSpecParams1 = TestDBUtility.getIfoClosingContractSpecRow();
            l_ifoClosingContractSpecParams1.setClosingContractSpecId(1001);
            l_ifoClosingContractSpecParams1.setAccountId(101001010010L);
            l_ifoClosingContractSpecParams1.setOrderUnitId(1001);
            l_ifoClosingContractSpecParams1.setContractId(1002);
            l_ifoClosingContractSpecParams1.setQuantity(0.0D);
            l_ifoClosingContractSpecParams1.setClosingSerialNo(222);
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams1);
            IfoClosingContractSpecParams l_ifoClosingContractSpecParams2 = TestDBUtility.getIfoClosingContractSpecRow();
            l_ifoClosingContractSpecParams2.setClosingContractSpecId(1002);
            l_ifoClosingContractSpecParams2.setAccountId(101001010011L);
            l_ifoClosingContractSpecParams2.setOrderUnitId(1001);
            l_ifoClosingContractSpecParams2.setContractId(1001);
            l_ifoClosingContractSpecParams2.setQuantity(100);
            l_ifoClosingContractSpecParams2.setExecutedQuantity(80);
            l_ifoClosingContractSpecParams2.setClosingSerialNo(111);
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams2);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1001);
            l_ifoContractParams.setProductId(1006149081018L);
            l_ifoContractParams.setContractPrice(15);
            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20080808","yyyyMMdd"));
            l_ifoContractParams.setMarketId(1002);
            l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006149081018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006149081018L);
            l_ifoProductParams.setUnderlyingProductCode("0001");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006149081018L);
            l_tradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(1006149081018L);
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setOrderUnitId(1001);
            l_ifoFinTransactionParams.setContractId(1001);
            l_ifoFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            l_ifoFinTransactionParams.setPrice(25);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            
            WEB3OptionsCloseMarginContractListRequest l_request = new WEB3OptionsCloseMarginContractListRequest();
            l_request.id = "1001";
            
            WEB3OptionOrderExecutedInquiryServiceImpl l_impl = new WEB3OptionOrderExecutedInquiryServiceImpl();
            WEB3OptionsCloseMarginContractListResponse l_response = l_impl.getSettleContractList(l_request);

            assertEquals("2", l_response.closeMarginContractGroups[0].execPrice);
            assertEquals("11", l_response.closeMarginContractGroups[0].income);
            assertEquals("40009", l_response.closeMarginContractGroups[0].contractCommission);
            assertEquals("2010", l_response.closeMarginContractGroups[0].contractCommissionConsumptionTax);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsChangeCancelEnableC0001()
    {
        final String STR_METHOD_NAME = " testIsChangeCancelEnableC0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_ifoOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_ifoOrderUnitParams.setOrderId(1111);
            l_ifoOrderUnitParams.setBranchId(33381);
            l_ifoOrderUnitParams.setBizDate("20050710");
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1111);
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3OptionOrderExecutedInquiryServiceImpl l_impl = new WEB3OptionOrderExecutedInquiryServiceImpl();
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    l_subAccountParams.getAccountId(), l_subAccountParams.getSubAccountId());

            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(1001);
            
            Method method = WEB3OptionOrderExecutedInquiryServiceImpl.class.getDeclaredMethod(
                "isChangeCancelEnable",
                new Class[]{WEB3GentradeSubAccount.class, IfoOrderUnit.class});
                
            method.setAccessible(true);
                
            Object[] l_obj = {l_subAccount, l_orderUnit};
            
            boolean l_result = ((Boolean)method.invoke(l_impl, l_obj)).booleanValue();
            
            assertTrue(l_result);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);   
    }
    
    public void testIsChangeCancelEnableC0002()
    {
        final String STR_METHOD_NAME = " testIsChangeCancelEnableC0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            Date l_datExpect = WEB3DateUtility.getDate("20060710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_ifoOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_ifoOrderUnitParams.setOrderId(1111);
            l_ifoOrderUnitParams.setBranchId(33381);
            l_ifoOrderUnitParams.setBizDate("20050710");
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1111);
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            
            WEB3OptionOrderExecutedInquiryServiceImpl l_impl = new WEB3OptionOrderExecutedInquiryServiceImpl();
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    l_subAccountParams.getAccountId(), l_subAccountParams.getSubAccountId());

            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(1001);
            
            Method method = WEB3OptionOrderExecutedInquiryServiceImpl.class.getDeclaredMethod(
                "isChangeCancelEnable",
                new Class[]{WEB3GentradeSubAccount.class, IfoOrderUnit.class});
                
            method.setAccessible(true);
                
            Object[] l_obj = {l_subAccount, l_orderUnit};
            
            boolean l_result = ((Boolean)method.invoke(l_impl, l_obj)).booleanValue();
            
            assertFalse(l_result);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);   
    }
    
    //create注文約定照会
    //先物OP銘柄≠NULL
    //発注条件区分≠NULL
    //察@詢條件中有and product_id = ? and nvl(org_order_condition_type,order_condition_type) = ?
    //沒有檢索出數據 返回null
    public void testCreateOrderExecutedInquiryCase1()
    {
        final String STR_METHOD_NAME = "testCreateOrderExecutedInquiryCase1";

        log.entering(STR_METHOD_NAME);

        try
        {
            //MarketRow
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            //MainAccountRow
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //IfoProductRow
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            //ProductRow
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            //SubAccountRow
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = ifoOrderUnit();
            l_ifoOrderUnitParams.setProductId(1006169090018L);
            l_ifoOrderUnitParams.setBizDate("20061211");
            l_ifoOrderUnitParams.setAccountId(201001010010L);
            l_ifoOrderUnitParams.setSubAccountId(10100101001007L);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setSessionType("5");
            l_ifoOrderUnitParams.setMarketId(1002);
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            l_ifoOrderUnitParams.setOrderConditionType("0");
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3OptionOrderExecutedInquiryServiceImpl l_impl =
                new WEB3OptionOrderExecutedInquiryServiceImpl();
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L,10100101001007L);
            WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
            l_request.orderBizDate = WEB3DateUtility.getDate("20061211","yyyyMMdd");
            l_request.orderCondType = "0";
            WEB3OptionsExecuteReferenceResponse l_response = null;
            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(1006169090018L);
            WEB3OptionsExecuteGroup[] l_executeGroups =
            l_impl.createOrderExecutedInquiry(l_subAccount, l_request, l_response, l_ifoProduct);
            assertNull(l_executeGroups);
            
        }
        catch (Exception e)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //create注文約定照会
    //先物OP銘柄≠NULL
    //発注条件区分≠NULL
    //察@詢條件中有and product_id = ? and nvl(org_order_condition_type,order_condition_type) = ?
    //察@出一條記路
    public void testCreateOrderExecutedInquiryCase2()
    {
        final String STR_METHOD_NAME = "testCreateOrderExecutedInquiryCase2";

        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006169090018L);
            l_IfoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            //MarketRow
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            //MainAccountRow
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //IfoProductRow
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            //ProductRow
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            //SubAccountRow
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = ifoOrderUnit();
            l_ifoOrderUnitParams.setProductId(1006169090018L);
            l_ifoOrderUnitParams.setBizDate("20061211");
            l_ifoOrderUnitParams.setAccountId(101001010010L);
            l_ifoOrderUnitParams.setSubAccountId(10100101001007L);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setSessionType("5");
            l_ifoOrderUnitParams.setFutureOptionDiv("2");
            l_ifoOrderUnitParams.setOrgOrderConditionType("0");
            l_ifoOrderUnitParams.setOrderConditionType(null);
            l_ifoOrderUnitParams.setMarketId(1002);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            //IfoOrderRow
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            WEB3OptionOrderExecutedInquiryServiceImplFortest l_impl =
                new WEB3OptionOrderExecutedInquiryServiceImplFortest();
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L,10100101001007L);
            WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
            l_request.orderBizDate = WEB3DateUtility.getDate("20061211","yyyyMMdd");
            l_request.orderCondType = "0";//  発注条件区分≠NULL
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3OptionsExecuteReferenceResponse l_response = new WEB3OptionsExecuteReferenceResponse();
            //  先物OP銘柄≠NULL
            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(1006169090018L);

            MOCK_MANAGER.setIsMockUsed(true);
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

            WEB3OptionsExecuteGroup[] l_executeGroups =
            l_impl.createOrderExecutedInquiry(l_subAccount, l_request, l_response, l_ifoProduct);
            assertEquals(l_executeGroups[0].id, "1001");
            
        }
        catch (Exception e)
        {
            log.error("", e);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //create注文約定照会
    //先物OP銘柄==NULL
    //発注条件区分≠NULL
    //察@詢條件中有 and nvl(org_order_condition_type,order_condition_type) = ?
    //察@出一條記路
    public void testCreateOrderExecutedInquiryCase3()
    {
        final String STR_METHOD_NAME = "testCreateOrderExecutedInquiryCase3";

        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006169090018L);
            l_IfoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            //MarketRow
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            //MainAccountRow
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //IfoProductRow
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            //ProductRow
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            //SubAccountRow
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = ifoOrderUnit();
            l_ifoOrderUnitParams.setProductId(1006169090018L);
            l_ifoOrderUnitParams.setBizDate("20061211");
            l_ifoOrderUnitParams.setAccountId(101001010010L);
            l_ifoOrderUnitParams.setSubAccountId(10100101001007L);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setSessionType("5");
            l_ifoOrderUnitParams.setFutureOptionDiv("2");
            l_ifoOrderUnitParams.setOrgOrderConditionType(null);
            l_ifoOrderUnitParams.setOrderConditionType("0");
            l_ifoOrderUnitParams.setMarketId(1002);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            //IfoOrderRow
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            WEB3OptionOrderExecutedInquiryServiceImplFortest l_impl =
                new WEB3OptionOrderExecutedInquiryServiceImplFortest();
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L,10100101001007L);
            WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
            l_request.orderBizDate = WEB3DateUtility.getDate("20061211","yyyyMMdd");
            l_request.orderCondType = "0";//  発注条件区分≠NULL
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3OptionsExecuteReferenceResponse l_response = new WEB3OptionsExecuteReferenceResponse();
            //  先物OP銘柄==NULL
            WEB3IfoProductImpl l_ifoProduct = null;
            WEB3OptionsExecuteGroup[] l_executeGroups =
            l_impl.createOrderExecutedInquiry(l_subAccount, l_request, l_response, l_ifoProduct);
            assertEquals(l_executeGroups[0].id, "1001");
            
        }
        catch (Exception e)
        {
            log.error("", e);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //create注文約定照会
    //先物OP銘柄≠NULL
    //発注条件区分==NULL
    //察@詢條件中有 and nvl(org_order_condition_type,order_condition_type) = ?
    //察@出一條記路
    public void testCreateOrderExecutedInquiryCase4()
    {
        final String STR_METHOD_NAME = "testCreateOrderExecutedInquiryCase3";

        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006169090018L);
            l_IfoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            //MarketRow
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            //MainAccountRow
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //IfoProductRow
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            //ProductRow
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            //SubAccountRow
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = ifoOrderUnit();
            l_ifoOrderUnitParams.setProductId(1006169090018L);
            l_ifoOrderUnitParams.setBizDate("20061211");
            l_ifoOrderUnitParams.setAccountId(101001010010L);
            l_ifoOrderUnitParams.setSubAccountId(10100101001007L);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setSessionType("5");
            l_ifoOrderUnitParams.setFutureOptionDiv("2");
            l_ifoOrderUnitParams.setOrgOrderConditionType("0");
            l_ifoOrderUnitParams.setOrderConditionType("1");
            l_ifoOrderUnitParams.setMarketId(1002);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            //IfoOrderRow
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            WEB3OptionOrderExecutedInquiryServiceImplFortest l_impl =
                new WEB3OptionOrderExecutedInquiryServiceImplFortest();
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L,10100101001007L);
            WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
            l_request.orderBizDate = WEB3DateUtility.getDate("20061211","yyyyMMdd");
            l_request.orderCondType = null;//  発注条件区分==NULL
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3OptionsExecuteReferenceResponse l_response = new WEB3OptionsExecuteReferenceResponse();
            //  先物OP銘柄≠NULL
            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(1006169090018L);
            WEB3OptionsExecuteGroup[] l_executeGroups =
            l_impl.createOrderExecutedInquiry(l_subAccount, l_request, l_response, l_ifoProduct);
            assertEquals(l_executeGroups[0].id, "1001");
            
        }
        catch (Exception e)
        {
            log.error("", e);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    class WEB3OptionOrderExecutedInquiryServiceImplFortest extends WEB3OptionOrderExecutedInquiryServiceImpl
    {
        public Trader getTrader() throws WEB3SystemLayerException
        {
            final String STR_METHOD_NAME = "getTrader()";
            log.entering(STR_METHOD_NAME);
            
            return null;
            
        }
        
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

}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.48.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesExecuteReferenceServiceImplTest_ES.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 
Author Name      : Daiwa Institute of Research
Revesion History : 
Revesion History : 2007/08/22 劉剣 (中訊) IFO小数点対応
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
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
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionRow;
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
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingClendarDetailsImpl;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3FuturesChangeCancelHistoryGroup;
import webbroker3.ifo.message.WEB3FuturesCloseMarginContractGroup;
import webbroker3.ifo.message.WEB3FuturesCloseMarginContractListRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginContractListResponse;
import webbroker3.ifo.message.WEB3FuturesExecuteDetailsRequest;
import webbroker3.ifo.message.WEB3FuturesExecuteDetailsResponse;
import webbroker3.ifo.message.WEB3FuturesExecuteGroup;
import webbroker3.ifo.message.WEB3FuturesExecuteReferenceRequest;
import webbroker3.ifo.message.WEB3FuturesExecuteReferenceResponse;
import webbroker3.ifo.message.WEB3FuturesOrderHistoryRequest;
import webbroker3.ifo.message.WEB3FuturesOrderHistoryResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesExecuteReferenceServiceImplTest_ES extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesExecuteReferenceServiceImplTest_ES.class);

    public WEB3FuturesExecuteReferenceServiceImplTest_ES(String name)
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
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public static String TRADING_CAL_CONTEXT_PATH = "web3.tradingcalendarcontext";

    /**
     * get注文約定詳細
     * レスポンス.注文有効期限 = null
     * 夕場前繰越対象フラグ = false
     * 立会区分 = "5"
     */
    public void test_getOrderExecuteDetail_0001()
    {
        WEB3FuturesExecuteDetailsRequest l_request = new WEB3FuturesExecuteDetailsRequest();
        l_request.id = "1001";
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = ifoOrderUnit();
            l_ifoOrderUnitParams.setSessionType("5");
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("11");
            l_context.setBranchCode("22");
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
            l_context.setTradingTimeType("1");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    TRADING_CAL_CONTEXT_PATH,l_context);
            
            WEB3FuturesExecuteReferenceServiceImpl l_impl = new WEB3FuturesExecuteReferenceServiceImpl();
            
            WEB3FuturesExecuteDetailsResponse l_response = l_impl.getOrderExecuteDetail(l_request);
            
            assertEquals("5",l_response.sessionType);
            assertFalse(l_response.eveningSessionCarryoverFlag);
            assertNull(l_response.expirationDate);
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
    }
    
    /**
     * get注文約定詳細
     * レスポンス.注文有効期限 = null
     * 夕場前繰越対象フラグ = true
     * 立会区分 = "5"
     */
    public void test_getOrderExecuteDetail_0002()
    {
        WEB3FuturesExecuteDetailsRequest l_request = new WEB3FuturesExecuteDetailsRequest();
        l_request.id = "1001";
        
        try
        {
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = ifoOrderUnit();
            l_ifoOrderUnitParams.setSessionType("5");
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("11");
            l_context.setBranchCode("22");
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
            l_context.setTradingTimeType("1");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    TRADING_CAL_CONTEXT_PATH,l_context);
            
            WEB3FuturesExecuteReferenceServiceImpl l_impl = new WEB3FuturesExecuteReferenceServiceImpl();
            
            WEB3FuturesExecuteDetailsResponse l_response = l_impl.getOrderExecuteDetail(l_request);
            
            assertEquals("5",l_response.sessionType);
            assertTrue(l_response.eveningSessionCarryoverFlag);
            
            assertEquals("0","" + WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20040101", "yyyyMMdd"), l_response.expirationDate));
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
    }
    /*
     * レスポンス.注文有効期限 = null
     * 夕場前繰越対象フラグ = true
     * 立会区分 = "5"
     * 約定がある場合(注文単位.isUnexecutedがfalse)のみセット
     */
    public void test_getOrderExecuteDetail_0003()
    {
        WEB3FuturesExecuteDetailsRequest l_request = new WEB3FuturesExecuteDetailsRequest();
        l_request.id = "1001";
        
        try
        {
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = ifoOrderUnit();
            l_ifoOrderUnitParams.setSessionType("5");
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
            l_ifoOrderUnitParams.setExecutedQuantity(100);
            l_ifoOrderUnitParams.setExecutedAmount(200);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setOrderId(1001);
            l_ifoFinTransactionParams.setOrderUnitId(1001);
            l_ifoFinTransactionParams.setCommissionFeeTax(7);
            l_ifoFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("11");
            l_context.setBranchCode("22");
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
            l_context.setTradingTimeType("1");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    TRADING_CAL_CONTEXT_PATH,l_context);
            
            WEB3FuturesExecuteReferenceServiceImpl l_impl = new WEB3FuturesExecuteReferenceServiceImpl();
            
            WEB3FuturesExecuteDetailsResponse l_response = l_impl.getOrderExecuteDetail(l_request);
            
            assertEquals("0", l_response.execPrice);
            assertEquals("7", l_response.commission);
            assertEquals("7", l_response.consumptionTax);
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
    }
    
    /**
     * get注文履歴照会
     * 先物OPデータアダプタ.get注文期限区分() = 2：出来るまで注文 の場合
     * レスポンス.注文有効期限 = GtlUtils.getSystemTimestamp()
     * 
     * 夕場前繰越対象フラグ = true
     * 立会区分 = 5
     */
    public void test_getOrderHistoryInquiry_0011()
    {
        WEB3FuturesOrderHistoryRequest l_request = new WEB3FuturesOrderHistoryRequest();
        l_request.id = "1001";
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            
            TestDBUtility.deleteAll(IfoOrderActionRow.TYPE);
            IfoOrderActionParams l_ifoOrderActionParams = getIfoOrderActionRow();
            TestDBUtility.insertWithDel(l_ifoOrderActionParams);
            
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = ifoOrderUnit();
            l_ifoOrderUnitParams.setSessionType("5");
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("11");
            l_context.setBranchCode("22");
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
            l_context.setTradingTimeType("1");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    TRADING_CAL_CONTEXT_PATH,l_context);
            
            WEB3FuturesExecuteReferenceServiceImpl l_impl = new WEB3FuturesExecuteReferenceServiceImpl();
            
            WEB3FuturesOrderHistoryResponse l_response  = l_impl.getOrderHistoryInquiry(l_request);
            
            WEB3FuturesChangeCancelHistoryGroup[] l_fuChangeCancelHistoryGroups = l_response.futChangeCancelHistoryGroups;
            
            
            assertEquals("0","" + WEB3DateUtility.compareToDay(Calendar.getInstance().getTime(),l_fuChangeCancelHistoryGroups[0].expirationDate));
            assertTrue(l_fuChangeCancelHistoryGroups[0].eveningSessionCarryoverFlag);
            assertEquals("5",l_fuChangeCancelHistoryGroups[0].sessionType);
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
    }
    
    /**
     * get注文履歴照会
     * 先物OPデータアダプタ.get注文期限区分() = 2：出来るまで注文 の場合
     * レスポンス.注文有効期限 = GtlUtils.getSystemTimestamp()
     * 
     * 夕場前繰越対象フラグ = true
     * 立会区分 = 5
     */
    public void test_getOrderHistoryInquiry_0012()
    {
        WEB3FuturesOrderHistoryRequest l_request = new WEB3FuturesOrderHistoryRequest();
        l_request.id = "1001";
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            
            TestDBUtility.deleteAll(IfoOrderActionRow.TYPE);
            IfoOrderActionParams l_ifoOrderActionParams = getIfoOrderActionRow();
            TestDBUtility.insertWithDel(l_ifoOrderActionParams);
            
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = ifoOrderUnit();
            l_ifoOrderUnitParams.setSessionType("5");
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
//            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("11");
            l_context.setBranchCode("22");
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
            l_context.setTradingTimeType("1");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    TRADING_CAL_CONTEXT_PATH,l_context);
            
            WEB3FuturesExecuteReferenceServiceImpl l_impl = new WEB3FuturesExecuteReferenceServiceImpl();
            
            WEB3FuturesOrderHistoryResponse l_response  = l_impl.getOrderHistoryInquiry(l_request);
            
            WEB3FuturesChangeCancelHistoryGroup[] l_fuChangeCancelHistoryGroups = l_response.futChangeCancelHistoryGroups;
            
            
            assertNull(l_fuChangeCancelHistoryGroups[0].expirationDate);
            assertTrue(l_fuChangeCancelHistoryGroups[0].eveningSessionCarryoverFlag);
            assertEquals("5",l_fuChangeCancelHistoryGroups[0].sessionType);
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
    }
    
    /**
     * get返済建玉一覧
     * 立会区分 = "1"
     */
    public void test_getSettleContractList_0111()
    {
        WEB3FuturesCloseMarginContractListRequest l_request = new WEB3FuturesCloseMarginContractListRequest();
        l_request.id = "1001";
        
        try
        {
            TestDBUtility.deleteAll(IfoClosingContractSpecRow.TYPE);
            IfoClosingContractSpecParams l_ifoClosingContractSpecParams = TestDBUtility.getIfoClosingContractSpecRow();
            l_ifoClosingContractSpecParams.setOrderUnitId(1001);
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setSessionType("1");
            l_ifoContractParams.setDeliveryDate(Calendar.getInstance().getTime());
//            l_ifoContractParams.setContractId("");
            l_ifoContractParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoOrderActionRow.TYPE);
            IfoOrderActionParams l_ifoOrderActionParams = getIfoOrderActionRow();
            TestDBUtility.insertWithDel(l_ifoOrderActionParams);
            
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = ifoOrderUnit();
            l_ifoOrderUnitParams.setSessionType("5");
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
//            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("11");
            l_context.setBranchCode("22");
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
            l_context.setTradingTimeType("1");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    TRADING_CAL_CONTEXT_PATH,l_context);
            
            WEB3FuturesExecuteReferenceServiceImpl l_impl = new WEB3FuturesExecuteReferenceServiceImpl();
            
            WEB3FuturesCloseMarginContractListResponse l_response  = l_impl.getSettleContractList(l_request);
            WEB3FuturesCloseMarginContractGroup[] l_futuresCloseMarginContractGroup= l_response.closeMarginContractGroups;
            
            assertEquals("1",l_futuresCloseMarginContractGroup[0].sessionType);
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
    }
        /*
         * 立会区分 = "1"
         * 返済約定数量 = 100
         * 返済注文数量 = 200
         */
        public void test_getSettleContractList_0112()
        {
            WEB3FuturesCloseMarginContractListRequest l_request = new WEB3FuturesCloseMarginContractListRequest();
            l_request.id = "1001";
            
            try
            {
                TestDBUtility.deleteAll(IfoClosingContractSpecRow.TYPE);
                IfoClosingContractSpecParams l_ifoClosingContractSpecParams = TestDBUtility.getIfoClosingContractSpecRow();
                l_ifoClosingContractSpecParams.setOrderUnitId(1001);
                l_ifoClosingContractSpecParams.setQuantity(200);
                TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);
                
                TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
                IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
                TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
                
                TestDBUtility.deleteAll(IfoContractRow.TYPE);
                IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
                l_ifoContractParams.setSessionType("1");
                l_ifoContractParams.setDeliveryDate(Calendar.getInstance().getTime());
//                l_ifoContractParams.setContractId("");
                l_ifoContractParams.setProductId(1006169090018L);
                TestDBUtility.insertWithDel(l_ifoContractParams);
                
                TestDBUtility.deleteAll(IfoOrderActionRow.TYPE);
                IfoOrderActionParams l_ifoOrderActionParams = getIfoOrderActionRow();
                TestDBUtility.insertWithDel(l_ifoOrderActionParams);
                
                
                TestDBUtility.deleteAll(MarketRow.TYPE);
                MarketParams l_marketParams = getMarketRow();
                TestDBUtility.insertWithDel(l_marketParams);
                
                
                TestDBUtility.deleteAll(BranchRow.TYPE);
                BranchParams l_branchParams = TestDBUtility.getBranchRow();
                TestDBUtility.insertWithDel(l_branchParams);
                
                TestDBUtility.deleteAll(MainAccountRow.TYPE);
                MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
                l_mainAccountParams.setAccountId(101001010010L);
                l_mainAccountParams.setIfoAccOpenDivOsaka("1");
                TestDBUtility.insertWithDel(l_mainAccountParams);
                
                
                TestDBUtility.deleteAll(IfoProductRow.TYPE);
                IfoProductParams l_ifoProductParams = getIfoProductRow();
                TestDBUtility.insertWithDel(l_ifoProductParams);
                
                
                TestDBUtility.deleteAll(ProductRow.TYPE);
                ProductParams l_productParams = getProductRow();
                TestDBUtility.insertWithDel(l_productParams);
                
                
                TestDBUtility.deleteAll(SubAccountRow.TYPE);
                SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
                l_subAccountParams.setAccountId(101001010010L);
                l_subAccountParams.setSubAccountId(10100101001007L);
                l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
                TestDBUtility.insertWithDel(l_subAccountParams);
                
                
                TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
                IfoOrderUnitParams l_ifoOrderUnitParams = ifoOrderUnit();
                l_ifoOrderUnitParams.setSessionType("5");
                l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
//                l_ifoOrderUnitParams.setFirstOrderUnitId(0);
                l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
                TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
                
                TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
                IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
                l_ifoFinTransactionParams.setOrderUnitId(1001);
                l_ifoFinTransactionParams.setContractId(1001);
                l_ifoFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
                TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
                
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                        "getAccountId",
                        new Class[] {},
                        new Long(101001010010L));
                
                WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
                l_context.setInstitutionCode("11");
                l_context.setBranchCode("22");
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
                l_context.setTradingTimeType("1");
                
                ThreadLocalSystemAttributesRegistry.setAttribute(
                        TRADING_CAL_CONTEXT_PATH,l_context);
                
                WEB3FuturesExecuteReferenceServiceImpl l_impl = new WEB3FuturesExecuteReferenceServiceImpl();
                
                WEB3FuturesCloseMarginContractListResponse l_response  = l_impl.getSettleContractList(l_request);
                WEB3FuturesCloseMarginContractGroup[] l_futuresCloseMarginContractGroup= l_response.closeMarginContractGroups;
                
                assertEquals("0",l_futuresCloseMarginContractGroup[0].execPrice);
                assertEquals("11",l_futuresCloseMarginContractGroup[0].settleProfitLoss);
                assertEquals("200009",l_futuresCloseMarginContractGroup[0].contractCommission);
                assertEquals("10010",l_futuresCloseMarginContractGroup[0].contractCommissionConsumptionTax);
            }
            catch (WEB3BaseException e)
            {
                fail();
            }
    }
    
//    /**
//     * create注文約定照会
//
//     */
//    public void test_createOrderExecutedInquiry_1111()
//    {
//        WEB3FuturesExecuteReferenceRequest l_request = new WEB3FuturesExecuteReferenceRequest();
//        l_request.id = "1001";
//        
//        try
//        {
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_marketParams = getMarketRow();
//            TestDBUtility.insertWithDel(l_marketParams);
//            
//            
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchParams);
//            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(101001010010L);
//            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            
//            TestDBUtility.deleteAll(IfoProductRow.TYPE);
//            IfoProductParams l_ifoProductParams = getIfoProductRow();
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_productParams = getProductRow();
//            TestDBUtility.insertWithDel(l_productParams);
//            
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            
//            
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            IfoOrderUnitParams l_ifoOrderUnitParams = ifoOrderUnit();
//            l_ifoOrderUnitParams.setSessionType("5");
//            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "getAccountId",
//                    new Class[] {},
//                    new Long(101001010010L));
//            
//            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
//            l_context.setInstitutionCode("11");
//            l_context.setBranchCode("22");
//            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
//            l_context.setTradingTimeType("1");
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                    TRADING_CAL_CONTEXT_PATH,l_context);
//            
//            WEB3FuturesExecuteReferenceServiceImpl l_impl = new WEB3FuturesExecuteReferenceServiceImpl();
//            WEB3FuturesExecuteReferenceResponse l_response = new WEB3FuturesExecuteReferenceResponse();
//            
//            l_impl.createOrderExecutedInquiry(l_request);
//            
//            assertEquals("5",l_response.sessionType);
//            assertFalse(l_response.eveningSessionCarryoverFlag);
//            assertNull(l_response.expirationDate);
//        }
//        catch (WEB3BaseException e)
//        {
//            fail();
//        }
//    }
    
    /**
     * isChangeCancelEnable
     */
    public void test_isChangeCancelEnable_11111()
    {
        WEB3FuturesExecuteReferenceServiceImpl l_impl = new WEB3FuturesExecuteReferenceServiceImpl();
        
               
//        ParameterCheckStage parameterCheckStage = (ParameterCheckStage)(new Frw00InstanceFactoryForTest().getInstance(ParameterCheckStage.class));
        Method method;
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeBranch",
                    "isEveningSessionEnforcemented", 
                    new Class[]{ ProductTypeEnum.class },
                    new Boolean(true));
                    
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setSessionType("1");
            l_ifoContractParams.setDeliveryDate(Calendar.getInstance().getTime());
//            l_ifoContractParams.setContractId("");
            l_ifoContractParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoOrderActionRow.TYPE);
            IfoOrderActionParams l_ifoOrderActionParams = getIfoOrderActionRow();
            TestDBUtility.insertWithDel(l_ifoOrderActionParams);
            
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = ifoOrderUnit();
            l_ifoOrderUnitParams.setSessionType("5");
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
//            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
               (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(1001);  
            
            method = WEB3FuturesExecuteReferenceServiceImpl.class.getDeclaredMethod("isChangeCancelEnable", new Class[]{WEB3GentradeSubAccount.class,IfoOrderUnit.class});
            method.setAccessible(true);
            assertFalse(((Boolean)method.invoke(l_impl, new Object[]{l_subAccount,l_orderUnit})).booleanValue());
        }
        catch (Exception e)
        {
            fail();
        }

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
            
            WEB3FuturesExecuteReferenceServiceImpl l_impl =
                new WEB3FuturesExecuteReferenceServiceImpl();
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L,10100101001007L);
            WEB3FuturesExecuteReferenceRequest l_request = new WEB3FuturesExecuteReferenceRequest();
            l_request.orderBizDate = WEB3DateUtility.getDate("20061211","yyyyMMdd");
            l_request.orderCondType = "0";
            WEB3FuturesExecuteReferenceResponse l_response = null;
            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(1006169090018L);
            WEB3FuturesExecuteGroup[] l_executeGroups =
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
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            l_ifoOrderUnitParams.setOrgOrderConditionType("0");
            l_ifoOrderUnitParams.setOrderConditionType(null);
            l_ifoOrderUnitParams.setMarketId(1002);
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            //IfoOrderRow
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            WEB3FuturesExecuteReferenceServiceImplFortest l_impl =
                new WEB3FuturesExecuteReferenceServiceImplFortest();
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L,10100101001007L);
            WEB3FuturesExecuteReferenceRequest l_request = new WEB3FuturesExecuteReferenceRequest();
            l_request.orderBizDate = WEB3DateUtility.getDate("20061211","yyyyMMdd");
            l_request.orderCondType = "0";//  発注条件区分≠NULL
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3FuturesExecuteReferenceResponse l_response = new WEB3FuturesExecuteReferenceResponse();
            //  先物OP銘柄≠NULL
            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(1006169090018L);
            WEB3FuturesExecuteGroup[] l_executeGroups =
            l_impl.createOrderExecutedInquiry(l_subAccount, l_request, l_response, l_ifoProduct);
            assertEquals(l_executeGroups[0].id, "1001");
            
        }
        catch (Exception e)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //create注文約定照会
    //先物OP銘柄==NULL
    //発注条件区分≠NULL
    //察@詢條件中有and nvl(org_order_condition_type,order_condition_type) = ?
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
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            l_ifoOrderUnitParams.setOrgOrderConditionType(null);
            l_ifoOrderUnitParams.setOrderConditionType("0");
            l_ifoOrderUnitParams.setMarketId(1002);
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            //IfoOrderRow
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            WEB3FuturesExecuteReferenceServiceImplFortest l_impl =
                new WEB3FuturesExecuteReferenceServiceImplFortest();
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L,10100101001007L);
            WEB3FuturesExecuteReferenceRequest l_request = new WEB3FuturesExecuteReferenceRequest();
            l_request.orderBizDate = WEB3DateUtility.getDate("20061211","yyyyMMdd");
            l_request.orderCondType = "0";//  発注条件区分≠NULL
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3FuturesExecuteReferenceResponse l_response = new WEB3FuturesExecuteReferenceResponse();
            //  先物OP銘柄==NULL
            WEB3IfoProductImpl l_ifoProduct = null;
            WEB3FuturesExecuteGroup[] l_executeGroups =
            l_impl.createOrderExecutedInquiry(l_subAccount, l_request, l_response, l_ifoProduct);
            assertEquals(l_executeGroups[0].id, "1001");
            
        }
        catch (Exception e)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //create注文約定照会
    //先物OP銘柄≠NULL
    //発注条件区分==NULL
    //察@詢條件中有and product_id = ?
    //察@出一條記路
    public void testCreateOrderExecutedInquiryCase4()
    {
        final String STR_METHOD_NAME = "testCreateOrderExecutedInquiryCase4";

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
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            l_ifoOrderUnitParams.setOrgOrderConditionType("0");
            l_ifoOrderUnitParams.setOrderConditionType("1");
            l_ifoOrderUnitParams.setMarketId(1002);
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            //IfoOrderRow
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            WEB3FuturesExecuteReferenceServiceImplFortest l_impl =
                new WEB3FuturesExecuteReferenceServiceImplFortest();
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L,10100101001007L);
            WEB3FuturesExecuteReferenceRequest l_request = new WEB3FuturesExecuteReferenceRequest();
            l_request.orderBizDate = WEB3DateUtility.getDate("20061211","yyyyMMdd");
            l_request.orderCondType = null;//  発注条件区分==NULL
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3FuturesExecuteReferenceResponse l_response = new WEB3FuturesExecuteReferenceResponse();
            //  先物OP銘柄≠NULL
            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(1006169090018L);
            WEB3FuturesExecuteGroup[] l_executeGroups =
            l_impl.createOrderExecutedInquiry(l_subAccount, l_request, l_response, l_ifoProduct);
            assertEquals(l_executeGroups[0].id, "1001");
            
        }
        catch (Exception e)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * 先物OP銘柄≠NULL
     * 発注条件区分==NULL
     * 察@詢條件中有and product_id = ?
     * 注文単位.注文カテゴリ == ”先物返済注文”の場合
     * 約定がある場合(注文単位.isUnexecutedがfalse)のみセット
     */
    public void testCreateOrderExecutedInquiryCase5()
    {
        final String STR_METHOD_NAME = "testCreateOrderExecutedInquiryCase5()";

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
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            l_ifoOrderUnitParams.setOrgOrderConditionType("0");
            l_ifoOrderUnitParams.setOrderConditionType("1");
            l_ifoOrderUnitParams.setMarketId(1002);
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_ifoOrderUnitParams.setClosingOrder("1");
            l_ifoOrderUnitParams.setExecutedQuantity(100);
            l_ifoOrderUnitParams.setExecutedAmount(200);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            //IfoOrderRow
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            WEB3FuturesExecuteReferenceServiceImplFortest l_impl =
                new WEB3FuturesExecuteReferenceServiceImplFortest();
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L,10100101001007L);
            WEB3FuturesExecuteReferenceRequest l_request = new WEB3FuturesExecuteReferenceRequest();
            l_request.orderBizDate = WEB3DateUtility.getDate("20061211","yyyyMMdd");
            l_request.orderCondType = null;//  発注条件区分==NULL
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3FuturesExecuteReferenceResponse l_response = new WEB3FuturesExecuteReferenceResponse();
            //  先物OP銘柄≠NULL
            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(1006169090018L);
            WEB3FuturesExecuteGroup[] l_executeGroups =
            l_impl.createOrderExecutedInquiry(l_subAccount, l_request, l_response, l_ifoProduct);

            assertEquals("3720", l_executeGroups[0].contractPrice);
            assertEquals("0", l_executeGroups[0].execPrice);
            
        }
        catch (Exception e)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    class WEB3FuturesExecuteReferenceServiceImplFortest extends WEB3FuturesExecuteReferenceServiceImpl
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
     * 注文履歴テーブル (ifo_order_action)
     */
    public static IfoOrderActionParams getIfoOrderActionRow()
    {
        IfoOrderActionParams l_ifoOrderActionParams = new IfoOrderActionParams();
        
        l_ifoOrderActionParams.setOrderActionId(10);
        l_ifoOrderActionParams.setAccountId(101001010010L);
        l_ifoOrderActionParams.setSubAccountId(10100101001007L);
        l_ifoOrderActionParams.setOrderId(1001);
        l_ifoOrderActionParams.setOrderUnitId(1001);
        l_ifoOrderActionParams.setOrderType(OrderTypeEnum.ASSET_IN);
        l_ifoOrderActionParams.setOrderEventType(OrderEventTypeEnum.UNDEFINED);
        l_ifoOrderActionParams.setExecutionConditionType(IfoOrderExecutionConditionType.NONE);
        
        l_ifoOrderActionParams.setExpirationDate(Calendar.getInstance().getTime());
        
        l_ifoOrderActionParams.setQuantity(1);
        l_ifoOrderActionParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
        l_ifoOrderActionParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRING);
        l_ifoOrderActionParams.setOrderActionSerialNo(45);
        l_ifoOrderActionParams.setProductId(1006169090018L);
        l_ifoOrderActionParams.setOrderActionSerialNo(1);
        l_ifoOrderActionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_ifoOrderActionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        return l_ifoOrderActionParams;
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
        l_ifoTradedProductUpdqParams.setTradedProductId(100106139070605L);
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

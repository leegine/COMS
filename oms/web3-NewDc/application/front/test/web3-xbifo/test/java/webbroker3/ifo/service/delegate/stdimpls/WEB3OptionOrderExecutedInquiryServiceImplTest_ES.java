head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.45.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionOrderExecutedInquiryServiceImplTest_ES.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP注文約定照会サービスImpl
Author Name      : Daiwa Institute of Research
Revesion History : meng-yanan
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.ifo.message.WEB3OptionsChangeCancelHistoryGroup;
import webbroker3.ifo.message.WEB3OptionsExecuteDetailsRequest;
import webbroker3.ifo.message.WEB3OptionsExecuteDetailsResponse;
import webbroker3.ifo.message.WEB3OptionsExecuteGroup;
import webbroker3.ifo.message.WEB3OptionsExecuteReferenceRequest;
import webbroker3.ifo.message.WEB3OptionsExecuteReferenceResponse;
import webbroker3.ifo.message.WEB3OptionsOrderHistoryRequest;
import webbroker3.ifo.message.WEB3OptionsOrderHistoryResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;

/**
 * OP注文約定照会サービスImpl
 * @@author meng-yanan
 *
 */
public class WEB3OptionOrderExecutedInquiryServiceImplTest_ES extends TestBaseForMock
{

    public WEB3OptionOrderExecutedInquiryServiceImplTest_ES(String name)
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

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * (get注文約定詳細)
     * 先物OPデータアダプタ.get注文期限区分() = 2：出来るまで注文 の場合
     * レスポンス.注文有効期限 = GtlUtils.getSystemTimestamp()
     * 
     * 夕場前繰越対象フラグ = false
     * 立会区分 = 1
     */
    public void test_getOrderExecutedDetail_0001()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3OptionsExecuteDetailsRequest l_request = new WEB3OptionsExecuteDetailsRequest();
        l_request.id = "1001";
        WEB3OptionOrderExecutedInquiryServiceImpl l_impl = new WEB3OptionOrderExecutedInquiryServiceImpl();
        
        try
        {
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = null;
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            
            ProductParams l_productParams = this.getProductRow();
            IfoProductParams l_ifoProductParams = this.getIfoProductRow();
            
            l_ifoOrderUnitParams = this.ifoOrderUnit();
            l_ifoOrderUnitParams.setOrderRootDiv("9");
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setWLimitExecCondType(IfoOrderExecutionConditionType.NONE);
            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
            l_ifoOrderUnitParams.setExpirationDate(GtlUtils.getSystemTimestamp());
            l_ifoOrderUnitParams.setSessionType("1");
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
            
            long l_lngOrderId = 1001;
            l_ifoOrderParams.setOrderId(l_lngOrderId);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            WEB3OptionsExecuteDetailsResponse l_response = l_impl.getOrderExecutedDetail(l_request);
            
            assertEquals("0","" + WEB3DateUtility.compare(GtlUtils.getSystemTimestamp(),l_response.expirationDate));
            assertFalse(l_response.eveningSessionCarryoverFlag);
            assertEquals("1",l_response.sessionType);
            
            
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
    }
    
    /**
     * (get注文約定詳細)
     * 先物OPデータアダプタ.get注文期限区分() = 2：出来るまで注文 の場合
     * レスポンス.注文有効期限 = GtlUtils.getSystemTimestamp()
     * 
     * 夕場前繰越対象フラグ = true
     * 立会区分 = 1
     */
    public void test_getOrderExecutedDetail_0002()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3OptionsExecuteDetailsRequest l_request = new WEB3OptionsExecuteDetailsRequest();
        l_request.id = "1001";
        WEB3OptionOrderExecutedInquiryServiceImpl l_impl = new WEB3OptionOrderExecutedInquiryServiceImpl();
        
        try
        {
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = null;
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            
            ProductParams l_productParams = this.getProductRow();
            IfoProductParams l_ifoProductParams = this.getIfoProductRow();
            
            l_ifoOrderUnitParams = this.ifoOrderUnit();
            l_ifoOrderUnitParams.setOrderRootDiv("9");
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setWLimitExecCondType(IfoOrderExecutionConditionType.NONE);
            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
            l_ifoOrderUnitParams.setExpirationDate(GtlUtils.getSystemTimestamp());
            l_ifoOrderUnitParams.setSessionType("1");
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            
            long l_lngOrderId = 1001;
            l_ifoOrderParams.setOrderId(l_lngOrderId);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            WEB3OptionsExecuteDetailsResponse l_response = l_impl.getOrderExecutedDetail(l_request);
            
            assertEquals("0","" + WEB3DateUtility.compare(GtlUtils.getSystemTimestamp(),l_response.expirationDate));
            assertTrue(l_response.eveningSessionCarryoverFlag);
            assertEquals("1",l_response.sessionType);
            
            
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
    }
    
    /**
     * (get注文履歴照会)
     * 先物OPデータアダプタ.get注文期限区分() = 2：出来るまで注文 の場合
     * レスポンス.注文有効期限 = GtlUtils.getSystemTimestamp()
     * 
     * 夕場前繰越対象フラグ = true
     * 立会区分 = 1
     */
    public void test_getOrderActionInquiry_0001()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3OptionsOrderHistoryRequest l_request = new WEB3OptionsOrderHistoryRequest();
        l_request.id = "1001";
        WEB3OptionOrderExecutedInquiryServiceImpl l_impl = new WEB3OptionOrderExecutedInquiryServiceImpl();
        
        try
        {
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = null;
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            
            ProductParams l_productParams = this.getProductRow();
            IfoProductParams l_ifoProductParams = this.getIfoProductRow();
            
            l_ifoOrderUnitParams = this.ifoOrderUnit();
            l_ifoOrderUnitParams.setOrderRootDiv("9");
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setWLimitExecCondType(IfoOrderExecutionConditionType.NONE);
            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
            l_ifoOrderUnitParams.setExpirationDate(GtlUtils.getSystemTimestamp());
            l_ifoOrderUnitParams.setSessionType("1");
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
//            l_ifoOrderUnitParams.setWLimitExecCondType();
            
            long l_lngOrderId = 1001;
            l_ifoOrderParams.setOrderId(l_lngOrderId);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            IfoOrderActionParams l_ifoOrderActionParams = getIfoOrderActionRow();
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderActionRow.TYPE);
            
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_ifoOrderActionParams);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            WEB3OptionsOrderHistoryResponse l_response = l_impl.getOrderActionInquiry(l_request);
            
            WEB3OptionsChangeCancelHistoryGroup[] l_opChangeCancelHistoryGroups = l_response.opChangeCancelHistoryGroups;
            
            
            assertEquals("0","" + WEB3DateUtility.compareToDay(Calendar.getInstance().getTime(),l_opChangeCancelHistoryGroups[0].expirationDate));
            assertTrue(l_opChangeCancelHistoryGroups[0].eveningSessionCarryoverFlag);
            assertEquals("1",l_opChangeCancelHistoryGroups[0].sessionType);
            
            
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
    }
    
    /**
     * (get注文履歴照会)
     * 先物OPデータアダプタ.get注文期限区分() = 2：出来るまで注文 の場合
     * レスポンス.注文有効期限 = GtlUtils.getSystemTimestamp()
     * 
     * 夕場前繰越対象フラグ = false
     * 立会区分 = 1
     */
    public void test_getOrderActionInquiry_0002()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3OptionsOrderHistoryRequest l_request = new WEB3OptionsOrderHistoryRequest();
        l_request.id = "1001";
        WEB3OptionOrderExecutedInquiryServiceImpl l_impl = new WEB3OptionOrderExecutedInquiryServiceImpl();
        
        try
        {
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = null;
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            
            ProductParams l_productParams = this.getProductRow();
            IfoProductParams l_ifoProductParams = this.getIfoProductRow();
            
            l_ifoOrderUnitParams = this.ifoOrderUnit();
            l_ifoOrderUnitParams.setOrderRootDiv("9");
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setWLimitExecCondType(IfoOrderExecutionConditionType.NONE);
            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
            l_ifoOrderUnitParams.setExpirationDate(GtlUtils.getSystemTimestamp());
            l_ifoOrderUnitParams.setSessionType("1");
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
//            l_ifoOrderUnitParams.setWLimitExecCondType();
            
            long l_lngOrderId = 1001;
            l_ifoOrderParams.setOrderId(l_lngOrderId);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            IfoOrderActionParams l_ifoOrderActionParams = getIfoOrderActionRow();
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderActionRow.TYPE);
            
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_ifoOrderActionParams);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            WEB3OptionsOrderHistoryResponse l_response = l_impl.getOrderActionInquiry(l_request);
            
            WEB3OptionsChangeCancelHistoryGroup[] l_opChangeCancelHistoryGroups = l_response.opChangeCancelHistoryGroups;
            
            
            assertEquals("0","" + WEB3DateUtility.compareToDay(Calendar.getInstance().getTime(),l_opChangeCancelHistoryGroups[0].expirationDate));
            assertFalse(l_opChangeCancelHistoryGroups[0].eveningSessionCarryoverFlag);
            assertEquals("1",l_opChangeCancelHistoryGroups[0].sessionType);
            
            
        }
        catch (WEB3BaseException e)
        {
            
            fail();
        }
    }
    
//    /**
//     * (create注文約定照会)
//     */
//    public void test_createOrderExecutedInquiry_0001()
//    {
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//        
//        WEB3OptionsOrderHistoryRequest l_request = new WEB3OptionsOrderHistoryRequest();
//        l_request.id = "1001";
//        WEB3OptionOrderExecutedInquiryServiceImpl_0001 l_impl = new WEB3OptionOrderExecutedInquiryServiceImpl_0001();
//        
//        try
//        {
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = null;
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            
//            l_mainAccountParams.setAccountId(101001010010L);
//            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
//            
//            ProductParams l_productParams = this.getProductRow();
//            IfoProductParams l_ifoProductParams = this.getIfoProductRow();
//            
//            l_ifoOrderUnitParams = this.ifoOrderUnit();
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
//            l_ifoOrderUnitParams.setWLimitExecCondType(IfoOrderExecutionConditionType.NONE);
//            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
//            l_ifoOrderUnitParams.setExpirationDate(GtlUtils.getSystemTimestamp());
//            l_ifoOrderUnitParams.setSessionType("1");
////            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
////            l_ifoOrderUnitParams.setWLimitExecCondType();
//            
//            long l_lngOrderId = 1001;
//            l_ifoOrderParams.setOrderId(l_lngOrderId);
//            l_ifoOrderParams.setAccountId(101001010010L);
//            l_ifoOrderParams.setSubAccountId(10100101001007L);
//            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//            
//            IfoOrderActionParams l_ifoOrderActionParams = getIfoOrderActionRow();
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            TestDBUtility.deleteAll(IfoProductRow.TYPE);
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            TestDBUtility.deleteAll(IfoProductRow.TYPE);
//            TestDBUtility.deleteAll(IfoOrderActionRow.TYPE);
//            
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            TestDBUtility.insertWithDel(l_ifoOrderActionParams);
//            
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "getAccountId",
//                    new Class[] {},
//                    new Long(101001010010L));
//            
//            IfoOrderUnitParams l_ifoOrderUnitParams1 = this.ifoOrderUnit();
//            l_ifoOrderUnitParams1.setWLimitExecCondType(IfoOrderExecutionConditionType.NONE);
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            TradingModule l_tradingModule =l_finApp.getTradingModule(ProductTypeEnum.IFO);
//            
//            WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
//                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
//
//            List l_lst = new ArrayList();
//            l_lst.add((IfoOrderUnit)l_optionOrderManagerImpl.toOrderUnit(l_ifoOrderUnitParams1));
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl",
//                    "getOrderUnits", new Class[] { SubAccount.class,
//                    ProductTypeEnum.class, String.class,String[].class,String.class },
//                    l_lst);
//            
//            
//            WEB3GentradeSubAccount l_subAccount = null;
//            WEB3OptionsExecuteReferenceRequest l_request1 = new WEB3OptionsExecuteReferenceRequest();
//            l_request1.pageIndex = "1";
//            l_request1.pageSize = "1";
//            WEB3OptionsExecuteReferenceResponse l_response = new WEB3OptionsExecuteReferenceResponse();
//            WEB3IfoProductImpl l_ifoProduct = null;
//            
//            WEB3OptionsExecuteGroup[] l_optionsExecuteGrou = l_impl.createOrderExecutedInquiry(l_subAccount,l_request1,l_response,l_ifoProduct);
//            
////            WEB3OptionsChangeCancelHistoryGroup[] l_opChangeCancelHistoryGroups = l_response.opChangeCancelHistoryGroups;
//            
//            
////            assertEquals("0","" + WEB3DateUtility.compare(GtlUtils.getSystemTimestamp(),l_opChangeCancelHistoryGroups[0].expirationDate));
////            assertFalse(l_opChangeCancelHistoryGroups[0].eveningSessionCarryoverFlag);
////            assertEquals("1",l_opChangeCancelHistoryGroups[0].sessionType);
//            
//            
//        }
//        catch (WEB3BaseException e)
//        {
//            
//            fail();
//        }
//    }
    
    public class WEB3OptionOrderExecutedInquiryServiceImpl_0001 extends WEB3OptionOrderExecutedInquiryServiceImpl
    {
        protected String createQueryString(WEB3IfoProductImpl l_ifoProduct,Date l_datBizDate)
        {
            return null;
        }
        
        protected String[] createQueryContainer(WEB3IfoProductImpl l_ifoProduct,Date l_datBizDate)
        {
            return null;
        }
        
        protected String createSortCond(WEB3FuturesOptionsSortKey[] l_futuresOptionsSortKey)
        {
            return null;
        }
        
        protected String getPRLevelTradeTypeDiv(OrderTypeEnum l_orderTypeEnum) throws WEB3BaseException
        {
            return null;
        }
        
        public Trader getTrader() throws WEB3SystemLayerException
        {
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
    
//    /**
//     * 投信注文履歴テーブル (mutual_fund_order_action)
//     */
//    public static MutualFundOrderActionParams getMutualFundOrderActionRow()
//    {
//        MutualFundOrderActionParams l_mutualFundOrderActionParams = new MutualFundOrderActionParams();
//        
//        l_mutualFundOrderActionParams.setOrderActionId(10);
//        l_mutualFundOrderActionParams.setAccountId(101001010010L);
//        l_mutualFundOrderActionParams.setSubAccountId(10100101001007L);
//        l_mutualFundOrderActionParams.setOrderId(1001);
//        l_mutualFundOrderActionParams.setOrderUnitId(1001);
//        l_mutualFundOrderActionParams.setOrderType(OrderTypeEnum.ASSET_IN);
//        l_mutualFundOrderActionParams.setOrderEventType(OrderEventTypeEnum.UNDEFINED);
//        
//        l_mutualFundOrderActionParams.setQuantity(1);
//        l_mutualFundOrderActionParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
//        l_mutualFundOrderActionParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRING);
//        l_mutualFundOrderActionParams.setOrderActionSerialNo(45);
//        l_mutualFundOrderActionParams.setProductId(1006169090018L);
//        l_mutualFundOrderActionParams.setQuantityType(QuantityTypeEnum.QUANTITY);
//        l_mutualFundOrderActionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//        l_mutualFundOrderActionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//        return l_mutualFundOrderActionParams;
//    }
    
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
}
@

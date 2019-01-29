head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.44.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesSettleContractOrderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : êÊï®ï‘çœíçï∂ÉTÅ[ÉrÉXImplTest(WEB3FuturesSettleContractOrderServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/26 í£ì´âF (íÜêu) êVãKçÏê¨
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendarDetails;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManagerPersistenceEventInterceptor;
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
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoSettleContractOrderSpec;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingClendarDetailsImpl;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.InstBranchProductParams;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoSettleContractUpdateInterceptor;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesCloseMarginCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginCompleteResponse;
import webbroker3.ifo.message.WEB3FuturesCloseMarginConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginConfirmResponse;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractOrderRequestAdapter;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.mock.util.WEB3MockObjectRuntimeException;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import weblogic.uddi.client.structures.datatypes.ErrInfo;

public class WEB3FuturesSettleContractOrderServiceImplTest extends TestBaseForMock
{
    /**
     * ÉçÉOÉÜÅ[ÉeÉBÉäÉeÉB
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesSettleContractOrderServiceImplTest.class);

    public WEB3FuturesSettleContractOrderServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setMarketCode("0");
        l_tradingTimeParams.setTradingTimeType("11");
        l_tradingTimeParams.setProductCode("0005");
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
        l_context.setMarketCode("0");
        l_context.setBranchCode("381");
        l_context.setProductCode("0");
        l_context.setBizDateType("1");
        l_context.setTradingTimeType("11");
        
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
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    public static String BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE = "web3.attributes.basetimestampfororderbizdate";
    public static String TRADING_CAL_CONTEXT_PATH = "web3.tradingcalendarcontext";
    public class WEB3FuturesCloseMarginConfirmRequestForMock 
        extends WEB3FuturesCloseMarginConfirmRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "WEB3FuturesCloseMarginConfirmRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    
    public class WEB3FuturesCloseMarginCompleteRequestForMock 
        extends WEB3FuturesCloseMarginCompleteRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "WEB3FuturesCloseMarginCompleteRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesSettleContractOrderServiceImpl.validateOrder(WEB3FuturesCloseMarginConfirmRequest)'
     */
    public void testValidateOrderCase1()
    {
        final String STR_METHOD_NAME = "testValidateOrderCase1()";
        log.entering(STR_METHOD_NAME);
        IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
        l_ifoContractParams.setSessionType("1");
        l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20060702","yyyyMMdd"));////
        l_ifoContractParams.setProductId(1006160060005L);
        l_ifoContractParams.setMarketId(3303L);
        l_ifoContractParams.setContractType(ContractTypeEnum.UNDEFINED);
        l_ifoContractParams.setContractId(1005);
        l_ifoContractParams.setAccountId(333812512203L);
        l_ifoContractParams.setSubAccountId(33381251220301L);
        l_ifoContractParams.setCloseDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
        l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
        
        InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
        l_instBranchProductParams.setCommissionProductCode("50");
        l_instBranchProductParams.setBranchId(33381);
        l_instBranchProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
        l_instBranchProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
        
        
        try
        {
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_format.format(l_datBizDate));
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(1006160060005L);
            l_tradedProductParams.setMarketId(3303L);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060005L);
            l_ifoProductParams.setTargetMarketId(3303L);
            l_ifoProductParams.setProductCode("0");
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
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
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,19);
            Date l_datBizDate1 = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date date = ca.getTime();
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date);
 
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
//            SettleContractEntry [] l_settleContractOrderEntries = new SettleContractEntry[1];
//            l_settleContractOrderEntries[0] = new  SettleContractEntry(1005,500);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
//                    "createSettleContractEntry", 
//                    new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
//                    l_settleContractOrderEntries);
            
//            NewOrderValidationResult l_validationResult =
//                new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
//                    "validateFuturesSettleContractOrder", 
//                    new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
//                    l_validationResult);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getExpirationDate", 
                    new Class[]{Date.class, String.class, String.class, String.class },
                    l_datBizDate1);
            
//            WEB3IfoEstimateDeliveryAmountCalcResult l_result =
//                new WEB3IfoEstimateDeliveryAmountCalcResult();
//            l_result.setCalcUnitPrice(100.0);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
//                    "calcEstimateSettlementIncome", 
//                    new Class[]{ WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
//                            WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class,
//                            SideEnum.class, boolean.class },
//                    l_result);
            MOCK_MANAGER.setIsMockUsed(true);
            NewOrderValidationResult l_validationResult =
            new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "validateFuturesSettleContractOrder", 
                new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
                l_validationResult);
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3FuturesCloseMarginConfirmRequestForMock l_request =
                new WEB3FuturesCloseMarginConfirmRequestForMock();
            l_request.limitPrice = "100";
            l_request.wlimitOrderCondPrice = "100";
            l_request.expirationDateType = "1";
            
            l_request.expirationDate = l_datBizDate1;
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = "1005";
            l_request.closeMarginContractUnits[0] = l_closeMarginContractUnit;
            
            WEB3FuturesCloseMarginConfirmResponse l_response =
                l_impl.validateOrder(l_request);
            
            WEB3FuturesOptionsContractUnit[] l_contractUnits =
                new WEB3FuturesOptionsContractUnit[l_response.contractUnits.length];
            l_contractUnits = l_response.contractUnits;
            WEB3FuturesOptionsContractUnit l_unit = l_contractUnits[0];
            assertEquals(l_unit.sessionType, "1");
            
            assertEquals(l_response.expirationDate, l_datBizDate1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesSettleContractOrderServiceImpl.submitOrder(WEB3FuturesCloseMarginCompleteRequest)'
     */
    public void testSubmitOrderCase1()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase1()";
        log.entering(STR_METHOD_NAME);
        IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
        l_ifoContractParams.setSessionType("1");
        l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20060702","yyyyMMdd"));////
        l_ifoContractParams.setProductId(1006160060005L);
        l_ifoContractParams.setMarketId(3303L);
        l_ifoContractParams.setContractType(ContractTypeEnum.UNDEFINED);
        l_ifoContractParams.setContractId(1005);
        l_ifoContractParams.setAccountId(333812512203L);
        l_ifoContractParams.setSubAccountId(33381251220301L);
        l_ifoContractParams.setCloseDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
        l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
        
        InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
        l_instBranchProductParams.setCommissionProductCode("50");
        l_instBranchProductParams.setBranchId(33381);
        l_instBranchProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
        l_instBranchProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
        
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setMarketCode("0");
        l_tradingTimeParams.setTradingTimeType("03");
        l_tradingTimeParams.setProductCode("0005");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setSessionType("1");
        try
        {
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datBizDate);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(1006160060005L);
            l_tradedProductParams.setMarketId(3303L);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060005L);
            l_ifoProductParams.setTargetMarketId(3303L);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
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
            
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            //IfoOrderUnitRow
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1001L);
            l_ifoOrderUnitParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,19);
            
            Date date = ca.getTime();
            this.setExpectedDate(date, "1");
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
            
            OrderSubmissionResult l_orderSubmissionResult = 
                new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "submitSettleContractOrder", new Class[]
                    { SubAccount.class, IfoSettleContractOrderSpec.class, long.class, String.class, boolean.class },
            l_orderSubmissionResult);
            Date l_datBizDate1 = GtlUtils.getTradingSystem().getSystemTimestamp();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getExpirationDate", 
                    new Class[]{Date.class, String.class, String.class, String.class },
                    l_datBizDate1);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
//                    "setThreadLocalPersistenceEventInterceptor", 
//                    new Class[]{ IfoOrderManagerPersistenceEventInterceptor.class },
//                    new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);
            l_clendarContext.setTradingTimeType("03");
            l_clendarContext.setMarketCode("0");
            MOCK_MANAGER.setIsMockUsed(true);
            NewOrderValidationResult l_validationResult =
            new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "validateFuturesSettleContractOrder", 
                new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
                l_validationResult);
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3FuturesCloseMarginCompleteRequestForMock l_request =
                new WEB3FuturesCloseMarginCompleteRequestForMock();
            l_request.limitPrice = "100";
            l_request.wlimitOrderCondPrice = "100";
            l_request.expirationDateType = "1";
            l_request.expirationDate = l_datBizDate1;
            l_request.orderId = "1111";
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_unit1.id = "1005";
            l_request.closeMarginContractUnits[0] = l_unit1;
            WEB3FuturesCloseMarginCompleteResponse l_response =
                l_impl.submitOrder(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //createï‘çœåöã ÉGÉìÉgÉäcreateSettleContractEntry
    public void testCreateSettleContractEntryCase1()
    {
        final String STR_METHOD_NAME = "testCreateSettleContractEntryCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
          SettleContractEntry [] l_settleContractOrderEntries = new SettleContractEntry[1];
          l_settleContractOrderEntries[0] = new  SettleContractEntry(1005,500);
          
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                  "createSettleContractEntry", 
                  new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                  l_settleContractOrderEntries);
            WEB3FuturesSettleContractOrderServiceImpl l_impl = new WEB3FuturesSettleContractOrderServiceImpl();
            WEB3FuturesCloseMarginCompleteRequest l_request  = new WEB3FuturesCloseMarginCompleteRequest();
            l_request.futOrderQuantity = "100";
            WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter =
                WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
            
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnits[0] = l_unit1;
            SettleContractEntry[] l_entries =
                l_impl.createSettleContractEntry(l_requestAdapter, l_closeMarginContractUnits);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //validateêÊï®ï‘çœíçï∂validateFuturesSettleContractOrder
    public void testValidateFuturesSettleContractOrderCase1()
    {
        final String STR_METHOD_NAME = "testValidateFuturesSettleContractOrderCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
          NewOrderValidationResult l_validationResult =
          new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
      TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
              "validateFuturesSettleContractOrder", 
              new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
              l_validationResult);
            WEB3FuturesSettleContractOrderServiceImpl l_impl = new WEB3FuturesSettleContractOrderServiceImpl();
            WEB3FuturesCloseMarginCompleteRequest l_request  = new WEB3FuturesCloseMarginCompleteRequest();
            
            SettleContractEntry [] l_settleContractOrderEntries = new SettleContractEntry[1];
            l_settleContractOrderEntries[0] = new  SettleContractEntry(1005,500);
            
            l_request.futOrderQuantity = "100";
            WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_unit1.id = "1005";
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            l_request.closeMarginContractUnits[0] = l_unit1;
            WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter =
                WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
            SubAccount l_subAccout = null;
            WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec = null;
            NewOrderValidationResult l_validateResult = 
                l_impl.validateFuturesSettleContractOrder(l_subAccout, l_settleContractOrderSpec, l_requestAdapter);
            assertTrue(l_validateResult.getProcessingResult().isSuccessfulResult());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateFuturesSettleContractOrderCase2()
    {
        final String STR_METHOD_NAME = "testValidateFuturesSettleContractOrderCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FuturesSettleContractOrderServiceImpl l_impl = new WEB3FuturesSettleContractOrderServiceImpl();
            WEB3FuturesCloseMarginCompleteRequest l_request  = new WEB3FuturesCloseMarginCompleteRequest();
            l_request.futOrderQuantity = "100";
            WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_unit1.id = "1005";
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            l_request.closeMarginContractUnits[0] = l_unit1;
            WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter =
                WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
            SubAccount l_subAccout = null;
            WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec = null;
            NewOrderValidationResult l_validateResult = 
                l_impl.validateFuturesSettleContractOrder(l_subAccout, l_settleContractOrderSpec, l_requestAdapter);
            assertTrue(l_validateResult.getProcessingResult().isFailedResult());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //getäTéZåàçœëπâv getEstimateSettlementIncome
    public void testGetEstimateSettlementIncomeCase1()
    {
        final String STR_METHOD_NAME = "testGetEstimateSettlementIncomeCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoContractParams.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setSessionType("1");
            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20060702","yyyyMMdd"));////
            l_ifoContractParams.setProductId(1006160060005L);
            l_ifoContractParams.setMarketId(3303L);
            l_ifoContractParams.setContractType(ContractTypeEnum.UNDEFINED);
            l_ifoContractParams.setContractId(1005);
            l_ifoContractParams.setAccountId(333812512203L);
            l_ifoContractParams.setSubAccountId(33381251220301L);
            l_ifoContractParams.setCloseDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoContractParams.setContractType(ContractTypeEnum.LONG);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            MOCK_MANAGER.setIsMockUsed(true);
          WEB3IfoEstimateDeliveryAmountCalcResult l_result =
          new WEB3IfoEstimateDeliveryAmountCalcResult();
          l_result.setCalcUnitPrice(100.0);
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                  "calcEstimateSettlementIncome", 
                  new Class[]{ WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                          WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class,
                          SideEnum.class, boolean.class },
                  l_result);
            WEB3GentradeCommission l_commision = null;
            SubAccount l_subAccout = null;
            WEB3IfoTradedProductImpl l_ifoTradedProduct = null;
            SettleContractEntry [] l_settleContractOrderEntries = new SettleContractEntry[1];
            l_settleContractOrderEntries[0] = new  SettleContractEntry(1005,500);
            WEB3FuturesCloseMarginCompleteRequest l_request  = new WEB3FuturesCloseMarginCompleteRequest();
            l_request.futOrderQuantity = "100";
            WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_unit1.id = "1005";
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            l_request.closeMarginContractUnits[0] = l_unit1;
            WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter =
                WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
            WEB3FuturesSettleContractOrderServiceImpl l_impl = new WEB3FuturesSettleContractOrderServiceImpl();
            WEB3IfoEstimateDeliveryAmountCalcResult l_result1 = l_impl.getEstimateSettlementIncome(
                    l_commision,
                    10.0,
                    l_subAccout, 
                    l_ifoTradedProduct,
                    l_settleContractOrderEntries,
                    10.0,
                    l_requestAdapter);
            assertEquals(l_result1.getCalcUnitPrice() + "", "100.0");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetEstimateSettlementIncomeCase2()
    {
        final String STR_METHOD_NAME = "testGetEstimateSettlementIncomeCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoContractParams.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setSessionType("1");
            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20060702","yyyyMMdd"));////
            l_ifoContractParams.setProductId(1006160060005L);
            l_ifoContractParams.setMarketId(3303L);
            l_ifoContractParams.setContractType(ContractTypeEnum.UNDEFINED);
            l_ifoContractParams.setContractId(1005);
            l_ifoContractParams.setAccountId(333812512203L);
            l_ifoContractParams.setSubAccountId(33381251220301L);
            l_ifoContractParams.setCloseDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            MOCK_MANAGER.setIsMockUsed(true);
          WEB3IfoEstimateDeliveryAmountCalcResult l_result =
          new WEB3IfoEstimateDeliveryAmountCalcResult();
          l_result.setCalcUnitPrice(100.0);
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                  "calcEstimateSettlementIncome", 
                  new Class[]{ WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                          WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class,
                          SideEnum.class, boolean.class },
                  l_result);
            WEB3GentradeCommission l_commision = null;
            SubAccount l_subAccout = null;
            WEB3IfoTradedProductImpl l_ifoTradedProduct = null;
            SettleContractEntry [] l_settleContractOrderEntries = new SettleContractEntry[1];
            l_settleContractOrderEntries[0] = new  SettleContractEntry(1005,500);
            WEB3FuturesCloseMarginCompleteRequest l_request  = new WEB3FuturesCloseMarginCompleteRequest();
            l_request.futOrderQuantity = "100";
            WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_unit1.id = "1005";
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            l_request.closeMarginContractUnits[0] = l_unit1;
            WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter =
                WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
            WEB3FuturesSettleContractOrderServiceImpl l_impl = new WEB3FuturesSettleContractOrderServiceImpl();
            WEB3IfoEstimateDeliveryAmountCalcResult l_result1 = l_impl.getEstimateSettlementIncome(
                    l_commision,
                    10.0,
                    l_subAccout, 
                    l_ifoTradedProduct,
                    l_settleContractOrderEntries,
                    10.0,
                    l_requestAdapter);
            assertEquals(l_result1.getCalcUnitPrice() + "", "100.0");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //createåöã ñæç◊createContractUnit
    public void testcreateContractUnitCase1()
    {
        final String STR_METHOD_NAME = "testcreateContractUnitCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoContractParams.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setSessionType("1");
            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20060702","yyyyMMdd"));////
            l_ifoContractParams.setProductId(1006160060005L);
            l_ifoContractParams.setMarketId(3303L);
            l_ifoContractParams.setContractType(ContractTypeEnum.UNDEFINED);
            l_ifoContractParams.setContractId(1005);
            l_ifoContractParams.setAccountId(333812512203L);
            l_ifoContractParams.setSubAccountId(33381251220301L);
            l_ifoContractParams.setCloseDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            MOCK_MANAGER.setIsMockUsed(true);
          WEB3IfoEstimateDeliveryAmountCalcResult l_result =
          new WEB3IfoEstimateDeliveryAmountCalcResult();
          l_result.setCalcUnitPrice(100.0);
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                  "calcEstimateSettlementIncome", 
                  new Class[]{ WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                          WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class,
                          SideEnum.class, boolean.class },
                  l_result);
            WEB3IfoTradedProductImpl l_ifoTradedProduct = new WEB3IfoTradedProductImpl(1006160060005L);
            SettleContractEntry [] l_settleContractOrderEntries = new SettleContractEntry[1];
            l_settleContractOrderEntries[0] = new  SettleContractEntry(1005,500);
            WEB3FuturesCloseMarginCompleteRequest l_request  = new WEB3FuturesCloseMarginCompleteRequest();
            l_request.futOrderQuantity = "100";
            WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_unit1.id = "1005";
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            l_request.closeMarginContractUnits[0] = l_unit1;
            WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter =
                WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
            
            WEB3FuturesSettleContractOrderServiceImpl l_impl = new WEB3FuturesSettleContractOrderServiceImpl();

            WEB3FuturesOptionsContractUnit[] l_contractUnits = l_impl.createContractUnit(
                    l_settleContractOrderEntries,
                    l_requestAdapter,
                    l_ifoTradedProduct);
            //ëπâv = åöã .getï]âøëπâv(ï‘çœíPâø, êîó )
            assertEquals(l_contractUnits[0].income, "18560000000");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testcreateContractUnitCase2()
    {
        final String STR_METHOD_NAME = "testcreateContractUnitCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoContractParams.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setSessionType("1");
            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20060702","yyyyMMdd"));////
            l_ifoContractParams.setProductId(1006160060005L);
            l_ifoContractParams.setMarketId(3303L);
            l_ifoContractParams.setContractType(ContractTypeEnum.UNDEFINED);
            l_ifoContractParams.setContractId(1005);
            l_ifoContractParams.setAccountId(333812512203L);
            l_ifoContractParams.setSubAccountId(33381251220301L);
            l_ifoContractParams.setCloseDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            l_ifoTradedProductParams.setLastClosingPrice(null);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
            l_ifoTradedProductUpdqParams.setLastClosingPrice(null);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_format.format(l_datBizDate));
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            MOCK_MANAGER.setIsMockUsed(true);
          WEB3IfoEstimateDeliveryAmountCalcResult l_result =
          new WEB3IfoEstimateDeliveryAmountCalcResult();
          l_result.setCalcUnitPrice(100.0);
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                  "calcEstimateSettlementIncome", 
                  new Class[]{ WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                          WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class,
                          SideEnum.class, boolean.class },
                  l_result);
            WEB3IfoTradedProductImpl l_ifoTradedProduct = new WEB3IfoTradedProductImpl(1006160060005L);
            SettleContractEntry [] l_settleContractOrderEntries = new SettleContractEntry[1];
            l_settleContractOrderEntries[0] = new  SettleContractEntry(1005,500);
            WEB3FuturesCloseMarginCompleteRequest l_request  = new WEB3FuturesCloseMarginCompleteRequest();
            l_request.futOrderQuantity = "100";
            WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_unit1.id = "1005";
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            l_request.closeMarginContractUnits[0] = l_unit1;
            WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter =
                WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
            
            WEB3FuturesSettleContractOrderServiceImpl l_impl = new WEB3FuturesSettleContractOrderServiceImpl();

            WEB3FuturesOptionsContractUnit[] l_contractUnits = l_impl.createContractUnit(
                    l_settleContractOrderEntries,
                    l_requestAdapter,
                    l_ifoTradedProduct);
            //ëπâv = åöã .getï]âøëπâv(ï‘çœíPâø, êîó )  ï‘çœíPâø=0
            assertEquals(l_contractUnits[0].income, "18600000000");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testcreateContractUnitCase3()
    {
        final String STR_METHOD_NAME = "testcreateContractUnitCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoContractParams.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setSessionType("1");
            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20060702","yyyyMMdd"));////
            l_ifoContractParams.setProductId(1006160060005L);
            l_ifoContractParams.setMarketId(3303L);
            l_ifoContractParams.setContractType(ContractTypeEnum.UNDEFINED);
            l_ifoContractParams.setContractId(1005);
            l_ifoContractParams.setAccountId(333812512203L);
            l_ifoContractParams.setSubAccountId(33381251220301L);
            l_ifoContractParams.setCloseDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            l_ifoTradedProductParams.setLastClosingPrice(null);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
            l_ifoTradedProductUpdqParams.setLastClosingPrice(null);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_format.format(l_datBizDate));
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            MOCK_MANAGER.setIsMockUsed(true);
          WEB3IfoEstimateDeliveryAmountCalcResult l_result =
          new WEB3IfoEstimateDeliveryAmountCalcResult();
          l_result.setCalcUnitPrice(100.0);
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                  "calcEstimateSettlementIncome", 
                  new Class[]{ WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                          WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class,
                          SideEnum.class, boolean.class },
                  l_result);
            WEB3IfoTradedProductImpl l_ifoTradedProduct = new WEB3IfoTradedProductImpl(1006160060005L);
            SettleContractEntry [] l_settleContractOrderEntries = new SettleContractEntry[0];
            WEB3FuturesCloseMarginCompleteRequest l_request  = new WEB3FuturesCloseMarginCompleteRequest();
            l_request.futOrderQuantity = "100";
            WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_unit1.id = "1005";
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            l_request.closeMarginContractUnits[0] = l_unit1;
            WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter =
                WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
            
            WEB3FuturesSettleContractOrderServiceImpl l_impl = new WEB3FuturesSettleContractOrderServiceImpl();

            WEB3FuturesOptionsContractUnit[] l_contractUnits = l_impl.createContractUnit(
                    l_settleContractOrderEntries,
                    l_requestAdapter,
                    l_ifoTradedProduct);
            //ëπâv = åöã .getï]âøëπâv(ï‘çœíPâø, êîó )
            assertEquals(l_contractUnits.length, 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testcreateContractUnitCase4()
    {
        final String STR_METHOD_NAME = "testcreateContractUnitCase4()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoContractParams.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setSessionType("1");
            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20060702","yyyyMMdd"));////
            l_ifoContractParams.setProductId(1006160060005L);
            l_ifoContractParams.setMarketId(3303L);
            l_ifoContractParams.setContractType(ContractTypeEnum.UNDEFINED);
            l_ifoContractParams.setContractId(1005);
            l_ifoContractParams.setAccountId(333812512203L);
            l_ifoContractParams.setSubAccountId(33381251220301L);
            l_ifoContractParams.setCloseDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            IfoContractParams l_ifoContractParams1 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams1.setSessionType("1");
            l_ifoContractParams1.setDeliveryDate(WEB3DateUtility.getDate("20060702","yyyyMMdd"));////
            l_ifoContractParams1.setProductId(1006160060005L);
            l_ifoContractParams1.setMarketId(3303L);
            l_ifoContractParams1.setContractType(ContractTypeEnum.UNDEFINED);
            l_ifoContractParams1.setContractId(1006);
            l_ifoContractParams1.setAccountId(333812512203L);
            l_ifoContractParams1.setSubAccountId(33381251220302L);
            l_ifoContractParams1.setCloseDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoContractParams1.setContractType(ContractTypeEnum.SHORT);
            TestDBUtility.insertWithDel(l_ifoContractParams1);
            IfoContractParams l_ifoContractParams2 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams2.setSessionType("1");
            l_ifoContractParams2.setDeliveryDate(WEB3DateUtility.getDate("20060702","yyyyMMdd"));////
            l_ifoContractParams2.setProductId(1006160060005L);
            l_ifoContractParams2.setMarketId(3303L);
            l_ifoContractParams2.setContractType(ContractTypeEnum.UNDEFINED);
            l_ifoContractParams2.setContractId(1007);
            l_ifoContractParams2.setAccountId(333812512203L);
            l_ifoContractParams2.setSubAccountId(33381251220303L);
            l_ifoContractParams2.setCloseDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoContractParams2.setContractType(ContractTypeEnum.SHORT);
            TestDBUtility.insertWithDel(l_ifoContractParams2);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            l_ifoTradedProductParams.setLastClosingPrice(null);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
            l_ifoTradedProductUpdqParams.setLastClosingPrice(null);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_format.format(l_datBizDate));
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            MOCK_MANAGER.setIsMockUsed(true);
          WEB3IfoEstimateDeliveryAmountCalcResult l_result =
          new WEB3IfoEstimateDeliveryAmountCalcResult();
          l_result.setCalcUnitPrice(100.0);
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                  "calcEstimateSettlementIncome", 
                  new Class[]{ WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                          WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class,
                          SideEnum.class, boolean.class },
                  l_result);
            WEB3IfoTradedProductImpl l_ifoTradedProduct = new WEB3IfoTradedProductImpl(1006160060005L);
            SettleContractEntry [] l_settleContractOrderEntries = new SettleContractEntry[3];
            l_settleContractOrderEntries[0] = new  SettleContractEntry(1005,500);
            l_settleContractOrderEntries[1] = new  SettleContractEntry(1006,500);
            l_settleContractOrderEntries[2] = new  SettleContractEntry(1007,500);
            WEB3FuturesCloseMarginCompleteRequest l_request  = new WEB3FuturesCloseMarginCompleteRequest();
            l_request.futOrderQuantity = "100";
            WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_unit1.id = "1005";
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            l_request.closeMarginContractUnits[0] = l_unit1;
            WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter =
                WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
            
            WEB3FuturesSettleContractOrderServiceImpl l_impl = new WEB3FuturesSettleContractOrderServiceImpl();

            WEB3FuturesOptionsContractUnit[] l_contractUnits = l_impl.createContractUnit(
                    l_settleContractOrderEntries,
                    l_requestAdapter,
                    l_ifoTradedProduct);
            //ëπâv = åöã .getï]âøëπâv(ï‘çœíPâø, êîó ) 
            assertEquals(l_contractUnits.length, 3);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //submitï‘çœíçï∂
    public void testsubmitSettleContractOrderCase1()
    {
        final String STR_METHOD_NAME = "testsubmitSettleContractOrderCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            MOCK_MANAGER.setIsMockUsed(true);
          NewOrderValidationResult l_validationResult =
              new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                  "validateFuturesSettleContractOrder", 
                  new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
                  l_validationResult);
          
          OrderSubmissionResult l_orderSubmissionResult = 
              new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT);
          
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                  "submitSettleContractOrder", new Class[]
                  { SubAccount.class, IfoSettleContractOrderSpec.class, long.class, String.class, boolean.class },
          l_orderSubmissionResult);
          Date l_datBizDate1 = GtlUtils.getTradingSystem().getSystemTimestamp();
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                  "getExpirationDate", 
                  new Class[]{Date.class, String.class, String.class, String.class },
                  l_datBizDate1);
            WEB3FuturesSettleContractOrderServiceImpl l_impl = new WEB3FuturesSettleContractOrderServiceImpl();
            WEB3FuturesCloseMarginCompleteRequest l_request  = new WEB3FuturesCloseMarginCompleteRequest();
            
            SettleContractEntry[] l_settleContractOrderEntries = new SettleContractEntry[1];
            l_settleContractOrderEntries[0] = new  SettleContractEntry(1005,500);
            
            l_request.futOrderQuantity = "100";
            WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_unit1.id = "1005";
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            l_request.closeMarginContractUnits[0] = l_unit1;
            l_request.stopOrderCondPrice = "100";
            l_request.wlimitOrderCondPrice = "100";
            WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter =
                WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
            WEB3GentradeSubAccount l_subAccout = null;
            WEB3GentradeCommission l_commision = null;
            WEB3IfoEstimateDeliveryAmountCalcResult l_amountCalcResult = null;
            //Trader trader, SettleContractEntry entries[], double price, 
            //IfoOrderExecutionConditionType execType, Date orderExpDate, TaxTypeEnum taxType
            Trader trader = null;
            IfoOrderExecutionConditionType execType = null;
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec =
                WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                    "0D",
                    trader,
                    10.0,
                    execType,
                    l_datBizDate,
                    l_settleContractOrderEntries,
                    "2",
                    10.0,
                    10.0,
                    execType,
                    "1",
                    new Long(1001L),
                    true);
            
            l_impl.submitSettleContractOrder(
                    l_requestAdapter,
                    l_subAccout, 
                    l_settleContractOrderSpec, 
                    123456L,
                    l_commision,
                    l_amountCalcResult);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testsubmitSettleContractOrderCase2()
    {
        final String STR_METHOD_NAME = "testsubmitSettleContractOrderCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            MOCK_MANAGER.setIsMockUsed(true);
          NewOrderValidationResult l_validationResult =
              new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                  "validateFuturesSettleContractOrder", 
                  new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
                  l_validationResult);
          
          OrderSubmissionResult l_orderSubmissionResult = 
              new OrderSubmissionResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003));
          
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                  "submitSettleContractOrder", new Class[]
                  { SubAccount.class, IfoSettleContractOrderSpec.class, long.class, String.class, boolean.class },
          l_orderSubmissionResult);
          Date l_datBizDate1 = GtlUtils.getTradingSystem().getSystemTimestamp();
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                  "getExpirationDate", 
                  new Class[]{Date.class, String.class, String.class, String.class },
                  l_datBizDate1);
            WEB3FuturesSettleContractOrderServiceImpl l_impl = new WEB3FuturesSettleContractOrderServiceImpl();
            WEB3FuturesCloseMarginCompleteRequest l_request  = new WEB3FuturesCloseMarginCompleteRequest();
            
            SettleContractEntry[] l_settleContractOrderEntries = new SettleContractEntry[1];
            l_settleContractOrderEntries[0] = new  SettleContractEntry(1005,500);
            
            l_request.futOrderQuantity = "100";
            WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_unit1.id = "1005";
            l_request.stopOrderCondPrice = "100";
            l_request.wlimitOrderCondPrice = "100";
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            l_request.closeMarginContractUnits[0] = l_unit1;
            WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter =
                WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
            WEB3GentradeSubAccount l_subAccout = null;
            WEB3GentradeCommission l_commision = null;
            WEB3IfoEstimateDeliveryAmountCalcResult l_amountCalcResult = null;
            //Trader trader, SettleContractEntry entries[], double price, 
            //IfoOrderExecutionConditionType execType, Date orderExpDate, TaxTypeEnum taxType
            Trader trader = null;
            IfoOrderExecutionConditionType execType = null;
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec =
                WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                    "0D",
                    trader,
                    10.0,
                    execType,
                    l_datBizDate,
                    l_settleContractOrderEntries,
                    "1",
                    10.0,
                    10.0,
                    execType,
                    "1",
                    new Long(1001L),
                    true);
            
            l_impl.submitSettleContractOrder(
                    l_requestAdapter,
                    l_subAccout, 
                    l_settleContractOrderSpec, 
                    123456L,
                    l_commision,
                    l_amountCalcResult);
            
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80003);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    private void setExpectedDate(Date l_expectDate,String l_sessionType)
    {
        final String STR_METHOD_NAME = "setExpectedDate()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(l_expectDate.getTime()), "1");
            
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("03");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");        
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setSessionType(l_sessionType);
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE, new Timestamp(l_expectDate.getTime()));   
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    private class WEB3OptionClientRequestServiceForMock extends WEB3FuturesSettleContractOrderServiceImpl
    {
        public SubAccount getSubAccount() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "getSubAccount()";
            log.entering(STR_METHOD_NAME);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            
            try
            {
                TestDBUtility.deleteAll(MainAccountRow.TYPE);
                TestDBUtility.insertWithDel(l_mainAccountParams);
                
                TestDBUtility.deleteAll(SubAccountRow.TYPE);
                TestDBUtility.insertWithDel(l_subAccountParams);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                //éÊìæï‚èïå˚ç¿
                l_subAccount =
                    (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                        333812512203L,
                        33381251220301L);
            }
            catch (NotFoundException l_nfe)
            {
                log.error("ÉfÅ[É^ïsêÆçáÉGÉâÅ[ÅB", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe); 
            }
            
            log.exiting(STR_METHOD_NAME);
            return l_subAccount;
        }
        public Trader getTrader() throws WEB3SystemLayerException
        {
            return null;
        }
        
        public String getLoginChannel()
        {
            return WEB3ChannelDef.BRANCH;
        }
        
        protected NewOrderValidationResult validateFuturesSettleContractOrder(
                SubAccount l_subAccount,
                IfoSettleContractOrderSpec l_settleContractOrderSpec,
                WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter) throws WEB3BaseException
            {
                final String STR_METHOD_NAME = "validateFuturesSettleContractOrder(" +
                    "SubAccount, IfoSettleContractOrderSpec, WEB3FuturesSettleContractOrderRequestAdapter)";
                log.entering(STR_METHOD_NAME);
                
                return new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            }
        
        protected WEB3IfoEstimateDeliveryAmountCalcResult getEstimateSettlementIncome(
                WEB3GentradeCommission l_commision,
                double l_dblLimitPrice,
                SubAccount l_subAccount,
                WEB3IfoTradedProductImpl l_ifoTradedProduct,
                SettleContractEntry[] l_settleContractEntry,
                double l_dblQuantity,
                WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter) throws WEB3BaseException
            {
                WEB3IfoEstimateDeliveryAmountCalcResult l_result =
                    new WEB3IfoEstimateDeliveryAmountCalcResult();
                l_result.setCalcUnitPrice(100.0);
                
                return l_result;
            }
        
        protected SettleContractEntry[] createSettleContractEntry(
                WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter,
                WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits) throws WEB3BaseException
            {
                final String STR_METHOD_NAME = "createSettleContractEntry(" +
                    "WEB3FuturesSettleContractOrderRequestAdapter, WEB3FuturesOptionsCloseMarginContractUnit[])";
                log.entering(STR_METHOD_NAME);
                SettleContractEntry [] l_settleContractOrderEntries = new SettleContractEntry[1];
                l_settleContractOrderEntries[0] = new  SettleContractEntry(1005,500);
                return l_settleContractOrderEntries;
            }
    }
}
@

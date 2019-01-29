head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.18.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesOrderCarryOverUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物注文繰越１件サービス実装クラス
Author Name      : Daiwa Institute of Research
Revesion History : 2007/7/06 孟亜南
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TraderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoLockedContractDetailsParams;
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
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoOpenContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractSettleOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SessionTypeDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * 先物注文繰越１件サービス実装クラス
 * @@author 孟亜南
 *
 */
public class WEB3FuturesOrderCarryOverUnitServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3FuturesOrderCarryOverUnitServiceImplTest.class);
    
    public WEB3FuturesOrderCarryOverUnitServiceImplTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void test_createOpenContractNextOrder_0001()
    {           
        final String STR_METHOD_NAME = "test_createOpenContractNextOrder_0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.gentrade.WEB3GentradeAccountManager",
//                "getInstitution",
//                new Class[]
//                { long.class},
//                new Institution(0L));
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());

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
            
            NewOrderValidationResult l_validationResult =
                new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateFuturesOpenContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class },
                    l_validationResult);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_result = new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_result.setRestraintTurnover(6);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimatePrice",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class, WEB3IfoTradedProductImpl.class,
                            double.class, boolean.class },
                            l_result);
            
            WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();
            l_tradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_tradingPowerResult);
            
            OrderSubmissionResult l_result1 = new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "submitOpenContractOrder", new Class[]
                    { SubAccount.class, IfoOpenContractOrderSpec.class, long.class, String.class, boolean.class },
                    l_result1);
                    
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(TraderRow.TYPE);
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();
            ProductParams l_productParams = getProductRow();
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = ifoOrderUnit();
            MarketParams l_marketParams = getMarketRow();
            
            IfoOrderParams l_IfoOrderParams = TestDBUtility.getIfoOrderRow();
            l_IfoOrderParams.setOrderId(1001);
            TestDBUtility.insertWithDel(l_IfoOrderParams);
            
            l_ifoOrderUnitParams.setOrderRootDiv("9");
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setWLimitExecCondType(IfoOrderExecutionConditionType.NONE);
            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
            l_ifoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20100101", "yyyyMMdd"));
            l_ifoOrderUnitParams.setSessionType("1");
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
            
            TraderParams l_traderParams = getTraderRow(); 
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_traderParams);
            
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            l_mainAccount.setAccountId(101001010010L);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccount);
            
            TestDBUtility.beginTransaction();
            
            WEB3FuturesOrderCarryOverUnitServiceImpl l_impl = new WEB3FuturesOrderCarryOverUnitServiceImpl();
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3FuturesOrderManagerImpl l_orderManager =
                (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(1001);   
            
            l_impl.createOpenContractNextOrder(l_orderUnit, null);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void test_createOpenContractNextOrder_0002()
    {           
        final String STR_METHOD_NAME = "test_createOpenContractNextOrder_0002()";
        log.entering(STR_METHOD_NAME);
        
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
            l_tradingTimeParams.setSessionType(WEB3SessionTypeDef.EVENING_SESSION);

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
            
            NewOrderValidationResult l_validationResult =
                new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateFuturesOpenContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class },
                    l_validationResult);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_result = new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_result.setRestraintTurnover(6);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimatePrice",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class, WEB3IfoTradedProductImpl.class,
                            double.class, boolean.class },
                            l_result);
            
            WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();
            l_tradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_tradingPowerResult);
            
            OrderSubmissionResult l_result1 = new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "submitOpenContractOrder", new Class[]
                    { SubAccount.class, IfoOpenContractOrderSpec.class, long.class, String.class, boolean.class },
                    l_result1);
            
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(TraderRow.TYPE);
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();
            ProductParams l_productParams = getProductRow();
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = ifoOrderUnit();
            MarketParams l_marketParams = getMarketRow();
            
            l_ifoOrderUnitParams.setOrderRootDiv("9");
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setWLimitExecCondType(IfoOrderExecutionConditionType.NONE);
//            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
            l_ifoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20100101", "yyyyMMdd"));
//            l_ifoOrderUnitParams.setSessionType(WEB3SessionTypeDef.EVENING_SESSION);
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
            
            TraderParams l_traderParams = getTraderRow(); 
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_traderParams);
            
            
            WEB3FuturesOrderCarryOverUnitServiceImplForTest l_impl = new WEB3FuturesOrderCarryOverUnitServiceImplForTest();
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3FuturesOrderManagerImpl l_orderManager =
                (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(1001);   

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "isCarryoverOrder",
                new Class[]{IfoOrderUnit.class},
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "getOrderUnits",
                new Class[]
                {long.class},
                new IfoOrderUnit[]{new IfoContractSettleOrderUnitImpl(1001L)});

            l_impl.createOpenContractNextOrder(l_orderUnit, new ArrayList());
            assertTrue(true);
        }
        catch (Exception e)
        {
            log.error("" + e);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void test_createSettleContractNextOrder_0011()
    {      
        final String STR_METHOD_NAME = "test_createSettleContractNextOrder_0011()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoContractParams.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(123456);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoLockedContractDetailsParams.TYPE);
            IfoLockedContractDetailsParams l_ifoLockedContractDetailsParams = TestDBUtility.getIfoLockedContractDetailsRow();
            l_ifoLockedContractDetailsParams.setContractId(123456);
            l_ifoLockedContractDetailsParams.setLockedQuantity(-50000);
            TestDBUtility.insertWithDel(l_ifoLockedContractDetailsParams);

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
            
            NewOrderValidationResult l_validationResult =
                new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateFuturesSettleContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
                    l_validationResult);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_result = new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_result.setRestraintTurnover(6);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimateSettlementIncome",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                            WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class, SideEnum.class,
                            boolean.class },
                            l_result);
            
            WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();
            l_tradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_tradingPowerResult);
            
            OrderSubmissionResult l_result1 = new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "submitSettleContractOrder", new Class[]
                    { SubAccount.class, IfoSettleContractOrderSpec.class, long.class, String.class, boolean.class },l_result1);
            
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(TraderRow.TYPE);
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();
            ProductParams l_productParams = getProductRow();
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = ifoOrderUnit();
            MarketParams l_marketParams = getMarketRow();
            
            l_ifoOrderUnitParams.setOrderRootDiv("9");
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setWLimitExecCondType(IfoOrderExecutionConditionType.NONE);
            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
            l_ifoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20100101", "yyyyMMdd"));
            l_ifoOrderUnitParams.setSessionType("1");
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
            
            TraderParams l_traderParams = getTraderRow(); 
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
         
            TestDBUtility.deleteAll(IfoOrderParams.TYPE);
            IfoOrderParams l_IfoOrderParams = TestDBUtility.getIfoOrderRow();
            l_IfoOrderParams.setOrderId(1001);
            TestDBUtility.insertWithDel(l_IfoOrderParams);
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.beginTransaction();
            WEB3FuturesOrderCarryOverUnitServiceImpl l_impl = new WEB3FuturesOrderCarryOverUnitServiceImpl();
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3FuturesOrderManagerImpl l_orderManager =
                (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(1001);   
            
            l_impl.createSettleContractNextOrder(l_orderUnit, null);

        }
        catch (Exception e)
        {
            log.error("" + e);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void test_createSettleContractNextOrder_0012()
    {           
        final String STR_METHOD_NAME = "test_createSettleContractNextOrder_0012()";
        log.entering(STR_METHOD_NAME);
        try
        {

            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
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
            l_tradingTimeParams.setSessionType(WEB3SessionTypeDef.EVENING_SESSION);

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
            
//            NewOrderValidationResult l_validationResult =
//                new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateSettleContractOrder", new Class[]
//                    { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
//                    l_validationResult);
//            
//            WEB3IfoEstimateDeliveryAmountCalcResult l_result = new WEB3IfoEstimateDeliveryAmountCalcResult();
//            l_result.setRestraintTurnover(6);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
//                    "calcEstimateDeliveryAmount",
//                    new Class[]
//                    { WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
//                            double.class, SideEnum.class, boolean.class, boolean.class },
//                            l_result);
//            
//            WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();
//            l_tradingPowerResult.setResultFlg(true);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
//                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
//                    l_tradingPowerResult);
//            
            OrderSubmissionResult l_result1 = new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "submitSettleContractOrder", new Class[]
                    { SubAccount.class, IfoSettleContractOrderSpec.class, long.class, String.class, boolean.class },
                    l_result1);
            
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(TraderRow.TYPE);
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
            TradedProductParams l_TradedProductParams = getTradedProductRow();
            IfoTradedProductParams l_IfoTradedProductParams = getIfoTradedProductRow();
            ProductParams l_productParams = getProductRow();
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = ifoOrderUnit();
            MarketParams l_marketParams = getMarketRow();
            
            l_ifoOrderUnitParams.setOrderRootDiv("9");
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setWLimitExecCondType(IfoOrderExecutionConditionType.NONE);
//            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
            l_ifoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20100101", "yyyyMMdd"));
//            l_ifoOrderUnitParams.setSessionType("1");
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
            
            TraderParams l_traderParams = getTraderRow(); 
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_traderParams);
            
            
            WEB3FuturesOrderCarryOverUnitServiceImplForTest l_impl = new WEB3FuturesOrderCarryOverUnitServiceImplForTest();
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3FuturesOrderManagerImpl l_orderManager =
                (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(1001);   

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "isCarryoverOrder",
                new Class[]{IfoOrderUnit.class},
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "getOrderUnits",
                new Class[]
                {long.class},
                new IfoOrderUnit[]{new IfoContractSettleOrderUnitImpl(1001L)});

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateFuturesSettleContractOrder",
                new Class[]
                {WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class},
                new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcEstimateSettlementIncome",
                new Class[]
                {WEB3GentradeCommission.class,
                double.class,
                WEB3GentradeSubAccount.class,
                WEB3IfoTradedProductImpl.class,
                SettleContractEntry[].class,
                double.class,
                SideEnum.class,
                boolean.class},
                null);

            l_impl.createSettleContractNextOrder(l_orderUnit, new ArrayList());
            assertTrue(true);
        }
        catch (Exception e)
        {
            log.error("" + e);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
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
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setProductCode("160030005");
        l_ifoProductParams.setUnderlyingProductCode("0005");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setStrikePrice(0.0D);
        l_ifoProductParams.setMonthOfDelivery("200503");
        l_ifoProductParams.setExerciseDate(WEB3DateUtility.getDate("20070112","yyyyMMdd"));
        l_ifoProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        return l_ifoProductParams;
    }
    
    public IfoOrderUnitParams ifoOrderUnit()
    {
        IfoOrderUnitParams l_params = new IfoOrderUnitParams();
        l_params.setOrderUnitId(1001);
        l_params.setAccountId(101001010010L);
        l_params.setSubAccountId(10100101001007L);
        l_params.setBranchId(33381);
        l_params.setTraderId(0);
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
     * 扱者Rowを作成.<BR>
     */
    public static TraderParams getTraderRow(){
        TraderParams l_traderParams = new TraderParams();
        l_traderParams.setTraderId(3338111123L);
        l_traderParams.setInstitutionCode("0D");
        l_traderParams.setBranchId(33381L);
        l_traderParams.setBranchCode("381");
        l_traderParams.setTraderCode("11123");
        l_traderParams.setTraderType(TraderTypeEnum.UNDEFINED);
        l_traderParams.setLoginId(3338111123L);
        l_traderParams.setFamilyName("大和");
        l_traderParams.setGivenName("太郎");
        l_traderParams.setFamilyNameAlt1("ダイワ");
        l_traderParams.setGivenNameAlt1("タロウ");
        l_traderParams.setTradingPassword("11123");
        l_traderParams.setAccountOrderFlag("1");
        l_traderParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_traderParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        return l_traderParams;
    }
    
    public class WEB3FuturesOrderCarryOverUnitServiceImplForTest extends WEB3FuturesOrderCarryOverUnitServiceImpl
    {
        public void updateCarryOverOriginOrder(
                OrderUnit l_orderUnit,
                String l_strOrderErrorReasonCode)
                throws DataQueryException, DataNetworkException, IllegalStateException
       {
            throw new DataQueryException("11");
       }
    }

    public void testExpireCarryOverOriginOrderC001()
    {
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "expireOrder",
                new Class[]{ long.class},
                ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                "expireOrder",
                new Class[]{ long.class},
                Boolean.TRUE);

            IfoOrderUnitParams l_ifoOrderUnitParams = ifoOrderUnit();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            WEB3FuturesOrderCarryOverUnitServiceImplForTest l_impl = new WEB3FuturesOrderCarryOverUnitServiceImplForTest();
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3FuturesOrderManagerImpl l_orderManager =
                (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(1001);  
            l_impl.expireCarryOverOriginOrder(l_orderUnit);
        }
        catch (Exception l_ex)
        {
            fail();
        }
    }
}
@

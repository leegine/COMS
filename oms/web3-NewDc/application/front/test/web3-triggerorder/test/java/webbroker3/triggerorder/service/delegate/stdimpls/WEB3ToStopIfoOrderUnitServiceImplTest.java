head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.20.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToStopIfoOrderUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoLockedContractDetailsParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoLockedContractDetailsRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractOpenOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractSettleOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3SubmitMarketTriggerDef;
import webbroker3.common.define.WEB3SubmitOrderRouteDivDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.FeqCalendarParams;
import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractChangeSpec;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.mock.util.WEB3MockObjectRuntimeException;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
/**
 * 逆指値注文先物OP発注一件サービスImplのテスト<BR>
 * @@author 崔遠鵬(中訊)
 * @@version 1.0
 */
public class WEB3ToStopIfoOrderUnitServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3ToStopIfoOrderUnitServiceImplTest.class);

    WEB3ToStopIfoOrderUnitServiceImpl l_toStopIfoOrderUnitServiceImpl = 
        new WEB3ToStopIfoOrderUnitServiceImpl();
    WEB3ToStopIfoOrderUnitServiceImpl toStopIfoOrderUnitServiceImpl =
        new WEB3ToStopIfoOrderUnitServiceImplForTest();
    
    public WEB3ToStopIfoOrderUnitServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        
        try
        {
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_marketParams);
        }
        catch (Exception l_ex)
        {
            
            fail();
        }
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "isEveningSessionOrder", 
            new Class[] {IfoOrderUnit.class},
            Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "validateFuturesOpenContractOrder",
            new Class[]{WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class, IfoOrderUnit.class},
            new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoBizLogicProvider",
            "createCommission", 
            new Class[] {long.class},
            new WEB3GentradeCommission());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getSubmitOrderRouteDiv",
            new Class[] {String.class, String.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoProductManagerImpl",
            "getIfoTradedProduct",
            new Class[]{Institution.class, String.class, String.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "calcEstimatePrice", new Class[]
            {WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                WEB3IfoTradedProductImpl.class, double.class, boolean.class },
            new WEB3IfoEstimateDeliveryAmountCalcResult());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
            "validateTradingPower",
            new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "throwTpErrorInfo",
            new Class[]{ WEB3TPTradingPowerResult.class, WEB3GentradeSubAccount.class },
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "insertOpenContractHostOrder",
            new Class[] {long.class},
            null);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeFinObjectManager",
            "getMarket",
            new Class[]{long.class}, 
            new WEB3GentradeMarket(3303L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getIfoTradedProduct",
                new Class[]{Institution.class, String.class, String.class},
                null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcEstimateSettlementIncome",
                new Class[]{ WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                    WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class,
                    SideEnum.class, boolean.class },
                new WEB3IfoEstimateDeliveryAmountCalcResult());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "insertSettleContractHostOrder",
                new Class[] {long.class},
                null);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testUpdateOrderData_C0001()
    {
        final String STR_METHOD_NAME = ".testUpdateOrderData_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // テーブルへデータをインサート
            IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_ifoOrderUnitParams.getRowType());
            l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            IfoOrderParams l_ifoOrderParams = new IfoOrderParams();
            l_processor.doDeleteAllQuery(l_ifoOrderParams.getRowType());
            l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(10001000L);
            TestDBUtility.insertWithDel(l_ifoOrderParams);

            // 実際メソッドをコール
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3FuturesOrderManagerImpl l_orderMgr =
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            long l_lngOrderUnitId = 1001;
            IfoOrderUnit l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_lngOrderUnitId);

            l_toStopIfoOrderUnitServiceImpl.updateOrderData(l_orderUnit, 1.0d, 2.0d, "a");

            // 予想結果と実際結果の比較
            l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_lngOrderUnitId);
            IfoOrderUnitRow l_row = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            IfoOrderUnitParams l_params = new IfoOrderUnitParams(l_row);
            assertEquals("a", l_params.submit_order_route_div);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSendMQTrigger_C0001()
    {
        final String STR_METHOD_NAME = ".testSendMQTrigger_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode",
            new Class[]{IfoOrderUnit.class},
            new WEB3BaseException(new ErrorInfo(), "abcdef"));
        try
        {
            //スタティックメソッドの準備
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("10");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONDAY,01);
            l_calendar.set(Calendar.DAY_OF_YEAR,01);
            l_calendar.set(Calendar.HOUR_OF_DAY,0);
            l_calendar.set(Calendar.MINUTE,0);
            l_calendar.set(Calendar.SECOND,0);
            l_calendar.set(Calendar.MILLISECOND,0);
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(l_calendar.getTimeInMillis()));

            // テーブルへデータをインサート
            IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_ifoOrderUnitParams.getRowType());
            l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setMarketId(3303L);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_processor.doInsertQuery(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            IfoOrderParams l_ifoOrderParams = new IfoOrderParams();
            l_processor.doDeleteAllQuery(l_ifoOrderParams.getRowType());
            l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(10001000L);
            TestDBUtility.insertWithDel(l_ifoOrderParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            MarketParams l_maketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_maketParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(new Timestamp(l_calendar.getTimeInMillis()));
            l_calendarParams.setBizDateType("0");
            TestDBUtility.insertWithDel(l_calendarParams);

            FeqCalendarParams l_feqCalendarParams = new FeqCalendarParams();
            l_feqCalendarParams.setInstitutionCode("0D");
            l_feqCalendarParams.setMarketCode("SP");
            l_feqCalendarParams.setBizDate(new Timestamp(l_calendar.getTimeInMillis()));
            l_feqCalendarParams.setBizDateType("0");
            TestDBUtility.insertWithDel(l_calendarParams);

            // 実際メソッドをコール
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3FuturesOrderManagerImpl l_orderMgr =
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            long l_lngOrderUnitId = 1001;
            IfoOrderUnit l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_lngOrderUnitId);

            l_toStopIfoOrderUnitServiceImpl.sendMQTrigger(l_orderUnit);
            assertTrue(true);
        }
        catch (WEB3BaseException l_ex)
        {
            // 予想結果と実際結果の比較
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSendMQTrigger_C0002()
    {
        final String STR_METHOD_NAME = ".testSendMQTrigger_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode",
            new Class[]{IfoOrderUnit.class},
            new WEB3BaseException(new ErrorInfo(), "abcdef"));
        try
        {
            //スタティックメソッドのデータの準備
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setBranchCode("123");
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("11");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONDAY,01);
            l_calendar.set(Calendar.DAY_OF_YEAR,01);
            l_calendar.set(Calendar.HOUR_OF_DAY,0);
            l_calendar.set(Calendar.MINUTE,0);
            l_calendar.set(Calendar.SECOND,0);
            l_calendar.set(Calendar.MILLISECOND,0);
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(l_calendar.getTimeInMillis()));

            // テーブルへデータをインサート
            IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_ifoOrderUnitParams.getRowType());
            l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setMarketId(3303L);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            IfoOrderParams l_ifoOrderParams = new IfoOrderParams();
            l_processor.doDeleteAllQuery(l_ifoOrderParams.getRowType());
            l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(10001000L);
            TestDBUtility.insertWithDel(l_ifoOrderParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            MarketParams l_maketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_maketParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();
            l_processor.doDeleteAllQuery(l_tradingTimeParams.getRowType());
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");
            l_tradingTimeParams.setSubmitMarketTrigger(WEB3SubmitMarketTriggerDef.TRIGGER);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(new Timestamp(l_calendar.getTimeInMillis()));
            l_calendarParams.setBizDateType("0");
            TestDBUtility.insertWithDel(l_calendarParams);

            // 実際メソッドをコール
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3FuturesOrderManagerImpl l_orderMgr =
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            long l_lngOrderUnitId = 1001;
            IfoOrderUnit l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_lngOrderUnitId);

            l_toStopIfoOrderUnitServiceImpl.sendMQTrigger(l_orderUnit);
            assertTrue(true);
        }
        catch (WEB3BaseException l_ex)
        {
            // 予想結果と実際結果の比較
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSendMQTrigger_C0003()
    {
        final String STR_METHOD_NAME = ".testSendMQTrigger_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send",
            new Class[]{WEB3MQMessageSpec.class},
            new WEB3MockObjectRuntimeException("error"));
        try
        {
            //スタティックメソッドのデータの準備
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setBranchCode("123");
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("11");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONDAY,01);
            l_calendar.set(Calendar.DAY_OF_YEAR,01);
            l_calendar.set(Calendar.HOUR_OF_DAY,0);
            l_calendar.set(Calendar.MINUTE,0);
            l_calendar.set(Calendar.SECOND,0);
            l_calendar.set(Calendar.MILLISECOND,0);
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(l_calendar.getTimeInMillis()));

            // テーブルへデータをインサート
            IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_ifoOrderUnitParams.getRowType());
            l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setMarketId(3303L);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv(WEB3SubmitOrderRouteDivDef.ORDER_STOP);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            IfoOrderParams l_ifoOrderParams = new IfoOrderParams();
            l_processor.doDeleteAllQuery(l_ifoOrderParams.getRowType());
            l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(10001000L);
            TestDBUtility.insertWithDel(l_ifoOrderParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            MarketParams l_maketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_maketParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();
            l_processor.doDeleteAllQuery(l_tradingTimeParams.getRowType());
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");
            l_tradingTimeParams.setSubmitMarketTrigger(WEB3SubmitMarketTriggerDef.TRIGGER);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams.setMarketCode("SP");
            l_orderSwitchingParams.setFrontOrderSystemCode("9");
            l_orderSwitchingParams.setSubmitMqTrigger("1");
            l_orderSwitchingParams.setSubmitOrderRouteDiv("9");
            TestDBUtility.insertWithDel(l_orderSwitchingParams);

            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(new Timestamp(l_calendar.getTimeInMillis()));
            l_calendarParams.setBizDateType("0");
            TestDBUtility.insertWithDel(l_calendarParams);

            // 実際メソッドをコール
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3FuturesOrderManagerImpl l_orderMgr =
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            long l_lngOrderUnitId = 1001;
            IfoOrderUnit l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_lngOrderUnitId);

            l_toStopIfoOrderUnitServiceImpl.sendMQTrigger(l_orderUnit);
            assertTrue(true);
        }
        catch (WEB3BaseException l_ex)
        {
            // 予想結果と実際結果の比較
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSendMQTrigger_C0004()
    {
        final String STR_METHOD_NAME = ".testSendMQTrigger_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send",
            new Class[]{WEB3MQMessageSpec.class},
            new WEB3MockObjectRuntimeException("error"));
        try
        {
            //スタティックメソッドのデータの準備
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setBranchCode("123");
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("11");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONDAY,01);
            l_calendar.set(Calendar.DAY_OF_YEAR,01);
            l_calendar.set(Calendar.HOUR_OF_DAY,0);
            l_calendar.set(Calendar.MINUTE,0);
            l_calendar.set(Calendar.SECOND,0);
            l_calendar.set(Calendar.MILLISECOND,0);
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(l_calendar.getTimeInMillis()));

            // テーブルへデータをインサート
            IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_ifoOrderUnitParams.getRowType());
            l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setMarketId(3303L);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            IfoOrderParams l_ifoOrderParams = new IfoOrderParams();
            l_processor.doDeleteAllQuery(l_ifoOrderParams.getRowType());
            l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(10001000L);
            TestDBUtility.insertWithDel(l_ifoOrderParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            MarketParams l_maketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_maketParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();
            l_processor.doDeleteAllQuery(l_tradingTimeParams.getRowType());
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");
            l_tradingTimeParams.setSubmitMarketTrigger(WEB3SubmitMarketTriggerDef.TRIGGER);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams.setMarketCode("SP");
            l_orderSwitchingParams.setFrontOrderSystemCode("9");
            l_orderSwitchingParams.setSubmitMqTrigger("1");
            l_orderSwitchingParams.setSubmitOrderRouteDiv("9");
            TestDBUtility.insertWithDel(l_orderSwitchingParams);

            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(new Timestamp(l_calendar.getTimeInMillis()));
            l_calendarParams.setBizDateType("0");
            TestDBUtility.insertWithDel(l_calendarParams);

            // 実際メソッドをコール
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3FuturesOrderManagerImpl l_orderMgr =
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            long l_lngOrderUnitId = 1001;
            IfoOrderUnit l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_lngOrderUnitId);

            l_toStopIfoOrderUnitServiceImpl.sendMQTrigger(l_orderUnit);
            assertTrue(true);
        }
        catch (WEB3BaseException l_ex)
        {
            // 予想結果と実際結果の比較
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //TODO
    /**
     * submit先物新規建逆指値注文
     */
    public void testSubmitFuturesOpenContractStopOrder_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitFuturesOpenContractStopOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            //スタティックメソッドのセット
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setMarketCode("SP");
            l_context.setTradingTimeType("26");
            l_context.setBizDateType("0");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 7-1, 4);
            Timestamp l_tsOrderAcceptDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_tsOrderAcceptDate);
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate", null);

            // テーブルへデータをインサート
            IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(IfoOrderUnitRow.TYPE);
            l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setBizDate("20070705");
            l_ifoOrderUnitParams.setSessionType("1");
            l_ifoOrderUnitParams.setMarketId(3303l);
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setFirstOrderUnitId(0L);
            l_ifoOrderUnitParams.setProductId(1006169090018L);
            l_ifoOrderUnitParams.setOrderUnitId(1001L);
            l_ifoOrderUnitParams.setOrderId(1001L);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_ifoOrderUnitParams.setReserveOrderExistFlag("0");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001L);
            TestDBUtility.insertWithDel(l_ifoOrderParams);

            TradingTimeParams l_tradingTimeParams =  new TradingTimeParams();
            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            ProductParams l_productParams = new ProductParams();
            l_processor.doDeleteAllQuery(ProductRow.TYPE);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            MarketParams l_maketParams = new MarketParams();
            l_processor.doDeleteAllQuery(MarketRow.TYPE);
            l_maketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_maketParams);

            MarketPreferencesParams l_maketPreferencesParams = new MarketPreferencesParams();
            l_processor.doDeleteAllQuery(MarketPreferencesRow.TYPE);
            l_maketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            TestDBUtility.insertWithDel(l_maketPreferencesParams);


            // 実際メソッドをコール
            IfoOrderUnit l_orderUnit = new IfoContractOpenOrderUnitImpl(1001L);
            toStopIfoOrderUnitServiceImpl.submitFuturesOpenContractStopOrder(l_orderUnit);

            // 予想結果と実際結果の比較
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "isEveningSessionOrder", 
                new Class[] {IfoOrderUnit.class});
            assertEquals(l_paramsValue.getFirstCalled()[0].getClass(), l_orderUnit.getClass());
            l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateFuturesOpenContractOrder",
                new Class[]{WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class, IfoOrderUnit.class});
            assertEquals(l_paramsValue.getFirstCalled()[1].getClass(), WEB3IfoOpenContractOrderSpec.class);
            assertEquals(l_paramsValue.getFirstCalled()[2].getClass(), l_orderUnit.getClass());
            l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3IfoBizLogicProvider",
                "createCommission", 
                new Class[] {long.class});
            assertEquals(l_paramsValue.getFirstCalled()[0], new Long(1001));
            l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getIfoTradedProduct",
                new Class[]{Institution.class, String.class, String.class});
            assertEquals(l_paramsValue.getFirstCalled()[1], "160030005");
            l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoBizLogicProvider",
                    "createCommission", 
                    new Class[] {long.class});
            assertEquals(l_paramsValue.getFirstCalled()[0], new Long(1001));
            l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcEstimatePrice", new Class[]
                {WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                    WEB3IfoTradedProductImpl.class, double.class, boolean.class });
            assertEquals(l_paramsValue.getFirstCalled()[0].getClass(), WEB3GentradeCommission.class);
            assertEquals(l_paramsValue.getFirstCalled()[1], new Double(0));
            assertNull(l_paramsValue.getFirstCalled()[3]);
            assertEquals(l_paramsValue.getFirstCalled()[4], new Double(100));
            assertEquals(l_paramsValue.getFirstCalled()[5], new Boolean(false));
            l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class,
                    OrderTypeEnum.class,boolean.class});
            assertEquals(((OrderTypeEnum)l_paramsValue.getFirstCalled()[3]).intValue(), 601);
            assertEquals(l_paramsValue.getFirstCalled()[4], new Boolean(true));
            l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "throwTpErrorInfo",
                new Class[]{ WEB3TPTradingPowerResult.class, WEB3GentradeSubAccount.class });
            assertNull(l_paramsValue.getFirstCalled()[0]);
            l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "insertOpenContractHostOrder",
                new Class[] {long.class});
            assertEquals(l_paramsValue.getFirstCalled()[0], new Long(1001));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage() + l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submit先物新規建逆指値注文
     */
    public void testSubmitFuturesOpenContractStopOrder_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitFuturesOpenContractStopOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //スタティックメソッドのセット
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setMarketCode("SP");
            l_context.setTradingTimeType("26");
            l_context.setBizDateType("0");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 7-1, 4);
            Timestamp l_tsOrderAcceptDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_tsOrderAcceptDate);
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate", null);

            // テーブルへデータをインサート
            IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(IfoOrderUnitRow.TYPE);
            l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setBizDate("20070705");
            l_ifoOrderUnitParams.setSessionType("1");
            l_ifoOrderUnitParams.setMarketId(3303l);
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setFirstOrderUnitId(0L);
            l_ifoOrderUnitParams.setProductId(1006169090018L);
            l_ifoOrderUnitParams.setOrderUnitId(1001L);
            l_ifoOrderUnitParams.setOrderId(1001L);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_ifoOrderUnitParams.setReserveOrderExistFlag("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001L);
            TestDBUtility.insertWithDel(l_ifoOrderParams);

            TradingTimeParams l_tradingTimeParams =  new TradingTimeParams();
            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            ProductParams l_productParams = new ProductParams();
            l_processor.doDeleteAllQuery(ProductRow.TYPE);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            MarketParams l_maketParams = new MarketParams();
            l_processor.doDeleteAllQuery(MarketRow.TYPE);
            l_maketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_maketParams);

            MarketPreferencesParams l_maketPreferencesParams = new MarketPreferencesParams();
            l_processor.doDeleteAllQuery(MarketPreferencesRow.TYPE);
            l_maketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            TestDBUtility.insertWithDel(l_maketPreferencesParams);

            // 実際メソッドをコール
            IfoOrderUnit l_orderUnit = new IfoContractOpenOrderUnitImpl(1001L);
            toStopIfoOrderUnitServiceImpl.submitFuturesOpenContractStopOrder(l_orderUnit);

            // 予想結果と実際結果の比較
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "isEveningSessionOrder", 
                new Class[] {IfoOrderUnit.class});
            assertEquals(l_paramsValue.getFirstCalled()[0].getClass(), l_orderUnit.getClass());
            l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateFuturesOpenContractOrder",
                new Class[]{WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class, IfoOrderUnit.class});
            assertEquals(l_paramsValue.getFirstCalled()[1].getClass(), WEB3IfoOpenContractOrderSpec.class);
            assertEquals(l_paramsValue.getFirstCalled()[2].getClass(), l_orderUnit.getClass());
            l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3IfoBizLogicProvider",
                "createCommission", 
                new Class[] {long.class});
            assertEquals(l_paramsValue.getFirstCalled()[0], new Long(1001));
            l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getIfoTradedProduct",
                new Class[]{Institution.class, String.class, String.class});
            assertEquals(l_paramsValue.getFirstCalled()[1], "160030005");
            l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoBizLogicProvider",
                    "createCommission", 
                    new Class[] {long.class});
            assertEquals(l_paramsValue.getFirstCalled()[0], new Long(1001));
            l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcEstimatePrice", new Class[]
                {WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                    WEB3IfoTradedProductImpl.class, double.class, boolean.class });
            assertEquals(l_paramsValue.getFirstCalled()[0].getClass(), WEB3GentradeCommission.class);
            assertEquals(l_paramsValue.getFirstCalled()[1], new Double(0));
            assertNull(l_paramsValue.getFirstCalled()[3]);
            assertEquals(l_paramsValue.getFirstCalled()[4], new Double(100));
            assertEquals(l_paramsValue.getFirstCalled()[5], new Boolean(false));
            l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class,
                    OrderTypeEnum.class,boolean.class});
            assertEquals(((OrderTypeEnum)l_paramsValue.getFirstCalled()[3]).intValue(), 601);
            assertEquals(l_paramsValue.getFirstCalled()[4], new Boolean(true));
            l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "throwTpErrorInfo",
                new Class[]{ WEB3TPTradingPowerResult.class, WEB3GentradeSubAccount.class });
            assertNull(l_paramsValue.getFirstCalled()[0]);
            l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "insertOpenContractHostOrder",
                new Class[] {long.class});
            assertEquals(l_paramsValue.getFirstCalled()[0], new Long(1001));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage() + l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     * submit先物返済逆指値注文
     *
     */
    public void testSubmitFuturesSettleContractStopOrder_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitFuturesSettleContractStopOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "validateFuturesSettleContractOrder",
            new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
            new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT));

        try
        {


            //スタティックメソッドのセット
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setMarketCode("SP");
            l_context.setTradingTimeType("26");
            l_context.setBizDateType("0");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 7-1, 4);
            Timestamp l_tsOrderAcceptDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_tsOrderAcceptDate);
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate", null);

            // テーブルへデータをインサート
            IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(IfoOrderUnitRow.TYPE);
            l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setBizDate("20070705");
            l_ifoOrderUnitParams.setSessionType("1");
            l_ifoOrderUnitParams.setMarketId(3303l);
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setFirstOrderUnitId(0L);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            TradingTimeParams l_tradingTimeParams =  new TradingTimeParams();
            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            IfoClosingContractSpecParams l_ifoClosingContractSpecParams = new IfoClosingContractSpecParams();
            l_processor.doDeleteAllQuery(IfoClosingContractSpecRow.TYPE);
            l_ifoClosingContractSpecParams = TestDBUtility.getIfoClosingContractSpecRow();
            l_ifoClosingContractSpecParams.setQuantity(101.0D);
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);

            IfoContractParams l_ifoContractParams = new IfoContractParams();
            l_processor.doDeleteAllQuery(IfoContractRow.TYPE);
            l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setQuantity(201.0);
            TestDBUtility.insertWithDel(l_ifoContractParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            ProductParams l_productParams = new ProductParams();
            l_processor.doDeleteAllQuery(ProductRow.TYPE);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            MarketParams l_maketParams = new MarketParams();
            l_processor.doDeleteAllQuery(MarketRow.TYPE);
            l_maketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_maketParams);

            MarketPreferencesParams l_maketPreferencesParams = new MarketPreferencesParams();
            l_processor.doDeleteAllQuery(MarketPreferencesRow.TYPE);
            l_maketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            TestDBUtility.insertWithDel(l_maketPreferencesParams);

            // 実際メソッドをコール
            IfoOrderUnit l_orderUnit = new IfoContractSettleOrderUnitImpl(1001L);
            toStopIfoOrderUnitServiceImpl.submitFuturesSettleContractStopOrder(l_orderUnit);

            // 予想結果と実際結果の比較
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     * submit先物返済逆指値注文
     *
     */
    public void testSubmitFuturesSettleContractStopOrder_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitFuturesSettleContractStopOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "validateFuturesSettleContractOrder",
            new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
            new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT));

        try
        {


            //スタティックメソッドのセット
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setMarketCode("SP");
            l_context.setTradingTimeType("26");
            l_context.setBizDateType("0");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 7-1, 4);
            Timestamp l_tsOrderAcceptDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_tsOrderAcceptDate);
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate", null);

            // テーブルへデータをインサート
            IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(IfoOrderUnitRow.TYPE);
            l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setBizDate("20070705");
            l_ifoOrderUnitParams.setSessionType("1");
            l_ifoOrderUnitParams.setMarketId(3303l);
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setFirstOrderUnitId(0L);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_ifoOrderUnitParams.setReserveOrderExistFlag("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            TradingTimeParams l_tradingTimeParams =  new TradingTimeParams();
            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            IfoClosingContractSpecParams l_ifoClosingContractSpecParams = new IfoClosingContractSpecParams();
            l_processor.doDeleteAllQuery(IfoClosingContractSpecRow.TYPE);
            l_ifoClosingContractSpecParams = TestDBUtility.getIfoClosingContractSpecRow();
            l_ifoClosingContractSpecParams.setQuantity(101.0D);
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);

            IfoContractParams l_ifoContractParams = new IfoContractParams();
            l_processor.doDeleteAllQuery(IfoContractRow.TYPE);
            l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setQuantity(201.0);
            TestDBUtility.insertWithDel(l_ifoContractParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            ProductParams l_productParams = new ProductParams();
            l_processor.doDeleteAllQuery(ProductRow.TYPE);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            MarketParams l_maketParams = new MarketParams();
            l_processor.doDeleteAllQuery(MarketRow.TYPE);
            l_maketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_maketParams);

            MarketPreferencesParams l_maketPreferencesParams = new MarketPreferencesParams();
            l_processor.doDeleteAllQuery(MarketPreferencesRow.TYPE);
            l_maketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            TestDBUtility.insertWithDel(l_maketPreferencesParams);

            // 実際メソッドをコール
            IfoOrderUnit l_orderUnit = new IfoContractSettleOrderUnitImpl(1001L);
            toStopIfoOrderUnitServiceImpl.submitFuturesSettleContractStopOrder(l_orderUnit);

            // 予想結果と実際結果の比較
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submitOP新規建逆指値注文
     */
    public void testSubmitOptionOpenContractStopOrder_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitOptionOpenContractStopOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getIfoTradedProduct",
                new Class[]{Institution.class, String.class, String.class},
                null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcEstimateDeliveryAmount",
                new Class[]{ WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                    double.class, SideEnum.class, boolean.class, boolean.class },
                new WEB3IfoEstimateDeliveryAmountCalcResult());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class, boolean.class},
                null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "throwTpErrorInfo",
                new Class[]{WEB3TPTradingPowerResult.class, WEB3GentradeSubAccount.class},
                null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "insertOpenContractHostOrder",
                new Class[]{long.class},
                null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getSubmitOrderRouteDiv",
                new Class[] {String.class, String.class},
                null);

            //スタティックメソッドのセット
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setMarketCode("SP");
            l_context.setTradingTimeType("26");
            l_context.setBizDateType("0");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 7-1, 4);
            Timestamp l_tsOrderAcceptDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_tsOrderAcceptDate);
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate", null);

            // テーブルへデータをインサート
            IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(IfoOrderUnitRow.TYPE);
            l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setBizDate("20070705");
            l_ifoOrderUnitParams.setSessionType("1");
            l_ifoOrderUnitParams.setMarketId(3303l);
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setFirstOrderUnitId(0L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TradingTimeParams l_tradingTimeParams =  new TradingTimeParams();
            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            ProductParams l_productParams = new ProductParams();
            l_processor.doDeleteAllQuery(ProductRow.TYPE);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);

            IfoProductParams l_ifoProductParams = new IfoProductParams();
            l_processor.doDeleteAllQuery(IfoProductRow.TYPE);
            l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setProductCode("160030005");
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TradedProductParams l_tradedProductParams =  new TradedProductParams();
            l_processor.doDeleteAllQuery(TradedProductRow.TYPE);
            l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            IfoTradedProductParams l_ifoTradedProductParams = new IfoTradedProductParams();
            l_processor.doDeleteAllQuery(IfoTradedProductRow.TYPE);
            l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(1006169090018L);
            l_ifoTradedProductParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            MarketParams l_marketParams = new MarketParams();
            l_processor.doDeleteAllQuery(MarketRow.TYPE);
            l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            MarketPreferencesParams l_marketPreferencesParams = new MarketPreferencesParams();
            l_processor.doDeleteAllQuery(MarketPreferencesRow.TYPE);
            l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            TestDBUtility.insertWithDel(l_marketPreferencesParams);


            // 実際メソッドをコール
            IfoOrderUnit l_orderUnit = new IfoContractOpenOrderUnitImpl(1001L);
            toStopIfoOrderUnitServiceImpl.submitOptionOpenContractStopOrder(l_orderUnit);

            // 予想結果と実際結果の比較
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submitOP新規建逆指値注文
     */
    public void testSubmitOptionOpenContractStopOrder_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitOptionOpenContractStopOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getIfoTradedProduct",
                new Class[]{Institution.class, String.class, String.class},
                null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcEstimateDeliveryAmount",
                new Class[]{ WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                    double.class, SideEnum.class, boolean.class, boolean.class },
                new WEB3IfoEstimateDeliveryAmountCalcResult());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class, boolean.class},
                null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "throwTpErrorInfo",
                new Class[]{WEB3TPTradingPowerResult.class, WEB3GentradeSubAccount.class},
                null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "insertOpenContractHostOrder",
                new Class[]{long.class},
                null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getSubmitOrderRouteDiv",
                new Class[] {String.class, String.class},
                null);

            //スタティックメソッドのセット
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setMarketCode("SP");
            l_context.setTradingTimeType("26");
            l_context.setBizDateType("0");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 7-1, 4);
            Timestamp l_tsOrderAcceptDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_tsOrderAcceptDate);
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate", null);

            // テーブルへデータをインサート
            IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(IfoOrderUnitRow.TYPE);
            l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setBizDate("20070705");
            l_ifoOrderUnitParams.setSessionType("1");
            l_ifoOrderUnitParams.setMarketId(3303l);
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setFirstOrderUnitId(0L);
            l_ifoOrderUnitParams.setReserveOrderExistFlag("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TradingTimeParams l_tradingTimeParams =  new TradingTimeParams();
            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            ProductParams l_productParams = new ProductParams();
            l_processor.doDeleteAllQuery(ProductRow.TYPE);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);

            IfoProductParams l_ifoProductParams = new IfoProductParams();
            l_processor.doDeleteAllQuery(IfoProductRow.TYPE);
            l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setProductCode("160030005");
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TradedProductParams l_tradedProductParams =  new TradedProductParams();
            l_processor.doDeleteAllQuery(TradedProductRow.TYPE);
            l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            IfoTradedProductParams l_ifoTradedProductParams = new IfoTradedProductParams();
            l_processor.doDeleteAllQuery(IfoTradedProductRow.TYPE);
            l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(1006169090018L);
            l_ifoTradedProductParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            MarketParams l_marketParams = new MarketParams();
            l_processor.doDeleteAllQuery(MarketRow.TYPE);
            l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            MarketPreferencesParams l_marketPreferencesParams = new MarketPreferencesParams();
            l_processor.doDeleteAllQuery(MarketPreferencesRow.TYPE);
            l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            TestDBUtility.insertWithDel(l_marketPreferencesParams);


            // 実際メソッドをコール
            IfoOrderUnit l_orderUnit = new IfoContractOpenOrderUnitImpl(1001L);
            toStopIfoOrderUnitServiceImpl.submitOptionOpenContractStopOrder(l_orderUnit);

            // 予想結果と実際結果の比較
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submitOP返済逆指値注文
     */
    public void testSubmitOptionSettleContractStopOrder_C0001()
    {
        final String STR_METHOD_NAME = ".testSubmitOptionSettleContractStopOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getIfoTradedProduct",
                new Class[]{Institution.class, String.class, String.class},
                null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcEstimateDeliveryAmount",
                new Class[]{ WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                    double.class, SideEnum.class, boolean.class, boolean.class },
                new WEB3IfoEstimateDeliveryAmountCalcResult());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "insertSettleContractHostOrder",
                new Class[] {long.class},
                null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getSubmitOrderRouteDiv",
                new Class[] {String.class, String.class},
                null);

            //スタティックメソッドのセット
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setMarketCode("SP");
            l_context.setTradingTimeType("26");
            l_context.setBizDateType("0");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 7-1, 4);
            Timestamp l_tsOrderAcceptDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_tsOrderAcceptDate);
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate", null);

            // テーブルへデータをインサート
            IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(IfoOrderUnitRow.TYPE);
            l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setBizDate("20070705");
            l_ifoOrderUnitParams.setSessionType("1");
            l_ifoOrderUnitParams.setMarketId(3303l);
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setFirstOrderUnitId(0L);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TradingTimeParams l_tradingTimeParams =  new TradingTimeParams();
            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            IfoClosingContractSpecParams l_ifoClosingContractSpecParams = new IfoClosingContractSpecParams();
            l_processor.doDeleteAllQuery(IfoClosingContractSpecRow.TYPE);
            l_ifoClosingContractSpecParams = TestDBUtility.getIfoClosingContractSpecRow();
            l_ifoClosingContractSpecParams.setQuantity(101.0D);
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);

            IfoContractParams l_ifoContractParams = new IfoContractParams();
            l_processor.doDeleteAllQuery(IfoContractRow.TYPE);
            l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setQuantity(201.0);
            TestDBUtility.insertWithDel(l_ifoContractParams);

            IfoLockedContractDetailsParams l_ifoLockedContractDetailsParams = new IfoLockedContractDetailsParams();
            l_processor.doDeleteAllQuery(IfoLockedContractDetailsRow.TYPE);
            l_ifoLockedContractDetailsParams.setContractId(1001);
            l_ifoLockedContractDetailsParams.setAccountId(101001010010L);
            l_ifoLockedContractDetailsParams.setSubAccountId(10100101001007L);
            l_ifoLockedContractDetailsParams.setLockedQuantity(0.1D);
            l_ifoLockedContractDetailsParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040705","yyyyMMdd"));
            l_ifoLockedContractDetailsParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040705","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoLockedContractDetailsParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            ProductParams l_productParams = new ProductParams();
            l_processor.doDeleteAllQuery(ProductRow.TYPE);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            MarketParams l_maketParams = new MarketParams();
            l_processor.doDeleteAllQuery(MarketRow.TYPE);
            l_maketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_maketParams);

            MarketPreferencesParams l_maketPreferencesParams = new MarketPreferencesParams();
            l_processor.doDeleteAllQuery(MarketPreferencesRow.TYPE);
            l_maketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            TestDBUtility.insertWithDel(l_maketPreferencesParams);

            // 実際メソッドをコール
            IfoOrderUnit l_orderUnit = new IfoContractSettleOrderUnitImpl(1001L);
            toStopIfoOrderUnitServiceImpl.submitOptionSettleContractStopOrder(l_orderUnit);

            // 予想結果と実際結果の比較
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitOptionSettleContractStopOrder_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitOptionSettleContractStopOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getIfoTradedProduct",
                new Class[]{Institution.class, String.class, String.class},
                null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcEstimateDeliveryAmount",
                new Class[]{ WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                    double.class, SideEnum.class, boolean.class, boolean.class },
                new WEB3IfoEstimateDeliveryAmountCalcResult());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "insertSettleContractHostOrder",
                new Class[] {long.class},
                null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getSubmitOrderRouteDiv",
                new Class[] {String.class, String.class},
                null);

            //スタティックメソッドのセット
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setMarketCode("SP");
            l_context.setTradingTimeType("26");
            l_context.setBizDateType("0");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 7-1, 4);
            Timestamp l_tsOrderAcceptDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_tsOrderAcceptDate);
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate", null);

            // テーブルへデータをインサート
            IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(IfoOrderUnitRow.TYPE);
            l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setBizDate("20070705");
            l_ifoOrderUnitParams.setSessionType("1");
            l_ifoOrderUnitParams.setMarketId(3303l);
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            l_ifoOrderUnitParams.setFirstOrderUnitId(0L);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_ifoOrderUnitParams.setReserveOrderExistFlag("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TradingTimeParams l_tradingTimeParams =  new TradingTimeParams();
            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            IfoClosingContractSpecParams l_ifoClosingContractSpecParams = new IfoClosingContractSpecParams();
            l_processor.doDeleteAllQuery(IfoClosingContractSpecRow.TYPE);
            l_ifoClosingContractSpecParams = TestDBUtility.getIfoClosingContractSpecRow();
            l_ifoClosingContractSpecParams.setQuantity(101.0D);
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);

            IfoContractParams l_ifoContractParams = new IfoContractParams();
            l_processor.doDeleteAllQuery(IfoContractRow.TYPE);
            l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setQuantity(201.0);
            TestDBUtility.insertWithDel(l_ifoContractParams);

            IfoLockedContractDetailsParams l_ifoLockedContractDetailsParams = new IfoLockedContractDetailsParams();
            l_processor.doDeleteAllQuery(IfoLockedContractDetailsRow.TYPE);
            l_ifoLockedContractDetailsParams.setContractId(1001);
            l_ifoLockedContractDetailsParams.setAccountId(101001010010L);
            l_ifoLockedContractDetailsParams.setSubAccountId(10100101001007L);
            l_ifoLockedContractDetailsParams.setLockedQuantity(0.1D);
            l_ifoLockedContractDetailsParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040705","yyyyMMdd"));
            l_ifoLockedContractDetailsParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040705","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoLockedContractDetailsParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            ProductParams l_productParams = new ProductParams();
            l_processor.doDeleteAllQuery(ProductRow.TYPE);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            MarketParams l_maketParams = new MarketParams();
            l_processor.doDeleteAllQuery(MarketRow.TYPE);
            l_maketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_maketParams);

            MarketPreferencesParams l_maketPreferencesParams = new MarketPreferencesParams();
            l_processor.doDeleteAllQuery(MarketPreferencesRow.TYPE);
            l_maketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            TestDBUtility.insertWithDel(l_maketPreferencesParams);

            // 実際メソッドをコール
            IfoOrderUnit l_orderUnit = new IfoContractSettleOrderUnitImpl(1001L);
            toStopIfoOrderUnitServiceImpl.submitOptionSettleContractStopOrder(l_orderUnit);

            // 予想結果と実際結果の比較
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3ToStopIfoOrderUnitServiceImplForTest extends WEB3ToStopIfoOrderUnitServiceImpl
    {
        protected boolean isProcessObject (IfoOrderUnit l_orderUnit)
        {
            return true;
        }

        protected WEB3IfoOpenContractChangeSpec createOpenContractChangeSpec (IfoOrderUnit l_orderUnit)
        {
            return null;
        }

        protected void updateOrderData(
            IfoOrderUnit l_orderUnit, 
            double l_dblOrderPrice, 
            double l_dblEstimatedPrice,
            String l_strOrderRootDiv)
        {
            return;
        }

        protected void sendMQTrigger(IfoOrderUnit l_orderUnit) throws WEB3SystemLayerException
        {
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    "");
        }
    }
}
@

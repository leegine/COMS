head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.19.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToWLimitIfoSwitchUnitServiceImplTest.java;


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
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractSettleOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoChangeSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoOpenContractChangeSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.mock.util.WEB3MockObjectRuntimeException;
import webbroker3.util.WEB3LogUtility;

/**
 * W指値注文先物OP切替一件サービスImplのテスト<BR>
 * @@author 崔遠鵬(中訊)
 * @@version 1.0
 */
public class WEB3ToWLimitIfoSwitchUnitServiceImplTest extends TestBaseForMock
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToWLimitIfoSwitchUnitServiceImplTest.class); 

    WEB3ToWLimitIfoSwitchUnitServiceImpl l_toWLimitIfoSwitchUnitServiceImplTest =
        new WEB3ToWLimitIfoSwitchUnitServiceImplForTest();

    IfoContractSettleOrderUnit l_ifoContractSettleOrderUnit = null;

    public WEB3ToWLimitIfoSwitchUnitServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        ThreadLocalSystemAttributesRegistry.clearAttributes();
        WEB3GentradeTradingTimeManagementForMock.setSystemAttributes();
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.set(Calendar.YEAR,2007);
        l_calendar.set(Calendar.MONTH,00);
        l_calendar.set(Calendar.DAY_OF_YEAR,01);
        l_calendar.set(Calendar.HOUR_OF_DAY,0);
        l_calendar.set(Calendar.MINUTE,0);
        l_calendar.set(Calendar.SECOND,0);
        l_calendar.set(Calendar.MILLISECOND,0);
        ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
            new Timestamp(l_calendar.getTimeInMillis()));
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);
        
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testSubmitFuturesOpenContractWLimitOrder_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitFuturesOpenContractWLimitOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "validateFuturesChangeOrder",
            new Class[]{WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class, boolean.class},
            new WEB3MockObjectRuntimeException());
        try
        {
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
            l_ifoOrderUnitParams.setWLimitPrice(200);
            l_ifoOrderUnitParams.setQuantity(2000);
            l_ifoOrderUnitParams.setBizDate("20070102");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);

            IfoProductParams l_ifoProductParams1 = TestDBUtility.getIfoProductRow();
            l_ifoProductParams1.setProductId(1006169090018L);
            l_ifoProductParams1.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ifoProductParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ifoProductParams1);

            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(6610L);
            TestDBUtility.insertWithDel(l_traderParams);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("381");
            l_tradingTimeParams1.setMarketCode("SP");
            l_tradingTimeParams1.setTradingTimeType("26");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("381");
            l_tradingTimeParams2.setMarketCode("SP");
            l_tradingTimeParams2.setTradingTimeType("26");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setBizDateType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);

            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setInstitutionCode("0D");
            l_tradingTimeParams3.setBranchCode("381");
            l_tradingTimeParams3.setMarketCode("SP");
            l_tradingTimeParams3.setTradingTimeType("26");
            l_tradingTimeParams3.setProductCode("0");
            l_tradingTimeParams3.setBizDateType("3");
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
//            Calendar l_calendar = Calendar.getInstance();
//            l_calendar.set(Calendar.YEAR,2007);
//            l_calendar.set(Calendar.MONDAY,01);
//            l_calendar.set(Calendar.DAY_OF_YEAR,01);
//            l_calendar.set(Calendar.HOUR_OF_DAY,0);
//            l_calendar.set(Calendar.MINUTE,0);
//            l_calendar.set(Calendar.SECOND,0);
//            l_calendar.set(Calendar.MILLISECOND,0);
//            CalendarParams l_calendarParams = new CalendarParams();
//            l_calendarParams.setHoliday(new Timestamp(l_calendar.getTimeInMillis()));
//            l_calendarParams.setBizDateType("0");
//            TestDBUtility.insertWithDel(l_calendarParams);

            //実際のメソッドをコール
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3FuturesOrderManagerImpl l_orderMgr =
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            long l_lngOrderUnitId = 1001;
            IfoOrderUnit l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_lngOrderUnitId);

            l_toWLimitIfoSwitchUnitServiceImplTest.submitFuturesOpenContractWLimitOrder(l_orderUnit);
            fail();
        }
        catch (RuntimeException l_ex)
        {
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateFuturesChangeOrder",
                new Class[] {WEB3GentradeSubAccount.class,
                WEB3IfoOpenContractChangeSpec.class, boolean.class});

            // 予想結果と実際結果の比較
            assertEquals(WEB3IfoOpenContractChangeSpec.class, l_paramsValue.getFirstCalled()[1].getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSubmitFuturesOpenContractWLimitOrder_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitFuturesOpenContractWLimitOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "validateFuturesChangeOrder",
            new Class[]{WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class, boolean.class},
            new OrderValidationResult(ProcessingResult.SUCCESS_RESULT));

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoProductManagerImpl",
            "getIfoTradedProduct",
            new Class[] {Institution.class, String.class, String.class},
            null);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "calcChangeEstimatePrice",
            new Class[] {WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
            WEB3IfoTradedProductImpl.class, double.class, double.class, double.class, boolean.class},
            new WEB3MockObjectRuntimeException());
        try
        {
            // テーブルへデータをインサート
            IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_ifoOrderUnitParams.getRowType());
            l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderChanel("1");
            l_ifoOrderUnitParams.setMarketId(3303L);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_ifoOrderUnitParams.setSonarTradedCode("1");
            l_ifoOrderUnitParams.setCommProductCode("2");
            l_ifoOrderUnitParams.setCommTblNo("1");
            l_ifoOrderUnitParams.setCommTblSubNo("1");
            l_ifoOrderUnitParams.setWLimitPrice(200);
            l_ifoOrderUnitParams.setQuantity(2000);
            l_ifoOrderUnitParams.setBizDate("20070102");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            IfoProductParams l_ifoProductParams1 = TestDBUtility.getIfoProductRow();
            l_ifoProductParams1.setProductId(1006169090018L);
            l_ifoProductParams1.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ifoProductParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ifoProductParams1);

            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(6610L);
            TestDBUtility.insertWithDel(l_traderParams);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONDAY,01);
            l_calendar.set(Calendar.DAY_OF_YEAR,01);
            l_calendar.set(Calendar.HOUR_OF_DAY,0);
            l_calendar.set(Calendar.MINUTE,0);
            l_calendar.set(Calendar.SECOND,0);
            l_calendar.set(Calendar.MILLISECOND,0);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(new Timestamp(l_calendar.getTimeInMillis()));
            l_calendarParams.setBizDateType("0");
            TestDBUtility.insertWithDel(l_calendarParams);

            //実際のメソッドをコール
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3FuturesOrderManagerImpl l_orderMgr =
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            long l_lngOrderUnitId = 1001;
            IfoOrderUnit l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_lngOrderUnitId);

            l_toWLimitIfoSwitchUnitServiceImplTest.submitFuturesOpenContractWLimitOrder(l_orderUnit);
            fail();
        }
        catch (RuntimeException l_ex)
        {
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcChangeEstimatePrice",
                new Class[] {WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                WEB3IfoTradedProductImpl.class, double.class, double.class, double.class,
                boolean.class});

            // 予想結果と実際結果の比較
            assertEquals("200.0", l_paramsValue.getFirstCalled()[1].toString());
            assertEquals("2000.0", l_paramsValue.getFirstCalled()[4].toString());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSubmitFuturesSettleContractWLimitOrder_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitFuturesSettleContractWLimitOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateFuturesChangeSettleContractOrder", new Class[]
            {WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class, boolean.class},
            new WEB3MockObjectRuntimeException());
            
        try
        {
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
            l_ifoOrderUnitParams.setWLimitPrice(200);
            l_ifoOrderUnitParams.setQuantity(2000);
            l_ifoOrderUnitParams.setBizDate("20070102");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);

            IfoProductParams l_ifoProductParams1 = TestDBUtility.getIfoProductRow();
            l_ifoProductParams1.setProductId(1006169090018L);
            l_ifoProductParams1.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ifoProductParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ifoProductParams1);

            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(6610L);
            TestDBUtility.insertWithDel(l_traderParams);

            IfoClosingContractSpecParams l_ifoClosingContractSpecParams = new IfoClosingContractSpecParams();
            l_ifoClosingContractSpecParams.setClosingContractSpecId(20002777L);
            l_ifoClosingContractSpecParams.setAccountId(101001010010L);
            l_ifoClosingContractSpecParams.setSubAccountId(10100101001007L);
            l_ifoClosingContractSpecParams.setOrderId(1001);
            l_ifoClosingContractSpecParams.setOrderUnitId(1001);
            l_ifoClosingContractSpecParams.setContractId(1001L);
            l_ifoClosingContractSpecParams.setQuantity(7000L);
            l_ifoClosingContractSpecParams.setClosingSerialNo(123);// getExecutedQuantity
            l_ifoClosingContractSpecParams.setExecutedQuantity(2000L);
            l_ifoClosingContractSpecParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ifoClosingContractSpecParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);
            
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setAccountId(101001010010L);
            l_ifoContractParams.setSubAccountId(10100101001007L);
            l_ifoContractParams.setMarketId(3303L);
            l_ifoContractParams.setProductId(1006169090018L);
            l_ifoContractParams.setContractId(1001L);
            l_ifoContractParams.setQuantity(7000L);
            TestDBUtility.insertWithDel(l_ifoContractParams);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONDAY,01);
            l_calendar.set(Calendar.DAY_OF_YEAR,01);
            l_calendar.set(Calendar.HOUR_OF_DAY,0);
            l_calendar.set(Calendar.MINUTE,0);
            l_calendar.set(Calendar.SECOND,0);
            l_calendar.set(Calendar.MILLISECOND,0);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(new Timestamp(l_calendar.getTimeInMillis()));
            l_calendarParams.setBizDateType("0");
            TestDBUtility.insertWithDel(l_calendarParams);

            //実際のメソッドをコール
            l_ifoContractSettleOrderUnit = new IfoContractSettleOrderUnitImpl(1001);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "getOrderUnit",
                new Class[] {long.class},
                this.l_ifoContractSettleOrderUnit);

            l_toWLimitIfoSwitchUnitServiceImplTest.submitFuturesSettleContractWLimitOrder(l_ifoContractSettleOrderUnit);
            fail();
        }
        catch (RuntimeException l_ex)
        {
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateFuturesChangeSettleContractOrder",
                new Class[] {WEB3GentradeSubAccount.class,
                    WEB3IfoChangeSettleContractOrderSpec.class, boolean.class});

            // 予想結果と実際結果の比較
            assertEquals(WEB3IfoChangeSettleContractOrderSpec.class, l_paramsValue.getFirstCalled()[1].getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSubmitFuturesSettleContractWLimitOrder_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitFuturesSettleContractWLimitOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateFuturesChangeSettleContractOrder", new Class[]
            {WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class, boolean.class},
            new OrderValidationResult(ProcessingResult.SUCCESS_RESULT));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoProductManagerImpl",
            "getIfoTradedProduct",
            new Class[] {Institution.class, String.class, String.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "calcChangeEstimateSettlementIncome",
            new Class[] {WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class, WEB3IfoTradedProductImpl.class,
                SettleContractEntry[].class, double.class, SideEnum.class, double.class, long.class, boolean.class},
            new WEB3MockObjectRuntimeException());
        try
        {
            // テーブルへデータをインサート
            IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_ifoOrderUnitParams.getRowType());
            l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setMarketId(3303L);
            l_ifoOrderUnitParams.setWLimitPrice(700L);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_ifoOrderUnitParams.setWLimitPrice(200);
            l_ifoOrderUnitParams.setQuantity(2000);
            l_ifoOrderUnitParams.setBizDate("20070102");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);

            IfoProductParams l_ifoProductParams1 = TestDBUtility.getIfoProductRow();
            l_ifoProductParams1.setProductId(1006169090018L);
            l_ifoProductParams1.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ifoProductParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ifoProductParams1);

            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(6610L);
            TestDBUtility.insertWithDel(l_traderParams);

            IfoClosingContractSpecParams l_ifoClosingContractSpecParams = new IfoClosingContractSpecParams();
            l_ifoClosingContractSpecParams.setClosingContractSpecId(20002777L);
            l_ifoClosingContractSpecParams.setAccountId(101001010010L);
            l_ifoClosingContractSpecParams.setSubAccountId(10100101001007L);
            l_ifoClosingContractSpecParams.setOrderId(1001);
            l_ifoClosingContractSpecParams.setOrderUnitId(1001);
            l_ifoClosingContractSpecParams.setContractId(123456L);
            l_ifoClosingContractSpecParams.setQuantity(7000L);
            l_ifoClosingContractSpecParams.setClosingSerialNo(123);// getExecutedQuantity
            l_ifoClosingContractSpecParams.setExecutedQuantity(2000L);
            l_ifoClosingContractSpecParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ifoClosingContractSpecParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);
            
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setAccountId(101001010010L);
            l_ifoContractParams.setSubAccountId(10100101001007L);
            l_ifoContractParams.setMarketId(3303L);
            l_ifoContractParams.setProductId(1006169090018L);
            l_ifoContractParams.setContractId(123456L);
            l_ifoContractParams.setQuantity(7000L);
            TestDBUtility.insertWithDel(l_ifoContractParams);


            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONDAY,01);
            l_calendar.set(Calendar.DAY_OF_YEAR,01);
            l_calendar.set(Calendar.HOUR_OF_DAY,0);
            l_calendar.set(Calendar.MINUTE,0);
            l_calendar.set(Calendar.SECOND,0);
            l_calendar.set(Calendar.MILLISECOND,0);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(new Timestamp(l_calendar.getTimeInMillis()));
            l_calendarParams.setBizDateType("0");
            TestDBUtility.insertWithDel(l_calendarParams);

            //実際のメソッドをコール
            l_ifoContractSettleOrderUnit = new IfoContractSettleOrderUnitImpl(1001);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "getOrderUnit",
                new Class[] {long.class},
                this.l_ifoContractSettleOrderUnit);

            l_toWLimitIfoSwitchUnitServiceImplTest.submitFuturesSettleContractWLimitOrder(l_ifoContractSettleOrderUnit);
            fail();
        }
        catch (RuntimeException l_ex)
        {
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcChangeEstimateSettlementIncome",
                new Class[]
                {WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class, SideEnum.class,
                double.class, long.class, boolean.class});

            // 予想結果と実際結果の比較
            assertEquals("200.0", l_paramsValue.getFirstCalled()[1].toString());
            assertEquals("7000.0", l_paramsValue.getFirstCalled()[5].toString());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private class WEB3ToWLimitIfoSwitchUnitServiceImplForTest extends WEB3ToWLimitIfoSwitchUnitServiceImpl
    {
        protected boolean isProcessObject(IfoOrderUnit l_orderUnit) throws WEB3BaseException
        {
            return true;
        }

        protected void validateSwitchPossible(IfoOrderUnit l_orderUnit)
        {
            return;
        }

        protected void updateSwitchFail(IfoOrderUnit l_orderUnit, String l_strErrorReasonCode) throws WEB3BaseException
        {
            throw new WEB3MockObjectRuntimeException();
        }
    }
}
@

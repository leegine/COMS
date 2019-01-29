head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.59.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoOrderManagerReusableValidationsTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP発注審査個別チェック(WEB3IfoOrderManagerReusableValidationsTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/7   徐大方 (Sinocom) 新規作成
*/

package webbroker3.ifo;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrder;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoOrderImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.ifo.data.IfoLimitPriceRangeMasterParams;
import webbroker3.ifo.data.IfoLimitPriceRangeMasterRow;
import webbroker3.ifo.data.IfoTickValuesMasterParams;
import webbroker3.ifo.data.IfoTickValuesMasterRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP発注審査個別チェック)<BR>
 * 先物OP発注審査個別チェッククラス<BR>
 *
 * @@author 徐大方
 * @@version 1.0
 */
public class WEB3IfoOrderManagerReusableValidationsTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3IfoOrderManagerReusableValidationsTest.class);
    public WEB3IfoOrderManagerReusableValidationsTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
        TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
        TestDBUtility.deleteAll(MarketRow.TYPE);
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setProductCode("12");
        l_clendarContext.setTradingTimeType("01");
        l_clendarContext.setOrderAcceptProduct("01");
        l_clendarContext.setBizDateType("1");

        WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.set(Calendar.HOUR_OF_DAY,15);
        l_calendar.set(Calendar.MINUTE,00);
        l_calendar.set(Calendar.SECOND,01);

        Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());
        WEB3GentradeTradingTimeManagementForMock.setTimestampTag(l_tsBizDate);

    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testCase1()
    {
        final String STR_METHOD_NAME = "testCase1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
        l_branchPreferencesParams.setBranchId(111);
        l_branchPreferencesParams.setName("multi.changeability.of.market.notified.order.in.break.time");
        l_branchPreferencesParams.setNameSerialNo(1);
        l_branchPreferencesParams.setValue("0");

        try
        {
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        try
        {
            l_reusableValidations.validateMultiChangeabilityOfMarketNotifiedOrderInBreakTime(111);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase2()
    {
        final String STR_METHOD_NAME = "testCase2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
        l_branchPreferencesParams.setBranchId(111);
        l_branchPreferencesParams.setName("multi.changeability.of.market.notified.order.in.break.time");
        l_branchPreferencesParams.setNameSerialNo(1);
        l_branchPreferencesParams.setValue("1");
       
        try
        {
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        try
        {
            l_reusableValidations.validateMultiChangeabilityOfMarketNotifiedOrderInBreakTime(111);
        }

        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00032.getErrorMessage(), l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase3()
    {
        final String STR_METHOD_NAME = "testCase3()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        try
        {
            l_reusableValidations.validateMultiChangeabilityOfMarketNotifiedOrderInBreakTime(111);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase4()
    {
        final String STR_METHOD_NAME = "testCase4()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderUnit l_ifoOrderUnit = null;
        double l_dblQuantity = 0;
        double l_dblPriceChange = 0;
        IfoOrderExecutionConditionType l_changeExecCondType = IfoOrderExecutionConditionType.AT_MARKET_CLOSE;
        try
        {
            l_reusableValidations.validateChangeOrderRevLimit(
                l_ifoOrderUnit,
                l_dblQuantity,
                l_dblPriceChange,
                l_changeExecCondType);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals("パラメータ値不正。", l_ex.getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase5()
    {
        final String STR_METHOD_NAME = "testCase5()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getChangeOrderRev", new Class[] {IfoOrderUnit.class},
            null);
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        IfoOrderUnit l_ifoOrderUnit = null;
        try
        {
            l_ifoOrderUnit = (IfoOrderUnit) l_orderManager.getOrderUnit(1001);
        }
        catch (NotFoundException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        double l_dblQuantity = 0;
        double l_dblPriceChange = 0;
        IfoOrderExecutionConditionType l_changeExecCondType = IfoOrderExecutionConditionType.AT_MARKET_CLOSE;
        try
        {
            l_reusableValidations.validateChangeOrderRevLimit(
                l_ifoOrderUnit,
                l_dblQuantity,
                l_dblPriceChange,
                l_changeExecCondType);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals("パラメータ値不正。", l_ex.getMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase6()
    {
        final String STR_METHOD_NAME = "testCase6()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getChangeOrderRev", new Class[] {IfoOrderUnit.class},
            new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02185,
                this.getClass().getName() + "." + STR_METHOD_NAME));
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        IfoOrderUnit l_ifoOrderUnit = null;
        try
        {
            l_ifoOrderUnit = (IfoOrderUnit) l_orderManager.getOrderUnit(1001);
        }
        catch (NotFoundException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        double l_dblQuantity = 0;
        double l_dblPriceChange = 0;
        IfoOrderExecutionConditionType l_changeExecCondType = IfoOrderExecutionConditionType.AT_MARKET_CLOSE;
        try
        {
            l_reusableValidations.validateChangeOrderRevLimit(
                l_ifoOrderUnit,
                l_dblQuantity,
                l_dblPriceChange,
                l_changeExecCondType);
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            assertEquals("注文Rev.の値が最大桁数を超過。", l_ex.getErrorInfo().getErrorMessage());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase7()
    {
        final String STR_METHOD_NAME = "testCase7()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();

        l_marketParams.setChangeableType("0");
        l_marketParams.setMarketId(1002);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        OrderUnit l_orderUnit = null;
        try
        {
            l_orderUnit = l_orderManager.getOrderUnit(1001);
        }
        catch (NotFoundException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        double l_dblQuantityAfterChange = 50;
        double l_dblLimitPrice = 150;
        IfoOrderExecutionConditionType l_executionConditionType = IfoOrderExecutionConditionType.NONE;
        String l_strOrderConditionType = "0";
        String l_strOrderCondOperator = "0";
        String l_strStopPriceTyp = "0";
        double l_dblStopPrice = 0;
        double l_dblWStopPrice = 0;
        IfoOrderExecutionConditionType l_wLimitExecCondType = IfoOrderExecutionConditionType.AT_MARKET_CLOSE;
        Date l_datExpriationDate = WEB3DateUtility.getDate("20070208", "yyyyMMdd");
        String l_strExpirationDateType = "1";
        SettleContractEntry[] l_modifiedSettleContractEntries = null;
        try
        {
            l_reusableValidations.validateOrderChangeSpec(
                l_orderUnit,
                l_dblQuantityAfterChange,
                l_dblLimitPrice,
                l_executionConditionType,
                l_strOrderConditionType,
                l_strOrderCondOperator,
                l_strStopPriceTyp,
                l_dblStopPrice,
                l_dblWStopPrice,
                l_wLimitExecCondType,
                l_datExpriationDate,
                l_strExpirationDateType,
                l_modifiedSettleContractEntries);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals("複数項目同時訂正不可（休憩時間帯）。", l_ex.getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase8()
    {
        final String STR_METHOD_NAME = "testCase8()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();

        l_marketParams.setMarketId(1002);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        OrderUnit l_orderUnit = null;
        try
        {
            l_orderUnit = l_orderManager.getOrderUnit(1001);
        }
        catch (NotFoundException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        double l_dblQuantityAfterChange = 100;
        double l_dblLimitPrice = 0;
        IfoOrderExecutionConditionType l_executionConditionType = IfoOrderExecutionConditionType.AT_MARKET_OPEN;
        String l_strOrderConditionType = "0";
        String l_strOrderCondOperator = "0";
        String l_strStopPriceTyp = "0";
        double l_dblStopPrice = 0;
        double l_dblWStopPrice = 0;
        IfoOrderExecutionConditionType l_wLimitExecCondType = IfoOrderExecutionConditionType.AT_MARKET_CLOSE;
        Date l_datExpriationDate = WEB3DateUtility.getDate("20070208", "yyyyMMdd");
        String l_strExpirationDateType = "1";
        SettleContractEntry[] l_modifiedSettleContractEntries = null;
        try
        {
            l_reusableValidations.validateOrderChangeSpec(
                l_orderUnit,
                l_dblQuantityAfterChange,
                l_dblLimitPrice,
                l_executionConditionType,
                l_strOrderConditionType,
                l_strOrderCondOperator,
                l_strStopPriceTyp,
                l_dblStopPrice,
                l_dblWStopPrice,
                l_wLimitExecCondType,
                l_datExpriationDate,
                l_strExpirationDateType,
                l_modifiedSettleContractEntries);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase9()
    {
        final String STR_METHOD_NAME = "testCase9()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(1002);

        l_marketParams.setMarketId(1002);
        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        OrderUnit l_orderUnit = null;
        try
        {
            l_orderUnit = l_orderManager.getOrderUnit(1001);
        }
        catch (NotFoundException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        double l_dblQuantityAfterChange = 0;
        double l_dblLimitPrice = 0;
        IfoOrderExecutionConditionType l_executionConditionType = IfoOrderExecutionConditionType.AT_MARKET_OPEN;
        String l_strOrderConditionType = "0";
        String l_strOrderCondOperator = "0";
        String l_strStopPriceTyp = "0";
        double l_dblStopPrice = 0;
        double l_dblWStopPrice = 0;
        IfoOrderExecutionConditionType l_wLimitExecCondType = IfoOrderExecutionConditionType.AT_MARKET_CLOSE;
        Date l_datExpriationDate = WEB3DateUtility.getDate("20070208", "yyyyMMdd");
        String l_strExpirationDateType = "1";
        SettleContractEntry[] l_modifiedSettleContractEntries = null;
        try
        {
            l_reusableValidations.validateOrderChangeSpec(
                l_orderUnit,
                l_dblQuantityAfterChange,
                l_dblLimitPrice,
                l_executionConditionType,
                l_strOrderConditionType,
                l_strOrderCondOperator,
                l_strStopPriceTyp,
                l_dblStopPrice,
                l_dblWStopPrice,
                l_wLimitExecCondType,
                l_datExpriationDate,
                l_strExpirationDateType,
                l_modifiedSettleContractEntries);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase10()
    {
        final String STR_METHOD_NAME = "testCase10()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();

        l_ifoOrderUnitParams.setExecutedQuantity(20);
        l_marketParams.setMarketId(1002);
        l_marketParams.setChangeableType("0");

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        OrderUnit l_orderUnit = null;
        try
        {
            l_orderUnit = l_orderManager.getOrderUnit(1001);
        }
        catch (NotFoundException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        double l_dblQuantityAfterChange = 50;
        double l_dblLimitPrice = 100;
        IfoOrderExecutionConditionType l_executionConditionType = IfoOrderExecutionConditionType.NONE;
        String l_strOrderConditionType = "0";
        String l_strOrderCondOperator = "0";
        String l_strStopPriceTyp = "0";
        double l_dblStopPrice = 0;
        double l_dblWStopPrice = 0;
        IfoOrderExecutionConditionType l_wLimitExecCondType = IfoOrderExecutionConditionType.AT_MARKET_CLOSE;
        Date l_datExpriationDate = WEB3DateUtility.getDate("20070208", "yyyyMMdd");
        String l_strExpirationDateType = "1";
        SettleContractEntry[] l_modifiedSettleContractEntries = null;
        try
        {
            l_reusableValidations.validateOrderChangeSpec(
                l_orderUnit,
                l_dblQuantityAfterChange,
                l_dblLimitPrice,
                l_executionConditionType,
                l_strOrderConditionType,
                l_strOrderCondOperator,
                l_strStopPriceTyp,
                l_dblStopPrice,
                l_dblWStopPrice,
                l_wLimitExecCondType,
                l_datExpriationDate,
                l_strExpirationDateType,
                l_modifiedSettleContractEntries);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals("複数項目同時訂正不可。", l_ex.getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase11()
    {
        final String STR_METHOD_NAME = "testCase11()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();

        l_ifoOrderUnitParams.setExecutedQuantity(20);
        l_marketParams.setMarketId(1002);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        OrderUnit l_orderUnit = null;
        try
        {
            l_orderUnit = l_orderManager.getOrderUnit(1001);
        }
        catch (NotFoundException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        double l_dblQuantityAfterChange = 50;
        double l_dblLimitPrice = 0;
        IfoOrderExecutionConditionType l_executionConditionType = IfoOrderExecutionConditionType.AT_MARKET_OPEN;
        String l_strOrderConditionType = "0";
        String l_strOrderCondOperator = "0";
        String l_strStopPriceTyp = "0";
        double l_dblStopPrice = 0;
        double l_dblWStopPrice = 0;
        IfoOrderExecutionConditionType l_wLimitExecCondType = IfoOrderExecutionConditionType.AT_MARKET_CLOSE;
        Date l_datExpriationDate = WEB3DateUtility.getDate("20070208", "yyyyMMdd");
        String l_strExpirationDateType = "1";
        SettleContractEntry[] l_modifiedSettleContractEntries = null;
        try
        {
            l_reusableValidations.validateOrderChangeSpec(
                l_orderUnit,
                l_dblQuantityAfterChange,
                l_dblLimitPrice,
                l_executionConditionType,
                l_strOrderConditionType,
                l_strOrderCondOperator,
                l_strStopPriceTyp,
                l_dblStopPrice,
                l_dblWStopPrice,
                l_wLimitExecCondType,
                l_datExpriationDate,
                l_strExpirationDateType,
                l_modifiedSettleContractEntries);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase12()
    {
        final String STR_METHOD_NAME = "testCase12()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();

        l_ifoOrderUnitParams.setExecutedQuantity(20);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        OrderUnit l_orderUnit = null;
        try
        {
            l_orderUnit = l_orderManager.getOrderUnit(1001);
        }
        catch (NotFoundException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        double l_dblQuantityAfterChange = 100;
        double l_dblLimitPrice = 0;
        IfoOrderExecutionConditionType l_executionConditionType = IfoOrderExecutionConditionType.AT_MARKET_OPEN;
        String l_strOrderConditionType = "0";
        String l_strOrderCondOperator = "0";
        String l_strStopPriceTyp = "0";
        double l_dblStopPrice = 0;
        double l_dblWStopPrice = 0;
        IfoOrderExecutionConditionType l_wLimitExecCondType = IfoOrderExecutionConditionType.AT_MARKET_CLOSE;
        Date l_datExpriationDate = WEB3DateUtility.getDate("20070208", "yyyyMMdd");
        String l_strExpirationDateType = "1";
        SettleContractEntry[] l_modifiedSettleContractEntries = null;
        try
        {
            l_reusableValidations.validateOrderChangeSpec(
                l_orderUnit,
                l_dblQuantityAfterChange,
                l_dblLimitPrice,
                l_executionConditionType,
                l_strOrderConditionType,
                l_strOrderCondOperator,
                l_strStopPriceTyp,
                l_dblStopPrice,
                l_dblWStopPrice,
                l_wLimitExecCondType,
                l_datExpriationDate,
                l_strExpirationDateType,
                l_modifiedSettleContractEntries);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase13()
    {
        final String STR_METHOD_NAME = "testCase13()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            WEB3IfoOrderManagerReusableValidations l_validation = 
                new WEB3IfoOrderManagerReusableValidations();
            IfoOrderParams l_ifoParams = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDel(l_ifoParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(33381L);
            l_ifoOrderUnitParams.setAccountId(123333812512238L);
            l_ifoOrderUnitParams.setSubAccountId(33381251223801L);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.OTHER);
            l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            IfoOrderImpl l_ifoOrder = new IfoOrderImpl(l_ifoParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            
            l_validation.validateOrderForCancellation(l_ifoOrder);
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00858,
                l_ex.getValidationResult().getProcessingResult().getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testCase13>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCase14()
    {
        final String STR_METHOD_NAME = "testCase14()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            WEB3IfoOrderManagerReusableValidations l_validation = 
                new WEB3IfoOrderManagerReusableValidations();
            IfoOrderParams l_ifoParams = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDel(l_ifoParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(33381L);
            l_ifoOrderUnitParams.setAccountId(123333812512238L);
            l_ifoOrderUnitParams.setSubAccountId(33381251223801L);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.OTHER);
            l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            IfoOrderImpl l_ifoOrder = new IfoOrderImpl(l_ifoParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
            l_validation.validateOrderForCancellation(l_ifoOrder);
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00155,
                l_ex.getValidationResult().getProcessingResult().getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testCase14>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCase15()
    {
        final String STR_METHOD_NAME = "testCase15()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            WEB3IfoOrderManagerReusableValidations l_validation = 
                new WEB3IfoOrderManagerReusableValidations();
            IfoOrderParams l_ifoParams = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDel(l_ifoParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002L);
            l_ifoOrderUnitParams.setOrderId(33381L);
            l_ifoOrderUnitParams.setAccountId(123333812512238L);
            l_ifoOrderUnitParams.setSubAccountId(33381251223801L);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.OTHER);
            l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setOrderConditionType("1");
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            IfoOrderImpl l_ifoOrder = new IfoOrderImpl(l_ifoParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
            l_validation.validateOrderForCancellation(l_ifoOrder);
        }
        catch (OrderValidationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    

    
    public void testCase16()
    {
        final String STR_METHOD_NAME = "testCase16()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            WEB3IfoOrderManagerReusableValidations l_validation = 
                new WEB3IfoOrderManagerReusableValidations();
            IfoOrderParams l_ifoParams = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDel(l_ifoParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002L);
            l_ifoOrderUnitParams.setOrderId(33381L);
            l_ifoOrderUnitParams.setAccountId(123333812512238L);
            l_ifoOrderUnitParams.setSubAccountId(33381251223801L);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.OTHER);
            l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setOrderConditionType("1");
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
            
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            IfoOrderImpl l_ifoOrder = new IfoOrderImpl(l_ifoParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
            l_validation.validateOrderForCancellation(l_ifoOrder);
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00155,
                l_ex.getValidationResult().getProcessingResult().getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testCase16>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testCase17()
    {
        final String STR_METHOD_NAME = "testCase17()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            WEB3IfoOrderManagerReusableValidations l_validation = 
                new WEB3IfoOrderManagerReusableValidations();
            IfoOrderParams l_ifoParams = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDel(l_ifoParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002L);
            l_ifoOrderUnitParams.setOrderId(33381L);
            l_ifoOrderUnitParams.setAccountId(123333812512238L);
            l_ifoOrderUnitParams.setSubAccountId(33381251223801L);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.OTHER);
            l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setOrderConditionType("1");
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLING);
            
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            IfoOrderImpl l_ifoOrder = new IfoOrderImpl(l_ifoParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
            l_validation.validateOrderForCancellation(l_ifoOrder);
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00155,
                l_ex.getValidationResult().getProcessingResult().getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testCase17>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testCase18()
    {
        final String STR_METHOD_NAME = "testCase18()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            WEB3IfoOrderManagerReusableValidations l_validation = 
                new WEB3IfoOrderManagerReusableValidations();
            IfoOrderParams l_ifoParams = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDel(l_ifoParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002L);
            l_ifoOrderUnitParams.setOrderId(33381L);
            l_ifoOrderUnitParams.setAccountId(123333812512238L);
            l_ifoOrderUnitParams.setSubAccountId(33381251223801L);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.OTHER);
            l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setOrderConditionType("1");
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            IfoOrderImpl l_ifoOrder = new IfoOrderImpl(l_ifoParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
            l_validation.validateOrderForCancellation(l_ifoOrder);
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00155,
                l_ex.getValidationResult().getProcessingResult().getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testCase18>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCase19()
    {
        final String STR_METHOD_NAME = "testCase19()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            WEB3IfoOrderManagerReusableValidations l_validation = 
                new WEB3IfoOrderManagerReusableValidations();
            IfoOrderParams l_ifoParams = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDel(l_ifoParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002L);
            l_ifoOrderUnitParams.setOrderId(33381L);
            l_ifoOrderUnitParams.setAccountId(123333812512238L);
            l_ifoOrderUnitParams.setSubAccountId(33381251223801L);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.OTHER);
            l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setOrderConditionType("1");
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFYING);
            
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            IfoOrderImpl l_ifoOrder = new IfoOrderImpl(l_ifoParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
            l_validation.validateOrderForCancellation(l_ifoOrder);
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00155,
                l_ex.getValidationResult().getProcessingResult().getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testCase19>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCase20()
    {
        final String STR_METHOD_NAME = "testCase20()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            WEB3IfoOrderManagerReusableValidations l_validation = 
                new WEB3IfoOrderManagerReusableValidations();
            IfoOrderParams l_ifoParams = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDel(l_ifoParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002L);
            l_ifoOrderUnitParams.setOrderId(33381L);
            l_ifoOrderUnitParams.setAccountId(123333812512238L);
            l_ifoOrderUnitParams.setSubAccountId(33381251223801L);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.OTHER);
            l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setOrderConditionType("1");
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            IfoOrderImpl l_ifoOrder = new IfoOrderImpl(l_ifoParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
            l_validation.validateOrderForCancellation(l_ifoOrder);
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00155,
                l_ex.getValidationResult().getProcessingResult().getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testCase20>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
                

    
    public void testCase21()
    {
        final String STR_METHOD_NAME = "testCase21()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            WEB3IfoOrderManagerReusableValidations l_validation = 
                new WEB3IfoOrderManagerReusableValidations();
            IfoOrderParams l_ifoParams = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDel(l_ifoParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002L);
            l_ifoOrderUnitParams.setOrderId(33381L);
            l_ifoOrderUnitParams.setAccountId(123333812512238L);
            l_ifoOrderUnitParams.setSubAccountId(33381251223801L);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.OTHER);
            l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setOrderConditionType("1");
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
            
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            IfoOrderImpl l_ifoOrder = new IfoOrderImpl(l_ifoParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
            l_validation.validateOrderForCancellation(l_ifoOrder);
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00032,
                l_ex.getValidationResult().getProcessingResult().getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testCase21>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testCase22()
    {
        final String STR_METHOD_NAME = "testCase22()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            WEB3IfoOrderManagerReusableValidations l_validation = 
                new WEB3IfoOrderManagerReusableValidations();
            IfoOrderParams l_ifoParams = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDel(l_ifoParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002L);
            l_ifoOrderUnitParams.setOrderId(33381L);
            l_ifoOrderUnitParams.setAccountId(123333812512238L);
            l_ifoOrderUnitParams.setSubAccountId(33381251223801L);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.OTHER);
            l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setOrderConditionType("1");
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLING);
            
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            IfoOrderImpl l_ifoOrder = new IfoOrderImpl(l_ifoParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
            l_validation.validateOrderForCancellation(l_ifoOrder);
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00032,
                l_ex.getValidationResult().getProcessingResult().getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testCase22>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCase23()
    {
        final String STR_METHOD_NAME = "testCase23()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            WEB3IfoOrderManagerReusableValidations l_validation = 
                new WEB3IfoOrderManagerReusableValidations();
            IfoOrderParams l_ifoParams = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDel(l_ifoParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002L);
            l_ifoOrderUnitParams.setOrderId(33381L);
            l_ifoOrderUnitParams.setAccountId(123333812512238L);
            l_ifoOrderUnitParams.setSubAccountId(33381251223801L);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.OTHER);
            l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setOrderConditionType("1");
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFYING);
            
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            IfoOrderImpl l_ifoOrder = new IfoOrderImpl(l_ifoParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
            l_validation.validateOrderForCancellation(l_ifoOrder);
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00032,
                l_ex.getValidationResult().getProcessingResult().getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testCase23>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCase24()
    {
        final String STR_METHOD_NAME = "testCase24()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "isMarketNotifyingOrderInBreakTimeZone", new Class[] {IfoOrderUnit.class},
            Boolean.TRUE);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoParams = TestDBUtility.getIfoOrderRow();
        l_ifoParams.setOrderId(1001);
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
        l_ifoOrderUnitParams.setBranchId(33381);
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoParams);
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            l_reusableValidations.validateOrderForCancellation(l_ifoOrderImpl);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00032.getErrorMessage(),
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    public void testCase25()
    {
        final String STR_METHOD_NAME = "testCase25()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "isMarketNotifyingOrderInBreakTimeZone",
                new Class[] {IfoOrderUnit.class},
                Boolean.TRUE);

            WEB3IfoOrderManagerReusableValidations l_validation = 
                new WEB3IfoOrderManagerReusableValidations();
            IfoOrderParams l_ifoParams = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDel(l_ifoParams);
            
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002L);
            l_ifoOrderUnitParams.setOrderId(33381L);
            l_ifoOrderUnitParams.setAccountId(123333812512238L);
            l_ifoOrderUnitParams.setSubAccountId(33381251223801L);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.OTHER);
            l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setOrderConditionType("1");
            
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
            

            
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            IfoOrder l_ifoOrder = new IfoOrderImpl(l_ifoParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);
            
   
            
            l_validation.validateOrderForCancellation(l_ifoOrder);
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00155,
                l_ex.getValidationResult().getProcessingResult().getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testCase25>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testCase26()
    {
        final String STR_METHOD_NAME = "testCase26()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "isMarketNotifyingOrderInBreakTimeZone",
                new Class[] {IfoOrderUnit.class},
                Boolean.TRUE);

            WEB3IfoOrderManagerReusableValidations l_validation = 
                new WEB3IfoOrderManagerReusableValidations();
            IfoOrderParams l_ifoParams = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDel(l_ifoParams);
            
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002L);
            l_ifoOrderUnitParams.setOrderId(33381L);
            l_ifoOrderUnitParams.setAccountId(123333812512238L);
            l_ifoOrderUnitParams.setSubAccountId(33381251223801L);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.OTHER);
            l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setOrderConditionType("1");
            
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLING);
            

            
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            IfoOrder l_ifoOrder = new IfoOrderImpl(l_ifoParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);
            
   
            
            l_validation.validateOrderForCancellation(l_ifoOrder);
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00155,
                l_ex.getValidationResult().getProcessingResult().getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testCase26>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCase27()
    {
        final String STR_METHOD_NAME = "testCase27()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "isMarketNotifyingOrderInBreakTimeZone",
                new Class[] {IfoOrderUnit.class},
                Boolean.TRUE);

            WEB3IfoOrderManagerReusableValidations l_validation = 
                new WEB3IfoOrderManagerReusableValidations();
            IfoOrderParams l_ifoParams = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDel(l_ifoParams);
            
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002L);
            l_ifoOrderUnitParams.setOrderId(33381L);
            l_ifoOrderUnitParams.setAccountId(123333812512238L);
            l_ifoOrderUnitParams.setSubAccountId(33381251223801L);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.OTHER);
            l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setOrderConditionType("1");
            
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            

            
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            IfoOrder l_ifoOrder = new IfoOrderImpl(l_ifoParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);
            
   
            
            l_validation.validateOrderForCancellation(l_ifoOrder);
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00155,
                l_ex.getValidationResult().getProcessingResult().getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testCase27>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testCase28()
    {
        final String STR_METHOD_NAME = "testCase28()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "isMarketNotifyingOrderInBreakTimeZone",
                new Class[] {IfoOrderUnit.class},
                Boolean.TRUE);

            WEB3IfoOrderManagerReusableValidations l_validation = 
                new WEB3IfoOrderManagerReusableValidations();
            IfoOrderParams l_ifoParams = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDel(l_ifoParams);
            
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002L);
            l_ifoOrderUnitParams.setOrderId(33381L);
            l_ifoOrderUnitParams.setAccountId(123333812512238L);
            l_ifoOrderUnitParams.setSubAccountId(33381251223801L);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.OTHER);
            l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setOrderConditionType("1");
            
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFYING);
            

            
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            IfoOrder l_ifoOrder = new IfoOrderImpl(l_ifoParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);
            
   
            
            l_validation.validateOrderForCancellation(l_ifoOrder);
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00155,
                l_ex.getValidationResult().getProcessingResult().getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testCase28>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCase29()
    {
        final String STR_METHOD_NAME = "testCase29()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "isMarketNotifyingOrderInBreakTimeZone",
                new Class[] {IfoOrderUnit.class},
                Boolean.TRUE);

            WEB3IfoOrderManagerReusableValidations l_validation = 
                new WEB3IfoOrderManagerReusableValidations();
            IfoOrderParams l_ifoParams = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDel(l_ifoParams);
            
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002L);
            l_ifoOrderUnitParams.setOrderId(33381L);
            l_ifoOrderUnitParams.setAccountId(123333812512238L);
            l_ifoOrderUnitParams.setSubAccountId(33381251223801L);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.OTHER);
            l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setOrderConditionType("1");
            
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            

            
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            IfoOrder l_ifoOrder = new IfoOrderImpl(l_ifoParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);
            
   
            
            l_validation.validateOrderForCancellation(l_ifoOrder);
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00155,
                l_ex.getValidationResult().getProcessingResult().getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testCase29>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCase30()
    {
        final String STR_METHOD_NAME = "testCase30()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "isMarketNotifyingOrderInBreakTimeZone", new Class[] {IfoOrderUnit.class},
            Boolean.FALSE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotOrderedDelay", new Class[] {IfoOrderUnit.class},
            Boolean.FALSE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderSwitching", new Class[] {IfoOrderUnit.class},
            null);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderUnitParams l_ifoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams1.setOrderUnitId(1002);
        l_ifoOrderUnitParams1.setOrderRequestNumber("000003007");
        IfoOrderUnitParams l_ifoOrderUnitParams2 = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams2.setOrderUnitId(1003);
        l_ifoOrderUnitParams2.setOrderRequestNumber("000003008");
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        l_ifoOrderParams.setOrderId(1001);
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams1);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams2);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            l_reusableValidations.validateOrderForCancellation(l_ifoOrderImpl);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCase31()
    {
        final String STR_METHOD_NAME = "testCase31()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "isMarketNotifyingOrderInBreakTimeZone",
                new Class[] {IfoOrderUnit.class},
                Boolean.TRUE);

            WEB3IfoOrderManagerReusableValidations l_validation = 
                new WEB3IfoOrderManagerReusableValidations();
            IfoOrderParams l_ifoParams = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDel(l_ifoParams);
            
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002L);
            l_ifoOrderUnitParams.setOrderId(33381L);
            l_ifoOrderUnitParams.setAccountId(123333812512238L);
            l_ifoOrderUnitParams.setSubAccountId(33381251223801L);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.OTHER);
            l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setOrderConditionType("1");
            
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
            
            l_ifoOrderUnitParams.setConfirmedQuantity(2.0D);
            
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            IfoOrder l_ifoOrder = new IfoOrderImpl(l_ifoParams);
            
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
            
   
            
            l_validation.validateOrderForCancellation(l_ifoOrder);
        }
        catch (OrderValidationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
     
    
    public void testCase32()
    {
        final String STR_METHOD_NAME = "testCase32()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "isMarketNotifyingOrderInBreakTimeZone",
                new Class[] {IfoOrderUnit.class},
                Boolean.TRUE);

            WEB3GentradeOrderSwitching l_switching = null;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getOrderSwitching",
                new Class[] {IfoOrderUnit.class},
                l_switching);
            
            WEB3IfoOrderManagerReusableValidations l_validation = 
                new WEB3IfoOrderManagerReusableValidations();
            IfoOrderParams l_ifoParams = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDel(l_ifoParams);
            
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002L);
            l_ifoOrderUnitParams.setOrderId(33381L);
            l_ifoOrderUnitParams.setAccountId(123333812512238L);
            l_ifoOrderUnitParams.setSubAccountId(33381251223801L);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.OTHER);
            l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setOrderConditionType("1");
            
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
            
            l_ifoOrderUnitParams.setConfirmedQuantity(2.0D);
            
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            IfoOrder l_ifoOrder = new IfoOrderImpl(l_ifoParams);
            
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
            
   
            
            l_validation.validateOrderForCancellation(l_ifoOrder);
        }
        catch (OrderValidationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    
    public void testCase33()
    {
        final String STR_METHOD_NAME = "testCase33()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
           

            OrderSwitchingParams l_switchParams = TestDBUtility.getOrderSwitchingRow();
            l_switchParams.setValidFlag("0");
            l_switchParams.setInstitutionCode("0D");
            l_switchParams.setProductType(ProductTypeEnum.IFO);
            l_switchParams.setMarketCode("00");
            l_switchParams.setFrontOrderSystemCode("9");
            TestDBUtility.insertWithDel(l_switchParams);
            WEB3GentradeOrderSwitching l_switching = new WEB3GentradeOrderSwitching(l_switchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getOrderSwitching",
                new Class[] {IfoOrderUnit.class},
                l_switching);
            
            WEB3IfoOrderManagerReusableValidations l_validation = 
                new WEB3IfoOrderManagerReusableValidations();
            IfoOrderParams l_ifoParams = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDel(l_ifoParams);
            
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002L);
            l_marketParams.setMarketCode("00");
            l_ifoOrderUnitParams.setOrderId(33381L);
            l_ifoOrderUnitParams.setAccountId(123333812512238L);
            l_ifoOrderUnitParams.setSubAccountId(33381251223801L);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.OTHER);
            l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderUnitParams.setOrderConditionType("1");

            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);

            l_ifoOrderUnitParams.setConfirmedQuantity(2.0D);

            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);

            IfoOrder l_ifoOrder = new IfoOrderImpl(l_ifoParams);

            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);

            l_validation.validateOrderForCancellation(l_ifoOrder);
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00032,
                l_ex.getValidationResult().getProcessingResult().getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testCase33>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testCase34()
    {
        final String STR_METHOD_NAME = "testCase34()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        WEB3GentradeOrderSwitching l_orderSwitching = new WEB3GentradeOrderSwitching(l_orderSwitchingParams);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderSwitching", new Class[] {IfoOrderUnit.class},
            l_orderSwitching);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        l_ifoOrderParams.setOrderId(1001);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            l_reusableValidations.validateOrderForCancellation(l_ifoOrderImpl);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals("パラメータ値不正。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCase35()
    {
        final String STR_METHOD_NAME = "testCase35()";
        log.entering(TEST_START + STR_METHOD_NAME);

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();

        l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = true;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals("注文有効状態がOPEN以外の場合は取消不可です。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase36()
    {
        final String STR_METHOD_NAME = "testCase36()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = true;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00156.getErrorMessage(),
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase37()
    {
        final String STR_METHOD_NAME = "testCase37()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(1002);
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_ifoOrderUnitParams.setOrderConditionType("1");

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = true;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase38()
    {
        final String STR_METHOD_NAME = "testCase38()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = true;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00156.getErrorMessage(),
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase39()
    {
        final String STR_METHOD_NAME = "testCase39()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLING);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = true;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00156.getErrorMessage(),
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase40()
    {
        final String STR_METHOD_NAME = "testCase40()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = true;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00156.getErrorMessage(),
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase41()
    {
        final String STR_METHOD_NAME = "testCase41()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFYING);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = true;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00156.getErrorMessage(),
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase42()
    {
        final String STR_METHOD_NAME = "testCase42()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = true;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00156.getErrorMessage(),
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase43()
    {
        final String STR_METHOD_NAME = "testCase43()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = true;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00032.getErrorMessage(),
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase44()
    {
        final String STR_METHOD_NAME = "testCase44()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLING);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = true;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00032.getErrorMessage(),
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase45()
    {
        final String STR_METHOD_NAME = "testCase45()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFYING);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = true;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00032.getErrorMessage(),
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase46()
    {
        final String STR_METHOD_NAME = "testCase46()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "isMarketNotifyingOrderInBreakTimeZone", new Class[] {IfoOrderUnit.class},
            Boolean.TRUE);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = true;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00032.getErrorMessage(),
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase47()
    {
        final String STR_METHOD_NAME = "testCase47()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = true;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00156.getErrorMessage(),
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase48()
    {
        final String STR_METHOD_NAME = "testCase48()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLING);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = true;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00156.getErrorMessage(),
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase49()
    {
        final String STR_METHOD_NAME = "testCase49()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = true;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00156.getErrorMessage(),
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase50()
    {
        final String STR_METHOD_NAME = "testCase50()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFYING);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = true;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00156.getErrorMessage(),
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase51()
    {
        final String STR_METHOD_NAME = "testCase51()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = true;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00156.getErrorMessage(),
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase52()
    {
        final String STR_METHOD_NAME = "testCase52()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "isMarketNotifyingOrderInBreakTimeZone", new Class[] {IfoOrderUnit.class},
            Boolean.FALSE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotOrderedDelay", new Class[] {IfoOrderUnit.class},
            Boolean.FALSE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderSwitching", new Class[] {IfoOrderUnit.class},
            null);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderUnitParams l_ifoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams1.setOrderUnitId(1002);
        l_ifoOrderUnitParams1.setOrderRequestNumber("000003007");
        IfoOrderUnitParams l_ifoOrderUnitParams2 = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams2.setOrderUnitId(1003);
        l_ifoOrderUnitParams2.setOrderRequestNumber("000003008");
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        l_ifoOrderParams.setOrderId(1001);
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams1);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams2);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = false;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase53()
    {
        final String STR_METHOD_NAME = "testCase53()";
        log.entering(TEST_START + STR_METHOD_NAME);
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        l_orderSwitchingParams.setValidFlag("1");
        WEB3GentradeOrderSwitching l_orderSwitching = new WEB3GentradeOrderSwitching(l_orderSwitchingParams);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderSwitching", new Class[] {IfoOrderUnit.class},
            l_orderSwitching);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(1002L);
        l_marketParams.setMarketCode("00");
        
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");
        
        OrderSwitchingParams l_switchParams = TestDBUtility.getOrderSwitchingRow();
        l_switchParams.setValidFlag("0");
        l_switchParams.setInstitutionCode("0D");
        l_switchParams.setProductType(ProductTypeEnum.IFO);
        l_switchParams.setMarketCode("00");
        l_switchParams.setFrontOrderSystemCode("9");
        l_switchParams.setValidFlag("1");

        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.insertWithDel(l_switchParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = true;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase54()
    {
        final String STR_METHOD_NAME = "testCase54()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderSwitching", new Class[] {IfoOrderUnit.class},
            null);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(1002L);
        l_marketParams.setMarketCode("00");

        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = true;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase55()
    {
        final String STR_METHOD_NAME = "testCase55()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        WEB3GentradeOrderSwitching l_orderSwitching = new WEB3GentradeOrderSwitching(l_orderSwitchingParams);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderSwitching", new Class[] {IfoOrderUnit.class},
            l_orderSwitching);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        l_ifoOrderParams.setOrderId(1001);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = true;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00156.getErrorMessage(),
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase56()
    {
        final String STR_METHOD_NAME = "testCase56()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderSwitching", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotOrderedDelay", new Class[] {IfoOrderUnit.class},
            Boolean.TRUE);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        l_ifoOrderParams.setOrderId(1001);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = false;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00156.getErrorMessage(),
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCase57()
    {
        final String STR_METHOD_NAME = "testCase57()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        WEB3GentradeOrderSwitching l_orderSwitching = new WEB3GentradeOrderSwitching(l_orderSwitchingParams);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderSwitching", new Class[] {IfoOrderUnit.class},
            l_orderSwitching);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        l_ifoOrderParams.setOrderId(1001);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = true;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals("パラメータ値不正。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCase58()
    {
        final String STR_METHOD_NAME = "testCase58()";
        log.entering(TEST_START + STR_METHOD_NAME);


        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
        IfoOrderParams l_ifoParams = TestDBUtility.getIfoOrderRow();
        l_ifoParams.setOrderId(1001);
        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();

        try
        {
            IfoOrder l_ifoOrder = new IfoOrderImpl(l_ifoParams);
            l_reusableValidations.validateOrderForCancellation(l_ifoOrder);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00155.getErrorMessage(),
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCase59()
    {
        final String STR_METHOD_NAME = "testCase59()";
        log.entering(TEST_START + STR_METHOD_NAME);


        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
        l_ifoOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.STOP_LIMIT_PRICE);
        IfoOrderParams l_ifoParams = TestDBUtility.getIfoOrderRow();
        l_ifoParams.setOrderId(1001);
        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();

        try
        {
            IfoOrder l_ifoOrder = new IfoOrderImpl(l_ifoParams);
            l_reusableValidations.validateOrderForCancellation(l_ifoOrder);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCase60()
    {
        final String STR_METHOD_NAME = "testCase60()";
        log.entering(TEST_START + STR_METHOD_NAME);


        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
        

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = true;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00156.getErrorMessage(),
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCase61()
    {
        final String STR_METHOD_NAME = "testCase61()";
        log.entering(TEST_START + STR_METHOD_NAME);


        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.STOP_LIMIT_PRICE);
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
        

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        IfoOrderImpl l_ifoOrderImpl = null;
        try
        {
            l_ifoOrderImpl = new IfoOrderImpl(1001);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        boolean l_blnIsSkipDelayStatusCheck = true;
        try
        {
            l_reusableValidations.validateOrderForChangeability(l_ifoOrderImpl, l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCase62()
    {
        final String STR_METHOD_NAME = "testCase62()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();

        l_ifoOrderUnitParams.setExecutedQuantity(20);
        l_marketParams.setMarketId(1002);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        OrderUnit l_orderUnit = null;
        try
        {
            l_orderUnit = l_orderManager.getOrderUnit(1001);
        }
        catch (NotFoundException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        double l_dblQuantityAfterChange = 50;
        double l_dblLimitPrice = 0;
        IfoOrderExecutionConditionType l_executionConditionType = IfoOrderExecutionConditionType.AT_MARKET_OPEN;
        String l_strOrderConditionType = "0";
        String l_strOrderCondOperator = "0";
        String l_strStopPriceTyp = "0";
        double l_dblStopPrice = 0;
        double l_dblWStopPrice = 0;
        IfoOrderExecutionConditionType l_wLimitExecCondType = IfoOrderExecutionConditionType.AT_MARKET_CLOSE;
        Date l_datExpriationDate = WEB3DateUtility.getDate("20070208", "yyyyMMdd");
        String l_strExpirationDateType = "1";
        SettleContractEntry[] l_modifiedSettleContractEntries = null;
        try
        {
            l_reusableValidations.validateOrderChangeSpec(
                l_orderUnit,
                l_dblQuantityAfterChange,
                l_dblLimitPrice,
                l_executionConditionType,
                l_strOrderConditionType,
                l_strOrderCondOperator,
                l_strStopPriceTyp,
                l_dblStopPrice,
                l_dblWStopPrice,
                l_wLimitExecCondType,
                l_datExpriationDate,
                l_strExpirationDateType,
                l_modifiedSettleContractEntries);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    public void testValidateOrderChangeSpec_Case63()
    {
        final String STR_METHOD_NAME = "testValidateOrderChangeSpec_Case63()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "isEveningSessionOrder",
            new Class[]{
            IfoOrderUnit.class},
            new Boolean(true));
        
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();

        l_ifoOrderUnitParams.setExecutedQuantity(20);
        l_marketParams.setMarketId(1002);

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_marketParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        OrderUnit l_orderUnit = null;
        try
        {
            l_orderUnit = l_orderManager.getOrderUnit(1001);
        }
        catch (NotFoundException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        double l_dblQuantityAfterChange = 50;
        double l_dblLimitPrice = 0;
        IfoOrderExecutionConditionType l_executionConditionType = IfoOrderExecutionConditionType.AT_MARKET_OPEN;
        String l_strOrderConditionType = "0";
        String l_strOrderCondOperator = "0";
        String l_strStopPriceTyp = "0";
        double l_dblStopPrice = 0;
        double l_dblWStopPrice = 0;
        IfoOrderExecutionConditionType l_wLimitExecCondType = IfoOrderExecutionConditionType.AT_MARKET_CLOSE;
        Date l_datExpriationDate = WEB3DateUtility.getDate("20070208", "yyyyMMdd");
        String l_strExpirationDateType = "1";
        SettleContractEntry[] l_modifiedSettleContractEntries = null;
        try
        {
            l_reusableValidations.validateOrderChangeSpec(
                l_orderUnit,
                l_dblQuantityAfterChange,
                l_dblLimitPrice,
                l_executionConditionType,
                l_strOrderConditionType,
                l_strOrderCondOperator,
                l_strStopPriceTyp,
                l_dblStopPrice,
                l_dblWStopPrice,
                l_wLimitExecCondType,
                l_datExpriationDate,
                l_strExpirationDateType,
                l_modifiedSettleContractEntries);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02102, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateEveningSessionOrderPossibleHandling_case1()
    {
        final String STR_METHOD_NAME = "testValidateEveningSessionOrderPossibleHandling_case1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        
        try 
        {
            l_reusableValidations.validateEveningSessionOrderPossibleHandling(
                false, "1", 1, null);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        
    }
    public void testValidateEveningSessionOrderPossibleHandling_case2()
    {
        final String STR_METHOD_NAME = "testValidateEveningSessionOrderPossibleHandling_case2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        
        try 
        {
            l_reusableValidations.validateEveningSessionOrderPossibleHandling(
                false, "1", 0, new Long(1));
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        } 
    }
    public void testValidateEveningSessionOrderPossibleHandling_case3()
    {
        final String STR_METHOD_NAME = "testValidateEveningSessionOrderPossibleHandling_case3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        
        try 
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP, BooleanEnum.TRUE);
            l_reusableValidations.validateEveningSessionOrderPossibleHandling(
                false, "1", 0, new Long(1));
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        } 
    }
    public void testValidateEveningSessionOrderPossibleHandling_case4()
    {
        final String STR_METHOD_NAME = "testValidateEveningSessionOrderPossibleHandling_case4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        
        try 
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP, BooleanEnum.FALSE);
            l_reusableValidations.validateEveningSessionOrderPossibleHandling(
                false, "1", 0, new Long(1));
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        } 
    }
    public void testValidateEveningSessionOrderPossibleHandling_case5()
    {
        final String STR_METHOD_NAME = "testValidateEveningSessionOrderPossibleHandling_case5()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        
        try 
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP, BooleanEnum.FALSE);
            l_reusableValidations.validateEveningSessionOrderPossibleHandling(
                false, "3", 0, null);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02816, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        } 
    }
    public void testValidateEveningSessionOrderPossibleHandling_case6()
    {
        final String STR_METHOD_NAME = "testValidateEveningSessionOrderPossibleHandling_case6()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        
        try 
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP, BooleanEnum.FALSE);
            
            this.setExpectedDate(new Date(), "1");
            l_reusableValidations.validateEveningSessionOrderPossibleHandling(
                true, "3", 0, null);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02816, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        } 
    }
    
    /**
     * 予約注文訂正<BR>
     * 　@　@　@　@　@ThreadLocalSystemAttributesRegistry.getAttribute()にて <BR>
     * 　@　@　@　@　@予約注文訂正フラグを取得した値 == TRUE の場合。 <BR>
     * 　@　@　@　@　@-ThreadLocalSystemAttributesRegistry.getAttribute( <BR>
     * 　@　@　@　@　@　@　@WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER) <BR>
     * 何もせずにreturnする。<BR>
     */
    public void testValidateEveningSessionOrderPossibleHandling_case7()
    {
        final String STR_METHOD_NAME = "testValidateEveningSessionOrderPossibleHandling_case7()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        
        try 
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER, BooleanEnum.TRUE);
            l_reusableValidations.validateEveningSessionOrderPossibleHandling(
                false, "1", 0, null);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        } 
    }
    
    public void testvalidateEveningSessionLastTradingDate_case1()
    {
        final String STR_METHOD_NAME = "testvalidateEveningSessionLastTradingDate_case1()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        
        try 
        {
            l_reusableValidations.validateEveningSessionLastTradingDate(null, "1",null);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        } 
    }
    
    public void testvalidateEveningSessionLastTradingDate_case2()
    {
        final String STR_METHOD_NAME = "testvalidateEveningSessionLastTradingDate_case2()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        
        try 
        {
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006160060009L);
            l_IfoTradedProductParams.setTradedProductId(330304148080002L);
            l_IfoTradedProductParams.setMarketId(3306L);
            l_IfoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080002L);
            l_tradedProductParams.setProductId(1006160060009L);
            l_tradedProductParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080002L);
            l_ifoTradedProductUpdqParams.setMarketId(3306L);
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,20,10,12);
            
            Date date = ca.getTime();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {},
                    new Timestamp(date.getTime()));
            
            l_ifoTradedProductUpdqParams.setValidForBizDate("20070620");
            l_ifoTradedProductUpdqParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_marketParams);
            WEB3IfoTradedProductImpl l_ifoTradedProduct = new WEB3IfoTradedProductImpl(l_tradedProductParams);
            setExpectedDate(new Date(), "2");
            l_reusableValidations.validateEveningSessionLastTradingDate(l_ifoTradedProduct, "1",null);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testvalidateEveningSessionLastTradingDate_case3()
    {
        final String STR_METHOD_NAME = "testvalidateEveningSessionLastTradingDate_case3()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        
        try 
        {
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006160060009L);
            l_IfoTradedProductParams.setTradedProductId(330304148080002L);
            l_IfoTradedProductParams.setMarketId(3306L);
            l_IfoTradedProductParams.setValidForBizDate(null);
            l_IfoTradedProductParams.setUnlistedDate(WEB3DateUtility.getDate("20040708100000","yyyyMMddHHmmss"));
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080002L);
            l_tradedProductParams.setProductId(1006160060009L);
            l_tradedProductParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080002L);
            l_ifoTradedProductUpdqParams.setMarketId(3306L);
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,20,10,12);
            
            Date date = ca.getTime();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {},
                    new Timestamp(date.getTime()));
            
            l_ifoTradedProductUpdqParams.setValidForBizDate("20070620");
            l_ifoTradedProductUpdqParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_marketParams);
            WEB3IfoTradedProductImpl l_ifoTradedProduct = new WEB3IfoTradedProductImpl(l_tradedProductParams);
            setExpectedDate(new Date(), "1");
            l_reusableValidations.validateEveningSessionLastTradingDate(l_ifoTradedProduct, "1",null);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    
    public void testvalidateEveningSessionLastTradingDate_case4()
    {
        final String STR_METHOD_NAME = "testvalidateEveningSessionLastTradingDate_case4()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        
        try 
        {
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006160060009L);
            l_IfoTradedProductParams.setTradedProductId(330304148080002L);
            l_IfoTradedProductParams.setMarketId(3306L);
            l_IfoTradedProductParams.setValidForBizDate(null);
            l_IfoTradedProductParams.setUnlistedDate(WEB3DateUtility.getDate("20040708100000","yyyyMMddHHmmss"));
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080002L);
            l_tradedProductParams.setProductId(1006160060009L);
            l_tradedProductParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080002L);
            l_ifoTradedProductUpdqParams.setMarketId(3306L);
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,20,10,12);
            
            Date date = ca.getTime();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {},
                    new Timestamp(date.getTime()));
            
            l_ifoTradedProductUpdqParams.setValidForBizDate("20070620");
            l_ifoTradedProductUpdqParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_marketParams);
            WEB3IfoTradedProductImpl l_ifoTradedProduct = new WEB3IfoTradedProductImpl(l_tradedProductParams);
            setExpectedDate(new Date(), "1");
            //注文期限区分 : 当日限り
            l_reusableValidations.validateEveningSessionLastTradingDate(l_ifoTradedProduct, "2",null);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testvalidateEveningSessionLastTradingDate_case5()
    {
        final String STR_METHOD_NAME = "testvalidateEveningSessionLastTradingDate_case5()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        Date l_dat1 = WEB3DateUtility.getDate("20070619", "yyyyMMdd");
        Date l_preDat1 = WEB3DateUtility.getDate("20070618", "yyyyMMdd");
        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        
        try 
        {
            
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006160060009L);
            l_IfoTradedProductParams.setTradedProductId(330304148080002L);
            l_IfoTradedProductParams.setMarketId(3306L);
            l_IfoTradedProductParams.setUnlistedDate(l_dat1);
            l_IfoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080002L);
            l_tradedProductParams.setProductId(1006160060009L);
            l_tradedProductParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080002L);
            l_ifoTradedProductUpdqParams.setMarketId(3306L);
            l_ifoTradedProductUpdqParams.setUnlistedDate(l_dat1);
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,20,10,12);
            
            Date date = ca.getTime();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {},
                    new Timestamp(date.getTime()));
            
            l_ifoTradedProductUpdqParams.setValidForBizDate("20070620");
            l_ifoTradedProductUpdqParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3IfoTradedProductImpl l_ifoTradedProduct = new WEB3IfoTradedProductImpl(l_tradedProductParams);
            setExpectedDate(l_preDat1, "1");
            
            //注文期限区分 : 当日限り
            l_reusableValidations.validateEveningSessionLastTradingDate(l_ifoTradedProduct, "2",null);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00145, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testvalidateEveningSessionLastTradingDate_case6()
    {
        final String STR_METHOD_NAME = "testvalidateEveningSessionLastTradingDate_case6()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        Date l_dat1 = WEB3DateUtility.getDate("20070619", "yyyyMMdd");
        Date l_preDat1 = WEB3DateUtility.getDate("20070618", "yyyyMMdd");
        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        
        try 
        {
            
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006160060009L);
            l_IfoTradedProductParams.setTradedProductId(330304148080002L);
            l_IfoTradedProductParams.setMarketId(3306L);
            l_IfoTradedProductParams.setValidForBizDate(null);
            //l_IfoTradedProductParams.setUnlistedDate(l_dat1);
            l_IfoTradedProductParams.setLastTradingDate(l_preDat1);
            l_IfoTradedProductParams.setUnlistedDate(WEB3DateUtility.getDate("20040708100000","yyyyMMddHHmmss"));
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080002L);
            l_tradedProductParams.setProductId(1006160060009L);
            l_tradedProductParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080002L);
            l_ifoTradedProductUpdqParams.setMarketId(3306L);
            //l_ifoTradedProductUpdqParams.setUnlistedDate(l_dat1);
            l_ifoTradedProductUpdqParams.setLastTradingDate(l_preDat1);
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,20,10,12);
            
            Date date = ca.getTime();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {},
                    new Timestamp(date.getTime()));
            
            l_ifoTradedProductUpdqParams.setValidForBizDate("20070620");
            l_ifoTradedProductUpdqParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3IfoTradedProductImpl l_ifoTradedProduct = new WEB3IfoTradedProductImpl(l_tradedProductParams);
            setExpectedDate(l_preDat1, "1");
            
            //注文期限区分 : 当日限り
            l_reusableValidations.validateEveningSessionLastTradingDate(l_ifoTradedProduct, "2",null);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00145, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateOrderCond()
    {
        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        try
        {
            Date l_dat1 = WEB3DateUtility.getDate("20070619", "yyyyMMdd");
            Date l_preDat1 = WEB3DateUtility.getDate("20070618", "yyyyMMdd");
            
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060009L);
            l_ifoProductParams.setFutureOptionDiv("0");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006160060009L);
            l_IfoTradedProductParams.setTradedProductId(330304148080002L);
            l_IfoTradedProductParams.setMarketId(3306L);
            l_IfoTradedProductParams.setValidForBizDate(null);
            //l_IfoTradedProductParams.setUnlistedDate(l_dat1);
            l_IfoTradedProductParams.setUnlistedDate(WEB3DateUtility.getDate("20040708100000","yyyyMMddHHmmss"));
            l_IfoTradedProductParams.setLastTradingDate(l_preDat1);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080002L);
            l_tradedProductParams.setProductId(1006160060009L);
            l_tradedProductParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080002L);
            l_ifoTradedProductUpdqParams.setMarketId(3306L);
            //l_ifoTradedProductUpdqParams.setUnlistedDate(l_dat1);
            l_ifoTradedProductUpdqParams.setLastTradingDate(l_preDat1);
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,20,10,12);
            
            Date date = ca.getTime();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getSystemTimestamp",
                    new Class[] {},
                    new Timestamp(date.getTime()));
            
            l_ifoTradedProductUpdqParams.setValidForBizDate("20070620");
            l_ifoTradedProductUpdqParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3IfoTradedProductImpl l_ifoTradedProduct = new WEB3IfoTradedProductImpl(l_tradedProductParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);
            l_reusableValidations.validateOrderCond(
                l_subAccount,
                1001L,
                false,
                l_ifoTradedProduct,
                false,
                false,
                l_dat1,
                l_dat1,
                "0",
                IfoOrderExecutionConditionType.MARKET_PRICE,
                "1",
                new Long(1002));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testValidateOrderCond_case0001()
    {
        WEB3IfoOrderManagerReusableValidations l_reusableValidations = new WEB3IfoOrderManagerReusableValidations();
        try
        {
            Date l_dat1 = WEB3DateUtility.getDate("20070619", "yyyyMMdd");
            Date l_preDat1 = WEB3DateUtility.getDate("20070618", "yyyyMMdd");
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setCarriedOrder("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060009L);
            l_ifoProductParams.setFutureOptionDiv("0");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006160060009L);
            l_IfoTradedProductParams.setTradedProductId(330304148080002L);
            l_IfoTradedProductParams.setMarketId(3306L);
            //l_IfoTradedProductParams.setUnlistedDate(l_dat1);
            l_IfoTradedProductParams.setLastTradingDate(l_preDat1);
            l_IfoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080002L);
            l_tradedProductParams.setProductId(1006160060009L);
            l_tradedProductParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080002L);
            l_ifoTradedProductUpdqParams.setMarketId(3306L);
            //l_ifoTradedProductUpdqParams.setUnlistedDate(l_dat1);
            l_ifoTradedProductUpdqParams.setLastTradingDate(l_preDat1);
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,20,10,12);
            
            Date date = ca.getTime();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getSystemTimestamp",
                    new Class[] {},
                    new Timestamp(date.getTime()));
            
            l_ifoTradedProductUpdqParams.setValidForBizDate("20070620");
            l_ifoTradedProductUpdqParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3IfoTradedProductImpl l_ifoTradedProduct = new WEB3IfoTradedProductImpl(l_tradedProductParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);
            l_reusableValidations.validateOrderCond(
                l_subAccount,
                1001L,
                false,
                l_ifoTradedProduct,
                false,
                false,
                l_dat1,
                l_dat1,
                "0",
                IfoOrderExecutionConditionType.MARKET_PRICE,
                "2",
                new Long(1002));
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00151, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testCalcBasePriceDeregPriceRange001()
    {
        String STR_METHOD_NAME = "testCalcBasePriceDeregPriceRange001";
        log.entering(STR_METHOD_NAME);
        
        double l_dblBasePrice = -1.0;
        double l_dblTickValue = 100.20;
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations =
            new WEB3IfoOrderManagerReusableValidations();
        try
        {
            double l_dbleReturn =
                l_ifoOrderManagerReusableValidations.calcBasePriceDeregPriceRange(l_dblBasePrice, l_dblTickValue);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCalcBasePriceDeregPriceRange002()
    {
        String STR_METHOD_NAME = "testCalcBasePriceDeregPriceRange002";
        log.entering(STR_METHOD_NAME);
        
        double l_dblBasePrice = 100.052;
        double l_dblTickValue = 0;
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations =
            new WEB3IfoOrderManagerReusableValidations();
        try
        {
            double l_dbleReturn =
                l_ifoOrderManagerReusableValidations.calcBasePriceDeregPriceRange(l_dblBasePrice, l_dblTickValue);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCalcBasePriceDeregPriceRange003()
    {
        String STR_METHOD_NAME = "testCalcBasePriceDeregPriceRange003";
        log.entering(STR_METHOD_NAME);
        
        double l_dblBasePrice = 100.30;
        double l_dblTickValue = -1.0;
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations =
            new WEB3IfoOrderManagerReusableValidations();
        try
        {
            double l_dbleReturn =
                l_ifoOrderManagerReusableValidations.calcBasePriceDeregPriceRange(l_dblBasePrice, l_dblTickValue);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testCalcBasePriceDeregPriceRange004()
    {
        String STR_METHOD_NAME = "testCalcBasePriceDeregPriceRange004";
        log.entering(STR_METHOD_NAME);
        
        double l_dblBasePrice = 102.5;
        double l_dblTickValue = 8.1;
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations =
            new WEB3IfoOrderManagerReusableValidations();
        try
        {
            double l_dblReturn =
                l_ifoOrderManagerReusableValidations.calcBasePriceDeregPriceRange(l_dblBasePrice, l_dblTickValue);
            assertEquals(105.3, l_dblReturn, 0);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCalcBasePriceDeregPriceRange005()
    {
        String STR_METHOD_NAME = "testCalcBasePriceDeregPriceRange005";
        log.entering(STR_METHOD_NAME);
        
        double l_dblBasePrice = 100.0;
        double l_dblTickValue = 8.0;
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations =
            new WEB3IfoOrderManagerReusableValidations();
        try
        {
            double l_dblReturn =
                l_ifoOrderManagerReusableValidations.calcBasePriceDeregPriceRange(l_dblBasePrice, l_dblTickValue);
            assertEquals(104, l_dblReturn, 0);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCalcBasePriceDeregPriceRange006()
    {
        String STR_METHOD_NAME = "testCalcBasePriceDeregPriceRange006";
        log.entering(STR_METHOD_NAME);
        
        double l_dblBasePrice = 90.3;
        double l_dblTickValue = 10;
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations =
            new WEB3IfoOrderManagerReusableValidations();
        try
        {
            double l_dblReturn =
                l_ifoOrderManagerReusableValidations.calcBasePriceDeregPriceRange(l_dblBasePrice, l_dblTickValue);
            assertEquals(90, l_dblReturn, 0);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateOrderUnitPrice001()
    {
        String STR_METHOD_NAME = "testValidateOrderUnitPrice001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations =
                new WEB3IfoOrderManagerReusableValidations();
            double l_dblLimitPrice = 100;
            
            Date l_preDat1 = WEB3DateUtility.getDate("20070617", "yyyyMMdd");
            
            TestDBUtility.deleteAll(IfoLimitPriceRangeMasterRow.TYPE);
            IfoLimitPriceRangeMasterParams l_ifoLimitPriceRangeMasterParams =
                new IfoLimitPriceRangeMasterParams();
            l_ifoLimitPriceRangeMasterParams.setCapPrice(2000);
            l_ifoLimitPriceRangeMasterParams.setLowPrice(1);
            l_ifoLimitPriceRangeMasterParams.setFutureOptionDiv("1");
            l_ifoLimitPriceRangeMasterParams.setTargetProductCode("0005");
            l_ifoLimitPriceRangeMasterParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ifoLimitPriceRangeMasterParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_ifoLimitPriceRangeMasterParams.setRange(100);
            TestDBUtility.insertWithDel(l_ifoLimitPriceRangeMasterParams);
            
            TestDBUtility.deleteAll(IfoTickValuesMasterRow.TYPE);
            IfoTickValuesMasterParams l_ifoTickValuesMasterParams = new IfoTickValuesMasterParams();
            l_ifoTickValuesMasterParams.setFutureOptionDiv("1");
            l_ifoTickValuesMasterParams.setTargetProductCode("0005");
            l_ifoTickValuesMasterParams.setLowPrice(1);
            l_ifoTickValuesMasterParams.setCapPrice(2000);
            l_ifoTickValuesMasterParams.setTickValue(10);
            l_ifoTickValuesMasterParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ifoTickValuesMasterParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ifoTickValuesMasterParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060009L);
            l_ifoProductParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006160060009L);
            l_IfoTradedProductParams.setTradedProductId(330304148080002L);
            l_IfoTradedProductParams.setMarketId(3306L);
            l_IfoTradedProductParams.setValidForBizDate("20070617");
            l_IfoTradedProductParams.setTargetSpotPrice(100);
            l_IfoTradedProductParams.setLiquidationPrice(200);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080002L);
            l_tradedProductParams.setProductId(1006160060009L);
            l_tradedProductParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080002L);
            l_ifoTradedProductUpdqParams.setMarketId(3306L);
            l_ifoTradedProductUpdqParams.setLastTradingDate(l_preDat1);
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,20,10,12);
            
            Date date = ca.getTime();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {},
                    new Timestamp(date.getTime()));
            
            l_ifoTradedProductUpdqParams.setValidForBizDate("20070618");
            l_ifoTradedProductUpdqParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_marketParams);        
            
            setExpectedDate(l_preDat1, "1");
            
            WEB3IfoTradedProductImpl l_ifoTradedProduct = new WEB3IfoTradedProductImpl(330304148080002L);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);
            l_ifoOrderManagerReusableValidations.validateOrderUnitPrice(
                l_dblLimitPrice, l_ifoTradedProduct,l_subAccount);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateOrderUnitPrice002()
    {
        String STR_METHOD_NAME = "testValidateOrderUnitPrice002";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations =
                new WEB3IfoOrderManagerReusableValidations();
            double l_dblLimitPrice = 100;
            
            Date l_preDat1 = WEB3DateUtility.getDate("20070617", "yyyyMMdd");
            
            TestDBUtility.deleteAll(IfoLimitPriceRangeMasterRow.TYPE);
            IfoLimitPriceRangeMasterParams l_ifoLimitPriceRangeMasterParams =
                new IfoLimitPriceRangeMasterParams();
            l_ifoLimitPriceRangeMasterParams.setCapPrice(2000);
            l_ifoLimitPriceRangeMasterParams.setLowPrice(1);
            l_ifoLimitPriceRangeMasterParams.setFutureOptionDiv("2");
            l_ifoLimitPriceRangeMasterParams.setTargetProductCode("0005");
            l_ifoLimitPriceRangeMasterParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ifoLimitPriceRangeMasterParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_ifoLimitPriceRangeMasterParams.setRange(100);
            TestDBUtility.insertWithDel(l_ifoLimitPriceRangeMasterParams);
            
            TestDBUtility.deleteAll(IfoTickValuesMasterRow.TYPE);
            IfoTickValuesMasterParams l_ifoTickValuesMasterParams = new IfoTickValuesMasterParams();
            l_ifoTickValuesMasterParams.setFutureOptionDiv("2");
            l_ifoTickValuesMasterParams.setTargetProductCode("0005");
            l_ifoTickValuesMasterParams.setLowPrice(1);
            l_ifoTickValuesMasterParams.setCapPrice(2000);
            l_ifoTickValuesMasterParams.setTickValue(10);
            l_ifoTickValuesMasterParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ifoTickValuesMasterParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ifoTickValuesMasterParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060009L);
            l_ifoProductParams.setFutureOptionDiv("2");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006160060009L);
            l_IfoTradedProductParams.setTradedProductId(330304148080002L);
            l_IfoTradedProductParams.setMarketId(3306L);
            l_IfoTradedProductParams.setValidForBizDate("20070617");
            l_IfoTradedProductParams.setTargetSpotPrice(400);
            l_IfoTradedProductParams.setLiquidationPrice(200);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080002L);
            l_tradedProductParams.setProductId(1006160060009L);
            l_tradedProductParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080002L);
            l_ifoTradedProductUpdqParams.setMarketId(3306L);
            l_ifoTradedProductUpdqParams.setLastTradingDate(l_preDat1);
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,20,10,12);
            
            Date date = ca.getTime();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {},
                    new Timestamp(date.getTime()));
            
            l_ifoTradedProductUpdqParams.setValidForBizDate("20070618");
            l_ifoTradedProductUpdqParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_marketParams);        
            
            setExpectedDate(l_preDat1, "1");
            
            WEB3IfoTradedProductImpl l_ifoTradedProduct = new WEB3IfoTradedProductImpl(330304148080002L);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);
            l_ifoOrderManagerReusableValidations.validateOrderUnitPrice(
                l_dblLimitPrice, l_ifoTradedProduct,l_subAccount);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
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
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
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
                "web3.attributes.basetimestampfororderbizdate", new Timestamp(l_expectDate.getTime()));   
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCalcStopHighPrice_C0001()
    {
        final String STR_METHOD_NAME = "testCalcStopHighPrice_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoProductManagerImpl",
            "getTickValue", 
            new Class[] {WEB3IfoProductImpl.class, double.class},
            new Double(7.6D));

        WEB3IfoOrderManagerReusableValidations l_reusableValidations =
            new WEB3IfoOrderManagerReusableValidations();
        
        try
        {
            //データベースへデータをインサート
            IfoProductParams l_IfoProductParams = new IfoProductParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_IfoProductParams.getRowType());
            l_IfoProductParams = TestDBUtility.getIfoProductRow();
            TestDBUtility.insertWithDel(l_IfoProductParams);

            ProductParams l_productParams = new ProductParams();
            l_processor.doDeleteAllQuery(l_productParams.getRowType());
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);

            TradedProductParams l_tradedProductParams = new TradedProductParams();
            l_processor.doDeleteAllQuery(l_tradedProductParams.getRowType());
            l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //実際メソッドをコール
            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(1006160060005L);
            double l_dblResult =
                l_reusableValidations.calcStopHighPrice(2.2d, 4.4d, l_ifoProduct);

            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getTickValue", 
                new Class[] {WEB3IfoProductImpl.class, double.class});
            
            assertEquals(new Double(6.6d), l_paramsValue.getFirstCalled()[1]);
            //比較
            assertEquals(7.6, l_dblResult, 0);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcStopHighPrice_C0002()
    {
        final String STR_METHOD_NAME = "testCalcStopHighPrice_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoProductManagerImpl",
            "getTickValue", 
            new Class[] {WEB3IfoProductImpl.class, double.class},
            new Double(13.2D));

        WEB3IfoOrderManagerReusableValidations l_reusableValidations =
            new WEB3IfoOrderManagerReusableValidations();
        
        try
        {
            //データベースへデータをインサート
            IfoProductParams l_IfoProductParams = new IfoProductParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_IfoProductParams.getRowType());
            l_IfoProductParams = TestDBUtility.getIfoProductRow();
            TestDBUtility.insertWithDel(l_IfoProductParams);

            ProductParams l_productParams = new ProductParams();
            l_processor.doDeleteAllQuery(l_productParams.getRowType());
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);

            TradedProductParams l_tradedProductParams = new TradedProductParams();
            l_processor.doDeleteAllQuery(l_tradedProductParams.getRowType());
            l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //実際メソッドをコール
            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(1006160060005L);
            double l_dblResult =
                l_reusableValidations.calcStopHighPrice(2.2d, 4.4d, l_ifoProduct);

            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getTickValue", 
                new Class[] {WEB3IfoProductImpl.class, double.class});
            
            assertEquals(new Double(6.6d), l_paramsValue.getFirstCalled()[1]);
            //比較
            assertEquals(13.2, l_dblResult, 0);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcStopHighPrice_C0003()
    {
        final String STR_METHOD_NAME = "testCalcStopHighPrice_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoProductManagerImpl",
            "getTickValue", 
            new Class[] {WEB3IfoProductImpl.class, double.class},
            new Double(3.1D));

        WEB3IfoOrderManagerReusableValidations l_reusableValidations =
            new WEB3IfoOrderManagerReusableValidations();
        
        try
        {
            //データベースへデータをインサート
            IfoProductParams l_IfoProductParams = new IfoProductParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_IfoProductParams.getRowType());
            l_IfoProductParams = TestDBUtility.getIfoProductRow();
            TestDBUtility.insertWithDel(l_IfoProductParams);

            ProductParams l_productParams = new ProductParams();
            l_processor.doDeleteAllQuery(l_productParams.getRowType());
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);

            TradedProductParams l_tradedProductParams = new TradedProductParams();
            l_processor.doDeleteAllQuery(l_tradedProductParams.getRowType());
            l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //実際メソッドをコール
            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(1006160060005L);
            double l_dblResult =
                l_reusableValidations.calcStopHighPrice(2.2d, 4.4d, l_ifoProduct);

            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getTickValue", 
                new Class[] {WEB3IfoProductImpl.class, double.class});
            
            assertEquals(new Double(6.6d), l_paramsValue.getFirstCalled()[1]);
            //比較
            assertEquals(6.2, l_dblResult, 0);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcBasePrice_C0001()
    {
        final String STR_METHOD_NAME = "testCalcBasePrice_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoOrderManagerReusableValidations l_reusableValidations =
            new WEB3IfoOrderManagerReusableValidations();
        
        try
        {
            double l_dblResult =
                l_reusableValidations.calcBasePrice(6.6d, 12.0d);

            assertEquals(12, l_dblResult, 0);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcBasePrice_C0002()
    {
        final String STR_METHOD_NAME = "testCalcBasePrice_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoOrderManagerReusableValidations l_reusableValidations =
            new WEB3IfoOrderManagerReusableValidations();
        
        try
        {
            double l_dblResult =
                l_reusableValidations.calcBasePrice(19.8d, 13.2d);

            assertEquals(26.4, l_dblResult, 0);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcBasePrice_C0003()
    {
        final String STR_METHOD_NAME = "testCalcBasePrice_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoOrderManagerReusableValidations l_reusableValidations =
            new WEB3IfoOrderManagerReusableValidations();
        
        try
        {
            double l_dblResult =
                l_reusableValidations.calcBasePrice(14.0d, 13.2d);

            assertEquals(13.2, l_dblResult, 0);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateTickValueDef_C0001()
    {
        final String STR_METHOD_NAME = "testValidateTickValueDef_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoOrderManagerReusableValidations l_reusableValidations =
            new WEB3IfoOrderManagerReusableValidations();
        
        try
        {
            l_reusableValidations.validateTickValueDef(6.6d, 0.0d);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateTickValueDef_C0002()
    {
        final String STR_METHOD_NAME = "testValidateTickValueDef_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoOrderManagerReusableValidations l_reusableValidations =
            new WEB3IfoOrderManagerReusableValidations();
        
        try
        {
            l_reusableValidations.validateTickValueDef(6.6d, 6.6d);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateTickValueDef_C0003()
    {
        final String STR_METHOD_NAME = "testValidateTickValueDef_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoOrderManagerReusableValidations l_reusableValidations =
            new WEB3IfoOrderManagerReusableValidations();
        
        try
        {
            l_reusableValidations.validateTickValueDef(6.6d, 13.2d);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateTickValueDef_C0004()
    {
        final String STR_METHOD_NAME = "testValidateTickValueDef_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoOrderManagerReusableValidations l_reusableValidations =
            new WEB3IfoOrderManagerReusableValidations();
        
        try
        {
            l_reusableValidations.validateTickValueDef(6.6d, 10.0d);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00148, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
        
    //validate発注条件単価
    //throw 発注条件単価入力エラー（乖離値が指定の倍率未満）。
    public void testValidateOrderCondPrice_C0001()
    {
        final String STR_METHOD_NAME = "testValidateOrderCondPrice_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoOrderManagerReusableValidations l_reusableValidations =
            new WEB3IfoOrderManagerReusableValidations();
        
        try
        {
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();      
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setNameSerialNo(2);

            l_branchPreferencesParams.setName("triggerorder.wlimitorder.check.order.cond.price");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            l_branchPreferencesParams.setName("triggerorder.wlimitorder.divergencerate");
            l_branchPreferencesParams.setValue("1.1");//
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006160060009L);
            l_IfoTradedProductParams.setTradedProductId(330304148080002L);
            l_IfoTradedProductParams.setMarketId(3306L);
            l_IfoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080002L);
            l_tradedProductParams.setProductId(1006160060009L);
            l_tradedProductParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getTickValue",
                new Class[] {WEB3IfoProductImpl.class, double.class},
                new Double(7.0));//
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getCurrentPrice",
                new Class[] {WEB3IfoTradedProductImpl.class},
                new Double(0.0));//
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381L);
            double l_dblLimitPrice = 7.9;//
            double l_dblOrderCondPrice = 1.3;//
            WEB3IfoTradedProductImpl l_ifoTradedProduct =
                new WEB3IfoTradedProductImpl(l_tradedProductParams);
            boolean l_blnIsBuyToOpenOrder = true;
            
            l_reusableValidations.validateOrderCondPrice(
                l_branch,
                l_dblLimitPrice,
                l_dblOrderCondPrice,
                l_ifoTradedProduct,
                l_blnIsBuyToOpenOrder);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02492, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //validate発注条件単価
    //throw 発注条件単価／注文単価入力エラー（時価の挟み込み不正）。
    public void testValidateOrderCondPrice_C0002()
    {
        final String STR_METHOD_NAME = "testValidateOrderCondPrice_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoOrderManagerReusableValidations l_reusableValidations =
            new WEB3IfoOrderManagerReusableValidations();
        
        try
        {
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();      
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setNameSerialNo(2);

            l_branchPreferencesParams.setName("triggerorder.wlimitorder.check.order.cond.price");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            l_branchPreferencesParams.setName("triggerorder.wlimitorder.divergencerate");
            l_branchPreferencesParams.setValue("1.1");//
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006160060009L);
            l_IfoTradedProductParams.setTradedProductId(330304148080002L);
            l_IfoTradedProductParams.setMarketId(3306L);
            l_IfoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080002L);
            l_tradedProductParams.setProductId(1006160060009L);
            l_tradedProductParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getTickValue",
                new Class[] {WEB3IfoProductImpl.class, double.class},
                new Double(6.0));//
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getCurrentPrice",
                new Class[] {WEB3IfoTradedProductImpl.class},
                new Double(7.9));//
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381L);
            double l_dblLimitPrice = 1.3;//
            double l_dblOrderCondPrice = 7.9;//
            WEB3IfoTradedProductImpl l_ifoTradedProduct =
                new WEB3IfoTradedProductImpl(l_tradedProductParams);
            boolean l_blnIsBuyToOpenOrder = true;
            
            l_reusableValidations.validateOrderCondPrice(
                l_branch,
                l_dblLimitPrice,
                l_dblOrderCondPrice,
                l_ifoTradedProduct,
                l_blnIsBuyToOpenOrder);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02493, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //validate発注条件単価
    //throw 発注条件単価／注文単価入力エラー（時価の挟み込み不正）。
    public void testValidateOrderCondPrice_C0003()
    {
        final String STR_METHOD_NAME = "testValidateOrderCondPrice_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoOrderManagerReusableValidations l_reusableValidations =
            new WEB3IfoOrderManagerReusableValidations();
        
        try
        {
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();      
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setNameSerialNo(2);

            l_branchPreferencesParams.setName("triggerorder.wlimitorder.check.order.cond.price");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            l_branchPreferencesParams.setName("triggerorder.wlimitorder.divergencerate");
            l_branchPreferencesParams.setValue("1.1");//
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006160060009L);
            l_IfoTradedProductParams.setTradedProductId(330304148080002L);
            l_IfoTradedProductParams.setMarketId(3306L);
            l_IfoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080002L);
            l_tradedProductParams.setProductId(1006160060009L);
            l_tradedProductParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getTickValue",
                new Class[] {WEB3IfoProductImpl.class, double.class},
                new Double(6.0));//
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getCurrentPrice",
                new Class[] {WEB3IfoTradedProductImpl.class},
                new Double(7.9));//
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381L);
            double l_dblLimitPrice = 1.3;//
            double l_dblOrderCondPrice = 7.9;//
            WEB3IfoTradedProductImpl l_ifoTradedProduct =
                new WEB3IfoTradedProductImpl(l_tradedProductParams);
            boolean l_blnIsBuyToOpenOrder = false;
            
            l_reusableValidations.validateOrderCondPrice(
                l_branch,
                l_dblLimitPrice,
                l_dblOrderCondPrice,
                l_ifoTradedProduct,
                l_blnIsBuyToOpenOrder);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02493, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@

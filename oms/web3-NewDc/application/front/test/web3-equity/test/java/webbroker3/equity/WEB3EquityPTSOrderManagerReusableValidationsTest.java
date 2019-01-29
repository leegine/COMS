head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.13.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityPTSOrderManagerReusableValidationsTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3EquityPTSOrderManagerReusableValidationsTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/19 崔遠鵬 (中訊) 新規作成
*/
package webbroker3.equity;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.PersistenceManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductCalendarParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductCalendarRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.AccountProductOrderStopParams;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondParams;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondRow;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityPTSOrderManagerReusableValidationsTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSOrderManagerReusableValidationsTest.class);

    WEB3EquityPTSOrderManagerReusableValidationsForTest l_validations = new WEB3EquityPTSOrderManagerReusableValidationsForTest();

    WEB3EquityPTSOrderManagerReusableValidations l_validations1 = new WEB3EquityPTSOrderManagerReusableValidations();
    public WEB3EquityPTSOrderManagerReusableValidationsTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        TestDBUtility.deleteAll(BranchParams.TYPE);
        TestDBUtility.deleteAll(TradedProductParams.TYPE);
        TestDBUtility.deleteAll(MarketParams.TYPE);
        TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
        TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
        TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
        TestDBUtility.deleteAll(ProductParams.TYPE);
        TestDBUtility.deleteAll(TraderParams.TYPE);
        TestDBUtility.deleteAll(TradingTimeParams.TYPE);
        TestDBUtility.deleteAll(MainAccountParams.TYPE);
        TestDBUtility.deleteAll(SubAccountParams.TYPE);
        TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
        TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
        TestDBUtility.deleteAll(AccountProductOrderStopParams.TYPE);

    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        TestDBUtility.deleteAll(BranchParams.TYPE);
        TestDBUtility.deleteAll(TradedProductParams.TYPE);
        TestDBUtility.deleteAll(MarketParams.TYPE);
        TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
        TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
        TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
        TestDBUtility.deleteAll(ProductParams.TYPE);
        TestDBUtility.deleteAll(TraderParams.TYPE);
        TestDBUtility.deleteAll(TradingTimeParams.TYPE);
        TestDBUtility.deleteAll(MainAccountParams.TYPE);
        TestDBUtility.deleteAll(SubAccountParams.TYPE);
        TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
        TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
        TestDBUtility.deleteAll(AccountProductOrderStopParams.TYPE);

    }

    /**
     * validatePTS注文訂正可能状態
     * 注文有効状態がOPEN以外の場合
     */
    public void testValidatePTSOrderForChangeabilityC001()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForChangeabilityC001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForChangeability(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文訂正可能状態
     * 注文有効状態がOPENの場合
     * 市場開局時間帯（PTS取引時間管理.is市場開局時間帯( ) == true）の場合
     * 注文状態がACCEPTED
     */
    public void testValidatePTSOrderForChangeabilityC002()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForChangeabilityC002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParam = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParam.setInstitutionCode("0D");
            l_tradingTimeParam.setBranchCode("381");
            l_tradingTimeParam.setMarketCode("11");
            l_tradingTimeParam.setTradingTimeType("26");
            l_tradingTimeParam.setProductCode("0");
            l_tradingTimeParam.setBizDateType("4");
            l_tradingTimeParam.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParam);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForChangeability(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            QueryProcessor l_processor;
            try
            {
                l_processor = Processors.getDefaultProcessor();
                l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            }
            catch (DataException e)
            {
                // TODO Auto-generated catch block
                log.error("DataException:" + e);
            }
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        finally
        {

        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文訂正可能状態
     * 注文有効状態がOPENの場合
     * 市場開局時間帯（PTS取引時間管理.is市場開局時間帯( ) == true）の場合
     * 注文状態がCANCEL_ACCEPTED
     */
    public void testValidatePTSOrderForChangeabilityC003()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForChangeabilityC003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParam = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParam.setInstitutionCode("0D");
            l_tradingTimeParam.setBranchCode("381");
            l_tradingTimeParam.setMarketCode("11");
            l_tradingTimeParam.setTradingTimeType("26");
            l_tradingTimeParam.setProductCode("0");
            l_tradingTimeParam.setBizDateType("4");
            l_tradingTimeParam.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParam);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForChangeability(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            QueryProcessor l_processor;
            try
            {
                l_processor = Processors.getDefaultProcessor();
                l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            }
            catch (DataException e)
            {
                // TODO Auto-generated catch block
                log.error("DataException:" + e);
            }
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文訂正可能状態
     * 注文有効状態がOPENの場合
     * 市場開局時間帯（PTS取引時間管理.is市場開局時間帯( ) == true）の場合
     * 注文状態がCANCELLING
     */
    public void testValidatePTSOrderForChangeabilityC004()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForChangeabilityC004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLING);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParam = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParam.setInstitutionCode("0D");
            l_tradingTimeParam.setBranchCode("381");
            l_tradingTimeParam.setMarketCode("11");
            l_tradingTimeParam.setTradingTimeType("26");
            l_tradingTimeParam.setProductCode("0");
            l_tradingTimeParam.setBizDateType("4");
            l_tradingTimeParam.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParam);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForChangeability(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            QueryProcessor l_processor;
            try
            {
                l_processor = Processors.getDefaultProcessor();
                l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            }
            catch (DataException e)
            {
                // TODO Auto-generated catch block
                log.error("DataException:" + e);
            }
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文訂正可能状態
     * 注文有効状態がOPENの場合
     * 市場開局時間帯（PTS取引時間管理.is市場開局時間帯( ) == true）の場合
     * 注文状態がMODIFY_ACCEPTED
     */
    public void testValidatePTSOrderForChangeabilityC005()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForChangeabilityC005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParam = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParam.setInstitutionCode("0D");
            l_tradingTimeParam.setBranchCode("381");
            l_tradingTimeParam.setMarketCode("11");
            l_tradingTimeParam.setTradingTimeType("26");
            l_tradingTimeParam.setProductCode("0");
            l_tradingTimeParam.setBizDateType("4");
            l_tradingTimeParam.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParam);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForChangeability(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            QueryProcessor l_processor;
            try
            {
                l_processor = Processors.getDefaultProcessor();
                l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            }
            catch (DataException e)
            {
                // TODO Auto-generated catch block
                log.error("DataException:" + e);
            }
            
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文訂正可能状態
     * 注文有効状態がOPENの場合
     * 市場開局時間帯（PTS取引時間管理.is市場開局時間帯( ) == true）の場合
     * 注文状態がMODIFYING
     */
    public void testValidatePTSOrderForChangeabilityC006()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForChangeabilityC006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFYING);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParam = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParam.setInstitutionCode("0D");
            l_tradingTimeParam.setBranchCode("381");
            l_tradingTimeParam.setMarketCode("11");
            l_tradingTimeParam.setTradingTimeType("26");
            l_tradingTimeParam.setProductCode("0");
            l_tradingTimeParam.setBizDateType("4");
            l_tradingTimeParam.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParam);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForChangeability(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            QueryProcessor l_processor;
            try
            {
                l_processor = Processors.getDefaultProcessor();
                l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            }
            catch (DataException e)
            {
                // TODO Auto-generated catch block
                log.error("DataException:" + e);
            }
            
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文訂正可能状態
     * 注文有効状態がOPENの場合
     * 市場開局時間帯（PTS取引時間管理.is市場開局時間帯( ) == true）の場合
     * 注文状態がORDERING
     */
    public void testValidatePTSOrderForChangeabilityC007()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForChangeabilityC007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParam = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParam.setInstitutionCode("0D");
            l_tradingTimeParam.setBranchCode("381");
            l_tradingTimeParam.setMarketCode("11");
            l_tradingTimeParam.setTradingTimeType("26");
            l_tradingTimeParam.setProductCode("0");
            l_tradingTimeParam.setBizDateType("4");
            l_tradingTimeParam.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParam);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForChangeability(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            QueryProcessor l_processor;
            try
            {
                l_processor = Processors.getDefaultProcessor();
                l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            }
            catch (DataException e)
            {
                // TODO Auto-generated catch block
                log.error("DataException:" + e);
            }
            
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文訂正可能状態
     * 注文有効状態がOPENの場合
     * 市場開局時間帯（PTS取引時間管理.is市場開局時間帯( ) == true）の場合
     * 注文単位.市場から確認済みの数量 == NaN
     */
    public void testValidatePTSOrderForChangeabilityC008()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForChangeabilityC008()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(null);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParam = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParam.setInstitutionCode("0D");
            l_tradingTimeParam.setBranchCode("381");
            l_tradingTimeParam.setMarketCode("11");
            l_tradingTimeParam.setTradingTimeType("26");
            l_tradingTimeParam.setProductCode("0");
            l_tradingTimeParam.setBizDateType("4");
            l_tradingTimeParam.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParam);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForChangeability(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            QueryProcessor l_processor;
            try
            {
                l_processor = Processors.getDefaultProcessor();
                l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            }
            catch (DataException e)
            {
                // TODO Auto-generated catch block
                log.error("DataException:" + e);
            }
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文訂正可能状態
     * 注文有効状態がOPENの場合
     * 市場閉局時間帯（PTS取引時間管理.is市場開局時間帯( ) == ｆａｌｓｅ）の場合
     * 注文状態がCANCEL_ACCEPTED
     */
    public void testValidatePTSOrderForChangeabilityC009()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForChangeabilityC009()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("10");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071227", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForChangeability(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文訂正可能状態
     * 注文有効状態がOPENの場合
     * 市場閉局時間帯（PTS取引時間管理.is市場開局時間帯( ) == ｆａｌｓｅ）の場合
     * 注文状態がCANCELLING
     */
    public void testValidatePTSOrderForChangeabilityC010()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForChangeabilityC010()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("10");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLING);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071227", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForChangeability(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文訂正可能状態
     * 注文有効状態がOPENの場合
     * 市場閉局時間帯（PTS取引時間管理.is市場開局時間帯( ) == ｆａｌｓｅ）の場合
     * 注文状態がMODIFY_ACCEPTED
     */
    public void testValidatePTSOrderForChangeabilityC011()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForChangeabilityC011()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("10");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071227", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForChangeability(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文訂正可能状態
     * 注文有効状態がOPENの場合
     * 市場閉局時間帯（PTS取引時間管理.is市場開局時間帯( ) == ｆａｌｓｅ）の場合
     * 注文状態がMODIFYING
     */
    public void testValidatePTSOrderForChangeabilityC012()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForChangeabilityC012()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("10");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFYING);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071227", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForChangeability(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文訂正可能状態
     * 注文有効状態がOPENの場合
     * 市場閉局時間帯（PTS取引時間管理.is市場開局時間帯( ) == ｆａｌｓｅ）の場合
     * 注文状態がORDERING
     */
    public void testValidatePTSOrderForChangeabilityC013()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForChangeabilityC013()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("10");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071227", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForChangeability(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文訂正可能状態
     * 注文有効状態がOPENの場合
     * 市場閉局時間帯（PTS取引時間管理.is市場開局時間帯( ) == ｆａｌｓｅ）の場合
     * 注文状態が下記以外
     * CANCEL_ACCEPTED
     * CANCELLING
     * MODIFY_ACCEPTED
     * MODIFYING
     * ORDERING
     */
    public void testValidatePTSOrderForChangeabilityC014()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForChangeabilityC014()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("10");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071227", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForChangeability(l_orderManager.getOrder(2099));
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文訂正可能状態
     * 注文有効状態がOPENの場合
     * 市場開局時間帯（PTS取引時間管理.is市場開局時間帯( ) == true）の場合
     * 注文状態が下記以外
     * ACCEPTED
     * CANCEL_ACCEPTED
     * CANCELLING
     * MODIFY_ACCEPTED
     * MODIFYING
     * ORDERING
     */
    public void testValidatePTSOrderForChangeabilityC015()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForChangeabilityC015()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(123.0);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForChangeability(l_orderManager.getOrder(2099));
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文取消可能状態
     * 注文有効状態がCLOSEDの場合
     */
    public void testValidatePTSOrderForCancellationC001()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForCancellationC001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForCancellation(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            
            
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "クローズした注文は当日約定の現引現渡注文を除いて取消できません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文取消可能状態
     * 注文有効状態がCLOSE以外の場合
     * 市場開局時間帯（PTS取引時間管理.is市場開局時間帯( ) == true）の場合
     * 注文状態がACCEPTED
     */
    public void testValidatePTSOrderForCancellationC002()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForCancellationC002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParam = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParam.setInstitutionCode("0D");
            l_tradingTimeParam.setBranchCode("381");
            l_tradingTimeParam.setMarketCode("11");
            l_tradingTimeParam.setTradingTimeType("26");
            l_tradingTimeParam.setProductCode("0");
            l_tradingTimeParam.setBizDateType("4");
            l_tradingTimeParam.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParam);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForCancellation(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文取消可能状態
     * 注文有効状態がCLOSE以外の場合
     * 市場開局時間帯（PTS取引時間管理.is市場開局時間帯( ) == true）の場合
     * 注文状態がCANCEL_ACCEPTED
     */
    public void testValidatePTSOrderForCancellationC003()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForCancellationC003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParam = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParam.setInstitutionCode("0D");
            l_tradingTimeParam.setBranchCode("381");
            l_tradingTimeParam.setMarketCode("11");
            l_tradingTimeParam.setTradingTimeType("26");
            l_tradingTimeParam.setProductCode("0");
            l_tradingTimeParam.setBizDateType("4");
            l_tradingTimeParam.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParam);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForCancellation(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文取消可能状態
     * 注文有効状態がCLOSE以外の場合
     * 市場開局時間帯（PTS取引時間管理.is市場開局時間帯( ) == true）の場合
     * 注文状態がCANCELLING
     */
    public void testValidatePTSOrderForCancellationC004()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForCancellationC004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLING);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParam = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParam.setInstitutionCode("0D");
            l_tradingTimeParam.setBranchCode("381");
            l_tradingTimeParam.setMarketCode("11");
            l_tradingTimeParam.setTradingTimeType("26");
            l_tradingTimeParam.setProductCode("0");
            l_tradingTimeParam.setBizDateType("4");
            l_tradingTimeParam.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParam);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForCancellation(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文取消可能状態
     * 注文有効状態がCLOSE以外の場合
     * 市場開局時間帯（PTS取引時間管理.is市場開局時間帯( ) == true）の場合
     * 注文状態がMODIFY_ACCEPTED
     */
    public void testValidatePTSOrderForCancellationC005()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForCancellationC005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParam = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParam.setInstitutionCode("0D");
            l_tradingTimeParam.setBranchCode("381");
            l_tradingTimeParam.setMarketCode("11");
            l_tradingTimeParam.setTradingTimeType("26");
            l_tradingTimeParam.setProductCode("0");
            l_tradingTimeParam.setBizDateType("4");
            l_tradingTimeParam.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParam);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForCancellation(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文取消可能状態
     * 注文有効状態がCLOSE以外の場合
     * 市場開局時間帯（PTS取引時間管理.is市場開局時間帯( ) == true）の場合
     * 注文状態がMODIFYING
     */
    public void testValidatePTSOrderForCancellationC006()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForCancellationC006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFYING);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParam = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParam.setInstitutionCode("0D");
            l_tradingTimeParam.setBranchCode("381");
            l_tradingTimeParam.setMarketCode("11");
            l_tradingTimeParam.setTradingTimeType("26");
            l_tradingTimeParam.setProductCode("0");
            l_tradingTimeParam.setBizDateType("4");
            l_tradingTimeParam.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParam);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForCancellation(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文取消可能状態
     * 注文有効状態がCLOSE以外の場合
     * 市場開局時間帯（PTS取引時間管理.is市場開局時間帯( ) == true）の場合
     * 注文状態がORDERING
     */
    public void testValidatePTSOrderForCancellationC007()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForCancellationC007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParam = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParam.setInstitutionCode("0D");
            l_tradingTimeParam.setBranchCode("381");
            l_tradingTimeParam.setMarketCode("11");
            l_tradingTimeParam.setTradingTimeType("26");
            l_tradingTimeParam.setProductCode("0");
            l_tradingTimeParam.setBizDateType("4");
            l_tradingTimeParam.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParam);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForCancellation(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文取消可能状態
     * 注文有効状態がOPENの場合
     * 市場開局時間帯（PTS取引時間管理.is市場開局時間帯( ) == true）の場合
     * 注文単位.市場から確認済みの数量 == NaN
     */
    public void testValidatePTSOrderForCancellationC008()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForCancellationC008()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(null);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParam = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParam.setInstitutionCode("0D");
            l_tradingTimeParam.setBranchCode("381");
            l_tradingTimeParam.setMarketCode("11");
            l_tradingTimeParam.setTradingTimeType("26");
            l_tradingTimeParam.setProductCode("0");
            l_tradingTimeParam.setBizDateType("4");
            l_tradingTimeParam.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParam);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForCancellation(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            QueryProcessor l_processor;
            try
            {
                l_processor = Processors.getDefaultProcessor();
                l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            }
            catch (DataException e)
            {
                log.error("DataException:" + e);
            }
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文取消可能状態
     * 注文有効状態がOPENの場合
     * 市場閉局時間帯（PTS取引時間管理.is市場開局時間帯( ) == ｆａｌｓｅ）の場合
     * 注文状態がCANCEL_ACCEPTED
     */
    public void testValidatePTSOrderForCancellationC009()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForCancellationC009()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("10");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071227", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForCancellation(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文取消可能状態
     * 注文有効状態がOPENの場合
     * 市場閉局時間帯（PTS取引時間管理.is市場開局時間帯( ) == ｆａｌｓｅ）の場合
     * 注文状態がCANCELLING
     */
    public void testValidatePTSOrderForCancellationC010()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForCancellationC010()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("10");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLING);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071227", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForCancellation(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文取消可能状態
     * 注文有効状態がOPENの場合
     * 市場閉局時間帯（PTS取引時間管理.is市場開局時間帯( ) == ｆａｌｓｅ）の場合
     * 注文状態がMODIFY_ACCEPTED
     */
    public void testValidatePTSOrderForCancellationC011()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForCancellationC011()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("10");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071227", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForCancellation(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文取消可能状態
     * 注文有効状態がOPENの場合
     * 市場閉局時間帯（PTS取引時間管理.is市場開局時間帯( ) == ｆａｌｓｅ）の場合
     * 注文状態がMODIFYING
     */
    public void testValidatePTSOrderForCancellationC012()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForCancellationC012()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("10");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFYING);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071227", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForCancellation(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文取消可能状態
     * 注文有効状態がOPENの場合
     * 市場閉局時間帯（PTS取引時間管理.is市場開局時間帯( ) == ｆａｌｓｅ）の場合
     * 注文状態がORDERING
     */
    public void testValidatePTSOrderForCancellationC013()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForCancellationC013()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("10");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071227", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForCancellation(l_orderManager.getOrder(2099));
            fail();
        }
        catch (OrderValidationException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "注文を受付られる状態ではありません。",
                l_ex.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文取消可能状態
     * 注文有効状態がOPENの場合
     * 市場閉局時間帯（PTS取引時間管理.is市場開局時間帯( ) == ｆａｌｓｅ）の場合
     * 注文状態が下記以外
     * CANCEL_ACCEPTED
     * CANCELLING
     * MODIFY_ACCEPTED
     * MODIFYING
     * ORDERING
     */
    public void testValidatePTSOrderForCancellationC014()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForCancellationC014()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("10");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071227", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForCancellation(l_orderManager.getOrder(2099));
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validatePTS注文取消可能状態
     * 注文有効状態がOPENの場合
     * 市場開局時間帯（PTS取引時間管理.is市場開局時間帯( ) == true）の場合
     * 注文状態が下記以外
     * ACCEPTED
     * CANCEL_ACCEPTED
     * CANCELLING
     * MODIFY_ACCEPTED
     * MODIFYING
     * ORDERING
     */
    public void testValidatePTSOrderForCancellationC015()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForCancellationC015()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(123.0);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSOrderForCancellation(l_orderManager.getOrder(2099));
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate株数（PTS）
     * super.validateQuantityに異常を起こす
     */
    public void testValidatePTSQuantityC001()
    {
        final String STR_METHOD_NAME = "testValidatePTSQuantityC001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            l_processor.doDeleteAllQuery(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setPrimaryMarketId(3303L);
            TestDBUtility.insertWithDel(l_productParams);

            l_processor.doDeleteAllQuery(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            TestDBUtility.insertWithDel(l_tradedProductParams);

            l_processor.doDeleteAllQuery(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            l_processor.doDeleteAllQuery(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            l_processor.doDeleteAllQuery(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            ProductManager l_productManger = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
            l_validations.validatePTSQuantity(
                l_productManger.getTradedProduct(1006160060005L),
                new WEB3GentradeBranch(33381L),
                -5.0d);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals("注文数量が0以下の値です。", l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate株数（PTS）
     * super.validateQuantityに異常なし
     * super.checkLotSizeに異常を起こす
     */
    public void testValidatePTSQuantityC002()
    {
        final String STR_METHOD_NAME = "testValidatePTSQuantityC002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            l_processor.doDeleteAllQuery(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setPrimaryMarketId(3303L);
            TestDBUtility.insertWithDel(l_productParams);

            l_processor.doDeleteAllQuery(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            TestDBUtility.insertWithDel(l_tradedProductParams);

            l_processor.doDeleteAllQuery(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            l_processor.doDeleteAllQuery(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setLotSize(0.07D);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            l_processor.doDeleteAllQuery(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            ProductManager l_productManger = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
            l_validations.validatePTSQuantity(
                l_productManger.getTradedProduct(1006160060005L),
                new WEB3GentradeBranch(33381L),
                0.1d);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals("株数が売買単位の整数倍ではありません。", l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate株数（PTS）
     * super.validateQuantityに異常なし
     * super.checkLotSizeに異常なし
     * this.validate株数（指定可能上限値）に異常を起こす
     */
    public void testValidatePTSQuantityC003()
    {
        final String STR_METHOD_NAME = "testValidatePTSQuantityC003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            l_processor.doDeleteAllQuery(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setPrimaryMarketId(3303L);
            TestDBUtility.insertWithDel(l_productParams);

            l_processor.doDeleteAllQuery(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            TestDBUtility.insertWithDel(l_tradedProductParams);

            l_processor.doDeleteAllQuery(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            l_processor.doDeleteAllQuery(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setLotSize(0.01D);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            l_processor.doDeleteAllQuery(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            InstitutionParams  l_institutionParams =  TestDBUtility.getInstitutionRow();
            l_institutionParams.setMaxOrderQuantity(0L);
            TestDBUtility.insertWithDel(l_institutionParams);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            ProductManager l_productManger = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
            l_validations.validatePTSQuantity(
                l_productManger.getTradedProduct(1006160060005L),
                new WEB3GentradeBranch(33381L),
                5.0d);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals("株数は指定可能株数上限値を越えています。", l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate株数（PTS）
     * super.validateQuantityに異常なし
     * super.checkLotSizeに異常なし
     * this.validate株数（指定可能上限値）に異常なし
     * 取引銘柄.強制限度単位 != null の場合
     * （市場の売買上限株数　@< 株数）の場合
     */
    public void testValidatePTSQuantityC004()
    {
        final String STR_METHOD_NAME = "testValidatePTSQuantityC004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            l_processor.doDeleteAllQuery(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setPrimaryMarketId(3303L);
            TestDBUtility.insertWithDel(l_productParams);

            l_processor.doDeleteAllQuery(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            TestDBUtility.insertWithDel(l_tradedProductParams);

            l_processor.doDeleteAllQuery(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            l_processor.doDeleteAllQuery(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setLotSize(0.01D);
            l_eqtypeTradedProductParams.setCompulsiveLimitedUnit(2);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            l_processor.doDeleteAllQuery(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            InstitutionParams  l_institutionParams =  TestDBUtility.getInstitutionRow();
            l_institutionParams.setMaxOrderQuantity(6L);
            TestDBUtility.insertWithDel(l_institutionParams);

            l_processor.doDeleteAllQuery(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams.setMarketCode("SP");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            ProductManager l_productManger = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
            l_validations.validatePTSQuantity(
                l_productManger.getTradedProduct(1006160060005L),
                new WEB3GentradeBranch(33381L),
                0.021d);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals("株数が売買上限株数を超えています。", l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate株数（PTS）
     * super.validateQuantityに異常なし
     * super.checkLotSizeに異常なし
     * this.validate株数（指定可能上限値）に異常なし
     * 取引銘柄.強制限度単位 == null の場合
     * （市場の売買上限株数　@>= 株数）の場合
     */
    public void testValidatePTSQuantityC005()
    {
        final String STR_METHOD_NAME = "testValidatePTSQuantityC005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            l_processor.doDeleteAllQuery(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setPrimaryMarketId(3303L);
            TestDBUtility.insertWithDel(l_productParams);

            l_processor.doDeleteAllQuery(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            TestDBUtility.insertWithDel(l_tradedProductParams);

            l_processor.doDeleteAllQuery(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            l_processor.doDeleteAllQuery(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setLotSize(0.01D);
            l_eqtypeTradedProductParams.setCompulsiveLimitedUnit(null);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            l_processor.doDeleteAllQuery(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            InstitutionParams  l_institutionParams =  TestDBUtility.getInstitutionRow();
            l_institutionParams.setMaxOrderQuantity(6L);
            TestDBUtility.insertWithDel(l_institutionParams);

            l_processor.doDeleteAllQuery(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams = TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams.setMarketCode("SP");
            l_branchMarketPtsDealtCondParams.setLimitedUnit(3);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            ProductManager l_productManger = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
            l_validations.validatePTSQuantity(
                l_productManger.getTradedProduct(1006160060005L),
                new WEB3GentradeBranch(33381L),
                0.021d);
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate訂正項目（PTS）
     * isChange株数（注文単位、訂正後株数）に異常を起こす
     */
    public void testValidatePTSChangeItemC001()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeItemC001()";
        log.entering(STR_METHOD_NAME);
        l_validations.flag = STR_METHOD_NAME;

        try
        {
            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSChangeItem(
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L),
                1.0d,
                0.0d,
                EqTypeExecutionConditionType.AT_MARKET_OPEN,
                "",
                "",
                "",
                0.0d,
                0.0d,
                EqTypeExecutionConditionType.AT_MARKET_OPEN,
                false,
                WEB3DateUtility.getDate("20071229", "yyyyMMdd"),
                new EqTypeSettleContractOrderEntry[]{});
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "お客様の入力された株数は、原注文が一部出来の状態で、約定数以下のため受け付けることが出来ません。",
                l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate訂正項目（PTS）
     * isChange株数（注文単位、訂正後株数）に異常なし
     * isChange執行条件（注文単位、訂正後執行条件）に異常を起こす
     */
    public void testValidatePTSChangeItemC002()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeItemC002()";
        log.entering(STR_METHOD_NAME);
        l_validations.flag = STR_METHOD_NAME;

        try
        {
            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setQuantity(2.0d);
            l_eqtypeOrderUnitParams.setExecutedQuantity(0.5d);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSChangeItem(
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L),
                1.0d,
                0.0d,
                EqTypeExecutionConditionType.AT_MARKET_OPEN,
                "",
                "",
                "",
                0.0d,
                0.0d,
                EqTypeExecutionConditionType.AT_MARKET_OPEN,
                false,
                WEB3DateUtility.getDate("20071229", "yyyyMMdd"),
                new EqTypeSettleContractOrderEntry[]{});
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "出来るまで注文は執行条件の訂正不可。",
                l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate訂正項目（PTS）
     * isChange株数（注文単位、訂正後株数）に異常なく、trueを返却
     * isChange単価（注文単位、訂正後指値）はtrueを返却
     * isChange執行条件（注文単位、訂正後執行条件）に異常なく、falseを返却
     * 市場オブジェクト.同時訂正可能区分＝”複数項目同時訂正不可”の場合
     */
    public void testValidatePTSChangeItemC003()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeItemC003()";
        log.entering(STR_METHOD_NAME);
        l_validations.flag = STR_METHOD_NAME;

        try
        {
            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setQuantity(2.0d);
            l_eqtypeOrderUnitParams.setExecutedQuantity(0.5d);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setChangeableType("0");
            TestDBUtility.insertWithDel(l_marketParams);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSChangeItem(
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L),
                1.0d,
                0.0d,
                EqTypeExecutionConditionType.AT_MARKET_OPEN,
                "",
                "",
                "",
                0.0d,
                0.0d,
                EqTypeExecutionConditionType.AT_MARKET_OPEN,
                false,
                WEB3DateUtility.getDate("20071229", "yyyyMMdd"),
                new EqTypeSettleContractOrderEntry[]{});
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(
                "複数項目同時訂正不可。",
                l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate訂正項目（PTS）
     * isChange株数（注文単位、訂正後株数）に異常なく、trueを返却
     * isChange単価（注文単位、訂正後指値）はfalseを返却
     * isChange執行条件（注文単位、訂正後執行条件）に異常なく、trueを返却
     * 市場オブジェクト.同時訂正可能区分!＝”複数項目同時訂正不可”の場合
     */
    public void testValidatePTSChangeItemC004()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeItemC004()";
        log.entering(STR_METHOD_NAME);
        l_validations.flag = STR_METHOD_NAME;

        try
        {
            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setQuantity(2.0d);
            l_eqtypeOrderUnitParams.setExecutedQuantity(0.5d);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setChangeableType("1");
            TestDBUtility.insertWithDel(l_marketParams);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSChangeItem(
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L),
                1.0d,
                0.0d,
                EqTypeExecutionConditionType.AT_MARKET_OPEN,
                "",
                "",
                "",
                0.0d,
                0.0d,
                EqTypeExecutionConditionType.AT_MARKET_OPEN,
                false,
                WEB3DateUtility.getDate("20071229", "yyyyMMdd"),
                new EqTypeSettleContractOrderEntry[]{});
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate訂正項目（PTS）
     * isChange株数（注文単位、訂正後株数）に異常なく、falseを返却
     * isChange単価（注文単位、訂正後指値）はfalseを返却
     * isChange執行条件（注文単位、訂正後執行条件）に異常なく、trueを返却
     */
    public void testValidatePTSChangeItemC005()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeItemC005()";
        log.entering(STR_METHOD_NAME);
        l_validations.flag = STR_METHOD_NAME;

        try
        {
            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setQuantity(2.0d);
            l_eqtypeOrderUnitParams.setExecutedQuantity(0.5d);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setChangeableType("1");
            TestDBUtility.insertWithDel(l_marketParams);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSChangeItem(
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L),
                1.0d,
                0.0d,
                EqTypeExecutionConditionType.AT_MARKET_OPEN,
                "",
                "",
                "",
                0.0d,
                0.0d,
                EqTypeExecutionConditionType.AT_MARKET_OPEN,
                false,
                WEB3DateUtility.getDate("20071229", "yyyyMMdd"),
                new EqTypeSettleContractOrderEntry[]{});
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate訂正項目（PTS）
     * isChange株数（注文単位、訂正後株数）に異常なく、falseを返却
     * isChange単価（注文単位、訂正後指値）はfalseを返却
     * isChange執行条件（注文単位、訂正後執行条件）に異常なく、falseを返却
     */
    public void testValidatePTSChangeItemC006()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeItemC006()";
        log.entering(STR_METHOD_NAME);
        l_validations.flag = STR_METHOD_NAME;

        try
        {
            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            l_processor.doDeleteAllQuery(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setQuantity(2.0d);
            l_eqtypeOrderUnitParams.setExecutedQuantity(0.5d);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            l_processor.doDeleteAllQuery(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setChangeableType("1");
            TestDBUtility.insertWithDel(l_marketParams);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_validations.validatePTSChangeItem(
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L),
                1.0d,
                0.0d,
                EqTypeExecutionConditionType.AT_MARKET_OPEN,
                "",
                "",
                "",
                0.0d,
                0.0d,
                EqTypeExecutionConditionType.AT_MARKET_OPEN,
                false,
                WEB3DateUtility.getDate("20071229", "yyyyMMdd"),
                new EqTypeSettleContractOrderEntry[]{});
            assertTrue(true);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals("訂正入力されていません。", l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate注文単価
     * 呼値チェック（PTS）==falseの場合
     */
    public void testValidatePTSPriceC0001()
    {
        final String STR_METHOD_NAME = "testValidatePTSPriceC0001()";
        log.entering(STR_METHOD_NAME);
        l_validations.flag = STR_METHOD_NAME;

        try
        {
            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setPrimaryMarketId(3303L);
            TestDBUtility.insertWithDel(l_productParams);

            l_processor.doDeleteAllQuery(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            l_processor.doDeleteAllQuery(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            l_processor.doDeleteAllQuery(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            l_processor.doDeleteAllQuery(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            ProductManager l_productManger = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
            boolean  l_blnResult = l_validations.validatePTSPrice(
                0.0d,
                (WEB3EquityTradedProduct)l_productManger.getTradedProduct(1006160060005L),
                new WEB3GentradeSubAccount(333812512246L, 33381251220301L));

            assertFalse(l_blnResult);
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate注文単価
     * 呼値チェック（PTS）==trueの場合
     * 対象銘柄=権利落ち日の場合
     */
    public void testValidatePTSPriceC0002()
    {
        final String STR_METHOD_NAME = "testValidatePTSPriceC0002()";
        log.entering(STR_METHOD_NAME);
        l_validations.flag = STR_METHOD_NAME;

        try
        {
            //スタティックの準備
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.bizdate.offset", null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getCurrentBizDate",
                    new Class[] {long.class},
                    WEB3DateUtility.getDate("20040712", "yyyyMMdd"));
            
            Timestamp l_tsTime = new Timestamp(WEB3DateUtility.getDate("20040712", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_tsTime);

            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setPrimaryMarketId(3303L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            l_processor.doDeleteAllQuery(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            l_processor.doDeleteAllQuery(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            l_processor.doDeleteAllQuery(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setValidUntilBizDate("20070712");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            l_processor.doDeleteAllQuery(EqtypeTradedProductUpdqRow.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams = new EqtypeTradedProductUpdqParams();
            l_eqtypeTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductUpdqParams.setInstitutionCode("0D");
            l_eqtypeTradedProductUpdqParams.setProductId(3304148080000L);
            l_eqtypeTradedProductUpdqParams.setMarketId(3303L);
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20040712");
            l_eqtypeTradedProductUpdqParams.setListFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductUpdqParams.setListType("1");
            l_eqtypeTradedProductUpdqParams.setNewListType("1");
            l_eqtypeTradedProductUpdqParams.setListedDate(Calendar.getInstance().getTime());
            l_eqtypeTradedProductUpdqParams.setMarginableFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductUpdqParams.setShortableFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductUpdqParams.setMiniStockCanDealt(BooleanEnum.TRUE);
            l_eqtypeTradedProductUpdqParams.setLotSize(100.0D);
            l_eqtypeTradedProductUpdqParams.setLastClosingPrice(1000.0D);
            l_eqtypeTradedProductUpdqParams.setMiniStockFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductUpdqParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeTradedProductUpdqParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeTradedProductUpdqParams.setBasePrice(10.0D);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParams);

            l_processor.doDeleteAllQuery(TradedProductCalendarRow.TYPE);
            TradedProductCalendarParams l_tradedProductCalendarParams = TestDBUtility.getTradedProductCalendarRow();
            l_tradedProductCalendarParams.setMarketId(3303L);
            l_tradedProductCalendarParams.setProductId(3304148080000L);
            l_tradedProductCalendarParams.setTradeOpenTime("00:00");
            l_tradedProductCalendarParams.setTradeCloseTime("23:59");
            TestDBUtility.insertWithDel(l_tradedProductCalendarParams);

            l_processor.doDeleteAllQuery(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

//            l_processor.doDeleteAllQuery(MarketCalendarRow.TYPE);
//            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
//            l_marketCalendarParams.setMarketId(3303L);
//            l_marketCalendarParams.setTradeDate(WEB3DateUtility.getDate("20040709","yyyyMMdd"));
//            l_marketCalendarParams.setHolidayFlag(BooleanEnum.FALSE);
//            l_marketCalendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_marketCalendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            l_marketCalendarParams.setTradeOpenTime("121212");
//            TestDBUtility.insertWithDel(l_marketCalendarParams);

            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

//            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);//
//            SystemPreferencesParams l_systemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
//            l_systemPreferencesParams.setName("shift.system.timestamp.millisecs");
//            l_systemPreferencesParams.setValue("20071231");
//            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            ProductManager l_productManger = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
            boolean l_blnResult = l_validations.validatePTSPrice(
                0.0d,
                (WEB3EquityTradedProduct)l_productManger.getTradedProduct(1006160060005L),
                new WEB3GentradeSubAccount(333812512246L, 33381251220301L));

            assertTrue(l_blnResult);
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate注文単価
     * 呼値チェック（PTS）==trueの場合
     * 対象銘柄!=権利落ち日の場合
     * 対象銘柄は対象取引市場において値幅チェック対象設定外となっている場合
     */
    public void testValidatePTSPriceC0003()
    {
        final String STR_METHOD_NAME = "testValidatePTSPriceC0003()";
        log.entering(STR_METHOD_NAME);
        l_validations.flag = STR_METHOD_NAME;

        try
        {
            //スタティックの準備
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.bizdate.offset", null);

            Timestamp l_tsTime = new Timestamp(WEB3DateUtility.getDate("20040712", "yyyyMMdd").getTime());
            
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_tsTime);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getCurrentBizDate",
                    new Class[] {long.class},
                    WEB3DateUtility.getDate("20040712", "yyyyMMdd"));
                    
            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setPrimaryMarketId(3303L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            l_processor.doDeleteAllQuery(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            l_processor.doDeleteAllQuery(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            l_processor.doDeleteAllQuery(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setValidUntilBizDate("20070712");
            l_eqtypeTradedProductParams.setPriceRangeType("0");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            l_processor.doDeleteAllQuery(EqtypeTradedProductUpdqRow.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams = new EqtypeTradedProductUpdqParams();
            l_eqtypeTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductUpdqParams.setInstitutionCode("0D");
            l_eqtypeTradedProductUpdqParams.setProductId(3304148080000L);
            l_eqtypeTradedProductUpdqParams.setMarketId(3303L);
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20040712");
            l_eqtypeTradedProductUpdqParams.setListFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductUpdqParams.setListType("1");
            l_eqtypeTradedProductUpdqParams.setNewListType("1");
            l_eqtypeTradedProductUpdqParams.setListedDate(Calendar.getInstance().getTime());
            l_eqtypeTradedProductUpdqParams.setMarginableFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductUpdqParams.setShortableFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductUpdqParams.setMiniStockCanDealt(BooleanEnum.TRUE);
            l_eqtypeTradedProductUpdqParams.setLotSize(100.0D);
            l_eqtypeTradedProductUpdqParams.setLastClosingPrice(1000.0D);
            l_eqtypeTradedProductUpdqParams.setMiniStockFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductUpdqParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeTradedProductUpdqParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeTradedProductUpdqParams.setBasePrice(10.0D);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParams);

            l_processor.doDeleteAllQuery(TradedProductCalendarRow.TYPE);
            TradedProductCalendarParams l_tradedProductCalendarParams = TestDBUtility.getTradedProductCalendarRow();
            l_tradedProductCalendarParams.setMarketId(3303L);
            l_tradedProductCalendarParams.setProductId(3304148080000L);
            l_tradedProductCalendarParams.setTradeOpenTime("00:00");
            l_tradedProductCalendarParams.setTradeCloseTime("23:59");
            TestDBUtility.insertWithDel(l_tradedProductCalendarParams);

            l_processor.doDeleteAllQuery(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            ProductManager l_productManger = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
            boolean  l_blnResult = l_validations.validatePTSPrice(
                0.0d,
                (WEB3EquityTradedProduct)l_productManger.getTradedProduct(1006160060005L),
                new WEB3GentradeSubAccount(333812512246L, 33381251220301L));

            assertTrue(l_blnResult);
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate注文単価
     * 呼値チェック（PTS）==trueの場合
     * 対象銘柄!=権利落ち日の場合
     * 対象銘柄は対象取引市場において値幅チェック対象設定となっている場合
     */
    public void testValidatePTSPriceC0004()
    {
        final String STR_METHOD_NAME = "testValidatePTSPriceC0004()";
        log.entering(STR_METHOD_NAME);
        l_validations.flag = STR_METHOD_NAME;

        try
        {
            //スタティックの準備
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.bizdate.offset", null);

            Timestamp l_tsTime = new Timestamp(WEB3DateUtility.getDate("20040712", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_tsTime);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getCurrentBizDate",
                    new Class[] {long.class},
                    WEB3DateUtility.getDate("20040712", "yyyyMMdd"));
            
            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setPrimaryMarketId(3303L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            l_processor.doDeleteAllQuery(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            l_processor.doDeleteAllQuery(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            l_processor.doDeleteAllQuery(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setValidUntilBizDate("20070712");
            l_eqtypeTradedProductParams.setPriceRangeType("1");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            l_processor.doDeleteAllQuery(EqtypeTradedProductUpdqRow.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams = new EqtypeTradedProductUpdqParams();
            l_eqtypeTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductUpdqParams.setInstitutionCode("0D");
            l_eqtypeTradedProductUpdqParams.setProductId(3304148080000L);
            l_eqtypeTradedProductUpdqParams.setMarketId(3303L);
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20040712");
            l_eqtypeTradedProductUpdqParams.setListFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductUpdqParams.setListType("1");
            l_eqtypeTradedProductUpdqParams.setNewListType("1");
            l_eqtypeTradedProductUpdqParams.setListedDate(Calendar.getInstance().getTime());
            l_eqtypeTradedProductUpdqParams.setMarginableFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductUpdqParams.setShortableFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductUpdqParams.setMiniStockCanDealt(BooleanEnum.TRUE);
            l_eqtypeTradedProductUpdqParams.setLotSize(100.0D);
            l_eqtypeTradedProductUpdqParams.setLastClosingPrice(1000.0D);
            l_eqtypeTradedProductUpdqParams.setMiniStockFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductUpdqParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeTradedProductUpdqParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeTradedProductUpdqParams.setBasePrice(10.0D);
            l_eqtypeTradedProductUpdqParams.setPriceRangeType("1");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParams);

            l_processor.doDeleteAllQuery(TradedProductCalendarRow.TYPE);
            TradedProductCalendarParams l_tradedProductCalendarParams = TestDBUtility.getTradedProductCalendarRow();
            l_tradedProductCalendarParams.setMarketId(3303L);
            l_tradedProductCalendarParams.setProductId(3304148080000L);
            l_tradedProductCalendarParams.setTradeOpenTime("00:00");
            l_tradedProductCalendarParams.setTradeCloseTime("23:59");
            TestDBUtility.insertWithDel(l_tradedProductCalendarParams);

            l_processor.doDeleteAllQuery(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //テストされたメソッドを実行
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            ProductManager l_productManger = l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
            boolean  l_blnResult = l_validations.validatePTSPrice(
                0.0d,
                (WEB3EquityTradedProduct)l_productManger.getTradedProduct(1006160060005L),
                new WEB3GentradeSubAccount(333812512246L, 33381251220301L));

            assertFalse(l_blnResult);
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    class WEB3EquityPTSOrderManagerReusableValidationsForTest extends WEB3EquityPTSOrderManagerReusableValidations
    {
        public String flag = null;

        protected boolean isChangeQuantity(EqTypeOrderUnit l_orderUnit, double l_dblModifiedQuantity)
            throws WEB3BaseException
        {
            if ("testValidatePTSChangeItemC001()".equals(flag))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00037,
                    this.getClass().getName(),
                    "お客様の入力された株数は、原注文が一部出来の状態で、約定数以下のため受け付けることが出来ません。");
            }
            else if ("testValidatePTSChangeItemC002()".equals(flag))
            {
                return true;
            }
            else if ("testValidatePTSChangeItemC003()".equals(flag))
            {
                return true;
            }
            else if ("testValidatePTSChangeItemC004()".equals(flag))
            {
                return true;
            }
            else if ("testValidatePTSChangeItemC005()".equals(flag))
            {
                return false;
            }
            else if ("testValidatePTSChangeItemC006()".equals(flag))
            {
                return false;
            }
            else
            {
                return super.isChangeQuantity(l_orderUnit, l_dblModifiedQuantity);
            }
        }

        protected boolean isChangePrice(EqTypeOrderUnit l_orderUnit, double l_dblModifiedLimitPrice) 
            throws WEB3BaseException
        {
            if ("testValidatePTSChangeItemC003()".equals(flag))
            {
                return true;
            }
            else if ("testValidatePTSChangeItemC004()".equals(flag))
            {
                return false;
            }
            else if ("testValidatePTSChangeItemC005()".equals(flag))
            {
                return false;
            }
            else if ("testValidatePTSChangeItemC006()".equals(flag))
            {
                return false;
            }
            else
            {
                return super.isChangePrice(l_orderUnit, l_dblModifiedLimitPrice);
            }
        }

        protected boolean isChangeExecutionCondition(
            EqTypeOrderUnit l_orderUnit, 
            EqTypeExecutionConditionType l_modifiedExecutionType)
            throws WEB3BaseException
        {
            if ("testValidatePTSChangeItemC002()".equals(flag))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00806,
                    this.getClass().getName(),
                    "出来るまで注文は執行条件の訂正不可。");
            }
            else if ("testValidatePTSChangeItemC003()".equals(flag))
            {
                return false;
            }
            else if ("testValidatePTSChangeItemC004()".equals(flag))
            {
                return true;
            }
            else if ("testValidatePTSChangeItemC005()".equals(flag))
            {
                return true;
            }
            else if ("testValidatePTSChangeItemC006()".equals(flag))
            {
                return false;
            }
            else
            {
                return super.isChangeExecutionCondition(l_orderUnit, l_modifiedExecutionType);
            }
        }

        protected boolean isPTSTickValueDef(
            WEB3EquityTradedProduct l_tradedProduct,
            double l_dblOrderPrice)
            throws WEB3BaseException
        {
            if ("testValidatePTSPriceC0001()".equals(flag))
            {
                return false;
            }
            else if ("testValidatePTSPriceC0002()".equals(flag))
            {
                return true;
            }
            else if ("testValidatePTSPriceC0003()".equals(flag))
            {
                return true;
            }
            else if ("testValidatePTSPriceC0004()".equals(flag))
            {
                return true;
            }
            else
            {
                return super.isPTSTickValueDef(l_tradedProduct, l_dblOrderPrice);
            }
        }

        protected boolean isDevidendRightDate(
            Timestamp l_tsOrderBizDate,
            Timestamp l_tsYearlyBooksClosingDate)
            throws WEB3BaseException
        {
            if ("testValidatePTSPriceC0002()".equals(flag))
            {
                return true;
            }
            else if ("testValidatePTSPriceC0003()".equals(flag))
            {
                return false;
            }
            else if ("testValidatePTSPriceC0004()".equals(flag))
            {
                return false;
            }
            else
            {
                return super.isDevidendRightDate(l_tsOrderBizDate, l_tsYearlyBooksClosingDate);
            }
        }

        protected boolean isPTSPriceRange(
            WEB3EquityTradedProduct l_tradedProduct,
            double l_dblOrderPrice)
            throws WEB3BaseException
        {
            if ("testValidatePTSPriceC0004()".equals(flag))
            {
                return false;
            }
            else
            {
                return super.isPTSPriceRange(l_tradedProduct, l_dblOrderPrice);
            }
        }

        public String caseNum = "0";
        
        public void setCaseNum(String caseNum)
        {
            this.caseNum = caseNum;
        }
        
        public double calcBasePriceForPTSPriceRange(WEB3EquityTradedProduct l_tradedProduct)
            throws WEB3SystemLayerException
        {
            if ("3".equals(caseNum))
            {
                return 11;
            }
            return 25;
        }
        
        protected double calcPriceRange(
            WEB3EquityTradedProduct l_tradedProduct,
            double l_dblBasePrice,
            int l_intHighLowDivision)
            throws WEB3BaseException
        {
            if (l_intHighLowDivision == 1)
            {
                return 36;
            }
            else
            {
                return 12;
            }
        }
        
        public double calcStopHighPrice(
            double l_dblBasePrice,
            double l_dblPriceRange,
            double l_dblLimitPriceUnit)
            throws WEB3BaseException
        {
            if ("1".equals(caseNum))
            {
                return 24;
            }
            else if ("2".equals(caseNum))
            {
                return 20;
            }
            else
            {
                return 22;
            }
        }
    }
    
    
    /**
     * validatePTS市場別取引可能上限金額
     * 部店 = null
     */
    public void test_validatePTSMarketMaxHandlingPrice_0001()
    {
        final String STR_METHOD_NAME = "test_validatePTSMarketMaxHandlingPrice_0001()";
        log.entering(STR_METHOD_NAME);
        
        Branch l_branch = null;
        Market l_market = null;
        try
        {
            l_validations.validatePTSMarketMaxHandlingPrice(l_branch, l_market, 0.0D);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validatePTS市場別取引可能上限金額
     * 市場 = null
     */
    public void test_validatePTSMarketMaxHandlingPrice_0002()
    {
        final String STR_METHOD_NAME = "test_validatePTSMarketMaxHandlingPrice_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            Market l_market = null;
            l_validations.validatePTSMarketMaxHandlingPrice(l_branch, l_market, 0.0D);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validatePTS市場別取引可能上限金額
     * 取引可能上限金額値　@＜　@拘束売買代金 
     */
    public void test_validatePTSMarketMaxHandlingPrice_0003()
    {
        final String STR_METHOD_NAME = "test_validatePTSMarketMaxHandlingPrice_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("11");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketPTSDealtCondParams.setMaxHandlingPrice(23);
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303);
            l_validations.validatePTSMarketMaxHandlingPrice(l_branch, l_market, 25.0D);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02972, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validatePTS市場別取引可能上限金額
     * 取引可能上限金額値　@>　@拘束売買代金 
     */
    public void test_validatePTSMarketMaxHandlingPrice_0004()
    {
        final String STR_METHOD_NAME = "test_validatePTSMarketMaxHandlingPrice_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("11");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketPTSDealtCondParams.setMaxHandlingPrice(26);
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303);
            l_validations.validatePTSMarketMaxHandlingPrice(l_branch, l_market, 25.0D);
            assertTrue(true);
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate取扱可能PTS市場
     * 部店 = null
     */
    public void test_validateHandlingPossiblePTSMarket_0001()
    {
        final String STR_METHOD_NAME = "test_validatePTSMarketMaxHandlingPrice_0001()";
        log.entering(STR_METHOD_NAME);
        
        Branch l_branch = null;
        WEB3EquityTradedProduct l_tradedProduct = null;
        try
        {
            l_validations.validateHandlingPossiblePTSMarket(l_branch, l_tradedProduct);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate取扱可能PTS市場
     * 取引銘柄 = null
     */
    public void test_validateHandlingPossiblePTSMarket_0002()
    {
        final String STR_METHOD_NAME = "test_validatePTSMarketMaxHandlingPrice_0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityTradedProduct l_tradedProduct = null;
        try
        {
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            l_validations.validateHandlingPossiblePTSMarket(l_branch, l_tradedProduct);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate取扱可能PTS市場
     * is取扱可能 = false
     */
    public void test_validateHandlingPossiblePTSMarket_0003()
    {
        final String STR_METHOD_NAME = "test_validatePTSMarketMaxHandlingPrice_0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("11");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("0");
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductCode("45");
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setStandardName("ggg");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            
            TradedProductRow l_row = l_tradedProductParams;
            
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_row);
            l_validations.validateHandlingPossiblePTSMarket(l_branch, l_tradedProduct);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00158, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate取扱可能PTS市場
     * is取扱可能 = true
     */
    public void test_validateHandlingPossiblePTSMarket_0004()
    {
        final String STR_METHOD_NAME = "test_validatePTSMarketMaxHandlingPrice_0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("11");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductCode("45");
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setStandardName("ggg");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            
            TradedProductRow l_row = l_tradedProductParams;
            
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_row);
            l_validations.validateHandlingPossiblePTSMarket(l_branch, l_tradedProduct);
            assertTrue(true);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate指値注文（PTS）
     * 株式注文内容 = null
     */
    public void test_validatePTSLimitOrder_0001()
    {
        final String STR_METHOD_NAME = "test_validatePTSLimitOrder_0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_validations.validatePTSLimitOrder(null);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * validate指値注文（PTS）
     * 株式注文内容 != null
     * 引数.株式注文内容.isLimitOrder == false
     */
    public void test_validatePTSLimitOrder_0002()
    {
        final String STR_METHOD_NAME = "test_validatePTSLimitOrder_0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,12);// 注意月?要減1
            Date date = ca.getTime();
            Timestamp st = new Timestamp(date.getTime());
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.tc." + 
//                    "gentrade.stdimpls.TradingSystemImpl", 
//                "getSystemTimestamp",
//                new Class[]{}, 
//                st);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate",
                    null);
                    
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    st);
            
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution("0D");
            
            WEB3GentradeTrader l_trader = new WEB3GentradeTrader(l_institution,"11123","381");
            WEB3EquityNewCashBasedOrderSpec l_equityNewCashBasedOrderSpec =
                WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                        "",
                        l_trader,
                        "0D",
                        "45",
                        89.0D,
                        0.0D,
                        EqTypeExecutionConditionType.AT_MARKET_CLOSE,
                        TaxTypeEnum.SPECIAL,
                        new Timestamp(Calendar.getInstance().getTime().getTime()),
                        true,
                        "",
                        "",
                        "",
                        "",
                        8.0D,
                        9.0D,
                        new Long(1));
            l_validations.validatePTSLimitOrder(l_equityNewCashBasedOrderSpec);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02974, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * validate指値注文（PTS）
     * 株式注文内容 != null
     * 引数.株式注文内容.isLimitOrder != false
     */
    public void test_validatePTSLimitOrder_0003()
    {
        final String STR_METHOD_NAME = "test_validatePTSLimitOrder_0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,12);// 注意月?要減1
            Date date = ca.getTime();
            Timestamp st = new Timestamp(date.getTime());
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.tc." + 
//                    "gentrade.stdimpls.TradingSystemImpl", 
//                "getSystemTimestamp",
//                new Class[]{}, 
//                st);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate",
                    null);
                    
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    st);
            
            
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution("0D");
            
            WEB3GentradeTrader l_trader = new WEB3GentradeTrader(l_institution,"11123","381");
            WEB3EquityNewCashBasedOrderSpec l_equityNewCashBasedOrderSpec =
                WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                        "",
                        l_trader,
                        "0D",
                        "45",
                        89.0D,
                        2.0D,
                        EqTypeExecutionConditionType.AT_MARKET_CLOSE,
                        TaxTypeEnum.SPECIAL,
                        new Timestamp(Calendar.getInstance().getTime().getTime()),
                        true,
                        "",
                        "",
                        "",
                        "",
                        8.0D,
                        9.0D,
                        new Long(1));
            l_validations.validatePTSLimitOrder(l_equityNewCashBasedOrderSpec);
            assertTrue(true);
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * calc基準値（PTS値幅チェック用）
     * 取引銘柄 = null
     */
    public void test_calcBasePriceForPTSPriceRange_0001()
    {
        final String STR_METHOD_NAME = "test_calcBasePriceForPTSPriceRange_0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_validations1.calcBasePriceForPTSPriceRange(null);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * calc基準値（PTS値幅チェック用）
     * 取引銘柄 != null
     */
    public void test_calcBasePriceForPTSPriceRange_0002()
    {
        final String STR_METHOD_NAME = "test_calcBasePriceForPTSPriceRange_0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("11");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            l_eqtypeTradedProductParams.setLastClosingPrice(90.0D);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductCode("45");
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setStandardName("ggg");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            
            TradedProductRow l_row = l_tradedProductParams;
            
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_row);
            
            double l_db = l_validations1.calcBasePriceForPTSPriceRange(l_tradedProduct);
            
            assertEquals("90.0",l_db + "");
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * validate顧客銘柄別取引停止（PTS）
     * 補助口座 == null
     */
    public void test_validatePTSAccountProductOrderStop_0001()
    {
        final String STR_METHOD_NAME = "test_validatePTSAccountProductOrderStop_0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_validations.validatePTSAccountProductOrderStop(null,90L,OrderTypeEnum.UNDEFINED);
            fail();
            
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * validate顧客銘柄別取引停止（PTS）
     * 補助口座 != null
     * 顧客.isPTS取引停止銘柄( ) = true
     */
    public void test_validatePTSAccountProductOrderStop_0002()
    {
        final String STR_METHOD_NAME = "test_validatePTSAccountProductOrderStop_0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2005,7-1,12);
            Date date = ca.getTime();
            Timestamp st = new Timestamp(date.getTime());
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.tc." + 
//                    "gentrade.stdimpls.TradingSystemImpl", 
//                "getSystemTimestamp",
//                new Class[]{}, 
//                st);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate",
                    null);
                    
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    st);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            AccountProductOrderStopParams l_accountProductOrderStopParams = TestDBUtility.getAccountProductOrderStopRow();
            l_accountProductOrderStopParams.setInstitutionCode("0D");
            l_accountProductOrderStopParams.setBranchId(33381L);
            l_accountProductOrderStopParams.setAccountId(333812512246L);
            l_accountProductOrderStopParams.setProductId(90L);
            TestDBUtility.insertWithDel(l_accountProductOrderStopParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            l_validations.validatePTSAccountProductOrderStop(l_subAccount,90L,OrderTypeEnum.EQUITY_BUY);
            fail();
            
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01357, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * validate顧客銘柄別取引停止（PTS）
     * 補助口座 != null
     * 顧客.isPTS取引停止銘柄( ) = false
     */
    public void test_validatePTSAccountProductOrderStop_0003()
    {
        final String STR_METHOD_NAME = "test_validatePTSAccountProductOrderStop_0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,12);// 注意月?要減1
            Date date = ca.getTime();
            Timestamp st = new Timestamp(date.getTime());
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.tc." + 
//                    "gentrade.stdimpls.TradingSystemImpl", 
//                "getSystemTimestamp",
//                new Class[]{}, 
//                st);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate",
                    null);
                    
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    st);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            AccountProductOrderStopParams l_accountProductOrderStopParams = TestDBUtility.getAccountProductOrderStopRow();
            TestDBUtility.insertWithDel(l_accountProductOrderStopParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            l_validations.validatePTSAccountProductOrderStop(l_subAccount,90L,OrderTypeEnum.UNDEFINED);
            
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * validate訂正時注文Rev上限（PTS）
     * 訂正前注文単位 = null
     */
    public void test_validatePTSChangeOrderRevUpperLimit_0001()
    {
        final String STR_METHOD_NAME = "test_validatePTSChangeOrderRevUpperLimit_0001()";
        log.entering(STR_METHOD_NAME);
        EqTypeOrderUnit l_orderUnit = null;
        try
        {
            l_validations.validatePTSChangeOrderRevUpperLimit(
                    l_orderUnit,
                    90D,
                    3D,
                    EqTypeExecutionConditionType.AT_MARKET_CLOSE,
                    "");
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * validate訂正時注文Rev上限（PTS）
     * 訂正前注文単位 != null
     * 正しい
     */
    public void test_validatePTSChangeOrderRevUpperLimit_0002()
    {
        final String STR_METHOD_NAME = "test_validatePTSChangeOrderRevUpperLimit_0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,12);// 注意月?要減1
            Date date = ca.getTime();
            Timestamp st = new Timestamp(date.getTime());
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.tc." + 
//                    "gentrade.stdimpls.TradingSystemImpl", 
//                "getSystemTimestamp",
//                new Class[]{}, 
//                st);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate",
                    null);
                    
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    st);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setOrderRev("2");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            PersistenceManagerImpl l_persistenceManager = new PersistenceManagerImpl();
            EqTypeOrderUnitImpl l_orderUnit = new EqTypeOrderUnitImpl(l_persistenceManager,l_eqtypeOrderUnitParams); 
            l_validations.validatePTSChangeOrderRevUpperLimit(
                    l_orderUnit,
                    90D,
                    3D,
                    EqTypeExecutionConditionType.AT_MARKET_CLOSE,
                    "");
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * validate訂正時注文Rev上限（PTS）
     * 訂正前注文単位 != null
     * WEB3ErrorCatalog.BUSINESS_ERROR_02185
     */
    public void test_validatePTSChangeOrderRevUpperLimit_0003()
    {
        final String STR_METHOD_NAME = "test_validatePTSChangeOrderRevUpperLimit_0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,12);// 注意月?要減1
            Date date = ca.getTime();
            Timestamp st = new Timestamp(date.getTime());
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.tc." + 
//                    "gentrade.stdimpls.TradingSystemImpl", 
//                "getSystemTimestamp",
//                new Class[]{}, 
//                st);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate",
                    null);
                    
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    st);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setOrderRev("99");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParam = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParam.setInstitutionCode("0D");
            l_tradingTimeParam.setBranchCode("381");
            l_tradingTimeParam.setMarketCode("SP");
            l_tradingTimeParam.setTradingTimeType("26");
            l_tradingTimeParam.setProductCode("0");
            l_tradingTimeParam.setBizDateType("1");
            l_tradingTimeParam.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParam);
            
            PersistenceManagerImpl l_persistenceManager = new PersistenceManagerImpl();
            EqTypeOrderUnitImpl l_orderUnit = new EqTypeOrderUnitImpl(l_persistenceManager,l_eqtypeOrderUnitParams); 
            l_validations.validatePTSChangeOrderRevUpperLimit(
                    l_orderUnit,
                    90D,
                    3D,
                    EqTypeExecutionConditionType.AT_MARKET_CLOSE,
                    "");
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            QueryProcessor l_processor;
            try
            {
                l_processor = Processors.getDefaultProcessor();
                l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            }
            catch (DataException e)
            {
                // TODO Auto-generated catch block
                log.error("DataException:" + e);
            }
            
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02185, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * is権利落ち日（PTS）
     * true
     */
    public void test_isPTSDevidendRightDate_0001()
    {
        final String STR_METHOD_NAME = "test_isPTSDevidendRightDate_0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,12);// 注意月?要減1
            Date date = ca.getTime();
            Timestamp st = new Timestamp(date.getTime());
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.tc." + 
//                    "gentrade.stdimpls.TradingSystemImpl", 
//                "getSystemTimestamp",
//                new Class[]{}, 
//                st);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate",
                    null);
                    
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    st);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setOrderRev("99");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            PersistenceManagerImpl l_persistenceManager = new PersistenceManagerImpl();
            EqTypeOrderUnitImpl l_orderUnit = new EqTypeOrderUnitImpl(l_persistenceManager,l_eqtypeOrderUnitParams);
            
            Timestamp l_tsOrderBizDate = new Timestamp(WEB3DateUtility.getDate("20071218","yyyyMMdd").getTime());
            Timestamp l_tsYearlyBooksClosingDate  = new Timestamp(WEB3DateUtility.getDate("20071221","yyyyMMdd").getTime());
            boolean l_bln = l_validations.isPTSDevidendRightDate(l_tsOrderBizDate, l_tsYearlyBooksClosingDate);
            assertTrue(l_bln);
            
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * is権利落ち日（PTS）
     * false
     */
    public void test_isPTSDevidendRightDate_0002()
    {
        final String STR_METHOD_NAME = "test_isPTSDevidendRightDate_0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,12);// 注意月?要減1
            Date date = ca.getTime();
            Timestamp st = new Timestamp(date.getTime());
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.tc." + 
//                    "gentrade.stdimpls.TradingSystemImpl", 
//                "getSystemTimestamp",
//                new Class[]{}, 
//                st);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate",
                    null);
                    
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    st);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setOrderRev("99");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            PersistenceManagerImpl l_persistenceManager = new PersistenceManagerImpl();
            EqTypeOrderUnitImpl l_orderUnit = new EqTypeOrderUnitImpl(l_persistenceManager,l_eqtypeOrderUnitParams);
            
            Timestamp l_tsOrderBizDate = new Timestamp(WEB3DateUtility.getDate("20060715","yyyyMMdd").getTime());
            Timestamp l_tsYearlyBooksClosingDate  = new Timestamp(WEB3DateUtility.getDate("20060715","yyyyMMdd").getTime());
            boolean l_bln = l_validations.isPTSDevidendRightDate(l_tsOrderBizDate, l_tsYearlyBooksClosingDate);
            assertFalse(l_bln);
            
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * 呼値チェック（PTS）
     * 取引銘柄がnullの場合
     *
     */
    public void testIsPTSTickValueDef_0001()
    {
        final String STR_METHOD_NAME = "testIsPTSTickValueDef_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_validations.isPTSTickValueDef(null, 2.3);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 呼値チェック（PTS）
     * 注文単価 < 0の場合
     *
     */
    public void testIsPTSTickValueDef_0002()
    {
        final String STR_METHOD_NAME = "testIsPTSTickValueDef_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
            
            l_validations.isPTSTickValueDef(l_tradedProduct, -2.3);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 呼値チェック（PTS）
     * 指定された呼値が正しくありませんの場合
     *
     */
    public void testIsPTSTickValueDef_0003()
    {
        final String STR_METHOD_NAME = "testIsPTSTickValueDef_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
   
            l_validations.isPTSTickValueDef(l_tradedProduct, 22.5);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00030, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 呼値チェック（PTS）
     * 指値が刻み値の整数倍の場合
     *
     */
    public void testIsPTSTickValueDef_0004()
    {
        final String STR_METHOD_NAME = "testIsPTSTickValueDef_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityProductManager",
                    "getTickValue", new Class[]
                    { WEB3EquityTradedProduct.class, double.class },
                    new Double(2.5));
            
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
   
            assertTrue(l_validations.isPTSTickValueDef(l_tradedProduct, 22.5));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 呼値チェック（PTS）
     * 指値が刻み値の非整数倍の場合
     *
     */
    public void testIsPTSTickValueDef_0005()
    {
        final String STR_METHOD_NAME = "testIsPTSTickValueDef_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityProductManager",
                    "getTickValue", new Class[]
                    { WEB3EquityTradedProduct.class, double.class },
                    new Double(2.5));
            
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
   
            l_validations.isPTSTickValueDef(l_tradedProduct, 22);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00030, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 値幅チェック（PTS）
     * 指値が値幅の範囲内である場合
     * 
     */
    public void testIsPTSPriceRange_0001()
    {
        final String STR_METHOD_NAME = "testIsPTSPriceRange_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityProductManager",
                    "getTickValue", new Class[]
                    { WEB3EquityTradedProduct.class, double.class },
                    new Double(2.5));
            
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
            WEB3EquityPTSOrderManagerReusableValidationsForTest
            l_validations = new WEB3EquityPTSOrderManagerReusableValidationsForTest();
            l_validations.setCaseNum("1");
            assertTrue(l_validations.isPTSPriceRange(l_tradedProduct, 22));
            
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.entering(TEST_END + STR_METHOD_NAME);
        
    }
    
    /**
     * 値幅チェック（PTS）
     * 指値が値幅の範囲内でない場合
     * 
     */
    public void testIsPTSPriceRange_0002()
    {
        final String STR_METHOD_NAME = "testIsPTSPriceRange_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityProductManager",
                    "getTickValue", new Class[]
                    { WEB3EquityTradedProduct.class, double.class },
                    new Double(2.5));
            
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
            WEB3EquityPTSOrderManagerReusableValidationsForTest
            l_validations = new WEB3EquityPTSOrderManagerReusableValidationsForTest();
            l_validations.setCaseNum("2");
            l_validations.isPTSPriceRange(l_tradedProduct, 22);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00031, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.entering(TEST_END + STR_METHOD_NAME);
        
    }
    
    /**
     * 値幅チェック（PTS）
     * (基準値－値幅)≦0 且つ　@指値が値幅の範囲内でないの場合
     * 
     */
    public void testIsPTSPriceRange_0003()
    {
        final String STR_METHOD_NAME = "testIsPTSPriceRange_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityProductManager",
                    "getTickValue", new Class[]
                    { WEB3EquityTradedProduct.class, double.class },
                    new Double(2.5));
            
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
            WEB3EquityPTSOrderManagerReusableValidationsForTest
            l_validations = new WEB3EquityPTSOrderManagerReusableValidationsForTest();
            l_validations.setCaseNum("3");
            l_validations.isPTSPriceRange(l_tradedProduct, 0);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00031, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.entering(TEST_END + STR_METHOD_NAME);
        
    }
    
    /**
     * validate税区分（PTS）
     * is売注文 ＝ true 且つ　@税区分 ＝ ストックオプションの場合
     * 
     * 抛出異常信息：BUSINESS_ERROR_03069
     *
     */
    public void testValidatePTSTaxType_C0001()
    {
        final String STR_METHOD_NAME = "testValidatePTSTaxType_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3EquityPTSOrderManagerReusableValidations
            l_validations = new WEB3EquityPTSOrderManagerReusableValidations();
            
            l_validations.validatePTSTaxType(true,TaxTypeEnum.STOCK_OPTION);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03069, l_ex.getErrorInfo());
            
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate税区分（PTS）
     * is売注文 ＝ false
     * 
     * 正常返回
     *
     */
    public void testValidatePTSTaxType_C0002()
    {
        final String STR_METHOD_NAME = "testValidatePTSTaxType_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3EquityPTSOrderManagerReusableValidations
            l_validations = new WEB3EquityPTSOrderManagerReusableValidations();
            
            l_validations.validatePTSTaxType(false,TaxTypeEnum.STOCK_OPTION);
            assertTrue(true);
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
            
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }

}
@

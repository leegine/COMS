head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.57.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquitySellOrderInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (WEB3EquitySellOrderInputServiceImplTest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/12/25  崔遠鵬(中訊)　@新規作成
 */
package webbroker3.equity.service.delegate.stdimpls;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.equity.message.WEB3EquitySellInputRequest;
import webbroker3.equity.message.WEB3EquitySellInputResponse;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchMarketDealtCondParams;
import webbroker3.gentrade.data.BranchMarketDealtCondRow;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondParams;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondRow;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.OrderAcceptStatusParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquitySellOrderInputServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquitySellOrderInputServiceImplTest.class);

    private WEB3EquitySellOrderInputServiceImpl l_impl = new WEB3EquitySellOrderInputServiceImpl();

    private boolean l_blnIsValidateAccountForTrading = false;

    private boolean l_blnGetHandlingPossibleMarket = false;
    
    public WEB3EquitySellOrderInputServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

//    /**
//     * 「validate注文受付可能」をテスト 取引時間管理.validate注文受付可能()正常終了 引数.isPTS口座開設==true
//     * 
//     */
//    public void testValidateOrderAcceptC001()
//    {
//        final String STR_METHOD_NAME = "testValidateOrderAcceptC001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            // DBの準備
//            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
//            l_orderAcceptStatusParams.setOrderAccTransaction("07");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//
//            // スタティックの準備
//            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
//            l_clendarContext.setInstitutionCode("0D");
//            l_clendarContext.setBranchCode("381");
//            l_clendarContext.setTradingTimeType("123");
//            l_clendarContext.setOrderAcceptProduct("01");
//            l_clendarContext.setOrderAcceptTransaction("07");
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);
//
//
//            // テストされたメソッドを実行
//            l_impl.validateOrderAccept(true);
//            assertTrue(true);
//        }
//        catch (Exception l_ex)
//        {
//            fail();
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//        }
//        log.entering(TEST_END + STR_METHOD_NAME);
//    }
//
//    /**
//     * 「validate注文受付可能」をテスト 取引時間管理.validate注文受付可能()正常終了 引数.isPTS口座開設==false
//     */
//    public void testValidateOrderAcceptC002()
//    {
//        final String STR_METHOD_NAME = "testValidateOrderAcceptC002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            // DBの準備
//            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
//            l_orderAcceptStatusParams.setOrderAccTransaction("07");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//
//            // スタティックの準備
//            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
//            l_clendarContext.setInstitutionCode("0D");
//            l_clendarContext.setBranchCode("381");
//            l_clendarContext.setTradingTimeType("123");
//            l_clendarContext.setOrderAcceptProduct("01");
//            l_clendarContext.setOrderAcceptTransaction("07");
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);
//
//
//            // テストされたメソッドを実行
//            l_impl.validateOrderAccept(false);
//            assertTrue(true);
//        }
//        catch (Exception l_ex)
//        {
//            fail();
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//        }
//        log.entering(TEST_END + STR_METHOD_NAME);
//    }
//
//    /**
//     * 「validate注文受付可能」をテスト 取引時間管理.validate注文受付可能()に例外が発生した場合 引数.isPTS口座開設==true
//     */
//    public void testValidateOrderAcceptC003()
//    {
//        final String STR_METHOD_NAME = "testValidateOrderAcceptC003()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            // DBの準備
//            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
//            l_orderAcceptStatusParams.setOrderAccTransaction("07");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//
//            // スタティックの準備
//            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
//            l_clendarContext.setInstitutionCode("0D");
//            l_clendarContext.setBranchCode(null);
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);
//
//
//            // テストされたメソッドを実行
//            l_impl.validateOrderAccept(true);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            // 比較
//            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            fail();
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//        }
//        log.entering(TEST_END + STR_METHOD_NAME);
//    }
//
//    /**
//     * 「validate注文受付可能」をテスト 取引時間管理.validate注文受付可能()に例外が発生した場合
//     * 引数.isPTS口座開設==false
//     */
//    public void testValidateOrderAcceptC004()
//    {
//        final String STR_METHOD_NAME = "testValidateOrderAcceptC004()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            // DBの準備
//            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
//            l_orderAcceptStatusParams.setOrderAccTransaction("07");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//
//            // スタティックの準備
//            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
//            l_clendarContext.setInstitutionCode("0D");
//            l_clendarContext.setBranchCode(null);
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);
//
//            // テストされたメソッドを実行
//            l_impl.validateOrderAccept(false);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            // 比較
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//          assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.entering(TEST_END + STR_METHOD_NAME);
//    }

    /**
     *  get市場閉局警告市場をテスト 
         引数.isPTS口座開設==false
     */
    public void testGetTradeCloseMarketC001()
    {
        final String STR_METHOD_NAME = "testGetTradeCloseMarketC001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071225", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071225", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071225", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            // スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071225", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
                    l_tsOrderAcceptTime);

            // テストされたメソッドを実行
            String[] l_strTradeCloseMarkets = l_impl.getTradeCloseMarket(new WEB3GentradeBranch(33381L), false);

            // 比較
            assertNull(l_strTradeCloseMarkets);
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }

    /**
     * get市場閉局警告市場をテスト 取引時間管理.get市場閉局警告市場：沒有取到
     * 引数.isPTS口座開設==true
     * PTS取引時間管理.get市場閉局警告市場：11
     */
    public void testGetTradeCloseMarketC002()
    {
        final String STR_METHOD_NAME = "testGetTradeCloseMarketC002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngEquityMargin(1500);
            TestDBUtility.insertWithDel(l_branchParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071225", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071225", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071225", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            l_processor.doDeleteAllQuery(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams = TestDBUtility
                    .getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("11");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("3");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            // スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071225", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
                    l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setBizdateCalcParameter("3");
            l_clendarContext.setSubmitMarketTrigger("1");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            // テストされたメソッドを実行
            String[] l_strTradeCloseMarkets = l_impl.getTradeCloseMarket(new WEB3GentradeBranch(33381L), true);

            // 比較
            assertEquals("11", l_strTradeCloseMarkets[0]);
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 取引時間管理.get市場閉局警告市場：11 
     * 引数.isPTS口座開設==true 
     * PTS取引時間管理.get市場閉局警告市場：沒有取到
     */
    public void testGetTradeCloseMarketC003()
    {
        final String STR_METHOD_NAME = "testGetTradeCloseMarketC003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngEquityMargin(1500);
            TestDBUtility.insertWithDel(l_branchParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071225", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071225", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071225", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            l_processor.doDeleteAllQuery(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams = TestDBUtility
                    .getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);

            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("11");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("3");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            // スタティックの準備
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071225", "yyyyMMdd").getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
                    l_tsOrderAcceptTime);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setBizdateCalcParameter("3");
            l_clendarContext.setSubmitMarketTrigger("1");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            // テストされたメソッドを実行
            String[] l_strTradeCloseMarkets = l_impl.getTradeCloseMarket(new WEB3GentradeBranch(33381L), true);

            // 比較
            assertEquals("11", l_strTradeCloseMarkets[0]);
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }

    /**
     * get取扱可能市場をテスト 引数.isPTS口座開設==false
     */
    public void testGetHandlingPossibleMarketC001()
    {
        final String STR_METHOD_NAME = "testGetHandlingPossibleMarketC001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            l_processor.doDeleteAllQuery(BranchMarketDealtCondRow.TYPE);
            BranchMarketDealtCondParams l_branchMarketDealtCondParams = TestDBUtility.getBranchMarketDealCondRow();
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams);

            // テストされたメソッドを実行
            String[] l_strHandlingPossibleMarkets = l_impl.getHandlingPossibleMarket(new WEB3GentradeBranch(33381L),
                    false);

            // 比較
            assertEquals("1", l_strHandlingPossibleMarkets[0]);
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }

    /**
     * get取扱可能市場をテスト 引数.isPTS口座開設==true
     */
    public void testGetHandlingPossibleMarketC002()
    {
        final String STR_METHOD_NAME = "testGetHandlingPossibleMarketC002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            l_processor.doDeleteAllQuery(BranchMarketDealtCondRow.TYPE);
            BranchMarketDealtCondParams l_branchMarketDealtCondParams = TestDBUtility.getBranchMarketDealCondRow();
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams);

            l_processor.doDeleteAllQuery(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams = TestDBUtility
                    .getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketPtsDealtCondParams.setMarketCode("2");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);

            // テストされたメソッドを実行
            String[] l_strHandlingPossibleMarkets = l_impl.getHandlingPossibleMarket(new WEB3GentradeBranch(33381L),
                    true);

            // 比較
            assertEquals("1", l_strHandlingPossibleMarkets[0]);
            assertEquals("2", l_strHandlingPossibleMarkets[1]);
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }

    /**
     * isPTS口座開設をテスト trueの場合
     */
    public void testIsPTSAccountOpenC001()
    {
        final String STR_METHOD_NAME = "testIsPTSAccountOpenC001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            // テストされたメソッドを実行
            boolean l_blnIsPTSAccountOpen = l_impl.isPTSAccountOpen(new WEB3GentradeMainAccount(333812512246L));

            // 比較
            assertTrue(l_blnIsPTSAccountOpen);
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }

    /**
     * パラメータ値不正。
     * 
     * 抛出異常信息:SYSTEM_ERROR_80017
     * 
     */
    public void testIsPTSAccountOpenC002()
    {
        final String STR_METHOD_NAME = "testIsPTSAccountOpenC002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // DBの準備
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            // テストされたメソッドを実行
            boolean l_blnIsPTSAccountOpen = l_impl.isPTSAccountOpen(null);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }

    /**
     * get入力画面 
     * 取引時間管理.validate注文受付ステイタス
     */
    public void testGetSellInputScreen_0001()
    {
        final String STR_METHOD_NAME = "testGetSellInputScreen_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3EquitySellInputRequest l_sellInputRequest = new WEB3EquitySellInputRequest();
        l_sellInputRequest.id = "1001";
        l_sellInputRequest.marketCode = WEB3MarketCodeDef.JNX_PTS;

        MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(333812512246L));

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(AssetRow.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_assetParams);
            
            
            WEB3GentradeTradingClendarContext l_clendarContext = 
                new WEB3GentradeTradingClendarContext();
            
            l_clendarContext.setInstitutionCode("0D");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, 
                    l_clendarContext);

            l_impl.getSellInputScreen(l_sellInputRequest);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
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
     * get入力画面 validate取引可能顧客
     */
    public void testGetSellInputScreen_0002()
    {
        final String STR_METHOD_NAME = "testGetSellInputScreen_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3EquitySellInputRequest l_sellInputRequest = new WEB3EquitySellInputRequest();
        l_sellInputRequest.id = "1001";
        l_sellInputRequest.marketCode = null;

        MOCK_MANAGER.setIsMockUsed(true);
        this.l_blnIsValidateAccountForTrading = true;

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(333812512246L));

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setYellowCustomer("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(AssetRow.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_assetParams);

            l_impl = new WEB3EquitySellOrderInputServiceImplForTest();
            l_impl.getSellInputScreen(l_sellInputRequest);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00275, l_ex.getErrorInfo());
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
     * get入力画面 get取扱可能市場 0件時はエラー
     */
    public void testGetSellInputScreen_0003()
    {
        final String STR_METHOD_NAME = "testGetSellInputScreen_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3EquitySellInputRequest l_sellInputRequest = new WEB3EquitySellInputRequest();
        l_sellInputRequest.id = "1001";
        l_sellInputRequest.marketCode = null;

        MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("1D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setOrderAcceptProduct("0");
            l_clendarContext.setOrderAcceptTransaction("1");
            l_clendarContext.setTradingTimeType("01");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setBizDateType("5");
            l_clendarContext.setBizdateCalcParameter("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);

            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("1D");
            l_tradingTimeParams1.setBranchCode("381");
            l_tradingTimeParams1.setMarketCode("11");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setBizDateType("1");

            TestDBUtility.insertWithDel(l_tradingTimeParams1);

            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("1D");
            l_tradingTimeParams2.setBranchCode("381");
            l_tradingTimeParams2.setMarketCode("11");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setBizDateType("0");

            TestDBUtility.insertWithDel(l_tradingTimeParams2);

            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setInstitutionCode("1D");
            l_tradingTimeParams3.setBranchCode("381");
            l_tradingTimeParams3.setMarketCode("11");
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setProductCode("0");
            l_tradingTimeParams3.setBizDateType("5");

            TestDBUtility.insertWithDel(l_tradingTimeParams3);

            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setInstitutionCode("1D");
            l_tradingTimeParams4.setBranchCode("381");
            l_tradingTimeParams4.setMarketCode("11");
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setProductCode("0");
            l_tradingTimeParams4.setBizDateType("4");

            TestDBUtility.insertWithDel(l_tradingTimeParams4);

            TradingTimeParams l_tradingTimeParams5 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams5.setInstitutionCode("1D");
            l_tradingTimeParams5.setBranchCode("381");
            l_tradingTimeParams5.setMarketCode("11");
            l_tradingTimeParams5.setTradingTimeType("01");
            l_tradingTimeParams5.setProductCode("0");
            l_tradingTimeParams5.setBizDateType("3");

            TestDBUtility.insertWithDel(l_tradingTimeParams5);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(333812512246L));

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(AssetRow.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_assetParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            this.l_blnGetHandlingPossibleMarket = true;

            l_impl = new WEB3EquitySellOrderInputServiceImplForTest();
            l_impl.getSellInputScreen(l_sellInputRequest);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00643, l_ex.getErrorInfo());
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
     * リクエスト.市場コード != null かつ 市場 .isPTS市場()==falseの場合
     * 
     * get出来るまで注文==null
     * 
     */
    public void testGetSellInputScreen_0004()
    {
        final String STR_METHOD_NAME = "testGetSellInputScreen_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3EquitySellInputRequest l_sellInputRequest = new WEB3EquitySellInputRequest();
        l_sellInputRequest.id = "1001";
        l_sellInputRequest.marketCode = WEB3MarketCodeDef.JNX_PTS;

        MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("1D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setOrderAcceptProduct("0");
            l_clendarContext.setOrderAcceptTransaction("1");
            l_clendarContext.setTradingTimeType("01");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setBizDateType("5");
            l_clendarContext.setBizdateCalcParameter("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);

            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("1D");
            l_tradingTimeParams1.setBranchCode("381");
            l_tradingTimeParams1.setMarketCode("11");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setBizDateType("1");

            TestDBUtility.insertWithDel(l_tradingTimeParams1);

            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("1D");
            l_tradingTimeParams2.setBranchCode("381");
            l_tradingTimeParams2.setMarketCode("11");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setBizDateType("0");

            TestDBUtility.insertWithDel(l_tradingTimeParams2);

            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setInstitutionCode("1D");
            l_tradingTimeParams3.setBranchCode("381");
            l_tradingTimeParams3.setMarketCode("11");
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setProductCode("0");
            l_tradingTimeParams3.setBizDateType("5");

            TestDBUtility.insertWithDel(l_tradingTimeParams3);

            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setInstitutionCode("1D");
            l_tradingTimeParams4.setBranchCode("381");
            l_tradingTimeParams4.setMarketCode("11");
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setProductCode("0");
            l_tradingTimeParams4.setBizDateType("4");

            TestDBUtility.insertWithDel(l_tradingTimeParams4);

            TradingTimeParams l_tradingTimeParams5 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams5.setInstitutionCode("1D");
            l_tradingTimeParams5.setBranchCode("381");
            l_tradingTimeParams5.setMarketCode("11");
            l_tradingTimeParams5.setTradingTimeType("01");
            l_tradingTimeParams5.setProductCode("0");
            l_tradingTimeParams5.setBizDateType("3");

            TestDBUtility.insertWithDel(l_tradingTimeParams5);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(333812512246L));

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(AssetRow.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_assetParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            TestDBUtility.commit();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "validateTradedProduct", new Class[]
                    { EqTypeProduct.class, Market.class }, null);

            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", new Timestamp(
                    WEB3DateUtility.getDate("20071225", "yyyyMMdd").getTime()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getSystemTimestamp",
                    new Class[]
                    {}, new Timestamp(WEB3DateUtility.getDate("20071225", "yyyyMMdd").getTime()));

            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate", null);

            l_impl = new WEB3EquitySellOrderInputServiceImplForTest();
            
            WEB3EquitySellInputResponse l_response = l_impl.getSellInputScreen(l_sellInputRequest);
            
            String[] l_strMarketsSuspension = new String[]{ "9","10"};
            
            String[] l_strMarkets = new String[]{"11"};
            // get市場閉局警告市場()の戻り値配列
            assertTrue(Arrays.equals(l_strMarketsSuspension, l_response.messageSuspension));
            
            assertTrue(Arrays.equals(l_strMarkets, l_response.marketList));
        }
        catch (WEB3BaseException l_ex)
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
    
    /**
     * （リクエスト.市場コード==null） または
     * （リクエスト.市場コード != null かつ 市場 .isPTS市場()==true）の場合 
     * 
     * 
     */
    public void testGetSellInputScreen_0005()
    {
        final String STR_METHOD_NAME = "testGetSellInputScreen_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3EquitySellInputRequest l_sellInputRequest = new WEB3EquitySellInputRequest();
        l_sellInputRequest.id = "1001";
        l_sellInputRequest.marketCode = null;

        MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("1D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setOrderAcceptProduct("0");
            l_clendarContext.setOrderAcceptTransaction("1");
            l_clendarContext.setTradingTimeType("01");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setBizDateType("5");
            l_clendarContext.setBizdateCalcParameter("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);

            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("1D");
            l_tradingTimeParams1.setBranchCode("381");
            l_tradingTimeParams1.setMarketCode("11");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setBizDateType("1");

            TestDBUtility.insertWithDel(l_tradingTimeParams1);

            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("1D");
            l_tradingTimeParams2.setBranchCode("381");
            l_tradingTimeParams2.setMarketCode("11");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setBizDateType("0");

            TestDBUtility.insertWithDel(l_tradingTimeParams2);

            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setInstitutionCode("1D");
            l_tradingTimeParams3.setBranchCode("381");
            l_tradingTimeParams3.setMarketCode("11");
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setProductCode("0");
            l_tradingTimeParams3.setBizDateType("5");

            TestDBUtility.insertWithDel(l_tradingTimeParams3);

            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setInstitutionCode("1D");
            l_tradingTimeParams4.setBranchCode("381");
            l_tradingTimeParams4.setMarketCode("11");
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setProductCode("0");
            l_tradingTimeParams4.setBizDateType("4");

            TestDBUtility.insertWithDel(l_tradingTimeParams4);

            TradingTimeParams l_tradingTimeParams5 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams5.setInstitutionCode("1D");
            l_tradingTimeParams5.setBranchCode("381");
            l_tradingTimeParams5.setMarketCode("11");
            l_tradingTimeParams5.setTradingTimeType("01");
            l_tradingTimeParams5.setProductCode("0");
            l_tradingTimeParams5.setBizDateType("3");

            TestDBUtility.insertWithDel(l_tradingTimeParams5);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(333812512246L));

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(AssetRow.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_assetParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "validateTradedProduct", new Class[]
                    { EqTypeProduct.class, Market.class }, null);

            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", new Timestamp(
                    WEB3DateUtility.getDate("20071225", "yyyyMMdd").getTime()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getSystemTimestamp",
                    new Class[]
                    {}, new Timestamp(WEB3DateUtility.getDate("20071225", "yyyyMMdd").getTime()));

            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate", null);

            l_impl = new WEB3EquitySellOrderInputServiceImplForTest();
            
            WEB3EquitySellInputResponse l_response = l_impl.getSellInputScreen(l_sellInputRequest);
            
            String[] l_strMarketsSuspension = new String[]{ "9","10"};
            
            String[] l_strMarkets = new String[]{"11"};
            // get市場閉局警告市場()の戻り値配列
            assertTrue(Arrays.equals(l_strMarketsSuspension, l_response.messageSuspension));
            
            assertTrue(Arrays.equals(l_strMarkets, l_response.marketList));
        }
        catch (WEB3BaseException l_ex)
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
    

    /**
     * validate取引可能顧客
     參數顧客 == null，
     */
    public void testValidateAccountForTradingCase0001()
    {
        final String STR_METHOD_NAME = "testValidateAccountForTradingCase0001()";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnIsPTSAccountEstablished = false;
        try
        {
            WEB3GentradeMainAccount l_mainAccount = null;
            
            OrderValidationResult l_orderValidationResult =
                l_impl.validateAccountForTrading(l_mainAccount, l_blnIsPTSAccountEstablished, null);

            assertEquals(l_orderValidationResult.getProcessingResult().getErrorInfo(),
                WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate取引可能顧客
     * 注文チェック.validate取引可能顧客(顧客、発注日)をコールする。 
     */
    public void testValidateAccountForTradingCase0002()
    {
        final String STR_METHOD_NAME = "testValidateAccountForTradingCase0002()";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnIsPTSAccountEstablished = true;
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    OrderValidationResult.VALIDATION_OK_RESULT);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_MarketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesRow.setName("equity.pts.market.div");
            l_MarketPreferencesRow.setValue("1");
            TestDBUtility.insertWithDel(l_MarketPreferencesRow);
            
            Date l_datsystime = WEB3DateUtility.getDate("20071225", "yyyyMMdd");
                //Calendar.getInstance().getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datsystime);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(l_mainAccountParams.getAccountId());
            
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            OrderValidationResult l_orderValidationResult =
                l_impl.validateAccountForTrading(l_mainAccount, l_blnIsPTSAccountEstablished, "1");

            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class});
            
            assertEquals(new Long(333812512246L) + "", 
                    ((WEB3GentradeMainAccount)l_paramsValue2.getFirstCalled()[0]).getAccountId() + "");
            
            assertEquals(WEB3DateUtility.formatDate(l_datsystime, "yyyyMMdd"), 
                    WEB3DateUtility.formatDate((Date)l_paramsValue2.getFirstCalled()[1], "yyyyMMdd"));
            
            assertEquals(l_orderValidationResult, OrderValidationResult.VALIDATION_OK_RESULT);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate取引可能顧客
     * 注文チェック.validate取引可能顧客(顧客)をコールする。 
     */
    public void testValidateAccountForTradingCase0003()
    {
        final String STR_METHOD_NAME = "testValidateAccountForTradingCase0003()";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnIsPTSAccountEstablished = true;
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    OrderValidationResult.VALIDATION_OK_RESULT);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            Date l_datsystime = WEB3DateUtility.getDate("20071225", "yyyyMMdd");
                //Calendar.getInstance().getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datsystime);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(l_mainAccountParams.getAccountId());
            
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            OrderValidationResult l_orderValidationResult =
                l_impl.validateAccountForTrading(l_mainAccount, l_blnIsPTSAccountEstablished, null);

            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class});

            assertEquals(new Long(333812512246L) + "", 
                    ((WEB3GentradeMainAccount)l_paramsValue1.getFirstCalled()[0]).getAccountId() + "");
            
            assertEquals(l_orderValidationResult, OrderValidationResult.VALIDATION_OK_RESULT);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate取引可能顧客
       注文チェック.validate取引可能顧客(顧客、発注日)をコールする。
       PTS取引時間管理.get発注日()  抛異常
     */
    public void testValidateAccountForTradingCase0004()
    {
        final String STR_METHOD_NAME = "testValidateAccountForTradingCase0004()";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnIsPTSAccountEstablished = true;
        try
        {
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_MarketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesRow.setName("equity.pts.market.div");
            l_MarketPreferencesRow.setValue("1");
            TestDBUtility.insertWithDel(l_MarketPreferencesRow);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(l_mainAccountParams.getAccountId());
            
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            OrderValidationResult l_orderValidationResult =
                l_impl.validateAccountForTrading(l_mainAccount, l_blnIsPTSAccountEstablished, "1");
            
            assertEquals(l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     validate顧客銘柄別取引停止 
     補助口座 == null，
     */
    public void testValidateAccountProductOrderStopCase0001()
    {
        final String STR_METHOD_NAME = "testValidateAccountProductOrderStopCase0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeSubAccount l_subAccount = null;
            long l_lngProductId = 12345678;
            l_impl.validateAccountProductOrderStop(l_subAccount, l_lngProductId, false, null);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     validate顧客銘柄別取引停止
     PTS注文マネージャ.validate顧客銘柄別取引停止(PTS)()をコールする。
     */
    public void testValidateAccountProductOrderStopCase0002()
    {
        final String STR_METHOD_NAME = "testValidateAccountProductOrderStopCase0002()";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnIsPTSAccountEstablished = true;
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_MarketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesRow.setName("equity.pts.market.div");
            l_MarketPreferencesRow.setValue("1");
            TestDBUtility.insertWithDel(l_MarketPreferencesRow);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                null);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    101001010010L, 10100101001007L);
            
            long l_lngProductId = 12345678;
            l_impl.validateAccountProductOrderStop(
                l_subAccount, l_lngProductId, l_blnIsPTSAccountEstablished, "1");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
        validate顧客銘柄別取引停止 
        PTS注文マネージャ.validate顧客銘柄別取引停止(PTS)()をコールする抛異常
     */
    public void testValidateAccountProductOrderStopCase0003()
    {
        final String STR_METHOD_NAME = "testValidateAccountProductOrderStopCase0003()";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnIsPTSAccountEstablished = true;
        MOCK_MANAGER.setIsMockUsed(true);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityPTSOrderManager",
            "validatePTSAccountProductOrderStop",
            new Class[]{ SubAccount.class, long.class, OrderTypeEnum.class },
            new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ""));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_MarketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesRow.setName("equity.pts.market.div");
            l_MarketPreferencesRow.setValue("1");
            TestDBUtility.insertWithDel(l_MarketPreferencesRow);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    101001010010L, 10100101001007L);
            
            long l_lngProductId = 12345678;
            l_impl.validateAccountProductOrderStop(l_subAccount, l_lngProductId, l_blnIsPTSAccountEstablished, "1");
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "validatePTSAccountProductOrderStop",
                    new Class[]{ SubAccount.class, long.class, OrderTypeEnum.class });

                assertEquals(101001010010L, ((WEB3GentradeSubAccount)l_paramsValue2.getFirstCalled()[0]).getAccountId());
                
                assertEquals(new Long(12345678), l_paramsValue2.getFirstCalled()[1]);

                assertEquals(2, ((OrderTypeEnum)l_paramsValue2.getFirstCalled()[2]).intValue());
            
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
        validate顧客銘柄別取引停止 
        PTS注文マネージャ.拡張株式注文マネージャ.validate顧客銘柄別取引停止()をコールする。 
     */
    public void testValidateAccountProductOrderStopCase0004()
    {
        final String STR_METHOD_NAME = "testValidateAccountProductOrderStopCase0004()";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnIsPTSAccountEstablished = false;
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateAccountProductOrderStop",
            new Class[] {SubAccount.class,long.class, OrderTypeEnum.class},
            null);        
        try
        {
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    101001010010L, 10100101001007L);
            
            long l_lngProductId = 12345678;
            l_impl.validateAccountProductOrderStop(l_subAccount, l_lngProductId, l_blnIsPTSAccountEstablished, null);
            
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateAccountProductOrderStop",
                new Class[] {SubAccount.class,long.class, OrderTypeEnum.class});

            assertEquals(101001010010L, ((WEB3GentradeSubAccount)l_paramsValue1.getFirstCalled()[0]).getAccountId());
            
            assertEquals(new Long(12345678), l_paramsValue1.getFirstCalled()[1]);

            assertEquals(2, ((OrderTypeEnum)l_paramsValue1.getFirstCalled()[2]).intValue());

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
        validate顧客銘柄別取引停止 
        PTS注文マネージャ.拡張株式注文マネージャ.validate顧客銘柄別取引停止()をコールする抛異常
     */
    public void testValidateAccountProductOrderStopCase0005()
    {
        final String STR_METHOD_NAME = "testValidateAccountProductOrderStopCase0005()";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnIsPTSAccountEstablished = false;
        MOCK_MANAGER.setIsMockUsed(true);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateAccountProductOrderStop",
            new Class[] {SubAccount.class,long.class, OrderTypeEnum.class},
            new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, ""));
        
        try
        {
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    101001010010L, 10100101001007L);

            long l_lngProductId = 12345678;
            l_impl.validateAccountProductOrderStop(l_subAccount, l_lngProductId, l_blnIsPTSAccountEstablished, "1");
            fail();

        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80006);

            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateAccountProductOrderStop",
                new Class[] {SubAccount.class,long.class, OrderTypeEnum.class});

            assertEquals(101001010010L, ((WEB3GentradeSubAccount)l_paramsValue1.getFirstCalled()[0]).getAccountId());
            
            assertEquals(new Long(12345678), l_paramsValue1.getFirstCalled()[1]);

            assertEquals(2, ((OrderTypeEnum)l_paramsValue1.getFirstCalled()[2]).intValue());
            
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * mergeAndSort
     * 
     */
    public void testMergeAndSort()
    {
        final String STR_METHOD_NAME = "testMergeAndSort()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Method l_method = WEB3EquitySellOrderInputServiceImpl.class.getDeclaredMethod("mergeAndSort", new Class[]
            { String[].class, String[].class });
            l_method.setAccessible(true);
            String[] l_strA =
            { "1", "2", "5" };
            String[] l_strB =
            { "2", "3", "6" };
            String[] l_strResults = (String[]) l_method.invoke(l_impl, new Object[]
            { l_strA, l_strB });

            assertEquals("5", l_strResults.length + "");
            assertEquals("1", l_strResults[0]);
            assertEquals("2", l_strResults[1]);
            assertEquals("3", l_strResults[2]);
            assertEquals("5", l_strResults[3]);
            assertEquals("6", l_strResults[4]);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    private class WEB3EquitySellOrderInputServiceImplForTest extends WEB3EquitySellOrderInputServiceImpl
    {
        protected void validateOrderAccept(boolean l_blnIsPTSAccountEstablished) throws WEB3BaseException
        {

        }

        protected OrderValidationResult validateAccountForTrading(WEB3GentradeMainAccount l_mainAccount,
                boolean l_blnIsPTSAccountEstablished)
        {
            if (l_blnIsValidateAccountForTrading)
            {
                return new OrderValidationResult(ProcessingResult
                        .newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00275));
            }
            return OrderValidationResult.VALIDATION_OK_RESULT;

        }

        protected String[] getHandlingPossibleMarket(WEB3GentradeBranch l_branch, boolean l_blnIsPTSAccountEstablished)
                throws WEB3BaseException
        {
            if (l_blnGetHandlingPossibleMarket)
            {
                String[] l_strMarkets = new String[0];
                return l_strMarkets;
            }

            String[] l_strMarkets = new String[]
            { "11" };
            return l_strMarkets;
        }
        
        protected String[] getTradeCloseMarket(
                WEB3GentradeBranch l_branch,
                boolean l_blnIsPTSAccountEstablished)throws WEB3BaseException
        {
            String[] l_strMarkets = new String[]{ "9","10"};
            return l_strMarkets;
        }

        protected String getEstimatedBookPrice(WEB3EquitySellInputRequest l_request) throws WEB3BaseException
        {
            return "6000";
        }
    }
}
@

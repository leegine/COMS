head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.54.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityMarginExecuteReferenceServiceImplTest_cyp.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImpl.OrderStatus;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.OrderAcceptStatusParams;
import webbroker3.gentrade.data.OrderAcceptStatusRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityMarginExecuteReferenceServiceImplTest_cyp extends TestBaseForMock
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3EquityMarginExecuteReferenceServiceImplTest_cyp.class);

    WEB3EquityMarginExecuteReferenceServiceImpl l_serviceImpl = null;

    WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();

    public WEB3EquityMarginExecuteReferenceServiceImplTest_cyp(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_serviceImpl = new WEB3EquityMarginExecuteReferenceServiceImpl();

        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("123");
        l_clendarContext.setMarketCode("N1");
        l_clendarContext.setProductCode("12");
        l_clendarContext.setTradingTimeType("01");
        l_clendarContext.setOrderAcceptProduct("01");
        l_clendarContext.setBizDateType("1");

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_clendarContext);

        Calendar l_calendar = Calendar.getInstance();
        l_calendar.set(Calendar.HOUR_OF_DAY,15);
        l_calendar.set(Calendar.MINUTE,00);
        l_calendar.set(Calendar.SECOND,01);

        Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        TestDBUtility.insertWithDel(l_mainAccountParams);

        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        TestDBUtility.insertWithDel(l_subAccountParams);
        l_subAccountParams.setSubAccountId(l_subAccountParams.getSubAccountId() + 1);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        TestDBUtility.insertWithDel(l_subAccountParams);

        TestDBUtility.deleteAll(OrderAcceptStatusRow.TYPE);
        TestDBUtility.deleteAll(TradingTimeRow.TYPE);

        WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsBizDate,"1");

    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImpl.validateOrderAccept(String, String, boolean, boolean)'
     */
    public void test_validateOrderAccept_C0001()
    {
        final String STR_METHOD_NAME = "test_validateOrderAccept_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //1： 訂正取消一覧 
        String l_strReferenceType = "1";
        //0： 現物株式、信用取引 すべて 
        String l_strProductType = "0";
        boolean l_blnIsMargin = true;
        boolean l_blnIsOffFloorExecute = true;

        try
        {
            //注文受付トランザクション 05：訂正
            l_clendarContext.setOrderAcceptTransaction("05");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());

            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams =
                TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”訂正”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”立会外分売”
            //注文受付商品： ”立会外分売”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("25");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”株式・信用”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”訂正”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("05");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”株式・信用”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”現引・現渡”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            OrderStatus l_orderStatus = l_serviceImpl.validateOrderAccept(
                l_strReferenceType,
                l_strProductType,
                l_blnIsMargin,
                l_blnIsOffFloorExecute);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00011, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImpl.validateOrderAccept(String, String, boolean, boolean)'
     */
    public void test_validateOrderAccept_C0002()
    {
        final String STR_METHOD_NAME = "test_validateOrderAccept_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //1： 訂正取消一覧
        String l_strReferenceType = "1";
        //0： 現物株式、信用取引 すべて
        String l_strProductType = "0";
        boolean l_blnIsMargin = true;
        boolean l_blnIsOffFloorExecute = true;
        
        try
        {
            //注文受付トランザクション 05：訂正
            l_clendarContext.setOrderAcceptTransaction("05");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());

            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(OrderAcceptStatusRow.TYPE);
            OrderAcceptStatusParams l_orderAcceptStatusParams =
                TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
//            //受付時間区分： ”株式・信用”
//            //注文受付商品： ”株式”
//            //注文受付トランザクション： ”訂正”
//            //”受付可”
//            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//
//
//            //受付時間区分： ”株式・信用”
//            //注文受付商品： ”株式”
//            //注文受付トランザクション： ”取消”
//            //”受付可”
//            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
//            l_orderAcceptStatusParams.setOrderAccTransaction("06");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//
//            //受付時間区分： ”立会外分売”
//            //注文受付商品： ”立会外分売”
//            //注文受付トランザクション： ”取消”
//            //”受付可”
//            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
//            l_orderAcceptStatusParams.setOrderAccProduct("25");
//            l_orderAcceptStatusParams.setOrderAccTransaction("06");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//            l_tradingTimeParams.setTradingTimeType("03");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//
//            //受付時間区分： ”株式・信用”
//            //注文受付商品： ”信用取引”
//            //注文受付トランザクション： ”訂正”
//            //”受付可”
//            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
//            l_orderAcceptStatusParams.setOrderAccProduct("03");
//            l_orderAcceptStatusParams.setOrderAccTransaction("05");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//
//            //受付時間区分： ”株式・信用”
//            //注文受付商品： ”信用取引”
//            //注文受付トランザクション： ”取消”
//            //”受付可”
//            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”現引・現渡”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”取消”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            
            //注文受付トランザクション： ”訂正”
            l_orderAcceptStatusParams.setOrderAccTransaction("05");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            l_tradingTimeParams.setTradingTimeType("19");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderStatus l_orderStatus = l_serviceImpl.validateOrderAccept(
                l_strReferenceType,
                l_strProductType,
                l_blnIsMargin,
                l_blnIsOffFloorExecute);
            assertTrue(l_orderStatus.interestEquityFlag);
            assertTrue(l_orderStatus.offFloor);
            assertTrue(l_orderStatus.interestMarginFlag);
            assertFalse(l_orderStatus.swapFlag);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImpl.validateOrderAccept(String, String, boolean, boolean)'
     */
    public void test_validateOrderAccept_C0003()
    {
        final String STR_METHOD_NAME = "test_validateOrderAccept_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //1： 訂正取消一覧
        String l_strReferenceType = "1";
        // 1： 現物株式
        String l_strProductType = "1";
        boolean l_blnIsMargin = true;
        boolean l_blnIsOffFloorExecute = true;

        try
        {
            //注文受付トランザクション 05：訂正
            l_clendarContext.setOrderAcceptTransaction("05");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());

            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams =
                TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”訂正”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);


            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”立会外分売”
            //注文受付商品： ”立会外分売”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("25");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            l_tradingTimeParams.setTradingTimeType("03");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            //受付時間区分： ”株式・信用”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”訂正”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("05");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”株式・信用”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”取消”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”現引・現渡”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”取消”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            l_tradingTimeParams.setTradingTimeType("19");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderStatus l_orderStatus = l_serviceImpl.validateOrderAccept(
                l_strReferenceType,
                l_strProductType,
                l_blnIsMargin,
                l_blnIsOffFloorExecute);
            assertTrue(l_orderStatus.interestEquityFlag);
            assertFalse(l_orderStatus.offFloor);
            assertFalse(l_orderStatus.interestMarginFlag);
            assertFalse(l_orderStatus.swapFlag);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImpl.validateOrderAccept(String, String, boolean, boolean)'
     */
    public void test_validateOrderAccept_C0004()
    {
        final String STR_METHOD_NAME = "test_validateOrderAccept_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //1： 訂正取消一覧 
        String l_strReferenceType = "1";
        // 1： 現物株式 
        String l_strProductType = "1";
        boolean l_blnIsMargin = true;
        boolean l_blnIsOffFloorExecute = true;

        try
        {
            //注文受付トランザクション 05：訂正
            l_clendarContext.setOrderAcceptTransaction("05");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());

            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams =
                TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”訂正”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);


            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”取消”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”立会外分売”
            //注文受付商品： ”立会外分売”
            //注文受付トランザクション： ”取消”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("25");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            l_tradingTimeParams.setTradingTimeType("03");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            //受付時間区分： ”株式・信用”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”訂正”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("05");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”株式・信用”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”取消”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”現引・現渡”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”取消”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            l_tradingTimeParams.setTradingTimeType("19");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderStatus l_orderStatus = l_serviceImpl.validateOrderAccept(
                l_strReferenceType,
                l_strProductType,
                l_blnIsMargin,
                l_blnIsOffFloorExecute);
            assertTrue(l_orderStatus.interestEquityFlag);
            assertTrue(l_orderStatus.offFloor);
            assertFalse(l_orderStatus.interestMarginFlag);
            assertFalse(l_orderStatus.swapFlag);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImpl.validateOrderAccept(String, String, boolean, boolean)'
     */
    public void test_validateOrderAccept_C0005()
    {
        final String STR_METHOD_NAME = "test_validateOrderAccept_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //1： 訂正取消一覧 
        String l_strReferenceType = "1";
        // 1： 現物株式 
        String l_strProductType = "1";
        boolean l_blnIsMargin = true;
        boolean l_blnIsOffFloorExecute = false;

        try
        {
            //注文受付トランザクション 05：訂正
            l_clendarContext.setOrderAcceptTransaction("05");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());

            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams =
                TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”訂正”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);


            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”立会外分売”
            //注文受付商品： ”立会外分売”
            //注文受付トランザクション： ”取消”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("25");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            l_tradingTimeParams.setTradingTimeType("03");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            //受付時間区分： ”株式・信用”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”訂正”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("05");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”株式・信用”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”取消”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”現引・現渡”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”取消”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            l_tradingTimeParams.setTradingTimeType("19");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderStatus l_orderStatus = l_serviceImpl.validateOrderAccept(
                l_strReferenceType,
                l_strProductType,
                l_blnIsMargin,
                l_blnIsOffFloorExecute);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00011, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImpl.validateOrderAccept(String, String, boolean, boolean)'
     */
    public void test_validateOrderAccept_C0006()
    {
        final String STR_METHOD_NAME = "test_validateOrderAccept_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //1： 訂正取消一覧 
        String l_strReferenceType = "1";
        // 2： 信用取引 
        String l_strProductType = "2";
        boolean l_blnIsMargin = true;
        boolean l_blnIsOffFloorExecute = false;

        try
        {
            //注文受付トランザクション 05：訂正
            l_clendarContext.setOrderAcceptTransaction("05");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());

            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams =
                TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”訂正”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);


            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”立会外分売”
            //注文受付商品： ”立会外分売”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("25");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            l_tradingTimeParams.setTradingTimeType("03");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            //受付時間区分： ”株式・信用”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”訂正”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("05");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”株式・信用”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”現引・現渡”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”取消”
            //”受付可”
            //l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("05");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            l_tradingTimeParams.setTradingTimeType("19");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderStatus l_orderStatus = l_serviceImpl.validateOrderAccept(
                l_strReferenceType,
                l_strProductType,
                l_blnIsMargin,
                l_blnIsOffFloorExecute);
            assertFalse(l_orderStatus.interestEquityFlag);
            assertFalse(l_orderStatus.offFloor);
            assertTrue(l_orderStatus.interestMarginFlag);
            assertFalse(l_orderStatus.swapFlag);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImpl.validateOrderAccept(String, String, boolean, boolean)'
     */
    public void test_validateOrderAccept_C0007()
    {
        final String STR_METHOD_NAME = "test_validateOrderAccept_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //1： 訂正取消一覧 
        String l_strReferenceType = "1";
        // 2： 信用取引 
        String l_strProductType = "2";
        boolean l_blnIsMargin = true;
        boolean l_blnIsOffFloorExecute = false;

        try
        {
            //注文受付トランザクション 05：訂正
            l_clendarContext.setOrderAcceptTransaction("05");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());

            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(OrderAcceptStatusRow.TYPE);
            OrderAcceptStatusParams l_orderAcceptStatusParams =
                TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”訂正”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);


            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”立会外分売”
            //注文受付商品： ”立会外分売”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("25");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            l_tradingTimeParams.setTradingTimeType("03");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            //受付時間区分： ”株式・信用”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”訂正”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("05");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”株式・信用”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”取消”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”現引・現渡”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            //注文受付商品と注文受付トランザクションの値が、信用取引の取消場合と同じですから.
            //注文受付ステイタステーブルは”受付不可”を設定できない.
            //それから、取引時間テーブルに "現引・現渡"のレコードを作成しない。
            //l_tradingTimeParams.setTradingTimeType("19");
            //TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderStatus l_orderStatus = l_serviceImpl.validateOrderAccept(
                l_strReferenceType,
                l_strProductType,
                l_blnIsMargin,
                l_blnIsOffFloorExecute);
            log.debug("l_orderStatus.interestEquityFlag = " + l_orderStatus.interestEquityFlag);
            log.debug("l_orderStatus.offFloor = " + l_orderStatus.offFloor);
            log.debug("l_orderStatus.interestMarginFlag = " + l_orderStatus.interestMarginFlag);
            log.debug("l_orderStatus.swapFlag = " + l_orderStatus.swapFlag);
            
            assertFalse(l_orderStatus.interestEquityFlag);
            assertFalse(l_orderStatus.offFloor);
            assertTrue(l_orderStatus.interestMarginFlag);
            assertFalse(l_orderStatus.swapFlag);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImpl.validateOrderAccept(String, String, boolean, boolean)'
     */
    public void test_validateOrderAccept_C0008()
    {
        final String STR_METHOD_NAME = "test_validateOrderAccept_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //1： 訂正取消一覧
        String l_strReferenceType = "1";
        // 2： 信用取引 
        String l_strProductType = "2";
        boolean l_blnIsMargin = true;
        boolean l_blnIsOffFloorExecute = false;

        try
        {
            //注文受付トランザクション 05：訂正
            l_clendarContext.setOrderAcceptTransaction("05");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());

            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams =
                TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”訂正”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);


            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”立会外分売”
            //注文受付商品： ”立会外分売”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("25");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            l_tradingTimeParams.setTradingTimeType("03");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            //受付時間区分： ”株式・信用”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”訂正”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("05");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”株式・信用”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”現引・現渡”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            l_tradingTimeParams.setTradingTimeType("19");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderStatus l_orderStatus = l_serviceImpl.validateOrderAccept(
                l_strReferenceType,
                l_strProductType,
                l_blnIsMargin,
                l_blnIsOffFloorExecute);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00011, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImpl.validateOrderAccept(String, String, boolean, boolean)'
     */
    public void test_validateOrderAccept_C0009()
    {
        final String STR_METHOD_NAME = "test_validateOrderAccept_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        //1： 訂正取消一覧
        String l_strReferenceType = "1";
        // 2： 信用取引
        String l_strProductType = "1";
        boolean l_blnIsMargin = false;
        boolean l_blnIsOffFloorExecute = false;

        try
        {
            //注文受付トランザクション 05：訂正
            l_clendarContext.setOrderAcceptTransaction("05");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());

            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams =
                TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”訂正”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);


            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”取消”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”立会外分売”
            //注文受付商品： ”立会外分売”
            //注文受付トランザクション： ”取消”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("25");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            l_tradingTimeParams.setTradingTimeType("03");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            //受付時間区分： ”株式・信用”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”訂正”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("05");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”株式・信用”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”取消”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”現引・現渡”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”取消”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            l_tradingTimeParams.setTradingTimeType("19");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderStatus l_orderStatus = l_serviceImpl.validateOrderAccept(
                l_strReferenceType,
                l_strProductType,
                l_blnIsMargin,
                l_blnIsOffFloorExecute);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00011, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImpl.validateOrderAccept(String, String, boolean, boolean)'
     */
    public void test_validateOrderAccept_C0010()
    {
        final String STR_METHOD_NAME = "test_validateOrderAccept_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //0： 注文約定照会
        String l_strReferenceType = "0";
        //0： 現物株式、信用取引 すべて
        String l_strProductType = "0";
        boolean l_blnIsMargin = true;
        boolean l_blnIsOffFloorExecute = true;

        try
        {

            l_clendarContext.setOrderAcceptTransaction("07");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());

            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams =
                TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”照会”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);


            //注文受付商品： ”立会外分売”
            //注文受付トランザクション： ”照会”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("25");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”照会”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            OrderStatus l_orderStatus = l_serviceImpl.validateOrderAccept(
                l_strReferenceType,
                l_strProductType,
                l_blnIsMargin,
                l_blnIsOffFloorExecute);

            assertTrue(l_orderStatus.interestEquityFlag);
            assertTrue(l_orderStatus.offFloor);
            assertTrue(l_orderStatus.interestMarginFlag);
            assertTrue(l_orderStatus.swapFlag);

        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_validateOrderAccept_C0011()
    {
        final String STR_METHOD_NAME = "test_validateOrderAccept_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //0： 注文約定照会
        String l_strReferenceType = "0";
        //1： 現物株式
        String l_strProductType = "1";
        boolean l_blnIsMargin = true;
        boolean l_blnIsOffFloorExecute = true;

        try
        {
            l_clendarContext.setOrderAcceptTransaction("07");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());

            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams =
                TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”照会”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);


            //注文受付商品： ”立会外分売”
            //注文受付トランザクション： ”照会”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("25");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”照会”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            OrderStatus l_orderStatus = l_serviceImpl.validateOrderAccept(
                l_strReferenceType,
                l_strProductType,
                l_blnIsMargin,
                l_blnIsOffFloorExecute);

            assertFalse(l_orderStatus.interestEquityFlag);
            assertTrue(l_orderStatus.offFloor);
            assertFalse(l_orderStatus.interestMarginFlag);
            assertFalse(l_orderStatus.swapFlag);

        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_validateOrderAccept_C0012()
    {
        final String STR_METHOD_NAME = "test_validateOrderAccept_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        //0： 注文約定照会
        String l_strReferenceType = "0";
        //1： 現物株式
        String l_strProductType = "1";
        boolean l_blnIsMargin = true;
        boolean l_blnIsOffFloorExecute = false;

        try
        {

            l_clendarContext.setOrderAcceptTransaction("07");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());

            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams =
                TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”照会”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);


            //注文受付商品： ”立会外分売”
            //注文受付トランザクション： ”照会”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("25");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”照会”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            OrderStatus l_orderStatus = l_serviceImpl.validateOrderAccept(
                l_strReferenceType,
                l_strProductType,
                l_blnIsMargin,
                l_blnIsOffFloorExecute);

            assertTrue(l_orderStatus.interestEquityFlag);
            assertFalse(l_orderStatus.offFloor);
            assertFalse(l_orderStatus.interestMarginFlag);
            assertFalse(l_orderStatus.swapFlag);

        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_validateOrderAccept_C0013()
    {
        final String STR_METHOD_NAME = "test_validateOrderAccept_C0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        //0： 注文約定照会
        String l_strReferenceType = "0";
        //1： 現物株式
        String l_strProductType = "1";
        boolean l_blnIsMargin = true;
        boolean l_blnIsOffFloorExecute = true;

        try
        {

            l_clendarContext.setOrderAcceptTransaction("07");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());

            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams =
                TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”照会”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);


            //注文受付商品： ”立会外分売”
            //注文受付トランザクション： ”照会”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("25");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”照会”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            OrderStatus l_orderStatus = l_serviceImpl.validateOrderAccept(
                l_strReferenceType,
                l_strProductType,
                l_blnIsMargin,
                l_blnIsOffFloorExecute);
            fail();

        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00011, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_validateOrderAccept_C0014()
    {
        final String STR_METHOD_NAME = "test_validateOrderAccept_C0014()";
        log.entering(TEST_START + STR_METHOD_NAME);
        //0： 注文約定照会
        String l_strReferenceType = "0";
        //2： 信用取引
        String l_strProductType = "1";
        boolean l_blnIsMargin = false;
        boolean l_blnIsOffFloorExecute = false;

        try
        {
			l_clendarContext.setOrderAcceptTransaction("07");
			ThreadLocalSystemAttributesRegistry.setAttribute(
					WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_clendarContext);

			TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
			l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
			l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
			l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
			l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
			l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
			l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());

			TestDBUtility.insertWithDel(l_tradingTimeParams);

			OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
			l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
			l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
			l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
			l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
			l_orderAcceptStatusParams.setOrderAcceptStatus("2");
			// 受付時間区分： ”株式・信用”
			// 注文受付商品： ”株式”
			// 注文受付トランザクション： ”照会”
			// ”受付不可”
//			l_orderAcceptStatusParams.setOrderAcceptStatus("1");

//			// 注文受付商品： ”立会外分売”
//			// 注文受付トランザクション： ”照会”
//			// ”受付不可”
//			l_orderAcceptStatusParams.setOrderAcceptStatus("1");
//			l_orderAcceptStatusParams.setOrderAccProduct("25");
			TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

			// 注文受付商品： ”信用取引”
			// 注文受付トランザクション： ”照会”
			// ”受付可”
			l_orderAcceptStatusParams.setOrderAcceptStatus("0");
			l_orderAcceptStatusParams.setOrderAccProduct("03");
			TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

			OrderStatus l_orderStatus = l_serviceImpl.validateOrderAccept(l_strReferenceType, l_strProductType,
					l_blnIsMargin, l_blnIsOffFloorExecute);
			fail();
			}
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00012, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_validateOrderAccept_C0015()
    {
        final String STR_METHOD_NAME = "test_validateOrderAccept_C0015()";
        log.entering(TEST_START + STR_METHOD_NAME);
        //0： 注文約定照会
        String l_strReferenceType = "0";
        //2： 信用取引
        String l_strProductType = "2";
        boolean l_blnIsMargin = true;
        boolean l_blnIsOffFloorExecute = false;

        try
        {

            l_clendarContext.setOrderAcceptTransaction("07");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());

            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams =
                TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”照会”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);


            //注文受付商品： ”立会外分売”
            //注文受付トランザクション： ”照会”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("25");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”照会”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            OrderStatus l_orderStatus = l_serviceImpl.validateOrderAccept(
                l_strReferenceType,
                l_strProductType,
                l_blnIsMargin,
                l_blnIsOffFloorExecute);

            assertFalse(l_orderStatus.interestEquityFlag);
            assertFalse(l_orderStatus.offFloor);
            assertTrue(l_orderStatus.interestMarginFlag);
            assertTrue(l_orderStatus.swapFlag);

        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_validateOrderAccept_C0016()
    {
        final String STR_METHOD_NAME = "test_validateOrderAccept_C0016()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //0： 注文約定照会
        String l_strReferenceType = "0";
        //2： 信用取引
        String l_strProductType = "2";
        boolean l_blnIsMargin = true;
        boolean l_blnIsOffFloorExecute = false;

        try
        {

            l_clendarContext.setOrderAcceptTransaction("07");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());

            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams = 
                TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”照会”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);


            //注文受付商品： ”立会外分売”
            //注文受付トランザクション： ”照会”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("25");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”照会”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            OrderStatus l_orderStatus = l_serviceImpl.validateOrderAccept(
                l_strReferenceType,
                l_strProductType,
                l_blnIsMargin,
                l_blnIsOffFloorExecute);

            fail();

        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00011, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_validateOrderAccept_C0017()
    {
        final String STR_METHOD_NAME = "test_validateOrderAccept_C0017()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //0： 注文約定照会
        String l_strReferenceType = "0";
        //0： 現物株式、信用取引 すべて
        String l_strProductType = "0";
        boolean l_blnIsMargin = true;
        boolean l_blnIsOffFloorExecute = true;

        try
        {

            l_clendarContext.setOrderAcceptTransaction("07");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());

            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams =
                TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”照会”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);


            //注文受付商品： ”立会外分売”
            //注文受付トランザクション： ”照会”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("25");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”照会”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            OrderStatus l_orderStatus = l_serviceImpl.validateOrderAccept(
                l_strReferenceType,
                l_strProductType,
                l_blnIsMargin,
                l_blnIsOffFloorExecute);

            fail();

        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00011, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

//    /*
//     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImpl.validateOrderAccept(String, String, boolean, boolean)'
//     */
//    public void test_validateOrderAccept_C0018()
//    {
//        final String STR_METHOD_NAME = "test_validateOrderAccept_C0018()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        //1： 訂正取消一覧
//        String l_strReferenceType = "1";
//        //0： 現物株式、信用取引 すべて
//        String l_strProductType = "0";
//        boolean l_blnIsMargin = true;
//        boolean l_blnIsOffFloorExecute = true;
//
//        try
//        {
//            //注文受付トランザクション 05：訂正
//            l_clendarContext.setOrderAcceptTransaction("05");
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
//                l_clendarContext);
//
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
//            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
//            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
//            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
//            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
//            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());
//
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//
//            OrderAcceptStatusParams l_orderAcceptStatusParams =
//                TestDBUtility.getOrderAcceptStatusRow();
//            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
//            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
//            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
//            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
//            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
//            //受付時間区分： ”株式・信用”
//            //注文受付商品： ”株式”
//            //注文受付トランザクション： ”訂正”
//            //”受付可”
//            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//
//
//            //受付時間区分： ”株式・信用”
//            //注文受付商品： ”株式”
//            //注文受付トランザクション： ”取消”
//            //”受付可”
//            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
//            l_orderAcceptStatusParams.setOrderAccTransaction("06");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//
//            //受付時間区分： ”立会外分売”
//            //注文受付商品： ”立会外分売”
//            //注文受付トランザクション： ”取消”
//            //”受付不可”
//            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
//            l_orderAcceptStatusParams.setOrderAccProduct("25");
//            l_orderAcceptStatusParams.setOrderAccTransaction("06");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//
//            //受付時間区分： ”株式・信用”
//            //注文受付商品： ”信用取引”
//            //注文受付トランザクション： ”訂正”
//            //”受付可”
//            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
//            l_orderAcceptStatusParams.setOrderAccProduct("03");
//            l_orderAcceptStatusParams.setOrderAccTransaction("05");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//
//            //受付時間区分： ”株式・信用”
//            //注文受付商品： ”信用取引”
//            //注文受付トランザクション： ”取消”
//            //”受付可”
//            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
//            l_orderAcceptStatusParams.setOrderAccProduct("03");
//            l_orderAcceptStatusParams.setOrderAccTransaction("06");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//
//            //受付時間区分： ”現引・現渡”
//            //注文受付商品： ”信用取引”
//            //注文受付トランザクション： ”取消”
//            //”受付可”
//            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
//            l_orderAcceptStatusParams.setOrderAccProduct("03");
//            l_orderAcceptStatusParams.setOrderAccTransaction("06");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//            l_tradingTimeParams.setTradingTimeType("19");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//
//            OrderStatus l_orderStatus = l_serviceImpl.validateOrderAccept(
//                l_strReferenceType,
//                l_strProductType,
//                l_blnIsMargin,
//                l_blnIsOffFloorExecute);
//            assertTrue(l_orderStatus.interestEquityFlag);
//            assertFalse(l_orderStatus.offFloor);
//            assertTrue(l_orderStatus.interestMarginFlag);
//            assertTrue(l_orderStatus.swapFlag);
//        }
//        catch (Exception l_ex)
//        {
//            log.error("", l_ex);
//            fail();
//        }
//
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
    
    /**
     *  注文約定照会の場合
     * 「緊急停止中」の場合、「緊急停止中」の例外をスロー
     *
     */
    public void test_validateOrderAccept_C0019()
    {
        final String STR_METHOD_NAME = "test_validateOrderAccept_C0019()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //0： 注文約定照会
        String l_strReferenceType = "0";
        //0： 現物株式、信用取引 すべて
        String l_strProductType = "0";
        boolean l_blnIsMargin = true;
        boolean l_blnIsOffFloorExecute = true;

        try
        {

            l_clendarContext.setOrderAcceptTransaction("07");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());

            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams =
                TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
            l_orderAcceptStatusParams.setOrderAcceptStatus("2");
            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”照会”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("2");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);


            //注文受付商品： ”立会外分売”
            //注文受付トランザクション： ”照会”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("2");
            l_orderAcceptStatusParams.setOrderAccProduct("25");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”照会”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("2");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            OrderStatus l_orderStatus = l_serviceImpl.validateOrderAccept(
                l_strReferenceType,
                l_strProductType,
                l_blnIsMargin,
                l_blnIsOffFloorExecute);
            
            fail();

        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00012, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *  注文約定照会の場合「バッチ処理中」の例外をスロー
     *
     */
    public void test_validateOrderAccept_C0020()
    {
        final String STR_METHOD_NAME = "test_validateOrderAccept_C0020()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //0： 注文約定照会
        String l_strReferenceType = "0";
        //0： 現物株式、信用取引 すべて
        String l_strProductType = "0";
        boolean l_blnIsMargin = true;
        boolean l_blnIsOffFloorExecute = true;

        try
        {

            l_clendarContext.setOrderAcceptTransaction("07");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());

            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams =
                TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”照会”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);


            //注文受付商品： ”立会外分売”
            //注文受付トランザクション： ”照会”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("25");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”照会”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            OrderStatus l_orderStatus = l_serviceImpl.validateOrderAccept(
                l_strReferenceType,
                l_strProductType,
                l_blnIsMargin,
                l_blnIsOffFloorExecute);
            
            fail();

        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00011, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *  訂正取消一覧 の場合 「緊急停止中」の例外をスロー
     *
     */
    public void test_validateOrderAccept_C0021()
    {
        final String STR_METHOD_NAME = "test_validateOrderAccept_C0021()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //1： 訂正取消一覧
        String l_strReferenceType = "1";
        //0： 現物株式、信用取引 すべて
        String l_strProductType = "0";
        boolean l_blnIsMargin = true;
        boolean l_blnIsOffFloorExecute = true;

        try
        {

//        	注文受付トランザクション 05：訂正
            l_clendarContext.setOrderAcceptTransaction("05");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());

            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams =
                TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
            l_orderAcceptStatusParams.setOrderAcceptStatus("2");
            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”訂正”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("2");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("2");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”立会外分売”
            //注文受付商品： ”立会外分売”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("2");
            l_orderAcceptStatusParams.setOrderAccProduct("25");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”株式・信用”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”訂正”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("2");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("05");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”株式・信用”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("2");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”現引・現渡”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("2");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            OrderStatus l_orderStatus = l_serviceImpl.validateOrderAccept(
                l_strReferenceType,
                l_strProductType,
                l_blnIsMargin,
                l_blnIsOffFloorExecute);
            
            fail();

        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00012, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     *  訂正取消一覧 の場合 「バッチ処理中」の例外をスロー
     *
     */
    public void test_validateOrderAccept_C0022()
    {
        final String STR_METHOD_NAME = "test_validateOrderAccept_C0022()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //1： 訂正取消一覧
        String l_strReferenceType = "1";
        //0： 現物株式、信用取引 すべて
        String l_strProductType = "0";
        boolean l_blnIsMargin = true;
        boolean l_blnIsOffFloorExecute = true;

        try
        {

//        	注文受付トランザクション 05：訂正
            l_clendarContext.setOrderAcceptTransaction("05");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());

            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams =
                TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”訂正”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”立会外分売”
            //注文受付商品： ”立会外分売”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("25");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”株式・信用”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”訂正”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("05");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”株式・信用”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //受付時間区分： ”現引・現渡”
            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”取消”
            //”受付不可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            OrderStatus l_orderStatus = l_serviceImpl.validateOrderAccept(
                l_strReferenceType,
                l_strProductType,
                l_blnIsMargin,
                l_blnIsOffFloorExecute);
            
            fail();

        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00011, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
//    /**
//     *  訂正取消一覧 の場合 「訂正取消不可」の例外をスロー
//     *
//     */
//    public void test_validateOrderAccept_C0023()
//    {
//        final String STR_METHOD_NAME = "test_validateOrderAccept_C0023()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        //1： 訂正取消一覧 
//        String l_strReferenceType = "1";
//        //0： 現物株式、信用取引 すべて 
//        String l_strProductType = "0";
//        boolean l_blnIsMargin = true;
//        boolean l_blnIsOffFloorExecute = true;
//
//        try
//        {
//            //注文受付トランザクション 05：訂正
//            l_clendarContext.setOrderAcceptTransaction("05");
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
//                l_clendarContext);
//
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
//            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
//            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
//            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
//            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
//            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());
//            l_tradingTimeParams.setEnableOrder(null);
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//            
//            OrderAcceptStatusParams l_orderAcceptStatusParams =
//                TestDBUtility.getOrderAcceptStatusRow();
//            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
//            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
//            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
//            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
//            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
//            //受付時間区分： ”株式・信用”
//            //注文受付商品： ”株式”
//            //注文受付トランザクション： ”訂正”
//            //”受付不可”
//            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//
//            //受付時間区分： ”株式・信用”
//            //注文受付商品： ”株式”
//            //注文受付トランザクション： ”取消”
//            //”受付不可”
//            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
//            l_orderAcceptStatusParams.setOrderAccTransaction("06");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//
//            //受付時間区分： ”立会外分売”
//            //注文受付商品： ”立会外分売”
//            //注文受付トランザクション： ”取消”
//            //”受付不可”
//            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
//            l_orderAcceptStatusParams.setOrderAccProduct("25");
//            l_orderAcceptStatusParams.setOrderAccTransaction("06");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//
//            //受付時間区分： ”株式・信用”
//            //注文受付商品： ”信用取引”
//            //注文受付トランザクション： ”訂正”
//            //”受付不可”
//            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
//            l_orderAcceptStatusParams.setOrderAccProduct("03");
//            l_orderAcceptStatusParams.setOrderAccTransaction("05");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//
//            //受付時間区分： ”株式・信用”
//            //注文受付商品： ”信用取引”
//            //注文受付トランザクション： ”取消”
//            //”受付不可”
//            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
//            l_orderAcceptStatusParams.setOrderAccProduct("03");
//            l_orderAcceptStatusParams.setOrderAccTransaction("06");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//
//            //受付時間区分： ”現引・現渡”
//            //注文受付商品： ”信用取引”
//            //注文受付トランザクション： ”取消”
//            //”受付不可”
//            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
//            l_orderAcceptStatusParams.setOrderAccProduct("03");
//            l_orderAcceptStatusParams.setOrderAccTransaction("06");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//
//            OrderStatus l_orderStatus = l_serviceImpl.validateOrderAccept(
//                l_strReferenceType,
//                l_strProductType,
//                l_blnIsMargin,
//                l_blnIsOffFloorExecute);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.error("", l_ex);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02177, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error("", l_ex);
//            fail();
//        }
//
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
    
//    /**
//     *  注文約定照会の場合「照会不可」の例外をスロー
//     *
//     */
//    public void test_validateOrderAccept_C0024()
//    {
//        final String STR_METHOD_NAME = "test_validateOrderAccept_C0024()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        //0： 注文約定照会
//        String l_strReferenceType = "0";
//        //0： 現物株式、信用取引 すべて
//        String l_strProductType = "0";
//        boolean l_blnIsMargin = true;
//        boolean l_blnIsOffFloorExecute = true;
//
//        try
//        {
//
//        	l_clendarContext.setOrderAcceptTransaction("03");
//        	ThreadLocalSystemAttributesRegistry.setAttribute(
//                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
//                l_clendarContext);
//
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
//            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
//            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
//            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
//            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
//            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());
//            l_tradingTimeParams.setEnableOrder(null);
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//
//            OrderAcceptStatusParams l_orderAcceptStatusParams =
//                TestDBUtility.getOrderAcceptStatusRow();
//            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
//            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
//            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
//            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
//            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
//            //受付時間区分： ”株式・信用”
//            //注文受付商品： ”株式”
//            //注文受付トランザクション： ”照会”
//            //”受付可”
//            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//
//
//            //注文受付商品： ”立会外分売”
//            //注文受付トランザクション： ”照会”
//            //”受付可”
//            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
//            l_orderAcceptStatusParams.setOrderAccProduct("25");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//
//            //注文受付商品： ”信用取引”
//            //注文受付トランザクション： ”照会”
//            //”受付可”
//            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
//            l_orderAcceptStatusParams.setOrderAccProduct("03");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//
//            OrderStatus l_orderStatus = l_serviceImpl.validateOrderAccept(
//                l_strReferenceType,
//                l_strProductType,
//                l_blnIsMargin,
//                l_blnIsOffFloorExecute);
//            
//            fail();
//
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.error("", l_ex);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02706, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error("", l_ex);
//            fail();
//        }
//
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }

}
@

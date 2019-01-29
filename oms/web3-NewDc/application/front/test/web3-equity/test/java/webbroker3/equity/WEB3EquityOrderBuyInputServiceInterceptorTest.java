head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.12.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityOrderBuyInputServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式買付注文入力サービスインタセプタ(WEB3EquityOrderBuyInputServiceInterceptorTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/04/10 崔遠鵬 (中訊) 新規作成
*/
package webbroker3.equity;

import java.sql.Timestamp;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.equity.message.WEB3EquityBuyInputRequest;
import webbroker3.equity.message.WEB3EquityProductSelectRequest;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityOrderBuyInputServiceInterceptorTest extends TestBaseForMock
{

    public WEB3EquityOrderBuyInputServiceInterceptorTest(String name)
    {
        super(name);
    }

    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3EquityOrderBuyInputServiceInterceptorTest.class);

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testOnCall_C001()
    {
        final String STR_METHOD_NAME = " testOnCall_C001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

//            TestDBUtility.deleteAll(CalendarRow.TYPE);
//            CalendarParams l_calendarParams = new CalendarParams();
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            TradingSystem l_tradingSys = l_finApp.getTradingSystem();
//            Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
//            l_calendarParams.setHoliday(WEB3DateUtility.toDay(l_processTime));
//            l_calendarParams.setBizDateType("2");
//            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            TestDBUtility.insertWithDel(l_calendarParams);
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {}, 
                    l_tsOrderAcceptTime);
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "1");

            WEB3EquityOrderBuyInputServiceInterceptor l_interceptor = new WEB3EquityOrderBuyInputServiceInterceptor();
            WEB3EquityBuyInputRequest l_request = new WEB3EquityBuyInputRequest();
            l_request.tradingType = "1";
            Object[] l_serviceParams = new Object[]{l_request};
            l_interceptor.onCall(null, l_serviceParams);

            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.
                    getAttribute("web3.tradingcalendarcontext");

            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertNull(l_context.getMarketCode());
            assertEquals("0", l_context.getProductCode());
            assertEquals("01", l_context.getOrderAcceptTransaction());
            assertEquals("01", l_context.getTradingTimeType());
            assertEquals("01", l_context.getOrderAcceptProduct());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testOnCall_C002()
    {
        final String STR_METHOD_NAME = " testOnCall_C002";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("03");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

//            TestDBUtility.deleteAll(CalendarRow.TYPE);
//            CalendarParams l_calendarParams = new CalendarParams();
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            TradingSystem l_tradingSys = l_finApp.getTradingSystem();
//            Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
//            l_calendarParams.setHoliday(WEB3DateUtility.toDay(l_processTime));
//            l_calendarParams.setBizDateType("2");
//            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            TestDBUtility.insertWithDel(l_calendarParams);
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {}, 
                    l_tsOrderAcceptTime);
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "1");
            WEB3EquityOrderBuyInputServiceInterceptor l_interceptor = new WEB3EquityOrderBuyInputServiceInterceptor();
            WEB3EquityBuyInputRequest l_request = new WEB3EquityBuyInputRequest();
            l_request.tradingType = "2";
            Object[] l_serviceParams = new Object[]{l_request};
            l_interceptor.onCall(null, l_serviceParams);

            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.
                    getAttribute("web3.tradingcalendarcontext");

            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertNull(l_context.getMarketCode());
            assertEquals("0", l_context.getProductCode());
            assertEquals("01", l_context.getOrderAcceptTransaction());
            assertEquals("03", l_context.getTradingTimeType());
            assertEquals("25", l_context.getOrderAcceptProduct());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testOnCall_C003()
    {
        final String STR_METHOD_NAME = " testOnCall_C003";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

//            TestDBUtility.deleteAll(CalendarRow.TYPE);
//            CalendarParams l_calendarParams = new CalendarParams();
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            TradingSystem l_tradingSys = l_finApp.getTradingSystem();
//            Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
//            l_calendarParams.setHoliday(WEB3DateUtility.toDay(l_processTime));
//            l_calendarParams.setBizDateType("2");
//            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            TestDBUtility.insertWithDel(l_calendarParams);

            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {}, 
                    l_tsOrderAcceptTime);
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "2");
            
            WEB3EquityOrderBuyInputServiceInterceptor l_interceptor = new WEB3EquityOrderBuyInputServiceInterceptor();
            WEB3EquityProductSelectRequest l_request = new WEB3EquityProductSelectRequest();
            Object[] l_serviceParams = new Object[]{l_request};
            l_interceptor.onCall(null, l_serviceParams);

            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.
                    getAttribute("web3.tradingcalendarcontext");

            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertNull(l_context.getMarketCode());
            assertEquals("0", l_context.getProductCode());
            assertEquals("01", l_context.getOrderAcceptTransaction());
            assertEquals("01", l_context.getTradingTimeType());
            assertEquals("01", l_context.getOrderAcceptProduct());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.29.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoManualExpireServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・先物OP手動失効サービスインタセプタ (WEB3AdminIfoManualExpireServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30　@謝旋(中訊) 新規作成
Revision History : 2007/07/09　@崔遠鵬(中訊)
*/
package webbroker3.ifoadmin;

import java.sql.Timestamp;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;

import test.util.TestDBUtility;
import test.util.TestSpecialClassUtility;

import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・先物OP手動失効サービスインタセプタ )<BR>
 * 管理者・先物OP手動失効サービスインタセプタ <BR>
 * <BR>
 * @@author 謝旋(中訊)
 * @@version 1.0
 */
public class WEB3AdminIfoManualExpireServiceInterceptorTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminIfoManualExpireServiceInterceptorTest.class);

    public WEB3AdminIfoManualExpireServiceInterceptorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

//    public void testOncall()
//    {
//        String STR__METHOD_NAME = "testOncall()";
//        log.entering(TEST_START + STR__METHOD_NAME);
//        try 
//        {
//            WEB3AdminIfoManualExpireServiceInterceptor interceptor = new WEB3AdminIfoManualExpireServiceInterceptor();
//            Object obj = interceptor.onCall(null , null);
//
//            assertNull(obj);
//            assertEquals(GtlUtils.getSystemTimestamp() , 
//                ThreadLocalSystemAttributesRegistry.getAttribute("xblocks.gtl.attributes.systemtimestamp"));
//
//            log.exiting(TEST_END + STR__METHOD_NAME);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR__METHOD_NAME , l_ex);
//            log.exiting(TEST_END + STR__METHOD_NAME);
//            fail();
//        }
//
//    }

    public void testOn()
    {
        String STR__METHOD_NAME = "testOncall()";
        log.entering(TEST_START + STR__METHOD_NAME);

        TestSpecialClassUtility.testServiceInterceptor(new WEB3AdminIfoManualExpireServiceInterceptor());

        log.exiting(TEST_END + STR__METHOD_NAME);
    }

    public void testOnCall_C0001()
    {
        String STR__METHOD_NAME = "testOnCall_C0001()";
        log.entering(TEST_START + STR__METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(33381330003L));

            //データベースへデータをインサート
            AdministratorParams l_administratorParams = new AdministratorParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(AdministratorRow.TYPE);
            l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);

            SystemPreferencesParams l_systemPreferencesParams = new SystemPreferencesParams();
            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);
            l_systemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            CalendarParams l_calendarParams = new CalendarParams();
            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            l_calendarParams.setHoliday(new Timestamp(System.currentTimeMillis()));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_calendarParams);

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();
            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "1");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_tsOrderAcceptTime);
            
            //メソッドを実行
            WEB3AdminIfoManualExpireServiceInterceptor interceptor = new WEB3AdminIfoManualExpireServiceInterceptor();
            Object obj = interceptor.onCall(null , null);

            //比較
            WEB3GentradeTradingClendarContext l_context = (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.
                getAttribute("web3.tradingcalendarcontext");
            assertNull(obj);
            assertEquals(l_context.getInstitutionCode(), "0D");
            assertEquals(l_context.getBranchCode(), "381");
            assertEquals(l_context.getMarketCode(), "0");
            assertEquals(l_context.getTradingTimeType(), "11");
            assertEquals(l_context.getProductCode(), "0");
            assertEquals(l_context.getBizDateType(), "1");
            assertEquals(l_context.getSubmitMarketTrigger(), "0");
            assertEquals(l_context.getBizdateCalcParameter(), "1");
            assertEquals(l_context.getEnableOrder(), "0");

            Integer l_intOffset = (Integer)ThreadLocalSystemAttributesRegistry.getAttribute("xblocks.gtl.attributes.bizdate.offset");
            assertEquals(l_intOffset, new Integer(0));
        }
        catch (Exception l_ex)
        {
            log.error(STR__METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR__METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR__METHOD_NAME);
    }
}
@

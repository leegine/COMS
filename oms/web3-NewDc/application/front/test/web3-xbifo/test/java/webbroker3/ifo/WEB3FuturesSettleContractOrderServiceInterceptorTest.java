head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.59.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesSettleContractOrderServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物返済注文サービスインタセプタTest(WEB3FuturesSettleContractOrderServiceInterceptorTest)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/03/27 張騰宇 (中訊) 新規作成
*/
package webbroker3.ifo;

import java.sql.Timestamp;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.message.WEB3FuturesCloseMarginConfirmRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesSettleContractOrderServiceInterceptorTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesSettleContractOrderServiceInterceptorTest.class);

    public WEB3FuturesSettleContractOrderServiceInterceptorTest(String arg0)
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
        l_tradingTimeParams.setProductCode("0");
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

    /*
     * Test method for 'webbroker3.ifo.WEB3FuturesSettleContractOrderServiceInterceptor.onCall(Method, Object[])'
     */
    public void testOnCallCase1()
    {
        final String STR_METHOD_NAME = "testOnCall_003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                new Class[] {}, new Long(333812512246L));
        
        try
        {
            // テーブルへデータをインサート
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
//            WEB3GentradeTradingTimeManagementForMock.setTimestamp();
            
            WEB3FuturesCloseMarginConfirmRequest[] l_serviceParams = new WEB3FuturesCloseMarginConfirmRequest[1];
            l_serviceParams[0] = new WEB3FuturesCloseMarginConfirmRequest();
    
            WEB3FuturesSettleContractOrderServiceInterceptor l_interceptor =
                new WEB3FuturesSettleContractOrderServiceInterceptor();
            // 実際メソッドをコール
            l_interceptor.onCall(null, l_serviceParams);
    
            // 予想結果と実際結果の比較
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            assertNull( l_context.getProductCode());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@

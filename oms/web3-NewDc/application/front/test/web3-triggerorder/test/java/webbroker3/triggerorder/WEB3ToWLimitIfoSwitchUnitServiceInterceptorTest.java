head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.41.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToWLimitIfoSwitchUnitServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ToWLimitIfoSwitchUnitServiceInterceptorTest.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/07 関博 新規作成
*/
package webbroker3.triggerorder;

import java.sql.Timestamp;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractOpenOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (W指値注文先物OP切替一件サービスインタセプタ)<BR>
 * W指値注文先物OP切替一件サービスインタセプタ<BR>
 * 
 * @@author 関博
 * @@version 1.0
 */
public class WEB3ToWLimitIfoSwitchUnitServiceInterceptorTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToWLimitIfoSwitchUnitServiceInterceptorTest.class);
    WEB3ToWLimitIfoSwitchUnitServiceInterceptor interceptor =
        new WEB3ToWLimitIfoSwitchUnitServiceInterceptor();

    public WEB3ToWLimitIfoSwitchUnitServiceInterceptorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testOnCall_C0001()
    {
        final String STR_METHOD_NAME = " testOnCall_C0001";
        log.entering(STR_METHOD_NAME);

        try
        {
            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.MONTH, 1);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_canlendar.set(Calendar.HOUR_OF_DAY, 0);
            l_canlendar.set(Calendar.MINUTE, 0);
            l_canlendar.set(Calendar.SECOND, 0);
            l_canlendar.set(Calendar.MILLISECOND, 0);
            Timestamp l_timestamp = new Timestamp(l_canlendar.getTimeInMillis());

            //データベースへデータをインサート
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(l_timestamp);
            l_calendarParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_calendarParams);

            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(105007L);
            l_ifoOrderUnitParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            ProductParams l_productParams =TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            IfoProductParams l_ifoProductParams =TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setProductCode("0005");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_timestamp);


            IfoOrderUnit l_orderunit = new IfoContractOpenOrderUnitImpl(105007L);
            Object[] l_objs = {l_orderunit};
            
            interceptor.onCall(null, l_objs);
            assertEquals(BooleanEnum.TRUE, ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}

@

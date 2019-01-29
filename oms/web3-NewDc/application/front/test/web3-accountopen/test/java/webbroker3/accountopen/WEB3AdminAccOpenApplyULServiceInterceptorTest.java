head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.08.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAccOpenApplyULServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen;

import java.lang.reflect.Method;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;
import test.util.TestSpecialClassUtility;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccOpenApplyULServiceInterceptorTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyULServiceInterceptorTest.class);

    public WEB3AdminAccOpenApplyULServiceInterceptorTest(String arg0)
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

    public void testOnCall()
    {
        final String STR_METHOD_NAME = "testOnCall()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(33381330001L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "isAccountIdSet",
            new Class[] {},
            Boolean.FALSE);
        
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("381");

            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingSystem l_tradingSys = l_finApp.getTradingSystem();
            java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("17");
            l_tradingTimeParams.setProductCode("04");
            l_tradingTimeParams.setBizDateType("1");//WEB3GentradeTradingTimeManagement.getBizDateType(l_processTime)
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar .getInstance().getTime());
            TestDBUtility.deleteAll(l_tradingTimeParams.getRowType());
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(l_mainAccountParams.getRowType());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3AdminAccOpenApplyULServiceInterceptor inter = new WEB3AdminAccOpenApplyULServiceInterceptor();
            inter.onCall(null, null);

            WEB3GentradeTradingClendarContext l_context = 
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals("0", l_context.getMarketCode());
            assertEquals("17", l_context.getTradingTimeType());
            assertEquals("04", l_context.getProductCode());
            assertEquals("22", l_context.getOrderAcceptProduct());
            assertEquals("00", l_context.getOrderAcceptTransaction());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testOnthrow()
    {
        TestSpecialClassUtility.testServiceInterceptor(new WEB3AdminAccOpenApplyULServiceInterceptor());
    }
}
@

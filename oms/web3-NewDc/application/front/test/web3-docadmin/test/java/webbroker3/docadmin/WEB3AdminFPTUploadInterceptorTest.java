head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.06.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTUploadInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginTypeInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;
import test.util.TestSpecialClassUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

import weblogic.xml.security.transforms.TestTransform;

public class WEB3AdminFPTUploadInterceptorTest extends TestBaseForMock
{


    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTUploadInterceptorTest.class);
    public WEB3AdminFPTUploadInterceptorTest(String arg0)
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

    public class LoginInfoTest implements LoginInfo
    {

        public LoginTypeInfo getLoginTypeInfo() {
            // TODO Auto-generated method stub
            return null;
        }

        public long getLoginId() {
            // TODO Auto-generated method stub
            return 1001;
        }

        public long getLoginTypeId() {
            // TODO Auto-generated method stub
            return 0;
        }

        public String getUsername() {
            // TODO Auto-generated method stub
            return null;
        }

        public String getInitialPassword() {
            // TODO Auto-generated method stub
            return null;
        }

        public Set getSubordinateLoginGroups() {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isDisabled() {
            // TODO Auto-generated method stub
            return false;
        }

        public Set getReachableAccountIds() {
            // TODO Auto-generated method stub
            return null;
        }

        public Set getReachableLoginIds() {
            // TODO Auto-generated method stub
            return null;
        }

        public Set getReachableLogins() {
            // TODO Auto-generated method stub
            return null;
        }

        public Map getAttributes() {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isAccountReachable(long arg0) {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean hasFailedLogin() {
            // TODO Auto-generated method stub
            return false;
        }

        public int getFailureCount() {
            // TODO Auto-generated method stub
            return 0;
        }

        public Date getLastFailureTimestamp() {
            // TODO Auto-generated method stub
            return null;
        }
        
    }

    /**
     * testOnCall<BR>
     */
    public void testOnCall1()
    {
        final String STR_METHOD_NAME = "testOnCall1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001l));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "isAccountIdSet",
                new Class[] {},
                Boolean.FALSE);

            LoginInfoTest l_loginInfoTest = new LoginInfoTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoTest);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            WEB3AdminFPTUploadInterceptor l_interceptor =
                new WEB3AdminFPTUploadInterceptor();
            l_interceptor.onCall(null, null);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * testOnCall<BR>
     */
    public void testOnCall2()
    {
        final String STR_METHOD_NAME = "testOnCall2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001l));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "isAccountIdSet",
                new Class[] {},
                Boolean.TRUE);

            LoginInfoTest l_loginInfoTest = new LoginInfoTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoTest);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            WEB3AdminFPTUploadInterceptor l_interceptor =
                new WEB3AdminFPTUploadInterceptor();
            l_interceptor.onCall(null, null);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * testOnCall<BR>
     */
    public void testOnCall()
    {
        final String STR_METHOD_NAME = "testOnCall()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001l));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "isAccountIdSet",
                new Class[] {},
                Boolean.FALSE);

            LoginInfoTest l_loginInfoTest = new LoginInfoTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoTest);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("17");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setMarketCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001l);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //InstitutionParams
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("33");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3AdminFPTUploadInterceptor l_interceptor =
                new WEB3AdminFPTUploadInterceptor();
            l_interceptor.onCall(null, null);

            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals("0", l_context.getMarketCode());
            assertEquals("17", l_context.getTradingTimeType());
            assertEquals("0", l_context.getProductCode());
            assertEquals("22", l_context.getOrderAcceptProduct());
            assertEquals("00", l_context.getOrderAcceptTransaction());
            assertEquals(l_context.getBizDateType(), "1");

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "isAccountIdSet",
                new Class[] {},
                Boolean.TRUE);
            
            l_tradingTimeParams.setInstitutionCode("33");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            l_interceptor.onCall(null, null);

            l_context =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            assertEquals("33", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals("0", l_context.getMarketCode());
            assertEquals("17", l_context.getTradingTimeType());
            assertEquals("0", l_context.getProductCode());
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
        TestSpecialClassUtility.testServiceInterceptor(new WEB3AdminFPTUploadInterceptor());
    }
}
@

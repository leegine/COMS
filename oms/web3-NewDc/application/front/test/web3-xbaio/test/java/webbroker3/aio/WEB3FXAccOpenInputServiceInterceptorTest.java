head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.04.08.00.53.50;	author che-jin;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1404d9e5c8b3d81;
filename	WEB3FXAccOpenInputServiceInterceptorTest.java;

1.1
date	2011.04.07.01.34.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXAccOpenInputServiceInterceptorTest.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3FXAccOpenInputServiceInterceptorTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/09 武波 (中訊) 新規作成
*/
package webbroker3.aio;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginTypeInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;
import test.util.TestSpecialClassUtility;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.CompFxConditionRow;
import webbroker3.aio.message.WEB3FXAccOpenInputRequest;
import webbroker3.aio.message.WEB3FXAccOpenTradeAgreementRequest;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FXAccOpenInputServiceInterceptorTest extends TestBaseForMock
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FXAccOpenInputServiceInterceptorTest.class);
    public WEB3FXAccOpenInputServiceInterceptorTest(String arg0)
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

		public Map getLoginAttributes() {
			// TODO Auto-generated method stub
			return null;
		}

		public Map getLoginTypeAttributes() {
			// TODO Auto-generated method stub
			return null;
		}

		public Long getDefaultAccountId() {
			// TODO Auto-generated method stub
			return null;
		}
        
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

            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("33");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("01");
            l_tradingTimeParams.setStartTime("120000");
            l_tradingTimeParams.setEndTime("160000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

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

            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setInstitutionCode("33");
            l_compFxConditionParams.setFxSystemCode("12");
            l_compFxConditionParams.setFxSystemDiv("2");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setInstitutionCode("33");
            l_compFxConditionParams.setFxSystemCode("13");
            l_compFxConditionParams.setFxSystemDiv("2");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            WEB3FXAccOpenTradeAgreementRequest l_agreementRequest =
                new WEB3FXAccOpenTradeAgreementRequest();
            l_agreementRequest.fxSystemCode = "12";
            WEB3FXAccOpenInputServiceInterceptor l_interceptor =
                new WEB3FXAccOpenInputServiceInterceptor();
            l_interceptor.onCall(null, new Object[]{l_agreementRequest});

            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            assertEquals("33", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals("0", l_context.getMarketCode());
            assertEquals("01", l_context.getTradingTimeType());
            assertEquals("01", l_context.getProductCode());
            assertEquals("01", l_context.getOrderAcceptProduct());
            assertEquals("01", l_context.getOrderAcceptTransaction());
            assertEquals(l_context.getBizDateType(), "1");

            WEB3FXAccOpenInputRequest l_inputRequest =
                new WEB3FXAccOpenInputRequest();
            l_inputRequest.fxSystemCode = "12";
            l_interceptor.onCall(null, new Object[]{l_inputRequest});

            l_context =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            assertEquals("33", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals("0", l_context.getMarketCode());
            assertEquals("01", l_context.getTradingTimeType());
            assertEquals("01", l_context.getProductCode());
            assertEquals("01", l_context.getOrderAcceptProduct());
            assertEquals("01", l_context.getOrderAcceptTransaction());
            assertEquals(l_context.getBizDateType(), "1");


            WEB3FXAccOpenInputRequest l_inputRequest1 =
                new WEB3FXAccOpenInputRequest();
            l_inputRequest1.fxSystemCode = null;
            l_interceptor.onCall(null, new Object[]{l_inputRequest1});
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
        	assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
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
     * ２）で取得した会社別FXシステムParam.FXシステム区分==”2”の場合＞
     * 取引カレンダコンテキスト.受付時間区分 = ２）で取得した会社別FXシステムParam.受付時間区分
     * 取引カレンダコンテキスト.注文受付商品 = ２）で取得した会社別FXシステムParam.受付時間区分
     */
    public void testOnCall_C0001()
    {
        final String STR_METHOD_NAME = "testOnCall_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));

            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("01");
            l_tradingTimeParams.setStartTime("120000");
            l_tradingTimeParams.setEndTime("160000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(100);
            l_mainAccountParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(100);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            
            Object[] l_serviceParam = new Object[1];
            WEB3FXAccOpenTradeAgreementRequest l_request =
                new WEB3FXAccOpenTradeAgreementRequest();
            l_request.fxSystemCode = "01";
            l_serviceParam[0] = l_request;

            WEB3FXAccOpenInputServiceInterceptor l_interceptor =
                new WEB3FXAccOpenInputServiceInterceptor();

            l_interceptor.onCall(null, l_serviceParam);

            WEB3GentradeTradingClendarContext l_gentradeTradingClendarContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    "web3.tradingcalendarcontext");

            assertEquals("01", l_gentradeTradingClendarContext.getTradingTimeType());
            assertEquals("01", l_gentradeTradingClendarContext.getOrderAcceptProduct());
            assertEquals("0D", l_gentradeTradingClendarContext.getInstitutionCode());
            assertEquals("381", l_gentradeTradingClendarContext.getBranchCode());
            assertEquals("0", l_gentradeTradingClendarContext.getMarketCode());
            assertEquals("01", l_gentradeTradingClendarContext.getProductCode());
            assertEquals("01", l_gentradeTradingClendarContext.getOrderAcceptTransaction());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ＜２）で取得した会社別FXシステムParam.FXシステム区分==”2”以外の場合＞
     * 取引カレンダコンテキスト.受付時間区分 = ２）で取得した会社別FXシステムParam.受付時間区分
     * 取引カレンダコンテキスト.注文受付商品 = ２）で取得した会社別FXシステムParam.受付時間区分
     */
    public void testOnCall_C0002()
    {
        final String STR_METHOD_NAME = "testOnCall_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));

            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("01");
            l_tradingTimeParams.setStartTime("120000");
            l_tradingTimeParams.setEndTime("160000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(100);
            l_mainAccountParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(100);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("3");
            l_compFxConditionParams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            
            Object[] l_serviceParam = new Object[1];
            WEB3FXAccOpenTradeAgreementRequest l_request =
                new WEB3FXAccOpenTradeAgreementRequest();
            l_request.fxSystemCode = "01";
            l_serviceParam[0] = l_request;

            WEB3FXAccOpenInputServiceInterceptor l_interceptor =
                new WEB3FXAccOpenInputServiceInterceptor();

            l_interceptor.onCall(null, l_serviceParam);

            WEB3GentradeTradingClendarContext l_gentradeTradingClendarContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    "web3.tradingcalendarcontext");

            assertEquals("01", l_gentradeTradingClendarContext.getTradingTimeType());
            assertEquals("01", l_gentradeTradingClendarContext.getOrderAcceptProduct());
            assertEquals("0D", l_gentradeTradingClendarContext.getInstitutionCode());
            assertEquals("381", l_gentradeTradingClendarContext.getBranchCode());
            assertEquals("0", l_gentradeTradingClendarContext.getMarketCode());
            assertEquals("01", l_gentradeTradingClendarContext.getProductCode());
            assertEquals("01", l_gentradeTradingClendarContext.getOrderAcceptTransaction());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testOnthrow()
    {
        TestSpecialClassUtility.testServiceInterceptor(new WEB3FXAccOpenInputServiceInterceptor());
    }
}
@


1.1
log
@*** empty log message ***
@
text
@d144 15
@


head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.40.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AccInfoElecDeliveryRegisterChangeHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright           : (株)大和総研ビジネス・イノベーション
 File Name           : Webbroker3-helloworld プラグインクラス(WEB3HelloWorldPlugin.java)
 Author Name         : Daiwa Institute of Research Business Innovation
 Revision History    : 2010/11/19 劉レイ(北京中訊) 新規作成
 */
package webbroker3.accountinfo.handler;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginTypeInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;

import test.util.TestDBUtility;

import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryApyReferenceRequest;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryApyReferenceResponse;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryRegisterChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryRegisterChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoElecDeliveryRegisterChangeService;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoElecDeliveryRegisterChangeServiceImpl;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccInfoElecDeliveryRegisterChangeHandlerTest extends TestBaseForMock
{
    private WEB3AccInfoElecDeliveryRegisterChangeService l_service = null;
    
    WEB3AccInfoElecDeliveryRegisterChangeHandler l_handler = null;
    
    WEB3AccInfoElecDeliveryRegisterChangeInputRequest l_inputRequest = null;
    
    WEB3AccInfoElecDeliveryRegisterChangeInputResponse l_inputResponse = null;
    
    WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_completeRequest = null;
    
    WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse l_completeResponse = null;
    
    WEB3AccInfoElecDeliveryApyReferenceRequest l_referenceRequest = null;
    
    WEB3AccInfoElecDeliveryApyReferenceResponse l_referenceResponse = null;

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoElecDeliveryRegisterChangeHandlerTest.class);
    
    protected void setUp() throws Exception
    {
        super.setUp();
        
        this.l_handler = new WEB3AccInfoElecDeliveryRegisterChangeHandler();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public WEB3AccInfoElecDeliveryRegisterChangeHandlerTest(String name)
    {
        super(name);
    }

    /**
     * LoginInfoForTest
     */
    private class LoginInfoForTest implements LoginInfo
    {

        public LoginTypeInfo getLoginTypeInfo()
        {
            return null;
        }

        public long getLoginId()
        {
            return 33381330003L;
        }

        public long getLoginTypeId()
        {
            return 0;
        }

        public String getUsername()
        {
            return null;
        }

        public String getInitialPassword()
        {
            return null;
        }

        public Set getSubordinateLoginGroups()
        {
            return null;
        }

        public boolean isDisabled()
        {
            return false;
        }

        public Set getReachableAccountIds()
        {
            return null;
        }

        public Set getReachableLoginIds()
        {
            return null;
        }

        public Set getReachableLogins()
        {
            return null;
        }

        public Map getAttributes()
        {
            return null;
        }

        public boolean isAccountReachable(long arg0)
        {
            return false;
        }

        public boolean hasFailedLogin()
        {
            return false;
        }

        public int getFailureCount()
        {
            return 0;
        }

        public Date getLastFailureTimestamp()
        {
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
    
    private class WEB3AccInfoElecDeliveryRegisterChangeServiceImplForTest
        extends WEB3AccInfoElecDeliveryRegisterChangeServiceImpl
    {
        protected WEB3AccInfoElecDeliveryRegisterChangeInputResponse getInputScreen(
            WEB3AccInfoElecDeliveryRegisterChangeInputRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME =
                "WEB3AccInfoElecDeliveryRegisterChangeServiceImplForTest.getInputScreen(WEB3AccInfoElecDeliveryRegisterChangeInputRequest)";
            log.entering(STR_METHOD_NAME);
            
            l_inputResponse = (WEB3AccInfoElecDeliveryRegisterChangeInputResponse)l_request.createResponse();
            
            log.exiting(STR_METHOD_NAME);
            return l_inputResponse;
        }
        
        protected WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse submitChangeScreen(
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "" +
                "WEB3AccInfoElecDeliveryRegisterChangeServiceImplForTest.submitChangeScreen(WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest)";
            log.entering(STR_METHOD_NAME);
            
            l_completeResponse = (WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse)l_request.createResponse();
            
            log.exiting(STR_METHOD_NAME);
            return l_completeResponse;
        }
        
        protected WEB3AccInfoElecDeliveryApyReferenceResponse getEleDeliveryInfoList(
            WEB3AccInfoElecDeliveryApyReferenceRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "WEB3AccInfoElecDeliveryRegisterChangeServiceImplForTest.elecDeliveryApyReference(WEB3AccInfoElecDeliveryApyReferenceRequest)";
            log.entering(STR_METHOD_NAME);
            
            l_referenceResponse = (WEB3AccInfoElecDeliveryApyReferenceResponse)l_request.createResponse();
            
            log.exiting(STR_METHOD_NAME);
            return l_referenceResponse;
        }
    }
    
    public void testInputScreenDisplayCase0001()
    {
        final String STR_METHOD_NAME = "testInputScreenDisplayCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_service =
                (WEB3AccInfoElecDeliveryRegisterChangeService)Services.getService(
                        WEB3AccInfoElecDeliveryRegisterChangeService.class);
            
            Services.unregisterService(WEB3AccInfoElecDeliveryRegisterChangeService.class);
            
            l_inputRequest = new WEB3AccInfoElecDeliveryRegisterChangeInputRequest();
            
            l_inputResponse = this.l_handler.inputScreenDisplay(l_inputRequest);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_inputResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AccInfoElecDeliveryRegisterChangeService.class,l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testInputScreenDisplayCase0002()
    {
        final String STR_METHOD_NAME = "testInputScreenDisplayCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "isAccountIdSet",
                new Class[] {},
                Boolean.FALSE);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(333812512246L));
        
        LoginInfoImpl l_loginInfo = new LoginInfoImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfo);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginId",
            new Class[] {},
            new Long(111111));

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setBranchCode("123");
        
        WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
        try
        {
            //取引時間コンテキストの取得
            WEB3GentradeTradingClendarContext l_clendarContext =
                new WEB3GentradeTradingClendarContext();
            
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("123");
            l_clendarContext.setTradingTimeType("00");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setProductCode("0");
            
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            
            l_inputRequest = new WEB3AccInfoElecDeliveryRegisterChangeInputRequest();
            
            l_inputRequest.eleDeliveryFlag = null;
            
            l_inputResponse = l_handler.inputScreenDisplay(l_inputRequest);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03221, l_inputResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testInputScreenDisplayCase0003()
    {
        final String STR_METHOD_NAME = "testInputScreenDisplayCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        

        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "isAccountIdSet",
                new Class[] {},
                Boolean.FALSE);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(333812512246L));
        
        LoginInfoImpl l_loginInfo = new LoginInfoImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfo);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginId",
            new Class[] {},
            new Long(111111));

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setBranchCode("123");
        
        WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
        try
        {
            //取引時間コンテキストの取得
            WEB3GentradeTradingClendarContext l_clendarContext =
                new WEB3GentradeTradingClendarContext();
            
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("123");
            l_clendarContext.setTradingTimeType("00");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setProductCode("0");
            
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
        
            Services.overrideService(WEB3AccInfoElecDeliveryRegisterChangeService.class,
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImplForTest());

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            
            l_inputRequest = new WEB3AccInfoElecDeliveryRegisterChangeInputRequest();
            
            l_inputRequest.eleDeliveryFlag = "0";
            
            l_inputResponse = l_handler.inputScreenDisplay(l_inputRequest);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testElecDeliveryRegisterChangeCase0001()
    {
        final String STR_METHOD_NAME = "testElecDeliveryRegisterChangeCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        

        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "isAccountIdSet",
                new Class[] {},
                Boolean.FALSE);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(333812512246L));
        
        LoginInfoImpl l_loginInfo = new LoginInfoImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfo);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginId",
            new Class[] {},
            new Long(111111));

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setBranchCode("123");
        
        WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
        try
        {
            //取引時間コンテキストの取得
            WEB3GentradeTradingClendarContext l_clendarContext =
                new WEB3GentradeTradingClendarContext();
            
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("123");
            l_clendarContext.setTradingTimeType("00");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setProductCode("0");
            
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
        
            Services.overrideService(WEB3AccInfoElecDeliveryRegisterChangeService.class,
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImplForTest());

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            
            l_completeRequest = new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            
            l_completeRequest.ordRulReportDiv = "0";
            
            l_completeResponse = l_handler.elecDeliveryRegisterChange(l_completeRequest);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetEleDeliveryInfoListCase0001()
    {
        final String STR_METHOD_NAME = "testGetEleDeliveryInfoListCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        

        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "isAccountIdSet",
                new Class[] {},
                Boolean.FALSE);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(333812512246L));
        
        LoginInfoImpl l_loginInfo = new LoginInfoImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfo);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginId",
            new Class[] {},
            new Long(111111));

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setBranchCode("123");
        
        WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
        try
        {
            //取引時間コンテキストの取得
            WEB3GentradeTradingClendarContext l_clendarContext =
                new WEB3GentradeTradingClendarContext();
            
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("123");
            l_clendarContext.setTradingTimeType("00");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setProductCode("0");
            
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
        
            Services.overrideService(WEB3AccInfoElecDeliveryRegisterChangeService.class,
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImplForTest());

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            
            l_referenceRequest = new WEB3AccInfoElecDeliveryApyReferenceRequest();
            
            l_referenceResponse = l_handler.elecDeliveryApyReference(l_referenceRequest);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@

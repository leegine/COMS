head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.41.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPAddDepositMailSendHandlerTest.java;


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
 Revision History    : 2010/12/24 劉レイ(北京中訊) 新規作成
 */
package webbroker3.tradingpower.handler;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginTypeInfo;

import test.util.TestDBUtility;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.message.WEB3TPAddDepositMailSendRequest;
import webbroker3.tradingpower.message.WEB3TPAddDepositMailSendResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPAddDepositMailSendService;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPAddDepositMailSendHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3TPAddDepositMailSendHandlerTest.class);


    private WEB3TPAddDepositMailSendHandler handler = null;
    
    private WEB3TPAddDepositMailSendService service = null;

    public WEB3TPAddDepositMailSendHandlerTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        handler = new WEB3TPAddDepositMailSendHandler();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
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
    }
    
    public void testWEB3TPAddDepositMailSendHandlerCase0001()
    {
        final String STR_METHOD_NAME = "testWEB3TPAddDepositMailSendHandlerCase0001()";
        log.debug(STR_METHOD_NAME);
        
        WEB3TPAddDepositMailSendRequest l_request = new WEB3TPAddDepositMailSendRequest();
        try
        {
            service =
                (WEB3TPAddDepositMailSendService)Services.getService(WEB3TPAddDepositMailSendService.class);
            Services.unregisterService(WEB3TPAddDepositMailSendService.class);
            WEB3TPAddDepositMailSendResponse l_response = handler.addDepositMailSend(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3TPAddDepositMailSendService.class, service);
        }
        log.debug(STR_METHOD_NAME);
    }

    public void testWEB3TPAddDepositMailSendHandlerCase0002()
    {
        final String STR_METHOD_NAME = "testWEB3TPAddDepositMailSendHandlerCase0002()";
        log.debug(STR_METHOD_NAME);
        
        try
        {
            LoginInfo l_loginInfo = new LoginInfoForTest();
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            WEB3TPAddDepositMailSendRequest l_request = new WEB3TPAddDepositMailSendRequest();
            
            WEB3TPAddDepositMailSendResponse l_response = handler.addDepositMailSend(l_request);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
//            assertEquals(null, l_response);
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.debug(STR_METHOD_NAME);
    }
}
@

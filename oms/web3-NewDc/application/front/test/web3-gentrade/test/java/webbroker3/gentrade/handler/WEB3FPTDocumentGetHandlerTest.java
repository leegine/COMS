head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.04.26.06.01.43;	author liu-lei;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	3004db65fa72760;
filename	WEB3FPTDocumentGetHandlerTest.java;

1.1
date	2011.04.07.02.15.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FPTDocumentGetHandlerTest.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
 Copyright           : (株)大和総研ビジネス・イノベーション
 File Name           : WEB3FPTDocumentGetHandlerTest.java
 Author Name         : Daiwa Institute of Research Business Innovation
 Revision History    : 2010/11/17 劉レイ(北京中訊) 新規作成
 */
package webbroker3.gentrade.handler;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginTypeInfo;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.message.WEB3FPTDocumentGetRequest;
import webbroker3.gentrade.message.WEB3FPTDocumentGetResponse;
import webbroker3.gentrade.service.delegate.WEB3FPTDocumentGetService;
import webbroker3.gentrade.service.delegate.stdimpls.WEB3FPTDocumentGetServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FPTDocumentGetHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FPTDocumentGetHandlerTest.class);

    private WEB3FPTDocumentGetHandler l_handler = null;
    
    private WEB3FPTDocumentGetService l_service = null;
    
    protected void setUp() throws Exception
    {
        super.setUp();
        
        l_handler = new WEB3FPTDocumentGetHandler();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public WEB3FPTDocumentGetHandlerTest(String name)
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

        public Map getLoginAttributes()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Map getLoginTypeAttributes()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Long getDefaultAccountId()
        {
            // TODO Auto-generated method stub
            return null;
        }
    }
    
    private class WEB3FPTDocumentGetServiceImplForTest extends WEB3FPTDocumentGetServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "WEB3FPTDocumentGetServiceImplForTest.execute(WEB3GenRequest)";
            
            log.entering(STR_METHOD_NAME);
            
            // createResponse()
            WEB3FPTDocumentGetResponse l_response = 
                (WEB3FPTDocumentGetResponse)l_request.createResponse();
            
            log.exiting(STR_METHOD_NAME);
            //レスポンス返却
            return l_response;
        }
    }
    
    /**
     * 金商法@書面情報取得サービスを取得に失敗しました。
     * SYSTEM_ERROR_80002
     */
    public void testFptDocumentGetCase0001()
    {
        final String STR_METHOD_NAME = "testFptDocumentGetCase0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FPTDocumentGetRequest l_request =
            new WEB3FPTDocumentGetRequest();
        
        try
        {
            l_service =
                (WEB3FPTDocumentGetService)Services.getService(
                    WEB3FPTDocumentGetService.class);
        
            Services.unregisterService(WEB3FPTDocumentGetService.class);
        
            WEB3FPTDocumentGetResponse l_response =
                l_handler.fptDocumentGet(l_request);
        
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3FPTDocumentGetService.class, l_service);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 金商法@書面情報取得処理に失敗しました。
     * BUSINESS_ERROR_03223
     */
    public void testFptDocumentGetCase0002()
    {
        final String STR_METHOD_NAME = "testFptDocumentGetCase0002()";
        log.entering(STR_METHOD_NAME);
        
        LoginInfo l_loginInfo = new LoginInfoForTest();
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfo);
       
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            
            WEB3FPTDocumentGetRequest l_request =
                new WEB3FPTDocumentGetRequest();
            
            l_request.deliveryTarget = null;
            
            WEB3FPTDocumentGetResponse l_response =
                l_handler.fptDocumentGet(l_request);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03223, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * normal case
     */
    public void testFptDocumentGetCase0003()
    {
        final String STR_METHOD_NAME = "testFptDocumentGetCase0003()";
        log.entering(STR_METHOD_NAME);
        
        l_service =
            (WEB3FPTDocumentGetService)Services.getService(
                WEB3FPTDocumentGetService.class);
    
        Services.unregisterService(WEB3FPTDocumentGetService.class);
        
        Services.registerService(WEB3FPTDocumentGetService.class,
            new WEB3FPTDocumentGetServiceImplForTest());

        WEB3FPTDocumentGetRequest l_request =
            new WEB3FPTDocumentGetRequest();
        
        try
        {
            l_request.deliveryTarget = "0";
            
            WEB3FPTDocumentGetResponse l_response =
                l_handler.fptDocumentGet(l_request);
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@


1.1
log
@*** empty log message ***
@
text
@d140 18
@


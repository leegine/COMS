head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.33.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoFailureOrderInTroubleDownloadHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright           : (株)大和総研 証券ソリューションシステム第二部
 File Name           : WEB3AdminIfoFailureOrderInTroubleDownloadHandlerTest.java
 Author Name         : Daiwa Institute of Research
 Revision History    : 2010/11/01 劉レイ(北京中訊) 新規作成
 */
package webbroker3.ifoadmin.handler;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginTypeInfo;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.ifoadmin.message.WEB3AdminIfoFailureOrderInTroubleDownloadRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoFailureOrderInTroubleDownloadResponse;
import webbroker3.ifoadmin.service.delegate.WEB3AdminIfoFailureOrderInTroubleDownloadService;
import webbroker3.ifoadmin.service.delegate.stdimpls.WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminIfoFailureOrderInTroubleDownloadHandlerTest extends TestBaseForMock
{

    public WEB3AdminIfoFailureOrderInTroubleDownloadHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        
        l_handler = new WEB3AdminIfoFailureOrderInTroubleDownloadHandler();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    WEB3AdminIfoFailureOrderInTroubleDownloadService l_service =  null;
    
    WEB3AdminIfoFailureOrderInTroubleDownloadHandler l_handler =  null;
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminIfoFailureOrderInTroubleDownloadHandlerTest.class);
    
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
    
    private class WEB3AdminIfoFailureOrderInTroubleDownloadServiceImplForTest
        extends WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl
    {
        protected WEB3AdminIfoFailureOrderInTroubleDownloadResponse getDownloadFile(
            WEB3AdminIfoFailureOrderInTroubleDownloadRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "WEB3AdminIfoFailureOrderInTroubleDownloadServiceImplForTest.getDownloadFile()";
            log.entering(STR_METHOD_NAME);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadResponse l_response =
                new WEB3AdminIfoFailureOrderInTroubleDownloadResponse();
            
            log.exiting(STR_METHOD_NAME);
            
            return l_response;
        }
    }
    
    /**
     * 管理者・障害時市場未発注注文ダウンロードサービスを取得に失敗しました。
     * SYSTEM_ERROR_80002
     */
    public void testGetDownloadFileCase0001()
    {
        final String STR_METHOD_NAME = "testGetDownloadFileCase0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIfoFailureOrderInTroubleDownloadRequest l_request =
            new WEB3AdminIfoFailureOrderInTroubleDownloadRequest();
        
        try
        {
            l_service =
                (WEB3AdminIfoFailureOrderInTroubleDownloadService)Services.getService(
                    WEB3AdminIfoFailureOrderInTroubleDownloadService.class);
        
            Services.unregisterService(WEB3AdminIfoFailureOrderInTroubleDownloadService.class);
        
            WEB3AdminIfoFailureOrderInTroubleDownloadResponse l_response =
                l_handler.getDownloadFile(l_request);
        
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminIfoFailureOrderInTroubleDownloadService.class, l_service);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 管理者・障害時市場未発注注文ダウンロードデータ取得・ダウンロード注文処理区分更新処理に失敗しました。
     * BUSINESS_ERROR_03202
     */
    public void testGetDownloadFileCase0002()
    {
        final String STR_METHOD_NAME = "testGetDownloadFileCase0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            LoginInfo l_loginInfo = new LoginInfoForTest();
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            TestDBUtility.deleteAll(AdministratorRow.TYPE);

            WEB3AdminIfoFailureOrderInTroubleDownloadRequest l_request =
                new WEB3AdminIfoFailureOrderInTroubleDownloadRequest();
            
            l_request.corpCode = null;
            
            WEB3AdminIfoFailureOrderInTroubleDownloadResponse l_response = l_handler.getDownloadFile(l_request);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03202, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * normal case
     */
    public void testGetDownloadFileCase0003()
    {
        final String STR_METHOD_NAME = "testGetDownloadFileCase0003()";
        log.entering(STR_METHOD_NAME);
        
        l_service =
            (WEB3AdminIfoFailureOrderInTroubleDownloadService)Services.getService(
                WEB3AdminIfoFailureOrderInTroubleDownloadService.class);
    
        Services.unregisterService(WEB3AdminIfoFailureOrderInTroubleDownloadService.class); 
        
        Services.registerService(WEB3AdminIfoFailureOrderInTroubleDownloadService.class,
            new WEB3AdminIfoFailureOrderInTroubleDownloadServiceImplForTest());

        WEB3AdminIfoFailureOrderInTroubleDownloadRequest l_request =
            new WEB3AdminIfoFailureOrderInTroubleDownloadRequest();
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleDownloadResponse l_response = l_handler.getDownloadFile(l_request);
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

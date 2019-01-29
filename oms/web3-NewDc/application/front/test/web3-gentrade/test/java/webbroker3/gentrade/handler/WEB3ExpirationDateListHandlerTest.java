head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.16.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ExpirationDateListHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ExpirationDateListHandlerTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/19 于瀟（中訊）新規作成
*/
package webbroker3.gentrade.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.message.WEB3ExpirationDateListRequest;
import webbroker3.gentrade.message.WEB3ExpirationDateListResponse;
import webbroker3.gentrade.service.delegate.WEB3ExpirationDateListService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3ExpirationDateListHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ExpirationDateListHandlerTest.class);

    public WEB3ExpirationDateListHandlerTest(String arg0)
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

    /**
     * 注文有効期限取得サービスの取得に失敗しました。
     * 
     */
    public void testExpirationDateListRequest_C0001()
    {
        String STR_METHOD_NAME = "testExpirationDateListRequest_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ExpirationDateListService l_service = null;
        try
        {
            l_service = (WEB3ExpirationDateListService)Services.getService(
                WEB3ExpirationDateListService.class);
            
            Services.unregisterService(WEB3ExpirationDateListService.class);
            
            WEB3ExpirationDateListHandler l_handler = 
                new WEB3ExpirationDateListHandler();
            
            WEB3ExpirationDateListRequest l_request =
                new WEB3ExpirationDateListRequest();
            
            WEB3ExpirationDateListResponse l_response =
                l_handler.expirationDateListRequest(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3ExpirationDateListService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 正常終了
     *
     */
    public void testExpirationDateListRequest_C0002()
    {
        String STR_METHOD_NAME = "testExpirationDateListRequest_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3ExpirationDateListHandler l_handler = 
                new WEB3ExpirationDateListHandler();
            
            WEB3ExpirationDateListRequest l_request =
                new WEB3ExpirationDateListRequest();
            
            WEB3ExpirationDateListResponse l_response =
                new WEB3ExpirationDateListResponse();
            l_response.sessionType = "aaa";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3ExpirationDateListServiceImpl", "execute",
                    new Class[]
                    {WEB3GenRequest.class},
                    l_response);
            assertEquals("aaa", l_handler.expirationDateListRequest(l_request).sessionType);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 注文有効期限取得の処理の実施に失敗しました。
     * 
     * excute() throw WEB3BaseException
     */
    public void testExpirationDateListRequest_C0003()
    {
        String STR_METHOD_NAME = "testExpirationDateListRequest_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3ExpirationDateListHandler l_handler = 
                new WEB3ExpirationDateListHandler();
            
            WEB3ExpirationDateListRequest l_request =
                new WEB3ExpirationDateListRequest();
            
            WEB3BaseException l_exception =
                new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00600, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3ExpirationDateListServiceImpl", "execute",
                new Class[]
                {WEB3GenRequest.class},
                l_exception);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_handler.expirationDateListRequest(l_request).errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 注文有効期限取得の処理の実施に失敗しました。
     * 
     * excute() throw WEB3BaseRuntimeException
     */
    public void testExpirationDateListRequest_C0004()
    {
        String STR_METHOD_NAME = "testExpirationDateListRequest_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3ExpirationDateListHandler l_handler = 
                new WEB3ExpirationDateListHandler();
            
            WEB3ExpirationDateListRequest l_request =
                new WEB3ExpirationDateListRequest();
            
            WEB3BaseRuntimeException l_exception =
                new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_00600, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3ExpirationDateListServiceImpl", "execute",
                new Class[]
                {WEB3GenRequest.class},
                l_exception);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_handler.expirationDateListRequest(l_request).errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@

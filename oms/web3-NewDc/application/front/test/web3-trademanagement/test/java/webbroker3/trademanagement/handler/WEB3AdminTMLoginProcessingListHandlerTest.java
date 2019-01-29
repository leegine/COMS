head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.32.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTMLoginProcessingListHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminTMLoginProcessingListHandlerTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/22 張少傑 (中訊) 新規作成 モデルNo.005
*/
package webbroker3.trademanagement.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryListRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryListResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryReferenceUnit;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginProcessingListService;
import webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginProcessingListServiceImplForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTMLoginProcessingListHandlerTest extends TestBaseForMock
{
    WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMLoginProcessingListHandlerTest.class);
    
    WEB3AdminTMLoginProcessingListService l_service = null;
    WEB3AdminTMLoginProcessingListHandler l_handler = new WEB3AdminTMLoginProcessingListHandler();

    public WEB3AdminTMLoginProcessingListHandlerTest(String arg0)
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
    //管理者・ログイン処理一覧サービスの取得に失敗しました
    public void testGetSearchInputScreen_C001()
    {
        final String STR_METHOD_NAME = "testGetSearchInputScreen_C001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTraderAdminLoginHistoryInputRequest l_request;
        WEB3AdminTraderAdminLoginHistoryInputResponse l_response = null;
        l_request = new WEB3AdminTraderAdminLoginHistoryInputRequest();
        l_handler = new WEB3AdminTMLoginProcessingListHandler();
        try
        {
            l_service =
                (WEB3AdminTMLoginProcessingListService)Services.getService(
                        WEB3AdminTMLoginProcessingListService.class);
            Services.unregisterService(WEB3AdminTMLoginProcessingListService.class);
            l_response = l_handler.getSearchInputScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            
            Services.registerService(WEB3AdminTMLoginProcessingListService.class,
                    new WEB3AdminTMLoginProcessingListServiceImplForMock());
        }     
        log.exiting(STR_METHOD_NAME);
    }
    //管理者・ログイン処理一覧検索入力の表示処理に失敗しました。
    public void testGetSearchInputScreen_C002()
    {
        final String STR_METHOD_NAME = "testGetSearchInputScreen_C002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTraderAdminLoginHistoryInputRequest l_request = new WEB3AdminTraderAdminLoginHistoryInputRequest();
        WEB3AdminTraderAdminLoginHistoryInputResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminTMLoginProcessingListService)Services.getService(
                        WEB3AdminTMLoginProcessingListService.class);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginProcessingListServiceImpl", 
                    "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME));
            l_response = l_handler.getSearchInputScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    //方法@正常返回
    public void testGetSearchInputScreen_C003()
    {
        final String STR_METHOD_NAME = "testGetSearchInputScreen_C003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTraderAdminLoginHistoryInputRequest l_request = new WEB3AdminTraderAdminLoginHistoryInputRequest();
        WEB3AdminTraderAdminLoginHistoryInputResponse l_response = null;
        WEB3AdminTraderAdminLoginHistoryInputResponse l_expectResponse =
            new WEB3AdminTraderAdminLoginHistoryInputResponse(l_request);
        try
        {
            l_response = new WEB3AdminTraderAdminLoginHistoryInputResponse();
            l_service =
                (WEB3AdminTMLoginProcessingListService)Services.getService(
                        WEB3AdminTMLoginProcessingListService.class);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginProcessingListServiceImpl", 
                    "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_expectResponse);
            l_response = l_handler.getSearchInputScreen(l_request);
            assertEquals(l_expectResponse, l_response);
        }
        catch(Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
    }
    //
    public void testGetSearchResultScreen_C001()
    {
        final String STR_METHOD_NAME ="testGetSearchResultScreen_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTraderAdminLoginHistoryListResponse l_response = null;
        WEB3AdminTraderAdminLoginHistoryListRequest l_request =
            new WEB3AdminTraderAdminLoginHistoryListRequest();
        l_handler = new WEB3AdminTMLoginProcessingListHandler();
        try
        {
            l_service =
                (WEB3AdminTMLoginProcessingListService)Services.getService(
                        WEB3AdminTMLoginProcessingListService.class);
            Services.unregisterService(WEB3AdminTMLoginProcessingListService.class);
            l_response = l_handler.getSearchResultScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminTMLoginProcessingListService.class,
                    new WEB3AdminTMLoginProcessingListServiceImplForMock());
        }
        log.exiting(STR_METHOD_NAME);
    }

    //管理者・ログイン処理一覧検索入力の表示処理に失敗しました。
    public void testGetSearchResultScreen_C002()
    {
        final String STR_METHOD_NAME = "testGetSearchResultScreen_C002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTraderAdminLoginHistoryListRequest l_request = 
            new WEB3AdminTraderAdminLoginHistoryListRequest();
        WEB3AdminTraderAdminLoginHistoryListResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminTMLoginProcessingListService)Services.getService(
                        WEB3AdminTMLoginProcessingListService.class);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginProcessingListServiceImpl", 
                    "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME));
            l_response = l_handler.getSearchResultScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    //方法@正常返回
    public void testGetSearchResultScreen_C003()
    {
        final String STR_METHOD_NAME = "testGetSearchResultScreen_C003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTraderAdminLoginHistoryListRequest l_request =
            new WEB3AdminTraderAdminLoginHistoryListRequest();
        WEB3AdminTraderAdminLoginHistoryListResponse l_response = null;
        WEB3AdminTraderAdminLoginHistoryListResponse l_expectResponse =
            new WEB3AdminTraderAdminLoginHistoryListResponse(l_request);
        WEB3AdminTraderAdminLoginHistoryReferenceUnit[] l_adminTraderAdminLoginHistoryReferenceUnits =
            new  WEB3AdminTraderAdminLoginHistoryReferenceUnit[1];
        l_adminTraderAdminLoginHistoryReferenceUnits[0] =
            new WEB3AdminTraderAdminLoginHistoryReferenceUnit();
        l_adminTraderAdminLoginHistoryReferenceUnits[0].branchCode = "102";
        l_adminTraderAdminLoginHistoryReferenceUnits[0].ipAddress = "192.168.255.21";
        l_adminTraderAdminLoginHistoryReferenceUnits[0].orderRootDiv = "02";
        l_expectResponse.totalPages = "11";
        l_expectResponse.pageIndex = "2";
        l_expectResponse.totalRecords = "30";
        l_expectResponse.loginHistoryList = l_adminTraderAdminLoginHistoryReferenceUnits;
        try
        {
            l_response = new WEB3AdminTraderAdminLoginHistoryListResponse();
            l_service =
                (WEB3AdminTMLoginProcessingListService)Services.getService(
                        WEB3AdminTMLoginProcessingListService.class);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginProcessingListServiceImpl", 
                    "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_expectResponse);
            l_response = l_handler.getSearchResultScreen(l_request);
            assertEquals("11", l_response.totalPages);
            assertEquals("2", l_response.pageIndex);
            assertEquals("30", l_response.totalRecords);
            assertEquals("102", l_response.loginHistoryList[0].branchCode);
            assertEquals("192.168.255.21", l_response.loginHistoryList[0].ipAddress);
            assertEquals("02", l_response.loginHistoryList[0].orderRootDiv);
        }
        catch(Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}

@

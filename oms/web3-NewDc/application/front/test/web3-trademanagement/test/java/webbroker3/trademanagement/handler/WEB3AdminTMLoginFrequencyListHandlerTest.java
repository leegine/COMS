head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.32.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTMLoginFrequencyListHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IP別ログイン回数一覧ハンドラTest(WEB3AdminTMLoginFrequencyListHandlerTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/22 劉剣(中訊) 新規作成
*/

package webbroker3.trademanagement.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountListRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountListResponse;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginFrequencyListService;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTMLoginFrequencyListHandlerTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminTMLoginFrequencyListHandlerTest.class);
    
    private WEB3AdminTMLoginFrequencyListHandler l_handler = null;
    private WEB3AdminTMLoginFrequencyListService l_service = null;
    
    public WEB3AdminTMLoginFrequencyListHandlerTest(String arg0)
    {
        super(arg0);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_handler = new WEB3AdminTMLoginFrequencyListHandler();
    }
    
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    /*
     * 管理者・IP別ログイン回数一覧サービスの取得に失敗しました。
     * WEB3ErrorCatalog.SYSTEM_ERROR_80002
     */
    public void testGetSearchInputScreen_C0001()
    {
        String STR_METHOD_NAME = "testGetSearchInputScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3AdminTraderAdminLoginCountInputRequest l_request = new WEB3AdminTraderAdminLoginCountInputRequest();
            
            l_service =
                (WEB3AdminTMLoginFrequencyListService)Services.getService(
                    WEB3AdminTMLoginFrequencyListService.class);
            Services.unregisterService(WEB3AdminTMLoginFrequencyListService.class);
            
            WEB3AdminTraderAdminLoginCountInputResponse l_response= l_handler.getSearchInputScreen(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminTMLoginFrequencyListService.class, l_service);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * IP別ログイン回数一覧の検索入力画面の表示に失敗しました。
     * WEB3ErrorCatalog.SYSTEM_ERROR_80003(WEB3BaseException)
     */
    public void testGetSearchInputScreen_C0002()
    {
        String STR_METHOD_NAME = "testGetSearchInputScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginFrequencyListServiceImpl",
            "execute",
            new Class[]{WEB3GenRequest.class},
            new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, "IP別ログイン回数一覧の検索入力画面の表示に失敗しました。"));
        
        try
        {
            WEB3AdminTraderAdminLoginCountInputRequest l_request = new WEB3AdminTraderAdminLoginCountInputRequest();
            
            WEB3AdminTraderAdminLoginCountInputResponse l_response= l_handler.getSearchInputScreen(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * IP別ログイン回数一覧の検索入力画面の表示に失敗しました。
     * WEB3ErrorCatalog.SYSTEM_ERROR_80004(WEB3BaseRuntimeException)
     */
    public void testGetSearchInputScreen_C0003()
    {
        String STR_METHOD_NAME = "testGetSearchInputScreen_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginFrequencyListServiceImpl",
            "execute",
            new Class[]{WEB3GenRequest.class},
            new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80004, "IP別ログイン回数一覧の検索入力画面の表示に失敗しました。"));
        
        try
        {
            WEB3AdminTraderAdminLoginCountInputRequest l_request = new WEB3AdminTraderAdminLoginCountInputRequest();
            
            WEB3AdminTraderAdminLoginCountInputResponse l_response= l_handler.getSearchInputScreen(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80004, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * normal case
     */
    public void testGetSearchInputScreen_C0004()
    {
        String STR_METHOD_NAME = "testGetSearchInputScreen_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminTraderAdminLoginCountInputResponse l_response = new WEB3AdminTraderAdminLoginCountInputResponse();
        l_response.errorMessage = "101";
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginFrequencyListServiceImpl",
            "execute",
            new Class[]{WEB3GenRequest.class},
            l_response);
        
        try
        {
            WEB3AdminTraderAdminLoginCountInputRequest l_request = new WEB3AdminTraderAdminLoginCountInputRequest();
            
            WEB3AdminTraderAdminLoginCountInputResponse l_responseCheck= l_handler.getSearchInputScreen(l_request);
            
            assertEquals("101", l_responseCheck.errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * 管理者・IP別ログイン回数一覧サービスの取得に失敗しました。
     * WEB3ErrorCatalog.SYSTEM_ERROR_80002
     */
    public void testGetSearchResultScreen_C0001()
    {
        String STR_METHOD_NAME = "testGetSearchResultScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3AdminTraderAdminLoginCountListRequest l_request = new WEB3AdminTraderAdminLoginCountListRequest();
            
            l_service =
                (WEB3AdminTMLoginFrequencyListService)Services.getService(
                    WEB3AdminTMLoginFrequencyListService.class);
            Services.unregisterService(WEB3AdminTMLoginFrequencyListService.class);
            
            WEB3AdminTraderAdminLoginCountListResponse l_response= l_handler.getSearchResultScreen(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminTMLoginFrequencyListService.class, l_service);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * IP別ログイン回数一覧の検索結果画面の表示に失敗しました。
     * WEB3ErrorCatalog.SYSTEM_ERROR_80003(WEB3BaseException)
     */
    public void testGetSearchResultScreen_C0002()
    {
        String STR_METHOD_NAME = "testGetSearchResultScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginFrequencyListServiceImpl",
            "execute",
            new Class[]{WEB3GenRequest.class},
            new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, "IP別ログイン回数一覧の検索結果画面の表示に失敗しました。"));
        
        try
        {
            WEB3AdminTraderAdminLoginCountListRequest l_request = new WEB3AdminTraderAdminLoginCountListRequest();
            
            WEB3AdminTraderAdminLoginCountListResponse l_response= l_handler.getSearchResultScreen(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * IP別ログイン回数一覧の検索結果画面の表示に失敗しました。
     * WEB3ErrorCatalog.SYSTEM_ERROR_80004(WEB3BaseRuntimeException)
     */
    public void testGetSearchResultScreen_C0003()
    {
        String STR_METHOD_NAME = "testGetSearchResultScreen_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginFrequencyListServiceImpl",
            "execute",
            new Class[]{WEB3GenRequest.class},
            new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80004, "IP別ログイン回数一覧の検索結果画面の表示に失敗しました。"));
        
        try
        {
            WEB3AdminTraderAdminLoginCountListRequest l_request = new WEB3AdminTraderAdminLoginCountListRequest();
            
            WEB3AdminTraderAdminLoginCountListResponse l_response= l_handler.getSearchResultScreen(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80004, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * normal case
     */
    public void testGetSearchResultScreen_C0004()
    {
        String STR_METHOD_NAME = "testGetSearchResultScreen_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminTraderAdminLoginCountListResponse l_response = new WEB3AdminTraderAdminLoginCountListResponse();
        l_response.errorMessage = "101";
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginFrequencyListServiceImpl",
            "execute",
            new Class[]{WEB3GenRequest.class},
            l_response);
        
        try
        {
            WEB3AdminTraderAdminLoginCountListRequest l_request = new WEB3AdminTraderAdminLoginCountListRequest();
            
            WEB3AdminTraderAdminLoginCountListResponse l_responseCheck= l_handler.getSearchResultScreen(l_request);
            
            assertEquals("101", l_responseCheck.errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@

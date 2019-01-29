head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.25.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAioSLCashOutStopReleaseHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminAioSLCashOutStopReleaseHandlerTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/20 金傑（中訊）新規作成
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseCompleteRequest;
import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseCompleteResponse;
import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseConfirmRequest;
import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseConfirmResponse;
import webbroker3.aio.message.WEB3AdminSLRestraintMoneyListRequest;
import webbroker3.aio.message.WEB3AdminSLRestraintMoneyListResponse;
import webbroker3.aio.message.WEB3SLCashOutStopInfoUnit;
import webbroker3.aio.service.delegate.WEB3AdminAioSLCashOutStopReleaseService;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLCashOutStopReleaseServiceImplForMock;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAioSLCashOutStopReleaseHandlerTest  extends TestBaseForMock
{

    private WEB3AdminAioSLCashOutStopReleaseHandler l_handler = null;
    
    private WEB3AdminAioSLCashOutStopReleaseService l_service = null;
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3AdminAioSLCashOutStopReleaseHandlerTest.class);
    
    public WEB3AdminAioSLCashOutStopReleaseHandlerTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        this.l_handler = new WEB3AdminAioSLCashOutStopReleaseHandler();
        Services.overrideService(WEB3AdminAioSLCashOutStopReleaseService.class,
            new WEB3AdminAioSLCashOutStopReleaseServiceImplForMock());
    }
    
    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.l_handler = null;
    }
    
    /**
     * 証券担保ローン出金停止解除サービを取得に失敗しました。
     * 
     * 抛出異常：SYSTEM_ERROR_80002
     *
     */
    public void testValidateCashOutStopReleaseConfirmScreen_C0001()
    {
        String STR_METHOD_NAME = "testValidateCashOutStopReleaseConfirmScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(false);
            l_service = (WEB3AdminAioSLCashOutStopReleaseService)Services.getService(
                WEB3AdminAioSLCashOutStopReleaseService.class);
            
            Services.unregisterService(WEB3AdminAioSLCashOutStopReleaseService.class);
            
            WEB3AdminSLCashOutStopReleaseConfirmRequest l_request =
                new WEB3AdminSLCashOutStopReleaseConfirmRequest();
            
            WEB3AdminSLCashOutStopReleaseConfirmResponse l_response =
                this.l_handler.validateCashOutStopReleaseConfirmScreen(l_request);
            
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
            Services.registerService(WEB3AdminAioSLCashOutStopReleaseService.class,l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 証券担保ローン出金停止解除確認画面表示処理が失敗しました。
     * 
     * 抛出異常：SYSTEM_ERROR_80017
     *
     */
    public void testValidateCashOutStopReleaseConfirmScreen_C0002()
    {
        String STR_METHOD_NAME = "testValidateCashOutStopReleaseConfirmScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLCashOutStopReleaseServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,"証券担保ローン出金停止解除確認画面表示処理が失敗しました。"));
            
            WEB3AdminSLCashOutStopReleaseConfirmRequest l_request =
                new WEB3AdminSLCashOutStopReleaseConfirmRequest();
            
            WEB3AdminSLCashOutStopReleaseConfirmResponse l_response =
                this.l_handler.validateCashOutStopReleaseConfirmScreen(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
            
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
     * 正常結束
     *
     */
    public void testValidateCashOutStopReleaseConfirmScreen_C0003()
    {
        String STR_METHOD_NAME = "testValidateCashOutStopReleaseConfirmScreen_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminSLCashOutStopReleaseConfirmResponse l_expectedResponse =
                new WEB3AdminSLCashOutStopReleaseConfirmResponse();
            
            l_expectedResponse.cashOutStopInfo =new WEB3SLCashOutStopInfoUnit();
            l_expectedResponse.cashOutStopInfo.branchCode = "381";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLCashOutStopReleaseServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_expectedResponse);
            
            WEB3AdminSLCashOutStopReleaseConfirmRequest l_request =
                new WEB3AdminSLCashOutStopReleaseConfirmRequest();
            
            WEB3AdminSLCashOutStopReleaseConfirmResponse l_response =
                this.l_handler.validateCashOutStopReleaseConfirmScreen(l_request);
            
            assertEquals("381", l_response.cashOutStopInfo.branchCode);
            
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
     * 証券担保ローン出金停止解除サービを取得に失敗しました。
     * 
     * 抛出異常：SYSTEM_ERROR_80002
     *
     */
    public void testSubmitCashOutStopReleaseCompleteScreen_C0001()
    {
        String STR_METHOD_NAME = "testSubmitCashOutStopReleaseCompleteScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(false);
            l_service = (WEB3AdminAioSLCashOutStopReleaseService)Services.getService(
                WEB3AdminAioSLCashOutStopReleaseService.class);
            
            Services.unregisterService(WEB3AdminAioSLCashOutStopReleaseService.class);
            
            WEB3AdminSLCashOutStopReleaseCompleteRequest l_request =
                new WEB3AdminSLCashOutStopReleaseCompleteRequest();
            
            WEB3AdminSLCashOutStopReleaseCompleteResponse l_response =
                this.l_handler.submitCashOutStopReleaseCompleteScreen(l_request);
            
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
            Services.registerService(WEB3AdminAioSLCashOutStopReleaseService.class,l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 証券担保ローン出金停止解除完了画面表示処理が失敗しました。
     * 
     * 抛出異常：SYSTEM_ERROR_80017
     *
     */
    public void testSubmitCashOutStopReleaseCompleteScreen_C0002()
    {
        String STR_METHOD_NAME = "testSubmitCashOutStopReleaseCompleteScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLCashOutStopReleaseServiceImpl", 
                "execute",
                new Class[]{ WEB3GenRequest.class },
                new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,"証券担保ローン出金停止解除完了画面表示処理が失敗しました。"));

            
            WEB3AdminSLCashOutStopReleaseCompleteRequest l_request =
                new WEB3AdminSLCashOutStopReleaseCompleteRequest();
            
            WEB3AdminSLCashOutStopReleaseCompleteResponse l_response =
                this.l_handler.submitCashOutStopReleaseCompleteScreen(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
            
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
     * 正常結束
     *
     */
    public void testSubmitCashOutStopReleaseCompleteScreen_C0003()
    {
        String STR_METHOD_NAME = "testSubmitCashOutStopReleaseCompleteScreen_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminSLCashOutStopReleaseCompleteResponse l_expectedResponse =
                new WEB3AdminSLCashOutStopReleaseCompleteResponse();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLCashOutStopReleaseServiceImpl", 
                "execute",
                new Class[]{ WEB3GenRequest.class },
                l_expectedResponse);

            
            WEB3AdminSLCashOutStopReleaseCompleteRequest l_request =
                new WEB3AdminSLCashOutStopReleaseCompleteRequest();
            
            WEB3AdminSLCashOutStopReleaseCompleteResponse l_response =
                this.l_handler.submitCashOutStopReleaseCompleteScreen(l_request);
            
            assertNotNull(l_response);
            
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
     * 証券担保ローン出金停止解除サービを取得に失敗しました。
     * 
     * 抛出異常：SYSTEM_ERROR_80002
     *
     */
    public void testGetSLRestraintMoneyListScreen_C0001()
    {
        String STR_METHOD_NAME = "testGetSLRestraintMoneyListScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(false);
            l_service = (WEB3AdminAioSLCashOutStopReleaseService)Services.getService(
                WEB3AdminAioSLCashOutStopReleaseService.class);
            
            Services.unregisterService(WEB3AdminAioSLCashOutStopReleaseService.class);
            
            WEB3AdminSLRestraintMoneyListRequest l_request = new WEB3AdminSLRestraintMoneyListRequest();
            
            WEB3AdminSLRestraintMoneyListResponse l_response =
                this.l_handler.getSLRestraintMoneyListScreen(l_request);
            
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
            Services.registerService(WEB3AdminAioSLCashOutStopReleaseService.class,l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 証券担保ローン出金拘束金一覧画面表示処理が失敗しました。
     * 
     * 抛出異常：SYSTEM_ERROR_80017
     *
     */
    public void testGetSLRestraintMoneyListScreen_C0002()
    {
        String STR_METHOD_NAME = "testGetSLRestraintMoneyListScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLCashOutStopReleaseServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,"証券担保ローン出金拘束金一覧画面表示処理が失敗しました。"));
            
            WEB3AdminSLRestraintMoneyListRequest l_request = new WEB3AdminSLRestraintMoneyListRequest();
            
            WEB3AdminSLRestraintMoneyListResponse l_response =
                this.l_handler.getSLRestraintMoneyListScreen(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
            
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
     * 正常結束
     *
     */
    public void testGetSLRestraintMoneyListScreen_C0003()
    {
        String STR_METHOD_NAME = "testGetSLRestraintMoneyListScreen_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminSLRestraintMoneyListResponse l_expectedResponse = new  WEB3AdminSLRestraintMoneyListResponse();
            
            l_expectedResponse.pageIndex = "2";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLCashOutStopReleaseServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_expectedResponse);
            
            WEB3AdminSLRestraintMoneyListRequest l_request = new WEB3AdminSLRestraintMoneyListRequest();
            
            WEB3AdminSLRestraintMoneyListResponse l_response =
                this.l_handler.getSLRestraintMoneyListScreen(l_request);
            
            assertEquals("2", l_response.pageIndex);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.16.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformSwElecDeliApplyHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminInformSwElecDeliApplyHandlerTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/23 金傑（中訊）新規作成
*/
package webbroker3.inform.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3ComplianceInfoUnit;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyCmpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyCmpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyConfRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyConfResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyInpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyInpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyRefRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyRefResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplySrcRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplySrcResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeCmpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeCmpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeConfRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeConfResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteCmpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteCmpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteConfRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteConfResponse;
import webbroker3.inform.service.delegate.WEB3AdminInformSwElecDeliApplyService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminInformSwElecDeliApplyHandlerTest  extends TestBaseForMock
{
    private WEB3AdminInformSwElecDeliApplyHandler l_handler = null;
    
    private WEB3AdminInformSwElecDeliApplyService l_service = null;

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3InformAccSwElecDeliApplyHandlerTest.class);
    
    public WEB3AdminInformSwElecDeliApplyHandlerTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_handler = new WEB3AdminInformSwElecDeliApplyHandler();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.l_handler = null;
    }
    
    /**
     * 管理者口座切替・電子交付申込サービスを取得に失敗しましたの場合
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80002
     *
     */
    public void testSearchScreenDisplay_C0001()
    {
        String STR_METHOD_NAME = "testDisplayInputScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(false);
        WEB3AdminInformAccSwElecDeliApplySrcRequest l_request = new WEB3AdminInformAccSwElecDeliApplySrcRequest();
        WEB3AdminInformAccSwElecDeliApplySrcResponse l_response = null;
        
        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService) Services.getService(WEB3AdminInformSwElecDeliApplyService.class);
            Services.unregisterService(WEB3AdminInformSwElecDeliApplyService.class);
            
            l_response = l_handler.searchScreenDisplay(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        finally
        {

            Services.registerService(WEB3AdminInformSwElecDeliApplyService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 管理者口座切替・電子交付申込検索に失敗しましたの場合
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80017
     *
     */
    public void testSearchScreenDisplay_C0002()
    {
        String STR_METHOD_NAME = "testDisplayInputScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliApplySrcRequest l_request = new WEB3AdminInformAccSwElecDeliApplySrcRequest();
        WEB3AdminInformAccSwElecDeliApplySrcResponse l_response = null;
        try
        {            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,"管理者口座切替・電子交付申込検索に失敗しました"));
            
            l_response = l_handler.searchScreenDisplay(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 正常結束
     *
     */
    public void testSearchScreenDisplay_C0003()
    {
        String STR_METHOD_NAME = "testSearchScreenDisplay_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliApplySrcRequest l_request = new WEB3AdminInformAccSwElecDeliApplySrcRequest();
        WEB3AdminInformAccSwElecDeliApplySrcResponse l_response = null;
        try
        {           
            
            WEB3AdminInformAccSwElecDeliApplySrcResponse l_expectedResponse = new WEB3AdminInformAccSwElecDeliApplySrcResponse();
            
            WEB3ComplianceInfoUnit complianceInfo = new WEB3ComplianceInfoUnit();
            complianceInfo.branchCode = "381";
            l_expectedResponse.complianceInfo = complianceInfo;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_expectedResponse);
            
            l_response = l_handler.searchScreenDisplay(l_request);
            assertEquals(WEB3AdminInformAccSwElecDeliApplySrcResponse.class, l_response.getClass());
            assertEquals("381", l_response.complianceInfo.branchCode);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 管理者口座切替・電子交付申込サービスを取得に失敗しましたの場合
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80002
     *
     */
    public void testDisplayInputScreen_C0001()
    {
        String STR_METHOD_NAME = "testDisplayInputScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(false);
        WEB3AdminInformAccSwElecDeliApplyInpRequest l_request = new WEB3AdminInformAccSwElecDeliApplyInpRequest();
        WEB3AdminInformAccSwElecDeliApplyInpResponse l_response = null;
        
        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService) Services.getService(WEB3AdminInformSwElecDeliApplyService.class);
            Services.unregisterService(WEB3AdminInformSwElecDeliApplyService.class);
            
            l_response = l_handler.displayInputScreen(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        finally
        {

            Services.registerService(WEB3AdminInformSwElecDeliApplyService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 管理者口座切替・電子交付申込入力画面に失敗しましたの場合
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80002
     *
     */
    public void testDisplayInputScreen_C0002()
    {
        String STR_METHOD_NAME = "testDisplayInputScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminInformAccSwElecDeliApplyInpRequest l_request = new WEB3AdminInformAccSwElecDeliApplyInpRequest();
        WEB3AdminInformAccSwElecDeliApplyInpResponse l_response = null;
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,"管理者口座切替・電子交付申込入力画面に失敗しましたの場合"));
            
            
            l_response = l_handler.displayInputScreen(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 正常結束
     *
     */
    public void testDisplayInputScreen_C0003()
    {
        String STR_METHOD_NAME = "testDisplayInputScreen_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminInformAccSwElecDeliApplyInpRequest l_request = new WEB3AdminInformAccSwElecDeliApplyInpRequest();
        WEB3AdminInformAccSwElecDeliApplyInpResponse l_response = null;
        
        try
        {
            WEB3AdminInformAccSwElecDeliApplyInpResponse l_expectedResponse = new WEB3AdminInformAccSwElecDeliApplyInpResponse();
            l_expectedResponse.accountCode = "1234567";
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_expectedResponse);
            
            
            l_response = l_handler.displayInputScreen(l_request);

            assertEquals(WEB3AdminInformAccSwElecDeliApplyInpResponse.class, l_response.getClass());
            assertEquals("1234567", l_response.accountCode);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 管理者口座切替・電子交付申込サービスを取得に失敗しましたの場合
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80002
     *
     */
    public void testApplyConfirm_C0001()
    {
        String STR_METHOD_NAME = "testDisplayInputScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(false);
        WEB3AdminInformAccSwElecDeliApplyConfRequest l_request = new WEB3AdminInformAccSwElecDeliApplyConfRequest();
        WEB3AdminInformAccSwElecDeliApplyConfResponse l_response = null;
        
        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService) Services.getService(WEB3AdminInformSwElecDeliApplyService.class);
            Services.unregisterService(WEB3AdminInformSwElecDeliApplyService.class);
            
            l_response = l_handler.applyConfirm(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        finally
        {

            Services.registerService(WEB3AdminInformSwElecDeliApplyService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 管理者口座切替・電子交付申込申込確認に失敗しましたの場合
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80002
     *
     */
    public void testApplyConfirm_C0002()
    {
        String STR_METHOD_NAME = "testApplyConfirm_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminInformAccSwElecDeliApplyConfRequest l_request = new WEB3AdminInformAccSwElecDeliApplyConfRequest();
        WEB3AdminInformAccSwElecDeliApplyConfResponse l_response = null;
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,"管理者口座切替・電子交付申込申込確認に失敗しましたの場合"));
            
            
            l_response = l_handler.applyConfirm(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 正常結束
     *
     */
    public void testApplyConfirm_C0003()
    {
        String STR_METHOD_NAME = "testApplyConfirm_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminInformAccSwElecDeliApplyConfRequest l_request = new WEB3AdminInformAccSwElecDeliApplyConfRequest();
        WEB3AdminInformAccSwElecDeliApplyConfResponse l_response = null;
        
        try
        {
            WEB3AdminInformAccSwElecDeliApplyConfResponse l_expectedResponse = new WEB3AdminInformAccSwElecDeliApplyConfResponse();
            
            WEB3ComplianceInfoUnit l_unit = new WEB3ComplianceInfoUnit();
            l_unit.branchCode = "381";
            l_expectedResponse.complianceInfo = l_unit;
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_expectedResponse);
            
            
            l_response = l_handler.applyConfirm(l_request);

            assertEquals(WEB3AdminInformAccSwElecDeliApplyConfResponse.class, l_response.getClass());
            assertEquals("381", l_response.complianceInfo.branchCode);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 管理者口座切替・電子交付申込サービスを取得に失敗しましたの場合
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80002
     *
     */
    public void testApplyComplete_C0001()
    {
        String STR_METHOD_NAME = "testDisplayInputScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(false);
        WEB3AdminInformAccSwElecDeliApplyCmpRequest l_request = new WEB3AdminInformAccSwElecDeliApplyCmpRequest();
        WEB3AdminInformAccSwElecDeliApplyCmpResponse l_response = null;
        
        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService) Services.getService(WEB3AdminInformSwElecDeliApplyService.class);
            Services.unregisterService(WEB3AdminInformSwElecDeliApplyService.class);
            
            l_response = l_handler.applyComplete(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        finally
        {

            Services.registerService(WEB3AdminInformSwElecDeliApplyService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 管理者口座切替・電子交付申込申込完了に失敗しましたの場合
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80002
     *
     */
    public void testApplyComplete_C0002()
    {
        String STR_METHOD_NAME = "testDisplayInputScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminInformAccSwElecDeliApplyCmpRequest l_request = new WEB3AdminInformAccSwElecDeliApplyCmpRequest();
        WEB3AdminInformAccSwElecDeliApplyCmpResponse l_response = null;
        
        try
        {
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,"管理者口座切替・電子交付申込申込完了に失敗しましたの場合"));
            
            l_response = l_handler.applyComplete(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 正常結束
     *
     */
    public void testApplyComplete_C0003()
    {
        String STR_METHOD_NAME = "testDisplayInputScreen_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminInformAccSwElecDeliApplyCmpRequest l_request = new WEB3AdminInformAccSwElecDeliApplyCmpRequest();
        WEB3AdminInformAccSwElecDeliApplyCmpResponse l_response = null;
        
        try
        {
            WEB3AdminInformAccSwElecDeliApplyCmpResponse l_expectedResponse = new WEB3AdminInformAccSwElecDeliApplyCmpResponse();
            
            
            WEB3ComplianceInfoUnit complianceInfo = new WEB3ComplianceInfoUnit();
            complianceInfo.branchCode = "381";
            l_expectedResponse.complianceInfo = complianceInfo;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_expectedResponse);
            
            l_response = l_handler.applyComplete(l_request);

            assertEquals(WEB3AdminInformAccSwElecDeliApplyCmpResponse.class, l_response.getClass());
            assertEquals("381", l_response.complianceInfo.branchCode);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 管理者口座切替・電子交付申込サービスを取得に失敗しましたの場合
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80002
     */
    public void testDisplayReferenceScreen_C0001()
    {
        final String STR_METHOD_NAME = "testDisplayReferenceScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliApplyRefRequest l_request =
            new WEB3AdminInformAccSwElecDeliApplyRefRequest();
        WEB3AdminInformAccSwElecDeliApplyRefResponse l_response = null;

        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService)Services.getService(
                WEB3AdminInformSwElecDeliApplyService.class);
            Services.unregisterService(WEB3AdminInformSwElecDeliApplyService.class);

            l_response = l_handler.displayReferenceScreen(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {

            Services.registerService(WEB3AdminInformSwElecDeliApplyService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 管理者・口座切替・電子交付申込参照に失敗しましたの場合
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80017
     *
     */
    public void testDisplayReferenceScreen_C0002()
    {
        final String STR_METHOD_NAME = "testDisplayReferenceScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminInformAccSwElecDeliApplyRefRequest l_request = new WEB3AdminInformAccSwElecDeliApplyRefRequest();
        WEB3AdminInformAccSwElecDeliApplyRefResponse l_response = null;

        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImpl",
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        "管理者・口座切替・電子交付申込参照に失敗しましたの場合"));

            l_response = l_handler.displayReferenceScreen(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 正常終了
     *
     */
    public void testDisplayReferenceScreen_C0003()
    {
        final String STR_METHOD_NAME = "testDisplayReferenceScreen_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminInformAccSwElecDeliApplyRefRequest l_request = new WEB3AdminInformAccSwElecDeliApplyRefRequest();
        WEB3AdminInformAccSwElecDeliApplyRefResponse l_response = null;

        try
        {
            WEB3AdminInformAccSwElecDeliApplyRefResponse l_expectedResponse =
                new WEB3AdminInformAccSwElecDeliApplyRefResponse();

            l_expectedResponse.accountCode = "320";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImpl",
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_expectedResponse);

            l_response = l_handler.displayReferenceScreen(l_request);

            assertEquals("320", l_response.accountCode);

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 管理者口座切替・電子交付申込サービスを取得に失敗しましたの場合
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80002
     */
    public void testChangeConfirm_C0001()
    {
        final String STR_METHOD_NAME = "testChangeConfirm_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliChangeConfRequest l_request =
            new WEB3AdminInformAccSwElecDeliChangeConfRequest();
        WEB3AdminInformAccSwElecDeliChangeConfResponse l_response = null;

        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService)Services.getService(
                WEB3AdminInformSwElecDeliApplyService.class);
            Services.unregisterService(WEB3AdminInformSwElecDeliApplyService.class);

            l_response = l_handler.changeConfirm(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {

            Services.registerService(WEB3AdminInformSwElecDeliApplyService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 管理者・口座切替・電子交付申込変更確認に失敗しましたの場合
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80017
     *
     */
    public void testChangeConfirm_C0002()
    {
        final String STR_METHOD_NAME = "testChangeConfirm_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminInformAccSwElecDeliChangeConfRequest l_request = new WEB3AdminInformAccSwElecDeliChangeConfRequest();
        WEB3AdminInformAccSwElecDeliChangeConfResponse l_response = null;

        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImpl",
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        "管理者・口座切替・電子交付申込変更確認に失敗しましたの場合"));

            l_response = l_handler.changeConfirm(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 正常終了
     *
     */
    public void testChangeConfirm_C0003()
    {
        final String STR_METHOD_NAME = "testChangeConfirm_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminInformAccSwElecDeliChangeConfRequest l_request = new WEB3AdminInformAccSwElecDeliChangeConfRequest();
        WEB3AdminInformAccSwElecDeliChangeConfResponse l_response = null;

        try
        {
            WEB3AdminInformAccSwElecDeliChangeConfResponse l_expectedResponse =
                new WEB3AdminInformAccSwElecDeliChangeConfResponse();

            l_expectedResponse.errorMessage = "320";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImpl",
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_expectedResponse);

            l_response = l_handler.changeConfirm(l_request);

            assertEquals("320", l_response.errorMessage);

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 管理者口座切替・電子交付申込サービスを取得に失敗しましたの場合
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80002
     */
    public void testChangeComplete_C0001()
    {
        final String STR_METHOD_NAME = "testChangeComplete_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliChangeCmpRequest l_request =
            new WEB3AdminInformAccSwElecDeliChangeCmpRequest();
        WEB3AdminInformAccSwElecDeliChangeCmpResponse l_response = null;

        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService)Services.getService(
                WEB3AdminInformSwElecDeliApplyService.class);
            Services.unregisterService(WEB3AdminInformSwElecDeliApplyService.class);

            l_response = l_handler.changeComplete(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {

            Services.registerService(WEB3AdminInformSwElecDeliApplyService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 管理者・口座切替・電子交付申込変更完了に失敗しましたの場合
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80017
     *
     */
    public void testChangeComplete_C0002()
    {
        final String STR_METHOD_NAME = "testChangeComplete_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminInformAccSwElecDeliChangeCmpRequest l_request =
            new WEB3AdminInformAccSwElecDeliChangeCmpRequest();
        WEB3AdminInformAccSwElecDeliChangeCmpResponse l_response = null;

        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImpl",
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        "管理者・口座切替・電子交付申込変更完了に失敗しましたの場合"));

            l_response = l_handler.changeComplete(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 正常終了
     *
     */
    public void testChangeComplete_C0003()
    {
        final String STR_METHOD_NAME = "testChangeComplete_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminInformAccSwElecDeliChangeCmpRequest l_request =
            new WEB3AdminInformAccSwElecDeliChangeCmpRequest();
        WEB3AdminInformAccSwElecDeliChangeCmpResponse l_response = null;

        try
        {
            WEB3AdminInformAccSwElecDeliChangeCmpResponse l_expectedResponse =
                new WEB3AdminInformAccSwElecDeliChangeCmpResponse();

            l_expectedResponse.errorMessage = "320";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImpl",
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_expectedResponse);

            l_response = l_handler.changeComplete(l_request);

            assertEquals("320", l_response.errorMessage);

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 管理者口座切替・電子交付申込サービスを取得に失敗しましたの場合
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80002
     */
    public void testDeleteConfirm_C0001()
    {
        final String STR_METHOD_NAME = "testDeleteConfirm_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliDeleteConfRequest l_request =
            new WEB3AdminInformAccSwElecDeliDeleteConfRequest();
        WEB3AdminInformAccSwElecDeliDeleteConfResponse l_response = null;

        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService)Services.getService(
                WEB3AdminInformSwElecDeliApplyService.class);
            Services.unregisterService(WEB3AdminInformSwElecDeliApplyService.class);

            l_response = l_handler.deleteConfirm(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {

            Services.registerService(WEB3AdminInformSwElecDeliApplyService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 管理者・口座切替・電子交付申込取消確認に失敗しましたの場合
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80017
     *
     */
    public void testDeleteConfirm_C0002()
    {
        final String STR_METHOD_NAME = "testDeleteConfirm_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminInformAccSwElecDeliDeleteConfRequest l_request = new WEB3AdminInformAccSwElecDeliDeleteConfRequest();
        WEB3AdminInformAccSwElecDeliDeleteConfResponse l_response = null;

        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImpl",
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        "管理者・口座切替・電子交付申込取消確認に失敗しましたの場合"));

            l_response = l_handler.deleteConfirm(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 正常終了
     *
     */
    public void testDeleteConfirm_C0003()
    {
        final String STR_METHOD_NAME = "testDeleteConfirm_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminInformAccSwElecDeliDeleteConfRequest l_request = new WEB3AdminInformAccSwElecDeliDeleteConfRequest();
        WEB3AdminInformAccSwElecDeliDeleteConfResponse l_response = null;

        try
        {
            WEB3AdminInformAccSwElecDeliDeleteConfResponse l_expectedResponse =
                new WEB3AdminInformAccSwElecDeliDeleteConfResponse();

            l_expectedResponse.errorMessage = "320";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImpl",
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_expectedResponse);

            l_response = l_handler.deleteConfirm(l_request);

            assertEquals("320", l_response.errorMessage);

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 管理者口座切替・電子交付申込サービスを取得に失敗しましたの場合
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80002
     */
    public void testDeleteComplete_C0001()
    {
        final String STR_METHOD_NAME = "testDeleteComplete_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliDeleteCmpRequest l_request =
            new WEB3AdminInformAccSwElecDeliDeleteCmpRequest();
        WEB3AdminInformAccSwElecDeliDeleteCmpResponse l_response = null;

        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService)Services.getService(
                WEB3AdminInformSwElecDeliApplyService.class);
            Services.unregisterService(WEB3AdminInformSwElecDeliApplyService.class);

            l_response = l_handler.deleteComplete(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {

            Services.registerService(WEB3AdminInformSwElecDeliApplyService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 管理者・口座切替・電子交付申込取消完了に失敗しましたの場合
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80017
     *
     */
    public void testDeleteComplete_C0002()
    {
        final String STR_METHOD_NAME = "testDeleteComplete_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminInformAccSwElecDeliDeleteCmpRequest l_request =
            new WEB3AdminInformAccSwElecDeliDeleteCmpRequest();
        WEB3AdminInformAccSwElecDeliDeleteCmpResponse l_response = null;

        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImpl",
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        "管理者・口座切替・電子交付申込取消完了に失敗しましたの場合"));

            l_response = l_handler.deleteComplete(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 正常終了
     *
     */
    public void testDeleteComplete_C0003()
    {
        final String STR_METHOD_NAME = "testDeleteComplete_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminInformAccSwElecDeliDeleteCmpRequest l_request =
            new WEB3AdminInformAccSwElecDeliDeleteCmpRequest();
        WEB3AdminInformAccSwElecDeliDeleteCmpResponse l_response = null;

        try
        {
            WEB3AdminInformAccSwElecDeliDeleteCmpResponse l_expectedResponse =
                new WEB3AdminInformAccSwElecDeliDeleteCmpResponse();

            l_expectedResponse.errorMessage = "320";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImpl",
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_expectedResponse);

            l_response = l_handler.deleteComplete(l_request);

            assertEquals("320", l_response.errorMessage);

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@

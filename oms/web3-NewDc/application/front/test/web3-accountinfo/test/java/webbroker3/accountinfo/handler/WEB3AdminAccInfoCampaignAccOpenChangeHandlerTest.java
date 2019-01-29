head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.40.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AdminAccInfoCampaignAccOpenChangeHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設条件指定変更ハンドラテスト(WEB3AdminAccInfoCampaignAccOpenChangeHandlerTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 魏 (中訊) 新規作成
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.accountinfo.message.WEB3AccInfoCampaignInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenInputResponse;

import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignAccOpenChangeService;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccInfoCampaignAccOpenChangeHandlerTest extends TestBaseForMock
{   
    private WEB3AdminAccInfoCampaignAccOpenInputRequest l_inputRequest = null;
    
    private WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_confirmRequest = null;
    
    private WEB3AdminAccInfoCampaignAccOpenCompleteRequest l_completeRequest = null;

    private WEB3AdminAccInfoCampaignAccOpenChangeHandler l_handler = null;
    
    private WEB3AdminAccInfoCampaignAccOpenChangeService l_service = null;
    
    private WEB3AdminAccInfoCampaignAccOpenInputResponse l_inputResponse = null;
    
    private WEB3AdminAccInfoCampaignAccOpenConfirmResponse l_confirmResponse = null;
    
    private WEB3AdminAccInfoCampaignAccOpenCompleteResponse l_completeResponse = null;

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignAccOpenChangeHandlerTest.class);
    
    public WEB3AdminAccInfoCampaignAccOpenChangeHandlerTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_handler = new WEB3AdminAccInfoCampaignAccOpenChangeHandler();
        
    }
    
    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.l_handler = null;
        this.l_service = null;
    }
    
    public void test_changeConfirmScreenProcess_C0001()
    {
        final String STR_METHOD_NAME = "test_changeConfirmScreenProcess_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            l_confirmResponse = this.l_handler.changeConfirmScreenProcess(l_confirmRequest);
            fail();
        }
        catch (WEB3BaseRuntimeException l_expectedException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_expectedException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_changeConfirmScreenProcess_C0002()
    {
        final String STR_METHOD_NAME = "test_changeConfirmScreenProcess_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_service = (WEB3AdminAccInfoCampaignAccOpenChangeService)Services.getService(WEB3AdminAccInfoCampaignAccOpenChangeService.class);
            Services.unregisterService(WEB3AdminAccInfoCampaignAccOpenChangeService.class);
            
            l_confirmRequest = new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
            
            l_confirmResponse = this.l_handler.changeConfirmScreenProcess(l_confirmRequest);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_confirmResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminAccInfoCampaignAccOpenChangeService.class,l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_changeConfirmScreenProcess_C0003()
    {
        final String STR_METHOD_NAME = "test_changeConfirmScreenProcess_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        l_confirmRequest = new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        WEB3AdminAccInfoCampaignAccOpenConfirmResponse l_expectResponse = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmResponse(l_confirmRequest);
        
        WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
        l_expectResponse.commissionCampaignInfo = l_info;
    
    
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl", 
                "execute",
                new Class[] {WEB3GenRequest.class}, 
                l_expectResponse);
        try
        {
            l_confirmResponse = this.l_handler.changeConfirmScreenProcess(l_confirmRequest);
            assertNotNull(l_confirmResponse);
            assertEquals(WEB3AdminAccInfoCampaignAccOpenConfirmResponse.class, l_confirmResponse.getClass());
            assertEquals(l_confirmResponse.commissionCampaignInfo, l_info);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_changeCompleteScreenProcess_C0001()
    {
        final String STR_METHOD_NAME = "test_changeCompleteScreenProcess_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            l_completeResponse = this.l_handler.changeCompleteScreenProcess(l_completeRequest);
            fail();
        }
        catch (WEB3BaseRuntimeException l_expectedException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_expectedException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_changeCompleteScreenProcess_C0002()
    {
        final String STR_METHOD_NAME = "test_changeCompleteScreenProcess_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_service = (WEB3AdminAccInfoCampaignAccOpenChangeService)Services.getService(WEB3AdminAccInfoCampaignAccOpenChangeService.class);
            Services.unregisterService(WEB3AdminAccInfoCampaignAccOpenChangeService.class);
            
            l_completeRequest = new WEB3AdminAccInfoCampaignAccOpenCompleteRequest();
            
            l_completeResponse = this.l_handler.changeCompleteScreenProcess(l_completeRequest);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_completeResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminAccInfoCampaignAccOpenChangeService.class,l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_changeCompleteScreenProcess_C0003()
    {
        final String STR_METHOD_NAME = "test_inputScreenProcess_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        l_completeRequest = new WEB3AdminAccInfoCampaignAccOpenCompleteRequest();
        
        WEB3AdminAccInfoCampaignAccOpenCompleteResponse l_expectResponse = 
            new WEB3AdminAccInfoCampaignAccOpenCompleteResponse(l_completeRequest);
        
        l_expectResponse.alertFlag = "alertFlag";
    
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl", 
                "execute",
                new Class[] {WEB3GenRequest.class}, 
                l_expectResponse);
        try
        {
            l_completeResponse = this.l_handler.changeCompleteScreenProcess(l_completeRequest);
            assertNotNull(l_completeResponse);
            assertEquals(WEB3AdminAccInfoCampaignAccOpenCompleteResponse.class, l_completeResponse.getClass());
            assertEquals(l_completeResponse.alertFlag, "alertFlag");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    public void test_inputScreenProcess_C0001()
    {
        final String STR_METHOD_NAME = "test_inputScreenProcess_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            l_inputResponse = this.l_handler.inputScreenProcess(l_inputRequest);
            fail();
        }
        catch (WEB3BaseRuntimeException l_expectedException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_expectedException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_inputScreenProcess_C0002()
    {
        final String STR_METHOD_NAME = "test_inputScreenProcess_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_service = (WEB3AdminAccInfoCampaignAccOpenChangeService)Services.getService(WEB3AdminAccInfoCampaignAccOpenChangeService.class);
            Services.unregisterService(WEB3AdminAccInfoCampaignAccOpenChangeService.class);
            
            l_inputRequest = new WEB3AdminAccInfoCampaignAccOpenInputRequest();
            
            l_inputResponse = this.l_handler.inputScreenProcess(l_inputRequest);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_inputResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminAccInfoCampaignAccOpenChangeService.class,l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_inputScreenProcess_C0003()
    {
        final String STR_METHOD_NAME = "test_inputScreenProcess_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        l_inputRequest = new WEB3AdminAccInfoCampaignAccOpenInputRequest();
        
        WEB3AdminAccInfoCampaignAccOpenInputResponse l_expectResponse = 
            new WEB3AdminAccInfoCampaignAccOpenInputResponse(l_inputRequest);
        
        WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
        l_expectResponse.commissionCampaignInfo = l_info;
    
    
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl", 
                "execute",
                new Class[] {WEB3GenRequest.class}, 
                l_expectResponse);
        try
        {
            l_inputResponse = this.l_handler.inputScreenProcess(l_inputRequest);
            assertNotNull(l_inputResponse);
            assertEquals(WEB3AdminAccInfoCampaignAccOpenInputResponse.class, l_inputResponse.getClass());
            assertEquals(l_inputResponse.commissionCampaignInfo, l_info);
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

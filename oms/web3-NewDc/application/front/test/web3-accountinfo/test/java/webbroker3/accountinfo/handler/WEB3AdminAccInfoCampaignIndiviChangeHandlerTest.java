head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.40.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AdminAccInfoCampaignIndiviChangeHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminAccInfoCampaignIndiviChangeHandlerTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/8  齊珂(中訊) 新規作成
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import test.util.TestDBUtility;
import webbroker3.accountinfo.message.WEB3AccInfoCampaignInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignIndiviChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccInfoCampaignIndiviChangeHandlerTest extends
    TestBaseForMock
{
    private WEB3AdminAccInfoCampaignIndiviInputRequest l_request = null;

    private WEB3AdminAccInfoCampaignIndiviChangeHandler l_handler = null;

    private WEB3AdminAccInfoCampaignIndiviChangeService l_service = null;
    
    private WEB3AdminAccInfoCampaignIndiviInputResponse l_response = null;
    
    private WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request2 = null;
    
    private WEB3AdminAccInfoCampaignIndiviConfirmResponse l_response2 = null;
    
    private WEB3AdminAccInfoCampaignIndiviCompleteRequest l_request3 = null;
    
    private WEB3AdminAccInfoCampaignIndiviCompleteResponse l_response3 = null;
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignIndiviChangeHandlerTest.class);
    
    
    public WEB3AdminAccInfoCampaignIndiviChangeHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        this.l_handler = new WEB3AdminAccInfoCampaignIndiviChangeHandler();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.l_handler = null;
        this.l_service = null;
    }

    public void test_inputScreenDisplay_C0001()
    {
        final String STR_METHOD_NAME = "test_inputScreenDisplay_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignIndiviChangeServiceImpl",
                "execute",
                new Class[] {WEB3GenRequest.class}, 
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ""));
            
            l_request = new WEB3AdminAccInfoCampaignIndiviInputRequest();
            l_response = this.l_handler.inputScreenDisplay(l_request);
        }
        catch (WEB3BaseRuntimeException l_response)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_inputScreenDisplay_C0002()
    {
        final String STR_METHOD_NAME = "test_searchExecuteReference_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            l_service = 
                (WEB3AdminAccInfoCampaignIndiviChangeService)Services.getService(
                    WEB3AdminAccInfoCampaignIndiviChangeService.class);
            Services.unregisterService(WEB3AdminAccInfoCampaignIndiviChangeService.class);
            l_request = new WEB3AdminAccInfoCampaignIndiviInputRequest();
            l_response = this.l_handler.inputScreenDisplay(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminAccInfoCampaignIndiviChangeService.class,l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_inputScreenDisplay_C0003()
    {
        final String STR_METHOD_NAME = "test_inputScreenDisplay_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        l_request = new WEB3AdminAccInfoCampaignIndiviInputRequest();
        WEB3AdminAccInfoCampaignIndiviInputResponse l_expectResponse = 
            (WEB3AdminAccInfoCampaignIndiviInputResponse) l_request.createResponse();
     
        try
        {
            
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.branchCode = "0";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignCondition",
                new Class[] {String.class},
                l_info);
               
            
            WEB3AdminAccInfoCampaignIndiviInputRequest l_request = 
                new WEB3AdminAccInfoCampaignIndiviInputRequest();
            
            l_request.updateFlag = "1";
            l_request.campaignId = "1";
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
                   
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);
            
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "0", true);
            
            l_response = this.l_handler.inputScreenDisplay(l_request);
            assertNotNull(l_response);
            assertEquals(WEB3AdminAccInfoCampaignIndiviInputResponse.class, l_response.getClass());
            assertEquals("0", l_response.commissionCampaignInfo.branchCode);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    public void test_IndiviChangeConfirm_C0001()
    {
        final String STR_METHOD_NAME = "test_IndiviChangeConfirm_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignIndiviChangeServiceImpl",
                "execute",
                new Class[] {WEB3GenRequest.class}, 
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, ""));
            
            l_request2 = new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
            l_response2 = this.l_handler.IndiviChangeConfirm(l_request2);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response2.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_IndiviChangeConfirm_C0002()
    {
        final String STR_METHOD_NAME = "test_IndiviChangeConfirm_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            l_service = 
                (WEB3AdminAccInfoCampaignIndiviChangeService)Services.getService(
                    WEB3AdminAccInfoCampaignIndiviChangeService.class);
            Services.unregisterService(WEB3AdminAccInfoCampaignIndiviChangeService.class);
            l_request2 = new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
            l_response2 = this.l_handler.IndiviChangeConfirm(l_request2);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response2.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminAccInfoCampaignIndiviChangeService.class,l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_IndiviChangeConfirm_C0003()
    {
        final String STR_METHOD_NAME = "test_IndiviChangeConfirm_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        l_request2 = new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        WEB3AdminAccInfoCampaignIndiviConfirmResponse l_expectResponse = 
            (WEB3AdminAccInfoCampaignIndiviConfirmResponse) l_request2.createResponse();
        l_expectResponse.alertFlag = "0";
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignIndiviChangeServiceImpl",
                "execute",
                new Class[] {WEB3GenRequest.class}, 
                l_expectResponse);
            
            l_expectResponse = this.l_handler.IndiviChangeConfirm(l_request2);
            assertNotNull(l_expectResponse);
            assertEquals(WEB3AdminAccInfoCampaignIndiviConfirmResponse.class, l_expectResponse.getClass());
            assertEquals("0", l_expectResponse.alertFlag);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    
    public void test_IndiviChangeComplete_C0001()
    {
        final String STR_METHOD_NAME = "test_IndiviChangeComplete_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignIndiviChangeServiceImpl",
                "execute",
                new Class[] {WEB3GenRequest.class}, 
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, ""));
            
            l_request3 = new WEB3AdminAccInfoCampaignIndiviCompleteRequest();
            l_response3 = this.l_handler.IndiviChangeComplete(l_request3);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response3.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_IndiviChangeComplete_C0002()
    {
        final String STR_METHOD_NAME = "test_IndiviChangeComplete_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            l_service = 
                (WEB3AdminAccInfoCampaignIndiviChangeService)Services.getService(
                    WEB3AdminAccInfoCampaignIndiviChangeService.class);
            Services.unregisterService(WEB3AdminAccInfoCampaignIndiviChangeService.class);
            l_request3 = new WEB3AdminAccInfoCampaignIndiviCompleteRequest();
            l_response3 = this.l_handler.IndiviChangeComplete(l_request3);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response3.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminAccInfoCampaignIndiviChangeService.class,l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_IndiviChangeComplete_C0003()
    {
        final String STR_METHOD_NAME = "test_IndiviChangeComplete_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        l_request3 = new WEB3AdminAccInfoCampaignIndiviCompleteRequest();
        
        WEB3AdminAccInfoCampaignIndiviCompleteResponse l_expectResponse = 
            (WEB3AdminAccInfoCampaignIndiviCompleteResponse) l_request3.createResponse();
        l_expectResponse.alertFlag = "0";
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignIndiviChangeServiceImpl",
                "execute",
                new Class[] {WEB3GenRequest.class}, 
                l_expectResponse);
            
            l_expectResponse = this.l_handler.IndiviChangeComplete(l_request3);
            assertNotNull(l_expectResponse);
            assertEquals(WEB3AdminAccInfoCampaignIndiviCompleteResponse.class, l_expectResponse.getClass());
            assertEquals("0", l_expectResponse.alertFlag);
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

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.48.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminMutualConditionsHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;


import webbroker3.accountinfo.message.WEB3AccInfoCampaignInfo;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualConditionsConfirmRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsConfirmResponse;
import webbroker3.mf.message.WEB3AdminMutualConditionsInputRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsInputResponse;
import webbroker3.mf.service.delegate.WEB3AdminMutualConditionsService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminMutualConditionsHandlerTest extends TestBaseForMock
{

    private WEB3AdminMutualConditionsInputRequest l_request = null;

    private WEB3AdminMutualConditionsInputResponse l_response = null;

    private WEB3AdminMutualConditionsConfirmRequest l_request1 = null;

    private WEB3AdminMutualConditionsConfirmResponse l_response1 = null;

    private WEB3AdminMutualConditionsCompleteRequest l_request2 = null;

    private WEB3AdminMutualConditionsCompleteResponse l_response2 = null;

    private WEB3AdminMutualConditionsService l_service = null;

    private WEB3AdminMutualConditionsHandler l_handler;
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualConditionsHandlerTest.class);

    public WEB3AdminMutualConditionsHandlerTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        this.l_handler = new WEB3AdminMutualConditionsHandler();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.l_handler = null;
    }

    public void testProductConditionsRegistInputRequest_C0001()
    {
        final String STR_METHOD_NAME = "testProductConditionsRegistInputRequest_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_service =
                (WEB3AdminMutualConditionsService)Services.getService(
                        WEB3AdminMutualConditionsService.class);
            Services.unregisterService(WEB3AdminMutualConditionsService.class);

            l_request = new WEB3AdminMutualConditionsInputRequest();
            l_response = this.l_handler.productConditionsRegistInputRequest(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminMutualConditionsService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testProductConditionsRegistInputRequest_C0002()
    {
        final String STR_METHOD_NAME = "testProductConditionsRegistInputRequest_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualConditionsServiceImpl",
                "execute",
                new Class[] {WEB3GenRequest.class}, 
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ""));

            l_request = new WEB3AdminMutualConditionsInputRequest();
            l_response =
                this.l_handler.productConditionsRegistInputRequest(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testProductConditionsRegistInputRequest_C0003()
    {
        final String STR_METHOD_NAME = "testProductConditionsRegistInputRequest_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        l_request = new WEB3AdminMutualConditionsInputRequest();
        WEB3AdminMutualConditionsInputResponse l_expectResponse = 
            (WEB3AdminMutualConditionsInputResponse)l_request.createResponse();
     
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualConditionsServiceImpl",
                "execute",
                new Class[] {WEB3GenRequest.class}, 
                l_expectResponse);

            l_response = this.l_handler.productConditionsRegistInputRequest(l_request);

            assertNotNull(l_response);
            assertEquals(l_expectResponse, l_response);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSearchProductConditionsRegist_C0001()
    {
        final String STR_METHOD_NAME = "testSearchProductConditionsRegist_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_service =
                (WEB3AdminMutualConditionsService)Services.getService(
                    WEB3AdminMutualConditionsService.class);

            Services.unregisterService(WEB3AdminMutualConditionsService.class);

            l_request1 = new WEB3AdminMutualConditionsConfirmRequest();
            l_response1 = this.l_handler.searchProductConditionsRegist(l_request1);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response1.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminMutualConditionsService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSearchProductConditionsRegist_C0002()
    {
        final String STR_METHOD_NAME = "testSearchProductConditionsRegist_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualConditionsServiceImpl",
                "execute",
                new Class[] {WEB3GenRequest.class}, 
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ""));

            l_request1 = new WEB3AdminMutualConditionsConfirmRequest();
            l_response1 =
                this.l_handler.searchProductConditionsRegist(l_request1);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response1.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSearchProductConditionsRegist_C0003()
    {
        final String STR_METHOD_NAME = "testProductConditionsRegistInputRequest_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        l_request1 = new WEB3AdminMutualConditionsConfirmRequest();
        WEB3AdminMutualConditionsConfirmResponse l_expectResponse1 = 
            (WEB3AdminMutualConditionsConfirmResponse)l_request1.createResponse();

        try
        {
            
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.branchCode = "0";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualConditionsServiceImpl",
                "execute",
                new Class[] {WEB3GenRequest.class}, 
                l_expectResponse1);

            l_response1 = this.l_handler.searchProductConditionsRegist(l_request1);

            assertNotNull(l_response1);
            assertEquals(l_expectResponse1, l_response1);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCompleteProductConditionsRegist_C0001()
    {
        final String STR_METHOD_NAME = "testCompleteProductConditionsRegist_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_service =
                (WEB3AdminMutualConditionsService)Services.getService(
                    WEB3AdminMutualConditionsService.class);

            Services.unregisterService(WEB3AdminMutualConditionsService.class);

            l_request2 = new WEB3AdminMutualConditionsCompleteRequest();
            l_response2 = this.l_handler.completeProductConditionsRegist(l_request2);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response2.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminMutualConditionsService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCompleteProductConditionsRegist_C0002()
    {
        final String STR_METHOD_NAME = "testCompleteProductConditionsRegist_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualConditionsServiceImpl",
                "execute",
                new Class[] {WEB3GenRequest.class}, 
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ""));

            l_request2 = new WEB3AdminMutualConditionsCompleteRequest();
            l_response2 =
                this.l_handler.completeProductConditionsRegist(l_request2);
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response2.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCompleteProductConditionsRegist_C0003()
    {
        final String STR_METHOD_NAME = "testCompleteProductConditionsRegist_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request2 = new WEB3AdminMutualConditionsCompleteRequest();
        WEB3AdminMutualConditionsCompleteResponse l_expectResponse2 = 
            (WEB3AdminMutualConditionsCompleteResponse)l_request2.createResponse();

        try
        {
            
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.branchCode = "0";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualConditionsServiceImpl",
                "execute",
                new Class[] {WEB3GenRequest.class}, 
                l_expectResponse2);

            l_response2 = this.l_handler.completeProductConditionsRegist(l_request2);

            assertNotNull(l_response2);
            assertEquals(l_expectResponse2, l_response2);
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

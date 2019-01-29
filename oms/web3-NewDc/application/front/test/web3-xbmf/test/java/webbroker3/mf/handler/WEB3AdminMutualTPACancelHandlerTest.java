head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.49.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminMutualTPACancelHandlerTest.java;


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
import webbroker3.mf.message.WEB3AdminMutualTPACancelCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualTPACancelCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualTPACancelListRequest;
import webbroker3.mf.message.WEB3AdminMutualTPACancelListResponse;
import webbroker3.mf.service.delegate.WEB3AdminMutualTPACancelService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminMutualTPACancelHandlerTest extends TestBaseForMock
{

    private WEB3AdminMutualTPACancelService l_service = null;

    private WEB3AdminMutualTPACancelListRequest l_request = null;

    private WEB3AdminMutualTPACancelListResponse l_response = null;

    private WEB3AdminMutualTPACancelCompleteRequest l_request1 = null;

    private WEB3AdminMutualTPACancelCompleteResponse l_response1 = null;

    private WEB3AdminMutualTPACancelHandler l_handler;

    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualConditionsHandlerTest.class);

    public WEB3AdminMutualTPACancelHandlerTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        this.l_handler = new WEB3AdminMutualTPACancelHandler();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.l_handler = null;
    }

    public void testTpACancelList_C0001()
    {
        final String STR_METHOD_NAME = "testTpACancelList_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_service =
                (WEB3AdminMutualTPACancelService)Services.getService(
                        WEB3AdminMutualTPACancelService.class);

            Services.unregisterService(WEB3AdminMutualTPACancelService.class);

            l_request = new WEB3AdminMutualTPACancelListRequest();
            l_response = this.l_handler.tpACancelList(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminMutualTPACancelService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testTpACancelList_C0002()
    {
        final String STR_METHOD_NAME = "testTpACancelList_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualTPACancelServiceImpl",
                "execute",
                new Class[] {WEB3GenRequest.class}, 
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ""));

            l_request = new WEB3AdminMutualTPACancelListRequest();
            l_response =
                this.l_handler.tpACancelList(l_request);
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testTpACancelList_C0003()
    {
        final String STR_METHOD_NAME = "testTpACancelList_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        l_request = new WEB3AdminMutualTPACancelListRequest();
        WEB3AdminMutualTPACancelListResponse l_expectResponse = 
            (WEB3AdminMutualTPACancelListResponse)l_request.createResponse();

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualTPACancelServiceImpl",
                "execute",
                new Class[] {WEB3GenRequest.class}, 
                l_expectResponse);

            l_response = this.l_handler.tpACancelList(l_request);

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

    public void testTpACancelComplete_C0001()
    {
        final String STR_METHOD_NAME = "testProductConditionsRegistInputRequest_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            l_service =
                (WEB3AdminMutualTPACancelService)Services.getService(
                        WEB3AdminMutualTPACancelService.class);
            Services.unregisterService(WEB3AdminMutualTPACancelService.class);

            l_request1 = new WEB3AdminMutualTPACancelCompleteRequest();
            l_response1 = this.l_handler.tpACancelComplete(l_request1);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response1.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminMutualTPACancelService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testTpACancelComplete_C0002()
    {
        final String STR_METHOD_NAME = "testProductConditionsRegistInputRequest_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualTPACancelServiceImpl",
                "execute",
                new Class[] {WEB3GenRequest.class}, 
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ""));

            l_request1 = new WEB3AdminMutualTPACancelCompleteRequest();
            l_response1 =
                this.l_handler.tpACancelComplete(l_request1);
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testTpACancelComplete_C0003()
    {
        final String STR_METHOD_NAME = "testProductConditionsRegistInputRequest_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request1 = new WEB3AdminMutualTPACancelCompleteRequest();
        WEB3AdminMutualTPACancelCompleteResponse l_expectResponse1 = 
            (WEB3AdminMutualTPACancelCompleteResponse)l_request1.createResponse();

        try
        {
            
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.branchCode = "0";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualTPACancelServiceImpl",
                "execute",
                new Class[] {WEB3GenRequest.class}, 
                l_expectResponse1);

            l_response1 = this.l_handler.tpACancelComplete(l_request1);

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
}
@

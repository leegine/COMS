head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.14.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityAttentionInfoReferenceHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefInpRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefInpResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefRefRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefRefResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoReferenceService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityAttentionInfoReferenceHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAttentionInfoReferenceHandlerTest.class);
    
    WEB3AdminEquityAttentionInfoReferenceService l_service = null;

    public WEB3AdminEquityAttentionInfoReferenceHandlerTest(String arg0)
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

    /*
     * Test method for 'webbroker3.eqtypeadmin.handler.WEB3AdminEquityAttentionInfoReferenceHandler.getInputScreen(WEB3AdminEqAttentionInfoRefInpRequest)'
     */
    public void testGetInputScreenCase1()
    {
        final String STR_METHOD_NAME = "testGetInputScreenCase1";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_service =
                (WEB3AdminEquityAttentionInfoReferenceService)Services.getService(
                        WEB3AdminEquityAttentionInfoReferenceService.class);
            Services.unregisterService(WEB3AdminEquityAttentionInfoReferenceService.class);
            
            WEB3AdminEquityAttentionInfoReferenceHandler l_handler =
                new WEB3AdminEquityAttentionInfoReferenceHandler();
            
            WEB3AdminEqAttentionInfoRefInpRequest l_request =
                new WEB3AdminEqAttentionInfoRefInpRequest();
            
            WEB3AdminEqAttentionInfoRefInpResponse l_response =
                l_handler.getInputScreen(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminEquityAttentionInfoReferenceService.class, l_service);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetInputScreenCase2()
    {
        final String STR_METHOD_NAME = "testGetInputScreenCase2";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminEqAttentionInfoRefInpRequest l_request = new WEB3AdminEqAttentionInfoRefInpRequest();
        WEB3AdminEqAttentionInfoRefInpResponse l_response = null;
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3AdminEquityAttentionInfoReferenceHandler l_handler =
                new WEB3AdminEquityAttentionInfoReferenceHandler();
            l_service =
                (WEB3AdminEquityAttentionInfoReferenceService)Services.getService(
                        WEB3AdminEquityAttentionInfoReferenceService.class);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoReferenceServiceImpl", 
                    "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME));
            l_response = l_handler.getInputScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetInputScreenCase3()
    {
        final String STR_METHOD_NAME = "testGetInputScreenCase3()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminEqAttentionInfoRefInpRequest l_request = new WEB3AdminEqAttentionInfoRefInpRequest();
        WEB3AdminEqAttentionInfoRefInpResponse l_response = null;
        WEB3AdminEqAttentionInfoRefInpResponse l_expectResponse =
            new WEB3AdminEqAttentionInfoRefInpResponse(l_request);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3AdminEquityAttentionInfoReferenceHandler l_handler =
                new WEB3AdminEquityAttentionInfoReferenceHandler();
            l_response = new WEB3AdminEqAttentionInfoRefInpResponse();
            l_service =
                (WEB3AdminEquityAttentionInfoReferenceService)Services.getService(
                        WEB3AdminEquityAttentionInfoReferenceService.class);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoReferenceServiceImpl", 
                    "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_expectResponse);
            l_response = l_handler.getInputScreen(l_request);
            assertEquals(l_expectResponse, l_response);
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /*
     * Test method for 'webbroker3.eqtypeadmin.handler.WEB3AdminEquityAttentionInfoReferenceHandler.getReferenceScreen(WEB3AdminEqAttentionInfoRefRefRequest)'
     */
    public void testGetReferenceScreenCase1()
    {
        final String STR_METHOD_NAME = "testGetReferenceScreenCase1";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_service =
                (WEB3AdminEquityAttentionInfoReferenceService)Services.getService(
                        WEB3AdminEquityAttentionInfoReferenceService.class);
            Services.unregisterService(WEB3AdminEquityAttentionInfoReferenceService.class);
            
            WEB3AdminEquityAttentionInfoReferenceHandler l_handler =
                new WEB3AdminEquityAttentionInfoReferenceHandler();
            
            WEB3AdminEqAttentionInfoRefRefRequest l_request =
                new WEB3AdminEqAttentionInfoRefRefRequest();
            
            WEB3AdminEqAttentionInfoRefRefResponse l_response =
                l_handler.getReferenceScreen(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminEquityAttentionInfoReferenceService.class, l_service);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetReferenceScreenCase2()
    {
        final String STR_METHOD_NAME = "testGetReferenceScreenCase2";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        WEB3AdminEqAttentionInfoRefRefResponse l_response = null;
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3AdminEquityAttentionInfoReferenceHandler l_handler =
                new WEB3AdminEquityAttentionInfoReferenceHandler();
            l_service =
                (WEB3AdminEquityAttentionInfoReferenceService)Services.getService(
                        WEB3AdminEquityAttentionInfoReferenceService.class);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoReferenceServiceImpl", 
                    "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME));
            l_response = l_handler.getReferenceScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetReferenceScreenCase3()
    {
        final String STR_METHOD_NAME = "testGetReferenceScreenCase3()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        WEB3AdminEqAttentionInfoRefRefResponse l_response = null;
        WEB3AdminEqAttentionInfoRefRefResponse l_expectResponse =
            new WEB3AdminEqAttentionInfoRefRefResponse(l_request);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3AdminEquityAttentionInfoReferenceHandler l_handler =
                new WEB3AdminEquityAttentionInfoReferenceHandler();
            l_response = new WEB3AdminEqAttentionInfoRefRefResponse();
            l_service =
                (WEB3AdminEquityAttentionInfoReferenceService)Services.getService(
                        WEB3AdminEquityAttentionInfoReferenceService.class);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoReferenceServiceImpl", 
                    "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_expectResponse);
            l_response = l_handler.getReferenceScreen(l_request);
            assertEquals(l_expectResponse, l_response);
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

}
@

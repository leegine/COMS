head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.39.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminToIfoOrderReferenceHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.admintriggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefInpRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefInpResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefRefRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefRefResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToIfoOrderReferenceService;
import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToIfoOrderReferenceServiceImpl;
import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToIfoOrderReferenceServiceImplForMock;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminToIfoOrderReferenceHandlerTest extends TestBaseForMock
{

    WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminToIfoOrderReferenceHandlerTest.class);

    WEB3AdminToIfoOrderReferenceHandler l_handler = null;
    
    public WEB3AdminToIfoOrderReferenceHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_handler = new WEB3AdminToIfoOrderReferenceHandler();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    /*
     * Test method for 'webbroker3.admintriggerorder.handler.WEB3AdminToIfoOrderReferenceHandler.getInputScreen(WEB3AdminToIfoOrderRefInpRequest)'
     */
    public void testGetInputScreen_C0001_1()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_C0001_1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToIfoOrderReferenceServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ""));
            
            WEB3AdminToIfoOrderRefInpRequest l_request = new WEB3AdminToIfoOrderRefInpRequest();
            WEB3AdminToIfoOrderRefInpResponse l_response = new WEB3AdminToIfoOrderRefInpResponse();
            l_response = l_handler.getInputScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetInputScreen_C0001_2()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_C0001_2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToIfoOrderReferenceServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ""));
            
            WEB3AdminToIfoOrderRefInpRequest l_request = new WEB3AdminToIfoOrderRefInpRequest();
            WEB3AdminToIfoOrderRefInpResponse l_response = new WEB3AdminToIfoOrderRefInpResponse();
            l_response = l_handler.getInputScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }    

    /*
     * Test method for 'webbroker3.admintriggerorder.handler.WEB3AdminToIfoOrderReferenceHandler.getReferenceScreen(WEB3AdminToIfoOrderRefRefRequest)'
     */
    public void testGetReferenceScreen_C0002_1()
    {
        final String STR_METHOD_NAME = "testGetReferenceScreen_C0002_1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToIfoOrderReferenceServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ""));
            
            WEB3AdminToIfoOrderRefRefRequest l_request = new WEB3AdminToIfoOrderRefRefRequest();
            WEB3AdminToIfoOrderRefRefResponse l_response = new WEB3AdminToIfoOrderRefRefResponse();
            l_response = l_handler.getReferenceScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetReferenceScreen_C0002_2()
    {
        final String STR_METHOD_NAME = "testGetReferenceScreen_C0002_2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToIfoOrderReferenceServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ""));
            
            WEB3AdminToIfoOrderRefRefRequest l_request = new WEB3AdminToIfoOrderRefRefRequest();
            WEB3AdminToIfoOrderRefRefResponse l_response = new WEB3AdminToIfoOrderRefRefResponse();
            l_response = l_handler.getReferenceScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetReferenceScreen_C0002_3()
    {
        final String STR_METHOD_NAME = "testGetReferenceScreen_C0002_3()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Services.unregisterService(WEB3AdminToIfoOrderReferenceService.class);
            
            WEB3AdminToIfoOrderRefRefRequest l_request = new WEB3AdminToIfoOrderRefRefRequest();
            WEB3AdminToIfoOrderRefRefResponse l_response = new WEB3AdminToIfoOrderRefRefResponse();
            l_response = l_handler.getReferenceScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminToIfoOrderReferenceService.class,
                    new              WEB3AdminToIfoOrderReferenceServiceImplForMock());
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetInputScreen_C0001_3()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_C0001_3()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Services.unregisterService(WEB3AdminToIfoOrderReferenceService.class);
            
            WEB3AdminToIfoOrderRefInpRequest l_request = new WEB3AdminToIfoOrderRefInpRequest();
            WEB3AdminToIfoOrderRefInpResponse l_response = new WEB3AdminToIfoOrderRefInpResponse();
            l_response = l_handler.getInputScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminToIfoOrderReferenceService.class,
                    new              WEB3AdminToIfoOrderReferenceServiceImplForMock());
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
        
    }

}
@

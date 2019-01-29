head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.16.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3InformPTSAccOpenApplyHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCmpRequest;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCmpResponse;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCnfRequest;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCnfResponse;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyInpRequest;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyInpResponse;
import webbroker3.inform.service.delegate.WEB3InformPTSAccOpenApplyService;
import webbroker3.inform.service.delegate.stdimpls.WEB3InformPTSAccOpenApplyServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3InformPTSAccOpenApplyHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformPTSAccOpenApplyHandlerTest.class);
    WEB3InformPTSAccOpenApplyHandler l_handler =
        new WEB3InformPTSAccOpenApplyHandler();
    
    static boolean isException = false;
    
    public WEB3InformPTSAccOpenApplyHandlerTest(String arg0)
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

    public void testDisplayInputScreen_T01()
    {
        final String STR_METHOD_NAME = "testDisplayInputScreen_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            isException = false;
            Services.unregisterService(WEB3InformPTSAccOpenApplyService.class);
            WEB3InformPTSAccOpenApplyInpRequest l_request =
                new WEB3InformPTSAccOpenApplyInpRequest();
            WEB3InformPTSAccOpenApplyInpResponse l_response =
                l_handler.displayInputScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo); 
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3InformPTSAccOpenApplyService.class,
                new WEB3InformPTSAccOpenApplyServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testDisplayInputScreen_T02()
    {
        final String STR_METHOD_NAME = "testDisplayInputScreen_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            isException = true;
            Services.unregisterService(WEB3InformPTSAccOpenApplyService.class);
            Services.registerService(WEB3InformPTSAccOpenApplyService.class,
                new WEB3InformPTSAccOpenApplyServiceForMock());
            WEB3InformPTSAccOpenApplyInpRequest l_request =
                new WEB3InformPTSAccOpenApplyInpRequest();
            WEB3InformPTSAccOpenApplyInpResponse l_response =
                l_handler.displayInputScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo); 

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3InformPTSAccOpenApplyService.class);
            Services.registerService(WEB3InformPTSAccOpenApplyService.class,
                new WEB3InformPTSAccOpenApplyServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testDisplayInputScreen_T03()
    {
        final String STR_METHOD_NAME = "testDisplayInputScreen_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            isException = false;
            Services.unregisterService(WEB3InformPTSAccOpenApplyService.class);
            Services.registerService(WEB3InformPTSAccOpenApplyService.class,
                new WEB3InformPTSAccOpenApplyServiceForMock());
            WEB3InformPTSAccOpenApplyInpRequest l_request =
                new WEB3InformPTSAccOpenApplyInpRequest();
            WEB3InformPTSAccOpenApplyInpResponse l_response =
                l_handler.displayInputScreen(l_request);
            assertNull(l_response); 
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3InformPTSAccOpenApplyService.class);
            Services.registerService(WEB3InformPTSAccOpenApplyService.class,
                new WEB3InformPTSAccOpenApplyServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testApplyConfirm_T01()
    {
        final String STR_METHOD_NAME = "testApplyConfirm_T01()";
        try
        {
            isException = false;
            Services.unregisterService(WEB3InformPTSAccOpenApplyService.class);
            WEB3InformPTSAccOpenApplyCnfRequest l_request =
                new WEB3InformPTSAccOpenApplyCnfRequest();
            WEB3InformPTSAccOpenApplyCnfResponse l_response =
                l_handler.applyConfirm(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo); 
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3InformPTSAccOpenApplyService.class,
                new WEB3InformPTSAccOpenApplyServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testApplyConfirm_T02()
    {
        final String STR_METHOD_NAME = "testApplyConfirm_T02()";
        try
        {
            isException = true;
            Services.unregisterService(WEB3InformPTSAccOpenApplyService.class);
            Services.registerService(WEB3InformPTSAccOpenApplyService.class,
                new WEB3InformPTSAccOpenApplyServiceForMock());
            WEB3InformPTSAccOpenApplyCnfRequest l_request =
                new WEB3InformPTSAccOpenApplyCnfRequest();
            WEB3InformPTSAccOpenApplyCnfResponse l_response =
                l_handler.applyConfirm(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo); 
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3InformPTSAccOpenApplyService.class);
            Services.registerService(WEB3InformPTSAccOpenApplyService.class,
                new WEB3InformPTSAccOpenApplyServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testApplyConfirm_T03()
    {
        final String STR_METHOD_NAME = "testApplyConfirm_T03()";
        try
        {
            isException = false;
            Services.unregisterService(WEB3InformPTSAccOpenApplyService.class);
            Services.registerService(WEB3InformPTSAccOpenApplyService.class,
                new WEB3InformPTSAccOpenApplyServiceForMock());
            WEB3InformPTSAccOpenApplyCnfRequest l_request =
                new WEB3InformPTSAccOpenApplyCnfRequest();
            WEB3InformPTSAccOpenApplyCnfResponse l_response =
                l_handler.applyConfirm(l_request);
            assertNull(l_response);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3InformPTSAccOpenApplyService.class);
            Services.registerService(WEB3InformPTSAccOpenApplyService.class,
                new WEB3InformPTSAccOpenApplyServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testApplyComplete_T01()
    {
        final String STR_METHOD_NAME = "testApplyComplete_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            isException = false;
            Services.unregisterService(WEB3InformPTSAccOpenApplyService.class);
            WEB3InformPTSAccOpenApplyCmpRequest l_request =
                new WEB3InformPTSAccOpenApplyCmpRequest();
            WEB3InformPTSAccOpenApplyCmpResponse l_response =
                l_handler.applyComplete(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo); 
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3InformPTSAccOpenApplyService.class,
                new WEB3InformPTSAccOpenApplyServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testApplyComplete_T02()
    {
        final String STR_METHOD_NAME = "testApplyComplete_T02()";
        try
        {
            isException = true;
            Services.unregisterService(WEB3InformPTSAccOpenApplyService.class);
            Services.registerService(WEB3InformPTSAccOpenApplyService.class,
                new WEB3InformPTSAccOpenApplyServiceForMock());
            WEB3InformPTSAccOpenApplyCmpRequest l_request =
                new WEB3InformPTSAccOpenApplyCmpRequest();
            WEB3InformPTSAccOpenApplyCmpResponse l_response =
                l_handler.applyComplete(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo); 
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3InformPTSAccOpenApplyService.class);
            Services.registerService(WEB3InformPTSAccOpenApplyService.class,
                new WEB3InformPTSAccOpenApplyServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testApplyComplete_T03()
    {
        final String STR_METHOD_NAME = "testApplyComplete_T03()";
        try
        {
            isException = false;
            Services.unregisterService(WEB3InformPTSAccOpenApplyService.class);
            Services.registerService(WEB3InformPTSAccOpenApplyService.class,
                new WEB3InformPTSAccOpenApplyServiceForMock());
            WEB3InformPTSAccOpenApplyCmpRequest l_request =
                new WEB3InformPTSAccOpenApplyCmpRequest();
            WEB3InformPTSAccOpenApplyCmpResponse l_response =
                l_handler.applyComplete(l_request);
            assertNull(l_response);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3InformPTSAccOpenApplyService.class);
            Services.registerService(WEB3InformPTSAccOpenApplyService.class,
                new WEB3InformPTSAccOpenApplyServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    class WEB3InformPTSAccOpenApplyServiceForMock extends WEB3InformPTSAccOpenApplyServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            if (isException)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    "fffffffffffff",
                    "dddddddddd");
            }
            else
            {
                return null;
            }
        }
    }
}
@

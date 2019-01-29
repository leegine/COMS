head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.33.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SrvRegiStreamHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.message.WEB3SrvRegiStreamRequest;
import webbroker3.srvregi.message.WEB3SrvRegiStreamResponse;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiStreamService;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiStreamServiceImpl;
import webbroker3.util.WEB3LogUtility;

public class WEB3SrvRegiStreamHandlerTest extends TestBaseForMock {

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiStreamHandlerTest.class);

    public WEB3SrvRegiStreamHandlerTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.srvregi.handler.WEB3SrvRegiStreamHandler.srvRegiStreamRequest(WEB3SrvRegiStreamRequest)'
     */
    public void testSrvRegiStreamRequest_T01() 
    {
        final String STR_METHOD_NAME = "testSrvRegiStreamRequest_T01";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiStreamHandler l_handler = new WEB3SrvRegiStreamHandler();
        WEB3SrvRegiStreamRequest l_request = new WEB3SrvRegiStreamRequest();
        try
        {
            
            Services.unregisterService(WEB3SrvRegiStreamService.class);
            WEB3SrvRegiStreamResponse l_response =
                l_handler.srvRegiStreamRequest(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002 , l_response.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            try
            {
                Services.registerService(
                        WEB3SrvRegiStreamService.class,
                    new WEB3SrvRegiStreamServiceImpl());
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }

    public void testSrvRegiStreamRequest_T02() 
    {
        final String STR_METHOD_NAME = "testSrvRegiStreamRequest_T02";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiStreamHandler l_handler = new WEB3SrvRegiStreamHandler();
        WEB3SrvRegiStreamRequest l_request = new WEB3SrvRegiStreamRequest();
        try
        {
            Services.overrideService(
                WEB3SrvRegiStreamService.class,
                new WEB3SrvRegiStreamServiceImplForTestA());

            WEB3SrvRegiStreamResponse l_response =
                l_handler.srvRegiStreamRequest(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002 , l_response.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
       
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    
    public void testSrvRegiStreamRequest_T03() 
    {
        final String STR_METHOD_NAME = "testSrvRegiStreamRequest_T03";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiStreamHandler l_handler = new WEB3SrvRegiStreamHandler();
        WEB3SrvRegiStreamRequest l_request = new WEB3SrvRegiStreamRequest();
        try
        {
            Services.overrideService(
                WEB3SrvRegiStreamService.class,
                new WEB3SrvRegiStreamServiceImplForTestB());

            WEB3SrvRegiStreamResponse l_response =
                l_handler.srvRegiStreamRequest(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002 , l_response.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
       
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testSrvRegiStreamRequest_T04() 
    {
        final String STR_METHOD_NAME = "testSrvRegiStreamRequest_T04";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiStreamHandler l_handler = new WEB3SrvRegiStreamHandler();
        WEB3SrvRegiStreamRequest l_request = new WEB3SrvRegiStreamRequest();
        try
        {
            Services.overrideService(
                WEB3SrvRegiStreamService.class,
                new WEB3SrvRegiStreamServiceImplForTestC());

            WEB3SrvRegiStreamResponse l_response =
                l_handler.srvRegiStreamRequest(l_request);

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
       
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    
    //=================================================================================
    public class WEB3SrvRegiStreamServiceImplForTestA extends WEB3SrvRegiStreamServiceImpl 
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            log.entering(STR_METHOD_NAME);

            WEB3SrvRegiStreamResponse l_srvRegiStreamResponse = null;
            if (l_request instanceof WEB3SrvRegiStreamRequest)
            {
                if (true)
                {
                    log.debug("パラメータタイプ不正。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "パラメータタイプ不正。");
                }
            }
            
            
            return l_srvRegiStreamResponse;
        }
        
    }
    
    public class WEB3SrvRegiStreamServiceImplForTestB extends WEB3SrvRegiStreamServiceImpl 
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            log.entering(STR_METHOD_NAME);

            WEB3SrvRegiStreamResponse l_srvRegiStreamResponse = null;
            if (l_request instanceof WEB3SrvRegiStreamRequest)
            {
                if (true)
                {
                    log.debug("予期しないシステムエラーが発生しました。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "予期しないシステムエラーが発生しました。");
                }
            }
            
            
            return l_srvRegiStreamResponse;
        }
        
    }
    
    public class WEB3SrvRegiStreamServiceImplForTestC extends WEB3SrvRegiStreamServiceImpl 
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            log.entering(STR_METHOD_NAME);

            WEB3SrvRegiStreamResponse l_srvRegiStreamResponse = null;
            if (l_request instanceof WEB3SrvRegiStreamRequest)
            {
                l_srvRegiStreamResponse =
                    (WEB3SrvRegiStreamResponse)l_request.createResponse();
            }

            return l_srvRegiStreamResponse;
        }
        
    }
}
@

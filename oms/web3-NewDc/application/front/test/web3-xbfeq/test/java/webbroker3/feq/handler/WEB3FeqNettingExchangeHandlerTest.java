head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.38.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqNettingExchangeHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.feq.message.WEB3FeqNettingExchangeRequest;
import webbroker3.feq.message.WEB3FeqNettingExchangeResponse;
import webbroker3.feq.service.delegate.WEB3FeqNettingExchangeService;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqNettingExchangeServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqNettingExchangeHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */    
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
                 WEB3FeqNettingExchangeHandlerTest.class);

     WEB3FeqNettingExchangeHandler l_handler =
         new WEB3FeqNettingExchangeHandler();
     
    public WEB3FeqNettingExchangeHandlerTest(String arg0)
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
     * Test method for 'webbroker3.feq.handler.WEB3FeqNettingExchangeHandler.nettingExchange(WEB3FeqNettingExchangeRequest)'
     */
    public void testNettingExchangeCase1()
    {
        final String STR_METHOD_NAME = "testNettingExchangeCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3FeqNettingExchangeService.class);
            WEB3FeqNettingExchangeRequest l_request = new WEB3FeqNettingExchangeRequest();
            l_request.institutionCode = "0D";
            WEB3FeqNettingExchangeResponse l_response =
                l_handler.nettingExchange(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(
                    WEB3FeqNettingExchangeService.class,
                new WEB3FeqNettingExchangeServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testNettingExchangeCase2()
    {
        final String STR_METHOD_NAME = "testNettingExchangeCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FeqNettingExchangeRequest l_request = new WEB3FeqNettingExchangeRequest();
            l_request.institutionCode = "0D";
            WEB3FeqNettingExchangeResponse l_response =
                l_handler.nettingExchange(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testNettingExchangeCase3()
    {
        final String STR_METHOD_NAME = "testNettingExchangeCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            forMock();
            WEB3FeqNettingExchangeRequest l_request = new WEB3FeqNettingExchangeRequest();
            l_request.institutionCode = "0D";
            WEB3FeqNettingExchangeResponse l_response =
                l_handler.nettingExchange(l_request);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3FeqNettingExchangeService.class);
            Services.registerService(
                    WEB3FeqNettingExchangeService.class,
                new WEB3FeqNettingExchangeServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void forMock()
    {
        
        try
        {
            Services.unregisterService(WEB3FeqNettingExchangeService.class);
            
            Services.registerService(
                    WEB3FeqNettingExchangeService.class,
                new WEB3FeqNettingExchangeServiceImplForTest());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

    }
    
    class WEB3FeqNettingExchangeServiceImplForTest extends WEB3FeqNettingExchangeServiceImpl
    {
        public WEB3BackResponse  execute(WEB3BackRequest  l_web3BackRequest)
        {
            return null;
                
        }
    }
}
@

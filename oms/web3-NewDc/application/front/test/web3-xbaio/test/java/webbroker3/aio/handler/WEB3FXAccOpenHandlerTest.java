head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.25.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXAccOpenHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.aio.message.WEB3FXAccOpenCompleteSoapRequest;
import webbroker3.aio.message.WEB3FXAccOpenCompleteSoapResponse;
import webbroker3.aio.service.delegate.WEB3FXAccOpenService;
import webbroker3.aio.service.delegate.stdimpls.WEB3FXAccOpenServiceImpl;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FXAccOpenHandlerTest extends TestBaseForMock {

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAccOpenHandlerTest.class);
    
    public WEB3FXAccOpenHandlerTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    WEB3FXAccOpenHandler l_handler = new WEB3FXAccOpenHandler();
    
    /*
     * Test method for 'webbroker3.aio.handler.WEB3FXAccOpenHandler.accountOpenCompleteSoap(WEB3FXAccOpenCompleteSoapRequest)'
     */
    public void testAccountOpenCompleteSoap_T001() 
    {

        final String STR_METHOD_NAME ="testAccountOpenCompleteSoap_T001( )";
        log.entering(STR_METHOD_NAME);

        try
        {
            
            Services.unregisterService(WEB3FXAccOpenService.class);
            WEB3FXAccOpenCompleteSoapRequest l_request = new WEB3FXAccOpenCompleteSoapRequest();
 
            WEB3FXAccOpenCompleteSoapResponse l_response = 
                l_handler.accountOpenCompleteSoap(l_request);
            
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80002);

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
                        WEB3FXAccOpenService.class,
                    new WEB3FXAccOpenServiceImpl());
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testAccountOpenCompleteSoap_T002() 
    {
        final String STR_METHOD_NAME ="testAccountOpenCompleteSoap_T002( )";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXAccOpenCompleteSoapRequest l_request = new WEB3FXAccOpenCompleteSoapRequest();
        
        try
        {

            Services.overrideService(
                    WEB3FXAccOpenService.class,
                    new WEB3FXAccOpenServiceImplForTestA());

            WEB3FXAccOpenCompleteSoapResponse l_response = 
                l_handler.accountOpenCompleteSoap(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testAccountOpenCompleteSoap_T003() 
    {
        final String STR_METHOD_NAME ="testAccountOpenCompleteSoap_T003( )";
        log.entering(STR_METHOD_NAME);

        WEB3FXAccOpenCompleteSoapRequest l_request = new WEB3FXAccOpenCompleteSoapRequest();
        
        try
        {

            Services.overrideService(
                    WEB3FXAccOpenService.class,
                    new WEB3FXAccOpenServiceImplForTestB());

            WEB3FXAccOpenCompleteSoapResponse l_response = 
                l_handler.accountOpenCompleteSoap(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public class WEB3FXAccOpenServiceImplForTestA extends WEB3FXAccOpenServiceImpl
    {
        
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
            log.entering(STR_METHOD_NAME);
        
            
            if (l_request instanceof WEB3FXAccOpenCompleteSoapRequest)
            {
                                
                if (true)
                {
                    log.debug("パラメータタイプ不正。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "パラメータタイプ不正。");
                }
            }

            return null;
            
        
        }
    }

    public class WEB3FXAccOpenServiceImplForTestB extends WEB3FXAccOpenServiceImpl
    {
        
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
            log.entering(STR_METHOD_NAME);
        
            WEB3GenResponse l_response = null;
            if (l_request instanceof WEB3FXAccOpenCompleteSoapRequest)
            {
                                
                    l_response = (WEB3FXAccOpenCompleteSoapResponse)l_request.createResponse();
            }

            return l_response;

        }
    }
    
}
@

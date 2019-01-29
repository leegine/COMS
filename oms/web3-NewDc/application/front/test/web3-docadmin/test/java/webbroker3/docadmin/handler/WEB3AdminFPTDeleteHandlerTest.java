head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.56.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTDeleteHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin.handler;


import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteConfirmResponse;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTDeleteService;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTDeleteServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

public class WEB3AdminFPTDeleteHandlerTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDeleteHandlerTest.class);
    
    public WEB3AdminFPTDeleteHandlerTest(String arg0)
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
    
    public void testGetDeleteConfirm_0001()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Services.unregisterService(WEB3AdminFPTDeleteService.class);
        
        WEB3AdminFPTDeleteHandler l_handler = new WEB3AdminFPTDeleteHandler();
        WEB3AdminFPTDeleteConfirmRequest l_request = new WEB3AdminFPTDeleteConfirmRequest();
        WEB3AdminFPTDeleteConfirmResponse l_response = new WEB3AdminFPTDeleteConfirmResponse();
        l_response = l_handler.getDeleteConfirm(l_request);
        
        assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        
        Services.registerService(WEB3AdminFPTDeleteService.class,
            new WEB3AdminFPTDeleteServiceImpl());
        Services.addInterceptor(WEB3AdminFPTDeleteService.class,
            new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminFPTDeleteService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetDeleteConfirm_0002()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Services.overrideService(WEB3AdminFPTDeleteService.class, new WEB3AdminFPTDeleteServiceImplForTest());
        
        WEB3AdminFPTDeleteHandler l_handler = new WEB3AdminFPTDeleteHandler();
        WEB3AdminFPTDeleteConfirmRequest l_request = new WEB3AdminFPTDeleteConfirmRequest();
        WEB3AdminFPTDeleteConfirmResponse l_response = new WEB3AdminFPTDeleteConfirmResponse();
        l_response = l_handler.getDeleteConfirm(l_request);
        
        assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        
        Services.overrideService(WEB3AdminFPTDeleteService.class, new WEB3AdminFPTDeleteServiceImpl());        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetDeleteConfirm_0003()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Services.overrideService(WEB3AdminFPTDeleteService.class, new WEB3AdminFPTDeleteServiceImplForTest1());
        
        WEB3AdminFPTDeleteHandler l_handler = new WEB3AdminFPTDeleteHandler();
        WEB3AdminFPTDeleteConfirmRequest l_request = new WEB3AdminFPTDeleteConfirmRequest();
        WEB3AdminFPTDeleteConfirmResponse l_response = new WEB3AdminFPTDeleteConfirmResponse();
        l_response = l_handler.getDeleteConfirm(l_request);
        
        assertEquals(l_response.getClass(), WEB3AdminFPTDeleteConfirmResponse.class);
        
        Services.overrideService(WEB3AdminFPTDeleteService.class, new WEB3AdminFPTDeleteServiceImpl());        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetDeleteComplete_0001()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Services.unregisterService(WEB3AdminFPTDeleteService.class);
        
        WEB3AdminFPTDeleteHandler l_handler = new WEB3AdminFPTDeleteHandler();
        WEB3AdminFPTDeleteCompleteRequest l_request = new WEB3AdminFPTDeleteCompleteRequest();
        WEB3AdminFPTDeleteCompleteResponse l_response = new WEB3AdminFPTDeleteCompleteResponse();
        l_response = l_handler.getDeleteComplete(l_request);
        
        assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        
        Services.registerService(WEB3AdminFPTDeleteService.class,
            new WEB3AdminFPTDeleteServiceImpl());
        Services.addInterceptor(WEB3AdminFPTDeleteService.class,
            new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminFPTDeleteService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetDeleteComplete_0002()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Services.overrideService(WEB3AdminFPTDeleteService.class, new WEB3AdminFPTDeleteServiceImplForTest());
        
        WEB3AdminFPTDeleteHandler l_handler = new WEB3AdminFPTDeleteHandler();
        WEB3AdminFPTDeleteCompleteRequest l_request = new WEB3AdminFPTDeleteCompleteRequest();
        WEB3AdminFPTDeleteCompleteResponse l_response = new WEB3AdminFPTDeleteCompleteResponse();
        l_response = l_handler.getDeleteComplete(l_request);
        
        assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        
        Services.overrideService(WEB3AdminFPTDeleteService.class, new WEB3AdminFPTDeleteServiceImpl());        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetDeleteComplete_0003()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Services.overrideService(WEB3AdminFPTDeleteService.class, new WEB3AdminFPTDeleteServiceImplForTest2());
        
        WEB3AdminFPTDeleteHandler l_handler = new WEB3AdminFPTDeleteHandler();
        WEB3AdminFPTDeleteCompleteRequest l_request = new WEB3AdminFPTDeleteCompleteRequest();
        WEB3AdminFPTDeleteCompleteResponse l_response = new WEB3AdminFPTDeleteCompleteResponse();
        l_response = l_handler.getDeleteComplete(l_request);
        
        assertEquals(l_response.getClass(), WEB3AdminFPTDeleteCompleteResponse.class);
        
        Services.overrideService(WEB3AdminFPTDeleteService.class, new WEB3AdminFPTDeleteServiceImpl());        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public class WEB3AdminFPTDeleteServiceImplForTest extends WEB3AdminFPTDeleteServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
    }
    
    public class WEB3AdminFPTDeleteServiceImplForTest1 extends WEB3AdminFPTDeleteServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
        {
                return new WEB3AdminFPTDeleteConfirmResponse();
        }
    }
    
    public class WEB3AdminFPTDeleteServiceImplForTest2 extends WEB3AdminFPTDeleteServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
        {
                return new WEB3AdminFPTDeleteCompleteResponse();
        }
    }
}
@

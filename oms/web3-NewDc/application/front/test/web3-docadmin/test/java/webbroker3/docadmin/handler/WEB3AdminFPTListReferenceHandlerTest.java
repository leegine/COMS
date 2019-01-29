head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.56.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTListReferenceHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin.handler;

import webbroker3.aio.WEB3AioSLAccountOpenServiceInterceptor;
import webbroker3.aio.service.delegate.WEB3AioSLRepayApplyInputService;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSLRepayApplyInputServiceImpl;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.message.WEB3AdminFPTListReferenceRequest;
import webbroker3.docadmin.message.WEB3AdminFPTListReferenceResponse;
import webbroker3.docadmin.message.WEB3AdminFPTSearchInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTSearchInputResponse;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTListReferenceService;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTListReferenceServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

public class WEB3AdminFPTListReferenceHandlerTest extends
        TestBaseForMock {

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTListReferenceHandlerTest.class);

    public WEB3AdminFPTListReferenceHandlerTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }


    public void testGetListSearchScreen_0001()
    {
        final String STR_METHOD_NAME = " testGetListSearchScreen_0001()";
        log.entering(STR_METHOD_NAME);

        Services.unregisterService(WEB3AdminFPTListReferenceService.class);
        
        WEB3AdminFPTSearchInputRequest l_request = new WEB3AdminFPTSearchInputRequest();
        WEB3AdminFPTSearchInputResponse l_response = new WEB3AdminFPTSearchInputResponse(l_request);

        WEB3AdminFPTListReferenceHandler l_handler = new WEB3AdminFPTListReferenceHandler();
        l_response = l_handler.getListSearchScreen(l_request);
        
        log.exiting(STR_METHOD_NAME);
        assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);

        Services.registerService(WEB3AdminFPTListReferenceService.class, new WEB3AdminFPTListReferenceServiceImpl());
        Services.addInterceptor(
                WEB3AdminFPTListReferenceService.class,
            new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(
                WEB3AdminFPTListReferenceService.class,
            new WEB3AioSLAccountOpenServiceInterceptor());
        Services.addInterceptor(
                WEB3AdminFPTListReferenceService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));
    }

    public void testGetListSearchScreen_0002()
    {
        final String STR_METHOD_NAME = " testGetListSearchScreen_0002()";
        log.entering(STR_METHOD_NAME);

        Services.overrideService(WEB3AdminFPTListReferenceService.class, new WEB3AdminDocAdminFPTListReferenceServiceImplForTest1());

        WEB3AdminFPTSearchInputRequest l_request = new WEB3AdminFPTSearchInputRequest();
        WEB3AdminFPTSearchInputResponse l_response = new WEB3AdminFPTSearchInputResponse(l_request);

        WEB3AdminFPTListReferenceHandler l_handler = new WEB3AdminFPTListReferenceHandler();
        l_response = l_handler.getListSearchScreen(l_request);

        log.exiting(STR_METHOD_NAME);
        assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);

        Services.overrideService(WEB3AdminFPTListReferenceService.class, new WEB3AdminFPTListReferenceServiceImpl());
    }
    
    public void testGetListSearchScreen_0003()
    {
        final String STR_METHOD_NAME = " testGetListSearchScreen_0003()";
        log.entering(STR_METHOD_NAME);

        Services.overrideService(WEB3AdminFPTListReferenceService.class, new WEB3AdminDocAdminFPTListReferenceServiceImplForTest());
        WEB3AdminFPTSearchInputRequest l_request = new WEB3AdminFPTSearchInputRequest();
        WEB3AdminFPTSearchInputResponse l_response = new WEB3AdminFPTSearchInputResponse(l_request);

        WEB3AdminFPTListReferenceHandler l_handler = new WEB3AdminFPTListReferenceHandler();
        l_response = l_handler.getListSearchScreen(l_request);

        log.exiting(STR_METHOD_NAME);
        assertEquals(null, l_response.errorInfo);

        Services.overrideService(WEB3AdminFPTListReferenceService.class, new WEB3AdminFPTListReferenceServiceImpl());
    }

    public void testGetListReferenceScreen_0001()
    {
        final String STR_METHOD_NAME = " testGetListReferenceScreen_0001()";
        log.entering(STR_METHOD_NAME);

        Services.unregisterService(WEB3AdminFPTListReferenceService.class);
        
        WEB3AdminFPTListReferenceRequest l_request = new WEB3AdminFPTListReferenceRequest();
        WEB3AdminFPTListReferenceResponse l_response = new WEB3AdminFPTListReferenceResponse(l_request);

        WEB3AdminFPTListReferenceHandler l_handler = new WEB3AdminFPTListReferenceHandler();
        l_response = l_handler.getListReferenceScreen(l_request);
        
        log.exiting(STR_METHOD_NAME);
        assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);

        Services.registerService(WEB3AdminFPTListReferenceService.class, new WEB3AdminFPTListReferenceServiceImpl());
        Services.addInterceptor(
                WEB3AdminFPTListReferenceService.class,
            new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(
                WEB3AdminFPTListReferenceService.class,
            new WEB3AioSLAccountOpenServiceInterceptor());
        Services.addInterceptor(
                WEB3AdminFPTListReferenceService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));
    }

    public void testGetListReferenceScreen_0002()
    {
        final String STR_METHOD_NAME = " testGetListReferenceScreen_0002()";
        log.entering(STR_METHOD_NAME);

        Services.overrideService(WEB3AdminFPTListReferenceService.class, new WEB3AdminDocAdminFPTListReferenceServiceImplForTest1());

        WEB3AdminFPTListReferenceRequest l_request = new WEB3AdminFPTListReferenceRequest();
        WEB3AdminFPTListReferenceResponse l_response = new WEB3AdminFPTListReferenceResponse(l_request);

        WEB3AdminFPTListReferenceHandler l_handler = new WEB3AdminFPTListReferenceHandler();
        l_response = l_handler.getListReferenceScreen(l_request);

        log.exiting(STR_METHOD_NAME);
        assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);

        Services.overrideService(WEB3AdminFPTListReferenceService.class, new WEB3AdminFPTListReferenceServiceImpl());
    }
    
    public void testGetListReferenceScreen_0003()
    {
        final String STR_METHOD_NAME = " testGetListReferenceScreen_0003()";
        log.entering(STR_METHOD_NAME);

        Services.overrideService(WEB3AdminFPTListReferenceService.class, new WEB3AdminDocAdminFPTListReferenceServiceImplForTest2());
        WEB3AdminFPTListReferenceRequest l_request = new WEB3AdminFPTListReferenceRequest();
        WEB3AdminFPTListReferenceResponse l_response = new WEB3AdminFPTListReferenceResponse(l_request);

        WEB3AdminFPTListReferenceHandler l_handler = new WEB3AdminFPTListReferenceHandler();
        l_response = l_handler.getListReferenceScreen(l_request);

        log.exiting(STR_METHOD_NAME);
        assertEquals(null, l_response.errorInfo);

        Services.overrideService(WEB3AdminFPTListReferenceService.class, new WEB3AdminFPTListReferenceServiceImpl());
    }
    
    private class WEB3AdminDocAdminFPTListReferenceServiceImplForTest extends WEB3AdminFPTListReferenceServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            WEB3AdminFPTSearchInputRequest l_request1 = new WEB3AdminFPTSearchInputRequest();
            WEB3AdminFPTSearchInputResponse l_response1 = new WEB3AdminFPTSearchInputResponse(l_request1);
            return l_response1;
        }
    }

    private class WEB3AdminDocAdminFPTListReferenceServiceImplForTest2 extends WEB3AdminFPTListReferenceServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            WEB3AdminFPTListReferenceRequest l_request1 = new WEB3AdminFPTListReferenceRequest();
            WEB3AdminFPTListReferenceResponse l_response1 = new WEB3AdminFPTListReferenceResponse(l_request1);
            return l_response1;
        }
    }

    private class WEB3AdminDocAdminFPTListReferenceServiceImplForTest1 extends WEB3AdminFPTListReferenceServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName(),
                "パラメータ値不正。");
        }
    }
}
@

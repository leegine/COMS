head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.24.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioSLRepayApplyHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.aio.WEB3AioSLAccountOpenServiceInterceptor;
import webbroker3.aio.message.WEB3SLRepayApplyCompleteRequest;
import webbroker3.aio.message.WEB3SLRepayApplyCompleteResponse;
import webbroker3.aio.message.WEB3SLRepayApplyConfirmRequest;
import webbroker3.aio.message.WEB3SLRepayApplyConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioSLRepayApplyInputService;
import webbroker3.aio.service.delegate.WEB3AioSLRepayApplyService;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSLRepayApplyServiceImpl;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AioSLRepayApplyHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLRepayApplyHandlerTest.class);

    public WEB3AioSLRepayApplyHandlerTest(String arg0)
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

    public void testConfirmOrder_001()
    {
        final String STR_METHOD_NAME = " testConfirmOrder_001()";
        log.entering(STR_METHOD_NAME);

        Services.unregisterService(WEB3AioSLRepayApplyService.class);

        WEB3SLRepayApplyConfirmRequest l_request = new WEB3SLRepayApplyConfirmRequest();
        WEB3SLRepayApplyConfirmResponse l_response = new WEB3SLRepayApplyConfirmResponse(l_request);

        WEB3AioSLRepayApplyHandler l_handler = new WEB3AioSLRepayApplyHandler();
        l_response = l_handler.confirmOrder(l_request);

        log.exiting(STR_METHOD_NAME);
        assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);

        Services.registerService(WEB3AioSLRepayApplyService.class, new WEB3AioSLRepayApplyServiceImpl());
        Services.addInterceptor(
            WEB3AioSLRepayApplyService.class,
            new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(
            WEB3AioSLRepayApplyService.class,
            new WEB3AioSLAccountOpenServiceInterceptor());
        Services.addInterceptor(
            WEB3AioSLRepayApplyService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));
    }

    public void testConfirmOrder_002()
    {
        final String STR_METHOD_NAME = " testSlRepayInputRequest_0002()";
        log.entering(STR_METHOD_NAME);

        Services.overrideService(WEB3AioSLRepayApplyService.class, new WEB3AioSLRepayApplyServiceImplForTest1());

        WEB3SLRepayApplyConfirmRequest l_request = new WEB3SLRepayApplyConfirmRequest();
        WEB3SLRepayApplyConfirmResponse l_response = new WEB3SLRepayApplyConfirmResponse(l_request);

        WEB3AioSLRepayApplyHandler l_handler = new WEB3AioSLRepayApplyHandler();
        l_response = l_handler.confirmOrder(l_request);

        log.exiting(STR_METHOD_NAME);
        assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);

        Services.overrideService(WEB3AioSLRepayApplyService.class, new WEB3AioSLRepayApplyServiceImpl());
    }
    
    public void testConfirmOrder_003()
    {
        final String STR_METHOD_NAME = " testSlRepayInputRequest_0003()";
        log.entering(STR_METHOD_NAME);

        Services.overrideService(WEB3AioSLRepayApplyService.class, new WEB3AioSLRepayApplyServiceImplForTest());
        WEB3SLRepayApplyConfirmRequest l_request = new WEB3SLRepayApplyConfirmRequest();
        WEB3SLRepayApplyConfirmResponse l_response = new WEB3SLRepayApplyConfirmResponse(l_request);

        WEB3AioSLRepayApplyHandler l_handler = new WEB3AioSLRepayApplyHandler();
        l_response = l_handler.confirmOrder(l_request);

        log.exiting(STR_METHOD_NAME);
        assertEquals(null, l_response.errorInfo);

        Services.overrideService(WEB3AioSLRepayApplyService.class, new WEB3AioSLRepayApplyServiceImpl());
    }
    
    public void testCompleteOrder_001()
    {
        final String STR_METHOD_NAME = " testSlRepayInputRequest_0001()";
        log.entering(STR_METHOD_NAME);

        Services.unregisterService(WEB3AioSLRepayApplyService.class);
        
        WEB3SLRepayApplyCompleteRequest l_request = new WEB3SLRepayApplyCompleteRequest();
        WEB3SLRepayApplyCompleteResponse l_response = new WEB3SLRepayApplyCompleteResponse(l_request);

        WEB3AioSLRepayApplyHandler l_handler = new WEB3AioSLRepayApplyHandler();
        l_response = l_handler.completeOrder(l_request);
        
        log.exiting(STR_METHOD_NAME);
        assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);

        Services.registerService(WEB3AioSLRepayApplyService.class, new WEB3AioSLRepayApplyServiceImpl());
        Services.addInterceptor(
            WEB3AioSLRepayApplyService.class,
            new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(
            WEB3AioSLRepayApplyService.class,
            new WEB3AioSLAccountOpenServiceInterceptor());
        Services.addInterceptor(
            WEB3AioSLRepayApplyService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));
    }

    public void testCompleteOrder_002()
    {
        final String STR_METHOD_NAME = " testCompleteOrder_002()";
        log.entering(STR_METHOD_NAME);

        Services.overrideService(WEB3AioSLRepayApplyService.class, new WEB3AioSLRepayApplyServiceImplForTest1());

        WEB3SLRepayApplyCompleteRequest l_request = new WEB3SLRepayApplyCompleteRequest();
        WEB3SLRepayApplyCompleteResponse l_response = new WEB3SLRepayApplyCompleteResponse(l_request);

        WEB3AioSLRepayApplyHandler l_handler = new WEB3AioSLRepayApplyHandler();
        l_response = l_handler.completeOrder(l_request);

        log.exiting(STR_METHOD_NAME);
        assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);

        Services.overrideService(WEB3AioSLRepayApplyService.class, new WEB3AioSLRepayApplyServiceImpl());
    }

    public void testCompleteOrder_003()
    {
        final String STR_METHOD_NAME = " testCompleteOrder_003()";
        log.entering(STR_METHOD_NAME);

        Services.overrideService(WEB3AioSLRepayApplyService.class, new WEB3AioSLRepayApplyServiceImplForTest2());
        WEB3SLRepayApplyCompleteRequest l_request = new WEB3SLRepayApplyCompleteRequest();
        WEB3SLRepayApplyCompleteResponse l_response = new WEB3SLRepayApplyCompleteResponse(l_request);

        WEB3AioSLRepayApplyHandler l_handler = new WEB3AioSLRepayApplyHandler();
        l_response = l_handler.completeOrder(l_request);

        log.exiting(STR_METHOD_NAME);
        assertEquals(null, l_response.errorInfo);

        Services.overrideService(WEB3AioSLRepayApplyService.class, new WEB3AioSLRepayApplyServiceImpl());
    }

    private class WEB3AioSLRepayApplyServiceImplForTest extends WEB3AioSLRepayApplyServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            WEB3SLRepayApplyConfirmRequest l_request1 = new WEB3SLRepayApplyConfirmRequest();
            WEB3SLRepayApplyConfirmResponse l_response1 = new WEB3SLRepayApplyConfirmResponse(l_request1);
            return l_response1;
        }
    }

    private class WEB3AioSLRepayApplyServiceImplForTest2 extends WEB3AioSLRepayApplyServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            WEB3SLRepayApplyCompleteRequest l_request1 = new WEB3SLRepayApplyCompleteRequest();
            WEB3SLRepayApplyCompleteResponse l_response1 = new WEB3SLRepayApplyCompleteResponse(l_request1);
            return l_response1;
        }
    }

    private class WEB3AioSLRepayApplyServiceImplForTest1 extends WEB3AioSLRepayApplyServiceImpl
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

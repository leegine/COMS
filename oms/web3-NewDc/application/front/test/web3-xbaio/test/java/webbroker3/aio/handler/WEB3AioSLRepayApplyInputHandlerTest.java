head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.25.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioSLRepayApplyInputHandlerTest.java;


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
import webbroker3.aio.message.WEB3SLRepayApplyInputRequest;
import webbroker3.aio.message.WEB3SLRepayApplyInputResponse;
import webbroker3.aio.service.delegate.WEB3AioSLRepayApplyInputService;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSLRepayApplyInputServiceImpl;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AioSLRepayApplyInputHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLRepayApplyInputHandlerTest.class);

    public WEB3AioSLRepayApplyInputHandlerTest(String arg0)
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

    public void testSlRepayInputRequest_0001()
    {
        final String STR_METHOD_NAME = " testSlRepayInputRequest_0001()";
        log.entering(STR_METHOD_NAME);

        Services.unregisterService(WEB3AioSLRepayApplyInputService.class);
        
        WEB3SLRepayApplyInputRequest l_request = new WEB3SLRepayApplyInputRequest();
        WEB3SLRepayApplyInputResponse l_response = new WEB3SLRepayApplyInputResponse(l_request);

        WEB3AioSLRepayApplyInputHandler l_handler = new WEB3AioSLRepayApplyInputHandler();
        l_response = l_handler.slRepayInputRequest(l_request);
        
        log.exiting(STR_METHOD_NAME);
        assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);

        Services.registerService(WEB3AioSLRepayApplyInputService.class, new WEB3AioSLRepayApplyInputServiceImpl());
        Services.addInterceptor(
            WEB3AioSLRepayApplyInputService.class,
            new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(
            WEB3AioSLRepayApplyInputService.class,
            new WEB3AioSLAccountOpenServiceInterceptor());
        Services.addInterceptor(
            WEB3AioSLRepayApplyInputService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));
    }

    public void testSlRepayInputRequest_0002()
    {
        final String STR_METHOD_NAME = " testSlRepayInputRequest_0002()";
        log.entering(STR_METHOD_NAME);

        Services.overrideService(WEB3AioSLRepayApplyInputService.class, new WEB3AioSLRepayApplyInputServiceImplForTest1());

        WEB3SLRepayApplyInputRequest l_request = new WEB3SLRepayApplyInputRequest();
        WEB3SLRepayApplyInputResponse l_response = new WEB3SLRepayApplyInputResponse(l_request);

        WEB3AioSLRepayApplyInputHandler l_handler = new WEB3AioSLRepayApplyInputHandler();
        l_response = l_handler.slRepayInputRequest(l_request);

        log.exiting(STR_METHOD_NAME);
        assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);

        Services.overrideService(WEB3AioSLRepayApplyInputService.class, new WEB3AioSLRepayApplyInputServiceImpl());
    }
    
    public void testSlRepayInputRequest_0003()
    {
        final String STR_METHOD_NAME = " testSlRepayInputRequest_0003()";
        log.entering(STR_METHOD_NAME);

        Services.overrideService(WEB3AioSLRepayApplyInputService.class, new WEB3AioSLRepayApplyInputServiceImplForTest());
        WEB3SLRepayApplyInputRequest l_request = new WEB3SLRepayApplyInputRequest();
        WEB3SLRepayApplyInputResponse l_response = new WEB3SLRepayApplyInputResponse(l_request);

        WEB3AioSLRepayApplyInputHandler l_handler = new WEB3AioSLRepayApplyInputHandler();
        l_response = l_handler.slRepayInputRequest(l_request);

        log.exiting(STR_METHOD_NAME);
        assertEquals(null, l_response.errorInfo);

        Services.overrideService(WEB3AioSLRepayApplyInputService.class, new WEB3AioSLRepayApplyInputServiceImpl());
    }
    
    private class WEB3AioSLRepayApplyInputServiceImplForTest extends WEB3AioSLRepayApplyInputServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            WEB3SLRepayApplyInputRequest l_request1 = new WEB3SLRepayApplyInputRequest();
            WEB3SLRepayApplyInputResponse l_response1 = new WEB3SLRepayApplyInputResponse(l_request1);
            return l_response1;
        }
    }

    private class WEB3AioSLRepayApplyInputServiceImplForTest2 extends WEB3AioSLRepayApplyInputServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            WEB3SLRepayApplyInputRequest l_request1 = new WEB3SLRepayApplyInputRequest();
            WEB3SLRepayApplyInputResponse l_response1 = new WEB3SLRepayApplyInputResponse(l_request1);
            return l_response1;
        }
    }

    private class WEB3AioSLRepayApplyInputServiceImplForTest1 extends WEB3AioSLRepayApplyInputServiceImpl
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

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.24.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioSLAccountOpenHandlerTest.java;


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
import webbroker3.aio.message.WEB3SLAccountOpenApplyRequest;
import webbroker3.aio.message.WEB3SLAccountOpenApplyResponse;
import webbroker3.aio.message.WEB3SLAccountOpenRequest;
import webbroker3.aio.message.WEB3SLAccountOpenResponse;
import webbroker3.aio.service.delegate.WEB3AioSLAccountOpenUnitService;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSLAccountOpenUnitServiceImpl;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AioSLAccountOpenHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLAccountOpenHandlerTest.class);

    public WEB3AioSLAccountOpenHandlerTest(String arg0)
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

    public void testSlAccountOpenConfirm_0001()
    {
        final String STR_METHOD_NAME = " testSlAccountOpenConfirm_0001()";
        log.entering(STR_METHOD_NAME);

        Services.unregisterService(WEB3AioSLAccountOpenUnitService.class);
        
        WEB3SLAccountOpenApplyRequest l_request = new WEB3SLAccountOpenApplyRequest();
        WEB3SLAccountOpenApplyResponse l_response = new WEB3SLAccountOpenApplyResponse(l_request);

        WEB3AioSLAccountOpenHandler l_handler = new WEB3AioSLAccountOpenHandler();
        l_response = l_handler.slAccountOpenConfirm(l_request);
        
        log.exiting(STR_METHOD_NAME);
        assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);

        Services.registerService(WEB3AioSLAccountOpenUnitService.class, new WEB3AioSLAccountOpenUnitServiceImpl());
        Services.addInterceptor(
            WEB3AioSLAccountOpenUnitService.class,
            new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(
            WEB3AioSLAccountOpenUnitService.class,
            new WEB3AioSLAccountOpenServiceInterceptor());
        Services.addInterceptor(
            WEB3AioSLAccountOpenUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));
    }

    public void testSlAccountOpenConfirm_0002()
    {
        final String STR_METHOD_NAME = " testSlAccountOpenConfirm_0002()";
        log.entering(STR_METHOD_NAME);

        Services.overrideService(WEB3AioSLAccountOpenUnitService.class, new WEB3AioSLAccountOpenUnitServiceImplForTest1());

        WEB3SLAccountOpenApplyRequest l_request = new WEB3SLAccountOpenApplyRequest();
        WEB3SLAccountOpenApplyResponse l_response = new WEB3SLAccountOpenApplyResponse(l_request);

        WEB3AioSLAccountOpenHandler l_handler = new WEB3AioSLAccountOpenHandler();
        l_response = l_handler.slAccountOpenConfirm(l_request);

        log.exiting(STR_METHOD_NAME);
        assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);

        Services.overrideService(WEB3AioSLAccountOpenUnitService.class, new WEB3AioSLAccountOpenUnitServiceImpl());
    }

    public void testSlAccountOpenConfirm_0003()
    {
        final String STR_METHOD_NAME = " testSlAccountOpenConfirm_0003()";
        log.entering(STR_METHOD_NAME);

        Services.overrideService(WEB3AioSLAccountOpenUnitService.class, new WEB3AioSLAccountOpenUnitServiceImplForTest());
        WEB3SLAccountOpenApplyRequest l_request = new WEB3SLAccountOpenApplyRequest();
        WEB3SLAccountOpenApplyResponse l_response = new WEB3SLAccountOpenApplyResponse(l_request);

        WEB3AioSLAccountOpenHandler l_handler = new WEB3AioSLAccountOpenHandler();
        l_response = l_handler.slAccountOpenConfirm(l_request);

        log.exiting(STR_METHOD_NAME);
        assertEquals(null, l_response.errorInfo);

        Services.overrideService(WEB3AioSLAccountOpenUnitService.class, new WEB3AioSLAccountOpenUnitServiceImpl());
    }

    public void testSlAccountOpen_0001()
    {
        final String STR_METHOD_NAME = " testSlAccountOpen_0001()";
        log.entering(STR_METHOD_NAME);

        Services.unregisterService(WEB3AioSLAccountOpenUnitService.class);
        
        WEB3SLAccountOpenRequest l_request = new WEB3SLAccountOpenRequest();
        WEB3SLAccountOpenResponse l_response = new WEB3SLAccountOpenResponse(l_request);

        WEB3AioSLAccountOpenHandler l_handler = new WEB3AioSLAccountOpenHandler();
        l_response = l_handler.slAccountOpen(l_request);
        
        log.exiting(STR_METHOD_NAME);
        assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);

        Services.registerService(WEB3AioSLAccountOpenUnitService.class, new WEB3AioSLAccountOpenUnitServiceImpl());
        Services.addInterceptor(
            WEB3AioSLAccountOpenUnitService.class,
            new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(
            WEB3AioSLAccountOpenUnitService.class,
            new WEB3AioSLAccountOpenServiceInterceptor());
        Services.addInterceptor(
            WEB3AioSLAccountOpenUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));
    }
    
    public void testSlAccountOpen_0002()
    {
        final String STR_METHOD_NAME = " testSlAccountOpen_0002()";
        log.entering(STR_METHOD_NAME);
        
        Services.overrideService(WEB3AioSLAccountOpenUnitService.class, new WEB3AioSLAccountOpenUnitServiceImplForTest1());
        WEB3SLAccountOpenRequest l_request = new WEB3SLAccountOpenRequest();
        WEB3SLAccountOpenResponse l_response = new WEB3SLAccountOpenResponse(l_request);

        WEB3AioSLAccountOpenHandler l_handler = new WEB3AioSLAccountOpenHandler();
        l_response = l_handler.slAccountOpen(l_request);
        
        log.exiting(STR_METHOD_NAME);
        assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);

        Services.overrideService(WEB3AioSLAccountOpenUnitService.class, new WEB3AioSLAccountOpenUnitServiceImpl());
    }

    public void testSlAccountOpen_0003()
    {
        final String STR_METHOD_NAME = " testSlAccountOpen_0003()";
        log.entering(STR_METHOD_NAME);

        Services.overrideService(WEB3AioSLAccountOpenUnitService.class, new WEB3AioSLAccountOpenUnitServiceImplForTest2());
        WEB3SLAccountOpenRequest l_request = new WEB3SLAccountOpenRequest();
        WEB3SLAccountOpenResponse l_response = new WEB3SLAccountOpenResponse(l_request);

        WEB3AioSLAccountOpenHandler l_handler = new WEB3AioSLAccountOpenHandler();
        l_response = l_handler.slAccountOpen(l_request);
        
        log.exiting(STR_METHOD_NAME);
        assertEquals(null, l_response.errorInfo);

        Services.overrideService(WEB3AioSLAccountOpenUnitService.class, new WEB3AioSLAccountOpenUnitServiceImpl());
    }

    private class WEB3AioSLAccountOpenUnitServiceImplForTest extends WEB3AioSLAccountOpenUnitServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            WEB3SLAccountOpenApplyRequest l_request1 = new WEB3SLAccountOpenApplyRequest();
            WEB3SLAccountOpenApplyResponse l_response1 = new WEB3SLAccountOpenApplyResponse(l_request1);
            return l_response1;
        }
    }

    private class WEB3AioSLAccountOpenUnitServiceImplForTest2 extends WEB3AioSLAccountOpenUnitServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            WEB3SLAccountOpenRequest l_request1 = new WEB3SLAccountOpenRequest();
            WEB3SLAccountOpenResponse l_response1 = new WEB3SLAccountOpenResponse(l_request1);
            return l_response1;
        }
    }

    private class WEB3AioSLAccountOpenUnitServiceImplForTest1 extends WEB3AioSLAccountOpenUnitServiceImpl
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

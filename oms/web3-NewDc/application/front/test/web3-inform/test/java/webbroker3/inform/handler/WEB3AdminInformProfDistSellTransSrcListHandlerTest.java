head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.16.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformProfDistSellTransSrcListHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.inform.WEB3AdminInformProfDistSellTransSrcListServiceInterceptor;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcInpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcInpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcListRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcListResponse;
import webbroker3.inform.service.delegate.WEB3AdminInformProfDistSellTransSrcListService;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformProfDistSellTransSrcListServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminInformProfDistSellTransSrcListHandlerTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformProfDistSellTransSrcListHandlerTest.class);

    public WEB3AdminInformProfDistSellTransSrcListHandlerTest(String arg0)
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
    public void testDisplayInputScreen_0001()
    {
        String STR_METHOD_NAME = "testDisplayInputScreen_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminInformProfDistSellTransSrcListService.class);
            WEB3AdminInformProfDistSellTransSrcInpRequest request = new WEB3AdminInformProfDistSellTransSrcInpRequest();
            WEB3AdminInformProfDistSellTransSrcListHandler handler = new WEB3AdminInformProfDistSellTransSrcListHandler();
            WEB3AdminInformProfDistSellTransSrcInpResponse response = handler.displayInputScreen(request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002 , response.errorInfo);

            Services.registerService(WEB3AdminInformProfDistSellTransSrcListService.class,
                new WEB3AdminInformProfDistSellTransSrcListServiceImpl());
            Services.addInterceptor(WEB3AdminInformProfDistSellTransSrcListService.class,
                new WEB3AdminInformProfDistSellTransSrcListServiceInterceptor());
            Services.addInterceptor(WEB3AdminInformProfDistSellTransSrcListService.class,
                new WEB3LogSysTimeInterceptor());
            Services.addInterceptor(WEB3AdminInformProfDistSellTransSrcListService.class,
                new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testDisplayInputScreen_0002()
    {
        String STR_METHOD_NAME = "testDisplayInputScreen_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.overrideService(WEB3AdminInformProfDistSellTransSrcListService.class,
                new WEB3AdminInformProfDistSellTransSrcListServiceImplForMock1());
            
            WEB3AdminInformProfDistSellTransSrcInpRequest request = new WEB3AdminInformProfDistSellTransSrcInpRequest();
            WEB3AdminInformProfDistSellTransSrcListHandler handler = new WEB3AdminInformProfDistSellTransSrcListHandler();
            WEB3AdminInformProfDistSellTransSrcInpResponse response = handler.displayInputScreen(request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018 , response.errorInfo);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testDisplayInputScreen_0003()
    {
        String STR_METHOD_NAME = "testDisplayInputScreen_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.overrideService(WEB3AdminInformProfDistSellTransSrcListService.class,
                new WEB3AdminInformProfDistSellTransSrcListServiceImplForMock());

            WEB3AdminInformProfDistSellTransSrcInpRequest request = new WEB3AdminInformProfDistSellTransSrcInpRequest();
            WEB3AdminInformProfDistSellTransSrcListHandler handler = new WEB3AdminInformProfDistSellTransSrcListHandler();
            WEB3AdminInformProfDistSellTransSrcInpResponse response = handler.displayInputScreen(request);

            assertEquals(WEB3AdminInformProfDistSellTransSrcInpResponse.class , response.getClass());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testDisplayListScreen_0001()
    {
        String STR_METHOD_NAME = "testDisplayListScreen_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminInformProfDistSellTransSrcListService.class);
            WEB3AdminInformProfDistSellTransSrcListRequest request = new WEB3AdminInformProfDistSellTransSrcListRequest();
            WEB3AdminInformProfDistSellTransSrcListHandler handler = new WEB3AdminInformProfDistSellTransSrcListHandler();
            WEB3AdminInformProfDistSellTransSrcListResponse response = handler.displayListScreen(request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002 , response.errorInfo);

            Services.registerService(WEB3AdminInformProfDistSellTransSrcListService.class,
                new WEB3AdminInformProfDistSellTransSrcListServiceImpl());
            Services.addInterceptor(WEB3AdminInformProfDistSellTransSrcListService.class,
                new WEB3AdminInformProfDistSellTransSrcListServiceInterceptor());
            Services.addInterceptor(WEB3AdminInformProfDistSellTransSrcListService.class,
                new WEB3LogSysTimeInterceptor());
            Services.addInterceptor(WEB3AdminInformProfDistSellTransSrcListService.class,
                new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testDisplayListScreen_0002()
    {
        String STR_METHOD_NAME = "testDisplayListScreen_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.overrideService(WEB3AdminInformProfDistSellTransSrcListService.class,
                new WEB3AdminInformProfDistSellTransSrcListServiceImplForMock1());
            
            WEB3AdminInformProfDistSellTransSrcListRequest request = new WEB3AdminInformProfDistSellTransSrcListRequest();
            WEB3AdminInformProfDistSellTransSrcListHandler handler = new WEB3AdminInformProfDistSellTransSrcListHandler();
            WEB3AdminInformProfDistSellTransSrcListResponse response = handler.displayListScreen(request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018 , response.errorInfo);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testDisplayListScreen_0003()
    {
        String STR_METHOD_NAME = "testDisplayListScreen_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.overrideService(WEB3AdminInformProfDistSellTransSrcListService.class,
                new WEB3AdminInformProfDistSellTransSrcListServiceImplForMock());

            WEB3AdminInformProfDistSellTransSrcListRequest request = new WEB3AdminInformProfDistSellTransSrcListRequest();
            WEB3AdminInformProfDistSellTransSrcListHandler handler = new WEB3AdminInformProfDistSellTransSrcListHandler();
            WEB3AdminInformProfDistSellTransSrcListResponse response = handler.displayListScreen(request);

            assertEquals(WEB3AdminInformProfDistSellTransSrcListResponse.class , response.getClass());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public class WEB3AdminInformProfDistSellTransSrcListServiceImplForMock extends WEB3AdminInformProfDistSellTransSrcListServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            if (l_request instanceof WEB3AdminInformProfDistSellTransSrcInpRequest)
            {
                return new WEB3AdminInformProfDistSellTransSrcInpResponse();
            }
            else if (l_request instanceof WEB3AdminInformProfDistSellTransSrcListRequest)
            {
                return new WEB3AdminInformProfDistSellTransSrcListResponse();
            }
            else
            {
                return null;
            }
        }
    }
    
    public class WEB3AdminInformProfDistSellTransSrcListServiceImplForMock1 extends WEB3AdminInformProfDistSellTransSrcListServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_request] = " + l_request);
        }
    }
}
@

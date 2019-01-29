head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.24.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXTransFromFXHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.aio.message.WEB3FXTransFromFXCompleteSoapRequest;
import webbroker3.aio.message.WEB3FXTransFromFXCompleteSoapResponse;
import webbroker3.aio.service.delegate.WEB3FXTransFromFXService;
import webbroker3.aio.service.delegate.stdimpls.WEB3FXTransFromFXServiceImpl;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FXTransFromFXHandlerTest extends TestBaseForMock
{

    public WEB3FXTransFromFXHandlerTest(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransFromFXHandlerTest.class);

    private static WEB3FXTransFromFXHandler l_handler=
        new WEB3FXTransFromFXHandler();

    boolean isException = false;

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.aio.handler.WEB3FXTransFromFXHandler.orderCompleteSoap(WEB3FXTransFromFXCompleteSoapRequest)'
     */
    public void testOrderCompleteSoapC0001()
    {

        final String STR_METHOD_NAME = "testOrderCompleteSoapC0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3FXTransFromFXService.class);
            WEB3FXTransFromFXCompleteSoapRequest l_request =
                new WEB3FXTransFromFXCompleteSoapRequest();
            WEB3FXTransFromFXCompleteSoapResponse l_response =
                l_handler.orderCompleteSoap(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80002);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3FXTransFromFXService.class);
            Services.registerService(WEB3FXTransFromFXService.class,
                new WEB3FXTransFromFXServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testOrderCompleteSoapC0002()
    {

        final String STR_METHOD_NAME = "testOrderCompleteSoapC0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3FXTransFromFXService.class);
            Services.registerService(WEB3FXTransFromFXService.class,
                new WEB3FXTransFromFXServiceImplTest());

            isException = true;
            WEB3FXTransFromFXCompleteSoapRequest l_request =
                new WEB3FXTransFromFXCompleteSoapRequest();

            WEB3FXTransFromFXCompleteSoapResponse l_response =
                l_handler.orderCompleteSoap(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80003);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3FXTransFromFXService.class);
            Services.registerService(WEB3FXTransFromFXService.class,
                new WEB3FXTransFromFXServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testOrderCompleteSoapC0003()
    {

        final String STR_METHOD_NAME = "testOrderCompleteSoapC0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3FXTransFromFXService.class);
            Services.registerService(WEB3FXTransFromFXService.class,
                new WEB3FXTransFromFXServiceImplTest());

            WEB3FXTransFromFXCompleteSoapRequest l_request =
                new WEB3FXTransFromFXCompleteSoapRequest();

            WEB3FXTransFromFXCompleteSoapResponse l_response =
                l_handler.orderCompleteSoap(l_request);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3FXTransFromFXService.class);
            Services.registerService(WEB3FXTransFromFXService.class,
                new WEB3FXTransFromFXServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    class WEB3FXTransFromFXServiceImplTest
        extends WEB3FXTransFromFXServiceImpl
    {

        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            if (isException)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "fffffffffffff",
                    "dddddddddd");
            }

            WEB3GenResponse l_response = null;
            if (l_request instanceof WEB3FXTransFromFXCompleteSoapRequest)
            {
                //管理者PTS口座開設状況変更検索画面の取得を行う。
                l_response = new WEB3FXTransFromFXCompleteSoapResponse();
            }
            else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName(),
                    "パラメータタイプ不正。");
            }
            return l_response;
        }
    }
}
@

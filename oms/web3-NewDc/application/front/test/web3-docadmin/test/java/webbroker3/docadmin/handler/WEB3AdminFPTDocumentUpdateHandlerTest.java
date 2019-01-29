head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.56.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTDocumentUpdateHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateInputResponse;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTDocumentUpdateService;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTDocumentUpdateServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFPTDocumentUpdateHandlerTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocumentUpdateHandlerTest.class);
    WEB3AdminFPTDocumentUpdateHandler l_handler = new WEB3AdminFPTDocumentUpdateHandler();
    boolean isException = false;
    boolean isRunTimeException = false;

    public WEB3AdminFPTDocumentUpdateHandlerTest(String arg0)
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

    public void testGetDocumentUpdateInput_T01()
    {
        final String STR_METHOD_NAME = "testGetDocumentUpdateInput_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminFPTDocumentUpdateService.class);
            WEB3AdminFPTDocumentUpdateInputRequest l_request =
                new WEB3AdminFPTDocumentUpdateInputRequest();
            WEB3AdminFPTDocumentUpdateInputResponse l_response =
                l_handler.getDocumentUpdateInput(l_request);
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
            Services.unregisterService(WEB3AdminFPTDocumentUpdateService.class);
            Services.registerService(WEB3AdminFPTDocumentUpdateService.class,
                new WEB3AdminFPTDocumentUpdateServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetDocumentUpdateInput_T02()
    {
        final String STR_METHOD_NAME = "testGetDocumentUpdateInput_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            isException = true;
            Services.unregisterService(WEB3AdminFPTDocumentUpdateService.class);
            Services.registerService(WEB3AdminFPTDocumentUpdateService.class,
                new WEB3AdminFPTDocumentUpdateServiceImplForMock());
            WEB3AdminFPTDocumentUpdateInputRequest l_request =
                new WEB3AdminFPTDocumentUpdateInputRequest();
            WEB3AdminFPTDocumentUpdateInputResponse l_response =
                l_handler.getDocumentUpdateInput(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            isException = false;
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminFPTDocumentUpdateService.class);
            Services.registerService(WEB3AdminFPTDocumentUpdateService.class,
                new WEB3AdminFPTDocumentUpdateServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDocumentUpdateInput_T03()
    {
        final String STR_METHOD_NAME = "testGetDocumentUpdateInput_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminFPTDocumentUpdateService.class);
            Services.registerService(WEB3AdminFPTDocumentUpdateService.class,
                new WEB3AdminFPTDocumentUpdateServiceImplForMock());
            WEB3AdminFPTDocumentUpdateInputRequest l_request =
                new WEB3AdminFPTDocumentUpdateInputRequest();
            WEB3AdminFPTDocumentUpdateInputResponse l_response =
                l_handler.getDocumentUpdateInput(l_request);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminFPTDocumentUpdateService.class);
            Services.registerService(WEB3AdminFPTDocumentUpdateService.class,
                new WEB3AdminFPTDocumentUpdateServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetDocumentUpdateInput_T04()
    {
        final String STR_METHOD_NAME = "testGetDocumentUpdateInput_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            isRunTimeException = true;
            Services.unregisterService(WEB3AdminFPTDocumentUpdateService.class);
            Services.registerService(WEB3AdminFPTDocumentUpdateService.class,
                new WEB3AdminFPTDocumentUpdateServiceImplForMock());
            WEB3AdminFPTDocumentUpdateInputRequest l_request =
                new WEB3AdminFPTDocumentUpdateInputRequest();
            WEB3AdminFPTDocumentUpdateInputResponse l_response =
                l_handler.getDocumentUpdateInput(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80002);
            isRunTimeException = false;
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminFPTDocumentUpdateService.class);
            Services.registerService(WEB3AdminFPTDocumentUpdateService.class,
                new WEB3AdminFPTDocumentUpdateServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    
    
    public void testGetDocumentUpdateConfirm_T01()
    {
        final String STR_METHOD_NAME = "testGetDocumentUpdateConfirm_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminFPTDocumentUpdateService.class);
            WEB3AdminFPTDocumentUpdateConfirmRequest l_request =
                new WEB3AdminFPTDocumentUpdateConfirmRequest();
            WEB3AdminFPTDocumentUpdateConfirmResponse l_response =
                l_handler.getDocumentUpdateConfirm(l_request);
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
            Services.unregisterService(WEB3AdminFPTDocumentUpdateService.class);
            Services.registerService(WEB3AdminFPTDocumentUpdateService.class,
                new WEB3AdminFPTDocumentUpdateServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetDocumentUpdateConfirm_T02()
    {
        final String STR_METHOD_NAME = "testGetDocumentUpdateConfirm_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            isException = true;
            Services.unregisterService(WEB3AdminFPTDocumentUpdateService.class);
            Services.registerService(WEB3AdminFPTDocumentUpdateService.class,
                new WEB3AdminFPTDocumentUpdateServiceImplForMock());
            WEB3AdminFPTDocumentUpdateConfirmRequest l_request =
                new WEB3AdminFPTDocumentUpdateConfirmRequest();
            WEB3AdminFPTDocumentUpdateConfirmResponse l_response =
                l_handler.getDocumentUpdateConfirm(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            isException = false;
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminFPTDocumentUpdateService.class);
            Services.registerService(WEB3AdminFPTDocumentUpdateService.class,
                new WEB3AdminFPTDocumentUpdateServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDocumentUpdateConfirm_T03()
    {
        final String STR_METHOD_NAME = "testGetDocumentUpdateConfirm_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminFPTDocumentUpdateService.class);
            Services.registerService(WEB3AdminFPTDocumentUpdateService.class,
                new WEB3AdminFPTDocumentUpdateServiceImplForMock());
            WEB3AdminFPTDocumentUpdateConfirmRequest l_request =
                new WEB3AdminFPTDocumentUpdateConfirmRequest();
            WEB3AdminFPTDocumentUpdateConfirmResponse l_response =
                l_handler.getDocumentUpdateConfirm(l_request);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminFPTDocumentUpdateService.class);
            Services.registerService(WEB3AdminFPTDocumentUpdateService.class,
                new WEB3AdminFPTDocumentUpdateServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDocumentUpdateConfirm_T04()
    {
        final String STR_METHOD_NAME = "testGetDocumentUpdateConfirm_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            isRunTimeException = true;
            Services.unregisterService(WEB3AdminFPTDocumentUpdateService.class);
            Services.registerService(WEB3AdminFPTDocumentUpdateService.class,
                new WEB3AdminFPTDocumentUpdateServiceImplForMock());
            WEB3AdminFPTDocumentUpdateConfirmRequest l_request =
                new WEB3AdminFPTDocumentUpdateConfirmRequest();
            WEB3AdminFPTDocumentUpdateConfirmResponse l_response =
                l_handler.getDocumentUpdateConfirm(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80002);
            isRunTimeException = false;
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminFPTDocumentUpdateService.class);
            Services.registerService(WEB3AdminFPTDocumentUpdateService.class,
                new WEB3AdminFPTDocumentUpdateServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDocumentUpdateComplete_T01()
    {
        final String STR_METHOD_NAME = "testGetDocumentUpdateComplete_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminFPTDocumentUpdateService.class);
            WEB3AdminFPTDocumentUpdateCompleteRequest l_request =
                new WEB3AdminFPTDocumentUpdateCompleteRequest();
            WEB3AdminFPTDocumentUpdateCompleteResponse l_response =
                l_handler.getDocumentUpdateComplete(l_request);
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
            Services.unregisterService(WEB3AdminFPTDocumentUpdateService.class);
            Services.registerService(WEB3AdminFPTDocumentUpdateService.class,
                new WEB3AdminFPTDocumentUpdateServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDocumentUpdateComplete_T02()
    {
        final String STR_METHOD_NAME = "testGetDocumentUpdateComplete_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            isException = true;
            Services.unregisterService(WEB3AdminFPTDocumentUpdateService.class);
            Services.registerService(WEB3AdminFPTDocumentUpdateService.class,
                new WEB3AdminFPTDocumentUpdateServiceImplForMock());
            WEB3AdminFPTDocumentUpdateCompleteRequest l_request =
                new WEB3AdminFPTDocumentUpdateCompleteRequest();
            WEB3AdminFPTDocumentUpdateCompleteResponse l_response =
                l_handler.getDocumentUpdateComplete(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            isException = false;
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminFPTDocumentUpdateService.class);
            Services.registerService(WEB3AdminFPTDocumentUpdateService.class,
                new WEB3AdminFPTDocumentUpdateServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDocumentUpdateComplete_T03()
    {
        final String STR_METHOD_NAME = "testGetDocumentUpdateComplete_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminFPTDocumentUpdateService.class);
            Services.registerService(WEB3AdminFPTDocumentUpdateService.class,
                new WEB3AdminFPTDocumentUpdateServiceImplForMock());
            WEB3AdminFPTDocumentUpdateCompleteRequest l_request =
                new WEB3AdminFPTDocumentUpdateCompleteRequest();
            WEB3AdminFPTDocumentUpdateCompleteResponse l_response =
                l_handler.getDocumentUpdateComplete(l_request);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminFPTDocumentUpdateService.class);
            Services.registerService(WEB3AdminFPTDocumentUpdateService.class,
                new WEB3AdminFPTDocumentUpdateServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testGetDocumentUpdateComplete_T04()
    {
        final String STR_METHOD_NAME = "testGetDocumentUpdateComplete_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            isRunTimeException = true;
            Services.unregisterService(WEB3AdminFPTDocumentUpdateService.class);
            Services.registerService(WEB3AdminFPTDocumentUpdateService.class,
                new WEB3AdminFPTDocumentUpdateServiceImplForMock());
            WEB3AdminFPTDocumentUpdateCompleteRequest l_request =
                new WEB3AdminFPTDocumentUpdateCompleteRequest();
            WEB3AdminFPTDocumentUpdateCompleteResponse l_response =
                l_handler.getDocumentUpdateComplete(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80002);
            isRunTimeException = false;
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminFPTDocumentUpdateService.class);
            Services.registerService(WEB3AdminFPTDocumentUpdateService.class,
                new WEB3AdminFPTDocumentUpdateServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    class WEB3AdminFPTDocumentUpdateServiceImplForMock extends WEB3AdminFPTDocumentUpdateServiceImpl
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
           
            if(isRunTimeException)
            {
                log.debug("予期しないシステムエラーが発生しました。");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName(),
                    "予期しないシステムエラーが発生しました。");
            }


            WEB3GenResponse l_response = null;
            if (l_request instanceof WEB3AdminFPTDocumentUpdateInputRequest)
            {
                //管理者PTS口座開設状況変更検索画面の取得を行う。
                l_response = new WEB3AdminFPTDocumentUpdateInputResponse();
            }
            else if (l_request instanceof WEB3AdminFPTDocumentUpdateConfirmRequest)
            {
                //管理者PTS口座開設状況変更入力画面の取得を行う。
                l_response = new WEB3AdminFPTDocumentUpdateConfirmResponse();
            }
            else if (l_request instanceof WEB3AdminFPTDocumentUpdateCompleteRequest)
            {
                //管理者PTS口座開設状況変更確認処理を行う。
                l_response = new WEB3AdminFPTDocumentUpdateCompleteResponse();
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

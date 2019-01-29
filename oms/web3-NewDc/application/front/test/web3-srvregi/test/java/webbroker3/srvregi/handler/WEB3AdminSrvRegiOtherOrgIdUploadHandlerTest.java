head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.33.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiOtherOrgIdUploadHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadInputResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdListService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdUploadService;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiOtherOrgIdListServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiOtherOrgIdUploadServiceImpl;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiOtherOrgIdUploadHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiOtherOrgIdUploadHandlerTest.class);

    WEB3AdminSrvRegiOtherOrgIdUploadHandler l_handler =
        new WEB3AdminSrvRegiOtherOrgIdUploadHandler();
    boolean isException = false;
    public WEB3AdminSrvRegiOtherOrgIdUploadHandlerTest(String arg0)
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

    public void testOtherOrgIdUploadInput_T01()
    {
        final String STR_METHOD_NAME = "testOtherOrgIdListSearch_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdUploadService.class);
            WEB3AdminSrvRegiOtherOrgIdUploadInputRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadInputRequest();
            WEB3AdminSrvRegiOtherOrgIdUploadInputResponse l_response =
                l_handler.otherOrgIdUploadScreenDisplay(l_request);
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
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdListService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdListService.class,
                new WEB3AdminSrvRegiOtherOrgIdListServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testOtherOrgIdUploadInput_T02()
    {
        final String STR_METHOD_NAME = "testOtherOrgIdListSearch_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdUploadService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdUploadService.class,
                new WEB3AdminSrvRegiOtherOrgIdUploadServiceImplTest());
            WEB3AdminSrvRegiOtherOrgIdUploadInputRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadInputRequest();
            isException = true;
            WEB3AdminSrvRegiOtherOrgIdUploadInputResponse l_response =
                l_handler.otherOrgIdUploadScreenDisplay(l_request);
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
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdListService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdListService.class,
                new WEB3AdminSrvRegiOtherOrgIdListServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testOtherOrgIdUploadInput_T03()
    {
        final String STR_METHOD_NAME = "testOtherOrgIdListSearch_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdUploadService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdUploadService.class,
                new WEB3AdminSrvRegiOtherOrgIdUploadServiceImplTest());
            WEB3AdminSrvRegiOtherOrgIdUploadInputRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadInputRequest();

            WEB3AdminSrvRegiOtherOrgIdUploadInputResponse l_response =
                l_handler.otherOrgIdUploadScreenDisplay(l_request);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdListService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdListService.class,
                new WEB3AdminSrvRegiOtherOrgIdListServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testOtherOrgIdUploadConfirm_T01()
    {
        final String STR_METHOD_NAME = "testOtherOrgIdUploadConfirm_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdUploadService.class);
            WEB3AdminSrvRegiOtherOrgIdUploadInputRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadInputRequest();
            WEB3AdminSrvRegiOtherOrgIdUploadInputResponse l_response =
                l_handler.otherOrgIdUploadScreenDisplay(l_request);
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
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdListService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdListService.class,
                new WEB3AdminSrvRegiOtherOrgIdListServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testOtherOrgIdUploadConfirm_T02()
    {
        final String STR_METHOD_NAME = "testOtherOrgIdUploadConfirm_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdUploadService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdUploadService.class,
                new WEB3AdminSrvRegiOtherOrgIdUploadServiceImplTest());
            WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest();
            isException = true;
            WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse l_response =
                l_handler.otherOrgIdUploadConfirm(l_request);
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
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdListService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdListService.class,
                new WEB3AdminSrvRegiOtherOrgIdListServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testOtherOrgIdUploadConfirm_T03()
    {
        final String STR_METHOD_NAME = "testOtherOrgIdUploadConfirm_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdUploadService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdUploadService.class,
                new WEB3AdminSrvRegiOtherOrgIdUploadServiceImplTest());
            WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest();
            WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse l_response =
                l_handler.otherOrgIdUploadConfirm(l_request);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdListService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdListService.class,
                new WEB3AdminSrvRegiOtherOrgIdListServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testOtherOrgIdUploadComplete_T01()
    {
        final String STR_METHOD_NAME = "testOtherOrgIdUploadComplete_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdUploadService.class);
            WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest();
            WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse l_response =
                l_handler.otherOrgIdUpload(l_request);
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
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdListService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdListService.class,
                new WEB3AdminSrvRegiOtherOrgIdListServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testOtherOrgIdUploadComplete_T02()
    {
        final String STR_METHOD_NAME = "testOtherOrgIdUploadComplete_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdUploadService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdUploadService.class,
                new WEB3AdminSrvRegiOtherOrgIdUploadServiceImplTest());
            WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest();
            isException = true;
            WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse l_response =
                l_handler.otherOrgIdUpload(l_request);
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
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdListService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdListService.class,
                new WEB3AdminSrvRegiOtherOrgIdListServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testOtherOrgIdUploadComplete_T03()
    {
        final String STR_METHOD_NAME = "testOtherOrgIdUploadComplete_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdUploadService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdUploadService.class,
                new WEB3AdminSrvRegiOtherOrgIdUploadServiceImplTest());
            WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest();
            WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse l_response =
                l_handler.otherOrgIdUpload(l_request);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdListService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdListService.class,
                new WEB3AdminSrvRegiOtherOrgIdListServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testOtherOrgIdUploadCancel_T01()
    {
        final String STR_METHOD_NAME = "testOtherOrgIdUploadCancel_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdUploadService.class);
            WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest();
            WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse l_response =
                l_handler.otherOrgIdUploadCancel(l_request);
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
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdListService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdListService.class,
                new WEB3AdminSrvRegiOtherOrgIdListServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testOtherOrgIdUploadCancel_T02()
    {
        final String STR_METHOD_NAME = "testOtherOrgIdUploadCancel_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdUploadService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdUploadService.class,
                new WEB3AdminSrvRegiOtherOrgIdUploadServiceImplTest());
            WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest();
            isException = true;
            WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse l_response =
                l_handler.otherOrgIdUploadCancel(l_request);
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
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdListService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdListService.class,
                new WEB3AdminSrvRegiOtherOrgIdListServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testOtherOrgIdUploadCancel_T03()
    {
        final String STR_METHOD_NAME = "testOtherOrgIdUploadCancel_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdUploadService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdUploadService.class,
                new WEB3AdminSrvRegiOtherOrgIdUploadServiceImplTest());
            WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest();
            WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse l_response =
                l_handler.otherOrgIdUploadCancel(l_request);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdListService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdListService.class,
                new WEB3AdminSrvRegiOtherOrgIdListServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }


    class WEB3AdminSrvRegiOtherOrgIdUploadServiceImplTest extends WEB3AdminSrvRegiOtherOrgIdUploadServiceImpl
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
            if (l_request instanceof WEB3AdminSrvRegiOtherOrgIdUploadInputRequest)
            {
                //管理者PTS口座開設状況変更検索画面の取得を行う。
                l_response = new WEB3AdminSrvRegiOtherOrgIdUploadInputResponse();
            }
            else if (l_request instanceof WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest)
            {
                //管理者PTS口座開設状況変更入力画面の取得を行う。
                l_response = new WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse();
            }
            else if (l_request instanceof WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest)
            {
                //管理者PTS口座開設状況変更入力画面の取得を行う。
                l_response = new WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse();
            }
            else if (l_request instanceof WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest)
            {
                //管理者PTS口座開設状況変更入力画面の取得を行う。
                l_response = new WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse();
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

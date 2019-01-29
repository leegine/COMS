head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.56.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTUploadHandlerTest.java;


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
import webbroker3.docadmin.message.WEB3AdminFPTUploadCancelRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCancelResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUploadConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUploadInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadInputResponse;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTUploadService;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTUploadServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFPTUploadHandlerTest extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTUploadHandlerTest.class);

    public WEB3AdminFPTUploadHandlerTest(String arg0)
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

    public class WEB3AdminFPTUploadServiceImpl1 extends WEB3AdminFPTUploadServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            log.entering(STR_METHOD_NAME);
            WEB3GenResponse l_response = null;
            if (true)
            {
                log.debug("パラメータタイプ不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "パラメータタイプ不正。");
            }
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
    }

    public class WEB3AdminFPTUploadServiceImpl11 extends WEB3AdminFPTUploadServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            log.entering(STR_METHOD_NAME);
            WEB3GenResponse l_response = null;
            if (true)
            {
                log.debug("パラメータタイプ不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "パラメータタイプ不正。");
            }
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
    }

    public class WEB3AdminFPTUploadServiceImpl2 extends WEB3AdminFPTUploadServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            log.entering(STR_METHOD_NAME);
            WEB3GenResponse l_response = null;
            if (true)
            {
                log.debug("予期しないシステムエラーが発生しました。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "予期しないシステムエラーが発生しました。");
            }
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
    }

    public class WEB3AdminFPTUploadServiceImpl3 extends WEB3AdminFPTUploadServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            log.entering(STR_METHOD_NAME);

            WEB3GenResponse l_response = null;

            if (l_request instanceof WEB3AdminFPTUploadInputRequest)
            {
                l_response = (WEB3AdminFPTUploadInputResponse)l_request.createResponse();
            }
            else if (l_request instanceof WEB3AdminFPTUploadConfirmRequest)
            {

                l_response =
                    (WEB3AdminFPTUploadConfirmResponse)l_request.createResponse();
            }
            else if (l_request instanceof WEB3AdminFPTUploadCompleteRequest)
            {

                l_response =
                    (WEB3AdminFPTUploadCompleteResponse)l_request.createResponse();
            }
            else if (l_request instanceof WEB3AdminFPTUploadCancelRequest)
            {
                l_response =
                    (WEB3AdminFPTUploadCancelResponse)l_request.createResponse();
            }

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
    }

    WEB3AdminFPTUploadHandler l_handler = new WEB3AdminFPTUploadHandler();

    public void testUploadScreenDisplay_T001()
    {

        final String STR_METHOD_NAME = "testUploadScreenDisplay_T001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Services.unregisterService(WEB3AdminFPTUploadService.class);
            WEB3AdminFPTUploadInputRequest l_Request =
                new WEB3AdminFPTUploadInputRequest();

            WEB3AdminFPTUploadInputResponse l_response =
                l_handler.uploadScreenDisplay(l_Request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminFPTUploadService.class,
                    new WEB3AdminFPTUploadServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testUploadScreenDisplay_T002()
    {

        final String STR_METHOD_NAME = "testUploadScreenDisplay_T002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Services.overrideService(
                WEB3AdminFPTUploadService.class,
                new WEB3AdminFPTUploadServiceImpl2());

            WEB3AdminFPTUploadInputRequest l_Request =
                new WEB3AdminFPTUploadInputRequest();

            WEB3AdminFPTUploadInputResponse l_response =
                l_handler.uploadScreenDisplay(l_Request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testUploadScreenDisplay_T003()
    {

        final String STR_METHOD_NAME = "testUploadScreenDisplay_T003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Services.overrideService(
                WEB3AdminFPTUploadService.class,
                new WEB3AdminFPTUploadServiceImpl1());

            WEB3AdminFPTUploadInputRequest l_Request =
                new WEB3AdminFPTUploadInputRequest();

            WEB3AdminFPTUploadInputResponse l_response =
                l_handler.uploadScreenDisplay(l_Request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testUploadScreenDisplay_T004()
    {

        final String STR_METHOD_NAME = "testUploadScreenDisplay_T004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Services.overrideService(
                WEB3AdminFPTUploadService.class,
                new WEB3AdminFPTUploadServiceImpl3());

            WEB3AdminFPTUploadInputRequest l_Request =
                new WEB3AdminFPTUploadInputRequest();

            l_handler.uploadScreenDisplay(l_Request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testUploadFileConfirm_T001()
    {

        final String STR_METHOD_NAME = "testUploadFileConfirm_T001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Services.unregisterService(WEB3AdminFPTUploadService.class);
            WEB3AdminFPTUploadConfirmRequest l_Request =
                new WEB3AdminFPTUploadConfirmRequest();

            WEB3AdminFPTUploadConfirmResponse l_response =
                l_handler.uploadFileConfirm(l_Request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminFPTUploadService.class,
                    new WEB3AdminFPTUploadServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testUploadFileConfirm_T002()
    {

        final String STR_METHOD_NAME = "testUploadFileConfirm_T002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Services.overrideService(
                WEB3AdminFPTUploadService.class,
                new WEB3AdminFPTUploadServiceImpl2());

            WEB3AdminFPTUploadConfirmRequest l_Request =
                new WEB3AdminFPTUploadConfirmRequest();

            WEB3AdminFPTUploadConfirmResponse l_response =
                l_handler.uploadFileConfirm(l_Request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testUploadFileConfirm_T003()
    {

        final String STR_METHOD_NAME = "testUploadFileConfirm_T003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Services.overrideService(
                WEB3AdminFPTUploadService.class,
                new WEB3AdminFPTUploadServiceImpl11());

            WEB3AdminFPTUploadConfirmRequest l_Request =
                new WEB3AdminFPTUploadConfirmRequest();

            WEB3AdminFPTUploadConfirmResponse l_response =
                l_handler.uploadFileConfirm(l_Request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
            assertEquals(l_response.errorMessage, "パラメータタイプ不正。");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testUploadFileConfirm_T004()
    {

        final String STR_METHOD_NAME = "testUploadFileConfirm_T004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Services.overrideService(
                WEB3AdminFPTUploadService.class,
                new WEB3AdminFPTUploadServiceImpl3());

            WEB3AdminFPTUploadConfirmRequest l_Request =
                new WEB3AdminFPTUploadConfirmRequest();

            l_handler.uploadFileConfirm(l_Request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testAdminFPTUpload_T001()
    {

        final String STR_METHOD_NAME = "testAdminFPTUpload_T001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Services.unregisterService(WEB3AdminFPTUploadService.class);
            WEB3AdminFPTUploadCompleteRequest l_request =
                new WEB3AdminFPTUploadCompleteRequest();

            WEB3AdminFPTUploadCompleteResponse l_response =
                l_handler.adminFPTUpload(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminFPTUploadService.class,
                    new WEB3AdminFPTUploadServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testAdminFPTUpload_T002()
    {

        final String STR_METHOD_NAME = "testAdminFPTUpload_T002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Services.overrideService(
                WEB3AdminFPTUploadService.class,
                new WEB3AdminFPTUploadServiceImpl2());

            WEB3AdminFPTUploadCompleteRequest l_request =
                new WEB3AdminFPTUploadCompleteRequest();

            WEB3AdminFPTUploadCompleteResponse l_response =
                l_handler.adminFPTUpload(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testAdminFPTUpload_T003()
    {

        final String STR_METHOD_NAME = "testAdminFPTUpload_T003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Services.overrideService(
                WEB3AdminFPTUploadService.class,
                new WEB3AdminFPTUploadServiceImpl11());

            WEB3AdminFPTUploadCompleteRequest l_request =
                new WEB3AdminFPTUploadCompleteRequest();

            WEB3AdminFPTUploadCompleteResponse l_response =
                l_handler.adminFPTUpload(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testAdminFPTUpload_T004()
    {

        final String STR_METHOD_NAME = "testAdminFPTUpload_T004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Services.overrideService(
                WEB3AdminFPTUploadService.class,
                new WEB3AdminFPTUploadServiceImpl3());

            WEB3AdminFPTUploadCompleteRequest l_request =
                new WEB3AdminFPTUploadCompleteRequest();

            l_handler.adminFPTUpload(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testUploadCancel_T001()
    {

        final String STR_METHOD_NAME = "testUploadCancel_T001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Services.unregisterService(WEB3AdminFPTUploadService.class);
            WEB3AdminFPTUploadCancelRequest l_request =
                new WEB3AdminFPTUploadCancelRequest();

            WEB3AdminFPTUploadCancelResponse l_response =
                l_handler.uploadCancel(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminFPTUploadService.class,
                    new WEB3AdminFPTUploadServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testUploadCancel_T002()
    {

        final String STR_METHOD_NAME = "testUploadCancel_T002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Services.overrideService(
                WEB3AdminFPTUploadService.class,
                new WEB3AdminFPTUploadServiceImpl2());

            WEB3AdminFPTUploadCancelRequest l_request =
                new WEB3AdminFPTUploadCancelRequest();

            WEB3AdminFPTUploadCancelResponse l_response =
                l_handler.uploadCancel(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testUploadCancel_T003()
    {

        final String STR_METHOD_NAME = "testUploadCancel_T003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Services.overrideService(
                WEB3AdminFPTUploadService.class,
                new WEB3AdminFPTUploadServiceImpl1());

            WEB3AdminFPTUploadCancelRequest l_request =
                new WEB3AdminFPTUploadCancelRequest();

            WEB3AdminFPTUploadCancelResponse l_response =
                l_handler.uploadCancel(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testUploadCancel_T004()
    {

        final String STR_METHOD_NAME = "testUploadCancel_T004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Services.overrideService(
                WEB3AdminFPTUploadService.class,
                new WEB3AdminFPTUploadServiceImpl3());

            WEB3AdminFPTUploadCancelRequest l_request =
                new WEB3AdminFPTUploadCancelRequest();

            l_handler.uploadCancel(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@

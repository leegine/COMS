head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.33.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecTriggerIssueHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueInputRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueInputResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueListResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecTriggerIssueService;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecTriggerIssueServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminDirSecTriggerIssueHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminDirSecTriggerIssueHandlerTest.class);

    WEB3AdminDirSecTriggerIssueHandler l_handler;
    public WEB3AdminDirSecTriggerIssueHandlerTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_handler = new WEB3AdminDirSecTriggerIssueHandler();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * getトリガー発行処理一覧画面表示
     * トリガー発行処理一覧画面表示を行う
     */
    public void testGetTriggerIssueListScreenDisplay_C001()
    {
        final String STR_METHOD_NAME = "testGetTriggerIssueListScreenDisplay_C001";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecTriggerIssueListRequest l_request =
            new WEB3AdminDirSecTriggerIssueListRequest();

        try
        {
            Services.unregisterService(WEB3AdminDirSecTriggerIssueService.class);
            WEB3AdminDirSecTriggerIssueListResponse l_response =
                l_handler.getTriggerIssueListScreenDisplay(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                Services.registerService(
                    WEB3AdminDirSecTriggerIssueService.class,
                    new WEB3AdminDirSecTriggerIssueServiceImpl());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetTriggerIssueListScreenDisplay_C002()
    {
        final String STR_METHOD_NAME = "testGetTriggerIssueListScreenDisplay_C002";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecTriggerIssueListRequest l_request =
            new WEB3AdminDirSecTriggerIssueListRequest();
        try
        {
            Services.overrideService(
                WEB3AdminDirSecTriggerIssueService.class,
                new WEB3AdminDirSecTriggerIssueServiceImplForTestA());

            WEB3AdminDirSecTriggerIssueListResponse l_response =
                l_handler.getTriggerIssueListScreenDisplay(l_request);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00857, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.entering(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetTriggerIssueListScreenDisplay_C003()
    {
        final String STR_METHOD_NAME = "testGetTriggerIssueListScreenDisplay_C003";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecTriggerIssueListRequest l_request =
            new WEB3AdminDirSecTriggerIssueListRequest();
        try
        {
            Services.overrideService(
                WEB3AdminDirSecTriggerIssueService.class,
                new WEB3AdminDirSecTriggerIssueServiceImplForTestB());

            WEB3AdminDirSecTriggerIssueListResponse l_response =
                l_handler.getTriggerIssueListScreenDisplay(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.entering(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetTriggerIssueListScreenDisplay_C004()
    {
        final String STR_METHOD_NAME = "testGetTriggerIssueListScreenDisplay_C004";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecTriggerIssueListRequest l_request =
            new WEB3AdminDirSecTriggerIssueListRequest();
        try
        {
            Services.overrideService(
                WEB3AdminDirSecTriggerIssueService.class,
                new WEB3AdminDirSecTriggerIssueServiceImplForTestC());

            WEB3AdminDirSecTriggerIssueListResponse l_response =
                l_handler.getTriggerIssueListScreenDisplay(l_request);
        }
        catch (Exception l_ex)
        {
            log.entering(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * getトリガー発行処理入力画面表示
     *トリガー発行処理入力画面表示を行う
     */
    public void testGetTriggerIssueInputScreenDisplay_C001()
    {
        final String STR_METHOD_NAME = "testGetTriggerIssueInputScreenDisplay_C001";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecTriggerIssueInputRequest l_request =
            new WEB3AdminDirSecTriggerIssueInputRequest();

        try
        {
            Services.unregisterService(WEB3AdminDirSecTriggerIssueService.class);
            WEB3AdminDirSecTriggerIssueInputResponse l_response =
                l_handler.getTriggerIssueInputScreenDisplay(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                Services.registerService(
                    WEB3AdminDirSecTriggerIssueService.class,
                    new WEB3AdminDirSecTriggerIssueServiceImpl());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetTriggerIssueInputScreenDisplay_C002()
    {
        final String STR_METHOD_NAME = "testGetTriggerIssueInputScreenDisplay_C002";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecTriggerIssueInputRequest l_request =
            new WEB3AdminDirSecTriggerIssueInputRequest();
        try
        {
            Services.overrideService(
                WEB3AdminDirSecTriggerIssueService.class,
                new WEB3AdminDirSecTriggerIssueServiceImplForTestA());

            WEB3AdminDirSecTriggerIssueInputResponse l_response =
                l_handler.getTriggerIssueInputScreenDisplay(l_request);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00857, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.entering(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetTriggerIssueInputScreenDisplay_C003()
    {
        final String STR_METHOD_NAME = "testGetTriggerIssueInputScreenDisplay_C003";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecTriggerIssueInputRequest l_request =
            new WEB3AdminDirSecTriggerIssueInputRequest();
        try
        {
            Services.overrideService(
                WEB3AdminDirSecTriggerIssueService.class,
                new WEB3AdminDirSecTriggerIssueServiceImplForTestB());

            WEB3AdminDirSecTriggerIssueInputResponse l_response =
                l_handler.getTriggerIssueInputScreenDisplay(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.entering(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetTriggerIssueInputScreenDisplay_C004()
    {
        final String STR_METHOD_NAME = "testGetTriggerIssueInputScreenDisplay_C004";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecTriggerIssueInputRequest l_request =
            new WEB3AdminDirSecTriggerIssueInputRequest();
        try
        {
            Services.overrideService(
                WEB3AdminDirSecTriggerIssueService.class,
                new WEB3AdminDirSecTriggerIssueServiceImplForTestC());

            WEB3AdminDirSecTriggerIssueInputResponse l_response =
                l_handler.getTriggerIssueInputScreenDisplay(l_request);
        }
        catch (Exception l_ex)
        {
            log.entering(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validateトリガー発行処理確認画面表示
     * トリガー発行処理確認画面表示を行う
     */

    public void testValidateTriggerIssueConfirmScreenDisplay_C001()
    {
        final String STR_METHOD_NAME = "testValidateTriggerIssueConfirmScreenDisplay_C001";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecTriggerIssueConfirmRequest l_request =
            new WEB3AdminDirSecTriggerIssueConfirmRequest();

        try
        {
            Services.unregisterService(WEB3AdminDirSecTriggerIssueService.class);
            WEB3AdminDirSecTriggerIssueConfirmResponse l_response =
                l_handler.validateTriggerIssueConfirmScreenDisplay(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                Services.registerService(
                    WEB3AdminDirSecTriggerIssueService.class,
                    new WEB3AdminDirSecTriggerIssueServiceImpl());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateTriggerIssueConfirmScreenDisplay_C002()
    {
        final String STR_METHOD_NAME = "testValidateTriggerIssueConfirmScreenDisplay_C002";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecTriggerIssueConfirmRequest l_request =
            new WEB3AdminDirSecTriggerIssueConfirmRequest();
        try
        {
            Services.overrideService(
                    WEB3AdminDirSecTriggerIssueService.class,
                    new WEB3AdminDirSecTriggerIssueServiceImplForTestA());

            WEB3AdminDirSecTriggerIssueConfirmResponse l_response =
                l_handler.validateTriggerIssueConfirmScreenDisplay(l_request);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00857, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.entering(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateTriggerIssueConfirmScreenDisplay_C003()
    {
        final String STR_METHOD_NAME = "testValidateTriggerIssueConfirmScreenDisplay_C003";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecTriggerIssueConfirmRequest l_request =
            new WEB3AdminDirSecTriggerIssueConfirmRequest();
        try
        {
            Services.overrideService(
                WEB3AdminDirSecTriggerIssueService.class,
                new WEB3AdminDirSecTriggerIssueServiceImplForTestB());

            WEB3AdminDirSecTriggerIssueConfirmResponse l_response =
                l_handler.validateTriggerIssueConfirmScreenDisplay(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.entering(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateTriggerIssueConfirmScreenDisplay_C004()
    {
        final String STR_METHOD_NAME = "testValidateTriggerIssueConfirmScreenDisplay_C004";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecTriggerIssueConfirmRequest l_request =
            new WEB3AdminDirSecTriggerIssueConfirmRequest();
        try
        {
            Services.overrideService(
                    WEB3AdminDirSecTriggerIssueService.class,
                    new WEB3AdminDirSecTriggerIssueServiceImplForTestC());

            WEB3AdminDirSecTriggerIssueConfirmResponse l_response =
                l_handler.validateTriggerIssueConfirmScreenDisplay(l_request);
        }
        catch (Exception l_ex)
        {
            log.entering(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * submitトリガー発行処理完了画面表示
     * トリガー発行処理完了画面表示処理を行う
     */

    public void testSubmitTriggerIssueCompleteScreenDisplay_C001()
    {
        final String STR_METHOD_NAME = "testSubmitTriggerIssueCompleteScreenDisplay_C001";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecTriggerIssueCompleteRequest l_request =
            new WEB3AdminDirSecTriggerIssueCompleteRequest();

        try
        {
            Services.unregisterService(WEB3AdminDirSecTriggerIssueService.class);
            WEB3AdminDirSecTriggerIssueCompleteResponse l_response =
                l_handler.submitTriggerIssueCompleteScreenDisplay(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                Services.registerService(
                    WEB3AdminDirSecTriggerIssueService.class,
                    new WEB3AdminDirSecTriggerIssueServiceImpl());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitTriggerIssueCompleteScreenDisplay_C002()
    {
        final String STR_METHOD_NAME = "testSubmitTriggerIssueCompleteScreenDisplay_C002";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecTriggerIssueCompleteRequest l_request =
            new WEB3AdminDirSecTriggerIssueCompleteRequest();
        try
        {
            Services.overrideService(
                WEB3AdminDirSecTriggerIssueService.class,
                new WEB3AdminDirSecTriggerIssueServiceImplForTestA());

            WEB3AdminDirSecTriggerIssueCompleteResponse l_response =
                l_handler.submitTriggerIssueCompleteScreenDisplay(l_request);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00857, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.entering(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitTriggerIssueCompleteScreenDisplay_C003()
    {
        final String STR_METHOD_NAME = "testSubmitTriggerIssueCompleteScreenDisplay_C003";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecTriggerIssueCompleteRequest l_request =
            new WEB3AdminDirSecTriggerIssueCompleteRequest();
        try
        {
            Services.overrideService(
                WEB3AdminDirSecTriggerIssueService.class,
                new WEB3AdminDirSecTriggerIssueServiceImplForTestB());

            WEB3AdminDirSecTriggerIssueCompleteResponse l_response =
                l_handler.submitTriggerIssueCompleteScreenDisplay(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.entering(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitTriggerIssueCompleteScreenDisplay_C004()
    {
        final String STR_METHOD_NAME = "testSubmitTriggerIssueCompleteScreenDisplay_C004";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecTriggerIssueCompleteRequest l_request =
            new WEB3AdminDirSecTriggerIssueCompleteRequest();
        try
        {
            Services.overrideService(
                    WEB3AdminDirSecTriggerIssueService.class,
                    new WEB3AdminDirSecTriggerIssueServiceImplForTestC());

            WEB3AdminDirSecTriggerIssueCompleteResponse l_response =
                l_handler.submitTriggerIssueCompleteScreenDisplay(l_request);
        }
        catch (Exception l_ex)
        {
            log.entering(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    //888888888888888888888888888888888888888888888
    public class WEB3AdminDirSecTriggerIssueServiceImplForTestA
        extends WEB3AdminDirSecTriggerIssueServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            log.entering(STR_METHOD_NAME);

            WEB3GenResponse l_response = null;
            if (true)
            {
                log.debug("DIR管理者権限チェックエラー。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00857 ,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DIR管理者権限チェックエラー。");
            }
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
    }

    public class WEB3AdminDirSecTriggerIssueServiceImplForTestB
        extends WEB3AdminDirSecTriggerIssueServiceImpl
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

    public class WEB3AdminDirSecTriggerIssueServiceImplForTestC
        extends WEB3AdminDirSecTriggerIssueServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            log.entering(STR_METHOD_NAME);

            WEB3GenResponse l_response = l_request.createResponse();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
    }
}
@

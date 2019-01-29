head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.24.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAioListHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者入出金一覧ハンドラテスト(WEB3AdminAioListHandlerTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/06 周捷 (中訊) 新規作成
*/
package webbroker3.aio.handler;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.aio.message.WEB3AdminAioCashTransferListDownloadRequest;
import webbroker3.aio.message.WEB3AdminAioCashTransferListDownloadResponse;
import webbroker3.aio.message.WEB3AdminAioCashTransferListInputRequest;
import webbroker3.aio.message.WEB3AdminAioCashTransferListInputResponse;
import webbroker3.aio.message.WEB3AdminAioCashTransferListRequest;
import webbroker3.aio.message.WEB3AdminAioCashTransferListResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * （管理者入出金一覧ハンドラテスト）<BR>
 *
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3AdminAioListHandlerTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3AdminAioListHandlerTest.class);

    WEB3AdminAioListHandler handler = null;
    WEB3AdminAioListService service = null;

    public WEB3AdminAioListHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        handler = new WEB3AdminAioListHandler();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.handler = null;
        this.service = null;
    }

    /*
     * Test method for 'webbroker3.aio.handler.WEB3AdminAioListHandler.getInputScreen(WEB3AdminAioCashTransferListInputRequest)'
     */
    public void testGetInputScreen1()
    {
        final String STR_METHOD_NAME = "testGetInputScreen1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminAioCashTransferListInputRequest l_request = new WEB3AdminAioCashTransferListInputRequest();
        try
        {
            service =
                (WEB3AdminAioListService)Services.getService(WEB3AdminAioListService.class);
            WEB3AdminAioCashTransferListInputResponse l_response = null;
            Services.unregisterService(WEB3AdminAioListService.class);
            l_response = this.handler.getInputScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminAioListService.class, service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetInputScreen2()
    {
        final String STR_METHOD_NAME = "testGetInputScreen2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminAioCashTransferListInputRequest l_request = new WEB3AdminAioCashTransferListInputRequest();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioListServiceImpl",
            "execute",
            new Class[] {WEB3GenRequest.class},
            new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ""));
        try
        {
            WEB3AdminAioCashTransferListInputResponse l_response = null;
            l_response = this.handler.getInputScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetInputScreen3()
    {
        final String STR_METHOD_NAME = "testGetInputScreen3()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminAioCashTransferListInputRequest l_request = new WEB3AdminAioCashTransferListInputRequest();
        WEB3AdminAioCashTransferListInputResponse l_responseReturn = new WEB3AdminAioCashTransferListInputResponse();
        l_responseReturn.deliveryDate = new Date("2007/02/09");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioListServiceImpl",
            "execute",
            new Class[] {WEB3GenRequest.class},
            l_responseReturn);
        try
        {
            WEB3AdminAioCashTransferListInputResponse l_response = null;
            l_response = this.handler.getInputScreen(l_request);
            assertEquals(new Date("2007/02/09"), l_response.deliveryDate);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.aio.handler.WEB3AdminAioListHandler.getListScreen(WEB3AdminAioCashTransferListRequest)'
     */
    public void testGetListScreen1()
    {
        final String STR_METHOD_NAME = "testGetInputScreen3()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminAioCashTransferListRequest l_request = new WEB3AdminAioCashTransferListRequest();
        try
        {
            service =
                (WEB3AdminAioListService)Services.getService(WEB3AdminAioListService.class);
            WEB3AdminAioCashTransferListResponse l_response = null;
            Services.unregisterService(WEB3AdminAioListService.class);
            l_response = this.handler.getListScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminAioListService.class, service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetListScreen2()
    {
        final String STR_METHOD_NAME = "testGetListScreen2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminAioCashTransferListRequest l_request = new WEB3AdminAioCashTransferListRequest();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioListServiceImpl",
            "execute",
            new Class[] {WEB3GenRequest.class},
            new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ""));
        try
        {
            WEB3AdminAioCashTransferListResponse l_response = null;
            l_response = this.handler.getListScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetListScreen3()
    {
        final String STR_METHOD_NAME = "testGetListScreen3()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminAioCashTransferListRequest l_request = new WEB3AdminAioCashTransferListRequest();
        WEB3AdminAioCashTransferListResponse l_responseReturn =
            new WEB3AdminAioCashTransferListResponse();
        l_responseReturn.cashoutTotal = "100";
        l_responseReturn.netCashinTotal = "100";
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioListServiceImpl",
            "execute",
            new Class[] {WEB3GenRequest.class},
            l_responseReturn);
        try
        {
            WEB3AdminAioCashTransferListResponse l_response = null;
            l_response = this.handler.getListScreen(l_request);
            assertEquals("100", l_response.cashoutTotal);
            assertEquals("100", l_response.netCashinTotal);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.aio.handler.WEB3AdminAioListHandler.getDownLoadFile(WEB3AdminAioCashTransferListDownloadRequest)'
     */
    public void testGetDownLoadFile1()
    {
        final String STR_METHOD_NAME = "testGetDownLoadFile1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminAioCashTransferListDownloadRequest l_request = new WEB3AdminAioCashTransferListDownloadRequest();
        try
        {
            service =
                (WEB3AdminAioListService)Services.getService(WEB3AdminAioListService.class);
            WEB3AdminAioCashTransferListDownloadResponse l_response = null;
            Services.unregisterService(WEB3AdminAioListService.class);
            l_response = this.handler.getDownLoadFile(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminAioListService.class, service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetDownLoadFile2()
    {
        final String STR_METHOD_NAME = "testGetDownLoadFile2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminAioCashTransferListDownloadRequest l_request = new WEB3AdminAioCashTransferListDownloadRequest();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioListServiceImpl",
            "execute",
            new Class[] {WEB3GenRequest.class},
            new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ""));
        try
        {
            WEB3AdminAioCashTransferListDownloadResponse l_response = null;
            l_response = this.handler.getDownLoadFile(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetDownLoadFile3()
    {
        final String STR_METHOD_NAME = "testGetDownLoadFile3()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminAioCashTransferListDownloadRequest l_request = new WEB3AdminAioCashTransferListDownloadRequest();
        WEB3AdminAioCashTransferListDownloadResponse l_responseReturn = new WEB3AdminAioCashTransferListDownloadResponse();
        l_responseReturn.currentDate = new Date("2007/02/09");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioListServiceImpl",
                "execute",
                new Class[] {WEB3GenRequest.class},
                l_responseReturn);
        try
        {
            WEB3AdminAioCashTransferListDownloadResponse l_response = null;
            l_response = this.handler.getDownLoadFile(l_request);
            assertEquals(new Date("2007/02/09"), l_response.currentDate);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@

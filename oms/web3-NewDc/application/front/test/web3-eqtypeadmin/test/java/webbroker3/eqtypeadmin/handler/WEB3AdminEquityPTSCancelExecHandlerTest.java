head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.14.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityPTSCancelExecHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3AdminEquityPTSCancelExecHandlerTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/30 トウ鋒鋼 (中訊) 新規作成
*/
package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSOrderDetailUnit;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityPTSCancelExecService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityPTSCancelExecHandlerTest extends TestBaseForMock
{
    /**
     *　@ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityPTSCancelExecHandlerTest.class);

    WEB3AdminEquityPTSCancelExecHandler l_handler = new WEB3AdminEquityPTSCancelExecHandler();
    WEB3AdminEquityPTSCancelExecService l_service = null;

    public WEB3AdminEquityPTSCancelExecHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * (get入力画面)
     * 管理者・株式（PTS）出来取消サービスの取得に失敗しました。
     */
    public void testGetInputScreen_0001()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_service =
                (WEB3AdminEquityPTSCancelExecService)Services.getService(
                    WEB3AdminEquityPTSCancelExecService.class);

            Services.unregisterService(WEB3AdminEquityPTSCancelExecService.class);

            WEB3AdminEquityPTSCancelExecInputRequest l_request = new WEB3AdminEquityPTSCancelExecInputRequest();

            WEB3AdminEquityPTSCancelExecInputResponse l_response = l_handler.getInputScreen(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminEquityPTSCancelExecService.class, l_service);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get入力画面)<BR>
     * get入力画面の処理の実施に失敗しました。<BR>
     * excute() throw WEB3BaseException<BR>
     */
    public void testGetInputScreen_0002()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminEquityPTSCancelExecInputRequest l_request = new WEB3AdminEquityPTSCancelExecInputRequest();

            WEB3AdminEquityPTSCancelExecInputResponse l_response = l_handler.getInputScreen(l_request);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get入力画面)<BR>
     * get入力画面の処理の実施に失敗しました。<BR>
     * excute() throw WEB3BaseRuntimeException<BR>
     */
    public void testGetInputScreen_0003()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3BaseRuntimeException l_runtimeException =
                new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80008, "");

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSCancelExecServiceImpl",
                    "execute", new Class[]
                    { WEB3GenRequest.class },
                    l_runtimeException);

            WEB3AdminEquityPTSCancelExecInputRequest l_request = new WEB3AdminEquityPTSCancelExecInputRequest();

            WEB3AdminEquityPTSCancelExecInputResponse l_response = l_handler.getInputScreen(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80008, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get入力画面)<BR>
     * 正常終了<BR>
     */
    public void testGetInputScreen_0004()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminEquityPTSCancelExecInputResponse l_expectedResponse =
                new WEB3AdminEquityPTSCancelExecInputResponse();

            WEB3AdminEquityPTSOrderDetailUnit l_detailUnit = new WEB3AdminEquityPTSOrderDetailUnit();
            l_detailUnit.accountCode = "2512246";
            l_detailUnit.branchCode = "381";
            l_detailUnit.changeCancelDiv = "2";
            l_expectedResponse.orderDetail = l_detailUnit;

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSCancelExecServiceImpl",
                    "execute", new Class[]
                    { WEB3GenRequest.class },
                    l_expectedResponse);

            WEB3AdminEquityPTSCancelExecInputRequest l_request = new WEB3AdminEquityPTSCancelExecInputRequest();

            WEB3AdminEquityPTSCancelExecInputResponse l_response = l_handler.getInputScreen(l_request);

            assertEquals("2512246", l_response.orderDetail.accountCode);
            assertEquals("381", l_response.orderDetail.branchCode);
            assertEquals("2", l_response.orderDetail.changeCancelDiv);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate出来取消)
     * 管理者・株式（PTS）出来取消サービスの取得に失敗しました。
     */
    public void testValidateCancelExec_0001()
    {
        final String STR_METHOD_NAME = "testValidateCancelExec_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_service =
                (WEB3AdminEquityPTSCancelExecService)Services.getService(
                    WEB3AdminEquityPTSCancelExecService.class);

            Services.unregisterService(WEB3AdminEquityPTSCancelExecService.class);

            WEB3AdminEquityPTSCancelExecConfirmRequest l_request = new WEB3AdminEquityPTSCancelExecConfirmRequest();

            WEB3AdminEquityPTSCancelExecConfirmResponse l_response = l_handler.validateCancelExec(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminEquityPTSCancelExecService.class, l_service);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate出来取消)<BR>
     * validate出来取消の処理の実施に失敗しました。<BR>
     * excute() throw WEB3BaseException<BR>
     */
    public void testValidateCancelExec_0002()
    {
        final String STR_METHOD_NAME = "testValidateCancelExec_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3BusinessLayerException l_businessLayerException =
                new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00031, "");

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSCancelExecServiceImpl",
                    "execute", new Class[]
                    { WEB3GenRequest.class },
                    l_businessLayerException);

            WEB3AdminEquityPTSCancelExecConfirmRequest l_request = new WEB3AdminEquityPTSCancelExecConfirmRequest();

            WEB3AdminEquityPTSCancelExecConfirmResponse l_response = l_handler.validateCancelExec(l_request);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00031, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate出来取消)<BR>
     * validate出来取消の処理の実施に失敗しました。<BR>
     * excute() throw WEB3BaseRuntimeException<BR>
     */
    public void testValidateCancelExec_0003()
    {
        final String STR_METHOD_NAME = "testValidateCancelExec_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3BaseRuntimeException l_runtimeException =
                new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80008, "");

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSCancelExecServiceImpl",
                    "execute", new Class[]
                    { WEB3GenRequest.class },
                    l_runtimeException);

            WEB3AdminEquityPTSCancelExecConfirmRequest l_request = new WEB3AdminEquityPTSCancelExecConfirmRequest();

            WEB3AdminEquityPTSCancelExecConfirmResponse l_response = l_handler.validateCancelExec(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80008, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate出来取消)<BR>
     * 正常終了<BR>
     */
    public void testValidateCancelExec_0004()
    {
        final String STR_METHOD_NAME = "testValidateCancelExec_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminEquityPTSCancelExecConfirmResponse l_expectedResponse =
                new WEB3AdminEquityPTSCancelExecConfirmResponse();

            WEB3AdminEquityPTSOrderDetailUnit l_detailUnit = new WEB3AdminEquityPTSOrderDetailUnit();
            l_detailUnit.accountCode = "2512246";
            l_detailUnit.branchCode = "381";
            l_detailUnit.changeCancelDiv = "2";
            l_expectedResponse.orderDetail = l_detailUnit;

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSCancelExecServiceImpl",
                    "execute", new Class[]
                    { WEB3GenRequest.class },
                    l_expectedResponse);

            WEB3AdminEquityPTSCancelExecConfirmRequest l_request = new WEB3AdminEquityPTSCancelExecConfirmRequest();

            WEB3AdminEquityPTSCancelExecConfirmResponse l_response = l_handler.validateCancelExec(l_request);

            assertEquals("2512246", l_response.orderDetail.accountCode);
            assertEquals("381", l_response.orderDetail.branchCode);
            assertEquals("2", l_response.orderDetail.changeCancelDiv);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }


    /**
     * (submit出来取消)
     * 管理者・株式（PTS）出来取消サービスの取得に失敗しました。
     */
    public void testSubmitCancelExec_0001()
    {
        final String STR_METHOD_NAME = "testSubmitCancelExec_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_service =
                (WEB3AdminEquityPTSCancelExecService)Services.getService(
                    WEB3AdminEquityPTSCancelExecService.class);

            Services.unregisterService(WEB3AdminEquityPTSCancelExecService.class);

            WEB3AdminEquityPTSCancelExecCompleteRequest l_request = new WEB3AdminEquityPTSCancelExecCompleteRequest();

            WEB3AdminEquityPTSCancelExecCompleteResponse l_response = l_handler.submitCancelExec(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminEquityPTSCancelExecService.class, l_service);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit出来取消)<BR>
     * submit出来取消の処理の実施に失敗しました。<BR>
     * excute() throw WEB3BaseException<BR>
     */
    public void testSubmitCancelExec_0002()
    {
        final String STR_METHOD_NAME = "testSubmitCancelExec_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3BusinessLayerException l_businessLayerException =
                new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00031, "");

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSCancelExecServiceImpl",
                    "execute", new Class[]
                    { WEB3GenRequest.class },
                    l_businessLayerException);

            WEB3AdminEquityPTSCancelExecCompleteRequest l_request = new WEB3AdminEquityPTSCancelExecCompleteRequest();

            WEB3AdminEquityPTSCancelExecCompleteResponse l_response = l_handler.submitCancelExec(l_request);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00031, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit出来取消)<BR>
     * submit出来取消の処理の実施に失敗しました。<BR>
     * excute() throw WEB3BaseRuntimeException<BR>
     */
    public void testSubmitCancelExec_0003()
    {
        final String STR_METHOD_NAME = "testSubmitCancelExec_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3BaseRuntimeException l_runtimeException =
                new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80008, "");

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSCancelExecServiceImpl",
                    "execute", new Class[]
                    { WEB3GenRequest.class },
                    l_runtimeException);

            WEB3AdminEquityPTSCancelExecCompleteRequest l_request = new WEB3AdminEquityPTSCancelExecCompleteRequest();

            WEB3AdminEquityPTSCancelExecCompleteResponse l_response = l_handler.submitCancelExec(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80008, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit出来取消)<BR>
     * 正常終了<BR>
     */
    public void testSubmitCancelExec_0004()
    {
        final String STR_METHOD_NAME = "testSubmitCancelExec_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminEquityPTSCancelExecCompleteResponse l_expectedResponse =
                new WEB3AdminEquityPTSCancelExecCompleteResponse();

            WEB3AdminEquityPTSOrderDetailUnit l_detailUnit = new WEB3AdminEquityPTSOrderDetailUnit();
            l_detailUnit.accountCode = "2512246";
            l_detailUnit.branchCode = "381";
            l_detailUnit.changeCancelDiv = "2";
            l_expectedResponse.orderDetail = l_detailUnit;

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSCancelExecServiceImpl",
                    "execute", new Class[]
                    { WEB3GenRequest.class },
                    l_expectedResponse);

            WEB3AdminEquityPTSCancelExecCompleteRequest l_request = new WEB3AdminEquityPTSCancelExecCompleteRequest();

            WEB3AdminEquityPTSCancelExecCompleteResponse l_response = l_handler.submitCancelExec(l_request);

            assertEquals("2512246", l_response.orderDetail.accountCode);
            assertEquals("381", l_response.orderDetail.branchCode);
            assertEquals("2", l_response.orderDetail.changeCancelDiv);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@

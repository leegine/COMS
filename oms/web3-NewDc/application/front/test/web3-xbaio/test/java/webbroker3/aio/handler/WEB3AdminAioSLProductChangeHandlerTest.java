head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.25.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAioSLProductChangeHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3AdminAioSLProductChangeHandlerTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/25 トウ鋒鋼 (中訊) 新規作成
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.aio.message.WEB3AdminSLProductChangeCompleteRequest;
import webbroker3.aio.message.WEB3AdminSLProductChangeCompleteResponse;
import webbroker3.aio.message.WEB3AdminSLProductChangeConfirmRequest;
import webbroker3.aio.message.WEB3AdminSLProductChangeConfirmResponse;
import webbroker3.aio.message.WEB3AdminSLProductChangeInputRequest;
import webbroker3.aio.message.WEB3AdminSLProductChangeInputResponse;
import webbroker3.aio.message.WEB3SLProductInfoUnit;
import webbroker3.aio.service.delegate.WEB3AdminAioSLProductChangeService;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductChangeServiceImplForMock;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3ComplianceInfoUnit;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAioSLProductChangeHandlerTest extends TestBaseForMock
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLProductChangeHandlerTest.class);

    private WEB3AdminAioSLProductChangeHandler l_handler = null;
    WEB3AdminAioSLProductChangeService l_service = null;

    public WEB3AdminAioSLProductChangeHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_handler = new WEB3AdminAioSLProductChangeHandler();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        Services.overrideService(WEB3AdminAioSLProductChangeService.class,
            new WEB3AdminAioSLProductChangeServiceImplForMock());
    }

    protected void tearDown() throws Exception
    {
        l_handler = null;
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        super.tearDown();
    }

    /**
     * 担保銘柄変更サービスの取得に失敗しましたの場合
     * throw WEB3ErrorCatalog.SYSTEM_ERROR_80002
     */
    public void testGetSLProductChangeInput_case0001()
    {
        final String STR_METHOD_NAME = "testGetSLProductChangeInput_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_service = (WEB3AdminAioSLProductChangeService)Services.getService(
                WEB3AdminAioSLProductChangeService.class);
            Services.unregisterService(WEB3AdminAioSLProductChangeService.class);

            WEB3AdminSLProductChangeInputRequest l_request = new WEB3AdminSLProductChangeInputRequest();
            WEB3AdminSLProductChangeInputResponse l_response = l_handler.getSLProductChangeInput(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminAioSLProductChangeService.class, l_service);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * get担保銘柄変更入力処理が失敗しましたの場合
     * throw WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testGetSLProductChangeInput_case0002()
    {
        final String STR_METHOD_NAME = "testGetSLProductChangeInput_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_service = (WEB3AdminAioSLProductChangeService)Services.getService(
                WEB3AdminAioSLProductChangeService.class);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductChangeServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        "get担保銘柄変更入力処理が失敗しました。"));
            WEB3AdminSLProductChangeInputRequest l_request = new WEB3AdminSLProductChangeInputRequest();
            WEB3AdminSLProductChangeInputResponse l_response = l_handler.getSLProductChangeInput(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 正常通過
     *
     */
    public void testGetSLProductChangeInput_case0003()
    {
        final String STR_METHOD_NAME = "testGetSLProductChangeInput_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_service = (WEB3AdminAioSLProductChangeService)Services.getService(
                WEB3AdminAioSLProductChangeService.class);

            WEB3AdminSLProductChangeInputResponse l_expectedResponse = new WEB3AdminSLProductChangeInputResponse();
            l_expectedResponse.stockLoanProductInfo = new WEB3SLProductInfoUnit();
            l_expectedResponse.stockLoanProductInfo.productCode = "320";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductChangeServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_expectedResponse);
            WEB3AdminSLProductChangeInputRequest l_request = new WEB3AdminSLProductChangeInputRequest();
            WEB3AdminSLProductChangeInputResponse l_response = l_handler.getSLProductChangeInput(l_request);

            assertEquals("320", l_response.stockLoanProductInfo.productCode);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 担保銘柄変更サービスの取得に失敗しましたの場合
     * throw WEB3ErrorCatalog.SYSTEM_ERROR_80002
     */
    public void testValidateSLProductChange_case0001()
    {
        final String STR_METHOD_NAME = "testValidateSLProductChange_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_service = (WEB3AdminAioSLProductChangeService)Services.getService(
                WEB3AdminAioSLProductChangeService.class);
            Services.unregisterService(WEB3AdminAioSLProductChangeService.class);

            WEB3AdminSLProductChangeConfirmRequest l_request = new WEB3AdminSLProductChangeConfirmRequest();
            WEB3AdminSLProductChangeConfirmResponse l_response = l_handler.validateSLProductChange(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminAioSLProductChangeService.class, l_service);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate担保銘柄変更処理が失敗しましたの場合
     * throw WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testValidateSLProductChange_case0002()
    {
        final String STR_METHOD_NAME = "testValidateSLProductChange_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_service = (WEB3AdminAioSLProductChangeService)Services.getService(
                WEB3AdminAioSLProductChangeService.class);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductChangeServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        "validate担保銘柄変更処理が失敗しました。"));
            WEB3AdminSLProductChangeConfirmRequest l_request = new WEB3AdminSLProductChangeConfirmRequest();
            WEB3AdminSLProductChangeConfirmResponse l_response = l_handler.validateSLProductChange(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 正常通過
     *
     */
    public void testValidateSLProductChange_case0003()
    {
        final String STR_METHOD_NAME = "testValidateSLProductChange_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_service = (WEB3AdminAioSLProductChangeService)Services.getService(
                WEB3AdminAioSLProductChangeService.class);

            WEB3AdminSLProductChangeConfirmResponse l_expectedResponse = new WEB3AdminSLProductChangeConfirmResponse();
            l_expectedResponse.complianceInfo = new WEB3ComplianceInfoUnit();
            l_expectedResponse.complianceInfo.branchCode = "320";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductChangeServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_expectedResponse);
            WEB3AdminSLProductChangeConfirmRequest l_request = new WEB3AdminSLProductChangeConfirmRequest();
            WEB3AdminSLProductChangeConfirmResponse l_response = l_handler.validateSLProductChange(l_request);

            assertEquals("320", l_response.complianceInfo.branchCode);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 担保銘柄変更サービスの取得に失敗しましたの場合
     * throw WEB3ErrorCatalog.SYSTEM_ERROR_80002
     */
    public void testSubmitSLProductChange_case0001()
    {
        final String STR_METHOD_NAME = "testSubmitSLProductChange_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_service = (WEB3AdminAioSLProductChangeService)Services.getService(
                WEB3AdminAioSLProductChangeService.class);
            Services.unregisterService(WEB3AdminAioSLProductChangeService.class);

            WEB3AdminSLProductChangeCompleteRequest l_request = new WEB3AdminSLProductChangeCompleteRequest();
            WEB3AdminSLProductChangeCompleteResponse l_response = l_handler.submitSLProductChange(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminAioSLProductChangeService.class, l_service);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * submit担保銘柄変更処理が失敗しましたの場合
     * throw WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testSubmitSLProductChange_case0002()
    {
        final String STR_METHOD_NAME = "testSubmitSLProductChange_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_service = (WEB3AdminAioSLProductChangeService)Services.getService(
                WEB3AdminAioSLProductChangeService.class);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductChangeServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        "submit担保銘柄変更処理が失敗しました。"));
            WEB3AdminSLProductChangeCompleteRequest l_request = new WEB3AdminSLProductChangeCompleteRequest();
            WEB3AdminSLProductChangeCompleteResponse l_response = l_handler.submitSLProductChange(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 正常通過
     *
     */
    public void testSubmitSLProductChange_case0003()
    {
        final String STR_METHOD_NAME = "testSubmitSLProductChange_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_service = (WEB3AdminAioSLProductChangeService)Services.getService(
                WEB3AdminAioSLProductChangeService.class);

            WEB3AdminSLProductChangeCompleteResponse l_expectedResponse = new WEB3AdminSLProductChangeCompleteResponse();
            l_expectedResponse.complianceInfo = new WEB3ComplianceInfoUnit();
            l_expectedResponse.complianceInfo.branchCode = "320";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductChangeServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_expectedResponse);
            WEB3AdminSLProductChangeCompleteRequest l_request = new WEB3AdminSLProductChangeCompleteRequest();
            WEB3AdminSLProductChangeCompleteResponse l_response = l_handler.submitSLProductChange(l_request);

            assertEquals("320", l_response.complianceInfo.branchCode);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@

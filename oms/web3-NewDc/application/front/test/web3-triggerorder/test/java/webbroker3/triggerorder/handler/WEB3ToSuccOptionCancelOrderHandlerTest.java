head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.32.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccOptionCancelOrderHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 
Author Name      : Daiwa Institute of Research
Revesion History : 
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.message.WEB3SuccOptionsCancelCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCancelCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsCancelConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCancelConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionCancelOrderService;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionCancelOrderServiceImplForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccOptionCancelOrderHandlerTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionCancelOrderHandlerTest.class);

    WEB3ToSuccOptionCancelOrderHandler l_handler = null;

    public WEB3ToSuccOptionCancelOrderHandlerTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        l_handler = new WEB3ToSuccOptionCancelOrderHandler();
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        l_handler = null;
        super.tearDown();
    }

    /**
     * Exception
     *
     */
    public void test_confirmCancel_0001()
    {
        final String STR_METHOD_NAME = "test_confirmCancel_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);


        WEB3SuccOptionsCancelConfirmRequest l_request =
            new WEB3SuccOptionsCancelConfirmRequest();

        WEB3ToSuccOptionCancelOrderService l_service =
            new WEB3ToSuccOptionCancelOrderServiceImplForMock();
        try
        {
            Services.unregisterService(WEB3ToSuccOptionCancelOrderService.class);

            WEB3SuccOptionsCancelConfirmResponse l_response =
                l_handler.confirmCancel(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3ToSuccOptionCancelOrderService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * WEB3SystemLayerException
     *
     */
    public void test_confirmCancel_0002()
    {
        final String STR_METHOD_NAME = "test_confirmCancel_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);

        WEB3SuccOptionsCancelConfirmRequest l_request =
            new WEB3SuccOptionsCancelConfirmRequest();

        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionCancelOrderServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                            "パラメータ値不正。"));
            WEB3SuccOptionsCancelConfirmResponse l_response =
                l_handler.confirmCancel(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * WEB3BaseRuntimeException
     *
     */
    public void test_confirmCancel_0003()
    {
        final String STR_METHOD_NAME = "test_confirmCancel_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);

        WEB3SuccOptionsCancelConfirmRequest l_request =
            new WEB3SuccOptionsCancelConfirmRequest();

        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionCancelOrderServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                            "パラメータ値不正。"));

            WEB3SuccOptionsCancelConfirmResponse l_response =
                l_handler.confirmCancel(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_confirmCancel_0004()
    {
        final String STR_METHOD_NAME = "test_confirmCancel_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);

        WEB3SuccOptionsCancelConfirmRequest l_request =
            new WEB3SuccOptionsCancelConfirmRequest();

        try
        {
            WEB3SuccOptionsCancelConfirmResponse l_confirmResponse = new WEB3SuccOptionsCancelConfirmResponse();
            l_confirmResponse.targetProductCode = "15";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionCancelOrderServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_confirmResponse);

            WEB3SuccOptionsCancelConfirmResponse l_response =
                l_handler.confirmCancel(l_request);

            assertEquals("15", "" + l_response.targetProductCode);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * Exception
     *
     */
    public void test_completeCancel_0001()
    {
        final String STR_METHOD_NAME = "test_completeCancel_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);


        WEB3SuccOptionsCancelCompleteRequest l_request =
            new WEB3SuccOptionsCancelCompleteRequest();

        WEB3ToSuccOptionCancelOrderService l_service =
            new WEB3ToSuccOptionCancelOrderServiceImplForMock();
        try
        {
            Services.unregisterService(WEB3ToSuccOptionCancelOrderService.class);

            WEB3SuccOptionsCancelCompleteResponse l_response =
                l_handler.completeCancel(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3ToSuccOptionCancelOrderService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * WEB3SystemLayerException
     *
     */
    public void test_completeCancel_0002()
    {
        final String STR_METHOD_NAME = "test_completeCancel_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);

        WEB3SuccOptionsCancelCompleteRequest l_request =
            new WEB3SuccOptionsCancelCompleteRequest();

        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionCancelOrderServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                            "パラメータ値不正。"));
            WEB3SuccOptionsCancelCompleteResponse l_response =
                l_handler.completeCancel(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * WEB3BaseRuntimeException
     *
     */
    public void test_completeCancel_0003()
    {
        final String STR_METHOD_NAME = "test_completeCancel_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);

        WEB3SuccOptionsCancelCompleteRequest l_request =
            new WEB3SuccOptionsCancelCompleteRequest();

        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionCancelOrderServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                            "パラメータ値不正。"));

            WEB3SuccOptionsCancelCompleteResponse l_response =
                l_handler.completeCancel(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_completeCancel_0004()
    {
        final String STR_METHOD_NAME = "test_completeCancel_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);

        WEB3SuccOptionsCancelCompleteRequest l_request =
            new WEB3SuccOptionsCancelCompleteRequest();

        try
        {
            WEB3SuccOptionsCancelCompleteResponse l_confirmResponse = new WEB3SuccOptionsCancelCompleteResponse();
            l_confirmResponse.orderActionId = "16";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionCancelOrderServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_confirmResponse);

            WEB3SuccOptionsCancelCompleteResponse l_response =
                l_handler.completeCancel(l_request);

            assertEquals("16", "" + l_response.orderActionId);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@

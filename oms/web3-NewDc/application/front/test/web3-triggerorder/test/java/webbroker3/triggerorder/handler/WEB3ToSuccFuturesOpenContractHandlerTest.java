head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.31.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccFuturesOpenContractHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）先物新規建注文ハンドラ(WEB3ToSuccFuturesOpenContractHandlerTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/25 孟亞南(中訊) 新規作成モデル257
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesOpenContractService;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesOpenContractServiceImplForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccFuturesOpenContractHandlerTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesOpenContractHandlerTest.class);

    WEB3ToSuccFuturesOpenContractHandler l_handler = null;
    
    public WEB3ToSuccFuturesOpenContractHandlerTest(String name)
    {
        
        super(name);
    }

    protected void setUp() throws Exception
    {
        l_handler = new WEB3ToSuccFuturesOpenContractHandler();
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
    public void test_confirmOrder_0001()
    {
        final String STR_METHOD_NAME = "test_confirmOrder_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);


        WEB3SuccFuturesOpenConfirmRequest l_request =
            new WEB3SuccFuturesOpenConfirmRequest();

        WEB3ToSuccFuturesOpenContractService l_service =
            new WEB3ToSuccFuturesOpenContractServiceImplForMock();
        try
        {
            Services.unregisterService(WEB3ToSuccFuturesOpenContractService.class);

            WEB3SuccFuturesOpenConfirmResponse l_response =
                l_handler.confirmOrder(l_request);
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
            Services.registerService(WEB3ToSuccFuturesOpenContractService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * WEB3SystemLayerException
     *
     */
    public void test_confirmOrder_0002()
    {
        final String STR_METHOD_NAME = "test_confirmOrder_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);

        WEB3SuccFuturesOpenConfirmRequest l_request =
            new WEB3SuccFuturesOpenConfirmRequest();

        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesOpenContractServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                            "パラメータ値不正。"));
            WEB3SuccFuturesOpenConfirmResponse l_response =
                l_handler.confirmOrder(l_request);

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
    public void test_confirmOrder_0003()
    {
        final String STR_METHOD_NAME = "test_confirmOrder_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);

        WEB3SuccFuturesOpenConfirmRequest l_request =
            new WEB3SuccFuturesOpenConfirmRequest();

        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesOpenContractServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                            "パラメータ値不正。"));

            WEB3SuccFuturesOpenConfirmResponse l_response =
                l_handler.confirmOrder(l_request);

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
    
    public void test_confirmOrder_0004()
    {
        final String STR_METHOD_NAME = "test_confirmOrder_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);

        WEB3SuccFuturesOpenConfirmRequest l_request =
            new WEB3SuccFuturesOpenConfirmRequest();

        try
        {
            WEB3SuccFuturesOpenConfirmResponse l_confirmResponse = new WEB3SuccFuturesOpenConfirmResponse(l_request);
            l_confirmResponse.afterAdjustmentPrice = "11";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesOpenContractServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_confirmResponse);

            WEB3SuccFuturesOpenConfirmResponse l_response =
                l_handler.confirmOrder(l_request);

            assertEquals("11", "" + l_response.afterAdjustmentPrice);
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
    public void test_completeOrder_0001()
    {
        final String STR_METHOD_NAME = "test_completeOrder_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);


        WEB3SuccFuturesOpenCompleteRequest l_request =
            new WEB3SuccFuturesOpenCompleteRequest();

        WEB3ToSuccFuturesOpenContractService l_service =
            new WEB3ToSuccFuturesOpenContractServiceImplForMock();
        try
        {
            Services.unregisterService(WEB3ToSuccFuturesOpenContractService.class);

            WEB3SuccFuturesOpenCompleteResponse l_response =
                l_handler.completeOrder(l_request);
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
            Services.registerService(WEB3ToSuccFuturesOpenContractService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * WEB3SystemLayerException
     *
     */
    public void test_completeOrder_0002()
    {
        final String STR_METHOD_NAME = "test_completeOrder_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);

        WEB3SuccFuturesOpenCompleteRequest l_request =
            new WEB3SuccFuturesOpenCompleteRequest();

        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesOpenContractServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                            "パラメータ値不正。"));
            WEB3SuccFuturesOpenCompleteResponse l_response =
                l_handler.completeOrder(l_request);

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
    public void test_completeOrder_0003()
    {
        final String STR_METHOD_NAME = "test_completeOrder_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);

        WEB3SuccFuturesOpenCompleteRequest l_request =
            new WEB3SuccFuturesOpenCompleteRequest();

        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesOpenContractServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                            "パラメータ値不正。"));

            WEB3SuccFuturesOpenCompleteResponse l_response =
                l_handler.completeOrder(l_request);

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
    
    public void test_completeOrder_0004()
    {
        final String STR_METHOD_NAME = "test_completeOrder_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);

        WEB3SuccFuturesOpenCompleteRequest l_request =
            new WEB3SuccFuturesOpenCompleteRequest();

        try
        {
            WEB3SuccFuturesOpenCompleteResponse l_completeResponse = new WEB3SuccFuturesOpenCompleteResponse(l_request);
            l_completeResponse.orderActionId = "12";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesOpenContractServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_completeResponse);

            WEB3SuccFuturesOpenCompleteResponse l_response =
                l_handler.completeOrder(l_request);

            assertEquals("12", "" + l_response.orderActionId);
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

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.31.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccOptionOpenContractOrderHandlerTest.java;


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
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionOpenContractOrderService;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionOpenContractOrderServiceImplForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccOptionOpenContractOrderHandlerTest extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionOpenContractOrderHandlerTest.class);

    WEB3ToSuccOptionOpenContractOrderHandler l_handler = null;
    
    public WEB3ToSuccOptionOpenContractOrderHandlerTest(String name)
    {
        
        super(name);
    }

    protected void setUp() throws Exception
    {
        l_handler = new WEB3ToSuccOptionOpenContractOrderHandler();
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


        WEB3SuccOptionsOpenConfirmRequest l_request =
            new WEB3SuccOptionsOpenConfirmRequest();

        WEB3ToSuccOptionOpenContractOrderService l_service =
            new WEB3ToSuccOptionOpenContractOrderServiceImplForMock();
        try
        {
            Services.unregisterService(WEB3ToSuccOptionOpenContractOrderService.class);

            WEB3SuccOptionsOpenConfirmResponse l_response =
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
            Services.registerService(WEB3ToSuccOptionOpenContractOrderService.class, l_service);
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

        WEB3SuccOptionsOpenConfirmRequest l_request =
            new WEB3SuccOptionsOpenConfirmRequest();

        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionOpenContractOrderServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                            "パラメータ値不正。"));
            WEB3SuccOptionsOpenConfirmResponse l_response =
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

        WEB3SuccOptionsOpenConfirmRequest l_request =
            new WEB3SuccOptionsOpenConfirmRequest();

        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionOpenContractOrderServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                            "パラメータ値不正。"));

            WEB3SuccOptionsOpenConfirmResponse l_response =
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

        WEB3SuccOptionsOpenConfirmRequest l_request =
            new WEB3SuccOptionsOpenConfirmRequest();

        try
        {
            WEB3SuccOptionsOpenConfirmResponse l_confirmResponse = new WEB3SuccOptionsOpenConfirmResponse(l_request);
            l_confirmResponse.afterAdjustmentPrice = "11";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionOpenContractOrderServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_confirmResponse);

            WEB3SuccOptionsOpenConfirmResponse l_response =
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


        WEB3SuccOptionsOpenCompleteRequest l_request =
            new WEB3SuccOptionsOpenCompleteRequest();

        WEB3ToSuccOptionOpenContractOrderService l_service =
            new WEB3ToSuccOptionOpenContractOrderServiceImplForMock();
        try
        {
            Services.unregisterService(WEB3ToSuccOptionOpenContractOrderService.class);

            WEB3SuccOptionsOpenCompleteResponse l_response =
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
            Services.registerService(WEB3ToSuccOptionOpenContractOrderService.class, l_service);
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

        WEB3SuccOptionsOpenCompleteRequest l_request =
            new WEB3SuccOptionsOpenCompleteRequest();

        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionOpenContractOrderServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                            "パラメータ値不正。"));
            WEB3SuccOptionsOpenCompleteResponse l_response =
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

        WEB3SuccOptionsOpenCompleteRequest l_request =
            new WEB3SuccOptionsOpenCompleteRequest();

        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionOpenContractOrderServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                            "パラメータ値不正。"));

            WEB3SuccOptionsOpenCompleteResponse l_response =
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

        WEB3SuccOptionsOpenCompleteRequest l_request =
            new WEB3SuccOptionsOpenCompleteRequest();

        try
        {
            WEB3SuccOptionsOpenCompleteResponse l_completeResponse = new WEB3SuccOptionsOpenCompleteResponse(l_request);
            l_completeResponse.orderActionId = "12";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionOpenContractOrderServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_completeResponse);

            WEB3SuccOptionsOpenCompleteResponse l_response =
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

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.32.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccOptionSettleContractHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3ToSuccOptionSettleContractHandlerTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/21 トウ鋒鋼 (中訊) 新規作成
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionSettleContractOrderService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccOptionSettleContractHandlerTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionSettleContractHandlerTest.class);
    
    WEB3ToSuccOptionSettleContractOrderService l_service = null;

    public WEB3ToSuccOptionSettleContractHandlerTest(String arg0)
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

    public void testConfirmSettleContract_0001()
    {
        final String STR_METHOD_NAME = "testConfirmSettleContract_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_service =
                (WEB3ToSuccOptionSettleContractOrderService)Services.getService(
                    WEB3ToSuccOptionSettleContractOrderService.class);
            
            Services.unregisterService(WEB3ToSuccOptionSettleContractOrderService.class);
            
            WEB3ToSuccOptionSettleContractHandler l_handler =
                new WEB3ToSuccOptionSettleContractHandler();
            WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
            WEB3SuccOptionsCloseConfirmResponse l_response = l_handler.confirmSettleContract(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3ToSuccOptionSettleContractOrderService.class, l_service);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testConfirmSettleContract_0002()
    {
        final String STR_METHOD_NAME = "testConfirmSettleContract_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractOrderServiceImpl",
                "execute", new Class[]
                {WEB3GenRequest.class},
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    "パラメータ値不正。"));
            
            WEB3ToSuccOptionSettleContractHandler l_handler =
                new WEB3ToSuccOptionSettleContractHandler();
            WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
            WEB3SuccOptionsCloseConfirmResponse l_response = l_handler.confirmSettleContract(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testConfirmSettleContract_0003()
    {
        final String STR_METHOD_NAME = "testConfirmSettleContract_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractOrderServiceImpl",
                "execute", new Class[]
                {WEB3GenRequest.class},
                new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    "パラメータ値不正。"));
            
            WEB3ToSuccOptionSettleContractHandler l_handler =
                new WEB3ToSuccOptionSettleContractHandler();
            WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
            WEB3SuccOptionsCloseConfirmResponse l_response = l_handler.confirmSettleContract(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testConfirmSettleContract_0004()
    {
        final String STR_METHOD_NAME = "testConfirmSettleContract_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3SuccOptionsCloseConfirmResponse l_expectedResponse = new WEB3SuccOptionsCloseConfirmResponse();
            l_expectedResponse.afterAdjustmentPrice = "55";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractOrderServiceImpl",
                "execute", new Class[]
                {WEB3GenRequest.class},
                l_expectedResponse);
            
            WEB3ToSuccOptionSettleContractHandler l_handler =
                new WEB3ToSuccOptionSettleContractHandler();
            WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
            WEB3SuccOptionsCloseConfirmResponse l_response = l_handler.confirmSettleContract(l_request);
            assertEquals("55", l_response.afterAdjustmentPrice);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCompleteSettleContract_0001()
    {
        final String STR_METHOD_NAME = "testCompleteSettleContract_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_service =
                (WEB3ToSuccOptionSettleContractOrderService)Services.getService(
                    WEB3ToSuccOptionSettleContractOrderService.class);
            
            Services.unregisterService(WEB3ToSuccOptionSettleContractOrderService.class);
            
            WEB3ToSuccOptionSettleContractHandler l_handler =
                new WEB3ToSuccOptionSettleContractHandler();
            WEB3SuccOptionsCloseCompleteRequest l_request = new WEB3SuccOptionsCloseCompleteRequest();
            WEB3SuccOptionsCloseCompleteResponse l_response = l_handler.completeSettleContract(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3ToSuccOptionSettleContractOrderService.class, l_service);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCompleteSettleContract_0002()
    {
        final String STR_METHOD_NAME = "testCompleteSettleContract_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractOrderServiceImpl",
                "execute", new Class[]
                {WEB3GenRequest.class},
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    "パラメータ値不正。"));
            
            WEB3ToSuccOptionSettleContractHandler l_handler =
                new WEB3ToSuccOptionSettleContractHandler();
            WEB3SuccOptionsCloseCompleteRequest l_request = new WEB3SuccOptionsCloseCompleteRequest();
            WEB3SuccOptionsCloseCompleteResponse l_response = l_handler.completeSettleContract(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCompleteSettleContract_0003()
    {
        final String STR_METHOD_NAME = "testCompleteSettleContract_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractOrderServiceImpl",
                "execute", new Class[]
                {WEB3GenRequest.class},
                new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    "パラメータ値不正。"));
            
            WEB3ToSuccOptionSettleContractHandler l_handler =
                new WEB3ToSuccOptionSettleContractHandler();
            WEB3SuccOptionsCloseCompleteRequest l_request = new WEB3SuccOptionsCloseCompleteRequest();
            WEB3SuccOptionsCloseCompleteResponse l_response = l_handler.completeSettleContract(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCompleteSettleContract_0004()
    {
        final String STR_METHOD_NAME = "testCompleteSettleContract_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3SuccOptionsCloseCompleteResponse l_expectedResponse = new WEB3SuccOptionsCloseCompleteResponse();
            l_expectedResponse.lastUpdatedTimestamp = WEB3DateUtility.getDate("20080326", "yyyyMMdd");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractOrderServiceImpl",
                "execute", new Class[]
                {WEB3GenRequest.class},
                l_expectedResponse);
            
            WEB3ToSuccOptionSettleContractHandler l_handler =
                new WEB3ToSuccOptionSettleContractHandler();
            WEB3SuccOptionsCloseCompleteRequest l_request = new WEB3SuccOptionsCloseCompleteRequest();
            WEB3SuccOptionsCloseCompleteResponse l_response = l_handler.completeSettleContract(l_request);
            assertEquals(WEB3DateUtility.getDate("20080326", "yyyyMMdd"), l_response.lastUpdatedTimestamp);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@

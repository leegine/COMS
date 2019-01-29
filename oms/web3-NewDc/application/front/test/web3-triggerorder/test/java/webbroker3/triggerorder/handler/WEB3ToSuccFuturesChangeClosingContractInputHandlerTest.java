head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.31.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccFuturesChangeClosingContractInputHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3ToSuccFuturesChangeClosingContractInputHandlerTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/27 トウ鋒鋼 (中訊) 新規作成
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeClosingContractInputService;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccFuturesChangeClosingContractInputHandlerTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesChangeClosingContractInputHandlerTest.class);
    
    WEB3ToSuccFuturesChangeClosingContractInputService l_service = null;

    public WEB3ToSuccFuturesChangeClosingContractInputHandlerTest(String arg0)
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

    public void testCloseChangeInputRequest_0001()
    {
        final String STR_METHOD_NAME = "testCloseChangeInputRequest_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_service =
                (WEB3ToSuccFuturesChangeClosingContractInputService)Services.getService(
                    WEB3ToSuccFuturesChangeClosingContractInputService.class);
            
            Services.unregisterService(WEB3ToSuccFuturesChangeClosingContractInputService.class);
            
            WEB3ToSuccFuturesChangeClosingContractInputHandler l_handler =
                new WEB3ToSuccFuturesChangeClosingContractInputHandler();
            
            WEB3SuccFuturesCloseChangeInputRequest l_request = new WEB3SuccFuturesCloseChangeInputRequest();
            
            WEB3SuccFuturesCloseChangeInputResponse l_response = l_handler.closeChangeInputRequest(l_request);
            
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
            Services.registerService(WEB3ToSuccFuturesChangeClosingContractInputService.class, l_service);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCloseChangeInputRequest_0002()
    {
        final String STR_METHOD_NAME = "testCloseChangeInputRequest_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeClosingContractInputServiceImpl",
                    "execute", new Class[]
                    {WEB3GenRequest.class},
                    new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                            "パラメータ値不正。"));
            
            WEB3ToSuccFuturesChangeClosingContractInputHandler l_handler =
                new WEB3ToSuccFuturesChangeClosingContractInputHandler();
            
            WEB3SuccFuturesCloseChangeInputRequest l_request = new WEB3SuccFuturesCloseChangeInputRequest();
            
            WEB3SuccFuturesCloseChangeInputResponse l_response = l_handler.closeChangeInputRequest(l_request);
            
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
    
    public void testCloseChangeInputRequest_0003()
    {
        final String STR_METHOD_NAME = "testCloseChangeInputRequest_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeClosingContractInputServiceImpl",
                    "execute", new Class[]
                    {WEB3GenRequest.class},
                    new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                            "パラメータ値不正。"));
            
            WEB3ToSuccFuturesChangeClosingContractInputHandler l_handler =
                new WEB3ToSuccFuturesChangeClosingContractInputHandler();
            
            WEB3SuccFuturesCloseChangeInputRequest l_request = new WEB3SuccFuturesCloseChangeInputRequest();
            
            WEB3SuccFuturesCloseChangeInputResponse l_response = l_handler.closeChangeInputRequest(l_request);
            
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
    
    public void testCloseChangeInputRequest_0004()
    {
        final String STR_METHOD_NAME = "testCloseChangeInputRequest_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3SuccFuturesCloseChangeInputResponse l_expectedResponse = new WEB3SuccFuturesCloseChangeInputResponse();
            l_expectedResponse.closingOrder = "0";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeClosingContractInputServiceImpl",
                    "execute", new Class[]
                    {WEB3GenRequest.class},
                    l_expectedResponse);
            
            WEB3ToSuccFuturesChangeClosingContractInputHandler l_handler =
                new WEB3ToSuccFuturesChangeClosingContractInputHandler();
            
            WEB3SuccFuturesCloseChangeInputRequest l_request = new WEB3SuccFuturesCloseChangeInputRequest();
            
            WEB3SuccFuturesCloseChangeInputResponse l_response = l_handler.closeChangeInputRequest(l_request);
            
            assertEquals("0", l_response.closingOrder);
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

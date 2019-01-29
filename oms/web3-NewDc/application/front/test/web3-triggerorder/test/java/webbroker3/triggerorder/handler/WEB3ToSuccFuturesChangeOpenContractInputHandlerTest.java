head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.32.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccFuturesChangeOpenContractInputHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3ToSuccFuturesChangeOpenContractInputHandlerTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/28 ���n�i���u�j�V�K�쐬
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenChangeInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeOpenContractInputService;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccFuturesChangeOpenContractInputHandlerTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesChangeOpenContractInputHandlerTest.class);

    public WEB3ToSuccFuturesChangeOpenContractInputHandlerTest(String arg0)
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

    /**
     * �i�A���j�����w���敨�����V�K�����̓T�[�r�X���擾�Ɏ��s���܂����B
     */
    public void testChangeOpenContractInputRequest_C0001()
    {
        String STR_METHOD_NAME = "testChangeOpenContractInputRequest_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccFuturesChangeOpenContractInputService l_service = null;
        try
        {
            l_service = (WEB3ToSuccFuturesChangeOpenContractInputService)Services.getService(
                WEB3ToSuccFuturesChangeOpenContractInputService.class);
            
            Services.unregisterService(WEB3ToSuccFuturesChangeOpenContractInputService.class);
            
            WEB3ToSuccFuturesChangeOpenContractInputHandler l_handler = 
                new WEB3ToSuccFuturesChangeOpenContractInputHandler();
            
            WEB3SuccFuturesOpenChangeInputRequest l_request =
                new WEB3SuccFuturesOpenChangeInputRequest();
            
            WEB3SuccFuturesOpenChangeInputResponse l_response =
                l_handler.changeOpenContractInputRequest(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3ToSuccFuturesChangeOpenContractInputService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �i�A���j�����w���敨�����V�K�����͕\�����������s���܂����B
     * 
     * excute() throws WEB3BaseException
     */
    public void testChangeOpenContractInputRequest_C0002()
    {
        String STR_METHOD_NAME = "testChangeOpenContractInputRequest_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3ToSuccFuturesChangeOpenContractInputHandler l_handler = 
                new WEB3ToSuccFuturesChangeOpenContractInputHandler();
            
            WEB3SuccFuturesOpenChangeInputRequest l_request =
                new WEB3SuccFuturesOpenChangeInputRequest();
            
            WEB3BaseException l_exception =
                new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00600, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeOpenContractInputServiceImpl",
                    "execute", new Class[]
                    { WEB3GenRequest.class },
                    l_exception);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_handler.changeOpenContractInputRequest(l_request).errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �i�A���j�����w���敨�����V�K�����͕\�����������s���܂����B
     * 
     * excute() throws WEB3BaseRuntimeException
     */
    public void testChangeOpenContractInputRequest_C0003()
    {
        String STR_METHOD_NAME = "testChangeOpenContractInputRequest_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3ToSuccFuturesChangeOpenContractInputHandler l_handler = 
                new WEB3ToSuccFuturesChangeOpenContractInputHandler();
            
            WEB3SuccFuturesOpenChangeInputRequest l_request =
                new WEB3SuccFuturesOpenChangeInputRequest();
            
            WEB3BaseRuntimeException l_exception =
                new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_00600, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeOpenContractInputServiceImpl",
                "execute", new Class[]
                { WEB3GenRequest.class },
                l_exception);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_handler.changeOpenContractInputRequest(l_request).errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ����I��
     */
    public void testChangeOpenContractInputRequest_C0004()
    {
        String STR_METHOD_NAME = "testChangeOpenContractInputRequest_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3ToSuccFuturesChangeOpenContractInputHandler l_handler = 
                new WEB3ToSuccFuturesChangeOpenContractInputHandler();
            
            WEB3SuccFuturesOpenChangeInputRequest l_request =
                new WEB3SuccFuturesOpenChangeInputRequest();
            
            WEB3SuccFuturesOpenChangeInputResponse l_response =
                new WEB3SuccFuturesOpenChangeInputResponse();
            l_response.currentPrice = "1";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeOpenContractInputServiceImpl",
                "execute", new Class[]
                { WEB3GenRequest.class },
                l_response);
            
            assertEquals("1", l_handler.changeOpenContractInputRequest(l_request).currentPrice);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@

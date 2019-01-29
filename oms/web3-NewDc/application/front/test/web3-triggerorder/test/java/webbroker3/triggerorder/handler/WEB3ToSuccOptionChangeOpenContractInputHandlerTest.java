head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.32.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccOptionChangeOpenContractInputHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ToSuccOptionChangeOpenContractInputHandlerTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/21 于瀟（中訊）新規作成
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeOpenContractInputService;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccOptionChangeOpenContractInputHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionChangeOpenContractInputHandlerTest.class);
    
    public WEB3ToSuccOptionChangeOpenContractInputHandlerTest(String arg0)
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
     * （連続）OP訂正新規建入力サービスを取得に失敗しました。
     */
    public void testChangeOpenContractInputRequest_C0001()
    {
        String STR_METHOD_NAME = "testChangeOpenContractInputRequest()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionChangeOpenContractInputService l_service = null;
        try
        {
            l_service = (WEB3ToSuccOptionChangeOpenContractInputService)Services.getService(
                WEB3ToSuccOptionChangeOpenContractInputService.class);
            
            Services.unregisterService(WEB3ToSuccOptionChangeOpenContractInputService.class);
            
            WEB3ToSuccOptionChangeOpenContractInputHandler l_handler = 
                new WEB3ToSuccOptionChangeOpenContractInputHandler();
            
            WEB3SuccOptionsOpenChangeInputRequest l_request =
                new WEB3SuccOptionsOpenChangeInputRequest();
            
            WEB3SuccOptionsOpenChangeInputResponse l_response =
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
            Services.registerService(WEB3ToSuccOptionChangeOpenContractInputService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * （連続）OP訂正新規建入力表示処理が失敗しました。
     * 
     * excute() throws WEB3BaseException
     */
    public void testChangeOpenContractInputRequest_C0002()
    {
        String STR_METHOD_NAME = "testChangeOpenContractInputRequest_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3ToSuccOptionChangeOpenContractInputHandler l_handler = 
                new WEB3ToSuccOptionChangeOpenContractInputHandler();
            
            WEB3SuccOptionsOpenChangeInputRequest l_request =
                new WEB3SuccOptionsOpenChangeInputRequest();
            
            WEB3BaseException l_exception =
                new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00600, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeOpenContractInputServiceImpl",
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
     * （連続）OP訂正新規建入力表示処理が失敗しました。
     * 
     * excute() throws WEB3BaseRuntimeException
     */
    public void testChangeOpenContractInputRequest_C0003()
    {
        String STR_METHOD_NAME = "testChangeOpenContractInputRequest_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3ToSuccOptionChangeOpenContractInputHandler l_handler = 
                new WEB3ToSuccOptionChangeOpenContractInputHandler();
            
            WEB3SuccOptionsOpenChangeInputRequest l_request =
                new WEB3SuccOptionsOpenChangeInputRequest();
            
            WEB3BaseRuntimeException l_exception =
                new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_00600, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeOpenContractInputServiceImpl",
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
     * 正常終了
     */
    public void testChangeOpenContractInputRequest_C0004()
    {
        String STR_METHOD_NAME = "testChangeOpenContractInputRequest_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3ToSuccOptionChangeOpenContractInputHandler l_handler = 
                new WEB3ToSuccOptionChangeOpenContractInputHandler();
            
            WEB3SuccOptionsOpenChangeInputRequest l_request =
                new WEB3SuccOptionsOpenChangeInputRequest();
            
            WEB3SuccOptionsOpenChangeInputResponse l_response =
                new WEB3SuccOptionsOpenChangeInputResponse();
            l_response.currentPrice = "1";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeOpenContractInputServiceImpl",
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

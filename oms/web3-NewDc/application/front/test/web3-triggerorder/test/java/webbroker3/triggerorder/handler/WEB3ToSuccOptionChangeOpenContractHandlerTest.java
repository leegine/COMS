head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.31.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccOptionChangeOpenContractHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ToSuccOptionChangeOpenContractHandlerTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/18 于瀟（中訊）新規作成
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeOpenContractService;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccOptionChangeOpenContractHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionChangeOpenContractHandlerTest.class);

    public WEB3ToSuccOptionChangeOpenContractHandlerTest(String arg0)
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
     * （連続）オプション訂正新規建サービスを取得に失敗しました。
     */
    public void testConfirmChangeOpenContract_C0001()
    {
        String STR_METHOD_NAME = "testConfirmChangeOpenContract_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionChangeOpenContractService l_service = null;
        try
        {
            l_service = (WEB3ToSuccOptionChangeOpenContractService)Services.getService(
                    WEB3ToSuccOptionChangeOpenContractService.class);
            
            Services.unregisterService(WEB3ToSuccOptionChangeOpenContractService.class);
            
            WEB3ToSuccOptionChangeOpenContractHandler l_handler = 
                new WEB3ToSuccOptionChangeOpenContractHandler();
            
            WEB3SuccOptionsOpenChangeConfirmRequest l_request =
                new WEB3SuccOptionsOpenChangeConfirmRequest();
            
            WEB3SuccOptionsOpenChangeConfirmResponse l_response =
                l_handler.confirmChangeOpenContract(l_request);
            
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
            Services.registerService(WEB3ToSuccOptionChangeOpenContractService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * （連続）オプション訂正新規建サービスを取得に失敗しました。
     * 
     * excute() throws WEB3BaseException
     */
    public void testConfirmChangeOpenContract_C0002()
    {
        String STR_METHOD_NAME = "testConfirmChangeOpenContract_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3ToSuccOptionChangeOpenContractHandler l_handler = 
                new WEB3ToSuccOptionChangeOpenContractHandler();
            
            WEB3SuccOptionsOpenChangeConfirmRequest l_request =
                new WEB3SuccOptionsOpenChangeConfirmRequest();
            
            WEB3BaseException l_exception =
                new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00600, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeOpenContractServiceImpl",
                "execute", new Class[]
                { WEB3GenRequest.class },
                l_exception);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_handler.confirmChangeOpenContract(l_request).errorInfo);
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
     * （連続）オプション訂正新規建サービスを取得に失敗しました。
     * 
     * excute() throws WEB3BaseRuntimeException
     */
    public void testConfirmChangeOpenContract_C0003()
    {
        String STR_METHOD_NAME = "testConfirmChangeOpenContract_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3ToSuccOptionChangeOpenContractHandler l_handler = 
                new WEB3ToSuccOptionChangeOpenContractHandler();
            
            WEB3SuccOptionsOpenChangeConfirmRequest l_request =
                new WEB3SuccOptionsOpenChangeConfirmRequest();
            
            WEB3BaseRuntimeException l_exception =
                new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_00600, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeOpenContractServiceImpl",
                    "execute", new Class[]
                    { WEB3GenRequest.class },
                    l_exception);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_handler.confirmChangeOpenContract(l_request).errorInfo);
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
    public void testConfirmChangeOpenContract_C0004()
    {
        String STR_METHOD_NAME = "testConfirmChangeOpenContract_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3ToSuccOptionChangeOpenContractHandler l_handler = 
                new WEB3ToSuccOptionChangeOpenContractHandler();
            
            WEB3SuccOptionsOpenChangeConfirmRequest l_request =
                new WEB3SuccOptionsOpenChangeConfirmRequest();
            
            WEB3SuccOptionsOpenChangeConfirmResponse l_response =
                new WEB3SuccOptionsOpenChangeConfirmResponse();
            l_response.checkPrice = "1";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeOpenContractServiceImpl",
                "execute", new Class[]
                { WEB3GenRequest.class },
                l_response);
            
            assertEquals("1", l_handler.confirmChangeOpenContract(l_request).checkPrice);
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
     * （連続）オプション訂正新規建サービスを取得に失敗しました。
     */
    public void testCompleteChangeOpenContract_C0001()
    {
        String STR_METHOD_NAME = "testCompleteChangeOpenContract_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionChangeOpenContractService l_service = null;
        try
        {
            l_service = (WEB3ToSuccOptionChangeOpenContractService)Services.getService(
                    WEB3ToSuccOptionChangeOpenContractService.class);
            
            Services.unregisterService(WEB3ToSuccOptionChangeOpenContractService.class);
            
            WEB3ToSuccOptionChangeOpenContractHandler l_handler = 
                new WEB3ToSuccOptionChangeOpenContractHandler();
            
            WEB3SuccOptionsOpenChangeCompleteRequest l_request =
                new WEB3SuccOptionsOpenChangeCompleteRequest();
            
            WEB3SuccOptionsOpenChangeCompleteResponse l_response =
                l_handler.completeChangeOpenContract(l_request);
            
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
            Services.registerService(WEB3ToSuccOptionChangeOpenContractService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * （連続）オプション訂正新規建注文の更新が失敗しました。
     * 
     * excute() throws WEB3BaseException
     */
    public void testCompleteChangeOpenContract_C0002()
    {
        String STR_METHOD_NAME = "testCompleteChangeOpenContract_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3ToSuccOptionChangeOpenContractHandler l_handler = 
                new WEB3ToSuccOptionChangeOpenContractHandler();
            
            WEB3SuccOptionsOpenChangeCompleteRequest l_request =
                new WEB3SuccOptionsOpenChangeCompleteRequest();
            
            WEB3BaseException l_exception =
                new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00600, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeOpenContractServiceImpl",
                    "execute", new Class[]
                    { WEB3GenRequest.class },
                    l_exception);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_handler.completeChangeOpenContract(l_request).errorInfo);
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
     * （連続）オプション訂正新規建注文の更新が失敗しました。
     * 
     * excute() throws WEB3BaseRuntimeException
     */
    public void testCompleteChangeOpenContract_C0003()
    {
        String STR_METHOD_NAME = "testCompleteChangeOpenContract_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3ToSuccOptionChangeOpenContractHandler l_handler = 
                new WEB3ToSuccOptionChangeOpenContractHandler();
            
            WEB3SuccOptionsOpenChangeCompleteRequest l_request =
                new WEB3SuccOptionsOpenChangeCompleteRequest();
            
            WEB3BaseRuntimeException l_exception =
                new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_00600, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeOpenContractServiceImpl",
                    "execute", new Class[]
                    { WEB3GenRequest.class },
                    l_exception);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_handler.completeChangeOpenContract(l_request).errorInfo);
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
    public void testCompleteChangeOpenContract_C0004()
    {
        String STR_METHOD_NAME = "testCompleteChangeOpenContract_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3ToSuccOptionChangeOpenContractHandler l_handler = 
                new WEB3ToSuccOptionChangeOpenContractHandler();
            
            WEB3SuccOptionsOpenChangeCompleteRequest l_request =
                new WEB3SuccOptionsOpenChangeCompleteRequest();
            
            WEB3SuccOptionsOpenChangeCompleteResponse l_response =
                new WEB3SuccOptionsOpenChangeCompleteResponse();
            l_response.errorMessage = "1";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeOpenContractServiceImpl",
                    "execute", new Class[]
                    { WEB3GenRequest.class },
                    l_response);
            
            assertEquals("1", l_handler.completeChangeOpenContract(l_request).errorMessage);
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

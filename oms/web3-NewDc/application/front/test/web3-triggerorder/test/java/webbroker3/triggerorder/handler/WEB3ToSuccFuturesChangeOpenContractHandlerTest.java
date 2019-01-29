head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.31.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccFuturesChangeOpenContractHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ToSuccFuturesChangeOpenContractHandlerTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/28 于瀟（中訊）新規作成
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenChangeConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeOpenContractService;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccFuturesChangeOpenContractHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesChangeOpenContractHandlerTest.class);

    public WEB3ToSuccFuturesChangeOpenContractHandlerTest(String arg0)
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
     * （連続）先物訂正新規建サービスを取得に失敗しました。
     */
    public void testConfirmChangeOpenContract_C0001()
    {
        String STR_METHOD_NAME = "testConfirmChangeOpenContract_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccFuturesChangeOpenContractService l_service = null;
        try
        {
            l_service = (WEB3ToSuccFuturesChangeOpenContractService)Services.getService(
                WEB3ToSuccFuturesChangeOpenContractService.class);
            
            Services.unregisterService(WEB3ToSuccFuturesChangeOpenContractService.class);
            
            WEB3ToSuccFuturesChangeOpenContractHandler l_handler = 
                new WEB3ToSuccFuturesChangeOpenContractHandler();
            
            WEB3SuccFuturesOpenChangeConfirmRequest l_request =
                new WEB3SuccFuturesOpenChangeConfirmRequest();
            
            WEB3SuccFuturesOpenChangeConfirmResponse l_response =
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
            Services.registerService(WEB3ToSuccFuturesChangeOpenContractService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * （連続）先物訂正新規建注文の発注審査が失敗しました。
     * 
     * excute() throws WEB3BaseException
     */
    public void testConfirmChangeOpenContract_C0002()
    {
        String STR_METHOD_NAME = "testConfirmChangeOpenContract_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3ToSuccFuturesChangeOpenContractHandler l_handler = 
                new WEB3ToSuccFuturesChangeOpenContractHandler();
            
            WEB3SuccFuturesOpenChangeConfirmRequest l_request =
                new WEB3SuccFuturesOpenChangeConfirmRequest();
            
            WEB3BaseException l_exception =
                new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00600, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeOpenContractServiceImpl",
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
     * （連続）先物訂正新規建注文の発注審査が失敗しました。
     * 
     * excute() throws WEB3BaseRuntimeException
     */
    public void testConfirmChangeOpenContract_C0003()
    {
        String STR_METHOD_NAME = "testConfirmChangeOpenContract_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3ToSuccFuturesChangeOpenContractHandler l_handler = 
                new WEB3ToSuccFuturesChangeOpenContractHandler();
            
            WEB3SuccFuturesOpenChangeConfirmRequest l_request =
                new WEB3SuccFuturesOpenChangeConfirmRequest();
            
            WEB3BaseRuntimeException l_exception =
                new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_00600, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeOpenContractServiceImpl",
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
     * 正常?了
     */
    public void testConfirmChangeOpenContract_C0004()
    {
        String STR_METHOD_NAME = "testConfirmChangeOpenContract_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3ToSuccFuturesChangeOpenContractHandler l_handler = 
                new WEB3ToSuccFuturesChangeOpenContractHandler();
            
            WEB3SuccFuturesOpenChangeConfirmRequest l_request =
                new WEB3SuccFuturesOpenChangeConfirmRequest();
            
            WEB3SuccFuturesOpenChangeConfirmResponse l_response =
                new WEB3SuccFuturesOpenChangeConfirmResponse();
            l_response.checkPrice = "1";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeOpenContractServiceImpl",
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
     * （連続）先物訂正新規建サービスを取得に失敗しました。
     */
    public void testCompleteChangeOpenContract_C0001()
    {
        String STR_METHOD_NAME = "testCompleteChangeOpenContract_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccFuturesChangeOpenContractService l_service = null;
        try
        {
            l_service = (WEB3ToSuccFuturesChangeOpenContractService)Services.getService(
                    WEB3ToSuccFuturesChangeOpenContractService.class);
            
            Services.unregisterService(WEB3ToSuccFuturesChangeOpenContractService.class);
            
            WEB3ToSuccFuturesChangeOpenContractHandler l_handler = 
                new WEB3ToSuccFuturesChangeOpenContractHandler();
            
            WEB3SuccFuturesOpenChangeCompleteRequest l_request =
                new WEB3SuccFuturesOpenChangeCompleteRequest();
            
            WEB3SuccFuturesOpenChangeCompleteResponse l_response =
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
            Services.registerService(WEB3ToSuccFuturesChangeOpenContractService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * （連続）先物訂正新規建注文の更新が失敗しました。
     * 
     * excute() throws WEB3BaseException
     */
    public void testCompleteChangeOpenContract_C0002()
    {
        String STR_METHOD_NAME = "testCompleteChangeOpenContract_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3ToSuccFuturesChangeOpenContractHandler l_handler = 
                new WEB3ToSuccFuturesChangeOpenContractHandler();
            
            WEB3SuccFuturesOpenChangeCompleteRequest l_request =
                new WEB3SuccFuturesOpenChangeCompleteRequest();
            
            WEB3BaseException l_exception =
                new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00600, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeOpenContractServiceImpl",
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
     * （連続）先物訂正新規建注文の更新が失敗しました。
     * 
     * excute() throws WEB3BaseRuntimeException
     */
    public void testCompleteChangeOpenContract_C0003()
    {
        String STR_METHOD_NAME = "testCompleteChangeOpenContract_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3ToSuccFuturesChangeOpenContractHandler l_handler = 
                new WEB3ToSuccFuturesChangeOpenContractHandler();
            
            WEB3SuccFuturesOpenChangeCompleteRequest l_request =
                new WEB3SuccFuturesOpenChangeCompleteRequest();
            
            WEB3BaseRuntimeException l_exception =
                new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_00600, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeOpenContractServiceImpl",
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
    
    public void testCompleteChangeOpenContract_C0004()
    {
        String STR_METHOD_NAME = "testCompleteChangeOpenContract_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3ToSuccFuturesChangeOpenContractHandler l_handler = 
                new WEB3ToSuccFuturesChangeOpenContractHandler();
            
            WEB3SuccFuturesOpenChangeCompleteRequest l_request =
                new WEB3SuccFuturesOpenChangeCompleteRequest();
            
            WEB3SuccFuturesOpenChangeCompleteResponse l_response =
                new WEB3SuccFuturesOpenChangeCompleteResponse();
            l_response.errorMessage = "1";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeOpenContractServiceImpl",
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

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.31.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccFuturesChangeClosingContractHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ToSuccFuturesChangeClosingContractHandlerTest.java
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
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeClosingContractService;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccFuturesChangeClosingContractHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesChangeClosingContractHandlerTest.class);
    
    public WEB3ToSuccFuturesChangeClosingContractHandlerTest(String arg0)
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
     * (（連続）先物訂正返済サービスを取得に失敗しました。
     */
    public void testConfirmChangeClosingContract_C0001()
    {
        String STR_METHOD_NAME = "testConfirmChangeClosingContract_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccFuturesChangeClosingContractService l_service = null;
        try
        {
            l_service = (WEB3ToSuccFuturesChangeClosingContractService)Services.getService(
                WEB3ToSuccFuturesChangeClosingContractService.class);
            
            Services.unregisterService(WEB3ToSuccFuturesChangeClosingContractService.class);
            
            WEB3ToSuccFuturesChangeClosingContractHandler l_handler = 
                new WEB3ToSuccFuturesChangeClosingContractHandler();
            
            WEB3SuccFuturesCloseChangeConfirmRequest l_request =
                new WEB3SuccFuturesCloseChangeConfirmRequest();
            
            WEB3SuccFuturesCloseChangeConfirmResponse l_response =
                l_handler.confirmChangeClosingContract(l_request);
            
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
            Services.registerService(WEB3ToSuccFuturesChangeClosingContractService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * （連続）先物訂正返済の発注審査が失敗しました。
     * 
     * excute() throws WEB3BaseException
     */
    public void testConfirmChangeClosingContract_C0002()
    {
        String STR_METHOD_NAME = "testConfirmChangeClosingContract_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3ToSuccFuturesChangeClosingContractHandler l_handler = 
                new WEB3ToSuccFuturesChangeClosingContractHandler();
            
            WEB3SuccFuturesCloseChangeConfirmRequest l_request =
                new WEB3SuccFuturesCloseChangeConfirmRequest();
            
            WEB3BaseException l_exception =
                new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00600, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeClosingContractServiceImpl",
                "execute", new Class[]
                { WEB3GenRequest.class },
                l_exception);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_handler.confirmChangeClosingContract(l_request).errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * （連続）先物訂正返済の発注審査が失敗しました。
     * 
     * excute() throws WEB3BaseRuntimeException
     */
    public void testConfirmChangeClosingContract_C0003()
    {
        String STR_METHOD_NAME = "testConfirmChangeClosingContract_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3ToSuccFuturesChangeClosingContractHandler l_handler = 
                new WEB3ToSuccFuturesChangeClosingContractHandler();
            
            WEB3SuccFuturesCloseChangeConfirmRequest l_request =
                new WEB3SuccFuturesCloseChangeConfirmRequest();
            
            WEB3BaseRuntimeException l_exception =
                new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_00600, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeClosingContractServiceImpl",
                "execute", new Class[]
                { WEB3GenRequest.class },
                l_exception);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_handler.confirmChangeClosingContract(l_request).errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /**
     * 正常終了
     */
    public void testConfirmChangeClosingContract_C0004()
    {
        String STR_METHOD_NAME = "testConfirmChangeClosingContract_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3ToSuccFuturesChangeClosingContractHandler l_handler = 
                new WEB3ToSuccFuturesChangeClosingContractHandler();
            
            WEB3SuccFuturesCloseChangeConfirmRequest l_request =
                new WEB3SuccFuturesCloseChangeConfirmRequest();
            
            WEB3SuccFuturesCloseChangeConfirmResponse l_response =
                new WEB3SuccFuturesCloseChangeConfirmResponse();
            l_response.checkPrice = "1";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeClosingContractServiceImpl",
                "execute", new Class[]
                { WEB3GenRequest.class },
                l_response);
            
            assertEquals("1", l_handler.confirmChangeClosingContract(l_request).checkPrice);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (（連続）先物訂正返済サービスを取得に失敗しました。
     */
    public void testCompleteChangeClosingContract_C0001()
    {
        String STR_METHOD_NAME = "testCompleteChangeClosingContract_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccFuturesChangeClosingContractService l_service = null;
        try
        {
            l_service = (WEB3ToSuccFuturesChangeClosingContractService)Services.getService(
                WEB3ToSuccFuturesChangeClosingContractService.class);
            
            Services.unregisterService(WEB3ToSuccFuturesChangeClosingContractService.class);
            
            WEB3ToSuccFuturesChangeClosingContractHandler l_handler = 
                new WEB3ToSuccFuturesChangeClosingContractHandler();
            
            WEB3SuccFuturesCloseChangeCompleteRequest l_request =
                new WEB3SuccFuturesCloseChangeCompleteRequest();
            
            WEB3SuccFuturesCloseChangeCompleteResponse l_response =
                l_handler.completeChangeClosingContract(l_request);
            
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
            Services.registerService(WEB3ToSuccFuturesChangeClosingContractService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * （連続）先物訂正返済の更新が失敗しました。
     * 
     * excute() throws WEB3BaseException
     */
    public void testCompleteChangeClosingContract_C0002()
    {
        String STR_METHOD_NAME = "testCompleteChangeClosingContract_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3ToSuccFuturesChangeClosingContractHandler l_handler = 
                new WEB3ToSuccFuturesChangeClosingContractHandler();
            
            WEB3SuccFuturesCloseChangeCompleteRequest l_request =
                new WEB3SuccFuturesCloseChangeCompleteRequest();
            
            WEB3BaseException l_exception =
                new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00600, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeClosingContractServiceImpl",
                "execute", new Class[]
                { WEB3GenRequest.class },
                l_exception);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_handler.completeChangeClosingContract(l_request).errorInfo);
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
     * （連続）先物訂正返済の更新が失敗しました。
     * 
     * excute() throws WEB3BaseRuntimeException
     */
    public void testCompleteChangeClosingContract_C0003()
    {
        String STR_METHOD_NAME = "testCompleteChangeClosingContract_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3ToSuccFuturesChangeClosingContractHandler l_handler = 
                new WEB3ToSuccFuturesChangeClosingContractHandler();
            
            WEB3SuccFuturesCloseChangeCompleteRequest l_request =
                new WEB3SuccFuturesCloseChangeCompleteRequest();
            
            WEB3BaseRuntimeException l_exception =
                new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_00600, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeClosingContractServiceImpl",
                "execute", new Class[]
                { WEB3GenRequest.class },
                l_exception);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_handler.completeChangeClosingContract(l_request).errorInfo);
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
    public void testCompleteChangeClosingContract_C0004()
    {
        String STR_METHOD_NAME = "testCompleteChangeClosingContract_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3ToSuccFuturesChangeClosingContractHandler l_handler = 
                new WEB3ToSuccFuturesChangeClosingContractHandler();
            
            WEB3SuccFuturesCloseChangeCompleteRequest l_request =
                new WEB3SuccFuturesCloseChangeCompleteRequest();
            
            WEB3SuccFuturesCloseChangeCompleteResponse l_response =
                new WEB3SuccFuturesCloseChangeCompleteResponse();
            l_response.orderActionId = "1";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeClosingContractServiceImpl",
                "execute", new Class[]
                { WEB3GenRequest.class },
                l_response);
            
            assertEquals("1", l_handler.completeChangeClosingContract(l_request).orderActionId);
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

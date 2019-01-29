head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.49.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFixedBuyConditionHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3MutualFixedBuyConditionHandlerTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/17 安陽(中訊) 新規作成
*/

package webbroker3.mf.handler;


import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyAccountInfo;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionCompleteRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionCompleteResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionConfirmRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionConfirmResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionInputRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionInputResponse;
import webbroker3.mf.service.delegate.WEB3MutualFixedBuyConditionService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualFixedBuyConditionHandlerTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyConditionHandlerTest.class);
    
    private WEB3MutualFixedBuyConditionHandler l_handler = null;

    private WEB3MutualFixedBuyConditionService l_service = null;
    
    public WEB3MutualFixedBuyConditionHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_handler = new WEB3MutualFixedBuyConditionHandler();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.mf.handler.WEB3MutualFixedBuyConditionHandler.mutualFixedBuyConditionInput(WEB3MutualFixedBuyConditionInputRequest)'
     */
    
    //投信定時定額買付銘柄条件登録サービスの取得に失敗しました。
    public void testMutualFixedBuyConditionInput_C0001()
    {
        final String STR_METHOD_NAME = "testMutualFixedBuyConditionInput_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MutualFixedBuyConditionInputRequest l_request =
                new WEB3MutualFixedBuyConditionInputRequest();
            WEB3MutualFixedBuyConditionInputResponse l_response = null;
            
            l_service =
                (WEB3MutualFixedBuyConditionService)Services.getService(
                        WEB3MutualFixedBuyConditionService.class);
            Services.unregisterService(WEB3MutualFixedBuyConditionService.class);
            
            l_response = l_handler.mutualFixedBuyConditionInput(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3MutualFixedBuyConditionService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //定時定額買付銘柄条件登録入力に失敗しました。
    //excute() throw WEB3BaseException
    public void testMutualFixedBuyConditionInput_C0002()
    {
        final String STR_METHOD_NAME = "testMutualFixedBuyConditionInput_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MutualFixedBuyConditionInputRequest l_request =
                new WEB3MutualFixedBuyConditionInputRequest();
            WEB3MutualFixedBuyConditionInputResponse l_response = null;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyConditionServiceImpl",
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,"パラメータタイプ不正。"));
            
            l_response = l_handler.mutualFixedBuyConditionInput(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
            
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //定時定額買付銘柄条件登録入力に失敗しました。
    //excute() throw WEB3BaseRuntimeException
    public void testMutualFixedBuyConditionInput_C0003()
    {
        final String STR_METHOD_NAME = "testMutualFixedBuyConditionInput_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MutualFixedBuyConditionInputRequest l_request =
                new WEB3MutualFixedBuyConditionInputRequest();
            WEB3MutualFixedBuyConditionInputResponse l_response = null;
            
            WEB3BaseRuntimeException l_runtimeException =
                new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80008, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyConditionServiceImpl",
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_runtimeException);
            
            l_response = l_handler.mutualFixedBuyConditionInput(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80008, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //正常終了
    public void testMutualFixedBuyConditionInput_C0004()
    {
        final String STR_METHOD_NAME = "testMutualFixedBuyConditionInput_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MutualFixedBuyConditionInputRequest l_request =
                new WEB3MutualFixedBuyConditionInputRequest();
            WEB3MutualFixedBuyConditionInputResponse l_response = null;
            
            WEB3MutualFixedBuyConditionInputResponse l_expectedResponse =
                new WEB3MutualFixedBuyConditionInputResponse();
            l_expectedResponse.acountInfo = new WEB3MutualFixedBuyAccountInfo();
            l_expectedResponse.acountInfo.financialInstitutionName = "ICBC";
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyConditionServiceImpl",
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_expectedResponse);
            
            l_response = l_handler.mutualFixedBuyConditionInput(l_request);
            
            assertEquals("ICBC", l_response.acountInfo.financialInstitutionName);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    /*
     * Test method for 'webbroker3.mf.handler.WEB3MutualFixedBuyConditionHandler.mutualFixedBuyConditionConfirm(WEB3MutualFixedBuyConditionConfirmRequest)'
     */
    
    //投信定時定額買付銘柄条件登録サービスの取得に失敗しました。
    public void testMutualFixedBuyConditionConfirm_C0001()
    {
        final String STR_METHOD_NAME = "testMutualFixedBuyConditionConfirm_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MutualFixedBuyConditionConfirmRequest l_request =
                new WEB3MutualFixedBuyConditionConfirmRequest();
            WEB3MutualFixedBuyConditionConfirmResponse l_response = null;
            
            l_service =
                (WEB3MutualFixedBuyConditionService)Services.getService(
                        WEB3MutualFixedBuyConditionService.class);
            Services.unregisterService(WEB3MutualFixedBuyConditionService.class);
            
            l_response = l_handler.mutualFixedBuyConditionConfirm(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3MutualFixedBuyConditionService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //定時定額買付銘柄条件登録入力に失敗しました。
    //excute() throw WEB3BaseException
    public void testMutualFixedBuyConditionConfirm_C0002()
    {
        final String STR_METHOD_NAME = "testMutualFixedBuyConditionConfirm_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MutualFixedBuyConditionConfirmRequest l_request =
                new WEB3MutualFixedBuyConditionConfirmRequest();
            WEB3MutualFixedBuyConditionConfirmResponse l_response = null;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyConditionServiceImpl",
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,"パラメータタイプ不正。"));
            
            l_response = l_handler.mutualFixedBuyConditionConfirm(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
            
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //定時定額買付銘柄条件登録入力に失敗しました。
    //excute() throw WEB3BaseRuntimeException
    public void testMutualFixedBuyConditionConfirm_C0003()
    {
        final String STR_METHOD_NAME = "testMutualFixedBuyConditionConfirm_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MutualFixedBuyConditionConfirmRequest l_request =
                new WEB3MutualFixedBuyConditionConfirmRequest();
            WEB3MutualFixedBuyConditionConfirmResponse l_response = null;
            
            WEB3BaseRuntimeException l_runtimeException =
                new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80008, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyConditionServiceImpl",
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_runtimeException);
            
            l_response = l_handler.mutualFixedBuyConditionConfirm(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80008, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //正常終了
    public void testMutualFixedBuyConditionConfirm_C0004()
    {
        final String STR_METHOD_NAME = "testMutualFixedBuyConditionConfirm_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MutualFixedBuyConditionConfirmRequest l_request =
                new WEB3MutualFixedBuyConditionConfirmRequest();
            WEB3MutualFixedBuyConditionConfirmResponse l_response = null;
            
            WEB3MutualFixedBuyConditionConfirmResponse l_expectedResponse =
                new WEB3MutualFixedBuyConditionConfirmResponse();
            l_expectedResponse.acountInfo = new WEB3MutualFixedBuyAccountInfo();
            l_expectedResponse.acountInfo.financialInstitutionName = "ICBC";
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyConditionServiceImpl",
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_expectedResponse);
            
            l_response = l_handler.mutualFixedBuyConditionConfirm(l_request);
            
            assertEquals("ICBC", l_response.acountInfo.financialInstitutionName);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    /*
     * Test method for 'webbroker3.mf.handler.WEB3MutualFixedBuyConditionHandler.mutualFixedBuyConditionComplete(WEB3MutualFixedBuyConditionCompleteRequest)'
     */
    
    //投信定時定額買付銘柄条件登録サービスの取得に失敗しました。
    public void testMutualFixedBuyConditionComplete_C0001()
    {
        final String STR_METHOD_NAME = "testMutualFixedBuyConditionComplete_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MutualFixedBuyConditionCompleteRequest l_request =
                new WEB3MutualFixedBuyConditionCompleteRequest();
            WEB3MutualFixedBuyConditionCompleteResponse l_response = null;
            
            l_service =
                (WEB3MutualFixedBuyConditionService)Services.getService(
                        WEB3MutualFixedBuyConditionService.class);
            Services.unregisterService(WEB3MutualFixedBuyConditionService.class);
            
            l_response = l_handler.mutualFixedBuyConditionComplete(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3MutualFixedBuyConditionService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //定時定額買付銘柄条件登録入力に失敗しました。
    //excute() throw WEB3BaseException
    public void testMutualFixedBuyConditionComplete_C0002()
    {
        final String STR_METHOD_NAME = "testMutualFixedBuyConditionComplete_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MutualFixedBuyConditionCompleteRequest l_request =
                new WEB3MutualFixedBuyConditionCompleteRequest();
            WEB3MutualFixedBuyConditionCompleteResponse l_response = null;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyConditionServiceImpl",
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,"パラメータタイプ不正。"));
            
            l_response = l_handler.mutualFixedBuyConditionComplete(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
            
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //定時定額買付銘柄条件登録入力に失敗しました。
    //excute() throw WEB3BaseRuntimeException
    public void testMutualFixedBuyConditionComplete_C0003()
    {
        final String STR_METHOD_NAME = "testMutualFixedBuyConditionComplete_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MutualFixedBuyConditionCompleteRequest l_request =
                new WEB3MutualFixedBuyConditionCompleteRequest();
            WEB3MutualFixedBuyConditionCompleteResponse l_response = null;
            
            WEB3BaseRuntimeException l_runtimeException =
                new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80008, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyConditionServiceImpl",
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_runtimeException);
            
            l_response = l_handler.mutualFixedBuyConditionComplete(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80008, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //正常終了
    public void testMutualFixedBuyConditionComplete_C0004()
    {
        final String STR_METHOD_NAME = "testMutualFixedBuyConditionComplete_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3MutualFixedBuyConditionCompleteRequest l_request =
                new WEB3MutualFixedBuyConditionCompleteRequest();
            WEB3MutualFixedBuyConditionCompleteResponse l_response = null;
            
            WEB3MutualFixedBuyConditionCompleteResponse l_expectedResponse =
                new WEB3MutualFixedBuyConditionCompleteResponse();
            l_expectedResponse.acountInfo = new WEB3MutualFixedBuyAccountInfo();
            l_expectedResponse.acountInfo.financialInstitutionName = "ICBC";
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyConditionServiceImpl",
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_expectedResponse);
            
            l_response = l_handler.mutualFixedBuyConditionComplete(l_request);
            
            assertEquals("ICBC", l_response.acountInfo.financialInstitutionName);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@

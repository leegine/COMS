head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.19.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesOpenContractInputHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部 
File Name        : WEB3OptionsOrderHistoryRequestTest.java
Author Name      : Daiwa Institute of Research  
Revesion History : 2008/07/07 劉剣(中訊) 新規作成  
*/
package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginInputRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginInputResponse;
import webbroker3.ifo.message.WEB3FuturesProductSelectRequest;
import webbroker3.ifo.message.WEB3FuturesProductSelectResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesOpenContractInputService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesOpenContractInputHandlerTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesOpenContractInputHandlerTest.class);

    private WEB3FuturesOpenContractInputHandler l_handler = null;
    private WEB3FuturesOpenMarginInputRequest l_request = null;
    private WEB3FuturesOpenMarginInputResponse l_response = null;
    private WEB3FuturesOpenContractInputService l_service = null;
    private WEB3FuturesProductSelectResponse l_selectResponse = null;
    private WEB3FuturesProductSelectRequest l_selectRequest = null;

    public WEB3FuturesOpenContractInputHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_request = new WEB3FuturesOpenMarginInputRequest();
        this.l_selectRequest = new WEB3FuturesProductSelectRequest();
        this.l_handler = new WEB3FuturesOpenContractInputHandler();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * 株価指数先物新規建入力サービス取得に失敗しました。
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80002
     */
    public void testOpenMarginInputRequest_C0001()
    {
        final String STR_METHOD_NAME = "testOpenMarginInputRequest_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_service =
                (WEB3FuturesOpenContractInputService)Services.getService(WEB3FuturesOpenContractInputService.class);
            Services.unregisterService(WEB3FuturesOpenContractInputService.class);
            l_response = l_handler.openMarginInputRequest(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3FuturesOpenContractInputService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 株価指数先物新規建入力の表示処理に失敗しました。
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80018
     */
    public void testOpenMarginInputRequest_C0002()
    {
        final String STR_METHOD_NAME = "testOpenMarginInputRequest_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOpenContractInputServiceImpl",
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,"パラメータタイプ不正。"));
            l_response = l_handler.openMarginInputRequest(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
            
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testOpenMarginInputRequest_C0003()
    {
        final String STR_METHOD_NAME = "testOpenMarginInputRequest_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3FuturesOpenMarginInputResponse l_futuresOpenMarginInputResponse = new WEB3FuturesOpenMarginInputResponse(l_request);
            l_futuresOpenMarginInputResponse.futTradingPower = "1001";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOpenContractInputServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_futuresOpenMarginInputResponse);
            l_response = l_handler.openMarginInputRequest(l_request);
            assertNotNull(l_response);
            assertEquals(WEB3FuturesOpenMarginInputResponse.class, l_response.getClass());
            assertEquals("1001", l_response.futTradingPower);
              
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 株価指数先物新規建入力サービス取得に失敗しました。
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80002
     */
    public void testOpenMarginProductSelectRequest_C0001()
    {
        final String STR_METHOD_NAME = "testOpenMarginProductSelectRequest_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_service =
                (WEB3FuturesOpenContractInputService)Services.getService(WEB3FuturesOpenContractInputService.class);
            Services.unregisterService(WEB3FuturesOpenContractInputService.class);
            l_selectResponse = l_handler.openMarginProductSelectRequest(l_selectRequest);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_selectResponse.errorInfo);
            
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3FuturesOpenContractInputService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 株価指数先物新規建入力の銘柄選択表示処理に失敗しました。
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80018
     */
    public void testOpenMarginProductSelectRequest_C0002()
    {
        final String STR_METHOD_NAME = "testOpenMarginProductSelectRequest_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOpenContractInputServiceImpl",
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,"パラメータタイプ不正。"));
            l_selectResponse = l_handler.openMarginProductSelectRequest(l_selectRequest);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_selectResponse.errorInfo);
            
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testOpenMarginProductSelectRequest_C0003()
    {
        final String STR_METHOD_NAME = "testOpenMarginProductSelectRequest_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3FuturesProductSelectResponse l_futuresProductSelectResponse = new WEB3FuturesProductSelectResponse(l_selectRequest);
            l_futuresProductSelectResponse.futTradingPower = "1001";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOpenContractInputServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_futuresProductSelectResponse);
            l_selectResponse = l_handler.openMarginProductSelectRequest(l_selectRequest);
            assertNotNull(l_selectResponse);
            assertEquals(WEB3FuturesProductSelectResponse.class, l_selectResponse.getClass());
            assertEquals("1001", l_selectResponse.futTradingPower);
              
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@

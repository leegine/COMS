head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.19.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesSettleContractOrderHandlerTest.java;


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
Revesion History : 2008/07/08 劉剣(中訊) 新規作成  
*/
package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginCompleteResponse;
import webbroker3.ifo.message.WEB3FuturesCloseMarginConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractOrderService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesSettleContractOrderHandlerTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesOpenContractInputHandlerTest.class);

    private WEB3FuturesSettleContractOrderHandler l_handler = null;
    private WEB3FuturesCloseMarginConfirmRequest l_request = null;
    private WEB3FuturesCloseMarginConfirmResponse l_response = null;
    private WEB3FuturesSettleContractOrderService l_service = null;
    private WEB3FuturesCloseMarginCompleteResponse l_selectResponse = null;
    private WEB3FuturesCloseMarginCompleteRequest l_selectRequest = null;

    public WEB3FuturesSettleContractOrderHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_request = new WEB3FuturesCloseMarginConfirmRequest();
        this.l_selectRequest = new WEB3FuturesCloseMarginCompleteRequest();
        this.l_handler = new WEB3FuturesSettleContractOrderHandler();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * 先物返済注文サービスの取得に失敗しました。
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80002
     */
    public void testConfirmCloseMargin_C0001()
    {
        final String STR_METHOD_NAME = "testConfirmCloseMargin_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_service =
                (WEB3FuturesSettleContractOrderService)Services.getService(WEB3FuturesSettleContractOrderService.class);
            Services.unregisterService(WEB3FuturesSettleContractOrderService.class);
            l_response = l_handler.confirmCloseMargin(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3FuturesSettleContractOrderService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 株価指数先物の返済発注審査に失敗しました。
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80018
     */
    public void testConfirmCloseMargin_C0002()
    {
        final String STR_METHOD_NAME = "testConfirmCloseMargin_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesSettleContractOrderServiceImpl",
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,"パラメータタイプ不正。"));
            l_response = l_handler.confirmCloseMargin(l_request);
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
    public void testConfirmCloseMargin_C0003()
    {
        final String STR_METHOD_NAME = "testConfirmCloseMargin_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3FuturesCloseMarginConfirmResponse l_futuresOpenMarginInputResponse = new WEB3FuturesCloseMarginConfirmResponse();
            l_futuresOpenMarginInputResponse.orderId = "1001";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesSettleContractOrderServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_futuresOpenMarginInputResponse);
            l_response = l_handler.confirmCloseMargin(l_request);
            assertNotNull(l_response);
            assertEquals(WEB3FuturesCloseMarginConfirmResponse.class, l_response.getClass());
            assertEquals("1001", l_response.orderId);
            
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 先物返済注文サービスの取得に失敗しました。
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80002
     */
    public void testCompleteCloseMargin_C0001()
    {
        final String STR_METHOD_NAME = "testCompleteCloseMargin_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_service =
                (WEB3FuturesSettleContractOrderService)Services.getService(WEB3FuturesSettleContractOrderService.class);
            Services.unregisterService(WEB3FuturesSettleContractOrderService.class);
            l_selectResponse = l_handler.completeCloseMargin(l_selectRequest);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_selectResponse.errorInfo);
            
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3FuturesSettleContractOrderService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 株価指数先物の返済注文に失敗しました。
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80018
     */
    public void testCompleteCloseMargin_C0002()
    {
        final String STR_METHOD_NAME = "testCompleteCloseMargin_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesSettleContractOrderServiceImpl",
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,"パラメータタイプ不正。"));
            l_selectResponse = l_handler.completeCloseMargin(l_selectRequest);
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
    public void testCompleteCloseMargin_C0003()
    {
        final String STR_METHOD_NAME = "testCompleteCloseMargin_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3FuturesCloseMarginCompleteResponse l_futuresCloseMarginCompleteResponse = new WEB3FuturesCloseMarginCompleteResponse();
            l_futuresCloseMarginCompleteResponse.orderActionId = "1001";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesSettleContractOrderServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_futuresCloseMarginCompleteResponse);
            l_selectResponse = l_handler.completeCloseMargin(l_selectRequest);
            assertNotNull(l_selectResponse);
            assertEquals(WEB3FuturesCloseMarginCompleteResponse.class, l_selectResponse.getClass());
            assertEquals("1001", l_selectResponse.orderActionId);
            
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

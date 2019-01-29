head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.42.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPShortfallGenerationHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TPShortfallGenerationHandlerTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/17 劉剣（中訊）新規作成
*/

package webbroker3.tradingpower.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.message.WEB3TPShortfallGenerationRequest;
import webbroker3.tradingpower.message.WEB3TPShortfallGenerationResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPAssetTradingPowerService;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPShortfallGenerationHandlerTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPShortfallGenerationHandlerTest.class);

    private WEB3TPShortfallGenerationHandler l_handler = null;
    private WEB3TPShortfallGenerationRequest l_request = null;
    private WEB3TPShortfallGenerationResponse l_response = null;
    private WEB3TPAssetTradingPowerService l_service = null;

    public WEB3TPShortfallGenerationHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_request = new WEB3TPShortfallGenerationRequest();
        this.l_handler = new WEB3TPShortfallGenerationHandler();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * 資産余力情報画面表示サービスを取得に失敗しました。
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80002
     */
    public void testCreateShortfallGenerationScreen_C0001()
    {
        final String STR_METHOD_NAME = "testCreateShortfallGenerationScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_service =
                (WEB3TPAssetTradingPowerService)Services.getService(WEB3TPAssetTradingPowerService.class);
            Services.unregisterService(WEB3TPAssetTradingPowerService.class);
            l_response = l_handler.createShortfallGenerationScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3TPAssetTradingPowerService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 資産余力情報画面表示処理に失敗しました。
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80018
     */
    public void testCreateShortfallGenerationScreen_C0002()
    {
        final String STR_METHOD_NAME = "testCreateShortfallGenerationScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPAssetTradingPowerServiceImpl",
                    "execute",
                    new Class[]{WEB3GenRequest.class},
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,"パラメータタイプ不正。"));
            
            l_response = l_handler.createShortfallGenerationScreen(l_request);
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
    public void testCreateShortfallGenerationScreen_C0003()
    {
        final String STR_METHOD_NAME = "testCreateShortfallGenerationScreen_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPShortfallGenerationResponse l_response0 = new WEB3TPShortfallGenerationResponse();
            l_response0.shortfallGenerationStateDiv = "0";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPAssetTradingPowerServiceImpl",
                    "execute",
                    new Class[]{WEB3GenRequest.class},
                    l_response0);

            l_response = l_handler.createShortfallGenerationScreen(l_request);
            assertNotNull(l_response);
            assertEquals(WEB3TPShortfallGenerationResponse.class, l_response.getClass());
            assertEquals("0", l_response.shortfallGenerationStateDiv);
            
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

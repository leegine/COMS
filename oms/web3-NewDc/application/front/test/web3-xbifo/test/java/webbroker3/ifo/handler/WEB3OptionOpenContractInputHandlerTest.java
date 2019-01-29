head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.19.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionOpenContractInputHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP新規建入力ハンドラ(WEB3OptionOpenContractInputHandlerTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/28 孫洪江(中訊) 新規作成
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginInputRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginInputResponse;
import webbroker3.ifo.message.WEB3OptionsProductSelectRequest;
import webbroker3.ifo.message.WEB3OptionsProductSelectResponse;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractInputService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionOpenContractInputHandlerTest extends TestBaseForMock
{
    private WEB3OptionsOpenMarginInputRequest l_request = null;

    private WEB3OptionOpenContractInputHandler l_handler = null;

    private WEB3OptionOpenContractInputService l_service = null;
    
    private WEB3OptionsOpenMarginInputResponse l_response = null;
    
    private WEB3OptionsProductSelectRequest l_selectRequest = null;
    
    private WEB3OptionsProductSelectResponse l_selectResponse = null;

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionOpenContractInputHandlerTest.class);

    public WEB3OptionOpenContractInputHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_handler = new WEB3OptionOpenContractInputHandler();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.l_handler = null;
        this.l_service = null;
    }
    
    /*
     * パラメータタイプ不正。
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80018
     */
    public void testOpenContractInputRequest_0001()
    {
        String STR_METHOD_NAME = "testOpenContractInputRequest_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3OptionsOpenMarginInputRequest();

        try
        {   
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOpenContractInputServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,"パラメータタイプ不正。"));
            
            l_response = this.l_handler.openContractInputRequest(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 株価指数オプション新規建入力サービス取得に失敗しました。
     */
    public void testOpenContractInputRequest_0002()
    {
        final String STR_METHOD_NAME = "test_searchExecuteReference_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            l_service = (WEB3OptionOpenContractInputService)Services.getService(WEB3OptionOpenContractInputService.class);
            Services.unregisterService(WEB3OptionOpenContractInputService.class);
            l_request = new WEB3OptionsOpenMarginInputRequest();
            l_response = this.l_handler.openContractInputRequest(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3OptionOpenContractInputService.class,l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * 正常
     */
    public void testOpenContractInputRequest_0003()
    {
        final String STR_METHOD_NAME = "test_searchExecuteReference_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        l_request = new WEB3OptionsOpenMarginInputRequest();
        WEB3OptionsOpenMarginInputResponse l_expectResponse = 
            new WEB3OptionsOpenMarginInputResponse();
        l_expectResponse.opTradingPower = "60";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOpenContractInputServiceImpl", "execute",
                new Class[]
                { WEB3GenRequest.class },
                l_expectResponse);

        try
        {
            l_response = this.l_handler.openContractInputRequest(l_request);
            assertNotNull(l_response);
            assertEquals(WEB3OptionsOpenMarginInputResponse.class, l_response.getClass());
            assertEquals("60", l_response.opTradingPower);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * パラメータタイプ不正。
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80018
     */
    public void testOpenContractProductSelectRequest_0004()
    {
        final String STR_METHOD_NAME = "testOpenContractProductSelectRequest_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_selectRequest = new WEB3OptionsProductSelectRequest();

        try
        {   
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOpenContractInputServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,"パラメータタイプ不正。"));
            
            l_selectResponse = this.l_handler.openContractProductSelectRequest(l_selectRequest);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_selectResponse.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);    
    }

    /*
     * 株価指数オプション新規建入力サービス取得に失敗しました。
     */
    public void testOpenContractProductSelectRequest_0005()
    {
        final String STR_METHOD_NAME = "testOpenContractProductSelectRequest_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            l_service = (WEB3OptionOpenContractInputService)Services.getService(WEB3OptionOpenContractInputService.class);
            Services.unregisterService(WEB3OptionOpenContractInputService.class);
            l_selectRequest = new WEB3OptionsProductSelectRequest();
            l_selectResponse = this.l_handler.openContractProductSelectRequest(l_selectRequest);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_selectResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3OptionOpenContractInputService.class,l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 正常
     */
    public void testOpenContractProductSelectRequest_0006()
    {
        final String STR_METHOD_NAME = "testOpenContractProductSelectRequest_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        this.l_selectRequest = new WEB3OptionsProductSelectRequest();
        WEB3OptionsProductSelectResponse l_expectResponse = 
            new WEB3OptionsProductSelectResponse();
        l_expectResponse.opTradingPower = "60";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOpenContractInputServiceImpl", "execute",
                new Class[]
                { WEB3GenRequest.class },
                l_expectResponse);

        try
        {
            this.l_selectResponse = this.l_handler.openContractProductSelectRequest(l_selectRequest);
            assertNotNull(l_selectResponse);
            assertEquals(WEB3OptionsProductSelectResponse.class, l_selectResponse.getClass());
            assertEquals("60", l_selectResponse.opTradingPower);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@

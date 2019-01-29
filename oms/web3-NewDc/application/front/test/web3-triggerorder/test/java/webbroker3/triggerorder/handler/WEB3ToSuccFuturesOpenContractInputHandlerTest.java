head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.31.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccFuturesOpenContractInputHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）先物新規建入力ハンドラ(WEB3ToSuccFuturesOpenContractInputHandlerTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/25 孟亞南(中訊) 新規作成モデル256
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenInputRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesOpenContractInputService;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesOpenContractInputServiceImplForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccFuturesOpenContractInputHandlerTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesOpenContractInputHandlerTest.class);

    WEB3ToSuccFuturesOpenContractInputHandler l_handler = null;
    
    public WEB3ToSuccFuturesOpenContractInputHandlerTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        l_handler = new WEB3ToSuccFuturesOpenContractInputHandler();
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        l_handler = null;
        super.tearDown();
    }

    /**
     * Exception
     *
     */
    public void test_openContractInputRequest_0001()
    {
        final String STR_METHOD_NAME = "test_openContractInputRequest_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);


        WEB3SuccFuturesOpenInputRequest l_request =
            new WEB3SuccFuturesOpenInputRequest();

        WEB3ToSuccFuturesOpenContractInputService l_service =
            new WEB3ToSuccFuturesOpenContractInputServiceImplForMock();
        try
        {
            Services.unregisterService(WEB3ToSuccFuturesOpenContractInputService.class);

            WEB3SuccFuturesOpenInputResponse l_response =
                l_handler.openContractInputRequest(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3ToSuccFuturesOpenContractInputService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * WEB3SystemLayerException
     *
     */
    public void test_openContractInputRequest_0002()
    {
        final String STR_METHOD_NAME = "test_openContractInputRequest_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);

        WEB3SuccFuturesOpenInputRequest l_request =
            new WEB3SuccFuturesOpenInputRequest();

        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesOpenContractInputServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                            "パラメータ値不正。"));
            WEB3SuccFuturesOpenInputResponse l_response =
                l_handler.openContractInputRequest(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * WEB3BaseRuntimeException
     *
     */
    public void test_openContractInputRequest_0003()
    {
        final String STR_METHOD_NAME = "test_openContractInputRequest_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);

        WEB3SuccFuturesOpenInputRequest l_request =
            new WEB3SuccFuturesOpenInputRequest();

        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesOpenContractInputServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                            "パラメータ値不正。"));

            WEB3SuccFuturesOpenInputResponse l_response =
                l_handler.openContractInputRequest(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_openContractInputRequest_0004()
    {
        final String STR_METHOD_NAME = "test_openContractInputRequest_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);

        WEB3SuccFuturesOpenInputRequest l_request =
            new WEB3SuccFuturesOpenInputRequest();

        try
        {
            WEB3SuccFuturesOpenInputResponse l_inputResponse = new WEB3SuccFuturesOpenInputResponse(l_request);
            l_inputResponse.marketList = new String[2];

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesOpenContractInputServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_inputResponse);

            WEB3SuccFuturesOpenInputResponse l_response =
                l_handler.openContractInputRequest(l_request);

            assertEquals("2", "" + l_response.marketList.length);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@

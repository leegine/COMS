head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.31.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccOptionOpenContractInputHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 
Author Name      : Daiwa Institute of Research
Revesion History : 
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
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenInputRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionOpenContractInputService;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionOpenContractInputServiceImplForMock;
import webbroker3.util.WEB3LogUtility;


public class WEB3ToSuccOptionOpenContractInputHandlerTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionOpenContractInputHandlerTest.class);

    WEB3ToSuccOptionOpenContractInputHandler l_handler = null;
    
    public WEB3ToSuccOptionOpenContractInputHandlerTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        l_handler = new WEB3ToSuccOptionOpenContractInputHandler();
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


        WEB3SuccOptionsOpenInputRequest l_request =
            new WEB3SuccOptionsOpenInputRequest();

        WEB3ToSuccOptionOpenContractInputService l_service =
            new WEB3ToSuccOptionOpenContractInputServiceImplForMock();
        try
        {
            Services.unregisterService(WEB3ToSuccOptionOpenContractInputService.class);

            WEB3SuccOptionsOpenInputResponse l_response =
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
            Services.registerService(WEB3ToSuccOptionOpenContractInputService.class, l_service);
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

        WEB3SuccOptionsOpenInputRequest l_request =
            new WEB3SuccOptionsOpenInputRequest();

        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionOpenContractInputServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                            "パラメータ値不正。"));
            WEB3SuccOptionsOpenInputResponse l_response =
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

        WEB3SuccOptionsOpenInputRequest l_request =
            new WEB3SuccOptionsOpenInputRequest();

        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionOpenContractInputServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                            "パラメータ値不正。"));

            WEB3SuccOptionsOpenInputResponse l_response =
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

        WEB3SuccOptionsOpenInputRequest l_request =
            new WEB3SuccOptionsOpenInputRequest();

        try
        {
            WEB3SuccOptionsOpenInputResponse l_inputResponse = new WEB3SuccOptionsOpenInputResponse(l_request);
            l_inputResponse.marketList = new String[2];

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionOpenContractInputServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_inputResponse);

            WEB3SuccOptionsOpenInputResponse l_response =
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

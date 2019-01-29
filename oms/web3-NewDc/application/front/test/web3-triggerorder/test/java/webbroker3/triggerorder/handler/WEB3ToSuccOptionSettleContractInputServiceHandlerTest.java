head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.31.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccOptionSettleContractInputServiceHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3ToSuccOptionSettleContractInputServiceHandlerTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/21 トウ鋒鋼 (中訊) 新規作成
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseInputRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionSettleContractInputService;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccOptionSettleContractInputServiceHandlerTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionSettleContractInputServiceHandlerTest.class);

    WEB3ToSuccOptionSettleContractInputService l_service = null;
    
    public WEB3ToSuccOptionSettleContractInputServiceHandlerTest(String arg0)
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

    public void testGetSettleContractInputScreen_0001()
    {
        final String STR_METHOD_NAME = "testGetSettleContractInputScreen_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_service =
                (WEB3ToSuccOptionSettleContractInputService)Services.getService(
                    WEB3ToSuccOptionSettleContractInputService.class);
            
            Services.unregisterService(WEB3ToSuccOptionSettleContractInputService.class);
            
            WEB3ToSuccOptionSettleContractInputServiceHandler l_handler =
                new WEB3ToSuccOptionSettleContractInputServiceHandler();
            WEB3SuccOptionsCloseInputRequest l_request = new WEB3SuccOptionsCloseInputRequest();
            WEB3SuccOptionsCloseInputResponse l_response = l_handler.getSettleContractInputScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3ToSuccOptionSettleContractInputService.class, l_service);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetSettleContractInputScreen_0002()
    {
        final String STR_METHOD_NAME = "testGetSettleContractInputScreen_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractInputServiceImpl",
                "execute", new Class[]
                {WEB3GenRequest.class},
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    "パラメータ値不正。"));
            
            WEB3ToSuccOptionSettleContractInputServiceHandler l_handler =
                new WEB3ToSuccOptionSettleContractInputServiceHandler();
            WEB3SuccOptionsCloseInputRequest l_request = new WEB3SuccOptionsCloseInputRequest();
            WEB3SuccOptionsCloseInputResponse l_response = l_handler.getSettleContractInputScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetSettleContractInputScreen_0003()
    {
        final String STR_METHOD_NAME = "testGetSettleContractInputScreen_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractInputServiceImpl",
                "execute", new Class[]
                {WEB3GenRequest.class},
                new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    "パラメータタイプ不正。"));
            
            WEB3ToSuccOptionSettleContractInputServiceHandler l_handler =
                new WEB3ToSuccOptionSettleContractInputServiceHandler();
            WEB3SuccOptionsCloseInputRequest l_request = new WEB3SuccOptionsCloseInputRequest();
            WEB3SuccOptionsCloseInputResponse l_response = l_handler.getSettleContractInputScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetSettleContractInputScreen_0004()
    {
        final String STR_METHOD_NAME = "testGetSettleContractInputScreen_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3SuccOptionsCloseInputResponse l_expectedResponse = new WEB3SuccOptionsCloseInputResponse();
            //執行条件一覧
            l_expectedResponse.execCondList = new String[]{WEB3ExecutionConditionDef.NO_CONDITION};
            //発注条件区分一覧
            l_expectedResponse.orderCondTypeList = new String[]{WEB3OrderingConditionDef.DEFAULT};
            //Ｗ指値用執行条件一覧
            l_expectedResponse.wlimitExecCondList = null;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractInputServiceImpl",
                "execute", new Class[]
                {WEB3GenRequest.class},
                l_expectedResponse);
            
            WEB3ToSuccOptionSettleContractInputServiceHandler l_handler =
                new WEB3ToSuccOptionSettleContractInputServiceHandler();
            WEB3SuccOptionsCloseInputRequest l_request = new WEB3SuccOptionsCloseInputRequest();
            WEB3SuccOptionsCloseInputResponse l_response = l_handler.getSettleContractInputScreen(l_request);
            
            assertEquals("1", l_response.execCondList[0]);
            assertEquals("0", l_response.orderCondTypeList[0]);
            assertNull(l_response.wlimitExecCondList);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@

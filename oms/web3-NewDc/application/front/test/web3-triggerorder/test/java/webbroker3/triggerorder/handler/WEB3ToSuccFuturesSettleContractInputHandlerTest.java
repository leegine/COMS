head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.31.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccFuturesSettleContractInputHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �iWEB3ToSuccFuturesSettleContractInputHandlerTest.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/26 �g�E�N�| (���u) �V�K�쐬
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
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseInputRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesSettleContractInputService;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccFuturesSettleContractInputHandlerTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesSettleContractInputHandlerTest.class);
    
    WEB3ToSuccFuturesSettleContractInputService l_service = null;

    public WEB3ToSuccFuturesSettleContractInputHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
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
                (WEB3ToSuccFuturesSettleContractInputService)Services.getService(
                    WEB3ToSuccFuturesSettleContractInputService.class);
            
            Services.unregisterService(WEB3ToSuccFuturesSettleContractInputService.class);
            
            WEB3ToSuccFuturesSettleContractInputHandler l_handler =
                new WEB3ToSuccFuturesSettleContractInputHandler();
            WEB3SuccFuturesCloseInputRequest l_request = new WEB3SuccFuturesCloseInputRequest();
            WEB3SuccFuturesCloseInputResponse l_response = l_handler.getSettleContractInputScreen(l_request);
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
            Services.registerService(WEB3ToSuccFuturesSettleContractInputService.class, l_service);
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
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesSettleContractInputServiceImpl",
                "execute", new Class[]
                {WEB3GenRequest.class},
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    "�p�����[�^�l�s���B"));
            
            WEB3ToSuccFuturesSettleContractInputHandler l_handler =
                new WEB3ToSuccFuturesSettleContractInputHandler();
            WEB3SuccFuturesCloseInputRequest l_request = new WEB3SuccFuturesCloseInputRequest();
            WEB3SuccFuturesCloseInputResponse l_response = l_handler.getSettleContractInputScreen(l_request);
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
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesSettleContractInputServiceImpl",
                "execute", new Class[]
                {WEB3GenRequest.class},
                new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    "�p�����[�^�^�C�v�s���B"));
            
            WEB3ToSuccFuturesSettleContractInputHandler l_handler =
                new WEB3ToSuccFuturesSettleContractInputHandler();
            WEB3SuccFuturesCloseInputRequest l_request = new WEB3SuccFuturesCloseInputRequest();
            WEB3SuccFuturesCloseInputResponse l_response = l_handler.getSettleContractInputScreen(l_request);
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
            WEB3SuccFuturesCloseInputResponse l_expectedResponse = new WEB3SuccFuturesCloseInputResponse();
            //���s�����ꗗ
            l_expectedResponse.execCondList = new String[]{WEB3ExecutionConditionDef.NO_CONDITION};
            //���������敪�ꗗ
            l_expectedResponse.orderCondTypeList = new String[]{WEB3OrderingConditionDef.DEFAULT};
            //�v�w�l�p���s�����ꗗ
            l_expectedResponse.wlimitExecCondList = null;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesSettleContractInputServiceImpl",
                "execute", new Class[]
                {WEB3GenRequest.class},
                l_expectedResponse);
            
            WEB3ToSuccFuturesSettleContractInputHandler l_handler =
                new WEB3ToSuccFuturesSettleContractInputHandler();
            WEB3SuccFuturesCloseInputRequest l_request = new WEB3SuccFuturesCloseInputRequest();
            WEB3SuccFuturesCloseInputResponse l_response = l_handler.getSettleContractInputScreen(l_request);
            
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
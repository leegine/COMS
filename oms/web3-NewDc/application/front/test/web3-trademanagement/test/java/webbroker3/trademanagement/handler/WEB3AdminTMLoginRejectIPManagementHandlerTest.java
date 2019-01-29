head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.32.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTMLoginRejectIPManagementHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (WEB3AdminTMLoginRejectIPManagementHandlerTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/26 ���z(���u) �V�K�쐬
*/
package webbroker3.trademanagement.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlListRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlListResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateInputResponse;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginRejectIPManagementService;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTMLoginRejectIPManagementHandlerTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMLoginRejectIPManagementHandlerTest.class);
    
    private WEB3AdminTMLoginRejectIPManagementHandler l_handler = null;

    private WEB3AdminTMLoginRejectIPManagementService l_service = null;

    public WEB3AdminTMLoginRejectIPManagementHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_handler = new WEB3AdminTMLoginRejectIPManagementHandler();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.trademanagement.handler.WEB3AdminTMLoginRejectIPManagementHandler.getListScreen(WEB3AdminTraderAdminIPControlListRequest)'
     */
    
    //(get�ꗗ���)
    //�Ǘ��҃��O�C������IP�Ǘ��T�[�r�XImpl�̎擾�Ɏ��s���܂����B
    public void testGetListScreen_C0001()
    {
        final String STR_METHOD_NAME = "testGetListScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlListRequest l_request =
                new WEB3AdminTraderAdminIPControlListRequest();
            WEB3AdminTraderAdminIPControlListResponse l_response = null;
            
            l_service =
                (WEB3AdminTMLoginRejectIPManagementService)Services.getService(
                    WEB3AdminTMLoginRejectIPManagementService.class);
            Services.unregisterService(WEB3AdminTMLoginRejectIPManagementService.class);
            
            l_response = l_handler.getListScreen(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminTMLoginRejectIPManagementService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //(get�ꗗ���)
    //���O�C������IP�ꗗ��ʕ\�������̎��{�Ɏ��s���܂����B
    //excute() throw WEB3BaseException
    public void testGetListScreen_C0002()
    {
        final String STR_METHOD_NAME = "testGetListScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlListRequest l_request =
                new WEB3AdminTraderAdminIPControlListRequest();
            WEB3AdminTraderAdminIPControlListResponse l_response = null;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,"�p�����[�^�^�C�v�s���B"));
            
            l_response = l_handler.getListScreen(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get�ꗗ���)
    //���O�C������IP�ꗗ��ʕ\�������̎��{�Ɏ��s���܂����B
    //excute() throw WEB3BaseRuntimeException
    public void testGetListScreen_C0003()
    {
        final String STR_METHOD_NAME = "testGetListScreen_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlListRequest l_request =
                new WEB3AdminTraderAdminIPControlListRequest();
            WEB3AdminTraderAdminIPControlListResponse l_response = null;
            
            WEB3BaseRuntimeException l_runtimeException =
                new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80008, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                l_runtimeException);
            
            l_response = l_handler.getListScreen(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80008, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get�ꗗ���)
    //����I��
    public void testGetListScreen_C0004()
    {
        final String STR_METHOD_NAME = "testGetListScreen_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlListRequest l_request =
                new WEB3AdminTraderAdminIPControlListRequest();
            WEB3AdminTraderAdminIPControlListResponse l_response = null;
            
            WEB3AdminTraderAdminIPControlListResponse l_expectedResponse =
                new WEB3AdminTraderAdminIPControlListResponse();
            l_expectedResponse.errorMessage = "OK";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                l_expectedResponse);
            
            l_response = l_handler.getListScreen(l_request);
            
            assertEquals("OK", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.trademanagement.handler.WEB3AdminTMLoginRejectIPManagementHandler.getRegistScreen(WEB3AdminTraderAdminIPControlRegistInputRequest)'
     */
    
    //(get�o�^���)
    //�Ǘ��҃��O�C������IP�Ǘ��T�[�r�XImpl�̎擾�Ɏ��s���܂����B
    public void testGetRegistScreen_C0001()
    {
        final String STR_METHOD_NAME = "testGetRegistScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlRegistInputRequest l_request =
                new WEB3AdminTraderAdminIPControlRegistInputRequest();
            WEB3AdminTraderAdminIPControlRegistInputResponse l_response = null;
            
            l_service =
                (WEB3AdminTMLoginRejectIPManagementService)Services.getService(
                    WEB3AdminTMLoginRejectIPManagementService.class);
            Services.unregisterService(WEB3AdminTMLoginRejectIPManagementService.class);
            
            l_response = l_handler.getRegistScreen(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminTMLoginRejectIPManagementService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //(get�o�^���)
    //���O�C������IP�o�^��ʕ\�������̎��{�Ɏ��s���܂����B
    //excute() throw WEB3BaseException
    public void testGetRegistScreen_C0002()
    {
        final String STR_METHOD_NAME = "testGetRegistScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlRegistInputRequest l_request =
                new WEB3AdminTraderAdminIPControlRegistInputRequest();
            WEB3AdminTraderAdminIPControlRegistInputResponse l_response = null;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,"�p�����[�^�^�C�v�s���B"));
            
            l_response = l_handler.getRegistScreen(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get�o�^���)
    //���O�C������IP�o�^��ʕ\�������̎��{�Ɏ��s���܂����B
    //excute() throw WEB3BaseRuntimeException
    public void testGetRegistScreen_C0003()
    {
        final String STR_METHOD_NAME = "testGetRegistScreen_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlRegistInputRequest l_request =
                new WEB3AdminTraderAdminIPControlRegistInputRequest();
            WEB3AdminTraderAdminIPControlRegistInputResponse l_response = null;
            
            WEB3BaseRuntimeException l_runtimeException =
                new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80008, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                l_runtimeException);
            
            l_response = l_handler.getRegistScreen(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80008, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get�o�^���)
    //����I��
    public void testGetRegistScreen_C0004()
    {
        final String STR_METHOD_NAME = "testGetRegistScreen_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlRegistInputRequest l_request =
                new WEB3AdminTraderAdminIPControlRegistInputRequest();
            WEB3AdminTraderAdminIPControlRegistInputResponse l_response = null;
            
            WEB3AdminTraderAdminIPControlRegistInputResponse l_expectedResponse =
                new WEB3AdminTraderAdminIPControlRegistInputResponse();
            l_expectedResponse.errorMessage = "OK";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                l_expectedResponse);
            
            l_response = l_handler.getRegistScreen(l_request);
            
            assertEquals("OK", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.trademanagement.handler.WEB3AdminTMLoginRejectIPManagementHandler.validateRegist(WEB3AdminTraderAdminIPControlRegistConfirmRequest)'
     */

    //(validate�o�^)
    //�Ǘ��҃��O�C������IP�Ǘ��T�[�r�XImpl�̎擾�Ɏ��s���܂����B
    public void testValidateRegist_C0001()
    {
        final String STR_METHOD_NAME = "testValidateRegist_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlRegistConfirmRequest l_request =
                new WEB3AdminTraderAdminIPControlRegistConfirmRequest();
            WEB3AdminTraderAdminIPControlRegistConfirmResponse l_response = null;
            
            l_service =
                (WEB3AdminTMLoginRejectIPManagementService)Services.getService(
                    WEB3AdminTMLoginRejectIPManagementService.class);
            Services.unregisterService(WEB3AdminTMLoginRejectIPManagementService.class);
            
            l_response = l_handler.validateRegist(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminTMLoginRejectIPManagementService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //(validate�o�^)
    //���O�C������IP�o�^�m�F�����̎��{�Ɏ��s���܂����B
    //excute() throw WEB3BaseException
    public void testValidateRegist_C0002()
    {
        final String STR_METHOD_NAME = "testValidateRegist_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlRegistConfirmRequest l_request =
                new WEB3AdminTraderAdminIPControlRegistConfirmRequest();
            WEB3AdminTraderAdminIPControlRegistConfirmResponse l_response = null;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,"�p�����[�^�^�C�v�s���B"));
            
            l_response = l_handler.validateRegist(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(validate�o�^)
    //���O�C������IP�o�^�m�F�����̎��{�Ɏ��s���܂����B
    //excute() throw WEB3BaseRuntimeException
    public void testValidateRegist_C0003()
    {
        final String STR_METHOD_NAME = "testValidateRegist_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlRegistConfirmRequest l_request =
                new WEB3AdminTraderAdminIPControlRegistConfirmRequest();
            WEB3AdminTraderAdminIPControlRegistConfirmResponse l_response = null;
            
            WEB3BaseRuntimeException l_runtimeException =
                new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80008, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                l_runtimeException);
            
            l_response = l_handler.validateRegist(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80008, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(validate�o�^)
    //����I��
    public void testValidateRegist_C0004()
    {
        final String STR_METHOD_NAME = "testValidateRegist_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlRegistConfirmRequest l_request =
                new WEB3AdminTraderAdminIPControlRegistConfirmRequest();
            WEB3AdminTraderAdminIPControlRegistConfirmResponse l_response = null;
            
            WEB3AdminTraderAdminIPControlRegistConfirmResponse l_expectedResponse =
                new WEB3AdminTraderAdminIPControlRegistConfirmResponse();
            l_expectedResponse.errorMessage = "OK";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                l_expectedResponse);
            
            l_response = l_handler.validateRegist(l_request);
            
            assertEquals("OK", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.trademanagement.handler.WEB3AdminTMLoginRejectIPManagementHandler.submitRegist(WEB3AdminTraderAdminIPControlRegistCompleteRequest)'
     */

    //(submit�o�^)
    //�Ǘ��҃��O�C������IP�Ǘ��T�[�r�XImpl�̎擾�Ɏ��s���܂����B
    public void testSubmitRegist_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitRegist_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlRegistCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlRegistCompleteRequest();
            WEB3AdminTraderAdminIPControlRegistCompleteResponse l_response = null;
            
            l_service =
                (WEB3AdminTMLoginRejectIPManagementService)Services.getService(
                    WEB3AdminTMLoginRejectIPManagementService.class);
            Services.unregisterService(WEB3AdminTMLoginRejectIPManagementService.class);
            
            l_response = l_handler.submitRegist(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminTMLoginRejectIPManagementService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //(submit�o�^)
    //���O�C������IP�o�^���������̎��{�Ɏ��s���܂����B
    //excute() throw WEB3BaseException
    public void testSubmitRegist_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitRegist_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlRegistCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlRegistCompleteRequest();
            WEB3AdminTraderAdminIPControlRegistCompleteResponse l_response = null;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,"�p�����[�^�^�C�v�s���B"));
            
            l_response = l_handler.submitRegist(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(submit�o�^)
    //���O�C������IP�o�^���������̎��{�Ɏ��s���܂����B
    //excute() throw WEB3BaseRuntimeException
    public void testSubmitRegist_C0003()
    {
        final String STR_METHOD_NAME = "testSubmitRegist_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlRegistCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlRegistCompleteRequest();
            WEB3AdminTraderAdminIPControlRegistCompleteResponse l_response = null;
            
            WEB3BaseRuntimeException l_runtimeException =
                new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80008, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                l_runtimeException);
            
            l_response = l_handler.submitRegist(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80008, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(submit�o�^)
    //����I��
    public void testSubmitRegist_C0004()
    {
        final String STR_METHOD_NAME = "testSubmitRegist_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlRegistCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlRegistCompleteRequest();
            WEB3AdminTraderAdminIPControlRegistCompleteResponse l_response = null;
            
            WEB3AdminTraderAdminIPControlRegistCompleteResponse l_expectedResponse =
                new WEB3AdminTraderAdminIPControlRegistCompleteResponse();
            l_expectedResponse.errorMessage = "OK";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                l_expectedResponse);
            
            l_response = l_handler.submitRegist(l_request);
            
            assertEquals("OK", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.trademanagement.handler.WEB3AdminTMLoginRejectIPManagementHandler.getChangedScreen(WEB3AdminTraderAdminIPControlUpdateInputRequest)'
     */

    //(get�ύX���)
    //�Ǘ��҃��O�C������IP�Ǘ��T�[�r�XImpl�̎擾�Ɏ��s���܂����B
    public void testGetChangedScreen_C0001()
    {
        final String STR_METHOD_NAME = "testGetChangedScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlUpdateInputRequest l_request =
                new WEB3AdminTraderAdminIPControlUpdateInputRequest();
            WEB3AdminTraderAdminIPControlUpdateInputResponse l_response = null;
            
            l_service =
                (WEB3AdminTMLoginRejectIPManagementService)Services.getService(
                    WEB3AdminTMLoginRejectIPManagementService.class);
            Services.unregisterService(WEB3AdminTMLoginRejectIPManagementService.class);
            
            l_response = l_handler.getChangedScreen(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminTMLoginRejectIPManagementService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //(get�ύX���)
    //���O�C������IP�o�^���ύX��ʕ\�������̎��{�Ɏ��s���܂����B
    //excute() throw WEB3BaseException
    public void testGetChangedScreen_C0002()
    {
        final String STR_METHOD_NAME = "testGetChangedScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlUpdateInputRequest l_request =
                new WEB3AdminTraderAdminIPControlUpdateInputRequest();
            WEB3AdminTraderAdminIPControlUpdateInputResponse l_response = null;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,"�p�����[�^�^�C�v�s���B"));
            
            l_response = l_handler.getChangedScreen(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get�ύX���)
    //���O�C������IP�o�^���ύX��ʕ\�������̎��{�Ɏ��s���܂����B
    //excute() throw WEB3BaseRuntimeException
    public void testGetChangedScreen_C0003()
    {
        final String STR_METHOD_NAME = "testGetChangedScreen_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlUpdateInputRequest l_request =
                new WEB3AdminTraderAdminIPControlUpdateInputRequest();
            WEB3AdminTraderAdminIPControlUpdateInputResponse l_response = null;
            
            WEB3BaseRuntimeException l_runtimeException =
                new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80008, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                l_runtimeException);
            
            l_response = l_handler.getChangedScreen(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80008, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get�ύX���)
    //����I��
    public void testGetChangedScreen_C0004()
    {
        final String STR_METHOD_NAME = "testGetChangedScreen_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlUpdateInputRequest l_request =
                new WEB3AdminTraderAdminIPControlUpdateInputRequest();
            WEB3AdminTraderAdminIPControlUpdateInputResponse l_response = null;
            
            WEB3AdminTraderAdminIPControlUpdateInputResponse l_expectedResponse =
                new WEB3AdminTraderAdminIPControlUpdateInputResponse();
            l_expectedResponse.errorMessage = "OK";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                l_expectedResponse);
            
            l_response = l_handler.getChangedScreen(l_request);
            
            assertEquals("OK", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * Test method for 'webbroker3.trademanagement.handler.WEB3AdminTMLoginRejectIPManagementHandler.validateChange(WEB3AdminTraderAdminIPControlUpdateConfirmRequest)'
     */
    
    //(validate�ύX)
    //�Ǘ��҃��O�C������IP�Ǘ��T�[�r�XImpl�̎擾�Ɏ��s���܂����B
    public void testValidateChange_C0001()
    {
        final String STR_METHOD_NAME = "testValidateChange_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlUpdateConfirmRequest l_request =
                new WEB3AdminTraderAdminIPControlUpdateConfirmRequest();
            WEB3AdminTraderAdminIPControlUpdateConfirmResponse l_response = null;
            
            l_service =
                (WEB3AdminTMLoginRejectIPManagementService)Services.getService(
                    WEB3AdminTMLoginRejectIPManagementService.class);
            Services.unregisterService(WEB3AdminTMLoginRejectIPManagementService.class);
            
            l_response = l_handler.validateChange(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminTMLoginRejectIPManagementService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //(validate�ύX)
    //���O�C������IP�o�^���ύX�m�F�����̎��{�Ɏ��s���܂����B
    //excute() throw WEB3BaseException
    public void testValidateChange_C0002()
    {
        final String STR_METHOD_NAME = "testValidateChange_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlUpdateConfirmRequest l_request =
                new WEB3AdminTraderAdminIPControlUpdateConfirmRequest();
            WEB3AdminTraderAdminIPControlUpdateConfirmResponse l_response = null;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,"�p�����[�^�^�C�v�s���B"));
            
            l_response = l_handler.validateChange(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(validate�ύX)
    //���O�C������IP�o�^���ύX�m�F�����̎��{�Ɏ��s���܂����B
    //excute() throw WEB3BaseRuntimeException
    public void testValidateChange_C0003()
    {
        final String STR_METHOD_NAME = "testValidateChange_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlUpdateConfirmRequest l_request =
                new WEB3AdminTraderAdminIPControlUpdateConfirmRequest();
            WEB3AdminTraderAdminIPControlUpdateConfirmResponse l_response = null;
            
            WEB3BaseRuntimeException l_runtimeException =
                new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80008, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                l_runtimeException);
            
            l_response = l_handler.validateChange(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80008, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(validate�ύX)
    //����I��
    public void testValidateChange_C0004()
    {
        final String STR_METHOD_NAME = "testValidateChange_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlUpdateConfirmRequest l_request =
                new WEB3AdminTraderAdminIPControlUpdateConfirmRequest();
            WEB3AdminTraderAdminIPControlUpdateConfirmResponse l_response = null;
            
            WEB3AdminTraderAdminIPControlUpdateConfirmResponse l_expectedResponse =
                new WEB3AdminTraderAdminIPControlUpdateConfirmResponse();
            l_expectedResponse.errorMessage = "OK";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                l_expectedResponse);
            
            l_response = l_handler.validateChange(l_request);
            
            assertEquals("OK", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.trademanagement.handler.WEB3AdminTMLoginRejectIPManagementHandler.submitChange(WEB3AdminTraderAdminIPControlUpdateCompleteRequest)'
     */

    //(submit�ύX)
    //�Ǘ��҃��O�C������IP�Ǘ��T�[�r�XImpl�̎擾�Ɏ��s���܂����B
    public void testSubmitChange_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitChange_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlUpdateCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlUpdateCompleteRequest();
            WEB3AdminTraderAdminIPControlUpdateCompleteResponse l_response = null;
            
            l_service =
                (WEB3AdminTMLoginRejectIPManagementService)Services.getService(
                    WEB3AdminTMLoginRejectIPManagementService.class);
            Services.unregisterService(WEB3AdminTMLoginRejectIPManagementService.class);
            
            l_response = l_handler.submitChange(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminTMLoginRejectIPManagementService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //(submit�ύX)
    //���O�C������IP�o�^���ύX���������̎��{�Ɏ��s���܂����B
    //excute() throw WEB3BaseException
    public void testSubmitChange_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitChange_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlUpdateCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlUpdateCompleteRequest();
            WEB3AdminTraderAdminIPControlUpdateCompleteResponse l_response = null;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,"�p�����[�^�^�C�v�s���B"));
            
            l_response = l_handler.submitChange(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(submit�ύX)
    //���O�C������IP�o�^���ύX���������̎��{�Ɏ��s���܂����B
    //excute() throw WEB3BaseRuntimeException
    public void testSubmitChange_C0003()
    {
        final String STR_METHOD_NAME = "testSubmitChange_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlUpdateCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlUpdateCompleteRequest();
            WEB3AdminTraderAdminIPControlUpdateCompleteResponse l_response = null;
            
            WEB3BaseRuntimeException l_runtimeException =
                new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80008, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                l_runtimeException);
            
            l_response = l_handler.submitChange(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80008, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(submit�ύX)
    //����I��
    public void testSubmitChange_C0004()
    {
        final String STR_METHOD_NAME = "testSubmitChange_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlUpdateCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlUpdateCompleteRequest();
            WEB3AdminTraderAdminIPControlUpdateCompleteResponse l_response = null;
            
            WEB3AdminTraderAdminIPControlUpdateCompleteResponse l_expectedResponse =
                new WEB3AdminTraderAdminIPControlUpdateCompleteResponse();
            l_expectedResponse.errorMessage = "OK";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                l_expectedResponse);
            
            l_response = l_handler.submitChange(l_request);
            
            assertEquals("OK", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.trademanagement.handler.WEB3AdminTMLoginRejectIPManagementHandler.validateDelete(WEB3AdminTraderAdminIPControlDeleteConfirmRequest)'
     */

    //(validate�폜)
    //�Ǘ��҃��O�C������IP�Ǘ��T�[�r�XImpl�̎擾�Ɏ��s���܂����B
    public void testValidateDelete_C0001()
    {
        final String STR_METHOD_NAME = "testValidateDelete_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlDeleteConfirmRequest l_request =
                new WEB3AdminTraderAdminIPControlDeleteConfirmRequest();
            WEB3AdminTraderAdminIPControlDeleteConfirmResponse l_response = null;
            
            l_service =
                (WEB3AdminTMLoginRejectIPManagementService)Services.getService(
                    WEB3AdminTMLoginRejectIPManagementService.class);
            Services.unregisterService(WEB3AdminTMLoginRejectIPManagementService.class);
            
            l_response = l_handler.validateDelete(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminTMLoginRejectIPManagementService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //(validate�폜)
    //���O�C������IP�o�^���폜�m�F�����̎��{�Ɏ��s���܂����B
    //excute() throw WEB3BaseException
    public void testValidateDelete_C0002()
    {
        final String STR_METHOD_NAME = "testValidateDelete_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlDeleteConfirmRequest l_request =
                new WEB3AdminTraderAdminIPControlDeleteConfirmRequest();
            WEB3AdminTraderAdminIPControlDeleteConfirmResponse l_response = null;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,"�p�����[�^�^�C�v�s���B"));
            
            l_response = l_handler.validateDelete(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(validate�폜)
    //���O�C������IP�o�^���폜�m�F�����̎��{�Ɏ��s���܂����B
    //excute() throw WEB3BaseRuntimeException
    public void testValidateDelete_C0003()
    {
        final String STR_METHOD_NAME = "testValidateDelete_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlDeleteConfirmRequest l_request =
                new WEB3AdminTraderAdminIPControlDeleteConfirmRequest();
            WEB3AdminTraderAdminIPControlDeleteConfirmResponse l_response = null;
            
            WEB3BaseRuntimeException l_runtimeException =
                new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80008, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                l_runtimeException);
            
            l_response = l_handler.validateDelete(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80008, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(validate�폜)
    //����I��
    public void testValidateDelete_C0004()
    {
        final String STR_METHOD_NAME = "testValidateDelete_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlDeleteConfirmRequest l_request =
                new WEB3AdminTraderAdminIPControlDeleteConfirmRequest();
            WEB3AdminTraderAdminIPControlDeleteConfirmResponse l_response = null;
            
            WEB3AdminTraderAdminIPControlDeleteConfirmResponse l_expectedResponse =
                new WEB3AdminTraderAdminIPControlDeleteConfirmResponse();
            l_expectedResponse.errorMessage = "OK";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                l_expectedResponse);
            
            l_response = l_handler.validateDelete(l_request);
            
            assertEquals("OK", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * Test method for 'webbroker3.trademanagement.handler.WEB3AdminTMLoginRejectIPManagementHandler.submitDelete(WEB3AdminTraderAdminIPControlDeleteCompleteRequest)'
     */

    //(submit�폜)
    //�Ǘ��҃��O�C������IP�Ǘ��T�[�r�XImpl�̎擾�Ɏ��s���܂����B
    public void testSubmitDelete_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitDelete_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlDeleteCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlDeleteCompleteRequest();
            WEB3AdminTraderAdminIPControlDeleteCompleteResponse l_response = null;
            
            l_service =
                (WEB3AdminTMLoginRejectIPManagementService)Services.getService(
                    WEB3AdminTMLoginRejectIPManagementService.class);
            Services.unregisterService(WEB3AdminTMLoginRejectIPManagementService.class);
            
            l_response = l_handler.submitDelete(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminTMLoginRejectIPManagementService.class, l_service);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //(submit�폜)
    //���O�C������IP�o�^���폜���������̎��{�Ɏ��s���܂����B
    //excute() throw WEB3BaseException
    public void testSubmitDelete_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitDelete_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlDeleteCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlDeleteCompleteRequest();
            WEB3AdminTraderAdminIPControlDeleteCompleteResponse l_response = null;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,"�p�����[�^�^�C�v�s���B"));
            
            l_response = l_handler.submitDelete(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(submit�폜)
    //���O�C������IP�o�^���폜���������̎��{�Ɏ��s���܂����B
    //excute() throw WEB3BaseRuntimeException
    public void testSubmitDelete_C0003()
    {
        final String STR_METHOD_NAME = "testSubmitDelete_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlDeleteCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlDeleteCompleteRequest();
            WEB3AdminTraderAdminIPControlDeleteCompleteResponse l_response = null;
            
            WEB3BaseRuntimeException l_runtimeException =
                new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80008, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                l_runtimeException);
            
            l_response = l_handler.submitDelete(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80008, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(submit�폜)
    //����I��
    public void testSubmitDelete_C0004()
    {
        final String STR_METHOD_NAME = "testSubmitDelete_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlDeleteCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlDeleteCompleteRequest();
            WEB3AdminTraderAdminIPControlDeleteCompleteResponse l_response = null;
            
            WEB3AdminTraderAdminIPControlDeleteCompleteResponse l_expectedResponse =
                new WEB3AdminTraderAdminIPControlDeleteCompleteResponse();
            l_expectedResponse.errorMessage = "OK";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute",
                new Class[]{ WEB3GenRequest.class },
                l_expectedResponse);
            
            l_response = l_handler.submitDelete(l_request);
            
            assertEquals("OK", l_response.errorMessage);
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

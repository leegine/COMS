head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.14.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityPTSInputExecHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����iPTS�j�o�����̓n���h��Test(WEB3AdminEquityPTSInputExecHandlerTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/01 ��іQ(���u) �V�K�쐬
*/

package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSOrderDetailUnit;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityPTSInputExecService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityPTSInputExecHandlerTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityPTSInputExecHandlerTest.class);

    public WEB3AdminEquityPTSInputExecHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    /**
     * get���͉��
     * �Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X���擾�Ɏ��s���܂����B
     */
    public void testGetInputScreenCase0001()
    {
        final String STR_METHOD_NAME = "testGetInputScreenCase0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminEquityPTSInputExecService l_service =
            (WEB3AdminEquityPTSInputExecService)Services.getService(WEB3AdminEquityPTSInputExecService.class);
        try
        {
            Services.unregisterService(WEB3AdminEquityPTSInputExecService.class);
            
            WEB3AdminEquityPTSInputExecHandler l_handler = new WEB3AdminEquityPTSInputExecHandler();
            WEB3AdminEquityPTSInputExecInputRequest l_request = new WEB3AdminEquityPTSInputExecInputRequest();
            WEB3AdminEquityPTSInputExecInputResponse l_response =
                l_handler.getInputScreen(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminEquityPTSInputExecService.class, l_service);
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (get���͉��)<BR>
     * get���͉�ʂ̏����̎��{�Ɏ��s���܂����B<BR>
     * excute() throw WEB3BaseException<BR>
     */
    public void testGetInputScreenCase0002()
    {
        final String STR_METHOD_NAME = "testGetInputScreenCase0002()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSInputExecServiceImpl", 
                "execute",
                new Class[]{WEB3GenRequest.class},
                new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00005, ""));
            
            WEB3AdminEquityPTSInputExecHandler l_handler = new WEB3AdminEquityPTSInputExecHandler();
            WEB3AdminEquityPTSInputExecInputRequest l_request = new WEB3AdminEquityPTSInputExecInputRequest();
            WEB3AdminEquityPTSInputExecInputResponse l_response =
                l_handler.getInputScreen(l_request);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00005, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (get���͉��)<BR>
     * get���͉�ʂ̏����̎��{�Ɏ��s���܂����B<BR>
     * excute() throw WEB3BaseRuntimeException<BR>
     */
    public void testGetInputScreenCase0003()
    {
        final String STR_METHOD_NAME = "testGetInputScreenCase0003()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSInputExecServiceImpl", 
                "execute",
                new Class[]{WEB3GenRequest.class},
                new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00006, ""));
            
            WEB3AdminEquityPTSInputExecHandler l_handler = new WEB3AdminEquityPTSInputExecHandler();
            WEB3AdminEquityPTSInputExecInputRequest l_request = new WEB3AdminEquityPTSInputExecInputRequest();
            WEB3AdminEquityPTSInputExecInputResponse l_response =
                l_handler.getInputScreen(l_request);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00006, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (get���͉��)<BR>
     * ����I��<BR>
     */
    public void testGetInputScreenCase0004()
    {
        final String STR_METHOD_NAME = "testGetInputScreenCase0004()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3AdminEquityPTSOrderDetailUnit l_orderDetail = new WEB3AdminEquityPTSOrderDetailUnit();
            l_orderDetail.accountCode = "123";
            WEB3AdminEquityPTSInputExecInputResponse l_execInputResponse =
                new WEB3AdminEquityPTSInputExecInputResponse();
            l_execInputResponse.orderDetail = l_orderDetail;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSInputExecServiceImpl", 
                "execute",
                new Class[]{WEB3GenRequest.class},
                l_execInputResponse);
            
            WEB3AdminEquityPTSInputExecHandler l_handler = new WEB3AdminEquityPTSInputExecHandler();
            WEB3AdminEquityPTSInputExecInputRequest l_request = new WEB3AdminEquityPTSInputExecInputRequest();
            
            WEB3AdminEquityPTSInputExecInputResponse l_response =
                l_handler.getInputScreen(l_request);

            assertEquals("123", l_response.orderDetail.accountCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate�o������
     * �Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X���擾�Ɏ��s���܂����B
     */
    public void testValidateInputExecCase0001()
    {
        final String STR_METHOD_NAME = "testValidateInputExecCase0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminEquityPTSInputExecService l_service =
            (WEB3AdminEquityPTSInputExecService)Services.getService(WEB3AdminEquityPTSInputExecService.class);
        try
        {
            Services.unregisterService(WEB3AdminEquityPTSInputExecService.class);
            
            WEB3AdminEquityPTSInputExecHandler l_handler = new WEB3AdminEquityPTSInputExecHandler();
            WEB3AdminEquityPTSInputExecConfirmRequest l_request = new WEB3AdminEquityPTSInputExecConfirmRequest();
            WEB3AdminEquityPTSInputExecConfirmResponse l_response =
                l_handler.validateInputExec(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminEquityPTSInputExecService.class, l_service);
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate�o������<BR>
     * �Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X�̊����iPTS�j�o�����͊m�F�����̎��{�Ɏ��s���܂���<BR>
     * excute() throw WEB3BaseException<BR>
     */
    public void testValidateInputExecCase0002()
    {
        final String STR_METHOD_NAME = "testValidateInputExecCase0002()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSInputExecServiceImpl", 
                "execute",
                new Class[]{WEB3GenRequest.class},
                new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00005, ""));
            
            WEB3AdminEquityPTSInputExecHandler l_handler = new WEB3AdminEquityPTSInputExecHandler();
            WEB3AdminEquityPTSInputExecConfirmRequest l_request = new WEB3AdminEquityPTSInputExecConfirmRequest();
            WEB3AdminEquityPTSInputExecConfirmResponse l_response =
                l_handler.validateInputExec(l_request);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00005, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate�o������<BR>
     * �Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X�̊����iPTS�j�o�����͊m�F�����̎��{�Ɏ��s���܂���<BR>
     * excute() throw WEB3BaseRuntimeException<BR>
     */
    public void testValidateInputExecCase0003()
    {
        final String STR_METHOD_NAME = "testValidateInputExecCase0003()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSInputExecServiceImpl", 
                "execute",
                new Class[]{WEB3GenRequest.class},
                new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00006, ""));
            
            WEB3AdminEquityPTSInputExecHandler l_handler = new WEB3AdminEquityPTSInputExecHandler();
            WEB3AdminEquityPTSInputExecConfirmRequest l_request = new WEB3AdminEquityPTSInputExecConfirmRequest();
            WEB3AdminEquityPTSInputExecConfirmResponse l_response =
                l_handler.validateInputExec(l_request);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00006, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate�o������<BR>
     * ����I��<BR>
     */
    public void testValidateInputExecCase0004()
    {
        final String STR_METHOD_NAME = "testValidateInputExecCase0004()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3AdminEquityPTSOrderDetailUnit l_orderDetail = new WEB3AdminEquityPTSOrderDetailUnit();
            l_orderDetail.accountCode = "123";
            WEB3AdminEquityPTSInputExecConfirmResponse l_execConfirmResponse =
                new WEB3AdminEquityPTSInputExecConfirmResponse();
            l_execConfirmResponse.orderDetail = l_orderDetail;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSInputExecServiceImpl", 
                "execute",
                new Class[]{WEB3GenRequest.class},
                l_execConfirmResponse);
            
            WEB3AdminEquityPTSInputExecHandler l_handler = new WEB3AdminEquityPTSInputExecHandler();
            WEB3AdminEquityPTSInputExecConfirmRequest l_request = new WEB3AdminEquityPTSInputExecConfirmRequest();
            WEB3AdminEquityPTSInputExecConfirmResponse l_response =
                l_handler.validateInputExec(l_request);

            assertEquals("123", l_response.orderDetail.accountCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submit�o������
     * �Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X���擾�Ɏ��s���܂����B
     */
    public void testSubmitInputExecCase0001()
    {
        final String STR_METHOD_NAME = "testSubmitInputExecCase0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminEquityPTSInputExecService l_service =
            (WEB3AdminEquityPTSInputExecService)Services.getService(WEB3AdminEquityPTSInputExecService.class);
        try
        {
            Services.unregisterService(WEB3AdminEquityPTSInputExecService.class);
            
            WEB3AdminEquityPTSInputExecHandler l_handler = new WEB3AdminEquityPTSInputExecHandler();
            WEB3AdminEquityPTSInputExecCompleteRequest l_request = new WEB3AdminEquityPTSInputExecCompleteRequest();
            WEB3AdminEquityPTSInputExecCompleteResponse l_response =
                l_handler.submitInputExec(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminEquityPTSInputExecService.class, l_service);
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submit�o������<BR>
     * �Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X�̊����iPTS�j�o�����͊��������̎��{�Ɏ��s���܂����B
     * excute() throw WEB3BaseException<BR>
     */
    public void testSubmitInputExecCase0002()
    {
        final String STR_METHOD_NAME = "testValidateInputExecCase0002()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSInputExecServiceImpl", 
                "execute",
                new Class[]{WEB3GenRequest.class},
                new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00005, ""));
            
            WEB3AdminEquityPTSInputExecHandler l_handler = new WEB3AdminEquityPTSInputExecHandler();
            WEB3AdminEquityPTSInputExecCompleteRequest l_request = new WEB3AdminEquityPTSInputExecCompleteRequest();
            WEB3AdminEquityPTSInputExecCompleteResponse l_response =
                l_handler.submitInputExec(l_request);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00005, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submit�o������<BR>
     * �Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X�̊����iPTS�j�o�����͊��������̎��{�Ɏ��s���܂����B
     * excute() throw WEB3BaseRuntimeException<BR>
     */
    public void testSubmitInputExecCase0003()
    {
        final String STR_METHOD_NAME = "testSubmitInputExecCase0003()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSInputExecServiceImpl", 
                "execute",
                new Class[]{WEB3GenRequest.class},
                new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00006, ""));
            
            WEB3AdminEquityPTSInputExecHandler l_handler = new WEB3AdminEquityPTSInputExecHandler();
            WEB3AdminEquityPTSInputExecCompleteRequest l_request = new WEB3AdminEquityPTSInputExecCompleteRequest();
            WEB3AdminEquityPTSInputExecCompleteResponse l_response =
                l_handler.submitInputExec(l_request);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00006, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate�o������<BR>
     * ����I��<BR>
     */
    public void testSubmitInputExecCase0004()
    {
        final String STR_METHOD_NAME = "testSubmitInputExecCase0004()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3AdminEquityPTSOrderDetailUnit l_orderDetail = new WEB3AdminEquityPTSOrderDetailUnit();
            l_orderDetail.accountCode = "123";
            WEB3AdminEquityPTSInputExecCompleteResponse l_execCompleteResponse =
                new WEB3AdminEquityPTSInputExecCompleteResponse();
            l_execCompleteResponse.orderDetail = l_orderDetail;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSInputExecServiceImpl", 
                "execute",
                new Class[]{WEB3GenRequest.class},
                l_execCompleteResponse);
            
            WEB3AdminEquityPTSInputExecHandler l_handler = new WEB3AdminEquityPTSInputExecHandler();
            WEB3AdminEquityPTSInputExecCompleteRequest l_request = new WEB3AdminEquityPTSInputExecCompleteRequest();
            WEB3AdminEquityPTSInputExecCompleteResponse l_response =
                l_handler.submitInputExec(l_request);

            assertEquals("123", l_response.orderDetail.accountCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@

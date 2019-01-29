head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.25.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAioSLProductCancelHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۖ�������n���h���N���X(WEB3AdminAioSLProductCancelHandlerTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 ���^�] (���u) �V�K�쐬
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.aio.message.WEB3AdminSLProductCancelCompleteRequest;
import webbroker3.aio.message.WEB3AdminSLProductCancelCompleteResponse;
import webbroker3.aio.message.WEB3AdminSLProductCancelConfirmRequest;
import webbroker3.aio.message.WEB3AdminSLProductCancelConfirmResponse;
import webbroker3.aio.message.WEB3SLProductInfoUnit;
import webbroker3.aio.service.delegate.WEB3AdminAioSLProductCancelService;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductCancelServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductCancelServiceImplForMock;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAioSLProductCancelHandlerTest extends TestBaseForMock
{
    private WEB3AdminAioSLProductCancelHandler l_handler = null;
    
    private WEB3AdminAioSLProductCancelService l_service = null;
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3AdminAioSLProductCancelHandlerTest.class);

    public WEB3AdminAioSLProductCancelHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        this.l_handler = new WEB3AdminAioSLProductCancelHandler();
        Services.overrideService(WEB3AdminAioSLProductCancelService.class,
            new WEB3AdminAioSLProductCancelServiceImplForMock());
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.l_handler = null;
    }

    /**
     * �S�ۖ�������T�[�r�X���擾�Ɏ��s���܂����B
     * 
     * �e�o�ُ�FSYSTEM_ERROR_80002
     */
    public void testValidateSLProductCancel_0001()
    {
        String STR_METHOD_NAME = "testValidateSLProductCancel_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(false);
            l_service = (WEB3AdminAioSLProductCancelService)Services.getService(
                    WEB3AdminAioSLProductCancelService.class);
            
            Services.unregisterService(WEB3AdminAioSLProductCancelService.class);
            
            WEB3AdminSLProductCancelConfirmRequest l_request =
                new WEB3AdminSLProductCancelConfirmRequest();
            
            WEB3AdminSLProductCancelConfirmResponse l_response =
                this.l_handler.validateSLProductCancel(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminAioSLProductCancelService.class, l_service);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �S�ۖ�������m�F��ʕ\���Ɏ��s���܂����B
     * 
     * �e�o�ُ�FSYSTEM_ERROR_80017
     */
    public void testValidateSLProductCancel_0002()
    {
        String STR_METHOD_NAME = "testValidateSLProductCancel_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductCancelServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    new WEB3BaseException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,"�S�ۖ�������m�F��ʕ\���Ɏ��s���܂����B"));
            
            WEB3AdminSLProductCancelConfirmRequest l_request =
                new WEB3AdminSLProductCancelConfirmRequest();
            
            WEB3AdminSLProductCancelConfirmResponse l_response =
                this.l_handler.validateSLProductCancel(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ����?��
     */
    public void testValidateSLProductCancel_0003()
    {
        String STR_METHOD_NAME = "testValidateSLProductCancel_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminSLProductCancelConfirmResponse l_expectedResponse =
                new WEB3AdminSLProductCancelConfirmResponse();
            
            l_expectedResponse.stockLoanProductInfo =new WEB3SLProductInfoUnit();
            l_expectedResponse.stockLoanProductInfo.productCode = "0D";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductCancelServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_expectedResponse);
            
            WEB3AdminSLProductCancelConfirmRequest l_request =
                new WEB3AdminSLProductCancelConfirmRequest();
            
            WEB3AdminSLProductCancelConfirmResponse l_response =
                this.l_handler.validateSLProductCancel(l_request);
            
            assertEquals("0D", l_response.stockLoanProductInfo.productCode);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �S�ۖ�������T�[�r�X���擾�Ɏ��s���܂����B
     * 
     * �e�o�ُ�FSYSTEM_ERROR_80002
     */
    public void testSubmitSLProductCancel_0001()
    {
        String STR_METHOD_NAME = "testSubmitSLProductCancel_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(false);
            l_service = (WEB3AdminAioSLProductCancelService)Services.getService(
                    WEB3AdminAioSLProductCancelService.class);
            
            Services.unregisterService(WEB3AdminAioSLProductCancelService.class);
            
            WEB3AdminSLProductCancelCompleteRequest l_request =
                new WEB3AdminSLProductCancelCompleteRequest();
            
            WEB3AdminSLProductCancelCompleteResponse l_response =
                this.l_handler.submitSLProductCancel(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminAioSLProductCancelService.class, l_service);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �S�ۖ������������ʕ\���Ɏ��s���܂����B
     * 
     * �e�o�ُ�FSYSTEM_ERROR_80017
     */
    public void testSubmitSLProductCancel_0002()
    {
        String STR_METHOD_NAME = "testSubmitSLProductCancel_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductCancelServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    new WEB3BaseException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,"�S�ۖ�������m�F��ʕ\���Ɏ��s���܂����B"));
            
            WEB3AdminSLProductCancelCompleteRequest l_request =
                new WEB3AdminSLProductCancelCompleteRequest();
            
            WEB3AdminSLProductCancelCompleteResponse l_response =
                this.l_handler.submitSLProductCancel(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ����?��
     */
    public void testSubmitSLProductCancel_0003()
    {
        String STR_METHOD_NAME = "testSubmitSLProductCancel_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminSLProductCancelCompleteResponse l_expectedResponse =
                new WEB3AdminSLProductCancelCompleteResponse();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductCancelServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_expectedResponse);
            
            WEB3AdminSLProductCancelCompleteRequest l_request =
                new WEB3AdminSLProductCancelCompleteRequest();
            
            WEB3AdminSLProductCancelCompleteResponse l_response =
                this.l_handler.submitSLProductCancel(l_request);
            
            assertEquals(WEB3AdminSLProductCancelCompleteResponse.class, l_response.getClass());
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@

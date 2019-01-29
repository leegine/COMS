head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.34.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoDepositTransitionReferenceHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3IfoDepositCalcResultSaveHandlerTest
Author Name      : Daiwa Institute of Research
Revision History : 2008/08/26 �����΁i���u�j�V�K�쐬
*/
package webbroker3.ifodeposit.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifodeposit.message.WEB3IfoDepositTransitionReferenceRequest;
import webbroker3.ifodeposit.message.WEB3IfoDepositTransitionReferenceResponse;
import webbroker3.ifodeposit.service.delegate.WEB3IfoDepositTransitionReferenceService;
import webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositTransitionReferenceServiceImpl;
import webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositTransitionReferenceServiceImplForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoDepositTransitionReferenceHandlerTest extends TestBaseForMock
{

    public WEB3IfoDepositTransitionReferenceHandlerTest(String arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility l_log = WEB3LogUtility.getInstance(
            WEB3IfoDepositTransitionReferenceHandlerTest.class);
    WEB3IfoDepositTransitionReferenceHandler l_ifoDepositTransitionReferenceHandler =null;
    protected void setUp() throws Exception
    {
        super.setUp();
        l_ifoDepositTransitionReferenceHandler = new WEB3IfoDepositTransitionReferenceHandler();
    }
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //�؋������ډ�ʕ\���T�[�r�X���擾�ł��܂���ł����B
    public void testIfoDepositTransitionReferenceRequest_C0001()
    {
        final String STR_METHOD_NAME = "testIfoDepositTransitionReferenceRequest_C0001()";
        l_log.debug(TEST_START + STR_METHOD_NAME);
        WEB3IfoDepositTransitionReferenceRequest l_request = new WEB3IfoDepositTransitionReferenceRequest();
        try
        {
            Services.unregisterService(WEB3IfoDepositTransitionReferenceService.class);
            WEB3IfoDepositTransitionReferenceResponse l_response =
                l_ifoDepositTransitionReferenceHandler.ifoDepositTransitionReferenceRequest(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            l_log.error(l_ex.getMessage(), l_ex);
            l_log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(
                    WEB3IfoDepositTransitionReferenceService.class,
                    new  WEB3IfoDepositTransitionReferenceServiceImplForMock());
        }
        l_log.debug(TEST_END + STR_METHOD_NAME);
    }
    //�؋������ډ�ʕ\���Ɏ��s���܂����B
    public void testIfoDepositTransitionReferenceRequest_C0002()
    {

        final String STR_METHOD_NAME = "testIfoDepositTransitionReferenceRequest_C0002()";
        l_log.debug(TEST_START + STR_METHOD_NAME);
        WEB3IfoDepositTransitionReferenceRequest l_request = new WEB3IfoDepositTransitionReferenceRequest();

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3BaseException l_Exception=new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80025,null,"�����ΏۊO�B");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositTransitionReferenceServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_Exception);

            WEB3IfoDepositTransitionReferenceResponse l_response =
                l_ifoDepositTransitionReferenceHandler.ifoDepositTransitionReferenceRequest(l_request);
                assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80025, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            l_log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        l_log.debug(TEST_END + STR_METHOD_NAME);
    }
    //�؋������ډ�ʕ\���Ɏ��s���܂����B
    public void testIfoDepositTransitionReferenceRequest_C0003()
    {

        final String STR_METHOD_NAME = "testIfoDepositTransitionReferenceRequest_C0003()";
        l_log.debug(TEST_START + STR_METHOD_NAME);
        WEB3IfoDepositTransitionReferenceRequest l_request = new WEB3IfoDepositTransitionReferenceRequest();

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3BaseRuntimeException l_Exception=new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80025,null,"�����ΏۊO�B");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositTransitionReferenceServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_Exception);

            WEB3IfoDepositTransitionReferenceResponse l_response =
                l_ifoDepositTransitionReferenceHandler.ifoDepositTransitionReferenceRequest(l_request);
                assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80025, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            l_log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        l_log.debug(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIfoDepositTransitionReferenceRequest_C0004()
    {

        final String STR_METHOD_NAME = "testIfoDepositTransitionReferenceRequest_C0004()";
        l_log.debug(TEST_START + STR_METHOD_NAME);
        WEB3IfoDepositTransitionReferenceRequest l_request = new WEB3IfoDepositTransitionReferenceRequest();

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3IfoDepositTransitionReferenceResponse l_Response=new WEB3IfoDepositTransitionReferenceResponse();
            l_Response.errorMessage = "123";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositTransitionReferenceServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_Response);
            WEB3IfoDepositTransitionReferenceResponse l_response =
                l_ifoDepositTransitionReferenceHandler.ifoDepositTransitionReferenceRequest(l_request);
            assertEquals("123", l_response.errorMessage);
        }
            catch (Exception l_ex)
            {
                l_log.error(STR_METHOD_NAME, l_ex);
                fail();
            }
            l_log.debug(TEST_END + STR_METHOD_NAME);
        }

}
@

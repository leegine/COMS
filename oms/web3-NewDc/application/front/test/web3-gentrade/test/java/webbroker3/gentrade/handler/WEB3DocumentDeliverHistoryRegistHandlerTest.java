head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.15.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3DocumentDeliverHistoryRegistHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3DocumentDeliverHistoryRegistHandlerTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/07 �����΁i���u�j�V�K�쐬
*/
package webbroker3.gentrade.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.message.WEB3DocumentDeliverHistoryRegistRequest;
import webbroker3.gentrade.message.WEB3DocumentDeliverHistoryRegistResponse;
import webbroker3.gentrade.service.delegate.WEB3DocumentDeliverHistoryRegistService;
import webbroker3.gentrade.service.delegate.stdimpls.WEB3DocumentDeliverHistoryRegistServiceImpl;
import webbroker3.gentrade.service.delegate.stdimpls.WEB3DocumentDeliverHistoryRegistServiceImplForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3DocumentDeliverHistoryRegistHandlerTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility l_log = WEB3LogUtility.getInstance(WEB3DocumentDeliverHistoryRegistHandlerTest.class);

    private WEB3DocumentDeliverHistoryRegistHandler l_handler = new WEB3DocumentDeliverHistoryRegistHandler() ;

    public WEB3DocumentDeliverHistoryRegistHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    //  ���ʌ�t����o�^�T�[�r�X���擾
    public void testdocumentDeliverHistoryRegist_C0001()
    {

        final String STR_METHOD_NAME = "testdocumentDeliverHistoryRegist_C0001()";
        l_log.debug(TEST_START + STR_METHOD_NAME);

        WEB3DocumentDeliverHistoryRegistRequest l_request = new WEB3DocumentDeliverHistoryRegistRequest();
        try
        {
            Services.unregisterService(WEB3DocumentDeliverHistoryRegistService.class);
            WEB3DocumentDeliverHistoryRegistResponse l_response = l_handler.documentDeliverHistoryRegist(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            l_log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(
                    WEB3DocumentDeliverHistoryRegistService.class,
                new  WEB3DocumentDeliverHistoryRegistServiceImplForMock());
        }

        l_log.debug(TEST_END + STR_METHOD_NAME);
    }
    //  ���ʌ�t����o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
    public void testdocumentDeliverHistoryRegist_C0002()
    {

        final String STR_METHOD_NAME = "testdocumentDeliverHistoryRegist_C0002()";
        l_log.debug(TEST_START + STR_METHOD_NAME);

        WEB3DocumentDeliverHistoryRegistRequest l_request=new WEB3DocumentDeliverHistoryRegistRequest();
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3DocumentDeliverHistoryRegistResponse l_Response = new WEB3DocumentDeliverHistoryRegistResponse();
            l_Response.errorMessage = "1243";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3DocumentDeliverHistoryRegistServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_Response);
                WEB3DocumentDeliverHistoryRegistResponse l_response = l_handler.documentDeliverHistoryRegist(l_request);
                assertEquals("1243", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            l_log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        l_log.debug(TEST_END + STR_METHOD_NAME);
    }
    //  ���ʌ�t����o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
    public void testdocumentDeliverHistoryRegist_C0003()
    {
        final String STR_METHOD_NAME = "testdocumentDeliverHistoryRegist_C0003()";
        l_log.debug(TEST_START + STR_METHOD_NAME);

        WEB3DocumentDeliverHistoryRegistRequest l_request=new WEB3DocumentDeliverHistoryRegistRequest();
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3BaseException l_Exception = new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_01801,null, "��t���ԊO�G���[�B");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3DocumentDeliverHistoryRegistServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_Exception);
                WEB3DocumentDeliverHistoryRegistResponse l_response = l_handler.documentDeliverHistoryRegist(l_request);
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01801, l_response.errorInfo);
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

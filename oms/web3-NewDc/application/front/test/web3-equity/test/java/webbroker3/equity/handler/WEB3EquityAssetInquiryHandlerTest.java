head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.54.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityAssetInquiryHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3EquityAssetInquiryHandlerTest.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2008/07/04 �k�v�u (���u) �V�K�쐬
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.message.WEB3EquitySellListRequest;
import webbroker3.equity.message.WEB3EquitySellListResponse;
import webbroker3.equity.service.delegate.WEB3EquityAssetInquiryService;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityAssetInquiryServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityAssetInquiryHandlerTest extends TestBaseForMock {

	/**
     *�@@���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityAssetInquiryHandlerTest.class);
    
    WEB3EquityAssetInquiryHandler l_handler = null;
    
	public WEB3EquityAssetInquiryHandlerTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		MOCK_MANAGER.setIsMockUsed(true);
		l_handler= new WEB3EquityAssetInquiryHandler();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
		
	// execute()���\�b�h���R�[������B
	//WEB3BaseException
	public void testAssetInquiryRequest_C0001()
	{
		final String STR_METHOD_NAME = "testAssetInquiryRequest_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	WEB3BaseException l_exception=new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, "SYSTEM_ERROR_80002");
        	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityAssetInquiryServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class},
                    l_exception);
        	
        	WEB3EquitySellListRequest l_requestData= new WEB3EquitySellListRequest();
        	WEB3EquitySellListResponse l_response=l_handler.assetInquiryRequest(l_requestData);
        	assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002,l_response.errorInfo);
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//execute()���\�b�h���R�[������B
	//WEB3BaseRuntimeException
	public void testAssetInquiryRequest_C0002()
	{
		final String STR_METHOD_NAME = "testAssetInquiryRequest_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	WEB3BaseRuntimeException l_exception=new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, "SYSTEM_ERROR_80003");
        	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityAssetInquiryServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class},
                    l_exception);
        	
        	WEB3EquitySellListRequest l_requestData= new WEB3EquitySellListRequest();
        	WEB3EquitySellListResponse l_response=l_handler.assetInquiryRequest(l_requestData);
        	assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003,l_response.errorInfo);
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}	

	//correctcase
	public void testAssetInquiryRequest_C0003()
	{
		final String STR_METHOD_NAME = "testAssetInquiryRequest_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	WEB3EquitySellListResponse l_lisresponse= new WEB3EquitySellListResponse();
        	l_lisresponse.pageIndex="11";
        	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityAssetInquiryServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class},
                    l_lisresponse);
        	
        	WEB3EquitySellListRequest l_requestData= new WEB3EquitySellListRequest();
        	WEB3EquitySellListResponse l_response=l_handler.assetInquiryRequest(l_requestData);
        	assertEquals("11",l_response.pageIndex);
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}	

	// ���t�ꗗ�T�[�r�X���擾
	//���t�ꗗ�T�[�r�X�擾�Ɏ��s���܂���
	public void testAssetInquiryRequest_C0004()
	{
		final String STR_METHOD_NAME = "testAssetInquiryRequest_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	Services.unregisterService(WEB3EquityAssetInquiryService.class);
        	WEB3EquitySellListRequest l_requestData= new WEB3EquitySellListRequest();
        	WEB3EquitySellListResponse l_response=l_handler.assetInquiryRequest(l_requestData);
        	assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002,l_response.errorInfo);
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally {
			Services.registerService(WEB3EquityAssetInquiryService.class,
					new WEB3EquityAssetInquiryServiceImpl());
		}
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.54.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityBalanceReferenceHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3EquityBalanceReferenceHandlerTest.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2008/07/09 楊夫志 (中訊) 新規作成
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.message.WEB3EquityBalanceReferenceRequest;
import webbroker3.equity.message.WEB3EquityBalanceReferenceResponse;
import webbroker3.equity.message.WEB3EquityBalanceReferenceTotalRequest;
import webbroker3.equity.message.WEB3EquityBalanceReferenceTotalResponse;
import webbroker3.equity.message.WEB3EquitySellListRequest;
import webbroker3.equity.message.WEB3EquitySellListResponse;
import webbroker3.equity.service.delegate.WEB3EquityAssetInquiryService;
import webbroker3.equity.service.delegate.WEB3EquityBalanceReferenceService;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityAssetInquiryServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityBalanceReferenceServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityBalanceReferenceHandlerTest extends TestBaseForMock {

	/**
     *　@ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityBalanceReferenceHandlerTest.class);
    
    WEB3EquityBalanceReferenceHandler l_handler = null;
    
	public WEB3EquityBalanceReferenceHandlerTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		MOCK_MANAGER.setIsMockUsed(true);
		l_handler = new WEB3EquityBalanceReferenceHandler();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	//execute()メソッドをコールする。
	//WEB3BaseException
	public void testGetBalanceReference_C0001()
	{
		final String STR_METHOD_NAME = "testGetBalanceReference_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	WEB3BaseException l_exception=new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, "SYSTEM_ERROR_80002");
        	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityBalanceReferenceServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class},
                    l_exception);
        	
        	WEB3EquityBalanceReferenceRequest l_request= new WEB3EquityBalanceReferenceRequest();
        	WEB3EquityBalanceReferenceResponse l_response=l_handler.getBalanceReference(l_request);
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

	//execute()メソッドをコールする。
	//WEB3BaseRuntimeException
	public void testGetBalanceReference_C0002()
	{
		final String STR_METHOD_NAME = "testGetBalanceReference_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	WEB3BaseRuntimeException l_exception=new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, "SYSTEM_ERROR_80003");
        	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityBalanceReferenceServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class},
                    l_exception);
        	
        	WEB3EquityBalanceReferenceRequest l_request= new WEB3EquityBalanceReferenceRequest();
        	WEB3EquityBalanceReferenceResponse l_response=l_handler.getBalanceReference(l_request);
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
	public void testGetBalanceReference_C0003()
	{
		final String STR_METHOD_NAME = "testGetBalanceReference_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	WEB3EquityBalanceReferenceResponse l_lisresponse= new WEB3EquityBalanceReferenceResponse();
        	l_lisresponse.pageIndex="11";
        	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityBalanceReferenceServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class},
                    l_lisresponse);
        	
        	WEB3EquityBalanceReferenceRequest l_request= new WEB3EquityBalanceReferenceRequest();
        	WEB3EquityBalanceReferenceResponse l_response=l_handler.getBalanceReference(l_request);
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

	//execute()メソッドをコールする。
	//WEB3BaseException
	public void testGetBalanceTotal_C0001()
	{
		final String STR_METHOD_NAME = "testGetBalanceTotal_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	WEB3BaseException l_exception=new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, "SYSTEM_ERROR_80002");
        	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityBalanceReferenceServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class},
                    l_exception);
        	
        	WEB3EquityBalanceReferenceTotalRequest l_request= new WEB3EquityBalanceReferenceTotalRequest();
        	WEB3EquityBalanceReferenceTotalResponse l_response=l_handler.getBalanceTotal(l_request);
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

	//execute()メソッドをコールする。
	//WEB3BaseRuntimeException
	public void testGetBalanceTotal_C0002()
	{
		final String STR_METHOD_NAME = "testGetBalanceTotal_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	WEB3BaseRuntimeException l_exception=new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, "SYSTEM_ERROR_80003");
        	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityBalanceReferenceServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class},
                    l_exception);
        	
        	WEB3EquityBalanceReferenceTotalRequest l_request= new WEB3EquityBalanceReferenceTotalRequest();
        	WEB3EquityBalanceReferenceTotalResponse l_response=l_handler.getBalanceTotal(l_request);
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
	public void testGetBalanceTotal_C0003()
	{
		final String STR_METHOD_NAME = "testGetBalanceTotal_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	WEB3EquityBalanceReferenceTotalResponse l_lisresponse= new WEB3EquityBalanceReferenceTotalResponse();
        	l_lisresponse.capitalGainTotalAsset="1111";
        	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityBalanceReferenceServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class},
                    l_lisresponse);
        	
        	WEB3EquityBalanceReferenceTotalRequest l_request= new WEB3EquityBalanceReferenceTotalRequest();
        	WEB3EquityBalanceReferenceTotalResponse l_response=l_handler.getBalanceTotal(l_request);
        	assertEquals("1111",l_response.capitalGainTotalAsset);
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}	

	// 現物株式残高照会サービスを取得
	//現物株式残高照会サービスの取得に失敗しました。
	public void testGetBalanceReference_C0004()
	{
		final String STR_METHOD_NAME = "testGetBalanceReference_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	Services.unregisterService(WEB3EquityBalanceReferenceService.class);
        	WEB3EquityBalanceReferenceRequest l_request= new WEB3EquityBalanceReferenceRequest();
        	WEB3EquityBalanceReferenceResponse l_response=l_handler.getBalanceReference(l_request);
        	assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002,l_response.errorInfo);
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally {
			Services.registerService(WEB3EquityBalanceReferenceService.class,
					new WEB3EquityBalanceReferenceServiceImpl());
		}
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}
	
	// 現物株式残高照会サービスを取得
	//現物株式残高照会サービスの取得に失敗しました。
	public void testGetBalanceTotal_C0004()
	{
		final String STR_METHOD_NAME = "testGetBalanceTotal_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	Services.unregisterService(WEB3EquityBalanceReferenceService.class);
        	WEB3EquityBalanceReferenceTotalRequest l_request= new WEB3EquityBalanceReferenceTotalRequest();
        	WEB3EquityBalanceReferenceTotalResponse l_response=l_handler.getBalanceTotal(l_request);
        	assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002,l_response.errorInfo);
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally {
			Services.registerService(WEB3EquityBalanceReferenceService.class,
					new WEB3EquityBalanceReferenceServiceImpl());
		}
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}
}
@

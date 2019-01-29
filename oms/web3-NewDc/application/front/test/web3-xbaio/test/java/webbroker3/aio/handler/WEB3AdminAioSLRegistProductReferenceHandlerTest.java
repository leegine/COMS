head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.25.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAioSLRegistProductReferenceHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保登録銘柄照会ハンドラクラス(WEB3AdminAioSLRegistProductReferenceHandlerTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/09/27 孟亞南 (中訊) 新規作成
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.aio.message.WEB3AdminSLProductChangeInputRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegiListRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegiListResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioSLRegistProductReferenceService;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLRegistProductReferenceServiceImpl;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAioSLRegistProductReferenceHandlerTest extends TestBaseForMock
{

	public WEB3AdminAioSLRegistProductReferenceHandlerTest(String name)
	{
		super(name);
	}
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLRegistProductReferenceHandlerTest.class);


	/**
	 * 担保登録銘柄照会サービス
	 *
	 */
	public void test_getSLRegiProductList_0001()
	{
        String STR_METHOD_NAME = "test_getSLRegiProductList_0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        Services.unregisterService(WEB3AdminAioSLRegistProductReferenceService.class);

        WEB3AdminAioSLRegistProductReferenceHandler l_handler =
        	new WEB3AdminAioSLRegistProductReferenceHandler();
        WEB3AdminSLProductRegiListResponse l_response =
        	new WEB3AdminSLProductRegiListResponse();
        WEB3AdminSLProductRegiListRequest l_request =
        	new WEB3AdminSLProductRegiListRequest();

        l_response = l_handler.getSLRegiProductList(l_request);

        assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002 , l_response.errorInfo);

        Services.registerService(WEB3AdminAioSLRegistProductReferenceService.class,
            new WEB3AdminAioSLRegistProductReferenceServiceImpl());

        log.exiting(STR_METHOD_NAME);
	}
	
	/**
	 * 担保登録銘柄照会サービス
	 *
	 */
	public void test_getSLRegiProductList_0002()
	{
        String STR_METHOD_NAME = "test_getSLRegiProductList_0002";
        log.entering(TEST_START + STR_METHOD_NAME);

        Services.unregisterService(WEB3AdminAioSLRegistProductReferenceService.class);
        Services.registerService(WEB3AdminAioSLRegistProductReferenceService.class,
            new WEB3AdminAioSLRegistProductReferenceServiceImplForTest());
        WEB3AdminAioSLRegistProductReferenceHandler l_handler =
        	new WEB3AdminAioSLRegistProductReferenceHandler();
        WEB3AdminSLProductRegiListResponse l_response =
        	new WEB3AdminSLProductRegiListResponse();
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();

        l_response = l_handler.getSLRegiProductList(l_request);

        assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017 , l_response.errorInfo);
        Services.unregisterService(WEB3AdminAioSLRegistProductReferenceService.class);
        Services.registerService(WEB3AdminAioSLRegistProductReferenceService.class,
            new WEB3AdminAioSLRegistProductReferenceServiceImpl());

        log.exiting(STR_METHOD_NAME);
	}
	
	/**
	 * 担保登録銘柄照会サービス
	 *
	 */
	public void test_getSLRegiProductList_0003()
	{
        String STR_METHOD_NAME = "test_getSLRegiProductList_0003";
        log.entering(TEST_START + STR_METHOD_NAME);

        Services.unregisterService(WEB3AdminAioSLRegistProductReferenceService.class);
        Services.registerService(WEB3AdminAioSLRegistProductReferenceService.class,
            new WEB3AdminAioSLRegistProductReferenceServiceImplForTest());
        WEB3AdminAioSLRegistProductReferenceHandler l_handler =
        	new WEB3AdminAioSLRegistProductReferenceHandler();
        WEB3AdminSLProductRegiListResponse l_response =
        	new WEB3AdminSLProductRegiListResponse();
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        l_request.pageIndex = "1";

        l_response = l_handler.getSLRegiProductList(l_request);

        assertNull(l_response.errorInfo);
        Services.unregisterService(WEB3AdminAioSLRegistProductReferenceService.class);
        Services.registerService(WEB3AdminAioSLRegistProductReferenceService.class,
            new WEB3AdminAioSLRegistProductReferenceServiceImpl());

        log.exiting(STR_METHOD_NAME);
	}
	
	private class WEB3AdminAioSLRegistProductReferenceServiceImplForTest extends WEB3AdminAioSLRegistProductReferenceServiceImpl
	{
	    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
	    {
	    	
	    	if ("1".equals(((WEB3AdminSLProductRegiListRequest)l_request).pageIndex))
			{
	    		WEB3AdminSLProductRegiListResponse l_response =
	    			(WEB3AdminSLProductRegiListResponse)l_request.createResponse();
	    		return l_response;
	    	}
	    	else
	    	{
	            throw new WEB3SystemLayerException(
	                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
	                    this.getClass().getName() + ".",
	                    "パラメータ値不正。");
	    	}
	    }
	}
}
@

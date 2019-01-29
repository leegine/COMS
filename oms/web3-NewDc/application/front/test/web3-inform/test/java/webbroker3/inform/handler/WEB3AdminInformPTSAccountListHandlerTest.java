head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.16.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformPTSAccountListHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListInquiryRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListInquiryResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListResultRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListResultResponse;
import webbroker3.inform.service.delegate.WEB3AdminInformPTSAccountListService;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformPTSAccountListServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminInformPTSAccountListHandlerTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformPTSAccountListHandlerTest.class);

    public WEB3AdminInformPTSAccountListHandlerTest(String arg0)
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

    public void testDisplaySearchScreen_0001()
    {
        final String STR_METHOD_NAME = "testDisplaySearchScreen_0001()";
        log.entering(STR_METHOD_NAME);

        Services.unregisterService(WEB3AdminInformPTSAccountListService.class);
        
        WEB3AdminInformPTSAccountListHandler handler = new WEB3AdminInformPTSAccountListHandler();
        WEB3AdminInformPTSAccountListInquiryRequest request = new WEB3AdminInformPTSAccountListInquiryRequest();
        WEB3AdminInformPTSAccountListInquiryResponse response = handler.displaySearchScreen(request);
        
        assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, response.errorInfo);
        
        Services.registerService(WEB3AdminInformPTSAccountListService.class, new WEB3AdminInformPTSAccountListServiceImpl());
        log.exiting(STR_METHOD_NAME);
    }

    public void testDisplaySearchScreen_0002()
    {
        final String STR_METHOD_NAME = "testDisplaySearchScreen_0002()";
        log.entering(STR_METHOD_NAME);

        Services.overrideService(WEB3AdminInformPTSAccountListService.class, new WEB3AdminInformPTSAccountListServiceImplForMock());
        
        WEB3AdminInformPTSAccountListHandler handler = new WEB3AdminInformPTSAccountListHandler();
        WEB3AdminInformPTSAccountListInquiryRequest request = new WEB3AdminInformPTSAccountListInquiryRequest();
        WEB3AdminInformPTSAccountListInquiryResponse response = handler.displaySearchScreen(request);
        
        assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00938, response.errorInfo);
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testDisplaySearchScreen_0003()
    {
        final String STR_METHOD_NAME = "testDisplaySearchScreen_0003()";
        log.entering(STR_METHOD_NAME);

        Services.overrideService(WEB3AdminInformPTSAccountListService.class, new WEB3AdminInformPTSAccountListServiceImplForMock2());
        
        WEB3AdminInformPTSAccountListHandler handler = new WEB3AdminInformPTSAccountListHandler();
        WEB3AdminInformPTSAccountListInquiryRequest request = new WEB3AdminInformPTSAccountListInquiryRequest();
        WEB3AdminInformPTSAccountListInquiryResponse response = handler.displaySearchScreen(request);
        
        assertEquals(null, response.errorInfo);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testDisplaySearchResultScreen_0001()
    {
        final String STR_METHOD_NAME = "testDisplaySearchResultScreen_0001()";
        log.entering(STR_METHOD_NAME);

        Services.unregisterService(WEB3AdminInformPTSAccountListService.class);
        
        WEB3AdminInformPTSAccountListHandler handler = new WEB3AdminInformPTSAccountListHandler();
        WEB3AdminInformPTSAccountListResultRequest request = new WEB3AdminInformPTSAccountListResultRequest();
        WEB3AdminInformPTSAccountListResultResponse response = handler.displaySearchResultScreen(request);
        
        assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, response.errorInfo);
        
        Services.registerService(WEB3AdminInformPTSAccountListService.class, new WEB3AdminInformPTSAccountListServiceImpl());
        log.exiting(STR_METHOD_NAME);
    }

    public void testDisplaySearchResultScreen_0002()
    {
        final String STR_METHOD_NAME = "testDisplaySearchResultScreen_0002()";
        log.entering(STR_METHOD_NAME);

        Services.overrideService(WEB3AdminInformPTSAccountListService.class, new WEB3AdminInformPTSAccountListServiceImplForMock());
        
        WEB3AdminInformPTSAccountListHandler handler = new WEB3AdminInformPTSAccountListHandler();
        WEB3AdminInformPTSAccountListResultRequest request = new WEB3AdminInformPTSAccountListResultRequest();
        WEB3AdminInformPTSAccountListResultResponse response = handler.displaySearchResultScreen(request);
        
        assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00938, response.errorInfo);
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testDisplaySearchResultScreen_0003()
    {
        final String STR_METHOD_NAME = "testDisplaySearchResultScreen_0003()";
        log.entering(STR_METHOD_NAME);

        Services.overrideService(WEB3AdminInformPTSAccountListService.class, new WEB3AdminInformPTSAccountListServiceImplForMock3());
        
        WEB3AdminInformPTSAccountListHandler handler = new WEB3AdminInformPTSAccountListHandler();
        WEB3AdminInformPTSAccountListResultRequest request = new WEB3AdminInformPTSAccountListResultRequest();
        WEB3AdminInformPTSAccountListResultResponse response = handler.displaySearchResultScreen(request);
        
        assertEquals(null, response.errorInfo);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    private class WEB3AdminInformPTSAccountListServiceImplForMock extends WEB3AdminInformPTSAccountListServiceImpl
    {
        /**
         * 管理者PTS申込客一覧問合せサービス処理を行う。<BR>
         * @@param l_request - (リクエストデータ)<BR>
         * リクエストデータ<BR>
         * @@return WEB3GenResponse
         * @@throws WEB3BaseException
         * @@roseuid 47B537740302
         */
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00938,
                this.getClass().getName(),
                "パラメータ値不正。");
        }
    }

    private class WEB3AdminInformPTSAccountListServiceImplForMock2 extends WEB3AdminInformPTSAccountListServiceImpl
    {
        /**
         * 管理者PTS申込客一覧問合せサービス処理を行う。<BR>
         * @@param l_request - (リクエストデータ)<BR>
         * リクエストデータ<BR>
         * @@return WEB3GenResponse
         * @@throws WEB3BaseException
         * @@roseuid 47B537740302
         */
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            return new WEB3AdminInformPTSAccountListInquiryResponse();
        }
    }

    private class WEB3AdminInformPTSAccountListServiceImplForMock3 extends WEB3AdminInformPTSAccountListServiceImpl
    {
        /**
         * 管理者PTS申込客一覧問合せサービス処理を行う。<BR>
         * @@param l_request - (リクエストデータ)<BR>
         * リクエストデータ<BR>
         * @@return WEB3GenResponse
         * @@throws WEB3BaseException
         * @@roseuid 47B537740302
         */
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            return new WEB3AdminInformPTSAccountListResultResponse();
        }
    }
}
@

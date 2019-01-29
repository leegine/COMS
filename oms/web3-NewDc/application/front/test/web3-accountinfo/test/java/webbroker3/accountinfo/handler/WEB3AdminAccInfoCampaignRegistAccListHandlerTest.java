head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.40.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AdminAccInfoCampaignRegistAccListHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignRegistAccListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignRegistAccListResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignRegistAccListService;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccInfoCampaignRegistAccListHandlerTest extends TestBaseForMock 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignRegistAccListHandlerTest.class);
    
    private WEB3AdminAccInfoCampaignRegistAccListHandler l_handler = null;
    
    private WEB3AdminAccInfoCampaignRegistAccListService l_service = null;
    
    private WEB3AdminAccInfoCampaignRegistAccListResponse l_response = null;
    
    private WEB3AdminAccInfoCampaignRegistAccListRequest l_request = null;
    
    public WEB3AdminAccInfoCampaignRegistAccListHandlerTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_handler = new WEB3AdminAccInfoCampaignRegistAccListHandler();
        
    }
    
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void test_listScreenShow_C0001()
    {
        final String STR_METHOD_NAME = "test_listScreenShow_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3AdminAccInfoCampaignRegistAccListRequest l_request1=  new WEB3AdminAccInfoCampaignRegistAccListRequest();
            l_response = this.l_handler.listScreenShow(l_request1);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02224, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_listScreenShow_C0002()
    {
        final String STR_METHOD_NAME = "test_listScreenShow_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            l_service = (WEB3AdminAccInfoCampaignRegistAccListService)Services.getService(WEB3AdminAccInfoCampaignRegistAccListService.class);
            Services.unregisterService(WEB3AdminAccInfoCampaignRegistAccListService.class);
            WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
            l_response = this.l_handler.listScreenShow(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminAccInfoCampaignRegistAccListService.class,l_service);
            Services.addInterceptor(
                WEB3AdminAccInfoCampaignRegistAccListService.class, 
                new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_listScreenShow_C0003()
    {
        final String STR_METHOD_NAME = "test_listScreenShow_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        WEB3AdminAccInfoCampaignRegistAccListResponse l_expectResponse = 
            new WEB3AdminAccInfoCampaignRegistAccListResponse(l_request);
        l_expectResponse.totalPages = "60";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignRegistAccListServiceImpl",
            "execute",
            new Class[] {WEB3GenRequest.class}, 
                l_expectResponse);
        try
        {
            l_response = this.l_handler.listScreenShow(l_request);
            assertNotNull(l_response);
            assertEquals(WEB3AdminAccInfoCampaignRegistAccListResponse.class, l_response.getClass());
            assertEquals("60", l_response.totalPages);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@

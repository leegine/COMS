head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.14.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityAttentionInfoNotifyHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3AdminEquityAttentionInfoNotifyHandlerTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/01/08 張少傑(中訊) 新規作成 モデルNo.219
*/

package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityAttentionInfoNotifyRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityAttentionInfoNotifyResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoNotifyService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityAttentionInfoNotifyHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAttentionInfoNotifyHandlerTest.class);

    WEB3AdminEquityAttentionInfoNotifyService l_service = null; 
    public WEB3AdminEquityAttentionInfoNotifyHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    public void testAttentionInfoNotifyRequest_C001()
    {
        final String STR_METHOD_NAME = "testAttentionInfoNotifyRequest_C001";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_service =
                (WEB3AdminEquityAttentionInfoNotifyService)Services.getService(
                WEB3AdminEquityAttentionInfoNotifyService.class);
            Services.unregisterService(WEB3AdminEquityAttentionInfoNotifyService.class);
            
            WEB3AdminEquityAttentionInfoNotifyHandler l_handler =
                new WEB3AdminEquityAttentionInfoNotifyHandler();
            
            WEB3AdminEquityAttentionInfoNotifyRequest l_request =
                new WEB3AdminEquityAttentionInfoNotifyRequest();
            
            WEB3AdminEquityAttentionInfoNotifyResponse l_response =
                l_handler.attentionInfoNotifyRequest(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminEquityAttentionInfoNotifyService.class, l_service);
        }
        log.exiting(STR_METHOD_NAME);
    }
    public void testAttentionInfoNotifyRequest_C002()
    {
        final String STR_METHOD_NAME = "testAttentionInfoNotifyRequest_C002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminEquityAttentionInfoNotifyRequest l_request = new WEB3AdminEquityAttentionInfoNotifyRequest();
        WEB3AdminEquityAttentionInfoNotifyResponse l_response = null;
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3AdminEquityAttentionInfoNotifyHandler l_handler =
                new WEB3AdminEquityAttentionInfoNotifyHandler();
            l_service =
                (WEB3AdminEquityAttentionInfoNotifyService)Services.getService(
                        WEB3AdminEquityAttentionInfoNotifyService.class);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyServiceImpl", 
                    "execute",
                    new Class[]
                    { WEB3BackRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME));
            l_response = l_handler.attentionInfoNotifyRequest(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public void testAttentionInfoNotifyRequest_C003()
    {
        final String STR_METHOD_NAME = "testAttentionInfoNotifyRequest_C003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminEquityAttentionInfoNotifyRequest l_request = new WEB3AdminEquityAttentionInfoNotifyRequest();
        WEB3AdminEquityAttentionInfoNotifyResponse l_response = null;
        WEB3AdminEquityAttentionInfoNotifyResponse l_expectResponse =
            new WEB3AdminEquityAttentionInfoNotifyResponse(l_request);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3AdminEquityAttentionInfoNotifyHandler l_handler =
                new WEB3AdminEquityAttentionInfoNotifyHandler();
            l_response = new WEB3AdminEquityAttentionInfoNotifyResponse();
            l_service =
                (WEB3AdminEquityAttentionInfoNotifyService)Services.getService(
                        WEB3AdminEquityAttentionInfoNotifyService.class);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyServiceImpl", 
                    "execute",
                    new Class[]
                    { WEB3BackRequest.class },
                    l_expectResponse);
            l_response = l_handler.attentionInfoNotifyRequest(l_request);
            assertEquals(l_expectResponse, l_response);
        }
        catch(Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
    }
}
@

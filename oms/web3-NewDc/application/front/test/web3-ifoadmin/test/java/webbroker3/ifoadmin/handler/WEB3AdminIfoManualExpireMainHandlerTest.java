head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.33.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoManualExpireMainHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・先物OP手動失効メインハンドラ(WEB3AdminIfoManualExpireMainHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30　@謝旋(中訊) 新規作成
*/
package webbroker3.ifoadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseMainRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseMainResponse;
import webbroker3.ifoadmin.service.delegate.WEB3AdminIfoManualExpireMainService;
import webbroker3.ifoadmin.service.delegate.stdimpls.WEB3AdminIfoManualExpireMainServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・先物OP手動失効メインハンドラ)<BR>
 * 管理者・先物OP手動失効メインハンドラクラス<BR>
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminIfoManualExpireMainHandlerTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
     private static WEB3LogUtility log = 
         WEB3LogUtility.getInstance(WEB3AdminIfoManualExpireMainHandler.class);

    public WEB3AdminIfoManualExpireMainHandlerTest(String arg0)
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

    public void testmanualExpireRequest_0001()
    {
        String STR_METHOD_NAME = "testmanualExpireRequest_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminIfoManualExpireMainService.class);

            WEB3AdminIfoManualLapseMainRequest request = new WEB3AdminIfoManualLapseMainRequest();
            WEB3AdminIfoManualExpireMainHandler handler = new WEB3AdminIfoManualExpireMainHandler();
            WEB3AdminIfoManualLapseMainResponse response = handler.manualExpireRequest(request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002 , response.errorInfo);

            Services.registerService(WEB3AdminIfoManualExpireMainService.class,
                new WEB3AdminIfoManualExpireMainServiceImpl());
            Services.addInterceptor(WEB3AdminIfoManualExpireMainService.class,
                new WEB3LogSysTimeInterceptor());
            Services.addInterceptor(WEB3AdminIfoManualExpireMainService.class,
                new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testmanualExpireRequest_0002()
    {
        String STR_METHOD_NAME = "testmanualExpireRequest_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoManualLapseMainRequest request = new WEB3AdminIfoManualLapseMainRequest();
            WEB3AdminIfoManualExpireMainHandler handler = new WEB3AdminIfoManualExpireMainHandler();
            WEB3AdminIfoManualLapseMainResponse response = handler.manualExpireRequest(request);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00827 , response.errorInfo);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex) 
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testmanualExpireRequest_0003()
    {
        String STR_METHOD_NAME = "testmanualExpireRequest_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminIfoManualExpireMainService.class);
            Services.registerService(WEB3AdminIfoManualExpireMainService.class,
                new WEB3AdminIfoManualExpireMainServiceImplForMock());
            Services.addInterceptor(WEB3AdminIfoManualExpireMainService.class,
                new WEB3LogSysTimeInterceptor());
            Services.addInterceptor(WEB3AdminIfoManualExpireMainService.class,
                new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

            WEB3AdminIfoManualLapseMainRequest request = new WEB3AdminIfoManualLapseMainRequest();
            WEB3AdminIfoManualExpireMainHandler handler = new WEB3AdminIfoManualExpireMainHandler();
            WEB3AdminIfoManualLapseMainResponse response = handler.manualExpireRequest(request);

            assertEquals(WEB3AdminIfoManualLapseMainResponse.class , response.getClass());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    class WEB3AdminIfoManualExpireMainServiceImplForMock implements WEB3AdminIfoManualExpireMainService
    {
        public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException 
        {
            return new WEB3AdminIfoManualLapseMainResponse();
        }
    }

}
@

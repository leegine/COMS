head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.23.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAccOpenStateInquiryHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.accountopen.message.WEB3AccOpenChangeRequest;
import webbroker3.accountopen.message.WEB3AccOpenChangeResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenStateInquiryService;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AdminAccOpenStateInquiryServiceImpl;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccOpenStateInquiryHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenStateInquiryHandlerTest.class);

    public WEB3AdminAccOpenStateInquiryHandlerTest(String arg0)
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

    public void testChangeCase1()
    {
        final String STR_METHOD_NAME = "testChangeCase1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminAccOpenStateInquiryHandler l_handler = new WEB3AdminAccOpenStateInquiryHandler();
            Services.unregisterService(WEB3AdminAccOpenStateInquiryService.class);
            WEB3AccOpenChangeRequest l_request =
                new WEB3AccOpenChangeRequest();

            WEB3AccOpenChangeResponse l_response =
                l_handler.change(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
                
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.registerService(
                    WEB3AdminAccOpenStateInquiryService.class, new WEB3AdminAccOpenStateInquiryServiceImpl());
        }
        
    }

    /*
     * 異常
     * 
     * execute()メソッドをコールすることが失敗しました。
     * SYSTEM_ERROR_80002
     */
    public void testInputScreenDisplayCase2()
    {
        final String STR_METHOD_NAME = "testInputScreenDisplayCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminAccOpenStateInquiryHandler l_handler = new WEB3AdminAccOpenStateInquiryHandler();
            WEB3AccOpenChangeRequest l_request =
                new WEB3AccOpenChangeRequest();

            MOCK_MANAGER.setIsMockUsed(true);
            Services.overrideService(WEB3AdminAccOpenStateInquiryService.class, new WEB3AdminAccOpenStateInquiryServiceImplForTest2());
            WEB3AccOpenChangeResponse l_response =
                l_handler.change(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 異常
     * 
     * execute()メソッドをコールすることが失敗しました。
     * SYSTEM_ERROR_80018
     */
    public void testInputScreenDisplayCase3()
    {
        final String STR_METHOD_NAME = "testInputScreenDisplayCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminAccOpenStateInquiryHandler l_handler = new WEB3AdminAccOpenStateInquiryHandler();
            WEB3AccOpenChangeRequest l_request =
                new WEB3AccOpenChangeRequest();

            MOCK_MANAGER.setIsMockUsed(true);
            Services.overrideService(WEB3AdminAccOpenStateInquiryService.class, new WEB3AdminAccOpenStateInquiryServiceImplForTest1());
            WEB3AccOpenChangeResponse l_response =
                l_handler.change(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    
    /*
     * 正常
     */
    public void testInputScreenDisplayCase4()
    {
        final String STR_METHOD_NAME = "testInputScreenDisplayCase4()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminAccOpenStateInquiryHandler l_handler = new WEB3AdminAccOpenStateInquiryHandler();
            WEB3AccOpenChangeRequest l_request =
                new WEB3AccOpenChangeRequest();

            MOCK_MANAGER.setIsMockUsed(true);
            Services.overrideService(WEB3AdminAccOpenStateInquiryService.class, new WEB3AdminAccOpenStateInquiryServiceImplForTest3());
            WEB3AccOpenChangeResponse l_response =
                l_handler.change(l_request);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public class WEB3AdminAccOpenStateInquiryServiceImplForTest1 extends WEB3AdminAccOpenStateInquiryServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            log.entering(STR_METHOD_NAME);
            WEB3GenResponse l_response = null;
            if (true)
            {
                log.debug("パラメータタイプ不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "パラメータタイプ不正。");
            }
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
    }

    public class WEB3AdminAccOpenStateInquiryServiceImplForTest2 extends WEB3AdminAccOpenStateInquiryServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            log.entering(STR_METHOD_NAME);
            WEB3GenResponse l_response = null;
            if (true)
            {
                log.debug("予期しないシステムエラーが発生しました。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "予期しないシステムエラーが発生しました。");
            }

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
    }

    public class WEB3AdminAccOpenStateInquiryServiceImplForTest3 extends WEB3AdminAccOpenStateInquiryServiceImpl
    {

        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            log.entering(STR_METHOD_NAME);

            WEB3GenResponse l_response = null;

            if (l_request instanceof WEB3AccOpenChangeRequest)
            {
                l_response =
                    (WEB3AccOpenChangeResponse)l_request.createResponse();
            }

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
    }
}
@

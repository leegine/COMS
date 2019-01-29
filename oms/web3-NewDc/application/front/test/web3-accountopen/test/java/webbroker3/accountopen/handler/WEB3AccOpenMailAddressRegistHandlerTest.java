head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.23.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccOpenMailAddressRegistHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegCompleteRequest;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegCompleteResponse;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegInputRequest;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegInputResponse;
import webbroker3.accountopen.service.delegate.WEB3AccOpenMailAddressRegistService;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenMailAddressRegistServiceImpl;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccOpenMailAddressRegistHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenMailAddressRegistHandlerTest.class);

    public WEB3AccOpenMailAddressRegistHandlerTest(String arg0)
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

    /*
     * Test method for 'webbroker3.accountopen.handler.WEB3AccOpenMailAddressRegistHandler.inputScreenDisplay(WEB3AccOpenMailAddrRegInputRequest)'
     */
    /*
     * 異常
     * 
     * 口座開設メールアドレス登録サービスの取得に失敗しました。
     * l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002
     */
    public void testInputScreenDisplayCase1()
    {
        final String STR_METHOD_NAME = "testInputScreenDisplayCase1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AccOpenMailAddressRegistHandler l_handler = new WEB3AccOpenMailAddressRegistHandler();
            Services.unregisterService(WEB3AccOpenMailAddressRegistService.class);
            WEB3AccOpenMailAddrRegInputRequest l_request =
                new WEB3AccOpenMailAddrRegInputRequest();

            WEB3AccOpenMailAddrRegInputResponse l_response =
                l_handler.inputScreenDisplay(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
                
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.registerService(
                    WEB3AccOpenMailAddressRegistService.class, new WEB3AccOpenMailAddressRegistServiceImpl());
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
            WEB3AccOpenMailAddressRegistHandler l_handler = new WEB3AccOpenMailAddressRegistHandler();
            WEB3AccOpenMailAddrRegInputRequest l_request =
                new WEB3AccOpenMailAddrRegInputRequest();

            MOCK_MANAGER.setIsMockUsed(true);
            Services.overrideService(WEB3AccOpenMailAddressRegistService.class, new WEB3AccOpenMailAddressRegistServiceImplForTest2());
            WEB3AccOpenMailAddrRegInputResponse l_response =
                l_handler.inputScreenDisplay(l_request);

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
            WEB3AccOpenMailAddressRegistHandler l_handler = new WEB3AccOpenMailAddressRegistHandler();
            WEB3AccOpenMailAddrRegInputRequest l_request =
                new WEB3AccOpenMailAddrRegInputRequest();

            MOCK_MANAGER.setIsMockUsed(true);
            Services.overrideService(WEB3AccOpenMailAddressRegistService.class, new WEB3AccOpenMailAddressRegistServiceImplForTest1());
            WEB3AccOpenMailAddrRegInputResponse l_response =
                l_handler.inputScreenDisplay(l_request);

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
            WEB3AccOpenMailAddressRegistHandler l_handler = new WEB3AccOpenMailAddressRegistHandler();
            WEB3AccOpenMailAddrRegInputRequest l_request =
                new WEB3AccOpenMailAddrRegInputRequest();

            MOCK_MANAGER.setIsMockUsed(true);
            Services.overrideService(WEB3AccOpenMailAddressRegistService.class, new WEB3AccOpenMailAddressRegistServiceImplForTest3());
            WEB3AccOpenMailAddrRegInputResponse l_response =
                l_handler.inputScreenDisplay(l_request);
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
     * Test method for 'webbroker3.accountopen.handler.WEB3AccOpenMailAddressRegistHandler.registComplete(WEB3AccOpenMailAddrRegCompleteRequest)'
     */
    public void testRegistCompleteCase1()
    {
        final String STR_METHOD_NAME = "testRegistCompleteCase1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AccOpenMailAddressRegistHandler l_handler = new WEB3AccOpenMailAddressRegistHandler();
            Services.unregisterService(WEB3AccOpenMailAddressRegistService.class);
            WEB3AccOpenMailAddrRegCompleteRequest l_request =
                new WEB3AccOpenMailAddrRegCompleteRequest();

            WEB3AccOpenMailAddrRegCompleteResponse l_response =
                l_handler.registComplete(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
                
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.registerService(
                    WEB3AccOpenMailAddressRegistService.class, new WEB3AccOpenMailAddressRegistServiceImpl());
        }
        
    }

    /*
     * 異常
     * 
     * execute()メソッドをコールすることが失敗しました。
     * SYSTEM_ERROR_80002
     */
    public void testRegistCompleteCase2()
    {
        final String STR_METHOD_NAME = "testRegistCompleteCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenMailAddressRegistHandler l_handler = new WEB3AccOpenMailAddressRegistHandler();
            WEB3AccOpenMailAddrRegCompleteRequest l_request =
                new WEB3AccOpenMailAddrRegCompleteRequest();

            MOCK_MANAGER.setIsMockUsed(true);
            Services.overrideService(WEB3AccOpenMailAddressRegistService.class, new WEB3AccOpenMailAddressRegistServiceImplForTest2());
            WEB3AccOpenMailAddrRegCompleteResponse l_response =
                l_handler.registComplete(l_request);

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
    public void testRegistCompleteCase3()
    {
        final String STR_METHOD_NAME = "testRegistCompleteCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenMailAddressRegistHandler l_handler = new WEB3AccOpenMailAddressRegistHandler();
            WEB3AccOpenMailAddrRegCompleteRequest l_request =
                new WEB3AccOpenMailAddrRegCompleteRequest();

            MOCK_MANAGER.setIsMockUsed(true);
            Services.overrideService(WEB3AccOpenMailAddressRegistService.class, new WEB3AccOpenMailAddressRegistServiceImplForTest1());
            WEB3AccOpenMailAddrRegCompleteResponse l_response =
                l_handler.registComplete(l_request);

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
    public void testRegistCompleteCase4()
    {
        final String STR_METHOD_NAME = "testRegistCompleteCase4()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenMailAddressRegistHandler l_handler = new WEB3AccOpenMailAddressRegistHandler();
            WEB3AccOpenMailAddrRegCompleteRequest l_request =
                new WEB3AccOpenMailAddrRegCompleteRequest();

            MOCK_MANAGER.setIsMockUsed(true);
            Services.overrideService(WEB3AccOpenMailAddressRegistService.class, new WEB3AccOpenMailAddressRegistServiceImplForTest3());
            WEB3AccOpenMailAddrRegCompleteResponse l_response =
                l_handler.registComplete(l_request);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    public class WEB3AccOpenMailAddressRegistServiceImplForTest1 extends WEB3AccOpenMailAddressRegistServiceImpl
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

    public class WEB3AccOpenMailAddressRegistServiceImplForTest2 extends WEB3AccOpenMailAddressRegistServiceImpl
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

    public class WEB3AccOpenMailAddressRegistServiceImplForTest3 extends WEB3AccOpenMailAddressRegistServiceImpl
    {

        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            log.entering(STR_METHOD_NAME);

            WEB3GenResponse l_response = null;

            if (l_request instanceof WEB3AccOpenMailAddrRegInputRequest)
            {
                l_response =
                    (WEB3AccOpenMailAddrRegInputResponse)l_request.createResponse();
            }
            else if (l_request instanceof WEB3AccOpenMailAddrRegCompleteRequest)
            {
                l_response =
                (WEB3AccOpenMailAddrRegCompleteResponse)l_request.createResponse();
            }

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
    }
}
@

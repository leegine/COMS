head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.16.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeBatoClientHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3GentradeBatoClientHandlerTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/04 陸文靜（中訊）新規作成
*/
package webbroker3.gentrade.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.message.WEB3DocumentDeliverRequest;
import webbroker3.gentrade.message.WEB3DocumentDeliverResponse;
import webbroker3.gentrade.message.WEB3GentradeMenuDisplayRequest;
import webbroker3.gentrade.message.WEB3GentradeMenuDisplayResponse;
import webbroker3.gentrade.message.WEB3GentradeProspectusDisplayRequest;
import webbroker3.gentrade.message.WEB3GentradeProspectusDisplayResponse;
import webbroker3.gentrade.message.WEB3GentradeReadDisplayRequest;
import webbroker3.gentrade.message.WEB3GentradeReadDisplayResponse;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImplForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3GentradeBatoClientHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility l_log = WEB3LogUtility.getInstance(WEB3GentradeBatoClientHandlerTest.class);

    private WEB3GentradeBatoClientHandler l_handler = new WEB3GentradeBatoClientHandler();

    public WEB3GentradeBatoClientHandlerTest(String arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }
    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    // 電子鳩システム接続サービスを取得し、execute()メソッドをコールする。
    public void testDisplayProspectus_C0001()
    {

        final String STR_METHOD_NAME = "testDisplayProspectus_C0001()";
        l_log.debug(TEST_START + STR_METHOD_NAME);
        WEB3GentradeProspectusDisplayRequest l_request = new WEB3GentradeProspectusDisplayRequest();

        try
        {
            Services.unregisterService(WEB3GentradeBatoClientService.class);
            WEB3GentradeProspectusDisplayResponse l_response = l_handler.displayProspectus(l_request);
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
                    WEB3GentradeBatoClientService.class,
                new  WEB3GentradeBatoClientServiceImplForMock());
        }

        l_log.debug(TEST_END + STR_METHOD_NAME);
    }
    //  電子鳩システム接続サービスを取得し、execute()メソッドをコールする。
    public void testDisplayProspectus_C0002()
    {

        final String STR_METHOD_NAME = "testDisplayProspectus_C0002()";
        l_log.debug(TEST_START + STR_METHOD_NAME);
        WEB3GentradeProspectusDisplayRequest l_request = new WEB3GentradeProspectusDisplayRequest();

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3BaseException l_Exception=new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80025,null,"処理対象外。");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_Exception);
            WEB3GentradeProspectusDisplayResponse l_response = l_handler.displayProspectus(l_request);
                assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80025, l_response.errorInfo);
                assertEquals("処理対象外。", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            l_log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        l_log.debug(TEST_END + STR_METHOD_NAME);
    }
    //  電子鳩システム接続サービスを取得し、execute()メソッドをコールする。
    public void testDisplayProspectus_C0003()
    {

        final String STR_METHOD_NAME = "testDisplayProspectus_C0003()";
        l_log.debug(TEST_START + STR_METHOD_NAME);
        WEB3GentradeProspectusDisplayRequest l_request = new WEB3GentradeProspectusDisplayRequest();

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3BaseRuntimeException l_Exception=new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80025,null,"処理対象外。");

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_Exception);  
            WEB3GentradeProspectusDisplayResponse l_response = l_handler.displayProspectus(l_request);
                assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            l_log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        l_log.debug(TEST_END + STR_METHOD_NAME);
    }
    //  電子鳩システム接続サービスを取得し、execute()メソッドをコールする。
    public void testDisplayProspectus_C0004()
    {

        final String STR_METHOD_NAME = "testDisplayProspectus_C0004()";
        l_log.debug(TEST_START + STR_METHOD_NAME);
        WEB3GentradeProspectusDisplayRequest l_request = new WEB3GentradeProspectusDisplayRequest();

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeProspectusDisplayResponse l_Response=new WEB3GentradeProspectusDisplayResponse();
            l_Response.errorMessage = "123";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_Response);
            WEB3GentradeProspectusDisplayResponse l_response = l_handler.displayProspectus(l_request);
                assertEquals("123", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            l_log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        l_log.debug(TEST_END + STR_METHOD_NAME);
    }
    //電子鳩システム接続サービスを取得し、execute()メソッドをコールする。
    public void testDisplayMenu_C0001()
    {

        final String STR_METHOD_NAME = "testDisplayMenu_C0001()";
        l_log.debug(TEST_START + STR_METHOD_NAME);

        WEB3GentradeMenuDisplayRequest l_request = new WEB3GentradeMenuDisplayRequest();
        try
        {
            Services.unregisterService(WEB3GentradeBatoClientService.class);
            WEB3GentradeMenuDisplayResponse l_response = l_handler.displayMenu(l_request);
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
                    WEB3GentradeBatoClientService.class,
                new  WEB3GentradeBatoClientServiceImplForMock());
        }

        l_log.debug(TEST_END + STR_METHOD_NAME);
    }
    //電子鳩システム接続サービスを取得し、execute()メソッドをコールする。
    public void testDisplayMenu_C0002()
    {

        final String STR_METHOD_NAME = "testDisplayMenu_C0002()";
        l_log.debug(TEST_START + STR_METHOD_NAME);

        WEB3GentradeMenuDisplayRequest l_request = new WEB3GentradeMenuDisplayRequest();
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3BaseException l_Exception = new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80075,null,"日本側予めエラーメッセージ採番ナンバー。");

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_Exception);
            WEB3GentradeMenuDisplayResponse l_response = l_handler.displayMenu(l_request);
                assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80075, l_response.errorInfo);
                assertEquals("日本側予めエラーメッセージ採番ナンバー。", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            l_log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        l_log.debug(TEST_END + STR_METHOD_NAME);
    }
    //電子鳩システム接続サービスを取得し、execute()メソッドをコールする。
    public void testDisplayMenu_C0003()
    {

        final String STR_METHOD_NAME = "testDisplayMenu_C0003()";
        l_log.debug(TEST_START + STR_METHOD_NAME);

        WEB3GentradeMenuDisplayRequest l_request=new WEB3GentradeMenuDisplayRequest();
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3BaseRuntimeException l_Exception=new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80025,null,"処理対象外。");

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_Exception);
            WEB3GentradeMenuDisplayResponse l_response = l_handler.displayMenu(l_request);
                assertEquals( "電子鳩システム接続サービスにて、想定外の例外が発生しました。" +
                        "例外の内容は、StackTrace を参照してください。", l_response.errorMessage);
                assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            l_log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        l_log.debug(TEST_END + STR_METHOD_NAME);
    }

    //  電子鳩システム接続サービスを取得し、execute()メソッドをコールする。
    public void testDisplayMenu_C0004()
    {

        final String STR_METHOD_NAME = "testDisplayMenu_C0004()";
        l_log.debug(TEST_START + STR_METHOD_NAME);

        WEB3GentradeMenuDisplayRequest l_request=new WEB3GentradeMenuDisplayRequest();
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeMenuDisplayResponse l_Response=new WEB3GentradeMenuDisplayResponse();
            l_Response.errorMessage = "1243";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_Response);
            WEB3GentradeMenuDisplayResponse l_response = l_handler.displayMenu(l_request);
                assertEquals( "1243", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            l_log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        l_log.debug(TEST_END + STR_METHOD_NAME);
    }
    //電子鳩システム接続サービスを取得し、execute()メソッドをコールする。
    public void testDocumentDeliver_C0001()
    {

        final String STR_METHOD_NAME = "testDocumentDeliver_C0001()";
        l_log.debug(TEST_START + STR_METHOD_NAME);

        WEB3DocumentDeliverRequest l_request=new WEB3DocumentDeliverRequest();
         try
         {
             Services.unregisterService(WEB3GentradeBatoClientService.class);
             WEB3DocumentDeliverResponse l_response = l_handler.documentDeliver(l_request);
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
                     WEB3GentradeBatoClientService.class,
                 new  WEB3GentradeBatoClientServiceImplForMock());
         }
        l_log.debug(TEST_END + STR_METHOD_NAME);
    }
    //電子鳩システム接続サービスを取得し、execute()メソッドをコールする。
    public void testDocumentDeliver_C0002()
    {

        final String STR_METHOD_NAME = "testDocumentDeliver_C0002()";
        l_log.debug(TEST_START + STR_METHOD_NAME);

        WEB3DocumentDeliverRequest l_request=new WEB3DocumentDeliverRequest();
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3BaseException l_Exception=new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80025,null,"処理対象外。");

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_Exception);
            WEB3DocumentDeliverResponse l_response = l_handler.documentDeliver(l_request);
                assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80025, l_response.errorInfo);

        }
        catch (Exception l_ex)
        {
            l_log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        l_log.debug(TEST_END + STR_METHOD_NAME);
    }
    //  電子鳩システム接続サービスを取得し、execute()メソッドをコールする。
    public void testDocumentDeliver_C0003()
    {

        final String STR_METHOD_NAME = "testDocumentDeliver_C0003()";
        l_log.debug(TEST_START + STR_METHOD_NAME);

        WEB3DocumentDeliverRequest l_request=new WEB3DocumentDeliverRequest();
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3DocumentDeliverResponse l_Response=new WEB3DocumentDeliverResponse();
            l_Response.errorMessage="123";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_Response);
            WEB3DocumentDeliverResponse l_response = l_handler.documentDeliver(l_request);
                assertEquals("123", l_response.errorMessage);

        }
        catch (Exception l_ex)
        {
            l_log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        l_log.debug(TEST_END + STR_METHOD_NAME);
    }

    //電子鳩システム接続サービスを取得し、execute()メソッドをコールする。
    public void testReadDisplayCase1()
    {

        final String STR_METHOD_NAME = "testReadDisplayCase1()";
        l_log.debug(TEST_START + STR_METHOD_NAME);

        WEB3DocumentDeliverRequest l_request=new WEB3DocumentDeliverRequest();
         try
         {
             Services.unregisterService(WEB3GentradeBatoClientService.class);
             WEB3DocumentDeliverResponse l_response = l_handler.documentDeliver(l_request);
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
                     WEB3GentradeBatoClientService.class,
                 new  WEB3GentradeBatoClientServiceImplForMock());
         }
        l_log.debug(TEST_END + STR_METHOD_NAME);
    }
    //電子鳩システム接続サービスを取得し、execute()メソッドをコールする。
    public void testReadDisplayCase2()
    {

        final String STR_METHOD_NAME = "testReadDisplayCase2()";
        l_log.debug(TEST_START + STR_METHOD_NAME);

        WEB3GentradeReadDisplayRequest l_request=new WEB3GentradeReadDisplayRequest();
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3BaseException l_Exception=new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80025,null,"処理対象外。");

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_Exception);
            WEB3GentradeReadDisplayResponse l_response = l_handler.readDisplay(l_request);
                assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80025, l_response.errorInfo);

        }
        catch (Exception l_ex)
        {
            l_log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        l_log.debug(TEST_END + STR_METHOD_NAME);
    }
    //  電子鳩システム接続サービスを取得し、execute()メソッドをコールする。
    public void testReadDisplayCase3()
    {

        final String STR_METHOD_NAME = "testReadDisplayCase3()";
        l_log.debug(TEST_START + STR_METHOD_NAME);

        WEB3GentradeReadDisplayRequest l_request=new WEB3GentradeReadDisplayRequest();
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeReadDisplayResponse l_Response=new WEB3GentradeReadDisplayResponse();
            l_Response.errorMessage="123";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_Response);
            WEB3GentradeReadDisplayResponse l_response = l_handler.readDisplay(l_request);
                assertEquals("123", l_response.errorMessage);

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

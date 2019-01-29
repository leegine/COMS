head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.40.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AccInfoEquityCommissionCourseRegistHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoEquityCommissionCourseRegistService;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoEquityCommissionCourseRegistServiceImpl;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccInfoEquityCommissionCourseRegistHandlerTest extends
    TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AccInfoEquityCommissionCourseRegistHandlerTest.class);

    WEB3AccInfoEquityCommissionCourseRegistHandler l_handler;
    
    public WEB3AccInfoEquityCommissionCourseRegistHandlerTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    /**
     * (入力画面表示)<BR>
     * 株式委託手数料コース変更申込入力画面表示処理を行う。 <BR>
     * <BR>
     * お客様情報株式委託手数料コース変更申込サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - お客様情報株式委託手数料コース変更申込入力リクエストデータオブジェクト
     * @@return WEB3AccInfoEquityCommissionCourseChangeInputResponse
     * @@roseuid 413D5CB500D4
     */
    public void testInputScreenDisplay_C001()
    {
        final String STR_METHOD_NAME = "testInputScreenDisplay_C001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoEquityCommissionCourseChangeInputRequest l_request =
            new WEB3AccInfoEquityCommissionCourseChangeInputRequest();
        
        try{
            Services.unregisterService(WEB3AccInfoEquityCommissionCourseRegistService.class);
            WEB3AccInfoEquityCommissionCourseChangeInputResponse l_response = 
                l_handler.inputScreenDisplay(l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);   
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                Services.registerService(
                        WEB3AccInfoEquityCommissionCourseRegistService.class,
                    new WEB3AccInfoEquityCommissionCourseRegistServiceImpl());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testInputScreenDisplay_C002()
    {
        final String STR_METHOD_NAME = "testInputScreenDisplay_C002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoEquityCommissionCourseChangeInputRequest l_request = 
            new WEB3AccInfoEquityCommissionCourseChangeInputRequest();
        
        try{
            
            Services.overrideService(
                WEB3AccInfoEquityCommissionCourseRegistService.class,
                new WEB3AccInfoEquityCommissionCourseRegistServiceImplForTestA()
                );
            
            WEB3AccInfoEquityCommissionCourseChangeInputResponse l_response =
                l_handler.inputScreenDisplay(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00857, l_response.errorInfo);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testInputScreenDisplay_C003()
    {
        final String STR_METHOD_NAME = "testInputScreenDisplay_C003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoEquityCommissionCourseChangeInputRequest l_request = 
            new WEB3AccInfoEquityCommissionCourseChangeInputRequest();
        
        try{
            
            Services.overrideService(
                WEB3AccInfoEquityCommissionCourseRegistService.class,
                new WEB3AccInfoEquityCommissionCourseRegistServiceImplForTestB()
                );
            
            WEB3AccInfoEquityCommissionCourseChangeInputResponse l_response =
                l_handler.inputScreenDisplay(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    

    
//  888888888888888888888888888888888888888888888
    public class WEB3AccInfoEquityCommissionCourseRegistServiceImplForTestA
        extends WEB3AccInfoEquityCommissionCourseRegistServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            log.entering(STR_METHOD_NAME);

            WEB3GenResponse l_response = null;
            if (true)
            {
                log.debug("DIR管理者権限チェックエラー。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00857 ,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DIR管理者権限チェックエラー。");
            }
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
    }
    
    public class WEB3AccInfoEquityCommissionCourseRegistServiceImplForTestB
    extends WEB3AccInfoEquityCommissionCourseRegistServiceImpl
{
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            log.entering(STR_METHOD_NAME);

            WEB3GenResponse l_response = l_request.createResponse();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
}
    
}
@

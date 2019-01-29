head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.56.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTDocumentListReferenceHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListReferenceRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListReferenceResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListSearchInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListSearchInputResponse;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTDocumentListReferenceService;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTRegistService;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTDocumentListReferenceServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFPTDocumentListReferenceHandlerTest extends
        TestBaseForMock {

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocumentListReferenceHandlerTest.class);

    
    public WEB3AdminFPTDocumentListReferenceHandlerTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    
    WEB3AdminFPTDocumentListReferenceHandler l_handler = new WEB3AdminFPTDocumentListReferenceHandler();
    
    /*
     * Test method for 'webbroker3.docadmin.handler.WEB3AdminFPTDocumentListReferenceHandler.getDocumentReferenceSearchInput(WEB3AdminFPTDocumentListSearchInputRequest)'
     */
    public void testGetDocumentReferenceSearchInput_T001()
    {
        
        final String STR_METHOD_NAME = "testGetDocumentReferenceSearchInput_T001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentListSearchInputRequest l_request  = 
            new WEB3AdminFPTDocumentListSearchInputRequest();

        try
        {
            Services.unregisterService(WEB3AdminFPTDocumentListReferenceService.class);
            
            WEB3AdminFPTDocumentListSearchInputResponse l_response =
                l_handler.getDocumentReferenceSearchInput(l_request);

            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80002);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            try
            {
                Services.registerService(
                        WEB3AdminFPTDocumentListReferenceService.class,
                    new WEB3AdminFPTDocumentListReferenceServiceImpl());
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        

    }

    public void testGetDocumentReferenceSearchInput_T002()
    {
        
        final String STR_METHOD_NAME = "testGetDocumentReferenceSearchInput_T002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentListSearchInputRequest l_request  = 
            new WEB3AdminFPTDocumentListSearchInputRequest();

        try
        {

            Services.overrideService(
                    WEB3AdminFPTDocumentListReferenceService.class,
                    new WEB3AdminFPTDocumentListReferenceServiceImplForTestA());

            WEB3AdminFPTDocumentListSearchInputResponse l_response =
                l_handler.getDocumentReferenceSearchInput(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
        
       
    }
    
    public void testGetDocumentReferenceSearchInput_T003()
    {
        
        final String STR_METHOD_NAME = "testGetDocumentReferenceSearchInput_T003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentListSearchInputRequest l_request  = 
            new WEB3AdminFPTDocumentListSearchInputRequest();

        try
        {

            Services.overrideService(
                    WEB3AdminFPTDocumentListReferenceService.class,
                    new WEB3AdminFPTDocumentListReferenceServiceImplForTestB());

            WEB3AdminFPTDocumentListSearchInputResponse l_response =
                l_handler.getDocumentReferenceSearchInput(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
        
       
    }
    
    public void testGetDocumentReferenceSearchInput_T004()
    {
        
        final String STR_METHOD_NAME = "testGetDocumentReferenceSearchInput_T004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentListSearchInputRequest l_request  = 
            new WEB3AdminFPTDocumentListSearchInputRequest();

        try
        {

            Services.overrideService(
                    WEB3AdminFPTDocumentListReferenceService.class,
                    new WEB3AdminFPTDocumentListReferenceServiceImplForTestC());

            WEB3AdminFPTDocumentListSearchInputResponse l_response =
                l_handler.getDocumentReferenceSearchInput(l_request);
 
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
        
       
    }
    
    
    
    /*
     * Test method for 'webbroker3.docadmin.handler.WEB3AdminFPTDocumentListReferenceHandler.getDocumentReferenceList(WEB3AdminFPTDocumentListReferenceRequest)'
     */
    public void testGetDocumentReferenceList_T001() 
    {
        final String STR_METHOD_NAME = "testGetDocumentReferenceList_T001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentListReferenceRequest l_request = new WEB3AdminFPTDocumentListReferenceRequest();
        
        
        try
        {
            
            Services.unregisterService(WEB3AdminFPTDocumentListReferenceService.class);
            
            WEB3AdminFPTDocumentListReferenceResponse l_response =
                l_handler.getDocumentReferenceList(l_request);

            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80002);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            try
            {
                Services.registerService(
                        WEB3AdminFPTDocumentListReferenceService.class,
                    new WEB3AdminFPTDocumentListReferenceServiceImpl());
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
  
    
    }
    
    
    public void testGetDocumentReferenceList_T002()
    {
        
        final String STR_METHOD_NAME = "testGetDocumentReferenceList_T002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentListReferenceRequest l_request  = 
            new WEB3AdminFPTDocumentListReferenceRequest();

        try
        {

            Services.overrideService(
                    WEB3AdminFPTDocumentListReferenceService.class,
                    new WEB3AdminFPTDocumentListReferenceServiceImplForTestA());

            WEB3AdminFPTDocumentListReferenceResponse l_response =
                l_handler.getDocumentReferenceList(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
        
       
    }
    
    public void testGetDocumentReferenceList_T003()
    {
        
        final String STR_METHOD_NAME = "testGetDocumentReferenceList_T003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentListReferenceRequest l_request  = 
            new WEB3AdminFPTDocumentListReferenceRequest();

        try
        {

            Services.overrideService(
                    WEB3AdminFPTDocumentListReferenceService.class,
                    new WEB3AdminFPTDocumentListReferenceServiceImplForTestB());

            WEB3AdminFPTDocumentListReferenceResponse l_response =
                l_handler.getDocumentReferenceList(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
        
       
    }
    
    public void testGetDocumentReferenceList_T004()
    {
        
        final String STR_METHOD_NAME = "testGetDocumentReferenceList_T004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentListReferenceRequest l_request  = 
            new WEB3AdminFPTDocumentListReferenceRequest();

        try
        {

            Services.overrideService(
                    WEB3AdminFPTDocumentListReferenceService.class,
                    new WEB3AdminFPTDocumentListReferenceServiceImplForTestC());

            WEB3AdminFPTDocumentListReferenceResponse l_response =
                l_handler.getDocumentReferenceList(l_request);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
        
       
    }
    
//#######################################################################################################################
    public class WEB3AdminFPTDocumentListReferenceServiceImplForTestA extends WEB3AdminFPTDocumentListReferenceServiceImpl
    {
        
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            log.entering(STR_METHOD_NAME);

            if (l_request == null)
            {
                log.debug("パラメータ値不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." +  STR_METHOD_NAME,
                    "パラメータ値不正。");
            }

            WEB3GenResponse l_response = null;
            //引数のリクエストデータが、管理者金商法@交付書面照会入力リクエストの場合
            if (l_request instanceof WEB3AdminFPTDocumentListSearchInputRequest)
            {

                if (true)
                {
                    log.debug("パラメータタイプ不正。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "パラメータタイプ不正。");
                }
                
                
            }
            //引数のリクエストデータが、管理者金商法@交付書面照会一覧リクエストの場合
            else if (l_request instanceof WEB3AdminFPTDocumentListReferenceRequest)
            {

                if (true)
                {
                    log.debug("パラメータタイプ不正。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "パラメータタイプ不正。");
                }

            }
            else
            {
                log.debug("パラメータタイプ不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "パラメータタイプ不正。");
            }

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        
        
        
        
    }
    
    
    public class WEB3AdminFPTDocumentListReferenceServiceImplForTestB extends WEB3AdminFPTDocumentListReferenceServiceImpl
    {
        
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            log.entering(STR_METHOD_NAME);

            if (l_request == null)
            {
                log.debug("パラメータ値不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." +  STR_METHOD_NAME,
                    "パラメータ値不正。");
            }

            WEB3GenResponse l_response = null;
            //引数のリクエストデータが、管理者金商法@交付書面照会入力リクエストの場合
            if (l_request instanceof WEB3AdminFPTDocumentListSearchInputRequest)
            {

                if (true)
                {
                    log.debug("予期しないシステムエラーが発生しました。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "予期しないシステムエラーが発生しました。");
                }
                
                
            }
            //引数のリクエストデータが、管理者金商法@交付書面照会一覧リクエストの場合
            else if (l_request instanceof WEB3AdminFPTDocumentListReferenceRequest)
            {

                if (true)
                {
                    log.debug("予期しないシステムエラーが発生しました。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "予期しないシステムエラーが発生しました。");
                }
            }
            else
            {
                log.debug("パラメータタイプ不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "パラメータタイプ不正。");
            }

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
    
    }
    
    public class WEB3AdminFPTDocumentListReferenceServiceImplForTestC extends WEB3AdminFPTDocumentListReferenceServiceImpl
    {
        
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            log.entering(STR_METHOD_NAME);

            if (l_request == null)
            {
                log.debug("パラメータ値不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." +  STR_METHOD_NAME,
                    "パラメータ値不正。");
            }

            WEB3GenResponse l_response = null;
            //引数のリクエストデータが、管理者金商法@交付書面照会入力リクエストの場合
            if (l_request instanceof WEB3AdminFPTDocumentListSearchInputRequest)
            {

                l_response = (WEB3AdminFPTDocumentListSearchInputResponse)l_request.createResponse();
 
            }
            //引数のリクエストデータが、管理者金商法@交付書面照会一覧リクエストの場合
            else if (l_request instanceof WEB3AdminFPTDocumentListReferenceRequest)
            {
                l_response = (WEB3AdminFPTDocumentListReferenceResponse)l_request.createResponse();
                
            }
            else
            {
                log.debug("パラメータタイプ不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "パラメータタイプ不正。");
            }

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
    
    }
    
}
@

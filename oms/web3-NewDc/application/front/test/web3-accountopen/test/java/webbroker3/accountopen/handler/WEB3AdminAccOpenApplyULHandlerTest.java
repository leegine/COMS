head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.23.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAccOpenApplyULHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCancelRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCancelResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadInputRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadInputResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenApplyULService;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AdminAccOpenApplyULServiceImpl;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccOpenApplyULHandlerTest extends TestBaseForMock {

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyULHandlerTest.class);

    WEB3AdminAccOpenApplyULHandler l_adminAccOpenApplyULHandler =
        new WEB3AdminAccOpenApplyULHandler();

    public WEB3AdminAccOpenApplyULHandlerTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
       
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.accountopen.handler.WEB3AdminAccOpenApplyULHandler.uploadScreenDisplay(WEB3AdminAccOpenApplyUploadInputRequest)'
     */
    public void testUploadScreenDisplay_T001() {

        final String STR_METHOD_NAME = "testUploadScreenDisplay_T001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Services.unregisterService(WEB3AdminAccOpenApplyULService.class);
            WEB3AdminAccOpenApplyUploadInputRequest l_Request =
                new WEB3AdminAccOpenApplyUploadInputRequest();
            
            WEB3AdminAccOpenApplyUploadInputResponse l_Response = 
                l_adminAccOpenApplyULHandler.uploadScreenDisplay(l_Request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_Response.errorInfo);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminAccOpenApplyULService.class,
                    new WEB3AdminAccOpenApplyULServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    
    public void testUploadScreenDisplay_T002() {

        final String STR_METHOD_NAME = "testUploadScreenDisplay_T002()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenApplyUploadInputResponse l_Response = null;

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3AdminAccOpenApplyUploadInputRequest l_Request =
                new WEB3AdminAccOpenApplyUploadInputRequest();
            Services.overrideService(WEB3AdminAccOpenApplyULService.class, 
                    new WEB3AdminAccOpenApplyULServiceImplForTest2());
           l_Response = l_adminAccOpenApplyULHandler.uploadScreenDisplay(l_Request);
           assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_Response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUploadScreenDisplay_T003() {

        final String STR_METHOD_NAME = "testUploadScreenDisplay_T003()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenApplyUploadInputResponse l_Response = null;
        
        try{
            
            WEB3AdminAccOpenApplyUploadInputRequest l_Request =
                new WEB3AdminAccOpenApplyUploadInputRequest();
            Services.overrideService(WEB3AdminAccOpenApplyULService.class, 
                    new WEB3AdminAccOpenApplyULServiceImplForTest1());
            l_Response = l_adminAccOpenApplyULHandler.uploadScreenDisplay(l_Request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_Response.errorInfo);

        }
        catch(WEB3BaseException l_ex){
            log.error(l_ex.getMessage(),l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_Response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUploadScreenDisplay_T004() {

        final String STR_METHOD_NAME = "testUploadScreenDisplay_T001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Services.overrideService(WEB3AdminAccOpenApplyULService.class, 
                    new WEB3AdminAccOpenApplyULServiceImplForTest3());
            WEB3AdminAccOpenApplyUploadInputRequest l_Request =
                new WEB3AdminAccOpenApplyUploadInputRequest();
            WEB3AdminAccOpenApplyUploadInputResponse l_Response = 
                l_adminAccOpenApplyULHandler.uploadScreenDisplay(l_Request);

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
       
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * Test method for 'webbroker3.accountopen.handler.WEB3AdminAccOpenApplyULHandler.uploadFileConfirm(WEB3AdminAccOpenApplyUploadConfirmRequest)'
     */
    public void testUploadFileConfirm_T001() {

        final String STR_METHOD_NAME = "testUploadFileConfirm()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenApplyUploadConfirmRequest l_adminAccOpenApplyUploadConfirmRequest =
            new WEB3AdminAccOpenApplyUploadConfirmRequest();
        
        try
        {
            Services.unregisterService(WEB3AdminAccOpenApplyULService.class);
            WEB3AdminAccOpenApplyUploadConfirmResponse l_Response = 
            l_adminAccOpenApplyULHandler.uploadFileConfirm(l_adminAccOpenApplyUploadConfirmRequest);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_Response.errorInfo);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminAccOpenApplyULService.class,
                    new WEB3AdminAccOpenApplyULServiceImpl());
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }

    
    public void testUploadFileConfirm_T002() {

        final String STR_METHOD_NAME = "testUploadFileConfirm_T002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenApplyUploadConfirmResponse l_Response = null;
 
        try{

            WEB3AdminAccOpenApplyUploadConfirmRequest l_Request =
                new WEB3AdminAccOpenApplyUploadConfirmRequest();
            
            Services.overrideService(WEB3AdminAccOpenApplyULService.class, 
                    new WEB3AdminAccOpenApplyULServiceImplForTest1());

            l_Response = l_adminAccOpenApplyULHandler.uploadFileConfirm(l_Request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_Response.errorInfo);

        }
        catch(WEB3BaseException l_ex){
            log.error(l_ex.getMessage(),l_ex);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    
    public void testUploadFileConfirm_T003() {

        final String STR_METHOD_NAME = "testUploadFileConfirm_T003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenApplyUploadConfirmResponse l_Response = null;
        
        try{

            WEB3AdminAccOpenApplyUploadConfirmRequest l_Request =
                new WEB3AdminAccOpenApplyUploadConfirmRequest();
            
            Services.overrideService(WEB3AdminAccOpenApplyULService.class, 
                    new WEB3AdminAccOpenApplyULServiceImplForTest2());

            l_Response = l_adminAccOpenApplyULHandler.uploadFileConfirm(l_Request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_Response.errorInfo);

        }
        catch(WEB3BaseException l_ex){
            log.error(l_ex.getMessage(),l_ex);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testUploadFileConfirm_T004() {

        final String STR_METHOD_NAME = "testUploadFileConfirm()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenApplyUploadConfirmRequest l_adminAccOpenApplyUploadConfirmRequest =
            new WEB3AdminAccOpenApplyUploadConfirmRequest();
        
        try
        {
            Services.overrideService(WEB3AdminAccOpenApplyULService.class, 
                    new WEB3AdminAccOpenApplyULServiceImplForTest3());
            
            WEB3AdminAccOpenApplyUploadConfirmResponse l_Response = 
            l_adminAccOpenApplyULHandler.uploadFileConfirm(l_adminAccOpenApplyUploadConfirmRequest);
          
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        
    }
    
    /*
     * Test method for 'webbroker3.accountopen.handler.WEB3AdminAccOpenApplyULHandler.accOpenApplyUpload(WEB3AdminAccOpenApplyUploadCompleteRequest)'
     */
    public void testAccOpenApplyUpload_T001() {

        final String STR_METHOD_NAME = "testAccOpenApplyUpload_T001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenApplyUploadCompleteRequest l_adminAccOpenApplyUploadCompleteRequest =
            new WEB3AdminAccOpenApplyUploadCompleteRequest();
        
        try
        {
            Services.unregisterService(WEB3AdminAccOpenApplyULService.class);
            WEB3AdminAccOpenApplyUploadCompleteResponse l_Response = 
            l_adminAccOpenApplyULHandler.accOpenApplyUpload(l_adminAccOpenApplyUploadCompleteRequest);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_Response.errorInfo);

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminAccOpenApplyULService.class,
                    new WEB3AdminAccOpenApplyULServiceImpl());
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }

    
    
    
    public void testAccOpenApplyUpload_T002() {

        final String STR_METHOD_NAME = "testAccOpenApplyUpload_T002()";
        log.entering(STR_METHOD_NAME);
        
        
        WEB3AdminAccOpenApplyUploadCompleteResponse l_Response = null;
        
        try{

            WEB3AdminAccOpenApplyUploadCompleteRequest l_Request =
                new WEB3AdminAccOpenApplyUploadCompleteRequest();
            
            Services.overrideService(WEB3AdminAccOpenApplyULService.class, 
                    new WEB3AdminAccOpenApplyULServiceImplForTest1());

            l_Response = l_adminAccOpenApplyULHandler.accOpenApplyUpload(l_Request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_Response.errorInfo);
  
        }
        catch(WEB3BaseException l_ex){
            log.error(l_ex.getMessage(),l_ex);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testAccOpenApplyUpload_T003() {

        final String STR_METHOD_NAME = "testAccOpenApplyUpload_T003()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenApplyUploadCompleteResponse l_Response = null;
        try{

            WEB3AdminAccOpenApplyUploadCompleteRequest l_Request =
                new WEB3AdminAccOpenApplyUploadCompleteRequest();
            
            Services.overrideService(WEB3AdminAccOpenApplyULService.class, 
                    new WEB3AdminAccOpenApplyULServiceImplForTest2());

            l_Response = l_adminAccOpenApplyULHandler.accOpenApplyUpload(l_Request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_Response.errorInfo);
  
        }
        catch(WEB3BaseException l_ex){
            log.error(l_ex.getMessage(),l_ex);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testAccOpenApplyUpload_T004() {

        final String STR_METHOD_NAME = "testAccOpenApplyUpload_T001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenApplyUploadCompleteRequest l_adminAccOpenApplyUploadCompleteRequest =
            new WEB3AdminAccOpenApplyUploadCompleteRequest();
        
        try
        {
            Services.overrideService(WEB3AdminAccOpenApplyULService.class, 
                    new WEB3AdminAccOpenApplyULServiceImplForTest3());
            WEB3AdminAccOpenApplyUploadCompleteResponse l_Response = 
            l_adminAccOpenApplyULHandler.accOpenApplyUpload(l_adminAccOpenApplyUploadCompleteRequest);

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        
    }
    
    /*
     * Test method for 'webbroker3.accountopen.handler.WEB3AdminAccOpenApplyULHandler.uploadCancel(WEB3AdminAccOpenApplyUploadCancelRequest)'
     */
    public void testUploadCancel_T001() {

        final String STR_METHOD_NAME = "testUploadCancel_T001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenApplyUploadCancelRequest l_adminAccOpenApplyUploadCancelRequest =
            new WEB3AdminAccOpenApplyUploadCancelRequest();
        
        try
        {
            Services.unregisterService(WEB3AdminAccOpenApplyULService.class);
            
            l_adminAccOpenApplyUploadCancelRequest.uploadID="132456";
            WEB3AdminAccOpenApplyUploadCancelResponse l_Response =
            l_adminAccOpenApplyULHandler.uploadCancel(l_adminAccOpenApplyUploadCancelRequest);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_Response.errorInfo);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminAccOpenApplyULService.class,
                new WEB3AdminAccOpenApplyULServiceImpl());
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }

    
    public void testUploadCancel_T002() {

        final String STR_METHOD_NAME = "testUploadCancel_T002()";
        log.entering(STR_METHOD_NAME);

      
        
        WEB3AdminAccOpenApplyUploadCancelResponse l_Response = null;
        
        try{

            WEB3AdminAccOpenApplyUploadCancelRequest l_Request =
                new WEB3AdminAccOpenApplyUploadCancelRequest();
            Services.overrideService(WEB3AdminAccOpenApplyULService.class, 
                    new WEB3AdminAccOpenApplyULServiceImplForTest1());
            l_Response = l_adminAccOpenApplyULHandler.uploadCancel(l_Request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_Response.errorInfo);

        }
        catch(WEB3BaseException l_ex){
            log.error(l_ex.getMessage(),l_ex);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        
        
    }
    
    
    public void testUploadCancel_T003() {

        final String STR_METHOD_NAME = "testUploadCancel_T003()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenApplyUploadCancelResponse l_Response = null;
        
        try{

            WEB3AdminAccOpenApplyUploadCancelRequest l_Request =
                new WEB3AdminAccOpenApplyUploadCancelRequest();
            Services.overrideService(WEB3AdminAccOpenApplyULService.class, 
                    new WEB3AdminAccOpenApplyULServiceImplForTest2());
            l_Response = l_adminAccOpenApplyULHandler.uploadCancel(l_Request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_Response.errorInfo);

        }
        catch(WEB3BaseException l_ex){
            log.error(l_ex.getMessage(),l_ex);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    

    public void testUploadCancel_T004() {

        final String STR_METHOD_NAME = "testUploadCancel_T004()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenApplyUploadCancelRequest l_adminAccOpenApplyUploadCancelRequest =
            new WEB3AdminAccOpenApplyUploadCancelRequest();
        
        try
        {
            Services.overrideService(WEB3AdminAccOpenApplyULService.class, 
                    new WEB3AdminAccOpenApplyULServiceImplForTest3());
            l_adminAccOpenApplyUploadCancelRequest.uploadID="132456";
            WEB3AdminAccOpenApplyUploadCancelResponse l_Response =
            l_adminAccOpenApplyULHandler.uploadCancel(l_adminAccOpenApplyUploadCancelRequest);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        
    }

    public class WEB3AdminAccOpenApplyULServiceImplForTest1 extends WEB3AdminAccOpenApplyULServiceImpl
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
    
    
    public class WEB3AdminAccOpenApplyULServiceImplForTest2 extends WEB3AdminAccOpenApplyULServiceImpl
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
    
    public class WEB3AdminAccOpenApplyULServiceImplForTest3 extends WEB3AdminAccOpenApplyULServiceImpl
    {

        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            log.entering(STR_METHOD_NAME);
           
            
            WEB3GenResponse l_response = null;
            
            if (l_request instanceof WEB3AdminAccOpenApplyUploadInputRequest)
            {
                l_response =(WEB3AdminAccOpenApplyUploadInputResponse)l_request.createResponse();
            }
            else if (l_request instanceof WEB3AdminAccOpenApplyUploadConfirmRequest)
            {

                l_response = 
                (WEB3AdminAccOpenApplyUploadConfirmResponse)l_request.createResponse();
            }
            else if (l_request instanceof WEB3AdminAccOpenApplyUploadCompleteRequest)
            {

                l_response = 
                (WEB3AdminAccOpenApplyUploadCompleteResponse)l_request.createResponse();
            }
            else if (l_request instanceof WEB3AdminAccOpenApplyUploadCancelRequest)
            {
                l_response =
                (WEB3AdminAccOpenApplyUploadCancelResponse)l_request.createResponse();
            }
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
    }

}
@

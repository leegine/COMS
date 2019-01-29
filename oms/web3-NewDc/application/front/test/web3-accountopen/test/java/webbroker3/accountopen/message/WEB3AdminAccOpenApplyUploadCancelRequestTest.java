head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.15.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAccOpenApplyUploadCancelRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccOpenApplyUploadCancelRequestTest extends
        TestBaseForMock {

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyUploadCancelRequestTest.class);
   
    
    public WEB3AdminAccOpenApplyUploadCancelRequestTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCancelRequest.validate()'
     */
    public void testValidate_T001() {

        final String STR_METHOD_NAME = "testValidate_T001()";
        log.entering(STR_METHOD_NAME);
       
        
        WEB3AdminAccOpenApplyUploadCancelRequest l_adminAccOpenApplyUploadCancelRequest =
            new WEB3AdminAccOpenApplyUploadCancelRequest();
        
        l_adminAccOpenApplyUploadCancelRequest.uploadID = null;
        try
        {
            l_adminAccOpenApplyUploadCancelRequest.validate();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00973, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail(); 
        }
        
       
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testValidate_T002() {

        final String STR_METHOD_NAME = "testValidate_T002()";
        log.entering(STR_METHOD_NAME);
       
        
        WEB3AdminAccOpenApplyUploadCancelRequest l_adminAccOpenApplyUploadCancelRequest =
            new WEB3AdminAccOpenApplyUploadCancelRequest();
        
        l_adminAccOpenApplyUploadCancelRequest.uploadID = "";
        try
        {
            l_adminAccOpenApplyUploadCancelRequest.validate();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00973, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail(); 
        }
        
       
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testValidate_T003() {

        final String STR_METHOD_NAME = "testValidate_T003()";
        log.entering(STR_METHOD_NAME);
       
        
        WEB3AdminAccOpenApplyUploadCancelRequest l_adminAccOpenApplyUploadCancelRequest =
            new WEB3AdminAccOpenApplyUploadCancelRequest();
        
        l_adminAccOpenApplyUploadCancelRequest.uploadID = "  ";
        try
        {
            l_adminAccOpenApplyUploadCancelRequest.validate();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00973, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail(); 
        }
        
       
        log.exiting(STR_METHOD_NAME);
        
    }

}
@

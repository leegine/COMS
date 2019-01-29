head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.15.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAccOpenApplyUploadCompleteRequestTest.java;


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

public class WEB3AdminAccOpenApplyUploadCompleteRequestTest extends
        TestBaseForMock {

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyUploadCompleteRequestTest.class);
    
    public WEB3AdminAccOpenApplyUploadCompleteRequestTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCompleteRequest.validate()'
     */
    public void testValidate_T001() {

        final String STR_METHOD_NAME = "testValidate_T001";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenApplyUploadCompleteRequest l_adminAccOpenApplyUploadCompleteRequest =
            new WEB3AdminAccOpenApplyUploadCompleteRequest();
        try
        {
            l_adminAccOpenApplyUploadCompleteRequest.uploadID = "";
            l_adminAccOpenApplyUploadCompleteRequest.validate();

        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
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

        final String STR_METHOD_NAME = "testValidate_T002";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenApplyUploadCompleteRequest l_adminAccOpenApplyUploadCompleteRequest =
            new WEB3AdminAccOpenApplyUploadCompleteRequest();
        try
        {
            l_adminAccOpenApplyUploadCompleteRequest.uploadID = null;
            l_adminAccOpenApplyUploadCompleteRequest.validate();

        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
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

        final String STR_METHOD_NAME = "testValidate_T003";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenApplyUploadCompleteRequest l_adminAccOpenApplyUploadCompleteRequest =
            new WEB3AdminAccOpenApplyUploadCompleteRequest();
        try
        {
            l_adminAccOpenApplyUploadCompleteRequest.uploadID = "   ";
            l_adminAccOpenApplyUploadCompleteRequest.validate();

        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
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

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.12.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTDocumentUpdateConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFPTDocumentUpdateConfirmRequestTest extends
        TestBaseForMock {

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocumentUpdateConfirmRequestTest.class);

    
    public WEB3AdminFPTDocumentUpdateConfirmRequestTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    WEB3AdminFPTDocumentUpdateConfirmRequest l_updateConfirmRequest = new WEB3AdminFPTDocumentUpdateConfirmRequest();
    
    /*
     * Test method for 'webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateConfirmRequest.validate()'
     */
    public void testValidate_T001() 
    {
        final String STR_METHOD_NAME = "testValidate_T001";
        
        log.entering(STR_METHOD_NAME);

        try
        {
            l_updateConfirmRequest.validate();
            fail();
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03030,l_ex.getErrorInfo());
           
            
        }
        
        

        log.exiting(STR_METHOD_NAME);
        
    }

    
    
}
@

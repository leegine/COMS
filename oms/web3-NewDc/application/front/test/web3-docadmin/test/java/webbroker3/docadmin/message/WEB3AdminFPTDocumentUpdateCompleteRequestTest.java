head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.12.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTDocumentUpdateCompleteRequestTest.java;


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

public class WEB3AdminFPTDocumentUpdateCompleteRequestTest extends
        TestBaseForMock {

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocumentUpdateCompleteRequestTest.class);

    
    public WEB3AdminFPTDocumentUpdateCompleteRequestTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    WEB3AdminFPTDocumentUpdateCompleteRequest l_updateCompleteRequest = new WEB3AdminFPTDocumentUpdateCompleteRequest();
    
    /*
     * Test method for 'webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateCompleteRequest.validate()'
     */
    public void testValidate_T001() 
    {

        final String STR_METHOD_NAME = "testValidate_T001";
        
        log.entering(STR_METHOD_NAME);
        
        
//        WEB3AdminFPTDocumentCommonRequest l_request = new WEB3AdminFPTDocumentCommonRequest();
//        l_request.updateProcessFlag = "1";
//        
//        WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[1];
//        
//        documentUpdateList[0] = new WEB3FPTDocumentUpdateInfoUnit();
//        documentUpdateList[0].batoProductCode = "";
//        documentUpdateList[0].documentCategory= "123";
//        documentUpdateList[0].documentCategoryName = "";
//        documentUpdateList[0].documentDiv = "122";
//        documentUpdateList[0].documentNumber = "1234";
//        documentUpdateList[0].remarks = "ìEóvÇÕàÍïSï∂éöà»ì‡Ç≈ê›íËÇµÇƒÇ≠ÇæÇ≥Ç¢ãÊï™";
//        documentUpdateList[0].validFlag = "1";
//        
//        l_request.documentUpdateList = documentUpdateList;

        try
        {

            l_updateCompleteRequest.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03030,l_ex.getErrorInfo());
           
        }
        log.exiting(STR_METHOD_NAME);
    }

    
    
}
@

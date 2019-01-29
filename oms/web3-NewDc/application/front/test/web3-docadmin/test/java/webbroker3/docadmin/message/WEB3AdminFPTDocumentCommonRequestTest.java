head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.12.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTDocumentCommonRequestTest.java;


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

public class WEB3AdminFPTDocumentCommonRequestTest extends TestBaseForMock {

    /**
     * ÉçÉOÉÜÅ[ÉeÉBÉäÉeÉB<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocumentCommonRequestTest.class);

    
    public WEB3AdminFPTDocumentCommonRequestTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

   
    /*
     * Test method for 'webbroker3.docadmin.message.WEB3AdminFPTDocumentCommonRequest.validate()'
     */
    public void testValidate_T001() 
    {
        final String STR_METHOD_NAME = "testValidate_T0()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTDocumentCommonRequest l_request = new WEB3AdminFPTDocumentCommonRequest();
        l_request.updateProcessFlag = "3";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03030,l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testValidate_T002() 
    {
        final String STR_METHOD_NAME = "testValidate_T0()";
        log.entering(STR_METHOD_NAME);
        
        
        WEB3AdminFPTDocumentCommonRequest l_request = new WEB3AdminFPTDocumentCommonRequest();
        l_request.updateProcessFlag = "1";
        l_request.documentUpdateList = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03031,l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        
        
        
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testValidate_T003() 
    {
        final String STR_METHOD_NAME = "testValidate_T0()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentCommonRequest l_request = new WEB3AdminFPTDocumentCommonRequest();
        l_request.updateProcessFlag = "1";
        
        WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[1];
        
        documentUpdateList[0] = new WEB3FPTDocumentUpdateInfoUnit();
        documentUpdateList[0].batoProductCode = "";
        documentUpdateList[0].documentCategory= "";
        documentUpdateList[0].documentCategoryName = "";
        
        documentUpdateList[0].documentDiv = "";
        
        documentUpdateList[0].documentNumber = "";
        documentUpdateList[0].remarks = "";
        documentUpdateList[0].validFlag = "";
        
        
        l_request.documentUpdateList = documentUpdateList;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02948,l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testValidate_T004() 
    {
        final String STR_METHOD_NAME = "testValidate_T0()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentCommonRequest l_request = new WEB3AdminFPTDocumentCommonRequest();
        l_request.updateProcessFlag = "1";
        
        WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[1];
        
        documentUpdateList[0] = new WEB3FPTDocumentUpdateInfoUnit();
        documentUpdateList[0].batoProductCode = "";
        documentUpdateList[0].documentCategory= "";
        documentUpdateList[0].documentCategoryName = "";
        
        documentUpdateList[0].documentDiv = "13ÇR";
        
        documentUpdateList[0].documentNumber = "";
        documentUpdateList[0].remarks = "";
        documentUpdateList[0].validFlag = "";
        
        
        l_request.documentUpdateList = documentUpdateList;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02941,l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        
        
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testValidate_T005() 
    {
        final String STR_METHOD_NAME = "testValidate_T0()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentCommonRequest l_request = new WEB3AdminFPTDocumentCommonRequest();
        l_request.updateProcessFlag = "1";
        
        WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[1];
        
        documentUpdateList[0] = new WEB3FPTDocumentUpdateInfoUnit();
        documentUpdateList[0].batoProductCode = "";
        documentUpdateList[0].documentCategory= "";
        documentUpdateList[0].documentCategoryName = "";
        
        documentUpdateList[0].documentDiv = "1234";
        
        documentUpdateList[0].documentNumber = "";
        documentUpdateList[0].remarks = "";
        documentUpdateList[0].validFlag = "";
        
        
        l_request.documentUpdateList = documentUpdateList;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02942,l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testValidate_T006() 
    {
        final String STR_METHOD_NAME = "testValidate_T0()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentCommonRequest l_request = new WEB3AdminFPTDocumentCommonRequest();
        l_request.updateProcessFlag = "1";
        
        WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[1];
        
        documentUpdateList[0] = new WEB3FPTDocumentUpdateInfoUnit();
        documentUpdateList[0].batoProductCode = "";
        
        documentUpdateList[0].documentCategory= "";
        
        documentUpdateList[0].documentCategoryName = "";
        
        documentUpdateList[0].documentDiv = "122";
        
        documentUpdateList[0].documentNumber = "";
        documentUpdateList[0].remarks = "";
        documentUpdateList[0].validFlag = "";
        
        
        l_request.documentUpdateList = documentUpdateList;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03013,l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testValidate_T007() 
    {
        final String STR_METHOD_NAME = "testValidate_T0()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentCommonRequest l_request = new WEB3AdminFPTDocumentCommonRequest();
        l_request.updateProcessFlag = "1";
        WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[1];
        documentUpdateList[0] = new WEB3FPTDocumentUpdateInfoUnit();
        documentUpdateList[0].batoProductCode = "";
        documentUpdateList[0].documentCategoryName = "";
        documentUpdateList[0].documentDiv = "122";
        documentUpdateList[0].documentNumber = "";
        documentUpdateList[0].remarks = "";
        documentUpdateList[0].validFlag = "";
 
        documentUpdateList[0].documentCategory= "123o";
        
        
        l_request.documentUpdateList = documentUpdateList;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02997,l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testValidate_T008() 
    {
        final String STR_METHOD_NAME = "testValidate_T0()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentCommonRequest l_request = new WEB3AdminFPTDocumentCommonRequest();
        l_request.updateProcessFlag = "1";
        
        WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[1];
        
        documentUpdateList[0] = new WEB3FPTDocumentUpdateInfoUnit();
        documentUpdateList[0].batoProductCode = "";
        documentUpdateList[0].documentCategory= "1234";
        documentUpdateList[0].documentCategoryName = "";
        documentUpdateList[0].documentDiv = "122";
        documentUpdateList[0].documentNumber = "";
        documentUpdateList[0].remarks = "";
        documentUpdateList[0].validFlag = "";
        
        l_request.documentUpdateList = documentUpdateList;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02997,l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);

    }

    public void testValidate_T009() 
    {
        final String STR_METHOD_NAME = "testValidate_T0()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentCommonRequest l_request = new WEB3AdminFPTDocumentCommonRequest();
        l_request.updateProcessFlag = "1";
        
        WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[1];
        
        documentUpdateList[0] = new WEB3FPTDocumentUpdateInfoUnit();
        documentUpdateList[0].batoProductCode = "";
        documentUpdateList[0].documentCategory= "123";
        documentUpdateList[0].documentCategoryName = "";
        documentUpdateList[0].documentDiv = "122";
        documentUpdateList[0].documentNumber = "";
        documentUpdateList[0].remarks = "";
        documentUpdateList[0].validFlag = "";
        
        l_request.documentUpdateList = documentUpdateList;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03032,l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    public void testValidate_T010() 
    {
        final String STR_METHOD_NAME = "testValidate_T0()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentCommonRequest l_request = new WEB3AdminFPTDocumentCommonRequest();
        l_request.updateProcessFlag = "1";
        
        WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[1];
        
        documentUpdateList[0] = new WEB3FPTDocumentUpdateInfoUnit();
        documentUpdateList[0].batoProductCode = "";
        documentUpdateList[0].documentCategory= "123";
        documentUpdateList[0].documentCategoryName = "";
        documentUpdateList[0].documentDiv = "122";
        documentUpdateList[0].documentNumber = "12u";
        documentUpdateList[0].remarks = "";
        documentUpdateList[0].validFlag = "";
        
        l_request.documentUpdateList = documentUpdateList;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03033,l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    public void testValidate_T011() 
    {
        final String STR_METHOD_NAME = "testValidate_T0()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentCommonRequest l_request = new WEB3AdminFPTDocumentCommonRequest();
        l_request.updateProcessFlag = "1";
        
        WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[1];
        
        documentUpdateList[0] = new WEB3FPTDocumentUpdateInfoUnit();
        documentUpdateList[0].batoProductCode = "";
        documentUpdateList[0].documentCategory= "123";
        documentUpdateList[0].documentCategoryName = "";
        documentUpdateList[0].documentDiv = "122";
        documentUpdateList[0].documentNumber = "12345678";
        documentUpdateList[0].remarks = "";
        documentUpdateList[0].validFlag = "";
        
        l_request.documentUpdateList = documentUpdateList;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03033,l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testValidate_T012() 
    {
        final String STR_METHOD_NAME = "testValidate_T0()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentCommonRequest l_request = new WEB3AdminFPTDocumentCommonRequest();
        l_request.updateProcessFlag = "1";
        
        WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[1];
        
        documentUpdateList[0] = new WEB3FPTDocumentUpdateInfoUnit();
        documentUpdateList[0].batoProductCode = "";
        documentUpdateList[0].documentCategory= "123";
        documentUpdateList[0].documentCategoryName = "";
        documentUpdateList[0].documentDiv = "122";
        documentUpdateList[0].documentNumber = "1234";
        documentUpdateList[0].remarks = "ìEóvÇÕa2ìEóvìEóva4 ¡";
        documentUpdateList[0].validFlag = "";
        
        l_request.documentUpdateList = documentUpdateList;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03034,l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testValidate_T013() 
    {
        final String STR_METHOD_NAME = "testValidate_T013()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentCommonRequest l_request = new WEB3AdminFPTDocumentCommonRequest();
        l_request.updateProcessFlag = "1";
        
        WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[1];
        
        documentUpdateList[0] = new WEB3FPTDocumentUpdateInfoUnit();
        documentUpdateList[0].batoProductCode = "";
        documentUpdateList[0].documentCategory= "123";
        documentUpdateList[0].documentCategoryName = "";
        documentUpdateList[0].documentDiv = "122";
        documentUpdateList[0].documentNumber = "1234";
        documentUpdateList[0].remarks = "ÇPÇQÇRÇSÇTÇUÇVÇWÇXÇOÇPÇQÇRÇSÇTÇUÇVÇWÇXÇOÇPÇQÇRÇSÇTÇUÇVÇWÇXÇOÇPÇQÇRÇSÇTÇUÇVÇWÇXÇOÇPÇQÇRÇSÇTÇUÇVÇWÇXÇOÇPÇQÇRÇSÇTÇUÇVÇWÇXÇOÇPÇQÇRÇSÇTÇUÇVÇWÇXÇOÇPÇQÇRÇSÇTÇUÇVÇWÇXÇOÇPÇQÇRÇSÇTÇUÇVÇWÇXÇOÇPÇQÇRÇSÇTÇUÇVÇWÇXÇOÇP";
        documentUpdateList[0].validFlag = "";
        
        l_request.documentUpdateList = documentUpdateList;
        
        
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03035,l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testValidate_T014() 
    {
        final String STR_METHOD_NAME = "testValidate_T014()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentCommonRequest l_request = new WEB3AdminFPTDocumentCommonRequest();
        l_request.updateProcessFlag = "1";
        
        WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[1];
        
        documentUpdateList[0] = new WEB3FPTDocumentUpdateInfoUnit();
        documentUpdateList[0].batoProductCode = "";
        documentUpdateList[0].documentCategory= "123";
        documentUpdateList[0].documentCategoryName = "";
        documentUpdateList[0].documentDiv = "122";
        documentUpdateList[0].documentNumber = "1234";
        documentUpdateList[0].remarks = "ìEóvÇÕàÍïSï∂éöà»ì‡Ç≈ê›íËÇµÇƒÇ≠ÇæÇ≥Ç¢ãÊï™";
        documentUpdateList[0].validFlag = "2";

        l_request.documentUpdateList = documentUpdateList;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03036,l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    //##########
    public void testValidate_T015() 
    {
        final String STR_METHOD_NAME = "testValidate_T015()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentCommonRequest l_request = new WEB3AdminFPTDocumentCommonRequest();
        l_request.updateProcessFlag = "1";
        
        WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[4];
        
        documentUpdateList[0] = new WEB3FPTDocumentUpdateInfoUnit();
        documentUpdateList[0].batoProductCode = "";
        documentUpdateList[0].documentCategory= "222";
        documentUpdateList[0].documentCategoryName = "";
        documentUpdateList[0].documentDiv = "122";
        documentUpdateList[0].documentNumber = "1234";
        documentUpdateList[0].remarks = "ìEóvÇÕàÍïSï∂éöà»ì‡Ç≈ê›íËÇµÇƒÇ≠ÇæÇ≥Ç¢ãÊï™";
        documentUpdateList[0].validFlag = "0";
        
        //22222222222222222222222222222222
        documentUpdateList[1] = new WEB3FPTDocumentUpdateInfoUnit();
        documentUpdateList[1].batoProductCode = "";
        documentUpdateList[1].documentCategory= "222";
        documentUpdateList[1].documentCategoryName = "";
        documentUpdateList[1].documentDiv = "122";
        documentUpdateList[1].documentNumber = "1234";
        documentUpdateList[1].remarks = "ìEóvÇÕàÍïSï∂éöà»ì‡Ç≈ê›íËÇµÇƒÇ≠ÇæÇ≥Ç¢ãÊï™";
        documentUpdateList[1].validFlag = "0";

        //333333333333333333333333333333333333333333
        documentUpdateList[2] = new WEB3FPTDocumentUpdateInfoUnit();
        documentUpdateList[2].batoProductCode = "";
        documentUpdateList[2].documentCategory= "222";
        documentUpdateList[2].documentCategoryName = "";
        documentUpdateList[2].documentDiv = "999";
        documentUpdateList[2].documentNumber = "1234";
        documentUpdateList[2].remarks = "ìEóvÇÕàÍïSï∂éöà»ì‡Ç≈ê›íËÇµÇƒÇ≠ÇæÇ≥Ç¢ãÊï™";
        documentUpdateList[2].validFlag = "1";
        
        //44444444444444444444444444444444444444444
        documentUpdateList[3] = new WEB3FPTDocumentUpdateInfoUnit();
        documentUpdateList[3].batoProductCode = "";
        documentUpdateList[3].documentCategory= "222";
        documentUpdateList[3].documentCategoryName = "";
        documentUpdateList[3].documentDiv = "122";
        documentUpdateList[3].documentNumber = "1234";
        documentUpdateList[3].remarks = "ìEóvÇÕàÍïSï∂éöà»ì‡Ç≈ê›íËÇµÇƒÇ≠ÇæÇ≥Ç¢ãÊï™";
        documentUpdateList[3].validFlag = "1";

        
        l_request.documentUpdateList = documentUpdateList;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03049,l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);

            
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testValidate_T016() 
    {
        final String STR_METHOD_NAME = "testValidate_T016()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentCommonRequest l_request = new WEB3AdminFPTDocumentCommonRequest();
        l_request.updateProcessFlag = "1";
        
        WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[4];
        
        documentUpdateList[0] = new WEB3FPTDocumentUpdateInfoUnit();
        documentUpdateList[0].batoProductCode = "";
        documentUpdateList[0].documentCategory= "123";
        documentUpdateList[0].documentCategoryName = "";
        documentUpdateList[0].documentDiv = "122";
        documentUpdateList[0].documentNumber = "1234";
        documentUpdateList[0].remarks = "ìEóvÇÕàÍïSï∂éöà»ì‡Ç≈ê›íËÇµÇƒÇ≠ÇæÇ≥Ç¢ãÊï™";
        documentUpdateList[0].validFlag = "1";
        
        //22222222222222222222222222222222
        documentUpdateList[1] = new WEB3FPTDocumentUpdateInfoUnit();
        documentUpdateList[1].batoProductCode = "";
        documentUpdateList[1].documentCategory= "009";
        documentUpdateList[1].documentCategoryName = "";
        documentUpdateList[1].documentDiv = "777";
        documentUpdateList[1].documentNumber = "1234";
        documentUpdateList[1].remarks = "ìEóvÇÕàÍïSï∂éöà»ì‡Ç≈ê›íËÇµÇƒÇ≠ÇæÇ≥Ç¢ãÊï™";
        documentUpdateList[1].validFlag = "0";

        //333333333333333333333333333333333333333333
        documentUpdateList[2] = new WEB3FPTDocumentUpdateInfoUnit();
        documentUpdateList[2].batoProductCode = "";
        documentUpdateList[2].documentCategory= "222";
        documentUpdateList[2].documentCategoryName = "";
        documentUpdateList[2].documentDiv = "999";
        documentUpdateList[2].documentNumber = "1234";
        documentUpdateList[2].remarks = "ìEóvÇÕàÍïSï∂éöà»ì‡Ç≈ê›íËÇµÇƒÇ≠ÇæÇ≥Ç¢ãÊï™";
        documentUpdateList[2].validFlag = "0";
        
        //44444444444444444444444444444444444444444
        documentUpdateList[3] = new WEB3FPTDocumentUpdateInfoUnit();
        documentUpdateList[3].batoProductCode = "";
        documentUpdateList[3].documentCategory= "222";
        documentUpdateList[3].documentCategoryName = "";
        documentUpdateList[3].documentDiv = "999";
        documentUpdateList[3].documentNumber = "1234";
        documentUpdateList[3].remarks = "ìEóvÇÕàÍïSï∂éöà»ì‡Ç≈ê›íËÇµÇƒÇ≠ÇæÇ≥Ç¢ãÊï™";
        documentUpdateList[3].validFlag = "0";

        
        l_request.documentUpdateList = documentUpdateList;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03049,l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);

            
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testValidate_T017() 
    {
        final String STR_METHOD_NAME = "testValidate_T017()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentCommonRequest l_request = new WEB3AdminFPTDocumentCommonRequest();
        l_request.updateProcessFlag = "1";
        
        WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList = new WEB3FPTDocumentUpdateInfoUnit[1];
        
        documentUpdateList[0] = new WEB3FPTDocumentUpdateInfoUnit();
        documentUpdateList[0].batoProductCode = "";
        documentUpdateList[0].documentCategory= "123";
        documentUpdateList[0].documentCategoryName = "";
        documentUpdateList[0].documentDiv = "122";
        documentUpdateList[0].documentNumber = "1234";
        documentUpdateList[0].remarks = "ìEóvÇÕàÍïSï∂éöà»ì‡Ç≈ê›íËÇµÇƒÇ≠ÇæÇ≥Ç¢ãÊï™";
        documentUpdateList[0].validFlag = "1";
        
       
        
        l_request.documentUpdateList = documentUpdateList;
        
        try
        {
            l_request.validate();
           
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            fail();
            
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    
}
@

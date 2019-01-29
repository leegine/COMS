head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.12.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTDocumentListReferenceRequestTest.java;


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

public class WEB3AdminFPTDocumentListReferenceRequestTest extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocumentListReferenceRequestTest.class);

    public WEB3AdminFPTDocumentListReferenceRequestTest(String arg0)
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

    public void testValidate01()
    {
        final String STR_METHOD_NAME = "testValidate01()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTDocumentListReferenceRequest l_request =
            new WEB3AdminFPTDocumentListReferenceRequest();

        try
        {
            l_request.documentDivList = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03007,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate02()
    {
        final String STR_METHOD_NAME = "testValidate02()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTDocumentListReferenceRequest l_request =
            new WEB3AdminFPTDocumentListReferenceRequest();

        try
        {
            l_request.documentDivList = new WEB3FPTDocumentDivAdminInfoUnit[1];
            l_request.documentDivList[0] = new WEB3FPTDocumentDivAdminInfoUnit();
            l_request.documentDivList[0].documentDiv = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02948,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate03()
    {
        final String STR_METHOD_NAME = "testValidate03()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTDocumentListReferenceRequest l_request =
            new WEB3AdminFPTDocumentListReferenceRequest();

        try
        {
            l_request.documentDivList = new WEB3FPTDocumentDivAdminInfoUnit[1];
            l_request.documentDivList[0] = new WEB3FPTDocumentDivAdminInfoUnit();
            l_request.documentDivList[0].documentDiv = "12";
            l_request.documentDivList[0].documentDiv = "abc";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02941,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate04()
    {
        final String STR_METHOD_NAME = "testValidate04()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTDocumentListReferenceRequest l_request =
            new WEB3AdminFPTDocumentListReferenceRequest();

        try
        {
            l_request.documentDivList = new WEB3FPTDocumentDivAdminInfoUnit[1];
            l_request.documentDivList[0] = new WEB3FPTDocumentDivAdminInfoUnit();
            l_request.documentDivList[0].documentDiv = "12";
            l_request.documentDivList[0].documentDiv = "123";
            l_request.documentDivList[0].documentDiv = "12345";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02942,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate05()
    {
        final String STR_METHOD_NAME = "testValidate05()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTDocumentListReferenceRequest l_request =
            new WEB3AdminFPTDocumentListReferenceRequest();

        try
        {
            l_request.documentDivList = new WEB3FPTDocumentDivAdminInfoUnit[1];
            l_request.documentDivList[0] = new WEB3FPTDocumentDivAdminInfoUnit();
            l_request.documentDivList[0].documentDiv = "12";
            l_request.documentDivList[0].documentDiv = "123";
            l_request.documentDivList[0].documentDiv = "1";
            l_request.documentDivList[0].documentCategory = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03013,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate06()
    {
        final String STR_METHOD_NAME = "testValidate06()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTDocumentListReferenceRequest l_request =
            new WEB3AdminFPTDocumentListReferenceRequest();

        try
        {
            l_request.documentDivList = new WEB3FPTDocumentDivAdminInfoUnit[1];
            l_request.documentDivList[0] = new WEB3FPTDocumentDivAdminInfoUnit();
            l_request.documentDivList[0].documentDiv = "12";
            l_request.documentDivList[0].documentDiv = "123";
            l_request.documentDivList[0].documentDiv = "1";
            l_request.documentDivList[0].documentCategory = "7";
            l_request.documentDivList[0].documentCategory = "abcd";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02997,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate07()
    {
        final String STR_METHOD_NAME = "testValidate07()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTDocumentListReferenceRequest l_request =
            new WEB3AdminFPTDocumentListReferenceRequest();

        try
        {
            l_request.documentDivList = new WEB3FPTDocumentDivAdminInfoUnit[1];
            l_request.documentDivList[0] = new WEB3FPTDocumentDivAdminInfoUnit();
            l_request.documentDivList[0].documentDiv = "12";
            l_request.documentDivList[0].documentDiv = "123";
            l_request.documentDivList[0].documentDiv = "1";
            l_request.documentDivList[0].documentCategory = "7";
            l_request.documentDivList[0].documentCategory = "56";
            l_request.documentDivList[0].documentCategory = "123456";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02997,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate08()
    {
        final String STR_METHOD_NAME = "testValidate08()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTDocumentListReferenceRequest l_request =
            new WEB3AdminFPTDocumentListReferenceRequest();

        try
        {
            l_request.documentDivList = new WEB3FPTDocumentDivAdminInfoUnit[1];
            l_request.documentDivList[0] = new WEB3FPTDocumentDivAdminInfoUnit();
            l_request.documentDivList[0].documentDiv = "12";
            l_request.documentDivList[0].documentDiv = "123";
            l_request.documentDivList[0].documentDiv = "1";
            l_request.documentDivList[0].documentCategory = "7";
            l_request.documentDivList[0].documentCategory = "56";
            l_request.documentDivList[0].documentCategory = "2";
            l_request.sortKeys = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate09()
    {
        final String STR_METHOD_NAME = "testValidate09()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTDocumentListReferenceRequest l_request =
            new WEB3AdminFPTDocumentListReferenceRequest();

        try
        {
            l_request.documentDivList = new WEB3FPTDocumentDivAdminInfoUnit[1];
            l_request.documentDivList[0] = new WEB3FPTDocumentDivAdminInfoUnit();
            l_request.documentDivList[0].documentDiv = "12";
            l_request.documentDivList[0].documentDiv = "123";
            l_request.documentDivList[0].documentDiv = "1";
            l_request.documentDivList[0].documentCategory = "7";
            l_request.documentDivList[0].documentCategory = "56";
            l_request.documentDivList[0].documentCategory = "2";
            l_request.sortKeys = new WEB3AdminFPTSortKey[1];
            l_request.sortKeys[0] = new WEB3AdminFPTSortKey();
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@

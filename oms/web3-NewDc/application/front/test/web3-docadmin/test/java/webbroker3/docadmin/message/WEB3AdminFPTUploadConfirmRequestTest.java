head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.11.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTUploadConfirmRequestTest.java;


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

public class WEB3AdminFPTUploadConfirmRequestTest extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTUploadConfirmRequestTest.class);

    public WEB3AdminFPTUploadConfirmRequestTest(String arg0)
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

        WEB3AdminFPTUploadConfirmRequest l_request =
            new WEB3AdminFPTUploadConfirmRequest();

        try
        {
            l_request.uploadFile = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00976,l_ex.getErrorInfo());
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

        WEB3AdminFPTUploadConfirmRequest l_request =
            new WEB3AdminFPTUploadConfirmRequest();

        try
        {
            l_request.uploadFile = new String[0];
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00976,l_ex.getErrorInfo());
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

        WEB3AdminFPTUploadConfirmRequest l_request =
            new WEB3AdminFPTUploadConfirmRequest();

        try
        {
            l_request.uploadFile = new String[1];
            l_request.statusDiv = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01249,l_ex.getErrorInfo());
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

        WEB3AdminFPTUploadConfirmRequest l_request =
            new WEB3AdminFPTUploadConfirmRequest();

        try
        {
            l_request.uploadFile = new String[1];
            l_request.statusDiv = "";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01250,l_ex.getErrorInfo());
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

        WEB3AdminFPTUploadConfirmRequest l_request =
            new WEB3AdminFPTUploadConfirmRequest();

        try
        {
            l_request.uploadFile = new String[1];
            l_request.statusDiv = "0";
            l_request.validate();
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

        WEB3AdminFPTUploadConfirmRequest l_request =
            new WEB3AdminFPTUploadConfirmRequest();

        try
        {
            l_request.uploadFile = new String[1];
            l_request.statusDiv = "1";
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

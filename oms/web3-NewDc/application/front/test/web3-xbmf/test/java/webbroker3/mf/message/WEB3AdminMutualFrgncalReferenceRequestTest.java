head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.58.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminMutualFrgncalReferenceRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.handler.WEB3AdminMutualConditionsHandlerTest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminMutualFrgncalReferenceRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminMutualConditionsHandlerTest.class);

    public WEB3AdminMutualFrgncalReferenceRequestTest(String name)
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

    public void testValidate_C0001()
    {
        String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminMutualFrgncalReferenceRequest l_request =
            new WEB3AdminMutualFrgncalReferenceRequest();
        l_request.mutualProductCode = null;

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00079, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0002()
    {
        String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminMutualFrgncalReferenceRequest l_request =
            new WEB3AdminMutualFrgncalReferenceRequest();
        l_request.mutualProductCode = "";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00079, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0003()
    {
        String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminMutualFrgncalReferenceRequest l_request =
            new WEB3AdminMutualFrgncalReferenceRequest();
        l_request.mutualProductCode = "120";
        l_request.searchYM = null;

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00429, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0004()
    {
        String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminMutualFrgncalReferenceRequest l_request =
            new WEB3AdminMutualFrgncalReferenceRequest();
        l_request.mutualProductCode = "120";
        l_request.searchYM = "";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00429, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0005()
    {
        String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminMutualFrgncalReferenceRequest l_request =
            new WEB3AdminMutualFrgncalReferenceRequest();
        l_request.mutualProductCode = "120";
        l_request.searchYM = "20y810";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00430, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0006()
    {
        String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminMutualFrgncalReferenceRequest l_request =
            new WEB3AdminMutualFrgncalReferenceRequest();
        l_request.mutualProductCode = "120";
        l_request.searchYM = "20081012";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00431, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0007()
    {
        String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminMutualFrgncalReferenceRequest l_request =
            new WEB3AdminMutualFrgncalReferenceRequest();
        l_request.mutualProductCode = "120";
        l_request.searchYM = "200815";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00432, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0008()
    {
        String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminMutualFrgncalReferenceRequest l_request =
            new WEB3AdminMutualFrgncalReferenceRequest();
        l_request.mutualProductCode = "120";
        l_request.searchYM = "200811";

        try
        {
            l_request.validate();
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage());
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@

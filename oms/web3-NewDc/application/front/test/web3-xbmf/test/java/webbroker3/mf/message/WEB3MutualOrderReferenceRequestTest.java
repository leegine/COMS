head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.57.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualOrderReferenceRequestTest.java;


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

public class WEB3MutualOrderReferenceRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminMutualConditionsHandlerTest.class);

    public WEB3MutualOrderReferenceRequestTest(String name)
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

        WEB3MutualOrderReferenceRequest l_request =
            new WEB3MutualOrderReferenceRequest();

        l_request.referenceType = null;
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00081, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0002()
    {
        String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualOrderReferenceRequest l_request =
            new WEB3MutualOrderReferenceRequest();

        l_request.referenceType = "2";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00082, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0003()
    {
        String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualOrderReferenceRequest l_request =
            new WEB3MutualOrderReferenceRequest();

        l_request.referenceType = "0";
        l_request.pageIndex = null;
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0004()
    {
        String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualOrderReferenceRequest l_request =
            new WEB3MutualOrderReferenceRequest();

        l_request.referenceType = "0";
        l_request.pageIndex = "10s1";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0005()
    {
        String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualOrderReferenceRequest l_request =
            new WEB3MutualOrderReferenceRequest();

        l_request.referenceType = "0";
        l_request.pageIndex = "101";
        l_request.pageSize = null;
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00091, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0006()
    {
        String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualOrderReferenceRequest l_request =
            new WEB3MutualOrderReferenceRequest();

        l_request.referenceType = "0";
        l_request.pageIndex = "101";
        l_request.pageSize = "1s21";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0007()
    {
        String STR_METHOD_NAME = "testValidate_C0007)";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualOrderReferenceRequest l_request =
            new WEB3MutualOrderReferenceRequest();

        l_request.referenceType = "0";
        l_request.pageIndex = "101";
        l_request.pageSize = "121";
        l_request.sortKeys = null;
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0008()
    {
        String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualOrderReferenceRequest l_request =
            new WEB3MutualOrderReferenceRequest();

        l_request.referenceType = "0";
        l_request.pageIndex = "101";
        l_request.pageSize = "121";
        l_request.sortKeys = new WEB3MutualSortKey[0];
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00232, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0009()
    {
        String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualOrderReferenceRequest l_request =
            new WEB3MutualOrderReferenceRequest();

        l_request.referenceType = "0";
        l_request.pageIndex = "101";
        l_request.pageSize = "121";
        l_request.sortKeys = new WEB3MutualSortKey[2];
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C00010()
    {
        String STR_METHOD_NAME = "testValidate_C00010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualOrderReferenceRequest l_request =
            new WEB3MutualOrderReferenceRequest();

        l_request.referenceType = "0";
        l_request.pageIndex = "101";
        l_request.pageSize = "121";
        WEB3MutualSortKey l_mutualSortKey = new WEB3MutualSortKey();
        l_mutualSortKey.keyItem = null;
        l_mutualSortKey.ascDesc = "A";
        l_request.sortKeys = new WEB3MutualSortKey[2];
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C00011()
    {
        String STR_METHOD_NAME = "testValidate_C00011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualOrderReferenceRequest l_request =
            new WEB3MutualOrderReferenceRequest();

        l_request.referenceType = "0";
        l_request.pageIndex = "101";
        l_request.pageSize = "121";
        WEB3MutualSortKey l_mutualSortKey = new WEB3MutualSortKey();
        l_mutualSortKey.keyItem = "Test";
        l_mutualSortKey.ascDesc = "A";
        l_request.sortKeys = new WEB3MutualSortKey[1];
        l_request.sortKeys[0] = l_mutualSortKey;
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C00012()
    {
        String STR_METHOD_NAME = "testValidate_C00012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualOrderReferenceRequest l_request =
            new WEB3MutualOrderReferenceRequest();

        l_request.referenceType = "0";
        l_request.pageIndex = "101";
        l_request.pageSize = "121";
        WEB3MutualSortKey l_mutualSortKey = new WEB3MutualSortKey();
        l_mutualSortKey.keyItem = "taxType";
        l_mutualSortKey.ascDesc = null;
        l_request.sortKeys = new WEB3MutualSortKey[1];
        l_request.sortKeys[0] = l_mutualSortKey;
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00087, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C00013()
    {
        String STR_METHOD_NAME = "testValidate_C00013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualOrderReferenceRequest l_request =
            new WEB3MutualOrderReferenceRequest();

        l_request.referenceType = "0";
        l_request.pageIndex = "101";
        l_request.pageSize = "121";
        WEB3MutualSortKey l_mutualSortKey = new WEB3MutualSortKey();
        l_mutualSortKey.keyItem = "taxType";
        l_mutualSortKey.ascDesc = "C";
        l_request.sortKeys = new WEB3MutualSortKey[1];
        l_request.sortKeys[0] = l_mutualSortKey;
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00088, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C00014()
    {
        String STR_METHOD_NAME = "testValidate_C00014()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualOrderReferenceRequest l_request =
            new WEB3MutualOrderReferenceRequest();

        l_request.referenceType = "0";
        l_request.pageIndex = "101";
        l_request.pageSize = "121";
        WEB3MutualSortKey l_mutualSortKey = new WEB3MutualSortKey();
        l_mutualSortKey.keyItem = "taxType";
        l_mutualSortKey.ascDesc = "A";
        l_request.sortKeys = new WEB3MutualSortKey[1];
        l_request.sortKeys[0] = l_mutualSortKey;
        try
        {
            l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage());
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@

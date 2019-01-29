head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.58.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminMutualDisplayOrderCompleteRequestTest.java;


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

public class WEB3AdminMutualDisplayOrderCompleteRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminMutualConditionsHandlerTest.class);

    public WEB3AdminMutualDisplayOrderCompleteRequestTest(String name)
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

        WEB3AdminMutualDisplayOrderCompleteRequest l_request =
            new WEB3AdminMutualDisplayOrderCompleteRequest();
        l_request.displayOrderChangeList = null;

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01273, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0002()
    {
        String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminMutualDisplayOrderCompleteRequest l_request =
            new WEB3AdminMutualDisplayOrderCompleteRequest();
        l_request.displayOrderChangeList = new WEB3MutualDisplayOrderChangeUnit[0];

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01274, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0003()
    {
        String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminMutualDisplayOrderCompleteRequest l_request =
            new WEB3AdminMutualDisplayOrderCompleteRequest();
        WEB3MutualDisplayOrderChangeUnit l_unit1 =
            new WEB3MutualDisplayOrderChangeUnit();
        WEB3MutualDisplayOrderChangeUnit l_unit2 =
            new WEB3MutualDisplayOrderChangeUnit();
        l_unit1.displayOrder = "120";
        l_unit1.mutualProductCode = "123456";
        l_unit2.displayOrder = "12er3";
        l_unit2.mutualProductCode = "12345678";
        l_request.displayOrderChangeList = new WEB3MutualDisplayOrderChangeUnit[2];
        l_request.displayOrderChangeList[0] = l_unit1;
        l_request.displayOrderChangeList[1] = l_unit2;

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01276, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    public void testValidate_C0004()
    {
        String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminMutualDisplayOrderCompleteRequest l_request =
            new WEB3AdminMutualDisplayOrderCompleteRequest();
        WEB3MutualDisplayOrderChangeUnit l_unit1 =
            new WEB3MutualDisplayOrderChangeUnit();
        WEB3MutualDisplayOrderChangeUnit l_unit2 =
            new WEB3MutualDisplayOrderChangeUnit();
        l_unit1.displayOrder = null;
        l_unit1.mutualProductCode = "123456";
        l_unit2.displayOrder = "12er3";
        l_unit2.mutualProductCode = "12345678";
        l_request.displayOrderChangeList = new WEB3MutualDisplayOrderChangeUnit[2];
        l_request.displayOrderChangeList[0] = l_unit1;
        l_request.displayOrderChangeList[1] = l_unit2;

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01276, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    public void testValidate_C0005()
    {
        String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminMutualDisplayOrderCompleteRequest l_request =
            new WEB3AdminMutualDisplayOrderCompleteRequest();
        WEB3MutualDisplayOrderChangeUnit l_unit1 =
            new WEB3MutualDisplayOrderChangeUnit();
        WEB3MutualDisplayOrderChangeUnit l_unit2 =
            new WEB3MutualDisplayOrderChangeUnit();
        l_unit1.displayOrder = "120";
        l_unit1.mutualProductCode = "123e6";
        l_unit2.displayOrder = "123";
        l_unit2.mutualProductCode = null;
        l_request.displayOrderChangeList = new WEB3MutualDisplayOrderChangeUnit[2];
        l_request.displayOrderChangeList[0] = l_unit1;
        l_request.displayOrderChangeList[1] = l_unit2;

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01277, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0006()
    {
        String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminMutualDisplayOrderCompleteRequest l_request =
            new WEB3AdminMutualDisplayOrderCompleteRequest();
        WEB3MutualDisplayOrderChangeUnit l_unit1 =
            new WEB3MutualDisplayOrderChangeUnit();
        WEB3MutualDisplayOrderChangeUnit l_unit2 =
            new WEB3MutualDisplayOrderChangeUnit();
        l_unit1.displayOrder = "120";
        l_unit1.mutualProductCode = "123e6";
        l_unit2.displayOrder = "123";
        l_unit2.mutualProductCode = "";
        l_request.displayOrderChangeList = new WEB3MutualDisplayOrderChangeUnit[2];
        l_request.displayOrderChangeList[0] = l_unit1;
        l_request.displayOrderChangeList[1] = l_unit2;

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01277, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0007()
    {
        String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminMutualDisplayOrderCompleteRequest l_request =
            new WEB3AdminMutualDisplayOrderCompleteRequest();
        WEB3MutualDisplayOrderChangeUnit l_unit1 =
            new WEB3MutualDisplayOrderChangeUnit();
        WEB3MutualDisplayOrderChangeUnit l_unit2 =
            new WEB3MutualDisplayOrderChangeUnit();
        l_unit1.displayOrder = "120";
        l_unit1.mutualProductCode = "123e6";
        l_unit2.displayOrder = "123";
        l_unit2.mutualProductCode = "1234678";
        l_request.displayOrderChangeList = new WEB3MutualDisplayOrderChangeUnit[2];
        l_request.displayOrderChangeList[0] = l_unit1;
        l_request.displayOrderChangeList[1] = l_unit2;

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

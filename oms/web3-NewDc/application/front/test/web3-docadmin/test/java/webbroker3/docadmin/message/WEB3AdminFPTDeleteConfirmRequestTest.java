head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.12.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTDeleteConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFPTDeleteConfirmRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDeleteConfirmRequestTest.class);

    public WEB3AdminFPTDeleteConfirmRequestTest(String arg0)
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

    public void testValidate_0001()
    {
        final String STR_METHOD_NAME = "testValidate_0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTDeleteConfirmRequest l_request = new WEB3AdminFPTDeleteConfirmRequest();
        try
        {
            l_request.validate();

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02784, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_0002()
    {
        final String STR_METHOD_NAME = "testValidate_0002()";
        log.entering(STR_METHOD_NAME);

        WEB3FPTHistoryInfoUnit l_unit = new WEB3FPTHistoryInfoUnit();
        WEB3AdminFPTDeleteConfirmRequest l_request = new WEB3AdminFPTDeleteConfirmRequest();
        try
        {
            l_request.financialProductTradeDeleteRow = l_unit;
            l_request.validate();

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00833, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_0003()
    {
        final String STR_METHOD_NAME = "testValidate_0003()";
        log.entering(STR_METHOD_NAME);

        WEB3FPTHistoryInfoUnit l_unit = new WEB3FPTHistoryInfoUnit();
        l_unit.branchCode = "123";

        WEB3AdminFPTDeleteConfirmRequest l_request = new WEB3AdminFPTDeleteConfirmRequest();
        try
        {
            l_request.financialProductTradeDeleteRow = l_unit;
            l_request.validate();

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00835, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_0004()
    {
        final String STR_METHOD_NAME = "testValidate_0004()";
        log.entering(STR_METHOD_NAME);

        WEB3FPTHistoryInfoUnit l_unit = new WEB3FPTHistoryInfoUnit();
        l_unit.branchCode = "123";
        l_unit.acceptCode = "123";

        WEB3AdminFPTDeleteConfirmRequest l_request = new WEB3AdminFPTDeleteConfirmRequest();
        try
        {
            l_request.financialProductTradeDeleteRow = l_unit;
            l_request.validate();

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02948, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

//    public void testValidate_0005()
//    {
//        final String STR_METHOD_NAME = "testValidate_0005()";
//        log.entering(STR_METHOD_NAME);
//
//        WEB3FPTHistoryInfoUnit l_unit = new WEB3FPTHistoryInfoUnit();
//        l_unit.branchCode = "123";
//        l_unit.acceptCode = "123";
//        l_unit.documentDiv = "1";
//
//        WEB3AdminFPTDeleteConfirmRequest l_request = new WEB3AdminFPTDeleteConfirmRequest();
//        try
//        {
//            l_request.financialProductTradeDeleteRow = l_unit;
//            l_request.validate();
//
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00079, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//
//        log.exiting(STR_METHOD_NAME);
//    }

    public void testValidate_0006()
    {
        final String STR_METHOD_NAME = "testValidate_0006()";
        log.entering(STR_METHOD_NAME);

        WEB3FPTHistoryInfoUnit l_unit = new WEB3FPTHistoryInfoUnit();
        l_unit.branchCode = "123";
        l_unit.acceptCode = "123";
        l_unit.documentDiv = "1";
        l_unit.batoProductCode = "1";
        l_unit.docuDeliDate = null;

        WEB3AdminFPTDeleteConfirmRequest l_request = new WEB3AdminFPTDeleteConfirmRequest();
        try
        {
            l_request.financialProductTradeDeleteRow = l_unit;
            l_request.validate();

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02943, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_0007()
    {
        final String STR_METHOD_NAME = "testValidate_0007()";
        log.entering(STR_METHOD_NAME);

        WEB3FPTHistoryInfoUnit l_unit = new WEB3FPTHistoryInfoUnit();
        l_unit.branchCode = "123";
        l_unit.acceptCode = "123";
        l_unit.documentDiv = "1";
        l_unit.batoProductCode = "1";
        l_unit.docuDeliDate = new Date();
        l_unit.documentCategory = null;

        WEB3AdminFPTDeleteConfirmRequest l_request = new WEB3AdminFPTDeleteConfirmRequest();
        try
        {
            l_request.financialProductTradeDeleteRow = l_unit;
            l_request.validate();

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03013, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_0008()
    {
        final String STR_METHOD_NAME = "testValidate_0008()";
        log.entering(STR_METHOD_NAME);

        WEB3FPTHistoryInfoUnit l_unit = new WEB3FPTHistoryInfoUnit();
        l_unit.branchCode = "123";
        l_unit.acceptCode = "123";
        l_unit.documentDiv = "1";
        l_unit.batoProductCode = "1";
        l_unit.docuDeliDate = new Date();
        l_unit.documentCategory = new String();

        WEB3AdminFPTDeleteConfirmRequest l_request = new WEB3AdminFPTDeleteConfirmRequest();
        try
        {
            l_request.financialProductTradeDeleteRow = l_unit;
            l_request.validate();

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@

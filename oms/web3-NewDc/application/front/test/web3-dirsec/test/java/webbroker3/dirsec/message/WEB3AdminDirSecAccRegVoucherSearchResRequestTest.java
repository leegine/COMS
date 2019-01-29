head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.59.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecAccRegVoucherSearchResRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminDirSecAccRegVoucherSearchResRequestTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminDirSecAccRegVoucherSearchResRequestTest.class);
    WEB3AdminDirSecAccRegVoucherSearchResRequest l_request = null;
    public WEB3AdminDirSecAccRegVoucherSearchResRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        l_request = new WEB3AdminDirSecAccRegVoucherSearchResRequest();
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testValidate_T01()
    {
        final String STR_METHOD_NAME = "testValidate_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_request.branchCode = null;
            l_request.validate();
            fail();
        }
        catch(WEB3BusinessLayerException l_bexc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02833, l_bexc.getErrorInfo());
            log.debug("STR_METHOD_NAME " + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_T02()
    {
        final String STR_METHOD_NAME = "testValidate_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_request.accountCode = null;
            l_request.validate();
            fail();
        }
        catch(WEB3BusinessLayerException l_bexc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02833, l_bexc.getErrorInfo());
            log.debug("STR_METHOD_NAME " + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T03()
    {
        final String STR_METHOD_NAME = "testValidate_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_request.accountCode = "456";
            l_request.branchCode = "12g";
            l_request.validate();
            fail();
        }
        catch(WEB3BusinessLayerException l_bexc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00779, l_bexc.getErrorInfo());
            log.debug("STR_METHOD_NAME " + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T04()
    {
        final String STR_METHOD_NAME = "testValidate_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_request.accountCode = "456";
            l_request.branchCode = "12２";
            l_request.validate();
            fail();
        }
        catch(WEB3BusinessLayerException l_bexc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00779, l_bexc.getErrorInfo());
            log.debug("STR_METHOD_NAME " + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T05()
    {
        final String STR_METHOD_NAME = "testValidate_T05()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_request.branchCode = "456";
            l_request.accountCode = "12g";
            l_request.validate();
            fail();
        }
        catch(WEB3BusinessLayerException l_bexc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00780, l_bexc.getErrorInfo());
            log.debug("STR_METHOD_NAME " + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T06()
    {
        final String STR_METHOD_NAME = "testValidate_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_request.branchCode = "456";
            l_request.accountCode = "12２";
            l_request.validate();
            fail();
        }
        catch(WEB3BusinessLayerException l_bexc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00780, l_bexc.getErrorInfo());
            log.debug("STR_METHOD_NAME " + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T07()
    {
        final String STR_METHOD_NAME = "testValidate_T07()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_request.branchCode = "456";
            l_request.accountCode = "798";
            l_request.dataCode = "123456";
            l_request.validate();
            fail();
        }
        catch(WEB3BusinessLayerException l_bexc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02828, l_bexc.getErrorInfo());
            log.debug("STR_METHOD_NAME " + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T08()
    {
        final String STR_METHOD_NAME = "testValidate_T08()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_request.branchCode = "456";
            l_request.accountCode = "798";
            l_request.dataCode = "12ｇｈ";
            l_request.validate();
            fail();
        }
        catch(WEB3BusinessLayerException l_bexc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02828, l_bexc.getErrorInfo());
            log.debug("STR_METHOD_NAME " + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T09()
    {
        final String STR_METHOD_NAME = "testValidate_T09()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_request.branchCode = "456";
            l_request.accountCode = "798";
            l_request.dataCode = "12４５";
            l_request.validate();
            fail();
        }
        catch(WEB3BusinessLayerException l_bexc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02828, l_bexc.getErrorInfo());
            log.debug("STR_METHOD_NAME " + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T10()
    {
        final String STR_METHOD_NAME = "testValidate_T10()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_request.branchCode = "456";
            l_request.accountCode = "798";
            l_request.dataCode = "123gh";
            l_request.voucherSendDate = "1234567";
            l_request.validate();
            fail();
        }
        catch(WEB3BusinessLayerException l_bexc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02834, l_bexc.getErrorInfo());
            log.debug("STR_METHOD_NAME " + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_T11()
    {
        final String STR_METHOD_NAME = "testValidate_T11()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_request.branchCode = "456";
            l_request.accountCode = "798";
            l_request.dataCode = "12345";
            l_request.voucherSendDate = "12345678";
            l_request.validate();
            fail();
        }
        catch(WEB3BusinessLayerException l_bexc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02834, l_bexc.getErrorInfo());
            log.debug("STR_METHOD_NAME " + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T12()
    {
        final String STR_METHOD_NAME = "testValidate_T12()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_request.branchCode = "456";
            l_request.accountCode = "798";
            l_request.dataCode = "hjklk";
            l_request.voucherSendDate = "20070203";
            l_request.validate();
            log.debug("STR_METHOD_NAME " + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.29.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformDetailRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;

public class WEB3AdminInformDetailRequestTest extends TestBaseForMock
{

    public WEB3AdminInformDetailRequestTest(String arg0)
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
        WEB3AdminInformDetailRequest l_request = new WEB3AdminInformDetailRequest();
        //連絡種別
        l_request.informType = "aa";
        //部店コード
        l_request.branchCode = null;
        try
        {
            l_request.validate();

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00833 , l_ex.getErrorInfo());
        }
    }
    
    public void testValidate_0002()
    {
        WEB3AdminInformDetailRequest l_request = new WEB3AdminInformDetailRequest();
        //連絡種別
        l_request.informType = "a1";
        //部店コード
        l_request.branchCode = null;
        try
        {
            l_request.validate();

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00833 , l_ex.getErrorInfo());
        }
    }
    
    public void testValidate_0003()
    {
        WEB3AdminInformDetailRequest l_request = new WEB3AdminInformDetailRequest();
        //連絡種別
        l_request.informType = "11";
        //部店コード
        l_request.branchCode = null;
        try
        {
            l_request.validate();

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00833 , l_ex.getErrorInfo());
        }
    }
    
    public void testValidate_0004()
    {
        WEB3AdminInformDetailRequest l_request = new WEB3AdminInformDetailRequest();
        //連絡種別
        l_request.informType = "ああ";
        //部店コード
        l_request.branchCode = null;
        try
        {
            l_request.validate();

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02778 , l_ex.getErrorInfo());
        }
    }
    
    public void testValidate_0005()
    {
        WEB3AdminInformDetailRequest l_request = new WEB3AdminInformDetailRequest();
        //連絡種別
        l_request.informType = "#a";
        //部店コード
        l_request.branchCode = null;
        try
        {
            l_request.validate();
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02778 , l_ex.getErrorInfo());
        }
    }

}
@

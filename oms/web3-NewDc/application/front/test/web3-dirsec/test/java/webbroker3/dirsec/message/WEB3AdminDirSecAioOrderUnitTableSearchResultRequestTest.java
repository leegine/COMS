head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.00.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecAioOrderUnitTableSearchResultRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;


public class WEB3AdminDirSecAioOrderUnitTableSearchResultRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecAioOrderUnitTableSearchResultRequestTest.class);

    public WEB3AdminDirSecAioOrderUnitTableSearchResultRequestTest(String arg0)
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
        final String STR_METHOD_NAME = " testValidate_0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminDirSecAioOrderUnitTableSearchResultRequest l_request =
            new WEB3AdminDirSecAioOrderUnitTableSearchResultRequest();
        l_request.orderUnitId = "12345";
        l_request.orderUnitTblKbn = "5";
        
        try
        {
            l_request.validate();
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02705, l_ex.getErrorInfo());
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
        final String STR_METHOD_NAME = " testValidate_0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminDirSecAioOrderUnitTableSearchResultRequest l_request =
            new WEB3AdminDirSecAioOrderUnitTableSearchResultRequest();
        l_request.orderUnitId = "12345";
        l_request.orderUnitTblKbn = "2";
        
        try
        {
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

    //２－２）this.注文単位テーブル区分 =3の場合、
    public void testValidate_0003()
    {
        final String STR_METHOD_NAME = " testValidate_0003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminDirSecAioOrderUnitTableSearchResultRequest l_request =
            new WEB3AdminDirSecAioOrderUnitTableSearchResultRequest();
        l_request.orderUnitId = "12345";
        l_request.orderUnitTblKbn = "3";
        
        try
        {
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

    //２－２）this.注文単位テーブル区分 =4の場合、
    public void testValidate_0004()
    {
        final String STR_METHOD_NAME = " testValidate_0004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminDirSecAioOrderUnitTableSearchResultRequest l_request =
            new WEB3AdminDirSecAioOrderUnitTableSearchResultRequest();
        l_request.orderUnitId = "12345";
        l_request.orderUnitTblKbn = "3";
        
        try
        {
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

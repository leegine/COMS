head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.40.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AccInfoExecMailDistributionChangeCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccInfoExecMailDistributionChangeCompleteRequestTest extends
    TestBaseForMock
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoExecMailDistributionChangeCompleteRequestTest.class);
    
    WEB3AccInfoExecMailDistributionChangeCompleteRequest l_request;

    public WEB3AccInfoExecMailDistributionChangeCompleteRequestTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_request = new WEB3AccInfoExecMailDistributionChangeCompleteRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    /**
     * 銘柄タイプ区分のチェック未入力の場合、例外をスローする。
     */
    public void testValidate_Case001()
    {
        final String STR_METHOD_NAME = "testValidate_Case001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.productTypeDiv = null;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01109, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_Case002()
    {
        final String STR_METHOD_NAME = "testValidate_Case002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.productTypeDiv = "";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01109, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * １－２）銘柄タイプ区分不正なコード値の場合、例外をスローする。
     */

    public void testValidate_Case003()
    {
        final String STR_METHOD_NAME = "testValidate_Case003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.productTypeDiv = "2";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01110, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ２－１）約定／未約定区分未入力の場合、例外をスローする。
     */
    public void testValidate_Case004()
    {
        final String STR_METHOD_NAME = "testValidate_Case004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.productTypeDiv = "1";
            l_request.execDiv = "";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01111, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    

    public void testValidate_Case005()
    {
        final String STR_METHOD_NAME = "testValidate_Case005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.productTypeDiv = "1";
            l_request.execDiv = null;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01111, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    /**
     * ２－２）約定／未約定区分不正なコード値の場合、例外をスローする。
     */
    public void testValidate_Case006()
    {
        final String STR_METHOD_NAME = "testValidate_Case006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.productTypeDiv = "1";
            l_request.execDiv = "3";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01112, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 全部正常通過。
     */
    public void testValidate_Case007()
    {
        final String STR_METHOD_NAME = "testValidate_Case007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.productTypeDiv = "1";
            l_request.execDiv = "0";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);

        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    

}
@

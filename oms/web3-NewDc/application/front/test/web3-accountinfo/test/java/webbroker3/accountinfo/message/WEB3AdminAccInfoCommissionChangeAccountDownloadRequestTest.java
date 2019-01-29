head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.39.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AdminAccInfoCommissionChangeAccountDownloadRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.message;

import java.text.DateFormat;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccInfoCommissionChangeAccountDownloadRequestTest extends
    TestBaseForMock
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCommissionChangeAccountDownloadRequestTest.class);
    
    WEB3AdminAccInfoCommissionChangeAccountDownloadRequest l_request;

    public WEB3AdminAccInfoCommissionChangeAccountDownloadRequestTest(
            String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_request = new WEB3AdminAccInfoCommissionChangeAccountDownloadRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    /**
     * １－１）適用開始日未入力の場合、例外をスローする。
     */
    public void testValidate_Case001()
    {
        final String STR_METHOD_NAME = "testValidate_Case001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.trialStartDate = null;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00837, l_ex.getErrorInfo());
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
     * ２－２）要求ページ番号数字以外の文字が含まれる場合、例外をスローする。
     */
    public void testValidate_Case002()
    {
        final String STR_METHOD_NAME = "testValidate_Case002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.trialStartDate = WEB3DateUtility.getDate("20080105", "yyyyMMdd");
            l_request.pageIndex = "xy";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, l_ex.getErrorInfo());
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
    
    public void testValidate_Case003()
    {
        final String STR_METHOD_NAME = "testValidate_Case003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.trialStartDate = WEB3DateUtility.getDate("20080105", "yyyyMMdd");
            l_request.pageIndex = "1.2";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, l_ex.getErrorInfo());
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
     * ２－３）要求ページ番号マイナス値の場合、例外をスローする。
     */

    public void testValidate_Case004()
    {
        final String STR_METHOD_NAME = "testValidate_Case004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.trialStartDate = WEB3DateUtility.getDate("20080105", "yyyyMMdd");
            l_request.pageIndex = "-2";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00616, l_ex.getErrorInfo());
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
     * ３－１）ページ内表示行数未入力の場合、例外をスローする。
     */
    public void testValidate_Case005()
    {
        final String STR_METHOD_NAME = "testValidate_Case005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.trialStartDate = WEB3DateUtility.getDate("20080105", "yyyyMMdd");
            l_request.pageIndex = "15";
            l_request.pageSize = null;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00091, l_ex.getErrorInfo());
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
    
    public void testValidate_Case006()
    {
        final String STR_METHOD_NAME = "testValidate_Case006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.trialStartDate = WEB3DateUtility.getDate("20080105", "yyyyMMdd");
            l_request.pageIndex = "15";
            l_request.pageSize = "";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00091, l_ex.getErrorInfo());
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
     * ３－２）数字以外の文字が含まれる場合、例外をスローする。
     */
    public void testValidate_Case007()
    {
        final String STR_METHOD_NAME = "testValidate_Case007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.trialStartDate = WEB3DateUtility.getDate("20080105", "yyyyMMdd");
            l_request.pageIndex = "15";
            l_request.pageSize = "12.3";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092, l_ex.getErrorInfo());
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
     * ３－３）マイナス値の場合、例外をスローする。
     */
    public void testValidate_Case008()
    {
        final String STR_METHOD_NAME = "testValidate_Case008()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.trialStartDate = WEB3DateUtility.getDate("20080105", "yyyyMMdd");
            l_request.pageIndex = "15";
            l_request.pageSize = "-10";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00617, l_ex.getErrorInfo());
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
     * ２－１）要求ページ番号未入力の場合、 要求ページ番号に”１”をセットする。
     */
    public void testValidate_Case009()
    {
        final String STR_METHOD_NAME = "testValidate_Case009()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.trialStartDate = WEB3DateUtility.getDate("20080105", "yyyyMMdd");
            l_request.pageIndex = null;
            l_request.pageSize = "12";
            l_request.validate();
            assertEquals("1", l_request.pageIndex);
            log.exiting(STR_METHOD_NAME);
        }
        catch(WEB3BusinessLayerException l_ex)
        {
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

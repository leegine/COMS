head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.00.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecTriggerIssueListRequestTest.java;


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

public class WEB3AdminDirSecTriggerIssueListRequestTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecTriggerIssueListRequestTest.class);

    WEB3AdminDirSecTriggerIssueListRequest l_request;
    public WEB3AdminDirSecTriggerIssueListRequestTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_request = new WEB3AdminDirSecTriggerIssueListRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * this.ページ内表示行数 == nullの場合、「ページ内表示行数の入力が不正です。」
     * の例外をスローする。
     */
    public void testValidate_Case249()
    {
        final String STR_METHOD_NAME = "testValidate_Case249";
        log.entering(STR_METHOD_NAME);

        try
        {
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

    /**
     * this.ページ内表示行数が数字以外の値であった場合、「ページ内表示行数が数字以外の値です。」
     * の例外をスローする。
     */
    public void testValidate_Case250()
    {
        final String STR_METHOD_NAME = "testValidate_Case250";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "a";
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
     * this.ページ内表示行数が数字以外の値であった場合、「ページ内表示行数が数字以外の値です。」
     * の例外をスローする。
     */
    public void testValidate_Case251()
    {
        final String STR_METHOD_NAME = "testValidate_Case251";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1.23";
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
     * this.ページ内表示行数が数字以外の値であった場合、「ページ内表示行数が数字以外の値です。」
     * の例外をスローする。
     */
    public void testValidate_Case252()
    {
        final String STR_METHOD_NAME = "testValidate_Case252";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "-1";
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
     * this.ページ内表示行数 <= 0であった場合、「ページ内表示行数の値が0以下です。」
     * の例外をスローする。
     */
    public void testValidate_Case253()
    {
        final String STR_METHOD_NAME = "testValidate_Case253";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "0";
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
     * this.表示ページ番号 == nullの場合、「要求ページ番号が未指定です。」の例外をスローする。
     */
    public void testValidate_Case254()
    {
        final String STR_METHOD_NAME = "testValidate_Case254";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1";
            l_request.pageIndex = null;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089, l_ex.getErrorInfo());
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
     * this.表示ページ番号 == nullの場合、「要求ページ番号が未指定です。」
     * の例外をスローする。
     * 表示ページ番号 == ""不抛出BUSINESS_ERROR_00089
     */
    public void testValidate_Case255()
    {
        final String STR_METHOD_NAME = "testValidate_Case255";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1";
            l_request.pageIndex = "";
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
     * this.表示ページ番号が数字以外の値であった場合、
     * 「要求ページ番号が数字以外の値です。」の例外をスローする。
     */
    public void testValidate_Case256()
    {
        final String STR_METHOD_NAME = "testValidate_Case256";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_request.pageSize = "1";
            l_request.pageIndex = "a";
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
     * this.表示ページ番号が数字以外の値であった場合、
     * 「要求ページ番号が数字以外の値です。」の例外をスローする。
     */
    public void testValidate_Case257()
    {
        final String STR_METHOD_NAME = "testValidate_Case257";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1";
            l_request.pageIndex = "1.45";
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
     * this.表示ページ番号が数字以外の値であった場合、
     * 「要求ページ番号が数字以外の値です。」の例外をスローする。
     */
    public void testValidate_Case258()
    {
        final String STR_METHOD_NAME = "testValidate_Case258";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1";
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
     * this.表示ページ番号 <= 0であった場合、「要求ページ番号の値が0以下です。」
     * の例外をスローする。
     */
    public void testValidate_Case259()
    {
        final String STR_METHOD_NAME = "testValidate_Case259";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1";
            l_request.pageIndex = "0";
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
     * this.トリガー発行ソートキー == nullであった場合「ソートキーがnull」
     * の例外をスローする。
     */
    public void testValidate_Case260()
    {

        final String STR_METHOD_NAME = "testValidate_Case260";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1";
            l_request.pageIndex = "2";
            l_request.sortKeys = null;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex.getErrorInfo());
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
     * this.トリガー発行ソートキー.length == 0だった場合「ソートキー.要素数が0」
     * の例外をスローする。
     */
    public void testValidate_Case261()
    {
        final String STR_METHOD_NAME = "testValidate_Case261";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1";
            l_request.pageIndex = "2";
            l_request.sortKeys = new WEB3AdminDirSecTriggerIssueSortKey[0];
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00232, l_ex.getErrorInfo());
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
     * this.トリガー発行ソートキー.length = 1 の場合、
     * トリガー発行ソートキー.validate()をコールする。
     */
    public void testValidate_Case262()
    {
        final String STR_METHOD_NAME = "testValidate_Case262";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1";
            l_request.pageIndex = "2";
            l_request.sortKeys = new WEB3AdminDirSecTriggerIssueSortKey[1];
            l_request.sortKeys[0] = new WEB3AdminDirSecTriggerIssueSortKey();
            l_request.sortKeys[0].keyItem = null;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_ex.getErrorInfo());
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
     * this.トリガー発行ソートキー.length = 3 の場合、
     * トリガー発行ソートキー.validate()をコールする。
     */
    public void testValidate_Case263()
    {
        final String STR_METHOD_NAME = "testValidate_Case263";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1";
            l_request.pageIndex = "2";
            l_request.sortKeys = new WEB3AdminDirSecTriggerIssueSortKey[3];
            l_request.sortKeys[0] = new WEB3AdminDirSecTriggerIssueSortKey();
            l_request.sortKeys[0].keyItem = "kkk";
            l_request.sortKeys[0].ascDesc = "A";
            l_request.sortKeys[1] = new WEB3AdminDirSecTriggerIssueSortKey();
            l_request.sortKeys[1].keyItem = null;
            l_request.sortKeys[1].ascDesc = "A";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_ex.getErrorInfo());
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
     * 全部正常
     */
    public void testValidate_Case264()
    {
        final String STR_METHOD_NAME = "testValidate_Case264";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1";
            l_request.pageIndex = "2";
            l_request.sortKeys = new WEB3AdminDirSecTriggerIssueSortKey[3];
            l_request.sortKeys[0] = new WEB3AdminDirSecTriggerIssueSortKey();
            l_request.sortKeys[0].keyItem = "mmm";
            l_request.sortKeys[0].ascDesc = "A";
            l_request.sortKeys[1] = new WEB3AdminDirSecTriggerIssueSortKey();
            l_request.sortKeys[1].keyItem = "www";
            l_request.sortKeys[1].ascDesc = "D";
            l_request.sortKeys[2] = new WEB3AdminDirSecTriggerIssueSortKey();
            l_request.sortKeys[2].keyItem = "kkk";
            l_request.sortKeys[2].ascDesc = "A";
            l_request.validate();

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

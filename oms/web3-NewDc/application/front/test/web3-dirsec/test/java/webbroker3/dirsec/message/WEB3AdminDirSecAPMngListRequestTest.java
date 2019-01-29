head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.00.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecAPMngListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3AdminDirSecAPMngListRequestTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/07/23 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminDirSecAPMngListRequestTest extends TestBaseForMock
{
    WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecAPMngListRequestTest.class);
        
    WEB3AdminDirSecAPMngListRequest l_request = null;
        
    public WEB3AdminDirSecAPMngListRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_request = new WEB3AdminDirSecAPMngListRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //１）ページ内表示行数チェック
    //　@１－１）this.ページ内表示行数 == nullの場合
    //　@　@　@　@「ページ内表示行数の入力が不正です。」の例外をスローする。
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.pageSize = null;
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00091, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //１－２）this.ページ内表示行数が半角数字以外の値であった場合、
    //　@　@　@　@「ページ内表示行数が数字以外の値です。」の例外をスローする
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "aa";
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //１－３）this.ページ内表示行数 <= 0であった場合、
    //　@　@　@「ページ内表示行数の値が0以下です。」の例外をスローする。
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "0";
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00617, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //２）表示番号チェック
    //　@２－１）this.表示ページ番号 == nullの場合、
    //　@　@　@　@「要求ページ番号が未指定です。」の例外をスローする。
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1111";
            l_request.pageIndex = null;
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //２－２）this.表示ページ番号が半角数字以外の値であった場合、
    //　@　@　@「要求ページ番号が数字以外の値です。」の例外をスローする。
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1111";
            l_request.pageIndex = "aaa";
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //２－３）this.表示ページ番号 <= 0であった場合、
    //　@「要求ページ番号の値が0以下です。」の例外をスローする。
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1111";
            l_request.pageIndex = "0";
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00616, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //３）　@ソートキーチェック
    //　@３－１）this.ソートキー == nullであった場合
    //　@　@　@「ソートキーがnull」の例外をスローする。
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1111";
            l_request.pageIndex = "1111";
            l_request.sortKeys = null;
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //３－２）this.ソートキー.length == 0だった場合
    //　@「ソートキー.要素数が0」の例外をスローする。
    public void testValidate_C0008()
    {
        final String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1111";
            l_request.pageIndex = "1111";
            l_request.sortKeys =new WEB3AdminDirSecAPMngForcedStartSortKey[]{};
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00232, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //３－３）this.ソートキーの全要素に対して下記のチェックを行う。
    //３－３－１）ソートキー.validate()をコールする。
    //第1個元素出現異常
    public void testValidate_C0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1111";
            l_request.pageIndex = "1111";
            l_request.sortKeys =new WEB3AdminDirSecAPMngForcedStartSortKey[]{
                new WEB3AdminDirSecAPMngForcedStartSortKey(),
                new WEB3AdminDirSecAPMngForcedStartSortKey(),
                new WEB3AdminDirSecAPMngForcedStartSortKey()};
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //３－３）this.ソートキーの全要素に対して下記のチェックを行う。
    //３－３－１）ソートキー.validate()をコールする。
    //第2個元素出現異常
    public void testValidate_C0010()
    {
        final String STR_METHOD_NAME = "testValidate_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1111";
            l_request.pageIndex = "1111";
            l_request.sortKeys =new WEB3AdminDirSecAPMngForcedStartSortKey[]{
                new WEB3AdminDirSecAPMngForcedStartSortKey(),
                new WEB3AdminDirSecAPMngForcedStartSortKey(),
                new WEB3AdminDirSecAPMngForcedStartSortKey()};
            l_request.sortKeys[0].keyItem = "1111";
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_request.sortKeys[0].ascDesc, WEB3AscDescDef.ASC);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //３－３）this.ソートキーの全要素に対して下記のチェックを行う。
    //３－３－１）ソートキー.validate()をコールする。
    //第3個元素出現異常
    public void testValidate_C0011()
    {
        final String STR_METHOD_NAME = "testValidate_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1111";
            l_request.pageIndex = "1111";
            l_request.sortKeys =new WEB3AdminDirSecAPMngForcedStartSortKey[]{
                new WEB3AdminDirSecAPMngForcedStartSortKey(),
                new WEB3AdminDirSecAPMngForcedStartSortKey(),
                new WEB3AdminDirSecAPMngForcedStartSortKey()};
            l_request.sortKeys[0].keyItem = "1111";
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            l_request.sortKeys[1].keyItem = "1111";
            l_request.sortKeys[1].ascDesc = WEB3AscDescDef.ASC;
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_request.sortKeys[1].ascDesc, WEB3AscDescDef.ASC);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //normalcase
    public void testValidate_C0012()
    {
        final String STR_METHOD_NAME = "testValidate_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1111";
            l_request.pageIndex = "1111";
            l_request.sortKeys =new WEB3AdminDirSecAPMngForcedStartSortKey[]{
                new WEB3AdminDirSecAPMngForcedStartSortKey(),
                new WEB3AdminDirSecAPMngForcedStartSortKey(),
                new WEB3AdminDirSecAPMngForcedStartSortKey()};
            l_request.sortKeys[0].keyItem = "1111";
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            l_request.sortKeys[1].keyItem = "1111";
            l_request.sortKeys[1].ascDesc = WEB3AscDescDef.ASC;
            l_request.sortKeys[2].keyItem = "1111";
            l_request.sortKeys[2].ascDesc = WEB3AscDescDef.ASC;
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@

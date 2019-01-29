head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.08.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTraderAdminIPControlListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・ログイン拒否IP一覧リクエスト(WEB3AdminTraderAdminIPControlListRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 張騰宇 (中訊) 新規作成 モデル004
*/

package webbroker3.trademanagement.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTraderAdminIPControlListRequestTest extends TestBaseForMock
{

    public WEB3AdminTraderAdminIPControlListRequestTest(String name)
    {
        super(name);
    }

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTraderAdminIPControlListRequestTest.class);

    WEB3AdminTraderAdminIPControlListRequest l_request = new WEB3AdminTraderAdminIPControlListRequest();
    
    /**
     * this.ページ内表示行数 == nullの場合、
     * 「ページ内表示行数の入力が不正です。」の例外をスローする。 
     */
    public void test_validate_0001()
    {
        String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(STR_METHOD_NAME);

        //ページ内表示行数
        l_request.pageSize = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00091, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.ページ内表示行数が半角数字以外の値であった場合、 
     * 「ページ内表示行数が数字以外の値です。」の例外をスローする。 
     */
    public void test_validate_0002()
    {
        String STR_METHOD_NAME = "test_validate_0002()";
        log.entering(STR_METHOD_NAME);

        //ページ内表示行数
        l_request.pageSize = "aa";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.ページ内表示行数 <= 0であった場合、 
     * 「ページ内表示行数の値が0以下です。」の例外をスローする。
     */
    public void test_validate_0003()
    {
        String STR_METHOD_NAME = "test_validate_0003()";
        log.entering(STR_METHOD_NAME);
        
        //ページ内表示行数
        l_request.pageSize = "0";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00617, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.ページ内表示行数="1"
     * this.要求ページ番号 == nullの場合、
     * 「要求ページ番号が未指定です。」の例外をスローする。 
     */
    public void test_validate_0004()
    {
        String STR_METHOD_NAME = "test_validate_0004()";
        log.entering(STR_METHOD_NAME);
        
        //ページ内表示行数
        l_request.pageSize = "1";
        //要求ページ番号
        l_request.pageIndex = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.ページ内表示行数="1"
     * this.要求ページ番号が半角数字以外の値であった場合、 
     * 「要求ページ番号が数字以外の値です。」の例外をスローする。
     */
    public void test_validate_0005()
    {
        String STR_METHOD_NAME = "test_validate_0005()";
        log.entering(STR_METHOD_NAME);

        //ページ内表示行数
        l_request.pageSize = "1";
        //要求ページ番号
        l_request.pageIndex = "a";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.ページ内表示行数="1"
     * this.要求ページ番号 <= 0であった場合、 
     * 「要求ページ番号の値が0以下です。」の例外をスローする。
     */
    public void test_validate_0006()
    {
        String STR_METHOD_NAME = "test_validate_0006()";
        log.entering(STR_METHOD_NAME);

        //ページ内表示行数
        l_request.pageSize = "1";
        //要求ページ番号
        l_request.pageIndex = "0";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00616, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.ページ内表示行数="1"
     * this.要求ページ番号="1"
     * this.ソートキー == nullであった場合
     * 「ソートキーがnull」の例外をスローする。
     */
    public void test_validate_0007()
    {
        String STR_METHOD_NAME = "test_validate_0007()";
        log.entering(STR_METHOD_NAME);

        //ページ内表示行数
        l_request.pageSize = "1";
        //要求ページ番号
        l_request.pageIndex = "1";
        //ソートキー
        l_request.sortKeys = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.ページ内表示行数="1"
     * this.要求ページ番号="1"
     * this.ソートキー.length == 0だった場合
     * 「ソートキー.要素数が0」の例外をスローする。
     */
    public void test_validate_0008()
    {
        String STR_METHOD_NAME = "test_validate_0008()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTraderAdminIPControlSortKey[] l_sortKeys = new WEB3AdminTraderAdminIPControlSortKey[]{};
        
        //ページ内表示行数
        l_request.pageSize = "1";
        //要求ページ番号
        l_request.pageIndex = "1";
        //ソートキー
        l_request.sortKeys = l_sortKeys;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00232, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.ページ内表示行数="1"
     * this.要求ページ番号="1"
     * ソートキー.validate()をコールする
     */
    public void test_validate_0009()
    {
        String STR_METHOD_NAME = "test_validate_0009()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminTraderAdminIPControlSortKey l_sortKey= new WEB3AdminTraderAdminIPControlSortKey();
        l_sortKey.keyItem = null;
        WEB3AdminTraderAdminIPControlSortKey[] l_sortKeys = new WEB3AdminTraderAdminIPControlSortKey[]{l_sortKey};
        
        //ページ内表示行数
        l_request.pageSize = "1";
        //要求ページ番号
        l_request.pageIndex = "1";
        //ソートキー
        l_request.sortKeys = l_sortKeys;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.07.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTraderAdminLoginCountListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・IP別ログイン回数一覧検索結果リクエスト(WEB3AdminTraderAdminLoginCountListRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 孟亞南 (中訊) 新規作成 モデルNo.005
*/
package webbroker3.trademanagement.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTraderAdminLoginCountListRequestTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTraderAdminLoginCountListRequestTest.class);
    
    WEB3AdminTraderAdminLoginCountListRequest l_request = new WEB3AdminTraderAdminLoginCountListRequest();
    
    public WEB3AdminTraderAdminLoginCountListRequestTest(String name)
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
    
    /**
     * this.日付 == nullの場合は
     * 「オペレーション日付が未指定です。」 
     */
    public void test_validate_0001()
    {
        String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(STR_METHOD_NAME);

        l_request.searchDate = null;
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01272, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.日付の入力形式が 'yyyymmdd' ではない場合は、 
     * 「日付が不正です。」
     */
    public void test_validate_0002()
    {
        String STR_METHOD_NAME = "test_validate_0002()";
        log.entering(STR_METHOD_NAME);

        l_request.searchDate = "2008-08-08";
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02994, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.日付="20080922" 
     * this.時間(自) == nullの場合は、
     * 「時間(自)がnullです。」の例外をスローする
     */
    public void test_validate_0003()
    {
        String STR_METHOD_NAME = "test_validate_0003()";
        log.entering(STR_METHOD_NAME);

        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03118, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.日付="20080922" 
     * this.時間(自)の入力形式が 'hh24mi' ではない場合は、 
     * 「表示期間（自）日付フォーマットエラー。」 
     */
    public void test_validate_0004()
    {
        String STR_METHOD_NAME = "test_validate_0004()";
        log.entering(STR_METHOD_NAME);

        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "12:30";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01065, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.日付="20080922" 
     * this.時間(自)="1230"
     * this.時間(至) == nullの場合は、
     * 　@「時間(至)がnullです。」の例外をスローする
     */
    public void test_validate_0005()
    {
        String STR_METHOD_NAME = "test_validate_0005()";
        log.entering(STR_METHOD_NAME);

        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1230";
        //時間(至)
        l_request.endTime = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03119, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.日付="20080922" 
     * this.時間(自)="1230"
     * this.時間(至)の入力形式が 'hh24mi' ではない場合は、 
     * 「表示期間（至）日付フォーマットエラー。」 
     */
    public void test_validate_0006()
    {
        String STR_METHOD_NAME = "test_validate_0006()";
        log.entering(STR_METHOD_NAME);

        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1230";
        //時間(至)
        l_request.endTime = "232301";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01066, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.日付="20080922" 
     * this.時間(自)="2330"
     * this.時間(至)="2323"
     * this.時間(自) > this.時間(至) の場合は
     * 「表示期間（自）は表示期間（至）より大きいです。」 
     */
    public void test_validate_0007()
    {
        String STR_METHOD_NAME = "test_validate_0007()";
        log.entering(STR_METHOD_NAME);

        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "2330";
        //時間(至)
        l_request.endTime = "2323";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01051, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.日付="20080922" 
     * this.時間(自)="1030"
     * this.時間(至)="1420"
     * [this.時間(至) - this.時間(自)] > １時間 の場合は、 
     * 「指定範囲は１時間以内で入力してください。」の例外をスローする。 
     */
    public void test_validate_0008()
    {
        String STR_METHOD_NAME = "test_validate_0008()";
        log.entering(STR_METHOD_NAME);

        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1030";
        //時間(至)
        l_request.endTime = "1420";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03128, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.日付="20080922" 
     * this.時間(自)="1030"
     * this.時間(至)="1120"
     * this.ランク == nullの場合は、 
     * 「ランクが未入力です。」の例外をスローする。
     */
    public void test_validate_0009()
    {
        String STR_METHOD_NAME = "test_validate_0009()";
        log.entering(STR_METHOD_NAME);

        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1030";
        //時間(至)
        l_request.endTime = "1120";
        //ランク
        l_request.rank = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03129, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.日付="20080922" 
     * this.時間(自)="1030"
     * this.時間(至)="1120"
     * this.ランク に半角数字以外の文字が入っていた場合は 
     * 「ランクは半角数字で入力してください。」の例外をスローする。
     */
    public void test_validate_0010()
    {
        String STR_METHOD_NAME = "test_validate_0010()";
        log.entering(STR_METHOD_NAME);

        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1030";
        //時間(至)
        l_request.endTime = "1120";
        //ランク
        l_request.rank = "aad";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03130, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.日付="20080922" 
     * this.時間(自)="1030"
     * this.時間(至)="1120"
     * this.ランク > 30 の場合は、
     * 「ランクは上位30位までの表示しかできません。」の例外をスローする。 
     */
    public void test_validate_0011()
    {
        String STR_METHOD_NAME = "test_validate_0011()";
        log.entering(STR_METHOD_NAME);

        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1030";
        //時間(至)
        l_request.endTime = "1120";
        //ランク
        l_request.rank = "45";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03131, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.日付="20080922" 
     * this.時間(自)="1030"
     * this.時間(至)="1120"
     * this.ランク="30"
     * this.ページ内表示行数 == nullの場合、
     * 「ページ内表示行数の入力が不正です。」の例外をスローする。 
     */
    public void test_validate_0012()
    {
        String STR_METHOD_NAME = "test_validate_0012()";
        log.entering(STR_METHOD_NAME);

        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1030";
        //時間(至)
        l_request.endTime = "1120";
        //ランク
        l_request.rank = "30";
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
     * this.日付="20080922" 
     * this.時間(自)="1030"
     * this.時間(至)="1120"
     * this.ランク="30"
     * this.ページ内表示行数が半角数字以外の値であった場合、 
     * 「ページ内表示行数が数字以外の値です。」の例外をスローする。 
     */
    public void test_validate_0013()
    {
        String STR_METHOD_NAME = "test_validate_0013()";
        log.entering(STR_METHOD_NAME);

        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1030";
        //時間(至)
        l_request.endTime = "1120";
        //ランク
        l_request.rank = "30";
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
     * this.日付="20080922" 
     * this.時間(自)="1030"
     * this.時間(至)="1120"
     * this.ランク="30"
     * this.ページ内表示行数 <= 0であった場合、 
     * 「ページ内表示行数の値が0以下です。」の例外をスローする。
     */
    public void test_validate_0014()
    {
        String STR_METHOD_NAME = "test_validate_0014()";
        log.entering(STR_METHOD_NAME);

        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1030";
        //時間(至)
        l_request.endTime = "1120";
        //ランク
        l_request.rank = "30";
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
     * this.日付="20080922" 
     * this.時間(自)="1030"
     * this.時間(至)="1120"
     * this.ランク="30"
     * this.ページ内表示行数="1"
     * this.要求ページ番号 == nullの場合、
     * 「要求ページ番号が未指定です。」の例外をスローする。 
     */
    public void test_validate_0015()
    {
        String STR_METHOD_NAME = "test_validate_0015()";
        log.entering(STR_METHOD_NAME);

        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1030";
        //時間(至)
        l_request.endTime = "1120";
        //ランク
        l_request.rank = "30";
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
     * this.日付="20080922" 
     * this.時間(自)="1030"
     * this.時間(至)="1120"
     * this.ランク="30"
     * this.ページ内表示行数="1"
     * this.要求ページ番号が半角数字以外の値であった場合、 
     * 「要求ページ番号が数字以外の値です。」の例外をスローする。
     */
    public void test_validate_0016()
    {
        String STR_METHOD_NAME = "test_validate_0016()";
        log.entering(STR_METHOD_NAME);

        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1030";
        //時間(至)
        l_request.endTime = "1120";
        //ランク
        l_request.rank = "30";
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
     * this.日付="20080922" 
     * this.時間(自)="1030"
     * this.時間(至)="1120"
     * this.ランク="30"
     * this.ページ内表示行数="1"
     * this.要求ページ番号 <= 0であった場合、 
     * 「要求ページ番号の値が0以下です。」の例外をスローする。
     */
    public void test_validate_0017()
    {
        String STR_METHOD_NAME = "test_validate_0017()";
        log.entering(STR_METHOD_NAME);

        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1030";
        //時間(至)
        l_request.endTime = "1120";
        //ランク
        l_request.rank = "30";
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
     * this.日付="20080922" 
     * this.時間(自)="1030"
     * this.時間(至)="1120"
     * this.ランク="30"
     * this.ページ内表示行数="1"
     * this.要求ページ番号="1"
     */
    public void test_validate_0018()
    {
        String STR_METHOD_NAME = "test_validate_0018()";
        log.entering(STR_METHOD_NAME);

        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1030";
        //時間(至)
        l_request.endTime = "1120";
        //ランク
        l_request.rank = "30";
        //ページ内表示行数
        l_request.pageSize = "1";
        //要求ページ番号
        l_request.pageIndex = "1";
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            fail();
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@

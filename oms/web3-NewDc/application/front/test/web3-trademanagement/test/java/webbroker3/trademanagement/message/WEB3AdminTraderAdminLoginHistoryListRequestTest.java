head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.08.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTraderAdminLoginHistoryListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・ログイン処理一覧検索結果リクエスト(WEB3AdminTraderAdminLoginHistoryListRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 孟亞南 (中訊) 新規作成 モデルNo.005
*/
package webbroker3.trademanagement.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTraderAdminLoginHistoryListRequestTest extends TestBaseForMock 
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3AdminTraderAdminLoginHistoryListRequestTest.class);

    WEB3AdminTraderAdminLoginHistoryListRequest l_request =
        new WEB3AdminTraderAdminLoginHistoryListRequest();

    public WEB3AdminTraderAdminLoginHistoryListRequestTest(String name)
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
     * [this.時間(至) - this.時間(自)] > ３時間 の場合は
     * 「指定範囲は３時間以内で入力してください。」の例外をスローする
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
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03120, l_ex.getErrorInfo());
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
     * this.時間(至)="1220"
     * this.ソートキー == nullであった場合 
     * 「ソートキーがnull」の例外をスローする。
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
        l_request.endTime = "1220";
        //ソートキー
        l_request.sortKeys = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog. BUSINESS_ERROR_00231, l_ex.getErrorInfo());
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
     * this.時間(至)="1220"
     * this.ソートキー.length == 0だった場合 
     * 「ソートキー.要素数が0」の例外をスローする。 
     */
    public void test_validate_0010()
    {
        String STR_METHOD_NAME = "test_validate_0010()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys = new WEB3AdminTraderAdminLoginHistorySortKey[]{};
        
        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1030";
        //時間(至)
        l_request.endTime = "1220";
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
     * this.日付="20080922" 
     * this.時間(自)="1030"
     * this.時間(至)="1220"
     * this.ソートキーの全要素に対して下記のチェックを行う。 
     * ソートキー.validate()をコールする。 
     */
    public void test_validate_0011()
    {
        String STR_METHOD_NAME = "test_validate_0011()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminTraderAdminLoginHistorySortKey l_sortKey = new WEB3AdminTraderAdminLoginHistorySortKey();
        l_sortKey.keyItem = null;
        WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys = new WEB3AdminTraderAdminLoginHistorySortKey[]{l_sortKey};
        
        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1030";
        //時間(至)
        l_request.endTime = "1220";
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
    
    /**
     * this.日付="20080922" 
     * this.時間(自)="1030"
     * this.時間(至)="1220"
     * this.ソートキー=object
     * this.部店 !=　@null の場合、下記処理を実施。  
     * this.部店 の値が半角数字以外の場合は、
     * 「部店コードが数値以外の値です。」 
     */
    public void test_validate_0012()
    {
        String STR_METHOD_NAME = "test_validate_0012()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminTraderAdminLoginHistorySortKey l_sortKey = new WEB3AdminTraderAdminLoginHistorySortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = "loginDate";
        WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys = new WEB3AdminTraderAdminLoginHistorySortKey[]{l_sortKey};
        
        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1030";
        //時間(至)
        l_request.endTime = "1220";
        //ソートキー
        l_request.sortKeys = l_sortKeys;
        //部店
        l_request.branchCode = "aaaa";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01729, l_ex.getErrorInfo());
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
     * this.時間(至)="1220"
     * this.ソートキー=object
     * this.部店="222"
     * this.顧客コード !=　@null の場合、下記処理を実施。 
     * this.顧客コード の値が半角数字以外の場合は、
     * 「顧客コードの値が数字以外の値です。」
     */
    public void test_validate_0014()
    {
        String STR_METHOD_NAME = "test_validate_0014()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminTraderAdminLoginHistorySortKey l_sortKey = new WEB3AdminTraderAdminLoginHistorySortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = "loginDate";
        WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys = new WEB3AdminTraderAdminLoginHistorySortKey[]{l_sortKey};
        
        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1030";
        //時間(至)
        l_request.endTime = "1220";
        //ソートキー
        l_request.sortKeys = l_sortKeys;
        //部店
        l_request.branchCode = "222";
        //顧客コード
        l_request.accountCode = "AA";
        
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01043, l_ex.getErrorInfo());
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
     * this.時間(至)="1220"
     * this.ソートキー=object
     * this.部店="222"
     * this.顧客コード="666666"
     * this.IPアドレス !=　@null の場合、下記処理を実施。 
     * WEB3StringTypeUtility.isIpAddress()==false 
     * の場合「IPアドレスの値が不正です。」の例外をスローする。
     */
    public void test_validate_0016()
    {
        String STR_METHOD_NAME = "test_validate_0016()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminTraderAdminLoginHistorySortKey l_sortKey = new WEB3AdminTraderAdminLoginHistorySortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = "loginDate";
        WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys = new WEB3AdminTraderAdminLoginHistorySortKey[]{l_sortKey};
        
        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1030";
        //時間(至)
        l_request.endTime = "1220";
        //ソートキー
        l_request.sortKeys = l_sortKeys;
        //部店
        l_request.branchCode = "222";
        //顧客コード
        l_request.accountCode = "666666";
        //IPアドレス
        l_request.ipAddress = "dasdasd";
 
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03121, l_ex.getErrorInfo());
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
     * this.時間(至)="1220"
     * this.ソートキー=object
     * this.部店="222"
     * this.顧客コード="666666"
     * this.IPアドレス="1325655"
     * this.ページ内表示行数 == nullの場合、 
     * 「ページ内表示行数の入力が不正です。」の例外をスローする。
     */
    public void test_validate_0017()
    {
        String STR_METHOD_NAME = "test_validate_0017()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminTraderAdminLoginHistorySortKey l_sortKey = new WEB3AdminTraderAdminLoginHistorySortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = "loginDate";
        WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys = new WEB3AdminTraderAdminLoginHistorySortKey[]{l_sortKey};
        
        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1030";
        //時間(至)
        l_request.endTime = "1220";
        //ソートキー
        l_request.sortKeys = l_sortKeys;
        //部店
        l_request.branchCode = "222";
        //顧客コード
        l_request.accountCode = "666666";
        //IPアドレス
        l_request.ipAddress = "1325655";
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
     * this.時間(至)="1220"
     * this.ソートキー=object
     * this.部店="222"
     * this.顧客コード="666666"
     * this.IPアドレス="1325655"
     * this.ページ内表示行数が半角数字以外の値であった場合、 
     * 「ページ内表示行数が数字以外の値です。」の例外をスローする。 
     */
    public void test_validate_0018()
    {
        String STR_METHOD_NAME = "test_validate_0018()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminTraderAdminLoginHistorySortKey l_sortKey = new WEB3AdminTraderAdminLoginHistorySortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = "loginDate";
        WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys = new WEB3AdminTraderAdminLoginHistorySortKey[]{l_sortKey};
        
        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1030";
        //時間(至)
        l_request.endTime = "1220";
        //ソートキー
        l_request.sortKeys = l_sortKeys;
        //部店
        l_request.branchCode = "222";
        //顧客コード
        l_request.accountCode = "666666";
        //IPアドレス
        l_request.ipAddress = "1325655";
        //ページ内表示行数
        l_request.pageSize = "aaaa";
 
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
     * this.時間(至)="1220"
     * this.ソートキー=object
     * this.部店="222"
     * this.顧客コード="666666"
     * this.IPアドレス="1325655"
     * this.ページ内表示行数 <= 0であった場合、 
     * 「ページ内表示行数の値が0以下です。」の例外をスローする。 
     */
    public void test_validate_0019()
    {
        String STR_METHOD_NAME = "test_validate_0019()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminTraderAdminLoginHistorySortKey l_sortKey = new WEB3AdminTraderAdminLoginHistorySortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = "loginDate";
        WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys = new WEB3AdminTraderAdminLoginHistorySortKey[]{l_sortKey};
        
        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1030";
        //時間(至)
        l_request.endTime = "1220";
        //ソートキー
        l_request.sortKeys = l_sortKeys;
        //部店
        l_request.branchCode = "222";
        //顧客コード
        l_request.accountCode = "666666";
        //IPアドレス
        l_request.ipAddress = "1325655";
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
     * this.時間(至)="1220"
     * this.ソートキー=object
     * this.部店="222"
     * this.顧客コード="666666"
     * this.IPアドレス="1325655"
     * this.ページ内表示行数="1" 
     * this.要求ページ番号 == nullの場合、 
     * 「要求ページ番号が未指定です。」の例外をスローする。
     */
    public void test_validate_0020()
    {
        String STR_METHOD_NAME = "test_validate_0020()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminTraderAdminLoginHistorySortKey l_sortKey = new WEB3AdminTraderAdminLoginHistorySortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = "loginDate";
        WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys = new WEB3AdminTraderAdminLoginHistorySortKey[]{l_sortKey};
        
        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1030";
        //時間(至)
        l_request.endTime = "1220";
        //ソートキー
        l_request.sortKeys = l_sortKeys;
        //部店
        l_request.branchCode = "222";
        //顧客コード
        l_request.accountCode = "666666";
        //IPアドレス
        l_request.ipAddress = "1325655";
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
     * this.時間(至)="1220"
     * this.ソートキー=object
     * this.部店="222"
     * this.顧客コード="666666"
     * this.IPアドレス="1325655"
     * this.ページ内表示行数="1" 
     * this.要求ページ番号が半角数字以外の値であった場合、 
     * 「要求ページ番号が数字以外の値です。」の例外をスローする。
     */
    public void test_validate_0021()
    {
        String STR_METHOD_NAME = "test_validate_0021()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminTraderAdminLoginHistorySortKey l_sortKey = new WEB3AdminTraderAdminLoginHistorySortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = "loginDate";
        WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys = new WEB3AdminTraderAdminLoginHistorySortKey[]{l_sortKey};
        
        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1030";
        //時間(至)
        l_request.endTime = "1220";
        //ソートキー
        l_request.sortKeys = l_sortKeys;
        //部店
        l_request.branchCode = "222";
        //顧客コード
        l_request.accountCode = "666666";
        //IPアドレス
        l_request.ipAddress = "1325655";
        //ページ内表示行数
        l_request.pageSize = "1";
        //要求ページ番号
        l_request.pageIndex = "aaa";
        
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
     * this.時間(至)="1220"
     * this.ソートキー=object
     * this.部店="222"
     * this.顧客コード="666666"
     * this.IPアドレス="1325655"
     * this.ページ内表示行数="1" 
     * this.要求ページ番号 <= 0であった場合、  
     * 「要求ページ番号の値が0以下です。」の例外をスローする。
     */
    public void test_validate_0022()
    {
        String STR_METHOD_NAME = "test_validate_0022()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminTraderAdminLoginHistorySortKey l_sortKey = new WEB3AdminTraderAdminLoginHistorySortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = "loginDate";
        WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys = new WEB3AdminTraderAdminLoginHistorySortKey[]{l_sortKey};
        
        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1030";
        //時間(至)
        l_request.endTime = "1220";
        //ソートキー
        l_request.sortKeys = l_sortKeys;
        //部店
        l_request.branchCode = "222";
        //顧客コード
        l_request.accountCode = "666666";
        //IPアドレス
        l_request.ipAddress = "1325655";
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
     * this.時間(至)="1220"
     * this.ソートキー=object
     * this.部店="222"
     * this.顧客コード="666666"
     * this.IPアドレス="1325655"
     * this.ページ内表示行数="1" 
     * this.要求ページ番号 ="1"
     */
    public void test_validate_0023()
    {
        String STR_METHOD_NAME = "test_validate_0023()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminTraderAdminLoginHistorySortKey l_sortKey = new WEB3AdminTraderAdminLoginHistorySortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = "loginDate";
        WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys = new WEB3AdminTraderAdminLoginHistorySortKey[]{l_sortKey};
        
        //日付
        l_request.searchDate = "20080922";
        //時間(自)
        l_request.startTime = "1030";
        //時間(至)
        l_request.endTime = "1220";
        //ソートキー
        l_request.sortKeys = l_sortKeys;
        //部店
        l_request.branchCode = "222";
        //顧客コード
        l_request.accountCode = "666666";
        //IPアドレス
        l_request.ipAddress = "1325655";
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

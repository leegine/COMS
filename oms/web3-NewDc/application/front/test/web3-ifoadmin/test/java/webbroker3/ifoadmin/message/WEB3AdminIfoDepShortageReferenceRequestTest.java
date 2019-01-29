head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.14.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoDepShortageReferenceRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ｘｘｘｘ(WEB3AdminIfoDepShortageReferenceRequestTest.java.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/03/06 張騰宇 (中訊) 新規作成 モデルNo.000
*/
package webbroker3.ifoadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminIfoDepShortageReferenceRequestTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoDepShortageReferenceRequestTest.class);

    public WEB3AdminIfoDepShortageReferenceRequestTest(String arg0)
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

    /*
     * Test method for 'WEB3AdminIfoDepShortageReferenceRequest.validate()'
     */
    // １）　@検索日付チェック 
    // 　@１－１）　@this.検索日付== nullの場合、 
    // 　@　@「検索日付がnull」の例外をスローする。
    // 　@　@　@　@　@class:　@WEB3BusinessLayerException
    // 　@　@　@　@　@tag　@:　@BUSINESS_ERROR_03154
    public void testValidateCase1()
    {
        String STR_METHOD_NAME = "testValidateCase1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("3154" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("検索日付がnull。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    // ２）　@部店コードチェック
    // 　@２－１）　@this.部店コード一覧 == nullの場合、
    // 　@　@「部店コードがnull」の例外をスローする。 
    // 　@　@（BUSINESS_ERROR_01429）
    public void testValidateCase2()
    {
        String STR_METHOD_NAME = "testValidateCase2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("1429" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("部店コード一覧が未指定です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    // 　@２－２）　@this.部店コード一覧の要素数分以下の処理を行う。
    // 　@　@２－２－１）　@this.部店コードが以下の条件に該当する場合、
    // 　@　@　@「部店コードエラー」の例外をスローする。
    // 　@　@　@　@・部店コード != 数字
    // 　@　@　@　@・部店コード.length != 3
    public void testValidateCase3()
    {
        String STR_METHOD_NAME = "testValidateCase3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "10a";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            l_request.branchCode = l_strBranchCodes;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("779" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("部店コードの入力が不正です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    // 　@２－２）　@this.部店コード一覧の要素数分以下の処理を行う。
    // 　@　@２－２－１）　@this.部店コードが以下の条件に該当する場合、
    // 　@　@　@「部店コードエラー」の例外をスローする。
    // 　@　@　@　@・部店コード != 数字
    // 　@　@　@　@・部店コード.length != 3
    public void testValidateCase4()
    {
        String STR_METHOD_NAME = "testValidateCase4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "1021";
            l_strBranchCodes[2] = "103";
            l_request.branchCode = l_strBranchCodes;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("779" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("部店コードの入力が不正です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //３）　@顧客コードチェック 
    //　@this.顧客コード != nullの場合、以下のチェックを行う。 
    //　@３－１）　@this.顧客コードが以下の条件に該当する場合、 
    //　@　@「顧客コードエラー」の例外をスローする。 
    //　@　@　@・顧客コード != 数字 
    //　@　@　@・顧客コード.length != 6 
    //　@　@（BUSINESS_ERROR_00780） 
    public void testValidateCase5()
    {
        String STR_METHOD_NAME = "testValidateCase5()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            l_request.branchCode = l_strBranchCodes;
            l_request.accountCode = "a12345";
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("780" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("顧客コードの入力が不正です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //３）　@顧客コードチェック 
    //　@this.顧客コード != nullの場合、以下のチェックを行う。 
    //　@３－１）　@this.顧客コードが以下の条件に該当する場合、 
    //　@　@「顧客コードエラー」の例外をスローする。 
    //　@　@　@・顧客コード != 数字 
    //　@　@　@・顧客コード.length != 6 
    //　@　@（BUSINESS_ERROR_00780） 
    public void testValidateCase6()
    {
        String STR_METHOD_NAME = "testValidateCase6()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            l_request.branchCode = l_strBranchCodes;
            l_request.accountCode = "1234567";
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("780" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("顧客コードの入力が不正です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //４）　@未解消客区分チェック 
    //４－１）　@this.未解消客区分== nullの場合、 
    //　@　@「未解消客区分がnull」の例外をスローする。
    //this.顧客コード == null
    public void testValidateCase7()
    {
        String STR_METHOD_NAME = "testValidateCase7()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            l_request.branchCode = l_strBranchCodes;
            l_request.uncancelDiv = null;
//            l_request.accountCode = "1234567";
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("3155" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("未解消客区分がnull。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //　@４－２）　@this.未解消客区分に下記の項目以外が設定されていたら、 
    //　@　@「未解消客区分が未定義の値」の例外をスローする。 
    //　@　@　@・"0：未解消客 " 
    //　@　@　@・"1：不足発生全顧客 " 
    public void testValidateCase8()
    {
        String STR_METHOD_NAME = "testValidateCase8()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            l_request.branchCode = l_strBranchCodes;
            l_request.accountCode = "123456";
            l_request.uncancelDiv = "2";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("3156" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("未解消客区分が未定義の値。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //５）　@ソートキーチェック 
    //　@５－１）　@this.ソートキーが未入力の場合、 
    //　@　@「ソートキーがnull」の例外をスローする。 
    //　@　@（BUSINESS_ERROR_00231） 
    public void testValidateCase9()
    {
        String STR_METHOD_NAME = "testValidateCase9()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            l_request.branchCode = l_strBranchCodes;
            l_request.accountCode = "123456";
            l_request.uncancelDiv = "1";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("231" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("ソートキーが未指定です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //　@５－２）　@this.ソートキーの要素数分以下の処理を繰り返す。 
    //　@　@５－２－１）　@ソートキー.validate()をコールする。 
    public void testValidateCase10()
    {
        String STR_METHOD_NAME = "testValidateCase10()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[3];
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKeys[0] = l_sortKey;
            
            l_request.branchCode = l_strBranchCodes;
            l_request.accountCode = "123456";
            l_request.uncancelDiv = "1";
            l_request.sortKeys = l_sortKeys;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("85" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("ソートキーのキー項目が未指定です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //６）　@要求ページ番号チェック 
    //　@６－１）　@this.要求ページ番号 == nullであった場合、 
    //　@　@　@　@「要求ページ番号がnull」の例外をスローする。 
    //　@　@　@（BUSINESS_ERROR_00089） 
    public void testValidateCase11()
    {
        String STR_METHOD_NAME = "testValidateCase11()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[1];
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "D";
            l_sortKey.keyItem = "branchCode";
            l_sortKeys[0] = l_sortKey;
            
            l_request.branchCode = l_strBranchCodes;
            l_request.accountCode = "123456";
            l_request.uncancelDiv = "1";
            l_request.sortKeys = l_sortKeys;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("89" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("要求ページ番号が未指定です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //　@６－２）　@this.要求ページ番号が数字以外の値であった場合、 
    //　@　@「要求ページ番号が数字以外」の例外をスローする。 
    //　@　@（BUSINESS_ERROR_00090） 
    public void testValidateCase12()
    {
        String STR_METHOD_NAME = "testValidateCase12()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[1];
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "D";
            l_sortKey.keyItem = "branchCode";
            l_sortKeys[0] = l_sortKey;
            
            l_request.branchCode = l_strBranchCodes;
            l_request.accountCode = "123456";
            l_request.uncancelDiv = "1";
            l_request.sortKeys = l_sortKeys;
            l_request.pageIndex = "1.1";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("90" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("要求ページ番号が数字以外の値です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    //　@６－３）　@this.要求ページ番号 <= 0であった場合、 
    //　@　@「要求ページ番号が0以下」の例外をスローする。 
    //　@（BUSINESS_ERROR_00616）
    public void testValidateCase13()
    {
        String STR_METHOD_NAME = "testValidateCase13()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[1];
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "D";
            l_sortKey.keyItem = "branchCode";
            l_sortKeys[0] = l_sortKey;
            
            l_request.branchCode = l_strBranchCodes;
            l_request.accountCode = "123456";
            l_request.uncancelDiv = "1";
            l_request.sortKeys = l_sortKeys;
            l_request.pageIndex = "-1";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("616" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("要求ページ番号の値が0以下です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //７）　@ページ内表示行数チェック 
    //　@７－１）　@this.ページ内表示行数 == nullであった場合、 
    //　@　@　@　@「ページ内表示行数がnull」の例外をスローする。 
    //　@　@　@（BUSINESS_ERROR_00091） 
    public void testValidateCase14()
    {
        String STR_METHOD_NAME = "testValidateCase14()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[1];
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "D";
            l_sortKey.keyItem = "branchCode";
            l_sortKeys[0] = l_sortKey;
            
            l_request.branchCode = l_strBranchCodes;
            l_request.accountCode = "123456";
            l_request.uncancelDiv = "1";
            l_request.sortKeys = l_sortKeys;
            l_request.pageIndex = "1";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("91" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("ページ内表示行数の入力が不正です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //　@７－２）　@this.ページ内表示行数が数字以外の値であった場合、 
    //　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。 
    //　@ 　@　@（BUSINESS_ERROR_00092） 
    public void testValidateCase15()
    {
        String STR_METHOD_NAME = "testValidateCase15()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[1];
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "D";
            l_sortKey.keyItem = "branchCode";
            l_sortKeys[0] = l_sortKey;
            
            l_request.branchCode = l_strBranchCodes;
            l_request.accountCode = "123456";
            l_request.uncancelDiv = "1";
            l_request.sortKeys = l_sortKeys;
            l_request.pageIndex = "1";
            l_request.pageSize = "a";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("92" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("ページ内表示行数が数字以外の値です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //　@７－３）　@this.ページ内表示行数 <= 0であった場合、 
    //　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。 
    //　@　@　@　@（BUSINESS_ERROR_00617） 
    public void testValidateCase16()
    {
        String STR_METHOD_NAME = "testValidateCase16()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[1];
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "D";
            l_sortKey.keyItem = "branchCode";
            l_sortKeys[0] = l_sortKey;
            
            l_request.branchCode = l_strBranchCodes;
            l_request.accountCode = "123456";
            l_request.uncancelDiv = "1";
            l_request.sortKeys = l_sortKeys;
            l_request.pageIndex = "1";
            l_request.pageSize = "-1";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("617" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("ページ内表示行数の値が0以下です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //正常通過
    public void testValidateCase17()
    {
        String STR_METHOD_NAME = "testValidateCase17()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[1];
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "D";
            l_sortKey.keyItem = "branchCode";
            l_sortKeys[0] = l_sortKey;
            
            l_request.branchCode = l_strBranchCodes;
            l_request.accountCode = "123456";
            l_request.uncancelDiv = "1";
            l_request.sortKeys = l_sortKeys;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.14.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoDepShortageSortKeyTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ｘｘｘｘ(WEB3IfoDepShortageSortKeyTest.java.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/03/09 張騰宇 (中訊) 新規作成 モデルNo.000
*/
package webbroker3.ifoadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoDepShortageSortKeyTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoDepShortageSortKeyTest.class);

    public WEB3IfoDepShortageSortKeyTest(String arg0)
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
     * Test method for 'webbroker3.ifoadmin.message.WEB3IfoDepShortageSortKey.validate()'
     */
    //１）this.キー項目 == nullの場合、 
    //　@　@「ソートキー.キー項目がnull」の例外をスローする。
    public void testValidateCase1()
    {
        String STR_METHOD_NAME = "testValidateCase1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();

            l_sortKey.validate();
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

    //２） this.キー項目に下記の項目以外が 
    //　@設定されていたら、 
    //　@「ソートキー.キー項目が未定義の値」の例外をスローする。 
    //　@　@・"部店コード" 
    //　@　@・"顧客コード" 
    //　@　@・"請求額" 
    //　@　@・"現在未入金額" 
    //　@　@・"現在証拠金所要額" 
    //　@　@・"建玉有無フラグ" 
    //　@　@・"注文有無フラグ"
    public void testValidateCase2()
    {
        String STR_METHOD_NAME = "testValidateCase2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.keyItem = "branchId";

            l_sortKey.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("86" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("ソートキーのキー項目の値が存在しないコード値です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //３）this.昇順／降順＝nullの場合、 
    //　@　@「ソートキー.昇順／降順がnull」の例外をスローする。 
    public void testValidateCase3()
    {
        String STR_METHOD_NAME = "testValidateCase3()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.keyItem = "branchCode";

            l_sortKey.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("87" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("昇順／降順が未指定です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //４）this.昇順／降順が下記の項目以外の場合、 
    //　@　@「ソートキー.昇順／降順が未定義の値」の例外をスローする。 
    //　@　@　@・”A：昇順” 
    //　@　@　@・”D：降順”  
    public void testValidateCase4()
    {
        String STR_METHOD_NAME = "testValidateCase4()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "S";
            l_sortKey.keyItem = "branchCode";

            l_sortKey.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("88" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("昇順／降順が”A：昇順”、”D：降順”以外の値です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //正常通過
    public void testValidateCase5()
    {
        String STR_METHOD_NAME = "testValidateCase5()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "A";
            l_sortKey.keyItem = "accountCode";

            l_sortKey.validate();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //正常通過
    public void testValidateCase6()
    {
        String STR_METHOD_NAME = "testValidateCase6()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "A";
            l_sortKey.keyItem = "claimAmount";

            l_sortKey.validate();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //正常通過
    public void testValidateCase7()
    {
        String STR_METHOD_NAME = "testValidateCase7()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "D";
            l_sortKey.keyItem = "curNonPayAmt";

            l_sortKey.validate();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //正常通過
    public void testValidateCase8()
    {
        String STR_METHOD_NAME = "testValidateCase8()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "D";
            l_sortKey.keyItem = "curIfoDepositNecessaryAmt";

            l_sortKey.validate();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //正常通過
    public void testValidateCase9()
    {
        String STR_METHOD_NAME = "testValidateCase9()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "D";
            l_sortKey.keyItem = "contractExistFlag";

            l_sortKey.validate();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //正常通過
    public void testValidateCase10()
    {
        String STR_METHOD_NAME = "testValidateCase10()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "D";
            l_sortKey.keyItem = "orderExistFlag";

            l_sortKey.validate();
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

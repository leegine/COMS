head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.08.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTraderAdminLoginHistorySortKeyTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・ログイン処理一覧検索結果ソートキー(WEB3AdminTraderAdminLoginHistorySortKeyTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 孟亞南 (中訊) 新規作成 モデルNo.005
*/

package webbroker3.trademanagement.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTraderAdminLoginHistorySortKeyTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3AdminTraderAdminLoginHistorySortKeyTest.class);

    WEB3AdminTraderAdminLoginHistorySortKey l_adminLoginHistorySortKey =
        new WEB3AdminTraderAdminLoginHistorySortKey();

    public WEB3AdminTraderAdminLoginHistorySortKeyTest(String name)
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
     * this.キー項目＝null
     * 「ソートキー.キー項目がnull」の例外をスローする。
     */
    public void test_validate_0001()
    {
        String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(STR_METHOD_NAME);

        l_adminLoginHistorySortKey.keyItem = null;
        try
        {
            l_adminLoginHistorySortKey.validate();
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
     * this.キー項目に以下の項目以外が設定されている場合、例外をスローする。
     * ・ログイン日時
     * ・IPアドレス
     * ・顧客コード
     */
    public void test_validate_0002()
    {
        String STR_METHOD_NAME = "test_validate_0002()";
        log.entering(STR_METHOD_NAME);

        l_adminLoginHistorySortKey.keyItem = "111";
        try
        {
            l_adminLoginHistorySortKey.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * this.キー項目 = ログイン日時
     * this.昇順／降順＝nullの場合、
     * 「ソートキー.昇順／降順がnull」の例外をスローする。
     */
    public void test_validate_0003()
    {
        String STR_METHOD_NAME = "test_validate_0003()";
        log.entering(STR_METHOD_NAME);

        l_adminLoginHistorySortKey.keyItem = "loginDate";
        l_adminLoginHistorySortKey.ascDesc = null;

        try
        {
            l_adminLoginHistorySortKey.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00087, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * this.キー項目=IPアドレス
     * this.昇順／降順が下記の項目以外の場合、
     * 「ソートキー.昇順／降順が未定義の値」の例外をスローする。
     * ・”A：昇順”
     * ・”D：降順”
     */
    public void test_validate_0004()
    {
        String STR_METHOD_NAME = "test_validate_0004()";
        log.entering(STR_METHOD_NAME);

        l_adminLoginHistorySortKey.keyItem = "ipAddress";
        l_adminLoginHistorySortKey.ascDesc = "11A";
        try
        {
            l_adminLoginHistorySortKey.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00088, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * this.キー項目=顧客コード
     * this.昇順／降順=”A：昇順”
     */
    public void test_validate_0005()
    {
        String STR_METHOD_NAME = "test_validate_0005()";
        log.entering(STR_METHOD_NAME);

        l_adminLoginHistorySortKey.keyItem = "accountCode";
        l_adminLoginHistorySortKey.ascDesc = "A";
        try
        {
            l_adminLoginHistorySortKey.validate();
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

    /**
     * this.キー項目=顧客コード
     * this.昇順／降順=”D：降順”
     */
    public void test_validate_0006()
    {
        String STR_METHOD_NAME = "test_validate_0006()";
        log.entering(STR_METHOD_NAME);

        l_adminLoginHistorySortKey.keyItem = "accountCode";
        l_adminLoginHistorySortKey.ascDesc = "D";
        try
        {
            l_adminLoginHistorySortKey.validate();
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

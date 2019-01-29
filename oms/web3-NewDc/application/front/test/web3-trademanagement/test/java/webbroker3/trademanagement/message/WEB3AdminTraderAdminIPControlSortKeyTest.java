head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.08.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTraderAdminIPControlSortKeyTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・ログイン拒否IP一覧ソートキー(WEB3AdminTraderAdminIPControlSortKeyTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 張騰宇 (中訊) 新規作成 モデル004
*/
package webbroker3.trademanagement.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTraderAdminIPControlSortKeyTest extends TestBaseForMock 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTraderAdminIPControlSortKeyTest.class);

    WEB3AdminTraderAdminIPControlSortKey l_request = new WEB3AdminTraderAdminIPControlSortKey();
    
    public WEB3AdminTraderAdminIPControlSortKeyTest(String name)
    {
        super(name);
    }

    /**
     * this.キー項目がnullの場合、例外をthrowする。 
     */
    public void test_validate_0001()
    {
        String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(STR_METHOD_NAME);

        l_request.keyItem = null;
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
     * this.キー項目に以下の項目以外が設定されている場合、例外をthrowする。 
     * ・IPアドレス
     * ・適用開始日時
     * ・ステータス 
     */
    public void test_validate_0002()
    {
        String STR_METHOD_NAME = "test_validate_0002()";
        log.entering(STR_METHOD_NAME);

        l_request.keyItem = "a";
        try
        {
            l_request.validate();
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
     * this.キー項目="ipAddress"
     * this.昇順／降順がnullの場合、例外をthrowする。 
     */
    public void test_validate_0003()
    {
        String STR_METHOD_NAME = "test_validate_0003()";
        log.entering(STR_METHOD_NAME);

        //キー項目
        l_request.keyItem = "ipAddress";
        //昇順／降順
        l_request.ascDesc = null;
        
        try
        {
            l_request.validate();
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
     * this.キー項目="startDate"
     * this.昇順／降順="A"
     */
    public void test_validate_0004()
    {
        String STR_METHOD_NAME = "test_validate_0004()";
        log.entering(STR_METHOD_NAME);

        //キー項目
        l_request.keyItem = "startDate";
        //昇順／降順
        l_request.ascDesc = "A";
        
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
    
    /**
     * this.キー項目="status"
     * this.昇順／降順="D"
     */
    public void test_validate_0005()
    {
        String STR_METHOD_NAME = "test_validate_0005()";
        log.entering(STR_METHOD_NAME);

        //キー項目
        l_request.keyItem = "status";
        //昇順／降順
        l_request.ascDesc = "D";
        
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

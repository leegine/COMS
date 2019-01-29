head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.08.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTraderAdminIPControlUpdateCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・ログイン拒否IP登録情報変更完了リクエスト(WEB3AdminTraderAdminIPControlUpdateCompleteRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 孟亞南 (中訊) 新規作成 モデルNo.004
*/
package webbroker3.trademanagement.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTraderAdminIPControlUpdateCompleteRequestTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTraderAdminIPControlUpdateCompleteRequestTest.class);
    
    WEB3AdminTraderAdminIPControlUpdateCompleteRequest l_request = new WEB3AdminTraderAdminIPControlUpdateCompleteRequest();
    
    public WEB3AdminTraderAdminIPControlUpdateCompleteRequestTest(String name)
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
     * this.ログイン拒否ID == nullの場合、例外をスローする。
     */
    public void test_validate_0001()
    {
        String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(STR_METHOD_NAME);

        //ログイン拒否ID
        l_request.denyLoginID = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03116, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.ログイン拒否ID="1"
     * this.ステータス == nullの場合、例外をスローする。 
     */
    public void test_validate_0002()
    {
        String STR_METHOD_NAME = "test_validate_0002()";
        log.entering(STR_METHOD_NAME);

        //ログイン拒否ID
        l_request.denyLoginID = "1";
        //ステータス
        l_request.status = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00889, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.ログイン拒否ID="1"
     * this.ステータスが半角数字以外の場合、例外をスローする。 
     */
    public void test_validate_0003()
    {
        String STR_METHOD_NAME = "test_validate_0003()";
        log.entering(STR_METHOD_NAME);

        //ログイン拒否ID
        l_request.denyLoginID = "1";
        //ステータス
        l_request.status = "aa";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03123, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.ログイン拒否ID="1"
     * this.ステータス="1" 
     * this.適用開始日時 == nullの場合、例外をスローする。
     */
    public void test_validate_0004()
    {
        String STR_METHOD_NAME = "test_validate_0004()";
        log.entering(STR_METHOD_NAME);

        //ログイン拒否ID
        l_request.denyLoginID = "1";
        //ステータス
        l_request.status = "1";
        //適用開始日時
        l_request.startDate = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03124, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.ログイン拒否ID="1"
     * this.ステータス="1" 
     * this.適用開始日時="20080808080800"
     * this.適用終了日時 == nullの場合、例外をスローする。 
     */
    public void test_validate_0005()
    {
        String STR_METHOD_NAME = "test_validate_0005()";
        log.entering(STR_METHOD_NAME);

        //ログイン拒否ID
        l_request.denyLoginID = "1";
        //ステータス
        l_request.status = "1";
        //適用開始日時
        l_request.startDate = WEB3DateUtility.getDate("20080808080800", "yyyyMMddHHmmss");
        //適用終了日時
        l_request.endDate = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03125, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.ログイン拒否ID="1"
     * this.ステータス="1" 
     * this.適用開始日時="20080808080800"
     * this.適用終了日時 ＜= 現在日時の場合、例外をスローする。 
     */
    public void test_validate_0006()
    {
        String STR_METHOD_NAME = "test_validate_0006()";
        log.entering(STR_METHOD_NAME);

        //ログイン拒否ID
        l_request.denyLoginID = "1";
        //ステータス
        l_request.status = "1";
        //適用開始日時
        l_request.startDate = WEB3DateUtility.getDate("20080808080800", "yyyyMMddHHmmss");
        //適用終了日時
        l_request.endDate = WEB3DateUtility.getDate("20080808080800", "yyyyMMddHHmmss");
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03126, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.ログイン拒否ID="1"
     * this.ステータス="1" 
     * this.適用開始日時="20080808080800"
     * this.適用開始日時 > this.適用終了日時の場合、例外をスローする。
     */
    public void test_validate_0007()
    {
        String STR_METHOD_NAME = "test_validate_0007()";
        log.entering(STR_METHOD_NAME);

        //ログイン拒否ID
        l_request.denyLoginID = "1";
        //ステータス
        l_request.status = "1";
        //適用開始日時
        l_request.startDate = WEB3DateUtility.getDate("21090908080800", "yyyyMMddHHmmss");
        //適用終了日時
        l_request.endDate = WEB3DateUtility.getDate("21090808080800", "yyyyMMddHHmmss");
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03127, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.ログイン拒否ID="1"
     * this.ステータス="1" 
     * this.適用開始日時="20080808080800"
     * this.適用開始日時 > this.適用終了日時の場合、例外をスローする。
     */
    public void test_validate_0008()
    {
        String STR_METHOD_NAME = "test_validate_0008()";
        log.entering(STR_METHOD_NAME);

        //ログイン拒否ID
        l_request.denyLoginID = "1";
        //ステータス
        l_request.status = "1";
        //適用開始日時
        l_request.startDate = WEB3DateUtility.getDate("20080908080800", "yyyyMMddHHmmss");
        //適用終了日時
        l_request.endDate = WEB3DateUtility.getDate("21090808080800", "yyyyMMddHHmmss");
        
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

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.08.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTraderAdminIPControlRegistCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・ログイン拒否IP登録共通リクエスト(WEB3AdminTraderAdminIPControlRegistCommonRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 張騰宇 (中訊) 新規作成 モデル004
*/

package webbroker3.trademanagement.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTraderAdminIPControlRegistCommonRequestTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTraderAdminIPControlRegistCommonRequestTest.class);

    WEB3AdminTraderAdminIPControlRegistCommonRequest l_request = new WEB3AdminTraderAdminIPControlRegistCommonRequest();
    
    public WEB3AdminTraderAdminIPControlRegistCommonRequestTest(String name)
    {
        super(name);
    }

    /**
     * this.IPアドレス == nullの場合、例外をスローする。 
     */
    public void test_validate_0001()
    {
        String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(STR_METHOD_NAME);

        //IPアドレス
        l_request.ipAddress = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03122, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.IPアドレスが以下の場合、例外をスローする。
     * WEB3StringTypeUtility.isIpAddress()==false 
     */
    public void test_validate_0002()
    {
        String STR_METHOD_NAME = "test_validate_0002()";
        log.entering(STR_METHOD_NAME);

        //IPアドレス
        l_request.ipAddress = "aa";
        
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
     * this.IPアドレス="1111"
     * this.ステータス == nullの場合、例外をスローする。 
     */
    public void test_validate_0003()
    {
        String STR_METHOD_NAME = "test_validate_0003()";
        log.entering(STR_METHOD_NAME);

        //IPアドレス
        l_request.ipAddress = "1111";
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
     * this.IPアドレス="1111"
     * this.ステータスが半角数字以外の場合、例外をスローする。  
     */
    public void test_validate_0004()
    {
        String STR_METHOD_NAME = "test_validate_0004()";
        log.entering(STR_METHOD_NAME);

        //IPアドレス
        l_request.ipAddress = "1111";
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
     * this.IPアドレス="1111"
     * this.ステータス="1"
     * this.適用開始日時 == nullの場合、例外をスローする。
     */
    public void test_validate_0005()
    {
        String STR_METHOD_NAME = "test_validate_0005()";
        log.entering(STR_METHOD_NAME);

        //IPアドレス
        l_request.ipAddress = "1111";
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
     * this.IPアドレス="1111"
     * this.ステータス="1"
     * this.適用開始日時="20080808080800"
     * this.適用終了日時 == nullの場合、例外をスローする。 
     */
    public void test_validate_0006()
    {
        String STR_METHOD_NAME = "test_validate_0006()";
        log.entering(STR_METHOD_NAME);

        //IPアドレス
        l_request.ipAddress = "1111";
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
     * this.IPアドレス="1111"
     * this.ステータス="1"
     * this.適用開始日時="20080808080800"
     * this.適用終了日時 ＜= 現在日時の場合、例外をスローする。 
     */
    public void test_validate_0007()
    {
        String STR_METHOD_NAME = "test_validate_0007()";
        log.entering(STR_METHOD_NAME);

        //IPアドレス
        l_request.ipAddress = "1111";
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
     * this.IPアドレス="1111"
     * this.ステータス="1"
     * this.適用開始日時="20080808080800"
     * this.適用開始日時 > this.適用終了日時の場合、例外をスローする。
     */
    public void test_validate_0008()
    {
        String STR_METHOD_NAME = "test_validate_0008()";
        log.entering(STR_METHOD_NAME);

        //IPアドレス
        l_request.ipAddress = "1111";
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
     * this.IPアドレス="1111"
     * this.ステータス="1"
     * this.適用開始日時="20080808080800"
     * this.適用開始日時 > this.適用終了日時の場合、例外をスローする。
     */
    public void test_validate_0009()
    {
        String STR_METHOD_NAME = "test_validate_0009()";
        log.entering(STR_METHOD_NAME);

        //IPアドレス
        l_request.ipAddress = "1111";
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

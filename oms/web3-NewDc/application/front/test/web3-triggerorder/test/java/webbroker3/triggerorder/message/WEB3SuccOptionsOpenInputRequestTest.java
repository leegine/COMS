head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.47.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccOptionsOpenInputRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部 
File Name        : WEB3SuccOptionsOpenInputRequestTest.java
Author Name      : Daiwa Institute of Research  
Revesion History : 2008/03/25 楊夫志 (中訊) 新規作成  
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3SuccOptionsOpenInputRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccOptionsOpenInputRequestTest.class);

    /**
     * <BR>
     */
    private WEB3SuccOptionsOpenInputRequest succOptionsOpenInputRequest = null;

    public WEB3SuccOptionsOpenInputRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succOptionsOpenInputRequest = new WEB3SuccOptionsOpenInputRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //２）　@連続注文共通情報チェック<
    // 　@２－１）　@this.連続注文共通情報==nullの場合、
    // 　@　@　@　@　@　@「連続注文共通情報が未指定」の例外をスローする。
    public void testvalidate_C0001()
    {
        final String STR_METHOD_NAME = "testvalidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // super.validate();
            succOptionsOpenInputRequest.contractType = "1";
            succOptionsOpenInputRequest.marketCode = null;
            // ２－１） this.連続注文共通情報＝nullの場合
            succOptionsOpenInputRequest.succCommonInfo = null;
            succOptionsOpenInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02251, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    // ２－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、<BR>
    // 　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。<BR>
    // 　@　@　@　@　@"OP新規建（前提注文）"<BR>
    // 　@　@　@　@　@"OP新規建"<BR>
    //　@　@　@　@　@※コード値は、先物OP予約注文単位テーブルを参照。
    public void testvalidate_C0002()
    {
        final String STR_METHOD_NAME = "testvalidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate();
            succOptionsOpenInputRequest.contractType = "1";
            succOptionsOpenInputRequest.marketCode = null;
            // ２－１） this.連続注文共通情報!＝nullの場合
            succOptionsOpenInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenInputRequest.succCommonInfo.parentOrderId = "0001";
            //２－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、<BR>
            // 　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。<BR>
            // 　@　@　@　@　@"OP新規建（前提注文）"<BR>
            // 　@　@　@　@　@"OP新規建"<BR>
            succOptionsOpenInputRequest.succCommonInfo.succTradingType = "14";
            succOptionsOpenInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02252, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    // ３）　@連続注文取引区分チェック<BR>
    // 　@３－１）　@this.連続注文共通情報.連続注文取引区分=="OP新規建（前提注文）"の場合、<BR>
    // 　@　@　@　@　@　@「銘柄コード」が設定されていなければ、<BR>
    // 　@　@　@　@　@　@「入力パラメータチェックエラー。」の例外をthrowする。
    public void testvalidate_C0003()
    {
        final String STR_METHOD_NAME = "testvalidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate();
            succOptionsOpenInputRequest.contractType = "1";
            succOptionsOpenInputRequest.marketCode = null;
            // ２－１） this.連続注文共通情報!＝nullの場合
            succOptionsOpenInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenInputRequest.succCommonInfo.parentOrderId = "0001";
            // ３－１）　@this.連続注文共通情報.連続注文取引区分=="OP新規建（前提注文）"の場合、
            succOptionsOpenInputRequest.succCommonInfo.succTradingType = "15";
            //銘柄コード== null
            succOptionsOpenInputRequest.opProductCode = null;
            succOptionsOpenInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //correct case 　@this.連続注文共通情報.連続注文取引区分=="OP新規建"の場合、
    public void testvalidate_C0004()
    {
        final String STR_METHOD_NAME = "testvalidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate();
            succOptionsOpenInputRequest.contractType = "1";
            succOptionsOpenInputRequest.marketCode = null;
            // ２－１） this.連続注文共通情報!＝nullの場合
            succOptionsOpenInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenInputRequest.succCommonInfo.parentOrderId = "0001";
            // ３－１）　@this.連続注文共通情報.連続注文取引区分=="OP新規建"の場合、
            succOptionsOpenInputRequest.succCommonInfo.succTradingType = "16";
            succOptionsOpenInputRequest.opProductCode = "0001";
            succOptionsOpenInputRequest.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //correct case this.連続注文共通情報.連続注文取引区分=="OP新規建（前提注文）"
    public void testvalidate_C0005()
    {
        final String STR_METHOD_NAME = "testvalidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate();
            succOptionsOpenInputRequest.contractType = "1";
            succOptionsOpenInputRequest.marketCode = null;
            // ２－１） this.連続注文共通情報!＝nullの場合
            succOptionsOpenInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenInputRequest.succCommonInfo.parentOrderId = "0001";
            // ３－１）　@this.連続注文共通情報.連続注文取引区分="OP新規建（前提注文）"の場合、
            succOptionsOpenInputRequest.succCommonInfo.succTradingType = "16";
            succOptionsOpenInputRequest.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //１）　@super.validate()をコールする。
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate();
            succOptionsOpenInputRequest.contractType = null;
            succOptionsOpenInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException  l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00263, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //２－２）　@連続注文共通情報.validate()をコールする。
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            succOptionsOpenInputRequest.contractType = "1";
            succOptionsOpenInputRequest.marketCode = "1";
            succOptionsOpenInputRequest.targetProductCode = "0001";
            succOptionsOpenInputRequest.delivaryMonth = "200103";
            succOptionsOpenInputRequest.opProductType = "C";
            succOptionsOpenInputRequest.strikePrice = "1000";
            //this.連続注文共通情報!＝nullの場合
            succOptionsOpenInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsOpenInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException  l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02258, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@

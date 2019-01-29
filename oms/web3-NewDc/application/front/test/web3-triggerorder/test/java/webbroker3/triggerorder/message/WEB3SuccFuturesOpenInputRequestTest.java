head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.48.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccFuturesOpenInputRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :（連続）株価指数先物新規建注文入力画面リクエストテスト(WEB3SuccFuturesOpenInputRequestTest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2008/03/17 楊夫志 (中訊) 新規作成
 */
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * （連続）株価指数先物新規建注文入力画面リクエスト<BR>
 * <BR>
 * 
 * @@author yang-fuzhi
 * @@version 1.0
 */
public class WEB3SuccFuturesOpenInputRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccFuturesOpenInputRequestTest.class);

    /**
     * <BR>
     */
    private WEB3SuccFuturesOpenInputRequest succFuturesOpenInputRequest = null;

    /**
     * <BR>
     * 
     * @@param arg0
     */
    public WEB3SuccFuturesOpenInputRequestTest(String arg0)
    {
        super(arg0);
    }

    /**
     * <BR>
     */
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succFuturesOpenInputRequest = new WEB3SuccFuturesOpenInputRequest();
    }

    /**
     * <BR>
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //  ２）　@連続注文共通情報チェック
    //  ２－１）　@this.連続注文共通情報＝nullの場合、
    //  「連続注文共通情報が未指定」の例外をスローする。
    public void testvalidate_C0001()
    {
        final String STR_METHOD_NAME = "testvalidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // super.validate();
            succFuturesOpenInputRequest.contractType = "1";
            succFuturesOpenInputRequest.marketCode = null;
            // ２－１） this.連続注文共通情報＝nullの場合
            succFuturesOpenInputRequest.succCommonInfo = null;
            succFuturesOpenInputRequest.validate();
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

    //  ２－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、
    //  「連続注文取引区分の値が処理対象外」の例外をthrowする。
    //"先物新規建（前提注文）"<BR>
    //"先物新規建"
    public void testvalidate_C0002()
    {
        final String STR_METHOD_NAME = "testvalidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate();
            succFuturesOpenInputRequest.contractType = "1";
            succFuturesOpenInputRequest.marketCode = null;
            // ２－１） this.連続注文共通情報!＝nullの場合
            succFuturesOpenInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succFuturesOpenInputRequest.succCommonInfo.parentOrderId = "0001";
            //this.連続注文共通情報.連続注文取引区分が以下の値以外の場合
            //"先物新規建（前提注文）"<BR>
            //"先物新規建"
            succFuturesOpenInputRequest.succCommonInfo.succTradingType = "10";
            succFuturesOpenInputRequest.validate();
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

    // ３－１）　@this.連続注文共通情報.連続注文取引区分=="先物新規建（前提注文）"の場合
    public void testvalidate_C0003()
    {
        final String STR_METHOD_NAME = "testvalidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate();
            succFuturesOpenInputRequest.contractType = "1";
            succFuturesOpenInputRequest.marketCode = null;
            // ２－１） this.連続注文共通情報!＝nullの場合
            succFuturesOpenInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succFuturesOpenInputRequest.succCommonInfo.parentOrderId = "0001";
            // ３－１）　@this.連続注文共通情報.連続注文取引区分=="先物新規建（前提注文）"の場合、
            succFuturesOpenInputRequest.succCommonInfo.succTradingType = "11";
            succFuturesOpenInputRequest.futProductCode = null;
            succFuturesOpenInputRequest.validate();
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
    //correct case this.連続注文共通情報.連続注文取引区分=="先物新規建"
    public void testvalidate_C0004()
    {
        final String STR_METHOD_NAME = "testvalidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate();
            succFuturesOpenInputRequest.contractType = "1";
            succFuturesOpenInputRequest.marketCode = null;
            // ２－１） this.連続注文共通情報!＝nullの場合
            succFuturesOpenInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succFuturesOpenInputRequest.succCommonInfo.parentOrderId = "0001";
            // ３－１）　@this.連続注文共通情報.連続注文取引区分=="先物新規建"の場合、
            succFuturesOpenInputRequest.succCommonInfo.succTradingType = "12";
            succFuturesOpenInputRequest.validate();
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
    //correct case this.連続注文共通情報.連続注文取引区分=="先物新規建（前提注文）"
    public void testvalidate_C0005()
    {
        final String STR_METHOD_NAME = "testvalidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate();
            succFuturesOpenInputRequest.contractType = "1";
            succFuturesOpenInputRequest.marketCode = null;
            // ２－１） this.連続注文共通情報!＝nullの場合
            succFuturesOpenInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succFuturesOpenInputRequest.succCommonInfo.parentOrderId = "0001";
            // ３－１）　@this.連続注文共通情報.連続注文取引区分="先物新規建（前提注文）"の場合、
            succFuturesOpenInputRequest.succCommonInfo.succTradingType = "11";
            succFuturesOpenInputRequest.futProductCode = "0001";
            succFuturesOpenInputRequest.validate();
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
    public void testvalidate_C0006()
    {
        final String STR_METHOD_NAME = "testvalidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate();
            succFuturesOpenInputRequest.contractType = null;
            succFuturesOpenInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
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
    //１）　@  ２－２）　@連続注文共通情報.validate()をコールする。
    public void testvalidate_C0007()
    {
        final String STR_METHOD_NAME = "testvalidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate();
            succFuturesOpenInputRequest.contractType = "1";
            succFuturesOpenInputRequest.marketCode = null;
            // ２－１） this.連続注文共通情報!＝nullの場合
            succFuturesOpenInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesOpenInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
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

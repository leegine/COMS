head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.47.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccFuturesCloseInputRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :（連続）株価指数先物返済入力画面リクエストテスト(WEB3SuccFuturesCloseInputRequestTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/03/17 楊夫志 (中訊) 新規作成
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;
/**
 * （連続）株価指数先物返済入力画面リクエスト(BR)
 * @@author yang-fuzhi
 * @@version 1.0
 */
public class WEB3SuccFuturesCloseInputRequestTest extends TestBaseForMock
{
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccFuturesCloseInputRequestTest.class);

    private WEB3SuccFuturesCloseInputRequest succFuturesCloseInputRequest= null;

    public WEB3SuccFuturesCloseInputRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succFuturesCloseInputRequest = new WEB3SuccFuturesCloseInputRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //１）　@連続注文共通情報チェック
    //  １－１）　@this.連続注文共通情報＝nullの場合、
    // 「連続注文共通情報が未指定」の例外をスローする。
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseInputRequest.succCommonInfo = null;
            succFuturesCloseInputRequest.validate();
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

    //   １－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合
    // "先物返済（前提注文）"
    // "先物返済（既存残）"
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate
            succFuturesCloseInputRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseInputRequest.succCommonInfo.succTradingType = "12";
            succFuturesCloseInputRequest.validate();
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
    //   ２） this.連続注文共通情報.連続注文取引区分=="先物返済（既存残）"の場合のみ、
    //  super.validate()をコールする。
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate
            succFuturesCloseInputRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseInputRequest.succCommonInfo.succTradingType = "14";
            // super.validate()をコールする。
            succFuturesCloseInputRequest.id = null;
            succFuturesCloseInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //correct case this.連続注文共通情報.連続注文取引区分=="先物返済（前提注文）"の場合
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate
            succFuturesCloseInputRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseInputRequest.succCommonInfo.succTradingType = "13";
            succFuturesCloseInputRequest.validate();
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
    //correct case  this.連続注文共通情報.連続注文取引区分=="先物返済（既存残）"の場合
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate
            succFuturesCloseInputRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseInputRequest.succCommonInfo.succTradingType = "14";
            // super.validate()をコールする。
            succFuturesCloseInputRequest.id = new String[]{"0001","0002"};
            succFuturesCloseInputRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[]
                          { new WEB3FuturesOptionsSortKey(), new WEB3FuturesOptionsSortKey()};
            int l_intObjectItemLength = succFuturesCloseInputRequest.futOpSortKeys.length;
            for (int i = 0; i < l_intObjectItemLength; i++)
            {
                succFuturesCloseInputRequest.futOpSortKeys[i].keyItem = "openDate";
                succFuturesCloseInputRequest.futOpSortKeys[i].ascDesc = "A";
            }
            succFuturesCloseInputRequest.validate();
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
    //this.連続注文共通情報.validate()をコールする。
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseInputRequest.validate();
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

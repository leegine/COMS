head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.46.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccFuturesOpenCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :（連続）株価指数先物新規建注文完了リクエストテスト(WEB3SuccFuturesOpenCompleteRequestTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/03/17 楊夫志 (中訊) 新規作成
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;
/**
 * （連続）株価指数先物新規建注文完了リクエスト<BR>
 * @@author yang-fuzhi
 * @@version 1.0
 */
public class WEB3SuccFuturesOpenCompleteRequestTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccFuturesOpenCompleteRequestTest.class);
    /**
     * <BR>
     */
    private WEB3SuccFuturesOpenCompleteRequest succFuturesOpenComplete = null;
    /**
     * <BR>
     * @@param arg0
     */
    public WEB3SuccFuturesOpenCompleteRequestTest(String arg0)
    {
        super(arg0);
    }

    /**
     *<BR>
     */
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succFuturesOpenComplete = new WEB3SuccFuturesOpenCompleteRequest();
    }

    /**
     *<BR>
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    // ２）　@連続注文共通情報チェック
    //  ２－１）　@this.連続注文共通情報＝nullの場合、
    // 「連続注文共通情報が未指定」の例外をスローする。
    public void testValidate_0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succFuturesOpenComplete.orderPriceDiv = "0";
            succFuturesOpenComplete.limitPrice = null;
            succFuturesOpenComplete.execCondType = "1";
            succFuturesOpenComplete.expirationDateType = "1";
            succFuturesOpenComplete.expirationDate = null;
            succFuturesOpenComplete.orderCondType = "0";
            succFuturesOpenComplete.stopOrderCondPrice = null;
            succFuturesOpenComplete.stopOrderCondOperator = null;
            succFuturesOpenComplete.wlimitOrderCondPrice = null;
            succFuturesOpenComplete.wlimitOrderCondOperator = null;
            succFuturesOpenComplete.wLimitOrderPriceDiv = null;
            succFuturesOpenComplete.wLimitPrice = null;
            succFuturesOpenComplete.wlimitExecCondType = null;
            succFuturesOpenComplete.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenComplete.futProductCode = "0001";
            succFuturesOpenComplete.contractType = "1";
            succFuturesOpenComplete.marketCode = "1";
            succFuturesOpenComplete.futOrderQuantity = "1000";
            succFuturesOpenComplete.orderId = null;
            //this.連続注文共通情報＝nullの場合
            succFuturesOpenComplete.succCommonInfo = null;
            succFuturesOpenComplete.validate();
            fail();
        }
        catch (WEB3BaseException  l_ex)
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
    // ２－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、
    // 「連続注文取引区分の値が処理対象外」の例外をthrowする。
    // "先物新規建（前提注文）"<BR>
    // "先物新規建"<BR>
    public void testvalidate_C0002()
    {
        final String STR_METHOD_NAME = "testvalidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // super.super.validate();
            succFuturesOpenComplete.orderPriceDiv = "0";
            succFuturesOpenComplete.limitPrice = null;
            succFuturesOpenComplete.execCondType = "1";
            succFuturesOpenComplete.expirationDateType = "1";
            succFuturesOpenComplete.expirationDate = null;
            succFuturesOpenComplete.orderCondType = "0";
            succFuturesOpenComplete.stopOrderCondPrice = null;
            succFuturesOpenComplete.stopOrderCondOperator = null;
            succFuturesOpenComplete.wlimitOrderCondPrice = null;
            succFuturesOpenComplete.wlimitOrderCondOperator = null;
            succFuturesOpenComplete.wLimitOrderPriceDiv = null;
            succFuturesOpenComplete.wLimitPrice = null;
            succFuturesOpenComplete.wlimitExecCondType = null;
            succFuturesOpenComplete.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenComplete.futProductCode = "0001";
            succFuturesOpenComplete.contractType = "1";
            succFuturesOpenComplete.marketCode = "1";
            succFuturesOpenComplete.futOrderQuantity = "1000";
            succFuturesOpenComplete.orderId = null;
            //succFuturesOpenComplete.succCommonInfo != null;
            succFuturesOpenComplete.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succFuturesOpenComplete.succCommonInfo.parentOrderId = "0001";
            //this.連続注文共通情報.連続注文取引区分が以下の値以外の場合
            succFuturesOpenComplete.succCommonInfo.succTradingType = "10";
            succFuturesOpenComplete.validate();
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
    //　@３－１）　@連続注文単価調整値情報≠nullの場合、
    // 　@連続注文単価調整値情報.validate()をコールする
    public void testvalidate_C0003()
    {
        final String STR_METHOD_NAME = "testvalidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // super.super.validate();
            succFuturesOpenComplete.orderPriceDiv = "0";
            succFuturesOpenComplete.limitPrice = null;
            succFuturesOpenComplete.execCondType = "1";
            succFuturesOpenComplete.expirationDateType = "1";
            succFuturesOpenComplete.expirationDate = null;
            succFuturesOpenComplete.orderCondType = "0";
            succFuturesOpenComplete.stopOrderCondPrice = null;
            succFuturesOpenComplete.stopOrderCondOperator = null;
            succFuturesOpenComplete.wlimitOrderCondPrice = null;
            succFuturesOpenComplete.wlimitOrderCondOperator = null;
            succFuturesOpenComplete.wLimitOrderPriceDiv = null;
            succFuturesOpenComplete.wLimitPrice = null;
            succFuturesOpenComplete.wlimitExecCondType = null;
            succFuturesOpenComplete.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenComplete.futProductCode = "0001";
            succFuturesOpenComplete.contractType = "1";
            succFuturesOpenComplete.marketCode = "1";
            succFuturesOpenComplete.futOrderQuantity = "1000";
            succFuturesOpenComplete.orderId = null;
            //succFuturesOpenComplete.succCommonInfo != null;
            succFuturesOpenComplete.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succFuturesOpenComplete.succCommonInfo.parentOrderId = "0001";
            //this.連続注文共通情報.連続注文取引区分が以下の値以外の場合
            succFuturesOpenComplete.succCommonInfo.succTradingType = "11";
            //連続注文単価調整値情報≠nullの場合
            succFuturesOpenComplete.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesOpenComplete.priceAdjustmentValueInfo.sign = null;
            succFuturesOpenComplete.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02243, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //３－２）　@連続注文単価調整値情報≠nullの場合、
    // 連続注文共通情報.連続注文取引区分≠"先物新規建（前提注文）"であれば
    public void testvalidate_C0004()
    {
        final String STR_METHOD_NAME = "testvalidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succFuturesOpenComplete.orderPriceDiv = "0";
            succFuturesOpenComplete.limitPrice = null;
            succFuturesOpenComplete.execCondType = "1";
            succFuturesOpenComplete.expirationDateType = "1";
            succFuturesOpenComplete.expirationDate = null;
            succFuturesOpenComplete.orderCondType = "0";
            succFuturesOpenComplete.stopOrderCondPrice = null;
            succFuturesOpenComplete.stopOrderCondOperator = null;
            succFuturesOpenComplete.wlimitOrderCondPrice = null;
            succFuturesOpenComplete.wlimitOrderCondOperator = null;
            succFuturesOpenComplete.wLimitOrderPriceDiv = null;
            succFuturesOpenComplete.wLimitPrice = null;
            succFuturesOpenComplete.wlimitExecCondType = null;
            succFuturesOpenComplete.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenComplete.futProductCode = "0001";
            succFuturesOpenComplete.contractType = "1";
            succFuturesOpenComplete.marketCode = "1";
            succFuturesOpenComplete.futOrderQuantity = "1000";
            succFuturesOpenComplete.orderId = null;
            //succFuturesOpenComplete.succCommonInfo != null;
            succFuturesOpenComplete.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succFuturesOpenComplete.succCommonInfo.parentOrderId = "0001";
            //２－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、
            // 「連続注文取引区分の値が処理対象外」の例外をthrowする。
            succFuturesOpenComplete.succCommonInfo.succTradingType = "12";
            //連続注文単価調整値情報≠nullの場合
            succFuturesOpenComplete.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succFuturesOpenComplete.priceAdjustmentValueInfo.sign = "+";
            succFuturesOpenComplete.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesOpenComplete.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02253, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    // 　@３－３）　@連続注文単価調整値情報≠nullの場合、
    //  注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が不整合」
    //  の例外をthrowする。
    public void testvalidate_C0005()
    {
        final String STR_METHOD_NAME = "testvalidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succFuturesOpenComplete.orderPriceDiv = "1";
            succFuturesOpenComplete.limitPrice = "1000";
            succFuturesOpenComplete.execCondType = "1";
            succFuturesOpenComplete.expirationDateType = "1";
            succFuturesOpenComplete.expirationDate = null;
            succFuturesOpenComplete.orderCondType = "0";
            succFuturesOpenComplete.stopOrderCondPrice = null;
            succFuturesOpenComplete.stopOrderCondOperator = null;
            succFuturesOpenComplete.wlimitOrderCondPrice = null;
            succFuturesOpenComplete.wlimitOrderCondOperator = null;
            succFuturesOpenComplete.wLimitOrderPriceDiv = null;
            succFuturesOpenComplete.wLimitPrice = null;
            succFuturesOpenComplete.wlimitExecCondType = null;
            succFuturesOpenComplete.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenComplete.futProductCode = "0001";
            succFuturesOpenComplete.contractType = "1";
            succFuturesOpenComplete.marketCode = "1";
            succFuturesOpenComplete.futOrderQuantity = "1000";
            succFuturesOpenComplete.orderId = null;
            //succFuturesOpenComplete.succCommonInfo != null;
            succFuturesOpenComplete.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succFuturesOpenComplete.succCommonInfo.parentOrderId = "0001";
            //２－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、
            // 「連続注文取引区分の値が処理対象外」の例外をthrowする。
            succFuturesOpenComplete.succCommonInfo.succTradingType = "11";
            //連続注文単価調整値情報≠nullの場合
            succFuturesOpenComplete.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succFuturesOpenComplete.priceAdjustmentValueInfo.sign = "+";
            succFuturesOpenComplete.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesOpenComplete.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02254, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //４）　@連続注文・注文条件チェック
    // super.validate連続注文()をコールする。
    public void testvalidate_C0006()
    {
        final String STR_METHOD_NAME = "testvalidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succFuturesOpenComplete.orderPriceDiv = "0";
            succFuturesOpenComplete.limitPrice = null;
            succFuturesOpenComplete.execCondType = "3";
            succFuturesOpenComplete.expirationDateType = "1";
            succFuturesOpenComplete.expirationDate = null;
            succFuturesOpenComplete.orderCondType = "0";
            succFuturesOpenComplete.stopOrderCondPrice = null;
            succFuturesOpenComplete.stopOrderCondOperator = null;
            succFuturesOpenComplete.wlimitOrderCondPrice = null;
            succFuturesOpenComplete.wlimitOrderCondOperator = null;
            succFuturesOpenComplete.wLimitOrderPriceDiv = null;
            succFuturesOpenComplete.wLimitPrice = null;
            succFuturesOpenComplete.wlimitExecCondType = null;
            succFuturesOpenComplete.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenComplete.futProductCode = "0001";
            succFuturesOpenComplete.contractType = "1";
            succFuturesOpenComplete.marketCode = "1";
            succFuturesOpenComplete.futOrderQuantity = "1000";
            succFuturesOpenComplete.orderId = null;
            //succFuturesOpenComplete.succCommonInfo != null;
            succFuturesOpenComplete.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succFuturesOpenComplete.succCommonInfo.parentOrderId = "0001";
            //this.連続注文共通情報.連続注文取引区分が以下の値以外の場合
            succFuturesOpenComplete.succCommonInfo.succTradingType = "11";
            succFuturesOpenComplete.priceAdjustmentValueInfo = null;
            succFuturesOpenComplete.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02235, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //correct case 連続注文共通情報.連続注文取引区分="先物新規建"  連続注文単価調整値情報=nullの場合
    public void testvalidate_C0007()
    {
        final String STR_METHOD_NAME = "testvalidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succFuturesOpenComplete.orderPriceDiv = "0";
            succFuturesOpenComplete.limitPrice = null;
            succFuturesOpenComplete.execCondType = "1";
            succFuturesOpenComplete.expirationDateType = "1";
            succFuturesOpenComplete.expirationDate = null;
            succFuturesOpenComplete.orderCondType = "0";
            succFuturesOpenComplete.stopOrderCondPrice = null;
            succFuturesOpenComplete.stopOrderCondOperator = null;
            succFuturesOpenComplete.wlimitOrderCondPrice = null;
            succFuturesOpenComplete.wlimitOrderCondOperator = null;
            succFuturesOpenComplete.wLimitOrderPriceDiv = null;
            succFuturesOpenComplete.wLimitPrice = null;
            succFuturesOpenComplete.wlimitExecCondType = null;
            succFuturesOpenComplete.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenComplete.futProductCode = "0001";
            succFuturesOpenComplete.contractType = "1";
            succFuturesOpenComplete.marketCode = "1";
            succFuturesOpenComplete.futOrderQuantity = "1000";
            succFuturesOpenComplete.orderId = null;
            //succFuturesOpenComplete.succCommonInfo != null;
            succFuturesOpenComplete.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succFuturesOpenComplete.succCommonInfo.parentOrderId = "0001";
            //this.連続注文共通情報.連続注文取引区分が以下の値以外の場合
            succFuturesOpenComplete.succCommonInfo.succTradingType = "12";
            //連続注文単価調整値情報=nullの場合
            succFuturesOpenComplete.priceAdjustmentValueInfo = null;
            succFuturesOpenComplete.validate();
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
        log.debug(TEST_END + STR_METHOD_NAME);
    }
    //correct case 連続注文共通情報.連続注文取引区分="先物新規建（前提注文）" 連続注文単価調整値情報≠nullの場合
    public void testvalidate_C0008()
    {
        final String STR_METHOD_NAME = "testvalidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succFuturesOpenComplete.orderPriceDiv = "0";
            succFuturesOpenComplete.limitPrice = null;
            succFuturesOpenComplete.execCondType = "1";
            succFuturesOpenComplete.expirationDateType = "1";
            succFuturesOpenComplete.expirationDate = null;
            succFuturesOpenComplete.orderCondType = "0";
            succFuturesOpenComplete.stopOrderCondPrice = null;
            succFuturesOpenComplete.stopOrderCondOperator = null;
            succFuturesOpenComplete.wlimitOrderCondPrice = null;
            succFuturesOpenComplete.wlimitOrderCondOperator = null;
            succFuturesOpenComplete.wLimitOrderPriceDiv = null;
            succFuturesOpenComplete.wLimitPrice = null;
            succFuturesOpenComplete.wlimitExecCondType = null;
            succFuturesOpenComplete.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenComplete.futProductCode = "0001";
            succFuturesOpenComplete.contractType = "1";
            succFuturesOpenComplete.marketCode = "1";
            succFuturesOpenComplete.futOrderQuantity = "1000";
            succFuturesOpenComplete.orderId = null;
            //succFuturesOpenComplete.succCommonInfo != null;
            succFuturesOpenComplete.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succFuturesOpenComplete.succCommonInfo.parentOrderId = "0001";
            //this.連続注文共通情報.連続注文取引区分が以下の値以外の場合
            succFuturesOpenComplete.succCommonInfo.succTradingType = "11";
            //連続注文単価調整値情報≠nullの場合
            succFuturesOpenComplete.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succFuturesOpenComplete.priceAdjustmentValueInfo.sign = "+";
            succFuturesOpenComplete.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesOpenComplete.validate();
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
        log.debug(TEST_END + STR_METHOD_NAME);
    }
    //  １）　@super.validate()をコールする。
    public void testValidate_0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succFuturesOpenComplete.orderPriceDiv = null;
            succFuturesOpenComplete.validate();
            fail();
        }
        catch (WEB3BaseException  l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00184, l_ex.getErrorInfo());
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
    public void testValidate_0010()
    {
        final String STR_METHOD_NAME = "testValidate_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succFuturesOpenComplete.orderPriceDiv = "0";
            succFuturesOpenComplete.limitPrice = null;
            succFuturesOpenComplete.execCondType = "1";
            succFuturesOpenComplete.expirationDateType = "1";
            succFuturesOpenComplete.expirationDate = null;
            succFuturesOpenComplete.orderCondType = "0";
            succFuturesOpenComplete.stopOrderCondPrice = null;
            succFuturesOpenComplete.stopOrderCondOperator = null;
            succFuturesOpenComplete.wlimitOrderCondPrice = null;
            succFuturesOpenComplete.wlimitOrderCondOperator = null;
            succFuturesOpenComplete.wLimitOrderPriceDiv = null;
            succFuturesOpenComplete.wLimitPrice = null;
            succFuturesOpenComplete.wlimitExecCondType = null;
            succFuturesOpenComplete.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenComplete.futProductCode = "0001";
            succFuturesOpenComplete.contractType = "1";
            succFuturesOpenComplete.marketCode = "1";
            succFuturesOpenComplete.futOrderQuantity = "1000";
            succFuturesOpenComplete.orderId = null;
            //２－１）this.連続注文共通情報!＝nullの場合
            succFuturesOpenComplete.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesOpenComplete.validate();
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

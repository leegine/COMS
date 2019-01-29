head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.48.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccFuturesCloseCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :（連続）株価指数先物返済注文完了リクエストテスト(WEB3SuccFuturesCloseCompleteRequestTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/03/18 楊夫志 (中訊) 新規作成
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
/***
 * （連続）株価指数先物返済注文完了リクエストテスト
 * @@author yang-fuzhi
 * @@version 1.0
 */
public class WEB3SuccFuturesCloseCompleteRequestTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccFuturesCloseCompleteRequestTest.class);
    WEB3SuccFuturesCloseCompleteRequest succFuturesCloseCompleteRequest = null;

    public WEB3SuccFuturesCloseCompleteRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succFuturesCloseCompleteRequest = new WEB3SuccFuturesCloseCompleteRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //１）　@連続注文共通情報チェック
    //１－１）　@this.連続注文共通情報＝nullの場合
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseCompleteRequest.succCommonInfo = null;
            succFuturesCloseCompleteRequest.validate();
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
    // １－３）this.連続注文共通情報.連続注文取引区分が以下の値以外の場合
    // "先物返済（前提注文）"
    // "先物返済（既存残）"
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.連続注文共通情報!＝nullの場合
            succFuturesCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //１－２）　@this.連続注文共通情報.validate()をコールする。
            succFuturesCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            //succCommonInfo.succTradingType
            succFuturesCloseCompleteRequest.succCommonInfo.succTradingType = "12";
            succFuturesCloseCompleteRequest.validate();
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
    //  ２） this.連続注文共通情報.連続注文取引区分=="先物返済（既存残）"の場合のみ、
    //  super.validate()をコールする。
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.succCommonInfo.validate()
            succFuturesCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseCompleteRequest.succCommonInfo.succTradingType = "14";
            //super.super.validate
            succFuturesCloseCompleteRequest.orderPriceDiv = null;
            succFuturesCloseCompleteRequest.validate();
        }
        catch (WEB3BaseException l_ex)
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
    // ２） this.連続注文共通情報.連続注文取引区分=="先物返済（既存残）"の場合のみ、
    //  以外、super.validateAT反対取引()をコールする。
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.succCommonInfo.validate()
            succFuturesCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseCompleteRequest.succCommonInfo.succTradingType = "13";
            //super.super.validate
            succFuturesCloseCompleteRequest.orderPriceDiv = null;
            succFuturesCloseCompleteRequest.validate();
        }
        catch (WEB3BaseException l_ex)
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
    // ３－１）　@連続注文単価調整値情報≠nullの場合、
    //  連続注文単価調整値情報.validate()をコールする。
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１－１）　@this.連続注文共通情報!＝nullの場合
            succFuturesCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //１－２）　@this.連続注文共通情報.validate()をコールする。
            succFuturesCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseCompleteRequest.succCommonInfo.succTradingType = "14";
            //２） this.連続注文共通情報.連続注文取引区分=="先物返済（既存残）"の場合のみ、
            //  super.validate()をコールする。
            //  以外、super.validateAT反対取引()をコールする。
            //super.super.validate()
            succFuturesCloseCompleteRequest.orderPriceDiv = "1";
            succFuturesCloseCompleteRequest.limitPrice = "1000";
            succFuturesCloseCompleteRequest.execCondType = "1";
            succFuturesCloseCompleteRequest.expirationDateType = "1";
            succFuturesCloseCompleteRequest.expirationDate = null;
            succFuturesCloseCompleteRequest.orderCondType = "0";
            succFuturesCloseCompleteRequest.stopOrderCondPrice = null;
            succFuturesCloseCompleteRequest.stopOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseCompleteRequest.wLimitPrice = null;
            succFuturesCloseCompleteRequest.wlimitExecCondType = null;
            succFuturesCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseCompleteRequest.closingOrder = "1";
            succFuturesCloseCompleteRequest.futOrderQuantity = "1000";
            for (int i = 0; i < succFuturesCloseCompleteRequest.closeMarginContractUnits.length; i++)
            {
                succFuturesCloseCompleteRequest.closeMarginContractUnits[i].id = "0001";
                succFuturesCloseCompleteRequest.closeMarginContractUnits[i].settlePriority = null;
            }
            succFuturesCloseCompleteRequest.orderId = null;
            //３－１）　@連続注文単価調整値情報≠nullの場合、
            //  連続注文単価調整値情報.validate()をコールする。
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo.sign = null;
            succFuturesCloseCompleteRequest.validate();
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
    //  連続注文共通情報.連続注文取引区分≠"先物返済（前提注文）"であれば
    //  「連続注文取引区分が、連続注文の±指値指定不可の区分」の例外をthrowする。
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１－１）　@this.連続注文共通情報!＝nullの場合
            succFuturesCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //１－２）　@this.連続注文共通情報.validate()をコールする。
            succFuturesCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseCompleteRequest.succCommonInfo.succTradingType = "14";
            //２） this.連続注文共通情報.連続注文取引区分=="先物返済（既存残）"の場合のみ、
            //  super.validate()をコールする。
            //  以外、super.validateAT反対取引()をコールする。
            //super.super.validate()
            succFuturesCloseCompleteRequest.orderPriceDiv = "1";
            succFuturesCloseCompleteRequest.limitPrice = "1000";
            succFuturesCloseCompleteRequest.execCondType = "1";
            succFuturesCloseCompleteRequest.expirationDateType = "1";
            succFuturesCloseCompleteRequest.expirationDate = null;
            succFuturesCloseCompleteRequest.orderCondType = "0";
            succFuturesCloseCompleteRequest.stopOrderCondPrice = null;
            succFuturesCloseCompleteRequest.stopOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseCompleteRequest.wLimitPrice = null;
            succFuturesCloseCompleteRequest.wlimitExecCondType = null;
            succFuturesCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseCompleteRequest.closingOrder = "1";
            succFuturesCloseCompleteRequest.futOrderQuantity = "1000";
            for (int i = 0; i < succFuturesCloseCompleteRequest.closeMarginContractUnits.length; i++)
            {
                succFuturesCloseCompleteRequest.closeMarginContractUnits[i].id = "0001";
                succFuturesCloseCompleteRequest.closeMarginContractUnits[i].settlePriority = null;
            }
            succFuturesCloseCompleteRequest.orderId = null;
            //this.priceAdjustmentValueInfo.validate
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesCloseCompleteRequest.validate();
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
    // ３－３）　@連続注文単価調整値情報≠nullの場合、
    //  注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が不整合」
    //  の例外をthrowする。
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // this.succCommonInfo.validate()
            succFuturesCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseCompleteRequest.succCommonInfo.succTradingType = "13";
            //super.super.validate()
            succFuturesCloseCompleteRequest.orderPriceDiv = "1";
            succFuturesCloseCompleteRequest.limitPrice = "1000";
            succFuturesCloseCompleteRequest.execCondType = "1";
            succFuturesCloseCompleteRequest.expirationDateType = "1";
            succFuturesCloseCompleteRequest.expirationDate = null;
            succFuturesCloseCompleteRequest.orderCondType = "0";
            succFuturesCloseCompleteRequest.stopOrderCondPrice = null;
            succFuturesCloseCompleteRequest.stopOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseCompleteRequest.wLimitPrice = null;
            succFuturesCloseCompleteRequest.wlimitExecCondType = null;
            succFuturesCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succFuturesCloseCompleteRequest.orderId = "0001";
            succFuturesCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseCompleteRequest.closingOrder ="1";
            succFuturesCloseCompleteRequest.futOrderQuantity = "1000";
            succFuturesCloseCompleteRequest.checkPrice = "1000";
            succFuturesCloseCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd");
            //this.priceAdjustmentValueInfo.validate
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesCloseCompleteRequest.validate();
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
    //  ４）　@this.連続注文共通情報.連続注文取引区分=="先物返済（前提注文）"の場合
    //リクエスト.決済順序==（null）の場合は
    public void testValidate_C0008()
    {
        final String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.succCommonInfo.validate()
            succFuturesCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseCompleteRequest.succCommonInfo.succTradingType = "13";
            //super.super.validate()
            succFuturesCloseCompleteRequest.orderPriceDiv = "0";
            succFuturesCloseCompleteRequest.limitPrice = null;
            succFuturesCloseCompleteRequest.execCondType = "1";
            succFuturesCloseCompleteRequest.expirationDateType = "1";
            succFuturesCloseCompleteRequest.expirationDate = null;
            succFuturesCloseCompleteRequest.orderCondType = "0";
            succFuturesCloseCompleteRequest.stopOrderCondPrice = null;
            succFuturesCloseCompleteRequest.stopOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseCompleteRequest.wLimitPrice = null;
            succFuturesCloseCompleteRequest.wlimitExecCondType = null;
            succFuturesCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succFuturesCloseCompleteRequest.orderId = "0001";
            succFuturesCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseCompleteRequest.closingOrder = null;
            succFuturesCloseCompleteRequest.futOrderQuantity = "1000";
            succFuturesCloseCompleteRequest.checkPrice = "1000";
            succFuturesCloseCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd");
            //this.priceAdjustmentValueInfo.validate
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseCompleteRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02306, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //  ４）　@this.連続注文共通情報.連続注文取引区分=="先物返済（前提注文）"の場合
    //リクエスト.決済順序==（"建日順"）の場合は
    public void testValidate_C0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.succCommonInfo.validate()
            succFuturesCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseCompleteRequest.succCommonInfo.succTradingType = "13";
            //super.super.validate()
            succFuturesCloseCompleteRequest.orderPriceDiv = "0";
            succFuturesCloseCompleteRequest.limitPrice = null;
            succFuturesCloseCompleteRequest.execCondType = "1";
            succFuturesCloseCompleteRequest.expirationDateType = "1";
            succFuturesCloseCompleteRequest.expirationDate = null;
            succFuturesCloseCompleteRequest.orderCondType = "0";
            succFuturesCloseCompleteRequest.stopOrderCondPrice = null;
            succFuturesCloseCompleteRequest.stopOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseCompleteRequest.wLimitPrice = null;
            succFuturesCloseCompleteRequest.wlimitExecCondType = null;
            succFuturesCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succFuturesCloseCompleteRequest.orderId = "0001";
            succFuturesCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseCompleteRequest.closingOrder = "3";
            succFuturesCloseCompleteRequest.futOrderQuantity = "1000";
            succFuturesCloseCompleteRequest.checkPrice = "1000";
            succFuturesCloseCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd");
            //this.priceAdjustmentValueInfo.validate
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseCompleteRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02306, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //　@連続注文・注文条件チェック
    // super.validate連続注文()をコールする。
    public void testValidate_C0010()
    {
        final String STR_METHOD_NAME = "testValidate_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // this.succCommonInfo.validate()
            succFuturesCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseCompleteRequest.succCommonInfo.succTradingType = "13";
            //super.super.validate()
            succFuturesCloseCompleteRequest.orderPriceDiv = "0";
            succFuturesCloseCompleteRequest.limitPrice = null;
            succFuturesCloseCompleteRequest.execCondType = "3";
            succFuturesCloseCompleteRequest.expirationDateType = "1";
            succFuturesCloseCompleteRequest.expirationDate = null;
            succFuturesCloseCompleteRequest.orderCondType = "0";
            succFuturesCloseCompleteRequest.stopOrderCondPrice = null;
            succFuturesCloseCompleteRequest.stopOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseCompleteRequest.wLimitPrice = null;
            succFuturesCloseCompleteRequest.wlimitExecCondType = null;
            succFuturesCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succFuturesCloseCompleteRequest.orderId = "0001";
            succFuturesCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseCompleteRequest.closingOrder ="1";
            succFuturesCloseCompleteRequest.futOrderQuantity = "1000";
            succFuturesCloseCompleteRequest.checkPrice = "1000";
            succFuturesCloseCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd");
            //this.priceAdjustmentValueInfo.validate
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseCompleteRequest.validate();
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
    //correct case this.連続注文共通情報.連続注文取引区分!="先物返済（既存残）" 連続注文単価調整値情報!=nullの場合
    public void testValidate_C0011()
    {
        final String STR_METHOD_NAME = "testValidate_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // this.succCommonInfo.validate()
            succFuturesCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseCompleteRequest.succCommonInfo.succTradingType = "13";
            // super.super.validate()
            succFuturesCloseCompleteRequest.orderPriceDiv = "0";
            succFuturesCloseCompleteRequest.limitPrice = null;
            succFuturesCloseCompleteRequest.execCondType = "1";
            succFuturesCloseCompleteRequest.expirationDateType = "1";
            succFuturesCloseCompleteRequest.expirationDate = null;
            succFuturesCloseCompleteRequest.orderCondType = "0";
            succFuturesCloseCompleteRequest.stopOrderCondPrice = null;
            succFuturesCloseCompleteRequest.stopOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseCompleteRequest.wLimitPrice = null;
            succFuturesCloseCompleteRequest.wlimitExecCondType = null;
            succFuturesCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succFuturesCloseCompleteRequest.orderId = "0001";
            succFuturesCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseCompleteRequest.closingOrder ="1";
            succFuturesCloseCompleteRequest.futOrderQuantity = "1000";
            succFuturesCloseCompleteRequest.checkPrice = "1000";
            succFuturesCloseCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd"); 
            //連続注文単価調整値情報!=nullの場合
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesCloseCompleteRequest.validate();
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
    //correct case this.連続注文共通情報.連続注文取引区分=="先物返済（既存残）" 連続注文単価調整値情報==nullの場合
    public void testValidate_C0012()
    {
        final String STR_METHOD_NAME = "testValidate_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // this.succCommonInfo.validate()
            succFuturesCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseCompleteRequest.succCommonInfo.succTradingType = "14";
            // ２） this.連続注文共通情報.連続注文取引区分=="先物返済（既存残）"の場合のみ、
            //  super.validate()をコールする。
            //super.super.validate()
            succFuturesCloseCompleteRequest.orderPriceDiv = "1";
            succFuturesCloseCompleteRequest.limitPrice = "1000";
            succFuturesCloseCompleteRequest.execCondType = "1";
            succFuturesCloseCompleteRequest.expirationDateType = "1";
            succFuturesCloseCompleteRequest.expirationDate = null;
            succFuturesCloseCompleteRequest.orderCondType = "0";
            succFuturesCloseCompleteRequest.stopOrderCondPrice = null;
            succFuturesCloseCompleteRequest.stopOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseCompleteRequest.wLimitPrice = null;
            succFuturesCloseCompleteRequest.wlimitExecCondType = null;
            succFuturesCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseCompleteRequest.closingOrder = "1";
            succFuturesCloseCompleteRequest.futOrderQuantity = "1000";
            for (int i = 0; i < succFuturesCloseCompleteRequest.closeMarginContractUnits.length; i++)
            {
                succFuturesCloseCompleteRequest.closeMarginContractUnits[i].id = "0001";
                succFuturesCloseCompleteRequest.closeMarginContractUnits[i].settlePriority = null;
            }
            succFuturesCloseCompleteRequest.orderId = null; 
            //連続注文単価調整値情報==nullの場合
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseCompleteRequest.validate();
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
    //１－２）　@this.連続注文共通情報.validate()をコールする。
    public void testValidate_C0013()
    {
        final String STR_METHOD_NAME = "testValidate_C0013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.連続注文共通情報!＝nullの場合
            succFuturesCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseCompleteRequest.validate();
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

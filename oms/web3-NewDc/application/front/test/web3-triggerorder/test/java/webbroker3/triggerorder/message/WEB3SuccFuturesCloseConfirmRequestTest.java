head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.48.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccFuturesCloseConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :（連続）株価指数先物返済注文確認リクエストテスト(WEB3SuccFuturesCloseConfirmRequestTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/03/18 楊夫志 (中訊) 新規作成
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;
/**
 * （連続）株価指数先物返済注文確認リクエスト<BR>
 * @@author yang-fuzhi
 * @@version 1.0
 */
public class WEB3SuccFuturesCloseConfirmRequestTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccFuturesCloseConfirmRequestTest.class);
    WEB3SuccFuturesCloseConfirmRequest succFuturesCloseConfirmRequest = null;
    public WEB3SuccFuturesCloseConfirmRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succFuturesCloseConfirmRequest = new WEB3SuccFuturesCloseConfirmRequest();
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
            succFuturesCloseConfirmRequest.succCommonInfo = null;
            succFuturesCloseConfirmRequest.validate();
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
    // １－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合
    // "先物返済（前提注文）"
    // "先物返済（既存残）"
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.連続注文共通情報!＝nullの場合
            succFuturesCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //１－２）　@this.連続注文共通情報.validate()をコールする。
            succFuturesCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            //succCommonInfo.succTradingType
            succFuturesCloseConfirmRequest.succCommonInfo.succTradingType = "12";
            succFuturesCloseConfirmRequest.validate();
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
            //this.succCommonInfo.validate()
            succFuturesCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseConfirmRequest.succCommonInfo.succTradingType = "14";
            //super.super.validate
            succFuturesCloseConfirmRequest.orderPriceDiv = null;
            succFuturesCloseConfirmRequest.validate();
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
            succFuturesCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseConfirmRequest.succCommonInfo.succTradingType = "13";
            //super.super.validate
            succFuturesCloseConfirmRequest.orderPriceDiv = null;
            succFuturesCloseConfirmRequest.validate();
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
            succFuturesCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //１－２）　@this.連続注文共通情報.validate()をコールする。
            succFuturesCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseConfirmRequest.succCommonInfo.succTradingType = "14";
            //２） this.連続注文共通情報.連続注文取引区分=="先物返済（既存残）"の場合のみ、
            //  super.validate()をコールする。
            //  以外、super.validateAT反対取引()をコールする。
            //super.super.validate()
            succFuturesCloseConfirmRequest.orderPriceDiv = "1";
            succFuturesCloseConfirmRequest.limitPrice = "1000";
            succFuturesCloseConfirmRequest.execCondType = "1";
            succFuturesCloseConfirmRequest.expirationDateType = "1";
            succFuturesCloseConfirmRequest.expirationDate = null;
            succFuturesCloseConfirmRequest.orderCondType = "0";
            succFuturesCloseConfirmRequest.stopOrderCondPrice = null;
            succFuturesCloseConfirmRequest.stopOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseConfirmRequest.wLimitPrice = null;
            succFuturesCloseConfirmRequest.wlimitExecCondType = null;
            succFuturesCloseConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesCloseConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseConfirmRequest.closingOrder = "1";
            succFuturesCloseConfirmRequest.futOrderQuantity = "1000";
            for (int i = 0; i < succFuturesCloseConfirmRequest.closeMarginContractUnits.length; i++)
            {
                succFuturesCloseConfirmRequest.closeMarginContractUnits[i].id = "0001";
                succFuturesCloseConfirmRequest.closeMarginContractUnits[i].settlePriority = null;
            }
            //３－１）　@連続注文単価調整値情報≠nullの場合、
            //  連続注文単価調整値情報.validate()をコールする。
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo.sign = null;
            succFuturesCloseConfirmRequest.validate();
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
    // 「連続注文取引区分が、連続注文の±指値指定不可の区分」の例外をthrowする
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
           //this.succCommonInfo.validate()
            succFuturesCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseConfirmRequest.succCommonInfo.succTradingType = "14";
            //super.super.validate()
            succFuturesCloseConfirmRequest.orderPriceDiv = "1";
            succFuturesCloseConfirmRequest.limitPrice = "1000";
            succFuturesCloseConfirmRequest.execCondType = "1";
            succFuturesCloseConfirmRequest.expirationDateType = "1";
            succFuturesCloseConfirmRequest.expirationDate = null;
            succFuturesCloseConfirmRequest.orderCondType = "0";
            succFuturesCloseConfirmRequest.stopOrderCondPrice = null;
            succFuturesCloseConfirmRequest.stopOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseConfirmRequest.wLimitPrice = null;
            succFuturesCloseConfirmRequest.wlimitExecCondType = null;
            succFuturesCloseConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesCloseConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseConfirmRequest.closingOrder = "1";
            succFuturesCloseConfirmRequest.futOrderQuantity = "1000";
            for (int i = 0; i < succFuturesCloseConfirmRequest.closeMarginContractUnits.length; i++)
            {
                succFuturesCloseConfirmRequest.closeMarginContractUnits[i].id = "0001";
                succFuturesCloseConfirmRequest.closeMarginContractUnits[i].settlePriority = null;
            }
            //this.priceAdjustmentValueInfo.validate
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesCloseConfirmRequest.validate();
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
            succFuturesCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseConfirmRequest.succCommonInfo.succTradingType = "13";
            //super.super.validate()
            succFuturesCloseConfirmRequest.orderPriceDiv = "1";
            succFuturesCloseConfirmRequest.limitPrice = "1000";
            succFuturesCloseConfirmRequest.execCondType = "1";
            succFuturesCloseConfirmRequest.expirationDateType = "1";
            succFuturesCloseConfirmRequest.expirationDate = null;
            succFuturesCloseConfirmRequest.orderCondType = "0";
            succFuturesCloseConfirmRequest.stopOrderCondPrice = null;
            succFuturesCloseConfirmRequest.stopOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseConfirmRequest.wLimitPrice = null;
            succFuturesCloseConfirmRequest.wlimitExecCondType = null;
            succFuturesCloseConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succFuturesCloseConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseConfirmRequest.closingOrder ="1";
            succFuturesCloseConfirmRequest.futOrderQuantity = "1000";
            //this.priceAdjustmentValueInfo.validate
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesCloseConfirmRequest.validate();
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
            succFuturesCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseConfirmRequest.succCommonInfo.succTradingType = "13";
            //super.super.validate()
            succFuturesCloseConfirmRequest.orderPriceDiv = "0";
            succFuturesCloseConfirmRequest.limitPrice = null;
            succFuturesCloseConfirmRequest.execCondType = "1";
            succFuturesCloseConfirmRequest.expirationDateType = "1";
            succFuturesCloseConfirmRequest.expirationDate = null;
            succFuturesCloseConfirmRequest.orderCondType = "0";
            succFuturesCloseConfirmRequest.stopOrderCondPrice = null;
            succFuturesCloseConfirmRequest.stopOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseConfirmRequest.wLimitPrice = null;
            succFuturesCloseConfirmRequest.wlimitExecCondType = null;
            succFuturesCloseConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succFuturesCloseConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseConfirmRequest.closingOrder = null;
            succFuturesCloseConfirmRequest.futOrderQuantity = "1000";
            //this.priceAdjustmentValueInfo.validate
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseConfirmRequest.validate();
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
            succFuturesCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseConfirmRequest.succCommonInfo.succTradingType = "13";
            //super.super.validate()
            succFuturesCloseConfirmRequest.orderPriceDiv = "0";
            succFuturesCloseConfirmRequest.limitPrice = null;
            succFuturesCloseConfirmRequest.execCondType = "1";
            succFuturesCloseConfirmRequest.expirationDateType = "1";
            succFuturesCloseConfirmRequest.expirationDate = null;
            succFuturesCloseConfirmRequest.orderCondType = "0";
            succFuturesCloseConfirmRequest.stopOrderCondPrice = null;
            succFuturesCloseConfirmRequest.stopOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseConfirmRequest.wLimitPrice = null;
            succFuturesCloseConfirmRequest.wlimitExecCondType = null;
            succFuturesCloseConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succFuturesCloseConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseConfirmRequest.closingOrder ="3";
            succFuturesCloseConfirmRequest.futOrderQuantity = "1000";
            //this.priceAdjustmentValueInfo.validate
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseConfirmRequest.validate();
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
    //　@ ５）　@連続注文・注文条件チェック
    //  super.validate連続注文()をコールする。
    public void testValidate_C0010()
    {
        final String STR_METHOD_NAME = "testValidate_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // this.succCommonInfo.validate()
            succFuturesCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseConfirmRequest.succCommonInfo.succTradingType = "13";
            //super.super.validate()
            succFuturesCloseConfirmRequest.orderPriceDiv = "0";
            succFuturesCloseConfirmRequest.limitPrice = null;
            succFuturesCloseConfirmRequest.execCondType = "3";
            succFuturesCloseConfirmRequest.expirationDateType = "1";
            succFuturesCloseConfirmRequest.expirationDate = null;
            succFuturesCloseConfirmRequest.orderCondType = "0";
            succFuturesCloseConfirmRequest.stopOrderCondPrice = null;
            succFuturesCloseConfirmRequest.stopOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseConfirmRequest.wLimitPrice = null;
            succFuturesCloseConfirmRequest.wlimitExecCondType = null;
            succFuturesCloseConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succFuturesCloseConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseConfirmRequest.closingOrder ="1";
            succFuturesCloseConfirmRequest.futOrderQuantity = "1000";
            //this.priceAdjustmentValueInfo.validate
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseConfirmRequest.validate();
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
    //correct case  this.連続注文共通情報.連続注文取引区分!="先物返済（既存残）"の場合 連続注文単価調整値情報!=nullの場合
    public void testValidate_C0011()
    {
        final String STR_METHOD_NAME = "testValidate_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // this.succCommonInfo.validate()
            succFuturesCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseConfirmRequest.succCommonInfo.succTradingType = "13";
            // super.super.validate()
            succFuturesCloseConfirmRequest.orderPriceDiv = "0";
            succFuturesCloseConfirmRequest.limitPrice = null;
            succFuturesCloseConfirmRequest.execCondType = "1";
            succFuturesCloseConfirmRequest.expirationDateType = "1";
            succFuturesCloseConfirmRequest.expirationDate = null;
            succFuturesCloseConfirmRequest.orderCondType = "0";
            succFuturesCloseConfirmRequest.stopOrderCondPrice = null;
            succFuturesCloseConfirmRequest.stopOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseConfirmRequest.wLimitPrice = null;
            succFuturesCloseConfirmRequest.wlimitExecCondType = null;
            succFuturesCloseConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succFuturesCloseConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseConfirmRequest.closingOrder ="1";
            succFuturesCloseConfirmRequest.futOrderQuantity = "1000";
            //連続注文単価調整値情報!=nullの場合
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesCloseConfirmRequest.validate();
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
    //correct case  this.連続注文共通情報.連続注文取引区分=="先物返済（既存残）"の場合 連続注文単価調整値情報==nullの場合
    public void testValidate_C0012()
    {
        final String STR_METHOD_NAME = "testValidate_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // this.succCommonInfo.validate()
            succFuturesCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseConfirmRequest.succCommonInfo.succTradingType = "14";
            // ２） this.連続注文共通情報.連続注文取引区分=="先物返済（既存残）"の場合のみ、
            //  super.validate()をコールする。
            //  以外、super.validateAT反対取引()をコールする。
            //super.super.validate()
            succFuturesCloseConfirmRequest.orderPriceDiv = "1";
            succFuturesCloseConfirmRequest.limitPrice = "1000";
            succFuturesCloseConfirmRequest.execCondType = "1";
            succFuturesCloseConfirmRequest.expirationDateType = "1";
            succFuturesCloseConfirmRequest.expirationDate = null;
            succFuturesCloseConfirmRequest.orderCondType = "0";
            succFuturesCloseConfirmRequest.stopOrderCondPrice = null;
            succFuturesCloseConfirmRequest.stopOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseConfirmRequest.wLimitPrice = null;
            succFuturesCloseConfirmRequest.wlimitExecCondType = null;
            succFuturesCloseConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesCloseConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseConfirmRequest.closingOrder = "1";
            succFuturesCloseConfirmRequest.futOrderQuantity = "1000";
            for (int i = 0; i < succFuturesCloseConfirmRequest.closeMarginContractUnits.length; i++)
            {
                succFuturesCloseConfirmRequest.closeMarginContractUnits[i].id = "0001";
                succFuturesCloseConfirmRequest.closeMarginContractUnits[i].settlePriority = null;
            }
            //連続注文単価調整値情報==nullの場合
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseConfirmRequest.validate();
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
            succFuturesCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseConfirmRequest.validate();
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

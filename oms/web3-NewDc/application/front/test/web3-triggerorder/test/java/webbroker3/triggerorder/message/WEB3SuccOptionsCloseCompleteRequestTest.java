head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.47.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccOptionsCloseCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部 
File Name        : WEB3SuccOptionsCloseCompleteRequestTest.java
Author Name      : Daiwa Institute of Research  
Revesion History : 2008/03/25 楊夫志 (中訊) 新規作成  
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3SuccOptionsCloseCompleteRequestTest extends TestBaseForMock
{


    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccOptionsCloseCompleteRequestTest.class);
    WEB3SuccOptionsCloseCompleteRequest succOptionsCloseCompleteRequest = null;

    public WEB3SuccOptionsCloseCompleteRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succOptionsCloseCompleteRequest = new WEB3SuccOptionsCloseCompleteRequest();
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
            succOptionsCloseCompleteRequest.succCommonInfo = null;
            succOptionsCloseCompleteRequest.validate();
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
    //１－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、
    // 　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。
    // 　@　@　@　@　@"OP返済（前提注文）"
    // 　@　@　@　@　@"OP返済（既存残）"
    // 　@　@　@　@　@※コード値は、先物OP予約注文単位テーブルを参照。
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.連続注文共通情報!＝nullの場合
            succOptionsCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //１－２）　@this.連続注文共通情報.validate()をコールする。
            succOptionsCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            //succCommonInfo.succTradingType
            succOptionsCloseCompleteRequest.succCommonInfo.succTradingType = "16";
            succOptionsCloseCompleteRequest.validate();
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
    //２）　@this.連続注文共通情報.連続注文取引区分=="OP返済（既存残）"の場合のみ、
    // 　@　@　@super.validate()をコールする。
    // 　@　@　@以外、super.validateAT反対取引()をコールする
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.連続注文共通情報!＝nullの場合
            succOptionsCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate()
            succOptionsCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseCompleteRequest.succCommonInfo.succTradingType = "18";
            //super.super.validate
            succOptionsCloseCompleteRequest.orderPriceDiv = null;
            succOptionsCloseCompleteRequest.validate();
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
    // ２）　@this.連続注文共通情報.連続注文取引区分=="OP返済（既存残）"の場合のみ、
    // 　@　@　@super.validate()をコールする。
    // 　@　@　@以外
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.succCommonInfo.validate()
            succOptionsCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseCompleteRequest.succCommonInfo.succTradingType = "17";
            //super.super.validate
            succOptionsCloseCompleteRequest.orderPriceDiv = null;
            succOptionsCloseCompleteRequest.validate();
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
    //３）　@連続注文単価調整値情報チェック
    // 　@３－１）　@連続注文単価調整値情報≠nullの場合、
    // 　@　@　@　@　@　@連続注文単価調整値情報.validate()をコールする。
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１－１）　@this.連続注文共通情報!＝nullの場合
            succOptionsCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //１－２）　@this.連続注文共通情報.validate()をコールする。
            succOptionsCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseCompleteRequest.succCommonInfo.succTradingType = "18";
            //２）　@this.連続注文共通情報.連続注文取引区分=="OP返済（既存残）"の場合のみ、
            // 　@　@　@super.validate()をコールする。
            // 　@　@　@以外、super.validateAT反対取引()をコールする。
            //super.super.validate()
            succOptionsCloseCompleteRequest.orderPriceDiv = "1";
            succOptionsCloseCompleteRequest.limitPrice = "1000";
            succOptionsCloseCompleteRequest.execCondType = "1";
            succOptionsCloseCompleteRequest.expirationDateType = "1";
            succOptionsCloseCompleteRequest.expirationDate = null;
            succOptionsCloseCompleteRequest.orderCondType = "0";
            succOptionsCloseCompleteRequest.stopOrderCondPrice = null;
            succOptionsCloseCompleteRequest.stopOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseCompleteRequest.wLimitPrice = null;
            succOptionsCloseCompleteRequest.wlimitExecCondType = null;
            succOptionsCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseCompleteRequest.closingOrder = "1";
            succOptionsCloseCompleteRequest.opOrderQuantity = "1000";
            for (int i = 0; i < succOptionsCloseCompleteRequest.closeMarginContractUnits.length; i++)
            {
                succOptionsCloseCompleteRequest.closeMarginContractUnits[i].id = "0001";
                succOptionsCloseCompleteRequest.closeMarginContractUnits[i].settlePriority = null;
            }
            succOptionsCloseCompleteRequest.orderId = null;
            //this.priceAdjustmentValueInfo.validate
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo.sign = null;
            succOptionsCloseCompleteRequest.validate();
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
    // 　@　@　@　@　@　@連続注文共通情報.連続注文取引区分≠"OP返済（前提注文）"であれば
    // 　@　@　@　@　@　@「連続注文取引区分が、連続注文の±指値指定不可の区分」の例外をthrowする。
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１－１）　@this.連続注文共通情報!＝nullの場合
            succOptionsCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //１－２）　@this.連続注文共通情報.validate()をコールする。
            succOptionsCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseCompleteRequest.succCommonInfo.succTradingType = "18";
            //２） this.連続注文共通情報.連続注文取引区分=="先物返済（既存残）"の場合のみ、
            //  super.validate()をコールする。
            //  以外、super.validateAT反対取引()をコールする。
            //super.super.validate()
            succOptionsCloseCompleteRequest.orderPriceDiv = "1";
            succOptionsCloseCompleteRequest.limitPrice = "1000";
            succOptionsCloseCompleteRequest.execCondType = "1";
            succOptionsCloseCompleteRequest.expirationDateType = "1";
            succOptionsCloseCompleteRequest.expirationDate = null;
            succOptionsCloseCompleteRequest.orderCondType = "0";
            succOptionsCloseCompleteRequest.stopOrderCondPrice = null;
            succOptionsCloseCompleteRequest.stopOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseCompleteRequest.wLimitPrice = null;
            succOptionsCloseCompleteRequest.wlimitExecCondType = null;
            succOptionsCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseCompleteRequest.closingOrder = "1";
            succOptionsCloseCompleteRequest.opOrderQuantity = "1000";
            for (int i = 0; i < succOptionsCloseCompleteRequest.closeMarginContractUnits.length; i++)
            {
                succOptionsCloseCompleteRequest.closeMarginContractUnits[i].id = "0001";
                succOptionsCloseCompleteRequest.closeMarginContractUnits[i].settlePriority = null;
            }
            succOptionsCloseCompleteRequest.orderId = null;
            //this.priceAdjustmentValueInfo.validate
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsCloseCompleteRequest.validate();
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
    // 　@　@　@　@　@　@注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が不整合」の
    // 　@　@　@　@　@　@例外をthrowする。
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // this.succCommonInfo.validate()
            succOptionsCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseCompleteRequest.succCommonInfo.succTradingType = "17";
            //super.super.validate()
            succOptionsCloseCompleteRequest.orderPriceDiv = "1";
            succOptionsCloseCompleteRequest.limitPrice = "1000";
            succOptionsCloseCompleteRequest.execCondType = "1";
            succOptionsCloseCompleteRequest.expirationDateType = "1";
            succOptionsCloseCompleteRequest.expirationDate = null;
            succOptionsCloseCompleteRequest.orderCondType = "0";
            succOptionsCloseCompleteRequest.stopOrderCondPrice = null;
            succOptionsCloseCompleteRequest.stopOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseCompleteRequest.wLimitPrice = null;
            succOptionsCloseCompleteRequest.wlimitExecCondType = null;
            succOptionsCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succOptionsCloseCompleteRequest.orderId = "0001";
            succOptionsCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseCompleteRequest.closingOrder ="1";
            succOptionsCloseCompleteRequest.opOrderQuantity = "1000";
            succOptionsCloseCompleteRequest.checkPrice = "1000";
            succOptionsCloseCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd");
            //this.priceAdjustmentValueInfo.validate
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsCloseCompleteRequest.validate();
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
    //this.連続注文共通情報.連続注文取引区分=="先物返済（前提注文）"の場合
    //リクエスト.決済順序==（null）の場合は
    public void testValidate_C0008()
    {
        final String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.succCommonInfo.validate()
            succOptionsCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseCompleteRequest.succCommonInfo.succTradingType = "17";
            //super.super.validate()
            succOptionsCloseCompleteRequest.orderPriceDiv = "0";
            succOptionsCloseCompleteRequest.limitPrice = null;
            succOptionsCloseCompleteRequest.execCondType = "1";
            succOptionsCloseCompleteRequest.expirationDateType = "1";
            succOptionsCloseCompleteRequest.expirationDate = null;
            succOptionsCloseCompleteRequest.orderCondType = "0";
            succOptionsCloseCompleteRequest.stopOrderCondPrice = null;
            succOptionsCloseCompleteRequest.stopOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseCompleteRequest.wLimitPrice = null;
            succOptionsCloseCompleteRequest.wlimitExecCondType = null;
            succOptionsCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succOptionsCloseCompleteRequest.orderId = "0001";
            succOptionsCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseCompleteRequest.opOrderQuantity = "1000";
            succOptionsCloseCompleteRequest.checkPrice = "1000";
            succOptionsCloseCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd");
            //this.priceAdjustmentValueInfo.validate
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo = null;
            //this.validate
            succOptionsCloseCompleteRequest.closingOrder = null;
            succOptionsCloseCompleteRequest.validate();
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
    //this.連続注文共通情報.連続注文取引区分=="先物返済（前提注文）"の場合
    //リクエスト.決済順序==（"建日順"）の場合は
    public void testValidate_C0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.succCommonInfo.validate()
            succOptionsCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseCompleteRequest.succCommonInfo.succTradingType = "17";
            //super.super.validate()
            succOptionsCloseCompleteRequest.orderPriceDiv = "0";
            succOptionsCloseCompleteRequest.limitPrice = null;
            succOptionsCloseCompleteRequest.execCondType = "1";
            succOptionsCloseCompleteRequest.expirationDateType = "1";
            succOptionsCloseCompleteRequest.expirationDate = null;
            succOptionsCloseCompleteRequest.orderCondType = "0";
            succOptionsCloseCompleteRequest.stopOrderCondPrice = null;
            succOptionsCloseCompleteRequest.stopOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseCompleteRequest.wLimitPrice = null;
            succOptionsCloseCompleteRequest.wlimitExecCondType = null;
            succOptionsCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succOptionsCloseCompleteRequest.orderId = "0001";
            succOptionsCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseCompleteRequest.closingOrder ="3";
            succOptionsCloseCompleteRequest.opOrderQuantity = "1000";
            succOptionsCloseCompleteRequest.checkPrice = "1000";
            succOptionsCloseCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd");
            //this.priceAdjustmentValueInfo.validate
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo = null;
            succOptionsCloseCompleteRequest.validate();
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
            succOptionsCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseCompleteRequest.succCommonInfo.succTradingType = "17";
            //super.super.validate()
            succOptionsCloseCompleteRequest.orderPriceDiv = "0";
            succOptionsCloseCompleteRequest.limitPrice = null;
            succOptionsCloseCompleteRequest.execCondType = "3";
            succOptionsCloseCompleteRequest.expirationDateType = "1";
            succOptionsCloseCompleteRequest.expirationDate = null;
            succOptionsCloseCompleteRequest.orderCondType = "0";
            succOptionsCloseCompleteRequest.stopOrderCondPrice = null;
            succOptionsCloseCompleteRequest.stopOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseCompleteRequest.wLimitPrice = null;
            succOptionsCloseCompleteRequest.wlimitExecCondType = null;
            succOptionsCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succOptionsCloseCompleteRequest.orderId = "0001";
            succOptionsCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseCompleteRequest.closingOrder ="1";
            succOptionsCloseCompleteRequest.opOrderQuantity = "1000";
            succOptionsCloseCompleteRequest.checkPrice = "1000";
            succOptionsCloseCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd");
            //this.priceAdjustmentValueInfo.validate
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo = null;
            succOptionsCloseCompleteRequest.validate();
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
    //correct case 連続注文共通情報.連続注文取引区分≠"OP返済（既存残）"  連続注文単価調整値情報=nullの場合
    public void testValidate_C0011()
    {
        final String STR_METHOD_NAME = "testValidate_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // this.succCommonInfo.validate()
            succOptionsCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseCompleteRequest.succCommonInfo.succTradingType = "18";
            //super.super.validate()
            succOptionsCloseCompleteRequest.orderPriceDiv = "1";
            succOptionsCloseCompleteRequest.limitPrice = "1000";
            succOptionsCloseCompleteRequest.execCondType = "1";
            succOptionsCloseCompleteRequest.expirationDateType = "1";
            succOptionsCloseCompleteRequest.expirationDate = null;
            succOptionsCloseCompleteRequest.orderCondType = "0";
            succOptionsCloseCompleteRequest.stopOrderCondPrice = null;
            succOptionsCloseCompleteRequest.stopOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseCompleteRequest.wLimitPrice = null;
            succOptionsCloseCompleteRequest.wlimitExecCondType = null;
            succOptionsCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseCompleteRequest.closingOrder = "1";
            succOptionsCloseCompleteRequest.opOrderQuantity = "1000";
            for (int i = 0; i < succOptionsCloseCompleteRequest.closeMarginContractUnits.length; i++)
            {
                succOptionsCloseCompleteRequest.closeMarginContractUnits[i].id = "0001";
                succOptionsCloseCompleteRequest.closeMarginContractUnits[i].settlePriority = null;
            }
            succOptionsCloseCompleteRequest.orderId = null;
            // 連続注文単価調整値情報=nullの場合
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo = null;
            succOptionsCloseCompleteRequest.validate();
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
    //correct case 連続注文共通情報.連続注文取引区分≠　@"OP返済（前提注文）"  連続注文単価調整値情報!=nullの場合
    public void testValidate_C0012()
    {
        final String STR_METHOD_NAME = "testValidate_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // this.succCommonInfo.validate()
            succOptionsCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseCompleteRequest.succCommonInfo.succTradingType = "17";
            // super.super.validate()
            succOptionsCloseCompleteRequest.orderPriceDiv = "0";
            succOptionsCloseCompleteRequest.limitPrice = null;
            succOptionsCloseCompleteRequest.execCondType = "1";
            succOptionsCloseCompleteRequest.expirationDateType = "1";
            succOptionsCloseCompleteRequest.expirationDate = null;
            succOptionsCloseCompleteRequest.orderCondType = "0";
            succOptionsCloseCompleteRequest.stopOrderCondPrice = null;
            succOptionsCloseCompleteRequest.stopOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseCompleteRequest.wLimitPrice = null;
            succOptionsCloseCompleteRequest.wlimitExecCondType = null;
            succOptionsCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succOptionsCloseCompleteRequest.orderId = "0001";
            succOptionsCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseCompleteRequest.closingOrder ="1";
            succOptionsCloseCompleteRequest.opOrderQuantity = "1000";
            succOptionsCloseCompleteRequest.checkPrice = "1000";
            succOptionsCloseCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd"); 
            //連続注文単価調整値情報!=nullの場
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsCloseCompleteRequest.validate();
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
            succOptionsCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseCompleteRequest.validate();
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

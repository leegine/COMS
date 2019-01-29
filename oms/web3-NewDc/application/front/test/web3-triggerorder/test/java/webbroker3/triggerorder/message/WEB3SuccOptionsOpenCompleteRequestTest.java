head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.47.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccOptionsOpenCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部 
File Name        : WEB3SuccOptionsOpenCompleteRequestTest.java
Author Name      : Daiwa Institute of Research  
Revesion History : 2008/03/25 楊夫志 (中訊) 新規作成  
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3SuccOptionsOpenCompleteRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccOptionsOpenCompleteRequestTest.class);
    /**
     * <BR>
     */
    private WEB3SuccOptionsOpenCompleteRequest succOptionsOpenCompleteRequest = null;
    public WEB3SuccOptionsOpenCompleteRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succOptionsOpenCompleteRequest = new WEB3SuccOptionsOpenCompleteRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //２）　@連続注文共通情報チェック
    // 　@２－１）　@連続注文共通情報＝nullの場合、
    // 　@　@　@　@　@　@「連続注文共通情報指定なし」の例外をスローする。
    public void testValidate_0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenCompleteRequest.limitPrice = null;
            succOptionsOpenCompleteRequest.execCondType = "1";
            succOptionsOpenCompleteRequest.expirationDateType = "1";
            succOptionsOpenCompleteRequest.expirationDate = null;
            succOptionsOpenCompleteRequest.orderCondType = "0";
            succOptionsOpenCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenCompleteRequest.wLimitPrice = null;
            succOptionsOpenCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenCompleteRequest.opProductCode = "0001";
            succOptionsOpenCompleteRequest.contractType = "1";
            succOptionsOpenCompleteRequest.marketCode = "1";
            succOptionsOpenCompleteRequest.opOrderQuantity = "1000";
            succOptionsOpenCompleteRequest.orderId = null;
            //this.連続注文共通情報＝nullの場合
            succOptionsOpenCompleteRequest.succCommonInfo = null;
            succOptionsOpenCompleteRequest.validate();
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
    //２－３）　@連続注文共通情報.連続注文取引区分が以下の値以外の場合、
    // 　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。
    // 　@　@　@　@　@"OP新規建（前提注文）"
    // 　@　@　@　@　@"OP新規建"
    // 　@　@　@　@　@※コード値は、先物OP予約注文単位テーブルを参照。
    public void testvalidate_C0002()
    {
        final String STR_METHOD_NAME = "testvalidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // super.super.validate();
            succOptionsOpenCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenCompleteRequest.limitPrice = null;
            succOptionsOpenCompleteRequest.execCondType = "1";
            succOptionsOpenCompleteRequest.expirationDateType = "1";
            succOptionsOpenCompleteRequest.expirationDate = null;
            succOptionsOpenCompleteRequest.orderCondType = "0";
            succOptionsOpenCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenCompleteRequest.wLimitPrice = null;
            succOptionsOpenCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenCompleteRequest.opProductCode = "0001";
            succOptionsOpenCompleteRequest.contractType = "1";
            succOptionsOpenCompleteRequest.marketCode = "1";
            succOptionsOpenCompleteRequest.opOrderQuantity = "1000";
            succOptionsOpenCompleteRequest.orderId = null;
            //succOptionsOpenCompleteRequest.succCommonInfo != null;
            succOptionsOpenCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenCompleteRequest.succCommonInfo.parentOrderId = "0001";
            //２－３）　@連続注文共通情報.連続注文取引区分が以下の値以外の場合、
            // 　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。
            // 　@　@　@　@　@"OP新規建（前提注文）"
            // 　@　@　@　@　@"OP新規建"
            // 　@　@　@　@　@※コード値は、先物OP予約注文単位テーブルを参照。
            succOptionsOpenCompleteRequest.succCommonInfo.succTradingType = "14";
            succOptionsOpenCompleteRequest.validate();
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
    //３）　@連続注文単価調整値情報チェック
    // 　@３－１）　@連続注文単価調整値情報≠nullの場合、
    // 　@　@　@　@　@　@連続注文単価調整値情報.validate()をコールする
    public void testvalidate_C0003()
    {
        final String STR_METHOD_NAME = "testvalidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // super.super.validate();
            succOptionsOpenCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenCompleteRequest.limitPrice = null;
            succOptionsOpenCompleteRequest.execCondType = "1";
            succOptionsOpenCompleteRequest.expirationDateType = "1";
            succOptionsOpenCompleteRequest.expirationDate = null;
            succOptionsOpenCompleteRequest.orderCondType = "0";
            succOptionsOpenCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenCompleteRequest.wLimitPrice = null;
            succOptionsOpenCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenCompleteRequest.opProductCode = "0001";
            succOptionsOpenCompleteRequest.contractType = "1";
            succOptionsOpenCompleteRequest.marketCode = "1";
            succOptionsOpenCompleteRequest.opOrderQuantity = "1000";
            succOptionsOpenCompleteRequest.orderId = null;
            //succOptionsOpenCompleteRequest.succCommonInfo != null;
            succOptionsOpenCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenCompleteRequest.succCommonInfo.parentOrderId = "0001";
            //this.連続注文共通情報.連続注文取引区分が以下の値以外の場合
            succOptionsOpenCompleteRequest.succCommonInfo.succTradingType = "15";
            //３）　@連続注文単価調整値情報チェック
            // 　@３－１）　@連続注文単価調整値情報≠nullの場合、
            // 　@　@　@　@　@　@連続注文単価調整値情報.validate()をコールする
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo.sign = null;
            succOptionsOpenCompleteRequest.validate();
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
    // 　@　@　@　@　@　@連続注文共通情報.連続注文取引区分≠"OP新規建（前提注文）"であれば
    // 　@　@　@　@　@　@「連続注文取引区分が、連続注文の±指値指定不可の区分」の例外をthrowする。
    public void testvalidate_C0004()
    {
        final String STR_METHOD_NAME = "testvalidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenCompleteRequest.limitPrice = null;
            succOptionsOpenCompleteRequest.execCondType = "1";
            succOptionsOpenCompleteRequest.expirationDateType = "1";
            succOptionsOpenCompleteRequest.expirationDate = null;
            succOptionsOpenCompleteRequest.orderCondType = "0";
            succOptionsOpenCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenCompleteRequest.wLimitPrice = null;
            succOptionsOpenCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenCompleteRequest.opProductCode = "0001";
            succOptionsOpenCompleteRequest.contractType = "1";
            succOptionsOpenCompleteRequest.marketCode = "1";
            succOptionsOpenCompleteRequest.opOrderQuantity = "1000";
            succOptionsOpenCompleteRequest.orderId = null;
            //succOptionsOpenCompleteRequest.succCommonInfo != null;
            succOptionsOpenCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenCompleteRequest.succCommonInfo.parentOrderId = "0001";
            //２－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、
            // 「連続注文取引区分の値が処理対象外」の例外をthrowする。
            succOptionsOpenCompleteRequest.succCommonInfo.succTradingType = "16";
            //連続注文単価調整値情報≠nullの場合
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsOpenCompleteRequest.validate();
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
    public void testvalidate_C0005()
    {
        final String STR_METHOD_NAME = "testvalidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenCompleteRequest.orderPriceDiv = "1";
            succOptionsOpenCompleteRequest.limitPrice = "1000";
            succOptionsOpenCompleteRequest.execCondType = "1";
            succOptionsOpenCompleteRequest.expirationDateType = "1";
            succOptionsOpenCompleteRequest.expirationDate = null;
            succOptionsOpenCompleteRequest.orderCondType = "0";
            succOptionsOpenCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenCompleteRequest.wLimitPrice = null;
            succOptionsOpenCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenCompleteRequest.opProductCode = "0001";
            succOptionsOpenCompleteRequest.contractType = "1";
            succOptionsOpenCompleteRequest.marketCode = "1";
            succOptionsOpenCompleteRequest.opOrderQuantity = "1000";
            succOptionsOpenCompleteRequest.orderId = null;
            //succOptionsOpenCompleteRequest.succCommonInfo != null;
            succOptionsOpenCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenCompleteRequest.succCommonInfo.parentOrderId = "0001";
            //２－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、
            // 「連続注文取引区分の値が処理対象外」の例外をthrowする。
            succOptionsOpenCompleteRequest.succCommonInfo.succTradingType = "15";
            //連続注文単価調整値情報≠nullの場合
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsOpenCompleteRequest.validate();
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
    //４）　@連続注文・注文条件チェック<BR>
    // 　@super.validate連続注文()をコールする。
    public void testvalidate_C0006()
    {
        final String STR_METHOD_NAME = "testvalidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenCompleteRequest.limitPrice = null;
            succOptionsOpenCompleteRequest.execCondType = "3";
            succOptionsOpenCompleteRequest.expirationDateType = "1";
            succOptionsOpenCompleteRequest.expirationDate = null;
            succOptionsOpenCompleteRequest.orderCondType = "0";
            succOptionsOpenCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenCompleteRequest.wLimitPrice = null;
            succOptionsOpenCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenCompleteRequest.opProductCode = "0001";
            succOptionsOpenCompleteRequest.contractType = "1";
            succOptionsOpenCompleteRequest.marketCode = "1";
            succOptionsOpenCompleteRequest.opOrderQuantity = "1000";
            succOptionsOpenCompleteRequest.orderId = null;
            //succOptionsOpenCompleteRequest.succCommonInfo != null;
            succOptionsOpenCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenCompleteRequest.succCommonInfo.parentOrderId = "0001";
            //this.連続注文共通情報.連続注文取引区分が以下の値以外の場合
            succOptionsOpenCompleteRequest.succCommonInfo.succTradingType = "15";
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo = null;
            succOptionsOpenCompleteRequest.validate();
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
    //correct case 連続注文共通情報.連続注文取引区分="OP新規建（前提注文）"  連続注文単価調整値情報==nullの場合
    public void testvalidate_C0007()
    {
        final String STR_METHOD_NAME = "testvalidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenCompleteRequest.limitPrice = null;
            succOptionsOpenCompleteRequest.execCondType = "1";
            succOptionsOpenCompleteRequest.expirationDateType = "1";
            succOptionsOpenCompleteRequest.expirationDate = null;
            succOptionsOpenCompleteRequest.orderCondType = "0";
            succOptionsOpenCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenCompleteRequest.wLimitPrice = null;
            succOptionsOpenCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenCompleteRequest.opProductCode = "0001";
            succOptionsOpenCompleteRequest.contractType = "1";
            succOptionsOpenCompleteRequest.marketCode = "1";
            succOptionsOpenCompleteRequest.opOrderQuantity = "1000";
            succOptionsOpenCompleteRequest.orderId = null;
            //succOptionsOpenCompleteRequest.succCommonInfo != null;
            succOptionsOpenCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenCompleteRequest.succCommonInfo.parentOrderId = "0001";
            //this.連続注文共通情報.連続注文取引区分が以下の値以外の場合
            succOptionsOpenCompleteRequest.succCommonInfo.succTradingType = "16";
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo = null;
            succOptionsOpenCompleteRequest.validate();
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
    //correct case 連続注文共通情報.連続注文取引区分="OP新規建" 連続注文単価調整値情報≠nullの場合
    public void testvalidate_C0008()
    {
        final String STR_METHOD_NAME = "testvalidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenCompleteRequest.limitPrice = null;
            succOptionsOpenCompleteRequest.execCondType = "1";
            succOptionsOpenCompleteRequest.expirationDateType = "1";
            succOptionsOpenCompleteRequest.expirationDate = null;
            succOptionsOpenCompleteRequest.orderCondType = "0";
            succOptionsOpenCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenCompleteRequest.wLimitPrice = null;
            succOptionsOpenCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenCompleteRequest.opProductCode = "0001";
            succOptionsOpenCompleteRequest.contractType = "1";
            succOptionsOpenCompleteRequest.marketCode = "1";
            succOptionsOpenCompleteRequest.opOrderQuantity = "1000";
            succOptionsOpenCompleteRequest.orderId = null;
            //succOptionsOpenCompleteRequest.succCommonInfo != null;
            succOptionsOpenCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenCompleteRequest.succCommonInfo.parentOrderId = "0001";
            //this.連続注文共通情報.連続注文取引区分が以下の値以外の場合
            succOptionsOpenCompleteRequest.succCommonInfo.succTradingType = "15";
            //連続注文単価調整値情報≠nullの場合
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsOpenCompleteRequest.validate();
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
    //１）　@super.validate()をコールする。
    public void testValidate_C0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenCompleteRequest.orderPriceDiv = null;
            succOptionsOpenCompleteRequest.validate();
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
    public void testValidate_C0010()
    {
        final String STR_METHOD_NAME = "testValidate_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenCompleteRequest.limitPrice = null;
            succOptionsOpenCompleteRequest.execCondType = "1";
            succOptionsOpenCompleteRequest.expirationDateType = "1";
            succOptionsOpenCompleteRequest.expirationDate = null;
            succOptionsOpenCompleteRequest.orderCondType = "0";
            succOptionsOpenCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenCompleteRequest.wLimitPrice = null;
            succOptionsOpenCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenCompleteRequest.opProductCode = "0001";
            succOptionsOpenCompleteRequest.contractType = "1";
            succOptionsOpenCompleteRequest.marketCode = "1";
            succOptionsOpenCompleteRequest.opOrderQuantity = "1000";
            succOptionsOpenCompleteRequest.orderId = null;
            //this.連続注文共通情報!＝nullの場合
            succOptionsOpenCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsOpenCompleteRequest.validate();
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

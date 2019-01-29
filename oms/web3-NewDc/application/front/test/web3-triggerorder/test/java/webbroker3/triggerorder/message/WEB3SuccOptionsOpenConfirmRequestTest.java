head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.48.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccOptionsOpenConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部 
File Name        : WEB3SuccOptionsOpenConfirmRequestTest.java
Author Name      : Daiwa Institute of Research  
Revesion History : 2008/03/25 楊夫志 (中訊) 新規作成  
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3SuccOptionsOpenConfirmRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccOptionsOpenConfirmRequestTest.class);

    /**
     * <BR>
     */
    private WEB3SuccOptionsOpenConfirmRequest succOptionsOpenConfirmRequest = null;

    public WEB3SuccOptionsOpenConfirmRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succOptionsOpenConfirmRequest = new WEB3SuccOptionsOpenConfirmRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //２）　@連続注文共通情報チェック
    // 　@２－１）　@連続注文共通情報＝nullの場合、
    // 　@　@　@　@　@　@「連続注文共通情報指定なし」の例外をスローする。
    public void testvalidate_C0001()
    {
        final String STR_METHOD_NAME = "testvalidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenConfirmRequest.orderPriceDiv = "0";
            succOptionsOpenConfirmRequest.limitPrice = null;
            succOptionsOpenConfirmRequest.execCondType = "1";
            succOptionsOpenConfirmRequest.expirationDateType = "1";
            succOptionsOpenConfirmRequest.expirationDate = null;
            succOptionsOpenConfirmRequest.orderCondType = "0";
            succOptionsOpenConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenConfirmRequest.wLimitPrice = null;
            succOptionsOpenConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenConfirmRequest.contractType = "1";
            succOptionsOpenConfirmRequest.marketCode = "1";
            succOptionsOpenConfirmRequest.targetProductCode = "0001";
            succOptionsOpenConfirmRequest.delivaryMonth = "200803";
            succOptionsOpenConfirmRequest.opProductType = "C";
            succOptionsOpenConfirmRequest.strikePrice = "1000";
            succOptionsOpenConfirmRequest.opOrderQuantity = "1000";
            //連続注文共通情報＝nullの場合
            succOptionsOpenConfirmRequest.succCommonInfo = null;
            succOptionsOpenConfirmRequest.validate();
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

    //２－３）　@連続注文共通情報.連続注文取引区分が以下の値以外の場合、<BR>
    // 　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。<BR>
    // 　@　@　@　@　@"OP新規建（前提注文）"<BR>
    // 　@　@　@　@　@"OP新規建"<BR>
    // 　@　@　@　@　@※コード値は、先物OP予約注文単位テーブルを参照
    public void testvalidate_C0002()
    {
        final String STR_METHOD_NAME = "testvalidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenConfirmRequest.orderPriceDiv = "0";
            succOptionsOpenConfirmRequest.limitPrice = null;
            succOptionsOpenConfirmRequest.execCondType = "1";
            succOptionsOpenConfirmRequest.expirationDateType = "1";
            succOptionsOpenConfirmRequest.expirationDate = null;
            succOptionsOpenConfirmRequest.orderCondType = "0";
            succOptionsOpenConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenConfirmRequest.wLimitPrice = null;
            succOptionsOpenConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenConfirmRequest.contractType = "1";
            succOptionsOpenConfirmRequest.marketCode = "1";
            succOptionsOpenConfirmRequest.targetProductCode = "0001";
            succOptionsOpenConfirmRequest.delivaryMonth = "200803";
            succOptionsOpenConfirmRequest.opProductType = "C";
            succOptionsOpenConfirmRequest.strikePrice = "1000";
            succOptionsOpenConfirmRequest.opOrderQuantity = "1000";
            //連続注文共通情報!＝nullの場合
            succOptionsOpenConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //２－２）　@連続注文共通情報.validate()をコールする。
            succOptionsOpenConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsOpenConfirmRequest.succCommonInfo.succTradingType = "14";
            succOptionsOpenConfirmRequest.validate();
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
    // 　@　@　@　@　@　@連続注文単価調整値情報.validate()をコールする。
    public void testvalidate_C0003()
    {
        final String STR_METHOD_NAME = "testvalidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenConfirmRequest.orderPriceDiv = "0";
            succOptionsOpenConfirmRequest.limitPrice = null;
            succOptionsOpenConfirmRequest.execCondType = "1";
            succOptionsOpenConfirmRequest.expirationDateType = "1";
            succOptionsOpenConfirmRequest.expirationDate = null;
            succOptionsOpenConfirmRequest.orderCondType = "0";
            succOptionsOpenConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenConfirmRequest.wLimitPrice = null;
            succOptionsOpenConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenConfirmRequest.contractType = "1";
            succOptionsOpenConfirmRequest.marketCode = "1";
            succOptionsOpenConfirmRequest.targetProductCode = "0001";
            succOptionsOpenConfirmRequest.delivaryMonth = "200803";
            succOptionsOpenConfirmRequest.opProductType = "C";
            succOptionsOpenConfirmRequest.strikePrice = "1000";
            succOptionsOpenConfirmRequest.opOrderQuantity = "1000";
            //連続注文共通情報!＝nullの場合
            succOptionsOpenConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //２－２）　@連続注文共通情報.validate()をコールする。
            succOptionsOpenConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsOpenConfirmRequest.succCommonInfo.succTradingType = "15";
            //連続注文単価調整値情報≠nullの場合、
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo.sign = null;
            succOptionsOpenConfirmRequest.validate();
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
    //  連続注文共通情報.連続注文取引区分≠"先物新規建（前提注文）"であれば
    public void testvalidate_C0004()
    {
        final String STR_METHOD_NAME = "testvalidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenConfirmRequest.orderPriceDiv = "0";
            succOptionsOpenConfirmRequest.limitPrice = null;
            succOptionsOpenConfirmRequest.execCondType = "1";
            succOptionsOpenConfirmRequest.expirationDateType = "1";
            succOptionsOpenConfirmRequest.expirationDate = null;
            succOptionsOpenConfirmRequest.orderCondType = "0";
            succOptionsOpenConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenConfirmRequest.wLimitPrice = null;
            succOptionsOpenConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenConfirmRequest.contractType = "1";
            succOptionsOpenConfirmRequest.marketCode = "1";
            succOptionsOpenConfirmRequest.targetProductCode = "0001";
            succOptionsOpenConfirmRequest.delivaryMonth = "200803";
            succOptionsOpenConfirmRequest.opProductType = "C";
            succOptionsOpenConfirmRequest.strikePrice = "1000";
            succOptionsOpenConfirmRequest.opOrderQuantity = "1000";
            //連続注文共通情報!＝nullの場合
            succOptionsOpenConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsOpenConfirmRequest.succCommonInfo.succTradingType = "16";
            //連続注文単価調整値情報≠nullの場合、
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsOpenConfirmRequest.validate();
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
    //３－３）　@連続注文単価調整値情報≠nullの場合、
    // 　@注文単価区分≠"成行"の場合
    public void testvalidate_C0005()
    {
        final String STR_METHOD_NAME = "testvalidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenConfirmRequest.orderPriceDiv = "1";
            succOptionsOpenConfirmRequest.limitPrice = "1000";
            succOptionsOpenConfirmRequest.execCondType = "1";
            succOptionsOpenConfirmRequest.expirationDateType = "1";
            succOptionsOpenConfirmRequest.expirationDate = null;
            succOptionsOpenConfirmRequest.orderCondType = "0";
            succOptionsOpenConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenConfirmRequest.wLimitPrice = null;
            succOptionsOpenConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenConfirmRequest.contractType = "1";
            succOptionsOpenConfirmRequest.marketCode = "1";
            succOptionsOpenConfirmRequest.targetProductCode = "0001";
            succOptionsOpenConfirmRequest.delivaryMonth = "200803";
            succOptionsOpenConfirmRequest.opProductType = "C";
            succOptionsOpenConfirmRequest.strikePrice = "1000";
            succOptionsOpenConfirmRequest.opOrderQuantity = "1000";
            //連続注文共通情報!＝nullの場合
            succOptionsOpenConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenConfirmRequest.succCommonInfo.parentOrderId="0001";
            succOptionsOpenConfirmRequest.succCommonInfo.succTradingType = "15";
            //連続注文単価調整値情報≠nullの場合、
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsOpenConfirmRequest.validate();
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
    // super.validate連続注文()をコールする
    public void testvalidate_C0006()
    {
        final String STR_METHOD_NAME = "testvalidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenConfirmRequest.orderPriceDiv = "0";
            succOptionsOpenConfirmRequest.limitPrice = null;
            succOptionsOpenConfirmRequest.execCondType = "3";
            succOptionsOpenConfirmRequest.expirationDateType = "1";
            succOptionsOpenConfirmRequest.expirationDate = null;
            succOptionsOpenConfirmRequest.orderCondType = "0";
            succOptionsOpenConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenConfirmRequest.wLimitPrice = null;
            succOptionsOpenConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenConfirmRequest.contractType = "1";
            succOptionsOpenConfirmRequest.marketCode = "1";
            succOptionsOpenConfirmRequest.targetProductCode = "0001";
            succOptionsOpenConfirmRequest.delivaryMonth = "200803";
            succOptionsOpenConfirmRequest.opProductType = "C";
            succOptionsOpenConfirmRequest.strikePrice = "1000";
            succOptionsOpenConfirmRequest.opOrderQuantity = "1000";
            //連続注文共通情報!＝nullの場合
            succOptionsOpenConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenConfirmRequest.succCommonInfo.parentOrderId="0001";
            succOptionsOpenConfirmRequest.succCommonInfo.succTradingType = "15";
            //連続注文単価調整値情報=nullの場合、
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo = null;
            succOptionsOpenConfirmRequest.validate();
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
            succOptionsOpenConfirmRequest.orderPriceDiv = "0";
            succOptionsOpenConfirmRequest.limitPrice = null;
            succOptionsOpenConfirmRequest.execCondType = "1";
            succOptionsOpenConfirmRequest.expirationDateType = "1";
            succOptionsOpenConfirmRequest.expirationDate = null;
            succOptionsOpenConfirmRequest.orderCondType = "0";
            succOptionsOpenConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenConfirmRequest.wLimitPrice = null;
            succOptionsOpenConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenConfirmRequest.contractType = "1";
            succOptionsOpenConfirmRequest.marketCode = "1";
            succOptionsOpenConfirmRequest.targetProductCode = "0001";
            succOptionsOpenConfirmRequest.delivaryMonth = "200803";
            succOptionsOpenConfirmRequest.opProductType = "C";
            succOptionsOpenConfirmRequest.strikePrice = "1000";
            succOptionsOpenConfirmRequest.opOrderQuantity = "1000";
            //連続注文共通情報!＝nullの場合
            succOptionsOpenConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsOpenConfirmRequest.succCommonInfo.succTradingType = "16";
            //連続注文単価調整値情報=nullの場合、
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo = null;
            succOptionsOpenConfirmRequest.validate();
        }
        catch(WEB3BaseException l_ex)
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
    //correct case 連続注文共通情報.連続注文取引区分="OP新規建" 連続注文単価調整値情報≠nullの場合
    public void testvalidate_C0008()
    {
        final String STR_METHOD_NAME = "testvalidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenConfirmRequest.orderPriceDiv = "0";
            succOptionsOpenConfirmRequest.limitPrice = null;
            succOptionsOpenConfirmRequest.execCondType = "1";
            succOptionsOpenConfirmRequest.expirationDateType = "1";
            succOptionsOpenConfirmRequest.expirationDate = null;
            succOptionsOpenConfirmRequest.orderCondType = "0";
            succOptionsOpenConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenConfirmRequest.wLimitPrice = null;
            succOptionsOpenConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenConfirmRequest.contractType = "1";
            succOptionsOpenConfirmRequest.marketCode = "1";
            succOptionsOpenConfirmRequest.targetProductCode = "0001";
            succOptionsOpenConfirmRequest.delivaryMonth = "200803";
            succOptionsOpenConfirmRequest.opProductType = "C";
            succOptionsOpenConfirmRequest.strikePrice = "1000";
            succOptionsOpenConfirmRequest.opOrderQuantity = "1000";
            //succOptionsOpenConfirmRequest.succCommonInfo != null;
            succOptionsOpenConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenConfirmRequest.succCommonInfo.parentOrderId = "0001";
            //this.連続注文共通情報.連続注文取引区分が以下の値以外の場合
            succOptionsOpenConfirmRequest.succCommonInfo.succTradingType = "15";
            //連続注文単価調整値情報≠nullの場合
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsOpenConfirmRequest.validate();
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
            succOptionsOpenConfirmRequest.orderPriceDiv = null;
            succOptionsOpenConfirmRequest.validate();
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
            succOptionsOpenConfirmRequest.orderPriceDiv = "0";
            succOptionsOpenConfirmRequest.limitPrice = null;
            succOptionsOpenConfirmRequest.execCondType = "1";
            succOptionsOpenConfirmRequest.expirationDateType = "1";
            succOptionsOpenConfirmRequest.expirationDate = null;
            succOptionsOpenConfirmRequest.orderCondType = "0";
            succOptionsOpenConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenConfirmRequest.wLimitPrice = null;
            succOptionsOpenConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenConfirmRequest.contractType = "1";
            succOptionsOpenConfirmRequest.marketCode = "1";
            succOptionsOpenConfirmRequest.targetProductCode = "0001";
            succOptionsOpenConfirmRequest.delivaryMonth = "200103";
            succOptionsOpenConfirmRequest.opProductType = "C";
            succOptionsOpenConfirmRequest.strikePrice = "1000";
            succOptionsOpenConfirmRequest.opOrderQuantity = "1000";
            //this.連続注文共通情報!＝nullの場合
            succOptionsOpenConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsOpenConfirmRequest.validate();
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

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.47.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccOptionsOpenChangeCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部 
File Name        : WEB3SuccOptionsOpenChangeCompleteRequestTest.java
Author Name      : Daiwa Institute of Research  
Revesion History : 2008/03/25 楊夫志 (中訊) 新規作成  
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3SuccOptionsOpenChangeCompleteRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccOptionsOpenChangeCompleteRequestTest.class);

    /**
     *<BR>
     */
    private WEB3SuccOptionsOpenChangeCompleteRequest succOptionsOpenChangeCompleteRequest = null;
    public WEB3SuccOptionsOpenChangeCompleteRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succOptionsOpenChangeCompleteRequest = new WEB3SuccOptionsOpenChangeCompleteRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //２）　@確認時概算受渡代金チェック
    // 　@this.確認時概算受渡代金の値が以下のいずれかに該当する場合は、
    // 　@例外をthrowする。
    // 　@　@・null
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succOptionsOpenChangeCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenChangeCompleteRequest.limitPrice = null;
            succOptionsOpenChangeCompleteRequest.execCondType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDateType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDate = null;
            succOptionsOpenChangeCompleteRequest.orderCondType = "0";
            succOptionsOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeCompleteRequest.wLimitPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeCompleteRequest.id = "0001";
            succOptionsOpenChangeCompleteRequest.opOrderQuantity = "1000";
            //２）　@確認時概算受渡代金チェック
            // 　@this.確認時概算受渡代金の値が以下のいずれかに該当する場合は、
            // 　@例外をthrowする。
            // 　@　@・null
            succOptionsOpenChangeCompleteRequest.estimatedPrice = null;
            succOptionsOpenChangeCompleteRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02292, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    // ２）　@確認時概算受渡代金チェック
    // 　@this.確認時概算受渡代金の値が以下のいずれかに該当する場合は、
    // 　@例外をthrowする。
    // 　@　@・数字以外 "XXXX"
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succOptionsOpenChangeCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenChangeCompleteRequest.limitPrice = null;
            succOptionsOpenChangeCompleteRequest.execCondType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDateType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDate = null;
            succOptionsOpenChangeCompleteRequest.orderCondType = "0";
            succOptionsOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeCompleteRequest.wLimitPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeCompleteRequest.id = "0001";
            succOptionsOpenChangeCompleteRequest.opOrderQuantity = "1000";
            //２）　@確認時概算受渡代金チェック
            // 　@this.確認時概算受渡代金の値が以下のいずれかに該当する場合は、
            // 　@例外をthrowする。
            // 　@　@・数字以外"XXXX"
            succOptionsOpenChangeCompleteRequest.estimatedPrice = "aaa";
            succOptionsOpenChangeCompleteRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02292, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //２）　@確認時概算受渡代金チェック
    // 　@this.確認時概算受渡代金の値が以下のいずれかに該当する場合は、
    // 　@例外をthrowする。
    // 　@　@・数字以外 ""
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succOptionsOpenChangeCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenChangeCompleteRequest.limitPrice = null;
            succOptionsOpenChangeCompleteRequest.execCondType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDateType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDate = null;
            succOptionsOpenChangeCompleteRequest.orderCondType = "0";
            succOptionsOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeCompleteRequest.wLimitPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeCompleteRequest.id = "0001";
            succOptionsOpenChangeCompleteRequest.opOrderQuantity = "1000";
            //２）　@確認時概算受渡代金チェック
            // 　@this.確認時概算受渡代金の値が以下のいずれかに該当する場合は、
            // 　@例外をthrowする。
            // 　@　@・数字以外""
            succOptionsOpenChangeCompleteRequest.estimatedPrice = "";
            succOptionsOpenChangeCompleteRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02292, l_ex.getErrorInfo());
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
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succOptionsOpenChangeCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenChangeCompleteRequest.limitPrice = null;
            succOptionsOpenChangeCompleteRequest.execCondType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDateType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDate = null;
            succOptionsOpenChangeCompleteRequest.orderCondType = "0";
            succOptionsOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeCompleteRequest.wLimitPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeCompleteRequest.id = "0001";
            succOptionsOpenChangeCompleteRequest.opOrderQuantity = "1000";
            //２）　@確認時概算建代金チェック
            succOptionsOpenChangeCompleteRequest.estimatedPrice = "1000";
            //３）　@連続注文単価調整値情報チェック
            // 　@３－１）　@連続注文単価調整値情報≠nullの場合、
            // 　@　@　@　@　@　@連続注文単価調整値情報.validate()をコールする。
            succOptionsOpenChangeCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succOptionsOpenChangeCompleteRequest.priceAdjustmentValueInfo.sign = null;
            succOptionsOpenChangeCompleteRequest.validate();
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
    //        注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が不整合」の
    //        例外をthrowする。
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succOptionsOpenChangeCompleteRequest.orderPriceDiv = "1";
            succOptionsOpenChangeCompleteRequest.limitPrice = "1000";
            succOptionsOpenChangeCompleteRequest.execCondType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDateType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDate = null;
            succOptionsOpenChangeCompleteRequest.orderCondType = "0";
            succOptionsOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeCompleteRequest.wLimitPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeCompleteRequest.id = "0001";
            succOptionsOpenChangeCompleteRequest.opOrderQuantity = "1000";
            //２）　@確認時概算建代金チェック
            succOptionsOpenChangeCompleteRequest.estimatedPrice = "1000";
            //３）　@連続注文単価調整値情報≠nullの場合、
            succOptionsOpenChangeCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            // ３－１）　@連続注文単価調整値情報≠nullの場合、
            //  連続注文単価調整値情報.validate()をコールする。
            succOptionsOpenChangeCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsOpenChangeCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            //３－２）　@連続注文単価調整値情報≠nullの場合、
            //        注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が不整合」の
            //        例外をthrowする。Request.orderPriceDiv = "1";
            succOptionsOpenChangeCompleteRequest.validate();
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
    // 　@super.validate連続注文()をコールする。
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succOptionsOpenChangeCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenChangeCompleteRequest.limitPrice = null;
            succOptionsOpenChangeCompleteRequest.execCondType = "3";
            succOptionsOpenChangeCompleteRequest.expirationDateType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDate = null;
            succOptionsOpenChangeCompleteRequest.orderCondType = "0";
            succOptionsOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeCompleteRequest.wLimitPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeCompleteRequest.id = "0001";
            succOptionsOpenChangeCompleteRequest.opOrderQuantity = "1000";
            //２）　@確認時概算建代金チェック
            succOptionsOpenChangeCompleteRequest.estimatedPrice = "1000";
            //３）　@連続注文単価調整値情報=nullの場合、
            succOptionsOpenChangeCompleteRequest.priceAdjustmentValueInfo = null;
            succOptionsOpenChangeCompleteRequest.validate();
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
    //correct case 連続注文単価調整値情報=nullの場合
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succOptionsOpenChangeCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenChangeCompleteRequest.limitPrice = null;
            succOptionsOpenChangeCompleteRequest.execCondType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDateType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDate = null;
            succOptionsOpenChangeCompleteRequest.orderCondType = "0";
            succOptionsOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeCompleteRequest.wLimitPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeCompleteRequest.id = "0001";
            succOptionsOpenChangeCompleteRequest.opOrderQuantity = "1000";
            //２）　@確認時概算建代金チェック
            succOptionsOpenChangeCompleteRequest.estimatedPrice = "1000";
            //３） 連続注文単価調整値情報チェック
            succOptionsOpenChangeCompleteRequest.priceAdjustmentValueInfo = null;
            succOptionsOpenChangeCompleteRequest.validate();
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
    //correct case 連続注文単価調整値情報≠nullの場合
    public void testValidate_C0008()
    {
        final String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succOptionsOpenChangeCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenChangeCompleteRequest.limitPrice = null;
            succOptionsOpenChangeCompleteRequest.execCondType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDateType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDate = null;
            succOptionsOpenChangeCompleteRequest.orderCondType = "0";
            succOptionsOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeCompleteRequest.wLimitPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeCompleteRequest.id = "0001";
            succOptionsOpenChangeCompleteRequest.opOrderQuantity = "1000";
            //２）　@確認時概算建代金チェック
            succOptionsOpenChangeCompleteRequest.estimatedPrice = "1000";
            //３） 連続注文単価調整値情報チェック
            succOptionsOpenChangeCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //３－１）　@連続注文単価調整値情報≠nullの場合、
            //  連続注文単価調整値情報.validate()をコールする。
            succOptionsOpenChangeCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsOpenChangeCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsOpenChangeCompleteRequest.validate();
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
    public void testValidate_C0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succOptionsOpenChangeCompleteRequest.orderPriceDiv = null;
            succOptionsOpenChangeCompleteRequest.validate();
            fail();
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
}
@

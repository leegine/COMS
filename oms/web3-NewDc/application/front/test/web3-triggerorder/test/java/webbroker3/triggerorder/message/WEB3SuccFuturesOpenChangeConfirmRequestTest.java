head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.48.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccFuturesOpenChangeConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :（連続）株価指数先物訂正新規建確認リクエストテスト(WEB3SuccFuturesOpenChangeConfirmRequestTest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2008/03/17 楊夫志 (中訊) 新規作成
 */
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * （連続）株価指数先物訂正新規建確認リクエスト<BR>
 * <BR>
 * @@author yang-fuzhi
 * @@version 1.0
 */
public class WEB3SuccFuturesOpenChangeConfirmRequestTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccFuturesOpenCompleteRequestTest.class);

    private WEB3SuccFuturesOpenChangeConfirmRequest succFuturesOpenChangeConfirm = null;

    public WEB3SuccFuturesOpenChangeConfirmRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succFuturesOpenChangeConfirm = new WEB3SuccFuturesOpenChangeConfirmRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //２－１）　@連続注文単価調整値情報≠nullの場合、
    //  連続注文単価調整値情報.validate()をコールする。
    public void testvalidate_C0001()
    {
        final String STR_METHOD_NAME = "testvalidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succFuturesOpenChangeConfirm.orderPriceDiv = "0";
            succFuturesOpenChangeConfirm.limitPrice = null;
            succFuturesOpenChangeConfirm.execCondType = "1";
            succFuturesOpenChangeConfirm.expirationDateType = "1";
            succFuturesOpenChangeConfirm.expirationDate = null;
            succFuturesOpenChangeConfirm.orderCondType = "0";
            succFuturesOpenChangeConfirm.stopOrderCondPrice = null;
            succFuturesOpenChangeConfirm.stopOrderCondOperator = null;
            succFuturesOpenChangeConfirm.wlimitOrderCondPrice = null;
            succFuturesOpenChangeConfirm.wlimitOrderCondOperator = null;
            succFuturesOpenChangeConfirm.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeConfirm.wLimitPrice = null;
            succFuturesOpenChangeConfirm.wlimitExecCondType = null;
            succFuturesOpenChangeConfirm.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeConfirm.id = "0001";
            succFuturesOpenChangeConfirm.futOrderQuantity = "1000";
            //  ２－１）　@連続注文単価調整値情報≠nullの場合、
            //  連続注文単価調整値情報.validate()をコールする。
            succFuturesOpenChangeConfirm.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesOpenChangeConfirm.priceAdjustmentValueInfo.sign = null;
            succFuturesOpenChangeConfirm.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
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
    // ２－２）　@連続注文単価調整値情報≠nullの場合、
    //  注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が不整合」
    //  の例外をthrowする。
    public void testvalidate_C0002()
    {
        final String STR_METHOD_NAME = "testvalidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succFuturesOpenChangeConfirm.orderPriceDiv = "1";
            succFuturesOpenChangeConfirm.limitPrice = "1000";
            succFuturesOpenChangeConfirm.execCondType = "1";
            succFuturesOpenChangeConfirm.expirationDateType = "1";
            succFuturesOpenChangeConfirm.expirationDate = null;
            succFuturesOpenChangeConfirm.orderCondType = "0";
            succFuturesOpenChangeConfirm.stopOrderCondPrice = null;
            succFuturesOpenChangeConfirm.stopOrderCondOperator = null;
            succFuturesOpenChangeConfirm.wlimitOrderCondPrice = null;
            succFuturesOpenChangeConfirm.wlimitOrderCondOperator = null;
            succFuturesOpenChangeConfirm.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeConfirm.wLimitPrice = null;
            succFuturesOpenChangeConfirm.wlimitExecCondType = null;
            succFuturesOpenChangeConfirm.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeConfirm.id = "0001";
            succFuturesOpenChangeConfirm.futOrderQuantity = "1000";
            //連続注文単価調整値情報≠nullの場合
            succFuturesOpenChangeConfirm.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succFuturesOpenChangeConfirm.priceAdjustmentValueInfo.sign = "+";
            succFuturesOpenChangeConfirm.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesOpenChangeConfirm.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
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
    // ３）　@連続注文・注文条件チェック
    // super.validate連続注文()をコールする。
    public void testvalidate_C0003()
    {
        final String STR_METHOD_NAME = "testvalidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succFuturesOpenChangeConfirm.orderPriceDiv = "0";
            succFuturesOpenChangeConfirm.limitPrice = null;
            succFuturesOpenChangeConfirm.execCondType = "3";
            succFuturesOpenChangeConfirm.expirationDateType = "1";
            succFuturesOpenChangeConfirm.expirationDate = null;
            succFuturesOpenChangeConfirm.orderCondType = "0";
            succFuturesOpenChangeConfirm.stopOrderCondPrice = null;
            succFuturesOpenChangeConfirm.stopOrderCondOperator = null;
            succFuturesOpenChangeConfirm.wlimitOrderCondPrice = null;
            succFuturesOpenChangeConfirm.wlimitOrderCondOperator = null;
            succFuturesOpenChangeConfirm.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeConfirm.wLimitPrice = null;
            succFuturesOpenChangeConfirm.wlimitExecCondType = null;
            succFuturesOpenChangeConfirm.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeConfirm.id = "0001";
            succFuturesOpenChangeConfirm.futOrderQuantity = "1000";
            //連続注文単価調整値情報==nullの場合
            succFuturesOpenChangeConfirm.priceAdjustmentValueInfo = null;
            succFuturesOpenChangeConfirm.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
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
    //correct case 連続注文単価調整値情報==nullの場合
    public void testvalidate_C0004()
    {
        final String STR_METHOD_NAME = "testvalidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succFuturesOpenChangeConfirm.orderPriceDiv = "0";
            succFuturesOpenChangeConfirm.limitPrice = null;
            succFuturesOpenChangeConfirm.execCondType = "1";
            succFuturesOpenChangeConfirm.expirationDateType = "1";
            succFuturesOpenChangeConfirm.expirationDate = null;
            succFuturesOpenChangeConfirm.orderCondType = "0";
            succFuturesOpenChangeConfirm.stopOrderCondPrice = null;
            succFuturesOpenChangeConfirm.stopOrderCondOperator = null;
            succFuturesOpenChangeConfirm.wlimitOrderCondPrice = null;
            succFuturesOpenChangeConfirm.wlimitOrderCondOperator = null;
            succFuturesOpenChangeConfirm.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeConfirm.wLimitPrice = null;
            succFuturesOpenChangeConfirm.wlimitExecCondType = null;
            succFuturesOpenChangeConfirm.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeConfirm.id = "0001";
            succFuturesOpenChangeConfirm.futOrderQuantity = "1000";
            //連続注文単価調整値情報==nullの場合
            succFuturesOpenChangeConfirm.priceAdjustmentValueInfo = null;
            succFuturesOpenChangeConfirm.validate();
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
    //correct case　@連続注文単価調整値情報≠nullの場合
    public void testvalidate_C0005()
    {
        final String STR_METHOD_NAME = "testvalidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succFuturesOpenChangeConfirm.orderPriceDiv = "0";
            succFuturesOpenChangeConfirm.limitPrice = null;
            succFuturesOpenChangeConfirm.execCondType = "1";
            succFuturesOpenChangeConfirm.expirationDateType = "1";
            succFuturesOpenChangeConfirm.expirationDate = null;
            succFuturesOpenChangeConfirm.orderCondType = "0";
            succFuturesOpenChangeConfirm.stopOrderCondPrice = null;
            succFuturesOpenChangeConfirm.stopOrderCondOperator = null;
            succFuturesOpenChangeConfirm.wlimitOrderCondPrice = null;
            succFuturesOpenChangeConfirm.wlimitOrderCondOperator = null;
            succFuturesOpenChangeConfirm.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeConfirm.wLimitPrice = null;
            succFuturesOpenChangeConfirm.wlimitExecCondType = null;
            succFuturesOpenChangeConfirm.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeConfirm.id = "0001";
            succFuturesOpenChangeConfirm.futOrderQuantity = "1000";
            //連続注文単価調整値情報≠nullの場合
            succFuturesOpenChangeConfirm.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succFuturesOpenChangeConfirm.priceAdjustmentValueInfo.sign = "+";
            succFuturesOpenChangeConfirm.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesOpenChangeConfirm.validate();
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
    // １）　@super.validate()をコールする。
    public void testvalidate_C0006()
    {
        final String STR_METHOD_NAME = "testvalidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succFuturesOpenChangeConfirm.orderPriceDiv = null;
            succFuturesOpenChangeConfirm.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
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

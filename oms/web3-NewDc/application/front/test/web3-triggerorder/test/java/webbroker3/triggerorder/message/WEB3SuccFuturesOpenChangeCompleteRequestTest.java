head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.47.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccFuturesOpenChangeCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :（連続）株価指数先物訂正新規建完了リクエストテスト(WEB3SuccFuturesOpenChangeCompleteRequestTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/03/17 楊夫志 (中訊) 新規作成
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;
/**
 * （連続）株価指数先物訂正新規建完了リクエスト<BR>
 * @@author yang-fuzhi
 * @@version 1.0
 */
public class WEB3SuccFuturesOpenChangeCompleteRequestTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccFuturesOpenInputRequestTest.class);

    /**
     *<BR>
     */
    private WEB3SuccFuturesOpenChangeCompleteRequest succFuturesOpenChangeCompleteRequest = null;

    public WEB3SuccFuturesOpenChangeCompleteRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succFuturesOpenChangeCompleteRequest = new WEB3SuccFuturesOpenChangeCompleteRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //   ２）　@確認時概算建代金チェック
    //  this.確認時概算建代金の値が以下のいずれかに該当する場合は
    //  null
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succFuturesOpenChangeCompleteRequest.orderPriceDiv = "0";
            succFuturesOpenChangeCompleteRequest.limitPrice = null;
            succFuturesOpenChangeCompleteRequest.execCondType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDateType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDate = null;
            succFuturesOpenChangeCompleteRequest.orderCondType = "0";
            succFuturesOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeCompleteRequest.wLimitPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitExecCondType = null;
            succFuturesOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeCompleteRequest.id = "0001";
            succFuturesOpenChangeCompleteRequest.futOrderQuantity = "1000";
            //２）　@確認時概算建代金チェック
            //  this.確認時概算建代金の値が以下のいずれかに該当する場合は
            //  null
            succFuturesOpenChangeCompleteRequest.estimatedContractPrice = null;
            succFuturesOpenChangeCompleteRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03061, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //  ２）　@確認時概算建代金チェック
    //  this.確認時概算建代金の値が以下のいずれかに該当する場合は
    //  数字以外 "XXXX"
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succFuturesOpenChangeCompleteRequest.orderPriceDiv = "0";
            succFuturesOpenChangeCompleteRequest.limitPrice = null;
            succFuturesOpenChangeCompleteRequest.execCondType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDateType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDate = null;
            succFuturesOpenChangeCompleteRequest.orderCondType = "0";
            succFuturesOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeCompleteRequest.wLimitPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitExecCondType = null;
            succFuturesOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeCompleteRequest.id = "0001";
            succFuturesOpenChangeCompleteRequest.futOrderQuantity = "1000";
            //数字以外
            succFuturesOpenChangeCompleteRequest.estimatedContractPrice = "aaa";
            succFuturesOpenChangeCompleteRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03061, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //  ２）　@確認時概算建代金チェック
    //  this.確認時概算建代金の値が以下のいずれかに該当する場合は
    //  数字以外 ""
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succFuturesOpenChangeCompleteRequest.orderPriceDiv = "0";
            succFuturesOpenChangeCompleteRequest.limitPrice = null;
            succFuturesOpenChangeCompleteRequest.execCondType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDateType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDate = null;
            succFuturesOpenChangeCompleteRequest.orderCondType = "0";
            succFuturesOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeCompleteRequest.wLimitPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitExecCondType = null;
            succFuturesOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeCompleteRequest.id = "0001";
            succFuturesOpenChangeCompleteRequest.futOrderQuantity = "1000";
            //数字以外
            succFuturesOpenChangeCompleteRequest.estimatedContractPrice = "";
            succFuturesOpenChangeCompleteRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03061, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //３） 連続注文単価調整値情報チェック
    // 　@連続注文単価調整値情報≠nullの場合、
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succFuturesOpenChangeCompleteRequest.orderPriceDiv = "0";
            succFuturesOpenChangeCompleteRequest.limitPrice = null;
            succFuturesOpenChangeCompleteRequest.execCondType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDateType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDate = null;
            succFuturesOpenChangeCompleteRequest.orderCondType = "0";
            succFuturesOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeCompleteRequest.wLimitPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitExecCondType = null;
            succFuturesOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeCompleteRequest.id = "0001";
            succFuturesOpenChangeCompleteRequest.futOrderQuantity = "1000";
            //２）　@確認時概算建代金チェック
            succFuturesOpenChangeCompleteRequest.estimatedContractPrice = "1000";
            //連続注文単価調整値情報≠nullの場合、
            succFuturesOpenChangeCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesOpenChangeCompleteRequest.priceAdjustmentValueInfo.sign = null;
            succFuturesOpenChangeCompleteRequest.validate();
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
    //３） 連続注文単価調整値情報チェック
    //連続注文単価調整値情報≠nullの場合、
    //注文単価区分≠"成行"の場合
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succFuturesOpenChangeCompleteRequest.orderPriceDiv = "1";
            succFuturesOpenChangeCompleteRequest.limitPrice = "1000";
            succFuturesOpenChangeCompleteRequest.execCondType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDateType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDate = null;
            succFuturesOpenChangeCompleteRequest.orderCondType = "0";
            succFuturesOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeCompleteRequest.wLimitPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitExecCondType = null;
            succFuturesOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeCompleteRequest.id = "0001";
            succFuturesOpenChangeCompleteRequest.futOrderQuantity = "1000";
            //２）　@確認時概算建代金チェック
            succFuturesOpenChangeCompleteRequest.estimatedContractPrice = "1000";
            //３）　@連続注文単価調整値情報≠nullの場合、
            succFuturesOpenChangeCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            // ３－１）　@連続注文単価調整値情報≠nullの場合、
            //  連続注文単価調整値情報.validate()をコールする。
            succFuturesOpenChangeCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesOpenChangeCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesOpenChangeCompleteRequest.validate();
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
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succFuturesOpenChangeCompleteRequest.orderPriceDiv = "0";
            succFuturesOpenChangeCompleteRequest.limitPrice = null;
            succFuturesOpenChangeCompleteRequest.execCondType = "3";
            succFuturesOpenChangeCompleteRequest.expirationDateType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDate = null;
            succFuturesOpenChangeCompleteRequest.orderCondType = "0";
            succFuturesOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeCompleteRequest.wLimitPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitExecCondType = null;
            succFuturesOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeCompleteRequest.id = "0001";
            succFuturesOpenChangeCompleteRequest.futOrderQuantity = "1000";
            //２）　@確認時概算建代金チェック
            succFuturesOpenChangeCompleteRequest.estimatedContractPrice = "1000";
            //３）　@連続注文単価調整値情報=nullの場合、
            succFuturesOpenChangeCompleteRequest.priceAdjustmentValueInfo = null;
            succFuturesOpenChangeCompleteRequest.validate();
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
            succFuturesOpenChangeCompleteRequest.orderPriceDiv = "0";
            succFuturesOpenChangeCompleteRequest.limitPrice = null;
            succFuturesOpenChangeCompleteRequest.execCondType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDateType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDate = null;
            succFuturesOpenChangeCompleteRequest.orderCondType = "0";
            succFuturesOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeCompleteRequest.wLimitPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitExecCondType = null;
            succFuturesOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeCompleteRequest.id = "0001";
            succFuturesOpenChangeCompleteRequest.futOrderQuantity = "1000";
            //２）　@確認時概算建代金チェック
            succFuturesOpenChangeCompleteRequest.estimatedContractPrice = "1000";
            //３） 連続注文単価調整値情報チェック
            succFuturesOpenChangeCompleteRequest.priceAdjustmentValueInfo = null;
            succFuturesOpenChangeCompleteRequest.validate();
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
            succFuturesOpenChangeCompleteRequest.orderPriceDiv = "0";
            succFuturesOpenChangeCompleteRequest.limitPrice = null;
            succFuturesOpenChangeCompleteRequest.execCondType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDateType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDate = null;
            succFuturesOpenChangeCompleteRequest.orderCondType = "0";
            succFuturesOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeCompleteRequest.wLimitPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitExecCondType = null;
            succFuturesOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeCompleteRequest.id = "0001";
            succFuturesOpenChangeCompleteRequest.futOrderQuantity = "1000";
            //２）　@確認時概算建代金チェック
            succFuturesOpenChangeCompleteRequest.estimatedContractPrice = "1000";
            //３） 連続注文単価調整値情報チェック
            succFuturesOpenChangeCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //３－１）　@連続注文単価調整値情報≠nullの場合、
            //  連続注文単価調整値情報.validate()をコールする。
            succFuturesOpenChangeCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesOpenChangeCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesOpenChangeCompleteRequest.validate();
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
    //super.validate()をコールする。
    public void testValidate_C0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succFuturesOpenChangeCompleteRequest.orderPriceDiv = null;
            succFuturesOpenChangeCompleteRequest.validate();
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

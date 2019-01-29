head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.46.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccFuturesCloseChangeCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : （連続）株価指数先物訂正返済完了リクエストテスト(WEB3SuccFuturesCloseChangeCompleteRequestTest.java)
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
 * （連続）株価指数先物訂正返済完了リクエストテスト
 * 
 * @@author yang-fuzhi
 * @@version 1.0
 */
public class WEB3SuccFuturesCloseChangeCompleteRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccFuturesCloseCompleteRequestTest.class);

    private WEB3SuccFuturesCloseChangeCompleteRequest succFuturesCloseChangeCompleteRequest = null;

    public WEB3SuccFuturesCloseChangeCompleteRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succFuturesCloseChangeCompleteRequest = new WEB3SuccFuturesCloseChangeCompleteRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    // １） 確認時概算決済損益チェック
    // null
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeCompleteRequest.estimatedSettleIncome = null;
            succFuturesCloseChangeCompleteRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseChangeCompleteRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03063, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    // １） 確認時概算決済損益チェック
    // 数字以外 "XXXX"
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeCompleteRequest.estimatedSettleIncome = "aaa";
            succFuturesCloseChangeCompleteRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseChangeCompleteRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03063, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    // １） 確認時概算決済損益チェック
    // 数字以外 ""
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeCompleteRequest.estimatedSettleIncome = "";
            succFuturesCloseChangeCompleteRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseChangeCompleteRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03063, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    // ２） 連続注文単価調整値情報チェック
    // ２－１） 連続注文単価調整値情報≠nullの場合、
    // 連続注文単価調整値情報.validate()をコールする。
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeCompleteRequest.estimatedSettleIncome = "1000";
            succFuturesCloseChangeCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseChangeCompleteRequest.priceAdjustmentValueInfo.sign = null;
            succFuturesCloseChangeCompleteRequest.orderPriceDiv = "0";
            succFuturesCloseChangeCompleteRequest.validate();
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

    // ２－２） 連続注文単価調整値情報≠nullの場合、
    // 注文単価区分≠"成行"の場合
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeCompleteRequest.estimatedSettleIncome = "1000";
            succFuturesCloseChangeCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseChangeCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesCloseChangeCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesCloseChangeCompleteRequest.orderPriceDiv = "1";
            succFuturesCloseChangeCompleteRequest.validate();
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

    // correct case 連続注文単価調整値情報==nullの場合
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeCompleteRequest.estimatedSettleIncome = "1000";
            succFuturesCloseChangeCompleteRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseChangeCompleteRequest.validate();
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

    // correct case 連続注文単価調整値情報≠nullの場合
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeCompleteRequest.estimatedSettleIncome = "1000";
            // １） 連続注文単価調整値情報チェック
            // １－１） 連続注文単価調整値情報≠nullの場合
            succFuturesCloseChangeCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            // 連続注文単価調整値情報.validate()をコールする。
            succFuturesCloseChangeCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesCloseChangeCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            // １－２） 連続注文単価調整値情報≠nullの場合、
            // 注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が不整合」の
            // 例外をthrowする。
            succFuturesCloseChangeCompleteRequest.orderPriceDiv = "0";
            succFuturesCloseChangeCompleteRequest.validate();
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

    // １）　@スーパークラスのvalidateメソッドをコールする。
    public void testValidateATExistingRemainderTrading_C0001()
    {
        final String STR_METHOD_NAME = "testValidateATExistingRemainderTrading_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeCompleteRequest.orderPriceDiv = null;
            succFuturesCloseChangeCompleteRequest.validateATExistingRemainderTrading();
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
    //  ２）　@連続注文・注文条件チェック
    public void testValidateATExistingRemainderTrading_C0002()
    {
        final String STR_METHOD_NAME = "testValidateATExistingRemainderTrading_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate()
            succFuturesCloseChangeCompleteRequest.orderPriceDiv = "1";
            succFuturesCloseChangeCompleteRequest.limitPrice = "1000";
            succFuturesCloseChangeCompleteRequest.execCondType = "3";
            succFuturesCloseChangeCompleteRequest.expirationDateType = "1";
            succFuturesCloseChangeCompleteRequest.expirationDate = null;
            succFuturesCloseChangeCompleteRequest.orderCondType = "0";
            succFuturesCloseChangeCompleteRequest.stopOrderCondPrice = null;
            succFuturesCloseChangeCompleteRequest.stopOrderCondOperator = null;
            succFuturesCloseChangeCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesCloseChangeCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesCloseChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseChangeCompleteRequest.wLimitPrice = null;
            succFuturesCloseChangeCompleteRequest.wlimitExecCondType = null;
            succFuturesCloseChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate
            succFuturesCloseChangeCompleteRequest.id = "0001";
            succFuturesCloseChangeCompleteRequest.futOrderQuantity = "1000";
            succFuturesCloseChangeCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseChangeCompleteRequest.validateATExistingRemainderTrading();
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
    //２）　@correct case
    public void testValidateATExistingRemainderTrading_C0003()
    {
        final String STR_METHOD_NAME = "testValidateATExistingRemainderTrading_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate()
            succFuturesCloseChangeCompleteRequest.orderPriceDiv = "1";
            succFuturesCloseChangeCompleteRequest.limitPrice = "1000";
            succFuturesCloseChangeCompleteRequest.execCondType = "1";
            succFuturesCloseChangeCompleteRequest.expirationDateType = "1";
            succFuturesCloseChangeCompleteRequest.expirationDate = null;
            succFuturesCloseChangeCompleteRequest.orderCondType = "0";
            succFuturesCloseChangeCompleteRequest.stopOrderCondPrice = null;
            succFuturesCloseChangeCompleteRequest.stopOrderCondOperator = null;
            succFuturesCloseChangeCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesCloseChangeCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesCloseChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseChangeCompleteRequest.wLimitPrice = null;
            succFuturesCloseChangeCompleteRequest.wlimitExecCondType = null;
            succFuturesCloseChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate
            succFuturesCloseChangeCompleteRequest.id = "0001";
            succFuturesCloseChangeCompleteRequest.futOrderQuantity = "1000";
            succFuturesCloseChangeCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseChangeCompleteRequest.validateATExistingRemainderTrading();
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

}
@

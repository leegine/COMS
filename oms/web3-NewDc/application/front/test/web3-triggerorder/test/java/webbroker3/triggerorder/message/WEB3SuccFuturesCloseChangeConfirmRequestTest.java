head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.48.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccFuturesCloseChangeConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : （連続）株価指数先物訂正返済確認リクエストテスト(WEB3SuccFuturesCloseChangeConfirmRequestTest.java)
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
 * （連続）株価指数先物訂正返済確認リクエストテスト
 * 
 * @@author yang-fuzhi
 * 
 */
public class WEB3SuccFuturesCloseChangeConfirmRequestTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccFuturesCloseCompleteRequestTest.class);

    WEB3SuccFuturesCloseChangeConfirmRequest succFuturesCloseChangeConfirmRequest = null;

    public WEB3SuccFuturesCloseChangeConfirmRequestTest(String arg0)
    {
        super(arg0);
        MOCK_MANAGER.setIsMockUsed(true);
        this.succFuturesCloseChangeConfirmRequest = new WEB3SuccFuturesCloseChangeConfirmRequest();
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    // １） 連続注文単価調整値情報チェック
    // １－１） 連続注文単価調整値情報≠nullの場合、
    // 連続注文単価調整値情報.validate()をコールする。
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseChangeConfirmRequest.priceAdjustmentValueInfo.sign = null;
            succFuturesCloseChangeConfirmRequest.validate();
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

    // １－２） 連続注文単価調整値情報≠nullの場合、
    // 注文単価区分≠"成行"の場合
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseChangeConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesCloseChangeConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "10000";
            succFuturesCloseChangeConfirmRequest.orderPriceDiv = "1";
            succFuturesCloseChangeConfirmRequest.validate();
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

    // correct case 連続注文単価調整値情報=nullの場合
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeConfirmRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseChangeConfirmRequest.validate();
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
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseChangeConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesCloseChangeConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "10000";
            succFuturesCloseChangeConfirmRequest.orderPriceDiv = "0";
            succFuturesCloseChangeConfirmRequest.validate();
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

    // １） スーパークラスのvalidateメソッドをコールする。
    public void testValidateATExistingRemainderTrading_C0001()
    {
        final String STR_METHOD_NAME = "testValidateATExistingRemainderTrading_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeConfirmRequest.orderPriceDiv = null;
            succFuturesCloseChangeConfirmRequest.validateATExistingRemainderTrading();
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

    // ２） 連続注文・注文条件チェック
    public void testValidateATExistingRemainderTrading_C0002()
    {
        final String STR_METHOD_NAME = "testValidateATExistingRemainderTrading_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate()
            succFuturesCloseChangeConfirmRequest.orderPriceDiv = "1";
            succFuturesCloseChangeConfirmRequest.limitPrice = "1000";
            succFuturesCloseChangeConfirmRequest.execCondType = "3";
            succFuturesCloseChangeConfirmRequest.expirationDateType = "1";
            succFuturesCloseChangeConfirmRequest.expirationDate = null;
            succFuturesCloseChangeConfirmRequest.orderCondType = "0";
            succFuturesCloseChangeConfirmRequest.stopOrderCondPrice = null;
            succFuturesCloseChangeConfirmRequest.stopOrderCondOperator = null;
            succFuturesCloseChangeConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesCloseChangeConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesCloseChangeConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseChangeConfirmRequest.wLimitPrice = null;
            succFuturesCloseChangeConfirmRequest.wlimitExecCondType = null;
            succFuturesCloseChangeConfirmRequest.wlimitEnableStatusDiv = null;
            // super.validate();
            succFuturesCloseChangeConfirmRequest.id = "0001";
            succFuturesCloseChangeConfirmRequest.futOrderQuantity = "1000";
            succFuturesCloseChangeConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseChangeConfirmRequest.validateATExistingRemainderTrading();
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
    //correct case
    public void testValidateATExistingRemainderTrading_C0003()
    {
        final String STR_METHOD_NAME = "testValidateATExistingRemainderTrading_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate()
            succFuturesCloseChangeConfirmRequest.orderPriceDiv = "1";
            succFuturesCloseChangeConfirmRequest.limitPrice = "1000";
            succFuturesCloseChangeConfirmRequest.execCondType = "1";
            succFuturesCloseChangeConfirmRequest.expirationDateType = "1";
            succFuturesCloseChangeConfirmRequest.expirationDate = null;
            succFuturesCloseChangeConfirmRequest.orderCondType = "0";
            succFuturesCloseChangeConfirmRequest.stopOrderCondPrice = null;
            succFuturesCloseChangeConfirmRequest.stopOrderCondOperator = null;
            succFuturesCloseChangeConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesCloseChangeConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesCloseChangeConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseChangeConfirmRequest.wLimitPrice = null;
            succFuturesCloseChangeConfirmRequest.wlimitExecCondType = null;
            succFuturesCloseChangeConfirmRequest.wlimitEnableStatusDiv = null;
            // super.validate();
            succFuturesCloseChangeConfirmRequest.id = "0001";
            succFuturesCloseChangeConfirmRequest.futOrderQuantity = "1000";
            succFuturesCloseChangeConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseChangeConfirmRequest.validateATExistingRemainderTrading();
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

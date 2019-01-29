head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.48.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccOptionsCloseChangeCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
 Copyright        : (株)大和総研 証券ソリューションシステム第二部 
 File Name        : WEB3SuccOptionsCloseChangeCompleteRequestTest.java
 Author Name      : Daiwa Institute of Research  
 Revesion History : 2008/03/25 楊夫志 (中訊) 新規作成  
 */
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3SuccOptionsCloseChangeCompleteRequestTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccOptionsCloseChangeCompleteRequestTest.class);

    WEB3SuccOptionsCloseChangeCompleteRequest succOptionsCloseChangeCompleteRequest = null;

    public WEB3SuccOptionsCloseChangeCompleteRequestTest(String arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succOptionsCloseChangeCompleteRequest = new WEB3SuccOptionsCloseChangeCompleteRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    // 2） 連続注文単価調整値情報チェック
    // 2－１） 連続注文単価調整値情報≠nullの場合、
    // 連続注文単価調整値情報.validate()をコールする。
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // 1）　@確認時概算受渡代金チェック
            succOptionsCloseChangeCompleteRequest.estimatedPrice = "1000";
            // 2） 連続注文単価調整値情報チェック
            // 2－１） 連続注文単価調整値情報≠nullの場合
            succOptionsCloseChangeCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            // 連続注文単価調整値情報.validate()をコールする。
            succOptionsCloseChangeCompleteRequest.priceAdjustmentValueInfo.sign = null;
            succOptionsCloseChangeCompleteRequest.validate();
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

    // 2－２） 連続注文単価調整値情報≠nullの場合、
    // 注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が不整合」の
    // 例外をthrowする。
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // 1）　@確認時概算受渡代金チェック
            succOptionsCloseChangeCompleteRequest.estimatedPrice = "1000";
            // 2） 連続注文単価調整値情報チェック
            // 2－１） 連続注文単価調整値情報≠nullの場合
            succOptionsCloseChangeCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            // 連続注文単価調整値情報.validate()をコールする。
            succOptionsCloseChangeCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsCloseChangeCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            // 2－２） 連続注文単価調整値情報≠nullの場合、
            // 注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が不整合」の
            // 例外をthrowする。
            succOptionsCloseChangeCompleteRequest.orderPriceDiv = "1";
            succOptionsCloseChangeCompleteRequest.validate();
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

    // correct case 連続注文単価調整値情報≠nullの場合
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // 1）　@確認時概算受渡代金チェック
            succOptionsCloseChangeCompleteRequest.estimatedPrice = "1000";
            // 2） 連続注文単価調整値情報チェック
            // 2－１） 連続注文単価調整値情報≠nullの場合
            succOptionsCloseChangeCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            // 連続注文単価調整値情報.validate()をコールする。
            succOptionsCloseChangeCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsCloseChangeCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            // 2－２） 連続注文単価調整値情報≠nullの場合、
            // 注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が不整合」の
            // 例外をthrowする。
            succOptionsCloseChangeCompleteRequest.orderPriceDiv = "0";
            succOptionsCloseChangeCompleteRequest.validate();
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

    // correct case 連続注文単価調整値情報==nullの場合
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // 1）　@確認時概算受渡代金チェック
            succOptionsCloseChangeCompleteRequest.estimatedPrice = "1000";
            // 2） 連続注文単価調整値情報チェック
            // 2－１） 連続注文単価調整値情報≠nullの場合
            succOptionsCloseChangeCompleteRequest.priceAdjustmentValueInfo = null;
            succOptionsCloseChangeCompleteRequest.validate();
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
            // １） スーパークラスのvalidateメソッドをコールする。
            succOptionsCloseChangeCompleteRequest.orderPriceDiv = null;
            succOptionsCloseChangeCompleteRequest.validateATExistingRemainderTrading();
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

    // ２）連続注文・注文条件チェック<BR>
    public void testValidateATExistingRemainderTrading_C0002()
    {
        final String STR_METHOD_NAME = "testValidateATExistingRemainderTrading_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // super.super.validate()
            succOptionsCloseChangeCompleteRequest.orderPriceDiv = "1";
            succOptionsCloseChangeCompleteRequest.limitPrice = "1000";
            succOptionsCloseChangeCompleteRequest.execCondType = "3";
            succOptionsCloseChangeCompleteRequest.expirationDateType = "1";
            succOptionsCloseChangeCompleteRequest.expirationDate = null;
            succOptionsCloseChangeCompleteRequest.orderCondType = "0";
            succOptionsCloseChangeCompleteRequest.stopOrderCondPrice = null;
            succOptionsCloseChangeCompleteRequest.stopOrderCondOperator = null;
            succOptionsCloseChangeCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsCloseChangeCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsCloseChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseChangeCompleteRequest.wLimitPrice = null;
            succOptionsCloseChangeCompleteRequest.wlimitExecCondType = null;
            succOptionsCloseChangeCompleteRequest.wlimitEnableStatusDiv = null;
            // super.validate()
            succOptionsCloseChangeCompleteRequest.id = "0001";
            succOptionsCloseChangeCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
            {new WEB3FuturesOptionsCloseMarginContractUnit(), new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseChangeCompleteRequest.opOrderQuantity = "1000";
            for (int i = 0; i < succOptionsCloseChangeCompleteRequest.closeMarginContractUnits.length; i++)
            succOptionsCloseChangeCompleteRequest.validateATExistingRemainderTrading();
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

    // correct case
    public void testValidateATExistingRemainderTrading_C0003()
    {
        final String STR_METHOD_NAME = "testValidateATExistingRemainderTrading_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // super.super.validate()
            succOptionsCloseChangeCompleteRequest.orderPriceDiv = "1";
            succOptionsCloseChangeCompleteRequest.limitPrice = "1000";
            succOptionsCloseChangeCompleteRequest.execCondType = "1";
            succOptionsCloseChangeCompleteRequest.expirationDateType = "1";
            succOptionsCloseChangeCompleteRequest.expirationDate = null;
            succOptionsCloseChangeCompleteRequest.orderCondType = "0";
            succOptionsCloseChangeCompleteRequest.stopOrderCondPrice = null;
            succOptionsCloseChangeCompleteRequest.stopOrderCondOperator = null;
            succOptionsCloseChangeCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsCloseChangeCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsCloseChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseChangeCompleteRequest.wLimitPrice = null;
            succOptionsCloseChangeCompleteRequest.wlimitExecCondType = null;
            succOptionsCloseChangeCompleteRequest.wlimitEnableStatusDiv = null;
            // super.validate()
            succOptionsCloseChangeCompleteRequest.id = "0001";
            succOptionsCloseChangeCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
            {new WEB3FuturesOptionsCloseMarginContractUnit(), new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseChangeCompleteRequest.opOrderQuantity = "1000";
            succOptionsCloseChangeCompleteRequest.validateATExistingRemainderTrading();
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
    //1）　@確認時概算受渡代金チェック
    // 　@this.確認時概算受渡代金の値が以下のいずれかに該当する場合は、
    // 　@例外をthrowする。
    // 　@　@・null
    // 　@　@・数字以外
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // 1）　@確認時概算受渡代金チェック
            succOptionsCloseChangeCompleteRequest.estimatedPrice = null;
            succOptionsCloseChangeCompleteRequest.validate();
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
}
@

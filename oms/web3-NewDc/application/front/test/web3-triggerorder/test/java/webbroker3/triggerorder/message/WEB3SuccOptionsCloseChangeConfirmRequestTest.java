head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.48.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccOptionsCloseChangeConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部 
File Name        : WEB3SuccOptionsCloseChangeConfirmRequestTest.java
Author Name      : Daiwa Institute of Research  
Revesion History : 2008/03/25 楊夫志 (中訊) 新規作成  
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3SuccOptionsCloseChangeConfirmRequestTest extends TestBaseForMock
{


    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccOptionsCloseChangeConfirmRequestTest.class);
    
    WEB3SuccOptionsCloseChangeConfirmRequest succOptionsCloseChangeConfirmRequest = null;
  
    public WEB3SuccOptionsCloseChangeConfirmRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succOptionsCloseChangeConfirmRequest = new WEB3SuccOptionsCloseChangeConfirmRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //１）　@連続注文単価調整値情報チェック
    // 　@１－１）　@連続注文単価調整値情報≠nullの場合、
    // 　@　@　@　@　@　@連続注文単価調整値情報.validate()をコールする。
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@連続注文単価調整値情報チェック
            // 　@１－１）　@連続注文単価調整値情報≠nullの場合
            succOptionsCloseChangeConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //連続注文単価調整値情報.validate()をコールする。
            succOptionsCloseChangeConfirmRequest.priceAdjustmentValueInfo.sign = null;
            succOptionsCloseChangeConfirmRequest.validate();
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
    //１－２）　@連続注文単価調整値情報≠nullの場合、
    //        注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が不整合」の
    //        例外をthrowする。
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@連続注文単価調整値情報チェック
            // 　@１－１）　@連続注文単価調整値情報≠nullの場合
            succOptionsCloseChangeConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //連続注文単価調整値情報.validate()をコールする。
            succOptionsCloseChangeConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsCloseChangeConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            //１－２）　@連続注文単価調整値情報≠nullの場合、
            //        注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が不整合」の
            //        例外をthrowする。
            succOptionsCloseChangeConfirmRequest.orderPriceDiv = "1";
            succOptionsCloseChangeConfirmRequest.validate();
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
    // correct case 　@連続注文単価調整値情報≠nullの場合
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@連続注文単価調整値情報チェック
            // 　@１－１）　@連続注文単価調整値情報≠nullの場合
            succOptionsCloseChangeConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //連続注文単価調整値情報.validate()をコールする。
            succOptionsCloseChangeConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsCloseChangeConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            //１－２）　@連続注文単価調整値情報≠nullの場合、
            //        注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が不整合」の
            //        例外をthrowする。
            succOptionsCloseChangeConfirmRequest.orderPriceDiv = "0";
            succOptionsCloseChangeConfirmRequest.validate();
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
    //correct case 　@連続注文単価調整値情報==nullの場合
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@連続注文単価調整値情報チェック
            // 　@１－１）　@連続注文単価調整値情報≠nullの場合
            succOptionsCloseChangeConfirmRequest.priceAdjustmentValueInfo = null;
            succOptionsCloseChangeConfirmRequest.validate();
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
    //１）　@スーパークラスのvalidateメソッドをコールする。
    public void testValidateATExistingRemainderTrading_C0001()
    {
        final String STR_METHOD_NAME = "testValidateATExistingRemainderTrading_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@スーパークラスのvalidateメソッドをコールする。
            succOptionsCloseChangeConfirmRequest.orderPriceDiv = null;
            succOptionsCloseChangeConfirmRequest.validateATExistingRemainderTrading();
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
    //２）連続注文・注文条件チェック<BR>
    public void testValidateATExistingRemainderTrading_C0002()
    {
        final String STR_METHOD_NAME = "testValidateATExistingRemainderTrading_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // super.super.validate()
            succOptionsCloseChangeConfirmRequest.orderPriceDiv = "1";
            succOptionsCloseChangeConfirmRequest.limitPrice = "1000";
            succOptionsCloseChangeConfirmRequest.execCondType = "3";
            succOptionsCloseChangeConfirmRequest.expirationDateType = "1";
            succOptionsCloseChangeConfirmRequest.expirationDate = null;
            succOptionsCloseChangeConfirmRequest.orderCondType = "0";
            succOptionsCloseChangeConfirmRequest.stopOrderCondPrice = null;
            succOptionsCloseChangeConfirmRequest.stopOrderCondOperator = null;
            succOptionsCloseChangeConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsCloseChangeConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsCloseChangeConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseChangeConfirmRequest.wLimitPrice = null;
            succOptionsCloseChangeConfirmRequest.wlimitExecCondType = null;
            succOptionsCloseChangeConfirmRequest.wlimitEnableStatusDiv = null;
            // super.validate()
            succOptionsCloseChangeConfirmRequest.id = "0001";
            succOptionsCloseChangeConfirmRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
            {new WEB3FuturesOptionsCloseMarginContractUnit(), new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseChangeConfirmRequest.opOrderQuantity = "1000";
            for (int i = 0; i < succOptionsCloseChangeConfirmRequest.closeMarginContractUnits.length; i++)
            succOptionsCloseChangeConfirmRequest.validateATExistingRemainderTrading();
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
            succOptionsCloseChangeConfirmRequest.orderPriceDiv = "1";
            succOptionsCloseChangeConfirmRequest.limitPrice = "1000";
            succOptionsCloseChangeConfirmRequest.execCondType = "1";
            succOptionsCloseChangeConfirmRequest.expirationDateType = "1";
            succOptionsCloseChangeConfirmRequest.expirationDate = null;
            succOptionsCloseChangeConfirmRequest.orderCondType = "0";
            succOptionsCloseChangeConfirmRequest.stopOrderCondPrice = null;
            succOptionsCloseChangeConfirmRequest.stopOrderCondOperator = null;
            succOptionsCloseChangeConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsCloseChangeConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsCloseChangeConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseChangeConfirmRequest.wLimitPrice = null;
            succOptionsCloseChangeConfirmRequest.wlimitExecCondType = null;
            succOptionsCloseChangeConfirmRequest.wlimitEnableStatusDiv = null;
            // super.validate()
            succOptionsCloseChangeConfirmRequest.id = "0001";
            succOptionsCloseChangeConfirmRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
            {new WEB3FuturesOptionsCloseMarginContractUnit(), new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseChangeConfirmRequest.opOrderQuantity = "1000";
            succOptionsCloseChangeConfirmRequest.validateATExistingRemainderTrading();
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

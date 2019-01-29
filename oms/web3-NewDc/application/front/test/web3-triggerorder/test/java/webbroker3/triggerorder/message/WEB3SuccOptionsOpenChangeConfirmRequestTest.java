head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.48.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccOptionsOpenChangeConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部 
File Name        : WEB3SuccOptionsOpenChangeConfirmRequestTest.java
Author Name      : Daiwa Institute of Research  
Revesion History : 2008/03/25 楊夫志 (中訊) 新規作成  
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3SuccOptionsOpenChangeConfirmRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccOptionsOpenChangeConfirmRequestTest.class);

    /**
     *<BR>
     */
    private WEB3SuccOptionsOpenChangeConfirmRequest succOptionsOpenChangeConfirmRequest = null;
    public WEB3SuccOptionsOpenChangeConfirmRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succOptionsOpenChangeConfirmRequest = new WEB3SuccOptionsOpenChangeConfirmRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //２）　@連続注文単価調整値情報チェック
    // 　@２－１）　@連続注文単価調整値情報≠nullの場合、
    // 　@　@　@　@　@　@連続注文単価調整値情報.validate()をコールする。
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succOptionsOpenChangeConfirmRequest.orderPriceDiv = "0";
            succOptionsOpenChangeConfirmRequest.limitPrice = null;
            succOptionsOpenChangeConfirmRequest.execCondType = "1";
            succOptionsOpenChangeConfirmRequest.expirationDateType = "1";
            succOptionsOpenChangeConfirmRequest.expirationDate = null;
            succOptionsOpenChangeConfirmRequest.orderCondType = "0";
            succOptionsOpenChangeConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeConfirmRequest.wLimitPrice = null;
            succOptionsOpenChangeConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenChangeConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeConfirmRequest.id = "0001";
            succOptionsOpenChangeConfirmRequest.opOrderQuantity = "1000";
            //２）　@連続注文単価調整値情報チェック
            // 　@２－１）　@連続注文単価調整値情報≠nullの場合、
            // 　@　@　@　@　@　@連続注文単価調整値情報.validate()をコールする。
            succOptionsOpenChangeConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //連続注文単価調整値情報.validate()をコールする。
            succOptionsOpenChangeConfirmRequest.priceAdjustmentValueInfo.sign = null;
            succOptionsOpenChangeConfirmRequest.validate();
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
    // ２－２）　@連続注文単価調整値情報≠nullの場合、
    // 　@　@　@　@　@　@注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が不整合」の
    // 　@　@　@　@　@　@例外をthrowする。
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succOptionsOpenChangeConfirmRequest.orderPriceDiv = "1";
            succOptionsOpenChangeConfirmRequest.limitPrice = "1000";
            succOptionsOpenChangeConfirmRequest.execCondType = "1";
            succOptionsOpenChangeConfirmRequest.expirationDateType = "1";
            succOptionsOpenChangeConfirmRequest.expirationDate = null;
            succOptionsOpenChangeConfirmRequest.orderCondType = "0";
            succOptionsOpenChangeConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeConfirmRequest.wLimitPrice = null;
            succOptionsOpenChangeConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenChangeConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeConfirmRequest.id = "0001";
            succOptionsOpenChangeConfirmRequest.opOrderQuantity = "1000";
            //２）　@連続注文単価調整値情報チェック
            // 　@２－１）　@連続注文単価調整値情報≠nullの場合、
            // 　@　@　@　@　@　@連続注文単価調整値情報.validate()をコールする。
            succOptionsOpenChangeConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //連続注文単価調整値情報.validate()をコールする。
            succOptionsOpenChangeConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsOpenChangeConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsOpenChangeConfirmRequest.validate();
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
    //３）　@連続注文・注文条件チェック
    // 　@super.validate連続注文()をコールする。。
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succOptionsOpenChangeConfirmRequest.orderPriceDiv = "0";
            succOptionsOpenChangeConfirmRequest.limitPrice = null;
            succOptionsOpenChangeConfirmRequest.execCondType = "3";
            succOptionsOpenChangeConfirmRequest.expirationDateType = "1";
            succOptionsOpenChangeConfirmRequest.expirationDate = null;
            succOptionsOpenChangeConfirmRequest.orderCondType = "0";
            succOptionsOpenChangeConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeConfirmRequest.wLimitPrice = null;
            succOptionsOpenChangeConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenChangeConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeConfirmRequest.id = "0001";
            succOptionsOpenChangeConfirmRequest.opOrderQuantity = "1000";
            //連続注文単価調整値情報==nullの場合
            succOptionsOpenChangeConfirmRequest.priceAdjustmentValueInfo = null;
            succOptionsOpenChangeConfirmRequest.validate();
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
    //correct case  連続注文単価調整値情報==nullの場合
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succOptionsOpenChangeConfirmRequest.orderPriceDiv = "0";
            succOptionsOpenChangeConfirmRequest.limitPrice = null;
            succOptionsOpenChangeConfirmRequest.execCondType = "1";
            succOptionsOpenChangeConfirmRequest.expirationDateType = "1";
            succOptionsOpenChangeConfirmRequest.expirationDate = null;
            succOptionsOpenChangeConfirmRequest.orderCondType = "0";
            succOptionsOpenChangeConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeConfirmRequest.wLimitPrice = null;
            succOptionsOpenChangeConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenChangeConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeConfirmRequest.id = "0001";
            succOptionsOpenChangeConfirmRequest.opOrderQuantity = "1000";
            //連続注文単価調整値情報==nullの場合
            succOptionsOpenChangeConfirmRequest.priceAdjustmentValueInfo = null;
            succOptionsOpenChangeConfirmRequest.validate();
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
    //correct case  連続注文単価調整値情報≠nullの場合
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succOptionsOpenChangeConfirmRequest.orderPriceDiv = "0";
            succOptionsOpenChangeConfirmRequest.limitPrice = null;
            succOptionsOpenChangeConfirmRequest.execCondType = "1";
            succOptionsOpenChangeConfirmRequest.expirationDateType = "1";
            succOptionsOpenChangeConfirmRequest.expirationDate = null;
            succOptionsOpenChangeConfirmRequest.orderCondType = "0";
            succOptionsOpenChangeConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeConfirmRequest.wLimitPrice = null;
            succOptionsOpenChangeConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenChangeConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeConfirmRequest.id = "0001";
            succOptionsOpenChangeConfirmRequest.opOrderQuantity = "1000";
            //連続注文単価調整値情報≠nullの場合
            succOptionsOpenChangeConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succOptionsOpenChangeConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsOpenChangeConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsOpenChangeConfirmRequest.validate();
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
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //１）　@super.super.validate()
            succOptionsOpenChangeConfirmRequest.orderPriceDiv = null;
            succOptionsOpenChangeConfirmRequest.validate();
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

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.23.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesCloseMarginConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3FuturesCloseMarginConfirmRequestTest.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2008/03/28 楊夫志 (中訊) 新規作成
 */
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesCloseMarginConfirmRequestTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesCloseMarginConfirmRequestTest.class);

    private WEB3FuturesCloseMarginConfirmRequest futuresCloseMarginConfirmRequest = null;

    public WEB3FuturesCloseMarginConfirmRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.futuresCloseMarginConfirmRequest = new WEB3FuturesCloseMarginConfirmRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //１）　@スーパークラスのvalidate チェック
    public void testValidateAtReverseOrder_C0001()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            futuresCloseMarginConfirmRequest.orderPriceDiv = null;
            futuresCloseMarginConfirmRequest.validateAtReverseOrder();
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
    //２）　@返済建玉チェック
    //２－１）this.返済建玉=null の場合、
    //「返済建玉が未指定です。」の例外をスローする。
    public void testValidateAtReverseOrder_C0002()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginConfirmRequest.limitPrice = "1000";
            futuresCloseMarginConfirmRequest.execCondType = "1";
            futuresCloseMarginConfirmRequest.expirationDateType = "1";
            futuresCloseMarginConfirmRequest.orderCondType = "0";
            //２－１）this.返済建玉=null
            futuresCloseMarginConfirmRequest.closeMarginContractUnits = null;
            futuresCloseMarginConfirmRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00178, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //２－２）this.返済建玉の要素数=0 の場合、
    // 「返済建玉が未指定です。」の例外をスローする。
    public void testValidateAtReverseOrder_C0003()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginConfirmRequest.limitPrice = "1000";
            futuresCloseMarginConfirmRequest.execCondType = "1";
            futuresCloseMarginConfirmRequest.expirationDateType = "1";
            futuresCloseMarginConfirmRequest.orderCondType = "0";
            //２－２）this.返済建玉の要素数=0
            futuresCloseMarginConfirmRequest.closeMarginContractUnits = new
                WEB3FuturesOptionsCloseMarginContractUnit[]{};
            futuresCloseMarginConfirmRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00178, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //３）　@決済順序チェック
    //this.決済順序≠null and this.決済順序≠（以下の値） の場合、
    //「決済順序の値が存在しないコード値です。」の例外をスローする。
    //　@　@　@　@・”0：ランダム”
    //　@　@　@　@・”1：単価益順”
    //　@　@　@　@・”2：単価損順”
    //　@　@　@　@・”3：建日順”
    public void testValidateAtReverseOrder_C0004()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginConfirmRequest.limitPrice = "1000";
            futuresCloseMarginConfirmRequest.execCondType = "1";
            futuresCloseMarginConfirmRequest.expirationDateType = "1";
            futuresCloseMarginConfirmRequest.orderCondType = "0";
            //this.返済建玉!=null and this.返済建玉の要素数!=0
            futuresCloseMarginConfirmRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.決済順序="4"
            futuresCloseMarginConfirmRequest.closingOrder = "4";
            futuresCloseMarginConfirmRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00179, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //４）　@注文数量チェック
    //　@４－１）this.決済順序=（null or ”1：単価益順” or ”2：単価損順” or ”3：建日順”） and
    //          this.注文数量=null の場合、
    //          「決済順序がランダム指定以外の場合、数量は必須入力項目です。」の例外をスローする。
    public void testValidateAtReverseOrder_C0005()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginConfirmRequest.limitPrice = "1000";
            futuresCloseMarginConfirmRequest.execCondType = "1";
            futuresCloseMarginConfirmRequest.expirationDateType = "1";
            futuresCloseMarginConfirmRequest.orderCondType = "0";
            //this.返済建玉!=null and this.返済建玉の要素数!=0
            futuresCloseMarginConfirmRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.決済順序="1"
            futuresCloseMarginConfirmRequest.closingOrder = "1";
            futuresCloseMarginConfirmRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00245, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //４－２）this.注文数量≠null and this.注文数量≠数字 の場合、
    //          「注文数量が数字以外の値です。」の例外をスローする。
    public void testValidateAtReverseOrder_C0006()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginConfirmRequest.limitPrice = "1000";
            futuresCloseMarginConfirmRequest.execCondType = "1";
            futuresCloseMarginConfirmRequest.expirationDateType = "1";
            futuresCloseMarginConfirmRequest.orderCondType = "0";
            //this.返済建玉!=null and this.返済建玉の要素数!=0
            futuresCloseMarginConfirmRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.決済順序="1"
            futuresCloseMarginConfirmRequest.closingOrder = "1";
            futuresCloseMarginConfirmRequest.futOrderQuantity = "aaa";
            futuresCloseMarginConfirmRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00075, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //４－３）this.注文数量≠null and this.注文数量≦0 の場合、
    //          「注文数量が0以下の値です。」の例外をスローする。
    public void testValidateAtReverseOrder_C0007()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginConfirmRequest.limitPrice = "1000";
            futuresCloseMarginConfirmRequest.execCondType = "1";
            futuresCloseMarginConfirmRequest.expirationDateType = "1";
            futuresCloseMarginConfirmRequest.orderCondType = "0";
            //this.返済建玉!=null and this.返済建玉の要素数!=0
            futuresCloseMarginConfirmRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.決済順序="1"
            futuresCloseMarginConfirmRequest.closingOrder = "1";
            futuresCloseMarginConfirmRequest.futOrderQuantity = "0";
            futuresCloseMarginConfirmRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00076, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //５）　@返済建玉の注文数量チェック
    //　@５－１）決済順序＝”0：ランダム”の場合のみ、
    //　@　@　@　@返済建玉の要素数分
    //　@　@　@　@下記のチェックを繰り返して行う。
    //           ・null
    //　@　@　@　@　@　@・数字以外
    //　@　@　@　@　@　@・０以下の数字
    //　@　@　@　@　@　@・８桁を超える数字
    public void testValidateAtReverseOrder_C0008()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginConfirmRequest.limitPrice = "1000";
            futuresCloseMarginConfirmRequest.execCondType = "1";
            futuresCloseMarginConfirmRequest.expirationDateType = "1";
            futuresCloseMarginConfirmRequest.orderCondType = "0";
            //this.返済建玉!=null and this.返済建玉の要素数!=0
            futuresCloseMarginConfirmRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.決済順序="1"
            futuresCloseMarginConfirmRequest.closingOrder = "0";
            futuresCloseMarginConfirmRequest.futOrderQuantity = "1000";
            futuresCloseMarginConfirmRequest.closeMarginContractUnits[0].contractOrderQuantity = null;
            futuresCloseMarginConfirmRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03060, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //normal case
    public void testValidateAtReverseOrder_C0009()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginConfirmRequest.limitPrice = "1000";
            futuresCloseMarginConfirmRequest.execCondType = "1";
            futuresCloseMarginConfirmRequest.expirationDateType = "1";
            futuresCloseMarginConfirmRequest.orderCondType = "0";
            //this.返済建玉!=null and this.返済建玉の要素数!=0
            futuresCloseMarginConfirmRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.決済順序="1"
            futuresCloseMarginConfirmRequest.closingOrder = "1";
            futuresCloseMarginConfirmRequest.futOrderQuantity = "1000";
            futuresCloseMarginConfirmRequest.validateAtReverseOrder();
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

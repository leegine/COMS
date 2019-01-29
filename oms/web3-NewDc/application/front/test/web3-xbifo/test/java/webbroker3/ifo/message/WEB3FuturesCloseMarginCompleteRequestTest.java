head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.24.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesCloseMarginCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部 
File Name        : WEB3FuturesCloseMarginCompleteRequestTest.java
Author Name      : Daiwa Institute of Research  
Revesion History : 2008/03/31 楊夫志 (中訊) 新規作成  
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesCloseMarginCompleteRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesCloseMarginCompleteRequestTest.class);

    private WEB3FuturesCloseMarginCompleteRequest futuresCloseMarginCompleteRequest = null;

    public WEB3FuturesCloseMarginCompleteRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.futuresCloseMarginCompleteRequest = new WEB3FuturesCloseMarginCompleteRequest();
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
            futuresCloseMarginCompleteRequest.orderPriceDiv = null;
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
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

    //２）　@注文IDチェック
    //　@２－１）this.注文ID＝nullの場合、
    //　@　@　@　@　@「注文IDがnull」の例外をスローする。
    public void testValidateAtReverseOrder_C0002()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.注文ID＝null
            futuresCloseMarginCompleteRequest.orderId = null;
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //３）　@返済建玉チェック
    //  ３－１）this.返済建玉=null の場合、
    //          「返済建玉が未指定です。」の例外をスローする。
    public void testValidateAtReverseOrder_C0003()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.注文ID!＝null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //２－１）this.返済建玉=null
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = null;
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
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

    // ３－２）this.返済建玉の要素数=0 の場合、
    //          「返済建玉が未指定です。」の例外をスローする。
    public void testValidateAtReverseOrder_C0004()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.注文ID!＝null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //２－２）this.返済建玉の要素数=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new
                WEB3FuturesOptionsCloseMarginContractUnit[]{};
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
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

    //４）　@決済順序チェック
    //　@this.決済順序≠null and
    //　@this.決済順序≠（以下の値） の場合、「決済順序の値が存在しないコード値です。」の例外をスローする。
    //　@　@　@　@・”0：ランダム”
    //　@　@　@　@・”1：単価益順”
    //　@　@　@　@・”2：単価損順”
    //　@　@　@　@・”3：建日順”
    public void testValidateAtReverseOrder_C0005()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.注文ID!＝null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.返済建玉!=null and this.返済建玉の要素数!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.決済順序="4"
            futuresCloseMarginCompleteRequest.closingOrder = "4";
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
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

    //５）　@注文数量チェック
    //　@５－１）this.決済順序=（null or ”1：単価益順” or ”2：単価損順” or ”3：建日順”） and
    //          this.注文数量=null の場合、
    //          「決済順序がランダム指定以外の場合、数量は必須入力項目です。」の例外をスローする。
    public void testValidateAtReverseOrder_C0006()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.注文ID!＝null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.返済建玉!=null and this.返済建玉の要素数!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.決済順序="1"
            futuresCloseMarginCompleteRequest.closingOrder = "1";
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
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

    //５－２）this.注文数量≠null and this.注文数量≠数字 の場合、
    //          「注文数量が数字以外の値です。」の例外をスローする。
    public void testValidateAtReverseOrder_C0007()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.注文ID!＝null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.返済建玉!=null and this.返済建玉の要素数!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.決済順序="1"
            futuresCloseMarginCompleteRequest.closingOrder = "1";
            futuresCloseMarginCompleteRequest.futOrderQuantity = "aaa";
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
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

    //５－３）this.注文数量≠null and this.注文数量≦0 の場合、
    //          「注文数量が0以下の値です。」の例外をスローする。
    public void testValidateAtReverseOrder_C0008()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.注文ID!＝null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.返済建玉!=null and this.返済建玉の要素数!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.決済順序="1"
            futuresCloseMarginCompleteRequest.closingOrder = "1";
            futuresCloseMarginCompleteRequest.futOrderQuantity = "0";
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
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

    //６）　@返済建玉の注文数量チェック
    //　@６－１）決済順序＝”0：ランダム”の場合のみ、
    //　@　@　@　@返済建玉の要素数分
    //　@　@　@　@下記のチェックを繰り返して行う。
    //           ・null
    public void testValidateAtReverseOrder_C0009()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.注文ID!＝null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.返済建玉!=null and this.返済建玉の要素数!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.決済順序="1"
            futuresCloseMarginCompleteRequest.closingOrder = "0";
            futuresCloseMarginCompleteRequest.futOrderQuantity = "1000";
            futuresCloseMarginCompleteRequest.closeMarginContractUnits[0].contractOrderQuantity = null;
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
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

    //７）　@確認時単価チェック
    //　@this.確認時単価＝nullであった場合、「確認時単価がnull」の
    //　@例外をスローする。
    public void testValidateAtReverseOrder_C0010()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.注文ID!＝null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.返済建玉!=null and this.返済建玉の要素数!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.決済順序="1"
            futuresCloseMarginCompleteRequest.closingOrder = "1";
            futuresCloseMarginCompleteRequest.futOrderQuantity = "1000";
            //this.確認時単価＝null
            futuresCloseMarginCompleteRequest.checkPrice = null;
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00206, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //８）　@確認時発注日チェック
    //　@this.確認時発注日＝nullであった場合、「確認時発注日がnull」の
    //　@例外をスローする。
    public void testValidateAtReverseOrder_C0011()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.注文ID!＝null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.返済建玉!=null and this.返済建玉の要素数!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.決済順序="1"
            futuresCloseMarginCompleteRequest.closingOrder = "1";
            futuresCloseMarginCompleteRequest.futOrderQuantity = "1000";
            //this.確認時単価!＝null
            futuresCloseMarginCompleteRequest.checkPrice = "1000";
            //this.確認時発注日＝null
            futuresCloseMarginCompleteRequest.checkDate = null;
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00078, l_ex.getErrorInfo());
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
    public void testValidateAtReverseOrder_C0012()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.注文ID!＝null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.返済建玉!=null and this.返済建玉の要素数!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.決済順序="1"
            futuresCloseMarginCompleteRequest.closingOrder = "1";
            futuresCloseMarginCompleteRequest.futOrderQuantity = "1000";
            //this.確認時単価!＝null
            futuresCloseMarginCompleteRequest.checkPrice = "1000";
            //this.確認時発注日!＝null
            futuresCloseMarginCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd");
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
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
    //６）　@返済建玉の注文数量チェック
    //　@６－１）決済順序＝”0：ランダム”の場合のみ、
    //　@　@　@　@返済建玉の要素数分
    //　@　@　@　@下記のチェックを繰り返して行う。
    //　@　@　@　@　@　@・数字以外
    public void testValidateAtReverseOrder_C0013()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.注文ID!＝null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.返済建玉!=null and this.返済建玉の要素数!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.決済順序="1"
            futuresCloseMarginCompleteRequest.closingOrder = "0";
            futuresCloseMarginCompleteRequest.futOrderQuantity = "1000";
            futuresCloseMarginCompleteRequest.closeMarginContractUnits[0].contractOrderQuantity = "aaa";
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
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
    //６）　@返済建玉の注文数量チェック
    //　@６－１）決済順序＝”0：ランダム”の場合のみ、
    //　@　@　@　@返済建玉の要素数分
    //　@　@　@　@下記のチェックを繰り返して行う。
    //　@　@　@　@　@　@・０以下の数字
    public void testValidateAtReverseOrder_C0014()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0014()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.注文ID!＝null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.返済建玉!=null and this.返済建玉の要素数!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.決済順序="1"
            futuresCloseMarginCompleteRequest.closingOrder = "0";
            futuresCloseMarginCompleteRequest.futOrderQuantity = "1000";
            futuresCloseMarginCompleteRequest.closeMarginContractUnits[0].contractOrderQuantity = "-1000";
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
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
    //６）　@返済建玉の注文数量チェック
    //　@６－１）決済順序＝”0：ランダム”の場合のみ、
    //　@　@　@　@返済建玉の要素数分
    //　@　@　@　@下記のチェックを繰り返して行う。
    //　@　@　@　@　@　@・８桁を超える数字
    public void testValidateAtReverseOrder_C0015()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0015()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.注文ID!＝null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.返済建玉!=null and this.返済建玉の要素数!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.決済順序="1"
            futuresCloseMarginCompleteRequest.closingOrder = "0";
            futuresCloseMarginCompleteRequest.futOrderQuantity = "1000";
            futuresCloseMarginCompleteRequest.closeMarginContractUnits[0].contractOrderQuantity = "111111111";
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
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
    //６）　@返済建玉の注文数量チェック
    //　@６－１）決済順序＝”0：ランダム”の場合のみ、
    //this.closeMarginContractUnits.length=1
    public void testValidateAtReverseOrder_C0016()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0016()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.注文ID!＝null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.返済建玉!=null and this.返済建玉の要素数!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.決済順序="1"
            futuresCloseMarginCompleteRequest.closingOrder = "0";
            futuresCloseMarginCompleteRequest.futOrderQuantity = "1000";
            futuresCloseMarginCompleteRequest.closeMarginContractUnits[0].contractOrderQuantity = "1000";
            //this.確認時単価!＝null
            futuresCloseMarginCompleteRequest.checkPrice = "1000";
            //this.確認時発注日!＝null
            futuresCloseMarginCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd");
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
            assertEquals("1000",futuresCloseMarginCompleteRequest.closeMarginContractUnits[0].contractOrderQuantity);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //６）　@返済建玉の注文数量チェック
    //　@６－１）決済順序＝”0：ランダム”の場合のみ、
    //this.closeMarginContractUnits.length=3
    public void testValidateAtReverseOrder_C0017()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0017()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.注文ID!＝null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.返済建玉!=null and this.返済建玉の要素数!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.決済順序="1"
            futuresCloseMarginCompleteRequest.closingOrder = "0";
            futuresCloseMarginCompleteRequest.futOrderQuantity = "1000";
            futuresCloseMarginCompleteRequest.closeMarginContractUnits[0].contractOrderQuantity = "1000";
            futuresCloseMarginCompleteRequest.closeMarginContractUnits[1].contractOrderQuantity = "1000";
            futuresCloseMarginCompleteRequest.closeMarginContractUnits[1].contractOrderQuantity = "-1000";
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
            assertEquals("1000",futuresCloseMarginCompleteRequest.closeMarginContractUnits[0].contractOrderQuantity);
            assertEquals("1000",futuresCloseMarginCompleteRequest.closeMarginContractUnits[1].contractOrderQuantity);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03060, l_ex.getErrorInfo());
            log.error(STR_METHOD_NAME,l_ex);
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

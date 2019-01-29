head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.24.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsCloseMarginChangeCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション訂正返済完了リクエストTest(WEB3OptionsCloseMarginChangeCompleteRequestTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/03/25 張騰宇 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionsCloseMarginChangeCompleteRequestTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionsCloseMarginChangeCompleteRequestTest.class);
    
    WEB3OptionsCloseMarginChangeCompleteRequest l_request = new WEB3OptionsCloseMarginChangeCompleteRequest();

    public WEB3OptionsCloseMarginChangeCompleteRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.ifo.message.WEB3OptionsCloseMarginChangeCompleteRequest.validateATReserveOrder()'
     */
    //連続注文対応
    //validateATReserveOrder
    //スーパークラスのvalidateメソッドを呼び出す
    public void testValidateATReserveOrderCase1()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00184);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    //２）　@ＩＤチェック 
    //　@this.ＩＤ=null の場合、例外をスローする
    public void testValidateATReserveOrderCase2()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            //注文単価区分
            l_request.orderPriceDiv = "1";

            //注文単価
            l_request.limitPrice = "111";

            //執行条件
            l_request.execCondType = "1";

            //注文期限区分
            l_request.expirationDateType ="3";

            //注文有効期限
            l_request.expirationDate = null;

            //発注条件区分
            l_request.orderCondType = "2";

            //逆指値用プレミアム／原資産価格
            l_request.stopPremium_underlyingAssets = null;

            //逆指値用発注条件単価
            l_request.stopOrderCondPrice = null;

            //逆指値用発注条件演算子
            l_request.stopOrderCondOperator = null;

            //Ｗ指値用プレミアム／原資産価格
            l_request.wlimitPremium_underlyingAssets = "1";

            //Ｗ指値用発注条件単価
            l_request.wlimitOrderCondPrice = "2";

            //Ｗ指値用発注条件演算子
            l_request.wlimitOrderCondOperator = "1";

            //Ｗ指値用注文単価区分
            l_request.wLimitOrderPriceDiv = "1";

            //Ｗ指値用注文単価
            l_request.wLimitPrice = "1";
            
            //W指値用執行条件
            l_request.wlimitExecCondType = "1";
            
            //W指値用有効状態区分
            l_request.wlimitEnableStatusDiv = "1";
            
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00600);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }

    //３）　@返済建玉チェック 
    //３－１）this.返済建玉=null の場合、 
    //例外をスローする。
    public void testValidateATReserveOrderCase3()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            //注文単価区分
            l_request.orderPriceDiv = "1";

            //注文単価
            l_request.limitPrice = "111";

            //執行条件
            l_request.execCondType = "1";

            //注文期限区分
            l_request.expirationDateType ="3";

            //注文有効期限
            l_request.expirationDate = null;

            //発注条件区分
            l_request.orderCondType = "2";

            //逆指値用プレミアム／原資産価格
            l_request.stopPremium_underlyingAssets = null;

            //逆指値用発注条件単価
            l_request.stopOrderCondPrice = null;

            //逆指値用発注条件演算子
            l_request.stopOrderCondOperator = null;

            //Ｗ指値用プレミアム／原資産価格
            l_request.wlimitPremium_underlyingAssets = "1";

            //Ｗ指値用発注条件単価
            l_request.wlimitOrderCondPrice = "2";

            //Ｗ指値用発注条件演算子
            l_request.wlimitOrderCondOperator = "1";

            //Ｗ指値用注文単価区分
            l_request.wLimitOrderPriceDiv = "1";

            //Ｗ指値用注文単価
            l_request.wLimitPrice = "1";
            
            //W指値用執行条件
            l_request.wlimitExecCondType = "1";
            
            //W指値用有効状態区分
            l_request.wlimitEnableStatusDiv = "1";
            
            l_request.id = "1101";
            
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00178);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    //３－２）this.返済建玉の要素数=0 の場合、 
    //例外をスローする
    public void testValidateATReserveOrderCase4()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            //注文単価区分
            l_request.orderPriceDiv = "1";

            //注文単価
            l_request.limitPrice = "111";

            //執行条件
            l_request.execCondType = "1";

            //注文期限区分
            l_request.expirationDateType ="3";

            //注文有効期限
            l_request.expirationDate = null;

            //発注条件区分
            l_request.orderCondType = "2";

            //逆指値用プレミアム／原資産価格
            l_request.stopPremium_underlyingAssets = null;

            //逆指値用発注条件単価
            l_request.stopOrderCondPrice = null;

            //逆指値用発注条件演算子
            l_request.stopOrderCondOperator = null;

            //Ｗ指値用プレミアム／原資産価格
            l_request.wlimitPremium_underlyingAssets = "1";

            //Ｗ指値用発注条件単価
            l_request.wlimitOrderCondPrice = "2";

            //Ｗ指値用発注条件演算子
            l_request.wlimitOrderCondOperator = "1";

            //Ｗ指値用注文単価区分
            l_request.wLimitOrderPriceDiv = "1";

            //Ｗ指値用注文単価
            l_request.wLimitPrice = "1";
            
            //W指値用執行条件
            l_request.wlimitExecCondType = "1";
            
            //W指値用有効状態区分
            l_request.wlimitEnableStatusDiv = "1";
            
            l_request.id = "1101";
            
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[0];
            
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00178);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    //４）　@確認時単価チェック 
    //　@this.確認時単価＝nullの場合、 
    //　@「確認時単価がnull」の例外をスローする
    public void testValidateATReserveOrderCase5()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case5()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            //注文単価区分
            l_request.orderPriceDiv = "1";

            //注文単価
            l_request.limitPrice = "111";

            //執行条件
            l_request.execCondType = "1";

            //注文期限区分
            l_request.expirationDateType ="3";

            //注文有効期限
            l_request.expirationDate = null;

            //発注条件区分
            l_request.orderCondType = "2";

            //逆指値用プレミアム／原資産価格
            l_request.stopPremium_underlyingAssets = null;

            //逆指値用発注条件単価
            l_request.stopOrderCondPrice = null;

            //逆指値用発注条件演算子
            l_request.stopOrderCondOperator = null;

            //Ｗ指値用プレミアム／原資産価格
            l_request.wlimitPremium_underlyingAssets = "1";

            //Ｗ指値用発注条件単価
            l_request.wlimitOrderCondPrice = "2";

            //Ｗ指値用発注条件演算子
            l_request.wlimitOrderCondOperator = "1";

            //Ｗ指値用注文単価区分
            l_request.wLimitOrderPriceDiv = "1";

            //Ｗ指値用注文単価
            l_request.wLimitPrice = "1";
            
            //W指値用執行条件
            l_request.wlimitExecCondType = "1";
            
            //W指値用有効状態区分
            l_request.wlimitEnableStatusDiv = "1";
            
            l_request.id = "1101";
            
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[2];
            WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_request.closeMarginContractUnits[0] = l_unit1;
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00206);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    //５）　@確認時発注日チェック 
    //　@this.確認時発注日＝nullの場合、 
    //　@「確認時発注日がnull」の例外をスローする
    public void testValidateATReserveOrderCase6()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case6()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            //注文単価区分
            l_request.orderPriceDiv = "1";

            //注文単価
            l_request.limitPrice = "111";

            //執行条件
            l_request.execCondType = "1";

            //注文期限区分
            l_request.expirationDateType ="3";

            //注文有効期限
            l_request.expirationDate = null;

            //発注条件区分
            l_request.orderCondType = "2";

            //逆指値用プレミアム／原資産価格
            l_request.stopPremium_underlyingAssets = null;

            //逆指値用発注条件単価
            l_request.stopOrderCondPrice = null;

            //逆指値用発注条件演算子
            l_request.stopOrderCondOperator = null;

            //Ｗ指値用プレミアム／原資産価格
            l_request.wlimitPremium_underlyingAssets = "1";

            //Ｗ指値用発注条件単価
            l_request.wlimitOrderCondPrice = "2";

            //Ｗ指値用発注条件演算子
            l_request.wlimitOrderCondOperator = "1";

            //Ｗ指値用注文単価区分
            l_request.wLimitOrderPriceDiv = "1";

            //Ｗ指値用注文単価
            l_request.wLimitPrice = "1";
            
            //W指値用執行条件
            l_request.wlimitExecCondType = "1";
            
            //W指値用有効状態区分
            l_request.wlimitEnableStatusDiv = "1";
            
            l_request.id = "1101";
            
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[2];
            WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_request.closeMarginContractUnits[0] = l_unit1;
            
            l_request.checkPrice = "10";
            
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00078);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    //６）　@連続注文・注文条件チェック 
    //　@スーパークラスのvalidate連続注文メソッドをコールする
    public void testValidateATReserveOrderCase7()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case7()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            //注文単価区分
            l_request.orderPriceDiv = "1";

            //注文単価
            l_request.limitPrice = "111";

            //執行条件
            l_request.execCondType = "1";

            //注文期限区分
            l_request.expirationDateType ="3";

            //注文有効期限
            l_request.expirationDate = null;

            //発注条件区分
            l_request.orderCondType = "2";

            //逆指値用プレミアム／原資産価格
            l_request.stopPremium_underlyingAssets = null;

            //逆指値用発注条件単価
            l_request.stopOrderCondPrice = null;

            //逆指値用発注条件演算子
            l_request.stopOrderCondOperator = null;

            //Ｗ指値用プレミアム／原資産価格
            l_request.wlimitPremium_underlyingAssets = "1";

            //Ｗ指値用発注条件単価
            l_request.wlimitOrderCondPrice = "2";

            //Ｗ指値用発注条件演算子
            l_request.wlimitOrderCondOperator = "1";

            //Ｗ指値用注文単価区分
            l_request.wLimitOrderPriceDiv = "1";

            //Ｗ指値用注文単価
            l_request.wLimitPrice = "1";
            
            //W指値用執行条件
            l_request.wlimitExecCondType = "1";
            
            //W指値用有効状態区分
            l_request.wlimitEnableStatusDiv = "1";
            
            l_request.id = "1101";
            
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[2];
            WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_request.closeMarginContractUnits[0] = l_unit1;
            
            l_request.checkPrice = "10";
            
            l_request.checkDate = WEB3DateUtility.getDate("20080325", "yyyyMMdd");
            
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02236);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }

    //以上check正常通過
    public void testValidateATReserveOrderCase8()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case8()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            //注文単価区分
            l_request.orderPriceDiv = "1";

            //注文単価
            l_request.limitPrice = "111";

            //執行条件
//            l_request.execCondType = "1";

            //注文期限区分
            l_request.expirationDateType ="3";

            //注文有効期限
            l_request.expirationDate = null;

            //発注条件区分
//            l_request.orderCondType = "2";

            //逆指値用プレミアム／原資産価格
            l_request.stopPremium_underlyingAssets = null;

            //逆指値用発注条件単価
            l_request.stopOrderCondPrice = null;

            //逆指値用発注条件演算子
            l_request.stopOrderCondOperator = null;

//            //Ｗ指値用プレミアム／原資産価格
//            l_request.wlimitPremium_underlyingAssets = null;
//
//            //Ｗ指値用発注条件単価
//            l_request.wlimitOrderCondPrice = null;
//
//            //Ｗ指値用発注条件演算子
//            l_request.wlimitOrderCondOperator = null;

            //Ｗ指値用注文単価区分
//            l_request.wLimitOrderPriceDiv = "1";
//
//            //Ｗ指値用注文単価
//            l_request.wLimitPrice = "1";
//            
//            //W指値用執行条件
//            l_request.wlimitExecCondType = "1";
//            
//            //W指値用有効状態区分
//            l_request.wlimitEnableStatusDiv = "1";
            
            l_request.id = "1101";
            
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[2];
            WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_request.closeMarginContractUnits[0] = l_unit1;
            //執行条件チェック
            //執行条件≠"無条件"の場合
            l_request.execCondType = "1";
            
            //発注条件区分≠"指定なし"
            l_request.orderCondType = "0";
            
            l_request.checkPrice = "10";
            
            l_request.checkDate = WEB3DateUtility.getDate("20080325", "yyyyMMdd");
            
            l_request.validateATReserveOrder();
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
            
        }
    }
    
}
@

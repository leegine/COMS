head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.26.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsOpenMarginConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション新規建注文確認リクエスト(WEB3OptionsOpenMarginConfirmRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/10 孫洪江(中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionsOpenMarginConfirmRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
        .getInstance(WEB3OptionsOpenMarginConfirmRequestTest.class);

    public WEB3OptionsOpenMarginConfirmRequestTest(String arg0)
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
     * ２）　@建区分チェック
     * ２－１）this.建区分がnullの値であれば例外をスローする。
     */
    public void testValidate_0001()
    {
        String STR_METHOD_NAME = ".testValidate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = null;

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00263, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //建区分チェック
    //this.建区分が”1：買建””2：売建"以外の場合例外をスローする。
    public void testValidate_0002()
    {
        String STR_METHOD_NAME = ".testValidate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "3";

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00264, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //３－１)取引市場チェック
    public void testValidate_0003()
    {
        String STR_METHOD_NAME = ".testValidate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = null;
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00265, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //３－２)this.取引市場が以下の値以外の値の場合例外をスローする。
    //　@・”1：東京” 
    //　@・”2：大阪”
    public void testValidate_0004()
    {
        String STR_METHOD_NAME = ".testValidate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "3";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01087, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //４－１指数種別チェック
    //this.指数種別がnullの値であれば例外をスローする。
    public void testValidate_0005()
    {
        String STR_METHOD_NAME = ".testValidate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= null;
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00266, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //４－２）this.指数種別が数字以外の値であれば例外をスローする。
    public void testValidate_0006()
    {
        String STR_METHOD_NAME = ".testValidate_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "a";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02441, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //４－３）this.指数種別の桁数が４桁以外の値であれば例外をスローする。
    public void testValidate_0007()
    {
        String STR_METHOD_NAME = ".testValidate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "12345";

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02442, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //５）限月チェック
    //５－１）this.限月がnullの値であれば例外をスローする。
    public void testValidate_0008()
    {
        String STR_METHOD_NAME = ".testValidate_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= null;
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00267, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //５－２）this.限月がＹＹＹＹＭＭ形式の値以外の値であれば例外をスローする。
    public void testValidate_0009()
    {
        String STR_METHOD_NAME = ".testValidate_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= "abcdef";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00268, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //６）　@オプション商品区分チェック
    //　@６－１）this.オプション商品区分がnullの値であれば例外をスローする。
    public void testValidate_0010()
    {
        String STR_METHOD_NAME = ".testValidate_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= "199912";
        l_request.opProductType=null;
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00269, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //６－２）this.オプション商品区分が以下の値以外の場合例外をスローする。
    public void testValidate_0011()
    {
        String STR_METHOD_NAME = ".testValidate_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= "199912";
        l_request.opProductType="a";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00270, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //７）　@行使価格チェック
    //　@７－１）this.行使価格がnullの値であれば例外をスローする。
    public void testValidate_0012()
    {
        String STR_METHOD_NAME = ".testValidate_0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= "199912";
        l_request.opProductType="C";
        l_request.strikePrice =null;
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00271, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //７－２）this.行使価格が数字以外の値であれば例外をスローする。
    public void testValidate_0013()
    {
        String STR_METHOD_NAME = ".testValidate_0013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= "199912";
        l_request.opProductType="C";
        l_request.strikePrice ="a";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00272, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //７－３）this.行使価格が≦０の値であれば例外をスローする。
    public void testValidate_0014()
    {
        String STR_METHOD_NAME = ".testValidate_0014()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= "199912";
        l_request.opProductType="C";
        l_request.strikePrice ="-1";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00273, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    //７－４）this.行使価格が８桁を超える値であれば例外をスローする。
    public void testValidate_0015()
    {
        String STR_METHOD_NAME = ".testValidate_0015()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= "199912";
        l_request.opProductType="C";
        l_request.strikePrice ="123456789";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00274, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //８－１）this.注文数量がnullの値であれば例外をスローする。
    public void testValidate_0016()
    {
        String STR_METHOD_NAME = ".testValidate_0016()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= "199912";
        l_request.opProductType="C";
        l_request.strikePrice ="12345678";
        l_request.opOrderQuantity= null;
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00074, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //８－２）this.注文数量が数字以外の値であれば例外をスローする。
    public void testValidate_0017()
    {
        String STR_METHOD_NAME = ".testValidate_0017()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= "199912";
        l_request.opProductType="C";
        l_request.strikePrice ="12345678";
        l_request.opOrderQuantity= "a";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00075, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //８－３）this.注文数量が≦０の値であれば例外をスローする。
    public void testValidate_0018()
    {
        String STR_METHOD_NAME = ".testValidate_0018()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= "199912";
        l_request.opProductType="C";
        l_request.strikePrice ="12345678";
        l_request.opOrderQuantity= "-1";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00076, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //８－４）this.注文数量が５桁を超える値であれば例外をスローする。
    public void testValidate_0019()
    {
        String STR_METHOD_NAME = ".testValidate_0019()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= "199912";
        l_request.opProductType="C";
        l_request.strikePrice ="12345678";
        l_request.opOrderQuantity= "123456";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00077, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //正常
    public void testValidate_0020()
    {
        String STR_METHOD_NAME = ".testValidate_0020()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= "199912";
        l_request.opProductType="C";
        l_request.strikePrice ="12345678";
        l_request.opOrderQuantity= "12345";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
}
@

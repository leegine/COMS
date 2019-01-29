head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.25.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsOpenMarginCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション新規建注文完了リクエスト(WEB3OptionsOpenMarginCompleteRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/11 孫洪江(中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

public class WEB3OptionsOpenMarginCompleteRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
        .getInstance(WEB3OptionsOpenMarginCompleteRequestTest.class);

    public WEB3OptionsOpenMarginCompleteRequestTest(String arg0)
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
    
    
    //２）　@銘柄チェック 
    //this.銘柄コードがnullの場合、下記条件以外は例外「銘柄指定エラー」をスローする。 
    //　@・this.指数種別!=null　@&&　@this.限月!=null　@&&　@
    //     this.オプション商品区分!=null　@&&　@this.行使価格!=null
    public void testValidate_0001()
    {
        String STR_METHOD_NAME = ".testValidate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
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

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00334, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //２）　@銘柄チェック 
    //this.銘柄コードがnullの場合、下記条件以外は例外「銘柄指定エラー」をスローする。 
    //　@・this.指数種別!=null　@&&　@this.限月!=null　@&&　@
    //     this.オプション商品区分!=null　@&&　@this.行使価格!=null
    public void testValidate_0002()
    {
        String STR_METHOD_NAME = ".testValidate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = null;
        
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

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00334, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
//  ２）　@銘柄チェック 
    //this.銘柄コードがnullの場合、下記条件以外は例外「銘柄指定エラー」をスローする。 
    //　@・this.指数種別!=null　@&&　@this.限月!=null　@&&　@
    //     this.オプション商品区分!=null　@&&　@this.行使価格!=null
    public void testValidate_0003()
    {
        String STR_METHOD_NAME = ".testValidate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = "199912";
        l_request.opProductType = null;
        
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

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00334, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
//  ２）　@銘柄チェック 
    //this.銘柄コードがnullの場合、下記条件以外は例外「銘柄指定エラー」をスローする。 
    //　@・this.指数種別!=null　@&&　@this.限月!=null　@&&　@
    //     this.オプション商品区分!=null　@&&　@this.行使価格!=null
    public void testValidate_0004()
    {
        String STR_METHOD_NAME = ".testValidate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = "199912";
        l_request.opProductType = "C";
        l_request.strikePrice=null;
        
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

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00334, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //３）　@建区分チェック
    //　@３－１）this.建区分がnullの値であれば例外をスローする。
    public void testValidate_0005()
    {
        String STR_METHOD_NAME = ".testValidate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = "199912";
        l_request.opProductType = "C";
        l_request.strikePrice="1";
        l_request.contractType=null;
        
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
    
    //　@３－２）this.建区分が以下の値以外の場合例外をスローする。
    //　@　@　@　@・”1：買建”
    //　@　@　@　@・”2：売建”
    public void testValidate_0006()
    {
        String STR_METHOD_NAME = ".testValidate_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = "199912";
        l_request.opProductType = "C";
        l_request.strikePrice="1";
        l_request.contractType="4";
        
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
    
    
    //４）　@取引市場チェック
    //　@４－１）this.取引市場がnullの値であれば例外をスローする。
    public void testValidate_0007()
    {
        String STR_METHOD_NAME = ".testValidate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = "199912";
        l_request.opProductType = "C";
        l_request.strikePrice="1";
        l_request.contractType="1";
        l_request.marketCode=null;
        
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
    
    
    //　@４－２)this.取引市場が以下の値以外の値の場合例外をスローする。 
    //       ・”1：東京”
    //       ・”2：大阪”
    public void testValidate_0008()
    {
        String STR_METHOD_NAME = ".testValidate_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = "199912";
        l_request.opProductType = "C";
        l_request.strikePrice="1";
        l_request.contractType="1";
        l_request.marketCode="4";
        
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
      
    //５）　@注文数量チェック
    //　@５－１）this.注文数量がnullの値であれば例外をスローする。
    public void testValidate_0009()
    {
        String STR_METHOD_NAME = ".testValidate_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = "199912";
        l_request.opProductType = "C";
        l_request.strikePrice="1";
        l_request.contractType="1";
        l_request.marketCode="1";
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
    
    //　@５－２）this.注文数量が数字以外の値であれば例外をスローする。
    public void testValidate_0010()
    {
        String STR_METHOD_NAME = ".testValidate_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = "199912";
        l_request.opProductType = "C";
        l_request.strikePrice="1";
        l_request.contractType="1";
        l_request.marketCode="1";
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
    
    //　@５－３）this.注文数量が≦０の値であれば例外をスローする。
    public void testValidate_0011()
    {
        String STR_METHOD_NAME = ".testValidate_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = "199912";
        l_request.opProductType = "C";
        l_request.strikePrice="1";
        l_request.contractType="1";
        l_request.marketCode="1";
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
    
    // 　@５－４）this.注文数量が５桁を超える値であれば例外をスローする。
    public void testValidate_0012()
    {
        String STR_METHOD_NAME = ".testValidate_0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = "199912";
        l_request.opProductType = "C";
        l_request.strikePrice="1";
        l_request.contractType="1";
        l_request.marketCode="1";
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

    // ７）　@確認時発注日チェック(this.注文ID==nullの場合、チェックを行わない）
    // 　@this.確認時発注日がnullの値であれば例外をスローする。
    public void testValidate_0013()
    {
        String STR_METHOD_NAME = ".testValidate_0013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = "199912";
        l_request.opProductType = "C";
        l_request.strikePrice="1";
        l_request.contractType="1";
        l_request.marketCode="1";
        l_request.opOrderQuantity= "12345";
        l_request.orderId="1";
        l_request.checkDate=null;
        
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

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00078, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //正常
    public void testValidate_0014()
    {
        String STR_METHOD_NAME = ".testValidate_0014()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = "199912";
        l_request.opProductType = "C";
        l_request.strikePrice="1";
        l_request.contractType="1";
        l_request.marketCode="1";
        l_request.opOrderQuantity= "12345";
        l_request.orderId=null;
        l_request.checkDate=null;
        
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

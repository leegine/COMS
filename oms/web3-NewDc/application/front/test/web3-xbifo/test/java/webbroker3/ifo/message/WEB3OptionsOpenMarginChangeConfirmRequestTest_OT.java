head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.23.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsOpenMarginChangeConfirmRequestTest_OT.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション訂正新規建確認リクエスト(WEB3OptionsOpenMarginChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/06/05 焦洋 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import test.util.JunitTestBase;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 *（株価指数オプション訂正新規建確認リクエストのテスト）<BR>
 * 
 * @@author 焦洋
 * @@version 1.0
 */
public class WEB3OptionsOpenMarginChangeConfirmRequestTest_OT extends JunitTestBase 
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
    .getInstance(WEB3OptionsOpenMarginChangeConfirmRequestTest_OT.class);
	
	public WEB3OptionsOpenMarginChangeConfirmRequestTest_OT(String arg0) 
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

    /**
     * 注文単価区分チェック
     * this.注文単価区分がnullの値であれば例外をスローする。
     * テスト確認内容: BUSINESS_ERROR_00184
     */
	public void testValidate_0001()
    {
        String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsOpenMarginChangeConfirmRequest l_request = new WEB3OptionsOpenMarginChangeConfirmRequest();
        
        l_request.orderPriceDiv = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00184, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * ＩＤチェック
     * this.ＩＤがnullの値であれば例外をスローする。
     * テスト確認内容: BUSINESS_ERROR_00080
     */
	public void testValidate_0002()
    {
        String STR_METHOD_NAME = "test_validate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsOpenMarginChangeConfirmRequest l_request = new WEB3OptionsOpenMarginChangeConfirmRequest();
        
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
        
        l_request.id = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }	

    /**
     * 注文数量チェック
     * this.注文数量がnullの値であれば例外をスローする。
     * テスト確認内容: BUSINESS_ERROR_00074
     */
	public void testValidate_0003()
    {
        String STR_METHOD_NAME = "test_validate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsOpenMarginChangeConfirmRequest l_request = new WEB3OptionsOpenMarginChangeConfirmRequest();
        
        
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
        
        //ＩＤ
        l_request.id = "0001";
        
        l_request.opOrderQuantity = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00074, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 注文数量チェック
     * this.注文数量が数字以外の値であれば例外をスローする。
     * テスト確認内容: BUSINESS_ERROR_00075
     */
	public void testValidate_0004()
    {
        String STR_METHOD_NAME = "test_validate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsOpenMarginChangeConfirmRequest l_request = new WEB3OptionsOpenMarginChangeConfirmRequest();
        
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
        
        //ＩＤ
        l_request.id = "0001";
        
        l_request.opOrderQuantity = "a";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00075, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 注文数量チェック
     * this.注文数量が≦０の値であれば例外をスローする。
     * テスト確認内容: BUSINESS_ERROR_00076
     */
	public void testValidate_0005()
    {
        String STR_METHOD_NAME = "test_validate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsOpenMarginChangeConfirmRequest l_request = new WEB3OptionsOpenMarginChangeConfirmRequest();
        
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
        
        //ＩＤ
        l_request.id = "0001";
        
        l_request.opOrderQuantity = "-18";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00076, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 注文数量チェック
     * this.注文数量が５桁を超える値であれば例外をスローする。
     * テスト確認内容: BUSINESS_ERROR_00077
     */
	public void testValidate_0006()
    {
        String STR_METHOD_NAME = "test_validate_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsOpenMarginChangeConfirmRequest l_request = new WEB3OptionsOpenMarginChangeConfirmRequest();
        
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
        
        //ＩＤ
        l_request.id = "0001";
        
        l_request.opOrderQuantity = "123456";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00077, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 注文数量チェック
     * 注文数量 = "1000"
     * 正常終了
     */
	public void testValidate_0007()
    {
        String STR_METHOD_NAME = "test_validate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsOpenMarginChangeConfirmRequest l_request = new WEB3OptionsOpenMarginChangeConfirmRequest();
        
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
        
        //ＩＤ
        l_request.id = "0001";
        
        //注文数量
        l_request.opOrderQuantity = "1000";
        
        try
        {
            l_request.validate();

        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
	
}
@

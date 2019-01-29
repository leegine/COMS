head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.26.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsOpenMarginChangeCompleteRequestTest_OT.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/06/04 唐性峰 (中訊) 新規作成 仕様変更モデル
*/
package webbroker3.ifo.message;

import test.util.JunitTestBase;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**                                                                    
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3OptionsOpenMarginChangeCompleteRequestTest_OT extends JunitTestBase
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionsOpenMarginChangeCompleteRequestTest_OT.class);

    public WEB3OptionsOpenMarginChangeCompleteRequestTest_OT(String arg0)
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
     * Test method for 'webbroker3.ifo.message.WEB3OptionsOpenMarginChangeCompleteRequest.validate()'
     */
    public void testValidate_case001()
    {
        final String STR_METHOD_NAME = "testValidate_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3OptionsOpenMarginChangeCompleteRequest l_request =
                new WEB3OptionsOpenMarginChangeCompleteRequest();
            l_request.validate();
            fail();
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00184);
        }
        catch (Exception l_ex)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }

    public void testValidate_case002()
    {
        final String STR_METHOD_NAME = "testValidate_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3OptionsOpenMarginChangeCompleteRequest l_request =
                new WEB3OptionsOpenMarginChangeCompleteRequest();

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

            l_request.validate();
            fail();
            
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00080);
        }
        catch (Exception l_ex)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }

    }

    public void testValidate_case003()
    {
        final String STR_METHOD_NAME = "testValidate_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3OptionsOpenMarginChangeCompleteRequest l_request =
                new WEB3OptionsOpenMarginChangeCompleteRequest();

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
            l_request.id = "123";

            l_request.validate();
            fail();
            
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00074);
        }
        catch (Exception l_ex)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }

    }

    public void testValidate_case004()
    {
        final String STR_METHOD_NAME = "testValidate_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3OptionsOpenMarginChangeCompleteRequest l_request =
                new WEB3OptionsOpenMarginChangeCompleteRequest();

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
            l_request.id = "123";

            //注文数量
            l_request.opOrderQuantity = "a";

            l_request.validate();
            fail();
            
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00075);
        }
        catch (Exception l_ex)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }

    }

    public void testValidate_case005()
    {
        final String STR_METHOD_NAME = "testValidate_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3OptionsOpenMarginChangeCompleteRequest l_request =
                new WEB3OptionsOpenMarginChangeCompleteRequest();

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
            l_request.id = "123";

            //注文数量
            l_request.opOrderQuantity = "-10";

            l_request.validate();
            fail();
            
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00076);
        }
        catch (Exception l_ex)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }

    }

    public void testValidate_case006()
    {
        final String STR_METHOD_NAME = "testValidate_case006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3OptionsOpenMarginChangeCompleteRequest l_request =
                new WEB3OptionsOpenMarginChangeCompleteRequest();

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
            l_request.id = "123";

            //注文数量
            l_request.opOrderQuantity = "100001";

            l_request.validate();
            fail();
            
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00077);
        }
        catch (Exception l_ex)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }

    }

    public void testValidate_case007()
    {
        final String STR_METHOD_NAME = "testValidate_case007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3OptionsOpenMarginChangeCompleteRequest l_request =
                new WEB3OptionsOpenMarginChangeCompleteRequest();

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
            l_request.id = "123";

            //注文数量
            l_request.opOrderQuantity = "5";

            l_request.validate();

        }
        catch (Exception l_ex)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }

    }
    
}
@

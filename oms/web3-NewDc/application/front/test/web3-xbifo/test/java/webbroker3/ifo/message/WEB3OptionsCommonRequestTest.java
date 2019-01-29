head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.24.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3OptionsCommonRequestTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2006/06/14 孫洪江 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionsCommonRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
        .getInstance(WEB3OptionsCommonRequestTest.class);

    public WEB3OptionsCommonRequestTest(String arg0)
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
    
    public void testValidate_0001()
    {
        String STR_METHOD_NAME = ".testValidate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCommonRequest l_request = new WEB3OptionsCommonRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
        //注文単価区分
        l_request.orderPriceDiv = "1";

        //注文単価
        l_request.limitPrice = "111";

        //執行条件
        l_request.execCondType = "7";

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

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02817, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    public void testValidate_0002()
    {
        String STR_METHOD_NAME = ".testValidate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCommonRequest l_request = new WEB3OptionsCommonRequest();
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
    
    public void testValidate_0003()
    {
        String STR_METHOD_NAME = ".testValidate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCommonRequest l_request = new WEB3OptionsCommonRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
        //注文単価区分
        l_request.orderPriceDiv = "1";

        //注文単価
        l_request.limitPrice = "111";

        //執行条件
        l_request.execCondType = "7";

        //注文期限区分
        l_request.expirationDateType ="1";

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
    
    public void testValidate_0004()
    {
        String STR_METHOD_NAME = ".testValidate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCommonRequest l_request = new WEB3OptionsCommonRequest();
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
        l_request.expirationDate = WEB3DateUtility.getDate("20070301","yyyyMMdd");

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
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02818, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    public void testValidate_0005()
    {
        String STR_METHOD_NAME = ".testValidate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCommonRequest l_request = new WEB3OptionsCommonRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
        //注文単価区分
        l_request.orderPriceDiv = "1";

        //注文単価
        l_request.limitPrice = "111";

        //執行条件
        l_request.execCondType = "1";

        //注文期限区分
        l_request.expirationDateType ="2";

        //注文有効期限
        l_request.expirationDate = WEB3DateUtility.getDate("20070301","yyyyMMdd");

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
    
    public void testValidate_0006()
    {
        String STR_METHOD_NAME = ".testValidate_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCommonRequest l_request = new WEB3OptionsCommonRequest();
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
    
    public void testValidate_0007()
    {
        String STR_METHOD_NAME = ".testValidate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCommonRequest l_request = new WEB3OptionsCommonRequest();
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
        l_request.wlimitExecCondType = "7";
        
        //W指値用有効状態区分
        l_request.wlimitEnableStatusDiv = "1";

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
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02503, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    public void testValidate_0008()
    {
        String STR_METHOD_NAME = ".testValidate_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCommonRequest l_request = new WEB3OptionsCommonRequest();
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
        l_request.orderCondType = "1";

        //逆指値用プレミアム／原資産価格
        l_request.stopPremium_underlyingAssets = "1";

        //逆指値用発注条件単価
        l_request.stopOrderCondPrice = "111";

        //逆指値用発注条件演算子
        l_request.stopOrderCondOperator = "1";

        //Ｗ指値用プレミアム／原資産価格
        l_request.wlimitPremium_underlyingAssets = null;

        //Ｗ指値用発注条件単価
        l_request.wlimitOrderCondPrice = null;

        //Ｗ指値用発注条件演算子
        l_request.wlimitOrderCondOperator = null;

        //Ｗ指値用注文単価区分
        l_request.wLimitOrderPriceDiv = null;

        //Ｗ指値用注文単価
        l_request.wLimitPrice = null;
        
        //W指値用執行条件
        l_request.wlimitExecCondType = null;
        
        //W指値用有効状態区分
        l_request.wlimitEnableStatusDiv = "1";

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

    public void testValidate_0009()
    {
        String STR_METHOD_NAME = ".testValidate_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCommonRequest l_request = new WEB3OptionsCommonRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
        //注文単価区分
        l_request.orderPriceDiv = "1";

        //注文単価
        l_request.limitPrice = "111";

        //執行条件
        l_request.execCondType = "1";

        //注文期限区分
        l_request.expirationDateType ="2";

        //注文有効期限
        l_request.expirationDate = WEB3DateUtility.getDate("20070301","yyyyMMdd");

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
    
    public void testValidate_0010()
    {
        String STR_METHOD_NAME = ".testValidate_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCommonRequest l_request = new WEB3OptionsCommonRequest();
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
    
    public void testValidate_0011()
    {
        String STR_METHOD_NAME = ".testValidate_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCommonRequest l_request = new WEB3OptionsCommonRequest();
        //注文数量
        l_request.opOrderQuantity = "1";
        
        //注文単価区分
        l_request.orderPriceDiv = "1";

        //注文単価
        l_request.limitPrice = "111";

        //執行条件
        l_request.execCondType = "1";

        //注文期限区分
        l_request.expirationDateType ="4";

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
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00209, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //連続注文対応
    //  執行条件≠"無条件"の場合
    public void testValidateTriggerOrderCase1()
    {
        String STR_METHOD_NAME = "testValidateTriggerOrderCase1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCommonRequest l_request = new WEB3OptionsCommonRequest();
        //執行条件チェック
        //執行条件≠"無条件"の場合
        l_request.execCondType = "2";
        try
        {
            l_request.validateSuccOrder();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02235, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //発注条件区分≠"指定なし"
    public void testValidateTriggerOrderCase2()
    {
        String STR_METHOD_NAME = "testValidateTriggerOrderCase2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCommonRequest l_request = new WEB3OptionsCommonRequest();
        //執行条件チェック
        //執行条件≠"無条件"の場合
        l_request.execCondType = "1";
        
        //発注条件区分≠"指定なし"
        l_request.orderCondType = "1";
        try
        {
            l_request.validateSuccOrder();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02236, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //　@執行条件="無条件"
    //発注条件区分="指定なし"
    public void testValidateTriggerOrderCase3()
    {
        String STR_METHOD_NAME = "testValidateTriggerOrderCase3()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCommonRequest l_request = new WEB3OptionsCommonRequest();
        //執行条件チェック
        //執行条件≠"無条件"の場合
        l_request.execCondType = "1";
        
        //発注条件区分≠"指定なし"
        l_request.orderCondType = "0";
        try
        {
            l_request.validateSuccOrder();

            log.exiting(TEST_END + STR_METHOD_NAME);
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

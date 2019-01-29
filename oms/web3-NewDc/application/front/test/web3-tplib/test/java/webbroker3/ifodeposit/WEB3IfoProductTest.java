head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.28.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoProductTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3IfoProductTest.java
Author Name      : Daiwa Institute of Research
Revesion History : 2008/08/26 祁永星 (中訊) 新規作成
*/
package webbroker3.ifodeposit;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifodeposit.define.WEB3IfoReservedDateDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.quoteadaptor.AskPriceTitle;
import webbroker3.quoteadaptor.BidPriceTitle;
import webbroker3.quoteadaptor.CurrentPriceFlag;
import webbroker3.quoteadaptor.DataType;
import webbroker3.quoteadaptor.PutAndCall;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 
 * @@author 祁永星
 * @@version 1.0
 */
public class WEB3IfoProductTest extends TestBaseForMock
{

    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoProductTest.class);

    WEB3IfoProduct l_product = null;

    public WEB3IfoProductTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_product = new WEB3IfoProduct();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //(set先物OP銘柄情報<当日清算値使用>)
    //引数.先物OP取引銘柄UpdqParams.清算値 == 0の場合
    //時価 == 先物OP取引銘柄Params.清算値
    public void testSetIfoProductWithCurrentBizDateLiquidationPrice_C0001()
    {
        final String STR_METHOD_NAME = "testSetIfoProductWithCurrentBizDateLiquidationPrice_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            IfoProductParams l_ifoProductParams = new IfoProductParams();
            l_ifoProductParams.setProductId(12345678L);
            l_ifoProductParams.setProductCode("123456");
            l_ifoProductParams.setUnderlyingProductCode("0005");
            l_ifoProductParams.setDerivativeType(new IfoDerivativeTypeEnum(1, "FUTURES"));

            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = new IfoTradedProductUpdqParams();
            l_ifoTradedProductUpdqParams.setMarketId(1001L);
            l_ifoTradedProductUpdqParams.setUnitSize(10000D);
            l_ifoTradedProductUpdqParams.setLiquidationPrice(0d);
            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20080808","yyyyMMdd"));

            IfoTradedProductParams l_ifoTradedProductParams = new IfoTradedProductParams();
            l_ifoTradedProductParams.setLiquidationPrice(995d);
           
            l_product.setIfoProductWithCurrentBizDateLiquidationPrice(
                l_ifoProductParams, 
                l_ifoTradedProductUpdqParams,
                l_ifoTradedProductParams);         

            assertEquals(12345678L, l_product.getProductId());
            assertEquals("123456", l_product.getProductCode());
            assertEquals("0005", l_product.getUnderlyingProductCode());
            assertEquals(
                new IfoDerivativeTypeEnum(1, "FUTURES"), 
                l_product.getIfoDerivativeType());
            assertEquals(1001L, l_product.getMarketId());
            assertEquals(10000L, l_product.getUnitSize(), 0);
            assertEquals(995d, l_product.getCurrentPrice(), 0);
            assertEquals(
                "20080808",
                WEB3DateUtility.formatDate(l_product.getLastTradingDate(), "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //(set先物OP銘柄情報<当日清算値使用>)
    //引数.先物OP取引銘柄UpdqParams.清算値 == 0以外の場合
    //時価 ==先物OP取引銘柄UpdqParams.清算値
    public void testSetIfoProductWithCurrentBizDateLiquidationPrice_C0002()
    {
        final String STR_METHOD_NAME = "testSetIfoProductWithCurrentBizDateLiquidationPrice_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            IfoProductParams l_ifoProductParams = new IfoProductParams();
            l_ifoProductParams.setProductId(12345678L);
            l_ifoProductParams.setProductCode("123456");
            l_ifoProductParams.setUnderlyingProductCode("0005");
            l_ifoProductParams.setDerivativeType(new IfoDerivativeTypeEnum(1, "FUTURES"));

            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = new IfoTradedProductUpdqParams();
            l_ifoTradedProductUpdqParams.setMarketId(1001L);
            l_ifoTradedProductUpdqParams.setUnitSize(10000D);
            l_ifoTradedProductUpdqParams.setLiquidationPrice(1d);
            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20080808","yyyyMMdd"));

            IfoTradedProductParams l_ifoTradedProductParams = new IfoTradedProductParams();
            l_ifoTradedProductParams.setLiquidationPrice(995d);

            l_product.setIfoProductWithCurrentBizDateLiquidationPrice(
                l_ifoProductParams, 
                l_ifoTradedProductUpdqParams,
                l_ifoTradedProductParams);         

            assertEquals(12345678L, l_product.getProductId());
            assertEquals("123456", l_product.getProductCode());
            assertEquals("0005", l_product.getUnderlyingProductCode());
            assertEquals(
                new IfoDerivativeTypeEnum(1, "FUTURES"), 
                l_product.getIfoDerivativeType());
            assertEquals(1001L, l_product.getMarketId());
            assertEquals(10000L, l_product.getUnitSize(), 0);
            assertEquals(1d, l_product.getCurrentPrice(), 0);
            assertEquals(
                "20080808",
                WEB3DateUtility.formatDate(l_product.getLastTradingDate(), "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(set先物OP銘柄情報<前日清算値使用>)
    public void testSetIfoProductWithPreBizDateLiquidationPrice_C0001()
    {
        final String STR_METHOD_NAME =
            "testSetIfoProductWithPreBizDateLiquidationPrice_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            IfoProductParams l_ifoProductParams = new IfoProductParams();
            l_ifoProductParams.setProductId(12345678L);
            l_ifoProductParams.setProductCode("123456");
            l_ifoProductParams.setUnderlyingProductCode("0005");
            l_ifoProductParams.setDerivativeType(new IfoDerivativeTypeEnum(1, "FUTURES"));

            IfoTradedProductParams l_ifoTradedProductParams = new IfoTradedProductParams();  
            l_ifoTradedProductParams.setMarketId(1001L);
            l_ifoTradedProductParams.setUnitSize(10000L);
            l_ifoTradedProductParams.setLiquidationPrice(8341);
            l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080808","yyyyMMdd"));

    
            l_product.setIfoProductWithPreBizDateLiquidationPrice(
                l_ifoProductParams,
                l_ifoTradedProductParams);

            assertEquals(12345678L, l_product.getProductId());
            assertEquals("123456", l_product.getProductCode());
            assertEquals("0005", l_product.getUnderlyingProductCode());
            assertEquals(
                new IfoDerivativeTypeEnum(1, "FUTURES"), 
                l_product.getIfoDerivativeType());
            assertEquals(1001L, l_product.getMarketId());
            assertEquals(10000L, l_product.getUnitSize(), 0);
            assertEquals(8341, l_product.getCurrentPrice(), 0);
            assertEquals(
                "20080808",
                WEB3DateUtility.formatDate(l_product.getLastTradingDate(), "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(set先物OP銘柄情報<リアル時価使用>)
    public void testSetIfoProductWithCurrentPrice_C0001()
    {
        final String STR_METHOD_NAME =
            "testSetIfoProductWithCurrentPrice_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            l_ifoProductParams = new IfoProductParams();
            l_ifoProductParams.setProductId(12345678L);
            l_ifoProductParams.setProductCode("123456");
            l_ifoProductParams.setUnderlyingProductCode("0005");
            l_ifoProductParams.setDerivativeType(new IfoDerivativeTypeEnum(1, "FUTURES"));
 
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            l_ifoTradedProductParams = new IfoTradedProductParams();
            l_ifoTradedProductParams.setMarketId(1001L);
            l_ifoTradedProductParams.setUnitSize(10000L);
            l_ifoTradedProductParams.setLiquidationPrice(8341);//
            l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080808","yyyyMMdd"));
            
            l_ifoTradedProductParams.setProductId(12345678L);
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.commit();
            
            l_product.setIfoProductWithCurrentPrice(
                l_ifoProductParams,
                l_ifoTradedProductParams);

            assertEquals(12345678L, l_product.getProductId());
            assertEquals("123456", l_product.getProductCode());
            assertEquals("0005", l_product.getUnderlyingProductCode());
            assertEquals(
                new IfoDerivativeTypeEnum(1, "FUTURES"), 
                l_product.getIfoDerivativeType());
            assertEquals(1001L, l_product.getMarketId());
            assertEquals(10000L, l_product.getUnitSize(), 0);
            assertEquals(8341, l_product.getCurrentPrice(), 0);
            assertEquals(
                "20080808",
                WEB3DateUtility.formatDate(l_product.getLastTradingDate(), "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(set先物OP銘柄情報<リアル時価使用-夕場>)
    //引数.先物OP取引銘柄UpdqParams.清算値　@==　@0の場合
    //当日清算値は引数.証拠金不足メール送信フラグ==trueの場合
    public void testSetIfoProductWithCurrentPriceEvening_C0001()
    {
        final String STR_METHOD_NAME = "testSetIfoProductWithCurrentPriceEvening_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.commit();
            
            //引数をセット
            l_ifoProductParams = new IfoProductParams();
            l_ifoProductParams.setProductId(12345678L);
            l_ifoProductParams.setProductCode("123456");
            l_ifoProductParams.setUnderlyingProductCode("0005");
            l_ifoProductParams.setDerivativeType(new IfoDerivativeTypeEnum(1, "FUTURES"));

            l_ifoTradedProductParams = new IfoTradedProductParams();
            l_ifoTradedProductParams.setMarketId(1001L);
            l_ifoTradedProductParams.setUnitSize(10000L);
            l_ifoTradedProductParams.setLiquidationPrice(8341);//
            l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080808","yyyyMMdd"));
            
            l_ifoTradedProductParams.setProductId(12345678L);
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            
            boolean l_blnIfoDepositMailFlag = true;
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = new IfoTradedProductUpdqParams();
            l_ifoTradedProductUpdqParams.setLiquidationPrice(0);
            
            l_product.setIfoProductWithCurrentPriceEvening(
                l_ifoProductParams,
                l_ifoTradedProductParams,
                l_blnIfoDepositMailFlag,
                l_ifoTradedProductUpdqParams);
 
            assertEquals(12345678L, l_product.getProductId());
            assertEquals("123456", l_product.getProductCode());
            assertEquals("0005", l_product.getUnderlyingProductCode());
            assertEquals(
                new IfoDerivativeTypeEnum(1, "FUTURES"), 
                l_product.getIfoDerivativeType());
            assertEquals(1001L, l_product.getMarketId());
            assertEquals(10000L, l_product.getUnitSize(), 0);
            assertEquals(8341, l_product.getCurrentPrice(), 0);
            assertEquals(
                "20080808",
                WEB3DateUtility.formatDate(l_product.getLastTradingDate(), "yyyyMMdd"));
            
            assertEquals(0, l_product.currentBizDateLiquidationPrice, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(set先物OP銘柄情報<リアル時価使用-夕場>)
    //引数.先物OP取引銘柄UpdqParams.清算値　@==　@0以外の場合
    //当日清算値は引数.証拠金不足メール送信フラグ==trueの場合
    public void testSetIfoProductWithCurrentPriceEvening_C0002()
    {
        final String STR_METHOD_NAME = "testSetIfoProductWithCurrentPriceEvening_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.commit();
            //引数をセット
            l_ifoProductParams = new IfoProductParams();
            l_ifoProductParams.setProductId(12345678L);
            l_ifoProductParams.setProductCode("123456");
            l_ifoProductParams.setUnderlyingProductCode("0005");
            l_ifoProductParams.setDerivativeType(new IfoDerivativeTypeEnum(1, "FUTURES"));

            l_ifoTradedProductParams = new IfoTradedProductParams();
            l_ifoTradedProductParams.setMarketId(1001L);
            l_ifoTradedProductParams.setUnitSize(10000L);
            l_ifoTradedProductParams.setLiquidationPrice(8341);//
            l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080808","yyyyMMdd"));
            
            l_ifoTradedProductParams.setProductId(12345678L);
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            
            boolean l_blnIfoDepositMailFlag = true;
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = new IfoTradedProductUpdqParams();
            l_ifoTradedProductUpdqParams.setLiquidationPrice(995d);
            
            l_product.setIfoProductWithCurrentPriceEvening(
                l_ifoProductParams,
                l_ifoTradedProductParams,
                l_blnIfoDepositMailFlag,
                l_ifoTradedProductUpdqParams);
 
            assertEquals(12345678L, l_product.getProductId());
            assertEquals("123456", l_product.getProductCode());
            assertEquals("0005", l_product.getUnderlyingProductCode());
            assertEquals(
                new IfoDerivativeTypeEnum(1, "FUTURES"), 
                l_product.getIfoDerivativeType());
            assertEquals(1001L, l_product.getMarketId());
            assertEquals(10000L, l_product.getUnitSize(), 0);
            assertEquals(995, l_product.getCurrentPrice(), 0);
            assertEquals(
                "20080808",
                WEB3DateUtility.formatDate(l_product.getLastTradingDate(), "yyyyMMdd"));
            
            assertEquals(0, l_product.currentBizDateLiquidationPrice, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(set先物OP銘柄情報<リアル時価使用-夕場>)
    //引数.先物OP取引銘柄UpdqParams.清算値　@==　@0の場合
    //当日清算値は引数.証拠金不足メール送信フラグ==falseの場合
    public void testSetIfoProductWithCurrentPriceEvening_C0003()
    {
        final String STR_METHOD_NAME = "testSetIfoProductWithCurrentPriceEvening_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.commit();
            
            //引数をセット
            l_ifoProductParams = new IfoProductParams();
            l_ifoProductParams.setProductId(12345678L);
            l_ifoProductParams.setProductCode("123456");
            l_ifoProductParams.setUnderlyingProductCode("0005");
            l_ifoProductParams.setDerivativeType(new IfoDerivativeTypeEnum(1, "FUTURES"));

            l_ifoTradedProductParams = new IfoTradedProductParams();
            l_ifoTradedProductParams.setMarketId(1001L);
            l_ifoTradedProductParams.setUnitSize(10000L);
            l_ifoTradedProductParams.setLiquidationPrice(8341);//
            l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080808","yyyyMMdd"));
            
            l_ifoTradedProductParams.setProductId(12345678L);
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            
            boolean l_blnIfoDepositMailFlag = false;
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = new IfoTradedProductUpdqParams();
            l_ifoTradedProductUpdqParams.setLiquidationPrice(0);
            
            l_product.setIfoProductWithCurrentPriceEvening(
                l_ifoProductParams,
                l_ifoTradedProductParams,
                l_blnIfoDepositMailFlag,
                l_ifoTradedProductUpdqParams);
 
            assertEquals(12345678L, l_product.getProductId());
            assertEquals("123456", l_product.getProductCode());
            assertEquals("0005", l_product.getUnderlyingProductCode());
            assertEquals(
                new IfoDerivativeTypeEnum(1, "FUTURES"), 
                l_product.getIfoDerivativeType());
            assertEquals(1001L, l_product.getMarketId());
            assertEquals(10000L, l_product.getUnitSize(), 0);
            assertEquals(8341, l_product.getCurrentPrice(), 0);
            assertEquals(
                "20080808",
                WEB3DateUtility.formatDate(l_product.getLastTradingDate(), "yyyyMMdd"));
            
            assertEquals(8341, l_product.currentBizDateLiquidationPrice, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(set先物OP銘柄情報<リアル時価使用-夕場>)
    //引数.先物OP取引銘柄UpdqParams.清算値　@==　@0の場合
    //当日清算値は引数.証拠金不足メール送信フラグ==falseの場合
    public void testSetIfoProductWithCurrentPriceEvening_C0004()
    {
        final String STR_METHOD_NAME = "testSetIfoProductWithCurrentPriceEvening_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.commit();
            //引数をセット
            l_ifoProductParams = new IfoProductParams();
            l_ifoProductParams.setProductId(12345678L);
            l_ifoProductParams.setProductCode("123456");
            l_ifoProductParams.setUnderlyingProductCode("0005");
            l_ifoProductParams.setDerivativeType(new IfoDerivativeTypeEnum(1, "FUTURES"));

            l_ifoTradedProductParams = new IfoTradedProductParams();
            l_ifoTradedProductParams.setMarketId(1001L);
            l_ifoTradedProductParams.setUnitSize(10000L);
            l_ifoTradedProductParams.setLiquidationPrice(8341);//
            l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080808","yyyyMMdd"));
            
            l_ifoTradedProductParams.setProductId(12345678L);
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            
            boolean l_blnIfoDepositMailFlag = false;
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = new IfoTradedProductUpdqParams();
            l_ifoTradedProductUpdqParams.setLiquidationPrice(995);
            
            l_product.setIfoProductWithCurrentPriceEvening(
                l_ifoProductParams,
                l_ifoTradedProductParams,
                l_blnIfoDepositMailFlag,
                l_ifoTradedProductUpdqParams);
 
            assertEquals(12345678L, l_product.getProductId());
            assertEquals("123456", l_product.getProductCode());
            assertEquals("0005", l_product.getUnderlyingProductCode());
            assertEquals(
                new IfoDerivativeTypeEnum(1, "FUTURES"), 
                l_product.getIfoDerivativeType());
            assertEquals(1001L, l_product.getMarketId());
            assertEquals(10000L, l_product.getUnitSize(), 0);
            assertEquals(995, l_product.getCurrentPrice(), 0);
            assertEquals(
                "20080808",
                WEB3DateUtility.formatDate(l_product.getLastTradingDate(), "yyyyMMdd"));
            
            assertEquals(995, l_product.currentBizDateLiquidationPrice, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
 
    //(get先物OP銘柄情報＜立会時間帯-日中＞)
    //リアル時価証拠金計算を実施している場合(引数.isリアル時価証拠金計算実施 == true)
    public void testGetOnSessionIfoProduct_C0001()
    {
        final String STR_METHOD_NAME = "testGetOnSessionIfoProduct_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            l_ifoProductParams = new IfoProductParams();
            l_ifoProductParams.setProductId(12345678L);
            l_ifoProductParams.setProductCode("123456");
            l_ifoProductParams.setUnderlyingProductCode("0005");
            l_ifoProductParams.setDerivativeType(new IfoDerivativeTypeEnum(1, "FUTURES"));
 
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            l_ifoTradedProductParams = new IfoTradedProductParams();
            l_ifoTradedProductParams.setMarketId(1001L);
            l_ifoTradedProductParams.setUnitSize(10000L);
            l_ifoTradedProductParams.setLiquidationPrice(8341);//
            l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080808","yyyyMMdd"));
            
            l_ifoTradedProductParams.setProductId(12345678L);
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3IfoQuoteDataImpl ifoQuoteData = new WEB3IfoQuoteDataImpl();
            ifoQuoteData.setCurrentPrice(8341);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getIfoQuote",
                    new Class[]
                    {IfoTradedProduct.class,RealType.class},
                    ifoQuoteData);
            
            boolean l_blnIsRealPriceIfoDepositCalc = true;

            l_product = WEB3IfoProduct.getOnSessionIfoProduct(l_ifoProductParams,l_ifoTradedProductParams,
                    l_blnIsRealPriceIfoDepositCalc);

            assertEquals(12345678L, l_product.getProductId());
            assertEquals("123456", l_product.getProductCode());
            assertEquals("0005", l_product.getUnderlyingProductCode());
            assertEquals(
                new IfoDerivativeTypeEnum(1, "FUTURES"), 
                l_product.getIfoDerivativeType());
            assertEquals(1001L, l_product.getMarketId());
            assertEquals(10000L, l_product.getUnitSize(), 0);
            assertEquals(8341, l_product.getCurrentPrice(), 0);
            assertEquals(
                "20080808",
                WEB3DateUtility.formatDate(l_product.getLastTradingDate(), "yyyyMMdd"));        
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(get先物OP銘柄情報＜立会時間帯-日中＞)
    //リアル時価証拠金計算を実施していない場合(引数.isリアル時価証拠金計算実施 == false)
    public void testGetOnSessionIfoProduct_C0002()
    {
        final String STR_METHOD_NAME = "testGetOnSessionIfoProduct_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            IfoProductParams l_ifoProductParams = new IfoProductParams();
            l_ifoProductParams.setProductId(12345678L);
            l_ifoProductParams.setProductCode("123456");
            l_ifoProductParams.setUnderlyingProductCode("0005");
            l_ifoProductParams.setDerivativeType(new IfoDerivativeTypeEnum(1, "FUTURES"));

            IfoTradedProductParams l_ifoTradedProductParams = new IfoTradedProductParams();  
            l_ifoTradedProductParams.setMarketId(1001L);
            l_ifoTradedProductParams.setUnitSize(10000L);
            l_ifoTradedProductParams.setLiquidationPrice(8341);
            l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080808","yyyyMMdd"));

            boolean l_blnIsRealPriceIfoDepositCalc = false;

            l_product =
                WEB3IfoProduct.getOnSessionIfoProduct(
                    l_ifoProductParams,
                    l_ifoTradedProductParams,
                    l_blnIsRealPriceIfoDepositCalc);

            assertEquals(12345678L, l_product.getProductId());
            assertEquals("123456", l_product.getProductCode());
            assertEquals("0005", l_product.getUnderlyingProductCode());
            assertEquals(
                new IfoDerivativeTypeEnum(1, "FUTURES"), 
                l_product.getIfoDerivativeType());
            assertEquals(1001L, l_product.getMarketId());
            assertEquals(10000L, l_product.getUnitSize(), 0);
            assertEquals(8341, l_product.getCurrentPrice(), 0);
            assertEquals(
                "20080808",
                WEB3DateUtility.formatDate(l_product.getLastTradingDate(), "yyyyMMdd"));  
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(get先物OP銘柄情報＜立会時間帯-夕場＞)
    //リアル時価証拠金計算を実施している場合(引数.isリアル時価証拠金計算実施 == true)
    public void testGetOnEveningSessionIfoProduct_C0001()
    {
        final String STR_METHOD_NAME = "testGetOnEveningSessionIfoProduct_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(12345678L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            //引数をセット
            l_ifoProductParams = new IfoProductParams();
            l_ifoProductParams.setProductId(12345678L);
            l_ifoProductParams.setProductCode("123456");
            l_ifoProductParams.setUnderlyingProductCode("0005");
            l_ifoProductParams.setDerivativeType(new IfoDerivativeTypeEnum(1, "FUTURES"));
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = new IfoTradedProductUpdqParams();
            l_ifoTradedProductUpdqParams.setLiquidationPrice(995);
            
            boolean l_blnIsRealPriceIfoDepositCalc = true;
            
            boolean l_blnIfoDepositMailFlag = false;

            l_ifoTradedProductParams = new IfoTradedProductParams();
            l_ifoTradedProductParams.setMarketId(1001L);
            l_ifoTradedProductParams.setUnitSize(10000L);
            l_ifoTradedProductParams.setLiquidationPrice(8341);//
            l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080808","yyyyMMdd"));
            
            l_ifoTradedProductParams.setProductId(12345678L);
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            
            l_product =
                WEB3IfoProduct.getOnEveningSessionIfoProduct(
                    l_ifoProductParams,
                    l_ifoTradedProductUpdqParams,
                    l_blnIsRealPriceIfoDepositCalc,
                    l_blnIfoDepositMailFlag,
                    l_ifoTradedProductParams);
 
            assertEquals(12345678L, l_product.getProductId());
            assertEquals("123456", l_product.getProductCode());
            assertEquals("0005", l_product.getUnderlyingProductCode());
            assertEquals(
                new IfoDerivativeTypeEnum(1, "FUTURES"), 
                l_product.getIfoDerivativeType());
            assertEquals(1001L, l_product.getMarketId());
            assertEquals(10000L, l_product.getUnitSize(), 0);
            assertEquals(995, l_product.getCurrentPrice(), 0);
            assertEquals(
                "20080808",
                WEB3DateUtility.formatDate(l_product.getLastTradingDate(), "yyyyMMdd"));
            
            assertEquals(995, l_product.currentBizDateLiquidationPrice, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get先物OP銘柄情報＜立会時間帯-夕場＞)
    //リアル時価証拠金計算を実施していない場合(引数.isリアル時価証拠金計算実施 == false)
    public void testGetOnEveningSessionIfoProduct_C0002()
    {
        final String STR_METHOD_NAME = "testGetOnEveningSessionIfoProduct_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            IfoProductParams l_ifoProductParams = new IfoProductParams();
            l_ifoProductParams.setProductId(12345678L);
            l_ifoProductParams.setProductCode("123456");
            l_ifoProductParams.setUnderlyingProductCode("0005");
            l_ifoProductParams.setDerivativeType(new IfoDerivativeTypeEnum(1, "FUTURES"));

            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = new IfoTradedProductUpdqParams();
            l_ifoTradedProductUpdqParams.setMarketId(1001L);
            l_ifoTradedProductUpdqParams.setUnitSize(10000D);
            l_ifoTradedProductUpdqParams.setLiquidationPrice(1d);
            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20080808","yyyyMMdd"));

            boolean l_blnIsRealPriceIfoDepositCalc = false;
            
            boolean l_blnIfoDepositMailFlag = false;
            
            IfoTradedProductParams l_ifoTradedProductParams = new IfoTradedProductParams();
            l_ifoTradedProductParams.setLiquidationPrice(995d);
            
            l_product =
                WEB3IfoProduct.getOnEveningSessionIfoProduct(
                    l_ifoProductParams,
                    l_ifoTradedProductUpdqParams,
                    l_blnIsRealPriceIfoDepositCalc,
                    l_blnIfoDepositMailFlag,
                    l_ifoTradedProductParams); 
           
            assertEquals(12345678L, l_product.getProductId());
            assertEquals("123456", l_product.getProductCode());
            assertEquals("0005", l_product.getUnderlyingProductCode());
            assertEquals(
                new IfoDerivativeTypeEnum(1, "FUTURES"), 
                l_product.getIfoDerivativeType());
            assertEquals(1001L, l_product.getMarketId());
            assertEquals(10000L, l_product.getUnitSize(), 0);
            assertEquals(1d, l_product.getCurrentPrice(), 0);
            assertEquals(
                "20080808",
                WEB3DateUtility.formatDate(l_product.getLastTradingDate(), "yyyyMMdd"));
            
            assertEquals(0, l_product.currentBizDateLiquidationPrice, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get先物OP銘柄情報＜立会時間外＞)
    //バッチ終了～市場開局までの場合
    public void testGetOffSessionIfoProduct_C0001()
    {
        final String STR_METHOD_NAME = "testGetOffSessionIfoProduct_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);////////////////////TradedProductRow
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            l_ifoTradedProductParams.setValidForBizDate("20040707");
            l_ifoTradedProductParams.setUnitSize(222L);
            l_ifoTradedProductParams.setLiquidationPrice(22);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);////////////////IfoTradedProductRow

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams =
                TestDBUtility.getProductRow();
            productParams.setProductId(1006160060005L);
            productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(productParams);////////////////////ProductRow

            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060005L);
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setTargetMarketId(3303L);
            l_ifoProductParams.setUnderlyingProductCode("111");
            TestDBUtility.insertWithDel(l_ifoProductParams);  ////////////////////IfoProductRow

            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setUnitSize(333D);
            l_ifoTradedProductUpdqParams.setLiquidationPrice(33);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);///////IfoTradedProductUpdqRow
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams tradingTimeParams = TestDBUtility.getTradingTimeRow();
            tradingTimeParams.setInstitutionCode("0D");
            tradingTimeParams.setBranchCode("123");
            tradingTimeParams.setMarketCode("N1");
            tradingTimeParams.setTradingTimeType("01");
            tradingTimeParams.setProductCode("0");
            tradingTimeParams.setBizDateType("1");
            tradingTimeParams.setBizdateCalcParameter("0");
            tradingTimeParams.setSubmitMarketTrigger("1");
            tradingTimeParams.setEndTime("0");
            TestDBUtility.insertWithDel(tradingTimeParams);

            //引数をセット
            l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060005L);
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setTargetMarketId(3303L);
            l_ifoProductParams.setUnderlyingProductCode("100");
            
            l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            l_ifoTradedProductParams.setValidForBizDate("20040707");
            l_ifoTradedProductParams.setUnitSize(200L);
            l_ifoTradedProductParams.setLiquidationPrice(20);
            
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20040707", "yyyyMMdd");
            
            l_product =
                WEB3IfoProduct.getOffSessionIfoProduct(
                    l_ifoProductParams,
                    l_ifoTradedProductParams,
                    l_datOrderBizDate);
            
            assertEquals("100", "" + l_product.getUnderlyingProductCode());
            assertEquals(222, l_product.getUnitSize(), 0);
            assertEquals(22, l_product.getCurrentPrice(), 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get先物OP銘柄情報＜立会時間外＞)
    //当日清算値が発表済の場合
    public void testGetOffSessionIfoProduct_C0002()
    {
        final String STR_METHOD_NAME = "testGetOffSessionIfoProduct_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);////////////////////TradedProductRow
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            l_ifoTradedProductParams.setValidForBizDate("20040707");
            l_ifoTradedProductParams.setUnitSize(222L);
            l_ifoTradedProductParams.setLiquidationPrice(22);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);////////////////IfoTradedProductRow

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams =
                TestDBUtility.getProductRow();
            productParams.setProductId(1006160060005L);
            productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(productParams);////////////////////ProductRow

            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060005L);
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setTargetMarketId(3303L);
            l_ifoProductParams.setUnderlyingProductCode("111");
            TestDBUtility.insertWithDel(l_ifoProductParams);  ////////////////////IfoProductRow

            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setValidForBizDate("20040707");
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
            l_ifoTradedProductUpdqParams.setUnitSize(333D);
            l_ifoTradedProductUpdqParams.setLiquidationPrice(33);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);///////IfoTradedProductUpdqRow
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams tradingTimeParams = TestDBUtility.getTradingTimeRow();
            tradingTimeParams.setInstitutionCode("0D");
            tradingTimeParams.setBranchCode("123");
            tradingTimeParams.setMarketCode("N1");
            tradingTimeParams.setTradingTimeType("01");
            tradingTimeParams.setProductCode("0");
            tradingTimeParams.setBizDateType("1");
            tradingTimeParams.setBizdateCalcParameter("0");
            tradingTimeParams.setSubmitMarketTrigger("1");
            tradingTimeParams.setEndTime("0");
            TestDBUtility.insertWithDel(tradingTimeParams);

            //引数をセット
            l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060005L);
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setTargetMarketId(3303L);
            l_ifoProductParams.setUnderlyingProductCode("100");
            
            l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            l_ifoTradedProductParams.setValidForBizDate("20040707");
            l_ifoTradedProductParams.setUnitSize(200L);
            l_ifoTradedProductParams.setLiquidationPrice(20);
            
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20040707", "yyyyMMdd");
            
            l_product =
                WEB3IfoProduct.getOffSessionIfoProduct(
                    l_ifoProductParams,
                    l_ifoTradedProductParams,
                    l_datOrderBizDate);
            
            assertEquals("100", "" + l_product.getUnderlyingProductCode());
            assertEquals(333, l_product.getUnitSize(), 0);
            assertEquals(33, l_product.getCurrentPrice(), 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get先物OP銘柄情報＜立会時間外＞)
    //当日清算値が未発表の場合
    public void testGetOffSessionIfoProduct_C0003()
    {
        final String STR_METHOD_NAME = "testGetOffSessionIfoProduct_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);////////////////////TradedProductRow
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            l_ifoTradedProductParams.setValidForBizDate("20040707");
            l_ifoTradedProductParams.setUnitSize(222L);
            l_ifoTradedProductParams.setLiquidationPrice(22);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);////////////////IfoTradedProductRow

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams =
                TestDBUtility.getProductRow();
            productParams.setProductId(1006160060005L);
            productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(productParams);////////////////////ProductRow

            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060005L);
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setTargetMarketId(3303L);
            l_ifoProductParams.setUnderlyingProductCode("111");
            TestDBUtility.insertWithDel(l_ifoProductParams);  ////////////////////IfoProductRow

            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setValidForBizDate("20040707");
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
            l_ifoTradedProductUpdqParams.setUnitSize(333D);
            l_ifoTradedProductUpdqParams.setLiquidationPrice(0D);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);///////IfoTradedProductUpdqRow
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams tradingTimeParams = TestDBUtility.getTradingTimeRow();
            tradingTimeParams.setInstitutionCode("0D");
            tradingTimeParams.setBranchCode("123");
            tradingTimeParams.setMarketCode("N1");
            tradingTimeParams.setTradingTimeType("01");
            tradingTimeParams.setProductCode("0");
            tradingTimeParams.setBizDateType("1");
            tradingTimeParams.setBizdateCalcParameter("0");
            tradingTimeParams.setSubmitMarketTrigger("1");
            tradingTimeParams.setEndTime("0");
            TestDBUtility.insertWithDel(tradingTimeParams);

          WEB3IfoQuoteDataImpl ifoQuoteData = new WEB3IfoQuoteDataImpl();
          ifoQuoteData.setCurrentPrice(40);
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                  "getIfoQuote",
                  new Class[]
                  {IfoTradedProduct.class,RealType.class},
                  ifoQuoteData);
            
            //引数をセット
            l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060005L);
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setTargetMarketId(3303L);
            l_ifoProductParams.setUnderlyingProductCode("100");
            
            l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            l_ifoTradedProductParams.setValidForBizDate("20040707");
            l_ifoTradedProductParams.setUnitSize(200L);
            l_ifoTradedProductParams.setLiquidationPrice(20);
            
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20040707", "yyyyMMdd");
            
            l_product =
                WEB3IfoProduct.getOffSessionIfoProduct(
                    l_ifoProductParams,
                    l_ifoTradedProductParams,
                    l_datOrderBizDate);
            
            assertEquals("100", "" + l_product.getUnderlyingProductCode());
            assertEquals(200, l_product.getUnitSize(), 0);
            assertEquals(40, l_product.getCurrentPrice(), 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //(is先物)
    //this.先物オプション区分==”先物”の場合
    public void testIsFutures_C0001()
    {
        final String STR_METHOD_NAME = "testIsFutures_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_product.setIfoDerivativeType(new IfoDerivativeTypeEnum(1, "FUTURES"));
            
            assertEquals(true, l_product.isFutures());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(is先物)
    //this.先物オプション区分==”先物”以外の場合
    public void testIsFutures_C0002()
    {
        final String STR_METHOD_NAME = "testIsFutures_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_product.setIfoDerivativeType(new IfoDerivativeTypeEnum(1, "CALL_OPTIONS"));
            
            assertEquals(false, l_product.isFutures());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(is先物)
    //this.先物オプション区分==”先物”以外の場合
    public void testIsFutures_C0003()
    {
        final String STR_METHOD_NAME = "testIsFutures_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_product.setIfoDerivativeType(new IfoDerivativeTypeEnum(1, "PUT_OPTIONS"));
            
            assertEquals(false, l_product.isFutures());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(is先物)
    //this.先物オプション区分==”先物”以外の場合
    public void testIsFutures_C0004()
    {
        final String STR_METHOD_NAME = "testIsFutures_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_product.setIfoDerivativeType(new IfoDerivativeTypeEnum(1, "OTHER"));
            
            assertEquals(false, l_product.isFutures());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(is有効ポジション)
    //引数．証拠金計算条件．is証拠金SQ日銘柄ポジション非計上()＝falseの場合、trueを返す。
    public void testIsPosition_C0001()
    {
        final String STR_METHOD_NAME = "testIsPosition_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3IfoDepositCalcCondition l_objIfoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            l_objIfoDepositCalcCondition.setIfodepositNonCalcSqProductFlag(false);
            
            Date[] BizDates = new Date[]{
                WEB3DateUtility.getDate("20080807", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080808", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080811", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080812", "yyyyMMdd")
            };
            l_objIfoDepositCalcCondition.setBizDates(BizDates);
            l_objIfoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            l_product.setLastTradingDate(WEB3DateUtility.getDate("20080806", "yyyyMMdd"));
            
            boolean l_blnIsPosition =l_product.isPosition(l_objIfoDepositCalcCondition);
            assertTrue(l_blnIsPosition);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(is有効ポジション)
    //1）証拠金計算基準日(*1) == 1の場合
    // 1-1）「 営業日[T+0]  <=  売買最終日 + 1営業日 」の場合、trueを返す。
    public void testIsPosition_C0002()
    {
        final String STR_METHOD_NAME = "testIsPosition_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3IfoDepositCalcCondition l_objIfoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            l_objIfoDepositCalcCondition.setIfodepositNonCalcSqProductFlag(true);
            
            Date[] BizDates = new Date[]{
                WEB3DateUtility.getDate("20080807", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080808", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080811", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080812", "yyyyMMdd")
            };
            l_objIfoDepositCalcCondition.setBizDates(BizDates);
            l_objIfoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            l_product.setLastTradingDate(WEB3DateUtility.getDate("20080807", "yyyyMMdd"));
            
            boolean l_blnIsPosition =l_product.isPosition(l_objIfoDepositCalcCondition);
            assertTrue(l_blnIsPosition);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(is有効ポジション)
    //1）証拠金計算基準日(*1) == 1の場合
    // 1-1）「 営業日[T+0]  <=  売買最終日 + 1営業日 」の場合、trueを返す。
    public void testIsPosition_C0003()
    {
        final String STR_METHOD_NAME = "testIsPosition_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3IfoDepositCalcCondition l_objIfoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            l_objIfoDepositCalcCondition.setIfodepositNonCalcSqProductFlag(true);
            
            Date[] BizDates = new Date[]{
                WEB3DateUtility.getDate("20080807", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080808", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080811", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080812", "yyyyMMdd")
            };
            l_objIfoDepositCalcCondition.setBizDates(BizDates);
            l_objIfoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            l_product.setLastTradingDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            boolean l_blnIsPosition =l_product.isPosition(l_objIfoDepositCalcCondition);
            assertTrue(l_blnIsPosition);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(is有効ポジション)
    //1）証拠金計算基準日(*1) == 1の場合
    // 1-2）  1-1）以外の場合、falseを返す。
    public void testIsPosition_C0004()
    {
        final String STR_METHOD_NAME = "testIsPosition_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3IfoDepositCalcCondition l_objIfoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            l_objIfoDepositCalcCondition.setIfodepositNonCalcSqProductFlag(true);
            
            Date[] BizDates = new Date[]{
                WEB3DateUtility.getDate("20080807", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080808", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080811", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080812", "yyyyMMdd")
            };
            l_objIfoDepositCalcCondition.setBizDates(BizDates);
            l_objIfoDepositCalcCondition.setIfoDepositCalcBaseDate(1);
            l_product.setLastTradingDate(WEB3DateUtility.getDate("20080806", "yyyyMMdd"));
            
            boolean l_blnIsPosition =l_product.isPosition(l_objIfoDepositCalcCondition);
            assertFalse(l_blnIsPosition);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(is有効ポジション)
    //2）証拠金計算基準日 != 1の場合
    // 2-1）  「 営業日[T+0]  <=  売買最終日 」の場合の場合、trueを返す。
    public void testIsPosition_C0005()
    {
        final String STR_METHOD_NAME = "testIsPosition_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3IfoDepositCalcCondition l_objIfoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            l_objIfoDepositCalcCondition.setIfodepositNonCalcSqProductFlag(true);
            
            Date[] BizDates = new Date[]{
                WEB3DateUtility.getDate("20080807", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080808", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080811", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080812", "yyyyMMdd")
            };
            l_objIfoDepositCalcCondition.setBizDates(BizDates);
            l_objIfoDepositCalcCondition.setIfoDepositCalcBaseDate(0);
            l_product.setLastTradingDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            boolean l_blnIsPosition =l_product.isPosition(l_objIfoDepositCalcCondition);
            assertTrue(l_blnIsPosition);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(is有効ポジション)
    //2）証拠金計算基準日 != 1の場合
    // 2-1）  「 営業日[T+0]  <=  売買最終日 」の場合の場合、trueを返す。
    public void testIsPosition_C0006()
    {
        final String STR_METHOD_NAME = "testIsPosition_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3IfoDepositCalcCondition l_objIfoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            l_objIfoDepositCalcCondition.setIfodepositNonCalcSqProductFlag(true);
            
            Date[] BizDates = new Date[]{
                WEB3DateUtility.getDate("20080807", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080808", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080811", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080812", "yyyyMMdd")
            };
            l_objIfoDepositCalcCondition.setBizDates(BizDates);
            l_objIfoDepositCalcCondition.setIfoDepositCalcBaseDate(2);
            l_product.setLastTradingDate(WEB3DateUtility.getDate("20080811", "yyyyMMdd"));
            
            boolean l_blnIsPosition =l_product.isPosition(l_objIfoDepositCalcCondition);
            assertTrue(l_blnIsPosition);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(is有効ポジション)
    //2）証拠金計算基準日 != 1の場合
    // 2-2）  2-1）以外の場合、falseを返す。
    public void testIsPosition_C0007()
    {
        final String STR_METHOD_NAME = "testIsPosition_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3IfoDepositCalcCondition l_objIfoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            l_objIfoDepositCalcCondition.setIfodepositNonCalcSqProductFlag(true);
            
            Date[] BizDates = new Date[]{
                WEB3DateUtility.getDate("20080807", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080808", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080811", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080812", "yyyyMMdd")
            };
            l_objIfoDepositCalcCondition.setBizDates(BizDates);
            l_objIfoDepositCalcCondition.setIfoDepositCalcBaseDate(2);
            l_product.setLastTradingDate(WEB3DateUtility.getDate("20080807", "yyyyMMdd"));
            
            boolean l_blnIsPosition =l_product.isPosition(l_objIfoDepositCalcCondition);
            assertFalse(l_blnIsPosition);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //(get時価)
    public void testGetCurrentPriceCase1()
    {
      final String STR_METHOD_NAME = "testGetCurrentPriceCase1()";
      log.entering(STR_METHOD_NAME);
      //  現在値!= 0の場合<BR>
      try
        {
            WEB3IfoQuoteDataImpl l_quoteData = new WEB3IfoQuoteDataImpl();
            l_quoteData.setCurrentPrice(10);
            l_quoteData.setBidPrice(20);
            l_quoteData.setAskPrice(30);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getIfoQuote",
                    new Class[] {IfoTradedProduct.class,RealType.class}, 
                    l_quoteData);
            
            IfoTradedProductImplForTest l_ifoTradedProduct = new IfoTradedProductImplForTest();
            Double l_dblLiquidationPriceToday = new Double(40);

            Method l_method =
                WEB3IfoProduct.class.getDeclaredMethod(
                    "getCurrentPrice", 
                    new Class[]{IfoTradedProduct.class,Double.class});
            l_method.setAccessible(true);

            Double l_dblCurrentPrice = (Double)l_method.invoke(l_product, new Object[]{l_ifoTradedProduct, l_dblLiquidationPriceToday});
            assertEquals(10, l_dblCurrentPrice.doubleValue(), 0);
            }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(get時価)
    //売気配値!= 0の場合<BR>
    public void testGetCurrentPriceCase2()
    {
      final String STR_METHOD_NAME = "testGetCurrentPriceCase2()";
      log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            WEB3IfoQuoteDataImpl l_quoteData = new WEB3IfoQuoteDataImpl();
            l_quoteData.setCurrentPrice(0);
            l_quoteData.setBidPrice(20);
            l_quoteData.setAskPrice(30);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService",
                "getIfoQuote",
                new Class[] {IfoTradedProduct.class, RealType.class},
                l_quoteData);
            
            IfoTradedProductImplForTest l_ifoTradedProduct = new IfoTradedProductImplForTest();
            Double l_dblLiquidationPriceToday = new Double(40);

            Method l_method =
                WEB3IfoProduct.class.getDeclaredMethod(
                    "getCurrentPrice", 
                    new Class[]{IfoTradedProduct.class,Double.class});
            l_method.setAccessible(true);

            Double l_dblCurrentPrice = (Double)l_method.invoke(l_product, new Object[]{l_ifoTradedProduct, l_dblLiquidationPriceToday});
            assertEquals(20, l_dblCurrentPrice.doubleValue(), 0);
            }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(get時価)
    //買気配値!= 0の場合<BR>
    public void testGetCurrentPriceCase3()
    {
      final String STR_METHOD_NAME = "testGetCurrentPriceCase3()";
      log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoQuoteDataImpl l_quoteData = new WEB3IfoQuoteDataImpl();
            l_quoteData.setCurrentPrice(0);
            l_quoteData.setBidPrice(0);
            l_quoteData.setAskPrice(30);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getIfoQuote",
                    new Class[] {IfoTradedProduct.class,RealType.class}, 
                    l_quoteData);
            
            IfoTradedProductImplForTest l_ifoTradedProduct = new IfoTradedProductImplForTest();
            Double l_dblLiquidationPriceToday = new Double(40);

            Method l_method =
                WEB3IfoProduct.class.getDeclaredMethod(
                    "getCurrentPrice", 
                    new Class[]{IfoTradedProduct.class,Double.class});
            l_method.setAccessible(true);

            Double l_dblCurrentPrice = (Double)l_method.invoke(l_product, new Object[]{l_ifoTradedProduct, l_dblLiquidationPriceToday});
            assertEquals(30, l_dblCurrentPrice.doubleValue(), 0);
            }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(get時価)
    public void testGetCurrentPriceCase4()
    {
      final String STR_METHOD_NAME = "testGetCurrentPriceCase4()";
      log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoQuoteDataImpl l_quoteData = new WEB3IfoQuoteDataImpl();
            l_quoteData.setCurrentPrice(0);
            l_quoteData.setBidPrice(0);
            l_quoteData.setAskPrice(0);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getIfoQuote",
                    new Class[] {IfoTradedProduct.class,RealType.class}, 
                    l_quoteData);
            
            IfoTradedProductImplForTest l_ifoTradedProduct = new IfoTradedProductImplForTest();
            Double l_dblLiquidationPriceToday = new Double(40);



            Method l_method =
                WEB3IfoProduct.class.getDeclaredMethod(
                    "getCurrentPrice", 
                    new Class[]{IfoTradedProduct.class,Double.class});
            l_method.setAccessible(true);

            Double l_dblCurrentPrice = (Double)l_method.invoke(l_product, new Object[]{l_ifoTradedProduct, l_dblLiquidationPriceToday});
            assertEquals(40, l_dblCurrentPrice.doubleValue(), 0);
            
            }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(get時価)
    public void testGetCurrentPriceCase5()
    {
      final String STR_METHOD_NAME = "testGetCurrentPriceCase5()";
      log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoQuoteDataImpl l_quoteData = new WEB3IfoQuoteDataImpl();
            l_quoteData.setCurrentPrice(0);
            l_quoteData.setBidPrice(0);
            l_quoteData.setAskPrice(0);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getIfoQuote",
                    new Class[] {IfoTradedProduct.class,RealType.class}, 
                    l_quoteData);

            Method l_method =
                WEB3IfoProduct.class.getDeclaredMethod(
                    "getCurrentPrice", 
                    new Class[]{IfoTradedProduct.class,Double.class});
            l_method.setAccessible(true);

            IfoTradedProductImplForTest l_ifoTradedProduct = new IfoTradedProductImplForTest();
            l_ifoTradedProduct.setFlag(true);
            Double l_dblCurrentPrice = (Double)l_method.invoke(l_product, new Object[]{l_ifoTradedProduct, null});
            
            assertEquals(41, l_dblCurrentPrice.doubleValue(), 0);
            
            }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(get時価)
    public void testGetCurrentPriceCase6()
    {
      final String STR_METHOD_NAME = "testGetCurrentPriceCase6()";
      log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoQuoteDataImpl l_quoteData = new WEB3IfoQuoteDataImpl();
            l_quoteData.setCurrentPrice(0);
            l_quoteData.setBidPrice(0);
            l_quoteData.setAskPrice(0);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getIfoQuote",
                    new Class[] {IfoTradedProduct.class,RealType.class}, 
                    l_quoteData);
            
            Method l_method =
                WEB3IfoProduct.class.getDeclaredMethod(
                    "getCurrentPrice", 
                    new Class[]{IfoTradedProduct.class,Double.class});
            l_method.setAccessible(true);

            IfoTradedProductImplForTest l_ifoTradedProduct = new IfoTradedProductImplForTest();
            l_ifoTradedProduct.setFlag(false);
            Double l_dblCurrentPrice = (Double)l_method.invoke(l_product, new Object[]{l_ifoTradedProduct, null});
            
            assertEquals(42, l_dblCurrentPrice.doubleValue(), 0);
            
            }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    public Date lastTradingDate;  
    
    class WEB3IfoProductForTest extends WEB3IfoProduct
    {
        public void setIfoProductWithCurrentPriceEvening(
            IfoProductParams l_ifoProductParams,
            IfoTradedProductParams l_ifoTradedProductParams,
            boolean l_blnIfoDepositMailFlag,
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams)
        {
            System.out.println("hahahahaahhfhahfahfhfhhffkfaffkfjf");
        }

    }

    private class IfoTradedProductImplForTest implements IfoTradedProduct
    {

        private boolean flag;
        public double getLastClosingPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getUnitSize()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getPerOrderMaxUnits()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getTickValueUnit(double arg0)
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public boolean isValidPriceAsPerTickValue(double arg0)
        {
            // TODO Auto-generated method stub
            return false;
        }

        public Date getStartTradingDate()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Date getLastTradingDate()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isTradingStopped()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isBuyToOpenOrdersStopped()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isSellToOpenOrdersStopped()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isSellToCloseOrdersStopped()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isBuyToCloseOrdersStopped()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getLimitPriceRange()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getStopHighPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getStopLowPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getTradedProductId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Product getProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Market getMarket()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isTradingSuspended()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getMarginRatio()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Date getBaseDate()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Date getDailyDeliveryDate()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isCollateralizable()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public Institution getInstitution()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Object getDataSourceObject()
        {
            if (flag)
            {
                IfoTradedProductUpdqParams updqParams = TestDBUtility.getIfoTradedProductUpdqRow();
                updqParams.setLiquidationPrice(41d);
                // TODO Auto-generated method stub
                return (IfoTradedProductUpdqRow)updqParams;
            }
            else
            {
                IfoTradedProductParams productParams = TestDBUtility.getIfoTradedProductRow();
                productParams.setLiquidationPrice(42d);
                // TODO Auto-generated method stub
                return (IfoTradedProductRow)productParams;
            }
        }

        public boolean isFlag()
        {
            return flag;
        }

        public void setFlag(boolean flag)
        {
            this.flag = flag;
        }
        
    }
    
    private class WEB3IfoQuoteDataImpl implements WEB3IfoQuoteData
    {
        public double currentPrice;
        public double bidPrice;
        public double askPrice;
        
        public void setCurrentPrice(double l_dblCurrentPrice)
        {
            currentPrice = l_dblCurrentPrice;
        }
        public void setBidPrice(double l_dblBidPrice)
        {
            bidPrice = l_dblBidPrice;
        }
        public void setAskPrice(double l_dblAskPrice)
        {
            askPrice = l_dblAskPrice;
        }
        public double getCurrentPrice()
        {
            // TODO Auto-generated method stub
            return currentPrice;
        }

        public double getBidPrice()
        {
            // TODO Auto-generated method stub
            return bidPrice;
        }

        public double getAskPrice()
        {
            // TODO Auto-generated method stub
            return askPrice;
        }

        public double getOpenPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getLastClosingPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Timestamp getQuoteTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Date getQuoteDate()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public RealType getRealType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public DataType getDataType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public String getMarketCode()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public String getProductCode()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public String getMonthOfDelivery()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public PutAndCall getPutAndCall()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public double getStrikePrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Timestamp getOpenPriceTime()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public double getHighPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Timestamp getHighPriceTime()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public double getLowPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Timestamp getLowPriceTime()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Timestamp getCurrentPriceTime()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public CurrentPriceFlag getCurrentPriceFlag()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public double getChange()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getVolume()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Timestamp getVolumeTime()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public AskPriceTitle getAskPriceTitle()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Timestamp getAskPriceTime()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public BidPriceTitle getBidPriceTitle()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Timestamp getBidPriceTime()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public double getBasePrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }
        
    }
}

@

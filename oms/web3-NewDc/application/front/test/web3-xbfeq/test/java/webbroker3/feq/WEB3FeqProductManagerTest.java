head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.55.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqProductManagerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductParams;

import test.util.TestDBUtility;

import webbroker3.gentrade.data.FeqLastClosingPriceParams;
import webbroker3.gentrade.data.FeqLastClosingPriceRow;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.quoteadaptor.RealType;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqProductManagerTest extends TestBaseForMock {

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3FeqProductManagerTest.class);
    
    
    WEB3FeqProductManager l_manager = new WEB3FeqProductManager();
    
	public WEB3FeqProductManagerTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

    public void testGetCurrency_case0001()
    {
        final String STR_METHOD_NAME = "testGetCurrency_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);

            WEB3FeqProductManager l_feqProductManager = new WEB3FeqProductManager();
            assertNull(l_feqProductManager.getCurrency("0D"));
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testGetCurrency_case0002()
    {
        final String STR_METHOD_NAME = "testGetCurrency_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("001");
            l_genCurrencyParams.setCurrencyName("test");
            TestDBUtility.insertWithDel(l_genCurrencyParams);

            WEB3FeqProductManager l_feqProductManager = new WEB3FeqProductManager();
            assertNotNull(l_feqProductManager.getCurrency("0D"));
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testGetCurrency_case0003()
    {
        final String STR_METHOD_NAME = "testGetCurrency_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("001");
            l_genCurrencyParams.setCurrencyName("test");
            TestDBUtility.insertWithDel(l_genCurrencyParams);

            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("002");
            l_genCurrencyParams.setCurrencyName("test");
            TestDBUtility.insertWithDel(l_genCurrencyParams);

            WEB3FeqProductManager l_feqProductManager = new WEB3FeqProductManager();
            assertEquals(2, l_feqProductManager.getCurrency("0D").length);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }

	public void testGetCurrentPriceUnit_T001()
	{
		final String STR_METHOD_NAME = "testGetCurrentPriceUnit_T001()";
		log.entering(STR_METHOD_NAME);
		try
		{
			//FeqTradedProductParams
			TestDBUtility.deleteAll(FeqTradedProductParams.TYPE);
			FeqTradedProductParams l_feqTradedProductParams = TestDBUtility.getFeqTradedProductRow();
			
			TestDBUtility.insertWithDel(l_feqTradedProductParams);
			
			//TradedProductParams
			TestDBUtility.deleteAll(TradedProductParams.TYPE);
			TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
			l_tradedProductParams.setTradedProductId(l_feqTradedProductParams.getTradedProductId());
			TestDBUtility.insertWithDel(l_tradedProductParams);
			
			//MarketParams
			TestDBUtility.deleteAll(MarketParams.TYPE);
			MarketParams l_marketParams = TestDBUtility.getMarketRow();
			TestDBUtility.insertWithDel(l_marketParams);
			
			//FeqLastClosingPriceRow
			TestDBUtility.deleteAll(FeqLastClosingPriceRow.TYPE);

			
			WEB3FeqTradedProduct l_feqTradedProduct = new WEB3FeqTradedProduct(l_feqTradedProductParams);
			WEB3FeqProductQuote l_productQuote =
				l_manager.getCurrentPriceUnit(l_feqTradedProduct, RealType.CLOSING_PRICE);
			
			
		   assertNull(l_productQuote);
			   
		}
		catch(Exception l_ex)
		{
			log.error(l_ex.getMessage(),l_ex);
			fail();
		}
		log.exiting(STR_METHOD_NAME);
	}
	
	
	public void testGetCurrentPriceUnit_T002()
	{
		final String STR_METHOD_NAME = "testGetCurrentPriceUnit_T002()";
		log.entering(STR_METHOD_NAME);
		try
		{
//			FeqTradedProductParams
			TestDBUtility.deleteAll(FeqTradedProductParams.TYPE);
			FeqTradedProductParams l_feqTradedProductParams = TestDBUtility.getFeqTradedProductRow();
			
			l_feqTradedProductParams.setLastClosingPrice(12.345);
			
			TestDBUtility.insertWithDel(l_feqTradedProductParams);
			
			//TradedProductParams
			TestDBUtility.deleteAll(TradedProductParams.TYPE);
			TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
			l_tradedProductParams.setTradedProductId(l_feqTradedProductParams.getTradedProductId());
			TestDBUtility.insertWithDel(l_tradedProductParams);
			
			//MarketParams
			TestDBUtility.deleteAll(MarketParams.TYPE);
			MarketParams l_marketParams = TestDBUtility.getMarketRow();
			TestDBUtility.insertWithDel(l_marketParams);
			
			//FeqLastClosingPriceRow
			TestDBUtility.deleteAll(FeqLastClosingPriceRow.TYPE);

			FeqLastClosingPriceRow l_FeqLastClosingPriceRow = WEB3FeqProductManagerTest.getFeqLastClosingPriceRow();
			TestDBUtility.insertWithDel(l_FeqLastClosingPriceRow);
			
			
			WEB3FeqTradedProduct l_feqTradedProduct = new WEB3FeqTradedProduct(l_feqTradedProductParams);
			WEB3FeqProductQuote l_productQuote =
				l_manager.getCurrentPriceUnit(l_feqTradedProduct, RealType.CLOSING_PRICE);
			
			assertNull(l_productQuote);
			
		}
		catch(Exception l_ex)
		{
			log.error(l_ex.getMessage(),l_ex);
			fail();
			
		}
		log.exiting(STR_METHOD_NAME);

	}
	
	public void testGetCurrentPriceUnit_T003()
	{
		final String STR_METHOD_NAME = "testGetCurrentPriceUnit_T003()";
		log.entering(STR_METHOD_NAME);
		try
		{
//			FeqTradedProductParams
			TestDBUtility.deleteAll(FeqTradedProductParams.TYPE);
			FeqTradedProductParams l_feqTradedProductParams = TestDBUtility.getFeqTradedProductRow();
			
			l_feqTradedProductParams.setLastClosingPrice(12.345);
			
			TestDBUtility.insertWithDel(l_feqTradedProductParams);
			
			//TradedProductParams
			TestDBUtility.deleteAll(TradedProductParams.TYPE);
			TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
			l_tradedProductParams.setTradedProductId(l_feqTradedProductParams.getTradedProductId());
            l_tradedProductParams.setBaseDate(new Date(2008-1900,0,5));
			TestDBUtility.insertWithDel(l_tradedProductParams);
			
			//MarketParams
			TestDBUtility.deleteAll(MarketParams.TYPE);
			MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_feqTradedProductParams.getMarketId());
			TestDBUtility.insertWithDel(l_marketParams);
            
            //GenCurrencyParams
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams  = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("001");
            TestDBUtility.insertWithDel(l_genCurrencyParams);
			
			//FeqLastClosingPriceRow
			TestDBUtility.deleteAll(FeqLastClosingPriceParams.TYPE);
			FeqLastClosingPriceParams l_feqLastClosingPriceParams = this.getFeqLastClosingPriceRow();
            l_feqLastClosingPriceParams.setProductId(l_feqTradedProductParams.getProductId());
            l_feqLastClosingPriceParams.setBaseDate(new Date(2008-1900,0,5));
            l_feqLastClosingPriceParams.setFeqClosingPriceMrktCodeS(l_marketParams.getMarketCode());
            TestDBUtility.insertWithDel(l_feqLastClosingPriceParams);
			
			WEB3FeqTradedProduct l_feqTradedProduct = new WEB3FeqTradedProduct(l_feqTradedProductParams);
			WEB3FeqProductQuote l_productQuote =
				l_manager.getCurrentPriceUnit(l_feqTradedProduct, RealType.CLOSING_PRICE);
			
			assertEquals(11.02,l_productQuote.getCurrentPrice(),0);
            assertEquals(l_feqTradedProduct.getBaseDate(),l_productQuote.getCurrentPricePublicTime());
			
		}
		catch(Exception l_ex)
		{
			log.error(l_ex.getMessage(),l_ex);
			fail();
			
		}
		log.exiting(STR_METHOD_NAME);

	}
	
	
	
	
	 public static FeqLastClosingPriceParams getFeqLastClosingPriceRow()
	    {
		 FeqLastClosingPriceParams l_feqLastClosingPriceParams = new FeqLastClosingPriceParams();
	    	
		
	    //銘柄ＩＤ product_id 	NUMBER 	18 NOT NULL
		 l_feqLastClosingPriceParams.setProductId(1006160060005L);
		 
	    //基準日 base_date 	DATE  	NOT NULL
		 l_feqLastClosingPriceParams.setBaseDate(new Date(20080212));
	    //外株終値 feq_closing_price 	NUMBER 	18	12	6	NULL
		 l_feqLastClosingPriceParams.setFeqClosingPrice(11.02);
	    //市場コード feq_closing_price_market_code 	VARCHAR2 	2 NOT NULL
		 l_feqLastClosingPriceParams.setFeqClosingPriceMarketCode("SP");
	    //作成日付 created_timestamp 	DATE  	NULL
		 l_feqLastClosingPriceParams.setCreatedTimestamp(new Date());
         l_feqLastClosingPriceParams.setOffshoreProductCode("123456");
         l_feqLastClosingPriceParams.setFeqClosingPriceMrktCodeS("11");
	    //更新日付 last_updated_timestamp 	DATE  	NULL
		 l_feqLastClosingPriceParams.setLastUpdatedTimestamp(new Date());
		 
	    	
	    	return l_feqLastClosingPriceParams;
	    }
}
@

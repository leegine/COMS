head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.38.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPSecurityValuationTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券評価額Test(WEB3TPSecurityValuationTest)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/08/01 鄧鋒鋼(中訊) 新規作成   
*/
package webbroker3.tradingpower.updtpower.asset;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import test.util.TestDBUtility;

import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.DailyAssetParams;
import webbroker3.gentrade.data.DailyAssetRow;
import webbroker3.gentrade.data.ExchangeRateParams;
import webbroker3.gentrade.data.ExchangeRateRow;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.gentrade.data.GenCurrencyRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPUnitPriceCallback;
import webbroker3.tradingpower.updtpower.WEB3TPUnitPriceStandardCallback;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPSecurityValuationTest extends TestBaseForMock 
{

	private WEB3TPSecurityValuation l_common = null ;
	
	private boolean l_blnIsValuation = false;

    private String methodName = null;

    private static WEB3LogUtility log = 
    	WEB3LogUtility.getInstance(WEB3TPSecurityValuationTest.class);
	
	public WEB3TPSecurityValuationTest(String name) {
		super(name);
	}
	
	protected void setUp() throws Exception
    {
        super.setUp();
        this.l_common = new WEB3TPSecurityValuationForTest();
        methodName = null;
    }
    
    protected void tearDown() throws Exception
    {
    	this.l_common = null;
    	this.l_blnIsValuation = false;
    	super.tearDown();
    }

//	public void testCalcPrevPriceSubstituteValuation_0001()
//	{
//		final String STR_METHOD_NAME = ".testCalcPrevPriceSubstituteValuation_0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//        	this.l_blnIsValuation = true;
//        	double l_dblResult = l_common.calcPrevPriceSubstituteValuation();
//        	
//        	assertEquals(6.0D, l_dblResult,1);
//        }
//        catch(Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//               
//        log.exiting(TEST_END + STR_METHOD_NAME);
//	}
    

//    public void testCalcPrevPricePerProductSubstituteValuation_0001()
//	{
//       
//        final String STR_METHOD_NAME = ".testCalcPrevPricePerProductSubstituteValuation_0001()";
//		log.entering(TEST_START + STR_METHOD_NAME);
//		
//		try
//		{
//			WEB3TPSecurityValuationProduct l_product = new WEB3TPSecurityValuationProduct();
//			WEB3TPSecurityValuationPerProduct l_valuation = new WEB3TPSecurityValuationPerProductForTest(); 
//			l_product.setProductType(ProductTypeEnum.EQUITY);
//			l_product.setMiniStockDivDef("0");
//			l_common.addSecurityValuationPerProduct(l_product,l_valuation);
//			double l_dblResult = l_common.calcPrevPricePerProductSubstituteValuation(ProductTypeEnum.EQUITY);
//			
//			assertEquals(500.25, l_dblResult, 2);
//		}
//		catch(Exception l_ex)
//		{
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//		}
//		
//		log.exiting(TEST_END + STR_METHOD_NAME);
//	}
    
//    public void testCalcPrevPricePerProductSubstituteValuation_0002()
//	{
//       
//        final String STR_METHOD_NAME = ".testCalcPrevPricePerProductSubstituteValuation_0002()";
//		log.entering(TEST_START + STR_METHOD_NAME);
//		
//		try
//		{
//			WEB3TPSecurityValuationProduct l_product = new WEB3TPSecurityValuationProduct();
//			WEB3TPSecurityValuationPerProduct l_valuation = new WEB3TPSecurityValuationPerProductForTest(); 
//			l_product.setProductType(ProductTypeEnum.EQUITY);
//			l_product.setMiniStockDivDef("0");
//			l_common.addSecurityValuationPerProduct(l_product,l_valuation);
//			double l_dblResult = l_common.calcPrevPricePerProductSubstituteValuation(ProductTypeEnum.BOND);
//			
//			assertEquals(0.0, l_dblResult, 2);
//		}
//		catch(Exception l_ex)
//		{
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//		}
//		
//		log.exiting(TEST_END + STR_METHOD_NAME);
//	}
    
    public void testLoadProduct_0001()
    {
    	final String STR_METHOD_NAME = ".testLoadProduct_0001";
		log.entering(TEST_START + STR_METHOD_NAME);
		try
		{
			WEB3TPSecurityValuationProduct l_Result = null;
		    TestDBUtility.deleteAll(ProductRow.TYPE);
		    TestDBUtility.insertWithDel(TestDBUtility.getProductRow());
		    l_Result = l_common.loadProduct(3304148080000L,"dsds");
		
		    assertEquals("dsds",l_Result.getMiniStockDivDef());
		}
		catch(Exception l_ex)
		{
            log.error(STR_METHOD_NAME, l_ex);
            fail();
		}
		log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
	private class WEB3TPSecurityValuationForTest extends WEB3TPSecurityValuation
	{
//		protected double  calcPrevPricePerProductSubstituteValuation(ProductTypeEnum l_productType)
//		{
//			if(l_blnIsValuation)
//			{
//				switch (l_productType.intValue())
//				{
//				case 1:
//					return 1.0D;
//			    case 2:
//				    return 2.0D;
//			    case 3:
//				    return 3.0D;
//			    case 0:
//				    return 0.0D;
//				}
//				return 0.0D;		
//			}
//			return super.calcPrevPricePerProductSubstituteValuation(l_productType);
//		}
		
		public WEB3TPCalcCondition getCalcCondition()
		{
			WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcConditionForTest();
			WEB3TPUnitPriceCallback l_unitPriceCallback = new WEB3TPUnitPriceStandardCallback(l_calcCondition);
			l_calcCondition.setUnitPriceCallback(l_unitPriceCallback);
			return l_calcCondition;
		}

        protected boolean isLoadedProduct(long l_lngProductId, String l_strMiniStockDivDef)
        {
            if ("testDoAssetChangesLoad_C001()".equals(methodName) ||
                "testDoAssetChangesLoad_C002()".equals(methodName))
            {
                return false;
            }
            else
            {
                return super.isLoadedProduct(l_lngProductId, l_strMiniStockDivDef);
            }
        }

        protected WEB3TPSecurityValuationProduct loadProduct(long l_lngProductId, String l_strMiniStockDivDef)
        {
            if ("testDoAssetChangesLoad_C001()".equals(methodName))
            {
                WEB3TPSecurityValuationProduct l_product = new WEB3TPSecurityValuationProduct();
                l_product.setSubstituteValuationRatio(1.0d);
                l_product.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
                l_product.setUnitPrice(2.0d);
                l_product.setUnitSize(3.0d);
                l_product.setProductId(3304148080000L);
                l_product.setMiniStockDivDef("123");
                return l_product;
            }
            else if ("testDoAssetChangesLoad_C002()".equals(methodName))
            {
                WEB3TPSecurityValuationProduct l_product = new WEB3TPSecurityValuationProduct();
                l_product.setSubstituteValuationRatio(1.0d);
                l_product.setProductType(ProductTypeEnum.MUTUAL_FUND);
                l_product.setUnitPrice(2.0d);
                l_product.setUnitSize(3.0d);
                l_product.setProductId(0100000L);
                l_product.setMiniStockDivDef("123");
                return l_product;
            }
            else
            {
                return super.loadProduct(l_lngProductId, l_strMiniStockDivDef);
            }
        }

    }
    public class WEB3TPCalcConditionForTest extends WEB3TPCalcCondition
    {
        public String getInstBranCalcCondition(String l_strName)
        {
            return "1";
        }
    }
    /**
     * 評価額的計算結果小數點後第7位是4且つ第1位是4的場合
     */
    public void testCalcMutualFundValuationPricePerChange_0001()
    {
        final String STR_METHOD_NAME = "testCalcMutualFundValuationPricePerChange_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3TPSecurityValuationProduct l_product = new WEB3TPSecurityValuationProduct();
            l_product.setUnitSize(2);
            l_product.setProductId(3211584215L);
            WEB3TPSecurityChange l_change = new WEB3TPSecurityTransactionChange();
            l_change.setQuantity(2);
            l_change.setUnitPrice(5.4999994);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            
            l_common = new WEB3TPSecurityValuation();
            double l_dblResult = l_common.calcMutualFundValuationPricePerChange(l_product, l_change);
            assertEquals("5.0", l_dblResult + "");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 評価額的計算結果小數點後第7位是5且つ第1位是5的場合<BR>
     * 
     */
    public void testCalcMutualFundValuationPricePerChange_0002()
    {
        final String STR_METHOD_NAME = "testCalcMutualFundValuationPricePerChange_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3TPSecurityValuationProduct l_product = new WEB3TPSecurityValuationProduct();
            l_product.setUnitSize(2);
            l_product.setProductId(3211584215L);
            WEB3TPSecurityChange l_change = new WEB3TPSecurityTransactionChange();
            l_change.setQuantity(2);
            l_change.setUnitPrice(5.4999995);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            
            l_common = new WEB3TPSecurityValuation();
            double l_dblResult = l_common.calcMutualFundValuationPricePerChange(l_product, l_change);
            assertEquals("6.0", l_dblResult + "");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 投信銘柄マスタが検索が存在する場合、<BR>
     * 且つ投信銘柄マスタが検索通貨コードが設定されている場合、<BR>
     * 且つ為替レートRowオブジェクトを取得できる場合<BR>
     * 且つ評価額的計算結果小數點後第1位>=5的場合<BR>
     *
     */
    public void testCalcMutualFundValuationPricePerChange_0003()
    {
        final String STR_METHOD_NAME = "testCalcMutualFundValuationPricePerChange_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3TPSecurityValuationProduct l_product = new WEB3TPSecurityValuationProduct();
            l_product.setUnitSize(1);
            l_product.setProductId(3211584215L);
            WEB3TPSecurityChange l_change = new WEB3TPSecurityTransactionChange();
            l_change.setQuantity(1);
            l_change.setUnitPrice(1);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(3211584215L);
            l_mutualFundProductParams.setCurrencyCode("01");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            
            TestDBUtility.deleteAll(ExchangeRateRow.TYPE);
            ExchangeRateParams l_exchangeRateParams = TestDBUtility.getExchangeRateRow();
            l_exchangeRateParams.setInstitutionCode("0D");
            l_exchangeRateParams.setCurrencyCode("01");
            l_exchangeRateParams.setTtBuyingRate(5.4999995);
            l_exchangeRateParams.setExchangeCalcUnit(1);
            TestDBUtility.insertWithDel(l_exchangeRateParams);
          
            l_common = new WEB3TPSecurityValuationForTest();
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setInstitutionCode("0D");
            l_common.setAccountInfo(l_accountInfo);
            
            double l_dblResult = l_common.calcMutualFundValuationPricePerChange(l_product, l_change);
            assertEquals("6.0", l_dblResult + "");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 投信銘柄マスタが検索が存在する場合、<BR>
     * 且つ投信銘柄マスタが検索通貨コードが設定されている場合、<BR>
     * 且つ為替レートRowオブジェクトを取得できる場合<BR>
     * 且つ評価額的計算結果小數點後第1位<5的場合<BR>
     *
     */
    public void testCalcMutualFundValuationPricePerChange_0004()
    {
        final String STR_METHOD_NAME = "testCalcMutualFundValuationPricePerChange_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3TPSecurityValuationProduct l_product = new WEB3TPSecurityValuationProduct();
            l_product.setUnitSize(1);
            l_product.setProductId(3211584215L);
            WEB3TPSecurityChange l_change = new WEB3TPSecurityTransactionChange();
            l_change.setQuantity(1);
            l_change.setUnitPrice(1);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(3211584215L);
            l_mutualFundProductParams.setCurrencyCode("01");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            
            TestDBUtility.deleteAll(ExchangeRateRow.TYPE);
            ExchangeRateParams l_exchangeRateParams = TestDBUtility.getExchangeRateRow();
            l_exchangeRateParams.setInstitutionCode("0D");
            l_exchangeRateParams.setCurrencyCode("01");
            l_exchangeRateParams.setTtBuyingRate(5.4999994);
            l_exchangeRateParams.setExchangeCalcUnit(1);
            TestDBUtility.insertWithDel(l_exchangeRateParams);
          
            l_common = new WEB3TPSecurityValuationForTest();
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setInstitutionCode("0D");
            l_common.setAccountInfo(l_accountInfo);
            
            double l_dblResult = l_common.calcMutualFundValuationPricePerChange(l_product, l_change);
            assertEquals("5.0", l_dblResult + "");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(*)分岐フロー
    //外貨投信評価非計上(*) = 「1：実施」 且つ 投信銘柄マスタRow.投信タイプ = 「2：国外」 の場合
    //(*)部店用プリファ@レンステーブル.外貨投信評価非計上 の設定値
    //”0”を返却する。
    public void testCalcMutualFundValuationPricePerChange_0005()
    {
        final String STR_METHOD_NAME = "testCalcMutualFundValuationPricePerChange_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3TPSecurityValuationProduct l_product = new WEB3TPSecurityValuationProduct();
            l_product.setUnitSize(1);
            l_product.setProductId(3211584215L);
            WEB3TPSecurityChange l_change = new WEB3TPSecurityTransactionChange();
            l_change.setQuantity(1);
            l_change.setUnitPrice(1);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_BranchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_BranchPreferencesParams.setBranchId(1231);
            l_BranchPreferencesParams.setNameSerialNo(1);
            l_BranchPreferencesParams.setValue("1");
            l_BranchPreferencesParams.setName("fn.mutualfund.valuationprice.non.calc");
            TestDBUtility.insertWithDel(l_BranchPreferencesParams);
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(3211584215L);
            l_mutualFundProductParams.setCurrencyCode("01");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            
            TestDBUtility.deleteAll(ExchangeRateRow.TYPE);
            ExchangeRateParams l_exchangeRateParams = TestDBUtility.getExchangeRateRow();
            l_exchangeRateParams.setInstitutionCode("0D");
            l_exchangeRateParams.setCurrencyCode("01");
            l_exchangeRateParams.setTtBuyingRate(5.4999995);
            l_exchangeRateParams.setExchangeCalcUnit(1);
            TestDBUtility.insertWithDel(l_exchangeRateParams);
          
            l_common = new WEB3TPSecurityValuationForTest();
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setInstitutionCode("0D");
            l_accountInfo.setBranchId(1231);
            l_common.setAccountInfo(l_accountInfo);
            
            double l_dblResult = l_common.calcMutualFundValuationPricePerChange(l_product, l_change);
            assertEquals("0.0", l_dblResult + "");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
	private class WEB3TPSecurityValuationPerProductForTest extends WEB3TPSecurityValuationPerProduct
	{
	    public double getPrevPriceSubstituteValuation()
	    {
	    	return 500.25;
	    }
	}

    /**
     * 外国株式の場合
     */
    public void testDoAssetChangesLoad_C001()
    {
        final String STR_METHOD_NAME = "testDoAssetChangesLoad_C001()";
        methodName = STR_METHOD_NAME;
        log.entering(STR_METHOD_NAME);

        try
        {
            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(DailyAssetRow.TYPE);
            DailyAssetParams l_dailyAssetParams = new DailyAssetParams();
            l_dailyAssetParams.setAccountId(333812512246L);
            l_dailyAssetParams.setSubAccountId(33381251220301L);
            l_dailyAssetParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_dailyAssetParams.setQuantity(0.0);
            l_dailyAssetParams.setProductId(0L);
            l_dailyAssetParams.setTaxType(TaxTypeEnum.NORMAL);
            l_dailyAssetParams.setDeliveryDate(WEB3DateUtility.getDate("20080123", "yyyyMMdd"));
            l_dailyAssetParams.setMiniStockDiv(BooleanEnum.TRUE);
            l_dailyAssetParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_dailyAssetParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_dailyAssetParams.setSplitNewStockDiv("1");
            l_dailyAssetParams.setOriginalExecDate(WEB3DateUtility.getDate("20080124", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_dailyAssetParams);

            l_processor.doDeleteAllQuery(FeqProductRow.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setCurrencyCode("132");
            TestDBUtility.insertWithDel(l_feqProductParams);

            l_processor.doDeleteAllQuery(GenCurrencyRow.TYPE);
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setCurrentSellExecRate(4.0d);
            TestDBUtility.insertWithDel(l_genCurrencyParams);

            l_processor.doDeleteAllQuery(AssetRow.TYPE);

            //パラメータの準備
            l_common = new WEB3TPSecurityValuationForTest();
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(333812512246L);

            Hashtable l_hashtable = new Hashtable();
            l_hashtable.put(new Long(33381251220301L), SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_accountInfo.setSubAccountIds(l_hashtable);
            l_accountInfo.setInstitutionCode("123");
            l_common.setAccountInfo(l_accountInfo);

            //実際のメソッドを実行
            l_common.doAssetChangesLoad();

            //比較
            WEB3TPSecurityValuationProduct l_product = l_common.getProduct(3304148080000L, "123");
            WEB3TPSecurityValuationPerProduct l_valuationProduct = l_common.getSecurityValuationPerProduct(l_product);
            List l_lisChanges = l_valuationProduct.getAssetChanges("1");
            WEB3TPSecurityAssetChange l_change = (WEB3TPSecurityAssetChange)l_lisChanges.get(0);
            assertTrue(l_change.isSplitNewStock());
            assertEquals(l_change.getDepositType(), "1");
            assertEquals(
                WEB3DateUtility.compareToDay(
                    l_change.getOriginalExecDate(),
                    WEB3DateUtility.getDate("20080124","yyyyMMdd")), 0);
            assertEquals(
                WEB3DateUtility.compareToDay(
                    l_change.getReflectStartDay(),
                    WEB3DateUtility.getDate("20080124","yyyyMMdd")), 0);
            assertEquals(
                WEB3DateUtility.compareToDay(
                    l_change.getDeliveryDate(),
                    WEB3DateUtility.getDate("20080123","yyyyMMdd")), 0);
            assertEquals(l_change.getTaxType(), TaxTypeEnum.NORMAL);
            assertFalse(l_change.isSpecialAccount());
            assertEquals(l_change.getQuantity(), 0.0d, 0);
            assertEquals(l_change.getValuationRatio(), 1.0d, 0);
            assertEquals(l_change.getUnitPrice(), 2.0d, 0);
            assertEquals(l_change.getValuationPrice(), 0.0d, 0);
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 外国株式でない場合
     */
    public void testDoAssetChangesLoad_C002()
    {
        final String STR_METHOD_NAME = "testDoAssetChangesLoad_C002()";
        methodName = STR_METHOD_NAME;
        log.entering(STR_METHOD_NAME);

        try
        {
            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(DailyAssetRow.TYPE);
            DailyAssetParams l_dailyAssetParams = new DailyAssetParams();
            l_dailyAssetParams.setAccountId(333812512246L);
            l_dailyAssetParams.setSubAccountId(33381251220301L);
            l_dailyAssetParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_dailyAssetParams.setQuantity(5.0);
            l_dailyAssetParams.setProductId(0L);
            l_dailyAssetParams.setTaxType(TaxTypeEnum.NORMAL);
            l_dailyAssetParams.setDeliveryDate(WEB3DateUtility.getDate("20080123", "yyyyMMdd"));
            l_dailyAssetParams.setMiniStockDiv(BooleanEnum.TRUE);
            l_dailyAssetParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_dailyAssetParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_dailyAssetParams.setSplitNewStockDiv("1");
            l_dailyAssetParams.setOriginalExecDate(WEB3DateUtility.getDate("20080124", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_dailyAssetParams);

            l_processor.doDeleteAllQuery(GenCurrencyRow.TYPE);
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setCurrentSellExecRate(4.0d);
            TestDBUtility.insertWithDel(l_genCurrencyParams);

            l_processor.doDeleteAllQuery(AssetRow.TYPE);

            l_processor.doDeleteAllQuery(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setCurrencyCode(null);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            //パラメータの準備
            l_common = new WEB3TPSecurityValuationForTest();
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(333812512246L);

            Hashtable l_hashtable = new Hashtable();
            l_hashtable.put(new Long(33381251220301L), SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_accountInfo.setSubAccountIds(l_hashtable);
            l_accountInfo.setInstitutionCode("123");
            l_common.setAccountInfo(l_accountInfo);

            //実際のメソッドを実行
            l_common.doAssetChangesLoad();

            //比較
            WEB3TPSecurityValuationProduct l_product = l_common.getProduct(0100000L, "123");
            WEB3TPSecurityValuationPerProduct l_valuationProduct = l_common.getSecurityValuationPerProduct(l_product);
            List l_lisChanges = l_valuationProduct.getAssetChanges("1");
            WEB3TPSecurityAssetChange l_change = (WEB3TPSecurityAssetChange)l_lisChanges.get(0);
            assertTrue(l_change.isSplitNewStock());
            assertEquals(l_change.getDepositType(), "1");
            assertEquals(
                WEB3DateUtility.compareToDay(
                    l_change.getOriginalExecDate(),
                    WEB3DateUtility.getDate("20080124","yyyyMMdd")), 0);
            assertEquals(
                WEB3DateUtility.compareToDay(
                    l_change.getReflectStartDay(),
                    WEB3DateUtility.getDate("20080124","yyyyMMdd")), 0);
            assertEquals(
                WEB3DateUtility.compareToDay(
                    l_change.getDeliveryDate(),
                    WEB3DateUtility.getDate("20080123","yyyyMMdd")), 0);
            assertEquals(l_change.getTaxType(), TaxTypeEnum.NORMAL);
            assertFalse(l_change.isSpecialAccount());
            assertEquals(l_change.getQuantity(), 5.0d, 0);
            assertEquals(l_change.getValuationRatio(), 1.0d, 0);
            assertEquals(l_change.getUnitPrice(), 2.0d, 0);
            assertEquals(l_change.getValuationPrice(), 3.0d, 0);
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 正常の場合
     */
    public void testCalcFeqValuation_C0001()
    {
        final String STR_METHOD_NAME = "testCalcFeqValuation_C0001()";
        methodName = STR_METHOD_NAME;
        log.entering(STR_METHOD_NAME);

        try
        {
            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(FeqProductRow.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setCurrencyCode("132");
            TestDBUtility.insertWithDel(l_feqProductParams);

            l_processor.doDeleteAllQuery(GenCurrencyRow.TYPE);
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setCurrentSellExecRate(4.0d);
            TestDBUtility.insertWithDel(l_genCurrencyParams);

            //パラメータの準備
            WEB3TPSecurityValuationProduct l_product = new WEB3TPSecurityValuationProduct();
            l_product.setUnitSize(1.0d);
            l_product.setProductId(3304148080000L);
            WEB3TPSecurityChange l_change = new WEB3TPSecurityAssetChange();
            l_change.setQuantity(2.0d);
            l_change.setUnitPrice(3.0d);

            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setInstitutionCode("123");
            l_common.setAccountInfo(l_accountInfo);

            //実際のメソッドを実行
            double l_result = l_common.calcFeqValuation(l_product, l_change);

            //比較
            assertEquals(l_result, 24.0d, 0);
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@

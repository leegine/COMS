head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.17.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqBalanceReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.WEB3FeqProductQuote;
import webbroker3.feq.define.WEB3FeqRoundDivDef;
import webbroker3.feq.message.WEB3FeqBalanceReferenceDetailUnit;
import webbroker3.feq.message.WEB3FeqBalanceReferenceRequest;
import webbroker3.feq.message.WEB3FeqBalanceReferenceTotalRequest;
import webbroker3.feq.message.WEB3FeqBalanceReferenceTotalResponse;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqBookValuePriceRegistServiceImplTest.WEB3FeqBookValuePriceRegistServiceImplForTest;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.FeqBranchMarketDealtCondParams;
import webbroker3.gentrade.data.FeqLastClosingPriceParams;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqBalanceReferenceServiceImplTest extends TestBaseForMock 
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqBalanceReferenceServiceImplTest.class);  

    WEB3FeqBalanceReferenceServiceImpl l_impl = new WEB3FeqBalanceReferenceServiceImpl();
    public WEB3FeqBalanceReferenceServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testGetEquityProductQuote_T001()
    {
        final String STR_METHOD_NAME = "testGetEquityProductQuote_T001()";
        
        log.entering(STR_METHOD_NAME);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        WEB3FeqProductManager l_feqProductManager =
            (WEB3FeqProductManager) GtlUtils.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
        
        WEB3GentradeSubAccount l_subAccount = null;
        WEB3FeqProduct l_feqProduct = null;

        try
        {
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);

            //FeqProductParams
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(l_productParams.getProductId());
            TestDBUtility.insertWithDel(l_feqProductParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("N2");
            TestDBUtility.insertWithDel(l_marketParams);
            
            //FeqTradedProductParams
            TestDBUtility.deleteAll(FeqTradedProductParams.TYPE);
            FeqTradedProductParams l_feqTradedProductParams = TestDBUtility.getFeqTradedProductRow();
            l_feqTradedProductParams.setProductId(l_productParams.getProductId());
            TestDBUtility.insertWithDel(l_feqTradedProductParams);
            
            //TradedProductParams
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(l_feqTradedProductParams.getTradedProductId());
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //FeqLastClosingPriceParams
            TestDBUtility.deleteAll(FeqLastClosingPriceParams.TYPE);
            FeqLastClosingPriceParams l_lastPriceParams = this.getFeqLastClosingPriceParams();
            l_lastPriceParams.setProductId(l_productParams.getProductId());
            l_lastPriceParams.setFeqClosingPriceMrktCodeS(l_marketParams.getMarketCode());
            l_lastPriceParams.setFeqClosingPrice(123.12);
            TestDBUtility.insertWithDel(l_lastPriceParams);

            l_subAccount =
                (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());

            l_feqProduct = 
                (WEB3FeqProduct)l_feqProductManager.getFeqProduct(
                    l_productParams.getProductId());

            HashMap map = new HashMap();
            WEB3FeqProductQuote l_feqProductQuote1 =
                l_impl.getEquityProductQuote(l_subAccount, l_feqProduct, map);
            assertEquals(l_feqProductQuote1.getCurrentPrice(), 123.12, 0);
            WEB3FeqProductQuote l_feqProductQuote2 = 
                (WEB3FeqProductQuote)map.get(
                    l_feqProductParams.getProductCode() + l_feqProductParams.getMarketCode());
            assertEquals(l_feqProductQuote2.getCurrentPrice(), 123.12, 0);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testcalcProfitLoss_T001()
    {
        final String STR_METHOD_NAME = "testcalcProfitLoss_T001";
        log.entering(STR_METHOD_NAME);
        
        
        double l_price = 1000.0;
        BigDecimal l_bookvalue = new BigDecimal(String.valueOf(1.5));
        double l_quantity = 10000.0;

        double l_calcProfitLOss;
        
        
        GenCurrencyParams l_currencyParams = TestDBUtility.getGenCurrencyRow();
        l_currencyParams.setCurrentSellRate(0.12345678D);
        l_currencyParams.setChangeJpyRoundDiv("0");
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcJPYAmount",
                new Class[]{
                    double.class,
                    double.class,
                    String.class
                },
                new Double(20000));

         try
         {
             WEB3GentradeCurrency l_currency = new WEB3GentradeCurrency(l_currencyParams);
             l_calcProfitLOss = l_impl.calcProfitLoss(l_price,l_bookvalue,l_quantity,l_currency);
             
             assertEquals(5000,l_calcProfitLOss, 0);
             
         }catch (WEB3BaseException l_ex)
         {
             log.error(l_ex.getMessage(), l_ex);
             fail();
         }
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testcalcProfitLoss_T002()
    {
        final String STR_METHOD_NAME = "testcalcProfitLoss_T002";
        log.entering(STR_METHOD_NAME);
        
        
        double l_price = 1000.0;
        BigDecimal l_bookvalue = new BigDecimal(String.valueOf(1.5));
        double l_quantity = 10000.0;

        double l_calcProfitLOss;
        
        
        GenCurrencyParams l_currencyParams = TestDBUtility.getGenCurrencyRow();
        l_currencyParams.setCurrentSellRate(0.12345678D);
        l_currencyParams.setChangeJpyRoundDiv("0");
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcJPYAmount",
                new Class[]{
                    double.class,
                    double.class,
                    String.class
                },
                new Double(15000));

         try
         {
             WEB3GentradeCurrency l_currency = new WEB3GentradeCurrency(l_currencyParams);
             l_calcProfitLOss = l_impl.calcProfitLoss(l_price,l_bookvalue,l_quantity,l_currency);
             
             assertEquals(0,l_calcProfitLOss, 0);
             
         }catch (WEB3BaseException l_ex)
         {
             log.error(l_ex.getMessage(), l_ex);
             fail();
         }
        
        log.exiting(STR_METHOD_NAME);

    }
    
    
    
    public void testcreateDetailList_T001()
    {
        final String STR_METHOD_NAME = "testcreateDetailList_T001()";
        log.entering(STR_METHOD_NAME);
        
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        
        WEB3GentradeSubAccount l_subAccount=null;
        
        WEB3FeqBalanceReferenceRequest l_request = new WEB3FeqBalanceReferenceRequest();
        
        l_request.productCode = "0D";
        
        
        try
        {
           
            MOCK_MANAGER.setIsMockUsed(true); 
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20080121","yyyyMMdd"));
 

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv(
                WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(l_mainAccountParams);
 
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
 
            
            l_subAccount =
                (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            
            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_feqOrderParams = TestDBUtility.getFeqOrderRow();
            l_feqOrderParams.setOrderId(1001L);
            l_feqOrderParams.setSubAccountId(1001L);
            TestDBUtility.insertWithDel(l_feqOrderParams);
 
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setOrderId(1001L);
            l_feqOrderUnitParams.setSubAccountId(1001L);
            l_feqOrderUnitParams.setProductId(1001L);
            l_feqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_feqOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_feqOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_feqOrderUnitParams.setExecutionConditionType(FeqExecutionConditionType.AT_MARKET_OPEN);
            l_feqOrderUnitParams.setFirstOrderUnitId(1001L);
            l_feqOrderUnitParams.setOrderUnitId(1001L);
            l_feqOrderUnitParams.setMarketId(1001L);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
//
//            l_feqOrderUnitParams.setOrderId(1233);
//            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
 
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_productParams);
 
            
            
            TestDBUtility.deleteAll(FeqProductParams.TYPE);

            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_feqProductParams.setProductCode(l_request.productCode);
            l_feqProductParams.setProductId(1001L);
            
            TestDBUtility.insertWithDel(l_feqProductParams);
 
            
            
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
 
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("N2");
            l_marketParams.setMarketId(1001);
            TestDBUtility.insertWithDel(l_marketParams);
 
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
 
            TestDBUtility.deleteAll(FeqBranchMarketDealtCondParams.TYPE);
            FeqBranchMarketDealtCondParams l_feqBranchMarketDealtCondParams =
                TestDBUtility.getFeqBranchMarketDealtCondRow();
            l_feqBranchMarketDealtCondParams.setBranchCode("381");
            l_feqBranchMarketDealtCondParams.setInstitutionCode("0D");
            l_feqBranchMarketDealtCondParams.setMarketCode("N2");
            TestDBUtility.insertWithDel(l_feqBranchMarketDealtCondParams);
 
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1001L);
            l_tradedProductParams.setMarketId(1001l);
            TestDBUtility.insertWithDel(l_tradedProductParams);
 
            TestDBUtility.deleteAll(FeqTradedProductParams.TYPE);
            FeqTradedProductParams l_feqTradedProductParams =
                TestDBUtility.getFeqTradedProductRow();
            l_feqTradedProductParams.setTradedProductId(1006160060005l);
            l_feqTradedProductParams.setTradeStop(0);
            l_feqTradedProductParams.setBuyStop(0);
            l_feqTradedProductParams.setProductId(1001L);
            l_feqTradedProductParams.setMarketId(1001L);
            l_feqTradedProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_feqTradedProductParams);
 
 
          TestDBUtility.deleteAll(AssetParams.TYPE);
          AssetParams l_assetParams = TestDBUtility.getAssetRow();
          
          l_assetParams.setProductId(1001L);
          l_assetParams.setAccountId(l_subAccount.getAccountId());
          l_assetParams.setSubAccountId(l_subAccount.getSubAccountId());
          l_assetParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
          l_assetParams.setMiniStockDiv( WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
          
          l_assetParams.setQuantity(100D);
          TestDBUtility.insertWithDel(l_assetParams);
         
            
            TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_enableOrderConditionParams.setMarketCode("N2");
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            
//            TestDBUtility.deleteAll(CurrencyParams.TYPE);
//            CurrencyParams l_currencyParams = TestDBUtility.getCurrencyRow();
//            l_currencyParams.setInstitutionCode("0D");
//            l_currencyParams.setCurrencyCode("001");
//            TestDBUtility.insertWithDel(l_currencyParams);
            
            

            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams  = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("001");
            l_genCurrencyParams.setCurrentSellRate(1.0D);
            TestDBUtility.insertWithDel(l_genCurrencyParams);
            
            
            TestDBUtility.deleteAll(FeqLastClosingPriceParams.TYPE);
            FeqLastClosingPriceParams l_lastPriceParams = this.getFeqLastClosingPriceParams();
            l_lastPriceParams.setProductId(1001l);
            l_lastPriceParams.setFeqClosingPriceMarketCode(l_marketParams.getMarketCode());
            l_lastPriceParams.setFeqClosingPriceMrktCodeS(l_marketParams.getMarketCode());
            l_lastPriceParams.setFeqClosingPrice(123.12);
            l_lastPriceParams.setBaseDate(WEB3DateUtility.getDate("20071112", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_lastPriceParams);
            

            WEB3FeqBalanceReferenceDetailUnit[] l_FeqBalanceReferenceDetailUnit;
            
            
            
            l_FeqBalanceReferenceDetailUnit =  l_impl.createDetailList(l_subAccount,l_request);
            
          
            assertEquals( "1001",l_FeqBalanceReferenceDetailUnit[0].assetId);
            assertEquals("200",String.valueOf( l_FeqBalanceReferenceDetailUnit[0].balanceQuantity));
            assertEquals("2007/11/12",l_FeqBalanceReferenceDetailUnit[0].currentPriceTime);
            
        }catch(Exception l_ex)
        {
            log.error( l_ex.getMessage(), l_ex);
            fail();
            
        }
        

        log.exiting(STR_METHOD_NAME);
    }
    
    
  
    
    public void testcreateDetailList_T002()
    {
        final String STR_METHOD_NAME = "testcreateDetailList_T002()";
        log.entering(STR_METHOD_NAME);
        
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        
        WEB3GentradeSubAccount l_subAccount=null;
        
        WEB3FeqBalanceReferenceRequest l_request = new WEB3FeqBalanceReferenceRequest();
        
        l_request.productCode = "0D";
        
        
        try
        {
           
            MOCK_MANAGER.setIsMockUsed(true); 
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20080121","yyyyMMdd"));
 

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv(
                WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(l_mainAccountParams);
 
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
 
            
            l_subAccount =
                (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            
            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_feqOrderParams = TestDBUtility.getFeqOrderRow();
            l_feqOrderParams.setOrderId(1001L);
            l_feqOrderParams.setSubAccountId(1001L);
            TestDBUtility.insertWithDel(l_feqOrderParams);
 
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setOrderId(1001L);
            l_feqOrderUnitParams.setSubAccountId(1001L);
            l_feqOrderUnitParams.setProductId(1001L);
            l_feqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_feqOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_feqOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_feqOrderUnitParams.setExecutionConditionType(FeqExecutionConditionType.AT_MARKET_OPEN);
            l_feqOrderUnitParams.setFirstOrderUnitId(1001L);
            l_feqOrderUnitParams.setOrderUnitId(1001L);
            l_feqOrderUnitParams.setMarketId(1001L);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
//
//            l_feqOrderUnitParams.setOrderId(1233);
//            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
 
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_productParams);
 
            
            
            TestDBUtility.deleteAll(FeqProductParams.TYPE);

            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_feqProductParams.setProductCode(l_request.productCode);
            l_feqProductParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_feqProductParams);
 
            
            
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
 
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("N2");
            l_marketParams.setMarketId(1001);
            TestDBUtility.insertWithDel(l_marketParams);
 
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
 
            TestDBUtility.deleteAll(FeqBranchMarketDealtCondParams.TYPE);
            FeqBranchMarketDealtCondParams l_feqBranchMarketDealtCondParams =
                TestDBUtility.getFeqBranchMarketDealtCondRow();
            l_feqBranchMarketDealtCondParams.setBranchCode("381");
            l_feqBranchMarketDealtCondParams.setInstitutionCode("0D");
            l_feqBranchMarketDealtCondParams.setMarketCode("N2");
            TestDBUtility.insertWithDel(l_feqBranchMarketDealtCondParams);
 
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1001L);
            l_tradedProductParams.setMarketId(1001l);
            TestDBUtility.insertWithDel(l_tradedProductParams);
 
            TestDBUtility.deleteAll(FeqTradedProductParams.TYPE);
            FeqTradedProductParams l_feqTradedProductParams =
                TestDBUtility.getFeqTradedProductRow();
            l_feqTradedProductParams.setTradedProductId(1006160060005l);
            l_feqTradedProductParams.setTradeStop(0);
            l_feqTradedProductParams.setBuyStop(0);
            l_feqTradedProductParams.setProductId(1001L);
            l_feqTradedProductParams.setMarketId(1001L);
            l_feqTradedProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_feqTradedProductParams);
 
 
          TestDBUtility.deleteAll(AssetParams.TYPE);
          AssetParams l_assetParams = TestDBUtility.getAssetRow();
          
          l_assetParams.setProductId(1001L);
          l_assetParams.setAccountId(l_subAccount.getAccountId());
          l_assetParams.setSubAccountId(l_subAccount.getSubAccountId());
          l_assetParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
          l_assetParams.setMiniStockDiv( WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
          
          l_assetParams.setQuantity(100D);
          
          l_assetParams.setBookValue(10.123556789D);
          
          
          TestDBUtility.insertWithDel(l_assetParams);
         
            
            TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_enableOrderConditionParams.setMarketCode("N2");
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            
//            TestDBUtility.deleteAll(CurrencyParams.TYPE);
//            CurrencyParams l_currencyParams = TestDBUtility.getCurrencyRow();
//            l_currencyParams.setInstitutionCode("0D");
//            l_currencyParams.setCurrencyCode("001");
//            TestDBUtility.insertWithDel(l_currencyParams);
//            
            
            
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams  = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("001");
            l_genCurrencyParams.setCurrentSellRate(1.0D);
            TestDBUtility.insertWithDel(l_genCurrencyParams);
            
            
            
            
            TestDBUtility.deleteAll(FeqLastClosingPriceParams.TYPE);
            FeqLastClosingPriceParams l_lastPriceParams = this.getFeqLastClosingPriceParams();
            l_lastPriceParams.setProductId(l_productParams.getProductId());
            l_lastPriceParams.setFeqClosingPriceMarketCode(l_marketParams.getMarketCode());
            l_lastPriceParams.setFeqClosingPrice(123.12);
            l_lastPriceParams.setFeqClosingPriceMrktCodeS("N2");
            l_lastPriceParams.setBaseDate(WEB3DateUtility.getDate("20071112", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_lastPriceParams);
            

            WEB3FeqBalanceReferenceDetailUnit[] l_FeqBalanceReferenceDetailUnit;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcJPYAmount",
                    new Class[]{
                        double.class,
                        double.class,
                        String.class
                    },
                new Double(24624));
            
            l_FeqBalanceReferenceDetailUnit =  l_impl.createDetailList(l_subAccount,l_request);
            
            
            
            assertEquals( "0.10124",l_FeqBalanceReferenceDetailUnit[0].estimatedBookPrice);
             
            assertEquals( "1001",l_FeqBalanceReferenceDetailUnit[0].assetId);
            assertEquals("200",String.valueOf( l_FeqBalanceReferenceDetailUnit[0].balanceQuantity));
            
            assertEquals("2007/11/12",l_FeqBalanceReferenceDetailUnit[0].currentPriceTime);
           assertEquals("123.12",l_FeqBalanceReferenceDetailUnit[0].currentPrice);
           assertEquals("0",l_FeqBalanceReferenceDetailUnit[0].comparedPreviousDay);
           assertEquals("2007/11/12",l_FeqBalanceReferenceDetailUnit[0].currentPriceTime);
           assertEquals("24624",l_FeqBalanceReferenceDetailUnit[0].estimatedAssetBalanceQuantity);
           assertEquals("24624",l_FeqBalanceReferenceDetailUnit[0].estimatedAssetSellPossQuantity);
           assertEquals("24624",l_FeqBalanceReferenceDetailUnit[0].estimatedAssetOrderedQuantity);
           assertEquals("24624",l_FeqBalanceReferenceDetailUnit[0].estimatedAssetSellImpossQuantity);
           
           
           assertEquals("24604",l_FeqBalanceReferenceDetailUnit[0].estimatedAppraisalProfitLossBalanceQuantity);
           assertEquals("24614",l_FeqBalanceReferenceDetailUnit[0].estimatedAppraisalProfitLossSellPossQuantity);
           assertEquals("24624",l_FeqBalanceReferenceDetailUnit[0].estimatedAppraisalProfitLossOrderedQuantity);
           assertEquals("24614",l_FeqBalanceReferenceDetailUnit[0].estimatedAppraisalProfitLossSellImpossQuantity);
          
 
            
        }catch(Exception l_ex)
        {
            log.error( l_ex.getMessage(), l_ex);
            fail();
            
        }
        

        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testcreateDetailList_T003()
    {
        final String STR_METHOD_NAME = "testcreateDetailList_T003()";
        log.entering(STR_METHOD_NAME);
        
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        
        WEB3GentradeSubAccount l_subAccount=null;
        
        WEB3FeqBalanceReferenceRequest l_request = new WEB3FeqBalanceReferenceRequest();
        
        l_request.productCode = "0D";
        
        
        try
        {
           
            MOCK_MANAGER.setIsMockUsed(true); 
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20080121","yyyyMMdd"));
 

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv(
                WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(l_mainAccountParams);
 
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
 
            
            l_subAccount =
                (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            
            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_feqOrderParams = TestDBUtility.getFeqOrderRow();
            l_feqOrderParams.setOrderId(1001L);
            l_feqOrderParams.setSubAccountId(1001L);
            TestDBUtility.insertWithDel(l_feqOrderParams);
 
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setOrderId(1001L);
            l_feqOrderUnitParams.setSubAccountId(1001L);
            l_feqOrderUnitParams.setProductId(1001L);
            l_feqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_feqOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_feqOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_feqOrderUnitParams.setExecutionConditionType(FeqExecutionConditionType.AT_MARKET_OPEN);
            l_feqOrderUnitParams.setFirstOrderUnitId(1001L);
            l_feqOrderUnitParams.setOrderUnitId(1001L);
            l_feqOrderUnitParams.setMarketId(1001L);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
//
//            l_feqOrderUnitParams.setOrderId(1233);
//            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
 
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_productParams);
 
            
            
            TestDBUtility.deleteAll(FeqProductParams.TYPE);

            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_feqProductParams.setProductCode(l_request.productCode);
            l_feqProductParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_feqProductParams);
 
            
            
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
 
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("N2");
            l_marketParams.setMarketId(1001);
            TestDBUtility.insertWithDel(l_marketParams);
 
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
 
            TestDBUtility.deleteAll(FeqBranchMarketDealtCondParams.TYPE);
            FeqBranchMarketDealtCondParams l_feqBranchMarketDealtCondParams =
                TestDBUtility.getFeqBranchMarketDealtCondRow();
            l_feqBranchMarketDealtCondParams.setBranchCode("381");
            l_feqBranchMarketDealtCondParams.setInstitutionCode("0D");
            l_feqBranchMarketDealtCondParams.setMarketCode("N2");
            TestDBUtility.insertWithDel(l_feqBranchMarketDealtCondParams);
 
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1001L);
            l_tradedProductParams.setMarketId(1001l);
            TestDBUtility.insertWithDel(l_tradedProductParams);
 
            TestDBUtility.deleteAll(FeqTradedProductParams.TYPE);
            FeqTradedProductParams l_feqTradedProductParams =
                TestDBUtility.getFeqTradedProductRow();
            l_feqTradedProductParams.setTradedProductId(1006160060005l);
            l_feqTradedProductParams.setTradeStop(0);
            l_feqTradedProductParams.setBuyStop(0);
            l_feqTradedProductParams.setProductId(1001L);
            l_feqTradedProductParams.setMarketId(1001L);
            l_feqTradedProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_feqTradedProductParams);
 
 
          TestDBUtility.deleteAll(AssetParams.TYPE);
          AssetParams l_assetParams = TestDBUtility.getAssetRow();
          
          l_assetParams.setProductId(1001L);
          l_assetParams.setAccountId(l_subAccount.getAccountId());
          l_assetParams.setSubAccountId(l_subAccount.getSubAccountId());
          l_assetParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
          l_assetParams.setMiniStockDiv( WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
          
          l_assetParams.setQuantity(100D);
          
          l_assetParams.setQuantityForBookValue(0.0);//===========================================
          
          TestDBUtility.insertWithDel(l_assetParams);
         
            
            TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_enableOrderConditionParams.setMarketCode("N2");
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            
//            TestDBUtility.deleteAll(CurrencyParams.TYPE);
//            CurrencyParams l_currencyParams = TestDBUtility.getCurrencyRow();
//            l_currencyParams.setInstitutionCode("0D");
//            l_currencyParams.setCurrencyCode("001");
//            TestDBUtility.insertWithDel(l_currencyParams);
          
            
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams  = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("001");
            l_genCurrencyParams.setCurrentSellRate(1.0D);
            TestDBUtility.insertWithDel(l_genCurrencyParams);
            
            

            TestDBUtility.deleteAll(FeqLastClosingPriceParams.TYPE);
            FeqLastClosingPriceParams l_lastPriceParams = this.getFeqLastClosingPriceParams();
            l_lastPriceParams.setProductId(l_productParams.getProductId());
            l_lastPriceParams.setFeqClosingPriceMarketCode(l_marketParams.getMarketCode());
            l_lastPriceParams.setFeqClosingPrice(123.12);
            l_lastPriceParams.setFeqClosingPriceMrktCodeS("N2");
            l_lastPriceParams.setBaseDate(WEB3DateUtility.getDate("20071112", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_lastPriceParams);
            

            WEB3FeqBalanceReferenceDetailUnit[] l_FeqBalanceReferenceDetailUnit;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcJPYAmount",
                    new Class[]{
                        double.class,
                        double.class,
                        String.class
                    },
                new Double(24624));
            
            l_FeqBalanceReferenceDetailUnit =  l_impl.createDetailList(l_subAccount,l_request);
            
          
            
            
            assertEquals( "1001",l_FeqBalanceReferenceDetailUnit[0].assetId);
            assertEquals("200",String.valueOf( l_FeqBalanceReferenceDetailUnit[0].balanceQuantity));
            
            
           assertEquals("2007/11/12",l_FeqBalanceReferenceDetailUnit[0].currentPriceTime);
           assertEquals("123.12",l_FeqBalanceReferenceDetailUnit[0].currentPrice);
           assertEquals("0",l_FeqBalanceReferenceDetailUnit[0].comparedPreviousDay);
           assertEquals("2007/11/12",l_FeqBalanceReferenceDetailUnit[0].currentPriceTime);
           assertEquals("24624",l_FeqBalanceReferenceDetailUnit[0].estimatedAssetBalanceQuantity);
           assertEquals("24624",l_FeqBalanceReferenceDetailUnit[0].estimatedAssetSellPossQuantity);
           assertEquals("24624",l_FeqBalanceReferenceDetailUnit[0].estimatedAssetOrderedQuantity);
           assertEquals("24624",l_FeqBalanceReferenceDetailUnit[0].estimatedAssetSellImpossQuantity);
           
           
           assertNull(l_FeqBalanceReferenceDetailUnit[0].estimatedAppraisalProfitLossBalanceQuantity);
           assertNull(l_FeqBalanceReferenceDetailUnit[0].estimatedAppraisalProfitLossSellPossQuantity);
           assertNull(l_FeqBalanceReferenceDetailUnit[0].estimatedAppraisalProfitLossOrderedQuantity);
           assertNull(l_FeqBalanceReferenceDetailUnit[0].estimatedAppraisalProfitLossSellImpossQuantity);
         
          
 
            
        }catch(Exception l_ex)
        {
            log.error( l_ex.getMessage(), l_ex);
            fail();
            
        }
        

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testgetBalanceTotal_T001()
    {
        
        final String STR_METHOD_NAME = "testgetBalanceTotal_T001()";
        
            log.entering(STR_METHOD_NAME);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
            
            
            
            WEB3GentradeSubAccount l_subAccount=null;
            
            WEB3FeqBalanceReferenceRequest l_request = new WEB3FeqBalanceReferenceRequest();

            l_request.productCode = "0D";
            
            
            WEB3FeqBalanceReferenceTotalResponse l_response = null;
           
            WEB3FeqBalanceReferenceTotalRequest l_feqBalanceReferenceTotalRequest = 
                new WEB3FeqBalanceReferenceTotalRequest();
            

            WEB3FeqBalanceReferenceServiceImplForTest l_implTest = 
                new WEB3FeqBalanceReferenceServiceImplForTest();
            
            try
            {
                
                MOCK_MANAGER.setIsMockUsed(true); 
                
                WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                    WEB3DateUtility.getDate("20080121","yyyyMMdd"));
     

                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
                l_mainAccountParams.setAccountId(1001L);
                l_mainAccountParams.setMarginGenAccOpenDiv(
                    WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
                TestDBUtility.insertWithDel(l_mainAccountParams);
     
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
                l_subAccountParams.setInstitutionCode("0D");
                l_subAccountParams.setAccountId(1001L);
                l_subAccountParams.setSubAccountId(1001L);
                l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                TestDBUtility.insertWithDel(l_subAccountParams);
     
                
                l_subAccount =
                    (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                        l_subAccountParams.getAccountId(),
                        l_subAccountParams.getSubAccountId());
                
                
//                TestDBUtility.deleteAll(FeqOrderParams.TYPE);
//                FeqOrderParams l_feqOrderParams = TestDBUtility.getFeqOrderRow();
//                l_feqOrderParams.setOrderId(1001L);
//                l_feqOrderParams.setSubAccountId(1001L);
//                TestDBUtility.insertWithDel(l_feqOrderParams);
     
                TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
                FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
                l_feqOrderUnitParams.setOrderId(1001L);
                l_feqOrderUnitParams.setSubAccountId(1001L);
                l_feqOrderUnitParams.setProductId(1001L);
                l_feqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
                l_feqOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
                l_feqOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
                l_feqOrderUnitParams.setExecutionConditionType(FeqExecutionConditionType.AT_MARKET_OPEN);
                l_feqOrderUnitParams.setFirstOrderUnitId(1001L);
                l_feqOrderUnitParams.setOrderUnitId(1001L);
                l_feqOrderUnitParams.setMarketId(1001L);
                TestDBUtility.insertWithDel(l_feqOrderUnitParams);
    //
//                l_feqOrderUnitParams.setOrderId(1233);
//                TestDBUtility.insertWithDel(l_feqOrderUnitParams);
     
                TestDBUtility.deleteAll(ProductParams.TYPE);
                ProductParams l_productParams = TestDBUtility.getProductRow();
                l_productParams.setProductId(1001L);
                TestDBUtility.insertWithDel(l_productParams);
     
                
                
                TestDBUtility.deleteAll(FeqProductParams.TYPE);

                FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
                l_feqProductParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
                l_feqProductParams.setProductCode(l_request.productCode);
                l_feqProductParams.setProductId(1001L);
                TestDBUtility.insertWithDel(l_feqProductParams);
     
                
                
                
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
                l_institutionParams.setInstitutionId(33);
                l_institutionParams.setInstitutionCode("0D");
                TestDBUtility.insertWithDel(l_institutionParams);
     
                TestDBUtility.deleteAll(MarketParams.TYPE);
                MarketParams l_marketParams = TestDBUtility.getMarketRow();
                l_marketParams.setMarketCode("N2");
                l_marketParams.setMarketId(1001);
                TestDBUtility.insertWithDel(l_marketParams);
     
                TestDBUtility.deleteAll(BranchParams.TYPE);
                BranchParams l_branchParams = TestDBUtility.getBranchRow();
                l_branchParams.setBranchId(33381);
                l_branchParams.setBranchCode("381");
                TestDBUtility.insertWithDel(l_branchParams);
     
                TestDBUtility.deleteAll(FeqBranchMarketDealtCondParams.TYPE);
                FeqBranchMarketDealtCondParams l_feqBranchMarketDealtCondParams =
                    TestDBUtility.getFeqBranchMarketDealtCondRow();
                l_feqBranchMarketDealtCondParams.setBranchCode("381");
                l_feqBranchMarketDealtCondParams.setInstitutionCode("0D");
                l_feqBranchMarketDealtCondParams.setMarketCode("N2");
                TestDBUtility.insertWithDel(l_feqBranchMarketDealtCondParams);
     
                TestDBUtility.deleteAll(TradedProductParams.TYPE);
                TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
                l_tradedProductParams.setProductId(1001L);
                l_tradedProductParams.setMarketId(1001l);
                TestDBUtility.insertWithDel(l_tradedProductParams);
     
                TestDBUtility.deleteAll(FeqTradedProductParams.TYPE);
                FeqTradedProductParams l_feqTradedProductParams =
                    TestDBUtility.getFeqTradedProductRow();
                l_feqTradedProductParams.setTradedProductId(1006160060005l);
                l_feqTradedProductParams.setTradeStop(0);
                l_feqTradedProductParams.setBuyStop(0);
                l_feqTradedProductParams.setProductId(1001L);
                l_feqTradedProductParams.setMarketId(1001L);
                l_feqTradedProductParams.setInstitutionCode("0D");
                TestDBUtility.insertWithDel(l_feqTradedProductParams);
     
     
              TestDBUtility.deleteAll(AssetParams.TYPE);
              AssetParams l_assetParams = TestDBUtility.getAssetRow();
              l_assetParams.setProductId(1001L);
              l_assetParams.setAccountId(l_subAccount.getAccountId());
              l_assetParams.setSubAccountId(l_subAccount.getSubAccountId());
              l_assetParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
              l_assetParams.setMiniStockDiv( WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
              l_assetParams.setQuantity(100D);
              //l_assetParams.setBookValue(10.123556789D);
              TestDBUtility.insertWithDel(l_assetParams);
             
                
                TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
                EnableOrderConditionParams l_enableOrderConditionParams =
                    TestDBUtility.getEnableOrderConditionParamsRow();
                l_enableOrderConditionParams.setInstitutionCode("0D");
                l_enableOrderConditionParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
                l_enableOrderConditionParams.setMarketCode("N2");
                l_enableOrderConditionParams.setFutureOptionDiv("0");
                l_enableOrderConditionParams.setMarginTradingDiv("0");
                TestDBUtility.insertWithDel(l_enableOrderConditionParams);
 
                
                TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
                GenCurrencyParams l_genCurrencyParams  = TestDBUtility.getGenCurrencyRow();
                l_genCurrencyParams.setInstitutionCode("0D");
                l_genCurrencyParams.setCurrencyCode("001");
                l_genCurrencyParams.setCurrentSellRate(1.0D);
                TestDBUtility.insertWithDel(l_genCurrencyParams);
                
                
                
                
                TestDBUtility.deleteAll(FeqLastClosingPriceParams.TYPE);
                FeqLastClosingPriceParams l_lastPriceParams = this.getFeqLastClosingPriceParams();
                l_lastPriceParams.setProductId(l_productParams.getProductId());
                l_lastPriceParams.setFeqClosingPriceMarketCode(l_marketParams.getMarketCode());
                l_lastPriceParams.setFeqClosingPrice(123.12);
                l_lastPriceParams.setFeqClosingPriceMrktCodeS("N2");
                TestDBUtility.insertWithDel(l_lastPriceParams);

                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcJPYAmount",
                    new Class[]{
                        double.class,
                        double.class,
                        String.class
                    },
                new Double(24624));
                
                l_response =  l_implTest.getBalanceTotal(l_feqBalanceReferenceTotalRequest);
                
                assertEquals("24624",l_response.normalAccountTotalAsset);
                assertEquals("24424", l_response.normalAccountTotalAppraisalProfitLoss);
                
                
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(),l_ex);
                fail();
            }
            
            
            log.exiting(STR_METHOD_NAME);
            
    }
    
    public void testcalcEstimatedValue_T001()
    {
        final String STR_METHOD_NAME = "testcalcEstimatedValue_T001()";
        
        log.entering(STR_METHOD_NAME);
        double l_price = 100;
        double l_quantity = 10;
        double l_calcEstimatedValue;
        
 
        GenCurrencyParams l_currencyParams = TestDBUtility.getGenCurrencyRow();
        
        l_currencyParams.setCurrentSellRate(0.12345D);
        l_currencyParams.setChangeJpyRoundDiv(WEB3FeqRoundDivDef.CUTOFF);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.feq.WEB3FeqBizLogicProvider",
            "calcJPYAmount",
            new Class[]{
                double.class,
                double.class,
                String.class
            },
        new Double(123));
        
        try
        {
            WEB3GentradeCurrency l_gencurrency = new WEB3GentradeCurrency(l_currencyParams);
            l_calcEstimatedValue=  l_impl.calcEstimatedValue( l_price,l_quantity,l_gencurrency);
            assertEquals(123.0, l_calcEstimatedValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage() ,l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testcalcEstimatedValue_T002()
    {
        final String STR_METHOD_NAME = "testcalcEstimatedValue_T002()";
        
        log.entering(STR_METHOD_NAME);
        double l_price = 100;
        double l_quantity = 10;
        double l_calcEstimatedValue;
        
        GenCurrencyParams l_currencyParams = TestDBUtility.getGenCurrencyRow();
        
        l_currencyParams.setCurrentSellRate(0.1357D);
        l_currencyParams.setChangeJpyRoundDiv("0");

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcJPYAmount",
                new Class[]{
                    double.class,
                    double.class,
                    String.class
                },
            new Double(136));
            
            WEB3GentradeCurrency l_gencurrency = new WEB3GentradeCurrency(l_currencyParams);
            l_calcEstimatedValue=  l_impl.calcEstimatedValue( l_price,l_quantity,l_gencurrency);
            assertEquals(136.0, l_calcEstimatedValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage() ,l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
     
    
    
    public FeqLastClosingPriceParams getFeqLastClosingPriceParams()
    {
        FeqLastClosingPriceParams l_params = new FeqLastClosingPriceParams();
        //銘柄ＩＤ      product_id   NUMBER   18
        l_params.setProductId(123456789l);
        //基準日       base_date   DATE
        l_params.setBaseDate(new Date(2007-1900, 10, 12));
        //外株終値      feq_closing_price   NUMBER   18
        //市場コード     feq_closing_price_market_code   VARCHAR2   2
        l_params.setFeqClosingPriceMarketCode("0D");
        //作成日付      created_timestamp   DATE
        //更新日付      last_updated_timestamp   DATE
        l_params.setOffshoreProductCode("1");
        l_params.setFeqClosingPriceMrktCodeS("2");
        return l_params;
    }

    
        public class WEB3FeqBalanceReferenceServiceImplForTest
        extends WEB3FeqBalanceReferenceServiceImpl
    {
        public SubAccount getSubAccount() throws WEB3BaseException 
        {
            SubAccount l_subAcccount = null;
            try
            {
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                
                //SubAccountParams
                
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
                
                l_subAccountParams.setInstitutionCode("0D");
                l_subAccountParams.setInstitutionId(123456);
                l_subAccountParams.setBranchId(456789);
                
                l_subAccountParams.setInstitutionCode("0D");
                l_subAccountParams.setAccountId(1001L);
                l_subAccountParams.setSubAccountId(1001L);
                //l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
              
                
                TestDBUtility.insertWithDel(l_subAccountParams);
                
                l_subAcccount = l_accountManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
                
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
            
            return l_subAcccount;
        }
    }
    
}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.18.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqSellServiceImplTest.java;


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

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.WEB3FeqAmountCalcResult;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqTradedProduct;
import webbroker3.feq.message.WEB3FeqSellCompleteRequest;
import webbroker3.feq.message.WEB3FeqSellCompleteResponse;
import webbroker3.feq.message.WEB3FeqSellConfirmRequest;
import webbroker3.feq.message.WEB3FeqSellConfirmResponse;
import webbroker3.feq.message.WEB3FeqSellInputRequest;
import webbroker3.feq.message.WEB3FeqSellInputResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqSellServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */    
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3FeqSellServiceImpl.class);
     WEB3FeqSellServiceImpl l_impl = new WEB3FeqSellServiceImpl();
     
    public WEB3FeqSellServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testGetInputScreen_T01()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {    
            WEB3FeqSellInputRequest l_request = new WEB3FeqSellInputRequest();
            l_request.assetId = "123456";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateOrder",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateAccountProductTradedStop",
                new Class[] {SubAccount.class, long.class, OrderTypeEnum.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcForeignCCYAmount",
                    new Class[]
                              { BigDecimal.class, double.class, int.class, String.class},
                    new BigDecimal(200.0));
           
            //ProductParams
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            
            //FeqProductParams
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_feqProductParams);
            WEB3FeqProduct l_feqProduct = new WEB3FeqProduct(l_feqProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateFeqProduct",
                new Class[] {WEB3GentradeInstitution.class, String.class},
                l_feqProduct);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateMarket",
                new Class[] {WEB3GentradeMarket.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcEstimatedBookValuePrice",
                new Class[] {WEB3GentradeSubAccount.class, long.class, TaxTypeEnum.class},
                new BigDecimal("1000"));

            //FeqTradedProductParams
            FeqTradedProductParams l_feqTradedProductParams =
                TestDBUtility.getFeqTradedProductRow();
            l_feqTradedProductParams.setProductId(3304148080000L);
            l_feqTradedProductParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_feqTradedProductParams);
            
            TradedProductParams l_TradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_TradedProductParams.setProductId(3304148080000L);
            l_TradedProductParams.setMarketId(3303L);
            l_TradedProductParams.setTradedProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_TradedProductParams);
            
            WEB3FeqTradedProduct l_feqTradedProduct =
                new WEB3FeqTradedProduct(l_feqTradedProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateTradedProduct",
                new Class[] {WEB3FeqProduct.class, WEB3GentradeMarket.class, boolean.class},
                l_feqTradedProduct);

            //AssetParams
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_assetParams.setAssetId(123456);
            l_assetParams.setProductId(3304148080000L);
            l_assetParams.setQuantity(123);
            l_assetParams.setQuantityCannotSell(321);
            l_assetParams.setAccountId(333812512203L);
            l_assetParams.setSubAccountId(33381251220301L);
            l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
            l_assetParams.setMiniStockDiv("0");
            TestDBUtility.insertWithDel(l_assetParams);

            //BranchParams
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3GentradeBranch l_web3GentradeBranch =
                new WEB3GentradeBranch(l_branchParams.getBranchId());
            
            //WEB3GentradeTradingTimeManagementForMock
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2008-1900,1,20));
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseMarket(
                l_web3GentradeBranch,
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                new String[]{"sf"});
            
            //MarketParams
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(l_feqProductParams.getMarketCode());
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_marketParams);
            
            //InstitutionParams
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //EnableOrderConditionParams
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("N2");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            //MainAccountParams
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //CurrencyParams
            GenCurrencyParams l_currentcyParams = TestDBUtility.getGenCurrencyRow();
            l_currentcyParams.setInstitutionCode("0D");
            l_currentcyParams.setCurrencyCode(l_feqProductParams.getCurrencyCode());
            TestDBUtility.insertWithDel(l_currentcyParams);
            
            WEB3FeqSellServiceImplForMock l_implTest = new WEB3FeqSellServiceImplForMock();
            WEB3FeqSellInputResponse l_resposne = l_implTest.getInputScreen(l_request);
            assertEquals("200",l_resposne.estimatedBookPrice);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateOrder_T01()
    {
        final String STR_METHOD_NAME = "testValidateOrder_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FeqSellServiceImplForMock l_implTest = new WEB3FeqSellServiceImplForMock();
            WEB3FeqSellConfirmRequest l_request = new WEB3FeqSellConfirmRequest();
            l_request.assetId = "123456";
            l_request.settleDiv = "0";
            l_request.orderQuantity = "500";
            l_request.orderPriceDiv = "0";
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "0";
            l_request.orderPriceDiv = "0";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "0";
            l_request.limitPrice = "0";
            
            //AssetParams
            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_assetParams.setAssetId(123456);
            l_assetParams.setProductId(3304148080000L);
            l_assetParams.setQuantity(123);
            l_assetParams.setQuantityCannotSell(321);
            l_assetParams.setAccountId(333812512203L);
            l_assetParams.setSubAccountId(33381251220301L);
            l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
            l_assetParams.setMiniStockDiv("0");
            TestDBUtility.insertWithDel(l_assetParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            //FeqProductParams
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //FeqTradeProductParams
            TestDBUtility.deleteAll(FeqTradedProductParams.TYPE);
            FeqTradedProductParams l_feqTradedProductParams = TestDBUtility.getFeqTradedProductRow();
            l_feqTradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_feqTradedProductParams);
            
            WEB3FeqProduct l_feqProduct = new WEB3FeqProduct(l_feqProductParams);
            
            //MarketParams
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(l_feqProductParams.getMarketCode());
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);


            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateNewOrder",
                new Class[] {SubAccount.class, ProductTypeEnum.class, NewOrderSpec.class},
                l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "getUnitPrice",
                new Class[] {WEB3FeqTradedProduct.class, WEB3GentradeBranch.class, String.class,
                        double.class, double.class, boolean.class},
                new Double(100));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcForeignCCYAmount",
                    new Class[]
                              { BigDecimal.class, double.class, int.class, String.class},
                    new BigDecimal(200.0));

            //MainAccountParams
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //BranchParams
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            
            //CurrencyParams
            GenCurrencyParams l_currentcyParams = TestDBUtility.getGenCurrencyRow();
            l_currentcyParams.setInstitutionCode("0D");
            l_currentcyParams.setCurrencyCode(l_feqProductParams.getCurrencyCode());
            TestDBUtility.insertWithDel(l_currentcyParams);
            
//            InstitutionPreferencesParams
//            MarketPreferencesParams
            
            WEB3FeqAmountCalcResult l_calResult = new WEB3FeqAmountCalcResult();
            l_calResult.setNetAmount(100);
            l_calResult.setCommissionFee(100);
            l_calResult.setCommissionFeeTax(100);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcFeqAmount",
                new Class[]
                {
                  WEB3GentradeSubAccount.class,
                  WEB3FeqProduct.class,
                  WEB3GentradeMarket.class,
                  Date.class,
                  Date.class,
                  double.class,
                  double.class,
                  boolean.class,
                  boolean.class,
                  boolean.class,
                  String.class},
                  l_calResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcEstimatedBookValuePrice",
                new Class[] {WEB3GentradeSubAccount.class, long.class, TaxTypeEnum.class},
                new BigDecimal("200"));
            
            
            WEB3TPTradingPowerResult l_WEB3TPTradingPowerResult = new WEB3TPTradingPowerResult();
            l_WEB3TPTradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_WEB3TPTradingPowerResult);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2008-1900, 1,22));
            WEB3FeqSellConfirmResponse l_response = l_implTest.validateOrder(l_request);
            assertEquals("200", l_response.estimatedBookPrice);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateOrder_T02()
    {
        final String STR_METHOD_NAME = "testValidateOrder_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FeqSellServiceImplForMock l_implTest = new WEB3FeqSellServiceImplForMock();
            WEB3FeqSellConfirmRequest l_request = new WEB3FeqSellConfirmRequest();
            l_request.assetId = "123456";
            l_request.settleDiv = "0";
            l_request.orderQuantity = "500";
            l_request.orderPriceDiv = "0";
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "0";
            l_request.orderPriceDiv = "0";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "0";
            l_request.limitPrice = "0";
            
            //AssetParams
            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_assetParams.setAssetId(123456);
            l_assetParams.setProductId(3304148080000L);
            l_assetParams.setQuantity(123);
            l_assetParams.setQuantityCannotSell(321);
            l_assetParams.setAccountId(333812512203L);
            l_assetParams.setSubAccountId(33381251220301L);
            l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
            l_assetParams.setMiniStockDiv("0");
            TestDBUtility.insertWithDel(l_assetParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            //FeqProductParams
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //FeqTradeProductParams
            TestDBUtility.deleteAll(FeqTradedProductParams.TYPE);
            FeqTradedProductParams l_feqTradedProductParams = TestDBUtility.getFeqTradedProductRow();
            l_feqTradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_feqTradedProductParams);
            
            WEB3FeqProduct l_feqProduct = new WEB3FeqProduct(l_feqProductParams);
            
            //MarketParams
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(l_feqProductParams.getMarketCode());
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);


            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateNewOrder",
                new Class[] {SubAccount.class, ProductTypeEnum.class, NewOrderSpec.class},
                l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "getUnitPrice",
                new Class[] {WEB3FeqTradedProduct.class, WEB3GentradeBranch.class, String.class,
                        double.class, double.class, boolean.class},
                new Double(100));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcForeignCCYAmount",
                    new Class[]
                              { BigDecimal.class, double.class, int.class, String.class},
                    new BigDecimal(200.0));

            //MainAccountParams
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //BranchParams
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            
            //CurrencyParams
            GenCurrencyParams l_currentcyParams = TestDBUtility.getGenCurrencyRow();
            l_currentcyParams.setInstitutionCode("0D");
            l_currentcyParams.setCurrencyCode(l_feqProductParams.getCurrencyCode());
            TestDBUtility.insertWithDel(l_currentcyParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("feq.day.trade.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("feq.day.trade.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            
            WEB3FeqAmountCalcResult l_calResult = new WEB3FeqAmountCalcResult();
            l_calResult.setNetAmount(100);
            l_calResult.setCommissionFee(100);
            l_calResult.setCommissionFeeTax(100);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcFeqAmount",
                new Class[]
                {
                  WEB3GentradeSubAccount.class,
                  WEB3FeqProduct.class,
                  WEB3GentradeMarket.class,
                  Date.class,
                  Date.class,
                  double.class,
                  double.class,
                  boolean.class,
                  boolean.class,
                  boolean.class,
                  String.class},
                  l_calResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcEstimatedBookValuePrice",
                new Class[] {WEB3GentradeSubAccount.class, long.class, TaxTypeEnum.class},
                new BigDecimal("200"));
            
            WEB3TPTradingPowerResult l_WEB3TPTradingPowerResult = new WEB3TPTradingPowerResult();
            l_WEB3TPTradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_WEB3TPTradingPowerResult);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2008-1900, 1,22));
            WEB3FeqSellConfirmResponse l_response = l_implTest.validateOrder(l_request);
            assertEquals("200", l_response.estimatedBookPrice);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateOrder_T03()
    {
        final String STR_METHOD_NAME = "testValidateOrder_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FeqSellServiceImplForMock l_implTest = new WEB3FeqSellServiceImplForMock();
            WEB3FeqSellConfirmRequest l_request = new WEB3FeqSellConfirmRequest();
            l_request.assetId = "123456";
            l_request.settleDiv = "0";
            l_request.orderQuantity = "500";
            l_request.orderPriceDiv = "0";
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "0";
            l_request.orderPriceDiv = "0";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "0";
            l_request.limitPrice = "0";
            
            //AssetParams
            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_assetParams.setAssetId(123456);
            l_assetParams.setProductId(3304148080000L);
            l_assetParams.setQuantity(123);
            l_assetParams.setQuantityCannotSell(321);
            l_assetParams.setAccountId(333812512203L);
            l_assetParams.setSubAccountId(33381251220301L);
            l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
            l_assetParams.setMiniStockDiv("0");
            TestDBUtility.insertWithDel(l_assetParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            //FeqProductParams
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //FeqTradeProductParams
            TestDBUtility.deleteAll(FeqTradedProductParams.TYPE);
            FeqTradedProductParams l_feqTradedProductParams = TestDBUtility.getFeqTradedProductRow();
            l_feqTradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_feqTradedProductParams);
            
            WEB3FeqProduct l_feqProduct = new WEB3FeqProduct(l_feqProductParams);
            
            //MarketParams
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(l_feqProductParams.getMarketCode());
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);


            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateNewOrder",
                new Class[] {SubAccount.class, ProductTypeEnum.class, NewOrderSpec.class},
                l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "getUnitPrice",
                new Class[] {WEB3FeqTradedProduct.class, WEB3GentradeBranch.class, String.class,
                        double.class, double.class, boolean.class},
                new Double(100));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcForeignCCYAmount",
                    new Class[]
                              { BigDecimal.class, double.class, int.class, String.class},
                    new BigDecimal(200.0));

            //MainAccountParams
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //BranchParams
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            
            //CurrencyParams
            GenCurrencyParams l_currentcyParams = TestDBUtility.getGenCurrencyRow();
            l_currentcyParams.setInstitutionCode("0D");
            l_currentcyParams.setCurrencyCode(l_feqProductParams.getCurrencyCode());
            TestDBUtility.insertWithDel(l_currentcyParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("feq.day.trade.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("feq.day.trade.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            
            WEB3FeqAmountCalcResult l_calResult = new WEB3FeqAmountCalcResult();
            l_calResult.setNetAmount(100);
            l_calResult.setCommissionFee(100);
            l_calResult.setCommissionFeeTax(100);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcFeqAmount",
                new Class[]
                {
                  WEB3GentradeSubAccount.class,
                  WEB3FeqProduct.class,
                  WEB3GentradeMarket.class,
                  Date.class,
                  Date.class,
                  double.class,
                  double.class,
                  boolean.class,
                  boolean.class,
                  boolean.class,
                  String.class},
                  l_calResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcEstimatedBookValuePrice",
                new Class[] {WEB3GentradeSubAccount.class, long.class, TaxTypeEnum.class},
                new BigDecimal("200"));
            
            
            WEB3TPTradingPowerResult l_WEB3TPTradingPowerResult = new WEB3TPTradingPowerResult();
            l_WEB3TPTradingPowerResult.setResultFlg(false);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_WEB3TPTradingPowerResult);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2008-1900, 1,22));
            WEB3FeqSellConfirmResponse l_response = l_implTest.validateOrder(l_request);
            assertEquals("200", l_response.estimatedBookPrice);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01306);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrder_T01()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FeqSellServiceImplForMock l_implTest = new WEB3FeqSellServiceImplForMock();
            WEB3FeqSellCompleteRequest l_request = new WEB3FeqSellCompleteRequest();
            l_request.assetId = "123456";
            l_request.settleDiv = "0";
            l_request.orderQuantity = "500";
            l_request.orderPriceDiv = "0";
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "0";
            l_request.orderPriceDiv = "0";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "0";
            l_request.limitPrice = "0";
            l_request.orderActionId = "1";
            l_request.checkPrice = "100";
            l_request.checkDate = new Date(2008-1900, 1,22);
            
            //AssetParams
            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_assetParams.setAssetId(123456);
            l_assetParams.setProductId(3304148080000L);
            l_assetParams.setQuantity(123);
            l_assetParams.setQuantityCannotSell(321);
            l_assetParams.setAccountId(333812512203L);
            l_assetParams.setSubAccountId(33381251220301L);
            l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
            l_assetParams.setMiniStockDiv("0");
            TestDBUtility.insertWithDel(l_assetParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            //FeqProductParams
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //FeqTradeProductParams
            TestDBUtility.deleteAll(FeqTradedProductParams.TYPE);
            FeqTradedProductParams l_feqTradedProductParams = TestDBUtility.getFeqTradedProductRow();
            l_feqTradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_feqTradedProductParams);
            
            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_feqOrderParams = TestDBUtility.getFeqOrderRow();
            l_feqOrderParams.setOrderId(1);
            TestDBUtility.insertWithDel(l_feqOrderParams);
            
            WEB3FeqProduct l_feqProduct = new WEB3FeqProduct(l_feqProductParams);
            
            //MarketParams
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(l_feqProductParams.getMarketCode());
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);


            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateNewOrder",
                new Class[] {SubAccount.class, ProductTypeEnum.class, NewOrderSpec.class},
                l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "getUnitPrice",
                new Class[] {WEB3FeqTradedProduct.class, WEB3GentradeBranch.class, String.class,
                        double.class, double.class, boolean.class},
                new Double(100));

            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderSubmissionResult l_OrderSubmissionResult = new OrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqOrderManager",
                    "submitNewOrder",
                    new Class[] {
                            SubAccount.class,
                            ProductTypeEnum.class,
                            NewOrderSpec.class,
                            long.class,
                            String.class,
                            boolean.class},
                            l_OrderSubmissionResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcForeignCCYAmount",
                    new Class[]
                              { BigDecimal.class, double.class, int.class, String.class},
                    new BigDecimal(200.0));

            //MainAccountParams
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //BranchParams
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            
            //CurrencyParams
            GenCurrencyParams l_currentcyParams = TestDBUtility.getGenCurrencyRow();
            l_currentcyParams.setInstitutionCode("0D");
            l_currentcyParams.setCurrencyCode(l_feqProductParams.getCurrencyCode());
            TestDBUtility.insertWithDel(l_currentcyParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
//            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
//            l_institutionPreferencesParams.setInstitutionId(33);
//            l_institutionPreferencesParams.setName("feq.day.trade.div");
//            l_institutionPreferencesParams.setNameSerialNo(1);
//            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
//            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
//            l_marketPreferencesParams.setMarketId(3303L);
//            l_marketPreferencesParams.setName("feq.day.trade.market.div");
//            l_marketPreferencesParams.setNameSerialNo(1);
//            l_marketPreferencesParams.setValue("1");
//            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            
            WEB3FeqAmountCalcResult l_calResult = new WEB3FeqAmountCalcResult();
            l_calResult.setNetAmount(100);
            l_calResult.setCommissionFee(100);
            l_calResult.setCommissionFeeTax(100);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcFeqAmount",
                new Class[]
                {
                  WEB3GentradeSubAccount.class,
                  WEB3FeqProduct.class,
                  WEB3GentradeMarket.class,
                  Date.class,
                  Date.class,
                  double.class,
                  double.class,
                  boolean.class,
                  boolean.class,
                  boolean.class,
                  String.class},
                  l_calResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcEstimatedBookValuePrice",
                new Class[] {WEB3GentradeSubAccount.class, long.class, TaxTypeEnum.class},
                new BigDecimal("200"));
            
            
            WEB3TPTradingPowerResult l_WEB3TPTradingPowerResult = new WEB3TPTradingPowerResult();
            l_WEB3TPTradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_WEB3TPTradingPowerResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "reCalcTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class},
                    null);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2008-1900, 1,22));
            WEB3FeqSellCompleteResponse l_response = l_implTest.submitOrder(l_request);
            assertEquals("1", l_response.orderActionId);
        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01306);
//        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrder_T02()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FeqSellServiceImplForMock l_implTest = new WEB3FeqSellServiceImplForMock();
            WEB3FeqSellCompleteRequest l_request = new WEB3FeqSellCompleteRequest();
            l_request.assetId = "123456";
            l_request.settleDiv = "0";
            l_request.orderQuantity = "500";
            l_request.orderPriceDiv = "0";
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "0";
            l_request.orderPriceDiv = "0";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "0";
            l_request.limitPrice = "0";
            l_request.orderActionId = "1";
            l_request.checkPrice = "100";
            l_request.checkDate = new Date(2008-1900, 1,22);
            
            //AssetParams
            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_assetParams.setAssetId(123456);
            l_assetParams.setProductId(3304148080000L);
            l_assetParams.setQuantity(123);
            l_assetParams.setQuantityCannotSell(321);
            l_assetParams.setAccountId(333812512203L);
            l_assetParams.setSubAccountId(33381251220301L);
            l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
            l_assetParams.setMiniStockDiv("0");
            TestDBUtility.insertWithDel(l_assetParams);
            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_feqOrderParams = TestDBUtility.getFeqOrderRow();
            l_feqOrderParams.setOrderId(1);
            TestDBUtility.insertWithDel(l_feqOrderParams);
            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            //FeqProductParams
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //FeqTradeProductParams
            TestDBUtility.deleteAll(FeqTradedProductParams.TYPE);
            FeqTradedProductParams l_feqTradedProductParams = TestDBUtility.getFeqTradedProductRow();
            l_feqTradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_feqTradedProductParams);
            
            WEB3FeqProduct l_feqProduct = new WEB3FeqProduct(l_feqProductParams);
            
            //MarketParams
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(l_feqProductParams.getMarketCode());
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);


            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateNewOrder",
                new Class[] {SubAccount.class, ProductTypeEnum.class, NewOrderSpec.class},
                l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "getUnitPrice",
                new Class[] {WEB3FeqTradedProduct.class, WEB3GentradeBranch.class, String.class,
                        double.class, double.class, boolean.class},
                new Double(100));

            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderSubmissionResult l_OrderSubmissionResult = new OrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqOrderManager",
                    "submitNewOrder",
                    new Class[] {
                            SubAccount.class,
                            ProductTypeEnum.class,
                            NewOrderSpec.class,
                            long.class,
                            String.class,
                            boolean.class},
                            l_OrderSubmissionResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcForeignCCYAmount",
                    new Class[]
                              { BigDecimal.class, double.class, int.class, String.class},
                    new BigDecimal(200.0));

            //MainAccountParams
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //BranchParams
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            
            //CurrencyParams
            GenCurrencyParams l_currentcyParams = TestDBUtility.getGenCurrencyRow();
            l_currentcyParams.setInstitutionCode("0D");
            l_currentcyParams.setCurrencyCode(l_feqProductParams.getCurrencyCode());
            TestDBUtility.insertWithDel(l_currentcyParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("feq.day.trade.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("feq.day.trade.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            
            WEB3FeqAmountCalcResult l_calResult = new WEB3FeqAmountCalcResult();
            l_calResult.setNetAmount(100);
            l_calResult.setCommissionFee(100);
            l_calResult.setCommissionFeeTax(100);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcFeqAmount",
                new Class[]
                {
                  WEB3GentradeSubAccount.class,
                  WEB3FeqProduct.class,
                  WEB3GentradeMarket.class,
                  Date.class,
                  Date.class,
                  double.class,
                  double.class,
                  boolean.class,
                  boolean.class,
                  boolean.class,
                  String.class},
                  l_calResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcEstimatedBookValuePrice",
                new Class[] {WEB3GentradeSubAccount.class, long.class, TaxTypeEnum.class},
                new BigDecimal("200"));
            
            
            WEB3TPTradingPowerResult l_WEB3TPTradingPowerResult = new WEB3TPTradingPowerResult();
            l_WEB3TPTradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_WEB3TPTradingPowerResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "reCalcTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class},
                    null);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2008-1900, 1,22));
            WEB3FeqSellCompleteResponse l_response = l_implTest.submitOrder(l_request);
            assertEquals("1", l_response.orderActionId);
        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01306);
//        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrder_T03()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FeqSellServiceImplForMock l_implTest = new WEB3FeqSellServiceImplForMock();
            WEB3FeqSellCompleteRequest l_request = new WEB3FeqSellCompleteRequest();
            l_request.assetId = "123456";
            l_request.settleDiv = "0";
            l_request.orderQuantity = "500";
            l_request.orderPriceDiv = "0";
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "0";
            l_request.orderPriceDiv = "0";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "0";
            l_request.limitPrice = "0";
            l_request.orderActionId = "1";
            l_request.checkPrice = "100";
            l_request.checkDate = new Date(2008-1900, 1,22);
            
            //AssetParams
            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_assetParams.setAssetId(123456);
            l_assetParams.setProductId(3304148080000L);
            l_assetParams.setQuantity(123);
            l_assetParams.setQuantityCannotSell(321);
            l_assetParams.setAccountId(333812512203L);
            l_assetParams.setSubAccountId(33381251220301L);
            l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
            l_assetParams.setMiniStockDiv("0");
            TestDBUtility.insertWithDel(l_assetParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            //FeqProductParams
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //FeqTradeProductParams
            TestDBUtility.deleteAll(FeqTradedProductParams.TYPE);
            FeqTradedProductParams l_feqTradedProductParams = TestDBUtility.getFeqTradedProductRow();
            l_feqTradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_feqTradedProductParams);
            
            WEB3FeqProduct l_feqProduct = new WEB3FeqProduct(l_feqProductParams);
            
            //MarketParams
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(l_feqProductParams.getMarketCode());
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);


            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateNewOrder",
                new Class[] {SubAccount.class, ProductTypeEnum.class, NewOrderSpec.class},
                l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "getUnitPrice",
                new Class[] {WEB3FeqTradedProduct.class, WEB3GentradeBranch.class, String.class,
                        double.class, double.class, boolean.class},
                new Double(100));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcForeignCCYAmount",
                    new Class[]
                              { BigDecimal.class, double.class, int.class, String.class},
                    new BigDecimal(200.0));

            //MainAccountParams
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //BranchParams
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            
            //CurrencyParams
            GenCurrencyParams l_currentcyParams = TestDBUtility.getGenCurrencyRow();
            l_currentcyParams.setInstitutionCode("0D");
            l_currentcyParams.setCurrencyCode(l_feqProductParams.getCurrencyCode());
            TestDBUtility.insertWithDel(l_currentcyParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("feq.day.trade.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("feq.day.trade.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            
            WEB3FeqAmountCalcResult l_calResult = new WEB3FeqAmountCalcResult();
            l_calResult.setNetAmount(100);
            l_calResult.setCommissionFee(100);
            l_calResult.setCommissionFeeTax(100);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcFeqAmount",
                new Class[]
                {
                  WEB3GentradeSubAccount.class,
                  WEB3FeqProduct.class,
                  WEB3GentradeMarket.class,
                  Date.class,
                  Date.class,
                  double.class,
                  double.class,
                  boolean.class,
                  boolean.class,
                  boolean.class,
                  String.class},
                  l_calResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcEstimatedBookValuePrice",
                new Class[] {WEB3GentradeSubAccount.class, long.class, TaxTypeEnum.class},
                new BigDecimal("200"));
            
            
            WEB3TPTradingPowerResult l_WEB3TPTradingPowerResult = new WEB3TPTradingPowerResult();
            l_WEB3TPTradingPowerResult.setResultFlg(false);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_WEB3TPTradingPowerResult);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2008-1900, 1,22));
            WEB3FeqSellCompleteResponse l_response = l_implTest.submitOrder(l_request);
            assertEquals("1", l_response.orderActionId);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01306);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void deletDB()
    {
        try
        {
            //AssetParams
            TestDBUtility.deleteAll(AssetParams.TYPE);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            //FeqProductParams
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public class WEB3FeqSellServiceImplForMock extends WEB3FeqSellServiceImpl
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
                SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
                l_subAccountParams.setInstitutionCode("0D");
                l_subAccountParams.setInstitutionId(33);
                l_subAccountParams.setBranchId(456789);
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
        
        public Trader getTrader() throws WEB3SystemLayerException
        {
            WEB3GentradeTrader l_trader = null;
            try
            {
                //TraderParams
                TraderParams l_traderParams = TestDBUtility.getTraderRow();
                l_traderParams.setTraderId(123456L);
                TestDBUtility.insertWithDel(l_traderParams);
                
                l_trader = new WEB3GentradeTrader(123456,false);
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
            return l_trader;
        }
        
        public String getLoginChannel()
        {
             return "jiddk";
        }
        
    }

}
@

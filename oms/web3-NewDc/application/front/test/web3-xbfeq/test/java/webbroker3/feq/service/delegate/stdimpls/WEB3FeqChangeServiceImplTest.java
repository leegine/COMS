head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.18.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqChangeServiceImplTest.java;


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
import java.util.Map;
import java.util.Set;

import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginTypeInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductParams;

import test.util.TestDBUtility;

import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.define.WEB3MarketPreferencesNameDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.feq.WEB3FeqAmountCalcResult;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.data.FeqTickValuesMstParams;
import webbroker3.feq.message.WEB3FeqChangeCompleteRequest;
import webbroker3.feq.message.WEB3FeqChangeCompleteResponse;
import webbroker3.feq.message.WEB3FeqChangeConfirmRequest;
import webbroker3.feq.message.WEB3FeqChangeConfirmResponse;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EquityCommAccountCondMstParams;
import webbroker3.gentrade.data.EquityCommCondParams;
import webbroker3.gentrade.data.FeqBranchMarketDealtCondParams;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.gentrade.data.InstBranchProductParams;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqChangeServiceImplTest extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqChangeServiceImplTest.class);

    public WEB3FeqChangeServiceImplTest(String arg0)
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

    WEB3FeqChangeServiceImpl l_impl = new WEB3FeqChangeServiceImpl();

//    public void testGetInputScreen_case0001()
//    {
//        final String STR_METHOD_NAME = "testGetInputScreen_case0001()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            MOCK_MANAGER.setIsMockUsed(true);
//
//            WEB3FeqChangeInputRequest l_request = new WEB3FeqChangeInputRequest();
//            l_request.orderId = "1001";
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "getAccountId",
//                new Class[] {},
//                new Long(1001L));
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.feq.WEB3FeqBizLogicProvider",
//                    "calcForeignCCYAmount",
//                    new Class[]
//                              { BigDecimal.class, double.class, int.class, String.class},
//                    new BigDecimal(1.0));
//
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
//                WEB3DateUtility.getDate("20080121","yyyyMMdd"));
//
//
//            TestDBUtility.deleteAll(MainAccountParams.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(1001L);
//            l_mainAccountParams.setMarginGenAccOpenDiv(
//                WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//
//            TestDBUtility.deleteAll(SubAccountParams.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(1001L);
//            l_subAccountParams.setSubAccountId(1001L);
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//
//            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
//            FeqOrderParams l_feqOrderParams = TestDBUtility.getFeqOrderRow();
//            l_feqOrderParams.setOrderId(1001L);
//            l_feqOrderParams.setSubAccountId(1001L);
//            TestDBUtility.insertWithDel(l_feqOrderParams);
//
//            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
//            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
//            l_feqOrderUnitParams.setOrderId(1001L);
//            l_feqOrderUnitParams.setSubAccountId(1001L);
//            l_feqOrderUnitParams.setProductId(1001L);
//            l_feqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            l_feqOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
//            l_feqOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
//            l_feqOrderUnitParams.setExecutionConditionType(FeqExecutionConditionType.AT_MARKET_OPEN);
//            l_feqOrderUnitParams.setFirstOrderUnitId(1001L);
//            l_feqOrderUnitParams.setOrderUnitId(1001L);
//            l_feqOrderUnitParams.setMarketId(1001L);
//            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
////
////            l_feqOrderUnitParams.setOrderId(1233);
////            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
//
//            TestDBUtility.deleteAll(ProductParams.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(1001L);
//            TestDBUtility.insertWithDel(l_productParams);
//
//            TestDBUtility.deleteAll(FeqProductParams.TYPE);
//            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
//            l_feqProductParams.setProductId(1001L);
//            TestDBUtility.insertWithDel(l_feqProductParams);
//
//            TestDBUtility.deleteAll(InstitutionParams.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            l_institutionParams.setInstitutionId(33);
//            l_institutionParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_institutionParams);
//
//            TestDBUtility.deleteAll(MarketParams.TYPE);
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setMarketCode("N2");
//            l_marketParams.setMarketId(1001);
//            TestDBUtility.insertWithDel(l_marketParams);
//
//            TestDBUtility.deleteAll(BranchParams.TYPE);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setBranchId(33381);
//            l_branchParams.setBranchCode("381");
//            TestDBUtility.insertWithDel(l_branchParams);
//
//            TestDBUtility.deleteAll(FeqBranchMarketDealtCondParams.TYPE);
//            FeqBranchMarketDealtCondParams l_feqBranchMarketDealtCondParams =
//                TestDBUtility.getFeqBranchMarketDealtCondRow();
//            l_feqBranchMarketDealtCondParams.setBranchCode("381");
//            l_feqBranchMarketDealtCondParams.setInstitutionCode("0D");
//            l_feqBranchMarketDealtCondParams.setMarketCode("N2");
//            TestDBUtility.insertWithDel(l_feqBranchMarketDealtCondParams);
//
//            TestDBUtility.deleteAll(TradedProductParams.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setProductId(1001L);
//            l_tradedProductParams.setMarketId(1001l);
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//
//            TestDBUtility.deleteAll(FeqTradedProductParams.TYPE);
//            FeqTradedProductParams l_feqTradedProductParams =
//                TestDBUtility.getFeqTradedProductRow();
//            l_feqTradedProductParams.setTradedProductId(1006160060005l);
//            l_feqTradedProductParams.setTradeStop(0);
//            l_feqTradedProductParams.setBuyStop(0);
//            l_feqTradedProductParams.setProductId(1001L);
//            l_feqTradedProductParams.setMarketId(1001L);
//            l_feqTradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_feqTradedProductParams);
//
//            TestDBUtility.deleteAll(AssetParams.TYPE);
//            AssetParams l_assetParams = TestDBUtility.getAssetRow();
//            l_assetParams.setAccountId(1001L);
//            l_assetParams.setSubAccountId(1001L);
//            l_assetParams.setProductId(1001L);
//            l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
//            l_assetParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
//            TestDBUtility.insertWithDel(l_assetParams);
//
//            TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
//            EnableOrderConditionParams l_enableOrderConditionParams =
//                TestDBUtility.getEnableOrderConditionParamsRow();
//            l_enableOrderConditionParams.setInstitutionCode("0D");
//            l_enableOrderConditionParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
//            l_enableOrderConditionParams.setMarketCode("N2");
//            l_enableOrderConditionParams.setFutureOptionDiv("0");
//            l_enableOrderConditionParams.setMarginTradingDiv("0");
//            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
//
//            WEB3FeqChangeInputResponse l_response = l_impl.getInputScreen(l_request);
//            assertEquals(l_response.estimatedBookPrice, "1");
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(l_ex.getMessage(), l_ex);
//            fail();
//        }
//    }

    public void testValidateOrder_case0001()
    {
        final String STR_METHOD_NAME = "testValidateOrder_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            WEB3FeqChangeConfirmRequest l_request = new WEB3FeqChangeConfirmRequest();
            l_request.orderId = "1001";
            l_request.orderQuantity = "12";
            l_request.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
            l_request.expirationDate = WEB3DateUtility.getDate("20080121","yyyyMMdd");
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            l_request.limitPrice = "1";
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.wLimitOrderPriceDiv = null;
//            l_request.wLimitPrice = "2";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            LoginInfo l_loginInfo = new LoginInfoTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20080121","yyyyMMdd"));

            OrderValidationResult l_orderValidationResult = OrderValidationResult.VALIDATION_OK_RESULT;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateChangeOrder",
                new Class[] {
                    SubAccount.class,
                    ChangeOrderSpec.class},
                l_orderValidationResult);
            
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            WEB3GentradeCurrency l_WEB3GentradeCurrency = new WEB3GentradeCurrency(l_genCurrencyParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqProduct",
                    "getCurrency",
                    new Class[] {},
                    l_WEB3GentradeCurrency);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "1");

            WEB3FeqAmountCalcResult l_result = new WEB3FeqAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider", 
                "calcChangeFeqAmount", new Class[]
                {WEB3GentradeSubAccount.class, WEB3FeqProduct.class,
                WEB3GentradeMarket.class, Date.class, double.class, double.class, boolean.class,
                boolean.class, boolean.class, FeqOrderUnit.class},
                l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcForeignCCYAmount",
                    new Class[]
                              { BigDecimal.class, double.class, int.class, String.class},
                    new BigDecimal(1.0));

            TestDBUtility.deleteAll(EquityCommCondParams.TYPE);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("0D");
            l_equityCommCondParams.setCommProductCode("40");
            l_equityCommCondParams.setRegNo("01 ");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("1");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20080120","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20080122","yyyyMMdd"));
            l_equityCommCondParams.setMaxTurnover(50.0);
            l_equityCommCondParams.setMinTurnover(0.0001);
            l_equityCommCondParams.setChargeRatio(1.1);
            l_equityCommCondParams.setAddedPrice(1.1);
            l_equityCommCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            l_equityCommCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            l_equityCommCondParams.setCommitionPerUnit(1001);
            TestDBUtility.insertWithDel(l_equityCommCondParams);

            TestDBUtility.deleteAll(EquityCommAccountCondMstParams.TYPE);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams =
                TestDBUtility.getEquityCommAccountCondMstRow();
            l_equityCommAccountCondMstParams.setInstitutionCode("0D");
            l_equityCommAccountCondMstParams.setBranchId(33381);
            l_equityCommAccountCondMstParams.setAccountId(1001);
            l_equityCommAccountCondMstParams.setCommProductCode("40");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20080121");
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);

            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(33381);
            l_instBranchProductParams.setCommissionProductCode("40");
            l_instBranchProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            l_instBranchProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_instBranchProductParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv(
                WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

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
            l_feqOrderUnitParams.setOrderType(OrderTypeEnum.ASSET_IN);
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
            l_feqTradedProductParams.setSellMinQty(11d);
            l_feqTradedProductParams.setSellLotSize(1);
            TestDBUtility.insertWithDel(l_feqTradedProductParams);

            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(1001L);
            l_assetParams.setSubAccountId(1001L);
            l_assetParams.setProductId(1001L);
            l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
            l_assetParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
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

            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
//            MarketPreferencesParams l_marketPreferencesParams =
//                TestDBUtility.getMarketPreferencesRow();
//            l_marketPreferencesParams.setMarketId(1001L);
//            l_marketPreferencesParams.setName(WEB3MarketPreferencesNameDef.FEQ_ORDER_QUANTITY_LIMIT);
//            l_marketPreferencesParams.setValue("1");
//            TestDBUtility.insertWithDel(l_marketPreferencesParams);

            TestDBUtility.deleteAll(FeqTickValuesMstParams.TYPE);
            FeqTickValuesMstParams l_feqTickValuesMstParams = new FeqTickValuesMstParams();
            l_feqTickValuesMstParams.setMarketCode("N2");
            l_feqTickValuesMstParams.setLowPrice(0.3);
            l_feqTickValuesMstParams.setCapPrice(2.0);
            l_feqTickValuesMstParams.setScale(1);
            l_feqTickValuesMstParams.setTickValue(0.1);
            l_feqTickValuesMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            l_feqTickValuesMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_feqTickValuesMstParams);
            WEB3FeqChangeConfirmResponse l_response = l_impl.validateOrder(l_request);
            assertEquals("1", l_response.estimatedBookPrice);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testValidateOrder_case0002()
    {
        final String STR_METHOD_NAME = "testValidateOrder_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            WEB3FeqChangeConfirmRequest l_request = new WEB3FeqChangeConfirmRequest();
            l_request.orderId = "1001";
            l_request.orderQuantity = "12";
            l_request.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
            l_request.expirationDate = WEB3DateUtility.getDate("20080121","yyyyMMdd");
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            l_request.limitPrice = "1";
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.wLimitOrderPriceDiv = null;
//            l_request.wLimitPrice = "2";
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            WEB3GentradeCurrency l_WEB3GentradeCurrency = new WEB3GentradeCurrency(l_genCurrencyParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqProduct",
                    "getCurrency",
                    new Class[] {},
                    l_WEB3GentradeCurrency);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            LoginInfo l_loginInfo = new LoginInfoTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20080121","yyyyMMdd"));

            OrderValidationResult l_orderValidationResult = OrderValidationResult.VALIDATION_OK_RESULT;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateChangeOrder",
                new Class[] {
                    SubAccount.class,
                    ChangeOrderSpec.class},
                l_orderValidationResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "1");

            WEB3FeqAmountCalcResult l_result = new WEB3FeqAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider", 
                "calcChangeFeqAmount", new Class[]
                {WEB3GentradeSubAccount.class, WEB3FeqProduct.class,
                WEB3GentradeMarket.class, Date.class, double.class, double.class, boolean.class,
                boolean.class, boolean.class, FeqOrderUnit.class},
                l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcForeignCCYAmount",
                    new Class[]
                              { BigDecimal.class, double.class, int.class, String.class},
                    new BigDecimal(1.0));

            
            WEB3TPTradingPowerResult l_WEB3TPTradingPowerResult = new WEB3TPTradingPowerResult();
            l_WEB3TPTradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_WEB3TPTradingPowerResult);

            TestDBUtility.deleteAll(EquityCommCondParams.TYPE);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("0D");
            l_equityCommCondParams.setCommProductCode("40");
            l_equityCommCondParams.setRegNo("01 ");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("1");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20080120","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20080122","yyyyMMdd"));
            l_equityCommCondParams.setMaxTurnover(50.0);
            l_equityCommCondParams.setMinTurnover(0.0001);
            l_equityCommCondParams.setChargeRatio(1.1);
            l_equityCommCondParams.setAddedPrice(1.1);
            l_equityCommCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            l_equityCommCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            l_equityCommCondParams.setCommitionPerUnit(1001);
            TestDBUtility.insertWithDel(l_equityCommCondParams);

            TestDBUtility.deleteAll(EquityCommAccountCondMstParams.TYPE);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams =
                TestDBUtility.getEquityCommAccountCondMstRow();
            l_equityCommAccountCondMstParams.setInstitutionCode("0D");
            l_equityCommAccountCondMstParams.setBranchId(33381);
            l_equityCommAccountCondMstParams.setAccountId(1001);
            l_equityCommAccountCondMstParams.setCommProductCode("40");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20080121");
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);

            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(33381);
            l_instBranchProductParams.setCommissionProductCode("40");
            l_instBranchProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            l_instBranchProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_instBranchProductParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv(
                WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

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
            l_feqOrderUnitParams.setOrderType(OrderTypeEnum.ASSET_IN);
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
            l_feqTradedProductParams.setSellMinQty(11d);
            l_feqTradedProductParams.setSellLotSize(1);
            TestDBUtility.insertWithDel(l_feqTradedProductParams);

            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(1001L);
            l_assetParams.setSubAccountId(1001L);
            l_assetParams.setProductId(1001L);
            l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
            l_assetParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
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

            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_marketPreferencesParams =
                TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(1001L);
            l_marketPreferencesParams.setName(WEB3MarketPreferencesNameDef.FEQ_DAY_TRADE_MARKET_DIV);
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setName(WEB3InstitutionPreferencesNameDef.FEQ_DAY_TRADE_DIV);
            l_institutionPreferencesParams.setValue("1");
            l_institutionPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            TestDBUtility.deleteAll(FeqTickValuesMstParams.TYPE);
            FeqTickValuesMstParams l_feqTickValuesMstParams = new FeqTickValuesMstParams();
            l_feqTickValuesMstParams.setMarketCode("N2");
            l_feqTickValuesMstParams.setLowPrice(0.3);
            l_feqTickValuesMstParams.setCapPrice(2.0);
            l_feqTickValuesMstParams.setScale(1);
            l_feqTickValuesMstParams.setTickValue(0.1);
            l_feqTickValuesMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            l_feqTickValuesMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_feqTickValuesMstParams);
            WEB3FeqChangeConfirmResponse l_response = l_impl.validateOrder(l_request);
            assertEquals("0", l_response.estimatedBookPrice);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    public void testSubmitOrder_case0001()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            WEB3FeqChangeCompleteRequest l_request = new WEB3FeqChangeCompleteRequest();
            l_request.orderId = "1001";
            l_request.orderQuantity = "12";
            l_request.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
            l_request.expirationDate = WEB3DateUtility.getDate("20080121","yyyyMMdd");
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            l_request.limitPrice = "1";
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.wLimitOrderPriceDiv = null;
            l_request.checkPrice = "100";
            l_request.checkDate = new Date(2008-1900, 1,22);
//            l_request.wLimitPrice = "2";

            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            WEB3GentradeCurrency l_WEB3GentradeCurrency = new WEB3GentradeCurrency(l_genCurrencyParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqProduct",
                    "getCurrency",
                    new Class[] {},
                    l_WEB3GentradeCurrency);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            LoginInfo l_loginInfo = new LoginInfoTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20080121","yyyyMMdd"));

            OrderValidationResult l_orderValidationResult = OrderValidationResult.VALIDATION_OK_RESULT;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateChangeOrder",
                new Class[] {
                    SubAccount.class,
                    ChangeOrderSpec.class},
                l_orderValidationResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "1");

            WEB3FeqAmountCalcResult l_result = new WEB3FeqAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider", 
                "calcChangeFeqAmount", new Class[]
                {WEB3GentradeSubAccount.class, WEB3FeqProduct.class,
                WEB3GentradeMarket.class, Date.class, double.class, double.class, boolean.class,
                boolean.class, boolean.class, FeqOrderUnit.class},
                l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcForeignCCYAmount",
                    new Class[]
                              { BigDecimal.class, double.class, int.class, String.class},
                    new BigDecimal(1.0));

            
            WEB3TPTradingPowerResult l_WEB3TPTradingPowerResult = new WEB3TPTradingPowerResult();
            l_WEB3TPTradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_WEB3TPTradingPowerResult);

            TestDBUtility.deleteAll(EquityCommCondParams.TYPE);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("0D");
            l_equityCommCondParams.setCommProductCode("40");
            l_equityCommCondParams.setRegNo("01 ");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("1");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20080120","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20080122","yyyyMMdd"));
            l_equityCommCondParams.setMaxTurnover(50.0);
            l_equityCommCondParams.setMinTurnover(0.0001);
            l_equityCommCondParams.setChargeRatio(1.1);
            l_equityCommCondParams.setAddedPrice(1.1);
            l_equityCommCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            l_equityCommCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            l_equityCommCondParams.setCommitionPerUnit(1001);
            TestDBUtility.insertWithDel(l_equityCommCondParams);

            TestDBUtility.deleteAll(EquityCommAccountCondMstParams.TYPE);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams =
                TestDBUtility.getEquityCommAccountCondMstRow();
            l_equityCommAccountCondMstParams.setInstitutionCode("0D");
            l_equityCommAccountCondMstParams.setBranchId(33381);
            l_equityCommAccountCondMstParams.setAccountId(1001);
            l_equityCommAccountCondMstParams.setCommProductCode("40");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20080121");
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);

            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(33381);
            l_instBranchProductParams.setCommissionProductCode("40");
            l_instBranchProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            l_instBranchProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_instBranchProductParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv(
                WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

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
            l_feqOrderUnitParams.setOrderType(OrderTypeEnum.ASSET_IN);
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
            l_feqTradedProductParams.setSellMinQty(11d);
            l_feqTradedProductParams.setSellLotSize(1);
            TestDBUtility.insertWithDel(l_feqTradedProductParams);

            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(1001L);
            l_assetParams.setSubAccountId(1001L);
            l_assetParams.setProductId(1001L);
            l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
            l_assetParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
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

            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
//            MarketPreferencesParams l_marketPreferencesParams =
//                TestDBUtility.getMarketPreferencesRow();
//            l_marketPreferencesParams.setMarketId(1001L);
//            l_marketPreferencesParams.setName(WEB3MarketPreferencesNameDef.FEQ_DAY_TRADE_MARKET_DIV);
//            l_marketPreferencesParams.setValue("1");
//            TestDBUtility.insertWithDel(l_marketPreferencesParams);

            TestDBUtility.deleteAll(FeqTickValuesMstParams.TYPE);
            FeqTickValuesMstParams l_feqTickValuesMstParams = new FeqTickValuesMstParams();
            l_feqTickValuesMstParams.setMarketCode("N2");
            l_feqTickValuesMstParams.setLowPrice(0.3);
            l_feqTickValuesMstParams.setCapPrice(2.0);
            l_feqTickValuesMstParams.setScale(1);
            l_feqTickValuesMstParams.setTickValue(0.1);
            l_feqTickValuesMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            l_feqTickValuesMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_feqTickValuesMstParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "reCalcTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class},
                    null);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderSubmissionResult l_OrderSubmissionResult = new OrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqOrderManager",
                    "submitChangeOrder",
                    new Class[] {
                            SubAccount.class,
                            ChangeOrderSpec.class,
                            String.class,
                            boolean.class},
                            l_OrderSubmissionResult);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2008-1900, 1,22));
            WEB3FeqChangeCompleteResponse l_response = l_impl.submitOrder(l_request);
            assertEquals("1001", l_response.orderId);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testSubmitOrder_case0002()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            WEB3FeqChangeCompleteRequest l_request = new WEB3FeqChangeCompleteRequest();
            l_request.orderId = "1001";
            l_request.orderQuantity = "12";
            l_request.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
            l_request.expirationDate = WEB3DateUtility.getDate("20080121","yyyyMMdd");
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            l_request.limitPrice = "1";
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.wLimitOrderPriceDiv = null;
            l_request.checkPrice = "100";
            l_request.checkDate = new Date(2008-1900, 1,22);
//            l_request.wLimitPrice = "2";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            WEB3GentradeCurrency l_WEB3GentradeCurrency = new WEB3GentradeCurrency(l_genCurrencyParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqProduct",
                    "getCurrency",
                    new Class[] {},
                    l_WEB3GentradeCurrency);
            
            LoginInfo l_loginInfo = new LoginInfoTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20080121","yyyyMMdd"));

            OrderValidationResult l_orderValidationResult = OrderValidationResult.VALIDATION_OK_RESULT;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateChangeOrder",
                new Class[] {
                    SubAccount.class,
                    ChangeOrderSpec.class},
                l_orderValidationResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "1");

            WEB3FeqAmountCalcResult l_result = new WEB3FeqAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider", 
                "calcChangeFeqAmount", new Class[]
                {WEB3GentradeSubAccount.class, WEB3FeqProduct.class,
                WEB3GentradeMarket.class, Date.class, double.class, double.class, boolean.class,
                boolean.class, boolean.class, FeqOrderUnit.class},
                l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcForeignCCYAmount",
                    new Class[]
                              { BigDecimal.class, double.class, int.class, String.class},
                    new BigDecimal(1.0));

            
            WEB3TPTradingPowerResult l_WEB3TPTradingPowerResult = new WEB3TPTradingPowerResult();
            l_WEB3TPTradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_WEB3TPTradingPowerResult);

            TestDBUtility.deleteAll(EquityCommCondParams.TYPE);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("0D");
            l_equityCommCondParams.setCommProductCode("40");
            l_equityCommCondParams.setRegNo("01 ");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("1");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20080120","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20080122","yyyyMMdd"));
            l_equityCommCondParams.setMaxTurnover(50.0);
            l_equityCommCondParams.setMinTurnover(0.0001);
            l_equityCommCondParams.setChargeRatio(1.1);
            l_equityCommCondParams.setAddedPrice(1.1);
            l_equityCommCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            l_equityCommCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            l_equityCommCondParams.setCommitionPerUnit(1001);
            TestDBUtility.insertWithDel(l_equityCommCondParams);

            TestDBUtility.deleteAll(EquityCommAccountCondMstParams.TYPE);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams =
                TestDBUtility.getEquityCommAccountCondMstRow();
            l_equityCommAccountCondMstParams.setInstitutionCode("0D");
            l_equityCommAccountCondMstParams.setBranchId(33381);
            l_equityCommAccountCondMstParams.setAccountId(1001);
            l_equityCommAccountCondMstParams.setCommProductCode("40");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20080121");
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);

            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(33381);
            l_instBranchProductParams.setCommissionProductCode("40");
            l_instBranchProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            l_instBranchProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_instBranchProductParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv(
                WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

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
            l_feqOrderUnitParams.setOrderType(OrderTypeEnum.ASSET_IN);
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
            l_feqTradedProductParams.setSellMinQty(11d);
            l_feqTradedProductParams.setSellLotSize(1);
            TestDBUtility.insertWithDel(l_feqTradedProductParams);

            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(1001L);
            l_assetParams.setSubAccountId(1001L);
            l_assetParams.setProductId(1001L);
            l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
            l_assetParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
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

            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_marketPreferencesParams =
                TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(1001L);
            l_marketPreferencesParams.setName(WEB3MarketPreferencesNameDef.FEQ_DAY_TRADE_MARKET_DIV);
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setName(WEB3InstitutionPreferencesNameDef.FEQ_DAY_TRADE_DIV);
            l_institutionPreferencesParams.setValue("1");
            l_institutionPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            TestDBUtility.deleteAll(FeqTickValuesMstParams.TYPE);
            FeqTickValuesMstParams l_feqTickValuesMstParams = new FeqTickValuesMstParams();
            l_feqTickValuesMstParams.setMarketCode("N2");
            l_feqTickValuesMstParams.setLowPrice(0.3);
            l_feqTickValuesMstParams.setCapPrice(2.0);
            l_feqTickValuesMstParams.setScale(1);
            l_feqTickValuesMstParams.setTickValue(0.1);
            l_feqTickValuesMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            l_feqTickValuesMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080121","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_feqTickValuesMstParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "reCalcTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class},
                    null);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderSubmissionResult l_OrderSubmissionResult = new OrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqOrderManager",
                    "submitChangeOrder",
                    new Class[] {
                            SubAccount.class,
                            ChangeOrderSpec.class,
                            String.class,
                            boolean.class},
                            l_OrderSubmissionResult);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2008-1900, 1,22));
            WEB3FeqChangeCompleteResponse l_response = l_impl.submitOrder(l_request);
            assertEquals("1001", l_response.orderId);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public class LoginInfoTest implements LoginInfo
    {

        public LoginTypeInfo getLoginTypeInfo() {
            // TODO Auto-generated method stub
            return null;
        }

        public long getLoginId() {
            // TODO Auto-generated method stub
            return 1001;
        }

        public long getLoginTypeId() {
            // TODO Auto-generated method stub
            return 0;
        }

        public String getUsername() {
            // TODO Auto-generated method stub
            return null;
        }

        public String getInitialPassword() {
            // TODO Auto-generated method stub
            return null;
        }

        public Set getSubordinateLoginGroups() {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isDisabled() {
            // TODO Auto-generated method stub
            return false;
        }

        public Set getReachableAccountIds() {
            // TODO Auto-generated method stub
            return null;
        }

        public Set getReachableLoginIds() {
            // TODO Auto-generated method stub
            return null;
        }

        public Set getReachableLogins() {
            // TODO Auto-generated method stub
            return null;
        }

        public Map getAttributes() {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isAccountReachable(long arg0) {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean hasFailedLogin() {
            // TODO Auto-generated method stub
            return false;
        }

        public int getFailureCount() {
            // TODO Auto-generated method stub
            return 0;
        }

        public Date getLastFailureTimestamp() {
            // TODO Auto-generated method stub
            return null;
        }
        
    }
}
@

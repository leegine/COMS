head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.16.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqCancelServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
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
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqTradedProductImpl;

import test.util.TestDBUtility;

import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.message.WEB3FeqCancelConfirmRequest;
import webbroker3.feq.message.WEB3FeqCancelConfirmResponse;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.FeqBranchMarketDealtCondParams;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqCancelServiceImplTest extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqCancelServiceImplTest.class);

    public WEB3FeqCancelServiceImplTest(String arg0)
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

    public void testValidateOrder_case0001()
    {
        final String STR_METHOD_NAME = "testValidateOrder_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcForeignCCYAmount",
                    new Class[]
                              { BigDecimal.class, double.class, int.class, String.class},
                    new BigDecimal(1.0));
            

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20080121","yyyyMMdd"));

            WEB3FeqCancelConfirmRequest l_request = new WEB3FeqCancelConfirmRequest();
            l_request.orderId = "1001";

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
            l_feqOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_feqOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_feqOrderUnitParams.setExecutionConditionType(FeqExecutionConditionType.AT_MARKET_OPEN);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

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

            TradedProduct l_TradedProduct = new FeqTradedProductImpl(l_tradedProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqOrderManager",
                    "validateTradedProduct",
                    new Class[]
                              { WEB3FeqProduct.class,
                            WEB3GentradeMarket.class,
                            boolean.class},
                            l_TradedProduct);
            
            TestDBUtility.deleteAll(FeqTradedProductParams.TYPE);
            FeqTradedProductParams l_feqTradedProductParams =
                TestDBUtility.getFeqTradedProductRow();
            l_feqTradedProductParams.setTradedProductId(1006160060005l);
            l_feqTradedProductParams.setTradeStop(0);
            l_feqTradedProductParams.setBuyStop(0);
            TestDBUtility.insertWithDel(l_feqTradedProductParams);
            
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "1");
            
            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(1001L);
            l_assetParams.setSubAccountId(1001L);
            l_assetParams.setProductId(1001L);
            l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
            l_assetParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
            TestDBUtility.insertWithDel(l_assetParams);
            
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams =TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("001");
            TestDBUtility.insertWithDel(l_genCurrencyParams);

            WEB3FeqCancelServiceImpl l_impl = new WEB3FeqCancelServiceImpl();
            WEB3FeqCancelConfirmResponse l_response = l_impl.validateOrder(l_request);
            assertEquals(l_response.estimatedBookPrice, "1");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }
}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.18.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFeqOrderAndExecutionServiceImplTest_wubo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;

import test.util.TestDBUtility;

import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.data.ForeignCostParams;
import webbroker3.feq.message.WEB3FeqExecDetailInfoUnit;
import webbroker3.feq.message.WEB3FeqOrderAndExecutionUnit;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.EquityCommAccountCondMstParams;
import webbroker3.gentrade.data.EquityCommCondMstParams;
import webbroker3.gentrade.data.EquityCommCondParams;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.gentrade.data.InstBranchProductParams;
import webbroker3.gentrade.data.TaxRateTableParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFeqOrderAndExecutionServiceImplTest_wubo extends TestBaseForMock
{

    public WEB3AdminFeqOrderAndExecutionServiceImplTest_wubo(String arg0)
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
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderAndExecutionServiceImplTest_wubo.class);

    WEB3AdminFeqOrderAndExecutionServiceImpl l_impl = new WEB3AdminFeqOrderAndExecutionServiceImpl();

    public void testValidateFeqAmount_case0001()
    {
        final String STR_METHOD_NAME = "testValidateFeqAmount_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TaxRateTableParams.TYPE);
            TaxRateTableParams l_taxRateTableParams = new TaxRateTableParams();
            l_taxRateTableParams.setInstitutionCode("0D");
            l_taxRateTableParams.setTaxType("10");
            l_taxRateTableParams.setTaxRate(1.1);
            l_taxRateTableParams.setAppliStartDate(WEB3DateUtility.getDate("20080120","yyyyMMdd"));
            l_taxRateTableParams.setAppliEndDate(WEB3DateUtility.getDate("20080128","yyyyMMdd"));
            l_taxRateTableParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080124","yyyyMMdd"));
            l_taxRateTableParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080124","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_taxRateTableParams);

            TestDBUtility.deleteAll(EquityCommCondMstParams.TYPE);
            EquityCommCondMstParams l_equityCommCondMstParams = new EquityCommCondMstParams();
            l_equityCommCondMstParams.setInstitutionCode("0D");
            l_equityCommCondMstParams.setCommProductCode("40");
            l_equityCommCondMstParams.setRegNo("01 ");
            l_equityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20080120","yyyyMMdd"));
            l_equityCommCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20080128","yyyyMMdd"));
            l_equityCommCondMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080124","yyyyMMdd"));
            l_equityCommCondMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080124","yyyyMMdd"));
            l_equityCommCondMstParams.setMinCommission(1.1);
            l_equityCommCondMstParams.setCommissionCourseDiv("1");
            TestDBUtility.insertWithDel(l_equityCommCondMstParams);

            TestDBUtility.deleteAll(EquityCommCondParams.TYPE);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("0D");
            l_equityCommCondParams.setCommProductCode("40");
            l_equityCommCondParams.setRegNo("01 ");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20080124","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("1");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20080120","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20080128","yyyyMMdd"));
            l_equityCommCondParams.setMaxTurnover(50.0);
            l_equityCommCondParams.setMinTurnover(0.0001);
            l_equityCommCondParams.setChargeRatio(1.1);
            l_equityCommCondParams.setAddedPrice(1.1);
            l_equityCommCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080124","yyyyMMdd"));
            l_equityCommCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080124","yyyyMMdd"));
            l_equityCommCondParams.setCommitionPerUnit(1001);
            TestDBUtility.insertWithDel(l_equityCommCondParams);

            TestDBUtility.deleteAll(EquityCommAccountCondMstParams.TYPE);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams =
                TestDBUtility.getEquityCommAccountCondMstRow();
            l_equityCommAccountCondMstParams.setInstitutionCode("0D");
            l_equityCommAccountCondMstParams.setBranchId(33381);
            l_equityCommAccountCondMstParams.setAccountId(1001);
            l_equityCommAccountCondMstParams.setCommProductCode("40");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20080124");
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);

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

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("N2");
            l_marketParams.setMarketId(1001);
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("001");
            l_genCurrencyParams.setCurrencyName("test");
            TestDBUtility.insertWithDel(l_genCurrencyParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_feqProductParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //InstBranchProduct
            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(33381);
            l_instBranchProductParams.setCommissionProductCode("40");
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            

            String l_strInstitutionCode = "0D";
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(1001);
            WEB3GentradeCurrency l_currency = new WEB3GentradeCurrency(l_genCurrencyParams);
            WEB3FeqProduct l_product = new WEB3FeqProduct(l_productParams);
            TaxTypeEnum l_taxType = TaxTypeEnum.NORMAL;
            WEB3FeqOrderAndExecutionUnit l_execInputInfo = new WEB3FeqOrderAndExecutionUnit();
            WEB3FeqExecDetailInfoUnit l_feqExecDetailInfoUnit = new WEB3FeqExecDetailInfoUnit();
            l_feqExecDetailInfoUnit.execPrice = "1";
            l_feqExecDetailInfoUnit.execQuantity = "1";
            l_feqExecDetailInfoUnit.foreignTradePrice = "1";
            l_feqExecDetailInfoUnit.localCommission = "1";
            l_feqExecDetailInfoUnit.localTradingTax = "1";
            l_feqExecDetailInfoUnit.otherCost1 = "1";
            l_feqExecDetailInfoUnit.otherCost2 = "2";
            l_feqExecDetailInfoUnit.executionTimestamp = WEB3DateUtility.getDate("20080124", "yyyyMMdd");

            l_execInputInfo.execDetailInfoUnit = l_feqExecDetailInfoUnit;
            l_execInputInfo.execExchangeRate = "1";
            l_execInputInfo.dealingType = "1";

            Object[] obj = new Object[]{l_strInstitutionCode, l_subAccount, l_market, l_currency,
                l_product, l_taxType, l_execInputInfo};
            Method method = WEB3AdminFeqOrderAndExecutionServiceImpl.class.getDeclaredMethod(
                "validateFeqAmount",
                new Class[]{String.class, 
                    WEB3GentradeSubAccount.class, 
                    WEB3GentradeMarket.class, 
                    WEB3GentradeCurrency.class, 
                    WEB3FeqProduct.class, 
                    TaxTypeEnum.class, 
                    WEB3FeqOrderAndExecutionUnit.class});
            method.setAccessible(true);
            method.invoke(l_impl, obj);
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getTargetException().getMessage(), l_ex.getTargetException());
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testPersistHostOrder_case0001()
    {
        final String STR_METHOD_NAME = "testPersistHostOrder_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(FeqOrderExecutionParams.TYPE);
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TaxRateTableParams.TYPE);
            TaxRateTableParams l_taxRateTableParams = new TaxRateTableParams();
            l_taxRateTableParams.setInstitutionCode("0D");
            l_taxRateTableParams.setTaxType("10");
            l_taxRateTableParams.setTaxRate(1.1);
            l_taxRateTableParams.setAppliStartDate(WEB3DateUtility.getDate("20080120","yyyyMMdd"));
            l_taxRateTableParams.setAppliEndDate(WEB3DateUtility.getDate("20080128","yyyyMMdd"));
            l_taxRateTableParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080124","yyyyMMdd"));
            l_taxRateTableParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080124","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_taxRateTableParams);

            TestDBUtility.deleteAll(EquityCommCondMstParams.TYPE);
            EquityCommCondMstParams l_equityCommCondMstParams = new EquityCommCondMstParams();
            l_equityCommCondMstParams.setInstitutionCode("0D");
            l_equityCommCondMstParams.setCommProductCode("40");
            l_equityCommCondMstParams.setRegNo("01 ");
            l_equityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20080120","yyyyMMdd"));
            l_equityCommCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20080128","yyyyMMdd"));
            l_equityCommCondMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080124","yyyyMMdd"));
            l_equityCommCondMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080124","yyyyMMdd"));
            l_equityCommCondMstParams.setMinCommission(1.1);
            l_equityCommCondMstParams.setCommissionCourseDiv("1");
            TestDBUtility.insertWithDel(l_equityCommCondMstParams);

            TestDBUtility.deleteAll(EquityCommCondParams.TYPE);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("0D");
            l_equityCommCondParams.setCommProductCode("40");
            l_equityCommCondParams.setRegNo("01 ");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20080124","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("1");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20080120","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20080128","yyyyMMdd"));
            l_equityCommCondParams.setMaxTurnover(50.0);
            l_equityCommCondParams.setMinTurnover(0.0001);
            l_equityCommCondParams.setChargeRatio(1.1);
            l_equityCommCondParams.setAddedPrice(1.1);
            l_equityCommCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080124","yyyyMMdd"));
            l_equityCommCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080124","yyyyMMdd"));
            l_equityCommCondParams.setCommitionPerUnit(1001);
            TestDBUtility.insertWithDel(l_equityCommCondParams);

            TestDBUtility.deleteAll(EquityCommAccountCondMstParams.TYPE);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams =
                TestDBUtility.getEquityCommAccountCondMstRow();
            l_equityCommAccountCondMstParams.setInstitutionCode("0D");
            l_equityCommAccountCondMstParams.setBranchId(33381);
            l_equityCommAccountCondMstParams.setAccountId(1001);
            l_equityCommAccountCondMstParams.setCommProductCode("40");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20080124");
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);

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

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("N2");
            l_marketParams.setMarketId(1001);
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("001");
            l_genCurrencyParams.setCurrencyName("test");
            TestDBUtility.insertWithDel(l_genCurrencyParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_feqProductParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //InstBranchProductParams
            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(33381);
            l_instBranchProductParams.setCommissionProductCode("40");
            TestDBUtility.insertWithDel(l_instBranchProductParams);

            TestDBUtility.deleteAll(FeqFinTransactionParams.TYPE);
            TestDBUtility.commit();
            
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            String l_strUpdaterCode = "1";
            WEB3FeqOrderAndExecutionUnit l_execInputInfo = new WEB3FeqOrderAndExecutionUnit();
            WEB3FeqExecDetailInfoUnit l_feqExecDetailInfoUnit = new WEB3FeqExecDetailInfoUnit();
            l_feqExecDetailInfoUnit.execPrice = "1";
            l_feqExecDetailInfoUnit.execQuantity = "1";
            l_feqExecDetailInfoUnit.foreignTradePrice = "1";
            l_feqExecDetailInfoUnit.localCommission = "1";
            l_feqExecDetailInfoUnit.localTradingTax = "1";
            l_feqExecDetailInfoUnit.otherCost1 = "1";
            l_feqExecDetailInfoUnit.otherCost2 = "2";
            l_feqExecDetailInfoUnit.executionTimestamp = WEB3DateUtility.getDate("20080124", "yyyyMMdd");
            l_feqExecDetailInfoUnit.deliveryPrice = "1";
            l_feqExecDetailInfoUnit.foreignDeliveryPrice = "1";
            l_feqExecDetailInfoUnit.deliveryDate = WEB3DateUtility.getDate("20080124", "yyyyMMdd");
            l_feqExecDetailInfoUnit.commission = "1";
            l_feqExecDetailInfoUnit.consumptionTax = "1";
            l_feqExecDetailInfoUnit.clearUpPrice = "1";
            l_feqExecDetailInfoUnit.foreignCommission = "1";
            l_feqExecDetailInfoUnit.foreignConsumptionTax = "1";
            l_feqExecDetailInfoUnit.foreignClearUpPrice = "1";
            l_feqExecDetailInfoUnit.localCommission = "1";
            l_feqExecDetailInfoUnit.localTradingTax = "1";

            l_execInputInfo.execDetailInfoUnit = l_feqExecDetailInfoUnit;
            l_execInputInfo.execExchangeRate = "1";
            l_execInputInfo.dealingType = "1";
            l_execInputInfo.accountCode = "2512246";
            l_execInputInfo.branchCode = "381";
            l_execInputInfo.localProductCode = "N8080";
            l_execInputInfo.productCode = "N8080";
            l_execInputInfo.currencyCode = "001";
            l_execInputInfo.requestNumber = "1111111";
            l_execInputInfo.passProfit = "1";
            l_execInputInfo.passProfitTax = "1";
            l_execInputInfo.managementCode = "1";
            l_execInputInfo.accountDiv = "1";
            l_execInputInfo.taxType = "1";
            l_execInputInfo.orderBizDate = WEB3DateUtility.getDate("20080124", "yyyyMMdd");
            l_execInputInfo.passProfit = "1";
            l_execInputInfo.passProfitTax = "1";

            Object[] obj = new Object[]{l_institution, l_strUpdaterCode, l_execInputInfo};
            Method method = WEB3AdminFeqOrderAndExecutionServiceImpl.class.getDeclaredMethod(
                "persistHostOrder",
                new Class[]{WEB3GentradeInstitution.class, 
                    String.class, 
                    WEB3FeqOrderAndExecutionUnit.class});
            method.setAccessible(true);
            method.invoke(l_impl, obj);
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex.getCause());
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /*
     * モデルNo.496を対応
     * get海外諸経費マスタ()に指定する引数
     * 
     * (売買区分：　@約定入力情報.売買区分)
     */
    public void testValidateFeqAmount_case0002()
    {
        final String STR_METHOD_NAME = "testValidateFeqAmount_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
        	TestDBUtility.deleteAll(ForeignCostParams.TYPE);
        	ForeignCostParams l_foreignCostParams = new ForeignCostParams();
        	l_foreignCostParams.setMarketCode("N2");
        	l_foreignCostParams.setInstitutionCode("0D");
        	l_foreignCostParams.setAmountFrom(20080124);
        	l_foreignCostParams.setAppliStartDate(WEB3DateUtility.getDate("20080124", "yyyyMMdd"));
        	l_foreignCostParams.setAppliEndDate(WEB3DateUtility.getDate("20080124", "yyyyMMdd"));
        	l_foreignCostParams.setAmountFrom(0);
        	l_foreignCostParams.setAmountTo(200);
        	l_foreignCostParams.setScale(1234);
        	l_foreignCostParams.setCostDiv("01");
        	l_foreignCostParams.setSideDiv("1");
        	l_foreignCostParams.setRoundDiv("0");
        	l_foreignCostParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        	l_foreignCostParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        	TestDBUtility.insertWithDel(l_foreignCostParams);

        	TestDBUtility.deleteAll(TradingTimeParams.TYPE);
        	TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        	l_tradingTimeParams.setMarketCode("N2");
        	TestDBUtility.insertWithDel(l_tradingTimeParams);
        	
            TestDBUtility.deleteAll(TaxRateTableParams.TYPE);
            TaxRateTableParams l_taxRateTableParams = new TaxRateTableParams();
            l_taxRateTableParams.setInstitutionCode("0D");
            l_taxRateTableParams.setTaxType("10");
            l_taxRateTableParams.setTaxRate(1.1);
            l_taxRateTableParams.setAppliStartDate(WEB3DateUtility.getDate("20080120","yyyyMMdd"));
            l_taxRateTableParams.setAppliEndDate(WEB3DateUtility.getDate("20080128","yyyyMMdd"));
            l_taxRateTableParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080124","yyyyMMdd"));
            l_taxRateTableParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080124","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_taxRateTableParams);

            TestDBUtility.deleteAll(EquityCommCondMstParams.TYPE);
            EquityCommCondMstParams l_equityCommCondMstParams = new EquityCommCondMstParams();
            l_equityCommCondMstParams.setInstitutionCode("0D");
            l_equityCommCondMstParams.setCommProductCode("40");
            l_equityCommCondMstParams.setRegNo("01 ");
            l_equityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20080120","yyyyMMdd"));
            l_equityCommCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20080128","yyyyMMdd"));
            l_equityCommCondMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080124","yyyyMMdd"));
            l_equityCommCondMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080124","yyyyMMdd"));
            l_equityCommCondMstParams.setMinCommission(1.1);
            l_equityCommCondMstParams.setCommissionCourseDiv("1");
            TestDBUtility.insertWithDel(l_equityCommCondMstParams);

            TestDBUtility.deleteAll(EquityCommCondParams.TYPE);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("0D");
            l_equityCommCondParams.setCommProductCode("40");
            l_equityCommCondParams.setRegNo("01 ");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20080124","yyyyMMdd"));
            l_equityCommCondParams.setSequenceNo("1");
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20080120","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20080128","yyyyMMdd"));
            l_equityCommCondParams.setMaxTurnover(50.0);
            l_equityCommCondParams.setMinTurnover(0.0001);
            l_equityCommCondParams.setChargeRatio(1.1);
            l_equityCommCondParams.setAddedPrice(1.1);
            l_equityCommCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080124","yyyyMMdd"));
            l_equityCommCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080124","yyyyMMdd"));
            l_equityCommCondParams.setCommitionPerUnit(1001);
            TestDBUtility.insertWithDel(l_equityCommCondParams);

            TestDBUtility.deleteAll(EquityCommAccountCondMstParams.TYPE);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams =
                TestDBUtility.getEquityCommAccountCondMstRow();
            l_equityCommAccountCondMstParams.setInstitutionCode("0D");
            l_equityCommAccountCondMstParams.setBranchId(33381);
            l_equityCommAccountCondMstParams.setAccountId(1001);
            l_equityCommAccountCondMstParams.setCommProductCode("40");
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20080124");
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);

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

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("N2");
            l_marketParams.setMarketId(1001);
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("001");
            l_genCurrencyParams.setCurrencyName("test");
            TestDBUtility.insertWithDel(l_genCurrencyParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_feqProductParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //InstBranchProduct
            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(33381);
            l_instBranchProductParams.setCommissionProductCode("40");
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            

            String l_strInstitutionCode = "0D";
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(1001);
            WEB3GentradeCurrency l_currency = new WEB3GentradeCurrency(l_genCurrencyParams);
            WEB3FeqProduct l_product = new WEB3FeqProduct(l_productParams);
            TaxTypeEnum l_taxType = TaxTypeEnum.NORMAL;
            WEB3FeqOrderAndExecutionUnit l_execInputInfo = new WEB3FeqOrderAndExecutionUnit();
            WEB3FeqExecDetailInfoUnit l_feqExecDetailInfoUnit = new WEB3FeqExecDetailInfoUnit();
            l_feqExecDetailInfoUnit.execPrice = "1";
            l_feqExecDetailInfoUnit.execQuantity = "1";
            l_feqExecDetailInfoUnit.foreignTradePrice = "1";
            l_feqExecDetailInfoUnit.localCommission = "1";
            l_feqExecDetailInfoUnit.localTradingTax = "1";
            l_feqExecDetailInfoUnit.otherCost1 = "1";
            l_feqExecDetailInfoUnit.otherCost2 = "2";
            l_feqExecDetailInfoUnit.executionTimestamp = WEB3DateUtility.getDate("20080124", "yyyyMMdd");

            l_execInputInfo.execDetailInfoUnit = l_feqExecDetailInfoUnit;
            l_execInputInfo.execExchangeRate = "1";
            l_execInputInfo.dealingType = "1";
            l_execInputInfo.marketCode="N2";
            

            Object[] obj = new Object[]{l_strInstitutionCode, l_subAccount, l_market, l_currency,
                l_product, l_taxType, l_execInputInfo};
            Method method = WEB3AdminFeqOrderAndExecutionServiceImpl.class.getDeclaredMethod(
                "validateFeqAmount",
                new Class[]{String.class, 
                    WEB3GentradeSubAccount.class, 
                    WEB3GentradeMarket.class, 
                    WEB3GentradeCurrency.class, 
                    WEB3FeqProduct.class, 
                    TaxTypeEnum.class, 
                    WEB3FeqOrderAndExecutionUnit.class});
            method.setAccessible(true);
            method.invoke(l_impl, obj);
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getTargetException().getMessage(), l_ex.getTargetException());
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
}
@

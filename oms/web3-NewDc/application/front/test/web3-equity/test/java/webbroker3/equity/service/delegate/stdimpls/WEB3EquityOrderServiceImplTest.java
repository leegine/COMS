head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.56.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityOrderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.service.delegate.stdimpls;

import java.util.Calendar;

import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewCashBasedOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrgDepositDivDef;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.message.WEB3EquityBuyCompleteRequest;
import webbroker3.equity.message.WEB3EquityBuyCompleteResponse;
import webbroker3.equity.message.WEB3EquityBuyConfirmRequest;
import webbroker3.equity.message.WEB3EquityBuyConfirmResponse;
import webbroker3.equity.message.WEB3EquitySellCompleteRequest;
import webbroker3.equity.message.WEB3EquitySellCompleteResponse;
import webbroker3.equity.message.WEB3EquitySellConfirmRequest;
import webbroker3.equity.message.WEB3EquitySellConfirmResponse;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.equity.service.delegate.WEB3EquityOrderRequestAdapter;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondParams;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.InsiderParams;
import webbroker3.gentrade.data.InsiderRow;
import webbroker3.gentrade.data.TaxRateTableParams;
import webbroker3.gentrade.data.TaxRateTableRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author âΩï∂ïq(íÜêu)
 * @@version 1.0
 */
public class WEB3EquityOrderServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderServiceImplTest.class);
    WEB3EquityOrderServiceImplForTest l_impl = new WEB3EquityOrderServiceImplForTest();
    
    public WEB3EquityOrderServiceImplTest(String arg0)
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

    public void testValidateOrder_Case0001()
    {
        final String STR_METHOD_NAME = "testValidateOrder_Case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3EquityBuyConfirmRequest l_request =
                new WEB3EquityBuyConfirmRequest();
            WEB3MarginCloseMarginContractUnit l_unit =
                new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "123456";
            l_unit.orderQuantity = "100";
            l_request.orderPriceDiv = "0";
            l_unit.settlePriority = "123";
            l_request.orderCondType = "0";
            l_request.priceCondType = "0";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "1";
            l_request.execCondType = "1";
            l_request.stopOrderCondPrice = "15";
            l_request.stopOrderCondOperator = "1";
            l_request.orderQuantity = "10";
            l_request.productCode = "12354";
            l_request.marketCode = "1";
            l_request.taxType = "0";
            l_request.tradingType = "1";
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = "10";
            l_request.orderCondType = "1";
            
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            l_BranchParams.setInstitutionId(33L);
            l_BranchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_BranchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setOrgDepositDiv(WEB3OrgDepositDivDef.AGREE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(123456);
            l_eqtypeContractParams.setCloseDate(Calendar.getInstance().getTime());
            l_eqtypeContractParams.setProductId(12342L);
            l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeContractParams.setRepaymentType("0");
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setProductId(12342);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);


            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_producttParams = TestDBUtility.getProductRow();
            l_producttParams.setProductId(12342);
            l_producttParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_producttParams);
            
            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(12342);
            l_eqtypeProductParams.setProductCode("12354");
            l_eqtypeProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setProductId(12342L);
            l_tradedProductParams.setMarketId(33381L);
            l_tradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            l_eqtypeTradedProductParams.setProductId(12342L);
            l_eqtypeTradedProductParams.setMarketId(33381L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams = TestDBUtility.getBranchMarketRepayDealCondRow();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            l_branchMarketRepayDealtCondParams.setMarketCode("SP");
            l_branchMarketRepayDealtCondParams.setRepaymentNum(0);
            l_branchMarketRepayDealtCondParams.setRepaymentDiv("0");
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeparams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeparams.setMarketCode("1");
            l_tradingTimeparams.setBranchCode("123");
            l_tradingTimeparams.setBizDateType("1");
            l_tradingTimeparams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeparams);

            TestDBUtility.deleteAll(TaxRateTableRow.TYPE);
            TaxRateTableParams l_taxRateTableParams = new TaxRateTableParams();
            l_taxRateTableParams.setTaxType("10");
            l_taxRateTableParams.setAppliEndDate(WEB3DateUtility.getDate("99990101", "yyyyMMdd"));
            l_taxRateTableParams.setAppliStartDate(WEB3DateUtility.getDate("20060203", "yyyyMMdd"));
            l_taxRateTableParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_taxRateTableParams.setInstitutionCode("0D");
            l_taxRateTableParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_taxRateTableParams.setTaxRate(0.10D);
            TestDBUtility.insertWithDel(l_taxRateTableParams);
            
            TestDBUtility.deleteAll(InsiderRow.TYPE);
            InsiderParams l_insiderParams = new InsiderParams();
            l_insiderParams.setAccountId(333812512246L);
            l_insiderParams.setBranchId(33381L);
            l_insiderParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_insiderParams.setInstitutionCode("0D");
            l_insiderParams.setProductId(12342);
            l_insiderParams.setRelationDiv("1");
            l_insiderParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_insiderParams.setRegistDiv("1");
            l_insiderParams.setLastUpdater("scofiled");
            TestDBUtility.insertWithDel(l_insiderParams);
            
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setOrderBizDate(GtlUtils.getSystemTimestamp());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "12");
            
            LoginInfo l_info = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_info);
            
            WEB3EquityEstimatedDeliveryPrice l_price1 = new WEB3EquityEstimatedDeliveryPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                            WEB3GentradeCommission.class, double.class,
                            double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                            String.class,
                            String.class,
                            String.class,
                            boolean.class,
                            SubAccount.class,
                            WEB3EquityTradedProduct.class,
                            double.class,
                            boolean.class,
                            double.class,
                            double.class,
                            boolean.class
                            },
                            l_price1);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20070619", "yyyyMMdd"));
            WEB3EquityBuyConfirmResponse l_response = (WEB3EquityBuyConfirmResponse)l_impl.validateOrder(l_request);
            
//            log.debug("***************** expirationDate = " + l_response.expirationDate);
//            assertEquals("", WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"));
            assertEquals("1", l_response.attentionObjectionType);
            assertEquals("999", l_response.accountBalanceInsufficiency);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateOrder_Case0002()
    {
        final String STR_METHOD_NAME = "testValidateOrder_Case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3EquitySellConfirmRequest l_request =
                new WEB3EquitySellConfirmRequest();
            WEB3MarginCloseMarginContractUnit l_unit =
                new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "123456";
            l_unit.orderQuantity = "100";
            l_request.orderPriceDiv = "0";
            l_unit.settlePriority = "123";
            l_request.orderCondType = "0";
            l_request.priceCondType = "0";
            l_request.expirationDateType = "2";
            l_request.orderCondType = "1";
            l_request.execCondType = "1";
            l_request.stopOrderCondPrice = "15";
            l_request.stopOrderCondOperator = "1";
            l_request.id = "123456789";
            l_request.orderQuantity = "10";
            l_request.expirationDate = WEB3DateUtility.getDate("20060507", "yyyyMMdd");
            l_request.marketCode = "1";
            
                        TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setOrgDepositDiv(WEB3OrgDepositDivDef.AGREE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(AssetRow.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductId(12342);
            l_assetParams.setAssetId(123456789L);
            l_assetParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_assetParams);
            
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(123456);
            l_eqtypeContractParams.setCloseDate(Calendar.getInstance().getTime());
            l_eqtypeContractParams.setProductId(12342L);
            l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeContractParams.setRepaymentType("0");
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setProductId(12342);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_producttParams = TestDBUtility.getProductRow();
            l_producttParams.setProductId(12342);
            l_producttParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_producttParams);
            
            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(12342);
            l_eqtypeProductParams.setProductCode("12354");
            l_eqtypeProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setProductId(12342L);
            l_tradedProductParams.setMarketId(33381L);
            l_tradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            l_eqtypeTradedProductParams.setProductId(12342L);
            l_eqtypeTradedProductParams.setMarketId(33381L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams = TestDBUtility.getBranchMarketRepayDealCondRow();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            l_branchMarketRepayDealtCondParams.setMarketCode("SP");
            l_branchMarketRepayDealtCondParams.setRepaymentNum(0);
            l_branchMarketRepayDealtCondParams.setRepaymentDiv("0");
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeparams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeparams.setMarketCode("1");
            l_tradingTimeparams.setBranchCode("123");
            l_tradingTimeparams.setBizDateType("1");
            l_tradingTimeparams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeparams);

            TestDBUtility.deleteAll(TaxRateTableRow.TYPE);
            TaxRateTableParams l_taxRateTableParams = new TaxRateTableParams();
            l_taxRateTableParams.setTaxType("10");
            l_taxRateTableParams.setAppliEndDate(WEB3DateUtility.getDate("99990101", "yyyyMMdd"));
            l_taxRateTableParams.setAppliStartDate(WEB3DateUtility.getDate("20060203", "yyyyMMdd"));
            l_taxRateTableParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_taxRateTableParams.setInstitutionCode("0D");
            l_taxRateTableParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_taxRateTableParams.setTaxRate(0.10D);
            TestDBUtility.insertWithDel(l_taxRateTableParams);
            
            TestDBUtility.deleteAll(InsiderRow.TYPE);
            InsiderParams l_insiderParams = new InsiderParams();
            l_insiderParams.setAccountId(333812512246L);
            l_insiderParams.setBranchId(33381L);
            l_insiderParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_insiderParams.setInstitutionCode("0D");
            l_insiderParams.setProductId(12342);
            l_insiderParams.setRelationDiv("1");
            l_insiderParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_insiderParams.setRegistDiv("1");
            l_insiderParams.setLastUpdater("scofiled");
            TestDBUtility.insertWithDel(l_insiderParams);
            
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setOrderBizDate(GtlUtils.getSystemTimestamp());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "12");
            
            LoginInfo l_info = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_info);
            
            WEB3EquityEstimatedDeliveryPrice l_price1 = new WEB3EquityEstimatedDeliveryPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                            WEB3GentradeCommission.class, double.class,
                            double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                            String.class,
                            String.class,
                            String.class,
                            boolean.class,
                            SubAccount.class,
                            WEB3EquityTradedProduct.class,
                            double.class,
                            boolean.class,
                            double.class,
                            double.class,
                            boolean.class
                            },
                            l_price1);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20070619", "yyyyMMdd"));
            WEB3EquitySellConfirmResponse l_response = (WEB3EquitySellConfirmResponse)l_impl.validateOrder(l_request);
            
//            log.debug("***************** expirationDate = " + l_response.expirationDate);
//            assertEquals("20060507", WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"));
            assertEquals("1", l_response.attentionObjectionType);
            assertEquals("999", l_response.accountBalanceInsufficiency);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitOrder_Case0001()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_Case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3EquityBuyCompleteRequest l_request =
                new WEB3EquityBuyCompleteRequest();
            WEB3MarginCloseMarginContractUnit l_unit =
                new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "123456";
            l_unit.orderQuantity = "100";
            l_request.orderPriceDiv = "0";
            l_unit.settlePriority = "123";
            l_request.orderCondType = "0";
            l_request.priceCondType = "0";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "1";
            l_request.execCondType = "1";
            l_request.stopOrderCondPrice = "15";
            l_request.stopOrderCondOperator = "1";
            l_request.orderQuantity = "10";
            l_request.productCode = "12354";
            l_request.marketCode = "1";
            l_request.taxType = "0";
            l_request.tradingType = "1";
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = "10";
            l_request.orderCondType = "1";
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            l_BranchParams.setInstitutionId(33L);
            l_BranchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_BranchParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setOrgDepositDiv(WEB3OrgDepositDivDef.AGREE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(123456);
            l_eqtypeContractParams.setCloseDate(Calendar.getInstance().getTime());
            l_eqtypeContractParams.setProductId(12342L);
            l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeContractParams.setRepaymentType("0");
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setProductId(12342);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);


            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_producttParams = TestDBUtility.getProductRow();
            l_producttParams.setProductId(12342);
            l_producttParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_producttParams);
            
            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(12342);
            l_eqtypeProductParams.setProductCode("12354");
            l_eqtypeProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setProductId(12342L);
            l_tradedProductParams.setMarketId(33381L);
            l_tradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            l_eqtypeTradedProductParams.setProductId(12342L);
            l_eqtypeTradedProductParams.setMarketId(33381L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams = TestDBUtility.getBranchMarketRepayDealCondRow();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            l_branchMarketRepayDealtCondParams.setMarketCode("SP");
            l_branchMarketRepayDealtCondParams.setRepaymentNum(0);
            l_branchMarketRepayDealtCondParams.setRepaymentDiv("0");
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeparams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeparams.setMarketCode("1");
            l_tradingTimeparams.setBranchCode("123");
            l_tradingTimeparams.setBizDateType("1");
            l_tradingTimeparams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeparams);

            TestDBUtility.deleteAll(TaxRateTableRow.TYPE);
            TaxRateTableParams l_taxRateTableParams = new TaxRateTableParams();
            l_taxRateTableParams.setTaxType("10");
            l_taxRateTableParams.setAppliEndDate(WEB3DateUtility.getDate("99990101", "yyyyMMdd"));
            l_taxRateTableParams.setAppliStartDate(WEB3DateUtility.getDate("20060203", "yyyyMMdd"));
            l_taxRateTableParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_taxRateTableParams.setInstitutionCode("0D");
            l_taxRateTableParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_taxRateTableParams.setTaxRate(0.10D);
            TestDBUtility.insertWithDel(l_taxRateTableParams);
            
            TestDBUtility.deleteAll(InsiderRow.TYPE);
            InsiderParams l_insiderParams = new InsiderParams();
            l_insiderParams.setAccountId(333812512246L);
            l_insiderParams.setBranchId(33381L);
            l_insiderParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_insiderParams.setInstitutionCode("0D");
            l_insiderParams.setProductId(12342);
            l_insiderParams.setRelationDiv("1");
            l_insiderParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_insiderParams.setRegistDiv("1");
            l_insiderParams.setLastUpdater("scofiled");
            TestDBUtility.insertWithDel(l_insiderParams);
            
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setOrderBizDate(GtlUtils.getSystemTimestamp());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "12");
            
            LoginInfo l_info = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_info);
            
            WEB3EquityEstimatedDeliveryPrice l_price1 = new WEB3EquityEstimatedDeliveryPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                            WEB3GentradeCommission.class, double.class,
                            double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                            String.class,
                            String.class,
                            String.class,
                            boolean.class,
                            SubAccount.class,
                            WEB3EquityTradedProduct.class,
                            double.class,
                            boolean.class,
                            double.class,
                            double.class,
                            boolean.class
                            },
                            l_price1);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20070619", "yyyyMMdd"));
            WEB3EquityBuyCompleteResponse l_response = (WEB3EquityBuyCompleteResponse)l_impl.submitOrder(l_request);
            
//            log.debug("***************** expirationDate = " + l_response.expirationDate);
//            assertEquals("", WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitOrder_Case0002()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_Case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3EquitySellCompleteRequest l_request =
                new WEB3EquitySellCompleteRequest();
            WEB3MarginCloseMarginContractUnit l_unit =
                new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "123456";
            l_unit.orderQuantity = "100";
            l_request.orderPriceDiv = "0";
            l_unit.settlePriority = "123";
            l_request.orderCondType = "0";
            l_request.priceCondType = "0";
            l_request.expirationDateType = "2";
            l_request.orderCondType = "1";
            l_request.execCondType = "1";
            l_request.stopOrderCondPrice = "15";
            l_request.stopOrderCondOperator = "1";
            l_request.id = "123456789";
            l_request.orderQuantity = "10";
            l_request.expirationDate = WEB3DateUtility.getDate("20060507", "yyyyMMdd");
            l_request.marketCode = "1";
            
                        TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setOrgDepositDiv(WEB3OrgDepositDivDef.AGREE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(AssetRow.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductId(12342);
            l_assetParams.setAssetId(123456789L);
            l_assetParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_assetParams);
            
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(123456);
            l_eqtypeContractParams.setCloseDate(Calendar.getInstance().getTime());
            l_eqtypeContractParams.setProductId(12342L);
            l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeContractParams.setRepaymentType("0");
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setProductId(12342);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);


            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_producttParams = TestDBUtility.getProductRow();
            l_producttParams.setProductId(12342);
            l_producttParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_producttParams);
            
            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(12342);
            l_eqtypeProductParams.setProductCode("12354");
            l_eqtypeProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setProductId(12342L);
            l_tradedProductParams.setMarketId(33381L);
            l_tradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            l_eqtypeTradedProductParams.setProductId(12342L);
            l_eqtypeTradedProductParams.setMarketId(33381L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams = TestDBUtility.getBranchMarketRepayDealCondRow();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            l_branchMarketRepayDealtCondParams.setMarketCode("SP");
            l_branchMarketRepayDealtCondParams.setRepaymentNum(0);
            l_branchMarketRepayDealtCondParams.setRepaymentDiv("0");
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeparams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeparams.setMarketCode("1");
            l_tradingTimeparams.setBranchCode("123");
            l_tradingTimeparams.setBizDateType("1");
            l_tradingTimeparams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeparams);

            TestDBUtility.deleteAll(TaxRateTableRow.TYPE);
            TaxRateTableParams l_taxRateTableParams = new TaxRateTableParams();
            l_taxRateTableParams.setTaxType("10");
            l_taxRateTableParams.setAppliEndDate(WEB3DateUtility.getDate("99990101", "yyyyMMdd"));
            l_taxRateTableParams.setAppliStartDate(WEB3DateUtility.getDate("20060203", "yyyyMMdd"));
            l_taxRateTableParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_taxRateTableParams.setInstitutionCode("0D");
            l_taxRateTableParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_taxRateTableParams.setTaxRate(0.10D);
            TestDBUtility.insertWithDel(l_taxRateTableParams);
            
            TestDBUtility.deleteAll(InsiderRow.TYPE);
            InsiderParams l_insiderParams = new InsiderParams();
            l_insiderParams.setAccountId(333812512246L);
            l_insiderParams.setBranchId(33381L);
            l_insiderParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_insiderParams.setInstitutionCode("0D");
            l_insiderParams.setProductId(12342);
            l_insiderParams.setRelationDiv("1");
            l_insiderParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_insiderParams.setRegistDiv("1");
            l_insiderParams.setLastUpdater("scofiled");
            TestDBUtility.insertWithDel(l_insiderParams);
            
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setOrderBizDate(GtlUtils.getSystemTimestamp());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "12");
            
            LoginInfo l_info = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_info);
            
            WEB3EquityEstimatedDeliveryPrice l_price1 = new WEB3EquityEstimatedDeliveryPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                            WEB3GentradeCommission.class, double.class,
                            double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                            String.class,
                            String.class,
                            String.class,
                            boolean.class,
                            SubAccount.class,
                            WEB3EquityTradedProduct.class,
                            double.class,
                            boolean.class,
                            double.class,
                            double.class,
                            boolean.class
                            },
                            l_price1);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20070619", "yyyyMMdd"));
            WEB3EquitySellCompleteResponse l_response = (WEB3EquitySellCompleteResponse)l_impl.submitOrder(l_request);
            
//            log.debug("***************** expirationDate = " + l_response.expirationDate);
//            assertEquals("20060507", WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    //validateã@@ç\óaëıìØà”(ï‚èïå˚ç¿)ùeàŸèÌ
    public void testValidateOrder_Case0003()
    {
        final String STR_METHOD_NAME = "testValidateOrder_Case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3EquityBuyConfirmRequest l_request =
                new WEB3EquityBuyConfirmRequest();
            WEB3MarginCloseMarginContractUnit l_unit =
                new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "123456";
            l_unit.orderQuantity = "100";
            l_request.orderPriceDiv = "0";
            l_unit.settlePriority = "123";
            l_request.orderCondType = "0";
            l_request.priceCondType = "0";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "1";
            l_request.execCondType = "1";
            l_request.stopOrderCondPrice = "15";
            l_request.stopOrderCondOperator = "1";
            l_request.orderQuantity = "10";
            l_request.productCode = "12354";
            l_request.marketCode = "1";
            l_request.taxType = "0";
            l_request.tradingType = "1";
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = "10";
            l_request.orderCondType = "1";
            
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            l_BranchParams.setInstitutionId(33L);
            l_BranchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_BranchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setOrgDepositDiv("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(123456);
            l_eqtypeContractParams.setCloseDate(Calendar.getInstance().getTime());
            l_eqtypeContractParams.setProductId(12342L);
            l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeContractParams.setRepaymentType("0");
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setProductId(12342);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);


            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_producttParams = TestDBUtility.getProductRow();
            l_producttParams.setProductId(12342);
            l_producttParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_producttParams);
            
            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(12342);
            l_eqtypeProductParams.setProductCode("12354");
            l_eqtypeProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setProductId(12342L);
            l_tradedProductParams.setMarketId(33381L);
            l_tradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            l_eqtypeTradedProductParams.setProductId(12342L);
            l_eqtypeTradedProductParams.setMarketId(33381L);
            l_eqtypeTradedProductParams.setNewListType("9");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams = TestDBUtility.getBranchMarketRepayDealCondRow();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            l_branchMarketRepayDealtCondParams.setMarketCode("SP");
            l_branchMarketRepayDealtCondParams.setRepaymentNum(0);
            l_branchMarketRepayDealtCondParams.setRepaymentDiv("0");
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeparams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeparams.setMarketCode("1");
            l_tradingTimeparams.setBranchCode("123");
            l_tradingTimeparams.setBizDateType("1");
            l_tradingTimeparams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeparams);

            TestDBUtility.deleteAll(TaxRateTableRow.TYPE);
            TaxRateTableParams l_taxRateTableParams = new TaxRateTableParams();
            l_taxRateTableParams.setTaxType("10");
            l_taxRateTableParams.setAppliEndDate(WEB3DateUtility.getDate("99990101", "yyyyMMdd"));
            l_taxRateTableParams.setAppliStartDate(WEB3DateUtility.getDate("20060203", "yyyyMMdd"));
            l_taxRateTableParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_taxRateTableParams.setInstitutionCode("0D");
            l_taxRateTableParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_taxRateTableParams.setTaxRate(0.10D);
            TestDBUtility.insertWithDel(l_taxRateTableParams);
            
            TestDBUtility.deleteAll(InsiderRow.TYPE);
            InsiderParams l_insiderParams = new InsiderParams();
            l_insiderParams.setAccountId(333812512246L);
            l_insiderParams.setBranchId(33381L);
            l_insiderParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_insiderParams.setInstitutionCode("0D");
            l_insiderParams.setProductId(12342);
            l_insiderParams.setRelationDiv("1");
            l_insiderParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_insiderParams.setRegistDiv("1");
            l_insiderParams.setLastUpdater("scofiled");
            TestDBUtility.insertWithDel(l_insiderParams);
            
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setOrderBizDate(GtlUtils.getSystemTimestamp());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "12");
            
            LoginInfo l_info = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_info);
            
            WEB3EquityEstimatedDeliveryPrice l_price1 = new WEB3EquityEstimatedDeliveryPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                            WEB3GentradeCommission.class, double.class,
                            double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                            String.class,
                            String.class,
                            String.class,
                            boolean.class,
                            SubAccount.class,
                            WEB3EquityTradedProduct.class,
                            double.class,
                            boolean.class,
                            double.class,
                            double.class,
                            boolean.class
                            },
                            l_price1);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20070619", "yyyyMMdd"));
            l_impl.validateOrder(l_request);
            fail();

        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02964);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    //validateã@@ç\óaëıìØà”(ï‚èïå˚ç¿)ê≥èÌí âﬂ
    public void testValidateOrder_Case0004()
    {
        final String STR_METHOD_NAME = "testValidateOrder_Case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3EquityBuyConfirmRequest l_request =
                new WEB3EquityBuyConfirmRequest();
            WEB3MarginCloseMarginContractUnit l_unit =
                new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "123456";
            l_unit.orderQuantity = "100";
            l_request.orderPriceDiv = "0";
            l_unit.settlePriority = "123";
            l_request.orderCondType = "0";
            l_request.priceCondType = "0";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "1";
            l_request.execCondType = "1";
            l_request.stopOrderCondPrice = "15";
            l_request.stopOrderCondOperator = "1";
            l_request.orderQuantity = "10";
            l_request.productCode = "12354";
            l_request.marketCode = "1";
            l_request.taxType = "0";
            l_request.tradingType = "1";
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = "10";
            l_request.orderCondType = "1";
            
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            l_BranchParams.setInstitutionId(33L);
            l_BranchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_BranchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setOrgDepositDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(123456);
            l_eqtypeContractParams.setCloseDate(Calendar.getInstance().getTime());
            l_eqtypeContractParams.setProductId(12342L);
            l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeContractParams.setRepaymentType("0");
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setProductId(12342);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);


            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_producttParams = TestDBUtility.getProductRow();
            l_producttParams.setProductId(12342);
            l_producttParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_producttParams);
            
            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(12342);
            l_eqtypeProductParams.setProductCode("12354");
            l_eqtypeProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setProductId(12342L);
            l_tradedProductParams.setMarketId(33381L);
            l_tradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            l_eqtypeTradedProductParams.setProductId(12342L);
            l_eqtypeTradedProductParams.setMarketId(33381L);
            l_eqtypeTradedProductParams.setNewListType("9");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams = TestDBUtility.getBranchMarketRepayDealCondRow();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            l_branchMarketRepayDealtCondParams.setMarketCode("SP");
            l_branchMarketRepayDealtCondParams.setRepaymentNum(0);
            l_branchMarketRepayDealtCondParams.setRepaymentDiv("0");
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeparams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeparams.setMarketCode("1");
            l_tradingTimeparams.setBranchCode("123");
            l_tradingTimeparams.setBizDateType("1");
            l_tradingTimeparams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeparams);

            TestDBUtility.deleteAll(TaxRateTableRow.TYPE);
            TaxRateTableParams l_taxRateTableParams = new TaxRateTableParams();
            l_taxRateTableParams.setTaxType("10");
            l_taxRateTableParams.setAppliEndDate(WEB3DateUtility.getDate("99990101", "yyyyMMdd"));
            l_taxRateTableParams.setAppliStartDate(WEB3DateUtility.getDate("20060203", "yyyyMMdd"));
            l_taxRateTableParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_taxRateTableParams.setInstitutionCode("0D");
            l_taxRateTableParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_taxRateTableParams.setTaxRate(0.10D);
            TestDBUtility.insertWithDel(l_taxRateTableParams);
            
            TestDBUtility.deleteAll(InsiderRow.TYPE);
            InsiderParams l_insiderParams = new InsiderParams();
            l_insiderParams.setAccountId(333812512246L);
            l_insiderParams.setBranchId(33381L);
            l_insiderParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_insiderParams.setInstitutionCode("0D");
            l_insiderParams.setProductId(12342);
            l_insiderParams.setRelationDiv("1");
            l_insiderParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_insiderParams.setRegistDiv("1");
            l_insiderParams.setLastUpdater("scofiled");
            TestDBUtility.insertWithDel(l_insiderParams);
            
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setOrderBizDate(GtlUtils.getSystemTimestamp());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "12");
            
            LoginInfo l_info = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_info);
            
            WEB3EquityEstimatedDeliveryPrice l_price1 = new WEB3EquityEstimatedDeliveryPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                            WEB3GentradeCommission.class, double.class,
                            double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                            String.class,
                            String.class,
                            String.class,
                            boolean.class,
                            SubAccount.class,
                            WEB3EquityTradedProduct.class,
                            double.class,
                            boolean.class,
                            double.class,
                            double.class,
                            boolean.class
                            },
                            l_price1);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20070619", "yyyyMMdd"));
            l_impl.validateOrder(l_request);

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }

    //validateã@@ç\óaëıìØà”(ï‚èïå˚ç¿)ùeàŸèÌ
    public void testSubmitOrder_Case0003()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_Case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3EquityBuyCompleteRequest l_request =
                new WEB3EquityBuyCompleteRequest();
            WEB3MarginCloseMarginContractUnit l_unit =
                new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "123456";
            l_unit.orderQuantity = "100";
            l_request.orderPriceDiv = "0";
            l_unit.settlePriority = "123";
            l_request.orderCondType = "0";
            l_request.priceCondType = "0";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "1";
            l_request.execCondType = "1";
            l_request.stopOrderCondPrice = "15";
            l_request.stopOrderCondOperator = "1";
            l_request.orderQuantity = "10";
            l_request.productCode = "12354";
            l_request.marketCode = "1";
            l_request.taxType = "0";
            l_request.tradingType = "1";
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = "10";
            l_request.orderCondType = "1";
            
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setOrgDepositDiv("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(123456);
            l_eqtypeContractParams.setCloseDate(Calendar.getInstance().getTime());
            l_eqtypeContractParams.setProductId(12342L);
            l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeContractParams.setRepaymentType("0");
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setProductId(12342);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);


            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_producttParams = TestDBUtility.getProductRow();
            l_producttParams.setProductId(12342);
            l_producttParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_producttParams);
            
            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(12342);
            l_eqtypeProductParams.setProductCode("12354");
            l_eqtypeProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setProductId(12342L);
            l_tradedProductParams.setMarketId(33381L);
            l_tradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            l_eqtypeTradedProductParams.setProductId(12342L);
            l_eqtypeTradedProductParams.setMarketId(33381L);
            l_eqtypeTradedProductParams.setNewListType("9");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams = TestDBUtility.getBranchMarketRepayDealCondRow();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            l_branchMarketRepayDealtCondParams.setMarketCode("SP");
            l_branchMarketRepayDealtCondParams.setRepaymentNum(0);
            l_branchMarketRepayDealtCondParams.setRepaymentDiv("0");
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeparams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeparams.setMarketCode("1");
            l_tradingTimeparams.setBranchCode("123");
            l_tradingTimeparams.setBizDateType("1");
            l_tradingTimeparams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeparams);

            TestDBUtility.deleteAll(TaxRateTableRow.TYPE);
            TaxRateTableParams l_taxRateTableParams = new TaxRateTableParams();
            l_taxRateTableParams.setTaxType("10");
            l_taxRateTableParams.setAppliEndDate(WEB3DateUtility.getDate("99990101", "yyyyMMdd"));
            l_taxRateTableParams.setAppliStartDate(WEB3DateUtility.getDate("20060203", "yyyyMMdd"));
            l_taxRateTableParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_taxRateTableParams.setInstitutionCode("0D");
            l_taxRateTableParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_taxRateTableParams.setTaxRate(0.10D);
            TestDBUtility.insertWithDel(l_taxRateTableParams);
            
            TestDBUtility.deleteAll(InsiderRow.TYPE);
            InsiderParams l_insiderParams = new InsiderParams();
            l_insiderParams.setAccountId(333812512246L);
            l_insiderParams.setBranchId(33381L);
            l_insiderParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_insiderParams.setInstitutionCode("0D");
            l_insiderParams.setProductId(12342);
            l_insiderParams.setRelationDiv("1");
            l_insiderParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_insiderParams.setRegistDiv("1");
            l_insiderParams.setLastUpdater("scofiled");
            TestDBUtility.insertWithDel(l_insiderParams);
            
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setOrderBizDate(GtlUtils.getSystemTimestamp());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "12");
            
            LoginInfo l_info = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_info);
            
            WEB3EquityEstimatedDeliveryPrice l_price1 = new WEB3EquityEstimatedDeliveryPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                            WEB3GentradeCommission.class, double.class,
                            double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                            String.class,
                            String.class,
                            String.class,
                            boolean.class,
                            SubAccount.class,
                            WEB3EquityTradedProduct.class,
                            double.class,
                            boolean.class,
                            double.class,
                            double.class,
                            boolean.class
                            },
                            l_price1);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20070619", "yyyyMMdd"));
            l_impl.submitOrder(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02964);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    //validateã@@ç\óaëıìØà”(ï‚èïå˚ç¿)ê≥èÌí âﬂ
    public void testSubmitOrder_Case0004()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_Case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3EquityBuyCompleteRequest l_request =
                new WEB3EquityBuyCompleteRequest();
            WEB3MarginCloseMarginContractUnit l_unit =
                new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "123456";
            l_unit.orderQuantity = "100";
            l_request.orderPriceDiv = "0";
            l_unit.settlePriority = "123";
            l_request.orderCondType = "0";
            l_request.priceCondType = "0";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "1";
            l_request.execCondType = "1";
            l_request.stopOrderCondPrice = "15";
            l_request.stopOrderCondOperator = "1";
            l_request.orderQuantity = "10";
            l_request.productCode = "12354";
            l_request.marketCode = "1";
            l_request.taxType = "0";
            l_request.tradingType = "1";
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = "10";
            l_request.orderCondType = "1";
            
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setOrgDepositDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(123456);
            l_eqtypeContractParams.setCloseDate(Calendar.getInstance().getTime());
            l_eqtypeContractParams.setProductId(12342L);
            l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeContractParams.setRepaymentType("0");
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setProductId(12342);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);


            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_producttParams = TestDBUtility.getProductRow();
            l_producttParams.setProductId(12342);
            l_producttParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_producttParams);
            
            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(12342);
            l_eqtypeProductParams.setProductCode("12354");
            l_eqtypeProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setProductId(12342L);
            l_tradedProductParams.setMarketId(33381L);
            l_tradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            l_eqtypeTradedProductParams.setProductId(12342L);
            l_eqtypeTradedProductParams.setMarketId(33381L);
            l_eqtypeTradedProductParams.setNewListType("9");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams = TestDBUtility.getBranchMarketRepayDealCondRow();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            l_branchMarketRepayDealtCondParams.setMarketCode("SP");
            l_branchMarketRepayDealtCondParams.setRepaymentNum(0);
            l_branchMarketRepayDealtCondParams.setRepaymentDiv("0");
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeparams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeparams.setMarketCode("1");
            l_tradingTimeparams.setBranchCode("123");
            l_tradingTimeparams.setBizDateType("1");
            l_tradingTimeparams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeparams);

            TestDBUtility.deleteAll(TaxRateTableRow.TYPE);
            TaxRateTableParams l_taxRateTableParams = new TaxRateTableParams();
            l_taxRateTableParams.setTaxType("10");
            l_taxRateTableParams.setAppliEndDate(WEB3DateUtility.getDate("99990101", "yyyyMMdd"));
            l_taxRateTableParams.setAppliStartDate(WEB3DateUtility.getDate("20060203", "yyyyMMdd"));
            l_taxRateTableParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_taxRateTableParams.setInstitutionCode("0D");
            l_taxRateTableParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_taxRateTableParams.setTaxRate(0.10D);
            TestDBUtility.insertWithDel(l_taxRateTableParams);
            
            TestDBUtility.deleteAll(InsiderRow.TYPE);
            InsiderParams l_insiderParams = new InsiderParams();
            l_insiderParams.setAccountId(333812512246L);
            l_insiderParams.setBranchId(33381L);
            l_insiderParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_insiderParams.setInstitutionCode("0D");
            l_insiderParams.setProductId(12342);
            l_insiderParams.setRelationDiv("1");
            l_insiderParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_insiderParams.setRegistDiv("1");
            l_insiderParams.setLastUpdater("scofiled");
            TestDBUtility.insertWithDel(l_insiderParams);
            
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setOrderBizDate(GtlUtils.getSystemTimestamp());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "12");
            
            LoginInfo l_info = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_info);
            
            WEB3EquityEstimatedDeliveryPrice l_price1 = new WEB3EquityEstimatedDeliveryPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                            WEB3GentradeCommission.class, double.class,
                            double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                            String.class,
                            String.class,
                            String.class,
                            boolean.class,
                            SubAccount.class,
                            WEB3EquityTradedProduct.class,
                            double.class,
                            boolean.class,
                            double.class,
                            double.class,
                            boolean.class
                            },
                            l_price1);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20070619", "yyyyMMdd"));
            l_impl.submitOrder(l_request);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public class WEB3EquityOrderServiceImplForTest extends WEB3EquityOrderServiceImpl
    {
        protected EqTypeNewOrderValidationResult validateNewCashBasedOrder(
                EqTypeNewCashBasedOrderSpec l_orderSpec,
                WEB3EquityOrderRequestAdapter l_requestAdaptor)
                throws WEB3BaseException
        {
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeNewOrderValidationResult l_result = new EqTypeNewOrderValidationResult(processingResult);
            return l_result;
        }
        
        protected WEB3TPTradingPowerResult validateTradingPower(
                WEB3GentradeSubAccount l_subAccount,
                WEB3EquityNewCashBasedOrderSpec l_orderSpec,
                boolean l_blnUpdateFlg,
                WEB3EquityTradedProduct l_tradedProduct)
                throws WEB3BaseException
            {
                WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
                l_result.setAttentionObjectionType("1");
                l_result.setLackAccountBalance(999);
                return l_result;
            }
        
        protected String getEstimatedBookPrice(
                WEB3EquityOrderRequestAdapter l_requestAdaptor)
                throws WEB3BaseException
            {
                return "10";
            }
        protected void submitNewCashBasedOrder(
                WEB3GentradeSubAccount l_subAccount,
                WEB3EquityNewCashBasedOrderSpec l_orderSpec,
                long l_lngOrderId,
                String l_strTradingPassword,
                WEB3EquityOrderRequestAdapter l_requestAdaptor)
                throws WEB3BaseException
            {
            
            }
    }
}
@

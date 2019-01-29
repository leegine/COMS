head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.54.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginChangeCloseMarginServiceImplTest.java;


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
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
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

import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeCompleteRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeCompleteResponse;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeConfirmRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeConfirmResponse;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondParams;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.gentrade.data.InsiderParams;
import webbroker3.gentrade.data.InsiderRow;
import webbroker3.gentrade.data.TaxRateTableParams;
import webbroker3.gentrade.data.TaxRateTableRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author âΩï∂ïq(íÜêu)
 * @@version 1.0
 */
public class WEB3MarginChangeCloseMarginServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginChangeCloseMarginServiceImplTest.class);
    WEB3MarginChangeCloseMarginServiceImpl l_impl = new WEB3MarginChangeCloseMarginServiceImpl();
    
    public WEB3MarginChangeCloseMarginServiceImplTest(String arg0)
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
//        super.checkMethodValue();
        super.tearDown();
    }
    
    public void testValidateOrder_Case0001()
    {
        final String STR_METHOD_NAME = "testValidateOrder()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3MarginCloseMarginChangeConfirmRequest l_request =
                new WEB3MarginCloseMarginChangeConfirmRequest();
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
            l_request.id = "123456789";
            l_request.orderQuantity = "10";
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchId(33381L);
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
            l_tradingTimeparams.setMarketCode("SP");
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
            
            LoginInfo l_info = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_info);
            
            WEB3EquityRealizedProfitAndLossPrice l_price = new WEB3EquityRealizedProfitAndLossPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimatedRealizedProfitAndLossAmount",
                    new Class[] {
                            WEB3GentradeCommission.class,
                            double.class,
                            WEB3GentradeSubAccount.class, 
                            WEB3EquityTradedProduct.class,
                            EqTypeSettleContractOrderEntry[].class,
                            double.class,
                            EqTypeOrderUnit.class,
                            double.class,
                            double.class,
                            boolean.class,
                            WEB3EquityContract.class
                            },
                    l_price);
            
            ProcessingResult l_ProcessingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeOrderValidationResult l_result = new EqTypeOrderValidationResult(l_ProcessingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "validateChangeSettleContractOrder", new Class[]
                    { SubAccount.class,
                      EqTypeChangeSettleContractOrderSpec.class }, 
                    l_result);
            
            l_request.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]{l_unit};
            
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(Calendar.getInstance().getTime());
            WEB3MarginCloseMarginChangeConfirmResponse l_response = l_impl.validateOrder(l_request);

            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateChangeSettleContractOrder", new Class[]
                    { SubAccount.class,
                      EqTypeChangeSettleContractOrderSpec.class});
            assertEquals(333812512246L, ((SubAccount)l_paramsValue3.getFirstCalled()[0]).getAccountId());
            assertEquals(123456789L, ((EqTypeChangeSettleContractOrderSpec)l_paramsValue3.getFirstCalled()[1]).getOrderId());
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimatedRealizedProfitAndLossAmount",
                    new Class[] {
                            WEB3GentradeCommission.class,
                            double.class,
                            WEB3GentradeSubAccount.class, 
                            WEB3EquityTradedProduct.class,
                            EqTypeSettleContractOrderEntry[].class,
                            double.class,
                            EqTypeOrderUnit.class,
                            double.class,
                            double.class,
                            boolean.class,
                            WEB3EquityContract.class });
            assertEquals(33381, ((WEB3GentradeCommission)l_paramsValue2.getFirstCalled()[0]).getBranchId());
            assertEquals(new Double(0.0), l_paramsValue2.getFirstCalled()[1]);
            assertEquals(333812512246L, ((WEB3GentradeSubAccount)l_paramsValue2.getFirstCalled()[2]).getAccountId());
            assertEquals("0D", ((WEB3EquityTradedProduct)l_paramsValue2.getFirstCalled()[3]).getInstitutionCode());
            assertEquals(123456, (((EqTypeSettleContractOrderEntry[])l_paramsValue2.getFirstCalled()[4])[0]).getContractId());
            assertEquals(new Double(10.0D), l_paramsValue2.getFirstCalled()[5]);
            assertEquals(333812512203L, ((EqTypeOrderUnit)l_paramsValue2.getFirstCalled()[6]).getAccountId());
            assertEquals(new Double(0.0D), l_paramsValue2.getFirstCalled()[7]);
            assertEquals(new Double(0.0D), l_paramsValue2.getFirstCalled()[8]);
            assertFalse("", ((Boolean)l_paramsValue2.getFirstCalled()[9]).booleanValue());
            
            log.debug("***************** expirationDate = " + l_response.expirationDate);
            assertEquals("", WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"));
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
        final String STR_METHOD_NAME = "testValidateOrder()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3MarginCloseMarginChangeConfirmRequest l_request =
                new WEB3MarginCloseMarginChangeConfirmRequest();
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
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(AssetRow.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_assetParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchId(33381L);
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
            l_eqtypeContractParams.setMarketId(33381);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setProductId(12342);
            l_eqtypeOrderUnitParams.setMarketId(33381);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);


            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381);
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
            l_tradingTimeparams.setMarketCode("SP");
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
            
            LoginInfo l_info = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_info);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty",
                    new Class[] {String.class},
                    "123");
            
            WEB3EquityRealizedProfitAndLossPrice l_price = new WEB3EquityRealizedProfitAndLossPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimatedRealizedProfitAndLossAmount",
                    new Class[] {
                            WEB3GentradeCommission.class, double.class,
                            WEB3GentradeSubAccount.class, WEB3EquityTradedProduct.class,
                            EqTypeSettleContractOrderEntry[].class, double.class,
                            EqTypeOrderUnit.class,
                            double.class,
                            double.class,
                            boolean.class,
                            WEB3EquityContract.class
                            },
                    l_price);
                    
            
            ProcessingResult l_ProcessingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeOrderValidationResult l_result = new EqTypeOrderValidationResult(l_ProcessingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "validateChangeSettleContractOrder", new Class[]
                    { SubAccount.class, EqTypeChangeSettleContractOrderSpec.class }, 
                    l_result);

            l_request.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]{l_unit};
            
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(Calendar.getInstance().getTime());
            WEB3MarginCloseMarginChangeConfirmResponse l_response = l_impl.validateOrder(l_request);
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateChangeSettleContractOrder", new Class[]
                    { SubAccount.class,
                      EqTypeChangeSettleContractOrderSpec.class});
            assertEquals(333812512246L, ((SubAccount)l_paramsValue3.getFirstCalled()[0]).getAccountId());
            assertEquals(123456789L, ((EqTypeChangeSettleContractOrderSpec)l_paramsValue3.getFirstCalled()[1]).getOrderId());
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimatedRealizedProfitAndLossAmount",
                    new Class[] {
                            WEB3GentradeCommission.class,
                            double.class,
                            WEB3GentradeSubAccount.class, 
                            WEB3EquityTradedProduct.class,
                            EqTypeSettleContractOrderEntry[].class,
                            double.class,
                            EqTypeOrderUnit.class,
                            double.class,
                            double.class,
                            boolean.class,
                            WEB3EquityContract.class });
            assertEquals(33381, ((WEB3GentradeCommission)l_paramsValue2.getFirstCalled()[0]).getBranchId());
            assertEquals(new Double(0.0), l_paramsValue2.getFirstCalled()[1]);
            assertEquals(333812512246L, ((WEB3GentradeSubAccount)l_paramsValue2.getFirstCalled()[2]).getAccountId());
            assertEquals("0D", ((WEB3EquityTradedProduct)l_paramsValue2.getFirstCalled()[3]).getInstitutionCode());
            assertEquals(123456, (((EqTypeSettleContractOrderEntry[])l_paramsValue2.getFirstCalled()[4])[0]).getContractId());
            assertEquals(new Double(10.0D), l_paramsValue2.getFirstCalled()[5]);
            assertEquals(333812512203L, ((EqTypeOrderUnit)l_paramsValue2.getFirstCalled()[6]).getAccountId());
            assertEquals(new Double(0.0D), l_paramsValue2.getFirstCalled()[7]);
            assertEquals(new Double(0.0D), l_paramsValue2.getFirstCalled()[8]);
            assertFalse("", ((Boolean)l_paramsValue2.getFirstCalled()[9]).booleanValue());
            
            log.debug("***************** expirationDate = " + l_response.expirationDate);
            assertEquals("20060507", WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"));
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
            WEB3MarginCloseMarginChangeCompleteRequest l_request =
                new WEB3MarginCloseMarginChangeCompleteRequest();
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
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchId(33381L);
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
            l_eqtypeContractParams.setMarketId(33381);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setProductId(12342);
            l_eqtypeOrderUnitParams.setMarketId(33381);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
    
    
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381);
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
            l_tradingTimeparams.setMarketCode("SP");
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
            
            LoginInfo l_info = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_info);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "123");
            
            WEB3EquityRealizedProfitAndLossPrice l_price = new WEB3EquityRealizedProfitAndLossPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimatedRealizedProfitAndLossAmount",
                    new Class[] {
                            WEB3GentradeCommission.class, double.class,
                            WEB3GentradeSubAccount.class, WEB3EquityTradedProduct.class,
                            EqTypeSettleContractOrderEntry[].class, double.class,
                            EqTypeOrderUnit.class,
                            double.class,
                            double.class,
                            boolean.class,
                            WEB3EquityContract.class
                            },
                    l_price);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "reCalcTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class},
                    null);
            
            ProcessingResult l_ProcessingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeOrderValidationResult l_result = new EqTypeOrderValidationResult(l_ProcessingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "validateChangeSettleContractOrder", new Class[]
                    { SubAccount.class, EqTypeChangeSettleContractOrderSpec.class }, 
                    l_result);
    
            l_request.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]{l_unit};
            
            EqTypeOrderSubmissionResult l_result1 = new EqTypeOrderSubmissionResult(l_ProcessingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "submitChangeSettleContractOrder", new Class[]
                    { SubAccount.class, EqTypeChangeSettleContractOrderSpec.class, String.class, boolean.class },
                    l_result1);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(Calendar.getInstance().getTime());
            WEB3MarginCloseMarginChangeCompleteResponse l_response = l_impl.submitOrder(l_request);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "submitChangeSettleContractOrder", new Class[]
                 { SubAccount.class, EqTypeChangeSettleContractOrderSpec.class, String.class, boolean.class });
    
            assertEquals(333812512246L, ((SubAccount)l_paramsValue1.getFirstCalled()[0]).getAccountId());
            assertEquals(123456789L, ((EqTypeChangeSettleContractOrderSpec)l_paramsValue1.getFirstCalled()[1]).getOrderId());
            assertEquals(null, l_paramsValue1.getFirstCalled()[2]);
            assertTrue(((Boolean)l_paramsValue1.getFirstCalled()[3]).booleanValue());
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateChangeSettleContractOrder", new Class[]
                    { SubAccount.class,
                      EqTypeChangeSettleContractOrderSpec.class});
            assertEquals(333812512246L, ((SubAccount)l_paramsValue3.getFirstCalled()[0]).getAccountId());
            assertEquals(123456789L, ((EqTypeChangeSettleContractOrderSpec)l_paramsValue3.getFirstCalled()[1]).getOrderId());
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimatedRealizedProfitAndLossAmount",
                    new Class[] {
                            WEB3GentradeCommission.class,
                            double.class,
                            WEB3GentradeSubAccount.class, 
                            WEB3EquityTradedProduct.class,
                            EqTypeSettleContractOrderEntry[].class,
                            double.class,
                            EqTypeOrderUnit.class,
                            double.class,
                            double.class,
                            boolean.class,
                            WEB3EquityContract.class });
            assertEquals(33381, ((WEB3GentradeCommission)l_paramsValue2.getFirstCalled()[0]).getBranchId());
            assertEquals(new Double(0.0), l_paramsValue2.getFirstCalled()[1]);
            assertEquals(333812512246L, ((WEB3GentradeSubAccount)l_paramsValue2.getFirstCalled()[2]).getAccountId());
            assertEquals("0D", ((WEB3EquityTradedProduct)l_paramsValue2.getFirstCalled()[3]).getInstitutionCode());
            assertEquals(123456, (((EqTypeSettleContractOrderEntry[])l_paramsValue2.getFirstCalled()[4])[0]).getContractId());
            assertEquals(new Double(10.0D), l_paramsValue2.getFirstCalled()[5]);
            assertEquals(333812512203L, ((EqTypeOrderUnit)l_paramsValue2.getFirstCalled()[6]).getAccountId());
            assertEquals(new Double(0.0D), l_paramsValue2.getFirstCalled()[7]);
            assertEquals(new Double(0.0D), l_paramsValue2.getFirstCalled()[8]);
            assertFalse("", ((Boolean)l_paramsValue2.getFirstCalled()[9]).booleanValue());
            
            log.debug("***************** expirationDate = " + l_response.expirationDate);
            assertEquals("20060507", WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"));
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
            WEB3MarginCloseMarginChangeCompleteRequest l_request =
                new WEB3MarginCloseMarginChangeCompleteRequest();
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
            l_request.id = "123456789";
            l_request.orderQuantity = "10";
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(EqtypeClosingContractSpecRow.TYPE);
            EqtypeClosingContractSpecParams l_eqtypeClosingContractSpecParams = TestDBUtility.getEqtypeClosingContractSpecRow();
            l_eqtypeClosingContractSpecParams.setOrderUnitId(3304148080001L);
            l_eqtypeClosingContractSpecParams.setContractId(123456);
            TestDBUtility.insertWithDel(l_eqtypeClosingContractSpecParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchId(33381L);
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
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setProductId(12342L);
            l_tradedProductParams.setMarketId(33381L);
            l_tradedProductParams.setTradedProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(3304148080001L);
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
            l_tradingTimeparams.setMarketCode("SP");
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
            
            LoginInfo l_info = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_info);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "123");
            
            WEB3EquityRealizedProfitAndLossPrice l_price = new WEB3EquityRealizedProfitAndLossPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimatedRealizedProfitAndLossAmount",
                    new Class[] {
                            WEB3GentradeCommission.class, double.class,
                            WEB3GentradeSubAccount.class, WEB3EquityTradedProduct.class,
                            EqTypeSettleContractOrderEntry[].class, double.class,
                            EqTypeOrderUnit.class,
                            double.class,
                            double.class,
                            boolean.class,
                            WEB3EquityContract.class
                            },
                    l_price);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "reCalcTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class},
                    null);
            
            ProcessingResult l_ProcessingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeOrderValidationResult l_result = new EqTypeOrderValidationResult(l_ProcessingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "validateChangeSettleContractOrder", new Class[]
                    { SubAccount.class, EqTypeChangeSettleContractOrderSpec.class }, 
                    l_result);
            
            EqTypeOrderSubmissionResult l_result1 = new EqTypeOrderSubmissionResult(l_ProcessingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "submitChangeSettleContractOrder", new Class[]
                    { SubAccount.class, EqTypeChangeSettleContractOrderSpec.class, String.class, boolean.class },
                    l_result1);

            l_request.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]{l_unit};
            
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(Calendar.getInstance().getTime());
            WEB3MarginCloseMarginChangeCompleteResponse l_response = l_impl.submitOrder(l_request);
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateChangeSettleContractOrder", new Class[]
                    { SubAccount.class,
                      EqTypeChangeSettleContractOrderSpec.class});
            assertEquals(333812512246L, ((SubAccount)l_paramsValue3.getFirstCalled()[0]).getAccountId());
            assertEquals(123456789L, ((EqTypeChangeSettleContractOrderSpec)l_paramsValue3.getFirstCalled()[1]).getOrderId());
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimatedRealizedProfitAndLossAmount",
                    new Class[] {
                            WEB3GentradeCommission.class,
                            double.class,
                            WEB3GentradeSubAccount.class, 
                            WEB3EquityTradedProduct.class,
                            EqTypeSettleContractOrderEntry[].class,
                            double.class,
                            EqTypeOrderUnit.class,
                            double.class,
                            double.class,
                            boolean.class,
                            WEB3EquityContract.class });
            assertEquals(33381, ((WEB3GentradeCommission)l_paramsValue2.getFirstCalled()[0]).getBranchId());
            assertEquals(new Double(0.0), l_paramsValue2.getFirstCalled()[1]);
            assertEquals(333812512246L, ((WEB3GentradeSubAccount)l_paramsValue2.getFirstCalled()[2]).getAccountId());
            assertEquals("0D", ((WEB3EquityTradedProduct)l_paramsValue2.getFirstCalled()[3]).getInstitutionCode());
            assertEquals(123456, (((EqTypeSettleContractOrderEntry[])l_paramsValue2.getFirstCalled()[4])[0]).getContractId());
            assertEquals(new Double(10.0D), l_paramsValue2.getFirstCalled()[5]);
            assertEquals(333812512203L, ((EqTypeOrderUnit)l_paramsValue2.getFirstCalled()[6]).getAccountId());
            assertEquals(new Double(0.0D), l_paramsValue2.getFirstCalled()[7]);
            assertEquals(new Double(0.0D), l_paramsValue2.getFirstCalled()[8]);
            assertFalse("", ((Boolean)l_paramsValue2.getFirstCalled()[9]).booleanValue());
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "submitChangeSettleContractOrder", new Class[]
                 { SubAccount.class, EqTypeChangeSettleContractOrderSpec.class, String.class, boolean.class });

            assertEquals(333812512246L, ((SubAccount)l_paramsValue1.getFirstCalled()[0]).getAccountId());
            assertEquals(123456789L, ((EqTypeChangeSettleContractOrderSpec)l_paramsValue1.getFirstCalled()[1]).getOrderId());
            assertEquals(null, l_paramsValue1.getFirstCalled()[2]);
            assertTrue(((Boolean)l_paramsValue1.getFirstCalled()[3]).booleanValue());
            
            log.debug("***************** expirationDate = " + l_response.expirationDate);
            assertEquals("", WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@

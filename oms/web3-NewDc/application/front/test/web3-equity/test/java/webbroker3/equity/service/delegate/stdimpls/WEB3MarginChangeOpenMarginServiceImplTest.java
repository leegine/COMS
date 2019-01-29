head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.56.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginChangeOpenMarginServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : (WEB3MarginChangeOpenMarginServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/14 âΩï∂ïq (íÜêu) ÉÇÉfÉã No.1170
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import test.util.TestDBUtility;
import webbroker3.equity.WEB3EquityEstimatedContractPrice;
import webbroker3.equity.WEB3EquityOrderValidationResult;
import webbroker3.equity.WEB3MarginChangeOrderSpec;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeCompleteRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeCompleteResponse;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeConfirmRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeConfirmResponse;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.InsiderParams;
import webbroker3.gentrade.data.InsiderRow;
import webbroker3.gentrade.data.InstBranchProductParams;
import webbroker3.gentrade.data.TaxRateTableParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
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

/**
 * @@author âΩï∂ïq
 * @@version 1.0
 */
public class WEB3MarginChangeOpenMarginServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginChangeOpenMarginServiceImplTest.class);
    WEB3MarginChangeOpenMarginServiceImplForTest l_impl = new WEB3MarginChangeOpenMarginServiceImplForTest();

    public WEB3MarginChangeOpenMarginServiceImplTest(String arg0)
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
    
    public void testValidateOrder()
    {
        final String STR_METHOD_NAME = "testValidateOrder()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityBizLogicProvider",
                "calcCommission", new Class[]
                { WEB3GentradeCommission.class, SubAccount.class },
                null);
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(33381L);
            l_commission.setCommissionProductCode("1");
            l_commission.setOrderBizDate(GtlUtils.getSystemTimestamp());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityBizLogicProvider",
                "createCommission",
                new Class[]{WEB3GentradeSubAccount.class,String.class,
                    Date.class,String.class,String.class,double.class,OrderCategEnum.class},
                l_commission);

            TestDBUtility.deleteAll(TaxRateTableParams.TYPE);
            TaxRateTableParams l_taxRateTableParams = new TaxRateTableParams();
            l_taxRateTableParams.setInstitutionCode("0D");
            l_taxRateTableParams.setTaxType("10");
            l_taxRateTableParams.setTaxRate(1.1D);
            l_taxRateTableParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_taxRateTableParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_taxRateTableParams.setAppliStartDate(WEB3DateUtility.getDate("20040924","yyyyMMdd"));
            l_taxRateTableParams.setAppliEndDate(WEB3DateUtility.getDate("20400924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_taxRateTableParams);

            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(33381L);
            l_instBranchProductParams.setCommissionProductCode("1");
            TestDBUtility.insertWithDel(l_instBranchProductParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setMarketId(33381L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setRepaymentType("0");
            l_eqtypeOrderUnitParams.setCommProductCode("1");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setMarketId(33381L);
            l_tradedProductParams.setTradedProductId(330304148080000L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            l_eqtypeTradedProductParams.setMarketId(33381L);
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(InsiderRow.TYPE);
            InsiderParams l_insiderParams = new InsiderParams();
            l_insiderParams.setAccountId(333812512203L);
            l_insiderParams.setBranchId(33381L);
            l_insiderParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_insiderParams.setInstitutionCode("0D");
            l_insiderParams.setProductId(3304148080001L);
            l_insiderParams.setRelationDiv("1");
            l_insiderParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_insiderParams.setRegistDiv("1");
            l_insiderParams.setLastUpdater("scofiled");
            TestDBUtility.insertWithDel(l_insiderParams);

            WEB3EquityEstimatedContractPrice l_price = new WEB3EquityEstimatedContractPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcContractAmountAtOrder",
                new Class[]
                { WEB3GentradeCommission.class, double.class, double.class, double.class,
                    EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class, String.class,
                    String.class, String.class, boolean.class, boolean.class, SubAccount.class,
                    EqTypeTradedProduct.class, double.class, double.class, double.class, boolean.class },
                    l_price);
            
            ProcessingResult l_results = ProcessingResult.newSuccessResultInstance();
            WEB3EquityOrderValidationResult l_result = new WEB3EquityOrderValidationResult(l_results, true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager", "validateChangeOrder", new Class[]
                { SubAccount.class, EqTypeChangeOrderSpec.class },
                l_result);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeBizLogicProvider",
                "calcCommission",
                new Class[]{ WEB3GentradeCommission.class, SubAccount.class },
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityBizLogicProvider",
                "calcCommission",
                new Class[]{ WEB3GentradeCommission.class, SubAccount.class },
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeBizLogicProvider", "calcSalesTax", new Class[]
                { double.class, Timestamp.class, SubAccount.class },
                new Double(12D));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512203L));

            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginId",
                new Class[] {},
                new Long(3338111123L));

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(Calendar.getInstance().getTime());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeBranch",
                "getMarketMessageSuspension",
                new Class[] {ProductTypeEnum.class,
                    String.class,
                    String.class},
                new Long(0));

            WEB3MarginOpenMarginChangeConfirmRequest l_request =
                new WEB3MarginOpenMarginChangeConfirmRequest();
            l_request.id = "123456789";
            l_request.orderQuantity = "10";
            WEB3MarginCloseMarginContractUnit l_unit =
                new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "123456";
            l_unit.orderQuantity = "100";
            l_request.orderPriceDiv = "0";
            l_unit.settlePriority = "123";
            l_request.orderCondType = "0";
            l_request.priceCondType = "0";
            l_request.orderCondType = "1";
            l_request.execCondType = "1";
            l_request.stopOrderCondPrice = "15";
            l_request.stopOrderCondOperator = "1";
            l_request.expirationDateType = "1";
            
            WEB3MarginOpenMarginChangeConfirmResponse l_response = l_impl.validateOrder(l_request);
            assertEquals(WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"), "");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitOrder()
    {
        final String STR_METHOD_NAME = "testSubmitOrder()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setMarketId(33381L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setRepaymentType("0");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setMarketId(33381L);
            l_tradedProductParams.setTradedProductId(330304148080000L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            l_eqtypeTradedProductParams.setMarketId(33381L);
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(InsiderRow.TYPE);
            InsiderParams l_insiderParams = new InsiderParams();
            l_insiderParams.setAccountId(333812512203L);
            l_insiderParams.setBranchId(33381L);
            l_insiderParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_insiderParams.setInstitutionCode("0D");
            l_insiderParams.setProductId(3304148080001L);
            l_insiderParams.setRelationDiv("1");
            l_insiderParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_insiderParams.setRegistDiv("1");
            l_insiderParams.setLastUpdater("scofiled");
            TestDBUtility.insertWithDel(l_insiderParams);

            WEB3EquityEstimatedContractPrice l_price = new WEB3EquityEstimatedContractPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcContractAmountAtOrder",
                new Class[]
                { WEB3GentradeCommission.class, double.class, double.class, double.class,
                        EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class, String.class,
                        String.class, String.class, boolean.class, boolean.class, SubAccount.class,
                        EqTypeTradedProduct.class, double.class, double.class, double.class, boolean.class },
                        l_price);
            
            ProcessingResult l_results = ProcessingResult.newSuccessResultInstance();
            WEB3EquityOrderValidationResult l_result = new WEB3EquityOrderValidationResult(l_results, true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager", "validateChangeOrder", new Class[]
                { SubAccount.class, EqTypeChangeOrderSpec.class },
                l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeBizLogicProvider",
                "calcCommission", 
                new Class[]{ WEB3GentradeCommission.class, SubAccount.class },
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeBizLogicProvider", "calcSalesTax", new Class[]
                { double.class, Timestamp.class, SubAccount.class },
                new Double(12D));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512203L));
            
            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                l_loginInfo);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginId",
                new Class[] {},
                new Long(3338111123L));
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(Calendar.getInstance().getTime());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeBranch",
                "getMarketMessageSuspension",
                new Class[] {ProductTypeEnum.class,
                    String.class,
                    String.class},
                new Long(0));

            EqTypeOrderSubmissionResult l_result3 = new EqTypeOrderSubmissionResult(l_results);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager", "submitChangeOrder", new Class[]
                { SubAccount.class, EqTypeChangeOrderSpec.class, String.class, boolean.class },
                l_result3);
            
            WEB3MarginOpenMarginChangeCompleteRequest l_request =
                new WEB3MarginOpenMarginChangeCompleteRequest();
            l_request.id = "123456789";
            l_request.orderQuantity = "10";
            WEB3MarginCloseMarginContractUnit l_unit =
                new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "123456";
            l_unit.orderQuantity = "100";
            l_request.orderPriceDiv = "0";
            l_unit.settlePriority = "123";
            l_request.orderCondType = "0";
            l_request.priceCondType = "0";
            l_request.orderCondType = "1";
            l_request.execCondType = "1";
            l_request.stopOrderCondPrice = "15";
            l_request.stopOrderCondOperator = "1";
            l_request.expirationDateType = "1";
            
            WEB3MarginOpenMarginChangeCompleteResponse l_response = l_impl.submitOrder(l_request);
            assertEquals(WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"), "");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public class WEB3MarginChangeOpenMarginServiceImplForTest extends WEB3MarginChangeOpenMarginServiceImpl
    {
        protected WEB3TPTradingPowerResult validateTradingPower(
                WEB3GentradeSubAccount l_subAccount,
                WEB3MarginChangeOrderSpec l_orderSpec,
                boolean l_blnUpdateFlg,
                WEB3EquityOrderValidationResult l_validationResult) 
        {
            return null;
        }
    }
}
@

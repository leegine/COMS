head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.11.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualSellServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3ClaimDivDef;
import webbroker3.common.define.WEB3DeliveryMethodDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.mf.WEB3MutualFundEstimatedPrice;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundTradingTimeManagementForMock;
import webbroker3.mf.message.WEB3MutualSellCompleteRequest;
import webbroker3.mf.message.WEB3MutualSellCompleteResponse;
import webbroker3.mf.message.WEB3MutualSellConfirmRequest;
import webbroker3.mf.message.WEB3MutualSellConfirmResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualSellServiceImplTest extends TestBaseForMock
{
    WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSellServiceImplTest.class);
    WEB3MutualSellServiceImpl l_impl = new WEB3MutualSellServiceImpl();
    
    public WEB3MutualSellServiceImplTest(String arg0)
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

    public void testValidateSell_T01()
    {
        final String STR_METHOD_NAME = "testValidateSell_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3MutualSellConfirmRequest l_request = new WEB3MutualSellConfirmRequest();
            l_request.id = "123";
            l_request.sellBuyDiv = WEB3ClaimDivDef.BUY;
            l_request.mutualProductCode = "0";
            l_request.specifyDiv = "3";
            l_request.mutualOrderQuantity = null;
            l_request.settleDiv = WEB3SettlementDivDef.JAPANESE_CURRENCY;
            l_request.deliveryDiv = WEB3DeliveryMethodDef.BANK_TRANSFER;
            l_request.orderedDate =  GtlUtils.getSystemTimestamp();
            l_request.mutualOrderQuantity = "456";
            
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            //l_mainAccountParams.setForeignContDiv("0");
            l_mainAccountParams.setMarginGenAccOpenDiv("0");
            l_mainAccountParams.setMarginSysAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(333812512203L));
            
            //institution
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_fundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }
            
            
            //WEB3MutualFundProductManager
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager", 
                "getMutualFundProduct",
                new Class[] {Institution.class, String.class, String.class},
                l_fundTradedProduct);
            
            ProcessingResult processingResult =
                ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_validationResult =
                new NewOrderValidationResult(processingResult);
            //WEB3MutualFundOrderManager
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundOrderManager", 
                    "validateNewOrder",
                    new Class[] {SubAccount.class, WEB3MutualFundProduct.class, 
                            double.class, String.class, String.class, String.class,
                            WEB3MutualFundProduct.class, TaxTypeEnum.class,String.class},
                    l_validationResult);
            
            //WEB3GentradeOrderValidator
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", 
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_result);
            
            //WEB3MutualFundOrderManagerReusableValidationsCheck
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck", 
                "isSellQtyLimitRateExcess",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, WEB3MutualFundProduct.class,
                        String.class, double.class, String.class, double.class,
                        String.class, String.class, String.class, String.class, Date.class},
                Boolean.TRUE);
            
            //MutualFundOrderUnitParams
            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            l_fundOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_fundOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_fundOrderUnitParams.setProductId(l_fundProductParams.getProductId());
            l_fundOrderUnitParams.setQuantityType(QuantityTypeEnum.AMOUNT);
            l_fundOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_fundOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_fundOrderUnitParams.setOrderType(OrderTypeEnum.MF_SELL);
            TestDBUtility.insertWithDel(l_fundOrderUnitParams);
            
            //WEB3MutualFundTradingTimeManagement
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            //Asset
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setAssetId(123l);
            TestDBUtility.insertWithDel(l_assetParams);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            l_impl.validateSell(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02269, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "----------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateSell_T02()
    {
        final String STR_METHOD_NAME = "testValidateSell_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3MutualSellConfirmRequest l_request = new WEB3MutualSellConfirmRequest();
            l_request.id = "123";
            l_request.sellBuyDiv = WEB3ClaimDivDef.BUY;
            l_request.mutualProductCode = "0";
            l_request.specifyDiv = "3";
            l_request.mutualOrderQuantity = null;
            l_request.settleDiv = WEB3SettlementDivDef.JAPANESE_CURRENCY;
            l_request.deliveryDiv = WEB3DeliveryMethodDef.BANK_TRANSFER;
            l_request.orderedDate =  GtlUtils.getSystemTimestamp();
            l_request.mutualOrderQuantity = "456";
            
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            //l_mainAccountParams.setForeignContDiv("0");
            l_mainAccountParams.setMarginGenAccOpenDiv("0");
            l_mainAccountParams.setMarginSysAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(333812512203L));
            
            //institution
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_fundProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }
            
            
            //WEB3MutualFundProductManager
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager", 
                "getMutualFundProduct",
                new Class[] {Institution.class, String.class, String.class},
                l_fundTradedProduct);
            
            ProcessingResult processingResult =
                ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_validationResult =
                new NewOrderValidationResult(processingResult);
            //WEB3MutualFundOrderManager
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundOrderManager", 
                    "validateNewOrder",
                    new Class[] {SubAccount.class, WEB3MutualFundProduct.class, 
                            double.class, String.class, String.class, String.class,
                            WEB3MutualFundProduct.class, TaxTypeEnum.class,String.class},
                    l_validationResult);
            
            //WEB3GentradeOrderValidator
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", 
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_result);
            
            //WEB3MutualFundOrderManagerReusableValidationsCheck
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck", 
                "isSellQtyLimitRateExcess",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, WEB3MutualFundProduct.class,
                        String.class, double.class, String.class, double.class,
                        String.class, String.class, String.class, String.class, Date.class},
                Boolean.TRUE);
            
            //MutualFundOrderUnitParams
            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            l_fundOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_fundOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_fundOrderUnitParams.setProductId(l_fundProductParams.getProductId());
            l_fundOrderUnitParams.setQuantityType(QuantityTypeEnum.AMOUNT);
            l_fundOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_fundOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_fundOrderUnitParams.setOrderType(OrderTypeEnum.MF_SELL);
            TestDBUtility.insertWithDel(l_fundOrderUnitParams);
            
            //WEB3MutualFundTradingTimeManagement
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            //Asset
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setAssetId(123l);
            TestDBUtility.insertWithDel(l_assetParams);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            l_impl.validateSell(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02269, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "----------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateSell_T03()
    {
        final String STR_METHOD_NAME = "testValidateSell_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3MutualSellConfirmRequest l_request = new WEB3MutualSellConfirmRequest();
            l_request.id = "123";
            l_request.sellBuyDiv = WEB3ClaimDivDef.BUY;
            l_request.mutualProductCode = "0";
            l_request.specifyDiv = "3";
            l_request.mutualOrderQuantity = null;
            l_request.settleDiv = WEB3SettlementDivDef.JAPANESE_CURRENCY;
            l_request.deliveryDiv = WEB3DeliveryMethodDef.BANK_TRANSFER;
            l_request.orderedDate =  GtlUtils.getSystemTimestamp();
            l_request.mutualOrderQuantity = "456";
            
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            //l_mainAccountParams.setForeignContDiv("0");
            l_mainAccountParams.setMarginGenAccOpenDiv("0");
            l_mainAccountParams.setMarginSysAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //OpLoginSecurityServiceImpl
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(333812512203L));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getSessionProperty",
                new Class[] {String.class},
                "jiddk");
            
            //institution
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //branch
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            //BranchPreferences 
            BranchPreferencesParams l_preferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_preferencesParams.setBranchId(l_branchParams.getBranchId());
            l_preferencesParams.setName(WEB3BranchPreferencesNameDef.DISSOLUTION_CREATE_PAYMENT_ORDER);
            l_preferencesParams.setNameSerialNo(1);
            l_preferencesParams.setValue("0");
            TestDBUtility.insertWithDel(l_preferencesParams);
            
            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_fundProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }
            
            
            //WEB3MutualFundProductManager
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager", 
                "getMutualFundProduct",
                new Class[] {Institution.class, String.class, String.class},
                l_fundTradedProduct);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager", 
                "getExecutedDate",
                new Class[] {Institution.class, String.class, Date.class},
                GtlUtils.getSystemTimestamp());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager", 
                "getDeliveryDate",
                new Class[] {Institution.class, String.class, boolean.class, Date.class},
                GtlUtils.getSystemTimestamp());
            
            ProcessingResult processingResult =
                ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_validationResult =
                new NewOrderValidationResult(processingResult);
            
            //WEB3MutualFundOrderManager
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundOrderManager", 
                    "validateNewOrder",
                    new Class[] {SubAccount.class, WEB3MutualFundProduct.class, 
                            double.class, String.class, String.class, String.class,
                            WEB3MutualFundProduct.class, TaxTypeEnum.class,String.class},
                    l_validationResult);
            
            WEB3MutualFundEstimatedPrice l_estimatedPrice =
                new WEB3MutualFundEstimatedPrice();
            l_estimatedPrice.setEstimatedQty(10D);
            l_estimatedPrice.setEstimatedPrice(100D);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager", 
                "calcEstimateDeliveryAmount",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, WEB3MutualFundProduct.class,
                        String.class, double.class, String.class, String.class, String.class,
                        String.class, String.class, Date.class},
             l_estimatedPrice);
            
            //WEB3GentradeOrderValidator
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", 
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_result);
            
            //WEB3MutualFundOrderManagerReusableValidationsCheck
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck", 
                "isSellQtyLimitRateExcess",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, WEB3MutualFundProduct.class,
                        String.class, double.class, String.class, double.class,
                        String.class, String.class, String.class, String.class, Date.class},
                Boolean.FALSE);
            
            //MutualFundOrderUnitParams
            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            l_fundOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_fundOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_fundOrderUnitParams.setProductId(l_fundProductParams.getProductId());
            l_fundOrderUnitParams.setQuantityType(QuantityTypeEnum.AMOUNT);
            l_fundOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_fundOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_fundOrderUnitParams.setOrderType(OrderTypeEnum.MF_SELL);
            TestDBUtility.insertWithDel(l_fundOrderUnitParams);
            
            //WEB3MutualFundTradingTimeManagement
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            //Asset
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setAssetId(123l);
            TestDBUtility.insertWithDel(l_assetParams);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(l_request.orderedDate);
            WEB3MutualSellConfirmResponse l_response = l_impl.validateSell(l_request);
            assertEquals("10", l_response.estimatedQty);
            log.info(STR_METHOD_NAME + "----------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitSell_T01()
    {
        final String STR_METHOD_NAME = "testSubmitSell_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3MutualSellCompleteRequest l_request = new WEB3MutualSellCompleteRequest();
            l_request.orderId = "1001";
            l_request.id = "123";
            l_request.sellBuyDiv = WEB3ClaimDivDef.BUY;
            l_request.mutualProductCode = "0";
            l_request.specifyDiv = "3";
            l_request.mutualOrderQuantity = null;
            l_request.settleDiv = WEB3SettlementDivDef.JAPANESE_CURRENCY;
            l_request.deliveryDiv = WEB3DeliveryMethodDef.BANK_TRANSFER;
            l_request.orderedDate =  GtlUtils.getSystemTimestamp();
            l_request.mutualOrderQuantity = "456";

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            //l_mainAccountParams.setForeignContDiv("0");
            l_mainAccountParams.setMarginGenAccOpenDiv("0");
            l_mainAccountParams.setMarginSysAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(333812512203L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(3338111123L));
            
            //institution
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_fundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }
            
            
            //WEB3MutualFundProductManager
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager", 
                "getMutualFundProduct",
                new Class[] {Institution.class, String.class, String.class},
                l_fundTradedProduct);
            
            ProcessingResult processingResult =
                ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_validationResult =
                new NewOrderValidationResult(processingResult);
            //WEB3MutualFundOrderManager
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundOrderManager", 
                    "validateNewOrder",
                    new Class[] {SubAccount.class, WEB3MutualFundProduct.class, 
                            double.class, String.class, String.class, String.class,
                            WEB3MutualFundProduct.class, TaxTypeEnum.class,String.class},
                    l_validationResult);
            
            //WEB3GentradeOrderValidator
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", 
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_result);
            
            //WEB3MutualFundOrderManagerReusableValidationsCheck
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck", 
                "isSellQtyLimitRateExcess",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, WEB3MutualFundProduct.class,
                        String.class, double.class, String.class, double.class,
                        String.class, String.class, String.class, String.class, Date.class},
                Boolean.TRUE);
            
            //MutualFundOrderUnitParams
            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            l_fundOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_fundOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_fundOrderUnitParams.setProductId(l_fundProductParams.getProductId());
            l_fundOrderUnitParams.setQuantityType(QuantityTypeEnum.AMOUNT);
            l_fundOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_fundOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_fundOrderUnitParams.setOrderType(OrderTypeEnum.MF_SELL);
            TestDBUtility.insertWithDel(l_fundOrderUnitParams);
            
            //WEB3MutualFundTradingTimeManagement
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            //Asset
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setAssetId(123l);
            TestDBUtility.insertWithDel(l_assetParams);
            
            //Trader
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            l_impl.submitSell(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02269, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "----------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitSell_T02()
    {
        final String STR_METHOD_NAME = "testSubmitSell_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3MutualSellCompleteRequest l_request = new WEB3MutualSellCompleteRequest();
            l_request.orderId = "1001";
            l_request.id = "123";
            l_request.sellBuyDiv = WEB3ClaimDivDef.BUY;
            l_request.mutualProductCode = "0";
            l_request.specifyDiv = "3";
            l_request.mutualOrderQuantity = null;
            l_request.settleDiv = WEB3SettlementDivDef.JAPANESE_CURRENCY;
            l_request.deliveryDiv = WEB3DeliveryMethodDef.BANK_TRANSFER;
            l_request.orderedDate =  GtlUtils.getSystemTimestamp();
            l_request.mutualOrderQuantity = "456";

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            //l_mainAccountParams.setForeignContDiv("0");
            l_mainAccountParams.setMarginGenAccOpenDiv("0");
            l_mainAccountParams.setMarginSysAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(333812512203L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(3338111123L));
            
            //institution
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_fundProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }
            
            
            //WEB3MutualFundProductManager
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager", 
                "getMutualFundProduct",
                new Class[] {Institution.class, String.class, String.class},
                l_fundTradedProduct);
            
            ProcessingResult processingResult =
                ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_validationResult =
                new NewOrderValidationResult(processingResult);
            //WEB3MutualFundOrderManager
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundOrderManager", 
                    "validateNewOrder",
                    new Class[] {SubAccount.class, WEB3MutualFundProduct.class, 
                            double.class, String.class, String.class, String.class,
                            WEB3MutualFundProduct.class, TaxTypeEnum.class,String.class},
                    l_validationResult);
            
            //WEB3GentradeOrderValidator
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", 
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_result);
            
            //WEB3MutualFundOrderManagerReusableValidationsCheck
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck", 
                "isSellQtyLimitRateExcess",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, WEB3MutualFundProduct.class,
                        String.class, double.class, String.class, double.class,
                        String.class, String.class, String.class, String.class, Date.class},
                Boolean.TRUE);
            
            //MutualFundOrderUnitParams
            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            l_fundOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_fundOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_fundOrderUnitParams.setProductId(l_fundProductParams.getProductId());
            l_fundOrderUnitParams.setQuantityType(QuantityTypeEnum.AMOUNT);
            l_fundOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_fundOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_fundOrderUnitParams.setOrderType(OrderTypeEnum.MF_SELL);
            TestDBUtility.insertWithDel(l_fundOrderUnitParams);
            
            //WEB3MutualFundTradingTimeManagement
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            //Asset
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setAssetId(123l);
            TestDBUtility.insertWithDel(l_assetParams);
            
            //Trader
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            l_impl.submitSell(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02269, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "----------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitSell_T03()
    {
        final String STR_METHOD_NAME = "testSubmitSell_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3MutualSellCompleteRequest l_request = new WEB3MutualSellCompleteRequest();
            l_request.orderId = "1001";
            l_request.id = "123";
            l_request.sellBuyDiv = WEB3ClaimDivDef.BUY;
            l_request.mutualProductCode = "0";
            l_request.specifyDiv = "3";
            l_request.mutualOrderQuantity = null;
            l_request.settleDiv = WEB3SettlementDivDef.JAPANESE_CURRENCY;
            l_request.deliveryDiv = WEB3DeliveryMethodDef.BANK_TRANSFER;
            l_request.orderedDate =  GtlUtils.getSystemTimestamp();
            l_request.mutualOrderQuantity = "456";

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            //l_mainAccountParams.setForeignContDiv("0");
            l_mainAccountParams.setMarginGenAccOpenDiv("0");
            l_mainAccountParams.setMarginSysAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(333812512203L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getSessionProperty",
                new Class[] {String.class},
                "jiddk");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(3338111123L));
            
            //institution
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_fundProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }
            
            
            //WEB3MutualFundProductManager
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager", 
                "getMutualFundProduct",
                new Class[] {Institution.class, String.class, String.class},
                l_fundTradedProduct);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager", 
                "getExecutedDate",
                new Class[] {Institution.class, String.class, Date.class},
                GtlUtils.getSystemTimestamp());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager", 
                "getDeliveryDate",
                new Class[] {Institution.class, String.class, boolean.class, Date.class},
                GtlUtils.getSystemTimestamp());
            
            ProcessingResult processingResult =
                ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_validationResult =
                new NewOrderValidationResult(processingResult);
            
            //WEB3MutualFundOrderManager
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundOrderManager", 
                    "validateNewOrder",
                    new Class[] {SubAccount.class, WEB3MutualFundProduct.class, 
                            double.class, String.class, String.class, String.class,
                            WEB3MutualFundProduct.class, TaxTypeEnum.class,String.class},
                    l_validationResult);
            
            WEB3MutualFundEstimatedPrice l_estimatedPrice =
                new WEB3MutualFundEstimatedPrice();
            l_estimatedPrice.setEstimatedQty(10D);
            l_estimatedPrice.setEstimatedPrice(100D);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager", 
                "calcEstimateDeliveryAmount",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, WEB3MutualFundProduct.class,
                        String.class, double.class, String.class, String.class, String.class,
                        String.class, String.class, Date.class},
             l_estimatedPrice);
            
            //WEB3GentradeOrderValidator
            OrderSubmissionResult l_submissionResult = new OrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "submitNewOrder",
                new Class[] {SubAccount.class, ProductTypeEnum.class, NewOrderSpec.class,
                        long.class, String.class, boolean.class},
                l_submissionResult);
            
            //WEB3MutualFundOrderManagerReusableValidationsCheck
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck", 
                "isSellQtyLimitRateExcess",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, WEB3MutualFundProduct.class,
                        String.class, double.class, String.class, double.class,
                        String.class, String.class, String.class, String.class, Date.class},
                Boolean.FALSE);

            //WEB3TPTradingPowerServiceImpl
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);

            //MutualFundOrderUnitParams
            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            l_fundOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_fundOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_fundOrderUnitParams.setProductId(l_fundProductParams.getProductId());
            l_fundOrderUnitParams.setQuantityType(QuantityTypeEnum.AMOUNT);
            l_fundOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_fundOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_fundOrderUnitParams.setOrderType(OrderTypeEnum.MF_SELL);
            TestDBUtility.insertWithDel(l_fundOrderUnitParams);
            
            //WEB3MutualFundTradingTimeManagement
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            //Asset
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setAssetId(123l);
            TestDBUtility.insertWithDel(l_assetParams);

            //Trader
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(l_request.orderedDate);
            WEB3MutualSellCompleteResponse l_response = l_impl.submitSell(l_request);

            assertEquals(l_request.orderId, l_response.orderActionId);
            log.info(STR_METHOD_NAME + "----------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidatePaymentMethod_T01()
    {
        final String STR_METHOD_NAME = "testValidatePaymentMethod_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //SubAccount 
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //MainAccount
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            //l_mainAccountParams.setForeignContDiv("0");
            l_mainAccountParams.setMarginGenAccOpenDiv("0");
            l_mainAccountParams.setMarginSysAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //Asset
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setAssetId(123l);
            TestDBUtility.insertWithDel(l_assetParams);
            
            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            l_impl.validatePaymentMethod(l_subAccount, "123", l_fundTradedProduct);
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02732, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "----------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidatePaymentMethod_T02()
    {
        final String STR_METHOD_NAME = "testValidatePaymentMethod_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //SubAccount 
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //MainAccount
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            //l_mainAccountParams.setForeignContDiv("0");
            l_mainAccountParams.setMarginGenAccOpenDiv("0");
            l_mainAccountParams.setMarginSysAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //Asset
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setAssetId(123l);
            TestDBUtility.insertWithDel(l_assetParams);
            
            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            l_impl.validatePaymentMethod(l_subAccount, "123", l_fundTradedProduct);
            log.info(STR_METHOD_NAME + "----------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

  public void testValidateSellCase0001()
  {
      final String STR_METHOD_NAME = "testValidateSellCase0001()";
      log.entering(STR_METHOD_NAME);
      try
      {
          WEB3MutualSellConfirmRequest l_request = new WEB3MutualSellConfirmRequest();
          l_request.id = "123";
          l_request.sellBuyDiv = WEB3ClaimDivDef.BUY;
          l_request.mutualProductCode = "0";
          l_request.specifyDiv = "3";
          l_request.mutualOrderQuantity = null;
          l_request.settleDiv = WEB3SettlementDivDef.JAPANESE_CURRENCY;
          l_request.deliveryDiv = WEB3DeliveryMethodDef.BANK_TRANSFER;
          l_request.orderedDate =  GtlUtils.getSystemTimestamp();
          l_request.mutualOrderQuantity = "456";
          
          SubAccountParams l_subAccountParams =
              TestDBUtility.getSubAccountRow();
          l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
          TestDBUtility.insertWithDel(l_subAccountParams);
          
          MainAccountParams l_mainAccountParams =
              TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
          l_mainAccountParams.setMarginGenAccOpenDiv("0");
          l_mainAccountParams.setMarginSysAccOpenDiv("0");
          TestDBUtility.insertWithDel(l_mainAccountParams);
          
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
              "getAccountId",
              new Class[] {},
              new Long(333812512203L));
          
          //institution
          InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
          TestDBUtility.insertWithDel(l_institutionParams);
          
          //WEB3MutualFundProduct
          ProductParams l_productParams = TestDBUtility.getProductRow();
          l_productParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
          TestDBUtility.insertWithDel(l_productParams);
          MutualFundProductParams l_fundProductParams =
              TestDBUtility.getMutualFundProductRow();
          l_fundProductParams.setProductId(l_productParams.getProductId());
          l_fundProductParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
          l_fundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
          TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
          TestDBUtility.insertWithDel(l_fundProductParams);
          WEB3MutualFundProduct l_fundTradedProduct = null;
          try
          {
              l_fundTradedProduct =
                  new WEB3MutualFundProduct(l_fundProductParams);
          }
          catch(Exception l_exc)
          {
              log.error(STR_METHOD_NAME, l_exc);
          }
          
          
          //WEB3MutualFundProductManager
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "webbroker3.mf.WEB3MutualFundProductManager", 
              "getMutualFundProduct",
              new Class[] {Institution.class, String.class, String.class},
              l_fundTradedProduct);
          
          ProcessingResult processingResult =
              ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80002);
          
          //WEB3GentradeOrderValidator
          OrderValidationResult l_result = new OrderValidationResult(processingResult);
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "webbroker3.gentrade.WEB3GentradeOrderValidator", 
              "validateAccountForTrading",
              new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
              l_result);
          
          //MutualFundOrderUnitParams
          MutualFundOrderUnitParams l_fundOrderUnitParams =
              this.getMutualFundOrderUnitParams();
          l_fundOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
          l_fundOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
          l_fundOrderUnitParams.setProductId(l_fundProductParams.getProductId());
          l_fundOrderUnitParams.setQuantityType(QuantityTypeEnum.AMOUNT);
          l_fundOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
          l_fundOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
          l_fundOrderUnitParams.setOrderType(OrderTypeEnum.MF_SELL);
          TestDBUtility.insertWithDel(l_fundOrderUnitParams);
          
          //WEB3MutualFundTradingTimeManagement
          WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
          WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
          //Asset
          AssetParams l_assetParams = TestDBUtility.getAssetRow();
          l_assetParams.setAccountId(l_subAccountParams.getAccountId());
          l_assetParams.setAssetId(123l);
          TestDBUtility.insertWithDel(l_assetParams);
          TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
          l_impl.validateSell(l_request);
          fail();
      }
      catch(WEB3BusinessLayerException l_exc)
      {
          log.exiting(STR_METHOD_NAME);
          l_exc.printStackTrace();
          assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_exc.getErrorInfo());
          log.info(STR_METHOD_NAME + "----------------->ok");
      }
      catch(Exception l_exc)
      {
          log.exiting(STR_METHOD_NAME);
          l_exc.printStackTrace();
          fail();
      }
      log.exiting(STR_METHOD_NAME);
  }
    
  public void testSubmitSellCase0001()
  {
      final String STR_METHOD_NAME = "testSubmitSellCase0001()";
      log.entering(STR_METHOD_NAME);
      try
      {
          WEB3MutualSellCompleteRequest l_request = new WEB3MutualSellCompleteRequest();
          l_request.orderId = "1001";
          l_request.id = "123";
          l_request.sellBuyDiv = WEB3ClaimDivDef.BUY;
          l_request.mutualProductCode = "0";
          l_request.specifyDiv = "3";
          l_request.mutualOrderQuantity = null;
          l_request.settleDiv = WEB3SettlementDivDef.JAPANESE_CURRENCY;
          l_request.deliveryDiv = WEB3DeliveryMethodDef.BANK_TRANSFER;
          l_request.orderedDate =  GtlUtils.getSystemTimestamp();
          l_request.mutualOrderQuantity = "456";

          SubAccountParams l_subAccountParams =
              TestDBUtility.getSubAccountRow();
          l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
          TestDBUtility.insertWithDel(l_subAccountParams);
          
          MainAccountParams l_mainAccountParams =
              TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
          //l_mainAccountParams.setForeignContDiv("0");
          l_mainAccountParams.setMarginGenAccOpenDiv("0");
          l_mainAccountParams.setMarginSysAccOpenDiv("0");
          TestDBUtility.insertWithDel(l_mainAccountParams);
          
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
              "getAccountId",
              new Class[] {},
              new Long(333812512203L));
          
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
              "getLoginInfo",
              new Class[] {},
              new LoginInfoImplForMock());
          
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
              "getLoginId",
              new Class[] {},
              new Long(3338111123L));
          
          //institution
          InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
          TestDBUtility.insertWithDel(l_institutionParams);
          
          //WEB3MutualFundProduct
          ProductParams l_productParams = TestDBUtility.getProductRow();
          l_productParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
          TestDBUtility.insertWithDel(l_productParams);
          MutualFundProductParams l_fundProductParams =
              TestDBUtility.getMutualFundProductRow();
          l_fundProductParams.setProductId(l_productParams.getProductId());
          l_fundProductParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
          l_fundProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
          TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
          TestDBUtility.insertWithDel(l_fundProductParams);
          WEB3MutualFundProduct l_fundTradedProduct = null;
          try
          {
              l_fundTradedProduct =
                  new WEB3MutualFundProduct(l_fundProductParams);
          }
          catch(Exception l_exc)
          {
              log.error(STR_METHOD_NAME, l_exc);
          }
          
          
          //WEB3MutualFundProductManager
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "webbroker3.mf.WEB3MutualFundProductManager", 
              "getMutualFundProduct",
              new Class[] {Institution.class, String.class, String.class},
              l_fundTradedProduct);
          
          ProcessingResult processingResult =
              ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80002);
          
          //WEB3GentradeOrderValidator
          OrderValidationResult l_result = new OrderValidationResult(processingResult);
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.gentrade.WEB3GentradeOrderValidator", 
                  "validateAccountForTrading",
                  new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                  l_result);

          //MutualFundOrderUnitParams
          MutualFundOrderUnitParams l_fundOrderUnitParams =
              this.getMutualFundOrderUnitParams();
          l_fundOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
          l_fundOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
          l_fundOrderUnitParams.setProductId(l_fundProductParams.getProductId());
          l_fundOrderUnitParams.setQuantityType(QuantityTypeEnum.AMOUNT);
          l_fundOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
          l_fundOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
          l_fundOrderUnitParams.setOrderType(OrderTypeEnum.MF_SELL);
          TestDBUtility.insertWithDel(l_fundOrderUnitParams);
          
          //WEB3MutualFundTradingTimeManagement
          WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
          WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
          //Asset
          AssetParams l_assetParams = TestDBUtility.getAssetRow();
          l_assetParams.setAccountId(l_subAccountParams.getAccountId());
          l_assetParams.setAssetId(123l);
          TestDBUtility.insertWithDel(l_assetParams);
          
          //Trader
          TraderParams l_traderParams = TestDBUtility.getTraderRow();
          TestDBUtility.insertWithDel(l_traderParams);
          TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
          l_impl.submitSell(l_request);
          fail();
      }
      catch(WEB3BusinessLayerException l_exc)
      {
          log.exiting(STR_METHOD_NAME);
          l_exc.printStackTrace();
          assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_exc.getErrorInfo());
          log.info(STR_METHOD_NAME + "----------------->ok");
      }
      catch(Exception l_exc)
      {
          log.exiting(STR_METHOD_NAME);
          l_exc.printStackTrace();
          fail();
      }
      log.exiting(STR_METHOD_NAME);
  }
    
    public MutualFundOrderUnitParams getMutualFundOrderUnitParams()
    {
        MutualFundOrderUnitParams l_params = new MutualFundOrderUnitParams();
        //      �����P�ʂh�c    order_unit_id     NUMBER  18 NotNull 
        l_params.setOrderUnitId(1001);
        //      �����h�c    account_id     NUMBER  18 NotNull  
        l_params.setAccountId(101001010010L);
        //      �⏕�����h�c    sub_account_id     NUMBER  18 NotNull  
        l_params.setSubAccountId(10100101001007L);
        //      ���X�h�c    branch_id     NUMBER  18 NotNull  
        l_params.setBranchId(33381);
        //      ����҂h�c    trader_id     NUMBER  18 NULL  
        //      �����h�c    order_id     NUMBER  18 NotNull  
        l_params.setOrderId(1001);
        //      �������    order_type     NUMBER  6 NotNull  
        l_params.setOrderType(OrderTypeEnum.ASSET_IN);
        //      �����J�e�S��    order_categ     NUMBER  6 NotNull  
        l_params.setOrderCateg(OrderCategEnum.ASSET);
        //      ���������ŏI�ʔ�    last_order_action_serial_no     NUMBER  8 NotNull  
        l_params.setLastOrderActionSerialNo(1);
        //      �����^�C�v    product_type     NUMBER  6 NotNull  
        l_params.setProductType(ProductTypeEnum.MUTUAL_FUND);
        //      �s��h�c    market_id     NUMBER  18 NULL  
        //      ��������    quantity     DECIMAL  18 12 6 NotNull  
        l_params.setQuantity(123d);
        Timestamp l_timestamp = GtlUtils.getSystemTimestamp();
        //      ��n��    delivery_date     DATE   NotNull  
        l_params.setDeliveryDate(l_timestamp);
        //      �����������t    expiration_date     DATE   NULL  
        //      �s�ꂩ��m�F�ς݂̐���    confirmed_quantity     DECIMAL  18 12 6 NULL  
        //      ��萔��    executed_quantity     DECIMAL  18 12 6 NULL  
        //      ���v�����z    executed_amount     DECIMAL  18 12 6 NULL  
        //      �������    order_status     NUMBER  6 NotNull 
        l_params.setOrderStatus(OrderStatusEnum.ACCEPTED);
        //      �����L�����    order_open_status     NUMBER  6 NotNull  
        l_params.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        //      �����敪    expiration_status     NUMBER  6 NotNull  
        l_params.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        //      �ŋ敪    tax_type     NUMBER  6 NotNull  
        l_params.setTaxType(TaxTypeEnum.NORMAL);
        //      ������    biz_date     VARCHAR2  8 NotNull  
        l_params.setBizDate("20010201");
        //      �����h�c    product_id     NUMBER  18 NotNull  
        l_params.setProductId(1006169090018L);
        //      �������ʃ^�C�v    quantity_type     NUMBER  6 NotNull  
        l_params.setQuantityType(QuantityTypeEnum.AMOUNT);
        //      ���񒍕��̒����`���l��    order_chanel     VARCHAR2  1 NULL  
        //      �󒍓���    received_date_time     DATE   NULL  
        //      ���҃R�[�h�iSONAR�j    sonar_trader_code     VARCHAR2  5 NULL  
        //      ���ʃR�[�h    order_request_number     VARCHAR2  9 NULL  
        //      �v�Z����z    calc_constant_value     DECIMAL  18 12 6 NULL  
        //      �v�Z����z�i�抷��j    swt_calc_constant_value     DECIMAL  18 12 6 NULL  
        //      ����z�K�p��    constant_value_app_date     DATE   NULL  
        //      �T�Z��n���    estimated_price     DECIMAL  18 12 6 NULL  
        //      �T�Z��������    estimate_dealing_qty     DECIMAL  18 12 6 NULL  
        //      �T�Z���t�����i�抷��j    swt_estimate_dealing_qty     DECIMAL  18 12 6 NULL  
        //      �ŋ敪�i�抷��j    swt_tax_type     NUMBER  6 NULL  
        //      �����R�[�h�i�抷��j    swt_product_code     VARCHAR2  10 NULL  
        //      ��n���@@    payment_method     VARCHAR2  1 NULL  
        //      ���M�^�C�v    fund_type     NUMBER  6 NULL  
        //      ���M���敪    fund_sell_div     VARCHAR2  1 NULL  
        //      ����    exec_date     DATE   NULL  
        //      �����    exec_status     VARCHAR2  1 NULL  
        //      ���ϋ敪    settlement_div     VARCHAR2  1 NULL  
        //      ���萔���敪    no_contract_commission_div     VARCHAR2  1 NULL  
        //      �����敪    request_div     VARCHAR2  1 NULL  
        //      �����o�H�敪    order_root_div     VARCHAR2  1 NULL  
        //      �����G���[���R�R�[�h    error_reason_code     VARCHAR2  4 NULL  
        //      �쐬���t    created_timestamp     DATE   NotNull  
        l_params.setCreatedTimestamp(l_timestamp);
        //      �X�V���t    last_updated_timestamp     DATE   NotNull 
        l_params.setLastUpdatedTimestamp(l_timestamp);
        //      ������    payment_date     DATE   NULL  
        //      ���򒥎��S����    withholding_tax_restriction     DECIMAL  18 12 6 NULL  
        //      �o���������ʃR�[�h    payment_order_req_number     VARCHAR2  9 NULL  
        //      CPU No.    cpu_no     VARCHAR2  5 NULL  
        //      �抷������    swt_exec_date     DATE   NULL  
        return l_params;
    }
}
@

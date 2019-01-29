head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.11.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualSellInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SystemHandlingDivDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.ExchangeRateParams;
import webbroker3.gentrade.data.FrgnMmfExchangeRateParams;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundTradingTimeManagementForMock;
import webbroker3.mf.message.WEB3MutualSellInputRequest;
import webbroker3.mf.message.WEB3MutualSellInputResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualSellInputServiceImplTest extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSellInputServiceImplTest.class);
    WEB3MutualSellInputServiceImpl l_imple = null;
    
    public WEB3MutualSellInputServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        l_imple = new WEB3MutualSellInputServiceImpl();
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.mf.service.delegate.stdimpls.WEB3MutualSellInputServiceImpl.execute(WEB3GenRequest)'
     */
    public void testExecuteT01()
    {
        final String STR_METHOD_NAME = "testExecuteT01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Date l_date = Calendar.getInstance().getTime();
            WEB3MutualSellInputRequest l_request = new WEB3MutualSellInputRequest();
            l_request.id = "123";
            l_request.sellBuyDiv = null;

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

            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setProductCode("0");
            l_fundProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
            l_fundProductParams.setSellSwtStartDate(l_date);
            l_fundProductParams.setSellSwtEndDate(l_date);
            l_fundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_fundProductParams.setCurrencyCode("T0");
            
            TestDBUtility.insertWithDel(l_fundProductParams);
            
            //asset
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setAssetId(123l);
            l_assetParams.setProductId(l_productParams.product_id);
            l_assetParams.setTaxType(TaxTypeEnum.UNDEFINED);
            TestDBUtility.insertWithDel(l_assetParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(l_date);
            
            ProcessingResult processingResult =
                ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_validationResult =
                new OrderValidationResult(processingResult);
            
            //WEB3GentradeOrderValidator
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", 
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_validationResult);

            //WEB3MutualFundOrderManagerReusableValidationsCheck
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck", 
                "validateEmergencyStop",
                new Class[] {WEB3MutualFundProduct.class, String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck", 
                "validateFrgnMmfDoubleOrder",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},
                null);
            
            //WEB3MutualFundProductManager 
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
            
            //institution
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //ExchangeRateRow
            ExchangeRateParams l_exchangeRateParams = new ExchangeRateParams();
            l_exchangeRateParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_exchangeRateParams.setCurrencyCode(l_fundProductParams.getCurrencyCode());
            l_exchangeRateParams.setExecTimestamp(l_date);
            l_exchangeRateParams.setTtBuyingRate(10d);
            l_exchangeRateParams.setTtSellingRate(10d);
            l_exchangeRateParams.setExchangeCalcUnit(10d);
            l_exchangeRateParams.setCreatedTimestamp(l_date);
            l_exchangeRateParams.setLastUpdatedTimestamp(l_date);
            TestDBUtility.insertWithDel(l_exchangeRateParams);
            
            //WEB3MutualFundPositionManagerForMock
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager", 
                "calcMarketValue",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
                new Double(123d));

            //MutualFundOrderUnitParams
            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            l_fundOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_fundOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_fundOrderUnitParams.setProductId(l_fundProductParams.getProductId());
            l_fundOrderUnitParams.setBizDate("20070225");
            l_fundOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_fundOrderUnitParams);
            
            //FrgnMmfExchangeRateParams
            FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = new FrgnMmfExchangeRateParams();
            l_frgnMmfExchangeRateParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_frgnMmfExchangeRateParams.setCurrencyCode(l_fundProductParams.getCurrencyCode());
            l_frgnMmfExchangeRateParams.setCreatedTimestamp(l_date);
            l_frgnMmfExchangeRateParams.setLastUpdatedTimestamp(l_date);
            TestDBUtility.insertWithDel(l_frgnMmfExchangeRateParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3MutualSellInputResponse l_response =
                (WEB3MutualSellInputResponse)l_imple.execute(l_request);
            assertEquals("2", l_response.taxType);
            assertNull(l_response.sellBuyDivList);
            assertNull(l_response.yenConstantValue);
            assertNull(l_response.referenceRate);
            assertNull(l_response.referenceRateFixedDay);
            
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

    public void testExecuteT02()
    {
        final String STR_METHOD_NAME = "testExecuteT02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Date l_date = Calendar.getInstance().getTime();
            WEB3MutualSellInputRequest l_request = new WEB3MutualSellInputRequest();
            l_request.id = "123";
            l_request.sellBuyDiv = null;

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

            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setProductCode("0");
            l_fundProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
            l_fundProductParams.setSellSwtStartDate(l_date);
            l_fundProductParams.setSellSwtEndDate(l_date);
            l_fundProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
            l_fundProductParams.setCurrencyCode("00");
            
            TestDBUtility.insertWithDel(l_fundProductParams);
            
            //asset
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setAssetId(123l);
            l_assetParams.setProductId(l_productParams.product_id);
            l_assetParams.setTaxType(TaxTypeEnum.UNDEFINED);
            TestDBUtility.insertWithDel(l_assetParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(l_date);
            
            ProcessingResult processingResult =
                ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_validationResult =
                new OrderValidationResult(processingResult);
            
            //WEB3GentradeOrderValidator
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", 
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_validationResult);

            //WEB3MutualFundOrderManagerReusableValidationsCheck
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck", 
                "validateEmergencyStop",
                new Class[] {WEB3MutualFundProduct.class, String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck", 
                "validateFrgnMmfDoubleOrder",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},
                null);
            
            //WEB3MutualFundProductManager 
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
            
            //institution
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //ExchangeRateRow
            ExchangeRateParams l_exchangeRateParams = new ExchangeRateParams();
            l_exchangeRateParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_exchangeRateParams.setCurrencyCode(l_fundProductParams.getCurrencyCode());
            l_exchangeRateParams.setExecTimestamp(l_date);
            l_exchangeRateParams.setTtBuyingRate(1d);
            l_exchangeRateParams.setTtSellingRate(3d);
            l_exchangeRateParams.setExchangeCalcUnit(3d);
            l_exchangeRateParams.setCreatedTimestamp(l_date);
            l_exchangeRateParams.setLastUpdatedTimestamp(l_date);
            TestDBUtility.insertWithDel(l_exchangeRateParams);
            
            //WEB3MutualFundPositionManagerForMock
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager", 
                "calcMarketValue",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
                new Double(123d));

            //MutualFundOrderUnitParams
            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            l_fundOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_fundOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_fundOrderUnitParams.setProductId(l_fundProductParams.getProductId());
            l_fundOrderUnitParams.setBizDate("20070225");
            l_fundOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_fundOrderUnitParams);
            
            //FrgnMmfExchangeRateParams
            FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = new FrgnMmfExchangeRateParams();
            l_frgnMmfExchangeRateParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_frgnMmfExchangeRateParams.setCurrencyCode(l_fundProductParams.getCurrencyCode());
            l_frgnMmfExchangeRateParams.setCreatedTimestamp(l_date);
            l_frgnMmfExchangeRateParams.setLastUpdatedTimestamp(l_date);
            TestDBUtility.insertWithDel(l_frgnMmfExchangeRateParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3MutualSellInputResponse l_response =
                (WEB3MutualSellInputResponse)l_imple.execute(l_request);
            assertEquals("2", l_response.taxType);
            assertEquals("0", l_response.sellBuyDivList[0]);
            assertNotNull(l_response.yenConstantValue);
            assertEquals("0.33", l_response.referenceRate);
            assertEquals(WEB3DateUtility.formatDate(l_date, "yyMMdd"), WEB3DateUtility.formatDate(l_response.referenceRateFixedDay, "yyMMdd"));
            
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

    public void testExecuteT03()
    {
        final String STR_METHOD_NAME = "testExecuteT03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Date l_date = Calendar.getInstance().getTime();
            WEB3MutualSellInputRequest l_request = new WEB3MutualSellInputRequest();
            l_request.id = "123";
            l_request.sellBuyDiv = null;

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

            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setProductCode("0");
            l_fundProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
            l_fundProductParams.setSellSwtStartDate(l_date);
            l_fundProductParams.setSellSwtEndDate(l_date);
            l_fundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_fundProductParams.setCurrencyCode("00");
            
            TestDBUtility.insertWithDel(l_fundProductParams);
            
            //asset
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setAssetId(123l);
            l_assetParams.setProductId(l_productParams.product_id);
            l_assetParams.setTaxType(TaxTypeEnum.UNDEFINED);
            TestDBUtility.insertWithDel(l_assetParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(l_date);
            
            ProcessingResult processingResult =
                ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_validationResult =
                new OrderValidationResult(processingResult);
            
            //WEB3GentradeOrderValidator
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", 
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_validationResult);

            //WEB3MutualFundOrderManagerReusableValidationsCheck
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck", 
                "validateEmergencyStop",
                new Class[] {WEB3MutualFundProduct.class, String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck", 
                "validateFrgnMmfDoubleOrder",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},
                null);
            
            //WEB3MutualFundProductManager 
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
            
            //institution
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //ExchangeRateRow
            ExchangeRateParams l_exchangeRateParams = new ExchangeRateParams();
            l_exchangeRateParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_exchangeRateParams.setCurrencyCode(l_fundProductParams.getCurrencyCode());
            l_exchangeRateParams.setExecTimestamp(l_date);
            l_exchangeRateParams.setTtBuyingRate(1d);
            l_exchangeRateParams.setTtSellingRate(3d);
            l_exchangeRateParams.setExchangeCalcUnit(3d);
            l_exchangeRateParams.setCreatedTimestamp(l_date);
            l_exchangeRateParams.setLastUpdatedTimestamp(l_date);
            TestDBUtility.insertWithDel(l_exchangeRateParams);
            
            //WEB3MutualFundPositionManagerForMock
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager", 
                "calcMarketValue",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
                new Double(123d));

            //MutualFundOrderUnitParams
            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            l_fundOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_fundOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_fundOrderUnitParams.setProductId(l_fundProductParams.getProductId());
            l_fundOrderUnitParams.setBizDate("20070225");
            l_fundOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_fundOrderUnitParams);
            
            //FrgnMmfExchangeRateParams
            FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = new FrgnMmfExchangeRateParams();
            l_frgnMmfExchangeRateParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_frgnMmfExchangeRateParams.setCurrencyCode(l_fundProductParams.getCurrencyCode());
            l_frgnMmfExchangeRateParams.setCreatedTimestamp(l_date);
            l_frgnMmfExchangeRateParams.setLastUpdatedTimestamp(l_date);
            l_frgnMmfExchangeRateParams.setTtBuyingRate(10.667d);
            l_frgnMmfExchangeRateParams.setExecTimestamp(l_date);
            TestDBUtility.insertWithDel(l_frgnMmfExchangeRateParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3MutualSellInputResponse l_response =
                (WEB3MutualSellInputResponse)l_imple.execute(l_request);
            assertEquals("2", l_response.taxType);
            assertNull(l_response.sellBuyDivList);
            assertNull(l_response.yenConstantValue);
            assertEquals("10.67", l_response.referenceRate);
            assertEquals(WEB3DateUtility.formatDate(l_date, "yyMMdd"), WEB3DateUtility.formatDate(l_response.referenceRateFixedDay, "yyMMdd"));
            
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
    
    public void testExecutecase0001()
    {
        final String STR_METHOD_NAME = "testExecutecase0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Date l_date = Calendar.getInstance().getTime();
            WEB3MutualSellInputRequest l_request = new WEB3MutualSellInputRequest();
            l_request.id = "123";
            l_request.sellBuyDiv = null;

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

            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setProductCode("0");
            l_fundProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
            l_fundProductParams.setSellSwtStartDate(l_date);
            l_fundProductParams.setSellSwtEndDate(l_date);
            l_fundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_fundProductParams.setCurrencyCode("00");
            
            TestDBUtility.insertWithDel(l_fundProductParams);
            
            //asset
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setAssetId(123l);
            l_assetParams.setProductId(l_productParams.product_id);
            l_assetParams.setTaxType(TaxTypeEnum.UNDEFINED);
            TestDBUtility.insertWithDel(l_assetParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(l_date);
            
            ProcessingResult processingResult =
                ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_validationResult =
                new OrderValidationResult(processingResult);
            
            //WEB3GentradeOrderValidator
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", 
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_validationResult);

            //WEB3MutualFundOrderManagerReusableValidationsCheck
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck", 
                "validateEmergencyStop",
                new Class[] {WEB3MutualFundProduct.class, String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck", 
                "validateFrgnMmfDoubleOrder",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},
                null);
            
            //WEB3MutualFundProductManager 
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
            
            //institution
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //ExchangeRateRow
            ExchangeRateParams l_exchangeRateParams = new ExchangeRateParams();
            l_exchangeRateParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_exchangeRateParams.setCurrencyCode(l_fundProductParams.getCurrencyCode());
            l_exchangeRateParams.setExecTimestamp(l_date);
            l_exchangeRateParams.setTtBuyingRate(1d);
            l_exchangeRateParams.setTtSellingRate(3d);
            l_exchangeRateParams.setExchangeCalcUnit(3d);
            l_exchangeRateParams.setCreatedTimestamp(l_date);
            l_exchangeRateParams.setLastUpdatedTimestamp(l_date);
            TestDBUtility.insertWithDel(l_exchangeRateParams);
            
            //WEB3MutualFundPositionManagerForMock
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager", 
                "calcMarketValue",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
                new Double(123d));

            //MutualFundOrderUnitParams
            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            l_fundOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_fundOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_fundOrderUnitParams.setProductId(l_fundProductParams.getProductId());
            l_fundOrderUnitParams.setBizDate("20070225");
            l_fundOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_fundOrderUnitParams);
            
            //FrgnMmfExchangeRateParams
            FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = new FrgnMmfExchangeRateParams();
            l_frgnMmfExchangeRateParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_frgnMmfExchangeRateParams.setCurrencyCode(l_fundProductParams.getCurrencyCode());
            l_frgnMmfExchangeRateParams.setCreatedTimestamp(l_date);
            l_frgnMmfExchangeRateParams.setLastUpdatedTimestamp(l_date);
            l_frgnMmfExchangeRateParams.setTtBuyingRate(10.667d);
            l_frgnMmfExchangeRateParams.setExecTimestamp(l_date);
            TestDBUtility.insertWithDel(l_frgnMmfExchangeRateParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3MutualSellInputResponse l_response =
                (WEB3MutualSellInputResponse)l_imple.execute(l_request);
            assertNull(l_response.sellBuyDivList);
            
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
    
    public void testExecutecase0002()
    {
        final String STR_METHOD_NAME = "testExecutecase0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Date l_date = Calendar.getInstance().getTime();
            WEB3MutualSellInputRequest l_request = new WEB3MutualSellInputRequest();
            l_request.id = "123";
            l_request.sellBuyDiv = null;

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

            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setProductCode("0");
            l_fundProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
            l_fundProductParams.setSellSwtStartDate(l_date);
            l_fundProductParams.setSellSwtEndDate(l_date);
            l_fundProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
            l_fundProductParams.setCurrencyCode("00");
            
            TestDBUtility.insertWithDel(l_fundProductParams);
            
            //asset
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setAssetId(123l);
            l_assetParams.setProductId(l_productParams.product_id);
            l_assetParams.setTaxType(TaxTypeEnum.UNDEFINED);
            TestDBUtility.insertWithDel(l_assetParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(l_date);
            
            ProcessingResult processingResult =
                ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_validationResult =
                new OrderValidationResult(processingResult);
            
            //WEB3GentradeOrderValidator
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", 
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_validationResult);

            //WEB3MutualFundOrderManagerReusableValidationsCheck
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck", 
                "validateEmergencyStop",
                new Class[] {WEB3MutualFundProduct.class, String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck", 
                "validateFrgnMmfDoubleOrder",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},
                null);
            
            //WEB3MutualFundProductManager 
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
            
            //institution
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //ExchangeRateRow
            ExchangeRateParams l_exchangeRateParams = new ExchangeRateParams();
            l_exchangeRateParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_exchangeRateParams.setCurrencyCode(l_fundProductParams.getCurrencyCode());
            l_exchangeRateParams.setExecTimestamp(l_date);
            l_exchangeRateParams.setTtBuyingRate(1d);
            l_exchangeRateParams.setTtSellingRate(3d);
            l_exchangeRateParams.setExchangeCalcUnit(3d);
            l_exchangeRateParams.setCreatedTimestamp(l_date);
            l_exchangeRateParams.setLastUpdatedTimestamp(l_date);
            TestDBUtility.insertWithDel(l_exchangeRateParams);
            
            //WEB3MutualFundPositionManagerForMock
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager", 
                "calcMarketValue",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
                new Double(123d));

            //MutualFundOrderUnitParams
            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            l_fundOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_fundOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_fundOrderUnitParams.setProductId(l_fundProductParams.getProductId());
            l_fundOrderUnitParams.setBizDate("20070225");
            l_fundOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_fundOrderUnitParams);
            
            //FrgnMmfExchangeRateParams
            FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = new FrgnMmfExchangeRateParams();
            l_frgnMmfExchangeRateParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_frgnMmfExchangeRateParams.setCurrencyCode(l_fundProductParams.getCurrencyCode());
            l_frgnMmfExchangeRateParams.setCreatedTimestamp(l_date);
            l_frgnMmfExchangeRateParams.setLastUpdatedTimestamp(l_date);
            l_frgnMmfExchangeRateParams.setTtBuyingRate(10.667d);
            l_frgnMmfExchangeRateParams.setExecTimestamp(l_date);
            TestDBUtility.insertWithDel(l_frgnMmfExchangeRateParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3MutualSellInputResponse l_response =
                (WEB3MutualSellInputResponse)l_imple.execute(l_request);
            assertEquals("0", l_response.sellBuyDivList[0]);
            
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
    
    public void testExecutecase0003()
    {
        final String STR_METHOD_NAME = "testExecutecase0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Date l_date = Calendar.getInstance().getTime();
            WEB3MutualSellInputRequest l_request = new WEB3MutualSellInputRequest();
            l_request.id = "123";
            l_request.sellBuyDiv = null;

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

            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setProductCode("0");
            l_fundProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
            l_fundProductParams.setSellSwtStartDate(l_date);
            l_fundProductParams.setSellSwtEndDate(l_date);
            l_fundProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
            l_fundProductParams.setCurrencyCode("00");
            
            TestDBUtility.insertWithDel(l_fundProductParams);
            
            //asset
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setAssetId(123l);
            l_assetParams.setProductId(l_productParams.product_id);
            l_assetParams.setTaxType(TaxTypeEnum.UNDEFINED);
            TestDBUtility.insertWithDel(l_assetParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(l_date);

            ProcessingResult processingResult =
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80002);
            
            OrderValidationResult l_validationResult =
                new OrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", 
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_validationResult);

            //institution
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //ExchangeRateRow
            ExchangeRateParams l_exchangeRateParams = new ExchangeRateParams();
            l_exchangeRateParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_exchangeRateParams.setCurrencyCode(l_fundProductParams.getCurrencyCode());
            l_exchangeRateParams.setExecTimestamp(l_date);
            l_exchangeRateParams.setTtBuyingRate(1d);
            l_exchangeRateParams.setTtSellingRate(3d);
            l_exchangeRateParams.setExchangeCalcUnit(3d);
            l_exchangeRateParams.setCreatedTimestamp(l_date);
            l_exchangeRateParams.setLastUpdatedTimestamp(l_date);
            TestDBUtility.insertWithDel(l_exchangeRateParams);

            //MutualFundOrderUnitParams
            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            l_fundOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_fundOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_fundOrderUnitParams.setProductId(l_fundProductParams.getProductId());
            l_fundOrderUnitParams.setBizDate("20070225");
            l_fundOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_fundOrderUnitParams);
            
            //FrgnMmfExchangeRateParams
            FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = new FrgnMmfExchangeRateParams();
            l_frgnMmfExchangeRateParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_frgnMmfExchangeRateParams.setCurrencyCode(l_fundProductParams.getCurrencyCode());
            l_frgnMmfExchangeRateParams.setCreatedTimestamp(l_date);
            l_frgnMmfExchangeRateParams.setLastUpdatedTimestamp(l_date);
            l_frgnMmfExchangeRateParams.setTtBuyingRate(10.667d);
            l_frgnMmfExchangeRateParams.setExecTimestamp(l_date);
            TestDBUtility.insertWithDel(l_frgnMmfExchangeRateParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            l_imple.execute(l_request);
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecutecase0004()
    {
        final String STR_METHOD_NAME = "testExecutecase0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Date l_date = Calendar.getInstance().getTime();
            WEB3MutualSellInputRequest l_request = new WEB3MutualSellInputRequest();
            l_request.id = "123";
            l_request.sellBuyDiv = null;

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

            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setProductCode("0");
            l_fundProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
            l_fundProductParams.setSellSwtStartDate(l_date);
            l_fundProductParams.setSellSwtEndDate(l_date);
            l_fundProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
            l_fundProductParams.setCurrencyCode("00");
            l_fundProductParams.setFrgnSellMinAmt(200L);
            l_fundProductParams.setFrgnSellUnitAmt(300L);
            TestDBUtility.insertWithDel(l_fundProductParams);
            
            //asset
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setAssetId(123l);
            l_assetParams.setProductId(l_productParams.product_id);
            l_assetParams.setTaxType(TaxTypeEnum.UNDEFINED);
            TestDBUtility.insertWithDel(l_assetParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(l_date);
            
            ProcessingResult processingResult =
                ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_validationResult =
                new OrderValidationResult(processingResult);
            
            //WEB3GentradeOrderValidator
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", 
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_validationResult);

            //WEB3MutualFundOrderManagerReusableValidationsCheck
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck", 
                "validateEmergencyStop",
                new Class[] {WEB3MutualFundProduct.class, String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck", 
                "validateFrgnMmfDoubleOrder",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},
                null);
            
            //WEB3MutualFundProductManager 
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
            
            //institution
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //ExchangeRateRow
            ExchangeRateParams l_exchangeRateParams = new ExchangeRateParams();
            l_exchangeRateParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_exchangeRateParams.setCurrencyCode(l_fundProductParams.getCurrencyCode());
            l_exchangeRateParams.setExecTimestamp(l_date);
            l_exchangeRateParams.setTtBuyingRate(1d);
            l_exchangeRateParams.setTtSellingRate(3d);
            l_exchangeRateParams.setExchangeCalcUnit(3d);
            l_exchangeRateParams.setCreatedTimestamp(l_date);
            l_exchangeRateParams.setLastUpdatedTimestamp(l_date);
            TestDBUtility.insertWithDel(l_exchangeRateParams);
            
            //WEB3MutualFundPositionManagerForMock
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager", 
                "calcMarketValue",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
                new Double(123d));

            //MutualFundOrderUnitParams
            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            l_fundOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_fundOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_fundOrderUnitParams.setProductId(l_fundProductParams.getProductId());
            l_fundOrderUnitParams.setBizDate("20070225");
            l_fundOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_fundOrderUnitParams);
            
            //FrgnMmfExchangeRateParams
            FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = new FrgnMmfExchangeRateParams();
            l_frgnMmfExchangeRateParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_frgnMmfExchangeRateParams.setCurrencyCode(l_fundProductParams.getCurrencyCode());
            l_frgnMmfExchangeRateParams.setCreatedTimestamp(l_date);
            l_frgnMmfExchangeRateParams.setLastUpdatedTimestamp(l_date);
            l_frgnMmfExchangeRateParams.setTtBuyingRate(10.667d);
            l_frgnMmfExchangeRateParams.setExecTimestamp(l_date);
            TestDBUtility.insertWithDel(l_frgnMmfExchangeRateParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3MutualSellInputResponse l_response =
                (WEB3MutualSellInputResponse)l_imple.execute(l_request);
            assertEquals("300", l_response.sellFrgnUnitAmt);
            assertEquals("200", l_response.sellFrgnMinAmt);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecutecase0005()
    {
        final String STR_METHOD_NAME = "testExecutecase0005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Date l_date = Calendar.getInstance().getTime();
            WEB3MutualSellInputRequest l_request = new WEB3MutualSellInputRequest();
            l_request.id = "123";
            l_request.sellBuyDiv = null;

            WEB3GentradeProspectusResult l_gentradeProspectusResult =
                new WEB3GentradeProspectusResult();
            l_gentradeProspectusResult.checkResult = "1";
            l_gentradeProspectusResult.hashValue = "3";
            l_gentradeProspectusResult.url = "aa";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateProspectus",
                new Class[] {String.class, String.class},
                l_gentradeProspectusResult);

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

            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setProductCode("0");
            l_fundProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
            l_fundProductParams.setSellSwtStartDate(l_date);
            l_fundProductParams.setSellSwtEndDate(l_date);
            l_fundProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
            l_fundProductParams.setCurrencyCode("00");
            l_fundProductParams.setFrgnSellMinAmt(200L);
            l_fundProductParams.setFrgnSellUnitAmt(300L);
            TestDBUtility.insertWithDel(l_fundProductParams);
            
            //asset
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setAssetId(123l);
            l_assetParams.setProductId(l_productParams.product_id);
            l_assetParams.setTaxType(TaxTypeEnum.UNDEFINED);
            TestDBUtility.insertWithDel(l_assetParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(l_date);
            
            ProcessingResult processingResult =
                ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_validationResult =
                new OrderValidationResult(processingResult);
            
            //WEB3GentradeOrderValidator
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", 
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_validationResult);

            //WEB3MutualFundOrderManagerReusableValidationsCheck
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck", 
                "validateEmergencyStop",
                new Class[] {WEB3MutualFundProduct.class, String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck", 
                "validateFrgnMmfDoubleOrder",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},
                null);
            
            //WEB3MutualFundProductManager 
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
            
            //institution
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //ExchangeRateRow
            ExchangeRateParams l_exchangeRateParams = new ExchangeRateParams();
            l_exchangeRateParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_exchangeRateParams.setCurrencyCode(l_fundProductParams.getCurrencyCode());
            l_exchangeRateParams.setExecTimestamp(l_date);
            l_exchangeRateParams.setTtBuyingRate(1d);
            l_exchangeRateParams.setTtSellingRate(3d);
            l_exchangeRateParams.setExchangeCalcUnit(3d);
            l_exchangeRateParams.setCreatedTimestamp(l_date);
            l_exchangeRateParams.setLastUpdatedTimestamp(l_date);
            TestDBUtility.insertWithDel(l_exchangeRateParams);
            
            //WEB3MutualFundPositionManagerForMock
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager", 
                "calcMarketValue",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
                new Double(123d));

            //MutualFundOrderUnitParams
            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            l_fundOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_fundOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_fundOrderUnitParams.setProductId(l_fundProductParams.getProductId());
            l_fundOrderUnitParams.setBizDate("20070225");
            l_fundOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_fundOrderUnitParams);
            
            //FrgnMmfExchangeRateParams
            FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = new FrgnMmfExchangeRateParams();
            l_frgnMmfExchangeRateParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_frgnMmfExchangeRateParams.setCurrencyCode(l_fundProductParams.getCurrencyCode());
            l_frgnMmfExchangeRateParams.setCreatedTimestamp(l_date);
            l_frgnMmfExchangeRateParams.setLastUpdatedTimestamp(l_date);
            l_frgnMmfExchangeRateParams.setTtBuyingRate(10.667d);
            l_frgnMmfExchangeRateParams.setExecTimestamp(l_date);
            TestDBUtility.insertWithDel(l_frgnMmfExchangeRateParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3MutualSellInputResponse l_response =
                (WEB3MutualSellInputResponse)l_imple.execute(l_request);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecutecase0006()
    {
        final String STR_METHOD_NAME = "testExecutecase0006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Date l_date = Calendar.getInstance().getTime();
            WEB3MutualSellInputRequest l_request = new WEB3MutualSellInputRequest();
            l_request.id = "123";
            l_request.sellBuyDiv = null;

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

            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setProductCode("0");
            l_fundProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
            l_fundProductParams.setSellSwtStartDate(l_date);
            l_fundProductParams.setSellSwtEndDate(l_date);
            l_fundProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
            l_fundProductParams.setCurrencyCode("00");
            l_fundProductParams.setFrgnSellMinAmt(200L);
            l_fundProductParams.setFrgnSellUnitAmt(300L);
            TestDBUtility.insertWithDel(l_fundProductParams);
            
            //asset
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setAssetId(123l);
            l_assetParams.setProductId(l_productParams.product_id);
            l_assetParams.setTaxType(TaxTypeEnum.UNDEFINED);
            TestDBUtility.insertWithDel(l_assetParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(l_date);
            
            ProcessingResult processingResult =
                ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_validationResult =
                new OrderValidationResult(processingResult);
            
            //WEB3GentradeOrderValidator
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", 
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_validationResult);

            //WEB3MutualFundOrderManagerReusableValidationsCheck
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck", 
                "validateEmergencyStop",
                new Class[] {WEB3MutualFundProduct.class, String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck", 
                "validateFrgnMmfDoubleOrder",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},
                null);
            
            //WEB3MutualFundProductManager 
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
            
            //institution
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //ExchangeRateRow
            ExchangeRateParams l_exchangeRateParams = new ExchangeRateParams();
            l_exchangeRateParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_exchangeRateParams.setCurrencyCode(l_fundProductParams.getCurrencyCode());
            l_exchangeRateParams.setExecTimestamp(l_date);
            l_exchangeRateParams.setTtBuyingRate(1d);
            l_exchangeRateParams.setTtSellingRate(3d);
            l_exchangeRateParams.setExchangeCalcUnit(3d);
            l_exchangeRateParams.setCreatedTimestamp(l_date);
            l_exchangeRateParams.setLastUpdatedTimestamp(l_date);
            TestDBUtility.insertWithDel(l_exchangeRateParams);
            
            //WEB3MutualFundPositionManagerForMock
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager", 
                "calcMarketValue",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
                new Double(123d));

            //MutualFundOrderUnitParams
            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            l_fundOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_fundOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_fundOrderUnitParams.setProductId(l_fundProductParams.getProductId());
            l_fundOrderUnitParams.setBizDate("20070225");
            l_fundOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_fundOrderUnitParams);
            
            //FrgnMmfExchangeRateParams
            FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = new FrgnMmfExchangeRateParams();
            l_frgnMmfExchangeRateParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_frgnMmfExchangeRateParams.setCurrencyCode(l_fundProductParams.getCurrencyCode());
            l_frgnMmfExchangeRateParams.setCreatedTimestamp(l_date);
            l_frgnMmfExchangeRateParams.setLastUpdatedTimestamp(l_date);
            l_frgnMmfExchangeRateParams.setTtBuyingRate(10.667d);
            l_frgnMmfExchangeRateParams.setExecTimestamp(l_date);
            TestDBUtility.insertWithDel(l_frgnMmfExchangeRateParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3MutualSellInputResponse l_response =
                (WEB3MutualSellInputResponse)l_imple.execute(l_request);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecutecase0007()
    {
        final String STR_METHOD_NAME = "testExecutecase0007()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Date l_date = Calendar.getInstance().getTime();
            WEB3MutualSellInputRequest l_request = new WEB3MutualSellInputRequest();
            l_request.id = "123";
            l_request.sellBuyDiv = null;

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

            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setProductCode("0");
            l_fundProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
            l_fundProductParams.setSellSwtStartDate(l_date);
            l_fundProductParams.setSellSwtEndDate(l_date);
            l_fundProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
            l_fundProductParams.setCurrencyCode("00");
            l_fundProductParams.setFrgnSellMinAmt(200L);
            l_fundProductParams.setFrgnSellUnitAmt(300L);
            TestDBUtility.insertWithDel(l_fundProductParams);
            
            //asset
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setAssetId(123l);
            l_assetParams.setProductId(l_productParams.product_id);
            l_assetParams.setTaxType(TaxTypeEnum.UNDEFINED);
            TestDBUtility.insertWithDel(l_assetParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(l_date);
            
            ProcessingResult processingResult =
                ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_validationResult =
                new OrderValidationResult(processingResult);
            
            //WEB3GentradeOrderValidator
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", 
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_validationResult);

            //WEB3MutualFundOrderManagerReusableValidationsCheck
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck", 
                "validateEmergencyStop",
                new Class[] {WEB3MutualFundProduct.class, String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck", 
                "validateFrgnMmfDoubleOrder",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},
                null);
            
            //WEB3MutualFundProductManager 
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
            
            //institution
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //ExchangeRateRow
            ExchangeRateParams l_exchangeRateParams = new ExchangeRateParams();
            l_exchangeRateParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_exchangeRateParams.setCurrencyCode(l_fundProductParams.getCurrencyCode());
            l_exchangeRateParams.setExecTimestamp(l_date);
            l_exchangeRateParams.setTtBuyingRate(1d);
            l_exchangeRateParams.setTtSellingRate(3d);
            l_exchangeRateParams.setExchangeCalcUnit(3d);
            l_exchangeRateParams.setCreatedTimestamp(l_date);
            l_exchangeRateParams.setLastUpdatedTimestamp(l_date);
            TestDBUtility.insertWithDel(l_exchangeRateParams);
            
            //WEB3MutualFundPositionManagerForMock
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager", 
                "calcMarketValue",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
                new Double(123d));

            //MutualFundOrderUnitParams
            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            l_fundOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_fundOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_fundOrderUnitParams.setProductId(l_fundProductParams.getProductId());
            l_fundOrderUnitParams.setBizDate("20070225");
            l_fundOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_fundOrderUnitParams);
            
            //FrgnMmfExchangeRateParams
            FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = new FrgnMmfExchangeRateParams();
            l_frgnMmfExchangeRateParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_frgnMmfExchangeRateParams.setCurrencyCode(l_fundProductParams.getCurrencyCode());
            l_frgnMmfExchangeRateParams.setCreatedTimestamp(l_date);
            l_frgnMmfExchangeRateParams.setLastUpdatedTimestamp(l_date);
            l_frgnMmfExchangeRateParams.setTtBuyingRate(10.667d);
            l_frgnMmfExchangeRateParams.setExecTimestamp(l_date);
            TestDBUtility.insertWithDel(l_frgnMmfExchangeRateParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            l_imple.execute(l_request);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80006);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public MutualFundOrderUnitParams getMutualFundOrderUnitParams()
    {
        MutualFundOrderUnitParams l_params = new MutualFundOrderUnitParams();
        //      注文単位ＩＤ    order_unit_id     NUMBER  18 NotNull 
        l_params.setOrderUnitId(1001);
        //      口座ＩＤ    account_id     NUMBER  18 NotNull  
        l_params.setAccountId(101001010010L);
        //      補助口座ＩＤ    sub_account_id     NUMBER  18 NotNull  
        l_params.setSubAccountId(10100101001007L);
        //      部店ＩＤ    branch_id     NUMBER  18 NotNull  
        l_params.setBranchId(33381);
        //      取引者ＩＤ    trader_id     NUMBER  18 NULL  
        //      注文ＩＤ    order_id     NUMBER  18 NotNull  
        l_params.setOrderId(1001);
        //      注文種別    order_type     NUMBER  6 NotNull  
        l_params.setOrderType(OrderTypeEnum.ASSET_IN);
        //      注文カテゴリ    order_categ     NUMBER  6 NotNull  
        l_params.setOrderCateg(OrderCategEnum.ASSET);
        //      注文履歴最終通番    last_order_action_serial_no     NUMBER  8 NotNull  
        l_params.setLastOrderActionSerialNo(1);
        //      銘柄タイプ    product_type     NUMBER  6 NotNull  
        l_params.setProductType(ProductTypeEnum.MUTUAL_FUND);
        //      市場ＩＤ    market_id     NUMBER  18 NULL  
        //      注文数量    quantity     DECIMAL  18 12 6 NotNull  
        l_params.setQuantity(123d);
        Timestamp l_timestamp = GtlUtils.getSystemTimestamp();
        //      受渡日    delivery_date     DATE   NotNull  
        l_params.setDeliveryDate(l_timestamp);
        //      注文失効日付    expiration_date     DATE   NULL  
        //      市場から確認済みの数量    confirmed_quantity     DECIMAL  18 12 6 NULL  
        //      約定数量    executed_quantity     DECIMAL  18 12 6 NULL  
        //      合計約定金額    executed_amount     DECIMAL  18 12 6 NULL  
        //      注文状態    order_status     NUMBER  6 NotNull 
        l_params.setOrderStatus(OrderStatusEnum.ACCEPTED);
        //      注文有効状態    order_open_status     NUMBER  6 NotNull  
        l_params.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        //      失効区分    expiration_status     NUMBER  6 NotNull  
        l_params.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        //      税区分    tax_type     NUMBER  6 NotNull  
        l_params.setTaxType(TaxTypeEnum.NORMAL);
        //      発注日    biz_date     VARCHAR2  8 NotNull  
        l_params.setBizDate("20010201");
        //      銘柄ＩＤ    product_id     NUMBER  18 NotNull  
        l_params.setProductId(1006169090018L);
        //      注文数量タイプ    quantity_type     NUMBER  6 NotNull  
        l_params.setQuantityType(QuantityTypeEnum.AMOUNT);
        //      初回注文の注文チャネル    order_chanel     VARCHAR2  1 NULL  
        //      受注日時    received_date_time     DATE   NULL  
        //      扱者コード（SONAR）    sonar_trader_code     VARCHAR2  5 NULL  
        //      識別コード    order_request_number     VARCHAR2  9 NULL  
        //      計算基準価額    calc_constant_value     DECIMAL  18 12 6 NULL  
        //      計算基準価額（乗換先）    swt_calc_constant_value     DECIMAL  18 12 6 NULL  
        //      基準価額適用日    constant_value_app_date     DATE   NULL  
        //      概算受渡代金    estimated_price     DECIMAL  18 12 6 NULL  
        //      概算売買口数    estimate_dealing_qty     DECIMAL  18 12 6 NULL  
        //      概算買付口数（乗換先）    swt_estimate_dealing_qty     DECIMAL  18 12 6 NULL  
        //      税区分（乗換先）    swt_tax_type     NUMBER  6 NULL  
        //      銘柄コード（乗換先）    swt_product_code     VARCHAR2  10 NULL  
        //      受渡方法@    payment_method     VARCHAR2  1 NULL  
        //      投信タイプ    fund_type     NUMBER  6 NULL  
        //      投信解約区分    fund_sell_div     VARCHAR2  1 NULL  
        //      約定日    exec_date     DATE   NULL  
        //      約定状態    exec_status     VARCHAR2  1 NULL  
        //      決済区分    settlement_div     VARCHAR2  1 NULL  
        //      無手数料区分    no_contract_commission_div     VARCHAR2  1 NULL  
        //      請求区分    request_div     VARCHAR2  1 NULL  
        //      注文経路区分    order_root_div     VARCHAR2  1 NULL  
        //      注文エラー理由コード    error_reason_code     VARCHAR2  4 NULL  
        //      作成日付    created_timestamp     DATE   NotNull  
        l_params.setCreatedTimestamp(l_timestamp);
        //      更新日付    last_updated_timestamp     DATE   NotNull 
        l_params.setLastUpdatedTimestamp(l_timestamp);
        //      入金日    payment_date     DATE   NULL  
        //      源泉徴収拘束金    withholding_tax_restriction     DECIMAL  18 12 6 NULL  
        //      出金注文識別コード    payment_order_req_number     VARCHAR2  9 NULL  
        //      CPU No.    cpu_no     VARCHAR2  5 NULL  
        //      乗換元約定日    swt_exec_date     DATE   NULL  
        return l_params;
    }
}
@

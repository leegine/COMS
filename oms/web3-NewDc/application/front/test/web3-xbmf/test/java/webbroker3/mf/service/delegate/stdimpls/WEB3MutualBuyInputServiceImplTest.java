head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.10.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualBuyInputServiceImplTest.java;


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

import junit.framework.Assert;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BuyLimitDivDef;
import webbroker3.common.define.WEB3ContMrgDivDef;
import webbroker3.common.define.WEB3ForeignSecAccOpenDiv;
import webbroker3.common.define.WEB3StockTypeBondTypeDef;
import webbroker3.common.define.WEB3SystemHandlingDivDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.ExchangeRateParams;
import webbroker3.gentrade.data.ExchangeRateRow;
import webbroker3.gentrade.data.FrgnMmfExchangeRateParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundTradedProduct;
import webbroker3.mf.WEB3MutualFundTradingTimeManagementForMock;
import webbroker3.mf.define.WEB3MFEstimatedPriceCurrencyCodeDef;
import webbroker3.mf.define.WEB3MFPlowbackProductDivDef;
import webbroker3.mf.message.WEB3MutualBuyInputRequest;
import webbroker3.mf.message.WEB3MutualBuyInputResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


public class WEB3MutualBuyInputServiceImplTest extends TestBaseForMock
{
    /**
     * Logger
     */
   private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3MutualBuyInputServiceImplTest.class);

    public WEB3MutualBuyInputServiceImplTest(String arg0)
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

    
    public class WEB3MutualBuyInputRequestForMock 
    extends WEB3MutualBuyInputRequest
    {
        public void validate()
        {
            final String STR_METHOD_NAME = "WEB3MutualBuyInputRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    /*
     * Test method for 'webbroker3.mf.service.delegate.stdimpls.WEB3MutualBuyInputServiceImpl.execute(WEB3GenRequest)'
     */
    public void testExecuteC1()
    {
        final String STR_METHOD_NAME = "testExecuteC1()";
        log.entering(STR_METHOD_NAME);
        
        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setAccountId(333812512203L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setForeignSecAccOpenDiv(WEB3ForeignSecAccOpenDiv.NOT_OPEN);
        l_mainAccountParams.setAccountId(333812512203L);
       
        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("6D");
        l_institutionParams.setInstitutionId(63);

        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("6D");
        l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
        
        TradedProductParams l_tradeProductParams =
            TestDBUtility.getTradedProductRow();
        l_tradeProductParams.setProductId(3304148080001L);
        l_tradeProductParams.setTradedProductId(330304148080000L);
        l_tradeProductParams.setInstitutionCode("6D");
        l_tradeProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
        
        MutualFundProductParams l_mfProductParams =
            TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(3304148080001L);
        l_mfProductParams.setProductCode("0");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setInstitutionCode("6D");
        l_mfProductParams.setPlowbackProductDiv(WEB3MFPlowbackProductDivDef.PLOWBACK_PRODUCT);
        l_mfProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
        l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20071001","yyyyMMdd"));
        l_mfProductParams.setBuyStartDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_mfProductParams.setBuyLimitDiv(WEB3BuyLimitDivDef.BUY_POSSIBLE);
        l_mfProductParams.setCurrencyCode("0");
        l_mfProductParams.setFundType(MutualFundTypeEnum.OTHER);

        
        MutualFundTradedProductParams l_mfTradeProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        l_mfTradeProductParams.setTradedProductId(330304148080000L);
        l_mfTradeProductParams.setProductId(3304148080001L);
        l_mfTradeProductParams.setMarketId(3303L);
        l_mfTradeProductParams.setInstitutionCode("6D");
        
        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        l_marketParams.setMarketCode("0");
        l_marketParams.setInstitutionCode("6D");
        
        ExchangeRateParams l_exchangeRateParams =
            TestDBUtility.getExchangeRateRow();
        l_exchangeRateParams.setInstitutionCode("6D");
        l_exchangeRateParams.setCurrencyCode("0");

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
             "validateEmergencyStop",
             new Class[] {WEB3MutualFundProduct.class, String.class},null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
             "validateFrgnMmfDoubleOrder",
             new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.gentrade.WEB3GentradeMainAccount",
             "isSpecialAccountEstablished",
             new Class[] {Date.class, SubAccount.class}, Boolean.TRUE);
        
        try
        {

            
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(
                WEB3DateUtility.getDate("20070110","yyyyMMdd"));
            ThreadLocalSystemAttributesRegistry.setAttribute(
                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME, new Timestamp(20070113000000L));
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_mfProductParams);
            
            TestDBUtility.deleteAll(MutualFundTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_mfTradeProductParams);

            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_tradeProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            TestDBUtility.deleteAll(ExchangeRateRow.TYPE);
            TestDBUtility.insertWithDel(l_exchangeRateParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "webbroker3.mf.WEB3MutualFundProductManager",
                 "getMutualFundTradedProduct",
                 new Class[] {Institution.class, String.class},
                 new WEB3MutualFundTradedProduct(l_mfTradeProductParams));
            
            WEB3MutualBuyInputServiceImpl l_impl = new WEB3MutualBuyInputServiceImpl();
            WEB3MutualBuyInputRequestForMock l_request = new WEB3MutualBuyInputRequestForMock();
            l_request.id = "3304148080001";
            l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            l_ex.printStackTrace();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01341, l_ex.getErrorInfo());
            log.info("当該顧客は外国証券口座開設なし。");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    
//    public void testExecuteC2()
//    {
//        final String STR_METHOD_NAME = "testExecuteC2()";
//        log.entering(STR_METHOD_NAME);
//        
//        SubAccountParams l_subAccountParams =
//            TestDBUtility.getSubAccountRow();
//        l_subAccountParams.setInstitutionCode("6D");
//        l_subAccountParams.setInstitutionId(63);
//        l_subAccountParams.setAccountId(333812512203L);
//        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//        
//        
//        MainAccountParams l_mainAccountParams =
//            TestDBUtility.getMainAccountRow();
//        l_mainAccountParams.setForeignSecAccOpenDiv(WEB3ForeignSecAccOpenDiv.NOT_OPEN);
//        l_mainAccountParams.setAccountId(333812512203L);
////        l_mainAccountParams.setForeignContDiv(WEB3ContMrgDivDef.COLLECT_CANCEL);
//       
//        InstitutionParams l_institutionParams =
//            TestDBUtility.getInstitutionRow();
//        l_institutionParams.setInstitutionCode("6D");
//        l_institutionParams.setInstitutionId(63);
//
//        ProductParams l_productParams =
//            TestDBUtility.getProductRow();
//        l_productParams.setProductId(3304148080001L);
//        l_productParams.setInstitutionCode("6D");
//        l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
//        
//        TradedProductParams l_tradeProductParams =
//            TestDBUtility.getTradedProductRow();
//        l_tradeProductParams.setProductId(3304148080001L);
//        l_tradeProductParams.setTradedProductId(330304148080000L);
//        l_tradeProductParams.setInstitutionCode("6D");
//        l_tradeProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
//        
//        MutualFundProductParams l_mfProductParams =
//            TestDBUtility.getMutualFundProductRow();
//        l_mfProductParams.setProductId(3304148080001L);
//        l_mfProductParams.setProductCode("0");
//        l_mfProductParams.setProductIssueCode("0");
//        l_mfProductParams.setInstitutionCode("6D");
//        l_mfProductParams.setPlowbackProductDiv(WEB3MFPlowbackProductDivDef.PLOWBACK_PRODUCT);
//        l_mfProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
//        l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20071001","yyyyMMdd"));
//        l_mfProductParams.setBuyStartDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//        l_mfProductParams.setBuyLimitDiv(WEB3BuyLimitDivDef.BUY_POSSIBLE);
//        l_mfProductParams.setCurrencyCode("0");
//        l_mfProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
//
//        
//        MutualFundTradedProductParams l_mfTradeProductParams =
//            TestDBUtility.getMutualFundTradedProductRow();
//        l_mfTradeProductParams.setTradedProductId(330304148080000L);
//        l_mfTradeProductParams.setProductId(3304148080001L);
//        l_mfTradeProductParams.setMarketId(3303L);
//        l_mfTradeProductParams.setInstitutionCode("6D");
//        
//        MarketParams l_marketParams =
//            TestDBUtility.getMarketRow();
//        l_marketParams.setMarketId(3303L);
//        l_marketParams.setMarketCode("0");
//        l_marketParams.setInstitutionCode("6D");
//        
//        ExchangeRateParams l_exchangeRateParams =
//            TestDBUtility.getExchangeRateRow();
//        l_exchangeRateParams.setInstitutionCode("6D");
//        l_exchangeRateParams.setCurrencyCode("0");
//
//        MOCK_MANAGER.setIsMockUsed(true);
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//            "getAccountId",
//            new Class[] {},new Long(333812512203L));
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//             "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
//             "validateEmergencyStop",
//             new Class[] {WEB3MutualFundProduct.class, String.class},null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//             "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
//             "validateFrgnMmfDoubleOrder",
//             new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//             "webbroker3.gentrade.WEB3GentradeMainAccount",
//             "isSpecialAccountEstablished",
//             new Class[] {Date.class, SubAccount.class}, Boolean.TRUE);
//        
//        try
//        {
//
//            
//            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(
//                WEB3DateUtility.getDate("20070110","yyyyMMdd"));
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME, new Timestamp(20070113000000L));
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//
//            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
//            TestDBUtility.insertWithDel(l_mfProductParams);
//            
//            TestDBUtility.deleteAll(MutualFundTradedProductRow.TYPE);
//            TestDBUtility.insertWithDel(l_mfTradeProductParams);
//
//            TestDBUtility.insertWithDel(l_institutionParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_tradeProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            
//            
//            TestDBUtility.deleteAll(ExchangeRateRow.TYPE);
//            TestDBUtility.insertWithDel(l_exchangeRateParams);
//            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                 "webbroker3.mf.WEB3MutualFundProductManager",
//                 "getMutualFundTradedProduct",
//                 new Class[] {Institution.class, String.class},
//                 new WEB3MutualFundTradedProduct(l_mfTradeProductParams));
//            
//            WEB3MutualBuyInputServiceImpl l_impl = new WEB3MutualBuyInputServiceImpl();
//            WEB3MutualBuyInputRequestForMock l_request = new WEB3MutualBuyInputRequestForMock();
//            l_request.id = "3304148080001";
//            l_impl.execute(l_request);
//            fail();
//        }
//        catch (WEB3BusinessLayerException l_ex)
//        {
//            l_ex.printStackTrace();
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02731, l_ex.getErrorInfo());
//            log.info("（外）契約書未徴収エラー。");
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        } 
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//
//    }
//    
//    public void testExecuteC3()
//    {
//        final String STR_METHOD_NAME = "testExecuteC3()";
//        log.entering(STR_METHOD_NAME);
//        
//        SubAccountParams l_subAccountParams =
//            TestDBUtility.getSubAccountRow();
//        l_subAccountParams.setInstitutionCode("6D");
//        l_subAccountParams.setInstitutionId(63);
//        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//        
//        MainAccountParams l_mainAccountParams =
//            TestDBUtility.getMainAccountRow();
//        l_mainAccountParams.setForeignSecAccOpenDiv(WEB3ForeignSecAccOpenDiv.NOT_OPEN);
//        l_mainAccountParams.setAccountId(333812512203L);
////        l_mainAccountParams.setForeignContDiv(WEB3ContMrgDivDef.COLLECT);
//       
//        InstitutionParams l_institutionParams =
//            TestDBUtility.getInstitutionRow();
//        l_institutionParams.setInstitutionCode("6D");
//        l_institutionParams.setInstitutionId(63);
//
//        ProductParams l_productParams =
//            TestDBUtility.getProductRow();
//        l_productParams.setProductId(3304148080001L);
//        l_productParams.setInstitutionCode("6D");
//        l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
//        
//        TradedProductParams l_tradeProductParams =
//            TestDBUtility.getTradedProductRow();
//        l_tradeProductParams.setProductId(3304148080001L);
//        l_tradeProductParams.setTradedProductId(330304148080000L);
//        l_tradeProductParams.setInstitutionCode("6D");
//        l_tradeProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
//        
//        MutualFundProductParams l_mfProductParams =
//            TestDBUtility.getMutualFundProductRow();
//        l_mfProductParams.setProductId(3304148080001L);
//        l_mfProductParams.setProductCode("0");
//        l_mfProductParams.setProductIssueCode("0");
//        l_mfProductParams.setInstitutionCode("6D");
//        l_mfProductParams.setPlowbackProductDiv(WEB3MFPlowbackProductDivDef.PLOWBACK_PRODUCT);
//        l_mfProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
//        l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20071001","yyyyMMdd"));
//        l_mfProductParams.setBuyStartDate(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
//        l_mfProductParams.setBuyLimitDiv(WEB3BuyLimitDivDef.BUY_POSSIBLE);
//        l_mfProductParams.setCurrencyCode(WEB3MFEstimatedPriceCurrencyCodeDef.T0);
//        l_mfProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
//
//        
//        MutualFundTradedProductParams l_mfTradeProductParams =
//            TestDBUtility.getMutualFundTradedProductRow();
//        l_mfTradeProductParams.setTradedProductId(330304148080000L);
//        l_mfTradeProductParams.setProductId(3304148080001L);
//        l_mfTradeProductParams.setMarketId(3303L);
//        l_mfTradeProductParams.setInstitutionCode("6D");
//        
//        MarketParams l_marketParams =
//            TestDBUtility.getMarketRow();
//        l_marketParams.setMarketId(3303L);
//        l_marketParams.setMarketCode("0");
//        l_marketParams.setInstitutionCode("6D");
//        
//        ExchangeRateParams l_exchangeRateParams =
//            TestDBUtility.getExchangeRateRow();
//        l_exchangeRateParams.setInstitutionCode("6D");
//        l_exchangeRateParams.setCurrencyCode("0");
//        
//        BranchParams l_branchParams = TestDBUtility.getBranchRow();
//        l_branchParams.setBranchId(33381);
//
//        MOCK_MANAGER.setIsMockUsed(true);
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//            "getAccountId",
//            new Class[] {},new Long(333812512203L));
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//             "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
//             "validateEmergencyStop",
//             new Class[] {WEB3MutualFundProduct.class, String.class},null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//             "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
//             "validateFrgnMmfDoubleOrder",
//             new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//             "webbroker3.gentrade.WEB3GentradeMainAccount",
//             "isSpecialAccountEstablished",
//             new Class[] {Date.class, SubAccount.class}, Boolean.TRUE);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
//                "getMutualFundBuyTradingPower",
//                new Class[] {WEB3GentradeSubAccount.class, Date.class, OrderTypeEnum.class}, new Double(213213));
//        
//        try
//        {
//
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(
//                WEB3DateUtility.getDate("20070110","yyyyMMdd"));
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME, new Timestamp(20070113000000L));
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//
//            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
//            TestDBUtility.insertWithDel(l_mfProductParams);
//            
//            TestDBUtility.deleteAll(MutualFundTradedProductRow.TYPE);
//            TestDBUtility.insertWithDel(l_mfTradeProductParams);
//
//            TestDBUtility.insertWithDel(l_institutionParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_tradeProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            
//            
//            TestDBUtility.deleteAll(ExchangeRateRow.TYPE);
//            TestDBUtility.insertWithDel(l_exchangeRateParams); 
//            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            
//            TestDBUtility.insertWithDel(l_branchParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                 "webbroker3.mf.WEB3MutualFundProductManager",
//                 "getMutualFundTradedProduct",
//                 new Class[] {Institution.class, String.class},
//                 new WEB3MutualFundTradedProduct(l_mfTradeProductParams));
//            
//            WEB3MutualBuyInputServiceImpl l_impl = new WEB3MutualBuyInputServiceImpl();
//            WEB3MutualBuyInputRequestForMock l_request = new WEB3MutualBuyInputRequestForMock();
//            l_request.id = "3304148080001";
//            WEB3MutualBuyInputResponse l_response = (WEB3MutualBuyInputResponse)l_impl.execute(l_request);
//            
//            //口座区分一覧
//            String[] l_strTaxType = l_response.taxTypeList;
//            assertEquals(l_strTaxType[0], "2");
//            //円転基準価額
//            assertNull(l_response.yenConstantValue);
//            //参考レート  
//            assertNull(l_response.referenceRate);
//            //参考レート確定日
//            assertNull(l_response.referenceRateFixedDay);
//            
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        } 
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//
//    }

    public void testExecuteC4()
    {
        final String STR_METHOD_NAME = "testExecuteC4()";
        log.entering(STR_METHOD_NAME);
        
        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setForeignSecAccOpenDiv(WEB3ForeignSecAccOpenDiv.OPEN);
        l_mainAccountParams.setAccountId(333812512203L);
//        l_mainAccountParams.setForeignContDiv(WEB3ContMrgDivDef.COLLECT);
       
        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("6D");
        l_institutionParams.setInstitutionId(63);

        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("6D");
        l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
        
        TradedProductParams l_tradeProductParams =
            TestDBUtility.getTradedProductRow();
        l_tradeProductParams.setProductId(3304148080001L);
        l_tradeProductParams.setTradedProductId(330304148080000L);
        l_tradeProductParams.setInstitutionCode("6D");
        l_tradeProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
        
        MutualFundProductParams l_mfProductParams =
            TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(3304148080001L);
        l_mfProductParams.setProductCode("0");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setInstitutionCode("6D");
        l_mfProductParams.setPlowbackProductDiv(WEB3MFPlowbackProductDivDef.PLOWBACK_PRODUCT);
        l_mfProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
        l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20071001","yyyyMMdd"));
        l_mfProductParams.setBuyStartDate(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
        l_mfProductParams.setBuyLimitDiv(WEB3BuyLimitDivDef.BUY_POSSIBLE);
        l_mfProductParams.setCurrencyCode(WEB3MFEstimatedPriceCurrencyCodeDef.A0);
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
        l_mfProductParams.setStockTypeBondType(WEB3StockTypeBondTypeDef.STOCK_TYPE);
        l_mfProductParams.setBuyConstantValue(11);
        
        MutualFundTradedProductParams l_mfTradeProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        l_mfTradeProductParams.setTradedProductId(330304148080000L);
        l_mfTradeProductParams.setProductId(3304148080001L);
        l_mfTradeProductParams.setMarketId(3303L);
        l_mfTradeProductParams.setInstitutionCode("6D");
        
        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        l_marketParams.setMarketCode("0");
        l_marketParams.setInstitutionCode("6D");
        
        ExchangeRateParams l_exchangeRateParams =
            TestDBUtility.getExchangeRateRow();
        l_exchangeRateParams.setInstitutionCode("6D");
        l_exchangeRateParams.setCurrencyCode("A0");
        l_exchangeRateParams.setExecTimestamp(WEB3DateUtility.getDate("20071001","yyyyMMdd"));
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        l_branchParams.setBranchId(33381);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
             "validateEmergencyStop",
             new Class[] {WEB3MutualFundProduct.class, String.class},null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
             "validateFrgnMmfDoubleOrder",
             new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.gentrade.WEB3GentradeMainAccount",
             "isSpecialAccountEstablished",
             new Class[] {Date.class, SubAccount.class}, Boolean.TRUE);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getMutualFundBuyTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Date.class, OrderTypeEnum.class}, new Double(213213));
        
        try
        {

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(
                WEB3DateUtility.getDate("20070110","yyyyMMdd"));
            ThreadLocalSystemAttributesRegistry.setAttribute(
                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME, new Timestamp(20070113000000L));
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_mfProductParams);
            
            TestDBUtility.deleteAll(MutualFundTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_mfTradeProductParams);

            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_tradeProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            TestDBUtility.deleteAll(ExchangeRateRow.TYPE);
            TestDBUtility.insertWithDel(l_exchangeRateParams); 
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "webbroker3.mf.WEB3MutualFundProductManager",
                 "getMutualFundTradedProduct",
                 new Class[] {Institution.class, String.class},
                 new WEB3MutualFundTradedProduct(l_mfTradeProductParams));
            
            WEB3MutualBuyInputServiceImpl l_impl = new WEB3MutualBuyInputServiceImpl();
            WEB3MutualBuyInputRequestForMock l_request = new WEB3MutualBuyInputRequestForMock();
            l_request.id = "3304148080001";
            WEB3MutualBuyInputResponse l_response = (WEB3MutualBuyInputResponse)l_impl.execute(l_request);
            
            //口座区分一覧
            String[] l_strTaxType = l_response.taxTypeList;
            assertEquals(l_strTaxType[0], "1");
            assertEquals(l_strTaxType[1], "0");
            //円転基準価額
            assertEquals(l_response.yenConstantValue, "50");
            //参考レート  
            assertEquals(l_response.referenceRate, "4.55");
            //参考レート確定日
            assertEquals(WEB3DateUtility.formatDate(l_response.referenceRateFixedDay,"yyyyMMdd"), "20071001");
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testExecuteC5()
    {
        final String STR_METHOD_NAME = "testExecuteC5()";
        log.entering(STR_METHOD_NAME);
        
        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setForeignSecAccOpenDiv(WEB3ForeignSecAccOpenDiv.OPEN);
        l_mainAccountParams.setAccountId(333812512203L);
//        l_mainAccountParams.setForeignContDiv(WEB3ContMrgDivDef.COLLECT);
       
        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("6D");
        l_institutionParams.setInstitutionId(63);

        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("6D");
        l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
        
        TradedProductParams l_tradeProductParams =
            TestDBUtility.getTradedProductRow();
        l_tradeProductParams.setProductId(3304148080001L);
        l_tradeProductParams.setTradedProductId(330304148080000L);
        l_tradeProductParams.setInstitutionCode("6D");
        l_tradeProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
        
        MutualFundProductParams l_mfProductParams =
            TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(3304148080001L);
        l_mfProductParams.setProductCode("0");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setInstitutionCode("6D");
        l_mfProductParams.setPlowbackProductDiv(WEB3MFPlowbackProductDivDef.PLOWBACK_PRODUCT);
        l_mfProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
        l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20071001","yyyyMMdd"));
        l_mfProductParams.setBuyStartDate(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
        l_mfProductParams.setBuyLimitDiv(WEB3BuyLimitDivDef.BUY_POSSIBLE);
        l_mfProductParams.setCurrencyCode(WEB3MFEstimatedPriceCurrencyCodeDef.A0);
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mfProductParams.setStockTypeBondType(WEB3StockTypeBondTypeDef.STOCK_TYPE);
        l_mfProductParams.setBuyConstantValue(11);
        
        MutualFundTradedProductParams l_mfTradeProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        l_mfTradeProductParams.setTradedProductId(330304148080000L);
        l_mfTradeProductParams.setProductId(3304148080001L);
        l_mfTradeProductParams.setMarketId(3303L);
        l_mfTradeProductParams.setInstitutionCode("6D");
        
        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        l_marketParams.setMarketCode("0");
        l_marketParams.setInstitutionCode("6D");
        
        ExchangeRateParams l_exchangeRateParams =
            TestDBUtility.getExchangeRateRow();
        l_exchangeRateParams.setInstitutionCode("6D");
        l_exchangeRateParams.setCurrencyCode("A0");
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        l_branchParams.setBranchId(33381);


        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
             "validateEmergencyStop",
             new Class[] {WEB3MutualFundProduct.class, String.class},null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
             "validateFrgnMmfDoubleOrder",
             new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.gentrade.WEB3GentradeMainAccount",
             "isSpecialAccountEstablished",
             new Class[] {Date.class, SubAccount.class}, Boolean.TRUE);
        
        FrgnMmfExchangeRateParams l_params = new FrgnMmfExchangeRateParams();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundProduct",
            "getFrgnMmfExchangeRate",
            new Class[] {},l_params);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getMutualFundBuyTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Date.class, OrderTypeEnum.class}, new Double(213213));
        
        l_params.setTtSellingRate(11);
        l_params.setExecTimestamp(WEB3DateUtility.getDate("20070110","yyyyMMdd"));
        
        try
        {

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(
                WEB3DateUtility.getDate("20070110","yyyyMMdd"));
            ThreadLocalSystemAttributesRegistry.setAttribute(
                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME, new Timestamp(20070113000000L));
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_mfProductParams);
            
            TestDBUtility.deleteAll(MutualFundTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_mfTradeProductParams);

            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_tradeProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            TestDBUtility.deleteAll(ExchangeRateRow.TYPE);
            TestDBUtility.insertWithDel(l_exchangeRateParams); 
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "webbroker3.mf.WEB3MutualFundProductManager",
                 "getMutualFundTradedProduct",
                 new Class[] {Institution.class, String.class},
                 new WEB3MutualFundTradedProduct(l_mfTradeProductParams));
            
            WEB3MutualBuyInputServiceImpl l_impl = new WEB3MutualBuyInputServiceImpl();
            WEB3MutualBuyInputRequestForMock l_request = new WEB3MutualBuyInputRequestForMock();
            l_request.id = "3304148080001";
            WEB3MutualBuyInputResponse l_response = (WEB3MutualBuyInputResponse)l_impl.execute(l_request);
            
            //口座区分一覧
            String[] l_strTaxType = l_response.taxTypeList;
            assertEquals(l_strTaxType[0], "2");
            //円転基準価額
            assertNull(l_response.yenConstantValue);
            //参考レート  
            assertEquals(l_response.referenceRate, "11");
            //参考レート確定日
            assertEquals(WEB3DateUtility.formatDate(l_response.referenceRateFixedDay,"yyyyMMdd"), "20070110");
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);

    }

    
    
    public void testExecuteC6()
    {
        final String STR_METHOD_NAME = "testExecuteC2()";
        log.entering(STR_METHOD_NAME);
        
        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setAccountId(333812512203L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setForeignSecAccOpenDiv(WEB3ForeignSecAccOpenDiv.NOT_OPEN);
        l_mainAccountParams.setAccountId(333812512203L);
//        l_mainAccountParams.setForeignContDiv(WEB3ContMrgDivDef.COLLECT_CANCEL);
       
        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("6D");
        l_institutionParams.setInstitutionId(63);

        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("6D");
        l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
        
        TradedProductParams l_tradeProductParams =
            TestDBUtility.getTradedProductRow();
        l_tradeProductParams.setProductId(3304148080001L);
        l_tradeProductParams.setTradedProductId(330304148080000L);
        l_tradeProductParams.setInstitutionCode("6D");
        l_tradeProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
        
        MutualFundProductParams l_mfProductParams =
            TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(3304148080001L);
        l_mfProductParams.setProductCode("0");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setInstitutionCode("6D");
        l_mfProductParams.setPlowbackProductDiv(WEB3MFPlowbackProductDivDef.PLOWBACK_PRODUCT);
        l_mfProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
        l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20071001","yyyyMMdd"));
        l_mfProductParams.setBuyStartDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_mfProductParams.setBuyLimitDiv(WEB3BuyLimitDivDef.BUY_POSSIBLE);
        l_mfProductParams.setCurrencyCode("0");
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);

        
        MutualFundTradedProductParams l_mfTradeProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        l_mfTradeProductParams.setTradedProductId(330304148080000L);
        l_mfTradeProductParams.setProductId(3304148080001L);
        l_mfTradeProductParams.setMarketId(3303L);
        l_mfTradeProductParams.setInstitutionCode("6D");
        
        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        l_marketParams.setMarketCode("0");
        l_marketParams.setInstitutionCode("6D");
        
        ExchangeRateParams l_exchangeRateParams =
            TestDBUtility.getExchangeRateRow();
        l_exchangeRateParams.setInstitutionCode("6D");
        l_exchangeRateParams.setCurrencyCode("0");

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
             "validateEmergencyStop",
             new Class[] {WEB3MutualFundProduct.class, String.class},null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
             "validateFrgnMmfDoubleOrder",
             new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.gentrade.WEB3GentradeMainAccount",
             "isSpecialAccountEstablished",
             new Class[] {Date.class, SubAccount.class}, Boolean.TRUE);
        
        try
        {

            
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(
                WEB3DateUtility.getDate("20070110","yyyyMMdd"));
            ThreadLocalSystemAttributesRegistry.setAttribute(
                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME, new Timestamp(20070113000000L));
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_mfProductParams);
            
            TestDBUtility.deleteAll(MutualFundTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_mfTradeProductParams);

            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_tradeProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            TestDBUtility.deleteAll(ExchangeRateRow.TYPE);
            TestDBUtility.insertWithDel(l_exchangeRateParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "webbroker3.mf.WEB3MutualFundProductManager",
                 "getMutualFundTradedProduct",
                 new Class[] {Institution.class, String.class},
                 new WEB3MutualFundTradedProduct(l_mfTradeProductParams));
            
            WEB3MutualBuyInputServiceImpl l_impl = new WEB3MutualBuyInputServiceImpl();
            WEB3MutualBuyInputRequestForMock l_request = new WEB3MutualBuyInputRequestForMock();
            l_request.id = "3304148080001";
            l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            l_ex.printStackTrace();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01341, l_ex.getErrorInfo());
            log.info("（外）契約書未徴収エラー。");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testExecuteC7()
    {
        final String STR_METHOD_NAME = "testExecuteC7()";
        log.entering(STR_METHOD_NAME);
        
        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setForeignSecAccOpenDiv(WEB3ForeignSecAccOpenDiv.OPEN);
        l_mainAccountParams.setAccountId(333812512203L);
       
        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("6D");
        l_institutionParams.setInstitutionId(63);

        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("6D");
        l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
        
        TradedProductParams l_tradeProductParams =
            TestDBUtility.getTradedProductRow();
        l_tradeProductParams.setProductId(3304148080001L);
        l_tradeProductParams.setTradedProductId(330304148080000L);
        l_tradeProductParams.setInstitutionCode("6D");
        l_tradeProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
        
        
        MutualFundProductParams l_mfProductParams =
            TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(3304148080001L);
        l_mfProductParams.setProductCode("0");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setInstitutionCode("6D");
        l_mfProductParams.setPlowbackProductDiv(WEB3MFPlowbackProductDivDef.PLOWBACK_PRODUCT);
        l_mfProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
        l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20071001","yyyyMMdd"));
        l_mfProductParams.setBuyStartDate(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
        l_mfProductParams.setBuyLimitDiv(WEB3BuyLimitDivDef.BUY_POSSIBLE);
        l_mfProductParams.setCurrencyCode(WEB3MFEstimatedPriceCurrencyCodeDef.A0);
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mfProductParams.setStockTypeBondType(WEB3StockTypeBondTypeDef.STOCK_TYPE);
        l_mfProductParams.setBuyConstantValue(11);
        l_mfProductParams.setFrgnNewBuyUnitAmt(new Long(123456));
        l_mfProductParams.setFrgnNewBuyMinAmt(new Long(456789));
        
        
        MutualFundTradedProductParams l_mfTradeProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        l_mfTradeProductParams.setTradedProductId(330304148080000L);
        l_mfTradeProductParams.setProductId(3304148080001L);
        l_mfTradeProductParams.setMarketId(3303L);
        l_mfTradeProductParams.setInstitutionCode("6D");
        
        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        l_marketParams.setMarketCode("0");
        l_marketParams.setInstitutionCode("6D");
        
        ExchangeRateParams l_exchangeRateParams =
            TestDBUtility.getExchangeRateRow();
        l_exchangeRateParams.setInstitutionCode("6D");
        l_exchangeRateParams.setCurrencyCode("A0");
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        l_branchParams.setBranchId(33381);


        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
             "validateEmergencyStop",
             new Class[] {WEB3MutualFundProduct.class, String.class},null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
             "validateFrgnMmfDoubleOrder",
             new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.gentrade.WEB3GentradeMainAccount",
             "isSpecialAccountEstablished",
             new Class[] {Date.class, SubAccount.class}, Boolean.TRUE);
        
        FrgnMmfExchangeRateParams l_params = new FrgnMmfExchangeRateParams();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundProduct",
            "getFrgnMmfExchangeRate",
            new Class[] {},l_params);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getMutualFundBuyTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Date.class, OrderTypeEnum.class}, new Double(213213));
        
        l_params.setTtSellingRate(11);
        l_params.setExecTimestamp(WEB3DateUtility.getDate("20070110","yyyyMMdd"));
        
        try
        {

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(
                WEB3DateUtility.getDate("20070110","yyyyMMdd"));
            ThreadLocalSystemAttributesRegistry.setAttribute(
                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME, new Timestamp(20070113000000L));
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(AssetRow.TYPE);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_tradeProductParams);
            TestDBUtility.insertWithDel(l_mfProductParams);
            
            TestDBUtility.deleteAll(MutualFundTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_mfTradeProductParams);

            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.insertWithDel(l_marketParams);
            
            
            TestDBUtility.deleteAll(ExchangeRateRow.TYPE);
            TestDBUtility.insertWithDel(l_exchangeRateParams); 
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "webbroker3.mf.WEB3MutualFundProductManager",
                 "getMutualFundTradedProduct",
                 new Class[] {Institution.class, String.class},
                 new WEB3MutualFundTradedProduct(l_mfTradeProductParams));
            
            WEB3MutualBuyInputServiceImpl l_impl = new WEB3MutualBuyInputServiceImpl();
            WEB3MutualBuyInputRequestForMock l_request = new WEB3MutualBuyInputRequestForMock();
            l_request.id = "3304148080001";
            WEB3MutualBuyInputResponse l_response = (WEB3MutualBuyInputResponse)l_impl.execute(l_request);
            
            assertEquals("123456", l_response.buyFrgnUnitAmt);
            assertEquals("456789", l_response.buyFrgnMinAmt);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    
    
    public void testExecuteC8()
    {
        final String STR_METHOD_NAME = "testExecuteC8()";
        log.entering(STR_METHOD_NAME);
        
        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setForeignSecAccOpenDiv(WEB3ForeignSecAccOpenDiv.OPEN);
        l_mainAccountParams.setAccountId(333812512203L);
       
        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("6D");
        l_institutionParams.setInstitutionId(63);

        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("6D");
        l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
        
        TradedProductParams l_tradeProductParams =
            TestDBUtility.getTradedProductRow();
        l_tradeProductParams.setProductId(3304148080001L);
        l_tradeProductParams.setTradedProductId(330304148080000L);
        l_tradeProductParams.setInstitutionCode("6D");
        l_tradeProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
        
        
        MutualFundProductParams l_mfProductParams =
            TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(3304148080001L);
        l_mfProductParams.setProductCode("0");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setInstitutionCode("6D");
        l_mfProductParams.setPlowbackProductDiv(WEB3MFPlowbackProductDivDef.PLOWBACK_PRODUCT);
        l_mfProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
        l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20071001","yyyyMMdd"));
        l_mfProductParams.setBuyStartDate(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
        l_mfProductParams.setBuyLimitDiv(WEB3BuyLimitDivDef.BUY_POSSIBLE);
        l_mfProductParams.setCurrencyCode(WEB3MFEstimatedPriceCurrencyCodeDef.A0);
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mfProductParams.setStockTypeBondType(WEB3StockTypeBondTypeDef.STOCK_TYPE);
        l_mfProductParams.setBuyConstantValue(11);
        l_mfProductParams.setFrgnAddBuyUnitAmt(new Long(7410));
        l_mfProductParams.setFrgnAddBuyMinAmt(new Long(8520));
        
        
        MutualFundTradedProductParams l_mfTradeProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        l_mfTradeProductParams.setTradedProductId(330304148080000L);
        l_mfTradeProductParams.setProductId(3304148080001L);
        l_mfTradeProductParams.setMarketId(3303L);
        l_mfTradeProductParams.setInstitutionCode("6D");
        
        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        l_marketParams.setMarketCode("0");
        l_marketParams.setInstitutionCode("6D");
        
        ExchangeRateParams l_exchangeRateParams =
            TestDBUtility.getExchangeRateRow();
        l_exchangeRateParams.setInstitutionCode("6D");
        l_exchangeRateParams.setCurrencyCode("A0");
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        l_branchParams.setBranchId(33381);

        AssetParams l_assetParams = TestDBUtility.getAssetRow();
        l_assetParams.setAccountId(l_subAccountParams.getAccountId());
        l_assetParams.setSubAccountId(l_subAccountParams.getSubAccountId());
        l_assetParams.setProductId(l_mfProductParams.getProductId());

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
             "validateEmergencyStop",
             new Class[] {WEB3MutualFundProduct.class, String.class},null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
             "validateFrgnMmfDoubleOrder",
             new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.gentrade.WEB3GentradeMainAccount",
             "isSpecialAccountEstablished",
             new Class[] {Date.class, SubAccount.class}, Boolean.TRUE);
        
        FrgnMmfExchangeRateParams l_params = new FrgnMmfExchangeRateParams();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundProduct",
            "getFrgnMmfExchangeRate",
            new Class[] {},l_params);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getMutualFundBuyTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Date.class, OrderTypeEnum.class}, new Double(213213));
        
        l_params.setTtSellingRate(11);
        l_params.setExecTimestamp(WEB3DateUtility.getDate("20070110","yyyyMMdd"));
        
        try
        {

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(
                WEB3DateUtility.getDate("20070110","yyyyMMdd"));
            ThreadLocalSystemAttributesRegistry.setAttribute(
                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME, new Timestamp(20070113000000L));
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_tradeProductParams);
            TestDBUtility.insertWithDel(l_mfProductParams);
            
            TestDBUtility.deleteAll(MutualFundTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_mfTradeProductParams);

            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.insertWithDelAndCommit(l_assetParams);
            
            TestDBUtility.deleteAll(ExchangeRateRow.TYPE);
            TestDBUtility.insertWithDel(l_exchangeRateParams); 
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "webbroker3.mf.WEB3MutualFundProductManager",
                 "getMutualFundTradedProduct",
                 new Class[] {Institution.class, String.class},
                 new WEB3MutualFundTradedProduct(l_mfTradeProductParams));
            
            WEB3MutualBuyInputServiceImpl l_impl = new WEB3MutualBuyInputServiceImpl();
            WEB3MutualBuyInputRequestForMock l_request = new WEB3MutualBuyInputRequestForMock();
            l_request.id = "3304148080001";
            WEB3MutualBuyInputResponse l_response = (WEB3MutualBuyInputResponse)l_impl.execute(l_request);
            
            assertEquals("7410", l_response.buyFrgnUnitAmt);
            assertEquals("8520", l_response.buyFrgnMinAmt);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    
   
    
    public void testExecuteC9()
    {
        final String STR_METHOD_NAME = "testExecuteC9()";
        log.entering(STR_METHOD_NAME);
        
        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setForeignSecAccOpenDiv(WEB3ForeignSecAccOpenDiv.OPEN);
        l_mainAccountParams.setAccountId(333812512203L);
       
        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("6D");
        l_institutionParams.setInstitutionId(63);

        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("6D");
        l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
        
        TradedProductParams l_tradeProductParams =
            TestDBUtility.getTradedProductRow();
        l_tradeProductParams.setProductId(3304148080001L);
        l_tradeProductParams.setTradedProductId(330304148080000L);
        l_tradeProductParams.setInstitutionCode("6D");
        l_tradeProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
        
        
        MutualFundProductParams l_mfProductParams =
            TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(3304148080001L);
        l_mfProductParams.setProductCode("0");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setInstitutionCode("6D");
        l_mfProductParams.setPlowbackProductDiv(WEB3MFPlowbackProductDivDef.PLOWBACK_PRODUCT);
        l_mfProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
        l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20071001","yyyyMMdd"));
        l_mfProductParams.setBuyStartDate(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
        l_mfProductParams.setBuyLimitDiv(WEB3BuyLimitDivDef.BUY_POSSIBLE);
        l_mfProductParams.setCurrencyCode(WEB3MFEstimatedPriceCurrencyCodeDef.A0);
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mfProductParams.setStockTypeBondType(WEB3StockTypeBondTypeDef.STOCK_TYPE);
        l_mfProductParams.setBuyConstantValue(11);
        l_mfProductParams.setFrgnAddBuyUnitAmt(new Long(7410));
        l_mfProductParams.setFrgnAddBuyMinAmt(new Long(8520));
        
        
        MutualFundTradedProductParams l_mfTradeProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        l_mfTradeProductParams.setTradedProductId(330304148080000L);
        l_mfTradeProductParams.setProductId(3304148080001L);
        l_mfTradeProductParams.setMarketId(3303L);
        l_mfTradeProductParams.setInstitutionCode("6D");
        
        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        l_marketParams.setMarketCode("0");
        l_marketParams.setInstitutionCode("6D");
        
        ExchangeRateParams l_exchangeRateParams =
            TestDBUtility.getExchangeRateRow();
        l_exchangeRateParams.setInstitutionCode("6D");
        l_exchangeRateParams.setCurrencyCode("A0");
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        l_branchParams.setBranchId(33381);

        AssetParams l_assetParams = TestDBUtility.getAssetRow();
        l_assetParams.setAccountId(l_subAccountParams.getAccountId());
        l_assetParams.setSubAccountId(l_subAccountParams.getSubAccountId());
        l_assetParams.setProductId(l_mfProductParams.getProductId());

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
             "validateEmergencyStop",
             new Class[] {WEB3MutualFundProduct.class, String.class},null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
             "validateFrgnMmfDoubleOrder",
             new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.gentrade.WEB3GentradeMainAccount",
             "isSpecialAccountEstablished",
             new Class[] {Date.class, SubAccount.class}, Boolean.TRUE);
        
        FrgnMmfExchangeRateParams l_params = new FrgnMmfExchangeRateParams();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundProduct",
            "getFrgnMmfExchangeRate",
            new Class[] {},l_params);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getMutualFundBuyTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Date.class, OrderTypeEnum.class}, new Double(213213));
        
        l_params.setTtSellingRate(11);
        l_params.setExecTimestamp(WEB3DateUtility.getDate("20070110","yyyyMMdd"));
        
        ProcessingResult processingResult = 
            ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003);
        OrderValidationResult orderResult = new OrderValidationResult(processingResult);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateAccountForTrading",
                new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                orderResult);
        
        
        try
        {

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(
                WEB3DateUtility.getDate("20070110","yyyyMMdd"));
            ThreadLocalSystemAttributesRegistry.setAttribute(
                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME, new Timestamp(20070113000000L));
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_tradeProductParams);
            TestDBUtility.insertWithDel(l_mfProductParams);
            
            TestDBUtility.deleteAll(MutualFundTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_mfTradeProductParams);

            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.insertWithDelAndCommit(l_assetParams);
            
            TestDBUtility.deleteAll(ExchangeRateRow.TYPE);
            TestDBUtility.insertWithDel(l_exchangeRateParams); 
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "webbroker3.mf.WEB3MutualFundProductManager",
                 "getMutualFundTradedProduct",
                 new Class[] {Institution.class, String.class},
                 new WEB3MutualFundTradedProduct(l_mfTradeProductParams));
            
            WEB3MutualBuyInputServiceImpl l_impl = new WEB3MutualBuyInputServiceImpl();
            WEB3MutualBuyInputRequestForMock l_request = new WEB3MutualBuyInputRequestForMock();
            l_request.id = "3304148080001";
            WEB3MutualBuyInputResponse l_response = (WEB3MutualBuyInputResponse)l_impl.execute(l_request);
            
            fail();
            
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            l_exc.printStackTrace();
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "------------------->ok");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);

    }
}
@

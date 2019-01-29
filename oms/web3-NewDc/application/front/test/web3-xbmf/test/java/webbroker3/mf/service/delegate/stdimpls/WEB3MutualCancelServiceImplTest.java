head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.11.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualCancelServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託取消サービス実装テストクラス(WEB3MutualCancelServiceImplTest)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/15 斉珂 (中訊) 新規作成
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundAssetImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3MfRecruitMqSendDivDef;
import webbroker3.common.define.WEB3SystemHandlingDivDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.FrgnMmfExchangeRateParams;
import webbroker3.gentrade.data.FrgnMmfExchangeRateRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundTradingTimeManagementForMock;
import webbroker3.mf.data.MutualFundInstCommissionParams;
import webbroker3.mf.data.MutualFundInstCommissionRow;
import webbroker3.mf.define.WEB3MFPlowbackProductDivDef;
import webbroker3.mf.message.WEB3MutualCancelCompleteRequest;
import webbroker3.mf.message.WEB3MutualCancelConfirmRequest;
import webbroker3.mf.message.WEB3MutualCancelConfirmResponse;
import webbroker3.mf.message.WEB3MutualSortKey;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualCancelServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualCancelServiceImplTest.class);
    
    public class WEB3MutualCancelConfirmRequestForMock 
        extends WEB3MutualCancelConfirmRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    
    public class WEB3MutualCancelCompleteRequestForMock
        extends WEB3MutualCancelCompleteRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    
    public WEB3MutualCancelServiceImplTest(String arg0)
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
    
    public void testValidateCancel01()
    {
        final String STR_METHOD_NAME = "testValidateCancel01()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
      
        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setSubAccountId(333812512203L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);
      
        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        l_institutionParams.setInstitutionId(63);
        
        
        MutualFundOrderUnitRow l_mfOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
        
        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("0");
        l_productParams.setProductType(ProductTypeEnum.EQUITY);
      
        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("N1");
        l_marketParams.setMarketId(3303L);
        l_marketParams.setInstitutionCode("6D");
      
        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setInstitutionCode("6D");
        l_branchParams.setInstitutionId(63);
        
        
        MutualFundTradedProductParams l_mfTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        
      
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setProductCode("0001000");
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
        "getAccountId",
            new Class[] {},new Long(333812512203L));
       
        List l_lis = new ArrayList();
        AssetParams l_assertParams = TestDBUtility.getAssetRow();
        l_assertParams.setTaxType(TaxTypeEnum.SPECIAL);
        MutualFundAssetImpl l_assert = new MutualFundAssetImpl(l_assertParams);
        l_lis.add(l_assert);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
        "getAssets",
            new Class[] {SubAccount.class, String.class},
            l_lis);
      
        Double l_dblQty = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
        "calcSellPossiblePositionQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblQty);
        
        Double l_dblValue = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
        "calcMarketValue",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblValue);
        
        Double l_dblProfitLoss = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
        "calcAppraisalProfitLoss",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblProfitLoss);
        
        ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
        OrderValidationResult l_res = new OrderValidationResult(processingResult);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "validateCancelOrder",
            new Class[] {SubAccount.class, CancelOrderSpec.class},
            l_res);
        

        MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(4003000900000000L);
        l_mfProductParams.setInstitutionCode("0D");
        l_mfProductParams.setProductCode("0001000");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setCurrencyCode("T0");
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
        ProductParams l_product = TestDBUtility.getProductRow();
        l_product.setProductId(4003000900000000L);
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
      
      
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
      
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_product);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            TestDBUtility.insertWithDel(l_mfOrderUnitParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            String l_strDate = "174120";
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime(l_strDate);
            
            WEB3MutualCancelConfirmRequestForMock l_request = 
                new WEB3MutualCancelConfirmRequestForMock();
            l_request.id = "50";
            WEB3MutualSortKey[] l_keys = new WEB3MutualSortKey[1];
            l_keys[0] = new WEB3MutualSortKey();
            l_keys[0].keyItem = "taxType";
            l_keys[0].ascDesc = "A";
            
            WEB3MutualCancelServiceImpl l_impl = 
                new WEB3MutualCancelServiceImpl();
            WEB3MutualCancelConfirmResponse l_response = l_impl.validateCancel(l_request);
            assertEquals("0", l_response.taxType);
        
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

    public void testValidateCancel02()
    {
        final String STR_METHOD_NAME = "testValidateCancel02()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
      
        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setSubAccountId(333812512203L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);
      
        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        l_institutionParams.setInstitutionId(63);
        
        
        MutualFundOrderUnitParams l_mfOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
        l_mfOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("0");
        l_productParams.setProductType(ProductTypeEnum.EQUITY);
      
        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("N1");
        l_marketParams.setMarketId(3303L);
        l_marketParams.setInstitutionCode("6D");
      
        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setInstitutionCode("6D");
        l_branchParams.setInstitutionId(63);
        
        
        MutualFundTradedProductParams l_mfTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        
      
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setProductCode("0001000");
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
        "getAccountId",
            new Class[] {},new Long(333812512203L));
       
        List l_lis = new ArrayList();
        AssetParams l_assertParams = TestDBUtility.getAssetRow();
        l_assertParams.setTaxType(TaxTypeEnum.SPECIAL);
        MutualFundAssetImpl l_assert = new MutualFundAssetImpl(l_assertParams);
        l_lis.add(l_assert);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
        "getAssets",
            new Class[] {SubAccount.class, String.class},
            l_lis);
      
        Double l_dblQty = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
        "calcSellPossiblePositionQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblQty);
        
        Double l_dblValue = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
        "calcMarketValue",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblValue);
        
        Double l_dblProfitLoss = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
        "calcAppraisalProfitLoss",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblProfitLoss);
        
        ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
        OrderValidationResult l_res = new OrderValidationResult(processingResult);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "validateCancelOrder",
            new Class[] {SubAccount.class, CancelOrderSpec.class},
            l_res);
        

        MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(4003000900000000L);
        l_mfProductParams.setInstitutionCode("0D");
        l_mfProductParams.setProductCode("0001000");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setCurrencyCode("T0");
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
        ProductParams l_product = TestDBUtility.getProductRow();
        l_product.setProductId(4003000900000000L);
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
      
      
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
      
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_product);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            TestDBUtility.insertWithDel(l_mfOrderUnitParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            String l_strDate = "174120";
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime(l_strDate);
            
            WEB3MutualCancelConfirmRequestForMock l_request = 
                new WEB3MutualCancelConfirmRequestForMock();
            l_request.id = "50";
            WEB3MutualSortKey[] l_keys = new WEB3MutualSortKey[1];
            l_keys[0] = new WEB3MutualSortKey();
            l_keys[0].keyItem = "taxType";
            l_keys[0].ascDesc = "A";
            
            WEB3MutualCancelServiceImpl l_impl = 
                new WEB3MutualCancelServiceImpl();
            WEB3MutualCancelConfirmResponse l_response = l_impl.validateCancel(l_request);
            assertEquals("2", l_response.taxType);
        
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
    
    public void testValidateCancel03()
    {
        final String STR_METHOD_NAME = "testValidateCancel03()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
      
        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setSubAccountId(333812512203L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);
      
        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        l_institutionParams.setInstitutionId(63);
        
        
        MutualFundOrderUnitParams l_mfOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
        l_mfOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("0");
        l_productParams.setProductType(ProductTypeEnum.EQUITY);
      
        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("N1");
        l_marketParams.setMarketId(3303L);
        l_marketParams.setInstitutionCode("6D");
      
        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setInstitutionCode("6D");
        l_branchParams.setInstitutionId(63);
        
        
        MutualFundTradedProductParams l_mfTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        
      
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setProductCode("0001000");
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
        "getAccountId",
            new Class[] {},new Long(333812512203L));
       
        List l_lis = new ArrayList();
        AssetParams l_assertParams = TestDBUtility.getAssetRow();
        l_assertParams.setTaxType(TaxTypeEnum.SPECIAL);
        MutualFundAssetImpl l_assert = new MutualFundAssetImpl(l_assertParams);
        l_lis.add(l_assert);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
        "getAssets",
            new Class[] {SubAccount.class, String.class},
            l_lis);
      
        Double l_dblQty = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
        "calcSellPossiblePositionQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblQty);
        
        Double l_dblValue = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
        "calcMarketValue",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblValue);
        
        Double l_dblProfitLoss = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
        "calcAppraisalProfitLoss",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblProfitLoss);
        
        ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
        OrderValidationResult l_res = new OrderValidationResult(processingResult);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "validateCancelOrder",
            new Class[] {SubAccount.class, CancelOrderSpec.class},
            l_res);
        

        MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(4003000900000000L);
        l_mfProductParams.setInstitutionCode("0D");
        l_mfProductParams.setProductCode("0001000");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setCurrencyCode("T0");
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
        ProductParams l_product = TestDBUtility.getProductRow();
        l_product.setProductId(4003000900000000L);
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
      
      
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
      
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_product);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            TestDBUtility.insertWithDel(l_mfOrderUnitParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            String l_strDate = "174120";
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime(l_strDate);
            
            WEB3MutualCancelConfirmRequestForMock l_request = 
                new WEB3MutualCancelConfirmRequestForMock();
            l_request.id = "50";
            WEB3MutualSortKey[] l_keys = new WEB3MutualSortKey[1];
            l_keys[0] = new WEB3MutualSortKey();
            l_keys[0].keyItem = "taxType";
            l_keys[0].ascDesc = "A";
            
            WEB3MutualCancelServiceImpl l_impl = 
                new WEB3MutualCancelServiceImpl();
            WEB3MutualCancelConfirmResponse l_response = l_impl.validateCancel(l_request);
            assertEquals("1", l_response.taxType);

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

    public void testValidateCancelCase1()
    {
        final String STR_METHOD_NAME = "testValidateCancelCase1()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
      
        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setSubAccountId(333812512203L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);
      
        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        l_institutionParams.setInstitutionId(63);
        
        
        MutualFundOrderUnitParams l_mfOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
        l_mfOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mfOrderUnitParams.setCalcConstantValue(123);
        
        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("0");
        l_productParams.setProductType(ProductTypeEnum.EQUITY);
      
        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("N1");
        l_marketParams.setMarketId(3303L);
        l_marketParams.setInstitutionCode("6D");
      
        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setInstitutionCode("6D");
        l_branchParams.setInstitutionId(63);
        
        
        MutualFundTradedProductParams l_mfTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        
      
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setProductCode("0001000");
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
        "getAccountId",
            new Class[] {},new Long(333812512203L));
       
        List l_lis = new ArrayList();
        AssetParams l_assertParams = TestDBUtility.getAssetRow();
        l_assertParams.setTaxType(TaxTypeEnum.SPECIAL);
        MutualFundAssetImpl l_assert = new MutualFundAssetImpl(l_assertParams);
        l_lis.add(l_assert);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
        "getAssets",
            new Class[] {SubAccount.class, String.class},
            l_lis);
      
        Double l_dblQty = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
        "calcSellPossiblePositionQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblQty);
        
        Double l_dblValue = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
        "calcMarketValue",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblValue);
        
        Double l_dblProfitLoss = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
        "calcAppraisalProfitLoss",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblProfitLoss);
        
        ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
        OrderValidationResult l_res = new OrderValidationResult(processingResult);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "validateCancelOrder",
            new Class[] {SubAccount.class, CancelOrderSpec.class},
            l_res);
        

        MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(4003000900000000L);
        l_mfProductParams.setInstitutionCode("0D");
        l_mfProductParams.setProductCode("0001000");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setCurrencyCode("T0");
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
        ProductParams l_product = TestDBUtility.getProductRow();
        l_product.setProductId(4003000900000000L);
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
      
      
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
      
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_product);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            TestDBUtility.insertWithDel(l_mfOrderUnitParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            String l_strDate = "174120";
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime(l_strDate);
            
            WEB3MutualCancelConfirmRequestForMock l_request = 
                new WEB3MutualCancelConfirmRequestForMock();
            l_request.id = "50";
            WEB3MutualSortKey[] l_keys = new WEB3MutualSortKey[1];
            l_keys[0] = new WEB3MutualSortKey();
            l_keys[0].keyItem = "taxType";
            l_keys[0].ascDesc = "A";
            
            WEB3MutualCancelServiceImpl l_impl = 
                new WEB3MutualCancelServiceImpl();
            WEB3MutualCancelConfirmResponse l_response = l_impl.validateCancel(l_request);
            assertNull(l_response.constantValue);
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

    public void testValidateCancelCase2()
    {
        final String STR_METHOD_NAME = "testValidateCancelCase2()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
      
        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setSubAccountId(333812512203L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);
      
        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        l_institutionParams.setInstitutionId(63);
        
        
        MutualFundOrderUnitParams l_mfOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
        l_mfOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN);
        l_mfOrderUnitParams.setCalcConstantValue(123);
        
        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("0");
        l_productParams.setProductType(ProductTypeEnum.EQUITY);
      
        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("N1");
        l_marketParams.setMarketId(3303L);
        l_marketParams.setInstitutionCode("6D");
      
        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setInstitutionCode("6D");
        l_branchParams.setInstitutionId(63);
        
        
        MutualFundTradedProductParams l_mfTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        
      
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setProductCode("0001000");
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
        "getAccountId",
            new Class[] {},new Long(333812512203L));
       
        List l_lis = new ArrayList();
        AssetParams l_assertParams = TestDBUtility.getAssetRow();
        l_assertParams.setTaxType(TaxTypeEnum.SPECIAL);
        MutualFundAssetImpl l_assert = new MutualFundAssetImpl(l_assertParams);
        l_lis.add(l_assert);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
        "getAssets",
            new Class[] {SubAccount.class, String.class},
            l_lis);
      
        Double l_dblQty = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
        "calcSellPossiblePositionQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblQty);
        
        Double l_dblValue = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
        "calcMarketValue",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblValue);
        
        Double l_dblProfitLoss = new Double("2.0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
        "calcAppraisalProfitLoss",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            l_dblProfitLoss);
        
        ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
        OrderValidationResult l_res = new OrderValidationResult(processingResult);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "validateCancelOrder",
            new Class[] {SubAccount.class, CancelOrderSpec.class},
            l_res);
        

        MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(4003000900000000L);
        l_mfProductParams.setInstitutionCode("0D");
        l_mfProductParams.setProductCode("0001000");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setCurrencyCode("T0");
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
        ProductParams l_product = TestDBUtility.getProductRow();
        l_product.setProductId(4003000900000000L);
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
      
      
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
      
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_product);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            TestDBUtility.insertWithDel(l_mfOrderUnitParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            String l_strDate = "174120";
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime(l_strDate);
            
            WEB3MutualCancelConfirmRequestForMock l_request = 
                new WEB3MutualCancelConfirmRequestForMock();
            l_request.id = "50";
            WEB3MutualSortKey[] l_keys = new WEB3MutualSortKey[1];
            l_keys[0] = new WEB3MutualSortKey();
            l_keys[0].keyItem = "taxType";
            l_keys[0].ascDesc = "A";
            
            WEB3MutualCancelServiceImpl l_impl = 
                new WEB3MutualCancelServiceImpl();
            WEB3MutualCancelConfirmResponse l_response = l_impl.validateCancel(l_request);
            assertEquals("123", l_response.constantValue);
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
    
    // 【投資信託】静銀TM対応（外貨建MMF）（管理者機@能） 2007/04/12
    public void testValidateCancelc1()
    {
        final String STR_METHOD_NAME = "testValidateCancelc1()";
        log.entering(STR_METHOD_NAME);
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        l_branchParams.setBranchCode("381");
        l_branchParams.setBranchId(33381L);
        
        BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
        l_branchPreferencesParams.setBranchId(33381L);
        l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.MF_RECRUIT_MQ_SEND_DIV);
        l_branchPreferencesParams.setNameSerialNo(1);
        l_branchPreferencesParams.setValue(WEB3MfRecruitMqSendDivDef.EXCEPT);
        
        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("0D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setAccountId(333812512203L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);
        l_mainAccountParams.setBranchId(33381L);
        l_mainAccountParams.setBranchCode("381");
        
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        l_institutionParams.setInstitutionId(63);
        
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setProductCode("0");
        
        AssetParams l_assetParams = TestDBUtility.getAssetRow();
        
        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setInstitutionCode("0D");
        l_productParams.setProductId(1006169090018L);
        l_productParams.setProductType(ProductTypeEnum.EQUITY);
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(400003000100000L);
        l_tradedProductParams.setInstitutionCode("0D");
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setProductId(1006169090018L);
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");
        
        MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(1006169090018L);
        l_mfProductParams.setInstitutionCode("0D");
        l_mfProductParams.setProductCode("0");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setCurrencyCode("T0");
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mfProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
        l_mfProductParams.setBuyStartDate(WEB3DateUtility.getDate("20040516","yyyyMMdd"));
        l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20080916","yyyyMMdd"));
        l_mfProductParams.setPlowbackProductDiv(WEB3MFPlowbackProductDivDef.PLOWBACK_PRODUCT);
        l_mfProductParams.setSellSwtStartDate(WEB3DateUtility.getDate("20040516","yyyyMMdd"));
        l_mfProductParams.setSellSwtEndDate(WEB3DateUtility.getDate("20080516","yyyyMMdd"));
        l_mfProductParams.setSwtPossibleGroupId(1);
        l_mfProductParams.setSwtUnitQty(123);
        
        MutualFundOrderUnitParams l_mfOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
        l_mfOrderUnitParams.setAccountId(333812512205L);
        l_mfOrderUnitParams.setSubAccountId(33381251220303L);
        l_mfOrderUnitParams.setProductId(1006169090018L);
        l_mfOrderUnitParams.setOrderType(OrderTypeEnum.MF_RECRUIT);
        l_mfOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_mfOrderUnitParams.setBranchId(33381L);
        l_mfOrderUnitParams.setOrderId(50);
        
        MutualFundTradedProductParams l_mfTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        l_mfTradedProductParams.setInstitutionCode("0D");
        l_mfTradedProductParams.setProductId(1006169090018L);
        l_mfTradedProductParams.setMarketId(3303L);
        
        MutualFundInstCommissionParams l_mfInstCommission = new MutualFundInstCommissionParams();
        l_mfInstCommission.setInstitutionCode("0D");
        l_mfInstCommission.setProductCode("0");
        l_mfInstCommission.setDealDiv("1");
        l_mfInstCommission.setOrderChanel("0");
        l_mfInstCommission.setValidDateFrom(WEB3DateUtility.getDate("20040516","yyyyMMdd"));
        l_mfInstCommission.setRegistDiv("0");
        l_mfInstCommission.setCreatedTimestamp(WEB3DateUtility.getDate("20040516","yyyyMMdd"));
        l_mfInstCommission.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040516","yyyyMMdd"));
        
        FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = TestDBUtility.getFrgnMmfExchangeRateRow();
        l_frgnMmfExchangeRateParams.setCurrencyCode("T0");
        l_frgnMmfExchangeRateParams.setInstitutionCode("0D");
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},"0");
        
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

         ProcessingResult processingResult = ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00275);
         OrderValidationResult l_validationResult = new OrderValidationResult(processingResult);
         TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "webbroker3.gentrade.WEB3GentradeOrderValidator",
                 "validateAccountForTrading",
                  new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                  l_validationResult);

         WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
         l_result.setResultFlg(true);
         TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                 "validateTradingPower",
                 new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                 l_result);
         
         ProcessingResult processingResult2 = ProcessingResult.newSuccessResultInstance();
         OrderValidationResult l_res = new OrderValidationResult(processingResult2);
         TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.mf.WEB3MutualFundOrderManager",
             "validateCancelOrder",
             new Class[] {SubAccount.class, CancelOrderSpec.class},
             l_res);
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(AssetRow.TYPE);
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(MutualFundTradedProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(MutualFundInstCommissionRow.TYPE);
            TestDBUtility.deleteAll(FrgnMmfExchangeRateRow.TYPE);
            TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);
            
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_assetParams);
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_mfInstCommission);
            TestDBUtility.insertWithDel(l_frgnMmfExchangeRateParams);
            TestDBUtility.insertWithDel(l_mfOrderUnitParams);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            l_ex.printStackTrace();
            fail();
        }
        
        try
        {
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            String l_strDate = "174120";
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime(l_strDate);
            
            WEB3MutualCancelConfirmRequestForMock l_request = 
                new WEB3MutualCancelConfirmRequestForMock();
            l_request.id = "50";
            WEB3MutualSortKey[] l_keys = new WEB3MutualSortKey[1];
            l_keys[0] = new WEB3MutualSortKey();
            l_keys[0].keyItem = "taxType";
            l_keys[0].ascDesc = "A";
            
            WEB3MutualCancelServiceImpl l_impl =  new WEB3MutualCancelServiceImpl();
            l_impl.validateCancel(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00275, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            l_ex.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
  }
    
    public void testSubmitCancellc1()
    {
        final String STR_METHOD_NAME = "testSubmitCancellc1()";
        log.entering(STR_METHOD_NAME);
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        l_branchParams.setBranchCode("381");
        l_branchParams.setBranchId(33381L);
        
        BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
        l_branchPreferencesParams.setBranchId(33381L);
        l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.MF_RECRUIT_MQ_SEND_DIV);
        l_branchPreferencesParams.setNameSerialNo(1);
        l_branchPreferencesParams.setValue(WEB3MfRecruitMqSendDivDef.EXCEPT);
        
        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("0D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setAccountId(333812512203L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);
        l_mainAccountParams.setBranchId(33381L);
        l_mainAccountParams.setBranchCode("381");
        
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        l_institutionParams.setInstitutionId(63);
        
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setProductCode("0");
        
        AssetParams l_assetParams = TestDBUtility.getAssetRow();
        
        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setInstitutionCode("0D");
        l_productParams.setProductId(1006169090018L);
        l_productParams.setProductType(ProductTypeEnum.EQUITY);
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(400003000100000L);
        l_tradedProductParams.setInstitutionCode("0D");
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setProductId(1006169090018L);
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");
        
        MutualFundProductParams l_mfProductParams = TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(1006169090018L);
        l_mfProductParams.setInstitutionCode("0D");
        l_mfProductParams.setProductCode("0");
        l_mfProductParams.setProductIssueCode("0");
        l_mfProductParams.setCurrencyCode("T0");
        l_mfProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mfProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
        l_mfProductParams.setBuyStartDate(WEB3DateUtility.getDate("20040516","yyyyMMdd"));
        l_mfProductParams.setBuyEndDate(WEB3DateUtility.getDate("20080916","yyyyMMdd"));
        l_mfProductParams.setPlowbackProductDiv(WEB3MFPlowbackProductDivDef.PLOWBACK_PRODUCT);
        l_mfProductParams.setSellSwtStartDate(WEB3DateUtility.getDate("20040516","yyyyMMdd"));
        l_mfProductParams.setSellSwtEndDate(WEB3DateUtility.getDate("20080516","yyyyMMdd"));
        l_mfProductParams.setSwtPossibleGroupId(1);
        l_mfProductParams.setSwtUnitQty(123);
        
        MutualFundOrderUnitParams l_mfOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
        l_mfOrderUnitParams.setAccountId(333812512205L);
        l_mfOrderUnitParams.setSubAccountId(33381251220303L);
        l_mfOrderUnitParams.setProductId(1006169090018L);
        l_mfOrderUnitParams.setOrderType(OrderTypeEnum.MF_RECRUIT);
        l_mfOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_mfOrderUnitParams.setBranchId(33381L);
        l_mfOrderUnitParams.setOrderId(50);
        
        MutualFundTradedProductParams l_mfTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        l_mfTradedProductParams.setInstitutionCode("0D");
        l_mfTradedProductParams.setProductId(1006169090018L);
        l_mfTradedProductParams.setMarketId(3303L);
        
        MutualFundInstCommissionParams l_mfInstCommission = new MutualFundInstCommissionParams();
        l_mfInstCommission.setInstitutionCode("0D");
        l_mfInstCommission.setProductCode("0");
        l_mfInstCommission.setDealDiv("1");
        l_mfInstCommission.setOrderChanel("0");
        l_mfInstCommission.setValidDateFrom(WEB3DateUtility.getDate("20040516","yyyyMMdd"));
        l_mfInstCommission.setRegistDiv("0");
        l_mfInstCommission.setCreatedTimestamp(WEB3DateUtility.getDate("20040516","yyyyMMdd"));
        l_mfInstCommission.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040516","yyyyMMdd"));
        
        FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = TestDBUtility.getFrgnMmfExchangeRateRow();
        l_frgnMmfExchangeRateParams.setCurrencyCode("T0");
        l_frgnMmfExchangeRateParams.setInstitutionCode("0D");
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},"0");
        
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
         
         ProcessingResult processingResult =ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00275);
         OrderValidationResult l_validationResult = new OrderValidationResult(processingResult);
         TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "webbroker3.gentrade.WEB3GentradeOrderValidator",
                 "validateAccountForTrading",
                  new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                  l_validationResult);

         WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
         l_result.setResultFlg(true);
         TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                 "validateTradingPower",
                 new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                 l_result);
         
         ProcessingResult processingResult1 = ProcessingResult.newSuccessResultInstance();
         OrderValidationResult l_validationResult1 = new OrderValidationResult(processingResult1);
         TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "webbroker3.gentrade.WEB3GentradeOrderValidator",
                 "validateTradingPassword",
                  new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                  l_validationResult1);
        
         ProcessingResult processingResult2 = ProcessingResult.newSuccessResultInstance();
         OrderValidationResult l_res = new OrderValidationResult(processingResult2);
         TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.mf.WEB3MutualFundOrderManager",
             "validateCancelOrder",
             new Class[] {SubAccount.class, CancelOrderSpec.class},
             l_res);
         
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(AssetRow.TYPE);
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(MutualFundTradedProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(MutualFundInstCommissionRow.TYPE);
            TestDBUtility.deleteAll(FrgnMmfExchangeRateRow.TYPE);
            TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);
            
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_assetParams);
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mfTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_mfInstCommission);
            TestDBUtility.insertWithDel(l_frgnMmfExchangeRateParams);
            TestDBUtility.insertWithDel(l_mfOrderUnitParams);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            l_ex.printStackTrace();
            fail();
        }
        
        try
        {
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            String l_strDate = "174120";
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime(l_strDate);
            
            WEB3MutualCancelCompleteRequestForMock l_request = 
                new WEB3MutualCancelCompleteRequestForMock();
            l_request.id = "50";
            l_request.password = "";
            l_request.checkDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            WEB3MutualSortKey[] l_keys = new WEB3MutualSortKey[1];
            l_keys[0] = new WEB3MutualSortKey();
            l_keys[0].keyItem = "taxType";
            l_keys[0].ascDesc = "A";
            
            WEB3MutualCancelServiceImpl l_impl =  new WEB3MutualCancelServiceImpl();
            l_impl.submitCancel(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00275, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            l_ex.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
  }
}
@

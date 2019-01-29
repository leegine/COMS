head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.10.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualSwitchingServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ClaimDivDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SystemHandlingDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.FrgnMmfExchangeRateParams;
import webbroker3.gentrade.data.FrgnMmfExchangeRateRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.data.MutualFundInstCommissionParams;
import webbroker3.mf.data.MutualFundInstCommissionRow;
import webbroker3.mf.define.WEB3MFPlowbackProductDivDef;
import webbroker3.mf.message.WEB3MutualSwitchingCompleteRequest;
import webbroker3.mf.message.WEB3MutualSwitchingConfirmRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductRow;

public class WEB3MutualSwitchingServiceImplTest extends TestBaseForMock
{
    WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSwitchingServiceImplTest.class);
    
    public WEB3MutualSwitchingServiceImplTest(String arg0)
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
    
    public class WEB3MutualSwitchingConfirmRequestForMock 
    extends WEB3MutualSwitchingConfirmRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "WEB3MutualSwitchingConfirmRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    
    public class WEB3MutualSwitchingCompleteRequestForMock 
    extends WEB3MutualSwitchingCompleteRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "WEB3MutualSwitchingCompleteRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    
    public void testValidateSwitchingC1()
    {
        final String STR_METHOD_NAME = "testValidateSwitchingC1";
        log.entering(STR_METHOD_NAME);
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        l_branchParams.setBranchCode("381");
        l_branchParams.setBranchId(33381L);
        
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
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
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
            
            TestDBUtility.insertWithDel(l_branchParams);
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
            
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            l_ex.printStackTrace();
            fail();
        }
        
        try
        {
            WEB3MutualSwitchingConfirmRequestForMock l_request = new WEB3MutualSwitchingConfirmRequestForMock();
            l_request.mutualProductCode = "0";
            l_request.switchingProductCode = "0";
            l_request.orderedDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            l_request.id = "1001";
            l_request.mutualOrderQuantity = "123";       
            l_request.specifyDiv = WEB3SellDivDef.ALL_DESIGNATE;
            l_request.sellBuyDiv = WEB3ClaimDivDef.BUY;
            l_request.switchingTaxType = WEB3TaxTypeSpecialDef.NORMAL;
            
            WEB3MutualSwitchingServiceImpl l_impl = new WEB3MutualSwitchingServiceImpl();
            l_impl.validateSwitching(l_request);
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
    
    public void testValidateSwitchingC2()
    {
        final String STR_METHOD_NAME = "testValidateSwitchingC2";
        log.entering(STR_METHOD_NAME);
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        l_branchParams.setBranchCode("381");
        l_branchParams.setBranchId(33381L);
        
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
        l_tradedProductParams.setTradedProductId(1006160060005L);
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

         WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
         l_result.setResultFlg(true);
         TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                 "validateTradingPower",
                 new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                 l_result);
         
         TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "webbroker3.mf.WEB3MutualFundBizLogicProvider",
                 "calcEstimatedBuyQty",
                 new Class[] {WEB3MutualFundProduct.class, double.class},
                 new Double(110));

         ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
         NewOrderValidationResult l_newOrderValidationResult = new NewOrderValidationResult(processingResult);
         TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.mf.WEB3MutualFundOrderManager",
             "validateNewOrder",
             new Class[] {
                 SubAccount.class,
                 WEB3MutualFundProduct.class,
                 double.class, String.class,
                 String.class, String.class,
                 WEB3MutualFundProduct.class,
                 TaxTypeEnum.class,String.class}, l_newOrderValidationResult);
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
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
            
            TestDBUtility.insertWithDel(l_branchParams);
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
            
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            l_ex.printStackTrace();
            fail();
        }
        
        try
        {
            WEB3MutualSwitchingConfirmRequestForMock l_request = new WEB3MutualSwitchingConfirmRequestForMock();
            l_request.mutualProductCode = "0";
            l_request.switchingProductCode = "0";
            l_request.orderedDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            l_request.id = "1001";
            l_request.mutualOrderQuantity = "123";       
            l_request.specifyDiv = WEB3SellDivDef.ALL_DESIGNATE;
            l_request.sellBuyDiv = WEB3ClaimDivDef.BUY;
            l_request.switchingTaxType = WEB3TaxTypeSpecialDef.NORMAL;
            
            WEB3MutualSwitchingServiceImpl l_impl = new WEB3MutualSwitchingServiceImpl();
            l_impl.validateSwitching(l_request);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            l_ex.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }    
    
    public void testSubmitSwitchingC1()
    {
        final String STR_METHOD_NAME = "testSubmitSwitchingC1";
        log.entering(STR_METHOD_NAME);
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        l_branchParams.setBranchCode("381");
        l_branchParams.setBranchId(33381L);
        
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
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
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
            
            TestDBUtility.insertWithDel(l_branchParams);
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
            
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            l_ex.printStackTrace();
            fail();
        }
        
        try
        {
            WEB3MutualSwitchingCompleteRequestForMock l_request = new WEB3MutualSwitchingCompleteRequestForMock();
            l_request.mutualProductCode = "0";
            l_request.switchingProductCode = "0";
            l_request.orderedDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            l_request.id = "1001";
            l_request.mutualOrderQuantity = "123";       
            l_request.specifyDiv = WEB3SellDivDef.ALL_DESIGNATE;
            l_request.sellBuyDiv = WEB3ClaimDivDef.BUY;
            l_request.switchingTaxType = WEB3TaxTypeSpecialDef.NORMAL;
            
            WEB3MutualSwitchingServiceImpl l_impl = new WEB3MutualSwitchingServiceImpl();
            l_impl.submitSwitching(l_request);
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
    
    public void testSubmitSwitchingC2()
    {
        final String STR_METHOD_NAME = "testSubmitSwitchingC2";
        log.entering(STR_METHOD_NAME);
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        l_branchParams.setBranchCode("381");
        l_branchParams.setBranchId(33381L);
        
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
        l_tradedProductParams.setTradedProductId(1006160060005L);
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

         WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
         l_result.setResultFlg(true);
         TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                 "validateTradingPower",
                 new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                 l_result);
         
         TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "webbroker3.mf.WEB3MutualFundBizLogicProvider",
                 "calcEstimatedBuyQty",
                 new Class[] {WEB3MutualFundProduct.class, double.class},
                 new Double(110));
         
         ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
         OrderSubmissionResult l_orderSubmissionResult = new OrderSubmissionResult(processingResult);
         TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "webbroker3.mf.WEB3MutualFundOrderManager",
                 "submitNewOrder",
                 new Class[] {
                         SubAccount.class,
                         ProductTypeEnum.class,
                         NewOrderSpec.class,
                         long.class,
                         String.class,
                         boolean.class},
                l_orderSubmissionResult);
         
         NewOrderValidationResult l_newOrderValidationResult = new NewOrderValidationResult(processingResult);
         TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.mf.WEB3MutualFundOrderManager",
             "validateNewOrder",
             new Class[] {
                 SubAccount.class,
                 WEB3MutualFundProduct.class,
                 double.class, String.class,
                 String.class, String.class,
                 WEB3MutualFundProduct.class,
                 TaxTypeEnum.class,String.class}, l_newOrderValidationResult);
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
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
            
            TestDBUtility.insertWithDel(l_branchParams);
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
            
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            l_ex.printStackTrace();
            fail();
        }
        
        try
        {
            WEB3MutualSwitchingCompleteRequestForMock l_request = new WEB3MutualSwitchingCompleteRequestForMock();
            l_request.mutualProductCode = "0";
            l_request.switchingProductCode = "0";
            l_request.orderedDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            l_request.id = "1001";
            l_request.orderId = "1001";
            l_request.mutualOrderQuantity = "123";       
            l_request.specifyDiv = WEB3SellDivDef.ALL_DESIGNATE;
            l_request.sellBuyDiv = WEB3ClaimDivDef.BUY;
            l_request.switchingTaxType = WEB3TaxTypeSpecialDef.NORMAL;
            
            WEB3MutualSwitchingServiceImpl l_impl = new WEB3MutualSwitchingServiceImpl();
            l_impl.submitSwitching(l_request);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            l_ex.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }   
}@

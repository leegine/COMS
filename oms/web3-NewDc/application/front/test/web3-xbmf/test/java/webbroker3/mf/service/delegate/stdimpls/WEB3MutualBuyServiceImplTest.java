head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.09.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualBuyServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託買付注文サービス実装クラス(WEB3MutualBuyServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/25 徐大方 (中訊) 新規作成
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.ordersubmitter.io.MutualFundNewOrderSpec;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuyLimitDivDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.define.WEB3SystemHandlingDivDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mf.WEB3MutualFundEstimatedPrice;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.define.WEB3MFAccountDivDef;
import webbroker3.mf.define.WEB3MFPlowbackProductDivDef;
import webbroker3.mf.message.WEB3MutualBuyCompleteRequest;
import webbroker3.mf.message.WEB3MutualBuyConfirmRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 投信買付注文サービスImpl<BR>
 * 投資信託買付注文サービス実装クラス
 *
 * @@author 徐大方(中訊)
 * @@version 1.0
 */
public class WEB3MutualBuyServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBuyServiceImplTest.class);

    public WEB3MutualBuyServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestDBUtility.deleteAll(MainAccountRow.TYPE);
        TestDBUtility.deleteAll(SubAccountRow.TYPE);
        TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);
        TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
        TestDBUtility.deleteAll(InstitutionRow.TYPE);
        TestDBUtility.deleteAll(TradingTimeRow.TYPE);
        TestDBUtility.deleteAll(TraderRow.TYPE);
        TestDBUtility.deleteAll(MarketRow.TYPE);
        TestDBUtility.deleteAll(BranchRow.TYPE);
        TestDBUtility.deleteAll(ProductRow.TYPE);
        TestDBUtility.deleteAll(MutualFundTradedProductRow.TYPE);
        TestDBUtility.deleteAll(TradedProductRow.TYPE);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512246L));
        
        ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
        NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "validateNewOrder",
            new Class[] {
                SubAccount.class,
                WEB3MutualFundProduct.class,
                double.class, String.class,
                String.class, String.class,
                WEB3MutualFundProduct.class,
                TaxTypeEnum.class,String.class}, l_result);
        
        LoginInfoImplForMock l_loginInfo = new LoginInfoImplForMock();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfo);

        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setProductCode("12");
        l_clendarContext.setTradingTimeType("01");
        l_clendarContext.setOrderAcceptProduct("01");
        l_clendarContext.setBizDateType("1");

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_clendarContext);

        Calendar l_calendar = Calendar.getInstance();
        l_calendar.set(Calendar.HOUR_OF_DAY,15);
        l_calendar.set(Calendar.MINUTE,00);
        l_calendar.set(Calendar.SECOND,01);

        Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundProductManager",
            "getExecutedDate",
            new Class[] {
                Institution.class,
                String.class,
                Date.class}, l_tsBizDate);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundProductManager",
            "getDeliveryDate",
            new Class[] {
                Institution.class,
                String.class,
                boolean.class,
                Date.class}, l_tsBizDate);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getSessionProperty",
            new Class[] {String.class}, "110");
        WEB3MutualFundEstimatedPrice l_price = new WEB3MutualFundEstimatedPrice();
        l_price.setEstimatedPrice(111);
        l_price.setEstimatedQty(100);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "calcEstimateDeliveryAmount",
            new Class[] {
                SubAccount.class,
                WEB3MutualFundProduct.class,
                WEB3MutualFundProduct.class,
                String.class,
                double.class,
                String.class,
                String.class,
                String.class,
                String.class,
                String.class,
                Date.class}, l_price);
        WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();
        l_tradingPowerResult.setResultFlg(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
            "validateTradingPower",
            new Class[] {
                WEB3GentradeSubAccount.class,
                Object[].class,
                Object[].class,
                OrderTypeEnum.class,
                boolean.class}, l_tradingPowerResult);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    
    public class WEB3MutualBuyConfirmRequestForMock 
        extends WEB3MutualBuyConfirmRequest
    {
        public void validate() 
        {
            final String STR_METHOD_NAME = "WEB3MutualBuyConfirmRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    
    public class WEB3MutualBuyCompleteRequestForMock 
        extends WEB3MutualBuyCompleteRequest
    {
        public void validate() 
        {
            final String STR_METHOD_NAME = "WEB3MutualBuyCompleteRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
}
    
    

    public void testValidateBuyOrderCase1()
    {
        final String STR_METHOD_NAME = "testValidateBuyOrderCase1()";
        log.entering(STR_METHOD_NAME);

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
        l_mutualFundOrderUnitParams.setAccountId(333812512246L);
        l_mutualFundOrderUnitParams.setSubAccountId(33381251220301L);
        Date l_dateBizDate =
            GtlUtils.getTradingSystem().getBizDate();
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
        String l_strBizDate = l_format.format(l_dateBizDate);
        l_mutualFundOrderUnitParams.setBizDate(l_strBizDate);
        l_mutualFundOrderUnitParams.setExecStatus("1");
        l_mutualFundOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
        l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductId(4003000900000000L);
        l_mutualFundProductParams.setNewBuyMinQty(111);
        l_mutualFundProductParams.setNewBuyUnitQty(111);
        l_mutualFundProductParams.setBuyStartDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
        l_mutualFundProductParams.setBuyEndDate(WEB3DateUtility.getDate("20070707","yyyyMMdd"));
        l_mutualFundProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
        l_mutualFundProductParams.setBuyLimitDiv(WEB3BuyLimitDivDef.BUY_POSSIBLE);
        l_mutualFundProductParams.setPlowbackProductDiv(WEB3MFPlowbackProductDivDef.PLOWBACK_PRODUCT);
        
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setProductId(4003000900000000L);
        
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setProductCode("12");
        TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams1.setInstitutionCode("0D");
        l_tradingTimeParams1.setBranchCode("381");
        l_tradingTimeParams1.setTradingTimeType("01");
        l_tradingTimeParams1.setBizDateType("1");
        l_tradingTimeParams1.setMarketCode("SP");
        l_tradingTimeParams1.setProductCode("0001000");
        
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(4003000900000000L);
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setInstitutionCode("0D");
        l_tradedProductParams.setProductId(4003000900000000L);
        l_tradedProductParams.setTradedProductId(400003000100000L);
        
        try
        {
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_traderParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        WEB3MutualBuyServiceImpl l_impl = new WEB3MutualBuyServiceImpl();
        WEB3MutualBuyConfirmRequest l_request = new WEB3MutualBuyConfirmRequest();
        l_request.mutualProductCode = "0001000";
        l_request.specifyDiv = WEB3SellDivDef.COUNT_DESIGNATE;
        l_request.mutualOrderQuantity = "111";
        l_request.taxType = WEB3MFAccountDivDef.OTHER;
        l_request.settleDiv = WEB3SettlementDivDef.JAPANESE_CURRENCY;
        try
        {
            l_request.orderedDate = new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp()).roll(1);
            System.out.println(l_request.orderedDate);
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            l_impl.execute(l_request);
            WEB3MockObjectParamsValue l_value = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class});
            Object[] l_object = new Object[5];
            l_object = l_value.getFirstCalled();
            Object[] l_object1 = new Object[1];
            l_object1 = (Object[]) l_object[2];
            MutualFundNewOrderSpec l_spec = (MutualFundNewOrderSpec)l_object1[0];
            assertEquals(TaxTypeEnum.UNDEFINED, l_spec.getTaxType());
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public void testValidateBuyOrderCase2()
    {
        final String STR_METHOD_NAME = "testValidateBuyOrderCase2()";
        log.entering(STR_METHOD_NAME);

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
        l_mutualFundOrderUnitParams.setAccountId(333812512246L);
        l_mutualFundOrderUnitParams.setSubAccountId(33381251220301L);
        Date l_dateBizDate =
            GtlUtils.getTradingSystem().getBizDate();
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
        String l_strBizDate = l_format.format(l_dateBizDate);
        l_mutualFundOrderUnitParams.setBizDate(l_strBizDate);
        l_mutualFundOrderUnitParams.setExecStatus("1");
        l_mutualFundOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
        l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductId(4003000900000000L);
        l_mutualFundProductParams.setNewBuyMinQty(111);
        l_mutualFundProductParams.setNewBuyUnitQty(111);
        l_mutualFundProductParams.setBuyStartDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
        l_mutualFundProductParams.setBuyEndDate(WEB3DateUtility.getDate("20070707","yyyyMMdd"));
        l_mutualFundProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
        l_mutualFundProductParams.setBuyLimitDiv(WEB3BuyLimitDivDef.BUY_POSSIBLE);
        l_mutualFundProductParams.setPlowbackProductDiv(WEB3MFPlowbackProductDivDef.PLOWBACK_PRODUCT);
        
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setProductId(4003000900000000L);
        
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setProductCode("12");
        TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams1.setInstitutionCode("0D");
        l_tradingTimeParams1.setBranchCode("381");
        l_tradingTimeParams1.setTradingTimeType("01");
        l_tradingTimeParams1.setBizDateType("1");
        l_tradingTimeParams1.setMarketCode("SP");
        l_tradingTimeParams1.setProductCode("0001000");
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(4003000900000000L);
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setInstitutionCode("0D");
        l_tradedProductParams.setProductId(4003000900000000L);
        l_tradedProductParams.setTradedProductId(400003000100000L);
        try
        {
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_traderParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        WEB3MutualBuyServiceImpl l_impl = new WEB3MutualBuyServiceImpl();
        WEB3MutualBuyConfirmRequest l_request = new WEB3MutualBuyConfirmRequest();
        l_request.mutualProductCode = "0001000";
        l_request.specifyDiv = WEB3SellDivDef.COUNT_DESIGNATE;
        l_request.mutualOrderQuantity = "111";
        l_request.taxType = WEB3MFAccountDivDef.NORMAL;
        l_request.settleDiv = WEB3SettlementDivDef.JAPANESE_CURRENCY;
        try
        {
            l_request.orderedDate = new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp()).roll(1);
            System.out.println(l_request.orderedDate);
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            l_impl.execute(l_request);
            WEB3MockObjectParamsValue l_value = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class});
            Object[] l_object = new Object[5];
            l_object = l_value.getFirstCalled();
            Object[] l_object1 = new Object[1];
            l_object1 = (Object[]) l_object[2];
            MutualFundNewOrderSpec l_spec = (MutualFundNewOrderSpec)l_object1[0];
            assertEquals(TaxTypeEnum.NORMAL, l_spec.getTaxType());
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public void testSubmitBuyOrderCase1()
    {
        final String STR_METHOD_NAME = "testSubmitBuyOrderCase1()";
        log.entering(STR_METHOD_NAME);

        ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
        OrderSubmissionResult l_result = new OrderSubmissionResult(processingResult);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "submitNewOrder",
            new Class[] {
                SubAccount.class,
                ProductTypeEnum.class,
                NewOrderSpec.class,
                long.class,
                String.class,
                boolean.class}, l_result);

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
        l_mutualFundOrderUnitParams.setAccountId(333812512246L);
        l_mutualFundOrderUnitParams.setSubAccountId(33381251220301L);
        Date l_dateBizDate =
            GtlUtils.getTradingSystem().getBizDate();
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
        String l_strBizDate = l_format.format(l_dateBizDate);
        l_mutualFundOrderUnitParams.setBizDate(l_strBizDate);
        l_mutualFundOrderUnitParams.setExecStatus("1");
        l_mutualFundOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
        l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductId(4003000900000000L);
        l_mutualFundProductParams.setNewBuyMinQty(111);
        l_mutualFundProductParams.setNewBuyUnitQty(111);
        l_mutualFundProductParams.setBuyStartDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
        l_mutualFundProductParams.setBuyEndDate(WEB3DateUtility.getDate("20070707","yyyyMMdd"));
        l_mutualFundProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
        l_mutualFundProductParams.setBuyLimitDiv(WEB3BuyLimitDivDef.BUY_POSSIBLE);
        l_mutualFundProductParams.setPlowbackProductDiv(WEB3MFPlowbackProductDivDef.PLOWBACK_PRODUCT);
        
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setProductId(4003000900000000L);
        
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setProductCode("12");
        TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams1.setInstitutionCode("0D");
        l_tradingTimeParams1.setBranchCode("381");
        l_tradingTimeParams1.setTradingTimeType("01");
        l_tradingTimeParams1.setBizDateType("1");
        l_tradingTimeParams1.setMarketCode("SP");
        l_tradingTimeParams1.setProductCode("0001000");
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(4003000900000000L);
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setInstitutionCode("0D");
        l_tradedProductParams.setProductId(4003000900000000L);
        l_tradedProductParams.setTradedProductId(400003000100000L);
        try
        {
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_traderParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        WEB3MutualBuyServiceImpl l_impl = new WEB3MutualBuyServiceImpl();
        WEB3MutualBuyCompleteRequest l_request = new WEB3MutualBuyCompleteRequest();
        l_request.mutualProductCode = "0001000";
        l_request.specifyDiv = WEB3SellDivDef.COUNT_DESIGNATE;
        l_request.mutualOrderQuantity = "111";
        l_request.taxType = WEB3MFAccountDivDef.OTHER;
        l_request.settleDiv = WEB3SettlementDivDef.JAPANESE_CURRENCY;
        l_request.orderId = "1001";
        try
        {
            l_request.orderedDate = new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp()).roll(1);
            System.out.println(l_request.orderedDate);
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            l_impl.execute(l_request);
            WEB3MockObjectParamsValue l_value = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class});
            Object[] l_object = new Object[5];
            l_object = l_value.getFirstCalled();
            Object[] l_object1 = new Object[1];
            l_object1 = (Object[]) l_object[2];
            MutualFundNewOrderSpec l_spec = (MutualFundNewOrderSpec)l_object1[0];
            assertEquals(TaxTypeEnum.UNDEFINED, l_spec.getTaxType());
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public void testSubmitBuyOrderCase2()
    {
        final String STR_METHOD_NAME = "testSubmitBuyOrderCase2()";
        log.entering(STR_METHOD_NAME);

        ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
        OrderSubmissionResult l_result = new OrderSubmissionResult(processingResult);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "submitNewOrder",
            new Class[] {
                SubAccount.class,
                ProductTypeEnum.class,
                NewOrderSpec.class,
                long.class,
                String.class,
                boolean.class}, l_result);

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
        l_mutualFundOrderUnitParams.setAccountId(333812512246L);
        l_mutualFundOrderUnitParams.setSubAccountId(33381251220301L);
        Date l_dateBizDate =
            GtlUtils.getTradingSystem().getBizDate();
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
        String l_strBizDate = l_format.format(l_dateBizDate);
        l_mutualFundOrderUnitParams.setBizDate(l_strBizDate);
        l_mutualFundOrderUnitParams.setExecStatus("1");
        l_mutualFundOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
        l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductId(4003000900000000L);
        l_mutualFundProductParams.setNewBuyMinQty(111);
        l_mutualFundProductParams.setNewBuyUnitQty(111);
        l_mutualFundProductParams.setBuyStartDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
        l_mutualFundProductParams.setBuyEndDate(WEB3DateUtility.getDate("20070707","yyyyMMdd"));
        l_mutualFundProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
        l_mutualFundProductParams.setBuyLimitDiv(WEB3BuyLimitDivDef.BUY_POSSIBLE);
        l_mutualFundProductParams.setPlowbackProductDiv(WEB3MFPlowbackProductDivDef.PLOWBACK_PRODUCT);
        
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setProductId(4003000900000000L);
        
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setProductCode("12");
        TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams1.setInstitutionCode("0D");
        l_tradingTimeParams1.setBranchCode("381");
        l_tradingTimeParams1.setTradingTimeType("01");
        l_tradingTimeParams1.setBizDateType("1");
        l_tradingTimeParams1.setMarketCode("SP");
        l_tradingTimeParams1.setProductCode("0001000");
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(4003000900000000L);
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setInstitutionCode("0D");
        l_tradedProductParams.setProductId(4003000900000000L);
        l_tradedProductParams.setTradedProductId(400003000100000L);
        try
        {
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_traderParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        WEB3MutualBuyServiceImpl l_impl = new WEB3MutualBuyServiceImpl();
        WEB3MutualBuyCompleteRequest l_request = new WEB3MutualBuyCompleteRequest();
        l_request.mutualProductCode = "0001000";
        l_request.specifyDiv = WEB3SellDivDef.COUNT_DESIGNATE;
        l_request.mutualOrderQuantity = "111";
        l_request.taxType = WEB3MFAccountDivDef.NORMAL;
        l_request.settleDiv = WEB3SettlementDivDef.JAPANESE_CURRENCY;
        l_request.orderId = "1001";
        try
        {
            l_request.orderedDate = new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp()).roll(1);
            System.out.println(l_request.orderedDate);
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            l_impl.execute(l_request);
            WEB3MockObjectParamsValue l_value = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class});
            Object[] l_object = new Object[5];
            l_object = l_value.getFirstCalled();
            Object[] l_object1 = new Object[1];
            l_object1 = (Object[]) l_object[2];
            MutualFundNewOrderSpec l_spec = (MutualFundNewOrderSpec)l_object1[0];
            assertEquals(TaxTypeEnum.NORMAL, l_spec.getTaxType());
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateBuyOrderCase0001()
    {
        final String STR_METHOD_NAME = "testValidateBuyOrderCase0001()";
        log.entering(STR_METHOD_NAME);

        ProcessingResult processingResult =
            ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80002);
        
        //WEB3GentradeOrderValidator
        OrderValidationResult l_result = new OrderValidationResult(processingResult);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeOrderValidator", 
            "validateAccountForTrading",
            new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
            l_result);
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
        l_mutualFundOrderUnitParams.setAccountId(333812512246L);
        l_mutualFundOrderUnitParams.setSubAccountId(33381251220301L);
        Date l_dateBizDate =
            GtlUtils.getTradingSystem().getBizDate();
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
        String l_strBizDate = l_format.format(l_dateBizDate);
        l_mutualFundOrderUnitParams.setBizDate(l_strBizDate);
        l_mutualFundOrderUnitParams.setExecStatus("1");
        l_mutualFundOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
        l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductId(4003000900000000L);
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setProductCode("12");
        TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams1.setInstitutionCode("0D");
        l_tradingTimeParams1.setBranchCode("381");
        l_tradingTimeParams1.setTradingTimeType("01");
        l_tradingTimeParams1.setBizDateType("1");
        l_tradingTimeParams1.setMarketCode("SP");
        l_tradingTimeParams1.setProductCode("0001000");
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(4003000900000000L);
        try
        {
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_traderParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_productParams);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();


            WEB3MutualBuyServiceImpl l_impl = new WEB3MutualBuyServiceImpl();
            WEB3MutualBuyConfirmRequest l_request = new WEB3MutualBuyConfirmRequest();
            l_request.mutualProductCode = "0001000";
            l_request.specifyDiv = WEB3SellDivDef.COUNT_DESIGNATE;
            l_request.mutualOrderQuantity = "111";
            l_request.taxType = WEB3MFAccountDivDef.OTHER;
            l_request.settleDiv = WEB3SettlementDivDef.FOREIGN_CURRENCY;

            l_request.orderedDate = new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp()).roll(1);

            l_impl.execute(l_request);

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
 
    public void testSubmitBuyOrderCase0001()
    {
        final String STR_METHOD_NAME = "testSubmitBuyOrderCase0001()";
        log.entering(STR_METHOD_NAME);

        ProcessingResult processingResult =
            ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80002);
        
        //WEB3GentradeOrderValidator
        OrderValidationResult l_result = new OrderValidationResult(processingResult);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeOrderValidator", 
            "validateAccountForTrading",
            new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
            l_result);

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
        l_mutualFundOrderUnitParams.setAccountId(333812512246L);
        l_mutualFundOrderUnitParams.setSubAccountId(33381251220301L);
        Date l_dateBizDate =
            GtlUtils.getTradingSystem().getBizDate();
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
        String l_strBizDate = l_format.format(l_dateBizDate);
        l_mutualFundOrderUnitParams.setBizDate(l_strBizDate);
        l_mutualFundOrderUnitParams.setExecStatus("1");
        l_mutualFundOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
        l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductId(4003000900000000L);
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setProductCode("12");
        TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams1.setInstitutionCode("0D");
        l_tradingTimeParams1.setBranchCode("381");
        l_tradingTimeParams1.setTradingTimeType("01");
        l_tradingTimeParams1.setBizDateType("1");
        l_tradingTimeParams1.setMarketCode("SP");
        l_tradingTimeParams1.setProductCode("0001000");
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(4003000900000000L);
        try
        {
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_traderParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_productParams);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();

            WEB3MutualBuyServiceImpl l_impl = new WEB3MutualBuyServiceImpl();
            WEB3MutualBuyCompleteRequest l_request = new WEB3MutualBuyCompleteRequest();
            l_request.mutualProductCode = "0001000";
            l_request.specifyDiv = WEB3SellDivDef.COUNT_DESIGNATE;
            l_request.mutualOrderQuantity = "111";
            l_request.taxType = WEB3MFAccountDivDef.OTHER;
            l_request.settleDiv = WEB3SettlementDivDef.FOREIGN_CURRENCY;
            l_request.orderId = "1001";

            l_request.orderedDate = new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp()).roll(1);

            l_impl.execute(l_request);

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
    
    public void testValidateBuyOrderC1()
    {
        final String STR_METHOD_NAME = "testValidateBuyOrderC1()";
        log.entering(STR_METHOD_NAME);
        
        ProcessingResult processingResult =
            ProcessingResult.newSuccessResultInstance();
        
        //WEB3GentradeOrderValidator
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        OrderValidationResult l_result = new OrderValidationResult(processingResult);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeOrderValidator", 
            "validateAccountForTrading",
            new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
            l_result);
        
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                "validateEmergencyStop",
                new Class[] {WEB3MutualFundProduct.class, String.class},null);
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
        l_mutualFundOrderUnitParams.setAccountId(333812512246L);
        l_mutualFundOrderUnitParams.setSubAccountId(33381251220301L);
        Date l_dateBizDate =
            GtlUtils.getTradingSystem().getBizDate();
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
        String l_strBizDate = l_format.format(l_dateBizDate);
        l_mutualFundOrderUnitParams.setBizDate(l_strBizDate);
        l_mutualFundOrderUnitParams.setExecStatus("1");
        l_mutualFundOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
        l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        
        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductId(4003000900000000L);
        l_mutualFundProductParams.setNewBuyMinQty(111);
        l_mutualFundProductParams.setNewBuyUnitQty(111);
        l_mutualFundProductParams.setBuyStartDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
        l_mutualFundProductParams.setBuyEndDate(WEB3DateUtility.getDate("20070707","yyyyMMdd"));
        l_mutualFundProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
        l_mutualFundProductParams.setBuyLimitDiv(WEB3BuyLimitDivDef.BUY_POSSIBLE);
        l_mutualFundProductParams.setPlowbackProductDiv(WEB3MFPlowbackProductDivDef.PLOWBACK_PRODUCT);
        
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setProductId(4003000900000000L);
        
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setProductCode("12");
        TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams1.setInstitutionCode("0D");
        l_tradingTimeParams1.setBranchCode("381");
        l_tradingTimeParams1.setTradingTimeType("01");
        l_tradingTimeParams1.setBizDateType("1");
        l_tradingTimeParams1.setMarketCode("SP");
        l_tradingTimeParams1.setProductCode("0001000");
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(4003000900000000L);
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setInstitutionCode("0D");
        l_tradedProductParams.setProductId(4003000900000000L);
        l_tradedProductParams.setTradedProductId(400003000100000L);
        try
        {
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_traderParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
        
            WEB3MutualBuyServiceImpl l_impl = new WEB3MutualBuyServiceImpl();
            WEB3MutualBuyConfirmRequestForMock l_request = new WEB3MutualBuyConfirmRequestForMock();
            l_request.mutualProductCode = "0001000";
            l_request.specifyDiv = WEB3SellDivDef.COUNT_DESIGNATE;
            l_request.mutualOrderQuantity = "111";
            l_request.taxType = WEB3MFAccountDivDef.OTHER;
            l_request.settleDiv = WEB3SettlementDivDef.FOREIGN_CURRENCY;
        
            l_request.orderedDate = new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp()).roll(1);
        
            l_impl.validateBuyOrder(l_request);
        
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateBuyOrderC2()
    {
        final String STR_METHOD_NAME = "testValidateBuyOrderC2()";
        log.entering(STR_METHOD_NAME);
        
        ProcessingResult processingResult =
            ProcessingResult.newSuccessResultInstance();
        
        //WEB3GentradeOrderValidator
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        OrderValidationResult l_result = new OrderValidationResult(processingResult);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeOrderValidator", 
            "validateAccountForTrading",
            new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
            l_result);
        
        WEB3TPTradingPowerResult l_result1 = new WEB3TPTradingPowerResult();
        l_result1.setResultFlg(false);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                    l_result1);
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
        l_mutualFundOrderUnitParams.setAccountId(333812512246L);
        l_mutualFundOrderUnitParams.setSubAccountId(33381251220301L);
        Date l_dateBizDate =
            GtlUtils.getTradingSystem().getBizDate();
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
        String l_strBizDate = l_format.format(l_dateBizDate);
        l_mutualFundOrderUnitParams.setBizDate(l_strBizDate);
        l_mutualFundOrderUnitParams.setExecStatus("1");
        l_mutualFundOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
        l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        
        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductId(4003000900000000L);
        l_mutualFundProductParams.setNewBuyMinQty(111);
        l_mutualFundProductParams.setNewBuyUnitQty(111);
        l_mutualFundProductParams.setBuyStartDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
        l_mutualFundProductParams.setBuyEndDate(WEB3DateUtility.getDate("20070707","yyyyMMdd"));
        l_mutualFundProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
        l_mutualFundProductParams.setBuyLimitDiv(WEB3BuyLimitDivDef.BUY_POSSIBLE);
        l_mutualFundProductParams.setPlowbackProductDiv(WEB3MFPlowbackProductDivDef.PLOWBACK_PRODUCT);
        
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setProductId(4003000900000000L);
        
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setProductCode("12");
        TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams1.setInstitutionCode("0D");
        l_tradingTimeParams1.setBranchCode("381");
        l_tradingTimeParams1.setTradingTimeType("01");
        l_tradingTimeParams1.setBizDateType("1");
        l_tradingTimeParams1.setMarketCode("SP");
        l_tradingTimeParams1.setProductCode("0001000");
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(4003000900000000L);
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setInstitutionCode("0D");
        l_tradedProductParams.setProductId(4003000900000000L);
        l_tradedProductParams.setTradedProductId(400003000100000L);
        try
        {
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_traderParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
        
            WEB3MutualBuyServiceImpl l_impl = new WEB3MutualBuyServiceImpl();
            WEB3MutualBuyConfirmRequestForMock l_request = new WEB3MutualBuyConfirmRequestForMock();
            l_request.mutualProductCode = "0001000";
            l_request.specifyDiv = WEB3SellDivDef.COUNT_DESIGNATE;
            l_request.mutualOrderQuantity = "111";
            l_request.taxType = WEB3MFAccountDivDef.OTHER;
            l_request.settleDiv = WEB3SettlementDivDef.JAPANESE_CURRENCY;
        
            l_request.orderedDate = new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp()).roll(1);
        
            l_impl.validateBuyOrder(l_request);
            fail();
        
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01187,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitBuyOrderC1()
    {
        final String STR_METHOD_NAME = "testSubmitBuyOrderC1()";
        log.entering(STR_METHOD_NAME);
        
        ProcessingResult processingResult =
            ProcessingResult.newSuccessResultInstance();
        
        //WEB3GentradeOrderValidator
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        OrderValidationResult l_result = new OrderValidationResult(processingResult);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeOrderValidator", 
            "validateAccountForTrading",
            new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
            l_result);
        
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
        

        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
        l_mutualFundOrderUnitParams.setAccountId(333812512246L);
        l_mutualFundOrderUnitParams.setSubAccountId(33381251220301L);
        Date l_dateBizDate =
            GtlUtils.getTradingSystem().getBizDate();
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
        String l_strBizDate = l_format.format(l_dateBizDate);
        l_mutualFundOrderUnitParams.setBizDate(l_strBizDate);
        l_mutualFundOrderUnitParams.setExecStatus("1");
        l_mutualFundOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
        l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        
        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductId(4003000900000000L);
        l_mutualFundProductParams.setNewBuyMinQty(111);
        l_mutualFundProductParams.setNewBuyUnitQty(111);
        l_mutualFundProductParams.setBuyStartDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
        l_mutualFundProductParams.setBuyEndDate(WEB3DateUtility.getDate("20070707","yyyyMMdd"));
        l_mutualFundProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
        l_mutualFundProductParams.setBuyLimitDiv(WEB3BuyLimitDivDef.BUY_POSSIBLE);
        l_mutualFundProductParams.setPlowbackProductDiv(WEB3MFPlowbackProductDivDef.PLOWBACK_PRODUCT);
        
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setProductId(4003000900000000L);
        
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setProductCode("12");
        TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams1.setInstitutionCode("0D");
        l_tradingTimeParams1.setBranchCode("381");
        l_tradingTimeParams1.setTradingTimeType("01");
        l_tradingTimeParams1.setBizDateType("1");
        l_tradingTimeParams1.setMarketCode("SP");
        l_tradingTimeParams1.setProductCode("0001000");
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(4003000900000000L);
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setInstitutionCode("0D");
        l_tradedProductParams.setProductId(4003000900000000L);
        l_tradedProductParams.setTradedProductId(400003000100000L);
        try
        {
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_traderParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
        
            WEB3MutualBuyServiceImpl l_impl = new WEB3MutualBuyServiceImpl();
            WEB3MutualBuyCompleteRequestForMock l_request = new WEB3MutualBuyCompleteRequestForMock();
            l_request.mutualProductCode = "0001000";
            l_request.specifyDiv = WEB3SellDivDef.COUNT_DESIGNATE;
            l_request.mutualOrderQuantity = "111";
            l_request.taxType = WEB3MFAccountDivDef.OTHER;
            l_request.settleDiv = WEB3SettlementDivDef.FOREIGN_CURRENCY;
            l_request.orderId = "1001";
            l_request.orderedDate = new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp()).roll(1);
        
            l_impl.submitBuyOrder(l_request);
        
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitBuyOrderC2()
    {
        final String STR_METHOD_NAME = "testSubmitBuyOrderC2()";
        log.entering(STR_METHOD_NAME);
        
        ProcessingResult processingResult =
            ProcessingResult.newSuccessResultInstance();
        
        //WEB3GentradeOrderValidator
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        OrderValidationResult l_result = new OrderValidationResult(processingResult);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeOrderValidator", 
            "validateAccountForTrading",
            new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
            l_result);
        
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
        
        WEB3TPTradingPowerResult l_result1 = new WEB3TPTradingPowerResult();
        l_result1.setResultFlg(false);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                    l_result1);
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
        l_mutualFundOrderUnitParams.setAccountId(333812512246L);
        l_mutualFundOrderUnitParams.setSubAccountId(33381251220301L);
        Date l_dateBizDate =
            GtlUtils.getTradingSystem().getBizDate();
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
        String l_strBizDate = l_format.format(l_dateBizDate);
        l_mutualFundOrderUnitParams.setBizDate(l_strBizDate);
        l_mutualFundOrderUnitParams.setExecStatus("1");
        l_mutualFundOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
        l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        
        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductId(4003000900000000L);
        l_mutualFundProductParams.setNewBuyMinQty(111);
        l_mutualFundProductParams.setNewBuyUnitQty(111);
        l_mutualFundProductParams.setBuyStartDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
        l_mutualFundProductParams.setBuyEndDate(WEB3DateUtility.getDate("20070707","yyyyMMdd"));
        l_mutualFundProductParams.setSystemHandlingDiv(WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN);
        l_mutualFundProductParams.setBuyLimitDiv(WEB3BuyLimitDivDef.BUY_POSSIBLE);
        l_mutualFundProductParams.setPlowbackProductDiv(WEB3MFPlowbackProductDivDef.PLOWBACK_PRODUCT);
        
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setProductId(4003000900000000L);
        
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setProductCode("12");
        TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams1.setInstitutionCode("0D");
        l_tradingTimeParams1.setBranchCode("381");
        l_tradingTimeParams1.setTradingTimeType("01");
        l_tradingTimeParams1.setBizDateType("1");
        l_tradingTimeParams1.setMarketCode("SP");
        l_tradingTimeParams1.setProductCode("0001000");
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(4003000900000000L);
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setInstitutionCode("0D");
        l_tradedProductParams.setProductId(4003000900000000L);
        l_tradedProductParams.setTradedProductId(400003000100000L);
        try
        {
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_traderParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
        
            WEB3MutualBuyServiceImpl l_impl = new WEB3MutualBuyServiceImpl();
            WEB3MutualBuyCompleteRequestForMock l_request = new WEB3MutualBuyCompleteRequestForMock();
            l_request.mutualProductCode = "0001000";
            l_request.specifyDiv = WEB3SellDivDef.COUNT_DESIGNATE;
            l_request.mutualOrderQuantity = "111";
            l_request.taxType = WEB3MFAccountDivDef.OTHER;
            l_request.settleDiv = WEB3SettlementDivDef.JAPANESE_CURRENCY;
            l_request.orderId = "1001";
            l_request.orderedDate = new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp()).roll(1);
        
            l_impl.submitBuyOrder(l_request);
        
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01187,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@

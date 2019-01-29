head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.57.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityPTSChangeOrderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (PTS)現物株式注文訂正サービスImplTest(WEB3EquityPTSChangeOrderServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/18 張騰宇 (中訊) 新規作成 モデル1241 1255 1265 1269
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.data.EquityTickValuesMstParams;
import webbroker3.equity.message.WEB3EquityChangeCompleteRequest;
import webbroker3.equity.message.WEB3EquityChangeCompleteResponse;
import webbroker3.equity.message.WEB3EquityChangeConfirmRequest;
import webbroker3.equity.message.WEB3EquityChangeConfirmResponse;
import webbroker3.equity.message.WEB3EquityChangeInputRequest;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondParams;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.InstBranchProductParams;
import webbroker3.gentrade.data.OrderAcceptStatusParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerErrorInfo;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.define.WEB3TPResultAttentionObjectionTypeDef;
import webbroker3.tradingpower.define.WEB3TPTradingPowerErrorDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityPTSChangeOrderServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSChangeOrderServiceImplTest.class);

    public WEB3EquityPTSChangeOrderServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// 注意月?要減1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc." + 
                "gentrade.stdimpls.TradingSystemImpl", 
            "getSystemTimestamp",
            new Class[]{}, 
            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",st);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validatePTSPrice",
                new Class[] { double.class, WEB3EquityTradedProduct.class, SubAccount.class },
                Boolean.TRUE);
        try
        {
        TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
        
        EnableOrderConditionParams l_EnableOrderConditionParams =
            TestDBUtility.getEnableOrderConditionParamsRow();
        l_EnableOrderConditionParams.setInstitutionCode("0D");
        l_EnableOrderConditionParams.setMarketCode("1");
        l_EnableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
        l_EnableOrderConditionParams.setFutureOptionDiv("0");
        l_EnableOrderConditionParams.setMarginTradingDiv("0");
        TestDBUtility.insertWithDel(l_EnableOrderConditionParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    //execute
    public void testExecuteCase1()
    {
        final String STR_METHOD_NAME = "testExecuteCase1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "2");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityBizLogicProvider",
                    "calcEstimatedBookPrice",
                    new Class[] {long.class, SubAccount.class, TaxTypeEnum.class },
                    new Double(22.2D));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSPrice",
                    new Class[] { double.class, WEB3EquityTradedProduct.class, SubAccount.class },
                    Boolean.TRUE);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            TestDBUtility.deleteAll(EquityTickValuesMstParams.TYPE);
            TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
            
            EnableOrderConditionParams l_EnableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_EnableOrderConditionParams.setInstitutionCode("0D");
            l_EnableOrderConditionParams.setMarketCode("1");
            l_EnableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_EnableOrderConditionParams.setFutureOptionDiv("0");
            l_EnableOrderConditionParams.setMarginTradingDiv("0");
            TestDBUtility.insertWithDel(l_EnableOrderConditionParams);
            
            EquityTickValuesMstParams l_EquityTickValuesMstParams = TestDBUtility.getEquityTickValuesMstRow();
            l_EquityTickValuesMstParams.setLowPrice(100);
            l_EquityTickValuesMstParams.setCapPrice(300);
            TestDBUtility.insertWithDel(l_EquityTickValuesMstParams);
            
            InstBranchProductParams l_InstBranchProductParams = new InstBranchProductParams();
            l_InstBranchProductParams.setBranchId(33381);
            l_InstBranchProductParams.setCommissionProductCode("10");
            l_InstBranchProductParams.setEstimatePriceCalcForm(1);
            l_InstBranchProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_InstBranchProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_InstBranchProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setMaxOrderQuantity(500);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchMarketPtsDealtCondParams l_BranchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_BranchMarketPtsDealtCondParams.setMarketCode("1");
            l_BranchMarketPtsDealtCondParams.setBranchCode("381");
            l_BranchMarketPtsDealtCondParams.setMartCanDealtEquity(WEB3DealtDef.CAN_DEALT);
            l_BranchMarketPtsDealtCondParams.setLimitedUnit(5);
            l_BranchMarketPtsDealtCondParams.setMaxHandlingPrice(210);
            TestDBUtility.insertWithDel(l_BranchMarketPtsDealtCondParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setCashBalance(12.0);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(90);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(100);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20080924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams); 
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            //計算単価
            l_deliveryPrice.setCalcUnitPrice(100);
            //概算受渡代金
            l_deliveryPrice.setEstimateDeliveryAmount(150);
            //拘束売買代金
            l_deliveryPrice.setRestraintTurnover(200);
            //手数料
            l_deliveryPrice.setCommissionFee(10.0);
            //手数料消費税
            l_deliveryPrice.setCommissionFeeTax(0.15);
            //確認時単価
            l_deliveryPrice.setCheckGetCurrentPrice(50);
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
                  boolean.class},
                  l_deliveryPrice);
            WEB3TPTradingPowerErrorInfo l_errorInfo = new WEB3TPTradingPowerErrorInfo();
            l_errorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.INC_DEPOSIT_LACK_ACCOUNT_BALANCE;
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.LACK_CASH_AMT_ATTENTION);
            l_tpResult.setLackAccountBalance(111);
            l_tpResult.setTpErrorInfo(l_errorInfo);
            l_tpResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tpResult);

            WEB3EquityPTSChangeOrderServiceImpl l_impl =
                new WEB3EquityPTSChangeOrderServiceImpl();
            WEB3EquityChangeConfirmRequest l_request = new WEB3EquityChangeConfirmRequest();
            l_request.orderQuantity = "100";
            l_request.id = "10";
            l_request.orderPriceDiv = "1";
            l_request.priceCondType = WEB3PriceConditionDef.DEFAULT;
            l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.limitPrice = "200";
            WEB3EquityChangeConfirmResponse l_changeConfirmResponse =
                (WEB3EquityChangeConfirmResponse)l_impl.execute(l_request);
            
            //レスポンス.概算簿価単価
            assertEquals("22.2", l_changeConfirmResponse.estimatedBookPrice);
            //レスポンス.確認時発注日
            assertEquals("20070612",WEB3DateUtility.formatDate(l_changeConfirmResponse.checkDate,"yyyyMMdd"));
            //レスポンス.概算受渡代金
            assertEquals("150", l_changeConfirmResponse.estimatedPrice);
            //レスポンス.取引終了警告市場コード一覧
            assertEquals(0, l_changeConfirmResponse.messageSuspension.length);
            //レスポンス.内出来株数
            assertEquals("90", l_changeConfirmResponse.partContQuantity);
            //レスポンス.手数料情報.手数料コース null
            assertNull(l_changeConfirmResponse.commissionInfo.commissionCourse);
            //レスポンス.手数料情報.手数料
            assertEquals("10", l_changeConfirmResponse.commissionInfo.commission);
            //レスポンス.手数料情報.手数料消費税
            assertEquals("0.15", l_changeConfirmResponse.commissionInfo.commissionConsumptionTax);
            //レスポンス.確認時単価
            assertEquals("50", l_changeConfirmResponse.checkPrice);
            //レスポンス.インサイダー警告表示フラグ
            assertFalse(l_changeConfirmResponse.insiderWarningFlag);
            //レスポンス.注意文言表示区分
            assertEquals("1", l_changeConfirmResponse.attentionObjectionType);
            //レスポンス.預り金不足額
            assertEquals("111", l_changeConfirmResponse.accountBalanceInsufficiency);
            //レスポンス.注文有効期限 null
            assertNull( l_changeConfirmResponse.expirationDate);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecuteCase2()
    {
        final String STR_METHOD_NAME = "testExecuteCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "2");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityBizLogicProvider",
                    "calcEstimatedBookPrice",
                    new Class[] {long.class, SubAccount.class, TaxTypeEnum.class },
                    new Double(22.2D));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            
            InstBranchProductParams l_InstBranchProductParams = new InstBranchProductParams();
            l_InstBranchProductParams.setBranchId(33381);
            l_InstBranchProductParams.setCommissionProductCode("10");
            l_InstBranchProductParams.setEstimatePriceCalcForm(1);
            l_InstBranchProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_InstBranchProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_InstBranchProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setMaxOrderQuantity(500);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchMarketPtsDealtCondParams l_BranchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_BranchMarketPtsDealtCondParams.setMarketCode("1");
            l_BranchMarketPtsDealtCondParams.setBranchCode("381");
            l_BranchMarketPtsDealtCondParams.setMartCanDealtEquity(WEB3DealtDef.CAN_DEALT);
            l_BranchMarketPtsDealtCondParams.setLimitedUnit(5);
            l_BranchMarketPtsDealtCondParams.setMaxHandlingPrice(210);
            TestDBUtility.insertWithDel(l_BranchMarketPtsDealtCondParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setCashBalance(12.0);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(90);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(100);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20080924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams); 
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            //計算単価
            l_deliveryPrice.setCalcUnitPrice(100);
            //概算受渡代金
            l_deliveryPrice.setEstimateDeliveryAmount(150);
            //拘束売買代金
            l_deliveryPrice.setRestraintTurnover(200);
            //手数料
            l_deliveryPrice.setCommissionFee(10.0);
            //手数料消費税
            l_deliveryPrice.setCommissionFeeTax(0.15);
            //確認時単価
            l_deliveryPrice.setCheckGetCurrentPrice(50);
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
                  boolean.class},
                  l_deliveryPrice);
            WEB3TPTradingPowerErrorInfo l_errorInfo = new WEB3TPTradingPowerErrorInfo();
            l_errorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.INC_DEPOSIT_LACK_ACCOUNT_BALANCE;
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.INC_DEPOSIT_REG_ATTENTION);
            l_tpResult.setLackAccountBalance(111);
            l_tpResult.setTpErrorInfo(l_errorInfo);
            l_tpResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tpResult);
            
            EqTypeOrderSubmissionResult l_submissionResult =
                new EqTypeOrderSubmissionResult(ProcessingResult.newSuccessResultInstance());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "submitChangeOrder",
                    new Class[] {SubAccount.class, EqTypeChangeOrderSpec.class, String.class, boolean.class},
                    l_submissionResult);

            WEB3EquityPTSChangeOrderServiceImpl l_impl =
                new WEB3EquityPTSChangeOrderServiceImpl();
            WEB3EquityChangeCompleteRequest l_request = new WEB3EquityChangeCompleteRequest();
            l_request.orderQuantity = "100";
            l_request.id = "10";
            l_request.orderPriceDiv = "1";
            l_request.checkDate = WEB3DateUtility.getDate("20070612","yyyyMMdd");
            l_request.priceCondType = WEB3PriceConditionDef.DEFAULT;
            l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.limitPrice = "200";
            WEB3EquityChangeCompleteResponse l_changeCompleteResponse =
                (WEB3EquityChangeCompleteResponse)l_impl.execute(l_request);

            //更新時間
            assertEquals(
                    WEB3DateUtility.formatDate(Calendar.getInstance().getTime(),"yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_changeCompleteResponse.lastUpdatedTimestamp,"yyyyMMdd"));
            //識別番号
            assertEquals("10", l_changeCompleteResponse.orderActionId+"");
            //インサイダー警告表示フラグ
            assertFalse(l_changeCompleteResponse.insiderWarningFlag);
            //連続注文設定フラグ
            assertFalse(l_changeCompleteResponse.succSettingFlag);
            //注文有効期限
            //レスポンス.注文有効期限 null
            assertNull( l_changeCompleteResponse.expirationDate);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecuteCase3()
    {
        final String STR_METHOD_NAME = "testExecuteCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3EquityPTSChangeOrderServiceImpl l_impl =
                new WEB3EquityPTSChangeOrderServiceImpl();
            l_impl.execute(null);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecuteCase4()
    {
        final String STR_METHOD_NAME = "testExecuteCase4()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3EquityPTSChangeOrderServiceImpl l_impl =
                new WEB3EquityPTSChangeOrderServiceImpl();
            WEB3EquityChangeInputRequest l_request = new WEB3EquityChangeInputRequest();
            l_impl.execute(l_request);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSChangeOrderServiceImpl.validateChangeOrder(WEB3EquityChangeConfirmRequest)'
     */
    //validate
    public void testValidateChangeOrderCase1()
    {
        final String STR_METHOD_NAME = "testValidateChangeOrderCase1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3EquityPTSChangeOrderServiceImpl l_impl =
                new WEB3EquityPTSChangeOrderServiceImpl();
            WEB3EquityChangeConfirmRequest l_request = new WEB3EquityChangeConfirmRequest();
//            l_request.orderQuantity = "100";
            l_request.id = "10";
            l_impl.validateChangeOrder(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //validate注文受付可能( )
    public void testValidateChangeOrderCase2()
    {
        final String STR_METHOD_NAME = "testValidateChangeOrderCase2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            l_clendarContext.setOrderAcceptTransaction("01");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3EquityPTSChangeOrderServiceImpl l_impl =
                new WEB3EquityPTSChangeOrderServiceImpl();
            WEB3EquityChangeConfirmRequest l_request = new WEB3EquityChangeConfirmRequest();
            l_request.orderQuantity = "100";
            l_request.id = "10";
            l_request.orderPriceDiv = "1";
            l_request.priceCondType = WEB3PriceConditionDef.DEFAULT;
            l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.limitPrice = "200";
            l_impl.validateChangeOrder(l_request);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //validatePTS訂正注文(補助口座, 株式注文訂正内容)
    public void testValidateChangeOrderCase3()
    {
        final String STR_METHOD_NAME = "testValidateChangeOrderCase3()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "2");
          
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
       
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setMaxOrderQuantity(500);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setPtsAccOpenDiv("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setCashBalance(12.0);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(90);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(100);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20080924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams); 
            
            WEB3EquityPTSChangeOrderServiceImpl l_impl =
                new WEB3EquityPTSChangeOrderServiceImpl();
            WEB3EquityChangeConfirmRequest l_request = new WEB3EquityChangeConfirmRequest();
            l_request.orderQuantity = "100";
            l_request.id = "10";
            l_request.orderPriceDiv = "1";
            l_request.priceCondType = WEB3PriceConditionDef.DEFAULT;
            l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.limitPrice = "200";
            l_impl.validateChangeOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02967, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    //validatePTS市場別取引可能上限金額(部店, 市場, double)
    public void testValidateChangeOrderCase4()
    {
        final String STR_METHOD_NAME = "testValidateChangeOrderCase4()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "2");
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            
            InstBranchProductParams l_InstBranchProductParams = new InstBranchProductParams();
            l_InstBranchProductParams.setBranchId(33381);
            l_InstBranchProductParams.setCommissionProductCode("10");
            l_InstBranchProductParams.setEstimatePriceCalcForm(1);
            l_InstBranchProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_InstBranchProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_InstBranchProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setMaxOrderQuantity(500);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchMarketPtsDealtCondParams l_BranchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_BranchMarketPtsDealtCondParams.setMarketCode("1");
            l_BranchMarketPtsDealtCondParams.setBranchCode("381");
            l_BranchMarketPtsDealtCondParams.setMartCanDealtEquity(WEB3DealtDef.CAN_DEALT);
            l_BranchMarketPtsDealtCondParams.setLimitedUnit(5);
            l_BranchMarketPtsDealtCondParams.setMaxHandlingPrice(-210);
            TestDBUtility.insertWithDel(l_BranchMarketPtsDealtCondParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setCashBalance(12.0);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(90);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(100);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20080924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams); 
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            //計算単価
            l_deliveryPrice.setCalcUnitPrice(100);
            //概算受渡代金
            l_deliveryPrice.setEstimateDeliveryAmount(150);
            //拘束売買代金
            l_deliveryPrice.setRestraintTurnover(220);//
            //手数料
            l_deliveryPrice.setCommissionFee(10.0);
            //手数料消費税
            l_deliveryPrice.setCommissionFeeTax(0.15);
            //確認時単価
            l_deliveryPrice.setCheckGetCurrentPrice(50);
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
                  boolean.class},
                  l_deliveryPrice);
            
            WEB3EquityPTSChangeOrderServiceImpl l_impl =
                new WEB3EquityPTSChangeOrderServiceImpl();
            WEB3EquityChangeConfirmRequest l_request = new WEB3EquityChangeConfirmRequest();
            l_request.orderQuantity = "100";
            l_request.id = "10";
            l_request.orderPriceDiv = "1";
            l_request.priceCondType = WEB3PriceConditionDef.DEFAULT;
            l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.limitPrice = "200";
            l_impl.validateChangeOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02972);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //validate取引余力
    //
    public void testValidateChangeOrderCase5()
    {
        final String STR_METHOD_NAME = "testValidateChangeOrderCase5()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "2");
            WEB3TPTradingPowerErrorInfo l_errorInfo = new WEB3TPTradingPowerErrorInfo();
            l_errorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.INC_DEPOSIT_LACK_ACCOUNT_BALANCE;
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.LACK_CASH_AMT_ATTENTION);
            l_tpResult.setLackAccountBalance(111);
            l_tpResult.setTpErrorInfo(l_errorInfo);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        ""));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            
            InstBranchProductParams l_InstBranchProductParams = new InstBranchProductParams();
            l_InstBranchProductParams.setBranchId(33381);
            l_InstBranchProductParams.setCommissionProductCode("10");
            l_InstBranchProductParams.setEstimatePriceCalcForm(1);
            l_InstBranchProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_InstBranchProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_InstBranchProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setMaxOrderQuantity(500);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchMarketPtsDealtCondParams l_BranchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_BranchMarketPtsDealtCondParams.setMarketCode("1");
            l_BranchMarketPtsDealtCondParams.setBranchCode("381");
            l_BranchMarketPtsDealtCondParams.setMartCanDealtEquity(WEB3DealtDef.CAN_DEALT);
            l_BranchMarketPtsDealtCondParams.setLimitedUnit(5);
            l_BranchMarketPtsDealtCondParams.setMaxHandlingPrice(210);
            TestDBUtility.insertWithDel(l_BranchMarketPtsDealtCondParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setCashBalance(12.0);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(90);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(100);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20080924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams); 
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            //計算単価
            l_deliveryPrice.setCalcUnitPrice(100);
            //概算受渡代金
            l_deliveryPrice.setEstimateDeliveryAmount(150);
            //拘束売買代金
            l_deliveryPrice.setRestraintTurnover(200);
            //手数料
            l_deliveryPrice.setCommissionFee(10.0);
            //手数料消費税
            l_deliveryPrice.setCommissionFeeTax(0.15);
            //確認時単価
            l_deliveryPrice.setCheckGetCurrentPrice(50);
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
                  boolean.class},
                  l_deliveryPrice);
            
            WEB3EquityPTSChangeOrderServiceImpl l_impl =
                new WEB3EquityPTSChangeOrderServiceImpl();
            WEB3EquityChangeConfirmRequest l_request = new WEB3EquityChangeConfirmRequest();
            l_request.orderQuantity = "100";
            l_request.id = "10";
            l_request.orderPriceDiv = "1";
            l_request.priceCondType = WEB3PriceConditionDef.DEFAULT;
            l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.limitPrice = "200";
            l_impl.validateChangeOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //throw現物株式余力エラー詳細情報
    //買注文の場合
    //get預り金不足情報（買付）取得した文字列は例外オブジェクトに設定
    public void testValidateChangeOrderCase6()
    {
        final String STR_METHOD_NAME = "testValidateChangeOrderCase6()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "2");
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            
            InstBranchProductParams l_InstBranchProductParams = new InstBranchProductParams();
            l_InstBranchProductParams.setBranchId(33381);
            l_InstBranchProductParams.setCommissionProductCode("10");
            l_InstBranchProductParams.setEstimatePriceCalcForm(1);
            l_InstBranchProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_InstBranchProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_InstBranchProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setMaxOrderQuantity(500);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchMarketPtsDealtCondParams l_BranchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_BranchMarketPtsDealtCondParams.setMarketCode("1");
            l_BranchMarketPtsDealtCondParams.setBranchCode("381");
            l_BranchMarketPtsDealtCondParams.setMartCanDealtEquity(WEB3DealtDef.CAN_DEALT);
            l_BranchMarketPtsDealtCondParams.setLimitedUnit(5);
            l_BranchMarketPtsDealtCondParams.setMaxHandlingPrice(210);
            TestDBUtility.insertWithDel(l_BranchMarketPtsDealtCondParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setCashBalance(12.0);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(90);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(100);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20080924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams); 
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            //計算単価
            l_deliveryPrice.setCalcUnitPrice(100);
            //概算受渡代金
            l_deliveryPrice.setEstimateDeliveryAmount(150);
            //拘束売買代金
            l_deliveryPrice.setRestraintTurnover(200);
            //手数料
            l_deliveryPrice.setCommissionFee(10.0);
            //手数料消費税
            l_deliveryPrice.setCommissionFeeTax(0.15);
            //確認時単価
            l_deliveryPrice.setCheckGetCurrentPrice(50);
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
                  boolean.class},
                  l_deliveryPrice);
            WEB3TPTradingPowerErrorInfo l_errorInfo = new WEB3TPTradingPowerErrorInfo();
            l_errorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.INC_DEPOSIT_LACK_ACCOUNT_BALANCE;
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.LACK_CASH_AMT_ATTENTION);
            l_tpResult.setLackAccountBalance(111);
            l_tpResult.setTpErrorInfo(l_errorInfo);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tpResult);
            
            WEB3EquityPTSChangeOrderServiceImpl l_impl =
                new WEB3EquityPTSChangeOrderServiceImpl();
            WEB3EquityChangeConfirmRequest l_request = new WEB3EquityChangeConfirmRequest();
            l_request.orderQuantity = "100";
            l_request.id = "10";
            l_request.orderPriceDiv = "1";
            l_request.priceCondType = WEB3PriceConditionDef.DEFAULT;
            l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.limitPrice = "200";
            l_impl.validateChangeOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01929, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //レスポンス.注意文言表示区分 "1"
    public void testValidateChangeOrderCase7()
    {
        final String STR_METHOD_NAME = "testValidateChangeOrderCase7()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "2");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityBizLogicProvider",
                    "calcEstimatedBookPrice",
                    new Class[] {long.class, SubAccount.class, TaxTypeEnum.class },
                    new Double(22.2D));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            
            InstBranchProductParams l_InstBranchProductParams = new InstBranchProductParams();
            l_InstBranchProductParams.setBranchId(33381);
            l_InstBranchProductParams.setCommissionProductCode("10");
            l_InstBranchProductParams.setEstimatePriceCalcForm(1);
            l_InstBranchProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_InstBranchProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_InstBranchProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setMaxOrderQuantity(500);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchMarketPtsDealtCondParams l_BranchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_BranchMarketPtsDealtCondParams.setMarketCode("1");
            l_BranchMarketPtsDealtCondParams.setBranchCode("381");
            l_BranchMarketPtsDealtCondParams.setMartCanDealtEquity(WEB3DealtDef.CAN_DEALT);
            l_BranchMarketPtsDealtCondParams.setLimitedUnit(5);
            l_BranchMarketPtsDealtCondParams.setMaxHandlingPrice(210);
            TestDBUtility.insertWithDel(l_BranchMarketPtsDealtCondParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setCashBalance(12.0);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(90);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(100);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20080924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams); 
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            //計算単価
            l_deliveryPrice.setCalcUnitPrice(100);
            //概算受渡代金
            l_deliveryPrice.setEstimateDeliveryAmount(150);
            //拘束売買代金
            l_deliveryPrice.setRestraintTurnover(200);
            //手数料
            l_deliveryPrice.setCommissionFee(10.0);
            //手数料消費税
            l_deliveryPrice.setCommissionFeeTax(0.15);
            //確認時単価
            l_deliveryPrice.setCheckGetCurrentPrice(50);
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
                  boolean.class},
                  l_deliveryPrice);
            WEB3TPTradingPowerErrorInfo l_errorInfo = new WEB3TPTradingPowerErrorInfo();
            l_errorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.INC_DEPOSIT_LACK_ACCOUNT_BALANCE;
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.LACK_CASH_AMT_ATTENTION);
            l_tpResult.setLackAccountBalance(111);
            l_tpResult.setTpErrorInfo(l_errorInfo);
            l_tpResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tpResult);

            WEB3EquityPTSChangeOrderServiceImpl l_impl =
                new WEB3EquityPTSChangeOrderServiceImpl();
            WEB3EquityChangeConfirmRequest l_request = new WEB3EquityChangeConfirmRequest();
            l_request.orderQuantity = "100";
            l_request.id = "10";
            l_request.orderPriceDiv = "1";
            l_request.priceCondType = WEB3PriceConditionDef.DEFAULT;
            l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.limitPrice = "200";
            WEB3EquityChangeConfirmResponse l_changeConfirmResponse =
                l_impl.validateChangeOrder(l_request);
            
            //レスポンス.概算簿価単価
            assertEquals("22.2", l_changeConfirmResponse.estimatedBookPrice);
            //レスポンス.確認時発注日
            assertEquals("20070612",WEB3DateUtility.formatDate(l_changeConfirmResponse.checkDate,"yyyyMMdd"));
            //レスポンス.概算受渡代金
            assertEquals("150", l_changeConfirmResponse.estimatedPrice);
            //レスポンス.取引終了警告市場コード一覧
            assertEquals(0, l_changeConfirmResponse.messageSuspension.length);
            //レスポンス.内出来株数
            assertEquals("90", l_changeConfirmResponse.partContQuantity);
            //レスポンス.手数料情報.手数料コース null
            assertNull(l_changeConfirmResponse.commissionInfo.commissionCourse);
            //レスポンス.手数料情報.手数料
            assertEquals("10", l_changeConfirmResponse.commissionInfo.commission);
            //レスポンス.手数料情報.手数料消費税
            assertEquals("0.15", l_changeConfirmResponse.commissionInfo.commissionConsumptionTax);
            //レスポンス.確認時単価
            assertEquals("50", l_changeConfirmResponse.checkPrice);
            //レスポンス.インサイダー警告表示フラグ
            assertFalse(l_changeConfirmResponse.insiderWarningFlag);
            //レスポンス.注意文言表示区分
            assertEquals("1", l_changeConfirmResponse.attentionObjectionType);
            //レスポンス.預り金不足額
            assertEquals("111", l_changeConfirmResponse.accountBalanceInsufficiency);
            //レスポンス.注文有効期限 null
            assertNull( l_changeConfirmResponse.expirationDate);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //レスポンス.注意文言表示区分 "3"
    public void testValidateChangeOrderCase8()
    {
        final String STR_METHOD_NAME = "testValidateChangeOrderCase8()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "2");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityBizLogicProvider",
                    "calcEstimatedBookPrice",
                    new Class[] {long.class, SubAccount.class, TaxTypeEnum.class },
                    new Double(22.2D));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            
            InstBranchProductParams l_InstBranchProductParams = new InstBranchProductParams();
            l_InstBranchProductParams.setBranchId(33381);
            l_InstBranchProductParams.setCommissionProductCode("10");
            l_InstBranchProductParams.setEstimatePriceCalcForm(1);
            l_InstBranchProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_InstBranchProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_InstBranchProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setMaxOrderQuantity(500);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchMarketPtsDealtCondParams l_BranchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_BranchMarketPtsDealtCondParams.setMarketCode("1");
            l_BranchMarketPtsDealtCondParams.setBranchCode("381");
            l_BranchMarketPtsDealtCondParams.setMartCanDealtEquity(WEB3DealtDef.CAN_DEALT);
            l_BranchMarketPtsDealtCondParams.setLimitedUnit(5);
            l_BranchMarketPtsDealtCondParams.setMaxHandlingPrice(210);
            TestDBUtility.insertWithDel(l_BranchMarketPtsDealtCondParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setCashBalance(12.0);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(90);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(100);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20080924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams); 
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            //計算単価
            l_deliveryPrice.setCalcUnitPrice(100);
            //概算受渡代金
            l_deliveryPrice.setEstimateDeliveryAmount(150);
            //拘束売買代金
            l_deliveryPrice.setRestraintTurnover(200);
            //手数料
            l_deliveryPrice.setCommissionFee(10.0);
            //手数料消費税
            l_deliveryPrice.setCommissionFeeTax(0.15);
            //確認時単価
            l_deliveryPrice.setCheckGetCurrentPrice(50);
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
                  boolean.class},
                  l_deliveryPrice);
            WEB3TPTradingPowerErrorInfo l_errorInfo = new WEB3TPTradingPowerErrorInfo();
            l_errorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.INC_DEPOSIT_LACK_ACCOUNT_BALANCE;
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION);
            l_tpResult.setLackAccountBalance(111);
            l_tpResult.setTpErrorInfo(l_errorInfo);
            l_tpResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tpResult);

            WEB3EquityPTSChangeOrderServiceImpl l_impl =
                new WEB3EquityPTSChangeOrderServiceImpl();
            WEB3EquityChangeConfirmRequest l_request = new WEB3EquityChangeConfirmRequest();
            l_request.orderQuantity = "100";
            l_request.id = "10";
            l_request.orderPriceDiv = "1";
            l_request.priceCondType = WEB3PriceConditionDef.DEFAULT;
            l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.limitPrice = "200";
            WEB3EquityChangeConfirmResponse l_changeConfirmResponse =
                l_impl.validateChangeOrder(l_request);
            
            //レスポンス.概算簿価単価
            assertEquals("22.2", l_changeConfirmResponse.estimatedBookPrice);
            //レスポンス.確認時発注日
            assertEquals("20070612",WEB3DateUtility.formatDate(l_changeConfirmResponse.checkDate,"yyyyMMdd"));
            //レスポンス.概算受渡代金
            assertEquals("150", l_changeConfirmResponse.estimatedPrice);
            //レスポンス.取引終了警告市場コード一覧
            assertEquals(0, l_changeConfirmResponse.messageSuspension.length);
            //レスポンス.内出来株数
            assertEquals("90", l_changeConfirmResponse.partContQuantity);
            //レスポンス.手数料情報.手数料コース null
            assertNull(l_changeConfirmResponse.commissionInfo.commissionCourse);
            //レスポンス.手数料情報.手数料
            assertEquals("10", l_changeConfirmResponse.commissionInfo.commission);
            //レスポンス.手数料情報.手数料消費税
            assertEquals("0.15", l_changeConfirmResponse.commissionInfo.commissionConsumptionTax);
            //レスポンス.確認時単価
            assertEquals("50", l_changeConfirmResponse.checkPrice);
            //レスポンス.インサイダー警告表示フラグ
            assertFalse(l_changeConfirmResponse.insiderWarningFlag);
            //レスポンス.注意文言表示区分
            assertEquals("3", l_changeConfirmResponse.attentionObjectionType);
            //レスポンス.預り金不足額
            assertEquals("111", l_changeConfirmResponse.accountBalanceInsufficiency);
            //レスポンス.注文有効期限 null
            assertNull( l_changeConfirmResponse.expirationDate);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //レスポンス.注意文言表示区分 "2"
    public void testValidateChangeOrderCase9()
    {
        final String STR_METHOD_NAME = "testValidateChangeOrderCase9()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "2");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityBizLogicProvider",
                    "calcEstimatedBookPrice",
                    new Class[] {long.class, SubAccount.class, TaxTypeEnum.class },
                    new Double(22.2D));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            
            InstBranchProductParams l_InstBranchProductParams = new InstBranchProductParams();
            l_InstBranchProductParams.setBranchId(33381);
            l_InstBranchProductParams.setCommissionProductCode("10");
            l_InstBranchProductParams.setEstimatePriceCalcForm(1);
            l_InstBranchProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_InstBranchProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_InstBranchProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setMaxOrderQuantity(500);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchMarketPtsDealtCondParams l_BranchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_BranchMarketPtsDealtCondParams.setMarketCode("1");
            l_BranchMarketPtsDealtCondParams.setBranchCode("381");
            l_BranchMarketPtsDealtCondParams.setMartCanDealtEquity(WEB3DealtDef.CAN_DEALT);
            l_BranchMarketPtsDealtCondParams.setLimitedUnit(5);
            l_BranchMarketPtsDealtCondParams.setMaxHandlingPrice(210);
            TestDBUtility.insertWithDel(l_BranchMarketPtsDealtCondParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setCashBalance(12.0);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(90);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(100);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20080924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams); 
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            //計算単価
            l_deliveryPrice.setCalcUnitPrice(100);
            //概算受渡代金
            l_deliveryPrice.setEstimateDeliveryAmount(150);
            //拘束売買代金
            l_deliveryPrice.setRestraintTurnover(200);
            //手数料
            l_deliveryPrice.setCommissionFee(10.0);
            //手数料消費税
            l_deliveryPrice.setCommissionFeeTax(0.15);
            //確認時単価
            l_deliveryPrice.setCheckGetCurrentPrice(50);
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
                  boolean.class},
                  l_deliveryPrice);
            WEB3TPTradingPowerErrorInfo l_errorInfo = new WEB3TPTradingPowerErrorInfo();
            l_errorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.INC_DEPOSIT_LACK_ACCOUNT_BALANCE;
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.INC_DEPOSIT_REG_ATTENTION);
            l_tpResult.setLackAccountBalance(111);
            l_tpResult.setTpErrorInfo(l_errorInfo);
            l_tpResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tpResult);

            WEB3EquityPTSChangeOrderServiceImpl l_impl =
                new WEB3EquityPTSChangeOrderServiceImpl();
            WEB3EquityChangeConfirmRequest l_request = new WEB3EquityChangeConfirmRequest();
            l_request.orderQuantity = "100";
            l_request.id = "10";
            l_request.orderPriceDiv = "1";
            l_request.priceCondType = WEB3PriceConditionDef.DEFAULT;
            l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.limitPrice = "200";

            WEB3EquityChangeConfirmResponse l_changeConfirmResponse =
                l_impl.validateChangeOrder(l_request);
            
            //レスポンス.概算簿価単価
            assertEquals("22.2", l_changeConfirmResponse.estimatedBookPrice);
            //レスポンス.確認時発注日
            assertEquals("20070612",WEB3DateUtility.formatDate(l_changeConfirmResponse.checkDate,"yyyyMMdd"));
            //レスポンス.概算受渡代金
            assertEquals("150", l_changeConfirmResponse.estimatedPrice);
            //レスポンス.取引終了警告市場コード一覧
            assertEquals(0, l_changeConfirmResponse.messageSuspension.length);
            //レスポンス.内出来株数
            assertEquals("90", l_changeConfirmResponse.partContQuantity);
            //レスポンス.手数料情報.手数料コース null
            assertNull(l_changeConfirmResponse.commissionInfo.commissionCourse);
            //レスポンス.手数料情報.手数料
            assertEquals("10", l_changeConfirmResponse.commissionInfo.commission);
            //レスポンス.手数料情報.手数料消費税
            assertEquals("0.15", l_changeConfirmResponse.commissionInfo.commissionConsumptionTax);
            //レスポンス.確認時単価
            assertEquals("50", l_changeConfirmResponse.checkPrice);
            //レスポンス.インサイダー警告表示フラグ
            assertFalse(l_changeConfirmResponse.insiderWarningFlag);
            //レスポンス.注意文言表示区分
            assertEquals("2", l_changeConfirmResponse.attentionObjectionType);
            //レスポンス.預り金不足額
            assertNull(l_changeConfirmResponse.accountBalanceInsufficiency);
            //レスポンス.注文有効期限 null
            assertNull( l_changeConfirmResponse.expirationDate);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSChangeOrderServiceImpl.submitChangeOrder(WEB3EquityChangeCompleteRequest)'
     */
    //validate
    public void testSubmitChangeOrderCase1()
    {
        final String STR_METHOD_NAME = "testSubmitChangeOrderCase1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3EquityPTSChangeOrderServiceImpl l_impl =
                new WEB3EquityPTSChangeOrderServiceImpl();
            
            WEB3EquityChangeCompleteRequest l_request = new WEB3EquityChangeCompleteRequest();
//            l_request.orderQuantity = "100";
            l_request.id = "10";

            l_impl.submitChangeOrder(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //validate注文受付可能( )
    //緊急停止中
    public void testSubmitChangeOrderCase2()
    {
        final String STR_METHOD_NAME = "testSubmitChangeOrderCase2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {           
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            l_clendarContext.setOrderAcceptTransaction("1");
            l_clendarContext.setOrderAcceptProduct("10");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            
            TestDBUtility.deleteAll(OrderAcceptStatusParams.TYPE);
            OrderAcceptStatusParams l_OrderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_OrderAcceptStatusParams.setBranchCode("381");
            l_OrderAcceptStatusParams.setInstitutionCode("0D");
            l_OrderAcceptStatusParams.setOrderAccProduct("10");
            l_OrderAcceptStatusParams.setOrderAccTransaction("1");
            l_OrderAcceptStatusParams.setOrderAcceptStatus("2");
            TestDBUtility.insertWithDel(l_OrderAcceptStatusParams);
            
            WEB3EquityPTSChangeOrderServiceImpl l_impl =
                new WEB3EquityPTSChangeOrderServiceImpl();
            WEB3EquityChangeCompleteRequest l_request = new WEB3EquityChangeCompleteRequest();
            l_request.orderQuantity = "100";
            l_request.id = "10";
            l_request.orderPriceDiv = "1";
            l_request.checkDate = WEB3DateUtility.getDate("20070612","yyyyMMdd");
            l_request.priceCondType = WEB3PriceConditionDef.DEFAULT;
            l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.limitPrice = "200";
            l_impl.submitChangeOrder(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00012, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //validatePTS訂正注文(補助口座, 株式注文訂正内容)
    public void testSubmitChangeOrderCase3()
    {
        final String STR_METHOD_NAME = "testSubmitChangeOrderCase3()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "2");
          
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
       
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setMaxOrderQuantity(500);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setPtsAccOpenDiv("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setCashBalance(12.0);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(90);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(100);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20080924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams); 
            
            WEB3EquityPTSChangeOrderServiceImpl l_impl =
                new WEB3EquityPTSChangeOrderServiceImpl();
            WEB3EquityChangeCompleteRequest l_request = new WEB3EquityChangeCompleteRequest();
            l_request.orderQuantity = "100";
            l_request.id = "10";
            l_request.orderPriceDiv = "1";
            l_request.priceCondType = WEB3PriceConditionDef.DEFAULT;
            l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.limitPrice = "200";
            l_impl.submitChangeOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02967, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    //validatePTS市場別取引可能上限金額(部店, 市場, double)
    public void testSubmitChangeOrderCase4()
    {
        final String STR_METHOD_NAME = "testSubmitChangeOrderCase4()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "2");
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            
            InstBranchProductParams l_InstBranchProductParams = new InstBranchProductParams();
            l_InstBranchProductParams.setBranchId(33381);
            l_InstBranchProductParams.setCommissionProductCode("10");
            l_InstBranchProductParams.setEstimatePriceCalcForm(1);
            l_InstBranchProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_InstBranchProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_InstBranchProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setMaxOrderQuantity(500);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchMarketPtsDealtCondParams l_BranchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_BranchMarketPtsDealtCondParams.setMarketCode("1");
            l_BranchMarketPtsDealtCondParams.setBranchCode("381");
            l_BranchMarketPtsDealtCondParams.setMartCanDealtEquity(WEB3DealtDef.CAN_DEALT);
            l_BranchMarketPtsDealtCondParams.setLimitedUnit(5);
            l_BranchMarketPtsDealtCondParams.setMaxHandlingPrice(-210);
            TestDBUtility.insertWithDel(l_BranchMarketPtsDealtCondParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setCashBalance(12.0);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(90);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(100);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20080924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams); 
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            //計算単価
            l_deliveryPrice.setCalcUnitPrice(100);
            //概算受渡代金
            l_deliveryPrice.setEstimateDeliveryAmount(150);
            //拘束売買代金
            l_deliveryPrice.setRestraintTurnover(220);//
            //手数料
            l_deliveryPrice.setCommissionFee(10.0);
            //手数料消費税
            l_deliveryPrice.setCommissionFeeTax(0.15);
            //確認時単価
            l_deliveryPrice.setCheckGetCurrentPrice(50);
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
                  boolean.class},
                  l_deliveryPrice);
            
            WEB3EquityPTSChangeOrderServiceImpl l_impl =
                new WEB3EquityPTSChangeOrderServiceImpl();
            WEB3EquityChangeCompleteRequest l_request = new WEB3EquityChangeCompleteRequest();
            l_request.orderQuantity = "100";
            l_request.id = "10";
            l_request.orderPriceDiv = "1";
            l_request.priceCondType = WEB3PriceConditionDef.DEFAULT;
            l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.limitPrice = "200";
            l_impl.submitChangeOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02972);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //validate取引余力
    //
    public void testSubmitChangeOrderCase5()
    {
        final String STR_METHOD_NAME = "testSubmitChangeOrderCase5()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "2");
            WEB3TPTradingPowerErrorInfo l_errorInfo = new WEB3TPTradingPowerErrorInfo();
            l_errorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.INC_DEPOSIT_LACK_ACCOUNT_BALANCE;
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.LACK_CASH_AMT_ATTENTION);
            l_tpResult.setLackAccountBalance(111);
            l_tpResult.setTpErrorInfo(l_errorInfo);
            l_tpResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        ""));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            
            InstBranchProductParams l_InstBranchProductParams = new InstBranchProductParams();
            l_InstBranchProductParams.setBranchId(33381);
            l_InstBranchProductParams.setCommissionProductCode("10");
            l_InstBranchProductParams.setEstimatePriceCalcForm(1);
            l_InstBranchProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_InstBranchProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_InstBranchProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setMaxOrderQuantity(500);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchMarketPtsDealtCondParams l_BranchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_BranchMarketPtsDealtCondParams.setMarketCode("1");
            l_BranchMarketPtsDealtCondParams.setBranchCode("381");
            l_BranchMarketPtsDealtCondParams.setMartCanDealtEquity(WEB3DealtDef.CAN_DEALT);
            l_BranchMarketPtsDealtCondParams.setLimitedUnit(5);
            l_BranchMarketPtsDealtCondParams.setMaxHandlingPrice(210);
            TestDBUtility.insertWithDel(l_BranchMarketPtsDealtCondParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setCashBalance(12.0);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(90);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(100);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20080924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams); 
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            //計算単価
            l_deliveryPrice.setCalcUnitPrice(100);
            //概算受渡代金
            l_deliveryPrice.setEstimateDeliveryAmount(150);
            //拘束売買代金
            l_deliveryPrice.setRestraintTurnover(200);
            //手数料
            l_deliveryPrice.setCommissionFee(10.0);
            //手数料消費税
            l_deliveryPrice.setCommissionFeeTax(0.15);
            //確認時単価
            l_deliveryPrice.setCheckGetCurrentPrice(50);
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
                  boolean.class},
                  l_deliveryPrice);
            
            WEB3EquityPTSChangeOrderServiceImpl l_impl =
                new WEB3EquityPTSChangeOrderServiceImpl();
            WEB3EquityChangeCompleteRequest l_request = new WEB3EquityChangeCompleteRequest();
            l_request.orderQuantity = "100";
            l_request.id = "10";
            l_request.orderPriceDiv = "1";
            l_request.priceCondType = WEB3PriceConditionDef.DEFAULT;
            l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.limitPrice = "200";
            l_impl.submitChangeOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //throw現物株式余力エラー詳細情報
    //買注文の場合
    //get預り金不足情報（買付）取得した文字列は例外オブジェクトに設定
    public void testSubmitChangeOrderCase6()
    {
        final String STR_METHOD_NAME = "testSubmitChangeOrderCase6()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "2");
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            
            InstBranchProductParams l_InstBranchProductParams = new InstBranchProductParams();
            l_InstBranchProductParams.setBranchId(33381);
            l_InstBranchProductParams.setCommissionProductCode("10");
            l_InstBranchProductParams.setEstimatePriceCalcForm(1);
            l_InstBranchProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_InstBranchProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_InstBranchProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setMaxOrderQuantity(500);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchMarketPtsDealtCondParams l_BranchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_BranchMarketPtsDealtCondParams.setMarketCode("1");
            l_BranchMarketPtsDealtCondParams.setBranchCode("381");
            l_BranchMarketPtsDealtCondParams.setMartCanDealtEquity(WEB3DealtDef.CAN_DEALT);
            l_BranchMarketPtsDealtCondParams.setLimitedUnit(5);
            l_BranchMarketPtsDealtCondParams.setMaxHandlingPrice(210);
            TestDBUtility.insertWithDel(l_BranchMarketPtsDealtCondParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setCashBalance(12.0);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(90);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(100);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20080924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams); 
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            //計算単価
            l_deliveryPrice.setCalcUnitPrice(100);
            //概算受渡代金
            l_deliveryPrice.setEstimateDeliveryAmount(150);
            //拘束売買代金
            l_deliveryPrice.setRestraintTurnover(200);
            //手数料
            l_deliveryPrice.setCommissionFee(10.0);
            //手数料消費税
            l_deliveryPrice.setCommissionFeeTax(0.15);
            //確認時単価
            l_deliveryPrice.setCheckGetCurrentPrice(50);
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
                  boolean.class},
                  l_deliveryPrice);
            WEB3TPTradingPowerErrorInfo l_errorInfo = new WEB3TPTradingPowerErrorInfo();
            l_errorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.INC_DEPOSIT_LACK_ACCOUNT_BALANCE;
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.LACK_CASH_AMT_ATTENTION);
            l_tpResult.setLackAccountBalance(111);
            l_tpResult.setTpErrorInfo(l_errorInfo);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tpResult);
            
            WEB3EquityPTSChangeOrderServiceImpl l_impl =
                new WEB3EquityPTSChangeOrderServiceImpl();
            WEB3EquityChangeCompleteRequest l_request = new WEB3EquityChangeCompleteRequest();
            l_request.orderQuantity = "100";
            l_request.id = "10";
            l_request.orderPriceDiv = "1";
            l_request.priceCondType = WEB3PriceConditionDef.DEFAULT;
            l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.limitPrice = "200";
            l_impl.submitChangeOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01929, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //l_request.checkDate == null
    public void testSubmitChangeOrderCase8()
    {
        final String STR_METHOD_NAME = "testSubmitChangeOrderCase8()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "2");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityBizLogicProvider",
                    "calcEstimatedBookPrice",
                    new Class[] {long.class, SubAccount.class, TaxTypeEnum.class },
                    new Double(22.2D));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            
            InstBranchProductParams l_InstBranchProductParams = new InstBranchProductParams();
            l_InstBranchProductParams.setBranchId(33381);
            l_InstBranchProductParams.setCommissionProductCode("10");
            l_InstBranchProductParams.setEstimatePriceCalcForm(1);
            l_InstBranchProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_InstBranchProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_InstBranchProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setMaxOrderQuantity(500);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchMarketPtsDealtCondParams l_BranchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_BranchMarketPtsDealtCondParams.setMarketCode("1");
            l_BranchMarketPtsDealtCondParams.setBranchCode("381");
            l_BranchMarketPtsDealtCondParams.setMartCanDealtEquity(WEB3DealtDef.CAN_DEALT);
            l_BranchMarketPtsDealtCondParams.setLimitedUnit(5);
            l_BranchMarketPtsDealtCondParams.setMaxHandlingPrice(210);
            TestDBUtility.insertWithDel(l_BranchMarketPtsDealtCondParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setCashBalance(12.0);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(90);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(100);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20080924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams); 
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            //計算単価
            l_deliveryPrice.setCalcUnitPrice(100);
            //概算受渡代金
            l_deliveryPrice.setEstimateDeliveryAmount(150);
            //拘束売買代金
            l_deliveryPrice.setRestraintTurnover(200);
            //手数料
            l_deliveryPrice.setCommissionFee(10.0);
            //手数料消費税
            l_deliveryPrice.setCommissionFeeTax(0.15);
            //確認時単価
            l_deliveryPrice.setCheckGetCurrentPrice(50);
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
                  boolean.class},
                  l_deliveryPrice);
            WEB3TPTradingPowerErrorInfo l_errorInfo = new WEB3TPTradingPowerErrorInfo();
            l_errorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.INC_DEPOSIT_LACK_ACCOUNT_BALANCE;
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.INC_DEPOSIT_REG_ATTENTION);
            l_tpResult.setLackAccountBalance(111);
            l_tpResult.setTpErrorInfo(l_errorInfo);
            l_tpResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tpResult);
            
            EqTypeOrderSubmissionResult l_submissionResult =
                new EqTypeOrderSubmissionResult(ProcessingResult.newSuccessResultInstance());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "submitChangeOrder",
                    new Class[] {SubAccount.class, EqTypeChangeOrderSpec.class, String.class, boolean.class},
                    l_submissionResult);

            WEB3EquityPTSChangeOrderServiceImpl l_impl =
                new WEB3EquityPTSChangeOrderServiceImpl();
            WEB3EquityChangeCompleteRequest l_request = new WEB3EquityChangeCompleteRequest();
            l_request.orderQuantity = "100";
            l_request.id = "10";
            l_request.orderPriceDiv = "1";
            l_request.priceCondType = WEB3PriceConditionDef.DEFAULT;
            l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.limitPrice = "200";
            WEB3EquityChangeCompleteResponse l_changeCompleteResponse =
                l_impl.submitChangeOrder(l_request);

            //更新時間
            assertEquals(
                    WEB3DateUtility.formatDate(Calendar.getInstance().getTime(),"yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_changeCompleteResponse.lastUpdatedTimestamp,"yyyyMMdd"));
            //識別番号
            assertEquals("10", l_changeCompleteResponse.orderActionId+"");
            //インサイダー警告表示フラグ
            assertFalse(l_changeCompleteResponse.insiderWarningFlag);
            //連続注文設定フラグ
            assertFalse(l_changeCompleteResponse.succSettingFlag);
            //注文有効期限
            //レスポンス.注文有効期限 null
            assertNull( l_changeCompleteResponse.expirationDate);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //submit現物株式注文訂正
    //__Error[注文訂正更新]__
    public void testSubmitChangeOrderCase7()
    {
        final String STR_METHOD_NAME = "testSubmitChangeOrderCase7()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "2");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityBizLogicProvider",
                    "calcEstimatedBookPrice",
                    new Class[] {long.class, SubAccount.class, TaxTypeEnum.class },
                    new Double(22.2D));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            
            InstBranchProductParams l_InstBranchProductParams = new InstBranchProductParams();
            l_InstBranchProductParams.setBranchId(33381);
            l_InstBranchProductParams.setCommissionProductCode("10");
            l_InstBranchProductParams.setEstimatePriceCalcForm(1);
            l_InstBranchProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_InstBranchProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_InstBranchProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setMaxOrderQuantity(500);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchMarketPtsDealtCondParams l_BranchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_BranchMarketPtsDealtCondParams.setMarketCode("1");
            l_BranchMarketPtsDealtCondParams.setBranchCode("381");
            l_BranchMarketPtsDealtCondParams.setMartCanDealtEquity(WEB3DealtDef.CAN_DEALT);
            l_BranchMarketPtsDealtCondParams.setLimitedUnit(5);
            l_BranchMarketPtsDealtCondParams.setMaxHandlingPrice(210);
            TestDBUtility.insertWithDel(l_BranchMarketPtsDealtCondParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setCashBalance(12.0);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(90);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(100);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20080924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams); 
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            //計算単価
            l_deliveryPrice.setCalcUnitPrice(100);
            //概算受渡代金
            l_deliveryPrice.setEstimateDeliveryAmount(150);
            //拘束売買代金
            l_deliveryPrice.setRestraintTurnover(200);
            //手数料
            l_deliveryPrice.setCommissionFee(10.0);
            //手数料消費税
            l_deliveryPrice.setCommissionFeeTax(0.15);
            //確認時単価
            l_deliveryPrice.setCheckGetCurrentPrice(50);
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
                  boolean.class},
                  l_deliveryPrice);
            WEB3TPTradingPowerErrorInfo l_errorInfo = new WEB3TPTradingPowerErrorInfo();
            l_errorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.INC_DEPOSIT_LACK_ACCOUNT_BALANCE;
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.INC_DEPOSIT_REG_ATTENTION);
            l_tpResult.setLackAccountBalance(111);
            l_tpResult.setTpErrorInfo(l_errorInfo);
            l_tpResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tpResult);
            
            EqTypeOrderSubmissionResult l_submissionResult =
                new EqTypeOrderSubmissionResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80018));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "submitChangeOrder",
                    new Class[] {SubAccount.class, EqTypeChangeOrderSpec.class, String.class, boolean.class},
                    l_submissionResult);

            WEB3EquityPTSChangeOrderServiceImpl l_impl =
                new WEB3EquityPTSChangeOrderServiceImpl();
            WEB3EquityChangeCompleteRequest l_request = new WEB3EquityChangeCompleteRequest();
            l_request.orderQuantity = "100";
            l_request.id = "10";
            l_request.orderPriceDiv = "1";
            l_request.priceCondType = WEB3PriceConditionDef.DEFAULT;
            l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.limitPrice = "200";
            l_impl.submitChangeOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80018);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //リクエスト.確認時発注日!=null
    public void testSubmitChangeOrderCase9()
    {
        final String STR_METHOD_NAME = "testSubmitChangeOrderCase9()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "2");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityBizLogicProvider",
                    "calcEstimatedBookPrice",
                    new Class[] {long.class, SubAccount.class, TaxTypeEnum.class },
                    new Double(22.2D));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            
            InstBranchProductParams l_InstBranchProductParams = new InstBranchProductParams();
            l_InstBranchProductParams.setBranchId(33381);
            l_InstBranchProductParams.setCommissionProductCode("10");
            l_InstBranchProductParams.setEstimatePriceCalcForm(1);
            l_InstBranchProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_InstBranchProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_InstBranchProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setMaxOrderQuantity(500);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchMarketPtsDealtCondParams l_BranchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_BranchMarketPtsDealtCondParams.setMarketCode("1");
            l_BranchMarketPtsDealtCondParams.setBranchCode("381");
            l_BranchMarketPtsDealtCondParams.setMartCanDealtEquity(WEB3DealtDef.CAN_DEALT);
            l_BranchMarketPtsDealtCondParams.setLimitedUnit(5);
            l_BranchMarketPtsDealtCondParams.setMaxHandlingPrice(210);
            TestDBUtility.insertWithDel(l_BranchMarketPtsDealtCondParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setCashBalance(12.0);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(90);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(100);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20080924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams); 
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            //計算単価
            l_deliveryPrice.setCalcUnitPrice(100);
            //概算受渡代金
            l_deliveryPrice.setEstimateDeliveryAmount(150);
            //拘束売買代金
            l_deliveryPrice.setRestraintTurnover(200);
            //手数料
            l_deliveryPrice.setCommissionFee(10.0);
            //手数料消費税
            l_deliveryPrice.setCommissionFeeTax(0.15);
            //確認時単価
            l_deliveryPrice.setCheckGetCurrentPrice(50);
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
                  boolean.class},
                  l_deliveryPrice);
            WEB3TPTradingPowerErrorInfo l_errorInfo = new WEB3TPTradingPowerErrorInfo();
            l_errorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.INC_DEPOSIT_LACK_ACCOUNT_BALANCE;
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.INC_DEPOSIT_REG_ATTENTION);
            l_tpResult.setLackAccountBalance(111);
            l_tpResult.setTpErrorInfo(l_errorInfo);
            l_tpResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tpResult);
            
            EqTypeOrderSubmissionResult l_submissionResult =
                new EqTypeOrderSubmissionResult(ProcessingResult.newSuccessResultInstance());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "submitChangeOrder",
                    new Class[] {SubAccount.class, EqTypeChangeOrderSpec.class, String.class, boolean.class},
                    l_submissionResult);

            WEB3EquityPTSChangeOrderServiceImpl l_impl =
                new WEB3EquityPTSChangeOrderServiceImpl();
            WEB3EquityChangeCompleteRequest l_request = new WEB3EquityChangeCompleteRequest();
            l_request.orderQuantity = "100";
            l_request.id = "10";
            l_request.orderPriceDiv = "1";
            l_request.checkDate = WEB3DateUtility.getDate("20070612","yyyyMMdd");
            l_request.priceCondType = WEB3PriceConditionDef.DEFAULT;
            l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.limitPrice = "200";
            WEB3EquityChangeCompleteResponse l_changeCompleteResponse =
                l_impl.submitChangeOrder(l_request);

            //更新時間
            assertEquals(
                    WEB3DateUtility.formatDate(Calendar.getInstance().getTime(),"yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_changeCompleteResponse.lastUpdatedTimestamp,"yyyyMMdd"));
            //識別番号
            assertEquals("10", l_changeCompleteResponse.orderActionId+"");
            //インサイダー警告表示フラグ
            assertFalse(l_changeCompleteResponse.insiderWarningFlag);
            //連続注文設定フラグ
            assertFalse(l_changeCompleteResponse.succSettingFlag);
            //注文有効期限
            //レスポンス.注文有効期限 null
            assertNull( l_changeCompleteResponse.expirationDate);
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

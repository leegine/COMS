head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.57.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginOpenMarginServiceImplTest070619.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOpenContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;

import test.util.TestDBUtility;

import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3RepaymentDivDef;
import webbroker3.common.define.WEB3ResidentDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.equity.WEB3EquityEstimatedContractPrice;
import webbroker3.equity.WEB3MarginNewOrderValidationResult;
import webbroker3.equity.define.WEB3MarginTradeTypeDef;
import webbroker3.equity.message.WEB3MarginOpenMarginCompleteRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginCompleteResponse;
import webbroker3.equity.message.WEB3MarginOpenMarginConfirmRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginConfirmResponse;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondParams;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EquityCommAccountCondMstParams;
import webbroker3.gentrade.data.EquityCommCondMstParams;
import webbroker3.gentrade.data.EquityCommCondParams;
import webbroker3.gentrade.data.InstBranchProductParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3MarginOpenMarginServiceImplTest070619 extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginOpenMarginServiceImplTest070619.class);
    public WEB3MarginOpenMarginServiceImplTest070619(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
 
    public void test_validateOrder_C0001()
    {
        final String STR_METHOD_NAME = " test_validateOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(1001L));

            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(1002L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    WEB3ChannelDef.BRANCH);

            WEB3EquityEstimatedContractPrice l_equityEstimatedContractPrice =
                new WEB3EquityEstimatedContractPrice();
            l_equityEstimatedContractPrice.setCalcUnitPrice(1D);
            l_equityEstimatedContractPrice.setEstimatedContractPrice(2D);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "calcContractAmountAtOrder", new Class[]
                    { WEB3GentradeCommission.class, double.class, double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class, String.class,
                            String.class, String.class, boolean.class, boolean.class, SubAccount.class,
                            EqTypeTradedProduct.class, double.class, double.class, double.class, boolean.class },
                            l_equityEstimatedContractPrice);

            ProcessingResult l_result = ProcessingResult.newSuccessResultInstance();
            WEB3MarginNewOrderValidationResult l_newOrderValidationResult =
                new WEB3MarginNewOrderValidationResult(l_result, false);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                "validateOpenContractOrder", new Class[]
                { SubAccount.class, EqTypeOpenContractOrderSpec.class},
                l_newOrderValidationResult );

            WEB3GentradeCommission l_gentradeCommission = new WEB3GentradeCommission();
            l_gentradeCommission.setBranchId(33381);
            l_gentradeCommission.setCommissionProductCode("10");
            l_gentradeCommission.setInstitutionCode("0D");
            l_gentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET);
            Timestamp l_tsDevidendRight = new Timestamp(WEB3DateUtility.getDate("20070622","yyyyMMdd").getTime());
            l_gentradeCommission.setOrderBizDate(l_tsDevidendRight);
            l_gentradeCommission.setPayType("12");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityBizLogicProvider",
                    "createCommission",
                    new Class[]{WEB3GentradeSubAccount.class,String.class,Date.class,String.class,String.class,double.class,OrderCategEnum.class},
                    l_gentradeCommission);

            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_tpResult);

            EqTypeOrderSubmissionResult l_eqTypeOrderSubmissionResult = new EqTypeOrderSubmissionResult(l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "submitOpenContractOrder", new Class[]
                    { SubAccount.class, EqTypeOpenContractOrderSpec.class, long.class, String.class, boolean.class },
                    l_eqTypeOrderSubmissionResult);

            WEB3MarginOpenMarginConfirmRequest l_request = new WEB3MarginOpenMarginConfirmRequest();
            l_request.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            l_request.limitPrice = null;
            l_request.priceCondType = WEB3PriceConditionDef.DEFAULT;
            l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
            l_request.expirationDate = WEB3DateUtility.getDate("20070619","yyyyMMdd");
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.stopOrderCondPrice = "0";
            l_request.stopOrderCondOperator = null;
            l_request.wlimitOrderCondPrice = "0";
            l_request.wlimitOrderCondOperator = null;
            l_request.wLimitOrderPriceDiv = null;
            l_request.wLimitPrice = "0";
            l_request.wlimitExecCondType = null;
            l_request.orderQuantity = null;
            l_request.tradingType = WEB3MarginTradeTypeDef.OPEN_LONG_MARGIN;
            l_request.productCode = "1";
            l_request.marketCode = WEB3MarketCodeDef.TOKYO;
            WEB3MarginRepaymentUnit l_repayment = new WEB3MarginRepaymentUnit();
            l_repayment.repaymentDiv = WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS;
            l_repayment.repaymentTimeLimit = "1000";
            l_request.repayment = l_repayment;
            l_request.taxType = WEB3TaxTypeSpecialDef.NORMAL;
            l_request.orderQuantity = "123";

            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080000L);
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setProductId(330304148080000L);
            l_tradedProductParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setInstitutionCode("0D");
            l_marketParams.setMarketCode("1");
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionId(33L);
            l_subAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setResident(WEB3ResidentDef.SPE_NON_RESIDENT);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(1002L);
            l_traderParams.setBranchCode("3D");
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setMarketCode("1");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setMarketCode("1");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(330304148080000L);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(330304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(BranchMarketRepayDealtCondParams.TYPE);
            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams =
                TestDBUtility.getBranchMarketRepayDealCondRow();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            l_branchMarketRepayDealtCondParams.setMarketCode("1");
            l_branchMarketRepayDealtCondParams.setRepaymentDiv("1");
            l_branchMarketRepayDealtCondParams.setRepaymentNum(1000);
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);

            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("123");
            l_branchMarketRepayDealtCondParams.setMarketCode("1");
            l_branchMarketRepayDealtCondParams.setRepaymentDiv("1");
            l_branchMarketRepayDealtCondParams.setRepaymentNum(1000);
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);

            TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("1");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(33381);
            l_instBranchProductParams.setCommissionProductCode("10");
            l_instBranchProductParams.setCommissionFeeCondFlag("F");
            l_instBranchProductParams.setEstimatePriceCalcForm(1);
            l_instBranchProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070619","yyyyMMdd"));
            l_instBranchProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070619","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_instBranchProductParams);

            TestDBUtility.deleteAll(EquityCommAccountCondMstParams.TYPE);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams =
                TestDBUtility.getEquityCommAccountCondMstRow();
            l_equityCommAccountCondMstParams.setInstitutionCode("0D");
            l_equityCommAccountCondMstParams.setBranchId(33381);
            l_equityCommAccountCondMstParams.setAccountId(1001L);
            l_equityCommAccountCondMstParams.setCommissionNo("10");
            l_equityCommAccountCondMstParams.setCommProductCode("10");

            Date l_datDevidendRightDate = GtlUtils.getSystemTimestamp();

            Timestamp l_tsDevidendRightDate = new Timestamp(l_datDevidendRightDate.getTime());

            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_tsDevidendRightDate);
            l_equityCommAccountCondMstParams.setCreatedTimestamp(l_gentradeBizDate.roll(1));
            l_equityCommAccountCondMstParams.setLastUpdatedTimestamp(l_gentradeBizDate.roll(1));
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20070622");
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);

            TestDBUtility.deleteAll(EquityCommCondParams.TYPE);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("0D");
            l_equityCommCondParams.setCommProductCode("10");
            l_equityCommCondParams.setRegNo("10 ");
            l_equityCommCondParams.setMaxTurnover(1D);
            l_equityCommCondParams.setMinTurnover(-1d);
            l_equityCommCondParams.setChargeRatio(1D);
            l_equityCommCondParams.setAddedPrice(0D);
            l_equityCommCondParams.setSequenceNo("1");
            l_equityCommCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070622","yyyyMMdd"));
            l_equityCommCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070622","yyyyMMdd"));
            l_equityCommCondParams.setCommitionPerUnit(100L);
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20070622","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20070622","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_equityCommCondParams);

            TestDBUtility.deleteAll(EquityCommCondMstParams.TYPE);
            EquityCommCondMstParams l_equityCommCondMstParams = new EquityCommCondMstParams();
            l_equityCommCondMstParams.setInstitutionCode("0D");
            l_equityCommCondMstParams.setCommProductCode("10");
            l_equityCommCondMstParams.setRegNo("10 ");
            l_equityCommCondMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070622","yyyyMMdd"));
            l_equityCommCondMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070622","yyyyMMdd"));
            l_equityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20070620","yyyyMMdd"));
            l_equityCommCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20070625","yyyyMMdd"));
            l_equityCommCondMstParams.setMaxCommission(1D);
            l_equityCommCondMstParams.setMinCommission(-1d);
            l_equityCommCondMstParams.setCommissionCourseDiv("1");
            TestDBUtility.insertWithDel(l_equityCommCondMstParams);

            WEB3MarginOpenMarginServiceImpl l_marginOpenMarginServiceImpl =
                new WEB3MarginOpenMarginServiceImpl();
            WEB3MarginOpenMarginConfirmResponse l_response =
                l_marginOpenMarginServiceImpl.validateOrder(l_request);
            Date l_datExpirationDate = l_response.expirationDate;
            int i_int = WEB3DateUtility.compareToDay(
                    WEB3DateUtility.getDate("20070619","yyyyMMdd"),l_datExpirationDate);
            assertEquals(0, i_int);

            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class});
            assertTrue(!((Boolean)l_paramsValue1.getFirstCalled()[4]).booleanValue());

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_submitOrder_C0001()
    {
        final String STR_METHOD_NAME = " test_submitOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(1001L));

            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(1002L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    WEB3ChannelDef.BRANCH);

            WEB3EquityEstimatedContractPrice l_equityEstimatedContractPrice =
                new WEB3EquityEstimatedContractPrice();
            l_equityEstimatedContractPrice.setCalcUnitPrice(1D);
            l_equityEstimatedContractPrice.setEstimatedContractPrice(2D);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "calcContractAmountAtOrder", new Class[]
                    { WEB3GentradeCommission.class, double.class, double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class, String.class,
                            String.class, String.class, boolean.class, boolean.class, SubAccount.class,
                            EqTypeTradedProduct.class, double.class, double.class, double.class, boolean.class },
                            l_equityEstimatedContractPrice);

            ProcessingResult l_result = ProcessingResult.newSuccessResultInstance();
            WEB3MarginNewOrderValidationResult l_newOrderValidationResult =
                new WEB3MarginNewOrderValidationResult(l_result, false);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                "validateOpenContractOrder", new Class[]
                { SubAccount.class, EqTypeOpenContractOrderSpec.class},
                l_newOrderValidationResult );

            WEB3GentradeCommission l_gentradeCommission = new WEB3GentradeCommission();
            l_gentradeCommission.setBranchId(33381);
            l_gentradeCommission.setCommissionProductCode("10");
            l_gentradeCommission.setInstitutionCode("0D");
            l_gentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET);
            Timestamp l_tsDevidendRight = new Timestamp(WEB3DateUtility.getDate("20070622","yyyyMMdd").getTime());
            l_gentradeCommission.setOrderBizDate(l_tsDevidendRight);
            l_gentradeCommission.setPayType("12");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityBizLogicProvider",
                    "createCommission",
                    new Class[]{WEB3GentradeSubAccount.class,String.class,Date.class,String.class,String.class,double.class,OrderCategEnum.class},
                    l_gentradeCommission);

            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_tpResult);

            EqTypeOrderSubmissionResult l_eqTypeOrderSubmissionResult = new EqTypeOrderSubmissionResult(l_result);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "submitOpenContractOrder", new Class[]
                    { SubAccount.class, EqTypeOpenContractOrderSpec.class, long.class, String.class, boolean.class },
                    l_eqTypeOrderSubmissionResult);

            WEB3MarginOpenMarginCompleteRequest l_request = new WEB3MarginOpenMarginCompleteRequest();
            l_request.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            l_request.limitPrice = null;
            l_request.priceCondType = WEB3PriceConditionDef.DEFAULT;
            l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
            l_request.expirationDate = WEB3DateUtility.getDate("20070619","yyyyMMdd");
            l_request.orderCondType = WEB3OrderingConditionDef.DEFAULT;
            l_request.stopOrderCondPrice = "0";
            l_request.stopOrderCondOperator = null;
            l_request.wlimitOrderCondPrice = "0";
            l_request.wlimitOrderCondOperator = null;
            l_request.wLimitOrderPriceDiv = null;
            l_request.wLimitPrice = "0";
            l_request.wlimitExecCondType = null;
            l_request.orderQuantity = null;
            l_request.tradingType = WEB3MarginTradeTypeDef.OPEN_LONG_MARGIN;
            l_request.productCode = "1";
            l_request.marketCode = WEB3MarketCodeDef.TOKYO;
            WEB3MarginRepaymentUnit l_repayment = new WEB3MarginRepaymentUnit();
            l_repayment.repaymentDiv = WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS;
            l_repayment.repaymentTimeLimit = "1000";
            l_request.repayment = l_repayment;
            l_request.taxType = WEB3TaxTypeSpecialDef.NORMAL;
            l_request.orderQuantity = "123";

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(1006160060005L);
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006160060005L);
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setInstitutionCode("0D");
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            

            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(1002L);
            l_traderParams.setBranchCode("123");
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setMarketCode("1");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setMarketCode("1");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(1006160060005L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("0");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            l_eqtypeProductParams.setProductId(1006160060005L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(BranchMarketRepayDealtCondParams.TYPE);
            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams =
                TestDBUtility.getBranchMarketRepayDealCondRow();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("123");
            l_branchMarketRepayDealtCondParams.setMarketCode("1");
            l_branchMarketRepayDealtCondParams.setRepaymentDiv("1");
            l_branchMarketRepayDealtCondParams.setRepaymentNum(1000);
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);

            TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("1");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(33381);
            l_instBranchProductParams.setCommissionProductCode("10");
            l_instBranchProductParams.setCommissionFeeCondFlag("F");
            l_instBranchProductParams.setEstimatePriceCalcForm(1);
            l_instBranchProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070619","yyyyMMdd"));
            l_instBranchProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070619","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_instBranchProductParams);

            TestDBUtility.deleteAll(EquityCommAccountCondMstParams.TYPE);
            EquityCommAccountCondMstParams l_equityCommAccountCondMstParams =
                TestDBUtility.getEquityCommAccountCondMstRow();
            l_equityCommAccountCondMstParams.setInstitutionCode("0D");
            l_equityCommAccountCondMstParams.setBranchId(33381);
            l_equityCommAccountCondMstParams.setAccountId(1001L);
            l_equityCommAccountCondMstParams.setCommissionNo("10");
            l_equityCommAccountCondMstParams.setCommProductCode("10");

            Date l_datDevidendRightDate = GtlUtils.getSystemTimestamp();

            Timestamp l_tsDevidendRightDate = new Timestamp(l_datDevidendRightDate.getTime());

            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_tsDevidendRightDate);
            l_equityCommAccountCondMstParams.setCreatedTimestamp(l_gentradeBizDate.roll(1));
            l_equityCommAccountCondMstParams.setLastUpdatedTimestamp(l_gentradeBizDate.roll(1));
            l_equityCommAccountCondMstParams.setValidUntilBizDate("20070622");
            TestDBUtility.insertWithDel(l_equityCommAccountCondMstParams);

            TestDBUtility.deleteAll(EquityCommCondParams.TYPE);
            EquityCommCondParams l_equityCommCondParams = new EquityCommCondParams();
            l_equityCommCondParams.setInstitutionCode("0D");
            l_equityCommCondParams.setCommProductCode("10");
            l_equityCommCondParams.setRegNo("10 ");
            l_equityCommCondParams.setMaxTurnover(1D);
            l_equityCommCondParams.setMinTurnover(-1d);
            l_equityCommCondParams.setChargeRatio(1D);
            l_equityCommCondParams.setAddedPrice(0D);
            l_equityCommCondParams.setSequenceNo("1");
            l_equityCommCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070622","yyyyMMdd"));
            l_equityCommCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070622","yyyyMMdd"));
            l_equityCommCondParams.setCommitionPerUnit(100L);
            l_equityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20070622","yyyyMMdd"));
            l_equityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20070622","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_equityCommCondParams);

            TestDBUtility.deleteAll(EquityCommCondMstParams.TYPE);
            EquityCommCondMstParams l_equityCommCondMstParams = new EquityCommCondMstParams();
            l_equityCommCondMstParams.setInstitutionCode("0D");
            l_equityCommCondMstParams.setCommProductCode("10");
            l_equityCommCondMstParams.setRegNo("10 ");
            l_equityCommCondMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070622","yyyyMMdd"));
            l_equityCommCondMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070622","yyyyMMdd"));
            l_equityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20070620","yyyyMMdd"));
            l_equityCommCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20070625","yyyyMMdd"));
            l_equityCommCondMstParams.setMaxCommission(1D);
            l_equityCommCondMstParams.setMinCommission(-1d);
            l_equityCommCondMstParams.setCommissionCourseDiv("1");
            TestDBUtility.insertWithDel(l_equityCommCondMstParams);

            WEB3MarginOpenMarginServiceImpl l_marginOpenMarginServiceImpl =
                new WEB3MarginOpenMarginServiceImpl();
            WEB3MarginOpenMarginCompleteResponse l_response =
                l_marginOpenMarginServiceImpl.submitOrder(l_request);
            Date l_datExpirationDate = l_response.expirationDate;
            int i_int = WEB3DateUtility.compareToDay(
                WEB3DateUtility.getDate("20070619","yyyyMMdd"),l_datExpirationDate);
            assertEquals(0, i_int);

            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class});
            assertTrue(((Boolean)l_paramsValue1.getFirstCalled()[4]).booleanValue());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.46.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondDomesticApplyServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.service.delegate.stdimpls;

import java.sql.Timestamp;

import test.util.TestDBUtility;
import webbroker3.bd.WEB3BondDomesticOrderUpdateInterceptor;
import webbroker3.bd.WEB3BondNewOrderSpec;
import webbroker3.bd.data.BondBranchConditionParams;
import webbroker3.bd.data.BondBranchRecruitLimitParams;
import webbroker3.bd.data.BondOrderAcceptActionParams;
import webbroker3.bd.message.WEB3BondDomesticApplyCommonRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyCompleteRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyCompleteResponse;
import webbroker3.bd.message.WEB3BondDomesticApplyConfirmRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyConfirmResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchRecruitLimitDivDef;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.OrderUnitIntroduceDivParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

public class WEB3BondDomesticApplyServiceImplTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticApplyServiceImplTest.class);

    public WEB3BondDomesticApplyServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        MOCK_MANAGER.setIsMockUsed(true);
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void test_execute_0001()
    {
        final String STR_METHOD_NAME =
            " test_execute_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3BondDomesticApplyServiceImpl l_bondDomesticApplyServiceImpl =
                new WEB3BondDomesticApplyServiceImpl();
            l_bondDomesticApplyServiceImpl.execute(null);
            fail();
        }
        catch (WEB3SystemLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public void test_execute_0002()
    {
        final String STR_METHOD_NAME =
            " test_execute_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3GenRequest l_request = new WEB3BondDomesticApplyCommonRequest();
            WEB3BondDomesticApplyServiceImpl l_bondDomesticApplyServiceImpl =
                new WEB3BondDomesticApplyServiceImpl();
            l_bondDomesticApplyServiceImpl.execute(l_request);
            fail();
        }
        catch (WEB3SystemLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public void test_execute_0003()
    {
        final String STR_METHOD_NAME =
            " test_execute_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                WEB3ChannelDef.CALL_CENTER);

            LoginInfoImpl l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                    Object[].class, OrderTypeEnum.class, boolean.class},
                l_result);

            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BondBranchRecruitLimitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);

            //TradingTimeParams
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("240000");
            l_tradingTimeParams.setBranchCode("123");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(0L);
            TestDBUtility.insertWithDel(l_traderParams);

            BondOrderAcceptActionParams l_bondOrderAcceptActionParams =
                this.getBondOrderAcceptActionRow();
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123456L);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setQuantity(111D);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            l_bondOrderUnitParams.setOrderUnitId(123457L);
            l_bondOrderUnitParams.setQuantity(2.2D);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(1));
            l_bondOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_bondOrderUnitParams.setLastUpdatedTimestamp(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            BondBranchRecruitLimitParams l_bondBranchRecruitLimitParams = new BondBranchRecruitLimitParams();
            l_bondBranchRecruitLimitParams.setProductId(123L);
            l_bondBranchRecruitLimitParams.setInstitutionCode("0D");
            l_bondBranchRecruitLimitParams.setBranchCode("---");
            l_bondBranchRecruitLimitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setRecruitLimit(111D);
            TestDBUtility.insertWithDel(l_bondBranchRecruitLimitParams);

            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchId(1001L);
            l_bondBranchConditionParams.setBranchRecruitLimitDiv(
                WEB3BranchRecruitLimitDivDef.BRANCH_LIMIT_NOT);
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setSex("1");
            l_mainAccountParams.setBranchId(1001L);
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123L);
            l_bondProductParams.setTradeHandleDiv("2");
            l_bondProductParams.setTradeEndDate(l_gentradeBizDate.roll(2));
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setTradeType("3");
            l_bondProductParams.setMinFaceAmount(111D);
            l_bondProductParams.setTradeUnit(111D);
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123L);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(2L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3BondDomesticApplyConfirmRequest l_request = new WEB3BondDomesticApplyConfirmRequest();
            l_request.productId = "123";
            l_request.applyAmount = "111";

            WEB3BondDomesticApplyServiceImpl l_bondDomesticApplyServiceImpl =
                new WEB3BondDomesticApplyServiceImpl();
            l_bondDomesticApplyServiceImpl.execute(l_request);

            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                        Object[].class, OrderTypeEnum.class, boolean.class}).getFirstCalled()[0];
            assertEquals(1001L, l_subAccount.getAccountId());
            WEB3BondDomesticOrderUpdateInterceptor l_bondDomesticOrderUpdateInterceptor =
                (WEB3BondDomesticOrderUpdateInterceptor)((Object[])TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                        Object[].class, OrderTypeEnum.class, boolean.class}).getFirstCalled()[1])[0];
            WEB3BondNewOrderSpec l_bondNewOrderSpec =
                (WEB3BondNewOrderSpec)((Object[])TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                        "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                        "validateTradingPower",
                        new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                            Object[].class, OrderTypeEnum.class, boolean.class}).getFirstCalled()[2])[0];
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public void test_execute_0004()
    {
        final String STR_METHOD_NAME =
            " test_execute_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                WEB3ChannelDef.CALL_CENTER);

            LoginInfoImpl l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                    Object[].class, OrderTypeEnum.class, boolean.class},
                l_result);

            OrderSubmissionResult l_orderSubmissionResult =
                new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondOrderManager",
                "submitNewOrder",
                new Class[]{SubAccount.class,
                    ProductTypeEnum.class,
                    NewOrderSpec.class,
                    long.class,
                    String.class,
                    boolean.class},
                l_orderSubmissionResult);

            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(OrderUnitIntroduceDivParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BondBranchRecruitLimitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TestDBUtility.deleteAll(BondOrderParams.TYPE);

            BondOrderParams l_bondOrderParams = new BondOrderParams();
            l_bondOrderParams.setSubAccountId(1001L);
            l_bondOrderParams.setProductType(ProductTypeEnum.BOND);
            l_bondOrderParams.setOrderId(123L);
            l_bondOrderParams.setAccountId(1001L);
            l_bondOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_bondOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_bondOrderParams);

            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(0L);
            l_traderParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_traderParams);

            BondOrderAcceptActionParams l_bondOrderAcceptActionParams =
                this.getBondOrderAcceptActionRow();
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123L);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setQuantity(111D);
            l_bondOrderUnitParams.setOrderId(123L);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            l_bondOrderUnitParams.setOrderUnitId(123L);
            l_bondOrderUnitParams.setQuantity(2.2D);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(1));
            l_bondOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_bondOrderUnitParams.setLastUpdatedTimestamp(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            BondBranchRecruitLimitParams l_bondBranchRecruitLimitParams = new BondBranchRecruitLimitParams();
            l_bondBranchRecruitLimitParams.setProductId(123L);
            l_bondBranchRecruitLimitParams.setInstitutionCode("0D");
            l_bondBranchRecruitLimitParams.setBranchCode("---");
            l_bondBranchRecruitLimitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setRecruitLimit(111D);
            TestDBUtility.insertWithDel(l_bondBranchRecruitLimitParams);

            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchId(1001L);
            l_bondBranchConditionParams.setBranchRecruitLimitDiv(
                WEB3BranchRecruitLimitDivDef.BRANCH_LIMIT_NOT);
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setSex("1");
            l_mainAccountParams.setBranchId(1001L);
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123L);
            l_bondProductParams.setTradeHandleDiv("2");
            l_bondProductParams.setTradeEndDate(l_gentradeBizDate.roll(2));
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setTradeType("3");
            l_bondProductParams.setMinFaceAmount(111D);
            l_bondProductParams.setTradeUnit(111D);
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123L);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(2L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3BondDomesticApplyCompleteRequest l_request = new WEB3BondDomesticApplyCompleteRequest();
            l_request.id = "123";
            l_request.productId = "123";
            l_request.applyAmount = "111";
            l_request.introduceStoreDiv = "1";

            WEB3BondDomesticApplyServiceImpl l_bondDomesticApplyServiceImpl =
                new WEB3BondDomesticApplyServiceImpl();
            WEB3BondDomesticApplyCompleteResponse l_response =
                (WEB3BondDomesticApplyCompleteResponse)l_bondDomesticApplyServiceImpl.execute(l_request);
            assertEquals(l_response.lastUpdatedTimestamp, GtlUtils.getSystemTimestamp());

            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.bd.WEB3BondOrderManager",
                    "submitNewOrder",
                    new Class[]{SubAccount.class,
                        ProductTypeEnum.class,
                        NewOrderSpec.class,
                        long.class,
                        String.class,
                        boolean.class}).getFirstCalled()[0];
            assertEquals(1001L, l_subAccount.getAccountId());

            WEB3GentradeSubAccount l_genSubAccount =
                (WEB3GentradeSubAccount)TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                        Object[].class, OrderTypeEnum.class, boolean.class}).getFirstCalled()[0];
            assertEquals(1001L, l_genSubAccount.getAccountId());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public void test_validateBondDomesticApplyOrder_0001()
    {
        final String STR_METHOD_NAME =
            " test_validateBondDomesticApplyOrder_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                WEB3ChannelDef.CALL_CENTER);

            LoginInfoImpl l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                    Object[].class, OrderTypeEnum.class, boolean.class},
                l_result);

            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BondBranchRecruitLimitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);

            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(0L);
            TestDBUtility.insertWithDel(l_traderParams);

            BondOrderAcceptActionParams l_bondOrderAcceptActionParams =
                this.getBondOrderAcceptActionRow();
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123456L);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setQuantity(111D);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            l_bondOrderUnitParams.setOrderUnitId(123457L);
            l_bondOrderUnitParams.setQuantity(2.2D);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(1));
            l_bondOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_bondOrderUnitParams.setLastUpdatedTimestamp(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            BondBranchRecruitLimitParams l_bondBranchRecruitLimitParams = new BondBranchRecruitLimitParams();
            l_bondBranchRecruitLimitParams.setProductId(123L);
            l_bondBranchRecruitLimitParams.setInstitutionCode("0D");
            l_bondBranchRecruitLimitParams.setBranchCode("---");
            l_bondBranchRecruitLimitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setRecruitLimit(111D);
            TestDBUtility.insertWithDel(l_bondBranchRecruitLimitParams);

            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchId(1001L);
            l_bondBranchConditionParams.setBranchRecruitLimitDiv(
                WEB3BranchRecruitLimitDivDef.BRANCH_LIMIT_NOT);
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setSex("1");
            l_mainAccountParams.setBranchId(1001L);
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123L);
            l_bondProductParams.setTradeHandleDiv("2");
            l_bondProductParams.setTradeEndDate(l_gentradeBizDate.roll(2));
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setTradeType("3");
            l_bondProductParams.setMinFaceAmount(111D);
            l_bondProductParams.setTradeUnit(111D);
            l_bondProductParams.setBuyPrice(1.1D);
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123L);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(2L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3BondDomesticApplyConfirmRequest l_request = new WEB3BondDomesticApplyConfirmRequest();
            l_request.productId = "123";
            l_request.applyAmount = "111";

            WEB3BondDomesticApplyServiceImpl l_bondDomesticApplyServiceImpl =
                new WEB3BondDomesticApplyServiceImpl();
            WEB3BondDomesticApplyConfirmResponse l_response =
                l_bondDomesticApplyServiceImpl.validateBondDomesticApplyOrder(l_request);
            assertEquals(l_response.deliveryPrice, "1");
            assertEquals(l_response.initialInterestAdjustAmount, "0");
            assertEquals(l_response.executionUpdateDate, l_gentradeBizDate.roll(2));
            assertEquals("20070730", WEB3DateUtility.formatDate(l_response.deliveryDate, "yyyyMMdd"));

            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                        Object[].class, OrderTypeEnum.class, boolean.class}).getFirstCalled()[0];
            assertEquals(1001L, l_subAccount.getAccountId());

            WEB3BondDomesticOrderUpdateInterceptor l_bondDomesticOrderUpdateInterceptor =
                (WEB3BondDomesticOrderUpdateInterceptor)((Object[])TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                        Object[].class, OrderTypeEnum.class, boolean.class}).getFirstCalled()[1])[0];

            WEB3BondNewOrderSpec l_bondNewOrderSpec =
                (WEB3BondNewOrderSpec)((Object[])TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                        Object[].class, OrderTypeEnum.class, boolean.class}).getFirstCalled()[2])[0];
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public void test_validateBondDomesticApplyOrder_0002()
    {
        final String STR_METHOD_NAME =
            " test_validateBondDomesticApplyOrder_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                WEB3ChannelDef.CALL_CENTER);

            LoginInfoImpl l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(false);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                    Object[].class, OrderTypeEnum.class, boolean.class},
                l_result);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BondBranchRecruitLimitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);

            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(0L);
            TestDBUtility.insertWithDel(l_traderParams);

            BondOrderAcceptActionParams l_bondOrderAcceptActionParams =
                this.getBondOrderAcceptActionRow();
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123456L);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setQuantity(111D);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            l_bondOrderUnitParams.setOrderUnitId(123457L);
            l_bondOrderUnitParams.setQuantity(2.2D);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(1));
            l_bondOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_bondOrderUnitParams.setLastUpdatedTimestamp(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            BondBranchRecruitLimitParams l_bondBranchRecruitLimitParams = new BondBranchRecruitLimitParams();
            l_bondBranchRecruitLimitParams.setProductId(123L);
            l_bondBranchRecruitLimitParams.setInstitutionCode("0D");
            l_bondBranchRecruitLimitParams.setBranchCode("---");
            l_bondBranchRecruitLimitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setRecruitLimit(111D);
            TestDBUtility.insertWithDel(l_bondBranchRecruitLimitParams);

            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchId(1001L);
            l_bondBranchConditionParams.setBranchRecruitLimitDiv(
                WEB3BranchRecruitLimitDivDef.BRANCH_LIMIT_NOT);
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setSex("1");
            l_mainAccountParams.setBranchId(1001L);
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123L);
            l_bondProductParams.setTradeHandleDiv("2");
            l_bondProductParams.setTradeEndDate(l_gentradeBizDate.roll(2));
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setTradeType("3");
            l_bondProductParams.setMinFaceAmount(111D);
            l_bondProductParams.setTradeUnit(111D);
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123L);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(2L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3BondDomesticApplyConfirmRequest l_request = new WEB3BondDomesticApplyConfirmRequest();
            l_request.productId = "123";
            l_request.applyAmount = "111";

            WEB3BondDomesticApplyServiceImpl l_bondDomesticApplyServiceImpl =
                new WEB3BondDomesticApplyServiceImpl();
            l_bondDomesticApplyServiceImpl.validateBondDomesticApplyOrder(l_request);

            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                        Object[].class, OrderTypeEnum.class, boolean.class}).getFirstCalled()[0];
            assertEquals(1001L, l_subAccount.getAccountId());

            WEB3BondDomesticOrderUpdateInterceptor l_bondDomesticOrderUpdateInterceptor =
                (WEB3BondDomesticOrderUpdateInterceptor)((Object[])TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                        Object[].class, OrderTypeEnum.class, boolean.class}).getFirstCalled()[1])[0];

            WEB3BondNewOrderSpec l_bondNewOrderSpec =
                (WEB3BondNewOrderSpec)((Object[])TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                        Object[].class, OrderTypeEnum.class, boolean.class}).getFirstCalled()[2])[0];
            fail();
        }
        catch (WEB3BusinessLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01306,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public void test_validateBondDomesticApplyOrder_0003()
    {
        final String STR_METHOD_NAME =
            " test_validateBondDomesticApplyOrder_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            ErrorInfo l_errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00517;
            OrderValidationResult l_orderValidationResult =
                new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(l_errorInfo));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_orderValidationResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                WEB3ChannelDef.CALL_CENTER);

            LoginInfoImpl l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BondBranchRecruitLimitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);

            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(0L);
            TestDBUtility.insertWithDel(l_traderParams);

            BondOrderAcceptActionParams l_bondOrderAcceptActionParams =
                this.getBondOrderAcceptActionRow();
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123456L);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setQuantity(111D);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            l_bondOrderUnitParams.setOrderUnitId(123457L);
            l_bondOrderUnitParams.setQuantity(2.2D);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(1));
            l_bondOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_bondOrderUnitParams.setLastUpdatedTimestamp(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            BondBranchRecruitLimitParams l_bondBranchRecruitLimitParams = new BondBranchRecruitLimitParams();
            l_bondBranchRecruitLimitParams.setProductId(123L);
            l_bondBranchRecruitLimitParams.setInstitutionCode("0D");
            l_bondBranchRecruitLimitParams.setBranchCode("---");
            l_bondBranchRecruitLimitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setRecruitLimit(111D);
            TestDBUtility.insertWithDel(l_bondBranchRecruitLimitParams);

            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchId(1001L);
            l_bondBranchConditionParams.setBranchRecruitLimitDiv(
                WEB3BranchRecruitLimitDivDef.BRANCH_LIMIT_NOT);
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setSex("1");
            l_mainAccountParams.setBranchId(1001L);
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123L);
            l_bondProductParams.setTradeHandleDiv("2");
            l_bondProductParams.setTradeEndDate(l_gentradeBizDate.roll(2));
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setTradeType("3");
            l_bondProductParams.setMinFaceAmount(111D);
            l_bondProductParams.setTradeUnit(111D);
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123L);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(2L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3BondDomesticApplyConfirmRequest l_request = new WEB3BondDomesticApplyConfirmRequest();
            l_request.productId = "123";
            l_request.applyAmount = "111";

            WEB3BondDomesticApplyServiceImpl l_bondDomesticApplyServiceImpl =
                new WEB3BondDomesticApplyServiceImpl();
            l_bondDomesticApplyServiceImpl.validateBondDomesticApplyOrder(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00517,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public void test_validateBondDomesticApplyOrder_0004()
    {
        final String STR_METHOD_NAME =
            " test_validateBondDomesticApplyOrder_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BondBranchRecruitLimitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            WEB3BondDomesticApplyConfirmRequest l_request = new WEB3BondDomesticApplyConfirmRequest();
            l_request.productId = "123";
            l_request.applyAmount = "111";

            WEB3BondDomesticApplyServiceImpl l_bondDomesticApplyServiceImpl =
                new WEB3BondDomesticApplyServiceImpl();
            l_bondDomesticApplyServiceImpl.validateBondDomesticApplyOrder(l_request);
            fail();
        }
        catch (WEB3SystemLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }


    public void test_validateBondDomesticApplyOrder_0005()
    {
        final String STR_METHOD_NAME =
            " test_validateBondDomesticApplyOrder_0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                WEB3ChannelDef.CALL_CENTER);

            LoginInfoImpl l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BondBranchRecruitLimitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);

            //TradingTimeParams
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("240000");
            l_tradingTimeParams.setBranchCode("123");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(0L);
            TestDBUtility.insertWithDel(l_traderParams);

            BondOrderAcceptActionParams l_bondOrderAcceptActionParams =
                this.getBondOrderAcceptActionRow();
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123456L);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setQuantity(111D);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            l_bondOrderUnitParams.setOrderUnitId(123457L);
            l_bondOrderUnitParams.setQuantity(2.2D);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(1));
            l_bondOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_bondOrderUnitParams.setLastUpdatedTimestamp(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            BondBranchRecruitLimitParams l_bondBranchRecruitLimitParams = new BondBranchRecruitLimitParams();
            l_bondBranchRecruitLimitParams.setProductId(123L);
            l_bondBranchRecruitLimitParams.setInstitutionCode("0D");
            l_bondBranchRecruitLimitParams.setBranchCode("---");
            l_bondBranchRecruitLimitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setRecruitLimit(111D);
            TestDBUtility.insertWithDel(l_bondBranchRecruitLimitParams);

            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchId(1001L);
            l_bondBranchConditionParams.setBranchRecruitLimitDiv(
                WEB3BranchRecruitLimitDivDef.BRANCH_LIMIT_NOT);
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setSex("0");
            l_mainAccountParams.setBranchId(1001L);
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123L);
            l_bondProductParams.setTradeHandleDiv("2");
            l_bondProductParams.setTradeEndDate(l_gentradeBizDate.roll(2));
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setTradeType("3");
            l_bondProductParams.setMinFaceAmount(111D);
            l_bondProductParams.setTradeUnit(111D);
            l_bondProductParams.setBondType(BondTypeEnum.INDIVIDUAL_GOVERNMENT_BOND);
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123L);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(2L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3BondDomesticApplyConfirmRequest l_request = new WEB3BondDomesticApplyConfirmRequest();
            l_request.productId = "123";
            l_request.applyAmount = "111";

            WEB3BondDomesticApplyServiceImpl l_bondDomesticApplyServiceImpl =
                new WEB3BondDomesticApplyServiceImpl();
            l_bondDomesticApplyServiceImpl.validateBondDomesticApplyOrder(l_request);

            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                        Object[].class, OrderTypeEnum.class, boolean.class}).getFirstCalled()[0];
            assertEquals(1001L, l_subAccount.getAccountId());

            WEB3BondDomesticOrderUpdateInterceptor l_bondDomesticOrderUpdateInterceptor =
                (WEB3BondDomesticOrderUpdateInterceptor)((Object[])TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                        Object[].class, OrderTypeEnum.class, boolean.class}).getFirstCalled()[1])[0];

            WEB3BondNewOrderSpec l_bondNewOrderSpec =
                (WEB3BondNewOrderSpec)((Object[])TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                        Object[].class, OrderTypeEnum.class, boolean.class}).getFirstCalled()[2])[0];
            fail();
        }
        catch (WEB3BusinessLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02884,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public void test_validateBondDomesticApplyOrder_0006()
    {
        final String STR_METHOD_NAME =
            " test_validateBondDomesticApplyOrder_0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BondBranchRecruitLimitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            WEB3BondDomesticApplyConfirmRequest l_request = new WEB3BondDomesticApplyConfirmRequest();
            l_request.productId = "123";

            WEB3BondDomesticApplyServiceImpl l_bondDomesticApplyServiceImpl =
                new WEB3BondDomesticApplyServiceImpl();
            l_bondDomesticApplyServiceImpl.validateBondDomesticApplyOrder(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02880,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public void test_submitBondDomesticApplyOrder_0001()
    {
        final String STR_METHOD_NAME =
            " test_submitBondDomesticApplyOrder_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            ErrorInfo l_errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00517;
            OrderValidationResult l_orderValidationResult =
                new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(l_errorInfo));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_orderValidationResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                WEB3ChannelDef.CALL_CENTER);

            LoginInfoImpl l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BondBranchRecruitLimitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);

            //TradingTimeParams
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("240000");
            l_tradingTimeParams.setBranchCode("123");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(0L);
            TestDBUtility.insertWithDel(l_traderParams);

            BondOrderAcceptActionParams l_bondOrderAcceptActionParams =
                this.getBondOrderAcceptActionRow();
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123456L);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setQuantity(111D);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            l_bondOrderUnitParams.setOrderUnitId(123457L);
            l_bondOrderUnitParams.setQuantity(2.2D);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(1));
            l_bondOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_bondOrderUnitParams.setLastUpdatedTimestamp(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            BondBranchRecruitLimitParams l_bondBranchRecruitLimitParams = new BondBranchRecruitLimitParams();
            l_bondBranchRecruitLimitParams.setProductId(123L);
            l_bondBranchRecruitLimitParams.setInstitutionCode("0D");
            l_bondBranchRecruitLimitParams.setBranchCode("---");
            l_bondBranchRecruitLimitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setRecruitLimit(111D);
            TestDBUtility.insertWithDel(l_bondBranchRecruitLimitParams);

            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchId(1001L);
            l_bondBranchConditionParams.setBranchRecruitLimitDiv(
                WEB3BranchRecruitLimitDivDef.BRANCH_LIMIT_NOT);
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setSex("1");
            l_mainAccountParams.setBranchId(1001L);
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123L);
            l_bondProductParams.setTradeHandleDiv("2");
            l_bondProductParams.setTradeEndDate(l_gentradeBizDate.roll(2));
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setTradeType("3");
            l_bondProductParams.setMinFaceAmount(111D);
            l_bondProductParams.setTradeUnit(111D);
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123L);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(2L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3BondDomesticApplyCompleteRequest l_request = new WEB3BondDomesticApplyCompleteRequest();
            l_request.id = "123";
            l_request.productId = "123";
            l_request.applyAmount = "111";

            WEB3BondDomesticApplyServiceImpl l_bondDomesticApplyServiceImpl =
                new WEB3BondDomesticApplyServiceImpl();
            l_bondDomesticApplyServiceImpl.submitBondDomesticApplyOrder(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00517,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public void test_submitBondDomesticApplyOrder_0002()
    {
        final String STR_METHOD_NAME =
            " test_submitBondDomesticApplyOrder_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);

            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                WEB3ChannelDef.CALL_CENTER);

            LoginInfoImpl l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(false);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                    Object[].class, OrderTypeEnum.class, boolean.class},
                l_result);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BondBranchRecruitLimitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);

            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(0L);
            TestDBUtility.insertWithDel(l_traderParams);

            BondOrderAcceptActionParams l_bondOrderAcceptActionParams =
                this.getBondOrderAcceptActionRow();
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123456L);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setQuantity(111D);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            l_bondOrderUnitParams.setOrderUnitId(123457L);
            l_bondOrderUnitParams.setQuantity(2.2D);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(1));
            l_bondOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_bondOrderUnitParams.setLastUpdatedTimestamp(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            BondBranchRecruitLimitParams l_bondBranchRecruitLimitParams = new BondBranchRecruitLimitParams();
            l_bondBranchRecruitLimitParams.setProductId(123L);
            l_bondBranchRecruitLimitParams.setInstitutionCode("0D");
            l_bondBranchRecruitLimitParams.setBranchCode("---");
            l_bondBranchRecruitLimitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setRecruitLimit(111D);
            TestDBUtility.insertWithDel(l_bondBranchRecruitLimitParams);

            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchId(1001L);
            l_bondBranchConditionParams.setBranchRecruitLimitDiv(
                WEB3BranchRecruitLimitDivDef.BRANCH_LIMIT_NOT);
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setSex("1");
            l_mainAccountParams.setBranchId(1001L);
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123L);
            l_bondProductParams.setTradeHandleDiv("2");
            l_bondProductParams.setTradeEndDate(l_gentradeBizDate.roll(2));
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setTradeType("3");
            l_bondProductParams.setMinFaceAmount(111D);
            l_bondProductParams.setTradeUnit(111D);
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123L);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(2L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3BondDomesticApplyCompleteRequest l_request = new WEB3BondDomesticApplyCompleteRequest();
            l_request.id = "123";
            l_request.productId = "123";
            l_request.applyAmount = "111";

            WEB3BondDomesticApplyServiceImpl l_bondDomesticApplyServiceImpl =
                new WEB3BondDomesticApplyServiceImpl();
            l_bondDomesticApplyServiceImpl.submitBondDomesticApplyOrder(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01306,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public void test_submitBondDomesticApplyOrder_0003()
    {
        final String STR_METHOD_NAME =
            " test_submitBondDomesticApplyOrder_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                WEB3ChannelDef.CALL_CENTER);

            LoginInfoImpl l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                    Object[].class, OrderTypeEnum.class, boolean.class},
                l_result);

            OrderSubmissionResult l_orderSubmissionResult =
                new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondOrderManager",
                "submitNewOrder",
                new Class[]{SubAccount.class,
                    ProductTypeEnum.class,
                    NewOrderSpec.class,
                    long.class,
                    String.class,
                    boolean.class},
                l_orderSubmissionResult);

            TestDBUtility.deleteAll(OrderUnitIntroduceDivParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BondBranchRecruitLimitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TestDBUtility.deleteAll(BondOrderParams.TYPE);

            BondOrderParams l_bondOrderParams = new BondOrderParams();
            l_bondOrderParams.setSubAccountId(1001L);
            l_bondOrderParams.setProductType(ProductTypeEnum.BOND);
            l_bondOrderParams.setOrderId(123L);
            l_bondOrderParams.setAccountId(1001L);
            l_bondOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_bondOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_bondOrderParams);

            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(0L);
            TestDBUtility.insertWithDel(l_traderParams);

            BondOrderAcceptActionParams l_bondOrderAcceptActionParams =
                this.getBondOrderAcceptActionRow();
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123L);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setQuantity(111D);
            l_bondOrderUnitParams.setOrderId(123L);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            l_bondOrderUnitParams.setOrderUnitId(123L);
            l_bondOrderUnitParams.setQuantity(2.2D);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(1));
            l_bondOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_bondOrderUnitParams.setLastUpdatedTimestamp(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            BondBranchRecruitLimitParams l_bondBranchRecruitLimitParams = new BondBranchRecruitLimitParams();
            l_bondBranchRecruitLimitParams.setProductId(123L);
            l_bondBranchRecruitLimitParams.setInstitutionCode("0D");
            l_bondBranchRecruitLimitParams.setBranchCode("---");
            l_bondBranchRecruitLimitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setRecruitLimit(111D);
            TestDBUtility.insertWithDel(l_bondBranchRecruitLimitParams);

            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchId(1001L);
            l_bondBranchConditionParams.setBranchRecruitLimitDiv(
                WEB3BranchRecruitLimitDivDef.BRANCH_LIMIT_NOT);
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setSex("1");
            l_mainAccountParams.setBranchId(1001L);
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123L);
            l_bondProductParams.setTradeHandleDiv("2");
            l_bondProductParams.setTradeEndDate(l_gentradeBizDate.roll(2));
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setTradeType("3");
            l_bondProductParams.setMinFaceAmount(111D);
            l_bondProductParams.setTradeUnit(111D);
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123L);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(2L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3BondDomesticApplyCompleteRequest l_request = new WEB3BondDomesticApplyCompleteRequest();
            l_request.id = "123";
            l_request.productId = "123";
            l_request.applyAmount = "111";
            l_request.introduceStoreDiv = "1";

            WEB3BondDomesticApplyServiceImpl l_bondDomesticApplyServiceImpl =
                new WEB3BondDomesticApplyServiceImpl();
            WEB3BondDomesticApplyCompleteResponse l_response =
                l_bondDomesticApplyServiceImpl.submitBondDomesticApplyOrder(l_request);
            assertEquals(l_response.lastUpdatedTimestamp, GtlUtils.getSystemTimestamp());

            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.bd.WEB3BondOrderManager",
                    "submitNewOrder",
                    new Class[]{SubAccount.class,
                        ProductTypeEnum.class,
                        NewOrderSpec.class,
                        long.class,
                        String.class,
                        boolean.class}).getFirstCalled()[0];
            assertEquals(1001L, l_subAccount.getAccountId());

            WEB3GentradeSubAccount l_genSubAccount =
                (WEB3GentradeSubAccount)TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                        Object[].class, OrderTypeEnum.class, boolean.class}).getFirstCalled()[0];
            assertEquals(1001L, l_genSubAccount.getAccountId());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public void test_submitBondDomesticApplyOrder_0004()
    {
        final String STR_METHOD_NAME =
            " test_submitBondDomesticApplyOrder_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                WEB3ChannelDef.CALL_CENTER);

            LoginInfoImpl l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                    Object[].class, OrderTypeEnum.class, boolean.class},
                l_result);

            OrderSubmissionResult l_orderSubmissionResult =
                new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondOrderManager",
                "submitNewOrder",
                new Class[]{SubAccount.class,
                    ProductTypeEnum.class,
                    NewOrderSpec.class,
                    long.class,
                    String.class,
                    boolean.class},
                l_orderSubmissionResult);

            TestDBUtility.deleteAll(OrderUnitIntroduceDivParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BondBranchRecruitLimitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TestDBUtility.deleteAll(BondOrderParams.TYPE);

            BondOrderParams l_bondOrderParams = new BondOrderParams();
            l_bondOrderParams.setSubAccountId(1001L);
            l_bondOrderParams.setProductType(ProductTypeEnum.BOND);
            l_bondOrderParams.setOrderId(123L);
            l_bondOrderParams.setAccountId(1001L);
            l_bondOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_bondOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_bondOrderParams);

            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(0L);
            l_traderParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_traderParams);

            BondOrderAcceptActionParams l_bondOrderAcceptActionParams =
                this.getBondOrderAcceptActionRow();
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123L);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setQuantity(111D);
            l_bondOrderUnitParams.setOrderId(123L);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            l_bondOrderUnitParams.setOrderUnitId(123L);
            l_bondOrderUnitParams.setQuantity(2.2D);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(1));
            l_bondOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_bondOrderUnitParams.setLastUpdatedTimestamp(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            BondBranchRecruitLimitParams l_bondBranchRecruitLimitParams = new BondBranchRecruitLimitParams();
            l_bondBranchRecruitLimitParams.setProductId(123L);
            l_bondBranchRecruitLimitParams.setInstitutionCode("0D");
            l_bondBranchRecruitLimitParams.setBranchCode("---");
            l_bondBranchRecruitLimitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setRecruitLimit(111D);
            TestDBUtility.insertWithDel(l_bondBranchRecruitLimitParams);

            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchId(1001L);
            l_bondBranchConditionParams.setBranchRecruitLimitDiv(
                WEB3BranchRecruitLimitDivDef.BRANCH_LIMIT_NOT);
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setSex("1");
            l_mainAccountParams.setBranchId(1001L);
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123L);
            l_bondProductParams.setTradeHandleDiv("2");
            l_bondProductParams.setTradeEndDate(l_gentradeBizDate.roll(2));
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setTradeType("3");
            l_bondProductParams.setMinFaceAmount(111D);
            l_bondProductParams.setTradeUnit(111D);
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123L);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(2L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3BondDomesticApplyCompleteRequest l_request = new WEB3BondDomesticApplyCompleteRequest();
            l_request.id = "123";
            l_request.productId = "123";
            l_request.applyAmount = "111";
            l_request.introduceStoreDiv = "1";

            WEB3BondDomesticApplyServiceImpl l_bondDomesticApplyServiceImpl =
                new WEB3BondDomesticApplyServiceImpl();
            WEB3BondDomesticApplyCompleteResponse l_response =
                l_bondDomesticApplyServiceImpl.submitBondDomesticApplyOrder(l_request);
            assertEquals(l_response.lastUpdatedTimestamp, GtlUtils.getSystemTimestamp());

            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.bd.WEB3BondOrderManager",
                    "submitNewOrder",
                    new Class[]{SubAccount.class,
                        ProductTypeEnum.class,
                        NewOrderSpec.class,
                        long.class,
                        String.class,
                        boolean.class}).getFirstCalled()[0];
            assertEquals(1001L, l_subAccount.getAccountId());

            WEB3GentradeSubAccount l_genSubAccount =
                (WEB3GentradeSubAccount)TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                        Object[].class, OrderTypeEnum.class, boolean.class}).getFirstCalled()[0];
            assertEquals(1001L, l_genSubAccount.getAccountId());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public void test_submitBondDomesticApplyOrder_0005()
    {
        final String STR_METHOD_NAME =
            " test_submitBondDomesticApplyOrder_0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                WEB3ChannelDef.CALL_CENTER);

            LoginInfoImpl l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                    Object[].class, OrderTypeEnum.class, boolean.class},
                l_result);

            ErrorInfo l_errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
            OrderSubmissionResult l_orderSubmissionResult =
                new OrderSubmissionResult(ProcessingResult.newFailedResultInstance(l_errorInfo));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondOrderManager",
                "submitNewOrder",
                new Class[]{SubAccount.class,
                    ProductTypeEnum.class,
                    NewOrderSpec.class,
                    long.class,
                    String.class,
                    boolean.class},
                l_orderSubmissionResult);

            TestDBUtility.deleteAll(OrderUnitIntroduceDivParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BondBranchRecruitLimitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TestDBUtility.deleteAll(BondOrderParams.TYPE);

            BondOrderParams l_bondOrderParams = new BondOrderParams();
            l_bondOrderParams.setSubAccountId(1001L);
            l_bondOrderParams.setProductType(ProductTypeEnum.BOND);
            l_bondOrderParams.setOrderId(123L);
            l_bondOrderParams.setAccountId(1001L);
            l_bondOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_bondOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_bondOrderParams);

            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(0L);
            l_traderParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_traderParams);

            BondOrderAcceptActionParams l_bondOrderAcceptActionParams =
                this.getBondOrderAcceptActionRow();
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123L);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setQuantity(111D);
            l_bondOrderUnitParams.setOrderId(123L);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            l_bondOrderUnitParams.setOrderUnitId(123L);
            l_bondOrderUnitParams.setQuantity(2.2D);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(1));
            l_bondOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_bondOrderUnitParams.setLastUpdatedTimestamp(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            BondBranchRecruitLimitParams l_bondBranchRecruitLimitParams = new BondBranchRecruitLimitParams();
            l_bondBranchRecruitLimitParams.setProductId(123L);
            l_bondBranchRecruitLimitParams.setInstitutionCode("0D");
            l_bondBranchRecruitLimitParams.setBranchCode("---");
            l_bondBranchRecruitLimitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setRecruitLimit(111D);
            TestDBUtility.insertWithDel(l_bondBranchRecruitLimitParams);

            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchId(1001L);
            l_bondBranchConditionParams.setBranchRecruitLimitDiv(
                WEB3BranchRecruitLimitDivDef.BRANCH_LIMIT_NOT);
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setSex("1");
            l_mainAccountParams.setBranchId(1001L);
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123L);
            l_bondProductParams.setTradeHandleDiv("2");
            l_bondProductParams.setTradeEndDate(l_gentradeBizDate.roll(2));
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setTradeType("3");
            l_bondProductParams.setMinFaceAmount(111D);
            l_bondProductParams.setTradeUnit(111D);
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123L);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(2L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3BondDomesticApplyCompleteRequest l_request = new WEB3BondDomesticApplyCompleteRequest();
            l_request.id = "123";
            l_request.productId = "123";
            l_request.applyAmount = "111";
            l_request.introduceStoreDiv = "1";

            WEB3BondDomesticApplyServiceImpl l_bondDomesticApplyServiceImpl =
                new WEB3BondDomesticApplyServiceImpl();
            l_bondDomesticApplyServiceImpl.submitBondDomesticApplyOrder(l_request);
            fail();
        }
        catch (WEB3SystemLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public void test_submitBondDomesticApplyOrder_0006()
    {
        final String STR_METHOD_NAME =
            " test_submitBondDomesticApplyOrder_0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BondBranchRecruitLimitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            WEB3BondDomesticApplyCompleteRequest l_request = new WEB3BondDomesticApplyCompleteRequest();
            l_request.id = "123";
            l_request.productId = "123";
            l_request.introduceStoreDiv = "1";

            WEB3BondDomesticApplyServiceImpl l_bondDomesticApplyServiceImpl =
                new WEB3BondDomesticApplyServiceImpl();
            l_bondDomesticApplyServiceImpl.submitBondDomesticApplyOrder(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02880,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public void test_submitBondDomesticApplyOrder_0007()
    {
        final String STR_METHOD_NAME =
            " test_submitBondDomesticApplyOrder_0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BondBranchRecruitLimitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            WEB3BondDomesticApplyCompleteRequest l_request = new WEB3BondDomesticApplyCompleteRequest();
            l_request.id = "123";
            l_request.productId = "123";
            l_request.introduceStoreDiv = "1";
            l_request.applyAmount = "111";

            WEB3BondDomesticApplyServiceImpl l_bondDomesticApplyServiceImpl =
                new WEB3BondDomesticApplyServiceImpl();
            l_bondDomesticApplyServiceImpl.submitBondDomesticApplyOrder(l_request);
            fail();
        }
        catch (WEB3SystemLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public void test_submitBondDomesticApplyOrder_0008()
    {
        final String STR_METHOD_NAME =
            " test_submitBondDomesticApplyOrder_0008()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                WEB3ChannelDef.CALL_CENTER);

            LoginInfoImpl l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BondBranchRecruitLimitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);

            //TradingTimeParams
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("240000");
            l_tradingTimeParams.setBranchCode("123");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(0L);
            TestDBUtility.insertWithDel(l_traderParams);

            BondOrderAcceptActionParams l_bondOrderAcceptActionParams =
                this.getBondOrderAcceptActionRow();
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123456L);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setQuantity(111D);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            l_bondOrderUnitParams.setOrderUnitId(123457L);
            l_bondOrderUnitParams.setQuantity(2.2D);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(1));
            l_bondOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_bondOrderUnitParams.setLastUpdatedTimestamp(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            BondBranchRecruitLimitParams l_bondBranchRecruitLimitParams = new BondBranchRecruitLimitParams();
            l_bondBranchRecruitLimitParams.setProductId(123L);
            l_bondBranchRecruitLimitParams.setInstitutionCode("0D");
            l_bondBranchRecruitLimitParams.setBranchCode("---");
            l_bondBranchRecruitLimitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setRecruitLimit(111D);
            TestDBUtility.insertWithDel(l_bondBranchRecruitLimitParams);

            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchId(1001L);
            l_bondBranchConditionParams.setBranchRecruitLimitDiv(
                WEB3BranchRecruitLimitDivDef.BRANCH_LIMIT_NOT);
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setSex("0");
            l_mainAccountParams.setBranchId(1001L);
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123L);
            l_bondProductParams.setTradeHandleDiv("2");
            l_bondProductParams.setTradeEndDate(l_gentradeBizDate.roll(2));
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setTradeType("3");
            l_bondProductParams.setMinFaceAmount(111D);
            l_bondProductParams.setTradeUnit(111D);
            l_bondProductParams.setBondType(BondTypeEnum.INDIVIDUAL_GOVERNMENT_BOND);
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123L);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(2L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3BondDomesticApplyCompleteRequest l_request = new WEB3BondDomesticApplyCompleteRequest();
            l_request.id = "123";
            l_request.productId = "123";
            l_request.introduceStoreDiv = "1";
            l_request.applyAmount = "111";

            WEB3BondDomesticApplyServiceImpl l_bondDomesticApplyServiceImpl =
                new WEB3BondDomesticApplyServiceImpl();
            l_bondDomesticApplyServiceImpl.submitBondDomesticApplyOrder(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02884,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public BondOrderAcceptActionParams getBondOrderAcceptActionRow()
    {
        BondOrderAcceptActionParams l_bondOrderAcceptActionParams = new BondOrderAcceptActionParams();

        l_bondOrderAcceptActionParams.setProductId(1001L);
        l_bondOrderAcceptActionParams.setBranchCode("123");
        l_bondOrderAcceptActionParams.setInstitutionCode("0D");
        l_bondOrderAcceptActionParams.setTotalOrderAmount(1.1D);
        l_bondOrderAcceptActionParams.setOrderAcceptDate(GtlUtils.getSystemTimestamp());
        l_bondOrderAcceptActionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_bondOrderAcceptActionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_bondOrderAcceptActionParams;
    }
}
@

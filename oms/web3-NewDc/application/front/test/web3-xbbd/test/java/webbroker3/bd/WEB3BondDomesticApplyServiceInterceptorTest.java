head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.02.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondDomesticApplyServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd;

import java.sql.Timestamp;

import test.util.TestDBUtility;
import webbroker3.bd.data.BondBranchConditionParams;
import webbroker3.bd.data.BondBranchRecruitLimitParams;
import webbroker3.bd.data.BondOrderAcceptActionParams;
import webbroker3.bd.message.WEB3BondDomesticApplyCompleteRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyConfirmRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BranchRecruitLimitDivDef;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.OrderUnitIntroduceDivParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
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
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

public class WEB3BondDomesticApplyServiceInterceptorTest extends TestBaseForMock
{

    public WEB3BondDomesticApplyServiceInterceptorTest(String arg0)
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

    WEB3BondDomesticApplyServiceInterceptor l_interceptor =
        new WEB3BondDomesticApplyServiceInterceptor();
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticApplyServiceInterceptorTest.class);

    public void testOnReturn()
    {
        final String STR_METHOD_NAME = "testOnReturn()";
        log.entering(STR_METHOD_NAME);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
                "123");

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG,
                "456");

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                "789");

            l_interceptor.onReturn(null, null);

            Object l_result1 =
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

            Object l_result2 =
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.OFFSET_TAG);

            Object l_result3 =
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            assertNull(l_result1);
            assertNull(l_result2);
            assertNull(l_result3);
        }
        catch (Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testOnThrowable()
    {
        final String STR_METHOD_NAME = "testOnThrowable()";
        log.entering(STR_METHOD_NAME);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
                "123");

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG,
                "456");

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                "789");

            l_interceptor.onThrowable(null, null);

            Object l_result1 =
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

            Object l_result2 =
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.OFFSET_TAG);

            Object l_result3 =
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            assertNull(l_result1);
            assertNull(l_result2);
            assertNull(l_result3);
        }
        catch (Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testOnCallT_01()
    {
        final String STR_METHOD_NAME = "testOnCallT_01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_interceptor.onCall(null, null);
            fail();
        }
        catch(WEB3BaseRuntimeException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_exc.getErrorInfo());
        }
        catch (Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testOnCallT_02()
    {
        final String STR_METHOD_NAME = "testOnCallT_02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_interceptor.onCall(null, new Object[0]);
            fail();
        }
        catch(WEB3BaseRuntimeException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_exc.getErrorInfo());
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testOnCallT_03()
    {
        final String STR_METHOD_NAME = "testOnCallT_3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

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
            l_branchParams.setBranchId(1001L);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123456L);
            l_bondOrderUnitParams.setBranchId(1001L);
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


            WEB3GentradeTradingClendarContext l_tmpClendarContext = new WEB3GentradeTradingClendarContext();
            l_tmpClendarContext.setInstitutionCode("0D");
            l_tmpClendarContext.setBranchCode("123");
            //  取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_tmpClendarContext.setMarketCode("0");

            //  取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
            l_tmpClendarContext.setProductCode("0");

            l_tmpClendarContext.setTradingTimeType("36");
            l_tmpClendarContext.setOrderAcceptProduct("28");

            l_tmpClendarContext.setOrderAcceptTransaction("11");
            l_tmpClendarContext.setBizDateType("1");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_tmpClendarContext);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();

            WEB3BondDomesticApplyConfirmRequest l_request = new WEB3BondDomesticApplyConfirmRequest();
            l_request.productId = "123";
            l_request.applyAmount = "111";

            Object[] l_objs = {l_request};
            l_interceptor.onCall(null, l_objs);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testOnCallT_04()
    {
        final String STR_METHOD_NAME = "testOnCallT_04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3GentradeTradingClendarContext l_tmpClendarContext = new WEB3GentradeTradingClendarContext();
            l_tmpClendarContext.setInstitutionCode("0D");
            l_tmpClendarContext.setBranchCode("123");
            //  取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_tmpClendarContext.setMarketCode("0");

            //  取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
            l_tmpClendarContext.setProductCode("0");

            l_tmpClendarContext.setTradingTimeType("36");
            l_tmpClendarContext.setOrderAcceptProduct("28");

            l_tmpClendarContext.setOrderAcceptTransaction("11");
            l_tmpClendarContext.setBizDateType("1");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_tmpClendarContext);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();

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

            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(OrderUnitIntroduceDivParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
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
            l_branchParams.setBranchId(1001L);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123L);
            l_bondOrderUnitParams.setBranchId(1001L);
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
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setInstitutionId(33L);
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

            Object[] l_objs = {l_request};
            l_interceptor.onCall(null, l_objs);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testOnCallT_05()
    {
        final String STR_METHOD_NAME = "testOnCallT_05()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("1001"));

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);

            l_interceptor.onCall(null, new Object[]{"dsfdsf"});
            fail();
        }
        catch(WEB3BaseRuntimeException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_exc.getErrorInfo());
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testOnCallT_06()
    {
        final String STR_METHOD_NAME = "testOnCallT_06()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3GentradeTradingClendarContext l_tmpClendarContext = new WEB3GentradeTradingClendarContext();
            l_tmpClendarContext.setInstitutionCode("0D");
            l_tmpClendarContext.setBranchCode("123");
            //  取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_tmpClendarContext.setMarketCode("0");

            //  取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
            l_tmpClendarContext.setProductCode("0");

            l_tmpClendarContext.setTradingTimeType("36");
            l_tmpClendarContext.setOrderAcceptProduct("28");

            l_tmpClendarContext.setOrderAcceptTransaction("11");
            l_tmpClendarContext.setBizDateType("1");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_tmpClendarContext);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();

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

            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(OrderUnitIntroduceDivParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
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
            l_branchParams.setBranchId(1001L);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123L);
            l_bondOrderUnitParams.setBranchId(1001L);
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
            l_mainAccountParams.setBranchCode("222");
            l_mainAccountParams.setAccountCode("1");
            l_mainAccountParams.setInstitutionId(33L);
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

            Object[] l_objs = {l_request};
            l_interceptor.onCall(null, l_objs);
            fail();
        }
        catch(WEB3BaseRuntimeException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_exc.getErrorInfo());
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
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

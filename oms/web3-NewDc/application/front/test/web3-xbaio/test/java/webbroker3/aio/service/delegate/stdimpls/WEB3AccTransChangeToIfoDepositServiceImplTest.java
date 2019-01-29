head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.36.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccTransChangeToIfoDepositServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;


import test.util.TestDBUtility;
import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositCompleteRequest;
import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositCompleteResponse;
import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositConfirmRequest;
import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositConfirmResponse;
import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositInputRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.ifodeposit.WEB3IfoDepositCalc;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccTransChangeToIfoDepositServiceImplTest extends
        TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccTransChangeToIfoDepositServiceImplTest.class);

    public WEB3AccTransChangeToIfoDepositServiceImpl l_impl= null;

    public WEB3AccTransChangeToIfoDepositServiceImplTest(String arg0)
    {
        super(arg0);
        l_impl = new WEB3AccTransChangeToIfoDepositServiceImpl();
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * リクエストデータ ==NULL
     */
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3GenRequest l_request = null;

        try
        {
            l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * リクエストデータ ==NULL
     */
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3GenRequest l_request = new WEB3AccTransChangeToIfoDepositConfirmRequest();
        ((WEB3AccTransChangeToIfoDepositConfirmRequest)l_request).changeAmt = null;

        try
        {
            l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00759, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * リクエストデータ ==NULL
     */
    public void testExecute_C0003()
    {
        final String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3GenRequest l_request = new WEB3AccTransChangeToIfoDepositCompleteRequest();
        ((WEB3AccTransChangeToIfoDepositCompleteRequest)l_request).changeAmt = "-10";

        try
        {
            l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00809, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * リクエストデータ ==NULL
     */
    public void testExecute_C0004()
    {
        final String STR_METHOD_NAME = "testExecute_C0004()";
        log.entering(STR_METHOD_NAME);

        WEB3GenRequest l_request = new WEB3AccTransChangeToIfoDepositInputRequest();

        try
        {
            l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * リクエストデータ.振替金額 = null
     */
    public void testValidateOrder_C0001()
    {
        final String STR_METHOD_NAME = "testValidateOrder_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AccTransChangeToIfoDepositConfirmRequest l_request =
            new WEB3AccTransChangeToIfoDepositConfirmRequest();
        l_request.changeAmt = null;

        try
        {
            l_impl.validateOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00759, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 取引余力結果.get取引可能額()の戻り値の取引余力結果.判定フラグ == false の場合
     */
    public void testValidateOrder_C0002()
    {
        final String STR_METHOD_NAME = "testValidateOrder_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AccTransChangeToIfoDepositConfirmRequest l_request =
            new WEB3AccTransChangeToIfoDepositConfirmRequest();
        l_request.changeAmt = "100";

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(123L);
            l_aioOrderUnitParams.setSubAccountId(124L);
            l_aioOrderUnitParams.setBizDate(
                WEB3DateUtility.formatDate(
                    WEB3GentradeTradingTimeManagement.getOrderBizDate(), "yyyyMMdd"));
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.CASH_TRANSFER);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(120L);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123L);
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.INDIVIDUAL_ACCOUNT);
            l_mainAccountParams.setIfoAccOpenDivTokyo("5");
            l_mainAccountParams.setIfoAccOpenDivOsaka("0");
            l_mainAccountParams.setIfoAccOpenDivNagoya("0");
            l_mainAccountParams.setBranchId(120L);
            l_mainAccountParams.setTransferCount(5);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(104);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123L);
            l_subAccountParams.setSubAccountId(124L);
            l_subAccountParams.setInstitutionId(104);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123L);
            l_subAccountParams.setSubAccountId(125L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20071010", "yyyyMMdd"));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "getProductId",
                new Class[]{ Institution.class },
                new Long(100));

            WEB3TPTradingPowerResult l_tPTradingPowerResult = new WEB3TPTradingPowerResult();
            l_tPTradingPowerResult.setResultFlg(false);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tPTradingPowerResult);

            WEB3IfoDepositCalc l_ifoDepositCalcForTest = new WEB3IfoDepositCalcForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl",
                "getIfoDepositCalc",
                new Class[]
                {WEB3GentradeSubAccount.class},
                l_ifoDepositCalcForTest);

            l_impl.validateOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01306, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 証拠金計算.calc未入金額()の戻り値 - リクエストデータ.振替金額
     * 計算結果 < 0 の場合
     */
    public void testValidateOrder_C0003()
    {
        final String STR_METHOD_NAME = "testValidateOrder_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3AccTransChangeToIfoDepositConfirmRequest l_request =
            new WEB3AccTransChangeToIfoDepositConfirmRequest();
        l_request.changeAmt = "300";

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(123L);
            l_aioOrderUnitParams.setSubAccountId(124L);
            l_aioOrderUnitParams.setBizDate(
                WEB3DateUtility.formatDate(
                    WEB3GentradeTradingTimeManagement.getOrderBizDate(), "yyyyMMdd"));
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.CASH_TRANSFER);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(120L);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123L);
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.INDIVIDUAL_ACCOUNT);
            l_mainAccountParams.setIfoAccOpenDivTokyo("5");
            l_mainAccountParams.setIfoAccOpenDivOsaka("0");
            l_mainAccountParams.setIfoAccOpenDivNagoya("0");
            l_mainAccountParams.setBranchId(120L);
            l_mainAccountParams.setTransferCount(5);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(104);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123L);
            l_subAccountParams.setSubAccountId(124L);
            l_subAccountParams.setInstitutionId(104);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123L);
            l_subAccountParams.setSubAccountId(125L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20071010", "yyyyMMdd"));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "getProductId",
                new Class[]{ Institution.class },
                new Long(100));

            WEB3TPTradingPowerResult l_tPTradingPowerResult = new WEB3TPTradingPowerResult();
            l_tPTradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tPTradingPowerResult);

            WEB3IfoDepositCalc l_ifoDepositCalcForTest = new WEB3IfoDepositCalcForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl",
                "getIfoDepositCalc",
                new Class[]
                {WEB3GentradeSubAccount.class},
                l_ifoDepositCalcForTest);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "createNewOrderId",
                new Class[]{},
                new Long(120));

            WEB3AccTransChangeToIfoDepositConfirmResponse l_response =
                l_impl.validateOrder(l_request);

            assertEquals("0", l_response.aftNonPayAmt);
            assertEquals("120", l_response.orderId);
            assertEquals("0", l_response.aftChangePossAmt);
            assertEquals("100", l_response.preIfoDeposit);
            assertEquals("400", l_response.aftIfoDeposit);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 証拠金計算.calc未入金額()の戻り値 - リクエストデータ.振替金額
     * 計算結果 < 0 の場合
     */
    public void testValidateOrder_C0004()
    {
        final String STR_METHOD_NAME = "testValidateOrder_C0004()";
        log.entering(STR_METHOD_NAME);

        WEB3AccTransChangeToIfoDepositConfirmRequest l_request =
            new WEB3AccTransChangeToIfoDepositConfirmRequest();
        l_request.changeAmt = "100";

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(123L);
            l_aioOrderUnitParams.setSubAccountId(124L);
            l_aioOrderUnitParams.setBizDate(
                WEB3DateUtility.formatDate(
                    WEB3GentradeTradingTimeManagement.getOrderBizDate(), "yyyyMMdd"));
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.CASH_TRANSFER);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(120L);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123L);
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.INDIVIDUAL_ACCOUNT);
            l_mainAccountParams.setIfoAccOpenDivTokyo("5");
            l_mainAccountParams.setIfoAccOpenDivOsaka("0");
            l_mainAccountParams.setIfoAccOpenDivNagoya("0");
            l_mainAccountParams.setBranchId(120L);
            l_mainAccountParams.setTransferCount(5);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(104);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123L);
            l_subAccountParams.setSubAccountId(124L);
            l_subAccountParams.setInstitutionId(104);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123L);
            l_subAccountParams.setSubAccountId(125L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20071010", "yyyyMMdd"));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "getProductId",
                new Class[]{ Institution.class },
                new Long(100));

            WEB3TPTradingPowerResult l_tPTradingPowerResult = new WEB3TPTradingPowerResult();
            l_tPTradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tPTradingPowerResult);

            WEB3IfoDepositCalc l_ifoDepositCalcForTest = new WEB3IfoDepositCalcForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl",
                "getIfoDepositCalc",
                new Class[]
                {WEB3GentradeSubAccount.class},
                l_ifoDepositCalcForTest);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "createNewOrderId",
                new Class[]{},
                new Long(120));

            WEB3AccTransChangeToIfoDepositConfirmResponse l_response =
                l_impl.validateOrder(l_request);

            assertEquals("1", l_response.aftNonPayAmt);
            assertEquals("120", l_response.orderId);
            assertEquals("0", l_response.aftChangePossAmt);
            assertEquals("100", l_response.preIfoDeposit);
            assertEquals("200", l_response.aftIfoDeposit);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01306, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * リクエストデータ.振替金額 = nullの場合
     */
    public void testSubmitOrder_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AccTransChangeToIfoDepositCompleteRequest l_request =
            new WEB3AccTransChangeToIfoDepositCompleteRequest();
        l_request.changeAmt = null;
        try
        {
            l_impl.submitOrder(l_request);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00759, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 余力のチェックを行う。
     * 戻り値の取引余力結果.判定フラグ == false の場合
     */
    public void testSubmitOrder_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AccTransChangeToIfoDepositCompleteRequest l_request =
            new WEB3AccTransChangeToIfoDepositCompleteRequest();
        l_request.changeAmt = "100";
        l_request.orderId = "103";
        l_request.checkDate = WEB3DateUtility.getDate("20071010", "yyyyMMdd");

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setIfoAccOpenDivTokyo("5");
            l_mainAccountParams.setIfoAccOpenDivOsaka("0");
            l_mainAccountParams.setIfoAccOpenDivNagoya("0");
            l_mainAccountParams.setTransferCount(5);
            l_mainAccountParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountId(124);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountId(125);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20071010", "yyyyMMdd"));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(123);
            l_aioOrderUnitParams.setSubAccountId(124);
            l_aioOrderUnitParams.setBranchId(101);
            l_aioOrderUnitParams.setBizDate("20071010");
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.CASH_TRANSFER);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            LoginInfo l_loginInfo = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123));

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(123);
            TestDBUtility.insertWithDel(l_traderParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber",
                new Class[]{ String.class, String.class, ProductTypeEnum.class },
                "00");

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_productParams.setProductId(121);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3TPTradingPowerResult l_tradingPowerResult =  new WEB3TPTradingPowerResult();
            l_tradingPowerResult.setResultFlg(false);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[]
                    {
                        WEB3GentradeSubAccount.class, 
                        Object[].class, 
                        Object[].class, 
                        OrderTypeEnum.class,
                        boolean.class
                    },
                l_tradingPowerResult);

            l_impl.submitOrder(l_request);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01306, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 余力のチェックを行う。
     * 戻り値の取引余力結果.判定フラグ ==true 
     * 補助口座.getMainAccount().is信用口座開設(弁済区分（=”指定なし”）)の戻り値 = true
     * get当日預り金への振替額()の戻り値 = 0
     * is保証金振替()の戻り値 = trueの場合
     * 注文を取得ない
     */
    public void testSubmitOrder_C0003()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3AccTransChangeToIfoDepositCompleteRequest l_request =
            new WEB3AccTransChangeToIfoDepositCompleteRequest();
        l_request.changeAmt = "100";
        l_request.orderId = "103";
        l_request.checkDate = WEB3DateUtility.getDate("20071010", "yyyyMMdd");

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setIfoAccOpenDivTokyo("5");
            l_mainAccountParams.setIfoAccOpenDivOsaka("0");
            l_mainAccountParams.setIfoAccOpenDivNagoya("0");
            l_mainAccountParams.setTransferCount(5);
            l_mainAccountParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountId(124);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountId(125);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20071010", "yyyyMMdd"));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(123);
            l_aioOrderUnitParams.setSubAccountId(124);
            l_aioOrderUnitParams.setBranchId(101);
            l_aioOrderUnitParams.setBizDate("20071010");
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.CASH_TRANSFER);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            LoginInfo l_loginInfo = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123));

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(123);
            TestDBUtility.insertWithDel(l_traderParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber",
                new Class[]{ String.class, String.class, ProductTypeEnum.class },
                "00");

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_productParams.setProductId(121);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3TPTradingPowerResult l_tradingPowerResult =  new WEB3TPTradingPowerResult();
            l_tradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[]
                    {
                        WEB3GentradeSubAccount.class, 
                        Object[].class, 
                        Object[].class, 
                        OrderTypeEnum.class,
                        boolean.class
                    },
                l_tradingPowerResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(true));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "getTransferAmountToEquitySubAcount",
                    new Class[]
                        { WEB3GentradeSubAccount.class, double.class, Date.class },
                    new Double(0));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager",
                    "submitTransferOrder",
                    new Class[]
                          { SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                              AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, "thow for test"));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);

            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(104);
            TestDBUtility.insertWithDel(l_aioOrderParams);

            l_impl.submitOrder(l_request);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 余力のチェックを行う。
     * 戻り値の取引余力結果.判定フラグ ==true
     * 補助口座.getMainAccount().is信用口座開設(弁済区分（=”指定なし”）)の戻り値 = true
     * get当日預り金への振替額()の戻り値 ＞ 0
     * is保証金振替()の戻り値 = trueの場合
     * 注文を取得ない
     */
    public void testSubmitOrder_C0004()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0004()";
        log.entering(STR_METHOD_NAME);

        WEB3AccTransChangeToIfoDepositCompleteRequest l_request =
            new WEB3AccTransChangeToIfoDepositCompleteRequest();
        l_request.changeAmt = "100";
        l_request.orderId = "103";
        l_request.checkDate = WEB3DateUtility.getDate("20071010", "yyyyMMdd");

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setIfoAccOpenDivTokyo("5");
            l_mainAccountParams.setIfoAccOpenDivOsaka("0");
            l_mainAccountParams.setIfoAccOpenDivNagoya("0");
            l_mainAccountParams.setTransferCount(5);
            l_mainAccountParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountId(124);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountId(125);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountId(126);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20071010", "yyyyMMdd"));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(123);
            l_aioOrderUnitParams.setSubAccountId(124);
            l_aioOrderUnitParams.setBranchId(101);
            l_aioOrderUnitParams.setBizDate("20071010");
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.CASH_TRANSFER);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            LoginInfo l_loginInfo = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123));

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(123);
            TestDBUtility.insertWithDel(l_traderParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber",
                new Class[]{ String.class, String.class, ProductTypeEnum.class },
                "00");

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_productParams.setProductId(121);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3TPTradingPowerResult l_tradingPowerResult =  new WEB3TPTradingPowerResult();
            l_tradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[]
                    {
                        WEB3GentradeSubAccount.class, 
                        Object[].class, 
                        Object[].class, 
                        OrderTypeEnum.class,
                        boolean.class
                    },
                l_tradingPowerResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(true));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "getTransferAmountToEquitySubAcount",
                    new Class[]
                        { WEB3GentradeSubAccount.class, double.class, Date.class },
                    new Double(1));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);

            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(104);
            TestDBUtility.insertWithDel(l_aioOrderParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitTransferOrder",
                new Class[]
                      { SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                          AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class },
                null);

            l_impl.submitOrder(l_request);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 正常
     */
    public void testSubmitOrder_C0005()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0005()";
        log.entering(STR_METHOD_NAME);

        WEB3AccTransChangeToIfoDepositCompleteRequest l_request =
            new WEB3AccTransChangeToIfoDepositCompleteRequest();
        l_request.changeAmt = "100";
        l_request.orderId = "103";
        l_request.checkDate = WEB3DateUtility.getDate("20071010", "yyyyMMdd");

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setIfoAccOpenDivTokyo("5");
            l_mainAccountParams.setIfoAccOpenDivOsaka("0");
            l_mainAccountParams.setIfoAccOpenDivNagoya("0");
            l_mainAccountParams.setTransferCount(5);
            l_mainAccountParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountId(124);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountId(125);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountId(126);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20071010", "yyyyMMdd"));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(123);
            l_aioOrderUnitParams.setSubAccountId(124);
            l_aioOrderUnitParams.setBranchId(101);
            l_aioOrderUnitParams.setBizDate("20071010");
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.CASH_TRANSFER);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            LoginInfo l_loginInfo = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123));

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(123);
            TestDBUtility.insertWithDel(l_traderParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber",
                new Class[]{ String.class, String.class, ProductTypeEnum.class },
                "00");

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_productParams.setProductId(121);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3TPTradingPowerResult l_tradingPowerResult =  new WEB3TPTradingPowerResult();
            l_tradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[]
                    {
                        WEB3GentradeSubAccount.class, 
                        Object[].class, 
                        Object[].class, 
                        OrderTypeEnum.class,
                        boolean.class
                    },
                l_tradingPowerResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(true));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "getTransferAmountToEquitySubAcount",
                    new Class[]
                        { WEB3GentradeSubAccount.class, double.class, Date.class },
                    new Double(1));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);

            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(103);
            l_aioOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitTransferOrder",
                new Class[]
                    { SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                        AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class },
                null);

            WEB3AccTransChangeToIfoDepositCompleteResponse l_response = l_impl.submitOrder(l_request);

            int l_intDateCompResult =
                WEB3DateUtility.compareToDay(
                    l_response.lastUpdatedTimestamp,
                    WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            assertEquals(0, l_intDateCompResult);
            assertEquals("103", l_response.orderId);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public class WEB3IfoDepositCalcForTest extends WEB3IfoDepositCalc
    {
        public WEB3IfoDepositCalcForTest()
        {
            super();
        }

        public double calcIfoDepositBalance(int l_intReservedDate)
        {
            return 100;
        }

        public double calcNonPayAmount()
        {
            return 101;
        }
    }
}
@

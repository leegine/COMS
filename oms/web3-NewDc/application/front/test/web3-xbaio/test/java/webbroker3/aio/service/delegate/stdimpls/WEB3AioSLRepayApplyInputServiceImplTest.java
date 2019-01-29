head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.39.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioSLRepayApplyInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import test.util.TestDBUtility;
import webbroker3.aio.message.WEB3SLRepayApplyInputRequest;
import webbroker3.aio.message.WEB3SLRepayApplyInputResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenStatusDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.StockSecuredLoanParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.WEB3TPTradingPowerServiceImplForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerSettlementService;
import webbroker3.tradingpower.WEB3TPTradingPowerSettlementServiceImplForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;

public class WEB3AioSLRepayApplyInputServiceImplTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLRepayApplyInputServiceImplTest.class);

    public WEB3AioSLRepayApplyInputServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        Services.overrideService(WEB3TPTradingPowerService.class, new WEB3TPTradingPowerServiceImplForMock());
        Services.overrideService(WEB3TPTradingPowerSettlementService.class, new WEB3TPTradingPowerSettlementServiceImplForMock());
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    WEB3AioSLRepayApplyInputServiceImpl l_impl =
        new WEB3AioSLRepayApplyInputServiceImpl();

    /**
     * is証券担保ローン口座開設() == false<BR>
     * 顧客行.証券担保ローン区分 == "0"<BR>
     */
    public void test_execute_0001()
    {
        final String STR_METHOD_NAME =
            " test_execute_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);

            StockSecuredLoanParams l_stockSecuredLoanParams =
                TestDBUtility.getStockSecuredLoanRow();
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setSubAccountId(2L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setSex("1");
            l_mainAccountParams.setBranchId(1001L);
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3SLRepayApplyInputRequest l_request = new WEB3SLRepayApplyInputRequest();
            l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02914, l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * is証券担保ローン口座開設() == true<BR>
     * 顧客行.証券担保ローン区分 == "1"<BR>
     * 算出した返済予定日の日付 > 現在日付の5日後の日付 の場合<BR>
     */
    public void test_execute_0002()
    {
        final String STR_METHOD_NAME =
            " test_execute_0002()";
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
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getSLChangePossAmt", new Class[]
                { WEB3GentradeSubAccount.class, Date.class },
                new Double(1.1D));

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(GtlUtils.getSystemTimestamp());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(1001L);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(1001L);
            l_institutionParams.setTheDayTransfer("1");
            l_institutionParams.setPaymentReserve("1");
            TestDBUtility.insertWithDel(l_institutionParams);

            StockSecuredLoanParams l_stockSecuredLoanParams =
                TestDBUtility.getStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAccountOpenStatus(WEB3AccountOpenStatusDef.PROMISE);
            l_stockSecuredLoanParams.setAccountId(1001L);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setInstitutionId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setSubAccountId(2L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setSex("1");
            l_mainAccountParams.setBranchId(1001L);
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv("2");
            l_mainAccountParams.setMarginSysAccOpenDiv("2");
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3SLRepayApplyInputRequest l_request = new WEB3SLRepayApplyInputRequest();
            WEB3SLRepayApplyInputResponse l_response = (WEB3SLRepayApplyInputResponse)l_impl.execute(l_request);
            assertEquals("1.1", l_response.repayableAmtList[0]);
            Timestamp l_ts = new Timestamp(l_response.repayScheduledDateList[0].getTime());
            Timestamp l_ts1 = new Timestamp(WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()).getTime());
            assertEquals(l_ts1, l_ts);
            l_ts = new Timestamp(l_response.repayScheduledDateList[1].getTime());
            WEB3GentradeBizDate l_gentradeTempDate =
                new WEB3GentradeBizDate(new Timestamp(GtlUtils.getSystemTimestamp().getTime()));
            l_ts1 = l_gentradeTempDate.roll(1);
            assertEquals(l_ts1, l_ts);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * is証券担保ローン口座開設() == true<BR>
     * 顧客行.証券担保ローン区分 == "1"<BR>
     * 算出した返済予定日の日付 < 現在日付の5日後の日付 の場合<BR>
     */
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
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getSLChangePossAmt", new Class[]
                { WEB3GentradeSubAccount.class, Date.class },
                new Double(2.1D));

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            WEB3GentradeBizDate l_gentradeTempDate =
                new WEB3GentradeBizDate(new Timestamp(GtlUtils.getSystemTimestamp().getTime()));
            Timestamp l_tsTempTime = l_gentradeTempDate.roll(6);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_tsTempTime);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(1001L);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(1001L);
            l_institutionParams.setTheDayTransfer("1");
            l_institutionParams.setPaymentReserve("2");
            TestDBUtility.insertWithDel(l_institutionParams);

            StockSecuredLoanParams l_stockSecuredLoanParams =
                TestDBUtility.getStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAccountOpenStatus(WEB3AccountOpenStatusDef.PROMISE);
            l_stockSecuredLoanParams.setAccountId(1001L);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setInstitutionId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setSubAccountId(2L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setSex("1");
            l_mainAccountParams.setBranchId(1001L);
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv("2");
            l_mainAccountParams.setMarginSysAccOpenDiv("2");
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3SLRepayApplyInputRequest l_request = new WEB3SLRepayApplyInputRequest();
            WEB3SLRepayApplyInputResponse l_response = (WEB3SLRepayApplyInputResponse)l_impl.execute(l_request);
            assertEquals("2.1", l_response.repayableAmtList[0]);
            Timestamp l_ts = new Timestamp(l_response.repayScheduledDateList[0].getTime());
            Timestamp l_ts1 = l_gentradeTempDate.roll(6);
            assertEquals(l_ts1, l_ts);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * l_request == null<BR>
     */
    public void test_execute_0004()
    {
        final String STR_METHOD_NAME =
            " test_execute_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.execute(null);
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


    /**
     * is証券担保ローン口座開設() == false<BR>
     * 顧客行.証券担保ローン区分 == "0"<BR>
     */
    public void test_execute_0005()
    {
        final String STR_METHOD_NAME =
            " test_execute_0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02915,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "直近振込日は返済予定日より大きいです。"));

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);

            StockSecuredLoanParams l_stockSecuredLoanParams =
                TestDBUtility.getStockSecuredLoanRow();
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setSubAccountId(2L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setSex("1");
            l_mainAccountParams.setBranchId(1001L);
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3SLRepayApplyInputRequest l_request = new WEB3SLRepayApplyInputRequest();
            l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02915, l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }
}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.10.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoDepositCalcResultCreatePerAccountServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3IfoDepositCalcResultCreatePerAccountServiceImplTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/08/15 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.ifodeposit.service.delegate.stdimpls;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifodeposit.WEB3IfoCustomerTransfer;
import webbroker3.ifodeposit.WEB3IfoDepositCalc;
import webbroker3.ifodeposit.WEB3IfoDepositCalcCondition;
import webbroker3.ifodeposit.WEB3IfoDepositCalcConditionPerIndex;
import webbroker3.ifodeposit.WEB3IfoSummaryContractPerIndex;
import webbroker3.ifodeposit.WEB3IfoSummaryContractPerProduct;
import webbroker3.ifodeposit.data.IfoDepositCalcResultParams;
import webbroker3.ifodeposit.data.IfoDepositCalcResultRow;
import webbroker3.ifodeposit.define.WEB3IfoDepositCalcResultSaveDiv;
import webbroker3.ifodeposit.define.WEB3IfoDepositFixedIfoDepositFlgDiv;
import webbroker3.ifodeposit.message.WEB3IfoDepositPerIndexUnit;
import webbroker3.ifodeposit.message.WEB3IfoDepositTranRefPerIndexUnit;
import webbroker3.ifodeposit.message.WEB3IfoDepositTransitionReferenceUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoDepositCalcResultCreatePerAccountServiceImplTest extends TestBaseForMock
{
    WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoDepositCalcResultCreatePerAccountServiceImplTest.class);
    
    WEB3IfoDepositCalcResultCreatePerAccountServiceImpl l_serviceImpl = null;
    public WEB3IfoDepositCalcResultCreatePerAccountServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_serviceImpl = new WEB3IfoDepositCalcResultCreatePerAccountServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //(証拠金計算条件.is夕場実施 == true and 証拠金計算条件.is清算値速報受信済() == true) or 
    //(証拠金計算条件.is夕場実施 == false and 証拠金計算条件.is証拠金不足メール送信済() == true)の場合、
    //1：確定。
    public void testCreateIfoDepositCalcResult_C0001()
    {
        final String STR_METHOD_NAME = "testCreateIfoDepositCalcResult_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(IfoDepositCalcResultRow.TYPE);
            IfoDepositCalcResultParams ifoDepositCalcResultParams = new IfoDepositCalcResultParams();;
            ifoDepositCalcResultParams.setDepositInfoId(1111);
            ifoDepositCalcResultParams.setAccountId(333812512246L);
            ifoDepositCalcResultParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            ifoDepositCalcResultParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(ifoDepositCalcResultParams);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams tradingTimeParams = TestDBUtility.getTradingTimeRow();
            tradingTimeParams.setInstitutionCode("0D");
            tradingTimeParams.setBranchCode("123");
            tradingTimeParams.setMarketCode("N1");
            tradingTimeParams.setTradingTimeType("13");
            tradingTimeParams.setProductCode("0");
            tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(tradingTimeParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount = null;
            l_subAccount =
                (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    333812512246L, 33381251220301L);

            WEB3IfoDepositCalc l_calc = new WEB3IfoDepositCalc();
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field.setAccessible(true);
            WEB3IfoDepositCalcCondition l_condition = new WEB3IfoDepositCalcCondition();
            l_condition.setIfoDepositCalcBaseDate(-1);
            Date[] dates = new Date[]{WEB3DateUtility.getDate("20080815", "yyyyMMdd")};
            l_condition.setBizDates(dates);
            l_condition.setEveningSessionFlag(true);
            l_condition.setQuickReportReceivedFlag(true);
            field.set(l_calc, l_condition);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field1.setAccessible(true);
            WEB3IfoCustomerTransfer customerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {0,1,2};
            customerTransfer.setBalances(l_balances);
            customerTransfer.setCurrentBizDateTransferAmount(1);
            customerTransfer.setNextBizDateTransferAmount(2);
            field1.set(l_calc, customerTransfer);

            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            WEB3IfoDepositCalcCondition condition = new WEB3IfoDepositCalcCondition();
            condition.setPreBizDateInfoDepositLackCharge(1);
            condition.setEveningSessionFlag(true);
            condition.setQuickReportReceivedFlag(true);
            field2.set(l_calc, condition);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl", "getIfoDepositCalc", new Class[]
                    { WEB3GentradeSubAccount.class },
                    l_calc);

            WEB3IfoDepositCalcResultCreatePerAccountServiceImplForTest l_forTest =
                new WEB3IfoDepositCalcResultCreatePerAccountServiceImplForTest();
            IfoDepositCalcResultParams resultParams = l_forTest.createIfoDepositCalcResult(l_subAccount);
            assertEquals(resultParams.getBalance0(), "0");
            assertEquals(resultParams.getIfoDepositBalance1(), "1");
            assertEquals(resultParams.getTodayFutSettleProfit2(), "2");
            assertEquals(resultParams.getBullQuantityNk2250(), "1");
            assertEquals(resultParams.getBullQuantityNk2251(), "1");
            assertEquals(resultParams.getBullQuantityNk2252(), "1");
            assertEquals(resultParams.getBullQuantityNk225Mini0(), "2");
            assertEquals(resultParams.getBullQuantityNk225Mini1(), "2");
            assertEquals(resultParams.getBullQuantityNk225Mini2(), "2");
            assertEquals(resultParams.getIfoDepositInsufficientFlag(), WEB3IfoDepositFixedIfoDepositFlgDiv.FIXED);
            assertEquals(resultParams.getIfoDepositIndexNk225(), "11");
            assertEquals(resultParams.getIfoDepositIndexNk225Mini(), "22");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //(証拠金計算条件.is夕場実施 == true and 証拠金計算条件.is清算値速報受信済() == true) or 
    //(証拠金計算条件.is夕場実施 == false and 証拠金計算条件.is証拠金不足メール送信済() == true)の場合、
    //以外、0：未確定。
    public void testCreateIfoDepositCalcResult_C0002()
    {
        final String STR_METHOD_NAME = "testCreateIfoDepositCalcResult_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(IfoDepositCalcResultRow.TYPE);
            IfoDepositCalcResultParams ifoDepositCalcResultParams = new IfoDepositCalcResultParams();;
            ifoDepositCalcResultParams.setDepositInfoId(1111);
            ifoDepositCalcResultParams.setAccountId(333812512246L);
            ifoDepositCalcResultParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            ifoDepositCalcResultParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(ifoDepositCalcResultParams);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams tradingTimeParams = TestDBUtility.getTradingTimeRow();
            tradingTimeParams.setInstitutionCode("0D");
            tradingTimeParams.setBranchCode("123");
            tradingTimeParams.setMarketCode("N1");
            tradingTimeParams.setTradingTimeType("13");
            tradingTimeParams.setProductCode("0");
            tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(tradingTimeParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount = null;
            l_subAccount =
                (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    333812512246L, 33381251220301L);

            WEB3IfoDepositCalc l_calc = new WEB3IfoDepositCalc();
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field.setAccessible(true);
            WEB3IfoDepositCalcCondition l_condition = new WEB3IfoDepositCalcCondition();
            l_condition.setIfoDepositCalcBaseDate(-1);
            Date[] dates = new Date[]{WEB3DateUtility.getDate("20080815", "yyyyMMdd")};
            l_condition.setBizDates(dates);
            l_condition.setEveningSessionFlag(true);
            l_condition.setQuickReportReceivedFlag(true);
            field.set(l_calc, l_condition);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field1.setAccessible(true);
            WEB3IfoCustomerTransfer customerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {0,1,2};
            customerTransfer.setBalances(l_balances);
            customerTransfer.setCurrentBizDateTransferAmount(1);
            customerTransfer.setNextBizDateTransferAmount(2);
            field1.set(l_calc, customerTransfer);

            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            WEB3IfoDepositCalcCondition condition = new WEB3IfoDepositCalcCondition();
            condition.setPreBizDateInfoDepositLackCharge(1);
            field2.set(l_calc, condition);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl", "getIfoDepositCalc", new Class[]
                    { WEB3GentradeSubAccount.class },
                    l_calc);

            WEB3IfoDepositCalcResultCreatePerAccountServiceImplForTest l_forTest =
                new WEB3IfoDepositCalcResultCreatePerAccountServiceImplForTest();
            IfoDepositCalcResultParams resultParams = l_forTest.createIfoDepositCalcResult(l_subAccount);
            assertEquals(resultParams.getBalance0(), "0");
            assertEquals(resultParams.getIfoDepositBalance1(), "1");
            assertEquals(resultParams.getTodayFutSettleProfit2(), "2");
            assertEquals(resultParams.getBullQuantityNk2250(), "1");
            assertEquals(resultParams.getBullQuantityNk2251(), "1");
            assertEquals(resultParams.getBullQuantityNk2252(), "1");
            assertEquals(resultParams.getBullQuantityNk225Mini0(), "2");
            assertEquals(resultParams.getBullQuantityNk225Mini1(), "2");
            assertEquals(resultParams.getBullQuantityNk225Mini2(), "2");
            assertEquals(resultParams.getIfoDepositInsufficientFlag(), WEB3IfoDepositFixedIfoDepositFlgDiv.NOT_FIXED);
            assertEquals(resultParams.getIfoDepositIndexNk225(), "11");
            assertEquals(resultParams.getIfoDepositIndexNk225Mini(), "22");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 証拠金計算を取得する失敗の場合
     */
    public void testCreateIfoDepositCalcResult_C0003()
    {
        final String STR_METHOD_NAME = "testCreateIfoDepositCalcResult_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(IfoDepositCalcResultRow.TYPE);
            IfoDepositCalcResultParams ifoDepositCalcResultParams = new IfoDepositCalcResultParams();;
            ifoDepositCalcResultParams.setDepositInfoId(1111);
            ifoDepositCalcResultParams.setAccountId(333812512246L);
            ifoDepositCalcResultParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            ifoDepositCalcResultParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(ifoDepositCalcResultParams);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams tradingTimeParams = TestDBUtility.getTradingTimeRow();
            tradingTimeParams.setInstitutionCode("0D");
            tradingTimeParams.setBranchCode("123");
            tradingTimeParams.setMarketCode("N1");
            tradingTimeParams.setTradingTimeType("13");
            tradingTimeParams.setProductCode("0");
            tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(tradingTimeParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount = null;
            l_subAccount =
                (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    333812512246L, 33381251220301L);

            WEB3IfoDepositCalc l_calc = new WEB3IfoDepositCalc();
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field.setAccessible(true);
            WEB3IfoDepositCalcCondition l_condition = new WEB3IfoDepositCalcCondition();
            l_condition.setIfoDepositCalcBaseDate(-1);
            Date[] dates = new Date[]{WEB3DateUtility.getDate("20080815", "yyyyMMdd")};
            l_condition.setBizDates(dates);
            l_condition.setEveningSessionFlag(true);
            l_condition.setQuickReportReceivedFlag(true);
            field.set(l_calc, l_condition);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field1.setAccessible(true);
            WEB3IfoCustomerTransfer customerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {0,1,2};
            customerTransfer.setBalances(l_balances);
            customerTransfer.setCurrentBizDateTransferAmount(1);
            customerTransfer.setNextBizDateTransferAmount(2);
            field1.set(l_calc, customerTransfer);

            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field2.setAccessible(true);
            WEB3IfoDepositCalcCondition condition = new WEB3IfoDepositCalcCondition();
            condition.setPreBizDateInfoDepositLackCharge(1);
            field2.set(l_calc, condition);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl", "getIfoDepositCalc", new Class[]
                    { WEB3GentradeSubAccount.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, "getIfoDepositCalc"));

            WEB3IfoDepositCalcResultCreatePerAccountServiceImplForTest l_forTest =
                new WEB3IfoDepositCalcResultCreatePerAccountServiceImplForTest();
            IfoDepositCalcResultParams resultParams = l_forTest.createIfoDepositCalcResult(l_subAccount);

            assertEquals(resultParams.getStatus(), WEB3IfoDepositCalcResultSaveDiv.STATUS_ERROR);
            assertEquals(resultParams.getAcceptanceIfoDepositBal0(), "webbroker3.common.WEB3BaseException");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //指定日の範囲(n = 0～2まで)のLoop処理
    //SPAN証拠金を取得する(isSPAN使用可能 = falseの場合)
    //本日振替額：      
    //指定日(n) == 0の場合、先物OP顧客移動明細.get振替額[T+0]の戻り値
    //指定日(n) != 1の場合、先物OP顧客移動明細.get振替額[T+1]の戻り値 
    //本日先物決済損益：
    //指定日(n) == 0の場合、null
    //指定日(n) == 1の場合、先物OP顧客移動明細.get先物決済損益[T+1]の戻り値
    //指定日(n) == 2の場合、先物OP顧客移動明細.get先物決済損益[T+2]の戻り値 
    //本日オプション受渡代金：    
    //指定日(n) == 0の場合、null
    //指定日(n) == 1の場合、先物OP顧客移動明細.getオプション受渡代金[T+1] + 先物OP顧客移動明細.getオプション買建受渡代金[T+1]
    //指定日(n) == 2の場合、先物OP顧客移動明細.getオプション受渡代金[T+2] + 先物OP顧客移動明細.getオプション買建受渡代金[T+2]
    //ポジションバランス == nullの場合
    public void testCreateIfoDepositTransitionReferences_C0001()
    {
        final String STR_METHOD_NAME = "testCreateIfoDepositCalcResult_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoDepositCalc l_calc = new WEB3IfoDepositCalc();
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field.setAccessible(true);
            WEB3IfoDepositCalcCondition l_condition = new WEB3IfoDepositCalcCondition();
            Date[] dates = new Date[]{
                WEB3DateUtility.getDate("20080815", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080816", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080817", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080817", "yyyyMMdd")};
            l_condition.setBizDates(dates);
            l_condition.setSimpleSPANCalcFlag(true);
            l_condition.setSpanTroubleFlag(false);
            field.set(l_calc, l_condition);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field1.setAccessible(true);
            WEB3IfoCustomerTransfer transfer = new WEB3IfoCustomerTransfer();
            transfer.setCurrentBizDateTransferAmount(100);
            transfer.setNextBizDateTransferAmount(200);
            transfer.addNextBizDateFuturesCloseProfitLoss(100);
            transfer.addNext2BizDateFuturesCloseProfitLoss(200);
            transfer.addNextBizDateOptionNetAmount(300);
            transfer.addNextBizDateOptionBuyEstimatedNetAmount(400);
            transfer.addNext2BizDateOptionNetAmount(500);
            transfer.addNext2BizDateOptionBuyEstimatedNetAmount(600);
            double[] l_balances = {0,1,2};
            transfer.setBalances(l_balances);
            field1.set(l_calc, transfer);

            WEB3IfoDepositTransitionReferenceUnit[] l_units =
                l_serviceImpl.createIfoDepositTransitionReferences(l_calc);
            assertEquals(l_units[0].todayCahangeAmt, "100");
            assertEquals(l_units[0].todayFutSettleProfitLoss, null);
            assertEquals(l_units[0].todayOpNetAmt, null);
            assertEquals(l_units[0].spanIfoDeposit, null);
            assertEquals(l_units[0].positionBalance, null);
            assertEquals(l_units[0].positionBalanceDiv, null);
            assertEquals(l_units[1].todayCahangeAmt, "200");
            assertEquals(l_units[1].todayFutSettleProfitLoss, "100");
            assertEquals(l_units[1].todayOpNetAmt, "700");
            assertEquals(l_units[1].ifoDepositPower, "0");
            assertEquals(l_units[1].spanIfoDeposit, null);
            assertEquals(l_units[1].positionBalance, null);
            assertEquals(l_units[1].positionBalanceDiv, null);
            assertEquals(l_units[2].todayCahangeAmt, "200");
            assertEquals(l_units[2].todayFutSettleProfitLoss, "200");
            assertEquals(l_units[2].todayOpNetAmt, "1100");
            assertEquals(l_units[2].ifoDepositPower, "0");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //指定日の範囲(n = 0～2まで)のLoop処理
    //SPAN証拠金を取得する(isSPAN使用可能 = trueの場合)
    //本日振替額：      
    //指定日(n) == 0の場合、先物OP顧客移動明細.get振替額[T+0]の戻り値
    //指定日(n) != 1の場合、先物OP顧客移動明細.get振替額[T+1]の戻り値 
    //本日先物決済損益：
    //指定日(n) == 0の場合、null
    //指定日(n) == 1の場合、先物OP顧客移動明細.get先物決済損益[T+1]の戻り値
    //指定日(n) == 2の場合、先物OP顧客移動明細.get先物決済損益[T+2]の戻り値 
    //本日オプション受渡代金：    
    //指定日(n) == 0の場合、null
    //指定日(n) == 1の場合、先物OP顧客移動明細.getオプション受渡代金[T+1] + 先物OP顧客移動明細.getオプション買建受渡代金[T+1]
    //指定日(n) == 2の場合、先物OP顧客移動明細.getオプション受渡代金[T+2] + 先物OP顧客移動明細.getオプション買建受渡代金[T+2]
    //ポジションバランス == nullの場合
    public void testCreateIfoDepositTransitionReferences_C0002()
    {
        final String STR_METHOD_NAME = "testCreateIfoDepositCalcResult_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoDepositCalc l_calc = new WEB3IfoDepositCalc();
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field.setAccessible(true);
            WEB3IfoDepositCalcCondition l_condition = new WEB3IfoDepositCalcCondition();
            Date[] dates = new Date[]{
                WEB3DateUtility.getDate("20080815", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080816", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080817", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080817", "yyyyMMdd")};
            l_condition.setBizDates(dates);
            l_condition.setSimpleSPANCalcFlag(false);
            l_condition.setSpanTroubleFlag(false);
            field.set(l_calc, l_condition);

            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field1.setAccessible(true);
            WEB3IfoCustomerTransfer transfer = new WEB3IfoCustomerTransfer();
            transfer.setCurrentBizDateTransferAmount(100);
            transfer.setNextBizDateTransferAmount(200);
            transfer.addNextBizDateFuturesCloseProfitLoss(100);
            transfer.addNext2BizDateFuturesCloseProfitLoss(200);
            transfer.addNextBizDateOptionNetAmount(300);
            transfer.addNextBizDateOptionBuyEstimatedNetAmount(400);
            transfer.addNext2BizDateOptionNetAmount(500);
            transfer.addNext2BizDateOptionBuyEstimatedNetAmount(600);
            double[] l_balances = {0,1,2};
            transfer.setBalances(l_balances);
            field1.set(l_calc, transfer);

            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerProductList");
            field2.setAccessible(true);
            WEB3IfoSummaryContractPerProduct product = new WEB3IfoSummaryContractPerProduct();
            product.setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
            WEB3IfoSummaryContractPerProduct ifoSummaryContractPerProductList[] =
                new WEB3IfoSummaryContractPerProduct[]{product};
            field2.set(l_calc, ifoSummaryContractPerProductList);
            
            Field field3 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field3.setAccessible(true);
            WEB3IfoSummaryContractPerIndex l_index = new WEB3IfoSummaryContractPerIndex();
            l_index.setFuturesBuyContractQuantity(1);
            l_index.setCurrentBizDateFuturesBuyOrderQuantity(2);
            l_index.setOptionPutSellContractQuantity(3);
            l_index.setCurrentBizDateOptionPutSellOrderQuantity(4);
            l_index.setNextBizDateFuturesBuyOrderQuantity(5);
            l_index.setNextBizDateOptionPutSellOrderQuantity(6);
            l_index.setTargetProductCode("7");
            WEB3IfoSummaryContractPerIndex ifoSummaryContractPerIndexList[] =
                new WEB3IfoSummaryContractPerIndex[]{l_index};
            field3.set(l_calc, ifoSummaryContractPerIndexList);

            WEB3IfoDepositTransitionReferenceUnit[] l_units =
                l_serviceImpl.createIfoDepositTransitionReferences(l_calc);
            assertEquals(l_units[0].todayCahangeAmt, "100");
            assertEquals(l_units[0].todayFutSettleProfitLoss, null);
            assertEquals(l_units[0].todayOpNetAmt, null);
            assertEquals(l_units[0].spanIfoDeposit, "0");
            assertEquals(l_units[0].positionBalance, "10");
            assertEquals(l_units[0].positionBalanceDiv, "1");
            assertEquals(l_units[1].todayCahangeAmt, "200");
            assertEquals(l_units[1].todayFutSettleProfitLoss, "100");
            assertEquals(l_units[1].todayOpNetAmt, "700");
            assertEquals(l_units[1].ifoDepositPower, "0");
            assertEquals(l_units[1].spanIfoDeposit, "0");
            assertEquals(l_units[1].positionBalance, "21");
            assertEquals(l_units[1].positionBalanceDiv, "1");
            assertEquals(l_units[2].todayCahangeAmt, "200");
            assertEquals(l_units[2].todayFutSettleProfitLoss, "200");
            assertEquals(l_units[2].todayOpNetAmt, "1100");
            assertEquals(l_units[2].ifoDepositPower, "0");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //分岐フロー：SPAN使用可能(isSPAN使用可能() == true)の場合
    public void testCreateIfoDepositPerIndexUnitList_C0001()
    {
        final String STR_METHOD_NAME = "testCreateIfoDepositPerIndexUnitList_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoDepositCalcCondition l_condition = new WEB3IfoDepositCalcCondition();
            l_condition.setSimpleSPANCalcFlag(false);
            l_condition.setSpanTroubleFlag(false);

            WEB3IfoDepositPerIndexUnit[] l_units =
                l_serviceImpl.createIfoDepositPerIndexUnitList(l_condition);
            assertNull(l_units);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //指数別証拠金計算条件ごとのLoop処理
    public void testCreateIfoDepositPerIndexUnitList_C0002()
    {
        final String STR_METHOD_NAME = "testCreateIfoDepositPerIndexUnitList_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoDepositCalcCondition l_condition = new WEB3IfoDepositCalcCondition();
            l_condition.setSimpleSPANCalcFlag(true);
            l_condition.setSpanTroubleFlag(false);
            WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndexList =
                new WEB3IfoDepositCalcConditionPerIndex[]{
                    new WEB3IfoDepositCalcConditionPerIndex(),
                    new WEB3IfoDepositCalcConditionPerIndex(),
                    new WEB3IfoDepositCalcConditionPerIndex()
            };
            l_ifoDepositCalcConditionPerIndexList[0].setUnderlyingProductCode("0");
            l_ifoDepositCalcConditionPerIndexList[1].setUnderlyingProductCode("1");
            l_ifoDepositCalcConditionPerIndexList[2].setUnderlyingProductCode("2");
            l_ifoDepositCalcConditionPerIndexList[0].setIfoDepositPerUnit(0);
            l_ifoDepositCalcConditionPerIndexList[1].setIfoDepositPerUnit(1);
            l_ifoDepositCalcConditionPerIndexList[2].setIfoDepositPerUnit(2);
            l_condition.setIfoDepositCalcPerIndexList(l_ifoDepositCalcConditionPerIndexList);

            WEB3IfoDepositPerIndexUnit[] l_units =
                l_serviceImpl.createIfoDepositPerIndexUnitList(l_condition);
            assertEquals(l_units[0].targetProductCode, "0");
            assertEquals(l_units[0].regIfoDeposit, "0");
            assertEquals(l_units[1].targetProductCode, "1");
            assertEquals(l_units[1].regIfoDeposit, "1");
            assertEquals(l_units[2].targetProductCode, "2");
            assertEquals(l_units[2].regIfoDeposit, "2");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //指数別証拠金計算条件ごとのLoop処理
    //新規建余力可能なしの場合
    //指数別先物OP建玉集計マップが指定された原資産銘柄コードのマッピングを保持する場合
    //SPAN使用可能、または、指定日 == 0 の場合
    public void testCreateIfoDepositTranRefPerIndexUnitList_C0001()
    {
        final String STR_METHOD_NAME = "testCreateIfoDepositTranRefPerIndexUnitList_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoDepositCalc l_calc = new WEB3IfoDepositCalc();

            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field.setAccessible(true);
            WEB3IfoDepositCalcCondition l_condition = new WEB3IfoDepositCalcCondition();
            WEB3IfoDepositCalcConditionPerIndex[] l_PerIndexList =
                new WEB3IfoDepositCalcConditionPerIndex[]{
                    new WEB3IfoDepositCalcConditionPerIndex(),
                    new WEB3IfoDepositCalcConditionPerIndex(),
                    new WEB3IfoDepositCalcConditionPerIndex()
            };
            l_PerIndexList[0].setUnderlyingProductCode("0");
            l_PerIndexList[1].setUnderlyingProductCode("1");
            l_PerIndexList[2].setUnderlyingProductCode("2");
            l_condition.setIfoDepositCalcPerIndexList(l_PerIndexList);

            l_condition.setSimpleSPANCalcFlag(false);
            l_condition.setSpanTroubleFlag(false);
            field.set(l_calc, l_condition);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field2.setAccessible(true);
            WEB3IfoSummaryContractPerIndex PerIndexList[] = new WEB3IfoSummaryContractPerIndex[]{
                new WEB3IfoSummaryContractPerIndex(),
                new WEB3IfoSummaryContractPerIndex(),
                new WEB3IfoSummaryContractPerIndex()};
            PerIndexList[0].setTargetProductCode("0");
            PerIndexList[1].setTargetProductCode("1");
            PerIndexList[1].setFuturesBuyContractQuantity(1);
            PerIndexList[2].setTargetProductCode("2");
            PerIndexList[2].setFuturesBuyContractQuantity(2);
            field2.set(l_calc, PerIndexList);

            WEB3IfoDepositCalcResultCreatePerAccountServiceImplForTest implForTest = 
                new WEB3IfoDepositCalcResultCreatePerAccountServiceImplForTest();
            implForTest.setFlag(false);
            WEB3IfoDepositTranRefPerIndexUnit[] l_units =
                implForTest.createIfoDepositTranRefPerIndexUnitList(l_calc, 0);
            assertEquals(l_units[0].longPositionContract, "0");
            assertEquals(l_units[0].bearQuantity, null);
            assertEquals(l_units[0].bullQuantity, null);
            assertEquals(l_units[1].longPositionContract, "1");
            assertEquals(l_units[1].bearQuantity, null);
            assertEquals(l_units[1].bullQuantity, null);
            assertEquals(l_units[2].longPositionContract, "2");
            assertEquals(l_units[2].bearQuantity, null);
            assertEquals(l_units[2].bullQuantity, null);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //指数別証拠金計算条件ごとのLoop処理
    //新規建余力可能の場合
    //指数別先物OP建玉集計マップが指定された原資産銘柄コードのマッピングを保持する場合なし
    //SPAN使用可能なし
    public void testCreateIfoDepositTranRefPerIndexUnitList_C0002()
    {
        final String STR_METHOD_NAME = "testCreateIfoDepositTranRefPerIndexUnitList_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoDepositCalc l_calc = new WEB3IfoDepositCalc();

            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field.setAccessible(true);
            WEB3IfoDepositCalcCondition l_condition = new WEB3IfoDepositCalcCondition();
            WEB3IfoDepositCalcConditionPerIndex[] l_PerIndexList =
                new WEB3IfoDepositCalcConditionPerIndex[]{
                    new WEB3IfoDepositCalcConditionPerIndex(),
                    new WEB3IfoDepositCalcConditionPerIndex(),
                    new WEB3IfoDepositCalcConditionPerIndex()
            };
            l_PerIndexList[0].setUnderlyingProductCode("0");
            l_PerIndexList[1].setUnderlyingProductCode("1");
            l_PerIndexList[2].setUnderlyingProductCode("2");
            l_condition.setIfoDepositCalcPerIndexList(l_PerIndexList);
            l_PerIndexList[0].setIfoDepositPerUnit(-1);
            l_PerIndexList[1].setIfoDepositPerUnit(1);
            l_PerIndexList[2].setIfoDepositPerUnit(2);
            l_condition.setSimpleSPANCalcFlag(true);
            l_condition.setSpanTroubleFlag(false);
            field.set(l_calc, l_condition);
            
            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field1.setAccessible(true);
            WEB3IfoCustomerTransfer customerTransfer = new WEB3IfoCustomerTransfer();
            double[] l_balances = {0,1,2};
            customerTransfer.setBalances(l_balances);
            customerTransfer.setCurrentBizDateTransferAmount(1);
            customerTransfer.setNextBizDateTransferAmount(2);
            field1.set(l_calc, customerTransfer);
            
            Field field2 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field2.setAccessible(true);
            WEB3IfoSummaryContractPerIndex PerIndexList[] = new WEB3IfoSummaryContractPerIndex[]{
                new WEB3IfoSummaryContractPerIndex(),
                new WEB3IfoSummaryContractPerIndex(),
                new WEB3IfoSummaryContractPerIndex()};
            PerIndexList[0].setTargetProductCode("3");
            PerIndexList[1].setTargetProductCode("4");
            PerIndexList[2].setTargetProductCode("5");
            field2.set(l_calc, PerIndexList);

            WEB3IfoDepositCalcResultCreatePerAccountServiceImplForTest implForTest = 
                new WEB3IfoDepositCalcResultCreatePerAccountServiceImplForTest();
            implForTest.setFlag(true);
            WEB3IfoDepositTranRefPerIndexUnit[] l_units =
                implForTest.createIfoDepositTranRefPerIndexUnitList(l_calc, 1);
            assertEquals(l_units[0].longPositionContract, "0");
            assertEquals(l_units[0].bearQuantity, "0");
            assertEquals(l_units[0].bullQuantity, "0");
            assertEquals(l_units[1].longPositionContract, "0");
            assertEquals(l_units[1].bearQuantity, "0");
            assertEquals(l_units[1].bullQuantity, "0");
            assertEquals(l_units[2].longPositionContract, "0");
            assertEquals(l_units[2].bearQuantity, "0");
            assertEquals(l_units[2].bullQuantity, "0");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //falseを返却する
    //・証拠金計算.get証拠金計算条件().is新規建余力可能() == false
    public void testIsNewOpenTradingPowerAvailable_C0001()
    {
        final String STR_METHOD_NAME = "testIsNewOpenTradingPowerAvailable_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoDepositCalc l_calc = new WEB3IfoDepositCalc();
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field.setAccessible(true);
            WEB3IfoDepositCalcCondition l_condition = new WEB3IfoDepositCalcCondition();
            l_condition.setMinIfoDeposit(11);
            l_condition.setNewOpenTradingPowerAvailableFlag(false);
            l_condition.setLackChargeNonManagementFlag(true);
            field.set(l_calc, l_condition);

            boolean l_bln = l_serviceImpl.isNewOpenTradingPowerAvailable(l_calc, 4);
            assertFalse(l_bln);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //trueを返却する
    public void testIsNewOpenTradingPowerAvailable_C0002()
    {
        final String STR_METHOD_NAME = "testIsNewOpenTradingPowerAvailable_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoDepositCalc l_calc = new WEB3IfoDepositCalc();
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field.setAccessible(true);
            WEB3IfoDepositCalcCondition l_condition = new WEB3IfoDepositCalcCondition();
            l_condition.setMinIfoDeposit(0);
            l_condition.setNewOpenTradingPowerAvailableFlag(true);
            l_condition.setLackChargeNonManagementFlag(true);
            field.set(l_calc, l_condition);

            boolean l_bln = l_serviceImpl.isNewOpenTradingPowerAvailable(l_calc, 4);
            assertTrue(l_bln);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }
    
    private class WEB3IfoDepositCalcResultCreatePerAccountServiceImplForTest
        extends WEB3IfoDepositCalcResultCreatePerAccountServiceImpl
    {
        public boolean flag = false;
        
        
        protected WEB3IfoDepositTransitionReferenceUnit[] createIfoDepositTransitionReferences(WEB3IfoDepositCalc l_ifoDepositCalc)
        {
            WEB3IfoDepositTransitionReferenceUnit[] l_units =
                new WEB3IfoDepositTransitionReferenceUnit[]{
                    new WEB3IfoDepositTransitionReferenceUnit(),
                        new WEB3IfoDepositTransitionReferenceUnit(),
                            new WEB3IfoDepositTransitionReferenceUnit()};
            l_units[0].ifoDepositTranRefPerIndexUnit =
                new WEB3IfoDepositTranRefPerIndexUnit[]{
                    new WEB3IfoDepositTranRefPerIndexUnit(),
                        new WEB3IfoDepositTranRefPerIndexUnit()};
            l_units[0].ifoDepositTranRefPerIndexUnit[0].targetProductCode = WEB3IfoDepositCalcResultSaveDiv.NK225;
            l_units[0].ifoDepositTranRefPerIndexUnit[0].bullQuantity = "1";
            l_units[0].ifoDepositTranRefPerIndexUnit[1].targetProductCode = WEB3IfoDepositCalcResultSaveDiv.MINI_NK225;
            l_units[0].ifoDepositTranRefPerIndexUnit[1].bullQuantity = "2";
            l_units[0].ifoDepositBal = "0";
            l_units[0].todayCahangeAmt = "0";
            l_units[0].todayFutSettleProfitLoss = "0";

            l_units[1].ifoDepositTranRefPerIndexUnit =
                new WEB3IfoDepositTranRefPerIndexUnit[]{
                    new WEB3IfoDepositTranRefPerIndexUnit(),
                        new WEB3IfoDepositTranRefPerIndexUnit()};
            l_units[1].ifoDepositTranRefPerIndexUnit[0].targetProductCode = WEB3IfoDepositCalcResultSaveDiv.NK225;
            l_units[1].ifoDepositTranRefPerIndexUnit[0].bullQuantity = "1";
            l_units[1].ifoDepositTranRefPerIndexUnit[1].targetProductCode = WEB3IfoDepositCalcResultSaveDiv.MINI_NK225;
            l_units[1].ifoDepositTranRefPerIndexUnit[1].bullQuantity = "2";
            l_units[1].ifoDepositBal = "1";
            l_units[1].todayCahangeAmt = "1";
            l_units[1].todayFutSettleProfitLoss = "1";

            l_units[2].ifoDepositTranRefPerIndexUnit =
                new WEB3IfoDepositTranRefPerIndexUnit[]{
                    new WEB3IfoDepositTranRefPerIndexUnit(),
                        new WEB3IfoDepositTranRefPerIndexUnit()};
            l_units[2].ifoDepositTranRefPerIndexUnit[0].targetProductCode = WEB3IfoDepositCalcResultSaveDiv.NK225;
            l_units[2].ifoDepositTranRefPerIndexUnit[0].bullQuantity = "1";
            l_units[2].ifoDepositTranRefPerIndexUnit[1].targetProductCode = WEB3IfoDepositCalcResultSaveDiv.MINI_NK225;
            l_units[2].ifoDepositTranRefPerIndexUnit[1].bullQuantity = "2";
            l_units[2].ifoDepositBal = "2";
            l_units[2].todayCahangeAmt = "2";
            l_units[2].todayFutSettleProfitLoss = "2";
            return l_units;
        }

        protected WEB3IfoDepositPerIndexUnit[] createIfoDepositPerIndexUnitList(WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition)
        {
            WEB3IfoDepositPerIndexUnit[] l_ifoDepositPerIndexUnit = new WEB3IfoDepositPerIndexUnit[]{
                new WEB3IfoDepositPerIndexUnit(), new WEB3IfoDepositPerIndexUnit()};
            l_ifoDepositPerIndexUnit[0].targetProductCode = WEB3IfoDepositCalcResultSaveDiv.NK225;
            l_ifoDepositPerIndexUnit[0].regIfoDeposit = "11";
            l_ifoDepositPerIndexUnit[1].targetProductCode = WEB3IfoDepositCalcResultSaveDiv.MINI_NK225;
            l_ifoDepositPerIndexUnit[1].regIfoDeposit = "22";
            return l_ifoDepositPerIndexUnit;
        }

        public boolean isFlag()
        {
            return flag;
        }

        public void setFlag(boolean flag)
        {
            this.flag = flag;
        }

        protected boolean isNewOpenTradingPowerAvailable(
                WEB3IfoDepositCalc l_ifoDepositCalc,
                int l_intReservedDate)
        {
            if(!this.flag)
            {
                return false;
            }
            return true;
        }
    }
}
@

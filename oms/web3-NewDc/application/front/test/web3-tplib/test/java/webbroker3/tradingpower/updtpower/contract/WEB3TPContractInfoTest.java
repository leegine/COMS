head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.40.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPContractInfoTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 建玉情報Test(WEB3TPContractInfoTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/31 張騰宇 (中訊) 新規作成
*/
package webbroker3.tradingpower.updtpower.contract;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import test.util.TestDBUtility;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.data.FixedFinTransactionParams;
import webbroker3.tradingpower.data.FixedFinTransactionRow;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

public class WEB3TPContractInfoTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3TPContractInfoTest.class);
    
    WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();

    public WEB3TPContractInfoTest(String arg0)
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
    
    //get未受渡建玉の集計
    public void testGetSummaryUndeliveredContractCase1()
    {
        final String STR_METHOD_NAME = "testGetSummaryUndeliveredContractCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3TPContractInfo l_info = new WEB3TPContractInfo();
            
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContractForTest();


            Date l_dat = WEB3DateUtility.getDate("20070101","yyyyMMdd");
            
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            List l_lisTargetContract = new ArrayList();
            l_lisTargetContract.add(l_targetContract);
            
            WEB3TPTargetContract l_targetContract1 = new WEB3TPTargetContract();
            WEB3TPTargetContractDetail l_targetContractDetail1 = new WEB3TPTargetContractDetail();
            l_targetContract1.setTargetContractDetail(l_targetContractDetail1);
            l_lisTargetContract.add(l_targetContract1);
            
            l_info.addHistoryPerContract(l_targetContract, l_historyPerContract);
            l_info.addHistoryPerContract(l_targetContract1, l_historyPerContract);

            Field l_method = WEB3TPContractInfo.class.getDeclaredField("targetContracts");
            l_method.setAccessible(true);
            l_method.set(l_info,l_lisTargetContract);

            WEB3TPSummaryUndeliveredContract l_sumUndeliveredContract = l_info.getSummaryUndeliveredContract(l_dat);
            
            assertEquals(l_sumUndeliveredContract.getContractTotalCost() + "", "200.0");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfo.getSummaryTodayRepayContractAmount()'
     */
    public void testGetSummaryTodayRepayContractAmountCase1()
    {
        final String STR_METHOD_NAME = "testGetSummaryTodayRepayContractAmountCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3TPContractInfo l_info = new WEB3TPContractInfo();
//            WEB3TPCalcCondition l_condition = new WEB3TPCalcCondition();
            Class c = Class.forName(WEB3TPCalcCondition.class.getName());
            WEB3TPCalcCondition l_condition = (WEB3TPCalcCondition)c.newInstance();
            Class[] param1 = {String.class, String.class};
            
            Method l_method = c.getDeclaredMethod("addInstBranCalcCondition", param1);
            l_method.setAccessible(true);
            Object[] l_obj = {"contractamount.apply.date", "1"};
            l_method.invoke(l_condition, l_obj);

            Date l_dat = WEB3DateUtility.getDate("20070101","yyyyMMdd");
            Date l_dat1 = WEB3DateUtility.getDate("20080101","yyyyMMdd");
            
            Date[] l_dats = new Date[6];
            l_dats[0] = l_dat;
            l_dats[1] = l_dat;
            l_dats[2] = l_dat1;
            l_dats[3] = l_dat;
            l_condition.setBizDate(l_dats);
            
            l_info.setCalcCondition(l_condition);
            double l_dblSummaryTodayRepayContractAmount = l_info.getSummaryTodayRepayContractAmount();
            assertEquals(l_dblSummaryTodayRepayContractAmount + "", "0.0");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testGetSummaryTodayRepayContractAmountCase2()
    {
        final String STR_METHOD_NAME = "testGetSummaryTodayRepayContractAmountCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3TPContractInfo l_info = new WEB3TPContractInfo();
//            WEB3TPCalcCondition l_condition = new WEB3TPCalcCondition();
            Class c = Class.forName(WEB3TPCalcCondition.class.getName());
            WEB3TPCalcCondition l_condition = (WEB3TPCalcCondition)c.newInstance();
            Class[] param1 = {String.class, String.class};
            
            Method l_method = c.getDeclaredMethod("addInstBranCalcCondition", param1);
            l_method.setAccessible(true);
            Object[] l_obj = {"contractamount.apply.date", "3"};
            l_method.invoke(l_condition, l_obj);

            Date l_dat = WEB3DateUtility.getDate("20070101","yyyyMMdd");
            Date l_dat1 = WEB3DateUtility.getDate("20080101","yyyyMMdd");
            
            Date[] l_dats = new Date[6];
            l_dats[0] = l_dat;
            l_dats[1] = l_dat1;
            l_dats[2] = l_dat;
            l_dats[3] = l_dat;
            l_condition.setBizDate(l_dats);
            
            l_info.setCalcCondition(l_condition);
            double l_dblSummaryTodayRepayContractAmount = l_info.getSummaryTodayRepayContractAmount();
            assertEquals(l_dblSummaryTodayRepayContractAmount + "", "0.0");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetSummaryTodayRepayContractAmountCase3()
    {
        final String STR_METHOD_NAME = "testGetSummaryTodayRepayContractAmountCase3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3TPContractInfo l_info = new WEB3TPContractInfo();
//            WEB3TPCalcCondition l_condition = new WEB3TPCalcCondition();
            Class c = Class.forName(WEB3TPCalcCondition.class.getName());
            WEB3TPCalcCondition l_condition = (WEB3TPCalcCondition)c.newInstance();
            Class[] param1 = {String.class, String.class};
            
            Method l_method = c.getDeclaredMethod("addInstBranCalcCondition", param1);
            l_method.setAccessible(true);
            Object[] l_obj = {"contractamount.apply.date", "2"};
            l_method.invoke(l_condition, l_obj);

            Date l_dat = WEB3DateUtility.getDate("20070101","yyyyMMdd");
            Date l_dat1 = WEB3DateUtility.getDate("20080101","yyyyMMdd");
            
            Date[] l_dats = new Date[6];
            l_dats[0] = l_dat1;
            l_dats[1] = l_dat;
            l_dats[2] = l_dat;
            l_dats[3] = l_dat;
            l_condition.setBizDate(l_dats);
            
            l_info.setCalcCondition(l_condition);
            double l_dblSummaryTodayRepayContractAmount = l_info.getSummaryTodayRepayContractAmount();
            assertEquals(l_dblSummaryTodayRepayContractAmount + "", "0.0");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetSummaryTodayRepayContractAmountCase4()
    {
        final String STR_METHOD_NAME = "testGetSummaryTodayRepayContractAmountCase4()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3TPContractInfoForTest l_info = new WEB3TPContractInfoForTest();
            WEB3TPCalcCondition l_condition = new WEB3TPCalcCondition();
            Date l_dat = WEB3DateUtility.getDate("20070701","yyyyMMdd");
            
            Date[] l_dats = new Date[1];
            l_dats[0] = l_dat;
            l_condition.setBizDate(l_dats);
            
            l_info.setCalcCondition(l_condition);

            double l_dblSummaryTodayRepayContractAmount = l_info.getSummaryTodayRepayContractAmount();
            assertEquals(l_dblSummaryTodayRepayContractAmount + "", "3150.0");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testLoadUnexecutedOrderSpecs_0001()
    {
        final String STR_METHOD_NAME = "testLoadUnexecutedOrderSpecs_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3TPContractInfo l_contractInfo = new WEB3TPContractInfoForTest1();
            
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(333812512203L);
            Hashtable l_hsSubAccountIds = new Hashtable();
            l_hsSubAccountIds.put(new Long(33381251220301L), SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_accountInfo.setSubAccountIds(l_hsSubAccountIds);
            l_accountInfo.setMarginCustFlag(true);
            l_contractInfo.setAccountInfo(l_accountInfo);
            
            List l_todaysEquityOrders = new ArrayList();
            l_contractInfo.setTodaysEquityOrders(l_todaysEquityOrders);
            
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_newOrderSpec = new WEB3TPNewOrderSpec();
            l_newOrderSpec.setOrderCategory(OrderCategEnum.SWAP_MARGIN);
            l_newOrderSpecs[0] = l_newOrderSpec;
            l_newOrderSpecs[0].setDeliveryDate(WEB3DateUtility.getDate("20080202", "yyyyMMdd"));
            
            l_contractInfo.setNewOrderSpecs(l_newOrderSpecs);
            
            WEB3TPCalcConditionForTest l_calcCondition = new WEB3TPCalcConditionForTest();
            Date[] l_datBizDates = new Date[8];
            for (int i = 1; i <= 8; i++)
            {
                l_datBizDates[i - 1] = WEB3DateUtility.getDate("2008020" + i, "yyyyMMdd");
            }

            l_calcCondition.setBizDate(l_datBizDates);
            l_calcCondition.addInstBranCalcCondition(
                "eqtype.swap.margin.cost.undelivered.contract.loss.div", "1");
            l_contractInfo.setCalcCondition(l_calcCondition);
            
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setContractId(12347);
            l_targetContractDetail.setContractType(ContractTypeEnum.LONG);
            l_targetContractDetail.setOriginalQuantity(10);
            l_targetContractDetail.setSetupFee(10);
            l_targetContractDetail.setSetupFeeTax(10);
            l_targetContractDetail.setNameTransferFee(10);
            l_targetContractDetail.setNameTransferFeeTax(10);
            l_targetContractDetail.setManagementFee(10);
            l_targetContractDetail.setManagementFeeTax(10);
            l_targetContractDetail.setInterestFee(10);
            l_targetContractDetail.setPayInterestFee(20);
            l_targetContractDetail.setLoanEquityFee(30);
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            l_targetContract.setContractExecuted(true);
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContract();
            
            l_contractInfo.addHistoryPerContract(l_targetContract, l_historyPerContract);

            l_contractInfo.loadUnexecutedOrderSpecs();
            WEB3TPHistoryPerContract l_historyPerContractResult =
                l_contractInfo.getHistoryPerContract(l_targetContract);
            WEB3TPHistory l_history = (WEB3TPHistory)l_historyPerContractResult.getHistories().get(0);
            assertEquals("70.0", "" + l_history.getContractTotalCost());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testLoadUnexecutedOrderSpecs_0002()
    {
        final String STR_METHOD_NAME = "testLoadUnexecutedOrderSpecs_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3TPContractInfo l_contractInfo = new WEB3TPContractInfoForTest1();
            
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(333812512203L);
            Hashtable l_hsSubAccountIds = new Hashtable();
            l_hsSubAccountIds.put(new Long(33381251220301L), SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_accountInfo.setSubAccountIds(l_hsSubAccountIds);
            l_accountInfo.setMarginCustFlag(true);
            l_contractInfo.setAccountInfo(l_accountInfo);
            
            List l_todaysEquityOrders = new ArrayList();
            l_contractInfo.setTodaysEquityOrders(l_todaysEquityOrders);
            
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_newOrderSpec = new WEB3TPNewOrderSpec();
            l_newOrderSpec.setOrderCategory(OrderCategEnum.SWAP_MARGIN);
            l_newOrderSpecs[0] = l_newOrderSpec;
            l_newOrderSpecs[0].setDeliveryDate(WEB3DateUtility.getDate("20080202", "yyyyMMdd"));
            
            l_contractInfo.setNewOrderSpecs(l_newOrderSpecs);
            
            WEB3TPCalcConditionForTest l_calcCondition = new WEB3TPCalcConditionForTest();
            Date[] l_datBizDates = new Date[8];
            for (int i = 1; i <= 8; i++)
            {
                l_datBizDates[i - 1] = WEB3DateUtility.getDate("2008020" + i, "yyyyMMdd");
            }

            l_calcCondition.setBizDate(l_datBizDates);
            l_calcCondition.addInstBranCalcCondition(
                "eqtype.swap.margin.cost.undelivered.contract.loss.div", "1");
            l_contractInfo.setCalcCondition(l_calcCondition);
            
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setContractId(12347);
            l_targetContractDetail.setContractType(ContractTypeEnum.SHORT);
            l_targetContractDetail.setOriginalQuantity(30);
            l_targetContractDetail.setSetupFee(10);
            l_targetContractDetail.setSetupFeeTax(10);
            l_targetContractDetail.setNameTransferFee(10);
            l_targetContractDetail.setNameTransferFeeTax(10);
            l_targetContractDetail.setManagementFee(10);
            l_targetContractDetail.setManagementFeeTax(10);
            l_targetContractDetail.setInterestFee(10);
            l_targetContractDetail.setPayInterestFee(20);
            l_targetContractDetail.setLoanEquityFee(30);
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            l_targetContract.setContractExecuted(true);
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContract();
            
            l_contractInfo.addHistoryPerContract(l_targetContract, l_historyPerContract);

            l_contractInfo.loadUnexecutedOrderSpecs();
            WEB3TPHistoryPerContract l_historyPerContractResult =
                l_contractInfo.getHistoryPerContract(l_targetContract);
            WEB3TPHistory l_history = (WEB3TPHistory)l_historyPerContractResult.getHistories().get(0);
            assertEquals("33.0", "" + l_history.getContractTotalCost());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testLoadUnexecutedOrderSpecs_0003()
    {
        final String STR_METHOD_NAME = "testLoadUnexecutedOrderSpecs_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3TPContractInfo l_contractInfo = new WEB3TPContractInfoForTest1();
            
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(333812512203L);
            Hashtable l_hsSubAccountIds = new Hashtable();
            l_hsSubAccountIds.put(new Long(33381251220301L), SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_accountInfo.setSubAccountIds(l_hsSubAccountIds);
            l_accountInfo.setMarginCustFlag(true);
            l_contractInfo.setAccountInfo(l_accountInfo);
            
            List l_todaysEquityOrders = new ArrayList();
            l_contractInfo.setTodaysEquityOrders(l_todaysEquityOrders);
            
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_newOrderSpec = new WEB3TPNewOrderSpec();
            l_newOrderSpec.setOrderCategory(OrderCategEnum.SWAP_MARGIN);
            l_newOrderSpecs[0] = l_newOrderSpec;
            l_newOrderSpecs[0].setDeliveryDate(WEB3DateUtility.getDate("20080202", "yyyyMMdd"));
            
            l_contractInfo.setNewOrderSpecs(l_newOrderSpecs);
            
            WEB3TPCalcConditionForTest l_calcCondition = new WEB3TPCalcConditionForTest();
            Date[] l_datBizDates = new Date[8];
            for (int i = 1; i <= 8; i++)
            {
                l_datBizDates[i - 1] = WEB3DateUtility.getDate("2008020" + i, "yyyyMMdd");
            }

            l_calcCondition.setBizDate(l_datBizDates);
            l_calcCondition.addInstBranCalcCondition(
                "eqtype.swap.margin.cost.undelivered.contract.loss.div", "1");
            l_contractInfo.setCalcCondition(l_calcCondition);
            
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setContractId(12347);
            l_targetContractDetail.setContractType(ContractTypeEnum.SHORT);
            l_targetContractDetail.setOriginalQuantity(0);
            l_targetContractDetail.setSetupFee(10);
            l_targetContractDetail.setSetupFeeTax(10);
            l_targetContractDetail.setNameTransferFee(10);
            l_targetContractDetail.setNameTransferFeeTax(10);
            l_targetContractDetail.setManagementFee(10);
            l_targetContractDetail.setManagementFeeTax(10);
            l_targetContractDetail.setInterestFee(10);
            l_targetContractDetail.setPayInterestFee(20);
            l_targetContractDetail.setLoanEquityFee(30);
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            l_targetContract.setContractExecuted(true);
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContract();
            
            l_contractInfo.addHistoryPerContract(l_targetContract, l_historyPerContract);

            l_contractInfo.loadUnexecutedOrderSpecs();
            WEB3TPHistoryPerContract l_historyPerContractResult =
                l_contractInfo.getHistoryPerContract(l_targetContract);
            WEB3TPHistory l_history = (WEB3TPHistory)l_historyPerContractResult.getHistories().get(0);
            assertEquals("0.0", "" + l_history.getContractTotalCost());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testLoadUnexecutedOrderSpecs_0004()
    {
        final String STR_METHOD_NAME = "testLoadUnexecutedOrderSpecs_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3TPContractInfo l_contractInfo = new WEB3TPContractInfoForTest1();
            
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(333812512203L);
            Hashtable l_hsSubAccountIds = new Hashtable();
            l_hsSubAccountIds.put(new Long(33381251220301L), SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_accountInfo.setSubAccountIds(l_hsSubAccountIds);
            l_accountInfo.setMarginCustFlag(true);
            l_contractInfo.setAccountInfo(l_accountInfo);
            
            List l_todaysEquityOrders = new ArrayList();
            l_contractInfo.setTodaysEquityOrders(l_todaysEquityOrders);
            
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_newOrderSpec = new WEB3TPNewOrderSpec();
            l_newOrderSpec.setOrderCategory(OrderCategEnum.SWAP_MARGIN);
            l_newOrderSpecs[0] = l_newOrderSpec;
            l_newOrderSpecs[0].setDeliveryDate(WEB3DateUtility.getDate("20080202", "yyyyMMdd"));
            
            l_contractInfo.setNewOrderSpecs(l_newOrderSpecs);
            
            WEB3TPCalcConditionForTest l_calcCondition = new WEB3TPCalcConditionForTest();
            Date[] l_datBizDates = new Date[8];
            for (int i = 1; i <= 8; i++)
            {
                l_datBizDates[i - 1] = WEB3DateUtility.getDate("2008020" + i, "yyyyMMdd");
            }

            l_calcCondition.setBizDate(l_datBizDates);
            l_calcCondition.addInstBranCalcCondition(
                "eqtype.swap.margin.cost.undelivered.contract.loss.div", "0");
            l_contractInfo.setCalcCondition(l_calcCondition);
            
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setContractId(12347);
            l_targetContractDetail.setContractType(ContractTypeEnum.SHORT);
            l_targetContractDetail.setOriginalQuantity(0);
            l_targetContractDetail.setSetupFee(10);
            l_targetContractDetail.setSetupFeeTax(10);
            l_targetContractDetail.setNameTransferFee(10);
            l_targetContractDetail.setNameTransferFeeTax(10);
            l_targetContractDetail.setManagementFee(10);
            l_targetContractDetail.setManagementFeeTax(10);
            l_targetContractDetail.setInterestFee(10);
            l_targetContractDetail.setPayInterestFee(20);
            l_targetContractDetail.setLoanEquityFee(30);
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            l_targetContract.setContractExecuted(true);
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContract();
            
            l_contractInfo.addHistoryPerContract(l_targetContract, l_historyPerContract);

            l_contractInfo.loadUnexecutedOrderSpecs();
            WEB3TPHistoryPerContract l_historyPerContractResult =
                l_contractInfo.getHistoryPerContract(l_targetContract);
            WEB3TPHistory l_history = (WEB3TPHistory)l_historyPerContractResult.getHistories().get(0);
            assertEquals("0.0", "" + l_history.getContractTotalCost());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    

    /**
     * 建玉変動情報<確定>をロードする
        確定トランザクションテーブル.トランザクションカテゴリ != 60:現引現渡時
        建玉変動. set建玉諸経費()
     */
    public void testLoadFixedHistoriesCase0001()
    {
        final String STR_METHOD_NAME = "testGetCarryOverOrderUnitCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(FixedFinTransactionRow.TYPE);
            FixedFinTransactionParams l_fixedFinTransactionParams = TestDBUtility.getFixedFinTransactionRow();
            l_fixedFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20080131", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_fixedFinTransactionParams);
            
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(l_fixedFinTransactionParams.getAccountId());
            Hashtable l_hashtable = new Hashtable();
            l_hashtable.put(new Long(l_fixedFinTransactionParams.getSubAccountId()),
                SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_accountInfo.setSubAccountIds(l_hashtable);

            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Date[] l_datBizDates = new Date[8];
            Date l_datBizDate = WEB3DateUtility.getDate("20080130", "yyyyMMdd");
            l_datBizDates[1] = l_datBizDate;
            l_datBizDates[2] = WEB3DateUtility.getDate("20080131", "yyyyMMdd");
            l_datBizDates[3] = WEB3DateUtility.getDate("20080131", "yyyyMMdd");
            l_calcCondition.setBizDate(l_datBizDates);

            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPContractInfo l_info =
                WEB3TPContractInfo.create(l_accountInfo, l_calcCondition, l_newOrderSpecs);
            
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setContractExecuted(true);
            WEB3TPTargetContractDetail l_detail = new WEB3TPTargetContractDetail();
            l_detail.setContractId(l_fixedFinTransactionParams.getFixedContractId());
            l_targetContract.setTargetContractDetail(l_detail);
            List l_targetContracts = l_info.getTargetContracts();
            l_targetContracts.add(l_targetContract);
            
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContract();
            l_info.addHistoryPerContract(l_targetContract, l_historyPerContract);
            l_info.loadFixedHistories();
            
            List l_lisHistories = l_historyPerContract.getHistories();
            if (l_lisHistories.isEmpty())
            {
                log.debug("WEB3TPHistoryPerContract.histories.isEmpty");
                fail();
            }
            for (int i = 0; i < l_lisHistories.size(); i++)
            {
                WEB3TPHistory l_history = (WEB3TPHistory)l_lisHistories.get(i);

                assertEquals(WEB3StringTypeUtility.formatNumber(l_history.getContractTotalCost()), "0");
            }
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 建玉変動情報<確定>をロードする
        this.get余力計算条件.get会社部店別余力計算条件（”eqtype.swap.margin.cost.undelivered.contract.loss.div”） = NULL時
        建玉変動. set建玉諸経費()
     */
    public void testLoadFixedHistoriesCase0002()
    {
        final String STR_METHOD_NAME = "testGetCarryOverOrderUnitCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(FixedFinTransactionRow.TYPE);
            FixedFinTransactionParams l_fixedFinTransactionParams = TestDBUtility.getFixedFinTransactionRow();
            l_fixedFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20080131", "yyyyMMdd"));
            l_fixedFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_SWAP_MARGIN);
            TestDBUtility.insertWithDel(l_fixedFinTransactionParams);
            
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(l_fixedFinTransactionParams.getAccountId());
            Hashtable l_hashtable = new Hashtable();
            l_hashtable.put(new Long(l_fixedFinTransactionParams.getSubAccountId()),
                SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_accountInfo.setSubAccountIds(l_hashtable);

            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Date[] l_datBizDates = new Date[8];
            Date l_datBizDate = WEB3DateUtility.getDate("20080130", "yyyyMMdd");
            l_datBizDates[1] = l_datBizDate;
            l_datBizDates[2] = WEB3DateUtility.getDate("20080131", "yyyyMMdd");
            l_datBizDates[3] = WEB3DateUtility.getDate("20080131", "yyyyMMdd");
            l_calcCondition.setBizDate(l_datBizDates);

            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPContractInfo l_info =
                WEB3TPContractInfo.create(l_accountInfo, l_calcCondition, l_newOrderSpecs);
            
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setContractExecuted(true);
            WEB3TPTargetContractDetail l_detail = new WEB3TPTargetContractDetail();
            l_detail.setContractId(l_fixedFinTransactionParams.getFixedContractId());
            l_targetContract.setTargetContractDetail(l_detail);
            List l_targetContracts = l_info.getTargetContracts();
            l_targetContracts.add(l_targetContract);
            
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContract();
            l_info.addHistoryPerContract(l_targetContract, l_historyPerContract);
            l_info.loadFixedHistories();
            
            List l_lisHistories = l_historyPerContract.getHistories();
            if (l_lisHistories.isEmpty())
            {
                log.debug("WEB3TPHistoryPerContract.histories.isEmpty");
                fail();
            }
            for (int i = 0; i < l_lisHistories.size(); i++)
            {
                WEB3TPHistory l_history = (WEB3TPHistory)l_lisHistories.get(i);

                assertEquals(WEB3StringTypeUtility.formatNumber(l_history.getContractTotalCost()), "0");
            }
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 建玉変動情報<確定>をロードする
        this.get余力計算条件.get会社部店別余力計算条件（”eqtype.swap.margin.cost.undelivered.contract.loss.div”） == 0”時
        建玉変動. set建玉諸経費()
     */
    public void testLoadFixedHistoriesCase0003()
    {
        final String STR_METHOD_NAME = "testGetCarryOverOrderUnitCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(FixedFinTransactionRow.TYPE);
            FixedFinTransactionParams l_fixedFinTransactionParams = TestDBUtility.getFixedFinTransactionRow();
            l_fixedFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20080131", "yyyyMMdd"));
            l_fixedFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_SWAP_MARGIN);
            TestDBUtility.insertWithDel(l_fixedFinTransactionParams);
            
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(l_fixedFinTransactionParams.getAccountId());
            Hashtable l_hashtable = new Hashtable();
            l_hashtable.put(new Long(l_fixedFinTransactionParams.getSubAccountId()),
                SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_accountInfo.setSubAccountIds(l_hashtable);
            
            WEB3TPCalcConditionForTest l_calcCondition = new WEB3TPCalcConditionForTest();
            Date[] l_datBizDates = new Date[8];
            Date l_datBizDate = WEB3DateUtility.getDate("20080130", "yyyyMMdd");
            l_datBizDates[1] = l_datBizDate;
            l_datBizDates[2] = WEB3DateUtility.getDate("20080131", "yyyyMMdd");
            l_datBizDates[3] = WEB3DateUtility.getDate("20080131", "yyyyMMdd");
            l_calcCondition.setBizDate(l_datBizDates);
            l_calcCondition.addInstBranCalcCondition("eqtype.swap.margin.cost.undelivered.contract.loss.div", "0");

            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPContractInfo l_info =
                WEB3TPContractInfo.create(l_accountInfo, l_calcCondition, l_newOrderSpecs);
            
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setContractExecuted(true);
            WEB3TPTargetContractDetail l_detail = new WEB3TPTargetContractDetail();
            l_detail.setContractId(l_fixedFinTransactionParams.getFixedContractId());
            l_targetContract.setTargetContractDetail(l_detail);
            List l_targetContracts = l_info.getTargetContracts();
            l_targetContracts.add(l_targetContract);
            
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContract();
            l_info.addHistoryPerContract(l_targetContract, l_historyPerContract);
            l_info.loadFixedHistories();
            
            List l_lisHistories = l_historyPerContract.getHistories();
            if (l_lisHistories.isEmpty())
            {
                log.debug("WEB3TPHistoryPerContract.histories.isEmpty");
                fail();
            }
            for (int i = 0; i < l_lisHistories.size(); i++)
            {
                WEB3TPHistory l_history = (WEB3TPHistory)l_lisHistories.get(i);

                assertEquals(WEB3StringTypeUtility.formatNumber(l_history.getContractTotalCost()), "0");
            }
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 建玉変動情報<確定>をロードする
        対象建玉詳細.建区分 = ”買建”の場合
        建玉変動. set建玉諸経費()
     */
    public void testLoadFixedHistoriesCase0004()
    {
        final String STR_METHOD_NAME = "testGetCarryOverOrderUnitCase0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(FixedFinTransactionRow.TYPE);
            FixedFinTransactionParams l_fixedFinTransactionParams = TestDBUtility.getFixedFinTransactionRow();
            l_fixedFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20080131", "yyyyMMdd"));
            l_fixedFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_SWAP_MARGIN);
            //建手数料          imported_setup_fee      NOT NULL
            l_fixedFinTransactionParams.setImportedSetupFee(100);
            
            //建手数料消費税           imported_setup_fee_tax      NOT NULL
            l_fixedFinTransactionParams.setImportedSetupFeeTax(200);
            
            //名義書換料         imported_name_transfer_fee 
            l_fixedFinTransactionParams.setImportedNameTransferFee(300);
            
            //名義書換料消費税      imported_name_transfer_fee_tax
            l_fixedFinTransactionParams.setImportedNameTransferFeeTax(400);
            
            //管理費               imported_management_fee     NOT NULL
            l_fixedFinTransactionParams.setImportedManagementFee(500);
            
            //管理費消費税            imported_management_fee_tax NOT NULL
            l_fixedFinTransactionParams.setImportedManagementFeeTax(600);
            
            //順日歩               imported_interest_fee       NOT NULL
            l_fixedFinTransactionParams.setImportedInterestFee(700);
            
            //逆日歩               imported_pay_interest_fee  
            l_fixedFinTransactionParams.setImportedPayInterestFee(800);
            
            //貸株料               imported_loan_equity_fee   
            l_fixedFinTransactionParams.setImportedLoanEquityFee(900);
            TestDBUtility.insertWithDel(l_fixedFinTransactionParams);
            
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(l_fixedFinTransactionParams.getAccountId());
            Hashtable l_hashtable = new Hashtable();
            l_hashtable.put(new Long(l_fixedFinTransactionParams.getSubAccountId()),
                SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_accountInfo.setSubAccountIds(l_hashtable);
            
            WEB3TPCalcConditionForTest l_calcCondition = new WEB3TPCalcConditionForTest();
            Date[] l_datBizDates = new Date[8];
            Date l_datBizDate = WEB3DateUtility.getDate("20080130", "yyyyMMdd");
            l_datBizDates[1] = l_datBizDate;
            l_datBizDates[2] = WEB3DateUtility.getDate("20080131", "yyyyMMdd");
            l_datBizDates[3] = WEB3DateUtility.getDate("20080131", "yyyyMMdd");
            l_calcCondition.setBizDate(l_datBizDates);
            l_calcCondition.addInstBranCalcCondition("eqtype.swap.margin.cost.undelivered.contract.loss.div", "1");

            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPContractInfo l_info =
                WEB3TPContractInfo.create(l_accountInfo, l_calcCondition, l_newOrderSpecs);
            
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setContractExecuted(true);
            WEB3TPTargetContractDetail l_detail = new WEB3TPTargetContractDetail();
            l_detail.setContractId(l_fixedFinTransactionParams.getFixedContractId());
            l_detail.setContractType(ContractTypeEnum.LONG);
            l_targetContract.setTargetContractDetail(l_detail);
            List l_targetContracts = l_info.getTargetContracts();
            l_targetContracts.add(l_targetContract);
            
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContract();
            l_info.addHistoryPerContract(l_targetContract, l_historyPerContract);
            l_info.loadFixedHistories();
            
            List l_lisHistories = l_historyPerContract.getHistories();
            if (l_lisHistories.isEmpty())
            {
                log.debug("WEB3TPHistoryPerContract.histories.isEmpty");
                fail();
            }
            for (int i = 0; i < l_lisHistories.size(); i++)
            {
                WEB3TPHistory l_history = (WEB3TPHistory)l_lisHistories.get(i);

                assertEquals(WEB3StringTypeUtility.formatNumber(l_history.getContractTotalCost()), "2800");
            }
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 建玉変動情報<確定>をロードする
        対象建玉詳細.建区分 = ”買建”の場合
        建玉変動. set建玉諸経費()
     */
    public void testLoadFixedHistoriesCase0005()
    {
        final String STR_METHOD_NAME = "testGetCarryOverOrderUnitCase0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(FixedFinTransactionRow.TYPE);
            FixedFinTransactionParams l_fixedFinTransactionParams = TestDBUtility.getFixedFinTransactionRow();
            l_fixedFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20080131", "yyyyMMdd"));
            l_fixedFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_SWAP_MARGIN);
            //建手数料          imported_setup_fee      NOT NULL
            l_fixedFinTransactionParams.setImportedSetupFee(100);
            
            //建手数料消費税           imported_setup_fee_tax      NOT NULL
            l_fixedFinTransactionParams.setImportedSetupFeeTax(200);
            
            //名義書換料         imported_name_transfer_fee 
            l_fixedFinTransactionParams.setImportedNameTransferFee(300);
            
            //名義書換料消費税      imported_name_transfer_fee_tax
            l_fixedFinTransactionParams.setImportedNameTransferFeeTax(400);
            
            //管理費               imported_management_fee     NOT NULL
            l_fixedFinTransactionParams.setImportedManagementFee(500);
            
            //管理費消費税            imported_management_fee_tax NOT NULL
            l_fixedFinTransactionParams.setImportedManagementFeeTax(600);
            
            //順日歩               imported_interest_fee       NOT NULL
            l_fixedFinTransactionParams.setImportedInterestFee(700);
            
            //逆日歩               imported_pay_interest_fee  
            l_fixedFinTransactionParams.setImportedPayInterestFee(800);
            
            //貸株料               imported_loan_equity_fee   
            l_fixedFinTransactionParams.setImportedLoanEquityFee(900);
            TestDBUtility.insertWithDel(l_fixedFinTransactionParams);
            
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(l_fixedFinTransactionParams.getAccountId());
            Hashtable l_hashtable = new Hashtable();
            l_hashtable.put(new Long(l_fixedFinTransactionParams.getSubAccountId()),
                SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_accountInfo.setSubAccountIds(l_hashtable);
            
            WEB3TPCalcConditionForTest l_calcCondition = new WEB3TPCalcConditionForTest();
            Date[] l_datBizDates = new Date[8];
            Date l_datBizDate = WEB3DateUtility.getDate("20080130", "yyyyMMdd");
            l_datBizDates[1] = l_datBizDate;
            l_datBizDates[2] = WEB3DateUtility.getDate("20080131", "yyyyMMdd");
            l_datBizDates[3] = WEB3DateUtility.getDate("20080131", "yyyyMMdd");
            l_calcCondition.setBizDate(l_datBizDates);
            l_calcCondition.addInstBranCalcCondition("eqtype.swap.margin.cost.undelivered.contract.loss.div", "1");

            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPContractInfo l_info =
                WEB3TPContractInfo.create(l_accountInfo, l_calcCondition, l_newOrderSpecs);
            
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setContractExecuted(true);
            WEB3TPTargetContractDetail l_detail = new WEB3TPTargetContractDetail();
            l_detail.setContractId(l_fixedFinTransactionParams.getFixedContractId());
            l_detail.setContractType(ContractTypeEnum.SHORT);
            l_targetContract.setTargetContractDetail(l_detail);
            List l_targetContracts = l_info.getTargetContracts();
            l_targetContracts.add(l_targetContract);
            
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContract();
            l_info.addHistoryPerContract(l_targetContract, l_historyPerContract);
            l_info.loadFixedHistories();
            
            List l_lisHistories = l_historyPerContract.getHistories();
            if (l_lisHistories.isEmpty())
            {
                log.debug("WEB3TPHistoryPerContract.histories.isEmpty");
                fail();
            }
            for (int i = 0; i < l_lisHistories.size(); i++)
            {
                WEB3TPHistory l_history = (WEB3TPHistory)l_lisHistories.get(i);

                assertEquals(WEB3StringTypeUtility.formatNumber(l_history.getContractTotalCost()), "3800");
            }
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     *  do建玉変動情報<当日>ロード (), 
        対象建玉詳細.建区分 = ”買建”の場合
        建玉変動. set建玉諸経費()
     */
    public void testLoadTodaysHistoriesCase0001()
    {
        final String STR_METHOD_NAME = "testLoadTodaysHistoriesCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeFinTransactionRow.TYPE);
            EqtypeFinTransactionParams l_eqtypeFinTransactionParams = TestDBUtility.getEqtypeFinTransactionParams();
            l_eqtypeFinTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20080130", "yyyyMMdd"));
            l_eqtypeFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20080130", "yyyyMMdd"));
            l_eqtypeFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_SWAP_MARGIN);
            //建手数料          imported_setup_fee      NOT NULL
            l_eqtypeFinTransactionParams.setImportedSetupFee(100);
            
            //建手数料消費税           imported_setup_fee_tax      NOT NULL
            l_eqtypeFinTransactionParams.setImportedSetupFeeTax(200);
            
            //名義書換料         imported_name_transfer_fee 
            l_eqtypeFinTransactionParams.setImportedNameTransferFee(300);
            
            //名義書換料消費税      imported_name_transfer_fee_tax
            l_eqtypeFinTransactionParams.setImportedNameTransferFeeTax(400);
            
            //管理費               imported_management_fee     NOT NULL
            l_eqtypeFinTransactionParams.setImportedManagementFee(500);
            
            //管理費消費税            imported_management_fee_tax NOT NULL
            l_eqtypeFinTransactionParams.setImportedManagementFeeTax(600);
            
            //順日歩               imported_interest_fee       NOT NULL
            l_eqtypeFinTransactionParams.setImportedInterestFee(700);
            
            //逆日歩               imported_pay_interest_fee  
            l_eqtypeFinTransactionParams.setImportedPayInterestFee(800);
            
            //貸株料               imported_loan_equity_fee   
            l_eqtypeFinTransactionParams.setImportedLoanEquityFee(900);
            TestDBUtility.insertWithDel(l_eqtypeFinTransactionParams);
            
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(l_eqtypeFinTransactionParams.getAccountId());
            Hashtable l_hashtable = new Hashtable();
            l_hashtable.put(new Long(l_eqtypeFinTransactionParams.getSubAccountId()),
                SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_accountInfo.setSubAccountIds(l_hashtable);
            
            WEB3TPCalcConditionForTest l_calcCondition = new WEB3TPCalcConditionForTest();
            Date[] l_datBizDates = new Date[8];
            Date l_datBizDate = WEB3DateUtility.getDate("20080130", "yyyyMMdd");
            l_datBizDates[1] = l_datBizDate;
            l_datBizDates[2] = WEB3DateUtility.getDate("20080131", "yyyyMMdd");
            l_datBizDates[3] = WEB3DateUtility.getDate("20080131", "yyyyMMdd");
            l_calcCondition.setBizDate(l_datBizDates);
            l_calcCondition.addInstBranCalcCondition("eqtype.swap.margin.cost.undelivered.contract.loss.div", "1");

            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPContractInfo l_info =
                WEB3TPContractInfo.create(l_accountInfo, l_calcCondition, l_newOrderSpecs);
            
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setContractExecuted(true);
            WEB3TPTargetContractDetail l_detail = new WEB3TPTargetContractDetail();
            l_detail.setContractId(l_eqtypeFinTransactionParams.getContractId());
            l_detail.setContractType(ContractTypeEnum.LONG);
            l_targetContract.setTargetContractDetail(l_detail);
            List l_targetContracts = l_info.getTargetContracts();
            l_targetContracts.add(l_targetContract);
            
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContract();
            l_info.addHistoryPerContract(l_targetContract, l_historyPerContract);
            l_info.loadTodaysHistories();
            
            List l_lisHistories = l_historyPerContract.getHistories();
            if (l_lisHistories.isEmpty())
            {
                log.debug("WEB3TPHistoryPerContract.histories.isEmpty");
                fail();
            }
            for (int i = 0; i < l_lisHistories.size(); i++)
            {
                WEB3TPHistory l_history = (WEB3TPHistory)l_lisHistories.get(i);

                assertEquals(WEB3StringTypeUtility.formatNumber(l_history.getContractTotalCost()), "2800");
            }
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *  do建玉変動情報<当日>ロード (), 
        対象建玉詳細.建区分 = ”買建”の場合
        建玉変動. set建玉諸経費()
     */
    public void testLoadTodaysHistoriesCase0002()
    {
        final String STR_METHOD_NAME = "testLoadTodaysHistoriesCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeFinTransactionRow.TYPE);
            EqtypeFinTransactionParams l_eqtypeFinTransactionParams = TestDBUtility.getEqtypeFinTransactionParams();
            l_eqtypeFinTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20080130", "yyyyMMdd"));
            l_eqtypeFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20080130", "yyyyMMdd"));
            l_eqtypeFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_SWAP_MARGIN);
            //建手数料          imported_setup_fee      NOT NULL
            l_eqtypeFinTransactionParams.setImportedSetupFee(100);
            
            //建手数料消費税           imported_setup_fee_tax      NOT NULL
            l_eqtypeFinTransactionParams.setImportedSetupFeeTax(200);
            
            //名義書換料         imported_name_transfer_fee 
            l_eqtypeFinTransactionParams.setImportedNameTransferFee(300);
            
            //名義書換料消費税      imported_name_transfer_fee_tax
            l_eqtypeFinTransactionParams.setImportedNameTransferFeeTax(400);
            
            //管理費               imported_management_fee     NOT NULL
            l_eqtypeFinTransactionParams.setImportedManagementFee(500);
            
            //管理費消費税            imported_management_fee_tax NOT NULL
            l_eqtypeFinTransactionParams.setImportedManagementFeeTax(600);
            
            //順日歩               imported_interest_fee       NOT NULL
            l_eqtypeFinTransactionParams.setImportedInterestFee(700);
            
            //逆日歩               imported_pay_interest_fee  
            l_eqtypeFinTransactionParams.setImportedPayInterestFee(800);
            
            //貸株料               imported_loan_equity_fee   
            l_eqtypeFinTransactionParams.setImportedLoanEquityFee(900);
            TestDBUtility.insertWithDel(l_eqtypeFinTransactionParams);
            
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(l_eqtypeFinTransactionParams.getAccountId());
            Hashtable l_hashtable = new Hashtable();
            l_hashtable.put(new Long(l_eqtypeFinTransactionParams.getSubAccountId()),
                SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_accountInfo.setSubAccountIds(l_hashtable);
            
            WEB3TPCalcConditionForTest l_calcCondition = new WEB3TPCalcConditionForTest();
            Date[] l_datBizDates = new Date[8];
            Date l_datBizDate = WEB3DateUtility.getDate("20080130", "yyyyMMdd");
            l_datBizDates[1] = l_datBizDate;
            l_datBizDates[2] = WEB3DateUtility.getDate("20080131", "yyyyMMdd");
            l_datBizDates[3] = WEB3DateUtility.getDate("20080131", "yyyyMMdd");
            l_calcCondition.setBizDate(l_datBizDates);
            l_calcCondition.addInstBranCalcCondition("eqtype.swap.margin.cost.undelivered.contract.loss.div", "1");

            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPContractInfo l_info =
                WEB3TPContractInfo.create(l_accountInfo, l_calcCondition, l_newOrderSpecs);
            
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setContractExecuted(true);
            WEB3TPTargetContractDetail l_detail = new WEB3TPTargetContractDetail();
            l_detail.setContractId(l_eqtypeFinTransactionParams.getContractId());
            l_detail.setContractType(ContractTypeEnum.SHORT);
            l_targetContract.setTargetContractDetail(l_detail);
            List l_targetContracts = l_info.getTargetContracts();
            l_targetContracts.add(l_targetContract);
            
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContract();
            l_info.addHistoryPerContract(l_targetContract, l_historyPerContract);
            l_info.loadTodaysHistories();
            
            List l_lisHistories = l_historyPerContract.getHistories();
            if (l_lisHistories.isEmpty())
            {
                log.debug("WEB3TPHistoryPerContract.histories.isEmpty");
                fail();
            }
            for (int i = 0; i < l_lisHistories.size(); i++)
            {
                WEB3TPHistory l_history = (WEB3TPHistory)l_lisHistories.get(i);

                assertEquals(WEB3StringTypeUtility.formatNumber(l_history.getContractTotalCost()), "3800");
            }
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *  do建玉変動情報<当日>ロード ()
        this.get余力計算条件.get会社部店別余力計算条件（”eqtype.swap.margin.cost.undelivered.contract.loss.div”） = 0時
        建玉変動. set建玉諸経費()
     */
    public void testLoadTodaysHistoriesCase0003()
    {
        final String STR_METHOD_NAME = "testLoadTodaysHistoriesCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(FixedFinTransactionRow.TYPE);
            EqtypeFinTransactionParams l_eqtypeFinTransactionParams = TestDBUtility.getEqtypeFinTransactionParams();
            l_eqtypeFinTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20080130", "yyyyMMdd"));
            l_eqtypeFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20080130", "yyyyMMdd"));
            l_eqtypeFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_SWAP_MARGIN);
            TestDBUtility.insertWithDel(l_eqtypeFinTransactionParams);
            
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(l_eqtypeFinTransactionParams.getAccountId());
            Hashtable l_hashtable = new Hashtable();
            l_hashtable.put(new Long(l_eqtypeFinTransactionParams.getSubAccountId()),
                SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_accountInfo.setSubAccountIds(l_hashtable);
            
            WEB3TPCalcConditionForTest l_calcCondition = new WEB3TPCalcConditionForTest();
            Date[] l_datBizDates = new Date[8];
            Date l_datBizDate = WEB3DateUtility.getDate("20080130", "yyyyMMdd");
            l_datBizDates[1] = l_datBizDate;
            l_datBizDates[2] = WEB3DateUtility.getDate("20080131", "yyyyMMdd");
            l_datBizDates[3] = WEB3DateUtility.getDate("20080131", "yyyyMMdd");
            l_calcCondition.setBizDate(l_datBizDates);
            l_calcCondition.addInstBranCalcCondition("eqtype.swap.margin.cost.undelivered.contract.loss.div", "0");

            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPContractInfo l_info =
                WEB3TPContractInfo.create(l_accountInfo, l_calcCondition, l_newOrderSpecs);
            
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setContractExecuted(true);
            WEB3TPTargetContractDetail l_detail = new WEB3TPTargetContractDetail();
            l_detail.setContractId(l_eqtypeFinTransactionParams.getContractId());
            l_targetContract.setTargetContractDetail(l_detail);
            List l_targetContracts = l_info.getTargetContracts();
            l_targetContracts.add(l_targetContract);
            
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContract();
            l_info.addHistoryPerContract(l_targetContract, l_historyPerContract);
            l_info.loadTodaysHistories();
            
            List l_lisHistories = l_historyPerContract.getHistories();
            if (l_lisHistories.isEmpty())
            {
                log.debug("WEB3TPHistoryPerContract.histories.isEmpty");
                fail();
            }
            for (int i = 0; i < l_lisHistories.size(); i++)
            {
                WEB3TPHistory l_history = (WEB3TPHistory)l_lisHistories.get(i);

                assertEquals(WEB3StringTypeUtility.formatNumber(l_history.getContractTotalCost()), "0");
            }
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * do建玉変動情報<当日>ロード ()
        確定トランザクションテーブル.トランザクションカテゴリ != 60:現引現渡時
        建玉変動. set建玉諸経費()
     */
    public void testLoadTodaysHistoriesCase0004()
    {
        final String STR_METHOD_NAME = "testGetCarryOverOrderUnitCase0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(FixedFinTransactionRow.TYPE);
            EqtypeFinTransactionParams l_eqtypeFinTransactionParams = TestDBUtility.getEqtypeFinTransactionParams();
            l_eqtypeFinTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20080130", "yyyyMMdd"));
            l_eqtypeFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20080130", "yyyyMMdd"));
            l_eqtypeFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_CLOSE_MARGIN);
            TestDBUtility.insertWithDel(l_eqtypeFinTransactionParams);
            
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(l_eqtypeFinTransactionParams.getAccountId());
            Hashtable l_hashtable = new Hashtable();
            l_hashtable.put(new Long(l_eqtypeFinTransactionParams.getSubAccountId()),
                SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_accountInfo.setSubAccountIds(l_hashtable);

            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Date[] l_datBizDates = new Date[8];
            Date l_datBizDate = WEB3DateUtility.getDate("20080130", "yyyyMMdd");
            l_datBizDates[1] = l_datBizDate;
            l_datBizDates[2] = WEB3DateUtility.getDate("20080131", "yyyyMMdd");
            l_datBizDates[3] = WEB3DateUtility.getDate("20080131", "yyyyMMdd");
            l_calcCondition.setBizDate(l_datBizDates);

            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPContractInfo l_info =
                WEB3TPContractInfo.create(l_accountInfo, l_calcCondition, l_newOrderSpecs);
            
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setContractExecuted(true);
            WEB3TPTargetContractDetail l_detail = new WEB3TPTargetContractDetail();
            l_detail.setContractId(l_eqtypeFinTransactionParams.getContractId());
            l_targetContract.setTargetContractDetail(l_detail);
            List l_targetContracts = l_info.getTargetContracts();
            l_targetContracts.add(l_targetContract);
            
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContract();
            l_info.addHistoryPerContract(l_targetContract, l_historyPerContract);
            l_info.loadTodaysHistories();
            
            List l_lisHistories = l_historyPerContract.getHistories();
            if (l_lisHistories.isEmpty())
            {
                log.debug("WEB3TPHistoryPerContract.histories.isEmpty");
                fail();
            }
            for (int i = 0; i < l_lisHistories.size(); i++)
            {
                WEB3TPHistory l_history = (WEB3TPHistory)l_lisHistories.get(i);

                assertEquals(WEB3StringTypeUtility.formatNumber(l_history.getContractTotalCost()), "0");
            }
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private class WEB3TPContractInfoForTest extends WEB3TPContractInfo
    {

        public List getTargetContracts()
        {
            WEB3TPTargetContractDetail targetContractDetail1 = new WEB3TPTargetContractDetail();
            targetContractDetail1.setOpenDate(WEB3DateUtility.getDate("20070801", "yyyyMMdd"));
            
            WEB3TPTargetContractDetail targetContractDetail2 = new WEB3TPTargetContractDetail();
            
            WEB3TPTargetContractDetail targetContractDetail3 = new WEB3TPTargetContractDetail();
            targetContractDetail3.setContractPrice(100);
            WEB3TPTargetContractDetail targetContractDetail4 = new WEB3TPTargetContractDetail();
            targetContractDetail4.setContractPrice(200);

            WEB3TPTargetContract l_targetContract0 = new WEB3TPTargetContract();
            WEB3TPTargetContract l_targetContract1 = new WEB3TPTargetContract();
            WEB3TPTargetContract l_targetContract2 = new WEB3TPTargetContract();
            WEB3TPTargetContract l_targetContract3 = new WEB3TPTargetContract();

            l_targetContract0.setTargetContractDetail(targetContractDetail1);
            l_targetContract1.setTargetContractDetail(targetContractDetail2);
            l_targetContract2.setTargetContractDetail(targetContractDetail3);
            l_targetContract3.setTargetContractDetail(targetContractDetail4);

            List l_lis = new ArrayList();
            l_lis.add(l_targetContract0);
            l_lis.add(l_targetContract1);
            l_lis.add(l_targetContract2);
            l_lis.add(l_targetContract3);
            return l_lis;
        }
        
        public WEB3TPHistoryPerContract getHistoryPerContract(WEB3TPTargetContract l_targetContract)
        {
            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Date l_dat = WEB3DateUtility.getDate("20070701","yyyyMMdd");
            
            Date[] l_dats = new Date[2];
            l_dats[0] = l_dat;
            l_dats[1] = l_dat;
            l_calcCondition.setBizDate(l_dats);
            
            WEB3TPContractInfo l_contractInfo = new WEB3TPContractInfo();
            l_contractInfo.setCalcCondition(l_calcCondition);
            
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContract();
            WEB3TPHistory l_history = new WEB3TPHistory();
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_CLOSE_MARGIN);
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_SWAP_MARGIN);
            l_history.setTransactionDate(WEB3DateUtility.getDate("20070701","yyyyMMdd"));
            l_history.setQuantity(10.5);
            l_historyPerContract.addHistory(l_history);
            
            l_historyPerContract.setContractInfo(l_contractInfo);
            
            return l_historyPerContract;
        }

    }
    
    private class WEB3TPCalcConditionForTest extends WEB3TPCalcCondition
    {
        protected void addInstBranCalcCondition(String l_strName, String l_strValue)
        {
            super.addInstBranCalcCondition(l_strName, l_strValue);
        }
    }
    
    private class WEB3TPContractInfoForTest1 extends WEB3TPContractInfo
    {
        public List getClosingContractSpecs(long l_lngOrderUnitId) 
        {
            WEB3TPClosingContractSpec l_closingContractSpec = new WEB3TPClosingContractSpec();
            l_closingContractSpec.setQuantity(20);
            l_closingContractSpec.setExecutedQuantity(10);
            l_closingContractSpec.setContractId(12347);
            List l_lisClosingContractSpecs = new ArrayList();
            l_lisClosingContractSpecs.add(l_closingContractSpec);
            return l_lisClosingContractSpecs;
        }
        
        public List getTargetContracts() 
        {
            List l_lisTargetContracts = new ArrayList();
            l_lisTargetContracts.add(l_targetContract);
            return l_lisTargetContracts;
        }
    }
    
    //4條數據
    public void testGetSummaryDayTradeSwapContractCase1()
    {
        final String STR_METHOD_NAME = "testGetSummaryDayTradeSwapContractCase1()";
        log.entering(STR_METHOD_NAME);
  
        try
        {
            WEB3TPContractInfoForTest0 l_info = new WEB3TPContractInfoForTest0();
            WEB3TPTargetContractDetail targetContractDetail = new WEB3TPTargetContractDetail();
            targetContractDetail.setContractId(1111);
            WEB3TPTargetContract l_targetContract1 = new WEB3TPTargetContract();
            WEB3TPTargetContract l_targetContract2 = new WEB3TPTargetContract();
            WEB3TPTargetContract l_targetContract3 = new WEB3TPTargetContract();
            
            List l_targetContracts = new ArrayList();
            l_targetContracts.add(l_targetContract1);
            l_targetContracts.add(l_targetContract2);
            l_targetContracts.add(l_targetContract3);
            
            //私有屬性的反射
            Field field = WEB3TPContractInfo.class.getDeclaredField("targetContracts");
            field.setAccessible(true);
            field.set(l_info, l_targetContracts);
            
            Date l_dat = WEB3DateUtility.getDate("20070701","yyyyMMdd");
            
            WEB3TPSummaryDayTradeSwapContract l_sumDayTradeSwapContract =
                l_info.getSummaryDayTradeSwapContract(l_dat);
            assertEquals(l_sumDayTradeSwapContract.getSwapContractSettleLoss()+"", "44.0");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //1條數據 
    public void testGetSummaryDayTradeSwapContractCase2()
    {
        final String STR_METHOD_NAME = "testGetSummaryDayTradeSwapContractCase2()";
        log.entering(STR_METHOD_NAME);
  
        try
        {
            WEB3TPContractInfoForTest2 l_info = new WEB3TPContractInfoForTest2();
            WEB3TPTargetContractDetail targetContractDetail = new WEB3TPTargetContractDetail();
            targetContractDetail.setContractId(1111);
            WEB3TPTargetContract l_targetContract1 = new WEB3TPTargetContract();
            WEB3TPTargetContract l_targetContract2 = new WEB3TPTargetContract();
            WEB3TPTargetContract l_targetContract3 = new WEB3TPTargetContract();
            
            List l_targetContracts = new ArrayList();
            l_targetContracts.add(l_targetContract1);
            l_targetContracts.add(l_targetContract2);
            l_targetContracts.add(l_targetContract3);
            
            //私有屬性的反射
            Field field = WEB3TPContractInfo.class.getDeclaredField("targetContracts");
            field.setAccessible(true);
            field.set(l_info, l_targetContracts);
            
            Date l_dat = WEB3DateUtility.getDate("20070701","yyyyMMdd");
            
            WEB3TPSummaryDayTradeSwapContract l_sumDayTradeSwapContract =
                l_info.getSummaryDayTradeSwapContract(l_dat);
            assertEquals(l_sumDayTradeSwapContract.getSwapContractSettleLoss()+"", "11.0");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    //1條數據 且?為0
    public void testGetSummaryDayTradeSwapContractCase3()
    {
        final String STR_METHOD_NAME = "testGetSummaryDayTradeSwapContractCase3()";
        log.entering(STR_METHOD_NAME);
  
        try
        {
            WEB3TPContractInfoForTest3 l_info = new WEB3TPContractInfoForTest3();
            WEB3TPTargetContractDetail targetContractDetail = new WEB3TPTargetContractDetail();
            targetContractDetail.setContractId(1111);
            WEB3TPTargetContract l_targetContract1 = new WEB3TPTargetContract();
            WEB3TPTargetContract l_targetContract2 = new WEB3TPTargetContract();
            WEB3TPTargetContract l_targetContract3 = new WEB3TPTargetContract();
            
            List l_targetContracts = new ArrayList();
            l_targetContracts.add(l_targetContract1);
            l_targetContracts.add(l_targetContract2);
            l_targetContracts.add(l_targetContract3);
            
            //私有屬性的反射
            Field field = WEB3TPContractInfo.class.getDeclaredField("targetContracts");
            field.setAccessible(true);
            field.set(l_info, l_targetContracts);
            
            Date l_dat = WEB3DateUtility.getDate("20070701","yyyyMMdd");
            
            WEB3TPSummaryDayTradeSwapContract l_sumDayTradeSwapContract =
                l_info.getSummaryDayTradeSwapContract(l_dat);
            assertEquals(l_sumDayTradeSwapContract.getSwapContractSettleLoss()+"", "0.0");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //get銘柄ID（）== 引数.銘柄IDの場合
    public void testCalcProductMarginDeposit_0001()
    {
        final String STR_METHOD_NAME = "testCalcProductMarginDeposit_0001()";
        log.entering(STR_METHOD_NAME);       
        try
        {
            WEB3TPContractInfoForTest4 l_contractinfo = new WEB3TPContractInfoForTest4();
            Class c = Class.forName(WEB3TPContractInfo.class.getName());
            
            WEB3TPTargetContract l_targetContract1 = new WEB3TPTargetContract();
            WEB3TPTargetContractDetail l_targetContractDetail1 = new WEB3TPTargetContractDetail();
            l_targetContractDetail1.setProductId(1111);
            l_targetContract1.setTargetContractDetail(l_targetContractDetail1);
            
            WEB3TPTargetContract l_targetContract2 = new WEB3TPTargetContract();
            WEB3TPTargetContractDetail l_targetContractDetail2 = new WEB3TPTargetContractDetail();
            l_targetContractDetail2.setProductId(2222);
            l_targetContract2.setTargetContractDetail(l_targetContractDetail2);
            
            WEB3TPTargetContract l_targetContract3 = new WEB3TPTargetContract();
            WEB3TPTargetContractDetail l_targetContractDetail3 = new WEB3TPTargetContractDetail();
            l_targetContractDetail3.setProductId(3333);
            l_targetContract3.setTargetContractDetail(l_targetContractDetail3);  
            
            //私有屬性的反射
            List l_targetContracts = new ArrayList();
            l_targetContracts.add(l_targetContract1);
            l_targetContracts.add(l_targetContract2);
            l_targetContracts.add(l_targetContract3);
            
            Field l_field = c.getDeclaredField("targetContracts");
            l_field.setAccessible(true);
            l_field.set(l_contractinfo, l_targetContracts);
            
            Date l_dat = WEB3DateUtility.getDate("20070701","yyyyMMdd");
            double l_dbProductMarginDeposit = l_contractinfo.calcProductMarginDeposit(l_dat,2222);
            assertEquals(33.666, l_dbProductMarginDeposit, 12);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    //get銘柄ID（）!= 引数.銘柄IDの場合
    public void testCalcProductMarginDeposit_0002()
    {
        final String STR_METHOD_NAME = "testCalcProductMarginDeposit_0002()";
        log.entering(STR_METHOD_NAME);       
        try
        {
            WEB3TPContractInfoForTest4 l_contractinfo = new WEB3TPContractInfoForTest4();
            Class c = Class.forName(WEB3TPContractInfo.class.getName());
            
            WEB3TPTargetContract l_targetContract1 = new WEB3TPTargetContract();
            WEB3TPTargetContractDetail l_targetContractDetail1 = new WEB3TPTargetContractDetail();
            l_targetContractDetail1.setProductId(1111);
            l_targetContract1.setTargetContractDetail(l_targetContractDetail1);
            
            WEB3TPTargetContract l_targetContract2 = new WEB3TPTargetContract();
            WEB3TPTargetContractDetail l_targetContractDetail2 = new WEB3TPTargetContractDetail();
            l_targetContractDetail2.setProductId(2222);
            l_targetContract2.setTargetContractDetail(l_targetContractDetail2);
            
            WEB3TPTargetContract l_targetContract3 = new WEB3TPTargetContract();
            WEB3TPTargetContractDetail l_targetContractDetail3 = new WEB3TPTargetContractDetail();
            l_targetContractDetail3.setProductId(3333);
            l_targetContract3.setTargetContractDetail(l_targetContractDetail3);  
            
            //私有屬性的反射
            List l_targetContracts = new ArrayList();
            l_targetContracts.add(l_targetContract1);
            l_targetContracts.add(l_targetContract2);
            l_targetContracts.add(l_targetContract3);
            
            Field l_field = c.getDeclaredField("targetContracts");
            l_field.setAccessible(true);
            l_field.set(l_contractinfo, l_targetContracts);
            
            Date l_dat = WEB3DateUtility.getDate("20070701","yyyyMMdd");
            double l_dbProductMarginDeposit = l_contractinfo.calcProductMarginDeposit(l_dat,4444);
            assertEquals(0, l_dbProductMarginDeposit, 12);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    private class WEB3TPContractInfoForTest0 extends WEB3TPContractInfo
    {
        public List getTargetContracts()
        {
            WEB3TPTargetContractDetail targetContractDetail1 = new WEB3TPTargetContractDetail();
            targetContractDetail1.setOpenDate(WEB3DateUtility.getDate("20070801", "yyyyMMdd"));
            
            WEB3TPTargetContractDetail targetContractDetail2 = new WEB3TPTargetContractDetail();
            
            WEB3TPTargetContractDetail targetContractDetail3 = new WEB3TPTargetContractDetail();
            targetContractDetail3.setContractPrice(100);
            WEB3TPTargetContractDetail targetContractDetail4 = new WEB3TPTargetContractDetail();
            targetContractDetail4.setContractPrice(200);

            WEB3TPTargetContract l_targetContract0 = new WEB3TPTargetContract();
            WEB3TPTargetContract l_targetContract1 = new WEB3TPTargetContract();
            WEB3TPTargetContract l_targetContract2 = new WEB3TPTargetContract();
            WEB3TPTargetContract l_targetContract3 = new WEB3TPTargetContract();

            l_targetContract0.setTargetContractDetail(targetContractDetail1);
            l_targetContract1.setTargetContractDetail(targetContractDetail2);
            l_targetContract2.setTargetContractDetail(targetContractDetail3);
            l_targetContract3.setTargetContractDetail(targetContractDetail4);

            List l_lis = new ArrayList();
            l_lis.add(l_targetContract0);
            l_lis.add(l_targetContract1);
            l_lis.add(l_targetContract2);
            l_lis.add(l_targetContract3);
            return l_lis;
        }
        
        public WEB3TPHistoryPerContract getHistoryPerContract(WEB3TPTargetContract l_targetContract)
        {
            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Date l_dat = WEB3DateUtility.getDate("20070701","yyyyMMdd");
            
            Date[] l_dats = new Date[2];
            l_dats[0] = l_dat;
            l_dats[1] = l_dat;
            l_calcCondition.setBizDate(l_dats);
            
            WEB3TPContractInfo l_contractInfo = new WEB3TPContractInfo();
            l_contractInfo.setCalcCondition(l_calcCondition);
            
            WEB3TPHistoryPerContractForTest l_historyPerContract = new WEB3TPHistoryPerContractForTest();
            WEB3TPHistory l_history = new WEB3TPHistory();
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_CLOSE_MARGIN);
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_SWAP_MARGIN);
            l_history.setTransactionDate(WEB3DateUtility.getDate("20070701","yyyyMMdd"));
            l_history.setQuantity(10.5);
            l_historyPerContract.addHistory(l_history);
            
            l_historyPerContract.setContractInfo(l_contractInfo);
            
            return l_historyPerContract;
        }
    }
    
    private class WEB3TPContractInfoForTest2 extends WEB3TPContractInfo
    {
        public List getTargetContracts()
        {
            WEB3TPTargetContractDetail targetContractDetail1 = new WEB3TPTargetContractDetail();
            targetContractDetail1.setOpenDate(WEB3DateUtility.getDate("20070801", "yyyyMMdd"));

            WEB3TPTargetContract l_targetContract0 = new WEB3TPTargetContract();

            l_targetContract0.setTargetContractDetail(targetContractDetail1);

            List l_lis = new ArrayList();
            l_lis.add(l_targetContract0);
            return l_lis;
        }
        
        public WEB3TPHistoryPerContract getHistoryPerContract(WEB3TPTargetContract l_targetContract)
        {
            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Date l_dat = WEB3DateUtility.getDate("20070701","yyyyMMdd");
            
            Date[] l_dats = new Date[2];
            l_dats[0] = l_dat;
            l_dats[1] = l_dat;
            l_calcCondition.setBizDate(l_dats);
            
            WEB3TPContractInfo l_contractInfo = new WEB3TPContractInfo();
            l_contractInfo.setCalcCondition(l_calcCondition);
            
            WEB3TPHistoryPerContractForTest l_historyPerContract = new WEB3TPHistoryPerContractForTest();
            WEB3TPHistory l_history = new WEB3TPHistory();
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_CLOSE_MARGIN);
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_SWAP_MARGIN);
            l_history.setTransactionDate(WEB3DateUtility.getDate("20070701","yyyyMMdd"));
            l_history.setQuantity(10.5);
            l_historyPerContract.addHistory(l_history);
            
            l_historyPerContract.setContractInfo(l_contractInfo);
            
            return l_historyPerContract;
        }
    }
    
    private class WEB3TPContractInfoForTest3 extends WEB3TPContractInfo
    {
        public List getTargetContracts()
        {
            WEB3TPTargetContractDetail targetContractDetail1 = new WEB3TPTargetContractDetail();
            targetContractDetail1.setOpenDate(WEB3DateUtility.getDate("20070801", "yyyyMMdd"));

            WEB3TPTargetContract l_targetContract0 = new WEB3TPTargetContract();

            l_targetContract0.setTargetContractDetail(targetContractDetail1);

            List l_lis = new ArrayList();
            l_lis.add(l_targetContract0);
            return l_lis;
        }
        
        public WEB3TPHistoryPerContract getHistoryPerContract(WEB3TPTargetContract l_targetContract)
        {
            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Date l_dat = WEB3DateUtility.getDate("20070701","yyyyMMdd");
            
            Date[] l_dats = new Date[2];
            l_dats[0] = l_dat;
            l_dats[1] = l_dat;
            l_calcCondition.setBizDate(l_dats);
            
            WEB3TPContractInfo l_contractInfo = new WEB3TPContractInfo();
            l_contractInfo.setCalcCondition(l_calcCondition);
            
            WEB3TPHistoryPerContractForTest1 l_historyPerContract = new WEB3TPHistoryPerContractForTest1();
            WEB3TPHistory l_history = new WEB3TPHistory();
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_CLOSE_MARGIN);
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_SWAP_MARGIN);
            l_history.setTransactionDate(WEB3DateUtility.getDate("20070701","yyyyMMdd"));
            l_history.setQuantity(10.5);
            l_historyPerContract.addHistory(l_history);
            
            l_historyPerContract.setContractInfo(l_contractInfo);
            
            return l_historyPerContract;
        }
    }
    
    private class WEB3TPHistoryPerContractForTest extends WEB3TPHistoryPerContract
    {
        //日計り返済・現引現渡建玉
      public WEB3TPSummaryDayTradeSwapContract getSummaryDayTradeSwapContract(Date l_datDate) 
      {
          WEB3TPSummaryDayTradeSwapContract l_WEB3TPSummaryDayTradeSwapContract =
              new WEB3TPSummaryDayTradeSwapContract();
          
          //現引現渡建玉決済損
          l_WEB3TPSummaryDayTradeSwapContract.setSwapContractSettleLoss(11.0);
          return l_WEB3TPSummaryDayTradeSwapContract;
      }
      
      public WEB3TPSummaryUndeliveredContract getSummaryUndeliveredContract(Date l_datDate) 
      {
    	  WEB3TPSummaryUndeliveredContract l_sumUndeliveredContract =new WEB3TPSummaryUndeliveredContract();
    	  l_sumUndeliveredContract.setContractTotalCost(100);
    	  return l_sumUndeliveredContract;
      }
    }
    
    private class WEB3TPHistoryPerContractForTest1 extends WEB3TPHistoryPerContract
    {
        //日計り返済・現引現渡建玉
      public WEB3TPSummaryDayTradeSwapContract getSummaryDayTradeSwapContract(Date l_datDate) 
      {
          WEB3TPSummaryDayTradeSwapContract l_WEB3TPSummaryDayTradeSwapContract =
              new WEB3TPSummaryDayTradeSwapContract();
          
          //現引現渡建玉決済損
          l_WEB3TPSummaryDayTradeSwapContract.setSwapContractSettleLoss(0.0);
          return l_WEB3TPSummaryDayTradeSwapContract;
      }
    }
    
    private class WEB3TPContractInfoForTest4 extends WEB3TPContractInfo
    {
        public WEB3TPHistoryPerContract getHistoryPerContract(WEB3TPTargetContract l_targetContract)
        {
            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Date l_dat = WEB3DateUtility.getDate("20070701","yyyyMMdd");
            
            Date[] l_dats = new Date[2];
            l_dats[0] = l_dat;
            l_dats[1] = l_dat;
            l_calcCondition.setBizDate(l_dats);
            
            WEB3TPContractInfo l_contractInfo = new WEB3TPContractInfo();
            l_contractInfo.setCalcCondition(l_calcCondition);
            
            WEB3TPHistoryPerContractForTest2 l_historyPerContract = new WEB3TPHistoryPerContractForTest2();
            WEB3TPHistory l_history = new WEB3TPHistory();
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_CLOSE_MARGIN);
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_SWAP_MARGIN);
            l_history.setTransactionDate(WEB3DateUtility.getDate("20070701","yyyyMMdd"));
            l_history.setQuantity(10.5);
            l_historyPerContract.addHistory(l_history);
            
            l_historyPerContract.setContractInfo(l_contractInfo);
            
            return l_historyPerContract;
        }
    }
    private class WEB3TPHistoryPerContractForTest2 extends WEB3TPHistoryPerContract
    {
         //日計り返済・現引現渡建玉
         public WEB3TPSummaryDayTradeSwapContract getSummaryDayTradeSwapContract(Date l_datDate) 
         {
          WEB3TPSummaryDayTradeSwapContract l_WEB3TPSummaryDayTradeSwapContract =
              new WEB3TPSummaryDayTradeSwapContract();
         //必要保証金
          l_WEB3TPSummaryDayTradeSwapContract.setMarginDeposit(11.111);
          
          return l_WEB3TPSummaryDayTradeSwapContract;
         }

         public WEB3TPSummaryOpenContract getSummaryOpenContract(Date l_datDate)
         {
         //未決済建玉の集計()
         WEB3TPSummaryOpenContract l_WEB3TPSummaryOpenContract =
            new WEB3TPSummaryOpenContract();
         //必要保証金
         l_WEB3TPSummaryOpenContract.setMarginDeposit (10.222);
         //発注分必要保証金
         l_WEB3TPSummaryOpenContract.setUnExecMarginDeposit(12.333);
         
         return l_WEB3TPSummaryOpenContract;
         }
    }
}
@

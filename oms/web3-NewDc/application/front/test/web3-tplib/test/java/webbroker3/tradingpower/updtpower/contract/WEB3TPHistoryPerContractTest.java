head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.40.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPHistoryPerContractTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 張騰宇 (中訊) 新規作成 仕様変更モデル
*/
package webbroker3.tradingpower.updtpower.contract;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;

import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPHistoryPerContractTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3TPHistoryPerContractTest.class);
    
    public String strMothod = null;

    public WEB3TPHistoryPerContractTest(String arg0)
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

    /*
     * Test method for 'webbroker3.tradingpower.updtpower.contract.WEB3TPHistoryPerContract.getSummaryTodayRepayContractQuantity()'
     */
//    public void testGetSummaryTodayRepayContractQuantityCase1()
//    {
//        final String STR_METHOD_NAME = "testGetSummaryTodayRepayContractQuantityCase1()";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContract();
//            WEB3TPCalcCondition l_condition = new WEB3TPCalcCondition();
//            Date l_dat = WEB3DateUtility.getDate("20070101","yyyyMMdd");
//            
//            Date[] l_dats = new Date[2];
//            l_dats[0] = l_dat;
//            l_dats[1] = l_dat;
//            l_condition.setBizDate(l_dats);
//            
//            WEB3TPContractInfo l_contractInfo = new WEB3TPContractInfo();
//            l_contractInfo.setCalcCondition(l_condition);
//            
//            l_historyPerContract.setContractInfo(l_contractInfo);
//            
//            double l_dblSummaryTodayRepayContractQuantity = l_historyPerContract.getSummaryTodayRepayContractQuantity();
//            assertEquals(l_dblSummaryTodayRepayContractQuantity + "", "0.0");
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    public void testGetSummaryTodayRepayContractQuantityCase2()
//    {
//        final String STR_METHOD_NAME = "testGetSummaryTodayRepayContractQuantityCase2()";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            WEB3TPHistoryPerContractForTest l_historyPerContract = new WEB3TPHistoryPerContractForTest();
//            WEB3TPCalcCondition l_condition = new WEB3TPCalcCondition();
//            Date l_dat = WEB3DateUtility.getDate("20070101","yyyyMMdd");
//            
//            Date[] l_dats = new Date[2];
//            l_dats[0] = l_dat;
//            l_dats[1] = l_dat;
//            l_condition.setBizDate(l_dats);
//            
//            WEB3TPContractInfo l_contractInfo = new WEB3TPContractInfo();
//            l_contractInfo.setCalcCondition(l_condition);
//            
//            l_historyPerContract.setContractInfo(l_contractInfo);
//            
//            double l_dblSummaryTodayRepayContractQuantity = l_historyPerContract.getSummaryTodayRepayContractQuantity();
//            assertEquals(l_dblSummaryTodayRepayContractQuantity + "", "201.05");
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }

    //(get未決済建玉の集計)
    public void testGetSummaryOpenContract_0001()
    {
        final String STR_METHOD_NAME = "testGetSummaryOpenContract_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        strMothod = STR_METHOD_NAME;
        
        try
        {
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContractForTest2();
            
            
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_historyPerContract.setTargetContract(l_targetContract);
            
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            
            Date l_datOpen = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
            l_targetContractDetail.setOpenDate(l_datOpen);

            
            WEB3TPContractInfo l_contractInfo = new WEB3TPContractInfo();
            l_historyPerContract.setContractInfo(l_contractInfo);
            
            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcConditionForTest();
            l_contractInfo.setCalcCondition(l_calcCondition);

            Date l_datDate_2 = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
            Date l_datDate_1 = WEB3DateUtility.getDate("20080809", "yyyyMMdd");
            Date l_datDate0 = WEB3DateUtility.getDate("20080810", "yyyyMMdd");
            Date l_datDate1 = WEB3DateUtility.getDate("20080811", "yyyyMMdd");
            Date l_datDate2 = WEB3DateUtility.getDate("20080812", "yyyyMMdd");
            Date l_datDate3 = WEB3DateUtility.getDate("20080813", "yyyyMMdd");
            Date l_datDate4 = WEB3DateUtility.getDate("20080814", "yyyyMMdd");
            Date l_datDate5 = WEB3DateUtility.getDate("20080815", "yyyyMMdd");
            Date[] l_datBizDate = new Date[]{l_datDate_2, l_datDate_1, l_datDate0, l_datDate1,
                l_datDate2, l_datDate3, l_datDate4, l_datDate5};
            l_calcCondition.setBizDate(l_datBizDate);
            
            Date l_datDate = WEB3DateUtility.getDate("20080809", "yyyyMMdd");
            l_historyPerContract.getSummaryOpenContract(l_datDate);

        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get未決済建玉の集計)
    public void testGetSummaryOpenContract_0002()
    {
        final String STR_METHOD_NAME = "testGetSummaryOpenContract_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        strMothod = STR_METHOD_NAME;
        
        try
        {
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContractForTest2();
            
            
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_historyPerContract.setTargetContract(l_targetContract);
            
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            
            Date l_datOpen = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
            l_targetContractDetail.setOpenDate(l_datOpen);

            
            WEB3TPContractInfo l_contractInfo = new WEB3TPContractInfo();
            l_historyPerContract.setContractInfo(l_contractInfo);
            
            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcConditionForTest();
            l_contractInfo.setCalcCondition(l_calcCondition);

            Date l_datDate_2 = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
            Date l_datDate_1 = WEB3DateUtility.getDate("20080809", "yyyyMMdd");
            Date l_datDate0 = WEB3DateUtility.getDate("20080810", "yyyyMMdd");
            Date l_datDate1 = WEB3DateUtility.getDate("20080811", "yyyyMMdd");
            Date l_datDate2 = WEB3DateUtility.getDate("20080812", "yyyyMMdd");
            Date l_datDate3 = WEB3DateUtility.getDate("20080813", "yyyyMMdd");
            Date l_datDate4 = WEB3DateUtility.getDate("20080814", "yyyyMMdd");
            Date l_datDate5 = WEB3DateUtility.getDate("20080815", "yyyyMMdd");
            Date[] l_datBizDate = new Date[]{l_datDate_2, l_datDate_1, l_datDate0, l_datDate1,
                l_datDate2, l_datDate3, l_datDate4, l_datDate5};
            l_calcCondition.setBizDate(l_datBizDate);
            
            Date l_datDate = WEB3DateUtility.getDate("20080810", "yyyyMMdd");
            l_historyPerContract.getSummaryOpenContract(l_datDate);

        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get未決済建玉の集計)
    public void testGetSummaryOpenContract_0003()
    {
        final String STR_METHOD_NAME = "testGetSummaryOpenContract_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        strMothod = STR_METHOD_NAME;
        
        try
        {
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContractForTest2();
            
            
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_historyPerContract.setTargetContract(l_targetContract);
            
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            
            Date l_datOpen = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
            l_targetContractDetail.setOpenDate(l_datOpen);

            
            WEB3TPContractInfo l_contractInfo = new WEB3TPContractInfo();
            l_historyPerContract.setContractInfo(l_contractInfo);
            
            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcConditionForTest();
            l_contractInfo.setCalcCondition(l_calcCondition);

            Date l_datDate_2 = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
            Date l_datDate_1 = WEB3DateUtility.getDate("20080809", "yyyyMMdd");
            Date l_datDate0 = WEB3DateUtility.getDate("20080810", "yyyyMMdd");
            Date l_datDate1 = WEB3DateUtility.getDate("20080811", "yyyyMMdd");
            Date l_datDate2 = WEB3DateUtility.getDate("20080812", "yyyyMMdd");
            Date l_datDate3 = WEB3DateUtility.getDate("20080813", "yyyyMMdd");
            Date l_datDate4 = WEB3DateUtility.getDate("20080814", "yyyyMMdd");
            Date l_datDate5 = WEB3DateUtility.getDate("20080815", "yyyyMMdd");
            Date[] l_datBizDate = new Date[]{l_datDate_2, l_datDate_1, l_datDate0, l_datDate1,
                l_datDate2, l_datDate3, l_datDate4, l_datDate5};
            l_calcCondition.setBizDate(l_datBizDate);
            
            Date l_datDate = WEB3DateUtility.getDate("20080810", "yyyyMMdd");
            l_historyPerContract.getSummaryOpenContract(l_datDate);

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
     * get日計り返済・現引現渡建玉の集計
     * 正常終了
     */
    public void testGetSummaryDayTradeSwapContract_0001()
    {
        final String STR_METHOD_NAME = "testGetSummaryDayTradeSwapContract_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContractForTest3();
            l_historyPerContract.setTargetContract(l_targetContract);
            
            Date l_datDate = WEB3DateUtility.getDate("20080201", "yyyyMMdd");
            WEB3TPSummaryDayTradeSwapContract l_sumDayTradeSwapContract =
                l_historyPerContract.getSummaryDayTradeSwapContract(l_datDate);
            //建玉代金 = 建玉代金 + 建玉変動.calc建玉代金() =9999.0
            assertEquals("9999.0", "" + l_sumDayTradeSwapContract.getContractAmount());
            
            //建手数料
            assertEquals("70.0", "" + l_sumDayTradeSwapContract.getSetupFee());
            //その他建玉諸経費
            assertEquals("290.0", "" + l_sumDayTradeSwapContract.getContractOtherCost());
            //日歩・逆日歩損
            assertEquals("90.0", "" + l_sumDayTradeSwapContract.getContractInterestLoss());
            //日歩・逆日歩益
            assertEquals("1000.0", "" + l_sumDayTradeSwapContract.getContractInterestProfit());
            
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetSummaryDayTradeSwapContract_00011()
    {
        final String STR_METHOD_NAME = "testGetSummaryDayTradeSwapContract_00011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContractForTest33();
            l_historyPerContract.setTargetContract(l_targetContract);
            
            Date l_datDate = WEB3DateUtility.getDate("20080201", "yyyyMMdd");
            WEB3TPSummaryDayTradeSwapContract l_sumDayTradeSwapContract =
                l_historyPerContract.getSummaryDayTradeSwapContract(l_datDate);
            //建玉代金 = 建玉代金 + 建玉変動.calc建玉代金() =9999.0
            assertEquals("9999.0", "" + l_sumDayTradeSwapContract.getContractAmount());
            //建手数料
            assertEquals("70.0", "" + l_sumDayTradeSwapContract.getSetupFee());
            //その他建玉諸経費
            assertEquals("290.0", "" + l_sumDayTradeSwapContract.getContractOtherCost());
            //日歩・逆日歩損
            assertEquals("1050.0", "" + l_sumDayTradeSwapContract.getContractInterestLoss());
            //日歩・逆日歩益
            assertEquals("90.0", "" + l_sumDayTradeSwapContract.getContractInterestProfit());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetSummaryDayTradeSwapContract_0002()
    {
        final String STR_METHOD_NAME = "testGetSummaryDayTradeSwapContract_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContractForTest5();
            l_historyPerContract.setTargetContract(l_targetContract);
            
            Date l_datDate = WEB3DateUtility.getDate("20080201", "yyyyMMdd");
            WEB3TPSummaryDayTradeSwapContract l_sumDayTradeSwapContract =
                l_historyPerContract.getSummaryDayTradeSwapContract(l_datDate);
            //建玉代金 = 建玉代金 + 建玉変動.calc建玉代金() =0.0
            assertEquals("0.0", "" + l_sumDayTradeSwapContract.getContractAmount());
            //建玉諸経費 = 建玉諸経費 + 建玉変動.get建玉諸経費() = 0.0
//            assertEquals("0.0", "" + l_sumDayTradeSwapContract.getSwapContractSettleLoss());
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
     * get未受渡建玉の集計
     * 正常終了
     * 非日計り返済・現引現渡の場合
     */
    public void testGetSummaryUndeliveredContract_0001()
    {
        final String STR_METHOD_NAME = "testGetSummaryDayTradeSwapContract_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setMarginDepositRate(10);
            l_targetContractDetail.setCashMarginDepositRate(20);
            l_targetContractDetail.setContractType(ContractTypeEnum.LONG);
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            
            
//            WEB3TPHistory l_history = new WEB3TPHistory();
//            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_CLOSE_MARGIN);
//            l_history.setTargetContract(l_targetContract);
//            l_history.setContractTotalCost(100);//建玉変動.建玉諸経費
//            l_history.setImportedPayInterestFee(90);//建玉変動.逆日歩
//            l_history.setNetAmount(10);//建玉変動.受渡代金
//            
            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Date[] l_datBizDates = new Date[8];
            for (int i = 1; i <= 8; i++)
            {
                l_datBizDates[i - 1] = WEB3DateUtility.getDate("2008020" + i, "yyyyMMdd");
            }
            l_calcCondition.setBizDate(l_datBizDates);
            WEB3TPContractInfo l_contractInfo = new WEB3TPContractInfo();
            l_contractInfo.setCalcCondition(l_calcCondition);
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContractForTest1();
            l_historyPerContract.setTargetContract(l_targetContract);
            l_historyPerContract.setContractInfo(l_contractInfo);
//            l_historyPerContract.addHistory(l_history);
            
            Date l_datDate = WEB3DateUtility.getDate("20080202", "yyyyMMdd");
            WEB3TPSummaryUndeliveredContract l_summaryUndeliveredContract =
                l_historyPerContract.getSummaryUndeliveredContract(l_datDate);
            
            //建玉代金＝建玉代金＋建玉変動.建玉代金 = 9999
            assertEquals("9999.0", "" + l_summaryUndeliveredContract.getContractAmount());
            //必要保証金＝必要保証金＋建玉変動.必要保証金 = 999.9
            assertEquals("999.0", "" + l_summaryUndeliveredContract.getMarginDeposit());
            //現金必要保証金＝現金必要保証金＋建玉変動.現金必要保証金 =1999.8
            assertEquals("1999.0", "" + l_summaryUndeliveredContract.getCashMarginDeposit());
            //決済益＝決済益＋　@建玉変動.決済益 = 360.0
            assertEquals("360.0", "" + l_summaryUndeliveredContract.getContractProfit());
            //決済損＝決済損＋　@建玉変動.決済損
            assertEquals("0.0", "" + l_summaryUndeliveredContract.getContractLoss());
            //建玉諸経費　@　@＝建玉諸経費 ＋　@建玉変動.建玉諸経費
//            assertEquals("100.0", "" + l_summaryUndeliveredContract.getContractTotalCost());
            
            
            
            
            //建手数料
            assertEquals("70.0", "" + l_summaryUndeliveredContract.getSetupFee());
            //その他建玉諸経費
            assertEquals("290.0", "" + l_summaryUndeliveredContract.getContractOtherCost());
            //日歩・逆日歩損
            assertEquals("90.0", "" + l_summaryUndeliveredContract.getContractInterestLoss());
            //日歩・逆日歩益
            assertEquals("100.0", "" + l_summaryUndeliveredContract.getContractInterestProfit());
            
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetSummaryUndeliveredContract_00011()
    {
        final String STR_METHOD_NAME = "testGetSummaryDayTradeSwapContract_00011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setMarginDepositRate(10);
            l_targetContractDetail.setCashMarginDepositRate(20);
            l_targetContractDetail.setContractType(ContractTypeEnum.SHORT);
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            
            
//            WEB3TPHistory l_history = new WEB3TPHistory();
//            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_CLOSE_MARGIN);
//            l_history.setTargetContract(l_targetContract);
//            l_history.setContractTotalCost(100);//建玉変動.建玉諸経費
//            l_history.setImportedPayInterestFee(90);//建玉変動.逆日歩
//            l_history.setNetAmount(10);//建玉変動.受渡代金
//            
            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Date[] l_datBizDates = new Date[8];
            for (int i = 1; i <= 8; i++)
            {
                l_datBizDates[i - 1] = WEB3DateUtility.getDate("2008020" + i, "yyyyMMdd");
            }
            l_calcCondition.setBizDate(l_datBizDates);
            WEB3TPContractInfo l_contractInfo = new WEB3TPContractInfo();
            l_contractInfo.setCalcCondition(l_calcCondition);
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContractForTest1a();
            l_historyPerContract.setTargetContract(l_targetContract);
            l_historyPerContract.setContractInfo(l_contractInfo);
//            l_historyPerContract.addHistory(l_history);
            
            Date l_datDate = WEB3DateUtility.getDate("20080202", "yyyyMMdd");
            WEB3TPSummaryUndeliveredContract l_summaryUndeliveredContract =
                l_historyPerContract.getSummaryUndeliveredContract(l_datDate);
            
            //建玉代金＝建玉代金＋建玉変動.建玉代金 = 9999
            assertEquals("9999.0", "" + l_summaryUndeliveredContract.getContractAmount());
            //必要保証金＝必要保証金＋建玉変動.必要保証金 = 999.9
            assertEquals("999.0", "" + l_summaryUndeliveredContract.getMarginDeposit());
            //現金必要保証金＝現金必要保証金＋建玉変動.現金必要保証金 =1999.8
            assertEquals("1999.0", "" + l_summaryUndeliveredContract.getCashMarginDeposit());
            //決済益＝決済益＋　@建玉変動.決済益 = 32.0
            assertEquals("430.0", "" + l_summaryUndeliveredContract.getContractProfit());
            //決済損＝決済損＋　@建玉変動.決済損
            assertEquals("0.0", "" + l_summaryUndeliveredContract.getContractLoss());
            //建玉諸経費　@　@＝建玉諸経費 ＋　@建玉変動.建玉諸経費
//            assertEquals("100.0", "" + l_summaryUndeliveredContract.getContractTotalCost());
            
            
            
            
            //建手数料
            assertEquals("70.0", "" + l_summaryUndeliveredContract.getSetupFee());
            //その他建玉諸経費
            assertEquals("290.0", "" + l_summaryUndeliveredContract.getContractOtherCost());
            //日歩・逆日歩損
            assertEquals("150.0", "" + l_summaryUndeliveredContract.getContractInterestLoss());
            //日歩・逆日歩益
            assertEquals("90.0", "" + l_summaryUndeliveredContract.getContractInterestProfit());
            
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
     * get未受渡建玉の集計
     * 正常終了
     * 非日計り返済・現引現渡の場合
     */
    public void testGetSummaryUndeliveredContract_0003()
    {
        final String STR_METHOD_NAME = "testGetSummaryDayTradeSwapContract_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setMarginDepositRate(10);
            l_targetContractDetail.setCashMarginDepositRate(20);
            l_targetContractDetail.setContractType(ContractTypeEnum.LONG);
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);

            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Date[] l_datBizDates = new Date[8];
            for (int i = 1; i <= 8; i++)
            {
                l_datBizDates[i - 1] = WEB3DateUtility.getDate("2008020" + i, "yyyyMMdd");
            }
            l_calcCondition.setBizDate(l_datBizDates);
            WEB3TPContractInfo l_contractInfo = new WEB3TPContractInfo();
            l_contractInfo.setCalcCondition(l_calcCondition);
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContractForTest11();
            l_historyPerContract.setTargetContract(l_targetContract);
            l_historyPerContract.setContractInfo(l_contractInfo);
//            l_historyPerContract.addHistory(l_history);
            
            Date l_datDate = WEB3DateUtility.getDate("20080202", "yyyyMMdd");
            WEB3TPSummaryUndeliveredContract l_summaryUndeliveredContract =
                l_historyPerContract.getSummaryUndeliveredContract(l_datDate);
            
            //建玉代金＝建玉代金＋建玉変動.建玉代金 = 9999
            assertEquals("0.0", "" + l_summaryUndeliveredContract.getContractAmount());
            //必要保証金＝必要保証金＋建玉変動.必要保証金 = 999.9
            assertEquals("0.0", "" + l_summaryUndeliveredContract.getMarginDeposit());
            //現金必要保証金＝現金必要保証金＋建玉変動.現金必要保証金 =1999.8
            assertEquals("0.0", "" + l_summaryUndeliveredContract.getCashMarginDeposit());
            //決済益＝決済益＋　@建玉変動.決済益 = 32.0
            assertEquals("360.0", "" + l_summaryUndeliveredContract.getContractProfit());
            //決済損＝決済損＋　@建玉変動.決済損
            assertEquals("0.0", "" + l_summaryUndeliveredContract.getContractLoss());
            //建玉諸経費　@　@＝建玉諸経費 ＋　@建玉変動.建玉諸経費
//            assertEquals("100.0", "" + l_summaryUndeliveredContract.getContractTotalCost());
            
            //建手数料
            assertEquals("70.0", "" + l_summaryUndeliveredContract.getSetupFee());
            //その他建玉諸経費
            assertEquals("290.0", "" + l_summaryUndeliveredContract.getContractOtherCost());
            //日歩・逆日歩損
            assertEquals("90.0", "" + l_summaryUndeliveredContract.getContractInterestLoss());
            //日歩・逆日歩益
            assertEquals("100.0", "" + l_summaryUndeliveredContract.getContractInterestProfit());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetSummaryUndeliveredContract_00033()
    {
        final String STR_METHOD_NAME = "testGetSummaryDayTradeSwapContract_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setMarginDepositRate(10);
            l_targetContractDetail.setCashMarginDepositRate(20);
            l_targetContractDetail.setContractType(ContractTypeEnum.LONG);
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);

            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Date[] l_datBizDates = new Date[8];
            for (int i = 1; i <= 8; i++)
            {
                l_datBizDates[i - 1] = WEB3DateUtility.getDate("2008020" + i, "yyyyMMdd");
            }
            l_calcCondition.setBizDate(l_datBizDates);
            WEB3TPContractInfo l_contractInfo = new WEB3TPContractInfo();
            l_contractInfo.setCalcCondition(l_calcCondition);
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContractForTest11a();
            l_historyPerContract.setTargetContract(l_targetContract);
            l_historyPerContract.setContractInfo(l_contractInfo);
//            l_historyPerContract.addHistory(l_history);
            
            Date l_datDate = WEB3DateUtility.getDate("20080202", "yyyyMMdd");
            WEB3TPSummaryUndeliveredContract l_summaryUndeliveredContract =
                l_historyPerContract.getSummaryUndeliveredContract(l_datDate);
            
            //建玉代金＝建玉代金＋建玉変動.建玉代金 = 9999
            assertEquals("0.0", "" + l_summaryUndeliveredContract.getContractAmount());
            //必要保証金＝必要保証金＋建玉変動.必要保証金 = 999.9
            assertEquals("0.0", "" + l_summaryUndeliveredContract.getMarginDeposit());
            //現金必要保証金＝現金必要保証金＋建玉変動.現金必要保証金 =1999.8
            assertEquals("0.0", "" + l_summaryUndeliveredContract.getCashMarginDeposit());
            //決済益＝決済益＋　@建玉変動.決済益 = 32.0
            assertEquals("430.0", "" + l_summaryUndeliveredContract.getContractProfit());
            //決済損＝決済損＋　@建玉変動.決済損
            assertEquals("0.0", "" + l_summaryUndeliveredContract.getContractLoss());
            //建玉諸経費　@　@＝建玉諸経費 ＋　@建玉変動.建玉諸経費
//            assertEquals("100.0", "" + l_summaryUndeliveredContract.getContractTotalCost());
            
            //建手数料
            assertEquals("70.0", "" + l_summaryUndeliveredContract.getSetupFee());
            //その他建玉諸経費
            assertEquals("290.0", "" + l_summaryUndeliveredContract.getContractOtherCost());
            //日歩・逆日歩損
            assertEquals("150.0", "" + l_summaryUndeliveredContract.getContractInterestLoss());
            //日歩・逆日歩益
            assertEquals("90.0", "" + l_summaryUndeliveredContract.getContractInterestProfit());
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
     * get未受渡建玉の集計
     * 正常終了
     * 非日計り返済・現引現渡の場合
     */
    public void testGetSummaryUndeliveredContract_0002()
    {
        final String STR_METHOD_NAME = "testGetSummaryDayTradeSwapContract_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setMarginDepositRate(10);
            l_targetContractDetail.setCashMarginDepositRate(20);
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            
            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Date[] l_datBizDates = new Date[8];
            for (int i = 1; i <= 8; i++)
            {
                l_datBizDates[i - 1] = WEB3DateUtility.getDate("2008020" + i, "yyyyMMdd");
            }
            l_calcCondition.setBizDate(l_datBizDates);
            WEB3TPContractInfo l_contractInfo = new WEB3TPContractInfo();
            l_contractInfo.setCalcCondition(l_calcCondition);
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContractForTest4();
            l_historyPerContract.setTargetContract(l_targetContract);
            l_historyPerContract.setContractInfo(l_contractInfo);
            
            Date l_datDate = WEB3DateUtility.getDate("20080202", "yyyyMMdd");
            WEB3TPSummaryUndeliveredContract l_summaryUndeliveredContract =
                l_historyPerContract.getSummaryUndeliveredContract(l_datDate);
            
            //建玉代金＝建玉代金＋建玉変動.建玉代金 = 9999.0
            assertEquals("0.0", "" + l_summaryUndeliveredContract.getContractAmount());
            //必要保証金＝必要保証金＋建玉変動.必要保証金 = 999.0
            assertEquals("0.0", "" + l_summaryUndeliveredContract.getMarginDeposit());
            //現金必要保証金＝現金必要保証金＋建玉変動.現金必要保証金 =1999.0
            assertEquals("0.0", "" + l_summaryUndeliveredContract.getCashMarginDeposit());
            //決済益＝決済益＋　@建玉変動.決済益 = 0.0
            assertEquals("0.0", "" + l_summaryUndeliveredContract.getContractProfit());
            //決済損＝決済損＋　@建玉変動.決済損 ＋　@建玉変動.建玉諸経費 = 0.0
            assertEquals("0.0", "" + l_summaryUndeliveredContract.getContractLoss());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3TPHistoryPerContractForTest extends WEB3TPHistoryPerContract
    {
        public List getHistories()
        {
            WEB3TPHistory l_history0 = new WEB3TPHistory();
            WEB3TPHistory l_history1 = new WEB3TPHistory();
            WEB3TPHistory l_history2 = new WEB3TPHistory();
            WEB3TPHistory l_history3 = new WEB3TPHistory();
            l_history1.setTransactionCateg(FinTransactionCateg.EQTYPE_CLOSE_MARGIN);
            l_history2.setTransactionCateg(FinTransactionCateg.EQTYPE_SWAP_MARGIN);
            l_history2.setTransactionDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_history2.setQuantity(100.5);
            l_history3.setTransactionCateg(FinTransactionCateg.EQTYPE_SWAP_MARGIN);
            l_history3.setTransactionDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_history3.setQuantity(100.55);
            List l_lis = new ArrayList();
            l_lis.add(l_history0);
            l_lis.add(l_history1);
            l_lis.add(l_history2);
            l_lis.add(l_history3);
            return l_lis;
        }
    }
    
    private class WEB3TPHistoryPerContractForTest1 extends WEB3TPHistoryPerContract
    {
        public List getHistories()
        {
            WEB3TPHistory l_history = new WEB3TPHistoryForTest();
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20080213","yyyyMMdd"));
            l_targetContractDetail.setMarginDepositRate(10);
            l_targetContractDetail.setCashMarginDepositRate(20);
            l_targetContractDetail.setContractType(ContractTypeEnum.LONG);
            
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_CLOSE_MARGIN);
//            l_history.setContractTotalCost(32);
            l_history.setTransactionDate(WEB3DateUtility.getDate("20080210","yyyyMMdd"));
            l_history.setTargetContract(l_targetContract);
            l_history.setExecuted(true);
            l_history.setDeliveryDate(WEB3DateUtility.getDate("20080217","yyyyMMdd"));
            l_history.setReflectStartDay(WEB3DateUtility.getDate("20080213","yyyyMMdd"));
            l_history.setReflectEndDay(WEB3DateUtility.getDate("20080213","yyyyMMdd"));
            l_history.setPrice(99.99);
            l_history.setQuantity(100);
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_CLOSE_MARGIN);
            l_history.setTargetContract(l_targetContract);
//            l_history.setContractTotalCost(100);//建玉変動.建玉諸経費
//            l_history.setImportedPayInterestFee(90);//建玉変動.逆日歩
            l_history.setNetAmount(10);//建玉変動.受渡代金
            
            
            l_history.setCommissionFee(10);
            l_history.setCommissionFeeTax(20);
            l_history.setImportedSetupFee(30);
            l_history.setImportedSetupFeeTax(40);
            l_history.setImportedNameTransferFee(50);
            l_history.setImportedNameTransferFeeTax(60);
            l_history.setImportedManagementFee(70);
            l_history.setImportedManagementFeeTax(80);
            l_history.setImportedInterestFee(90);
            l_history.setImportedPayInterestFee(100);
            l_history.setImportedLoanEquityFee(50);
            
            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Date[] l_datBizDates = new Date[8];
            for (int i = 1; i <= 8; i++)
            {
                l_datBizDates[i - 1] = WEB3DateUtility.getDate("20080201" + i, "yyyyMMdd");
            }
            l_calcCondition.setBizDate(l_datBizDates);
            l_calcCondition.setEquityNextDayOrderStartDiv(false);
            l_history.setCalcCondition(l_calcCondition);
            List l_lisHistories = new ArrayList();
            l_lisHistories.add(l_history);
            return l_lisHistories;
        }
    }
    
    private class WEB3TPHistoryPerContractForTest1a extends WEB3TPHistoryPerContract
    {
        public List getHistories()
        {
            WEB3TPHistory l_history = new WEB3TPHistoryForTest();
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20080213","yyyyMMdd"));
            l_targetContractDetail.setMarginDepositRate(10);
            l_targetContractDetail.setCashMarginDepositRate(20);
            l_targetContractDetail.setContractType(ContractTypeEnum.SHORT);
            
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_CLOSE_MARGIN);
//            l_history.setContractTotalCost(32);
            l_history.setTransactionDate(WEB3DateUtility.getDate("20080210","yyyyMMdd"));
            l_history.setTargetContract(l_targetContract);
            l_history.setExecuted(true);
            l_history.setDeliveryDate(WEB3DateUtility.getDate("20080217","yyyyMMdd"));
            l_history.setReflectStartDay(WEB3DateUtility.getDate("20080213","yyyyMMdd"));
            l_history.setReflectEndDay(WEB3DateUtility.getDate("20080213","yyyyMMdd"));
            l_history.setPrice(99.99);
            l_history.setQuantity(100);
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_CLOSE_MARGIN);
            l_history.setTargetContract(l_targetContract);
//            l_history.setContractTotalCost(100);//建玉変動.建玉諸経費
//            l_history.setImportedPayInterestFee(90);//建玉変動.逆日歩
            l_history.setNetAmount(10);//建玉変動.受渡代金
            
            
            l_history.setCommissionFee(10);
            l_history.setCommissionFeeTax(20);
            l_history.setImportedSetupFee(30);
            l_history.setImportedSetupFeeTax(40);
            l_history.setImportedNameTransferFee(50);
            l_history.setImportedNameTransferFeeTax(60);
            l_history.setImportedManagementFee(70);
            l_history.setImportedManagementFeeTax(80);
            l_history.setImportedInterestFee(90);
            l_history.setImportedPayInterestFee(100);
            l_history.setImportedLoanEquityFee(50);
            
            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Date[] l_datBizDates = new Date[8];
            for (int i = 1; i <= 8; i++)
            {
                l_datBizDates[i - 1] = WEB3DateUtility.getDate("20080201" + i, "yyyyMMdd");
            }
            l_calcCondition.setBizDate(l_datBizDates);
            l_calcCondition.setEquityNextDayOrderStartDiv(false);
            l_history.setCalcCondition(l_calcCondition);
            List l_lisHistories = new ArrayList();
            l_lisHistories.add(l_history);
            return l_lisHistories;
        }
    }
    
    private class WEB3TPHistoryPerContractForTest11 extends WEB3TPHistoryPerContract
    {
        public List getHistories()
        {
            WEB3TPHistory l_history = new WEB3TPHistoryForTest();
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20080213","yyyyMMdd"));
            l_targetContractDetail.setMarginDepositRate(10);
            l_targetContractDetail.setCashMarginDepositRate(20);
            l_targetContractDetail.setContractType(ContractTypeEnum.LONG);
            
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_CLOSE_MARGIN);
//            l_history.setContractTotalCost(32);
            l_history.setTransactionDate(WEB3DateUtility.getDate("20080213","yyyyMMdd"));
            l_history.setTargetContract(l_targetContract);
            l_history.setExecuted(true);
            l_history.setDeliveryDate(WEB3DateUtility.getDate("20080117","yyyyMMdd"));
            l_history.setReflectStartDay(WEB3DateUtility.getDate("20080213","yyyyMMdd"));
            l_history.setReflectEndDay(WEB3DateUtility.getDate("20080213","yyyyMMdd"));
            l_history.setPrice(99.99);
            l_history.setQuantity(100);
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_CLOSE_MARGIN);
            l_history.setTargetContract(l_targetContract);
//            l_history.setContractTotalCost(100);//建玉変動.建玉諸経費
//            l_history.setImportedPayInterestFee(90);//建玉変動.逆日歩
            l_history.setNetAmount(10);//建玉変動.受渡代金
            
            l_history.setCommissionFee(10);
            l_history.setCommissionFeeTax(20);
            l_history.setImportedSetupFee(30);
            l_history.setImportedSetupFeeTax(40);
            l_history.setImportedNameTransferFee(50);
            l_history.setImportedNameTransferFeeTax(60);
            l_history.setImportedManagementFee(70);
            l_history.setImportedManagementFeeTax(80);
            l_history.setImportedInterestFee(90);
            l_history.setImportedPayInterestFee(100);
            l_history.setImportedLoanEquityFee(50);
            
            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Date[] l_datBizDates = new Date[8];
            for (int i = 1; i <= 8; i++)
            {
                l_datBizDates[i - 1] = WEB3DateUtility.getDate("20080201" + i, "yyyyMMdd");
            }
            l_calcCondition.setBizDate(l_datBizDates);
            l_calcCondition.setEquityNextDayOrderStartDiv(false);
            l_history.setCalcCondition(l_calcCondition);
            List l_lisHistories = new ArrayList();
            l_lisHistories.add(l_history);
            return l_lisHistories;
        }
    }
    
    private class WEB3TPHistoryPerContractForTest11a extends WEB3TPHistoryPerContract
    {
        public List getHistories()
        {
            WEB3TPHistory l_history = new WEB3TPHistoryForTest();
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20080213","yyyyMMdd"));
            l_targetContractDetail.setMarginDepositRate(10);
            l_targetContractDetail.setCashMarginDepositRate(20);
            l_targetContractDetail.setContractType(ContractTypeEnum.SHORT);
            
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_CLOSE_MARGIN);
//            l_history.setContractTotalCost(32);
            l_history.setTransactionDate(WEB3DateUtility.getDate("20080213","yyyyMMdd"));
            l_history.setTargetContract(l_targetContract);
            l_history.setExecuted(true);
            l_history.setDeliveryDate(WEB3DateUtility.getDate("20080117","yyyyMMdd"));
            l_history.setReflectStartDay(WEB3DateUtility.getDate("20080213","yyyyMMdd"));
            l_history.setReflectEndDay(WEB3DateUtility.getDate("20080213","yyyyMMdd"));
            l_history.setPrice(99.99);
            l_history.setQuantity(100);
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_CLOSE_MARGIN);
            l_history.setTargetContract(l_targetContract);
//            l_history.setContractTotalCost(100);//建玉変動.建玉諸経費
//            l_history.setImportedPayInterestFee(90);//建玉変動.逆日歩
            l_history.setNetAmount(10);//建玉変動.受渡代金
            
            l_history.setCommissionFee(10);
            l_history.setCommissionFeeTax(20);
            l_history.setImportedSetupFee(30);
            l_history.setImportedSetupFeeTax(40);
            l_history.setImportedNameTransferFee(50);
            l_history.setImportedNameTransferFeeTax(60);
            l_history.setImportedManagementFee(70);
            l_history.setImportedManagementFeeTax(80);
            l_history.setImportedInterestFee(90);
            l_history.setImportedPayInterestFee(100);
            l_history.setImportedLoanEquityFee(50);
            
            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Date[] l_datBizDates = new Date[8];
            for (int i = 1; i <= 8; i++)
            {
                l_datBizDates[i - 1] = WEB3DateUtility.getDate("20080201" + i, "yyyyMMdd");
            }
            l_calcCondition.setBizDate(l_datBizDates);
            l_calcCondition.setEquityNextDayOrderStartDiv(false);
            l_history.setCalcCondition(l_calcCondition);
            List l_lisHistories = new ArrayList();
            l_lisHistories.add(l_history);
            return l_lisHistories;
        }
    }
    private class WEB3TPHistoryPerContractForTest4 extends WEB3TPHistoryPerContract
    {
        public List getHistories()
        {
            WEB3TPHistory l_history = new WEB3TPHistoryForTest();
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20080213","yyyyMMdd"));
            l_targetContractDetail.setMarginDepositRate(10);
            l_targetContractDetail.setCashMarginDepositRate(20);
            
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_SWAP_MARGIN);
//            l_history.setContractTotalCost(32);
            l_history.setTransactionDate(WEB3DateUtility.getDate("20080210","yyyyMMdd"));
            l_history.setTargetContract(l_targetContract);
            l_history.setExecuted(true);
            l_history.setDeliveryDate(WEB3DateUtility.getDate("20080217","yyyyMMdd"));
            l_history.setReflectStartDay(WEB3DateUtility.getDate("20080213","yyyyMMdd"));
            l_history.setReflectEndDay(WEB3DateUtility.getDate("20080213","yyyyMMdd"));
            l_history.setPrice(99.99);
            l_history.setQuantity(100);
            l_history.setNetAmount(500);
//            l_history.setContractTotalCost(650);
            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            
            Map map = new HashMap();
            map.put("swap.contract.deposit.restraint", "1");
            map.put("swap.contract.profitloss.restraint", "1");
            
            Field field;
            try
            {
                field = WEB3TPCalcCondition.class.getDeclaredField("instBranCalcCondition");
                field.setAccessible(true);
                field.set(l_calcCondition, map);
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Date[] l_datBizDates = new Date[8];
            for (int i = 1; i <= 8; i++)
            {
                l_datBizDates[i - 1] = WEB3DateUtility.getDate("20080201" + i, "yyyyMMdd");
            }
            l_calcCondition.setBizDate(l_datBizDates);
            l_calcCondition.setEquityNextDayOrderStartDiv(true);
            l_history.setCalcCondition(l_calcCondition);
            l_history.setPrice(99.99);
            l_history.setQuantity(100);
            l_history.setNetAmount(500);
            List l_lisHistories = new ArrayList();
            l_lisHistories.add(l_history);
            return l_lisHistories;
        }
    }
    private class WEB3TPHistoryPerContractForTest2 extends WEB3TPHistoryPerContract
    {

    }

    private class WEB3TPHistoryPerContractForTest3 extends WEB3TPHistoryPerContract
    {
        public List getHistories()
        {
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setContractType(ContractTypeEnum.LONG);
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            
            WEB3TPHistory l_history = new WEB3TPHistoryForTest();
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_SWAP_MARGIN);
//            l_history.setContractTotalCost(32);
            l_history.setTransactionDate(WEB3DateUtility.getDate("20080201","yyyyMMdd"));
            WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
            l_tPCalcCondition.setEquityNextDayOrderStartDiv(false);
            try
            {
                Map map = new HashMap();
                map.put("swap.contract.deposit.restraint", "0");
                map.put("swap.contract.profitloss.restraint", "1");
                
                Field field;
                field = WEB3TPCalcCondition.class.getDeclaredField("instBranCalcCondition");
    
                field.setAccessible(true);

                field.set(l_tPCalcCondition, map);
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            l_history.setCalcCondition(l_tPCalcCondition);
            l_history.setTargetContract(l_targetContract);
            l_history.setPrice(99.99);
            l_history.setQuantity(100);
            l_history.setNetAmount(500);
//            l_history.setContractTotalCost(650);
            
            l_history.setCommissionFee(10);
            l_history.setCommissionFeeTax(20);
            l_history.setImportedSetupFee(30);
            l_history.setImportedSetupFeeTax(40);
            l_history.setImportedNameTransferFee(50);
            l_history.setImportedNameTransferFeeTax(60);
            l_history.setImportedManagementFee(70);
            l_history.setImportedManagementFeeTax(80);
            l_history.setImportedInterestFee(90);
            l_history.setImportedPayInterestFee(1000);
            l_history.setImportedLoanEquityFee(50);
            List l_lisHistories = new ArrayList();
            l_lisHistories.add(l_history);
            return l_lisHistories;
        }
    }
    
    private class WEB3TPHistoryPerContractForTest33 extends WEB3TPHistoryPerContract
    {
        public List getHistories()
        {
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setContractType(ContractTypeEnum.SHORT);
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            
            WEB3TPHistory l_history = new WEB3TPHistoryForTest();
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_SWAP_MARGIN);
//            l_history.setContractTotalCost(32);
            l_history.setTransactionDate(WEB3DateUtility.getDate("20080201","yyyyMMdd"));
            WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
            l_tPCalcCondition.setEquityNextDayOrderStartDiv(false);
            try
            {
                Map map = new HashMap();
                map.put("swap.contract.deposit.restraint", "0");
                map.put("swap.contract.profitloss.restraint", "1");
                
                Field field;
                field = WEB3TPCalcCondition.class.getDeclaredField("instBranCalcCondition");
    
                field.setAccessible(true);

                field.set(l_tPCalcCondition, map);
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            l_history.setCalcCondition(l_tPCalcCondition);
            l_history.setTargetContract(l_targetContract);
            l_history.setPrice(99.99);
            l_history.setQuantity(100);
            l_history.setNetAmount(500);
//            l_history.setContractTotalCost(650);
            
            l_history.setCommissionFee(10);
            l_history.setCommissionFeeTax(20);
            l_history.setImportedSetupFee(30);
            l_history.setImportedSetupFeeTax(40);
            l_history.setImportedNameTransferFee(50);
            l_history.setImportedNameTransferFeeTax(60);
            l_history.setImportedManagementFee(70);
            l_history.setImportedManagementFeeTax(80);
            l_history.setImportedInterestFee(90);
            l_history.setImportedPayInterestFee(1000);
            l_history.setImportedLoanEquityFee(50);
            List l_lisHistories = new ArrayList();
            l_lisHistories.add(l_history);
            return l_lisHistories;
        }
    }
    
    private class WEB3TPHistoryPerContractForTest5 extends WEB3TPHistoryPerContract
    {
        public List getHistories()
        {
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            
            WEB3TPHistory l_history = new WEB3TPHistoryForTest();
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_SWAP_MARGIN);
//            l_history.setContractTotalCost(32);
            l_history.setTransactionDate(WEB3DateUtility.getDate("20080201","yyyyMMdd"));
            WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
            l_tPCalcCondition.setEquityNextDayOrderStartDiv(true);
            try
            {
                Map map = new HashMap();
                map.put("swap.contract.deposit.restraint", "0");
                map.put("swap.contract.profitloss.restraint", "1");
                
                Field field;
                field = WEB3TPCalcCondition.class.getDeclaredField("instBranCalcCondition");
    
                field.setAccessible(true);

                field.set(l_tPCalcCondition, map);
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            l_history.setCalcCondition(l_tPCalcCondition);
            l_history.setTargetContract(l_targetContract);
            l_history.setPrice(99.99);
            l_history.setQuantity(100);
            l_history.setNetAmount(500);
//            l_history.setContractTotalCost(650);
            l_history.setExecuted(true);
            List l_lisHistories = new ArrayList();
            l_lisHistories.add(l_history);
            return l_lisHistories;
        }
    }
    
    private class WEB3TPHistoryForTest extends WEB3TPHistory
    {
        public boolean isDayTradeSwap() 
        {
            return true;
        }
        
        public boolean isDuringReflectTime(Date l_datBizDate)
        {
            return true;
        }
        
        public boolean isUndeliveredContractNotDayTradeSwap()
        {
            return true;
        }
    }
    
    private class WEB3TPCalcConditionForTest extends WEB3TPCalcCondition
    {
        public String getInstBranCalcCondition(String l_strName)
        {
            if ("contractamount.apply.date".equals(l_strName)
                && "testGetSummaryOpenContract_0001()".equals(strMothod))
            {
                return "0";
            }
            if ("contractamount.apply.date".equals(l_strName)
                && "testGetSummaryOpenContract_0002()".equals(strMothod))
            {
                return "1";
            }
            if ("contractamount.apply.date".equals(l_strName)
                && "testGetSummaryOpenContract_0003()".equals(strMothod))
            {
                return "2";
            }
            return null;
        }
    }
}
@

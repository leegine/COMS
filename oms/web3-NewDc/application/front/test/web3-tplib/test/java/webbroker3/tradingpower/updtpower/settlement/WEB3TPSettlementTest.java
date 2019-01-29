head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.39.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPSettlementTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3TPSettlementTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 トウ鋒鋼 (中訊) 新規作成
*/
package webbroker3.tradingpower.updtpower.settlement;

import java.lang.reflect.Method;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;

import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuation;
import webbroker3.tradingpower.updtpower.cash.WEB3TPCashBalance;
import webbroker3.tradingpower.updtpower.cash.WEB3TPCashValuation;
import webbroker3.tradingpower.updtpower.cash.WEB3TPRestraint;
import webbroker3.tradingpower.updtpower.cash.WEB3TPTempRestraint;
import webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionAmount;
import webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionReflector;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPSettlementTest extends TestBaseForMock
{
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPSettlementTest.class);

    WEB3TPCashValuation l_cashValuation = new WEB3TPCashValuation();
    WEB3TPSecurityValuation l_securityValuation = new WEB3TPSecurityValuation();
    WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
    
    public WEB3TPSettlementTest(String arg0)
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

    /**
     * getDayTradeRestraintIncUnexecSellOrder(Date l_datSpecifiedDate, boolean l_blnIsSettlement)
     *
     */
    public void testGetDayTradeRestraintIncUnexecSellOrder_C0001()
    {
        final String STR_METHOD_NAME = "testGetDayTradeRestraintIncUnexecSellOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPSettlement l_settlement = new WEB3TPSettlementForTest(
                l_cashValuation, l_securityValuation, l_calcCondition);

            Date l_datSpecifiedDate = WEB3DateUtility.getDate("20070929", "yyyyMMdd");
            double l_dblResult = l_settlement.getDayTradeRestraintIncUnexecSellOrder(l_datSpecifiedDate, true);
            
            assertEquals(new Double(0.0), new Double(l_dblResult));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetDayTradeRestraintIncUnexecSellOrder_C0002()
    {
        final String STR_METHOD_NAME = "testGetDayTradeRestraintIncUnexecSellOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPSettlement l_settlement = new WEB3TPSettlementForTest(
                l_cashValuation, l_securityValuation, l_calcCondition);

            Date l_datSpecifiedDate = WEB3DateUtility.getDate("20070929", "yyyyMMdd");
            double l_dblResult = l_settlement.getDayTradeRestraintIncUnexecSellOrder(l_datSpecifiedDate, false);
            
            assertEquals(new Double(0.0), new Double(l_dblResult));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * getDayTradeRestraintIncUnexecSellOrder(Date l_datSpecifiedDate)
     *
     */
    public void testGetDayTradeRestraintIncUnexecSellOrder1_C0001()
    {
        final String STR_METHOD_NAME = "testGetDayTradeRestraintIncUnexecSellOrder1_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPSettlement l_settlement = new WEB3TPSettlementForTest(
                l_cashValuation, l_securityValuation, l_calcCondition);

            Date l_datSpecifiedDate = WEB3DateUtility.getDate("20070929", "yyyyMMdd");
            double l_dblResult = l_settlement.getDayTradeRestraintIncUnexecSellOrder(l_datSpecifiedDate);
            
            assertEquals(new Double(0.0), new Double(l_dblResult));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testLoadAccountTransition_C0001()
    {
        final String STR_METHOD_NAME = "testLoadAccountTransition_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3TPCashValuation l_cashValuationForTest = new WEB3TPCashValuationForTest();
        try
        {
            Date[] bizDate = new Date [7];
            bizDate[0] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            bizDate[1] = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            bizDate[2] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            bizDate[3] = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            bizDate[4] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            bizDate[5] = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            bizDate[6] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            l_calcCondition.setBizDate(bizDate);

            WEB3TPTempRestraint l_tempRestraint = new WEB3TPTempRestraintForTest();
            l_tempRestraint.setRestraintDiv("1");
            WEB3TPCashBalance l_cashBalance = new WEB3TPCashBalance();
            l_cashValuationForTest.setCashBalance(l_cashBalance);

            WEB3TPRestraint l_restraint = new WEB3TPRestraintForTest();
            
            Method l_method =
                WEB3TPRestraint.class.getDeclaredMethod("addTempRestraint", new Class[]{WEB3TPTempRestraint.class});
            l_method.setAccessible(true);
            l_method.invoke(l_restraint, new Object[]{l_tempRestraint});
            l_cashValuationForTest.setRestraint(l_restraint);

            WEB3TPTransactionAmount l_transactionAmount = new WEB3TPTransactionAmountForTest();
            l_transactionAmount.setCalcCondition(l_calcCondition);
            l_cashValuationForTest.setTransactionAmount(l_transactionAmount);

            WEB3TPSettlement l_settlement = new WEB3TPSettlement(
                    l_cashValuationForTest, l_securityValuation, l_calcCondition);

            Date l_datSpecifiedDate = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            l_settlement.loadAccountTransition(l_datSpecifiedDate, true, true);
            assertEquals(62, l_settlement.accountTransition.getPrevDateBalance(), 0);
            assertEquals(47, l_settlement.accountTransition.getSpecifiedDateBalance(), 0);
            assertEquals(47, l_settlement.accountTransition.getTotalPaymentAmount(), 0);
            
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testLoadAccountTransition_C0002()
    {
        final String STR_METHOD_NAME = "testLoadAccountTransition_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date[] bizDate = new Date [7];
            bizDate[0] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            bizDate[1] = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            bizDate[2] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            bizDate[3] = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            bizDate[4] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            bizDate[5] = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            bizDate[6] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            l_calcCondition.setBizDate(bizDate);

            WEB3TPCashBalance l_cashBalance = new WEB3TPCashBalance();
            l_cashValuation.setCashBalance(l_cashBalance);

            WEB3TPRestraint l_restraint = new WEB3TPRestraint();
            l_cashValuation.setRestraint(l_restraint);

            WEB3TPTransactionAmount l_transactionAmount = new WEB3TPTransactionAmountForTest();
            l_transactionAmount.setCalcCondition(l_calcCondition);
            l_cashValuation.setTransactionAmount(l_transactionAmount);

            WEB3TPSettlement l_settlement = new WEB3TPSettlement(
                    l_cashValuation, l_securityValuation, l_calcCondition);

            Date l_datSpecifiedDate = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            l_settlement.loadAccountTransition(l_datSpecifiedDate, true, false);
            assertEquals(WEB3TPSettlementReflector.class,
                l_settlement.accountTransition.getLisSettlementReflectors().get(0).getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testLoadAccountTransition1_C0001()
    {
        final String STR_METHOD_NAME = "testLoadAccountTransition1_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date[] bizDate = new Date [7];
            bizDate[0] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            bizDate[1] = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            bizDate[2] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            bizDate[3] = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            bizDate[4] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            bizDate[5] = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            bizDate[6] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            l_calcCondition.setBizDate(bizDate);

            WEB3TPCashBalance l_cashBalance = new WEB3TPCashBalance();
            l_cashValuation.setCashBalance(l_cashBalance);

            WEB3TPRestraint l_restraint = new WEB3TPRestraint();
            l_cashValuation.setRestraint(l_restraint);

            WEB3TPTransactionAmount l_transactionAmount = new WEB3TPTransactionAmountForTest();
            l_transactionAmount.setCalcCondition(l_calcCondition);
            l_cashValuation.setTransactionAmount(l_transactionAmount);

            WEB3TPSettlement l_settlement = new WEB3TPSettlement(
                    l_cashValuation, l_securityValuation, l_calcCondition);

            Date l_datSpecifiedDate = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            l_settlement.loadAccountTransition(l_datSpecifiedDate, true);
            assertEquals(WEB3TPSettlementReflector.class,
                l_settlement.accountTransition.getLisSettlementReflectors().get(0).getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * 指定日前日からの顧客勘定残高"の計算式に"+　@仮拘束金(指定日-1)"を追加
     * "指定日の顧客勘定残高"の計算式に"+　@仮拘束金(指定日)"を追加
     * 出金要素合計"の計算式に"-　@仮拘束金(指定日)"を追加
     */
    public void testLoadAccountTransition_C0003()
    {
        final String STR_METHOD_NAME = "testLoadAccountTransition_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date[] bizDate = new Date [7];
            bizDate[0] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            bizDate[1] = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            bizDate[2] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            bizDate[3] = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            bizDate[4] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            bizDate[5] = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            bizDate[6] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            l_calcCondition.setBizDate(bizDate);

            WEB3TPCashBalance l_cashBalance = new WEB3TPCashBalance();
            l_cashValuation.setCashBalance(l_cashBalance);

            WEB3TPRestraint l_restraint = new WEB3TPRestraint();
            l_cashValuation.setRestraint(l_restraint);

            WEB3TPTransactionAmount l_transactionAmount = new WEB3TPTransactionAmountForTest();
            l_transactionAmount.setCalcCondition(l_calcCondition);
            l_cashValuation.setTransactionAmount(l_transactionAmount);

            WEB3TPSettlement l_settlement = new WEB3TPSettlement(
                    l_cashValuation, l_securityValuation, l_calcCondition);

            Date l_datSpecifiedDate = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            l_settlement.loadAccountTransition(l_datSpecifiedDate, true, true);

            assertEquals("-1.0", l_settlement.accountTransition.getPrevDateBalance() + "");
            assertEquals("0.0", l_settlement.accountTransition.getSpecifiedDateBalance() + "");
            assertEquals("24.0", l_settlement.accountTransition.getTotalPaymentAmount() + "");

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    private class WEB3TPSettlementForTest extends WEB3TPSettlement
    {

        public WEB3TPSettlementForTest(WEB3TPCashValuation l_cashValuation,
            WEB3TPSecurityValuation l_securityValuation,
            WEB3TPCalcCondition l_calcCondition)
        {
            super(l_cashValuation, l_securityValuation, l_calcCondition);
        }
        
        protected void loadAccountTransition(Date l_datSpecifiedDate,
            boolean l_blnUnexecSellOrder, boolean l_blnIsSettlement)
        {
            
        }
        
    }
    
    private class WEB3TPCashValuationForTest extends WEB3TPCashValuation
    {
        /**
         * calc当日約定済代金
         */
        public double calcTodaysExecutedTotal(Date l_datDate)
        {
            Date l_datSpecifiedDate = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            Date bizDate = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            if (WEB3DateUtility.compareToDay(l_datSpecifiedDate, l_datDate) == 0)
            {
                return 1;
            }
            else if (WEB3DateUtility.compareToDay(bizDate, l_datDate) == 0)
            {
                return 2;
            }
            return 0.0;
        }
        /**
         * calc当日未約定代金
         */
        public double calcTodaysUnexecutedTotal(Date l_datDate)
        {
            Date l_datSpecifiedDate = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            Date bizDate = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            if (WEB3DateUtility.compareToDay(l_datSpecifiedDate, l_datDate) == 0)
            {
                return 3;
            }
            else if (WEB3DateUtility.compareToDay(bizDate, l_datDate) == 0)
            {
                return 4;
            }
            return 0.0;
        }

        /**
         * calcその他拘束金
         */
        public double calcOtherRestraintsTotal(Date l_datDate)
        {
            Date l_datSpecifiedDate = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            Date bizDate = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            if (WEB3DateUtility.compareToDay(l_datSpecifiedDate, l_datDate) == 0)
            {
                return 5;
            }
            else if (WEB3DateUtility.compareToDay(bizDate, l_datDate) == 0)
            {
                return 6;
            }
            return 0.0;
        }
        
//        public WEB3TPRestraint getRestraint()
//        {
//            return new WEB3TPRestraintForTest();
//        }
    }
    
    private class WEB3TPRestraintForTest extends WEB3TPRestraint
    {
        private Date datBizDate; 
        
        /**
         * calc即日入金銘柄拘束金
         */
        public double calcTodayDepFundRestraint(Date l_datDate)
        {
            Date l_datSpecifiedDate = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            Date bizDate = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            
            if (WEB3DateUtility.compareToDay(l_datDate, bizDate) == 0)
            {
                return 7;
            }
            else if (WEB3DateUtility.compareToDay(l_datDate, l_datSpecifiedDate) == 0)
            {
                return 8;
            }
            return 0.0;
        }
        
        /**
         * calcIPO拘束金
         */
        public double calcIPORestraint(Date l_datDate)
        {
            Date l_datSpecifiedDate = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            Date bizDate = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            if (WEB3DateUtility.compareToDay(l_datDate, bizDate) == 0)
            {
                return 9;
            }
            else if (WEB3DateUtility.compareToDay(l_datDate, l_datSpecifiedDate) == 0)
            {
                return 10;
            }
            return 0.0;
        }
        
        /**
         * calc有料サービス利用拘束金
         */
        public double calcServiceChargeRestraint(Date l_datDate)
        {
            Date l_datSpecifiedDate = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            Date bizDate = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            if (WEB3DateUtility.compareToDay(l_datDate, bizDate) == 0)
            {
                return 11;
            }
            else if (WEB3DateUtility.compareToDay(l_datDate, l_datSpecifiedDate) == 0)
            {
                return 12;
            }
            return 0.0;
        }
        
        /**
         * calc投資信託前受拘束金
         */
        public double calcMutualFundAdvanceRestraint(Date l_datDate)
        {
            Date l_datSpecifiedDate = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            Date bizDate = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            
            if (WEB3DateUtility.compareToDay(l_datDate, bizDate) == 0)
            {
                return 13;
            }
            else if (WEB3DateUtility.compareToDay(l_datDate, l_datSpecifiedDate) == 0)
            {
                return 14;
            }
            return 0.0;
        }
        
        /**
         * calcストックオプション売付代金拘束金
         */
        public double calcStockOptionSellAmountRestraint(Date l_datDate)
        {
            Date l_datSpecifiedDate = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            Date bizDate = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            if (WEB3DateUtility.compareToDay(l_datDate, bizDate) == 0)
            {
                return 15;
            }
            else if (WEB3DateUtility.compareToDay(l_datDate, l_datSpecifiedDate) == 0)
            {
                return 16;
            }
            return 0.0;
        }
        
        public boolean isDuringReflectTime(Date l_datBizDate)
        {
            datBizDate = l_datBizDate;
            return true;
        }
        
        public double getAmount()
        {
            Date l_datSpecifiedDate = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            Date bizDate = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            if (WEB3DateUtility.compareToDay(l_datSpecifiedDate, datBizDate) == 0)
            {
                return 17;
            }
            else if (WEB3DateUtility.compareToDay(bizDate, datBizDate) == 0)
            {
                return 18;
            }
            return 0.0;
        }
        
        public double calcAdvanceRestraintOffset(Date l_finTransactionDate)
        {
            return 23;
        }
    }
    
    private class WEB3TPTempRestraintForTest extends WEB3TPTempRestraint
    {
        private Date datBizDate;
        
        public boolean isDuringReflectTime(Date l_datBizDate)
        {
            datBizDate = l_datBizDate;
            return true;
        }
        
        public double getAmount()
        {
            Date l_datSpecifiedDate = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            Date bizDate = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
            if (WEB3DateUtility.compareToDay(l_datSpecifiedDate, datBizDate) == 0)
            {
                return 19;
            }
            else if (WEB3DateUtility.compareToDay(bizDate, datBizDate) == 0)
            {
                return 20;
            }
            return 0.0;
        }
    }
    
    private class WEB3TPTransactionAmountForTest extends WEB3TPTransactionAmount
    {
        public WEB3TPTransactionReflector[] getEquityTransactions(
            FinTransactionType[] l_transactionTypes, Date l_datDeliveryDate)
        {
            WEB3TPTransactionReflector[] l_transactionReflector = new WEB3TPTransactionReflector[1];
            l_transactionReflector[0] = new WEB3TPTransactionReflector();
            l_transactionReflector[0].setFinTransactionType(FinTransactionType.EQTYPE_EQUITY_BUY);
            return l_transactionReflector;
        }
        
        /**
         * get出金要素集計<確定>
         */
        public double getFixedMinusTotal(Date l_datDeliveryDate)
        {
            return 21;
        }
        
        /**
         * get入金要素集計<確定>
         */
        public double getFixedPlusTotal(Date l_datDeliveryDate)
        {
            return 22;
        }
        
        public double getMinusTotal(Date l_datDate)
        {
            return 24;
        }
    }
}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.24.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPCashBalanceTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TPCashBalanceTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2009/12/15 張騰宇  新規作成 モデルNo.409 410 429
*/
package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;
import java.util.Hashtable;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import test.util.TestDBUtility;

import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.data.TpCashBalanceParams;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPCashBalanceTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPCashBalanceTest.class);
    
    public WEB3TPCashBalanceTest(String arg0)
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
     * Test method for 'webbroker3.tradingpower.updtpower.cash.WEB3TPCashBalance.calcFixedDeposit(Date)'
     */
    public void testCalcFixedDepositCase1()
    {
        final String STR_METHOD_NAME = "testCalcFixedDepositCase1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            Date l_datStartDate = WEB3DateUtility.getDate("20091214", "yyyyMMdd");
            Date l_datEndDate = WEB3DateUtility.getDate("20091214", "yyyyMMdd");
            l_datStartDate.setTime(1260716400000L);
            l_datEndDate.setTime(1260716600000L);
            WEB3TPCashBalanceReflector l_cashBalance1 = new WEB3TPCashBalanceReflector();
            l_cashBalance1.setAmount(100);
            l_cashBalance1.setReflectStartDay(l_datStartDate);
            l_cashBalance1.setReflectEndDay(l_datEndDate);
            l_cashBalance1.setDeposit(true);
            
            WEB3TPCashBalanceReflector l_cashBalance2 = new WEB3TPCashBalanceReflector();
            l_cashBalance2.setAmount(200);
            l_cashBalance2.setReflectStartDay(l_datStartDate);
            l_cashBalance2.setReflectEndDay(l_datEndDate);
            l_cashBalance2.setDeposit(true);
            
            WEB3TPCashBalanceReflector l_cashBalance3 = new WEB3TPCashBalanceReflector();
            l_cashBalance3.setAmount(300);
            l_cashBalance3.setReflectStartDay(l_datStartDate);
            l_cashBalance3.setReflectEndDay(l_datEndDate);
            l_cashBalance3.setDeposit(true);
            
            WEB3TPCashBalance l_cashBalance = new WEB3TPCashBalance();
            l_cashBalance.addFixedCashBalance(l_cashBalance1);
            l_cashBalance.addFixedCashBalance(l_cashBalance2);
            l_cashBalance.addFixedCashBalance(l_cashBalance3);
            Date l_datDate = WEB3DateUtility.getDate("20091214", "yyyyMMdd");
            l_datDate.setTime(1260716500000L);
            double l_dblFixedDeposit = l_cashBalance.calcFixedDeposit(l_datDate);
            
            assertEquals(600.0, l_dblFixedDeposit, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
        }

    public void testCalcFixedDepositCase2()
    {
        final String STR_METHOD_NAME = "testCalcFixedDepositCase2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            Date l_datStartDate = WEB3DateUtility.getDate("20091214", "yyyyMMdd");
            Date l_datEndDate = WEB3DateUtility.getDate("20091214", "yyyyMMdd");
            Date l_datStartDate2 = WEB3DateUtility.getDate("20091214", "yyyyMMdd");
            Date l_datEndDate2 = WEB3DateUtility.getDate("20091214", "yyyyMMdd");
            l_datStartDate.setTime(1260716400000L);
            l_datEndDate.setTime(1260716600000L);
            WEB3TPCashBalanceReflector l_cashBalance1 = new WEB3TPCashBalanceReflector();
            l_cashBalance1.setAmount(100);
            l_cashBalance1.setReflectStartDay(l_datStartDate);
            l_cashBalance1.setReflectEndDay(l_datEndDate);
            l_cashBalance1.setDeposit(true);
            
            WEB3TPCashBalanceReflector l_cashBalance2 = new WEB3TPCashBalanceReflector();
            l_cashBalance2.setAmount(200);
            l_datStartDate2.setTime(1260716600000L);
            l_datEndDate2.setTime(1260716400000L);
            l_cashBalance2.setReflectStartDay(l_datStartDate2);
            l_cashBalance2.setReflectEndDay(l_datEndDate2);
            l_cashBalance2.setDeposit(true);
            
            WEB3TPCashBalanceReflector l_cashBalance3 = new WEB3TPCashBalanceReflector();
            l_cashBalance3.setAmount(300);
            l_datStartDate.setTime(1260716400000L);
            l_datEndDate.setTime(1260716600000L);
            l_cashBalance3.setReflectStartDay(l_datStartDate);
            l_cashBalance3.setReflectEndDay(l_datEndDate);
            l_cashBalance3.setDeposit(false);
            
            WEB3TPCashBalance l_cashBalance = new WEB3TPCashBalance();
            l_cashBalance.addFixedCashBalance(l_cashBalance1);
            l_cashBalance.addFixedCashBalance(l_cashBalance2);
            l_cashBalance.addFixedCashBalance(l_cashBalance3);
            Date l_datDate = WEB3DateUtility.getDate("20091214", "yyyyMMdd");
            l_datDate.setTime(1260716500000L);
            double l_dblFixedDeposit = l_cashBalance.calcFixedDeposit(l_datDate);
            
            assertEquals(100.0, l_dblFixedDeposit, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
        }
    
    public void testCalcFixedDepositCase3()
    {
        final String STR_METHOD_NAME = "testCalcFixedDepositCase3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            Date l_datStartDate = WEB3DateUtility.getDate("20091214", "yyyyMMdd");
            Date l_datEndDate = WEB3DateUtility.getDate("20091214", "yyyyMMdd");
            l_datStartDate.setTime(1260716400000L);
            l_datEndDate.setTime(1260716600000L);
            WEB3TPCashBalanceReflector l_cashBalance1 = new WEB3TPCashBalanceReflector();
            l_cashBalance1.setAmount(100);
            l_cashBalance1.setReflectStartDay(l_datStartDate);
            l_cashBalance1.setReflectEndDay(l_datEndDate);
            l_cashBalance1.setDeposit(true);
            
            WEB3TPCashBalance l_cashBalance = new WEB3TPCashBalance();
            l_cashBalance.addFixedCashBalance(l_cashBalance1);
            
            Date l_datDate = WEB3DateUtility.getDate("20091214", "yyyyMMdd");
            l_datDate.setTime(1260716500000L);
            double l_dblFixedDeposit = l_cashBalance.calcFixedDeposit(l_datDate);
            
            assertEquals(100.0, l_dblFixedDeposit, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
        }
    
    public void testCalcFixedDepositCase4()
    {
        final String STR_METHOD_NAME = "testCalcFixedDepositCase4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            Date l_datStartDate = WEB3DateUtility.getDate("20091214", "yyyyMMdd");
            Date l_datEndDate = WEB3DateUtility.getDate("20091214", "yyyyMMdd");
            l_datStartDate.setTime(1260716400000L);
            l_datEndDate.setTime(1260716600000L);
            WEB3TPCashBalanceReflector l_cashBalance1 = new WEB3TPCashBalanceReflector();
            l_cashBalance1.setAmount(100);
            l_cashBalance1.setReflectStartDay(l_datStartDate);
            l_cashBalance1.setReflectEndDay(l_datEndDate);
            l_cashBalance1.setDeposit(true);
            
            WEB3TPCashBalance l_cashBalance = new WEB3TPCashBalance();
//            l_cashBalance.addFixedCashBalance(l_cashBalance1);
            
            Date l_datDate = WEB3DateUtility.getDate("20091214", "yyyyMMdd");
            l_datDate.setTime(1260716500000L);
            double l_dblFixedDeposit = l_cashBalance.calcFixedDeposit(l_datDate);
            
            assertEquals(0.0, l_dblFixedDeposit, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
        }
    
    public void testLoadFixedCashBalancesCase1()
    {
        final String STR_METHOD_NAME = "testLoadFixedCashBalancesCase1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TpCashBalanceParams.TYPE);
            TpCashBalanceParams l_TpCashBalanceParams = TestDBUtility.getTpCashBalanceRow();
            TestDBUtility.insertWithDel(l_TpCashBalanceParams);
            
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(333812512203L);
            Hashtable l_subAccountIds = new Hashtable();
            l_subAccountIds.put(new Long(33381251220301L), SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_accountInfo.setSubAccountIds(l_subAccountIds);
            l_accountInfo.setMarginCustFlag(false);
            
            Date l_datStartDate = WEB3DateUtility.getDate("20091214", "yyyyMMdd");
            Date l_datEndDate = WEB3DateUtility.getDate("20091214", "yyyyMMdd");
            l_datStartDate.setTime(1260716400000L);
            l_datEndDate.setTime(1260716600000L);
            WEB3TPCashBalanceReflector l_cashBalance1 = new WEB3TPCashBalanceReflector();
            l_cashBalance1.setAmount(100);
            l_cashBalance1.setReflectStartDay(l_datStartDate);
            l_cashBalance1.setReflectEndDay(l_datEndDate);
            l_cashBalance1.setDeposit(true);
            
            Date l_datDate0 = WEB3DateUtility.getDate("20091201", "yyyyMMdd");
            Date l_datDate1 = WEB3DateUtility.getDate("20091202", "yyyyMMdd");
            Date l_datDate2 = WEB3DateUtility.getDate("20091203", "yyyyMMdd");
            Date l_datDate3 = WEB3DateUtility.getDate("20091204", "yyyyMMdd");
            Date l_datDate4 = WEB3DateUtility.getDate("20091205", "yyyyMMdd");
            Date l_datDate5 = WEB3DateUtility.getDate("20091206", "yyyyMMdd");
            Date l_datDate6 = WEB3DateUtility.getDate("20091205", "yyyyMMdd");
            Date l_datDate7 = WEB3DateUtility.getDate("20091206", "yyyyMMdd");
            Date[] bizDate = new Date[8];
            bizDate[0] = l_datDate0;
            bizDate[1] = l_datDate1;
            bizDate[2] = l_datDate2;
            bizDate[3] = l_datDate3;
            bizDate[4] = l_datDate4;
            bizDate[5] = l_datDate5;
            bizDate[6] = l_datDate6;
            bizDate[7] = l_datDate7;
            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            l_calcCondition.setBizDate(bizDate);
            
            WEB3TPCashBalance l_cashBalance = new WEB3TPCashBalance();
            l_cashBalance.setCalcCondition(l_calcCondition);
            l_cashBalance.addFixedCashBalance(l_cashBalance1);
            l_cashBalance.setAccountInfo(l_accountInfo);
            
            l_cashBalance.load();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
        }
    
    public void testLoadFixedCashBalancesCase2()
    {
        final String STR_METHOD_NAME = "testLoadFixedCashBalancesCase2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TpCashBalanceParams.TYPE);
            TpCashBalanceParams l_TpCashBalanceParams = TestDBUtility.getTpCashBalanceRow();
            l_TpCashBalanceParams.setTpCashBalanceId(1);
            l_TpCashBalanceParams.setAccountId(333812512203L);
            l_TpCashBalanceParams.setSubAccountId(33381251220300L);
            TestDBUtility.insertWithDel(l_TpCashBalanceParams);
            TpCashBalanceParams l_TpCashBalanceParams1 = TestDBUtility.getTpCashBalanceRow();
            l_TpCashBalanceParams1.setTpCashBalanceId(2);
            l_TpCashBalanceParams1.setAccountId(333812512203L);
            l_TpCashBalanceParams1.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_TpCashBalanceParams1);
            
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(333812512203L);
            Hashtable l_subAccountIds = new Hashtable();
            l_subAccountIds.put(new Long(33381251220300L), SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountIds.put(new Long(33381251220301L), SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_accountInfo.setSubAccountIds(l_subAccountIds);
            l_accountInfo.setMarginCustFlag(true);
            
            Date l_datStartDate = WEB3DateUtility.getDate("20091214", "yyyyMMdd");
            Date l_datEndDate = WEB3DateUtility.getDate("20091214", "yyyyMMdd");
            l_datStartDate.setTime(1260716400000L);
            l_datEndDate.setTime(1260716600000L);
            WEB3TPCashBalanceReflector l_cashBalance1 = new WEB3TPCashBalanceReflector();
            l_cashBalance1.setAmount(100);
            l_cashBalance1.setReflectStartDay(l_datStartDate);
            l_cashBalance1.setReflectEndDay(l_datEndDate);
            l_cashBalance1.setDeposit(true);
            
            Date l_datDate0 = WEB3DateUtility.getDate("20091201", "yyyyMMdd");
            Date l_datDate1 = WEB3DateUtility.getDate("20091202", "yyyyMMdd");
            Date l_datDate2 = WEB3DateUtility.getDate("20091203", "yyyyMMdd");
            Date l_datDate3 = WEB3DateUtility.getDate("20091204", "yyyyMMdd");
            Date l_datDate4 = WEB3DateUtility.getDate("20091205", "yyyyMMdd");
            Date l_datDate5 = WEB3DateUtility.getDate("20091206", "yyyyMMdd");
            Date l_datDate6 = WEB3DateUtility.getDate("20091205", "yyyyMMdd");
            Date l_datDate7 = WEB3DateUtility.getDate("20091206", "yyyyMMdd");
            Date[] bizDate = new Date[8];
            bizDate[0] = l_datDate0;
            bizDate[1] = l_datDate1;
            bizDate[2] = l_datDate2;
            bizDate[3] = l_datDate3;
            bizDate[4] = l_datDate4;
            bizDate[5] = l_datDate5;
            bizDate[6] = l_datDate6;
            bizDate[7] = l_datDate7;
            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            l_calcCondition.setBizDate(bizDate);
            
            WEB3TPCashBalance l_cashBalance = new WEB3TPCashBalance();
            l_cashBalance.setCalcCondition(l_calcCondition);
            l_cashBalance.addFixedCashBalance(l_cashBalance1);
            l_cashBalance.setAccountInfo(l_accountInfo);
            
            l_cashBalance.load();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
        }
}
@

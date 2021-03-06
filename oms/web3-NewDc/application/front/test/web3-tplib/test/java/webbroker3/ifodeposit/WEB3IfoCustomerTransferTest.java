head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.28.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoCustomerTransferTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3IfoCustomerTransferTest
Author Name      : Daiwa Institute of Research
Revision History : 2008/08/19 陸文靜（中訊）新規作成
*/
package webbroker3.ifodeposit;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

public class WEB3IfoCustomerTransferTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3IfoCustomerTransferTest.class);
    WEB3IfoCustomerTransfer l_ifoCustomerTransfer = null;

    public WEB3IfoCustomerTransferTest(String argo)
    {
        super(argo);
        // TODO Auto-generated constructor stub
    }
    protected void setUp() throws Exception
    {
        super.setUp();
        l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
    }
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //get残高
    //n == 0の場合、this.残高[T+0..2][0]を返却する。
    public void testGetBalance_C0001()
    {
        final String STR_METHOD_NAME = "testGetBalance_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            double[] l_balances = new double[]{10,11,12};
            l_ifoCustomerTransfer.setBalances(l_balances);
            int l_intReservedDate = 0;
            double l_dbGetBalance = l_ifoCustomerTransfer.getBalance(l_intReservedDate);
            assertEquals("10",WEB3StringTypeUtility.formatNumber(l_dbGetBalance));

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get残高
    //n == 1の場合、this.残高[T+0..2][1]を返却する。
    public void testGetBalance_C0002()
    {
        final String STR_METHOD_NAME = "testGetBalance_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            double[] l_balances = new double[]{10,11,12};
            l_ifoCustomerTransfer.setBalances(l_balances);
            int l_intReservedDate = 1;
            double l_dbGetBalance = l_ifoCustomerTransfer.getBalance(l_intReservedDate);
            assertEquals("11",WEB3StringTypeUtility.formatNumber(l_dbGetBalance));

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get残高
    //n == 2の場合、this.残高[T+0..2][2]を返却する。
    public void testGetBalance_C0003()
    {
        final String STR_METHOD_NAME = "testGetBalance_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            double[] l_balances = new double[]{10,11,12};
            l_ifoCustomerTransfer.setBalances(l_balances);
            int l_intReservedDate = 2;
            double l_dbGetBalance = l_ifoCustomerTransfer.getBalance(l_intReservedDate);
            assertEquals("12",WEB3StringTypeUtility.formatNumber(l_dbGetBalance));

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get振替額
    //n == 0の場合(引数.指定日 == 0)
    //this.get振替額[T+0]( )を返却する。
    public void testGetTransferAmount_C0001()
    {
        final String STR_METHOD_NAME = "testGetTransferAmount_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            double l_dblCurrentBizDateTransferAmount = 0;
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(l_dblCurrentBizDateTransferAmount);
            int l_intReservedDate = 0;
            double l_dbGetTransferAmount = l_ifoCustomerTransfer.getTransferAmount(l_intReservedDate);
            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbGetTransferAmount));

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get振替額
    //n == 1、または2の場合(引数.指定日 != 0)
    //this.get振替額[T+0]( )+ this.get振替額[T+1]( )を返却する。
    public void testGetTransferAmount_C0002()
    {
        final String STR_METHOD_NAME = "testGetTransferAmount_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            double l_dblCurrentBizDateTransferAmount = 0;
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(l_dblCurrentBizDateTransferAmount);
            double l_dblNextBizDateTransferAmount = 0;
            l_ifoCustomerTransfer.setNextBizDateTransferAmount(l_dblNextBizDateTransferAmount);
            int l_intReservedDate = 1;
            double l_dbGetTransferAmount = l_ifoCustomerTransfer.getTransferAmount(l_intReservedDate);
            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbGetTransferAmount));

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get振替額
    //指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却する。
    public void testGetTransferAmount_C0003()
    {
        final String STR_METHOD_NAME = "testGetTransferAmount_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 5;
            double l_dbGetTransferAmount = l_ifoCustomerTransfer.getTransferAmount(l_intReservedDate);
            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbGetTransferAmount));

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get振替額
    //n == 1、または2の場合(引数.指定日 != 0)
    //this.get振替額[T+0]( )+ this.get振替額[T+1]( )を返却する。
    public void testGetTransferAmount_C0004()
    {
        final String STR_METHOD_NAME = "testGetTransferAmount_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            double l_dblCurrentBizDateTransferAmount = 0;
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(l_dblCurrentBizDateTransferAmount);
            double l_dblNextBizDateTransferAmount = 0;
            l_ifoCustomerTransfer.setNextBizDateTransferAmount(l_dblNextBizDateTransferAmount);
            int l_intReservedDate = 2;
            double l_dbGetTransferAmount = l_ifoCustomerTransfer.getTransferAmount(l_intReservedDate);
            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbGetTransferAmount));

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get先物決済損益
    //n == 1の場合,this.get先物決済損益[T+1]( )を返却する。
    public void testGetFuturesCloseProfitLoss_C0001()
    {
        final String STR_METHOD_NAME = "testGetFuturesCloseProfitLoss_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            double l_dblNextBizDateFutureCloseProfitLoss = 2;
            l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(l_dblNextBizDateFutureCloseProfitLoss);
            int l_intReservedDate = 1;
            double l_dbGetFuturesCloseProfitLoss = l_ifoCustomerTransfer.getFuturesCloseProfitLoss(l_intReservedDate);
            assertEquals("2",WEB3StringTypeUtility.formatNumber(l_dbGetFuturesCloseProfitLoss));

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get先物決済損益
    //n == 2の場合,this.get先物決済損益[T+1]( )+this.get先物決済損益[T+2]( )を返却する。
    public void testGetFuturesCloseProfitLoss_C0002()
    {
        final String STR_METHOD_NAME = "testGetFuturesCloseProfitLoss_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            double l_dblNextBizDateFutureCloseProfitLoss = 2;
            l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(l_dblNextBizDateFutureCloseProfitLoss);
            double l_dblNext2BizDateFutureCloseProfitLoss = 2;
            l_ifoCustomerTransfer.addNext2BizDateFuturesCloseProfitLoss(l_dblNext2BizDateFutureCloseProfitLoss);
            int l_intReservedDate = 2;
            double l_dbGetFuturesCloseProfitLoss = l_ifoCustomerTransfer.getFuturesCloseProfitLoss(l_intReservedDate);
            assertEquals("4",WEB3StringTypeUtility.formatNumber(l_dbGetFuturesCloseProfitLoss));

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get先物決済損益
    //指定日が範囲外の場合(nが1以上2以下でない場合)、0を返却する。
    public void testGetFuturesCloseProfitLoss_C0003()
    {
        final String STR_METHOD_NAME = "testGetFuturesCloseProfitLoss_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 0;
            double l_dbGetFuturesCloseProfitLoss = l_ifoCustomerTransfer.getFuturesCloseProfitLoss(l_intReservedDate);
            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbGetFuturesCloseProfitLoss));

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //getオプション受渡代金
    //n == 1の場合,this.getオプション受渡代金[T+1]( )を返却する。
    public void testGetOptionNetAmount_C0001()
    {
        final String STR_METHOD_NAME = "testGetOptionNetAmount_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            double l_dblNextOptionNetAmount = 2;
            l_ifoCustomerTransfer.addNextBizDateOptionNetAmount(l_dblNextOptionNetAmount);
            int l_intReservedDate = 1;
            double l_dbGetOptionNetAmount = l_ifoCustomerTransfer.getOptionNetAmount(l_intReservedDate);
            assertEquals("2",WEB3StringTypeUtility.formatNumber(l_dbGetOptionNetAmount));

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //getオプション受渡代金
    //n == 2の場合,this.getオプション受渡代金[T+1]( )　@+　@this.getオプション受渡代金[T+2]( )を返却する。
    public void testGetOptionNetAmount_C0002()
    {
        final String STR_METHOD_NAME = "testGetOptionNetAmount_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            double l_dblNextOptionNetAmount = 2;
            l_ifoCustomerTransfer.addNextBizDateOptionNetAmount(l_dblNextOptionNetAmount);
            double l_dblNext2OptionNetAmount = 2;
            l_ifoCustomerTransfer.addNext2BizDateOptionNetAmount(l_dblNext2OptionNetAmount);
            int l_intReservedDate = 2;
            double l_dbGetOptionNetAmount = l_ifoCustomerTransfer.getOptionNetAmount(l_intReservedDate);
            assertEquals("4",WEB3StringTypeUtility.formatNumber(l_dbGetOptionNetAmount));

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //getオプション受渡代金
    //指定日が範囲外の場合(nが1以上2以下でない場合)、0を返却する。
    public void testGetOptionNetAmount_C0003()
    {
        final String STR_METHOD_NAME = "testGetOptionNetAmount_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 0;
            double l_dbGetOptionNetAmount = l_ifoCustomerTransfer.getOptionNetAmount(l_intReservedDate);
            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbGetOptionNetAmount));

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //getオプション買建概算受渡代金
    //n == 1の場合(引数.指定日 == 1),this.getオプション買建概算受渡代金[T+1]( )を返却する。
    public void testGetOptionBuyEstimatedNetAmount_C0001()
    {
        final String STR_METHOD_NAME = "testGetOptionBuyEstimatedNetAmount_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            double l_dblNextBizDateOptionBuyEstimatedNetAmount = 2;
            l_ifoCustomerTransfer.addNextBizDateOptionBuyEstimatedNetAmount(l_dblNextBizDateOptionBuyEstimatedNetAmount);
            int l_intReservedDate = 1;
            double l_dbGetOptionBuyEstimatedNetAmount = l_ifoCustomerTransfer.getOptionBuyEstimatedNetAmount(l_intReservedDate);
            assertEquals("2",WEB3StringTypeUtility.formatNumber(l_dbGetOptionBuyEstimatedNetAmount));

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //getオプション買建概算受渡代金
    //n == 2の場合(引数.指定日 == 2)
    //this.getオプション買建概算受渡代金[T+1]( ) + this.getオプション買建概算受渡代金[T+2]( )を返却する。
    public void testGetOptionBuyEstimatedNetAmount_C0002()
    {
        final String STR_METHOD_NAME = "testGetOptionBuyEstimatedNetAmount_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            double l_dblNextBizDateOptionBuyEstimatedNetAmount = 2;
            l_ifoCustomerTransfer.addNextBizDateOptionBuyEstimatedNetAmount(l_dblNextBizDateOptionBuyEstimatedNetAmount);
            double l_dblNext2BizDateOptionBuyEstimatedNetAmount = 2;
            l_ifoCustomerTransfer.addNext2BizDateOptionBuyEstimatedNetAmount(l_dblNext2BizDateOptionBuyEstimatedNetAmount);
            int l_intReservedDate = 2;
            double l_dbGetOptionBuyEstimatedNetAmount = l_ifoCustomerTransfer.getOptionBuyEstimatedNetAmount(l_intReservedDate);
            assertEquals("4",WEB3StringTypeUtility.formatNumber(l_dbGetOptionBuyEstimatedNetAmount));

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //getオプション買建概算受渡代金
    //指定日が範囲外の場合(nが1以上2以下でない場合)、0を返却する。
    public void testGetOptionBuyEstimatedNetAmount_C0003()
    {
        final String STR_METHOD_NAME = "testGetOptionBuyEstimatedNetAmount_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            int l_intReservedDate = 0;
            double l_dbGetOptionBuyEstimatedNetAmount = l_ifoCustomerTransfer.getOptionBuyEstimatedNetAmount(l_intReservedDate);
            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbGetOptionBuyEstimatedNetAmount));

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //add入金額
    //当日振替の場合(引数.受渡日 == 引数.営業日[T+0]),this.入金額[T+0] += 引数.振替額
    public void testAddCashinAmount_C0001()
    {
        final String STR_METHOD_NAME = "testAddCashinAmount_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            OrderTypeEnum l_orderType = OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN;
            double l_dblTransferAmount = 1;
            Date l_datDeliveryDate = WEB3DateUtility.getDate("20040702","yyyyMMdd");
            Date l_datCurrentBizDate = WEB3DateUtility.getDate("20040702","yyyyMMdd");

            l_ifoCustomerTransfer.addCashinAmount(l_orderType,l_dblTransferAmount,l_datDeliveryDate,l_datCurrentBizDate);
            double l_dbGetCurrentBizDateCashinAmount = l_ifoCustomerTransfer.getCurrentBizDateCashinAmount();
            assertEquals("1",WEB3StringTypeUtility.formatNumber(l_dbGetCurrentBizDateCashinAmount));

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //add入金額
    //翌日振替の場合(引数.受渡日 != 引数.営業日[T+0]),this.入金額[T+1] += 引数.振替額
    public void testAddCashinAmount_C0002()
    {
        final String STR_METHOD_NAME = "testAddCashinAmount_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            OrderTypeEnum l_orderType = OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN;
            double l_dblTransferAmount = 1;
            Date l_datDeliveryDate = WEB3DateUtility.getDate("20040702","yyyyMMdd");
            Date l_datCurrentBizDate = WEB3DateUtility.getDate("20040705","yyyyMMdd");

            l_ifoCustomerTransfer.addCashinAmount(l_orderType,l_dblTransferAmount,l_datDeliveryDate,l_datCurrentBizDate);
            double l_dbGetNextBizDateCashoutAmount = l_ifoCustomerTransfer.getNextBizDateCashinAmount();
            assertEquals("1",WEB3StringTypeUtility.formatNumber(l_dbGetNextBizDateCashoutAmount));
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //add入金額
    //証拠金口座への振替の場合(引数.注文種別 != (”振替注文（預り金から株先証拠金）”）)
    public void testAddCashinAmount_C0003()
    {
        final String STR_METHOD_NAME = "testAddCashinAmount_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            OrderTypeEnum l_orderType = OrderTypeEnum.CASH_IN;
            double l_dblTransferAmount = 1;
            Date l_datDeliveryDate = WEB3DateUtility.getDate("20040702","yyyyMMdd");
            Date l_datCurrentBizDate = WEB3DateUtility.getDate("20040705","yyyyMMdd");

            l_ifoCustomerTransfer.addCashinAmount(l_orderType,l_dblTransferAmount,l_datDeliveryDate,l_datCurrentBizDate);

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //add出金額
    //当日振替の場合(引数.受渡日 == 引数.営業日[T+0]),this.出金額[T+0] += 引数.振替額
    public void testAddCashoutAmount_C0001()
    {
        final String STR_METHOD_NAME = "testAddCashoutAmount_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            OrderTypeEnum l_orderType = OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT;
            double l_dblTransferAmount = 1;
            Date l_datDeliveryDate = WEB3DateUtility.getDate("20040702","yyyyMMdd");
            Date l_datCurrentBizDate = WEB3DateUtility.getDate("20040702","yyyyMMdd");

            l_ifoCustomerTransfer.addCashoutAmount(l_orderType,l_dblTransferAmount,l_datDeliveryDate,l_datCurrentBizDate);
            double l_dbGetCurrentBizDateCashinAmount = l_ifoCustomerTransfer.getCurrentBizDateCashoutAmount();
            assertEquals("1",WEB3StringTypeUtility.formatNumber(l_dbGetCurrentBizDateCashinAmount));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //add出金額
    //翌日振替の場合(引数.受渡日 != 引数.営業日[T+0]),this.出金額[T+1] += 引数.振替額
    public void testAddCashoutAmount_C0002()
    {
        final String STR_METHOD_NAME = "testAddCashoutAmount_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            OrderTypeEnum l_orderType = OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT;
            double l_dblTransferAmount = 1;
            Date l_datDeliveryDate = WEB3DateUtility.getDate("20040702","yyyyMMdd");
            Date l_datCurrentBizDate = WEB3DateUtility.getDate("20040705","yyyyMMdd");

            l_ifoCustomerTransfer.addCashoutAmount(l_orderType,l_dblTransferAmount,l_datDeliveryDate,l_datCurrentBizDate);
            double l_dbGetNextBizDateCashoutAmount = l_ifoCustomerTransfer.getNextBizDateCashoutAmount();
            assertEquals("1",WEB3StringTypeUtility.formatNumber(l_dbGetNextBizDateCashoutAmount));

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //add出金額
    //預り金口座への振替の場合(引数.注文種別 != (”振替注文（株先証拠金から預り金）”）)
    public void testAddCashoutAmount_C0003()
    {
        final String STR_METHOD_NAME = "testAddCashoutAmount_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            OrderTypeEnum l_orderType = OrderTypeEnum.CASH_IN;
            double l_dblTransferAmount = 1;
            Date l_datDeliveryDate = WEB3DateUtility.getDate("20040702","yyyyMMdd");
            Date l_datCurrentBizDate = WEB3DateUtility.getDate("20040705","yyyyMMdd");

            l_ifoCustomerTransfer.addCashoutAmount(l_orderType,l_dblTransferAmount,l_datDeliveryDate,l_datCurrentBizDate);

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //add先物決済損益[T+1]
    public void testAddNextBizDateFuturesCloseProfitLoss()
    {
        final String STR_METHOD_NAME = "testAddNextBizDateFuturesCloseProfitLoss()";
        log.entering(STR_METHOD_NAME);
        try
        {
            double l_dblNextBizDateFutureCloseProfitLoss = 10;
            l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(l_dblNextBizDateFutureCloseProfitLoss);
            assertEquals(10,l_ifoCustomerTransfer.getNextBizDateFuturesCloseProfitLoss(),0);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //add先物決済損益[T+2]
    public void testAddNext2BizDateFuturesCloseProfitLoss()
    {
        final String STR_METHOD_NAME = "testAddNext2BizDateFuturesCloseProfitLoss()";
        log.entering(STR_METHOD_NAME);
        try
        {
            double l_dblNext2BizDateFutureCloseProfitLoss = 10;
            l_ifoCustomerTransfer.addNext2BizDateFuturesCloseProfitLoss(l_dblNext2BizDateFutureCloseProfitLoss);
            assertEquals(10,l_ifoCustomerTransfer.getNext2BizDateFuturesCloseProfitLoss(),0);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //addオプション受渡代金[T+1]
    public void testAddNextBizDateOptionNetAmount()
    {
        final String STR_METHOD_NAME = "testAddNextBizDateOptionNetAmount()";
        log.entering(STR_METHOD_NAME);
        try
        {
            double l_dblNextOptionNetAmount = 10;
            l_ifoCustomerTransfer.addNextBizDateOptionNetAmount(l_dblNextOptionNetAmount);
            assertEquals(10,l_ifoCustomerTransfer.getNextBizDateOptionNetAmount(),0);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //addオプション受渡代金[T+2]
    public void testAddNext2BizDateOptionNetAmount()
    {
        final String STR_METHOD_NAME = "testAddNext2BizDateOptionNetAmount()";
        log.entering(STR_METHOD_NAME);
        try
        {
            double l_dblNext2OptionNetAmount = 10;
            l_ifoCustomerTransfer.addNext2BizDateOptionNetAmount(l_dblNext2OptionNetAmount);
            assertEquals(10,l_ifoCustomerTransfer.getNext2BizDateOptionNetAmount(),0);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //addオプション買建概算受渡代金[T+1]
    public void testAddNextBizDateOptionBuyEstimatedNetAmount()
    {
        final String STR_METHOD_NAME = "testAddNextBizDateOptionBuyEstimatedNetAmount()";
        log.entering(STR_METHOD_NAME);
        try
        {
            double l_dblNextBizDateOptionBuyEstimatedNetAmount = 10;
            l_ifoCustomerTransfer.addNextBizDateOptionBuyEstimatedNetAmount(l_dblNextBizDateOptionBuyEstimatedNetAmount);
            assertEquals(10,l_ifoCustomerTransfer.getNextBizDateOptionBuyEstimatedNetAmount(),0);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //addオプション買建概算受渡代金[T+2]
    public void testAddNext2BizDateOptionBuyEstimatedNetAmount()
    {
        final String STR_METHOD_NAME = "testAddNext2BizDateOptionBuyEstimatedNetAmount()";
        log.entering(STR_METHOD_NAME);
        try
        {
            double l_dblNext2BizDateOptionBuyEstimatedNetAmount = 10;
            l_ifoCustomerTransfer.addNext2BizDateOptionBuyEstimatedNetAmount(l_dblNext2BizDateOptionBuyEstimatedNetAmount);
            assertEquals(10,l_ifoCustomerTransfer.getNext2BizDateOptionBuyEstimatedNetAmount(),0);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    

}
@

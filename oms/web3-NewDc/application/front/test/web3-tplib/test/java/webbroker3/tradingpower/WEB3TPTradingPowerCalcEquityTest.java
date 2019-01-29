head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.49.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPTradingPowerCalcEquityTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3TPTradingPowerCalcEquityTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2007/10/10 トウ鋒鋼 (中訊) 新規作成
*/
package webbroker3.tradingpower;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.data.TpCalcResultEquityParams;
import webbroker3.tradingpower.data.TpCalcResultEquityRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPTradingPowerCalcEquityTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerCalcEquityTest.class);
    
    WEB3TPTradingPowerCalcEquity l_tpCalcEquity = null;
    
    private String strMethodName = null;

    public WEB3TPTradingPowerCalcEquityTest(String arg0)
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
     * a.引数.注文種別 == 1020:オリックスクレジットへの振替の場合
     *
     */
    public void testCalcAppliedOtherTradingPower_case0001()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPower_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        strMethodName = STR_METHOD_NAME;
        
        OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.TO_ORIX_CREDIT;
        int l_intBasePoint = 5;
        l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
        l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
        
        try
        {
            WEB3TPCalcResult l_result =
                l_tpCalcEquity.calcAppliedOtherTradingPower(l_orderTypeEnum, l_intBasePoint);
            
            assertEquals(5, l_result.appliedPoint);
            assertEquals(1020, l_result.orderTypeEnum.intValue());
            assertEquals(new Double(0.0), new Double(l_result.tradingPower));
            assertEquals(0, l_tpCalcEquity.calcCondition.getOtherBasePoint());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcAppliedOtherTradingPower1_case0001()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPower1_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        strMethodName = STR_METHOD_NAME;
        
        OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.TO_ORIX_CREDIT;
        l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
        l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
        l_tpCalcEquity.calcCondition.setOtherBasePoint(5);
        l_tpCalcEquity.calcCondition.setOtherTradingStop(true);
        l_tpCalcEquity.calcCondition.setPaymentStop(true);
        
        try
        {
            WEB3TPCalcResult l_result =
                l_tpCalcEquity.calcAppliedOtherTradingPower(l_orderTypeEnum);
            
            assertEquals(5, l_result.appliedPoint);
            assertEquals(1020, l_result.orderTypeEnum.intValue());
            assertEquals(new Double(-1.0), new Double(l_result.tradingPower));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcAppliedOtherTradingPower1_case0002()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPower1_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        strMethodName = STR_METHOD_NAME;
        
        OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.TO_ORIX_CREDIT;
        l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
        l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
        l_tpCalcEquity.calcCondition.setOtherBasePoint(5);
        l_tpCalcEquity.calcCondition.setOtherTradingStop(true);
        l_tpCalcEquity.calcCondition.setCashDepositStopDiv(true);
        
        try
        {
            WEB3TPCalcResult l_result =
                l_tpCalcEquity.calcAppliedOtherTradingPower(l_orderTypeEnum);
            
            assertEquals(5, l_result.appliedPoint);
            assertEquals(1020, l_result.orderTypeEnum.intValue());
            assertEquals(new Double(-1.0), new Double(l_result.tradingPower));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcAppliedOtherTradingPower1_case0003()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPower1_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        strMethodName = STR_METHOD_NAME;
        
        OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.CASH_OUT;
        l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
        l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
        l_tpCalcEquity.calcCondition.setPaymentBasePoint(5);
        l_tpCalcEquity.calcCondition.setOrixSecuredLoanAccOpenDiv(true);
        l_tpCalcEquity.calcResultEquity = TestDBUtility.getTpCalcResultEquityRow();
        l_tpCalcEquity.calcCondition.setOrixSecuredLoanLockFlag("1");
        
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            l_tpCalcEquity.calcAppliedOtherTradingPower(l_orderTypeEnum);
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcAppliedOtherTradingPower1_case0004()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPower1_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        strMethodName = STR_METHOD_NAME;
        
        OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN;
        l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
        l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
        l_tpCalcEquity.calcCondition.setPaymentBasePoint(5);
        l_tpCalcEquity.calcCondition.setOrixSecuredLoanAccOpenDiv(true);
        l_tpCalcEquity.calcResultEquity = TestDBUtility.getTpCalcResultEquityRow();
        
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            l_tpCalcEquity.calcAppliedOtherTradingPower(l_orderTypeEnum);
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcAppliedOtherTradingPower1_case0005()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPower1_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        strMethodName = STR_METHOD_NAME;
        
        OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT;
        l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
        l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
        l_tpCalcEquity.calcCondition.setPaymentBasePoint(5);
        l_tpCalcEquity.calcCondition.setOrixSecuredLoanAccOpenDiv(true);
        l_tpCalcEquity.calcResultEquity = TestDBUtility.getTpCalcResultEquityRow();
        l_tpCalcEquity.calcCondition.setOrixSecuredLoanLockFlag("1");
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3TPCalcResult l_result = l_tpCalcEquity.calcAppliedOtherTradingPower(l_orderTypeEnum);
            
            assertEquals(0, l_result.appliedPoint);
            assertEquals(1011, l_result.orderTypeEnum.intValue());
            assertEquals(new Double(-1.0), new Double(l_result.tradingPower));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcAppliedOtherTradingPower1_case0006()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPower1_case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        strMethodName = STR_METHOD_NAME;
        
        OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.TRANSFER_TO_FEQ;
        l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
        l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
        l_tpCalcEquity.calcCondition.setPaymentBasePoint(5);
        l_tpCalcEquity.calcCondition.setOrixSecuredLoanAccOpenDiv(true);
        l_tpCalcEquity.calcResultEquity = TestDBUtility.getTpCalcResultEquityRow();
        l_tpCalcEquity.calcCondition.setOrixSecuredLoanPaymentTradingPower("-1");
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3TPCalcResult l_result = l_tpCalcEquity.calcAppliedOtherTradingPower(l_orderTypeEnum);
            
            assertEquals(0, l_result.appliedPoint);
            assertEquals(1013, l_result.orderTypeEnum.intValue());
            assertEquals(new Double(-1.0), new Double(l_result.tradingPower));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcAppliedOtherTradingPower1_case0007()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPower1_case0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        strMethodName = STR_METHOD_NAME;
        
        OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK;
        l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
        l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
        l_tpCalcEquity.calcCondition.setPaymentBasePoint(5);
        l_tpCalcEquity.calcCondition.setOtherBasePoint(5);
        l_tpCalcEquity.calcCondition.setOrixSecuredLoanAccOpenDiv(true);
        l_tpCalcEquity.calcResultEquity = TestDBUtility.getTpCalcResultEquityRow();
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3TPCalcResult l_result = l_tpCalcEquity.calcAppliedOtherTradingPower(l_orderTypeEnum);
            
            assertEquals(5, l_result.appliedPoint);
            assertEquals(1019, l_result.orderTypeEnum.intValue());
            assertEquals(new Double(0.0), new Double(l_result.tradingPower));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcOrixCreditPaymentPower_case0001()
    {
        final String STR_METHOD_NAME = "testCalcOrixCreditPaymentPower_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
            l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
            l_tpCalcEquity.calcCondition.setPaymentBasePoint(5);
            l_tpCalcEquity.calcCondition.setOtherBasePoint(5);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanAccOpenDiv(true);
            
            Date[] l_datBizDate = new Date[7];
            l_datBizDate[0] = WEB3DateUtility.getDate("20070911", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20070912", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20070913", "yyyyMMdd");
            l_datBizDate[3] = WEB3DateUtility.getDate("20070914", "yyyyMMdd");
            l_datBizDate[4] = WEB3DateUtility.getDate("20070915", "yyyyMMdd");
            l_datBizDate[5] = WEB3DateUtility.getDate("20070916", "yyyyMMdd");
            l_datBizDate[6] = WEB3DateUtility.getDate("20070917", "yyyyMMdd");
            l_tpCalcEquity.calcCondition.setBizDate(l_datBizDate);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanPaymentTradingPower("20.3");
            l_tpCalcEquity.calcResultEquity = TestDBUtility.getTpCalcResultEquityRow();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(101001010010L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_MODIFIED);
            l_aioOrderUnitParams.setQuantity(5.5);
            l_aioOrderUnitParams.setBizDate("20070917");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            Method l_method = WEB3TPTradingPowerCalcEquity.class.getDeclaredMethod(
                "calcOrixCreditPaymentPower",
                new Class[]{});
            l_method.setAccessible(true);
            
            Double l_dblResult = (Double)l_method.invoke(l_tpCalcEquity, new Object[]{});
            assertEquals(new Double(25.8), l_dblResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcOrixCreditPaymentPower_case0002()
    {
        final String STR_METHOD_NAME = "testCalcOrixCreditPaymentPower_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
            l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
            l_tpCalcEquity.calcCondition.setPaymentBasePoint(5);
            l_tpCalcEquity.calcCondition.setOtherBasePoint(5);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanAccOpenDiv(true);
            
            Date[] l_datBizDate = new Date[7];
            l_datBizDate[0] = WEB3DateUtility.getDate("20070911", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20070912", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20070913", "yyyyMMdd");
            l_datBizDate[3] = WEB3DateUtility.getDate("20070914", "yyyyMMdd");
            l_datBizDate[4] = WEB3DateUtility.getDate("20070915", "yyyyMMdd");
            l_datBizDate[5] = WEB3DateUtility.getDate("20070916", "yyyyMMdd");
            l_datBizDate[6] = WEB3DateUtility.getDate("20070917", "yyyyMMdd");
            l_tpCalcEquity.calcCondition.setBizDate(l_datBizDate);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanPaymentTradingPower("20.3");
            l_tpCalcEquity.calcResultEquity = TestDBUtility.getTpCalcResultEquityRow();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(101001010010L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.CASH_OUT);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_MODIFIED);
            l_aioOrderUnitParams.setQuantity(5.5);
            l_aioOrderUnitParams.setBizDate("20070917");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            Method l_method = WEB3TPTradingPowerCalcEquity.class.getDeclaredMethod(
                "calcOrixCreditPaymentPower",
                new Class[]{});
            l_method.setAccessible(true);
            
            Double l_dblResult = (Double)l_method.invoke(l_tpCalcEquity, new Object[]{});
            assertEquals(new Double(14.8), l_dblResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcOrixCreditPaymentPower_case0003()
    {
        final String STR_METHOD_NAME = "testCalcOrixCreditPaymentPower_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
            l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
            l_tpCalcEquity.calcCondition.setPaymentBasePoint(5);
            l_tpCalcEquity.calcCondition.setOtherBasePoint(5);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanAccOpenDiv(true);
            
            Date[] l_datBizDate = new Date[7];
            l_datBizDate[0] = WEB3DateUtility.getDate("20070911", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20070912", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20070913", "yyyyMMdd");
            l_datBizDate[3] = WEB3DateUtility.getDate("20070914", "yyyyMMdd");
            l_datBizDate[4] = WEB3DateUtility.getDate("20070915", "yyyyMMdd");
            l_datBizDate[5] = WEB3DateUtility.getDate("20070916", "yyyyMMdd");
            l_datBizDate[6] = WEB3DateUtility.getDate("20070917", "yyyyMMdd");
            l_tpCalcEquity.calcCondition.setBizDate(l_datBizDate);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanPaymentTradingPower("20.3");
            l_tpCalcEquity.calcResultEquity = TestDBUtility.getTpCalcResultEquityRow();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(101001010010L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_MODIFIED);
            l_aioOrderUnitParams.setQuantity(5.5);
            l_aioOrderUnitParams.setBizDate("20070917");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            Method l_method = WEB3TPTradingPowerCalcEquity.class.getDeclaredMethod(
                "calcOrixCreditPaymentPower",
                new Class[]{});
            l_method.setAccessible(true);
            
            Double l_dblResult = (Double)l_method.invoke(l_tpCalcEquity, new Object[]{});
            assertEquals(new Double(25.8), l_dblResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcOrixCreditPaymentPower_case0004()
    {
        final String STR_METHOD_NAME = "testCalcOrixCreditPaymentPower_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
            l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
            l_tpCalcEquity.calcCondition.setPaymentBasePoint(5);
            l_tpCalcEquity.calcCondition.setOtherBasePoint(5);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanAccOpenDiv(true);
            
            Date[] l_datBizDate = new Date[7];
            l_datBizDate[0] = WEB3DateUtility.getDate("20070911", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20070912", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20070913", "yyyyMMdd");
            l_datBizDate[3] = WEB3DateUtility.getDate("20070914", "yyyyMMdd");
            l_datBizDate[4] = WEB3DateUtility.getDate("20070915", "yyyyMMdd");
            l_datBizDate[5] = WEB3DateUtility.getDate("20070916", "yyyyMMdd");
            l_datBizDate[6] = WEB3DateUtility.getDate("20070917", "yyyyMMdd");
            l_tpCalcEquity.calcCondition.setBizDate(l_datBizDate);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanPaymentTradingPower("20.3");
            l_tpCalcEquity.calcResultEquity = TestDBUtility.getTpCalcResultEquityRow();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(101001010010L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_MODIFIED);
            l_aioOrderUnitParams.setQuantity(5.5);
            l_aioOrderUnitParams.setBizDate("20070917");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            Method l_method = WEB3TPTradingPowerCalcEquity.class.getDeclaredMethod(
                "calcOrixCreditPaymentPower",
                new Class[]{});
            l_method.setAccessible(true);
            
            Double l_dblResult = (Double)l_method.invoke(l_tpCalcEquity, new Object[]{});
            assertEquals(new Double(14.8), l_dblResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcOrixCreditPaymentPower_case0005()
    {
        final String STR_METHOD_NAME = "testCalcOrixCreditPaymentPower_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
            l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
            l_tpCalcEquity.calcCondition.setPaymentBasePoint(5);
            l_tpCalcEquity.calcCondition.setOtherBasePoint(5);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanAccOpenDiv(true);
            
            Date[] l_datBizDate = new Date[7];
            l_datBizDate[0] = WEB3DateUtility.getDate("20070911", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20070912", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20070913", "yyyyMMdd");
            l_datBizDate[3] = WEB3DateUtility.getDate("20070914", "yyyyMMdd");
            l_datBizDate[4] = WEB3DateUtility.getDate("20070915", "yyyyMMdd");
            l_datBizDate[5] = WEB3DateUtility.getDate("20070916", "yyyyMMdd");
            l_datBizDate[6] = WEB3DateUtility.getDate("20070917", "yyyyMMdd");
            l_tpCalcEquity.calcCondition.setBizDate(l_datBizDate);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanPaymentTradingPower("20.3");
            l_tpCalcEquity.calcResultEquity = TestDBUtility.getTpCalcResultEquityRow();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(101001010010L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_MODIFIED);
            l_aioOrderUnitParams.setQuantity(5.5);
            l_aioOrderUnitParams.setBizDate("20070917");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            Method l_method = WEB3TPTradingPowerCalcEquity.class.getDeclaredMethod(
                "calcOrixCreditPaymentPower",
                new Class[]{});
            l_method.setAccessible(true);
            
            Double l_dblResult = (Double)l_method.invoke(l_tpCalcEquity, new Object[]{});
            assertEquals(new Double(25.8), l_dblResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcOrixCreditPaymentPower_case0006()
    {
        final String STR_METHOD_NAME = "testCalcOrixCreditPaymentPower_case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
            l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
            l_tpCalcEquity.calcCondition.setPaymentBasePoint(5);
            l_tpCalcEquity.calcCondition.setOtherBasePoint(5);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanAccOpenDiv(true);
            
            Date[] l_datBizDate = new Date[7];
            l_datBizDate[0] = WEB3DateUtility.getDate("20070911", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20070912", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20070913", "yyyyMMdd");
            l_datBizDate[3] = WEB3DateUtility.getDate("20070914", "yyyyMMdd");
            l_datBizDate[4] = WEB3DateUtility.getDate("20070915", "yyyyMMdd");
            l_datBizDate[5] = WEB3DateUtility.getDate("20070916", "yyyyMMdd");
            l_datBizDate[6] = WEB3DateUtility.getDate("20070917", "yyyyMMdd");
            l_tpCalcEquity.calcCondition.setBizDate(l_datBizDate);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanPaymentTradingPower("20.3");
            l_tpCalcEquity.calcResultEquity = TestDBUtility.getTpCalcResultEquityRow();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(101001010010L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_MODIFIED);
            l_aioOrderUnitParams.setQuantity(5.5);
            l_aioOrderUnitParams.setBizDate("20070917");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            Method l_method = WEB3TPTradingPowerCalcEquity.class.getDeclaredMethod(
                "calcOrixCreditPaymentPower",
                new Class[]{});
            l_method.setAccessible(true);
            
            Double l_dblResult = (Double)l_method.invoke(l_tpCalcEquity, new Object[]{});
            assertEquals(new Double(14.8), l_dblResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcOrixCreditPaymentPower_case0007()
    {
        final String STR_METHOD_NAME = "testCalcOrixCreditPaymentPower_case0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
            l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
            l_tpCalcEquity.calcCondition.setPaymentBasePoint(5);
            l_tpCalcEquity.calcCondition.setOtherBasePoint(5);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanAccOpenDiv(true);
            
            Date[] l_datBizDate = new Date[7];
            l_datBizDate[0] = WEB3DateUtility.getDate("20070911", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20070912", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20070913", "yyyyMMdd");
            l_datBizDate[3] = WEB3DateUtility.getDate("20070914", "yyyyMMdd");
            l_datBizDate[4] = WEB3DateUtility.getDate("20070915", "yyyyMMdd");
            l_datBizDate[5] = WEB3DateUtility.getDate("20070916", "yyyyMMdd");
            l_datBizDate[6] = WEB3DateUtility.getDate("20070917", "yyyyMMdd");
            l_tpCalcEquity.calcCondition.setBizDate(l_datBizDate);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanPaymentTradingPower("20.3");
            l_tpCalcEquity.calcResultEquity = TestDBUtility.getTpCalcResultEquityRow();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(101001010010L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.TRANSFER_FROM_FEQ);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_MODIFIED);
            l_aioOrderUnitParams.setQuantity(5.5);
            l_aioOrderUnitParams.setBizDate("20070917");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            Method l_method = WEB3TPTradingPowerCalcEquity.class.getDeclaredMethod(
                "calcOrixCreditPaymentPower",
                new Class[]{});
            l_method.setAccessible(true);
            
            Double l_dblResult = (Double)l_method.invoke(l_tpCalcEquity, new Object[]{});
            assertEquals(new Double(25.8), l_dblResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcOrixCreditPaymentPower_case0008()
    {
        final String STR_METHOD_NAME = "testCalcOrixCreditPaymentPower_case0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
            l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
            l_tpCalcEquity.calcCondition.setPaymentBasePoint(5);
            l_tpCalcEquity.calcCondition.setOtherBasePoint(5);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanAccOpenDiv(true);
            
            Date[] l_datBizDate = new Date[7];
            l_datBizDate[0] = WEB3DateUtility.getDate("20070911", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20070912", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20070913", "yyyyMMdd");
            l_datBizDate[3] = WEB3DateUtility.getDate("20070914", "yyyyMMdd");
            l_datBizDate[4] = WEB3DateUtility.getDate("20070915", "yyyyMMdd");
            l_datBizDate[5] = WEB3DateUtility.getDate("20070916", "yyyyMMdd");
            l_datBizDate[6] = WEB3DateUtility.getDate("20070917", "yyyyMMdd");
            l_tpCalcEquity.calcCondition.setBizDate(l_datBizDate);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanPaymentTradingPower("20.3");
            l_tpCalcEquity.calcResultEquity = TestDBUtility.getTpCalcResultEquityRow();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(101001010010L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.TRANSFER_TO_FEQ);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_MODIFIED);
            l_aioOrderUnitParams.setQuantity(5.5);
            l_aioOrderUnitParams.setBizDate("20070917");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            Method l_method = WEB3TPTradingPowerCalcEquity.class.getDeclaredMethod(
                "calcOrixCreditPaymentPower",
                new Class[]{});
            l_method.setAccessible(true);
            
            Double l_dblResult = (Double)l_method.invoke(l_tpCalcEquity, new Object[]{});
            assertEquals(new Double(14.8), l_dblResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcOrixCreditPaymentPower_case0009()
    {
        final String STR_METHOD_NAME = "testCalcOrixCreditPaymentPower_case0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
            l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
            l_tpCalcEquity.calcCondition.setPaymentBasePoint(5);
            l_tpCalcEquity.calcCondition.setOtherBasePoint(5);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanAccOpenDiv(true);
            
            Date[] l_datBizDate = new Date[7];
            l_datBizDate[0] = WEB3DateUtility.getDate("20070911", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20070912", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20070913", "yyyyMMdd");
            l_datBizDate[3] = WEB3DateUtility.getDate("20070914", "yyyyMMdd");
            l_datBizDate[4] = WEB3DateUtility.getDate("20070915", "yyyyMMdd");
            l_datBizDate[5] = WEB3DateUtility.getDate("20070916", "yyyyMMdd");
            l_datBizDate[6] = WEB3DateUtility.getDate("20070917", "yyyyMMdd");
            l_tpCalcEquity.calcCondition.setBizDate(l_datBizDate);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanPaymentTradingPower("20.3");
            l_tpCalcEquity.calcResultEquity = TestDBUtility.getTpCalcResultEquityRow();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(101001010010L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_OTHER_TRANSFER);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_MODIFIED);
            l_aioOrderUnitParams.setQuantity(5.5);
            l_aioOrderUnitParams.setBizDate("20070917");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            Method l_method = WEB3TPTradingPowerCalcEquity.class.getDeclaredMethod(
                "calcOrixCreditPaymentPower",
                new Class[]{});
            l_method.setAccessible(true);
            
            Double l_dblResult = (Double)l_method.invoke(l_tpCalcEquity, new Object[]{});
            assertEquals(new Double(25.8), l_dblResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcOrixCreditPaymentPower_case0010()
    {
        final String STR_METHOD_NAME = "testCalcOrixCreditPaymentPower_case0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
            l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
            l_tpCalcEquity.calcCondition.setPaymentBasePoint(5);
            l_tpCalcEquity.calcCondition.setOtherBasePoint(5);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanAccOpenDiv(true);
            
            Date[] l_datBizDate = new Date[7];
            l_datBizDate[0] = WEB3DateUtility.getDate("20070911", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20070912", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20070913", "yyyyMMdd");
            l_datBizDate[3] = WEB3DateUtility.getDate("20070914", "yyyyMMdd");
            l_datBizDate[4] = WEB3DateUtility.getDate("20070915", "yyyyMMdd");
            l_datBizDate[5] = WEB3DateUtility.getDate("20070916", "yyyyMMdd");
            l_datBizDate[6] = WEB3DateUtility.getDate("20070917", "yyyyMMdd");
            l_tpCalcEquity.calcCondition.setBizDate(l_datBizDate);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanPaymentTradingPower("20.3");
            l_tpCalcEquity.calcResultEquity = TestDBUtility.getTpCalcResultEquityRow();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(101001010010L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.TO_OTHER_TRANSFER);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_MODIFIED);
            l_aioOrderUnitParams.setQuantity(5.5);
            l_aioOrderUnitParams.setBizDate("20070917");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            Method l_method = WEB3TPTradingPowerCalcEquity.class.getDeclaredMethod(
                "calcOrixCreditPaymentPower",
                new Class[]{});
            l_method.setAccessible(true);
            
            Double l_dblResult = (Double)l_method.invoke(l_tpCalcEquity, new Object[]{});
            assertEquals(new Double(14.8), l_dblResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcOrixCreditPaymentPower_case0011()
    {
        final String STR_METHOD_NAME = "testCalcOrixCreditPaymentPower_case0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
            l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
            l_tpCalcEquity.calcCondition.setPaymentBasePoint(5);
            l_tpCalcEquity.calcCondition.setOtherBasePoint(5);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanAccOpenDiv(true);
            
            Date[] l_datBizDate = new Date[7];
            l_datBizDate[0] = WEB3DateUtility.getDate("20070911", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20070912", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20070913", "yyyyMMdd");
            l_datBizDate[3] = WEB3DateUtility.getDate("20070914", "yyyyMMdd");
            l_datBizDate[4] = WEB3DateUtility.getDate("20070915", "yyyyMMdd");
            l_datBizDate[5] = WEB3DateUtility.getDate("20070916", "yyyyMMdd");
            l_datBizDate[6] = WEB3DateUtility.getDate("20070917", "yyyyMMdd");
            l_tpCalcEquity.calcCondition.setBizDate(l_datBizDate);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanPaymentTradingPower("20.3");
            l_tpCalcEquity.calcResultEquity = TestDBUtility.getTpCalcResultEquityRow();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(101001010010L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_MODIFIED);
            l_aioOrderUnitParams.setQuantity(5.5);
            l_aioOrderUnitParams.setBizDate("20070917");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            Method l_method = WEB3TPTradingPowerCalcEquity.class.getDeclaredMethod(
                "calcOrixCreditPaymentPower",
                new Class[]{});
            l_method.setAccessible(true);
            
            Double l_dblResult = (Double)l_method.invoke(l_tpCalcEquity, new Object[]{});
            assertEquals(new Double(14.8), l_dblResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcOrixCreditPaymentPower_case0012()
    {
        final String STR_METHOD_NAME = "testCalcOrixCreditPaymentPower_case0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
            l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
            l_tpCalcEquity.calcCondition.setPaymentBasePoint(5);
            l_tpCalcEquity.calcCondition.setOtherBasePoint(5);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanAccOpenDiv(true);
            
            Date[] l_datBizDate = new Date[7];
            l_datBizDate[0] = WEB3DateUtility.getDate("20070911", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20070912", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20070913", "yyyyMMdd");
            l_datBizDate[3] = WEB3DateUtility.getDate("20070914", "yyyyMMdd");
            l_datBizDate[4] = WEB3DateUtility.getDate("20070915", "yyyyMMdd");
            l_datBizDate[5] = WEB3DateUtility.getDate("20070916", "yyyyMMdd");
            l_datBizDate[6] = WEB3DateUtility.getDate("20070917", "yyyyMMdd");
            l_tpCalcEquity.calcCondition.setBizDate(l_datBizDate);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanPaymentTradingPower("20.3");
            l_tpCalcEquity.calcResultEquity = TestDBUtility.getTpCalcResultEquityRow();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(101001010010L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.TO_ORIX_CREDIT);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_MODIFIED);
            l_aioOrderUnitParams.setQuantity(5.5);
            l_aioOrderUnitParams.setBizDate("20070912");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            Method l_method = WEB3TPTradingPowerCalcEquity.class.getDeclaredMethod(
                "calcOrixCreditPaymentPower",
                new Class[]{});
            l_method.setAccessible(true);
            
            Double l_dblResult = (Double)l_method.invoke(l_tpCalcEquity, new Object[]{});
            assertEquals(new Double(20.3), l_dblResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcOrixCreditPaymentPower_case0013()
    {
        final String STR_METHOD_NAME = "testCalcOrixCreditPaymentPower_case0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
            l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
            l_tpCalcEquity.calcCondition.setPaymentBasePoint(5);
            l_tpCalcEquity.calcCondition.setOtherBasePoint(5);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanAccOpenDiv(true);
            
            Date[] l_datBizDate = new Date[7];
            l_datBizDate[0] = WEB3DateUtility.getDate("20070911", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20070912", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20070913", "yyyyMMdd");
            l_datBizDate[3] = WEB3DateUtility.getDate("20070914", "yyyyMMdd");
            l_datBizDate[4] = WEB3DateUtility.getDate("20070915", "yyyyMMdd");
            l_datBizDate[5] = WEB3DateUtility.getDate("20070916", "yyyyMMdd");
            l_datBizDate[6] = WEB3DateUtility.getDate("20070917", "yyyyMMdd");
            l_tpCalcEquity.calcCondition.setBizDate(l_datBizDate);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanPaymentTradingPower("20.3");
            l_tpCalcEquity.calcResultEquity = TestDBUtility.getTpCalcResultEquityRow();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(101001010010L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.TO_ORIX_CREDIT);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
            l_aioOrderUnitParams.setQuantity(5.5);
            l_aioOrderUnitParams.setBizDate("20070917");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            Method l_method = WEB3TPTradingPowerCalcEquity.class.getDeclaredMethod(
                "calcOrixCreditPaymentPower",
                new Class[]{});
            l_method.setAccessible(true);
            
            Double l_dblResult = (Double)l_method.invoke(l_tpCalcEquity, new Object[]{});
            assertEquals(new Double(20.3), l_dblResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcOrixCreditPaymentPower_case0014()
    {
        final String STR_METHOD_NAME = "testCalcOrixCreditPaymentPower_case0014()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
            l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
            l_tpCalcEquity.calcCondition.setPaymentBasePoint(5);
            l_tpCalcEquity.calcCondition.setOtherBasePoint(5);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanAccOpenDiv(true);
            
            Date[] l_datBizDate = new Date[7];
            l_datBizDate[0] = WEB3DateUtility.getDate("20070911", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20070912", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20070913", "yyyyMMdd");
            l_datBizDate[3] = WEB3DateUtility.getDate("20070914", "yyyyMMdd");
            l_datBizDate[4] = WEB3DateUtility.getDate("20070915", "yyyyMMdd");
            l_datBizDate[5] = WEB3DateUtility.getDate("20070916", "yyyyMMdd");
            l_datBizDate[6] = WEB3DateUtility.getDate("20070917", "yyyyMMdd");
            l_tpCalcEquity.calcCondition.setBizDate(l_datBizDate);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanPaymentTradingPower("20.3");
            l_tpCalcEquity.calcResultEquity = TestDBUtility.getTpCalcResultEquityRow();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(101001010010L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.TO_ORIX_CREDIT);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_aioOrderUnitParams.setQuantity(5.5);
            l_aioOrderUnitParams.setBizDate("20070917");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            Method l_method = WEB3TPTradingPowerCalcEquity.class.getDeclaredMethod(
                "calcOrixCreditPaymentPower",
                new Class[]{});
            l_method.setAccessible(true);
            
            Double l_dblResult = (Double)l_method.invoke(l_tpCalcEquity, new Object[]{});
            assertEquals(new Double(20.3), l_dblResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcOrixCreditPaymentPower_case0015()
    {
        final String STR_METHOD_NAME = "testCalcOrixCreditPaymentPower_case0015()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
            l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
            l_tpCalcEquity.calcCondition.setPaymentBasePoint(5);
            l_tpCalcEquity.calcCondition.setOtherBasePoint(5);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanAccOpenDiv(true);
            
            Date[] l_datBizDate = new Date[7];
            l_datBizDate[0] = WEB3DateUtility.getDate("20070911", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20070912", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20070913", "yyyyMMdd");
            l_datBizDate[3] = WEB3DateUtility.getDate("20070914", "yyyyMMdd");
            l_datBizDate[4] = WEB3DateUtility.getDate("20070915", "yyyyMMdd");
            l_datBizDate[5] = WEB3DateUtility.getDate("20070916", "yyyyMMdd");
            l_datBizDate[6] = WEB3DateUtility.getDate("20070917", "yyyyMMdd");
            l_tpCalcEquity.calcCondition.setBizDate(l_datBizDate);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanPaymentTradingPower("20.3");
            l_tpCalcEquity.calcResultEquity = TestDBUtility.getTpCalcResultEquityRow();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(101001010010L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.TO_ORIX_CREDIT);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_aioOrderUnitParams.setQuantity(5.5);
            l_aioOrderUnitParams.setBizDate("20070910");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            Method l_method = WEB3TPTradingPowerCalcEquity.class.getDeclaredMethod(
                "calcOrixCreditPaymentPower",
                new Class[]{});
            l_method.setAccessible(true);
            
            Double l_dblResult = (Double)l_method.invoke(l_tpCalcEquity, new Object[]{});
            assertEquals(new Double(20.3), l_dblResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 
     * 引数が0以上5以下でない時、0を返却する
     */
    public void test_getDayTradeRestraint_0001()
    {
        final String STR_METHOD_NAME = "test_getDayTradeRestraint_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TpCalcResultEquityParams l_calcResultEquityParams = new TpCalcResultEquityParams();
        l_calcResultEquityParams.setDayTradeRestraint5(938.0D);
        
        WEB3TPTradingPowerCalcEquity l_equity = new WEB3TPTradingPowerCalcEquity();
        l_equity.calcResultEquity = l_calcResultEquityParams;
        
        assertEquals("938.0", l_equity.getDayTradeRestraint(5) + "");
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 出金余力停止区分 == trueかつ
     * 注文種別= 1021:CFD振替注文（預り金からCFD口座) 
     */
    public void testCalcAppliedOtherTradingPower_C001()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPower_C001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        strMethodName = STR_METHOD_NAME;
        
        OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT;

        l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
        l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
        
        try
        {
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(101001010010L);
            l_tradingpowerCalcConditionParam.setPaymentStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            
            WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
            l_tPCalcCondition.setPaymentStop(true);
            l_tPCalcCondition.setOtherTradingStop(true);
            l_tPCalcCondition.setOtherBasePoint(4);
            
            TestDBUtility.deleteAll(TpCalcResultEquityRow.TYPE);
            TpCalcResultEquityParams l_tpCalcResultEquityParams = TestDBUtility.getTpCalcResultEquityRow();
            TestDBUtility.insertWithDel(l_tpCalcResultEquityParams);
            l_tpCalcEquity.setCalcResultEquity(l_tpCalcResultEquityParams);
            l_tpCalcEquity.setCalcCondition(l_tPCalcCondition);
            
            WEB3TPCalcResult l_result =
                l_tpCalcEquity.calcAppliedOtherTradingPower(l_orderTypeEnum);
            
            assertEquals(4, l_result.appliedPoint);
            assertEquals(1021, l_result.orderTypeEnum.intValue());
            assertEquals(-1, l_result.tradingPower, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 預り金担保出金停止区分 == true かつ
     * 注文種別= 1021:CFD振替注文（預り金からCFD口座) 
     */
    public void testCalcAppliedOtherTradingPower_C002()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPower_C002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        strMethodName = STR_METHOD_NAME;
        
        OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT;

        l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
        l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
        
        try
        {
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(101001010010L);
            l_tradingpowerCalcConditionParam.setPaymentStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            
            WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
            l_tPCalcCondition.setCashDepositStopDiv(true);
            l_tPCalcCondition.setOtherBasePoint(3);
            
            TestDBUtility.deleteAll(TpCalcResultEquityRow.TYPE);
            TpCalcResultEquityParams l_tpCalcResultEquityParams = TestDBUtility.getTpCalcResultEquityRow();
            TestDBUtility.insertWithDel(l_tpCalcResultEquityParams);
            l_tpCalcEquity.setCalcResultEquity(l_tpCalcResultEquityParams);
            l_tpCalcEquity.setCalcCondition(l_tPCalcCondition);
            
            WEB3TPCalcResult l_result =
                l_tpCalcEquity.calcAppliedOtherTradingPower(l_orderTypeEnum);
            
            assertEquals(3, l_result.appliedPoint);
            assertEquals(1021, l_result.orderTypeEnum.intValue());
            assertEquals(-1, l_result.tradingPower, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * その他商品買付可能額 >= 0  かつ
     * 証券担保ローン口座開設済顧客（担保ローン出金拘束金レコード有）==true  かつ
     * 注文種別= 1021:CFD振替注文（預り金からCFD口座) 
     */
    public void testCalcAppliedOtherTradingPower_C003()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPower_C003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        strMethodName = STR_METHOD_NAME;
        
        OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT;

        l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
        l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
        
        try
        {
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(101001010010L);
            l_tradingpowerCalcConditionParam.setPaymentStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            
            WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
            l_tPCalcCondition.setOtherBasePoint(2);
            l_tPCalcCondition.setOrixSecuredLoanAccOpenDiv(true);
            
            TestDBUtility.deleteAll(TpCalcResultEquityRow.TYPE);
            TpCalcResultEquityParams l_tpCalcResultEquityParams = TestDBUtility.getTpCalcResultEquityRow();
            TestDBUtility.insertWithDel(l_tpCalcResultEquityParams);
            l_tpCalcEquity.setCalcResultEquity(l_tpCalcResultEquityParams);
            l_tpCalcEquity.setCalcCondition(l_tPCalcCondition);
            
            WEB3TPCalcResult l_result =
                l_tpCalcEquity.calcAppliedOtherTradingPower(l_orderTypeEnum);
            assertEquals(2, l_result.appliedPoint);
            assertEquals(1021, l_result.orderTypeEnum.intValue());
            assertEquals(0, l_result.tradingPower, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * その他商品買付余力停止 == trueかつ
     * 注文種別 = 201:MF_BUY 
     */
    public void testCalcAppliedOtherTradingPower_C004()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPower_C004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        strMethodName = STR_METHOD_NAME;
        
        OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.MF_BUY;

        l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
        l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
        
        try
        {
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(101001010010L);
            l_tradingpowerCalcConditionParam.setPaymentStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            
            WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
            l_tPCalcCondition.setOtherTradingStop(true);
            l_tPCalcCondition.setFundBasePoint(5);
            
            TestDBUtility.deleteAll(TpCalcResultEquityRow.TYPE);
            TpCalcResultEquityParams l_tpCalcResultEquityParams = TestDBUtility.getTpCalcResultEquityRow();
            TestDBUtility.insertWithDel(l_tpCalcResultEquityParams);
            l_tpCalcEquity.setCalcResultEquity(l_tpCalcResultEquityParams);
            l_tpCalcEquity.setCalcCondition(l_tPCalcCondition);
            
            WEB3TPCalcResult l_result =
                l_tpCalcEquity.calcAppliedOtherTradingPower(l_orderTypeEnum);
            
            assertEquals(5, l_result.appliedPoint);
            assertEquals(201, l_result.orderTypeEnum.intValue());
            assertEquals(-1, l_result.tradingPower, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 注文種別がCFD振替注文（CFD口座から預り金)の場合
     */
    public void testCalcOrixCreditPaymentPower_C001()
    {
        final String STR_METHOD_NAME = "testCalcOrixCreditPaymentPower_C001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
            l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
            l_tpCalcEquity.calcCondition.setPaymentBasePoint(5);
            l_tpCalcEquity.calcCondition.setOtherBasePoint(5);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanAccOpenDiv(true);
            
            Date[] l_datBizDate = new Date[7];
            l_datBizDate[0] = WEB3DateUtility.getDate("20070911", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20070912", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20070913", "yyyyMMdd");
            l_datBizDate[3] = WEB3DateUtility.getDate("20070914", "yyyyMMdd");
            l_datBizDate[4] = WEB3DateUtility.getDate("20070915", "yyyyMMdd");
            l_datBizDate[5] = WEB3DateUtility.getDate("20070916", "yyyyMMdd");
            l_datBizDate[6] = WEB3DateUtility.getDate("20070917", "yyyyMMdd");
            l_tpCalcEquity.calcCondition.setBizDate(l_datBizDate);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanPaymentTradingPower("20.3");
            l_tpCalcEquity.calcResultEquity = TestDBUtility.getTpCalcResultEquityRow();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(101001010010L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_CFD);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_MODIFIED);
            l_aioOrderUnitParams.setQuantity(5.5);
            l_aioOrderUnitParams.setBizDate("20070917");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            double l_dbThisTimOrderCont = 1.0;
            Field field = WEB3TPTradingPowerCalcEquity.class.getDeclaredField("thisTimOrderCont");
            field.setAccessible(true);
            field.set(l_tpCalcEquity, new Double(l_dbThisTimOrderCont));
            
            Method l_method = WEB3TPTradingPowerCalcEquity.class.getDeclaredMethod(
                "calcOrixCreditPaymentPower",
                new Class[]{});
            l_method.setAccessible(true);
            
            Double l_dblResult = (Double)l_method.invoke(l_tpCalcEquity, new Object[]{});
            assertEquals(new Double(24.8), l_dblResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 注文種別がCFD振替注文（預り金からCFD口座)の場合
     */
    public void testCalcOrixCreditPaymentPower_C002()
    {
        final String STR_METHOD_NAME = "testCalcOrixCreditPaymentPower_C002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
            l_tpCalcEquity.calcCondition = new WEB3TPCalcCondition();
            l_tpCalcEquity.calcCondition.setPaymentBasePoint(5);
            l_tpCalcEquity.calcCondition.setOtherBasePoint(5);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanAccOpenDiv(true);
            
            Date[] l_datBizDate = new Date[7];
            l_datBizDate[0] = WEB3DateUtility.getDate("20070911", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20070912", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20070913", "yyyyMMdd");
            l_datBizDate[3] = WEB3DateUtility.getDate("20070914", "yyyyMMdd");
            l_datBizDate[4] = WEB3DateUtility.getDate("20070915", "yyyyMMdd");
            l_datBizDate[5] = WEB3DateUtility.getDate("20070916", "yyyyMMdd");
            l_datBizDate[6] = WEB3DateUtility.getDate("20070917", "yyyyMMdd");
            l_tpCalcEquity.calcCondition.setBizDate(l_datBizDate);
            l_tpCalcEquity.calcCondition.setOrixSecuredLoanPaymentTradingPower("20.3");
            l_tpCalcEquity.calcResultEquity = TestDBUtility.getTpCalcResultEquityRow();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(101001010010L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_MODIFIED);
            l_aioOrderUnitParams.setQuantity(5.5);
            l_aioOrderUnitParams.setBizDate("20070917");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            double l_dbThisTimOrderCont = 1.0;
            Field field = WEB3TPTradingPowerCalcEquity.class.getDeclaredField("thisTimOrderCont");
            field.setAccessible(true);
            field.set(l_tpCalcEquity, new Double(l_dbThisTimOrderCont));
            
            Method l_method = WEB3TPTradingPowerCalcEquity.class.getDeclaredMethod(
                "calcOrixCreditPaymentPower",
                new Class[]{});
            l_method.setAccessible(true);
            
            Double l_dblResult = (Double)l_method.invoke(l_tpCalcEquity, new Object[]{});
            assertEquals(new Double(13.8), l_dblResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * 引数.基準日がT+0より小さい時
     * WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testCalcAppliedOtherTradingPower2_C0001()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPower2_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.MF_BUY;
            int l_intBasePoint = -1;

            l_tpCalcEquity = new WEB3TPTradingPowerCalcEquity();
            l_tpCalcEquity.calcAppliedOtherTradingPower(l_orderTypeEnum, l_intBasePoint);

            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 引数.注文種別 == 201：投信買付、203：投信募集、204：投信乗換 の場合
     */
    public void testCalcAppliedOtherTradingPower2_C0002()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPower2_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.MF_BUY;
            int l_intBasePoint = 0;

            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Integer fundBasePoint = new Integer(10);
            Field l_field = WEB3TPCalcCondition.class.getDeclaredField("fundBasePoint");
            l_field.setAccessible(true);
            l_field.set(l_calcCondition, fundBasePoint);

            WEB3TPTradingPowerCalcEquity l_equity = new WEB3TPTradingPowerCalcEquityForTestA();
            l_equity.setCalcCondition(l_calcCondition);

            WEB3TPCalcResult l_result = l_equity.calcAppliedOtherTradingPower(l_orderTypeEnum, l_intBasePoint);

            assertEquals(5, l_result.appliedPoint);
            assertEquals(10, l_equity.calcCondition.getFundBasePoint());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 引数.注文種別 == 1001：出金 の場合
     */
    public void testCalcAppliedOtherTradingPower2_C0003()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPower2_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.CASH_OUT;
            int l_intBasePoint = 6;

            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Integer paymentBasePoint = new Integer(10);
            Field l_field = WEB3TPCalcCondition.class.getDeclaredField("paymentBasePoint");
            l_field.setAccessible(true);
            l_field.set(l_calcCondition, paymentBasePoint);

            WEB3TPTradingPowerCalcEquity l_equity = new WEB3TPTradingPowerCalcEquityForTestA();
            l_equity.setCalcCondition(l_calcCondition);

            WEB3TPCalcResult l_result = l_equity.calcAppliedOtherTradingPower(l_orderTypeEnum, l_intBasePoint);

            assertEquals(5, l_result.appliedPoint);
            assertEquals(10, l_equity.calcCondition.getPaymentBasePoint());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 引数.注文種別 == 605：OP新規買建
     */
    public void testCalcAppliedOtherTradingPower2_C0004()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPower2_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN;
            int l_intBasePoint = 5;

            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Integer optionBasePoint = new Integer(10);
            Field l_field = WEB3TPCalcCondition.class.getDeclaredField("optionBasePoint");
            l_field.setAccessible(true);
            l_field.set(l_calcCondition, optionBasePoint);

            WEB3TPTradingPowerCalcEquity l_equity = new WEB3TPTradingPowerCalcEquityForTestA();
            l_equity.setCalcCondition(l_calcCondition);

            WEB3TPCalcResult l_result = l_equity.calcAppliedOtherTradingPower(l_orderTypeEnum, l_intBasePoint);

            assertEquals(5, l_result.appliedPoint);
            assertEquals(10, l_equity.calcCondition.getOptionBasePoint());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 引数.注文種別 == 1007：証拠金への振替, 1011:為替保証金への振替,
     * 1013:外国株式への振替, 1021:CFD振替注文（預り金からCFD口座）
     */
    public void testCalcAppliedOtherTradingPower2_C0005()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPower2_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT;
            int l_intBasePoint = 3;

            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Integer otherBasePoint = new Integer(10);
            Field l_field = WEB3TPCalcCondition.class.getDeclaredField("otherBasePoint");
            l_field.setAccessible(true);
            l_field.set(l_calcCondition, otherBasePoint);

            WEB3TPTradingPowerCalcEquity l_equity = new WEB3TPTradingPowerCalcEquityForTestA();
            l_equity.setCalcCondition(l_calcCondition);

            WEB3TPCalcResult l_result = l_equity.calcAppliedOtherTradingPower(l_orderTypeEnum, l_intBasePoint);

            assertEquals(5, l_result.appliedPoint);
            assertEquals(10, l_equity.calcCondition.getOtherBasePoint());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 以外の場合
     */
    public void testCalcAppliedOtherTradingPower2_C0006()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPower2_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.UNDEFINED;
            int l_intBasePoint = 3;

            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Integer otherBasePoint = new Integer(10);
            Field l_field = WEB3TPCalcCondition.class.getDeclaredField("otherBasePoint");
            l_field.setAccessible(true);
            l_field.set(l_calcCondition, otherBasePoint);

            WEB3TPTradingPowerCalcEquity l_equity = new WEB3TPTradingPowerCalcEquityForTestA();
            l_equity.setCalcCondition(l_calcCondition);

            WEB3TPCalcResult l_result = l_equity.calcAppliedOtherTradingPower(l_orderTypeEnum, l_intBasePoint);

            assertEquals(5, l_result.appliedPoint);
            assertEquals(10, l_equity.calcCondition.getOtherBasePoint());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * WEB3TPTradingPowerCalcEquityForTestA
     */
    private class WEB3TPTradingPowerCalcEquityForTestA extends WEB3TPTradingPowerCalcEquity
    {
        public WEB3TPCalcResult calcAppliedOtherTradingPower(OrderTypeEnum l_orderTypeEnum)
        {
                WEB3TPCalcResult l_calcResult = new WEB3TPCalcResult();
                l_calcResult.appliedPoint = 5;

                return l_calcResult;
        }
    }
     
    private class WEB3TPTradingPowerCalcEquityForTest extends WEB3TPTradingPowerCalcEquity
    {
        public WEB3TPCalcResult calcAppliedOtherTradingPower(OrderTypeEnum l_orderTypeEnum)
        {
            if ("testCalcAppliedOtherTradingPower_case0001()".equals(strMethodName))
            {
                WEB3TPCalcResult l_calcResult = new WEB3TPCalcResult();
                l_calcResult.appliedPoint = 5;
                l_calcResult.orderTypeEnum = OrderTypeEnum.TO_ORIX_CREDIT;
                l_calcResult.tradingPower = 0.0;
                return l_calcResult;
            }
            else
            {
                return super.calcAppliedOtherTradingPower(l_orderTypeEnum);
            }
        }
        
        public double calcOtherTradingPower(int l_intSpecifiedPoint)
        {
            if ("testCalcAppliedOtherTradingPower1_case0003()".equals(strMethodName))
            {
                return 0.0;
            }
            return 2.3;
        }

        public double calcPaymentTradingPower(int l_intSpecifiedPoint)
        {
            if ("testCalcAppliedOtherTradingPower1_case0003()".equals(strMethodName))
            {
                return 0.0;
            }
            return 2.3;
        }
        
        public double calcActualPaymentBalance(int l_intSpecifiedPoint)
        {
            return 26;
        }
    }
}
@

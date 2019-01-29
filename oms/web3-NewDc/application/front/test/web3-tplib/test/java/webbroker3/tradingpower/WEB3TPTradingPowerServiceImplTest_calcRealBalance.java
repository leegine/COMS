head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.50.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPTradingPowerServiceImplTest_calcRealBalance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TPTradingPowerServiceImplTest_calcRealBalance.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/10/12 金傑（中訊）新規作成
*/
package webbroker3.tradingpower;

import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPTradingPowerServiceImplTest_calcRealBalance extends TestBaseForMock
{

    private WEB3TPTradingPowerServiceImpl l_serviceImpl = null;
    
    private WEB3GentradeSubAccount l_subAccount = null;
    
    private WEB3TPTradingPowerCalcEquity l_tpCalcEquity = null;
    
    private WEB3TPTradingPowerCalcMargin l_tpCalcMargin = null;
    
    private Date l_datBizDate = null;

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerServiceImplTest_calcRealBalance.class);

    public WEB3TPTradingPowerServiceImplTest_calcRealBalance(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        this.l_serviceImpl = new WEB3TPTradingPowerServiceImpl();
        this.l_subAccount = new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
        this.l_tpCalcEquity = new WEB3TPTradingPowerCalcEquityForTest();
        this.l_tpCalcMargin = new WEB3TPTradingPowerCalcMarginForTest();
        
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.set(Calendar.YEAR,2007);
        l_calendar.set(Calendar.MONTH,9);
        l_calendar.set(Calendar.DAY_OF_MONTH,12);
        
        this.l_datBizDate = l_calendar.getTime();
        this.initData();

    }
    
    protected void tearDown() throws Exception
    {
        this.l_serviceImpl = null;
        this.l_subAccount = null;
        this.l_tpCalcEquity = null;
        this.l_datBizDate = null;
        this.l_tpCalcMargin = null;
        super.tearDown();
    }
    
    /**
     * 累投注文単位テーブル.発注日=>当日(T+0)
     * 累投注文単位テーブル.受渡日.delivery_date<=引数.受渡日
     *
     */
    public void testCalcRealBalanceEquity_C0001()
    {
        final String STR_METHOD_NAME = "testCalcRealBalanceEquity_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {               
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,9);
            l_calendar.set(Calendar.DAY_OF_MONTH,12);

            double l_dblRealBal =
                this.l_serviceImpl.calcRealBalanceEquity(this.l_subAccount,this.l_tpCalcEquity,l_calendar.getTime());
            
            assertEquals(627430.28,l_dblRealBal,0);
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
     * AIO注文単位テーブル.発注日=>当日(T+0)
     * AIO注文単位テーブル.受渡日.delivery_date<=引数.受渡日
     *
     */
    public void testCalcRealBalanceMargin_C0001()
    {
        final String STR_METHOD_NAME = "testCalcRealBalanceMargin_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {               
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,9);
            l_calendar.set(Calendar.DAY_OF_MONTH,12);

            double l_dblRealBal =
                this.l_serviceImpl.calcRealBalanceMargin(this.l_subAccount,this.l_tpCalcMargin,l_calendar.getTime());
            
            assertEquals(606374.28,l_dblRealBal,0);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getMainAccountRow());
            

            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,9);
            l_calendar.set(Calendar.DAY_OF_MONTH,10);
            
            // RuitoOrderUnitParams.TYPE
            TestDBUtility.deleteAll(RuitoOrderUnitRow.TYPE);
            RuitoOrderUnitParams l_ruitoOrderUnitParams1 = new RuitoOrderUnitParams();
            l_ruitoOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_ruitoOrderUnitParams1.setAccountId(333812512203L);
            l_ruitoOrderUnitParams1.setSubAccountId(33381251220301L);
            l_ruitoOrderUnitParams1.setBranchId(33381L);
            l_ruitoOrderUnitParams1.setOrderId(123456789L);
            l_ruitoOrderUnitParams1.setOrderType(OrderTypeEnum.RUITO_SELL);
            l_ruitoOrderUnitParams1.setOrderCateg(OrderCategEnum.ASSET);
            // last_order_action_serial_no
            l_ruitoOrderUnitParams1.setLastOrderActionSerialNo(123456);
            l_ruitoOrderUnitParams1.setProductType(ProductTypeEnum.RUITO);
            l_ruitoOrderUnitParams1.setQuantity(2632);
            l_ruitoOrderUnitParams1.setDeliveryDate(l_calendar.getTime());
            l_ruitoOrderUnitParams1.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_ruitoOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            // expiration_status
            l_ruitoOrderUnitParams1.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_ruitoOrderUnitParams1.setTaxType(TaxTypeEnum.NORMAL);
            l_ruitoOrderUnitParams1.setBizDate("20071015");
            l_ruitoOrderUnitParams1.setProductId(123456);
            l_ruitoOrderUnitParams1.setQuantityType(QuantityTypeEnum.QUANTITY);
            l_ruitoOrderUnitParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ruitoOrderUnitParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ruitoOrderUnitParams1);
            
            RuitoOrderUnitParams l_ruitoOrderUnitParams2 = new RuitoOrderUnitParams();
            l_ruitoOrderUnitParams2.setOrderUnitId(3304148080002L);
            l_ruitoOrderUnitParams2.setAccountId(333812512203L);
            l_ruitoOrderUnitParams2.setSubAccountId(33381251220301L);
            l_ruitoOrderUnitParams2.setBranchId(33381L);
            l_ruitoOrderUnitParams2.setOrderId(123456789L);
            l_ruitoOrderUnitParams2.setOrderType(OrderTypeEnum.RUITO_SELL);
            l_ruitoOrderUnitParams2.setOrderCateg(OrderCategEnum.ASSET);
            // last_order_action_serial_no
            l_ruitoOrderUnitParams2.setLastOrderActionSerialNo(123456);
            l_ruitoOrderUnitParams2.setProductType(ProductTypeEnum.RUITO);
            l_ruitoOrderUnitParams2.setQuantity(2632);
            l_ruitoOrderUnitParams2.setDeliveryDate(l_calendar.getTime());
            l_ruitoOrderUnitParams2.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_ruitoOrderUnitParams2.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            // expiration_status
            l_ruitoOrderUnitParams2.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_ruitoOrderUnitParams2.setTaxType(TaxTypeEnum.NORMAL);
            l_ruitoOrderUnitParams2.setBizDate("20071015");
            l_ruitoOrderUnitParams2.setProductId(123456);
            l_ruitoOrderUnitParams2.setQuantityType(QuantityTypeEnum.QUANTITY);
            l_ruitoOrderUnitParams2.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ruitoOrderUnitParams2.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ruitoOrderUnitParams2);
            
            RuitoOrderUnitParams l_ruitoOrderUnitParams3 = new RuitoOrderUnitParams();
            l_ruitoOrderUnitParams3.setOrderUnitId(3304148080003L);
            l_ruitoOrderUnitParams3.setAccountId(333812512203L);
            l_ruitoOrderUnitParams3.setSubAccountId(33381251220301L);
            l_ruitoOrderUnitParams3.setBranchId(33381L);
            l_ruitoOrderUnitParams3.setOrderId(123456789L);
            l_ruitoOrderUnitParams3.setOrderType(OrderTypeEnum.RUITO_SELL);
            l_ruitoOrderUnitParams3.setOrderCateg(OrderCategEnum.ASSET);
            // last_order_action_serial_no
            l_ruitoOrderUnitParams3.setLastOrderActionSerialNo(123456);
            l_ruitoOrderUnitParams3.setProductType(ProductTypeEnum.RUITO);
            l_ruitoOrderUnitParams3.setQuantity(2632);
            l_ruitoOrderUnitParams3.setDeliveryDate(l_calendar.getTime());
            l_ruitoOrderUnitParams3.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_ruitoOrderUnitParams3.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            // expiration_status
            l_ruitoOrderUnitParams3.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_ruitoOrderUnitParams3.setTaxType(TaxTypeEnum.NORMAL);
            l_ruitoOrderUnitParams3.setBizDate("20071015");
            l_ruitoOrderUnitParams3.setProductId(123456);
            l_ruitoOrderUnitParams3.setQuantityType(QuantityTypeEnum.QUANTITY);
            l_ruitoOrderUnitParams3.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ruitoOrderUnitParams3.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ruitoOrderUnitParams3);
            
            RuitoOrderUnitParams l_ruitoOrderUnitParams4 = new RuitoOrderUnitParams();
            l_ruitoOrderUnitParams4.setOrderUnitId(3304148080004L);
            l_ruitoOrderUnitParams4.setAccountId(333812512203L);
            l_ruitoOrderUnitParams4.setSubAccountId(33381251220301L);
            l_ruitoOrderUnitParams4.setBranchId(33381L);
            l_ruitoOrderUnitParams4.setOrderId(123456789L);
            l_ruitoOrderUnitParams4.setOrderType(OrderTypeEnum.RUITO_SELL);
            l_ruitoOrderUnitParams4.setOrderCateg(OrderCategEnum.ASSET);
            // last_order_action_serial_no
            l_ruitoOrderUnitParams4.setLastOrderActionSerialNo(123456);
            l_ruitoOrderUnitParams4.setProductType(ProductTypeEnum.RUITO);
            l_ruitoOrderUnitParams4.setQuantity(2632);
            l_ruitoOrderUnitParams4.setDeliveryDate(l_calendar.getTime());
            l_ruitoOrderUnitParams4.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_ruitoOrderUnitParams4.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            // expiration_status
            l_ruitoOrderUnitParams4.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_ruitoOrderUnitParams4.setTaxType(TaxTypeEnum.NORMAL);
            
            // ruito_order_unit.biz_date<当日(T+0)
            l_ruitoOrderUnitParams4.setBizDate("20071008");
            l_ruitoOrderUnitParams4.setProductId(123456);
            l_ruitoOrderUnitParams4.setQuantityType(QuantityTypeEnum.QUANTITY);
            l_ruitoOrderUnitParams4.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ruitoOrderUnitParams4.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ruitoOrderUnitParams4);
            
            RuitoOrderUnitParams l_ruitoOrderUnitParams5 = new RuitoOrderUnitParams();
            l_ruitoOrderUnitParams5.setOrderUnitId(3304148080005L);
            l_ruitoOrderUnitParams5.setAccountId(333812512203L);
            l_ruitoOrderUnitParams5.setSubAccountId(33381251220301L);
            l_ruitoOrderUnitParams5.setBranchId(33381L);
            l_ruitoOrderUnitParams5.setOrderId(123456789L);
            l_ruitoOrderUnitParams5.setOrderType(OrderTypeEnum.RUITO_SELL);
            l_ruitoOrderUnitParams5.setOrderCateg(OrderCategEnum.ASSET);
            // last_order_action_serial_no
            l_ruitoOrderUnitParams5.setLastOrderActionSerialNo(123456);
            l_ruitoOrderUnitParams5.setProductType(ProductTypeEnum.RUITO);
            l_ruitoOrderUnitParams5.setQuantity(2632);
            
            
            // ruito_order_unit.delivery_date>引数.受渡日
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,9);
            l_calendar.set(Calendar.DAY_OF_MONTH,15);
            
            l_ruitoOrderUnitParams5.setDeliveryDate(l_calendar.getTime());
            l_ruitoOrderUnitParams5.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_ruitoOrderUnitParams5.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            // expiration_status
            l_ruitoOrderUnitParams5.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_ruitoOrderUnitParams5.setTaxType(TaxTypeEnum.NORMAL);
            
            l_ruitoOrderUnitParams5.setBizDate("20071015");
            l_ruitoOrderUnitParams5.setProductId(123456);
            l_ruitoOrderUnitParams5.setQuantityType(QuantityTypeEnum.QUANTITY);
            l_ruitoOrderUnitParams5.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ruitoOrderUnitParams5.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ruitoOrderUnitParams5);
            
            RuitoOrderUnitParams l_ruitoOrderUnitParams6 = new RuitoOrderUnitParams();
            l_ruitoOrderUnitParams6.setOrderUnitId(3304148080006L);
            l_ruitoOrderUnitParams6.setAccountId(333812512203L);
            l_ruitoOrderUnitParams6.setSubAccountId(33381251220301L);
            l_ruitoOrderUnitParams6.setBranchId(33381L);
            l_ruitoOrderUnitParams6.setOrderId(123456789L);
            l_ruitoOrderUnitParams6.setOrderType(OrderTypeEnum.RUITO_SELL);
            l_ruitoOrderUnitParams6.setOrderCateg(OrderCategEnum.ASSET);
            // last_order_action_serial_no
            l_ruitoOrderUnitParams6.setLastOrderActionSerialNo(123456);
            l_ruitoOrderUnitParams6.setProductType(ProductTypeEnum.RUITO);
            l_ruitoOrderUnitParams6.setQuantity(2632);
            
            
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,9);
            l_calendar.set(Calendar.DAY_OF_MONTH,12);
            
            l_ruitoOrderUnitParams6.setDeliveryDate(l_calendar.getTime());
            l_ruitoOrderUnitParams6.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_ruitoOrderUnitParams6.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            // expiration_status
            l_ruitoOrderUnitParams6.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_ruitoOrderUnitParams6.setTaxType(TaxTypeEnum.NORMAL);
            
            l_ruitoOrderUnitParams6.setBizDate("20071012");
            l_ruitoOrderUnitParams6.setProductId(123456);
            l_ruitoOrderUnitParams6.setQuantityType(QuantityTypeEnum.QUANTITY);
            l_ruitoOrderUnitParams6.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ruitoOrderUnitParams6.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ruitoOrderUnitParams6);
            
            // AioOrderUnitParams
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams1 = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams1.setOrderUnitId(2000011L);
            l_aioOrderUnitParams1.setAccountId(333812512203L);
            l_aioOrderUnitParams1.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams1.setBranchId(33381L);
            l_aioOrderUnitParams1.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams1.setBizDate("20071015");
            l_aioOrderUnitParams1.setQuantity(2632);

            
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,9);
            l_calendar.set(Calendar.DAY_OF_MONTH,10);
            
            l_aioOrderUnitParams1.setDeliveryDate(l_calendar.getTime());
            l_aioOrderUnitParams1.setOrderStatus(OrderStatusEnum.ORDERED);
            
            l_aioOrderUnitParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_aioOrderUnitParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_aioOrderUnitParams1);
            
            AioOrderUnitParams l_aioOrderUnitParams2 = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams2.setOrderUnitId(2000012L);
            l_aioOrderUnitParams2.setAccountId(333812512203L);
            l_aioOrderUnitParams2.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams2.setBranchId(33381L);
            l_aioOrderUnitParams2.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams2.setBizDate("20071015");
            l_aioOrderUnitParams2.setQuantity(2632);
            
            l_aioOrderUnitParams2.setDeliveryDate(l_calendar.getTime());
            l_aioOrderUnitParams2.setOrderStatus(OrderStatusEnum.ORDERED);
            
            l_aioOrderUnitParams2.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_aioOrderUnitParams2.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_aioOrderUnitParams2);
            
            AioOrderUnitParams l_aioOrderUnitParams3 = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams3.setOrderUnitId(2000013L);
            l_aioOrderUnitParams3.setAccountId(333812512203L);
            l_aioOrderUnitParams3.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams3.setBranchId(33381L);
            l_aioOrderUnitParams3.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams3.setBizDate("20071015");
            l_aioOrderUnitParams3.setQuantity(2632);
            
            l_aioOrderUnitParams3.setDeliveryDate(l_calendar.getTime());
            l_aioOrderUnitParams3.setOrderStatus(OrderStatusEnum.ORDERED);
            
            l_aioOrderUnitParams3.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_aioOrderUnitParams3.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_aioOrderUnitParams3);
            
            AioOrderUnitParams l_aioOrderUnitParams4 = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams4.setOrderUnitId(2000014L);
            l_aioOrderUnitParams4.setAccountId(333812512203L);
            l_aioOrderUnitParams4.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams4.setBranchId(33381L);
            l_aioOrderUnitParams4.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams4.setBizDate("20071008");
            l_aioOrderUnitParams4.setQuantity(2632);
            
            l_aioOrderUnitParams4.setDeliveryDate(l_calendar.getTime());
            l_aioOrderUnitParams4.setOrderStatus(OrderStatusEnum.ORDERED);
            
            l_aioOrderUnitParams4.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_aioOrderUnitParams4.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_aioOrderUnitParams4);
            
            AioOrderUnitParams l_aioOrderUnitParams5 = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams5.setOrderUnitId(2000015L);
            l_aioOrderUnitParams5.setAccountId(333812512203L);
            l_aioOrderUnitParams5.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams5.setBranchId(33381L);
            l_aioOrderUnitParams5.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams5.setBizDate("20071015");
            l_aioOrderUnitParams5.setQuantity(2632);
            
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,9);
            l_calendar.set(Calendar.DAY_OF_MONTH,15);
            
            l_aioOrderUnitParams5.setDeliveryDate(l_calendar.getTime());
            l_aioOrderUnitParams5.setOrderStatus(OrderStatusEnum.ORDERED);
            
            l_aioOrderUnitParams5.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_aioOrderUnitParams5.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_aioOrderUnitParams5);
            
            AioOrderUnitParams l_aioOrderUnitParams6 = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams6.setOrderUnitId(2000016L);
            l_aioOrderUnitParams6.setAccountId(333812512203L);
            l_aioOrderUnitParams6.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams6.setBranchId(33381L);
            l_aioOrderUnitParams6.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams6.setBizDate("20071015");
            l_aioOrderUnitParams6.setQuantity(2632);
            
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,9);
            l_calendar.set(Calendar.DAY_OF_MONTH,12);
            
            l_aioOrderUnitParams6.setDeliveryDate(l_calendar.getTime());
            l_aioOrderUnitParams6.setOrderStatus(OrderStatusEnum.ORDERED);
            
            l_aioOrderUnitParams6.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_aioOrderUnitParams6.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_aioOrderUnitParams6);
            
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3GentradeSubAccountForTest extends WEB3GentradeSubAccount
    {

        public WEB3GentradeSubAccountForTest(SubAccountRow l_subAcctRow)
        {
            super(l_subAcctRow);
        }
        
        public MainAccount getMainAccount()
        {
            WEB3GentradeMainAccount l_mainAccount = null;
            try
            {
               l_mainAccount = new WEB3GentradeMainAccountForTest(333812512246L); 
            }
            catch(Exception l_ex)
            {
                fail();
            }
            return l_mainAccount; 
        }
    }
    
    private class WEB3TPTradingPowerCalcEquityForTest extends WEB3TPTradingPowerCalcEquity
    {
        public WEB3TPCalcCondition getCalcCondition()
        {
            return new WEB3TPCalcConditionForTest();
        }
        
        public double getAccountBalance(int l_intSpecifiedPoint)
        {
            return 625252.27;
        }
        
        public double getTodayExecutedAmount(int l_intSpecifiedPoint)
        {
            return 2569.52;
        }
        
        public double getTodayUnexecutedAmount(int l_intSpecifiedPoint)
        {
            return 5145.26;
        }
        
        public double getOtherRestraint(int l_intSpecifiedPoint)
        {
            return 635.21;
        }
    }
    
    private class WEB3TPTradingPowerCalcMarginForTest extends WEB3TPTradingPowerCalcMargin
    {
        public WEB3TPCalcCondition getCalcCondition()
        {
            return new WEB3TPCalcConditionForTest();
        }
        
        public double getAccountBalance(int l_intSpecifiedPoint)
        {
            return 625252.27;
        }
        
        public double getTodayExecutedAmount(int l_intSpecifiedPoint)
        {
            return 2569.52;
        }
        
        public double getTodayUnexecutedAmount(int l_intSpecifiedPoint)
        {
            return 5145.26;
        }
        
        public double getOtherRestraint(int l_intSpecifiedPoint)
        {
            return 635.21;
        }
    }
    
    private class WEB3TPCalcConditionForTest extends WEB3TPCalcCondition
    {
        public Date getBizDate(int l_intSpecifiedPoint)
        {
            return l_datBizDate;
        }
        
        public int calcSpecifiedPoint(Date l_datBizDate)
        {
            return 3;
        }
    }
    
    private class WEB3GentradeMainAccountForTest extends WEB3GentradeMainAccount
    {
        public WEB3GentradeMainAccountForTest(long l_lngAccountLd) throws DataQueryException, DataNetworkException
        {
            super(l_lngAccountLd);
        }
        
        public SubAccount getSubAccount(SubAccountTypeEnum subAccountType)
            throws NotFoundException
        {
            // SubAccountTypeEnum.EQUITY_SUB_ACCOUNT
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(33381251220301L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            return new WEB3GentradeSubAccountForTest(l_subAccountParams);    
        }
        
    }

}
@

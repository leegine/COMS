head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.54.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityReceiveCloseOrderUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3EquityReceiveCloseOrderUnitServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/21 金傑（中訊）新規作成
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrder;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketCalendar;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Preferences;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityReceiveCloseOrderUnitServiceImplTest extends TestBaseForMock
{
    private WEB3EquityReceiveCloseOrderUnitServiceImpl l_service = null;
    
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3EquityReceiveCloseOrderUnitServiceImplTest.class);

    public WEB3EquityReceiveCloseOrderUnitServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        super.setUp();
        this.l_service = new WEB3EquityReceiveCloseOrderUnitServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        this.l_service = null;
        super.tearDown();
    }
    
    /**
     * <失効通知キュー.失効数量に値がセットされている場合>
     * 失効通知キュー.失効数量 < (注文単位.市場から確認済みの数量-注文単位.約定数量)
     *
     */
    public void testExecCloseOrder_C0001()
    {
        final String STR_METHOD_NAME = "testExecCloseOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            // getConfirmedQuantity
            // getExecutedQuantity
            l_eqtypeOrderUnitParams.setConfirmedQuantity(20000);
            l_eqtypeOrderUnitParams.setExecutedQuantity(5000);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams = new HostEqtypeCloseOrderNotifyParams();
            l_hostEqtypeCloseOrderNotifyParams.setCloseQuantity(8000);
            
            EqTypeOrderUnitForTest l_orderUnit = new EqTypeOrderUnitForTest();
            this.l_service.execCloseOrder(l_hostEqtypeCloseOrderNotifyParams, l_orderUnit);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisResults = l_qp.doFindAllQuery(
                EqtypeOrderUnitRow.TYPE,
                "order_unit_id = ?",
                null,
                null,
                new Object[]{new Long(3304148080001L)});
            
            assertEquals(1,l_lisResults.size());
            assertEquals(4,((EqtypeOrderUnitRow)l_lisResults.get(0)).getExpirationStatus().intValue());
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * <失効通知キュー.失効数量に値がセットされていない場合>
     * 注文単位.約定数量 < 失効通知キュー.約定数量
     */
    public void testExecCloseOrder_C0002()
    {
        final String STR_METHOD_NAME = "testExecCloseOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            // getConfirmedQuantity
            // getExecutedQuantity
            l_eqtypeOrderUnitParams.setConfirmedQuantity(20000);
            l_eqtypeOrderUnitParams.setExecutedQuantity(6000);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams = new HostEqtypeCloseOrderNotifyParams();
            l_hostEqtypeCloseOrderNotifyParams.setExecutedQuantity(7000);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", "getMarket", new Class[] { long.class },
                    new MarketForTest());
                    
            EqTypeOrderUnitForTest l_orderUnit = new EqTypeOrderUnitForTest();
            this.l_service.execCloseOrder(l_hostEqtypeCloseOrderNotifyParams, l_orderUnit);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisResults = l_qp.doFindAllQuery(
                EqtypeOrderUnitRow.TYPE,
                "order_unit_id = ?",
                null,
                null,
                new Object[]{new Long(3304148080001L)});
            
            assertEquals(1,l_lisResults.size());
            assertEquals(4,((EqtypeOrderUnitRow)l_lisResults.get(0)).getExpirationStatus().intValue());
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株式失効通知キューテーブル.失効通知区分が”失効”かつ
     * 出来通知待ちなしの場合
     * <失効通知キュー.失効数量に値がセットされている場合>
     * 失効通知キュー.失効数量 ≧ (注文単位.市場から確認済みの数量-注文単位.約定数量)
     */
    public void testExecCloseOrder_C0003()
    {
        final String STR_METHOD_NAME = "testExecCloseOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            // getConfirmedQuantity
            // getExecutedQuantity
            l_eqtypeOrderUnitParams.setConfirmedQuantity(8000);
            l_eqtypeOrderUnitParams.setExecutedQuantity(6000);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams = new HostEqtypeCloseOrderNotifyParams();
            l_hostEqtypeCloseOrderNotifyParams.setExecutedQuantity(7000);
            l_hostEqtypeCloseOrderNotifyParams.setCloseQuantity(9000);
            l_hostEqtypeCloseOrderNotifyParams.setCloseNotifyType("1");
                    
            EqTypeOrderUnitForTest l_orderUnit = new EqTypeOrderUnitForTest();
            this.l_service.execCloseOrder(l_hostEqtypeCloseOrderNotifyParams, l_orderUnit);
            fail();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityExecutedMailSenderServiceImpl",
                    "sendMailProcess", 
                    new Class[]{OrderUnit.class, String.class},
                    null);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            assertTrue(true);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            assertTrue(true);
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株式失効通知キューテーブル.失効通知区分が”失効”かつ
     * 出来通知待ちなしの場合
     * // <失効通知キュー.失効数量に値がセットされていない場合>
     * // 注文単位.約定数量 ≧ 失効通知キュー.約定数量
     */
    public void testExecCloseOrder_C0004()
    {
        final String STR_METHOD_NAME = "testExecCloseOrder_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            // getConfirmedQuantity
            // getExecutedQuantity
            l_eqtypeOrderUnitParams.setConfirmedQuantity(8000);
            l_eqtypeOrderUnitParams.setExecutedQuantity(8000);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams = new HostEqtypeCloseOrderNotifyParams();
            l_hostEqtypeCloseOrderNotifyParams.setExecutedQuantity(7000);
            l_hostEqtypeCloseOrderNotifyParams.setCloseNotifyType("1");
                    
            EqTypeOrderUnitForTest l_orderUnit = new EqTypeOrderUnitForTest();
            this.l_service.execCloseOrder(l_hostEqtypeCloseOrderNotifyParams, l_orderUnit);
            fail();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityExecutedMailSenderServiceImpl",
                    "sendMailProcess", 
                    new Class[]{OrderUnit.class, String.class},
                    null);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            assertTrue(true);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            assertTrue(true);
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    private class EqTypeOrderUnitForTest implements EqTypeOrderUnit
    {

        public EqTypeOrder getEqTypeOrder()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public long getOrderUnitId()
        {
            // TODO Auto-generated method stub
            return 3304148080001L;
        }

        public long getOrderId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getAccountId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getSubAccountId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getBranchId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getTraderId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public OrderAction[] getOrderActions()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Timestamp getReceivedTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Timestamp getExpirationTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Product getProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Order getOrder()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderExecution[] getExecutions()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderOpenStatusEnum getOrderOpenStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderTypeEnum getOrderType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderCategEnum getOrderCateg()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public TaxTypeEnum getTaxType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public SideEnum getSide()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderStatusEnum getOrderStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderExpirationStatusEnum getExpirationStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isExpired()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isFullyExecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isPartiallyExecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isUnexecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getConfirmedPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public boolean isConfirmedPriceMarketOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getConfirmedQuantity()
        {
            return 20000;
        }

        public double getQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Date getDeliveryDate()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public double getExecutedAmount()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getExecutedQuantity()
        {
            return 5000;
        }

        public double getLimitPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public boolean isMarketOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public TradedProduct getTradedProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Object getDataSourceObject()
        {
            // TODO Auto-generated method stub
            return null;
        }
        
    }
    
    private class MarketForTest implements Market
    {

        public long getMarketId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public String getMarketCode()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public String getMarketName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public String getMarketNameAlt1()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public String getMarketNameAlt2()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public MarketCalendar getMarketCalendar()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean acceptsLimitOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean acceptsMarketOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public Preferences getPreferences()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isAcceptingOrders()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getMaxNumberOfLotsPerOrder(ProductTypeEnum arg0)
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Institution getInstitution()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Object getDataSourceObject()
        {
            // TODO Auto-generated method stub
            return null;
        }
        
    }
}
@

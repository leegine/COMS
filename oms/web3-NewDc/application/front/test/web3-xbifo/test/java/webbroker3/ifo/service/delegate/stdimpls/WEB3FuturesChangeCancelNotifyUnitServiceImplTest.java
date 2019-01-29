head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.46.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesChangeCancelNotifyUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3FuturesChangeCancelNotifyUnitServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/27 金傑（中訊）新規作成
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.NewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.ifo.WEB3IfoChangeCancelNotifyUpdateInterceptor;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.base.data.RsvIfoOrderActionRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesChangeCancelNotifyUnitServiceImplTest extends TestBaseForMock
{

    private WEB3FuturesChangeCancelNotifyUnitServiceImpl l_service = null;

    private String l_strChangeCancelNotifyDivision = "1";
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3FuturesChangeCancelNotifyUnitServiceImplTest.class);

    public WEB3FuturesChangeCancelNotifyUnitServiceImplTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        this.l_service = new WEB3FuturesChangeCancelNotifyUnitServiceImpl();
    }
    
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    /**
     * is予約注文確認要=false
     *
     */
    public void testNotifyCancel_C0001()
    {
        final String STR_METHOD_NAME = "testNotifyCancel_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            // 予約注文の設定
            l_ifoOrderUnitParams.setReserveOrderExistFlag("0");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getRsvIfoOrderUnitRow());
            
            ProcessingResult l_result = ProcessingResult.newSuccessResultInstance();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoMarketResponseReceiverCallbackServiceImpl",
                    "process",
                    new Class[] {NewOrderAcceptedMarketResponseMessage.class},
                    l_result);
            
            l_strChangeCancelNotifyDivision = "3";
            OrderUnit l_orderUnit = new OrderUnitForTest();
            WEB3IfoChangeCancelNotifyUpdateInterceptorForTest l_ifoChangeCancelNotifyInterceptor = 
                new WEB3IfoChangeCancelNotifyUpdateInterceptorForTest();
            
            l_ifoChangeCancelNotifyInterceptor.setChangeCancelNotifyDivision();
            
            String l_notifyCancel = this.l_service.notifyCancel(l_orderUnit, l_ifoChangeCancelNotifyInterceptor);
            
            assertEquals("1",l_notifyCancel);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            List l_lisRsvIfoOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    RsvIfoOrderUnitRow.TYPE,
                    " parent_order_id = ?",
                    new Object[]{new Long(1001)});
            // setOrderOpenStatus
            assertEquals(OrderOpenStatusEnum.OPEN,((RsvIfoOrderUnitRow)l_lisRsvIfoOrderUnitRows.get(0)).getOrderOpenStatus());
            
            List l_lisRsvIfoOrderActionRows =
                l_queryProcessor.doFindAllQuery(
                    RsvIfoOrderActionRow.TYPE,
                    "order_id = ?",
                    new Object[]{new Long(1001)});
            
            assertEquals(0,l_lisRsvIfoOrderActionRows.size());
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * is予約注文確認要=true
     *
     */
    public void testNotifyCancel_C0002()
    {
        final String STR_METHOD_NAME = "testNotifyCancel_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            // 予約注文の設定
            l_ifoOrderUnitParams.setReserveOrderExistFlag("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getRsvIfoOrderUnitRow());
            
            ProcessingResult l_result = ProcessingResult.newSuccessResultInstance();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoMarketResponseReceiverCallbackServiceImpl",
                    "process",
                    new Class[] {NewOrderAcceptedMarketResponseMessage.class},
                    l_result);
            
            l_strChangeCancelNotifyDivision = "3";
            OrderUnit l_orderUnit = new OrderUnitForTest();
            WEB3IfoChangeCancelNotifyUpdateInterceptorForTest l_ifoChangeCancelNotifyInterceptor = 
                new WEB3IfoChangeCancelNotifyUpdateInterceptorForTest();
            
            l_ifoChangeCancelNotifyInterceptor.setChangeCancelNotifyDivision();
            
            String l_notifyCancel = this.l_service.notifyCancel(l_orderUnit, l_ifoChangeCancelNotifyInterceptor);
            
            assertEquals("1",l_notifyCancel);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            List l_lisRsvIfoOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    RsvIfoOrderUnitRow.TYPE,
                    " parent_order_id = ?",
                    new Object[]{new Long(1001)});
            // setOrderOpenStatus
            assertEquals(OrderOpenStatusEnum.CLOSED,((RsvIfoOrderUnitRow)l_lisRsvIfoOrderUnitRows.get(0)).getOrderOpenStatus());
            
            List l_lisRsvIfoOrderActionRows =
                l_queryProcessor.doFindAllQuery(
                    RsvIfoOrderActionRow.TYPE,
                    "order_id = ?",
                    new Object[]{new Long(1001)});
            
            assertEquals(1001,((RsvIfoOrderActionRow)l_lisRsvIfoOrderActionRows.get(0)).getOrderId(),0);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class OrderUnitForTest implements OrderUnit
    {

        public long getOrderUnitId()
        {
            // TODO Auto-generated method stub
            return 1001;
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
            // TODO Auto-generated method stub
            return 0;
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
            // TODO Auto-generated method stub
            return 0;
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
    
    private class WEB3IfoChangeCancelNotifyUpdateInterceptorForTest extends WEB3IfoChangeCancelNotifyUpdateInterceptor
    {
        public void setChangeCancelNotifyDivision()
        {
           super.setChangeCancelNotifyDivision(l_strChangeCancelNotifyDivision);
        }
    }

}
@

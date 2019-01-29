head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.45.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionChangeCancelNotifyUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3OptionChangeCancelNotifyUnitServiceImplForTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/25 劉剣（中訊）新規作成
*/
package webbroker3.ifo.service.delegate.stdimpls;


import java.sql.Timestamp;
import java.util.Date;

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
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.CancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifo.WEB3IfoChangeCancelNotifyUpdateInterceptor;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionChangeCancelNotifyUnitServiceImplTest extends TestBaseForMock
{

    private WEB3OptionChangeCancelNotifyUnitServiceImpl l_service = null;
    private WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyInterceptor = null;
    private OrderUnit l_orderUnit = null;
    private boolean l_bln = true;
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3OptionCancelOrderServiceImplTest.class);

    public WEB3OptionChangeCancelNotifyUnitServiceImplTest(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_service = new WEB3OptionChangeCancelNotifyUnitServiceImpl();
        this.l_ifoChangeCancelNotifyInterceptor = new WEB3IfoChangeCancelNotifyUpdateInterceptorForTest();
        this.l_orderUnit = new OrderUnitForTest();

    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testNotifyCancel_C0001()
    {
        final String STR_METHOD_NAME = "testnotifyCancel_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoMarketResponseReceiverCallbackServiceImpl",
                "process",
                new Class[] {CancelOrderAcceptedMarketResponseMessage.class},
                ProcessingResult.newSuccessResultInstance());

            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel( TestDBUtility.getIfoOrderUnitRow());
            l_bln = false;

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getSubAccount",
                    new Class[] { long.class, long.class },
                    new WEB3GentradeSubAccount(TestDBUtility.getSubAccountRow()));
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            String l_str = this.l_service.notifyCancel(l_orderUnit,this.l_ifoChangeCancelNotifyInterceptor);
            assertTrue(true);
  
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public void testNotifyCancel_C0002()
    {
        final String STR_METHOD_NAME = "testnotifyCancel_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoMarketResponseReceiverCallbackServiceImpl",
                "process",
                new Class[] {CancelOrderAcceptedMarketResponseMessage.class},
                ProcessingResult.newSuccessResultInstance());

            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel( TestDBUtility.getIfoOrderUnitRow());
            l_bln = true;

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getSubAccount",
                    new Class[] { long.class, long.class },
                    new WEB3GentradeSubAccount(TestDBUtility.getSubAccountRow()));

            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            String l_str = this.l_service.notifyCancel(l_orderUnit,this.l_ifoChangeCancelNotifyInterceptor);
            assertTrue(true);
  
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    private class WEB3IfoChangeCancelNotifyUpdateInterceptorForTest extends WEB3IfoChangeCancelNotifyUpdateInterceptor
    {
        public String getChangeCancelNotifyDivision()
        {
            return "3";
        }
    }

    private class OrderUnitForTest implements IfoOrderUnit
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
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            if(l_bln)
            {
                
                l_ifoOrderUnitParams.setReserveOrderExistFlag("0");

            }
            else
            {
                l_ifoOrderUnitParams.setReserveOrderExistFlag("1");

            }
            return l_ifoOrderUnitParams;
        }

        public ProductTypeEnum getProductType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isFuturesOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isOptionsOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isCallOptionsOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isPutOptionsOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isOpenContractOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isSettleContractOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public IfoOrderExecutionConditionType getExecutionConditionType()
        {
            // TODO Auto-generated method stub
            return null;
        }
    }

}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.47.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoCloseNotifyUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3IfoCloseNotifyUnitServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/28 金傑（中訊）新規作成
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.UndoOrderInvalidatedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchForMock;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMainAccountForMock;
import webbroker3.gentrade.WEB3GentradeSubAccountForMock;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.base.data.RsvIfoOrderActionRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoCloseNotifyUnitServiceImplTest extends TestBaseForMock
{
    private WEB3IfoCloseNotifyUnitServiceImpl l_service = null;

    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoCloseNotifyUnitServiceImplTest.class);
    
    public WEB3IfoCloseNotifyUnitServiceImplTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        l_service = new WEB3IfoCloseNotifyUnitServiceImpl();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        this.l_service = null;
        super.tearDown();
    }
    
    /**
     * is予約注文確認要 = false
     *
     */
    public void testNotifyClose_C0001()
    {
        final String STR_METHOD_NAME = "testValidateOrderAcceptC001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try      
        {
//            OrderUnit l_orderUnit, 
//            double l_dblExecutionQuantity, 
//            String l_strCloseReasonCode, 
//            String l_strCloseNotifyType
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setReserveOrderExistFlag("0");
            l_ifoOrderUnitParams.setExecutedQuantity(7000);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getRsvIfoOrderUnitRow());
            IfoOrderParams l_ifoOrderparams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderparams.setOrderId(1001);
            TestDBUtility.insertWithDel(l_ifoOrderparams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoExecutedMailSendServiceImpl", 
                    "sendMailProcess",
                    new Class[]
                    { OrderUnit.class, String.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoMarketResponseReceiverCallbackServiceImpl",
                    "process",
                    new Class[] {UndoOrderInvalidatedMarketResponseMessage.class},
                    ProcessingResult.newSuccessResultInstance());
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", "getSubAccount",
                    new Class[] { long.class, long.class },
                    new WEB3GentradeSubAccountForMock(l_subAccountParams));
            
            MainAccountParams l_map = TestDBUtility.getMainAccountRow();
            l_map.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_map);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("387");
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3GentradeBranch l_branch = new WEB3GentradeBranchForMock(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", 
                    new Class[] {},
                    l_branch);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams =  TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccountForMock(101001010010L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount", 
                    "getMainAccount", 
                    new Class[]{},
                    l_mainAccount);
            
            MarketParams l_marketP = TestDBUtility.getMarketRow();
            l_marketP.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketP);
            
            String l_strResult = l_service.notifyClose(new IfoOrderUnitForTest(),2000,"12345","1");
            assertEquals("1",l_strResult);
            
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
        catch (WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();

        }
    }
    
    /**
     * is予約注文確認要 = true
     *
     */
    public void testNotifyClose_C0002()
    {
        final String STR_METHOD_NAME = "testValidateOrderAcceptC002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try      
        {
//            OrderUnit l_orderUnit, 
//            double l_dblExecutionQuantity, 
//            String l_strCloseReasonCode, 
//            String l_strCloseNotifyType
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setReserveOrderExistFlag("1");
            l_ifoOrderUnitParams.setExecutedQuantity(7000);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getRsvIfoOrderUnitRow());
            IfoOrderParams l_ifoOrderparams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderparams.setOrderId(1001);
            TestDBUtility.insertWithDel(l_ifoOrderparams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoExecutedMailSendServiceImpl", 
                    "sendMailProcess",
                    new Class[]
                    { OrderUnit.class, String.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoMarketResponseReceiverCallbackServiceImpl",
                    "process",
                    new Class[] {UndoOrderInvalidatedMarketResponseMessage.class},
                    ProcessingResult.newSuccessResultInstance());
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", "getSubAccount",
                    new Class[] { long.class, long.class },
                    new WEB3GentradeSubAccountForMock(l_subAccountParams));
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccountForMock(101001010010L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount", 
                    "getMainAccount", 
                    new Class[]{},
                    l_mainAccount);
            String l_strResult = l_service.notifyClose(new IfoOrderUnitForTest(),2000,"12345","1");
            assertEquals("1",l_strResult);
            
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
        catch (WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    private class IfoOrderUnitForTest implements IfoOrderUnit
    {

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

}
@

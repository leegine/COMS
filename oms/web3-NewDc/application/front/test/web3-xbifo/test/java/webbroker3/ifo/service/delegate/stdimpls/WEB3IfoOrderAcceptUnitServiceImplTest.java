head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.48.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoOrderAcceptUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP注文受付UnitServiceImplテスト(WEB3IfoOrderAcceptUnitServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/02 周捷 (中訊) 新規作成 仕様変更 モデル605
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.NewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.NewOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeSubAccountForMock;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.data.HostFotypeOrderAcceptParams;
import webbroker3.ifo.data.HostFotypeOrderAllDao;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.ifo.service.delegate.WEB3IfoOrderAcceptUnitService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.base.data.RsvIfoOrderActionRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （先物OP注文受付UnitServiceImplテスト）<BR>
 *
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3IfoOrderAcceptUnitServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3IfoChangeCancelOrderAcceptNormalTransactionCallbackTest.class);

    public WEB3IfoOrderAcceptUnitServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(false);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    WEB3IfoOrderAcceptUnitService service = null;
    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptUnitServiceImpl.notifyOrderAcceptOvertime(HostFotypeOrderAcceptParams)'
     */
    public void testNotifyOrderAcceptOvertime1()
    {
        final String STR_METHOD_NAME = "testNotifyOrderAcceptOvertime1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        HostFotypeOrderAcceptParams l_params = null;

        try
        {
            service =
                (WEB3IfoOrderAcceptUnitService)Services.getService(WEB3IfoOrderAcceptUnitService.class);
            service.notifyOrderAcceptOvertime(l_params);
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testNotifyOrderAcceptOvertime2()
    {
        final String STR_METHOD_NAME = "testNotifyOrderAcceptOvertime2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        HostFotypeOrderAcceptParams l_params = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        try
        {
            l_params = new HostFotypeOrderAcceptParams();
            l_params.setRequestCode("EI80A");
            l_params.setInstitutionCode("");
            l_params.setBranchCode("");
            l_params.setAccountCode("");
            l_params.setTraderCode("");
            l_params.setOrderRequestNumber("111");
            l_params.setAcceptStatus("1");
            l_params.setStatus("");
            l_params.setSubmitOrderRouteDiv("");
            TestDBUtility.insertWithDelAndCommit(l_params);

            service =
                (WEB3IfoOrderAcceptUnitService)Services.getService(WEB3IfoOrderAcceptUnitService.class);
            service.notifyOrderAcceptOvertime(l_params);
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testNotifyOrderAcceptOvertime3()
    {
        final String STR_METHOD_NAME = "testNotifyOrderAcceptOvertime3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        HostFotypeOrderAcceptParams l_params = null;
        IfoOrderUnitParams l_unitParams = null;
        HostFotypeOrderAllParams l_hostAllParams = null;
        BranchParams l_branchRarams = TestDBUtility.getBranchRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        try
        {
            l_params = new HostFotypeOrderAcceptParams();
            l_params.setRequestCode("EI80A");
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("1010010");
            l_params.setTraderCode("10011");
            l_params.setOrderRequestNumber("000003006");
            l_params.setAcceptStatus("1");
            l_params.setErrorMessage("1001");
            l_params.setStatus("1");
            l_params.setSubmitOrderRouteDiv("");
            l_params.setCreatedTimestamp(new Date("2004/01/01"));
            l_params.setLastUpdatedTimestamp(new Date("2004/01/01"));
            TestDBUtility.insertWithDelAndCommit(l_params);

            l_unitParams = this.ifoOrderUnit();
            TestDBUtility.insertWithDelAndCommit(l_unitParams);
            
            l_hostAllParams = this.hostFotypeOrderAll();
            TestDBUtility.insertWithDelAndCommit(l_hostAllParams);
            
            TestDBUtility.insertWithDelAndCommit(l_branchRarams);
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);        

            service =
                (WEB3IfoOrderAcceptUnitService)Services.getService(WEB3IfoOrderAcceptUnitService.class);
            service.notifyOrderAcceptOvertime(l_params);
            
            IfoOrderUnitRow l_unitRow =
                (IfoOrderUnitRow)IfoOrderUnitDao.findRowByOrderUnitId(1001);
            List l_lis =
                HostFotypeOrderAllDao.findRowsByAccountIdOrderRequestNumber(new Long(101001010010L), "000003006");
            HostFotypeOrderAllRow l_hostAllRow =
                (HostFotypeOrderAllRow)l_lis.get(0);

            assertEquals("03", l_unitRow.getConfirmedOrderRev());
            assertEquals("02", l_unitRow.getOrderRev());
            assertEquals("0", l_hostAllRow.getStatus());
            String l_date = WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getSystemTimestamp(), "yyyyMMdd");
            assertEquals(l_date, WEB3DateUtility.formatDate(l_unitRow.getLastUpdatedTimestamp(), "yyyyMMdd"));
            
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * is予約注文確認要 = false
     *
     */
    public void testNotifyOrderAccept_C0001()
    {
        final String STR_METHOD_NAME = "testNotifyOrderAccept_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // RsvIfoOrderActionRow
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getRsvIfoOrderUnitRow());
            
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getOrderUnit", new Class[]
                    { String.class, String.class, ProductTypeEnum.class, String.class },
                    new OrderUnitForTest());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getSubAccount",
                    new Class[] { long.class, long.class },
                    new WEB3GentradeSubAccountForMock(TestDBUtility.getSubAccountRow()));
            
            ProcessingResult l_result = ProcessingResult.newSuccessResultInstance();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoMarketResponseReceiverCallbackServiceImpl",
                    "process",
                    new Class[] {NewOrderAcceptedMarketResponseMessage.class},
                    l_result);
            
            HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams = new HostFotypeOrderAcceptParams();
            l_hostFotypeOrderAcceptParams.setAcceptStatus("1");
            WEB3IfoOrderAcceptUnitServiceImpl l_service = new WEB3IfoOrderAcceptUnitServiceImpl();
            l_service.notifyOrderAccept(l_hostFotypeOrderAcceptParams);
            assertTrue(true);
            
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
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * is予約注文確認要 = true
     *
     */
    public void testNotifyOrderAccept_C0002()
    {
        final String STR_METHOD_NAME = "testNotifyOrderAccept_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // RsvIfoOrderActionRow
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getRsvIfoOrderUnitRow());
            
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getOrderUnit", new Class[]
                    { String.class, String.class, ProductTypeEnum.class, String.class },
                    new OrderUnitForTest());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getSubAccount",
                    new Class[] { long.class, long.class },
                    new WEB3GentradeSubAccountForMock(TestDBUtility.getSubAccountRow()));
            
            ProcessingResult l_result = ProcessingResult.newSuccessResultInstance();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoMarketResponseReceiverCallbackServiceImpl",
                    "process",
                    new Class[] {NewOrderRejectedMarketResponseMessage.class},
                    l_result);
            
            HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams = new HostFotypeOrderAcceptParams();
            l_hostFotypeOrderAcceptParams.setAcceptStatus("0");
            WEB3IfoOrderAcceptUnitServiceImpl l_service = new WEB3IfoOrderAcceptUnitServiceImpl();
            IfoOrderParams l_ifoOrderparams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderparams.setOrderId(1001);
            TestDBUtility.insertWithDel(l_ifoOrderparams);
            l_service.notifyOrderAccept(l_hostFotypeOrderAcceptParams);
            assertTrue(true);
            
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
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public IfoOrderUnitParams ifoOrderUnit()
    {
        IfoOrderUnitParams l_params = new IfoOrderUnitParams();
        l_params.setOrderUnitId(1001);
        l_params.setAccountId(101001010010L);
        l_params.setSubAccountId(10100101001007L);
        l_params.setBranchId(33381);
        l_params.setTraderId(null);
        l_params.setOrderId(1001);
        l_params.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
        l_params.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
        l_params.setLastOrderActionSerialNo(1);
        l_params.setLastExecutionSerialNo(0);
        l_params.setProductType(ProductTypeEnum.IFO);
        l_params.setFutureOptionDiv("1");
        l_params.setMarketId(1002);
        l_params.setQuantity(100);
        l_params.setLimitPrice(0);
        l_params.setExecutionConditionType(IfoOrderExecutionConditionType.NONE);
        l_params.setOrderConditionType("0");
        l_params.setOrderCondOperator(null);
        l_params.setStopPriceType(null);
        l_params.setStopOrderPrice(null);
        l_params.setWLimitPrice(null);
        l_params.setDeliveryDate(new Date("2004/01/01"));
        l_params.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_params.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        l_params.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_params.setTaxType(TaxTypeEnum.NORMAL);
        l_params.setBizDate("20040101");
        l_params.setProductId(1006169090018L);
        l_params.setCreatedTimestamp(new Date("2004/01/01"));
        l_params.setLastUpdatedTimestamp(new Date("2004/01/02"));
        l_params.setOrderRequestNumber("000003006");
        l_params.setConfirmedOrderRev("2");
        l_params.setOrderRev("1");
        return l_params;
    }
    public HostFotypeOrderAllParams hostFotypeOrderAll()
    {
        HostFotypeOrderAllParams l_params =new HostFotypeOrderAllParams();
        l_params.setAccountId(101001010010L);
        l_params.setOrderRequestNumber("000003006");
        l_params.setSubmitOrderRouteDiv("0");
        l_params.setCancelDiv("0");
        l_params.setCorpCode("3338160000030061999");
        l_params.setAllOrderChangeDiv("0");
        return l_params;
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
            return 1001;
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
            WEB3IfoProductImpl l_product = null;
            try
            {
                TestDBUtility.deleteAll(ProductRow.TYPE);
                TestDBUtility.insertWithDel(TestDBUtility.getProductRow());
                
                IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
                l_ifoProductParams.setProductId(3304148080000L);
                TestDBUtility.insertWithDel(l_ifoProductParams);
                
                l_product = new WEB3IfoProductImpl(3304148080000L);
            }
            catch(Exception l_ex)
            {
                fail();
            }            
            return l_product;
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
            // IfoOrderUnitRow
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setReserveOrderExistFlag("1");
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

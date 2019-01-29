head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.44.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoDesignateEmbeddedNotifyServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ToSuccIfoOrderUnitServiceInterceptorTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/25 劉剣（中訊）新規作成
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoClosingContractSpecImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.data.HostFotypeClosingContSpecRow;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoDesignateEmbeddedNotifyServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoDesignateEmbeddedNotifyServiceImplTest.class);
    
    private WEB3IfoDesignateEmbeddedNotifyServiceImpl l_impl = null;
    private boolean l_bln1 = true;
    private boolean l_bln2 = true;
    private int l_int = 0;

    public WEB3IfoDesignateEmbeddedNotifyServiceImplTest(String arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_impl = new WEB3IfoDesignateEmbeddedNotifyServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    /*
     * 注文単位オブジェクト = null
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testCreateDesignateEmbeddedNotify_C0001()
    {
        final String STR_METHOD_NAME = "testCreateDesignateEmbeddedNotify_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            OrderUnit l_orderUnit = null;
            
            this.l_impl.createDesignateEmbeddedNotify(l_orderUnit);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 返済注文でない場合
     */
    public void testCreateDesignateEmbeddedNotify_C0002()
    {
        final String STR_METHOD_NAME = "testCreateDesignateEmbeddedNotify_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_bln1 = true;
            
            OrderUnit l_orderUnit = new OrderUnitForTest();

            this.l_impl.createDesignateEmbeddedNotify(l_orderUnit);

            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * lデータ不整合エラー。
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80006
     */
    public void testCreateDesignateEmbeddedNotify_C0003()
    {
        final String STR_METHOD_NAME = "testCreateDesignateEmbeddedNotify_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_bln1 = false;
            
            OrderUnit l_orderUnit = new OrderUnitForTest();
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            
            this.l_impl.createDesignateEmbeddedNotify(l_orderUnit);

            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }
    /*
     * l_orderUnitRow.getFutureOptionDiv() = OPTION(2)
     * l_productRow.getDerivativeType() = CALL_OPTIONS
     */
    public void testCreateDesignateEmbeddedNotify_C0004()
    {
        final String STR_METHOD_NAME = "testCreateDesignateEmbeddedNotify_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
 
        try
        {
            l_bln1 = false;
            l_bln2 = true;
            l_int = 0;

            OrderUnit l_orderUnit = new OrderUnitForTest();
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractPrice(0002);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(HostFotypeClosingContSpecRow.TYPE);
            
            this.l_impl.createDesignateEmbeddedNotify(l_orderUnit);
            
            HostFotypeClosingContSpecRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "institution_code = ? ";
            Object[] l_objWhere = {"0D"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    HostFotypeClosingContSpecRow.TYPE,
                    l_strWhere,
                    l_objWhere);

            l_row = (HostFotypeClosingContSpecRow)l_lstRecords.get(0);
            
            assertEquals("EI806", l_row.getRequestCode());
            assertEquals(WEB3IfoProductTypeDef.CALL_OPTIONS, l_row.getFutureOptionProductType());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * l_orderUnitRow.getFutureOptionDiv() = FUTURES(1)
     * l_productRow.getDerivativeType() = PUT_OPTIONS
     */
    public void testCreateDesignateEmbeddedNotify_C0005()
    {
        final String STR_METHOD_NAME = "testCreateDesignateEmbeddedNotify_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
 
        try
        {
            l_bln1 = false;
            l_bln2 = false;
            l_int = 1;

            OrderUnit l_orderUnit = new OrderUnitForTest();
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractPrice(0002);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(HostFotypeClosingContSpecRow.TYPE);
            
            this.l_impl.createDesignateEmbeddedNotify(l_orderUnit);
            
            HostFotypeClosingContSpecRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "institution_code = ? ";
            Object[] l_objWhere = {"0D"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    HostFotypeClosingContSpecRow.TYPE,
                    l_strWhere,
                    l_objWhere);

            l_row = (HostFotypeClosingContSpecRow)l_lstRecords.get(0);
            
            assertEquals("EI807", l_row.getRequestCode());
            assertEquals(WEB3IfoProductTypeDef.PUT_OPTIONS, l_row.getFutureOptionProductType());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }
    /*
     * l_orderUnitRow.getFutureOptionDiv() = FUTURES(1)
     * l_productRow.getDerivativeType() = FUTURES
     */
    public void testCreateDesignateEmbeddedNotify_C0006()
    {
        final String STR_METHOD_NAME = "testCreateDesignateEmbeddedNotify_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
     
        try
        {
            l_bln1 = false;
            l_bln2 = false;
            l_int = 2;

            OrderUnit l_orderUnit = new OrderUnitForTest();
                
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
                
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
                
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
                
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
                
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractPrice(0002);
            TestDBUtility.insertWithDel(l_ifoContractParams);
                
            TestDBUtility.deleteAll(HostFotypeClosingContSpecRow.TYPE);
                
            this.l_impl.createDesignateEmbeddedNotify(l_orderUnit);
                
            HostFotypeClosingContSpecRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "institution_code = ? ";
            Object[] l_objWhere = {"0D"};
            List l_lstRecords = l_processor.doFindAllQuery(
                HostFotypeClosingContSpecRow.TYPE,
                l_strWhere,
                l_objWhere);

            l_row = (HostFotypeClosingContSpecRow)l_lstRecords.get(0);
                
            assertEquals("EI807", l_row.getRequestCode());
            assertEquals(WEB3IfoProductTypeDef.FUTURES, l_row.getFutureOptionProductType());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * データ不整合エラー。
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80006
     */
    public void testUndoDesignateEmbeddedNotify_C0001()
    {
        final String STR_METHOD_NAME = "testUndoDesignateEmbeddedNotify_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
         
        try
        {
            OrderUnit l_orderUnit = new OrderUnitForTest();
                    
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001);
            TestDBUtility.insertWithDel(l_branchParams);
                    
            this.l_impl.undoDesignateEmbeddedNotify(l_orderUnit);
                    
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testUndoDesignateEmbeddedNotify_C0002()
    {
        final String STR_METHOD_NAME = "testUndoDesignateEmbeddedNotify_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
         
        try
        {
            l_bln1 = false;
            l_bln2 = true;
            l_int = 0;

            OrderUnit l_orderUnit = new OrderUnitForTest();
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractPrice(0002);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(HostFotypeClosingContSpecRow.TYPE);
            
            this.l_impl.createDesignateEmbeddedNotify(l_orderUnit);
            this.l_impl.undoDesignateEmbeddedNotify(l_orderUnit);
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "request_code = ? ";
            Object[] l_objWhere = {"EI806"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    HostFotypeClosingContSpecRow.TYPE,
                    l_strWhere,
                    l_objWhere);
            
            assertEquals(0, l_lstRecords.size());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private class IfoClosingContractSpecImplForTest extends IfoClosingContractSpecImpl
    {

        protected IfoClosingContractSpecImplForTest(IfoClosingContractSpecRow arg0)
        {
            super(arg0);
            // TODO Auto-generated constructor stub
        }
        public long getContractId()
        {
            return 1001;
        }
        public double getQuantity()
        {
            return 0003;
        }
        
    }
    
    private class OrderUnitForTest implements IfoContractSettleOrderUnit
    {

        public IfoClosingContractSpec[] getContractsToClose()
        {
            IfoClosingContractSpec[] l_closingContractSpecs = new IfoClosingContractSpec[1];
            l_closingContractSpecs[0] = new IfoClosingContractSpecImplForTest(null);

            return l_closingContractSpecs;
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

        public long getOrderUnitId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getOrderId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getAccountId()
        {
            // TODO Auto-generated method stub
            return 333812512246L;
        }

        public long getSubAccountId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getBranchId()
        {
            // TODO Auto-generated method stub
            return 33381;
        }

        public long getTraderId()
        {
            // TODO Auto-generated method stub
            return 3338111123L;
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
            WEB3IfoProductImpl l_product = null;
            try
            {
                TestDBUtility.deleteAll(ProductRow.TYPE);
                TestDBUtility.insertWithDel(TestDBUtility.getProductRow());
                
                IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
                l_ifoProductParams.setProductId(3304148080000L);
                l_ifoProductParams.setStrikePrice(0001);
                
                switch (l_int)
                {
                    case 0:
                        l_ifoProductParams.setDerivativeType(new IfoDerivativeTypeEnum(2, "CALL_OPTIONS"));
                        break;
                        
                    case 1:
                        l_ifoProductParams.setDerivativeType(new IfoDerivativeTypeEnum(3, "PUT_OPTIONS"));
                        break;
                        
                    case 2:
                        l_ifoProductParams.setDerivativeType(new IfoDerivativeTypeEnum(1, "FUTURES"));
                        break;
                }
                
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
            OrderCategEnum l_orderTypeEnum = null;

            if (l_bln1)
            {
                l_orderTypeEnum = null;
            }
            else
            {
                l_orderTypeEnum = new OrderCategEnum(5, "CLOSE_MARGIN");
            }
            return l_orderTypeEnum;
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
            IfoOrderUnitParams l_ifoOrderUnitParams = null;
            
            try
            {
                TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
                l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
                
                if (l_bln2)
                {
                    l_ifoOrderUnitParams.setFutureOptionDiv("2");
                }
                else
                {
                    l_ifoOrderUnitParams.setFutureOptionDiv("1");
                }

                TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            }
            catch (WEB3BaseException l_ex)
            {
                fail();
            }
            
            return l_ifoOrderUnitParams;
        }

    }
}
@

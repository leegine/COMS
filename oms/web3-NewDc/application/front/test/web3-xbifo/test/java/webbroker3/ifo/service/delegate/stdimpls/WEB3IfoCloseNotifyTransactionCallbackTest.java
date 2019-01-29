head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.48.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoCloseNotifyTransactionCallbackTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3IfoCloseNotifyTransactionCallbackTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/20 金傑（中訊）新規作成
 */
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.ifo.data.HostFotypeCloseOrderNotifyParams;
import webbroker3.ifo.data.HostFotypeCloseOrderNotifyRow;
import webbroker3.ifo.message.WEB3IfoCloseOrderRequest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3AsynIfoCloseNotifyServiceImpl.WEB3IfoCloseNotifyTransactionCallback;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoCloseNotifyTransactionCallbackTest extends TestBaseForMock
{
    private WEB3AsynIfoCloseNotifyServiceImpl l_notifyService = null;

    private WEB3IfoCloseOrderRequest l_request = null;

    private WEB3IfoCloseNotifyTransactionCallback l_callBack = null;
    
    private boolean l_blnIsCloseQuantity = true;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoCloseNotifyTransactionCallbackTest.class);

    public WEB3IfoCloseNotifyTransactionCallbackTest(String l_strName)
    {
        super(l_strName);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_request = new WEB3IfoCloseOrderRequest();
        this.l_notifyService = new WEB3AsynIfoCloseNotifyServiceImpl(this.l_request);
        this.l_callBack = this.l_notifyService.new WEB3IfoCloseNotifyTransactionCallback();

    }

    protected void tearDown() throws Exception
    {
        this.l_request = null;
        this.l_notifyService = null;
        super.tearDown();
    }

    /**
     * 失効通知キュー.失効数量 ! = NULL の場合
     * 約定数量 = 注文単位.市場から確認済みの数量-失効通知キュー.失効数量
     *
     */
    public void testProcess_C0001()
    {
        final String STR_METHOD_NAME = "testProcess_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
                    
            this.initData();
            this.setMockData();
            
            this.l_callBack.process();
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_listRecords = l_queryProcessor.doFindAllQuery(
                    HostFotypeCloseOrderNotifyRow.TYPE,
                    "request_code = ?",
                    null,
                    null,
                    new Object[]{"EI813"}) ;
            assertEquals(1,l_listRecords.size());
            assertEquals(20000,((HostFotypeCloseOrderNotifyRow)l_listRecords.get(0)).getCloseQuantity(),0);

        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);

    }
    
    /**
     * 失効通知キュー.失効数量 == NULL の場合
     * 先物OP失効通知キューテーブル.約定数量
     *
     */
    public void testProcess_C0002()
    {
        final String STR_METHOD_NAME = "testProcess_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_blnIsCloseQuantity = false;        
            this.initData();
            this.setMockData();
            
            this.l_callBack.process();
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_listRecords = l_queryProcessor.doFindAllQuery(
                    HostFotypeCloseOrderNotifyRow.TYPE,
                    "request_code = ?",
                    null,
                    null,
                    new Object[]{"EI813"}) ;
            assertEquals(1,l_listRecords.size());
            assertEquals(0,((HostFotypeCloseOrderNotifyRow)l_listRecords.get(0)).getCloseQuantity(),0);

        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
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
            TestDBUtility.deleteAll(HostFotypeCloseOrderNotifyRow.TYPE);
            HostFotypeCloseOrderNotifyParams l_params1 = new HostFotypeCloseOrderNotifyParams();
            l_params1.setRequestCode("EI813");
            l_params1.setStatus("0");
            l_params1.setInstitutionCode("0D");
            l_params1.setBranchCode("381");
            l_params1.setAccountCode("2512246");
            l_params1.setAcceptNumber("12345678901234567890");
            l_params1.setProductCode("1234567890");
            l_params1.setBuySellDiv("1");
            l_params1.setOrderRequestNumber("12345");
            l_params1.setCloseNotifyType("2");
            if(l_blnIsCloseQuantity)
            {
                l_params1.setCloseQuantity(20000);
            }
            l_params1.setAccountCode("123456");
            l_params1.setTraderCode("32142");
            l_params1.setReasonCode("1");
            l_params1.setErrorMessage("err");
            l_params1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_params1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_params1.setVirtualServerNumberMarket("01");
            l_params1.setNoticeType("02");
            l_params1.setNoticeNumber(5678);
            l_params1.setExecutedQuantity(5000);
            TestDBUtility.insertWithDel(l_params1);
//            TestDBUtility.commit();
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1001);
            l_ifoOrderUnitParams.setConfirmedQuantity(5000);
            l_ifoOrderUnitParams.setConfirmedQuantity(0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            // EXPIRATION_DATE_TYPE
            l_ifoOrderUnitParams.setExpirationDateType("1");
            l_ifoOrderUnitParams.setAcceptNumber("12345678901234567890");
            l_ifoOrderUnitParams.setProductCode("1234567890");
            l_ifoOrderUnitParams.setBuySellDiv("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            l_mainAccountParams.setIfoAccOpenDivNagoya("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_BranchParams);
            
            TestDBUtility.commit();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    private void setMockData()
    {
        final String STR_METHOD_NAME = "setMockData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getOrderUnit", new Class[]
                    { String.class, String.class, ProductTypeEnum.class, String.class },
                    new OrderUnitImpl());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl",
                    "notifyClose",
                    new Class[] 
                   {OrderUnit.class,double.class,String.class,String.class},
                            "0");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrderUnit", 
                    new Class[]{long.class},
                    new OrderUnitImpl());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    private class OrderUnitImpl implements IfoOrderUnit
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
            return 28000;
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
            // IfoOrderUnitRow
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(5000);
            // l_orderUnitRow.getOrderStatus() CANCELLED
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
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

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.45.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AsynFuturesOrderExecNotifyServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3AsynFuturesOrderExecNotifyServiceImplテスト
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/04/26 孟亜南 (中訊) 新規作成
 */
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractSettleOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.ifo.data.HostOptionOrderExecNotifyParams;
import webbroker3.ifo.data.HostOptionOrderExecNotifyRow;
import webbroker3.ifo.message.WEB3FuturesOrderExecNotifyRequest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3AsynFuturesOrderExecNotifyServiceImpl.WEB3FuturesOrderExecNotifyTransactionCallback;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;

public class WEB3AsynFuturesOrderExecNotifyServiceImplTest extends TestBaseForMock
{

    public WEB3AsynFuturesOrderExecNotifyServiceImplTest(String name) 
    {
        super(name);
    }

    public void test_process_0001()
    {
        WEB3FuturesOrderExecNotifyRequest l_request = new WEB3FuturesOrderExecNotifyRequest();
        WEB3AsynFuturesOrderExecNotifyServiceImpl l_asynFutures =  new WEB3AsynFuturesOrderExecNotifyServiceImpl(l_request);
        
        WEB3FuturesOrderExecNotifyTransactionCallback l_callback = l_asynFutures.new WEB3FuturesOrderExecNotifyTransactionCallback();
        String[]
               l_orderRequestNumberPrefixGroup = {"1"};
        l_callback.setOrderRequestNumberPrefixGroup(l_orderRequestNumberPrefixGroup);
        try
        {
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = this.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(0D);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            IfoContractSettleOrderUnitImpl l_ifoContract = new  IfoContractSettleOrderUnitImpl(1001);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "getOrderUnit",
                new Class[] {String.class, String.class, ProductTypeEnum.class, String.class},
                l_ifoContract);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "getOrderUnit",
                new Class[] {long.class},
                l_ifoContract);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderExecNotifyUnitServiceImpl",
                "notifyExecute",
                new Class[] {OrderUnit.class,Timestamp.class,double.class,double.class,String.class},
                new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01975,""));
            
            TestDBUtility.deleteAllAndCommit(HostOptionOrderExecNotifyRow.TYPE);
            HostOptionOrderExecNotifyParams l_optionOrderExecNotify = this.getHostOptionOrderExecNotifyRow();
            l_optionOrderExecNotify.setRequestCode("EI813");
            l_optionOrderExecNotify.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDelAndCommit(l_optionOrderExecNotify);
            l_callback.process();
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" status= ? ");

            List l_listRecords = new ArrayList();
            
            Object[] l_objWhere =
            {
            "0"
            };
            
            QueryProcessor l_queryProcessor = null;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_listRecords = l_queryProcessor.doFindAllQuery(
                HostOptionOrderExecNotifyRow.TYPE,
                l_sbWhere.toString() ,
                null,
                null,
                l_objWhere) ;
            
            assertEquals(0,WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
                ((HostOptionOrderExecNotifyRow)l_listRecords.get(0)).getLastUpdatedTimestamp()));
        }
        catch (DataNetworkException e)
        {
            fail();
        } 
        catch (DataQueryException e) 
        {
            fail();
        }
        catch (WEB3BaseException e) 
        {
            fail();
        }
    }
    
    public void test_process_0002()
    {
        WEB3FuturesOrderExecNotifyRequest l_request = new WEB3FuturesOrderExecNotifyRequest();
        WEB3AsynFuturesOrderExecNotifyServiceImpl l_asynFutures =  new WEB3AsynFuturesOrderExecNotifyServiceImpl(l_request);
        
        WEB3FuturesOrderExecNotifyTransactionCallback l_callback = l_asynFutures.new WEB3FuturesOrderExecNotifyTransactionCallback();
        String[]
               l_orderRequestNumberPrefixGroup = {"1"};
        l_callback.setOrderRequestNumberPrefixGroup(l_orderRequestNumberPrefixGroup);
        try
        {
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = this.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(0D);

            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            IfoContractSettleOrderUnitImpl l_ifoContract = new  IfoContractSettleOrderUnitImpl(1001);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "getOrderUnit",
                new Class[] {String.class, String.class, ProductTypeEnum.class, String.class},
                l_ifoContract);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "getOrderUnit",
                new Class[] {long.class},
                l_ifoContract);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderExecNotifyUnitServiceImpl",
                "notifyExecuteCancel",
                new Class[] {OrderUnit.class,Timestamp.class,double.class,double.class},
                new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01975,""));
            
            TestDBUtility.deleteAllAndCommit(HostOptionOrderExecNotifyRow.TYPE);
            HostOptionOrderExecNotifyParams l_optionOrderExecNotify = this.getHostOptionOrderExecNotifyRow();
            l_optionOrderExecNotify.setRequestCode("EI813");
            l_optionOrderExecNotify.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_optionOrderExecNotify.setDealedType("4");
            TestDBUtility.insertWithDelAndCommit(l_optionOrderExecNotify);
            l_callback.process();
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append("status= ?");

            List l_listRecords = new ArrayList();
            
            Object[] l_objWhere =
            {
            "0"
            };
            
            QueryProcessor l_queryProcessor = null;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_listRecords = l_queryProcessor.doFindAllQuery(
                HostOptionOrderExecNotifyRow.TYPE,
                l_sbWhere.toString() ,
                null,
                null,
                l_objWhere) ;
            
            assertEquals(0,WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
                ((HostOptionOrderExecNotifyRow)l_listRecords.get(0)).getLastUpdatedTimestamp()));
        }
        catch (DataNetworkException e)
        {
            fail();
        } 
        catch (DataQueryException e) 
        {
            fail();
        }
        catch (WEB3BaseException e) 
        {
            fail();
        }
    }
    
    public void test_process_0003()
    {
        WEB3FuturesOrderExecNotifyRequest l_request = new WEB3FuturesOrderExecNotifyRequest();
        WEB3AsynFuturesOrderExecNotifyServiceImpl l_asynFutures =  new WEB3AsynFuturesOrderExecNotifyServiceImpl(l_request);
        
        WEB3FuturesOrderExecNotifyTransactionCallback l_callback = l_asynFutures.new WEB3FuturesOrderExecNotifyTransactionCallback();
        String[]
               l_orderRequestNumberPrefixGroup = {"1"};
        l_callback.setOrderRequestNumberPrefixGroup(l_orderRequestNumberPrefixGroup);
        try
        {
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            IfoOrderUnitParams l_ifoOrderUnitParams = this.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setConfirmedQuantity(0D);
//
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            IfoContractSettleOrderUnitImpl l_ifoContract = new  IfoContractSettleOrderUnitImpl(1001);
            IfoContractSettleOrderUnitImpl l_ifoContract = null;
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "getOrderUnit",
                new Class[] {String.class, String.class, ProductTypeEnum.class, String.class},
                new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,""));
            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
//                "getOrderUnit",
//                new Class[] {long.class},
//                l_ifoContract);
            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderExecNotifyUnitServiceImpl",
//                "notifyExecuteCancel",
//                new Class[] {OrderUnit.class,Timestamp.class,double.class,double.class},
//                new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01975,""));
            
            TestDBUtility.deleteAll(HostOptionOrderExecNotifyRow.TYPE);
            HostOptionOrderExecNotifyParams l_optionOrderExecNotify = this.getHostOptionOrderExecNotifyRow();
            l_optionOrderExecNotify.setDealedType("4");
            l_optionOrderExecNotify.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_optionOrderExecNotify.setRequestCode("EI813");
            l_optionOrderExecNotify.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_optionOrderExecNotify);
            l_callback.process();
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" status= ? ");

            List l_listRecords = new ArrayList();
            
            Object[] l_objWhere =
            {
            "0"
            };
            
            QueryProcessor l_queryProcessor = null;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_listRecords = l_queryProcessor.doFindAllQuery(
                HostOptionOrderExecNotifyRow.TYPE,
                l_sbWhere.toString() ,
                null,
                null,
                l_objWhere) ;
            
            assertEquals(0,WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
                ((HostOptionOrderExecNotifyRow)l_listRecords.get(0)).getLastUpdatedTimestamp()));
        }
        catch (DataNetworkException e)
        {
            fail();
        } 
        catch (DataQueryException e) 
        {
            fail();
        }
        catch (WEB3BaseException e) 
        {
            fail();
        }
    }
    
    public static HostOptionOrderExecNotifyParams getHostOptionOrderExecNotifyRow()
    {
        HostOptionOrderExecNotifyParams l_optionOrderExecNotify = new 
        HostOptionOrderExecNotifyParams();
        
        l_optionOrderExecNotify.setRequestCode("EI815");
        l_optionOrderExecNotify.setInstitutionCode("01");
        l_optionOrderExecNotify.setBranchCode("220");
        l_optionOrderExecNotify.setOrderRequestNumber("11");
        l_optionOrderExecNotify.setDealedType("1");
//        l_optionOrderExecNotify.setCloseNotifyType("1");
        l_optionOrderExecNotify.setStatus("0");
        l_optionOrderExecNotify.setExecTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
        l_optionOrderExecNotify.setExecQuantity(1);
        l_optionOrderExecNotify.setExecPrice(2);
        
//        l_optionOrderExecNotify.setReasonCode("1");
        
        return l_optionOrderExecNotify;
    }
    
    /**
     * 注文単位テーブル(ifo_order_unit)
     */
    public static IfoOrderUnitParams getIfoOrderUnitRow()
    {
        IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
        
        l_ifoOrderUnitParams.setOrderUnitId(1001);
        l_ifoOrderUnitParams.setAccountId(101001010010L);
        l_ifoOrderUnitParams.setSubAccountId(10100101001007L);
        l_ifoOrderUnitParams.setBranchId(33381);
        l_ifoOrderUnitParams.setTraderId(null);
        l_ifoOrderUnitParams.setOrderId(1001);
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
        l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setLastExecutionSerialNo(0);
        l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderUnitParams.setFutureOptionDiv("1");
        l_ifoOrderUnitParams.setMarketId(1002);
        l_ifoOrderUnitParams.setQuantity(100);
        l_ifoOrderUnitParams.setLimitPrice(0);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.NONE);
        l_ifoOrderUnitParams.setOrderConditionType("0");
        l_ifoOrderUnitParams.setOrderCondOperator(null);
        l_ifoOrderUnitParams.setStopPriceType(null);
        l_ifoOrderUnitParams.setStopOrderPrice(null);
        l_ifoOrderUnitParams.setWLimitPrice(null);
        l_ifoOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        l_ifoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_ifoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
        l_ifoOrderUnitParams.setBizDate("20040101");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
        l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
        l_ifoOrderUnitParams.setOrderRequestNumber("000003006");
//        l_ifoOrderUnitParams.setConfirmedOrderRev("2");
//        l_ifoOrderUnitParams.setOrderRev("1");
        return l_ifoOrderUnitParams;
    }
}
@

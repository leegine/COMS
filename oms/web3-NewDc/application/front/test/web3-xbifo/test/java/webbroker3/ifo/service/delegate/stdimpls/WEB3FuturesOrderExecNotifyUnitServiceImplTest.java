head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.47.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesOrderExecNotifyUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3FuturesOrderExecNotifyUnitServiceImplテスト
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/04/26 孟亜南 (中訊) 新規作成
 */
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractSettleOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesOrderExecNotifyUnitServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3FuturesOrderExecNotifyUnitServiceImplTest.class);
    
    public WEB3FuturesOrderExecNotifyUnitServiceImplTest(String name)
    {
        super(name);
    }

    public void test_validateOrderStatus_0001()
    {
        final String STR_METHOD_NAME = "test_validateOrderStatus_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3FuturesOrderExecNotifyUnitServiceImpl l_futuresOrder = new WEB3FuturesOrderExecNotifyUnitServiceImpl();
        try 
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = this.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(0D);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoContractSettleOrderUnitImpl l_ifoContract = new  IfoContractSettleOrderUnitImpl(1001);
            l_futuresOrder.validateOrderStatus(l_ifoContract);
        }
        catch (DataQueryException e)
        {
            fail();
        }
        catch (DataNetworkException e) 
        {
            fail();
        }
        catch (WEB3BaseException e) 
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01975, e.getErrorInfo());
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_validateOrderStatus_0002()
    {
        
        final String STR_METHOD_NAME = "test_validateOrderStatus_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3FuturesOrderExecNotifyUnitServiceImpl l_futuresOrder = new WEB3FuturesOrderExecNotifyUnitServiceImpl();
        
        try 
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = this.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(1D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoContractSettleOrderUnitImpl l_ifoContract = new  IfoContractSettleOrderUnitImpl(1001);
            l_futuresOrder.validateOrderStatus(l_ifoContract);
            fail();
        }
        catch (DataQueryException e)
        {
            fail();
        }
        catch (DataNetworkException e) 
        {
            fail();
        }
        catch (WEB3BaseException e) 
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01975, e.getErrorInfo());
        }
        log.entering(TEST_START + STR_METHOD_NAME);
    }
    
    public void test_validateOrderStatus_0003()
    {
        final String STR_METHOD_NAME = "test_validateOrderStatus_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3FuturesOrderExecNotifyUnitServiceImpl l_futuresOrder = new WEB3FuturesOrderExecNotifyUnitServiceImpl();
        
        try 
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = this.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(1D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoContractSettleOrderUnitImpl l_ifoContract = new  IfoContractSettleOrderUnitImpl(1001);
            l_futuresOrder.validateOrderStatus(l_ifoContract);
            fail();
        }
        catch (DataQueryException e)
        {
            fail();
        }
        catch (DataNetworkException e) 
        {
            fail();
        }
        catch (WEB3BaseException e) 
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01975, e.getErrorInfo());
        }
        log.entering(TEST_START + STR_METHOD_NAME);
    }
    
    public void test_validateOrderStatus_0004()
    {
        
        final String STR_METHOD_NAME = "test_validateOrderStatus_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3FuturesOrderExecNotifyUnitServiceImpl l_futuresOrder = new WEB3FuturesOrderExecNotifyUnitServiceImpl();
        
        try 
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = this.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(1D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFYING);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoContractSettleOrderUnitImpl l_ifoContract = new  IfoContractSettleOrderUnitImpl(1001);
            l_futuresOrder.validateOrderStatus(l_ifoContract);
            fail();
        }
        catch (DataQueryException e)
        {
            fail();
        }
        catch (DataNetworkException e) 
        {
            fail();
        }
        catch (WEB3BaseException e) 
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01975, e.getErrorInfo());
        }
        log.entering(TEST_START + STR_METHOD_NAME);
    }
    
    public void test_validateOrderStatus_0005()
    {
        final String STR_METHOD_NAME = "test_validateOrderStatus_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3FuturesOrderExecNotifyUnitServiceImpl l_futuresOrder = new WEB3FuturesOrderExecNotifyUnitServiceImpl();
        
        try 
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = this.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(1D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLING);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoContractSettleOrderUnitImpl l_ifoContract = new  IfoContractSettleOrderUnitImpl(1001);
            l_futuresOrder.validateOrderStatus(l_ifoContract);
        }
        catch (DataQueryException e)
        {
            fail();
        }
        catch (DataNetworkException e) 
        {
            fail();
        }
        catch (WEB3BaseException e) 
        {
            fail();
        }
        log.entering(TEST_START + STR_METHOD_NAME);
    }
    
    public void test_validateOrderStatus_0006()
    {
        
        final String STR_METHOD_NAME = "test_validateOrderStatus_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3FuturesOrderExecNotifyUnitServiceImpl l_futuresOrder = new WEB3FuturesOrderExecNotifyUnitServiceImpl();
        
        try 
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = this.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFYING);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoContractSettleOrderUnitImpl l_ifoContract = new  IfoContractSettleOrderUnitImpl(1001);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "getOrderUnit",
                new Class[] {String.class, String.class, ProductTypeEnum.class, String.class},
                l_ifoContract);
            
            Timestamp l_tsExecDate = new Timestamp(0);
            l_futuresOrder.notifyExecute(l_ifoContract,l_tsExecDate,45D,56D,"121");
            fail();
        }
        catch (DataQueryException e)
        {
            fail();
        }
        catch (DataNetworkException e) 
        {
            fail();
        }
        catch (WEB3BaseException e) 
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01975, e.getErrorInfo());
        }
        log.entering(TEST_START + STR_METHOD_NAME);
    }
    
    public void test_validateOrderStatus_0007()
    {
        final String STR_METHOD_NAME = "test_validateOrderStatus_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3FuturesOrderExecNotifyUnitServiceImpl l_futuresOrder = new WEB3FuturesOrderExecNotifyUnitServiceImpl();
        
        try 
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = this.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFYING);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoContractSettleOrderUnitImpl l_ifoContract = new  IfoContractSettleOrderUnitImpl(1001);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "getOrderUnit",
                new Class[] {String.class, String.class, ProductTypeEnum.class, String.class},
                l_ifoContract);
            
            Timestamp l_tsExecDate = new Timestamp(0);
            l_futuresOrder.notifyExecuteCancel(l_ifoContract,l_tsExecDate,45D,56D);
            fail();
        }
        catch (DataQueryException e)
        {
            fail();
        }
        catch (DataNetworkException e) 
        {
            fail();
        }
        catch (WEB3BaseException e) 
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01975, e.getErrorInfo());
        }
        log.entering(TEST_START + STR_METHOD_NAME);
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

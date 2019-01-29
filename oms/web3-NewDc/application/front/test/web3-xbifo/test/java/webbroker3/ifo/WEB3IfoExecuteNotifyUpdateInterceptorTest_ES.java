head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.59.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoExecuteNotifyUpdateInterceptorTest_ES.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP出来通知更新インタセプタ
Author Name      : Daiwa Institute of Research
Revesion History : 2007/07/03 孟亜南
*/
package webbroker3.ifo;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;

/**
 * 先物OP出来通知更新インタセプタ
 * @@author 孟亜南
 *
 */
public class WEB3IfoExecuteNotifyUpdateInterceptorTest_ES extends TestBaseForMock
{

    public WEB3IfoExecuteNotifyUpdateInterceptorTest_ES(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void test_mutate_0001()
    {
        WEB3IfoExecuteNotifyUpdateInterceptor l_interceptor = new WEB3IfoExecuteNotifyUpdateInterceptor();
        IfoOrderExecutionParams l_ifoOrderExecutionParams = new IfoOrderExecutionParams();
        l_ifoOrderExecutionParams.setOrderUnitId(1001);
        
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = ifoOrderUnit();
            l_ifoOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
        l_ifoOrderExecutionParams = l_interceptor.mutate(OrderManagerPersistenceType.INSERT,OrderManagerPersistenceContext.ASSET_TRANSFER_DONE,l_ifoOrderExecutionParams);
        assertEquals("0","" + WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20040101", "yyyyMMdd"),l_ifoOrderExecutionParams.getDeliveryDate()));
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
        l_params.setOrderConditionType("3");
        l_params.setOrderCondOperator(null);
        l_params.setStopPriceType(null);
        l_params.setStopOrderPrice(null);
        l_params.setWLimitPrice(null);
        l_params.setDeliveryDate(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
        l_params.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_params.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        l_params.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_params.setTaxType(TaxTypeEnum.NORMAL);
        l_params.setBizDate("20040101");
        l_params.setProductId(1006169090018L);
        l_params.setCreatedTimestamp(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
        l_params.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
        l_params.setOrderRequestNumber("000003006");
        l_params.setConfirmedOrderRev("2");
        l_params.setOrderRev("1");
        l_params.setBizDate("20070808");
        l_params.setWLimitExecCondType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        
        l_params.setConfirmedExecConditionType(IfoOrderExecutionConditionType.NONE);
        l_params.setConfirmedQuantity(100);
        
        l_params.setExpirationDate(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
        return l_params;
    }
}
@

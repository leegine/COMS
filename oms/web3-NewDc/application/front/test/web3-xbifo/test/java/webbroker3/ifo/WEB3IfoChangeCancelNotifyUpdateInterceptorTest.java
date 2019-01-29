head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.59.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoChangeCancelNotifyUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP訂正取消通知更新インタセプタテスト(WEB3IfoChangeCancelNotifyUpdateInterceptorTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/06 周捷 (中訊) 新規作成
*/
package webbroker3.ifo;

import java.util.Date;

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
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import test.util.TestDBUtility;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * （先物OP訂正取消通知更新インタセプタテスト）<BR>
 *
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3IfoChangeCancelNotifyUpdateInterceptorTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3IfoChangeCancelNotifyUpdateInterceptorTest.class);

    public WEB3IfoChangeCancelNotifyUpdateInterceptorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.ifo.WEB3IfoChangeCancelNotifyUpdateInterceptor.mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, IfoOrderUnitParams)'
     */
    public void testMutate1()
    {
        final String STR_METHOD_NAME = "testMutate1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3IfoChangeCancelNotifyUpdateInterceptor l_interceptor =
            new WEB3IfoChangeCancelNotifyUpdateInterceptor();
        l_interceptor.setChangeCancelNotifyDivision("2");
        l_interceptor.setModSubmitOrderRouteDiv("0");
        l_interceptor.setModifiedOrderRev("00");
        IfoOrderUnitParams l_params = ifoOrderUnit();
        l_params.setFutureOptionDiv("2");

        IfoOrderUnitParams l_afterParams = new IfoOrderUnitParams();
        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;

        try
        {
            TestDBUtility.insertWithDelAndCommit(l_params);
            l_afterParams = l_interceptor.mutate(l_updateType, l_dealing, l_params);

            assertEquals("0", l_afterParams.getSubmitOrderRouteDiv());
            assertEquals("00", l_afterParams.getConfirmedOrderRev());
            assertEquals("1", l_afterParams.getOrderRev());
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

    public void testMutate2()
    {
        final String STR_METHOD_NAME = "testMutate2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3IfoChangeCancelNotifyUpdateInterceptor l_interceptor =
            new WEB3IfoChangeCancelNotifyUpdateInterceptor();
        l_interceptor.setChangeCancelNotifyDivision("1");
        l_interceptor.setModifiedOrderRev("00");
        IfoOrderUnitParams l_params = ifoOrderUnit();
        l_params.setFutureOptionDiv("1");
        l_params.setSubmitOrderRouteDiv("1");

        IfoOrderUnitParams l_afterParams = new IfoOrderUnitParams();
        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;

        try
        {
            TestDBUtility.insertWithDelAndCommit(l_params);
            l_afterParams = l_interceptor.mutate(l_updateType, l_dealing, l_params);

            assertEquals("1", l_afterParams.getSubmitOrderRouteDiv());
            assertEquals("00", l_afterParams.getConfirmedOrderRev());
            assertEquals("1", l_afterParams.getOrderRev());
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

    public void testMutate3()
    {
        final String STR_METHOD_NAME = "testMutate3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3IfoChangeCancelNotifyUpdateInterceptor l_interceptor =
            new WEB3IfoChangeCancelNotifyUpdateInterceptor();
        l_interceptor.setChangeCancelNotifyDivision("3");
        l_interceptor.setModSubmitOrderRouteDiv("0");
        l_interceptor.setModifiedOrderRev("00");
        IfoOrderUnitParams l_params = ifoOrderUnit();
        l_params.setFutureOptionDiv("2");
        l_params.setSubmitOrderRouteDiv("1");

        IfoOrderUnitParams l_afterParams = new IfoOrderUnitParams();
        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;

        try
        {
            TestDBUtility.insertWithDelAndCommit(l_params);
            l_afterParams = l_interceptor.mutate(l_updateType, l_dealing, l_params);

            assertEquals("0", l_afterParams.getSubmitOrderRouteDiv());
            assertEquals("2", l_afterParams.getConfirmedOrderRev());
            assertEquals("1", l_afterParams.getOrderRev());
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

    public void testMutate4()
    {
        final String STR_METHOD_NAME = "testMutate4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3IfoChangeCancelNotifyUpdateInterceptor l_interceptor =
            new WEB3IfoChangeCancelNotifyUpdateInterceptor();
        l_interceptor.setChangeCancelNotifyDivision("4");
        l_interceptor.setModifiedOrderRev("00");
        IfoOrderUnitParams l_params = ifoOrderUnit();
        l_params.setFutureOptionDiv("1");
        l_params.setSubmitOrderRouteDiv("1");
        l_params.setConfirmedOrderRev("2");
        l_params.setOrderRev("1");

        IfoOrderUnitParams l_afterParams = new IfoOrderUnitParams();
        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;

        try
        {
            TestDBUtility.insertWithDelAndCommit(l_params);
            l_afterParams = l_interceptor.mutate(l_updateType, l_dealing, l_params);

            assertEquals("1", l_afterParams.getSubmitOrderRouteDiv());
            assertEquals("2", l_afterParams.getConfirmedOrderRev());
            assertEquals("1", l_afterParams.getOrderRev());
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
        l_params.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
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

}
@

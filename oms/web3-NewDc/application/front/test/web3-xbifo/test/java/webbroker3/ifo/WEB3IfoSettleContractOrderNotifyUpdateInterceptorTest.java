head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.00.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoSettleContractOrderNotifyUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP返済注文通知更新インタセプタテスト(WEB3IfoSettleContractOrderNotifyUpdateInterceptorTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/06 周捷 (中訊) 新規作成
*/
package webbroker3.ifo;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * （先物OP返済注文通知更新インタセプタテスト）<BR>
 *
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3IfoSettleContractOrderNotifyUpdateInterceptorTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(
            WEB3IfoSettleContractOrderNotifyUpdateInterceptorTest.class);
    
    public WEB3IfoSettleContractOrderNotifyUpdateInterceptorTest(String arg0)
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
     * Test method for 'webbroker3.ifo.WEB3IfoSettleContractOrderNotifyUpdateInterceptor.mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, IfoOrderUnitParams)'
     */
    public void testMutate1()
    {
        final String STR_METHOD_NAME = "testMutate1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoSettleContractOrderSpec l_spec = null;
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());
            
            l_spec = WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                "0D",
                null,
                0.0,
                null,//IfoOrderExecutionConditionType
                new Date(),
                null,//SettleContractEntry[]
                "",
                0.0,
                0.0,
                null,
                "1",
                new Long(1001L),
                true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        WEB3IfoSettleContractOrderNotifyUpdateInterceptor l_interceptor =
            new WEB3IfoSettleContractOrderNotifyUpdateInterceptor(l_spec);
        WEB3GentradeCommission l_commRevMstId = new WEB3GentradeCommission();
        l_commRevMstId.setOrderChannel("123");
        l_commRevMstId.setCommissionNo("123");
        l_commRevMstId.setCommissionRevNo("123");
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmounCalcResult =
            new WEB3IfoEstimateDeliveryAmountCalcResult();
        l_interceptor.commRevMstId = l_commRevMstId;
        l_interceptor.estimateDeliveryAmounCalcResult = l_estimateDeliveryAmounCalcResult;
        l_interceptor.setOrderRequestNumber("111");
        IfoOrderUnitParams l_params = TestDBUtility.getIfoOrderUnitRow();
        l_params.setConfirmedOrderRev(null);
        l_params.setOrderRev(null);
        IfoOrderUnitParams l_outputParams = new IfoOrderUnitParams();
        l_params.setFutureOptionDiv("2");
        l_params.setMarketId(1002);

        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;
        SubAccountParams l_subParams = TestDBUtility.getSubAccountRow();
        l_subParams.setAccountId(l_params.getAccountId());
        l_subParams.setSubAccountId(l_params.getSubAccountId());

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(1002);
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(101001010010L);
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006169090018L);
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006169090018L);
        try
        {
            TestDBUtility.insertWithDel(l_params);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            l_outputParams = l_interceptor.mutate(l_updateType, l_dealing, l_params);

            assertEquals("0", l_outputParams.getSubmitOrderRouteDiv());
            assertEquals(null, l_outputParams.getConfirmedOrderRev());
            assertEquals(null, l_outputParams.getOrderRev());
            assertEquals("1", l_outputParams.getExpirationDateType());//注文期限区分
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
        
    public void testMutate2()
    {
        final String STR_METHOD_NAME = "testMutate2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3IfoSettleContractOrderSpec l_spec = null;
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());
            
            l_spec = WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                "0D",
                null,
                0.0,
                null,//IfoOrderExecutionConditionType
                new Date(),
                null,//SettleContractEntry[]
                "",
                0.0,
                0.0,
                null,
                "1",
                new Long(1001L),
                true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        WEB3IfoSettleContractOrderNotifyUpdateInterceptor l_interceptor =
            new WEB3IfoSettleContractOrderNotifyUpdateInterceptor(l_spec);
        WEB3GentradeCommission l_commRevMstId = new WEB3GentradeCommission();
        l_commRevMstId.setOrderChannel("123");
        l_commRevMstId.setCommissionNo("123");
        l_commRevMstId.setCommissionRevNo("123");
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmounCalcResult =
            new WEB3IfoEstimateDeliveryAmountCalcResult();
        l_interceptor.commRevMstId = l_commRevMstId;
        l_interceptor.estimateDeliveryAmounCalcResult = l_estimateDeliveryAmounCalcResult;
        l_interceptor.setOrderRequestNumber("111");
        IfoOrderUnitParams l_params = TestDBUtility.getIfoOrderUnitRow();
        l_params.setConfirmedOrderRev(null);
        l_params.setOrderRev(null);
        IfoOrderUnitParams l_outputParams = new IfoOrderUnitParams();
        l_params.setFutureOptionDiv("2");
        l_params.setMarketId(1002);

        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;
        SubAccountParams l_subParams = TestDBUtility.getSubAccountRow();
        l_subParams.setAccountId(l_params.getAccountId());
        l_subParams.setSubAccountId(l_params.getSubAccountId());

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(1002);
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(101001010010L);
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006169090018L);
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006169090018L);
        try
        {
            TestDBUtility.insertWithDel(l_params);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            l_outputParams = l_interceptor.mutate(l_updateType, l_dealing, l_params);

            assertEquals("0", l_outputParams.getSubmitOrderRouteDiv());
            assertEquals(null, l_outputParams.getConfirmedOrderRev());
            assertEquals(null, l_outputParams.getOrderRev());
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
        
        IfoOrderUnitParams l_params = TestDBUtility.getIfoOrderUnitRow();
        l_params.setConfirmedOrderRev(null);
        l_params.setOrderRev(null);
        IfoOrderUnitParams l_outputParams = new IfoOrderUnitParams();
        l_params.setFutureOptionDiv("2");
        l_params.setMarketId(1002);

        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;
        SubAccountParams l_subParams = TestDBUtility.getSubAccountRow();
        l_subParams.setAccountId(l_params.getAccountId());
        l_subParams.setSubAccountId(l_params.getSubAccountId());

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(1002);
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(101001010010L);
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006169090018L);
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006169090018L);
        
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        
        WEB3IfoSettleContractOrderSpec l_spec = null;
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());
            
            l_spec = WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                "0D",
                null,
                0.0,
                null,//IfoOrderExecutionConditionType
                new Date(),
                null,//SettleContractEntry[]
                "",
                0.0,
                0.0,
                null,
                "1",
                new Long(1001L),
                true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        WEB3IfoSettleContractOrderNotifyUpdateInterceptor l_interceptor =
            new WEB3IfoSettleContractOrderNotifyUpdateInterceptor(l_spec);
        WEB3GentradeCommission l_commRevMstId = new WEB3GentradeCommission();
        l_commRevMstId.setOrderChannel("123");
        l_commRevMstId.setCommissionNo("123");
        l_commRevMstId.setCommissionRevNo("123");
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmounCalcResult =
            new WEB3IfoEstimateDeliveryAmountCalcResult();
        l_interceptor.commRevMstId = l_commRevMstId;
        l_interceptor.estimateDeliveryAmounCalcResult = l_estimateDeliveryAmounCalcResult;
        l_interceptor.setOrderRequestNumber("111");
        //注文受付番号
        l_interceptor.setAcceptNumber("100002");
        //銘柄コード
        l_interceptor.setProductCode("160030008");
        //売買区分
        l_interceptor.setBuySellDiv("2");
        
        try
        {

            l_outputParams = l_interceptor.mutate(l_updateType, l_dealing, l_params);

            assertEquals("100002", l_outputParams.getAcceptNumber());
            assertEquals("160030008", l_outputParams.getProductCode());
            assertEquals("2", l_outputParams.getBuySellDiv());
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
    
    private class WEB3IfoProductImplForTest extends WEB3IfoProductImpl
    {

        public WEB3IfoProductImplForTest(long l_lngProductID) throws DataFindException, DataQueryException, DataNetworkException
        {
            super(l_lngProductID);
            // TODO Auto-generated constructor stub
        }
        
    }
}
@

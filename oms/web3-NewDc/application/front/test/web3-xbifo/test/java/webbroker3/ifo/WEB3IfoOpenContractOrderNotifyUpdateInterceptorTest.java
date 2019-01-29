head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.58.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoOpenContractOrderNotifyUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP新規建注文通知更新インタセプタテスト(WEB3IfoOpenContractOrderNotifyUpdateInterceptorTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/06 周捷 (中訊) 新規作成
*/
package webbroker3.ifo;

import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.PersonNameDetails;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TraderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
//import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOpenContractOrderServiceImplTest.TraderForTest;
//import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOpenContractOrderServiceImplTest.WEB3IfoProductImplForTest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * （先物OP新規建注文通知更新インタセプタテスト）<BR>
 *
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3IfoOpenContractOrderNotifyUpdateInterceptorTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3IfoOpenContractOrderNotifyUpdateInterceptorTest.class);

    public WEB3IfoOpenContractOrderNotifyUpdateInterceptorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        Calendar ca1 =  Calendar.getInstance();
        ca1.set(2007,6-1,19);
        
        Date date1 = ca1.getTime();
        WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.ifo.WEB3IfoOpenContractOrderNotifyUpdateInterceptor.mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, IfoOrderUnitParams)'
     */
    public void testMutate1()
    {
        final String STR_METHOD_NAME = "testMutate1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getSubmitOrderRouteDiv", new Class[] {String.class, String.class},
            "0");

        WEB3GentradeCommission l_commRevMstId = new WEB3GentradeCommission();
        l_commRevMstId.setOrderChannel("123");
        l_commRevMstId.setCommissionNo("123");
        l_commRevMstId.setCommissionRevNo("123");
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmounCalcResult =
            new WEB3IfoEstimateDeliveryAmountCalcResult();
        l_estimateDeliveryAmounCalcResult.setCalcUnitPrice(100);
        l_estimateDeliveryAmounCalcResult.setEstimateDeliveryAmount(100);

        IfoOrderUnitParams l_params = TestDBUtility.getIfoOrderUnitRow();
        l_params.setFutureOptionDiv("2");
        l_params.setConfirmedOrderRev("00");
        l_params.setOrderRev("00");

        IfoOrderUnitParams l_afterParams = new IfoOrderUnitParams();
        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;

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
            TestDBUtility.commit();
            
            WEB3IfoOpenContractOrderSpec l_spec =
                WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                    "0D",
                    new TraderForTest(),
                    true,
                    "01",
                    new WEB3IfoProductImplForTest(l_ifoProductParams),
                     0,
                     0,
                     null,
                     new Date(),
                     null,
                     0,
                     0,
                     null,
                     null,
                     null,
                     true);
            WEB3IfoOpenContractOrderNotifyUpdateInterceptor l_interceptor =
                new WEB3IfoOpenContractOrderNotifyUpdateInterceptor(l_spec);
            l_interceptor.commRevMstId = l_commRevMstId;
            l_interceptor.estimateDeliveryAmounCalcResult = l_estimateDeliveryAmounCalcResult;
            l_interceptor.setOrderRequestNumber("111");
            l_afterParams = l_interceptor.mutate(l_updateType, l_dealing, l_params);

            assertEquals("0", l_afterParams.getSubmitOrderRouteDiv());
            assertEquals("00", l_afterParams.getConfirmedOrderRev());
            assertEquals("00", l_afterParams.getOrderRev());
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
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getSubmitOrderRouteDiv", new Class[] {String.class, String.class},
            "1");
//        WEB3IfoOpenContractOrderNotifyUpdateInterceptor l_interceptor =
//            new WEB3IfoOpenContractOrderNotifyUpdateInterceptor();
        WEB3GentradeCommission l_commRevMstId = new WEB3GentradeCommission();
        l_commRevMstId.setOrderChannel("123");
        l_commRevMstId.setCommissionNo("123");
        l_commRevMstId.setCommissionRevNo("123");
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmounCalcResult =
            new WEB3IfoEstimateDeliveryAmountCalcResult();
        l_estimateDeliveryAmounCalcResult.setCalcUnitPrice(100);
        l_estimateDeliveryAmounCalcResult.setEstimateDeliveryAmount(100);
//        l_interceptor.commRevMstId = l_commRevMstId;
//        l_interceptor.estimateDeliveryAmounCalcResult = l_estimateDeliveryAmounCalcResult;
//        l_interceptor.setOrderRequestNumber("111");
        IfoOrderUnitParams l_params = TestDBUtility.getIfoOrderUnitRow();
        l_params.setFutureOptionDiv("1");
        l_params.setConfirmedOrderRev("00");
        l_params.setOrderRev("00");

        IfoOrderUnitParams l_afterParams = new IfoOrderUnitParams();
        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;

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
            TestDBUtility.commit();
            WEB3IfoOpenContractOrderSpec l_spec =
                WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                    "0D",
                    new TraderForTest(),
                    true,
                    "01",
                    new WEB3IfoProductImplForTest(l_ifoProductParams),
                     0,
                     0,
                     null,
                     new Date(),
                     null,
                     0,
                     0,
                     null,
                     null,
                     null,
                     true);
            WEB3IfoOpenContractOrderNotifyUpdateInterceptor l_interceptor =
                new WEB3IfoOpenContractOrderNotifyUpdateInterceptor(l_spec);
            l_interceptor.commRevMstId = l_commRevMstId;
            l_interceptor.estimateDeliveryAmounCalcResult = l_estimateDeliveryAmounCalcResult;
            l_interceptor.setOrderRequestNumber("111");
            l_afterParams = l_interceptor.mutate(l_updateType, l_dealing, l_params);
            l_afterParams = l_interceptor.mutate(l_updateType, l_dealing, l_params);

            assertEquals("0", l_afterParams.getSubmitOrderRouteDiv());
            assertEquals("00", l_afterParams.getConfirmedOrderRev());
            assertEquals("00", l_afterParams.getOrderRev());
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
     * 「予約注文設定フラグ」和「注文期限区分」的取得
     *
     */
    public void testMutate_C0001()
    {
        final String STR_METHOD_NAME = "testMutate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getSubmitOrderRouteDiv", new Class[] {String.class, String.class},
            "1");
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(1002);
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(101001010010L);
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006169090018L);
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006169090018L);
        
       
        WEB3IfoOpenContractOrderSpec l_spec = null;
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());
            
            l_spec = WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                "0D",
                null,
                true,
                "3316",
                new WEB3IfoProductImplForTest(1006169090018L),
                0,
                0,
                null,
                new Date(),
                "0",
                123,0, null, "1", null, false);
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
        
        WEB3IfoOpenContractOrderNotifyUpdateInterceptor l_interceptor =
            new WEB3IfoOpenContractOrderNotifyUpdateInterceptor(l_spec);
        
        WEB3GentradeCommission l_commRevMstId = new WEB3GentradeCommission();
        l_commRevMstId.setOrderChannel("123");
        l_commRevMstId.setCommissionNo("123");
        l_commRevMstId.setCommissionRevNo("123");
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmounCalcResult =
            new WEB3IfoEstimateDeliveryAmountCalcResult();
        l_estimateDeliveryAmounCalcResult.setCalcUnitPrice(100);
        l_estimateDeliveryAmounCalcResult.setEstimateDeliveryAmount(100);
        l_interceptor.commRevMstId = l_commRevMstId;
        l_interceptor.estimateDeliveryAmounCalcResult = l_estimateDeliveryAmounCalcResult;
        l_interceptor.setOrderRequestNumber("111");
        IfoOrderUnitParams l_params = TestDBUtility.getIfoOrderUnitRow();
        l_params.setFutureOptionDiv("1");
        l_params.setConfirmedOrderRev("00");
        l_params.setOrderRev("00");

        IfoOrderUnitParams l_afterParams = new IfoOrderUnitParams();
        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;



        try
        {
            TestDBUtility.insertWithDel(l_params);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            l_afterParams = l_interceptor.mutate(l_updateType, l_dealing, l_params);

            assertEquals("0", l_afterParams.getSubmitOrderRouteDiv());
            assertEquals("00", l_afterParams.getConfirmedOrderRev());
            assertEquals("00", l_afterParams.getOrderRev());
//            assertEquals("0", l_afterParams.getReserveOrderExistFlag());
            assertEquals("1", l_afterParams.getExpirationDateType());
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
     * 先物注文通知(新規建)_注文単位テーブル DB更新仕様
     * 「注文受付番号」、「銘柄コード」と「売買区分」を取得
     *
     */
    public void testMutate_C0002()
    {
        final String STR_METHOD_NAME = "testMutate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getSubmitOrderRouteDiv", new Class[] {String.class, String.class},
            "1");
        
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
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
       
        WEB3IfoOpenContractOrderSpec l_spec = null;
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());
            
            l_spec = WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                "0D",
                null,
                true,
                "3316",
                new WEB3IfoProductImplForTest(1006169090018L),
                0,
                0,
                null,
                new Date(),
                "0",
                123,0, null, "1", null, false);
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
        
        WEB3IfoOpenContractOrderNotifyUpdateInterceptor l_interceptor =
            new WEB3IfoOpenContractOrderNotifyUpdateInterceptor(l_spec);
        
        WEB3GentradeCommission l_commRevMstId = new WEB3GentradeCommission();
        l_commRevMstId.setOrderChannel("123");
        l_commRevMstId.setCommissionNo("123");
        l_commRevMstId.setCommissionRevNo("123");
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmounCalcResult =
            new WEB3IfoEstimateDeliveryAmountCalcResult();
        l_estimateDeliveryAmounCalcResult.setCalcUnitPrice(100);
        l_estimateDeliveryAmounCalcResult.setEstimateDeliveryAmount(100);
        l_interceptor.commRevMstId = l_commRevMstId;
        l_interceptor.estimateDeliveryAmounCalcResult = l_estimateDeliveryAmounCalcResult;
        l_interceptor.setOrderRequestNumber("111");
        //注文受付番号
        l_interceptor.setAcceptNumber("100001");
        //銘柄コード
        l_interceptor.setProductCode("160030006");
        //売買区分
        l_interceptor.setBuySellDiv("1");
        
        IfoOrderUnitParams l_params = TestDBUtility.getIfoOrderUnitRow();
        l_params.setFutureOptionDiv("1");
        l_params.setConfirmedOrderRev("00");
        l_params.setOrderRev("00");

        IfoOrderUnitParams l_afterParams = new IfoOrderUnitParams();
        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;

        try
        {
            TestDBUtility.insertWithDel(l_params);
            l_afterParams = l_interceptor.mutate(l_updateType, l_dealing, l_params);

            assertEquals("100001", l_afterParams.getAcceptNumber());
            assertEquals("160030006", l_afterParams.getProductCode());
            assertEquals("1", l_afterParams.getBuySellDiv());

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
        
        public WEB3IfoProductImplForTest(IfoProductRow l_row) throws DataFindException, DataQueryException, DataNetworkException
        {
            super(l_row);
            // TODO Auto-generated constructor stub
        }
        
    }
    private class TraderForTest implements Trader
    {

        public long getTraderId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public String getTraderCode()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public long getLoginId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public TraderTypeEnum getTraderType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public PersonNameDetails getNameDetails()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public PersonNameDetails getAlt1NameDetails()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public PersonNameDetails getAlt2NameDetails()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Branch getBranch()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Institution getInstitution()
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

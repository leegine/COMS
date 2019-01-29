head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.41.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToWLimitIfoSwitchUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ToWLimitIfoSwitchUpdateInterceptorTest.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/02 金傑 新規作成
*/
package webbroker3.triggerorder;


import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractChangeSpec;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * W指値注文先物OP切替一件サービスインタセプタのテスト<BR>
 * @@author 金傑(中訊)
 * @@version 1.0
 */
public class WEB3ToWLimitIfoSwitchUpdateInterceptorTest extends TestBaseForMock
{

	
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToWLimitIfoSwitchUpdateInterceptorTest.class); 
    
	private WEB3ToWLimitIfoSwitchUpdateInterceptor l_Wlimitinterceptor = null;
	
	private IfoOrderUnitParams l_orderUnitParams = null;
	
	public WEB3ToWLimitIfoSwitchUpdateInterceptorTest(String name)
	{
		super(name);
	}
	
    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        this.l_Wlimitinterceptor = new WEB3ToWLimitIfoSwitchUpdateInterceptor();
        this.l_orderUnitParams = new IfoOrderUnitParams();
        this.getData();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.l_Wlimitinterceptor = null;
        this.l_orderUnitParams = null;
    }

    public void test_mutate_C0001()
	{
        final String STR_METHOD_NAME = "test_mutate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
    	WEB3IfoOpenContractChangeSpec l_web3IfoOpenContractChangeSpec = 
    		new WEB3IfoOpenContractChangeSpecForTest(1, 2, 3, 4);
    	this.l_Wlimitinterceptor.changeSpec = l_web3IfoOpenContractChangeSpec;
    	try
		{

			TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
					"getChangeSubmitOrderRouteDiv", new Class[] { IfoOrderUnit.class }, 
					new WEB3BaseException(new ErrorInfo(), STR_METHOD_NAME));

			TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl", "getChangeOrderRev",
					new Class[] { IfoOrderUnit.class }, "1");
			
    		this.l_orderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
    		
    		IfoOrderUnitParams l_orderUnitParamsResult = 
				this.l_Wlimitinterceptor.mutate(null, null, this.l_orderUnitParams);
    		fail();

		}
		catch (WEB3BaseRuntimeException l_web3BaseRuntimeException)
		{
			log.error(STR_METHOD_NAME,l_web3BaseRuntimeException);
			assertEquals(WEB3BaseRuntimeException.class,l_web3BaseRuntimeException.getClass());
			
		}
		catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME,l_ex);
			fail();
		}
		log.exiting(TEST_END + STR_METHOD_NAME);
	}
    
    public void test_mutate_C0002()
	{
        final String STR_METHOD_NAME = "test_mutate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
    	WEB3IfoOpenContractChangeSpec l_web3IfoOpenContractChangeSpec = 
    		new WEB3IfoOpenContractChangeSpecForTest(1, 2, 3, 4);
    	this.l_Wlimitinterceptor.changeSpec = l_web3IfoOpenContractChangeSpec;
    	try
		{

			TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
					"getChangeSubmitOrderRouteDiv", new Class[] { IfoOrderUnit.class }, 
					"0");

			TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl", "getChangeOrderRev",
					new Class[] { IfoOrderUnit.class }, 
					new WEB3BaseException(new ErrorInfo(), STR_METHOD_NAME));
			
    		this.l_orderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
    		
    		IfoOrderUnitParams l_orderUnitParamsResult = 
				this.l_Wlimitinterceptor.mutate(null, null, this.l_orderUnitParams);
    		fail();

		}
		catch (WEB3BaseRuntimeException l_web3BaseRuntimeException)
		{
			log.error(STR_METHOD_NAME,l_web3BaseRuntimeException);
			assertEquals(WEB3BaseRuntimeException.class,l_web3BaseRuntimeException.getClass());
			
		}
		catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME,l_ex);
			fail();
		}
		log.exiting(TEST_END + STR_METHOD_NAME);
	}
    
    public void test_mutate_C0003()
	{
		final String STR_METHOD_NAME = "test_mutate_C0003()";
		log.entering(TEST_START + STR_METHOD_NAME);

		WEB3IfoOpenContractChangeSpec l_web3IfoOpenContractChangeSpec = new WEB3IfoOpenContractChangeSpecForTest(1, 2,
				3, 4);
		this.l_Wlimitinterceptor.changeSpec = l_web3IfoOpenContractChangeSpec;
		try
		{

			TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
					"getChangeSubmitOrderRouteDiv", new Class[] { IfoOrderUnit.class }, "0");

			TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
					"webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl", "getChangeOrderRev",
					new Class[] { IfoOrderUnit.class }, "1");

			this.l_orderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);

			IfoOrderUnitParams l_orderUnitParamsResult = this.l_Wlimitinterceptor.mutate(null, null,
					this.l_orderUnitParams);
			assertEquals("0", l_orderUnitParamsResult.getSubmitOrderRouteDiv());
			assertEquals("1", l_orderUnitParamsResult.getOrderRev());

		}
		catch (WEB3BaseRuntimeException l_web3BaseRuntimeException)
		{
			log.error(STR_METHOD_NAME, l_web3BaseRuntimeException);
			fail();
		}
		catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		log.exiting(TEST_END + STR_METHOD_NAME);
	}
    
    /**
     * 注文履歴
     *
     */
    public void test_mutate_Case1()
    {
        final String STR_METHOD_NAME = "test_mutate_Case1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            IfoOrderActionParams l_orderActionParams = new IfoOrderActionParams();
            l_orderActionParams.setOrderUnitId(1001L);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1001L);
            l_ifoOrderUnitParams.setExpirationDateType("0");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            WEB3ToWLimitIfoSwitchUpdateInterceptor l_interceptor = new WEB3ToWLimitIfoSwitchUpdateInterceptor();
            l_orderActionParams= l_interceptor.mutate(null, null, l_orderActionParams);
            assertEquals("0", l_orderActionParams.getExpirationDateType());

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private void getData()
	{

		final String STR_METHOD_NAME = "getData()";
		log.entering(TEST_START + STR_METHOD_NAME);
		try
		{
			TraderParams l_traderParams = TestDBUtility.getTraderRow();
			l_traderParams.setTraderId(3338111132L);
			l_traderParams.setLoginId(3338111132L);
			TestDBUtility.insertWithDel(l_traderParams);
			
        	WEB3GentradeTrader l_genTrader = new WEB3GentradeTrader(3338111132L,false);
        	
        	this.l_Wlimitinterceptor.trader = l_genTrader;
        	
        	WEB3IfoEstimateDeliveryAmountCalcResult l_web3IfoEstimateDeliveryAmountCalcResult = 
        		new WEB3IfoEstimateDeliveryAmountCalcResultForTest();
        	
        	this.l_Wlimitinterceptor.estimateDeliveryAmounCalcResult = 
        		l_web3IfoEstimateDeliveryAmountCalcResult;
		
		}
		catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
		}
		log.exiting(TEST_END + STR_METHOD_NAME);
	}

    private class WEB3IfoOpenContractChangeSpecForTest extends WEB3IfoOpenContractChangeSpec{

		public WEB3IfoOpenContractChangeSpecForTest(long l_lngOrderID, long l_lngOrderUnitID, double l_dblQuantityAfterChange, double l_dblLimitPriceAfterChange)
		{
			super(l_lngOrderID, l_lngOrderUnitID, l_dblQuantityAfterChange, l_dblLimitPriceAfterChange);
		}
		
	    public IfoOrderExecutionConditionType getChangeExecCondType() 
	    {
	        return IfoOrderExecutionConditionType.AT_MARKET_OPEN; 
	    }
    	
    }
    
    private class WEB3IfoEstimateDeliveryAmountCalcResultForTest extends WEB3IfoEstimateDeliveryAmountCalcResult
    {
        public double getCalcUnitPrice() 
        {
            return 1234L;
        }
        
        public double getEstimateDeliveryAmount() 
        {
            return 4320L;
        }
    }

}
@

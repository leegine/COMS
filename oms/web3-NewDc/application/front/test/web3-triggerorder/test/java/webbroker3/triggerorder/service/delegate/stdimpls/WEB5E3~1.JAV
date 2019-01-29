head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.20.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToWLimitIfoSwitchUnitServiceImplTest_submitOptionSettleContractWLimitOrder.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ToWLimitIfoSwitchUnitServiceImplTest_submitOptionSettleContractWLimitOrder.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/06 金傑 新規作成
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoContractOpenOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractOpenOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractSettleOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.ifo.WEB3IfoChangeSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoOpenContractChangeSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToWLimitIfoSwitchUnitServiceImplTest_submitOptionSettleContractWLimitOrder extends TestBaseForMock
{

	private WEB3ToWLimitIfoSwitchUnitServiceImpl l_wLimitIfoSwitchUnitServiceImpl = null;
	
	private IfoContractSettleOrderUnit l_ifoContractSettleOrderUnit = null;
	
	private IfoContractOpenOrderUnit l_ifoContractOpenOrderUnit = null;
	
	/**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToWLimitIfoSwitchUnitServiceImplTest_submitOptionSettleContractWLimitOrder.class); 
    
	public WEB3ToWLimitIfoSwitchUnitServiceImplTest_submitOptionSettleContractWLimitOrder(String name)
	{
		super(name);
	}
	
    protected void setUp() throws Exception
    {
    	super.setUp();
    	TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    	this.l_wLimitIfoSwitchUnitServiceImpl = new WEB3ToWLimitIfoSwitchUnitServiceImplForTest();
    	this.getData();
    	this.getMock();
    }
    
    protected void tearDown() throws Exception
    {
    	super.tearDown();
    }
    
    public void test_submitOptionSettleContractWLimitOrder_C0001()
	{
		final String STR_METHOD_NAME = "test_submitOptionSettleContractWLimitOrder_C0001()";
		log.entering(TEST_START + STR_METHOD_NAME);
		
		TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
		    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
		    "validateChangeSettleContractOrder", 
		    new Class[] { WEB3GentradeSubAccount.class,
            WEB3IfoChangeSettleContractOrderSpec.class, boolean.class },
            new WEB3BaseException(new ErrorInfo(),STR_METHOD_NAME));
        
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("123");
            l_context.setProductCode("0");
            l_context.setBizDateType("1");
            l_context.setMarketCode("SP");
            l_context.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(Calendar.getInstance().getTime());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
		try
		{
			this.l_wLimitIfoSwitchUnitServiceImpl.submitOptionSettleContractWLimitOrder(this.l_ifoContractSettleOrderUnit);
			fail();
		}
		catch (WEB3BaseException l_web3BaseException)
		{
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "validateChangeSettleContractOrder", 
                    new Class[] {WEB3GentradeSubAccount.class,WEB3IfoChangeSettleContractOrderSpec.class,boolean.class});
            assertEquals(WEB3IfoChangeSettleContractOrderSpec.class,l_paramsValue.getFirstCalled()[1].getClass());
		}
		catch(Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		log.exiting(TEST_END + STR_METHOD_NAME);
	}
    
    public void test_submitOptionSettleContractWLimitOrder_C0002()
	{
		final String STR_METHOD_NAME = "test_submitOptionSettleContractWLimitOrder_C0002()";
		log.entering(TEST_START + STR_METHOD_NAME);
				
		OrderValidationResult l_orderValidationResult = OrderValidationResult.VALIDATION_OK_RESULT;
		
		TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
		    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
		    "validateChangeSettleContractOrder", 
		    new Class[] { WEB3GentradeSubAccount.class,
            WEB3IfoChangeSettleContractOrderSpec.class, boolean.class },
            l_orderValidationResult);
			
		TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
		    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
		    "calcChangeEstimateDeliveryAmount", 
		    new Class[]
            {WEB3GentradeCommission.class,
            double.class,
            WEB3GentradeSubAccount.class,
            WEB3IfoTradedProductImpl.class,
            double.class,
            SideEnum.class,
            boolean.class,
            double.class,
            double.class,
            boolean.class
            },
            new WEB3BaseException(new ErrorInfo(),STR_METHOD_NAME));
        

		try
		{
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("123");
            l_context.setProductCode("0");
            l_context.setBizDateType("1");
            l_context.setMarketCode("SP");
            l_context.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(Calendar.getInstance().getTime());
			this.l_wLimitIfoSwitchUnitServiceImpl.submitOptionSettleContractWLimitOrder(this.l_ifoContractSettleOrderUnit);
			fail();
		}
		catch (WEB3BaseException l_web3BaseException)
		{
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "calcChangeEstimateDeliveryAmount", 
                    new Class[]
                              {WEB3GentradeCommission.class,
                              		double.class,
                              		WEB3GentradeSubAccount.class,
                              		WEB3IfoTradedProductImpl.class,
                              		double.class,
                              		SideEnum.class,
                              		boolean.class,
                              		double.class,
                              		double.class,
                              		boolean.class
                              		});
            
            assertEquals("200.0",l_paramsValue.getFirstCalled()[1].toString());
            assertEquals("7000.0",l_paramsValue.getFirstCalled()[4].toString());
		}
		catch(Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		log.exiting(TEST_END + STR_METHOD_NAME);
	}
    
    public void test_submitOptionOpenContractWLimitOrder_C0001()
	{
		final String STR_METHOD_NAME = "test_submitOptionOpenContractWLimitOrder_C0001()";
		log.entering(TEST_START + STR_METHOD_NAME);
		TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
				"webbroker3.ifo.WEB3FuturesOrderManagerImpl",
				"validateChangeOrder", 
				new Class[] { WEB3GentradeSubAccount.class,
						WEB3IfoOpenContractChangeSpec.class, boolean.class },
						new WEB3BaseException(new ErrorInfo(),STR_METHOD_NAME));
    	
		try
		{
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("123");
            l_context.setProductCode("0");
            l_context.setBizDateType("1");
            l_context.setMarketCode("SP");
            l_context.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(Calendar.getInstance().getTime());
			this.l_wLimitIfoSwitchUnitServiceImpl.submitOptionOpenContractWLimitOrder(this.l_ifoContractOpenOrderUnit);
			fail();
		}
		catch (WEB3BaseException l_web3BaseException)
		{
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "validateChangeOrder", 
                    new Class[] {WEB3GentradeSubAccount.class,WEB3IfoOpenContractChangeSpec.class,boolean.class});
            assertEquals(WEB3IfoOpenContractChangeSpec.class,l_paramsValue.getFirstCalled()[1].getClass());
		}
		catch(Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		log.exiting(TEST_END + STR_METHOD_NAME);
	}
    
    public void test_submitOptionOpenContractWLimitOrder_C0002()
	{
		final String STR_METHOD_NAME = "test_submitOptionOpenContractWLimitOrder_C0002()";
		log.entering(TEST_START + STR_METHOD_NAME);
		
		
		TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
				"webbroker3.ifo.WEB3FuturesOrderManagerImpl",
				"validateChangeOrder", 
				new Class[] { WEB3GentradeSubAccount.class,
						WEB3IfoOpenContractChangeSpec.class, boolean.class },
						OrderValidationResult.VALIDATION_OK_RESULT);
		
		TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcChangeEstimateDeliveryAmount", 
                new Class[]
                {WEB3GentradeCommission.class,
                		double.class,
                		WEB3GentradeSubAccount.class,
                		WEB3IfoTradedProductImpl.class,
                		double.class,
                		SideEnum.class,
                		boolean.class,
                		double.class,
                		double.class,
                		boolean.class
                		},
                		new WEB3BaseException(new ErrorInfo(),STR_METHOD_NAME));
    	
		try
		{
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("123");
            l_context.setProductCode("0");
            l_context.setBizDateType("1");
            l_context.setMarketCode("SP");
            l_context.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(Calendar.getInstance().getTime());
			this.l_wLimitIfoSwitchUnitServiceImpl.submitOptionOpenContractWLimitOrder(this.l_ifoContractOpenOrderUnit);
			fail();
		}
		catch (WEB3BaseException l_web3BaseException)
		{
			 WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
	                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
	                    "calcChangeEstimateDeliveryAmount", 
	                    new Class[]{
	                            WEB3GentradeCommission.class,
	                      		double.class,
	                      		WEB3GentradeSubAccount.class,
	                      		WEB3IfoTradedProductImpl.class,
	                      		double.class,
	                      		SideEnum.class,
	                      		boolean.class,
	                      		double.class,
	                      		double.class,
	                      		boolean.class
	                              		});
	            assertEquals("200.0",l_paramsValue.getFirstCalled()[1].toString());
	            assertEquals("2000.0",l_paramsValue.getFirstCalled()[4].toString());
		}
		catch(Exception l_ex)
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
		    Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR, 2007);
            l_calendar.set(Calendar.MONTH, 01);
            l_calendar.set(Calendar.DAY_OF_MONTH, 10);
            l_calendar.set(Calendar.HOUR_OF_DAY, 0);
            l_calendar.set(Calendar.MINUTE, 0);
            l_calendar.set(Calendar.SECOND, 0);
            l_calendar.set(Calendar.MILLISECOND, 0);
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                new Timestamp(l_calendar.getTimeInMillis()));
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate", null);

//            WEB3GentradeTradingClendarContext l_clendarContext = null;
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

			IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
			QueryProcessor l_processor = Processors.getDefaultProcessor();
			l_processor.doDeleteAllQuery(l_ifoOrderUnitParams.getRowType());
			
			IfoOrderUnitParams l_ifoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
			l_ifoOrderUnitParams1.setOrderUnitId(20002777L);
	        l_ifoOrderUnitParams1.setOrderType(OrderTypeEnum.EQUITY_BUY);
	        l_ifoOrderUnitParams1.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
	        l_ifoOrderUnitParams1.setWLimitPrice(200);
	        l_ifoOrderUnitParams1.setQuantity(2000);
            Timestamp dd = GtlUtils.getSystemTimestamp();
	        l_ifoOrderUnitParams1.setBizDate(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"));
	        l_ifoOrderUnitParams1.setTraderId(null);
	        l_ifoOrderUnitParams1.setProductId(1000126L);
	        TestDBUtility.insertWithDel(l_ifoOrderUnitParams1);
	        
			SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(10100101001007L);
	        TestDBUtility.insertWithDel(l_subAccountParams);
	        
	        MarketParams l_marketParams = TestDBUtility.getMarketRow();
	        l_marketParams.setMarketId(1002);
	        TestDBUtility.insertWithDel(l_marketParams);
	        
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1000126L);
            TestDBUtility.insertWithDel(l_productParams);
            
            IfoProductParams l_ifoProductParams1 = TestDBUtility.getIfoProductRow();
            l_ifoProductParams1.setProductId(1000126L);
            l_ifoProductParams1.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ifoProductParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ifoProductParams1);
            
            IfoClosingContractSpecParams l_ifoClosingContractSpecParams = new IfoClosingContractSpecParams();
            l_ifoClosingContractSpecParams.setClosingContractSpecId(20002777L);
            l_ifoClosingContractSpecParams.setAccountId(333812512126L);
            l_ifoClosingContractSpecParams.setSubAccountId(33381251220126L);
            l_ifoClosingContractSpecParams.setOrderId(20002777L);
            l_ifoClosingContractSpecParams.setOrderUnitId(20002777L);
            l_ifoClosingContractSpecParams.setContractId(123456L);
            l_ifoClosingContractSpecParams.setQuantity(7000L);
            l_ifoClosingContractSpecParams.setClosingSerialNo(123);// getExecutedQuantity
            l_ifoClosingContractSpecParams.setExecutedQuantity(2000L);
            l_ifoClosingContractSpecParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ifoClosingContractSpecParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);
            
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(123456L);
            l_ifoContractParams.setQuantity(7000L);
            TestDBUtility.insertWithDel(l_ifoContractParams);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            long l_lngOrderUnitId = 20002777L;
            this.l_ifoContractSettleOrderUnit = new IfoContractSettleOrderUnitImpl(l_lngOrderUnitId);
            this.l_ifoContractOpenOrderUnit = new  IfoContractOpenOrderUnitImpl(l_lngOrderUnitId);
		}
		catch (WEB3BaseException l_web3BaseException)
		{
			log.error(STR_METHOD_NAME, l_web3BaseException);
			fail();
		}
		catch(Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		
		log.exiting(TEST_END + STR_METHOD_NAME);
	}
    private void getMock(){
    	
		final String STR_METHOD_NAME = "getMock()";
		log.entering(TEST_START + STR_METHOD_NAME);
    	try{
    	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "getOrderUnit",
                new Class[] {long.class},
                this.l_ifoContractSettleOrderUnit);
    	
    	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getIfoTradedProduct",
                new Class[] {Institution.class, String.class, String.class},
                    null);
    	
    	}
		catch(Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3ToWLimitIfoSwitchUnitServiceImplForTest extends WEB3ToWLimitIfoSwitchUnitServiceImpl
	{
		public boolean isProcessObject(IfoOrderUnit l_orderUnit) throws WEB3BaseException
		{
			return true;
		}

		public void validateSwitchPossible(IfoOrderUnit l_orderUnit) throws WEB3BaseException
		{

		}
		
	    public void updateSwitchFail(IfoOrderUnit l_orderUnit, String l_strErrorReasonCode)
        throws WEB3BaseException
        {
	    	throw new WEB3BaseException(new ErrorInfo(),"error");
        }
	}
}
@

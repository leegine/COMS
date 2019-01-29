head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.01.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityOrderManagerForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3EquityOrderManagerForMock.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/01/16  金傑　@(中訊) 新規作成
 */
package webbroker3.equity;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeCancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewCashBasedOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOpenContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidator;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.DefaultOrderValidatorImplForMock;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.data.OffFloorOrderProductParams;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityOrderManagerForMock extends WEB3EquityOrderManager
{
	/**
	 * ログ出力ユーティリティ。<BR>
	 */
	private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityOrderManagerForMock.class);

	public List getOrderUnits(WEB3GentradeSubAccount l_genSubAccount, ProductTypeEnum l_productTypeEnum,
			String l_strSearchCond, Object[] l_searchCondContainers, String l_strSortCond) throws WEB3BaseException
	{
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "getOrderUnits",
                new Class[] { WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, Object[].class,
                    String.class },
                new Object[]{l_genSubAccount, l_productTypeEnum, l_strSearchCond, l_searchCondContainers, l_strSortCond});

		if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager", "getOrderUnits",
				new Class[] { WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, Object[].class,
						String.class }))
		{
			log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.getOrderUnits(WEB3GentradeSubAccount, "
					+ "ProductTypeEnum,String,Object[],String)");
			
			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.equity.WEB3EquityOrderManager",
					"getOrderUnits",
					new Class[] { WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, Object[].class,
							String.class }).asWEB3BaseException();
			
			return (List) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.equity.WEB3EquityOrderManager",
					"getOrderUnits",
					new Class[] { WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, Object[].class,
							String.class }).asObject();
		}
		return super.getOrderUnits(l_genSubAccount, l_productTypeEnum, l_strSearchCond, l_searchCondContainers,
				l_strSortCond);
	}
	
    public EqTypeOrderUnit[] removeCarryOverOriginalOrderUnit(EqTypeOrderUnit[] l_orderUnitList)
			throws WEB3SystemLayerException
	{
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "removeCarryOverOriginalOrderUnit",
            new Class[] { EqTypeOrderUnit[].class },
            new Object[]{l_orderUnitList});

    	if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager", 
    			"removeCarryOverOriginalOrderUnit",
				new Class[] {EqTypeOrderUnit[].class}))
		{
			log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.removeCarryOverOriginalOrderUnit(EqTypeOrderUnit[])");
			return (EqTypeOrderUnit[]) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.equity.WEB3EquityOrderManager",
					"removeCarryOverOriginalOrderUnit",
					new Class[] {EqTypeOrderUnit[].class}).asObject();
		}
    	return super.removeCarryOverOriginalOrderUnit(l_orderUnitList);
	}
    
    public TradedProduct validateTradedProduct(EqTypeProduct l_eqTypeProduct, Market l_market) throws WEB3BaseException
	{
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateTradedProduct",
            new Class[] {EqTypeProduct.class, Market.class},
            new Object[]{l_eqTypeProduct, l_market});

    	if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager", 
    			"validateTradedProduct",
				new Class[] {EqTypeProduct.class,Market.class}))
		{
			log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.validateTradedProduct(EqTypeProduct,Market)");
			
			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.equity.WEB3EquityOrderManager",
					"validateTradedProduct",
					new Class[] {EqTypeProduct.class,Market.class}).asWEB3BaseException();
			
			return (TradedProduct) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.equity.WEB3EquityOrderManager",
					"validateTradedProduct",
					new Class[] {EqTypeProduct.class,Market.class}).asObject();
		}
    	return super.validateTradedProduct(l_eqTypeProduct, l_market);
	}
    
    public void validateHandlingMarket(WEB3GentradeBranch l_branch, WEB3EquityTradedProduct l_tradedProduct,
			String l_strMarketCode, String l_strRepaymentType, double l_dblRepaymentNum) throws WEB3BaseException
	{
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateHandlingMarket",
            new Class[] {WEB3GentradeBranch.class,WEB3EquityTradedProduct.class,String.class,String.class,double.class},
            new Object[]{l_branch, l_tradedProduct, l_strMarketCode, l_strRepaymentType, new Double(l_dblRepaymentNum)});

    	if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager", 
    			"validateHandlingMarket",
				new Class[] {WEB3GentradeBranch.class,WEB3EquityTradedProduct.class,String.class,String.class,double.class}))
    	{
    		log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.validateHandlingMarket(WEB3GentradeBranch,WEB3EquityTradedProduct,String,String,double)");
    		

			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.equity.WEB3EquityOrderManager",
					"validateHandlingMarket",
					new Class[] {WEB3GentradeBranch.class,
							WEB3EquityTradedProduct.class,
							String.class,
							String.class,
							double.class}).asWEB3BaseException();
			
			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.equity.WEB3EquityOrderManager",
					"validateHandlingMarket",
					new Class[] {WEB3GentradeBranch.class,
							WEB3EquityTradedProduct.class,
							String.class,
							String.class,
							double.class}).asVoid();
			return;
		}
    	super.validateHandlingMarket(l_branch, l_tradedProduct, l_strMarketCode, l_strRepaymentType, l_dblRepaymentNum);
	}
    
    public void validateOrderForCancellation(Order l_order) throws WEB3BaseException
	{
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateOrderForCancellation",
            new Class[] {Order.class},
            new Object[]{l_order});

    	if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager", 
    			"validateOrderForCancellation",
				new Class[] {Order.class}))
    	{
			log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.validateOrderForCancellation(Order)");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateOrderForCancellation",
                    new Class[] {Order.class}).asWEB3BaseException();

            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.equity.WEB3EquityOrderManager",
					"validateOrderForCancellation",
					new Class[] {Order.class}).asVoid();
			return;
    	}
    	super.validateOrderForCancellation(l_order);
	}
    
	public double getEstimateDeliveryAmountForClose(EqTypeOrderUnit l_eqtypeOrderUnit) throws WEB3BaseException
	{
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "getEstimateDeliveryAmountForClose",
            new Class[] {EqTypeOrderUnit.class},
            new Object[]{l_eqtypeOrderUnit});

    	if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager", 
    			"getEstimateDeliveryAmountForClose",
				new Class[] {EqTypeOrderUnit.class}))
    	{
			log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.getEstimateDeliveryAmountForClose(EqTypeOrderUnit)");
			
			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.equity.WEB3EquityOrderManager",
					"getEstimateDeliveryAmountForClose",
					new Class[] {EqTypeOrderUnit.class}).asWEB3BaseException();
			
			return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.equity.WEB3EquityOrderManager",
					"getEstimateDeliveryAmountForClose",
					new Class[] {EqTypeOrderUnit.class}).asDouble();
    	}
		return super.getEstimateDeliveryAmountForClose(l_eqtypeOrderUnit);
	}
	
    public void validateHandlingMarket(WEB3GentradeBranch l_branch, TradedProduct l_tradedProduct)
			throws WEB3BaseException
	{
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateHandlingMarket",
            new Class[] {WEB3GentradeBranch.class,TradedProduct.class},
            new Object[]{l_branch, l_tradedProduct});

    	if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager", 
    			"validateHandlingMarket",
				new Class[] {WEB3GentradeBranch.class,TradedProduct.class}))
    	{
    		log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.validateHandlingMarket(WEB3GentradeBranch,TradedProduct)");
    		
			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.equity.WEB3EquityOrderManager",
					"validateHandlingMarket",
					new Class[] {WEB3GentradeBranch.class,
							TradedProduct.class}).asWEB3BaseException();
			
			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.equity.WEB3EquityOrderManager",
					"validateHandlingMarket",
					new Class[] {WEB3GentradeBranch.class,
							TradedProduct.class}).asVoid();
			return;
		}
    	super.validateHandlingMarket(l_branch, l_tradedProduct);
	}
    
    
    public OrderValidator getOrderValidator()
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager", 
            "getOrderValidator",
            new Class[] {}))
        {
            log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.getOrderValidator()");
            return (DefaultOrderValidatorImplForMock)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "getOrderValidator",
                new Class[] {}).asObject();
        }
        return super.getOrderValidator();
    }
    
    public void validateOrderForChangeability(Order l_order) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateOrderForChangeability",
            new Class[] {Order.class},
            new Object[]{l_order});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager", 
                "validateOrderForChangeability",
                new Class[] {Order.class}))
        {
            log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.validateOrderForChangeability(Order)");
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateOrderForChangeability",
                    new Class[] {Order.class}).asWEB3BaseException();
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateOrderForChangeability",
                    new Class[] {Order.class}).asVoid();
            return;
        }
        super.validateOrderForChangeability(l_order);
    }
    
    public void validateInsider(
        SubAccount l_subAccount,
        EqTypeProduct l_eqtyprProduct) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateInsider",
            new Class[] {SubAccount.class,
                EqTypeProduct.class},
            new Object[]{l_subAccount,
                l_eqtyprProduct});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager", 
            "validateInsider",
            new Class[] {SubAccount.class, EqTypeProduct.class}))
        {
            log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.validateInsider(SubAccount, EqTypeProduct)");
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateInsider",
                    new Class[] {SubAccount.class, EqTypeProduct.class}).asWEB3BaseException();
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateInsider",
                    new Class[] {SubAccount.class, EqTypeProduct.class}).asVoid();

            return;
        }
    super.validateInsider(l_subAccount, l_eqtyprProduct);

    }
    
    public void validateAccountProductOrderStop(SubAccount l_subAccount,
        long l_lngProductId,
        OrderTypeEnum l_orderType)
        throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateAccountProductOrderStop",
            new Class[] {SubAccount.class,long.class, OrderTypeEnum.class},
            new Object[]{l_subAccount, new Long(l_lngProductId), l_orderType});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager", 
            "validateAccountProductOrderStop",
            new Class[] {SubAccount.class,
            long.class,
            OrderTypeEnum.class}))
    {
        log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.validateAccountProductOrderStop(SubAccount, long, OrderTypeEnum)");
        
        TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateAccountProductOrderStop",
                new Class[] {SubAccount.class, long.class, OrderTypeEnum.class}).asWEB3BaseException();
        
        TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateAccountProductOrderStop",
                new Class[] {SubAccount.class, long.class, OrderTypeEnum.class}).asVoid();
        return;
    }
    super.validateAccountProductOrderStop(l_subAccount, l_lngProductId, l_orderType);

    }
    
    public WEB3EquityTradedProduct validateTradedProductForMarginTrading(
        SubAccount l_subAccount,
        WEB3EquityProduct l_product, 
        WEB3GentradeMarket l_market, 
        WEB3GentradeBranch l_branch, 
        String l_strRepaymentType, 
        OrderCategEnum l_orderCateg, 
        boolean l_isShort,
        boolean l_isTradeStopCheck) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateTradedProductForMarginTrading",
            new Class[] {SubAccount.class,
                WEB3EquityProduct.class, 
                WEB3GentradeMarket.class, 
                WEB3GentradeBranch.class, 
                String.class, 
                OrderCategEnum.class, 
                boolean.class,
                boolean.class},
            new Object[]{l_subAccount,
                l_product, 
                l_market, 
                l_branch, 
                l_strRepaymentType, 
                l_orderCateg, 
                new Boolean(l_isShort),
                new Boolean(l_isTradeStopCheck)});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager", 
            "validateTradedProductForMarginTrading",
            new Class[] {SubAccount.class,
            WEB3EquityProduct.class, 
            WEB3GentradeMarket.class, 
            WEB3GentradeBranch.class, 
            String.class, 
            OrderCategEnum.class, 
            boolean.class,
            boolean.class}))
        {
            log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.validateTradedProductForMarginTrading(" +
                    "SubAccount, WEB3EquityProduct, WEB3GentradeMarket, WEB3GentradeBranch," +
                    "String, OrderCategEnum, boolean, boolean)");            
            
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateTradedProductForMarginTrading",
                new Class[] {SubAccount.class,
                    WEB3EquityProduct.class, 
                    WEB3GentradeMarket.class, 
                    WEB3GentradeBranch.class, 
                    String.class, 
                    OrderCategEnum.class, 
                    boolean.class,
                    boolean.class}).asWEB3BaseException();

            return (WEB3EquityTradedProduct)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateTradedProductForMarginTrading",
                new Class[] {SubAccount.class,
                    WEB3EquityProduct.class, 
                    WEB3GentradeMarket.class, 
                    WEB3GentradeBranch.class, 
                    String.class, 
                    OrderCategEnum.class, 
                    boolean.class,
                    boolean.class}).asObject();
        
        }
        return super.validateTradedProductForMarginTrading(
            l_subAccount,
            l_product, 
            l_market, 
            l_branch, 
            l_strRepaymentType, 
            l_orderCateg, 
            l_isShort,
            l_isTradeStopCheck);
    }
    
    public OffFloorOrderProductParams validateOffFloorOrderPossible(long l_lngProductId, long l_lngMarketId,
            SubAccount l_subAccount) throws WEB3BaseException
    {

    	TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateOffFloorOrderPossible",
                new Class[]{long.class,long.class,SubAccount.class},
                new Object[]{new Long(l_lngProductId),new Long(l_lngMarketId),l_subAccount});
                
    	if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager",
                "validateOffFloorOrderPossible", new Class[] {long.class, long.class, SubAccount.class}))
        {
            log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.validateOffFloorOrderPossible(long,long,SubAccount)");
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "validateOffFloorOrderPossible",
                    new Class[] {long.class, long.class, SubAccount.class}).asWEB3BaseException();
            
            return (OffFloorOrderProductParams) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "validateOffFloorOrderPossible",
                    new Class[] {long.class, long.class, SubAccount.class}).asObject();
        }
        return super.validateOffFloorOrderPossible(l_lngProductId, l_lngMarketId, l_subAccount);
    }

    /**
     * (notifyルールエンジンサーバ(Mock))<BR>
     * （OrderManagerPersistenceContextにて定数定義）
     * @@throws WEB3BaseException
     */
    public void notifyRLS(
        EqTypeOrderUnit l_orderUnit,
        OrderManagerPersistenceContext l_context)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "notifyRLS(EqTypeOrderUnit, OrderManagerPersistenceContext)-->ForMock";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "notifyRLS",
            new Class[]{EqTypeOrderUnit.class,OrderManagerPersistenceContext.class},
            new Object[]{l_orderUnit, l_context});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager",
                "notifyRLS",  new Class[]{EqTypeOrderUnit.class,OrderManagerPersistenceContext.class}))
        {
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "notifyRLS",
                    new Class[]{EqTypeOrderUnit.class,OrderManagerPersistenceContext.class}).asWEB3BaseException();

             TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "notifyRLS",
                    new Class[]{EqTypeOrderUnit.class,OrderManagerPersistenceContext.class}).asObject();

             return;
        }
        log.exiting(STR_METHOD_NAME);
        super.notifyRLS(l_orderUnit, l_context);
    }
    
    public OrderUnit[] getOrderUnits(long l_orderId)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "getOrderUnits",
                new Class[] {long.class},
                new Object[]{new Long(l_orderId)});
     
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager", "getOrderUnits",
                new Class[] {long.class}))
        {
            log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.getOrderUnits(long)");
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "getOrderUnits",
                    new Class[] { long.class }).asObject();
            
            return (OrderUnit[]) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "getOrderUnits",
                    new Class[] { long.class }).asObject();
        }
        return super.getOrderUnits(l_orderId);
    }

    /**
     * (validate返済注文(mock))<BR>
     * 指定返済注文の発注審査を行う。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@param l_settleContractOrderSpec - (信用返済注文内容)<BR>
     * 信用返済注文内容オブジェクト。
     * @@param l_contract - (建株)<BR>
     * 建株オブジェクト。
     * @@return EqTypeNewOrderValidationResult
     */
    public EqTypeNewOrderValidationResult validateSettleContractOrder(
        SubAccount l_subAccount, 
        EqTypeSettleContractOrderSpec l_settleContractOrderSpec,
        WEB3EquityContract l_contract) 
    {
        String STR_METHOD_NAME = 
            "validateSettleContractOrder(WEB3GentradeSubAccount, EqTypeSettleContractOrderSpec, WEB3EquityContract)--Formock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateSettleContractOrder",
            new Class[] {SubAccount.class, EqTypeSettleContractOrderSpec.class, WEB3EquityContract.class},
            new Object[]{l_subAccount, l_settleContractOrderSpec, l_contract});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateSettleContractOrder",
            new Class[] {SubAccount.class, EqTypeSettleContractOrderSpec.class, WEB3EquityContract.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            //3)MockFor --〉 asObject
            return (EqTypeNewOrderValidationResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateSettleContractOrder",
                new Class[] {SubAccount.class, EqTypeSettleContractOrderSpec.class, WEB3EquityContract.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.validateSettleContractOrder(l_subAccount, l_settleContractOrderSpec, l_contract);
    }

    /**
     * （calc概算決済損益代金(mock)）<BR>
     */
    public WEB3EquityRealizedProfitAndLossPrice calcEstimatedRealizedProfitAndLossAmount(
        WEB3GentradeCommission l_genCommission,
        double l_dblLimitPrice,
        WEB3GentradeSubAccount l_genSubAccount,
        WEB3EquityTradedProduct l_equityTradedProduct,
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys,
        double l_dblQuantity,
        EqTypeOrderUnit l_orderUnit,
        double l_dblNowExecQuantity,
        double l_dblNowExecPrice,
        boolean l_isSkipAmountRange,
        WEB3EquityContract l_contract)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcEstimatedRealizedProfitAndLossAmount(WEB3GentradeCommission, " +
            "double, WEB3GentradeSubAccount, WEB3EquityTradedProduct, " +
            "EqTypeSettleContractOrderEntry[], double, EqTypeOrderUnit, double, double, boolean, WEB3EquityContract)";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "calcEstimatedRealizedProfitAndLossAmount",
            new Class[] {
                WEB3GentradeCommission.class, double.class,
                WEB3GentradeSubAccount.class, WEB3EquityTradedProduct.class,
                EqTypeSettleContractOrderEntry[].class, double.class,
                EqTypeOrderUnit.class,
                double.class,
                double.class,
                boolean.class,
                WEB3EquityContract.class
                },
            new Object[]{               
                    l_genCommission,
                    new Double(l_dblLimitPrice),
                    l_genSubAccount,
                    l_equityTradedProduct,
                    l_settleContractOrderEntrys,
                    new Double(l_dblQuantity),
                    l_orderUnit,
                    new Double(l_dblNowExecQuantity),
                    new Double(l_dblNowExecPrice),
                    new Boolean(l_isSkipAmountRange),
                    l_contract});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.equity.WEB3EquityOrderManager",
            "calcEstimatedRealizedProfitAndLossAmount",
            new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    WEB3GentradeSubAccount.class, WEB3EquityTradedProduct.class,
                    EqTypeSettleContractOrderEntry[].class, double.class,
                    EqTypeOrderUnit.class,
                    double.class,
                    double.class,
                    boolean.class,
                    WEB3EquityContract.class
                    }))
        {
            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcEstimatedRealizedProfitAndLossAmount",
                new Class[] {
                        WEB3GentradeCommission.class, double.class,
                        WEB3GentradeSubAccount.class, WEB3EquityTradedProduct.class,
                        EqTypeSettleContractOrderEntry[].class, double.class,
                        EqTypeOrderUnit.class,
                        double.class,
                        double.class,
                        boolean.class,
                        WEB3EquityContract.class
                        }).asWEB3BaseException();

            //3)MockFor --〉 asBoolean
            log.exiting(STR_METHOD_NAME);
            return (WEB3EquityRealizedProfitAndLossPrice)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcEstimatedRealizedProfitAndLossAmount",
                new Class[] {
                        WEB3GentradeCommission.class, double.class,
                        WEB3GentradeSubAccount.class, WEB3EquityTradedProduct.class,
                        EqTypeSettleContractOrderEntry[].class, double.class,
                        EqTypeOrderUnit.class,
                        double.class,
                        double.class,
                        boolean.class,
                        WEB3EquityContract.class
                        }).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.calcEstimatedRealizedProfitAndLossAmount(
                l_genCommission,
                l_dblLimitPrice,
                l_genSubAccount,
                l_equityTradedProduct,
                l_settleContractOrderEntrys,
                l_dblQuantity,
                l_orderUnit,
                l_dblNowExecQuantity,
                l_dblNowExecPrice,
                l_isSkipAmountRange,
                l_contract);
    }

    /**
     * (get注文エラー理由コード)<BR>
     * <BR>
     */
    public String getErrorReasonCode(
        String l_strErrorCode)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getErrorReasonCode(String)";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "getErrorReasonCode",
                new Class[]{String.class},
                new Object[]{l_strErrorCode});

            if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.equity.WEB3EquityOrderManager",
                "getErrorReasonCode",
                new Class[]{String.class}))
            {
                //2）MockFor --〉 asWEB3BaseException
                log.exiting(STR_METHOD_NAME);
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "getErrorReasonCode",
                    new Class[]{String.class}).asWEB3BaseException();

                //3)MockFor --〉 asObject
                return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "getErrorReasonCode",
                    new Class[]{String.class}).asObject();
                
            }

            log.exiting(STR_METHOD_NAME);
            return super.getErrorReasonCode(l_strErrorCode);

    }
    
    public EqTypeOrderSubmissionResult submitSettleContractOrder(SubAccount subAccount, EqTypeSettleContractOrderSpec spec, long orderId, String tradingPassword, boolean skipOrderValidation)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "submitSettleContractOrder",
                new Class[]{SubAccount.class,EqTypeSettleContractOrderSpec.class,long.class,String.class,boolean.class},
                new Object[]{subAccount,spec,new Long(orderId),tradingPassword,new Boolean(skipOrderValidation)});
        
        if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.equity.WEB3EquityOrderManager",
                "submitSettleContractOrder",
                new Class[]{SubAccount.class,EqTypeSettleContractOrderSpec.class,long.class,String.class,boolean.class}))
        {
            log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.submitSettleContractOrder");
            
            return (EqTypeOrderSubmissionResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "submitSettleContractOrder",
                    new Class[]{SubAccount.class,EqTypeSettleContractOrderSpec.class,long.class,String.class,boolean.class}).asObject();
        }
        
        return super.submitSettleContractOrder(subAccount,spec,orderId,tradingPassword,skipOrderValidation);
    }
    
    public WEB3EquityRealizedProfitAndLossPrice calcEstimatedRealizedProfitAndLossAmount(
            WEB3GentradeCommission l_genCommission,
            double l_dblLimitPrice,
            WEB3GentradeSubAccount l_genSubAccount,
            WEB3EquityTradedProduct l_equityTradedProduct,
            EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys,
            double l_dblQuantity,
            EqTypeOrderUnit l_orderUnit,
            double l_dblNowExecQuantity,
            double l_dblNowExecPrice,
            boolean l_isSkipAmountRange)
            throws WEB3BaseException
        {
            
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcEstimatedRealizedProfitAndLossAmount",
                new Class[]{WEB3GentradeCommission.class,
                            double.class,
                            WEB3GentradeSubAccount.class,
                            WEB3EquityTradedProduct.class,
                            EqTypeSettleContractOrderEntry[].class,
                            double.class,
                            EqTypeOrderUnit.class,
                            double.class,
                            double.class,
                            boolean.class},
                new Object[]{l_genCommission,
                             new Double(l_dblLimitPrice),
                             l_genSubAccount,
                             l_equityTradedProduct,
                             l_settleContractOrderEntrys,
                             new Double(l_dblQuantity),
                             l_orderUnit,
                             new Double(l_dblNowExecQuantity),
                             new Double(l_dblNowExecPrice),
                             new Boolean(l_isSkipAmountRange)});
        
        if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcEstimatedRealizedProfitAndLossAmount",
                new Class[]{WEB3GentradeCommission.class,
                        double.class,
                        WEB3GentradeSubAccount.class,
                        WEB3EquityTradedProduct.class,
                        EqTypeSettleContractOrderEntry[].class,
                        double.class,
                        EqTypeOrderUnit.class,
                        double.class,
                        double.class,
                        boolean.class}))
        {
            log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.calcEstimatedRealizedProfitAndLossAmount");
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimatedRealizedProfitAndLossAmount",
                    new Class[]{WEB3GentradeCommission.class,
                            double.class,
                            WEB3GentradeSubAccount.class,
                            WEB3EquityTradedProduct.class,
                            EqTypeSettleContractOrderEntry[].class,
                            double.class,
                            EqTypeOrderUnit.class,
                            double.class,
                            double.class,
                            boolean.class}).asWEB3BaseException();
            
            return (WEB3EquityRealizedProfitAndLossPrice)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimatedRealizedProfitAndLossAmount",
                    new Class[]{WEB3GentradeCommission.class,
                            double.class,
                            WEB3GentradeSubAccount.class,
                            WEB3EquityTradedProduct.class,
                            EqTypeSettleContractOrderEntry[].class,
                            double.class,
                            EqTypeOrderUnit.class,
                            double.class,
                            double.class,
                            boolean.class}).asObject();
            
        }
        return super.calcEstimatedRealizedProfitAndLossAmount(
                l_genCommission,
                l_dblLimitPrice,
                l_genSubAccount,
                l_equityTradedProduct,
                l_settleContractOrderEntrys,
                l_dblQuantity,
                l_orderUnit,
                l_dblNowExecQuantity,
                l_dblNowExecPrice,
                l_isSkipAmountRange);
        }
    
    public void validateMarginOrder(WEB3GentradeSubAccount l_genSubAccount, String l_strRepaymentType)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityOrderManager",
                "validateMarginOrder", new Class[]
                { WEB3GentradeSubAccount.class, String.class }, new Object[]
                { l_genSubAccount, l_strRepaymentType });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager", "validateMarginOrder",
                new Class[]
                { WEB3GentradeSubAccount.class, String.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.validateMarginOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "validateMarginOrder", new Class[]
                    { WEB3GentradeSubAccount.class, String.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "validateMarginOrder", new Class[]
                    { WEB3GentradeSubAccount.class, String.class }).asVoid();
            return;
        }
        super.validateMarginOrder(l_genSubAccount, l_strRepaymentType);
    }
    
    public void validateMarginSpecialAccountOpen(WEB3GentradeSubAccount l_subAccount, TaxTypeEnum l_taxType,
            Date l_datDeliveryDate) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityOrderManager",
                "validateMarginSpecialAccountOpen", new Class[]
                { WEB3GentradeSubAccount.class, TaxTypeEnum.class, Date.class }, new Object[]
                { l_subAccount, l_taxType, l_datDeliveryDate });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager",
                "validateMarginSpecialAccountOpen", new Class[]
                { WEB3GentradeSubAccount.class, TaxTypeEnum.class, Date.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.validateMarginSpecialAccountOpen()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "validateMarginSpecialAccountOpen", new Class[]
                    { WEB3GentradeSubAccount.class, TaxTypeEnum.class, Date.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "validateMarginSpecialAccountOpen", new Class[]
                    { WEB3GentradeSubAccount.class, TaxTypeEnum.class, Date.class }).asVoid();
            return;
        }
        super.validateMarginSpecialAccountOpen(l_subAccount, l_taxType, l_datDeliveryDate);
    }
    
    public String[] getOrderPriceDivs(Branch l_branch, EqTypeTradedProduct l_tradedProduct) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityOrderManager",
                "getOrderPriceDivs", new Class[]
                { Branch.class, EqTypeTradedProduct.class }, new Object[]
                { l_branch, l_tradedProduct });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager", "getOrderPriceDivs",
                new Class[]
                { Branch.class, EqTypeTradedProduct.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.getOrderPriceDivs()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "getOrderPriceDivs", new Class[]
                    { Branch.class, EqTypeTradedProduct.class }).asWEB3BaseException();
            return (String[]) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "getOrderPriceDivs", new Class[]
                    { Branch.class, EqTypeTradedProduct.class }).asObject();
        }
        return super.getOrderPriceDivs(l_branch, l_tradedProduct);
    }
    
    public WEB3EquityEstimatedContractPrice calcContractAmountAtOrder(WEB3GentradeCommission l_commission,
            double l_dblLimitPrice, double l_dblWLimitPrice, double l_dblStopOrderBasePrice,
            EqTypeExecutionConditionType l_execConditionType, EqTypeExecutionConditionType l_execWConditionType,
            String l_strPriceConditionType, String l_strOrderConditionType, String l_strCheckCurrentPrice,
            boolean l_blnIsStopOrderValid, boolean l_blnIsShort, SubAccount l_subAccount,
            EqTypeTradedProduct l_eqTypeTradedProduct, double l_dblQuantity, double l_dblExecQuantity,
            double l_bdlExecutedAmount, boolean l_blnIsSkipAmountCheck) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityOrderManager",
                "calcContractAmountAtOrder", new Class[]
                { WEB3GentradeCommission.class, double.class, double.class, double.class,
                        EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class, String.class,
                        String.class, String.class, boolean.class, boolean.class, SubAccount.class,
                        EqTypeTradedProduct.class, double.class, double.class, double.class, boolean.class },
                new Object[]
                { l_commission, new Double(l_dblLimitPrice), new Double(l_dblWLimitPrice),
                        new Double(l_dblStopOrderBasePrice), l_execConditionType, l_execWConditionType,
                        l_strPriceConditionType, l_strOrderConditionType, l_strCheckCurrentPrice,
                        new Boolean(l_blnIsStopOrderValid), new Boolean(l_blnIsShort), l_subAccount,
                        l_eqTypeTradedProduct, new Double(l_dblQuantity), new Double(l_dblExecQuantity),
                        new Double(l_bdlExecutedAmount), new Boolean(l_blnIsSkipAmountCheck) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager",
                "calcContractAmountAtOrder", new Class[]
                { WEB3GentradeCommission.class, double.class, double.class, double.class,
                        EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class, String.class,
                        String.class, String.class, boolean.class, boolean.class, SubAccount.class,
                        EqTypeTradedProduct.class, double.class, double.class, double.class, boolean.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.calcContractAmountAtOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcContractAmountAtOrder",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class, String.class,
                            String.class, String.class, boolean.class, boolean.class, SubAccount.class,
                            EqTypeTradedProduct.class, double.class, double.class, double.class, boolean.class })
                    .asWEB3BaseException();
            return (WEB3EquityEstimatedContractPrice) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcContractAmountAtOrder",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class, String.class,
                            String.class, String.class, boolean.class, boolean.class, SubAccount.class,
                            EqTypeTradedProduct.class, double.class, double.class, double.class, boolean.class })
                    .asObject();
        }
        return super.calcContractAmountAtOrder(l_commission, l_dblLimitPrice, l_dblWLimitPrice,
                l_dblStopOrderBasePrice, l_execConditionType, l_execWConditionType, l_strPriceConditionType,
                l_strOrderConditionType, l_strCheckCurrentPrice, l_blnIsStopOrderValid, l_blnIsShort, l_subAccount,
                l_eqTypeTradedProduct, l_dblQuantity, l_dblExecQuantity, l_bdlExecutedAmount, l_blnIsSkipAmountCheck);
    }
    
    public EqTypeOrderValidationResult validateChangeOrder(SubAccount l_subAccount,
            EqTypeChangeOrderSpec l_eqChangeOrderSpec)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityOrderManager",
                "validateChangeOrder", new Class[]
                { SubAccount.class, EqTypeChangeOrderSpec.class }, new Object[]
                { l_subAccount, l_eqChangeOrderSpec });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager", "validateChangeOrder",
                new Class[]
                { SubAccount.class, EqTypeChangeOrderSpec.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.validateChangeOrder()");
            return (EqTypeOrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "validateChangeOrder", new Class[]
                    { SubAccount.class, EqTypeChangeOrderSpec.class }).asObject();
        }
        return super.validateChangeOrder(l_subAccount, l_eqChangeOrderSpec);
    }
    
    /**
     * （validate返済注文訂正(mock)）<BR>
     * 指定返済訂正注文の発注審査を行う。 <BR>
     */
    public EqTypeOrderValidationResult validateChangeSettleContractOrder(
        SubAccount l_subAccount,
        EqTypeChangeSettleContractOrderSpec l_changeSettleContractOrderSpec)
    {
        String STR_METHOD_NAME = 
            "validateChangeSettleContractOrder(SubAccount, EqTypeChangeSettleContractOrderSpec)";
        log.entering(STR_METHOD_NAME);
 
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityOrderManager",
            "validateChangeSettleContractOrder", new Class[]
            { SubAccount.class, EqTypeChangeSettleContractOrderSpec.class }, new Object[]
            { l_subAccount, l_changeSettleContractOrderSpec });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager", "validateChangeSettleContractOrder",
            new Class[]
            { SubAccount.class, EqTypeChangeSettleContractOrderSpec.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.validateChangeSettleContractOrder()");
            return (EqTypeOrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "validateChangeSettleContractOrder", new Class[]
                    { SubAccount.class, EqTypeChangeSettleContractOrderSpec.class }).asObject();
        }
 
        log.exiting(STR_METHOD_NAME);
        return super.validateChangeSettleContractOrder(l_subAccount, l_changeSettleContractOrderSpec);
    }
    
    public EqTypeOrderSubmissionResult submitChangeSettleContractOrder(SubAccount l_subAccount,
            EqTypeChangeSettleContractOrderSpec l_spec, String l_strTradingPassword, boolean l_blnSkipOrderValidation)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityOrderManager",
                "submitChangeSettleContractOrder", new Class[]
                { SubAccount.class, EqTypeChangeSettleContractOrderSpec.class, String.class, boolean.class },
                new Object[]
                { l_subAccount, l_spec, l_strTradingPassword, new Boolean(l_blnSkipOrderValidation) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager",
                "submitChangeSettleContractOrder", new Class[]
                { SubAccount.class, EqTypeChangeSettleContractOrderSpec.class, String.class, boolean.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.submitChangeSettleContractOrder()");
            return (EqTypeOrderSubmissionResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "submitChangeSettleContractOrder", new Class[]
                    { SubAccount.class, EqTypeChangeSettleContractOrderSpec.class, String.class, boolean.class })
                    .asObject();
        }
        return super.submitChangeSettleContractOrder(l_subAccount, l_spec, l_strTradingPassword,
                l_blnSkipOrderValidation);
    }
    
    public EqTypeOrderSubmissionResult submitChangeOrder(
            SubAccount l_subAccount,
            EqTypeChangeOrderSpec l_changeOrderSpec,
            String l_strPassword,
            boolean l_isSkipValidateOrder)
        {
            TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityOrderManager",
                    "submitChangeOrder", new Class[]
                    { SubAccount.class, EqTypeChangeOrderSpec.class, String.class, boolean.class },
                    new Object[]
                    { l_subAccount, l_changeOrderSpec, l_strPassword, new Boolean(l_isSkipValidateOrder) });
            if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager",
                    "submitChangeOrder", new Class[]
                    { SubAccount.class, EqTypeChangeOrderSpec.class, String.class, boolean.class }))
            {
                log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.submitChangeSettleContractOrder()");
                return (EqTypeOrderSubmissionResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                        "webbroker3.equity.WEB3EquityOrderManager", "submitChangeOrder", new Class[]
                        { SubAccount.class, EqTypeChangeOrderSpec.class, String.class, boolean.class })
                        .asObject();
            }
            return super.submitChangeOrder(l_subAccount, l_changeOrderSpec, l_strPassword,
                l_isSkipValidateOrder);
        }
    
    public EqTypeNewOrderValidationResult validateOpenContractOrder(SubAccount l_subAccount,
            EqTypeOpenContractOrderSpec l_openContractOrderSpec)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityOrderManager",
                "validateOpenContractOrder", new Class[]
                { SubAccount.class, EqTypeOpenContractOrderSpec.class }, new Object[]
                { l_subAccount, l_openContractOrderSpec });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager",
                "validateOpenContractOrder", new Class[]
                { SubAccount.class, EqTypeOpenContractOrderSpec.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.validateOpenContractOrder()");
            return (EqTypeNewOrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "validateOpenContractOrder", new Class[]
                    { SubAccount.class, EqTypeOpenContractOrderSpec.class }).asObject();
        }
        return super.validateOpenContractOrder(l_subAccount, l_openContractOrderSpec);
    }
    
    public EqTypeOrderSubmissionResult submitOpenContractOrder(SubAccount l_subAccount,
            EqTypeOpenContractOrderSpec l_orderSpec, long l_lngOrderId, String l_strTradingPassword,
            boolean l_blnIsSkipOrderValidation)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityOrderManager",
                "submitOpenContractOrder", new Class[]
                { SubAccount.class, EqTypeOpenContractOrderSpec.class, long.class, String.class, boolean.class },
                new Object[]
                { l_subAccount, l_orderSpec, new Long(l_lngOrderId), l_strTradingPassword,
                        new Boolean(l_blnIsSkipOrderValidation) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager",
                "submitOpenContractOrder", new Class[]
                { SubAccount.class, EqTypeOpenContractOrderSpec.class, long.class, String.class, boolean.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.submitOpenContractOrder()");
            return (EqTypeOrderSubmissionResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "submitOpenContractOrder", new Class[]
                    { SubAccount.class, EqTypeOpenContractOrderSpec.class, long.class, String.class, boolean.class })
                    .asObject();
        }
        return super.submitOpenContractOrder(l_subAccount, l_orderSpec, l_lngOrderId, l_strTradingPassword,
                l_blnIsSkipOrderValidation);
    }
    
    public WEB3EquityEstimatedDeliveryPrice calcEstimateDeliveryAmount(
            WEB3GentradeCommission l_commission,
            double l_dblLimitPrice,
            double l_dblWLimitPrice,
            double l_dblStopOrderBasePrice,
            EqTypeExecutionConditionType l_execConditionType,
            EqTypeExecutionConditionType l_execWConditionType,
            String l_strPriceConditionType,
            String l_strOrderConditionType,
            String l_strCheckCurrentPrice,
            boolean l_blnIsStopOrderValid,
            SubAccount l_subAccount,
            WEB3EquityTradedProduct l_tradedProduct,
            double l_dblQuantity,
            boolean l_blnIsSellOrder,
            double l_dblExecQuantity,
            double l_bdlExecutedAmount,
            boolean l_blnIsSkipAmountRange)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = 
                "calcEstimateDeliveryAmount(WEB3GentradeCommission, double, double, double,"
                + "EqTypeExecutionConditionType, EqTypeExecutionConditionType, String,"
                + "String, String, boolean, SubAccount, WEB3EquityTradedProduct, double,"
                + "boolean, double, double, double)";
            log.entering(STR_METHOD_NAME);
 
            //1）參數驗證
            TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcEstimateDeliveryAmount",
                new Class[] {
                        WEB3GentradeCommission.class, double.class,
                        double.class, double.class,
                        EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                        String.class,
                        String.class,
                        String.class,
                        boolean.class,
                        SubAccount.class,
                        WEB3EquityTradedProduct.class,
                        double.class,
                        boolean.class,
                        double.class,
                        double.class,
                        boolean.class},
                new Object[]{               
                        l_commission,
                        new Double(l_dblLimitPrice),
                        new Double(l_dblWLimitPrice),
                        new Double(l_dblStopOrderBasePrice),
                        l_execConditionType,
                        l_execWConditionType,
                        l_strPriceConditionType,
                        l_strOrderConditionType,
                        l_strCheckCurrentPrice,
                        new Boolean(l_blnIsStopOrderValid),
                        l_subAccount,
                        l_tradedProduct,
                        new Double(l_dblQuantity),
                        new Boolean(l_blnIsSellOrder),
                        new Double(l_dblExecQuantity),
                        new Double(l_bdlExecutedAmount),
                        new Boolean(l_blnIsSkipAmountRange)
                        });
 
            if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcEstimateDeliveryAmount",
                new Class[] {
                        WEB3GentradeCommission.class, double.class,
                        double.class, double.class,
                        EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                        String.class,
                        String.class,
                        String.class,
                        boolean.class,
                        SubAccount.class,
                        WEB3EquityTradedProduct.class,
                        double.class,
                        boolean.class,
                        double.class,
                        double.class,
                        boolean.class
                        }))
            {
                //2）MockFor --〉 asWEB3BaseException
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                            WEB3GentradeCommission.class, double.class,
                            double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                            String.class,
                            String.class,
                            String.class,
                            boolean.class,
                            SubAccount.class,
                            WEB3EquityTradedProduct.class,
                            double.class,
                            boolean.class,
                            double.class,
                            double.class,
                            boolean.class
                            }).asWEB3BaseException();
 
                //3)MockFor --〉 asBoolean
                log.exiting(STR_METHOD_NAME);
                return (WEB3EquityEstimatedDeliveryPrice)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                            WEB3GentradeCommission.class, double.class,
                            double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                            String.class,
                            String.class,
                            String.class,
                            boolean.class,
                            SubAccount.class,
                            WEB3EquityTradedProduct.class,
                            double.class,
                            boolean.class,
                            double.class,
                            double.class,
                            boolean.class
                            }).asObject();
            }
 
            log.exiting(STR_METHOD_NAME);
            return super.calcEstimateDeliveryAmount(
                    l_commission,
                    l_dblLimitPrice,
                    l_dblWLimitPrice,
                    l_dblStopOrderBasePrice,
                    l_execConditionType,
                    l_execWConditionType,
                    l_strPriceConditionType,
                    l_strOrderConditionType,
                    l_strCheckCurrentPrice,
                    l_blnIsStopOrderValid,
                    l_subAccount,
                    l_tradedProduct,
                    l_dblQuantity,
                    l_blnIsSellOrder,
                    l_dblExecQuantity,
                    l_bdlExecutedAmount,
                    l_blnIsSkipAmountRange);
        }

    public EqTypeOrderValidationResult validateChangeOrder(
            SubAccount l_subAccount,
            EqTypeChangeOrderSpec l_eqTypeChangeOrderSpec,
            boolean l_blnIsSkipDelayStatusCheck)
        {
            TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityOrderManager",
                    "validateChangeOrder", new Class[]
                    { SubAccount.class, EqTypeChangeOrderSpec.class, boolean.class},
                    new Object[]
                    { l_subAccount, l_eqTypeChangeOrderSpec, new Boolean(l_blnIsSkipDelayStatusCheck) });
            if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager",
                    "validateChangeOrder", new Class[]
                    { SubAccount.class, EqTypeChangeOrderSpec.class, boolean.class }))
            {
                log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.validateChangeOrder()");
                return (EqTypeOrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                        "webbroker3.equity.WEB3EquityOrderManager", "validateChangeOrder", new Class[]
                        { SubAccount.class, EqTypeChangeOrderSpec.class, boolean.class})
                        .asObject();
            }
            return super.validateChangeOrder(l_subAccount, l_eqTypeChangeOrderSpec,
                    l_blnIsSkipDelayStatusCheck);
        }
    
    public EqTypeOrderValidationResult validateCancelOrder(SubAccount l_subAccount,
            EqTypeCancelOrderSpec l_cancelOrderSpec)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityOrderManager",
                "validateCancelOrder", new Class[]
                { SubAccount.class, EqTypeCancelOrderSpec.class }, new Object[]
                { l_subAccount, l_cancelOrderSpec });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager", "validateCancelOrder",
                new Class[]
                { SubAccount.class, EqTypeCancelOrderSpec.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.validateCancelOrder()");
            return (EqTypeOrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "validateCancelOrder", new Class[]
                    { SubAccount.class, EqTypeCancelOrderSpec.class }).asObject();
        }
        return super.validateCancelOrder(l_subAccount, l_cancelOrderSpec);
    }
    
    public void validateMechanismDepositAgree(SubAccount l_subAccount) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityOrderManager",
                "validateMechanismDepositAgree", new Class[]
                { SubAccount.class }, new Object[]
                { l_subAccount });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager",
                "validateMechanismDepositAgree", new Class[]
                { SubAccount.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.validateMechanismDepositAgree()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "validateMechanismDepositAgree", new Class[]
                    { SubAccount.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "validateMechanismDepositAgree", new Class[]
                    { SubAccount.class }).asVoid();
            return;
        }
        super.validateMechanismDepositAgree(l_subAccount);
    }
    
    public OrderValidationResult validateMiniStockCancelOrder(
            WEB3GentradeSubAccount l_subAccount, CancelOrderSpec l_cancelOrderSpec) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityOrderManager",
                "validateMiniStockCancelOrder", new Class[]
                { WEB3GentradeSubAccount.class, CancelOrderSpec.class}, new Object[]
                { l_subAccount, l_cancelOrderSpec});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager",
                "validateMiniStockCancelOrder", new Class[]
                {  WEB3GentradeSubAccount.class, CancelOrderSpec.class}))
        {
            log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.validateMiniStockCancelOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "validateMiniStockCancelOrder", new Class[]
                    {  WEB3GentradeSubAccount.class, CancelOrderSpec.class}).asWEB3BaseException();
            
            return (OrderValidationResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "validateMiniStockCancelOrder", new Class[]
                    {  WEB3GentradeSubAccount.class, CancelOrderSpec.class}).asObject();
        }
        return super.validateMiniStockCancelOrder(l_subAccount, l_cancelOrderSpec);
    }
    
    public EqTypeOrderSubmissionResult submitCancelOrder(
            SubAccount l_subAccount,
            EqTypeCancelOrderSpec l_cancelOrderSpec,
            String l_strPassword,
            boolean l_isSkipValidateOrder)
   {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityOrderManager",
                "submitCancelOrder", new Class[]
                { SubAccount.class, EqTypeCancelOrderSpec.class, String.class, boolean.class }, new Object[]
                { l_subAccount, l_cancelOrderSpec, l_strPassword, new Boolean(l_isSkipValidateOrder)});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager",
                "submitCancelOrder", new Class[]
                {  SubAccount.class, EqTypeCancelOrderSpec.class, String.class, boolean.class}))
        {
            log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.submitCancelOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "submitCancelOrder", new Class[]
                    {SubAccount.class, EqTypeCancelOrderSpec.class, String.class, boolean.class}).asWEB3BaseRuntimeException();
            return (EqTypeOrderSubmissionResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "submitCancelOrder", new Class[]
                    { SubAccount.class, EqTypeCancelOrderSpec.class, String.class, boolean.class}).asObject();
        }
        return super.submitCancelOrder(l_subAccount, l_cancelOrderSpec, l_strPassword, l_isSkipValidateOrder);
   }
    public void updateOrderData(
            EqTypeOrderUnit l_orderUnit,
            boolean l_blnIsCreateOrderAction)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityOrderManager",
                "updateOrderData", new Class[]
                { EqTypeOrderUnit.class,
                    boolean.class},
                new Object[]
                { l_orderUnit,
                  new Boolean(l_blnIsCreateOrderAction)});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager",
                "updateOrderData", new Class[]
                {EqTypeOrderUnit.class,
                boolean.class}))
        {
            log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.updateOrderData()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "updateOrderData", new Class[]
                    { EqTypeOrderUnit.class,
                    boolean.class }).asWEB3BaseException();
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "updateOrderData", new Class[]
                    { EqTypeOrderUnit.class,
                    boolean.class}).asVoid();
            return;
        }
        super.updateOrderData(l_orderUnit, l_blnIsCreateOrderAction);
    }
    public EqTypeOrderSubmissionResult submitNewCashBasedOrder(SubAccount subAccount, EqTypeNewCashBasedOrderSpec spec, long orderId, String tradingPassword, boolean skipOrderValidation)
    {
        final String STR_METHOD_NAME = "submitNewCashBasedOrder(subAccount, spec, orderId, tradingPassword, skipOrderValidation) -- ForMock";
        log.entering(STR_METHOD_NAME);
        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "submitNewCashBasedOrder",
            new Class[] {SubAccount.class, EqTypeNewCashBasedOrderSpec.class, long.class, String.class, boolean.class},
            new Object[]{subAccount, spec, new Long(orderId), tradingPassword, new Boolean(skipOrderValidation)});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.equity.WEB3EquityOrderManager",
            "submitNewCashBasedOrder",
            new Class[] {SubAccount.class, EqTypeNewCashBasedOrderSpec.class, long.class, String.class, boolean.class}))
        {
            //3)MockFor --〉 asVoid
            log.exiting(STR_METHOD_NAME);
            return (EqTypeOrderSubmissionResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "submitNewCashBasedOrder",
                new Class[] {SubAccount.class, EqTypeNewCashBasedOrderSpec.class, long.class, String.class, boolean.class}).asObject();            
        }

        log.exiting(STR_METHOD_NAME);
        return super.submitNewCashBasedOrder(subAccount, spec, orderId, tradingPassword, skipOrderValidation);
    }
    
    public double calcPriceForRestraintAmount(
            WEB3GentradeCommission l_commission,
            OrderTypeEnum l_orderType,
            double l_dblLimitPrice,
            double l_dblWLimitPrice,
            String l_strOrderConditionType,
            EqTypeExecutionConditionType l_execConditionType,
            String l_strPriceConditionType,
            WEB3EquityTradedProduct l_tradedProduct,
            WEB3GentradeSubAccount l_subAccount,
            String l_strCheckPrice
            ) throws WEB3BaseException
     {
        String STR_METHOD_NAME = 
            "calcPriceForRestraintAmount(" +
            "WEB3GentradeCommission,OrderTypeEnum,"+
            "double, double," +
            "String, EqTypeExecutionConditionType," +
            "String, WEB3EquityTradedProduct" +
            "WEB3GentradeSubAccount, String)";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "calcPriceForRestraintAmount",
            new Class[] {
                WEB3GentradeCommission.class, OrderTypeEnum.class,
                double.class, double.class,
                String.class, EqTypeExecutionConditionType.class,
                String.class,WEB3EquityTradedProduct.class,
                WEB3GentradeSubAccount.class,String.class},
            new Object[]{               
                l_commission, l_orderType,
                new Double(l_dblLimitPrice), new Double(l_dblWLimitPrice),
                l_strOrderConditionType, l_execConditionType,
                l_strPriceConditionType, l_tradedProduct,
                l_subAccount, l_strCheckPrice});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.equity.WEB3EquityOrderManager",
            "calcPriceForRestraintAmount",
            new Class[] {
                    WEB3GentradeCommission.class, OrderTypeEnum.class,
                    double.class, double.class,
                    String.class, EqTypeExecutionConditionType.class,
                    String.class,WEB3EquityTradedProduct.class,
                    WEB3GentradeSubAccount.class,String.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcPriceForRestraintAmount",
                new Class[] {
                    WEB3GentradeCommission.class, OrderTypeEnum.class,
                    double.class, double.class,
                    String.class, EqTypeExecutionConditionType.class,
                    String.class,WEB3EquityTradedProduct.class,
                    WEB3GentradeSubAccount.class,String.class}).asWEB3BaseException();

            //3)MockFor --〉 asBoolean
            log.exiting(STR_METHOD_NAME);
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcPriceForRestraintAmount",
                new Class[] {
                    WEB3GentradeCommission.class, OrderTypeEnum.class,
                    double.class, double.class,
                    String.class, EqTypeExecutionConditionType.class,
                    String.class,WEB3EquityTradedProduct.class,
                    WEB3GentradeSubAccount.class,String.class}).asDouble();
        }

        log.exiting(STR_METHOD_NAME);
        return super.calcPriceForRestraintAmount(
            l_commission, l_orderType,
            l_dblLimitPrice, l_dblWLimitPrice,
            l_strOrderConditionType, l_execConditionType,
            l_strPriceConditionType, l_tradedProduct,
            l_subAccount, l_strCheckPrice);
     }
    
    public WEB3EquityEstimatedDeliveryPrice calcEstimateDeliveryAmount(
            WEB3GentradeCommission l_commission,
            double l_dblCalcUnitPrice,
            SubAccount l_subAccount,
            WEB3EquityTradedProduct l_tradedProduct,
            double l_dblQuantity,
            boolean l_isSellOrder,
            double l_dblExecutedQuantity,
            double l_dblExecutedAmount,
            boolean l_isSkipPriceCheck,
            boolean l_isRestraintConsideration)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcEstimateDeliveryAmount(" +
            "l_commission,l_dblCalcUnitPrice,"+
            "l_subAccount, l_tradedProduct," +
            "l_dblQuantity, l_isSellOrder," +
            "l_dblExecutedQuantity, l_dblExecutedAmount," +
            "l_isSkipPriceCheck, l_isRestraintConsideration)";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "calcEstimateDeliveryAmount",
            new Class[] {
                WEB3GentradeCommission.class, double.class,
                SubAccount.class, WEB3EquityTradedProduct.class,
                double.class, boolean.class,
                double.class, double.class,
                boolean.class, boolean.class},
            new Object[]{               
                l_commission,new Double(l_dblCalcUnitPrice),
                l_subAccount, l_tradedProduct,
                new Double(l_dblQuantity), new Boolean(l_isSellOrder),
                new Double(l_dblExecutedQuantity), new Double(l_dblExecutedAmount),
                new Boolean(l_isSkipPriceCheck), new Boolean(l_isRestraintConsideration)});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.equity.WEB3EquityOrderManager",
            "calcEstimateDeliveryAmount",
            new Class[] {
                WEB3GentradeCommission.class, double.class,
                SubAccount.class, WEB3EquityTradedProduct.class,
                double.class, boolean.class,
                double.class, double.class,
                boolean.class, boolean.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcEstimateDeliveryAmount",
                new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    SubAccount.class, WEB3EquityTradedProduct.class,
                    double.class, boolean.class,
                    double.class, double.class,
                    boolean.class, boolean.class}).asWEB3BaseException();

            //3)MockFor --〉 asBoolean
            log.exiting(STR_METHOD_NAME);
            return (WEB3EquityEstimatedDeliveryPrice)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcEstimateDeliveryAmount",
                new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    SubAccount.class, WEB3EquityTradedProduct.class,
                    double.class, boolean.class,
                    double.class, double.class,
                    boolean.class, boolean.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.calcEstimateDeliveryAmount(
            l_commission,l_dblCalcUnitPrice,
            l_subAccount, l_tradedProduct,
            l_dblQuantity, l_isSellOrder,
            l_dblExecutedQuantity, l_dblExecutedAmount,
            l_isSkipPriceCheck, l_isRestraintConsideration);
    }
    
    public WEB3EquityEstimatedPrice getStopOrderExpireEstimatedPrice(
            EqTypeOrderUnit l_eqOrderUnit,
            SubAccount l_subAccount) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityOrderManager",
                "getStopOrderExpireEstimatedPrice", new Class[]
                { EqTypeOrderUnit.class, SubAccount.class}, new Object[]
                { l_eqOrderUnit, l_subAccount});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityOrderManager",
                "getStopOrderExpireEstimatedPrice", new Class[]
                {EqTypeOrderUnit.class, SubAccount.class}))
        {
            log.debug("webbroker3.equity.WEB3EquityOrderManagerForMock.getStopOrderExpireEstimatedPrice()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "getStopOrderExpireEstimatedPrice", new Class[]
                    {EqTypeOrderUnit.class, SubAccount.class}).asWEB3BaseException();
            
            return (WEB3EquityEstimatedPrice)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "getStopOrderExpireEstimatedPrice", new Class[]
                    {EqTypeOrderUnit.class, SubAccount.class}).asObject();
        }
        return super.getStopOrderExpireEstimatedPrice(l_eqOrderUnit, l_subAccount);
    }
    public double calcContractAmountAtOrder(
            WEB3GentradeCommission l_genCommission,
            double l_dblCalcUnitPrice,
            WEB3GentradeSubAccount l_genSubAccount,
            WEB3EquityTradedProduct l_equityTradedProduct,
            double l_dblQuantity,
            double l_dblExecutedQuantity,
            double l_dblExecutedAmount,
            boolean l_isSkipAmountRange)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcContractAmountAtOrder(" +
            "l_genCommission,l_dblCalcUnitPrice,"+
            "l_subAccount, l_equityTradedProduct," +
            "l_dblQuantity, l_dblExecutedQuantity," +
            "l_dblExecutedAmount, l_isSkipAmountRange)";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "calcContractAmountAtOrder",
            new Class[] {
                WEB3GentradeCommission.class, double.class,
                WEB3GentradeSubAccount.class, WEB3EquityTradedProduct.class,
                double.class, double.class,
                double.class, boolean.class},
            new Object[]{               
                l_genCommission,new Double(l_dblCalcUnitPrice),
                l_genSubAccount, l_equityTradedProduct,
                new Double(l_dblQuantity), new Double(l_dblExecutedQuantity),
                new Double(l_dblExecutedAmount), new Boolean(l_isSkipAmountRange)});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.equity.WEB3EquityOrderManager",
            "calcContractAmountAtOrder",
            new Class[] {
                WEB3GentradeCommission.class, double.class,
                WEB3GentradeSubAccount.class, WEB3EquityTradedProduct.class,
                double.class, double.class,
                double.class, boolean.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcContractAmountAtOrder",
                new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    WEB3GentradeSubAccount.class, WEB3EquityTradedProduct.class,
                    double.class, double.class,
                    double.class, boolean.class}).asWEB3BaseException();

            //3)MockFor --〉 asBoolean
            log.exiting(STR_METHOD_NAME);
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcContractAmountAtOrder",
                new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    WEB3GentradeSubAccount.class, WEB3EquityTradedProduct.class,
                    double.class, double.class,
                    double.class, boolean.class}).asDouble();
        }

        log.exiting(STR_METHOD_NAME);
        return super.calcContractAmountAtOrder(
                l_genCommission,l_dblCalcUnitPrice,
                l_genSubAccount, l_equityTradedProduct,
                l_dblQuantity, l_dblExecutedQuantity,
                l_dblExecutedAmount, l_isSkipAmountRange);
    }

    public EqTypeSettleContractOrderEntry[] createClosingContractEntry(
            long l_lngOrderUnitId,
            double l_dblOrderQuantity,
            WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits,
            boolean l_isSkipCloseDateCheck)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "createClosingContractEntry(l_lngOrderUnitId," +
            "l_dblOrderQuantity" +
            "l_closeMarginContractUnits" +
            "l_isSkipCloseDateCheck)";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "createClosingContractEntry",
            new Class[] {
                long.class,
                double.class,
                WEB3MarginCloseMarginContractUnit[].class,
                boolean.class},
            new Object[]{               
                new Long(l_lngOrderUnitId),
                new Double(l_dblOrderQuantity),
                l_closeMarginContractUnits,
                new Boolean(l_isSkipCloseDateCheck)});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.equity.WEB3EquityOrderManager",
            "createClosingContractEntry",
            new Class[] {
                long.class,
                double.class,
                WEB3MarginCloseMarginContractUnit[].class,
                boolean.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "createClosingContractEntry",
                new Class[] {
                    long.class,
                    double.class,
                    WEB3MarginCloseMarginContractUnit[].class,
                    boolean.class}).asWEB3BaseException();

            //3)MockFor --〉 asBoolean
            log.exiting(STR_METHOD_NAME);
            return (EqTypeSettleContractOrderEntry[])TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "createClosingContractEntry",
                new Class[] {
                    long.class,
                    double.class,
                    WEB3MarginCloseMarginContractUnit[].class,
                    boolean.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.createClosingContractEntry(
            l_lngOrderUnitId,
            l_dblOrderQuantity,
            l_closeMarginContractUnits,
            l_isSkipCloseDateCheck);
    }
}
@

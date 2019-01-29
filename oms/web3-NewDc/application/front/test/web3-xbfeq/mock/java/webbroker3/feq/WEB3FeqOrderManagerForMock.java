head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.19.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqOrderManagerForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqProduct;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqOrderManagerForMock extends WEB3FeqOrderManager
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3FeqOrderManagerForMock.class);
    
    public WEB3FeqOrderManagerForMock()
    {
        super();
    }
    public double getUnitPrice(
            WEB3FeqTradedProduct l_feqProduct,
            WEB3GentradeBranch l_branch,
            String l_strOrderPriceDiv,
            double l_dblPrice,
            double l_dblChangePrice,
            boolean l_blnIsBuy) throws WEB3BaseException
    {
        
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "getUnitPrice",
                new Class[]{
                        WEB3FeqTradedProduct.class,
                        WEB3GentradeBranch.class,
                        String.class,
                        double.class,
                        double.class,
                        boolean.class},
                        
                new Object[]{
                        l_feqProduct,
                        l_branch,
                        l_strOrderPriceDiv,
                        new Double(l_dblPrice),
                        new Double(l_dblChangePrice),
                        new Boolean(l_blnIsBuy)});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.feq.WEB3FeqOrderManager",
                "getUnitPrice",
                new Class[] {WEB3FeqTradedProduct.class, WEB3GentradeBranch.class, String.class,
                	double.class, double.class, boolean.class}))
            {
                log.debug("webbroker3.gentrade.WEB3FeqOrderManager.validateOrder(String)" + 
                    "l_strOrderPriceDiv = " + l_strOrderPriceDiv);
                
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                        "webbroker3.feq.WEB3FeqOrderManager",
                        "getUnitPrice",
                        new Class[] {WEB3FeqTradedProduct.class, WEB3GentradeBranch.class, String.class,
                            	double.class, double.class, boolean.class}).asWEB3BaseException();
                
                return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.feq.WEB3FeqOrderManager",
                    "getUnitPrice",
                    new Class[] {WEB3FeqTradedProduct.class, WEB3GentradeBranch.class, String.class,
                        	double.class, double.class, boolean.class}).asDouble();

            }
	    
    	return super.getUnitPrice(
    	    l_feqProduct, l_branch, l_strOrderPriceDiv, l_dblPrice, l_dblChangePrice, l_blnIsBuy);
    }
    public void validateOrder(WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException
    {
        
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateOrder",
                new Class[]{WEB3GentradeSubAccount.class},
                new Object[]{l_subAccount});
        
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.feq.WEB3FeqOrderManager",
            "validateOrder",
            new Class[] {WEB3GentradeSubAccount.class}))
        {
            log.debug("webbroker3.gentrade.WEB3FeqOrderManager.validateOrder(String)" + 
                "l_subAccount = " + l_subAccount);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateOrder",
                new Class[] {WEB3GentradeSubAccount.class}).asWEB3BaseException();
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateOrder",
                new Class[] {WEB3GentradeSubAccount.class}).asVoid();
            return;
        }
        super.validateOrder(l_subAccount);
    }
    
    public long createNewOrderId() {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.feq.WEB3FeqOrderManager",
                "createNewOrderId",
                new Class[] {}))
            {            
                return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.feq.WEB3FeqOrderManager",
                    "createNewOrderId",
                    null).asLong();
            }
         return super.createNewOrderId();               
    }
    
    public NewOrderValidationResult validateNewOrder(SubAccount l_subAccount,
        ProductTypeEnum l_productType, 
        NewOrderSpec l_orderSpec)
    {
        
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateNewOrder",
                new Class[]{
                        SubAccount.class,
                        ProductTypeEnum.class,
                        NewOrderSpec.class},
                new Object[]{
                        l_subAccount,
                        l_productType,
                        l_orderSpec});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.feq.WEB3FeqOrderManager", "validateNewOrder",
            new Class[] { SubAccount.class, ProductTypeEnum.class, NewOrderSpec.class }))
        {
            log.debug("webbroker3.gentrade.WEB3FeqOrderManager.validateNewOrder(WEB3GentradeSubAccount,ProductTypeEnum,NewOrderSpec)"
                    + "l_subAccount = "
                    + l_subAccount
                    + "l_productType = "
                    + l_productType
                    + "l_orderSpec = "
                    + l_orderSpec);
            return (NewOrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager", "validateNewOrder",
                new Class[] { SubAccount.class, ProductTypeEnum.class, NewOrderSpec.class }).asObject();

        }
        return super.validateNewOrder(l_subAccount, l_productType, l_orderSpec);
    }
    
    public FeqProduct validateFeqProduct(WEB3GentradeInstitution l_institution, String l_strProductCode)
			throws WEB3BaseException
	{
		
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateFeqProduct",
                new Class[]{
                        WEB3GentradeInstitution.class,
                        String.class},
                new Object[]{
                        l_institution,
                        l_strProductCode});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.feq.WEB3FeqOrderManager", "validateFeqProduct",
				new Class[] { WEB3GentradeInstitution.class, String.class }))
		{
			log.debug("webbroker3.gentrade.WEB3FeqOrderManager.validateOrder(String)" + "l_subAccount = "
					+ l_institution);

			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.feq.WEB3FeqOrderManager",
					"validateFeqProduct", new Class[] { WEB3GentradeInstitution.class, String.class })
					.asWEB3BaseException();

			return (WEB3FeqProduct) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.feq.WEB3FeqOrderManager", "validateFeqProduct",
					new Class[] { WEB3GentradeInstitution.class, String.class }).asObject();

		}

		return super.validateFeqProduct(l_institution, l_strProductCode);

	}
    
    public void validateMarket(WEB3GentradeMarket l_market) throws WEB3BaseException
	{
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateMarket",
                new Class[]{
                        WEB3GentradeMarket.class},
                new Object[]{l_market});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.feq.WEB3FeqOrderManager", "validateMarket",
				new Class[] { WEB3GentradeMarket.class }))
		{
			log.debug("webbroker3.gentrade.WEB3FeqOrderManager.validateOrder(String)" + "l_subAccount = " + l_market);

			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.feq.WEB3FeqOrderManager", "validateMarket",
					new Class[] { WEB3GentradeMarket.class }).asWEB3BaseException();

			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.feq.WEB3FeqOrderManager", "validateMarket",
					new Class[] { WEB3GentradeMarket.class }).asVoid();
			return;
		}
		super.validateMarket(l_market);

	}
    
    public TradedProduct validateTradedProduct(WEB3FeqProduct l_feqProduct, WEB3GentradeMarket l_market,
			boolean l_blnIsBuyOrder) throws WEB3BaseException
	{
		
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateTradedProduct",
                new Class[]{
                        WEB3FeqProduct.class,
                        WEB3GentradeMarket.class,
                        boolean.class},
                new Object[]{l_feqProduct,l_market,new Boolean(l_blnIsBuyOrder)});	
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.feq.WEB3FeqOrderManager", "validateTradedProduct",
					new Class[] { WEB3FeqProduct.class, WEB3GentradeMarket.class, boolean.class }))
			{
				log.debug("webbroker3.gentrade.WEB3FeqOrderManager.validateOrder(String)" + "l_subAccount = "
						+ l_feqProduct + l_market);

				TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.feq.WEB3FeqOrderManager",
						"validateTradedProduct",
						new Class[] { WEB3FeqProduct.class, WEB3GentradeMarket.class, boolean.class })
						.asWEB3BaseException();

				return (TradedProduct) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
						"webbroker3.feq.WEB3FeqOrderManager", "validateTradedProduct",
						new Class[] { WEB3FeqProduct.class, WEB3GentradeMarket.class, boolean.class }).asObject();

			}
			return super.validateTradedProduct(l_feqProduct, l_market);

		

	}

    public void validateAccountProductTradedStop(SubAccount l_subAccount, long l_lngProductId, OrderTypeEnum l_orderType)
			throws WEB3BaseException
	{
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateAccountProductTradedStop",
                new Class[]{
                        SubAccount.class,
                        long.class,
                        OrderTypeEnum.class},
                new Object[]{l_subAccount,new Long(l_lngProductId),l_orderType});  
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.feq.WEB3FeqOrderManager",
				"validateAccountProductTradedStop", new Class[] { SubAccount.class, long.class, OrderTypeEnum.class }))
		{
			log
					.debug("webbroker3.gentrade.WEB3FeqOrderManager.validateOrder(String)" + "l_subAccount = "
							+ l_orderType);
			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.feq.WEB3FeqOrderManager",
					"validateAccountProductTradedStop",
					new Class[] { SubAccount.class, long.class, OrderTypeEnum.class }).asWEB3BaseException();
			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.feq.WEB3FeqOrderManager",
					"validateAccountProductTradedStop",
					new Class[] { SubAccount.class, long.class, OrderTypeEnum.class }).asVoid();
			return;
		}
		super.validateAccountProductTradedStop(l_subAccount, l_lngProductId, l_orderType);
	}
    
    public OrderSubmissionResult submitNewOrder(SubAccount subAccount, ProductTypeEnum productType,
            NewOrderSpec inSpec, long orderId, String tradingPassword, boolean skipOrderValidation)
    {

        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "submitNewOrder",
                new Class[]{
                        SubAccount.class,
                        ProductTypeEnum.class,
                        NewOrderSpec.class,
                        long.class,
                        String.class,
                        boolean.class},
                        
                new Object[]{subAccount,
                        productType,
                        inSpec,
                        new Long(orderId),
                        tradingPassword,
                        new Boolean(skipOrderValidation)});  
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.feq.WEB3FeqOrderManager", "submitNewOrder",
                new Class[] {SubAccount.class, ProductTypeEnum.class, NewOrderSpec.class, long.class, String.class,
                        boolean.class}))
        {
            log.debug("webbroker3.gentrade.WEB3FeqOrderManager.submitNewOrder");
            return (OrderSubmissionResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.feq.WEB3FeqOrderManager",
                    "submitNewOrder",
                    new Class[] {SubAccount.class, ProductTypeEnum.class, NewOrderSpec.class, long.class, String.class,
                            boolean.class}).asObject();
        }
        return super.submitNewOrder(subAccount, productType, inSpec, orderId, tradingPassword, skipOrderValidation);
    }
    
    public OrderSubmissionResult submitChangeOrder(SubAccount subAccount, ChangeOrderSpec spec, 
            String tradingPassword, boolean skipOrderValidation)
    {

        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "submitChangeOrder",
                new Class[]{
                        SubAccount.class,
                        ChangeOrderSpec.class,
                        String.class,
                        boolean.class},
                        
                new Object[]{subAccount,
                        spec,
                        tradingPassword,
                        new Boolean(skipOrderValidation)});  
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.feq.WEB3FeqOrderManager", "submitChangeOrder",
                new Class[] {SubAccount.class,
                ChangeOrderSpec.class,
                String.class,
                boolean.class}))
        {
            log.debug("webbroker3.gentrade.WEB3FeqOrderManager.submitChangeOrder");
            return (OrderSubmissionResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.feq.WEB3FeqOrderManager",
                    "submitChangeOrder",
                    new Class[] {SubAccount.class,
                            ChangeOrderSpec.class,
                            String.class,
                            boolean.class}).asObject();
        }
        return super.submitChangeOrder(subAccount, spec, tradingPassword, skipOrderValidation);
    }
    
    public void validateChangePossMarket(long l_lngOrderId) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.feq.WEB3FeqOrderManager",
            "validateChangePossMarket",
            new Class[]{long.class},
            new Object[]{new Long(l_lngOrderId)});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.feq.WEB3FeqOrderManager",
            "validateChangePossMarket",
            new Class[]{long.class}))
        {            
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateChangePossMarket",
                new Class[]{long.class}).asWEB3BaseException();
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateChangePossMarket",
                new Class[]{long.class}).asVoid();
            return;
        }
        super.validateChangePossMarket(l_lngOrderId); 
    }
    
    public void validateHandlingPossibleMarket(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMarketCode) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.feq.WEB3FeqOrderManager",
            "validateHandlingPossibleMarket",
            new Class[]{String.class,String.class,String.class},
            new Object[]{l_strInstitutionCode, l_strBranchCode, l_strMarketCode});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.feq.WEB3FeqOrderManager",
            "validateHandlingPossibleMarket",
            new Class[]{String.class,String.class,String.class}))
        {            
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateHandlingPossibleMarket",
                new Class[]{String.class,String.class,String.class}).asWEB3BaseException();
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "validateHandlingPossibleMarket",
                new Class[]{String.class,String.class,String.class}).asVoid();
            return;
        }
        super.validateHandlingPossibleMarket(l_strInstitutionCode, l_strBranchCode, l_strMarketCode); 
    }

    /**
     * (get出来終了対象注文単位(Mock))<BR>
     * 出来終了対象となる注文単位の一覧を取得する。<BR>
     * @@param l_lngAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード<BR>
     * @@param l_datBizDate - (発注日)<BR>
     * 発注日<BR>
     * @@return webbroker3.feq.WEB3FeqOrderUnit[]
     * @@throws WEB3BaseException
     */
    public WEB3FeqOrderUnit[] getOrderExecEndObjectOrderUnit(
        Long l_lngAccountId,
        String l_strInstitutionCode,
        String l_strMarketCode,
        Date l_datBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderExecEndObjectOrderUnit(long, String, String, Date)-->ForMock";
        log.entering(STR_METHOD_NAME);


        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.feq.WEB3FeqOrderManager",
            "getOrderExecEndObjectOrderUnit",
            new Class[] {Long.class, String.class, String.class, Date.class},
            new Object[]{l_lngAccountId, l_strInstitutionCode, l_strMarketCode, l_datBizDate});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.feq.WEB3FeqOrderManager",
            "getOrderExecEndObjectOrderUnit",
            new Class[] {Long.class, String.class, String.class, Date.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "getOrderExecEndObjectOrderUnit",
                new Class[] {Long.class, String.class, String.class, Date.class}).asWEB3BaseException();

            //3)MockFor --〉 asObject
            return (WEB3FeqOrderUnit[])TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "getOrderExecEndObjectOrderUnit",
                new Class[] {Long.class, String.class, String.class, Date.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getOrderExecEndObjectOrderUnit(l_lngAccountId, l_strInstitutionCode, l_strMarketCode, l_datBizDate);
    }
    
    /**
     * (update概算受渡代金)<BR>
     * 概算受渡代金再計算を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株注文）update概算受渡代金」参照。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_datBaseDate - (基準日)<BR>
     * 基準日<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4292B16A015C
     */
    public void updateEstimatedPrice(
        WEB3FeqOrderUnit l_orderUnit,
        Date l_ExecDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateEstimatedPrice(WEB3FeqOrderUnit, Date)-->ForMock";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "updateEstimatedPrice",
                new Class[] {WEB3FeqOrderUnit.class, Date.class},
                new Object[]{l_orderUnit, l_ExecDate});

            if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.feq.WEB3FeqOrderManager",
                "updateEstimatedPrice",
                new Class[] {WEB3FeqOrderUnit.class, Date.class}))
            {
                //2）MockFor --〉 asWEB3BaseException
                log.exiting(STR_METHOD_NAME);
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.feq.WEB3FeqOrderManager",
                    "updateEstimatedPrice",
                    new Class[] {WEB3FeqOrderUnit.class, Date.class}).asWEB3BaseException();

                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                        "webbroker3.feq.WEB3FeqOrderManager",
                        "updateEstimatedPrice",
                        new Class[]{WEB3FeqOrderUnit.class, Date.class}).asVoid();
                return;

            }

            log.exiting(STR_METHOD_NAME);
            super.updateEstimatedPrice(l_orderUnit, l_ExecDate);
    }


    /**
     * (validate訂正注文)<BR>
     * （validateChangeOrderのオーバーライド）<BR>
     * 訂正注文の発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株注文）validate訂正注文」 参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     *
     * @@param l_orderSpec - (注文内容)<BR>
     * 注文内容オブジェクト<BR>
     *
     * @@return OrderValidationResult
     * @@roseuid 429740FA036B
     */
    public OrderValidationResult validateChangeOrder(SubAccount l_subAccount, ChangeOrderSpec l_orderSpec)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.feq.WEB3FeqOrderManager",
            "validateChangeOrder",
            new Class[]{
                    SubAccount.class,
                    ChangeOrderSpec.class},
            new Object[]{
                    l_subAccount,
                    l_orderSpec});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.feq.WEB3FeqOrderManager", "validateChangeOrder",
            new Class[] {SubAccount.class, ChangeOrderSpec.class}))
        {
            return (OrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager", "validateChangeOrder",
                new Class[] { SubAccount.class,
                        ChangeOrderSpec.class }).asObject();

        }
        return super.validateChangeOrder(l_subAccount, l_orderSpec);
    }

    /**
     * (validate約定日)<BR>
     * 約定日のチェックを行う。<BR>
     * <BR>
     * 外国株式発注審査個別チェック.validate約定日()に委譲（deligate）する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_datExecDate - (約定日)<BR>
     * 約定日<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4292A2EC02D3
     */
    public void validateExecutionDate(
        WEB3FeqOrderUnit l_orderUnit,
        Date l_datExecDate) throws WEB3BaseException
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.feq.WEB3FeqOrderManager",
            "validateExecutionDate",
            new Class[] {WEB3FeqOrderUnit.class, Date.class}))
        {
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager", "validateExecutionDate",
                new Class[] { WEB3FeqOrderUnit.class, Date.class }).asObject();
            return;
        }
        super.validateExecutionDate(l_orderUnit, l_datExecDate);
    }

    /**
     * (validate約定数量)<BR>
     * 約定数量をチェックする。<BR>
     * <BR>
     * 外国株式発注審査個別チェック.validate約定数量()に委譲（deligate）する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_dblQuantity - (約定数量)<BR>
     * 約定数量（今回）<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4292A2EC02E3
     */
    public void validateExecutedQuantity(
        WEB3FeqOrderUnit l_orderUnit,
        double l_dblQuantity) throws WEB3BaseException
    {

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.feq.WEB3FeqOrderManager",
            "validateExecutedQuantity",
            new Class[] {WEB3FeqOrderUnit.class, double.class}))
        {
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager", "validateExecutedQuantity",
                new Class[] { WEB3FeqOrderUnit.class, double.class }).asObject();
            return;
        }
        super.validateExecutedQuantity(l_orderUnit, l_dblQuantity);
    }

    /**
     * (validate現地受渡日)<BR>
     * 現地受渡日をチェックする。<BR>
     * <BR>
     * 外国株式発注審査個別チェック.validate現地受渡日()<BR>
     * に委譲（deligate）する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_datFDeliveryDate - (現地受渡日)<BR>
     * 現地受渡日<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B66851038A
     */
    public void validateFDeliveryDate(
        WEB3FeqOrderUnit l_orderUnit,
        Date l_datFDeliveryDate) throws WEB3BaseException
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.feq.WEB3FeqOrderManager",
            "validateFDeliveryDate",
            new Class[] {WEB3FeqOrderUnit.class, Date.class}))
        {
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager", "validateFDeliveryDate",
                new Class[] { WEB3FeqOrderUnit.class, Date.class }).asObject();
            return;
        }
        super.validateFDeliveryDate(l_orderUnit, l_datFDeliveryDate);
    }
    
    /**
     * (validate約定単価)<BR>
     * 約定単価をチェックする。<BR>
     * <BR>
     * 外国株式発注審査個別チェック.validate約定単価()に委譲（deligate）する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_dblPrice - (約定単価)<BR>
     * 約定単価<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42C39F0D0331
     */
    public void validateExecutedPrice(
        WEB3FeqOrderUnit l_orderUnit,
        double l_dblPrice) throws WEB3BaseException
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.feq.WEB3FeqOrderManager",
            "validateExecutedPrice",
            new Class[] {WEB3FeqOrderUnit.class, double.class}))
        {
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager", "validateExecutedPrice",
                new Class[] { WEB3FeqOrderUnit.class, double.class }).asObject();
            return;
        }
        super.validateExecutedPrice(l_orderUnit, l_dblPrice);
    }

    /**
     * (訂正／取消エラー処理実行)<BR>
     * 該当注文が訂正／取消中で且つ全出来の場合、<BR>
     * 配当注文を訂正／取消エラーにする。
     * <BR>
     * シーケンス図<BR>
     * 「（外株注文）訂正／取消エラー処理実行」参照。<BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * @@throws WEB3BaseException
     */
    public void executeChangeCancelOrderRejected(long l_lngOrderUnitId) throws WEB3BaseException
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.feq.WEB3FeqOrderManager",
            "executeChangeCancelOrderRejected",
            new Class[] {long.class}))
        {
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager", 
                "executeChangeCancelOrderRejected",
                new Class[] {long.class }).asObject();
            return;
        }
        super.executeChangeCancelOrderRejected(l_lngOrderUnitId);
    }
    
    /**
     * (update合計約定金額（円）)<BR>
     * 合計約定金額（円）の再計算を行なう。<BR>
     * <BR>
     * １）　@this.getOrderUnit( )をコールし、注文単位を取得する。<BR>
     * 　@　@　@［引数］<BR>
     * 　@　@　@　@注文単位ID：　@パラメータ.注文単位ID<BR>
     * <BR>
     * 　@　@　@※該当データなしの場合は、例外をスローする。<BR>
     * <BR>
     * ２）　@外国株式トランザクションマネージャ.getトランザクション( )をコールし、<BR>
     * 　@　@　@１）で取得した注文単位に該当するトランザクションを取得する。<BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@外国株式注文単位：　@１）で取得した注文単位<BR>
     * <BR>
     * 　@　@　@※該当データなしの場合は、例外をスローする。<BR>
     * <BR>
     * ３）　@外国株式トランザクションマネージャ.getトランザクション( )の戻り値の要素数分ループし、<BR>
     * 　@　@　@各要素毎に以下内容で計算を行い、計算結果の合計値を計算する。<BR>
     * <BR>
     * 　@　@　@○　@トランザクション.約定単価　@×　@トランザクション.約定数量　@×　@トランザクション.適用為替レート<BR>
     * <BR>
     * 　@　@　@※上記の計算結果は、小数点以下を通貨.円貨換算丸め方式に従って丸めを行う。<BR>
     * <BR>
     * ４）　@３）で取得した合計値で、注文単位テーブル.合計約定金額（円）を更新する。<BR>
     * <BR>
     * @@param l_orderUnitId - 注文単位ID<BR>
     * @@throws WEB3BaseException
     */
    public void updateExecutedAmountYen(long l_orderUnitId) throws WEB3BaseException
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.feq.WEB3FeqOrderManager",
                "updateExecutedAmountYen",
                new Class[] {long.class}))
            {
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.feq.WEB3FeqOrderManager", 
                    "updateExecutedAmountYen",
                    new Class[] {long.class }).asVoid();
                return;
            }
            super.updateExecutedAmountYen(l_orderUnitId);
    }
    
    public WEB3FeqOrderUnit[] getNettingOrderUnit(
            Long l_lngAccountId,
            String l_strInstitutionCode,
            Date l_datBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNettingOrderUnit(Long, String, Date)-->ForMock";
        log.entering(STR_METHOD_NAME);
        
        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.feq.WEB3FeqOrderManager",
            "getNettingOrderUnit",
            new Class[] {Long.class,String.class, Date.class},
            new Object[]{l_lngAccountId, l_strInstitutionCode, l_datBizDate});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.feq.WEB3FeqOrderManager",
            "getNettingOrderUnit",
            new Class[] {Long.class, String.class, Date.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "getNettingOrderUnit",
                new Class[] {Long.class,String.class, Date.class}).asWEB3BaseException();

            //3)MockFor --〉 asObject
            return (WEB3FeqOrderUnit[])TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "getNettingOrderUnit",
                new Class[] {Long.class,String.class, Date.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getNettingOrderUnit(l_lngAccountId, l_strInstitutionCode, l_datBizDate);

    }
}
@

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
     * ���O�o�̓��[�e�B���e�B�B<BR>
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
     * (get�o���I���Ώے����P��(Mock))<BR>
     * �o���I���ΏۂƂȂ钍���P�ʂ̈ꗗ���擾����B<BR>
     * @@param l_lngAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * @@param l_datBizDate - (������)<BR>
     * ������<BR>
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


        //1�j�ҝ���
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
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "getOrderExecEndObjectOrderUnit",
                new Class[] {Long.class, String.class, String.class, Date.class}).asWEB3BaseException();

            //3)MockFor --�r asObject
            return (WEB3FeqOrderUnit[])TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "getOrderExecEndObjectOrderUnit",
                new Class[] {Long.class, String.class, String.class, Date.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getOrderExecEndObjectOrderUnit(l_lngAccountId, l_strInstitutionCode, l_strMarketCode, l_datBizDate);
    }
    
    /**
     * (update�T�Z��n���)<BR>
     * �T�Z��n����Čv�Z���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������jupdate�T�Z��n����v�Q�ƁB<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_datBaseDate - (���)<BR>
     * ���<BR>
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
                //2�jMockFor --�r asWEB3BaseException
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
     * (validate��������)<BR>
     * �ivalidateChangeOrder�̃I�[�o�[���C�h�j<BR>
     * ���������̔����R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������jvalidate���������v �Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     *
     * @@param l_orderSpec - (�������e)<BR>
     * �������e�I�u�W�F�N�g<BR>
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
     * (validate����)<BR>
     * �����̃`�F�b�N���s���B<BR>
     * <BR>
     * �O�����������R���ʃ`�F�b�N.validate����()�ɈϏ��ideligate�j����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_datExecDate - (����)<BR>
     * ����<BR>
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
     * (validate��萔��)<BR>
     * ��萔�ʂ��`�F�b�N����B<BR>
     * <BR>
     * �O�����������R���ʃ`�F�b�N.validate��萔��()�ɈϏ��ideligate�j����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_dblQuantity - (��萔��)<BR>
     * ��萔�ʁi����j<BR>
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
     * (validate���n��n��)<BR>
     * ���n��n�����`�F�b�N����B<BR>
     * <BR>
     * �O�����������R���ʃ`�F�b�N.validate���n��n��()<BR>
     * �ɈϏ��ideligate�j����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_datFDeliveryDate - (���n��n��)<BR>
     * ���n��n��<BR>
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
     * (validate���P��)<BR>
     * ���P�����`�F�b�N����B<BR>
     * <BR>
     * �O�����������R���ʃ`�F�b�N.validate���P��()�ɈϏ��ideligate�j����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_dblPrice - (���P��)<BR>
     * ���P��<BR>
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
     * (�����^����G���[�������s)<BR>
     * �Y�������������^������Ŋ��S�o���̏ꍇ�A<BR>
     * �z������������^����G���[�ɂ���B
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������j�����^����G���[�������s�v�Q�ƁB<BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
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
     * (update���v�����z�i�~�j)<BR>
     * ���v�����z�i�~�j�̍Čv�Z���s�Ȃ��B<BR>
     * <BR>
     * �P�j�@@this.getOrderUnit( )���R�[�����A�����P�ʂ��擾����B<BR>
     * �@@�@@�@@�m�����n<BR>
     * �@@�@@�@@�@@�����P��ID�F�@@�p�����[�^.�����P��ID<BR>
     * <BR>
     * �@@�@@�@@���Y���f�[�^�Ȃ��̏ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �Q�j�@@�O�������g�����U�N�V�����}�l�[�W��.get�g�����U�N�V����( )���R�[�����A<BR>
     * �@@�@@�@@�P�j�Ŏ擾���������P�ʂɊY������g�����U�N�V�������擾����B<BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�O�����������P�ʁF�@@�P�j�Ŏ擾���������P��<BR>
     * <BR>
     * �@@�@@�@@���Y���f�[�^�Ȃ��̏ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �R�j�@@�O�������g�����U�N�V�����}�l�[�W��.get�g�����U�N�V����( )�̖߂�l�̗v�f�������[�v���A<BR>
     * �@@�@@�@@�e�v�f���Ɉȉ����e�Ōv�Z���s���A�v�Z���ʂ̍��v�l���v�Z����B<BR>
     * <BR>
     * �@@�@@�@@���@@�g�����U�N�V����.���P���@@�~�@@�g�����U�N�V����.��萔�ʁ@@�~�@@�g�����U�N�V����.�K�p�בփ��[�g<BR>
     * <BR>
     * �@@�@@�@@����L�̌v�Z���ʂ́A�����_�ȉ���ʉ�.�~�݊��Z�ۂߕ����ɏ]���Ċۂ߂��s���B<BR>
     * <BR>
     * �S�j�@@�R�j�Ŏ擾�������v�l�ŁA�����P�ʃe�[�u��.���v�����z�i�~�j���X�V����B<BR>
     * <BR>
     * @@param l_orderUnitId - �����P��ID<BR>
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
        
        //1�j�ҝ���
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
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "getNettingOrderUnit",
                new Class[] {Long.class,String.class, Date.class}).asWEB3BaseException();

            //3)MockFor --�r asObject
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

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.49.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondOrderManagerForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3BondOrderManagerForMock.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/08 金傑 (中訊) 新規作成
*/
package webbroker3.bd;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 拡張債券注文マネージャクラスForMock
 *
 * @@author 金傑(中訊)
 * @@version 1.0
 */
public class WEB3BondOrderManagerForMock extends WEB3BondOrderManager
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        		WEB3BondOrderManagerForMock.class);
    
    public void validateOrderCancelPossibleStatus(WEB3BondOrderUnit l_bondOrderUnit) throws WEB3BaseException
	{
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.bd.WEB3BondOrderManager",
                "validateOrderCancelPossibleStatus",
                new Class[] {WEB3BondOrderUnit.class},
                new Object[]{l_bondOrderUnit});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.bd.WEB3BondOrderManager",
				"validateOrderCancelPossibleStatus", new Class[] { WEB3BondOrderUnit.class }))
		{
        	log.debug("webbroker3.bd.WEB3BondOrderManager.validateOrderCancelPossibleStatus(WEB3BondOrderUnit)");
        	
        	TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.bd.WEB3BondOrderManager",
    				"validateOrderCancelPossibleStatus", new Class[] { WEB3BondOrderUnit.class }).asWEB3BaseException();
        	
        	TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.bd.WEB3BondOrderManager",
    				"validateOrderCancelPossibleStatus", new Class[] { WEB3BondOrderUnit.class }).asVoid();
        	return;
		}
        super.validateOrderCancelPossibleStatus(l_bondOrderUnit);
	}
    
    public void validateSellOrder(SubAccount l_subAccount, WEB3BondProduct l_bondProduct,
            WEB3BondNewOrderSpec l_bondNewOrderSpec) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.bd.WEB3BondOrderManager",
                "validateSellOrder",
                new Class[]{SubAccount.class,WEB3BondProduct.class,WEB3BondNewOrderSpec.class},
                new Object[]{l_subAccount,l_bondProduct,l_bondNewOrderSpec});
        
        if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.bd.WEB3BondOrderManager",
                "validateSellOrder",
                new Class[]{SubAccount.class,WEB3BondProduct.class,WEB3BondNewOrderSpec.class}))
        {
            log.debug("webbroker3.bd.WEB3BondOrderManager.validateSellOrder(SubAccount,WEB3BondProduct,WEB3BondNewOrderSpec)");
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(                
                    "webbroker3.bd.WEB3BondOrderManager",
                    "validateSellOrder",
                    new Class[]{SubAccount.class,WEB3BondProduct.class,WEB3BondNewOrderSpec.class}).asWEB3BaseException();
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(                
                    "webbroker3.bd.WEB3BondOrderManager",
                    "validateSellOrder",
                    new Class[]{SubAccount.class,WEB3BondProduct.class,WEB3BondNewOrderSpec.class}).asVoid();
            return;
        }
        super.validateSellOrder(l_subAccount, l_bondProduct, l_bondNewOrderSpec);
    }
    
    public OrderSubmissionResult submitNewOrder(
            SubAccount subAccount, 
            ProductTypeEnum productType,
            NewOrderSpec inSpec, 
            long orderId, 
            String tradingPassword, 
            boolean skipOrderValidation)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.bd.WEB3BondOrderManager",
                "submitNewOrder",
                new Class[]{SubAccount.class,
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
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.bd.WEB3BondOrderManager",
                "submitNewOrder",
                new Class[]{SubAccount.class,
                             ProductTypeEnum.class,
                             NewOrderSpec.class,
                             long.class,
                             String.class,
                             boolean.class}))
        {
            log.debug("webbroker3.bd.WEB3BondOrderForMockManager.submitNewOrder");
            
            return(OrderSubmissionResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.bd.WEB3BondOrderManager",
                    "submitNewOrder",
                    new Class[]{SubAccount.class,
                                 ProductTypeEnum.class,
                                 NewOrderSpec.class,
                                 long.class,
                                 String.class,
                                 boolean.class}).asObject();
            
        }
        return super.submitNewOrder(subAccount,productType,inSpec,orderId,tradingPassword,skipOrderValidation);
    }

    /**
     * (validate（応募/買付）注文(Mock))<BR>
     * validate応募/買付注文<BR>
     */
    public void validateRecruitOrBuyOrder(SubAccount l_subAccount, 
        WEB3BondProduct l_bondProduct, String l_strDealDiv, 
        String l_strSettlementDiv, double l_dblOrderQuantity) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateRecruitOrBuyOrder(SubAccount, WEB3BondProduct, String, String, double)--ForMock";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.bd.WEB3BondOrderManager",
            "validateRecruitOrBuyOrder",
            new Class[]{SubAccount.class,WEB3BondProduct.class,String.class, String.class, double.class},
            new Object[]{l_subAccount, 
                l_bondProduct, l_strDealDiv, 
                l_strSettlementDiv, new Double(l_dblOrderQuantity)});

        if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.bd.WEB3BondOrderManager",
                "validateRecruitOrBuyOrder",
                new Class[]{SubAccount.class,WEB3BondProduct.class,String.class, String.class, double.class}))
        {
            log.debug("validateRecruitOrBuyOrder(SubAccount, WEB3BondProduct, String, String, double)--ForMock");
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(                
                    "webbroker3.bd.WEB3BondOrderManager",
                    "validateRecruitOrBuyOrder",
                    new Class[]{SubAccount.class,WEB3BondProduct.class,String.class, String.class, double.class}).asWEB3BaseException();
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(                
                    "webbroker3.bd.WEB3BondOrderManager",
                    "validateRecruitOrBuyOrder",
                    new Class[]{SubAccount.class,WEB3BondProduct.class,String.class, String.class, double.class}).asVoid();
            return;
        }
        super.validateRecruitOrBuyOrder(l_subAccount, 
            l_bondProduct, l_strDealDiv, 
            l_strSettlementDiv, l_dblOrderQuantity);
        log.exiting(STR_METHOD_NAME);
    }
    
    public WEB3BondOrderUnit getBondOrderUnitByOrderId(long l_lngOrderId) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.bd.WEB3BondOrderManager",
                "getBondOrderUnitByOrderId", new Class[]
                { long.class }, new Object[]
                { new Long(l_lngOrderId) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.bd.WEB3BondOrderManager", "getBondOrderUnitByOrderId",
                new Class[]
                { long.class }))
        {
            log.debug("webbroker3.bd.WEB3BondOrderManagerForMock.getBondOrderUnitByOrderId()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.bd.WEB3BondOrderManager",
                    "getBondOrderUnitByOrderId", new Class[]
                    { long.class }).asWEB3BaseException();
            return (WEB3BondOrderUnit) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.bd.WEB3BondOrderManager", "getBondOrderUnitByOrderId", new Class[]
                    { long.class }).asObject();
        }
        return super.getBondOrderUnitByOrderId(l_lngOrderId);
    }
    
    public void validateAccountHandlingPossibleProduct(WEB3BondProduct l_bondProduct, String l_strDealDiv)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.bd.WEB3BondOrderManager",
                "validateAccountHandlingPossibleProduct", new Class[]
                { WEB3BondProduct.class, String.class }, new Object[]
                { l_bondProduct, l_strDealDiv });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.bd.WEB3BondOrderManager",
                "validateAccountHandlingPossibleProduct", new Class[]
                { WEB3BondProduct.class, String.class }))
        {
            log.debug("webbroker3.bd.WEB3BondOrderManagerForMock.validateAccountHandlingPossibleProduct()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.bd.WEB3BondOrderManager",
                    "validateAccountHandlingPossibleProduct", new Class[]
                    { WEB3BondProduct.class, String.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.bd.WEB3BondOrderManager",
                    "validateAccountHandlingPossibleProduct", new Class[]
                    { WEB3BondProduct.class, String.class }).asVoid();
            return;
        }
        super.validateAccountHandlingPossibleProduct(l_bondProduct, l_strDealDiv);
    }
}
@

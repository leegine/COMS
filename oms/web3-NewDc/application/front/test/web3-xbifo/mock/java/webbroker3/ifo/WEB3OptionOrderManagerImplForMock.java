head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.42.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionOrderManagerImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP注文マネージャForMock(WEB3OptionOrderManagerImplForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/02 徐宏偉 (中訊) 新規作成
*/
package webbroker3.ifo;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoSettleContractOrderSpec;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * OP注文マネージャForMock
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3OptionOrderManagerImplForMock extends WEB3FuturesOrderManagerImpl
{
    /**
     * ログユーティリティ<BR>
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3OptionOrderManagerImplForMock.class);

    /**
     * (get注文単位(Mock))<BR>
     * 指定条件に一致する注文の注文単位オブジェクトを返却する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_productType - (商品タイプ)<BR>
     * （ProductTypeEnumにて定義）<BR>
     * @@param l_strDiscriminationCode - 識別コード
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit
     * @@throws WEB3BaseException
     */
    public OrderUnit getOrderUnit(
        String l_strInstitutionCode,
        String l_strBranchCode,
        ProductTypeEnum l_productType,
        String l_strDiscriminationCode)
            throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            "getOrderUnit(l_strSecutieCompanyCode,l_strBranchCode," +
            "l_productType,l_strDiscriminationCode)--ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "getOrderUnit",
            new Class[] {String.class, String.class, ProductTypeEnum.class, String.class},
            new Object[]{l_strInstitutionCode, l_strBranchCode, l_productType, l_strDiscriminationCode});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "getOrderUnit",
            new Class[] {String.class, String.class, ProductTypeEnum.class, String.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "getOrderUnit",
                new Class[] {
                    String.class,
                    String.class,
                    ProductTypeEnum.class,
                    String.class}).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            return (OrderUnit)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "getOrderUnit",
                new Class[] {
                    String.class,
                    String.class,
                    ProductTypeEnum.class,
                    String.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getOrderUnit(
            l_strInstitutionCode,
            l_strBranchCode,
            l_productType,
            l_strDiscriminationCode);
    }

    /**
     * (insert新規建注文キュー(Mock))<BR>
     * <BR>
     * （insertOpenContractHostOrder）<BR>
     * @@param l_lngOrderId - 注文ID<BR>
     * 注文ID。
     * @@throws WEB3BaseException
     */
    public void insertOpenContractHostOrder(
        long l_lngOrderId)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "insertOpenContractHostOrder(long) -- ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "insertOpenContractHostOrder",
            new Class[] {long.class},
            new Object[]{new Long(l_lngOrderId)});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "insertOpenContractHostOrder",
            new Class[] {long.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "insertOpenContractHostOrder",
                new Class[] {long.class}).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "insertOpenContractHostOrder",
                new Class[] {long.class}).asVoid();
            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.insertOpenContractHostOrder(l_lngOrderId);

    }

    /**
     * (is内容通知済注文(Mock))<BR>
     * 発注済注文であるかを判定する。<BR>
     * @@return boolean
     */
    public boolean isNotifyEndOrder(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isNotifyEndOrder(l_orderUnit)--ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder",
            new Class[] {OrderUnit.class},
            new Object[]{l_orderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder",
            new Class[] {OrderUnit.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isNotifyEndOrder",
                new Class[] {OrderUnit.class}).asWEB3BaseException();

            //3)MockFor --〉 asBoolean
            return (boolean)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isNotifyEndOrder",
                new Class[] {OrderUnit.class}).asBoolean();
        }

        log.exiting(STR_METHOD_NAME);
        return super.isNotifyEndOrder(l_orderUnit);
    }

    /**
     * (validate注文(Mock))<BR>
     */
    public void validateOrder(
        SubAccount l_subAccount,
        String l_strFuturesOptionDivision)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateOrder(l_subAccount,l_strFuturesOptionDivision)--ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "validateOrder",
            new Class[] {SubAccount.class, String.class},
            new Object[]{l_subAccount, l_strFuturesOptionDivision});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "validateOrder",
            new Class[] {SubAccount.class, String.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "validateOrder",
                new Class[] {SubAccount.class, String.class}).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "validateOrder",
                new Class[] {SubAccount.class, String.class}).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateOrder(l_subAccount, l_strFuturesOptionDivision);
    }

    /**
     * (insert返済注文キュー(Mock))<BR>
     * @@param l_lngOrderId - 注文ID<BR>
     * 注文ID。
     * @@throws WEB3BaseException
     */
    public void insertSettleContractHostOrder(
        long l_lngOrderId)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "insertSettleContractHostOrder(long)--ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "insertSettleContractHostOrder",
            new Class[] {long.class},
            new Object[]{new Long(l_lngOrderId)});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "insertSettleContractHostOrder",
            new Class[] {long.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "insertSettleContractHostOrder",
                new Class[] {long.class}).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "insertSettleContractHostOrder",
                new Class[] {long.class}).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.insertSettleContractHostOrder(l_lngOrderId);
    }
    
    public OrderUnit getOrderUnit(long orderUnitId) throws NotFoundException
	{
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "getOrderUnit",
                new Class[] {long.class},
                new Object[]{new Long(orderUnitId)});
        if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "getOrderUnit",
                new Class[] {long.class}))
        {
        	log.debug( "webbroker3.ifo.WEB3OptionOrderManagerImplForMock.getOrderUnit(long)");
        	return (OrderUnit)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getOrderUnit",
                    new Class[] {long.class}).asObject();
        }
        return super.getOrderUnit(orderUnitId);
	}
    
    public OrderValidationResult validateChangeSettleContractOrder(WEB3GentradeSubAccount l_subAccount,
			WEB3IfoChangeSettleContractOrderSpec l_settleContractChangeSpec, boolean l_blnIsSkipDelayStatusCheck)
	{
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "validateChangeSettleContractOrder",
                new Class[] {WEB3GentradeSubAccount.class,WEB3IfoChangeSettleContractOrderSpec.class,boolean.class},
                new Object[]{l_subAccount,l_settleContractChangeSpec,new Boolean(l_blnIsSkipDelayStatusCheck)});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3OptionOrderManagerImpl",
				"validateChangeSettleContractOrder", new Class[] { WEB3GentradeSubAccount.class,
						WEB3IfoChangeSettleContractOrderSpec.class, boolean.class }))
		{
        	log.debug( "webbroker3.ifo.WEB3OptionOrderManagerImplForMock.validateChangeSettleContractOrder(WEB3GentradeSubAccount,WEB3IfoChangeSettleContractOrderSpec,boolean)");
        	
        	return(OrderValidationResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl",
    				"validateChangeSettleContractOrder", new Class[] { WEB3GentradeSubAccount.class,
    						WEB3IfoChangeSettleContractOrderSpec.class, boolean.class }).asObject();
		}
        
    	return super.validateChangeSettleContractOrder(l_subAccount,l_settleContractChangeSpec,l_blnIsSkipDelayStatusCheck);
	}
    
	public OrderValidationResult validateFuturesChangeOrder(WEB3GentradeSubAccount l_subAccount,
			WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec, boolean l_blnIsSkipDelayStatusCheck)
	{
		TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
	            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
	            "validateFuturesChangeOrder",
	            new Class[] {WEB3GentradeSubAccount.class,WEB3IfoOpenContractChangeSpec.class,boolean.class},
	            new Object[]{l_subAccount, l_ifoOpenContractChangeSpec, new Boolean(l_blnIsSkipDelayStatusCheck)});
		if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
				"validateFuturesChangeOrder", new Class[] { WEB3GentradeSubAccount.class,
						WEB3IfoOpenContractChangeSpec.class, boolean.class }))
		{
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImpl.WEB3FuturesOrderManagerImplForMock.validateFuturesChangeOrder(WEB3GentradeSubAccount,WEB3IfoOpenContractChangeSpec,boolean)");
            return (OrderValidationResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
					"validateFuturesChangeOrder", new Class[] { WEB3GentradeSubAccount.class,
							WEB3IfoOpenContractChangeSpec.class, boolean.class }).asObject();
		}
		
		return super.validateFuturesChangeOrder(l_subAccount,l_ifoOpenContractChangeSpec,l_blnIsSkipDelayStatusCheck);
	}
    
    public List getOrderUnits(
            SubAccount l_subAccount,
            ProductTypeEnum l_productType,
            String l_strSearchCondition,
            String[] l_searchCondContainers,
            String l_strSortCondition)
                throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "getOrderUnits",
                new Class[] {SubAccount.class,
                        ProductTypeEnum.class, String.class,String[].class,String.class},
                new Object[]{l_subAccount, l_productType, l_strSearchCondition,l_searchCondContainers,l_strSortCondition});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "getOrderUnits", new Class[] { SubAccount.class,
                ProductTypeEnum.class, String.class,String[].class,String.class}))
        {
            log.debug("webbroker3.ifo.WEB3OptionOrderManagerImpl.WEB3OptionOrderManagerImplForMock.getOrderUnits(SubAccount,ProductTypeEnum,String,String[],String)");
            return (List)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getOrderUnits", new Class[] { SubAccount.class,
                    ProductTypeEnum.class, String.class,String[].class,String.class }).asObject();
        }
        
        return super.getOrderUnits(l_subAccount,l_productType,l_strSearchCondition,l_searchCondContainers,l_strSortCondition);
    }

    /**
     * (is未発注遅延注文(Mock))<BR>
     * @@param l_ifoOrderUnit - (注文単位) <BR>
     * @@return boolean
     */
    public boolean isNotOrderedDelay(IfoOrderUnit l_ifoOrderUnit)
    {
        final String STR_METHOD_NAME = "isNotOrderedDelay(IfoOrderUnit)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotOrderedDelay",
            new Class[] {IfoOrderUnit.class},
            new Object[]{l_ifoOrderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotOrderedDelay",
            new Class[] {IfoOrderUnit.class}))
        {
            log.exiting(STR_METHOD_NAME);
            //3)MockFor --〉 asVoid
            return (boolean)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isNotOrderedDelay",
                new Class[] {IfoOrderUnit.class}).asBoolean();

        }

        log.exiting(STR_METHOD_NAME);
        return super.isNotOrderedDelay(l_ifoOrderUnit);
    }

    public Date getExpirationDate(
            Date l_datExpirationDate,
            String l_strProductCode,
            String l_strMarketCode,
            String l_strFutureOptionDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExpirationDate()-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "getExpirationDate",
            new Class[] {Date.class, String.class, String.class, String.class},
            new Object[]{l_datExpirationDate, l_strProductCode, l_strMarketCode, l_strFutureOptionDiv});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "getExpirationDate",
            new Class[] {Date.class, String.class, String.class, String.class}))
        {
            log.exiting(STR_METHOD_NAME);
            //3)MockFor --〉 asVoid
            return (Date)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "getExpirationDate",
                new Class[] {Date.class, String.class, String.class, String.class}).asObject();

        }

        log.exiting(STR_METHOD_NAME);
        return super.getExpirationDate(l_datExpirationDate, l_strProductCode, l_strMarketCode, l_strFutureOptionDiv);
    }
    
    public WEB3IfoEstimateDeliveryAmountCalcResult calcChangeEstimateDeliveryAmount(
            WEB3GentradeCommission l_commission,
            double l_dblLimitPrice,
            WEB3GentradeSubAccount l_subAccount,
            WEB3IfoTradedProductImpl l_futuresOptionTradedProduct,
            double l_dblQuantity,
            SideEnum l_dealing,
            boolean l_blnIsClosingContractOrder,
            double l_dblExecQuantity,
            double l_dblSumTransferredAssetBookValue,
            boolean l_blnIsSkipPriceCheck)
                throws WEB3BaseException
        {
            //1）參數驗證
            TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "calcChangeEstimateDeliveryAmount",
                new Class[] {WEB3GentradeCommission.class,
                    double.class,
                    WEB3GentradeSubAccount.class,
                    WEB3IfoTradedProductImpl.class,
                    double.class,
                    SideEnum.class,
                    boolean.class,
                    double.class,
                    double.class,
                    boolean.class},
                new Object[]{l_commission,
                    new Double(l_dblLimitPrice),
                    l_subAccount,
                    l_futuresOptionTradedProduct,
                    new Double(l_dblQuantity),
                    l_dealing,
                    new Boolean(l_blnIsClosingContractOrder),
                    new Double(l_dblExecQuantity),
                    new Double(l_dblSumTransferredAssetBookValue),
                    new Boolean(l_blnIsSkipPriceCheck)});
    
            if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "calcChangeEstimateDeliveryAmount",
                new Class[] {WEB3GentradeCommission.class,
                        double.class,
                        WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class,
                        double.class,
                        SideEnum.class,
                        boolean.class,
                        double.class,
                        double.class,
                        boolean.class}))
            {
                //3)MockFor --〉 asVoid
                return (WEB3IfoEstimateDeliveryAmountCalcResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[] {WEB3GentradeCommission.class,
                            double.class,
                            WEB3GentradeSubAccount.class,
                            WEB3IfoTradedProductImpl.class,
                            double.class,
                            SideEnum.class,
                            boolean.class,
                            double.class,
                            double.class,
                            boolean.class}).asObject();
    
            }
    
            return super.calcChangeEstimateDeliveryAmount(l_commission, l_dblLimitPrice,
                    l_subAccount, l_futuresOptionTradedProduct,
                    l_dblQuantity, l_dealing,
                    l_blnIsClosingContractOrder, l_dblExecQuantity,
                    l_dblSumTransferredAssetBookValue, l_blnIsSkipPriceCheck);
        }
    
    public void notifyRLS(IfoOrderUnit l_orderUnit, OrderManagerPersistenceContext l_context) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3OptionOrderManagerImpl", "notifyRLS",
                new Class[]
                {IfoOrderUnit.class, OrderManagerPersistenceContext.class}, new Object[]
                {l_orderUnit, l_context});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3OptionOrderManagerImpl", "notifyRLS",
                new Class[]
                {IfoOrderUnit.class, OrderManagerPersistenceContext.class}))
        {
            log.debug("webbroker3.ifo.WEB3OptionOrderManagerImplForMock.notifyRLS()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl", "notifyRLS",
                    new Class[]
                    {IfoOrderUnit.class, OrderManagerPersistenceContext.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl", "notifyRLS",
                    new Class[]
                    {IfoOrderUnit.class, OrderManagerPersistenceContext.class}).asVoid();
            return;
        }
        super.notifyRLS(l_orderUnit, l_context);
    }
    
    public void validateOrderChangePossibleStatus(Order l_order)
        throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3OptionOrderManagerImpl", "validateOrderChangePossibleStatus",
                new Class[]
                {Order.class}, new Object[]
                {l_order});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3OptionOrderManagerImpl", "validateOrderChangePossibleStatus",
                new Class[]
                {Order.class}))
        {
            log.debug("webbroker3.ifo.WEB3OptionOrderManagerImplForMock.validateOrderChangePossibleStatus()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl", "validateOrderChangePossibleStatus",
                    new Class[]
                    {Order.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl", "validateOrderChangePossibleStatus",
                    new Class[]
                    {Order.class}).asVoid();
            return;
        }
        super.validateOrderChangePossibleStatus(l_order);
    }
    
    public void validateOrderCancelPossibleStatus(Order l_order)
    throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3OptionOrderManagerImpl", "validateOrderCancelPossibleStatus",
                new Class[]
                {Order.class}, new Object[]
                {l_order});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3OptionOrderManagerImpl", "validateOrderCancelPossibleStatus",
                new Class[]
                {Order.class}))
        {
            log.debug("webbroker3.ifo.WEB3OptionOrderManagerImplForMock.validateOrderCancelPossibleStatus()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl", "validateOrderCancelPossibleStatus",
                    new Class[]
                    {Order.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl", "validateOrderCancelPossibleStatus",
                    new Class[]
                    {Order.class}).asVoid();
            return;
        }
        super.validateOrderCancelPossibleStatus(l_order);
    }
    
    public NewOrderValidationResult validateOpenContractOrder(
            WEB3GentradeSubAccount l_subAccount,
            WEB3IfoOpenContractOrderSpec l_openContractOrderSpec)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3OptionOrderManagerImpl", "validateOpenContractOrder",
                new Class[]
                {WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class}, new Object[]
                {l_subAccount, l_openContractOrderSpec});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3OptionOrderManagerImpl", "validateOpenContractOrder",
                new Class[]
                {WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class}))
        {
            log.debug("webbroker3.ifo.WEB3OptionOrderManagerImplForMock.validateOpenContractOrder()");
//            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl", "validateOpenContractOrder",
//                    new Class[]
//                    {WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class}).asWEB3BaseException();

            return (NewOrderValidationResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl", "validateOpenContractOrder",
                    new Class[]
                    {WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class}).asObject();
        }
        return super.validateOpenContractOrder(l_subAccount, l_openContractOrderSpec);
    }
    
    public WEB3IfoEstimateDeliveryAmountCalcResult calcEstimateDeliveryAmount(
            WEB3GentradeCommission l_commission,
            double l_dblLimitPrice,
            SubAccount l_subAccount,
            WEB3IfoTradedProductImpl l_tradeProduct,
            double l_dblQuantity,
            SideEnum l_dealing,
            boolean l_blnIsClosingContractOrder,
            boolean l_blnIsSkipPriceCheck)
                throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3OptionOrderManagerImpl", "calcEstimateDeliveryAmount",
                new Class[]
                {WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class, double.class, SideEnum.class, boolean.class, boolean.class}, new Object[]
                {l_commission,
                new Double(l_dblLimitPrice),
                l_subAccount,
                l_tradeProduct,
                new Double(l_dblQuantity),
                l_dealing,
                new Boolean(l_blnIsClosingContractOrder),
                new Boolean(l_blnIsSkipPriceCheck)});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3OptionOrderManagerImpl", "calcEstimateDeliveryAmount",
                new Class[]
                {WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class, double.class, SideEnum.class, boolean.class, boolean.class}))
        {
            log.debug("webbroker3.ifo.WEB3OptionOrderManagerImplForMock.calcEstimateDeliveryAmount()");
//            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl", "validateOpenContractOrder",
//                    new Class[]
//                    {WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class}).asWEB3BaseException();

            return (WEB3IfoEstimateDeliveryAmountCalcResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl", "calcEstimateDeliveryAmount",
                    new Class[]
                    {WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class, double.class, SideEnum.class, boolean.class, boolean.class}).asObject();
        }
        return super.calcEstimateDeliveryAmount(
                l_commission,
                l_dblLimitPrice,
                l_subAccount,
                l_tradeProduct,
                l_dblQuantity,
                l_dealing,
                l_blnIsClosingContractOrder,
                l_blnIsSkipPriceCheck);
    }
    
    public SettleContractEntry[] createSettleContractEntry(long l_lngOrderUnitId, double l_dblOrderQuantity,
            WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "createSettleContractEntry", new Class[]
                {long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class}, new Object[]
                {new Long(l_lngOrderUnitId), new Double(l_dblOrderQuantity), l_futuresOptionsCloseMarginContractUnits});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "createSettleContractEntry", new Class[]
                {long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class}))
        {
            log.debug("webbroker3.ifo.WEB3OptionOrderManagerImplForMock.createSettleContractEntry()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "createSettleContractEntry", new Class[]
                    {long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class})
                    .asWEB3BaseException();
            return (SettleContractEntry[]) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl", "createSettleContractEntry", new Class[]
                    {long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class}).asObject();
        }
        return super.createSettleContractEntry(l_lngOrderUnitId, l_dblOrderQuantity,
                l_futuresOptionsCloseMarginContractUnits);
    }
    
    public NewOrderValidationResult validateSettleContractOrder(
            WEB3GentradeSubAccount l_subAccount,
            WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec,
            WEB3IfoContractImpl l_ifoContractImpl)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "validateSettleContractOrder", new Class[]
            {WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class, WEB3IfoContractImpl.class}, new Object[]
            {l_subAccount, l_settleContractOrderSpec, l_ifoContractImpl});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "validateSettleContractOrder", new Class[]
            {WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class, WEB3IfoContractImpl.class}))
        {
            log.debug("webbroker3.ifo.WEB3OptionOrderManagerImplForMock.validateSettleContractOrder()");
            
            return (NewOrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl", "validateSettleContractOrder", new Class[]
                    {WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class, WEB3IfoContractImpl.class}).asObject();
        }
        return super.validateSettleContractOrder(l_subAccount, l_settleContractOrderSpec, l_ifoContractImpl);
    }
    

    public NewOrderValidationResult validateSettleContractOrder(WEB3GentradeSubAccount l_subAccount,
            WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "validateSettleContractOrder", new Class[]
                {WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class}, new Object[]
                {l_subAccount, l_settleContractOrderSpec});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "validateSettleContractOrder", new Class[]
                {WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class}))
        {
            log.debug("webbroker3.ifo.WEB3OptionOrderManagerImplForMock.validateSettleContractOrder()");
            return (NewOrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl", "validateSettleContractOrder", new Class[]
                    {WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class}).asObject();
        }
        return super.validateSettleContractOrder(l_subAccount, l_settleContractOrderSpec);
    }

    public OrderUnit[] getOrderUnits(long orderId)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "getOrderUnits",
                new Class[] {long.class},
                new Object[]{new Long(orderId)});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "getOrderUnits", new Class[] {long.class}))
        {
            log.debug("webbroker3.ifo.WEB3OptionOrderManagerImpl.WEB3OptionOrderManagerImplForMock.getOrderUnits(long)");
            return (OrderUnit[])TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getOrderUnits", new Class[] {long.class}).asObject();
        }
        
        return super.getOrderUnits(orderId);
    }

    public boolean isCarryoverOrder(IfoOrderUnit l_ifoOrderUnit) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isCarryoverOrder",
                new Class[] {IfoOrderUnit.class},
                new Object[]{l_ifoOrderUnit});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isCarryoverOrder", new Class[] {IfoOrderUnit.class}))
        {
            log.debug("webbroker3.ifo.WEB3OptionOrderManagerImpl.WEB3OptionOrderManagerImplForMock.isCarryoverOrder(IfoOrderUnit)");
            return (boolean)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "isCarryoverOrder", new Class[] {IfoOrderUnit.class}).asBoolean();
        }
        
        return super.isCarryoverOrder(l_ifoOrderUnit);
    }

    public ProcessingResult expireOrder(final long orderId)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "expireOrder",
                new Class[] {long.class},
                new Object[]{new Long(orderId)});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "expireOrder", new Class[] {long.class}))
        {
            log.debug("webbroker3.ifo.WEB3OptionOrderManagerImpl.WEB3OptionOrderManagerImplForMock.expireOrder(long)");
            return (ProcessingResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "expireOrder", new Class[] {long.class}).asObject();
        }
        
        return super.expireOrder(orderId);
    }
    
    public String getErrorReasonCode(String l_strErrorCode)throws WEB3BaseException 
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "getErrorReasonCode",
            new Class[]{String.class},
            new Object[]{l_strErrorCode});
        if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "getErrorReasonCode",
            new Class[]{String.class}))
        {
            log.debug("webbroker3.ifo.WEB3OptionOrderManagerImplForMock.getErrorReasonCode()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "getErrorReasonCode",
                new Class[]{String.class}).asWEB3BaseException();
            return(String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "getErrorReasonCode",
                new Class[]{String.class}).asObject();
        }
        return super.getErrorReasonCode(l_strErrorCode);
    }

    public OrderSubmissionResult submitSettleContractOrder(SubAccount l_subAccount, IfoSettleContractOrderSpec l_spec,
            long l_lngOrderId, String l_strTradingPassword, boolean l_blnIsSkipOrderValidation)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "submitSettleContractOrder", new Class[]
                { SubAccount.class, IfoSettleContractOrderSpec.class, long.class, String.class, boolean.class },
                new Object[]
                { l_subAccount, l_spec, new Long(l_lngOrderId), l_strTradingPassword,
                        new Boolean(l_blnIsSkipOrderValidation) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "submitSettleContractOrder", new Class[]
                { SubAccount.class, IfoSettleContractOrderSpec.class, long.class, String.class, boolean.class }))
        {
            log.debug("webbroker3.ifo.WEB3FuturesOrderManagerImplForMock.submitSettleContractOrder()");
            return (OrderSubmissionResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "submitSettleContractOrder", new Class[]
                    { SubAccount.class, IfoSettleContractOrderSpec.class, long.class, String.class, boolean.class })
                    .asObject();
        }
        return new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT);
    }

    public IfoOrderUnitParams[] removeCarryOverOriginalOrderUnit(IfoOrderUnitParams[] l_orderUnitList) throws WEB3BaseException 
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "removeCarryOverOriginalOrderUnit",
            new Class[]{IfoOrderUnitParams[].class},
            new Object[]{l_orderUnitList});
        if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "removeCarryOverOriginalOrderUnit",
            new Class[]{IfoOrderUnitParams[].class}))
        {
            log.debug("webbroker3.ifo.WEB3OptionOrderManagerImplForMock.removeCarryOverOriginalOrderUnit()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "removeCarryOverOriginalOrderUnit",
                new Class[]{IfoOrderUnitParams[].class}).asWEB3BaseException();
            return(IfoOrderUnitParams[])TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "removeCarryOverOriginalOrderUnit",
                new Class[]{IfoOrderUnitParams[].class}).asObject();
        }
        return super.removeCarryOverOriginalOrderUnit(l_orderUnitList);
    }
}
@

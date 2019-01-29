head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.41.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioOrderManagerForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderManagerPersistenceEventInterceptor;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AioOrderManagerForMock extends WEB3AioOrderManager
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOrderManagerForMock.class);

    public void validateOpenFuturesTradedAccount(SubAccount l_subAccount) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateOpenFuturesTradedAccount()";
        log.entering(STR_METHOD_NAME);
        
        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOpenFuturesTradedAccount",
                new Class[] {SubAccount.class},
                new Object[]{l_subAccount});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOpenFuturesTradedAccount",
                new Class[] {SubAccount.class})){
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager", 
                    "validateOpenFuturesTradedAccount",
                    new Class[] {SubAccount.class}).asWEB3BaseException();
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager", 
                    "validateOpenFuturesTradedAccount",
                    new Class[] {SubAccount.class}).asVoid();
            return;
        }
        super.validateOpenFuturesTradedAccount(l_subAccount);
    }
    /**
     * (validate新規注文)<BR>
     * （validateNewOrderのオーバーライド）<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_productType - (商品タイプ)
     * @@param l_cashTransOrderSpec - (入出金注文内容)
     * @@return NewOrderValidationResult
     */
    public NewOrderValidationResult validateNewOrder(
        SubAccount l_subAccount, 
        ProductTypeEnum l_productType, 
        NewOrderSpec l_newOrderSpec)
    {
        String STR_METHOD_NAME = "validateNewOrder(SubAccount l_subAccount," + 
            "ProductTypeEnum l_productType, NewOrderSpec l_newOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.aio.WEB3AioOrderManager",
            "validateNewOrder",
            new Class[] {SubAccount.class, ProductTypeEnum.class, NewOrderSpec.class},
            new Object[]{l_subAccount, l_productType, l_newOrderSpec});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.aio.WEB3AioOrderManager",
            "validateNewOrder",   new Class[] {SubAccount.class, ProductTypeEnum.class, NewOrderSpec.class}))
        {
            //3)MockFor --〉 asObject
            return (NewOrderValidationResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateNewOrder",
                new Class[] {SubAccount.class, ProductTypeEnum.class, NewOrderSpec.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.validateNewOrder(l_subAccount, l_productType, l_newOrderSpec);
    }
      
    public OrderSubmissionResult submitNewOrder(
        SubAccount subAccount,
        ProductTypeEnum productType,
        NewOrderSpec inSpec, long orderId, String tradingPassword, boolean skipOrderValidation)
    {
        String STR_METHOD_NAME = "submitNewOrder()";
        log.entering(STR_METHOD_NAME);
        
        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.aio.WEB3AioOrderManager",
            "submitNewOrder",
        new Class[] {SubAccount.class, ProductTypeEnum.class, NewOrderSpec.class, long.class, String.class, boolean.class},
        new Object[]{subAccount, productType, inSpec, new Long(orderId), tradingPassword,new Boolean(skipOrderValidation)});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.aio.WEB3AioOrderManager",
            "submitNewOrder",
            new Class[] {SubAccount.class, ProductTypeEnum.class, NewOrderSpec.class, long.class, String.class, boolean.class}))
        {
            //3)MockFor --〉 asObject
            return (OrderSubmissionResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitNewOrder",
                new Class[] {SubAccount.class, ProductTypeEnum.class, NewOrderSpec.class, long.class, String.class, boolean.class}).asObject();
        }
    
        log.exiting(STR_METHOD_NAME);
        return super.submitNewOrder(subAccount, productType, inSpec, orderId, tradingPassword,skipOrderValidation);
        
        }
    
    public void validateOrder(SubAccount l_subAccount) 
    throws WEB3BaseException
    {
        String STR_METHOD_NAME = "submitNewOrder()";
        log.entering(STR_METHOD_NAME);
        
        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                new Object[]{l_subAccount});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class})){
            log.debug("webbroker3.aio.WEB3AioOrderManagerForMock.validateOrder(SubAccount)");
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager", 
                    "validateOrder",
                    new Class[] {SubAccount.class}).asWEB3BaseException();
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager", 
                    "validateOrder",
                    new Class[] {SubAccount.class}).asVoid();
            return;
        }
        super.validateOrder(l_subAccount);
    }
    
    public long getProductId(Institution l_institution) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.aio.WEB3AioOrderManager", "getProductId",
                new Class[]
                { Institution.class }, new Object[]
                { l_institution });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.aio.WEB3AioOrderManager", "getProductId", new Class[]
        { Institution.class }))
        {
            log.debug("webbroker3.aio.WEB3AioOrderManagerForMock.getProductId()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.aio.WEB3AioOrderManager", "getProductId",
                    new Class[]
                    { Institution.class }).asWEB3BaseException();
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.aio.WEB3AioOrderManager",
                    "getProductId", new Class[]
                    { Institution.class }).asLong();
        }
        return super.getProductId(l_institution);
    }
    
    public void submitTransferOrder(SubAccount l_subAccount, ProductTypeEnum l_productTypeEnem,
            OrderTypeEnum l_orderTypeEnum, NewOrderSpec l_newOrderSpec,
            AioOrderManagerPersistenceEventInterceptor l_aioOrderManagerPersistenceEventInterceptor, long l_lngOrderId,
            String l_strPassword) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.aio.WEB3AioOrderManager", "submitTransferOrder",
                new Class[]
                { SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                        AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class }, new Object[]
                { l_subAccount, l_productTypeEnem, l_orderTypeEnum, l_newOrderSpec,
                        l_aioOrderManagerPersistenceEventInterceptor, new Long(l_lngOrderId), l_strPassword });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.aio.WEB3AioOrderManager", "submitTransferOrder",
                new Class[]
                { SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                        AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class }))
        {
            log.debug("webbroker3.aio.WEB3AioOrderManagerForMock.submitTransferOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager",
                    "submitTransferOrder",
                    new Class[]
                    { SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                            AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class })
                    .asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager",
                    "submitTransferOrder",
                    new Class[]
                    { SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                            AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class }).asVoid();
            return;
        }
        super.submitTransferOrder(l_subAccount, l_productTypeEnem, l_orderTypeEnum, l_newOrderSpec,
                l_aioOrderManagerPersistenceEventInterceptor, l_lngOrderId, l_strPassword);
    }
    
    public OrderUnit[] getOrderUnits(long l_lngOrderId)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.aio.WEB3AioOrderManager", "getOrderUnits",
                new Class[]
                { long.class }, new Object[]
                { new Long(l_lngOrderId) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.aio.WEB3AioOrderManager", "getOrderUnits", new Class[]
        { long.class }))
        {
            log.debug("webbroker3.aio.WEB3AioOrderManagerForMock.getOrderUnits()");
            return (OrderUnit[]) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager", "getOrderUnits", new Class[]
                    { long.class }).asObject();
        }
        return super.getOrderUnits(l_lngOrderId);
    }
    
    public long createNewOrderId()
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.aio.WEB3AioOrderManager", "createNewOrderId",
                new Class[]
                {}, new Object[]
                {});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.aio.WEB3AioOrderManager", "createNewOrderId",
                new Class[]
                {}))
        {
            log.debug("webbroker3.aio.WEB3AioOrderManagerForMock.createNewOrderId()");
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.aio.WEB3AioOrderManager",
                    "createNewOrderId", new Class[]
                    {}).asLong();
        }
        return super.createNewOrderId();
    }
    
    public OrderValidationResult validateCancelOrder(
        SubAccount l_subAccount, 
        CancelOrderSpec l_cancelOrderSpec)         
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.aio.WEB3AioOrderManager", "validateCancelOrder",
                new Class[]
                {SubAccount.class, CancelOrderSpec.class}, new Object[]
                {l_subAccount, l_cancelOrderSpec});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.aio.WEB3AioOrderManager", "validateCancelOrder",
                new Class[]
                {SubAccount.class, CancelOrderSpec.class}))
        {
            log.debug("webbroker3.aio.WEB3AioOrderManagerForMock.createNewOrderId()");
            return (OrderValidationResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.aio.WEB3AioOrderManager",
                    "validateCancelOrder", new Class[]
                    {SubAccount.class, CancelOrderSpec.class}).asObject();
        }
        
            return super.validateCancelOrder(l_subAccount, l_cancelOrderSpec);
    }

    public void validateOtherSystemAcceptPossible(String l_strSystemCode) 
        throws WEB3BaseException
    {

        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.aio.WEB3AioOrderManager",
            "validateOtherSystemAcceptPossible",
            new Class[]{String.class},
            new Object[]{l_strSystemCode});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.aio.WEB3AioOrderManager",
            "validateOtherSystemAcceptPossible",
            new Class[]
            {String.class}))
        {
            log.debug("webbroker3.aio.WEB3AioOrderManagerForMock.createNewOrderId()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOtherSystemAcceptPossible",
                new Class[]
                      {String.class}).asObject();
            return ;
        }
            super.validateOtherSystemAcceptPossible(l_strSystemCode);
    }
    

    public int validateTransferPossibleCount(
        SubAccount l_subAccount, Date l_datBizDate, OrderCategEnum l_orderCategEnum)
    throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.aio.WEB3AioOrderManager",
            "validateTransferPossibleCount",
            new Class[]{SubAccount.class, Date.class, OrderCategEnum.class},
            new Object[]{l_subAccount, l_datBizDate, l_orderCategEnum});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.aio.WEB3AioOrderManager",
            "validateTransferPossibleCount",
            new Class[]
            {SubAccount.class, Date.class, OrderCategEnum.class}))
        {
            log.debug("webbroker3.aio.WEB3AioOrderManagerForMock.createNewOrderId()");
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateTransferPossibleCount",
                new Class[]
                    {SubAccount.class, Date.class, OrderCategEnum.class}).asInt();
        }
        return super.validateTransferPossibleCount(l_subAccount, l_datBizDate, l_orderCategEnum);
    }
}
@

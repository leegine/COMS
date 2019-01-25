// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultOrderValidatorImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

public class DefaultOrderValidatorImpl
    implements OrderValidator
{

    public DefaultOrderValidatorImpl()
    {
    }

    public OrderValidationResult validateSubAccountForTrading(SubAccount subAccount)
    {
        return OrderValidationResult.VALIDATION_OK_RESULT;
    }

    public OrderValidationResult validateTradedProductForTrading(SubAccount subAccount, OrderTypeEnum orderType, TradedProduct tradedProduct)
    {
        if(tradedProduct.isTradingSuspended())
            return new OrderValidationResult(ProcessingResult.newFailedResultInstance(OrderManagerErrorCatalog.TRADING_TEMPORARILY_SUSPENDED));
        else
            return OrderValidationResult.VALIDATION_OK_RESULT;
    }

    public OrderValidationResult validateMarket(OrderTypeEnum orderType, Market market)
    {
        if(OrderTypeEnum.SWAP_MARGIN_LONG.equals(orderType) || OrderTypeEnum.SWAP_MARGIN_SHORT.equals(orderType))
            return OrderValidationResult.VALIDATION_OK_RESULT;
        if(!market.isAcceptingOrders())
            return new OrderValidationResult(ProcessingResult.newFailedResultInstance(OrderManagerErrorCatalog.NOT_ACCEPTING_ORDERS_FOR_MARKET));
        else
            return OrderValidationResult.VALIDATION_OK_RESULT;
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static final Logit m_log;
    private static final boolean DBG;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.DefaultOrderValidatorImpl.class);
        DBG = m_log.ison();
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultGlobalBizLogicProviderImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

public class DefaultGlobalBizLogicProviderImpl
    implements GlobalBizLogicProvider
{

    public DefaultGlobalBizLogicProviderImpl()
    {
    }

    public double calcSalesTax(double amount)
    {
        return Math.floor(0.050000000000000003D * amount);
    }

    public OrderValidationResult checkTradingPower(SubAccount sub_account, OrderSpec order_spec)
    {
        return OrderValidationResult.VALIDATION_OK_RESULT;
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OrderValidator.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            SubAccount, OrderTypeEnum, TradedProduct, Market

public interface OrderValidator
{

    public abstract OrderValidationResult validateSubAccountForTrading(SubAccount subaccount);

    public abstract OrderValidationResult validateTradedProductForTrading(SubAccount subaccount, OrderTypeEnum ordertypeenum, TradedProduct tradedproduct);

    public abstract OrderValidationResult validateMarket(OrderTypeEnum ordertypeenum, Market market);
}

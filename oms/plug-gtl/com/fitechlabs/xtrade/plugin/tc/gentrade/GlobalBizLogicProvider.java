// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GlobalBizLogicProvider.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            SubAccount, OrderSpec

public interface GlobalBizLogicProvider
{

    public abstract double calcSalesTax(double d);

    public abstract OrderValidationResult checkTradingPower(SubAccount subaccount, OrderSpec orderspec);
}

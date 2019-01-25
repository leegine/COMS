// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeMarketRequestMarketTradedOrderUnitSpec.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages:
//            EqTypeMarketRequestBaseOrderUnitSpec

public interface EqTypeMarketRequestMarketTradedOrderUnitSpec
    extends EqTypeMarketRequestBaseOrderUnitSpec
{

    public abstract double getLimitPrice();

    public abstract boolean isMarketOrder();

    public abstract Date getExpirationDate();

    public abstract EqTypeExecutionConditionType getExecConditionType();
}

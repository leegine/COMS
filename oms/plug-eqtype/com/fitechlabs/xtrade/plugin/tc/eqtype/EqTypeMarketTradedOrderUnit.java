// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeMarketTradedOrderUnit.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype:
//            EqTypeOrderUnit, EqTypeExecutionConditionType

public interface EqTypeMarketTradedOrderUnit
    extends EqTypeOrderUnit
{

    public abstract ProductTypeEnum getProductType();

    public abstract EqTypeExecutionConditionType getExecutionConditionType();
}

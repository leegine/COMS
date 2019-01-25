// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeOrderAction.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype:
//            EqTypeExecutionConditionType

public interface EqTypeOrderAction
    extends OrderAction
{

    public abstract long getProductId();

    public abstract long getMarketId();

    public abstract EqTypeExecutionConditionType getExecutionConditionType();
}

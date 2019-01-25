// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeChangeSwapContractOrderMarketRequestMessage.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages;

import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeContractSwapChangeOrderUnitEntry;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages:
//            EqTypeOrderBaseMarketRequestMessage

public interface EqTypeChangeSwapContractOrderMarketRequestMessage
    extends EqTypeOrderBaseMarketRequestMessage
{

    public abstract long getOrderId();

    public abstract EqTypeContractSwapChangeOrderUnitEntry[] getChangeOrderUnitEntries();
}

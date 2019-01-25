// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MarketResponseReceiverCallbackService.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketResponseMessage;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            ProcessingResult

public interface MarketResponseReceiverCallbackService
{

    public abstract ProcessingResult process(MarketResponseMessage marketresponsemessage);
}

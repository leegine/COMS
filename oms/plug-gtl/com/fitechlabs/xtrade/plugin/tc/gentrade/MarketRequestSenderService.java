// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MarketRequestSenderService.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketRequestMessage;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            MarketRequestSendResult

public interface MarketRequestSenderService
{

    public abstract MarketRequestSendResult send(MarketRequestMessage marketrequestmessage);
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MarketRequestMessage.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

public interface MarketRequestMessage
{

    public abstract SubAccount getSubAccount();

    public abstract long getOrderId();
}

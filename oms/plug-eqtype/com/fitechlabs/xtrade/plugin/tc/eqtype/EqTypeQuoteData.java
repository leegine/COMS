// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeQuoteData.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;

import com.fitechlabs.xtrade.plugin.tc.gentrade.QuoteData;

public interface EqTypeQuoteData
    extends QuoteData
{

    public abstract double getCurrentPrice();

    public abstract double getBidPrice();

    public abstract double getAskPrice();

    public abstract double getOpenPrice();
}

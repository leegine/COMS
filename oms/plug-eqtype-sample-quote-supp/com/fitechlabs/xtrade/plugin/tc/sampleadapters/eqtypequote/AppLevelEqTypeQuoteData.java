// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AppLevelEqTypeQuoteData.java

package com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeQuoteData;

public interface AppLevelEqTypeQuoteData
    extends EqTypeQuoteData
{

    public abstract double getVolume();

    public abstract double getBidSize();

    public abstract double getAskSize();

    public abstract double getYtdHighPrice();

    public abstract double getYtdLowPrice();

    public abstract double getLastClosingPrice();

    public abstract double getPrevTradingDayClosingPrice();

    public abstract double getDiff();
}

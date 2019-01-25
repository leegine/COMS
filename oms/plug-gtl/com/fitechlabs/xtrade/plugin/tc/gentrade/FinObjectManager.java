// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FinObjectManager.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            NotFoundException, Institution, Market, Trader, 
//            TradingCalendar, MarketCalendar, TradingCalendarModel

public interface FinObjectManager
{

    public abstract Market getMarket(Institution institution, String s)
        throws NotFoundException;

    public abstract Market getMarket(long l)
        throws NotFoundException;

    public abstract Market[] getMarkets();

    public abstract Market[] getMarkets(Institution institution);

    public abstract Trader getTraderByLoginId(long l)
        throws NotFoundException;

    public abstract Trader getTrader(long l)
        throws NotFoundException;

    public abstract Trader getTrader(Institution institution, String s, String s1)
        throws NotFoundException;

    public abstract TradingCalendar getTradingCalendar(long l);

    public abstract MarketCalendar getMarketCalendar(Market market);

    public abstract TradingCalendarModel getTradingCalendarModel();

    public abstract void overrideTradingCalendarModel(TradingCalendarModel tradingcalendarmodel);
}

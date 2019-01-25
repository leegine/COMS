// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MarketCalendar.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            TradingCalendar

public interface MarketCalendar
    extends TradingCalendar
{

    public abstract String getRecessStartTime();

    public abstract String getRecessEndTime();

    public static final String PREF_NAME_RECESS_START_TIME = "recess.start.time";
    public static final String PREF_NAME_RECESS_END_TIME = "recess.end.time";
}

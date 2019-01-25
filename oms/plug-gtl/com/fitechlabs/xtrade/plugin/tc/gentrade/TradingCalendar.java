// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TradingCalendar.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import java.util.Date;

public interface TradingCalendar
{

    public abstract Date getCurrentBizDate();

    public abstract Date getNextBizDate();

    public abstract Date getPrevBizDate();

    public abstract Date roll(Date date, int i);

    public abstract boolean isHoliday(Date date);

    public abstract Date[] getHolidays();

    public abstract String getTradeOpenTime();

    public abstract String getTradeCloseTime();
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TradingCalendarDetails.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import java.util.Date;

public interface TradingCalendarDetails
{

    public abstract Date[] getHolidays();

    public abstract String getTradeOpenTime();

    public abstract String getTradeCloseTime();
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TradingSystem.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            Institution, Market

public interface TradingSystem
{

    public abstract Timestamp getSystemTimestamp();

    public abstract Date getBizDate();

    public abstract boolean isSystemAcceptingOrders();

    public abstract String getSystemWideBroadcastMessages();

    public abstract String getPreference(String s);

    public abstract Map getPreferences();

    public abstract Market getMarketForSystemCalendar(Institution institution);

    public static final String PREF_NAME_SHIFT_SYSTEM_TIMESTAMP = "shift.system.timestamp";
    public static final String PREF_NAME_SHIFT_SYSTEM_TIMESTAMP_MILLISECS = "shift.system.timestamp.millisecs";
    public static final String PREF_NAME_BIZ_DATE = "system.bizdate";
    public static final String PREF_NAME_SYSTEM_ACCEPTING_ORDERS = "system.accepting.orders";
    public static final String PREF_NAME_SYSTEM_BROADCAST_MESSAGE = "system.broadcast.message";
    public static final String SYSTEM_TIMESTAMP_ATTRIBUTE_NAME = "xblocks.gtl.attributes.systemtimestamp";
    public static final String PREF_NAME_MARKET_CODE_FOR_SYSTEM_CALENDAR = "system.calendar.market.code";
}

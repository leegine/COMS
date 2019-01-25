// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Market.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            BusinessObject, MarketCalendar, Preferences, ProductTypeEnum, 
//            Institution

public interface Market
    extends BusinessObject
{

    public abstract long getMarketId();

    public abstract String getMarketCode();

    public abstract String getMarketName();

    public abstract String getMarketNameAlt1();

    public abstract String getMarketNameAlt2();

    /**
     * @deprecated Method getMarketCalendar is deprecated
     */

    public abstract MarketCalendar getMarketCalendar();

    public abstract boolean acceptsLimitOrder();

    public abstract boolean acceptsMarketOrder();

    public abstract Preferences getPreferences();

    public abstract boolean isAcceptingOrders();

    public abstract double getMaxNumberOfLotsPerOrder(ProductTypeEnum producttypeenum);

    public abstract Institution getInstitution();

    public static final String ACCEPTS_LIMIT_ORDER_PREF_NAME = "accepts_limit_order";
    public static final String ACCEPTS_MARKET_ORDER_PREF_NAME = "accepts_market_order";
    public static final String IS_ACCEPTING_ORDERS_PREF_NAME = "is_accepting_orders";
    public static final String PER_ORDER_MAX_NO_OF_LOTS_PREF_NAME_SUFFIX = "per_order_max_lots";
}

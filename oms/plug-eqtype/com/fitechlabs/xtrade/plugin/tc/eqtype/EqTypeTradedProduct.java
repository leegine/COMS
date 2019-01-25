// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeTradedProduct.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;

import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import java.util.Date;

public interface EqTypeTradedProduct
    extends TradedProduct
{

    public abstract double getLastClosingPrice();

    /**
     * @deprecated Method getDailyStopHigh is deprecated
     */

    public abstract double getDailyStopHigh();

    /**
     * @deprecated Method getDailyStopLow is deprecated
     */

    public abstract double getDailyStopLow();

    public abstract double getStopHighPrice();

    public abstract double getStopLowPrice();

    public abstract boolean isMarginable();

    public abstract boolean isShortable();

    /**
     * @deprecated Method getLastUpdatedTimestamp is deprecated
     */

    public abstract Date getLastUpdatedTimestamp();

    /**
     * @deprecated Method getTickValueUnit is deprecated
     */

    public abstract double getTickValueUnit();

    public abstract double getTickValueUnit(double d);

    public abstract boolean isValidPriceAsPerTickValue(double d);

    public abstract Date getListedDate();

    public abstract Date getUnlistedDate();

    public abstract boolean isListedCurrently();

    public abstract boolean isMiniStockTradable();
}

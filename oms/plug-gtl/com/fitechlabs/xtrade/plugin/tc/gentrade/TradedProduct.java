// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TradedProduct.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            BusinessObject, Product, Market, Institution

public interface TradedProduct
    extends BusinessObject
{

    public abstract long getTradedProductId();

    public abstract Product getProduct();

    public abstract Market getMarket();

    public abstract boolean isTradingSuspended();

    public abstract double getMarginRatio();

    public abstract Date getBaseDate();

    public abstract Date getDailyDeliveryDate();

    public abstract boolean isCollateralizable();

    public abstract Institution getInstitution();
}

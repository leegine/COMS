// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Product.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            BusinessObject, Market, TradedProduct, ProductTypeEnum, 
//            Institution

public interface Product
    extends BusinessObject
{

    public abstract long getProductId();

    public abstract Market[] getTradedMarkets();

    public abstract Market getPrimaryMarket();

    public abstract TradedProduct getPrimaryTradedProduct();

    public abstract ProductTypeEnum getProductType();

    public abstract boolean isTradedOnMarket(Market market);

    public abstract double getLotSize();

    public abstract String getStandardName();

    public abstract String getNameAlt1();

    public abstract String getNameAlt2();

    public abstract double getMarginRatio();

    public abstract Institution getInstitution();
}

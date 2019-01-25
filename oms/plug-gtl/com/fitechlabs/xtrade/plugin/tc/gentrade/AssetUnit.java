// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AssetUnit.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            BusinessObject, TradedProduct, ProductTypeEnum

public interface AssetUnit
    extends BusinessObject
{

    public abstract long getAccountId();

    public abstract double getAcquiredPrice();

    public abstract Date getAcquiredTimestamp();

    public abstract long getAssetId();

    public abstract long getAssetUnitId();

    public abstract Date getDeliveryDate();

    public abstract double getOrignalQuantity();

    public abstract double getQuantity();

    public abstract long getSubAccountId();

    public abstract TradedProduct getTradedProduct();

    public abstract ProductTypeEnum getProductType();
}

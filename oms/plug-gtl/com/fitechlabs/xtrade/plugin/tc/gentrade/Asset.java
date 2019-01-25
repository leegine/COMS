// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Asset.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            Position, TaxTypeEnum, AssetUnit

public interface Asset
    extends Position
{

    public abstract long getAssetId();

    public abstract double getAveragePrice();

    public abstract double getBookValue();

    public abstract double getManagementFee();

    public abstract double getManagementFeeTax();

    public abstract double getSetupFee();

    public abstract double getSetupFeeTax();

    public abstract double getLockedQuantity();

    public abstract TaxTypeEnum getTaxType();

    public abstract AssetUnit[] getAssetUnits();
}

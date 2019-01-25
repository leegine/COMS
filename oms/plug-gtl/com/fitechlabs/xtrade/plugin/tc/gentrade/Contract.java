// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Contract.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            Position, TradedProduct

public interface Contract
    extends Position
{

    public abstract Date getCloseDate();

    public abstract long getContractId();

    public abstract double getOriginalContractPrice();

    public abstract double getContractPrice();

    public abstract double getInterestFee();

    public abstract double getInterestFeeTax();

    public abstract boolean isLong();

    public abstract boolean isShort();

    public abstract double getManagementFee();

    public abstract double getManagementFeeTax();

    public abstract long getMarketId();

    public abstract Date getOpenDate();

    public abstract double getOriginalQuantity();

    public abstract double getSetupFee();

    public abstract double getSetupFeeTax();

    public abstract double getLockedQuantity();

    public abstract TradedProduct getTradedProduct();
}

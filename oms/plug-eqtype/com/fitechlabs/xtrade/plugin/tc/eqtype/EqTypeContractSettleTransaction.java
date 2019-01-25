// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeContractSettleTransaction.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype:
//            EqTypeMarketTradedOrderTransaction

public interface EqTypeContractSettleTransaction
    extends EqTypeMarketTradedOrderTransaction
{

    public abstract long getContractId();

    public abstract double getCapitalGain();

    public abstract double getCapitalGainTax();

    public abstract double getImportedInterestFee();

    public abstract double getImportedInterestFeeTax();

    public abstract double getImportedManagementFee();

    public abstract double getImportedManagementFeeTax();

    public abstract double getImportedPaidValue();

    public abstract double getImportedSetupFee();

    public abstract double getImportedSetupFeeTax();
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeContractSwapTransaction.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype:
//            EqTypeMarketTradedOrderTransaction

public interface EqTypeContractSwapTransaction
    extends EqTypeMarketTradedOrderTransaction
{

    public abstract long getAssetId();

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

    public abstract double getTransferedAssetBookValue();

    public abstract double getTransferedAssetMngFee();

    public abstract double getTransferedAssetMngFeeTax();

    public abstract double getTransferedAssetSetupFee();

    public abstract double getTransferedAssetSetupFeeTax();
}

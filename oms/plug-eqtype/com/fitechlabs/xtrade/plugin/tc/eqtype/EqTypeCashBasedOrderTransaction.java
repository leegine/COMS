// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeCashBasedOrderTransaction.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype:
//            EqTypeMarketTradedOrderTransaction

public interface EqTypeCashBasedOrderTransaction
    extends EqTypeMarketTradedOrderTransaction
{

    public abstract long getAssetId();

    public abstract double getCapitalGain();

    public abstract double getCapitalGainTax();

    /**
     * @deprecated Method getImportedPaidValue is deprecated
     */

    public abstract double getImportedPaidValue();

    /**
     * @deprecated Method getImportedSetupFee is deprecated
     */

    public abstract double getImportedSetupFee();

    /**
     * @deprecated Method getImportedSetupFeeTax is deprecated
     */

    public abstract double getImportedSetupFeeTax();

    public abstract double getTransferedAssetBookValue();

    public abstract double getTransferedAssetMngFee();

    public abstract double getTransferedAssetMngFeeTax();

    public abstract double getTransferedAssetSetupFee();

    public abstract double getTransferedAssetSetupFeeTax();
}

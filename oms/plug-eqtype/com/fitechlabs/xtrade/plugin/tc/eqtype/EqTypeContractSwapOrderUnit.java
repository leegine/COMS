// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeContractSwapOrderUnit.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype:
//            EqTypeMarketTradedOrderUnit, EqTypeClosingContractSpec

public interface EqTypeContractSwapOrderUnit
    extends EqTypeMarketTradedOrderUnit
{

    public abstract EqTypeClosingContractSpec[] getContractsToClose();
}

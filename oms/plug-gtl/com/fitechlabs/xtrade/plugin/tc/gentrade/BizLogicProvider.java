// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BizLogicProvider.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            OrderExecution, SubAccount, TaxTypeEnum, ConsolidatedCommissionInfo, 
//            ProductTypeEnum, QuantityTypeEnum

public interface BizLogicProvider
{

    public abstract double calcCommission(OrderExecution orderexecution);

    public abstract double calcCapitalGainTax(SubAccount subaccount, TaxTypeEnum taxtypeenum, double d);

    public abstract ConsolidatedCommissionInfo calcCommission(Object aobj[]);

    public abstract double calcExecutionAmount(ProductTypeEnum producttypeenum, long l, double d, double d1, 
            QuantityTypeEnum quantitytypeenum);
}

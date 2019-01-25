// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeMarketTradedOrderTransaction.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype:
//            EqTypeFinTransaction

public interface EqTypeMarketTradedOrderTransaction
    extends EqTypeFinTransaction
{

    public abstract double getCommissionFee();

    public abstract double getCommissionFeeTax();

    public abstract long getMarketId();

    public abstract long getOrderExecutionId();

    public abstract long getOrderId();

    public abstract long getOrderUnitId();

    public abstract double getPrice();

    public abstract long getProductId();

    public abstract ProductTypeEnum getProductType();

    public abstract double getQuantity();

    public abstract TaxTypeEnum getTaxType();
}

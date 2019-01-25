// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Order.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            BusinessObject, OrderUnit, SubAccount, ProductTypeEnum

public interface Order
    extends BusinessObject
{

    public abstract long getOrderId();

    public abstract long getAccountId();

    public abstract long getSubAccountId();

    public abstract OrderUnit[] getOrderUnits();

    public abstract SubAccount getSubAccount();

    public abstract ProductTypeEnum getProductType();
}

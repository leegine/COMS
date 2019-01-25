// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Position.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            BusinessObject, Product, ProductTypeEnum, SubAccount

public interface Position
    extends BusinessObject
{

    public abstract Product getProduct();

    public abstract ProductTypeEnum getProductType();

    public abstract double getQuantity();

    public abstract SubAccount getSubAccount();
}

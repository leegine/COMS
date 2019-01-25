// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeProduct.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import java.util.Date;

public interface EqTypeProduct
    extends Product
{

    public abstract String getProductCode();

    public abstract Date getYearlyBooksClosingDate();

    public abstract double getMiniStockLotSize();
}

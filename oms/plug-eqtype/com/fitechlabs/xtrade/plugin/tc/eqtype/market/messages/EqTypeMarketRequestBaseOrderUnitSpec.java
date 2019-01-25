// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeMarketRequestBaseOrderUnitSpec.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;

public interface EqTypeMarketRequestBaseOrderUnitSpec
{

    public abstract long getOrderUnitId();

    public abstract double getQuantity();

    public abstract EqTypeTradedProduct getTradedProduct();

    public abstract Object getDataSourceObject();
}

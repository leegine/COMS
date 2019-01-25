// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FillOrderUnitSpec.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages;


public interface FillOrderUnitSpec
{

    public abstract long getOrderUnitId();

    public abstract double getFillQuantity();

    public abstract double getFillPrice();
}

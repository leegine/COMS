// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CancelOrderSpec.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io;


public class CancelOrderSpec
{

    public CancelOrderSpec(long orderId)
    {
        m_orderId = orderId;
    }

    public long getOrderId()
    {
        return m_orderId;
    }

    private final long m_orderId;
}

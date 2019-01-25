// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeChangeOrderUnitEntry.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

public class EqTypeChangeOrderUnitEntry
    implements Comparable
{

    public EqTypeChangeOrderUnitEntry(long orderUnitId, double afterChangeOrigQuantity, double afterChangePrice)
    {
        m_orderUnitId = orderUnitId;
        m_quantity = afterChangeOrigQuantity;
        m_price = afterChangePrice;
    }

    public long getOrderUnitId()
    {
        return m_orderUnitId;
    }

    public double getAfterChangeOriginalQuantity()
    {
        return m_quantity;
    }

    public double getAfterChangePrice()
    {
        return m_price;
    }

    public boolean isAfterChangePriceMarket()
    {
        return com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(getAfterChangePrice());
    }

    public String toString()
    {
        return "orderUnitId=" + getOrderUnitId() + ", after change quantity=" + getAfterChangeOriginalQuantity() + ", after change price=" + getAfterChangePrice();
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj instanceof EqTypeChangeOrderUnitEntry)
        {
            EqTypeChangeOrderUnitEntry entry = (EqTypeChangeOrderUnitEntry)obj;
            return entry.getOrderUnitId() == getOrderUnitId();
        } else
        {
            return false;
        }
    }

    public int hashCode()
    {
        return (int)getOrderUnitId();
    }

    public int compareTo(Object o)
    {
        if(o instanceof EqTypeChangeOrderUnitEntry)
        {
            EqTypeChangeOrderUnitEntry entry = (EqTypeChangeOrderUnitEntry)o;
            long thisId = getOrderUnitId();
            long anotherId = entry.getOrderUnitId();
            return thisId >= anotherId ? ((int) (thisId != anotherId ? 1 : 0)) : -1;
        } else
        {
            throw new IllegalArgumentException("invalid object. is not of type " + (com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderUnitEntry.class));
        }
    }

    private final long m_orderUnitId;
    private final double m_price;
    private final double m_quantity;
}

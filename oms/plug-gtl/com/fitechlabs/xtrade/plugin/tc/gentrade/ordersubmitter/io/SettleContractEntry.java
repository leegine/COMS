// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SettleContractEntry.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io;


public class SettleContractEntry
    implements Comparable
{

    public SettleContractEntry(long contractId, double quantity)
    {
        m_contractId = contractId;
        m_quantity = quantity;
    }

    public long getContractId()
    {
        return m_contractId;
    }

    public double getQuantity()
    {
        return m_quantity;
    }

    public String toString()
    {
        return "{contractId,qty}= {" + getContractId() + ", " + getQuantity() + "}";
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj instanceof SettleContractEntry)
        {
            SettleContractEntry entry = (SettleContractEntry)obj;
            return entry.getContractId() == getContractId();
        } else
        {
            return false;
        }
    }

    public int hashCode()
    {
        return (int)getContractId();
    }

    public int compareTo(Object o)
    {
        if(o instanceof SettleContractEntry)
        {
            SettleContractEntry entry = (SettleContractEntry)o;
            long thisId = getContractId();
            long anotherId = entry.getContractId();
            return thisId >= anotherId ? ((int) (thisId != anotherId ? 1 : 0)) : -1;
        } else
        {
            throw new IllegalArgumentException("invalid object. is not of type " + (com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry.class));
        }
    }

    private final long m_contractId;
    private final double m_quantity;
}

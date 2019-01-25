// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeContractSwapChangeOrderUnitEntry.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io:
//            EqTypeSettleContractOrderEntry

public class EqTypeContractSwapChangeOrderUnitEntry
    implements Comparable
{

    public EqTypeContractSwapChangeOrderUnitEntry(long orderUnitId, EqTypeSettleContractOrderEntry newContractEntries[])
    {
        this(orderUnitId, newContractEntries, (0.0D / 0.0D));
    }

    public EqTypeContractSwapChangeOrderUnitEntry(long orderUnitId, double afterChangeTotalQuantity)
    {
        this(orderUnitId, null, afterChangeTotalQuantity);
    }

    private EqTypeContractSwapChangeOrderUnitEntry(long orderUnitId, EqTypeSettleContractOrderEntry newContractEntries[], double afterChangeTotalQuantity)
    {
        m_orderUnitId = orderUnitId;
        m_isContractsSpecified = newContractEntries != null;
        m_newContractEntries = newContractEntries != null ? newContractEntries : NULL_SETTLE_CONTRACT_ORDER_ENTRY_ARRAY;
        double qty = 0.0D;
        if(Double.isNaN(afterChangeTotalQuantity))
        {
            for(int i = 0; i < m_newContractEntries.length; i++)
                qty += m_newContractEntries[i].getQuantity();

        } else
        {
            qty = afterChangeTotalQuantity;
        }
        m_afterChangeTotalQuantity = qty;
    }

    public long getOrderUnitId()
    {
        return m_orderUnitId;
    }

    public double getAfterChangeTotalQuantity()
    {
        return m_afterChangeTotalQuantity;
    }

    public boolean isContractsSpecified()
    {
        return m_isContractsSpecified;
    }

    public EqTypeSettleContractOrderEntry[] getAfterChangeSettleContractOrderEntries()
    {
        return m_newContractEntries;
    }

    public String toString()
    {
        return "orderUnitId=" + getOrderUnitId();
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj instanceof EqTypeContractSwapChangeOrderUnitEntry)
        {
            EqTypeContractSwapChangeOrderUnitEntry entry = (EqTypeContractSwapChangeOrderUnitEntry)obj;
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
        if(o instanceof EqTypeContractSwapChangeOrderUnitEntry)
        {
            EqTypeContractSwapChangeOrderUnitEntry entry = (EqTypeContractSwapChangeOrderUnitEntry)o;
            long thisId = getOrderUnitId();
            long anotherId = entry.getOrderUnitId();
            return thisId >= anotherId ? ((int) (thisId != anotherId ? 1 : 0)) : -1;
        } else
        {
            throw new IllegalArgumentException("invalid object. is not of type " + (com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeContractSwapChangeOrderUnitEntry.class));
        }
    }

    private final long m_orderUnitId;
    private final double m_afterChangeTotalQuantity;
    private final boolean m_isContractsSpecified;
    private final EqTypeSettleContractOrderEntry m_newContractEntries[];
    private static final EqTypeSettleContractOrderEntry NULL_SETTLE_CONTRACT_ORDER_ENTRY_ARRAY[] = new EqTypeSettleContractOrderEntry[0];

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeContractSettleChangeOrderUnitEntry.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io:
//            EqTypeSettleContractOrderEntry

public class EqTypeContractSettleChangeOrderUnitEntry
    implements Comparable
{

    public EqTypeContractSettleChangeOrderUnitEntry(long orderUnitId, double afterChangePrice, EqTypeSettleContractOrderEntry newContractEntries[])
    {
        this(orderUnitId, newContractEntries, afterChangePrice, (0.0D / 0.0D));
    }

    public EqTypeContractSettleChangeOrderUnitEntry(long orderUnitId, double afterChangePrice, double afterChangeTotalQuantity)
    {
        this(orderUnitId, null, afterChangePrice, afterChangeTotalQuantity);
    }

    private EqTypeContractSettleChangeOrderUnitEntry(long orderUnitId, EqTypeSettleContractOrderEntry newContractEntries[], double afterChangePrice, double afterChangeTotalQuantity)
    {
        m_orderUnitId = orderUnitId;
        m_afterChangePrice = afterChangePrice;
        m_isContractsSpecified = newContractEntries != null;
        m_newContractEntries = newContractEntries != null ? newContractEntries : NULL_SETTLE_CONTRACT_ORDER_ENTRY_ARRAY;
        double qty = 0.0D;
        if(java.lang.Double.isNaN(afterChangeTotalQuantity))
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

    public double getAfterChangePrice()
    {
        return m_afterChangePrice;
    }

    public boolean isAfterChangePriceMarket()
    {
        return com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(getAfterChangePrice());
    }

    public String toString()
    {
        return "orderUnitId=" + getOrderUnitId() + ", after change price=" + getAfterChangePrice();
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj instanceof EqTypeContractSettleChangeOrderUnitEntry)
        {
            EqTypeContractSettleChangeOrderUnitEntry entry = (EqTypeContractSettleChangeOrderUnitEntry)obj;
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
        if(o instanceof EqTypeContractSettleChangeOrderUnitEntry)
        {
            EqTypeContractSettleChangeOrderUnitEntry entry = (EqTypeContractSettleChangeOrderUnitEntry)o;
            long thisId = getOrderUnitId();
            long anotherId = entry.getOrderUnitId();
            return thisId >= anotherId ? ((int) (thisId != anotherId ? 1 : 0)) : -1;
        } else
        {
            throw new IllegalArgumentException("invalid object. is not of type " + (com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeContractSettleChangeOrderUnitEntry.class));
        }
    }

    private final long m_orderUnitId;
    private final double m_afterChangePrice;
    private final double m_afterChangeTotalQuantity;
    private final boolean m_isContractsSpecified;
    private final EqTypeSettleContractOrderEntry m_newContractEntries[];
    private static final EqTypeSettleContractOrderEntry NULL_SETTLE_CONTRACT_ORDER_ENTRY_ARRAY[] = new EqTypeSettleContractOrderEntry[0];

}

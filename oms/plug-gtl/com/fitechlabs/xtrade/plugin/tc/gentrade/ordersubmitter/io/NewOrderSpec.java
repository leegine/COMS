// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NewOrderSpec.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import java.util.Date;

public class NewOrderSpec
{

    protected NewOrderSpec(Trader trader, Date orderExpDate)
    {
        m_trader = trader;
        m_traderId = trader != null ? new Long(trader.getTraderId()) : null;
        m_orderExpDate = orderExpDate;
    }

    public Trader getTrader()
    {
        return m_trader;
    }

    public Long getTraderIdAsObject()
    {
        return m_traderId;
    }

    public Date getOrderExpDate()
    {
        return m_orderExpDate;
    }

    public static final Date MAX_ORDER_EXP_DATE = new Date(400, 11, 31);
    private final Trader m_trader;
    private final Long m_traderId;
    private final Date m_orderExpDate;

}

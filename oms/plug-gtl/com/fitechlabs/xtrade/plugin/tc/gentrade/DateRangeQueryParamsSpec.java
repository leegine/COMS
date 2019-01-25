// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DateRangeQueryParamsSpec.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import java.util.Date;

public class DateRangeQueryParamsSpec
{

    public DateRangeQueryParamsSpec(Date from, Date to)
        throws IllegalArgumentException
    {
        if(from == null)
            throw new IllegalArgumentException("Invalid from date. from date cant be null");
        if(to == null)
            throw new IllegalArgumentException("Invalid to date. to date cant be null");
        if(from.after(to))
        {
            throw new IllegalArgumentException("From date should be less than or equal to to_date.");
        } else
        {
            m_from = (Date)from.clone();
            m_to = (Date)to.clone();
            return;
        }
    }

    public Date getFromDate()
    {
        return m_from;
    }

    public Date getToDate()
    {
        return m_to;
    }

    public String toString()
    {
        return "from date: " + getFromDate().toString() + "; to date : " + getToDate().toString();
    }

    public static final DateRangeQueryParamsSpec ALL_DATE_RANGES = new DateRangeQueryParamsSpec(new Date(0L), new Date(8099, 11, 31));
    private Date m_from;
    private Date m_to;

}

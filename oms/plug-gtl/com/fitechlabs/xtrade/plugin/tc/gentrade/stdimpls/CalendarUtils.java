// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CalendarUtils.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import java.text.DateFormat;
import java.util.*;

public class CalendarUtils
{

    public CalendarUtils()
    {
    }

    public static String toYYYYMMDDString(Date in)
    {
        Date truncatedInDate = clearTimeFields(in);
        Object yyyymmdd = m_cachedYYYYMMDDValues.get(clearTimeFields(truncatedInDate));
        if(yyyymmdd == null)
        {
            DateFormat yyyymmddFormatter = GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat();
            yyyymmdd = yyyymmddFormatter.format(truncatedInDate);
            m_cachedYYYYMMDDValues.put(truncatedInDate, yyyymmdd);
        }
        return (String)yyyymmdd;
    }

    public static Date clearTimeFields(Date in)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(in);
        cal.clear(11);
        cal.clear(10);
        cal.clear(12);
        cal.clear(13);
        cal.clear(14);
        return cal.getTime();
    }

    public static boolean isWeekend(Date in)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(in);
        int dayOfWeek = cal.get(7);
        return dayOfWeek == 7 || dayOfWeek == 1;
    }

    public static Date getPrevCalendarDay(Date base)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(base);
        cal.add(5, -1);
        return cal.getTime();
    }

    public static Date getNextCalendarDay(Date base)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(base);
        cal.add(5, 1);
        return cal.getTime();
    }

    public static Date roll(Date base, int n)
    {
        if(n == 0)
            return base;
        Calendar cal = Calendar.getInstance();
        cal.setTime(clearTimeFields(base));
        int upFlag = n <= 0 ? -1 : 1;
        int count = Math.abs(n);
        do
        {
            if(count <= 0)
                break;
            cal.add(5, upFlag);
            if(!isWeekend(cal.getTime()))
                count--;
        } while(true);
        return cal.getTime();
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static final Logit m_log;
    private static final boolean DBG;
    private static final Map m_cachedYYYYMMDDValues = Collections.synchronizedMap(new HashMap());

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.CalendarUtils.class);
        DBG = m_log.ison();
    }
}

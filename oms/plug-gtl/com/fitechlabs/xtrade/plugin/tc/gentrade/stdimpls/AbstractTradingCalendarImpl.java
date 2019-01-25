// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractTradingCalendarImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendar;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendarDetails;
import java.util.Calendar;
import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls:
//            CalendarUtils

public abstract class AbstractTradingCalendarImpl
    implements TradingCalendar
{

    public AbstractTradingCalendarImpl()
    {
    }

    public abstract Date getCurrentBizDate();

    public Date getNextBizDate()
    {
        return roll(getCurrentBizDate(), 1);
    }

    public Date getPrevBizDate()
    {
        return roll(getCurrentBizDate(), -1);
    }

    public Date roll(Date base, int n)
    {
        if(n == 0)
            return base;
        Calendar cal = Calendar.getInstance();
        cal.setTime(CalendarUtils.clearTimeFields(base));
        int upFlag = n <= 0 ? -1 : 1;
        int count = Math.abs(n);
        Date holidays[] = getHolidays();
        do
        {
            if(count <= 0)
                break;
            cal.add(5, upFlag);
            if(!isHoliday(cal.getTime(), true, true, holidays))
                count--;
        } while(true);
        return cal.getTime();
    }

    private boolean isHoliday(Date in, boolean isInDateTimeFieldsCleared, boolean checkForWeekend, Date holidays[])
    {
        if(checkForWeekend && CalendarUtils.isWeekend(in))
            return true;
        Date truncatedInDate = isInDateTimeFieldsCleared ? in : CalendarUtils.clearTimeFields(in);
        int i = 0;
        do
        {
            if(i >= holidays.length)
                break;
            Date d = holidays[i];
            int diff = d.compareTo(truncatedInDate);
            if(diff == 0)
                return true;
            if(diff > 0)
                break;
            i++;
        } while(true);
        return false;
    }

    public boolean isHoliday(Date in)
    {
        if(CalendarUtils.isWeekend(in))
        {
            return true;
        } else
        {
            Date holidays[] = getHolidays();
            return isHoliday(in, false, false, holidays);
        }
    }

    protected abstract TradingCalendarDetails getTradingCalendarDetails();

    public Date[] getHolidays()
    {
        return getTradingCalendarDetails().getHolidays();
    }

    public String getTradeOpenTime()
    {
        return getTradingCalendarDetails().getTradeOpenTime();
    }

    public String getTradeCloseTime()
    {
        return getTradingCalendarDetails().getTradeCloseTime();
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
    private static final int NEXT = 1;
    private static final int PREVIOUS = -1;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.AbstractTradingCalendarImpl.class);
        DBG = m_log.ison();
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MarketCalendarImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import java.util.Date;
import java.util.List;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls:
//            AbstractTradingCalendarImpl

public class MarketCalendarImpl extends AbstractTradingCalendarImpl
    implements MarketCalendar
{

    public MarketCalendarImpl(Market m)
    {
        m_market = m;
        m_calendarDetails = GtlUtils.getFinObjectManager().getTradingCalendarModel().getTradingCalendarDetails(m);
    }

    public Date getCurrentBizDate()
    {
        return GtlUtils.getFinObjectManager().getTradingCalendarModel().getCurrentBizDate(m_market);
    }

    protected TradingCalendarDetails getTradingCalendarDetails()
    {
        return m_calendarDetails;
    }

    public String getRecessStartTime()
    {
        List prefs = m_market.getPreferences().getPreferences("recess.start.time");
        return prefs == null || prefs.size() <= 0 ? null : (String)prefs.get(0);
    }

    public String getRecessEndTime()
    {
        List prefs = m_market.getPreferences().getPreferences("recess.end.time");
        return prefs == null || prefs.size() <= 0 ? null : (String)prefs.get(0);
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
    private final TradingCalendarDetails m_calendarDetails;
    private final Market m_market;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.MarketCalendarImpl.class);
        DBG = m_log.ison();
    }
}

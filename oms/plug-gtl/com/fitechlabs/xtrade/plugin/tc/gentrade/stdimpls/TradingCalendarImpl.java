// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TradingCalendarImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls:
//            AbstractTradingCalendarImpl

public class TradingCalendarImpl extends AbstractTradingCalendarImpl
{

    public TradingCalendarImpl(long tradedProductId)
    {
        m_tradedProductId = tradedProductId;
        m_calendarDetails = GtlUtils.getFinObjectManager().getTradingCalendarModel().getTradingCalendarDetails(tradedProductId);
    }

    public Date getCurrentBizDate()
    {
        return GtlUtils.getFinObjectManager().getTradingCalendarModel().getCurrentBizDate(m_tradedProductId);
    }

    protected TradingCalendarDetails getTradingCalendarDetails()
    {
        return m_calendarDetails;
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
    private final long m_tradedProductId;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingCalendarImpl.class);
        DBG = m_log.ison();
    }
}

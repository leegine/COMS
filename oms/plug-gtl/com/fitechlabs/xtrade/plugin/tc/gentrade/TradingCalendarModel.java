// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TradingCalendarModel.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            TradingCalendarDetails, Market

public interface TradingCalendarModel
{

    public abstract TradingCalendarDetails getTradingCalendarDetails(long l);

    public abstract TradingCalendarDetails getTradingCalendarDetails(Market market);

    public abstract Date getCurrentBizDate(Market market);

    public abstract Date getCurrentBizDate(long l);

    public static final String BIZ_DATE_OFFSET_ATTRIBUTES_NAME = "xblocks.gtl.attributes.bizdate.offset";
}

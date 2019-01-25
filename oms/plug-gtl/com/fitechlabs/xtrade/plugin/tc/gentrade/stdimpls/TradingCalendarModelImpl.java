// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TradingCalendarModelImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls:
//            CalendarUtils

public class TradingCalendarModelImpl
    implements TradingCalendarModel
{
    private static class TradingCalendarDetailsImpl
        implements TradingCalendarDetails
    {

        public Date[] getHolidays()
        {
            if(m_holidays == null)
                m_holidays = (Date[])m_model.getHolidays(m_tradedProductId, m_market).toArray(NULL_DATE_ARRAY);
            return m_holidays;
        }

        public String getTradeCloseTime()
        {
            return m_closeTime;
        }

        public String getTradeOpenTime()
        {
            return m_openTime;
        }

        private static final Date NULL_DATE_ARRAY[] = new Date[0];
        private final TradingCalendarModelImpl m_model;
        private final Long m_tradedProductId;
        private final Market m_market;
        private final String m_openTime;
        private final String m_closeTime;
        private Date m_holidays[];


        public TradingCalendarDetailsImpl(TradingCalendarModelImpl model, Long tradedProductId, Market m, String openTime, String closeTime)
        {
            m_model = model;
            m_tradedProductId = tradedProductId;
            m_market = m;
            m_openTime = openTime;
            m_closeTime = closeTime;
        }
    }


    public TradingCalendarModelImpl()
    {
    }

    public Date getCurrentBizDate(long tradedProductId)
    {
        Date now = GtlUtils.getSystemTimestamp();
        TradingCalendarDetails tcd = getTradingCalendarDetails(tradedProductId);
        String closeTimeStr = tcd.getTradeCloseTime();
        boolean nextDayCloseTime = containsShiftToNextDaySymbol(closeTimeStr);
        TradingCalendar tcal = GtlUtils.getFinObjectManager().getTradingCalendar(tradedProductId);
        Date bizDate;
        if(!nextDayCloseTime)
        {
            Date tradeCloseTime = toDate(closeTimeStr, nextDayCloseTime);
            if(now.before(tradeCloseTime) && !tcal.isHoliday(now))
                bizDate = CalendarUtils.clearTimeFields(now);
            else
                bizDate = tcal.roll(CalendarUtils.clearTimeFields(now), 1);
        } else
        {
            Date tradeOpenTime = toDate(tcd.getTradeOpenTime(), false);
            Date tradeCloseTime = toDate(tcd.getTradeCloseTime(), nextDayCloseTime);
            Date oneDayBackShiftedTradeOpenTime = CalendarUtils.getPrevCalendarDay(tradeOpenTime);
            Date oneDayBackShiftedTradeCloseTime = CalendarUtils.getPrevCalendarDay(tradeCloseTime);
            Date baseDateForCalc = CalendarUtils.clearTimeFields(now.before(oneDayBackShiftedTradeCloseTime) ? oneDayBackShiftedTradeOpenTime : now);
            if(!tcal.isHoliday(baseDateForCalc))
                bizDate = baseDateForCalc;
            else
                bizDate = tcal.roll(baseDateForCalc, 1);
        }
        int bizDateOffset = getCurrentBizDateOffset();
        return bizDateOffset != 0 ? tcal.roll(bizDate, bizDateOffset) : bizDate;
    }

    public Date getCurrentBizDate(Market m)
    {
        Date now = GtlUtils.getSystemTimestamp();
        TradingCalendarDetails tcd = getTradingCalendarDetails(m);
        String closeTimeStr = tcd.getTradeCloseTime();
        boolean nextDayCloseTime = containsShiftToNextDaySymbol(closeTimeStr);
        MarketCalendar mcal = GtlUtils.getFinObjectManager().getMarketCalendar(m);
        Date bizDate;
        if(!nextDayCloseTime)
        {
            Date tradeCloseTime = toDate(closeTimeStr, nextDayCloseTime);
            if(now.before(tradeCloseTime) && !mcal.isHoliday(now))
                bizDate = CalendarUtils.clearTimeFields(now);
            else
                bizDate = mcal.roll(CalendarUtils.clearTimeFields(now), 1);
        } else
        {
            Date tradeOpenTime = toDate(tcd.getTradeOpenTime(), false);
            Date tradeCloseTime = toDate(tcd.getTradeCloseTime(), nextDayCloseTime);
            Date oneDayBackShiftedTradeOpenTime = CalendarUtils.getPrevCalendarDay(tradeOpenTime);
            Date oneDayBackShiftedTradeCloseTime = CalendarUtils.getPrevCalendarDay(tradeCloseTime);
            Date baseDateForCalc = CalendarUtils.clearTimeFields(now.before(oneDayBackShiftedTradeCloseTime) ? oneDayBackShiftedTradeOpenTime : now);
            bizDate = mcal.isHoliday(baseDateForCalc) ? mcal.roll(baseDateForCalc, 1) : baseDateForCalc;
        }
        int bizDateOffset = getCurrentBizDateOffset();
        return bizDateOffset != 0 ? mcal.roll(bizDate, bizDateOffset) : bizDate;
    }

    public TradingCalendarDetails getTradingCalendarDetails(long tradedProductId)
    {
        String tradeOpenTime = null;
        String tradeCloseTime = null;
        try
        {
            QueryProcessor qp = Processors.getDefaultProcessor();
            String where = "traded_product_id = ? and trade_date = ? ";
            Object bvs[] = {
                new Long(tradedProductId), CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp())
            };
            List rows = qp.doFindAllQuery(TradedProductCalendarRow.TYPE, "traded_product_id = ? and trade_date = ? ", bvs);
            if(rows.size() > 0)
            {
                TradedProductCalendarRow r = (TradedProductCalendarRow)rows.get(0);
                if(r.getTradeOpenTime() != null)
                    tradeOpenTime = r.getTradeOpenTime();
                if(r.getTradeCloseTime() != null)
                    tradeCloseTime = r.getTradeCloseTime();
            }
        }
        catch(DataException de)
        {
            String msg = "Exception while fetching TRADED_PRODUCT_CALENDAR.";
            m_log.error("Exception while fetching TRADED_PRODUCT_CALENDAR.", de);
            throw new RuntimeSystemException("Exception while fetching TRADED_PRODUCT_CALENDAR.", de);
        }
        Market m = null;
        try
        {
            TradedProductRow row = TradedProductDao.findRowByTradedProductId(tradedProductId);
            m = GtlUtils.getFinObjectManager().getMarket(row.getMarketId());
        }
        catch(DataException de)
        {
            String msg = "Exception while fetching traded_product table with tradedProductId:" + tradedProductId;
            m_log.error(msg, de);
            throw new RuntimeSystemException(msg, de);
        }
        catch(NotFoundException nfe)
        {
            String msg = "No market found for marketId specified in traded_product table with tradedProductID: " + tradedProductId;
            m_log.error(msg, nfe);
            throw new RuntimeSystemException(msg, nfe);
        }
        MarketRow marketRow = (MarketRow)m.getDataSourceObject();
        if(tradeOpenTime == null)
            tradeOpenTime = getMarketSpecificTradeOpenTime(marketRow);
        if(tradeCloseTime == null)
            tradeCloseTime = getMarketSpecificTradeCloseTime(marketRow);
        return new TradingCalendarDetailsImpl(this, new Long(tradedProductId), m, tradeOpenTime, tradeCloseTime);
    }

    public TradingCalendarDetails getTradingCalendarDetails(Market m)
    {
        MarketRow row = (MarketRow)m.getDataSourceObject();
        String tradeOpenTime = getMarketSpecificTradeOpenTime(row);
        String tradeCloseTime = getMarketSpecificTradeCloseTime(row);
        return new TradingCalendarDetailsImpl(this, null, m, tradeOpenTime, tradeCloseTime);
    }

    protected List getHolidays(Long tradedProductId)
    {
        List retList;
        String where = "traded_product_id = ? and holiday_flag = ?";
        Object bvs[] = {
            tradedProductId, BooleanEnum.TRUE
        };
        String orderBy = "trade_date";
        QueryProcessor qp = Processors.getDefaultProcessor();
        List rows = qp.doFindAllQuery(TradedProductCalendarRow.TYPE, "traded_product_id = ? and holiday_flag = ?", "trade_date", null, bvs);
        int size = rows.size();
        retList = new ArrayList(size);
        for(int i = 0; i < size; i++)
        {
            TradedProductCalendarRow calendarRow = (TradedProductCalendarRow)rows.get(i);
            Date val = calendarRow.getTradeDate();
            retList.add(val);
        }

        return retList;
        DataException de;
        de;
        String msg = "Exception while getting holidays info for tradedProductId: " + tradedProductId;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    protected List getHolidays(Market m)
    {
        List retList;
        String where = "market_id = ? and holiday_flag = ?";
        Object bvs[] = {
            new Long(m.getMarketId()), BooleanEnum.TRUE
        };
        String orderBy = "trade_date";
        QueryProcessor qp = Processors.getDefaultProcessor();
        List rows = qp.doFindAllQuery(MarketCalendarRow.TYPE, "market_id = ? and holiday_flag = ?", "trade_date", null, bvs);
        int size = rows.size();
        retList = new ArrayList(size);
        for(int i = 0; i < size; i++)
        {
            MarketCalendarRow calendarRow = (MarketCalendarRow)rows.get(i);
            Date val = calendarRow.getTradeDate();
            retList.add(val);
        }

        return retList;
        DataException de;
        de;
        String msg = "Exception while getting holidays info for marketId: " + m.getMarketId();
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    protected List getHolidays(Long tradedProductId, Market m)
    {
        if(tradedProductId == null && m == null)
        {
            m_log.warn("Possible program error. Both TradedProduct and Market are null !!!");
            return Collections.EMPTY_LIST;
        }
        List holidays4TradedProduct = tradedProductId == null ? Collections.EMPTY_LIST : getHolidays(tradedProductId);
        List holidays4Market = m == null ? Collections.EMPTY_LIST : getHolidays(m);
        if(holidays4TradedProduct.size() == 0)
        {
            return holidays4Market;
        } else
        {
            holidays4Market.addAll(holidays4TradedProduct);
            Collections.sort(holidays4Market);
            return holidays4Market;
        }
    }

    private static String getMarketSpecificTradeOpenTime(MarketRow marketRow)
    {
        MarketCalendarRow mcr = getMarketCalendarRow(marketRow.getMarketId());
        if(mcr != null && mcr.getTradeOpenTime() != null)
            return mcr.getTradeOpenTime();
        else
            return marketRow.getOpenTime();
    }

    private static String getMarketSpecificTradeCloseTime(MarketRow marketRow)
    {
        MarketCalendarRow mcr = getMarketCalendarRow(marketRow.getMarketId());
        if(mcr != null && mcr.getTradeCloseTime() != null)
            return mcr.getTradeCloseTime();
        else
            return marketRow.getCloseTime();
    }

    private static MarketCalendarRow getMarketCalendarRow(long marketId)
    {
        Object bvs[];
        String where = "market_id = ? and trade_date = ? ";
        bvs = (new Object[] {
            new Long(marketId), CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp())
        });
        MarketCalendarRow mcr;
        QueryProcessor qp = Processors.getDefaultProcessor();
        List rows = qp.doFindAllQuery(MarketCalendarRow.TYPE, "market_id = ? and trade_date = ? ", bvs);
        if(rows.size() <= 0)
            break MISSING_BLOCK_LABEL_74;
        mcr = (MarketCalendarRow)rows.get(0);
        return mcr;
        return null;
        DataException de;
        de;
        String msg = "Exception while fetching market_calendar.";
        m_log.error("Exception while fetching market_calendar.", de);
        throw new RuntimeSystemException("Exception while fetching market_calendar.", de);
    }

    private static boolean containsShiftToNextDaySymbol(String timeStr)
    {
        return timeStr.endsWith("+");
    }

    private static Date toDate(String hhmmStr, boolean lookForShiftToNextDaySymbol)
    {
        boolean shouldShiftToNextDay = lookForShiftToNextDaySymbol && containsShiftToNextDaySymbol(hhmmStr);
        Calendar cal;
        Date hhmmDate = GtlUtils.getThreadSafeSimpleDateFormat("HH:mm").parse(hhmmStr);
        Date today = GtlUtils.getSystemTimestamp();
        cal = Calendar.getInstance();
        cal.setTime(today);
        int yyyy = cal.get(1);
        int mm = cal.get(2);
        int dd = cal.get(5);
        cal.setTime(hhmmDate);
        cal.set(yyyy, mm, dd);
        if(shouldShiftToNextDay)
            cal.add(5, 1);
        return cal.getTime();
        ParseException pe;
        pe;
        String msg = "Invalid time string : " + hhmmStr + ", expecting in format:" + "HH:mm";
        m_log.error(msg, pe);
        throw new RuntimeSystemException(msg, pe);
    }

    private static int getCurrentBizDateOffset()
    {
        Object offset = ThreadLocalSystemAttributesRegistry.getAttribute("xblocks.gtl.attributes.bizdate.offset");
        if(offset == null)
            return 0;
        else
            return ((Integer)offset).intValue();
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
    private static final String HH_MM_DATE_FORMAT_STRING = "HH:mm";

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingCalendarModelImpl.class);
        DBG = m_log.ison();
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TradingSystemImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TradingSystemImpl
    implements TradingSystem
{

    public TradingSystemImpl()
    {
    }

    public Timestamp getSystemTimestamp()
    {
        Object timestamp = ThreadLocalSystemAttributesRegistry.getAttribute("xblocks.gtl.attributes.systemtimestamp");
        if(timestamp != null)
            return (Timestamp)timestamp;
        if(m_shiftTime)
        {
            long shiftMilliSecs = 0L;
            try
            {
                SystemPreferencesRow row = SystemPreferencesDao.findRowByName("shift.system.timestamp.millisecs");
                if(row == null)
                    m_log.warn("no row found in SYSTEM_PREFERENCES with  name : shift.system.timestamp.millisecs");
                else
                    try
                    {
                        shiftMilliSecs = Long.parseLong(row.getValue());
                    }
                    catch(NumberFormatException nfe)
                    {
                        m_log.warn("Invalid number for valuein SYSTEM_PREFERENCES with  name : shift.system.timestamp.millisecs");
                    }
            }
            catch(DataException de)
            {
                String msg = "Exception while fetching system_preferenes with name:shift.system.timestamp.millisecs";
                m_log.error("Exception while fetching system_preferenes with name:shift.system.timestamp.millisecs", de);
                throw new RuntimeSystemException("Exception while fetching system_preferenes with name:shift.system.timestamp.millisecs", de);
            }
            return new Timestamp(System.currentTimeMillis() + shiftMilliSecs);
        } else
        {
            return new Timestamp(System.currentTimeMillis());
        }
    }

    public Date getBizDate()
    {
        SystemPreferencesRow row = SystemPreferencesDao.findRowByName("system.bizdate");
        if(row == null)
            return clearTimeFields(GtlUtils.getTradingSystem().getSystemTimestamp());
        if(DBG)
            m_log.debug("biz date : " + row.getValue());
        if(!row.getValue().equals(m_cachedBizDateYYYYMMDD))
            synchronized(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl.class)
            {
                if(!row.getValue().equals(m_cachedBizDateYYYYMMDD))
                {
                    String yyyymmdd = row.getValue();
                    m_cachedBizDate = GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().parse(yyyymmdd);
                    m_cachedBizDateYYYYMMDD = yyyymmdd;
                }
            }
        return new Date(m_cachedBizDate.getTime());
        ParseException pe;
        pe;
        String msg = "Invalid value for biz date in system_preferenes.Should be of format YYYYMMDD. value found is" + row.getValue();
        m_log.error(msg, pe);
        throw new RuntimeSystemException(msg, pe);
        DataException de;
        de;
        String msg = "Exception while fetching biz_date from system_preferenes with pref namesystem.bizdate";
        m_log.error("Exception while fetching biz_date from system_preferenes with pref namesystem.bizdate", de);
        throw new RuntimeSystemException("Exception while fetching biz_date from system_preferenes with pref namesystem.bizdate", de);
    }

    public boolean isSystemAcceptingOrders()
    {
        SystemPreferencesRow row = SystemPreferencesDao.findRowByPk("system.accepting.orders");
        return "true".equals(row.getValue());
        DataFindException dfe;
        dfe;
        m_log.warn("no row found in SYSTEM_PREFERENCES with  name : system.accepting.orders");
        return true;
        DataException de;
        de;
        String msg = "Exception while fetching system_preferenes with name:system.accepting.orders";
        m_log.error("Exception while fetching system_preferenes with name:system.accepting.orders", de);
        throw new RuntimeSystemException("Exception while fetching system_preferenes with name:system.accepting.orders", de);
    }

    public String getSystemWideBroadcastMessages()
    {
        String val;
        SystemPreferencesRow row = SystemPreferencesDao.findRowByPk("system.broadcast.message");
        val = row.getValue();
        return val.trim().length() != 0 ? val : null;
        DataFindException dfe;
        dfe;
        return null;
        DataException de;
        de;
        String msg = "Exception while fetching system_preferenes with name:system.broadcast.message";
        m_log.error("Exception while fetching system_preferenes with name:system.broadcast.message", de);
        throw new RuntimeSystemException("Exception while fetching system_preferenes with name:system.broadcast.message", de);
    }

    public String getPreference(String name)
    {
        SystemPreferencesRow row = SystemPreferencesDao.findRowByName(name);
        return row == null ? null : row.getValue();
        DataException de;
        de;
        String msg = "Exception while fetching system_preferenes with name:" + name;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public Map getPreferences()
    {
        Map prefsMap;
        QueryProcessor qp = Processors.getDefaultProcessor();
        List rows = qp.doFindAllQuery(SystemPreferencesRow.TYPE);
        int size = rows.size();
        prefsMap = new HashMap(size);
        for(int i = 0; i < size; i++)
        {
            SystemPreferencesRow r = (SystemPreferencesRow)rows.get(i);
            prefsMap.put(r.getName(), r.getValue());
        }

        return prefsMap;
        DataException de;
        de;
        String msg = "Exception while fetching all system_preferenes.";
        m_log.error("Exception while fetching all system_preferenes.", de);
        throw new RuntimeSystemException("Exception while fetching all system_preferenes.", de);
    }

    private static Date clearTimeFields(Date inDate)
    {
        return new Date(inDate.getYear(), inDate.getMonth(), inDate.getDate());
    }

    public Market getMarketForSystemCalendar(Institution inst)
    {
        String marketCode;
        marketCode = GtlUtils.getTradingSystem().getPreference(inst.getInstitutionCode() + "." + "system.calendar.market.code");
        if(marketCode == null)
            return null;
        return GtlUtils.getFinObjectManager().getMarket(inst, marketCode);
        NotFoundException notFoundEx;
        notFoundEx;
        String msg = "Market could not be obtained for market code defined in system preferences with preference name:system.calendar.market.code; Market code defined in system_preferences is : " + marketCode;
        m_log.error(msg, notFoundEx);
        throw new RuntimeSystemException(msg, notFoundEx);
    }

    private static final Logit m_log;
    private static final boolean DBG;
    private static boolean m_shiftTime;
    private static Date m_cachedBizDate;
    private static String m_cachedBizDateYYYYMMDD;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl.class);
        DBG = m_log.ison();
        try
        {
            SystemPreferencesRow row = SystemPreferencesDao.findRowByPk("shift.system.timestamp");
            m_shiftTime = "true".equals(row.getValue());
        }
        catch(DataFindException dfe)
        {
            m_log.warn("No row found in system_preferences with name : shift.system.timestamp");
        }
        catch(DataException de)
        {
            String msg = "Error while fetching system_preferences.";
            m_log.error("Error while fetching system_preferences.", de);
            throw new RuntimeSystemException("Error while fetching system_preferences.", de);
        }
        if(m_shiftTime)
            m_log.warn("!!! system timestamp shifting is ON !!!");
    }
}

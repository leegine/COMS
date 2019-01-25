// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GtlUtils.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            FinApp, TradingSystem, ProductTypeEnum, TradingModule, 
//            FinObjectManager, AccountManager, GeneralizedFinTransactionManager, CommonOrderValidator, 
//            GlobalBizLogicProvider

public class GtlUtils
{
    public static class Double
    {

        public static boolean isEqual(double a, double b)
        {
            if(java.lang.Double.isNaN(a) && java.lang.Double.isNaN(b))
                return true;
            if(java.lang.Double.isNaN(a) || java.lang.Double.isNaN(b))
                return false;
            else
                return a == b || a + 9.9899999999999999E-011D > b && a - 9.9899999999999999E-011D < b;
        }

        public static boolean isZero(double a)
        {
            double b = 0.0D;
            if(java.lang.Double.isNaN(a))
                return false;
            else
                return a == 0.0D || a + 9.9899999999999999E-011D > 0.0D && a - 9.9899999999999999E-011D < 0.0D;
        }

        public static final double DELTA = 9.9899999999999999E-011D;

        public Double()
        {
        }
    }


    public GtlUtils()
    {
    }

    public static Date clearTimeFields(Date in)
    {
        return new Date(in.getYear(), in.getMonth(), in.getDate());
    }

    public static Date clearTimeFields(Timestamp in)
    {
        return new Date(in.getYear(), in.getMonth(), in.getDate());
    }

    public static Timestamp getSystemTimestamp()
    {
        return getFinApp().getTradingSystem().getSystemTimestamp();
    }

    public static TradingModule getTradingModule(ProductTypeEnum productType)
    {
        return getFinApp().getTradingModule(productType);
    }

    public static TradingModule getTradingModule(String name)
    {
        return getFinApp().getTradingModule(name);
    }

    public static SimpleDateFormat getThreadSafeSimpleDateFormat(String pattern)
    {
        Map map = (Map)m_dateFormattersTable.get();
        SimpleDateFormat val = (SimpleDateFormat)map.get(pattern);
        if(val == null)
        {
            val = new SimpleDateFormat(pattern);
            map.put(pattern, val);
        }
        return val;
    }

    public static FinObjectManager getFinObjectManager()
    {
        return getFinApp().getFinObjectManager();
    }

    public static TradingSystem getTradingSystem()
    {
        return getFinApp().getTradingSystem();
    }

    public static AccountManager getAccountManager()
    {
        return getFinApp().getAccountManager();
    }

    public static GeneralizedFinTransactionManager getGeneralizedFinTransactionManager()
    {
        return getFinApp().getGeneralizedFinTransactionManager();
    }

    public static SimpleDateFormat getThreadSafeYYYYMMDDSimpleDateFormat()
    {
        return getThreadSafeSimpleDateFormat("yyyyMMdd");
    }

    public static CommonOrderValidator getCommonOrderValidator()
    {
        return getFinApp().getCommonOrderValidator();
    }

    public static FinApp getFinApp()
    {
        FinApp finApp = (FinApp)Services.getService(com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp.class);
        return finApp;
    }

    public static GlobalBizLogicProvider getGlobalBizLogicProvider()
    {
        return getFinApp().getGlobalBizLogicProvider();
    }

    public static Date truncateDate(Date dt)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static void copyRow2Params(Row from, Params to)
    {
        String field;
        Object value;
        for(Iterator it = from.toInsertMap().keySet().iterator(); it.hasNext(); to.setColumn(field, value))
        {
            field = (String)it.next();
            value = from.getColumn(field);
        }

    }

    private static final Logit m_log;
    private static final boolean DBG;
    private static final String YYYYMMDD_DATE_FORMAT = "yyyyMMdd";
    private static final ThreadLocal m_dateFormattersTable = new ThreadLocal() {

        protected Object initialValue()
        {
            return new HashMap();
        }

    }
;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.class);
        DBG = m_log.ison();
    }
}

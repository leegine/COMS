// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MarketImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls:
//            MarketCalendarImpl, SimplePreferencesImpl

public class MarketImpl
    implements Market
{

    public MarketImpl(long marketId)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        m_prefs = null;
        MarketPK pk = new MarketPK(marketId);
        m_marketRow = (MarketRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(pk, null);
    }

    public MarketImpl(Institution inst, String code)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        m_prefs = null;
        m_marketRow = MarketDao.findRowByInstitutionCodeMarketCode(inst.getInstitutionCode(), code);
        if(m_marketRow == null)
            throw new DataFindException("Not market found with institutionCode,marketCode:" + inst.getInstitutionCode() + "," + code);
        else
            return;
    }

    public long getMarketId()
    {
        return m_marketRow.getMarketId();
    }

    public String getMarketCode()
    {
        return m_marketRow.getMarketCode();
    }

    public String getMarketName()
    {
        return m_marketRow.getMarketName();
    }

    public String getMarketNameAlt1()
    {
        return m_marketRow.getMarketNameAlt1();
    }

    public String getMarketNameAlt2()
    {
        return m_marketRow.getMarketNameAlt2();
    }

    public MarketCalendar getMarketCalendar()
    {
        return new MarketCalendarImpl(this);
    }

    public boolean isAcceptingOrders()
    {
        return isMarketPrefSetToTrue("is_accepting_orders");
    }

    public boolean acceptsLimitOrder()
    {
        return isMarketPrefSetToTrue("accepts_limit_order");
    }

    public boolean acceptsMarketOrder()
    {
        return isMarketPrefSetToTrue("accepts_market_order");
    }

    public Preferences getPreferences()
    {
        if(m_prefs != null)
            return m_prefs;
        String where = "market_id = ?";
        String orderBy = "name, name_serial_no";
        Object bindVars[] = {
            new Long(getMarketId())
        };
        List rows = Processors.getDefaultProcessor().doFindAllQuery(MarketPreferencesRow.TYPE, "market_id = ?", "name, name_serial_no", null, bindVars);
        int size = rows.size();
        String prevPrefName = null;
        ArrayList groupedPrefsByName = new ArrayList();
        SimplePreferencesImpl simplePrefs = new SimplePreferencesImpl();
        for(int i = 0; i < size; i++)
        {
            MarketPreferencesRow r = (MarketPreferencesRow)rows.get(i);
            String prefName = r.getName();
            if(!prefName.equals(prevPrefName))
            {
                if(prevPrefName != null)
                    simplePrefs.add(prevPrefName, groupedPrefsByName);
                prevPrefName = prefName;
                groupedPrefsByName = new ArrayList();
            }
            groupedPrefsByName.add(r.getValue());
        }

        if(groupedPrefsByName.size() > 0)
            simplePrefs.add(prevPrefName, groupedPrefsByName);
        m_prefs = simplePrefs;
        return m_prefs;
        DataException de;
        de;
        m_log.error("Exception while fetching market_preferences table.", de);
        return new SimplePreferencesImpl();
    }

    public double getMaxNumberOfLotsPerOrder(ProductTypeEnum productType)
    {
        String val;
        String prefName = productType.stringValue() + "." + "per_order_max_lots";
        val = getPrefValue(prefName);
        if(val == null)
            return (0.0D / 0.0D);
        return Double.parseDouble(val);
        NumberFormatException nfe;
        nfe;
        String msg = "Invalid per order MAX number of lots in MARKET_PREFERNCES table. Valid number is expected.";
        m_log.error("Invalid per order MAX number of lots in MARKET_PREFERNCES table. Valid number is expected.", nfe);
        throw new RuntimeSystemException("Invalid per order MAX number of lots in MARKET_PREFERNCES table. Valid number is expected.", nfe);
    }

    private boolean isMarketPrefSetToTrue(String prefName)
    {
        String val = getPrefValue(prefName);
        return val == null || "true".equals(val);
    }

    private String getPrefValue(String prefName)
    {
        List rows;
        String where = "market_id = ? and name = ?";
        Object bindVars[] = {
            new Long(getMarketId()), prefName
        };
        rows = Processors.getDefaultProcessor().doFindAllQuery(MarketPreferencesRow.TYPE, "market_id = ? and name = ?", null, bindVars);
        if(rows.size() > 0)
            return ((MarketPreferencesRow)rows.get(0)).getValue();
        return null;
        DataException de;
        de;
        String msg = "Exception while fetching market_preferences table.";
        m_log.error("Exception while fetching market_preferences table.", de);
        throw new RuntimeSystemException("Exception while fetching market_preferences table.", de);
    }

    public Object getDataSourceObject()
    {
        return m_marketRow;
    }

    public Institution getInstitution()
    {
        return GtlUtils.getAccountManager().getInstitution(m_marketRow.getInstitutionCode());
        NotFoundException nfe;
        nfe;
        throw new IllegalStateException(nfe.getMessage());
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
    private final MarketRow m_marketRow;
    private Preferences m_prefs;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.MarketImpl.class);
        DBG = m_log.ison();
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FinObjectManagerImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import java.util.List;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls:
//            TradingCalendarModelImpl, MarketImpl, TraderImpl, TradingCalendarImpl, 
//            MarketCalendarImpl

public class FinObjectManagerImpl
    implements FinObjectManager
{

    public FinObjectManagerImpl()
    {
        m_tradingCalendarModel = new TradingCalendarModelImpl();
    }

    public Market getMarket(Institution inst, String code)
        throws NotFoundException
    {
        return new MarketImpl(inst, code);
        DataFindException dfe;
        dfe;
        throw new NotFoundException("No Market object found with the given code : " + code);
        DataException de;
        de;
        String msg = "Error while getting Market Object for code : " + code;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public Market getMarket(long id)
        throws NotFoundException
    {
        return new MarketImpl(id);
        DataFindException dfe;
        dfe;
        throw new NotFoundException("No Market object found with the given id : " + id);
        DataException de;
        de;
        String msg = "Error while getting Market Object for code : " + id;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public Market[] getMarkets()
    {
        Market markets[];
        QueryProcessor qp = Processors.getDefaultProcessor();
        List rows = qp.doFindAllQuery(MarketRow.TYPE, null, "market_id", null, null);
        int size = rows.size();
        markets = new Market[size];
        FinObjectManager fom = GtlUtils.getFinObjectManager();
        for(int i = 0; i < size; i++)
        {
            long marketId = ((MarketRow)rows.get(i)).getMarketId();
            try
            {
                markets[i] = fom.getMarket(marketId);
            }
            catch(NotFoundException nfe)
            {
                String msg = "Unexpected NotFoundException when fetching Market for marketId:" + marketId;
                m_log.error(msg, nfe);
                throw new RuntimeSystemException(msg, nfe);
            }
        }

        return markets;
        DataException de;
        de;
        String msg = "Exception while fetching all rows in market_table:" + de.getMessage();
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public Market[] getMarkets(Institution inst)
    {
        Market markets[];
        QueryProcessor qp = Processors.getDefaultProcessor();
        String where = "institution_code=?";
        Object bvs[] = {
            inst.getInstitutionCode()
        };
        List rows = qp.doFindAllQuery(MarketRow.TYPE, "institution_code=?", "market_id", null, bvs);
        int size = rows.size();
        markets = new Market[size];
        FinObjectManager fom = GtlUtils.getFinObjectManager();
        for(int i = 0; i < size; i++)
        {
            long marketId = ((MarketRow)rows.get(i)).getMarketId();
            try
            {
                markets[i] = fom.getMarket(marketId);
            }
            catch(NotFoundException nfe)
            {
                String msg = "Unexpected NotFoundException when fetching Market for marketId:" + marketId;
                m_log.error(msg, nfe);
                throw new RuntimeSystemException(msg, nfe);
            }
        }

        return markets;
        DataException de;
        de;
        String msg = "Exception while fetching all rows in market_table:" + de.getMessage();
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public Trader getTrader(long traderId)
        throws NotFoundException
    {
        return new TraderImpl(traderId, false);
        DataFindException dfe;
        dfe;
        throw new NotFoundException("No Trader  object found with the trader id : " + traderId);
        DataException de;
        de;
        String msg = "Error while getting Trader  Object for id : " + traderId;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public Trader getTrader(Institution inst, String traderCode, String branchCode)
        throws NotFoundException
    {
        return new TraderImpl(inst, traderCode, branchCode);
        DataFindException dfe;
        dfe;
        throw new NotFoundException("No Trader  object found with the InstitutionCode,trader code, branchCode : " + inst.getInstitutionCode() + "," + traderCode + "," + branchCode);
        DataException de;
        de;
        String msg = "Error while getting InstitutionCode,Trader code, branchCode : " + inst.getInstitutionCode() + "," + traderCode + "," + branchCode;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public Trader getTraderByLoginId(long loginId)
        throws NotFoundException
    {
        return new TraderImpl(loginId, true);
        DataFindException dfe;
        dfe;
        throw new NotFoundException("No Trader  object found with the login id : " + loginId);
        DataException de;
        de;
        String msg = "Error while getting Trader  Object for login id : " + loginId;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public TradingCalendar getTradingCalendar(long tradedProductId)
    {
        return new TradingCalendarImpl(tradedProductId);
    }

    public MarketCalendar getMarketCalendar(Market m)
    {
        return new MarketCalendarImpl(m);
    }

    public TradingCalendarModel getTradingCalendarModel()
    {
        return m_tradingCalendarModel;
    }

    public void overrideTradingCalendarModel(TradingCalendarModel newTcm)
    {
        synchronized(m_tradingCalendarModel)
        {
            m_tradingCalendarModel = newTcm;
        }
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
    private TradingCalendarModel m_tradingCalendarModel;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.FinObjectManagerImpl.class);
        DBG = m_log.ison();
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AppLevelEqTypeQuoteDataImpl.java

package com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.data.EqtypeSampleQuoteDataDao;
import com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.data.EqtypeSampleQuoteDataRow;
import java.sql.Timestamp;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote:
//            AppLevelEqTypeQuoteData

final class AppLevelEqTypeQuoteDataImpl
    implements AppLevelEqTypeQuoteData
{

    public AppLevelEqTypeQuoteDataImpl(EqTypeTradedProduct tradedProduct)
    {
        String productCode = ((EqTypeProduct)tradedProduct.getProduct()).getProductCode();
        String marketCode = tradedProduct.getMarket().getMarketCode();
        EqtypeSampleQuoteDataRow row = null;
        try
        {
            EqtypeSampleQuoteDataDao dao = EqtypeSampleQuoteDataDao.findDaoByPk(productCode, marketCode);
            if(dao != null)
            {
                row = dao.getEqtypeSampleQuoteDataRow();
            } else
            {
                m_log.error("FIX ME LATER once xTrade3.6 OR mapping API is fixed. Pain !!!");
                throw new DataFindException("eqtype_sample_quote_data row not found.");
            }
        }
        catch(DataFindException dfe)
        {
            m_log.warn("Return default quote since Quote data could note found for product code, market code: " + productCode + "," + marketCode);
        }
        catch(DataException de)
        {
            m_log.error(de.getMessage(), de);
            throw new RuntimeSystemException("Error while fetching from Eqtype_sample_Quote_Data table", de);
        }
        if(row != null)
        {
            m_askPrice = row.getAskPriceIsNull() ? (0.0D / 0.0D) : row.getAskPrice();
            m_askSize = row.getAskSizeIsNull() ? (0.0D / 0.0D) : row.getAskSize();
            m_bidPrice = row.getBidPriceIsNull() ? (0.0D / 0.0D) : row.getBidPrice();
            m_bidSize = row.getBidSizeIsNull() ? (0.0D / 0.0D) : row.getBidSize();
            m_openPrice = row.getOpenPriceIsNull() ? (0.0D / 0.0D) : row.getOpenPrice();
            m_currentPrice = row.getCurrentPriceIsNull() ? (0.0D / 0.0D) : row.getCurrentPrice();
            m_diff = row.getDiffIsNull() ? (0.0D / 0.0D) : row.getDiff();
            m_lastClosingPrice = row.getLastClosingPriceIsNull() ? (0.0D / 0.0D) : row.getLastClosingPrice();
            m_timestamp = row.getLastUpdatedTimestamp();
            m_prevTradingDayClosingPrice = row.getPrevTradingDayClosingPriceIsNull() ? (0.0D / 0.0D) : row.getPrevTradingDayClosingPrice();
            m_volume = row.getVolumeIsNull() ? (0.0D / 0.0D) : row.getVolume();
            m_ytdHighPrice = row.getYtdHighPriceIsNull() ? (0.0D / 0.0D) : row.getYtdHighPrice();
            m_ytdLowPrice = row.getYtdLowPriceIsNull() ? (0.0D / 0.0D) : row.getYtdLowPrice();
        } else
        {
            FinApp finApp = (FinApp)Services.getService(com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp.class);
            Timestamp now = finApp.getTradingSystem().getSystemTimestamp();
            m_askPrice = 101D;
            m_askSize = 1000D;
            m_bidPrice = 99D;
            m_bidSize = 1000D;
            m_openPrice = 98.849999999999994D;
            m_currentPrice = 100D;
            m_diff = 10D;
            m_lastClosingPrice = 90D;
            m_timestamp = now;
            m_prevTradingDayClosingPrice = m_lastClosingPrice;
            m_volume = 200000D;
            m_ytdHighPrice = 150D;
            m_ytdLowPrice = 65D;
        }
    }

    public double getVolume()
    {
        return m_volume;
    }

    public double getBidSize()
    {
        return m_bidSize;
    }

    public double getAskSize()
    {
        return m_askSize;
    }

    public double getYtdHighPrice()
    {
        return m_ytdHighPrice;
    }

    public double getYtdLowPrice()
    {
        return m_ytdLowPrice;
    }

    public double getLastClosingPrice()
    {
        return m_lastClosingPrice;
    }

    public double getPrevTradingDayClosingPrice()
    {
        return m_prevTradingDayClosingPrice;
    }

    public double getDiff()
    {
        return m_diff;
    }

    public double getOpenPrice()
    {
        return m_openPrice;
    }

    public double getCurrentPrice()
    {
        return m_currentPrice;
    }

    public double getBidPrice()
    {
        return m_bidPrice;
    }

    public double getAskPrice()
    {
        return m_askPrice;
    }

    public Timestamp getQuoteTimestamp()
    {
        return m_timestamp;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("current price : " + getCurrentPrice());
        return sb.toString();
    }

    private static final Logit m_log;
    private static final boolean DBG;
    private final double m_openPrice;
    private final double m_currentPrice;
    private final double m_volume;
    private final double m_bidSize;
    private final double m_askSize;
    private final double m_ytdHighPrice;
    private final double m_ytdLowPrice;
    private final double m_lastClosingPrice;
    private final double m_prevTradingDayClosingPrice;
    private final double m_diff;
    private final double m_bidPrice;
    private final double m_askPrice;
    private final Timestamp m_timestamp;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.AppLevelEqTypeQuoteDataImpl.class);
        DBG = m_log.ison();
    }
}

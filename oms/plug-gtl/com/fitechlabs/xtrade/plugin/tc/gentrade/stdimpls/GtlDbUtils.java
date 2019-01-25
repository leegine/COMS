// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GtlDbUtils.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class GtlDbUtils
{

    private GtlDbUtils()
    {
    }

    public static double getLimitPriceRange(long marketId, double price)
        throws DataException
    {
        List rows = Processors.getDefaultProcessor().doFindAllQuery(LimitPriceRangeDefsRow.TYPE, "market_id=? and cap_price > ? ", "cap_price asc", null, new Object[] {
            new Long(marketId), new Double(price)
        });
        if(rows.size() == 0)
            return (0.0D / 0.0D);
        else
            return ((LimitPriceRangeDefsRow)rows.get(0)).getRange();
    }

    public static double getTickValueUnit(long productId, ProductTypeEnum productType, long marketId, double limitPrice)
        throws DataException
    {
        List rows = Processors.getDefaultProcessor().doFindAllQuery(TickValuesDefsRow.TYPE, "market_id=? and product_type =? and cap_price>? and (product_id = ? or product_id is null)", "product_id asc,cap_price asc", null, new Object[] {
            new Long(marketId), productType, new Double(limitPrice), new Long(productId)
        });
        if(rows.size() == 0)
            return (0.0D / 0.0D);
        else
            return ((TickValuesDefsRow)rows.get(0)).getTickValue();
    }

    public static TradedProductRow getTradedProductRow(long productId, long marketId)
    {
        String currentBizDateYYYYMMDD;
        TradedProductRow row;
        try
        {
            row = TradedProductDao.findRowByProductIdMarketId(productId, marketId);
        }
        catch(DataException de)
        {
            String msg = "Exception while fetching traded_product table with product_id, market_id:" + productId + marketId;
            m_log.error(msg, de);
            throw new RuntimeSystemException(msg, de);
        }
        if(row == null)
            break MISSING_BLOCK_LABEL_270;
        if(row.getValidUntilBizDate() == null)
            return row;
        java.util.Date currentBizDate = GtlUtils.getFinObjectManager().getTradingCalendar(row.getTradedProductId()).getCurrentBizDate();
        currentBizDateYYYYMMDD = GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(currentBizDate);
        if(row.getValidUntilBizDate().equals(currentBizDateYYYYMMDD))
            return row;
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductUpdqRow updqRow;
        updqRow = TradedProductUpdqDao.findRowByProductIdMarketIdValidUntilBizDate(productId, marketId, currentBizDateYYYYMMDD);
        if(updqRow != null)
            break MISSING_BLOCK_LABEL_187;
        m_log.warn("No traded_product_updq row found with product_id,market_id,biz_date:" + productId + "," + marketId + "," + currentBizDateYYYYMMDD);
        return null;
        TradedProductParams params;
        params = new TradedProductParams();
        GtlUtils.copyRow2Params(updqRow, params);
        return params;
        DataException de;
        de;
        String msg = "Exception while fetching traded_product_updq table with product_id,market_id,biz_date:" + productId + "," + marketId + "," + currentBizDateYYYYMMDD;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
        return null;
    }

    public static TradedProductRow getTradedProductRow(long tradedProductId)
        throws DataFindException
    {
        String currentBizDateYYYYMMDD;
        TradedProductRow row;
        try
        {
            row = TradedProductDao.findRowByPk(tradedProductId);
        }
        catch(DataFindException dfe)
        {
            throw new DataFindException("No traded_product row found with tradedProductId :" + tradedProductId);
        }
        catch(DataException dq)
        {
            String msg = "Exception while fetching traded_product table with tradedProduct ID:" + tradedProductId;
            m_log.error(msg, dq);
            throw new RuntimeSystemException(msg, dq);
        }
        if(row.getValidUntilBizDate() == null)
            return row;
        java.util.Date currentBizDate = GtlUtils.getFinObjectManager().getTradingCalendar(tradedProductId).getCurrentBizDate();
        currentBizDateYYYYMMDD = GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(currentBizDate);
        if(row.getValidUntilBizDate().equals(currentBizDateYYYYMMDD))
            return row;
        TradedProductParams params;
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductUpdqRow updqRow = TradedProductUpdqDao.findRowByPk(tradedProductId, currentBizDateYYYYMMDD);
        params = new TradedProductParams();
        GtlUtils.copyRow2Params(updqRow, params);
        return params;
        DataFindException dfe;
        dfe;
        String msg = "No traded_product_updq row found with tradedProductId, bizDate:" + tradedProductId + "," + currentBizDateYYYYMMDD;
        m_log.warn(msg, dfe);
        throw new DataFindException(dfe.getMessage());
        DataException dq;
        dq;
        String msg = "Exception while fetching traded_product_updq table with tradedProductId,bizDate:" + tradedProductId + "," + currentBizDateYYYYMMDD;
        m_log.error(msg, dq);
        throw new RuntimeSystemException(msg, dq);
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
    static final String YYYYMM_DATE_FORMAT = "yyyyMM";
    static final String HH24MMSS_DATE_FORMAT = "HH:mm:ss";
    static final String YYYYMMDDHH24MMSS_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlDbUtils.class);
        DBG = m_log.ison();
    }
}

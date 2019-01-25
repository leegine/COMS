// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractProductImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import java.util.List;

public abstract class AbstractProductImpl
    implements Product
{

    public AbstractProductImpl(long productId)
        throws DataQueryException, DataNetworkException
    {
        this(ProductDao.findRowByPk(productId));
    }

    public AbstractProductImpl(ProductRow row)
    {
        m_productId = row.getProductId();
        m_productRow = row;
    }

    public long getProductId()
    {
        return m_productRow.getProductId();
    }

    public Market[] getTradedMarkets()
    {
        TradedProductRow row = null;
        List rows = null;
        try
        {
            rows = TradedProductDao.findRowsByProductId(m_productRow.getProductId());
        }
        catch(DataException dex)
        {
            String msg = "Failed to get traded products by product id : " + m_productRow.getProductId();
            m_log.error(msg);
            throw new RuntimeSystemException(msg, dex);
        }
        FinObjectManager finObjMgr = GtlUtils.getFinObjectManager();
        Market markets[] = new Market[rows.size()];
        for(int i = 0; i < rows.size(); i++)
            try
            {
                row = (TradedProductRow)rows.get(i);
                markets[i] = finObjMgr.getMarket(row.getMarketId());
            }
            catch(NotFoundException nfex)
            {
                String msg = "Failed to get market by market id : " + row.getMarketId();
                m_log.error(msg, nfex);
                throw new RuntimeSystemException(msg, nfex);
            }

        return markets;
    }

    public ProductTypeEnum getProductType()
    {
        return m_productRow.getProductType();
    }

    public boolean isTradedOnMarket(Market mkt)
    {
        Market markets[] = getTradedMarkets();
        for(int j = 0; j < markets.length; j++)
            if(mkt.getMarketId() == markets[j].getMarketId())
                return true;

        return false;
    }

    public double getLotSize()
    {
        return m_productRow.getLotSize();
    }

    public String getStandardName()
    {
        return m_productRow.getStandardName();
    }

    public String getNameAlt1()
    {
        return m_productRow.getNameAlt1();
    }

    public String getNameAlt2()
    {
        return m_productRow.getNameAlt2();
    }

    public double getMarginRatio()
    {
        return m_productRow.getMarginRatio();
    }

    public abstract Object getDataSourceObject();

    public Institution getInstitution()
    {
        if(m_inst == null)
            try
            {
                String instCode = m_productRow.getInstitutionCode();
                m_inst = GtlUtils.getAccountManager().getInstitution(instCode);
            }
            catch(NotFoundException nfe)
            {
                String msg = "No Institution for institution code in product row:" + m_productRow.toString();
                m_log.error(msg, nfe);
                throw new RuntimeSystemException(msg, nfe);
            }
        return m_inst;
    }

    public Market getPrimaryMarket()
    {
        if(m_primaryMarket == null)
            try
            {
                m_primaryMarket = GtlUtils.getFinObjectManager().getMarket(m_productRow.getPrimaryMarketId());
            }
            catch(NotFoundException nfe)
            {
                String msg = "No PrimaryMarket found for primary_market_id in product row:" + m_productRow.toString();
                m_log.error(msg, nfe);
                throw new RuntimeSystemException(msg, nfe);
            }
        return m_primaryMarket;
    }

    public TradedProduct getPrimaryTradedProduct()
    {
        if(m_tradedProduct == null)
        {
            Market pm = getPrimaryMarket();
            try
            {
                m_tradedProduct = getThisTradingModule().getProductManager().getTradedProduct(getProductId(), pm.getMarketId());
            }
            catch(NotFoundException nfe)
            {
                String msg = "No Primary TradedProduct found for product_id, primary_market_id in product row:" + m_productRow.toString();
                m_log.error(msg, nfe);
                throw new RuntimeSystemException(msg, nfe);
            }
        }
        return m_tradedProduct;
    }

    protected abstract TradingModule getThisTradingModule();

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private long m_productId;
    protected ProductRow m_productRow;
    private static final Logit m_log;
    private static final boolean DBG;
    private Institution m_inst;
    private Market m_primaryMarket;
    private TradedProduct m_tradedProduct;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.AbstractProductImpl.class);
        DBG = m_log.ison();
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractTradedProductImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls:
//            GtlDbUtils

public abstract class AbstractTradedProductImpl
    implements TradedProduct
{

    protected AbstractTradedProductImpl(long tradedProductId)
        throws DataFindException
    {
        this(GtlDbUtils.getTradedProductRow(tradedProductId));
    }

    protected AbstractTradedProductImpl(TradedProductRow row)
    {
        m_tradedProductId = row.getTradedProductId();
        m_tradedProductRow = row;
    }

    public long getTradedProductId()
    {
        return m_tradedProductId;
    }

    public Date getDailyDeliveryDate()
    {
        Date deliveryDate = m_tradedProductRow.getDailyDeliveryDate();
        if(deliveryDate != null)
        {
            return deliveryDate;
        } else
        {
            TradingCalendar tcal = GtlUtils.getFinObjectManager().getTradingCalendar(getTradedProductId());
            Date curDate = tcal.getCurrentBizDate();
            return tcal.roll(curDate, getDeliveryDateShiftDays());
        }
    }

    public double getMarginRatio()
    {
        if(m_tradedProductRow.getMarginRatioIsNull())
            return getProduct().getMarginRatio();
        else
            return m_tradedProductRow.getMarginRatio();
    }

    public Product getProduct()
    {
        if(m_product == null)
            try
            {
                m_product = getThisTradingModule().getProductManager().getProduct(m_tradedProductRow.getProductId());
            }
            catch(NotFoundException nfe)
            {
                String msg = "No product found with productId: " + m_tradedProductRow.getProductId() + ", in tradedProduct with Id" + getTradedProductId();
                m_log.error(msg, nfe);
                throw new RuntimeSystemException(msg, nfe);
            }
        return m_product;
    }

    public Market getMarket()
    {
        if(m_market == null)
            try
            {
                m_market = GtlUtils.getFinObjectManager().getMarket(m_tradedProductRow.getMarketId());
            }
            catch(NotFoundException ex)
            {
                String msg = "Cannot get market by market id :" + m_tradedProductRow.getMarketId() + " in tradedProduct with id : " + getTradedProductId();
                m_log.error(msg, ex);
                throw new RuntimeSystemException(msg, ex);
            }
        return m_market;
    }

    public boolean isTradingSuspended()
    {
        return BooleanEnum.TRUE.equals(m_tradedProductRow.getSuspensionFlag());
    }

    public boolean isCollateralizable()
    {
        return BooleanEnum.TRUE.equals(m_tradedProductRow.getCollateralFlag());
    }

    public Date getBaseDate()
    {
        return m_tradedProductRow.getBaseDate();
    }

    protected abstract int getDeliveryDateShiftDays();

    public abstract Object getDataSourceObject();

    public Institution getInstitution()
    {
        if(m_inst == null)
            try
            {
                String instCode = m_tradedProductRow.getInstitutionCode();
                m_inst = GtlUtils.getAccountManager().getInstitution(instCode);
            }
            catch(NotFoundException nfe)
            {
                String msg = "No Institution for institution code in traded_product row:" + m_tradedProductRow.toString();
                m_log.error(msg, nfe);
                throw new RuntimeSystemException(msg, nfe);
            }
        return m_inst;
    }

    protected abstract TradingModule getThisTradingModule();

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static final Logit m_log;
    private static final boolean DBG;
    private long m_tradedProductId;
    protected TradedProductRow m_tradedProductRow;
    private Product m_product;
    private Market m_market;
    private Institution m_inst;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.AbstractTradedProductImpl.class);
        DBG = m_log.ison();
    }
}

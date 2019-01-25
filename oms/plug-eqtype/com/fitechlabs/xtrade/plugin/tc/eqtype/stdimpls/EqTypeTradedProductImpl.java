// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeTradedProductImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.AbstractTradedProductImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlDbUtils;
import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            EqTypeProductManagerUtils, EqTypeServerConfigConstants

public class EqTypeTradedProductImpl extends AbstractTradedProductImpl
    implements EqTypeTradedProduct
{

    public EqTypeTradedProductImpl(TradedProductRow row)
        throws DataQueryException, DataNetworkException
    {
        super(row);
        m_eqtypeTradedProductRow = EqTypeProductManagerUtils.getEqtypeTradedProductRow(row.getTradedProductId());
    }

    public double getLastClosingPrice()
    {
        return m_eqtypeTradedProductRow.getLastClosingPrice();
    }

    public double getDailyStopHigh()
    {
        return getStopHighPrice();
    }

    public double getDailyStopLow()
    {
        return getStopLowPrice();
    }

    public double getStopHighPrice()
    {
        return m_eqtypeTradedProductRow.getStopHighPriceIsNull() ? 1.7976931348623157E+308D : m_eqtypeTradedProductRow.getStopHighPrice();
    }

    public double getStopLowPrice()
    {
        return m_eqtypeTradedProductRow.getStopLowPriceIsNull() ? 4.9406564584124654E-324D : m_eqtypeTradedProductRow.getStopLowPrice();
    }

    public boolean isMarginable()
    {
        return BooleanEnum.TRUE.equals(m_eqtypeTradedProductRow.getMarginableFlag());
    }

    public boolean isShortable()
    {
        return BooleanEnum.TRUE.equals(m_eqtypeTradedProductRow.getShortableFlag());
    }

    public Date getLastUpdatedTimestamp()
    {
        return m_eqtypeTradedProductRow.getLastUpdatedTimestamp();
    }

    public Product getProduct()
    {
        return GtlUtils.getTradingModule("eqtype").getProductManager().getProduct(m_eqtypeTradedProductRow.getProductId());
        NotFoundException ex;
        ex;
        String msg = "Cannot get product by product id : " + m_eqtypeTradedProductRow.getProductId();
        m_log.error(msg);
        throw new RuntimeSystemException(msg, ex);
    }

    public Date getListedDate()
    {
        return m_eqtypeTradedProductRow.getListedDate();
    }

    public Date getUnlistedDate()
    {
        return m_eqtypeTradedProductRow.getUnlistedDate();
    }

    public boolean isListedCurrently()
    {
        return BooleanEnum.TRUE.equals(m_eqtypeTradedProductRow.getListFlag());
    }

    public double getTickValueUnit()
    {
        return getTickValueUnit(getLastClosingPrice());
    }

    public double getTickValueUnit(double limitPrice)
    {
        Product p = getProduct();
        return GtlDbUtils.getTickValueUnit(p.getProductId(), p.getProductType(), m_eqtypeTradedProductRow.getMarketId(), limitPrice);
        DataException e;
        e;
        String msg = "Can not get Tick Value for EqType TradedProduct:" + getProduct().getProductId() + "/" + getMarket().getMarketId();
        m_log.error(msg, e);
        throw new RuntimeSystemException(msg, e);
    }

    public boolean isValidPriceAsPerTickValue(double price)
    {
        double tickValueUnit = getTickValueUnit(price);
        if(Double.isNaN(tickValueUnit))
        {
            return true;
        } else
        {
            boolean isAsPerTickValueUnit = (long)price % (long)tickValueUnit == 0L;
            return isAsPerTickValueUnit;
        }
    }

    public Object getDataSourceObject()
    {
        return m_eqtypeTradedProductRow;
    }

    public boolean isMiniStockTradable()
    {
        return BooleanEnum.TRUE.equals(m_eqtypeTradedProductRow.getMiniStockFlag());
    }

    protected int getDeliveryDateShiftDays()
    {
        return EqTypeServerConfigConstants.getDeliverDateShiftDays();
    }

    protected TradingModule getThisTradingModule()
    {
        return GtlUtils.getTradingModule("eqtype");
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
    private final EqtypeTradedProductRow m_eqtypeTradedProductRow;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeTradedProductImpl.class);
        DBG = m_log.ison();
    }
}

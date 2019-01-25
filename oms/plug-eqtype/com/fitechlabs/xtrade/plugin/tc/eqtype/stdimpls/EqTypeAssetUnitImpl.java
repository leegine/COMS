// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeAssetUnitImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeAssetUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetUnitDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetUnitRow;
import java.util.Date;

public class EqTypeAssetUnitImpl
    implements EqTypeAssetUnit
{

    public EqTypeAssetUnitImpl(long assetUnitId)
        throws DataQueryException, DataNetworkException
    {
        m_assetUnitId = assetUnitId;
        m_Row = AssetUnitDao.findRowByPk(assetUnitId);
    }

    public EqTypeAssetUnitImpl(AssetUnitRow row)
    {
        m_assetUnitId = row.getAssetUnitId();
        m_Row = row;
    }

    public long getAccountId()
    {
        return m_Row.getAccountId();
    }

    public double getAcquiredPrice()
    {
        return m_Row.getAcquiredPrice();
    }

    public Date getAcquiredTimestamp()
    {
        return m_Row.getAcquiredTimestamp();
    }

    public long getAssetId()
    {
        return m_Row.getAssetId();
    }

    public long getAssetUnitId()
    {
        return m_Row.getAssetUnitId();
    }

    public Date getDeliveryDate()
    {
        return m_Row.getDeliveryDate();
    }

    public double getOrignalQuantity()
    {
        return m_Row.getOriginalQuantity();
    }

    public double getQuantity()
    {
        return m_Row.getQuantity();
    }

    public long getSubAccountId()
    {
        return m_Row.getSubAccountId();
    }

    public Object getDataSourceObject()
    {
        return m_Row;
    }

    public TradedProduct getTradedProduct()
    {
        if(m_tradedProduct == null)
            try
            {
                TradingModule tm = GtlUtils.getTradingModule(getProductType());
                m_tradedProduct = tm.getProductManager().getTradedProduct(m_Row.getProductId(), m_Row.getMarketId());
            }
            catch(NotFoundException nfe)
            {
                String msg = "TradedProduct not found for productId/marketId : " + m_Row.getProductId() + "/" + m_Row.getMarketId() + ", for assetUnitId:" + getAssetUnitId();
                m_log.error(msg, nfe);
                throw new RuntimeSystemException(msg, nfe);
            }
        return m_tradedProduct;
    }

    public ProductTypeEnum getProductType()
    {
        return m_Row.getProductType();
    }

    protected MainAccount getMainAccount()
    {
        if(m_mainAccount == null)
            try
            {
                m_mainAccount = GtlUtils.getAccountManager().getMainAccount(getAccountId());
            }
            catch(NotFoundException nfe)
            {
                String msg = "MainAccount not found accountId : " + getAccountId() + ", for assetUnitID:" + getAssetUnitId();
                m_log.error(msg, nfe);
                throw new RuntimeSystemException(msg, nfe);
            }
        return m_mainAccount;
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private final long m_assetUnitId;
    protected final AssetUnitRow m_Row;
    private static final Logit m_log;
    private static final boolean DBG;
    private MainAccount m_mainAccount;
    private TradedProduct m_tradedProduct;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeAssetUnitImpl.class);
        DBG = m_log.ison();
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeAssetImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeAsset;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeAssetUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            EqTypeAssetUnitImpl

public class EqTypeAssetImpl
    implements EqTypeAsset
{

    public EqTypeAssetImpl(long assetId)
        throws DataQueryException, DataNetworkException
    {
        m_product = null;
        m_assetId = assetId;
        m_Row = AssetDao.findRowByPk(assetId);
    }

    public EqTypeAssetImpl(AssetRow row)
    {
        m_product = null;
        m_assetId = row.getAssetId();
        m_Row = row;
    }

    public long getAssetId()
    {
        return m_Row.getAssetId();
    }

    public double getAveragePrice()
    {
        double price = 0.0D;
        double quantity = m_Row.getQuantity();
        double bookValue = m_Row.getBookValue();
        if(quantity == 0.0D)
            return 0.0D;
        else
            return bookValue / quantity;
    }

    public double getBookValue()
    {
        return m_Row.getBookValue();
    }

    public double getManagementFee()
    {
        return m_Row.getManagementFee();
    }

    public double getManagementFeeTax()
    {
        return m_Row.getManagementFeeTax();
    }

    public double getSetupFee()
    {
        return m_Row.getSetupFee();
    }

    public double getSetupFeeTax()
    {
        return m_Row.getSetupFeeTax();
    }

    public Product getProduct()
    {
        if(m_product == null)
            try
            {
                TradingModule tm = GtlUtils.getTradingModule(getProductType());
                m_product = tm.getProductManager().getProduct(m_Row.getProductId());
            }
            catch(NotFoundException nfe)
            {
                String msg = "Product not found for Id : " + m_Row.getProductId() + ", for assetId:" + getAssetId();
                m_log.error(msg, nfe);
                throw new RuntimeSystemException(msg, nfe);
            }
        return m_product;
    }

    public ProductTypeEnum getProductType()
    {
        return m_Row.getProductType();
    }

    public double getQuantity()
    {
        return m_Row.getQuantity();
    }

    public SubAccount getSubAccount()
    {
        FinApp finApp = (FinApp)Services.getService(com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp.class);
        AccountManager acctMgr = finApp.getAccountManager();
        SubAccount mSubAccount = null;
        try
        {
            mSubAccount = acctMgr.getSubAccount(m_Row.getAccountId(), m_Row.getSubAccountId());
        }
        catch(NotFoundException nfe)
        {
            m_log.error(nfe.getMessage(), nfe);
            throw new RuntimeSystemException("SubAccount object could not be obtained for account_id/sub_account_id : " + m_Row.getAccountId() + "/" + m_Row.getSubAccountId(), nfe);
        }
        return mSubAccount;
    }

    public TaxTypeEnum getTaxType()
    {
        return m_Row.getTaxType();
    }

    public AssetUnit[] getAssetUnits()
    {
        List rows;
        try
        {
            rows = AssetUnitDao.findRowsByAssetId(getAssetId());
        }
        catch(DataException e)
        {
            throw new RuntimeSystemException("Exception while getting AssetUnitRows from asset_unit table for asset_id:" + getAssetId(), e);
        }
        List objs = new ArrayList();
        for(int i = 0; i < rows.size(); i++)
        {
            AssetUnitRow row = (AssetUnitRow)rows.get(i);
            objs.add(toAssetUnit(row));
        }

        return (EqTypeAssetUnit[])objs.toArray(new EqTypeAssetUnit[0]);
    }

    protected EqTypeAssetUnit toAssetUnit(Row row)
    {
        return new EqTypeAssetUnitImpl((AssetUnitRow)row);
    }

    public double getLockedQuantity()
    {
        double locked_quantity;
        try
        {
            LockedAssetDetailsRow row = LockedAssetDetailsDao.findRowByPk(getAssetId());
            locked_quantity = row.getLockedQuantity();
        }
        catch(DataFindException e)
        {
            locked_quantity = 0.0D;
        }
        catch(DataException e)
        {
            throw new RuntimeSystemException("Exception while getting LockedAssetDetailsRow from locked_asset_details table for asset_id:" + getAssetId(), e);
        }
        return locked_quantity;
    }

    public Object getDataSourceObject()
    {
        return m_Row;
    }

    private final long m_assetId;
    protected final AssetRow m_Row;
    private static final Logit m_log;
    private static final boolean DBG;
    private Product m_product;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeAssetImpl.class);
        DBG = m_log.ison();
    }
}

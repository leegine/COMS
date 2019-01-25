// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeContractImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContract;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import java.util.Date;

public class EqTypeContractImpl
    implements EqTypeContract
{

    public EqTypeContractImpl(long contractId)
        throws DataQueryException, DataNetworkException
    {
        m_contractId = contractId;
        m_Row = EqtypeContractDao.findRowByPk(contractId);
    }

    public EqTypeContractImpl(EqtypeContractRow row)
    {
        m_contractId = row.getContractId();
        m_Row = row;
    }

    public Date getCloseDate()
    {
        return m_Row.getCloseDate();
    }

    public long getContractId()
    {
        return m_Row.getContractId();
    }

    public double getOriginalContractPrice()
    {
        return m_Row.getOriginalContractPrice();
    }

    public double getContractPrice()
    {
        return m_Row.getContractPrice();
    }

    public double getInterestFee()
    {
        return m_Row.getInterestFee();
    }

    public double getInterestFeeTax()
    {
        return m_Row.getInterestFeeTax();
    }

    public boolean isLong()
    {
        return m_Row.getContractType().equals(ContractTypeEnum.LONG);
    }

    public boolean isShort()
    {
        return m_Row.getContractType().equals(ContractTypeEnum.SHORT);
    }

    public double getManagementFee()
    {
        return m_Row.getManagementFee();
    }

    public double getManagementFeeTax()
    {
        return m_Row.getManagementFeeTax();
    }

    public long getMarketId()
    {
        return m_Row.getMarketId();
    }

    public Date getOpenDate()
    {
        return m_Row.getOpenDate();
    }

    public double getOriginalQuantity()
    {
        return m_Row.getOriginalQuantity();
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
                String msg = "Product not found for Id : " + m_Row.getProductId() + ", for contractId:" + getContractId();
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

    public double getLockedQuantity()
    {
        double locked_quantity;
        try
        {
            EqtypeLockedContractDetailsRow row = EqtypeLockedContractDetailsDao.findRowByPk(getContractId());
            locked_quantity = row.getLockedQuantity();
        }
        catch(DataFindException e)
        {
            locked_quantity = 0.0D;
        }
        catch(DataException e)
        {
            throw new RuntimeSystemException("Exception while getting EqtypeLockedContractDetailsRow from eqtype_locked_contract_details table for contract_id:" + getContractId(), e);
        }
        return locked_quantity;
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
                String msg = "TradedProduct not found for productId/marketId : " + m_Row.getProductId() + "/" + m_Row.getMarketId() + ", for contractId:" + getContractId();
                m_log.error(msg, nfe);
                throw new RuntimeSystemException(msg, nfe);
            }
        return m_tradedProduct;
    }

    public Object getDataSourceObject()
    {
        return m_Row;
    }

    private final long m_contractId;
    protected final EqtypeContractRow m_Row;
    private static final Logit m_log;
    private static final boolean DBG;
    private Product m_product;
    private TradedProduct m_tradedProduct;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeContractImpl.class);
        DBG = m_log.ison();
    }
}

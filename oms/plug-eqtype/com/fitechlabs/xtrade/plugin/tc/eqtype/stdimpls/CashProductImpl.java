// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CashProductImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.CashProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.AbstractProductImpl;

public class CashProductImpl extends AbstractProductImpl
    implements CashProduct
{

    public Market getPrimaryMarket()
    {
        throw new UnsupportedOperationException("This method is unsupported for CashProduct");
    }

    public TradedProduct getPrimaryTradedProduct()
    {
        throw new UnsupportedOperationException("This method is unsupported for CashProduct");
    }

    public Institution getInstitution()
    {
        throw new UnsupportedOperationException("This method is unsupported for CashProduct");
    }

    public CashProductImpl(long productId)
        throws DataQueryException, DataNetworkException
    {
        super(productId);
    }

    public CashProductImpl(ProductRow row)
    {
        super(row);
    }

    public Object getDataSourceObject()
    {
        return null;
    }

    protected TradingModule getThisTradingModule()
    {
        return GtlUtils.getTradingModule("eqtype");
    }
}

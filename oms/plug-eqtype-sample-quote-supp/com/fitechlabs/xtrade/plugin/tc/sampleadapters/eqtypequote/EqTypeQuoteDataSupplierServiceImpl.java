// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeQuoteDataSupplierServiceImpl.java

package com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote:
//            AppLevelEqTypeQuoteDataImpl

final class EqTypeQuoteDataSupplierServiceImpl
    implements EqTypeQuoteDataSupplierService
{

    public EqTypeQuoteDataSupplierServiceImpl()
    {
    }

    public EqTypeQuoteData getEqTypeQuote(EqTypeTradedProduct tradedProduct)
        throws NotFoundException
    {
        EqTypeQuoteData q = new AppLevelEqTypeQuoteDataImpl(tradedProduct);
        return q;
    }

    public QuoteData getQuote(TradedProduct tradedProduct)
        throws NotFoundException
    {
        if(tradedProduct instanceof EqTypeTradedProduct)
            return getEqTypeQuote((EqTypeTradedProduct)tradedProduct);
        else
            throw new IllegalArgumentException("Product is not an instance of EqTypeTradedProduct.");
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

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.EqTypeQuoteDataSupplierServiceImpl.class);
        DBG = m_log.ison();
    }
}

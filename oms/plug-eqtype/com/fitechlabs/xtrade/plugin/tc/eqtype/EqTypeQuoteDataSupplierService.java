// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeQuoteDataSupplierService.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;

import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuoteDataSupplierService;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype:
//            EqTypeTradedProduct, EqTypeQuoteData

public interface EqTypeQuoteDataSupplierService
    extends QuoteDataSupplierService
{

    public abstract EqTypeQuoteData getEqTypeQuote(EqTypeTradedProduct eqtypetradedproduct)
        throws NotFoundException;
}

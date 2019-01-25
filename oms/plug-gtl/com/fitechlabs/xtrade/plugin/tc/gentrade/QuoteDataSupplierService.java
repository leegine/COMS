// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QuoteDataSupplierService.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            NotFoundException, TradedProduct, QuoteData

public interface QuoteDataSupplierService
{

    public abstract QuoteData getQuote(TradedProduct tradedproduct)
        throws NotFoundException;
}

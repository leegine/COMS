// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SampleMainEqTypeQuoteService.java

package com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote:
//            AppLevelEqTypeQuoteData

public interface SampleMainEqTypeQuoteService
    extends Service
{

    public abstract AppLevelEqTypeQuoteData getAppLevelEqTypeQuoteData(EqTypeTradedProduct eqtypetradedproduct)
        throws NotFoundException;
}

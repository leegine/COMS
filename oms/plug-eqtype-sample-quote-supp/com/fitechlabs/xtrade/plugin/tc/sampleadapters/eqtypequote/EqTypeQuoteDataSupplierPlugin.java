// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeQuoteDataSupplierPlugin.java

package com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.data.EqTypeSampleQuoteDataSupplierMasterDatabaseExtensions;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote:
//            EqTypeQuoteDataSupplierServiceImpl, AppLevelEqTypeQuoteDataImpl, SampleMainEqTypeQuoteService, AppLevelEqTypeQuoteData

public final class EqTypeQuoteDataSupplierPlugin extends Plugin
{

    private EqTypeQuoteDataSupplierPlugin()
    {
    }

    public static void plug()
        throws Exception
    {
        plug(com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.EqTypeQuoteDataSupplierPlugin.class);
    }

    public static void onPlug()
        throws Exception
    {
        KernelPlugin.plug();
        EqTypeSampleQuoteDataSupplierMasterDatabaseExtensions.plug();
        Services.registerService(com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.SampleMainEqTypeQuoteService.class, new SampleMainEqTypeQuoteService() {

            public AppLevelEqTypeQuoteData getAppLevelEqTypeQuoteData(EqTypeTradedProduct tradedProduct)
                throws NotFoundException
            {
                AppLevelEqTypeQuoteData q = new AppLevelEqTypeQuoteDataImpl(tradedProduct);
                return q;
            }

        }
);
        m_log.info("Registered SampleMainEqTypeQuoteService service.");
        m_log.info("Getting FinApp");
        FinApp finApp = (FinApp)Services.getService(com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp.class);
        m_log.info("Getting eqtype TradingModule");
        TradingModule tm = finApp.getTradingModule("eqtype");
        m_log.info("Installing quote supplier service : " + (com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.EqTypeQuoteDataSupplierServiceImpl.class));
        tm.installQuoteDataSupplierService(new EqTypeQuoteDataSupplierServiceImpl());
        m_log.info("Installed quote supplier service ok!!!. ");
        m_log.info("EqTypeQuoteDataSupplierPlugin started !!!");
    }

    private static final Logit m_log;
    private static final boolean DBG;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.EqTypeQuoteDataSupplierPlugin.class);
        DBG = m_log.ison();
    }
}

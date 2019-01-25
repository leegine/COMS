// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeSampleQuoteDataSupplierMasterDatabaseExtensions.java

package com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.data;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.data.DataAccessObject;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.data:
//            EqtypeSampleQuoteDataDao

public final class EqTypeSampleQuoteDataSupplierMasterDatabaseExtensions extends Plugin
{

    private EqTypeSampleQuoteDataSupplierMasterDatabaseExtensions()
    {
    }

    public static void plug()
        throws Exception
    {
        plug(com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.data.EqTypeSampleQuoteDataSupplierMasterDatabaseExtensions.class);
    }

    public static void onPlug()
        throws Exception
    {
        KernelPlugin.plug();
        regClasses();
        regDbExtensions();
        regDataObjectClasses();
    }

    private static void regClasses()
        throws Exception
    {
        regClass(com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.data.EqtypeSampleQuoteDataPK.class);
        regClass(com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.data.EqtypeSampleQuoteDataParams.class);
    }

    private static void regDbExtensions()
        throws Exception
    {
        regDbExtension("master", "eqtype_sample_quote_data", com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.data.EqtypeSampleQuoteDataPK.class, com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.data.EqtypeSampleQuoteDataParams.class, null, null);
    }

    private static void regDataObjectClasses()
        throws Exception
    {
        regDao(com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.data.EqtypeSampleQuoteDataRow.class, EqtypeSampleQuoteDataDao.FACTORY);
    }
}

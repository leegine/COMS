// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KernelDBExtensions.java

package com.fitechlabs.xtrade.kernel.data.db;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.data.DataAccessObject;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data.db:
//            ServerConfigDao, ServerLicenseDao, SegmentUrlDao, AccountSegmentDao, 
//            ErrorMessageDao

public final class KernelDBExtensions extends Plugin
{

    private KernelDBExtensions()
    {
    }

    public static void plug()
        throws Exception
    {
        Plugin.plug(com.fitechlabs.xtrade.kernel.data.db.KernelDBExtensions.class);
    }

    public static void onPlug()
        throws Exception
    {
        regClasses();
        regDbExtensions();
        regDataObjectClasses();
    }

    private static void regClasses()
        throws Exception
    {
        Plugin.regClass(com.fitechlabs.xtrade.kernel.data.db.ServerConfigPK.class);
        Plugin.regClass(com.fitechlabs.xtrade.kernel.data.db.ServerConfigParams.class);
        Plugin.regClass(com.fitechlabs.xtrade.kernel.data.db.ServerLicensePK.class);
        Plugin.regClass(com.fitechlabs.xtrade.kernel.data.db.ServerLicenseParams.class);
        Plugin.regClass(com.fitechlabs.xtrade.kernel.data.db.SegmentUrlPK.class);
        Plugin.regClass(com.fitechlabs.xtrade.kernel.data.db.SegmentUrlParams.class);
        Plugin.regClass(com.fitechlabs.xtrade.kernel.data.db.AccountSegmentPK.class);
        Plugin.regClass(com.fitechlabs.xtrade.kernel.data.db.AccountSegmentParams.class);
        Plugin.regClass(com.fitechlabs.xtrade.kernel.data.db.ErrorMessagePK.class);
        Plugin.regClass(com.fitechlabs.xtrade.kernel.data.db.ErrorMessageParams.class);
    }

    private static void regDbExtensions()
        throws Exception
    {
        Plugin.regDbExtension("config", "server_config", com.fitechlabs.xtrade.kernel.data.db.ServerConfigPK.class, com.fitechlabs.xtrade.kernel.data.db.ServerConfigParams.class, null, null);
        Plugin.regDbExtension("config", "server_license", com.fitechlabs.xtrade.kernel.data.db.ServerLicensePK.class, com.fitechlabs.xtrade.kernel.data.db.ServerLicenseParams.class, null, null);
        Plugin.regDbExtension("config", "segment_url", com.fitechlabs.xtrade.kernel.data.db.SegmentUrlPK.class, com.fitechlabs.xtrade.kernel.data.db.SegmentUrlParams.class, null, null);
        Plugin.regDbExtension("config", "account_segment", com.fitechlabs.xtrade.kernel.data.db.AccountSegmentPK.class, com.fitechlabs.xtrade.kernel.data.db.AccountSegmentParams.class, null, null);
        Plugin.regDbExtension("config", "error_message", com.fitechlabs.xtrade.kernel.data.db.ErrorMessagePK.class, com.fitechlabs.xtrade.kernel.data.db.ErrorMessageParams.class, null, null);
    }

    private static void regDataObjectClasses()
        throws Exception
    {
        Plugin.regDao(com.fitechlabs.xtrade.kernel.data.db.ServerConfigRow.class, ServerConfigDao.FACTORY);
        Plugin.regDao(com.fitechlabs.xtrade.kernel.data.db.ServerLicenseRow.class, ServerLicenseDao.FACTORY);
        Plugin.regDao(com.fitechlabs.xtrade.kernel.data.db.SegmentUrlRow.class, SegmentUrlDao.FACTORY);
        Plugin.regDao(com.fitechlabs.xtrade.kernel.data.db.AccountSegmentRow.class, AccountSegmentDao.FACTORY);
        Plugin.regDao(com.fitechlabs.xtrade.kernel.data.db.ErrorMessageRow.class, ErrorMessageDao.FACTORY);
    }
}

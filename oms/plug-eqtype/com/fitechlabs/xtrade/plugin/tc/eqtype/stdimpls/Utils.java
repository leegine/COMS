// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Utils.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

public class Utils
{

    public Utils()
    {
    }

    public static TradingModule getTradingModule()
    {
        return GtlUtils.getTradingModule("eqtype");
    }

    public static EqTypeProductManager getProductManager()
    {
        return (EqTypeProductManager)getTradingModule().getProductManager();
    }

    public static EqTypeOrderManager getOrderManager()
    {
        return (EqTypeOrderManager)getTradingModule().getOrderManager();
    }

    public static EqTypePositionManager getPositionManager()
    {
        return (EqTypePositionManager)getTradingModule().getPositionManager();
    }

    public static EqTypeBizLogicProvider getBizLogicProvider()
    {
        return (EqTypeBizLogicProvider)getTradingModule().getBizLogicProvider();
    }

    public static EqTypeMarketRequestSenderService getMarketSenderService()
    {
        EqTypeMarketRequestSenderService marketSenderSvc = (EqTypeMarketRequestSenderService)getTradingModule().getMarketAdapter().getMarketRequestSenderServce();
        return marketSenderSvc;
        NotInstalledException nie;
        nie;
        String msg = "EqtypeMarketSenderService is probably not installed.";
        m_log.error("EqtypeMarketSenderService is probably not installed.", nie);
        throw new RuntimeSystemException("EqtypeMarketSenderService is probably not installed.", nie);
    }

    public static EqTypeOrderManager getGlobalOrderManager()
    {
        return (EqTypeOrderManager)GtlUtils.getTradingModule("eqtype").getOrderManager();
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
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.Utils.class);
        DBG = m_log.ison();
    }
}

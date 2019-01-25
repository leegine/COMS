// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OrderSubmitContextIndicator.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.util.log.Logit;

public class OrderSubmitContextIndicator
{

    private OrderSubmitContextIndicator()
    {
    }

    public static boolean isValidateCalledFromSubmit()
    {
        return Boolean.TRUE.equals(m_validateCallingContextHolde.get());
    }

    public static void setSubmitContext()
    {
        m_validateCallingContextHolde.set(Boolean.TRUE);
    }

    public static void clearSubmitContext()
    {
        m_validateCallingContextHolde.set(null);
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
    private static final ThreadLocal m_validateCallingContextHolde = new ThreadLocal();

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.OrderSubmitContextIndicator.class);
        DBG = m_log.ison();
    }
}

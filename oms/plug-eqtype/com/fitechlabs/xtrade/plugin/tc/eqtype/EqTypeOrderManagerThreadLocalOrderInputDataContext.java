// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeOrderManagerThreadLocalOrderInputDataContext.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerThreadLocalOrderInputDataContext;

/**
 * @deprecated Class EqTypeOrderManagerThreadLocalOrderInputDataContext is deprecated
 */

public class EqTypeOrderManagerThreadLocalOrderInputDataContext extends OrderManagerThreadLocalOrderInputDataContext
{

    protected EqTypeOrderManagerThreadLocalOrderInputDataContext()
    {
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
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManagerThreadLocalOrderInputDataContext.class);
        DBG = m_log.ison();
    }
}

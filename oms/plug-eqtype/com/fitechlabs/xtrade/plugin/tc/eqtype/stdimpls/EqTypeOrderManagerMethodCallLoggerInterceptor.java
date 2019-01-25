// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeOrderManagerMethodCallLoggerInterceptor.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlAbstractMethodLevelInterceptor;
import java.lang.reflect.Method;

public class EqTypeOrderManagerMethodCallLoggerInterceptor extends GtlAbstractMethodLevelInterceptor
    implements Interceptor
{

    public EqTypeOrderManagerMethodCallLoggerInterceptor()
    {
        super(com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager.class, new String[] {
            "validateCancelOrder", "validateChangeOrder", "validateNewOrder", "submitCancelOrder", "submitChangeOrder", "submitNewOrder"
        });
    }

    public Object onCall(Method method, Object arguments[])
    {
        if(isMethodInterceptable(method))
        {
            m_eqtypeOrderManagerCtxIndicator.set(Boolean.TRUE);
            return Boolean.TRUE;
        } else
        {
            return null;
        }
    }

    public void onReturn(Object context, Object returnValue)
    {
        if(Boolean.TRUE.equals(context))
            clearFlag();
    }

    public void onThrowable(Object context, Throwable thrownObject)
    {
        if(Boolean.TRUE.equals(context))
            clearFlag();
    }

    public static boolean isCalledViaEqTypeOrderManager()
    {
        return m_eqtypeOrderManagerCtxIndicator.get() != null;
    }

    private static void clearFlag()
    {
        m_eqtypeOrderManagerCtxIndicator.set(null);
    }

    private static final Logit m_log;
    private static final boolean DBG;
    private static final ThreadLocal m_eqtypeOrderManagerCtxIndicator = new ThreadLocal();

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderManagerMethodCallLoggerInterceptor.class);
        DBG = m_log.ison();
    }
}

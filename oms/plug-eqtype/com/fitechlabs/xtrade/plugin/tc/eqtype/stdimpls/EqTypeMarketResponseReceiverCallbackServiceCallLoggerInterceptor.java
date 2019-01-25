// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeMarketResponseReceiverCallbackServiceCallLoggerInterceptor.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlAbstractMethodLevelInterceptor;
import java.lang.reflect.Method;

public class EqTypeMarketResponseReceiverCallbackServiceCallLoggerInterceptor extends GtlAbstractMethodLevelInterceptor
    implements Interceptor
{

    public EqTypeMarketResponseReceiverCallbackServiceCallLoggerInterceptor()
    {
        super(com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeMarketResponseReceiverCallbackService.class, null);
    }

    public Object onCall(Method method, Object arguments[])
    {
        if(isMethodInterceptable(method))
        {
            m_ctxIndicator.set(Boolean.TRUE);
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

    public static boolean isCalledViaEqTypeMarketResponseReceiver()
    {
        return m_ctxIndicator.get() != null;
    }

    private static void clearFlag()
    {
        m_ctxIndicator.set(null);
    }

    private static final Logit m_log;
    private static final boolean DBG;
    private static final ThreadLocal m_ctxIndicator = new ThreadLocal();

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeMarketResponseReceiverCallbackServiceCallLoggerInterceptor.class);
        DBG = m_log.ison();
    }
}

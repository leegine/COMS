// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeOrderManagerCtxCheckerInterceptor.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlAbstractMethodLevelInterceptor;
import java.lang.reflect.Method;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            EqTypeOrderManagerMethodCallLoggerInterceptor

public class EqTypeOrderManagerCtxCheckerInterceptor extends GtlAbstractMethodLevelInterceptor
    implements Interceptor
{

    public EqTypeOrderManagerCtxCheckerInterceptor()
    {
        super(com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager.class, new String[] {
            "validateCancelOrder", "validateChangeOrder", "validateNewOrder", "submitCancelOrder", "submitChangeOrder", "submitNewOrder"
        });
    }

    public Object onCall(Method method, Object arguments[])
    {
        if(isMethodInterceptable(method) && !EqTypeOrderManagerMethodCallLoggerInterceptor.isCalledViaEqTypeOrderManager())
            throw new IllegalStateException("Method should be called via EqTypeOrderManager.");
        else
            return null;
    }

    public void onReturn(Object obj, Object obj1)
    {
    }

    public void onThrowable(Object obj, Throwable throwable)
    {
    }

    private static final Logit m_log;
    private static final boolean DBG;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderManagerCtxCheckerInterceptor.class);
        DBG = m_log.ison();
    }
}

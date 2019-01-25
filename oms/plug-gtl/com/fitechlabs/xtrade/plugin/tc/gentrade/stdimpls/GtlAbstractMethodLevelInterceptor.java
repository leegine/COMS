// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GtlAbstractMethodLevelInterceptor.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class GtlAbstractMethodLevelInterceptor
{

    protected GtlAbstractMethodLevelInterceptor(Class classToIntercept, String methodsToIntercept[])
    {
        m_methodsToIntercept = null;
        if(methodsToIntercept == null)
            return;
        Method methods[] = classToIntercept.getMethods();
        Map validMethodsTable = new HashMap();
        for(int i = 0; i < methods.length; i++)
            validMethodsTable.put(methods[i].getName(), Boolean.TRUE);

        m_methodsToIntercept = new HashMap();
        for(int i = 0; i < methodsToIntercept.length; i++)
        {
            if(!validMethodsTable.containsKey(methodsToIntercept[i]))
                throw new IllegalArgumentException("method : " + methodsToIntercept[i] + " - is not valid !!!");
            m_methodsToIntercept.put(methodsToIntercept[i], Boolean.TRUE);
        }

    }

    protected boolean isMethodInterceptable(Method m)
    {
        return m_methodsToIntercept == null || m_methodsToIntercept.containsKey(m.getName());
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
    private Map m_methodsToIntercept;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlAbstractMethodLevelInterceptor.class);
        DBG = m_log.ison();
    }
}

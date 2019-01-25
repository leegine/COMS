// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GenericOrderManagerInterceptor.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls:
//            GtlAbstractMethodLevelInterceptor

public class GenericOrderManagerInterceptor extends GtlAbstractMethodLevelInterceptor
    implements Interceptor
{

    public GenericOrderManagerInterceptor(Class classToIntercept, String methodsToIntercept[])
    {
        super(classToIntercept, methodsToIntercept);
        m_classToIntercept = classToIntercept;
    }

    public Object onCall(Method method, Object arguments[])
    {
        Object txiContext;
        if(!isMethodInterceptable(method))
            return null;
        if(DBG)
            m_log.debug(m_classToIntercept.toString() + "." + method.getName() + " - being intercepted");
        txiContext = txi.onCall(method, arguments);
        Map contextValues;
        contextValues = new HashMap();
        contextValues.put("txi_context", txiContext);
        if(DBG)
            m_log.debug("Tx started ");
        if(arguments != null && arguments.length > 0 && (arguments[0] instanceof SubAccount))
        {
            SubAccount subAccount = (SubAccount)arguments[0];
            if(DBG)
                m_log.debug("Serializing operations of account : " + subAccount.getSubAccountId());
            subAccount.serializeOperationsWithWait();
            if(DBG)
                m_log.debug("Serializing done.");
        }
        return contextValues;
        Throwable t;
        t;
        try
        {
            txi.onThrowable(txiContext, t);
        }
        catch(Throwable tt)
        {
            m_log.error(tt.getMessage(), tt);
        }
        m_log.error(t.getMessage(), t);
        throw new RuntimeSystemException(t.getMessage(), t);
    }

    public void onReturn(Object context, Object returnValue)
    {
        if(context != null)
        {
            boolean commit = false;
            if(returnValue instanceof ProcessingResultHolder)
                commit = ((ProcessingResultHolder)returnValue).getProcessingResult().isSuccessfulResult();
            Object txiContext = ((Map)context).get("txi_context");
            if(commit)
            {
                txi.onReturn(txiContext, returnValue);
            } else
            {
                txi.onThrowable(txiContext, null);
                if(DBG)
                    m_log.debug("**** Rollback transaction ****");
            }
        }
    }

    public void onThrowable(Object context, Throwable thrownObject)
    {
        if(context != null)
        {
            Object txiContext = ((Map)context).get("txi_context");
            txi.onThrowable(txiContext, thrownObject);
        }
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
    private static final String TXI_CONTEXT_VAR_NAME = "txi_context";
    private final TransactionalInterceptor txi = new TransactionalInterceptor(1);
    private final Class m_classToIntercept;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GenericOrderManagerInterceptor.class);
        DBG = m_log.ison();
    }
}

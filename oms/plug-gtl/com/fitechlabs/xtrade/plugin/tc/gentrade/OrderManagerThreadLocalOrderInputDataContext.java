// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OrderManagerThreadLocalOrderInputDataContext.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            SubAccount

public class OrderManagerThreadLocalOrderInputDataContext
{

    protected OrderManagerThreadLocalOrderInputDataContext()
    {
    }

    public static SubAccount getSubAccountParam()
    {
        return (SubAccount)getContextHolderAsMap().get("SUB_ACCOUNT");
    }

    public static Object getSpecParam()
    {
        return getContextHolderAsMap().get("SPEC");
    }

    protected static Map getContextHolderAsMap()
    {
        return (Map)m_dataContext.get();
    }

    public static boolean isNewOrderContext()
    {
        return Boolean.TRUE.equals(getContextHolderAsMap().get("IS_NEW_ORDER"));
    }

    public static boolean isCancelOrderContext()
    {
        return Boolean.TRUE.equals(getContextHolderAsMap().get("IS_CANCEL_ORDER"));
    }

    public static boolean isChangeOrderContext()
    {
        return Boolean.TRUE.equals(getContextHolderAsMap().get("IS_CHANGE_ORDER"));
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
    private static final ThreadLocal m_dataContext = new ThreadLocal() {

        protected Object initialValue()
        {
            return new HashMap();
        }

    }
;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerThreadLocalOrderInputDataContext.class);
        DBG = m_log.ison();
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OrderManagerThreadLocalOrderInputDataContextSetter.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerThreadLocalOrderInputDataContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import java.util.Map;

public class OrderManagerThreadLocalOrderInputDataContextSetter extends OrderManagerThreadLocalOrderInputDataContext
{

    private OrderManagerThreadLocalOrderInputDataContextSetter()
    {
    }

    private static void setSubAccountParam(SubAccount subAccount)
    {
        getContextHolderAsMap().put("SUB_ACCOUNT", subAccount);
    }

    private static void setSpecParam(Object spec)
    {
        getContextHolderAsMap().put("SPEC", spec);
    }

    private static void setToNewOrderContext()
    {
        getContextHolderAsMap().put("IS_NEW_ORDER", Boolean.TRUE);
    }

    private static void setToCancelOrderContext()
    {
        getContextHolderAsMap().put("IS_CANCEL_ORDER", Boolean.TRUE);
    }

    private static void setToChangeOrderContext()
    {
        getContextHolderAsMap().put("IS_CHANGE_ORDER", Boolean.TRUE);
    }

    public static void setNewOrderSubAccountAndSpecParams(SubAccount subAccount, Object spec)
    {
        setSubAccountParam(subAccount);
        setSpecParam(spec);
        setToNewOrderContext();
    }

    public static void setChangeOrderSubAccountAndSpecParams(SubAccount subAccount, Object spec)
    {
        setSubAccountParam(subAccount);
        setSpecParam(spec);
        setToChangeOrderContext();
    }

    public static void setCancelOrderSubAccountAndSpecParams(SubAccount subAccount, Object spec)
    {
        setSubAccountParam(subAccount);
        setSpecParam(spec);
        setToCancelOrderContext();
    }

    public static void clearContext()
    {
        getContextHolderAsMap().clear();
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
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.OrderManagerThreadLocalOrderInputDataContextSetter.class);
        DBG = m_log.ison();
    }
}

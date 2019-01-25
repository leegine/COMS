// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultEqTypeOrderManagerPersistenceEventInterceptor.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;

import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype:
//            EqTypeOrderManagerPersistenceEventInterceptor

public class DefaultEqTypeOrderManagerPersistenceEventInterceptor
    implements EqTypeOrderManagerPersistenceEventInterceptor
{

    public DefaultEqTypeOrderManagerPersistenceEventInterceptor()
    {
    }

    public EqtypeOrderUnitParams mutate(OrderManagerPersistenceType persistenceType, OrderManagerPersistenceContext persistenceContext, EqtypeOrderUnitParams beforeOrderUnitParams)
    {
        return beforeOrderUnitParams;
    }

    public EqtypeOrderExecutionParams mutate(OrderManagerPersistenceType persistenceType, OrderManagerPersistenceContext persistenceContext, EqtypeOrderExecutionParams beforeOrderExecParams)
    {
        return beforeOrderExecParams;
    }

    public BatchedQuery getQueryToExecute(OrderManagerPersistenceType persistenceType, Class tableRowClass)
    {
        return null;
    }

    public EqtypeOrderActionParams mutate(OrderManagerPersistenceType persistenceType, OrderManagerPersistenceContext persistenceContext, EqtypeOrderActionParams beforeOrderActionParams)
    {
        return beforeOrderActionParams;
    }
}

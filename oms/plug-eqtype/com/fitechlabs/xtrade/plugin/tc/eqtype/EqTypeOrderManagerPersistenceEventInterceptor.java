// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeOrderManagerPersistenceEventInterceptor.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;

import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;

public interface EqTypeOrderManagerPersistenceEventInterceptor
{

    public abstract EqtypeOrderUnitParams mutate(OrderManagerPersistenceType ordermanagerpersistencetype, OrderManagerPersistenceContext ordermanagerpersistencecontext, EqtypeOrderUnitParams eqtypeorderunitparams);

    public abstract EqtypeOrderExecutionParams mutate(OrderManagerPersistenceType ordermanagerpersistencetype, OrderManagerPersistenceContext ordermanagerpersistencecontext, EqtypeOrderExecutionParams eqtypeorderexecutionparams);

    public abstract BatchedQuery getQueryToExecute(OrderManagerPersistenceType ordermanagerpersistencetype, Class class1);

    public abstract EqtypeOrderActionParams mutate(OrderManagerPersistenceType ordermanagerpersistencetype, OrderManagerPersistenceContext ordermanagerpersistencecontext, EqtypeOrderActionParams eqtypeorderactionparams);
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PersistenceManager.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.PersistenceManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import java.util.List;
import java.util.Properties;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype:
//            EqTypeOrder, EqTypeOrderUnit, EqTypeOrderAction, EqTypeOrderExecution, 
//            EqTypeClosingContractSpec, EqTypeFinTransaction

public abstract class PersistenceManager
{

    public PersistenceManager()
    {
    }

    public static PersistenceManager getInstance(Properties properties)
    {
        if(instance == null)
            instance = new PersistenceManagerImpl();
        return instance;
    }

    public static PersistenceManager getInstance()
    {
        return m_defaultInstance;
    }

    public abstract EqTypeOrder findEqTypeOrder(long l)
        throws NotFoundException;

    public abstract List findEqTypeOrders(String s, String s1, String s2, Object aobj[], int i, int j);

    public abstract EqTypeOrderUnit findEqTypeOrderUnit(long l)
        throws NotFoundException;

    public abstract List findEqTypeOrderUnits(String s, String s1, String s2, Object aobj[], int i, int j);

    public abstract EqTypeOrderAction findEqTypeOrderAction(long l)
        throws NotFoundException;

    public abstract List findEqTypeOrderActions(String s, String s1, String s2, Object aobj[], int i, int j);

    public abstract EqTypeOrderExecution findEqTypeOrderExecution(long l)
        throws NotFoundException;

    public abstract EqTypeOrderExecution findEqTypeOrderExecution(long l, String s, Object aobj[])
        throws NotFoundException;

    public abstract List findEqTypeOrderExecutions(String s, String s1, String s2, Object aobj[], int i, int j);

    public abstract EqTypeClosingContractSpec findEqTypeClosingContractSpec(long l)
        throws NotFoundException;

    public abstract EqTypeFinTransaction findEqTypeFinTransaction(long l)
        throws NotFoundException;

    public abstract EqTypeFinTransaction findEqTypeFinTransaction(long l, String s, Object aobj[])
        throws NotFoundException;

    public abstract List findEqTypeFinTransactions(String s, String s1, String s2, Object aobj[], int i, int j);

    public abstract long createNewOrderId();

    public abstract long createNewOrderUnitId();

    public abstract int newEqTypeFinTransaction(EqTypeFinTransaction eqtypefintransaction);

    public abstract int removeEqTypeFinTransaction(long l);

    public abstract EqTypeOrder toOrder(EqtypeOrderRow eqtypeorderrow);

    public abstract EqTypeOrderUnit toOrderUnit(EqtypeOrderUnitRow eqtypeorderunitrow);

    public abstract EqTypeOrderAction toOrderAction(EqtypeOrderActionRow eqtypeorderactionrow);

    public abstract EqTypeOrderExecution toOrderExecution(EqtypeOrderExecutionRow eqtypeorderexecutionrow);

    private static PersistenceManager instance;
    private static PersistenceManager m_defaultInstance = new PersistenceManagerImpl();

}

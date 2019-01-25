// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TransactionalInterceptor.java

package com.fitechlabs.xtrade.kernel.interceptor;

import com.fitechlabs.dbind.DBindTransactions;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import javax.transaction.*;

// Referenced classes of package com.fitechlabs.xtrade.kernel.interceptor:
//            Interceptor

public class TransactionalInterceptor
    implements Interceptor
{
    private static abstract class Impl
    {

        protected abstract Transaction begin()
            throws SystemException;

        protected abstract void commit(Transaction transaction)
            throws SystemException;

        protected abstract void rollback(Transaction transaction)
            throws SystemException;

        private Impl()
        {
        }

    }


    public TransactionalInterceptor(int transactionSemantics)
    {
        switch(transactionSemantics)
        {
        case 2: // '\002'
            impl = createNewImpl;
            break;

        case 1: // '\001'
            impl = joinExistingImpl;
            break;

        default:
            throw new IllegalArgumentException("bad value for semantics: " + transactionSemantics);
        }
    }

    public Object onCall(Method method, Object arguments[])
    {
        return impl.begin();
        SystemException e;
        e;
        throw new RuntimeException(e.toString());
    }

    public void onReturn(Object context, Object returnValue)
    {
        try
        {
            impl.commit((Transaction)context);
        }
        catch(SystemException e)
        {
            throw new RuntimeException("commit failure: " + e.toString());
        }
    }

    public void onThrowable(Object context, Throwable thrownObject)
    {
        try
        {
            impl.rollback((Transaction)context);
        }
        catch(SystemException e)
        {
            throw new RuntimeException("rollback failure: " + e.toString());
        }
    }

    public static final int TX_JOIN_EXISTING = 1;
    public static final int TX_CREATE_NEW = 2;
    private final Impl impl;
    private static final Impl createNewImpl = new Impl() {

        void pushTrans(Object trans)
        {
            List list = (List)currentTrans.get();
            list.add(0, trans);
        }

        Object popTrans()
        {
            List list = (List)currentTrans.get();
            return list.remove(0);
        }

        protected Transaction begin()
            throws SystemException
        {
            Transaction trans = DBindTransactions.getTransactionManager().suspend();
            if(trans instanceof Transaction)
                pushTrans(trans);
            else
                pushTrans(NULL);
            DBindTransactions.getTransactionManager().begin();
            return null;
            NotSupportedException e;
            e;
            throw new IllegalStateException(e.toString());
        }

        protected void commit(Transaction tx)
            throws SystemException
        {
            try
            {
                DBindTransactions.getTransactionManager().commit();
            }
            catch(RollbackException e)
            {
                throw new RuntimeException(e.toString());
            }
            catch(HeuristicMixedException e)
            {
                throw new RuntimeException(e.toString());
            }
            catch(HeuristicRollbackException e)
            {
                throw new RuntimeException(e.toString());
            }
            finally
            {
                try
                {
                    Object trans = popTrans();
                    if(trans instanceof Transaction)
                        DBindTransactions.getTransactionManager().resume((Transaction)trans);
                    else
                        DBindTransactions.getTransactionManager().resume(null);
                }
                catch(InvalidTransactionException e)
                {
                    throw new IllegalStateException(e.toString());
                }
            }
        }

        protected void rollback(Transaction tx)
            throws SystemException
        {
            try
            {
                DBindTransactions.getTransactionManager().rollback();
            }
            finally
            {
                try
                {
                    Object trans = popTrans();
                    if(trans instanceof Transaction)
                        DBindTransactions.getTransactionManager().resume((Transaction)trans);
                    else
                        DBindTransactions.getTransactionManager().resume(null);
                }
                catch(InvalidTransactionException e)
                {
                    throw new IllegalStateException(e.toString());
                }
            }
        }

        ThreadLocal currentTrans;
        final Object NULL = new Object();

            
                throws SystemException
            {
                currentTrans = new ThreadLocal() {

                    protected synchronized Object initialValue()
                    {
                        return new LinkedList();
                    }

                }
;
            }
    }
;
    private static final Impl joinExistingImpl = new Impl() {

        protected Transaction begin()
            throws SystemException
        {
            DBindTransactions.getTransactionManager().begin();
            return null;
            NotSupportedException e;
            e;
            throw new IllegalStateException(e.toString());
        }

        protected void commit(Transaction tx)
            throws SystemException
        {
            try
            {
                DBindTransactions.getTransactionManager().commit();
            }
            catch(RollbackException e)
            {
                throw new RuntimeException(e.toString());
            }
            catch(HeuristicMixedException e)
            {
                throw new RuntimeException(e.toString());
            }
            catch(HeuristicRollbackException e)
            {
                throw new RuntimeException(e.toString());
            }
        }

        protected void rollback(Transaction tx)
            throws SystemException
        {
            DBindTransactions.getTransactionManager().rollback();
        }

    }
;

}

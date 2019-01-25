// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QPStdImpl.java

package com.fitechlabs.xtrade.kernel.data.impl;

import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.util.List;
import java.util.Map;
import javax.transaction.*;

public class QPStdImpl
    implements QueryProcessor
{
    protected class SeparateExplicitTX extends TX
    {

        public void begin()
            throws DataNetworkException
        {
            try
            {
                tm = DBindTransactions.getTransactionManager();
                tx = tm.suspend();
                tm.begin();
            }
            catch(NotSupportedException e)
            {
                throw new DataNetworkException("tx begin excep: " + e);
            }
            catch(SystemException e)
            {
                throw new DataNetworkException("tx begin excep: " + e);
            }
        }

        public void commit()
        {
            c = true;
        }

        public void complete()
            throws DataNetworkException
        {
            if(c)
                tm.commit();
            else
                tm.rollback();
            tm.resume(tx);
            break MISSING_BLOCK_LABEL_188;
            RollbackException e;
            e;
            tm.resume(tx);
            throw e;
            e;
            throw new DataNetworkException("tx complete excep: " + e);
            e;
            throw new DataNetworkException("tx complete excep: " + e);
            e;
            throw new DataNetworkException("tx complete excep: " + e);
            e;
            throw new DataNetworkException("tx complete excep: " + e);
            e;
            throw new InternalError(e.toString());
        }

        private TransactionManager tm;
        private Transaction tx;
        private boolean c;

        protected SeparateExplicitTX()
        {
            c = false;
        }
    }

    protected class ExplicitTX extends TX
    {

        public void begin()
            throws DataNetworkException
        {
            try
            {
                tm = DBindTransactions.getTransactionManager();
                tm.begin();
            }
            catch(NotSupportedException e)
            {
                throw new DataNetworkException("tx begin excep: " + e);
            }
            catch(SystemException e)
            {
                throw new DataNetworkException("tx begin excep: " + e);
            }
        }

        public void commit()
        {
            c = true;
        }

        public void complete()
            throws DataNetworkException
        {
            try
            {
                if(c)
                    tm.commit();
                else
                    tm.rollback();
            }
            catch(RollbackException e)
            {
                throw new DataNetworkException("tx complete excep: " + e);
            }
            catch(HeuristicMixedException e)
            {
                throw new DataNetworkException("tx complete excep: " + e);
            }
            catch(HeuristicRollbackException e)
            {
                throw new DataNetworkException("tx complete excep: " + e);
            }
            catch(SystemException e)
            {
                throw new DataNetworkException("tx complete excep: " + e);
            }
        }

        private TransactionManager tm;
        private boolean c;

        protected ExplicitTX()
        {
            c = false;
        }
    }

    private static class TX
    {

        public void begin()
            throws DataNetworkException
        {
        }

        public void commit()
        {
        }

        public void complete()
            throws DataNetworkException
        {
        }

        private TX()
        {
        }

    }


    private void assertConditionsValid(String conditions)
    {
        if(conditions != null && conditions.length() != 0 && !database.isTransactionActive())
            throw new IllegalStateException("non-null conditions '" + conditions + "' require active transaction");
        else
            return;
    }

    public QPStdImpl(Database database)
    {
        this.database = database;
    }

    private TX beginTx(int semantics)
        throws DataNetworkException
    {
        TX tx = createTX(semantics);
        tx.begin();
        return tx;
    }

    private TX createTX(int semantics)
        throws DataNetworkException
    {
        boolean active = DBindTransactions.getIsTransactionActive();
        switch(semantics)
        {
        case 2: // '\002'
            return new SeparateExplicitTX();

        case 1: // '\001'
            if(active)
                return NULL_TX;
            else
                return new ExplicitTX();
        }
        throw new IllegalArgumentException("unknown semantics value: " + semantics);
    }

    public Object[] doQueries(BatchedQuery queries[])
        throws DataNetworkException, DataBatchException, DataRollbackException
    {
        return doQueries(1, queries);
    }

    public Object[] doQueries(int transactionSemantics, BatchedQuery queries[])
        throws DataNetworkException, DataBatchException, DataRollbackException
    {
        TX tx;
        int i;
        Object o[];
        tx = beginTx(transactionSemantics);
        i = 0;
        o = new Object[queries.length];
        Object aobj[];
        try
        {
            for(; i < queries.length; i++)
                o[i] = doQuery(queries[i]);

            tx.commit();
            aobj = o;
        }
        catch(DataQueryException e)
        {
            throw new DataBatchException("subquery failed with exception:" + e.getMessage(), e, o, i);
        }
        tx.complete();
        return aobj;
        Exception exception;
        exception;
        tx.complete();
        throw exception;
    }

    public Object doQuery(int transactionSemantics, BatchedQuery query)
        throws DataNetworkException, DataQueryException, DataRollbackException
    {
        TX tx = beginTx(transactionSemantics);
        Object obj;
        Object o = doQuery(query);
        tx.commit();
        obj = o;
        tx.complete();
        return obj;
        Exception exception;
        exception;
        tx.complete();
        throw exception;
    }

    public Object doQuery(BatchedQuery query)
        throws DataNetworkException, DataQueryException, DataRollbackException
    {
        int qtype = query.getQueryType();
        String name = query.getTableName();
        Object qa[] = query.getQueryArgs();
        switch(qtype)
        {
        case 10: // '\n'
        {
            return doInsertQuery((Row)qa[0]);
        }

        case 11: // '\013'
        {
            return doInsertQuery(name, (Map)qa[0]);
        }

        case 20: // '\024'
        {
            int count = doUpdateQuery((Row)qa[0]);
            return new Integer(count);
        }

        case 21: // '\025'
        {
            int count = doUpdateQuery((PrimaryKey)qa[0], (Map)qa[1]);
            return new Integer(count);
        }

        case 22: // '\026'
        {
            int count = doUpdateAllQuery(query.getRowType(), (String)qa[0], (Object[])qa[1], (Map)qa[2]);
            return new Integer(count);
        }

        case 23: // '\027'
        {
            int count = doUpdateQuery((Row)qa[0], (String)qa[1], (Object[])qa[2]);
            return new Integer(count);
        }

        case 24: // '\030'
        {
            int count = doUpdateQuery((PrimaryKey)qa[0], (String)qa[1], (Object[])qa[2], (Map)qa[3]);
            return new Integer(count);
        }

        case 30: // '\036'
        {
            int count = doDeleteQuery((PrimaryKey)qa[0]);
            return new Integer(count);
        }

        case 31: // '\037'
        {
            int count = doDeleteAllQuery(query.getRowType(), (String)qa[0], (Object[])qa[1]);
            return new Integer(count);
        }

        case 32: // ' '
        {
            int count = doDeleteQuery((PrimaryKey)qa[0], (String)qa[1], (Object[])qa[2]);
            return new Integer(count);
        }

        case 40: // '('
        {
            return doFindByPrimaryKeyQuery((PrimaryKey)qa[0], (String)qa[1]);
        }

        case 41: // ')'
        {
            return doFindByPrimaryKeyQuery((PrimaryKey)qa[0], (String)qa[1], (String)qa[2], (Object[])qa[3]);
        }

        case 50: // '2'
        {
            return doFindAllQuery(query.getRowType(), (String)qa[0], (String)qa[1], (String)qa[2], (Object[])qa[3], (RowType[])qa[4]);
        }

        case 51: // '3'
        {
            return doFindAllQuery(query.getRowType(), (String)qa[0], (String)qa[1], (String)qa[2], (Object[])qa[3], ((Integer)qa[4]).intValue(), ((Integer)qa[5]).intValue(), (RowType[])qa[6]);
        }

        case 60: // '<'
        {
            return ((Object) (doProcedureCallQuery(name, (Object[])qa[0], (Object[])qa[1])));
        }

        case 70: // 'F'
        {
            return doTransaction(((Integer)qa[0]).intValue(), (TransactionCallback)qa[1]);
        }

        case 80: // 'P'
        {
            boolean result = doLockPartititionQuery(((Boolean)qa[0]).booleanValue());
            return result ? Boolean.TRUE : Boolean.FALSE;
        }

        case 90: // 'Z'
        {
            long value = doGetNewPkValueQuery(name);
            return new Long(value);
        }

        case 100: // 'd'
        {
            int count = doGetCountQuery(query.getRowType(), (String)qa[0], (Object[])qa[1]);
            return new Integer(count);
        }
        }
        throw new IllegalArgumentException("bad batched query type: " + qtype);
    }

    public Object doTransaction(TransactionCallback callback)
        throws DataNetworkException, DataQueryException, DataCallbackException, DataRollbackException
    {
        TX tx = beginTx(1);
        Object obj;
        Object o = callback.process();
        tx.commit();
        obj = o;
        tx.complete();
        return obj;
        Exception exception;
        exception;
        tx.complete();
        throw exception;
    }

    public Object doTransaction(int transactionSemantics, TransactionCallback callback)
        throws DataNetworkException, DataQueryException, DataCallbackException, DataRollbackException
    {
        TX tx = beginTx(transactionSemantics);
        Object obj;
        Object o = callback.process();
        tx.commit();
        obj = o;
        tx.complete();
        return obj;
        Exception exception;
        exception;
        tx.complete();
        throw exception;
    }

    public Object doInsertQuery(final String tableName, final Row initialValues)
        throws DataQueryException, DataNetworkException
    {
        return doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return doInsertQueryImpl(tableName, initialValues);
            }

        }
);
    }

    public Object doInsertQueryImpl(String tableName, Row initialValues)
        throws DataQueryException, DataNetworkException
    {
        Table table = database.getTable(tableName);
        Object o = table.insert(initialValues);
        return o;
        InsertException e;
        e;
        throw new DataQueryException("insert failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public Object doInsertQuery(final String tableName, final Map initialValues)
        throws DataQueryException, DataNetworkException
    {
        return doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return doInsertQueryImpl(tableName, initialValues);
            }

        }
);
    }

    public Object doInsertQueryImpl(String tableName, Map initialValues)
        throws DataQueryException, DataNetworkException
    {
        Table table = database.getTable(tableName);
        Object o = table.insert(initialValues);
        return o;
        InsertException e;
        e;
        throw new DataQueryException("insert failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public int doUpdateQuery(final String tableName, final Row newValues)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return new Integer(doUpdateQueryImpl(tableName, newValues));
            }

        }
)).intValue();
    }

    public int doUpdateQueryImpl(String tableName, Row newValues)
        throws DataQueryException, DataNetworkException
    {
        Table table = database.getTable(tableName);
        int count = table.update(newValues);
        return count;
        UpdateException e;
        e;
        throw new DataQueryException("update failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public int doUpdateQuery(final String tableName, final PrimaryKey primaryKey, final Map changes)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return new Integer(doUpdateQueryImpl(tableName, primaryKey, changes));
            }

        }
)).intValue();
    }

    public int doUpdateQueryImpl(String tableName, PrimaryKey primaryKey, Map changes)
        throws DataQueryException, DataNetworkException
    {
        Table table = database.getTable(tableName);
        int count = table.update(primaryKey, changes);
        return count;
        UpdateException e;
        e;
        throw new DataQueryException("update failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public int doUpdateAllQuery(final String tableName, final String where, final Object bindVars[], final Map changes)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return new Integer(doUpdateAllQueryImpl(tableName, where, bindVars, changes));
            }

        }
)).intValue();
    }

    public int doUpdateAllQueryImpl(String tableName, String where, Object bindVars[], Map changes)
        throws DataQueryException, DataNetworkException
    {
        Table table = database.getTable(tableName);
        int count = table.updateAll(where, bindVars, changes);
        return count;
        UpdateException e;
        e;
        throw new DataQueryException("update failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public int doDeleteQuery(final String tableName, final PrimaryKey primaryKey)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return new Integer(doDeleteQueryImpl(tableName, primaryKey));
            }

        }
)).intValue();
    }

    public int doDeleteQueryImpl(String tableName, PrimaryKey primaryKey)
        throws DataQueryException, DataNetworkException
    {
        Table table = database.getTable(tableName);
        int count = table.delete(primaryKey);
        return count;
        DeleteException e;
        e;
        throw new DataQueryException("delete failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public int doDeleteAllQuery(final String tableName, final String where, final Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return new Integer(doDeleteAllQueryImpl(tableName, where, bindVars));
            }

        }
)).intValue();
    }

    public int doDeleteAllQueryImpl(String tableName, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        Table table = database.getTable(tableName);
        int count = table.deleteAll(where, bindVars);
        return count;
        DeleteException e;
        e;
        throw new DataQueryException("delete failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public Row doFindByPrimaryKeyQuery(final String tableName, final PrimaryKey primaryKey, final String conditions)
        throws DataFindException, DataQueryException, DataNetworkException, IllegalStateException
    {
        assertConditionsValid(conditions);
        return (Row)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return doFindByPrimaryKeyQueryImpl(tableName, primaryKey, conditions);
            }

        }
);
    }

    public Row doFindByPrimaryKeyQueryImpl(String tableName, PrimaryKey primaryKey, String conditions)
        throws DataFindException, DataQueryException, DataNetworkException, IllegalStateException
    {
        Table table = database.getTable(tableName);
        Row row = table.select(primaryKey, conditions);
        return row;
        FindException e;
        e;
        throw new DataFindException("not found: " + e);
        e;
        throw new DataQueryException("select failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public Row doFindByPrimaryKeyQuery(final String tableName, final PrimaryKey primaryKey, final String where, final String conditions, final Object bindVars[])
        throws DataFindException, DataQueryException, DataNetworkException, IllegalStateException
    {
        assertConditionsValid(conditions);
        return (Row)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return doFindByPrimaryKeyQueryImpl(tableName, primaryKey, where, conditions, bindVars);
            }

        }
);
    }

    public Row doFindByPrimaryKeyQueryImpl(String tableName, PrimaryKey primaryKey, String where, String conditions, Object bindVars[])
        throws DataFindException, DataQueryException, DataNetworkException, IllegalStateException
    {
        Table table = database.getTable(tableName);
        Row row = table.select(primaryKey, where, conditions, bindVars);
        return row;
        FindException e;
        e;
        throw new DataFindException("not found: " + e);
        e;
        throw new DataQueryException("select failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public List doFindAllQuery(final String tableName, final String where, final String conditions, final Object bindVars[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        assertConditionsValid(conditions);
        return (List)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return doFindAllQueryImpl(tableName, where, conditions, bindVars);
            }

        }
);
    }

    public List doFindAllQueryImpl(String tableName, String where, String conditions, Object bindVars[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        Table table = database.getTable(tableName);
        List list = table.selectAll(where, null, conditions, bindVars);
        return list;
        SelectException e;
        e;
        throw new DataQueryException("select failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public List doFindAllQuery(final String tableName, final String where, final String orderBy, final String conditions, final Object bindVars[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        assertConditionsValid(conditions);
        return (List)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return doFindAllQueryImpl(tableName, where, orderBy, conditions, bindVars);
            }

        }
);
    }

    public List doFindAllQueryImpl(String tableName, String where, String orderBy, String conditions, Object bindVars[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        Table table = database.getTable(tableName);
        List list = table.selectAll(where, orderBy, conditions, bindVars);
        return list;
        SelectException e;
        e;
        throw new DataQueryException("select failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public ListPage doFindAllQuery(final String tableName, final String where, final String orderBy, final String conditions, final Object bindVars[], final int pageSize, final int pageNumber)
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        assertConditionsValid(conditions);
        return (ListPage)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return doFindAllQueryImpl(tableName, where, orderBy, conditions, bindVars, pageSize, pageNumber);
            }

        }
);
    }

    public ListPage doFindAllQueryImpl(String tableName, String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber)
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        Table table = database.getTable(tableName);
        com.fitechlabs.dbind.ListSubset listSubset = table.selectAll(where, orderBy, conditions, bindVars, pageSize * pageNumber, pageSize * (pageNumber + 1));
        return new ArrayListPage(listSubset, pageSize);
        SelectException e;
        e;
        throw new DataQueryException("select failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public Object[] doProcedureCallQuery(final String procName, final Object inArgs[], final Object outArgs[])
        throws DataQueryException, DataNetworkException
    {
        return (Object[])doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return ((Object) (doProcedureCallQueryImpl(procName, inArgs, outArgs)));
            }

        }
);
    }

    public Object[] doProcedureCallQueryImpl(String procName, Object inArgs[], Object outArgs[])
        throws DataQueryException, DataNetworkException
    {
        Object results[];
        int n = outArgs != null ? outArgs.length : 0;
        results = new Object[n];
        System.arraycopy(((Object) (outArgs)), 0, ((Object) (results)), 0, n);
        database.call(procName, inArgs, results, false);
        return results;
        CallException e;
        e;
        throw new DataQueryException("call failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public Object[] doReadOnlyProcedureCallQuery(final String procName, final Object inArgs[], final Object outArgs[])
        throws DataQueryException, DataNetworkException
    {
        return (Object[])doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return ((Object) (doReadOnlyProcedureCallQueryImpl(procName, inArgs, outArgs)));
            }

        }
);
    }

    public Object[] doReadOnlyProcedureCallQueryImpl(String procName, Object inArgs[], Object outArgs[])
        throws DataQueryException, DataNetworkException
    {
        Object results[];
        int n = outArgs != null ? outArgs.length : 0;
        results = new Object[n];
        System.arraycopy(((Object) (outArgs)), 0, ((Object) (results)), 0, n);
        database.call(procName, inArgs, results, true);
        return results;
        CallException e;
        e;
        throw new DataQueryException("call failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public boolean lockAccount(long account_id, boolean waitOnBusy)
        throws DataQueryException, DataNetworkException, IllegalStateException, IllegalArgumentException
    {
        if(!database.isTransactionActive())
            throw new IllegalStateException("no active transaction.");
        if(!waitOnBusy)
            break MISSING_BLOCK_LABEL_56;
        database.call("exec.lock_account_wait", new Object[] {
            new Long(account_id)
        }, null, true);
        return true;
        Object outargs[];
        outargs = (new Object[] {
            null, new Integer(4)
        });
        database.call("exec.lock_account_nowait", new Object[] {
            new Long(account_id)
        }, outargs, true);
        return ((Number)outargs[1]).intValue() == 1;
        CallException ce;
        ce;
        throw new InternalError("call failed: " + ce);
        ce;
        throw new RuntimeException("database connection not available: " + ce);
    }

    public boolean doLockPartititionQuery(boolean waitOnBusy)
        throws DataQueryException, DataNetworkException
    {
        return doLockPartititionQueryImpl(waitOnBusy);
        FindException e;
        e;
        throw new DataFindException(e.getMessage());
        e;
        throw new DataQueryException(e.getMessage(), e.getQueryText());
        e;
        throw new DataNetworkException(e.getMessage());
    }

    protected boolean doLockPartititionQueryImpl(boolean waitOnBusy)
        throws ConnectionException, SelectException
    {
        return false;
    }

    public int doGetCountQuery(final String tableName, final String where, final Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return new Integer(doGetCountQueryImpl(tableName, where, bindVars));
            }

        }
)).intValue();
    }

    public int doGetCountQueryImpl(String tableName, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        Table table = database.getTable(tableName);
        int count = table.selectCount(where, bindVars);
        return count;
        SelectException e;
        e;
        throw new DataQueryException("select failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public long doGetNewPkValueQuery(String tableName)
        throws DataQueryException, DataNetworkException
    {
        long value = Processors.getNewPkValue(tableName);
        return value;
    }

    public Object doInsertQuery(final Row initialValues)
        throws DataQueryException, DataNetworkException
    {
        return doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return doInsertQueryImpl(initialValues);
            }

        }
);
    }

    public Object doInsertQueryImpl(Row initialValues)
        throws DataQueryException, DataNetworkException
    {
        Table table = database.getTable(initialValues.getRowType().getTableName());
        Object o = table.insert(initialValues);
        return o;
        InsertException e;
        e;
        throw new DataQueryException("insert failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public int doUpdateQuery(final Row newValues)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return new Integer(doUpdateQueryImpl(newValues));
            }

        }
)).intValue();
    }

    public int doUpdateQueryImpl(Row newValues)
        throws DataQueryException, DataNetworkException
    {
        Table table = database.getTable(newValues.getRowType().getTableName());
        int count = table.update(newValues);
        return count;
        UpdateException e;
        e;
        throw new DataQueryException("update failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public int doUpdateQuery(final Row newValues, final String where, final Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return new Integer(doUpdateQueryImpl(newValues, where, bindVars));
            }

        }
)).intValue();
    }

    public int doUpdateQueryImpl(Row newValues, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        Table table = database.getTable(newValues.getRowType().getTableName());
        int count = table.update(newValues, where, bindVars);
        return count;
        UpdateException e;
        e;
        throw new DataQueryException("update failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public int doUpdateQuery(final PrimaryKey primaryKey, final Map changes)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return new Integer(doUpdateQueryImpl(primaryKey, changes));
            }

        }
)).intValue();
    }

    public int doUpdateQueryImpl(PrimaryKey primaryKey, Map changes)
        throws DataQueryException, DataNetworkException
    {
        Table table = database.getTable(primaryKey.getRowType().getTableName());
        int count = table.update(primaryKey, changes);
        return count;
        UpdateException e;
        e;
        throw new DataQueryException("update failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public int doUpdateQuery(final PrimaryKey primaryKey, final String where, final Object bindVars[], final Map changes)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return new Integer(doUpdateQueryImpl(primaryKey, where, bindVars, changes));
            }

        }
)).intValue();
    }

    public int doUpdateQueryImpl(PrimaryKey primaryKey, String where, Object bindVars[], Map changes)
        throws DataQueryException, DataNetworkException
    {
        Table table = database.getTable(primaryKey.getRowType().getTableName());
        int count = table.update(primaryKey, where, bindVars, changes);
        return count;
        UpdateException e;
        e;
        throw new DataQueryException("update failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public int doUpdateAllQuery(final RowType rowType, final Map changes)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return new Integer(doUpdateAllQueryImpl(rowType, changes));
            }

        }
)).intValue();
    }

    public int doUpdateAllQueryImpl(RowType rowType, Map changes)
        throws DataQueryException, DataNetworkException
    {
        Table table = database.getTable(rowType.getTableName());
        int count = table.updateAll(null, null, changes);
        return count;
        UpdateException e;
        e;
        throw new DataQueryException("update failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public int doUpdateAllQuery(final RowType rowType, final String where, final Object bindVars[], final Map changes)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return new Integer(doUpdateAllQueryImpl(rowType, where, bindVars, changes));
            }

        }
)).intValue();
    }

    public int doUpdateAllQueryImpl(RowType rowType, String where, Object bindVars[], Map changes)
        throws DataQueryException, DataNetworkException
    {
        Table table = database.getTable(rowType.getTableName());
        int count = table.updateAll(where, bindVars, changes);
        return count;
        UpdateException e;
        e;
        throw new DataQueryException("update failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public int doDeleteQuery(final PrimaryKey primaryKey)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return new Integer(doDeleteQueryImpl(primaryKey));
            }

        }
)).intValue();
    }

    public int doDeleteQueryImpl(PrimaryKey primaryKey)
        throws DataQueryException, DataNetworkException
    {
        Table table = database.getTable(primaryKey.getRowType().getTableName());
        int count = table.delete(primaryKey);
        return count;
        DeleteException e;
        e;
        throw new DataQueryException("delete failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public int doDeleteQuery(final PrimaryKey primaryKey, final String where, final Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return new Integer(doDeleteQueryImpl(primaryKey, where, bindVars));
            }

        }
)).intValue();
    }

    public int doDeleteQueryImpl(PrimaryKey primaryKey, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        Table table = database.getTable(primaryKey.getRowType().getTableName());
        int count = table.delete(primaryKey, where, bindVars);
        return count;
        DeleteException e;
        e;
        throw new DataQueryException("delete failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public int doDeleteAllQuery(final RowType rowType)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return new Integer(doDeleteAllQueryImpl(rowType));
            }

        }
)).intValue();
    }

    public int doDeleteAllQueryImpl(RowType rowType)
        throws DataQueryException, DataNetworkException
    {
        Table table = database.getTable(rowType.getTableName());
        int count = table.deleteAll(null, null);
        return count;
        DeleteException e;
        e;
        throw new DataQueryException("delete failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public int doDeleteAllQuery(final RowType rowType, final String where, final Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return new Integer(doDeleteAllQueryImpl(rowType, where, bindVars));
            }

        }
)).intValue();
    }

    public int doDeleteAllQueryImpl(RowType rowType, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        Table table = database.getTable(rowType.getTableName());
        int count = table.deleteAll(where, bindVars);
        return count;
        DeleteException e;
        e;
        throw new DataQueryException("delete failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public Row doFindByPrimaryKeyQuery(final PrimaryKey primaryKey)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        return (Row)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return doFindByPrimaryKeyQueryImpl(primaryKey);
            }

        }
);
    }

    public Row doFindByPrimaryKeyQueryImpl(PrimaryKey primaryKey)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        Table table = database.getTable(primaryKey.getRowType().getTableName());
        Row row = table.select(primaryKey);
        return row;
        FindException e;
        e;
        throw new DataFindException("not found: " + e);
        e;
        throw new DataQueryException("select failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public Row doFindByPrimaryKeyQuery(final PrimaryKey primaryKey, final String conditions)
        throws DataFindException, DataQueryException, DataNetworkException, IllegalStateException
    {
        assertConditionsValid(conditions);
        return (Row)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return doFindByPrimaryKeyQueryImpl(primaryKey, conditions);
            }

        }
);
    }

    public Row doFindByPrimaryKeyQueryImpl(PrimaryKey primaryKey, String conditions)
        throws DataFindException, DataQueryException, DataNetworkException, IllegalStateException
    {
        Table table = database.getTable(primaryKey.getRowType().getTableName());
        Row row = table.select(primaryKey, conditions);
        return row;
        FindException e;
        e;
        throw new DataFindException("not found: " + e);
        e;
        throw new DataQueryException("select failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public Row doFindByPrimaryKeyQuery(final PrimaryKey primaryKey, final String where, final Object bindVars[])
        throws DataFindException, DataQueryException, DataNetworkException
    {
        return (Row)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return doFindByPrimaryKeyQueryImpl(primaryKey, where, bindVars);
            }

        }
);
    }

    public Row doFindByPrimaryKeyQueryImpl(PrimaryKey primaryKey, String where, Object bindVars[])
        throws DataFindException, DataQueryException, DataNetworkException
    {
        Table table = database.getTable(primaryKey.getRowType().getTableName());
        Row row = table.select(primaryKey, where, null, bindVars);
        return row;
        FindException e;
        e;
        throw new DataFindException("not found: " + e);
        e;
        throw new DataQueryException("select failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public Row doFindByPrimaryKeyQuery(final PrimaryKey primaryKey, final String where, final String conditions, final Object bindVars[])
        throws DataFindException, DataQueryException, DataNetworkException, IllegalStateException
    {
        assertConditionsValid(conditions);
        return (Row)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return doFindByPrimaryKeyQueryImpl(primaryKey, where, conditions, bindVars);
            }

        }
);
    }

    public Row doFindByPrimaryKeyQueryImpl(PrimaryKey primaryKey, String where, String conditions, Object bindVars[])
        throws DataFindException, DataQueryException, DataNetworkException, IllegalStateException
    {
        Table table = database.getTable(primaryKey.getRowType().getTableName());
        Row row = table.select(primaryKey, where, conditions, bindVars);
        return row;
        FindException e;
        e;
        throw new DataFindException("not found: " + e);
        e;
        throw new DataQueryException("select failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public List doFindAllQuery(final RowType rowType)
        throws DataQueryException, DataNetworkException
    {
        return (List)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return doFindAllQueryImpl(rowType);
            }

        }
);
    }

    public List doFindAllQueryImpl(RowType rowType)
        throws DataQueryException, DataNetworkException
    {
        Table table = database.getTable(rowType.getTableName());
        List list = table.selectAll(null, null, null, null);
        return list;
        SelectException e;
        e;
        throw new DataQueryException("select failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public List doFindAllQuery(final RowType rowType, final String conditions)
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        assertConditionsValid(conditions);
        return (List)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return doFindAllQueryImpl(rowType, conditions);
            }

        }
);
    }

    public List doFindAllQueryImpl(RowType rowType, String conditions)
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        Table table = database.getTable(rowType.getTableName());
        List list = table.selectAll(null, null, conditions, null);
        return list;
        SelectException e;
        e;
        throw new DataQueryException("select failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public List doFindAllQuery(final RowType rowType, final String where, final Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return (List)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return doFindAllQueryImpl(rowType, where, bindVars);
            }

        }
);
    }

    public List doFindAllQueryImpl(RowType rowType, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        Table table = database.getTable(rowType.getTableName());
        List list = table.selectAll(where, null, null, bindVars);
        return list;
        SelectException e;
        e;
        throw new DataQueryException("select failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public List doFindAllQuery(final RowType rowType, final String where, final String conditions, final Object bindVars[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        assertConditionsValid(conditions);
        return (List)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return doFindAllQueryImpl(rowType, where, conditions, bindVars);
            }

        }
);
    }

    public List doFindAllQueryImpl(RowType rowType, String where, String conditions, Object bindVars[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        Table table = database.getTable(rowType.getTableName());
        List list = table.selectAll(where, null, conditions, bindVars);
        return list;
        SelectException e;
        e;
        throw new DataQueryException("select failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public List doFindAllQuery(final RowType rowType, final String where, final String orderBy, final String conditions, final Object bindVars[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        assertConditionsValid(conditions);
        return (List)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return doFindAllQueryImpl(rowType, where, orderBy, conditions, bindVars);
            }

        }
);
    }

    public List doFindAllQueryImpl(RowType rowType, String where, String orderBy, String conditions, Object bindVars[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        Table table = database.getTable(rowType.getTableName());
        List list = table.selectAll(where, orderBy, conditions, bindVars);
        return list;
        SelectException e;
        e;
        throw new DataQueryException("select failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public List doFindAllQuery(final RowType rowType, final String where, final String orderBy, final String conditions, final Object bindVars[], final RowType rowTypes[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        assertConditionsValid(conditions);
        return (List)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return doFindAllQueryImpl(rowType, where, orderBy, conditions, bindVars, rowTypes);
            }

        }
);
    }

    public List doFindAllQueryImpl(RowType rowType, String where, String orderBy, String conditions, Object bindVars[], RowType rowTypes[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        Table table = database.getTable(rowType.getTableName());
        List list = table.selectAll(where, orderBy, conditions, bindVars, rowTypes);
        return list;
        SelectException e;
        e;
        throw new DataQueryException("select failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public ListPage doFindAllQuery(final RowType rowType, final String where, final String orderBy, final String conditions, final Object bindVars[], final int pageSize, final int pageNumber)
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        assertConditionsValid(conditions);
        return (ListPage)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return doFindAllQueryImpl(rowType, where, orderBy, conditions, bindVars, pageSize, pageNumber);
            }

        }
);
    }

    public ListPage doFindAllQueryImpl(RowType rowType, String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber)
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        Table table = database.getTable(rowType.getTableName());
        com.fitechlabs.dbind.ListSubset listSubset = table.selectAll(where, orderBy, conditions, bindVars, pageSize * pageNumber, pageSize * (pageNumber + 1));
        return new ArrayListPage(listSubset, pageSize);
        SelectException e;
        e;
        throw new DataQueryException("select failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public ListPage doFindAllQuery(final RowType rowType, final String where, final String orderBy, final String conditions, final Object bindVars[], final int pageSize, final int pageNumber, 
            final RowType rowTypes[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        assertConditionsValid(conditions);
        return (ListPage)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return doFindAllQueryImpl(rowType, where, orderBy, conditions, bindVars, pageSize, pageNumber, rowTypes);
            }

        }
);
    }

    public ListPage doFindAllQueryImpl(RowType rowType, String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber, 
            RowType rowTypes[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        Table table = database.getTable(rowType.getTableName());
        com.fitechlabs.dbind.ListSubset listSubset = table.selectAll(where, orderBy, conditions, bindVars, pageSize * pageNumber, pageSize * (pageNumber + 1), rowTypes);
        return new ArrayListPage(listSubset, pageSize);
        SelectException e;
        e;
        throw new DataQueryException("select failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public int doGetCountQuery(final RowType rowType)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return new Integer(doGetCountQueryImpl(rowType));
            }

        }
)).intValue();
    }

    public int doGetCountQueryImpl(RowType rowType)
        throws DataQueryException, DataNetworkException
    {
        Table table = database.getTable(rowType.getTableName());
        int count = table.selectCount(null, null);
        return count;
        SelectException e;
        e;
        throw new DataQueryException("select failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public int doGetCountQuery(final RowType rowType, final String where, final Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTransaction(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException
            {
                return new Integer(doGetCountQueryImpl(rowType, where, bindVars));
            }

        }
)).intValue();
    }

    public int doGetCountQueryImpl(RowType rowType, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        Table table = database.getTable(rowType.getTableName());
        int count = table.selectCount(where, bindVars);
        return count;
        SelectException e;
        e;
        throw new DataQueryException("select failed: " + e);
        e;
        throw new DataNetworkException("connect failed: " + e);
    }

    public long doGetNewPkValueQuery(RowType rowType)
        throws DataQueryException, DataNetworkException
    {
        String tableName = rowType.getTableName();
        long value = Processors.getNewPkValue(tableName);
        return value;
    }

    public void forceReadonlyMode(RowType rowType, boolean readonly)
        throws UnsupportedOperationException, DataNetworkException
    {
        String message = "Ignoring readonly mode setting since it has no effect on non-master type of tables";
        log.warn(message);
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static final Logit log;
    private static final boolean DBG;
    protected final Database database;
    private static final int DEFAULT_TX_SEMANTICS = 1;
    private static TX NULL_TX = new TX();

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.data.impl.QPStdImpl.class);
        DBG = log.ison();
    }
}

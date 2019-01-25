// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QueryProcessorStdImpl.java

package com.fitechlabs.xtrade.kernel.data;

import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.util.List;
import java.util.Map;
import javax.transaction.*;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data:
//            DataQueryException, DataBatchException, DataNetworkException, DataRollbackException, 
//            TransactionCallback, DataFindException, QueryProcessor, DataCallbackException, 
//            BatchedQuery, Processors

public class QueryProcessorStdImpl
    implements QueryProcessor
{
    private static abstract class TransactionCallbackImpl
        implements TransactionCallback
    {

        public Object process()
            throws DataQueryException, DataNetworkException, DataCallbackException
        {
            return processImpl();
            FindException qe;
            qe;
            throw new DataFindException(qe.getMessage());
            qe;
            throw new DataQueryException(qe.getMessage(), qe.getQueryText());
            ConnectionException ce;
            ce;
            throw new DataNetworkException(ce.getMessage());
        }

        public abstract Object processImpl()
            throws ConnectionException, QueryException;

        private TransactionCallbackImpl()
        {
        }

    }


    public QueryProcessorStdImpl(Database database)
    {
        this(database, 1);
    }

    public QueryProcessorStdImpl(Database database, int defaultTransactionSemantics)
    {
        db = database;
        tx = defaultTransactionSemantics;
    }

    public Object[] doQueries(BatchedQuery queries[])
        throws DataNetworkException, DataBatchException, DataRollbackException
    {
        return doQueries(tx, queries);
    }

    public Object doQuery(BatchedQuery query)
        throws DataQueryException, DataNetworkException, DataRollbackException
    {
        return doQuery(tx, query);
    }

    public Object doTransaction(TransactionCallback callback)
        throws DataQueryException, DataNetworkException, DataCallbackException, DataRollbackException
    {
        return doTransaction(tx, callback);
    }

    public Object[] doQueries(int transactionSemantics, final BatchedQuery queries[])
        throws DataNetworkException, DataBatchException, DataRollbackException
    {
        return (Object[])doTransaction(transactionSemantics, new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException, DataCallbackException
            {
                Object results[] = new Object[queries.length];
                int i = 0;
                try
                {
                    while(i < queries.length) 
                    {
                        results[i] = doQueryImpl(queries[i]);
                        i++;
                    }
                }
                catch(DataQueryException dqe)
                {
                    throw new DataBatchException("sub-query[" + i + "] failed, " + dqe, dqe, results, i);
                }
                return ((Object) (results));
            }

        }
);
        DataQueryException dqe;
        dqe;
        if(dqe instanceof DataBatchException)
            throw (DataBatchException)dqe;
        else
            throw new IllegalStateException("bad exception: " + dqe);
    }

    public Object doQuery(int transactionSemantics, final BatchedQuery query)
        throws DataQueryException, DataNetworkException, DataRollbackException
    {
        return doTxImplicit(transactionSemantics, new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException, DataCallbackException
            {
                return doQueryImpl(query);
            }

        }
);
    }

    public Object doTransaction(int transactionSemantics, TransactionCallback callback)
        throws DataQueryException, DataNetworkException, DataCallbackException, DataRollbackException
    {
        switch(transactionSemantics)
        {
        case 1: // '\001'
            return doTxExplicit(callback);

        case 2: // '\002'
            return doTxSepExpl(callback);
        }
        throw new IllegalArgumentException("bad transactionSemantics: " + transactionSemantics);
    }

    private Object doTxImplicit(int transactionSemantics, TransactionCallback callback)
        throws DataQueryException, DataNetworkException, DataCallbackException, DataRollbackException
    {
        switch(transactionSemantics)
        {
        case 1: // '\001'
            return callback.process();

        case 2: // '\002'
            return doTxSeparate(callback);
        }
        throw new IllegalArgumentException("bad transactionSemantics: " + transactionSemantics);
    }

    private Object doTxSepExpl(final TransactionCallback callback)
        throws DataQueryException, DataNetworkException, DataCallbackException, DataRollbackException
    {
        return doTxSeparate(new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException, DataCallbackException
            {
                return doTxExplicit(callback);
            }

        }
);
    }

    private Object doTxSeparate(TransactionCallback callback)
        throws DataQueryException, DataNetworkException, DataCallbackException, DataRollbackException
    {
        TransactionManager tm;
        javax.transaction.Transaction tx;
        tm = DBindTransactions.getTransactionManager();
        tx = tm.suspend();
        Object obj = callback.process();
        tm.resume(tx);
        return obj;
        Exception exception;
        exception;
        tm.resume(tx);
        throw exception;
        SystemException se;
        se;
        throw new DataNetworkException("transaction manager failure: " + se);
        InvalidTransactionException ite;
        ite;
        throw new DataNetworkException("couldn't resume transaction: " + ite);
    }

    private Object doTxExplicit(TransactionCallback callback)
        throws DataQueryException, DataNetworkException, DataCallbackException, DataRollbackException
    {
        boolean commit;
        db.beginTransaction();
        commit = false;
        Object obj;
        try
        {
            Object result = callback.process();
            commit = true;
            obj = result;
        }
        finally
        {
            if(commit)
                db.commitTransaction();
            else
                db.rollbackTransaction();
        }
        return obj;
        DbException dbe;
        dbe;
        throw new DataRollbackException("transaction control failure", dbe);
    }

    public Object doInsertQuery(String tableName, Row initialValues)
        throws DataQueryException, DataNetworkException
    {
        return doInsertQuery(initialValues);
    }

    public Object doInsertQuery(final String tableName, final Map initialValues)
        throws DataQueryException, DataNetworkException
    {
        return doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                return doInsertQueryImpl(tableName, initialValues);
            }

        }
);
    }

    public int doUpdateQuery(String tableName, Row newValues)
        throws DataQueryException, DataNetworkException
    {
        return doUpdateQuery(newValues);
    }

    public int doUpdateQuery(String tableName, PrimaryKey primaryKey, Map changes)
        throws DataQueryException, DataNetworkException
    {
        return doUpdateQuery(primaryKey, changes);
    }

    public int doUpdateAllQuery(final String tableName, final String where, final Object bindVars[], final Map changes)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                return doUpdateAllQueryImpl(tableName, where, bindVars, changes);
            }

        }
)).intValue();
    }

    public int doDeleteQuery(String tableName, PrimaryKey primaryKey)
        throws DataQueryException, DataNetworkException
    {
        return doDeleteQuery(primaryKey);
    }

    public int doDeleteAllQuery(final String tableName, final String where, final Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                return doDeleteQueryImpl(tableName, where, bindVars);
            }

        }
)).intValue();
    }

    public Row doFindByPrimaryKeyQuery(String tableName, PrimaryKey primaryKey, String conditions)
        throws DataQueryException, DataNetworkException, DataFindException
    {
        return doFindByPrimaryKeyQuery(primaryKey, conditions);
    }

    public Row doFindByPrimaryKeyQuery(String tableName, PrimaryKey primaryKey, String where, String conditions, Object bindVars[])
        throws DataQueryException, DataNetworkException, DataFindException
    {
        return doFindByPrimaryKeyQuery(primaryKey, where, conditions, bindVars);
    }

    public List doFindAllQuery(final String tableName, final String where, final String conditions, final Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return (List)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                return doFindAllQueryImpl(tableName, where, conditions, bindVars);
            }

        }
);
    }

    public List doFindAllQuery(final String tableName, final String where, final String orderBy, final String conditions, final Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return (List)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                return doFindAllQueryImpl(tableName, where, orderBy, conditions, bindVars);
            }

        }
);
    }

    public ListPage doFindAllQuery(final String tableName, final String where, final String orderBy, final String conditions, final Object bindVars[], final int pageSize, final int pageNumber)
        throws DataQueryException, DataNetworkException
    {
        return (ListPage)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                return doFindAllQueryImpl(tableName, where, orderBy, conditions, bindVars, pageSize, pageNumber);
            }

        }
);
    }

    public Object[] doProcedureCallQuery(final String procName, final Object inArgs[], final Object outArgs[])
        throws DataQueryException, DataNetworkException
    {
        return (Object[])doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                return ((Object) (doProcedureCallQueryImpl(procName, inArgs, outArgs, false)));
            }

        }
);
    }

    public Object[] doReadOnlyProcedureCallQuery(final String procName, final Object inArgs[], final Object outArgs[])
        throws DataQueryException, DataNetworkException
    {
        return (Object[])doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                return ((Object) (doProcedureCallQueryImpl(procName, inArgs, outArgs, true)));
            }

        }
);
    }

    public boolean doLockPartititionQuery(final boolean waitOnBusy)
        throws DataQueryException, DataNetworkException
    {
        return ((Boolean)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                return doLockPartititionQueryImpl(waitOnBusy);
            }

        }
)).booleanValue();
    }

    public int doGetCountQuery(final String tableName, final String where, final Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                return doGetCountQueryImpl(tableName, where, bindVars);
            }

        }
)).intValue();
    }

    public long doGetNewPkValueQuery(final String tableName)
        throws DataQueryException, DataNetworkException
    {
        return ((Long)doTxImplicit(tx, new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException, DataCallbackException
            {
                return doGetNewPkValueQueryImpl(tableName);
            }

        }
)).longValue();
    }

    public Object doInsertQuery(final Row initialValues)
        throws DataQueryException, DataNetworkException
    {
        return doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                String tableName = initialValues.getRowType().getTableName();
                return doInsertQueryImpl(tableName, initialValues);
            }

        }
);
    }

    public int doUpdateQuery(final Row newValues)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                String tableName = newValues.getRowType().getTableName();
                return doUpdateQueryImpl(tableName, newValues);
            }

        }
)).intValue();
    }

    public int doUpdateQuery(final Row newValues, final String where, final Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                String tableName = newValues.getRowType().getTableName();
                return doUpdateQueryImpl(tableName, newValues, where, bindVars);
            }

        }
)).intValue();
    }

    public int doUpdateQuery(final PrimaryKey primaryKey, final Map changes)
        throws DataQueryException, DataNetworkException
    {
        if(changes == null || changes.size() == 0)
            throw new IllegalArgumentException("supplied argument contains no changes.");
        else
            return ((Integer)doTxImplicit(tx, new TransactionCallbackImpl() {

                public Object processImpl()
                    throws ConnectionException, QueryException
                {
                    String tableName = primaryKey.getRowType().getTableName();
                    return doUpdateQueryImpl(tableName, primaryKey, changes);
                }

            }
)).intValue();
    }

    public int doUpdateQuery(final PrimaryKey primaryKey, final String where, final Object bindVars[], final Map changes)
        throws DataQueryException, DataNetworkException
    {
        if(changes == null || changes.size() == 0)
            throw new IllegalArgumentException("supplied argument contains no changes.");
        else
            return ((Integer)doTxImplicit(tx, new TransactionCallbackImpl() {

                public Object processImpl()
                    throws ConnectionException, QueryException
                {
                    String tableName = primaryKey.getRowType().getTableName();
                    return doUpdateQueryImpl(tableName, primaryKey, where, bindVars, changes);
                }

            }
)).intValue();
    }

    public int doUpdateAllQuery(final RowType rowType, final Map changes)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                String tableName = rowType.getTableName();
                return doUpdateAllQueryImpl(tableName, null, null, changes);
            }

        }
)).intValue();
    }

    public int doUpdateAllQuery(final RowType rowType, final String where, final Object bindVars[], final Map changes)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                String tableName = rowType.getTableName();
                return doUpdateAllQueryImpl(tableName, where, bindVars, changes);
            }

        }
)).intValue();
    }

    public int doDeleteQuery(final PrimaryKey primaryKey)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                String tableName = primaryKey.getRowType().getTableName();
                return doDeleteQueryImpl(tableName, primaryKey);
            }

        }
)).intValue();
    }

    public int doDeleteQuery(final PrimaryKey primaryKey, final String where, final Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                String tableName = primaryKey.getRowType().getTableName();
                return doDeleteQueryImpl(tableName, primaryKey, where, bindVars);
            }

        }
)).intValue();
    }

    public int doDeleteAllQuery(final RowType rowType)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                String tableName = rowType.getTableName();
                return doDeleteQueryImpl(tableName, null, null);
            }

        }
)).intValue();
    }

    public int doDeleteAllQuery(final RowType rowType, final String where, final Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                String tableName = rowType.getTableName();
                return doDeleteQueryImpl(tableName, where, bindVars);
            }

        }
)).intValue();
    }

    public Row doFindByPrimaryKeyQuery(final PrimaryKey primaryKey)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        return (Row)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                String tableName = primaryKey.getRowType().getTableName();
                return doFindByPrimaryKeyQueryImpl(tableName, primaryKey, null);
            }

        }
);
    }

    public Row doFindByPrimaryKeyQuery(final PrimaryKey primaryKey, final String conditions)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        return (Row)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                String tableName = primaryKey.getRowType().getTableName();
                return doFindByPrimaryKeyQueryImpl(tableName, primaryKey, conditions);
            }

        }
);
    }

    public Row doFindByPrimaryKeyQuery(final PrimaryKey primaryKey, final String where, final Object bindVars[])
        throws DataFindException, DataQueryException, DataNetworkException
    {
        return (Row)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                String tableName = primaryKey.getRowType().getTableName();
                return doFindByPrimaryKeyQueryImpl(tableName, primaryKey, where, null, bindVars);
            }

        }
);
    }

    public Row doFindByPrimaryKeyQuery(final PrimaryKey primaryKey, final String where, final String conditions, final Object bindVars[])
        throws DataFindException, DataQueryException, DataNetworkException
    {
        return (Row)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                String tableName = primaryKey.getRowType().getTableName();
                return doFindByPrimaryKeyQueryImpl(tableName, primaryKey, where, conditions, bindVars);
            }

        }
);
    }

    public List doFindAllQuery(final RowType rowType)
        throws DataQueryException, DataNetworkException
    {
        return (List)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                String tableName = rowType.getTableName();
                return doFindAllQueryImpl(tableName, null, null, null);
            }

        }
);
    }

    public List doFindAllQuery(final RowType rowType, final String conditions)
        throws DataQueryException, DataNetworkException
    {
        return (List)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                String tableName = rowType.getTableName();
                return doFindAllQueryImpl(tableName, null, conditions, null);
            }

        }
);
    }

    public List doFindAllQuery(final RowType rowType, final String where, final Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return (List)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                String tableName = rowType.getTableName();
                return doFindAllQueryImpl(tableName, where, null, bindVars);
            }

        }
);
    }

    public List doFindAllQuery(final RowType rowType, final String where, final String conditions, final Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return (List)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                String tableName = rowType.getTableName();
                return doFindAllQueryImpl(tableName, where, conditions, bindVars);
            }

        }
);
    }

    public List doFindAllQuery(final RowType rowType, final String where, final String orderBy, final String conditions, final Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return (List)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                String tableName = rowType.getTableName();
                return doFindAllQueryImpl(tableName, where, orderBy, conditions, bindVars);
            }

        }
);
    }

    public List doFindAllQuery(final RowType rowType, final String where, final String orderBy, final String conditions, final Object bindVars[], final RowType rowTypes[])
        throws DataQueryException, DataNetworkException
    {
        return (List)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                String tableName = rowType.getTableName();
                return doFindAllQueryImpl(tableName, where, orderBy, conditions, bindVars, rowTypes);
            }

        }
);
    }

    public ListPage doFindAllQuery(final RowType rowType, final String where, final String orderBy, final String conditions, final Object bindVars[], final int pageSize, final int pageNumber)
        throws DataQueryException, DataNetworkException
    {
        return (ListPage)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                String tableName = rowType.getTableName();
                return doFindAllQueryImpl(tableName, where, orderBy, conditions, bindVars, pageSize, pageNumber);
            }

        }
);
    }

    public ListPage doFindAllQuery(final RowType rowType, final String where, final String orderBy, final String conditions, final Object bindVars[], final int pageSize, final int pageNumber, 
            final RowType rowTypes[])
        throws DataQueryException, DataNetworkException
    {
        return (ListPage)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                String tableName = rowType.getTableName();
                return doFindAllQueryImpl(tableName, where, orderBy, conditions, bindVars, pageSize, pageNumber, rowTypes);
            }

        }
);
    }

    public int doGetCountQuery(final RowType rowType)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                String tableName = rowType.getTableName();
                return doGetCountQueryImpl(tableName, null, null);
            }

        }
)).intValue();
    }

    public int doGetCountQuery(final RowType rowType, final String where, final Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doTxImplicit(tx, new TransactionCallbackImpl() {

            public Object processImpl()
                throws ConnectionException, QueryException
            {
                String tableName = rowType.getTableName();
                return doGetCountQueryImpl(tableName, where, bindVars);
            }

        }
)).intValue();
    }

    public long doGetNewPkValueQuery(final RowType rowType)
        throws DataQueryException, DataNetworkException
    {
        return ((Long)doTxImplicit(tx, new TransactionCallback() {

            public Object process()
                throws DataQueryException, DataNetworkException, DataCallbackException
            {
                String tableName = rowType.getTableName();
                return doGetNewPkValueQueryImpl(tableName);
            }

        }
)).longValue();
    }

    public Object doQueryImpl(BatchedQuery query)
        throws DataQueryException, DataNetworkException
    {
        int qtype;
        String name;
        Object qa[];
        qtype = query.getQueryType();
        name = query.getTableName();
        qa = query.getQueryArgs();
        qtype;
        JVM INSTR lookupswitch 19: default 641
    //                   10: 180
    //                   11: 193
    //                   20: 206
    //                   21: 219
    //                   22: 239
    //                   23: 266
    //                   24: 293
    //                   30: 327
    //                   31: 340
    //                   32: 360
    //                   40: 387
    //                   41: 407
    //                   50: 441
    //                   51: 482
    //                   60: 544
    //                   70: 578
    //                   80: 600
    //                   90: 615
    //                   100: 621;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20
_L2:
        return doInsertQueryImpl(name, (Row)qa[0]);
_L3:
        return doInsertQueryImpl(name, (Map)qa[0]);
_L4:
        return doUpdateQueryImpl(name, (Row)qa[0]);
_L5:
        return doUpdateQueryImpl(name, (PrimaryKey)qa[0], (Map)qa[1]);
_L6:
        return doUpdateAllQueryImpl(name, (String)qa[0], (Object[])qa[1], (Map)qa[2]);
_L7:
        return doUpdateQueryImpl(name, (Row)qa[0], (String)qa[1], (Object[])qa[2]);
_L8:
        return doUpdateQueryImpl(name, (PrimaryKey)qa[0], (String)qa[1], (Object[])qa[2], (Map)qa[3]);
_L9:
        return doDeleteQueryImpl(name, (PrimaryKey)qa[0]);
_L10:
        return doDeleteQueryImpl(name, (String)qa[0], (Object[])qa[1]);
_L11:
        return doDeleteQueryImpl(name, (PrimaryKey)qa[0], (String)qa[1], (Object[])qa[2]);
_L12:
        return doFindByPrimaryKeyQueryImpl(name, (PrimaryKey)qa[0], (String)qa[1]);
_L13:
        return doFindByPrimaryKeyQueryImpl(name, (PrimaryKey)qa[0], (String)qa[1], (String)qa[2], (Object[])qa[3]);
_L14:
        return doFindAllQueryImpl(name, (String)qa[0], (String)qa[1], (String)qa[2], (Object[])qa[3], (RowType[])qa[4]);
_L15:
        return doFindAllQueryImpl(name, (String)qa[0], (String)qa[1], (String)qa[2], (Object[])qa[3], ((Integer)qa[4]).intValue(), ((Integer)qa[5]).intValue(), (RowType[])qa[6]);
_L16:
        boolean isReadOnlySP = ((Boolean)qa[2]).booleanValue();
        return ((Object) (doProcedureCallQueryImpl(name, (Object[])qa[0], (Object[])qa[1], isReadOnlySP)));
_L17:
        return doTransactionImpl(((Integer)qa[0]).intValue(), (TransactionCallback)qa[1]);
_L18:
        return doLockPartititionQueryImpl(((Boolean)qa[0]).booleanValue());
_L19:
        return doGetNewPkValueQueryImpl(name);
_L20:
        return doGetCountQueryImpl(name, (String)qa[0], (Object[])qa[1]);
_L1:
        try
        {
            throw new IllegalArgumentException("bad batched query type: " + qtype);
        }
        catch(FindException qe)
        {
            throw new DataFindException(qe.getMessage());
        }
        catch(QueryException qe)
        {
            throw new DataQueryException(qe.getMessage());
        }
        catch(ConnectionException ce)
        {
            throw new DataNetworkException(ce.getMessage());
        }
    }

    protected Table getTable(String tableName)
    {
        Table t = db.getTable(tableName);
        if(t == null)
            throw new IllegalArgumentException("unknown table name: " + tableName);
        else
            return t;
    }

    protected Object doInsertQueryImpl(String tableName, Row initialValues)
        throws ConnectionException, InsertException
    {
        return getTable(tableName).insert(initialValues);
    }

    protected Object doInsertQueryImpl(String tableName, Map initialValues)
        throws ConnectionException, InsertException
    {
        return getTable(tableName).insert(initialValues);
    }

    protected Integer doUpdateQueryImpl(String tableName, Row newValues)
        throws ConnectionException, UpdateException
    {
        return new Integer(getTable(tableName).update(newValues));
    }

    protected Integer doUpdateQueryImpl(String tableName, PrimaryKey primaryKey, Map changes)
        throws ConnectionException, UpdateException
    {
        return new Integer(getTable(tableName).update(primaryKey, changes));
    }

    protected Integer doUpdateAllQueryImpl(String tableName, String where, Object bindVars[], Map changes)
        throws ConnectionException, UpdateException
    {
        return new Integer(getTable(tableName).updateAll(where, bindVars, changes));
    }

    protected Object doUpdateQueryImpl(String tableName, Row row, String where, Object bindVars[])
        throws ConnectionException, UpdateException
    {
        Table table = getTable(tableName);
        int count = table.update(row, where, bindVars);
        return new Integer(count);
    }

    protected Object doUpdateQueryImpl(String tableName, PrimaryKey primaryKey, String where, Object bindVars[], Map changes)
        throws ConnectionException, UpdateException
    {
        Table table = getTable(tableName);
        int count = table.update(primaryKey, where, bindVars, changes);
        return new Integer(count);
    }

    private Integer doDeleteQueryImpl(String tableName, PrimaryKey primaryKey, String where, Object bindVars[])
        throws ConnectionException, DeleteException
    {
        Table table = getTable(tableName);
        int count = table.delete(primaryKey, where, bindVars);
        return new Integer(count);
    }

    private Integer doDeleteQueryImpl(String tableName, PrimaryKey primaryKey)
        throws ConnectionException, DeleteException
    {
        return new Integer(getTable(tableName).delete(primaryKey));
    }

    protected Integer doDeleteQueryImpl(String tableName, String where, Object bindVars[])
        throws ConnectionException, DeleteException
    {
        return new Integer(getTable(tableName).deleteAll(where, bindVars));
    }

    protected Object doDeleteWhereQueryImpl(String tableName, PrimaryKey primaryKey, String where, Object bindVars[])
        throws ConnectionException, DeleteException
    {
        Table table = getTable(tableName);
        int count = table.delete(primaryKey, where, bindVars);
        return new Integer(count);
    }

    protected Row doFindByPrimaryKeyQueryImpl(String tableName, PrimaryKey primaryKey, String conditions)
        throws ConnectionException, SelectException, FindException
    {
        return getTable(tableName).select(primaryKey, conditions);
    }

    protected Row doFindByPrimaryKeyQueryImpl(String tableName, PrimaryKey primaryKey, String where, String conditions, Object bindVars[])
        throws ConnectionException, SelectException, FindException
    {
        return getTable(tableName).select(primaryKey, where, conditions, bindVars);
    }

    protected List doFindAllQueryImpl(String tableName, String where, String conditions, Object bindVars[])
        throws ConnectionException, SelectException
    {
        return getTable(tableName).selectAll(where, null, conditions, bindVars);
    }

    protected List doFindAllQueryImpl(String tableName, String where, String orderBy, String conditions, Object bindVars[])
        throws ConnectionException, SelectException
    {
        return getTable(tableName).selectAll(where, orderBy, conditions, bindVars);
    }

    protected List doFindAllQueryImpl(String tableName, String where, String orderBy, String conditions, Object bindVars[], RowType rowTypes[])
        throws ConnectionException, SelectException
    {
        return getTable(tableName).selectAll(where, orderBy, conditions, bindVars, rowTypes);
    }

    protected ListPage doFindAllQueryImpl(String tableName, String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber)
        throws ConnectionException, SelectException
    {
        Table table = getTable(tableName);
        int i = pageSize * pageNumber;
        int j = pageSize + i;
        com.fitechlabs.dbind.ListSubset list = table.selectAll(where, orderBy, conditions, bindVars, i, j);
        return new ArrayListPage(list, pageSize);
    }

    protected ListPage doFindAllQueryImpl(String tableName, String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber, 
            RowType rowTypes[])
        throws ConnectionException, SelectException
    {
        Table table = getTable(tableName);
        int i = pageSize * pageNumber;
        int j = pageSize + i;
        com.fitechlabs.dbind.ListSubset list = table.selectAll(where, orderBy, conditions, bindVars, i, j, rowTypes);
        return new ArrayListPage(list, pageSize);
    }

    protected Object[] doProcedureCallQueryImpl(String procName, Object inArgs[], Object outArgs[], boolean isReadOnly)
        throws ConnectionException, CallException
    {
        Object results[] = new Object[outArgs.length];
        System.arraycopy(((Object) (outArgs)), 0, ((Object) (results)), 0, outArgs.length);
        db.call(procName, inArgs, results, isReadOnly);
        return results;
    }

    protected Integer doGetCountQueryImpl(String tableName, String where, Object bindVars[])
        throws ConnectionException, SelectException
    {
        return new Integer(getTable(tableName).selectCount(where, bindVars));
    }

    protected Object doGetNewPkValueQueryImpl(String tableName)
        throws DataQueryException, DataNetworkException
    {
        return new Long(Processors.getNewPkValue(tableName));
    }

    protected Object doTransactionImpl(int transactionSemantics, TransactionCallback callback)
        throws DataQueryException, DataNetworkException, DataCallbackException, DataRollbackException
    {
        return doTransaction(transactionSemantics, callback);
    }

    protected Boolean doLockPartititionQueryImpl(boolean waitOnBusy)
        throws ConnectionException, SelectException
    {
        return Boolean.FALSE;
    }

    public boolean lockAccount(long account_id, boolean waitOnBusy)
        throws DataQueryException, DataNetworkException, IllegalStateException, IllegalArgumentException
    {
        if(!db.isTransactionActive())
            throw new IllegalStateException("no active transaction.");
        if(!waitOnBusy)
            break MISSING_BLOCK_LABEL_56;
        db.call("exec.lock_account_wait", new Object[] {
            new Long(account_id)
        }, null, true);
        return true;
        Object outargs[];
        outargs = (new Object[] {
            null, new Integer(4)
        });
        db.call("exec.lock_account_nowait", new Object[] {
            new Long(account_id)
        }, outargs, true);
        return ((Number)outargs[1]).intValue() == 1;
        CallException ce;
        ce;
        throw new InternalError("call failed: " + ce);
        ce;
        throw new RuntimeException("database connection not available: " + ce);
    }

    public void forceReadonlyMode(RowType rowType, boolean isPreload)
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
    protected Database db;
    protected int tx;

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.data.QueryProcessorStdImpl.class);
        DBG = log.ison();
    }



}

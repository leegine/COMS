// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QPFilteringImpl.java

package com.fitechlabs.xtrade.kernel.data.impl;

import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.kernel.data.*;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data.impl:
//            QPBeanImpl, QPStdImpl

public abstract class QPFilteringImpl
    implements QueryProcessor
{

    Long getId()
    {
        return _flddelegate.getId();
    }

    public QPFilteringImpl(QPBeanImpl delegate)
    {
        _flddelegate = delegate;
    }

    protected abstract void assertValidInsert(Row row);

    protected abstract void assertValidInsert(Map map);

    protected abstract void assertValidUpdate(Row row);

    protected abstract void assertValidChanges(Map map);

    protected abstract void assertValidAccount(long l);

    protected abstract String filteredWhere(String s);

    protected abstract Object[] filteredBindVars(Object aobj[]);

    public List doFindAllQuery(RowType rowType, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return _flddelegate.doFindAllQuery(rowType, filteredWhere(where), filteredBindVars(bindVars));
    }

    public List doFindAllQuery(RowType rowType, String where, String conditions, Object bindVars[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        return _flddelegate.doFindAllQuery(rowType, filteredWhere(where), conditions, filteredBindVars(bindVars));
    }

    public ListPage doFindAllQuery(RowType rowType, String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber)
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        return _flddelegate.doFindAllQuery(rowType, filteredWhere(where), orderBy, conditions, filteredBindVars(bindVars), pageSize, pageNumber);
    }

    public ListPage doFindAllQuery(RowType rowType, String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber, 
            RowType rowTypes[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        return _flddelegate.doFindAllQuery(rowType, filteredWhere(where), orderBy, conditions, filteredBindVars(bindVars), pageSize, pageNumber, rowTypes);
    }

    public List doFindAllQuery(RowType rowType, String where, String orderBy, String conditions, Object bindVars[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        return _flddelegate.doFindAllQuery(rowType, filteredWhere(where), orderBy, conditions, filteredBindVars(bindVars));
    }

    public List doFindAllQuery(RowType rowType, String where, String orderBy, String conditions, Object bindVars[], RowType rowTypes[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        return _flddelegate.doFindAllQuery(rowType, filteredWhere(where), orderBy, conditions, filteredBindVars(bindVars), rowTypes);
    }

    public List doFindAllQuery(RowType rowType, String conditions)
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        return _flddelegate.doFindAllQuery(rowType, filteredWhere(null), conditions, filteredBindVars(null));
    }

    public List doFindAllQuery(RowType rowType)
        throws DataQueryException, DataNetworkException
    {
        return _flddelegate.doFindAllQuery(rowType, filteredWhere(null), null, filteredBindVars(null));
    }

    public List doFindAllQuery(String tableName, String where, String conditions, Object bindVars[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        return _flddelegate.doFindAllQuery(tableName, filteredWhere(where), conditions, filteredBindVars(bindVars));
    }

    public ListPage doFindAllQuery(String tableName, String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber)
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        return _flddelegate.doFindAllQuery(tableName, filteredWhere(where), orderBy, conditions, filteredBindVars(bindVars), pageSize, pageNumber);
    }

    public List doFindAllQuery(String tableName, String where, String orderBy, String conditions, Object bindVars[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        return _flddelegate.doFindAllQuery(tableName, filteredWhere(where), orderBy, conditions, filteredBindVars(bindVars));
    }

    public Row doFindByPrimaryKeyQuery(PrimaryKey primaryKey, String where, Object bindVars[])
        throws DataFindException, DataQueryException, DataNetworkException
    {
        return _flddelegate.doFindByPrimaryKeyQuery(primaryKey, filteredWhere(where), filteredBindVars(bindVars));
    }

    public Row doFindByPrimaryKeyQuery(PrimaryKey primaryKey, String where, String conditions, Object bindVars[])
        throws DataFindException, DataQueryException, DataNetworkException, IllegalStateException
    {
        return _flddelegate.doFindByPrimaryKeyQuery(primaryKey, filteredWhere(where), conditions, filteredBindVars(bindVars));
    }

    public Row doFindByPrimaryKeyQuery(PrimaryKey primaryKey, String conditions)
        throws DataFindException, DataQueryException, DataNetworkException, IllegalStateException
    {
        return _flddelegate.doFindByPrimaryKeyQuery(primaryKey, filteredWhere(null), conditions, filteredBindVars(null));
    }

    public Row doFindByPrimaryKeyQuery(PrimaryKey primaryKey)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        return _flddelegate.doFindByPrimaryKeyQuery(primaryKey, filteredWhere(null), null, filteredBindVars(null));
    }

    public Row doFindByPrimaryKeyQuery(String tableName, PrimaryKey primaryKey, String where, String conditions, Object bindVars[])
        throws DataFindException, DataQueryException, DataNetworkException, IllegalStateException
    {
        return _flddelegate.doFindByPrimaryKeyQuery(tableName, primaryKey, filteredWhere(where), conditions, filteredBindVars(bindVars));
    }

    public Row doFindByPrimaryKeyQuery(String tableName, PrimaryKey primaryKey, String conditions)
        throws DataFindException, DataQueryException, DataNetworkException, IllegalStateException
    {
        return _flddelegate.doFindByPrimaryKeyQuery(primaryKey, filteredWhere(null), conditions, filteredBindVars(null));
    }

    public int doGetCountQuery(RowType rowType, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return _flddelegate.doGetCountQuery(rowType, filteredWhere(where), filteredBindVars(bindVars));
    }

    public int doGetCountQuery(RowType rowType)
        throws DataQueryException, DataNetworkException
    {
        return _flddelegate.doGetCountQuery(rowType, filteredWhere(null), filteredBindVars(null));
    }

    public int doGetCountQuery(String tableName, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return _flddelegate.doGetCountQuery(tableName, filteredWhere(where), filteredBindVars(bindVars));
    }

    public int doDeleteAllQuery(RowType rowType, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return _flddelegate.doDeleteAllQuery(rowType, filteredWhere(where), filteredBindVars(bindVars));
    }

    public int doDeleteAllQuery(RowType rowType)
        throws DataQueryException, DataNetworkException
    {
        return _flddelegate.doDeleteAllQuery(rowType, filteredWhere(null), filteredBindVars(null));
    }

    public int doDeleteAllQuery(String tableName, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return _flddelegate.doDeleteAllQuery(tableName, filteredWhere(where), filteredBindVars(bindVars));
    }

    public int doDeleteQuery(PrimaryKey primaryKey, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return _flddelegate.doDeleteQuery(primaryKey, filteredWhere(where), filteredBindVars(bindVars));
    }

    public int doDeleteQuery(PrimaryKey primaryKey)
        throws DataQueryException, DataNetworkException
    {
        return _flddelegate.doDeleteQuery(primaryKey, filteredWhere(null), filteredBindVars(null));
    }

    public int doDeleteQuery(String tableName, PrimaryKey primaryKey)
        throws DataQueryException, DataNetworkException
    {
        return _flddelegate.doDeleteQuery(primaryKey, filteredWhere(null), filteredBindVars(null));
    }

    public Object doInsertQuery(Row initialValues)
        throws DataQueryException, DataNetworkException
    {
        assertValidInsert(initialValues);
        return _flddelegate.doInsertQuery(initialValues);
    }

    public Object doInsertQuery(String tableName, Map initialValues)
        throws DataQueryException, DataNetworkException
    {
        assertValidInsert(initialValues);
        return _flddelegate.doInsertQuery(tableName, initialValues);
    }

    public Object doInsertQuery(String tableName, Row initialValues)
        throws DataQueryException, DataNetworkException
    {
        assertValidInsert(initialValues);
        return _flddelegate.doInsertQuery(initialValues);
    }

    public int doUpdateAllQuery(RowType rowType, Map changes)
        throws DataQueryException, DataNetworkException
    {
        assertValidChanges(changes);
        return _flddelegate.doUpdateAllQuery(rowType, filteredWhere(null), filteredBindVars(null), changes);
    }

    public int doUpdateAllQuery(RowType rowType, String where, Object bindVars[], Map changes)
        throws DataQueryException, DataNetworkException
    {
        assertValidChanges(changes);
        return _flddelegate.doUpdateAllQuery(rowType, filteredWhere(where), filteredBindVars(bindVars), changes);
    }

    public int doUpdateAllQuery(String tableName, String where, Object bindVars[], Map changes)
        throws DataQueryException, DataNetworkException
    {
        assertValidChanges(changes);
        return _flddelegate.doUpdateAllQuery(tableName, filteredWhere(where), filteredBindVars(bindVars), changes);
    }

    public int doUpdateQuery(PrimaryKey primaryKey, Map changes)
        throws DataQueryException, DataNetworkException
    {
        assertValidChanges(changes);
        return _flddelegate.doUpdateQuery(primaryKey, filteredWhere(null), filteredBindVars(null), changes);
    }

    public int doUpdateQuery(PrimaryKey primaryKey, String where, Object bindVars[], Map changes)
        throws DataQueryException, DataNetworkException
    {
        assertValidChanges(changes);
        return _flddelegate.doUpdateQuery(primaryKey, filteredWhere(where), filteredBindVars(bindVars), changes);
    }

    public int doUpdateQuery(Row newValues, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        assertValidUpdate(newValues);
        return _flddelegate.doUpdateQuery(newValues, filteredWhere(where), filteredBindVars(bindVars));
    }

    public int doUpdateQuery(Row newValues)
        throws DataQueryException, DataNetworkException
    {
        assertValidUpdate(newValues);
        return _flddelegate.doUpdateQuery(newValues, filteredWhere(null), filteredBindVars(null));
    }

    public int doUpdateQuery(String tableName, PrimaryKey primaryKey, Map changes)
        throws DataQueryException, DataNetworkException
    {
        assertValidChanges(changes);
        return _flddelegate.doUpdateQuery(primaryKey, filteredWhere(null), filteredBindVars(null), changes);
    }

    public int doUpdateQuery(String tableName, Row newValues)
        throws DataQueryException, DataNetworkException
    {
        assertValidUpdate(newValues);
        return _flddelegate.doUpdateQuery(newValues, filteredWhere(null), filteredBindVars(null));
    }

    public boolean lockAccount(long account_id, boolean waitOnBusy)
        throws DataQueryException, DataNetworkException, IllegalStateException, IllegalArgumentException
    {
        assertValidAccount(account_id);
        return _flddelegate.lockAccount(account_id, waitOnBusy);
    }

    public long doGetNewPkValueQuery(RowType rowType)
        throws DataQueryException, DataNetworkException
    {
        return _flddelegate.doGetNewPkValueQuery(rowType);
    }

    public long doGetNewPkValueQuery(String tableName)
        throws DataQueryException, DataNetworkException
    {
        return _flddelegate.doGetNewPkValueQuery(tableName);
    }

    public boolean doLockPartititionQuery(boolean waitOnBusy)
        throws DataQueryException, DataNetworkException
    {
        Long id = getId();
        if(id == null || id.longValue() == 0L)
            throw new UnsupportedOperationException("cannot lock partition 0 using QPFilteringImpl.doLockPartitionQuery()");
        else
            return _flddelegate.lockAccount(id.longValue(), waitOnBusy);
    }

    public Object[] doProcedureCallQuery(String procName, Object inArgs[], Object outArgs[])
        throws DataQueryException, DataNetworkException
    {
        return _flddelegate.doProcedureCallQuery(procName, inArgs, outArgs);
    }

    public Object[] doReadOnlyProcedureCallQuery(String procName, Object inArgs[], Object outArgs[])
        throws DataQueryException, DataNetworkException
    {
        return _flddelegate.doReadOnlyProcedureCallQuery(procName, inArgs, outArgs);
    }

    public Object[] doQueries(BatchedQuery queries[])
        throws DataNetworkException, DataBatchException, DataRollbackException
    {
        return _flddelegate.doQueries(queries);
    }

    public Object[] doQueries(int transactionSemantics, BatchedQuery queries[])
        throws DataNetworkException, DataBatchException, DataRollbackException
    {
        return _flddelegate.doQueries(transactionSemantics, queries);
    }

    public Object doQuery(BatchedQuery query)
        throws DataNetworkException, DataQueryException, DataRollbackException
    {
        return _flddelegate.doQuery(query);
    }

    public Object doQuery(int transactionSemantics, BatchedQuery query)
        throws DataNetworkException, DataQueryException, DataRollbackException
    {
        return _flddelegate.doQuery(transactionSemantics, query);
    }

    public Object doTransaction(int transactionSemantics, TransactionCallback callback)
        throws DataNetworkException, DataQueryException, DataCallbackException, DataRollbackException
    {
        return _flddelegate.doTransaction(transactionSemantics, callback);
    }

    public Object doTransaction(TransactionCallback callback)
        throws DataNetworkException, DataQueryException, DataCallbackException, DataRollbackException
    {
        return _flddelegate.doTransaction(callback);
    }

    public void forceReadonlyMode(RowType rowtype, boolean flag)
        throws UnsupportedOperationException, DataNetworkException
    {
    }

    private QPBeanImpl _flddelegate;
}

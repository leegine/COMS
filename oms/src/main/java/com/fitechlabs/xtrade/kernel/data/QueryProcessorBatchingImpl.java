// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QueryProcessorBatchingImpl.java

package com.fitechlabs.xtrade.kernel.data;

import com.fitechlabs.dbind.*;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data:
//            QueryProcessorStdImpl, DataQueryException, DataNetworkException, DataFindException, 
//            BatchedQuery

public class QueryProcessorBatchingImpl extends QueryProcessorStdImpl
{

    public QueryProcessorBatchingImpl(Database database)
    {
        super(database);
    }

    public QueryProcessorBatchingImpl(Database database, int defaultTransactionSemantics)
    {
        super(database, defaultTransactionSemantics);
    }

    public Object doInsertQuery(String tableName, Row initialValues)
        throws DataQueryException, DataNetworkException
    {
        return doQuery(super.tx, BatchedQuery.createInsertQuery(tableName, initialValues));
    }

    public Object doInsertQuery(String tableName, Map initialValues)
        throws DataQueryException, DataNetworkException
    {
        return doQuery(super.tx, BatchedQuery.createInsertQuery(tableName, initialValues));
    }

    public int doUpdateQuery(String tableName, Row newValues)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doQuery(super.tx, BatchedQuery.createUpdateQuery(tableName, newValues))).intValue();
    }

    public int doUpdateQuery(String tableName, Object primaryKey, Map changes)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doQuery(super.tx, BatchedQuery.createUpdateQuery(tableName, primaryKey, changes))).intValue();
    }

    public int doUpdateAllQuery(String tableName, String where, Object bindVars[], Map changes)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doQuery(super.tx, BatchedQuery.createUpdateAllQuery(tableName, where, bindVars, changes))).intValue();
    }

    public int doDeleteQuery(String tableName, Object primaryKey)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doQuery(super.tx, BatchedQuery.createDeleteQuery(tableName, primaryKey))).intValue();
    }

    public int doDeleteAllQuery(String tableName, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doQuery(super.tx, BatchedQuery.createDeleteAllQuery(tableName, where, bindVars))).intValue();
    }

    public Row doFindByPrimaryKeyQuery(String tableName, Object primaryKey, String conditions)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        return (Row)doQuery(super.tx, BatchedQuery.createFindByPrimaryKeyQuery(tableName, primaryKey, conditions));
    }

    public Row doFindByPrimaryKeyQuery(String tableName, Object primaryKey, String where, String conditions, Object bindVars[])
        throws DataFindException, DataQueryException, DataNetworkException
    {
        return (Row)doQuery(super.tx, BatchedQuery.createFindByPrimaryKeyQuery(tableName, primaryKey, where, conditions, bindVars));
    }

    public List doFindAllQuery(String tableName, String where, String conditions, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return (List)doQuery(super.tx, BatchedQuery.createFindAllQuery(tableName, where, conditions, bindVars));
    }

    public List doFindAllQuery(String tableName, String where, String orderBy, String conditions, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return (List)doQuery(super.tx, BatchedQuery.createFindAllQuery(tableName, where, orderBy, conditions, bindVars));
    }

    public ListPage doFindAllQuery(String tableName, String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber)
        throws DataQueryException, DataNetworkException
    {
        return (ListPage)doQuery(super.tx, BatchedQuery.createFindAllQuery(tableName, where, orderBy, conditions, bindVars, pageSize, pageNumber));
    }

    public Object[] doProcedureCallQuery(String procName, Object inArgs[], Object outArgs[])
        throws DataQueryException, DataNetworkException
    {
        return (Object[])doQuery(super.tx, BatchedQuery.createProcedureCallQuery(procName, inArgs, outArgs, false));
    }

    public Object[] doReadOnlyProcedureCallQuery(String procName, Object inArgs[], Object outArgs[])
        throws DataQueryException, DataNetworkException
    {
        return (Object[])doQuery(super.tx, BatchedQuery.createProcedureCallQuery(procName, inArgs, outArgs, true));
    }

    public boolean doLockPartititionQuery(boolean waitOnBusy)
        throws DataQueryException, DataNetworkException
    {
        return ((Boolean)doQuery(super.tx, BatchedQuery.createLockPartitionQuery(waitOnBusy))).booleanValue();
    }

    public int doGetCountQuery(String tableName, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doQuery(super.tx, BatchedQuery.createGetCountQuery(tableName, where, bindVars))).intValue();
    }

    public long doGetNewPkValueQuery(String tableName)
        throws DataQueryException, DataNetworkException
    {
        return ((Long)doQuery(super.tx, BatchedQuery.createGetNewPkValueQuery(tableName))).longValue();
    }

    public Object doInsertQuery(Row initialValues)
        throws DataQueryException, DataNetworkException
    {
        return doQuery(super.tx, BatchedQuery.createInsertQuery(initialValues));
    }

    public int doUpdateQuery(Row newValues)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doQuery(super.tx, BatchedQuery.createUpdateQuery(newValues))).intValue();
    }

    public int doUpdateQuery(Row newValues, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doQuery(super.tx, BatchedQuery.createUpdateQuery(newValues, where, bindVars))).intValue();
    }

    public int doUpdateQuery(PrimaryKey primaryKey, Map changes)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doQuery(super.tx, BatchedQuery.createUpdateQuery(primaryKey, changes))).intValue();
    }

    public int doUpdateQuery(PrimaryKey primaryKey, String where, Object bindVars[], Map changes)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doQuery(super.tx, BatchedQuery.createUpdateQuery(primaryKey, where, bindVars, changes))).intValue();
    }

    public int doUpdateAllQuery(RowType type, Map changes)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doQuery(super.tx, BatchedQuery.createUpdateAllQuery(type, changes))).intValue();
    }

    public int doUpdateAllQuery(RowType type, String where, Object bindVars[], Map changes)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doQuery(super.tx, BatchedQuery.createUpdateAllQuery(type, where, bindVars, changes))).intValue();
    }

    public int doDeleteQuery(PrimaryKey primaryKey)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doQuery(super.tx, BatchedQuery.createDeleteQuery(primaryKey))).intValue();
    }

    public int doDeleteQuery(PrimaryKey primaryKey, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doQuery(super.tx, BatchedQuery.createDeleteQuery(primaryKey, where, bindVars))).intValue();
    }

    public int doDeleteAllQuery(RowType type)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doQuery(super.tx, BatchedQuery.createDeleteAllQuery(type))).intValue();
    }

    public int doDeleteAllQuery(RowType type, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doQuery(super.tx, BatchedQuery.createDeleteAllQuery(type, where, bindVars))).intValue();
    }

    public Row doFindByPrimaryKeyQuery(PrimaryKey primaryKey)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        return (Row)doQuery(super.tx, BatchedQuery.createFindByPrimaryKeyQuery(primaryKey));
    }

    public Row doFindByPrimaryKeyQuery(PrimaryKey primaryKey, String conditions)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        return (Row)doQuery(super.tx, BatchedQuery.createFindByPrimaryKeyQuery(primaryKey, conditions));
    }

    public Row doFindByPrimaryKeyQuery(PrimaryKey primaryKey, String where, Object bindVars[])
        throws DataFindException, DataQueryException, DataNetworkException
    {
        return (Row)doQuery(super.tx, BatchedQuery.createFindByPrimaryKeyQuery(primaryKey, where, bindVars));
    }

    public Row doFindByPrimaryKeyQuery(PrimaryKey primaryKey, String where, String conditions, Object bindVars[])
        throws DataFindException, DataQueryException, DataNetworkException
    {
        return (Row)doQuery(super.tx, BatchedQuery.createFindByPrimaryKeyQuery(primaryKey, where, conditions, bindVars));
    }

    public List doFindAllQuery(RowType type)
        throws DataQueryException, DataNetworkException
    {
        return (List)doQuery(super.tx, BatchedQuery.createFindAllQuery(type));
    }

    public List doFindAllQuery(RowType type, String conditions)
        throws DataQueryException, DataNetworkException
    {
        return (List)doQuery(super.tx, BatchedQuery.createFindAllQuery(type, conditions));
    }

    public List doFindAllQuery(RowType type, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return (List)doQuery(super.tx, BatchedQuery.createFindAllQuery(type, where, bindVars));
    }

    public List doFindAllQuery(RowType type, String where, String conditions, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return (List)doQuery(super.tx, BatchedQuery.createFindAllQuery(type, where, conditions, bindVars));
    }

    public List doFindAllQuery(RowType type, String where, String orderBy, String conditions, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return (List)doQuery(super.tx, BatchedQuery.createFindAllQuery(type, where, orderBy, conditions, bindVars));
    }

    public List doFindAllQuery(RowType type, String where, String orderBy, String conditions, Object bindVars[], RowType rowTypes[])
        throws DataQueryException, DataNetworkException
    {
        return (List)doQuery(super.tx, BatchedQuery.createFindAllQuery(type, where, orderBy, conditions, bindVars, rowTypes));
    }

    public ListPage doFindAllQuery(RowType type, String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber)
        throws DataQueryException, DataNetworkException
    {
        return (ListPage)doQuery(super.tx, BatchedQuery.createFindAllQuery(type, where, orderBy, conditions, bindVars, pageSize, pageNumber));
    }

    public ListPage doFindAllQuery(RowType type, String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber, 
            RowType rowTypes[])
        throws DataQueryException, DataNetworkException
    {
        return (ListPage)doQuery(super.tx, BatchedQuery.createFindAllQuery(type, where, orderBy, conditions, bindVars, pageSize, pageNumber, rowTypes));
    }

    public int doGetCountQuery(RowType type)
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doQuery(super.tx, BatchedQuery.createGetCountQuery(type))).intValue();
    }

    public int doGetCountQuery(RowType type, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        return ((Integer)doQuery(super.tx, BatchedQuery.createGetCountQuery(type, where, bindVars))).intValue();
    }

    public long doGetNewPkValueQuery(RowType type)
        throws DataQueryException, DataNetworkException
    {
        return ((Long)doQuery(super.tx, BatchedQuery.createGetNewPkValueQuery(type))).longValue();
    }
}

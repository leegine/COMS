// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QPDefaultImpl.java

package com.fitechlabs.xtrade.kernel.data.impl;

import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.kernel.data.*;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data.impl:
//            QPStdImpl

public class QPDefaultImpl extends QPStdImpl
{

    QueryProcessor getMutatorProcessor(String processorName)
    {
        QueryProcessorFactory qpf = Processors.getQueryProcessorFactory(processorName);
        if(qpf == null)
            throw new IllegalArgumentException("processor not found: " + processorName);
        else
            return qpf.getMutatorProcessor();
    }

    QueryProcessor getFinderProcessor(String processorName)
        throws DataNetworkException
    {
        QueryProcessorFactory qpf = Processors.getQueryProcessorFactory(processorName);
        if(qpf == null)
            throw new IllegalArgumentException("processor not found: " + processorName);
        else
            return qpf.getFinderProcessor();
    }

    public QPDefaultImpl(Database database)
    {
        super(database);
    }

    public int doDeleteAllQuery(RowType rowType, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        String processorName = rowType.getProcessorName();
        QueryProcessor qp = getMutatorProcessor(processorName);
        return qp.doDeleteAllQuery(rowType, where, bindVars);
    }

    public int doDeleteAllQuery(RowType rowType)
        throws DataQueryException, DataNetworkException
    {
        String processorName = rowType.getProcessorName();
        QueryProcessor qp = getMutatorProcessor(processorName);
        return qp.doDeleteAllQuery(rowType);
    }

    public int doDeleteAllQuery(String tableName, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        throw new UnsupportedOperationException("deleteAll with table name not supported for default processor.");
    }

    public int doDeleteQuery(PrimaryKey primaryKey, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        String processorName = primaryKey.getRowType().getProcessorName();
        QueryProcessor qp = getMutatorProcessor(processorName);
        return qp.doDeleteQuery(primaryKey, where, bindVars);
    }

    public int doDeleteQuery(PrimaryKey primaryKey)
        throws DataQueryException, DataNetworkException
    {
        String processorName = primaryKey.getRowType().getProcessorName();
        QueryProcessor qp = getMutatorProcessor(processorName);
        return qp.doDeleteQuery(primaryKey);
    }

    public int doDeleteQuery(String tableName, PrimaryKey primaryKey)
        throws DataQueryException, DataNetworkException
    {
        String processorName = primaryKey.getRowType().getProcessorName();
        QueryProcessor qp = getMutatorProcessor(processorName);
        return qp.doDeleteQuery(tableName, primaryKey);
    }

    public List doFindAllQuery(RowType rowType, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        String processorName = rowType.getProcessorName();
        QueryProcessor qp = getFinderProcessor(processorName);
        return qp.doFindAllQuery(rowType, where, bindVars);
    }

    public List doFindAllQuery(RowType rowType, String where, String conditions, Object bindVars[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        String processorName = rowType.getProcessorName();
        QueryProcessor qp = getFinderProcessor(processorName);
        return qp.doFindAllQuery(rowType, where, conditions, bindVars);
    }

    public ListPage doFindAllQuery(RowType rowType, String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber)
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        String processorName = rowType.getProcessorName();
        QueryProcessor qp = getFinderProcessor(processorName);
        return qp.doFindAllQuery(rowType, where, orderBy, conditions, bindVars, pageSize, pageNumber);
    }

    public ListPage doFindAllQuery(RowType rowType, String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber, 
            RowType rowTypes[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        String processorName = rowType.getProcessorName();
        QueryProcessor qp = getFinderProcessor(processorName);
        return qp.doFindAllQuery(rowType, where, orderBy, conditions, bindVars, pageSize, pageNumber, rowTypes);
    }

    public List doFindAllQuery(RowType rowType, String where, String orderBy, String conditions, Object bindVars[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        String processorName = rowType.getProcessorName();
        QueryProcessor qp = getFinderProcessor(processorName);
        return qp.doFindAllQuery(rowType, where, orderBy, conditions, bindVars);
    }

    public List doFindAllQuery(RowType rowType, String where, String orderBy, String conditions, Object bindVars[], RowType rowTypes[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        String processorName = rowType.getProcessorName();
        QueryProcessor qp = getFinderProcessor(processorName);
        return qp.doFindAllQuery(rowType, where, orderBy, conditions, bindVars, rowTypes);
    }

    public List doFindAllQuery(RowType rowType, String conditions)
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        String processorName = rowType.getProcessorName();
        QueryProcessor qp = getFinderProcessor(processorName);
        return qp.doFindAllQuery(rowType, conditions);
    }

    public List doFindAllQuery(RowType rowType)
        throws DataQueryException, DataNetworkException
    {
        String processorName = rowType.getProcessorName();
        QueryProcessor qp = getFinderProcessor(processorName);
        return qp.doFindAllQuery(rowType);
    }

    public List doFindAllQuery(String tableName, String where, String conditions, Object bindVars[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        throw new UnsupportedOperationException("findAll with table name not supported for default processor.");
    }

    public ListPage doFindAllQuery(String tableName, String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber)
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        throw new UnsupportedOperationException("findAll with table name not supported for default processor.");
    }

    public List doFindAllQuery(String tableName, String where, String orderBy, String conditions, Object bindVars[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        throw new UnsupportedOperationException("findAll with table name not supported for default processor.");
    }

    public Row doFindByPrimaryKeyQuery(PrimaryKey primaryKey, String where, Object bindVars[])
        throws DataFindException, DataQueryException, DataNetworkException
    {
        String processorName = primaryKey.getRowType().getProcessorName();
        QueryProcessor qp = getFinderProcessor(processorName);
        return qp.doFindByPrimaryKeyQuery(primaryKey, where, bindVars);
    }

    public Row doFindByPrimaryKeyQuery(PrimaryKey primaryKey, String where, String conditions, Object bindVars[])
        throws DataFindException, DataQueryException, DataNetworkException, IllegalStateException
    {
        String processorName = primaryKey.getRowType().getProcessorName();
        QueryProcessor qp = getFinderProcessor(processorName);
        return qp.doFindByPrimaryKeyQuery(primaryKey, where, conditions, bindVars);
    }

    public Row doFindByPrimaryKeyQuery(PrimaryKey primaryKey, String conditions)
        throws DataFindException, DataQueryException, DataNetworkException, IllegalStateException
    {
        String processorName = primaryKey.getRowType().getProcessorName();
        QueryProcessor qp = getFinderProcessor(processorName);
        return qp.doFindByPrimaryKeyQuery(primaryKey, conditions);
    }

    public Row doFindByPrimaryKeyQuery(PrimaryKey primaryKey)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        String processorName = primaryKey.getRowType().getProcessorName();
        QueryProcessor qp = getFinderProcessor(processorName);
        return qp.doFindByPrimaryKeyQuery(primaryKey);
    }

    public Row doFindByPrimaryKeyQuery(String tableName, PrimaryKey primaryKey, String where, String conditions, Object bindVars[])
        throws DataFindException, DataQueryException, DataNetworkException, IllegalStateException
    {
        String processorName = primaryKey.getRowType().getProcessorName();
        QueryProcessor qp = getFinderProcessor(processorName);
        return qp.doFindByPrimaryKeyQuery(tableName, primaryKey, where, conditions, bindVars);
    }

    public Row doFindByPrimaryKeyQuery(String tableName, PrimaryKey primaryKey, String conditions)
        throws DataFindException, DataQueryException, DataNetworkException, IllegalStateException
    {
        String processorName = primaryKey.getRowType().getProcessorName();
        QueryProcessor qp = getFinderProcessor(processorName);
        return qp.doFindByPrimaryKeyQuery(tableName, primaryKey, conditions);
    }

    public int doGetCountQuery(RowType rowType, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        String processorName = rowType.getProcessorName();
        QueryProcessor qp = getFinderProcessor(processorName);
        return qp.doGetCountQuery(rowType, where, bindVars);
    }

    public int doGetCountQuery(RowType rowType)
        throws DataQueryException, DataNetworkException
    {
        String processorName = rowType.getProcessorName();
        QueryProcessor qp = getFinderProcessor(processorName);
        return qp.doGetCountQuery(rowType);
    }

    public int doGetCountQuery(String tableName, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        throw new UnsupportedOperationException("getCount with table name not supported for default processor.");
    }

    public long doGetNewPkValueQuery(RowType rowType)
        throws DataQueryException, DataNetworkException
    {
        String processorName = rowType.getProcessorName();
        QueryProcessor qp = getFinderProcessor(processorName);
        return qp.doGetNewPkValueQuery(rowType);
    }

    public long doGetNewPkValueQuery(String tableName)
        throws DataQueryException, DataNetworkException
    {
        throw new UnsupportedOperationException("getNewPkValue with table name not supported for default processor.");
    }

    public Object doInsertQuery(Row initialValues)
        throws DataQueryException, DataNetworkException
    {
        String processorName = initialValues.getRowType().getProcessorName();
        QueryProcessor qp = getMutatorProcessor(processorName);
        return qp.doInsertQuery(initialValues);
    }

    public Object doInsertQuery(String tableName, Map initialValues)
        throws DataQueryException, DataNetworkException
    {
        throw new UnsupportedOperationException("insert with table name and map not supported for default processor.");
    }

    public Object doInsertQuery(String tableName, Row initialValues)
        throws DataQueryException, DataNetworkException
    {
        String processorName = initialValues.getRowType().getProcessorName();
        QueryProcessor qp = getMutatorProcessor(processorName);
        return qp.doInsertQuery(tableName, initialValues);
    }

    public int doUpdateAllQuery(RowType rowType, Map changes)
        throws DataQueryException, DataNetworkException
    {
        String processorName = rowType.getProcessorName();
        QueryProcessor qp = getMutatorProcessor(processorName);
        return qp.doUpdateAllQuery(rowType, changes);
    }

    public int doUpdateAllQuery(RowType rowType, String where, Object bindVars[], Map changes)
        throws DataQueryException, DataNetworkException
    {
        String processorName = rowType.getProcessorName();
        QueryProcessor qp = getMutatorProcessor(processorName);
        return qp.doUpdateAllQuery(rowType, where, bindVars, changes);
    }

    public int doUpdateAllQuery(String tableName, String where, Object bindVars[], Map changes)
        throws DataQueryException, DataNetworkException
    {
        throw new UnsupportedOperationException("updateAll with table name not supported for default processor.");
    }

    public int doUpdateQuery(PrimaryKey primaryKey, Map changes)
        throws DataQueryException, DataNetworkException
    {
        String processorName = primaryKey.getRowType().getProcessorName();
        QueryProcessor qp = getMutatorProcessor(processorName);
        return qp.doUpdateQuery(primaryKey, changes);
    }

    public int doUpdateQuery(PrimaryKey primaryKey, String where, Object bindVars[], Map changes)
        throws DataQueryException, DataNetworkException
    {
        String processorName = primaryKey.getRowType().getProcessorName();
        QueryProcessor qp = getMutatorProcessor(processorName);
        return qp.doUpdateQuery(primaryKey, where, bindVars, changes);
    }

    public int doUpdateQuery(Row newValues, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        String processorName = newValues.getRowType().getProcessorName();
        QueryProcessor qp = getMutatorProcessor(processorName);
        return qp.doUpdateQuery(newValues, where, bindVars);
    }

    public int doUpdateQuery(Row newValues)
        throws DataQueryException, DataNetworkException
    {
        String processorName = newValues.getRowType().getProcessorName();
        QueryProcessor qp = getMutatorProcessor(processorName);
        return qp.doUpdateQuery(newValues);
    }

    public int doUpdateQuery(String tableName, PrimaryKey primaryKey, Map changes)
        throws DataQueryException, DataNetworkException
    {
        String processorName = primaryKey.getRowType().getProcessorName();
        QueryProcessor qp = getMutatorProcessor(processorName);
        return qp.doUpdateQuery(tableName, primaryKey, changes);
    }

    public int doUpdateQuery(String tableName, Row newValues)
        throws DataQueryException, DataNetworkException
    {
        String processorName = newValues.getRowType().getProcessorName();
        QueryProcessor qp = getMutatorProcessor(processorName);
        return qp.doUpdateQuery(tableName, newValues);
    }

    public boolean doLockPartititionQuery(boolean waitOnBusy)
        throws DataQueryException, DataNetworkException
    {
        throw new UnsupportedOperationException("Method doLockPartitionQuery is not supported on QPDefaultImpl");
    }

    public void forceReadonlyMode(RowType rowType, boolean readonly)
        throws UnsupportedOperationException, DataNetworkException
    {
        String processorName = rowType.getProcessorName();
        QueryProcessor qp = getFinderProcessor(processorName);
        qp.forceReadonlyMode(rowType, readonly);
    }
}

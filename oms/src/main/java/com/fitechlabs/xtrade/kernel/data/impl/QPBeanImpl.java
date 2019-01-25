// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QPBeanImpl.java

package com.fitechlabs.xtrade.kernel.data.impl;

import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.kernel.data.*;
import java.util.List;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data.impl:
//            QPStdImpl

public abstract class QPBeanImpl extends QPStdImpl
{

    public QPBeanImpl(Database mutatorDatabase)
    {
        super(mutatorDatabase);
    }

    protected abstract QueryProcessor reserveFinder()
        throws DataNetworkException;

    protected abstract void releaseFinder(QueryProcessor queryprocessor);

    public abstract Long getId();

    private boolean startsWithSelect(BatchedQuery queries[])
    {
        return queries != null && queries.length != 0 && isSelect(queries[0]);
    }

    private boolean isSelect(BatchedQuery query)
    {
        if(query == null)
            return false;
        switch(query.getQueryType())
        {
        case 40: // '('
        case 41: // ')'
        case 50: // '2'
        case 51: // '3'
        case 100: // 'd'
            return true;
        }
        return false;
    }

    public List doFindAllQuery(RowType rowType, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        QueryProcessor finder = reserveFinder();
        List list = finder.doFindAllQuery(rowType, where, bindVars);
        releaseFinder(finder);
        return list;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }

    public List doFindAllQuery(RowType rowType, String where, String conditions, Object bindVars[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        QueryProcessor finder = reserveFinder();
        List list = finder.doFindAllQuery(rowType, where, conditions, bindVars);
        releaseFinder(finder);
        return list;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }

    public ListPage doFindAllQuery(RowType rowType, String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber)
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        QueryProcessor finder = reserveFinder();
        ListPage listpage = finder.doFindAllQuery(rowType, where, orderBy, conditions, bindVars, pageSize, pageNumber);
        releaseFinder(finder);
        return listpage;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }

    public ListPage doFindAllQuery(RowType rowType, String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber, 
            RowType rowTypes[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        QueryProcessor finder = reserveFinder();
        ListPage listpage = finder.doFindAllQuery(rowType, where, orderBy, conditions, bindVars, pageSize, pageNumber, rowTypes);
        releaseFinder(finder);
        return listpage;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }

    public List doFindAllQuery(RowType rowType, String where, String orderBy, String conditions, Object bindVars[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        QueryProcessor finder = reserveFinder();
        List list = finder.doFindAllQuery(rowType, where, orderBy, conditions, bindVars);
        releaseFinder(finder);
        return list;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }

    public List doFindAllQuery(RowType rowType, String where, String orderBy, String conditions, Object bindVars[], RowType rowTypes[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        QueryProcessor finder = reserveFinder();
        List list = finder.doFindAllQuery(rowType, where, orderBy, conditions, bindVars, rowTypes);
        releaseFinder(finder);
        return list;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }

    public List doFindAllQuery(RowType rowType, String conditions)
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        QueryProcessor finder = reserveFinder();
        List list = finder.doFindAllQuery(rowType, conditions);
        releaseFinder(finder);
        return list;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }

    public List doFindAllQuery(RowType rowType)
        throws DataQueryException, DataNetworkException
    {
        QueryProcessor finder = reserveFinder();
        List list = finder.doFindAllQuery(rowType);
        releaseFinder(finder);
        return list;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }

    public List doFindAllQuery(String tableName, String where, String conditions, Object bindVars[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        QueryProcessor finder = reserveFinder();
        List list = finder.doFindAllQuery(tableName, where, conditions, bindVars);
        releaseFinder(finder);
        return list;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }

    public ListPage doFindAllQuery(String tableName, String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber)
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        QueryProcessor finder = reserveFinder();
        ListPage listpage = finder.doFindAllQuery(tableName, where, orderBy, conditions, bindVars, pageSize, pageNumber);
        releaseFinder(finder);
        return listpage;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }

    public List doFindAllQuery(String tableName, String where, String orderBy, String conditions, Object bindVars[])
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        QueryProcessor finder = reserveFinder();
        List list = finder.doFindAllQuery(tableName, where, orderBy, conditions, bindVars);
        releaseFinder(finder);
        return list;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }

    public Row doFindByPrimaryKeyQuery(PrimaryKey primaryKey, String where, Object bindVars[])
        throws DataFindException, DataQueryException, DataNetworkException
    {
        QueryProcessor finder = reserveFinder();
        Row row = finder.doFindByPrimaryKeyQuery(primaryKey, where, bindVars);
        releaseFinder(finder);
        return row;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }

    public Row doFindByPrimaryKeyQuery(PrimaryKey primaryKey, String where, String conditions, Object bindVars[])
        throws DataFindException, DataQueryException, DataNetworkException, IllegalStateException
    {
        QueryProcessor finder = reserveFinder();
        Row row = finder.doFindByPrimaryKeyQuery(primaryKey, where, conditions, bindVars);
        releaseFinder(finder);
        return row;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }

    public Row doFindByPrimaryKeyQuery(PrimaryKey primaryKey, String conditions)
        throws DataFindException, DataQueryException, DataNetworkException, IllegalStateException
    {
        QueryProcessor finder = reserveFinder();
        Row row = finder.doFindByPrimaryKeyQuery(primaryKey, conditions);
        releaseFinder(finder);
        return row;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }

    public Row doFindByPrimaryKeyQuery(PrimaryKey primaryKey)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        QueryProcessor finder = reserveFinder();
        Row row = finder.doFindByPrimaryKeyQuery(primaryKey);
        releaseFinder(finder);
        return row;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }

    public Row doFindByPrimaryKeyQuery(String tableName, PrimaryKey primaryKey, String where, String conditions, Object bindVars[])
        throws DataFindException, DataQueryException, DataNetworkException, IllegalStateException
    {
        QueryProcessor finder = reserveFinder();
        Row row = finder.doFindByPrimaryKeyQuery(tableName, primaryKey, where, conditions, bindVars);
        releaseFinder(finder);
        return row;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }

    public Row doFindByPrimaryKeyQuery(String tableName, PrimaryKey primaryKey, String conditions)
        throws DataFindException, DataQueryException, DataNetworkException, IllegalStateException
    {
        QueryProcessor finder = reserveFinder();
        Row row = finder.doFindByPrimaryKeyQuery(tableName, primaryKey, conditions);
        releaseFinder(finder);
        return row;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }

    public int doGetCountQuery(RowType rowType, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        QueryProcessor finder = reserveFinder();
        int i = finder.doGetCountQuery(rowType, where, bindVars);
        releaseFinder(finder);
        return i;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }

    public int doGetCountQuery(RowType rowType)
        throws DataQueryException, DataNetworkException
    {
        QueryProcessor finder = reserveFinder();
        int i = finder.doGetCountQuery(rowType);
        releaseFinder(finder);
        return i;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }

    public int doGetCountQuery(String tableName, String where, Object bindVars[])
        throws DataQueryException, DataNetworkException
    {
        QueryProcessor finder = reserveFinder();
        int i = finder.doGetCountQuery(tableName, where, bindVars);
        releaseFinder(finder);
        return i;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }

    public boolean doLockPartititionQuery(boolean waitOnBusy)
        throws DataQueryException, DataNetworkException
    {
        QueryProcessor finder = reserveFinder();
        boolean flag = finder.doLockPartititionQuery(waitOnBusy);
        releaseFinder(finder);
        return flag;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }

    public Object[] doQueries(BatchedQuery queries[])
        throws DataNetworkException, DataBatchException, DataRollbackException
    {
        QueryProcessor finder;
        if(!startsWithSelect(queries))
            return super.doQueries(queries);
        finder = reserveFinder();
        Object aobj[] = finder.doQueries(queries);
        releaseFinder(finder);
        return aobj;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }

    public Object[] doQueries(int transactionSemantics, BatchedQuery queries[])
        throws DataNetworkException, DataBatchException, DataRollbackException
    {
        QueryProcessor finder;
        if(!startsWithSelect(queries))
            return super.doQueries(transactionSemantics, queries);
        finder = reserveFinder();
        Object aobj[] = finder.doQueries(transactionSemantics, queries);
        releaseFinder(finder);
        return aobj;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }

    public Object doQuery(BatchedQuery query)
        throws DataNetworkException, DataQueryException, DataRollbackException
    {
        QueryProcessor finder;
        if(!isSelect(query))
            return super.doQuery(query);
        finder = reserveFinder();
        Object obj = finder.doQuery(query);
        releaseFinder(finder);
        return obj;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }

    public Object doQuery(int transactionSemantics, BatchedQuery query)
        throws DataNetworkException, DataQueryException, DataRollbackException
    {
        QueryProcessor finder;
        if(!isSelect(query))
            return super.doQuery(transactionSemantics, query);
        finder = reserveFinder();
        Object obj = finder.doQuery(transactionSemantics, query);
        releaseFinder(finder);
        return obj;
        Exception exception;
        exception;
        releaseFinder(finder);
        throw exception;
    }
}

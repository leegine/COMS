// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QueryProcessor.java

package com.fitechlabs.xtrade.kernel.data;

import com.fitechlabs.dbind.*;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data:
//            DataNetworkException, DataBatchException, DataRollbackException, DataQueryException, 
//            DataCallbackException, DataFindException, BatchedQuery, TransactionCallback

public interface QueryProcessor
{

    public abstract Object[] doQueries(BatchedQuery abatchedquery[])
        throws DataNetworkException, DataBatchException, DataRollbackException;

    public abstract Object[] doQueries(int i, BatchedQuery abatchedquery[])
        throws DataNetworkException, DataBatchException, DataRollbackException;

    public abstract Object doQuery(BatchedQuery batchedquery)
        throws DataNetworkException, DataQueryException, DataRollbackException;

    public abstract Object doQuery(int i, BatchedQuery batchedquery)
        throws DataNetworkException, DataQueryException, DataRollbackException;

    public abstract Object doTransaction(TransactionCallback transactioncallback)
        throws DataNetworkException, DataQueryException, DataCallbackException, DataRollbackException;

    public abstract Object doTransaction(int i, TransactionCallback transactioncallback)
        throws DataNetworkException, DataQueryException, DataCallbackException, DataRollbackException;

    /**
     * @deprecated Method doInsertQuery is deprecated
     */

    public abstract Object doInsertQuery(String s, Row row)
        throws DataQueryException, DataNetworkException;

    /**
     * @deprecated Method doInsertQuery is deprecated
     */

    public abstract Object doInsertQuery(String s, Map map)
        throws DataQueryException, DataNetworkException;

    /**
     * @deprecated Method doUpdateQuery is deprecated
     */

    public abstract int doUpdateQuery(String s, Row row)
        throws DataQueryException, DataNetworkException;

    /**
     * @deprecated Method doUpdateQuery is deprecated
     */

    public abstract int doUpdateQuery(String s, PrimaryKey primarykey, Map map)
        throws DataQueryException, DataNetworkException;

    /**
     * @deprecated Method doUpdateAllQuery is deprecated
     */

    public abstract int doUpdateAllQuery(String s, String s1, Object aobj[], Map map)
        throws DataQueryException, DataNetworkException;

    /**
     * @deprecated Method doDeleteQuery is deprecated
     */

    public abstract int doDeleteQuery(String s, PrimaryKey primarykey)
        throws DataQueryException, DataNetworkException;

    /**
     * @deprecated Method doDeleteAllQuery is deprecated
     */

    public abstract int doDeleteAllQuery(String s, String s1, Object aobj[])
        throws DataQueryException, DataNetworkException;

    /**
     * @deprecated Method doFindByPrimaryKeyQuery is deprecated
     */

    public abstract Row doFindByPrimaryKeyQuery(String s, PrimaryKey primarykey, String s1)
        throws DataFindException, DataQueryException, DataNetworkException, IllegalStateException;

    /**
     * @deprecated Method doFindByPrimaryKeyQuery is deprecated
     */

    public abstract Row doFindByPrimaryKeyQuery(String s, PrimaryKey primarykey, String s1, String s2, Object aobj[])
        throws DataFindException, DataQueryException, DataNetworkException, IllegalStateException;

    /**
     * @deprecated Method doFindAllQuery is deprecated
     */

    public abstract List doFindAllQuery(String s, String s1, String s2, Object aobj[])
        throws DataQueryException, DataNetworkException, IllegalStateException;

    /**
     * @deprecated Method doFindAllQuery is deprecated
     */

    public abstract List doFindAllQuery(String s, String s1, String s2, String s3, Object aobj[])
        throws DataQueryException, DataNetworkException, IllegalStateException;

    /**
     * @deprecated Method doFindAllQuery is deprecated
     */

    public abstract ListPage doFindAllQuery(String s, String s1, String s2, String s3, Object aobj[], int i, int j)
        throws DataQueryException, DataNetworkException, IllegalStateException;

    public abstract Object[] doProcedureCallQuery(String s, Object aobj[], Object aobj1[])
        throws DataQueryException, DataNetworkException;

    public abstract Object[] doReadOnlyProcedureCallQuery(String s, Object aobj[], Object aobj1[])
        throws DataQueryException, DataNetworkException;

    public abstract boolean lockAccount(long l, boolean flag)
        throws DataQueryException, DataNetworkException, IllegalStateException, IllegalArgumentException;

    /**
     * @deprecated Method doLockPartititionQuery is deprecated
     */

    public abstract boolean doLockPartititionQuery(boolean flag)
        throws DataQueryException, DataNetworkException;

    /**
     * @deprecated Method doGetCountQuery is deprecated
     */

    public abstract int doGetCountQuery(String s, String s1, Object aobj[])
        throws DataQueryException, DataNetworkException;

    /**
     * @deprecated Method doGetNewPkValueQuery is deprecated
     */

    public abstract long doGetNewPkValueQuery(String s)
        throws DataQueryException, DataNetworkException;

    public abstract Object doInsertQuery(Row row)
        throws DataQueryException, DataNetworkException;

    public abstract int doUpdateQuery(Row row)
        throws DataQueryException, DataNetworkException;

    public abstract int doUpdateQuery(Row row, String s, Object aobj[])
        throws DataQueryException, DataNetworkException;

    public abstract int doUpdateQuery(PrimaryKey primarykey, Map map)
        throws DataQueryException, DataNetworkException;

    public abstract int doUpdateQuery(PrimaryKey primarykey, String s, Object aobj[], Map map)
        throws DataQueryException, DataNetworkException;

    public abstract int doUpdateAllQuery(RowType rowtype, Map map)
        throws DataQueryException, DataNetworkException;

    public abstract int doUpdateAllQuery(RowType rowtype, String s, Object aobj[], Map map)
        throws DataQueryException, DataNetworkException;

    public abstract int doDeleteQuery(PrimaryKey primarykey)
        throws DataQueryException, DataNetworkException;

    public abstract int doDeleteQuery(PrimaryKey primarykey, String s, Object aobj[])
        throws DataQueryException, DataNetworkException;

    public abstract int doDeleteAllQuery(RowType rowtype)
        throws DataQueryException, DataNetworkException;

    public abstract int doDeleteAllQuery(RowType rowtype, String s, Object aobj[])
        throws DataQueryException, DataNetworkException;

    public abstract Row doFindByPrimaryKeyQuery(PrimaryKey primarykey)
        throws DataFindException, DataQueryException, DataNetworkException;

    public abstract Row doFindByPrimaryKeyQuery(PrimaryKey primarykey, String s)
        throws DataFindException, DataQueryException, DataNetworkException, IllegalStateException;

    public abstract Row doFindByPrimaryKeyQuery(PrimaryKey primarykey, String s, Object aobj[])
        throws DataFindException, DataQueryException, DataNetworkException;

    public abstract Row doFindByPrimaryKeyQuery(PrimaryKey primarykey, String s, String s1, Object aobj[])
        throws DataFindException, DataQueryException, DataNetworkException, IllegalStateException;

    public abstract List doFindAllQuery(RowType rowtype)
        throws DataQueryException, DataNetworkException;

    public abstract List doFindAllQuery(RowType rowtype, String s)
        throws DataQueryException, DataNetworkException, IllegalStateException;

    public abstract List doFindAllQuery(RowType rowtype, String s, Object aobj[])
        throws DataQueryException, DataNetworkException;

    public abstract List doFindAllQuery(RowType rowtype, String s, String s1, Object aobj[])
        throws DataQueryException, DataNetworkException, IllegalStateException;

    public abstract List doFindAllQuery(RowType rowtype, String s, String s1, String s2, Object aobj[])
        throws DataQueryException, DataNetworkException, IllegalStateException;

    public abstract List doFindAllQuery(RowType rowtype, String s, String s1, String s2, Object aobj[], RowType arowtype[])
        throws DataQueryException, DataNetworkException, IllegalStateException;

    public abstract ListPage doFindAllQuery(RowType rowtype, String s, String s1, String s2, Object aobj[], int i, int j)
        throws DataQueryException, DataNetworkException, IllegalStateException;

    public abstract ListPage doFindAllQuery(RowType rowtype, String s, String s1, String s2, Object aobj[], int i, int j, 
            RowType arowtype[])
        throws DataQueryException, DataNetworkException, IllegalStateException;

    public abstract int doGetCountQuery(RowType rowtype)
        throws DataQueryException, DataNetworkException;

    public abstract int doGetCountQuery(RowType rowtype, String s, Object aobj[])
        throws DataQueryException, DataNetworkException;

    public abstract long doGetNewPkValueQuery(RowType rowtype)
        throws DataQueryException, DataNetworkException;

    public abstract void forceReadonlyMode(RowType rowtype, boolean flag)
        throws DataNetworkException;

    public static final int TX_JOIN_EXISTING = 1;
    public static final int TX_CREATE_NEW = 2;
}

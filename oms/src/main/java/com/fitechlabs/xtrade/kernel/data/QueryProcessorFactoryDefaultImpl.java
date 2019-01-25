// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QueryProcessorFactoryDefaultImpl.java

package com.fitechlabs.xtrade.kernel.data;

import com.fitechlabs.dbind.*;
import java.sql.SQLException;
import java.util.*;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data:
//            QueryProcessorFactoryStdImpl, QueryProcessor, QueryProcessorStdImpl, TransactionCallback, 
//            DataQueryException, DataNetworkException, DataFindException, BatchedQuery, 
//            Processors, QueryProcessorFactory

public class QueryProcessorFactoryDefaultImpl extends QueryProcessorFactoryStdImpl
{
    public static class QueryProcessorDefaultImpl extends QueryProcessorStdImpl
    {

        public boolean doLockPartititionQuery(boolean waitOnBusy)
            throws DataNetworkException, DataQueryException
        {
            throw new UnsupportedOperationException("doLockPartitionQuery not supported on DefaultQueryProcessor");
        }

        public Object doQueryImpl(BatchedQuery query)
            throws DataNetworkException, DataQueryException
        {
            int qtype;
            String name;
            RowType type;
            Object qa[];
            qtype = query.getQueryType();
            name = query.getTableName();
            type = query.getRowType();
            qa = query.getQueryArgs();
            qtype;
            JVM INSTR lookupswitch 19: default 712
        //                       10: 184
        //                       11: 196
        //                       20: 209
        //                       21: 228
        //                       22: 254
        //                       23: 289
        //                       24: 322
        //                       30: 362
        //                       31: 381
        //                       32: 409
        //                       40: 442
        //                       41: 461
        //                       50: 494
        //                       51: 536
        //                       60: 599
        //                       70: 633
        //                       80: 655
        //                       90: 670
        //                       100: 684;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20
_L2:
            return doInsertQuery((Row)qa[0]);
_L3:
            return doInsertQuery(name, (Map)qa[0]);
_L4:
            return new Integer(doUpdateQuery((Row)qa[0]));
_L5:
            return new Integer(doUpdateQuery((PrimaryKey)qa[0], (Map)qa[1]));
_L6:
            return new Integer(doUpdateAllQuery(type, (String)qa[0], (Object[])qa[1], (Map)qa[2]));
_L7:
            return new Integer(doUpdateQuery((Row)qa[0], (String)qa[1], (Object[])qa[2]));
_L8:
            return new Integer(doUpdateQuery((PrimaryKey)qa[0], (String)qa[1], (Object[])qa[2], (Map)qa[3]));
_L9:
            return new Integer(doDeleteQuery((PrimaryKey)qa[0]));
_L10:
            return new Integer(doDeleteAllQuery(type, (String)qa[0], (Object[])qa[1]));
_L11:
            return new Integer(doDeleteQuery((PrimaryKey)qa[0], (String)qa[1], (Object[])qa[2]));
_L12:
            return doFindByPrimaryKeyQuery((PrimaryKey)qa[0], (String)qa[1]);
_L13:
            return doFindByPrimaryKeyQuery((PrimaryKey)qa[0], (String)qa[1], (String)qa[2], (Object[])qa[3]);
_L14:
            return doFindAllQuery(type, (String)qa[0], (String)qa[1], (String)qa[2], (Object[])qa[3], (RowType[])qa[4]);
_L15:
            return doFindAllQuery(type, (String)qa[0], (String)qa[1], (String)qa[2], (Object[])qa[3], ((Integer)qa[4]).intValue(), ((Integer)qa[5]).intValue(), (RowType[])qa[6]);
_L16:
            boolean isReadOnlySP = ((Boolean)qa[2]).booleanValue();
            return ((Object) (super.doProcedureCallQueryImpl(name, (Object[])qa[0], (Object[])qa[1], isReadOnlySP)));
_L17:
            return super.doTransactionImpl(((Integer)qa[0]).intValue(), (TransactionCallback)qa[1]);
_L18:
            return super.doLockPartititionQueryImpl(((Boolean)qa[0]).booleanValue());
_L19:
            return new Long(doGetNewPkValueQuery(type));
_L20:
            return new Integer(doGetCountQuery(type, (String)qa[0], (Object[])qa[1]));
_L1:
            try
            {
                throw new IllegalArgumentException("bad batched query type: " + qtype);
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

        protected QueryProcessor getMutatorProcessor(RowType rowType)
            throws DataNetworkException, DataFindException
        {
            String proc = rowType.getProcessorName();
            if(proc == null)
                throw new IllegalStateException("Type has no processor associated with it");
            QueryProcessorFactory qpf = Processors.getQueryProcessorFactory(proc);
            if(qpf == null)
                throw new IllegalArgumentException("Bad processor name '" + proc + "'");
            else
                return qpf.getMutatorProcessor();
        }

        public Object doInsertQuery(Row initialValues)
            throws DataNetworkException, DataQueryException
        {
            RowType type = initialValues.getRowType();
            QueryProcessor qp = getMutatorProcessor(type);
            return qp.doInsertQuery(initialValues);
        }

        public int doUpdateQuery(Row newValues)
            throws DataNetworkException, DataQueryException
        {
            RowType type = newValues.getRowType();
            QueryProcessor qp = getMutatorProcessor(type);
            return qp.doUpdateQuery(newValues);
        }

        public int doUpdateQuery(Row newValues, String where, Object conditions[])
            throws DataNetworkException, DataQueryException
        {
            RowType type = newValues.getRowType();
            QueryProcessor qp = getMutatorProcessor(type);
            return qp.doUpdateQuery(newValues, where, conditions);
        }

        public int doUpdateQuery(PrimaryKey primaryKey, Map changes)
            throws DataNetworkException, DataQueryException
        {
            RowType type = primaryKey.getRowType();
            QueryProcessor qp = getMutatorProcessor(type);
            return qp.doUpdateQuery(primaryKey, changes);
        }

        public int doUpdateQuery(PrimaryKey primaryKey, String where, Object conditions[], Map changes)
            throws DataNetworkException, DataQueryException
        {
            RowType type = primaryKey.getRowType();
            QueryProcessor qp = getMutatorProcessor(type);
            return qp.doUpdateQuery(primaryKey, where, conditions, changes);
        }

        public int doUpdateAllQuery(RowType rowType, Map changes)
            throws DataNetworkException, DataQueryException
        {
            QueryProcessor qp = getMutatorProcessor(rowType);
            return qp.doUpdateAllQuery(rowType, changes);
        }

        public int doUpdateAllQuery(RowType rowType, String where, Object bindVars[], Map changes)
            throws DataNetworkException, DataQueryException
        {
            QueryProcessor qp = getMutatorProcessor(rowType);
            return qp.doUpdateAllQuery(rowType, where, bindVars, changes);
        }

        public int doDeleteQuery(PrimaryKey primaryKey)
            throws DataNetworkException, DataQueryException
        {
            RowType type = primaryKey.getRowType();
            QueryProcessor qp = getMutatorProcessor(type);
            return qp.doDeleteQuery(primaryKey);
        }

        public int doDeleteQuery(PrimaryKey primaryKey, String where, Object bindVars[])
            throws DataNetworkException, DataQueryException
        {
            RowType type = primaryKey.getRowType();
            QueryProcessor qp = getMutatorProcessor(type);
            return qp.doDeleteQuery(primaryKey, where, bindVars);
        }

        public int doDeleteAllQuery(RowType rowType)
            throws DataNetworkException, DataQueryException
        {
            QueryProcessor qp = getMutatorProcessor(rowType);
            return qp.doDeleteAllQuery(rowType);
        }

        public int doDeleteAllQuery(RowType rowType, String where, Object bindVars[])
            throws DataNetworkException, DataQueryException
        {
            QueryProcessor qp = getMutatorProcessor(rowType);
            return qp.doDeleteAllQuery(rowType, where, bindVars);
        }

        public long doGetNewPkValueQuery(RowType rowType)
            throws DataNetworkException, DataQueryException
        {
            QueryProcessor qp = getMutatorProcessor(rowType);
            return qp.doGetNewPkValueQuery(rowType);
        }

        protected QueryProcessor getFinderProcessor(RowType rowType)
            throws DataNetworkException
        {
            String proc = rowType.getProcessorName();
            if(proc == null)
                throw new IllegalStateException("Type has no processor associated with it");
            QueryProcessorFactory qpf = Processors.getQueryProcessorFactory(proc);
            if(qpf == null)
                throw new IllegalArgumentException("Bad processor name '" + proc + "'");
            else
                return qpf.getFinderProcessor();
        }

        public Row doFindByPrimaryKeyQuery(PrimaryKey primaryKey)
            throws DataNetworkException, DataQueryException, DataFindException
        {
            RowType rowType = primaryKey.getRowType();
            QueryProcessor qp = getFinderProcessor(rowType);
            return qp.doFindByPrimaryKeyQuery(primaryKey);
        }

        public Row doFindByPrimaryKeyQuery(PrimaryKey primaryKey, String conditions)
            throws DataNetworkException, DataQueryException, DataFindException
        {
            RowType rowType = primaryKey.getRowType();
            QueryProcessor qp = getFinderProcessor(rowType);
            return qp.doFindByPrimaryKeyQuery(primaryKey, conditions);
        }

        public Row doFindByPrimaryKeyQuery(PrimaryKey primaryKey, String where, Object bindVars[])
            throws DataNetworkException, DataQueryException, DataFindException
        {
            RowType rowType = primaryKey.getRowType();
            QueryProcessor qp = getFinderProcessor(rowType);
            return qp.doFindByPrimaryKeyQuery(primaryKey, where, bindVars);
        }

        public Row doFindByPrimaryKeyQuery(PrimaryKey primaryKey, String where, String conditions, Object bindVars[])
            throws DataNetworkException, DataQueryException, DataFindException
        {
            RowType rowType = primaryKey.getRowType();
            QueryProcessor qp = getFinderProcessor(rowType);
            return qp.doFindByPrimaryKeyQuery(primaryKey, where, conditions, bindVars);
        }

        public List doFindAllQuery(RowType rowType)
            throws DataNetworkException, DataQueryException
        {
            QueryProcessor qp = getFinderProcessor(rowType);
            return qp.doFindAllQuery(rowType);
        }

        public List doFindAllQuery(RowType rowType, String conditions)
            throws DataNetworkException, DataQueryException
        {
            QueryProcessor qp = getFinderProcessor(rowType);
            return qp.doFindAllQuery(rowType, conditions);
        }

        public List doFindAllQuery(RowType rowType, String where, Object bindVars[])
            throws DataNetworkException, DataQueryException
        {
            QueryProcessor qp = getFinderProcessor(rowType);
            return qp.doFindAllQuery(rowType, where, bindVars);
        }

        public List doFindAllQuery(RowType rowType, String where, String conditions, Object bindVars[])
            throws DataNetworkException, DataQueryException
        {
            QueryProcessor qp = getFinderProcessor(rowType);
            return qp.doFindAllQuery(rowType, where, conditions, bindVars);
        }

        public List doFindAllQuery(RowType rowType, String where, String orderBy, String conditions, Object bindVars[])
            throws DataNetworkException, DataQueryException
        {
            QueryProcessor qp = getFinderProcessor(rowType);
            return qp.doFindAllQuery(rowType, where, orderBy, conditions, bindVars);
        }

        public List doFindAllQuery(RowType rowType, String where, String orderBy, String conditions, Object bindVars[], RowType rowTypes[])
            throws DataNetworkException, DataQueryException
        {
            QueryProcessor qp = getFinderProcessor(rowType);
            return qp.doFindAllQuery(rowType, where, orderBy, conditions, bindVars, rowTypes);
        }

        public ListPage doFindAllQuery(RowType rowType, String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber, 
                RowType rowTypes[])
            throws DataNetworkException, DataQueryException
        {
            QueryProcessor qp = getFinderProcessor(rowType);
            return qp.doFindAllQuery(rowType, where, orderBy, conditions, bindVars, pageSize, pageNumber, rowTypes);
        }

        public int doGetCountQuery(RowType rowType)
            throws DataNetworkException, DataQueryException
        {
            QueryProcessor qp = getFinderProcessor(rowType);
            return qp.doGetCountQuery(rowType);
        }

        public int doGetCountQuery(RowType rowType, String where, Object bindVars[])
            throws DataNetworkException, DataQueryException
        {
            QueryProcessor qp = getFinderProcessor(rowType);
            return qp.doGetCountQuery(rowType, where, bindVars);
        }

        public Object doInsertQuery(String tableName, Row initialValues)
            throws DataNetworkException, DataQueryException
        {
            return doInsertQuery(initialValues);
        }

        public Object doInsertQuery(String tableName, Map initialValues)
            throws DataNetworkException, DataQueryException
        {
            throw new UnsupportedOperationException("Deprecated method doInsertQuery() not implemented for default processor.");
        }

        public int doUpdateQuery(String tableName, Row newValues)
            throws DataNetworkException, DataQueryException
        {
            return doUpdateQuery(newValues);
        }

        public int doUpdateQuery(String tableName, Object primaryKey, Map changes)
            throws DataNetworkException, DataQueryException
        {
            if(!(primaryKey instanceof PrimaryKey))
                throw new IllegalArgumentException("not a PrimaryKey: " + primaryKey);
            else
                return doUpdateQuery((PrimaryKey)primaryKey, changes);
        }

        public int doUpdateAllQuery(String tableName, String where, Object bindVars[], Map changes)
            throws DataNetworkException, DataQueryException
        {
            throw new UnsupportedOperationException("Deprecated method doUpdateAllQuery() not implemented for default processor.");
        }

        public int doDeleteQuery(String tableName, Object primaryKey)
            throws DataNetworkException, DataQueryException
        {
            if(!(primaryKey instanceof PrimaryKey))
                throw new IllegalArgumentException("not a PrimaryKey: " + primaryKey);
            else
                return doDeleteQuery((PrimaryKey)primaryKey);
        }

        public int doDeleteAllQuery(String tableName, String where, Object bindVars[])
            throws DataNetworkException, DataQueryException
        {
            throw new UnsupportedOperationException("Deprecated method doDeleteAllQuery() not implemented for default processor.");
        }

        public Row doFindByPrimaryKeyQuery(String tableName, Object primaryKey, String conditions)
            throws DataNetworkException, DataQueryException, DataFindException
        {
            if(!(primaryKey instanceof PrimaryKey))
                throw new IllegalArgumentException("not a PrimaryKey: " + primaryKey);
            else
                return doFindByPrimaryKeyQuery((PrimaryKey)primaryKey, conditions);
        }

        public Row doFindByPrimaryKeyQuery(String tableName, Object primaryKey, String where, String conditions, Object bindVars[])
            throws DataNetworkException, DataQueryException, DataFindException
        {
            if(!(primaryKey instanceof PrimaryKey))
                throw new IllegalArgumentException("not a PrimaryKey: " + primaryKey);
            else
                return doFindByPrimaryKeyQuery((PrimaryKey)primaryKey, where, conditions, bindVars);
        }

        public List doFindAllQuery(String tableName, String where, String conditions, Object bindVars[])
            throws DataNetworkException, DataQueryException
        {
            throw new UnsupportedOperationException("Deprecated method doFindAllQuery() not implemented for default processor.");
        }

        public List doFindAllQuery(String tableName, String where, String orderBy, String conditions, Object bindVars[])
            throws DataNetworkException, DataQueryException
        {
            throw new UnsupportedOperationException("Deprecated method doFindAllQuery() not implemented for default processor.");
        }

        public ListPage doFindAllQuery(String tableName, String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber)
            throws DataNetworkException, DataQueryException
        {
            throw new UnsupportedOperationException("Deprecated method doFindAllQuery() not implemented for default processor.");
        }

        public int doGetCountQuery(String tableName, String where, Object bindVars[])
            throws DataNetworkException, DataQueryException
        {
            throw new UnsupportedOperationException("Deprecated method doGetCountQuery() not implemented for default processor.");
        }

        public long doGetNewPkValueQuery(String tableName)
            throws DataNetworkException, DataQueryException
        {
            throw new UnsupportedOperationException("Deprecated method doGetNewPkValueQuery() not implemented for default processor.");
        }

        public void forceReadonlyMode(RowType rowType, boolean readonly)
            throws UnsupportedOperationException, DataNetworkException
        {
            QueryProcessor qp = getFinderProcessor(rowType);
            qp.forceReadonlyMode(rowType, readonly);
        }

        public QueryProcessorDefaultImpl(Database database)
        {
            super(database);
        }
    }


    public QueryProcessorFactoryDefaultImpl(Properties properties, String namespace)
        throws SQLException
    {
        super(properties, namespace);
    }

    protected QueryProcessor createQueryProcessor(Database database)
    {
        return new QueryProcessorDefaultImpl(database);
    }
}

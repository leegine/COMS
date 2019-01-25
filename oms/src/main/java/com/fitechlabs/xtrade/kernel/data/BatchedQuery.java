// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BatchedQuery.java

package com.fitechlabs.xtrade.kernel.data;

import com.fitechlabs.dbind.*;
import java.io.Serializable;
import java.util.Map;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data:
//            TransactionCallback

public final class BatchedQuery
    implements Serializable
{

    public String toString()
    {
        String t = "<<unknown>>";
        switch(queryType)
        {
        case 10: // '\n'
            t = "INSERT";
            break;

        case 11: // '\013'
            t = "INSERT_MAP";
            break;

        case 20: // '\024'
            t = "UPDATE";
            break;

        case 21: // '\025'
            t = "UPDATE_MAP";
            break;

        case 22: // '\026'
            t = "UPDATE_ALL";
            break;

        case 23: // '\027'
            t = "UPDATE_WHERE";
            break;

        case 24: // '\030'
            t = "UPDATE_MAP_WHERE";
            break;

        case 30: // '\036'
            t = "DELETE";
            break;

        case 31: // '\037'
            t = "DELETE_ALL";
            break;

        case 32: // ' '
            t = "DELETE_WHERE";
            break;

        case 40: // '('
            t = "FIND_BY_PK";
            break;

        case 41: // ')'
            t = "FIND_BY_PK_W";
            break;

        case 50: // '2'
            t = "FIND_ALL";
            break;

        case 51: // '3'
            t = "FIND_ALL_PAGE";
            break;

        case 60: // '<'
            t = "CALL";
            break;

        case 70: // 'F'
            t = "TX";
            break;

        case 80: // 'P'
            t = "LOCK";
            break;

        case 90: // 'Z'
            t = "GET_PK";
            break;

        case 100: // 'd'
            t = "COUNT";
            break;
        }
        StringBuffer sb = new StringBuffer("(" + t + ": " + rowType);
        if(queryArgs != null)
        {
            for(int i = 0; i < queryArgs.length; i++)
            {
                sb.append(", ");
                sb.append(queryArgs[i]);
            }

        }
        sb.append(")");
        return sb.toString();
    }

    private BatchedQuery(String table, int queryType, Object args[])
    {
        tableName = table;
        rowType = new RowType(table, null);
        this.queryType = queryType;
        queryArgs = args;
    }

    private BatchedQuery(RowType rowType, int queryType, Object args[])
    {
        tableName = rowType.getTableName();
        this.rowType = rowType;
        this.queryType = queryType;
        queryArgs = args;
    }

    /**
     * @deprecated Method createInsertQuery is deprecated
     */

    public static BatchedQuery createInsertQuery(String tableName, Row initialValues)
    {
        return createInsertQuery(initialValues);
    }

    /**
     * @deprecated Method createInsertQuery is deprecated
     */

    public static BatchedQuery createInsertQuery(String tableName, Map initialValues)
    {
        return new BatchedQuery(tableName, 11, new Object[] {
            initialValues
        });
    }

    /**
     * @deprecated Method createUpdateQuery is deprecated
     */

    public static BatchedQuery createUpdateQuery(String tableName, Row newValues)
    {
        return createUpdateQuery(newValues);
    }

    /**
     * @deprecated Method createUpdateQuery is deprecated
     */

    public static BatchedQuery createUpdateQuery(String tableName, Object primaryKey, Map changes)
    {
        return createUpdateQuery((PrimaryKey)primaryKey, changes);
    }

    /**
     * @deprecated Method createUpdateAllQuery is deprecated
     */

    public static BatchedQuery createUpdateAllQuery(String tableName, String where, Object bindVars[], Map changes)
    {
        return new BatchedQuery(tableName, 22, new Object[] {
            where, bindVars, changes
        });
    }

    /**
     * @deprecated Method createDeleteQuery is deprecated
     */

    public static BatchedQuery createDeleteQuery(String tableName, Object primaryKey)
    {
        return createDeleteQuery((PrimaryKey)primaryKey);
    }

    /**
     * @deprecated Method createDeleteAllQuery is deprecated
     */

    public static BatchedQuery createDeleteAllQuery(String tableName, String where, Object bindVars[])
    {
        return new BatchedQuery(tableName, 31, new Object[] {
            where, bindVars
        });
    }

    /**
     * @deprecated Method createFindByPrimaryKeyQuery is deprecated
     */

    public static BatchedQuery createFindByPrimaryKeyQuery(String tableName, Object primaryKey, String conditions)
    {
        return createFindByPrimaryKeyQuery((PrimaryKey)primaryKey, conditions);
    }

    /**
     * @deprecated Method createFindByPrimaryKeyQuery is deprecated
     */

    public static BatchedQuery createFindByPrimaryKeyQuery(String tableName, Object primaryKey, String where, String conditions, Object bindVars[])
    {
        return createFindByPrimaryKeyQuery((PrimaryKey)primaryKey, where, conditions, bindVars);
    }

    /**
     * @deprecated Method createFindAllQuery is deprecated
     */

    public static BatchedQuery createFindAllQuery(String tableName, String where, String orderBy, String conditions, Object bindVars[])
    {
        return new BatchedQuery(tableName, 50, new Object[] {
            where, orderBy, conditions, bindVars, null
        });
    }

    /**
     * @deprecated Method createFindAllQuery is deprecated
     */

    public static BatchedQuery createFindAllQuery(String tableName, String where, String conditions, Object bindVars[])
    {
        return new BatchedQuery(tableName, 50, new Object[] {
            where, null, conditions, bindVars, null
        });
    }

    /**
     * @deprecated Method createFindAllQuery is deprecated
     */

    public static BatchedQuery createFindAllQuery(String tableName, String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber)
    {
        return new BatchedQuery(tableName, 51, new Object[] {
            where, orderBy, conditions, bindVars, new Integer(pageSize), new Integer(pageNumber), null
        });
    }

    public static BatchedQuery createProcedureCallQuery(String procName, Object inArgs[], Object outArgs[], boolean isReadOnly)
    {
        return new BatchedQuery(procName, 60, new Object[] {
            inArgs, outArgs, isReadOnly ? Boolean.TRUE : Boolean.FALSE
        });
    }

    public static BatchedQuery createProcedureCallQuery(String procName, Object inArgs[], Object outArgs[])
    {
        return createProcedureCallQuery(procName, inArgs, outArgs, false);
    }

    public static BatchedQuery createTransactionCallbackQuery(TransactionCallback callback)
    {
        return new BatchedQuery((String)null, 70, new Object[] {
            new Integer(1), callback
        });
    }

    public static BatchedQuery createTransactionCallbackQuery(int transactionSemantics, TransactionCallback callback)
    {
        return new BatchedQuery((String)null, 70, new Object[] {
            new Integer(transactionSemantics), callback
        });
    }

    public static BatchedQuery createLockPartitionQuery(boolean waitOnBusy)
    {
        return new BatchedQuery((String)null, 80, new Object[] {
            waitOnBusy ? Boolean.TRUE : Boolean.FALSE
        });
    }

    /**
     * @deprecated Method createGetNewPkValueQuery is deprecated
     */

    public static BatchedQuery createGetNewPkValueQuery(String tableName)
    {
        return new BatchedQuery(tableName, 90, new Object[0]);
    }

    /**
     * @deprecated Method createGetCountQuery is deprecated
     */

    public static BatchedQuery createGetCountQuery(String tableName, String where, Object bindVars[])
    {
        return new BatchedQuery(tableName, 100, new Object[] {
            where, bindVars
        });
    }

    public static BatchedQuery createInsertQuery(Row initialValues)
    {
        RowType rowType = initialValues.getRowType();
        return new BatchedQuery(rowType, 10, new Object[] {
            initialValues
        });
    }

    public static BatchedQuery createUpdateQuery(Row newValues)
    {
        RowType rowType = newValues.getRowType();
        return new BatchedQuery(rowType, 20, new Object[] {
            newValues
        });
    }

    public static BatchedQuery createUpdateQuery(Row newValues, String where, Object bindVars[])
    {
        RowType rowType = newValues.getRowType();
        return new BatchedQuery(rowType, 23, new Object[] {
            newValues, where, bindVars
        });
    }

    public static BatchedQuery createUpdateQuery(PrimaryKey primaryKey, Map changes)
    {
        RowType rowType = primaryKey.getRowType();
        return new BatchedQuery(rowType, 21, new Object[] {
            primaryKey, changes
        });
    }

    public static BatchedQuery createUpdateQuery(PrimaryKey primaryKey, String where, Object bindVars[], Map changes)
    {
        RowType rowType = primaryKey.getRowType();
        return new BatchedQuery(rowType, 24, new Object[] {
            primaryKey, where, bindVars, changes
        });
    }

    public static BatchedQuery createUpdateAllQuery(RowType rowType, Map changes)
    {
        return new BatchedQuery(rowType, 22, new Object[] {
            null, null, changes
        });
    }

    public static BatchedQuery createUpdateAllQuery(RowType rowType, String where, Object bindVars[], Map changes)
    {
        return new BatchedQuery(rowType, 22, new Object[] {
            where, bindVars, changes
        });
    }

    public static BatchedQuery createDeleteQuery(PrimaryKey primaryKey)
    {
        RowType rowType = primaryKey.getRowType();
        return new BatchedQuery(rowType, 30, new Object[] {
            primaryKey
        });
    }

    public static BatchedQuery createDeleteQuery(PrimaryKey primaryKey, String where, Object bindVars[])
    {
        RowType rowType = primaryKey.getRowType();
        return new BatchedQuery(rowType, 32, new Object[] {
            primaryKey, where, bindVars
        });
    }

    public static BatchedQuery createDeleteAllQuery(RowType rowType)
    {
        return new BatchedQuery(rowType, 31, new Object[] {
            null, null
        });
    }

    public static BatchedQuery createDeleteAllQuery(RowType rowType, String where, Object bindVars[])
    {
        return new BatchedQuery(rowType, 31, new Object[] {
            where, bindVars
        });
    }

    public static BatchedQuery createFindByPrimaryKeyQuery(PrimaryKey primaryKey)
    {
        RowType rowType = primaryKey.getRowType();
        return new BatchedQuery(rowType, 40, new Object[] {
            primaryKey, null
        });
    }

    public static BatchedQuery createFindByPrimaryKeyQuery(PrimaryKey primaryKey, String conditions)
    {
        RowType rowType = primaryKey.getRowType();
        return new BatchedQuery(rowType, 40, new Object[] {
            primaryKey, conditions
        });
    }

    public static BatchedQuery createFindByPrimaryKeyQuery(PrimaryKey primaryKey, String where, Object bindVars[])
    {
        RowType rowType = primaryKey.getRowType();
        return new BatchedQuery(rowType, 41, new Object[] {
            primaryKey, where, null, bindVars
        });
    }

    public static BatchedQuery createFindByPrimaryKeyQuery(PrimaryKey primaryKey, String where, String conditions, Object bindVars[])
    {
        RowType rowType = primaryKey.getRowType();
        return new BatchedQuery(rowType, 41, new Object[] {
            primaryKey, where, conditions, bindVars
        });
    }

    public static BatchedQuery createFindAllQuery(RowType rowType)
    {
        return new BatchedQuery(rowType, 50, new Object[] {
            null, null, null, null, null
        });
    }

    public static BatchedQuery createFindAllQuery(RowType rowType, String where, Object bindVars[])
    {
        return new BatchedQuery(rowType, 50, new Object[] {
            where, null, null, bindVars, null
        });
    }

    public static BatchedQuery createFindAllQuery(RowType rowType, String conditions)
    {
        return new BatchedQuery(rowType, 50, new Object[] {
            null, null, conditions, null, null
        });
    }

    public static BatchedQuery createFindAllQuery(RowType rowType, String where, String conditions, Object bindVars[])
    {
        return new BatchedQuery(rowType, 50, new Object[] {
            where, null, conditions, bindVars, null
        });
    }

    public static BatchedQuery createFindAllQuery(RowType rowType, String where, String orderBy, String conditions, Object bindVars[])
    {
        return new BatchedQuery(rowType, 50, new Object[] {
            where, orderBy, conditions, bindVars, null
        });
    }

    public static BatchedQuery createFindAllQuery(RowType rowType, String where, String orderBy, String conditions, Object bindVars[], RowType rowTypes[])
    {
        return new BatchedQuery(rowType, 50, new Object[] {
            where, orderBy, conditions, bindVars, rowTypes
        });
    }

    public static BatchedQuery createFindAllQuery(RowType rowType, String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber)
    {
        return new BatchedQuery(rowType, 51, new Object[] {
            where, orderBy, conditions, bindVars, new Integer(pageSize), new Integer(pageNumber), null
        });
    }

    public static BatchedQuery createFindAllQuery(RowType rowType, String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber, RowType rowTypes[])
    {
        return new BatchedQuery(rowType, 51, new Object[] {
            where, orderBy, conditions, bindVars, new Integer(pageSize), new Integer(pageNumber), rowTypes
        });
    }

    public static BatchedQuery createGetNewPkValueQuery(RowType rowType)
    {
        return new BatchedQuery(rowType, 90, new Object[0]);
    }

    public static BatchedQuery createGetCountQuery(RowType rowType)
    {
        return new BatchedQuery(rowType, 100, new Object[] {
            null, null
        });
    }

    public static BatchedQuery createGetCountQuery(RowType rowType, String where, Object bindVars[])
    {
        return new BatchedQuery(rowType, 100, new Object[] {
            where, bindVars
        });
    }

    public String getTableName()
    {
        return tableName;
    }

    public RowType getRowType()
    {
        return rowType;
    }

    public int getQueryType()
    {
        return queryType;
    }

    public Object[] getQueryArgs()
    {
        return queryArgs;
    }

    public static final int INSERT = 10;
    public static final int INSERT_MAP = 11;
    public static final int UPDATE = 20;
    public static final int UPDATE_MAP = 21;
    public static final int UPDATE_ALL = 22;
    public static final int UPDATE_WHERE = 23;
    public static final int UPDATE_MAP_WHERE = 24;
    public static final int DELETE = 30;
    public static final int DELETE_ALL = 31;
    public static final int DELETE_WHERE = 32;
    public static final int FIND_BY_PRIMARY_KEY = 40;
    public static final int FIND_BY_PK_WHERE = 41;
    public static final int FIND_ALL = 50;
    public static final int FIND_ALL_PAGINATED = 51;
    public static final int PROCEDURE_CALL = 60;
    public static final int TRANSACTIONAL_CALLBACK = 70;
    public static final int LOCK_PARTITION = 80;
    public static final int GET_NEW_PK_VALUE = 90;
    public static final int GET_COUNT = 100;
    private String tableName;
    private int queryType;
    private Object queryArgs[];
    private RowType rowType;
}

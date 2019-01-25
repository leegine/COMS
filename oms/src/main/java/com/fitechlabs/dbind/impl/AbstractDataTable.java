// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractDataTable.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.dbind.*;
import java.util.*;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            BindSql, DataTable, SQLHelper, Accessor

public abstract class AbstractDataTable
    implements DataTable
{

    protected AbstractDataTable(String name, SQLHelper sqlHelper, Accessor accessor)
    {
        this.sqlHelper = sqlHelper;
        this.accessor = accessor;
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public SQLHelper getSQLHelper()
    {
        return sqlHelper;
    }

    public Accessor getAccessor()
    {
        return accessor;
    }

    public Collection getColumnNames()
    {
        return sqlHelper.getColumnNames();
    }

    public Object createPk()
        throws ConnectionException, UpdateException
    {
        throw new UnsupportedOperationException("createPK() not supported");
    }

    public int insert(Row initialValues)
        throws ConnectionException, InsertException
    {
        BindSql insertSpec = sqlHelper.toInsertSpec(initialValues);
        return accessor.mutateInsert(name, insertSpec);
    }

    public int insert(Map initialValues)
        throws InsertException, ConnectionException
    {
        BindSql insertSpec = sqlHelper.toInsertSpec(initialValues);
        return accessor.mutateInsert(name, insertSpec);
    }

    public int update(Row newValues, String where, Object bindVariables[])
        throws ConnectionException, UpdateException
    {
        return accessor.mutateUpdate(name, sqlHelper.toUpdateSpec(newValues), sqlHelper.getMutatePkBindSql(newValues.getPrimaryKey()), createBindSql(where, bindVariables));
    }

    public int update(PrimaryKey primaryKey, Map changes)
        throws UpdateException, ConnectionException
    {
        BindSql updateSpec = sqlHelper.toUpdateSpec(changes);
        if(updateSpec == null)
            return 0;
        else
            return accessor.mutateUpdate(name, updateSpec, sqlHelper.getMutatePkBindSql(primaryKey), null);
    }

    public int update(PrimaryKey primaryKey, String where, Object bindVariables[], Map changes)
        throws UpdateException, ConnectionException
    {
        return accessor.mutateUpdate(name, sqlHelper.toUpdateSpec(changes), sqlHelper.getMutatePkBindSql(primaryKey), createBindSql(where, bindVariables));
    }

    public int updateAll(String where, Object bindVariables[], Map changes)
        throws UpdateException, ConnectionException
    {
        return accessor.mutateUpdate(name, sqlHelper.toUpdateSpec(changes), createBindSql(where, bindVariables), null);
    }

    public int delete(PrimaryKey primaryKey)
        throws ConnectionException, DeleteException
    {
        return accessor.mutateDelete(name, sqlHelper.getDeleteFromSql(), sqlHelper.getMutatePkBindSql(primaryKey), null);
    }

    public int delete(PrimaryKey primaryKey, String where, Object bindVariables[])
        throws ConnectionException, DeleteException
    {
        return accessor.mutateDelete(name, sqlHelper.getDeleteFromSql(), sqlHelper.getMutatePkBindSql(primaryKey), createBindSql(where, bindVariables));
    }

    public int deleteAll(String where, Object bindVariables[])
        throws ConnectionException, DeleteException
    {
        return accessor.mutateDelete(name, sqlHelper.getDeleteFromSql(), createBindSql(where, bindVariables), null);
    }

    public Row select(PrimaryKey primaryKey)
        throws ConnectionException, SelectException
    {
        Object obj = accessor.accessUnique(sqlHelper.getSelectRowSql(), sqlHelper.getWhereKeysMatchBindSql(), sqlHelper.getAccessPkBindSql(primaryKey), null, null, sqlHelper.getRowFiller());
        return (Row)obj;
    }

    public Map selectMap(PrimaryKey primaryKey)
        throws ConnectionException, SelectException
    {
        Object obj = accessor.accessUnique(sqlHelper.getSelectMapSql(), sqlHelper.getWhereKeysMatchBindSql(), sqlHelper.getAccessPkBindSql(primaryKey), null, null, sqlHelper.getMapFiller());
        return (Map)obj;
    }

    public Row select(PrimaryKey primaryKey, String conditions)
        throws ConnectionException, SelectException
    {
        Object obj = accessor.accessUnique(sqlHelper.getSelectRowSql(), sqlHelper.getWhereKeysMatchBindSql(), sqlHelper.getAccessPkBindSql(primaryKey), null, conditions, sqlHelper.getRowFiller());
        return (Row)obj;
    }

    public Row select(PrimaryKey primaryKey, String where, String conditions, Object bindVariables[])
        throws ConnectionException, SelectException
    {
        Object obj = accessor.accessUnique(sqlHelper.getSelectRowSql(), sqlHelper.getWhereKeysMatchBindSql(), sqlHelper.getAccessPkBindSql(primaryKey), createBindSql(where, bindVariables), conditions, sqlHelper.getRowFiller());
        return (Row)obj;
    }

    public List selectAll(String where, String orderBy, String conditions, Object bindVariables[])
        throws ConnectionException, SelectException
    {
        List list = accessor.accessList(sqlHelper.getSelectRowSql(), sqlHelper.getWhereKeysMatchBindSql(), createBindSql(where, bindVariables), orderBy, conditions, sqlHelper.getRowFiller());
        return list;
    }

    public List selectAll(String where, String orderBy, String conditions, Object bindVariables[], RowType dependentTables[])
        throws ConnectionException, SelectException
    {
        return selectAll(where, orderBy, conditions, bindVariables);
    }

    public ListSubset selectAll(String where, String orderBy, String conditions, Object bindVariables[], int fromIndex, int toIndex)
        throws ConnectionException, SelectException
    {
        int count = selectCount(where, bindVariables);
        List rows = accessor.accessList(sqlHelper.getSelectRowSql(), sqlHelper.getWhereKeysMatchBindSql(), sqlHelper.getPaginatedBindSql(where, orderBy, bindVariables, fromIndex, toIndex), orderBy, conditions, sqlHelper.getRowFiller());
        return ArrayListSubset.fromSubList(rows, fromIndex, count);
    }

    public ListSubset selectAll(String where, String orderBy, String conditions, Object bindVariables[], int fromIndex, int toIndex, RowType dependentTables[])
        throws ConnectionException, SelectException
    {
        return selectAll(where, orderBy, conditions, bindVariables, fromIndex, toIndex);
    }

    public List selectPks(String where, String orderBy, String conditions, Object bindVariables[])
        throws ConnectionException, SelectException
    {
        List list = accessor.accessList(sqlHelper.getSelectPkSql(), sqlHelper.getWhereKeysMatchBindSql(), createBindSql(where, bindVariables), orderBy, conditions, sqlHelper.getPkFiller());
        return list;
    }

    public int selectCount(String where, Object bindVariables[])
        throws ConnectionException, SelectException
    {
        Object obj = accessor.accessUnique(sqlHelper.getSelectCountSql(), sqlHelper.getWhereKeysMatchBindSql(), createBindSql(where, bindVariables), null, null, sqlHelper.getCountFiller());
        Integer count = (Integer)obj;
        return count.intValue();
    }

    private BindSql createBindSql(String where, Object bindVariables[])
    {
        if(where == null || where.length() == 0)
            return null;
        else
            return new BindSql(where, bindVariables);
    }

    public DataTable getSuperTable()
    {
        return null;
    }

    public void setReadonlyMode(boolean readonly)
    {
        throw new UnsupportedOperationException("setReadonlyMode method can only be called on a CachingDataTable object");
    }

    public abstract boolean hasPrimaryKey();

    protected final Accessor accessor;
    protected final SQLHelper sqlHelper;
    private final String name;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WrapperDataTable.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.dbind.*;
import java.util.*;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            DataTable, SQLHelper

public class WrapperDataTable
    implements DataTable
{

    public WrapperDataTable(DataTable source)
    {
        this.source = source;
    }

    public Object createPk()
        throws ConnectionException, UpdateException
    {
        return source.createPk();
    }

    public int delete(PrimaryKey primaryKey, String where, Object bindVariables[])
        throws ConnectionException, DeleteException
    {
        return source.delete(primaryKey, where, bindVariables);
    }

    public int deleteAll(String where, Object bindVariables[])
        throws ConnectionException, DeleteException
    {
        return source.deleteAll(where, bindVariables);
    }

    public Collection getColumnNames()
    {
        return source.getColumnNames();
    }

    public int insert(Map initialValues)
        throws InsertException, ConnectionException
    {
        return source.insert(initialValues);
    }

    public int insert(Row initialValues)
        throws ConnectionException, InsertException
    {
        return source.insert(initialValues);
    }

    public Row select(PrimaryKey primaryKey, String where, String conditions, Object bindVars[])
        throws ConnectionException, SelectException, FindException
    {
        return source.select(primaryKey, where, conditions, bindVars);
    }

    public Row select(PrimaryKey primaryKey, String conditions)
        throws ConnectionException, SelectException, FindException
    {
        return source.select(primaryKey, conditions);
    }

    public Row select(PrimaryKey primaryKey)
        throws ConnectionException, SelectException, FindException
    {
        return source.select(primaryKey);
    }

    public ListSubset selectAll(String where, String orderBy, String conditions, Object bindVariables[], int fromIndex, int toIndex)
        throws ConnectionException, SelectException
    {
        return source.selectAll(where, orderBy, conditions, bindVariables, fromIndex, toIndex);
    }

    public ListSubset selectAll(String where, String orderBy, String conditions, Object bindVariables[], int fromIndex, int toIndex, RowType dependentTables[])
        throws ConnectionException, SelectException
    {
        return source.selectAll(where, orderBy, conditions, bindVariables, fromIndex, toIndex, dependentTables);
    }

    public List selectAll(String where, String orderBy, String conditions, Object bindVariables[])
        throws ConnectionException, SelectException
    {
        return source.selectAll(where, orderBy, conditions, bindVariables);
    }

    public List selectAll(String where, String orderBy, String conditions, Object bindVariables[], RowType dependentTables[])
        throws ConnectionException, SelectException
    {
        return source.selectAll(where, orderBy, conditions, bindVariables, dependentTables);
    }

    public int selectCount(String where, Object bindVariables[])
        throws ConnectionException, SelectException
    {
        return source.selectCount(where, bindVariables);
    }

    public List selectPks(String where, String orderBy, String conditions, Object bindVariables[])
        throws ConnectionException, SelectException
    {
        return source.selectPks(where, orderBy, conditions, bindVariables);
    }

    public int update(PrimaryKey primaryKey, String where, Object bindVariables[], Map changes)
        throws UpdateException, ConnectionException
    {
        return source.update(primaryKey, where, bindVariables, changes);
    }

    public int update(Row newValues, String where, Object bindVariables[])
        throws ConnectionException, UpdateException
    {
        return source.update(newValues, where, bindVariables);
    }

    public int updateAll(String where, Object bindVariables[], Map changes)
        throws UpdateException, ConnectionException
    {
        return source.updateAll(where, bindVariables, changes);
    }

    public SQLHelper getSQLHelper()
    {
        return source.getSQLHelper();
    }

    public String getName()
    {
        return source.getName();
    }

    public DataTable getSuperTable()
    {
        return source.getSuperTable();
    }

    public boolean hasPrimaryKey()
    {
        return source.hasPrimaryKey();
    }

    public void setReadonlyMode(boolean readonly)
    {
        throw new UnsupportedOperationException("setReadonlyMode method can only be called on a CachingDataTable object");
    }

    protected DataTable source;
}

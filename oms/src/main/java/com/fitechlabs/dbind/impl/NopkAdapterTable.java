// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NopkAdapterTable.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.dbind.*;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            AdapterTable, DataTable

public class NopkAdapterTable extends AdapterTable
{

    public NopkAdapterTable(DataTable impl)
    {
        super(impl);
    }

    public Object insert(Row initialValues)
        throws ConnectionException, InsertException
    {
        int count = super.impl.insert(initialValues);
        return null;
    }

    public int delete(Object primaryKey)
        throws ConnectionException, DeleteException
    {
        throw new UnsupportedOperationException("pk-based operations not supported for tables with no primary key");
    }

    public int delete(PrimaryKey primaryKey, String where, Object bindVariables[])
        throws ConnectionException, DeleteException
    {
        throw new UnsupportedOperationException("pk-based operations not supported for tables with no primary key");
    }

    public int delete(PrimaryKey primaryKey)
        throws ConnectionException, DeleteException
    {
        throw new UnsupportedOperationException("pk-based operations not supported for tables with no primary key");
    }

    public Row select(Object primaryKey, String where, String conditions, Object bindVars[])
        throws ConnectionException, SelectException, FindException
    {
        throw new UnsupportedOperationException("pk-based operations not supported for tables with no primary key");
    }

    public Row select(Object primaryKey, String conditions)
        throws ConnectionException, SelectException, FindException
    {
        throw new UnsupportedOperationException("pk-based operations not supported for tables with no primary key");
    }

    public Row select(Object primaryKey)
        throws ConnectionException, SelectException, FindException
    {
        throw new UnsupportedOperationException("pk-based operations not supported for tables with no primary key");
    }

    public Row select(PrimaryKey primaryKey, String where, String conditions, Object bindVars[])
        throws ConnectionException, SelectException, FindException
    {
        throw new UnsupportedOperationException("pk-based operations not supported for tables with no primary key");
    }

    public Row select(PrimaryKey primaryKey, String conditions)
        throws ConnectionException, SelectException, FindException
    {
        throw new UnsupportedOperationException("pk-based operations not supported for tables with no primary key");
    }

    public Row select(PrimaryKey primaryKey)
        throws ConnectionException, SelectException, FindException
    {
        throw new UnsupportedOperationException("pk-based operations not supported for tables with no primary key");
    }

    public List selectPks(String where, String orderBy, String conditions, Object bindVariables[])
        throws ConnectionException, SelectException
    {
        throw new UnsupportedOperationException("pk-based operations not supported for tables with no primary key");
    }

    public int update(Object primaryKey, Map changes)
        throws UpdateException, ConnectionException
    {
        throw new UnsupportedOperationException("pk-based operations not supported for tables with no primary key");
    }

    public int update(PrimaryKey primaryKey, Map changes)
        throws UpdateException, ConnectionException
    {
        throw new UnsupportedOperationException("pk-based operations not supported for tables with no primary key");
    }

    public int update(PrimaryKey primaryKey, String where, Object bindVariables[], Map changes)
        throws UpdateException, ConnectionException
    {
        throw new UnsupportedOperationException("pk-based operations not supported for tables with no primary key");
    }

    public int update(Row newValues, String where, Object bindVariables[])
        throws ConnectionException, UpdateException
    {
        throw new UnsupportedOperationException("pk-based operations not supported for tables with no primary key");
    }

    public int update(Row newValues)
        throws ConnectionException, UpdateException
    {
        throw new UnsupportedOperationException("pk-based operations not supported for tables with no primary key");
    }

    private static final String MESSAGE = "pk-based operations not supported for tables with no primary key";
}

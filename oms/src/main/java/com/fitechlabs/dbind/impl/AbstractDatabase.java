// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractDatabase.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.dbind.*;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import javax.transaction.Transaction;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            AdapterTable, NopkAdapterTable, AbstractDataStore, SubclassDataStore, 
//            DataTable

public abstract class AbstractDatabase
    implements Database
{

    public AbstractDatabase(SubclassDataStore sourceDataStore)
    {
        this.sourceDataStore = sourceDataStore;
    }

    SubclassDataStore getSubclassDataStore()
    {
        return sourceDataStore;
    }

    public DataSource getDataSource()
    {
        return sourceDataStore.getDataSource();
    }

    public void setDataSource(DataSource ds)
    {
        sourceDataStore.setDataSource(ds);
    }

    public Table getTable(String tableName)
    {
        Table t = (Table)userTables.get(tableName);
        if(t == null)
            synchronized(userTables)
            {
                t = (Table)userTables.get(tableName);
                if(t == null)
                {
                    DataTable dt = sourceDataStore.getDataTable(tableName);
                    if(dt == null)
                        throw new IllegalArgumentException("no table named " + tableName);
                    t = newTableInstance(tableName, dt);
                    userTables.put(tableName, t);
                }
            }
        return t;
    }

    protected Table newTableInstance(String name, DataTable dt)
    {
        if(dt.hasPrimaryKey())
            return new AdapterTable(dt);
        else
            return new NopkAdapterTable(dt);
    }

    public Table createTable(String tableName, Class pkClass, Class paramsClass)
    {
        sourceDataStore.addPlainTable(tableName, pkClass, paramsClass);
        return getTable(tableName);
    }

    public Table createTable(String tableName, Class pkClass, Class paramsClass, String superTableName, String subclassFieldName)
    {
        if(superTableName == null && subclassFieldName == null)
        {
            return createTable(tableName, pkClass, paramsClass);
        } else
        {
            DataTable dataTable = sourceDataStore.addSubclassTable(tableName, pkClass, paramsClass, superTableName, subclassFieldName);
            return getTable(tableName);
        }
    }

    public void reserveConnection()
        throws DbException
    {
        throw new UnsupportedOperationException("reserveConnection no longer supported - use explicit transaction instead.");
    }

    public void releaseConnection()
    {
        throw new UnsupportedOperationException("releaseConnection no longer supported - use explicit transaction instead.");
    }

    public void beginTransaction()
        throws DbException
    {
        sourceDataStore.beginTransaction();
    }

    public void call(String procedureName, Object inArgs[], Object outArgs[], boolean isReadOnly)
        throws ConnectionException, CallException
    {
        sourceDataStore.call(procedureName, inArgs, outArgs, isReadOnly);
    }

    public void commitTransaction()
        throws DbException
    {
        sourceDataStore.commitTransaction();
    }

    public boolean isTransactionActive()
    {
        return sourceDataStore.isTransactionActive();
    }

    public void resumeTransaction(Transaction suspendedTransaction)
        throws DbException
    {
        sourceDataStore.resumeTransaction(suspendedTransaction);
    }

    public void rollbackTransaction()
        throws DbException
    {
        sourceDataStore.rollbackTransaction();
    }

    public Transaction suspendTransaction()
        throws DbException
    {
        return sourceDataStore.suspendTransaction();
    }

    private final SubclassDataStore sourceDataStore;
    private final Map userTables = new HashMap();
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataStore.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.dbind.*;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.transaction.Transaction;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            DataTable

public interface DataStore
{

    public abstract DataSource getDataSource();

    public abstract void setDataSource(DataSource datasource);

    public abstract DataTable getDataTable(String s);

    public abstract DataTable addDataTable(String s, DataTable datatable);

    public abstract void beginTransaction()
        throws DbException;

    public abstract void call(String s, Object aobj[], Object aobj1[], boolean flag)
        throws ConnectionException, CallException;

    public abstract void commitTransaction()
        throws DbException;

    public abstract boolean isTransactionActive();

    public abstract void resumeTransaction(Transaction transaction)
        throws DbException;

    public abstract void rollbackTransaction()
        throws DbException;

    public abstract Transaction suspendTransaction()
        throws DbException;

    public abstract Connection getConnection()
        throws SQLException;

    public abstract void closeConnection(Connection connection);

    public abstract DataTable addPlainTable(String s, Class class1, Class class2);

    public abstract DataTable addSubclassTable(String s, Class class1, Class class2, String s1, String s2);
}

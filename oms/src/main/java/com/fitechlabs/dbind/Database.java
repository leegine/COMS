// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Database.java

package com.fitechlabs.dbind;

import javax.sql.DataSource;
import javax.transaction.Transaction;

// Referenced classes of package com.fitechlabs.dbind:
//            ConnectionException, CallException, DbException, Table

public interface Database
{

    public abstract Table getTable(String s);

    public abstract void call(String s, Object aobj[], Object aobj1[], boolean flag)
        throws ConnectionException, CallException;

    /**
     * @deprecated Method reserveConnection is deprecated
     */

    public abstract void reserveConnection()
        throws DbException;

    /**
     * @deprecated Method releaseConnection is deprecated
     */

    public abstract void releaseConnection();

    public abstract void beginTransaction()
        throws DbException;

    public abstract void commitTransaction()
        throws DbException;

    public abstract void rollbackTransaction()
        throws DbException;

    public abstract Transaction suspendTransaction()
        throws DbException;

    public abstract void resumeTransaction(Transaction transaction)
        throws DbException;

    public abstract boolean isTransactionActive();

    public abstract DataSource getDataSource();

    public abstract void setDataSource(DataSource datasource);

    public abstract Table createTable(String s, Class class1, Class class2);

    public abstract Table createTable(String s, Class class1, Class class2, String s1, String s2);
}

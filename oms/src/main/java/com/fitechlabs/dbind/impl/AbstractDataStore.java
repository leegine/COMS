// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractDataStore.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import javax.transaction.*;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            AccessorImpl, DataTable, DataStore, SqlStatistics, 
//            Accessor

public abstract class AbstractDataStore
    implements DataStore
{

    public AbstractDataStore(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource()
    {
        return dataSource;
    }

    public void setDataSource(DataSource ds)
    {
        dataSource = ds;
    }

    public Accessor getAccessor()
    {
        return accessor;
    }

    public DataTable getDataTable(String tableName)
    {
        return (DataTable)tables.get(tableName);
    }

    public DataTable addDataTable(String name, DataTable table)
    {
        synchronized(tables)
        {
            if(tables.containsKey(name))
                throw new IllegalStateException("table named '" + name + "' already exists");
            tables.put(name, table);
        }
        return table;
    }

    public Connection getConnection()
        throws SQLException
    {
        TransactionManager tm;
        tm = DBindTransactions.getTransactionManager();
        if(!(tm instanceof DBindTransactionManager))
            break MISSING_BLOCK_LABEL_34;
        return ((DBindTransactionManager)tm).getConnection(dataSource);
        RollbackException re;
        re;
        throw new SQLException("getConnection() called after previous transaction element was rolled back.");
        return dataSource.getConnection();
    }

    public void closeConnection(Connection connection)
    {
        TransactionManager tm = DBindTransactions.getTransactionManager();
        if(tm instanceof DBindTransactionManager)
            ((DBindTransactionManager)tm).closeConnection(connection);
        else
            try
            {
                connection.close();
            }
            catch(Exception ignore) { }
    }

    public void call(String procedureName, Object inArgs[], Object outArgs[], boolean isReadOnly)
        throws ConnectionException, CallException
    {
        Connection con;
        CallableStatement stmt;
        String sql;
        con = null;
        stmt = null;
        sql = null;
        com.fitechlabs.dbind.Row row = null;
        try
        {
            if(DBG)
                log.debug("Procedure Called:" + procedureName + ", isReadOnly:" + isReadOnly);
            con = getConnection();
            if(!isReadOnly)
                DBindTransactions.setIsMutating();
            sql = "{call " + procedureName + getArgsString(inArgs, outArgs) + "}";
            stmt = con.prepareCall(sql);
            if(inArgs != null)
            {
                for(int i = 0; i < inArgs.length; i++)
                    if(inArgs[i] != null)
                        stmt.setObject(1 + i, inArgs[i]);

            }
            if(outArgs != null)
            {
                for(int i = 0; i < outArgs.length; i++)
                    if(outArgs[i] != null)
                        stmt.registerOutParameter(1 + i, ((Integer)outArgs[i]).intValue());

            }
            if(!SqlStatistics.isCollecting())
            {
                stmt.executeUpdate();
            } else
            {
                long start = System.currentTimeMillis();
                try
                {
                    stmt.execute();
                    SqlStatistics.addTiming("db", "call", sql, "success", System.currentTimeMillis() - start);
                }
                catch(SQLException sqe)
                {
                    SqlStatistics.addTiming("db", "call", sql, "failure - " + sqe.getMessage(), System.currentTimeMillis() - start);
                    throw sqe;
                }
            }
            if(outArgs != null)
            {
                for(int i = 0; i < outArgs.length; i++)
                    if(outArgs[i] != null)
                        outArgs[i] = stmt.getObject(1 + i);

            }
        }
        catch(SQLException se)
        {
            throw new CallException("sql exception during call() in " + getClass() + ": " + se, sql);
        }
        closeStatement(stmt);
        closeConnection(con);
        break MISSING_BLOCK_LABEL_414;
        Exception exception;
        exception;
        closeStatement(stmt);
        closeConnection(con);
        throw exception;
    }

    private static String getArgsString(Object in[], Object out[])
    {
        if(in == null || in.length == 0)
            return "";
        StringBuffer sb = new StringBuffer("(");
        int n = Math.max(in == null ? 0 : in.length, out == null ? 0 : out.length);
        for(int i = 0; i < n; i++)
            sb.append(i <= 0 ? "?" : ",?");

        sb.append(")");
        return sb.toString();
    }

    private static void closeStatement(Statement st)
    {
        if(st != null)
            try
            {
                st.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
    }

    public void beginTransaction()
        throws DbException
    {
        try
        {
            DBindTransactions.getTransactionManager().begin();
        }
        catch(SystemException se)
        {
            throw new ConnectionException("transaction management failed, " + se);
        }
        catch(Exception e)
        {
            throw new DbException("begin failed, " + e.toString());
        }
    }

    public void commitTransaction()
        throws DbException
    {
        try
        {
            DBindTransactions.getTransactionManager().commit();
        }
        catch(SystemException se)
        {
            throw new ConnectionException("transaction management failed, " + se);
        }
        catch(Exception e)
        {
            throw new DbException("commit failed, " + e.toString());
        }
    }

    public void rollbackTransaction()
        throws DbException
    {
        try
        {
            DBindTransactions.getTransactionManager().rollback();
        }
        catch(SystemException se)
        {
            throw new ConnectionException("transaction management failed, " + se);
        }
        catch(Exception e)
        {
            throw new DbException("rollback failed, " + e.toString());
        }
    }

    public Transaction suspendTransaction()
        throws DbException
    {
        return DBindTransactions.getTransactionManager().suspend();
        SystemException se;
        se;
        throw new ConnectionException("transaction management failed, " + se);
        Exception e;
        e;
        throw new DbException("suspend failed, " + e.toString());
    }

    public void resumeTransaction(Transaction suspendedTransaction)
        throws DbException
    {
        try
        {
            DBindTransactions.getTransactionManager().resume(suspendedTransaction);
        }
        catch(SystemException se)
        {
            throw new ConnectionException("transaction management failed, " + se);
        }
        catch(Exception e)
        {
            throw new DbException("resume failed, " + e.toString());
        }
    }

    public boolean isTransactionActive()
    {
        TransactionManager tm = DBindTransactions.getTransactionManager();
        int status;
        try
        {
            status = tm.getStatus();
        }
        catch(SystemException e)
        {
            throw new InternalError("can't check transaction status: " + e);
        }
        return status == 0;
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    public abstract DataTable addSubclassTable(String s, Class class1, Class class2, String s1, String s2);

    public abstract DataTable addPlainTable(String s, Class class1, Class class2);

    private static final Logit log;
    private static final boolean DBG;
    private DataSource dataSource;
    private final Accessor accessor = new AccessorImpl(this);
    private final Map tables = new HashMap();

    static 
    {
        log = Logit.getInstance(com.fitechlabs.dbind.impl.AbstractDataStore.class);
        DBG = log.ison();
    }
}

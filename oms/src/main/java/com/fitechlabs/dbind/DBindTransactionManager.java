// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DBindTransactionManager.java

package com.fitechlabs.dbind;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import javax.sql.DataSource;
import javax.transaction.*;
import javax.transaction.xa.XAResource;

public class DBindTransactionManager
    implements TransactionManager
{
    private static class DBindTransaction
        implements Transaction
    {

        public Connection getConnection(DataSource dataSource)
            throws SQLException, RollbackException
        {
            if(status == 1)
                throw new RollbackException("DBindTransactionManager: Transaction is marked 'rollback only'");
            if(connections == null)
                connections = new HashMap();
            Connection c = (Connection)connections.get(dataSource);
            if(c == null)
            {
                c = dataSource.getConnection();
                connections.put(dataSource, c);
                c.setAutoCommit(false);
                if(connections.size() >= 2)
                {
                    DBindTransactionManager.m_log.warn("Distributed transactions are not fully supported by DBindTransactionManager, will use separate transaction semantics for different DataSource objects.");
                    Set set = connections.keySet();
                    for(Iterator it = set.iterator(); it.hasNext();)
                    {
                        DataSource ds = (DataSource)it.next();
                        if(ds == dataSource)
                            DBindTransactionManager.m_log.warn("Enlisted DataSource Object*:" + ds);
                        else
                            DBindTransactionManager.m_log.warn("Enlisted DataSource Object:" + ds);
                    }

                }
            }
            return c;
        }

        public int getStatus()
            throws SystemException
        {
            return status;
        }

        public void commit()
            throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, SystemException
        {
            switch(status)
            {
            case 0: // '\0'
                completeTransactions(8);
                return;

            case 1: // '\001'
                rollback();
                throw new RollbackException("DBindTransactionManager: Transaction was marked 'rollback only'");
            }
            throw new SystemException("DBindTransactionManager: Illegal status: " + status);
        }

        public void setRollbackOnly()
            throws IllegalStateException, SystemException
        {
            status = 1;
        }

        public void rollback()
            throws IllegalStateException, SystemException
        {
            try
            {
                completeTransactions(9);
            }
            catch(Exception illegal)
            {
                throw new IllegalStateException("DBindTransactionManager: Bad exception caught on rollback request - " + illegal);
            }
        }

        private void completeTransactions(int intermediateStatus)
            throws HeuristicMixedException, HeuristicRollbackException
        {
            status = intermediateStatus;
            boolean someCommits = false;
            boolean someRollbacks = false;
            boolean someUnknowns = false;
            String exceptions = "";
            if(connections != null)
            {
                for(Iterator it = connections.values().iterator(); it.hasNext();)
                {
                    Connection c = (Connection)it.next();
                    if(status == 8)
                        try
                        {
                            c.commit();
                            someCommits = true;
                        }
                        catch(Exception e)
                        {
                            status = 9;
                            someRollbacks = true;
                            exceptions = exceptions + " [" + e.getMessage() + "] ";
                            DBindTransactionManager.m_log.error("JDBC Committing transaction fails:");
                            DBindTransactionManager.m_log.error(e);
                        }
                    else
                        try
                        {
                            someRollbacks = true;
                            c.rollback();
                        }
                        catch(Exception e)
                        {
                            status = 5;
                            exceptions = exceptions + " [" + e.getMessage() + "] ";
                            DBindTransactionManager.m_log.error("JDBC Rolling back transaction fails:");
                            DBindTransactionManager.m_log.error(e);
                        }
                    try
                    {
                        c.close();
                    }
                    catch(Exception e) { }
                }

                connections = null;
            }
            switch(status)
            {
            case 8: // '\b'
                status = 3;
                return;

            case 9: // '\t'
                if(someCommits && someRollbacks)
                {
                    status = 5;
                    throw new HeuristicMixedException("DBindTransactionManager: Unsupported distributed transaction was partially committed and partially rolled back. Underlying exception:" + exceptions);
                }
                status = 4;
                if(intermediateStatus == 8)
                    throw new HeuristicRollbackException("DBindTransactionManager: Commit failed, so transaction rolled back instead. Underlying exception:" + exceptions);
                else
                    return;

            case 5: // '\005'
                throw new HeuristicMixedException("DBindTransactionManager: Some rollbacks failed, producing unknown result. Underlying exception:" + exceptions);

            case 6: // '\006'
            case 7: // '\007'
            default:
                throw new HeuristicMixedException("DBindTransactionManager: Illegal state detected: " + status);
            }
        }

        private void setIsMutating()
        {
            if(DBindTransactionManager.DBG)
                DBindTransactionManager.m_log.debug("setIsMutating method called with no parameter.");
            mutating = true;
        }

        private void setIsMutating(String tblName)
        {
            if(DBindTransactionManager.DBG)
                DBindTransactionManager.m_log.debug("setIsMutating method called with tblName:" + tblName);
            mutatingTbls.add(tblName);
        }

        private boolean getIsMutating()
        {
            return mutating;
        }

        private boolean getIsMutating(String tblName)
        {
            if(DBindTransactionManager.DBG)
            {
                DBindTransactionManager.m_log.debug("getIsMutating method called with tblName:" + tblName);
                DBindTransactionManager.m_log.debug("db mutating flag:" + mutating + ", table mutating flag:" + mutatingTbls.contains(tblName));
            }
            return mutating || mutatingTbls.contains(tblName);
        }

        public boolean delistResource(XAResource parm1, int parm2)
            throws IllegalStateException, SystemException
        {
            throw new UnsupportedOperationException("Method delistResource() not implemented by the DBindTransactionManager.");
        }

        public boolean enlistResource(XAResource parm1)
            throws RollbackException, IllegalStateException, SystemException
        {
            throw new UnsupportedOperationException("Method enlistResource() not implemented by the DBindTransactionManager.");
        }

        public void registerSynchronization(Synchronization parm1)
            throws RollbackException, IllegalStateException, SystemException
        {
            throw new UnsupportedOperationException("Method registerSynchronization() not implemented by the DBindTransactionManager.");
        }

        private Map connections;
        private int status;
        private int nestingLevels;
        private boolean mutating;
        Set mutatingTbls;








        private DBindTransaction()
        {
            mutatingTbls = new HashSet();
            status = 0;
            mutating = false;
        }

    }

    private static class PerThreadManager
        implements TransactionManager
    {

        public Connection getConnection(DataSource dataSource)
            throws SQLException, RollbackException
        {
            if(currentTransaction != null)
            {
                return currentTransaction.getConnection(dataSource);
            } else
            {
                implicitTransaction = dataSource.getConnection();
                implicitTransaction.setAutoCommit(true);
                return implicitTransaction;
            }
        }

        private void closeConnection(Connection conn)
        {
            if(implicitTransaction == null)
                break MISSING_BLOCK_LABEL_59;
            if(implicitTransaction != conn)
                throw new RuntimeException("DBindTransactionManager doesn't support multiple connections outside of an explicit transaction.");
            try
            {
                implicitTransaction.close();
            }
            catch(Exception e)
            {
                implicitTransaction = null;
                break MISSING_BLOCK_LABEL_59;
            }
            implicitTransaction = null;
            break MISSING_BLOCK_LABEL_59;
            Exception exception;
            exception;
            implicitTransaction = null;
            throw exception;
        }

        public void begin()
            throws NotSupportedException, SystemException
        {
            if(implicitTransaction != null)
                throw new RuntimeException("DBindTransactionManager can't mix implicit and explicit transactions.");
            if(currentTransaction == null)
                currentTransaction = new DBindTransaction();
            else
                currentTransaction.nestingLevels++;
        }

        public void commit()
            throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException
        {
            if(currentTransaction == null)
                throw new IllegalStateException("DBindTransactionManager: No transaction is underway");
            if(currentTransaction.nestingLevels != 0)
                break MISSING_BLOCK_LABEL_50;
            currentTransaction.commit();
            currentTransaction = null;
            break MISSING_BLOCK_LABEL_58;
            Exception exception;
            exception;
            currentTransaction = null;
            throw exception;
            currentTransaction.nestingLevels--;
        }

        public void rollback()
            throws IllegalStateException, SecurityException, SystemException
        {
            if(currentTransaction == null)
                throw new IllegalStateException("DBindTransactionManager: No transaction is underway");
            if(currentTransaction.nestingLevels != 0)
                break MISSING_BLOCK_LABEL_50;
            currentTransaction.rollback();
            currentTransaction = null;
            break MISSING_BLOCK_LABEL_65;
            Exception exception;
            exception;
            currentTransaction = null;
            throw exception;
            currentTransaction.nestingLevels--;
            currentTransaction.setRollbackOnly();
        }

        public void setRollbackOnly()
            throws IllegalStateException, SystemException
        {
            if(currentTransaction == null)
            {
                throw new IllegalStateException("DBindTransactionManager: No transaction is underway");
            } else
            {
                currentTransaction.setRollbackOnly();
                return;
            }
        }

        public int getStatus()
            throws SystemException
        {
            if(currentTransaction == null)
                return 6;
            else
                return currentTransaction.getStatus();
        }

        public Transaction getTransaction()
            throws SystemException
        {
            return currentTransaction;
        }

        public void setTransactionTimeout(int timeoutValue)
            throws SystemException
        {
            throw new UnsupportedOperationException("DefaultTransactionManager does not support variable transaction timeout");
        }

        public Transaction suspend()
            throws SystemException
        {
            Transaction priorTransaction = currentTransaction;
            currentTransaction = null;
            return priorTransaction;
        }

        public void resume(Transaction transaction)
            throws InvalidTransactionException, IllegalStateException, SystemException
        {
            if(currentTransaction != null)
                throw new IllegalStateException("DBindTransactionManager: Another transaction is underway, so the supplied transaction cannot be resumed.");
            if(transaction != null && !(transaction instanceof DBindTransaction))
            {
                throw new IllegalStateException("DBindTransactionManager: The supplied transaction is of a type not supplied by this TransactionManager, so it cannot be resumed.");
            } else
            {
                currentTransaction = (DBindTransaction)transaction;
                return;
            }
        }

        private void setIsMutating()
        {
            if(currentTransaction != null)
                currentTransaction.setIsMutating();
            else
            if(implicitTransaction == null)
                throw new IllegalStateException("cant set mutating status because no transaction underway");
        }

        private void setIsMutating(String tblName)
        {
            if(currentTransaction != null)
                currentTransaction.setIsMutating(tblName);
            else
            if(implicitTransaction == null)
                throw new IllegalStateException("cant set mutating status because no transaction underway");
        }

        private boolean getIsMutating()
        {
            if(currentTransaction == null)
                return false;
            else
                return currentTransaction.getIsMutating();
        }

        private boolean getIsMutating(String tblName)
        {
            if(currentTransaction == null)
                return false;
            else
                return currentTransaction.getIsMutating(tblName);
        }

        private DBindTransaction currentTransaction;
        private Connection implicitTransaction;






        private PerThreadManager()
        {
        }

    }


    public DBindTransactionManager()
    {
    }

    public void begin()
        throws NotSupportedException, SystemException
    {
        getPerThreadManager().begin();
    }

    public void commit()
        throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException
    {
        getPerThreadManager().commit();
    }

    public int getStatus()
        throws SystemException
    {
        return getPerThreadManager().getStatus();
    }

    public Transaction getTransaction()
        throws SystemException
    {
        return getPerThreadManager().getTransaction();
    }

    public void resume(Transaction transaction)
        throws InvalidTransactionException, IllegalStateException, SystemException
    {
        getPerThreadManager().resume(transaction);
    }

    public void rollback()
        throws IllegalStateException, SecurityException, SystemException
    {
        getPerThreadManager().rollback();
    }

    public void setRollbackOnly()
        throws IllegalStateException, SystemException
    {
        getPerThreadManager().setRollbackOnly();
    }

    public void setTransactionTimeout(int timeout)
        throws SystemException
    {
        getPerThreadManager().setTransactionTimeout(timeout);
    }

    public Transaction suspend()
        throws SystemException
    {
        return getPerThreadManager().suspend();
    }

    void setIsMutating()
    {
        PerThreadManager ptm = (PerThreadManager)perThreadManager.get();
        if(ptm == null)
        {
            throw new IllegalStateException("cant set mutating status because no active transaction");
        } else
        {
            ptm.setIsMutating();
            return;
        }
    }

    void setIsMutating(String tblName)
    {
        PerThreadManager ptm = (PerThreadManager)perThreadManager.get();
        if(ptm == null)
        {
            throw new IllegalStateException("cant set mutating status because no active transaction");
        } else
        {
            ptm.setIsMutating(tblName);
            return;
        }
    }

    boolean getIsMutating()
    {
        PerThreadManager ptm = (PerThreadManager)perThreadManager.get();
        if(ptm == null)
            return false;
        else
            return ptm.getIsMutating();
    }

    boolean getIsMutating(String tblName)
    {
        PerThreadManager ptm = (PerThreadManager)perThreadManager.get();
        if(ptm == null)
            return false;
        else
            return ptm.getIsMutating(tblName);
    }

    public Connection getConnection(DataSource dataSource)
        throws SQLException, RollbackException
    {
        return getPerThreadManager().getConnection(dataSource);
    }

    public void closeConnection(Connection conn)
    {
        getPerThreadManager().closeConnection(conn);
    }

    private PerThreadManager getPerThreadManager()
    {
        PerThreadManager ptm = (PerThreadManager)perThreadManager.get();
        if(ptm == null)
            perThreadManager.set(ptm = new PerThreadManager());
        return ptm;
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static final Logit m_log;
    private static final boolean DBG;
    private static ThreadLocal perThreadManager = new ThreadLocal();

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.dbind.DBindTransactionManager.class);
        DBG = m_log.ison();
    }


}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CachingDatabase.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            AbstractDatabase, SubclassDataStore, AdapterTable, CachingDataTable, 
//            CacheSizes, InvHeartbeats, InvHeartbeat, CDBBacking, 
//            AbstractDataStore, InvSQL, InvRouter, DataTable, 
//            CDTBacking

public class CachingDatabase extends AbstractDatabase
{
    private class InvListenerImpl extends InvRouter.InvListener
    {

        protected void onInvReceived(String tableName, String operation, String pkComponents)
        {
            try
            {
                onInvalidate(tableName, operation, pkComponents);
            }
            catch(Exception ex)
            {
                CachingDatabase.log.error("Exception receiving invalidation!", ex);
            }
        }

        private InvListenerImpl()
        {
        }

    }


    private CachingDatabase(SubclassDataStore dataStore)
        throws DbException
    {
        super(dataStore);
        cachingDataTables = new HashMap();
        id = DEFAULT_INITIAL_ID;
        separateTxRead = false;
        invListener = new InvListenerImpl();
        defaultRowCacheSize = CacheSizes.getDefaultRowCacheSize();
        defaultEnumCacheSize = CacheSizes.getDefaultEnumCacheSize();
        DataSource dataSource = super.getDataSource();
        try
        {
            heartbeat = InvHeartbeats.checkHeartbeat(dataSource);
        }
        catch(SQLException e)
        {
            throw new DbException("Caching database initialization failed, " + e);
        }
    }

    protected Table newTableInstance(String name, DataTable dataTable)
    {
        DataTable cachingDataTable = newCachingTableInstance(name, dataTable);
        synchronized(cachingDataTables)
        {
            cachingDataTables.put(name, cachingDataTable);
        }
        return super.newTableInstance(name, cachingDataTable);
    }

    public CachingDatabase(DataSource datasource)
        throws DbException
    {
        this(new SubclassDataStore(datasource));
    }

    public CachingDatabase(DataSource datasource, boolean separateTxRead)
        throws DbException
    {
        this(new SubclassDataStore(datasource));
        this.separateTxRead = separateTxRead;
    }

    public CachingDatabase(Database nonCachingDatabase)
        throws DbException
    {
        this(((AbstractDatabase)nonCachingDatabase).getSubclassDataStore());
    }

    public CachingDatabase(Database nonCachingDatabase, Long initialId)
        throws DbException
    {
        this(nonCachingDatabase);
        id = initialId;
    }

    public CachingDataTable getCachingDataTable(String cachingTableName)
    {
        Table t = getTable(cachingTableName);
        if(t == null)
            throw new IllegalStateException("table name '" + cachingTableName + "' not found.");
        if(!(t instanceof AdapterTable))
            throw new InternalError("not an AbstractTable: " + t);
        DataTable dt = ((AdapterTable)t).getDataTable();
        if(!(dt instanceof CachingDataTable))
            throw new InternalError("not a CachingDataTable: " + t);
        else
            return (CachingDataTable)dt;
    }

    private DataTable newCachingTableInstance(String name, DataTable dataTable)
    {
        int rowCacheSize = CacheSizes.getActualRowCacheSize(name, defaultRowCacheSize);
        int enumCacheSize = CacheSizes.getActualEnumCacheSize(name, defaultEnumCacheSize);
        return new CachingDataTable(this, dataTable, rowCacheSize, enumCacheSize, separateTxRead);
    }

    InvRouter.InvListener getInvListener()
    {
        return invListener;
    }

    void onInvalidate(String tableName, String op, String pk)
    {
        if(DBG)
            log.debug("invalidation: " + tableName + ", op=" + op + ", pk=" + pk);
        CachingDataTable table = (CachingDataTable)cachingDataTables.get(tableName);
        if(table != null)
            table.onInvalidate(op, pk);
    }

    public void setId(Long newId)
        throws DbException
    {
label0:
        {
            synchronized(this)
            {
                if((id != null || newId != null) && (id == null || !id.equals(newId)))
                    break label0;
            }
            return;
        }
        try
        {
            clearSubscription();
            id = newId;
            onIdChanged(id);
        }
        catch(SQLException sqe)
        {
            throw new DbException("can't set id: " + sqe);
        }
        cachingdatabase;
        JVM INSTR monitorexit ;
          goto _L1
        exception;
        throw exception;
_L1:
    }

    protected void onIdChanged(Long newId)
    {
        CDBBacking backing = heartbeat.getBacking(this, false);
        if(backing != null)
            backing.dropAll();
    }

    public Long getId()
    {
        return id;
    }

    void lockAccountNoWait()
        throws DbException
    {
        if(!lockPartition(false))
            throw new SelectException("resource busy, lock failed");
        else
            return;
    }

    public boolean lockPartition(boolean waitOnBusy)
        throws ConnectionException, SelectException
    {
        Exception e;
        if(id == null)
            throw new IllegalStateException("cannot lock null partition");
        assertActiveTransaction();
        try
        {
            checkSubscription();
        }
        // Misplaced declaration of an exception variable
        catch(Exception e)
        {
            throw new ConnectionException("can't check subscription: " + e);
        }
        java.sql.Connection con = getSubclassDataStore().getConnection();
        boolean flag = InvSQL.lockAccount(con, id.longValue(), waitOnBusy);
        getSubclassDataStore().closeConnection(con);
        return flag;
        Exception exception;
        exception;
        getSubclassDataStore().closeConnection(con);
        throw exception;
        con;
        throw new ConnectionException("lock failed: " + con);
    }

    private void assertActiveTransaction()
        throws IllegalStateException
    {
        if(!isTransactionActive())
            throw new IllegalStateException("no active transaction.");
        else
            return;
    }

    void checkSubscription()
        throws SQLException, DbException
    {
        synchronized(this)
        {
            invListener.setId(id);
            setSubscriptionId(id);
        }
    }

    private void clearSubscription()
        throws SQLException, DbException
    {
        synchronized(this)
        {
            setSubscriptionId(null);
            invListener.setId(null);
        }
    }

    private void setSubscriptionId(Long sid)
        throws SQLException
    {
        if(subscriptionId == null && sid == null)
            return;
        if(subscriptionId != null && subscriptionId.equals(sid))
            return;
        if(subscriptionId != null)
        {
            heartbeat.removeSubscription(subscriptionId);
            subscriptionId = null;
        }
        if(sid != null)
        {
            heartbeat.addSubscription(sid);
            subscriptionId = sid;
        }
    }

    public CDTBacking getBacking(CachingDataTable cachingDataTable, boolean create)
    {
        CDBBacking backing = heartbeat.getBacking(this, create);
        if(backing != null)
            return backing.getBacking(cachingDataTable, create);
        else
            return null;
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static final Logit log;
    private static final boolean DBG;
    public static final Long DEFAULT_INITIAL_ID = new Long(0L);
    private final int defaultRowCacheSize;
    private final int defaultEnumCacheSize;
    private final Map cachingDataTables;
    private Long id;
    private Long subscriptionId;
    private boolean separateTxRead;
    final InvHeartbeat heartbeat;
    InvListenerImpl invListener;

    static 
    {
        log = Logit.getInstance(com.fitechlabs.dbind.impl.CachingDatabase.class);
        DBG = log.ison();
    }

}

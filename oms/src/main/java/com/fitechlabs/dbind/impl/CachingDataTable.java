// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CachingDataTable.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.sql.SQLException;
import java.util.*;
import javax.transaction.*;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            WrapperDataTable, SubclassDataTable, DataTable, CachingDatabase, 
//            CacheStatistics, CDTBacking, InvRouter, LRUCache, 
//            AbstractDatabase

public class CachingDataTable extends WrapperDataTable
{
    private static class Key
    {

        public int hashCode()
        {
            if(z == 0)
                z = t + i + j + h(a) + h(b) + h(c) + h(d);
            return z;
        }

        public boolean equals(Object o)
        {
            if(o == null || o.getClass() != (CachingDataTable.class$com$fitechlabs$dbind$impl$CachingDataTable$Key != null ? CachingDataTable.class$com$fitechlabs$dbind$impl$CachingDataTable$Key : (CachingDataTable.class$com$fitechlabs$dbind$impl$CachingDataTable$Key = CachingDataTable._mthclass$("com.fitechlabs.dbind.impl.CachingDataTable$Key"))))
            {
                return false;
            } else
            {
                Key k = (Key)o;
                return t == k.t && i == k.i && j == k.j && e(a, k.a) && e(b, k.b) && e(c, k.c) && e(d, k.d);
            }
        }

        private static int h(Object o)
        {
            return o != null ? o.hashCode() : 0;
        }

        private static int h(Object a[])
        {
            if(a == null)
                return 0;
            int s = 0;
            int i = 0;
            for(int n = a.length; i < n; i++)
                s += h(a[i]);

            return s;
        }

        private static boolean e(Object a, Object b)
        {
            if(a == null)
                return b == null;
            else
                return a.equals(b);
        }

        private static boolean e(Object a[], Object b[])
        {
            if(a == null)
                return b == null;
            if(b == null)
                return false;
            if(a.length != b.length)
                return false;
            for(int i = 0; i < a.length; i++)
                if(!e(a[i], b[i]))
                    return false;

            return true;
        }

        public String toString()
        {
            return "K(" + t + "," + a + "," + b + "," + c + "," + i + "," + j + "," + s(d) + ")";
        }

        private static String s(Object o[])
        {
            if(o == null)
                return "null";
            StringBuffer sb = new StringBuffer();
            String comma = "[";
            for(int i = 0; i < o.length; i++)
            {
                sb.append(comma);
                sb.append(String.valueOf(o[i]));
                comma = ",";
            }

            sb.append("]");
            return sb.toString();
        }

        private int t;
        private int i;
        private int j;
        private Object a;
        private Object b;
        private Object c;
        private Object d[];
        private int z;

        private Key(int t, Object a, Object b, Object c, Object d[], int i, int j)
        {
            z = 0;
            this.t = t;
            this.a = a;
            this.b = b;
            this.c = c;
            this.i = i;
            this.j = j;
            if(d != null)
            {
                this.d = new Object[d.length];
                System.arraycopy(((Object) (d)), 0, ((Object) (this.d)), 0, d.length);
            }
        }

    }

    private abstract class Reader
    {

        protected abstract Object plainRead()
            throws ConnectionException, SelectException;

        protected Object lockRead()
            throws DbException
        {
            cachingDatabase.lockAccountNoWait();
            return plainRead();
        }

        private Object txRead()
            throws DbException
        {
            cachingDatabase.beginTransaction();
            Object obj = lockRead();
            cachingDatabase.commitTransaction();
            return obj;
            Exception exception;
            exception;
            cachingDatabase.commitTransaction();
            throw exception;
        }

        private Object cleanRead()
            throws DbException
        {
            if(!separateTxRead)
                return txRead();
            TransactionManager tm;
            javax.transaction.Transaction tx;
            tm = DBindTransactions.getTransactionManager();
            tx = tm.suspend();
            Object obj = txRead();
            tm.resume(tx);
            return obj;
            Exception exception;
            exception;
            tm.resume(tx);
            throw exception;
            SystemException se;
            se;
            throw new DbException("transaction manager failure: " + se);
            InvalidTransactionException ite;
            ite;
            throw new DbException("couldn't resume transaction: " + ite);
        }


        private Reader()
        {
        }

    }


    public void setReadonlyMode(boolean readonly)
    {
        this.readonly = readonly;
    }

    public CachingDataTable(CachingDatabase cachingDatabase, DataTable dataTable, int rowCacheSize, int enumCacheSize, boolean separateTxRead)
    {
        super(dataTable);
        notifications = null;
        this.separateTxRead = false;
        readonly = false;
        dependentTables = new HashSet();
        this.cachingDatabase = cachingDatabase;
        sourceName = dataTable.getName();
        this.rowCacheSize = rowCacheSize;
        this.enumCacheSize = enumCacheSize;
        this.separateTxRead = separateTxRead;
        if(dataTable instanceof SubclassDataTable)
        {
            SubclassDataTable subclassDataTable = (SubclassDataTable)dataTable;
            DataTable superTable = subclassDataTable.getSuperTable();
            if(superTable != null)
            {
                String superTableName = superTable.getName();
                CachingDataTable superCachingDataTable = cachingDatabase.getCachingDataTable(superTableName);
                superCachingDataTable.addNotification(this);
            }
        }
    }

    private void addNotification(CachingDataTable table)
    {
        if(notifications == null)
            synchronized(this)
            {
                if(notifications == null)
                    notifications = new ArrayList();
            }
        notifications.add(table);
    }

    private void addDependency(CachingDataTable table)
    {
        if(cachingDatabase != table.cachingDatabase)
            throw new IllegalStateException("CachingDataTable should be in same CachingDatabase as its dependents");
        if(this == table)
            return;
        synchronized(dependentTables)
        {
            dependentTables.add(table);
        }
    }

    public void onInvalidate(String op, String pk)
    {
        if(DBG)
            log.debug("inv received on " + getName() + "(" + cachingDatabase.getId() + ") " + op + ", " + pk);
        boolean stats = CacheStatistics.isCollecting();
        long start = stats ? getInvalidationSenderBeginTimestamp() : 0L;
        int pkDrops = 0;
        int enumDrops = 0;
        Object removed = null;
        CDTBacking backing = cachingDatabase.getBacking(this, false);
        if(backing != null)
            backing.onInvalidate(op, pk, true);
        if(notifications != null)
            synchronized(this)
            {
                CachingDataTable cdt;
                for(Iterator it = notifications.iterator(); it.hasNext(); cdt.onInvalidate(op, pk))
                    cdt = (CachingDataTable)it.next();

            }
        synchronized(dependentTables)
        {
            CachingDataTable cdt;
            for(Iterator it = dependentTables.iterator(); it.hasNext(); cdt.onInvalidateDependent(op, pk))
                cdt = (CachingDataTable)it.next();

        }
        if(stats)
        {
            long invLapsedTimeMilliSecs = System.currentTimeMillis() - start;
            if(invLapsedTimeMilliSecs >= 0L);
            CacheStatistics.addInv(super.source.getName(), op, invLapsedTimeMilliSecs, removed == null ? 0 : 1, pkDrops, enumDrops);
        }
    }

    private void onInvalidateDependent(String op, String pk)
    {
        if(DBG)
            log.debug("inv received on " + getName() + "(" + cachingDatabase.getId() + ") " + op + ", " + pk);
        boolean stats = CacheStatistics.isCollecting();
        long start = stats ? getInvalidationSenderBeginTimestamp() : 0L;
        int pkDrops = 0;
        int enumDrops = 0;
        Object removed = null;
        CDTBacking backing = cachingDatabase.getBacking(this, false);
        if(backing != null)
            backing.onInvalidate(op, pk, false);
        if(stats)
        {
            long invLapsedTimeMilliSecs = System.currentTimeMillis() - start;
            if(invLapsedTimeMilliSecs >= 0L);
            CacheStatistics.addInv(super.source.getName(), "dependent " + op, invLapsedTimeMilliSecs, removed == null ? 0 : 1, pkDrops, enumDrops);
        }
    }

    private long getInvalidationSenderBeginTimestamp()
    {
        String beginTimestampStr;
        beginTimestampStr = (String)InvRouter.dbServerSideInvBeginTimestampData.get();
        if(beginTimestampStr == null)
            return System.currentTimeMillis();
        return Long.parseLong(beginTimestampStr);
        NumberFormatException nfe;
        nfe;
        log.warn("Invalidation begin timestamp received from InvalidationSender contains invalid number, hence will use default system timestamp for inv stats. Timestamp String:" + beginTimestampStr);
        return System.currentTimeMillis();
    }

    public Row select(final PrimaryKey primaryKey)
        throws ConnectionException, SelectException, FindException
    {
        Object key = primaryKey.toString();
        Reader reader = new Reader() {

            protected Object lockRead()
                throws ConnectionException, SelectException, DbException
            {
                Object val = source.select(primaryKey, null, "for update nowait", null);
                if(val != null)
                    return val;
                else
                    return super.lockRead();
            }

            protected Object plainRead()
                throws ConnectionException, SelectException
            {
                return source.select(primaryKey);
            }

        }
;
        CDTBacking backing = cachingDatabase.getBacking(this, true);
        LRUCache rowCache = backing.getRowCache();
        Object o = cachedRead("row", "find-by-pk", rowCache, key, null, reader);
        return (Row)o;
    }

    public Row select(final PrimaryKey primaryKey, final String conditions)
        throws ConnectionException, SelectException, FindException
    {
        Object key = primaryKey.toString();
        Reader reader = new Reader() {

            protected Object lockRead()
                throws ConnectionException, SelectException, DbException
            {
                Object val = source.select(primaryKey, null, "for update nowait", null);
                if(val != null)
                    return val;
                else
                    return super.lockRead();
            }

            protected Object plainRead()
                throws ConnectionException, SelectException
            {
                return source.select(primaryKey, null, conditions, null);
            }

        }
;
        LRUCache rowCache = null;
        if(conditions == null)
        {
            CDTBacking backing = cachingDatabase.getBacking(this, true);
            rowCache = backing.getRowCache();
        }
        Object o = cachedRead("row", "find-by-pk-cond", rowCache, key, conditions, reader);
        return (Row)o;
    }

    public Row select(final PrimaryKey primaryKey, final String where, final String conditions, final Object bindVars[])
        throws ConnectionException, SelectException, FindException
    {
        Object key = new Key(1, primaryKey, where, conditions, bindVars, 0, 0);
        Reader reader = new Reader() {

            protected Object lockRead()
                throws ConnectionException, SelectException, DbException
            {
                Object val = source.select(primaryKey, where, "for update nowait", bindVars);
                if(val != null)
                    return val;
                else
                    return super.lockRead();
            }

            protected Object plainRead()
                throws ConnectionException, SelectException
            {
                return source.select(primaryKey, where, conditions, bindVars);
            }

        }
;
        LRUCache enumCache = null;
        if(conditions == null)
        {
            CDTBacking backing = cachingDatabase.getBacking(this, true);
            enumCache = backing.getEnumCache();
        }
        Object o = cachedRead("enum", "find-by-pk-where", enumCache, key, conditions, reader);
        return (Row)o;
    }

    public ListSubset selectAll(final String where, final String orderBy, final String conditions, final Object bindVariables[], final int fromIndex, final int toIndex)
        throws ConnectionException, SelectException
    {
        Object key = new Key(2, where, orderBy, conditions, bindVariables, fromIndex, toIndex);
        Reader reader = new Reader() {

            protected Object plainRead()
                throws ConnectionException, SelectException
            {
                return source.selectAll(where, orderBy, conditions, bindVariables, fromIndex, toIndex);
            }

        }
;
        LRUCache enumCache = null;
        if(conditions == null)
        {
            CDTBacking backing = cachingDatabase.getBacking(this, true);
            enumCache = backing.getEnumCache();
        }
        Object o = cachedRead("enum", "select-paginated", enumCache, key, conditions, reader);
        return (ListSubset)o;
    }

    public ListSubset selectAll(String where, String orderBy, String conditions, Object bindVariables[], int fromIndex, int toIndex, RowType dependentTables[])
        throws ConnectionException, SelectException
    {
        for(int i = 0; i < dependentTables.length; i++)
            cachingDatabase.getCachingDataTable(dependentTables[i].getTableName()).addDependency(this);

        return selectAll(where, orderBy, conditions, bindVariables, fromIndex, toIndex);
    }

    public List selectAll(final String where, final String orderBy, final String conditions, final Object bindVariables[])
        throws ConnectionException, SelectException
    {
        Object key = new Key(3, where, orderBy, conditions, bindVariables, 0, 0);
        Reader reader = new Reader() {

            protected Object plainRead()
                throws ConnectionException, SelectException
            {
                return source.selectAll(where, orderBy, conditions, bindVariables);
            }

        }
;
        LRUCache enumCache = null;
        if(conditions == null)
        {
            CDTBacking backing = cachingDatabase.getBacking(this, true);
            enumCache = backing.getEnumCache();
        }
        Object o = cachedRead("enum", "select-all", enumCache, key, conditions, reader);
        return (List)o;
    }

    public List selectAll(String where, String orderBy, String conditions, Object bindVariables[], RowType dependentTables[])
        throws ConnectionException, SelectException
    {
        if(dependentTables != null)
        {
            for(int i = 0; i < dependentTables.length; i++)
                cachingDatabase.getCachingDataTable(dependentTables[i].getTableName()).addDependency(this);

        }
        return selectAll(where, orderBy, conditions, bindVariables);
    }

    public int selectCount(final String where, final Object bindVariables[])
        throws ConnectionException, SelectException
    {
        Object key = new Key(4, where, null, null, bindVariables, 0, 0);
        Reader reader = new Reader() {

            protected Object plainRead()
                throws ConnectionException, SelectException
            {
                int count = source.selectCount(where, bindVariables);
                return new Integer(count);
            }

        }
;
        CDTBacking backing = cachingDatabase.getBacking(this, true);
        LRUCache enumCache = backing.getEnumCache();
        Object o = cachedRead("enum", "select-count", enumCache, key, null, reader);
        return ((Integer)o).intValue();
    }

    public List selectPks(final String where, final String orderBy, final String conditions, final Object bindVariables[])
        throws ConnectionException, SelectException
    {
        Object key = new Key(5, where, orderBy, conditions, bindVariables, 0, 0);
        Reader reader = new Reader() {

            protected Object plainRead()
                throws ConnectionException, SelectException
            {
                return source.selectPks(where, orderBy, conditions, bindVariables);
            }

        }
;
        LRUCache enumCache = null;
        if(conditions == null)
        {
            CDTBacking backing = cachingDatabase.getBacking(this, true);
            enumCache = backing.getEnumCache();
        }
        return (List)cachedRead("enum", "select-pks", enumCache, key, conditions, reader);
    }

    private Object cachedRead(String cacheName, String subType, LRUCache cache, Object key, String conditions, Reader reader)
        throws ConnectionException, SelectException
    {
        boolean stats;
        long start;
        SoftReference holder[];
        Object item;
        if(DBG)
            log.debug("checking " + sourceName + "." + cacheName + "." + subType + " with " + key);
        stats = CacheStatistics.isCollecting();
        start = stats ? System.currentTimeMillis() : 0L;
        if(conditions != null)
        {
            Object item = reader.plainRead();
            if(stats)
                CacheStatistics.addMiss(sourceName, cacheName, subType, System.currentTimeMillis() - start, "has conditions");
            return item;
        }
        holder = (SoftReference[])cache.get(key);
        if(holder == null)
            holder = (SoftReference[])cache.getOrPut(key, new SoftReference[1]);
        Reference ref = holder[0];
        item = ref == null ? null : ref.get();
        if(readonly)
        {
            if(item != null)
            {
                if(DBG)
                    log.debug("item found: " + item);
                if(stats)
                    CacheStatistics.addHit(sourceName, cacheName, subType, System.currentTimeMillis() - start, null);
                return item != NULL_RESULT ? item : null;
            }
            if(DBG)
                log.debug("item not found, continuing.");
            item = reader.plainRead();
            if(!DBindTransactions.getIsMutating(sourceName))
            {
                holder[0] = new SoftReference(item == null ? NULL_RESULT : (item = makeImmutable(item)));
                if(DBG)
                    log.debug("database not mutating, item saved in cache in preload mode.");
                if(stats)
                {
                    CacheStatistics.addMiss(sourceName, cacheName, subType, System.currentTimeMillis() - start, "(preload mode)");
                    CacheStatistics.addSizeChange(sourceName, cacheName, cache.getSize(), cache.getCapacity());
                }
            } else
            {
                if(DBG)
                    log.debug("database mutating, item not saved in cache in preload mode.");
                if(stats)
                {
                    CacheStatistics.addMiss(sourceName, cacheName, subType, System.currentTimeMillis() - start, "mutating (preload mode)");
                    CacheStatistics.addSizeChange(sourceName, cacheName, cache.getSize(), cache.getCapacity());
                }
            }
            return item;
        }
        if(item != null)
        {
            if(DBG)
                log.debug("item found: " + item);
            if(!cache.isValid())
            {
                item = reader.plainRead();
                if(DBG)
                    log.debug("synch-loss after hit on " + key);
                if(stats)
                    CacheStatistics.addMiss(sourceName, cacheName, subType, System.currentTimeMillis() - start, "synch-loss after hit");
                return item;
            }
            if(stats)
                CacheStatistics.addHit(sourceName, cacheName, subType, System.currentTimeMillis() - start, null);
            if(DBG)
                log.debug("cache validated, returning found item.");
            return item != NULL_RESULT ? item : null;
        }
        if(DBG)
            log.debug("item not found, continuing.");
        SoftReference asoftreference[] = holder;
        JVM INSTR monitorenter ;
        Reference ref = holder[0];
        item = ref == null ? null : ref.get();
        if(item != null) goto _L2; else goto _L1
_L1:
        if(!DBindTransactions.getIsMutating(sourceName))
        {
            try
            {
                cachingDatabase.checkSubscription();
            }
            catch(SQLException sqe)
            {
                throw new ConnectionException("subscription failure", sqe.getMessage());
            }
            item = reader.cleanRead();
            if(DBG)
                log.debug("clean read succeeded, item: " + item);
            holder[0] = new SoftReference(item == null ? NULL_RESULT : (item = makeImmutable(item)));
            if(DBG)
                log.debug("database not mutating, item saved in cache.");
            if(stats)
            {
                CacheStatistics.addMiss(sourceName, cacheName, subType, System.currentTimeMillis() - start, null);
                CacheStatistics.addSizeChange(sourceName, cacheName, cache.getSize(), cache.getCapacity());
            }
        } else
        {
            item = reader.plainRead();
            if(DBG)
                log.debug("database mutating, item not saved in cache.");
            if(stats)
            {
                CacheStatistics.addMiss(sourceName, cacheName, subType, System.currentTimeMillis() - start, "mutating");
                CacheStatistics.addSizeChange(sourceName, cacheName, cache.getSize(), cache.getCapacity());
            }
        }
          goto _L3
_L2:
        if(DBG)
            log.debug("double-check found " + item);
        if(cache.isValid()) goto _L5; else goto _L4
_L4:
        item = reader.plainRead();
        if(DBG)
            log.debug("synch-loss after double-check hit on " + key);
        if(stats)
            CacheStatistics.addMiss(sourceName, cacheName, subType, System.currentTimeMillis() - start, "synch-loss after double-check");
        return item;
_L5:
        if(stats)
            CacheStatistics.addHit(sourceName, cacheName, subType, System.currentTimeMillis() - start, "on double check");
        if(DBG)
            log.debug("double-check validated, returing item.");
        item != NULL_RESULT ? item : null;
        asoftreference;
        JVM INSTR monitorexit ;
        return;
_L3:
        asoftreference;
        JVM INSTR monitorexit ;
        break MISSING_BLOCK_LABEL_1128;
        Exception exception;
        exception;
        throw exception;
        DbException de;
        de;
        if(DBG)
            log.debug("db exception " + de + ", continuing to plain read");
        item = reader.plainRead();
        if(stats)
            CacheStatistics.addMiss(sourceName, cacheName, subType, System.currentTimeMillis() - start, "locked");
        if(DBG)
            log.debug("final plain read succeeded, returning item " + item);
        return item;
    }

    private Object makeImmutable(Object item)
    {
        if(item instanceof Params)
        {
            ((Params)item).makeImmutable();
            return item;
        }
        if(item instanceof List)
        {
            List l = (List)item;
            for(Iterator it = l.iterator(); it.hasNext();)
            {
                Object o = it.next();
                if(o instanceof Params)
                    ((Params)o).makeImmutable();
                else
                    log.warn("Unexpected Data Type in the List for cache:" + o.getClass() + ". It may be an unnoticed xTrade bug, please report to your vendor.", new Throwable("Unexpected data Type when expecting:" + (com.fitechlabs.dbind.Params.class)));
            }

            if(l instanceof ArrayListSubset)
                return l;
            else
                return Collections.unmodifiableList(l);
        }
        if(item instanceof Integer)
        {
            return item;
        } else
        {
            log.warn("Unexpected Data Type for cache:" + item.getClass() + ". It may be an unnoticed xTrade bug, please report to your vendor.", new Throwable("Unexpected data Type when expecting:" + (java.util.List.class) + "," + (com.fitechlabs.dbind.Params.class) + "," + (java.lang.Integer.class)));
            return item;
        }
    }

    private static final Logit log;
    private static final boolean DBG;
    private final CachingDatabase cachingDatabase;
    private final String sourceName;
    private static final int KEY_TYPE_PK_WHERE = 1;
    private static final int KEY_TYPE_PAGINATED = 2;
    private static final int KEY_TYPE_SELECT_ALL = 3;
    private static final int KEY_TYPE_SELECT_COUNT = 4;
    private static final int KEY_TYPE_SELECT_PKS = 5;
    private static final Object NULL_RESULT = "NULL_RESULT_OBJ";
    final int rowCacheSize;
    final int enumCacheSize;
    private List notifications;
    private boolean separateTxRead;
    private boolean readonly;
    private Set dependentTables;
    static Class class$com$fitechlabs$dbind$impl$CachingDataTable$Key; /* synthetic field */

    static 
    {
        log = Logit.getInstance(com.fitechlabs.dbind.impl.CachingDataTable.class);
        DBG = log.ison();
    }


}

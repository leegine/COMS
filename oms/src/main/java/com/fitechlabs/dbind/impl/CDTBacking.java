// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CDTBacking.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.xtrade.kernel.util.log.Logit;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            CachingDataTable, LRUCache, IHBBacking

public class CDTBacking
{
    private class ValidatingLRUCache extends LRUCache
    {

        public boolean isValid()
        {
            return ihbBacking.isValid();
        }

        public String toString()
        {
            return "VLRU(" + getSize() + ")";
        }

        public ValidatingLRUCache(int maxcount)
        {
            super(maxcount);
        }
    }


    public CDTBacking(IHBBacking ihbBacking, CachingDataTable table)
    {
        rowCache = null;
        enumCache = null;
        this.ihbBacking = ihbBacking;
        this.table = table;
    }

    LRUCache getRowCache()
    {
        if(DBG)
            log.debug("getRowCache row=" + rowCache + ", enum=" + enumCache);
        LRUCache c = rowCache;
        if(c != null)
            return c;
        CDTBacking cdtbacking = this;
        JVM INSTR monitorenter ;
        if(rowCache == null)
            rowCache = new ValidatingLRUCache(table.rowCacheSize);
        return rowCache;
        Exception exception;
        exception;
        throw exception;
    }

    LRUCache getEnumCache()
    {
        if(DBG)
            log.debug("getEnumCach row=" + rowCache + ", enum=" + enumCache);
        LRUCache c = enumCache;
        if(c != null)
            return c;
        CDTBacking cdtbacking = this;
        JVM INSTR monitorenter ;
        if(enumCache == null)
            enumCache = new ValidatingLRUCache(table.enumCacheSize);
        return enumCache;
        Exception exception;
        exception;
        throw exception;
    }

    void onInvalidate(String op, String pk, boolean invalidatePk)
    {
        if(DBG)
            log.debug("inv-before: row=" + rowCache + ", enum=" + enumCache);
        synchronized(this)
        {
            if(invalidatePk && rowCache != null)
                rowCache.remove(pk);
            if(enumCache != null)
                enumCache.clear();
        }
        if(DBG)
            log.debug("inv-after:  row=" + rowCache + ", enum=" + enumCache);
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
    private final CachingDataTable table;
    private final IHBBacking ihbBacking;
    private LRUCache rowCache;
    private LRUCache enumCache;

    static 
    {
        log = Logit.getInstance(com.fitechlabs.dbind.impl.CDTBacking.class);
        DBG = log.ison();
    }

}

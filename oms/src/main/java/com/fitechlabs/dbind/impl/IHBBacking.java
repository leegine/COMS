// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IHBBacking.java

package com.fitechlabs.dbind.impl;

import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            CDBBacking, CachingDatabase

public class IHBBacking
{

    public IHBBacking(long expiration)
    {
        dbBackings = new HashMap();
        this.expiration = expiration;
        valid = true;
    }

    boolean isValid()
    {
        return valid && System.currentTimeMillis() < expiration;
    }

    void setInvalid()
    {
        valid = false;
    }

    void setExpiration(long expiration)
    {
        this.expiration = expiration;
    }

    long getExpiration()
    {
        return expiration;
    }

    CDBBacking getBacking(CachingDatabase database, boolean create)
    {
        CDBBacking b;
        synchronized(dbBackings)
        {
            b = (CDBBacking)dbBackings.get(database);
            if(b == null && create)
            {
                b = new CDBBacking(this);
                dbBackings.put(database, b);
            }
        }
        return b;
    }

    private long expiration;
    private boolean valid;
    private Map dbBackings;
}

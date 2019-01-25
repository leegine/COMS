// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CDBBacking.java

package com.fitechlabs.dbind.impl;

import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            CDTBacking, IHBBacking, CachingDataTable

public class CDBBacking
{

    public CDBBacking(IHBBacking ihbBacking)
    {
        this.ihbBacking = ihbBacking;
    }

    CDTBacking getBacking(CachingDataTable table, boolean create)
    {
        CDTBacking b;
        synchronized(tableBackings)
        {
            b = (CDTBacking)tableBackings.get(table);
            if(b == null && create)
            {
                b = new CDTBacking(ihbBacking, table);
                tableBackings.put(table, b);
            }
        }
        return b;
    }

    public void dropAll()
    {
        synchronized(tableBackings)
        {
            tableBackings.clear();
        }
    }

    private final IHBBacking ihbBacking;
    private final Map tableBackings = new HashMap();
}

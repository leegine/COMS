// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Params.java

package com.fitechlabs.dbind;

import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.fitechlabs.dbind:
//            Row, RowType, PrimaryKey

public abstract class Params
    implements Row
{

    public Params()
    {
        mutable_flg = true;
    }

    public abstract void setColumn(String s, Object obj);

    public Map toInsertMap()
    {
        return new HashMap();
    }

    public Map toUpdateMap()
    {
        return new HashMap();
    }

    /**
     * @deprecated Method toMap is deprecated
     */

    public Map toMap()
    {
        return toInsertMap();
    }

    public void markAllValuesAsSet()
    {
    }

    protected void assertValidForInsert()
        throws IllegalArgumentException
    {
    }

    public boolean mutable()
    {
        return mutable_flg;
    }

    public void makeImmutable()
    {
        mutable_flg = false;
    }

    public abstract RowType getRowType();

    public abstract Object getColumn(String s);

    public abstract PrimaryKey getPrimaryKey();

    private boolean mutable_flg;
}

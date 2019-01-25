// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RowidPrimaryKey.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

public class RowidPrimaryKey
    implements PrimaryKey
{

    public RowidPrimaryKey()
    {
    }

    public RowType getRowType()
    {
        return rowType;
    }

    public void setRowType(RowType rowType)
    {
        this.rowType = rowType;
    }

    public static RowidPrimaryKey fromString(String pkValueString)
        throws NumberFormatException
    {
        RowidPrimaryKey pk = new RowidPrimaryKey();
        pk.rowid = pkValueString;
        return pk;
    }

    public String toString()
    {
        return rowid;
    }

    public boolean equals(Object other)
    {
        if(other == null || !(other instanceof RowidPrimaryKey))
            return false;
        RowidPrimaryKey o = (RowidPrimaryKey)other;
        if(rowType == null)
            return o.rowType == null;
        return rowType.equals(o.rowType);
    }

    public int hashCode()
    {
        return rowid == null ? 0 : rowid.hashCode();
    }

    public String rowid;
    private RowType rowType;
}

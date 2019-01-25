// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SegmentUrlRow.java

package com.fitechlabs.xtrade.kernel.data.db;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;

public interface SegmentUrlRow
    extends Row
{

    public abstract long getSegmentId();

    public abstract boolean getSegmentIdIsSet();

    public abstract String getDataSourceUrl();

    public abstract boolean getDataSourceUrlIsSet();

    public static final RowType TYPE = new RowType("segment_url", "config");

}

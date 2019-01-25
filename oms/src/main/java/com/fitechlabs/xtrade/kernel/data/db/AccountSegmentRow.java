// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AccountSegmentRow.java

package com.fitechlabs.xtrade.kernel.data.db;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;

public interface AccountSegmentRow
    extends Row
{

    public abstract long getAccountId();

    public abstract boolean getAccountIdIsSet();

    public abstract long getSegmentId();

    public abstract boolean getSegmentIdIsSet();

    public static final RowType TYPE = new RowType("account_segment", "config");

}

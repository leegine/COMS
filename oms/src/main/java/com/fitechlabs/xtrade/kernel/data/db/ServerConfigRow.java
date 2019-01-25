// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServerConfigRow.java

package com.fitechlabs.xtrade.kernel.data.db;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;

public interface ServerConfigRow
    extends Row
{

    public abstract String getConfigTitle();

    public abstract boolean getConfigTitleIsSet();

    public abstract String getConfigCateg();

    public abstract boolean getConfigCategIsSet();

    public abstract String getConfigName();

    public abstract boolean getConfigNameIsSet();

    public abstract String getConfigValue();

    public static final RowType TYPE = new RowType("server_config", "config");

}

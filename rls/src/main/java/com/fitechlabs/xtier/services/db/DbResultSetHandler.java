// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fitechlabs.xtier.services.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DbResultSetHandler
{

    public abstract void handle(ResultSet resultset)
        throws SQLException;
}
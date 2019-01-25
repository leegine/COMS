// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ResultFiller.java

package com.fitechlabs.dbind.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface ResultFiller
{

    public abstract Object fromResultSet(ResultSet resultset)
        throws SQLException;

    public abstract Object fromMap(Map map);
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TypedDatabase.java

package com.fitechlabs.dbind;

import com.fitechlabs.dbind.impl.NonCachingDatabase;
import javax.sql.DataSource;

public class TypedDatabase extends NonCachingDatabase
{

    public TypedDatabase(DataSource dataSource)
    {
        super(dataSource);
    }
}

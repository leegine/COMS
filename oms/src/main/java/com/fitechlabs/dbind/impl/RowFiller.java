// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RowFiller.java

package com.fitechlabs.dbind.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            FieldFiller, ResultFiller

public class RowFiller
    implements ResultFiller
{

    RowFiller(Class objClass, Field fields[])
    {
        try
        {
            constructor = objClass.getConstructor(null);
        }
        catch(NoSuchMethodException e)
        {
            throw new IllegalArgumentException("no default constructor found for " + objClass);
        }
        fillers = new FieldFiller[fields.length];
        for(int i = 0; i < fields.length; i++)
            fillers[i] = FieldFiller.forField(fields[i], i + 1);

    }

    public Object fromResultSet(ResultSet rs)
        throws SQLException
    {
        Object obj;
        try
        {
            obj = constructor.newInstance(null);
        }
        catch(Exception e)
        {
            throw new RuntimeException("failed to construct instance; using " + constructor + ": " + e);
        }
        for(int i = 0; i < fillers.length; i++)
            fillers[i].fill(obj, rs);

        return obj;
    }

    public Object fromMap(Map map)
    {
        Object obj;
        try
        {
            obj = constructor.newInstance(null);
        }
        catch(Exception e)
        {
            throw new RuntimeException("failed to construct instance; using " + constructor + ": " + e);
        }
        for(int i = 0; i < fillers.length; i++)
            fillers[i].fill(obj, map);

        return obj;
    }

    private Constructor constructor;
    private FieldFiller fillers[];
}

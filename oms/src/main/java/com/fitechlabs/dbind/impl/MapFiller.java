// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MapFiller.java

package com.fitechlabs.dbind.impl;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            FieldFiller, ResultFiller

public class MapFiller
    implements ResultFiller
{

    protected MapFiller(Field fields[])
    {
        fillers = new FieldFiller[fields.length];
        for(int i = 0; i < fields.length; i++)
            fillers[i] = FieldFiller.forField(fields[i], i + 1);

    }

    public Object fromResultSet(ResultSet rs)
        throws SQLException
    {
        Map map = new HashMap();
        for(int i = 0; i < fillers.length; i++)
            fillers[i].fill(map, rs);

        return map;
    }

    public Object fromMap(Map initialValues)
    {
        throw new UnsupportedOperationException("map filling from maps not supported");
    }

    private FieldFiller fillers[];
}

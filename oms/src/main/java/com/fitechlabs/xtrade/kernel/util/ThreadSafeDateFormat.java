// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ThreadSafeDateFormat.java

package com.fitechlabs.xtrade.kernel.util;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class ThreadSafeDateFormat
{

    private ThreadSafeDateFormat()
    {
    }

    public static SimpleDateFormat getSimpleDateFormat(String pattern)
    {
        Map map = (Map)m_dateFormattersTable.get();
        SimpleDateFormat val = (SimpleDateFormat)map.get(pattern);
        if(val == null)
        {
            val = new SimpleDateFormat(pattern);
            map.put(pattern, val);
        }
        return val;
    }

    private static final ThreadLocal m_dateFormattersTable = new ThreadLocal() {

        protected Object initialValue()
        {
            return new HashMap();
        }

    }
;

}

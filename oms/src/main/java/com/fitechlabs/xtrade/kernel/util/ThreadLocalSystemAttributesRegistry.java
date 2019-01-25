// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ThreadLocalSystemAttributesRegistry.java

package com.fitechlabs.xtrade.kernel.util;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalSystemAttributesRegistry
{

    public ThreadLocalSystemAttributesRegistry()
    {
    }

    private static Map getContextForThisThread()
    {
        return (Map)context.get();
    }

    public static Object getAttribute(String name)
    {
        return getContextForThisThread().get(name);
    }

    public static void setAttribute(String name, Object value)
    {
        if(value == null)
            getContextForThisThread().remove(name);
        else
            getContextForThisThread().put(name, value);
    }

    public static void clearAttributes()
    {
        getContextForThisThread().clear();
    }

    private static ThreadLocal context = new ThreadLocal() {

        protected Object initialValue()
        {
            return new HashMap();
        }

    }
;

}

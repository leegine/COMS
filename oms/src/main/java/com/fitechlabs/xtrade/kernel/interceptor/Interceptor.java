// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Interceptor.java

package com.fitechlabs.xtrade.kernel.interceptor;

import java.lang.reflect.Method;

public interface Interceptor
{

    public abstract Object onCall(Method method, Object aobj[]);

    public abstract void onReturn(Object obj, Object obj1);

    public abstract void onThrowable(Object obj, Throwable throwable);
}

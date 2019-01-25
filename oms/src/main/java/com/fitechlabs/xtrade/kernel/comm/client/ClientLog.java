// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ClientLog.java

package com.fitechlabs.xtrade.kernel.comm.client;


public interface ClientLog
{

    public abstract boolean ison();

    public abstract void debug(String s);

    public abstract void debug(String s, Throwable throwable);

    public abstract void info(String s);

    public abstract void info(String s, Throwable throwable);

    public abstract void warn(String s);

    public abstract void warn(String s, Throwable throwable);
}

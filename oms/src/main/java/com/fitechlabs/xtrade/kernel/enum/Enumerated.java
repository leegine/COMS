// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enumerated.java

package com.fitechlabs.xtrade.kernel.enum;

import java.io.Serializable;

public abstract class Enumerated
    implements Serializable
{

    public Enumerated(int i, String s)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("String value cannot be null");
        } else
        {
            this.i = i;
            this.s = s;
            return;
        }
    }

    public int intValue()
    {
        return i;
    }

    public String stringValue()
    {
        return s;
    }

    public String toString()
    {
        return String.valueOf(i) + ":" + s;
    }

    public boolean equals(Object o)
    {
        if(o == null || !getClass().isAssignableFrom(o.getClass()))
        {
            return false;
        } else
        {
            Enumerated e = (Enumerated)o;
            return i == e.i && s.equals(e.s);
        }
    }

    public int hashCode()
    {
        return i + s.hashCode();
    }

    private final int i;
    private final String s;
}

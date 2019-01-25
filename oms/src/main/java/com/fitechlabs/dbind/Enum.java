// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enum.java

package com.fitechlabs.dbind;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @deprecated Class Enum is deprecated
 */

public abstract class Enum
    implements Serializable
{
    /**
     * @deprecated Class SparseEnumList is deprecated
     */

    protected static class SparseEnumList
    {

        /**
         * @deprecated Method add is deprecated
         */

        public Enum add(int i, Enum e)
        {
            return (Enum)map.put(new Integer(i), e);
        }

        /**
         * @deprecated Method get is deprecated
         */

        public Enum get(int i)
        {
            return (Enum)map.get(new Integer(i));
        }

        private Map map;

        /**
         * @deprecated Method SparseEnumList is deprecated
         */

        public SparseEnumList()
        {
            map = new HashMap();
        }

        /**
         * @deprecated Method SparseEnumList is deprecated
         */

        public SparseEnumList(int size)
        {
            map = new HashMap(size);
        }
    }


    /**
     * @deprecated Method Enum is deprecated
     */

    protected Enum(int intValue, String stringValue)
    {
        if(stringValue == null || stringValue.length() == 0)
        {
            throw new IllegalArgumentException("String value cannot be null");
        } else
        {
            i = intValue;
            s = stringValue;
            return;
        }
    }

    /**
     * @deprecated Method intValue is deprecated
     */

    public int intValue()
    {
        return i;
    }

    /**
     * @deprecated Method stringValue is deprecated
     */

    public String stringValue()
    {
        return s;
    }

    /**
     * @deprecated Method toString is deprecated
     */

    public String toString()
    {
        String n = getClass().getName();
        n = n.substring(n.lastIndexOf(".") + 1);
        return n + "." + s + "=" + i;
    }

    /**
     * @deprecated Method valueOf is deprecated
     */

    public static Enum valueOf(int intValue)
    {
        throw new UnsupportedOperationException("Enum.valueOf(int) is a subclass responsibility");
    }

    /**
     * @deprecated Method valueOf is deprecated
     */

    public static Enum valueOf(String stringValue)
    {
        throw new UnsupportedOperationException("Enum.valueOf(String) is a subclass responsibility");
    }

    /**
     * @deprecated Method equals is deprecated
     */

    public boolean equals(Object o)
    {
        if(o == null || o.getClass() != getClass())
        {
            return false;
        } else
        {
            Enum e = (Enum)o;
            return e.i == i && e.s.equals(s);
        }
    }

    /**
     * @deprecated Method init is deprecated
     */

    protected void init(SparseEnumList numericIndex, Map hashedIndex)
    {
        Enum oldEnum = numericIndex.add(i, this);
        if(oldEnum != null)
            throw new IllegalArgumentException("Int value value '" + i + "' is already taken by '" + oldEnum.toString() + "!");
        oldEnum = (Enum)hashedIndex.put(s, this);
        if(oldEnum != null)
            throw new IllegalArgumentException("String value '" + s + "' is already taken by '" + oldEnum.toString() + "!");
        else
            return;
    }

    protected final int i;
    protected final String s;
}

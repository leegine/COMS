// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ObjectPrettyPrinter.java

package com.fitechlabs.xtrade.kernel.util;

import com.fitechlabs.dbind.Enum;
import com.fitechlabs.xtrade.kernel.enum.Enumerated;
import java.lang.reflect.*;
import java.util.*;

public final class ObjectPrettyPrinter
{

    private ObjectPrettyPrinter()
    {
    }

    public static String toString(Object o)
    {
        StringBuffer b = new StringBuffer();
        firsts.set(new HashSet());
        unwrap(b, o);
        return b.toString();
    }

    private static boolean isFirst(Object o)
    {
        Set s = (Set)firsts.get();
        boolean r = !s.contains(o);
        s.add(o);
        return r;
    }

    private static void unwrap(StringBuffer b, Object o)
    {
        if(o == null)
            b.append("null");
        else
        if(basicTypes.contains(o.getClass()))
            b.append(String.valueOf(o));
        else
        if(o instanceof String)
        {
            b.append('"');
            b.append(toEscapedString((String)o));
            b.append('"');
        } else
        if(o instanceof Enum)
            b.append(o.toString());
        else
        if(o instanceof Enumerated)
            b.append(o.toString());
        else
        if(!isFirst(o))
        {
            b.append("<mult-ref-on-");
            b.append(nameStub(o.getClass()));
            b.append(">");
        } else
        if(o.getClass().isArray())
            expand(b, o.getClass().getComponentType(), o, "[", "]", null);
        else
        if(o instanceof Collection)
            expand(b, o.getClass(), ((Object) (((Collection)o).toArray())), "{", "}", null);
        else
        if(o instanceof Map)
            expand(b, o.getClass(), ((Object) (((Map)o).entrySet().toArray())), "{", "}", null);
        else
        if(o instanceof java.util.Map.Entry)
        {
            Object oo[] = {
                ((java.util.Map.Entry)o).getKey(), ((java.util.Map.Entry)o).getValue()
            };
            expand(b, o.getClass(), ((Object) (oo)), "{", "}", null);
        } else
        {
            expand(b, o.getClass(), o.getClass().getFields(), "(", ")", o);
        }
    }

    private static void expand(StringBuffer b, Class c, Object array, String lft, String rit, Object o)
    {
        String lastIndent;
        if(c != (java.util.HashMap.class))
            b.append(nameStub(c));
        if(array == null || Array.getLength(array) == 0)
        {
            b.append(lft);
            b.append(rit);
            break MISSING_BLOCK_LABEL_395;
        }
        b.append(lft);
        lastIndent = getIndent();
        addIndent();
        Class eleType = array.getClass().getComponentType();
        for(int i = 0; i < Array.getLength(array); i++)
        {
            Object ai = Array.get(array, i);
            if(i > 0 && o == null)
                b.append(",");
            if(o != null)
            {
                Field f = (Field)ai;
                if(Modifier.isStatic(f.getModifiers()))
                    continue;
                b.append(getIndent());
                if(!f.getName().equals("data") || !nameStub(o.getClass()).equals("DataBagImpl"))
                {
                    b.append(f.getName());
                    b.append("=");
                }
                try
                {
                    unwrap(b, f.get(o));
                }
                catch(Exception e)
                {
                    b.append("<!" + e + "!>");
                }
                continue;
            }
            if(ai instanceof java.util.Map.Entry)
            {
                try
                {
                    java.util.Map.Entry entry = (java.util.Map.Entry)ai;
                    b.append(getIndent());
                    unwrap(b, entry.getKey());
                    b.append("=");
                    unwrap(b, entry.getValue());
                }
                catch(Exception e)
                {
                    b.append("<!" + e + "!>");
                }
            } else
            {
                unwrap(b, ai);
                lastIndent = "";
            }
        }

        removeIndent();
        b.append(lastIndent);
        b.append(rit);
        break MISSING_BLOCK_LABEL_395;
        Exception exception;
        exception;
        removeIndent();
        b.append(lastIndent);
        b.append(rit);
        throw exception;
    }

    private static String nameStub(Class c)
    {
        String s = c.getName();
        return s.substring(s.lastIndexOf('.') + 1);
    }

    private static String getIndent()
    {
        String i = (String)indent.get();
        return i == null ? "\n" : i;
    }

    private static void addIndent()
    {
        indent.set(getIndent() + "   ");
    }

    private static void removeIndent()
    {
        String i = getIndent();
        int n = i.length();
        indent.set(n <= 3 ? null : ((Object) (i.substring(0, n - 3))));
    }

    public static String toEscapedString(String s)
    {
        if(s == null)
            return "null";
        int n = s.length();
        char c[] = new char[n];
        s.getChars(0, n, c, 0);
        if(!requiresEscape(c))
            return s;
        StringBuffer b = new StringBuffer(n * 6);
        for(int i = 0; i < n; i++)
        {
            char ci = c[i];
            int u = ci;
            if(u >= 256)
            {
                int j;
                for(j = i; j < n && c[j] >= '\u0100'; j++)
                    b.append(c[j]);

                b.append("(");
                for(; i < j; i++)
                {
                    u = c[i];
                    b.append("\\u");
                    b.append(toHex(u >> 12));
                    b.append(toHex(u >> 8));
                    b.append(toHex(u >> 4));
                    b.append(toHex(u >> 0));
                }

                b.append(")");
                i--;
                continue;
            }
            if(u < 32 || u >= 127)
                switch(u)
                {
                case 13: // '\r'
                    b.append("\\r");
                    break;

                case 10: // '\n'
                    b.append("\\n");
                    break;

                default:
                    b.append("\\0");
                    b.append(toHex(u >> 4));
                    b.append(toHex(u >> 0));
                    break;
                }
            else
                b.append(ci);
        }

        return b.toString();
    }

    private static boolean requiresEscape(char c[])
    {
        for(int i = c.length; --i >= 0;)
            if(c[i] >= '\177' || c[i] < ' ')
                return true;

        return false;
    }

    private static char toHex(int u)
    {
        int i = u & 0xf;
        char h = i >= 10 ? (char)(65 + (i - 10)) : (char)(48 + i);
        return h;
    }

    private static ThreadLocal indent = new ThreadLocal();
    private static ThreadLocal firsts = new ThreadLocal();
    private static Set basicTypes;

    static 
    {
        basicTypes = new HashSet();
        basicTypes.add(Character.TYPE);
        basicTypes.add(Byte.TYPE);
        basicTypes.add(Short.TYPE);
        basicTypes.add(Integer.TYPE);
        basicTypes.add(Long.TYPE);
        basicTypes.add(Float.TYPE);
        basicTypes.add(Double.TYPE);
        basicTypes.add(Boolean.TYPE);
        basicTypes.add(java.lang.Character.class);
        basicTypes.add(java.lang.Byte.class);
        basicTypes.add(java.lang.Short.class);
        basicTypes.add(java.lang.Integer.class);
        basicTypes.add(java.lang.Long.class);
        basicTypes.add(java.lang.Float.class);
        basicTypes.add(java.lang.Double.class);
        basicTypes.add(java.lang.Boolean.class);
        basicTypes.add(java.util.Date.class);
        basicTypes.add(java.sql.Date.class);
        basicTypes.add(java.sql.Time.class);
        basicTypes.add(java.sql.Timestamp.class);
    }
}

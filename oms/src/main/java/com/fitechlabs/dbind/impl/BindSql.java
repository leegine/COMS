// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BindSql.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.dbind.Enum;
import com.fitechlabs.xtrade.kernel.enum.Enumerated;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

public class BindSql
{
    private static interface Converter
    {

        public abstract Object convert(Object obj);
    }


    public BindSql(String sql, Object bindVars[])
    {
        this.sql = sql;
        this.bindVars = toSqlValues(bindVars);
    }

    public BindSql(String sql, List bindVars)
    {
        this.sql = sql;
        this.bindVars = toSqlValues(bindVars);
    }

    public String getSql()
    {
        return sql;
    }

    public Object[] getBindVarialbes()
    {
        return bindVars;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("BindSql(sql=");
        sb.append(sql);
        sb.append(",");
        if(bindVars != null)
        {
            for(int i = 0; i < bindVars.length; i++)
            {
                if(i != 0)
                    sb.append(",");
                sb.append("a");
                sb.append(String.valueOf(i));
                sb.append("=");
                sb.append(String.valueOf(bindVars[i]));
            }

        }
        sb.append(")");
        return sb.toString();
    }

    private Object[] toSqlValues(Object bv[])
    {
        if(bv == null || bv.length == 0)
            return null;
        Object a[] = new Object[bv.length];
        for(int i = 0; i < bv.length; i++)
            a[i] = toSqlValue(bv[i]);

        return a;
    }

    private Object[] toSqlValues(List bv)
    {
        if(bv == null || bv.size() == 0)
            return null;
        Object a[] = new Object[bv.size()];
        Iterator it = bv.iterator();
        for(int i = 0; i < a.length; i++)
            a[i] = toSqlValue(it.next());

        return a;
    }

    private Object toSqlValue(Object value)
    {
        if(value == null)
            return null;
        Class c = value.getClass();
        Converter cnvr = (Converter)converters.get(c);
        if(cnvr == null)
        {
            if(value instanceof Enumerated)
                cnvr = ENUMERATED_CONVERTER;
            else
            if(value instanceof Enum)
                cnvr = ENUM_CONVERTER;
            else
            if(value instanceof Date)
                cnvr = UTIL_DATE_CONVERTER;
            else
                throw new IllegalArgumentException("value type not supported: '" + value.getClass() + "'");
            converters.put(c, cnvr);
        }
        return cnvr.convert(value);
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private String sql;
    private Object bindVars[];
    private static final Converter NULL_CONVERTER;
    private static final Converter ENUM_CONVERTER = new Converter() {

        public Object convert(Object o)
        {
            Enum e = (Enum)o;
            return new Integer(e.intValue());
        }

    }
;
    private static final Converter ENUMERATED_CONVERTER = new Converter() {

        public Object convert(Object o)
        {
            Enumerated e = (Enumerated)o;
            return new Integer(e.intValue());
        }

    }
;
    private static final Converter UTIL_DATE_CONVERTER;
    private static final Converter BIG_INTEGER_CONVERTER;
    private static Map converters;

    static 
    {
        NULL_CONVERTER = new Converter() {

            public Object convert(Object o)
            {
                return o;
            }

        }
;
        UTIL_DATE_CONVERTER = new Converter() {

            public Object convert(Object o)
            {
                Date d = (Date)o;
                return new Timestamp(d.getTime());
            }

        }
;
        BIG_INTEGER_CONVERTER = new Converter() {

            public Object convert(Object o)
            {
                BigInteger i = (BigInteger)o;
                return new BigDecimal(i);
            }

        }
;
        converters = Collections.synchronizedMap(new HashMap());
        converters.put(Boolean.TYPE, NULL_CONVERTER);
        converters.put(java.lang.Boolean.class, NULL_CONVERTER);
        converters.put(Character.TYPE, NULL_CONVERTER);
        converters.put(java.lang.Character.class, NULL_CONVERTER);
        converters.put(Byte.TYPE, NULL_CONVERTER);
        converters.put(java.lang.Byte.class, NULL_CONVERTER);
        converters.put(byte[].class, NULL_CONVERTER);
        converters.put(Integer.TYPE, NULL_CONVERTER);
        converters.put(java.lang.Integer.class, NULL_CONVERTER);
        converters.put(Long.TYPE, NULL_CONVERTER);
        converters.put(java.lang.Long.class, NULL_CONVERTER);
        converters.put(Float.TYPE, NULL_CONVERTER);
        converters.put(java.lang.Float.class, NULL_CONVERTER);
        converters.put(Double.TYPE, NULL_CONVERTER);
        converters.put(java.lang.Double.class, NULL_CONVERTER);
        converters.put(java.lang.String.class, NULL_CONVERTER);
        converters.put(java.sql.Date.class, NULL_CONVERTER);
        converters.put(java.sql.Time.class, NULL_CONVERTER);
        converters.put(java.sql.Timestamp.class, NULL_CONVERTER);
        converters.put(java.util.Date.class, UTIL_DATE_CONVERTER);
        converters.put(java.math.BigInteger.class, BIG_INTEGER_CONVERTER);
        converters.put(java.math.BigDecimal.class, NULL_CONVERTER);
    }
}

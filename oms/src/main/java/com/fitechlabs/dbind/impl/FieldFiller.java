// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FieldFiller.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.xtrade.kernel.enum.EnumeratedManager;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public abstract class FieldFiller
{
    private static class EnumeratedFieldGetter
        implements FieldGetter
    {

        public Object get(ResultSet rs, int index)
            throws SQLException
        {
            int value = rs.getInt(index);
            if(rs.wasNull())
                return null;
            else
                return EnumeratedManager.getInstance().valueFromInt(c, value);
        }

        private Class c;

        private EnumeratedFieldGetter(Class c)
        {
            this.c = c;
        }

    }

    private static class EnumFieldGetter
        implements FieldGetter
    {

        public Object get(ResultSet rs, int index)
            throws SQLException
        {
            int value = rs.getInt(index);
            if(rs.wasNull())
                return null;
            Object enum;
            try
            {
                Object args[] = {
                    new Integer(value)
                };
                enum = m.invoke(c, args);
            }
            catch(Exception e)
            {
                throw new RuntimeException("Enum lookup failed for value " + value + ", " + c);
            }
            return enum;
        }

        private Class c;
        private Method m;

        private EnumFieldGetter(Class c)
        {
            this.c = c;
            try
            {
                m = c.getMethod("valueOf", new Class[] {
                    Integer.TYPE
                });
            }
            catch(NoSuchMethodException e)
            {
                throw new InternalError("Enum class method 'valueOf' not found: " + e);
            }
        }

    }

    private static class ReflectionFieldFiller extends FieldFiller
    {

        public void fill(Object obj, ResultSet rs)
            throws SQLException
        {
            Object value = getter.get(rs, index);
            try
            {
                field.set(obj, value);
            }
            catch(IllegalAccessException e)
            {
                throw new RuntimeException("failed to set " + field + " to value " + value);
            }
        }

        public void fill(Object obj, Map map)
        {
            Object value = map.get(field.getName());
            try
            {
                field.set(obj, value);
            }
            catch(IllegalAccessException e)
            {
                throw new RuntimeException("failed to set " + field + " to value " + value);
            }
        }

        public void fill(Map map, ResultSet rs)
            throws SQLException
        {
            throw new UnsupportedOperationException("reflection-based map filling not supported.");
        }

        private FieldGetter getter;
        private int index;
        private Field field;

        private ReflectionFieldFiller(FieldGetter getter, int index, Field field)
        {
            this.getter = getter;
            this.index = index;
            this.field = field;
        }

    }

    private static class ParamsFieldFiller extends FieldFiller
    {

        public void fill(Object obj, ResultSet rs)
            throws SQLException
        {
            Object value = getter.get(rs, index);
            ((Params)obj).setColumn(name, value);
        }

        public void fill(Object obj, Map map)
        {
            Object value = map.get(name);
            ((Params)obj).setColumn(name, value);
        }

        public void fill(Map map, ResultSet rs)
            throws SQLException
        {
            Object value = getter.get(rs, index);
            map.put(name, value);
        }

        private FieldGetter getter;
        private int index;
        private String name;

        private ParamsFieldFiller(FieldGetter getter, int index, String name)
        {
            this.getter = getter;
            this.index = index;
            this.name = name;
        }

    }

    private static interface FieldGetter
    {

        public abstract Object get(ResultSet resultset, int i)
            throws SQLException;
    }


    public FieldFiller()
    {
    }

    abstract void fill(Object obj, ResultSet resultset)
        throws SQLException;

    abstract void fill(Object obj, Map map);

    abstract void fill(Map map, ResultSet resultset)
        throws SQLException;

    static FieldFiller forField(Field field, int index)
    {
        Class c = field.getType();
        String name = field.getName();
        FieldGetter getter = (FieldGetter)getters.get(c);
        if(getter == null)
            if((com.fitechlabs.xtrade.kernel.enum.Enumerated.class).isAssignableFrom(c))
                getter = new EnumeratedFieldGetter(c);
            else
            if((com.fitechlabs.dbind.Enum.class).isAssignableFrom(c))
                getter = new EnumFieldGetter(c);
            else
            if((java.util.Date.class).isAssignableFrom(c))
                getter = DATE_GETTER;
            else
            if((java.lang.Number.class).isAssignableFrom(c))
                getter = BIG_DECIMAL_GETTER;
            else
                throw new IllegalArgumentException("field type not supported: '" + c.getName() + "'");
        Class d = field.getDeclaringClass();
        if((com.fitechlabs.dbind.Params.class).isAssignableFrom(d))
            return new ParamsFieldFiller(getter, index, name);
        else
            return new ReflectionFieldFiller(getter, index, field);
    }

    private static Map getters;
    private static final FieldGetter BOOL_GETTER;
    private static final FieldGetter CHAR_GETTER;
    private static final FieldGetter BYTE_GETTER;
    private static final FieldGetter BYTE_ARRAY_GETTER;
    private static final FieldGetter INT_GETTER;
    private static final FieldGetter LONG_GETTER;
    private static final FieldGetter FLOAT_GETTER;
    private static final FieldGetter DOUBLE_GETTER;
    private static final FieldGetter STRING_GETTER;
    private static final FieldGetter DATE_GETTER;
    private static final FieldGetter TIME_GETTER;
    private static final FieldGetter TIMESTAMP_GETTER;
    private static final FieldGetter BIG_INTEGER_GETTER;
    private static final FieldGetter BIG_DECIMAL_GETTER;

    static 
    {
        getters = new HashMap();
        BOOL_GETTER = new FieldGetter() {

            public Object get(ResultSet rs, int index)
                throws SQLException
            {
                int value = rs.getInt(index);
                if(rs.wasNull())
                    return null;
                else
                    return value == 0 ? Boolean.FALSE : Boolean.TRUE;
            }

        }
;
        CHAR_GETTER = new FieldGetter() {

            public Object get(ResultSet rs, int index)
                throws SQLException
            {
                String value = rs.getString(index);
                if(rs.wasNull() || value == null || value.length() < 1)
                {
                    return null;
                } else
                {
                    char c = value.charAt(0);
                    return new Character(c);
                }
            }

        }
;
        BYTE_GETTER = new FieldGetter() {

            public Object get(ResultSet rs, int index)
                throws SQLException
            {
                byte value = rs.getByte(index);
                if(rs.wasNull())
                    return null;
                else
                    return new Integer(value);
            }

        }
;
        BYTE_ARRAY_GETTER = new FieldGetter() {

            public Object get(ResultSet rs, int index)
                throws SQLException
            {
                byte value[] = rs.getBytes(index);
                if(rs.wasNull())
                    return null;
                if(value.length == 0)
                    return null;
                else
                    return value;
            }

        }
;
        INT_GETTER = new FieldGetter() {

            public Object get(ResultSet rs, int index)
                throws SQLException
            {
                int value = rs.getInt(index);
                if(rs.wasNull())
                    return null;
                else
                    return new Integer(value);
            }

        }
;
        LONG_GETTER = new FieldGetter() {

            public Object get(ResultSet rs, int index)
                throws SQLException
            {
                long value = rs.getLong(index);
                if(rs.wasNull())
                    return null;
                else
                    return new Long(value);
            }

        }
;
        FLOAT_GETTER = new FieldGetter() {

            public Object get(ResultSet rs, int index)
                throws SQLException
            {
                float value = rs.getFloat(index);
                if(rs.wasNull())
                    return null;
                else
                    return new Float(value);
            }

        }
;
        DOUBLE_GETTER = new FieldGetter() {

            public Object get(ResultSet rs, int index)
                throws SQLException
            {
                double value = rs.getDouble(index);
                if(rs.wasNull())
                    return null;
                else
                    return new Double(value);
            }

        }
;
        STRING_GETTER = new FieldGetter() {

            public Object get(ResultSet rs, int index)
                throws SQLException
            {
                String value = rs.getString(index);
                if(rs.wasNull())
                    return null;
                else
                    return value;
            }

        }
;
        DATE_GETTER = new FieldGetter() {

            public Object get(ResultSet rs, int index)
                throws SQLException
            {
                java.sql.Date value = rs.getDate(index);
                if(rs.wasNull())
                    return null;
                else
                    return value;
            }

        }
;
        TIME_GETTER = new FieldGetter() {

            public Object get(ResultSet rs, int index)
                throws SQLException
            {
                java.sql.Time value = rs.getTime(index);
                if(rs.wasNull())
                    return null;
                else
                    return value;
            }

        }
;
        TIMESTAMP_GETTER = new FieldGetter() {

            public Object get(ResultSet rs, int index)
                throws SQLException
            {
                java.sql.Timestamp value = rs.getTimestamp(index);
                if(rs.wasNull())
                    return null;
                else
                    return value;
            }

        }
;
        BIG_INTEGER_GETTER = new FieldGetter() {

            public Object get(ResultSet rs, int index)
                throws SQLException
            {
                BigDecimal value = rs.getBigDecimal(index);
                if(rs.wasNull())
                    return null;
                else
                    return value.toBigInteger();
            }

        }
;
        BIG_DECIMAL_GETTER = new FieldGetter() {

            public Object get(ResultSet rs, int index)
                throws SQLException
            {
                BigDecimal value = rs.getBigDecimal(index);
                if(rs.wasNull())
                    return null;
                else
                    return value;
            }

        }
;
        getters.put(Boolean.TYPE, BOOL_GETTER);
        getters.put(java.lang.Boolean.class, BOOL_GETTER);
        getters.put(Character.TYPE, CHAR_GETTER);
        getters.put(java.lang.Character.class, CHAR_GETTER);
        getters.put(Byte.TYPE, BYTE_GETTER);
        getters.put(java.lang.Byte.class, BYTE_GETTER);
        getters.put(byte[].class, BYTE_ARRAY_GETTER);
        getters.put(Integer.TYPE, INT_GETTER);
        getters.put(java.lang.Integer.class, INT_GETTER);
        getters.put(Long.TYPE, LONG_GETTER);
        getters.put(java.lang.Long.class, LONG_GETTER);
        getters.put(Float.TYPE, FLOAT_GETTER);
        getters.put(java.lang.Float.class, FLOAT_GETTER);
        getters.put(Double.TYPE, DOUBLE_GETTER);
        getters.put(java.lang.Double.class, DOUBLE_GETTER);
        getters.put(java.lang.String.class, STRING_GETTER);
        getters.put(java.sql.Date.class, DATE_GETTER);
        getters.put(java.sql.Time.class, TIME_GETTER);
        getters.put(java.sql.Timestamp.class, TIMESTAMP_GETTER);
        getters.put(java.util.Date.class, TIMESTAMP_GETTER);
        getters.put(java.math.BigInteger.class, BIG_INTEGER_GETTER);
        getters.put(java.math.BigDecimal.class, BIG_DECIMAL_GETTER);
        getters.put(java.lang.Number.class, BIG_DECIMAL_GETTER);
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataAccessObject.java

package com.fitechlabs.xtrade.kernel.data;

import com.fitechlabs.dbind.Row;
import java.util.*;

public abstract class DataAccessObject
{
    public static interface Factory
    {

        public abstract DataAccessObject newInstance(Row row1);
    }


    public static void registerClass(Class rowClass, Factory factory)
    {
        if(registrations.containsKey(rowClass))
        {
            throw new IllegalStateException("Already registered: " + rowClass);
        } else
        {
            registrations.put(rowClass, factory);
            factories.clear();
            return;
        }
    }

    protected DataAccessObject(Row row)
    {
        this.row = row;
    }

    public Row getRow()
    {
        return row;
    }

    public static DataAccessObject forRow(Row row)
        throws IllegalArgumentException
    {
        if(row == null)
            return null;
        Factory factory = findFactory(row.getClass());
        if(factory == null)
            throw new IllegalArgumentException("DataAccessObject association not found for row " + row.getClass());
        else
            return factory.newInstance(row);
    }

    public static List forRows(List rows)
    {
        List list = new ArrayList(rows.size());
        DataAccessObject actor;
        for(Iterator it = rows.iterator(); it.hasNext(); list.add(actor))
        {
            Row row = (Row)it.next();
            actor = forRow(row);
        }

        return list;
    }

    private static Factory findFactory(Class rowClass)
    {
        Factory holder[] = (Factory[])factories.get(rowClass);
        if(holder == null)
        {
            holder = new Factory[1];
            holder[0] = resolveFactory(rowClass);
            factories.put(rowClass, holder);
        }
        return holder[0];
    }

    private static Factory resolveFactory(Class aClass)
    {
        if(registrations.containsKey(aClass))
            return (Factory)registrations.get(aClass);
        Class superClass = aClass.getSuperclass();
        if(superClass == null)
            return null;
        if(registrations.containsKey(superClass))
            return findFactory(superClass);
        Class ifaces[] = aClass.getInterfaces();
        for(int i = 0; i < ifaces.length; i++)
            if(registrations.containsKey(ifaces[i]))
                return (Factory)registrations.get(ifaces[i]);

        Factory f = findFactory(superClass);
        if(f != null)
            return f;
        for(int i = 0; i < ifaces.length; i++)
        {
            f = findFactory(ifaces[i]);
            if(f != null)
                return f;
        }

        return null;
    }

    private static Map factories = new HashMap();
    private static Map registrations = new HashMap();
    private Row row;

}

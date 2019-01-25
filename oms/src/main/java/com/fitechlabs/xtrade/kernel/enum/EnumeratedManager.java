// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EnumeratedManager.java

package com.fitechlabs.xtrade.kernel.enum;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

// Referenced classes of package com.fitechlabs.xtrade.kernel.enum:
//            Enumerated

public class EnumeratedManager
{
    private class Additions
    {

        private void assertNoConflicts(Helper topmost)
        {
            assertKeySetsDisjoint(stringLookupAdditions, topmost.stringLookup, topmost.enumClass);
            assertKeySetsDisjoint(intLookupAdditions, topmost.intLookup, topmost.enumClass);
        }

        private void assertKeySetsDisjoint(Map lookupAdditions, Map topmostLookups, Class topmostClass)
        {
            for(Iterator it = lookupAdditions.keySet().iterator(); it.hasNext();)
            {
                Object key = it.next();
                if(topmostLookups.containsKey(key))
                    throw new IllegalArgumentException("Value '" + lookupAdditions.get(key) + "' from " + enumClass.getName() + " conflicts with '" + topmostLookups.get(key) + "' from " + topmostClass.getName());
            }

        }

        private void addTo(Helper helper)
        {
            addAll(helper.intLookup, intLookupAdditions);
            addAll(helper.stringLookup, stringLookupAdditions);
        }

        private void addAll(Map dest, Map src)
        {
            Object key;
            Object value;
            for(Iterator it = src.entrySet().iterator(); it.hasNext(); dest.put(key, value))
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
                key = entry.getKey();
                value = entry.getValue();
                if(dest.containsKey(entry.getKey()))
                    throw new InternalError("disjoint check failed");
            }

        }

        private Map intLookupAdditions;
        private Map stringLookupAdditions;
        private Class enumClass;



        private Additions(Class ec)
        {
            intLookupAdditions = new HashMap();
            stringLookupAdditions = new HashMap();
            enumClass = ec;
            Field fields[] = ec.getFields();
            for(int i = 0; i < fields.length; i++)
            {
                Field f = fields[i];
                Class c = f.getDeclaringClass();
                if(c != ec)
                    continue;
                int m = f.getModifiers();
                if(!Modifier.isPublic(m) || !Modifier.isStatic(m) || !Modifier.isFinal(m))
                    throw new IllegalArgumentException("Field " + f.getName() + " must be marked 'public static final' because it is of type " + ec);
                Object fieldValue;
                try
                {
                    fieldValue = f.get(ec);
                }
                catch(IllegalAccessException e)
                {
                    throw new IllegalArgumentException("Field lookup failed for Enumerated " + ec + ", field " + f.getName() + ": " + e);
                }
                Enumerated enumValue = (Enumerated)fieldValue;
                int intValue = enumValue.intValue();
                Integer integerValue = new Integer(intValue);
                String stringValue = enumValue.stringValue();
                EnumeratedManager.debug("found value " + integerValue + ", " + stringValue + " = " + enumValue + " to class " + ec.getName());
                Enumerated ev = (Enumerated)stringLookupAdditions.get(stringValue);
                if(ev != null)
                    throw new IllegalArgumentException("Value '" + ev + "' from " + ev.getClass().getName() + " conflicts with '" + enumValue + "' from " + ec.getName());
                ev = (Enumerated)intLookupAdditions.get(integerValue);
                if(ev != null)
                    throw new IllegalArgumentException("Value '" + ev + "' from " + ev.getClass().getName() + " conflicts with '" + enumValue + "' from " + ec.getName());
                stringLookupAdditions.put(stringValue, enumValue);
                intLookupAdditions.put(integerValue, enumValue);
            }

        }

    }

    private class Helper
    {

        private Enumerated fromString(String value)
            throws IllegalArgumentException
        {
            Object o = stringLookup.get(value);
            if(o == null)
                throw new IllegalArgumentException("String value '" + value + "' not found in Enumerated " + enumClass);
            else
                return (Enumerated)o;
        }

        private Enumerated fromInt(int value)
            throws IllegalArgumentException
        {
            Object o = intLookup.get(new Integer(value));
            if(o == null)
                throw new IllegalArgumentException("int value '" + value + "' not found in Enumerated " + enumClass);
            else
                return (Enumerated)o;
        }

        private Map stringLookup;
        private Map intLookup;
        private Class enumClass;






        private Helper(Class ec)
            throws IllegalArgumentException, IllegalStateException
        {
            stringLookup = new HashMap();
            intLookup = new HashMap();
            enumClass = ec;
            Additions additions = new Additions(ec);
            List allhelpers = new ArrayList();
            Helper topmost = this;
            allhelpers.add(this);
            for(Class c = ec.getSuperclass(); c != null && c != (EnumeratedManager.class$com$fitechlabs$xtrade$kernel$enum$Enumerated != null ? EnumeratedManager.class$com$fitechlabs$xtrade$kernel$enum$Enumerated : (EnumeratedManager.class$com$fitechlabs$xtrade$kernel$enum$Enumerated = EnumeratedManager._mthclass$("com.fitechlabs.xtrade.kernel.enum.Enumerated"))); c = c.getSuperclass())
            {
                Helper h = getOrCreateHelper(c);
                allhelpers.add(h);
                topmost = h;
            }

            additions.assertNoConflicts(topmost);
            Helper helper;
            for(Iterator it = allhelpers.iterator(); it.hasNext(); additions.addTo(helper))
                helper = (Helper)it.next();

        }

    }


    private static void out(String s)
    {
        System.out.println(s);
    }

    private static void warn(String s)
    {
        out("WARN  " + s);
    }

    private static void info(String s)
    {
        out("INFO  " + s);
    }

    private static void debug(String s1)
    {
    }

    public static EnumeratedManager getInstance()
    {
        if(instance == null)
            synchronized(com.fitechlabs.xtrade.kernel.enum.EnumeratedManager.class)
            {
                if(instance == null)
                    instance = new EnumeratedManager();
            }
        return instance;
    }

    public EnumeratedManager()
    {
        helpers = new HashMap();
    }

    public void addEnumeratedClass(Class enumeratedClass)
        throws IllegalArgumentException, IllegalStateException
    {
        if(helpers.containsKey(enumeratedClass))
            return;
        if(!(com.fitechlabs.xtrade.kernel.enum.Enumerated.class).isAssignableFrom(enumeratedClass))
        {
            throw new IllegalArgumentException("not an Enumerated subclass: " + enumeratedClass);
        } else
        {
            getOrCreateHelper(enumeratedClass);
            return;
        }
    }

    public Enumerated valueFromString(Class enumeratedClass, String value)
    {
        Helper helper = (Helper)helpers.get(enumeratedClass);
        if(helper == null)
            throw new IllegalArgumentException("Unknwon " + enumeratedClass);
        else
            return helper.fromString(value);
    }

    public Enumerated valueFromInt(Class enumeratedClass, int value)
    {
        Helper helper = (Helper)helpers.get(enumeratedClass);
        if(helper == null)
            throw new IllegalArgumentException("Unknown " + enumeratedClass);
        else
            return helper.fromInt(value);
    }

    private synchronized Helper getOrCreateHelper(Class enumeratedClass)
    {
        Object o = helpers.get(enumeratedClass);
        if(o != null)
        {
            return (Helper)o;
        } else
        {
            Helper h = new Helper(enumeratedClass);
            helpers.put(enumeratedClass, h);
            return h;
        }
    }

    private Map helpers;
    private static EnumeratedManager instance = null;



}

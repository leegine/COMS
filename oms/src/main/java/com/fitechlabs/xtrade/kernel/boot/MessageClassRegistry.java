// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MessageClassRegistry.java

package com.fitechlabs.xtrade.kernel.boot;

import java.lang.reflect.Field;
import java.util.HashMap;

public class MessageClassRegistry
{

    public MessageClassRegistry()
    {
    }

    public static void registerBaseClass(Class msgClass)
        throws Exception
    {
        String tagname;
        try
        {
            tagname = (String)msgClass.getField("TAGNAME").get(null);
            register(tagname, null, msgClass, false);
        }
        catch(NoSuchFieldException nsfe)
        {
            throw new Exception("Registered base classes must have a public static String field 'TAGNAME', " + msgClass + " does not.");
        }
        catch(ClassCastException cce)
        {
            throw new Exception("Registered base classes must have a public static String field 'TAGNAME', " + msgClass + "'s is of the wrong type.");
        }
        try
        {
            String ptype = (String)msgClass.getField("PTYPE").get(null);
            register(tagname, ptype, msgClass, true);
        }
        catch(NoSuchFieldException ignored) { }
        catch(ClassCastException cce)
        {
            throw new Exception("When used, the public static field 'PTYPE' must be a String, " + msgClass + "'s is of the wrong type.");
        }
    }

    public static void register(Class msgClass)
        throws Exception
    {
        try
        {
            String contextTagName = (String)msgClass.getField("TAGNAME").get(null);
            String ptype = (String)msgClass.getField("PTYPE").get(null);
            register(contextTagName, ptype, msgClass, true);
        }
        catch(NoSuchFieldException ignored)
        {
            throw new Exception("Registered subclasses must have both public static String fields 'TAGNAME' and 'PTYPE', " + msgClass + " does not.");
        }
        catch(ClassCastException cce)
        {
            throw new Exception("Registered subclasses must have both public static String fields 'TAGNAME' and 'PTYPE', " + msgClass + " has one or more fields of the wrong type.");
        }
    }

    /**
     * @deprecated Method register is deprecated
     */

    public static void register(Class msgClass, boolean reverse)
    {
        try
        {
            String contextTagName = (String)msgClass.getField("TAGNAME").get(null);
            String ptype = (String)msgClass.getField("PTYPE").get(null);
            register(contextTagName, ptype, msgClass, reverse);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void register(String contextTagName, String tagName, String instanceClassName)
    {
        register(contextTagName, tagName, instanceClassName, false);
    }

    public static void register(String contextTagName, String tagName, String instanceClassName, boolean reverse)
    {
        try
        {
            register(contextTagName, tagName, Class.forName(instanceClassName), reverse);
        }
        catch(ClassNotFoundException e)
        {
            throw new RuntimeException("Class " + instanceClassName + " could not be found.");
        }
    }

    public static void register(String contextTagName, String tagName, Class instanceClass)
    {
        register(contextTagName, tagName, instanceClass, false);
    }

    public static void register(String contextTagName, String tagName, Class instanceClass, boolean reverse)
    {
        HashMap tagMap = null;
        if(classMap.containsKey(contextTagName))
        {
            tagMap = (HashMap)classMap.get(contextTagName);
        } else
        {
            tagMap = new HashMap();
            classMap.put(contextTagName, tagMap);
        }
        tagMap.put(tagName, instanceClass);
        if(reverse)
        {
            String array[] = {
                contextTagName, tagName
            };
            classToTagMap.put(instanceClass, array);
        }
    }

    public static String getClassName(String contextTagName, String tagName)
    {
        Class c = getClass(contextTagName, tagName);
        if(c == null)
            throw new RuntimeException("MessageClassRegistry has no p_type=" + tagName);
        else
            return c.getName();
    }

    public static Class getClass(String contextTagName, String tagName)
    {
        HashMap tagMap = (HashMap)classMap.get(contextTagName);
        if(tagMap == null)
            return null;
        else
            return (Class)tagMap.get(tagName);
    }

    /**
     * @deprecated Method getTagName is deprecated
     */

    public static String[] getTagName(String className)
    {
        return getTagName(Class.forName(className));
        ClassNotFoundException cnfe;
        cnfe;
        return null;
    }

    public static String[] getTagName(Class clazz)
    {
        return (String[])classToTagMap.get(clazz);
    }

    private static HashMap classMap = new HashMap();
    private static HashMap classToTagMap = new HashMap();

}

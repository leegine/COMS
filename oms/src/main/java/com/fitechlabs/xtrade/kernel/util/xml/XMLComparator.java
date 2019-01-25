// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMLComparator.java

package com.fitechlabs.xtrade.kernel.util.xml;

import java.util.*;

// Referenced classes of package com.fitechlabs.xtrade.kernel.util.xml:
//            XMLToObjectConverter

public final class XMLComparator
{

    private XMLComparator()
    {
    }

    public static void assertEquivalent(String actualXml, String expectedXml)
        throws Exception
    {
        Map a = XMLToObjectConverter.toMapOfLists(XMLToObjectConverter.toDocument(actualXml));
        Map e = XMLToObjectConverter.toMapOfLists(XMLToObjectConverter.toDocument(expectedXml));
        assertEquivalent(a, e, "xml");
    }

    private static void assertEquivalent(Map actual, Map expected, String context)
        throws Exception
    {
        Set ak = actual.keySet();
        Set ek = expected.keySet();
        assertContainsKeys(actual, ek, context, "not found.");
        assertContainsKeys(expected, ak, context, "not expected.");
        String key;
        for(Iterator it = ak.iterator(); it.hasNext(); assertEquivalent((List)actual.get(key), (List)expected.get(key), context + "." + key))
            key = (String)it.next();

    }

    private static void assertContainsKeys(Map map, Set keys, String context, String onError)
        throws Exception
    {
        for(Iterator it = keys.iterator(); it.hasNext();)
        {
            String key = (String)it.next();
            if(!map.containsKey(key))
                throw new Exception(context + " subelement '" + key + "' " + onError);
        }

    }

    private static void assertEquivalent(List actual, List expected, String context)
        throws Exception
    {
        if(isWildcard(actual) || isWildcard(expected))
            return;
        int i = 0;
        int an = actual.size();
        int en = expected.size();
        if(an != en)
            throw new Exception(context + ": found " + an + " subelements, expected " + en);
        for(; i < an; i++)
            assertEquivalentStringOrMaps(actual.get(i), expected.get(i), an <= 1 ? context : context + "[" + i + "]");

    }

    private static boolean isWildcard(List list)
    {
        return list.size() == 1 && "*".equals(list.get(0));
    }

    private static void assertEquivalentStringOrMaps(Object actual, Object expected, String context)
        throws Exception
    {
        if(actual == null && expected == null)
            return;
        if(actual == null && expected != null || expected == null && actual != null)
            throw new Exception(context + ": null/non-null mismatch, actual=" + actual + ", expected=" + expected + ".");
        if((actual instanceof Map) && (expected instanceof Map))
        {
            assertEquivalent((Map)actual, (Map)expected, context);
        } else
        {
            if((actual instanceof Map) || (expected instanceof Map))
                if("*".equals(expected) || "*".equals(actual))
                    return;
                else
                    throw new Exception(context + ": subelement type mismatch, one contains subelements, the other does not.");
            if(!actual.equals(expected))
                throw new Exception(context + ": strings differ, actual='" + actual + "', expected='" + expected + "'.");
        }
    }
}

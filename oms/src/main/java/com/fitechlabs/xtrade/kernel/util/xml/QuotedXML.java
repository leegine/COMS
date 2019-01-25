// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QuotedXML.java

package com.fitechlabs.xtrade.kernel.util.xml;

import java.util.*;

public class QuotedXML
{

    private QuotedXML()
    {
    }

    public static String quote(String s)
    {
        if(s == null)
            return null;
        int i = 0;
        int n = s.length();
        int j = nextQuotePos(s, 0, n);
        if(j < 0)
            return s;
        StringBuffer sb = new StringBuffer(n + 20);
        for(; j >= 0; j = nextQuotePos(s, i, n))
        {
            sb.append(s.substring(i, j));
            char c = s.charAt(j);
            sb.append(quotes[c]);
            i = j + 1;
        }

        sb.append(s.substring(i, n));
        return sb.toString();
    }

    private static int nextQuotePos(String s, int i, int n)
    {
        for(; i < n; i++)
        {
            char c = s.charAt(i);
            if(c < '\200' && quotes[c] != null)
                return i;
        }

        return -1;
    }

    public static String unquote(String s)
    {
        if(s == null)
            return null;
        int i = 0;
        int n = s.length();
        int j = s.indexOf('&');
        if(j < 0)
            return s;
        StringBuffer sb = new StringBuffer(n);
        for(; j >= 0; j = s.indexOf('&', i))
        {
            sb.append(s.substring(i, j));
            i = j + consume(s, j, sb);
        }

        sb.append(s.substring(i, n));
        return sb.toString();
    }

    private static int consume(String source, int start, StringBuffer destination)
    {
        String s = source.substring(start);
        for(Iterator it = reverse.entrySet().iterator(); it.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
            if(s.startsWith((String)entry.getKey()))
            {
                destination.append(((Character)entry.getValue()).charValue());
                return ((String)entry.getKey()).length();
            }
        }

        throw new IllegalArgumentException("unrecognized quoted text: '" + source + "'");
    }

    private static final String quotes[];
    private static final Map reverse;

    static 
    {
        quotes = new String[128];
        reverse = new HashMap();
        quotes[38] = "&amp;";
        quotes[60] = "&lt;";
        quotes[62] = "&gt;";
        quotes[34] = "&quot;";
        quotes[39] = "&apos;";
        for(int i = 0; i < quotes.length; i++)
            if(quotes[i] != null)
                reverse.put(quotes[i], new Character((char)i));

    }
}

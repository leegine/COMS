// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimplePreferencesImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Preferences;
import java.util.*;

public class SimplePreferencesImpl
    implements Preferences
{

    public SimplePreferencesImpl()
    {
        m_map = new HashMap();
    }

    public void add(String name, List values)
    {
        m_map.put(name, values);
    }

    public Map getPreferences()
    {
        return Collections.unmodifiableMap(m_map);
    }

    public List getPreferences(String prefName)
    {
        return (List)m_map.get(prefName);
    }

    private HashMap m_map;
}

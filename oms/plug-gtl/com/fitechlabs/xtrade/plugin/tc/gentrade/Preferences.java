// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Preferences.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import java.util.List;
import java.util.Map;

public interface Preferences
{

    public abstract Map getPreferences();

    public abstract List getPreferences(String s);
}

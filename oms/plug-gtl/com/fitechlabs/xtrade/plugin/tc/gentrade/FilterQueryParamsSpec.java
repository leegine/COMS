// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FilterQueryParamsSpec.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


public class FilterQueryParamsSpec
{

    public FilterQueryParamsSpec(String where, Object bindVars[])
    {
        m_where = where;
        m_bindVars = bindVars;
    }

    public String getWhereCondition()
    {
        return m_where;
    }

    public Object[] getBindVars()
    {
        return m_bindVars;
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        buf.append("Where=" + getWhereCondition());
        buf.append(",bind vars={");
        Object bindVars[] = getBindVars();
        if(bindVars != null)
        {
            for(int i = 0; i < bindVars.length; i++)
            {
                Object o = bindVars[i];
                buf.append(o);
                buf.append(",");
            }

        }
        buf.append("}");
        return buf.toString();
    }

    private final String m_where;
    private final Object m_bindVars[];
}

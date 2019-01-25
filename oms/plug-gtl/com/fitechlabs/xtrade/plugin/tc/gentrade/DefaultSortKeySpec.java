// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultSortKeySpec.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            SortKeySpec

public class DefaultSortKeySpec
    implements SortKeySpec
{

    public DefaultSortKeySpec(String sortKey)
    {
        m_sortKey = sortKey;
    }

    public String getSortKeySpec()
    {
        return m_sortKey;
    }

    public boolean isSortKeyNull()
    {
        return getSortKeySpec() == null;
    }

    private final String m_sortKey;
    public static final SortKeySpec NULL_SORT_KEY_SPEC = new DefaultSortKeySpec(null);

}

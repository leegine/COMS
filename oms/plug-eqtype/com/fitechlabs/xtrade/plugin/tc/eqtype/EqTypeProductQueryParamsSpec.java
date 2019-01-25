// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeProductQueryParamsSpec.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;

import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

/**
 * @deprecated Class EqTypeProductQueryParamsSpec is deprecated
 */

public class EqTypeProductQueryParamsSpec
{

    public EqTypeProductQueryParamsSpec(PaginationQueryParamsSpec pageSpec, SortKeySpec sortSpec)
    {
        m_pageSpec = pageSpec;
        m_sortSpec = sortSpec;
    }

    public PaginationQueryParamsSpec getPaginationQueryParamsSpec()
    {
        return m_pageSpec;
    }

    public SortKeySpec getSortKeySpec()
    {
        return m_sortSpec;
    }

    public String toString()
    {
        String orderBy = getSortKeySpec() != null && !getSortKeySpec().isSortKeyNull() ? getSortKeySpec().getSortKeySpec() : "null";
        return "pagination : " + getPaginationQueryParamsSpec().toString() + "; sort key spec :" + orderBy;
    }

    public static final SortKeySpec SORT_ON_PRODUCT_CODE = new DefaultSortKeySpec("product_code");
    public static final SortKeySpec SORT_ON_PRODUCT_STANDARD_NAME = new DefaultSortKeySpec("standard_name");
    public static final SortKeySpec SORT_ON_PRODUCT_ALT1_NAME = new DefaultSortKeySpec("name_alt1");
    public static final SortKeySpec SORT_ON_PRODUCT_ALT2_NAME = new DefaultSortKeySpec("name_alt2");
    private final PaginationQueryParamsSpec m_pageSpec;
    private final SortKeySpec m_sortSpec;

}

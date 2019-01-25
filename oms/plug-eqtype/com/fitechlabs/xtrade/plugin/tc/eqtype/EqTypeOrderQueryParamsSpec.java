// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeOrderQueryParamsSpec.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;

import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

public class EqTypeOrderQueryParamsSpec
{

    public EqTypeOrderQueryParamsSpec(DateRangePaginationQueryParamsSpec dateRangePageSpec, SortKeySpec sortSpec)
    {
        m_dateRangePageSpec = dateRangePageSpec;
        m_sortSpec = sortSpec;
    }

    public DateRangePaginationQueryParamsSpec getDateRangePaginationQueryParamsSpec()
    {
        return m_dateRangePageSpec;
    }

    public SortKeySpec getSortKeySpec()
    {
        return m_sortSpec;
    }

    public String toString()
    {
        String orderBy = getSortKeySpec() != null && !getSortKeySpec().isSortKeyNull() ? getSortKeySpec().getSortKeySpec() : "null";
        return "date range pagination : " + getDateRangePaginationQueryParamsSpec().toString() + "; sort key spec :" + orderBy;
    }

    private final DateRangePaginationQueryParamsSpec m_dateRangePageSpec;
    private final SortKeySpec m_sortSpec;
    public static final SortKeySpec SORT_ON_RECEIVED_TIME = new DefaultSortKeySpec("created_timestamp");
    public static final SortKeySpec SORT_ON_PRODUCT_CODE = new DefaultSortKeySpec("product_code");

}

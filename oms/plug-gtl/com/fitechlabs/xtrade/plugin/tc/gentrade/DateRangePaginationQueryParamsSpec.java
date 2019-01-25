// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DateRangePaginationQueryParamsSpec.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            DateRangeQueryParamsSpec, PaginationQueryParamsSpec

public class DateRangePaginationQueryParamsSpec
{

    public DateRangePaginationQueryParamsSpec(DateRangeQueryParamsSpec dateRangeSpec, PaginationQueryParamsSpec paginationSpec)
    {
        m_dateRange = dateRangeSpec;
        m_pagination = paginationSpec;
    }

    public DateRangeQueryParamsSpec getDateRangeQueryParamsSpec()
    {
        return m_dateRange;
    }

    public PaginationQueryParamsSpec getPaginationQueryParamsSpec()
    {
        return m_pagination;
    }

    public String toString()
    {
        return "date range : " + getDateRangeQueryParamsSpec().toString() + "; pagination : " + getPaginationQueryParamsSpec();
    }

    public static final DateRangePaginationQueryParamsSpec ALL_DATE_RANGES_ALL_PAGES;
    private final DateRangeQueryParamsSpec m_dateRange;
    private final PaginationQueryParamsSpec m_pagination;

    static 
    {
        ALL_DATE_RANGES_ALL_PAGES = new DateRangePaginationQueryParamsSpec(DateRangeQueryParamsSpec.ALL_DATE_RANGES, PaginationQueryParamsSpec.ALL_PAGES);
    }
}

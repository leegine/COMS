// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PaginationQueryParamsSpec.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


public class PaginationQueryParamsSpec
{

    public PaginationQueryParamsSpec(int pageSize, int pageNumber)
        throws IllegalArgumentException
    {
        if(pageSize <= 0)
            throw new IllegalArgumentException("page size should be greater than zero");
        if(pageNumber < 0)
        {
            throw new IllegalArgumentException("page number should be zero or greater");
        } else
        {
            m_pageNumber = pageNumber;
            m_pageSize = pageSize;
            return;
        }
    }

    public int getPageNumber()
    {
        return m_pageNumber;
    }

    public int getPageSize()
    {
        return m_pageSize;
    }

    public String toString()
    {
        return " pageNumber : " + getPageNumber() + "; page size : " + getPageSize();
    }

    public int hashCode()
    {
        return toString().hashCode();
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof PaginationQueryParamsSpec)
        {
            PaginationQueryParamsSpec other = (PaginationQueryParamsSpec)obj;
            return other.getPageNumber() == getPageNumber() && other.getPageSize() == getPageSize();
        } else
        {
            return false;
        }
    }

    public static final PaginationQueryParamsSpec ALL_PAGES = new PaginationQueryParamsSpec(0x7fffffff, 0);
    private final int m_pageSize;
    private final int m_pageNumber;

}

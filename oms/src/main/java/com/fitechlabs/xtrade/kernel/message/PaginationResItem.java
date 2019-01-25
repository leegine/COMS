// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PaginationResItem.java

package com.fitechlabs.xtrade.kernel.message;


// Referenced classes of package com.fitechlabs.xtrade.kernel.message:
//            Message

public class PaginationResItem extends Message
{

    public PaginationResItem()
    {
    }

    public int getPageSize()
    {
        return page_size;
    }

    public void setPageSize(int page_size)
    {
        this.page_size = page_size;
    }

    public int getPageIndex()
    {
        return page_index;
    }

    public void setPageIndex(int page_index)
    {
        this.page_index = page_index;
    }

    public int getTotalSize()
    {
        return total_size;
    }

    public void setTotalSize(int total_size)
    {
        this.total_size = total_size;
    }

    public int getTotalPages()
    {
        return total_pages;
    }

    public void setTotalPages(int total_pages)
    {
        this.total_pages = total_pages;
    }

    public int page_size;
    public int page_index;
    public int total_size;
    public int total_pages;
}

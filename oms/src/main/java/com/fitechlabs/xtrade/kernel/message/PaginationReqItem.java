// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PaginationReqItem.java

package com.fitechlabs.xtrade.kernel.message;


// Referenced classes of package com.fitechlabs.xtrade.kernel.message:
//            Message

public class PaginationReqItem extends Message
{

    public PaginationReqItem()
    {
    }

    public String getOrderBy()
    {
        return order_by;
    }

    public int getPageIndex()
    {
        return page_index;
    }

    public int getPageSize()
    {
        return page_size;
    }

    public void setOrderBy(String string)
    {
        order_by = string;
    }

    public void setPageIndex(int i)
    {
        page_index = i;
    }

    public void setPageSize(int i)
    {
        page_size = i;
    }

    public int page_size;
    public int page_index;
    public String order_by;
}

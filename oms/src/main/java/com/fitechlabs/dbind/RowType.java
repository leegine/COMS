// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RowType.java

package com.fitechlabs.dbind;


public class RowType
{

    public RowType(String tableName, String processorName)
    {
        this.tableName = tableName;
        this.processorName = processorName;
    }

    public String getTableName()
    {
        return tableName;
    }

    public String getProcessorName()
    {
        return processorName;
    }

    public String toString()
    {
        return processorName + "." + tableName;
    }

    private String tableName;
    private String processorName;
}

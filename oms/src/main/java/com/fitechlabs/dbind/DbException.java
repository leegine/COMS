// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DbException.java

package com.fitechlabs.dbind;


public class DbException extends Exception
{

    public DbException()
    {
        queryText = null;
    }

    public DbException(String message)
    {
        super(message);
        queryText = null;
    }

    public DbException(String message, String txt)
    {
        super(message);
        queryText = null;
        queryText = txt;
    }

    public String getQueryText()
    {
        return queryText;
    }

    public String toString()
    {
        if(queryText == null)
            return super.toString();
        else
            return super.toString() + " sql=" + queryText;
    }

    private String queryText;
}

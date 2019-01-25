// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ListPage.java

package com.fitechlabs.dbind;

import java.util.List;

public interface ListPage
    extends List
{

    public abstract int pageSize();

    public abstract int pageNumber();

    public abstract int totalSize();

    public abstract int totalPages();
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SQLHelper.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.dbind.Row;
import java.util.Collection;
import java.util.Map;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            BindSql, ResultFiller

public interface SQLHelper
{

    public abstract BindSql toInsertSpec(Map map);

    public abstract BindSql toInsertSpec(Row row);

    public abstract BindSql toUpdateSpec(Map map);

    public abstract BindSql toUpdateSpec(Row row);

    public abstract String getDeleteFromSql();

    public abstract BindSql getAccessPkBindSql(Object obj);

    public abstract BindSql getMutatePkBindSql(Object obj);

    public abstract String getSelectRowSql();

    public abstract String getSelectPkSql();

    public abstract String getSelectCountSql();

    public abstract ResultFiller getRowFiller();

    public abstract ResultFiller getPkFiller();

    public abstract ResultFiller getCountFiller();

    public abstract BindSql getPaginatedBindSql(String s, String s1, Object aobj[], int i, int j);

    public abstract Object pkFromMap(Map map);

    public abstract ResultFiller getMapFiller();

    public abstract Row rowFromMap(Map map);

    public abstract Collection getColumnNames();

    public abstract String getSelectAddedFieldsSql();

    public abstract ResultFiller getAddedFieldsMapFiller();

    public abstract String getSelectMapSql();

    public abstract BindSql getWhereKeysMatchBindSql();

    public abstract BindSql getPaginatedRowBindSql(String s, String s1, Object aobj[], int i, int j);
}

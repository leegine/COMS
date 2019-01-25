// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Table.java

package com.fitechlabs.dbind;

import java.util.List;
import java.util.Map;

// Referenced classes of package com.fitechlabs.dbind:
//            UpdateException, ConnectionException, DeleteException, SelectException, 
//            FindException, InsertException, Row, PrimaryKey, 
//            RowType, ListSubset

public interface Table
{

    /**
     * @deprecated Method update is deprecated
     */

    public abstract int update(Object obj, Map map)
        throws UpdateException, ConnectionException;

    /**
     * @deprecated Method delete is deprecated
     */

    public abstract int delete(Object obj)
        throws ConnectionException, DeleteException;

    /**
     * @deprecated Method select is deprecated
     */

    public abstract Row select(Object obj)
        throws ConnectionException, SelectException, FindException;

    /**
     * @deprecated Method select is deprecated
     */

    public abstract Row select(Object obj, String s)
        throws ConnectionException, SelectException, FindException;

    /**
     * @deprecated Method select is deprecated
     */

    public abstract Row select(Object obj, String s, String s1, Object aobj[])
        throws ConnectionException, SelectException, FindException;

    public abstract Object insert(Row row)
        throws ConnectionException, InsertException;

    public abstract Object insert(Map map)
        throws InsertException, ConnectionException;

    public abstract int update(Row row)
        throws ConnectionException, UpdateException;

    public abstract int update(Row row, String s, Object aobj[])
        throws ConnectionException, UpdateException;

    public abstract int update(PrimaryKey primarykey, Map map)
        throws UpdateException, ConnectionException;

    public abstract int update(PrimaryKey primarykey, String s, Object aobj[], Map map)
        throws UpdateException, ConnectionException;

    public abstract int updateAll(String s, Object aobj[], Map map)
        throws UpdateException, ConnectionException;

    public abstract int delete(PrimaryKey primarykey)
        throws ConnectionException, DeleteException;

    public abstract int delete(PrimaryKey primarykey, String s, Object aobj[])
        throws ConnectionException, DeleteException;

    public abstract int deleteAll(String s, Object aobj[])
        throws ConnectionException, DeleteException;

    public abstract Row select(PrimaryKey primarykey)
        throws ConnectionException, SelectException, FindException;

    public abstract Row select(PrimaryKey primarykey, String s)
        throws ConnectionException, SelectException, FindException;

    public abstract Row select(PrimaryKey primarykey, String s, String s1, Object aobj[])
        throws ConnectionException, SelectException, FindException;

    public abstract List selectAll(String s, String s1, String s2, Object aobj[])
        throws ConnectionException, SelectException;

    public abstract List selectAll(String s, String s1, String s2, Object aobj[], RowType arowtype[])
        throws ConnectionException, SelectException;

    public abstract ListSubset selectAll(String s, String s1, String s2, Object aobj[], int i, int j)
        throws ConnectionException, SelectException;

    public abstract ListSubset selectAll(String s, String s1, String s2, Object aobj[], int i, int j, RowType arowtype[])
        throws ConnectionException, SelectException;

    public abstract List selectPks(String s, String s1, String s2, Object aobj[])
        throws ConnectionException, SelectException;

    public abstract int selectCount(String s, Object aobj[])
        throws ConnectionException, SelectException;

    public abstract Object createPk()
        throws ConnectionException, UpdateException;

    public abstract String getName();

    public abstract void setReadonlyMode(boolean flag);
}

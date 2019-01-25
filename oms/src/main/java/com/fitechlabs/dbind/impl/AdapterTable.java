// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AdapterTable.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.dbind.*;
import java.util.*;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            DataTable, SQLHelper

public class AdapterTable
    implements Table
{

    AdapterTable(DataTable impl)
    {
        this.impl = impl;
    }

    public DataTable getDataTable()
    {
        return impl;
    }

    private void assertConditionsValid(String conditions)
    {
        if(conditions != null && conditions.length() != 0 && !DBindTransactions.getIsTransactionActive())
            throw new IllegalStateException("non-null conditions require active transaction.");
        else
            return;
    }

    private void assertValid(Map map)
    {
        Collection unknowns = new HashSet(map.keySet());
        Collection valid = impl.getColumnNames();
        unknowns.removeAll(valid);
        if(unknowns.size() > 0)
            throw new IllegalArgumentException("bad colum names " + unknowns);
        else
            return;
    }

    public Object createPk()
        throws ConnectionException, UpdateException
    {
        return impl.createPk();
    }

    public int delete(Object primaryKey)
        throws ConnectionException, DeleteException
    {
        if(!(primaryKey instanceof PrimaryKey))
            throw new IllegalArgumentException("not a PrimaryKey: " + primaryKey);
        else
            return impl.delete((PrimaryKey)primaryKey, null, null);
    }

    public int delete(PrimaryKey primaryKey, String where, Object bindVariables[])
        throws ConnectionException, DeleteException
    {
        return impl.delete(primaryKey, where, bindVariables);
    }

    public int delete(PrimaryKey primaryKey)
        throws ConnectionException, DeleteException
    {
        return impl.delete(primaryKey, null, null);
    }

    public int deleteAll(String where, Object bindVariables[])
        throws ConnectionException, DeleteException
    {
        return impl.deleteAll(where, bindVariables);
    }

    public Object insert(Map initialValues)
        throws InsertException, ConnectionException
    {
        assertValid(initialValues);
        int count = impl.insert(initialValues);
        if(count == 1)
            return impl.getSQLHelper().pkFromMap(initialValues);
        else
            return null;
    }

    public Object insert(Row initialValues)
        throws ConnectionException, InsertException
    {
        int count = impl.insert(initialValues);
        if(count == 1)
            return initialValues.getPrimaryKey();
        else
            return null;
    }

    public Row select(Object primaryKey, String where, String conditions, Object bindVars[])
        throws ConnectionException, SelectException, FindException
    {
        if(!(primaryKey instanceof PrimaryKey))
            throw new IllegalArgumentException("not a PrimaryKey: " + primaryKey);
        assertConditionsValid(conditions);
        Row row = impl.select((PrimaryKey)primaryKey, where, conditions, bindVars);
        if(row == null)
            throw new FindException("not found: " + primaryKey);
        else
            return row;
    }

    public Row select(Object primaryKey, String conditions)
        throws ConnectionException, SelectException, FindException
    {
        if(!(primaryKey instanceof PrimaryKey))
            throw new IllegalArgumentException("not a PrimaryKey: " + primaryKey);
        assertConditionsValid(conditions);
        Row row = impl.select((PrimaryKey)primaryKey, conditions);
        if(row == null)
            throw new FindException("not found: " + primaryKey);
        else
            return row;
    }

    public Row select(Object primaryKey)
        throws ConnectionException, SelectException, FindException
    {
        if(!(primaryKey instanceof PrimaryKey))
            throw new IllegalArgumentException("not a PrimaryKey: " + primaryKey);
        Row row = impl.select((PrimaryKey)primaryKey);
        if(row == null)
            throw new FindException("not found: " + primaryKey);
        else
            return row;
    }

    public Row select(PrimaryKey primaryKey, String where, String conditions, Object bindVars[])
        throws ConnectionException, SelectException, FindException
    {
        assertConditionsValid(conditions);
        Row row = impl.select(primaryKey, where, conditions, bindVars);
        if(row == null)
            throw new FindException("not found: " + primaryKey);
        else
            return row;
    }

    public Row select(PrimaryKey primaryKey, String conditions)
        throws ConnectionException, SelectException, FindException
    {
        assertConditionsValid(conditions);
        Row row = impl.select(primaryKey, conditions);
        if(row == null)
            throw new FindException("not found: " + primaryKey);
        else
            return row;
    }

    public Row select(PrimaryKey primaryKey)
        throws ConnectionException, SelectException, FindException
    {
        Row row = impl.select(primaryKey);
        if(row == null)
            throw new FindException("not found: " + primaryKey);
        else
            return row;
    }

    public ListSubset selectAll(String where, String orderBy, String conditions, Object bindVariables[], int fromIndex, int toIndex)
        throws ConnectionException, SelectException
    {
        assertConditionsValid(conditions);
        return impl.selectAll(where, orderBy, conditions, bindVariables, fromIndex, toIndex);
    }

    public ListSubset selectAll(String where, String orderBy, String conditions, Object bindVariables[], int fromIndex, int toIndex, RowType rowTypes[])
        throws ConnectionException, SelectException
    {
        assertConditionsValid(conditions);
        return impl.selectAll(where, orderBy, conditions, bindVariables, fromIndex, toIndex, rowTypes);
    }

    public List selectAll(String where, String orderBy, String conditions, Object bindVariables[])
        throws ConnectionException, SelectException
    {
        assertConditionsValid(conditions);
        return impl.selectAll(where, orderBy, conditions, bindVariables);
    }

    public List selectAll(String where, String orderBy, String conditions, Object bindVariables[], RowType rowTypes[])
        throws ConnectionException, SelectException
    {
        assertConditionsValid(conditions);
        return impl.selectAll(where, orderBy, conditions, bindVariables, rowTypes);
    }

    public int selectCount(String where, Object bindVariables[])
        throws ConnectionException, SelectException
    {
        return impl.selectCount(where, bindVariables);
    }

    public List selectPks(String where, String orderBy, String conditions, Object bindVariables[])
        throws ConnectionException, SelectException
    {
        assertConditionsValid(conditions);
        return impl.selectPks(where, orderBy, conditions, bindVariables);
    }

    public int update(Object primaryKey, Map changes)
        throws UpdateException, ConnectionException
    {
        if(!(primaryKey instanceof PrimaryKey))
        {
            throw new IllegalArgumentException("not a PrimaryKey: " + primaryKey);
        } else
        {
            assertValid(changes);
            return impl.update((PrimaryKey)primaryKey, null, null, changes);
        }
    }

    public int update(PrimaryKey primaryKey, Map changes)
        throws UpdateException, ConnectionException
    {
        assertValid(changes);
        return impl.update(primaryKey, null, null, changes);
    }

    public int update(PrimaryKey primaryKey, String where, Object bindVariables[], Map changes)
        throws UpdateException, ConnectionException
    {
        assertValid(changes);
        return impl.update(primaryKey, where, bindVariables, changes);
    }

    public int update(Row newValues, String where, Object bindVariables[])
        throws ConnectionException, UpdateException
    {
        return impl.update(newValues, where, bindVariables);
    }

    public int update(Row newValues)
        throws ConnectionException, UpdateException
    {
        return impl.update(newValues, null, null);
    }

    public int updateAll(String where, Object bindVariables[], Map changes)
        throws UpdateException, ConnectionException
    {
        assertValid(changes);
        return impl.updateAll(where, bindVariables, changes);
    }

    public String getName()
    {
        return impl.getName();
    }

    public void setReadonlyMode(boolean readonly)
    {
        impl.setReadonlyMode(readonly);
    }

    protected final DataTable impl;
}

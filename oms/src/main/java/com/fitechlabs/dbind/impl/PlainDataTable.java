// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PlainDataTable.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.dbind.*;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            AbstractDataTable, SQLHelperImpl, SQLHelper, Accessor

public class PlainDataTable extends AbstractDataTable
{

    public PlainDataTable(Accessor accessor, String tableName, Class pkClass, Class paramsClass)
    {
        super(tableName, new SQLHelperImpl(tableName, pkClass, paramsClass, null, null), accessor);
        hasPk = pkClass != null;
    }

    public PlainDataTable(Accessor accessor, String tableName, Class pkClass, Class paramsClass, PlainDataTable superPlainDataTable, String subclassFieldName)
    {
        super(tableName, new SQLHelperImpl(tableName, pkClass, paramsClass, superPlainDataTable != null ? (SQLHelperImpl)superPlainDataTable.getSQLHelper() : null, subclassFieldName), accessor);
        hasPk = pkClass != null;
    }

    public ListSubset selectAll(String where, String orderBy, String conditions, Object bindVariables[], int fromIndex, int toIndex)
        throws ConnectionException, SelectException
    {
        if(hasPk && conditions != null && conditions.length() > 0)
        {
            return super.selectAll(where, orderBy, conditions, bindVariables, fromIndex, toIndex);
        } else
        {
            int count = selectCount(where, bindVariables);
            java.util.List rows = super.accessor.accessList(null, super.sqlHelper.getPaginatedRowBindSql(where, orderBy, bindVariables, fromIndex, toIndex), null, null, conditions, super.sqlHelper.getRowFiller());
            return ArrayListSubset.fromSubList(rows, fromIndex, count);
        }
    }

    public boolean hasPrimaryKey()
    {
        return hasPk;
    }

    private final boolean hasPk;
}

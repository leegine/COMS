// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PlainDataStore.java

package com.fitechlabs.dbind.impl;

import javax.sql.DataSource;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            AbstractDataStore, PlainDataTable, DataTable

public class PlainDataStore extends AbstractDataStore
{

    public PlainDataStore(DataSource dataSource)
    {
        super(dataSource);
    }

    PlainDataTable getPlainDataTable(String name)
    {
        return (PlainDataTable)super.getDataTable(name);
    }

    public DataTable addPlainTable(String name, Class pkClass, Class paramsClass)
    {
        Accessor accessor = getAccessor();
        PlainDataTable plainDataTable = new PlainDataTable(accessor, name, pkClass, paramsClass);
        super.addDataTable(name, plainDataTable);
        return plainDataTable;
    }

    public DataTable addSubclassTable(String tableName, Class pkClass, Class paramsClass, String superTableName, String subclassFieldName)
    {
        PlainDataTable superPlainDataTable = null;
        if(superTableName != null)
        {
            superPlainDataTable = getPlainDataTable(superTableName);
            if(superPlainDataTable == null)
                throw new IllegalStateException("super table '" + superTableName + "' not found.");
        }
        Accessor accessor = getAccessor();
        PlainDataTable plainDataTable = new PlainDataTable(accessor, tableName, pkClass, paramsClass, superPlainDataTable, subclassFieldName);
        super.addDataTable(tableName, plainDataTable);
        return plainDataTable;
    }
}

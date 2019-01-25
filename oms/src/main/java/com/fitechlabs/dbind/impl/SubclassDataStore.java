// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SubclassDataStore.java

package com.fitechlabs.dbind.impl;

import javax.sql.DataSource;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            AbstractDataStore, PlainDataStore, SubclassDataTable, PlainDataTable, 
//            DataTable

public class SubclassDataStore extends AbstractDataStore
{

    public SubclassDataStore(DataSource dataSource)
    {
        super(dataSource);
        plainDataStore = new PlainDataStore(dataSource);
    }

    SubclassDataTable getSubclassDataTable(String dataTableName)
    {
        DataTable dt = super.getDataTable(dataTableName);
        return (SubclassDataTable)dt;
    }

    public DataTable addPlainTable(String name, Class pkClass, Class paramsClass)
    {
        DataTable pdt = plainDataStore.addPlainTable(name, pkClass, paramsClass);
        super.addDataTable(name, pdt);
        return pdt;
    }

    public DataTable addSubclassTable(String name, Class pkClass, Class paramsClass, String superTableName, String subclassFieldName)
    {
        SubclassDataTable superSubclassDataTable = null;
        PlainDataTable superPlainDataTable = null;
        if(superTableName != null)
        {
            superSubclassDataTable = getSubclassDataTable(superTableName);
            if(superSubclassDataTable == null)
                throw new IllegalStateException("super table '" + superTableName + "' not found.");
            superPlainDataTable = superSubclassDataTable.getPlainDataTable();
        }
        PlainDataTable pdt = (PlainDataTable)plainDataStore.addSubclassTable(name, pkClass, paramsClass, superTableName, subclassFieldName);
        SubclassDataTable sdt = new SubclassDataTable(pdt, superSubclassDataTable, subclassFieldName, this);
        super.addDataTable(name, sdt);
        return sdt;
    }

    public void setDataSource(DataSource ds)
    {
        super.setDataSource(ds);
        plainDataStore.setDataSource(ds);
    }

    private PlainDataStore plainDataStore;
}

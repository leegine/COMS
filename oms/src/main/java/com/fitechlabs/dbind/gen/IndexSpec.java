// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IndexSpec.java

package com.fitechlabs.dbind.gen;

import java.io.PrintStream;
import java.util.*;

// Referenced classes of package com.fitechlabs.dbind.gen:
//            ColumnSpec, DataSpec, TableSpec

public class IndexSpec
{

    private static void status(String s)
    {
        System.out.println(s);
    }

    private static void warn(String s)
    {
        System.err.println("  WARN: " + s);
    }

    private static void error(String s)
    {
        System.err.println("  ERROR: " + s);
    }

    private static void debug(String s1)
    {
    }

    public String toString()
    {
        return indexName + ": " + tableName + columnSpecs;
    }

    public IndexSpec(String indexName, String tableName, boolean unique, List columnNames)
    {
        this.indexName = indexName;
        this.tableName = tableName;
        this.unique = unique;
        this.columnNames = columnNames;
    }

    public String getIndexName()
    {
        return indexName;
    }

    public String getTableName()
    {
        return tableName;
    }

    public boolean isUnique()
    {
        return unique;
    }

    public List getColumnNames()
    {
        return columnNames;
    }

    public boolean resolveReferences(DataSpec ds)
    {
        valid = false;
        tableSpec = ds.getTableOrImportNamed(tableName);
        if(tableSpec == null)
        {
            error("Unknown table named in index " + indexName + ": " + tableName);
            return false;
        }
        columnSpecs = new ArrayList();
        ColumnSpec cs;
        for(Iterator it = columnNames.iterator(); it.hasNext(); columnSpecs.add(cs))
        {
            String name = (String)it.next();
            cs = tableSpec.getColumnByName(name);
            if(cs == null)
            {
                error("Unknown column named in index " + indexName + ": " + tableName + "." + name);
                return false;
            }
        }

        valid = true;
        return true;
    }

    public TableSpec getTableSpec()
    {
        return tableSpec;
    }

    public int getColumnCount()
    {
        return columnNames.size();
    }

    public ColumnSpec getColumnSpec(int i)
    {
        return (ColumnSpec)columnSpecs.get(i);
    }

    public boolean isValid()
    {
        return valid;
    }

    private String indexName;
    private String tableName;
    private boolean unique;
    private List columnNames;
    private TableSpec tableSpec;
    private List columnSpecs;
    private boolean valid;
}

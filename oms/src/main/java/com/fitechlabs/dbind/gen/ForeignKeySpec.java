// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ForeignKeySpec.java

package com.fitechlabs.dbind.gen;

import java.io.PrintStream;
import java.util.*;

// Referenced classes of package com.fitechlabs.dbind.gen:
//            ColumnSpec, TableSpec, DataSpec

public class ForeignKeySpec
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
        System.err.println("  WARN: " + s);
    }

    private static void debug(String s1)
    {
    }

    public String toString()
    {
        return name + ": " + localTable.asHeader() + "." + localNames + "->" + foreignTableName + "." + foreignNames;
    }

    public ForeignKeySpec(String fkName, TableSpec localTable, String foreignTableName, List localNames, List foreignNames)
    {
        if(localNames.size() != foreignNames.size())
        {
            throw new IllegalArgumentException("list sizes don't match");
        } else
        {
            name = fkName;
            this.localTable = localTable;
            this.foreignTableName = foreignTableName;
            this.localNames = localNames;
            this.foreignNames = foreignNames;
            return;
        }
    }

    public String getName()
    {
        return name;
    }

    public int getColumnCount()
    {
        return localNames.size();
    }

    public String getLocalName(int i)
    {
        return (String)localNames.get(i);
    }

    public String getForeignName(int i)
    {
        return (String)foreignNames.get(i);
    }

    public String getForeignTableName()
    {
        return foreignTableName;
    }

    public Map getForeignColumnToLocalColumnMap()
    {
        return foreignColumnToLocalColumn;
    }

    public Map getLocalColumnToForeignColumnMap()
    {
        return localColumnToForeignColumn;
    }

    public boolean resolveReferences(DataSpec ds)
    {
        valid = false;
        foreignTable = ds.getTableOrImportNamed(foreignTableName);
        if(foreignTable == null)
        {
            error("table named in foreign key relation not found: " + localTable.asHeader() + "->" + foreignTableName);
            return false;
        }
        int i = 0;
        int n = getColumnCount();
        localColumns = new ArrayList(n);
        foreignColumns = new ArrayList(n);
        foreignColumnToLocalColumn = new HashMap();
        localColumnToForeignColumn = new HashMap();
        Map byfname = new HashMap(n * 2);
        for(; i < n; i++)
        {
            String localColumnName = getLocalName(i);
            String foreignColumnName = getForeignName(i);
            ColumnSpec localColumn = localTable.getColumnByName(localColumnName);
            ColumnSpec foreignColumn = foreignTable.getColumnByName(foreignColumnName);
            foreignColumnToLocalColumn.put(foreignColumn, localColumn);
            localColumnToForeignColumn.put(localColumn, foreignColumn);
            if(localColumn == null)
            {
                error("local column named in foreign key relation not found: " + localTable.asHeader() + "." + localColumnName);
                return false;
            }
            if(foreignColumn == null)
            {
                error("foreign column named in foreign key relation not found: " + localTable.asHeader() + "->" + foreignTableName + "." + foreignColumnName);
                return false;
            }
            localColumns.add(localColumn);
            foreignColumns.add(foreignColumn);
            byfname.put(foreignColumn.asHeader(), localColumn);
        }

        localColumnsInForeignPkOrder = new ArrayList(n);
        ColumnSpec localColumn;
        for(Enumeration e = foreignTable.getPrimaryKeyComponents(); e.hasMoreElements(); localColumnsInForeignPkOrder.add(localColumn))
        {
            ColumnSpec foreignColumn = (ColumnSpec)e.nextElement();
            String foreignColumnName = foreignColumn.asHeader();
            localColumn = (ColumnSpec)byfname.remove(foreignColumnName);
            if(localColumn == null)
            {
                error("foreign table primary key component not found in foreign key relation " + localTable.asHeader() + "->" + foreignTableName + "." + foreignColumnName);
                return false;
            }
        }

        if(!byfname.isEmpty())
        {
            error("foreign key relation contains elements not found in foreign table primary key " + localTable.asHeader() + "->" + foreignTableName + ": " + byfname);
            return false;
        } else
        {
            foreignTable.addReverseForeignKeySpec(this);
            valid = true;
            return true;
        }
    }

    public ColumnSpec getLocalColumn(int i)
    {
        return (ColumnSpec)localColumns.get(i);
    }

    public ColumnSpec getForeignColumn(int i)
    {
        return (ColumnSpec)foreignColumns.get(i);
    }

    public List getLocalColumnsInForeignPkOrder()
    {
        return localColumnsInForeignPkOrder;
    }

    public TableSpec getLocalTable()
    {
        return localTable;
    }

    public TableSpec getForeignTable()
    {
        return foreignTable;
    }

    public boolean getIsNullable()
    {
        int i = 0;
        for(int n = localColumns.size(); i < n; i++)
            if(!getLocalColumn(i).getIsNullable())
                return false;

        return true;
    }

    public boolean isValid()
    {
        return valid;
    }

    public boolean isParentRelation()
    {
        String sup = localTable.getAttributeValue("sup");
        return sup != null && sup.equalsIgnoreCase(foreignTableName);
    }

    private String name;
    private TableSpec localTable;
    private String foreignTableName;
    private List localNames;
    private List foreignNames;
    private TableSpec foreignTable;
    private List localColumns;
    private List foreignColumns;
    private List localColumnsInForeignPkOrder;
    private boolean valid;
    private Map foreignColumnToLocalColumn;
    private Map localColumnToForeignColumn;
}

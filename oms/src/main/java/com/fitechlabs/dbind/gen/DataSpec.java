// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataSpec.java

package com.fitechlabs.dbind.gen;

import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.*;

// Referenced classes of package com.fitechlabs.dbind.gen:
//            TableSpec, ColumnSpec, PackageSpec

public class DataSpec
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

    public DataSpec()
    {
        tableSpecs = new Vector();
        tableSpecsWithPKs = null;
        tableSpecsByName = new Hashtable();
        importTablesByName = new HashMap();
        name = "auto";
        className = "AutoDatabase";
        packages = new ArrayList();
    }

    public DataSpec(String name)
    {
        tableSpecs = new Vector();
        tableSpecsWithPKs = null;
        tableSpecsByName = new Hashtable();
        importTablesByName = new HashMap();
        this.name = "auto";
        className = "AutoDatabase";
        packages = new ArrayList();
        setName(name);
    }

    public String toString()
    {
        return "{tables=" + tableSpecs.toString() + "}";
    }

    public DataSpec(TableSpec tables[])
    {
        tableSpecs = new Vector();
        tableSpecsWithPKs = null;
        tableSpecsByName = new Hashtable();
        importTablesByName = new HashMap();
        name = "auto";
        className = "AutoDatabase";
        packages = new ArrayList();
        for(int i = 0; i < tables.length; i++)
            addTableSpec(tables[i]);

    }

    public DataSpec(TableSpec tables[], PackageSpec packages[])
    {
        this(tables);
        for(int i = 0; i < packages.length; i++)
            addPackage(packages[i]);

    }

    public static DataSpec getInstance(String implClassName)
        throws Exception
    {
        Method instanceMethod;
        Class implClass = Class.forName(implClassName);
        instanceMethod = implClass.getMethod("getInstance", new Class[0]);
        return (DataSpec)instanceMethod.invoke(null, null);
        ClassNotFoundException e;
        e;
        error("Could not find class: -" + implClassName + "-\n");
        throw new Exception(e.getMessage());
        Exception ex;
        ex;
        ex.printStackTrace(System.err);
        throw ex;
    }

    public void addTableSpec(TableSpec tableSpec)
    {
        String key = tableSpec.asHeader().toLowerCase();
        if(tableSpecsByName.get(key) != null)
        {
            error("table " + key + " already defined, ignoring");
            return;
        } else
        {
            tableSpecs.addElement(tableSpec);
            tableSpecsByName.put(key, tableSpec);
            tableSpecsWithPKs = null;
            return;
        }
    }

    public void addImportTableSpec(TableSpec tableSpec)
    {
        String key = tableSpec.asHeader().toLowerCase();
        if(importTablesByName.get(key) != null)
        {
            warn("table " + key + " already imported, ignoring");
            return;
        } else
        {
            importTablesByName.put(key, tableSpec);
            return;
        }
    }

    public Enumeration getTableSpecElements()
    {
        return tableSpecs.elements();
    }

    public void addPackage(PackageSpec ps)
    {
        packages.add(ps);
    }

    public Iterator getPackageSpecIterator()
    {
        return packages.iterator();
    }

    public Enumeration getTableSpecElementsWithPrimaryKeys()
    {
        Vector v = tableSpecsWithPKs;
        if(v == null)
        {
            v = new Vector();
            for(Enumeration e = getTableSpecElements(); e.hasMoreElements();)
            {
                TableSpec ts = (TableSpec)e.nextElement();
                if(!ts.hasPrimaryKeyComponents())
                    warn("table " + ts.asHeader() + " has no primary key ");
                else
                    v.addElement(ts);
            }

            tableSpecsWithPKs = v;
        }
        return v.elements();
    }

    public TableSpec getTableNamed(String headerName)
    {
        String key = headerName.toLowerCase();
        return (TableSpec)tableSpecsByName.get(key);
    }

    public TableSpec getTableOrImportNamed(String headerName)
    {
        String key = headerName.toLowerCase();
        TableSpec ts = (TableSpec)tableSpecsByName.get(key);
        if(ts == null)
            ts = (TableSpec)importTablesByName.get(key);
        return ts;
    }

    public Enumeration getTableSpecElementsLeavesFirst()
    {
        Vector a = new Vector();
        Vector b = new Vector();
        Vector c = new Vector();
        Enumeration e = getTableSpecElementsWithPrimaryKeys();
        do
        {
            if(!e.hasMoreElements())
                break;
            TableSpec ts = (TableSpec)e.nextElement();
            if(hasNonNullableForeignKeys(ts))
                a.addElement(ts);
            else
            if(hasNullableForeignKeys(ts))
            {
                if(!a.contains(ts))
                    b.addElement(ts);
            } else
            {
                c.addElement(ts);
            }
        } while(true);
        Vector inorder = new Vector();
        Vector callstack = new Vector();
        TableSpec ts;
        for(Enumeration e = c.elements(); e.hasMoreElements(); inorder.addElement(ts))
        {
            ts = (TableSpec)e.nextElement();
            if(debug)
                debug("-- adding a-type table " + ts.asHeader());
        }

        TableSpec ts;
        for(Enumeration e = b.elements(); e.hasMoreElements(); includeBType(ts, inorder, callstack))
        {
            ts = (TableSpec)e.nextElement();
            if(debug)
                debug("-- including b-type table " + ts.asHeader());
        }

        TableSpec ts;
        for(Enumeration e = a.elements(); e.hasMoreElements(); includeCType(ts, inorder, callstack))
        {
            ts = (TableSpec)e.nextElement();
            if(debug)
                debug("-- including c-type table " + ts.asHeader());
        }

        return inorder.elements();
    }

    private void includeBType(TableSpec ts, Vector inorder, Vector callstack)
    {
        if(checkCircularReferences(ts, callstack, "soft"))
            return;
        Enumeration e = getNullableForeignKeyTables(ts).elements();
        do
        {
            if(!e.hasMoreElements())
                break;
            TableSpec fkts = (TableSpec)e.nextElement();
            if(!inorder.contains(fkts) && !hasNonNullableForeignKeys(fkts))
            {
                if(debug)
                    debug("-- including B-type foreign table first: " + fkts.asHeader());
                callstack.addElement(ts);
                includeBType(fkts, inorder, callstack);
                callstack.removeElement(ts);
            }
        } while(true);
        if(!inorder.contains(ts))
        {
            if(debug)
                debug("---->> all conditions met for B-type table to be added: " + ts.asHeader());
            inorder.addElement(ts);
        }
    }

    private void includeCType(TableSpec ts, Vector inorder, Vector callstack)
    {
        if(checkCircularReferences(ts, callstack, "hard"))
            return;
        for(Enumeration e = getNonNullableForeignKeyTables(ts).elements(); e.hasMoreElements(); callstack.removeElement(ts))
        {
            TableSpec fkts = (TableSpec)e.nextElement();
            if(!inorder.contains(fkts) && debug)
                debug("-- including C-type foreign table first: " + fkts.asHeader());
            callstack.addElement(ts);
            includeCType(fkts, inorder, callstack);
        }

        if(!inorder.contains(ts))
        {
            if(debug)
                debug("---->> all conditions met for C-type table to be added: " + ts.asHeader());
            inorder.addElement(ts);
        }
    }

    private boolean checkCircularReferences(TableSpec ts, Vector callstack, String type)
    {
        if(callstack.contains(ts))
        {
            System.out.print(type + " circular reference: ");
            Enumeration f = callstack.elements();
            do
            {
                if(!f.hasMoreElements())
                    break;
                System.out.print(((TableSpec)f.nextElement()).asHeader());
                if(f.hasMoreElements())
                    System.out.print(",");
            } while(true);
            System.out.println();
            return true;
        } else
        {
            return false;
        }
    }

    private boolean hasNonNullableForeignKeys(TableSpec ts)
    {
        return getNonNullableForeignKeyTables(ts).size() > 0;
    }

    private boolean hasNullableForeignKeys(TableSpec ts)
    {
        return getNullableForeignKeyTables(ts).size() > 0;
    }

    private Vector getNonNullableForeignKeyTables(TableSpec ts)
    {
        Vector v = new Vector();
        Enumeration e = ts.getForeignKeyColumns();
        do
        {
            if(!e.hasMoreElements())
                break;
            ColumnSpec col = (ColumnSpec)e.nextElement();
            if(!col.getIsNullable())
            {
                String fkTableName = col.getForeignTableName().toLowerCase();
                TableSpec fkts = (TableSpec)tableSpecsByName.get(fkTableName);
                if(fkts == null)
                    debug("error: can't lookup foreign table named " + fkTableName);
                else
                    v.addElement(fkts);
            }
        } while(true);
        return v;
    }

    private Vector getNullableForeignKeyTables(TableSpec ts)
    {
        Vector v = new Vector();
        Enumeration e = ts.getForeignKeyColumns();
        do
        {
            if(!e.hasMoreElements())
                break;
            ColumnSpec col = (ColumnSpec)e.nextElement();
            if(col.getIsNullable())
            {
                String fkTableName = col.getForeignTableName().toLowerCase();
                TableSpec fkts = (TableSpec)tableSpecsByName.get(fkTableName);
                if(fkts == null)
                    debug("error: can't lookup foreign table named " + fkTableName);
                else
                    v.addElement(fkts);
            }
        } while(true);
        return v;
    }

    void resolveForeignKeyReferences()
    {
        TableSpec ts;
        for(Enumeration e = getTableSpecElements(); e.hasMoreElements(); ts.resolveForeignKeyReferences(this))
            ts = (TableSpec)e.nextElement();

    }

    private void setName(String name)
    {
        this.name = name;
        className = name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public String getName()
    {
        return name;
    }

    public String asClass()
    {
        return className;
    }

    private static boolean debug = false;
    private Vector tableSpecs;
    private Vector tableSpecsWithPKs;
    private Hashtable tableSpecsByName;
    private Map importTablesByName;
    private String name;
    private String className;
    private List packages;

}

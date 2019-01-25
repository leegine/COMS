// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TableSpec.java

package com.fitechlabs.dbind.gen;

import java.io.PrintStream;
import java.util.*;

// Referenced classes of package com.fitechlabs.dbind.gen:
//            ColumnSpec, ForeignKeySpec, DataSpec, IndexSpec

public class TableSpec
{

    public String toString()
    {
        if(!derived)
            deriveAuxLists();
        return "\nname=" + headerName + (derivedPrimaryKeys.size() <= 0 ? "" : ",\n\tpks=" + derivedPrimaryKeys) + (derivedNonPrimaryKeys.size() <= 0 ? "" : ",\n\tcols=" + derivedNonPrimaryKeys) + (allForeignKeySpecs.size() <= 0 ? "" : ",\n\tfks=" + allForeignKeySpecs) + (allIndexes.size() <= 0 ? "" : ",\n\tindexes=" + allIndexes);
    }

    public TableSpec(String tableNameInDatabase)
    {
        allColumns = new Vector();
        allNonNullableColumns = new Vector();
        allForeignKeys = new Vector();
        allForeignKeySpecs = new Vector();
        allColumnsByName = new Hashtable();
        allPrimaryKeysByName = new Hashtable();
        allForeignKeysByName = new Hashtable();
        allReverseForeignKeySpecs = new HashSet();
        allIndexes = new Vector();
        derivedPrimaryKeys = null;
        derivedNonPKNonNullables = null;
        derivedNonPrimaryKeys = null;
        derivedForeignKeyTables = null;
        attributes = null;
        attributeProperties = null;
        derived = false;
        headerName = tableNameInDatabase.toLowerCase();
        className = ColumnSpec.convertTableNameToClassName(headerName);
        pkeyName = className + "PK";
        homeName = className + "Table";
        ifaceName = className;
        beanName = className + "Bean";
        ddFileName = className + "DD.txt";
        pkParamName = "p_" + ColumnSpec.uncapfirst(pkeyName);
        localName = "l_" + ColumnSpec.uncapfirst(className);
        procName = "new_" + headerName;
    }

    public TableSpec(String tableNameInDatabase, ColumnSpec columns[], String attributes)
    {
        this(tableNameInDatabase);
        for(int i = 0; i < columns.length; i++)
        {
            addColumn(columns[i]);
            try
            {
                if(columns[i].getIsPrimaryKeyComponent())
                    addPrimaryKey(columns[i].asHeader());
                if(columns[i].getForeignTableName() != null)
                    addForeignKey(columns[i].asHeader(), columns[i].getForeignTableName(), columns[i].getForeignColumnName());
            }
            catch(Exception e)
            {
                System.err.println("TableSpec.constructor(): " + e);
            }
        }

        this.attributes = attributes;
    }

    public ColumnSpec addColumn(String name, int sqlType, int size, int digits, boolean nullable)
    {
        name = name.toLowerCase();
        ColumnSpec cs = new ColumnSpec(name, sqlType, size, digits, nullable);
        addColumn(cs);
        return cs;
    }

    private void addColumn(ColumnSpec cs)
    {
        if(allColumnsByName.get(cs.asHeader()) != null)
        {
            System.err.println("warning: ignoring duplicate column " + cs.asHeader() + " spec in table " + headerName);
            return;
        }
        allColumns.addElement(cs);
        allColumnsByName.put(cs.asHeader(), cs);
        derived = false;
        if(!cs.getIsNullable())
            allNonNullableColumns.addElement(cs);
        derived = false;
    }

    public void addPrimaryKey(String keyName)
        throws Exception
    {
        keyName = keyName.toLowerCase();
        if(allPrimaryKeysByName.containsKey(keyName))
        {
            System.err.println("warning: ignoring duplicate primary key " + keyName + " spec in table " + headerName);
            return;
        }
        ColumnSpec col = (ColumnSpec)allColumnsByName.get(keyName);
        if(col == null)
        {
            throw new Exception("table " + headerName + ": primary key " + keyName + " not found");
        } else
        {
            allPrimaryKeysByName.put(keyName, col);
            col.setIsPrimaryKeyComponent(true);
            derived = false;
            return;
        }
    }

    public void addForeignKey(String fkName, String foreignTableName, List localNames, List foreignNames)
        throws Exception
    {
        Iterator it = localNames.iterator();
        Iterator ft = foreignNames.iterator();
        while(it.hasNext()) 
        {
            String localName = (String)it.next();
            String foreignName = (String)ft.next();
            ColumnSpec col = (ColumnSpec)allColumnsByName.get(localName);
            if(col == null)
                throw new Exception("Tablespec ERROR table " + headerName + ": foreign key column " + localName + " not found");
            col.setForeignKeyNames(foreignTableName, foreignName);
            allForeignKeys.addElement(col);
            allForeignKeysByName.put(localName, col);
            derived = false;
        }
        ForeignKeySpec fks = new ForeignKeySpec(fkName, this, foreignTableName, localNames, foreignNames);
        allForeignKeySpecs.addElement(fks);
    }

    public void addForeignKey(String s, String s1, String s2)
        throws Exception
    {
    }

    public ColumnSpec getColumnByName(String name)
    {
        return (ColumnSpec)allColumnsByName.get(name);
    }

    void resolveForeignKeyReferences(DataSpec ds)
    {
        ForeignKeySpec fks;
        for(Enumeration e = getForeignKeySpecs(); e.hasMoreElements(); fks.resolveReferences(ds))
            fks = (ForeignKeySpec)e.nextElement();

    }

    private void deriveAuxLists()
    {
        derivedPrimaryKeys = new Vector();
        derivedNonPKNonNullables = new Vector();
        derivedNonPrimaryKeys = new Vector();
        Enumeration e;
        for(e = allColumns.elements(); e.hasMoreElements();)
        {
            ColumnSpec cs = (ColumnSpec)e.nextElement();
            String columnName = cs.asHeader();
            boolean isPrimaryKeyComponent = null != allPrimaryKeysByName.get(columnName);
            if(isPrimaryKeyComponent)
                derivedPrimaryKeys.addElement(cs);
            else
                derivedNonPrimaryKeys.addElement(cs);
        }

        e = allNonNullableColumns.elements();
        do
        {
            if(!e.hasMoreElements())
                break;
            ColumnSpec cs = (ColumnSpec)e.nextElement();
            String columnName = cs.asHeader();
            boolean isPrimaryKeyComponent = null != allPrimaryKeysByName.get(columnName);
            if(!isPrimaryKeyComponent)
                derivedNonPKNonNullables.addElement(cs);
        } while(true);
        derivedForeignKeyTables = new Vector();
        derived = true;
    }

    public int hashCode()
    {
        return headerName.hashCode();
    }

    public String asHeader()
    {
        return headerName;
    }

    public String asClass()
    {
        return className;
    }

    public String asInterface()
    {
        return ifaceName;
    }

    public String asPKClass()
    {
        return pkeyName;
    }

    public String asPKType()
    {
        return hasPrimaryKeyComponents() ? pkeyName : "java.lang.String";
    }

    public String asBean()
    {
        return beanName;
    }

    public String asHome()
    {
        return homeName;
    }

    public String asDDFile()
    {
        return ddFileName;
    }

    public String asPKParam()
    {
        return pkParamName;
    }

    public String asLocal()
    {
        return localName;
    }

    public String asProc()
    {
        return procName;
    }

    public Enumeration getPrimaryKeyComponents()
    {
        if(!derived)
            deriveAuxLists();
        return derivedPrimaryKeys.elements();
    }

    public ColumnSpec getPrimaryKeyComponent(int index)
    {
        if(!derived)
            deriveAuxLists();
        return (ColumnSpec)derivedPrimaryKeys.elementAt(index);
    }

    public int getPrimaryKeyComponentCount()
    {
        if(!derived)
            deriveAuxLists();
        return derivedPrimaryKeys.size();
    }

    public boolean hasPrimaryKeyComponents()
    {
        if(!derived)
            deriveAuxLists();
        return derivedPrimaryKeys.size() > 0;
    }

    public Enumeration getNonPrimaryKeyComponents()
    {
        if(!derived)
            deriveAuxLists();
        return derivedNonPrimaryKeys.elements();
    }

    public Enumeration getNonPKCreateRequireds()
    {
        if(!derived)
            deriveAuxLists();
        return derivedNonPKNonNullables.elements();
    }

    public Enumeration getGetterColumns()
    {
        return allColumns.elements();
    }

    public Enumeration getSetterColumns()
    {
        if(!derived)
            deriveAuxLists();
        return derivedNonPrimaryKeys.elements();
    }

    public Enumeration getColumnElements()
    {
        return allColumns.elements();
    }

    public int getColumnCount()
    {
        return allColumns.size();
    }

    public Enumeration getForeignKeyColumns()
    {
        return allForeignKeys.elements();
    }

    public Enumeration getForeignKeySetterTables()
    {
        if(!derived)
            deriveAuxLists();
        return derivedForeignKeyTables.elements();
    }

    public Enumeration getForeignKeySpecs()
    {
        return allForeignKeySpecs.elements();
    }

    public Set getReverseForeignKeySpecs()
    {
        return allReverseForeignKeySpecs;
    }

    public void addReverseForeignKeySpec(ForeignKeySpec fks)
    {
        allReverseForeignKeySpecs.add(fks);
    }

    public void addIndex(IndexSpec is)
    {
        allIndexes.addElement(is);
    }

    public Enumeration getIndexSpecs()
    {
        return allIndexes.elements();
    }

    public String getAttributes()
    {
        return attributes;
    }

    public void setAttributes(String s)
        throws Exception
    {
        attributes = s;
        attributeProperties = null;
        if("true".equals(getAttributeValue("rowid_pk")))
        {
            addColumn("rowid", 12, 20, 20, false);
            addPrimaryKey("rowid");
        }
    }

    public boolean getIsPartitioned()
    {
        return getAttributeValue("p") != null;
    }

    public String getAttributeValue(String name)
    {
        if(attributeProperties == null)
        {
            if(attributes == null)
                return null;
            attributeProperties = toProperties(attributes);
        }
        return attributeProperties.getProperty(name);
    }

    private Properties toProperties(String s)
    {
        Properties p = new Properties();
        StringTokenizer pairs = new StringTokenizer(s, ";");
        do
        {
            if(!pairs.hasMoreTokens())
                break;
            String pair = pairs.nextToken();
            StringTokenizer tokens = new StringTokenizer(pair, "=");
            if(tokens.hasMoreTokens())
            {
                String name = tokens.nextToken();
                if(!tokens.hasMoreTokens())
                    throw new IllegalArgumentException(headerName + " attribute not of form 'a=b': " + pair);
                p.put(name, tokens.nextToken());
            }
        } while(true);
        return p;
    }

    public String[] getAttributeValues(String name)
    {
        if(attributes == null)
            return null;
        List list = new ArrayList();
        StringTokenizer pairs = new StringTokenizer(attributes, ";");
        do
        {
            if(!pairs.hasMoreTokens())
                break;
            StringTokenizer tokens = new StringTokenizer(pairs.nextToken(), "=");
            if(tokens.hasMoreTokens())
            {
                String key = tokens.nextToken();
                String value = tokens.hasMoreTokens() ? tokens.nextToken() : null;
                if(name.equals(key) && value != null && value.length() > 0)
                    list.add(value);
            }
        } while(true);
        if(list.size() <= 0)
            return null;
        else
            return (String[])list.toArray(new String[list.size()]);
    }

    private String headerName;
    private String className;
    private String pkeyName;
    private String homeName;
    private String ifaceName;
    private String beanName;
    private String ddFileName;
    private String pkParamName;
    private String localName;
    private String procName;
    private Vector allColumns;
    private Vector allNonNullableColumns;
    private Vector allForeignKeys;
    private Vector allForeignKeySpecs;
    private Hashtable allColumnsByName;
    private Hashtable allPrimaryKeysByName;
    private Hashtable allForeignKeysByName;
    private Set allReverseForeignKeySpecs;
    private Vector allIndexes;
    private Vector derivedPrimaryKeys;
    private Vector derivedNonPKNonNullables;
    private Vector derivedNonPrimaryKeys;
    private Vector derivedForeignKeyTables;
    private String attributes;
    private Properties attributeProperties;
    private boolean derived;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DatabaseReader.java

package com.fitechlabs.dbind.gen;

import java.io.PrintStream;
import java.sql.*;
import java.util.*;

// Referenced classes of package com.fitechlabs.dbind.gen:
//            TableSpec, IndexSpec, DataSpec

public class DatabaseReader
{

    public DatabaseReader()
    {
        jdbcClassName = "oracle.jdbc.driver.OracleDriver";
        dbConnectName = "jdbc:oracle:thin:@fig:1521:twoeuc";
        dbUsername = "jimtest0";
        dbPassword = "jimtest0";
    }

    public DatabaseReader(String className, String connectName, String databaseUser, String databasePassword)
    {
        jdbcClassName = "oracle.jdbc.driver.OracleDriver";
        dbConnectName = "jdbc:oracle:thin:@fig:1521:twoeuc";
        dbUsername = "jimtest0";
        dbPassword = "jimtest0";
        jdbcClassName = className == null ? "oracle.jdbc.driver.OracleDriver" : className;
        dbConnectName = connectName;
        if(databaseUser != null)
            dbUsername = databaseUser;
        if(databasePassword != null)
            dbPassword = databasePassword;
    }

    public void readDataSpec(DataSpec dataSpec, Hashtable tables)
    {
        Connection connection = null;
        System.out.println("reading schema info from url=" + dbConnectName + ", user=" + dbUsername + ", password=" + dbPassword);
        try
        {
            Class.forName(jdbcClassName);
            connection = DriverManager.getConnection(dbConnectName, dbUsername, dbPassword);
            addTablesUsing(dataSpec, tables, connection, "select table_name from user_tables", true);
            addTablesUsing(dataSpec, tables, connection, "select view_name from user_views", false);
        }
        catch(Exception e)
        {
            System.err.println("DatabaseReader: exception reading from database: " + e);
            System.err.println("DatabaseReader: database connection settings are (" + jdbcClassName + ", " + dbConnectName + "): " + e);
            dataSpec = null;
        }
        finally
        {
            if(connection != null)
                try
                {
                    connection.close();
                    System.out.println("DatabaseReader: connection closed");
                }
                catch(Exception e)
                {
                    System.out.println("DatabaseReader: failed to close connection: " + e);
                }
        }
        dataSpec.resolveForeignKeyReferences();
    }

    private void addTablesUsing(DataSpec dataSpec, Hashtable tables, Connection connection, String sql, boolean addIndexes)
        throws Exception
    {
        Vector tableNames = new Vector();
        Statement statement = connection.createStatement();
        statement.execute(sql);
        ResultSet rs = statement.getResultSet();
        do
        {
            if(!rs.next())
                break;
            String s = rs.getString(1);
            if(tables == null || tables.size() == 0 || tables.get(s.toLowerCase()) != null || tables.get(s.toUpperCase()) != null)
                tableNames.addElement(s);
        } while(true);
        rs.close();
        rs = null;
        DatabaseMetaData dbmd = connection.getMetaData();
        String schemaPattern = dbUsername.toUpperCase();
        String catalogPattern = null;
        Enumeration e = tableNames.elements();
        do
        {
            if(!e.hasMoreElements())
                break;
            String tableName = (String)e.nextElement();
            TableSpec tableSpec = new TableSpec(tableName);
            String columnName;
            short dataType;
            int columnSize;
            int decimalDigits;
            boolean nullable;
            for(rs = dbmd.getColumns(catalogPattern, schemaPattern, tableName, null); rs.next(); tableSpec.addColumn(columnName, dataType, columnSize, decimalDigits, nullable))
            {
                String catalog = rs.getString("TABLE_CAT");
                String schema = rs.getString("TABLE_SCHEM");
                columnName = rs.getString("COLUMN_NAME");
                dataType = rs.getShort("DATA_TYPE");
                columnSize = rs.getInt("COLUMN_SIZE");
                decimalDigits = rs.getInt("DECIMAL_DIGITS");
                nullable = rs.getString("IS_NULLABLE").equalsIgnoreCase("YES");
            }

            rs.close();
            try
            {
                String keyName;
                for(rs = dbmd.getPrimaryKeys(catalogPattern, schemaPattern, tableName); rs.next(); tableSpec.addPrimaryKey(keyName))
                    keyName = rs.getString("COLUMN_NAME");

                rs.close();
            }
            catch(Exception ep)
            {
                System.out.println("DatabaseReader: exception getting primary keys: " + ep);
            }
            dataSpec.addTableSpec(tableSpec);
            if(addIndexes)
                addIndexInfo(dataSpec, dbmd, catalogPattern, schemaPattern, tableName, tableSpec);
        } while(true);
        for(e = tableNames.elements(); e.hasMoreElements();)
        {
            String tableName = (String)e.nextElement();
            TableSpec tableSpec = dataSpec.getTableNamed(tableName);
            if(tableSpec == null)
                System.err.println("can't find table named " + tableName);
            else
                try
                {
                    String fkName;
                    String pkTableName;
                    List localNames;
                    List foreignNames;
                    for(rs = dbmd.getImportedKeys(catalogPattern, schemaPattern, tableName); rs.next(); tableSpec.addForeignKey(fkName, pkTableName, localNames, foreignNames))
                    {
                        fkName = rs.getString("FK_NAME");
                        String fkTableName = rs.getString("FKTABLE_NAME");
                        String fkColumnName = rs.getString("FKCOLUMN_NAME");
                        pkTableName = rs.getString("PKTABLE_NAME");
                        String pkColumnName = rs.getString("PKCOLUMN_NAME");
                        localNames = new ArrayList(1);
                        foreignNames = new ArrayList(1);
                        localNames.add(fkColumnName.toLowerCase());
                        foreignNames.add(pkColumnName.toLowerCase());
                    }

                    rs.close();
                }
                catch(Exception ep)
                {
                    System.err.println("DatabaseReader: foreign key exception: " + ep);
                }
        }

    }

    private void addIndexInfo(DataSpec dataSpec, DatabaseMetaData dbmd, String catalogPattern, String schemaPattern, String tableName, TableSpec table)
    {
        try
        {
            System.out.println("DatabaseReader: getting index information for " + tableName);
            ResultSet rs = dbmd.getIndexInfo(catalogPattern, schemaPattern, tableName, false, false);
            Map byIndexName = new HashMap();
            do
            {
                if(!rs.next())
                    break;
                String indexName = rs.getString("INDEX_NAME");
                if(indexName != null)
                {
                    boolean nonUnique = rs.getBoolean("NON_UNIQUE");
                    String columnName = rs.getString("COLUMN_NAME");
                    columnName = columnName.toLowerCase();
                    System.out.println("  index " + indexName + ", table " + tableName + ", adding column " + columnName);
                    Object info[] = (Object[])byIndexName.get(indexName);
                    if(info == null)
                    {
                        info = (new Object[] {
                            nonUnique ? Boolean.TRUE : Boolean.FALSE, new ArrayList()
                        });
                        byIndexName.put(indexName, ((Object) (info)));
                    }
                    List columnNames = (List)info[1];
                    columnNames.add(columnName);
                }
            } while(true);
            rs.close();
            IndexSpec index;
            for(Iterator it = byIndexName.entrySet().iterator(); it.hasNext(); index.resolveReferences(dataSpec))
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
                String indexName = (String)entry.getKey();
                Object info[] = (Object[])entry.getValue();
                boolean nonUnique = ((Boolean)info[0]).booleanValue();
                List columnNames = (List)info[1];
                index = new IndexSpec(indexName, tableName, !nonUnique, columnNames);
                table.addIndex(index);
            }

        }
        catch(Exception ep)
        {
            System.err.println("DatabaseReader: index exception: " + ep);
        }
    }

    private static void printall(ResultSet rs)
        throws Exception
    {
        ResultSetMetaData rsmd = rs.getMetaData();
        for(int i = 0; i < rsmd.getColumnCount(); i++)
        {
            String name = rsmd.getColumnName(i + 1);
            String label = rsmd.getColumnLabel(i + 1);
            Object value = rs.getObject(i + 1);
            System.out.println("   -- name=" + name + " label=" + label + " value=" + value);
        }

    }

    public static void main(String args[])
        throws Exception
    {
        DatabaseReader reader = new DatabaseReader("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@fig:1521:twoeuc", "jimtest0", "jimtest0");
        DataSpec ds = new DataSpec();
        Hashtable tables = null;
        reader.readDataSpec(ds, tables);
        System.out.println("DataSpec: " + ds);
    }

    private static final boolean DEBUG_TABLES = false;
    private static final boolean DEBUG_COLUMNS = false;
    private static final boolean DEBUG_PKS = false;
    private static final boolean DEBUG_FKS = false;
    private static final boolean DEBUG_INDEXES = true;
    private static final String defaultJdbcClassName = "oracle.jdbc.driver.OracleDriver";
    private static final String defaultDbConnectName = "jdbc:oracle:thin:@fig:1521:twoeuc";
    private static final String defaultDbUsername = "jimtest0";
    private static final String defaultDbPassword = "jimtest0";
    private String jdbcClassName;
    private String dbConnectName;
    private String dbUsername;
    private String dbPassword;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExcelCsvReader.java

package com.fitechlabs.dbind.gen;

import java.io.*;
import java.util.*;

// Referenced classes of package com.fitechlabs.dbind.gen:
//            TableSpec, DataSpec

public class ExcelCsvReader
{

    public ExcelCsvReader()
    {
        schemaCsvFileName = "dao.csv";
    }

    public ExcelCsvReader(String name)
    {
        schemaCsvFileName = "dao.csv";
        schemaCsvFileName = name;
    }

    public void readDataSpec(DataSpec dataSpec)
    {
        readDataSpec(dataSpec, 0);
    }

    public void readDataSpec(DataSpec dataSpec, int maxColumnCount)
    {
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(schemaCsvFileName));
            System.out.println("ExcelCsvReader: reading from file: " + schemaCsvFileName + ", skipping first " + 2 + " lines");
            for(int skipped = 0; skipped < 2; skipped++)
                reader.readLine();

            String priorTableName = "";
            TableSpec tableSpec = null;
            int count = 0;
            do
            {
                String line;
                if((line = reader.readLine()) == null)
                    break;
                String fields[] = fieldsFromLine(line);
                String tableName = fields[0];
                String fieldName = fields[1];
                String fieldDataType = fields[2];
                String nullNotNull = fields[3];
                String primaryKey = fields[4];
                String foreignKey = fields[5];
                if(!priorTableName.equals(tableName))
                {
                    if(maxColumnCount > 0 && ++count > maxColumnCount)
                        break;
                    tableSpec = new TableSpec(tableName);
                    dataSpec.addTableSpec(tableSpec);
                    priorTableName = tableName;
                }
                try
                {
                    String s[] = subFields(fieldDataType);
                    Integer isqlType = (Integer)sqlTypeByName.get(s[0]);
                    if(isqlType == null)
                    {
                        System.err.println("unrecognized type \"" + fieldDataType + "\"");
                        System.err.println("table " + tableSpec.asHeader() + ", field " + fieldName);
                        System.err.println("source line: \"" + line + "\"");
                    } else
                    {
                        int sqlType = isqlType.intValue();
                        int size = s.length > 1 ? (new Integer(s[1])).intValue() : 0;
                        int digits = s.length > 2 ? (new Integer(s[2])).intValue() : 0;
                        boolean nullable = nullNotNull.equalsIgnoreCase("NULL");
                        tableSpec.addColumn(fieldName, sqlType, size, digits, nullable);
                        if(primaryKey.equalsIgnoreCase("Yes"))
                            tableSpec.addPrimaryKey(fieldName);
                        if(foreignKey.equalsIgnoreCase("Yes"))
                        {
                            String tbl = fields.length <= 6 ? "unspecified_foreign_tbl" : fields[6];
                            String col = fields.length <= 7 ? "unspecified_foreign_column" : fields[7];
                            tableSpec.addForeignKey(fieldName, tbl, col);
                        }
                    }
                }
                catch(Exception e) { }
            } while(true);
        }
        catch(Exception e)
        {
            System.out.println("ExcelCsvReader: exception reading from file: " + e);
            System.out.println("ExcelCsvReader: file settings are (" + schemaCsvFileName + "): ");
            e.printStackTrace();
            dataSpec = null;
        }
        finally
        {
            if(reader != null)
                try
                {
                    reader.close();
                }
                catch(Exception e)
                {
                    System.err.println("failed to close input file: " + e);
                }
        }
    }

    String[] fieldsFromLine(String line)
    {
        String r[] = new String[8];
        int beg = 0;
        int i = 0;
        do
        {
            if(i >= r.length || beg >= line.length())
                break;
            int end;
            if(line.charAt(beg) == '"')
            {
                beg++;
                end = line.indexOf('"', beg);
                r[i] = line.substring(beg, end);
                end++;
            } else
            {
                end = line.indexOf(',', beg);
                if(end < 0)
                    end = line.length();
                r[i] = line.substring(beg, end);
            }
            if(end < line.length())
            {
                if(line.charAt(end) == ',')
                {
                    beg = end + 1;
                } else
                {
                    i++;
                    break;
                }
            } else
            {
                i++;
                break;
            }
            i++;
        } while(true);
        for(; i < r.length; i++)
            r[i] = "";

        return r;
    }

    String[] subFields(String field)
    {
        StringTokenizer st = new StringTokenizer(field, "(,)");
        Vector v = new Vector();
        for(; st.hasMoreTokens(); v.addElement(st.nextToken()));
        String r[] = new String[v.size()];
        v.copyInto(r);
        return r;
    }

    public static final int schemaCsvSkipLineCount = 2;
    public static final int maxFieldsPerString = 8;
    public String schemaCsvFileName;
    static Object typeMappings[][] = {
        {
            "CHAR", new Integer(12)
        }, {
            "DATE", new Integer(91)
        }, {
            "TIMESTAMP", new Integer(93)
        }, {
            "NUMERIC", new Integer(2)
        }, {
            "DECIMAL", new Integer(3)
        }, {
            "VARCHAR", new Integer(12)
        }, {
            "VARCHAR2", new Integer(12)
        }
    };
    static Hashtable sqlTypeByName;

    static 
    {
        sqlTypeByName = new Hashtable();
        for(int i = 0; i < typeMappings.length; i++)
            sqlTypeByName.put(typeMappings[i][0], typeMappings[i][1]);

    }
}

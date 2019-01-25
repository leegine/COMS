// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SqlReader.java

package com.fitechlabs.dbind.gen;

import com.fitechlabs.dbind.gen.parser.CreateTableParser;
import java.io.*;
import java.util.*;

// Referenced classes of package com.fitechlabs.dbind.gen:
//            TableSpec, ForeignKeySpec, IndexSpec, ColumnSpec, 
//            Settings, DataSpec

public class SqlReader
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

    public SqlReader(String filename)
    {
        filenames = null;
        StringTokenizer st = new StringTokenizer(filename, ",; \t\n\r");
        List files = new ArrayList();
        for(; st.hasMoreTokens(); files.add(st.nextToken()));
        filenames = new String[files.size()];
        for(int i = 0; i < filenames.length; i++)
            filenames[i] = Settings.getAbsPath((String)files.get(i));

    }

    private static int sqlTypeFromString(String typeName)
    {
        Object lookup = sqlTypeByName.get(typeName.toUpperCase());
        if(lookup == null)
        {
            error("type '" + typeName + "' not recognized, using VARCHAR");
            return 12;
        } else
        {
            return ((Integer)lookup).intValue();
        }
    }

    public void readDataSpec(DataSpec dataSpec, Hashtable tablesToInclude)
    {
        String statement;
        LineNumberReader reader = null;
        statement = null;
        debug("tablesToInclude: " + tablesToInclude);
        int i = 0;
_L2:
        String filename;
        File file;
        if(i >= filenames.length)
            break MISSING_BLOCK_LABEL_366;
        filename = filenames[i];
        file = new File(filename);
        if(!file.exists())
        {
            error("COULD NOT FIND FILE '" + filename);
            return;
        }
        FileReader frdr = new FileReader(file);
        LineNumberReader reader = new LineNumberReader(frdr);
        status("reading from file: " + filename);
        StringBuffer stringBuffer = new StringBuffer();
        do
        {
            String line;
            if((line = reader.readLine()) == null)
                break;
            if(line.indexOf("--") < 0)
                stringBuffer.append(" " + line);
        } while(true);
        String contents = stringBuffer.toString();
        reader.close();
        int beg = contents.indexOf("/*");
        if(beg >= 0)
        {
            StringBuffer sb = new StringBuffer();
            int end = 0;
            for(; beg >= 0; beg = contents.indexOf("/*", end))
            {
                sb.append(contents.substring(end, beg));
                end = contents.indexOf("*/", beg + 2);
                if(end < 0)
                {
                    end = sb.length();
                    error("unterminated c-style comment '/*'.");
                } else
                {
                    end += 2;
                }
            }

            sb.append(contents.substring(end));
            contents = sb.toString();
        }
        for(StringTokenizer st = new StringTokenizer(contents, ";"); st.hasMoreTokens(); handleStatement(statement, dataSpec, tablesToInclude))
            statement = st.nextToken();

        i++;
        if(true) goto _L2; else goto _L1
_L1:
        for(Enumeration ee = dataSpec.getTableSpecElements(); ee.hasMoreElements();)
        {
            TableSpec ts = (TableSpec)ee.nextElement();
            Enumeration ff = ts.getForeignKeySpecs();
            while(ff.hasMoreElements()) 
            {
                ForeignKeySpec fks = (ForeignKeySpec)ff.nextElement();
                fks.resolveReferences(dataSpec);
            }
        }

        break MISSING_BLOCK_LABEL_481;
        Exception e;
        e;
        error("SqlReader: exception reading file: " + e + "\n\tlast statment: " + statement);
        e.printStackTrace();
        dataSpec = null;
    }

    private void handleStatement(String statement, DataSpec ds, Hashtable tablesToInclude)
        throws Exception
    {
        StringTokenizer st = new StringTokenizer(statement, "( \t\n\r");
        if(!st.hasMoreTokens())
            return;
        String verb = st.nextToken();
        if(!st.hasMoreTokens())
            return;
        String noun = st.nextToken();
        String name = null;
        if(st.hasMoreTokens())
            name = st.nextToken();
        name = name.toLowerCase();
        if(verb.equalsIgnoreCase("create"))
        {
            if(noun.equalsIgnoreCase("table"))
            {
                debug("checking whether to include table named " + name);
                TableSpec ts = new TableSpec(name);
                if(tablesToInclude == null || tablesToInclude.containsKey(name.toLowerCase()))
                    ds.addTableSpec(ts);
                else
                    ds.addImportTableSpec(ts);
                handleCreateTableUseParser(ts, statement, ds);
            } else
            if(noun.equalsIgnoreCase("unique") || noun.equalsIgnoreCase("index"))
                handleCreateIndex(statement, ds);
            else
            if(noun.equalsIgnoreCase("view"))
            {
                TableSpec ts = handleCreateView(statement, ds);
                if(ts != null)
                    if(tablesToInclude == null || tablesToInclude.containsKey(name.toLowerCase()))
                        ds.addTableSpec(ts);
                    else
                        ds.addImportTableSpec(ts);
            }
        } else
        if(verb.equalsIgnoreCase("alter") && noun.equalsIgnoreCase("table"))
        {
            TableSpec ts = ds.getTableOrImportNamed(name);
            handleAlterTable(ts, statement, ds);
        }
    }

    private void handleCreateIndex(String statement, DataSpec ds)
        throws Exception
    {
        debug("============================================================");
        debug("index statement: " + statement);
        debug("------------------------------------------------------------");
        StringTokenizer st = new StringTokenizer(statement, "( \t\n\r");
        String indexName = null;
        String on = null;
        boolean unique = false;
        do
        {
            if(!st.hasMoreTokens() || "on".equalsIgnoreCase(on))
                break;
            indexName = on;
            on = st.nextToken();
            if("unique".equalsIgnoreCase(on))
                unique = true;
        } while(true);
        if("index".equalsIgnoreCase(indexName))
            indexName = null;
        String tableName = st.hasMoreTokens() ? st.nextToken() : "unnamed";
        int a = statement.indexOf('(') + 1;
        int b = statement.lastIndexOf(')');
        if(a < 0 || b < a)
        {
            error("index statement contains no body: " + statement);
            return;
        }
        String stmt = statement.substring(a, b);
        List columnList = new ArrayList();
        int end = 0;
        do
        {
            int beg = end;
            end = nextFieldSeparatorOrEnd(stmt, beg);
            String argument = stmt.substring(beg, end);
            end++;
            debug("  argument: --------->|" + argument + "|<-------");
            StringTokenizer fields = new StringTokenizer(argument, " \t\n\r");
            if(!fields.hasMoreTokens())
            {
                warn("null table spec, statement='" + statement + "', ignoring");
                return;
            }
            String columnName = fields.nextToken();
            columnList.add(columnName.toLowerCase());
        } while(end < stmt.length());
        IndexSpec is = new IndexSpec(indexName, tableName, unique, columnList);
        TableSpec table = ds.getTableOrImportNamed(tableName);
        if(table == null)
            warn("table '" + tableName + "' named by index '" + indexName + "' cannot be found.");
        else
        if(is.resolveReferences(ds))
            table.addIndex(is);
    }

    private static int nextOrEnd(String string, String pattern, int start)
    {
        int i = string.indexOf(pattern, start);
        return i < 0 ? string.length() : i;
    }

    private static int nextCommaOrEnd(String string, int start)
    {
        return nextOrEnd(string, ",", start);
    }

    private static int nextLeftParenOrEnd(String string, int start)
    {
        return nextOrEnd(string, "(", start);
    }

    private static int nextRightParenOrEnd(String string, int start)
    {
        return nextOrEnd(string, ")", start);
    }

    static int matchingRightParenOrEnd(String string, int start)
    {
        int l = nextLeftParenOrEnd(string, start);
        int r = nextRightParenOrEnd(string, start);
        if(l < r)
            r = matchingRightParenOrEnd(string, l + 1);
        return r;
    }

    private static int nextFieldSeparatorOrEnd(String string, int start)
    {
        int c = nextCommaOrEnd(string, start);
        for(int p = nextLeftParenOrEnd(string, start); p < c; p = nextLeftParenOrEnd(string, p))
        {
            p = matchingRightParenOrEnd(string, p + 1);
            c = nextCommaOrEnd(string, p);
        }

        return c;
    }

    static int matchRight(String string, int start)
    {
        int l = nextLeftParenOrEnd(string, start);
        int r = nextRightParenOrEnd(string, start);
        if(l < r)
            return matchRight(string, matchRight(string, l + 1) + 1);
        else
            return r;
    }

    private void handleCreateTable(TableSpec tableSpec, String statement, DataSpec ds)
        throws Exception
    {
        int left = statement.indexOf('(');
        int right = matchRight(statement, left + 1);
        String stmt = statement.substring(left + 1, right);
        int end = 0;
        debug("============================================================");
        debug("statement: " + statement);
        debug("------------------------------------------------------------");
        do
        {
            int beg = end;
            end = nextFieldSeparatorOrEnd(stmt, beg);
            String argument = stmt.substring(beg, end);
            end++;
            debug("  argument: --------->|" + argument + "|<-------");
            StringTokenizer fields = new StringTokenizer(argument, " \t\n\r");
            if(!fields.hasMoreTokens())
            {
                warn("null table spec, statement='" + statement + "', ignoring");
                return;
            }
            String columnName = fields.nextToken();
            boolean hasDefault = false;
            if(columnName.equalsIgnoreCase("constraint"))
            {
                checkPrimaryForeignKeys(tableSpec, argument, ds);
            } else
            {
                String type = fields.nextToken();
                boolean isNullable = true;
                if(fields.hasMoreTokens())
                {
                    String nextField = fields.nextToken();
                    if(type.equalsIgnoreCase("LONG"))
                    {
                        type = type + " " + nextField;
                    } else
                    {
                        boolean hasNot = false;
                        boolean hasNull = false;
                        do
                        {
                            if(nextField.equalsIgnoreCase("NOT"))
                                hasNot = true;
                            else
                            if(nextField.equalsIgnoreCase("NULL"))
                                hasNull = true;
                            else
                            if(nextField.equalsIgnoreCase("DEFAULT"))
                                hasDefault = true;
                            nextField = fields.hasMoreTokens() ? fields.nextToken() : null;
                        } while(nextField != null);
                        isNullable = !hasNot || !hasNull;
                    }
                }
                int size = 0;
                int digits = 0;
                if(type.indexOf('(') > 0)
                {
                    String precis = type.substring(type.indexOf('(') + 1, type.indexOf(')'));
                    StringTokenizer numbers = new StringTokenizer(precis, ", \t\n\r");
                    size = Integer.valueOf(numbers.nextToken()).intValue();
                    if(numbers.hasMoreTokens())
                        digits = Integer.valueOf(numbers.nextToken()).intValue();
                    type = type.substring(0, type.indexOf('('));
                }
                int sqlType = sqlTypeFromString(type);
                ColumnSpec cs = tableSpec.addColumn(columnName, sqlType, size, digits, isNullable);
                if(hasDefault)
                    cs.setHasDatabaseDefaultValue(true);
            }
        } while(end < stmt.length());
    }

    private void handleCreateTableUseParser(TableSpec tableSpec, String statement, DataSpec ds)
        throws Exception
    {
        debug("============================================================");
        debug("statement: " + statement);
        debug("------------------------------------------------------------");
        com.fitechlabs.dbind.gen.parser.CreateTableParser.Table table = CreateTableParser.handle(statement);
        Iterator it = table.columns.iterator();
        do
        {
            if(!it.hasNext())
                break;
            com.fitechlabs.dbind.gen.parser.CreateTableParser.Column col = (com.fitechlabs.dbind.gen.parser.CreateTableParser.Column)it.next();
            String columnName = col.name;
            int sqlType = sqlTypeFromString(col.type);
            int size = col.precision == null ? 0 : col.precision.intValue();
            int digits = col.scale == null ? 0 : col.scale.intValue();
            boolean isNullable = col.nullable == null ? true : col.nullable.booleanValue();
            ColumnSpec cs = tableSpec.addColumn(columnName, sqlType, size, digits, isNullable);
            if(col.hasDefault)
                cs.setHasDatabaseDefaultValue(true);
        } while(true);
        for(it = table.pkColumns.iterator(); it.hasNext(); tableSpec.addPrimaryKey((String)it.next()));
        String fkName;
        String fkTableName;
        List local;
        List foreign;
        for(it = table.fks.iterator(); it.hasNext(); tableSpec.addForeignKey(fkName, fkTableName, local, foreign))
        {
            com.fitechlabs.dbind.gen.parser.CreateTableParser.FK fk = (com.fitechlabs.dbind.gen.parser.CreateTableParser.FK)it.next();
            fkName = fk.constraintName;
            System.out.println(fkName);
            fkTableName = fk.parentName.toLowerCase();
            local = new ArrayList();
            foreign = new ArrayList();
            for(Iterator it2 = fk.columns.iterator(); it2.hasNext(); local.add(((String)it2.next()).toLowerCase()));
            if(fk.parentColumns.size() == 0)
            {
                for(Iterator it2 = fk.columns.iterator(); it2.hasNext(); foreign.add(((String)it2.next()).toLowerCase()));
                continue;
            }
            for(Iterator it2 = fk.parentColumns.iterator(); it2.hasNext(); foreign.add(((String)it2.next()).toLowerCase()));
        }

    }

    private void handleAlterTable(TableSpec tableSpec, String stmt, DataSpec ds)
        throws Exception
    {
        StringTokenizer st = new StringTokenizer(stmt, " \t\n\r");
        st.nextToken();
        st.nextToken();
        st.nextToken();
        if(!st.hasMoreTokens())
            return;
        if(!st.nextToken().equalsIgnoreCase("add"))
        {
            return;
        } else
        {
            checkPrimaryForeignKeys(tableSpec, stmt, ds);
            return;
        }
    }

    private void checkPrimaryForeignKeys(TableSpec tableSpec, String stmt, DataSpec ds)
        throws Exception
    {
        int i = stmt.indexOf("PRIMARY");
        if(i < 0)
            i = stmt.indexOf("primary");
        if(i > 0)
        {
            String args = stmt.substring(i, stmt.length());
            args = args.substring(args.indexOf('(') + 1, args.indexOf(')'));
            String column;
            for(StringTokenizer cols = new StringTokenizer(args, ", \t\n\r"); cols.hasMoreTokens(); tableSpec.addPrimaryKey(column))
                column = cols.nextToken();

            return;
        }
        i = stmt.indexOf("FOREIGN");
        if(i < 0)
            i = stmt.indexOf("foreign");
        if(i > 0)
        {
            String args = stmt.substring(i);
            args = args.substring(args.indexOf('(') + 1, args.indexOf(')'));
            int irefs = stmt.indexOf("REFERENCES");
            if(irefs < 0)
                irefs = stmt.indexOf("references");
            String refs = stmt.substring(irefs, stmt.length());
            StringTokenizer reftok = new StringTokenizer(refs, "() \t\n\r");
            reftok.nextToken();
            String fkTableName = reftok.nextToken().toLowerCase();
            int ri = refs.indexOf('(') + 1;
            int rj = refs.indexOf(')');
            String fkargs = ri <= 0 || rj <= ri ? "" : refs.substring(ri, rj);
            StringTokenizer cols = new StringTokenizer(args, ", \t\n\r");
            StringTokenizer fkcols = new StringTokenizer(fkargs, ", \t\n\r");
            List local = new ArrayList();
            List foreign = new ArrayList();
            for(int index = 0; cols.hasMoreTokens(); index++)
            {
                String column = cols.nextToken().toLowerCase();
                String fkcolumn = fkcols.hasMoreTokens() ? fkcols.nextToken().toLowerCase() : column;
                local.add(column);
                foreign.add(fkcolumn);
            }

            String fkName = null;
            int j = stmt.indexOf("CONSTRAINT");
            if(j < 0)
                j = stmt.indexOf("constraint");
            if(j >= 0)
            {
                StringTokenizer st = new StringTokenizer(stmt.substring(j), " \n\t\r");
                st.nextToken();
                if(st.hasMoreTokens())
                {
                    fkName = st.nextToken();
                    if("foreign".equalsIgnoreCase(fkName))
                        fkName = null;
                }
            }
            tableSpec.addForeignKey(fkName, fkTableName, local, foreign);
            return;
        } else
        {
            return;
        }
    }

    private TableSpec handleCreateView(String statement, DataSpec ds)
    {
        Map variables;
        debug("============================================================");
        debug("view statement: " + statement);
        debug("------------------------------------------------------------");
        variables = new HashMap();
        String name;
        ColumnSpec columns[];
        StringTokenizer st = new StringTokenizer(statement, ", \t\n\r");
        String create = st.nextToken();
        String view = st.nextToken();
        name = st.nextToken();
        String as = st.nextToken();
        String select = st.nextToken();
        if(!"select".equalsIgnoreCase(select))
            throw new IllegalArgumentException("no SELECT keyword");
        List fields = between(statement, "select", "from", false);
        List tables = between(statement, "from", "where", true);
        if(fields.size() == 0)
            throw new IllegalArgumentException("illegal view - no fields");
        if(tables.size() == 0)
            throw new IllegalArgumentException("illegal view - no tables");
        String alias;
        TableSpec table;
        for(Iterator it = tables.iterator(); it.hasNext(); variables.put(alias.toLowerCase(), table))
        {
            String names[] = (String[])it.next();
            String tablename = names[0];
            alias = names[1];
            table = ds.getTableOrImportNamed(tablename);
            if(table == null)
                throw new IllegalArgumentException("table '" + tablename + "' not found");
        }

        int n = fields.size();
        columns = new ColumnSpec[n];
        for(int i = 0; i < n; i++)
        {
            String names[] = (String[])fields.get(i);
            String columnname = names[0];
            String alias = names[1];
            alias = alias.substring(alias.lastIndexOf('.') + 1);
            int j = columnname.lastIndexOf('.');
            ColumnSpec columnref = null;
            if(j >= 0)
            {
                String tablename = columnname.substring(0, j);
                String columnid = columnname.substring(j + 1);
                TableSpec table = (TableSpec)variables.get(tablename.toLowerCase());
                if(null == table)
                    throw new IllegalArgumentException("table '" + tablename + "' not found");
                columnref = table.getColumnByName(columnid.toLowerCase());
                if(null == columnref)
                    throw new IllegalArgumentException("column '" + columnid + "' not found in table '" + tablename + "'");
            } else
            {
                Iterator it = variables.values().iterator();
                do
                {
                    if(!it.hasNext())
                        break;
                    TableSpec table = (TableSpec)it.next();
                    ColumnSpec cs = table.getColumnByName(columnname.toLowerCase());
                    if(cs != null)
                        if(columnref == null)
                            columnref = cs;
                        else
                            throw new IllegalArgumentException("column name '" + columnname + "' is ambiguous");
                } while(true);
                if(columnref == null)
                    throw new IllegalArgumentException("column '" + columnname + "' not found in any table");
            }
            columns[i] = new ColumnSpec(columnref, alias);
        }

        return new TableSpec(name, columns, null);
        Exception e;
        e;
        error("VIEW statement failed, " + e.toString() + ", statement='" + statement + "'");
        e.printStackTrace();
        return null;
    }

    private static final List between(String statement, String begin, String end, boolean autocomplete)
    {
        String lowercase = statement.toLowerCase().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ');
        String b = " " + begin + " ";
        String e = " " + end + " ";
        int frm = lowercase.indexOf(b);
        int whr = lowercase.indexOf(e, frm + 1);
        if(whr < 0)
            if(autocomplete)
                whr = lowercase.length();
            else
                throw new IllegalArgumentException("keyword '" + end + "' not found");
        String commalist = statement.substring(frm + b.length(), whr);
        debug("list: " + commalist);
        StringTokenizer st = new StringTokenizer(commalist, ",");
        List list = new ArrayList(st.countTokens());
        String lhs;
        String rhs;
        for(; st.hasMoreTokens(); list.add(new String[] {
    lhs, rhs
}))
        {
            String clause = st.nextToken();
            StringTokenizer ct = new StringTokenizer(clause, ", \t\n\r");
            lhs = ct.nextToken();
            rhs = ct.hasMoreTokens() ? ct.nextToken() : lhs;
        }

        return list;
    }

    private String filenames[];
    static Object typeMappings[][] = {
        {
            "CHAR", new Integer(12)
        }, {
            "DATE", new Integer(93)
        }, {
            "DATETIME", new Integer(93)
        }, {
            "TIME", new Integer(92)
        }, {
            "TIMESTAMP", new Integer(93)
        }, {
            "NUMERIC", new Integer(2)
        }, {
            "NUMBER", new Integer(2)
        }, {
            "INTEGER", new Integer(2)
        }, {
            "DECIMAL", new Integer(3)
        }, {
            "VARCHAR", new Integer(12)
        }, {
            "VARCHAR2", new Integer(12)
        }, {
            "LONG", new Integer(-4)
        }, {
            "LONG RAW", new Integer(-4)
        }, {
            "LONG VARCHAR", new Integer(-1)
        }
    };
    static Hashtable sqlTypeByName;
    private static final String whitespace = " \t\n\r";
    private static final String commasAndWhitespace = ", \t\n\r";
    private static final String closeParensAndWhitespace = ") \t\n\r";
    private static final String openParensAndWhitespace = "( \t\n\r";
    private static final String parensAndWhitespace = "() \t\n\r";

    static 
    {
        sqlTypeByName = new Hashtable();
        for(int i = 0; i < typeMappings.length; i++)
            sqlTypeByName.put(typeMappings[i][0], typeMappings[i][1]);

    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataSpecWriter.java

package com.fitechlabs.dbind.gen;

import java.io.*;
import java.util.*;

// Referenced classes of package com.fitechlabs.dbind.gen:
//            TableSpec, ColumnSpec, Settings, DataSpec

public class DataSpecWriter
{

    public DataSpecWriter()
    {
    }

    public void write(DataSpec ds)
        throws IOException
    {
        String className = "DataSpecInstance";
        String processor = Settings.getProcessor();
        if(known.contains(processor))
            className = ColumnSpec.toCapName(processor) + className;
        String dir = Settings.getAbsPath(Settings.beanSourceRelPath());
        (new File(dir)).mkdirs();
        String path = dir + className + ".java";
        System.out.println("creating " + path);
        PrintWriter pw = new PrintWriter(new FileOutputStream(path));
        pw.println("package " + Settings.beanPackageName() + ";");
        pw.println();
        pw.println("import com.fitechlabs.dbind.gen.*;");
        pw.println("import java.sql.Types;");
        pw.println();
        pw.println("public class " + className + " extends DataSpec {");
        pw.println();
        for(Enumeration et = ds.getTableSpecElements(); et.hasMoreElements(); pw.println("  };"))
        {
            TableSpec ts = (TableSpec)et.nextElement();
            pw.println("  private static final ColumnSpec " + ts.asHeader() + "_columns[] = {");
            ColumnSpec cs;
            for(Enumeration ec = ts.getColumnElements(); ec.hasMoreElements(); pw.println("    new ColumnSpec(\"" + cs.asHeader() + "\"," + toSqlTypeName(cs.getSqlType()) + "," + cs.columnSize() + "," + cs.decimalDigits() + "," + cs.getIsNullable() + "," + cs.getIsPrimaryKeyComponent() + "," + (cs.getForeignTableName() == null ? "null" : "\"" + cs.getForeignTableName() + "\"") + "," + (cs.getForeignColumnName() == null ? "null" : "\"" + cs.getForeignColumnName() + "\"") + "),"))
                cs = (ColumnSpec)ec.nextElement();

        }

        pw.println();
        pw.println("  private static final TableSpec tables[] = {");
        TableSpec ts;
        for(Enumeration et = ds.getTableSpecElements(); et.hasMoreElements(); pw.println("    new TableSpec(\"" + ts.asHeader() + "\"," + ts.asHeader() + "_columns," + (ts.getAttributes() == null ? "null" : "\"" + ts.getAttributes() + "\"") + "),"))
            ts = (TableSpec)et.nextElement();

        pw.println("  };");
        pw.println();
        pw.println("  private static final " + className + " instance = new " + className + "();");
        pw.println();
        pw.println("  private " + className + "() {");
        pw.println("    super( tables );");
        pw.println("  }");
        pw.println();
        pw.println("  public static DataSpec getInstance() {");
        pw.println("    return instance;");
        pw.println("  };");
        pw.println();
        pw.println("}");
        pw.close();
    }

    private String toSqlTypeName(int sqlType)
    {
        switch(sqlType)
        {
        case 91: // '['
            return "java.sql.Types.DATE";

        case 92: // '\\'
            return "java.sql.Types.TIME";

        case 93: // ']'
            return "java.sql.Types.TIMESTAMP";

        case 4: // '\004'
            return "java.sql.Types.INTEGER";

        case 3: // '\003'
            return "java.sql.Types.DECIMAL";

        case 2: // '\002'
            return "java.sql.Types.NUMERIC";

        case 12: // '\f'
            return "java.sql.Types.VARCHAR";
        }
        return String.valueOf(sqlType);
    }

    public static final Set known;

    static 
    {
        known = new HashSet();
        known.add("session");
        known.add("master");
        known.add("account");
        known.add("login-session");
        known.add("login-account");
        known.add("login-master");
    }
}

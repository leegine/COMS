// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WritesPlugin.java

package com.fitechlabs.dbind.gen;

import java.io.*;
import java.util.*;

// Referenced classes of package com.fitechlabs.dbind.gen:
//            TableSpec, Settings, DataSpec

public class WritesPlugin
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

    private WritesPlugin()
    {
    }

    public static void write(DataSpec ds, String dbname, String proc, String dependencies, String pluginInfoDirectory)
        throws IOException
    {
        PrintWriter pw = null;
        subpackage = Settings.beanPackageName();
        devpath = Settings.beanSourceRelPath();
        processor = proc;
        String pkg = subpackage;
        String name = dbname + "Extensions";
        String dir = Settings.getAbsPath(Settings.beanSourceRelPath());
        (new File(dir)).mkdirs();
        String path = dir + name + ".java";
        System.out.println("creating " + path);
        pw = new PrintWriter(new FileOutputStream(path));
        if(pluginInfoDirectory != null)
        {
            FileOutputStream pluginInfoFile = new FileOutputStream(pluginInfoDirectory + "/" + pkg + "." + name + ".info-token");
            pluginInfoFile.close();
        }
        pw.println("package " + pkg + ";");
        pw.println();
        pw.println("import com.fitechlabs.xtrade.kernel.boot.*;");
        pw.println();
        pw.println("public final class " + name + " extends Plugin {");
        pw.println();
        pw.println("  private " + name + "() {}");
        pw.println();
        pw.println("  public static void plug() throws Exception {");
        pw.println("    plug( " + name + ".class );");
        pw.println("  }");
        pw.println();
        pw.println("  public static void onPlug() throws Exception {");
        pw.println();
        pw.println("    // dependencies");
        if(dependencies != null)
        {
            for(StringTokenizer st = new StringTokenizer(dependencies, ", \t\n"); st.hasMoreTokens(); pw.println("    " + st.nextToken() + ".plug();"));
        }
        pw.println();
        pw.println("    // extensions");
        pw.println("    regClasses();");
        if(processor != null)
        {
            pw.println("    regDbExtensions();");
            pw.println("    regDataObjectClasses();");
        }
        pw.println("  }");
        pw.println();
        pw.println("  private static void regClasses() throws Exception {");
        TableSpec ts;
        for(Enumeration e = ds.getTableSpecElements(); e.hasMoreElements(); pw.println("    regClass(  " + subpackage + "." + ts.asClass() + "Params.class );"))
        {
            ts = (TableSpec)e.nextElement();
            if(ts.hasPrimaryKeyComponents())
                pw.println("    regClass(  " + subpackage + "." + ts.asClass() + "PK.class );");
        }

        pw.println("  }");
        pw.println();
        if(processor != null)
        {
            pw.println("  private static void regDbExtensions() throws Exception {");
            Enumeration e = ds.getTableSpecElements();
            Set written = new HashSet();
            TableSpec ts;
            for(; e.hasMoreElements(); writeRegExtensionSuperclassesFirst(pw, ds, ts, written))
                ts = (TableSpec)e.nextElement();

            pw.println("  }");
            pw.println();
            pw.println("  private static void regDataObjectClasses() throws Exception {");
            e = ds.getTableSpecElements();
            String daopackage = Settings.daoPackageName();
            TableSpec ts;
            for(; e.hasMoreElements(); pw.println("        " + daopackage + "." + ts.asInterface() + "Dao.FACTORY );"))
            {
                ts = (TableSpec)e.nextElement();
                pw.println("    regDao(");
                pw.println("        " + subpackage + "." + ts.asInterface() + "Row.class,");
            }

            pw.println("  }");
            pw.println();
        }
        pw.println("}");
        if(pw != null)
            pw.close();
        break MISSING_BLOCK_LABEL_897;
        Exception exception;
        exception;
        if(pw != null)
            pw.close();
        throw exception;
    }

    private static void writeRegExtensionSuperclassesFirst(PrintWriter pw, DataSpec ds, TableSpec ts, Set written)
    {
        if(ts == null || written.contains(ts.asHeader()))
            return;
        written.add(ts.asHeader());
        String sup = ts.getAttributeValue("sup");
        String sub = ts.getAttributeValue("sub");
        if(sup != null)
            writeRegExtensionSuperclassesFirst(pw, ds, ds.getTableNamed(sup), written);
        pw.println("    regDbExtension( \"" + processor + "\", \"" + ts.asHeader() + "\",");
        if(ts.hasPrimaryKeyComponents())
            pw.println("      " + subpackage + "." + ts.asClass() + "PK.class,");
        else
            pw.println("      null,");
        pw.println("      " + subpackage + "." + ts.asClass() + "Params.class,");
        pw.println("      " + (sup == null ? "null," : "\"" + sup + "\","));
        pw.println("      " + (sub == null ? "null );" : "\"" + sub + "\" );"));
    }

    private static String subpackage = "auto";
    private static String devpath = "../..";
    private static String processor = null;

}

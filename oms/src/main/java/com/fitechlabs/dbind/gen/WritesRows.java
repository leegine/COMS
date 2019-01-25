// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WritesRows.java

package com.fitechlabs.dbind.gen;

import java.io.*;
import java.util.Enumeration;

// Referenced classes of package com.fitechlabs.dbind.gen:
//            ColumnSpec, JavaDocResourceBundleHelper, Settings, TableSpec

public class WritesRows
{

    public WritesRows()
    {
        helper = JavaDocResourceBundleHelper.instance();
    }

    public void write(TableSpec ts, String enc)
        throws IOException
    {
        String dir = Settings.getAbsPath(Settings.beanSourceRelPath());
        (new File(dir)).mkdirs();
        String path = dir + ts.asInterface() + "Row.java";
        System.out.println("creating " + path);
        FileOutputStream out = new FileOutputStream(path);
        PrintWriter pw = enc != null ? new PrintWriter(new OutputStreamWriter(out, enc)) : new PrintWriter(out);
        pw.println("package " + Settings.beanPackageName() + ";");
        pw.println();
        pw.println("import com.fitechlabs.dbind.Row;");
        pw.println("import com.fitechlabs.dbind.RowType;");
        pw.println(Settings.getImportsString());
        String sup = ts.getAttributeValue("sup");
        String extendz = ts.getAttributeValue("extends");
        String superInterface = "Row";
        if(extendz != null)
            superInterface = extendz + "Row";
        else
        if(sup != null)
            superInterface = ColumnSpec.toCapName(sup) + "Row";
        pw.println(helper.getJavaDoc("ROW_HEADER", ts, null));
        pw.println("public interface " + ts.asInterface() + "Row extends " + superInterface + " {");
        pw.println();
        pw.println(helper.getJavaDoc("ROW_ROW_TYPE", ts, null));
        pw.println("   public static final RowType TYPE = new RowType( \"" + ts.asHeader() + "\", \"" + Settings.getProcessor() + "\" );");
        pw.println();
        Enumeration e = sup == null ? ts.getColumnElements() : ts.getNonPrimaryKeyComponents();
        do
        {
            if(!e.hasMoreElements())
                break;
            ColumnSpec cs = (ColumnSpec)e.nextElement();
            pw.println(helper.getJavaDoc("ROW_COLUMN_GETTER", ts, cs));
            pw.println("  public " + cs.asType() + " get" + cs.asClass() + "();");
            pw.println();
            if(cs.getIsNullable() && cs.getIsPrimitiveType())
            {
                pw.println(helper.getJavaDoc("ROW_COLUMN_IS_NULL", ts, cs));
                pw.println("  public  boolean  get" + cs.asClass() + "IsNull();");
                pw.println();
            }
            if(cs.shouldTrackUserSet())
            {
                pw.println(helper.getJavaDoc("ROW_COLUMN_IS_SET", ts, cs));
                pw.println("  public  boolean  get" + cs.asClass() + "IsSet();");
                pw.println();
            }
        } while(true);
        pw.println();
        pw.println("}");
        pw.close();
    }

    JavaDocResourceBundleHelper helper;
}

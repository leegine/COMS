// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WritesPKs.java

package com.fitechlabs.dbind.gen;

import java.io.*;
import java.util.Enumeration;

// Referenced classes of package com.fitechlabs.dbind.gen:
//            ColumnSpec, JavaDocResourceBundleHelper, TableSpec, Settings

public class WritesPKs
{

    public WritesPKs()
    {
        helper = JavaDocResourceBundleHelper.instance();
    }

    public void write(TableSpec ts, String enc)
        throws Exception
    {
        if(!ts.hasPrimaryKeyComponents())
            return;
        String dir = Settings.getAbsPath(Settings.beanSourceRelPath());
        (new File(dir)).mkdirs();
        String path = dir + ts.asPKClass() + ".java";
        System.out.println("creating " + path);
        FileOutputStream out = new FileOutputStream(path);
        PrintWriter pw = enc != null ? new PrintWriter(new OutputStreamWriter(out, enc)) : new PrintWriter(out);
        pw.println("package " + Settings.beanPackageName() + ";");
        pw.println();
        pw.println("import com.fitechlabs.dbind.PrimaryKey;");
        pw.println("import com.fitechlabs.dbind.RowType;");
        pw.println(Settings.getImportsString());
        String sup = ts.getAttributeValue("sup");
        String extendz = ts.getAttributeValue("extends");
        String superClass = null;
        if(extendz != null)
            superClass = extendz + "PK";
        else
        if(sup != null)
            superClass = ColumnSpec.toCapName(sup) + "PK";
        pw.println(helper.getJavaDoc("PK_CLASS_HEADER", ts, null));
        pw.println("public class " + ts.asPKClass() + (superClass == null ? "" : " extends " + superClass) + " implements PrimaryKey {");
        pw.println();
        pw.println(helper.getJavaDoc("PK_CLASS_TAGNAME", ts, null));
        pw.println("  public static final String TAGNAME = \"pk\";");
        pw.println();
        pw.println(helper.getJavaDoc("PK_CLASS_PTYPE", ts, null));
        pw.println("  public static final String PTYPE = \"" + ts.asHeader() + "\";");
        pw.println();
        pw.println(helper.getJavaDoc("PK_CLASS_GET_ROW_TYPE", ts, null));
        pw.println("  public RowType getRowType() {");
        pw.println("    return " + ts.asInterface() + "Row.TYPE;");
        pw.println("  }");
        pw.println();
        if(sup == null)
        {
            ColumnSpec cs;
            for(Enumeration e = ts.getPrimaryKeyComponents(); e.hasMoreElements(); pw.println("  public " + cs.asType() + " " + cs.asMember() + ";"))
            {
                cs = (ColumnSpec)e.nextElement();
                pw.println("  /**");
                pw.println("   * " + getColumnDefination(ts, cs));
                pw.println("   */");
            }

            pw.println();
        }
        pw.println(helper.getJavaDoc("PK_DEFAULT_CONSTRUCTOR", ts, null));
        pw.println("  public " + ts.asPKClass() + "() { ");
        pw.println("  }");
        pw.println();
        pw.println(helper.getJavaDoc("PK_CONSTRUCTOR_HEADER", ts, null));
        int primaryKeyComponentCount = 0;
        ColumnSpec cs;
        for(Enumeration e = ts.getPrimaryKeyComponents(); e.hasMoreElements(); pw.println("   * @param " + cs.asParam() + " " + getColumnDefination(ts, cs)))
        {
            primaryKeyComponentCount++;
            cs = (ColumnSpec)e.nextElement();
        }

        pw.println("   */");
        pw.print("  public " + ts.asPKClass() + "( ");
        for(Enumeration e = ts.getPrimaryKeyComponents(); e.hasMoreElements(); pw.print(e.hasMoreElements() ? ", " : " "))
        {
            ColumnSpec cs = (ColumnSpec)e.nextElement();
            pw.print(cs.asType() + " " + cs.asParam());
        }

        pw.println(") {");
        ColumnSpec cs;
        for(Enumeration e = ts.getPrimaryKeyComponents(); e.hasMoreElements(); pw.println("      " + cs.asMember() + " = " + cs.asParam() + ";"))
            cs = (ColumnSpec)e.nextElement();

        pw.println("  }");
        pw.println();
        boolean isSingleStringArgument = ts.getPrimaryKeyComponentCount() == 1 && ((ColumnSpec)ts.getPrimaryKeyComponents().nextElement()).asType().equals("String");
        if(sup == null)
        {
            pw.println(helper.getJavaDoc("PK_FROM_STRING", ts, null));
            pw.println("  public static " + ts.asPKClass() + " fromString( String pkValueString ) ");
            pw.println("      throws NumberFormatException");
            pw.println("  {");
            if(isSingleStringArgument)
            {
                pw.println("    return new " + ts.asPKClass() + "( pkValueString );");
                pw.println("  }");
            } else
            {
                String args[] = new String[primaryKeyComponentCount];
                String preamble = null;
                int i;
                switch(primaryKeyComponentCount)
                {
                case 0: // '\0'
                    break;

                case 1: // '\001'
                    args[0] = "pkValueString";
                    break;

                case 2: // '\002'
                    preamble = "int i = pkValueString.indexOf(',');";
                    args[0] = "pkValueString.substring(0,i)";
                    args[1] = "pkValueString.substring(i+1)";
                    break;

                default:
                    preamble = "java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,\",\");";
                    for(i = 0; i < primaryKeyComponentCount; i++)
                        args[i] = "st.nextToken()";

                    break;
                }
                pw.println("    " + ts.asPKClass() + " pk = new " + ts.asPKClass() + "();");
                if(preamble != null)
                    pw.println("    " + preamble);
                i = 0;
                for(Enumeration e = ts.getPrimaryKeyComponents(); e.hasMoreElements(); i++)
                {
                    ColumnSpec cs = (ColumnSpec)e.nextElement();
                    String cstype = cs.asType();
                    if(cstype.equals("double"))
                    {
                        pw.println("    pk." + cs.asMember() + " = Double.valueOf(" + args[i] + ").doubleValue();");
                        continue;
                    }
                    if(cstype.equals("int"))
                    {
                        pw.println("    pk." + cs.asMember() + " = Integer.valueOf(" + args[i] + ").intValue();");
                        continue;
                    }
                    if(cstype.equals("long"))
                    {
                        pw.println("    pk." + cs.asMember() + " = Long.valueOf(" + args[i] + ").longValue();");
                        continue;
                    }
                    if(cstype.equals("java.sql.Timestamp"))
                    {
                        pw.println("    pk." + cs.asMember() + " = java.sql.Timestamp.valueOf(" + args[i] + ");");
                        continue;
                    }
                    if(cstype.equals("String"))
                        pw.println("    pk." + cs.asMember() + " = " + args[i] + ";");
                    else
                        System.out.println("ERROR: PrimaryKeyWriter.fromString: don't know PK component type: " + cstype);
                }

                pw.println("    pk.m_id = pkValueString;");
                pw.println("    return pk;");
                pw.println("  }");
            }
        }
        pw.println(helper.getJavaDoc("PK_TO_STRING", ts, null));
        pw.println("  public String toString() {");
        if(isSingleStringArgument)
        {
            ColumnSpec cs = (ColumnSpec)ts.getPrimaryKeyComponents().nextElement();
            pw.println("    return " + cs.asMember() + ";");
            pw.println("  }");
        } else
        {
            pw.println("    if ( m_id == null )");
            pw.print("      m_id = ");
            Enumeration e;
            for(e = ts.getPrimaryKeyComponents(); e.hasMoreElements(); pw.print(e.hasMoreElements() ? " + \",\" + " : ""))
            {
                ColumnSpec cs = (ColumnSpec)e.nextElement();
                String type = cs.asType();
                if(cs.getIsCustomType())
                {
                    if((com.fitechlabs.xtrade.kernel.enum.Enumerated.class).isAssignableFrom(Class.forName(type)))
                        pw.print("String.valueOf(" + cs.asMember() + ".intValue())");
                    else
                        pw.print("String.valueOf(" + cs.asMember() + ")");
                    continue;
                }
                if(type.equals("String"))
                {
                    pw.print(cs.asMember());
                    continue;
                }
                if(type.equals("double"))
                {
                    pw.print(cs.asMember() + "Format.format(" + cs.asMember() + ")");
                    continue;
                }
                if(type.equals("java.sql.Timestamp"))
                    pw.print("com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat(\"yyMMddHHmmss\").format(" + cs.asMember() + ")");
                else
                    pw.print("String.valueOf(" + cs.asMember() + ")");
            }

            pw.println(";");
            pw.println("    return m_id;");
            pw.println("  }");
            pw.println();
            pw.print("  private String m_id = null;");
            pw.println();
            e = ts.getPrimaryKeyComponents();
            do
            {
                if(!e.hasMoreElements())
                    break;
                ColumnSpec cs = (ColumnSpec)e.nextElement();
                String type = cs.asType();
                if(type.equals("double"))
                {
                    pw.print("  private static final java.text.DecimalFormat " + cs.asMember() + "Format = new java.text.DecimalFormat(\"#.");
                    for(int i = 0; i < cs.decimalDigits(); i++)
                        pw.print("#");

                    pw.print("\");");
                }
            } while(true);
        }
        pw.println();
        writeEquals(pw, ts);
        pw.println("}");
        pw.println();
        pw.close();
    }

    private void writeEquals(PrintWriter pw, TableSpec ts)
    {
        String sup = ts.getAttributeValue("sup");
        String sub = ts.getAttributeValue("sub");
        String extendz = ts.getAttributeValue("extends");
        if(sup != null || extendz != null)
            return;
        pw.println(helper.getJavaDoc("PK_EQUALS", ts, null));
        pw.println("  public boolean equals( Object other )");
        pw.println("  {");
        pw.println("    if ( other == null || !( other instanceof " + ts.asPKClass() + ") )");
        pw.println("      return false;");
        pw.println("    " + ts.asPKClass() + " o = (" + ts.asPKClass() + ") other;");
        for(Enumeration e = ts.getPrimaryKeyComponents(); e.hasMoreElements();)
        {
            ColumnSpec cs = (ColumnSpec)e.nextElement();
            if(cs.getIsPrimitiveType() && !cs.getIsNullable())
            {
                pw.println("    if ( " + cs.asMember() + " != o." + cs.asMember() + " )");
                pw.println("      return false;");
            } else
            {
                pw.println("    if ( " + cs.asMember() + " == null ) {");
                pw.println("      if ( o." + cs.asMember() + " != null )");
                pw.println("        return false;");
                pw.println("    } else if ( !" + cs.asMember() + ".equals( o." + cs.asMember() + " ) ) {");
                pw.println("        return false;");
                pw.println("    }");
            }
        }

        pw.println("    return true;");
        pw.println("  }");
        pw.println();
        pw.println(helper.getJavaDoc("PK_HASHCODE", ts, null));
        pw.println("  public int hashCode() {");
        String between = "    return";
        for(Enumeration e = ts.getPrimaryKeyComponents(); e.hasMoreElements();)
        {
            ColumnSpec cs = (ColumnSpec)e.nextElement();
            if(cs.getIsPrimitiveType())
            {
                if(!cs.getIsNullable())
                    pw.println(between + " ((int) " + cs.asMember() + ")");
                else
                    pw.println(between + " (" + cs.asMember() + "!=null? " + cs.asMember() + ".hashCode(): 0) ");
            } else
            {
                pw.println(between + " (" + cs.asMember() + "!=null? " + cs.asMember() + ".hashCode(): 0) ");
            }
            between = "        +";
        }

        pw.println("    ;");
        pw.println("  }");
        pw.println();
    }

    private String getColumnDefination(TableSpec ts, ColumnSpec cs)
    {
        if(cs.columnSize() > 0)
        {
            if(cs.decimalDigits() > 0)
                return helper.getJavaDoc("COLUMN_WITH_SIZE_PRECISION", ts, cs);
            else
                return helper.getJavaDoc("COLUMN_WITH_SIZE", ts, cs);
        } else
        {
            return helper.getJavaDoc("COLUMN", ts, cs);
        }
    }

    JavaDocResourceBundleHelper helper;
}

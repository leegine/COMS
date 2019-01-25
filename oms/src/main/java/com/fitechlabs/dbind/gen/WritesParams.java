// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WritesParams.java

package com.fitechlabs.dbind.gen;

import java.io.*;
import java.util.*;

// Referenced classes of package com.fitechlabs.dbind.gen:
//            ColumnSpec, JavaDocResourceBundleHelper, Settings, TableSpec

public class WritesParams
{

    public WritesParams()
    {
        helper = JavaDocResourceBundleHelper.instance();
    }

    public void write(TableSpec ts, String enc)
        throws IOException
    {
        String dir = Settings.getAbsPath(Settings.beanSourceRelPath());
        (new File(dir)).mkdirs();
        String path = dir + ts.asInterface() + "Params.java";
        System.out.println("creating " + path);
        FileOutputStream out = new FileOutputStream(path);
        PrintWriter pw = enc != null ? new PrintWriter(new OutputStreamWriter(out, enc)) : new PrintWriter(out);
        pw.println("package " + Settings.beanPackageName() + ";");
        pw.println();
        pw.println("import com.fitechlabs.dbind.Params;");
        pw.println("import com.fitechlabs.dbind.PrimaryKey;");
        pw.println("import com.fitechlabs.dbind.RowType;");
        pw.println(Settings.getImportsString());
        String sup = ts.getAttributeValue("sup");
        String extendz = ts.getAttributeValue("extends");
        String paramsextends = ts.getAttributeValue("paramsextends");
        String superClass = "Params";
        if(paramsextends != null)
            superClass = paramsextends;
        else
        if(extendz != null)
            superClass = extendz + "Params";
        else
        if(sup != null)
            superClass = ColumnSpec.toCapName(sup) + "Params";
        pw.println(helper.getJavaDoc("PARAMS_HEADER", ts, null));
        pw.println("public class " + ts.asInterface() + "Params extends " + superClass + " implements " + ts.asInterface() + "Row {");
        pw.println();
        pw.println(helper.getJavaDoc("PARAMS_TAGNAME", ts, null));
        pw.println("  public static final String TAGNAME = \"row\";");
        pw.println();
        pw.println(helper.getJavaDoc("PARAMS_PTYPE", ts, null));
        pw.println("  public static final String PTYPE = \"" + ts.asHeader() + "\";");
        pw.println();
        pw.println(helper.getJavaDoc("PARAMS_ROWTYPE", ts, null));
        pw.println("  public static final RowType TYPE = " + ts.asInterface() + "Row.TYPE;");
        pw.println();
        pw.println(helper.getJavaDoc("PARAMS_GET_ROWTYPE", ts, null));
        pw.println("  public RowType getRowType() {");
        pw.println("    return " + ts.asInterface() + "Row.TYPE;");
        pw.println("  }");
        pw.println();
        writeMemberVariables(pw, ts);
        writeConstructors(pw, ts);
        writeEquals(pw, ts);
        writeToMaps(pw, ts);
        writeGetters(pw, ts);
        writeSetters(pw, ts);
        writeGetSetColumn(pw, ts);
        pw.println("}");
        pw.close();
    }

    private String dotGetPrimitive(ColumnSpec cs)
    {
        if(cs.getIsNullable() && cs.getIsPrimitiveType())
            return "." + cs.asType() + "Value()";
        else
            return "";
    }

    static String objectTypeOf(ColumnSpec cs)
    {
        String primitiveType = cs.asType();
        if(cs.getIsNullable() && cs.getIsPrimitiveType())
        {
            String s = (String)objectTypes.get(primitiveType);
            if(s != null)
                return s;
            System.err.println("ERROR: Unknown nullable primitive type " + primitiveType);
        }
        return primitiveType;
    }

    private String defaultValue(ColumnSpec cs)
    {
        String primitiveType = cs.asType();
        if(cs.getIsNullable() && cs.getIsPrimitiveType())
        {
            String s = (String)defaultValues.get(primitiveType);
            if(s != null)
                return s;
            System.err.println("ERROR: Unknown nullable primitive type " + primitiveType);
        }
        return "0";
    }

    private void writeGetters(PrintWriter pw, TableSpec ts)
    {
        String sup = ts.getAttributeValue("sup");
        Enumeration e = sup == null ? ts.getColumnElements() : ts.getNonPrimaryKeyComponents();
        do
        {
            if(!e.hasMoreElements())
                break;
            ColumnSpec cs = (ColumnSpec)e.nextElement();
            pw.println(helper.getJavaDoc("ROW_COLUMN_GETTER", ts, cs));
            pw.println("  public final " + cs.asType() + " get" + cs.asClass() + "()");
            pw.println("  {");
            if(cs.getIsNullable() && cs.getIsPrimitiveType())
                pw.println("    return ( " + cs.asMember() + "==null? " + defaultValue(cs) + ": " + cs.asMember() + dotGetPrimitive(cs) + " );");
            else
                pw.println("    return " + cs.asMember() + ";");
            pw.println("  }");
            pw.println();
            if(cs.getIsNullable() && cs.getIsPrimitiveType())
            {
                pw.println(helper.getJavaDoc("ROW_COLUMN_IS_NULL", ts, cs));
                pw.println("  public final boolean get" + cs.asClass() + "IsNull()");
                pw.println("  {");
                pw.println("    return " + cs.asMember() + " == null;");
                pw.println("  }");
                pw.println();
            }
            if(cs.shouldTrackUserSet())
            {
                pw.println(helper.getJavaDoc("ROW_COLUMN_IS_SET", ts, cs));
                pw.println("  public final boolean get" + cs.asClass() + "IsSet() {");
                pw.println("    return " + cs.asMember() + "_is_set;");
                pw.println("  }");
                pw.println();
            }
        } while(true);
        if(!ts.hasPrimaryKeyComponents())
        {
            pw.println(helper.getJavaDoc("PARAMS_GET_PK", ts, null));
            pw.println("  public PrimaryKey getPrimaryKey()");
            pw.println("  {");
            pw.println("    throw new UnsupportedOperationException(\"Table " + ts.asHeader() + " has no primary key.\");");
            pw.println("  }");
            pw.println();
        } else
        {
            pw.println(helper.getJavaDoc("PARAMS_GET_PK", ts, null));
            pw.println("  public PrimaryKey getPrimaryKey()");
            pw.println("  {");
            pw.print("    return new " + ts.asInterface() + "PK(");
            ColumnSpec cs;
            for(e = ts.getPrimaryKeyComponents(); e.hasMoreElements(); pw.print(cs.asMember() + (e.hasMoreElements() ? ", " : "")))
                cs = (ColumnSpec)e.nextElement();

            pw.println(");");
            pw.println("  }");
            pw.println();
        }
    }

    private void writeSetters(PrintWriter pw, TableSpec ts)
    {
        String sup = ts.getAttributeValue("sup");
        String sub = ts.getAttributeValue("sub");
        Enumeration e = sup == null ? ts.getColumnElements() : ts.getNonPrimaryKeyComponents();
        do
        {
            if(!e.hasMoreElements())
                break;
            ColumnSpec cs = (ColumnSpec)e.nextElement();
            if(!cs.asHeader().equals(sub))
            {
                pw.println(helper.getJavaDoc("PARAMS_SETTER_HEADER", ts, cs));
                pw.println("   * @param " + cs.asParam() + " " + getColumnDefination(ts, cs));
                pw.println("   */");
                pw.print("  public final void set" + cs.asClass() + "( " + cs.asType() + " " + cs.asParam() + " )");
                pw.println("  {");
                pw.println("    if(!mutable())");
                pw.println("      throw new UnsupportedOperationException(\"This Params object cann't be modified. Use copy contructor to create a new Params object for modification.\");");
                if(cs.getIsNullable() && cs.getIsPrimitiveType())
                    pw.println("    " + cs.asMember() + " = new " + objectTypeOf(cs) + "(" + cs.asParam() + ");");
                else
                    pw.println("    " + cs.asMember() + " = " + cs.asParam() + ";");
                if(cs.shouldTrackUserSet())
                    pw.println("    " + cs.asMember() + "_is_set = true;");
                pw.println("  }");
                pw.println();
                if(cs.getIsNullable() && cs.getIsPrimitiveType())
                {
                    pw.println(helper.getJavaDoc("PARAMS_OBJECT_SETTER_FOR_PRIMITIVE", ts, cs));
                    pw.print("  public final void set" + cs.asClass() + "( " + cs.asCapType() + " " + cs.asParam() + " )");
                    pw.println("  {");
                    pw.println("    if(!mutable())");
                    pw.println("      throw new UnsupportedOperationException(\"This Params object can't be modified. Use copy contructor to create a new Params object for modification.\");");
                    pw.println("    " + cs.asMember() + " = " + cs.asParam() + ";");
                    if(cs.shouldTrackUserSet())
                        pw.println("    " + cs.asMember() + "_is_set = true;");
                    pw.println("  }");
                    pw.println();
                }
                if(javaUtilDateSubclasses.contains(cs.asType()))
                {
                    pw.println(helper.getJavaDoc("PARAMS_OBJECT_SETTER_FOR_JAVA_UTIL_DATE", ts, cs));
                    pw.print("  public final void set" + cs.asClass() + "( java.util.Date date )");
                    pw.println("  {");
                    pw.println("    if(!mutable())");
                    pw.println("      throw new UnsupportedOperationException(\"This Params object can't be modified. Use copy contructor to create a new Params object for modification.\");");
                    pw.println("    " + cs.asMember() + " = (date==null? null: new " + cs.asType() + "( date.getTime() ));");
                    if(cs.shouldTrackUserSet())
                        pw.println("    " + cs.asMember() + "_is_set = true;");
                    pw.println("  }");
                    pw.println();
                }
            }
        } while(true);
    }

    private void writeGetSetColumn(PrintWriter pw, TableSpec ts)
    {
        String sup = ts.getAttributeValue("sup");
        String sub = ts.getAttributeValue("sub");
        Map map = new TreeMap();
        ColumnSpec cs;
        List list;
        for(Enumeration e = sup == null ? ts.getColumnElements() : ts.getNonPrimaryKeyComponents(); e.hasMoreElements(); list.add(cs))
        {
            cs = (ColumnSpec)e.nextElement();
            String column_name = cs.asHeader();
            char c = column_name.charAt(0);
            Character C = new Character(c);
            list = (List)map.get(C);
            if(list == null)
                map.put(C, list = new ArrayList());
        }

        pw.println(helper.getJavaDoc("PARAMS_GET_COLUMN", ts, null));
        pw.println("    public Object getColumn( String name ) {");
        pw.println("        if ( name == null || name.length() <= 0 )");
        pw.println("            throw new IllegalArgumentException( \"name cannot be null.\" );");
        pw.println("        switch ( name.charAt(0) ) {");
        for(Iterator i = map.entrySet().iterator(); i.hasNext(); pw.println("                break;"))
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)i.next();
            Character C = (Character)entry.getKey();
            List list = (List)entry.getValue();
            pw.println("            case '" + C.charValue() + "':");
            String ifelse = "if";
            for(Iterator j = list.iterator(); j.hasNext();)
            {
                ColumnSpec cs = (ColumnSpec)j.next();
                pw.println("                " + ifelse + " ( name.equals(\"" + cs.asHeader() + "\") ) {");
                if(!cs.getIsNullable() && cs.getIsPrimitiveType())
                    pw.println("                    return new " + cs.asCapType() + "( " + cs.asMember() + " );");
                else
                    pw.println("                    return this." + cs.asMember() + ";");
                pw.println("                }");
                ifelse = "else if";
            }

        }

        pw.println("        }");
        if(sup != null)
            pw.println("        return super.getColumn( name );");
        else
            pw.println("        throw new IllegalArgumentException(\"field '\"+name+\"' not found.\");");
        pw.println("    }");
        pw.println();
        pw.println(helper.getJavaDoc("PARAMS_SET_COLUMN", ts, null));
        pw.println("    public void setColumn( String name, Object value ) {");
        pw.println("        if(!mutable())");
        pw.println("            throw new UnsupportedOperationException(\"This Params object can't be modified. Use copy contructor to create a new Params object for modification.\");");
        pw.println("        if ( name == null || name.length() <= 0 )");
        pw.println("            throw new IllegalArgumentException( \"name cannot be null.\" );");
        pw.println("        switch ( name.charAt(0) ) {");
label0:
        for(Iterator i = map.entrySet().iterator(); i.hasNext(); pw.println("                break;"))
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)i.next();
            Character C = (Character)entry.getKey();
            List list = (List)entry.getValue();
            pw.println("            case '" + C.charValue() + "':");
            String ifelse = "if";
            Iterator j = list.iterator();
            do
            {
                if(!j.hasNext())
                    continue label0;
                ColumnSpec cs = (ColumnSpec)j.next();
                if(!cs.asHeader().equals(sub))
                {
                    pw.println("                " + ifelse + " ( name.equals(\"" + cs.asHeader() + "\") ) {");
                    if(cs.getIsNullable())
                    {
                        pw.println("                    if ( value != null )");
                        pw.print("  ");
                    }
                    if(!cs.getIsNullable() && cs.getIsPrimitiveType())
                    {
                        pw.println("                    if ( !(value instanceof " + cs.asCapType() + ") )");
                        pw.println("                        throw new IllegalArgumentException( \"value for '" + cs.asHeader() + "' must be " + cs.asCapType() + ": '\"+value+\"' is not.\" );");
                        pw.println("                    this." + cs.asMember() + " = ((" + cs.asCapType() + ") value)." + cs.asType() + "Value();");
                    } else
                    {
                        pw.println("                    if ( !(value instanceof " + objectTypeOf(cs) + ") )");
                        pw.println("                        throw new IllegalArgumentException( \"value for '" + cs.asHeader() + "' must be " + objectTypeOf(cs) + ": '\"+value+\"' is not.\" );");
                        pw.println("                    this." + cs.asMember() + " = (" + objectTypeOf(cs) + ") value;");
                    }
                    if(cs.shouldTrackUserSet())
                        pw.println("                    this." + cs.asMember() + "_is_set = true;");
                    pw.println("                    return;");
                    pw.println("                }");
                    ifelse = "else if";
                }
            } while(true);
        }

        pw.println("        }");
        if(sup != null)
            pw.println("        super.setColumn( name, value );");
        else
            pw.println("        throw new IllegalArgumentException(\"field '\"+name+\"' not found.\");");
        pw.println("    }");
        pw.println();
    }

    private void writeMemberVariables(PrintWriter pw, TableSpec ts)
    {
        String sup = ts.getAttributeValue("sup");
        String sub = ts.getAttributeValue("sub");
        Enumeration e;
        if(sup == null && ts.hasPrimaryKeyComponents())
        {
            ColumnSpec cs;
            for(e = ts.getPrimaryKeyComponents(); e.hasMoreElements(); pw.println(objectTypeOf(cs) + "  " + cs.asMember() + ";"))
            {
                cs = (ColumnSpec)e.nextElement();
                pw.println(helper.getJavaDoc("PARAMS_FIELD", ts, cs));
                pw.print(sub == null || !cs.asHeader().equals(sub) ? "  public  " : "  final public ");
            }

        }
        ColumnSpec cs;
        String defaultValue;
        for(e = ts.getNonPrimaryKeyComponents(); e.hasMoreElements(); pw.println(objectTypeOf(cs) + "  " + cs.asMember() + (defaultValue == null ? "" : " = " + defaultValue) + ";"))
        {
            cs = (ColumnSpec)e.nextElement();
            pw.println(helper.getJavaDoc("PARAMS_FIELD", ts, cs));
            pw.print(sub == null || !cs.asHeader().equals(sub) ? "  public  " : "  final public ");
            defaultValue = cs.getCodegenDefaultValue();
        }

        pw.println();
        e = ts.getColumnElements();
        do
        {
            if(!e.hasMoreElements())
                break;
            ColumnSpec cs = (ColumnSpec)e.nextElement();
            if(cs.shouldTrackUserSet())
                pw.println("  private boolean " + cs.asMember() + "_is_set;");
        } while(true);
        pw.println();
        pw.println(helper.getJavaDoc("PARAMS_TO_STRING", ts, null));
        pw.println("  public String toString() {");
        pw.println("    return \"{\"" + (sup == null ? "" : " + super.toString()"));
        String and = "+";
        String comma = "";
        if(ts.hasPrimaryKeyComponents())
            for(e = ts.getPrimaryKeyComponents(); e.hasMoreElements();)
            {
                ColumnSpec cs = (ColumnSpec)e.nextElement();
                pw.println("      " + comma + " + " + "\"" + cs.asHeader() + "=\" + " + cs.asMember());
                comma = "+ \",\"";
            }

        for(e = ts.getNonPrimaryKeyComponents(); e.hasMoreElements();)
        {
            ColumnSpec cs = (ColumnSpec)e.nextElement();
            pw.println("      " + comma + " + " + "\"" + cs.asHeader() + "=\" +" + cs.asMember());
            comma = "+ \",\"";
        }

        pw.println("      + \"}\";");
        pw.println("  }");
        pw.println();
    }

    private void writeToMaps(PrintWriter pw, TableSpec ts)
    {
        String sup = ts.getAttributeValue("sup");
        String sub = ts.getAttributeValue("sub");
        pw.println(helper.getJavaDoc("PARAMS_ASSERT_VALID_FOR_INSERT", ts, null));
        pw.println("\tprotected void assertValidForInsert() throws IllegalArgumentException {");
        pw.println("\t\tsuper.assertValidForInsert();");
        Enumeration e = ts.getNonPrimaryKeyComponents();
        do
        {
            if(!e.hasMoreElements())
                break;
            ColumnSpec cs = (ColumnSpec)e.nextElement();
            if(cs.mustBeSetByUserBeforeInsert())
            {
                pw.println("\t\tif ( !" + cs.asMember() + "_is_set )");
                pw.println("\t\t\tthrow new IllegalArgumentException(\"non-nullable field '" + cs.asHeader() + "' must be set before inserting.\");");
            }
        } while(true);
        pw.println("\t}");
        pw.println();
        pw.println(helper.getJavaDoc("PARAMS_TO_INSERT_MAP", ts, null));
        pw.println("\tpublic java.util.Map toInsertMap() {");
        if(sup == null)
            pw.println("\t\tassertValidForInsert();");
        pw.println("\t\tjava.util.Map map = super.toInsertMap();");
        for(e = sup == null ? ts.getColumnElements() : ts.getNonPrimaryKeyComponents(); e.hasMoreElements();)
        {
            ColumnSpec cs = (ColumnSpec)e.nextElement();
            if(cs.shouldSkipOnInsertWhenNotSetByUser())
                pw.print("\t\tif ( " + cs.asMember() + "_is_set )\n\t");
            else
            if(cs.getIsNullable())
                pw.print("\t\tif ( " + cs.asMember() + " != null )\n\t");
            if(!cs.getIsNullable() && cs.getIsPrimitiveType())
                pw.println("\t\tmap.put(\"" + cs.asHeader() + "\",new " + cs.asCapType() + "(" + cs.asMember() + "));");
            else
                pw.println("\t\tmap.put(\"" + cs.asHeader() + "\"," + cs.asMember() + ");");
        }

        pw.println("\t\tmap.remove(\"rowid\");");
        pw.println("\t\treturn map;");
        pw.println("\t}");
        pw.println();
        pw.println(helper.getJavaDoc("PARAMS_TO_UPDATE_MAP", ts, null));
        pw.println("\tpublic java.util.Map toUpdateMap() {");
        pw.println("\t\tjava.util.Map map = super.toUpdateMap();");
        for(e = ts.getNonPrimaryKeyComponents(); e.hasMoreElements();)
        {
            ColumnSpec cs = (ColumnSpec)e.nextElement();
            if(cs.shouldTrackUserSet())
                pw.print("\t\tif ( " + cs.asMember() + "_is_set )\n\t");
            if(!cs.getIsNullable() && cs.getIsPrimitiveType())
                pw.println("\t\tmap.put(\"" + cs.asHeader() + "\",new " + cs.asCapType() + "(" + cs.asMember() + "));");
            else
                pw.println("\t\tmap.put(\"" + cs.asHeader() + "\"," + cs.asMember() + ");");
        }

        pw.println("\t\treturn map;");
        pw.println("\t}");
        pw.println();
    }

    private void writeConstructors(PrintWriter pw, TableSpec ts)
    {
        String sup = ts.getAttributeValue("sup");
        String sub = ts.getAttributeValue("sub");
        pw.println(helper.getJavaDoc("PARAMS_DEFAULT_CONSTRUCTOR", ts, null));
        pw.println("  public " + ts.asInterface() + "Params() {");
        if(sup != null)
            pw.println("    super( \"" + ts.asHeader() + "\" );");
        if(sub != null)
            pw.println("    " + sub + " = null;");
        pw.println("  }");
        pw.println();
        pw.println(helper.getJavaDoc("PARAMS_CONSTRUCTOR_FOR_SUBTABLE", ts, null));
        if(sub != null)
        {
            pw.println("  protected " + ts.asInterface() + "Params( String tableName ) {");
            if(sup != null)
                pw.println("    super( \"" + ts.asHeader() + "\" );");
            if(sub != null)
                pw.println("    " + sub + " = tableName;");
            pw.println("  }");
        }
        pw.println();
        pw.println(helper.getJavaDoc("PARAMS_COPY_CONSTRUCTOR", ts, null));
        pw.println("  public " + ts.asInterface() + "Params( " + ts.asInterface() + "Row p_row )");
        pw.println("  {");
        if(sup != null)
            pw.println("    super( p_row );");
        Enumeration e;
        if(sup == null && ts.hasPrimaryKeyComponents())
        {
            e = ts.getPrimaryKeyComponents();
            do
            {
                if(!e.hasMoreElements())
                    break;
                ColumnSpec cs = (ColumnSpec)e.nextElement();
                if(cs.getIsNullable() && cs.getIsPrimitiveType())
                {
                    pw.println("    if ( !p_row.get" + cs.asClass() + "IsNull() )");
                    pw.println("      " + cs.asMember() + " = new " + objectTypeOf(cs) + "( p_row.get" + cs.asClass() + "() );");
                } else
                {
                    pw.println("    " + cs.asMember() + " = p_row.get" + cs.asClass() + "();");
                }
                if(cs.shouldTrackUserSet())
                    pw.println("    " + cs.asMember() + "_is_set = p_row.get" + cs.asClass() + "IsSet();");
            } while(true);
        }
        e = ts.getNonPrimaryKeyComponents();
        do
        {
            if(!e.hasMoreElements())
                break;
            ColumnSpec cs = (ColumnSpec)e.nextElement();
            if(cs.getIsNullable() && cs.getIsPrimitiveType())
            {
                pw.println("    if ( !p_row.get" + cs.asClass() + "IsNull() )");
                pw.println("      " + cs.asMember() + " = new " + objectTypeOf(cs) + "( p_row.get" + cs.asClass() + "() );");
            } else
            {
                pw.println("    " + cs.asMember() + " = p_row.get" + cs.asClass() + "();");
            }
            if(cs.shouldTrackUserSet())
                pw.println("    " + cs.asMember() + "_is_set = p_row.get" + cs.asClass() + "IsSet();");
        } while(true);
        pw.println("  }");
        pw.println();
        pw.println(helper.getJavaDoc("PARAMS_MARK_ALL_VALUE_AS_SET", ts, null));
        pw.println("    public void markAllValuesAsSet() {");
        pw.println("      super.markAllValuesAsSet();");
        e = ts.getNonPrimaryKeyComponents();
        do
        {
            if(!e.hasMoreElements())
                break;
            ColumnSpec cs = (ColumnSpec)e.nextElement();
            if(cs.shouldTrackUserSet())
                pw.println("      " + cs.asMember() + "_is_set = true;");
        } while(true);
        pw.println("    }");
        pw.println();
    }

    private void writeEquals(PrintWriter pw, TableSpec ts)
    {
        String sup = ts.getAttributeValue("sup");
        String sub = ts.getAttributeValue("sub");
        pw.println(helper.getJavaDoc("PARAMS_EQUALS", ts, null));
        pw.println("  public boolean equals( Object other )");
        pw.println("  {");
        pw.println("    if ( !( other instanceof " + ts.asInterface() + "Row ) )");
        pw.println("       return false;");
        pw.println("    return fieldsEqual( (" + ts.asInterface() + "Row) other );");
        pw.println("  }");
        pw.println();
        pw.println(helper.getJavaDoc("PARAMS_FIELDS_EQUALS", ts, null));
        pw.println("  public boolean fieldsEqual( " + ts.asInterface() + "Row row )");
        pw.println("  {");
        if(sup != null)
        {
            pw.println("    if ( !super.fieldsEqual( row ) )");
            pw.println("      return false;");
        }
        for(Enumeration e = ts.getColumnElements(); e.hasMoreElements();)
        {
            ColumnSpec cs = (ColumnSpec)e.nextElement();
            if(cs.isArrayType())
            {
                pw.println("    if ( !java.util.Arrays.equals( " + cs.asMember() + ", row.get" + cs.asClass() + "() ) )");
                pw.println("      return false;");
            } else
            if(cs.getIsPrimitiveType() && !cs.getIsNullable())
            {
                pw.println("    if ( " + cs.asMember() + " != row.get" + cs.asClass() + "() )");
                pw.println("      return false;");
            } else
            if(cs.getIsPrimitiveType())
            {
                pw.println("    if ( " + cs.asMember() + " == null ) {");
                pw.println("      if ( !row.get" + cs.asClass() + "IsNull() )");
                pw.println("        return false;");
                pw.println("    } else if ( row.get" + cs.asClass() + "IsNull() || ( " + cs.asMember() + "." + cs.asType() + "Value() != row.get" + cs.asClass() + "() ) ) {");
                pw.println("        return false;");
                pw.println("    }");
            } else
            {
                pw.println("    if ( " + cs.asMember() + " == null ) {");
                pw.println("      if ( row.get" + cs.asClass() + "() != null )");
                pw.println("        return false;");
                pw.println("    } else if ( !" + cs.asMember() + ".equals( row.get" + cs.asClass() + "() ) ) {");
                pw.println("        return false;");
                pw.println("    }");
            }
        }

        pw.println("    return true;");
        pw.println("  }");
        pw.println();
        pw.println(helper.getJavaDoc("PARAMS_HASHCODE", ts, null));
        pw.println("  public int hashCode() {");
        pw.print("      return ");
        String plus = "";
        if(sup != null)
        {
            pw.println("super.hashCode()");
            plus = "        +";
        }
        for(Enumeration e = ts.getColumnElements(); e.hasMoreElements(); plus = "        +")
        {
            ColumnSpec cs = (ColumnSpec)e.nextElement();
            if(cs.isArrayType())
            {
                pw.println(plus + " (" + cs.asMember() + "!=null? " + cs.asMember() + ".length: 0)");
                continue;
            }
            if(cs.getIsPrimitiveType())
            {
                if(!cs.getIsNullable())
                    pw.println(plus + " ((int) " + cs.asMember() + ")");
                else
                    pw.println(plus + " (" + cs.asMember() + "!=null? " + cs.asMember() + ".hashCode(): 0) ");
            } else
            {
                pw.println(plus + " (" + cs.asMember() + "!=null? " + cs.asMember() + ".hashCode(): 0) ");
            }
        }

        pw.println("      ;");
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
    private static Map objectTypes;
    private static Map defaultValues;
    private static Set javaUtilDateSubclasses;

    static 
    {
        objectTypes = new HashMap();
        defaultValues = new HashMap();
        objectTypes.put("byte", "Byte");
        objectTypes.put("char", "Character");
        objectTypes.put("short", "Short");
        objectTypes.put("int", "Integer");
        objectTypes.put("long", "Long");
        objectTypes.put("float", "Float");
        objectTypes.put("double", "Double");
        objectTypes.put("boolean", "Boolean");
        defaultValues.put("byte", "((byte)0)");
        defaultValues.put("char", "'\\0'");
        defaultValues.put("short", "((short)0)");
        defaultValues.put("int", "((int)0)");
        defaultValues.put("long", "((long)0)");
        defaultValues.put("float", "((float)0)");
        defaultValues.put("double", "((double)0)");
        defaultValues.put("boolean", "false");
        javaUtilDateSubclasses = new HashSet();
        javaUtilDateSubclasses.add("java.sql.Date");
        javaUtilDateSubclasses.add("java.sql.Time");
        javaUtilDateSubclasses.add("java.sql.Timestamp");
    }
}

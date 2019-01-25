// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WritesDaos.java

package com.fitechlabs.dbind.gen;

import java.io.*;
import java.util.*;

// Referenced classes of package com.fitechlabs.dbind.gen:
//            ColumnSpec, ForeignKeySpec, IndexSpec, JavaDocResourceBundleHelper, 
//            Settings, TableSpec, WritesParams

public class WritesDaos
{

    public WritesDaos()
    {
        helper = JavaDocResourceBundleHelper.instance();
        signatures = new HashSet() {

        }
;
    }

    public void write(TableSpec ts, String enc)
        throws IOException
    {
        signatures.clear();
        String relpath = Settings.daoSourceRelPath();
        String rowpkg = Settings.beanPackageName();
        String daopkg = Settings.daoPackageName();
        String ClassName = ts.asInterface();
        String ClassNameDao = ClassName + "Dao";
        String ClassNameRow = ClassName + "Row";
        String dir = Settings.getAbsPath(relpath);
        (new File(dir)).mkdirs();
        String path = dir + ClassNameDao + ".java";
        System.out.println("creating " + path);
        FileOutputStream out = new FileOutputStream(path);
        PrintWriter pw = enc != null ? new PrintWriter(new OutputStreamWriter(out, enc)) : new PrintWriter(out);
        pw.println("package " + daopkg + ";");
        pw.println();
        pw.println("import com.fitechlabs.xtrade.kernel.data.*;");
        pw.println("import com.fitechlabs.dbind.*;");
        pw.println("import java.util.*;");
        pw.println("import " + rowpkg + ".*;");
        pw.println("import com.fitechlabs.dbind.*;");
        pw.println(Settings.getImportsString());
        pw.println(helper.getJavaDoc("DAO_HEADER", ts, null));
        String sup = ts.getAttributeValue("sup");
        String extendz = ts.getAttributeValue("extends");
        String paramsextends = ts.getAttributeValue("paramsextends");
        String superClass = "DataAccessObject";
        if(paramsextends != null)
            superClass = paramsextends;
        else
        if(extendz != null)
            superClass = extendz + "Dao";
        else
        if(sup != null)
            superClass = ColumnSpec.toCapName(sup) + "Dao";
        pw.println("public class " + ts.asInterface() + "Dao extends " + superClass + " {");
        pw.println();
        writeConstructors(pw, ts);
        writeNewPkValues(pw, ts);
        writeFindByPrimaryKey(pw, ts);
        writeFetchForeignKeyObject(pw, ts);
        writeFetchReverseForeignKeyObject(pw, ts);
        writeFindByForeignKeys(pw, ts);
        writeFindByIndexValues(pw, ts);
        pw.println("}");
        pw.close();
    }

    private void writeConstructors(PrintWriter pw, TableSpec ts)
    {
        pw.println(helper.getJavaDoc("DAO_ROW_FIELD", ts, null));
        pw.println("    private " + ts.asInterface() + "Row row;");
        pw.println();
        pw.println(helper.getJavaDoc("DAO_FACTORY_FIELD", ts, null));
        pw.println("    public static final Factory FACTORY = new Factory() {");
        pw.println(helper.getJavaDoc("DAO_FACTORY_NEW_INSTANCE", ts, null));
        pw.println("        public DataAccessObject newInstance( Row row ) {");
        pw.println("            if ( row instanceof " + ts.asInterface() + "Row )");
        pw.println("                return new " + ts.asInterface() + "Dao( (" + ts.asInterface() + "Row) row );");
        pw.println("            throw new java.lang.IllegalArgumentException( \"Not a " + ts.asInterface() + "Row or subclass: \"+row.getClass() );");
        pw.println("        }");
        pw.println("    };");
        pw.println();
        pw.println(helper.getJavaDoc("DAO_CONSTRUCTOR", ts, null));
        pw.println("    protected " + ts.asInterface() + "Dao( " + ts.asInterface() + "Row row ) {");
        pw.println("        super( row );");
        pw.println("        this.row = row;");
        pw.println("    }");
        pw.println();
        pw.println(helper.getJavaDoc("DAO_GET_ROW", ts, null));
        pw.println("    public " + ts.asInterface() + "Row get" + ts.asInterface() + "Row() {");
        pw.println("        return row;");
        pw.println("    }");
        pw.println();
        pw.println(helper.getJavaDoc("DAO_FOR_ROW", ts, null));
        pw.println("    public static " + ts.asInterface() + "Dao forRow( " + ts.asInterface() + "Row row ) throws java.lang.IllegalArgumentException {");
        pw.println("        return (" + ts.asInterface() + "Dao) DataAccessObject.forRow( row );");
        pw.println("    }");
        pw.println();
    }

    private void writeNewPkValues(PrintWriter pw, TableSpec ts)
    {
        String sup = ts.getAttributeValue("sup");
        if(sup != null)
            return;
        pw.println();
        pw.println("    //--------------------------------------------");
        pw.println("    // Create new primary key values");
        pw.println("    //--------------------------------------------");
        pw.println();
        if(ts.hasPrimaryKeyComponents())
        {
            pw.println(helper.getJavaDoc("DAO_NEW_PK_VALUE", ts, null));
            pw.println("    public static long newPkValue() throws DataNetworkException, DataQueryException {");
            pw.println("        QueryProcessor qp = Processors.getDefaultProcessor();");
            pw.println("        return qp.doGetNewPkValueQuery( " + ts.asInterface() + "Row.TYPE );");
            pw.println("    }");
            pw.println();
            if(ts.getPrimaryKeyComponentCount() > 1)
            {
                pw.println(helper.getJavaDoc("DAO_NEW_PK_OBJECT", ts, null));
                pw.println("    public static " + ts.asInterface() + "PK newPkObject() throws DataNetworkException, DataQueryException {");
                pw.println("      throw new java.lang.UnsupportedOperationException( \"auto-generation of primary keys with multiple components not supported.\" );");
                pw.println("    }");
                pw.println();
            } else
            if(!"long".equals(ts.getPrimaryKeyComponent(0).asType()))
            {
                pw.println(helper.getJavaDoc("DAO_NEW_PK_OBJECT", ts, null));
                pw.println("    public static " + ts.asInterface() + "PK newPkObject() throws DataNetworkException, DataQueryException {");
                pw.println("      throw new java.lang.UnsupportedOperationException( \"auto-generation of primary keys with non-long value components not supported.\" );");
                pw.println("    }");
                pw.println();
            } else
            {
                pw.println(helper.getJavaDoc("DAO_NEW_PK_OBJECT", ts, null));
                pw.println("    public static " + ts.asInterface() + "PK newPkObject() throws DataNetworkException, DataQueryException {");
                pw.println("        long id = newPkValue();");
                pw.println("        return new " + ts.asInterface() + "PK( id );");
                pw.println("    }");
                pw.println();
            }
        } else
        {
            pw.println();
            pw.println("      // (this object has no primary key components)");
            pw.println();
        }
    }

    private void writeFindByPrimaryKey(PrintWriter pw, TableSpec ts)
    {
        String sup = ts.getAttributeValue("sup");
        if(sup != null)
            return;
        StringBuffer sbd = new StringBuffer();
        StringBuffer sbs = new StringBuffer();
        StringBuffer sba = new StringBuffer();
        StringBuffer sbt = new StringBuffer();
        ColumnSpec cs;
        for(Enumeration e = ts.getPrimaryKeyComponents(); e.hasMoreElements(); sbt.append(cs.asType() + (e.hasMoreElements() ? "," : "")))
        {
            cs = (ColumnSpec)e.nextElement();
            sbd.append("   * @param " + cs.asParam() + " " + helper.getJavaDoc("SEARCH_COLUMN", ts, cs) + "\n");
            sbs.append(cs.asType() + " " + cs.asParam() + (e.hasMoreElements() ? ", " : ""));
            sba.append(cs.asParam() + (e.hasMoreElements() ? ", " : ""));
        }

        String pkdoc = sbd.toString();
        String pksig = sbs.toString();
        String pkargs = sba.toString();
        String pktyps = sbt.toString();
        pw.println();
        pw.println("    //===========================================================================");
        pw.println("    //");
        pw.println("    // Find Rows by primary key");
        pw.println("    //");
        pw.println("    //===========================================================================");
        pw.println();
        if(ts.hasPrimaryKeyComponents())
        {
            HashMap map = new HashMap();
            map.put("ts", ts);
            map.put("params", pkdoc);
            pw.println(helper.getJavaDoc("DAO_FIND_ROW_BY_PK_VALUE", map));
            pw.println("    public static " + ts.asInterface() + "Row findRowByPk( " + pksig + " ) throws DataFindException, DataQueryException, DataNetworkException {");
            pw.println("        " + ts.asInterface() + "PK pk = new " + ts.asInterface() + "PK( " + pkargs + " );");
            pw.println("        return findRowByPk( pk );");
            pw.println("    }");
            pw.println();
            pw.println(helper.getJavaDoc("DAO_FIND_ROW_BY_PK_OBJECT", ts, null));
            pw.println("    public static " + ts.asInterface() + "Row findRowByPk( " + ts.asInterface() + "PK pk ) throws DataFindException, DataQueryException, DataNetworkException  {");
            pw.println("        QueryProcessor qp = Processors.getDefaultProcessor();");
            pw.println("        return (" + ts.asInterface() + "Row) qp.doFindByPrimaryKeyQuery( pk, null );");
            pw.println("    }");
            pw.println();
            map.clear();
            map.put("ts", ts);
            map.put("pktyps", pktyps);
            pw.println(helper.getJavaDoc("DAO_FIND_DAO_BY_PK_VALUE", map));
            pw.println("    public static " + ts.asInterface() + "Dao findDaoByPk( " + pksig + " ) throws DataFindException, DataQueryException, DataNetworkException {");
            pw.println("        " + ts.asInterface() + "PK pk = new " + ts.asInterface() + "PK( " + pkargs + " );");
            pw.println("        " + ts.asInterface() + "Row row = findRowByPk( pk );");
            pw.println("        return forRow( row );");
            pw.println("    }");
            pw.println();
            pw.println(helper.getJavaDoc("DAO_FIND_DAO_BY_PK_OBJECT", ts, null));
            pw.println("    public static " + ts.asInterface() + "Dao findDaoByPk( " + ts.asInterface() + "PK pk ) throws DataFindException, DataQueryException, DataNetworkException {");
            pw.println("        " + ts.asInterface() + "Row row = findRowByPk( pk );");
            pw.println("        return forRow( row );");
            pw.println("    }");
            pw.println();
        } else
        {
            pw.println();
            pw.println("      // (this object has no primary key components)");
            pw.println();
        }
    }

    private void writeFetchForeignKeyObject(PrintWriter pw, TableSpec ts)
    {
        pw.println();
        pw.println("    //===========================================================================");
        pw.println("    //");
        pw.println("    // Fetch Rows related by foreign key");
        pw.println("    //");
        pw.println("    //===========================================================================");
        pw.println();
        Enumeration e = ts.getForeignKeySpecs();
        if(!e.hasMoreElements())
        {
            pw.println();
            pw.println("      // (this object has no foreign keys)");
            pw.println();
            return;
        }
        do
        {
            if(!e.hasMoreElements())
                break;
            ForeignKeySpec fks = (ForeignKeySpec)e.nextElement();
            if(fks.isValid() && !fks.isParentRelation())
            {
                TableSpec fkts = fks.getForeignTable();
                boolean refIsNullable = fks.getIsNullable();
                StringBuffer sbn = new StringBuffer();
                StringBuffer sba = new StringBuffer();
                ColumnSpec cs;
                boolean hasmore;
                for(Iterator it = fks.getLocalColumnsInForeignPkOrder().iterator(); it.hasNext(); sba.append("row.get" + cs.asClass() + "()" + (hasmore ? ", " : "")))
                {
                    cs = (ColumnSpec)it.next();
                    hasmore = it.hasNext();
                    sbn.append("row.get" + cs.asClass() + "IsNull()" + (hasmore ? " && " : ""));
                }

                String nullchk = sbn.toString();
                String pkargs = sba.toString();
                String method = toFkMethodNameStub(fks);
                String sig = "fetch" + fkts.asInterface() + "RowVia" + method + "()";
                if(!signatures.contains(sig))
                {
                    signatures.add(sig);
                    HashMap map = new HashMap();
                    map.put("fktsAsInterface", fkts.asInterface());
                    map.put("ts", ts);
                    if(!refIsNullable)
                        pw.println(helper.getJavaDoc("DAO_FETCH_ROW_VIA_FK", map));
                    else
                        pw.println(helper.getJavaDoc("DAO_FETCH_ROW_VIA_FK_NULLABLE", map));
                    pw.println("    public " + fkts.asInterface() + "Row fetch" + fkts.asInterface() + "RowVia" + method + "() throws DataNetworkException, DataFindException, DataQueryException  {");
                    if(refIsNullable)
                    {
                        pw.println("        if ( " + nullchk + " )");
                        pw.println("            return null;");
                    }
                    pw.println("        " + fkts.asInterface() + "PK pk = new " + fkts.asInterface() + "PK( " + pkargs + " );");
                    pw.println("        Row row = " + fkts.asInterface() + "Dao.findRowByPk( pk );");
                    pw.println("        if ( row != null && !(row instanceof " + fkts.asInterface() + "Row) )");
                    pw.println("            throw new java.lang.IllegalStateException( \"not a subclass: \"+row.getClass() );");
                    pw.println("        return (" + fkts.asInterface() + "Row) row;");
                    pw.println("    }");
                    pw.println();
                }
                sig = "fetch" + fkts.asInterface() + "DaoVia" + method + "()";
                if(!signatures.contains(sig))
                {
                    signatures.add(sig);
                    HashMap map = new HashMap();
                    map.put("fktsAsInterface", fkts.asInterface());
                    map.put("ts", ts);
                    map.put("method", method);
                    pw.println(helper.getJavaDoc("DAO_FETCH_DAO_VIA_FK", map));
                    pw.println("    public " + fkts.asInterface() + "Dao fetch" + fkts.asInterface() + "DaoVia" + method + "() throws DataNetworkException, DataFindException, DataQueryException  {");
                    if(refIsNullable)
                    {
                        pw.println("        if ( " + nullchk + " )");
                        pw.println("            return null;");
                    }
                    pw.println("        " + fkts.asInterface() + "PK pk = new " + fkts.asInterface() + "PK( " + pkargs + " );");
                    pw.println("        DataAccessObject dao = " + fkts.asInterface() + "Dao.findDaoByPk( pk );");
                    pw.println("        if ( dao != null && !(dao instanceof " + fkts.asInterface() + "Dao) )");
                    pw.println("            throw new java.lang.IllegalStateException( \"not a subclass: \"+dao.getClass() );");
                    pw.println("        return (" + fkts.asInterface() + "Dao) dao;");
                    pw.println("    }");
                    pw.println();
                }
            }
        } while(true);
    }

    private void writeFetchReverseForeignKeyObject(PrintWriter pw, TableSpec ts)
    {
        Set s = ts.getReverseForeignKeySpecs();
        if(s.isEmpty())
            return;
        pw.println();
        pw.println("    //===========================================================================");
        pw.println("    //");
        pw.println("    // Fetch Rows having foreign keys on this object");
        pw.println("    //");
        pw.println("    //===========================================================================");
        pw.println();
        Iterator it = s.iterator();
        do
        {
            if(!it.hasNext())
                break;
            ForeignKeySpec fks = (ForeignKeySpec)it.next();
            if(fks.isValid() && !fks.isParentRelation())
            {
                TableSpec myTable = fks.getForeignTable();
                TableSpec theirTable = fks.getLocalTable();
                String method = toFkMethodNameStub(fks);
                String sig = "fetch" + theirTable.asInterface() + "RowsBy" + method + "()";
                if(!signatures.contains(sig))
                {
                    signatures.add(sig);
                    HashMap map = new HashMap();
                    map.put("theirTableAsInterface", theirTable.asInterface());
                    map.put("myTableAsInterface", myTable.asInterface());
                    map.put("ts", ts);
                    pw.println(helper.getJavaDoc("DAO_FETCH_ROWS_BY_FK", map));
                    pw.println("    public List fetch" + theirTable.asInterface() + "RowsBy" + method + "() throws DataNetworkException, DataQueryException  {");
                    pw.println("        return " + theirTable.asInterface() + "Dao.findRowsBy" + method + "( row );");
                    pw.println("    }");
                    pw.println();
                }
                sig = "fetch" + theirTable.asInterface() + "DaosBy" + method + "()";
                if(!signatures.contains(sig))
                {
                    signatures.add(sig);
                    HashMap map = new HashMap();
                    map.put("theirTableAsInterface", theirTable.asInterface());
                    map.put("myTableAsInterface", myTable.asInterface());
                    map.put("ts", ts);
                    map.put("method", method);
                    pw.println(helper.getJavaDoc("DAO_FETCH_DAOS_BY_FK", map));
                    pw.println("    public List fetch" + theirTable.asInterface() + "DaosBy" + method + "() throws DataNetworkException, DataQueryException  {");
                    pw.println("        return " + theirTable.asInterface() + "Dao.findDaosBy" + method + "( row );");
                    pw.println("    }");
                    pw.println();
                }
                sig = "fetch" + theirTable.asInterface() + "Daos" + method + "()";
                if(!signatures.contains(sig))
                {
                    signatures.add(sig);
                    HashMap map = new HashMap();
                    map.put("theirTableAsInterface", theirTable.asInterface());
                    map.put("myTableAsInterface", myTable.asInterface());
                    map.put("ts", ts);
                    map.put("method", method);
                    pw.println(helper.getJavaDoc("DAO_FETCH_DAOS_BY_FK", map));
                    pw.println("    public List fetch" + theirTable.asInterface() + "Daos" + method + "() throws DataNetworkException, DataQueryException  {");
                    pw.println("        return fetch" + theirTable.asInterface() + "DaosBy" + method + "();");
                    pw.println("    }");
                    pw.println();
                }
            }
        } while(true);
    }

    private void writeFindByForeignKeys(PrintWriter pw, TableSpec ts)
    {
        Enumeration e = ts.getForeignKeySpecs();
        if(!e.hasMoreElements())
            return;
        pw.println();
        pw.println("    //===========================================================================");
        pw.println("    //");
        pw.println("    // Find Rows given foreign key values");
        pw.println("    //");
        pw.println("    //===========================================================================");
        pw.println();
        do
        {
            if(!e.hasMoreElements())
                break;
            ForeignKeySpec fks = (ForeignKeySpec)e.nextElement();
            if(fks.isValid() && !fks.isParentRelation())
            {
                TableSpec fkts = fks.getForeignTable();
                Map mapFl = fks.getForeignColumnToLocalColumnMap();
                StringBuffer sbdoc = new StringBuffer();
                StringBuffer sbfpktypes = new StringBuffer();
                StringBuffer sbfpkparams = new StringBuffer();
                StringBuffer sbfpkargs = new StringBuffer();
                StringBuffer sbrowargs = new StringBuffer();
                StringBuffer sbpkargs = new StringBuffer();
                StringBuffer sbwhere = new StringBuffer();
                StringBuffer sbLocalWhere = new StringBuffer();
                StringBuffer sbbind = new StringBuffer();
                ColumnSpec cs;
                boolean hasmore;
                for(Enumeration ee = fkts.getPrimaryKeyComponents(); ee.hasMoreElements(); sbbind.append(asBindVar(cs) + (hasmore ? ", " : "")))
                {
                    cs = (ColumnSpec)ee.nextElement();
                    hasmore = ee.hasMoreElements();
                    sbdoc.append("   * @param " + cs.asParam() + " " + helper.getJavaDoc("SEARCH_COLUMN", ts, cs) + "\n");
                    sbfpktypes.append(cs.asType() + (hasmore ? ", " : ""));
                    sbfpkparams.append(cs.asType() + " " + cs.asParam() + (hasmore ? ", " : ""));
                    sbfpkargs.append(cs.asParam() + (hasmore ? ", " : ""));
                    sbrowargs.append("row.get" + cs.asClass() + "()" + (hasmore ? ", " : ""));
                    sbpkargs.append("pk." + cs.asHeader() + (hasmore ? ", " : ""));
                    sbwhere.append(cs.asHeader() + "=?" + (hasmore ? " and " : ""));
                    sbLocalWhere.append(((ColumnSpec)mapFl.get(cs)).asHeader() + "=?" + (hasmore ? " and " : ""));
                }

                String doc = sbdoc.toString();
                String fpktypes = sbfpktypes.toString();
                String fpkparams = sbfpkparams.toString();
                String fpkargs = sbfpkargs.toString();
                String rowargs = sbrowargs.toString();
                String pkargs = sbpkargs.toString();
                String where = sbwhere.toString();
                String localWhere = sbLocalWhere.toString();
                String bind = sbbind.toString();
                String method = toFkMethodNameStub(fks);
                pw.println("    //-----------------------------------------------------------");
                pw.println("    // Find Rows given foreign key values for " + fkts.asInterface() + "");
                pw.println("    //-----------------------------------------------------------");
                pw.println();
                String sig = "findRowsBy" + method + "(" + fkts.asInterface() + "Dao)";
                if(!signatures.contains(sig))
                {
                    signatures.add(sig);
                    HashMap map = new HashMap();
                    map.put("ts", ts);
                    map.put("method", method);
                    map.put("fktsAsInterface", fkts.asInterface());
                    pw.println(helper.getJavaDoc("DAO_FIND_ROWS_BY_DAO", map));
                    pw.println("    public static List findRowsBy" + method + "( " + fkts.asInterface() + "Dao dao ) throws DataNetworkException, DataQueryException {");
                    pw.println("        return findRowsBy" + method + "( dao.get" + fkts.asInterface() + "Row() );");
                    pw.println("    }");
                    pw.println();
                }
                sig = "findRowsBy" + method + "(" + fkts.asInterface() + "Row)";
                if(!signatures.contains(sig))
                {
                    signatures.add(sig);
                    HashMap map = new HashMap();
                    map.put("ts", ts);
                    map.put("method", method);
                    map.put("fktsAsInterface", fkts.asInterface());
                    pw.println(helper.getJavaDoc("DAO_FIND_ROWS_BY_ROW", map));
                    pw.println("    public static List findRowsBy" + method + "( " + fkts.asInterface() + "Row row ) throws DataNetworkException, DataQueryException {");
                    pw.println("        return findRowsBy" + method + "( " + rowargs + " );");
                    pw.println("    }");
                    pw.println();
                }
                sig = "findRowsBy" + method + "(" + fkts.asInterface() + "PK)";
                if(!signatures.contains(sig))
                {
                    signatures.add(sig);
                    HashMap map = new HashMap();
                    map.put("ts", ts);
                    map.put("method", method);
                    map.put("fktsAsInterface", fkts.asInterface());
                    pw.println(helper.getJavaDoc("DAO_FIND_ROWS_BY_PK", map));
                    pw.println("    public static List findRowsBy" + method + "( " + fkts.asInterface() + "PK pk ) throws DataNetworkException, DataQueryException {");
                    pw.println("        return findRowsBy" + method + "( " + pkargs + " );");
                    pw.println("    }");
                    pw.println();
                }
                sig = "findRowsBy" + method + "(" + fpktypes + ")";
                if(!signatures.contains(sig))
                {
                    signatures.add(sig);
                    HashMap map = new HashMap();
                    map.put("ts", ts);
                    map.put("method", method);
                    map.put("fktsAsInterface", fkts.asInterface());
                    map.put("params", doc);
                    pw.println(helper.getJavaDoc("DAO_FIND_ROWS_BY_FK_VALUE", map));
                    pw.println("    public static List findRowsBy" + method + "( " + fpkparams + "  ) throws DataNetworkException, DataQueryException {");
                    pw.println("        QueryProcessor qp = Processors.getDefaultProcessor();");
                    pw.println("        return qp.doFindAllQuery(");
                    pw.println("            " + ts.asInterface() + "Row.TYPE,");
                    pw.println("            \"" + localWhere + "\",");
                    pw.println("            null,");
                    pw.println("            new Object[] { " + bind + " } );");
                    pw.println("    }");
                    pw.println();
                }
                pw.println();
                pw.println("    //-----------------------------------------------------------------");
                pw.println("    // Find Daos given foreign key values for " + fkts.asInterface() + "");
                pw.println("    //-----------------------------------------------------------------");
                pw.println();
                sig = "findDaosBy" + method + "(" + fkts.asInterface() + "Dao)";
                if(!signatures.contains(sig))
                {
                    signatures.add(sig);
                    HashMap map = new HashMap();
                    map.put("ts", ts);
                    map.put("method", method);
                    map.put("fktsAsInterface", fkts.asInterface());
                    pw.println(helper.getJavaDoc("DAO_FIND_DAOS_BY_DAO", map));
                    pw.println("    public static List findDaosBy" + method + "( " + fkts.asInterface() + "Dao actor ) throws DataNetworkException, DataQueryException {");
                    pw.println("        return forRows( findRowsBy" + method + "( actor ) );");
                    pw.println("    }");
                    pw.println();
                }
                sig = "findDaosBy" + method + "(" + fkts.asInterface() + "Row)";
                if(!signatures.contains(sig))
                {
                    signatures.add(sig);
                    HashMap map = new HashMap();
                    map.put("ts", ts);
                    map.put("method", method);
                    map.put("fktsAsInterface", fkts.asInterface());
                    pw.println(helper.getJavaDoc("DAO_FIND_DAOS_BY_ROW", map));
                    pw.println("    public static List findDaosBy" + method + "( " + fkts.asInterface() + "Row row ) throws DataNetworkException, DataQueryException {");
                    pw.println("        return forRows( findRowsBy" + method + "( row ) );");
                    pw.println("    }");
                    pw.println();
                }
                sig = "findDaosBy" + method + "(" + fkts.asInterface() + "PK)";
                if(!signatures.contains(sig))
                {
                    signatures.add(sig);
                    HashMap map = new HashMap();
                    map.put("ts", ts);
                    map.put("method", method);
                    map.put("fktsAsInterface", fkts.asInterface());
                    pw.println(helper.getJavaDoc("DAO_FIND_DAOS_BY_PK", map));
                    pw.println("    public static List findDaosBy" + method + "( " + fkts.asInterface() + "PK pk ) throws DataNetworkException, DataQueryException {");
                    pw.println("        return forRows( findRowsBy" + method + "( " + pkargs + " ) );");
                    pw.println("    }");
                    pw.println();
                }
                sig = "findDaosBy" + method + "(" + fpktypes + ")";
                if(!signatures.contains(sig))
                {
                    signatures.add(sig);
                    HashMap map = new HashMap();
                    map.put("ts", ts);
                    map.put("method", method);
                    map.put("fktsAsInterface", fkts.asInterface());
                    map.put("fpktypes", fpktypes);
                    pw.println(helper.getJavaDoc("DAO_FIND_DAOS_BY_PK_VALUE", map));
                    pw.println("    public static List findDaosBy" + method + "( " + fpkparams + " ) throws DataNetworkException, DataQueryException {");
                    pw.println("        return forRows( findRowsBy" + method + "( " + fpkargs + " ) );");
                    pw.println("    }");
                    pw.println();
                    pw.println();
                }
            }
        } while(true);
    }

    private void writeFindByIndexValues(PrintWriter pw, TableSpec ts)
    {
        pw.println();
        pw.println("    //===========================================================================");
        pw.println("    //");
        pw.println("    // Find Rows or Daos given index values");
        pw.println("    //");
        pw.println("    //===========================================================================");
        pw.println();
        for(int j = 0; j < 2; j++)
        {
            boolean isUnique = j == 0;
            boolean hadsome = false;
            pw.println("    //------------------------------------------------------");
            pw.println("    // Find Rows given " + (isUnique ? "" : "non-") + "unique index values");
            pw.println("    //------------------------------------------------------");
            pw.println();
            Enumeration e = ts.getIndexSpecs();
            do
            {
                if(!e.hasMoreElements())
                    break;
                IndexSpec is = (IndexSpec)e.nextElement();
                if(is.isUnique() == isUnique)
                {
                    hadsome = true;
                    StringBuffer sbmethod = new StringBuffer();
                    StringBuffer sbdoc = new StringBuffer();
                    StringBuffer sblist = new StringBuffer();
                    StringBuffer sbtypes = new StringBuffer();
                    StringBuffer sbparams = new StringBuffer();
                    StringBuffer sbargs = new StringBuffer();
                    StringBuffer sbwhere = new StringBuffer();
                    StringBuffer sbbind = new StringBuffer();
                    int i = 0;
                    for(int n = is.getColumnCount(); i < n; i++)
                    {
                        ColumnSpec cs = is.getColumnSpec(i);
                        boolean hasmore = i + 1 < n;
                        String otype = WritesParams.objectTypeOf(cs);
                        sbmethod.append(cs.asClass());
                        sbdoc.append("   * @param " + cs.asParam() + " " + helper.getJavaDoc("SEARCH_COLUMN", ts, cs) + "\n");
                        sbtypes.append(otype + (hasmore ? ", " : ""));
                        sbparams.append(otype + " " + cs.asParam() + (hasmore ? ", " : ""));
                        sblist.append(cs.asParam() + (hasmore ? ", " : ", and "));
                        sbargs.append(cs.asParam() + (hasmore ? ", " : ""));
                        sbwhere.append(cs.asHeader() + "=?" + (hasmore ? " and " : ""));
                        sbbind.append(asBindVar(cs) + (hasmore ? ", " : ""));
                    }

                    String method = sbmethod.toString();
                    String doc = sbdoc.toString();
                    String list = sblist.toString();
                    String types = sbtypes.toString();
                    String params = sbparams.toString();
                    String args = sbargs.toString();
                    String where = sbwhere.toString();
                    String bind = sbbind.toString();
                    if(isUnique)
                    {
                        String sig = "findRowBy" + method + "(" + types + ")";
                        if(!signatures.contains(sig))
                        {
                            signatures.add(sig);
                            HashMap map = new HashMap();
                            map.put("ts", ts);
                            map.put("method", method);
                            map.put("list", list);
                            map.put("params", doc);
                            pw.println(helper.getJavaDoc("DAO_FIND_ROW_BY_UNIQUE_INDEX_COLUMN", map));
                            pw.println("    public static " + ts.asInterface() + "Row findRowBy" + method + "( " + params + " ) throws DataNetworkException, DataFindException, DataQueryException  {");
                            pw.println("        QueryProcessor qp = Processors.getDefaultProcessor();");
                            pw.println("        List list = qp.doFindAllQuery(");
                            pw.println("            " + ts.asInterface() + "Row.TYPE,");
                            pw.println("            \"" + where + "\",");
                            pw.println("            null,");
                            pw.println("            new Object[] { " + bind + " } );");
                            pw.println("        switch ( list.size() ) {");
                            pw.println("            case 0: return null;");
                            pw.println("            case 1: return (" + ts.asInterface() + "Row) list.get(0);");
                            pw.println("            default: throw new DataFindException( \"too many results in unique query " + ts.asInterface() + "Dao.findRowsBy" + method + "(): \"+list.size() );");
                            pw.println("        }");
                            pw.println("    }");
                            pw.println();
                        }
                        sig = "findDaoBy" + method + "(" + types + ")";
                        if(!signatures.contains(sig))
                        {
                            signatures.add(sig);
                            HashMap map = new HashMap();
                            map.put("ts", ts);
                            map.put("method", method);
                            map.put("list", list);
                            map.put("types", types);
                            pw.println(helper.getJavaDoc("DAO_FIND_DAO_BY_UNIQUE_INDEX_COLUMN", map));
                            pw.println("    public static " + ts.asInterface() + "Dao findDaoBy" + method + "( " + params + " ) throws DataNetworkException, DataFindException, DataQueryException  {");
                            pw.println("        return forRow( findRowBy" + method + "( " + sbargs + " ) );");
                            pw.println("    }");
                            pw.println();
                        }
                    } else
                    {
                        String sig = "findRowsBy" + method + "(" + types + ")";
                        if(!signatures.contains(sig))
                        {
                            signatures.add(sig);
                            HashMap map = new HashMap();
                            map.put("ts", ts);
                            map.put("method", method);
                            map.put("list", list);
                            map.put("params", doc);
                            pw.println(helper.getJavaDoc("DAO_FIND_ROWS_BY_INDEX_COLUMN", map));
                            pw.println("    public static List findRowsBy" + method + "( " + params + " ) throws DataNetworkException, DataQueryException  {");
                            pw.println("        QueryProcessor qp = Processors.getDefaultProcessor();");
                            pw.println("        return qp.doFindAllQuery(");
                            pw.println("            " + ts.asInterface() + "Row.TYPE,");
                            pw.println("            \"" + where + "\",");
                            pw.println("            null,");
                            pw.println("            new Object[] { " + bind + " } );");
                            pw.println("    }");
                            pw.println();
                        }
                        sig = "findDaosBy" + method + "(" + types + ")";
                        if(!signatures.contains(sig))
                        {
                            signatures.add(sig);
                            HashMap map = new HashMap();
                            map.put("ts", ts);
                            map.put("method", method);
                            map.put("list", list);
                            map.put("types", types);
                            pw.println(helper.getJavaDoc("DAO_FIND_DAOS_BY_INDEX_COLUMN", map));
                            pw.println("    public static List findDaosBy" + method + "( " + params + " ) throws DataNetworkException, DataQueryException  {");
                            pw.println("        return forRows( findRowsBy" + method + "( " + sbargs + " ) );");
                            pw.println("    }");
                            pw.println();
                        }
                    }
                }
            } while(true);
            if(!hadsome)
            {
                pw.println("        // (none) ");
                pw.println();
            }
        }

    }

    private static String asBindVar(ColumnSpec cs)
    {
        String type = cs.asType();
        String param = cs.asParam();
        if(!cs.getIsNullable() && cs.getIsPrimitiveType())
            return "new " + cs.asCapType() + "(" + param + ")";
        if(dateTypes.contains(type))
            return "new java.sql.Timestamp(" + param + ".getTime())";
        else
            return param;
    }

    private static String toFkMethodNameStub(ForeignKeySpec fks)
    {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for(int n = fks.getColumnCount(); i < n; i++)
        {
            ColumnSpec cs = fks.getLocalColumn(i);
            boolean hasmore = i + 1 < n;
            sb.append(cs.asClass());
        }

        return sb.toString();
    }

    JavaDocResourceBundleHelper helper;
    private Set signatures;
    private static Set dateTypes;

    static 
    {
        dateTypes = new HashSet();
        dateTypes.add("Date");
        dateTypes.add("java.util.Date");
    }
}

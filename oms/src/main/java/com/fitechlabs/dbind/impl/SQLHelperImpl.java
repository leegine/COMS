// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SQLHelperImpl.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            RowFiller, MapFiller, BindSql, SQLHelper, 
//            ResultFiller

public class SQLHelperImpl
    implements SQLHelper
{

    public SQLHelperImpl(SQLHelperImpl template)
    {
        tableName = template.tableName;
        pkClass = template.pkClass;
        paramsClass = template.paramsClass;
        pkFields = template.pkFields;
        rowFields = template.rowFields;
        pkNames = template.pkNames;
        rowNames = template.rowNames;
        insertNames = template.insertNames;
        updateNames = template.updateNames;
        selectRowNonPkNames = template.selectRowNonPkNames;
        selectMapNames = template.selectMapNames;
        selectMapNonPkNames = template.selectMapNonPkNames;
        selectAddedMapNames = template.selectAddedMapNames;
        accessPkWhereClause = template.accessPkWhereClause;
        mutatePkWhereClause = template.mutatePkWhereClause;
        insertSqlIntro = template.insertSqlIntro;
        updateSqlIntro = template.updateSqlIntro;
        deleteFromSql = template.deleteFromSql;
        selectPkSql = template.selectPkSql;
        selectRowSql = template.selectRowSql;
        selectCountSql = template.selectCountSql;
        selectMapSql = template.selectMapSql;
        selectAddedMapSql = template.selectAddedMapSql;
        pkFiller = template.pkFiller;
        rowFiller = template.rowFiller;
        mapFiller = template.mapFiller;
        addedMapFiller = template.addedMapFiller;
        baseTable = template.baseTable;
        tablesList = template.tablesList;
        qualifiedPkComps = template.qualifiedPkComps;
        whereKeysMatch = template.whereKeysMatch;
        keysMatchBindSql = template.keysMatchBindSql;
    }

    public SQLHelperImpl(String tableName, Class pkClass, Class paramsClass, SQLHelperImpl superSQLHelper, String subclassFieldName)
    {
        this.tableName = tableName;
        this.pkClass = pkClass;
        this.paramsClass = paramsClass;
        pkFields = getPublicFieldsArray(pkClass);
        rowFields = getPublicFieldsArray(paramsClass);
        if(pkClass == null)
            pkClass = com.fitechlabs.dbind.impl.RowidPrimaryKey.class;
        pkNames = getNames(pkFields);
        rowNames = getNames(rowFields);
        selectMapNames = rowNames;
        selectMapNonPkNames = diff(rowNames, pkNames);
        if(superSQLHelper == null)
        {
            insertNames = rowNames;
            updateNames = diff(selectMapNonPkNames, subclassFieldName);
            selectRowNonPkNames = updateNames;
            selectAddedMapNames = insertNames;
        } else
        {
            insertNames = diff(rowNames, superSQLHelper.selectMapNonPkNames);
            updateNames = diff(diff(insertNames, subclassFieldName), pkNames);
            selectRowNonPkNames = sum(superSQLHelper.selectRowNonPkNames, updateNames);
            selectAddedMapNames = diff(rowNames, superSQLHelper.selectMapNames);
        }
        if(insertNames.isEmpty())
            throw new IllegalArgumentException("no fields to insert");
        if(updateNames.isEmpty())
            log.debug("no fields to update: " + pkClass);
        if(selectRowNonPkNames.isEmpty())
            log.debug("no fields in selectRowNonPkNames: " + pkClass);
        if(selectMapNames.isEmpty())
            log.debug("no fields in selectMapNames: " + pkClass);
        if(selectMapNonPkNames.isEmpty())
            log.debug("no fields in selectMapNonPkNames: " + pkClass);
        if(selectAddedMapNames.isEmpty())
            log.debug("no fields in selectAddedMapNames: " + pkClass);
        if(superSQLHelper == null)
        {
            baseTable = tableName;
            tablesList = tableName;
            qualifiedPkComps = toQualifiedPkComps(tableName, pkNames);
            whereKeysMatch = null;
        } else
        {
            baseTable = superSQLHelper.baseTable;
            tablesList = superSQLHelper.tablesList + "," + tableName;
            qualifiedPkComps = superSQLHelper.qualifiedPkComps;
            String theseMatch = toTheseKeysMatch(baseTable, tableName, pkNames);
            whereKeysMatch = combineWhereAndWhere(superSQLHelper.whereKeysMatch, theseMatch);
        }
        insertSqlIntro = "insert into " + tableName;
        updateSqlIntro = "update " + tableName + " set ";
        deleteFromSql = "delete from " + tableName;
        selectPkSql = "select " + qualifiedPkComps + " from " + tablesList;
        selectRowSql = "select " + commas(qualifiedPkComps, commas(selectRowNonPkNames)) + " from " + tablesList;
        selectMapSql = "select " + commas(qualifiedPkComps, commas(selectMapNonPkNames)) + " from " + tablesList;
        selectCountSql = "select count(*) from " + tablesList;
        selectAddedMapSql = "select " + commas(selectAddedMapNames) + " from " + tablesList;
        accessPkWhereClause = toPkWhereClause(tableName, pkNames);
        mutatePkWhereClause = toPkWhereClause(null, pkNames);
        pkFiller = new RowFiller(pkClass, pkFields);
        rowFiller = new RowFiller(paramsClass, fieldSubset(rowFields, sum(pkNames, selectRowNonPkNames)));
        mapFiller = new MapFiller(fieldSubset(rowFields, sum(pkNames, selectMapNonPkNames)));
        addedMapFiller = new MapFiller(fieldSubset(rowFields, selectAddedMapNames));
        keysMatchBindSql = whereKeysMatch != null ? new BindSql(whereKeysMatch, (List)null) : null;
    }

    private static Field[] fieldSubset(Field fields[], Collection names)
    {
        Map map = new HashMap();
        List list = new ArrayList();
        for(int i = 0; i < fields.length; i++)
            map.put(fields[i].getName(), fields[i]);

        Object value;
        for(Iterator it = names.iterator(); it.hasNext(); list.add(value))
        {
            String name = (String)it.next();
            value = map.get(name);
            if(value == null)
                throw new IllegalArgumentException("field not found: " + name);
        }

        Field array[] = (Field[])list.toArray(new Field[list.size()]);
        return array;
    }

    private static String commas(Collection selectRowNonPkNames)
    {
        StringBuffer sb = new StringBuffer();
        String between = "";
        for(Iterator it = selectRowNonPkNames.iterator(); it.hasNext();)
        {
            String name = (String)it.next();
            sb.append(between);
            sb.append(name);
            between = ",";
        }

        return sb.toString();
    }

    private String commas(String lhs, String rhs)
    {
        if(lhs == null || lhs.length() == 0)
            return rhs;
        if(rhs == null || rhs.length() == 0)
            return lhs;
        else
            return lhs + "," + rhs;
    }

    private static String combineWhereAndWhere(String a, String b)
    {
        if(a == null)
            return b;
        if(b == null)
            return a;
        else
            return a + " and " + b;
    }

    private static String toTheseKeysMatch(String baseTable, String tableName, Collection pkNames)
    {
        StringBuffer sb = new StringBuffer();
        String a = baseTable + ".";
        String b = "=" + tableName + ".";
        String between = "";
        for(Iterator it = pkNames.iterator(); it.hasNext();)
        {
            String name = (String)it.next();
            sb.append(between);
            sb.append(a);
            sb.append(name);
            sb.append(b);
            sb.append(name);
            between = ",";
        }

        return sb.toString();
    }

    private static String toQualifiedPkComps(String tableName, Collection pkNames)
    {
        StringBuffer sb = new StringBuffer();
        String between = "";
        for(Iterator it = pkNames.iterator(); it.hasNext();)
        {
            String name = (String)it.next();
            sb.append(between);
            sb.append(tableName);
            sb.append(".");
            sb.append(name);
            between = ",";
        }

        return sb.toString();
    }

    private static String toPkWhereClause(String tableName, Collection pkNames)
    {
        StringBuffer sb = new StringBuffer();
        String between = "";
        String a = tableName != null ? tableName + "." : "";
        for(Iterator it = pkNames.iterator(); it.hasNext();)
        {
            String name = (String)it.next();
            sb.append(between);
            sb.append(a);
            sb.append(name);
            sb.append("=?");
            between = " and ";
        }

        return sb.toString();
    }

    protected static Field[] getPublicFieldsArray(Class c)
    {
        if(c == null)
            return new Field[0];
        Field f[] = c.getFields();
        List list = new ArrayList();
        for(int i = 0; i < f.length; i++)
            if(!Modifier.isStatic(f[i].getModifiers()))
                list.add(f[i]);

        Field array[] = (Field[])list.toArray(new Field[list.size()]);
        return array;
    }

    protected static List getNames(Field f[])
    {
        List list = new ArrayList();
        for(int i = 0; i < f.length; i++)
            list.add(f[i].getName());

        return list;
    }

    private static List sum(Collection a, Collection b)
    {
        List c = new ArrayList(a.size() + b.size());
        c.addAll(a);
        c.addAll(b);
        return c;
    }

    private static List diff(Collection a, Collection b)
    {
        List c = new ArrayList(a);
        c.removeAll(b);
        return c;
    }

    private static List diff(Collection a, String b)
    {
        List c = new ArrayList(a);
        c.remove(b);
        return c;
    }

    public String getDeleteFromSql()
    {
        return deleteFromSql;
    }

    public ResultFiller getCountFiller()
    {
        return countFiller;
    }

    public ResultFiller getPkFiller()
    {
        return pkFiller;
    }

    public ResultFiller getMapFiller()
    {
        return mapFiller;
    }

    public String getSelectCountSql()
    {
        return selectCountSql;
    }

    public String getSelectPkSql()
    {
        return selectPkSql;
    }

    public String getSelectRowSql()
    {
        return selectRowSql;
    }

    public ResultFiller getRowFiller()
    {
        return rowFiller;
    }

    public BindSql toUpdateSpec(Map changes)
    {
        StringBuffer sql = new StringBuffer();
        List bindVars = new ArrayList();
        String between = "";
        sql.append(updateSqlIntro);
        boolean hasChanges = false;
        Iterator it = updateNames.iterator();
        do
        {
            if(!it.hasNext())
                break;
            String name = (String)it.next();
            if(changes.containsKey(name))
            {
                sql.append(between);
                sql.append(name);
                Object value = changes.get(name);
                if(value == null)
                {
                    sql.append("=null");
                } else
                {
                    sql.append("=?");
                    bindVars.add(value);
                }
                between = ",";
                hasChanges = true;
            }
        } while(true);
        if(!hasChanges)
            return null;
        else
            return new BindSql(sql.toString(), bindVars);
    }

    public BindSql toUpdateSpec(Row newValues)
    {
        return toUpdateSpec(newValues.toUpdateMap());
    }

    public BindSql toInsertSpec(Map initialValues)
    {
        StringBuffer insertSql = new StringBuffer();
        StringBuffer valuesSql = new StringBuffer();
        List bindVars = new ArrayList();
        insertSql.append(insertSqlIntro);
        insertSql.append(" (");
        appendFields(insertNames, initialValues, insertSql, valuesSql, bindVars);
        insertSql.append(") values (");
        insertSql.append(valuesSql.toString());
        insertSql.append(")");
        return new BindSql(insertSql.toString(), bindVars);
    }

    private static String appendFields(Collection names, Map map, StringBuffer insertSql, StringBuffer valuesSql, List bindVars)
    {
        String between = "";
        Iterator it = names.iterator();
        do
        {
            if(!it.hasNext())
                break;
            String name = (String)it.next();
            if(map.containsKey(name))
            {
                insertSql.append(between);
                insertSql.append(name);
                valuesSql.append(between);
                Object value = map.get(name);
                if(value == null)
                {
                    valuesSql.append("null");
                } else
                {
                    valuesSql.append("?");
                    bindVars.add(value);
                }
                between = ",";
            }
        } while(true);
        return between;
    }

    public BindSql toInsertSpec(Row initialValues)
    {
        Map map = initialValues.toInsertMap();
        return toInsertSpec(map);
    }

    public BindSql getAccessPkBindSql(Object primaryKey)
    {
        Object bindVars[] = new Object[pkFields.length];
        for(int i = 0; i < pkFields.length; i++)
            try
            {
                bindVars[i] = pkFields[i].get(primaryKey);
            }
            catch(IllegalAccessException e)
            {
                throw new RuntimeException("can't access field " + pkFields[i] + " on pk " + primaryKey);
            }

        return new BindSql(accessPkWhereClause, bindVars);
    }

    public BindSql getMutatePkBindSql(Object primaryKey)
    {
        Object bindVars[] = new Object[pkFields.length];
        for(int i = 0; i < pkFields.length; i++)
            try
            {
                bindVars[i] = pkFields[i].get(primaryKey);
            }
            catch(IllegalAccessException e)
            {
                throw new RuntimeException("can't access field " + pkFields[i] + " on pk " + primaryKey);
            }

        return new BindSql(mutatePkWhereClause, bindVars);
    }

    public BindSql getPaginatedBindSql(String where, String orderBy, Object bindVariables[], int fromIndex, int toIndex)
    {
        String whereAndWhere = combineWhereAndWhere(whereKeysMatch, where);
        if(orderBy == null || orderBy.length() == 0)
            orderBy = qualifiedPkComps;
        String extWhere = baseTable + ".rowid in" + " (select R from" + " (select R, rownum N from" + " (select " + baseTable + ".rowid R" + " from " + tablesList + "" + (whereAndWhere == null ? "" : " where " + whereAndWhere) + " order by " + orderBy + " )" + " )" + " where N>? and N<=?" + " )";
        Object pageBinds[] = {
            new Integer(fromIndex), new Integer(toIndex)
        };
        Object extBindVars[] = combineBindVars(bindVariables, pageBinds);
        return new BindSql(extWhere, extBindVars);
    }

    public BindSql getPaginatedRowBindSql(String where, String orderBy, Object bindVariables[], int fromIndex, int toIndex)
    {
        List list = sum(pkNames, selectRowNonPkNames);
        String fields = commas(list);
        if(orderBy == null || orderBy.length() == 0)
            if(pkNames != null && pkNames.size() > 0)
                orderBy = commas(pkNames);
            else
                orderBy = fields;
        String sql = "select * from (select " + fields + ",rownum N from" + " (select *" + " from " + tablesList + (where == null ? "" : " where " + where) + " order by " + orderBy + ")" + " )" + " where N>? and N<=? order by N";
        Object pageBinds[] = {
            new Integer(fromIndex), new Integer(toIndex)
        };
        Object extBindVars[] = combineBindVars(bindVariables, pageBinds);
        return new BindSql(sql, extBindVars);
    }

    private static Object[] combineBindVars(Object bindVars1[], Object bindVars2[])
    {
        if(bindVars1 == null || bindVars1.length == 0)
            return bindVars2;
        if(bindVars2 == null || bindVars2.length == 0)
        {
            return bindVars1;
        } else
        {
            Object o[] = new Object[bindVars1.length + bindVars2.length];
            System.arraycopy(((Object) (bindVars1)), 0, ((Object) (o)), 0, bindVars1.length);
            System.arraycopy(((Object) (bindVars2)), 0, ((Object) (o)), bindVars1.length, bindVars2.length);
            return o;
        }
    }

    public Object pkFromMap(Map initialValues)
    {
        return pkFiller.fromMap(initialValues);
    }

    public Row rowFromMap(Map columnValues)
    {
        return (Row)rowFiller.fromMap(columnValues);
    }

    public Collection getColumnNames()
    {
        return rowNames;
    }

    public String getSelectAddedFieldsSql()
    {
        return selectAddedMapSql;
    }

    public ResultFiller getAddedFieldsMapFiller()
    {
        return addedMapFiller;
    }

    public String getSelectMapSql()
    {
        return selectMapSql;
    }

    public BindSql getWhereKeysMatchBindSql()
    {
        return keysMatchBindSql;
    }

    private static final Logit log;
    private static final boolean DBG;
    protected final String tableName;
    protected final Class pkClass;
    protected final Class paramsClass;
    protected final Field pkFields[];
    protected final Field rowFields[];
    protected final List pkNames;
    protected final List rowNames;
    protected final List insertNames;
    protected final List updateNames;
    protected final List selectRowNonPkNames;
    protected final List selectMapNames;
    protected final List selectMapNonPkNames;
    protected final List selectAddedMapNames;
    protected final String accessPkWhereClause;
    protected final String mutatePkWhereClause;
    protected final String insertSqlIntro;
    protected final String updateSqlIntro;
    protected final String deleteFromSql;
    protected final String selectPkSql;
    protected final String selectRowSql;
    protected final String selectMapSql;
    protected final String selectCountSql;
    protected final String selectAddedMapSql;
    protected final RowFiller pkFiller;
    protected final RowFiller rowFiller;
    protected final MapFiller mapFiller;
    protected final MapFiller addedMapFiller;
    protected final String baseTable;
    protected final String tablesList;
    protected final String qualifiedPkComps;
    protected final String whereKeysMatch;
    protected final BindSql keysMatchBindSql;
    protected static final ResultFiller countFiller = new ResultFiller() {

        public Object fromResultSet(ResultSet rs)
            throws SQLException
        {
            int i = rs.getInt(1);
            return new Integer(i);
        }

        public Object fromMap(Map map)
        {
            throw new UnsupportedOperationException("filling counts from Maps not supported");
        }

    }
;

    static 
    {
        log = Logit.getInstance(com.fitechlabs.dbind.impl.SQLHelperImpl.class);
        DBG = log.ison();
    }
}

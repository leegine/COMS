// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SubclassDataTable.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.dbind.*;
import java.util.*;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            AbstractDataTable, PlainDataTable, BindSql, SubclassDataStore, 
//            SQLHelper, Accessor, AbstractDataStore, DataTable

public class SubclassDataTable extends AbstractDataTable
{

    public SubclassDataTable(PlainDataTable plainDataTable, SubclassDataTable superTable, String subclassFieldName, SubclassDataStore subclassDataStore)
    {
        super(plainDataTable.getName(), plainDataTable.getSQLHelper(), plainDataTable.getAccessor());
        if(!plainDataTable.hasPrimaryKey())
            throw new UnsupportedOperationException("subclass tables require primary keys.");
        this.plainDataTable = plainDataTable;
        plainDataTables = plainTablesArray(plainDataTable, superTable);
        this.subclassDataStore = subclassDataStore;
        this.subclassFieldName = subclassFieldName;
        this.superTable = superTable;
        if(superTable == null)
        {
            superTableName = null;
            baseTable = this;
        } else
        {
            superTableName = superTable.getName();
            baseTable = superTable.baseTable;
        }
    }

    public boolean hasPrimaryKey()
    {
        return true;
    }

    public DataTable getSuperTable()
    {
        return superTable;
    }

    private static PlainDataTable[] plainTablesArray(PlainDataTable plainDataTable, SubclassDataTable superTable)
    {
        if(superTable == null)
        {
            return (new PlainDataTable[] {
                plainDataTable
            });
        } else
        {
            PlainDataTable s[] = superTable.getPlainTables();
            PlainDataTable a[] = new PlainDataTable[s.length + 1];
            System.arraycopy(s, 0, a, 0, s.length);
            a[s.length] = plainDataTable;
            return a;
        }
    }

    PlainDataTable getPlainDataTable()
    {
        return plainDataTable;
    }

    private PlainDataTable[] getPlainTables()
    {
        return plainDataTables;
    }

    String getSubclassFieldName()
    {
        return subclassFieldName;
    }

    public String getSuperTableName()
    {
        return superTableName;
    }

    public Object createPk()
        throws ConnectionException, UpdateException
    {
        return plainDataTable.createPk();
    }

    protected PlainDataTable[] getPlainTables(Row row)
    {
        RowType rowType = row.getRowType();
        String tableName = rowType.getTableName();
        SubclassDataTable sdt = subclassDataStore.getSubclassDataTable(tableName);
        if(sdt == null)
        {
            throw new IllegalStateException("subclass table '" + tableName + "' not found.");
        } else
        {
            PlainDataTable tables[] = sdt.getPlainTables();
            return tables;
        }
    }

    private int deleteRow(Row row)
        throws ConnectionException, DeleteException
    {
        PlainDataTable tables[] = getPlainTables(row);
        PrimaryKey pk = row.getPrimaryKey();
        int count = 0;
        for(int i = tables.length; --i >= 0;)
            count = Math.max(count, tables[i].delete(pk));

        return count;
    }

    public int delete(PrimaryKey primaryKey)
        throws ConnectionException, DeleteException
    {
        boolean commit;
        commit = false;
        beginTx();
        Row row;
        int i;
        try
        {
            row = selectRow(primaryKey);
        }
        catch(SelectException e)
        {
            throw new DeleteException("select failed: " + e.getMessage(), e.getQueryText());
        }
        if(row != null)
            break MISSING_BLOCK_LABEL_72;
        commit = true;
        i = 0;
        endTx(commit);
        return i;
        int count;
        count = deleteRow(row);
        commit = true;
        endTx(commit);
        break MISSING_BLOCK_LABEL_99;
        Exception exception;
        exception;
        endTx(commit);
        throw exception;
        return count;
    }

    public int delete(PrimaryKey primaryKey, String where, Object bindVariables[])
        throws ConnectionException, DeleteException
    {
        int count;
        boolean commit;
        count = 0;
        commit = false;
        beginTx();
        Row row;
label0:
        {
            int i;
            try
            {
                row = select(primaryKey, where, null, bindVariables);
                if(row != null)
                    break label0;
                commit = true;
                i = 0;
            }
            catch(SelectException e)
            {
                throw new DeleteException("select failed: " + e.getMessage(), e.getQueryText());
            }
            endTx(commit);
            return i;
        }
        count = deleteRow(row);
        commit = true;
        endTx(commit);
        break MISSING_BLOCK_LABEL_112;
        Exception exception;
        exception;
        endTx(commit);
        throw exception;
        return count;
    }

    public int deleteAll(String where, Object bindVariables[])
        throws ConnectionException, DeleteException
    {
        int count;
        boolean commit;
        count = 0;
        commit = false;
        beginTx();
        List rows;
label0:
        {
            int i;
            try
            {
                rows = selectAll(where, null, null, bindVariables);
                if(rows.size() != 0)
                    break label0;
                commit = true;
                i = 0;
            }
            catch(SelectException e)
            {
                throw new DeleteException("select failed: " + e.getMessage(), e.getQueryText());
            }
            endTx(commit);
            return i;
        }
        for(Iterator it = rows.iterator(); it.hasNext();)
        {
            Row row = (Row)it.next();
            count += deleteRow(row);
        }

        commit = true;
        endTx(commit);
        break MISSING_BLOCK_LABEL_151;
        Exception exception;
        exception;
        endTx(commit);
        throw exception;
        return count;
    }

    public Collection getColumnNames()
    {
        return plainDataTable.getColumnNames();
    }

    public int insert(Map initialValues)
        throws InsertException, ConnectionException
    {
        int count;
        boolean commit;
        count = 0;
        commit = false;
        beginTx();
        SubclassDataTable sdt = baseTable;
        do
        {
            PlainDataTable pdt = sdt.getPlainDataTable();
            count = Math.max(count, pdt.insert(initialValues));
            String sfName = sdt.subclassFieldName;
            if(sfName == null)
                break;
            String sfValue = (String)initialValues.get(sfName);
            if(sfValue == null)
                break;
            sdt = subclassDataStore.getSubclassDataTable(sfValue);
            if(sdt == null)
                throw new IllegalStateException("subclass table " + sfValue + " not found");
        } while(true);
        commit = true;
        endTx(commit);
        break MISSING_BLOCK_LABEL_140;
        Exception exception;
        exception;
        endTx(commit);
        throw exception;
        return count;
    }

    public int insert(Row initialValues)
        throws ConnectionException, InsertException
    {
        Map map = initialValues.toInsertMap();
        return insert(map);
    }

    public Row select(PrimaryKey primaryKey)
        throws ConnectionException, SelectException
    {
        boolean commit;
        commit = false;
        beginTx();
        Row row;
        row = selectRow(primaryKey);
        commit = true;
        endTx(commit);
        break MISSING_BLOCK_LABEL_32;
        Exception exception;
        exception;
        endTx(commit);
        throw exception;
        return row;
    }

    public Row select(PrimaryKey primaryKey, String where, String conditions, Object bindVariables[])
        throws ConnectionException, SelectException
    {
        boolean commit;
        commit = false;
        beginTx();
        Row row1;
        Object pk = super.accessor.accessUnique(super.sqlHelper.getSelectPkSql(), super.sqlHelper.getWhereKeysMatchBindSql(), super.sqlHelper.getAccessPkBindSql(primaryKey), new BindSql(where, bindVariables), conditions, super.sqlHelper.getPkFiller());
        if(pk != null)
            break MISSING_BLOCK_LABEL_86;
        commit = true;
        row1 = null;
        endTx(commit);
        return row1;
        Row row;
        row = selectRow(primaryKey);
        commit = true;
        endTx(commit);
        break MISSING_BLOCK_LABEL_116;
        Exception exception;
        exception;
        endTx(commit);
        throw exception;
        return row;
    }

    public Row select(PrimaryKey primaryKey, String conditions)
        throws ConnectionException, SelectException
    {
        return select(primaryKey, null, conditions, null);
    }

    private Row selectRow(PrimaryKey primaryKey)
        throws ConnectionException, SelectException
    {
        Map all = super.selectMap(primaryKey);
        if(all == null)
            return null;
        SubclassDataTable leaf = this;
        String sfName = leaf.getSubclassFieldName();
        String sfValue = (String)all.get(sfName);
        if(sfValue != null)
        {
            BindSql pkBindSql = super.sqlHelper.getAccessPkBindSql(primaryKey);
            do
            {
                leaf = subclassDataStore.getSubclassDataTable(sfValue);
                Map more = leaf.selectAddedFields(primaryKey);
                if(more == null)
                    throw new IllegalStateException("leaf fields '" + sfValue + " not found for pk=" + primaryKey);
                all.putAll(more);
                sfName = leaf.getSubclassFieldName();
                if(sfName == null)
                    break;
                sfValue = (String)all.get(sfName);
            } while(sfValue != null);
        }
        Row row = leaf.fillFromMap(all);
        return row;
    }

    private Map selectAddedFields(PrimaryKey primaryKey)
        throws ConnectionException, SelectException
    {
        Object obj = super.accessor.accessUnique(super.sqlHelper.getSelectAddedFieldsSql(), super.sqlHelper.getWhereKeysMatchBindSql(), super.sqlHelper.getAccessPkBindSql(primaryKey), null, null, super.sqlHelper.getAddedFieldsMapFiller());
        return (Map)obj;
    }

    private Row fillFromMap(Map columnValues)
    {
        return super.sqlHelper.rowFromMap(columnValues);
    }

    public List selectAll(String where, String orderBy, String conditions, Object bindVariables[])
        throws ConnectionException, SelectException
    {
        boolean commit;
        commit = false;
        beginTx();
        List pks;
        List list;
        pks = selectPks(where, orderBy, conditions, bindVariables);
        if(pks.size() != 0)
            break MISSING_BLOCK_LABEL_44;
        commit = true;
        list = pks;
        endTx(commit);
        return list;
        List rows;
        rows = new ArrayList(pks.size());
        Row row;
        for(Iterator it = pks.iterator(); it.hasNext(); rows.add(row))
        {
            PrimaryKey pk = (PrimaryKey)it.next();
            row = select(pk);
        }

        commit = true;
        endTx(commit);
        break MISSING_BLOCK_LABEL_135;
        Exception exception;
        exception;
        endTx(commit);
        throw exception;
        return rows;
    }

    public int update(PrimaryKey primaryKey, String where, Object bindVariables[], Map changes)
        throws UpdateException, ConnectionException
    {
        int count;
        boolean commit;
        count = 0;
        commit = false;
        beginTx();
        Row row;
label0:
        {
            int j;
            try
            {
                row = select(primaryKey, where, "for update", bindVariables);
                if(row != null)
                    break label0;
                commit = true;
                j = 0;
            }
            catch(SelectException e)
            {
                throw new UpdateException("select failed: " + e.getMessage(), e.getQueryText());
            }
            endTx(commit);
            return j;
        }
        PlainDataTable tables[] = getPlainTables(row);
        for(int i = 0; i < tables.length; i++)
            count = Math.max(count, tables[i].update(primaryKey, changes));

        commit = true;
        endTx(commit);
        break MISSING_BLOCK_LABEL_148;
        Exception exception;
        exception;
        endTx(commit);
        throw exception;
        return count;
    }

    public int update(Row newValues, String where, Object bindVariables[])
        throws ConnectionException, UpdateException
    {
        PrimaryKey pk = newValues.getPrimaryKey();
        Map changes = newValues.toUpdateMap();
        int count = update(pk, where, bindVariables, changes);
        return count;
    }

    public int updateAll(String where, Object bindVariables[], Map changes)
        throws UpdateException, ConnectionException
    {
        int count;
        boolean commit;
        count = 0;
        commit = false;
        beginTx();
        List pks;
label0:
        {
            int i;
            try
            {
                pks = selectPks(where, null, "for update", bindVariables);
                if(pks.size() != 0)
                    break label0;
                commit = true;
                i = 0;
            }
            catch(SelectException e)
            {
                throw new UpdateException("select failed: " + e.getMessage(), e.getQueryText());
            }
            endTx(commit);
            return i;
        }
        List rows = new ArrayList(pks.size());
        for(Iterator it = pks.iterator(); it.hasNext();)
        {
            PrimaryKey pk = (PrimaryKey)it.next();
            count += update(pk, null, null, changes);
        }

        commit = true;
        endTx(commit);
        break MISSING_BLOCK_LABEL_174;
        Exception exception;
        exception;
        endTx(commit);
        throw exception;
        return count;
    }

    private void beginTx()
    {
        try
        {
            subclassDataStore.beginTransaction();
        }
        catch(DbException dbe)
        {
            throw new RuntimeException("transaction failure: " + dbe);
        }
    }

    private void endTx(boolean commit)
    {
        try
        {
            if(commit)
                subclassDataStore.commitTransaction();
            else
                subclassDataStore.rollbackTransaction();
        }
        catch(DbException dbe)
        {
            throw new RuntimeException("transaction failure: " + dbe);
        }
    }

    public ListSubset selectAll(String where, String orderBy, String conditions, Object bindVariables[], int fromIndex, int toIndex)
        throws ConnectionException, SelectException
    {
        boolean commit;
        commit = false;
        beginTx();
        ListSubset subset;
        int count = selectCount(where, bindVariables);
        List pks = super.accessor.accessList(super.sqlHelper.getSelectPkSql(), super.sqlHelper.getWhereKeysMatchBindSql(), super.sqlHelper.getPaginatedBindSql(where, orderBy, bindVariables, fromIndex, toIndex), orderBy, conditions, super.sqlHelper.getPkFiller());
        List rows = new ArrayList(pks.size());
        Row row;
        for(Iterator it = pks.iterator(); it.hasNext(); rows.add(row))
        {
            PrimaryKey pk = (PrimaryKey)it.next();
            row = selectRow(pk);
        }

        subset = ArrayListSubset.fromSubList(rows, fromIndex, count);
        commit = true;
        endTx(commit);
        break MISSING_BLOCK_LABEL_175;
        Exception exception;
        exception;
        endTx(commit);
        throw exception;
        return subset;
    }

    private final SubclassDataStore subclassDataStore;
    private final PlainDataTable plainDataTable;
    private final PlainDataTable plainDataTables[];
    private final String subclassFieldName;
    private final SubclassDataTable superTable;
    private final SubclassDataTable baseTable;
    private final String superTableName;
}

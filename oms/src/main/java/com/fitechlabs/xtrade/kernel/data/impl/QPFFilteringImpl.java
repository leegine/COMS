// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QPFFilteringImpl.java

package com.fitechlabs.xtrade.kernel.data.impl;

import com.fitechlabs.dbind.DbException;
import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.*;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data.impl:
//            QPFBeanImpl, QPBeanImpl, QPFStdImpl, QPFilteringImpl

public class QPFFilteringImpl extends QPFBeanImpl
{
    private class QPStdFilteringImpl extends QPFilteringImpl
    {

        protected void assertValidInsert(Map map)
        {
            if(!map.containsKey(pkFieldName))
                throw new IllegalArgumentException("map must contain value for " + pkFieldName);
            Object value = map.get(pkFieldName);
            Long id = getId();
            if(!id.equals(value))
                throw new IllegalArgumentException("values for " + pkFieldName + " differ: map=" + value + ", processor=" + id);
            else
                return;
        }

        protected void assertValidInsert(Row row)
        {
            Object value = row.getColumn(pkFieldName);
            Long id = getId();
            if(!id.equals(value))
                throw new IllegalArgumentException("values for " + pkFieldName + " differ: row=" + value + ", processor=" + id);
            else
                return;
        }

        protected void assertValidUpdate(Row row)
        {
            Object value = row.getColumn(pkFieldName);
            Long id = getId();
            if(!id.equals(value))
                throw new IllegalArgumentException("values for " + pkFieldName + " differ: row=" + value + ", processor=" + id);
            else
                return;
        }

        protected void assertValidChanges(Map changes)
        {
            if(!changes.containsKey(pkFieldName))
                return;
            Object value = changes.get(pkFieldName);
            Long id = getId();
            if(!id.equals(value))
                throw new IllegalArgumentException("values for " + pkFieldName + " differ: row=" + value + ", processor=" + id);
            else
                return;
        }

        protected void assertValidAccount(long account_id)
        {
            Long id = getId();
            if(id.longValue() != account_id)
                throw new IllegalArgumentException("values for " + pkFieldName + " differ: supplied=" + account_id + ", processor=" + id);
            else
                return;
        }

        protected String filteredWhere(String unfilteredWhere)
        {
            if(unfilteredWhere == null || unfilteredWhere.length() == 0)
                return where;
            else
                return where + " and " + unfilteredWhere;
        }

        protected Object[] filteredBindVars(Object unfilteredBindVars[])
        {
            if(unfilteredBindVars == null || unfilteredBindVars.length == 0)
            {
                return (new Object[] {
                    getId()
                });
            } else
            {
                Object combined[] = new Object[unfilteredBindVars.length + 1];
                System.arraycopy(((Object) (unfilteredBindVars)), 0, ((Object) (combined)), 1, unfilteredBindVars.length);
                combined[0] = getId();
                return combined;
            }
        }

        public QPStdFilteringImpl(QPBeanImpl qp)
        {
            super(qp);
        }
    }


    public QPFFilteringImpl(Properties properties, String namespace)
        throws SQLException, DbException
    {
        super(properties, namespace);
        pkFieldName = namespace + "_id";
        where = pkFieldName + "=?";
    }

    public QueryProcessor getQueryProcessor(Long argument)
        throws DataNetworkException, DataFindException
    {
        if(argument == null || argument.longValue() == 0L)
            return super.processor;
        QueryProcessor qp = super.getQueryProcessor(argument);
        if(!(qp instanceof QPBeanImpl))
            throw new InternalError("not a QPBeanImpl: " + qp);
        else
            return new QPStdFilteringImpl((QPBeanImpl)qp);
    }

    private String pkFieldName;
    private String where;


}

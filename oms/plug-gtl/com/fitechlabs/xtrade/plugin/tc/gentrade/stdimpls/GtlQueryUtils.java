// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GtlQueryUtils.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class GtlQueryUtils
{
    public static class Where
    {

        public void append(Where other)
        {
            if(other != null)
                append(other.getWhere(), other.getBindVar());
        }

        public void append(String where, Object bindVar[])
        {
            append(where, bindVar == null || bindVar.length <= 0 ? null : Arrays.asList(bindVar));
        }

        public void append(String where, List list)
        {
            if(where != null && where.trim().length() > 0)
            {
                if(whereBuffer.length() > 0)
                    whereBuffer.append(" and ");
                whereBuffer.append("(");
                whereBuffer.append(where);
                whereBuffer.append(")");
            }
            if(list != null)
                bindVar.addAll(list);
        }

        public String getWhere()
        {
            return whereBuffer.length() <= 0 ? null : whereBuffer.toString();
        }

        public List getBindVar()
        {
            return bindVar.size() <= 0 ? null : bindVar;
        }

        public Object[] getBindVarArray()
        {
            return bindVar.size() <= 0 ? null : bindVar.toArray();
        }

        public String toString()
        {
            StringBuffer re = new StringBuffer();
            re.append("whereBuffer: \"" + whereBuffer.toString() + "\";  ");
            re.append("bindVar:{");
            for(int i = 0; i < bindVar.size(); i++)
            {
                re.append(bindVar.get(i));
                re.append(",");
            }

            re.append("}");
            return re.toString();
        }

        StringBuffer whereBuffer;
        List bindVar;

        public Where()
        {
            whereBuffer = new StringBuffer();
            bindVar = new ArrayList();
        }

        public Where(String original_where, List original_bindVar)
        {
            whereBuffer = new StringBuffer();
            bindVar = new ArrayList();
            append(original_where, original_bindVar);
        }
    }


    public GtlQueryUtils()
    {
    }

    private static Where prepareAppend(Where where)
    {
        if(where == null)
            where = new Where();
        else
        if(where.whereBuffer.length() > 0)
            where.whereBuffer.append(" and ");
        return where;
    }

    public static Where addNotMarkedDeleted0(Where where)
    {
        where = prepareAppend(where);
        where.whereBuffer.append("(delete_flag is null or delete_flag = ");
        where.whereBuffer.append(0);
        where.whereBuffer.append(")");
        return where;
    }

    public static Where addMainAccountQueryInfo0(Where where, MainAccount mainAccount)
    {
        if(mainAccount != null)
        {
            where = prepareAppend(where);
            where.whereBuffer.append("(account_id=?)");
            where.bindVar.add(new Long(mainAccount.getAccountId()));
        }
        return where;
    }

    public static Where addSubAccountQueryInfo0(Where where, SubAccount subAccount)
    {
        if(subAccount != null)
        {
            where = prepareAppend(where);
            where.whereBuffer.append("(account_id=? and sub_account_id=?)");
            where.bindVar.add(new Long(subAccount.getMainAccount().getAccountId()));
            where.bindVar.add(new Long(subAccount.getSubAccountId()));
        }
        return where;
    }

    public static Where addProductTypeInfo0(Where where, ProductTypeEnum productType)
    {
        if(productType != null)
        {
            where = prepareAppend(where);
            where.whereBuffer.append("(product_type=?)");
            where.bindVar.add(productType);
        }
        return where;
    }

    public static Where addContractTypeInfo0(Where where, ContractTypeEnum contractType)
    {
        if(contractType != null)
        {
            where = prepareAppend(where);
            where.whereBuffer.append("(contract_type=?)");
            where.bindVar.add(contractType);
        }
        return where;
    }

    public static Where addDateRangeQueryInfo0(Where where, DateRangeQueryParamsSpec dateRangeQuerySpec, String date_field_name)
    {
        if(dateRangeQuerySpec != null && !dateRangeQuerySpec.equals(DateRangeQueryParamsSpec.ALL_DATE_RANGES))
        {
            if(date_field_name == null)
                throw new RuntimeSystemException("Not date_field_name is provided for query with date range.");
            where = prepareAppend(where);
            java.util.Date from = dateRangeQuerySpec.getFromDate();
            java.util.Date to = dateRangeQuerySpec.getToDate();
            where.whereBuffer.append("(");
            where.whereBuffer.append("to_char(");
            where.whereBuffer.append(date_field_name);
            where.whereBuffer.append(",'YYYYMMDD') >= ? and to_char(");
            where.whereBuffer.append(date_field_name);
            where.whereBuffer.append(",'YYYYMMDD') <= ? ");
            where.whereBuffer.append(")");
            SimpleDateFormat dateFormatter = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            where.bindVar.add(dateFormatter.format(from));
            where.bindVar.add(dateFormatter.format(to));
        }
        return where;
    }

    public static Where addDateRangePaginationQueryInfo0(Where where, DateRangePaginationQueryParamsSpec querySpec, String date_field_name)
    {
        if(querySpec != null)
            where = addDateRangeQueryInfo0(where, querySpec.getDateRangeQueryParamsSpec(), date_field_name);
        return where;
    }

    public static String addSubStringSearchModifier(String stringToSearch)
    {
        StringBuffer ret = new StringBuffer("%");
        if(stringToSearch != null)
            ret.append(stringToSearch);
        ret.append("%");
        return ret.toString();
    }

    public static ListPage executeQuery(RowType type, String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber)
        throws DataNetworkException, DataQueryException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        if(DBG)
        {
            m_log.debug("where=" + where);
            m_log.debug("orderBy=" + orderBy);
            m_log.debug("pageSize=" + pageSize);
            m_log.debug("pageNumber=" + pageNumber);
            if(bindVars != null)
            {
                for(int i = 0; i < bindVars.length; i++)
                    m_log.debug("bindVars[" + i + "]=" + bindVars[i]);

            } else
            {
                m_log.debug("bindVars=null");
            }
        }
        if(pageSize <= 0 || pageNumber < 0)
        {
            List results = qp.doFindAllQuery(type, where, orderBy, conditions, bindVars);
            int size = results.size();
            return new ArrayListPage(results, size != 0 ? size : 1, 0, size);
        } else
        {
            return qp.doFindAllQuery(type, where, orderBy, conditions, bindVars, pageSize, pageNumber);
        }
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static final Logit m_log;
    private static final boolean DBG;
    private static final String YYYYMMDD_DATE_FORMAT = "yyyyMMdd";
    private static final String YYYYMM_DATE_FORMAT = "yyyyMM";
    private static final String HH24MMSS_DATE_FORMAT = "HH:mm:ss";
    private static final String YYYYMMDDHH24MMSS_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlQueryUtils.class);
        DBG = m_log.ison();
    }
}

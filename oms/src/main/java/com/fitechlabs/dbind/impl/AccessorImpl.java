// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AccessorImpl.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.io.StringReader;
import java.sql.*;
import java.util.*;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            Accessor, DataStore, SqlStatistics, ResultFiller, 
//            BindSql

public class AccessorImpl
    implements Accessor
{

    public AccessorImpl(DataStore dataStore)
    {
        this.dataStore = dataStore;
    }

    public List accessList(String selectSql, BindSql whereOne, BindSql whereTwo, String orderBy, String conditions, ResultFiller filler)
        throws ConnectionException, SelectException
    {
        assertConditionsValid(conditions);
        return access(selectSql, whereOne, whereTwo, null, orderBy, conditions, filler);
    }

    public Object accessUnique(String selectSql, BindSql whereOne, BindSql whereTwo, BindSql whereThree, String conditions, ResultFiller filler)
        throws ConnectionException, SelectException
    {
        assertConditionsValid(conditions);
        List list = access(selectSql, whereOne, whereTwo, whereThree, null, conditions, filler);
        switch(list.size())
        {
        case 0: // '\0'
            return null;

        case 1: // '\001'
            return list.get(0);
        }
        throw new RuntimeException("unique select failed");
    }

    public int mutateDelete(String tblName, String deleteSql, BindSql whereOne, BindSql whereTwo)
        throws ConnectionException, DeleteException
    {
        return mutate(tblName, deleteSql, null, whereOne, whereTwo);
        QueryException e;
        e;
        throw new DeleteException(e.getMessage(), e.getQueryText());
    }

    public int mutateInsert(String tblName, BindSql insertSpec)
        throws ConnectionException, InsertException
    {
        return mutate(tblName, null, insertSpec, null, null);
        QueryException e;
        e;
        throw new InsertException(e.getMessage(), e.getQueryText());
    }

    public int mutateUpdate(String tblName, BindSql updateSpec, BindSql whereOne, BindSql whereTwo)
        throws ConnectionException, UpdateException
    {
        return mutate(tblName, null, updateSpec, whereOne, whereTwo);
        QueryException e;
        e;
        throw new UpdateException(e.getMessage(), e.getQueryText());
    }

    private void assertConditionsValid(String conditions)
    {
        if(conditions != null && conditions.length() != 0 && !dataStore.isTransactionActive())
            throw new IllegalStateException("non-null conditions '" + conditions + "' require active transaction");
        else
            return;
    }

    private List access(String selectSql, BindSql whereOne, BindSql whereTwo, BindSql whereThree, String orderBy, String conditions, ResultFiller filler)
        throws ConnectionException, SelectException
    {
        String sql;
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        StringBuffer sb = new StringBuffer();
        String between;
        if(selectSql == null)
        {
            between = append(sb, whereOne, "");
        } else
        {
            sb.append(selectSql);
            between = appendWithParentheses(sb, whereOne, " where ");
        }
        between = appendWithParentheses(sb, whereTwo, between);
        between = appendWithParentheses(sb, whereThree, between);
        append(sb, orderBy, " order by ");
        append(sb, conditions, " ");
        sql = sb.toString();
        if(DBG)
            log.debug("SQL: " + sql);
        con = null;
        ps = null;
        rs = null;
        List list1;
        try
        {
            con = getConnection();
            ps = con.prepareStatement(sql);
            int index = 1;
            index = bind(ps, whereOne, index);
            index = bind(ps, whereTwo, index);
            index = bind(ps, whereThree, index);
        }
        catch(SQLException e)
        {
            throw new ConnectionException("query setup failure: " + e);
        }
        try
        {
            if(!SqlStatistics.isCollecting())
            {
                rs = ps.executeQuery();
            } else
            {
                long start = System.currentTimeMillis();
                try
                {
                    rs = ps.executeQuery();
                    addTiming(sql, "success", System.currentTimeMillis() - start);
                }
                catch(SQLException sqe)
                {
                    addTiming(sql, "failure - " + sqe.getMessage(), System.currentTimeMillis() - start);
                    throw sqe;
                }
            }
        }
        catch(SQLException e)
        {
            throw new SelectException("select failure, " + e, sql);
        }
        List list = new ArrayList();
        try
        {
            for(; rs.next(); list.add(filler.fromResultSet(rs)));
        }
        catch(SQLException e)
        {
            throw new RuntimeException("object fill from result set failed, " + e);
        }
        list1 = list;
        close(rs);
        close(ps);
        close(con);
        return list1;
        Exception exception;
        exception;
        close(rs);
        close(ps);
        close(con);
        throw exception;
    }

    private int mutate(String tblName, String sqlIntro, BindSql updateOrInsertSpec, BindSql whereOne, BindSql whereTwo)
        throws ConnectionException, QueryException
    {
        String sql;
        Connection con;
        PreparedStatement ps;
        StringBuffer sb = new StringBuffer();
        if(sqlIntro != null)
            sb.append(sqlIntro);
        append(sb, updateOrInsertSpec, "");
        String between = " where ";
        between = appendWithParentheses(sb, whereOne, between);
        between = appendWithParentheses(sb, whereTwo, between);
        sql = sb.toString();
        if(DBG)
            log.debug("SQL: " + sql);
        con = null;
        ps = null;
        try
        {
            con = getConnection();
            ps = con.prepareStatement(sql);
            int index = 1;
            index = bind(ps, updateOrInsertSpec, index);
            index = bind(ps, whereOne, index);
            index = bind(ps, whereTwo, index);
        }
        catch(SQLException e)
        {
            throw new ConnectionException("query setup failure: " + e);
        }
        DBindTransactions.setIsMutating(tblName);
        int i;
        if(SqlStatistics.isCollecting())
            break MISSING_BLOCK_LABEL_221;
        i = ps.executeUpdate();
        close(ps);
        close(con);
        return i;
        int j;
        try
        {
            long start = System.currentTimeMillis();
            int result;
            try
            {
                result = ps.executeUpdate();
                addTiming(sql, "success", System.currentTimeMillis() - start);
            }
            catch(SQLException sqe)
            {
                addTiming(sql, "failure - " + sqe.getMessage(), System.currentTimeMillis() - start);
                throw sqe;
            }
            j = result;
        }
        catch(SQLException e)
        {
            throw new QueryException("mutate failure, " + e, sql);
        }
        close(ps);
        close(con);
        return j;
        Exception exception;
        exception;
        close(ps);
        close(con);
        throw exception;
    }

    private void addTiming(String sql, String message, long time)
    {
        try
        {
            StringTokenizer st = new StringTokenizer(sql);
            String op = st.nextToken();
            String table;
            String where;
            if("update".equalsIgnoreCase(op))
            {
                table = st.nextToken();
                where = st.nextToken(";");
            } else
            if("select".equalsIgnoreCase(op))
            {
                String what = st.nextToken();
                st.nextToken();
                table = st.nextToken();
                where = st.hasMoreTokens() ? st.nextToken(";") : "";
                op = op + " " + truncate(what);
            } else
            {
                st.nextToken();
                table = st.nextToken();
                where = st.hasMoreTokens() ? st.nextToken(";") : "";
            }
            SqlStatistics.addTiming(table, op, where, message, time);
        }
        catch(Exception e)
        {
            log.warn("timing record failed, sql=" + sql + ", excep=" + e);
        }
    }

    private String truncate(String what)
    {
        int LEN = 32;
        if(what.length() < LEN)
            return what;
        else
            return what.substring(0, LEN - 3) + "...";
    }

    private static String append(StringBuffer sb, BindSql bindSql, String between)
    {
        if(bindSql == null)
            return between;
        String sql = bindSql.getSql();
        if(sql == null || sql.length() == 0)
        {
            return between;
        } else
        {
            sb.append(between);
            sb.append(sql);
            return " and ";
        }
    }

    private static String appendWithParentheses(StringBuffer sb, BindSql bindSql, String between)
    {
        if(bindSql == null)
            return between;
        String sql = bindSql.getSql();
        if(sql == null || sql.length() == 0)
        {
            return between;
        } else
        {
            sb.append(between);
            sb.append("(");
            sb.append(sql);
            sb.append(")");
            return " and ";
        }
    }

    private static void append(StringBuffer sb, String sql, String between)
    {
        if(sql == null || sql.length() == 0)
        {
            return;
        } else
        {
            sb.append(between);
            sb.append(sql);
            return;
        }
    }

    private int bind(PreparedStatement ps, BindSql bindSql, int index)
        throws SQLException
    {
        if(bindSql == null)
            return index;
        Object bindVars[] = bindSql.getBindVarialbes();
        if(bindVars == null)
            return index;
        for(int i = 0; i < bindVars.length;)
        {
            Object v = bindVars[i];
            if(v == null)
                ps.setNull(index, 12);
            else
            if((v instanceof String) && ((String)v).length() > DBindConfig.getStringMaxSize())
            {
                String s = (String)v;
                StringReader reader = new StringReader(s);
                ps.setCharacterStream(index, reader, s.length());
            } else
            {
                ps.setObject(index, v);
            }
            i++;
            index++;
        }

        return index;
    }

    private static void close(ResultSet rs)
    {
        if(rs != null)
            try
            {
                rs.close();
            }
            catch(Exception e)
            {
                log.warn("ResultSet close failed, " + e);
            }
    }

    private static void close(PreparedStatement ps)
    {
        if(ps != null)
            try
            {
                ps.close();
            }
            catch(Exception e)
            {
                log.warn("PreparedStatement close failed, " + e);
            }
    }

    private Connection getConnection()
        throws SQLException
    {
        return dataStore.getConnection();
    }

    private void close(Connection con)
    {
        if(con != null)
            dataStore.closeConnection(con);
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static final Logit log;
    private static final boolean DBG;
    private final DataStore dataStore;

    static 
    {
        log = Logit.getInstance(com.fitechlabs.dbind.impl.AccessorImpl.class);
        DBG = log.ison();
    }
}

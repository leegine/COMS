// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InvSQL.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.xtrade.kernel.data.DataSources;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.sql.*;
import javax.sql.DataSource;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            InvHeartbeats

public class InvSQL
{
    static class UpdateHeartbeatResult
    {

        public String toString()
        {
            return "UpdateHeartbeatResult(lost=" + synchWasLost + ",expires=" + newExpirationDate + ",updated=" + updateDate + ",prior=" + priorExpirationDate + ")";
        }

        Timestamp priorExpirationDate;
        Timestamp newExpirationDate;
        Timestamp updateDate;
        boolean synchWasLost;

        UpdateHeartbeatResult()
        {
        }
    }

    static class CreateHeartbeatResult
    {

        public String toString()
        {
            return "CreateHeartbeatResult(expires=" + expirationDate + ",created=" + createDate + ",url=" + url + ")";
        }

        String url;
        Timestamp expirationDate;
        Timestamp createDate;

        CreateHeartbeatResult()
        {
        }
    }


    private InvSQL()
    {
    }

    static long selectServerId()
        throws SQLException
    {
        Connection con;
        CallableStatement cs;
        DataSource ds = null;
        con = null;
        cs = null;
        long l;
        DataSource ds = DataSources.getDefaultDataSource();
        con = ds.getConnection();
        con.setAutoCommit(true);
        cs = con.prepareCall("{call exec.serial_values(?,?,?)}");
        cs.setString(1, "server");
        cs.setInt(2, 1);
        cs.registerOutParameter(3, 2);
        cs.executeUpdate();
        l = cs.getLong(3);
        close(cs);
        close(con);
        return l;
        Exception exception;
        exception;
        close(cs);
        close(con);
        throw exception;
    }

    static void insertSubscription(DataSource ds, long serverId, long accountId)
        throws SQLException
    {
        Connection con;
        PreparedStatement ps;
        con = null;
        ps = null;
        con = ds.getConnection();
        con.setAutoCommit(true);
        ps = con.prepareStatement("insert into inv_subscription (account_id, server_id) values (?,?)");
        ps.setLong(1, accountId);
        ps.setLong(2, serverId);
        ps.executeUpdate();
        close(ps);
        close(con);
        break MISSING_BLOCK_LABEL_87;
        Exception exception;
        exception;
        close(ps);
        close(con);
        throw exception;
    }

    static void deleteSubscription(DataSource ds, long serverId, long accountId)
        throws SQLException
    {
        Connection con;
        PreparedStatement ps;
        con = null;
        ps = null;
        con = ds.getConnection();
        con.setAutoCommit(true);
        ps = con.prepareStatement("delete from inv_subscription where account_id=? and server_id=?");
        ps.setLong(1, accountId);
        ps.setLong(2, serverId);
        ps.executeUpdate();
        close(ps);
        close(con);
        break MISSING_BLOCK_LABEL_87;
        Exception exception;
        exception;
        close(ps);
        close(con);
        throw exception;
    }

    static int selectServerEntry(DataSource ds, long serverId)
        throws SQLException
    {
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        con = null;
        ps = null;
        rs = null;
        int i;
        con = ds.getConnection();
        con.setAutoCommit(true);
        ps = con.prepareStatement("select heartbeat_serial from inv_server where server_id=?");
        ps.setLong(1, serverId);
        rs = ps.executeQuery();
        if(rs.next())
            break MISSING_BLOCK_LABEL_80;
        i = 0;
        close(rs);
        close(ps);
        close(con);
        return i;
        i = rs.getInt(1);
        close(rs);
        close(ps);
        close(con);
        return i;
        Exception exception;
        exception;
        close(rs);
        close(ps);
        close(con);
        throw exception;
    }

    static CreateHeartbeatResult createHeartbeat(DataSource ds, long serverId, String serverAddress, int serverPort, int heartbeatSerial)
        throws SQLException
    {
        Connection con;
        CallableStatement cs;
        con = null;
        cs = null;
        CreateHeartbeatResult createheartbeatresult;
        con = ds.getConnection();
        con.setAutoCommit(true);
        cs = con.prepareCall("{call invalidation.create_heartbeat(?,?,?,?,?,?,?)}");
        cs.setLong(1, serverId);
        cs.setString(2, serverAddress);
        cs.setInt(3, serverPort);
        cs.setInt(4, InvHeartbeats.SUBSCRIPTION_DURATION_SECONDS);
        cs.setInt(5, heartbeatSerial);
        cs.registerOutParameter(6, 93);
        cs.registerOutParameter(7, 93);
        cs.executeUpdate();
        CreateHeartbeatResult result = new CreateHeartbeatResult();
        result.url = con.getMetaData().getURL();
        result.expirationDate = cs.getTimestamp(6);
        result.createDate = cs.getTimestamp(7);
        createheartbeatresult = result;
        close(cs);
        close(con);
        return createheartbeatresult;
        Exception exception;
        exception;
        close(cs);
        close(con);
        throw exception;
    }

    static UpdateHeartbeatResult updateHeartbeat(Connection con, long serverId)
        throws SQLException
    {
        CallableStatement cs = null;
        UpdateHeartbeatResult updateheartbeatresult;
        con.setAutoCommit(true);
        cs = con.prepareCall("{call invalidation.update_heartbeat(?,?,?,?,?,?)}");
        cs.setLong(1, serverId);
        cs.setInt(2, InvHeartbeats.SUBSCRIPTION_DURATION_SECONDS);
        cs.registerOutParameter(3, 93);
        cs.registerOutParameter(4, 93);
        cs.registerOutParameter(5, 93);
        cs.registerOutParameter(6, 2);
        cs.executeUpdate();
        UpdateHeartbeatResult result = new UpdateHeartbeatResult();
        result.priorExpirationDate = cs.getTimestamp(3);
        result.newExpirationDate = cs.getTimestamp(4);
        result.updateDate = cs.getTimestamp(5);
        int synchWasLostValue = cs.getInt(6);
        result.synchWasLost = synchWasLostValue != 0;
        updateheartbeatresult = result;
        close(cs);
        return updateheartbeatresult;
        Exception exception;
        exception;
        close(cs);
        throw exception;
    }

    static void deleteServerEntry(DataSource ds, long serverId)
        throws SQLException
    {
        Connection con;
        PreparedStatement ps;
        con = null;
        ps = null;
        con = ds.getConnection();
        con.setAutoCommit(true);
        ps = con.prepareStatement("delete from inv_server where server_id=?");
        ps.setLong(1, serverId);
        ps.executeUpdate();
        close(ps);
        close(con);
        break MISSING_BLOCK_LABEL_72;
        Exception exception;
        exception;
        close(ps);
        close(con);
        throw exception;
    }

    public static boolean lockAccount(Connection con, long accountId, boolean waitOnBusy)
        throws SQLException
    {
        CallableStatement cs = null;
        boolean flag;
        if(!waitOnBusy)
            break MISSING_BLOCK_LABEL_45;
        cs = con.prepareCall("{call exec.lock_account_wait(?)}");
        cs.setLong(1, accountId);
        cs.executeUpdate();
        flag = true;
        close(cs);
        return flag;
        boolean flag1;
        cs = con.prepareCall("{call exec.lock_account_nowait(?,?)}");
        cs.setLong(1, accountId);
        cs.registerOutParameter(2, 4);
        cs.executeUpdate();
        Object o = cs.getObject(2);
        int result = cs.getInt(2);
        flag1 = result == 1;
        close(cs);
        return flag1;
        Exception exception;
        exception;
        close(cs);
        throw exception;
    }

    static int getDBMSPipeSize(DataSource dataSource)
        throws SQLException
    {
        Connection c = null;
        CallableStatement stmt = null;
        int i;
        try
        {
            c = dataSource.getConnection();
            stmt = c.prepareCall("{? = call invalidation_constants.get_inv_dbms_pipe_size() }");
            stmt.registerOutParameter(1, 4);
            stmt.execute();
            i = stmt.getInt(1);
        }
        finally
        {
            if(stmt != null)
                try
                {
                    stmt.close();
                }
                catch(Exception e) { }
            if(c != null)
                try
                {
                    c.close();
                }
                catch(Exception e) { }
        }
        return i;
    }

    static String getDBHostnames(DataSource dataSource)
        throws SQLException
    {
        Connection c = null;
        CallableStatement stmt = null;
        String s;
        try
        {
            c = dataSource.getConnection();
            stmt = c.prepareCall("{? = call invalidation_constants.get_db_hostnames() }");
            stmt.registerOutParameter(1, 12);
            stmt.execute();
            s = stmt.getString(1);
        }
        finally
        {
            if(stmt != null)
                try
                {
                    stmt.close();
                }
                catch(Exception e) { }
            if(c != null)
                try
                {
                    c.close();
                }
                catch(Exception e) { }
        }
        return s;
    }

    private static void close(Connection con)
    {
        if(con != null)
            try
            {
                con.close();
            }
            catch(Exception e)
            {
                if(DBG)
                    log.debug("connection close failure", e);
            }
    }

    private static void close(Statement stmt)
    {
        if(stmt != null)
            try
            {
                stmt.close();
            }
            catch(Exception e)
            {
                if(DBG)
                    log.debug("statment close failure", e);
            }
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
                if(DBG)
                    log.debug("result set close failure", e);
            }
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

    static 
    {
        log = Logit.getInstance(com.fitechlabs.dbind.impl.InvSQL.class);
        DBG = log.ison();
    }
}

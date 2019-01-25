// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InvalidationSender.java

package com.fitechlabs.dbind.impl;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public class InvalidationSender
{

    public InvalidationSender()
    {
    }

    public static String sendMessage(String address, int port, String inMessage, int timeout)
        throws SQLException
    {
        String message;
        long beginTimestamp = System.currentTimeMillis();
        message = inMessage + ":" + beginTimestamp;
        Vector list;
        Object info[];
        list = getRecycleList(address, port, timeout);
        info = findSocket(list);
        if(info == null)
            break MISSING_BLOCK_LABEL_61;
        return trySocket(info, message);
        IOException ioe;
        ioe;
        info = createSocket(address, port, timeout, list);
        return trySocket(info, message);
        InterruptedIOException iioe;
        iioe;
        throw new SQLException("timeout " + timeout + " msec exceeded on " + address + ":" + port);
        Exception e;
        e;
        throw new SQLException("thrown: " + e.toString());
    }

    private static String trySocket(Object info[], String message)
        throws IOException, ClassNotFoundException
    {
        boolean recycled = false;
        String s;
        ObjectOutputStream output = (ObjectOutputStream)info[0];
        ObjectInputStream input = (ObjectInputStream)info[1];
        output.writeObject(message);
        output.flush();
        String result = (String)input.readObject();
        info[4] = getTime();
        recycleSocket(info);
        recycled = true;
        s = result;
        if(!recycled)
            discardSocket(info);
        return s;
        Exception exception;
        exception;
        if(!recycled)
            discardSocket(info);
        throw exception;
    }

    private static Vector getRecycleList(String address, int port, int timeout)
    {
        String key = address + ":" + port + "." + timeout;
        Vector list = null;
        synchronized(map)
        {
            list = (Vector)map.get(key);
            if(list == null)
                map.put(key, list = new Vector());
        }
        return list;
    }

    private static Object[] findSocket(Vector list)
    {
        Vector vector = list;
        JVM INSTR monitorenter ;
        Object info[];
        int n = list.size();
        if(n <= 0)
            break MISSING_BLOCK_LABEL_35;
        info = (Object[])list.elementAt(n - 1);
        list.removeElementAt(n - 1);
        return info;
        vector;
        JVM INSTR monitorexit ;
          goto _L1
        Exception exception;
        exception;
        throw exception;
_L1:
        return null;
    }

    private static Object[] createSocket(String address, int port, int timeout, Vector list)
        throws IOException
    {
        Socket socket = new Socket(address, port);
        socket.setSoTimeout(timeout);
        ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        output.flush();
        ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        Object info[] = {
            output, input, socket, list, getTime()
        };
        return info;
    }

    private static void recycleSocket(Object info[])
    {
        Vector list = (Vector)info[3];
        synchronized(list)
        {
            list.addElement(((Object) (info)));
        }
    }

    private static void discardSocket(Object info[])
    {
        if(info == null)
            return;
        ObjectOutputStream output = (ObjectOutputStream)info[0];
        ObjectInputStream input = (ObjectInputStream)info[1];
        Socket socket = (Socket)info[2];
        if(input != null)
            try
            {
                input.close();
            }
            catch(IOException ioe) { }
        if(output != null)
            try
            {
                output.close();
            }
            catch(IOException ioe) { }
        if(socket != null)
            try
            {
                socket.close();
            }
            catch(IOException ioe) { }
    }

    public static int cleanTimedOutSockets(int unusedPeroidSecs)
        throws SQLException
    {
        long minInterval = unusedPeroidSecs * 1000;
        if(System.currentTimeMillis() - lastCleanupTimestamp < minInterval)
            return -1;
        int discarded = 0;
        Vector lists[] = null;
        synchronized(map)
        {
            lists = new Vector[map.size()];
            int i = 0;
            for(Enumeration enum = map.elements(); enum.hasMoreElements();)
            {
                lists[i] = (Vector)enum.nextElement();
                i++;
            }

        }
        int i = 0;
        do
        {
            if(i >= lists.length)
                break;
            Vector list = lists[i];
            synchronized(list)
            {
                int listSize = list.size();
                for(int j = 0; j < listSize; j++)
                {
                    Object info[] = (Object[])list.get(j);
                    if(System.currentTimeMillis() - ((Long)info[4]).longValue() > minInterval)
                    {
                        discardSocket(info);
                        discarded++;
                        list.removeElementAt(j--);
                        listSize--;
                    }
                }

            }
            i++;
        } while(true);
        lastCleanupTimestamp = System.currentTimeMillis();
        return discarded;
    }

    public static String infos()
        throws SQLException
    {
        Vector lists[] = null;
        String keys[] = null;
        int i;
        synchronized(map)
        {
            lists = new Vector[map.size()];
            keys = new String[map.size()];
            i = 0;
            for(Enumeration enum = map.keys(); enum.hasMoreElements();)
            {
                keys[i] = (String)enum.nextElement();
                lists[i] = (Vector)map.get(keys[i]);
                i++;
            }

        }
        StringBuffer sb = new StringBuffer();
        sb.append("sinceLastCheck=").append((System.currentTimeMillis() - lastCleanupTimestamp) / 1000L).append("s");
        i = 0;
        do
        {
            if(i >= lists.length)
                break;
            Vector list = lists[i];
            synchronized(list)
            {
                int listSize = list.size();
                for(int j = 0; j < listSize; j++)
                {
                    Object info[] = (Object[])list.get(j);
                    if(j == 0)
                        sb.append(" ").append(keys[i]);
                    int port = ((Socket)info[2]).getLocalPort();
                    long sinceLast = System.currentTimeMillis() - ((Long)info[4]).longValue();
                    sb.append("[").append(port).append(",").append(sinceLast / 1000L).append("s]");
                }

            }
            i++;
        } while(true);
        return sb.toString();
    }

    private static Long getTime()
    {
        return new Long(System.currentTimeMillis());
    }

    public static int getRequestSequence()
    {
        if(dbmsPipeInvSeq == 0xf4240)
            dbmsPipeInvSeq = 0;
        return ++dbmsPipeInvSeq;
    }

    public static String getRequestPipeName(long serverId)
        throws SQLException
    {
        Long sId;
        Object serverInfo[];
        Connection conn;
        CallableStatement call;
        sId = new Long(serverId);
        serverInfo = (Object[])invServerInfos.get(sId);
        if(serverInfo != null)
            break MISSING_BLOCK_LABEL_408;
        if(pipeSize != -1)
            break MISSING_BLOCK_LABEL_377;
        conn = DriverManager.getConnection("jdbc:default:connection:");
        call = null;
        try
        {
            call = conn.prepareCall("{? = call invalidation_constants.get_db_hostnames() }");
            call.registerOutParameter(1, 12);
            call.execute();
            String names = call.getString(1);
            if(names != null)
            {
                String name;
                for(StringTokenizer tokens = new StringTokenizer(names, ","); tokens.hasMoreTokens(); databaseHostNames.put(name, name))
                    name = tokens.nextToken().trim().toUpperCase();

            }
        }
        catch(SQLException e)
        {
            throw new SQLException("SQLException thrown when getting dbms pipe size: " + e.getMessage());
        }
        closeStatement(call);
        break MISSING_BLOCK_LABEL_184;
        Exception exception;
        exception;
        closeStatement(call);
        throw exception;
        try
        {
            databaseHostName = InetAddress.getLocalHost().getHostName().toUpperCase();
        }
        catch(UnknownHostException e)
        {
            throw new SQLException("UnknownHostException thrown when getting host name: " + e.getMessage());
        }
        if(!databaseHostNames.isEmpty() && !databaseHostNames.containsKey(databaseHostName))
            throw new SQLException("Database host lists defined in invalidation_constants package should be either empty or contains " + databaseHostName + " to allow cache invalidation request to be sent.");
        try
        {
            call = conn.prepareCall("{? = call invalidation_constants.get_inv_dbms_pipe_size() }");
            call.registerOutParameter(1, 4);
            call.execute();
            pipeSize = call.getInt(1);
        }
        catch(SQLException e)
        {
            throw new SQLException("SQLException thrown when getting dbms pipe size: " + e.getMessage());
        }
        closeStatement(call);
        break MISSING_BLOCK_LABEL_377;
        Exception exception1;
        exception1;
        closeStatement(call);
        throw exception1;
        invServerInfos.put(sId, ((Object) (serverInfo = (new Object[] {
            createOrderList(serverId), new int[] {
                0
            }
        }))));
        Vector pipeNames = (Vector)serverInfo[0];
        int next[] = (int[])serverInfo[1];
        String pipeName = (String)pipeNames.get(next[0]);
        next[0] = (next[0] + 1) % pipeSize;
        return pipeName;
    }

    private static Vector createOrderList(long serverId)
    {
        Vector orderList = new Vector();
        if(databaseHostNames.isEmpty())
        {
            for(int i = 0; i < pipeSize; i++)
                orderList.add("s$" + serverId + "." + i);

            return orderList;
        }
        for(int i = 0; i < pipeSize; i++)
            orderList.add("s$" + databaseHostName + "." + serverId + "." + i);

        return orderList;
    }

    private static void closeStatement(Statement stmt)
    {
        try
        {
            if(stmt != null)
                stmt.close();
        }
        catch(SQLException ignore) { }
    }

    private static void closeResultSet(ResultSet rs)
    {
        try
        {
            if(rs != null)
                rs.close();
        }
        catch(SQLException ignore) { }
    }

    private static Hashtable map = new Hashtable(10);
    private static long lastCleanupTimestamp = System.currentTimeMillis();
    static int dbmsPipeInvSeq = 0;
    static Random random = new Random(System.currentTimeMillis());
    static int pipeSize = -1;
    static String databaseHostName = null;
    static Hashtable databaseHostNames = new Hashtable();
    static Hashtable invServerInfos = new Hashtable();

}

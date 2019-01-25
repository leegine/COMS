// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SocketPoolAccessor.java

package com.fitechlabs.xtrade.kernel.comm.client;

import com.fitechlabs.xtrade.kernel.message.Request;
import com.fitechlabs.xtrade.kernel.message.Response;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.util.*;

// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.client:
//            InitialConnectException, CommunicationException, ClientSideException, ServerAccessor, 
//            ServerException, ClientLogging

public class SocketPoolAccessor
    implements ServerAccessor
{
    private static class PerAddressInfo
    {

        private ActiveSocketInfo waitForOrCreateSocket(int maxsize)
            throws IOException
        {
            ActiveSocketInfo newInfo = this;
            JVM INSTR monitorenter ;
            ActiveSocketInfo info;
            while(currentCount >= maxsize && freeList.size() <= 0) 
                try
                {
                    wait(timeout);
                }
                catch(InterruptedException ie)
                {
                    ClientLogging.warn(ie.toString());
                }
            int n = freeList.size();
            if(n <= 0)
                break MISSING_BLOCK_LABEL_83;
            info = (ActiveSocketInfo)freeList.remove(n - 1);
            return info;
            if(currentCount >= maxsize)
                throw new IllegalStateException("freelist empty and count still > maxsize");
            currentCount++;
            newInfo;
            JVM INSTR monitorexit ;
              goto _L1
            Exception exception;
            exception;
            throw exception;
_L1:
            newInfo = null;
            ActiveSocketInfo activesocketinfo;
            newInfo = new ActiveSocketInfo();
            activesocketinfo = newInfo;
            if(newInfo == null)
                decrementCount();
            return activesocketinfo;
            Exception exception1;
            exception1;
            if(newInfo == null)
                decrementCount();
            throw exception1;
        }

        private void decrementCount()
        {
            synchronized(this)
            {
                currentCount--;
                notify();
            }
        }

        private void addToFreeList(ActiveSocketInfo info)
        {
            synchronized(this)
            {
                freeList.add(info);
                notify();
            }
        }

        private String address;
        private int port;
        private int timeout;
        private int currentCount;
        private final int bufferSize;
        private List freeList;








        private PerAddressInfo(String address, int port, int timeout, int bufferSize)
        {
            currentCount = 0;
            freeList = new ArrayList();
            this.address = address;
            this.port = port;
            this.timeout = timeout;
            this.bufferSize = bufferSize;
        }

    }


    public SocketPoolAccessor(String address, int port, int timeout)
    {
        this.address = address;
        this.port = port;
        this.timeout = timeout;
        maxsize = 4;
        bufferSize = 1024;
    }

    public SocketPoolAccessor(String address, int port)
    {
        this(address, port, 60000);
    }

    public SocketPoolAccessor(String url)
    {
        int i = url.lastIndexOf('/');
        int j = url.lastIndexOf(':');
        if(i < 0 || j < 0)
        {
            throw new IllegalArgumentException("url not in format 'sockpool://address:port'");
        } else
        {
            String sport = url.substring(j + 1);
            port = Integer.parseInt(sport);
            timeout = 60000;
            address = url.substring(i + 1, j);
            maxsize = 4;
            bufferSize = 1024;
            return;
        }
    }

    public void setMaxSize(int maxsize)
    {
        this.maxsize = maxsize;
    }

    public void setTimeOut(int timeout)
    {
        this.timeout = timeout;
    }

    public void setBufferSize(int bufferSize)
    {
        this.bufferSize = bufferSize;
    }

    public String toString()
    {
        return "SocketPool('" + address + ":" + port + "|timeout=" + timeout + ",maxSize=" + maxsize + ",bufferSize=" + bufferSize + ")";
    }

    public Response doRequestO(String xmlRequest)
        throws CommunicationException, ServerException
    {
        Object o = doRequest(new String[] {
            "O", xmlRequest
        });
        return (Response)o;
    }

    public Response doRequest(Request requestObj)
        throws CommunicationException, ServerException
    {
        Object o = doRequest(new Object[] {
            "O", requestObj
        });
        return (Response)o;
    }

    public String doRequest(String xmlRequest)
        throws CommunicationException, ServerException
    {
        Object o = doRequest(((Object []) (new String[] {
            "S", xmlRequest
        })));
        return (String)o;
    }

    private Object doRequest(Object message[])
        throws CommunicationException, ServerException
    {
        PerAddressInfo perAddr = getPerAddressInfo(address, port, timeout, bufferSize);
_L1:
        PerAddressInfo.ActiveSocketInfo sockInfo;
        sockInfo = perAddr.waitForOrCreateSocket(maxsize);
        boolean wasNew = PerAddressInfo.ActiveSocketInfo.access._mth100(sockInfo);
        if(wasNew)
            break MISSING_BLOCK_LABEL_123;
        return PerAddressInfo.ActiveSocketInfo.access._mth200(sockInfo, message);
        InterruptedIOException iioe;
        iioe;
        ClientLogging.debug(sockInfo.toString() + " discarding socket: " + iioe.toString());
        throw iioe;
        IOException eof;
        eof;
        ClientLogging.debug(sockInfo.toString() + " discarding socket: " + eof.toString());
          goto _L1
        return PerAddressInfo.ActiveSocketInfo.access._mth200(sockInfo, message);
        SocketException jnce;
        jnce;
        throw new InitialConnectException("createSocket failed", jnce);
        InterruptedIOException iioe;
        iioe;
        throw new CommunicationException("timeout " + timeout + " msec exceeded on " + address + ":" + port);
        Exception excep;
        excep;
        ClientLogging.warn("SocketPoolAccessor.doRequest() client side exception", excep);
        throw new ClientSideException(excep);
    }

    private PerAddressInfo getPerAddressInfo(String address, int port, int timeout, int bufferSize)
    {
        String key = address + ":" + port + "." + timeout + "." + bufferSize;
        Hashtable hashtable = map;
        JVM INSTR monitorenter ;
        PerAddressInfo info;
        info = (PerAddressInfo)map.get(key);
        if(info == null)
        {
            info = new PerAddressInfo(address, port, timeout, bufferSize);
            map.put(key, info);
        }
        return info;
        Exception exception;
        exception;
        throw exception;
    }

    public static final long serialVersionUID = 2L;
    private String address;
    private int port;
    private int timeout;
    private int maxsize;
    private int bufferSize;
    public static final String RETURN_STRING = "S";
    public static final String RETURN_OBJECT = "O";
    public static final int DEFAULT_TIMEOUT = 60000;
    public static final int DEFAULT_MAX_SIZE = 4;
    public static final int DEFAULT_BUFFER_SIZE = 1024;
    public static final String URL_STUB = "sockpool:";
    private static Hashtable map = new Hashtable(10);

}

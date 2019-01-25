// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FailoverAccessor.java

package com.fitechlabs.xtrade.kernel.comm.client;

import com.fitechlabs.xtrade.kernel.message.Request;
import com.fitechlabs.xtrade.kernel.message.Response;

// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.client:
//            ServerAccessor, SocketPoolAccessor, HttpServerAccessor, InitialConnectException, 
//            CommunicationException, ServerException, RequestProxy, OrderShuffler, 
//            ClientLogging

public class FailoverAccessor
    implements ServerAccessor
{

    public FailoverAccessor(String urls[])
    {
        this(urls, 10000L);
    }

    public FailoverAccessor(String urls[], long timeoutOnFailureMillis)
    {
        accessors = new ServerAccessor[urls.length];
        for(int i = 0; i < urls.length; i++)
            if(urls[i].startsWith("sockpool:"))
                accessors[i] = new SocketPoolAccessor(urls[i]);
            else
                accessors[i] = new HttpServerAccessor(urls[i]);

        this.timeoutOnFailureMillis = timeoutOnFailureMillis;
        notbefore = new long[accessors.length];
    }

    public FailoverAccessor(ServerAccessor accessors[])
    {
        this(accessors, 10000L);
    }

    public FailoverAccessor(ServerAccessor accessors[], long timeoutOnFailureMillis)
    {
        this.accessors = accessors;
        this.timeoutOnFailureMillis = timeoutOnFailureMillis;
        notbefore = new long[accessors.length];
    }

    public String toString()
    {
        StringBuffer b = new StringBuffer();
        b.append("Failover(onfail=");
        b.append((double)timeoutOnFailureMillis / 1000D);
        for(int i = 0; i < accessors.length; i++)
        {
            b.append(",");
            b.append(accessors[i].toString());
        }

        b.append(")");
        return b.toString();
    }

    public Response doRequestO(String xmlRequest)
        throws CommunicationException, ServerException
    {
        RequestProxy proxy = RequestProxy.getStringObjectRequestProxy();
        return (Response)doRequest(proxy, xmlRequest);
    }

    public Response doRequest(Request xmlRequest)
        throws CommunicationException, ServerException
    {
        RequestProxy proxy = RequestProxy.getObjectObjectRequestProxy();
        return (Response)doRequest(proxy, xmlRequest);
    }

    public String doRequest(String xmlRequest)
        throws CommunicationException, ServerException
    {
        RequestProxy proxy = RequestProxy.getStringStringRequestProxy();
        return (String)doRequest(proxy, xmlRequest);
    }

    protected Object doRequest(RequestProxy proxy, Object request)
        throws CommunicationException, ServerException
    {
        return doRequestFailOver(proxy, request, getRoundRobinOrder());
    }

    protected int[] getRoundRobinOrder()
    {
        int hash = nexthash++ & 0x7fffffff;
        return OrderShuffler.shuffle(accessors.length, hash);
    }

    protected Object doRequestFailOver(RequestProxy proxy, Object request, int order[])
        throws CommunicationException, ServerException
    {
        long beginDoRequestFailoverTstamp;
        Exception lastException;
        int n;
        long now;
        int j;
        beginDoRequestFailoverTstamp = System.currentTimeMillis();
        ClientLogging.debug("FailoverAccessor - begin doRequestFailOver at [A].");
        lastException = null;
        n = order.length;
        now = System.currentTimeMillis();
        j = 0;
_L5:
        int o;
        if(j >= 2)
            break; /* Loop/switch isn't completed */
        o = 0;
_L3:
        if(o >= n) goto _L2; else goto _L1
_L1:
        int i;
        i = order[o];
        if(i >= accessors.length)
            continue; /* Loop/switch isn't completed */
        Object response;
        if(notbefore[i] != 0L && now <= notbefore[i])
            continue; /* Loop/switch isn't completed */
        response = proxy.doRequest(accessors[i], request);
        notbefore[i] = 0L;
        ClientLogging.debug("FailoverAccessor - end doRequestFailOver at [B]. [B]-[A]=" + (System.currentTimeMillis() - beginDoRequestFailoverTstamp) + "ms.");
        return response;
        InitialConnectException ice;
        ice;
        notbefore[i] = Math.max(now + timeoutOnFailureMillis, notbefore[i]);
        lastException = ice;
        ClientLogging.info("FailoverAccessor - Server " + i + " could not be reached, " + accessors[i], ice);
        o++;
          goto _L3
_L2:
        if(lastException != null)
            break; /* Loop/switch isn't completed */
        ClientLogging.debug("FailoverAccessor - Resetting timeouts.");
        for(int a = 0; a < notbefore.length; a++)
            notbefore[a] = 0L;

        j++;
        if(true) goto _L5; else goto _L4
_L4:
        ClientLogging.info("FailoverAccessor - None of the servers could be reached.", lastException);
        throw new InitialConnectException("None of the servers could be reached.", lastException);
    }

    public ServerAccessor[] getServerAccessors()
    {
        return accessors;
    }

    public static final long serialVersionUID = 1L;
    protected ServerAccessor accessors[];
    private long timeoutOnFailureMillis;
    private int nexthash;
    private long notbefore[];
    public static final long DEFAULT_TIMEOUT_ON_FAIL = 10000L;
}

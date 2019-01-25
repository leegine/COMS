// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OrderedFailoverAccessor.java

package com.fitechlabs.xtrade.kernel.comm.client;


// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.client:
//            FailoverAccessor, CommunicationException, ServerException, ServerAccessor, 
//            RequestProxy

public class OrderedFailoverAccessor extends FailoverAccessor
{

    private int[] initTryOrder(int length)
    {
        int order[] = new int[length];
        for(int i = 0; i < length; i++)
            order[i] = i;

        return order;
    }

    public OrderedFailoverAccessor(String urls[])
    {
        super(urls);
        tryOrder = initTryOrder(urls.length);
    }

    public OrderedFailoverAccessor(String urls[], long timeoutOnFailureMillis)
    {
        super(urls, timeoutOnFailureMillis);
        tryOrder = initTryOrder(urls.length);
    }

    public OrderedFailoverAccessor(ServerAccessor accessors[])
    {
        super(accessors);
        tryOrder = initTryOrder(accessors.length);
    }

    public OrderedFailoverAccessor(ServerAccessor accessors[], long timeoutOnFailureMillis)
    {
        super(accessors, timeoutOnFailureMillis);
        tryOrder = initTryOrder(accessors.length);
    }

    protected Object doRequest(RequestProxy proxy, Object request)
        throws CommunicationException, ServerException
    {
        return doRequestFailOver(proxy, request, tryOrder);
    }

    public static final long serialVersionUID = 1L;
    private final int tryOrder[];
}

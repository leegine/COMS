// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AffinityAccessor.java

package com.fitechlabs.xtrade.kernel.comm.client;

import com.fitechlabs.xtrade.kernel.message.Request;
import com.fitechlabs.xtrade.kernel.message.Response;
import java.lang.reflect.Field;

// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.client:
//            FailoverAccessor, CommunicationException, ServerException, RequestProxy, 
//            SessionIdEncoder, ServerAccessor

public class AffinityAccessor extends FailoverAccessor
{

    protected String getTagName()
    {
        return tagname;
    }

    public AffinityAccessor(String tagname, String urls[])
    {
        this(tagname, urls, 10000L);
    }

    public AffinityAccessor(String tagname, String urls[], long timeoutOnFailureMillis)
    {
        super(urls, timeoutOnFailureMillis);
        initTagname(tagname);
    }

    public AffinityAccessor(String tagname, ServerAccessor accessors[])
    {
        this(tagname, accessors, 10000L);
    }

    public AffinityAccessor(String tagname, ServerAccessor accessors[], long timeoutOnFailureMillis)
    {
        super(accessors, timeoutOnFailureMillis);
        initTagname(tagname);
    }

    private void initTagname(String tagname)
    {
        this.tagname = tagname;
        taghead = "<" + tagname + ">";
        tagtail = "</" + tagname + ">";
    }

    public String toString()
    {
        return "Affinity('" + tagname + "'," + super.toString() + ")";
    }

    public Response doRequestO(String xmlRequest)
        throws CommunicationException, ServerException
    {
        RequestProxy proxy = RequestProxy.getStringObjectRequestProxy();
        return (Response)doRequestWithTagValue(proxy, xmlRequest, findTagValue(xmlRequest));
    }

    public Response doRequest(Request requestObj)
        throws CommunicationException, ServerException
    {
        RequestProxy proxy = RequestProxy.getObjectObjectRequestProxy();
        return (Response)doRequestWithTagValue(proxy, requestObj, findTagValue(requestObj));
    }

    public String doRequest(String xmlRequest)
        throws CommunicationException, ServerException
    {
        RequestProxy proxy = RequestProxy.getStringStringRequestProxy();
        return (String)doRequestWithTagValue(proxy, xmlRequest, findTagValue(xmlRequest));
    }

    private Object doRequestWithTagValue(RequestProxy proxy, Object request, String tagvalue)
        throws CommunicationException, ServerException
    {
        int order[] = deriveOrderFromTagValue(tagvalue);
        return super.doRequestFailOver(proxy, request, order);
    }

    protected int[] deriveOrderFromTagValue(String tagvalue)
    {
        int order[] = SessionIdEncoder.decodeServerTryOrder(tagvalue);
        if(order == null)
            return getRoundRobinOrder();
        else
            return order;
    }

    private String findTagValue(String xml)
    {
        int i = xml.indexOf(taghead);
        if(i < 0)
            return null;
        i += taghead.length();
        int j = xml.indexOf(tagtail, i);
        if(j < 0)
            return null;
        else
            return xml.substring(i, j);
    }

    private String findTagValue(Request request)
    {
        return (String)request.getClass().getField(tagname).get(request);
        Exception e;
        e;
        return null;
    }

    public static final long serialVersionUID = 1L;
    private String tagname;
    private String taghead;
    private String tagtail;
}

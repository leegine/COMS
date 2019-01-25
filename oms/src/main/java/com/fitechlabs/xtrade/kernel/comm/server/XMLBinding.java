// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMLBinding.java

package com.fitechlabs.xtrade.kernel.comm.server;

import com.fitechlabs.xtrade.kernel.message.Request;
import com.fitechlabs.xtrade.kernel.message.Response;
import com.fitechlabs.xtrade.kernel.util.xml.*;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.server:
//            Handler

public class XMLBinding
    implements TagnameResolver
{

    public XMLBinding()
    {
        requestToHandler = Collections.synchronizedMap(new HashMap());
        responseClasses = Collections.synchronizedSet(new HashSet());
        classMap = Collections.synchronizedMap(new HashMap());
        converter = new XMLToObjectConverter(this);
    }

    public void addRequest(Class requestClass, Handler handlerInstance)
    {
        synchronized(requestToHandler)
        {
            if(requestToHandler.containsKey(requestClass))
                throw new IllegalArgumentException(String.valueOf(requestClass) + " has already been added.");
            if(!(com.fitechlabs.xtrade.kernel.message.Request.class).isAssignableFrom(requestClass))
                throw new IllegalArgumentException("not a Request subclass: " + requestClass);
            requestToHandler.put(requestClass, handlerInstance);
            registerClassTags(requestClass);
        }
    }

    public void addResponse(Class responseClass)
    {
        if(!(com.fitechlabs.xtrade.kernel.message.Response.class).isAssignableFrom(responseClass))
        {
            throw new IllegalArgumentException("not a Response subclass: " + responseClass);
        } else
        {
            responseClasses.add(responseClass);
            registerClassTags(responseClass);
            return;
        }
    }

    public Request toRequest(String xml)
        throws Exception
    {
        return (Request)converter.toObject(converter.toDocument(xml));
    }

    public Request toRequest(InputStream xml)
        throws Exception
    {
        return (Request)converter.toObject(converter.toDocument(xml));
    }

    public Response toResponse(String xml)
        throws Exception
    {
        return (Response)converter.toObject(converter.toDocument(xml));
    }

    public Response toResponse(InputStream xml)
        throws Exception
    {
        return (Response)converter.toObject(converter.toDocument(xml));
    }

    public String toXML(Response response)
        throws Exception
    {
        if(response == null)
            throw new IllegalArgumentException("cannot convert null response");
        if(!responseClasses.contains(response.getClass()))
            throw new Exception("response class not recognized: " + response.getClass());
        else
            return ObjectToXMLConverter.toXMLString(response);
    }

    public String toXML(Request request)
        throws Exception
    {
        if(request == null)
            throw new IllegalArgumentException("cannot convert null request");
        if(!requestToHandler.containsKey(request.getClass()))
            throw new Exception("request class not recognized: " + request.getClass());
        else
            return ObjectToXMLConverter.toXMLString(request);
    }

    public Response handleRequest(Request request)
        throws Exception
    {
        if(request == null)
            throw new IllegalArgumentException("cannot handle null request");
        Handler handler = (Handler)requestToHandler.get(request.getClass());
        if(handler == null)
            throw new Exception("no handler found for request class " + request.getClass());
        else
            return handler.handle(request);
    }

    public Class getClass(String contextTag, String tagName)
    {
        Map tagMap = (Map)classMap.get(contextTag);
        if(tagMap == null)
            return null;
        else
            return (Class)tagMap.get(tagName);
    }

    private void registerClassTags(Class requestClass)
    {
        String tagname = getFieldOrNull(requestClass, "TAGNAME");
        if(tagname != null)
        {
            register(tagname, null, requestClass);
            String p_type = getFieldOrNull(requestClass, "P_TYPE");
            if(p_type != null)
                register(tagname, p_type, requestClass);
        }
    }

    private static String getFieldOrNull(Class msgClass, String fieldName)
    {
        return (String)msgClass.getField(fieldName).get(null);
        Exception ignored;
        ignored;
        return null;
    }

    private void register(String contextTagName, String tagName, Class instanceClass)
    {
        synchronized(classMap)
        {
            Map tagMap = (Map)classMap.get(contextTagName);
            if(tagMap == null)
            {
                tagMap = Collections.synchronizedMap(new HashMap());
                classMap.put(contextTagName, tagMap);
            }
            tagMap.put(tagName, instanceClass);
        }
    }

    private Map requestToHandler;
    private Set responseClasses;
    private Map classMap;
    private XMLToObjectConverter converter;
}

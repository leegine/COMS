// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WrappingAccessor.java

package com.fitechlabs.xtrade.kernel.comm.client;

import com.fitechlabs.xtrade.kernel.message.Request;
import com.fitechlabs.xtrade.kernel.message.Response;

// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.client:
//            ServerAccessor, CommunicationException, ServerException

public class WrappingAccessor
    implements ServerAccessor
{

    public WrappingAccessor(String header, String footer, ServerAccessor delegate)
    {
        this.header = header;
        this.footer = footer;
        _flddelegate = delegate;
    }

    public String toString()
    {
        return "Wrap('" + header + "'," + _flddelegate + ",'" + footer + "')";
    }

    public Response doRequestO(String xmlRequest)
        throws CommunicationException, ServerException
    {
        int i = xmlRequest.indexOf("<request");
        if(i >= 0)
            return _flddelegate.doRequestO(xmlRequest.substring(0, i) + header + xmlRequest.substring(i) + footer);
        else
            return _flddelegate.doRequestO(header + xmlRequest + footer);
    }

    public Response doRequest(Request xmlRequest)
        throws CommunicationException, ServerException
    {
        throw new UnsupportedOperationException("Method doRequest(Request) not implemented for WrappingAccessor - use xml version instead.");
    }

    public String doRequest(String xmlRequest)
        throws CommunicationException, ServerException
    {
        int i = xmlRequest.indexOf("<request");
        if(i >= 0)
            return _flddelegate.doRequest(xmlRequest.substring(0, i) + header + xmlRequest.substring(i) + footer);
        else
            return _flddelegate.doRequest(header + xmlRequest + footer);
    }

    public static final long serialVersionUID = 1L;
    private String header;
    private String footer;
    private ServerAccessor _flddelegate;
}

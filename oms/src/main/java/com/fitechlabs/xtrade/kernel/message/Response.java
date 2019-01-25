// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Response.java

package com.fitechlabs.xtrade.kernel.message;


// Referenced classes of package com.fitechlabs.xtrade.kernel.message:
//            Message, ErrorInfo

public class Response extends Message
{

    public Response()
    {
    }

    /**
     * @deprecated Method Response is deprecated
     */

    public Response(boolean success, String text, String error_text)
    {
    }

    /**
     * @deprecated Method Response is deprecated
     */

    public Response(Response response)
    {
    }

    /**
     * @deprecated Method setSuccess is deprecated
     */

    public void setSuccess(boolean flag)
    {
    }

    /**
     * @deprecated Method wrap is deprecated
     */

    public Response wrap()
    {
        return this;
    }

    public String getPType()
    {
        return "xtrade_response";
    }

    public static final long serialVersionUID = 2L;
    public static final String TAGNAME = "response";
    public static final String PTYPE = "xtrade_response";
    public ErrorInfo server_exception;
}

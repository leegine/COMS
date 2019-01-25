// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServerException.java

package com.fitechlabs.xtrade.kernel.comm.client;


// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.client:
//            AccessorException

public class ServerException extends AccessorException
{

    public ServerException(String error_code, String error_tag, String error_debug_info)
    {
        super("Error " + error_code + ": " + error_tag + "; " + error_debug_info);
        this.error_code = error_code;
        this.error_tag = error_tag;
        this.error_debug_info = error_debug_info;
    }

    public String getErrorCode()
    {
        return error_code;
    }

    public String getErrorTag()
    {
        return error_tag;
    }

    public String getErrorDebugInfo()
    {
        return error_debug_info;
    }

    private String error_code;
    private String error_tag;
    private String error_debug_info;
}

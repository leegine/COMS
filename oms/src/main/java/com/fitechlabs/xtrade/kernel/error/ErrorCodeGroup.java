// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ErrorCodeGroup.java

package com.fitechlabs.xtrade.kernel.error;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.message.Response;

/**
 * @deprecated Class ErrorCodeGroup is deprecated
 */

public final class ErrorCodeGroup
{

    /**
     * @deprecated Method ErrorCodeGroup is deprecated
     */

    public ErrorCodeGroup(int start, int length, String tag, String details)
    {
        code_range_start = start;
        code_range_end = start + length;
        code_range_tag = tag;
        code_range_details = details;
    }

    /**
     * @deprecated Method createErrorResponse is deprecated
     */

    public Response createErrorResponse(int offset, String tag, String debug_info)
    {
        Response r = new Response();
        r.server_exception = createErrorInfo(offset, tag, debug_info);
        return r;
    }

    /**
     * @deprecated Method createErrorInfo is deprecated
     */

    public ErrorInfo createErrorInfo(int offset, String tag, String debug_info)
    {
        int j = code_range_start + offset;
        if(j < code_range_start || j >= code_range_end)
        {
            throw new RuntimeException("offset not in range for '" + code_range_details + "': " + offset);
        } else
        {
            ErrorInfo info = new ErrorInfo();
            info.error_code = String.valueOf(j);
            info.error_tag = code_range_tag + "-" + tag;
            info.error_debug_info = (code_range_details == null ? "" : code_range_details + " - ") + debug_info;
            return info;
        }
    }

    int code_range_start;
    int code_range_end;
    String code_range_tag;
    String code_range_details;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ErrorResponseRegistry.java

package com.fitechlabs.xtrade.kernel.error;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.message.Response;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.fitechlabs.xtrade.kernel.error:
//            ErrorManager

public final class ErrorResponseRegistry
{

    private static Response defineErrorResponse(int code, String tag, String message)
    {
        Response response = new Response();
        response.server_exception = errmgr.defineErrorInfo(code, tag, message);
        return response;
    }

    private ErrorResponseRegistry()
    {
    }

    public static Response deriveResponse(Response baseErrorResponse, String dynamicDebugInfo)
    {
        if(baseErrorResponse.server_exception == null)
        {
            throw new IllegalArgumentException("not a standard error: " + baseErrorResponse);
        } else
        {
            Response resp = new Response();
            resp.server_exception = baseErrorResponse.server_exception.addText(dynamicDebugInfo);
            return resp;
        }
    }

    public static Response deriveResponse(Response baseErrorResponse, Throwable throwable)
    {
        return deriveResponse(baseErrorResponse, throwableToString(throwable));
    }

    public static Response deriveResponse(Response baseErrorResponse, String details, Throwable throwable)
    {
        String s = throwableToString(throwable);
        return deriveResponse(baseErrorResponse, s == null ? details : details + ", " + s);
    }

    private static String throwableToString(Throwable th)
    {
        if(th == null)
            return null;
        else
            return th.toString();
    }

    public static String deriveLastGaspResponse(Response baseErrorResponse, Throwable t1, Throwable t2)
    {
        ErrorInfo ei = baseErrorResponse.server_exception;
        if(ei == null)
            ei = INTERNAL_ERROR.server_exception;
        return "<?xml version='1.0' encoding='UTF-8'?>\n<response p_type=\"xtrade_response\">\n <server_exception>\n  <error_code>" + ei.error_code + "</error_code>\n" + "  <error_tag>" + ei.error_tag + "</error_tag>\n" + "  <error_debug_info>Manual last-gasp error -\n" + "    description: " + ei.error_debug_info + ",\n" + "     throwable-1: " + t1 + "\n" + "     throwable-2: " + t2 + "\n" + "   </error_debug_info>\n" + " </server_exception>\n" + "</request>\n";
    }

    public static Response[] getBaseErrors()
        throws Exception
    {
        Field f[] = (com.fitechlabs.xtrade.kernel.error.ErrorResponseRegistry.class).getFields();
        List list = new ArrayList();
        for(int i = 0; i < f.length; i++)
            if(f[i].getType() == (com.fitechlabs.xtrade.kernel.message.Response.class))
                list.add(f[i].get(com.fitechlabs.xtrade.kernel.error.ErrorResponseRegistry.class));

        return (Response[])list.toArray(new Response[list.size()]);
    }

    private static final boolean DBG = true;
    private static final boolean STK = false;
    private static final ErrorManager errmgr;
    public static final Response INTERNAL_ERROR = defineErrorResponse(51, "XT-INTERNAL_ERROR", "The server threw a runtime exception indicating faulty configuration.");
    public static final Response FILE_IO_ERROR = defineErrorResponse(52, "XT-FILE_IO_ERROR", "The named file could not be read for processing.");
    public static final Response XML_PARSE_ERROR = defineErrorResponse(101, "XT-XML_PARSE_ERROR", "Parse error during XML parsing.");
    public static final Response PTYPE_NOT_FOUND = defineErrorResponse(301, "XT-PTYPE_NOT_FOUND", "Class with the supplied p_type could not be found.");
    public static final Response OBJECT_READ_ERROR = defineErrorResponse(305, "XT-OBJECT_READ_ERROR", "An object could not be read from the input stream.");
    public static final Response REQUEST_CAST_ERROR = defineErrorResponse(306, "XT-REQUEST_CAST_ERROR", "The object received could not be cast to a Request.");
    public static final Response XML_WRITE_ERROR = defineErrorResponse(304, "XT-XML_WRITE_ERROR", "The message could not be converted to XML.");
    public static final Response HANDLER_NOT_FOUND = defineErrorResponse(501, "XT-HANDLER_NOT_FOUND", "No MessageHandler registered for this Message class.");
    public static final Response NOT_ALLOWED = defineErrorResponse(601, "XT-NOT_ALLOWED", "The operation is not allowed by the security service.");
    public static final Response HANDLER_EXCEPTION = defineErrorResponse(701, "XT-HANDLER_EXCEPTION", "The handler threw an exception during message handling.");
    public static final Response HANDLER_ON_ENTER_EXCEPTION = defineErrorResponse(801, "XT-HANDLER_ON_ENTER_EXCEPTION", "The handler threw a RuntimeException during calling onEnter() method of registered Callbacks.");
    public static final Response HANDLER_ON_EXIT_EXCEPTION = defineErrorResponse(901, "XT-HANDLER_ON_EXIT_EXCEPTION", "he handler threw a RuntimeException during calling onExit() method of registered Callbacks.");

    static 
    {
        errmgr = ErrorManager.getInstance(com.fitechlabs.xtrade.kernel.error.ErrorResponseRegistry.class);
    }
}

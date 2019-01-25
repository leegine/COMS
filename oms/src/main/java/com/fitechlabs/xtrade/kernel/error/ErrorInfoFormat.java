// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ErrorInfoFormat.java

package com.fitechlabs.xtrade.kernel.error;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import java.text.Format;
import java.text.MessageFormat;

public class ErrorInfoFormat
{

    public String toString()
    {
        return "ErrorInfoFormat(" + code + "," + tag + "," + messageFormatString + ")";
    }

    /**
     * @deprecated Method ErrorInfoFormat is deprecated
     */

    public ErrorInfoFormat(String code, String tag, String messageFormat)
    {
        this.code = code;
        error_class = "(undefined)";
        this.tag = tag;
        messageFormatString = messageFormat;
        messageFormatObject = new MessageFormat(messageFormat);
    }

    ErrorInfoFormat(String error_code, String error_class, String error_tag, String messageFormat)
    {
        code = error_code;
        this.error_class = error_class;
        tag = error_tag;
        messageFormatString = messageFormat;
        messageFormatObject = new MessageFormat(messageFormat);
    }

    public ErrorInfo format(Object arg)
    {
        return format(new Object[] {
            arg
        });
    }

    public ErrorInfo format(Object arg1, Object arg2)
    {
        return format(new Object[] {
            arg1, arg2
        });
    }

    public ErrorInfo format(Object arg1, Object arg2, Object arg3)
    {
        return format(new Object[] {
            arg1, arg2, arg3
        });
    }

    public ErrorInfo format(Object args[])
    {
        ErrorInfo ei = new ErrorInfo();
        ei.error_code = code;
        ei.error_class = error_class;
        ei.error_tag = tag;
        ei.error_message = messageFormatObject.format(((Object) (args)));
        return ei;
    }

    public String getMessageFormatString()
    {
        return messageFormatString;
    }

    private String code;
    private String error_class;
    private String tag;
    private String messageFormatString;
    private MessageFormat messageFormatObject;
}

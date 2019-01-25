// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ErrorInfo.java

package com.fitechlabs.xtrade.kernel.message;


// Referenced classes of package com.fitechlabs.xtrade.kernel.message:
//            Message

public class ErrorInfo extends Message
{

    public ErrorInfo()
    {
    }

    public String toString()
    {
        return "ErrorInfo(" + error_code + "," + (error_class == null ? "" : error_class + ".") + error_tag + "," + error_message + "," + error_debug_info + ")";
    }

    public void setErrorCode(String errorCode)
    {
        error_code = errorCode;
    }

    public String getErrorCode()
    {
        return error_code;
    }

    public String getErrorClass()
    {
        return error_class;
    }

    public void setErrorClass(String error_class)
    {
        this.error_class = error_class;
    }

    public void setErrorTag(String errorTag)
    {
        error_tag = errorTag;
    }

    public String getErrorTag()
    {
        return error_tag;
    }

    public void setErrorMessage(String errorMessage)
    {
        error_message = errorMessage;
    }

    public String getErrorMessage()
    {
        return error_message;
    }

    public void setErrorDebugInfo(String errorDebugInfo)
    {
        error_debug_info = errorDebugInfo;
    }

    public String getErrorDebugInfo()
    {
        return error_debug_info;
    }

    public ErrorInfo addText(String text)
    {
        ErrorInfo e = new ErrorInfo();
        e.error_code = error_code;
        e.error_class = error_class;
        e.error_tag = error_tag;
        e.error_message = error_message;
        e.error_debug_info = error_debug_info == null ? text : error_debug_info + '\n' + text;
        return e;
    }

    public boolean equals(Object arg0)
    {
        if(arg0 instanceof ErrorInfo)
        {
            ErrorInfo other = (ErrorInfo)arg0;
            return other.error_class.equals(error_class) && (other.error_tag.equals(error_tag) || other.error_code.equals(error_code));
        } else
        {
            return false;
        }
    }

    public String toUiString()
    {
        return getErrorTag() + "[" + getErrorClass() + "]" + "-" + getErrorMessage();
    }

    public static final long serialVersionUID = 4L;
    public String error_code;
    public String error_class;
    public String error_tag;
    public String error_message;
    public String error_debug_info;
}

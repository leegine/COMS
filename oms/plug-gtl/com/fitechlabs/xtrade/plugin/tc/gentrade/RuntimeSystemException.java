// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RuntimeSystemException.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

public class RuntimeSystemException extends RuntimeException
{

    public RuntimeSystemException()
    {
    }

    public RuntimeSystemException(String message)
    {
        this(message, null, null);
    }

    public RuntimeSystemException(String message, Throwable cause)
    {
        this(message, null, cause);
    }

    public RuntimeSystemException(ErrorInfo errorInfo)
    {
        this(null, errorInfo, null);
    }

    public RuntimeSystemException(ErrorInfo errorInfo, Throwable t)
    {
        this(null, errorInfo, t);
    }

    private RuntimeSystemException(String msg, ErrorInfo errorInfo, Throwable t)
    {
        super(errorInfo == null ? msg : errorInfo.getErrorMessage());
        m_errorInfo = errorInfo;
        m_cause = t;
    }

    public Throwable getCause()
    {
        return m_cause;
    }

    public ErrorInfo getErrorInfo()
    {
        return m_errorInfo;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(getMessage());
        ErrorInfo errInfo = getErrorInfo();
        if(errInfo != null)
        {
            sb.append(", ");
            sb.append(errInfo);
        }
        return sb.toString();
    }

    private Throwable m_cause;
    private ErrorInfo m_errorInfo;
}

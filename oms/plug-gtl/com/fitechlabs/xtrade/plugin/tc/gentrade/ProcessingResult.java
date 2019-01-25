// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProcessingResult.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

public class ProcessingResult
{

    private ProcessingResult(boolean isResultOk, ErrorInfo errorInfo)
    {
        m_isResultOk = isResultOk;
        m_errorInfo = errorInfo;
    }

    public static ProcessingResult newSuccessResultInstance()
    {
        return SUCCESS_RESULT;
    }

    public static ProcessingResult newFailedResultInstance(ErrorInfo errorInfo)
    {
        return new ProcessingResult(false, errorInfo);
    }

    public boolean isSuccessfulResult()
    {
        return m_isResultOk;
    }

    public boolean isFailedResult()
    {
        return !m_isResultOk;
    }

    public ErrorInfo getErrorInfo()
    {
        return m_errorInfo;
    }

    public String toString()
    {
        return "successful=" + isSuccessfulResult() + ", ErrorInfo = " + getErrorInfo();
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof ProcessingResult)
        {
            ProcessingResult other = (ProcessingResult)obj;
            if(isSuccessfulResult() && other.isSuccessfulResult())
                return true;
            if(isFailedResult() && other.isFailedResult())
            {
                ErrorInfo thisErrorInfo = getErrorInfo();
                ErrorInfo otherErrorInfo = other.getErrorInfo();
                return thisErrorInfo.getErrorClass().equals(otherErrorInfo.getErrorClass()) && thisErrorInfo.getErrorTag().equals(otherErrorInfo.getErrorTag());
            }
        }
        return false;
    }

    public static final ProcessingResult SUCCESS_RESULT = new ProcessingResult(true, null);
    private final boolean m_isResultOk;
    private final ErrorInfo m_errorInfo;

}

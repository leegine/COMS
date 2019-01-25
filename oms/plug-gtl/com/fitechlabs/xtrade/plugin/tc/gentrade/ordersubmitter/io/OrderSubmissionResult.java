// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OrderSubmissionResult.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResultHolder;

public class OrderSubmissionResult
    implements ProcessingResultHolder
{

    public OrderSubmissionResult(ProcessingResult processingResult)
    {
        m_processingResult = processingResult;
    }

    public ProcessingResult getProcessingResult()
    {
        return m_processingResult;
    }

    public String toString()
    {
        return getProcessingResult().toString();
    }

    private final ProcessingResult m_processingResult;
    public static final OrderSubmissionResult SUBMISSION_OK_RESULT;

    static 
    {
        SUBMISSION_OK_RESULT = new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT);
    }
}

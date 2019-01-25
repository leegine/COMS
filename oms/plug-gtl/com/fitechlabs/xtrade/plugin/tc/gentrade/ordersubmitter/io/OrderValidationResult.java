// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OrderValidationResult.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResultHolder;

public class OrderValidationResult
    implements ProcessingResultHolder
{

    public OrderValidationResult(ProcessingResult processingResult)
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
    public static final OrderValidationResult VALIDATION_OK_RESULT;

    static 
    {
        VALIDATION_OK_RESULT = new OrderValidationResult(ProcessingResult.SUCCESS_RESULT);
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeOrderValidationResult.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

public class EqTypeOrderValidationResult extends OrderValidationResult
{

    public EqTypeOrderValidationResult(ProcessingResult processingResult)
    {
        super(processingResult);
    }

    public static EqTypeOrderValidationResult getOrderValidationOkResult()
    {
        return OK_RESULT;
    }

    public static final EqTypeOrderValidationResult OK_RESULT;

    static 
    {
        OK_RESULT = new EqTypeOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeOrderSubmissionResult.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;

public class EqTypeOrderSubmissionResult extends OrderSubmissionResult
{

    public EqTypeOrderSubmissionResult(ProcessingResult processingResult)
    {
        super(processingResult);
    }

    public static EqTypeOrderSubmissionResult getOrderSubmissionOkResult()
    {
        return OK_RESULT;
    }

    public static final EqTypeOrderSubmissionResult OK_RESULT = new EqTypeOrderSubmissionResult(ProcessingResult.newSuccessResultInstance());

}

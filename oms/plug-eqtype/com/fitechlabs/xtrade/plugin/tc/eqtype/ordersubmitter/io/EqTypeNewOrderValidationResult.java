// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeNewOrderValidationResult.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;

public class EqTypeNewOrderValidationResult extends NewOrderValidationResult
{

    public EqTypeNewOrderValidationResult(ProcessingResult processingResult, long orderId)
    {
        super(processingResult, orderId);
    }

    public EqTypeNewOrderValidationResult(ProcessingResult processingResult)
    {
        super(processingResult);
    }
}

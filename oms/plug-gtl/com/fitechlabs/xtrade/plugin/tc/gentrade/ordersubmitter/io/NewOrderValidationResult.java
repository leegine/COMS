// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NewOrderValidationResult.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io:
//            OrderValidationResult

public class NewOrderValidationResult extends OrderValidationResult
{

    public NewOrderValidationResult(ProcessingResult processingResult, long orderId)
    {
        super(processingResult);
        m_orderId = orderId;
    }

    public NewOrderValidationResult(ProcessingResult processingResult)
    {
        this(processingResult, -1L);
    }

    public long getNewOrderId()
    {
        return m_orderId;
    }

    public String toString()
    {
        return "order id : " + getNewOrderId() + "," + super.toString();
    }

    private final long m_orderId;
}

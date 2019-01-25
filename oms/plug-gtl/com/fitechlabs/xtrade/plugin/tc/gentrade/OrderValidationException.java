// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OrderValidationException.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            ProcessingResult

public class OrderValidationException extends Exception
{

    public OrderValidationException(ErrorInfo errorInfo)
    {
        m_result = new OrderValidationResult(ProcessingResult.newFailedResultInstance(errorInfo));
    }

    public OrderValidationResult getValidationResult()
    {
        return m_result;
    }

    private final OrderValidationResult m_result;
}

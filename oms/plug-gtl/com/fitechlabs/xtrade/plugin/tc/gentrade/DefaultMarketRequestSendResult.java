// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultMarketRequestSendResult.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            MarketRequestSendResult, ProcessingResult

public final class DefaultMarketRequestSendResult
    implements MarketRequestSendResult
{

    private DefaultMarketRequestSendResult(ProcessingResult processingResult, long messageTokenId)
    {
        m_processingResult = processingResult;
        m_messageTokenId = messageTokenId;
    }

    public static MarketRequestSendResult newSuccessResultInstance(long messageTokenId)
    {
        return new DefaultMarketRequestSendResult(SUCCESS_INSTANCE, messageTokenId);
    }

    public static MarketRequestSendResult newFailedResultInstance(ProcessingResult processingResult)
        throws IllegalArgumentException
    {
        if(processingResult.isSuccessfulResult())
            throw new IllegalArgumentException("Invalid ProcessingResult. Result should indicate a failure. but seems to indicate a success");
        else
            return new DefaultMarketRequestSendResult(processingResult, -1L);
    }

    public static MarketRequestSendResult valueOf(ProcessingResult result, long messageTokenId)
    {
        return new DefaultMarketRequestSendResult(SUCCESS_INSTANCE, messageTokenId);
    }

    public final ProcessingResult getProcessingResult()
    {
        return m_processingResult;
    }

    public final long getMessageTokenId()
    {
        if(m_processingResult.isFailedResult())
            throw new RuntimeException("The result indicates failure and hence message id is not available.");
        else
            return m_messageTokenId;
    }

    private static final ProcessingResult SUCCESS_INSTANCE = ProcessingResult.newSuccessResultInstance();
    private final ProcessingResult m_processingResult;
    private final long m_messageTokenId;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MarketRequestSendResult.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            ProcessingResult

public interface MarketRequestSendResult
{

    public abstract ProcessingResult getProcessingResult();

    public abstract long getMessageTokenId();
}
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QuoteData.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import java.sql.Timestamp;

public interface QuoteData
{

    public abstract Timestamp getQuoteTimestamp();
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FinTransactionManager.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import java.util.Map;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            NotFoundException, FinTransaction, ProcessingResult, SubAccount, 
//            DateRangeQueryParamsSpec

public interface FinTransactionManager
{

    public abstract ProcessingResult addTransaction(FinTransaction fintransaction);

    public abstract FinTransaction getTransaction(long l)
        throws NotFoundException;

    public abstract Map getTransactions(SubAccount subaccount, long al[]);

    public abstract Map getTransactions(SubAccount subaccount, DateRangeQueryParamsSpec daterangequeryparamsspec);

    public abstract ProcessingResult undoTransaction(long l)
        throws NotFoundException;
}

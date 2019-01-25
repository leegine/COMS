// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GeneralizedFinTransactionManager.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import com.fitechlabs.dbind.ListPage;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            FinTransactionManager, NotFoundException, GeneralizedFinTransaction, ProcessingResult, 
//            SubAccount, DateRangePaginationQueryParamsSpec, FinTransactionType, FilterQueryParamsSpec, 
//            PaginationQueryParamsSpec, SortKeySpec, FinTransaction

public interface GeneralizedFinTransactionManager
    extends FinTransactionManager
{

    public abstract long createTransactionId();

    public abstract ProcessingResult addTransaction(GeneralizedFinTransaction generalizedfintransaction);

    public abstract ProcessingResult notifyTransaction(GeneralizedFinTransaction generalizedfintransaction);

    public abstract ListPage searchTransactions(SubAccount subaccount, DateRangePaginationQueryParamsSpec daterangepaginationqueryparamsspec);

    public abstract ListPage searchTransactions(SubAccount subaccount, FinTransactionType fintransactiontype, DateRangePaginationQueryParamsSpec daterangepaginationqueryparamsspec);

    public abstract ListPage searchTransactions(SubAccount subaccount, FinTransactionType afintransactiontype[], DateRangePaginationQueryParamsSpec daterangepaginationqueryparamsspec);

    public abstract ListPage searchTransactions(SubAccount subaccount, FilterQueryParamsSpec filterqueryparamsspec, PaginationQueryParamsSpec paginationqueryparamsspec, SortKeySpec sortkeyspec);

    public abstract FinTransaction getGeneralizedTransaction(long l)
        throws NotFoundException;
}

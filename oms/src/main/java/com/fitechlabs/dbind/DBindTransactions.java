// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DBindTransactions.java

package com.fitechlabs.dbind;

import javax.transaction.SystemException;
import javax.transaction.TransactionManager;

// Referenced classes of package com.fitechlabs.dbind:
//            DBindTransactionManager

public final class DBindTransactions
{

    private DBindTransactions()
    {
    }

    public static void setTransactionManager(TransactionManager transactionManager)
    {
        synchronized(com.fitechlabs.dbind.DBindTransactions.class)
        {
            if(theTxMgr != null)
                throw new IllegalStateException("TransactionManager must be set before any database operation - was previously set to " + theTxMgr);
            theTxMgr = transactionManager;
        }
    }

    public static TransactionManager getTransactionManager()
    {
        if(theTxMgr == null)
            synchronized(com.fitechlabs.dbind.DBindTransactions.class)
            {
                if(theTxMgr == null)
                    theTxMgr = new DBindTransactionManager();
            }
        return theTxMgr;
    }

    public static void setIsMutating()
    {
        TransactionManager txm = getTransactionManager();
        if(!(txm instanceof DBindTransactionManager))
        {
            throw new UnsupportedOperationException("third party transaction managers are not supported");
        } else
        {
            ((DBindTransactionManager)txm).setIsMutating();
            return;
        }
    }

    public static void setIsMutating(String tblName)
    {
        TransactionManager txm = getTransactionManager();
        if(!(txm instanceof DBindTransactionManager))
        {
            throw new UnsupportedOperationException("third party transaction managers are not supported");
        } else
        {
            ((DBindTransactionManager)txm).setIsMutating(tblName);
            return;
        }
    }

    public static boolean getIsMutating()
    {
        TransactionManager txm = getTransactionManager();
        if(!(txm instanceof DBindTransactionManager))
            throw new UnsupportedOperationException("third party transaction managers are not supported");
        else
            return ((DBindTransactionManager)txm).getIsMutating();
    }

    public static boolean getIsMutating(String tblName)
    {
        TransactionManager txm = getTransactionManager();
        if(!(txm instanceof DBindTransactionManager))
            throw new UnsupportedOperationException("third party transaction managers are not supported");
        else
            return ((DBindTransactionManager)txm).getIsMutating(tblName);
    }

    public static boolean getIsTransactionActive()
    {
        TransactionManager txm = getTransactionManager();
        int status;
        try
        {
            status = txm.getStatus();
        }
        catch(SystemException e)
        {
            throw new InternalError("can't check transaction status: " + e);
        }
        return status == 0;
    }

    private static TransactionManager theTxMgr;
}

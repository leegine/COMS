// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FinTransaction.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import java.sql.Timestamp;
import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            BusinessObject, SubAccount, FinTransactionType, FinTransactionCateg

public interface FinTransaction
    extends BusinessObject
{

    public abstract Date getDeliveryDate();

    public abstract long getId();

    public abstract double getNetAmount();

    public abstract SubAccount getSubAccount();

    public abstract FinTransactionType getFinTransactionType();

    public abstract FinTransactionCateg getFinTransactionCateg();

    public abstract Timestamp getTransactionTimestamp();
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SubAccount.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            BusinessObject, ResourceBusyException, MainAccount, SubAccountTypeEnum, 
//            SubAccountStatusEnum, Preferences, ProcessingResult, Institution

public interface SubAccount
    extends BusinessObject
{

    public abstract long getSubAccountId();

    public abstract long getAccountId();

    public abstract MainAccount getMainAccount();

    public abstract SubAccountTypeEnum getSubAccountType();

    public abstract SubAccountStatusEnum getSubAccountStatus();

    public abstract double getCashBalance();

    public abstract Date getSubAccountOpenDate();

    public abstract Date getSubAccountClosedDate();

    public abstract Preferences getPreferences();

    public abstract ProcessingResult adjustCashBalance(double d);

    /**
     * @deprecated Method serializeOperations is deprecated
     */

    public abstract void serializeOperations(boolean flag)
        throws ResourceBusyException;

    public abstract void serializeOperationsWithWait();

    public abstract void serializeOperationsWithNoWait()
        throws ResourceBusyException;

    public abstract Institution getInstitution();
}

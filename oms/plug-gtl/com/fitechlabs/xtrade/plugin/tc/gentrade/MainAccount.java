// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MainAccount.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            BusinessObject, NotFoundException, ResourceBusyException, PersonNameDetails, 
//            Branch, ContactInfoDetails, SubAccount, SubAccountTypeEnum, 
//            Participant, Preferences, MainAccountStatusEnum, Institution

public interface MainAccount
    extends BusinessObject
{

    public abstract long getAccountId();

    public abstract String getAccountCode();

    public abstract PersonNameDetails getNameDetails();

    public abstract PersonNameDetails getAlt1NameDetails();

    public abstract PersonNameDetails getAlt2NameDetails();

    public abstract Branch getBranch();

    public abstract ContactInfoDetails getContactInfoDetails();

    public abstract String getTradingPassword();

    public abstract SubAccount[] getSubAccounts();

    public abstract SubAccount getSubAccount(SubAccountTypeEnum subaccounttypeenum)
        throws NotFoundException;

    public abstract Participant[] getParticipants();

    public abstract Preferences getPreferences();

    public abstract void serializeOperationsWithWait();

    public abstract void serializeOperationsWithNoWait()
        throws ResourceBusyException;

    public abstract MainAccountStatusEnum getAccountStatus();

    public abstract Institution getInstitution();
}

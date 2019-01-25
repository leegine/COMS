// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AccountManager.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            NotFoundException, MainAccount, Institution, Branch, 
//            SubAccount, SubAccountTypeEnum

public interface AccountManager
{

    public abstract MainAccount getMainAccount(long l)
        throws NotFoundException;

    public abstract MainAccount getMainAccount(Institution institution, Branch branch, String s)
        throws NotFoundException;

    public abstract MainAccount getMainAccount(long l, long l1, String s)
        throws NotFoundException;

    public abstract MainAccount getMainAccount(long l, String s, String s1)
        throws NotFoundException;

    public abstract SubAccount getSubAccount(long l, long l1)
        throws NotFoundException;

    public abstract SubAccount getSubAccount(long l, SubAccountTypeEnum subaccounttypeenum)
        throws NotFoundException;

    public abstract SubAccount[] getSubAccounts(long l);

    public abstract Institution[] getAllInstitutions();

    public abstract Institution getInstitution(long l)
        throws NotFoundException;

    public abstract Institution getInstitution(String s)
        throws NotFoundException;

    public abstract Branch getBranch(long l)
        throws NotFoundException;

    public abstract Branch getBranch(Institution institution, String s)
        throws NotFoundException;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Branch.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            BusinessObject, BranchTypeEnum, Institution, MainAccount

public interface Branch
    extends BusinessObject
{

    public abstract long getBranchId();

    public abstract String getBranchCode();

    public abstract String getBranchName();

    public abstract String getBranchNameAlt1();

    public abstract BranchTypeEnum getBranchType();

    public abstract Institution getInstitution();

    public abstract MainAccount[] getMainAccounts();
}

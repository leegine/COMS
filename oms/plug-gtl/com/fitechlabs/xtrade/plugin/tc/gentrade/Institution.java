// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Institution.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            BusinessObject, Branch

public interface Institution
    extends BusinessObject
{

    public abstract long getInstitutionId();

    public abstract String getInstitutionCode();

    public abstract String getInstitutionName();

    public abstract Branch[] getBranches();
}

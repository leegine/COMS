// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Trader.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            BusinessObject, TraderTypeEnum, PersonNameDetails, Branch, 
//            Institution

public interface Trader
    extends BusinessObject
{

    public abstract long getTraderId();

    public abstract String getTraderCode();

    public abstract long getLoginId();

    public abstract TraderTypeEnum getTraderType();

    public abstract PersonNameDetails getNameDetails();

    public abstract PersonNameDetails getAlt1NameDetails();

    public abstract PersonNameDetails getAlt2NameDetails();

    public abstract Branch getBranch();

    public abstract Institution getInstitution();
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Participant.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            BusinessObject, MainAccount, ParticipantTypeEnum, PersonNameDetails

public interface Participant
    extends BusinessObject
{

    public abstract long getParticipantId();

    public abstract String getParticipantCode();

    public abstract MainAccount getMainAccount();

    public abstract ParticipantTypeEnum getParticipantType();

    public abstract PersonNameDetails getNameDetails();

    public abstract PersonNameDetails getAlt1NameDetails();
}

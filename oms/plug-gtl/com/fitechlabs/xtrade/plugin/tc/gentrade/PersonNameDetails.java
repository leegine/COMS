// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PersonNameDetails.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


public class PersonNameDetails
{

    public PersonNameDetails(String familyName, String givenName, String middleName)
    {
        m_familyName = familyName;
        m_given_name = givenName;
        m_middleName = middleName;
    }

    public String getFamilyName()
    {
        return m_familyName;
    }

    public String getGivenName()
    {
        return m_given_name;
    }

    public String getMiddleName()
    {
        return m_middleName;
    }

    public String toString()
    {
        return "{family name, middle name, given name} = {" + getFamilyName() + ',' + getMiddleName() + "," + getGivenName() + "}";
    }

    private final String m_familyName;
    private final String m_given_name;
    private final String m_middleName;
}

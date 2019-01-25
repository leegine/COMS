// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ContactInfoDetails.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


public class ContactInfoDetails
{

    public ContactInfoDetails(String addressLine1, String addressLine2, String postalCode, String postalCodeSub, String telNo, String emailAddress, String emailAddressAlt1, 
            String faxNo)
    {
        m_addressLine1 = addressLine1;
        m_addressLine2 = addressLine2;
        m_emailAddress = emailAddress;
        m_emailAddressAlt1 = emailAddressAlt1;
        m_faxNo = faxNo;
        m_postalCode = postalCode;
        m_postalCodeSub = postalCodeSub;
        m_telNo = telNo;
    }

    public String getAddressLine1()
    {
        return m_addressLine1;
    }

    public String getAddressLine2()
    {
        return m_addressLine2;
    }

    public String getEmailAddress()
    {
        return m_emailAddress;
    }

    public String getEmailAddressAlt1()
    {
        return m_emailAddressAlt1;
    }

    public String getFaxNo()
    {
        return m_faxNo;
    }

    public String getPostalCode()
    {
        return m_postalCode;
    }

    public String getPostalCodeSub()
    {
        return m_postalCodeSub;
    }

    public String getTelephoneNo()
    {
        return m_telNo;
    }

    private final String m_addressLine1;
    private final String m_addressLine2;
    private final String m_postalCode;
    private final String m_postalCodeSub;
    private final String m_telNo;
    private final String m_emailAddress;
    private final String m_emailAddressAlt1;
    private final String m_faxNo;
}

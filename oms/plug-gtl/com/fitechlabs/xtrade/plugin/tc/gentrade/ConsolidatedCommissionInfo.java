// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConsolidatedCommissionInfo.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


public class ConsolidatedCommissionInfo
{

    public double getCommission(int index)
    {
        return m_commissions[index];
    }

    public double getTotalCommission()
    {
        return m_totalCommission;
    }

    public double getSalesTax(int index)
    {
        return m_salesTaxes[index];
    }

    public double getTotalSalesTax()
    {
        return m_totalSalesTax;
    }

    public ConsolidatedCommissionInfo(double commissions[], double totalCommission, double salesTaxes[], double totalSalesTax)
    {
        m_commissions = commissions;
        m_totalCommission = totalCommission;
        m_salesTaxes = salesTaxes;
        m_totalSalesTax = totalSalesTax;
    }

    private double m_commissions[];
    private double m_totalCommission;
    private double m_salesTaxes[];
    private double m_totalSalesTax;
}

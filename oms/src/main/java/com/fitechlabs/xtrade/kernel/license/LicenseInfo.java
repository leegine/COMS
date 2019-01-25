// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LicenseInfo.java

package com.fitechlabs.xtrade.kernel.license;

import java.sql.Date;
import java.util.Properties;

public interface LicenseInfo
{

    public abstract String getLicensee();

    public abstract String getComponent();

    public abstract Date getIssueDate();

    public abstract Date getExpirationDate();

    public abstract Properties getAttributes();
}

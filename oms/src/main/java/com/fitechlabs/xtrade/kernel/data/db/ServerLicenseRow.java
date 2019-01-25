// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServerLicenseRow.java

package com.fitechlabs.xtrade.kernel.data.db;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import java.sql.Timestamp;

public interface ServerLicenseRow
    extends Row
{

    public abstract String getLicenseId();

    public abstract boolean getLicenseIdIsSet();

    public abstract String getComponent();

    public abstract boolean getComponentIsSet();

    public abstract String getLicensee();

    public abstract boolean getLicenseeIsSet();

    public abstract Timestamp getExpireDate();

    public abstract boolean getExpireDateIsSet();

    public abstract String getLicenseXml();

    public abstract boolean getLicenseXmlIsSet();

    public static final RowType TYPE = new RowType("server_license", "config");

}

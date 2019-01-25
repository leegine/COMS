// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LicenseParams.java

package com.fitechlabs.xtrade.kernel.license;

import com.fitechlabs.xtrade.kernel.util.ISO8601Format;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Properties;

// Referenced classes of package com.fitechlabs.xtrade.kernel.license:
//            LicenseInfo

public class LicenseParams
    implements LicenseInfo
{

    public LicenseParams()
    {
    }

    public LicenseParams(String component, String licensee, Date issueDate, Date expirationDate, Properties attributes)
    {
        this.component = component;
        this.licensee = licensee;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
        this.attributes = attributes;
    }

    public LicenseParams(String component, String licensee, java.util.Date issueDate, java.util.Date expirationDate, Properties attributes)
    {
        this.component = component;
        this.licensee = licensee;
        this.attributes = attributes;
        this.issueDate = truncateDate(issueDate);
        this.expirationDate = truncateDate(expirationDate);
    }

    public Properties getAttributes()
    {
        return attributes;
    }

    public String getComponent()
    {
        return component;
    }

    public Date getExpirationDate()
    {
        return expirationDate;
    }

    public Date getIssueDate()
    {
        return issueDate;
    }

    public String getLicensee()
    {
        return licensee;
    }

    public void setAttributes(Properties attributes)
    {
        this.attributes = attributes;
    }

    public void setComponent(String component)
    {
        this.component = component;
    }

    public void setExpirationDate(java.util.Date expirationDate)
    {
        this.expirationDate = truncateDate(expirationDate);
    }

    public void setIssueDate(java.util.Date issueDate)
    {
        this.issueDate = truncateDate(issueDate);
    }

    private static Date truncateDate(java.util.Date date)
    {
        ISO8601Format format = new ISO8601Format(3, false);
        try
        {
            date = format.parse(format.format(date));
        }
        catch(ParseException e)
        {
            throw new InternalError("date trunction error: " + e);
        }
        return new Date(date.getTime());
    }

    public void setLicensee(String licensee)
    {
        this.licensee = licensee;
    }

    private String component;
    private String licensee;
    private Date issueDate;
    private Date expirationDate;
    private Properties attributes;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServerLicensePK.java

package com.fitechlabs.xtrade.kernel.data.db;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data.db:
//            ServerLicenseRow

public class ServerLicensePK
    implements PrimaryKey
{

    public RowType getRowType()
    {
        return ServerLicenseRow.TYPE;
    }

    public ServerLicensePK()
    {
    }

    public ServerLicensePK(String p_licenseId)
    {
        license_id = p_licenseId;
    }

    public static ServerLicensePK fromString(String pkValueString)
        throws NumberFormatException
    {
        return new ServerLicensePK(pkValueString);
    }

    public String toString()
    {
        return license_id;
    }

    public boolean equals(Object other)
    {
        if(other == null || !(other instanceof ServerLicensePK))
            return false;
        ServerLicensePK o = (ServerLicensePK)other;
        if(license_id == null)
        {
            if(o.license_id != null)
                return false;
        } else
        if(!license_id.equals(o.license_id))
            return false;
        return true;
    }

    public int hashCode()
    {
        return license_id == null ? 0 : license_id.hashCode();
    }

    public static final String TAGNAME = "pk";
    public static final String PTYPE = "server_license";
    public String license_id;
}

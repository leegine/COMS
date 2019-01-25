// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GtlComponentLicenseCheckerUtil.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.license.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import java.io.IOException;
import java.util.Collection;

public final class GtlComponentLicenseCheckerUtil
{

    public GtlComponentLicenseCheckerUtil(String componentName, String publicKey)
    {
        m_componentName = componentName;
        m_publicKey = publicKey;
        m_log.info("::::::: Component Name : " + componentName + " License Available = " + isValidLicenseAvailable());
    }

    private Key getPublicKey()
        throws IOException
    {
        LicenseService ls = Licensing.getLicenseServiceInstance();
        return ls.keyFromHexString(m_publicKey);
    }

    public boolean isValidLicenseAvailable()
    {
        Collection c;
        LicenseService ls = Licensing.getLicenseServiceInstance();
        c = ls.getValidLicenses(m_componentName, getPublicKey());
        return c.size() > 0;
        IOException ioe;
        ioe;
        m_log.error(ioe.getMessage(), ioe);
        throw new RuntimeSystemException(ioe.getMessage(), ioe);
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static final Logit m_log;
    private static final boolean DBG;
    private final String m_componentName;
    private final String m_publicKey;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlComponentLicenseCheckerUtil.class);
        DBG = m_log.ison();
    }
}

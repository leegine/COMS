// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Licensing.java

package com.fitechlabs.xtrade.kernel.license;

import com.fitechlabs.xtrade.kernel.license.impl.LicenseServiceImpl;

// Referenced classes of package com.fitechlabs.xtrade.kernel.license:
//            LicenseService

public final class Licensing
{

    public static LicenseService getLicenseServiceInstance()
    {
        if(impl == null)
            synchronized(com.fitechlabs.xtrade.kernel.license.Licensing.class)
            {
                if(impl == null)
                    impl = new LicenseServiceImpl();
            }
        return impl;
    }

    private Licensing()
    {
    }

    private static LicenseService impl;
}

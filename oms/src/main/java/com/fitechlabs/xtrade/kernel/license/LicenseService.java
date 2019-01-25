// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LicenseService.java

package com.fitechlabs.xtrade.kernel.license;

import java.io.IOException;
import java.util.Collection;

// Referenced classes of package com.fitechlabs.xtrade.kernel.license:
//            KeyPair, LicenseInfo, Key, License

public interface LicenseService
{

    public abstract KeyPair genKeyPair();

    public abstract License createLicense(LicenseInfo licenseinfo, Key key);

    public abstract void installLicense(License license)
        throws IllegalArgumentException;

    public abstract void installLicense(String s)
        throws Exception;

    public abstract Collection getInstalledLicenses();

    public abstract Collection getValidLicenses(String s, Key key);

    public abstract Collection getValidAttributeValues(String s, Key key, String s1);

    public abstract Key keyFromFile(String s)
        throws IOException;

    public abstract Key keyFromHexString(String s)
        throws IOException;

    public abstract Key keyFromBytes(byte abyte0[])
        throws IOException;

    public abstract License licenseFromXmlString(String s)
        throws Exception;
}

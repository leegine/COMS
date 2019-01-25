// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   License.java

package com.fitechlabs.xtrade.kernel.license;


// Referenced classes of package com.fitechlabs.xtrade.kernel.license:
//            LicenseInfo, Key

public interface License
    extends LicenseInfo
{

    public abstract String toXmlString();

    public abstract boolean isValid(Key key);
}

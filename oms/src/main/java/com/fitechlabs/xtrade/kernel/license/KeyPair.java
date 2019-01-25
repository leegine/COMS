// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyPair.java

package com.fitechlabs.xtrade.kernel.license;


// Referenced classes of package com.fitechlabs.xtrade.kernel.license:
//            Key

public interface KeyPair
{

    public abstract Key getPublicKey();

    public abstract Key getPrivateKey();
}

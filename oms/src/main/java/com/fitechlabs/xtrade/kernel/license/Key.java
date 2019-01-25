// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Key.java

package com.fitechlabs.xtrade.kernel.license;

import java.io.IOException;

public interface Key
{

    public abstract Object getObject();

    public abstract String toHexString()
        throws IOException;

    public abstract byte[] toBytes()
        throws IOException;

    public abstract void toFile(String s)
        throws IOException;
}

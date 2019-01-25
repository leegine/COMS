// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ArgumentSpec.java

package com.fitechlabs.dbind.gen;


// Referenced classes of package com.fitechlabs.dbind.gen:
//            ColumnSpec

public class ArgumentSpec extends ColumnSpec
{

    public ArgumentSpec(String name, int sqlType, int size, int digits, boolean isIn, boolean isOut)
    {
        super(name, sqlType, size, digits, false);
        this.isIn = isIn;
        this.isOut = isOut;
    }

    public boolean getIsIn()
    {
        return isIn;
    }

    public boolean getIsOut()
    {
        return isOut;
    }

    boolean isIn;
    boolean isOut;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EnumeratedBoolean.java

package com.fitechlabs.xtrade.kernel.enum;


// Referenced classes of package com.fitechlabs.xtrade.kernel.enum:
//            Enumerated

public final class EnumeratedBoolean extends Enumerated
{
    public static class StringValues
    {

        public static final String FALSE = "FALSE";
        public static final String TRUE = "TRUE";

        public StringValues()
        {
        }
    }

    public static class IntValues
    {

        public static final int FALSE = 0;
        public static final int TRUE = 1;

        public IntValues()
        {
        }
    }


    private EnumeratedBoolean(int i, String s)
    {
        super(i, s);
    }

    public static final EnumeratedBoolean TRUE = new EnumeratedBoolean(1, "TRUE");
    public static final EnumeratedBoolean FALSE = new EnumeratedBoolean(0, "FALSE");

}

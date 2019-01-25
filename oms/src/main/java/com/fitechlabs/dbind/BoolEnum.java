// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BoolEnum.java

package com.fitechlabs.dbind;

import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.fitechlabs.dbind:
//            Enum

/**
 * @deprecated Class BoolEnum is deprecated
 */

public class BoolEnum extends Enum
{

    /**
     * @deprecated Method BoolEnum is deprecated
     */

    private BoolEnum(int intValue, String stringValue)
    {
        super(intValue, stringValue);
    }

    /**
     * @deprecated Method valueOf is deprecated
     */

    public static Enum valueOf(int intValue)
    {
        switch(intValue)
        {
        case 0: // '\0'
            return FALSE;

        case 1: // '\001'
            return TRUE;
        }
        return null;
    }

    /**
     * @deprecated Method valueOf is deprecated
     */

    public static Enum valueOf(String stringValue)
    {
        return (BoolEnum)s2e.get(stringValue);
    }

    public static final BoolEnum FALSE;
    public static final BoolEnum TRUE;
    private static Map s2e;

    static 
    {
        FALSE = new BoolEnum(0, "FALSE");
        TRUE = new BoolEnum(1, "TRUE");
        s2e = new HashMap();
        s2e.put(((Enum) (FALSE)).s, FALSE);
        s2e.put(((Enum) (TRUE)).s, TRUE);
    }
}

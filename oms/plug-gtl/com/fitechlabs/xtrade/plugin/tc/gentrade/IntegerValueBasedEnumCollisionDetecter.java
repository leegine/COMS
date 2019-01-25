// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IntegerValueBasedEnumCollisionDetecter.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            IntegerValueBasedEnum

public class IntegerValueBasedEnumCollisionDetecter
{
    private static class TestEnum
        implements IntegerValueBasedEnum
    {

        public int getIntegerValue()
        {
            return m_enum;
        }

        public static final TestEnum ONE = new TestEnum(1);
        public static final TestEnum TWO = new TestEnum(2);
        public static final TestEnum TWO_dup = new TestEnum(2);
        public static final String ss = "xxx";
        public static final int m_i = 0;
        private final int m_enum;


        public TestEnum(int val)
        {
            m_enum = val;
        }
    }


    private IntegerValueBasedEnumCollisionDetecter()
    {
    }

    public static void checkForCollisions(Class c)
    {
        Map valuesMap = new HashMap();
        Field fields[] = c.getDeclaredFields();
        for(int i = 0; i < fields.length; i++)
        {
            Field f = fields[i];
            if(!(com.fitechlabs.xtrade.plugin.tc.gentrade.IntegerValueBasedEnum.class).isAssignableFrom(f.getType()))
                continue;
            int modifiers = f.getModifiers();
            if(Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers) && Modifier.isFinal(modifiers))
                try
                {
                    IntegerValueBasedEnum ivbe = (IntegerValueBasedEnum)f.get(com.fitechlabs.xtrade.plugin.tc.gentrade.IntegerValueBasedEnum.class);
                    Integer currentValue = new Integer(ivbe.getIntegerValue());
                    if(valuesMap.containsKey(currentValue))
                        throw new RuntimeException("Duplicate enum value definition detected for Field : " + f.getName() + ", in class : " + c.getName());
                    valuesMap.put(currentValue, null);
                }
                catch(IllegalAccessException iae)
                {
                    throw new RuntimeException("IllegalAccessException, when trying to detect overlapping enum definitions in class:" + c.getName());
                }
            else
                throw new RuntimeException("Field : " + f.getName() + ", in class : " + c.getName() + " is not declared as public static final");
        }

    }

    public static void main(String args[])
    {
        checkForCollisions(com.fitechlabs.xtrade.plugin.tc.gentrade.IntegerValueBasedEnumCollisionDetecter$TestEnum.class);
    }
}

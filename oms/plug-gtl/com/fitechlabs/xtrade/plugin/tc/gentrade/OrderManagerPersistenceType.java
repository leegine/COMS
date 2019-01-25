// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OrderManagerPersistenceType.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            IntegerValueBasedEnum, IntegerValueBasedEnumCollisionDetecter

public class OrderManagerPersistenceType
    implements IntegerValueBasedEnum
{

    private OrderManagerPersistenceType(int value)
    {
        m_enumValue = value;
    }

    public int hashCode()
    {
        return getIntegerValue();
    }

    public int getIntegerValue()
    {
        return m_enumValue;
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof OrderManagerPersistenceType)
        {
            OrderManagerPersistenceType other = (OrderManagerPersistenceType)obj;
            return other.m_enumValue == m_enumValue;
        } else
        {
            return false;
        }
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    public static final OrderManagerPersistenceType INSERT = new OrderManagerPersistenceType(0);
    public static final OrderManagerPersistenceType UPDATE = new OrderManagerPersistenceType(1);
    private final int m_enumValue;

    static 
    {
        IntegerValueBasedEnumCollisionDetecter.checkForCollisions(com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType.class);
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OrderManagerPersistenceContext.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;


// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            IntegerValueBasedEnum, IntegerValueBasedEnumCollisionDetecter

public class OrderManagerPersistenceContext
    implements IntegerValueBasedEnum
{

    private OrderManagerPersistenceContext(int value)
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
        if(obj instanceof OrderManagerPersistenceContext)
        {
            OrderManagerPersistenceContext other = (OrderManagerPersistenceContext)obj;
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

    public static final OrderManagerPersistenceContext NEW_CASH_BASED_ORDER = new OrderManagerPersistenceContext(0);
    public static final OrderManagerPersistenceContext NEW_OPEN_CONTRACT_ORDER = new OrderManagerPersistenceContext(1);
    public static final OrderManagerPersistenceContext NEW_SETTLE_CONTRACT_ORDER = new OrderManagerPersistenceContext(2);
    public static final OrderManagerPersistenceContext NEW_SWAP_CONTRACT_ORDER = new OrderManagerPersistenceContext(3);
    public static final OrderManagerPersistenceContext FILL_ORDER = new OrderManagerPersistenceContext(4);
    public static final OrderManagerPersistenceContext ORDER_CONFIRMED_BY_MKT = new OrderManagerPersistenceContext(5);
    public static final OrderManagerPersistenceContext ORDER_REJECTED_BY_MKT = new OrderManagerPersistenceContext(6);
    public static final OrderManagerPersistenceContext CANCEL_ORDER_ACCEPTED = new OrderManagerPersistenceContext(7);
    public static final OrderManagerPersistenceContext CANCEL_ORDER_CONFIRMED_BY_MKT = new OrderManagerPersistenceContext(8);
    public static final OrderManagerPersistenceContext CANCEL_ORDER_REJECTED_BY_MKT = new OrderManagerPersistenceContext(9);
    public static final OrderManagerPersistenceContext MODIFY_ORDER_ACCEPTED = new OrderManagerPersistenceContext(10);
    public static final OrderManagerPersistenceContext MODIFY_ORDER_CONFIRMED_BY_MKT = new OrderManagerPersistenceContext(11);
    public static final OrderManagerPersistenceContext MODIFY_ORDER_REJECTED_BY_MKT = new OrderManagerPersistenceContext(12);
    public static final OrderManagerPersistenceContext ORDER_INVALIDATED_BY_MKT = new OrderManagerPersistenceContext(13);
    public static final OrderManagerPersistenceContext ORDER_EXPIRED = new OrderManagerPersistenceContext(14);
    public static final OrderManagerPersistenceContext UNDO_INVALIDATION_BY_MKT = new OrderManagerPersistenceContext(15);
    public static final OrderManagerPersistenceContext UNDO_EXECUTION = new OrderManagerPersistenceContext(16);
    public static final OrderManagerPersistenceContext NEW_ASSET_TRANSFER_ORDER = new OrderManagerPersistenceContext(17);
    public static final OrderManagerPersistenceContext ASSET_TRANSFER_DONE = new OrderManagerPersistenceContext(18);
    public static final OrderManagerPersistenceContext NEW_ORDER_SENT_TO_MARKET = new OrderManagerPersistenceContext(19);
    public static final OrderManagerPersistenceContext CANCEL_ORDER_SENT_TO_MARKET = new OrderManagerPersistenceContext(20);
    public static final OrderManagerPersistenceContext CHANGE_ORDER_SENT_TO_MARKET = new OrderManagerPersistenceContext(21);
    private final int m_enumValue;

    static 
    {
        IntegerValueBasedEnumCollisionDetecter.checkForCollisions(com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext.class);
    }
}

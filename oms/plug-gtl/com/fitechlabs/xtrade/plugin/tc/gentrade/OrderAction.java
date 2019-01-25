// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OrderAction.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import java.sql.Timestamp;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            BusinessObject, Product, SideEnum, OrderExpirationStatusEnum, 
//            OrderTypeEnum, OrderEventTypeEnum, OrderStatusEnum, TradedProduct

public interface OrderAction
    extends BusinessObject
{

    public abstract long getOrderActionId();

    public abstract long getOrderUnitId();

    public abstract long getOrderId();

    public abstract long getAccountId();

    public abstract long getSubAccountId();

    public abstract Product getProduct();

    public abstract Timestamp getOrderActionTimestamp();

    public abstract int getOrderActionSerialNo();

    public abstract boolean isExpired();

    public abstract boolean isFullyExecuted();

    public abstract boolean isPartiallyExecuted();

    public abstract boolean isUnexecuted();

    public abstract double getExecutionQuantity();

    public abstract double getExecutionPrice();

    public abstract double getQuantity();

    public abstract double getConfirmedQuantity();

    public abstract SideEnum getSide();

    public abstract OrderExpirationStatusEnum getExpirationStatus();

    public abstract OrderTypeEnum getOrderType();

    public abstract OrderEventTypeEnum getOrderEventType();

    public abstract OrderStatusEnum getOrderStatus();

    public abstract double getPrice();

    public abstract boolean isMarketOrder();

    public abstract double getConfirmedPrice();

    public abstract TradedProduct getTradedProduct();
}

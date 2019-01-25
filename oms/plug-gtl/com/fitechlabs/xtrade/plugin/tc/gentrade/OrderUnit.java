// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OrderUnit.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import java.sql.Timestamp;
import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            BusinessObject, OrderAction, Product, Order, 
//            OrderExecution, OrderOpenStatusEnum, OrderTypeEnum, OrderCategEnum, 
//            TaxTypeEnum, SideEnum, OrderStatusEnum, OrderExpirationStatusEnum, 
//            TradedProduct

public interface OrderUnit
    extends BusinessObject
{

    public abstract long getOrderUnitId();

    public abstract long getOrderId();

    public abstract long getAccountId();

    public abstract long getSubAccountId();

    public abstract long getBranchId();

    public abstract long getTraderId();

    public abstract OrderAction[] getOrderActions();

    public abstract Timestamp getReceivedTimestamp();

    public abstract Timestamp getExpirationTimestamp();

    public abstract Product getProduct();

    public abstract Order getOrder();

    public abstract OrderExecution[] getExecutions();

    public abstract OrderOpenStatusEnum getOrderOpenStatus();

    public abstract OrderTypeEnum getOrderType();

    public abstract OrderCategEnum getOrderCateg();

    public abstract TaxTypeEnum getTaxType();

    public abstract SideEnum getSide();

    public abstract OrderStatusEnum getOrderStatus();

    public abstract OrderExpirationStatusEnum getExpirationStatus();

    public abstract boolean isExpired();

    public abstract boolean isFullyExecuted();

    public abstract boolean isPartiallyExecuted();

    public abstract boolean isUnexecuted();

    public abstract double getConfirmedPrice();

    public abstract boolean isConfirmedPriceMarketOrder();

    public abstract double getConfirmedQuantity();

    public abstract double getQuantity();

    public abstract Date getDeliveryDate();

    public abstract double getExecutedAmount();

    public abstract double getExecutedQuantity();

    public abstract double getLimitPrice();

    public abstract boolean isMarketOrder();

    public abstract TradedProduct getTradedProduct();
}

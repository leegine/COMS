// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OrderExecution.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import java.sql.Timestamp;
import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            BusinessObject, Product, OrderTypeEnum, TradedProduct

public interface OrderExecution
    extends BusinessObject
{

    public abstract long getAccountId();

    public abstract long getBranchId();

    public abstract long getTraderId();

    public abstract Date getDeliveryDate();

    public abstract double getExecutionPrice();

    public abstract int getExecutionSerialNo();

    public abstract Timestamp getExecutionTimestamp();

    public abstract long getMarketId();

    public abstract long getOrderExecutionId();

    public abstract long getOrderUnitId();

    public abstract Product getProduct();

    public abstract double getExecutionQuantity();

    public abstract long getSubAccountId();

    public abstract OrderTypeEnum getOrderType();

    public abstract TradedProduct getTradedProduct();
}

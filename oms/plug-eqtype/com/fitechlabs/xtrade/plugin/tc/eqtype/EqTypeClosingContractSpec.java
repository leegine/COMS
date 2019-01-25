// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeClosingContractSpec.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

public interface EqTypeClosingContractSpec
    extends BusinessObject
{

    public abstract long getAccountId();

    public abstract long getClosingContractSpecId();

    public abstract long getContractId();

    public abstract int getClosingSerialNo();

    public abstract double getExecutedQuantity();

    public abstract double getConfirmedQuantity();

    public abstract boolean isFullyExecuted();

    public abstract boolean isPartiallyExecuted();

    public abstract boolean isUnexecuted();

    public abstract long getOrderId();

    public abstract long getOrderUnitId();

    public abstract double getQuantity();

    public abstract long getSubAccountId();
}

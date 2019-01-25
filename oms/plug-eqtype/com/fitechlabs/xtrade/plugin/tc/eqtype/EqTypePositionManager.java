// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypePositionManager.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;

import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;

public interface EqTypePositionManager
    extends PositionManager
{

    public abstract void processAssetOrderExecution(OrderExecution orderexecution)
        throws RuntimeSystemException;

    public abstract void processContractOrderExecution(OrderExecution orderexecution)
        throws RuntimeSystemException;

    public abstract void processContractOrderExecution(OrderExecution orderexecution, SettleContractEntry asettlecontractentry[])
        throws RuntimeSystemException;

    public abstract void updateLockedQuantity(long l, long l1, long l2, long l3, double d)
        throws RuntimeSystemException;

    public abstract void updateLockedQuantity(long l, Contract contract, double d)
        throws RuntimeSystemException;

    public abstract void undoExecution(long l)
        throws RuntimeSystemException;
}

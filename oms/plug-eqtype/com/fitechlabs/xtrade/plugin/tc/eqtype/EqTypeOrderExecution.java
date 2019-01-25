// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeOrderExecution.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

public interface EqTypeOrderExecution
    extends OrderExecution
{

    /**
     * @deprecated Method getProductId is deprecated
     */

    public abstract long getProductId();

    /**
     * @deprecated Method getProductType is deprecated
     */

    public abstract ProductTypeEnum getProductType();
}

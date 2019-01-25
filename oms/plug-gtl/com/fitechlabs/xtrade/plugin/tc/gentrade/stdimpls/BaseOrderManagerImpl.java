// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseOrderManagerImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

public abstract class BaseOrderManagerImpl
    implements OrderManager
{

    protected BaseOrderManagerImpl(OrderValidator defaultOrderValidator)
    {
        m_orderValidator = null;
        setOrderValidator(defaultOrderValidator);
    }

    public OrderValidator getOrderValidator()
    {
        return m_orderValidator;
    }

    public void overrideOrderValidator(OrderValidator newValidator)
    {
        m_log.info("Overriding OrderValidator of OrderManager in tradingModule:" + getThisTradingModule().getTradingModuleName() + " with " + newValidator.getClass());
        setOrderValidator(newValidator);
    }

    private void setOrderValidator(OrderValidator orderValidator)
    {
        synchronized(com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidator.class)
        {
            m_orderValidator = orderValidator;
        }
    }

    protected abstract TradingModule getThisTradingModule();

    private static final Logit m_log;
    private static final boolean DBG;
    protected OrderValidator m_orderValidator;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.BaseOrderManagerImpl.class);
        DBG = m_log.ison();
    }
}

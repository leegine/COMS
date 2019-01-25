// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GenericMarketResponseReceiverCallbackServiceInterceptor.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.config.ServerConfig;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketResponseMessage;
import java.lang.reflect.Method;

public class GenericMarketResponseReceiverCallbackServiceInterceptor
    implements Interceptor
{

    public GenericMarketResponseReceiverCallbackServiceInterceptor(String tradingModuleName)
    {
        m_tradingModuleName = tradingModuleName;
        if(isSerializationOn())
            m_log.info("SubAccount/MainAccount serialization in MarketResponseReceiverCallbackService of TradingModule:" + getTradingModuleName() + " is turned on");
        else
            m_log.info("SubAccount/MainAccount serialization in MarketResponseReceiverCallbackService of TradingModule:" + getTradingModuleName() + " is turned off");
    }

    public Object onCall(Method method, Object arguments[])
    {
        Object txiContext = txi.onCall(method, arguments);
        if(isSerializationOn())
            try
            {
                if(arguments != null && arguments.length > 0 && (arguments[0] instanceof MarketResponseMessage))
                {
                    long orderId = ((MarketResponseMessage)arguments[0]).getOrderId();
                    try
                    {
                        OrderManager om = getTradingModule().getOrderManager();
                        Order order = om.getOrder(orderId);
                        SubAccount subAccount = order.getSubAccount();
                        if(DBG)
                            m_log.debug("Serializing operations of account : " + subAccount.getSubAccountId());
                        subAccount.serializeOperationsWithWait();
                        if(DBG)
                            m_log.debug("Serializing done.");
                    }
                    catch(NotFoundException nfe)
                    {
                        String msg = "order not found with id: " + orderId + ", in tradingModule:" + getTradingModuleName();
                        m_log.error(msg);
                        throw new RuntimeSystemException(msg, nfe);
                    }
                } else
                {
                    throw new IllegalStateException(method.getName() + "arg list does not contain or is not of MarketResponseMessage type");
                }
            }
            catch(Throwable t)
            {
                try
                {
                    txi.onThrowable(txiContext, t);
                }
                catch(Throwable tt)
                {
                    m_log.error(tt.getMessage(), tt);
                }
                m_log.error(t.getMessage(), t);
                throw new RuntimeSystemException(t.getMessage(), t);
            }
        else
        if(DBG)
            m_log.debug("serialization is turned off in MarketResponseReceiverCallbackService in trading module:" + getTradingModuleName());
        return txiContext;
    }

    public void onReturn(Object context, Object returnValue)
    {
        txi.onReturn(context, returnValue);
    }

    public void onThrowable(Object context, Throwable thrownObject)
    {
        txi.onThrowable(context, thrownObject);
    }

    private TradingModule getTradingModule()
    {
        return GtlUtils.getTradingModule(getTradingModuleName());
    }

    protected boolean isSerializationOn()
    {
        String category;
        category = "xblocks." + getTradingModuleName();
        String configName = "MarketResponseReceiverCallbackService.serializeOnAccount";
        String val = ServerConfig.getConfigValue(category, "MarketResponseReceiverCallbackService.serializeOnAccount");
        return val == null || "true".equals(val);
        DataNetworkException dne;
        dne;
        String msg = "Exception while fetching server_config with categ=" + category + ", config name:" + "MarketResponseReceiverCallbackService.serializeOnAccount";
        m_log.error(msg, dne);
        throw new RuntimeSystemException(msg, dne);
    }

    private String getTradingModuleName()
    {
        return m_tradingModuleName;
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static final Logit m_log;
    private static final boolean DBG;
    private final TransactionalInterceptor txi = new TransactionalInterceptor(1);
    private final String m_tradingModuleName;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GenericMarketResponseReceiverCallbackServiceInterceptor.class);
        DBG = m_log.ison();
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeMarketAdapterImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptable;
import com.fitechlabs.xtrade.kernel.interceptor.InterceptableBuilder;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.BaseMarketAdapterImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GenericMarketResponseReceiverCallbackServiceInterceptor;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            EqTypeMarketResponseReceiverCallbackServiceImpl, EqTypeMarketResponseReceiverCallbackServiceCallLoggerInterceptor

class EqTypeMarketAdapterImpl extends BaseMarketAdapterImpl
{

    public EqTypeMarketAdapterImpl()
    {
        super(getInterceptorWrappedResponseCallbackService());
    }

    private static EqTypeMarketResponseReceiverCallbackService getInterceptorWrappedResponseCallbackService()
    {
        EqTypeMarketResponseReceiverCallbackService impl = new EqTypeMarketResponseReceiverCallbackServiceImpl();
        Interceptable in = InterceptableBuilder.buildInterceptable(com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeMarketResponseReceiverCallbackService.class, impl);
        in.addInterceptor(new GenericMarketResponseReceiverCallbackServiceInterceptor("eqtype"));
        in.addInterceptor(new EqTypeMarketResponseReceiverCallbackServiceCallLoggerInterceptor());
        m_log.info("Wrapped EqTypeMarketResponseReceiverCallbackService with interceptor : " + (com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GenericMarketResponseReceiverCallbackServiceInterceptor.class).getName());
        return (EqTypeMarketResponseReceiverCallbackService)in;
    }

    private static final Logit m_log;
    private static final boolean DBG;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeMarketAdapterImpl.class);
        DBG = m_log.ison();
    }
}

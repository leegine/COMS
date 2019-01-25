// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseEqTypeMarketResponseReceiverCallbackServiceImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.*;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            EqTypeOrderSubmitterPersistenceManager

public abstract class BaseEqTypeMarketResponseReceiverCallbackServiceImpl
    implements EqTypeMarketResponseReceiverCallbackService
{

    protected BaseEqTypeMarketResponseReceiverCallbackServiceImpl()
    {
    }

    protected abstract EqTypeOrderSubmitterPersistenceManager getThisOrderSubmitterPersistenceManager();

    protected abstract TradingModule getThisTradingModule();

    public abstract ProcessingResult process(OrderFillMarketResponseMessage orderfillmarketresponsemessage);

    public abstract ProcessingResult process(MarketResponseMessage marketresponsemessage);

    public ProcessingResult process(NewOrderSentMarketResponseMessage msg)
    {
        long orderId = msg.getOrderId();
        if(DBG)
            m_log.debug("processing response from market adapter. SENT_TO_MARKET. order Id : " + orderId);
        getThisOrderSubmitterPersistenceManager().handleOrderSentToMarketUpdates(orderId);
        if(DBG)
            m_log.debug("processed SENT_TO_MARKET.");
        return ProcessingResult.SUCCESS_RESULT;
    }

    public ProcessingResult process(NewOrderAcceptedMarketResponseMessage msg)
    {
        long orderId = msg.getOrderId();
        if(DBG)
            m_log.debug("processing response from market adapter. ORDER_ACCEPTED. order Id : " + orderId);
        getThisOrderSubmitterPersistenceManager().handleOrderAcceptedByMarketUpdates(orderId);
        return ProcessingResult.SUCCESS_RESULT;
    }

    public ProcessingResult process(NewOrderRejectedMarketResponseMessage msg)
    {
        long orderId = msg.getOrderId();
        if(DBG)
            m_log.debug("processing response from market adapter. ORDER_REJECTED. order Id : " + orderId);
        getThisOrderSubmitterPersistenceManager().handleOrderRejectedByMarketUpdates(orderId);
        return ProcessingResult.SUCCESS_RESULT;
    }

    public ProcessingResult process(ChangeOrderSentMarketResponseMessage msg)
    {
        long orderId = msg.getOrderId();
        if(DBG)
            m_log.debug("processing response from market adapter. CHANGE_ORDER_SENT. order Id : " + orderId);
        getThisOrderSubmitterPersistenceManager().handleChangingOrderUpdates(orderId);
        return ProcessingResult.SUCCESS_RESULT;
    }

    public ProcessingResult process(CancelOrderSentMarketResponseMessage msg)
    {
        long orderId = msg.getOrderId();
        if(DBG)
            m_log.debug("processing response from market adapter. CANCEL_ORDER_SENT. order Id : " + orderId);
        getThisOrderSubmitterPersistenceManager().handleCancellingOrderUpdates(orderId);
        return ProcessingResult.SUCCESS_RESULT;
    }

    public ProcessingResult process(CancelOrderAcceptedMarketResponseMessage msg)
    {
        long orderId = msg.getOrderId();
        if(DBG)
            m_log.debug("processing response from market adapter. CANCEL_ACCEPTED. order Id : " + orderId);
        getThisOrderSubmitterPersistenceManager().handleCancelledOrderUpdates(orderId);
        return ProcessingResult.SUCCESS_RESULT;
    }

    public ProcessingResult process(CancelOrderRejectedMarketResponseMessage msg)
    {
        long orderId = msg.getOrderId();
        if(DBG)
            m_log.debug("canel REJECTED. order Id " + msg.getOrderId());
        getThisOrderSubmitterPersistenceManager().handleCancelRejectedOrderUpdates(orderId);
        return ProcessingResult.SUCCESS_RESULT;
    }

    public ProcessingResult process(ChangeOrderAcceptedMarketResponseMessage msg)
    {
        long orderId = msg.getOrderId();
        if(DBG)
            m_log.debug("processing response from market adapter. CHANGE_ACCEPTED. order Id : " + orderId);
        getThisOrderSubmitterPersistenceManager().handleChangedOrderUpdates(orderId);
        return ProcessingResult.SUCCESS_RESULT;
    }

    public ProcessingResult process(ChangeOrderRejectedMarketResponseMessage msg)
    {
        long orderId = msg.getOrderId();
        if(DBG)
            m_log.debug("CHANGE REJECTED. order Id " + msg.getOrderId());
        getThisOrderSubmitterPersistenceManager().handleChangeRejectedOrderUpdates(orderId);
        return ProcessingResult.SUCCESS_RESULT;
    }

    public ProcessingResult process(OrderInvalidatedMarketResponseMessage msg)
    {
        long orderId = msg.getOrderId();
        if(DBG)
            m_log.debug("processing response from market adapter. ORDER_INVALIDATED_BY_MARKET. order Id : " + orderId);
        getThisOrderSubmitterPersistenceManager().handleOrderInvalidatedUpdates(orderId);
        return ProcessingResult.SUCCESS_RESULT;
    }

    public ProcessingResult process(UndoOrderInvalidatedMarketResponseMessage msg)
    {
        long orderId = msg.getOrderId();
        if(DBG)
            m_log.debug("processing response from market adapter. UNDO ORDER_INVALIDATED_BY_MARKET. order Id : " + orderId);
        getThisOrderSubmitterPersistenceManager().handleUndoOrderInvalidationUpdates(orderId);
        return ProcessingResult.SUCCESS_RESULT;
    }

    public ProcessingResult process(UndoOrderFillMarketResponseMessage msg)
    {
        throw new UnsupportedOperationException("EqTypeUndoOrderFillMarketResponseMessage processing is not supported.");
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

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.BaseEqTypeMarketResponseReceiverCallbackServiceImpl.class);
        DBG = m_log.ison();
    }
}

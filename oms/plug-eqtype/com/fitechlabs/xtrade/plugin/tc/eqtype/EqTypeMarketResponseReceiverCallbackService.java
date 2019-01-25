// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeMarketResponseReceiverCallbackService.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;

import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.*;

public interface EqTypeMarketResponseReceiverCallbackService
    extends MarketResponseReceiverCallbackService
{

    public abstract ProcessingResult process(NewOrderSentMarketResponseMessage newordersentmarketresponsemessage);

    public abstract ProcessingResult process(NewOrderAcceptedMarketResponseMessage neworderacceptedmarketresponsemessage);

    public abstract ProcessingResult process(NewOrderRejectedMarketResponseMessage neworderrejectedmarketresponsemessage);

    public abstract ProcessingResult process(CancelOrderSentMarketResponseMessage cancelordersentmarketresponsemessage);

    public abstract ProcessingResult process(CancelOrderAcceptedMarketResponseMessage cancelorderacceptedmarketresponsemessage);

    public abstract ProcessingResult process(CancelOrderRejectedMarketResponseMessage cancelorderrejectedmarketresponsemessage);

    public abstract ProcessingResult process(ChangeOrderSentMarketResponseMessage changeordersentmarketresponsemessage);

    public abstract ProcessingResult process(ChangeOrderAcceptedMarketResponseMessage changeorderacceptedmarketresponsemessage);

    public abstract ProcessingResult process(ChangeOrderRejectedMarketResponseMessage changeorderrejectedmarketresponsemessage);

    public abstract ProcessingResult process(OrderFillMarketResponseMessage orderfillmarketresponsemessage);

    public abstract ProcessingResult process(OrderInvalidatedMarketResponseMessage orderinvalidatedmarketresponsemessage);

    public abstract ProcessingResult process(UndoOrderInvalidatedMarketResponseMessage undoorderinvalidatedmarketresponsemessage);

    public abstract ProcessingResult process(UndoOrderFillMarketResponseMessage undoorderfillmarketresponsemessage);
}

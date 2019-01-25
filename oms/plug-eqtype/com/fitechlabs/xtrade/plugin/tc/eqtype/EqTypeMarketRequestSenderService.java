// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeMarketRequestSenderService.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;

import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeChangeOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeChangeSettleContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeChangeSwapContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeNewCashBasedOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeOpenContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeSettleContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeSwapContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.CancelOrderMarketRequestMessage;

public interface EqTypeMarketRequestSenderService
    extends MarketRequestSenderService
{

    public abstract MarketRequestSendResult send(CancelOrderMarketRequestMessage cancelordermarketrequestmessage, boolean flag)
        throws TooLateException;

    public abstract MarketRequestSendResult send(EqTypeChangeOrderMarketRequestMessage eqtypechangeordermarketrequestmessage, boolean flag)
        throws TooLateException;

    public abstract MarketRequestSendResult send(EqTypeChangeSettleContractOrderMarketRequestMessage eqtypechangesettlecontractordermarketrequestmessage, boolean flag)
        throws TooLateException;

    public abstract MarketRequestSendResult send(EqTypeChangeSwapContractOrderMarketRequestMessage eqtypechangeswapcontractordermarketrequestmessage, boolean flag)
        throws TooLateException;

    public abstract MarketRequestSendResult send(EqTypeNewCashBasedOrderMarketRequestMessage eqtypenewcashbasedordermarketrequestmessage);

    public abstract MarketRequestSendResult send(EqTypeOpenContractOrderMarketRequestMessage eqtypeopencontractordermarketrequestmessage);

    public abstract MarketRequestSendResult send(EqTypeSettleContractOrderMarketRequestMessage eqtypesettlecontractordermarketrequestmessage);

    public abstract MarketRequestSendResult send(EqTypeSwapContractOrderMarketRequestMessage eqtypeswapcontractordermarketrequestmessage);
}

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.28.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	AioMarketResponseReceiverCallbackServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.NewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbaio.market.messages.AioTransferDoneMarketResponseMessage;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class AioMarketResponseReceiverCallbackServiceImplForMock extends
    AioMarketResponseReceiverCallbackServiceImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        AioMarketResponseReceiverCallbackServiceImplForMock.class);

    public ProcessingResult process(NewOrderAcceptedMarketResponseMessage msg)
    {
        final String STR_METHOD_NAME = "process(NewOrderAcceptedMarketResponseMessage) -- ForMock";
        log.entering(STR_METHOD_NAME);
        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioMarketResponseReceiverCallbackServiceImpl",
            "process",
            new Class[] {NewOrderAcceptedMarketResponseMessage.class},
            new Object[]{msg});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioMarketResponseReceiverCallbackServiceImpl",
            "process",
            new Class[] {NewOrderAcceptedMarketResponseMessage.class}))
        {
            //3)MockFor --〉 asVoid
            log.exiting(STR_METHOD_NAME);
            return (ProcessingResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioMarketResponseReceiverCallbackServiceImpl",
                "process",
                new Class[] {NewOrderAcceptedMarketResponseMessage.class}).asObject();            
        }

        log.exiting(STR_METHOD_NAME);
        return super.process(msg);
    }

    public ProcessingResult process(AioTransferDoneMarketResponseMessage msg)
    {
        final String STR_METHOD_NAME = "process(NewOrderAcceptedMarketResponseMessage) -- ForMock";
        log.entering(STR_METHOD_NAME);
        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioMarketResponseReceiverCallbackServiceImpl",
            "process",
            new Class[] {AioTransferDoneMarketResponseMessage.class},
            new Object[]{msg});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioMarketResponseReceiverCallbackServiceImpl",
            "process",
            new Class[] {AioTransferDoneMarketResponseMessage.class}))
        {
            //3)MockFor --〉 asVoid
            log.exiting(STR_METHOD_NAME);
            return (ProcessingResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioMarketResponseReceiverCallbackServiceImpl",
                "process",
                new Class[] {AioTransferDoneMarketResponseMessage.class}).asObject();            
        }

        log.exiting(STR_METHOD_NAME);
        return super.process(msg);
    }
}
@

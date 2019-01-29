head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.27.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	IfoMarketResponseReceiverCallbackServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(IfoMarketResponseReceiverCallbackServiceImplForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/03 徐宏偉 (中訊) 新規作成
*/
package com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.NewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.OrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.UndoOrderFillMarketResponseMessage;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class IfoMarketResponseReceiverCallbackServiceImplForMock
    extends IfoMarketResponseReceiverCallbackServiceImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        IfoMarketResponseReceiverCallbackServiceImplForMock.class);

    public ProcessingResult process(NewOrderAcceptedMarketResponseMessage msg)
    {
        final String STR_METHOD_NAME = "process(NewOrderAcceptedMarketResponseMessage) -- ForMock";
        log.entering(STR_METHOD_NAME);
        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoMarketResponseReceiverCallbackServiceImpl",
            "process",
            new Class[] {NewOrderAcceptedMarketResponseMessage.class},
            new Object[]{msg});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoMarketResponseReceiverCallbackServiceImpl",
            "process",
            new Class[] {NewOrderAcceptedMarketResponseMessage.class}))
        {
            //3)MockFor --〉 asVoid
            log.exiting(STR_METHOD_NAME);
            return (ProcessingResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoMarketResponseReceiverCallbackServiceImpl",
                "process",
                new Class[] {NewOrderAcceptedMarketResponseMessage.class}).asObject();            
        }

        log.exiting(STR_METHOD_NAME);
        return super.process(msg);
    }

    public ProcessingResult process(OrderFillMarketResponseMessage msg)
    {
        final String STR_METHOD_NAME = "process(MarketResponseMessage) -- ForMock";
        log.entering(STR_METHOD_NAME);
        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoMarketResponseReceiverCallbackServiceImpl",
            "process",
            new Class[] {OrderFillMarketResponseMessage.class},
            new Object[]{msg});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoMarketResponseReceiverCallbackServiceImpl",
            "process",
            new Class[] {OrderFillMarketResponseMessage.class}))
        {
            //3)MockFor --〉 asVoid
            log.exiting(STR_METHOD_NAME);
            return (ProcessingResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoMarketResponseReceiverCallbackServiceImpl",
                "process",
                new Class[] {OrderFillMarketResponseMessage.class}).asObject();            
        }

        log.exiting(STR_METHOD_NAME);
        return super.process(msg);
    }

    public ProcessingResult process(UndoOrderFillMarketResponseMessage msg)
    {
        final String STR_METHOD_NAME = "process(MarketResponseMessage) -- ForMock";
        log.entering(STR_METHOD_NAME);
        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoMarketResponseReceiverCallbackServiceImpl",
            "process",
            new Class[] {UndoOrderFillMarketResponseMessage.class},
            new Object[]{msg});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoMarketResponseReceiverCallbackServiceImpl",
            "process",
            new Class[] {UndoOrderFillMarketResponseMessage.class}))
        {
            //3)MockFor --〉 asVoid
            log.exiting(STR_METHOD_NAME);
            return (ProcessingResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoMarketResponseReceiverCallbackServiceImpl",
                "process",
                new Class[] {UndoOrderFillMarketResponseMessage.class}).asObject();            
        }

        log.exiting(STR_METHOD_NAME);
        return super.process(msg);
    }
}
@

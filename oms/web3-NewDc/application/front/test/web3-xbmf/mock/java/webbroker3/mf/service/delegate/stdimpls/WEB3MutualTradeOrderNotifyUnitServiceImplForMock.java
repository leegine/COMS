head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.05.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualTradeOrderNotifyUnitServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3MutualTradeOrderNotifyUnitServiceImplForMock
Author Name      : Daiwa Institute of Research
Revision History : 馮海濤　@(中訊) 新規作成
*/
package webbroker3.mf.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.mf.data.HostXbmfOrderNotifyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;



public class WEB3MutualTradeOrderNotifyUnitServiceImplForMock 
    extends WEB3MutualTradeOrderNotifyUnitServiceImpl{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3MutualTradeOrderNotifyUnitServiceImplForMock.class);
    
    public void notifyTradeOrderNotify(HostXbmfOrderNotifyParams l_orderNotifyQueueParams)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyTradeOrderNotify(HostXbmfOrderNotifyParams l_orderNotifyQueueParams) ";
        log.entering(STR_METHOD_NAME);
    

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.service.delegate.stdimpls.WEB3MutualTradeOrderNotifyUnitServiceImpl",
            "notifyTradeOrderNotify",
            new Class[] {HostXbmfOrderNotifyParams.class}))
        {
            //3)MockFor --〉 asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3MutualTradeOrderNotifyUnitServiceImpl",
                "notifyTradeOrderNotify",
                new Class[] {HostXbmfOrderNotifyParams.class}).asVoid();
            
            log.exiting(STR_METHOD_NAME);
             return;
        }

        log.exiting(STR_METHOD_NAME);
        

    }
}@

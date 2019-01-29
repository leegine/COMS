head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoTradeOrderNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投売買注文通知１件サービスインタフェース(WEB3RuitoTradeOrderNotifyUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 杜森 (中訊) 新規作成
*/
package webbroker3.xbruito.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.xbruito.data.HostRuitoOrderNotifyParams;
import webbroker3.xbruito.WEB3RuitoTradedOrderNotifyDecisionInterceptor;

/**
 * 累投売買注文通知１件サービスインタフェース<BR>
 * <BR>
 * 累投売買注文通知１件ごとの処理を実施する。<BR>
 */
public interface WEB3RuitoTradeOrderNotifyUnitService extends Service
{

    /**
     * @@param l_orderNotifyCue - 累投注文通知キューParams <BR>
     * @@param l_tradeOrderNotifyDecisionInterceptor - 累投売買注文通知確定インタセプタ 
     * <BR>
     * @@roseuid 408F4EF2026C
     */
    public abstract void notifyTradeOrderNotify(
        HostRuitoOrderNotifyParams l_orderNotifyCue,
        WEB3RuitoTradedOrderNotifyDecisionInterceptor l_tradeOrderNotifyDecisionInterceptor)throws WEB3BaseException;
}
@

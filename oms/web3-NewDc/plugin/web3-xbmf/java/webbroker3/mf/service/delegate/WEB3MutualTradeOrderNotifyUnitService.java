head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualTradeOrderNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信売買注文通知UnitService(WEB3MutualTradeOrderNotifyUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 黄建  (中訊) 新規作成
                   2004/08/24 王蘭芬 (中訊) レビュー
*/

package webbroker3.mf.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.mf.data.HostXbmfOrderNotifyParams;

import com.fitechlabs.xtrade.kernel.boot.Service;

/**
 * (投信売買注文通知UnitService)
 * 投信売買注文通知１件サービスインタフェース投信売買注文通知１件ごとの処理を実施する。<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public interface WEB3MutualTradeOrderNotifyUnitService extends Service 
{
    
    /**
     * (notify売買注文通知)<BR>
     * 投信売買注文通知処理を行う。<BR>
     * @@param l_orderNotifyQueueParams - 投信注文通知キューParams<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40BEF93003E7
     */
    public void notifyTradeOrderNotify(HostXbmfOrderNotifyParams l_orderNotifyQueueParams)
    	throws WEB3BaseException;
}
@

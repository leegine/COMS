head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionChangeCancelNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP訂正取消通知UnitService(WEB3OptionChangeCancelNotifyUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/15 盧法@旭 (中訊) 新規作成
*/

package webbroker3.ifo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.ifo.WEB3IfoChangeCancelNotifyUpdateInterceptor;

/**
 * (OP訂正取消通知UnitService)<BR>
 * 株価指数オプション訂正取消通知UnitServiceインタフェイス<BR>
 * @@author 盧法@旭
 * @@version 1.0
 */
public interface WEB3OptionChangeCancelNotifyUnitService extends Service 
{
    
    /**
     * (notify訂正)<BR>
     * 訂正通知処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OP訂正取消通知）notify訂正」参照。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@param l_ifoChangeCancelNotifyInterceptor - 先物OP訂正取消通知インタセプタオブジェクト
     * @@roseuid 4084C2EC022B
     */
    public void notifyChange(OrderUnit l_orderUnit, WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyInterceptor) throws WEB3BaseException;
    
    /**
     * (notify取消)<BR>
     * 取消通知処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OP訂正取消通知）notify取消」参照。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@param l_ifoChangeCancelNotifyInterceptor - 先物OP訂正取消通知インタセプタオブジェクト
     * @@roseuid 4084C3010102
     */
    public String notifyCancel(OrderUnit l_orderUnit, WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyInterceptor) throws WEB3BaseException;
}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.05.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToIfoManualOrderUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP手動発注UnitService(WEB3ToIfoManualOrderUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17　@余新敏(中訊) 新規作成
*/

package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.triggerorder.message.WEB3FuturesOptionsManualUnit;

/**
 * (先物OP手動発注UnitService)<BR>
 * 先物OP手動発注１件サービスインタフェイス<BR>
 * <BR>
 * １件ごとの手動発注処理を実施する。<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public interface WEB3ToIfoManualOrderUnitService extends Service 
{
    
    /**
     * (exec手動発注)<BR>
     * 注文1件ごとの手動発注を行う。<BR>
     * @@param l_strProductType - (銘柄タイプ)<BR>
     * 銘柄タイプ<BR>
     * @@param l_strTriggerOrderType - (条件注文種別)<BR>
     * 条件注文種別<BR>
     * @@param l_strOrderId - (注文ID)<BR>
     * 注文ID<BR>
     * @@param l_blnIsUpdated - (is更新)<BR>
     * is更新<BR>
     * @@param l_lngSubmitterLoginId - (発注者ログインID)<BR>
     * 発注者ログインID<BR>
     * @@param l_strSubmitnotifyType - (通知経路)<BR>
     * 通知経路<BR>
     * @@return WEB3FuturesOptionsManualUnit
     * @@throws WEB3BaseException
     * @@roseuid 43EFF162038A
     */
    public WEB3FuturesOptionsManualUnit execManualOrder(
        String l_strProductType, 
        String l_strTriggerOrderType, 
        String l_strOrderId, 
        boolean l_blnIsUpdated,
        Long l_lngSubmitterLoginId,
        String l_strSubmitnotifyType) throws WEB3BaseException;
}
@

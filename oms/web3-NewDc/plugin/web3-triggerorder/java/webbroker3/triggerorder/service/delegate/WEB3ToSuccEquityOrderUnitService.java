head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.03.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityOrderUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続注文株式発注一件サービス(WEB3ToSuccEquityOrderUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 凌建平(中訊) 新規作成
*/

package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;

/**
 * (連続注文株式発注一件サービス)<BR>
 * 連続注文株式発注一件サービスインタフェース。<BR>
 * （トランザクション属性：　@TX_CREATE_NEW）<BR>
 * <BR>
 * @@author 凌建平 <BR>
 * @@version 1.0<BR>
 */
public interface WEB3ToSuccEquityOrderUnitService extends Service 
{
    
    /**
     * (submit現物株式注文)<BR>
     * 現物株式注文を発注する。<BR>
     * @@param l_rsvEqOrderUnit - (株式予約注文単位)<BR>
     * 株式予約注文単位オブジェクト。<BR>
     * @@exception WEB3BaseException<BR>
     * @@roseuid 432565AE0388
     */
    public void submitEquityOrder(WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException;
    
    /**
     * (submit信用新規建注文)<BR>
     * 信用新規建注文を発注する。<BR>
     * @@param l_rsvEqOrderUnit - (株式予約注文単位)<BR>
     * 株式予約注文単位オブジェクト。<BR>
     * @@exception WEB3BaseException<BR>
     * @@roseuid 43329885027B
     */
    public void submitMarginOpenContractOrder(WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException;
    
    /**
     * (submit信用返済注文)<BR>
     * 信用返済注文を発注する。<BR>
     * @@param l_rsvEqOrderUnit - (株式予約注文単位)<BR>
     * 株式予約注文単位オブジェクト。<BR>
     * @@exception WEB3BaseException<BR>
     * @@roseuid 434622680148
     */
    public void submitMarginSettleContractOrder(WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException;
    
    /**
     * (submit信用現引現渡注文)<BR>
     * 信用現引現渡注文を発注する。<BR>
     * @@param l_rsvEqOrderUnit - (株式予約注文単位)<BR>
     * 株式予約注文単位オブジェクト。<BR>
     * @@exception WEB3BaseException<BR>
     * @@roseuid 43462268014A
     */
    public void submitMarginSwapContractOrder(WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException;
}
@

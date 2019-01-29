head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.01.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopEquityOrderUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 逆指値注文株式発注一件サービス(WEB3ToStopEquityOrderUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/07 呉艶飛(中訊) 新規作成
*/
package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.common.WEB3BaseException;


/**
 * (逆指値注文株式発注一件サービス)<BR>
 * 逆指値注文株式発注一件サービスインターフェイス<BR>
 * （トランザクション属性：TX_CREATE_NEW）<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public interface WEB3ToStopEquityOrderUnitService extends Service 
{
    
    /**
     * (submit現物株式逆指値注文)<BR>
     * 現物株式逆指値注文を発注する。<BR>
     * @@param EqTypeOrderUnit - (株式注文単位)<BR>
     * 株式注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 434C7A5C021A
     */
    public void submitEquityStopOrder(EqTypeOrderUnit EqTypeOrderUnit) throws WEB3BaseException;
    
    /**
     * (submit信用新規建逆指値注文)<BR>
     * 信用新規建逆指値注文を発注する。<BR>
     * @@param l_orderUnit - (株式注文単位)<BR>
     * 株式注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 434C7A5C0277
     */
    public void submitMarginOpenContractStopOrder(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (submit信用返済逆指値注文)<BR>
     * 信用返済逆指値注文を発注する。<BR>
     * @@param l_orderUnit - (株式注文単位)<BR>
     * 株式注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 434C7A5C02C6
     */
    public void submitMarginSettleContractStopOrder(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException;
}
@

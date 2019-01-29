head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.01.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopIfoOrderUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 逆指値注文先物OP発注一件サービス(WEB3ToStopIfoOrderUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/1/23 呉艶飛(中訊) 新規作成
*/
package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;

/**
 * (逆指値注文先物OP発注一件サービス)<BR>
 *逆指値注文先物OP発注一件サービスインターフェイス<BR>
 *（トランザクション属性：TX_CREATE_NEW）<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public interface WEB3ToStopIfoOrderUnitService extends Service 
{

    /**
     * (submit先物新規建逆指値注文)<BR>
     * 先物新規建逆指値注文を発注する。<BR>
     * @@param l_orderUnit - (先物OP注文単位)<BR>
     * 先物OP単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     */
    public void submitFuturesOpenContractStopOrder(IfoOrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (submit先物返済逆指値注文)<BR>
     * 先物返済逆指値注文を発注する。<BR>
     * @@param l_orderUnit - (先物OP注文単位)<BR>
     * 先物OP注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     */
    public void submitFuturesSettleContractStopOrder(IfoOrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (submitOP新規建逆指値注文)<BR>
     * オプション新規建逆指値注文を発注する。<BR>
     * @@param l_orderUnit - (先物OP注文単位)<BR>
     * 先物OP単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     */
    public void submitOptionOpenContractStopOrder(IfoOrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (submitOP返済逆指値注文)<BR>
     * オプション返済逆指値注文を発注する。<BR>
     * @@param l_orderUnit - (先物OP注文単位)<BR>
     * 先物OP注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     */
    public void submitOptionSettleContractStopOrder(IfoOrderUnit l_orderUnit) throws WEB3BaseException;
}
@

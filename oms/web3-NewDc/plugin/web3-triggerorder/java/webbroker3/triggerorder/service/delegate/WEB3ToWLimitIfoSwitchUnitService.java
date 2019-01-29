head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.03.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitIfoSwitchUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : W指値注文先物OP切替一件サービス(WEB3ToWLimitIfoSwitchUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/23　@肖志偉(中訊) 新規作成
*/
package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;


/**
 * (W指値注文先物OP切替一件サービス)<BR>
 * W指値注文先物OP切替一件サービスインターフェイス<BR>
 * （トランザクション属性：TX_CREATE_NEW）<BR>
 * 
 * @@author 肖志偉
 * @@version 1.0
 */
public interface WEB3ToWLimitIfoSwitchUnitService extends Service
{
    
    /**
     * (submit先物新規建W指値注文)<BR>
     * 先物新規建W指値注文切替処理を行う。<BR>
     * @@param l_orderUnit - (先物OP注文単位)<BR>
     * 先物オプション注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44923D5403DC
     */
    public void submitFuturesOpenContractWLimitOrder(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException;
    
    /**
     * (submit先物返済W指値注文)<BR>
     * 先物返済W指値注文切替処理を行う。
     * @@param l_orderUnit - (先物OP注文単位)<BR>
     * 先物オプション注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44923D5403DE
     */
    public void submitFuturesSettleContractWLimitOrder(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException;
    
    /**
     * (submitOP新規建W指値注文)<BR>
     * オプション新規建W指値注文切替処理を行う。<BR>
     * @@param l_orderUnit - (先物OP注文単位)<BR>
     * 先物オプション注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44923D550004
     */
    public void submitOptionOpenContractWLimitOrder(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException;
    
    /**
     * (submitOP返済W指値注文)<BR>
     * オプション返済W指値注文切替処理を行う。<BR>
     * @@param l_orderUnit - (先物OP注文単位)<BR>
     * 先物オプション注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44923D550006
     */
    public void submitOptionSettleContractWLimitOrder(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException;
}
@

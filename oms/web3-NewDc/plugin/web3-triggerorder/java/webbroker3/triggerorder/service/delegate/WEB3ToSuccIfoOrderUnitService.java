head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.01.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccIfoOrderUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続注文先物OP発注一件サービス(WEB3ToSuccIfoOrderUnitService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/06 于瀟(中訊) 新規作成モデル311
*/
package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;

/**
 * (連続注文先物OP発注一件サービス)<BR>
 * 連続注文先物OP発注一件サービスインタフェース。<BR>
 * （トランザクション属性：　@TX_JOIN_EXISTING）<BR>
 * <BR>
 * @@author 于瀟
 * @@version 1.0
 */
public interface WEB3ToSuccIfoOrderUnitService extends Service
{

    /**
     * (submit先物新規建注文)<BR>
     * 先物新規建注文を発注する。<BR>
     * @@param l_rsvIfoOrderUnit - (先物OP予約注文単位)<BR>
     * 先物OP予約注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47DF4E2F03C8
     */
    public void submitFuturesOpenContractOrder(WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit)
        throws WEB3BaseException;

    /**
     * (submit先物返済注文)<BR>
     * 先物返済注文を発注する。<BR>
     * @@param l_rsvIfoOrderUnit - (先物OP予約注文単位)<BR>
     * 先物OP予約注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47DF4E2F03D8
     */
    public void submitFuturesSettleContractOrder(WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit)
        throws WEB3BaseException;

    /**
     * (submitOP新規建注文)<BR>
     * オプション新規建注文を発注する。<BR>
     * @@param l_rsvIfoOrderUnit - (先物OP予約注文単位)<BR>
     * 先物OP予約注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47DF4E2F03DA
     */
    public void submitOptionsOpenContractOrder(WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit)
        throws WEB3BaseException;

    /**
     * (submitOP返済注文)<BR>
     * オプション返済注文を発注する。<BR>
     * @@param l_rsvIfoOrderUnit - (先物OP予約注文単位)<BR>
     * 先物OP予約注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47DF4E2F03DC
     */
    public void submitOptionsSettleContractOrder(WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit)
        throws WEB3BaseException;
}
@

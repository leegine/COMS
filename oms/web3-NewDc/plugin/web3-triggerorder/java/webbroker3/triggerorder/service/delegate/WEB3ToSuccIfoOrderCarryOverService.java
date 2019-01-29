head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.03.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccIfoOrderCarryOverService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP連続注文繰越サービス（WEB3ToSuccIfoOrderCarryOverService.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 金傑 (中訊) 新規作成 モデルNo.276
*/
package webbroker3.triggerorder.service.delegate;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;

/**
 * (先物OP連続注文繰越サービス)<BR>
 * 先物OP連続注文繰越サービスインターフェイス<BR>
 * <BR>
 * （トランザクション属性：TransactionalInterceptor.TX_JOIN_EXISTING）
 * @@author 金傑
 * @@version 1.0
 */
public interface WEB3ToSuccIfoOrderCarryOverService extends Service
{

    /**
     * (exec連続注文繰越)<BR>
     * 連続注文の繰越処理を行う。<BR>
     * @@param l_carryOverOriginalParentOrderUnit - (繰越元の親注文の注文単位)<BR>
     * 繰越元の親注文の注文単位<BR>
     * @@param l_carryOverAfterParentOrderUnit - (繰越後の親注文の注文単位)<BR>
     * 繰越後の親注文の注文単位<BR>
     * @@param l_lisRsvIfoOrderUnits - (予約注文単位一覧)<BR>
     * 予約注文単位一覧<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47D8760A011C
     */
    public void execToSuccOrderCarryOver(
        IfoOrderUnit l_carryOverOriginalParentOrderUnit,
        IfoOrderUnit l_carryOverAfterParentOrderUnit,
        List l_lisRsvIfoOrderUnits) throws WEB3BaseException;
}
@

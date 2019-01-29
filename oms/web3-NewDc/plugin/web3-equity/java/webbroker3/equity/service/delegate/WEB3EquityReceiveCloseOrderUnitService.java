head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityReceiveCloseOrderUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式失効通知一件サービス(WEB3EquityReceiveCloseOrderService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/24 李綱 (中訊) 新規作成
                   2004/09/20 鄒政 (中訊) 修正
                   2004/12/10 中尾寿彦(SRA) 残案件対応による修正
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;

/**
 * （株式失効通知一件サービスインタフェース）。<BR>
 * <BR>
 * （トランザクション属性：TransactionalInterceptor.TX_JOIN_EXISTING）
 * @@version 1.0
 */
public interface WEB3EquityReceiveCloseOrderUnitService extends Service
{

    /**
     * (exec失効)<BR>
     * 注文失効一件処理を行う。<BR>
     * @@param l_params - (株式失効通知キューParams)<BR>
     * 【株式失効通知キューテーブル】の１レコード。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4087A39F02E2
     */
    public void execCloseOrder(
        HostEqtypeCloseOrderNotifyParams l_params,
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException;

	/**
	 * (notify失効)<BR>
	 * @@param l_params - (株式失効通知キューParams)<BR>
	 * 【株式失効通知キューテーブル】の１レコード。<BR>
	 * @@param l_orderUnit - (注文単位オブジェクト)<BR>
	 * @@throws WEB3BaseException
	 */        
    public void notifyCloseOrder(
		HostEqtypeCloseOrderNotifyParams l_params,
		EqTypeOrderUnit l_orderUnit)
		throws WEB3BaseException;
}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderExecNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式出来通知一件サービスインターフェイス(WEB3EquityOrderExecNotifyPartService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 鄭海良(中訊) 新規作成
                   2004/12/02 中尾寿彦(SRA) 残案件対応による修正
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.equity.data.HostEquityOrderExecNotifyParams;

/**
 * （現物株式出来通知一件サービスインターフェイス）。<BR>
 * <BR>
 * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）
 * @@version 1.0
 */
public interface WEB3EquityOrderExecNotifyUnitService extends Service
{
   /**
    * (notify約定)<BR>
    * <BR>
    * 約定処理を行う。<BR>
    * <BR>
    * @@param l_orderUnit - 注文単位
    * @@param l_hostEquityOrderExecNotifyParams - 株式出来通知キューParams
    */
   public void notifyExecute(EqTypeOrderUnit l_ordeUnit, HostEquityOrderExecNotifyParams l_equityExecNotifyQueueParams)
       throws WEB3BaseException;

    /**
     * (notify約定取消)<BR>
     * <BR>
     * 約定取消処理を行う。<BR>
     * <BR>
     * @@param l_orderUnit - 注文単位
     * @@param l_hostEquityOrderExecNotifyParams - 株式出来通知キューParams
     */
    public void notifyExecuteCancel(EqTypeOrderUnit l_ordeUnit, HostEquityOrderExecNotifyParams l_equityExecNotifyQueueParams)
        throws WEB3BaseException;
}
@

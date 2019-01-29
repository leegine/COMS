head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOrderExecNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引出来通知一件サービスインタフェース(WEB3MarginOrderExecNotifyUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/08 凌建平 (中訊) 新規作成
                   2004/12/03 SRA水落 call失効通知処理()削除
                   2005/01/05 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.equity.data.HostEquityOrderExecNotifyParams;

import webbroker3.common.WEB3BaseException;

/**
 * （信用取引出来通知一件サービスインタフェース）。<BR>
 * <BR>
 * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）<BR>
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3MarginOrderExecNotifyUnitService extends Service 
{
    
    /**
     * (notify約定)<BR>
     * 約定処理を実行する。
     * @@param l_ordeUnit - (注文単位)<BR>
     * 出来通知キュー.識別コードに該当する、注文単位オブジェクト。
     * @@param l_equityExecNotifyQueueParams - 株式出来通知キューParamsオブジェクト。
     * @@roseuid 40CFB831029F
     */
    public void notifyExecute(EqTypeOrderUnit l_ordeUnit, HostEquityOrderExecNotifyParams l_equityExecNotifyQueueParams)
        throws WEB3BaseException;
    
    /**
     * (notify約定取消)<BR>
     * 約定取消処理を実行する。
     * @@param l_ordeUnit - (注文単位)<BR>
     * 出来通知キュー.識別コードに該当する、注文単位オブジェクト。
     * @@param l_equityExecNotifyQueueParams - 株式出来通知キューParamsオブジェクト。
     * @@roseuid 40CFB83102A2
     */
    public void notifyExecuteCancel(EqTypeOrderUnit l_ordeUnit, HostEquityOrderExecNotifyParams l_equityExecNotifyQueueParams)
        throws WEB3BaseException;
    
}
@

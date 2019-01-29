head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAndExecutionUpdateService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式出来／約定更新サービス(WEB3FeqOrderAndExecutionUpdateService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19 呉艶飛(中訊) 新規作成
                 : 2005/07/26 王煜(中訊) レビュー
*/
package webbroker3.feq.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;


/**
 * (外国株式出来／約定更新サービス) <BR>
 * 外国株式出来／約定更新サービスインタフェイス <BR>
 * TransactionalInterceptor.TX_JOIN_EXISTING <BR>
 * 
 * @@ author 呉艶飛 
 * @@ version 1.0
 */
public interface WEB3FeqOrderAndExecutionUpdateService extends Service
{
    
    /**
     * (update約定情報) <BR>
     * 出来／約定更新処理を行う <BR>
     *  <BR>
     * シーケンス図 <BR>
     * 「（約定情報更新）update約定情報」参照。 <BR>
     * @@param l_hostFeqOrderExecNotifyParams - (外株出来通知キュー行) <BR>
     * 外株出来通知キュー行オブジェクト <BR>
     *  <BR>
     * ※外株出来通知キューParamsはDDLより自動生成する。 <BR>
     * @@throws WEB3BaseException
     * @@roseuid 428C039102C5
     */
    public void updateExecuteUnit(HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams) 
        throws WEB3BaseException;
    
    /**
     * (update約定取消) <BR>
     * 出来／約定取消更新処理を行う <BR>
     *  <BR>
     * シーケンス図 <BR>
     * 「（約定情報更新）update約定取消」参照。 <BR>
     * @@param l_hostFeqOrderExecNotifyParams - (外株出来通知キュー行) <BR>
     * 外株出来通知キュー行オブジェクト <BR>
     *  <BR>
     * ※外株出来通知キューParamsはDDLより自動生成する。 <BR>
     * 
     * @@param l_lngExecuteId - (約定ＩＤ)
     * @@throws WEB3BaseException
     * @@roseuid 428C039102D5
     */
    public void updateExecuteCancel(
        HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams, 
        long l_lngExecuteId)throws WEB3BaseException;
}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecutionNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式出来通知１件サービス(WEB3AdminFeqExecutionOneNotifyService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/09/12 齊珂 (中訊) 新規作成 
                   2006/10/17 徐大方(中訊) モデルNo.288対応
                   2006/12/15 齊珂 (中訊) モデルNo.311対応
                   2006/12/19 齊珂 (中訊) モデルNo.319対応   
                   2006/12/20 齊珂 (中訊) モデルNo.323対応
Revesion History : 2008/10/02 大澤(SRA) 【外国株式】仕様変更管理台帳（モデル）No.468               
Revesion History : 2010/03/05 武波 (中訊)【外国株式】仕様変更管理台帳（モデル）No.541
*/

package webbroker3.feq.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;
import webbroker3.slebase.data.SleRcvdQParams;

/**
 * (外国株式出来通知１件サービス)<BR>
 * 外国株式出来通知１件サービスインタフェイス <BR>
 * <BR>
 * （トランザクション属性：TransactionalInterceptor.TX_JOIN_EXISTING）<BR>
 *   
 * @@author 齊珂
 * @@version 1.0
 */
public interface WEB3FeqExecutionNotifyUnitService extends Service
{

    /**
     * notify約定<BR>
     * 約定処理を行う。<BR>
     * @@param l_feqOrderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_hostFeqOrderExecNotifyParams - (外株出来通知キュー)<BR>
     * 外株出来通知キュー<BR>
     * @@param l_sleRvcdQParams - (外国株取引RCVD_Q)<BR>
     * 外国株取引RCVD_Q<BR>
     * @@param l_todayLoginFlag - (当日為替登録フラグ)<BR>
     * 当日為替登録フラグ<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 429FECAF006D
     */
    public void notifyOrder(
    	WEB3FeqOrderUnit l_feqOrderUnit, 
    	HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams,
    	SleRcvdQParams l_sleRvcdQParams,
        Boolean l_todayLoginFlag)
        throws WEB3BaseException;
}
@

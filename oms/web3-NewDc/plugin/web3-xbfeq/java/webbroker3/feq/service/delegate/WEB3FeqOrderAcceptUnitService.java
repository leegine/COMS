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
filename	WEB3FeqOrderAcceptUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文受付１件サービス(WEB3FeqOrderAcceptUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 徐大方 (中訊) 新規作成
                   2006/11/20 徐大方(中訊) モデル　@No.304対応
                   2006/12/14 齊珂(中訊) モデル　@No.311対応
                   2006/12/19 徐宏偉(中訊) モデル　@No.318対応
Revesion History : 2008/02/26 馮海濤(中訊) モデル　@No.401対応
*/

package webbroker3.feq.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.message.WEB3FeqOrderAcceptCancelUnit;
import webbroker3.slebase.data.SleRcvdQParams;

/**
 * (外国株式注文受付１件サービス)<BR>
 * 外国株式注文受付１件サービスインタフェイス<BR>
 * <BR>
 * （トランザクション属性：TransactionalInterceptor.TX_JOIN_EXISTING）<BR>
 * 
 * @@author 徐大方
 * @@version 1.0
 */
public interface WEB3FeqOrderAcceptUnitService extends Service 
{
    /**
     * (notify注文受付)<BR>
     * 注文受付１件処理を行う。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_cancelUnit - (外国株式注文受付取消情報)<BR>
     * 外国株式注文受付取消情報<BR>
     * @@param l_sleRvcdQParams - (外国株取引RCVD_Q)<BR>
     * SLE_RCVD_Q<BR>
     * @@throws WEB3BaseException
     */
    public void notifyOrderAccept(
        WEB3FeqOrderUnit l_orderUnit,
        WEB3FeqOrderAcceptCancelUnit l_cancelUnit,
        SleRcvdQParams l_sleRvcdQParams) throws WEB3BaseException;
}
@

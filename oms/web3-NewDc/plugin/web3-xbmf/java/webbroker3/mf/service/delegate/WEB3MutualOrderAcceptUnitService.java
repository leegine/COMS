head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualOrderAcceptUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信注文受付UnitService(WEB3MutualOrderAcceptUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 黄建  (中訊) 新規作成
                   2004/08/24 王蘭芬 (中訊) レビュー
*/
package webbroker3.mf.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.mf.WEB3MutualFundAcceptConfirmInterceptor;

/**
 * (投信注文受付UnitService)<BR>
 * 投信注文受付１件サービスインタフェース
 * 注文１件ごとの受付処理を実施する。<BR>
 * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public interface WEB3MutualOrderAcceptUnitService extends Service 
{

    /**
     * 投信注文受付完了処理をおこなう。<BR>
     * @@param l_mutualFundOrderUnit - 投信注文単位 <BR>
     * @@param l_mutualFundAcceptConfirmInterceptor - 投信受付確定インタセプタ<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 408887A103B8
     */
    public void notifyOrderAcceptComplete(
        MutualFundOrderUnit l_mutualFundOrderUnit,
        WEB3MutualFundAcceptConfirmInterceptor l_mutualFundAcceptConfirmInterceptor)
        throws WEB3BaseException;

    /**
     * 投信注文受付失敗処理をおこなう。<BR>
     * @@param l_mutualFundOrderUnit - 投信注文単位 <BR>
     * @@param l_mutualFundAcceptConfirmInterceptor - 投信受付確定インタセプタ<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 408887A103BA
     */
    public void notifyOrderAcceptFail(
        MutualFundOrderUnit l_mutualFundOrderUnit,
        WEB3MutualFundAcceptConfirmInterceptor l_mutualFundAcceptConfirmInterceptor)
        throws WEB3BaseException;
}
@

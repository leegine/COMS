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
filename	WEB3EquityChangeCancelAcceptUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式訂正取消受付一件サービスのインタフェース(WEB3EquityChangeCancelAcceptUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/22 盧法@旭 (中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.equity.data.HostEqtypeOrderAcceptParams;

/**
 * （株式訂正取消受付一件サービス）。<BR>
 * <BR>
 * 株式訂正取消受付一件サービスのインタフェース<BR>
 * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）
 * @@author 盧法@旭
 * @@version 1.0
 */
public interface WEB3EquityChangeCancelAcceptUnitService extends Service 
{
    
    /**
     * (notify訂正取消受付)<BR>
     * 【株式注文受付キューテーブル】訂正取消受付のキューデータ一件<BR>
     * に対する処理を行う。<BR>
     * （注文受付結果 == （"注文受付完了"、"エラー"）の場合）<BR>
     * @@param l_orderAcceptQueParams - 株式注文受付キューParams。
     * @@roseuid 4100F97600A1
     */
    public void notifyChangeCancelAccept(HostEqtypeOrderAcceptParams l_orderAcceptQueParams)
        throws WEB3BaseException;
    
    /**
     * (notify訂正取消受付時間外)<BR>
     * 【株式注文受付キューテーブル】訂正取消受付のキューデータ一件に対する処理を行う。<BR>
     * （注文受付結果 == "前場受付時間外エラー"の場合）<BR>
     * @@param l_params - (株式注文受付キューParams)<BR>
     * 株式注文受付キューParams。
     * @@throws WEB3BaseException
     */
    public void notifyChangeCancelAcceptOvertime(
        HostEqtypeOrderAcceptParams l_params)
        throws WEB3BaseException;
}
@

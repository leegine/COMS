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
filename	WEB3EquityOrderNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文通知一件サービス(WEB3EquityOrderNotifyPartService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 欒学峰 (中訊) 新規作成
                   2004/12/15 岡村和明(SAR) 残案件対応 Ｎｏ.１５４
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;

/**
 * （株式注文通知一件サービス）。<br>
 * <br>
 * 株式注文通知一件サービスインターフェース<br>
 * <br>
 * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）
 * @@version 1.0
 */
public interface WEB3EquityOrderNotifyUnitService extends Service
{

    /**
     * （通知一件処理）。
     * @@param l_hostEqtypeOrderReceiptParams 株式注文入力通知データアダプタ
     * @@roseuid 405FF51C0388
     */
    public void notifyPartProcess(WEB3EquityOrderInputNotifyAdapter l_orderInputNotifyAdapter)
        throws WEB3BaseException;
}
@
